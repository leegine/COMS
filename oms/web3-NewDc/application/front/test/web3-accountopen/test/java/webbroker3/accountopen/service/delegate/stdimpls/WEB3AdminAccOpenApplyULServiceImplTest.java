head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyULServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.WEB3AdminAccOpenApplyULCsv;
import webbroker3.accountopen.data.AccOpenItemMasterParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.define.WEB3AccOpenUploadStateDivDef;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenApplyULServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULCsv.class);

    public WEB3AdminAccOpenApplyULServiceImplTest(String arg0)
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

    public void testUndoUploadFile_0001()
    {
        final String STR_METHOD_NAME = "testUndoUploadFile_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyUploadCancelRequest l_request = new WEB3AdminAccOpenApplyUploadCancelRequest();
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            impl.undoUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testUndoUploadFile_0002()
    {
        final String STR_METHOD_NAME = "testUndoUploadFile_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            WEB3AdminAccOpenApplyUploadCancelRequest l_request = new WEB3AdminAccOpenApplyUploadCancelRequest();
            l_request.uploadID = "123";
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            impl.undoUploadFile(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
//
//    public void testGetUploadScreen_0001()
//    {
//        final String STR_METHOD_NAME = "testGetUploadScreen_0001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.deleteAll(l_administratorParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
//            
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("A0101");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
//            WEB3AdminAccOpenApplyUploadInputRequest l_request = new WEB3AdminAccOpenApplyUploadInputRequest();
//            impl.getUploadScreen(l_request);
//
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//
//    public void testGetUploadScreen_0002()
//    {
//        final String STR_METHOD_NAME = "testGetUploadScreen_0002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.deleteAll(l_administratorParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
//
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("A0403");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            WEB3GentradeTradingClendarContext l_clendarContext =
//                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
//                    "web3.tradingcalendarcontext");
//            l_clendarContext.setBranchCode(null);
//            
//            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
//            WEB3AdminAccOpenApplyUploadInputRequest l_request = new WEB3AdminAccOpenApplyUploadInputRequest();
//            impl.getUploadScreen(l_request);
//            
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    public void testGetUploadScreen_0003()
//    {
//        final String STR_METHOD_NAME = "testGetUploadScreen_0003()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(l_administratorParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
//
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("A0403");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
//            l_administratorUploadParams.setUploadFileId("口座開設申込");
//            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
//            l_administratorUploadParams.setUploadKey(1);
//            l_administratorUploadParams.setNote1("中止");
//            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorUploadParams);
//            
//            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
//            WEB3AdminAccOpenApplyUploadInputRequest l_request = new WEB3AdminAccOpenApplyUploadInputRequest();
//            WEB3AdminAccOpenApplyUploadInputResponse l_response = new WEB3AdminAccOpenApplyUploadInputResponse();
//            l_response = impl.getUploadScreen(l_request);
//            
//            assertEquals(null, l_response.complianceInfo);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    public void testGetUploadScreen_0004()
//    {
//        final String STR_METHOD_NAME = "testGetUploadScreen_0004()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(l_administratorParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
//
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("A0403");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
//            l_administratorUploadParams.setUploadFileId("口座開設申込");
//            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
//            l_administratorUploadParams.setUploadKey(0);
//            l_administratorUploadParams.setNote1("ああ");
//            l_administratorUploadParams.setUploadEndTimestamp(null);
//            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorUploadParams);
//            
//            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
//            WEB3AdminAccOpenApplyUploadInputRequest l_request = new WEB3AdminAccOpenApplyUploadInputRequest();
//            WEB3AdminAccOpenApplyUploadInputResponse l_response = new WEB3AdminAccOpenApplyUploadInputResponse();
//            l_response = impl.getUploadScreen(l_request);
//            
//            assertEquals("1", l_response.uploadHistoryList.uploadStateDiv);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//
//    public void testGetUploadScreen_0005()
//    {
//        final String STR_METHOD_NAME = "testGetUploadScreen_0005()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(l_administratorParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
//
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("A0403");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
//            l_administratorUploadParams.setUploadFileId("口座開設申込");
//            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
//            l_administratorUploadParams.setUploadKey(0);
//            l_administratorUploadParams.setNote1("ああ");
//            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
//            TestDBUtility.insertWithDel(l_administratorUploadParams);
//            
//            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
//            WEB3AdminAccOpenApplyUploadInputRequest l_request = new WEB3AdminAccOpenApplyUploadInputRequest();
//            WEB3AdminAccOpenApplyUploadInputResponse l_response = new WEB3AdminAccOpenApplyUploadInputResponse();
//            l_response = impl.getUploadScreen(l_request);
//            
//            assertEquals("2", l_response.uploadHistoryList.uploadStateDiv);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
    public void testValidateUploadFile_0001()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
            WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00976, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateUploadFile_0002()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
            l_request.uploadFile = new String[2];
            WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;

            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateUploadFile_0003()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            l_clendarContext.setBranchCode(null);

            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
            l_request.uploadFile = new String[2];
            WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateUploadFile_0004()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAllAndCommit(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
            l_request.uploadFile = new String[2];
            WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01969, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0005()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
            String[] l_struploadFiles = new String[1];
            l_struploadFiles[0] = "11,12,13,14,15,16";
            l_request.uploadFile = l_struploadFiles;
            WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01993, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testvalidateUploadFile_0006()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "00001,2004071690001,3845,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,82,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "00001,2004071690001,384,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,82,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01312, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testvalidateUploadFile_0007()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "aaa11,2004071690001,384,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "aaa11,2004071690001,384,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01738, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    //validate重複顧客(int)
    public void testvalidateUploadFile_0008()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "00001,2004071690001,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "00001,2004071690001,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(1);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(1);
            l_loginTypeAttributeParams1.setAttributeName("PASSWORD_MAX_LENGTH");
            l_loginTypeAttributeParams1.setAttributeValue("30");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01312, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    //validate口座開設見込客登録(int, String)
    public void testvalidateUploadFile_0009()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0009()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "00001,2004071690001,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "00002,2004071690002,382,600006,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccOpenRequestNumber("2004071690002");
            TestDBUtility.deleteAll(l_expAccountOpenParams.getRowType());
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setAccountTypeId(1);
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(1);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(1);
            l_loginTypeAttributeParams1.setAttributeName("PASSWORD_MAX_LENGTH");
            l_loginTypeAttributeParams1.setAttributeValue("30");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01312, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    //validate口座開設見込客登録(int, String)
    public void testvalidateUploadFile_0010()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0010()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "00001,2004071690001,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "00002,2004071690002,382,600006,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            TestDBUtility.deleteAllAndCommit(AdministratorUploadRow.TYPE);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setAccountTypeId(1);
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(1);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(1);
            l_loginTypeAttributeParams1.setAttributeName("PASSWORD_MAX_LENGTH");
            l_loginTypeAttributeParams1.setAttributeValue("30");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            fail();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ
            l_sbWhere.append(" and UPLOAD_START_TIMESTAMP IS NOT NULL ");

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };
            List l_lisRecords = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);

            assertEquals(1, l_lisRecords.size());
            assertEquals("2", l_response.uploadNumber);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01312, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public void testvalidateUploadFile_0011()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0011()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "00001,,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "00001,2004071690001,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(1);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(1);
            l_loginTypeAttributeParams1.setAttributeName("PASSWORD_MAX_LENGTH");
            l_loginTypeAttributeParams1.setAttributeValue("30");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public void testvalidateUploadFile_0012()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0012()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
        String[] l_struploadFiles = new String[3];
        l_struploadFiles[0] = "00001,2004071690000,381,600005,11123,,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_struploadFiles[1] = "";
        l_struploadFiles[2] = "00001,2004071690001,381,600005,11123,0,3,20040716100000,福永,祐一,フクナガ,ユウイチ,1,3,101010,12355@@163.com,1001235,滋賀県栗東市,御園,栗東トレーニングセンター,シガケンリットウシ,ミソノ１０２８,リットウトレーニングセンター,646433,2222,4343,132344,5566,2231,95,日本中央競馬会（ＪＲＡ）,2222222,東京都港区西新橋１丁目１番１９号,222333,2323,3232,栗東,役職名,2,坂本　@龍一,世帯主勤務先名称,世帯主役職名,5,2121,りそな銀行,051,大阪営業部,2,0190544,12345,12345678,2,1,2,1,1,3,1,2,0,0,1,1,1,0,0,6,6,0,1,ＪＲ東日本,2,1,3,2,4,05";
        l_request.uploadFile = l_struploadFiles;
        WEB3AdminAccOpenApplyUploadConfirmResponse l_response = null;
        
        try
        {
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(1);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(1);
            l_loginTypeAttributeParams1.setAttributeName("PASSWORD_MAX_LENGTH");
            l_loginTypeAttributeParams1.setAttributeValue("30");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(AccOpenItemMasterParams.TYPE);
            
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            
            l_response = impl.validateUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309, l_ex.getErrorInfo());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ

            Object[] l_objAdministratorUploadWhere =
                { "0D", //証券会社コード
                "123", //部店ID
                ProductTypeEnum.OTHER //銘柄タイプ
            };

            try
            {
                List l_lisRecords = null;
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =
                    l_queryProcessor.doFindAllQuery(
                        AdministratorUploadRow.TYPE,
                        l_sbWhere.toString(),
                        l_objAdministratorUploadWhere);
    
                assertEquals(1, l_lisRecords.size());
            }
            catch (Exception ex)
            {
                log.error(STR_METHOD_NAME, ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
    *
    */
    public void testGetUploadScreen_case0001()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);



            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdminAccOpenApplyUploadInputRequest l_request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            l_impl.getUploadScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testGetUploadScreen_case0002()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            WEB3AdminAccOpenApplyUploadInputRequest l_request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            l_impl.getUploadScreen(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testGetUploadScreen_case0003()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setInstitutionCode("0D");
            l_administratorUploadParams.setUploadFileId("口座開設申込");
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_administratorUploadParams.setUploadKey(new Long(0));
            l_administratorUploadParams.setNote1("note1");

            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminAccOpenApplyUploadInputRequest l_request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            WEB3AdminAccOpenApplyUploadInputResponse l_response =
                l_impl.getUploadScreen(l_request);
            assertEquals(WEB3AccOpenUploadStateDivDef.UPLOADING,
                l_response.uploadHistoryList.uploadStateDiv);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testGetUploadScreen_case0004()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_case0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(GtlUtils.getSystemTimestamp());
            l_administratorUploadParams.setInstitutionCode("0D");
            l_administratorUploadParams.setUploadFileId("口座開設申込");
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_administratorUploadParams.setUploadKey(new Long(0));
            l_administratorUploadParams.setNote1("note1");
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            WEB3AdminAccOpenApplyUploadInputRequest l_request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            WEB3AdminAccOpenApplyUploadInputResponse l_response = l_impl.getUploadScreen(l_request);
            assertEquals(WEB3AccOpenUploadStateDivDef.UPLOAD_COMPLETE,
                l_response.uploadHistoryList.uploadStateDiv);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testGetUploadScreen_case0005()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            WEB3AdminAccOpenApplyUploadInputRequest l_request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            WEB3AdminAccOpenApplyUploadInputResponse l_response = l_impl.getUploadScreen(l_request);
            assertNull(l_response.uploadHistoryList);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = null;
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0003()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0004()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.FALSE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0005()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setInstitutionCode("0D");
            l_administratorUploadParams.setUploadFileId("口座開設申込");
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_administratorUploadParams.setUploadKey(new Long(0));
            l_administratorUploadParams.setBranchCode("123");
            l_administratorUploadParams.setNote1("note1");
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01969, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0006()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.submitUploadFile(l_request);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0007()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            String l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "c,";
                    }
                }
                else
                {
                    l_str += "c";
                }
            }
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(2);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(3);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);

            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("c");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.submitUploadFile(l_request);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0008()
    {
        
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            String l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "c,";
                    }
                }
                else
                {
                    l_str += "c";
                }
            }

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(2);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(3);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);

            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "1,";
                    }
                }
                else
                {
                    l_str += "1";
                }
            }

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(4);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("c");
            TestDBUtility.insertWithDel(l_branchParams);


            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33382);
            l_branchParams.setBranchCode("1");
            l_branchParams.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.submitUploadFile(l_request);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testSubmitUploadFile_case0009()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_case0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            String l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "c,";
                    }
                }
                else
                {
                    l_str += "c";
                }
            }

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(2);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(3);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);

            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "1,";
                    }
                }
                else
                {
                    l_str += "1";
                }
            }

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(4);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("c");
            TestDBUtility.insertWithDel(l_branchParams);

            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33382);
            l_branchParams.setBranchCode("1");
            l_branchParams.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testExec_case0001()
    {
        final String STR_METHOD_NAME = "testExec_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            String l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "c,";
                    }
                }
                else
                {
                    l_str += "c";
                }
            }

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(2);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(3);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);

            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_str = "";
            for (int i =0; i < 77; i++)
            {
                if (i != 76)
                {
                    if (i == 7)
                    {
                        l_str += "20071127170809,";
                    }
                    else
                    {
                        l_str += "1,";
                    }
                }
                else
                {
                    l_str += "1";
                }
            }

            l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setLineNumber(4);
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue(l_str);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("c");
            TestDBUtility.insertWithDel(l_branchParams);


            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33382);
            l_branchParams.setBranchCode("1");
            l_branchParams.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3AdminAccOpenApplyUploadCompleteRequest l_request =
                new WEB3AdminAccOpenApplyUploadCompleteRequest();
            l_request.uploadID = "123";
            l_request.password = "123";
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testExec_case0002()
    {
        final String STR_METHOD_NAME = "testExec_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl l_impl = new WEB3AdminAccOpenApplyULServiceImpl();
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0403");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(GtlUtils.getSystemTimestamp());
            l_administratorUploadParams.setInstitutionCode("0D");
            l_administratorUploadParams.setUploadFileId("口座開設申込");
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_administratorUploadParams.setUploadKey(new Long(0));
            l_administratorUploadParams.setNote1("note1");
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            WEB3AdminAccOpenApplyUploadInputRequest l_request =
                new WEB3AdminAccOpenApplyUploadInputRequest();
            WEB3AdminAccOpenApplyUploadInputResponse l_response =
                (WEB3AdminAccOpenApplyUploadInputResponse)l_impl.execute(l_request);
            assertEquals(WEB3AccOpenUploadStateDivDef.UPLOAD_COMPLETE,
                l_response.uploadHistoryList.uploadStateDiv);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testExec_case0003()
    {
        final String STR_METHOD_NAME = "testExec_case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            WEB3AdminAccOpenApplyUploadCancelRequest l_request = new WEB3AdminAccOpenApplyUploadCancelRequest();
            l_request.uploadID = "123";
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testExec_case0004()
    {
        final String STR_METHOD_NAME = "testExec_case0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            impl.execute(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testExec_case0005()
    {
        final String STR_METHOD_NAME = "testExec_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenInspectListRequest l_request = new WEB3AdminAccOpenInspectListRequest();
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            impl.execute(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
    public void testExec_case0006()
    {
        final String STR_METHOD_NAME = "testExec_case0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenApplyUploadConfirmRequest l_request = new WEB3AdminAccOpenApplyUploadConfirmRequest();
            WEB3AdminAccOpenApplyULServiceImpl impl = new WEB3AdminAccOpenApplyULServiceImpl();
            impl.execute(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00976, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
        }
    }

    /**
    *
    */
   public class LoginInfoImplTest extends LoginInfoImpl
   {
       /**
        * @@return long
        */
       public long getLoginId()
       {
           return 1001L;
       }
   }

}
@
