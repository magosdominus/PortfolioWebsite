/**Player.cs
*
* Description: This abstract class represents a generic player in a card game
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (03/24/2016)
*/

/**Attribution
*
* Player images retrieved from http://opengameart.org/content/boardgame-pack
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms; //implemented for adding controls to game form
using System.Drawing; //implemented for using the point structure

namespace CardGameLibrary
{
    public abstract class Player
    {
        #region Properties

        /// <summary>
        /// Name of the player.
        /// </summary>
        protected string myName;
        public string MyName
        {
            get { return myName; }
            set { myName = value; }
        }

        protected Image playerIcon;
        public Image PlayerIcon
        {
            get { return playerIcon; }
            set { playerIcon = value; }
        }

        /// <summary>
        /// Player's hand of cards.
        /// </summary>
        public Hand myHand = new Hand();
        //public List<PlayingCard> myHand = new List<PlayingCard>();

        /// <summary>
        /// list of cardboxes for displaying cards
        /// </summary>
        public List<CardBox> myCardBoxes = new List<CardBox>();

        #endregion

        #region Constructors

        /// <summary>
        /// Player constructor which can take a name, image, and windows form
        /// </summary>
        /// <param name="newName">Default = "Player"</param>
        /// <param name="newPlayerIcon">Default = null</param>
        /// <param name="newForm">Default = null</param>
        public Player(string newName = "Player", Image newPlayerIcon = null, Form newForm = null)
        { 
            this.MyName = newName; //set MyName based on entered newName

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
            }
        }

        #endregion

        #region Methods

        /// <summary>
        /// Adds a passed card to the player's hand.
        /// </summary>
        /// <param name="newCard"></param>
        /// <param name="form">Default = null</param>
        public void Add(PlayingCard newCard, Form form = null)
        {
            myHand.Add(newCard); //add new card to hand

            if (form != null)
            {
                myCardBoxes.Add(new CardBox(newCard)); //add new card to cardbox list
                DisplayHand(form); //display hand passing along given form
            }
        }

        /// <summary>
        /// Removes a passed card to the player's hand.
        /// </summary>
        /// <param name="newCard"></param>
        public void Remove(PlayingCard newCard)
        {
            // Loop through cards...
            for (int cardCounter = 0; cardCounter < myHand.Count; cardCounter++)
            {
                // If the current card is equal to the passed card, remove it from the hand.
                if (myHand[cardCounter] == newCard)
                {
                    myHand.RemoveAt(cardCounter);
                }
            }
            
            // Loop through card boxes...
            for (int boxCounter = 0; boxCounter < myCardBoxes.Count(); boxCounter++)
            {
                // If the current cardbox is equal to the passed card, remove it from the list.
                if (myCardBoxes[boxCounter].MyCard == newCard)
                {
                    myCardBoxes.RemoveAt(boxCounter);
                }
            }
        }

        /// <summary>
        /// DisplayHand virtual method which displays a generic players hand
        /// </summary>
        /// <param name="form">Default = null</param>
        public virtual void DisplayHand(Form form = null)
        {
            if (form != null) //check if form is null
            {
                const int OFFSET = 50; //integer for storing the offset between cards in a hand
                int position = (form.Width / 2) - (myCardBoxes.Count() * OFFSET / 2); //integer for storing x axis ofset of cardbox, set to half width of form minus half the number of cardboxes multiplied bythe offset

                foreach (CardBox cardBox in myCardBoxes.ToList())
                {
                    form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                    {
                        cardBox.Location = new Point(position, (form.Height - (cardBox.Height + 40))); //modify card location

                        form.Controls.Add(cardBox); //add hand of cardboxes to the form
                    });

                    position += OFFSET; //incriment position by offset
                }
            }
        }

        /// <summary>
        /// GetLowestTrumpCard method for getting and returning the lowest trump rank card in players hand
        /// </summary>
        /// <param name="newTrump"></param>
        /// <returns>Rank representing the lowest trump rank</returns>
        public Rank GetLowestTrumpCard(Suit newTrump)
        {
            Rank lowestTrumpRank = Rank.ace; //Rank variable for storing lowest trump rank of player, default set to highest rank 'ace'

            foreach (CardBox card in myCardBoxes) //loop through cards in hand
            {
                if (card.MyCard.MySuit == newTrump && card.MyCard.MyRank < lowestTrumpRank) //check if the current card is a trump card and has a rank less than the current lowest rank trump card
                {
                    lowestTrumpRank = card.MyCard.MyRank; //set the lowest trump rank to the new lowest
                }
            }

            return lowestTrumpRank; //return the lowest trump rank
        }

        /// <summary>
        /// Attack virtual method for representing an attack from a player
        /// </summary>
        /// <param name="trumpSuit"></param>
        /// <param name="bout"></param>
        /// <returns>CardBox representing the card played</returns>
        public virtual CardBox Attack(Suit trumpSuit, List<CardBox> bout = null)
        {
            CardBox firstCard = myCardBoxes[0]; //create a new cardbox for storing the first card in players hand

            myCardBoxes.Remove(myCardBoxes[0]); //remove the first card in hand

            return firstCard; //return the first card in players hand
        }

        /// <summary>
        /// Defend virtual method for representing a defense from a player
        /// </summary>
        /// <param name="trumpSuit"></param>
        /// <param name="bout"></param>
        /// <returns></returns>
        public virtual CardBox Defend(Suit trumpSuit, List<CardBox> bout = null)
        {
            CardBox firstCard = myCardBoxes[0]; //create a new cardbox for storing the first card in players hand

            myCardBoxes.Remove(myCardBoxes[0]); //remove the first card in hand

            return firstCard; //return the first card in players hand
        }

        #endregion
    }
}