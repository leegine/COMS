head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoMultiMailAddressInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoMultiMailAddressInfoTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMultiMailAddressInfoTest.class);

    public WEB3AccInfoMultiMailAddressInfoTest(String arg0)
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

    public void testValidateMultiSendMailAddressInfoFlag_0001()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        try
        {
            info.validateMultiSendMailAddressInfoFlag(null);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02892, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0002()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        try
        {
            info.validateMultiSendMailAddressInfoFlag("");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02892, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0003()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "-1";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02893, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0004()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "5";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02893, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0005()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "0";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02896, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0006()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02896, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0007()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "2";
        info.mailAddress2 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0008()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "3";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0009()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "4";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02896, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0010()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0010()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "2";
        info.mailAddress2 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0011()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0011()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "3";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0012()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0012()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "-1";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02896, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0013()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0013()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "5";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02896, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0014()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0014()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "0";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02897, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0015()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0015()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02897, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0016()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0016()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "2";
        info.mailAddress2 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0017()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0017()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "3";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0018()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0018()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "4";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02897, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0019()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0019()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "2";
        info.mailAddress2 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0020()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0020()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "3";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0021()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0021()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "-1";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02897, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0022()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0022()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "5";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02897, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0023()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0023()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02898, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0024()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0024()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "2";
        info.mailAddress2 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0025()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0025()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "3";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0026()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0026()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "4";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02898, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0027()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0027()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "2";
        info.mailAddress2 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0028()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0028()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "3";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0029()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0029()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "-1";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02898, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0030()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0030()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "5";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02898, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0031()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0031()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "0";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0032()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0032()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "1";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0033()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0033()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "2";
        info.mailAddress2 = "123";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0034()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0034()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "3";
        info.mailAddress3 = "123";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0035()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0035()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "4";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0036()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0036()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "2";
        info.mailAddress2 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0037()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0037()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "2";
        info.mailAddress2 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02894, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0038()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0038()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "3";
        info.mailAddress3 = null;
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateMultiSendMailAddressInfoFlag_0039()
    {
        final String STR_METHOD_NAME = "testValidateMultiSendMailAddressInfoFlag_0039()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.execMailFlag = "1";
        info.unExecMailFlag = "1";
        info.importantMailFlag = "1";
        info.guidanceMailFlag2 = "3";
        info.mailAddress3 = "";
        try
        {
            info.validateMultiSendMailAddressInfoFlag("123");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02895, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0001()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = "123";
        info.mailAddressDelFlag2 = true;
        
        try
        {
            info.validateMultiMailAddressInfo();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02899, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0002()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = "123";
        info.mailAddressDelFlag2 = false;
        
        try
        {
            info.validateMultiMailAddressInfo();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00777, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0003()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = "";
        info.mailAddress3 = "123";
        info.mailAddressDelFlag3 = true;
        
        try
        {
            info.validateMultiMailAddressInfo();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02900, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0004()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = "";
        info.mailAddress3 = "123";
        info.mailAddressDelFlag3 = false;
        
        try
        {
            info.validateMultiMailAddressInfo();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00777, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0005()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = null;
        info.mailAddress3 = null;
        
        try
        {
            info.validateMultiMailAddressInfo();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0006()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = "";
        info.mailAddress3 = "";
        
        try
        {
            info.validateMultiMailAddressInfo();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateMultiMailAddressInfo_0007()
    {
        final String STR_METHOD_NAME = "testValidateMultiMailAddressInfo_0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMultiMailAddressInfo info = new WEB3AccInfoMultiMailAddressInfo();
        info.mailAddress2 = "xie@@sinacom.cn";
        info.mailAddress3 = "xie@@sinacom.cn";
        info.mailAddressDelFlag2 = false;
        info.mailAddressDelFlag3 = false;
        
        try
        {
            info.validateMultiMailAddressInfo();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
