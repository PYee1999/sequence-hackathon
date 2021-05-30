import {Component, Input, OnInit} from '@angular/core';
import {Board} from "../../models/board";
import {UtilService} from "../../services/util.service";

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
  @Input() board: Board;

  constructor(private utilService: UtilService) {
  }

  ngOnInit(): void {
  }

  getCardName(cardSuitNum: number) {
    if(Math.floor(cardSuitNum / 100) === 5) {
      return 'Free space';
    }
    return this.utilService.cardRank(cardSuitNum) + ' of ' + this.utilService.cardSuit(cardSuitNum);
  }

}
