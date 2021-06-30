package Battleships;

import java.util.Scanner;
import Battleships.Game;
import Battleships.Game.FieldState;
import Battleships.Game.PlayerType;

public class ConsoleGame {
    static Scanner sc = new Scanner(System.in);

    public static void Run()
    {
        boolean play = true;
        while(play)
        {
            PlayerType p1Type, p2Type;
            String p1Name, p2Name;

            //Create Player1
            p1Type = PlayerTypeInput(1);
            if(p1Type == PlayerType.PLAYER)
                p1Name = PlayerNameInput(1);
            else
                p1Name = "AI";

            //Create Player2
            p2Type = PlayerTypeInput(2);
            if(p2Type == PlayerType.PLAYER)
                p2Name = PlayerNameInput(2);
            else
                p2Name = "AI";


            //Preparation phase
            Game game = new Game(p1Name, p1Type, p2Name, p2Type);
            System.out.println();
            System.out.println("Preparation phase");
            ShowGame(0, game, p1Name, p2Name);
            



            //Game game = new Game();
            


            //Ask if they want to play until they type in a valid answer (Y/N or y/n)
            boolean validAnswer = false;
            while(!validAnswer)
            {
                System.out.println("Do you want to play another game? (Type Y/N)");
                char answer = sc.next().toLowerCase().toCharArray()[0];
                switch(answer)
                {
                    case 'y':
                        validAnswer = true;
                        break;
                    case 'n':
                        validAnswer = true;
                        play = false;
                        System.out.println("Closing the game. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid answer");
                        break;
                }
            }
        }
        sc.close();
    }

    static PlayerType PlayerTypeInput(int playerNumber)
    {
        while(true)
        {
            System.out.println("Choose player type for Player " + playerNumber + " (Type P for Player or A1 for Very Easy AI[WIP])");
            char answer = sc.next().toLowerCase().toCharArray()[0];
            switch(answer)
            {
                case 'p':
                    return PlayerType.PLAYER;
                default:
                    break;
            }
        }
    }

    static String PlayerNameInput(int playerNumber)
    {
        while(true)
        {
            System.out.println("Type name for Player " + playerNumber + " (max 10 characters)");
            String input = sc.next();
            if(input.toCharArray().length <= 10 && input.toCharArray().length > 0)
            {
                return input;
            }
            if(input.toCharArray().length > 10)
                System.out.println("This name is too long!");
        }
        
    }

    static void ShowGame(int modeID, Game game, String p1Name, String p2Name)
    {
        //Mode IDs (0 - Spectator Mode, 1 - Player 1 Mode, 2 - Player 2 Mode)
        System.out.println();
        
        //Player Names
        System.out.print(p1Name);
        for(int i = 0; i < 10-p1Name.toCharArray().length+17; i++)
        {
            System.out.print(" ");
        }
        System.out.print(p2Name);
        System.out.println("\n");


        for(int i = 0; i < 10; i++)
        {
            char row = 'A';
            switch(i)
            {
                case 0:
                    row = 'A';
                    break;
                case 1:
                    row = 'B';
                    break;
                case 2:
                    row = 'C';
                    break;
                case 3:
                    row = 'D';
                    break;
                case 4:
                    row = 'E';
                    break;
                case 5:
                    row = 'F';
                    break;
                case 6:
                    row = 'G';
                    break;
                case 7:
                    row = 'H';
                    break;
                case 8:
                    row = 'I';
                    break;
                case 9:
                    row = 'J';
                    break;
                default:
                    break;
                
            }
            //Player 1
            System.out.print(row + "  ");
            if(modeID == 0 || modeID == 1)
            {
                PrintRowVisible(0, i, game);
            }
            else
            {
                PrintRowInvisible(0, i, game);
            }
            //Player 2
            System.out.print("    ");
            System.out.print(row + "  ");
            if(modeID == 0 || modeID == 1)
            {
                PrintRowVisible(1, i, game);
            }
            else
            {
                PrintRowInvisible(1, i, game);
            }
            System.out.print("\n");
        }

        //Column numbers
        System.out.println("   1 2 3 4 5 6 7 8 9 10       1 2 3 4 5 6 7 8 9 10");

        System.out.println();
    }

    static void PrintRowVisible(int player, int row, Game game)
    {
        for(int i = row * 10; i < row * 10 + 10; i++)
        {
            if(game.players[player].GetFields()[i].fieldState == FieldState.NONE)
            {
                //The field is not hit
                switch(game.players[player].GetFields()[i].shipType)
                {
                    case NONE:
                        System.out.print("- ");
                        break;
                    case A:
                        System.out.print("A ");
                        break;
                    case B:
                        System.out.print("B ");
                        break;
                    case C:
                        System.out.print("C ");
                        break;
                    case D:
                        System.out.print("D ");
                        break;
                    case S:
                        System.out.print("S ");
                        break;
                }
            }
            else if(game.players[player].GetFields()[i].fieldState == FieldState.MISS)
                System.out.print("O ");
            else if(game.players[player].GetFields()[i].fieldState == FieldState.HIT)
                System.out.print("X ");

        }
    }

    static void PrintRowInvisible(int player, int row, Game game)
    {
        for(int i = row * 10; i < row * 10 + 10; i++)
        {
            if(game.players[player].GetFields()[i].fieldState == FieldState.NONE)
                System.out.print("- ");
            else if(game.players[player].GetFields()[i].fieldState == FieldState.MISS)
                System.out.print("O ");
            else if(game.players[player].GetFields()[i].fieldState == FieldState.HIT)
                System.out.print("X ");
        }
    }
}
