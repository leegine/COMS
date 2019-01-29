head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPAdddepositGenerationInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPAdddepositGenerationInfoTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPAdddepositGenerationInfoTest.class);
    WEB3TPAdddepositGenerationInfo l_tPAdddepositGenerationInfo =null;
    public WEB3TPAdddepositGenerationInfoTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_tPAdddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public void testCreateAdddepositGenerationInfo_001()
    {
        final  String STR_METHOD_NAME ="testCreateAdddepositGenerationInfo_001";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagementForTest l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagementForTest();
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                WEB3TPAdddepositGenerationInfo.createAdddepositGenerationInfo(l_paymentRequisitionManagement);
            assertEquals(true,l_adddepositGenerationInfo.depositAutoTransferDivFlag);
            assertEquals(WEB3DateUtility.getDate("20040716", "yyyyMMdd"),
                ((WEB3TPFirstAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo()).firstDepositOccurredDate);
            assertEquals(1,
                ((WEB3TPFirstAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo()).firstDepositPassDay); 
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateAdddepositGenerationInfo_002()
    {
        final  String STR_METHOD_NAME ="testCreateAdddepositGenerationInfo_002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest l_paymentRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest();
        try
        {
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                WEB3TPAdddepositGenerationInfo.createAdddepositGenerationInfo(l_paymentRequisitionManagement);
            assertEquals(WEB3DateUtility.getDate("20040720", "yyyyMMdd"),
                    ((WEB3TPSecondAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo()).secondCloseDate2);
            assertEquals(true,l_adddepositGenerationInfo.getDepositAutoTransferDivFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3TPPaymentRequisitionManagementForTest extends WEB3TPPaymentRequisitionManagement
    {
        public int getFirstAdddepositPassDay()
        {
            return 1;
        }
        public String getMarginEquityJudgeFlag()
        {
            return 1 + "";
        }
        public int getFirstAdddepositPassDayValid()
        {
            return 2;
        }
        public Date getFirstAdddepositOccurredDate()
        {
            return WEB3DateUtility.getDate("20040716", "yyyyMMdd");
        }
        public boolean isFirstAdddeposit()
        {
            return true;
        }
        public boolean isDepositAutoTransferDivFlag()
        {
            return true;
        }
        public boolean isSecondAdddeposit()
        {
            return true;
        }
        public Date getSecondAdddepositCloseDate2()
        {
            return WEB3DateUtility.getDate("20040720", "yyyyMMdd");
        }
    }
}
@
