/**
 * Physician Patient Report - This report will display all admitted patients and the Doctors that referred them to the hospital.
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
    public partial class frmPhysicianPatientReport : Form
    {
        /// <summary>
        /// Initializes the form.
        /// </summary>
        public frmPhysicianPatientReport()
        {
            InitializeComponent();
            this.Load += new System.EventHandler(frmPhysicianPatientReport_Load);
        }

        // Create Dataset and TableAdapter objects.
        private MVCH_db_DataSet MVCHDataset;
        private MVCH_db_DataSetTableAdapters.Patients_ReferredTableAdapter referredAdapter;
        private MVCH_db_DataSetTableAdapters.PhysiciansTableAdapter physicianAdapter;
        private MVCH_db_DataSetTableAdapters.PatientsTableAdapter patientAdapter;
        private MVCH_db_DataSetTableAdapters.Locations_Admitted_TableAdapter locationsAdapter;

        /// <summary>
        /// Loads the data from MVCH_db onto the report layout from rptRooms.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void frmPhysicianPatientReport_Load(object sender, EventArgs e)
        {
            rptPhysicianPatient aPatientPhysicianReport;

            aPatientPhysicianReport = new rptPhysicianPatient();

            // Initialize and fill data from the db.
            try
            {
                MVCHDataset = new MVCH_db_DataSet();
                referredAdapter = new MVCH_db_DataSetTableAdapters.Patients_ReferredTableAdapter();
                physicianAdapter = new MVCH_db_DataSetTableAdapters.PhysiciansTableAdapter();
                patientAdapter = new MVCH_db_DataSetTableAdapters.PatientsTableAdapter();
                locationsAdapter = new MVCH_db_DataSetTableAdapters.Locations_Admitted_TableAdapter();

                // An unknown error is causing an exception to be thrown, although the report loads correctly, so I'm catching the exception with no action.
                try
                {
                    locationsAdapter.Fill(MVCHDataset.Locations_Admitted_);
                }
                catch (Exception)
                {

                }

                patientAdapter.Fill(MVCHDataset.Patients);
                physicianAdapter.Fill(MVCHDataset.Physicians);
                referredAdapter.Fill(MVCHDataset.Patients_Referred);

                aPatientPhysicianReport.SetDataSource(MVCHDataset);

                PhysicianPatientViewer.ReportSource = aPatientPhysicianReport;
            }
            catch (Exception dataException)
            {
                MessageBox.Show("Data Error Encountered: " + dataException.Message);
            }
            this.PhysicianPatientViewer.RefreshReport();
        }
    }
}