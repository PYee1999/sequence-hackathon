import {Component, Input, OnInit} from '@angular/core';
import {Board} from '../../models/board';
import {UtilService} from '../../services/util.service';
import {Space} from '../../models/space';
import {ApiService} from '../../services/api.service';
import {Constants} from '../../constants';
import {Card} from '../../models/card';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
  @Input() board: Board;
  @Input() spaces: Space[];
  @Input() selectedCard: Card;

  constructor(private utilService: UtilService, private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  selectSpace(x: number, y: number, cardSuitNum: number): void {
    const found = this.spaces.find(val => val.xLocation === x && val.yLocation === y);
    if (found) {
      this.apiService.sendObject(Constants.SELECT_SPACE_REQ_TYPE, {
        x,
        y,
        card: cardSuitNum,
        player: this.utilService.player
      });
    }
  }

  selectable(space: Space): boolean {
    return !!this.spaces.find(val => val.xLocation === space.xLocation && val.yLocation === space.yLocation);
  }

  reverse(arr: any[]): any[] {
    return [...arr].reverse();
  }
}
