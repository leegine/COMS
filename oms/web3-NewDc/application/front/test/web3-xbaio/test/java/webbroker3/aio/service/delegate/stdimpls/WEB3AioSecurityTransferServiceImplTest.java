head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSecurityTransferServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : (WEB3AioSecurityTransferServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/21 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AioSecurityTransferConfirmRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq
 */
public class WEB3AioSecurityTransferServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferServiceImplTest.class);
    
    WEB3AioSecurityTransferServiceImpl l_mpl =
        new WEB3AioSecurityTransferServiceImpl();
    
    WEB3AioSecurityTransferConfirmRequest l_request =
        new WEB3AioSecurityTransferConfirmRequest();
    
    public WEB3AioSecurityTransferServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferServiceImpl.validateOrder(WEB3AioSecurityTransferConfirmRequest)'
     */
    public void testValidateOrder1()
    {
        final String STR_METHOD_NAME = "testValidateOrder1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.transferQuantity = "2125435";
            l_request.instrumentsType = "3";
            l_request.productCode = "1";
            l_request.taxType = "0";
            l_request.depositDiv  = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams. setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220302L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams1.setAccountId(333812512245L);
            l_subAccountParams.setSubAccountId(33381251220304L);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInputUnit("2");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
           
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
            Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeMainAccount",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(33381L));
                l_mpl.validateOrder(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals("êUë÷êîó ÇÃÉTÉCÉYÇ™ïsê≥Ç≈Ç∑ÅB", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testValidateOrder2()
    {
        final String STR_METHOD_NAME = "testValidateOrder2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.transferQuantity = "2000000";
            l_request.instrumentsType = "3";
            l_request.productCode = "1";
            l_request.taxType = "0";
            l_request.depositDiv  = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams. setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220302L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220304L);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInputUnit("2");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
           
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
            Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioBizLogicProvider",
                    "calcTransPossibleAmount",
                    new Class[] {long.class, 
                            ProductTypeEnum.class, 
                            long.class,
                            TaxTypeEnum.class, 
                            String.class },
                    new Double(2125437));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_result);

            
            WEB3AioSecurityTransferConfirmResponse l_response = l_mpl.validateOrder(l_request);
//            assertEquals("88003" , l_response.orderId);
//            assertEquals(WEB3DateUtility.getDate("20070609","yyyymmdd") , l_response.checkDate);
                
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder3()
    {
        final String STR_METHOD_NAME = "testValidateOrder3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.transferQuantity = "2000000";
            l_request.instrumentsType = "3";
            l_request.productCode = "1";
            l_request.taxType = "0";
            l_request.depositDiv  = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams. setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220302L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220304L);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInputUnit("2");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
           
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
            Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioBizLogicProvider",
                    "calcTransPossibleAmount",
                    new Class[] {long.class, 
                            ProductTypeEnum.class, 
                            long.class,
                            TaxTypeEnum.class, 
                            String.class },
                    new Double(2125437));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_result);

            
            WEB3AioSecurityTransferConfirmResponse l_response = l_mpl.validateOrder(l_request);
//            assertEquals("88003" , l_response.orderId);
//            assertEquals(WEB3DateUtility.getDate("20070609","yyyymmdd") , l_response.checkDate);
                
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrder4()
    {
        final String STR_METHOD_NAME = "testValidateOrder4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.transferQuantity = "2000000";
            l_request.instrumentsType = "3";
            l_request.productCode = "1";
            l_request.taxType = "0";
            l_request.depositDiv  = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams. setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220302L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220304L);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInputUnit("1");
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
           
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "validateOrder",
                    new Class[] {SubAccount.class},
                    null);
            Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioBizLogicProvider",
                    "calcTransPossibleAmount",
                    new Class[] {long.class, 
                            ProductTypeEnum.class, 
                            long.class,
                            TaxTypeEnum.class, 
                            String.class },
                    new Double(2125437));
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_result);

            
            WEB3AioSecurityTransferConfirmResponse l_response = l_mpl.validateOrder(l_request);
            Date l_strDate = WEB3DateUtility.getDate("20070609", "yyyyMMdd");
            WEB3DateUtility.compare(l_response.checkDate, l_strDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
