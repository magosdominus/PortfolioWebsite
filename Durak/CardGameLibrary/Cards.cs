/**Cards.cs
*
* Description: This class represents a collection of playing cards
*
* @author Matthew Cormier, Kyle Warner, Chris Hobday
* @version 1.0
* @since 03/1/2016
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;           // For List<>.

namespace CardGameLibrary
{
    public class Cards : List<PlayingCard>, ICloneable
    {
        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="targetCards"></param>
        public void CopyTo(Cards targetCards)
        {
            // For each element in the list.
            for (int index = 0; index < this.Count; index++)
            {
                // clone the card.
                targetCards[index] = this[index];
            }
        }

        /// <summary>
        /// Clones the card list.
        /// </summary>
        /// <returns> newCards- new list of playing cards. </returns>
        public object Clone()
        {
            Cards newCards = new Cards();

            // foreach source card in the list.
            foreach (PlayingCard sourceCard in this)
            {
                // Clone the cards.
                newCards.Add((PlayingCard)sourceCard.Clone());
            }

            // Return the list.
            return newCards;
        }
    }
}