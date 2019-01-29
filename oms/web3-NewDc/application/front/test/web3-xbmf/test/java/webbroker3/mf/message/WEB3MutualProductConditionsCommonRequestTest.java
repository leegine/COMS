head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualProductConditionsCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.message;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3UnitTypeProductDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualProductConditionsCommonRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualProductConditionsCommonRequestTest.class);

    public WEB3MutualProductConditionsCommonRequestTest(String arg0)
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
     * Test method for 'webbroker3.mf.message.WEB3MutualProductConditionsCommonRequest.validate()'
     */
    public void testValidateC1()
    {
        final String STR_METHOD_NAME = "testValidateC1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01257,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC2()
    {
        final String STR_METHOD_NAME = "testValidateC2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.unitTypeProductDiv = WEB3UnitTypeProductDivDef.NORMAL_PRODUCT;
        
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC3()
    {
        final String STR_METHOD_NAME = "testValidateC3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.frgnMinAmtBuy = "111";
        
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC4()
    {
        final String STR_METHOD_NAME = "testValidateC4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.frgnUnitAmtBuy = "111";
        
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC5()
    {
        final String STR_METHOD_NAME = "testValidateC5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.frgnMinAmtAdd = "111";
        
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC6()
    {
        final String STR_METHOD_NAME = "testValidateC6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.frgnUnitAmtAdd = "111";
        
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC7()
    {
        final String STR_METHOD_NAME = "testValidateC7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.frgnMinAmtSell = "111";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC8()
    {
        final String STR_METHOD_NAME = "testValidateC8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.frgnUnitAmtSell = "111";
        
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateC9()
    {
        final String STR_METHOD_NAME = "testValidateC9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();
        
        mutualProductInfo.deliveryVariation = "0";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 24)募集手数料区分のチェック
     * 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の
     * 　@いずれかではない場合、例外をスローする。
     * 　@　@　@"0：なし"
     * 　@　@　@"1：内枠"
     * 　@　@　@"2：外枠" 
     * 
     *  this.銘柄情報,募集手数料区分 = "0"  
     *  正常結束
     */
    public void testValidateC10()
    {
        final String STR_METHOD_NAME = "testValidateC10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();        
        mutualProductInfo.deliveryVariation = "0";
        
        mutualProductInfo.applyCommissionDiv = "0";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 24)募集手数料区分のチェック
     * 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の
     * 　@いずれかではない場合、例外をスローする。
     * 　@　@　@"0：なし"
     * 　@　@　@"1：内枠"
     * 　@　@　@"2：外枠" 
     * 
     *  this.銘柄情報,募集手数料区分 = "1"  
     *  正常結束
     */
    public void testValidateC11()
    {
        final String STR_METHOD_NAME = "testValidateC11()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();        
        mutualProductInfo.deliveryVariation = "0";
        
        mutualProductInfo.applyCommissionDiv = "1";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 24)募集手数料区分のチェック
     * 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の
     * 　@いずれかではない場合、例外をスローする。
     * 　@　@　@"0：なし"
     * 　@　@　@"1：内枠"
     * 　@　@　@"2：外枠" 
     * 
     *  this.銘柄情報,募集手数料区分 = "2"  
     *  正常結束
     */
    public void testValidateC12()
    {
        final String STR_METHOD_NAME = "testValidateC12()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();        
        mutualProductInfo.deliveryVariation = "0";
        
        mutualProductInfo.applyCommissionDiv = "2";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 24)募集手数料区分のチェック
     * 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の
     * 　@いずれかではない場合、例外をスローする。
     * 　@　@　@"0：なし"
     * 　@　@　@"1：内枠"
     * 　@　@　@"2：外枠" 
     * 
     *  this.銘柄情報,募集手数料区分 = "4"  
     *  BUSINESS_ERROR_02949
     */
    public void testValidateC13()
    {
        final String STR_METHOD_NAME = "testValidateC13()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();        
        mutualProductInfo.deliveryVariation = "0";
        
        mutualProductInfo.applyCommissionDiv = "4";
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02949, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 24)募集手数料区分のチェック
     * 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の
     * 　@いずれかではない場合、例外をスローする。
     * 　@　@　@"0：なし"
     * 　@　@　@"1：内枠"
     * 　@　@　@"2：外枠" 
     * 
     *  this.銘柄情報,募集手数料区分 = null  
     *  
     */
    public void testValidateC14()
    {
        final String STR_METHOD_NAME = "testValidateC14()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualProductConditionsCommonInfo mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        mutualProductInfo.mutualProductCode = "111";
        mutualProductInfo.operationDate = GtlUtils.getSystemTimestamp();        
        mutualProductInfo.deliveryVariation = "0";
        
        mutualProductInfo.applyCommissionDiv = null;
        
        WEB3MutualProductConditionsCommonRequest l_request =
            new WEB3MutualProductConditionsCommonRequest();
        l_request.mutualProductInfo = mutualProductInfo;
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
