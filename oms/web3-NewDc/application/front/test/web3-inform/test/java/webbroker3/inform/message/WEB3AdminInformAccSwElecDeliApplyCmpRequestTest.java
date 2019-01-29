head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.30.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformAccSwElecDeliApplyCmpRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込完了リクエスト(WEB3AdminInformAccSwElecDeliApplyCmpRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/25 孫洪江 (中訊) 新規作成
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformAccSwElecDeliApplyCmpRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminInformAccSwElecDeliApplyCmpRequestTest.class);

    public WEB3AdminInformAccSwElecDeliApplyCmpRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "123";
        l_request.accountCode = "123456";
        l_request.informType = "1";

        l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();

        l_request.changedInfo.mobileAccoutDiv = "1";
        l_request.changedInfo.tradingReportDiv = "0";
        l_request.changedInfo.positionReportDiv = "0";

        l_request.changedInfo.mobileAccoutDiv = "1";
        l_request.changedInfo.positionReportCycleDiv = "1";
        l_request.changedInfo.certificateDepositDiv = "1";
        l_request.changedInfo.accountStatementDiv = "1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "1234";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "0.1";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "123";
        l_request.accountCode = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00835, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "123";
        l_request.accountCode = "1234";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "123";
        l_request.accountCode = "0.1234";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();

        l_request.branchCode = "123";
        l_request.accountCode = "123456";
        l_request.informType = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
//    public void testValidate_0009()
//    {
//        String STR_METHOD_NAME = ".testValidate_0009()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();
//
//        l_request.branchCode = "123";
//        l_request.accountCode = "123456";
//        l_request.informType = "1";
//        l_request.changedInfo = null;
//
//        try
//        {
//            l_request.validate();
//
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//
//            fail();
//        }
//    }
}
@
