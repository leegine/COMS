head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashoutServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AioCashoutServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/19 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.AmountRangeParams;
import webbroker3.aio.data.AmountRangeRow;
import webbroker3.aio.message.WEB3AioCashoutCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutConfirmResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.data.TransferedFinInstitutionParams;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3AioCashoutServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutServiceImplTest.class);
    
    WEB3AioCashoutServiceImpl l_mpl = new WEB3AioCashoutServiceImpl();
    public WEB3AioCashoutServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateOrder1()
    {
        final String STR_METHOD_NAME = "testValidateOrder1()";
        log.entering(STR_METHOD_NAME);
        WEB3AioCashoutConfirmRequest l_request = new WEB3AioCashoutConfirmRequest();
        l_request.cashoutAmt = "1234567";
        l_request.transScheduledDate = WEB3DateUtility.getDate("99990101", "yyyyMMdd");
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[]{},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_mpl.validateOrder(l_request);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getErrorMessage() + l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00774, l_ex.getErrorInfo());
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
        WEB3AioCashoutConfirmRequest l_request = new WEB3AioCashoutConfirmRequest();
        l_request.cashoutAmt = "1234567";
        l_request.transScheduledDate = WEB3DateUtility.getDate("99990101", "yyyyMMdd");
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[]{},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1222222222L));
            
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Date.class},
                    new Double(100));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(AioOrderUnitRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBankAccountRegi("1");
            l_mainAccountParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(123);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setPaymentReserve("0");
            l_institutionParams.setTheDayTransfer("1");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility.getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setInstitutionCode("0D");
            l_transferedFinInstitutionParams.setBranchCode("381");
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv("5");
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("99990101","yyyyMMdd"));
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(AmountRangeRow.TYPE);
            AmountRangeParams l_amountRangeParams = TestDBUtility.getAmountRangeRow();
            TestDBUtility.insertWithDel(l_amountRangeParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);            
            
            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_result);
            
            WEB3AioCashoutConfirmResponse l_response = l_mpl.validateOrder(l_request);
            assertEquals("1", l_response.transRegistDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testsubmitOrder1()
    {
        final String STR_METHOD_NAME = "testsubmitOrder1()";
        log.entering(STR_METHOD_NAME);
        WEB3AioCashoutCompleteRequest l_request = new WEB3AioCashoutCompleteRequest();
        l_request.cashoutAmt = "1234567";
        l_request.transScheduledDate = WEB3DateUtility.getDate("99990101", "yyyyMMdd");
        l_request.checkDate = WEB3DateUtility.getDate("20070228", "yyyyMMdd");
        l_request.checkOrderID = 123;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[]{},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_mpl.submitOrder(l_request);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getErrorMessage() + l_ex);
            assertEquals("êUçûêÊìoò^ãÊï™= 0Åiñ¢ìoò^Åj", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testsubmitOrder2()
    {
        final String STR_METHOD_NAME = "testsubmitOrder2()";
        log.entering(STR_METHOD_NAME);
        WEB3AioCashoutCompleteRequest l_request = new WEB3AioCashoutCompleteRequest();
        l_request.cashoutAmt = "1234567";
        l_request.transScheduledDate = WEB3DateUtility.getDate("99990101", "yyyyMMdd");
        l_request.checkDate = WEB3DateUtility.getDate("20070228", "yyyyMMdd");
        l_request.checkOrderID = 123;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[]{},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1222222222L));
            
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Date.class},
                    new Double(100));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(AioOrderUnitRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBankAccountRegi("1");
            l_mainAccountParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setPaymentReserve("0");
            l_institutionParams.setTheDayTransfer("1");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility.getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setInstitutionCode("0D");
            l_transferedFinInstitutionParams.setBranchCode("381");
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv("5");
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);
            WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(AmountRangeRow.TYPE);
            AmountRangeParams l_amountRangeParams = TestDBUtility.getAmountRangeRow();
            TestDBUtility.insertWithDel(l_amountRangeParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_result);
            
            NewOrderValidationResult l_newOrderValidationResult = 
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, 123);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "validateNewOrder",
                    new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class},
                    l_newOrderValidationResult);
            
            ProcessingResult l_PResult = ProcessingResult.newSuccessResultInstance();
            OrderSubmissionResult l_orderSR = new OrderSubmissionResult(l_PResult);
         
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "submitNewOrder",
                new Class[] {SubAccount.class, ProductTypeEnum.class, NewOrderSpec.class, long.class, String.class, boolean.class},
                l_orderSR);
            
            String l_strNewNumber = "111";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },
                l_strNewNumber);
            
            WEB3AioCashoutCompleteResponse l_response = l_mpl.submitOrder(l_request);
            assertEquals("1", l_response.transRegistDiv);
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
