head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAcceptServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付取消認証サービスImplテストクラス(WEB3AdminFeqOrderAcceptServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/25  齊珂 (中訊) 新規作成
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式注文受付取消認証サービスImplテストクラス)<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminFeqOrderAcceptServiceImplTest.class);
    
    public WEB3AdminFeqOrderAcceptServiceImplTest(String arg0)
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

    public void testGetSearchScreen1()
    {
        final String STR_METHOD_NAME = " testGetSearchScreen1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse l_response = null;
        WEB3AdminFeqOrderAcceptServiceImpl l_impl = new WEB3AdminFeqOrderAcceptServiceImpl();
        WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest l_request = 
            new WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            //AdminPermission
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_response = l_impl.getSearchScreen(l_request);
            assertEquals(l_response.marketList[0], "SP");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testGetSearchScreen2()
    {
        final String STR_METHOD_NAME = " testGetSearchScreen2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse l_response = null;
        WEB3AdminFeqOrderAcceptServiceImpl l_impl = new WEB3AdminFeqOrderAcceptServiceImpl();
        WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest l_request = 
            new WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            //AdminPermission
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            MarketPreferencesParams l_MPParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_MPParams);
            
            l_response = l_impl.getSearchScreen(l_request);
            assertEquals(l_response.marketList[0], "SP");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testGetSearchScreen3()
    {
        final String STR_METHOD_NAME = " testGetSearchScreen3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse l_response = null;
        WEB3AdminFeqOrderAcceptServiceImpl l_impl = new WEB3AdminFeqOrderAcceptServiceImpl();
        WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest l_request = 
            new WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            
            //admin
            AdministratorParams l_adminParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            //AdminPermission
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //FeqBranchMarketDealtCond 1
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            //market 1
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //FeqBranchMarketDealtCond 2
            FeqBranchMarketDealtCondParams l_params2 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params2.setBranchCode("382");
            l_params2.setMarketCode("PS");           
            TestDBUtility.insertWithDel(l_params2);
            
            //market 2
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3304L);
            l_marketParams2.setMarketCode("PS");
            TestDBUtility.insertWithDel(l_marketParams2);
            
            //FeqBranchMarketDealtCond 3
            FeqBranchMarketDealtCondParams l_params3 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params3.setBranchCode("383");
            l_params3.setMarketCode("PP");           
            TestDBUtility.insertWithDel(l_params3);
            
            //MarketPreferences
            MarketPreferencesParams l_MPParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_MPParams);
            
            l_response = l_impl.getSearchScreen(l_request);
            assertEquals(l_response.marketList[0], "PS");
            assertEquals(l_response.marketList[1], "SP");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void testGetInputScreen_c0001()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_c0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOrderAcceptServiceImpl l_impl = new WEB3AdminFeqOrderAcceptServiceImpl();
        WEB3AdminFeqOrderAcceptCancelInputRequest l_request = 
            new WEB3AdminFeqOrderAcceptCancelInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            //admin
            AdministratorParams l_adminParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            //AdminPermission
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //FeqBranchMarketDealtCond 1
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            //market 1
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //FeqBranchMarketDealtCond 2
            FeqBranchMarketDealtCondParams l_params2 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params2.setBranchCode("382");
            l_params2.setMarketCode("PS");           
            TestDBUtility.insertWithDel(l_params2);
            
            //market 2
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3304L);
            l_marketParams2.setMarketCode("PS");
            TestDBUtility.insertWithDel(l_marketParams2);
            
            //FeqBranchMarketDealtCond 3
            FeqBranchMarketDealtCondParams l_params3 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params3.setBranchCode("383");
            l_params3.setMarketCode("PP");           
            TestDBUtility.insertWithDel(l_params3);
            
            //MarketPreferences
            MarketPreferencesParams l_MPParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_MPParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_request.managementCode = "12345";
            l_request.branchCode ="381";
            l_request.accountCode="123456";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3ForeignSortKey l_key = new WEB3ForeignSortKey();
            l_key.keyItem = WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE;
            l_key.ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys = new WEB3ForeignSortKey[]{l_key};

            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_request.branchCode);
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            l_ex.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testSubmitAccept_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitAccept_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_adminParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator,
                "C0402",
                true,
                true);

            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);

            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(123456789);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            
            WEB3AdminFeqOrderAcceptCancelCompleteRequest l_request =
                new WEB3AdminFeqOrderAcceptCancelCompleteRequest();
            
            WEB3FeqOrderAcceptCancelUnit[] l_orderAcceptCancelList =
                new WEB3FeqOrderAcceptCancelUnit[1];
            l_orderAcceptCancelList[0] = new WEB3FeqOrderAcceptCancelUnit();
            l_orderAcceptCancelList[0].aftChangeAcceptDiv = "03";
            l_orderAcceptCancelList[0].orderId = "123456789";
            l_request.orderAcceptCancelList = l_orderAcceptCancelList;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl",
                "updateAccept",
                new Class[]{MarketResponseMessage.class},
                null);
            
            WEB3AdminFeqOrderAcceptServiceImpl l_impl = new WEB3AdminFeqOrderAcceptServiceImpl();
            l_impl.submitAccept(l_request);
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
