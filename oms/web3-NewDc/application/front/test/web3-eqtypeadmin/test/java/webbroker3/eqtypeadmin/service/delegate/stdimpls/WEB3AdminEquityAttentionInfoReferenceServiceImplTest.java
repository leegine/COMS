head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryParams;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryRow;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefDetail;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefSortKey;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoReferenceServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceServiceImplTest.class);

    WEB3AdminEquityAttentionInfoReferenceServiceImpl l_impl =
        new WEB3AdminEquityAttentionInfoReferenceServiceImpl();

    public WEB3AdminEquityAttentionInfoReferenceServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.execute(WEB3GenRequest)'
     */
    //l_request == null
    public void testExecute_0001()
    {
        String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0110");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_impl.execute(null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //l_request instanceof WEB3AdminEqAttentionInfoRefInpRequest
    public void testExecute_0002()
    {
        String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0110");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminEqAttentionInfoRefInpRequest l_request =
                new WEB3AdminEqAttentionInfoRefInpRequest();
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //l_request instanceof WEB3AdminEqAttentionInfoRefRefRequest
    public void testExecute_0003()
    {
        String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();
            l_request.marketCode = "1";
            l_request.productCode = "13010";
            l_request.validDate = "20090106";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[1];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "marketCode";
            l_sortKey[0].ascDesc = "A";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //パラメータタイプ不正
    public void testExecute_0004()
    {
        String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));

            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionテーブルRowを作成AdminPermissionRow 管理者権限
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0110");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminEquityManualLapseConfirmRequest l_request =
                new WEB3AdminEquityManualLapseConfirmRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
 

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.getInputScreen(WEB3AdminEquityAttentionInfoReferenceInputRequest)'
     */
    //正常返回
    public void testGetInputScreen_0001() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetInputScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
       
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd").getTime()));
            
            WEB3AdminEqAttentionInfoRefInpResponse l_response =
                new WEB3AdminEqAttentionInfoRefInpResponse();

            WEB3AdminEqAttentionInfoRefInpRequest l_request =
                new WEB3AdminEqAttentionInfoRefInpRequest();

            l_response = l_impl.getInputScreen(l_request);

            assertEquals("20090109000000", l_response.infoOccuredDateFrom);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate権限
    public void testGetInputScreen_0002() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetInputScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0101");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
       
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd").getTime()));
            

            WEB3AdminEqAttentionInfoRefInpRequest l_request =
                new WEB3AdminEqAttentionInfoRefInpRequest();

            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.getReferenceScreen(WEB3AdminEquityAttentionInfoReferenceRequest)'
     */
    //正常返回
    //檢索條件為注意情報種別升序，注意情報区分コード降序
    public void testGetReferenceScreen_0001() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetReferenceScreen_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[3];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);

            l_attentionInfoHistoryParams[1] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[1].setAttentionInfoHistoryId(1002);
            l_attentionInfoHistoryParams[1].setAttentionInfoType("3");
            l_attentionInfoHistoryParams[1].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[1].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[1].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setMarketId(6001);
            l_attentionInfoHistoryParams[1].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[1].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[1].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[1].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[1].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[1].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[1].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[1]);

            l_attentionInfoHistoryParams[2] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[2].setAttentionInfoHistoryId(1003);
            l_attentionInfoHistoryParams[2].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[2].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[2].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[2].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setMarketId(6001);
            l_attentionInfoHistoryParams[2].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[2].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[2].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[2].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[2].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[2].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[2].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[2]);

            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                new WEB3AdminEqAttentionInfoRefRefResponse();

            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();

            l_request.marketCode = "1";
            l_request.productCode = "13010";
            l_request.validDate = "20090106";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[2];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "attentionInfoType";
            l_sortKey[0].ascDesc = "A";
            l_sortKey[1] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[1].keyItem = "attentionInfoDivCode";
            l_sortKey[1].ascDesc = "D";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_response = l_impl.getReferenceScreen(l_request);

            WEB3AdminEqAttentionInfoRefDetail l_unit1 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit1 = l_response.attentionInfoRefList[0];
            WEB3AdminEqAttentionInfoRefDetail l_unit2 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit2 = l_response.attentionInfoRefList[1];
            WEB3AdminEqAttentionInfoRefDetail l_unit3 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit3 = l_response.attentionInfoRefList[2];
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals("2", l_unit1.attentionInfoType);
            assertEquals("A031", l_unit1.attentionInfoDivCode);
            assertEquals("2", l_unit2.attentionInfoType);
            assertEquals("A001", l_unit2.attentionInfoDivCode);
            assertEquals("3", l_unit3.attentionInfoType);
            assertEquals("A001", l_unit3.attentionInfoDivCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);

    }

    //正常返回
    //檢索條件為情報発生日時升序，銘柄コード降序
    public void testGetReferenceScreen_0002() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetReferenceScreen_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setInstitutionCode("6D");
            l_productParams1.setProductId(6301130200000L);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TradedProductParams l_tradedProductParams1 = TestDBUtility.getTradedProductRow();
            l_tradedProductParams1.setTradedProductId(630101130200000L);
            l_tradedProductParams1.setInstitutionCode("6D");
            l_tradedProductParams1.setMarketId(6001L);
            l_tradedProductParams1.setProductId(6301130200000L);
            l_tradedProductParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams1);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            EqtypeProductParams l_eqtypeProductParams1 = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams1.setProductId(6301130200000L);
            l_eqtypeProductParams1.setInstitutionCode("6D");
            l_eqtypeProductParams1.setProductCode("13020");
            TestDBUtility.insertWithDel(l_eqtypeProductParams1);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            EqtypeTradedProductParams l_eqtypeTradedProductParams1 = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams1.setInstitutionCode("6D");
            l_eqtypeTradedProductParams1.setProductId(6301130200000L);
            l_eqtypeTradedProductParams1.setMarketId(6001L);
            l_eqtypeTradedProductParams1.setTradedProductId(630101130200000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
          
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[3];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);

            l_attentionInfoHistoryParams[1] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[1].setAttentionInfoHistoryId(1002);
            l_attentionInfoHistoryParams[1].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[1].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[1].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[1].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090107","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setMarketId(6001);
            l_attentionInfoHistoryParams[1].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[1].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[1].setValidUntilBizDate("20090107");
            l_attentionInfoHistoryParams[1].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[1].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[1].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[1].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[1]);

            l_attentionInfoHistoryParams[2] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[2].setAttentionInfoHistoryId(1003);
            l_attentionInfoHistoryParams[2].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[2].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[2].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[2].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090107","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setMarketId(6001);
            l_attentionInfoHistoryParams[2].setProductId(6301130200000L);
            l_attentionInfoHistoryParams[2].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[2].setValidUntilBizDate("20090107");
            l_attentionInfoHistoryParams[2].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[2].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[2].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[2].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[2]);

            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                new WEB3AdminEqAttentionInfoRefRefResponse();

            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();

            l_request.attentionInfoType = "2";
            l_request.attentionInfoDivCode = "A031";
            l_request.marketCode = "1";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[2];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "infoOccuredDate";
            l_sortKey[0].ascDesc = "A";
            l_sortKey[1] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[1].keyItem = "productCode";
            l_sortKey[1].ascDesc = "D";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_response = l_impl.getReferenceScreen(l_request);

            WEB3AdminEqAttentionInfoRefDetail l_unit1 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit1 = l_response.attentionInfoRefList[0];
            WEB3AdminEqAttentionInfoRefDetail l_unit2 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit2 = l_response.attentionInfoRefList[1];
            WEB3AdminEqAttentionInfoRefDetail l_unit3 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit3 = l_response.attentionInfoRefList[2];
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals("2009-01-06 00:00:00.0", String.valueOf(l_unit1.infoOccuredDate));
            assertEquals("13010", l_unit1.productCode);
            assertEquals("2009-01-07 00:00:00.0", String.valueOf(l_unit2.infoOccuredDate));
            assertEquals("13020", l_unit2.productCode);
            assertEquals("2009-01-07 00:00:00.0", String.valueOf(l_unit3.infoOccuredDate));
            assertEquals("13010", l_unit3.productCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    //正常返回
    //檢索條件為市場コード升序
    public void testGetReferenceScreen_0003() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetReferenceScreen_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);
                    
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
            l_marketParams1.setMarketId(6002L);
            l_marketParams1.setInstitutionCode("6D");
            l_marketParams1.setMarketCode("2");
            TestDBUtility.insertWithDel(l_marketParams1);
     
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setInstitutionCode("6D");
            l_productParams1.setProductId(6302130200000L);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TradedProductParams l_tradedProductParams1 = TestDBUtility.getTradedProductRow();
            l_tradedProductParams1.setTradedProductId(630201130200000L);
            l_tradedProductParams1.setInstitutionCode("6D");
            l_tradedProductParams1.setMarketId(6002L);
            l_tradedProductParams1.setProductId(6302130200000L);
            l_tradedProductParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams1);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            EqtypeProductParams l_eqtypeProductParams1 = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams1.setProductId(6302130200000L);
            l_eqtypeProductParams1.setInstitutionCode("6D");
            l_eqtypeProductParams1.setProductCode("13020");
            TestDBUtility.insertWithDel(l_eqtypeProductParams1);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams1 = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams1.setInstitutionCode("6D");
            l_eqtypeTradedProductParams1.setProductId(6302130200000L);
            l_eqtypeTradedProductParams1.setMarketId(6002L);
            l_eqtypeTradedProductParams1.setTradedProductId(630201130200000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams1);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[3];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            l_attentionInfoHistoryParams[1] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[1].setAttentionInfoHistoryId(1002);
            l_attentionInfoHistoryParams[1].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[1].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[1].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[1].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090107","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setMarketId(6001);
            l_attentionInfoHistoryParams[1].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[1].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[1].setValidUntilBizDate("20090107");
            l_attentionInfoHistoryParams[1].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[1].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[1].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[1].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[1]);
            
            l_attentionInfoHistoryParams[2] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[2].setAttentionInfoHistoryId(1003);
            l_attentionInfoHistoryParams[2].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[2].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[2].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[2].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090107","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setMarketId(6002);
            l_attentionInfoHistoryParams[2].setProductId(6302130200000L);
            l_attentionInfoHistoryParams[2].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[2].setValidUntilBizDate("20090107");
            l_attentionInfoHistoryParams[2].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[2].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[2].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[2].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[2]);
            
            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                new WEB3AdminEqAttentionInfoRefRefResponse();
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();

            l_request.attentionInfoType = "2";
            l_request.attentionInfoDivCode = "A031";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[1];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "marketCode";
            l_sortKey[0].ascDesc = "A";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_response = l_impl.getReferenceScreen(l_request);

            WEB3AdminEqAttentionInfoRefDetail l_unit1 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit1 = l_response.attentionInfoRefList[0];
            WEB3AdminEqAttentionInfoRefDetail l_unit2 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit2 = l_response.attentionInfoRefList[1];
            WEB3AdminEqAttentionInfoRefDetail l_unit3 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit3 = l_response.attentionInfoRefList[2];
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals("2009-01-07 00:00:00.0", String.valueOf(l_unit1.infoOccuredDate));
            assertEquals("1", l_unit1.marketCode);
            assertEquals("2009-01-06 00:00:00.0", String.valueOf(l_unit2.infoOccuredDate));
            assertEquals("1", l_unit2.marketCode);
            assertEquals("2009-01-07 00:00:00.0", String.valueOf(l_unit3.infoOccuredDate));
            assertEquals("2", l_unit3.marketCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    //get注意情報履歴一覧()の戻り値 == nullの場合
    public void testGetReferenceScreen_0004() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetReferenceScreen_0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);
                    
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
     
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[3];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoHistoryId(1001);      
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            l_attentionInfoHistoryParams[1] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[1].setAttentionInfoHistoryId(1002);
            l_attentionInfoHistoryParams[1].setAttentionInfoType("3");
            l_attentionInfoHistoryParams[1].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[1].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[1].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setMarketId(6001);
            l_attentionInfoHistoryParams[1].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[1].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[1].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[1].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[1].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[1].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[1].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[1]);
            
            l_attentionInfoHistoryParams[2] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[2].setAttentionInfoHistoryId(1003);
            l_attentionInfoHistoryParams[2].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[2].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[2].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[2].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setMarketId(6001);
            l_attentionInfoHistoryParams[2].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[2].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[2].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[2].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[2].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[2].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[2].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[2]);
            
            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                new WEB3AdminEqAttentionInfoRefRefResponse();
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[2];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "infoOccuredDate";
            l_sortKey[0].ascDesc = "A";
            l_sortKey[1] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[1].keyItem = "productCode";
            l_sortKey[1].ascDesc = "D";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.attentionInfoType = "1";
            l_response = l_impl.getReferenceScreen(l_request);

            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.pageIndex);
            assertEquals(null, l_response.attentionInfoRefList);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate権限
    public void testGetReferenceScreen_0005() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetReferenceScreen_0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);
                    
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
     
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0101");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);
            
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[3];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoHistoryId(1001);      
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            l_attentionInfoHistoryParams[1] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[1].setAttentionInfoHistoryId(1002);
            l_attentionInfoHistoryParams[1].setAttentionInfoType("3");
            l_attentionInfoHistoryParams[1].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[1].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[1].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setMarketId(6001);
            l_attentionInfoHistoryParams[1].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[1].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[1].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[1].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[1].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[1].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[1].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[1]);
            
            l_attentionInfoHistoryParams[2] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[2].setAttentionInfoHistoryId(1003);
            l_attentionInfoHistoryParams[2].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[2].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[2].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[2].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setMarketId(6001);
            l_attentionInfoHistoryParams[2].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[2].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[2].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[2].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[2].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[2].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[2].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[2]);
            
            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                new WEB3AdminEqAttentionInfoRefRefResponse();
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[2];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "infoOccuredDate";
            l_sortKey[0].ascDesc = "A";
            l_sortKey[1] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[1].keyItem = "productCode";
            l_sortKey[1].ascDesc = "D";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            l_request.attentionInfoType = "1";
            l_response = l_impl.getReferenceScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //正常返回
    //檢索條件為注意情報種別升序，注意情報区分コード降序
    public void testGetReferenceScreen_0006() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetReferenceScreen_0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(33381330001L));
            //administratorテーブルRowを作成 AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330001L);
            l_administratorParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams1 = new AdminPermissionParams();
            l_adminPermissionParams1.setInstitutionCode("6D");
            l_adminPermissionParams1.setPermissionLevel("331");
            l_adminPermissionParams1.setTransactionCategory("C0110");
            l_adminPermissionParams1.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams1.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams1);

            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[3];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);

            l_attentionInfoHistoryParams[1] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[1].setAttentionInfoHistoryId(1002);
            l_attentionInfoHistoryParams[1].setAttentionInfoType("3");
            l_attentionInfoHistoryParams[1].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[1].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[1].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setMarketId(6001);
            l_attentionInfoHistoryParams[1].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[1].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[1].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[1].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[1].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[1].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[1].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[1].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[1].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[1]);

            l_attentionInfoHistoryParams[2] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[2].setAttentionInfoHistoryId(1003);
            l_attentionInfoHistoryParams[2].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[2].setAttentionInfoDivCode("A001");
            l_attentionInfoHistoryParams[2].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[2].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setMarketId(6001);
            l_attentionInfoHistoryParams[2].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[2].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[2].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[2].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[2].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[2].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[2].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[2].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[2].setNewLowPriceRange(23.22);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[2]);

            WEB3AdminEqAttentionInfoRefRefResponse l_response =
                new WEB3AdminEqAttentionInfoRefRefResponse();

            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();

            l_request.marketCode = "1";
            l_request.productCode = "13010";
            l_request.validDate = "20090106";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            WEB3AdminEqAttentionInfoRefSortKey[] l_sortKey = new WEB3AdminEqAttentionInfoRefSortKey[2];
            l_sortKey[0] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[0].keyItem = "attentionInfoType";
            l_sortKey[0].ascDesc = "A";
            l_sortKey[1] = new WEB3AdminEqAttentionInfoRefSortKey();
            l_sortKey[1].keyItem = "attentionInfoDivCode";
            l_sortKey[1].ascDesc = "D";
            l_request.sortKeys = l_sortKey;
            l_request.pageIndex = "2";
            l_request.pageSize = "1";
            l_response = l_impl.getReferenceScreen(l_request);

            WEB3AdminEqAttentionInfoRefDetail l_unit1 = new WEB3AdminEqAttentionInfoRefDetail();
            l_unit1 = l_response.attentionInfoRefList[0];
            assertEquals("3", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals("2", l_response.pageIndex);
            assertEquals("2", l_unit1.attentionInfoType);
            assertEquals("A001", l_unit1.attentionInfoDivCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.get情報発生日時From()'
     */
    //正常返回
    public void testGetInfoOccuredDateFrom_0001() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetInfoOccuredDateFrom_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd").getTime()));
            
            assertEquals("20090109000000", l_impl.getInfoOccuredDateFrom());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.createQueryString(WEB3AdminEquityAttentionInfoReferenceRequest)'
     */
    //正常返回
    public void testCreateQueryString_0001() throws WEB3BaseException
    {  
        String STR_METHOD_NAME = "testCreateQueryString_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();
                 
            l_request.attentionInfoType = "2";
            l_request.attentionInfoDivCode = "A031";
            l_request.marketCode = "1";
            l_request.productCode = "13010";
            l_request.validDate = "20090218";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            String l_strCreateQueryString = l_impl.createQueryString(l_request);
            String l_str = "institution_code = ? " +
                "and attention_info_type = ? " +
                "and attention_info_div_code = ? " +
                "and market_id = ? and product_id = ? " +
                "and valid_until_biz_date = ? " +
                "and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') " +
                "and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') ";
            assertEquals(l_str, l_strCreateQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //l_request = nullの場合
    public void testCreateQueryString_0002() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testCreateQueryString_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEqAttentionInfoRefRefRequest l_request = 
                new WEB3AdminEqAttentionInfoRefRefRequest();
        String l_strCreateQueryString = l_impl.createQueryString(l_request);
        assertEquals("institution_code = ? ", l_strCreateQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.createQueryDataContainer(WEB3AdminEquityAttentionInfoReferenceRequest, String)'
     */
    //正常返回
    public void testCreateQueryDataContainer_0001() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testCreateQueryDataContainer_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
     
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0110");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();
                 
            l_request.attentionInfoType = "2";
            l_request.attentionInfoDivCode = "A031";
            l_request.marketCode = "1";
            l_request.productCode = "13010";
            l_request.validDate = "20090218";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            String[] l_strCreateQueryDataContainer = l_impl.createQueryDataContainer(l_request, "6D");
            assertEquals("6D", l_strCreateQueryDataContainer[0]);
            assertEquals("2", l_strCreateQueryDataContainer[1]);
            assertEquals("A031", l_strCreateQueryDataContainer[2]);
            assertEquals("6001", l_strCreateQueryDataContainer[3]);
            assertEquals("6301130100000", l_strCreateQueryDataContainer[4]);
            assertEquals("20090218", l_strCreateQueryDataContainer[5]);
            assertEquals("20081229100000", l_strCreateQueryDataContainer[6]);
            assertEquals("20090111110000", l_strCreateQueryDataContainer[7]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //l_request = nullの場合
    public void testCreateQueryDataContainer_0002() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testCreateQueryDataContainer_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request = new WEB3AdminEqAttentionInfoRefRefRequest();
            String[] l_strCreateQueryDataContainer = l_impl.createQueryDataContainer(l_request, "6D");
            assertEquals("6D", l_strCreateQueryDataContainer[0]);
            assertEquals(1, l_strCreateQueryDataContainer.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_0003() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testCreateQueryDataContainer_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
     
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductId(6301130100000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(630101130100000L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setMarketId(6001L);
            l_tradedProductParams.setProductId(6301130100000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("6D");
            l_eqtypeTradedProductParams.setProductId(6301130100000L);
            l_eqtypeTradedProductParams.setMarketId(6001L);
            l_eqtypeTradedProductParams.setTradedProductId(630101130100000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0110");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            WEB3AdminEqAttentionInfoRefRefRequest l_request =
                new WEB3AdminEqAttentionInfoRefRefRequest();
                 
            l_request.attentionInfoType = "2";
            l_request.attentionInfoDivCode = "A031";
            l_request.marketCode = "1";
            l_request.productCode = "14010";
            l_request.validDate = "20090218";
            l_request.infoOccuredDateFrom = "20081229100000";
            l_request.infoOccuredDateTo = "20090111110000";
            String[] l_strCreateQueryDataContainer = l_impl.createQueryDataContainer(l_request, "6D");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.createSortCond(WEB3AdminEquityAttentionInfoReferenceSortKey[])'
     */
    //正常返回
    public void testCreateSortCond_0001()
    {
        String STR_METHOD_NAME = "testCreateSortCond_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefSortKey[] l_sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[5];
        l_sortKeys[0] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[1] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[2] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[3] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[4] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[0].keyItem = "attentionInfoType";
        l_sortKeys[0].ascDesc = "A";
        l_sortKeys[1].keyItem = "attentionInfoDivCode";
        l_sortKeys[1].ascDesc = "D";
        l_sortKeys[2].keyItem = "infoOccuredDate";
        l_sortKeys[2].ascDesc = "A";
        l_sortKeys[3].keyItem = "productCode";
        l_sortKeys[3].ascDesc = "D";
        l_sortKeys[4].keyItem = "marketCode";
        l_sortKeys[4].ascDesc = "A";
        String l_strCreateSortCond = l_impl.createSortCond(l_sortKeys);
        String l_str = "attention_info_type ASC, " +
            "attention_info_div_code DESC, " +
            "info_generation_timestamp ASC, " +
            "product_id DESC, " +
            "market_id ASC";
        assertEquals(l_str, l_strCreateSortCond);
        log.exiting(STR_METHOD_NAME);
    }
    
    //ソート条件に「情報発生日時」が含まれていない場合
    public void testCreateSortCond_0002()
    {
        String STR_METHOD_NAME = "testCreateSortCond_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefSortKey[] l_sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[4];
        l_sortKeys[0] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[1] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[2] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[3] = new WEB3AdminEqAttentionInfoRefSortKey();
        l_sortKeys[0].keyItem = "attentionInfoType";
        l_sortKeys[0].ascDesc = "A";
        l_sortKeys[1].keyItem = "attentionInfoDivCode";
        l_sortKeys[1].ascDesc = "D";
        l_sortKeys[2].keyItem = "productCode";
        l_sortKeys[2].ascDesc = "D";
        l_sortKeys[3].keyItem = "marketCode";
        l_sortKeys[3].ascDesc = "A";
        String l_strCreateSortCond = l_impl.createSortCond(l_sortKeys);
        String l_str = "attention_info_type ASC, " +
            "attention_info_div_code DESC, " +
            "product_id DESC, " +
            "market_id ASC, " +
            "info_generation_timestamp DESC";
        assertEquals(l_str, l_strCreateSortCond);
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常返回
    //參數為null
    public void testCreateSortCond_0003()
    {
        String STR_METHOD_NAME = "testCreateSortCond_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEqAttentionInfoRefSortKey[] l_sortKeys = new WEB3AdminEqAttentionInfoRefSortKey[]{};
        String l_strCreateSortCond = l_impl.createSortCond(l_sortKeys);
        String l_str = "info_generation_timestamp DESC";
        assertEquals(l_str, l_strCreateSortCond);
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.getAttentionInfoHistoryList(String, String[], String)'
     */
    //正常返回
    public void testGetAttentionInfoHistoryList_0001() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetAttentionInfoHistoryList_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1111);
            l_attentionInfoHistoryParams.setAttentionInfoType("2");
            l_attentionInfoHistoryParams.setInstitutionCode("6D");
            l_attentionInfoHistoryParams.setProductId(6301130100000L);
            l_attentionInfoHistoryParams.setMarketId(6001);
            l_attentionInfoHistoryParams.setValidUntilBizDate("20090218");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams.setProcessResultDiv("1");
            l_attentionInfoHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams1 = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams1.setAttentionInfoHistoryId(2222);
            l_attentionInfoHistoryParams1.setAttentionInfoType("2");
            l_attentionInfoHistoryParams1.setInstitutionCode("60");
            l_attentionInfoHistoryParams1.setProductId(6301130100000L);
            l_attentionInfoHistoryParams1.setMarketId(6001);
            l_attentionInfoHistoryParams1.setValidUntilBizDate("20090218");
            l_attentionInfoHistoryParams1.setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams1.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams1.setProcessResultDiv("1");
            l_attentionInfoHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            l_attentionInfoHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        String l_strQueryString = "institution_code = ? " +
            "and attention_info_type = ? " +
            "and attention_info_div_code = ? " +
            "and market_id = ? and product_id = ? " +
            "and valid_until_biz_date = ? " +
            "and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') " +
            "and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') ";
        
        String[] l_strQueryDataContainers = new String[8];
        l_strQueryDataContainers[0] = "6D";
        l_strQueryDataContainers[1] = "2";
        l_strQueryDataContainers[2] = "A031";
        l_strQueryDataContainers[3] = "6001";
        l_strQueryDataContainers[4] = "6301130100000";
        l_strQueryDataContainers[5] = "20090218";
        l_strQueryDataContainers[6] = "20081229100000";
        l_strQueryDataContainers[7] = "20090111110000";

        String l_strSortCond = "attention_info_type ASC, " +
            "attention_info_div_code DESC, " +
            "info_generation_timestamp ASC, " +
            "product_id DESC, " +
            "market_id ASC";
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRow = 
            l_impl.getAttentionInfoHistoryList(l_strQueryString, l_strQueryDataContainers, l_strSortCond);
        assertEquals("6D", l_attentionInfoHistoryRow[0].getInstitutionCode());
        assertEquals("2", l_attentionInfoHistoryRow[0].getAttentionInfoType());
        assertEquals("A031", l_attentionInfoHistoryRow[0].getAttentionInfoDivCode());
        assertEquals(6001, l_attentionInfoHistoryRow[0].getMarketId());
        assertEquals(6301130100000L, l_attentionInfoHistoryRow[0].getProductId());
        assertEquals("20090218", l_attentionInfoHistoryRow[0].getValidUntilBizDate());
        assertEquals(1,l_attentionInfoHistoryRow.length);
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常返回
    public void testGetAttentionInfoHistoryList_0002() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetAttentionInfoHistoryList_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1111);
            l_attentionInfoHistoryParams.setAttentionInfoType("2");
            l_attentionInfoHistoryParams.setInstitutionCode("6D");
            l_attentionInfoHistoryParams.setProductId(6301130100000L);
            l_attentionInfoHistoryParams.setMarketId(6001);
            l_attentionInfoHistoryParams.setValidUntilBizDate("20090218");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams.setProcessResultDiv("1");
            l_attentionInfoHistoryParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams1 = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams1.setAttentionInfoHistoryId(2222);
            l_attentionInfoHistoryParams1.setAttentionInfoType("2");
            l_attentionInfoHistoryParams1.setInstitutionCode("60");
            l_attentionInfoHistoryParams1.setProductId(6301130100000L);
            l_attentionInfoHistoryParams1.setMarketId(6001);
            l_attentionInfoHistoryParams1.setValidUntilBizDate("20090218");
            l_attentionInfoHistoryParams1.setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams1.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams1.setProcessResultDiv("1");
            l_attentionInfoHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            l_attentionInfoHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams1);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams2 = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams2.setAttentionInfoHistoryId(3333);
            l_attentionInfoHistoryParams2.setAttentionInfoType("2");
            l_attentionInfoHistoryParams2.setInstitutionCode("6D");
            l_attentionInfoHistoryParams2.setProductId(6301130100000L);
            l_attentionInfoHistoryParams2.setMarketId(6001);
            l_attentionInfoHistoryParams2.setValidUntilBizDate("20090218");
            l_attentionInfoHistoryParams2.setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams2.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams2.setProcessResultDiv("1");
            l_attentionInfoHistoryParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            l_attentionInfoHistoryParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        String l_strQueryString = "institution_code = ? " +
            "and attention_info_type = ? " +
            "and attention_info_div_code = ? " +
            "and market_id = ? and product_id = ? " +
            "and valid_until_biz_date = ? " +
            "and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') " +
            "and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') ";
        
        String[] l_strQueryDataContainers = new String[8];
        l_strQueryDataContainers[0] = "6D";
        l_strQueryDataContainers[1] = "2";
        l_strQueryDataContainers[2] = "A031";
        l_strQueryDataContainers[3] = "6001";
        l_strQueryDataContainers[4] = "6301130100000";
        l_strQueryDataContainers[5] = "20090218";
        l_strQueryDataContainers[6] = "20081229100000";
        l_strQueryDataContainers[7] = "20090111110000";

        String l_strSortCond = "attention_info_type ASC, " +
            "attention_info_div_code DESC, " +
            "info_generation_timestamp ASC, " +
            "product_id DESC, " +
            "market_id ASC";
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRow = 
            l_impl.getAttentionInfoHistoryList(l_strQueryString, l_strQueryDataContainers, l_strSortCond);
        assertEquals("6D", l_attentionInfoHistoryRow[0].getInstitutionCode());
        assertEquals("2", l_attentionInfoHistoryRow[0].getAttentionInfoType());
        assertEquals("A031", l_attentionInfoHistoryRow[0].getAttentionInfoDivCode());
        assertEquals(6001, l_attentionInfoHistoryRow[0].getMarketId());
        assertEquals(6301130100000L, l_attentionInfoHistoryRow[0].getProductId());
        assertEquals("20090218", l_attentionInfoHistoryRow[0].getValidUntilBizDate());
        assertEquals("6D", l_attentionInfoHistoryRow[1].getInstitutionCode());
        assertEquals("2", l_attentionInfoHistoryRow[1].getAttentionInfoType());
        assertEquals("A031", l_attentionInfoHistoryRow[1].getAttentionInfoDivCode());
        assertEquals(6001, l_attentionInfoHistoryRow[1].getMarketId());
        assertEquals(6301130100000L, l_attentionInfoHistoryRow[1].getProductId());
        assertEquals("20090218", l_attentionInfoHistoryRow[1].getValidUntilBizDate());
        assertEquals(2,l_attentionInfoHistoryRow.length);
        log.exiting(STR_METHOD_NAME);
    }

    //検索結果が取得できなかった場合、nullを返却する
    public void testGetAttentionInfoHistoryList_0003() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "testGetAttentionInfoHistoryList_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
//            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
//            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams l_attentionInfoHistoryParams1 = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams1.setAttentionInfoHistoryId(1111);
            l_attentionInfoHistoryParams1.setAttentionInfoType("2");
            l_attentionInfoHistoryParams1.setInstitutionCode("60");
            l_attentionInfoHistoryParams1.setProductId(6301130100000L);
            l_attentionInfoHistoryParams1.setMarketId(6001);
            l_attentionInfoHistoryParams1.setValidUntilBizDate("20090218");
            l_attentionInfoHistoryParams1.setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams1.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams1.setProcessResultDiv("1");
            l_attentionInfoHistoryParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            l_attentionInfoHistoryParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        String l_strQueryString = "institution_code = ? " +
            "and attention_info_type = ? " +
            "and attention_info_div_code = ? " +
            "and market_id = ? and product_id = ? " +
            "and valid_until_biz_date = ? " +
            "and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') " +
            "and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') ";
        
        String[] l_strQueryDataContainers = new String[8];
        l_strQueryDataContainers[0] = "6D";
        l_strQueryDataContainers[1] = "2";
        l_strQueryDataContainers[2] = "A031";
        l_strQueryDataContainers[3] = "6001";
        l_strQueryDataContainers[4] = "6301130100000";
        l_strQueryDataContainers[5] = "20090218";
        l_strQueryDataContainers[6] = "20081229100000";
        l_strQueryDataContainers[7] = "20090111110000";

        String l_strSortCond = "attention_info_type ASC, " +
            "attention_info_div_code DESC, " +
            "info_generation_timestamp ASC, " +
            "product_id DESC, " +
            "market_id ASC";
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRow = 
            l_impl.getAttentionInfoHistoryList(l_strQueryString, l_strQueryDataContainers, l_strSortCond);
        assertEquals(null,l_attentionInfoHistoryRow);
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl.createAttentionInfoList(AttentionInfoHistoryRow[])'
     */
    //処理対象の要素.基準値（変更前）-処理対象の要素.強制値幅（下限値）（変更前） < 0
    public void testCreateAttentionInfoList_0001()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            assertEquals("2",l_eqAttentionInfoRefDetail[0].attentionInfoType);
            assertEquals("A031",l_eqAttentionInfoRefDetail[0].attentionInfoDivCode);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].infoOccuredDate));
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);
            assertEquals("1",l_eqAttentionInfoRefDetail[0].attentionInfoProcResDiv);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].orderAcceptResumeScheduledDate));
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].buySellSuspendResumeDate));
            assertEquals("11.11",l_eqAttentionInfoRefDetail[0].befChgBasePrice);
            assertEquals("33.33",l_eqAttentionInfoRefDetail[0].befChgUpperPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgLowerPriceRange);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgBasePrice);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgUpperPriceRange);
            assertEquals("23.22",l_eqAttentionInfoRefDetail[0].aftChgLowerPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].title);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].text);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    //処理対象の要素.基準値（変更前）-処理対象の要素.強制値幅（下限値）（変更前） = 0
    public void testCreateAttentionInfoList_0002()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(11.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            assertEquals("2",l_eqAttentionInfoRefDetail[0].attentionInfoType);
            assertEquals("A031",l_eqAttentionInfoRefDetail[0].attentionInfoDivCode);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].infoOccuredDate));
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);
            assertEquals("1",l_eqAttentionInfoRefDetail[0].attentionInfoProcResDiv);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].orderAcceptResumeScheduledDate));
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].buySellSuspendResumeDate));
            assertEquals("11.11",l_eqAttentionInfoRefDetail[0].befChgBasePrice);
            assertEquals("33.33",l_eqAttentionInfoRefDetail[0].befChgUpperPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgLowerPriceRange);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgBasePrice);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgUpperPriceRange);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgLowerPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].title);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].text);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //銘柄ID=nullの場合
    //処理対象の要素.基準値（変更前）-処理対象の要素.強制値幅（下限値）（変更前） > 0
    public void testCreateAttentionInfoList_0003()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(10.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(21.22);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            assertEquals("2",l_eqAttentionInfoRefDetail[0].attentionInfoType);
            assertEquals("A031",l_eqAttentionInfoRefDetail[0].attentionInfoDivCode);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].infoOccuredDate));
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);
            assertEquals("1",l_eqAttentionInfoRefDetail[0].attentionInfoProcResDiv);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].orderAcceptResumeScheduledDate));
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].buySellSuspendResumeDate));
            assertEquals("11.11",l_eqAttentionInfoRefDetail[0].befChgBasePrice);
            assertEquals("33.33",l_eqAttentionInfoRefDetail[0].befChgUpperPriceRange);
            assertEquals("1",l_eqAttentionInfoRefDetail[0].befChgLowerPriceRange);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgBasePrice);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgUpperPriceRange);
            assertEquals("21.22",l_eqAttentionInfoRefDetail[0].aftChgLowerPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].title);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].text);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //処理対象の要素.基準値（変更前）」 = null
    public void testCreateAttentionInfoList_0004()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(10.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(21.22);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            assertEquals("2",l_eqAttentionInfoRefDetail[0].attentionInfoType);
            assertEquals("A031",l_eqAttentionInfoRefDetail[0].attentionInfoDivCode);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].infoOccuredDate));
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);
            assertEquals("1",l_eqAttentionInfoRefDetail[0].attentionInfoProcResDiv);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].orderAcceptResumeScheduledDate));
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].buySellSuspendResumeDate));
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgBasePrice);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgUpperPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgLowerPriceRange);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgBasePrice);
            assertEquals("22.22",l_eqAttentionInfoRefDetail[0].aftChgUpperPriceRange);
            assertEquals("21.22",l_eqAttentionInfoRefDetail[0].aftChgLowerPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].title);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].text);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //処理対象の要素.強制値幅（上限値）（変更前）」 = nullの場合
    //基準値（変更後）：　@= nullの場合
    //制限値幅上限（変更後）：　@= nullの場合
    //制限値幅下限（変更後）：　@= nullの場合
    public void testCreateAttentionInfoList_0005()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            assertEquals("2",l_eqAttentionInfoRefDetail[0].attentionInfoType);
            assertEquals("A031",l_eqAttentionInfoRefDetail[0].attentionInfoDivCode);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].infoOccuredDate));
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);
            assertEquals("1",l_eqAttentionInfoRefDetail[0].attentionInfoProcResDiv);
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].orderAcceptResumeScheduledDate));
            assertEquals("2009-01-06 00:00:00.0",String.valueOf(l_eqAttentionInfoRefDetail[0].buySellSuspendResumeDate));
            assertEquals("11.11",l_eqAttentionInfoRefDetail[0].befChgBasePrice);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgUpperPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].befChgLowerPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].aftChgBasePrice);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].aftChgUpperPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].aftChgLowerPriceRange);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].title);
            assertEquals(null,l_eqAttentionInfoRefDetail[0].text);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 処理対象の要素.市場ID≠nullの場合
     * 処理対象の要素.銘柄ID≠nullの場合
     * 正常返回
     * 市場コード = "1" 銘柄コード = "13010" 銘柄名 = "シンセンテルス"
     */
    public void testCreateAttentionInfoList_0006()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            //市場コード
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            //銘柄コード
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            //銘柄名
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);

            //評価単価（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgEvaluationPrice);
            //評価単価（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgEvaluationPrice);
            //基準値（終値）（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgLastClosingPrice);
            //基準値（終値）（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgLastClosingPrice);
            
            //値幅チェック区分（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgPriceRangeCheckDiv);
            //値幅チェック区分（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgPriceRangeCheckDiv);
            //値幅区分（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgPriceRangeDiv);
            //値幅区分（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgPriceRangeDiv);

            //基準値（updq）（終値）（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgLastClosingPriceUpdq);
            //基準値（updq）（終値）（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgLastClosingPriceUpdq);
            //基準値（updq）（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgBasePriceUpdq);
            //基準値（updq）（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgBasePriceUpdq);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 処理対象の要素.市場ID = nullの場合
     * 処理対象の要素.銘柄ID = nullの場合
     * 正常返回
     * 市場コード = null 銘柄コード = null 銘柄名 = null
     * 値幅チェック区分（変更前）= "1" 値幅チェック区分（変更後）= "2"
     * 値幅区分（変更前）= "3" 値幅区分（変更後）= "4"
     */
    public void testCreateAttentionInfoList_0007()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
//            l_attentionInfoHistoryParams[0].setMarketId(6001);
//            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
//            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            l_attentionInfoHistoryParams[0].setOldPriceRangeType("1");
            l_attentionInfoHistoryParams[0].setNewPriceRangeType("2");
            l_attentionInfoHistoryParams[0].setOldPriceRangeUnitType("3");
            l_attentionInfoHistoryParams[0].setNewPriceRangeUnitType("4");
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_eqtypeProductParams.setProductId(6301130100000L);
//            l_eqtypeProductParams.setInstitutionCode("6D");
//            l_eqtypeProductParams.setProductCode("13010");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setStandardName("シンセ");
//            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
//            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(6001L);
//            l_marketParams.setInstitutionCode("6D");
//            l_marketParams.setMarketCode("1");
//            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            //市場コード
            assertNull(l_eqAttentionInfoRefDetail[0].marketCode);
            //銘柄コード
            assertNull(l_eqAttentionInfoRefDetail[0].productCode);
            //銘柄名
            assertNull(l_eqAttentionInfoRefDetail[0].productName);

            //評価単価（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgEvaluationPrice);
            //評価単価（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgEvaluationPrice);
            //基準値（終値）（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgLastClosingPrice);
            //基準値（終値）（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgLastClosingPrice);
            
            //値幅チェック区分（変更前）
            assertEquals("1", l_eqAttentionInfoRefDetail[0].befChgPriceRangeCheckDiv);
            //値幅チェック区分（変更後）
            assertEquals("2", l_eqAttentionInfoRefDetail[0].aftChgPriceRangeCheckDiv);
            //値幅区分（変更前）
            assertEquals("3", l_eqAttentionInfoRefDetail[0].befChgPriceRangeDiv);
            //値幅区分（変更後）
            assertEquals("4", l_eqAttentionInfoRefDetail[0].aftChgPriceRangeDiv);

            //基準値（updq）（終値）（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgLastClosingPriceUpdq);
            //基準値（updq）（終値）（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgLastClosingPriceUpdq);
            //基準値（updq）（変更前）
            assertNull(l_eqAttentionInfoRefDetail[0].befChgBasePriceUpdq);
            //基準値（updq）（変更後）
            assertNull(l_eqAttentionInfoRefDetail[0].aftChgBasePriceUpdq);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 正常返回
     */
    public void testCreateAttentionInfoList_0008()
    {
        String STR_METHOD_NAME = "testCreateAttentionInfoList_0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryParams.TYPE);
            AttentionInfoHistoryParams[] l_attentionInfoHistoryParams = new AttentionInfoHistoryParams[1];
            l_attentionInfoHistoryParams[0] = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams[0].setAttentionInfoType("2");
            l_attentionInfoHistoryParams[0].setAttentionInfoDivCode("A031");
            l_attentionInfoHistoryParams[0].setInstitutionCode("6D");
            l_attentionInfoHistoryParams[0].setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setMarketId(6001);
            l_attentionInfoHistoryParams[0].setProductId(6301130100000L);
            l_attentionInfoHistoryParams[0].setProcessResultDiv("1");
            l_attentionInfoHistoryParams[0].setValidUntilBizDate("20090106");
            l_attentionInfoHistoryParams[0].setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090106","yyyyMMdd"));
            l_attentionInfoHistoryParams[0].setOldBasePrice(11.11);
            l_attentionInfoHistoryParams[0].setOldHighCompPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setOldLowCompPriceRange(12.11);
            l_attentionInfoHistoryParams[0].setNewBasePrice(22.22);
            l_attentionInfoHistoryParams[0].setNewHighPriceRange(22.22);
            l_attentionInfoHistoryParams[0].setNewLowPriceRange(23.22);
            l_attentionInfoHistoryParams[0].setStandardName("シンセンテルス");
            l_attentionInfoHistoryParams[0].setOldPriceRangeType("1");
            l_attentionInfoHistoryParams[0].setNewPriceRangeType("2");
            l_attentionInfoHistoryParams[0].setOldPriceRangeUnitType("3");
            l_attentionInfoHistoryParams[0].setNewPriceRangeUnitType("4");
            l_attentionInfoHistoryParams[0].setOldEstimationPrice(10.11);
            l_attentionInfoHistoryParams[0].setNewEstimationPrice(10.22);
            l_attentionInfoHistoryParams[0].setOldLastClosingPrice(10.33);
            l_attentionInfoHistoryParams[0].setNewLastClosingPrice(10.44);
            l_attentionInfoHistoryParams[0].setOldLastClosingPriceUpdq(10.55);
            l_attentionInfoHistoryParams[0].setNewLastClosingPriceUpdq(10.66);
            l_attentionInfoHistoryParams[0].setOldBasePriceUpdq(10.77);
            l_attentionInfoHistoryParams[0].setNewBasePriceUpdq(10.88);
            TestDBUtility.insertWithDel(l_attentionInfoHistoryParams[0]);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(6301130100000L);
            l_eqtypeProductParams.setInstitutionCode("6D");
            l_eqtypeProductParams.setProductCode("13010");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setStandardName("シンセ");
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(6001L);
            l_marketParams.setInstitutionCode("6D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
                l_impl.createAttentionInfoList(l_attentionInfoHistoryParams);
            
            //市場コード
            assertEquals("1",l_eqAttentionInfoRefDetail[0].marketCode);
            //銘柄コード
            assertEquals("13010",l_eqAttentionInfoRefDetail[0].productCode);
            //銘柄名
            assertEquals("シンセンテルス",l_eqAttentionInfoRefDetail[0].productName);

            //評価単価（変更前）
            assertEquals("10.11", l_eqAttentionInfoRefDetail[0].befChgEvaluationPrice);
            //評価単価（変更後）
            assertEquals("10.22", l_eqAttentionInfoRefDetail[0].aftChgEvaluationPrice);
            //基準値（終値）（変更前）
            assertEquals("10.33", l_eqAttentionInfoRefDetail[0].befChgLastClosingPrice);
            //基準値（終値）（変更後）
            assertEquals("10.44", l_eqAttentionInfoRefDetail[0].aftChgLastClosingPrice);
            
            //値幅チェック区分（変更前）
            assertEquals("1", l_eqAttentionInfoRefDetail[0].befChgPriceRangeCheckDiv);
            //値幅チェック区分（変更後）
            assertEquals("2", l_eqAttentionInfoRefDetail[0].aftChgPriceRangeCheckDiv);
            //値幅区分（変更前）
            assertEquals("3", l_eqAttentionInfoRefDetail[0].befChgPriceRangeDiv);
            //値幅区分（変更後）
            assertEquals("4", l_eqAttentionInfoRefDetail[0].aftChgPriceRangeDiv);

            //基準値（updq）（終値）（変更前）
            assertEquals("10.55", l_eqAttentionInfoRefDetail[0].befChgLastClosingPriceUpdq);
            //基準値（updq）（終値）（変更後）
            assertEquals("10.66", l_eqAttentionInfoRefDetail[0].aftChgLastClosingPriceUpdq);
            //基準値（updq）（変更前）
            assertEquals("10.77", l_eqAttentionInfoRefDetail[0].befChgBasePriceUpdq);
            //基準値（updq）（変更後）
            assertEquals("10.88", l_eqAttentionInfoRefDetail[0].aftChgBasePriceUpdq);

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
