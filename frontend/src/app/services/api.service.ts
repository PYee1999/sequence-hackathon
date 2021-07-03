import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {interval, Observable} from 'rxjs';
import {WebSocketSubject, webSocket} from 'rxjs/webSocket';
import {map} from "rxjs/operators";

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
      if (window.location.host.includes('heroku')) {
        this.webSocket =
          webSocket(`wss://${window.location.host}/api`);
      } else {
        this.webSocket =
          webSocket(`ws://${window.location.host}/api`);
      }
    } else {
      this.webSocket =
        webSocket('ws://localhost:8080/api');
    }
    interval(2000)
      .pipe(map(() => this.webSocket.next({
        type: 'ping',
        body: ''
      })))
      .subscribe();
  }

  sendObject(type: string, body: any): void {
    this.webSocket.next({
      type,
      body: JSON.stringify(body)
    });
  }
}
