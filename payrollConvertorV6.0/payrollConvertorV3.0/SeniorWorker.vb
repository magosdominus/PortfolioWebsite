' Class Name: SeniorWorker
' Name: Matthew Cormier
' Date: 10/11/2015
'
' Description: This class will inherit the proporties from the pice worker class. This class will be used to create worker objects for senior workers. The payroll processing methods from the
' piece worker class will be overridden for the business rules involving the senior workers.
'

Friend Class SeniorWorker
    Inherits PieceWorker

#Region "Variables"

    ' Inherit variables from PieceWorker

#End Region

#Region "Constructos"

    ' Worker object constructor.
    Friend Sub New(ByVal messagesValue As Integer, ByVal workerName As String)

        ' Build object.
        MyBase.New(CStr(messagesValue), workerName)

        ' Get the messages sent and worker name proporties.
        MyBase.messagesSent = CInt(workerMessages)
        MyBase.workerName = name


    End Sub

#End Region

#Region "Class Methods"

    ' calculation method
    Protected Friend Overrides Sub payrollProcessing()

        ' Declare constants for the array lengths.
        Const NUMBER_OF_TIERS As Integer = 5
        Const NUMBER_OF_RATES As Integer = 4
        Const BASE_PAY As Double = 200.0

        ' Declare variable to hold the calculation result.
        Dim result As Double = 0.0
        Dim processingResult As Double = 0.0

        ' Declare rates and tiers singlar arrays
        Dim commissionRates(NUMBER_OF_RATES) As Double
        Dim commissionTiers(NUMBER_OF_TIERS) As Integer

        ' Populate arrays.
        commissionRates = {0.1,
                           0.12,
                           0.13,
                           0.14,
                           0.15
                           }

        commissionTiers = {0,
                          199,
                          399,
                          599,
                          799
                          }

        ' For every tier...
        For tierCounter As Integer = 0 To NUMBER_OF_TIERS - 1

            ' If the number of messages is greater than or equal to the highiest tier.
            If messagesSent > commissionTiers(tierCounter) Then

                ' Calculate the pay based on the highiest tier.
                result = messagesSent * commissionRates(tierCounter) + BASE_PAY

                ' Add the pay to the total pay.
                totalPay += result

            End If

        Next


        ' Call the proccessing function to calculate the worker's pay.
        processingResult = Math.Round(result, 2)

        workerPay = CDec(processingResult)

        ' Add to totals.
        totalPay += processingResult
        totalMessages += messagesSent
        totalWorkers += 1

    End Sub

    ' Overide the ToString function.
    Public Overrides Function ToString() As String

        ' Return worker information for output.
        Return workerName & " - " & workerMessages & " messages - " & workerPay & " - Senior Worker"

    End Function

#End Region

#Region "Proporties"

    ' Inherit proporties from PieceWorker.

#End Region

End Class
