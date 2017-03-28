/**Computer.cs
*
* Description: This class represents a computer player in a card game
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (04/15/2016)
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms; //implemented for adding controls to game form
using System.Drawing; //implemented for using the point structure

namespace CardGameLibrary
{
    public class Computer : Player
    {
        #region Properties

        /// <summary>
        /// myDifficulty string for storing the difficulty of the computer player
        /// </summary>
        protected string myDifficulty;
        public string MyDifficulty
        {
            get { return myDifficulty; }
            set { myDifficulty = value; }
        }

        #endregion

        #region Constructors

        /// <summary>
        /// Computer constructor which can take a name, player icon, difficulty, and form
        /// </summary>
        /// <param name="newName">Default = "Computer"</param>
        /// <param name="newPlayerIcon">Default = null</param>
        /// <param name="newDifficulty">Default = "Easy"</param>
        /// <param name="form">Default = null</param>
        public Computer(string newName = "Computer", Image newPlayerIcon = null, string newDifficulty = "Easy", Form newForm = null)
        {
            this.MyName = newName; //set name based on entered newName
            this.MyDifficulty = newDifficulty; //set difficulty based on entered newDifficulty

            if (newForm != null) //check if form is not null
            {
                if (newPlayerIcon == null) //check if newPlayerIcon is null
                {
                    this.PlayerIcon = Properties.Resources.ResourceManager.GetObject("white_character") as Image; //set image to default white character
                }
                else //newPlayerIcon is not null
                {
                    this.PlayerIcon = newPlayerIcon; //set PlayerIcon based on entered newPlayerIcon
                }

                PictureBox characterIcon = new PictureBox(); //create a new picturebox
                characterIcon.Width = 64; //set characterIcon Width
                characterIcon.Height = 64; //set characterIcon Height
                characterIcon.Image = PlayerIcon; //set characterIcon picturebox image to PlayerIcon
                characterIcon.Location = new Point(0, 0); //set characterIcon location

                newForm.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    newForm.Controls.Add(characterIcon); //add characterIcon to newForm
                });
            }
        }

        #endregion

        #region Methods

        /// <summary>
        /// DisplayHand overloaded method for displaying a computer players hand
        /// </summary>
        /// <param name="form">Default = null</param>
        public override void DisplayHand(Form form = null)
        {       
            if (form != null) //check if form is null
            {
                const int OFFSET = 50; //integer for storing the offset between cards in a hand
                int position = (form.Width / 2) - (myCardBoxes.Count() * OFFSET / 2); //integer for storing x axis ofset of cardbox, set to half width of form minus half the number of cardboxes multiplied bythe offset

                foreach (CardBox cardBox in myCardBoxes) //loop through hand of cards
                {
                    form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        cardBox.Location = new Point(position, 0); //modify card location
                        form.Controls.Add(cardBox); //add hand of cardboxes to the form
                    });

                    position += OFFSET; //incriment position by offset
                }
            }
        }

        /// <summary>
        /// Attack override method for processing a computer ai attack
        /// </summary>
        /// <param name="trumpSuit"></param>
        /// <param name="bout">Default = null</param>
        /// <returns>CardBox representing the card played</returns>
        public override CardBox Attack(Suit trumpSuit, List<CardBox> bout = null)
        {
            CardBox playedCard = null; //create a new cardbox for storing the card to be played, default set to null

            Thread.Sleep(3000); //simulate thinking of computer with 5 second delay

            if (bout == null || bout.Count == 0) //check if no cards have been played in the bout yet
            {
                playedCard = myCardBoxes[0]; //set the playedCard to the first card in computers hand
                Remove(myCardBoxes[0].MyCard); //remove the first card in hand
                return playedCard; //return the bout with the new card added
            }
            else //cards have already been played in the bout
            {
                foreach (CardBox boutCard in bout) //loop through bout of cards
                {
                    foreach (CardBox handCard in myCardBoxes) //loop through hand of cards
                    {
                        if (handCard.MyCard.MyRank == boutCard.MyCard.MyRank) //check if the current card in hand is of same rank as current card in bout
                        {
                            playedCard = handCard; //set the card of the same rank from hand
                            Remove(handCard.MyCard); //remove the card of the same rank from hand
                            return playedCard; //return the bout with the new card added
                        }
                    }
                }
            }

            //no cards were playable
            return playedCard; //return null playedCard
        }

        /// <summary>
        /// Defend override method for processing a computer ai's defence to an attack
        /// </summary>
        /// <param name="trumpSuit"></param>
        /// <param name="bout"></param>
        /// <returns>CardBox representing the card played</returns>
        public override CardBox Defend(Suit trumpSuit, List<CardBox> bout)
        {
            CardBox playedCard = null; //create a new cardbox for storing the card to be played, default set to null

            Thread.Sleep(3000); //simulate thinking of computer with 5 second delay

            foreach (CardBox handCard in myCardBoxes) //loop through hand of cards
            {
                if (handCard.MyCard.MySuit == bout[bout.Count - 1].MyCard.MySuit && handCard.MyCard.MyRank > bout[bout.Count - 1].MyCard.MyRank) //check if the current card in hand is of same suit as the last played card in the bout and of higher rank
                {
                    playedCard = handCard; //set the playedCard to the current hand card
                    Remove(handCard.MyCard); //remove the hand card from cardboxes
                    return playedCard; //return the bout with the new card added
                }
                else if (handCard.MyCard.MySuit == trumpSuit && bout[bout.Count - 1].MyCard.MySuit != trumpSuit) //check if current card in hand is of same suit as trump suit and the last played card in the bout is not of trump suit
                {
                    playedCard = handCard; //set the playedCard to the current hand card
                    Remove(handCard.MyCard); //remove the hand card from cardboxes
                    return playedCard; //return the bout with the new card added
                }
            }

            //no cards were playable
            return playedCard; //return null playedCard
        }

        #endregion
    }
}