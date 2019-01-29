head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IpoOrderValidatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO注文チェック(WEB3IpoOrderValidator.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 孟亜南 (中訊) モデル  No.175
*/
package webbroker3.ipo;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO注文チェック
 * @@author 孟亜南
 *
 */
public class WEB3IpoOrderValidatorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderValidatorTest.class);

    public WEB3IpoOrderValidatorTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * is当選者() == true
     * の他商品買付可能額（0補正無し） < 0 の場合、例外をスローする
     */
    public void test_validatePayAmount_0001()
    {
        final String STR_METHOD_NAME =
            "test_validatePayAmount_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOtherTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(-1));
                    
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(10L);
            l_ipoProductParams.setInstitutionCode("1");   
            l_ipoProductParams.setProductCode("2");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("1");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setPublicOfferingDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            
            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
            l_ipoOrderParams.setIpoOrderId(11L);
            l_ipoOrderParams.setIpoProductId(10L);  
            l_ipoOrderParams.setBranchId(123);
            l_ipoOrderParams.setAccountId(321);
            l_ipoOrderParams.setSubAccountId(256);
            l_ipoOrderParams.setLastOrderActionSerialNo(1);
            l_ipoOrderParams.setQuantity(3);
            l_ipoOrderParams.setLimitPrice(5);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.ELECTION);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(321);
            l_subAccountParams.setSubAccountId(256);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3IpoProductImpl l_ipoProduct = new WEB3IpoProductImpl(10L);
            WEB3IpoOrderImpl l_ipoOrder = new WEB3IpoOrderImpl(11L);
            
            WEB3IpoOrderValidator l_ipoOrderValidator = new WEB3IpoOrderValidator();
            try
            {
                l_ipoOrderValidator.validatePayAmount(l_ipoOrder.getSubAccount(),l_ipoProduct,l_ipoOrder,123D);
                fail();
            }
            catch (WEB3BaseException e)
            {
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02878,e.getErrorInfo());
                log.exiting(STR_METHOD_NAME);
            }
        }
        catch (DataFindException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * is当選者() == true
     * の他商品買付可能額（0補正無し） > 0 の場合、例外をスローする
     */
    public void test_validatePayAmount_0002()
    {
        final String STR_METHOD_NAME =
            "test_validatePayAmount_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOtherTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(1));
                    
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(10L);
            l_ipoProductParams.setInstitutionCode("1");   
            l_ipoProductParams.setProductCode("2");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("1");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setPublicOfferingDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            
            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
            l_ipoOrderParams.setIpoOrderId(11L);
            l_ipoOrderParams.setIpoProductId(10L);  
            l_ipoOrderParams.setBranchId(123);
            l_ipoOrderParams.setAccountId(321);
            l_ipoOrderParams.setSubAccountId(256);
            l_ipoOrderParams.setLastOrderActionSerialNo(1);
            l_ipoOrderParams.setQuantity(3);
            l_ipoOrderParams.setLimitPrice(5);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.ELECTION);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(321);
            l_subAccountParams.setSubAccountId(256);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3IpoProductImpl l_ipoProduct = new WEB3IpoProductImpl(10L);
            WEB3IpoOrderImpl l_ipoOrder = new WEB3IpoOrderImpl(11L);
            
            WEB3IpoOrderValidator l_ipoOrderValidator = new WEB3IpoOrderValidator();
            try
            {
                l_ipoOrderValidator.validatePayAmount(l_ipoOrder.getSubAccount(),l_ipoProduct,l_ipoOrder,123D);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (DataFindException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * is当選者() != true
     * その他商品買付可能額（0補正無し） < 購入申込代金 の場合、例外をスローする。
     */
    public void test_validatePayAmount_0003()
    {
        final String STR_METHOD_NAME =
            "test_validatePayAmount_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOtherTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(2.9027));
                    
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(10L);
            l_ipoProductParams.setInstitutionCode("1");   
            l_ipoProductParams.setProductCode("2");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("1");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setPublicPrice(2.36);
            l_ipoProductParams.setPublicOfferingDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            
            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
            l_ipoOrderParams.setIpoOrderId(11L);
            l_ipoOrderParams.setIpoProductId(10L);  
            l_ipoOrderParams.setBranchId(123);
            l_ipoOrderParams.setAccountId(321);
            l_ipoOrderParams.setSubAccountId(256);
            l_ipoOrderParams.setLastOrderActionSerialNo(1);
            l_ipoOrderParams.setQuantity(3);
            l_ipoOrderParams.setLimitPrice(5);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.DEFEAT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(321);
            l_subAccountParams.setSubAccountId(256);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3IpoProductImpl l_ipoProduct = new WEB3IpoProductImpl(10L);
            WEB3IpoOrderImpl l_ipoOrder = new WEB3IpoOrderImpl(11L);
            
            WEB3IpoOrderValidator l_ipoOrderValidator = new WEB3IpoOrderValidator();
            try
            {
                l_ipoOrderValidator.validatePayAmount(l_ipoOrder.getSubAccount(),l_ipoProduct,l_ipoOrder,1.23);
                fail();
            }
            catch (WEB3BaseException e)
            {
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02879,e.getErrorInfo());
                log.exiting(STR_METHOD_NAME);
            }
        }
        catch (DataFindException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * is当選者() != true
     * その他商品買付可能額（0補正無し） > 購入申込代金 の場合、例外をスローする。
     */
    public void test_validatePayAmount_0004()
    {
        final String STR_METHOD_NAME =
            "test_validatePayAmount_0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOtherTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(2.9037));
                    
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(10L);
            l_ipoProductParams.setInstitutionCode("1");   
            l_ipoProductParams.setProductCode("2");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("1");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setPublicPrice(2.36);
            l_ipoProductParams.setPublicOfferingDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            
            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
            l_ipoOrderParams.setIpoOrderId(11L);
            l_ipoOrderParams.setIpoProductId(10L);  
            l_ipoOrderParams.setBranchId(123);
            l_ipoOrderParams.setAccountId(321);
            l_ipoOrderParams.setSubAccountId(256);
            l_ipoOrderParams.setLastOrderActionSerialNo(1);
            l_ipoOrderParams.setQuantity(3);
            l_ipoOrderParams.setLimitPrice(5);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.DEFEAT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(321);
            l_subAccountParams.setSubAccountId(256);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3IpoProductImpl l_ipoProduct = new WEB3IpoProductImpl(10L);
            WEB3IpoOrderImpl l_ipoOrder = new WEB3IpoOrderImpl(11L);
            
            WEB3IpoOrderValidator l_ipoOrderValidator = new WEB3IpoOrderValidator();
            try
            {
                l_ipoOrderValidator.validatePayAmount(l_ipoOrder.getSubAccount(),l_ipoProduct,l_ipoOrder,1.23);
            }
            catch (WEB3BaseException e)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (DataFindException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
