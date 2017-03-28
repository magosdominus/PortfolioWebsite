/**Hand.cs
*
* Description: This class represents a players hand of cards
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (03/01/2016)
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CardGameLibrary
{
    public class Hand : Cards, ICloneable
    {
        #region Properties

        /// <summary>
        /// Cards list.
        /// </summary>
        public Cards cards = new Cards();

        #endregion

        #region Methods

        /// <summary>
        /// Sort the hand based of the rank of each card.
        /// </summary>
        public new void Sort()
        {
            var sorted = cards.OrderByDescending(c => c.MyRank);
        }

        #endregion
    }
}