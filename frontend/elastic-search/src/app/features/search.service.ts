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
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    let params = new HttpParams()
      .set("name", name)
      .set("surname", surname);

    return this.http.get<Applicant[]>(this.pathPrefix + 'full-name', { headers: headers, params: params });
  }
}
