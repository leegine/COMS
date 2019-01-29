head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExecutionEndServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来終了サービスImplテストクラス(WEB3AdminFeqExecutionEndServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  齊珂 (中訊) 新規作成
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.FeqOrderexecutionEndParams;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteResponse;
import webbroker3.feq.message.WEB3FeqExecutionEndExecuteCondUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式出来終了サービスImplテストクラス)<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */           
public class WEB3AdminFeqExecutionEndServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminFeqExecutionEndServiceImplTest.class);
    
    public WEB3AdminFeqExecutionEndServiceImplTest(String arg0)
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

    public class WEB3AdminFeqExecutionEndCompleteRequestForMock extends WEB3AdminFeqExecutionEndCompleteRequest 
    {
        public void validate() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " validate() ";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
        }
        
    }
    public void testSubmitExecEnd()
    {
        final String STR_METHOD_NAME = " testGetInputScreen1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        Services.overrideService(WEB3AdminFeqExecutionEndService.class,
                new WEB3AdminFeqExecutionEndServiceImplA());
        
        WEB3AdminFeqExecutionEndServiceImpl l_impl = new WEB3AdminFeqExecutionEndServiceImplA();
        WEB3AdminFeqExecutionEndCompleteRequestForMock l_request = 
            new WEB3AdminFeqExecutionEndCompleteRequestForMock();
        
        l_request.executionEndExecuteCondList = new WEB3FeqExecutionEndExecuteCondUnit[1];
        WEB3FeqExecutionEndExecuteCondUnit l_unit = new WEB3FeqExecutionEndExecuteCondUnit();
        l_unit.marketCode = "a";
        l_unit.orderBizDate = WEB3DateUtility.getDate("20070227", "yyyyMMdd");
        l_request.executionEndExecuteCondList[0] = l_unit;
        try
        {
            Services.overrideService(WEB3AdminFeqExecutionEndUnitService.class,
                new WEB3AdminFeqExecutionEndUnitServiceImplA());
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            
            //FeqOrderUnit
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            WEB3FeqOrderUnit l_orderUnit = 
                (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());

            
            WEB3FeqOrderUnit[] l_units = new WEB3FeqOrderUnit[1];
            l_units[0] = l_orderUnit;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getOrderExecEndObjectOrderUnit",
                new Class[] {Long.class, String.class, String.class, Date.class},
                l_units);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount", 
                new Class[] { String.class,String.class,String.class }, 
                null);

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            
            //admin
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
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
            
            //mainAccount
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword("a",true);
            
            TestDBUtility.commit();
            WEB3AdminFeqExecutionEndCompleteResponse l_response = l_impl.submitExecEnd(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.overrideService(WEB3AdminFeqExecutionEndUnitService.class,
                 new WEB3AdminFeqExecutionEndUnitServiceImpl());
            Services.overrideService(WEB3AdminFeqExecutionEndService.class,
                    new WEB3AdminFeqExecutionEndServiceImpl());
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    class WEB3AdminFeqExecutionEndUnitServiceImplA extends WEB3AdminFeqExecutionEndUnitServiceImpl
    {
        public void updateExecEndOrder(WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException 
        {
            return;
        }
            
    
    }
    
    class WEB3AdminFeqExecutionEndServiceImplA extends WEB3AdminFeqExecutionEndServiceImpl
    {
        protected FeqOrderexecutionEndParams[] getFeqExecEnd(
            String l_strInstitutionCode, 
            String[] l_strMarketCodes) 
                throws WEB3BaseException
        {
            FeqOrderexecutionEndParams[] l_feqOrderexecutionEndParams = new FeqOrderexecutionEndParams[1];
            FeqOrderexecutionEndParams  l_feqOrderexecutionEndParams0 = new FeqOrderexecutionEndParams();
            l_feqOrderexecutionEndParams0.institution_code = "0D";
            l_feqOrderexecutionEndParams0.market_code = "SP";
            l_feqOrderexecutionEndParams[0] = l_feqOrderexecutionEndParams0;
            return l_feqOrderexecutionEndParams;
        }
        
        protected boolean isExecEndPossible(Date l_datLastExecuteDate, Date l_datBizDate) 
        throws WEB3BaseException
        {
            return true;
        }
        
    }
    
    
}
@
