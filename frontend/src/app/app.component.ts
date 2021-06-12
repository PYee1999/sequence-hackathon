import {Component, OnInit} from '@angular/core';
import {ApiService} from './services/api.service';
import {Constants} from './constants';
import {Card} from './models/card';
import {Board} from './models/board';
import {Space} from './models/space';
import {UtilService} from './services/util.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  joining = true;
  _message = '';
  get message() {
    return this._message;
  }
  set message(value: string) {
    this._message = value;
    setTimeout(() => this._message = '', 5000)
  }
  player = 0;
  cards: Card[];
  spaces: Space[] = [];
  board: Board;
  selectedCard: Card;

  constructor(private apiService: ApiService, private utilService: UtilService) {
  }

  ngOnInit() {
    this.apiService.socket.subscribe(res => {
      console.log(res);
      switch (res.type) {
        case Constants.JOIN_RES_TYPE:
          this.player = this.utilService.player = res.body.player;
          this.joining = false;
          break;
        case Constants.START_GAME_RES_TYPE:
          this.cards = res.body.hand;
          this.board = res.body.board;
          break;
        case Constants.SELECT_CARD_RES_TYPE:
          this.spaces = res.body.spaces;
          break;
        case Constants.SELECT_SPACE_RES_TYPE:
          this.board = res.body.board;
          this.spaces = [];
          if(res.body.winner !== 0) {
            this.message = (res.body.winner === 1 ? 'Red' : 'Blue') + ' wins!';
          }
          if(res.body.currentPlayer !== this.player) {
            this.cards = res.body.hand;
          }
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

  cardSelected(card: Card) {
    this.selectedCard = card;
    this.apiService.sendObject(Constants.SELECT_CARD_REQ_TYPE, {
      player: this.player,
      ...card
    });
  }

  getCardName(cardSuitNum: number) {
    return this.utilService.cardRank(cardSuitNum) + ' of ' + this.utilService.cardSuit(cardSuitNum);
  }
}
