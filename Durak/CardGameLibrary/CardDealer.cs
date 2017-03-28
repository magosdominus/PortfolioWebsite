/**CardDealer.cs
*
* Description: This class represents a card dealer/shoe which will be used to deal cards to players
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (03/22/2016)
*/

/**Attribution
* Card sounds retrieved from https://freesound.org/people/empraetorius/sounds/201253/ and https://freesound.org/people/f4ngy/sounds/240776/
*/

using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Media; //implemented for using sound files
using System.Windows.Forms; //implemented for adding controls to form
using System.Drawing; //implemented for updating control locations with point

namespace CardGameLibrary
{
    public class CardDealer : ICloneable
    {
        #region Properties

        /// <summary>
        /// Deck of standard cards.
        /// </summary>
        protected StandardDeck myDeck;
        public StandardDeck MyDeck
        {
            get { return myDeck; }
            set { myDeck = value; }
        }

        #endregion

        #region Constructors

        /// <summary>
        /// CardDealer constructor which can take a deck of cards and a windows form
        /// </summary>
        /// <param name="deck">Default = null</param>
        /// <param name="form">Default = null</param>
        public CardDealer(StandardDeck deck = null, Form form = null)
        {
            MyDeck = deck; //set MyDeck based on entered deck

            Shuffle(); //shuffle the deck

            if (form != null) //check if form is null
            {
                MyDeck.DeckImage.MyCard.FaceUp = false; //flip deck image to become a card back
                MyDeck.DeckImage.UpdateCardImage(); //update deck image

                MyDeck.DeckImage.Location = new Point((form.Width - (MyDeck.DeckImage.Width + 40)), ((form.Height / 2) - (MyDeck.DeckImage.Height / 2))); //set the deck image location to middle right of form

                form.Invoke((MethodInvoker)delegate //use the Invoke method to safely alter a control of the ui thread
                {
                    form.Controls.Add(MyDeck.DeckImage); //add the deckImage control to the form
                });

                SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.contact_sound); //create new soundplayer for the contact_sound
                audio.PlaySync(); //play the contact_sound haulting program
            }
        }

        #endregion

        #region Methods

        /// <summary>
        /// Clones the dealer.
        /// </summary>
        /// <returns> newDealer </returns>
        public object Clone()
        {
            CardDealer newDealer = new CardDealer(MyDeck.Clone() as StandardDeck);
            return newDealer;
        }

        /// <summary>
        /// Shuffle the deck.
        /// </summary>
        public void Shuffle()
        {
            if (myDeck != null) //check if deck is not null
            {
                MyDeck.Shuffle(); //shuffle deck

                SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.shuffle_sound); //create new soundplayer for the shuffle sound
                audio.PlaySync(); //play the shuffle sound on same thread haulting program
            }
        }

        /// <summary>
        /// Deal a hand of cards.
        /// </summary>
        /// <param name="player"></param>
        /// <param name="numberOfCards"></param>
        /// <param name="form">Default = null</param>
        public void DealHand(Player player, int numberOfCards, Form form = null)
        {
            for (int cardCounter = 0; cardCounter < numberOfCards; cardCounter++)
            {
                if (form != null) //check if form is not null
                {
                    DealCard(player, form); //deal card to player passing along the given form
                }
                else //form is null
                {
                    DealCard(player); //deal card to player
                }
            }
        }

        /// <summary>
        /// Deal a card.
        /// </summary>
        /// <param name="player"></param>
        public void DealCard(Player player, Form form = null)
        {
            // Get the first card on the top of the deck. Assign to drawnCard.
            PlayingCard drawnCard = MyDeck.GetCard(0);
            // Remove this card from the deck.
            MyDeck.RemoveCard(drawnCard);

            if (player.GetType() != typeof(Computer)) //check if the given player is not a computer
            {
                drawnCard.FlipCard(); //flip the drawnCard
            }

            if (form != null) //check if form is not null
            {
                player.Add(drawnCard, form); //add card to given players hand passing along given form
            }
            else //form is null
            {
                player.Add(drawnCard); //add card to given players hand
            }

            SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.flip_sound); //create new soundplayer for the card flip sound
            audio.PlaySync(); //play the card flip sound on same thread haulting program
        }

        /// <summary>
        /// Remove each card from the deck. Used for starting a new game after an initial game.
        /// </summary>
        public void Empty()
        {
            for (int removeCounter = 0; removeCounter < MyDeck.DeckSize; removeCounter++)
            {
                MyDeck.RemoveCardAt(removeCounter);
            }
        }

        /// <summary>
        /// Return the number of remaining cards in the deck within the dealer.
        /// </summary>
        /// <returns></returns>
        public int CardsRemaining()
        {
            int cardsRemaining;

            if (MyDeck.DeckSize != null)
            {
                cardsRemaining = (int)MyDeck.DeckSize;
            }
            else
            {
                cardsRemaining = 0;
            }

            //Debug.WriteLine("Cards remaining: " + cardsRemaining);

            return cardsRemaining;
        }

        /// <summary>
        /// Method for selecting the trump of the game
        /// </summary>
        /// <returns></returns>
        public PlayingCard SelectTrumpCard()
        {
            PlayingCard drawnCard = MyDeck.GetCard(0); // Get the first card on the top of the deck. Assign to drawnCard.
            MyDeck.RemoveCard(drawnCard); // Remove this card from the deck.
            drawnCard.FlipCard(); // Flip drawnCard.

            SoundPlayer audio = new SoundPlayer(CardGameLibrary.Properties.Resources.flip_sound); //create new soundplayer for the card flip sound
            audio.PlaySync(); //play the card flip sound on same thread haulting program

            return drawnCard; //return trump card
        }

        /// <summary>
        /// DisplayAllCards Method for testing the display of all cardBoxes
        /// </summary>
        /// <param name="form">Default = null</param>
        public void DisplayAllCards(Form form = null)
        {
            if (form != null) //check if form is not null
            {
                int positionX = 0; //integer for storing x position of cardbox control
                CardBox[] cardBoxes = new CardBox[(int)MyDeck.DeckSize]; //create array for holding all cards as card boxes
                ComboBox cmbTestBox = new ComboBox(); //combobox for storing all cards in text form
                cmbTestBox.Location = new Point(300, 300); //set combobox location
            
                for (int x = 0; x < MyDeck.DeckSize; x++) //loop through deck
                {
                    form.Invoke((MethodInvoker)delegate
                    {
                        cardBoxes[x] = new CardBox(MyDeck.Cards[x]); //create a new cardbox of the current card

                        cardBoxes[x].FaceUp = true; //set faceup

                        cardBoxes[x].Location = new Point(positionX, 0); //set cardbox position

                        form.Controls.Add(cardBoxes[x]); //add cardbox to form

                        cmbTestBox.Items.Add(cardBoxes[x]); //add cardbox to combobox
                    });

                    positionX += 30; //increase position x
                }
            }
        }

        #endregion
    }
}