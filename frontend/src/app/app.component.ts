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
  lobbyFull = false;
  message_ = '';
  get message(): string {
    return this.message_;
  }

  set message(value: string) {
    this.message_ = value;
    const timeout = value.length > 80 ? 15000 : 5000;
    setTimeout(() => this.message_ = '', timeout);
  }

  player = 0;
  winner = 0;
  sequences = 0;
  enemySequences = 0;
  cards: Card[];
  spaces: Space[] = [];
  board: Board;
  selectedCard: Card;

  constructor(private apiService: ApiService, private utilService: UtilService) {
  }

  ngOnInit(): void {
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
          if (this.player === 1) {
            this.sequences = res.body.redSequences;
            this.enemySequences = res.body.blueSequences;
          } else {
            this.sequences = res.body.blueSequences;
            this.enemySequences = res.body.redSequences;
          }
          if (res.body.winner !== 0) {
            this.winner = res.body.winner;
          }
          if (res.body.currentPlayer !== this.player) {
            this.cards = res.body.hand;
          } else {
            this.checkDeadCards();
          }
          break;
        case Constants.DEAD_CARD_RES_TYPE:
          if (res.body.cardRemoved) {
            this.message = `A dead card (${this.getCardName(res.body.oldCard.cardSuitNum)}) was removed from your deck.
            It was replaced with ${this.getCardName(res.body.newCard.cardSuitNum)}`;
            this.cards = res.body.hand;
          }
          break;
        case Constants.ERROR_RES_TYPE:
          if (res.body.error.toLowerCase().includes('lobby is full')) {
            this.lobbyFull = true;
            this.joining = false;
            this.cards = [];
          } else {
            this.message = res.body.error;
          }
          break;
      }
    });
    this.apiService.sendObject(Constants.JOIN_REQ_TYPE, {
      name: 'Testing player'
    });
  }

  cardSelected(card: Card): void {
    console.log('Card selected:', card);
    this.selectedCard = card;
    this.apiService.sendObject(Constants.SELECT_CARD_REQ_TYPE, {
      player: this.player,
      ...card
    });
  }

  checkDeadCards(): void {
    this.apiService.sendObject(Constants.DEAD_CARD_REQ_TYPE, {
      player: this.player
    });
  }

  getCardName(cardSuitNum: number): string {
    return this.utilService.cardRank(cardSuitNum) + ' of ' + this.utilService.cardSuit(cardSuitNum);
  }
}
