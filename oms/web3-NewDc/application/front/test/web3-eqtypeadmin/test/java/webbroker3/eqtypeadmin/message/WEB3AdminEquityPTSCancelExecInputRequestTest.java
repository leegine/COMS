head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSCancelExecInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminEquityPTSCancelExecInputRequestTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/30 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSCancelExecInputRequestTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecInputRequestTest.class);
    
    WEB3AdminEquityPTSCancelExecInputRequest l_request = null;

    public WEB3AdminEquityPTSCancelExecInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminEquityPTSCancelExecInputRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest.validate()'
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
            assertEquals(WEB3BusinessLayerException.class, l_ex.getClass());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.orderId = "3333";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
