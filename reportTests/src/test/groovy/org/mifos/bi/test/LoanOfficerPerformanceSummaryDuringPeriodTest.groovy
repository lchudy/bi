package org.mifos.bi.test

import org.junit.Test

class LoanOfficerPerformanceSummaryDuringPeriodTest {

    def reportName = 'Loan Officer Performance Summary (During Period)'
    def branch = ['Branch:', 'br2']
    def detailsHeader = ['Name', 'Date Joined', 'Total Groups Formed', 'Total Loans Disbursed', 'Total amt of Loans Disbursed',
            'Total amt of Loans Repaid', 'Total # new Savings Accounts', 'Total Clients Recruited', 'Total amt Savings Deposits',
            'Total amt Savings Withdrawals', '# of Dropouts']

    def detailsDuringPeriodBr2LOxxx = ['br2 LO xxx', '2010-07-20', '3', '1', '3000.0000', '78.0000', '5', '3', '1977.0000', '0.0000', '0']
    def totalDuringPeriod = ['Total During Period', '3', '1', '3000.0000', '78.0000', '5', '3', '1977.0000', '0.0000', '0']
    def grandTotalDuringPeriod = ['Grand Total During Period', '3', '1', '3000.0000', '78.0000', '5', '3', '1977.0000', '0.0000', '0']

    def pageFooter = ['Version 1.0', 'Page', '1 / 1']

    @Test
    void testParams_br2_20100701_20100930() {
        new PrptReport().execute () { t ->

            // Report settings.
            t.reportPath = 'reports/standardReports/prpts/LoanOfficerPerformanceSummaryDuringPeriod.prpt'
            t.reportParams = ['selected_office': '1.1.1.', 'start_date': '2010-07-01', 'end_date': '2010-09-30']

            // Report title
            t.assertRowEquals(1, [reportName])

            // Page header
            t.assertRowEquals(2, ['Time Period:', 'From:', 'MFI Name:', 'Mifos HO'])
            t.assertCellEquals(3, 1, 'To:')
            t.assertCellEquals(3, 2, 'Report Date:')

            // 1st Details header
            def offset = 4
            t.assertRowEquals(offset, branch)
            t.assertRowEquals(offset+1, detailsHeader)
            // Details
            t.assertRowEquals(offset+2, detailsDuringPeriodBr2LOxxx)
            t.assertRowEquals(offset+3, totalDuringPeriod)
            // 1st Details footer
            t.assertRowEquals(offset+4, detailsHeader)
            t.assertRowEquals(offset+5, grandTotalDuringPeriod)

            // Page footer
            offset = 10
            t.assertRowEquals(offset, pageFooter)
            t.assertCellEquals(offset+1, 1, 'Printed by:')
        }
    }

    @Test
    void testParams_br2_20100701_20100815() {
        new PrptReport().execute () { t ->

            // Report settings.
            t.reportPath = 'reports/standardReports/prpts/LoanOfficerPerformanceSummaryDuringPeriod.prpt'
            t.reportParams = ['selected_office': '1.1.1.', 'start_date': '2010-07-01', 'end_date': '2010-08-15']

            // Report title
            t.assertRowEquals(1, [reportName])

            // Page header
            t.assertRowEquals(2, ['Time Period:', 'From:', 'MFI Name:', 'Mifos HO'])
            t.assertCellEquals(3, 1, 'To:')
            t.assertCellEquals(3, 2, 'Report Date:')

            // 1st Details header
            def offset = 4
            t.assertRowEquals(offset, branch)
            t.assertRowEquals(offset+1, detailsHeader)
            // Details
            t.assertRowEquals(offset+2, detailsDuringPeriodBr2LOxxx)
            t.assertRowEquals(offset+3, totalDuringPeriod)
            // 1st Details footer
            t.assertRowEquals(offset+4, detailsHeader)
            t.assertRowEquals(offset+5, grandTotalDuringPeriod)

            // Page footer
            offset = 10
            t.assertRowEquals(offset, pageFooter)
            t.assertCellEquals(offset+1, 1, 'Printed by:')
        }
    }
}