head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToTradeStopDelCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminToTradeStopDelCompleteRequestTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopDelCompleteRequestTest.class); // ///////////

    WEB3AdminToTradeStopDelCompleteRequest l_request = null; // ///////////////

    public WEB3AdminToTradeStopDelCompleteRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true); // //////////
        l_request = new WEB3AdminToTradeStopDelCompleteRequest(); // ////////////////
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // １）　@特殊執行条件取扱停止IDチェック
    //　@１－１）　@this.特殊執行条件取扱停止ID == nullの場合、
    //　@　@「特殊執行条件取扱停止IDが未入力」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.triggerTradeStopId = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02430, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

}
@
