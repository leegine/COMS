head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBuyCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3EquityBuyCompleteRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/21 ���n�i���u�j�V�K�쐬
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
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityBuyCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityBuyCompleteRequestTest.class);

    public WEB3EquityBuyCompleteRequestTest(String arg0)
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
     * ����d�l�ύX1205
     * 
     * ����ʉ�
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();
        this.superValidatePassData(l_request);
        l_request.productCode = "";
        l_request.marketCode = WEB3MarketCodeDef.JNX_PTS;
        l_request.taxType = WEB3TaxTypeDef.NORMAL;
        l_request.tradingType = WEB3TradingTypeDef.BUY_ORDER;
        
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
     * ����d�l�ύX1205
     * 
     * �e�o�ُ�M���FBUSINESS_ERROR_00608
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();
        this.superValidatePassData(l_request);
        l_request.productCode = "";
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

    private void superValidatePassData(WEB3EquityBuyCompleteRequest l_request)
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
