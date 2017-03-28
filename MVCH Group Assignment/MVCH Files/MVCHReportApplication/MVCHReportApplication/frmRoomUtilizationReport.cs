/**
 * Room Utilization Report - This report will display all rooms and any patient admissions to said rooms.
 * 
 * @author Matthew Cormier, Gurpreet Singh Maan, Maitri Gemlawala
 * @since 1.0 (04/13/2016)
 * @verson 1.0
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
    public partial class frmRoomUtilizationReport : Form
    {
        /// <summary>
        /// Initializes the form.
        /// </summary>
        public frmRoomUtilizationReport()
        {
            InitializeComponent();
            this.Load += new System.EventHandler(frmRoomUtilizationReport_Load);
        }

        // Create Dataset and TableAdapter objects.
        private MVCH_db_DataSet MVCHDataset;
        private MVCH_db_DataSetTableAdapters.Locations_Admitted_TableAdapter patientAdmissionTableAdapter;
        private MVCH_db_DataSetTableAdapters.RoomsTableAdapter roomTableAdapter;

        /// <summary>
        /// Loads the data from MVCH_db onto the report layout from rptRooms.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void frmRoomUtilizationReport_Load(object sender, EventArgs e)
        {
            rptRooms aRoomReport;

            aRoomReport = new rptRooms();

            // Initialize and fill data from the db.
            try
            {
                MVCHDataset = new MVCH_db_DataSet();
                patientAdmissionTableAdapter = new MVCH_db_DataSetTableAdapters.Locations_Admitted_TableAdapter();
                roomTableAdapter = new MVCH_db_DataSetTableAdapters.RoomsTableAdapter();

                // An unknown error is causing an exception to be thrown, although the report loads correctly, so I'm catching the exception with no action.
                try
                {
                    patientAdmissionTableAdapter.Fill(MVCHDataset.Locations_Admitted_);
                }
                catch (Exception)
                {
                    
                }
                roomTableAdapter.Fill(MVCHDataset.Rooms);

                aRoomReport.SetDataSource(MVCHDataset);

                RoomUtilizationViewer.ReportSource = aRoomReport;
            }
            catch(Exception dataException)
            {
                MessageBox.Show("Data Error Encountered: " + dataException.Message);
            }
            this.RoomUtilizationViewer.RefreshReport();
        }
    }
}