head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductCancelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄取消サービス実装クラス(WEB3AdminAioSLProductCancelServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/26 孫洪江 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmResponse;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteRequest;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductCancelServiceImplTest extends TestBaseForMock
{
    private WEB3AdminAioSLProductCancelServiceImpl l_serviceImpl = null;
    
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductCancelServiceImplTest.class);

    public WEB3AdminAioSLProductCancelServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3AdminAioSLProductCancelServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_serviceImpl = null;
    }

    /**
     * パラメータ値不正の場合
     * 抛出：SYSTEM_ERROR_80017異常信息
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_serviceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータタイプ不正の場合
     * 抛出：SYSTEM_ERROR_80018異常信息
     *
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AioCashinSettleCompleteRequest l_request = new WEB3AioCashinSettleCompleteRequest();
            this.l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。  
     * １－１） 引数のリクエストデータが、担保銘柄登録取消確認リクエストの場合
     *   －validate担保銘柄取消()をコールする。
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLProductCancelConfirmRequest l_request = new WEB3AdminSLProductCancelConfirmRequest();
            this.l_serviceImpl = new WEB3AdminAioSLProductCancelServiceImplForTest();
            this.l_serviceImpl.execute(l_request);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * １－２） 引数のリクエストデータが、担保銘柄登録取消完了リクエストの場合  
     * 　@－submit担保銘柄取消()をコールする。   
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLProductCancelCompleteRequest l_request = new WEB3AdminSLProductCancelCompleteRequest();
            this.l_serviceImpl = new WEB3AdminAioSLProductCancelServiceImplForTest();
            this.l_serviceImpl.execute(l_request);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * レコードが取得できない場合、対象レコード存在なしエラーをthrowする。
     *
     * BUSINESS_ERROR_02837
     */
    public void testValidateSLProductCancel_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(33381330001L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdminSLProductCancelConfirmRequest l_request= new WEB3AdminSLProductCancelConfirmRequestForTest();
            l_request.searchConditions = new WEB3SLProductSearchConditions();
            l_request.searchConditions.productId = 111111111L;
            l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070916","yyyyMMdd");

            this.l_serviceImpl.validateSLProductCancel(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate担保銘柄取消
     * 正常?束
     */
    public void testValidateSLProductCancel_C0002()
    {
        final String STR_METHOD_NAME = "testValidateSLProductCancel_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(33381330001L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdminSLProductCancelConfirmRequest l_request= new WEB3AdminSLProductCancelConfirmRequestForTest();
            l_request.searchConditions = new WEB3SLProductSearchConditions();
            l_request.searchConditions.productId = 1006169090018L;
            l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070916","yyyyMMdd");

            this.l_serviceImpl.validateSLProductCancel(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate担保銘柄取消
     * 「銘柄オブジェクト」沒有取到的場合
     * 
     * 抛出異常信息：SYSTEM_ERROR_80005
     */
    public void testValidateSLProductCancel_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            TestDBUtility.deleteAll(ProductRow.TYPE);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(33381330001L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdminSLProductCancelConfirmRequest l_request= new WEB3AdminSLProductCancelConfirmRequestForTest();
            l_request.searchConditions = new WEB3SLProductSearchConditions();
            l_request.searchConditions.productId = 1006169090018L;
            l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070916","yyyyMMdd");

            this.l_serviceImpl.validateSLProductCancel(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * submit担保銘柄取消
     * 正常?束
     */
    public void testSubmitSLProductCancel_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(33381330001L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdminSLProductCancelCompleteRequest l_request= new WEB3AdminSLProductCancelCompleteRequestForTest();
            l_request.searchConditions = new WEB3SLProductSearchConditions();
            l_request.searchConditions.productId = 1006169090018L;
            l_request.searchConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070916","yyyyMMdd");

            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);

            this.l_serviceImpl.submitSLProductCancel(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //AdministratorRow
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionRow
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0602");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //SecurityProductRow
            SecurityProductParams l_securityProductParams = new SecurityProductParams();
            l_securityProductParams.setProductId(1006169090018L);
            l_securityProductParams.setInstitutionCode("0D");
            l_securityProductParams.setProductCode("1");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(263.3);
            l_securityProductParams.setFitFlg("0");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070916","yyyyMMdd"));
            l_securityProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_securityProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            TestDBUtility.insertWithDel(l_securityProductParams);

            //ProductRow
            ProductParams l_productParams = new ProductParams();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductRow
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //TradedProductRow
            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setMarginRatio(0.000000D);
            l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //MarketRow
            MarketParams l_marketParams = new MarketParams();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("SP");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3AdminAioSLProductCancelServiceImplForTest extends WEB3AdminAioSLProductCancelServiceImpl
    {
        public WEB3AdminSLProductCancelConfirmResponse validateSLProductCancel(
            WEB3AdminSLProductCancelConfirmRequest l_request) throws WEB3BaseException
        {
            
            return new WEB3AdminSLProductCancelConfirmResponse();
        }
        
        public WEB3AdminSLProductCancelCompleteResponse submitSLProductCancel(
                WEB3AdminSLProductCancelCompleteRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminSLProductCancelCompleteResponse();
        }
    }
    
    private class WEB3AdminSLProductCancelConfirmRequestForTest extends WEB3AdminSLProductCancelConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminSLProductCancelCompleteRequestForTest extends WEB3AdminSLProductCancelCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
}
@
