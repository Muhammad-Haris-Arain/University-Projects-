package com.classes;


  import java.io.*; // used to allow user to input data
public class Main {


    // start class



        //Main Method
        public static void main (String [] args) throws IOException
        {// start main method

            //Welcoming Screen

            BufferedReader myInput2 = new BufferedReader (new InputStreamReader (System.in));
            // Print the welcome screen and instructions
            System.out.println ("\t\t\t\t\t\tWelcome To Snakes And Ladders\n\n");
            System.out.println ("\t\t\t\t\t\t\tInstructions:");
            System.out.println ("\t\t\t This program will simulate a regular snakes and ladders game, where you will be");
            System.out.println ("\t\t\t facing the computer. You and the computer start at square 1, and the first one to");
            System.out.println ("\t\t\t square 100 wins, however, there will be preset squares which will be the snakes or ladders.");
            System.out.println ("\t\t\t Once you land on top of a snake you go down a few squares, and move up if you land");
            System.out.println ("\t\t\t\t\t\t on the bottom of a ladder. Good Luck and Have FUN!!!");


            int counter= 100,iteration=-1; // sets the counter and iteration vaiables to be used in making the board
            System.out.println ("-----------------------------------------------------Game Board-----------------------------------------------------------------------------");

        /*
        This while loop makes the board for the player to visualize what the
        game looks like, it uses a counter to increment or decrement by 1.
        It will also subtract by 9 or 10 when necessary to create a board
        exactly like the one given.
        */
            while (counter > 0){// start while
                if (counter%10 == 0 && counter != 100){// checks if the counter is at a 90, or 80...
                    if(iteration==-1)
                    {
                        // subtract 9 from the counter
                        // and sets it to start adding by one
                        counter-=9;
                        iteration=1;
                    }
                    else
                    {
                        System.out.print(counter+"\t");
                        counter-=10;
                        iteration=-1; // set the counter to start subtract by ones
                    }
                    if(counter!=0)
                        System.out.print("\n" + counter + "\t"); // just prints out the counter with a line breck
                }
                else
                    System.out.print(counter + "\t"); // just prints out the counter
                counter+=iteration; // sets counter to add by iteration
            }// end while
            System.out.println();
            System.out.println ("----------------------------------------------------------------------------------------------------------------------------------");




            String sGame = "y"; // decare variable used to ask user if he wants to play

            System.out.print ("Do you want to play? Y or N     >  "); // ask user if we wants to play the game
            sGame = myInput2.readLine (); // reads the user's input into the variable sGame
            System.out.print ("\n\n\n\n\n\n");
            // While the user says yes, go to startGame method
            // startGame is fuction type method, which start the game
            while (sGame.equals ("y") || sGame.equals ("Y"))
            {
                sGame = startGame(sGame); // give startGame a variable to work with
            }
            System.out.println ("\n\n\n\n\t\t\t\t\t\tSEE YA!!");


        } //end main method





        //-------------------------------------------------------------------startGame Method------------------------------------------------------------------------------

        /*
        startGame method:

        This method is responsible for organizing the game, asking the user to continue playing,
        and setting the important varibales. It will also return a vaule to the main method, which
        will reset the game so the user can play again.
        */
        public static String startGame (String start) throws IOException // Recieves data from the main method
        {// start startGame method

            BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));

            // sets important variables for the game
            // NOTE: These variables will change as the game progresses

            int userPosition = 1; // sets the default loaction for user's piece to 1
            int compPosition = 1; // sets the default loaction for computer's piece to 1
            int diceRoll = 0; // creates the first die
            int diceRoll2 = 0; // creates the second die
            int userRoll = 1; // declares what the user rolled
            int compRoll = 1; // declares what the computer rolled
            String playAgain = "y"; // sets the play again variable

            // declare all the snakes and ladders in a array
            int snakesLaddersArray [] = new int [6]; // create a 6 element array
            // store the snakes and ladders location in the array
            snakesLaddersArray [0] = 54;
            snakesLaddersArray [1] = 90;
            snakesLaddersArray [2] = 99;
            snakesLaddersArray [3] = 9;
            snakesLaddersArray [4] = 40;
            snakesLaddersArray [5] = 67;


            while (playAgain.equals ("y") || playAgain.equals ("Y")) // loops while the playAgaim vaiable equals "y" or "Y".
            {// start playAgain While


                // gets the dice roll for user and computer
                userRoll =  getDice(diceRoll, diceRoll2); // sends data to a function type method called getDice
                compRoll =  getDice(diceRoll, diceRoll2); // does the same for the computer
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                System.out.println ("\t\t\t\t\t------------------------------------------");
                System.out.println ("\t\t\t\t\t|\tYou Rolled a " + userRoll + "\t\t|"); // print the roll the user got
                System.out.println ("\t\t\t\t\t|\tThe Computer Rolled a " + compRoll + "\t|"); // print the roll the computer got
                System.out.println ("\t\t\t\t\t------------------------------------------");

                // hold the user's last position for switching back if current position was greater than 100
                userPosition = userPosition + userRoll;

                // hold the computer's last position for switching back if current position was greater than 100
                compPosition = compPosition + compRoll;



                // check to see if user landed on top of a snake or at the bottom of a ladder
                // give getP parameters to work with, and recieve a final value which can be printed out
                userPosition = getP(userPosition, userRoll, snakesLaddersArray);
                // The same goes for compPosition, however compgetP gets an additional
                // parameter (userPosition) to check if user has already won
                compPosition = compgetP(compPosition, compRoll, snakesLaddersArray, userPosition);

                System.out.println("\t\t\t*************************************************************************");
                System.out.println ("\t\t\t*\t\tYou are currently on square " + userPosition + "\t\t\t*"); // print out the user's current location
                System.out.println ("\t\t\t*\t\tThe Computer is currently on square " + compPosition + "\t\t*"); // print out the computer's current location
                System.out.println("\t\t\t*************************************************************************");

                // resets the position values, if the user or the computer won
                // so that the user could play the entire game again if he/she wanted to
                if (userPosition == 100 || compPosition == 100)
                {
                    userPosition = 1;
                    compPosition = 1;
                    // asks the user if we wants to play again
                    System.out.print ("Do you want to play? Y or N     >  ");
                    playAgain = myInput.readLine ();
                    System.out.print ("\n\n\n\n\n\n\n\n\n\n\n\n");
                }
                else
                {
                    // asks the user if we wants to continue playing
                    System.out.print ("Do you want to play? Y or N     >  ");
                    playAgain = myInput.readLine ();

                }


            }// end playAgain While

            return playAgain; // returns prameter "playAgain" to main: if the user wants to play the game again
        }// end startGame method






        //-------------------------------------------------------------------geDice Method------------------------------------------------------------------------------

        /*
        getDice method:

        This method generates two random numbers bewteen 1 and 6, then
        adds them to get a final roll. Next it returns the value to be
        diplayed on the screen.
        */
        public static int getDice (int diceRoll, int diceRoll2)
        {// start getDies class
            diceRoll = (int)(Math.random()*6 )+1 ; //creates dice roll number 1
            diceRoll2 = (int)(Math.random()*6 )+1 ; //creates dice roll number 2
            int move = diceRoll + diceRoll2; // adds the two dice rolls to get a final move
            return move; // return parameter move: this will give the final dice roll back to startGame
        }// end getDice class




        //-------------------------------------------------------------------getP Method------------------------------------------------------------------------------
    /*
    getP method:

    This method is responsible for checking if the USER is on
    top of a snake, or at the bottom of a ladder, and then
    adjusting the user's position to mathch the snake or
    ladders value.
    */
        public static int getP (int userPosition, int userRoll, int snakesLaddersArray []) throws IOException // recieves two parameter from startGame
        {// start getP


            if(userPosition == snakesLaddersArray[0]) //if position equals snake 1
            {
                userPosition = 19; // set new position
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake, GO DOWN!!!~~~~~~~~~~~~~");
            }
            else if (userPosition == snakesLaddersArray[1]) //if position equals snake 2
            {
                userPosition = 48; // set new position
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake, GO DOWN!!!~~~~~~~~~~~~~");

            }
            else if (userPosition == snakesLaddersArray[2]) //if position equals snake 3
            {
                userPosition = 77; // set new position
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake, GO DOWN!!!~~~~~~~~~~~~~");
            }
            else if (userPosition == snakesLaddersArray[3]) //if position equals ladder 1
            {
                userPosition = 34; // set new position
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!! GO UP!!!~~~~~~~~~~~~~");

            }
            else if (userPosition == snakesLaddersArray[4]) //if position equals ladder 2
            {
                userPosition = 64; // set new position
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!! GO UP!!!~~~~~~~~~~~~~");

            }
            else if (userPosition == snakesLaddersArray[5]) //if position equals ladder 3
            {


                userPosition = 86; // set new position
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!! GO UP!!!~~~~~~~~~~~~~");
            }

            if (userPosition < 0 || userPosition > 112) // This is ab ERROR TRAP to catch any unwanted system errors that may occur by chance
            {
                System.out.println ("An error has occured please reset the game!!!!!!");
            }

            else if (userPosition > 100) // checks if user's location if greater then a 100
            {
                userPosition = userPosition - userRoll; // subtract userRoll from the userposition to get back old position
                System.out.println ("OHHH You cant jump, you must land on a 100"); // print out the users current location

            }
            else if (userPosition == 100)
            {
                System.out.println ("YOU WON, GOOD JOB!!!"); // print out that the user won

            }



            return userPosition; // return the final position to starGame: this position had gone through all checks and test and can be displayed on screen
        }// end getP






        //-------------------------------------------------------------------compgetP Method------------------------------------------------------------------------------

    /*
    compgetP method:

    This method is responsible for checking if the COMPUTER is on
    top of a snake, or at the bottom of a ladder, and then
    adjusting the compuer's position to mathch the snakes or
    ladders value.
    */

        public static int compgetP (int compPosition, int compRoll, int snakesLaddersArray [], int userPosition) throws IOException
        {// start compgetP

            // NOTE: this method is similar to getP, so the comments are the same for both!!
            // Look at getP's commments if you need help

            if(compPosition == snakesLaddersArray[0])
            {
                compPosition = 19;
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, HE WENT GO DOWN!!!~~~~~~~~~~~~~");


            }
            else if (compPosition == snakesLaddersArray[1])
            {
                compPosition = 48;
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, HE WENT GO DOWN!!!~~~~~~~~~~~~~");

            }
            else if (compPosition == snakesLaddersArray[2])
            {
                compPosition = 77;
                System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, HE WENT GO DOWN!!!~~~~~~~~~~~~~");
            }
            else if (compPosition == snakesLaddersArray[3])
            {
                compPosition = 34;
                System.out.println ("Computer Got TO A Ladder, HE WENT UP!!!");
            }
            else if (compPosition == snakesLaddersArray[4])
            {
                compPosition = 64;
                System.out.println ("Computer Got TO A Ladder, HE WENT UP!!!");

            }
            else if (compPosition == snakesLaddersArray[5])
            {
                compPosition = 86;
                System.out.println ("Computer Got TO A Ladder, HE WENT UP!!!");
            }


            if (compPosition < 0 || compPosition > 112) //  ERROR TRAP to catch any unwanted system errors that may occur by chance
            {
                System.out.println ("An error has occured for the computer, please reset the game!!!!!!");
            }

            else if (compPosition > 100) // checks if computers's location if greater then a 100
            {
                compPosition = compPosition - compRoll; // get old position
                System.out.println ("THE COMPUTER CAN'T JUMP!!! He must land on a 100"); // give message that the computer cant jump

            }
            else if (compPosition == 100 && userPosition != 100)
            {
                System.out.println ("THE COMPUTER WON, YOU FAILED!!!"); // print out that the computer won

            }

            return compPosition; // return the final position to starGame: this position had gone through all checks and test and can be displayed on screen
        } // end compgetPy
    }//end cla