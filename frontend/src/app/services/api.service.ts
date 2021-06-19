import {Injectable} from '@angular/core';
import {WebSocketSubject} from 'rxjs/internal-compatibility';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private webSocket: WebSocketSubject<any>;

  get socket(): Observable<any> {
    return this.webSocket.asObservable();
  }

  constructor() {
    if (environment.production) {
      this.webSocket =
        new WebSocketSubject(`ws://${window.location.host}/api`);
    } else {
      this.webSocket =
        new WebSocketSubject('ws://localhost:8080/api');
    }
  }

  sendObject(type: string, body: any): void {
    this.webSocket.next({
      type,
      body: JSON.stringify(body)
    });
  }
}
