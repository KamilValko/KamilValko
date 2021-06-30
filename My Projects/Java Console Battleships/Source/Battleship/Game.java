package Battleships;

import Battleships.Game.FieldState;
import Battleships.Game.PlayerType;
import Battleships.Game.ShipType;

public class Game 
{
    //Enums
    public enum ShipType 
    {
        NONE,
        A, //Aircraft Carrier (Count = 1, Length = 5)
        B, //Battleship (Count = 1, Length = 4)
        C, //Cruiser (Count = 1, Length = 3)
        D, //Destroyer (Count = 2, Length = 2)
        S  //Submarine (Count = 2, Length = 1)
    }
    public enum FieldState 
    {
        NONE,
        HIT,
        MISS
    }
    public enum PlayerType
    {
        PLAYER,
        AI1 //Difficulty - very easy (random shots)
    }

    //GameValues
    Player[] players = new Player[2];

    public Game(String p1Name, PlayerType p1Type, String p2Name, PlayerType p2Type)
    {
        players[0] = new Player(p1Name, p1Type);
        players[1] = new Player(p2Name, p2Type);
    }
}

class Player
{
    private String playerName;
    //10 rows + 10 cols = 100 fields
    private Field[] fields = new Field[100];
    private PlayerType playerType;

    public Player(String playerName, PlayerType playerType)
    {
        this.playerName = playerName;
        this.playerType =  playerType;
        for(int i = 0; i < fields.length; i++)
        {
            fields[i] = new Field(ShipType.NONE);
        }
    }

    public Field[] GetFields()
    {
        return fields;
    }
}

class Field 
{
    ShipType shipType;
    FieldState fieldState;

    public Field(ShipType shipType)
    {
        this.shipType = shipType;
        this.fieldState = FieldState.NONE;
    }

    
}
