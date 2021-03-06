import { HttpClient, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(private http: HttpClient) { }

  apply(name: string, surname: string, mail: string, address: string, degree: string, cv: File): Observable<Object> {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    let body = new FormData();
    body.append("name", name);
    body.append("surname", surname);
    body.append("mail", mail);
    body.append("address", address);
    body.append("degree", degree);
    body.append("cv", cv);

    return this.http.post('http://localhost:9000/api/job/application', body);
  }
}
