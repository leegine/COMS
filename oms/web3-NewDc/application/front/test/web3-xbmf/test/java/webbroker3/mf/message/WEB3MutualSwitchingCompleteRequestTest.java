head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSwitchingCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualSwitchingCompleteRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingCompleteRequest.class);

    public WEB3MutualSwitchingCompleteRequestTest(String name)
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

    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00400, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00400, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "1";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00073, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "2";
        l_request.mutualOrderQuantity = "100";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00093, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "3";
        l_request.mutualOrderQuantity = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00094, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "3";
        l_request.mutualOrderQuantity = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00094, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0009()
    {
        String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "14ds5";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00095, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0010()
    {
        String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "-12";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00096, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0011()
    {
        String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "4816112151212423";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00097, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0012()
    {
        String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00401, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0013()
    {
        String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00401, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0014()
    {
        String STR_METHOD_NAME = "testValidate_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "2";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00402, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0015()
    {
        String STR_METHOD_NAME = "testValidate_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00414, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0016()
    {
        String STR_METHOD_NAME = "testValidate_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00414, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0017()
    {
        String STR_METHOD_NAME = "testValidate_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00415, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0018()
    {
        String STR_METHOD_NAME = "testValidate_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00415, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0019()
    {
        String STR_METHOD_NAME = "testValidate_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "2";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00416, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0020()
    {
        String STR_METHOD_NAME = "testValidate_C0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "0";
        l_request.orderedDate = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00406, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0021()
    {
        String STR_METHOD_NAME = "testValidate_C0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "0";
        l_request.orderedDate = new Date(20081012);
        l_request.orderId = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0022()
    {
        String STR_METHOD_NAME = "testValidate_C0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "0";
        l_request.orderedDate = new Date(20081012);
        l_request.orderId = "142";
        l_request.id = null;

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0023()
    {
        String STR_METHOD_NAME = "testValidate_C0023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "0";
        l_request.orderedDate = new Date(20081012);
        l_request.orderId = "142";
        l_request.id = "";

        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_C0024()
    {
        String STR_METHOD_NAME = "testValidate_C0024()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualSwitchingCompleteRequest l_request =
            new WEB3MutualSwitchingCompleteRequest();
        l_request.mutualProductCode = "1245";
        l_request.specifyDiv = "4";
        l_request.mutualOrderQuantity = "48161";
        l_request.sellBuyDiv = "0";
        l_request.switchingProductCode = "142";
        l_request.switchingTaxType = "0";
        l_request.orderedDate = new Date(20081012);
        l_request.orderId = "142";
        l_request.id = "1245";

        try
        {
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
