/**CardOutOfRangeException.cs
*
* Description: This is an exception class for managing when a card is out of range
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

namespace CardGameLibrary
{
    class CardOutOfRangeException : Exception
    {
        
        private Cards deckContents;
        public Cards DeckContents
        {
            get { return deckContents; }
        }

        public CardOutOfRangeException(Cards sourceDeckContents)
            : base(" ")
        {
            deckContents = sourceDeckContents;
        }
    }
}