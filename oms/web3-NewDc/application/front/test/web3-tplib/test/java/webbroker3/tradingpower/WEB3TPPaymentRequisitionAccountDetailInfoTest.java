head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPPaymentRequisitionAccountDetailInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPPaymentRequisitionAccountDetailInfoTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionAccountDetailInfoTest.class);
    
    WEB3TPPaymentRequisitionAccountDetailInfo l_info = null;
    public WEB3TPPaymentRequisitionAccountDetailInfoTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_info = new WEB3TPPaymentRequisitionAccountDetailInfo();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public void testCreatePaymentRequisitionAccountDetailInfo_C001()
    {
        final String STR_METHOD_NAME = "testCreatePaymentRequisitionAccountDetailInfo_C001";
        log.entering(STR_METHOD_NAME);
        WEB3TPPaymentRequisitionManagementForTest l_paymentRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest();
        try
        {
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                WEB3TPPaymentRequisitionAccountDetailInfo.
                    createPaymentRequisitionAccountDetailInfo(l_paymentRequisitionManagement);
            assertEquals(true, l_paymentRequisitionAccountDetailInfo.depositAutoTransferDivFlag);
            assertEquals("01", l_paymentRequisitionAccountDetailInfo.accountAttribute);
            assertEquals(20.4,
                l_paymentRequisitionAccountDetailInfo.shortfallGenerationInfo.specialDebitAmount, 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    private class WEB3TPPaymentRequisitionManagementForTest extends WEB3TPPaymentRequisitionManagement
    {
        public boolean isDepositAutoTransferDivFlag()
        {
            return true;
        }
        public double getDebitAmount()
        {
            return 10.5;
        }
        public double getSpecialDebitAmount()
        {
            return 20.4;
        }
        public String getAccountAttribute()
        {
            return "01";
        }
    }
}
@
