/**CardBox.cs
*
* Description: This control is used to display a standard playing card
*
* @authors Matthew Cormier, Kyle Warner, Chris Hobday
* @version 1.0
* @since 1.0 (03/23/2016)
*/


using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CardGameLibrary
{
    /// <summary>
    /// Control that displays a card picture.
    /// </summary>
    public partial class CardBox : UserControl
    {
        #region Properties and Methods

        /// <summary>
        /// A playing card.
        /// </summary>
        protected PlayingCard myCard;
        public PlayingCard MyCard
        {
            get { return myCard; }
            set
            {
                myCard = value;

                UpdateCardImage();
            }
        }


        /// <summary>
        /// Suit property of the card.
        /// </summary>
        public Suit Suit
        {
            get { return MyCard.MySuit; }
            set
            {
                MyCard.MySuit = value;

                UpdateCardImage();
            }
        }

        /// <summary>
        /// Rank property of the card.
        /// </summary>
        public Rank Rank
        {
            get { return MyCard.MyRank; }
            set
            {
                MyCard.MyRank = value;

                UpdateCardImage();
            }
        }

        /// <summary>
        /// faceUp - Bool repressenting if the card face up?
        /// </summary>
        public bool FaceUp
        {
            get { return MyCard.FaceUp; }
            set
            {
                // If value is different than the underlying card's FaceUp property.
                if (MyCard.FaceUp != value)
                {
                    // Set faceup to value and update card.
                    MyCard.FaceUp = value;

                    UpdateCardImage();

                    // If there is an event handler for CardFlipped in the client program.
                    if (CardFlipped != null)
                    {
                        // Call it.
                        CardFlipped(this, new EventArgs());
                    }
                }
            }
        }

        /// <summary>
        /// Orientation of the card image when displayed.
        /// </summary>
        protected Orientation myOrientation;
        public Orientation MyOrientation
        {
            get { return myOrientation; }
            set
            {
                // if value is different...
                if (myOrientation != value)
                {
                    // Change orientation and swap height and width.
                    myOrientation = value;
                    this.Size = new Size(Size.Height, Size.Width);

                    UpdateCardImage();
                }
            }
        }

        /// <summary>
        /// Sets the PicutreBox image using the underlying card and the orientation.
        /// </summary>
        public void UpdateCardImage()
        {
            // Set image.
            pbMyPictureBox.Image = MyCard.GetCardImage();

            // If orientation is horizontal.
            if (myOrientation == Orientation.Horizontal)
            {
                // rotate the image.
                pbMyPictureBox.Image.RotateFlip(RotateFlipType.Rotate90FlipNone);
            }
        }

        #endregion

        #region Constructors

        /// <summary>
        /// Default constructor - new card, oriented vertically.
        /// </summary>
        public CardBox()
        {
            InitializeComponent();

            myOrientation = Orientation.Vertical;

            myCard = new PlayingCard();
        }

        /// <summary>
        /// Parameterized constructor.
        /// </summary>
        /// <param name="card"></param>
        /// <param name="orientation"></param>
        public CardBox(PlayingCard card, Orientation orientation = Orientation.Vertical)
        {
            InitializeComponent();

            myOrientation = orientation;

            myCard = card;
        }

        #endregion

        #region Other Methods

        /// <summary>
        /// Call card ToString.
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return MyCard.ToString();
        }

        #endregion

        #region Events and Event Handlers

        /// <summary>
        /// An event handler for the load event.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void CardBox_Load(object sender, EventArgs e)
        {
            // Load image.
            UpdateCardImage();
        }

        /// <summary>
        /// An event the client program can handle when the user clicks the control.
        /// </summary>
        new public event EventHandler Click;

        /// <summary>
        /// An event the client program can handle when the card flips up/down.
        /// </summary>
        public event EventHandler CardFlipped;

        /// <summary>
        /// An event handler for the user clicking the picturebox control.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void pbMyPictureBox_Click(object sender, EventArgs e)
        {
            // If there is a handler for clicking the contorl.
            if (Click != null)
            {
                // Call it.
                Click(this, e);
            }
        }

        #endregion

    }
}