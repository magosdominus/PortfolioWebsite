namespace MVCHReportApplication
{
    partial class frmRoomUtilizationReport
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
            this.RoomUtilizationViewer = new CrystalDecisions.Windows.Forms.CrystalReportViewer();
            this.SuspendLayout();
            // 
            // RoomUtilizationViewer
            // 
            this.RoomUtilizationViewer.ActiveViewIndex = -1;
            this.RoomUtilizationViewer.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.RoomUtilizationViewer.Cursor = System.Windows.Forms.Cursors.Default;
            this.RoomUtilizationViewer.Dock = System.Windows.Forms.DockStyle.Fill;
            this.RoomUtilizationViewer.Location = new System.Drawing.Point(0, 0);
            this.RoomUtilizationViewer.Name = "RoomUtilizationViewer";
            this.RoomUtilizationViewer.Size = new System.Drawing.Size(284, 261);
            this.RoomUtilizationViewer.TabIndex = 0;
            // 
            // frmRoomUtilizationReport
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.RoomUtilizationViewer);
            this.Name = "frmRoomUtilizationReport";
            this.Text = "Room Utilization Report";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.ResumeLayout(false);

        }

        #endregion

        private CrystalDecisions.Windows.Forms.CrystalReportViewer RoomUtilizationViewer;
    }
}