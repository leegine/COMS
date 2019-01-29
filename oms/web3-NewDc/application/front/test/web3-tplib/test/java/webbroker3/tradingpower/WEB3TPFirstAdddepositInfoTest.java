head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPFirstAdddepositInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPFirstAdddepositInfoTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 劉剣 (中訊) 新規作成
*/

package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPFirstAdddepositInfoTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPFirstAdddepositInfoTest.class);

    private WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
    private boolean l_blnFlag = false;

    public WEB3TPFirstAdddepositInfoTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * パラメータ値不正。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testCreateFirstAdddepositInfo_C0001()
    {
        final String STR_METHOD_NAME = "testCreateFirstAdddepositInfo_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = null;
            
            l_firstAdddepositInfo.createFirstAdddepositInfo(l_paymentRequisitionManagement);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * get信用現物判定フラグ()の戻り値　@==　@"0"(現物顧客) の場合
     * WEB3ErrorCatalog.BUSINESS_ERROR_02887
     */
    public void testCreateFirstAdddepositInfo_C0002()
    {
        final String STR_METHOD_NAME = "testCreateFirstAdddepositInfo_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            l_paymentRequisitionManagement.setMarginEquityJudgeFlag("0");
            
            l_firstAdddepositInfo.createFirstAdddepositInfo(l_paymentRequisitionManagement);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02887, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * is第一水準追証発生( )の戻り値　@==　@TRUE の場合
     */
    public void testCreateFirstAdddepositInfo_C0003()
    {
        final String STR_METHOD_NAME = "testCreateFirstAdddepositInfo_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagementForTest();
            l_paymentRequisitionManagement.setMarginEquityJudgeFlag("1");

            WEB3TPFirstAdddepositInfo l_InfoReture =
                l_firstAdddepositInfo.createFirstAdddepositInfo(l_paymentRequisitionManagement);
            
            assertEquals(11, l_InfoReture.firstDepositPassDay);
            assertEquals(12, l_InfoReture.firstDepositPassDayValid);
            assertEquals(WEB3DateUtility.getDate("20080808", "yyyyMMdd"), l_InfoReture.firstDepositOccurredDate);
            assertEquals("0.1", String.valueOf(l_InfoReture.firstMarginDepositRate));
            assertEquals("0.2", String.valueOf(l_InfoReture.firstDepositRate));
            assertEquals("0.3", String.valueOf(l_InfoReture.firstDepositAmount));
            assertEquals("21.0", String.valueOf(l_InfoReture.firstSettlement));
            assertEquals("22.0", String.valueOf(l_InfoReture.firstMarginDepositInDe));
            assertEquals("23.0", String.valueOf(l_InfoReture.firstMarginDepositInDeExpect));
            assertEquals("24.0", String.valueOf(l_InfoReture.firstSettledContract));
            assertEquals("25.0", String.valueOf(l_InfoReture.firstUncancelAmt));
            assertEquals("26.0", String.valueOf(l_InfoReture.firstUncancelSettleRequiredAmt));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * is第一水準追証発生( )の戻り値　@==　@false の場合
     */
    public void testCreateFirstAdddepositInfo_C0004()
    {
        final String STR_METHOD_NAME = "testCreateFirstAdddepositInfo_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = false;
            
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagementForTest();
            l_paymentRequisitionManagement.setMarginEquityJudgeFlag("1");

            WEB3TPFirstAdddepositInfo l_InfoReture =
                l_firstAdddepositInfo.createFirstAdddepositInfo(l_paymentRequisitionManagement);
            
            assertNull(l_InfoReture);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3TPPaymentRequisitionManagementForTest
     */
    private class WEB3TPPaymentRequisitionManagementForTest extends WEB3TPPaymentRequisitionManagement
    {
        public boolean isFirstAdddeposit() 
        {
            boolean l_blnReturn = false;
            if (l_blnFlag)
            {
                l_blnReturn = true;
            }
            else
            {
                l_blnReturn = false;
            }
            return l_blnReturn;
        }
        
        public int getFirstAdddepositPassDay()
        {
            return 11;
        }
        
        public int getFirstAdddepositPassDayValid()
        {
            return 12;
        }
        
        public Date getFirstAdddepositOccurredDate()
        {
            return WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        }
        
        public double getFirstAdddepositMarginDepositRate()
        {
            return 0.1;
        }
        
        public double getFirstAdddepositDepositRate()
        {
            return 0.2;
        }
        
        public double getFirstAdddepositAmount()
        {
            return 0.3;
        }
        
        public double getFirstAdddepositSettlement()
        {
            return 21.0;
        }
        
        public double getFirstAdddepositMarginDepositInDe()
        {
            return 22.0;
        }
        
        public double getFirstAdddepositMarginDepositInDeExpect()
        {
            return 23.0;
        }
        
        public double getFirstAdddepositSettledContract()
        {
            return 24.0;
        }
        
        public double getFirstAdddepositUncancelAmt()
        {
            return 25.0;
        }
        
        public double getFirstAdddepositUncancelSettleRequiredAmt()
        {
            return 26.0;
        }
    }
}
@
