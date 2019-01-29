head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAcceptResultUploadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqOrderAcceptResultUploadServiceImplTest extends JunitTestBase
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptResultUploadServiceImplTest.class);

    WEB3AdminFeqOrderAcceptResultUploadServiceImpl l_impl = null;
    public WEB3AdminFeqOrderAcceptResultUploadServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_impl = new WEB3AdminFeqOrderAcceptResultUploadServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidateUpload_Case001()
    {
        final String STR_METHOD_NAME = "testValidateUpload_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqOrderAcceptResultUploadConfirmRequest l_request =
                new WEB3AdminFeqOrderAcceptResultUploadConfirmRequest();
            String[] l_uploadFileList = new String[1];
            l_uploadFileList[0] = "NVjidd0,NVjiddk1,NVjiddk2,NVjiddk3,NVjiddk4,NVjiddk5,NVjiddk6,NVjiddk7";
            l_request.uploadFileList = l_uploadFileList;
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator, "C0402", true, true);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstitutionPreferencesRow
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NC");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_clendarContext.setBranchCode("624");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_clendarContext);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = 
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_feqOrderUnitParams.setConfirmedQuantity(0);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            OrderManager l_orderManager = l_tradingModule.getOrderManager();
            FeqOrderUnit l_feqOrderUnit =
                (FeqOrderUnit)l_orderManager.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getValidOrderUnitByOrderEmpCode",
                new Class[] {Date.class, String.class},
                l_feqOrderUnit);
            
            TestDBUtility.deleteAll(AdministratorUploadRow.TYPE);
            WEB3AdminFeqOrderAcceptResultUploadConfirmResponse l_response =
                l_impl.validateUpload(l_request);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03164);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
