head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeOpenContractServiceImplTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建サービスImpl(WEB3OptionChangeOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/05 焦洋 唐性峰 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 *（OP訂正新規建サービスImplのテスト）<BR>
 * 
 * @@author 焦洋 唐性峰
 * @@version 1.0
 */ 
public class WEB3OptionChangeOpenContractServiceImplTest_OT extends TestBaseForMock 
{
	/**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
    .getInstance(WEB3OptionChangeOpenContractServiceImplTest_OT.class);
	
	public WEB3OptionChangeOpenContractServiceImplTest_OT(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);   
        System.out.println();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}
	
    /**
     * 株価指数オプション訂正新規建確認リクエスト.validate
     * this.注文単価区分がnullの値であれば例外をスローする。
     * テスト確認内容: BUSINESS_ERROR_00184
     */
	public void testValidate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        l_request.orderPriceDiv = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
	
    /**
     * OP注文マネージャ.validate新規建訂正注文
     */
	public void testValidate_0002()
    {
		
		TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);	
		
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = 
        	new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        
        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "1";

        //注文期限区分
        l_request.expirationDateType ="3";

        //注文有効期限
        l_request.expirationDate = null;

        //発注条件区分
        l_request.orderCondType = "2";

        //逆指値用プレミアム／原資産価格
        l_request.stopPremium_underlyingAssets = null;

        //逆指値用発注条件単価
        l_request.stopOrderCondPrice = null;

        //逆指値用発注条件演算子
        l_request.stopOrderCondOperator = null;

        //Ｗ指値用プレミアム／原資産価格
        l_request.wlimitPremium_underlyingAssets = "1";

        //Ｗ指値用発注条件単価
        l_request.wlimitOrderCondPrice = "2";

        //Ｗ指値用発注条件演算子
        l_request.wlimitOrderCondOperator = "1";

        //Ｗ指値用注文単価区分
        l_request.wLimitOrderPriceDiv = "1";

        //Ｗ指値用注文単価
        l_request.wLimitPrice = "1";
        
        //W指値用執行条件
        l_request.wlimitExecCondType = "1";
        
        //W指値用有効状態区分
        l_request.wlimitEnableStatusDiv = "1";
        
        //ＩＤ
        l_request.id = "1001";
        
        //注文数量
        l_request.opOrderQuantity = "1000";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE); 
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                                  
            l_serviceImpl.validateOrder(l_request);
            fail();                       
            
        }      
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateChangeOrder", 
                    new Class[] {  WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class});
            
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = 
                (WEB3IfoOpenContractChangeSpec) l_paramsValue.getFirstCalled()[1];
            
            assertTrue(l_ifoOpenContractChangeSpec.getEveningSessionCarryoverFlag());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
	

	
    /**                                                                                                          
     * ①@買建、かつ、リミット注文が有効なW指値注文以外の場合、処理を行う。                                        
     * ②訂正失効日：　@　@リクエストデータ.注文有効期限?!=?nullの場合、                                            
	 * 　@OP注文マネージャ.get注文有効期限(リクエストデータ.注文有効期限,                                          
	 * 　@先物OP銘柄(*1).銘柄コード,市場.getMarketCode(),”オプション”)の戻り値                                   
	 * ③証拠金・余力チェックを行う                                                                               
	 */                                                                                                           
    public void testValidate_0004()                                                                              
	{                                                                                                            
	    String STR_METHOD_NAME = "test_validate_0004()";                                                          
	    log.entering(TEST_START + STR_METHOD_NAME);                                                               
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);                                                                                                                  
	    WEB3OptionChangeOpenContractServiceImpl l_serviceImpl =                                                   
	        new WEB3OptionChangeOpenContractServiceImpl();                                                        
	    WEB3OptionsOpenMarginChangeConfirmRequest l_request =                                                     
	        new WEB3OptionsOpenMarginChangeConfirmRequest();                                                       
	                             
//        onReturn();
        Date l_datBusinessTime = WEB3DateUtility.getDate(
                "2004070710:00:00",
                "yyyyMMddHH:mm:ss");
        
        
	    //注文単価区分                                                                                            
	    l_request.orderPriceDiv = "1";                                                                            
	                                                                                                                  
	    //注文単価                                                                                                
	    l_request.limitPrice = "111";                                                                             
	                                                                                                                  
	    //執行条件                                                                                                
	    l_request.execCondType = "1";                                                                             
	                                                                                                                  
	    //注文期限区分                                                                                            
	    l_request.expirationDateType ="2";                                                                        
	                                                                                                                  
	    //注文有効期限                                                                                            
	    l_request.expirationDate = l_datBusinessTime;                                
	                                                                                                                  
	    //発注条件区分                                                                                            
	    l_request.orderCondType = "2";                                                                            
	                                                                                                                  
	    //逆指値用プレミアム／原資産価格                                                                          
	    l_request.stopPremium_underlyingAssets = null;                                                            
	                                                                                                                  
	    //逆指値用発注条件単価                                                                                    
	    l_request.stopOrderCondPrice = null;                                                                      
	                                                                                                                  
	    //逆指値用発注条件演算子                                                                                  
	    l_request.stopOrderCondOperator = null;                                                                   
	                                                                                                                  
	    //Ｗ指値用プレミアム／原資産価格                                                                          
	    l_request.wlimitPremium_underlyingAssets = "1";                                                           
	                                                                                                                  
	    //Ｗ指値用発注条件単価                                                                                    
	    l_request.wlimitOrderCondPrice = "2";                                                                     
	                                                                                                                  
	    //Ｗ指値用発注条件演算子                                                                                  
	    l_request.wlimitOrderCondOperator = "1";                                                                  
	                                                                                                                  
	    //Ｗ指値用注文単価区分                                                                                    
	    l_request.wLimitOrderPriceDiv = "1";                                                                      
	                                                                                                                  
	    //Ｗ指値用注文単価                                                                                        
	    l_request.wLimitPrice = "1";                                                                              
	                                                                                                                  
	    //W指値用執行条件                                                                                         
	    l_request.wlimitExecCondType = "1";                                                                       
	                                                                                                                  
	    //W指値用有効状態区分                                                                                     
	    l_request.wlimitEnableStatusDiv = "0";                                                                    
	                                                                                                                  
	    //ＩＤ                                                                                                    
	    l_request.id = "1001";                                                                                    
	                                                                                                                  
	    //注文数量                                                                                                
	    l_request.opOrderQuantity = "1000";                                                                       
	                                                                                                                  
	    try                                                                                                       
	    {          
//            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",              
	                "getAccountId",                                                                               
	                new Class[] {},                                                                               
	                new Long(101001010010L));                                                                     
	                                                                                                                  
	        ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();                      
	        OrderValidationResult l_result = new OrderValidationResult(processingResult);                         
	                                                                                                                  
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",                                                 
	                "validateChangeOrder",                                                                        
	                new Class[]                                                                                   
	                { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },                        
	                l_result);                                                                                    
	                                                                                                                  
	        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =                                                
	            new WEB3IfoEstimateDeliveryAmountCalcResult();                                                    
	        l_calcResult.setCalcUnitPrice(0.2D);                                                                  
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",                                                 
	                "calcChangeEstimateDeliveryAmount",                                                           
	                new Class[]                                                                                   
	                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,                   
	                    WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
	                    double.class, boolean.class },                                                            
	                    l_calcResult);                                                                            
	                                                                                                                  
	        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));                
	                                                                                                                  
	        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();                            
	        l_mainAccountParams.setAccountId(101001010010L);                                                      
	        l_mainAccountParams.setIfoAccOpenDivOsaka("1");                                                       
	        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();                               
	        l_subAccountParams.setAccountId(101001010010L);                                                       
	        l_subAccountParams.setSubAccountId(10100101001007L);                                                  
	        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);                          
	        BranchParams l_branchParams = TestDBUtility.getBranchRow();                                           
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();                         
	        l_ifoOrderUnitParams.setBizDate("20040707");                                                          
            MarketParams l_mp = TestDBUtility.getMarketRow();                                                                          
            l_mp.setMarketId(1002L);
            
	        TestDBUtility.deleteAll(MainAccountRow.TYPE);     
            TestDBUtility.deleteAll(MarketParams.TYPE);      
	        TestDBUtility.deleteAll(SubAccountRow.TYPE);                                                          
	        TestDBUtility.deleteAll(BranchRow.TYPE);                                                              
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);                                                        
	                                                                                                                  
	        TestDBUtility.insertWithDel(l_subAccountParams);                                                      
	        TestDBUtility.insertWithDel(l_mainAccountParams);                                                     
	        TestDBUtility.insertWithDel(l_branchParams);                                                          
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);              
            TestDBUtility.insertWithDel(l_mp);          
	                                                                                                                  
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);                                                        
	        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);                            
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);                                                    
	                                                                                                                  
	        TestDBUtility.deleteAll(MarketParams.TYPE);                                                           
	        MarketParams l_marketParams = TestDBUtility.getMarketRow();                                           
	        l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());                                       
	        l_marketParams.setInstitutionCode("10");                                                              
	        TestDBUtility.insertWithDel(l_marketParams);                                                          
	                                                                                                                  
	        TestDBUtility.deleteAll(InstitutionParams.TYPE);                                                      
	        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();                            
	        l_institutionParams.setInstitutionId(33L);                                                            
	        l_institutionParams.setInstitutionCode("10");                                                         
	        TestDBUtility.insertWithDel(l_institutionParams);                                                     
	                                                                                                                  
            TestDBUtility.deleteAll(ProductParams.TYPE);           
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());     
            TestDBUtility.insertWithDel(l_productParams);    

            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(1002L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setMarketId(1002L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setMarketId(1002L);
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040708");
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);  
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow(); 
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());  
            l_tradedProductParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setBaseDate(l_datBusinessTime);
            TestDBUtility.insertWithDel(l_tradedProductParams);          
            
            TestDBUtility.deleteAll(TradedProductUpdqParams.TYPE);  
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow(); 
            l_tradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());  
            l_tradedProductUpdqParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductUpdqParams.setMarketId(1002L);
            l_tradedProductUpdqParams.setBaseDate(WEB3DateUtility.addDay(l_datBusinessTime, 1));
            TestDBUtility.insertWithDel(l_tradedProductParams);                                   
	                                                                                                                  
	        TestDBUtility.deleteAll(TraderParams.TYPE);                                                           
	        TraderParams l_traderParams = TestDBUtility.getTraderRow();                                           
	        TestDBUtility.insertWithDel(l_traderParams);                                                          
	                                                                                                                  
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",              
	                "getSessionProperty",                                                                         
	                new Class[] {String.class},                                                                   
	                "1");                                                                                         
	                                                                                                                  
	        LoginInfo l_lifo = new LoginInfoImpl();                                                               
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",              
	                "getLoginInfo",                                                                               
	                new Class[] {},                                                                               
	                l_lifo);                                                                                      
	                                                                                                                  
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",              
	                "getLoginId",                                                                                 
	                new Class[] {},                                                                               
	                new Long(l_traderParams.getLoginId()));                                                       
	                                                                                                                  
	        WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();                              
	        l_powerResult.setAttentionObjectionType("0");                                                         
	        l_powerResult.setResultFlg(false);                                                                    
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",                                      
	                "validateTradingPower",                                                                       
	                new Class[] {WEB3GentradeSubAccount.class, Object[].class,                                    
	                Object[].class, OrderTypeEnum.class,boolean.class},                                           
	                l_powerResult);                                                                               
	                                                                                                                  
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "webbroker3.ifo.WEB3IfoProductManagerImpl",                                                   
	                "getTradedProduct", new Class[]                                                               
	                { long.class, long.class },                                                                   
	                new WEB3IfoTradedProductImpl(1006169090018L));                                                
	                                                                                                                  
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                                                    
	                "webbroker3.ifo.WEB3OptionOrderManagerImpl",                                                  
	                "getExpirationDate",                                                                          
	                new Class[] {Date.class, String.class, String.class, String.class},                           
                    l_datBusinessTime);                                             
	                                                                                                             
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBusinessTime);
            
            TradingTimeParams tt = TestDBUtility.getTradingTimeRow();
            tt.setBranchCode("123");
            tt.setMarketCode("0");
            tt.setTradingTimeType("01");
            tt.setProductCode("1");
            tt.setBizDateType("1");
            tt.setBizdateCalcParameter("0");
            tt.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(tt);
            
            TradingTimeParams tt1 = TestDBUtility.getTradingTimeRow();
            tt1.setBranchCode("123");
            tt1.setMarketCode("0");
            tt1.setTradingTimeType("01");
            tt1.setProductCode("2");
            tt1.setBizDateType("1");
            tt1.setBizdateCalcParameter("0");
            tt1.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(tt1);
            
	        WEB3OptionsOpenMarginChangeConfirmResponse l_response = l_serviceImpl.validateOrder(l_request);       
	        assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), "19700101");          
	                                                                                                                  
	    }                                                                                                         
	    catch (WEB3BaseException e)                                                                               
	    {                                                                                                         
	        log.debug("" + e.getErrorInfo());                                                                     
	        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306, e.getErrorInfo());                                
	        log.exiting(TEST_END + STR_METHOD_NAME);                                                              
	        }                                                                                                         
	    catch (Exception l_ex)                                                                                    
	    {                                                                                                         
	        log.error("", l_ex);                                                                                  
	        log.debug(l_ex.getMessage(), l_ex);                                                                   
	        log.exiting(TEST_END + STR_METHOD_NAME);                                                              
	        fail();                                                                                               
	    }                                                                                                         
	 }                                                                                                                                                                                                                      
	
    

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute_case001()
    {
        final String STR_METHOD_NAME = "testExecute_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesCloseMarginConfirmRequest l_request =
                new WEB3FuturesCloseMarginConfirmRequest();
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            l_impl.execute(l_request);
            fail();
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80018);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImpl.submitOrder(WEB3OptionsOpenMarginChangeCompleteRequest)'
     */
    /**
     * validate( )
     */
    public void testSubmitOrder_case001()
    {
        final String STR_METHOD_NAME = "testValidate_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            l_impl.submitOrder(l_request);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    /**
     *①@リクエストデータ.確認時発注日 == nullの場合
     *  確認時発注日 = 取引時間管理.get発注日()
     *②validate新規建訂正注文()
     *③get発注日 : リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値
     */
    public void testSubmitOrder_case002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";

            //W指値用執行条件
            l_request.wlimitExecCondType = "1";

            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "1";

            //ＩＤ
            l_request.id = "1001";

            //注文数量
            l_request.opOrderQuantity = "5";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                             "getSessionProperty",
                             new Class[] {String.class},
                             "1");
                
            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateChangeOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(processingResult));
                    
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));
            
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20040102","yyyyMMdd"));
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            l_impl.submitOrder(l_request);
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();

        }

    }

    /**
     * ①@　@リクエストデータ.注文有効期限?!=?nullの場合、OP注文マネージャ.get注文有効期限(
     * 　@　@リクエストデータ.注文有効期限,先物OP銘柄(*1).銘柄コード,市場.getMarketCode(),”オプション”)の戻り値
     * ②　@完了処理直接起動時 かつ リミット注文が有効なW指値注文（買建）の場合
     * （リクエストデータ.確認時発注日 == null &&
     *  注文単位.getSide() == "BUY" &&
     *　@新規建訂正注文内容.発注条件() == "W指値" &&
     *  リクエストデータ.Ｗ指値用有効状態区分 == "リミット注文有効"）、処理を行う。
     * ③リクエストデータ.確認時発注日 == nullの場合、
     * 　@手数料.is指値 =　@リクエスト.注文単価!=0ならば、true。
     * ④is指値：　@新規建訂正内容.(W指値)訂正指値 != 0の場合、true。
     */
     
    public void testSubmitOrder_case003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="2";

            //注文有効期限
            l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";

            //W指値用執行条件
            l_request.wlimitExecCondType = "1";

            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "0";

            //ＩＤ
            l_request.id = "1001";

            //注文数量
            l_request.opOrderQuantity = "5";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20040102","yyyyMMdd"));
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            //************************************************
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(1006169090018L);
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setTradedProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institution);
            
            //******************************************
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(0);
            Date l_datpreBizDate = new Timestamp(19700101);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            


            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
//            l_calcResult.setRestraintTurnover(50D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
//            assertEquals("100.0", l_paramsValue1.getFirstCalled()[1] + "");
            
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            WEB3OptionsOpenMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
//            assertEquals(l_response.lastUpdatedTimestamp,GtlUtils.getSystemTimestamp());
            log.debug("system time : "+l_response.lastUpdatedTimestamp);
            assertEquals(l_response.orderActionId,"1001");
            assertFalse(l_response.succSettingFlag);
            

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }

    }

    /**
     * ①@　@リクエストデータ.確認時発注日 ！= nullの場合、
     * 　@　@手数料.is指値 =　@this.is指値(リクエストデータ)。
     * ②    完了処理直接起動時 かつ リミット注文が有効なW指値注文（買建）以外の場合、処理を行う。
     * ③　@予約注文確認要（is予約注文確認要() == true）の場合
     * ④　@[リクエストデータ.確認時発注日 != nullの場合]
     * 　@　@リクエストデータ.確認時単価 == nullの場合、 リクエストデータ.注文単価(*1)をセット。 
     * 　@(*1　@注文単価==nullの場合、ゼロをセット）
     */
    public void testSubmitOrder_case004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";

            //W指値用執行条件
            l_request.wlimitExecCondType = "1";

            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "0";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("19700101","yyyyMMdd");

            //ＩＤ
            l_request.id = "1001";

            //注文数量
            l_request.opOrderQuantity = "5";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = 
                TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setAccountId(l_ifoOrderUnitParams.getAccountId());
            l_rsvIfoOrderUnitParams.setSubAccountId(l_ifoOrderUnitParams.getSubAccountId());
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            //************************************************
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setTradedProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setTradedProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institution);
            
            //******************************************
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new Timestamp(19700101);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            

            


            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            WEB3OptionsOpenMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
//            assertEquals(l_response.lastUpdatedTimestamp,GtlUtils.getSystemTimestamp());
            log.debug("system time : "+l_response.lastUpdatedTimestamp);
            assertEquals(l_response.orderActionId,"1001");
            assertTrue(l_response.succSettingFlag);
            

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }  
    }
    

    /**
     * ①@　@リクエストデータ.確認時発注日 == nullの場合、
     * 　@　@手数料.is指値 =　@リクエスト.注文単価==0ならば、false。
     * ②　@[リクエストデータ.確認時発注日 == nullの場合]
     * 　@　@　@リクエストデータ.注文単価(*1)をセット。
     * 　@　@　@(*1　@注文単価==nullの場合、ゼロをセット）
     * ③　@is指値：　@新規建訂正内容.(W指値)訂正指値?==?0の場合、false。
     * ④　@ validate取引余力()
     */
    public void testSubmitOrder_case005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            //注文単価区分
            l_request.orderPriceDiv = "0";

            //注文単価
            l_request.limitPrice = null;

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";

            //W指値用執行条件
            l_request.wlimitExecCondType = "1";

            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "0";

            //ＩＤ
            l_request.id = "1001";

            //注文数量
            l_request.opOrderQuantity = "5";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            //************************************************
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setTradedProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setTradedProductId(1006169090018L);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institution);
            
            //******************************************
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new Timestamp(19700101);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcChangeEstimateDeliveryAmount",
                new Class[]
                { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                    WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                    double.class, boolean.class },
                    l_calcResult);
            
        
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
           l_impl.submitOrder(l_request);
           fail();           

        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01306);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }  
    }

    /**
     * ①@　@[リクエストデータ.確認時発注日 != nullの場合]
     * 　@　@リクエストデータ.確認時単価 != nullの場合、リクエストデータ.確認時単価。
     */
    public void testSubmitOrder_case006()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "1";

            //W指値用執行条件
            l_request.wlimitExecCondType = "1";

            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "0";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("19700101","yyyyMMdd");

            //確認時単価
            l_request.checkPrice = "120";

            //ＩＤ
            l_request.id = "1001";

            //注文数量
            l_request.opOrderQuantity = "5";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            //************************************************
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setTradedProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setTradedProductId(1006169090018L);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institution);
            
            //******************************************
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new Timestamp(19700101);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            

            


            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            WEB3OptionsOpenMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
//            assertEquals(l_response.lastUpdatedTimestamp,GtlUtils.getSystemTimestamp());
            log.debug("system time : "+l_response.lastUpdatedTimestamp);
            assertEquals(l_response.orderActionId,"1001");
            assertFalse(l_response.succSettingFlag);
            

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }  
    }

    /**
     * ①@　@[リクエストデータ.確認時発注日 != nullの場合]
     * 　@　@リクエストデータ.確認時単価 == nullの場合、
     * 　@　@注文単位.getSide() == "買" and 
     *     リクエストデータ.注文単価(*1) < リクエストデータ.W指値用注文単価(*2) ならば、
     *     リクエストデータ.W指値用注文単価(*2)をセット。
     *     (*2　@W指値用注文単価==nullの場合、ゼロをセット）
     * ②　@is指値：　@新規建訂正内容.(W指値)訂正指値?==?0の場合、false。
     */
    public void testSubmitOrder_case007()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            //注文単価区分
            l_request.orderPriceDiv = "1";

            //注文単価
            l_request.limitPrice = "111";

            //執行条件
            l_request.execCondType = "1";

            //注文期限区分
            l_request.expirationDateType ="3";

            //注文有効期限
            l_request.expirationDate = null;

            //発注条件区分
            l_request.orderCondType = "2";

            //逆指値用プレミアム／原資産価格
            l_request.stopPremium_underlyingAssets = null;

            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;

            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;

            //Ｗ指値用プレミアム／原資産価格
            l_request.wlimitPremium_underlyingAssets = "1";

            //Ｗ指値用発注条件単価
            l_request.wlimitOrderCondPrice = "2";

            //Ｗ指値用発注条件演算子
            l_request.wlimitOrderCondOperator = "1";

            //Ｗ指値用注文単価区分
            l_request.wLimitOrderPriceDiv = "1";

            //Ｗ指値用注文単価
            l_request.wLimitPrice = "115";

            //W指値用執行条件
            l_request.wlimitExecCondType = "1";

            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = "0";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("19700101","yyyyMMdd");

            //ＩＤ
            l_request.id = "1001";

            //注文数量
            l_request.opOrderQuantity = "5";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(101001010010L));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Timestamp(20040101));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class,
                        double.class,
                        SideEnum.class,
                        boolean.class,
                        double.class,
                        double.class,
                        boolean.class},
                        new WEB3IfoEstimateDeliveryAmountCalcResult());
        
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {
                            WEB3GentradeSubAccount.class,
                            Object[].class, Object[].class,
                            OrderTypeEnum.class,boolean.class},
                            l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "submitChangeOrder",
                new Class[]{ SubAccount.class, ChangeOrderSpec.class, String.class, boolean.class },
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001L);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            //************************************************
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setTradedProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setTradedProductId(1006169090018L);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institution);
            
            //******************************************
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new Timestamp(19700101);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            

            


            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            WEB3OptionsOpenMarginChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
//            assertEquals(l_response.lastUpdatedTimestamp,GtlUtils.getSystemTimestamp());
            log.debug("system time : "+l_response.lastUpdatedTimestamp);
            assertEquals(l_response.orderActionId,"1001");
            assertFalse(l_response.succSettingFlag);
            

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }  
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImpl.isLimitPrice(WEB3OptionsOpenMarginChangeCompleteRequest)'
     */
    /**
     * リクエストデータ = null。
     */
    public void testIsLimitPrice_case001()
    {
        final String STR_METHOD_NAME = "testIsLimitPrice_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            l_impl.isLimitPrice(null);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    /**
     *確認時単価 == nullの場合、trueを返却する。
     */
    public void testIsLimitPrice_case002()
    {
        final String STR_METHOD_NAME = "testIsLimitPrice_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            assertTrue(l_impl.isLimitPrice(l_request));
            
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    /**
     *確認時単価 == nullの場合、falseを返却する。
     */
    public void testIsLimitPrice_case003()
    {
        final String STR_METHOD_NAME = "testIsLimitPrice_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3OptionsOpenMarginChangeCompleteRequest l_request =
                new WEB3OptionsOpenMarginChangeCompleteRequest();
            l_request.checkPrice = "123";
            WEB3OptionChangeOpenContractServiceImpl l_impl =
                new WEB3OptionChangeOpenContractServiceImpl();
            assertFalse(l_impl.isLimitPrice(l_request));
            
        }
        catch (Exception l_ex)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    /**
     * ①@買建、かつ、リミット注文が有効なW指値注文の場合、処理を行う。
     * ②is指値：　@新規建訂正内容.(W指値)訂正指値?!=?0の場合、true。   
     * ③リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値     
     */
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);   
        
//        onReturn();
        
        WEB3OptionChangeOpenContractServiceImpl l_serviceImpl = 
            new WEB3OptionChangeOpenContractServiceImpl();
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = 
            new WEB3OptionsOpenMarginChangeConfirmRequest();
             

        Date l_datBusinessTime = WEB3DateUtility.getDate(
                "2004070710:00:00",
                "yyyyMMddHH:mm:ss");
        

        
        //注文単価区分
        l_request.orderPriceDiv = "1";

        //注文単価
        l_request.limitPrice = "111";

        //執行条件
        l_request.execCondType = "1";

        //注文期限区分
        l_request.expirationDateType ="3";

        //注文有効期限
        l_request.expirationDate = null; 

        //発注条件区分
        l_request.orderCondType = "2";

        //逆指値用プレミアム／原資産価格
        l_request.stopPremium_underlyingAssets = null;

        //逆指値用発注条件単価
        l_request.stopOrderCondPrice = null;

        //逆指値用発注条件演算子
        l_request.stopOrderCondOperator = null;

        //Ｗ指値用プレミアム／原資産価格
        l_request.wlimitPremium_underlyingAssets = "1";

        //Ｗ指値用発注条件単価
        l_request.wlimitOrderCondPrice = "2";

        //Ｗ指値用発注条件演算子
        l_request.wlimitOrderCondOperator = "1";

        //Ｗ指値用注文単価区分
        l_request.wLimitOrderPriceDiv = "1";

        //Ｗ指値用注文単価
        l_request.wLimitPrice = "1";
        
        //W指値用執行条件
        l_request.wlimitExecCondType = "1";
        
        //W指値用有効状態区分
        l_request.wlimitEnableStatusDiv = "0";
        
        //ＩＤ
        l_request.id = "1001";
        
        //注文数量
        l_request.opOrderQuantity = "1000";
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeOrder", 
                    new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractChangeSpec.class },
                    l_result);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            

                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBizDate("20040707");
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE); 
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE); 
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams); 

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);           
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());        
            TestDBUtility.insertWithDel(l_productParams);    

            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductParams.setMarketId(1002L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setMarketId(1002L);
            l_ifoTradedProductUpdqParams.setValidForBizDate("20040708");
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);  
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow(); 
            l_tradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());  
            l_tradedProductParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setBaseDate(l_datBusinessTime);
            TestDBUtility.insertWithDel(l_tradedProductParams);          
            
            TestDBUtility.deleteAll(TradedProductUpdqParams.TYPE);  
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow(); 
            l_tradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());  
            l_tradedProductUpdqParams.setTradedProductId(l_ifoOrderUnitParams.getProductId());
            l_tradedProductUpdqParams.setMarketId(1002L);
            l_tradedProductUpdqParams.setBaseDate(WEB3DateUtility.addDay(l_datBusinessTime, 1));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            LoginInfo l_lifo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_lifo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_traderParams.getLoginId()));
            
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setAttentionObjectionType("0");
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                    Object[].class, OrderTypeEnum.class,boolean.class},
                    l_powerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getTradedProduct", new Class[]
                    { long.class, long.class },
                    new WEB3IfoTradedProductImpl(1006169090018L));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBusinessTime);
            
            TradingTimeParams tt = TestDBUtility.getTradingTimeRow();
            tt.setBranchCode("123");
            tt.setMarketCode("0");
            tt.setTradingTimeType("01");
            tt.setProductCode("1");
            tt.setBizDateType("1");
            tt.setBizdateCalcParameter("0");
            tt.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(tt);
            
            TradingTimeParams tt1 = TestDBUtility.getTradingTimeRow();
            tt1.setBranchCode("123");
            tt1.setMarketCode("0");
            tt1.setTradingTimeType("01");
            tt1.setProductCode("2");
            tt1.setBizDateType("1");
            tt1.setBizdateCalcParameter("0");
            tt1.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(tt1);
            
            WEB3OptionsOpenMarginChangeConfirmResponse l_response = l_serviceImpl.validateOrder(l_request);
            
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.expirationDate, l_datBusinessTime));
            
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
 
    }
    
    public void onReturn()
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
    
}

@
