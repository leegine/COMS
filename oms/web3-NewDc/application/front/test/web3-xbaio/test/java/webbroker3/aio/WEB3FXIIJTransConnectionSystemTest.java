head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXIIJTransConnectionSystemTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : （WEB3FXIIJConnectionSystemTest.java）
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/07/15 平野順啓 (DIR-BI) 新規作成
*/
package webbroker3.aio;

import webbroker3.aio.message.WEB3FXIIJAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3FXIIJTransConnectionSystemTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXIIJTransConnectionSystemTest.class);

    WEB3FXIIJTransConnectionSystem l_iijTransConnectionSystem =
        new WEB3FXIIJTransConnectionSystem();

    public WEB3FXIIJTransConnectionSystemTest(String arg0)
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

    /**
     * 正常状況下
     * createHashメソッド正常パターン
     */
    public void testCreateHash_Case001()
    {
        final String STR_METHOD_NAME = "testCreateHash_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
        	WEB3FXIIJAskingTelegramUnit l_iijAskingTelegramUnit =
                new WEB3FXIIJAskingTelegramUnit();
        	l_iijAskingTelegramUnit.send_time = "20100628123108";
        	l_iijAskingTelegramUnit.login_id = "1";
        	l_iijAskingTelegramUnit.amount = "2";
        	l_iijAskingTelegramUnit.req_cd = "3";
        	l_iijAskingTelegramUnit.iijOperationDiv = "04";
            WEB3FXIIJAskingTelegramUnit l_unit =
            	l_iijTransConnectionSystem.createHash(l_iijAskingTelegramUnit, "fx_retela");
            log.debug(l_unit.hashKey);
            assertEquals("c4067407b6a936186b0f7d6a5431ef43b8978ade", l_unit.hashKey);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 異常状況下
     * createHashメソッド異常パターン
     */
    public void testCreateHash_Case002()
    {
        final String STR_METHOD_NAME = "testCreateHash_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_iijTransConnectionSystem.createHash(null, "fx_retela");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常状況下
     * createURLメソッド正常パターン
     */
    public void testCreateURL_Case001()
    {
        final String STR_METHOD_NAME = "testCreateURL_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXIIJAskingTelegramUnit l_iijAskingTelegramUnit =
                new WEB3FXIIJAskingTelegramUnit();
            l_iijAskingTelegramUnit.send_time = "20100628123108";
            l_iijAskingTelegramUnit.login_id = "12345678";
            l_iijAskingTelegramUnit.amount = "2000";
            l_iijAskingTelegramUnit.req_cd = "211384798";
            l_iijAskingTelegramUnit.hashKey = "c4067407b6a936186b0f7d6a5431ef43b8978ade";
            l_iijAskingTelegramUnit.iijOperationDiv = "04";
            String l_strURL =
            	l_iijTransConnectionSystem.createURL("1", l_iijAskingTelegramUnit);
            log.debug(l_strURL);
            assertEquals("1/xxxxxxxxxxxxx.do?send_time=20100628123108"
                     + "&login_id=12345678&amount=2000&req_cd=211384798"
			         + "&hashKey=c4067407b6a936186b0f7d6a5431ef43b8978ade", l_strURL);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 異常状況下
     * createURLメソッド異常パターン
     */
    public void testCreateURL_Case002()
    {
        final String STR_METHOD_NAME = "testCreateURL_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
        	l_iijTransConnectionSystem.createURL("1", null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
