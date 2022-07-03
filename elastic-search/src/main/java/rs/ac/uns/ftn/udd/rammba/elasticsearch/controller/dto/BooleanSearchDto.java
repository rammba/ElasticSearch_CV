package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto;

public class BooleanSearchDto {
	public String key;
	public String value;
	public Boolean isAndOperation;
	
	public BooleanSearchDto(String key, String value, Boolean isAndOperation) {
		this.key = key;
		this.value = value;
		this.isAndOperation = isAndOperation;
	}
}
