head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3RuitoBuyOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 累積投資買付注文サービス実装クラスTEST(WEB3RuitoBuyOrderServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/10/25 趙林鵬(中訊) 新規作成 モデルNo.094
 */
package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;

public class WEB3RuitoBuyOrderServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderServiceImplTest.class);
    
    public WEB3RuitoBuyOrderServiceImplTest(String arg0)
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
    
    /**
    累積投資買付注文審査を行う。
    目論見書閲覧チェックを実施的場合
    累積投資買付注文確認レスポンス的属性目論見書閲覧チェック結果得賦zhi
    リクエスト.電子鳩チェックフラグ = True 
     */
    public void testValidateBuyOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3RuitoBuyConfirmRequest l_request = new WEB3RuitoBuyConfirmRequest();
            l_request.specifyDiv = "3";
            l_request.ruitoOrderQuantity = "123";
            l_request.ruitoProductCode = "N8080";
            l_request.batoCheckFlag = true;
            
            WEB3RuitoBuyOrderServiceImpl l_impl = new WEB3RuitoBuyOrderServiceImpl();
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.xbruito.WEB3RuitoOrderManager",
                    "validateNewOrder",
                    new Class[] { SubAccount.class, WEB3RuitoProduct.class, double.class, 
                    boolean.class, String.class, String.class },
                    l_newOrderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class,
                    OrderTypeEnum.class, boolean.class},
                l_tpResult);

            WEB3GentradeProspectusResult l_result = 
                new WEB3GentradeProspectusResult();
            l_result.checkResult = "2";
            l_result.url = "111"; 
            l_result.hashValue = "222";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_result);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(RuitoProductRow.TYPE);
            RuitoProductParams l_ruitoProductParams = TestDBUtility.getRuitoProductRow();
            TestDBUtility.insertWithDel(l_ruitoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.RUITO);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3RuitoBuyConfirmResponse l_response = l_impl.validateBuyOrder(l_request);

            assertEquals(l_response.prospectusResult.checkResult, "2");
            assertEquals(l_response.prospectusResult.url, "111");
            assertEquals(l_response.prospectusResult.hashValue, "222");
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
    累積投資買付注文審査を行う。
    目論見書閲覧チェックを実施的場合
     validate目論見書閲覧抛出?常
     リクエスト.電子鳩チェックフラグ = True 
     リクエスト.種別コード  = null;
     */
    public void testValidateBuyOrderCase0002()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3RuitoBuyConfirmRequest l_request = new WEB3RuitoBuyConfirmRequest();
            l_request.specifyDiv = "3";
            l_request.ruitoOrderQuantity = "123";
            l_request.ruitoProductCode = "N8080";
            l_request.batoCheckFlag = true;
            l_request.typeCode = null;
            
            WEB3RuitoBuyOrderServiceImpl l_impl = new WEB3RuitoBuyOrderServiceImpl();
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.xbruito.WEB3RuitoOrderManager",
                "validateNewOrder",
                new Class[] { SubAccount.class, WEB3RuitoProduct.class, double.class, 
                boolean.class, String.class, String.class },
                l_newOrderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class,
                    OrderTypeEnum.class, boolean.class},
                l_tpResult);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(RuitoProductRow.TYPE);
            RuitoProductParams l_ruitoProductParams = TestDBUtility.getRuitoProductRow();
            TestDBUtility.insertWithDel(l_ruitoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.RUITO);
            TestDBUtility.insertWithDel(l_productParams);
            l_impl.validateBuyOrder(l_request);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
    累積投資買付注文審査を行う。
    目論見書閲覧チェックを実施的場合
    累積投資買付注文確認レスポンス的属性目論見書閲覧チェック結果的驗證。
    リクエスト.電子鳩チェックフラグ = false
     */
    public void testValidateBuyOrderCase0003()
    {
        final String STR_METHOD_NAME = "testValidateBuyOrderCase0003()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3RuitoBuyConfirmRequest l_request = new WEB3RuitoBuyConfirmRequest();
            l_request.specifyDiv = "3";
            l_request.ruitoOrderQuantity = "123";
            l_request.ruitoProductCode = "N8080";
            l_request.batoCheckFlag = false;
            
            WEB3RuitoBuyOrderServiceImpl l_impl = new WEB3RuitoBuyOrderServiceImpl();
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.xbruito.WEB3RuitoOrderManager",
                "validateNewOrder",
                new Class[] { SubAccount.class, WEB3RuitoProduct.class, double.class, 
                boolean.class, String.class, String.class },
                l_newOrderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class,
                    OrderTypeEnum.class, boolean.class},
                l_tpResult);

            WEB3GentradeProspectusResult l_result = 
                new WEB3GentradeProspectusResult();
            l_result.checkResult = "2";
            l_result.url = "111"; 
            l_result.hashValue = "222";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_result);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(RuitoProductRow.TYPE);
            RuitoProductParams l_ruitoProductParams = TestDBUtility.getRuitoProductRow();
            TestDBUtility.insertWithDel(l_ruitoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.RUITO);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3RuitoBuyConfirmResponse l_response = l_impl.validateBuyOrder(l_request);

            assertNull(l_response.prospectusResult);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
