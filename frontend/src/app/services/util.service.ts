import {Injectable} from '@angular/core';
import {Constants} from "../constants";

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() {
  }

  cardSuit(cardSuitNum: number) {
    switch (Math.floor(cardSuitNum / 100)) {
      case Constants.SPADES_SUIT:
        return 'Spades';
      case Constants.CLUBS_SUIT:
        return 'Clubs';
      case Constants.DIAMONDS_SUIT:
        return 'Diamonds';
      case Constants.HEARTS_SUIT:
        return 'Hearts';
    }
  }

  cardRank(cardSuitNum: number) {
    const rank = cardSuitNum % 100;
    if(rank === 1) {
      return 'Ace';
    }
    if (rank <= 10) {
      return '' + rank;
    }
    switch (rank) {
      case 11:
        return 'Jack';
      case 12:
        return 'Queen';
      case 13:
        return 'King';
    }
  }
}
