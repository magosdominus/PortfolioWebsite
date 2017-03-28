Imports CclECILib

Public Class frmInterface

    ' Declarations
    Dim ECI As CclOECI = New CclOECI
    Dim Connect As CclOConn = New CclOConn
    Dim Flow As CclOFlow = New CclOFlow
    Dim Buffer As CclOBuf = New CclOBuf
    Dim UOW As CclOUOW = New CclOUOW

    Dim DFHCOMMAREA As String   ' Output from buffer
    Dim LK_COURSE As String     ' Course code from buffer
    Dim LK_DESC As String       ' Description from buffer

    Const TARGET_LENGTH As Integer = 8 ' Length need for valid course code
    Const COMMAREA_LENGTH As Integer = 30

    Dim errorMessage As String 'error message that is set by validation functions.

    ' On load, set connection
    Private Sub frmInterface_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles MyBase.Load

        Try

            ' Configure connection to infinity CICS server
            Connect.Details("Infinity", "STUDENT", "STUDENT")

        Catch ex As Exception

            Debug.Write("Connection Error: " + ex.Message)

        End Try

    End Sub

    ' Validate input and submit to CICS program
    Private Sub btnSearch_Clicked(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSearch.Click

        Try

            errorMessage = ""

            ' TODO: validate input
            If (validateCourseCode(Me.txtCourseCode.Text) = True) Then

                Me.txtError.Text = errorMessage

                ' Fill buffer
                Buffer.SetString(Me.txtCourseCode.Text.ToString)
                Buffer.SetLength(COMMAREA_LENGTH)

                ' Attempt to execute CICS 
                Connect.Link(Flow, "ZZPRGCC", Buffer, UOW)

                ' Recieve and parse output                
                ' Display error message or result
                If (String.Compare(Buffer.ExtractString(8, 16), "COURSE NOT FOUND") = 0) Then

                    errorMessage = "Course '" + Buffer.ExtractString(0, 8) + "' not found in database."
                    txtDescription.Text = ""
                    txtError.Text = errorMessage

                ElseIf (Buffer.ExtractString(0, 7) = "SQLERR:") Then

                    errorMessage = "SQL error: " + Buffer.ToString
                    txtDescription.Text = ""
                    txtError.Text = errorMessage

                Else

                    LK_COURSE = Buffer.ExtractString(0, 7)

                    LK_DESC = Buffer.ExtractString(8)

                    txtDescription.Text = "Course description found for " + LK_COURSE + ": " + LK_DESC

                End If

                ' UOW.commit to end the transaction 
                UOW.Commit(Flow)

            Else

                Me.txtError.Text = errorMessage

            End If

        Catch ex As Exception

            errorMessage = "Unknown Error: " + ex.Message
            Me.txtError.Text = errorMessage

        End Try

    End Sub

    ' Close form
    Private Sub btnExit_Click(sender As Object, e As EventArgs) Handles btnExit.Click

        Me.Close()

    End Sub

    ' Validates passed course codes.
    '
    ' @param string, the coursecode
    ' @return boolean, true is valid
    Private Function validateCourseCode(courseCode As String) As Boolean

        Dim isValid As Boolean = False

        If (String.IsNullOrEmpty(courseCode) = False) Then

            ' Check if the course code is the correctr length.
            If (courseCode.Length = TARGET_LENGTH) Then

                ' Validate formatting, XXXX9999
                If (Char.IsLetter(courseCode.Chars(0)) And Char.IsLetter(courseCode.Chars(1)) And Char.IsLetter(courseCode.Chars(2)) And Char.IsLetter(courseCode.Chars(3))) Then

                    If (IsNumeric(courseCode.Chars(4))) And IsNumeric(courseCode.Chars(5)) And IsNumeric(courseCode.Chars(6)) And IsNumeric(courseCode.Chars(7)) Then

                        isValid = True

                        errorMessage = ""

                        Return isValid

                    Else

                        errorMessage = "Input Error: The last four course code characters must be numeric. You entered '" + courseCode + "'. Correct formatting would look like 'XXXX9999'."
                        isValid = False

                        Return isValid

                    End If

                Else

                        errorMessage = "Input Error: The first four course code characters must be alphabetic. You entered '" + courseCode + "'. Correct formatting would look like 'XXXX9999'."
                    isValid = False

                    Return isValid
                End If

            Else

                errorMessage = "Input Error: Course code must be exactly 8 characters long. You entered an input of " & Len(courseCode).ToString & " characters."
                isValid = False

                Return isValid
            End If

        Else

            errorMessage = "Input Error: Course code cannot be null."
            Return isValid

        End If

        Return isValid

    End Function
End Class
