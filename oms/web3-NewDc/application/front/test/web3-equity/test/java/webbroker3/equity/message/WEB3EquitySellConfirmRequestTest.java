head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquitySellConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquitySellConfirmRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/24 于瀟（中訊）新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquitySellConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquitySellConfirmRequestTest.class);

    public WEB3EquitySellConfirmRequestTest(String arg0)
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
     * 對應仕様変更1205
     * 
     * 正常通過
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();
        this.superValidatePassData(l_request);
        l_request.id = "";
        l_request.marketCode = WEB3MarketCodeDef.JNX_PTS;
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * 對應仕様変更1205
     * 
     * 抛出異常信息：BUSINESS_ERROR_00608
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();
        this.superValidatePassData(l_request);
        l_request.id = "";
        l_request.marketCode = "";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    private void superValidatePassData(WEB3EquitySellConfirmRequest l_request)
    {
        l_request.orderQuantity = "3";
        l_request.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        l_request.priceCondType = WEB3PriceConditionDef.DEFAULT;
        l_request.execCondType = WEB3ExecutionConditionDef.NO_CONDITION;
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        l_request.orderCondType = WEB3OrderingConditionDef.STOP_LIMIT_PRICE;
        l_request.limitPrice = null;
        l_request.expirationDate = null;
        l_request.wlimitOrderCondPrice = null;
        l_request.wlimitOrderCondOperator = null;
        l_request.wLimitOrderPriceDiv = null;
        l_request.wLimitPrice = null;
        l_request.wlimitExecCondType = null;
        l_request.stopOrderCondPrice = "3";
        l_request.stopOrderCondOperator = WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE;
        l_request.wlimitEnableStatusDiv = null;
    }
}
@
