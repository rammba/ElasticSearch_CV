import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Applicant } from './models/applicant.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private pathPrefix = 'http://localhost:9000/api/search/';

  constructor(private http: HttpClient) { }

  byFullName(name: string, surname: string): Observable<Applicant[]> {
    let params = new HttpParams()
      .set("name", name)
      .set("surname", surname);

    return this.http.get<Applicant[]>(this.pathPrefix + 'full-name', { headers: this.getHeaders(), params: params });
  }

  byDegree(degree: string): Observable<Applicant[]> {
    let params = new HttpParams()
      .set("degree", degree);

    return this.http.get<Applicant[]>(this.pathPrefix + 'degree', { headers: this.getHeaders(), params: params });
  }

  byCvContent(cvContent: string): Observable<Applicant[]> {
    let params = new HttpParams()
      .set("cvContent", cvContent);

    return this.http.get<Applicant[]>(this.pathPrefix + 'cv', { headers: this.getHeaders(), params: params });
  }

  booleanSimple(key1: string, value1: string, key2: string, value2: string, isAndOperation: boolean): Observable<Applicant[]> {
    let params = new HttpParams()
      .set('key1', key1)
      .set('value1', value1)
      .set('key2', key2)
      .set('value2', value2)
      .set('isAndOperation', isAndOperation)

    return this.http.get<Applicant[]>(this.pathPrefix + 'boolean-simple', { headers: this.getHeaders(), params: params });
  }

  private getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    return headers;
  }
}
