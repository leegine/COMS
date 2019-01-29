head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBookValuePriceRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmRequest;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmResponse;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBookValuePriceRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBookValuePriceRegistServiceImplTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqBookValuePriceRegistHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3FeqBookValuePriceRegistServiceImplTest.class);

     WEB3FeqBookValuePriceRegistHandler l_handler =
         new WEB3FeqBookValuePriceRegistHandler();
     
    public WEB3FeqBookValuePriceRegistHandlerTest(String arg0)
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

    public void testValidateBookValuePrice_T01()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3FeqBookValuePriceRegistService.class);
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "2";
            l_request.balanceQuantity = "3";
            WEB3FeqBookPriceConfirmResponse l_response =
                l_handler.validateBookValuePrice(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(
                WEB3FeqBookValuePriceRegistService.class,
                new WEB3FeqBookValuePriceRegistServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBookValuePrice_T02()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = null;
            l_request.aftBookAmount = "2";
            l_request.balanceQuantity = "3";
            WEB3FeqBookPriceConfirmResponse l_response =
                l_handler.validateBookValuePrice(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01919, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidateBookValuePrice_T03()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setQuantity(2);
            l_assetParams.setQuantityCannotSell(1);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "2";
            l_request.balanceQuantity = "3";
            WEB3FeqBookPriceConfirmResponse l_response =
                l_handler.validateBookValuePrice(l_request);
            assertEquals("0.66667", l_response.aftBookPrice);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
