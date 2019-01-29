head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXSimplexConnectionSystemTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXSimplexConnectionSystemTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexConnectionSystemTest.class);
    
    public WEB3FXSimplexConnectionSystemTest(String name)
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

    public void test_createSimplexAskingTelegramUnit_0001()
    {
        final String STR_METHOD_NAME = "test_createSimplexAskingTelegramUnit_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_getURLSimplexResultCode_0001()
    {
        final String STR_METHOD_NAME = "test_getURLSimplexResultCode_0001()";
        log.entering(STR_METHOD_NAME);
        String str = "<resultParameter> <result>        NG      </result>       <error>         <errorId>               MI-03402            </errorId>          <errorMsg>              パラメータ（振替ID）が不正です。           </errorMsg>";
        
        WEB3FXSimplexConnectionSystemForTest l_system = new WEB3FXSimplexConnectionSystemForTest();
        String l_str = l_system.getURLSimplexResultCode(str);
        HashMap l_map = l_system.connectionResultDetails;
        assertEquals("00000602", l_str);
        assertEquals("MI-03402", (String)l_map.get("connectResult"));
        log.exiting(STR_METHOD_NAME);
    }
    
    public void test_getURLSimplexResultCode_0002()
    {
        final String STR_METHOD_NAME = "test_getURLSimplexResultCode_0002()";
        log.entering(STR_METHOD_NAME);
        String str = "<resultParameter> <result>        NG      </result>  ";
        
        WEB3FXSimplexConnectionSystemForTest l_system = new WEB3FXSimplexConnectionSystemForTest();
        String l_str = l_system.getURLSimplexResultCode(str);
        HashMap l_map = l_system.connectionResultDetails;
        assertEquals("00000000", l_str);
        assertEquals("0", (String)l_map.get("connectResult"));
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3FXSimplexConnectionSystemForTest extends WEB3FXSimplexConnectionSystem
    {

        protected WEB3FXSimplexAskingTelegramUnit createHash(WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit, String l_strOperationName) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
            return null;
        }

        protected String createURL(String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
            return null;
        }
        
        protected WEB3FXSimplexAskingTelegramUnit createSimplexAskingTelegramUnit(
        Message l_message) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
@
