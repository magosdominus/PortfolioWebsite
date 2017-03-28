/**Game.cs
*
* Description: This class represents a game of durak, with players, and a dealer
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (04/15/2016)
*/

/**Attribution
* Durak Icon Image retrieved from https://openclipart.org/tags/hat
*/
/**Attribution
* Card sounds retrieved from https://freesound.org/people/empraetorius/sounds/201253/ and https://freesound.org/people/f4ngy/sounds/240776/
*/
/**Attribution
* Card images retrieved from http://opengameart.org/content/boardgame-pack
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Media; //implemented for using sound files
using System.Windows.Forms; //for passing game form to constructor
using System.Drawing; //implemented for modifying controls location with the point class
using System.IO; //implemented for reading and writing to text files
using System.Diagnostics; //implemented for using the debug window

namespace CardGameLibrary
{
    public class Game
    {
        #region Properties

        /// <summary>
        /// List of player objects.
        /// </summary>
        public List<Player> myPlayers = new List<Player>();

        /// <summary>
        /// Discard pile, cards removed from the hand or deck will be moved here.
        /// </summary>
        public List<CardBox> myDiscardPile = new List<CardBox>();

        /// <summary>
        /// Deck of playing cards.
        /// </summary>
        public StandardDeck myDeck = new StandardDeck();

        /// <summary>
        /// Card dealer/shoe for the game/
        /// </summary>
        public CardDealer myDealer = new CardDealer();

        /// <summary>
        /// A suit variable for representing the trump this game
        /// </summary>
        public Suit trump = new Suit();

        /// <summary>
        /// A CardBox for storing and displaying the trump card
        /// </summary>
        public CardBox trumpCard = new CardBox();

        /// <summary>
        /// list of cardboxes for displaying cards in a bout
        /// </summary>
        public List<CardBox> firstBout = new List<CardBox>();

        /// <summary>
        /// Will hold the winning player once the game is complete.
        /// </summary>
        protected Player winningPlayer = null;
        public Player WinningPlayer
        {
            get { return winningPlayer; }
            set { winningPlayer = value; }
        }

        /// <summary>
        /// Bool value representing if the game has been completed or not.
        /// </summary>
        protected bool gameWon = false;
        public bool GameWon
        {
            get { return gameWon; }
            set { gameWon = value; }
        }

        /// <summary>
        /// Holds the index of the player that will have the next attack turn.
        /// </summary>
        protected int nextTurn = 0;
        public int NextTurn
        {
            get { return nextTurn; }
            set { nextTurn = value; }
        }

        /// <summary>
        /// Holds the form the game will be displayed on
        /// </summary>
        protected Form myForm;

        /// <summary>
        /// Pass button.
        /// </summary>
        protected Button passButton;

        /// <summary>
        /// Prompt label.
        /// </summary>
        protected Label promptLabel;

        /// <summary>
        /// Turn counter.
        /// </summary>
        protected int turnCounter = 0;

        /// <summary>
        /// Tracks the phases within each turn;
        /// </summary>
        protected bool phaseOver = false;

        /// <summary>
        /// Holds the winning player of a turn.
        /// </summary>
        protected Player turnWon;

        /// <summary>
        /// turnOver bool for storing wheter a turn is over
        /// </summary>
        protected bool turnOver = false;

        #endregion

        #region Constructors

        /// <summary>
        /// Game constructor that can take a deck size and a windows form
        /// </summary>
        /// <param name="deckSize">Default = 36</param>
        /// <param name="form">Default = null</param>
        /// <param name="playerName">Default = "Player 1"</param>
        public Game(int deckSize = 36, Form form = null, string playerName = "Player 1")
        {
            myForm = form; //set myForm to entered form

            myDeck = new StandardDeck(deckSize); // Create a deck based on the deck size arugment and assign to myDeck.

            myDealer = new CardDealer(myDeck, form); // Create a new dealer with myDeck and assign to myDealer.

            myPlayers.Add(new Human(newName: playerName, newForm: form)); //add new real player to players list

            myPlayers.Add(new Computer("Computer 1", newForm: form)); //add new computer player to players list

            foreach (Player player in myPlayers) //deal out 6 cards for each player
            {
                if (form != null) //check if form is not null
                {
                    myDealer.DealHand(player, 6, form); //deal out 6 card for player passing along entered form
                }
                else //form is null
                {
                    myDealer.DealHand(player, 6); //deal out 6 card for player passing along entered form
                }
            }

            trumpCard = new CardBox(myDealer.SelectTrumpCard()); //select trump card
            trump = trumpCard.Suit; //set trump
            PlayingCard.UseTrumps = true;
            PlayingCard.IsAceHigh = true;
            PlayingCard.Trump = trumpCard.Suit;

            if (form != null) //check if form is null
            {
                trumpCard.MyOrientation = Orientation.Horizontal; //chang trumpCard orientation to horizontal
                trumpCard.Location = new Point((form.Width - (trumpCard.Width + 40)), ((form.Height / 2) - (trumpCard.Height / 2))); //set trumpCard location

                form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    form.Controls.Add(trumpCard); //add trumpCard control to form
                });

                Label lblPlayerName = new Label(); //create a label for showing player name
                lblPlayerName.Location = new Point(70,(form.Height - 80)); //set label location
                lblPlayerName.Text = myPlayers[0].MyName; //set text of label to players name

                form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    form.Controls.Add(lblPlayerName); //add label to form
                });

                Label lblComputerName = new Label(); //create a label for showing player name
                lblComputerName.Location = new Point(70, 20); //set label location
                lblComputerName.Text = myPlayers[1].MyName; //set text of label to players name

                form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    form.Controls.Add(lblComputerName); //add label to form
                });

                Button btnPass = new Button();
                btnPass.Size = new Size(100, 40);
                btnPass.Location = new Point(900, 500);
                btnPass.Enabled = false;
                btnPass.Text = "Pass";
                passButton = btnPass;
                form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    form.Controls.Add(passButton);
                });
                passButton.Click += new EventHandler(Pass_Click);

                Label lblGamePrompt = new Label();
                lblGamePrompt.Width = 200;
                lblGamePrompt.Text = "Game starting...";
                lblGamePrompt.Location = new Point(50, 300);
                promptLabel = lblGamePrompt;
                form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    form.Controls.Add(promptLabel);
                });                
            }

            // Disable all cards.
            // Add click event handler for each card.
            foreach (Player player in myPlayers)
            {
                foreach (CardBox cardBox in player.myCardBoxes)
                {
                    cardBox.Click += new EventHandler(Card_Click);
                    form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        cardBox.Enabled = false;
                    });
                }
            }
        }

        #endregion

        #region Methods

        /// <summary>
        /// Adds a card to a bout.
        /// </summary>
        /// <param name="cbInput"></param>
        public void AddCardToBout(CardBox cbInput)
        {
            firstBout.Add(cbInput); //add cardbox to bout
            DisplayBout(myForm); //display the new bout
        }

        /// <summary>
        /// When the Pass turn button is pressed, call this event handler.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public void Pass_Click(object sender, EventArgs e)
        {
            phaseOver = true; //set phaseOver to true
            turnOver = true; //set turnOver to true
            turnWon = myPlayers[1]; //set the winning player of the turn

            if (nextTurn == 0) //check if it was the human players turn
            {
                myDiscardPile.AddRange(firstBout); //add the bout to the discard pile
                firstBout.Clear(); //clear the cards from the bout
                DisplayBout(myForm); //display the bout
                DisplayDiscardPile(myForm); //display the discard pile
            }
            if (nextTurn == 1) //check if it was the computer players turn
            {
                myPlayers[0].myCardBoxes.AddRange(firstBout); //add the bout cards to the human players hand
                myPlayers[0].DisplayHand(myForm); //display human players hand
                firstBout.Clear(); //clear the bout
                DisplayBout(myForm); //display the bout
            }

            nextTurn = 1; //set the next turn to 1 representing the computer is next
        }

        /// <summary>
        /// CardBox click event handler.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public void Card_Click(object sender, EventArgs e)
        {
            // Send CardBox that was clicked to a method.
            CardBox cbBox = (CardBox)sender;
            AddCardToBout(cbBox);
            myPlayers[0].Remove(cbBox.MyCard);
            SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.flip_sound); //create new soundplayer for the card flip sound
            audio.PlaySync(); //play the card flip sound on same thread haulting program
            phaseOver = true;
        }

        /// <summary>
        /// Virtual method for child classes. Checks for a win condition.
        /// </summary>
        protected bool HasWon()
        {
            bool hasWon = false;

            // For each player...
            foreach(Player player in myPlayers)
            {
                // If hand is empty.
                if (player.myCardBoxes.Count == 0)
                {
                    // Assign player to winningPlayer.
                    WinningPlayer = player;

                    hasWon = true;
                }
            }

            // Return if the game has been won or not.
            return hasWon;
        }

        /// <summary>
        /// Processes an attack turn for the player passed to it.
        /// </summary>
        /// <param name="player"></param>
        public void ProcessTurn(int playerIndex)
        {
            turnOver = false; //boolean for storing wheter a turn is over or not

            if (playerIndex == 1) //check if it's the computer players turn
            {
                while (turnOver == false)
                {
                    #region computer player attacks

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        promptLabel.Text = myPlayers[1].MyName + "'s attack phase."; //alter the prompt label's text
                    });

                    CardBox tempCard = myPlayers[1].Attack(trump, firstBout); //create a temp card and set it to the result of the computers attack

                    if (tempCard != null) //check if the computer had a card to play
                    {
                        AddCardToBout(tempCard); //add computer played card to bout
                        SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.flip_sound); //create new soundplayer for the card flip sound
                        audio.PlaySync(); //play the card flip sound on same thread haulting program
                    }
                    else //the computer didn't have a card to play
                    {
                        myDiscardPile.AddRange(firstBout); //add the bout to the discard pile
                        firstBout.Clear(); //clear the cards from the bout
                        DisplayBout(myForm); //display the bout
                        DisplayDiscardPile(myForm); //display the discard pile
                        turnOver = true; //set turn over to true
                        nextTurn = 0; //set the next turn to 0 representing the human
                        break; //break from loop
                    }

                    #endregion

                    #region human player defends

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        promptLabel.Text = myPlayers[0].MyName + "'s defense phase."; //alter the prompt label's text
                    });

                    foreach (CardBox cardBox in myPlayers[0].myCardBoxes) //loop through human players hand of cards
                    {
                        if (cardBox.MyCard.MySuit == firstBout[firstBout.Count - 1].MyCard.MySuit && cardBox.MyCard.MyRank > firstBout[firstBout.Count - 1].MyCard.MyRank) //check if the current card in hand is of same suit as the last played card in the bout and of higher rank
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Enabled = true; //enable the current human player card
                            });
                        }
                        else if (cardBox.MyCard.MySuit == trump && firstBout[firstBout.Count - 1].MyCard.MySuit != trump) //check if current card in hand is of same suit as trump suit and the last played card in the bout is not of trump suit
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Enabled = true; //enable the current human player card
                            });
                        }
                        
                    }

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        passButton.Enabled = true; //enable the pass button
                    });

                    while (phaseOver == false) //loop until the human player phase is over
                    {
                        //waiting for pass button to be clicked or a cardbox to be clicked
                    }

                    foreach (CardBox cardBox in myPlayers[0].myCardBoxes.ToList()) //loop through human players hand of cards
                    {
                        myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                        {
                            cardBox.Enabled = false; //disable the current human player card
                        });
                    }

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        passButton.Enabled = false; //disable the pass button
                    });

                    phaseOver = false; //set phaseOver to false for next phase

                    #endregion
                }               
            }
            else if (playerIndex == 0) //check if it's the human players turn
            {
                while (turnOver == false)
                {
                    #region human player attacks

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        promptLabel.Text = myPlayers[0].MyName + "'s attack phase."; //alter the prompt label's text
                    });

                    if (firstBout == null || firstBout.Count == 0) //check if no cards have been played in the bout yet
                    {
                        //enable all cards in human players hand
                        foreach (CardBox cardBox in myPlayers[0].myCardBoxes) //loop through human players hand of cards
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Enabled = true; //enable the current human player card
                            });
                        }
                    }
                    else //cards have already been played in the bout
                    {
                        foreach (CardBox boutCard in firstBout) //loop through bout of cards
                        {
                            foreach (CardBox handCard in myPlayers[0].myCardBoxes) //loop through hand of cards
                            {
                                if (handCard.MyCard.MyRank == boutCard.MyCard.MyRank) //check if the current card in hand is of same rank as current card in bout
                                {
                                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                                    {
                                        handCard.Enabled = true; //enable the current human player card
                                    });
                                }
                            }
                        }
                    }

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        passButton.Enabled = true; //enable the pass button
                    });

                    while (phaseOver == false) //loop until the human player phase is over
                    {
                        //waiting for pass button to be clicked or a cardbox to be clicked
                    }

                    foreach (CardBox cardBox in myPlayers[0].myCardBoxes.ToList()) //loop through human players hand of cards
                    {
                        myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                        {
                            cardBox.Enabled = false; //disable the current human player card
                        });
                    }

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        passButton.Enabled = false; //disable the pass button
                    });

                    phaseOver = false; //set phaseOver to false for next phase

                    if (turnOver == true) //check if turnOver is true
                    {
                        break; //break from loop
                    }

                    #endregion

                    #region computer player defends

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        promptLabel.Text = myPlayers[1].MyName + "'s defense phase."; //alter the prompt label's text
                    });

                    CardBox tempCard = myPlayers[1].Defend(trump, firstBout); //create a temp card and set it to the result of the computers attack

                    if (tempCard != null) //check if the computer had a card to play
                    {
                        AddCardToBout(tempCard); //add computer played card to bout
                        SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.flip_sound); //create new soundplayer for the card flip sound
                        audio.PlaySync(); //play the card flip sound on same thread haulting program
                    }
                    else //the computer didn't have a card to play
                    {
                        foreach (CardBox cardBox in firstBout) //loop through cards in bout
                        {
                            cardBox.FaceUp = false; //filp card face down
                        }
                        myPlayers[1].myCardBoxes.AddRange(firstBout); //add the bout cards to the computer players hand
                        myPlayers[1].DisplayHand(myForm); //display computer players hand
                        firstBout.Clear(); //clear the bout
                        DisplayBout(myForm); //display the bout
                        turnOver = true; //set turn over to true
                        break; //break from loop
                    }

                    #endregion
                }
            }
        }

        /// <summary>
        /// Processes the turns for the game.
        /// </summary>
        public void ProcessGame()
        {
            #region decide player to go first

            Rank lowestHumanTrump = myPlayers[0].GetLowestTrumpCard(trump); //Rank variable for storing lowest human trump card rank, setting it equal to the return of GetLowestTrumpCard
            Rank lowestComputerTrump = myPlayers[1].GetLowestTrumpCard(trump); //Rank variable for storing lowest computer trump card rank, setting it equal to the return of GetLowestTrumpCard

            if (lowestHumanTrump <= lowestComputerTrump) //check if the human player's lowest trump is less than or equal to the computer player's lowest trump
            {
                nextTurn = 0; //set nextTurn to 0 representing the human player going first
            }
            else //computer player has the lowest trump
            {
                nextTurn = 1; //set nextTurn to 1 representing the computer player going first
            }

            #endregion

            #region main game loop

            // While the game is not complete...
            while (GameWon == false)
            {
                ProcessTurn(nextTurn); //process the turn of the current player

                GameWon = HasWon(); //set GameWon to the return of HasWon()

                if (GameWon == false) //check if a player has won
                {
                    foreach (Player player in myPlayers) //loop through players
                    {
                        while (player.myCardBoxes.Count < 6) //fill players hand up to six cards
                        {
                            if (myDealer.CardsRemaining() != 0) //check if there is not zero cards remaining in the deck
                            {
                                myDealer.DealCard(player, myForm); //deal card to current player
                            }
                            else //there are no cards remaining in the deck
                            {
                                myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                                {
                                    myDealer.MyDeck.DeckImage.Hide(); //hide the image of the deck
                                });
                                if (player.GetType() == typeof(Computer)) //check if the current player is a computer
                                {
                                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                                    {
                                        trumpCard.FaceUp = false; //flip trump card face down
                                    });
                                }
                                myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                                {
                                    trumpCard.MyOrientation = Orientation.Vertical; //orient the trump card verticaly
                                });
                                player.myCardBoxes.Add(trumpCard); //add the trump card to the players hand
                                player.DisplayHand(myForm); //display the players hand
                                break; //break from the while loop
                            }
                        }
                    }

                    // Add click event handler for each card.
                    foreach (CardBox cardBox in myPlayers[0].myCardBoxes) //loop through human players hand
                    {
                        myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                        {
                            cardBox.Enabled = false; //disable cardBox
                            cardBox.Click -= new EventHandler(Card_Click); //remove any existing Card_Click event handler
                            cardBox.Click += new EventHandler(Card_Click); //add event to the cardbox
                        });
                    }
                }
                else //a player has won
                {
                    #region log the game and display winner

                    try //try writing a new game to the log file
                    {
                        string path = Path.Combine(Directory.GetCurrentDirectory(), "log.txt"); //string for holding path to log text file
                        StreamWriter sw = new StreamWriter(path, true); //create a StreamWriter to the log text file, pass true to not overwrite existing contents

                        //write date of game
                        //newSW.Write("Date: "); //write date heading for log file
                        DateTime dateTime = DateTime.Now; //create a new date and time and set it equal to now
                        sw.WriteLine(dateTime); //write date and time of game

                        //write trump card of game
                        //newSW.Write("Trump: "); //write trump heading for log file
                        //newSW.WriteLine(trumpCard.MyCard.ToShorthandString()); //write date heading for log file

                        //write players and their starting hand
                        //newSW.Write("Players: "); //write date heading for log file
                        foreach (Player player in myPlayers) //loop through players
                        {
                            sw.Write(player.MyName + ", "); //write the players to the log
                            //foreach (PlayingCard card in player.myHand) //loop through cards in players hand
                            //{
                            //    newSW.Write(card.ToShorthandString() + " "); //write card to the log
                            //}
                        }
                        sw.WriteLine(""); //write blank line to go to next line

                        if (WinningPlayer == myPlayers[0]) //check if the winning player is the human player
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                promptLabel.Text = myPlayers[0].MyName + " has won!"; //alter the prompt label's text
                            });

                            sw.WriteLine("WIN"); //write win to log
                        }
                        else if (WinningPlayer == myPlayers[1]) //check if the winning player is the computer player
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                promptLabel.Text = myPlayers[1].MyName + " has won!"; //alter the prompt label's text
                            });

                            sw.WriteLine("LOSE"); //write lose to log
                        }

                        sw.Flush(); //flush the write to the file

                        sw.Close(); //close the stream writer to the log file
                    }
                    catch (Exception e) //catch any throw exception
                    {
                        Debug.Write(e); //write exception to debug window
                    }

                    #endregion                  
                }
            }

            #endregion
        }

        /// <summary>
        /// DisplayBout method for displaying a computer players hand
        /// </summary>
        /// <param name="form">Default = null</param>
        public void DisplayBout(Form form = null)
        {
            if (form != null) //check if form is not null
            {
                const int X_OFFSET = 20; //integer for storing the x offset of cards in a bout
                const int Y_OFFSET = 20; //integer for storing the y offset of cards in a bout
                int xPosition; //integer for storing x position of cardbox
                int yPosition; //integer for storing y position pf cardbox
                int phaseCounter = 2; //integer for storing whos phase it is

                if (nextTurn == 0) //check if it is the human players turn
                {
                    xPosition = (form.Width / 2 - 300) - (firstBout.Count() * X_OFFSET / 2); //set default x position for human players turn
                    yPosition = (form.Height / 2 - 50); //set default y position for human players turn

                    foreach (CardBox cardBox in firstBout) //loop through cards in bout
                    {
                        if (phaseCounter % 2 == 0) //check if it's the attackers phase
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Location = new Point(xPosition, yPosition); //modify card location
                                cardBox.FaceUp = true; //flip cardbox up
                                form.Controls.Add(cardBox); //add hand of cardboxes to the form
                            });
                            xPosition += X_OFFSET; //incriment x position by x offset
                            yPosition -= Y_OFFSET; //decriment y position by y offset
                        }
                        else if (phaseCounter % 2 == 1) //check if it's the defenders phase
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Location = new Point(xPosition, yPosition); //modify card location
                                cardBox.FaceUp = true; //flip cardbox up
                                form.Controls.Add(cardBox); //add hand of cardboxes to the form
                            });
                            xPosition += X_OFFSET + cardBox.Width; //incriment x position by x offset
                            yPosition += Y_OFFSET; //incriment y position by y offset
                        }
                        phaseCounter++; //incriment phaseCounter
                    }
                }
                else  //check if its the computer players turn
                {
                    xPosition = (form.Width / 2 - 300) - (firstBout.Count() * X_OFFSET / 2); //set default x position for computer players turn
                    yPosition = (form.Height / 2 - 125); //set default y position for computer players turn

                    foreach (CardBox cardBox in firstBout) //loop through cards in bout
                    {
                        if (phaseCounter % 2 == 0) //check if it's the attackers phase
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Location = new Point(xPosition, yPosition); //modify card location
                                cardBox.FaceUp = true; //flip cardbox up
                                form.Controls.Add(cardBox); //add hand of cardboxes to the form
                            });
                            xPosition += X_OFFSET; //incriment x position by offset
                            yPosition += Y_OFFSET; //incriment y position by y offset
                        }
                        else if (phaseCounter % 2 == 1) //check if it's the defenders phase
                        {
                            myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                            {
                                cardBox.Location = new Point(xPosition, yPosition); //modify card location
                                cardBox.FaceUp = true; //flip cardbox up
                                form.Controls.Add(cardBox); //add hand of cardboxes to the form
                            });
                            xPosition += X_OFFSET + cardBox.Width; //incriment x position by x offset
                            yPosition -= Y_OFFSET; //decriment y position by y offset
                        }
                        phaseCounter++; //incriment phaseCounter
                    }
                }             

                foreach (CardBox cardBox in firstBout) //loop through cards in bout
                {
                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        cardBox.Enabled = false; //disable cards in bout
                    });
                }
            }
        }

        /// <summary>
        /// DisplayDiscardPile method for displaying a messey discard pile
        /// </summary>
        /// <param name="form">Default = null</param>
        public void DisplayDiscardPile(Form form = null)
        {
            if (form != null) //check if form is not null
            {
                int xPosition; //integer for storing x position of a card in the discard pile
                int yPosition; //integer for storing y position of a card in the discard pile
                Random rnd = new Random(); //Random object for creating random numbers

                foreach (CardBox cardBox in myDiscardPile) //loop through each card in the discard pile
                {
                    xPosition = 20 + rnd.Next(0,25); //set the x position to 20 plus a random number between 0 and 10 to simulate a messey discard pile
                    yPosition = (form.Height / 2) + rnd.Next(0, 25); //set the 7 position to half the form height plus a random number between 0 and 10 to simulate a messey discard pile

                    myForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        cardBox.Location = new Point(xPosition, yPosition); //modify card location

                        form.Controls.Add(cardBox); //add hand of cardboxes to the form
                    });
                }
            }
        }

        #endregion
    }
}