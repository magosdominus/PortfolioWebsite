/**DeckOutOfRangeException.cs
*
* Description:  This is an exception class for managing when a deck is out of range
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
    class DeckOutOfRangeException : Exception
    {
        public DeckOutOfRangeException()
            : base(HumanReadable())
        {
        }

        private static string HumanReadable()
        {
            return "\nDeck out of range! Deck must be passed a deck size of 20, 36, or 52. ";
        }
    }
}