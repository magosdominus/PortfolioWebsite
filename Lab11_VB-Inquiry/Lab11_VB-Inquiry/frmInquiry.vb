
' Description: Inquiry/search EPI application. Retreives student records.
'
' @author Matthew Cormier
' @version 1.0
' @since 03/03/2017

'Library definitions CICS EPI
Imports CclEPILib

Public Class frmInquiry

    ' PROPERTIES
    Private isConnected As Boolean = False

    'CLASS LEVEL - Connection objects required
    Dim EPI As CclOEPI ' external Presentation Interface definition
    Dim Terminal As CclOTerminal ' you need a Terminal definition
    Dim Session As CclOSession ' a Session, or transaction
    Dim Screen As CclOScreen ' screen simulates the screen...
    Dim map As CclOMap ' map, as in BMS map definition
    Dim Field As CclOField ' and a generic field definition..

    ' FUNCTIONS & SUBS

    Private Sub frmInquiry_Load(sender As Object, e As EventArgs) Handles MyBase.Load

        ' Connect to the server
        If (connectToServer()) Then

            prepareConnected()

        Else

            prepareDisconnected()
            txtError.Text = "Error: Failed to connect to the Infinity server. Connection is required, please retry to connect."

        End If

    End Sub

    Sub prepareConnected()

        btnClose.Enabled = False
        btnClear.Enabled = False
        btnInquire.Enabled = True
        txtStudentNumber.Enabled = True

        btnConnect.Text = "&Disconnect"
        TTLForm.SetToolTip(btnConnect, "Click to disconnect from the Infinity server.")

        isConnected = True

    End Sub

    Sub prepareDisconnected()

        btnClose.Enabled = True

        btnConnect.Text = "&Connect"
        TTLForm.SetToolTip(btnConnect, "Click to connect to the Infinity server.")

        btnInquire.Enabled = False
        txtStudentNumber.Enabled = False

        isConnected = False

    End Sub

    Function connectToServer() As Boolean

        ' Attempt to connect to infinity 
        Try

            'INSTANCE LEVEL – Establish connection and initiate transaction
            EPI = New CclOEPI
            Terminal = New CclOTerminal 'Create a terminal object
            Session = New CclOSession 'Create a session object (defaults to synchronous)
            map = New CclOMap 'Create a map object
            Terminal.Connect("INFINITY", "", "") 'Connect to CICS
            Terminal.Start(Session, "XX02", "") 'Start transaction
            Screen = Terminal.Screen 'Scrape the screen from the terminal

            'Check to see if the screen matches the map and populate map object if so
            If (map.Validate(Screen, MAP1)) Then
                'Screen data is valid meaning the application has started correctly
                'Set message for the user
                txtError.Text = "Connection successful. Please enter a student number"
            Else
                'Unexpected screen data…alert the user
                Throw New Exception("Unexpected screen data.")
            End If

            ' Success
            Return True

        Catch ex As Exception
            ' Failed
            txtError.Text = "Failed to connect to the Infinity server. Connection is required, please retry to connect. " + Environment.NewLine + Environment.NewLine + ex.Message
            Return False
        End Try

    End Function

    Function disconnectFromServer() As Boolean

        ' Attempt to disconnect from infinity 
        Try

            'INSTANCE LEVEL – terminate the CICS app and disconnect terminal
            Screen.SetAID(CclAIDKeys.cclPF9) 'place F9 into the screen buffer to exit
            Terminal.Send(Session) ' send the F9
            Terminal.Disconnect() ' finally disconnect
            Field = Nothing
            Screen = Nothing
            Session = Nothing
            Terminal = Nothing
            EPI = Nothing

            txtError.Text = "Disconnect successful. You may now safely terminate the application."
            ' Success
            Return True

        Catch ex As Exception
            ' Failed
            txtError.Text = "Failed to properly disconnect from the Infinity server. " + Environment.NewLine + Environment.NewLine + ex.Message
            Return False
        End Try

    End Function

    Private Sub btnClear_Click(sender As Object, e As EventArgs) Handles btnClear.Click

        ' Clear input and output
        txtStudentNumber.Text = ""
        txtCourse1.Text = ""
        txtCourse2.Text = ""
        txtCourse3.Text = ""
        txtCourse4.Text = ""
        txtCourse5.Text = ""
        txtAddressLine1.Text = ""
        txtAddressLine2.Text = ""
        txtAddressLine3.Text = ""
        txtName.Text = ""
        txtPostalCode.Text = ""
        txtPhoneNumber.Text = ""
        txtError.Text = ""

    End Sub

    Private Sub txtStudentNumber_TextChanged(sender As Object, e As EventArgs) Handles txtStudentNumber.TextChanged

        ' If there is input, enable the clear button.
        If (txtStudentNumber.TextLength > 0) Then

            btnClear.Enabled = True

        Else

            btnClear.Enabled = False

        End If

    End Sub

    Private Sub btnClose_Click(sender As Object, e As EventArgs) Handles btnClose.Click

        ' EXIT
        Close()

    End Sub

    Private Sub btnConnect_Click(sender As Object, e As EventArgs) Handles btnConnect.Click

        If (isConnected = True) Then

            ' Disconnect to the server
            If (disconnectFromServer()) Then

                prepareDisconnected()

            Else

                prepareDisconnected()

            End If
        Else

            ' Connect to the server
            If (connectToServer()) Then

                prepareConnected()

            Else

                prepareDisconnected()

            End If
        End If

    End Sub

    Private Function isValidStudentNumber(stuNumber As String) As Boolean

        Const LENGTH As Integer = 7
        Dim number As Integer = 0

        ' Check length
        If (stuNumber.Length = LENGTH) Then

            ' Check numeric
            If (Integer.TryParse(stuNumber, number)) Then
                txtError.Text = ""
                Return True
            Else

                txtError.Text = "Student number must be numeric."
                Return False
            End If
        Else
            txtError.Text = "Student number must be 7 characters long."
            Return False
        End If

    End Function

    Private Sub btnInquire_Click(sender As Object, e As EventArgs) Handles btnInquire.Click

        txtCourse1.Text = ""
        txtCourse2.Text = ""
        txtCourse3.Text = ""
        txtCourse4.Text = ""
        txtCourse5.Text = ""
        txtAddressLine1.Text = ""
        txtAddressLine2.Text = ""
        txtAddressLine3.Text = ""
        txtName.Text = ""
        txtPostalCode.Text = ""
        txtPhoneNumber.Text = ""
        txtError.Text = ""

        Try
            ' Validate input
            If (isValidStudentNumber(txtStudentNumber.Text)) Then

                'INSTANCE LEVEL – Inquire for student by injecting student number input into map, send it to the application and handle the response
                'Set the value in txtStudentNumber to the STUNUM field on the inquiry screen
                Field = map.FieldByName(MAP1_STUNUM)
                Field.SetText(txtStudentNumber.Text)

                'Start a session and invoke the inquiry program via transaction code
                Terminal.Start(Session, "XX02", "")
                Screen = Terminal.Screen
                map = New CclOMap

                'prepareDisconnected()

                'OK, we have the results back..
                'Check to see if the screen matches the map and populate map object if so
                If (map.Validate(Screen, MAP1)) Then

                    'Screen data is valid, we can now extract data from fields using our map definition
                    'Get the message field and set the required label
                    Field = map.FieldByName(MAP1_MESSAGE)
                    'Check message to determine action required
                    If (Field.Text.Substring(0, 12) = "RECORD FOUND") Then

                        Field = map.FieldByName(MAP1_MESSAGE)
                        txtError.Text = "Record found! " + Environment.NewLine + Environment.NewLine + Field.Text

                        'use data from map field to populate output label for student name on form
                        Field = map.FieldByName(MAP1_SNAME)
                        txtName.Text = Field.Text
                        '...many many many more of these statements... one for each field

                        Field = map.FieldByName(MAP1_CRS1)
                        txtCourse1.Text = Field.Text

                        Field = map.FieldByName(MAP1_CRS2)
                        txtCourse2.Text = Field.Text

                        Field = map.FieldByName(MAP1_CRS3)
                        txtCourse3.Text = Field.Text

                        Field = map.FieldByName(MAP1_CRS4)
                        txtCourse4.Text = Field.Text

                        Field = map.FieldByName(MAP1_CRS5)
                        txtCourse5.Text = Field.Text

                        Field = map.FieldByName(MAP1_ADDR1)
                        txtAddressLine1.Text = Field.Text

                        Field = map.FieldByName(MAP1_ADDR2)
                        txtAddressLine2.Text = Field.Text

                        Field = map.FieldByName(MAP1_ADDR3)
                        txtAddressLine3.Text = Field.Text

                        Field = map.FieldByName(MAP1_POSTC1)
                        txtPostalCode.Text = Field.Text

                        Field = map.FieldByName(MAP1_POSTC2)
                        txtPostalCode.Text = txtPostalCode.Text + Field.Text

                        Field = map.FieldByName(MAP1_PHONE1)
                        txtPhoneNumber.Text = Field.Text

                        Field = map.FieldByName(MAP1_PHONE2)
                        txtPhoneNumber.Text = txtPhoneNumber.Text + Field.Text

                        Field = map.FieldByName(MAP1_PHONE3)
                        txtPhoneNumber.Text = txtPhoneNumber.Text + Field.Text

                    ElseIf (Field.Text.Substring(0, 22) = "STUDENT DOES NOT EXIST") Then 'logic to handle each message from the application appropriately

                        txtError.Text = "Student record not found." + Environment.NewLine + Environment.NewLine + Field.Text.Substring(0, 22)

                    End If
                Else
                    Throw New Exception("Map is invalid.")
                End If

            End If
        Catch ex As Exception
            txtError.Text = "Failed to inquire student record. " + Environment.NewLine + Environment.NewLine + ex.Message
        End Try
    End Sub

End Class
