head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadCsvTest_validatePaymentFreeRegiDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadCsvTest_validatePaymentFreeRegiDate.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/06/12 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadCsvTest_validatePaymentFreeRegiDate extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadCsvTest_validatePaymentFreeRegiDate.class);

    WEB3AdminSrvRegiAccountDataUploadCsv csv = new WEB3AdminSrvRegiAccountDataUploadCsv();

    public WEB3AdminSrvRegiAccountDataUploadCsvTest_validatePaymentFreeRegiDate(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidatePaymentFreeRegiDate01()
    {
        final String STR_METHOD_NAME = "testValidatePaymentFreeRegiDate01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 12);
            Timestamp l_tsAppliyStartDate = new Timestamp(l_calendar.getTimeInMillis());

            csv.validatePaymentFreeRegiDate(l_tsAppliyStartDate, null);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    public void testValidatePaymentFreeRegiDate02()
//    {
//        final String STR_METHOD_NAME = "testValidatePaymentFreeRegiDate02()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 6-1, 12);
//            Timestamp l_tsAppliyStartDate = new Timestamp(l_calendar.getTimeInMillis());
//            l_calendar.set(2007, 6-1, 13);
//            Timestamp l_tsAppliyEndDate = new Timestamp(l_calendar.getTimeInMillis());
//
//            l_calendar.set(2007, 6-1, 14);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);
//                    
//            csv.validatePaymentFreeRegiDate(l_tsAppliyStartDate, l_tsAppliyEndDate);
//            fail();
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02804, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    public void testValidatePaymentFreeRegiDate03()
    {
        final String STR_METHOD_NAME = "testValidatePaymentFreeRegiDate03()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 12);
            Timestamp l_tsAppliyStartDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendar.set(2007, 6-1, 13);
            Timestamp l_tsAppliyEndDate = new Timestamp(l_calendar.getTimeInMillis());

            l_calendar.set(2007, 6-1, 13);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
                    
            csv.validatePaymentFreeRegiDate(l_tsAppliyStartDate, l_tsAppliyEndDate);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidatePaymentFreeRegiDate04()
    {
        final String STR_METHOD_NAME = "testValidatePaymentFreeRegiDate04()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 13);
            Timestamp l_tsAppliyStartDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendar.set(2007, 6-1, 12);
            Timestamp l_tsAppliyEndDate = new Timestamp(l_calendar.getTimeInMillis());

            l_calendar.set(2007, 6-1, 11);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
                    
            csv.validatePaymentFreeRegiDate(l_tsAppliyStartDate, l_tsAppliyEndDate);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00839, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }    
}
@
