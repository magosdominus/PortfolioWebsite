namespace MVCHReportApplication
{
    partial class frmMain
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
            this.btnRoomUtilizationReport = new System.Windows.Forms.Button();
            this.btnPhysicianPatientReport = new System.Windows.Forms.Button();
            this.btnExit = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnRoomUtilizationReport
            // 
            this.btnRoomUtilizationReport.Location = new System.Drawing.Point(67, 31);
            this.btnRoomUtilizationReport.Name = "btnRoomUtilizationReport";
            this.btnRoomUtilizationReport.Size = new System.Drawing.Size(287, 34);
            this.btnRoomUtilizationReport.TabIndex = 0;
            this.btnRoomUtilizationReport.Text = "&Room Utilization Report";
            this.btnRoomUtilizationReport.UseVisualStyleBackColor = true;
            this.btnRoomUtilizationReport.Click += new System.EventHandler(this.btnRoomUtilizationReport_Click);
            // 
            // btnPhysicianPatientReport
            // 
            this.btnPhysicianPatientReport.Location = new System.Drawing.Point(67, 71);
            this.btnPhysicianPatientReport.Name = "btnPhysicianPatientReport";
            this.btnPhysicianPatientReport.Size = new System.Drawing.Size(287, 34);
            this.btnPhysicianPatientReport.TabIndex = 1;
            this.btnPhysicianPatientReport.Text = "&Physician-Patient Report";
            this.btnPhysicianPatientReport.UseVisualStyleBackColor = true;
            this.btnPhysicianPatientReport.Click += new System.EventHandler(this.btnPhysicianPatientReport_Click);
            // 
            // btnExit
            // 
            this.btnExit.Location = new System.Drawing.Point(67, 222);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(287, 34);
            this.btnExit.TabIndex = 2;
            this.btnExit.Text = "E&xit";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // frmMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(427, 289);
            this.Controls.Add(this.btnExit);
            this.Controls.Add(this.btnPhysicianPatientReport);
            this.Controls.Add(this.btnRoomUtilizationReport);
            this.Name = "frmMain";
            this.Text = "MVCH DBMS Report Application v1.0";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnRoomUtilizationReport;
        private System.Windows.Forms.Button btnPhysicianPatientReport;
        private System.Windows.Forms.Button btnExit;
    }
}