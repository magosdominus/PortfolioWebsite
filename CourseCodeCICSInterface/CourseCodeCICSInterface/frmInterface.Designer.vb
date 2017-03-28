<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frmInterface
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.txtDescription = New System.Windows.Forms.TextBox()
        Me.txtCourseCode = New System.Windows.Forms.TextBox()
        Me.btnSearch = New System.Windows.Forms.Button()
        Me.btnExit = New System.Windows.Forms.Button()
        Me.lblDescription = New System.Windows.Forms.Label()
        Me.lblCourseCode = New System.Windows.Forms.Label()
        Me.lblError = New System.Windows.Forms.Label()
        Me.txtError = New System.Windows.Forms.TextBox()
        Me.SuspendLayout()
        '
        'txtDescription
        '
        Me.txtDescription.BackColor = System.Drawing.SystemColors.ScrollBar
        Me.txtDescription.Enabled = False
        Me.txtDescription.Location = New System.Drawing.Point(12, 29)
        Me.txtDescription.Multiline = True
        Me.txtDescription.Name = "txtDescription"
        Me.txtDescription.Size = New System.Drawing.Size(267, 129)
        Me.txtDescription.TabIndex = 0
        '
        'txtCourseCode
        '
        Me.txtCourseCode.Location = New System.Drawing.Point(308, 29)
        Me.txtCourseCode.MaxLength = 8
        Me.txtCourseCode.Name = "txtCourseCode"
        Me.txtCourseCode.Size = New System.Drawing.Size(174, 20)
        Me.txtCourseCode.TabIndex = 1
        '
        'btnSearch
        '
        Me.btnSearch.Location = New System.Drawing.Point(308, 315)
        Me.btnSearch.Name = "btnSearch"
        Me.btnSearch.Size = New System.Drawing.Size(75, 23)
        Me.btnSearch.TabIndex = 2
        Me.btnSearch.Text = "&Search"
        Me.btnSearch.UseVisualStyleBackColor = True
        '
        'btnExit
        '
        Me.btnExit.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.btnExit.Location = New System.Drawing.Point(407, 315)
        Me.btnExit.Name = "btnExit"
        Me.btnExit.Size = New System.Drawing.Size(75, 23)
        Me.btnExit.TabIndex = 3
        Me.btnExit.Text = "E&xit"
        Me.btnExit.UseVisualStyleBackColor = True
        '
        'lblDescription
        '
        Me.lblDescription.AutoSize = True
        Me.lblDescription.Location = New System.Drawing.Point(12, 13)
        Me.lblDescription.Name = "lblDescription"
        Me.lblDescription.Size = New System.Drawing.Size(96, 13)
        Me.lblDescription.TabIndex = 4
        Me.lblDescription.Text = "Course Description"
        '
        'lblCourseCode
        '
        Me.lblCourseCode.AutoSize = True
        Me.lblCourseCode.Location = New System.Drawing.Point(305, 13)
        Me.lblCourseCode.Name = "lblCourseCode"
        Me.lblCourseCode.Size = New System.Drawing.Size(129, 13)
        Me.lblCourseCode.TabIndex = 5
        Me.lblCourseCode.Text = "Course Code (XXXX9999)"
        '
        'lblError
        '
        Me.lblError.AutoSize = True
        Me.lblError.Location = New System.Drawing.Point(9, 193)
        Me.lblError.Name = "lblError"
        Me.lblError.Size = New System.Drawing.Size(80, 13)
        Me.lblError.TabIndex = 6
        Me.lblError.Text = "Error Messages"
        '
        'txtError
        '
        Me.txtError.BackColor = System.Drawing.SystemColors.ScrollBar
        Me.txtError.Location = New System.Drawing.Point(12, 209)
        Me.txtError.Multiline = True
        Me.txtError.Name = "txtError"
        Me.txtError.Size = New System.Drawing.Size(267, 129)
        Me.txtError.TabIndex = 7
        '
        'frmInterface
        '
        Me.AcceptButton = Me.btnSearch
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.CancelButton = Me.btnExit
        Me.ClientSize = New System.Drawing.Size(509, 361)
        Me.Controls.Add(Me.txtError)
        Me.Controls.Add(Me.lblError)
        Me.Controls.Add(Me.lblCourseCode)
        Me.Controls.Add(Me.lblDescription)
        Me.Controls.Add(Me.btnExit)
        Me.Controls.Add(Me.btnSearch)
        Me.Controls.Add(Me.txtCourseCode)
        Me.Controls.Add(Me.txtDescription)
        Me.MaximizeBox = False
        Me.Name = "frmInterface"
        Me.Text = "Course Code Search - CICS Interface"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents txtDescription As TextBox
    Friend WithEvents txtCourseCode As TextBox
    Friend WithEvents btnSearch As Button
    Friend WithEvents btnExit As Button
    Friend WithEvents lblDescription As Label
    Friend WithEvents lblCourseCode As Label
    Friend WithEvents lblError As Label
    Friend WithEvents txtError As TextBox
End Class
