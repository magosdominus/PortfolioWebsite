/**Human.cs
*
* Description: This class represents a human player in a card game
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday 
* @version 1.0
* @since 1.0 (03/23/2016)
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
    public class Human : Player
    {
        #region Properties

        

        #endregion

        #region Constructors

        /// <summary>
        /// Human Constructor which can take a name
        /// </summary>
        /// <param name="newName">Default = "Computer"</param>
        /// <param name="newPlayerIcon">Default = null</param>
        /// <param name="newForm">Default = null</param>
        public Human(string newName = "Human", Image newPlayerIcon = null, Form newForm = null)
        {
            this.MyName = newName; //set name based on entered newName

            if (newForm != null) //check if form is not null
            {
                if (newPlayerIcon == null) //check if newPlayerIcon is null
                {
                    this.PlayerIcon = Properties.Resources.ResourceManager.GetObject("black_character") as Image; //set image to default white character
                }
                else //newPlayerIcon is not null
                {
                    this.PlayerIcon = newPlayerIcon; //set PlayerIcon based on entered newPlayerIcon
                }

                PictureBox characterIcon = new PictureBox(); //create a new picturebox
                characterIcon.Width = 64; //set characterIcon Width
                characterIcon.Height = 64; //set characterIcon Height
                characterIcon.Image = PlayerIcon; //set characterIcon picturebox image to PlayerIcon
                characterIcon.Location = new Point(0, (newForm.Height - (characterIcon.Height + 40))); //set characterIcon location

                newForm.Invoke((MethodInvoker)delegate
                {
                    newForm.Controls.Add(characterIcon); //add characterIcon to newForm
                });
            }
        }

        #endregion

        #region Methods

        #endregion
    }
}
