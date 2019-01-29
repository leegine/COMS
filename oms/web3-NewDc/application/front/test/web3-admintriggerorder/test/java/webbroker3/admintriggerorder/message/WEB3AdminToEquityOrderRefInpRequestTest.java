head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToEquityOrderRefInpRequestTest.java;


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

public class WEB3AdminToEquityOrderRefInpRequestTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToEquityOrderRefInpRequestTest.class); // ///////////

    WEB3AdminToEquityOrderRefInpRequest l_request = null; // ///////////////

    public WEB3AdminToEquityOrderRefInpRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true); // //////////
        l_request = new WEB3AdminToEquityOrderRefInpRequest(); // ////////////////
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // ‚Pj•”“XƒR[ƒhƒ`ƒFƒbƒN
    // ‚P|‚Pjthis.•”“XƒR[ƒh == null‚Ìê‡A
    // u•”“XƒR[ƒh‚ªnullv‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    // ‚P|‚Qjthis.•”“XƒR[ƒh.length == 0‚Ìê‡A
    // u•”“XƒR[ƒh‚Ì—v‘f”‚ª0v‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = new String[]
            {};
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02175, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    // ‚P|‚Rjthis.•”“XƒR[ƒh‚Ì—v‘f”•ªˆÈ‰º‚Ìˆ—‚ðs‚¤B
    // ‚P|‚R|‚Pjthis.•”“XƒR[ƒh‚ªˆÈ‰º‚ÌðŒ‚ÉŠY“–‚·‚éê‡A
    // u•”“XƒR[ƒhƒGƒ‰[v‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    // E•”“XƒR[ƒh‚”Žš
    // E•”“XƒR[ƒh.length‚3
    // normalcase
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = new String[]
            {"111", "111", "111"};
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    // ‚P|‚Rjthis.•”“XƒR[ƒh‚Ì—v‘f”•ªˆÈ‰º‚Ìˆ—‚ðs‚¤B
    // ‚P|‚R|‚Pjthis.•”“XƒR[ƒh‚ªˆÈ‰º‚ÌðŒ‚ÉŠY“–‚·‚éê‡A
    // u•”“XƒR[ƒhƒGƒ‰[v‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    // E•”“XƒR[ƒh.length‚3
    // ‘æˆêŒÂŒ³‘f•sŸÞ‘«‚–‚‚Œ‚‰‚„‚‚”‚…žŠŒCeoˆÙí
    // BUSINESS_ERROR_00779
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = new String[]
            {"11", "111", "111"};
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    // ‚P|‚Rjthis.•”“XƒR[ƒh‚Ì—v‘f”•ªˆÈ‰º‚Ìˆ—‚ðs‚¤B
    // ‚P|‚R|‚Pjthis.•”“XƒR[ƒh‚ªˆÈ‰º‚ÌðŒ‚ÉŠY“–‚·‚éê‡A
    // u•”“XƒR[ƒhƒGƒ‰[v‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    // E•”“XƒR[ƒh‚”Žš
    // ‘æˆêŒÂŒ³‘f•sŸÞ‘«‚–‚‚Œ‚‰‚„‚‚”‚…žŠŒCeoˆÙí
    // BUSINESS_ERROR_00779
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = new String[]
            {"aaa", "111", "111"};
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    // ‚P|‚Rjthis.•”“XƒR[ƒh‚Ì—v‘f”•ªˆÈ‰º‚Ìˆ—‚ðs‚¤B
    // ‚P|‚R|‚Pjthis.•”“XƒR[ƒh‚ªˆÈ‰º‚ÌðŒ‚ÉŠY“–‚·‚éê‡A
    // u•”“XƒR[ƒhƒGƒ‰[v‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    // E•”“XƒR[ƒh‚”Žš
    // ‘æ2ŒÂŒ³‘f•sŸÞ‘«‚–‚‚Œ‚‰‚„‚‚”‚…žŠŒCeoˆÙí
    // BUSINESS_ERROR_00779
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = new String[]
            {"111", "aaa", "111"};
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }

    // ‚P|‚Rjthis.•”“XƒR[ƒh‚Ì—v‘f”•ªˆÈ‰º‚Ìˆ—‚ðs‚¤B
    // ‚P|‚R|‚Pjthis.•”“XƒR[ƒh‚ªˆÈ‰º‚ÌðŒ‚ÉŠY“–‚·‚éê‡A
    // u•”“XƒR[ƒhƒGƒ‰[v‚Ì—áŠO‚ðƒXƒ[‚·‚éB
    // E•”“XƒR[ƒh‚”Žš
    // ‘æ3ŒÂŒ³‘f•sŸÞ‘«‚–‚‚Œ‚‰‚„‚‚”‚…žŠŒCeoˆÙí
    // BUSINESS_ERROR_00779
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = new String[]
            {"111", "111", "aaa"};
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
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
