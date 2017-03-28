' Class Name: PieceWorker
' Name: Matthew Cormier
' Date: 10/02/2015
'
' Description: This class will create worker objects. These objects represent the information of the worker. The class will hold total messages sent, total pay, average pay, and total workers. Objects of this class will
'              hold messages sent, pay, and name of the worker. A method will handle the processing of the pay.
'

Friend Class PieceWorker

#Region "Class property variables"

    ' Declare worker object variables.
    Friend messagesSent As Integer
    Friend workerPay As Decimal = CDec(0.0)
    Friend workerName As String = ""

    ' Declare class variables.
    Protected Shared totalMessages As Integer = 0
    Protected Shared totalPay As Double = 0
    Protected Shared averagePay As Double = 0
    Protected Shared totalWorkers As Integer = 0

#End Region

#Region "Constructors"

    ' Worker object constructor.
    Friend Sub New(ByVal messagesValue As String, ByVal nameValue As String)

        ' Get the messages sent and worker name proporties.
        Me.workerMessages = messagesValue
        Me.Name = nameValue

    End Sub

#End Region

#Region "Class Methods"

    ' METHOD payrollProcessing
    '
    ' This method will calculate the pay of the worker.
    ' Add values to the shared class variables.
    Protected Friend Overridable Sub payrollProcessing()

        ' Declare constants for the array lengths.
        Const NUMBER_OF_TIERS As Integer = 3
        Const NUMBER_OF_RATES As Integer = 4

        ' Declare variable to hold the calculation result.
        Dim result As Double = 0.0
        Dim processingResult As Double = 0.0

        ' Declare rates and tiers singlar arrays
        Dim commissionRates(NUMBER_OF_RATES) As Double
        Dim commissionTiers(NUMBER_OF_TIERS) As Integer

        ' Populate arrays.
        commissionRates = {0.15,
                           0.18,
                           0.21,
                           0.25
                           }

        commissionTiers = {0,
                          200,
                          400,
                          600
                          }

        ' For every tier...
        For tierCounter As Integer = 1 To NUMBER_OF_TIERS

            ' If the number of messages is greater than or equal to the highiest tier.
            If messagesSent >= commissionTiers(NUMBER_OF_TIERS) Then

                ' Calculate the pay based on the highiest tier.
                result = messagesSent * commissionRates(NUMBER_OF_RATES - 1)

                ' Make this the last loop.
                tierCounter = NUMBER_OF_TIERS

                ' Add the pay to the total pay.
                totalPay += result

            Else

                ' If the number of messages sent is less than the current tier.
                If messagesSent < commissionTiers(tierCounter) Then

                    ' Calculate the pay based on the current tier.
                    result = messagesSent * commissionRates(tierCounter - 1)

                    ' Make this the last loop.
                    tierCounter = NUMBER_OF_TIERS

                End If

            End If

        Next


        ' Call the proccessing function to calculate the worker's pay.
        processingResult = Math.Round(result, 2)

        workerPay = CDec(processingResult)

        ' Assign result to worker pay class variable.
        'workerPay = result

        ' Add to totals.
        totalPay += processingResult
        totalMessages += messagesSent
        totalWorkers += 1

    End Sub

    ' Override ToString function.
    Public Overrides Function ToString() As String

        ' Return worker information for output.
        Return workerName & " - " & workerMessages & " messages - " & workerPay

    End Function

#End Region

#Region "Property Procedures"

    ' Worker's pay property.
    Protected Friend ReadOnly Property Pay() As Decimal


        ' Get the pay value.
        Get
            Return workerPay
        End Get

    End Property

    ' Worker name property.
    Protected Friend Property Name() As String

        ' Get the name value.
        Get
            Return workerName
        End Get

        ' Validate the name input, if invalid, set name input to workerName.
        Set(ByVal setWorkerNameValue As String)

            ' Declare counter and return variables.
            Dim letterCounter As Integer = 0
            Dim nameError As Boolean = False

            ' If thew input is not blank.
            If setWorkerNameValue IsNot "" Then

                ' For each character in the input.
                For characterCounter As Integer = 0 To setWorkerNameValue.Length - 1

                    ' Check if the current character is a letter or not.
                    If Char.IsLetter(setWorkerNameValue.Chars(characterCounter)) Then

                        ' Increment the letter counter.
                        letterCounter = letterCounter + 1

                    End If

                Next

                ' If there were 2 or more letters.
                If letterCounter >= 2 Then

                    workerName = setWorkerNameValue

                Else

                    ' Throw an out of range exception to be caught in the presentation tier.
                    Throw New ArgumentOutOfRangeException("Worker Name", "Worker name must be atleast 2 alphabetical characters.")

                End If

            Else

                ' Throw a null exception to be caught in the presentation tier.
                Throw New ArgumentNullException("Worker Name", "Worker name must not be null.")

            End If

        End Set

    End Property

    ' Property for the number of messages sent by a worker.
    Protected Friend Property WorkerMessages() As String

        ' Get messages sent value.
        Get
            Return CStr(messagesSent)
        End Get

        ' Validate the messages sent input, if valid, set messagesSent.
        Set(ByVal setMessagesSentValue As String)

            Dim messageError As Boolean = False

            ' Declare variable to hold the input.
            Dim messageTest As Integer = 0

            Const MIN_MESSAGES As Integer = 1
            Const MAX_MESSAGES As Integer = 2000

            ' If the input is not blank.
            If setMessagesSentValue IsNot "" Then

                ' Try to convert the input into an integer.
                If Integer.TryParse(setMessagesSentValue, messageTest) And messageTest >= MIN_MESSAGES And messageTest <= MAX_MESSAGES Then

                    ' If the conversion works, return true.
                    messagesSent = messageTest

                Else

                    ' Throw an out of range exception to be caught in the presentation tier.
                    Throw New ArgumentOutOfRangeException("Messages Sent", "Messages sent must be a whole number between 1-2000.")

                End If

            Else

                ' Throw a null exception to be caught in the presentation tier.
                Throw New ArgumentNullException("Messages Sent", "Messages sent must not be null.")

            End If

        End Set
    End Property

    ' Total pay property.
    Protected Friend Shared ReadOnly Property TotalPayRoll() As Decimal

        Get
            Return CDec(totalPay)
        End Get

    End Property

    ' Total messages sent property.
    Protected Friend Shared ReadOnly Property TotalMessagesProcessed As Integer

        Get
            Return totalMessages
        End Get

    End Property

    ' Average pay property.
    Protected Friend Shared ReadOnly Property AverageWorkerPay As Decimal

        Get
            Return CDec(averagePay)
        End Get

    End Property

    ' Total workers property.
    Protected Friend Shared ReadOnly Property TotalWorkersProcessed As Integer

        Get
            Return totalWorkers
        End Get

    End Property

#End Region
End Class
