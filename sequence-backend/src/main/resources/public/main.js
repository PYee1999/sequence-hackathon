(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Anand\sequence-hackathon\frontend\src\main.ts */"zUnb");


/***/ }),

/***/ "2Rin":
/*!******************************************!*\
  !*** ./src/app/services/util.service.ts ***!
  \******************************************/
/*! exports provided: UtilService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UtilService", function() { return UtilService; });
/* harmony import */ var _constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../constants */ "l207");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");


class UtilService {
    constructor() {
    }
    cardSuit(cardSuitNum) {
        switch (Math.floor(cardSuitNum / 100)) {
            case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].SPADES_SUIT:
                return 'Spades';
            case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].CLUBS_SUIT:
                return 'Clubs';
            case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].DIAMONDS_SUIT:
                return 'Diamonds';
            case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].HEARTS_SUIT:
                return 'Hearts';
        }
    }
    cardRank(cardSuitNum) {
        const rank = cardSuitNum % 100;
        if (rank === 1) {
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
UtilService.ɵfac = function UtilService_Factory(t) { return new (t || UtilService)(); };
UtilService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: UtilService, factory: UtilService.ɵfac, providedIn: 'root' });


/***/ }),

/***/ "AytR":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "H+bZ":
/*!*****************************************!*\
  !*** ./src/app/services/api.service.ts ***!
  \*****************************************/
/*! exports provided: ApiService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ApiService", function() { return ApiService; });
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../environments/environment */ "AytR");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var rxjs_webSocket__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/webSocket */ "3uOa");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ "fXoL");





class ApiService {
    constructor() {
        if (_environments_environment__WEBPACK_IMPORTED_MODULE_0__["environment"].production) {
            if (window.location.host.includes('heroku')) {
                this.webSocket =
                    new rxjs_webSocket__WEBPACK_IMPORTED_MODULE_2__["WebSocketSubject"](`wss://${window.location.host}/api`);
            }
            else {
                this.webSocket =
                    new rxjs_webSocket__WEBPACK_IMPORTED_MODULE_2__["WebSocketSubject"](`ws://${window.location.host}/api`);
            }
        }
        else {
            this.webSocket =
                new rxjs_webSocket__WEBPACK_IMPORTED_MODULE_2__["WebSocketSubject"]('ws://localhost:8080/api');
        }
        Object(rxjs__WEBPACK_IMPORTED_MODULE_1__["interval"])(2000)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_3__["map"])(() => this.webSocket.next({
            type: 'ping',
            body: ''
        })))
            .subscribe();
    }
    get socket() {
        return this.webSocket.asObservable();
    }
    sendObject(type, body) {
        this.webSocket.next({
            type,
            body: JSON.stringify(body)
        });
    }
}
ApiService.ɵfac = function ApiService_Factory(t) { return new (t || ApiService)(); };
ApiService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineInjectable"]({ token: ApiService, factory: ApiService.ɵfac, providedIn: 'root' });


/***/ }),

/***/ "Sy1n":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./constants */ "l207");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_api_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./services/api.service */ "H+bZ");
/* harmony import */ var _services_util_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./services/util.service */ "2Rin");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _components_board_board_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./components/board/board.component */ "qtDB");
/* harmony import */ var _components_card_card_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./components/card/card.component */ "lXt9");







function AppComponent_h1_0_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "h1");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"]("You are the ", ctx_r0.player === 1 ? "red" : "blue", " player.");
} }
function AppComponent_h2_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "h2");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"]("", ctx_r1.winner === 1 ? "Red" : "Blue", " wins!");
} }
function AppComponent_h2_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "h2");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1, "Joining game...");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} }
function AppComponent_h2_3_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "h2");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1, "The lobby is full. Please try again later.");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} }
function AppComponent_h2_6_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "h2");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](1, "Waiting for game to start...");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} }
function AppComponent_app_board_8_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](0, "app-board", 5);
} if (rf & 2) {
    const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("board", ctx_r5.board)("spaces", ctx_r5.spaces)("selectedCard", ctx_r5.selectedCard);
} }
function AppComponent_div_9_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](1, "p");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](3, "p");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r6 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"]("Your sequences: ", ctx_r6.sequences, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate1"]("Opponent's sequences: ", ctx_r6.enemySequences, "");
} }
function AppComponent_app_card_10_Template(rf, ctx) { if (rf & 1) {
    const _r9 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "app-card", 7);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵlistener"]("cardSelected", function AppComponent_app_card_10_Template_app_card_cardSelected_0_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵrestoreView"](_r9); const ctx_r8 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"](); return ctx_r8.cardSelected($event); });
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("cards", ctx_r7.cards);
} }
class AppComponent {
    constructor(apiService, utilService) {
        this.apiService = apiService;
        this.utilService = utilService;
        this.title = 'frontend';
        this.joining = true;
        this.lobbyFull = false;
        this.message_ = '';
        this.player = 0;
        this.winner = 0;
        this.sequences = 0;
        this.enemySequences = 0;
        this.spaces = [];
    }
    get message() {
        return this.message_;
    }
    set message(value) {
        this.message_ = value;
        const timeout = value.length > 80 ? 15000 : 5000;
        setTimeout(() => this.message_ = '', timeout);
    }
    ngOnInit() {
        this.apiService.socket.subscribe(res => {
            console.log(res);
            switch (res.type) {
                case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].JOIN_RES_TYPE:
                    this.player = this.utilService.player = res.body.player;
                    this.joining = false;
                    break;
                case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].START_GAME_RES_TYPE:
                    this.cards = res.body.hand;
                    this.board = res.body.board;
                    break;
                case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].SELECT_CARD_RES_TYPE:
                    this.spaces = res.body.spaces;
                    break;
                case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].SELECT_SPACE_RES_TYPE:
                    this.board = res.body.board;
                    this.spaces = [];
                    if (this.player === 1) {
                        this.sequences = res.body.redSequences;
                        this.enemySequences = res.body.blueSequences;
                    }
                    else {
                        this.sequences = res.body.blueSequences;
                        this.enemySequences = res.body.redSequences;
                    }
                    if (res.body.winner !== 0) {
                        this.winner = this.utilService.winner = res.body.winner;
                    }
                    if (res.body.currentPlayer !== this.player) {
                        this.cards = res.body.hand;
                    }
                    else {
                        this.checkDeadCards();
                    }
                    break;
                case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].DEAD_CARD_RES_TYPE:
                    if (res.body.cardRemoved) {
                        this.message = `A dead card (${this.getCardName(res.body.oldCard.cardSuitNum)}) was removed from your deck.
            It was replaced with ${this.getCardName(res.body.newCard.cardSuitNum)}`;
                        this.cards = res.body.hand;
                    }
                    break;
                case _constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].ERROR_RES_TYPE:
                    if (res.body.error.toLowerCase().includes('lobby is full')) {
                        this.lobbyFull = true;
                        this.joining = false;
                        this.cards = [];
                    }
                    else {
                        this.message = res.body.error;
                    }
                    break;
            }
        });
        this.apiService.sendObject(_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].JOIN_REQ_TYPE, {
            name: 'Testing player'
        });
    }
    cardSelected(card) {
        if (!this.winner) {
            console.log('Card selected:', card);
            this.selectedCard = card;
            this.apiService.sendObject(_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].SELECT_CARD_REQ_TYPE, Object.assign({ player: this.player }, card));
        }
    }
    checkDeadCards() {
        this.apiService.sendObject(_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].DEAD_CARD_REQ_TYPE, {
            player: this.player
        });
    }
    getCardName(cardSuitNum) {
        return this.utilService.cardRank(cardSuitNum) + ' of ' + this.utilService.cardSuit(cardSuitNum);
    }
}
AppComponent.ɵfac = function AppComponent_Factory(t) { return new (t || AppComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_api_service__WEBPACK_IMPORTED_MODULE_2__["ApiService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_util_service__WEBPACK_IMPORTED_MODULE_3__["UtilService"])); };
AppComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: AppComponent, selectors: [["app-root"]], decls: 11, vars: 9, consts: [[4, "ngIf"], [2, "display", "flex"], [3, "board", "spaces", "selectedCard", 4, "ngIf"], ["style", "display: flex; flex-direction: column", 4, "ngIf"], [3, "cards", "cardSelected", 4, "ngIf"], [3, "board", "spaces", "selectedCard"], [2, "display", "flex", "flex-direction", "column"], [3, "cards", "cardSelected"]], template: function AppComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](0, AppComponent_h1_0_Template, 2, 1, "h1", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](1, AppComponent_h2_1_Template, 2, 1, "h2", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](2, AppComponent_h2_2_Template, 2, 0, "h2", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](3, AppComponent_h2_3_Template, 2, 0, "h2", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](4, "h2");
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtext"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](6, AppComponent_h2_6_Template, 2, 0, "h2", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](7, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](8, AppComponent_app_board_8_Template, 1, 3, "app-board", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](9, AppComponent_div_9_Template, 5, 2, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](10, AppComponent_app_card_10_Template, 1, 1, "app-card", 4);
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.player);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.winner);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.joining);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.lobbyFull);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtextInterpolate"](ctx.message);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", !ctx.cards && !ctx.joining);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.board);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.board);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", ctx.cards);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_4__["NgIf"], _components_board_board_component__WEBPACK_IMPORTED_MODULE_5__["BoardComponent"], _components_card_card_component__WEBPACK_IMPORTED_MODULE_6__["CardComponent"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhcHAuY29tcG9uZW50LnNjc3MifQ== */"] });


/***/ }),

/***/ "ZAI4":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "jhN1");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./app-routing.module */ "vY5A");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app.component */ "Sy1n");
/* harmony import */ var _components_card_card_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./components/card/card.component */ "lXt9");
/* harmony import */ var _components_board_board_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./components/board/board.component */ "qtDB");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ "fXoL");






class AppModule {
}
AppModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineNgModule"]({ type: AppModule, bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"]] });
AppModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjector"]({ factory: function AppModule_Factory(t) { return new (t || AppModule)(); }, providers: [], imports: [[
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
            _app_routing_module__WEBPACK_IMPORTED_MODULE_1__["AppRoutingModule"]
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](AppModule, { declarations: [_app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"],
        _components_card_card_component__WEBPACK_IMPORTED_MODULE_3__["CardComponent"],
        _components_board_board_component__WEBPACK_IMPORTED_MODULE_4__["BoardComponent"]], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
        _app_routing_module__WEBPACK_IMPORTED_MODULE_1__["AppRoutingModule"]] }); })();


/***/ }),

/***/ "l207":
/*!******************************!*\
  !*** ./src/app/constants.ts ***!
  \******************************/
/*! exports provided: Constants */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Constants", function() { return Constants; });
class Constants {
}
Constants.PLAYER_RED = 1;
Constants.PLAYER_BLUE = -1;
Constants.SPADES_SUIT = 1;
Constants.CLUBS_SUIT = 2;
Constants.DIAMONDS_SUIT = 3;
Constants.HEARTS_SUIT = 4;
Constants.JOIN_REQ_TYPE = 'join';
Constants.SELECT_CARD_REQ_TYPE = 'selectcard';
Constants.SELECT_SPACE_REQ_TYPE = 'selectspace';
Constants.DEAD_CARD_REQ_TYPE = 'deadcard';
Constants.JOIN_RES_TYPE = 'join';
Constants.SELECT_CARD_RES_TYPE = 'selectcard';
Constants.SELECT_SPACE_RES_TYPE = 'selectspace';
Constants.DEAD_CARD_RES_TYPE = 'deadcard';
Constants.ERROR_RES_TYPE = 'error';
Constants.START_GAME_RES_TYPE = 'startgame';


/***/ }),

/***/ "lXt9":
/*!***************************************************!*\
  !*** ./src/app/components/card/card.component.ts ***!
  \***************************************************/
/*! exports provided: CardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CardComponent", function() { return CardComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_util_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../services/util.service */ "2Rin");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "ofXK");




function CardComponent_img_1_Template(rf, ctx) { if (rf & 1) {
    const _r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "img", 2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardComponent_img_1_Template_img_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r3); const card_r1 = ctx.$implicit; const ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r2.cardSelected.emit(card_r1); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const card_r1 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("src", "/assets/cards_png/" + card_r1.cardSuitNum + ".png", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵsanitizeUrl"]);
} }
class CardComponent {
    constructor(utilService) {
        this.utilService = utilService;
        this.cardSelected = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
    }
    ngOnInit() {
    }
}
CardComponent.ɵfac = function CardComponent_Factory(t) { return new (t || CardComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_util_service__WEBPACK_IMPORTED_MODULE_1__["UtilService"])); };
CardComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: CardComponent, selectors: [["app-card"]], inputs: { cards: "cards" }, outputs: { cardSelected: "cardSelected" }, decls: 2, vars: 1, consts: [[2, "display", "flex", "flex-direction", "row"], ["class", "card", "height", "231", "width", "150", 3, "src", "click", 4, "ngFor", "ngForOf"], ["height", "231", "width", "150", 1, "card", 3, "src", "click"]], template: function CardComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, CardComponent_img_1_Template, 1, 1, "img", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx.cards);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgForOf"]], styles: [".card[_ngcontent-%COMP%] {\n  height: 231px;\n  width: 150px;\n  margin: 20px 10px 0;\n  cursor: pointer;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uXFwuLlxcLi5cXC4uXFxjYXJkLmNvbXBvbmVudC5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsYUFBQTtFQUNBLFlBQUE7RUFDQSxtQkFBQTtFQUNBLGVBQUE7QUFDRiIsImZpbGUiOiJjYXJkLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmNhcmQge1xyXG4gIGhlaWdodDogMjMxcHg7XHJcbiAgd2lkdGg6IDE1MHB4O1xyXG4gIG1hcmdpbjogMjBweCAxMHB4IDA7XHJcbiAgY3Vyc29yOiBwb2ludGVyO1xyXG59XHJcbiJdfQ== */"] });


/***/ }),

/***/ "qtDB":
/*!*****************************************************!*\
  !*** ./src/app/components/board/board.component.ts ***!
  \*****************************************************/
/*! exports provided: BoardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BoardComponent", function() { return BoardComponent; });
/* harmony import */ var _constants__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../constants */ "l207");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_util_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/util.service */ "2Rin");
/* harmony import */ var _services_api_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/api.service */ "H+bZ");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "ofXK");





function BoardComponent_div_1_div_1_img_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](0, "img", 7);
} if (rf & 2) {
    const card_r3 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]().$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("src", card_r3.occupancy === 1 ? "/assets/cards_png/markerchips/red_marker_chip_faceup.png" : "/assets/cards_png/markerchips/blue_marker_chip_faceup.png", _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsanitizeUrl"]);
} }
function BoardComponent_div_1_div_1_Template(rf, ctx) { if (rf & 1) {
    const _r7 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 4);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵlistener"]("click", function BoardComponent_div_1_div_1_Template_div_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵrestoreView"](_r7); const card_r3 = ctx.$implicit; const ctx_r6 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"](2); return ctx_r6.selectSpace(card_r3.xLocation, card_r3.yLocation, ctx_r6.selectedCard.cardSuitNum); });
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](1, "img", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](2, BoardComponent_div_1_div_1_img_2_Template, 1, 1, "img", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const card_r3 = ctx.$implicit;
    const ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵclassProp"]("bordered", ctx_r2.selectable(card_r3));
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("src", "/assets/cards_png/" + card_r3.cardSuitNum + ".png", _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsanitizeUrl"]);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngIf", card_r3.occupancy);
} }
function BoardComponent_div_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 2);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](1, BoardComponent_div_1_div_1_Template, 3, 4, "div", 3);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
} if (rf & 2) {
    const row_r1 = ctx.$implicit;
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngForOf", ctx_r0.reverse(row_r1));
} }
class BoardComponent {
    constructor(utilService, apiService) {
        this.utilService = utilService;
        this.apiService = apiService;
    }
    ngOnInit() {
    }
    selectSpace(x, y, cardSuitNum) {
        if (!this.utilService.winner) {
            const found = this.spaces.find(val => val.xLocation === x && val.yLocation === y);
            if (found) {
                this.apiService.sendObject(_constants__WEBPACK_IMPORTED_MODULE_0__["Constants"].SELECT_SPACE_REQ_TYPE, {
                    x,
                    y,
                    card: cardSuitNum,
                    player: this.utilService.player
                });
            }
        }
    }
    selectable(space) {
        return !!this.spaces.find(val => val.xLocation === space.xLocation && val.yLocation === space.yLocation);
    }
    reverse(arr) {
        return [...arr].reverse();
    }
}
BoardComponent.ɵfac = function BoardComponent_Factory(t) { return new (t || BoardComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_util_service__WEBPACK_IMPORTED_MODULE_2__["UtilService"]), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdirectiveInject"](_services_api_service__WEBPACK_IMPORTED_MODULE_3__["ApiService"])); };
BoardComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: BoardComponent, selectors: [["app-board"]], inputs: { board: "board", spaces: "spaces", selectedCard: "selectedCard" }, decls: 2, vars: 1, consts: [[2, "display", "flex", "flex-direction", "column", "margin-left", "20px"], ["style", "display: flex; flex-direction: row", 4, "ngFor", "ngForOf"], [2, "display", "flex", "flex-direction", "row"], ["class", "card-container", 3, "click", 4, "ngFor", "ngForOf"], [1, "card-container", 3, "click"], ["height", "77", "width", "50", 1, "card", 3, "src"], ["class", "chip", 3, "src", 4, "ngIf"], [1, "chip", 3, "src"]], template: function BoardComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵtemplate"](1, BoardComponent_div_1_Template, 2, 1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵproperty"]("ngForOf", ctx.reverse(ctx.board.board));
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_4__["NgForOf"], _angular_common__WEBPACK_IMPORTED_MODULE_4__["NgIf"]], styles: [".card-container[_ngcontent-%COMP%] {\n  width: 77px;\n  height: 50px;\n  margin: 2px 5px;\n  position: relative;\n}\n.card-container[_ngcontent-%COMP%]   .chip[_ngcontent-%COMP%] {\n  position: absolute;\n  top: 23px;\n  left: 8px;\n  height: 30px;\n  width: 30px;\n  border-radius: 999px;\n}\n.card-container[_ngcontent-%COMP%]   .card[_ngcontent-%COMP%] {\n  transform: rotate(90deg);\n}\n.bordered[_ngcontent-%COMP%] {\n  border: 1px solid red;\n  border-radius: 3px;\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uXFwuLlxcLi5cXC4uXFxib2FyZC5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLFdBQUE7RUFDQSxZQUFBO0VBQ0EsZUFBQTtFQUNBLGtCQUFBO0FBQ0Y7QUFDRTtFQUNFLGtCQUFBO0VBQ0EsU0FBQTtFQUNBLFNBQUE7RUFDQSxZQUFBO0VBQ0EsV0FBQTtFQUNBLG9CQUFBO0FBQ0o7QUFFRTtFQUNFLHdCQUFBO0FBQUo7QUFJQTtFQUNFLHFCQUFBO0VBQ0Esa0JBQUE7QUFERiIsImZpbGUiOiJib2FyZC5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5jYXJkLWNvbnRhaW5lciB7XHJcbiAgd2lkdGg6IDc3cHg7XHJcbiAgaGVpZ2h0OiA1MHB4O1xyXG4gIG1hcmdpbjogMnB4IDVweDtcclxuICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcblxyXG4gIC5jaGlwIHtcclxuICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgIHRvcDogMjNweDtcclxuICAgIGxlZnQ6IDhweDtcclxuICAgIGhlaWdodDogMzBweDtcclxuICAgIHdpZHRoOiAzMHB4O1xyXG4gICAgYm9yZGVyLXJhZGl1czogOTk5cHg7XHJcbiAgfVxyXG5cclxuICAuY2FyZCB7XHJcbiAgICB0cmFuc2Zvcm06IHJvdGF0ZSg5MGRlZyk7XHJcbiAgfVxyXG59XHJcblxyXG4uYm9yZGVyZWQge1xyXG4gIGJvcmRlcjogMXB4IHNvbGlkIHJlZDtcclxuICBib3JkZXItcmFkaXVzOiAzcHg7XHJcbn1cclxuIl19 */"] });


/***/ }),

/***/ "vY5A":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");



const routes = [];
class AppRoutingModule {
}
AppRoutingModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineNgModule"]({ type: AppRoutingModule });
AppRoutingModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjector"]({ factory: function AppRoutingModule_Factory(t) { return new (t || AppRoutingModule)(); }, imports: [[_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"].forRoot(routes)], _angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsetNgModuleScope"](AppRoutingModule, { imports: [_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]], exports: [_angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"]] }); })();


/***/ }),

/***/ "zUnb":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "jhN1");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "ZAI4");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "AytR");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["enableProdMode"])();
}
_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["platformBrowser"]().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(err => console.error(err));


/***/ }),

/***/ "zn8P":
/*!******************************************************!*\
  !*** ./$$_lazy_route_resource lazy namespace object ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "zn8P";

/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map