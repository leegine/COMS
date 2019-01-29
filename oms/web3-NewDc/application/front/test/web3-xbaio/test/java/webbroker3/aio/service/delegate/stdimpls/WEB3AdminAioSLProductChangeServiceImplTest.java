head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminAioSLProductChangeServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/26 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImplForMock;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.message.WEB3GentradeProspectusDisplayRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductChangeServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAioSLProductChangeServiceImplTest.class);

    WEB3AdminAioSLProductChangeServiceImpl l_serviceImpl = null;

    public WEB3AdminAioSLProductChangeServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_serviceImpl = new WEB3AdminAioSLProductChangeServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Services.overrideService(WEB3AdminAioSLProductRegistControlService.class,
            new WEB3AdminAioSLProductRegistControlServiceImplForMock());
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            new Timestamp(WEB3DateUtility.getDate("20080901","yyyymmdd").getTime()));
    }

    protected void tearDown() throws Exception
    {
        l_serviceImpl = null;
        super.tearDown();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
    }

    public void testExcute_case0001()
    {
        final String STR_METHOD_NAME = "testExcute_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_serviceImpl.execute(null);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExcute_case0002()
    {
        final String STR_METHOD_NAME = "testExcute_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioSLProductChangeServiceImpl l_serviceImpl = new WEB3AdminAioSLProductChangeServiceImplForTest();
        try
        {
            WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminSLProductChangeInputResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExcute_case0003()
    {
        final String STR_METHOD_NAME = "testExcute_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioSLProductChangeServiceImpl l_serviceImpl = new WEB3AdminAioSLProductChangeServiceImplForTest();
        try
        {
            WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequest();
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminSLProductChangeConfirmResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExcute_case0004()
    {
        final String STR_METHOD_NAME = "testExcute_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAioSLProductChangeServiceImpl l_serviceImpl = new WEB3AdminAioSLProductChangeServiceImplForTest();
        try
        {
            WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequest();
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminSLProductChangeCompleteResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExcute_case0005()
    {
        final String STR_METHOD_NAME = "testExcute_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeProspectusDisplayRequest l_request = new WEB3GentradeProspectusDisplayRequest();
            l_serviceImpl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 担保銘柄登録変更入力リクエスト.validate()異常
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02917
     */
    public void testGetSLProductChangeInputScreen_case0001()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInputScreen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
        try
        {
            l_serviceImpl.getSLProductChangeInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02917, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate権限(機@能カテゴリコード : String, is更新 : boolean)異常
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testGetSLProductChangeInputScreen_case0002()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInputScreen_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = new Date(20070926);
        l_request.searchConditions.productId = 222313L;
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("230");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            l_serviceImpl.getSLProductChangeInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * レコードが取得できない場合
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02837
     */
    public void testGetSLProductChangeInputScreen_case0003()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInputScreen_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = new Date(20070926);
        l_request.searchConditions.productId = 2223163L;
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            l_serviceImpl.getSLProductChangeInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常通過
     * 
     */
    public void testGetSLProductChangeInputScreen_case0004()
    {
        final String STR_METHOD_NAME = "testGetSLProductChangeInputScreen_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070926", "yyyyMMdd");
        l_request.searchConditions.productId = 1006169090018L;
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070926", "yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071026", "yyyyMMdd"));
            l_securityProductParams.setReason("hahaha");
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            WEB3AdminSLProductChangeInputResponse l_response = 
                l_serviceImpl.getSLProductChangeInputScreen(l_request);
            
            assertEquals(1006169090018L, l_response.stockLoanProductInfo.productId);
            assertEquals("1", l_response.stockLoanProductInfo.productCode);
            assertEquals("8", l_response.stockLoanProductInfo.productType);
            assertEquals("シンセンテルス", l_response.stockLoanProductInfo.productName);
            assertEquals("0", l_response.stockLoanProductInfo.qualifiedDiv);
            assertEquals("263", l_response.stockLoanProductInfo.weight);
            assertEquals(WEB3DateUtility.getDate("20070926", "yyyyMMdd"), l_response.stockLoanProductInfo.targetPeriodFrom);
            assertEquals(WEB3DateUtility.getDate("20071026", "yyyyMMdd"), l_response.stockLoanProductInfo.targetPeriodTo);
            assertEquals("hahaha", l_response.stockLoanProductInfo.reason);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 担保銘柄登録変更確認リクエスト.validate()異常
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02917
     */
    public void testValidateSLProductChange_case0001()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequest();
        try
        {
            l_serviceImpl.validateSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02917, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate権限(機@能カテゴリコード : String, is更新 : boolean)異常
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testValidateSLProductChange_case0002()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequestForTest();
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("230");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            l_serviceImpl.validateSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * レコードが取得できない場合
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02837
     */
    public void testValidateSLProductChange_case0003()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 2313554521213L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            l_serviceImpl.validateSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate変更項目(担保銘柄Row, 担保銘柄登録情報)
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02688
     */
    public void testValidateSLProductChange_case0004()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 1006169090018L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071026", "yyyyMMdd"));
            l_securityProductParams.setReason("hahaha");
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(1));
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            l_serviceImpl.validateSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常通過
     *
     */
    public void testValidateSLProductChange_case0005()
    {
        final String STR_METHOD_NAME = "testValidateSLProductChange_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeConfirmRequest l_request = new WEB3AdminSLProductChangeConfirmRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 1006169090018L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071026", "yyyyMMdd"));
            l_securityProductParams.setReason("hahaha");
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(0));
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_request.changedStockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20081202", "yyyyMMdd");
            l_request.changedStockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20081002", "yyyyMMdd");
            l_serviceImpl.validateSLProductChange(l_request);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 担保銘柄登録変更完了リクエスト.validate()異常
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02917
     */
    public void testSubmitSLProductChange_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequest();
        try
        {
            l_serviceImpl.submitSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02917, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate権限(機@能カテゴリコード : String, is更新 : boolean)異常
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testSubmitSLProductChange_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequestForTest();
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("230");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            l_serviceImpl.submitSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取引パスワード(パスワード : String) 異常
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testSubmitSLProductChange_case0003()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 2313554521213L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        l_request.password = "nopassword";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
                        
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginTypeId",
                    new Class[] {},
                    new Long(123l));
                
                HashMap l_map = new HashMap();
                l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, null);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                        "getLoginTypeAttributes",
                        new Class[] {long.class},
                        l_map);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            l_serviceImpl.submitSLProductChange(l_request);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * レコードが取得できない場合
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02837
     */
    public void testSubmitSLProductChange_case0004()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 2313554521213L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        l_request.password = "nopassword";
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("nopassword", true);
            l_serviceImpl.submitSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate変更項目(担保銘柄Row, 担保銘柄登録情報)
     * throw WEB3ErrorCatalog.BUSINESS_ERROR_02688
     */
    public void testSubmitSLProductChange_case0005()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 1006169090018L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        l_request.password = "nopassword";
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071026", "yyyyMMdd"));
            l_securityProductParams.setReason("hahaha");
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(1));
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("nopassword", true);
            l_serviceImpl.submitSLProductChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常通過
     *
     */
    public void testSubmitSLProductChange_case0006()
    {
        final String STR_METHOD_NAME = "testSubmitSLProductChange_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductChangeCompleteRequest l_request = new WEB3AdminSLProductChangeCompleteRequestForTest();
        l_request.searchConditions = new WEB3SLProductSearchConditions();
        l_request.searchConditions.productId = 1006169090018L;
        l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
        l_request.password = "nopassword";
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams =TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("231");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071026", "yyyyMMdd"));
            l_securityProductParams.setReason("hahaha");
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(0));
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("nopassword", true);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "B0602", true, true);
            l_request.changedStockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_request.changedStockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20081226", "yyyyMMdd");
            l_request.changedStockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20081026", "yyyyMMdd");
            l_request.changedStockLoanProductInfo.reason = "12231";
            l_request.changedStockLoanProductInfo.qualifiedDiv = "0";
            l_serviceImpl.submitSLProductChange(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSLProductChangeUpdateInfo_case0001()
    {
        final String STR_METHOD_NAME = "testCreateSLProductChangeUpdateInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SLProductInfoUnit l_changedProductInfo = new WEB3SLProductInfoUnit();
            l_changedProductInfo.qualifiedDiv = "0";
            l_changedProductInfo.reason = "1234544";
            l_changedProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070827", "yyyyMMdd");
            String l_strAdminCode = "1285554";
            Date l_datUpdateDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            
            SecurityProductParams l_SecurityProductParams = TestDBUtility.getSecurityProductRow();
            
            Method l_method = WEB3AdminAioSLProductChangeServiceImpl.class.getDeclaredMethod(
                    "createSLProductChangeUpdateInfo",
                    new Class[]{WEB3SLProductInfoUnit.class, String.class, Date.class, SecurityProductRow.class});
            
            l_method.setAccessible(true);
            SecurityProductParams l_SecurityProductParamsResult = (SecurityProductParams)l_method.invoke(l_serviceImpl,
                new Object[]{l_changedProductInfo, l_strAdminCode, l_datUpdateDate, l_SecurityProductParams});

            assertEquals("0", l_SecurityProductParamsResult.getFitFlg());
            assertEquals("0.0", l_SecurityProductParamsResult.getEstimationRatio() + "");
            assertEquals(WEB3DateUtility.getDate("20070827", "yyyyMMdd"), l_SecurityProductParamsResult.getApplyTermFrom());
            assertEquals(WEB3DateUtility.getDate("99991231", "yyyyMMdd"), l_SecurityProductParamsResult.getApplyTermTo());
            assertEquals("1234544", l_SecurityProductParamsResult.getReason());
            assertEquals("1285554", l_SecurityProductParamsResult.getLastUpdater());
            assertEquals(WEB3DateUtility.getDate("20070927", "yyyyMMdd"), l_SecurityProductParamsResult.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSLProductChangeUpdateInfo_case0002()
    {
        final String STR_METHOD_NAME = "testCreateSLProductChangeUpdateInfo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SLProductInfoUnit l_changedProductInfo = new WEB3SLProductInfoUnit();
            l_changedProductInfo.qualifiedDiv = "0";
            l_changedProductInfo.weight = "2.36";
            l_changedProductInfo.reason = "1234544";
            l_changedProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070827", "yyyyMMdd");
            l_changedProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            String l_strAdminCode = "1285554";
            Date l_datUpdateDate = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            
            SecurityProductParams l_SecurityProductParams = TestDBUtility.getSecurityProductRow();
            
            Method l_method = WEB3AdminAioSLProductChangeServiceImpl.class.getDeclaredMethod(
                    "createSLProductChangeUpdateInfo",
                    new Class[]{WEB3SLProductInfoUnit.class, String.class, Date.class, SecurityProductRow.class});
            
            l_method.setAccessible(true);
            SecurityProductParams l_SecurityProductParamsResult = (SecurityProductParams)l_method.invoke(l_serviceImpl,
                new Object[]{l_changedProductInfo, l_strAdminCode, l_datUpdateDate, l_SecurityProductParams});

            assertEquals("0", l_SecurityProductParamsResult.getFitFlg());
            assertEquals("2.36", l_SecurityProductParamsResult.getEstimationRatio() + "");
            assertEquals(WEB3DateUtility.getDate("20070827", "yyyyMMdd"), l_SecurityProductParamsResult.getApplyTermFrom());
            assertEquals(WEB3DateUtility.getDate("20070927", "yyyyMMdd"), l_SecurityProductParamsResult.getApplyTermTo());
            assertEquals("1234544", l_SecurityProductParamsResult.getReason());
            assertEquals("1285554", l_SecurityProductParamsResult.getLastUpdater());
            assertEquals(WEB3DateUtility.getDate("20070927", "yyyyMMdd"), l_SecurityProductParamsResult.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testEditSLProductInfo_case0001()
    {
        final String STR_METHOD_NAME = "testEditSLProductInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Method l_method = WEB3AdminAioSLProductChangeServiceImpl.class.getDeclaredMethod(
                "editSLProductInfo",
                new Class[]{Date.class, List.class});
            l_method.setAccessible(true);
            
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            
            List l_lisSecurityProductInfos = new ArrayList();
            l_lisSecurityProductInfos.add(l_securityProductParams);
            
            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            
            List l_lisResults = (List)l_method.invoke(l_serviceImpl, new Object[]{l_datTargetPeriodFrom, l_lisSecurityProductInfos});
            
            assertEquals(0, l_lisResults.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChangeItem_case0001()
    {
        final String STR_METHOD_NAME = "testValidateChangeItem_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Method l_method = WEB3AdminAioSLProductChangeServiceImpl.class.getDeclaredMethod(
                "validateChangeItem",
                new Class[]{SecurityProductRow.class, WEB3SLProductInfoUnit.class});
            l_method.setAccessible(true);
            
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            
            WEB3SLProductInfoUnit l_changedSecurityProductInfo = new WEB3SLProductInfoUnit();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(1));
            
            l_method.invoke(l_serviceImpl, new Object[]{l_securityProductParams, l_changedSecurityProductInfo});
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688, ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChangeItem_case0002()
    {
        final String STR_METHOD_NAME = "testValidateChangeItem_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Method l_method = WEB3AdminAioSLProductChangeServiceImpl.class.getDeclaredMethod(
                "validateChangeItem",
                new Class[]{SecurityProductRow.class, WEB3SLProductInfoUnit.class});
            l_method.setAccessible(true);
            
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            TestDBUtility.insertWithDel(l_securityProductParams);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070927", "yyyyMMdd"));

            WEB3SLProductInfoUnit l_changedSecurityProductInfo = new WEB3SLProductInfoUnit();
            l_changedSecurityProductInfo.productId = 1006169090018L;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(0));
            
            l_method.invoke(l_serviceImpl, new Object[]{l_securityProductParams, l_changedSecurityProductInfo});
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02929, ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChangeItem_case0003()
    {
        final String STR_METHOD_NAME = "testValidateChangeItem_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Method l_method = WEB3AdminAioSLProductChangeServiceImpl.class.getDeclaredMethod(
                "validateChangeItem",
                new Class[]{SecurityProductRow.class, WEB3SLProductInfoUnit.class});
            l_method.setAccessible(true);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20080927", "yyyyMMdd"));

            WEB3SLProductInfoUnit l_changedSecurityProductInfo = new WEB3SLProductInfoUnit();
            l_changedSecurityProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20080927", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "compareChangeInfo",
                    new Class[]{ SecurityProductRow.class, WEB3SLProductInfoUnit.class }, new Integer(0));
            
            l_method.invoke(l_serviceImpl, new Object[]{l_securityProductParams, l_changedSecurityProductInfo});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3AdminAioSLProductChangeServiceImplForTest extends WEB3AdminAioSLProductChangeServiceImpl
    {
        protected WEB3AdminSLProductChangeInputResponse getSLProductChangeInputScreen(
            WEB3AdminSLProductChangeInputRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminSLProductChangeInputResponse();
        }

        protected WEB3AdminSLProductChangeConfirmResponse validateSLProductChange(
            WEB3AdminSLProductChangeConfirmRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminSLProductChangeConfirmResponse();
        }

        protected WEB3AdminSLProductChangeCompleteResponse submitSLProductChange(
            WEB3AdminSLProductChangeCompleteRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminSLProductChangeCompleteResponse();
        }
    }
    
    private class WEB3AdminSLProductChangeConfirmRequestForTest extends WEB3AdminSLProductChangeConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminSLProductChangeCompleteRequestForTest extends WEB3AdminSLProductChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
}
@
