import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Card} from '../../models/card';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
  @Input() cards: Card[];
  @Output() cardSelected = new EventEmitter<Card>();

  constructor(private utilService: UtilService) {
  }

  ngOnInit(): void {
  }

}
