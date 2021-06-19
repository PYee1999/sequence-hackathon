export class Constants {
  public static readonly PLAYER_RED = 1;
  public static readonly PLAYER_BLUE = -1;

  public static readonly SPADES_SUIT = 1;
  public static readonly CLUBS_SUIT = 2;
  public static readonly DIAMONDS_SUIT = 3;
  public static readonly HEARTS_SUIT = 4;

  public static readonly JOIN_REQ_TYPE = 'join';
  public static readonly SELECT_CARD_REQ_TYPE = 'selectcard';
  public static readonly SELECT_SPACE_REQ_TYPE = 'selectspace';
  public static readonly DEAD_CARD_REQ_TYPE = 'deadcard';

  public static readonly JOIN_RES_TYPE = 'join';
  public static readonly SELECT_CARD_RES_TYPE = 'selectcard';
  public static readonly SELECT_SPACE_RES_TYPE = 'selectspace';
  public static readonly DEAD_CARD_RES_TYPE = 'deadcard';
  public static readonly ERROR_RES_TYPE = 'error';
  public static readonly START_GAME_RES_TYPE = 'startgame';
}
