head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.34.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXIIJConnectionSystemTest.java;


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

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.aio.message.WEB3FXIIJAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3FXIIJConnectionSystemTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXIIJConnectionSystemTest.class);

    public WEB3FXIIJConnectionSystemTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }


    public void test_getURLIIJResultCode_0001()
    {
        final String STR_METHOD_NAME = "test_getURLIIJResultCode_0001()";
        log.entering(STR_METHOD_NAME);
        String str = "<?xml version=1.0 encoding=UTF-8?>    <resultParameter>    <send_time>    20100715162510    </send_time>    <result>    00000    </result>";

        WEB3FXIIJConnectionSystemForTest l_system = new WEB3FXIIJConnectionSystemForTest();
        String l_str = l_system.getURLIIJResultCode(str);
        HashMap l_map = l_system.connectionResultDetails;
        assertEquals("00000000", l_str);
        assertEquals("000000", (String)l_map.get("connectResult"));
        log.exiting(STR_METHOD_NAME);
    }

    public class WEB3FXIIJConnectionSystemForTest extends WEB3FXIIJConnectionSystem
    {

        protected WEB3FXIIJAskingTelegramUnit createHash(WEB3FXIIJAskingTelegramUnit l_iijAskingTelegramUnit, String l_strOperationName) throws WEB3BaseException
        {
            return null;
        }

        protected String createURL(String l_strEndpointName, WEB3FXIIJAskingTelegramUnit l_iijAskingTelegramUnit) throws WEB3BaseException
        {
            return null;
        }

        protected WEB3FXIIJAskingTelegramUnit createIIJAskingTelegramUnit(
        Message l_message) throws WEB3BaseException
        {
            return null;
        }
    }

}
@
