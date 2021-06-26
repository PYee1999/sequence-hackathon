import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private responses: Subject<any> = new Subject<any>();
  private connected: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private webSocket: WebSocket;

  get socket(): Observable<any> {
    return this.responses.asObservable();
  }

  constructor() {
    if (environment.production) {
      if (window.location.host.includes('heroku')) {
        this.webSocket =
          new WebSocket(`wss://${window.location.host}/api`);
      } else {
        this.webSocket =
          new WebSocket(`ws://${window.location.host}/api`);
      }
    } else {
      this.webSocket =
        new WebSocket('ws://localhost:8080/api');
    }
    this.webSocket.onopen = () => this.connected.next(true);
    this.webSocket.onmessage = res => this.responses.next(JSON.parse(res.data));
  }

  sendObject(type: string, body: any): void {
    this.connected.subscribe(con => {
      if (con) {
        this.webSocket.send(JSON.stringify({
          type,
          body: JSON.stringify(body)
        }));
      }
    })
  }
}
