head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.30.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAccSwElecDeliApplyCmpRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\���������N�G�X�g(WEB3InformAccSwElecDeliApplyCmpRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/25 ���^�] (���u) �V�K�쐬
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformAccSwElecDeliApplyCmpRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3InformAccSwElecDeliApplyCmpRequestTest.class);

    public WEB3InformAccSwElecDeliApplyCmpRequestTest(String arg0)
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

        WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();

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

    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();

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

//    public void testValidate_0003()
//    {
//        String STR_METHOD_NAME = ".testValidate_0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();
//
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
