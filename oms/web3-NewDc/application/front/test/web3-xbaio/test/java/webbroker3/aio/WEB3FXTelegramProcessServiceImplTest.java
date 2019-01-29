head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTelegramProcessServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTelegramProcessServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTelegramProcessServiceImplTest.class);

    public WEB3FXTelegramProcessServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.aio.WEB3FXTelegramProcessServiceImpl.isGFTTelegramSet(WEB3FXGftResultNoticeTelegramUnit)'
     */
    public void testIsGFTTelegramLengthPropSameCase1()
    {
        final String STR_METHOD_NAME = "testIsGFTTelegramLengthPropSameCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTelegramProcessServiceImpl l_impl = new WEB3FXTelegramProcessServiceImpl();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit = new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "20090825100000";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "01";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "12345678912";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "222";
            boolean flag = l_impl.isGFTTelegramLengthPropSame(l_fxGftResultNoticeTelegramUnit);
            assertFalse(flag);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsGFTTelegramLengthPropSameCase2()
    {
        final String STR_METHOD_NAME = "testIsGFTTelegramLengthPropSameCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTelegramProcessServiceImpl l_impl = new WEB3FXTelegramProcessServiceImpl();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit = new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "20090825100000";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "01";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "111";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "12345678912";
            boolean flag = l_impl.isGFTTelegramLengthPropSame(l_fxGftResultNoticeTelegramUnit);
            assertFalse(flag);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsGFTTelegramLengthPropSameCase3()
    {
        final String STR_METHOD_NAME = "testIsGFTTelegramLengthPropSameCase3()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTelegramProcessServiceImpl l_impl = new WEB3FXTelegramProcessServiceImpl();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit = new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "20090825100000";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "01";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "111";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "222";
            boolean flag = l_impl.isGFTTelegramLengthPropSame(l_fxGftResultNoticeTelegramUnit);
            assertTrue(flag);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
