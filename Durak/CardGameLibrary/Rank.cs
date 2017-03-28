/**Rank.cs
*
* Description: This enumerate holds the rank values of a standard playing card
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
    public enum Rank : byte
    {
        two = 1,
        three,
        four,
        five,
        six,
        seven,
        eight,
        nine,
        ten,
        jack,
        queen,
        king,
        ace
    }
}