package org.mifos.bi.test

import org.junit.Test
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTime

class LoansPendingApprovalTest {

    def today = DateTimeFormat.forPattern("yyyy-MM-dd").print(new DateTime())

    def mfiName = 'Mifos HO'
    def reportPath = 'reports/standardReports/prpts/LoansPendingApproval.prpt'
    def reportName = 'Loans Pending Approval';
    def detailsHeader = ['Account ID', 'Client name', 'Product name', 'Loan Officer', 'Loan Amount', 'Loan Status', 'Time in existing status (days)', 'Last edited by']

    def total = [' ', ' ', ' ', ' ', '0', ' ', ' ', ' ']

    def pageFooter = ['Version 1.1', 'Page', '1 / 1']

    @Test
    void testParams_All_All_All() {
        new PrptReport().execute () { t ->

            // Report settings.
            t.reportPath = reportPath
            t.reportParams = ['selected_office': '-1', 'selected_loan_officer': '0', 'selected_product': '0']

            // MFI Name
            t.assertRowEquals(1, [mfiName])
            // Report title
            t.assertRowEquals(2, [reportName])

            // Page header
            t.assertRowEquals(3, ['As Of:', today, 'Office:', 'All'])
            t.assertRowEquals(4, ['Product:', 'All', 'Loan Officer:', 'All'])

            // Details header
            t.assertRowEquals(5, detailsHeader)
            // Group footer
            t.assertRowEquals(6, total)

            // Page footer
            t.assertRowEquals(7, pageFooter)
            t.assertCellEquals(8, 1, 'Printed by:')
            t.assertCellEquals(8, 2, 'On:')
        }
    }
}