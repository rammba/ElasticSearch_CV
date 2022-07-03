package rs.ac.uns.ftn.udd.rammba.elasticsearch.services;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto.GeolocationDto;
import rs.ac.uns.ftn.udd.rammba.elasticsearch.model.Coordinates;

@Service
public class GeolocationService implements IGeolocationService {

	private final String LOCALHOST_IPV4 = "127.0.0.1";
	private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

	private static final String[] VALID_IP_HEADERS = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	private static String getClientIpFromHeaders(HttpServletRequest request) {
		for (String header : VALID_IP_HEADERS) {
			String ipAddress = request.getHeader(header);
			if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
				return ipAddress;
			}
		}
		return request.getRemoteAddr();
	}

	@Override
	public String getClientIp(HttpServletRequest request) {
		String ipAddress = getClientIpFromHeaders(request);

		if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
				ipAddress = inetAddress.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		return ipAddress;
	}

	@Override
	public Coordinates getCoordinates(String ipAddress) {
		String json = getGeolocationResponse(ipAddress);
		if (json == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			GeolocationDto dto = mapper.readValue(json, GeolocationDto.class);
			return dto.getCoordinates();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return getCoordinatesFromHardcodedIp();
		}
	}

	private Coordinates getCoordinatesFromHardcodedIp() {
		String json = getGeolocationResponse("188.2.1.1");
		if (json == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			GeolocationDto dto = mapper.readValue(json, GeolocationDto.class);
			return dto.getCoordinates();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getGeolocationResponse(String idAddress) {
		return HttpService.get("http://ip-api.com/json/" + idAddress);
	}

}
