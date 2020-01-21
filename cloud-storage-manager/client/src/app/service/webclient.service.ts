import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BikeData} from '../model/BikeData';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class WebclientService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  querySingleValue(key: string): Observable<BikeData> {
    const url = this.baseUrl + '/values/' + key;
    return this.http.get<BikeData>(url);
  }

  queryRange(fromKey: string, toKey: string): Observable<BikeData[]> {
    const url = this.baseUrl + '/values?from=' + fromKey + '&to=' + toKey;
    return this.http.get<BikeData[]>(url);
  }

  insertValue(value: BikeData): Observable<string> {
    const url = this.baseUrl + '/values';
    return this.http.post<string>(url, value);
  }

  deleteValue(key: string): Observable<string> {
    const url = this.baseUrl + '/values/' + key;
    return this.http.delete<string>(url);
  }
}
