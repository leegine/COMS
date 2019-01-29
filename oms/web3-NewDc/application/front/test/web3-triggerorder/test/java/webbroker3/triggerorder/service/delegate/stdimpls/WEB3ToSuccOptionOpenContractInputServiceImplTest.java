head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionOpenContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.data.IfoIndexMasterParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputResponse;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionOpenContractInputServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractInputServiceImplTest.class);

    WEB3ToSuccOptionOpenContractInputServiceImpl l_service = null;
    
    public WEB3ToSuccOptionOpenContractInputServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_service = new WEB3ToSuccOptionOpenContractInputServiceImpl();
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(IfoProductParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
        
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
        TestDBUtility.deleteAll(Web3QuoteProtoParams.TYPE);
        
        TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
        TestDBUtility.deleteAll(IfoIndexMasterParams.TYPE);
        
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        l_service = null;
        super.tearDown();
    }

    /**
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccOptionsCancelConfirmRequest l_request = new WEB3SuccOptionsCancelConfirmRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * WEB3ErrorCatalog.BUSINESS_ERROR_00263
     * validate
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccOptionsOpenInputRequest l_request = new WEB3SuccOptionsOpenInputRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     * l_request = null
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccOptionsOpenInputRequest l_request = null;

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create入力画面
     * false
     */
    public void test_createInput_0001()
    {
        final String STR_METHOD_NAME = "test_createInput_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccOptionsOpenInputRequest l_request = new WEB3SuccOptionsOpenInputRequest();
        //建区分
        l_request.contractType = "1";
        //銘柄コード
//        l_request.futProductCode = "";
        //取引市場
//        l_request.marketCode = "";
        //指数種別
//        l_request.targetProductCode = "";
        //限月
//        l_request.delivaryMonth = "200808";
        //連続注文共通情報
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        //（親注文）注文ID
        l_succCommonInfo.parentOrderId = "56";
        //連続注文取引区分
        l_succCommonInfo.succTradingType = "16";
        
        l_request.succCommonInfo = l_succCommonInfo;
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder", new Class[]
                    { SubAccount.class, String.class },
                    null);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080808", "yyyyMMdd").getTime()), "2");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));

//            String[] l_strValues = {"0005"};
//            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, "2");
            
            IfoIndexMasterParams l_ifoIndexMasterParams = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams.setUnderlyingProductCode("0005");
            l_ifoIndexMasterParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams);
            
            EnableOrderConditionParams l_enableOrderConditionParam = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParam.setInstitutionCode("0D");
            l_enableOrderConditionParam.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParam.setFutureOptionDiv("2");
            l_enableOrderConditionParam.setMarginTradingDiv("0");
            l_enableOrderConditionParam.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParam);
            
//            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
//            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
//            l_branchIndexDealtCondParams.setInstitutionCode("0D");
//            l_branchIndexDealtCondParams.setBranchCode("381");
//            l_branchIndexDealtCondParams.setFutureOptionDiv("1");
//            l_branchIndexDealtCondParams.setEnableOrder("1");
//            l_branchIndexDealtCondParams.setTargetProductCode("12");
            
//            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, 
                    ProductTypeEnum.class, 
                    String.class, 
                    String.class, 
                    OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", 
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOptionBuyTradingPower", new Class[]
                    { WEB3GentradeSubAccount.class, IfoProduct.class },
                    null);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3SuccOptionsOpenInputResponse l_response = (WEB3SuccOptionsOpenInputResponse)l_service.createInput(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]{
                        WEB3GentradeSubAccount.class, 
                        ProductTypeEnum.class, 
                        String.class, 
                        String.class, 
                        OrderUnit.class});

           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue.getCalled(0)[0]).getAccountId());
           assertEquals("6", "" + ((ProductTypeEnum)l_paramsValue.getCalled(0)[1]).intValue());
           assertEquals("2", "" + ((String)l_paramsValue.getCalled(0)[2]));
           assertEquals("16", "" + ((String)l_paramsValue.getCalled(0)[3]));
           assertEquals("100.0", "" + ((OrderUnit)l_paramsValue.getCalled(0)[4]).getQuantity());
           
           WEB3MockObjectParamsValue l_paramsValue1 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                   "validateSuccOrderMaxQuantity",
                   new Class[]{ OrderUnit.class });

          assertEquals("100.0", "" + ((OrderUnit)l_paramsValue1.getCalled(0)[0]).getQuantity());
          
          WEB3MockObjectParamsValue l_paramsValue2 =
              TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                  "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                  "isReversingTrade",
                  new Class[]{ String.class, OrderUnit.class });

         assertEquals("16", "" + ((String)l_paramsValue2.getCalled(0)[0]));
         assertEquals("100.0", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[1]).getQuantity());
         
         assertNull(l_response.orderQuantity);
         assertEquals("1", l_response.execCondList[0]);
         assertEquals("0", l_response.orderCondTypeList[0]);
         assertNull(l_response.wlimitExecCondList);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create入力画面
     * true
     */
    public void test_createInput_0002()
    {
        final String STR_METHOD_NAME = "test_createInput_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccOptionsOpenInputRequest l_request = new WEB3SuccOptionsOpenInputRequest();
        //建区分
        l_request.contractType = "1";
        //銘柄コード
//        l_request.futProductCode = "";
        //取引市場
//        l_request.marketCode = "";
        //指数種別
//        l_request.targetProductCode = "";
        //限月
//        l_request.delivaryMonth = "200808";
        //連続注文共通情報
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        //（親注文）注文ID
        l_succCommonInfo.parentOrderId = "56";
        //連続注文取引区分
        l_succCommonInfo.succTradingType = "16";
        
        l_request.succCommonInfo = l_succCommonInfo;
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder", new Class[]
                    { SubAccount.class, String.class },
                    null);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080808", "yyyyMMdd").getTime()), "2");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
//            String[] l_strValues = {"0005"};
//            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, "2");
            
            IfoIndexMasterParams l_ifoIndexMasterParams = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams.setUnderlyingProductCode("0005");
            l_ifoIndexMasterParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams);
            
            EnableOrderConditionParams l_enableOrderConditionParam = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParam.setInstitutionCode("0D");
            l_enableOrderConditionParam.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParam.setFutureOptionDiv("2");
            l_enableOrderConditionParam.setMarginTradingDiv("0");
            l_enableOrderConditionParam.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParam);
            
//            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
//            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
//            l_branchIndexDealtCondParams.setInstitutionCode("0D");
//            l_branchIndexDealtCondParams.setBranchCode("381");
//            l_branchIndexDealtCondParams.setFutureOptionDiv("1");
//            l_branchIndexDealtCondParams.setEnableOrder("1");
//            l_branchIndexDealtCondParams.setTargetProductCode("12");
            
//            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, 
                    ProductTypeEnum.class, 
                    String.class, 
                    String.class, 
                    OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOptionBuyTradingPower", new Class[]
                    { WEB3GentradeSubAccount.class, IfoProduct.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", 
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
                    
            
            WEB3SuccOptionsOpenInputResponse l_response = (WEB3SuccOptionsOpenInputResponse)l_service.createInput(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]{
                        WEB3GentradeSubAccount.class, 
                        ProductTypeEnum.class, 
                        String.class, 
                        String.class, 
                        OrderUnit.class});

           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue.getCalled(0)[0]).getAccountId());
           assertEquals("6", "" + ((ProductTypeEnum)l_paramsValue.getCalled(0)[1]).intValue());
           assertEquals("2", "" + ((String)l_paramsValue.getCalled(0)[2]));
           assertEquals("16", "" + ((String)l_paramsValue.getCalled(0)[3]));
           assertEquals("100.0", "" + ((OrderUnit)l_paramsValue.getCalled(0)[4]).getQuantity());
           
           WEB3MockObjectParamsValue l_paramsValue1 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                   "validateSuccOrderMaxQuantity",
                   new Class[]{ OrderUnit.class });

          assertEquals("100.0", "" + ((OrderUnit)l_paramsValue1.getCalled(0)[0]).getQuantity());
          
          WEB3MockObjectParamsValue l_paramsValue2 =
              TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                  "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                  "isReversingTrade",
                  new Class[]{ String.class, OrderUnit.class });

         assertEquals("16", "" + ((String)l_paramsValue2.getCalled(0)[0]));
         assertEquals("100.0", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[1]).getQuantity());
         
         assertEquals("100", "" + l_response.orderQuantity);
         assertEquals("1", l_response.execCondList[0]);
         assertEquals("0", l_response.orderCondTypeList[0]);
         assertNull(l_response.wlimitExecCondList);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
