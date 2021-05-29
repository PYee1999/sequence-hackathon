import {Component, OnInit} from '@angular/core';
import {ApiService} from "./services/api.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.apiService.socket.subscribe(console.log);
    this.apiService.sendObject({
      message: 'hello'
    });
    setTimeout(() => {
      this.apiService.sendObject({
        message: 'hello again'
      });
    }, 1000);
  }
}
