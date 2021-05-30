import {Component, OnInit} from '@angular/core';
import {ApiService} from "./services/api.service";
import {Constants} from "./constants";
import {Card} from "./models/card";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  message = 'Joining game...';
  cards: Card[];

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.apiService.socket.subscribe(res => {
      switch (res.type) {
        case Constants.JOIN_RES_TYPE:
          this.message = res.body.player === Constants.PLAYER_RED ? 'You are the RED player' : 'You are the BLUE player';
          break;
        case Constants.START_GAME_RES_TYPE:
          this.cards = res.body.hand;
          break;
        case Constants.ERROR_RES_TYPE:
          this.message = res.body.error;
          break;
      }
    });
    this.apiService.sendObject(Constants.JOIN_REQ_TYPE, {
      name: 'Testing player'
    });
  }
}
