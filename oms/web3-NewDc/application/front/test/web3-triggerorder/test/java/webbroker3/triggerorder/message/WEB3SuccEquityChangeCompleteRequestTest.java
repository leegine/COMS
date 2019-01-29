head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccEquityChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3SuccEquityChangeCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/08 安陽(中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccEquityChangeCompleteRequestTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityChangeCompleteRequestTest.class);

    private WEB3SuccEquityChangeCompleteRequest l_request = null;

    public WEB3SuccEquityChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3SuccEquityChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        l_request = null;
    }

    /*
     * Test method for 'webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest.validate()'
     */
    
    //１）　@super.validate()をコールする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２）　@確認時概算受渡代金チェック
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２）　@確認時概算受渡代金チェック
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    //２）　@確認時概算受渡代金チェック
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "!";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);   
    }

    // ３）　@連続注文単価調整値情報チェック
    // ３－１）　@連続注文単価調整値情報≠nullの場合、連続注文単価調整値情報.validate()をコールする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "55";
            
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02243, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    //３－２）　@連続注文単価調整値情報≠nullの場合、注文単価区分≠"成行"の場合は
    //「単価調整値と注文単価区分の指定が不整合」の例外をthrowする。
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "250";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "55";
            
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "99";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02254, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    //４）　@連続注文・注文条件チェック
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "3";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "55";
            
            l_request.priceAdjustmentValueInfo = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    //normal case1
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "55";
            
            l_request.priceAdjustmentValueInfo = null;
            
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
    
    //normal case2
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.orderQuantity = "1000";
            l_request.orderPriceDiv = "0";
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.orderCondType = "0";
            
            l_request.id = "101";
            
            l_request.estimatedPrice = "55";
            
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "99";
            
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);   
    }
}
@
