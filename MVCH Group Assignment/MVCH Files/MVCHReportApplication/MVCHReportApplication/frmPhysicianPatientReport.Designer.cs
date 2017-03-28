namespace MVCHReportApplication
{
    partial class frmPhysicianPatientReport
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.PhysicianPatientViewer = new CrystalDecisions.Windows.Forms.CrystalReportViewer();
            this.SuspendLayout();
            // 
            // PhysicianPatientViewer
            // 
            this.PhysicianPatientViewer.ActiveViewIndex = -1;
            this.PhysicianPatientViewer.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.PhysicianPatientViewer.Cursor = System.Windows.Forms.Cursors.Default;
            this.PhysicianPatientViewer.Dock = System.Windows.Forms.DockStyle.Fill;
            this.PhysicianPatientViewer.Location = new System.Drawing.Point(0, 0);
            this.PhysicianPatientViewer.Name = "PhysicianPatientViewer";
            this.PhysicianPatientViewer.Size = new System.Drawing.Size(284, 261);
            this.PhysicianPatientViewer.TabIndex = 0;
            // 
            // frmPhysicianPatientReport
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.PhysicianPatientViewer);
            this.Name = "frmPhysicianPatientReport";
            this.Text = "Physician-Patient Report";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.ResumeLayout(false);

        }

        #endregion

        private CrystalDecisions.Windows.Forms.CrystalReportViewer PhysicianPatientViewer;
    }
}