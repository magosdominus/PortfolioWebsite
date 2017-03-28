
' Program Name: commissionCalculator v6.0
' Author: Matthew Cormier
' Date: 12/11/2015
'
' Description: This windows form application will prompt the user to enter workers' names and number of messages sent by each worker.
' The application will validate the inputs based on the business rules. If the inputs are invalid, the application will display an
' error message. Once valid, the application will calculate the commission pay for the worker based on the amount of messages sent.
' At any time the user may open a summary form to view totals and average pay of the workers entered since runtime of the application. The 
' user may choose if the worker is a regular worker or a senior worker. Senior worker calculations will be processed by the senior worker class.
' The user may also click a clear button to reset the fields on the main form. All validation and processing will be handled in the Piece 
' Worker class. A list of employees and summary fields will be in an employee list and summary tabs. Employee list will allow the viewing and removing
' of workers and the summary form will summarize the total messages sent, total pay, and average pay. The program will write all events to a text log.
' An about message, viewing the log file, refreshing the tabs, and exiting the form will be avialable as buttons in a menu at the top of the form.
' A status bar will output the date, time, and last action when ever a change is made to the employee list.
'



Public Class frmMain

    ' Program text log and variable to hold the log output.
    Dim logOutput As String = ""
    Dim programLog As System.IO.StreamWriter

    ' File path for the log. Change to desired log save path.
    Dim filePath As String = "c:\scripts\programLog.txt"

    Dim currentDate As String = DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss")

    ' When the about menu button is clicked.
    Private Sub mnuAbout_Click(sender As Object, e As RoutedEventArgs)

        ' Set current date and output an about message.
        currentDate = DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss")
        MessageBox.Show("Application: payrollConverter" & Environment.NewLine & "Author: Matthew Cormier" & Environment.NewLine & "Date: " & currentDate & Environment.NewLine & "Description: This application allows for worker data entry for incInc.", "About")

    End Sub

    ' When the log menu button is clicked.
    Private Sub mnuLog_Click(sender As Object, e As RoutedEventArgs)

        ' Open the program log.
        Process.Start(filePath)

    End Sub

    ' When the refresh menu button is clicked.
    Private Sub mnuRefresh_Click(sender As Object, e As RoutedEventArgs)

        'Write event to the log.
        logOutput = DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") & " Summary/Employee List tabs refreshed."
        programLog.WriteLine(logOutput)

        ' Clear the employee list box.
        lstEmployees.Items.Clear()

        ' For each worker object in the worker collection, add the object.ToString() result to the employee list box.
        For Each employee As PieceWorker In employeeList

            lstEmployees.Items.Add(employee.ToString())

        Next

        ' Variables to hold calculation results.
        Dim summaryTotalPay As Double = 0
        Dim summaryTotalMessages As Integer = 0

        ' For each worker in the worker collection...
        For workerCounter = 0 To employeeList.Count() - 1

            ' Add the workers messages sents and thier pay to the total result variables.
            summaryTotalPay += employeeList(workerCounter).workerPay
            summaryTotalMessages += employeeList(workerCounter).messagesSent

        Next

        ' Display the total messages sent, total pay, and average pay.

        ' Check if the summary is zero to prevent dividing by zero.
        If summaryTotalPay = 0 Then

            ' Display default.
            lblAverageResult.Content = "$0"

        Else

            ' Display result.
            lblAverageResult.Content = "$" & Math.Round(summaryTotalPay / employeeList.Count, 2)

        End If

        lblMessagesResult.Content = summaryTotalMessages
        lblTotalPayResult.Content = "$" & Math.Round(summaryTotalPay, 2)

    End Sub

    ' When the exit menu button is clicked.
    Private Sub mnuExit_Click(sender As Object, e As RoutedEventArgs)

        ' Close the form.
        Me.Close()

        ' END 

    End Sub

#Region "Main Tab"

    ' When the main form loads.
    Private Sub frmMain_Load(sender As Object, e As EventArgs) Handles MyBase.Loaded

        optRegular.IsChecked = True
        txtMessages.IsEnabled = False
        tbSummary.IsEnabled = False
        tbEmployeeList.IsEnabled = False

        ' Write that the program has started and the date to the log.
        programLog = My.Computer.FileSystem.OpenTextFileWriter(filePath, True)

        logOutput = "Program started: " & DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss")
        programLog.WriteLine(logOutput)

        lblDate.Text = currentDate

    End Sub

    ' When the form closes.
    Private Sub frmMain_Closed(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Closed

        ' Close the streamwriter.
        programLog.Close()

    End Sub

    ' When the clear button is clicked.
    Private Sub btnClear_Click(sender As Object, e As EventArgs) Handles btnClear.Click

        ' Reset textboxes, labels, and button values.
        txtMessages.Text = ""
        txtName.Text = ""
        lblPayResult.Content = "$" & "0.00"
        txtMessages.IsEnabled = False
        txtName.Focus()
        optRegular.IsChecked = True


        lblProduction.Foreground = Brushes.Black
        lblProduction.Content = ""

        optRegular.IsEnabled = True
        optSenior.IsEnabled = True

    End Sub

    ' When the calculate button is click.
    Private Sub btnCalculate_Click(sender As Object, e As EventArgs) Handles btnCalculate.Click

        ' Create new Piece worker object.
        Dim worker As PieceWorker

        ' Constants that represent the lower and upper production. 
        Const UPPER_PRODUCTION As Integer = 1200
        Const LOWER_PRODUCTION As Integer = 300

        ' Try to create the worker object and call the class production method.
        Try

            ' For the regular worker.
            If optRegular.IsChecked = True Then

                ' Send inputs to the object.
                worker = New PieceWorker(txtMessages.Text, txtName.Text)

                ' Write worker information to the log.
                logOutput = DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") & " " & worker.ToString()
                programLog.WriteLine(logOutput)

                ' Call the processing method.
                worker.payrollProcessing()

                ' Format and display the result from teh class.
                lblPayResult.Content = "$" & worker.workerPay

                ' Enable the summary button.
                tbSummary.IsEnabled = True

                tbEmployeeList.IsEnabled = True

                ' Disable radiobuttons and messages textbox.
                txtMessages.IsEnabled = False
                optRegular.IsEnabled = False
                optSenior.IsEnabled = False

                ' If messages sent is over the upper production.
                If txtMessages.Text > UPPER_PRODUCTION Then

                    ' Display message.
                    lblProduction.Foreground = Brushes.Blue
                    lblProduction.Content = worker.Name & " should be considered for promotion or recognition. "

                End If

                ' Add the worker object to a worker collection that will be used for both the summary and employee list forms.
                employeeList.Add(worker)

                currentDate = DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss")

                lblDate.Text = currentDate
                lblStatus.Text = worker.Name & " added to the employee list."

                ' For the senior worker.
            ElseIf optSenior.IsChecked = True Then

                ' Disable radiobuttons and messages textbox.
                txtMessages.IsEnabled = False
                optRegular.IsEnabled = False
                optSenior.IsEnabled = False

                ' Create senior worker object.
                worker = New SeniorWorker(txtMessages.Text, txtName.Text)

                ' Write worker information to the log.
                logOutput = DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") & " " & worker.ToString()
                programLog.WriteLine(logOutput)

                ' Call the class processing method.
                worker.payrollProcessing()

                ' Format and display result.
                lblPayResult.Content = "$" & worker.workerPay

                ' Enable the summary button.
                tbSummary.IsEnabled = True

                ' If messages sent is under the lower production.
                If txtMessages.Text < LOWER_PRODUCTION Then

                    ' Display messages.
                    lblProduction.Foreground = Brushes.Red
                    lblProduction.Content = worker.workerName & " is falling behind in production. "

                End If

                ' Add the worker object to a worker collection that will be used for both the summary and employee list forms.
                employeeList.Add(worker)

                lblDate.Text = currentDate
                lblStatus.Text = worker.Name & " added to the employee list."

            End If

            ' Catch any thrown null exceptions and display an error message.
        Catch nullException As ArgumentNullException

            MessageBox.Show(nullException.Message & vbCrLf & vbCrLf & "Please try again.", "Input Error!")

            ' Catch any thrown out of range exceptions and display an error message.
        Catch outOfException As ArgumentOutOfRangeException

            MessageBox.Show(outOfException.Message & vbCrLf & vbCrLf & "Please try again.", "Input Error!")

            ' Catch any unknown exceptions thrown during runtime and display error message.
        Catch unkownException As Exception

            MessageBox.Show("Unknown run time error.", "Unkown Error!")

        End Try
    End Sub

    ' When the value in the name textbox is changed.
    Private Sub txtName_TextChanged(sender As Object, e As EventArgs) Handles txtName.TextChanged

        ' Clear the messages sent textbox and result label. Enable the messages sent textbox.
        txtMessages.Text = ""
        lblPayResult.Content = "$0.00"
        txtMessages.IsEnabled = True

        optRegular.IsEnabled = True
        optSenior.IsEnabled = True

    End Sub

#End Region

#Region "Employee List Tab"

    ' When the user clicks the remove worker button.
    Private Sub btnRemoveWorker_Click(sender As Object, e As RoutedEventArgs) Handles btnRemoveWorker.Click

        ' Check if a worker is selected.
        If Me.lstEmployees.SelectedItems.Count > 0 Then

            ' Remove the selected worker.
            Dim selectedWorker As Integer = lstEmployees.SelectedIndex

            lblDate.Text = currentDate
            lblStatus.Text = CStr(employeeList.ElementAt(selectedWorker).Name) & " removed from the employee list."

            employeeList.RemoveAt(selectedWorker)


        Else

            ' Else when no selection, display an error message.
            MessageBox.Show("Please select a worker from the list.", "No Worker Selected!")

        End If

    End Sub

    ' When the user clicks the remove all button.
    Private Sub btnRemoveAll_Click(sender As Object, e As RoutedEventArgs) Handles btnRemoveAll.Click

        ' Clear the worker collection.
        employeeList.Clear()

        lblDate.Text = currentDate
        lblStatus.Text = "All workers removed from the employee list."

    End Sub

#End Region

#Region "Summary Tab"

#End Region

End Class

