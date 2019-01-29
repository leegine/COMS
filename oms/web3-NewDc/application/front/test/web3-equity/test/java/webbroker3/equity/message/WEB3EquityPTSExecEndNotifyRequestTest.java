head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSExecEndNotifyRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知リクエストTest(WEB3EquityPTSExecEndNotifyRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/31 張騰宇(中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSExecEndNotifyRequestTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyRequestTest.class);

    public WEB3EquityPTSExecEndNotifyRequestTest(String arg0)
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
     * Test method for 'webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequest.validate()'
     */
    //this.証券会社コード＝null
    public void testVaidate_0001()
    {
        final String STR_METHOD_NAME = "testVaidate_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSExecEndNotifyRequest l_request = new WEB3EquityPTSExecEndNotifyRequest();
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.証券会社コード＝"0D"
    //this.市場コード＝null
    public void testVaidate_0002()
    {
        final String STR_METHOD_NAME = "testVaidate_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSExecEndNotifyRequest l_request = new WEB3EquityPTSExecEndNotifyRequest();
            l_request.institutionCode = "0D";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00443, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.証券会社コード＝"0D"
    //this.市場コード＝"11"
    public void testVaidate_0003()
    {
        final String STR_METHOD_NAME = "testVaidate_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSExecEndNotifyRequest l_request = new WEB3EquityPTSExecEndNotifyRequest();
            l_request.institutionCode = "0D";
            l_request.marketCode = "11";
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
