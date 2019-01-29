head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenVoucherMakeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenVoucherMakeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenVoucherMakeCompleteRequestTest.class);

    public WEB3AdminAccOpenVoucherMakeCompleteRequestTest(String arg0)
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

    /*
     * Test method for 'webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest.validate()'
     */
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenVoucherMakeCompleteRequest l_request =
                new WEB3AdminAccOpenVoucherMakeCompleteRequest();
            WEB3AccOpenApplyInfo l_info = new WEB3AccOpenApplyInfo();
            l_info.accountCodeAutoFlag = "1";
            l_request.accoutOpenApplyInfo = l_info;
            WEB3AccOpenVoucherInfo l_vocherInfo = new WEB3AccOpenVoucherInfo();
            l_request.voucherInfo = l_vocherInfo;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03180, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenVoucherMakeCompleteRequest l_request =
                new WEB3AdminAccOpenVoucherMakeCompleteRequest();
            WEB3AccOpenApplyInfo l_info = new WEB3AccOpenApplyInfo();
            l_info.accountCodeAutoFlag = "1";
            l_request.accoutOpenApplyInfo = l_info;
            l_request.accountDiv = "1";
            WEB3AccOpenVoucherInfo l_vocherInfo = new WEB3AccOpenVoucherInfo();
            l_request.voucherInfo = l_vocherInfo;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
