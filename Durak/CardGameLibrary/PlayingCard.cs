/**PlayingCard.cs
*
* Description: This class represents a standard playing card in card games
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (03/22/2016)
*/

/**Attribution
* Card images retrieved from http://opengameart.org/content/boardgame-pack
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace CardGameLibrary
{
    public class PlayingCard : ICloneable, IComparable
    {

        #region Attributes and properties

        /// <summary>
        /// Suit property of the card.
        /// </summary>
        protected Suit mySuit;
        public Suit MySuit
        {
            get { return mySuit; }
            set { mySuit = value; }
        }

        /// <summary>
        /// Rank property of the card.
        /// </summary>
        protected Rank myRank;
        public Rank MyRank
        {
            get { return myRank; }
            set { myRank = value; }
        }

        /// <summary>
        /// Value of the card based off the rank and alternate value.
        /// </summary>
        protected int myValue;
        public int CardValue
        {
            get { return myValue; }
            set { myValue = value; }
        }

        /// <summary>
        /// faceUp - Bool repressenting if the card face up?
        /// </summary>
        protected bool faceUp;
        public bool FaceUp
        {
            get { return faceUp; }
            set { faceUp = value; }
        }

        /// <summary>
        /// Alternate value of the card for certain card games.
        /// </summary>
        protected int? altValue;
        public int? AlternateValue
        {
            get { return altValue; }
            set { altValue = value; }
        }

        /// <summary>
        /// Static boolean that represents if trumps is used for the card.
        /// </summary>
        protected static bool useTrumps;
        public static bool UseTrumps
        {
            get { return useTrumps; }
            set { useTrumps = value; }
        }

        /// <summary>
        /// Static boolean that represents if ace is high for the card comparisons.
        /// </summary>
        protected static bool isAceHigh;
        public static bool IsAceHigh
        {
            get { return isAceHigh; }
            set { isAceHigh = value; }
        }

        /// <summary>
        /// Static suit that represents the trump suit for the card comparisons.
        /// </summary>
        protected static Suit trump;
        public static Suit Trump
        {
            get { return trump; }
            set { trump = value; }
        }

        #endregion

        #region Constructors

        /// <summary>
        /// PlayingCard constructor that optionaly takes a suit, rank, use trumps, trump, face up, and alt value
        /// </summary>
        /// <param name="newSuit"></param>
        /// <param name="newRank"></param>
        /// <param name="newUseTrumps"></param>
        /// <param name="newTrump"></param>
        /// <param name="newFaceUp"></param>
        /// <param name="newAltValue"></param>
        public PlayingCard(Suit newSuit = Suit.hearts, Rank newRank = Rank.ace, bool newUseTrumps = true, Suit newTrump = Suit.hearts, bool newFaceUp = false, int? newAltValue = null)
        {
            this.mySuit = newSuit; //set MySuit based on entered newSuit
            this.MyRank = newRank; //set MyRank based on entered newRank
            UseTrumps = newUseTrumps; //set UseTrumps based on entered newUseTrumps
            Trump = newTrump; //set Trump based on entered newTrump
            FaceUp = newFaceUp; //set FaceUp based on entered newFaceUp

            if (newAltValue != null) //check if newAltValue is not default null value
            {
                this.AlternateValue = newAltValue; //set AlternateValue based on entered newAltValue
                this.CardValue = (int)newAltValue; //set CardValue based on entered newAltValue
            }
            else //newAltValue is default null value
            {
                this.CardValue = (int)newRank; //set CardValue based on integer version of newRank
            }
        }

        #endregion

        #region Methods

        /// <summary>
        /// Clone() - Returns a clone.
        /// </summary>
        /// <returns> Memberwise Clone() </returns>
        public object Clone()
        {
            return MemberwiseClone();
        }

        /// <summary>
        /// CompareTo method that takes an object and converts it to a card and compares it to this card.
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public virtual int CompareTo(object obj)
        {
            // Check if the arugment is null.
            if (obj == null)
            {
                throw new ArgumentNullException("Unable to compare a Card to a null object.");
            }

            // Convert arugment object to a card.
            PlayingCard compareCard = obj as PlayingCard;

            // Check if the arugment object could be comparable to a card object.
            if (compareCard != null)
            {
                // Compare the card objects.
                int thisSort = this.myValue * 10 + (int)this.MySuit;
                int compareCardSort = compareCard.myValue * 10 * (int)compareCard.MySuit;
                return (thisSort.CompareTo(compareCardSort));
            }
            else
            {
                throw new ArgumentException("Object being compared cannot be converted to a card.");
            }
        }

        /// <summary>
        /// ToString() - Returns the rank and suit of the card as a string.
        /// </summary>
        /// <returns> output - string with the value of the card. </returns>
        public override string ToString()
        {
            string output = ""; //string for storing text version of card

            // Check if the card is face up or down.
            // Add appropriate message to output.
            if (this.FaceUp == true)
            {
                output = "The " + MyRank + " of " + MySuit; //set output to text version of rank and suit
            }
            else
            {
                output = "Face down"; //set output to face down
            }

            // Return output.
            return output;
        }

        /// <summary>
        /// ToShorthandString() - Returns the rank and suit of the card as a shorthand string.
        /// </summary>
        /// <returns> output - string with the value of the card. NOTE: always returns value even if face down</returns>
        public string ToShorthandString()
        {
            string rank = ""; //string for storing text version of rank
            string suit = ""; //string for storing text version of suit
            string output = ""; //string for storing text version of card

            if ((int)MyRank + 1 > 9) //check if rank of card is higher than 9
            {
                rank += MyRank; //set rank to string version of MyRank
            }
            else
            {
                rank += (int)MyRank + 1; //set rank to int version of MyRank
            }
            suit += MySuit; //set suit
            output = rank.Substring(0, 1).ToUpper() + suit.Substring(0, 1).ToUpper(); //set output for short hand rank and suit

            return output; //return output
        }

        public string ToStringImage()
        {
            string output = ""; //string for storing image name as string

            // Check if the card is face up or down.
            // Add appropriate message to output.
            if (this.FaceUp == true)
            {
                output = (MyRank.ToString()).ToLower() + "_" + "of" + "_" + (MySuit.ToString()).ToLower(); //create and set string to the text version of cards rank and suit
            }
            else
            {
                output = "card_back"; //set output string to card_back
            }

            // Return output.
            return output;
        }

        /// <summary>
        /// GetHashCode() - gets the hash code of the card.
        /// </summary>
        /// <returns> 13 * suit and rank </returns>
        public override int GetHashCode()
        {
            return 10 * this.myValue + (int)this.MySuit + ((this.faceUp) ? 1 : 0); //return hashcode
        }

        /// <summary>
        /// Gets the image matching the suit and rank of this card.
        /// </summary>
        /// <returns> Image of the card </returns>
        public Image GetCardImage()
        {
            string imageName = ToStringImage();   // The filename of the image in the resources file.
            Image cardImage;    // holds the image.

            // Grab the image from resources and assign to cardImage.
            cardImage = CardGameLibrary.Properties.Resources.ResourceManager.GetObject(imageName) as Image;

            // Return the image.
            return cardImage;
        }

        /// <summary>
        /// Returns a debug message.
        /// </summary>
        /// <returns> String debug message. </returns>
        public string DebugString()
        {
            string cardState = (string)(MyRank.ToString() + " of " + MySuit.ToString()).PadLeft(20);
            cardState += (string)((FaceUp) ? "(Face Up)" : "(Face Down)").PadLeft(12);
            cardState += " Value: " + myValue.ToString().PadLeft(2);
            cardState += ((AlternateValue != null) ? "/" + AlternateValue.ToString() : "");

            return cardState;
        }

        public void FlipCard()
        {
            FaceUp = true;
        }

        #endregion

        #region Operators

        /// <summary>
        /// = - Equals comparison operator.
        /// </summary>
        /// <param name="card1"></param>
        /// <param name="card2"></param>
        /// <returns></returns>
        public static bool operator ==(PlayingCard card1, PlayingCard card2)
        {
            return (card1.mySuit == card2.mySuit) && (card1.myRank == card2.myRank);
        }

        /// <summary>
        /// != - Does not equal operator.
        /// </summary>
        /// <param name="card1"></param>
        /// <param name="card2"></param>
        /// <returns></returns>
        public static bool operator !=(PlayingCard card1, PlayingCard card2)
        {
            return !(card1 == card2);
        }

        /// <summary>
        /// Eqauls method
        /// </summary>
        /// <param name="card"></param>
        /// <returns></returns>
        public override bool Equals(object card)
        {
            return this == (PlayingCard)card;
        }

        /// <summary>
        /// > - greater than operator.
        /// </summary>
        /// <param name="card1"></param>
        /// <param name="card2"></param>
        /// <returns></returns>
        public static bool operator >(PlayingCard card1, PlayingCard card2)
        {
            if (card1.MySuit == card2.MySuit)
            {
                if (IsAceHigh == true)
                {
                    if (card1.MyRank == Rank.ace)
                    {
                        return true;
                    }
                    else if (card1.MyRank != Rank.ace && card2.MyRank == Rank.ace)
                    {
                        return false;
                    }
                    else
                    {
                        return (card1.MyRank > card2.MyRank);
                    }
                }
                else
                {
                    return (card1.MyRank > card2.MyRank);
                }
            }
            else
            {
                if (UseTrumps == true && card2.MySuit == Trump)
                {
                    return false;
                }
                else
                {
                    return (card1.MyRank > card2.MyRank);
                }
            }
        }

        /// <summary>
        /// Less than operator.
        /// </summary>
        /// <param name="card1"></param>
        /// <param name="card2"></param>
        /// <returns></returns>
        public static bool operator <(PlayingCard card1, PlayingCard card2)
        {
            return !(card1 >= card2);
        }

        /// <summary>
        /// >= - Great than or equal to operator.
        /// </summary>
        /// <param name="card1"></param>
        /// <param name="card2"></param>
        /// <returns></returns>
        public static bool operator >=(PlayingCard card1, PlayingCard card2)
        {
            if (card1.mySuit == card2.mySuit)
            {
                if (isAceHigh)
                {
                    if (card1.myRank == Rank.ace)
                    {
                        return true;
                    }
                    else
                    {
                        if (card2.myRank == Rank.ace)
                        {
                            return false;
                        }
                        else
                        {
                            return (card1.myRank >= card2.myRank);
                        }
                    }
                }
                else
                {
                    return (card1.myRank >= card2.myRank);
                }
            }
            else
            {
                if (useTrumps && (card2.mySuit == PlayingCard.Trump))
                {
                    return false;
                }
                else
                {
                    return (card1.myRank >= card2.myRank);
                }
            }
        }

        /// <summary>
        /// Less than or equal to operator.
        /// </summary>
        /// <param name="card1"></param>
        /// <param name="card2"></param>
        /// <returns></returns>
        public static bool operator <=(PlayingCard card1, PlayingCard card2)
        {
            return !(card1 > card2);
        }

        #endregion
    }
}