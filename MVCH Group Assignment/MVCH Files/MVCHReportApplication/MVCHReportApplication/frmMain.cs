/*
 * frmMain - This application will allow the user to view multiple reports queried from
 *           the MVCH database. This form will act as the main nav menu.
 * 
 * @author Matthew Cormier, Gurpreet Singh Maan, Maitri Gemlawala
 * @version 1.0
 * @since 1.0 (04/07/2016)
 * 
 */

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace MVCHReportApplication
{
    public partial class frmMain : Form
    {
        public frmMain()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Open room utilization report.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnRoomUtilizationReport_Click(object sender, EventArgs e)
        {
            frmRoomUtilizationReport newForm = new frmRoomUtilizationReport();
            newForm.ShowDialog();
        }

        /// <summary>
        /// Open physician patient report.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnPhysicianPatientReport_Click(object sender, EventArgs e)
        {
            frmPhysicianPatientReport newForm = new frmPhysicianPatientReport();
            newForm.ShowDialog();
        }

        /// <summary>
        /// Close the application.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
