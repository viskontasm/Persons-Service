import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class HttpService {
  private api = 'http://localhost:8080';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  callPersonsService(value: string): Observable<any[]> {
    return this.httpClient.get<any[]>(this.api + '/persons/search/' + value);
    // return this.httpClient.get<any[]>(this.api + '/persons');
  }
}
