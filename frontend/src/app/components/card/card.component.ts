import {Component, OnInit, Input} from '@angular/core';
import {Card} from "../../models/card";
import {UtilService} from "../../services/util.service";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
  @Input() cards: Card[];

  constructor(private utilService: UtilService) {
  }

  ngOnInit(): void {
  }

  getCardName(cardSuitNum: number) {
    return this.utilService.cardRank(cardSuitNum) + ' of ' + this.utilService.cardSuit(cardSuitNum);
  }

}
