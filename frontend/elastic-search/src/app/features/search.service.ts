import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private pathPrefix = 'http://localhost:9000/api/search/';

  constructor(private http: HttpClient) { }

  byFullName(name: string, surname: string) {
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    let params = new HttpParams()
      .set("name", name)
      .set("surname", surname);

    // this.http.post(this.pathPrefix + 'full-name', body)
    //   .subscribe(a => console.log(a));
    this.http.get(this.pathPrefix + 'full-name', { headers: headers, params: params })
      .subscribe(a => console.log(a));
  }
}
