head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSwitchOrderRouteServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注先切替サービス実装クラステスト(WEB3AdminSwitchOrderRouteServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/19  孟亜南 (中訊) 新規作成
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketCodeSONARDef;
import webbroker3.common.define.WEB3OrderEngineDivDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SubmitMqTriggerDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注先切替サービス実装クラステスト)<BR>
 * 
 * @@author 孟亜南(中訊)
 * @@version 1.0
 */
public class WEB3AdminSwitchOrderRouteServiceImplTest extends TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminSwitchOrderRouteServiceImplTest.class);
    
	public WEB3AdminSwitchOrderRouteServiceImplTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
    
    public void testSubmitOrderRouteChange_0002()
    {
        final String STR_METHOD_NAME = " testSubmitOrderRouteChange_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminSwitchOrderRouteCompleteRequest l_request = new WEB3AdminSwitchOrderRouteCompleteRequest();
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit = new WEB3AdminOrderRouteSwitchingInfo();
   
        //変換市場コード
        l_infoUnit.convertMarketCode = "12"; 
    
        l_infoUnit.marketCode = "10";
        
        l_infoUnit.frontOrderSystemCode = "1";
        
        // 変更後有効フラグを取得
        l_infoUnit.changedValidFlag = "1";
        
        // 銘柄タイプ
        l_infoUnit.productType = "5";
        
        // 発注経路区分
        l_infoUnit.submitOrderRouteDiv = "9";
        
        // 有効フラグ
        l_infoUnit.validFlag = "7";
        
        // 証券会社コード
        l_request.institutionCode = "bbb";
        
        l_request.password = "8989";
        l_request.infoUnit = l_infoUnit;
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        
        l_mainAccountParams.setInstitutionId(12);
        
        l_mainAccountParams.setBranchId(123);
        
        l_mainAccountParams.setAccountCode("213");
        MainAccountImpl l_mainAccountImpl = new MainAccountImpl(l_mainAccountParams);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {long.class},
            l_mainAccountImpl);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "lockAccount",
            new Class[] {String.class,String.class,String.class},
            null);
     
        WEB3AdminSwitchOrderRouteServiceImpl  l_adminSwitchOrderRouteServiceImpl =new WEB3AdminSwitchOrderRouteServiceImpl();
                
        try
        {
            TestDBUtility.insertWithDel(this.getEqtypeTradedProductRow());
            
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            TestDBUtility.insertWithDel(this.getAdministratorTypeRow());

            TestDBUtility.insertWithDel(this.getLoginTypeRow());
            TestDBUtility.insertWithDel(this.getLoginTypeAttributeRow());
            TestDBUtility.insertWithDel(this.getInstitutionRow());
            TestDBUtility.insertWithDel(this.getMarketRow());
            TestDBUtility.insertWithDelAndCommit(this.getEqtypeOrderUnitRow());
            
            TestDBUtility.insertWithDel(this.getBranchRow());
            TestDBUtility.insertWithDel(this.getEquityProductRow());
            TestDBUtility.insertWithDel(this.getProductRow());
            
            TradedProductParams l_tradedProductParams = this.getTradedProductRow();
            
            //銘柄ID
            l_tradedProductParams.setProductId(78954654);
            //市場ID
            l_tradedProductParams.setMarketId(89765);
            
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            l_adminSwitchOrderRouteServiceImpl.submitOrderRouteChange(l_request);
            
            List l_eqOrderList = (List) new ArrayList();       
            
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";
                    
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
                {
                "555555"
                };
            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            l_eqOrderList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_eqOrderList.get(0);
            
            assertEquals("0",l_eqtypeOrderUnitRow.getSubmitOrderRouteDiv());
            assertEquals(new Timestamp(0L),l_eqtypeOrderUnitRow.getLastUpdatedTimestamp());
        } 
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitOrderRouteChange_0004()
    {
        final String STR_METHOD_NAME = " testSubmitOrderRouteChange_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminSwitchOrderRouteCompleteRequest l_request = new WEB3AdminSwitchOrderRouteCompleteRequest();
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit = new WEB3AdminOrderRouteSwitchingInfo();
   
        //変換市場コード
        l_infoUnit.convertMarketCode = "12"; 
    
        l_infoUnit.marketCode = "11";
        
        l_infoUnit.frontOrderSystemCode = "1";
        
        // 変更後有効フラグを取得
        l_infoUnit.changedValidFlag = "1";
        
        // 銘柄タイプ
        l_infoUnit.productType = "5";
        
        // 発注経路区分
        l_infoUnit.submitOrderRouteDiv = "9";
        
        // 有効フラグ
        l_infoUnit.validFlag = "7";
        
        // 証券会社コード
        l_request.institutionCode = "bbb";
        
        l_request.password = "8989";
        l_request.infoUnit = l_infoUnit;
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(0L));
        
        LoginInfo loginInfo = new LoginInfoImpl();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            loginInfo);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
  
        MainAccountParams l_mainAccountParams = new MainAccountParams();
        
        l_mainAccountParams.setInstitutionId(12);
        
        l_mainAccountParams.setBranchId(123);
        
        l_mainAccountParams.setAccountCode("213");
        MainAccountImpl l_mainAccountImpl = new MainAccountImpl(l_mainAccountParams);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {long.class},
            l_mainAccountImpl);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "lockAccount",
            new Class[] {String.class,String.class,String.class},
            null);
     
        WEB3AdminSwitchOrderRouteServiceImpl  l_adminSwitchOrderRouteServiceImpl =new WEB3AdminSwitchOrderRouteServiceImpl();
                
        try
        {
            TestDBUtility.insertWithDel(this.getAdministratorRow());
            TestDBUtility.insertWithDel(this.getAdminPermissionRow());
            TestDBUtility.insertWithDel(this.getAdministratorTypeRow());

            TestDBUtility.insertWithDel(this.getLoginTypeRow());
            TestDBUtility.insertWithDel(this.getLoginTypeAttributeRow());
            
            InstitutionParams l_lnstitutionRow = getInstitutionRow();
            l_lnstitutionRow.setInstitutionId(11111L);
            TestDBUtility.insertWithDel(l_lnstitutionRow);
            
            MarketParams l_marketParams = this.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.insertWithDelAndCommit(this.getEqtypeOrderUnitRow());
            TestDBUtility.insertWithDel(this.getBranchRow());
            TestDBUtility.insertWithDel(this.getTradedProductRow());
            
            l_adminSwitchOrderRouteServiceImpl.submitOrderRouteChange(l_request);
            assertTrue(true);
        } 
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
   public void test_isUpdateEqtypeOrderUnit_0001() 
   {   
       final String STR_METHOD_NAME = " test_isUpdateEqtypeOrderUnit_0001()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
     
       try
       { 
           l_switchInfoObj.marketCode = "11";
           
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();
         
           EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("isUpdateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
         
           method.setAccessible(true);
           
           boolean result = ((Boolean)method.invoke(l_switchOrderRouteServiceImpl,
               new Object[]{l_orderUnitParams,l_switchInfoObj})).booleanValue();
           assertTrue(result);
       } 
       catch (Exception l_ex)
       { 
           log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
       } 
       
   } 
   
   public void test_isUpdateEqtypeOrderUnit_0002() 
   {
       final String STR_METHOD_NAME = " test_isUpdateEqtypeOrderUnit_0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try
       {
           l_switchInfoObj.marketCode = "10";

           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();

           EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_orderUnitParams.setProductId(123L);
           l_orderUnitParams.setMarketId(11L);
           
           TradedProductParams l_productParams = TestDBUtility.getTradedProductRow();
           TestDBUtility.insertWithDel(l_productParams);
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("isUpdateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});

           method.setAccessible(true);
           
           method.invoke(l_switchOrderRouteServiceImpl,new Object[]{l_orderUnitParams,l_switchInfoObj});
           
           fail();
       }
       catch (InvocationTargetException l_ex)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo());
           assertEquals("テーブルに該当するデータがありません。", ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo().getErrorMessage());
       } 
       catch (Exception e)
       {
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       
   }
   public void test_isUpdateEqtypeOrderUnit_0003() 
   {
       final String STR_METHOD_NAME = " test_isUpdateEqtypeOrderUnit_0003()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
     
       try
       {
           l_switchInfoObj.marketCode = "10";
           
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();

           EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_orderUnitParams.setProductId(3304148080000L);
           
           EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
           
           ProductParams l_productParams = TestDBUtility.getProductRow();
           
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           
           TestDBUtility.insertWithDel(l_marketParams);
           TestDBUtility.insertWithDel(l_eqtypeProductParams);
           TestDBUtility.insertWithDel(l_productParams);
           
           TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
           l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
           TestDBUtility.insertWithDel(l_tradedProductParams);
           
           EqtypeTradedProductParams l_tradedProductRow = TestDBUtility.getEqtypeTradedProductRow();
           l_tradedProductRow.setOpenOtcDiv("3");
           
           TestDBUtility.insertWithDel(l_tradedProductRow);
           
           ///フロント発注システム区分 != "マーケットメイク"なら注文単位テーブルを更新しない。
           l_switchInfoObj.frontOrderSystemCode = "4";
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("isUpdateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});

           method.setAccessible(true);
           
           boolean result = ((Boolean)method.invoke(l_switchOrderRouteServiceImpl,
               new Object[]{l_orderUnitParams,l_switchInfoObj})).booleanValue();
           assertEquals(false,result);
       }
       catch (Exception l_ex)
       {
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
       
   }
   
   public void test_isUpdateEqtypeOrderUnit_0004() 
   {
       final String STR_METHOD_NAME = " test_isUpdateEqtypeOrderUnit_0004()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try
       {
           l_switchInfoObj.marketCode = "10";
           
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();

           EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_orderUnitParams.setProductId(3304148080000L);
           
           EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
           
           ProductParams l_productParams = TestDBUtility.getProductRow();
           
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           
           TestDBUtility.insertWithDel(l_marketParams);
           TestDBUtility.insertWithDel(l_eqtypeProductParams);
           TestDBUtility.insertWithDel(l_productParams);
           
           TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
           l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
           TestDBUtility.insertWithDel(l_tradedProductParams);
           
           EqtypeTradedProductParams l_tradedProductRow = TestDBUtility.getEqtypeTradedProductRow();
           l_tradedProductRow.setOpenOtcDiv("3");
           
           TestDBUtility.insertWithDel(l_tradedProductRow);
           
           ///フロント発注システム区分 = "マーケットメイク"なら注文単位テーブルを更新しない。
           l_switchInfoObj.frontOrderSystemCode = "3";
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("isUpdateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});

           method.setAccessible(true);
           
           boolean result = ((Boolean)method.invoke(l_switchOrderRouteServiceImpl,
               new Object[]{l_orderUnitParams,l_switchInfoObj})).booleanValue();
           assertEquals(true,result);
       }
       catch (Exception l_ex)
       {
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
    }    
   
   public void test_isUpdateEqtypeOrderUnit_0005() 
   {
       final String STR_METHOD_NAME = " test_isUpdateEqtypeOrderUnit_0005()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try
       {
           l_switchInfoObj.marketCode = "10";

           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();
           
           EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_orderUnitParams.setProductId(3304148080000L);
           
           EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
           
           ProductParams l_productParams = TestDBUtility.getProductRow();
           
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           
           TestDBUtility.insertWithDel(l_marketParams);
           TestDBUtility.insertWithDel(l_eqtypeProductParams);
           TestDBUtility.insertWithDel(l_productParams);
           
           TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
           l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
           TestDBUtility.insertWithDel(l_tradedProductParams);
           
           //取引銘柄.店頭公開区分 != "マーケットメイク"の場合
           EqtypeTradedProductParams l_eqtypeTradedProductRow = TestDBUtility.getEqtypeTradedProductRow(); 
           l_eqtypeTradedProductRow.setOpenOtcDiv("4");
           TestDBUtility.insertWithDel(l_eqtypeTradedProductRow);
           
           ///フロント発注システム区分 == "マーケットメイク"なら注文単位テーブルを更新しない。
           l_switchInfoObj.frontOrderSystemCode = "3";
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("isUpdateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});

           method.setAccessible(true);
           
           boolean result = ((Boolean)method.invoke(l_switchOrderRouteServiceImpl,
               new Object[]{l_orderUnitParams,l_switchInfoObj})).booleanValue();
           assertEquals(false,result);
       }
       catch (Exception l_ex)
       {
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
   }
   
   public void test_isUpdateEqtypeOrderUnit_0006() 
   {
       final String STR_METHOD_NAME = " test_isUpdateEqtypeOrderUnit_0006()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try
       {
           l_switchInfoObj.marketCode = "10";
           
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();

           EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_orderUnitParams.setProductId(3304148080000L);
           
           EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
           
           ProductParams l_productParams = TestDBUtility.getProductRow();
           
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           
           TestDBUtility.insertWithDel(l_marketParams);
           TestDBUtility.insertWithDel(l_eqtypeProductParams);
           TestDBUtility.insertWithDel(l_productParams);
           
           TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
           l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
           TestDBUtility.insertWithDel(l_tradedProductParams);
           
           //取引銘柄.店頭公開区分 != "マーケットメイク"の場合
           EqtypeTradedProductParams l_eqtypeTradedProductRow = TestDBUtility.getEqtypeTradedProductRow(); 
           l_eqtypeTradedProductRow.setOpenOtcDiv("4");
           TestDBUtility.insertWithDel(l_eqtypeTradedProductRow);
           
           ///フロント発注システム区分 != "マーケットメイク"なら注文単位テーブルを更新しない。
           l_switchInfoObj.frontOrderSystemCode = "4";
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("isUpdateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});

           method.setAccessible(true);
           
           boolean result = ((Boolean)method.invoke(l_switchOrderRouteServiceImpl,
               new Object[]{l_orderUnitParams,l_switchInfoObj})).booleanValue();
           assertEquals(true,result);
       }
       catch (Exception l_ex)
       {
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
   }
    
   public void test_updateEqtypeOrderUnit_0001() 
   {
       final String STR_METHOD_NAME = " test_updateEqtypeOrderUnit_0001()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try 
       {
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();
           
           l_switchInfoObj.submitOrderRouteDiv ="11";
           
           EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_eqtypeOrderUnitParams.setOrderUnitId(12345L);
           TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
          
           method.setAccessible(true);
           method.invoke(l_switchOrderRouteServiceImpl,new Object[]{l_eqtypeOrderUnitParams,l_switchInfoObj});
           fail();
       }
       catch (InvocationTargetException l_ex)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo());
           assertEquals("DBへのアクセスに失敗しました。", ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo().getErrorMessage());
       }
     catch (Exception e)
     {
         log.exiting(TEST_END + STR_METHOD_NAME);
         fail();
     }
   }
   
   public void test_updateEqtypeOrderUnit_0002() 
   {
       final String STR_METHOD_NAME = " test_updateEqtypeOrderUnit_0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try 
       {
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();
           
           l_switchInfoObj.submitOrderRouteDiv ="6";
           
           EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_eqtypeOrderUnitParams.setOrderUnitId(12345L);
           TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
          
           method.setAccessible(true);
           method.invoke(l_switchOrderRouteServiceImpl,new Object[]{l_eqtypeOrderUnitParams,l_switchInfoObj});
         
           List EqOrderUnitList = new ArrayList();
           // 抽出条件文字列の生成
           StringBuffer l_sbWhere = new StringBuffer();
           l_sbWhere.append("  order_unit_id = ? ");

           
           // 抽出条件コンテナの生成
           Object[] l_objWhere =
           {
               "12345"
           };
           
           // 口座ID昇順指定
           String l_strSort = "account_id asc";

           QueryProcessor qp = Processors.getDefaultProcessor();

           // 株式注文単位テーブルを検索する。 
           EqOrderUnitList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                   l_sbWhere.toString(),
                                   l_strSort,
                                   null,
                                   l_objWhere);
           EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)EqOrderUnitList.get(0);
           
           assertEquals("6", l_eqtypeOrderUnitRow.getSubmitOrderRouteDiv());    
           assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"), WEB3DateUtility.formatDate(l_eqtypeOrderUnitRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
       }
      catch (Exception e)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          fail();
      }
   }
   
   public void test_updateEqtypeOrderUnit_0003() 
   {
       final String STR_METHOD_NAME = " test_updateEqtypeOrderUnit_0003()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();  
       
       try 
       {
           WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
               WEB3AdminSwitchOrderRouteServiceImpl();
           
           l_switchInfoObj.submitOrderRouteDiv ="6";
           
           EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
           l_eqtypeOrderUnitParams.setOrderUnitId(12345L);
           l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
           TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
           
           EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
           l_eqtypeOrderUnitParams1.setOrderUnitId(11111L);
           l_eqtypeOrderUnitParams1.setAccountId(11111L);
           l_eqtypeOrderUnitParams1.setBranchId(11111L);
           l_eqtypeOrderUnitParams1.setSubmitOrderRouteDiv("1");
           
           l_eqtypeOrderUnitParams1.setLastUpdatedTimestamp(new Timestamp(0L));
           TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams1);
           
           Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrderUnit",
               new Class[] {EqtypeOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
          
           method.setAccessible(true);
           method.invoke(l_switchOrderRouteServiceImpl,new Object[]{l_eqtypeOrderUnitParams,l_switchInfoObj});
         
           List EqOrderUnitList = new ArrayList();
           // 抽出条件文字列の生成
           StringBuffer l_sbWhere = new StringBuffer();
           l_sbWhere.append("  order_unit_id = ? ");

           // 抽出条件コンテナの生成
           Object[] l_objWhere =
           {
               "12345"
           };
           
           // 口座ID昇順指定
           String l_strSort = "account_id asc";

           QueryProcessor qp = Processors.getDefaultProcessor();

           // 株式注文単位テーブルを検索する。 
           EqOrderUnitList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                   l_sbWhere.toString(),
                                   l_strSort,
                                   null,
                                   l_objWhere);
           EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)EqOrderUnitList.get(0);
           
           List EqOrderUnitList1 = new ArrayList();
           // 抽出条件文字列の生成
           StringBuffer l_sbWhere1 = new StringBuffer();
           l_sbWhere1.append("  order_unit_id = ? ");

           
           // 抽出条件コンテナの生成
           Object[] l_objWhere1 =
           {
               "11111"
           };
           
           // 口座ID昇順指定
           String l_strSort1 = "account_id asc";

           QueryProcessor qp1 = Processors.getDefaultProcessor();

           // 株式注文単位テーブルを検索する。 
           EqOrderUnitList1 = qp1.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                   l_sbWhere1.toString(),
                                   l_strSort1,
                                   null,
                                   l_objWhere1);
           EqtypeOrderUnitRow l_eqtypeOrderUnitRow1 = (EqtypeOrderUnitRow)EqOrderUnitList1.get(0);
           
           assertEquals("6", l_eqtypeOrderUnitRow.getSubmitOrderRouteDiv());    
           assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"), WEB3DateUtility.formatDate(l_eqtypeOrderUnitRow.getLastUpdatedTimestamp(), "yyyyMMdd"));
           assertEquals("1", l_eqtypeOrderUnitRow1.getSubmitOrderRouteDiv());    
           assertEquals(new Timestamp(0L), l_eqtypeOrderUnitRow1.getLastUpdatedTimestamp());
       }
       catch (Exception e)
       {
           log.exiting(TEST_END + STR_METHOD_NAME);
           fail();
       }
   } 
   
   public  static OrderSwitchingParams  getOrderSwitching()
   {
       OrderSwitchingParams l_orderSwitchingParams =new OrderSwitchingParams();
       
       l_orderSwitchingParams.setInstitutionCode("123");
       l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
       l_orderSwitchingParams.setMarketCode("12");
       l_orderSwitchingParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
       l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
       l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
       l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.OFF);
       l_orderSwitchingParams.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.ENABLE);
       l_orderSwitchingParams.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.TRIGGER);
       l_orderSwitchingParams.setCreatedTimestamp(Calendar.getInstance().getTime());
       l_orderSwitchingParams.setLastUpdatedTimestamp(new Timestamp(0L));///
       l_orderSwitchingParams.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
       return l_orderSwitchingParams;
   }

    /**
     * 管理者テーブルRowを作成.<BR>
     */
    public AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();
        
        //管理者ID
        l_administratorParams.setAdministratorId(123456);
        //管理者コード
        l_administratorParams.setAdministratorCode("aaa");
        //証券会社ID
        l_administratorParams.setInstitutionId(987654);
        //証券会社コード
        l_administratorParams.setInstitutionCode("bbb");
        //管理者ログインＩＤ
        l_administratorParams.setLoginId(0L);
        //権限レベル
        l_administratorParams.setPermissionLevel("a");
                
        return l_administratorParams;
    }
    
    /**
     * 管理者権限Rowを作成.<BR>
     */
    public AdminPermissionParams getAdminPermissionRow()
    {
        AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
        
        //証券会社コード
        l_adminPermissionParams.setInstitutionCode("bbb");
        //権限レベル
        l_adminPermissionParams.setPermissionLevel("a");
        //機@能カテゴリコード
        l_adminPermissionParams.setTransactionCategory("Z0201");
        //更新許可フラグ
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
        //作成日時
        l_adminPermissionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時
        l_adminPermissionParams.setUpdateTimestamp(Calendar.getInstance().getTime());
                
        return l_adminPermissionParams;
    }
    
    /**
     * 管理者タイプRowを作成.<BR>
     */
    public AdministratorTypeParams getAdministratorTypeRow()
    {
        AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
        
        //証券会社コード
        l_administratorTypeParams.setInstitutionCode("bbb");
        //権限レベル
        l_administratorTypeParams.setPermissionLevel("a");
        //ＤＩＲ管理者フラグ
        l_administratorTypeParams.setDirAdminFlag(1);
        //全部店許可フラグ
        l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
        //作成日時
        l_administratorTypeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時
        l_administratorTypeParams.setUpdateTimestamp(Calendar.getInstance().getTime());
                
        return l_administratorTypeParams;
    }
    
    /**
     * ログインタイプ属性Rowを作成.<BR>
     */
    public LoginTypeAttributeParams getLoginTypeAttributeRow()
    {
        LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
        
        //ログインタイプID
        l_loginTypeAttributeParams.setTypeId(0);
        //属性名
        l_loginTypeAttributeParams.setAttributeName("TRADING_PWD_ENV");
        //属性値
        l_loginTypeAttributeParams.setAttributeValue("0");

                
        return l_loginTypeAttributeParams;
    }

    /**
     * ログインタイプRowを作成.<BR>
     */
    public LoginTypeParams getLoginTypeRow()
    {
        LoginTypeParams l_loginTypeParams = new LoginTypeParams();
        
        //ログインタイプID
        l_loginTypeParams.setTypeId(0);
        //ログインタイプ名
        l_loginTypeParams.setTypeName("abc");
        
        return l_loginTypeParams;
    }
        
    /**
     * 証券会社テーブルRowを作成.<BR>
     */
    public InstitutionParams getInstitutionRow()
    {
        InstitutionParams l_institutionParams = new InstitutionParams();
        
        //証券会社ＩＤ
        l_institutionParams.setInstitutionId(12);
        //証券会社コード
        l_institutionParams.setInstitutionCode("bbb");
        //作成日時
        l_institutionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時
        l_institutionParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        return l_institutionParams;
    }
    
    /**
     * 市場テーブルRowを作成.<BR>
     */
    public MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();
        
        //市場ＩＤ
        l_marketParams.setMarketId(89765);
        //証券会社コード
        l_marketParams.setInstitutionCode("bbb");
        //市場コード
        l_marketParams.setMarketCode("10");
        //市場名
        l_marketParams.setMarketName("東京");
        //注文受付開始時刻
        l_marketParams.setOpenTime("2056");
        //注文受付終了時刻
        l_marketParams.setCloseTime("2058");
        //作成日付
        l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_marketParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        return l_marketParams;
        
    }
    
    /**
     * 株式注文単位テーブルRowを作成.<BR>
     */
    public EqtypeOrderUnitParams getEqtypeOrderUnitRow()
    {
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
        
        //注文単位ＩＤ
        l_eqtypeOrderUnitParams.setOrderUnitId(555555);
        //口座ＩＤ
        l_eqtypeOrderUnitParams.setAccountId(444444);
        //補助口座ＩＤ
        l_eqtypeOrderUnitParams.setSubAccountId(6666666);
        //部店ＩＤ
        l_eqtypeOrderUnitParams.setBranchId(1236);
        //注文ＩＤ
        l_eqtypeOrderUnitParams.setOrderId(654789);
        //注文種別
        l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);  
        //注文カテゴリ
        l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
        //注文履歴最終通番
        l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0);
        //約定最終通番
        l_eqtypeOrderUnitParams.setLastExecutionSerialNo(0);
        //銘柄タイプ
        l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        //注文数量
        l_eqtypeOrderUnitParams.setQuantity(456.32);
        //受渡日
        l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
        //注文状態
        l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        //注文有効状態    
        l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //失効区分
        l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
        //税区分
        l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        //税区分（現引現渡）
        l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.SPECIAL);
        //発注日
        l_eqtypeOrderUnitParams.setBizDate("20070121");
        //銘柄ＩＤ
        l_eqtypeOrderUnitParams.setProductId(78954654);
        //注文数量タイプ
        l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        //作成日付
        l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        
        //発注経路区分
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("0");
        //更新日付
        l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        //市場ＩＤ
        l_eqtypeOrderUnitParams.setMarketId(89765);
        //取引コード（SONAR）
        l_eqtypeOrderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        
        
        return l_eqtypeOrderUnitParams;
    }

    /**
     * 部店テーブルRowを作成.<BR>
     */
    public BranchParams getBranchRow()
    {
        BranchParams l_branchParams = new BranchParams();
        //部店ＩＤ
        l_branchParams.setBranchId(123);
        //証券会社コード
        l_branchParams.setInstitutionCode("21");
        //証券会社ID
        l_branchParams.setInstitutionId(23);
        //部店コード
        l_branchParams.setBranchCode("456");
        //部店名
        l_branchParams.setBranchName("213");
        //部店タイプ
        l_branchParams.setBranchType(BranchTypeEnum.OTHER_BRANCH);
        //作成日付
        l_branchParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_branchParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        return l_branchParams;
    }
    
    /**
     * 取引銘柄マスターテーブルRowを作成.<BR>
     */
    public TradedProductParams getTradedProductRow()
    {
        TradedProductParams l_tradedProductParams = new TradedProductParams();
        //取引銘柄ID
        l_tradedProductParams.setTradedProductId(222);
        //証券会社コード
        l_tradedProductParams.setInstitutionCode("bbb");
        //銘柄ID
        l_tradedProductParams.setProductId(789546541);//DOTO
        //市場ID
        l_tradedProductParams.setMarketId(897651);//DOTO
        //取引停止フラグ
        l_tradedProductParams.setSuspensionFlag(BooleanEnum.TRUE);
        //基準日
        l_tradedProductParams.setBaseDate(Calendar.getInstance().getTime());
        //銘柄タイプ
        l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
        //担保可能フラグ
        l_tradedProductParams.setCollateralFlag(BooleanEnum.UNDEFINED);
        //作成日付
        l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_tradedProductParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        return l_tradedProductParams;
    } 
    
    /**
     * 株式取引銘柄マスターRowを作成.<BR>
     */
    public EqtypeTradedProductParams getEqtypeTradedProductRow()
    {
        EqtypeTradedProductParams l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
        
        //取引銘柄ＩＤ
        l_eqtypeTradedProductParams.setTradedProductId(222);
        //証券会社コード
        l_eqtypeTradedProductParams.setInstitutionCode("bbb");
        //銘柄ＩＤ
        l_eqtypeTradedProductParams.setProductId(78954654);
        //市場ＩＤ
        l_eqtypeTradedProductParams.setMarketId(89765);
        //上場フラグ
        l_eqtypeTradedProductParams.setListFlag(BooleanEnum.TRUE);
        //上場区分
        l_eqtypeTradedProductParams.setListType("1");
        //新市場区分
        l_eqtypeTradedProductParams.setNewListType("2");
        //上場（登録）日
        l_eqtypeTradedProductParams.setListedDate(Calendar.getInstance().getTime());
        //買信用可否フラグ
        l_eqtypeTradedProductParams.setMarginableFlag(BooleanEnum.FALSE);
        //売信用可否フラグ
        l_eqtypeTradedProductParams.setShortableFlag(BooleanEnum.TRUE);
        //ミニ株取扱 
        l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
        //売買単位
        l_eqtypeTradedProductParams.setLotSize(12);
        //基準値（終値）
        l_eqtypeTradedProductParams.setLastClosingPrice(45646);
        //ミニ株フラグ
        l_eqtypeTradedProductParams.setMiniStockFlag(BooleanEnum.TRUE);
        //作成日付
        l_eqtypeTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_eqtypeTradedProductParams.setLastUpdatedTimestamp(new Timestamp(0L));
        //基準値
        l_eqtypeTradedProductParams.setBasePrice(123);
        
        //店頭公開区分 
        l_eqtypeTradedProductParams.setOpenOtcDiv("3");
        
        return l_eqtypeTradedProductParams;
    }
    
    /**
     * 投信取引銘柄マスタテーブルRowを作成.<BR>
     */
    public MutualFundTradedProductParams getMutualFundTradedProductRow()
    {
        MutualFundTradedProductParams l_mutualFundTradedProductParams = new MutualFundTradedProductParams();
        
        //取引銘柄ID
        l_mutualFundTradedProductParams.setTradedProductId(222);
        //証券会社コード
        l_mutualFundTradedProductParams.setInstitutionCode("bbb");
        //銘柄ID
        l_mutualFundTradedProductParams.setProductId(78954654);
        //市場ID
        l_mutualFundTradedProductParams.setMarketId(89765);
        //最終更新者コード
        l_mutualFundTradedProductParams.setLastUpdater("20070122");
        //更新日付（オンライン）
        l_mutualFundTradedProductParams.setOnlineUpdatedTimestamp(Calendar.getInstance().getTime()); 
        
        //作成日付
        l_mutualFundTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_mutualFundTradedProductParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        return l_mutualFundTradedProductParams;
    }
    
    /**
     * 株式銘柄テーブルRowを作成.<BR>
     */
    public EqtypeProductParams getEquityProductRow()
    {
        EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
        
        //銘柄ＩＤ
        l_eqtypeProductParams.setProductId(78954654);
        //証券会社コード
        l_eqtypeProductParams.setInstitutionCode("bbb");
        //銘柄コード
        l_eqtypeProductParams.setProductCode("1023");
        //銘柄タイプ
        l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
        //決算日
        l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
        //作成日付
        l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_eqtypeProductParams.setLastUpdatedTimestamp(new Timestamp(0L));
        
        return l_eqtypeProductParams;
    }
    
    /**
     * 銘柄Rowを作成.<BR>
     */
    public ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(78954654);
        l_productParams.setInstitutionCode("bbb");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(new Timestamp(0L));

        return l_productParams;
    }
    
    /**
     * 株式注文取引キューテーブルRowを作成.<BR>
     */
    public HostEqtypeOrderAllParams getHostEqtypeOrderAllRow()
    {
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
        

        //社内処理項目
        l_hostEqtypeOrderAllParams.setCorpCode("21313");
        //全訂正処理区分
        l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
        //取消区分
        l_hostEqtypeOrderAllParams.setCancelDiv("1");
        
        return l_hostEqtypeOrderAllParams;
    }
}
@
