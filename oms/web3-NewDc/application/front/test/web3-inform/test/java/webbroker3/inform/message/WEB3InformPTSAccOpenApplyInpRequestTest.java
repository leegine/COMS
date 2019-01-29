head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.30.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformPTSAccOpenApplyInpRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformPTSAccOpenApplyInpRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformPTSAccOpenApplyInpRequestTest.class);
    public WEB3InformPTSAccOpenApplyInpRequestTest(String arg0)
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

    public void testValidate_T01()
    {
        final String STR_METHOD_NAME = "testValidate_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3InformPTSAccOpenApplyInpRequest l_request =
                new WEB3InformPTSAccOpenApplyInpRequest();
            l_request.informType = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T02()
    {
        final String STR_METHOD_NAME = "testValidate_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3InformPTSAccOpenApplyInpRequest l_request =
                new WEB3InformPTSAccOpenApplyInpRequest();
            l_request.informType = "1";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
