head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesContractInquiryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/15 劉剣（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.message.WEB3FuturesContractReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsContractReferenceRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesContractInquiryServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractInquiryServiceImplTest.class);
    
    private WEB3FuturesContractInquiryServiceImpl l_impl = null;

    public WEB3FuturesContractInquiryServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3FuturesContractInquiryServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * パラメータタイプ不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3OptionsContractReferenceRequest l_request = new WEB3OptionsContractReferenceRequest();
            this.l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * create銘柄コード名称from建玉の返り値がNULLの場合、
     * is取引可能顧客(補助口座 : 補助口座) = true
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.pageSize = "1";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            assertNull(l_response.contractReferenceUnits);
            assertEquals("0",l_response.totalPages);
            assertEquals("0",l_response.totalRecords);
            assertEquals("0",l_response.pageIndex);
            assertNull(l_response.futOpProductCodeNames);
            assertFalse(l_response.accountLock);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * create銘柄コード名称from建玉の返り値がNULLの場合、
     * is取引可能顧客(補助口座 : 補助口座) = false
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.pageSize = "1";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            assertNull(l_response.contractReferenceUnits);
            assertEquals("0",l_response.totalPages);
            assertEquals("0",l_response.totalRecords);
            assertEquals("0",l_response.pageIndex);
            assertNull(l_response.futOpProductCodeNames);
            assertTrue(l_response.accountLock);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * リクエストデータに銘柄コードがセットされている場のみ、
     * NotFoundException
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.pageSize = "1";
            l_request.futProductCode = "2";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * create先物建玉照会明細の返り値がNULLの場合、
     * is取引可能顧客(補助口座 : 補助口座) = true
     */
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        WEB3FuturesOptionsProductCodeNameUnit[] l_futuresOptionsProductCodeNameUnit = new WEB3FuturesOptionsProductCodeNameUnit[1];
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createProductCodeNameFromContract", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, String.class },
                l_futuresOptionsProductCodeNameUnit);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.pageSize = "1";
            l_request.futProductCode = "160030005";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            assertNull(l_response.contractReferenceUnits);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.pageIndex);
            assertEquals(l_futuresOptionsProductCodeNameUnit, l_response.futOpProductCodeNames);
            assertFalse(l_response.accountLock);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * create先物建玉照会明細の返り値がNULLの場合、
     * is取引可能顧客(補助口座 : 補助口座) = fasle
     */
    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        WEB3FuturesOptionsProductCodeNameUnit[] l_futuresOptionsProductCodeNameUnit = new WEB3FuturesOptionsProductCodeNameUnit[1];
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createProductCodeNameFromContract", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, String.class },
                l_futuresOptionsProductCodeNameUnit);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.pageSize = "1";
            l_request.futProductCode = "160030005";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            assertNull(l_response.contractReferenceUnits);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.pageIndex);
            assertEquals(l_futuresOptionsProductCodeNameUnit, l_response.futOpProductCodeNames);
            assertTrue(l_response.accountLock);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * レスポンス.総レコード数 <= (リクエストデータ.ページ内表示行数 * (リクエストデータ.要求ページ番号 - 1))の場合
     * is取引可能顧客(補助口座 : 補助口座) = true
     */
    public void testExecute_C0007()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        WEB3FuturesOptionsProductCodeNameUnit[] l_futuresOptionsProductCodeNameUnit = new WEB3FuturesOptionsProductCodeNameUnit[1];
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createProductCodeNameFromContract", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, String.class },
                l_futuresOptionsProductCodeNameUnit);

        WEB3FuturesContractReferenceUnit[] l_futuresContractReferenceUnit = new WEB3FuturesContractReferenceUnit[0];
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createFuturesContractInquiryDetails",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_futuresContractReferenceUnit);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.futOpSortKeys[0].ascDesc = "A";
            l_request.pageSize = "1";
            l_request.pageIndex = "5";
            l_request.futProductCode = "160030005";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.pageIndex);
            assertEquals(l_futuresOptionsProductCodeNameUnit, l_response.futOpProductCodeNames);
            assertFalse(l_response.accountLock);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * l_intRemains != 0
     * is取引可能顧客(補助口座 : 補助口座) = false
     */
    public void testExecute_C0008()
    {
        final String STR_METHOD_NAME = "testExecute_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateFuturesOptionAccountOpen",
                new Class[]{ SubAccount.class, String.class },
                null);

        WEB3FuturesOptionsProductCodeNameUnit[] l_futuresOptionsProductCodeNameUnit = new WEB3FuturesOptionsProductCodeNameUnit[1];
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createProductCodeNameFromContract", new Class[]
                { WEB3GentradeSubAccount.class, boolean.class, String.class },
                l_futuresOptionsProductCodeNameUnit);

        WEB3FuturesContractReferenceUnit[] l_futuresContractReferenceUnit = new WEB3FuturesContractReferenceUnit[5];
        for(int i = 0;i < 5; i++)
        {
            l_futuresContractReferenceUnit[i] = new WEB3FuturesContractReferenceUnit();
        }
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createFuturesContractInquiryDetails",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_futuresContractReferenceUnit);

        try
        {
            WEB3FuturesContractReferenceRequest l_request = new WEB3FuturesContractReferenceRequest();
            l_request.settlementState = null;
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "openDate";
            l_request.futOpSortKeys[0].ascDesc = "A";
            l_request.pageSize = "3";
            l_request.pageIndex = "1";
            l_request.futProductCode = "160030005";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesContractReferenceResponse l_response =
                (WEB3FuturesContractReferenceResponse)l_impl.execute(l_request);

            assertEquals("2", l_response.totalPages);
            assertEquals("5", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals(l_futuresOptionsProductCodeNameUnit, l_response.futOpProductCodeNames);
            assertTrue(l_response.accountLock);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、
     */
    public void testCreateSearchCondCharacter_C0001()
    {
        final String STR_METHOD_NAME = "testCreateSearchCondCharacter_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "createSearchCondCharacter",
                    new Class[]{String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new String("1")};
            String l_strReturnValue = (String) l_method.invoke(l_impl, l_obj);
            
            assertEquals(" and product_id = ?", l_strReturnValue);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * パラメータ.銘柄コード = NULL（銘柄コード指定）の場合、
     */
    public void testCreateSearchCondCharacter_C0002()
    {
        final String STR_METHOD_NAME = "testCreateSearchCondCharacter_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "createSearchCondCharacter",
                    new Class[]{String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new String("")};
            String l_strReturnValue = (String)l_method.invoke(l_impl, l_obj);
            
            assertNull(l_strReturnValue);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、
     */
    public void testCreateSearchCondDataContainer_C0001()
    {
        final String STR_METHOD_NAME = "testCreateSearchCondDataContainer_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "createSearchCondDataContainer",
                    new Class[]{String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new String("160030005")};
            String[] l_strReturnValue = (String[])l_method.invoke(l_impl, l_obj);
            
            assertEquals("1006160060005",l_strReturnValue[0]);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * 該当銘柄なし。
     * ?出：WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testCreateSearchCondDataContainer_C0002()
    {
        final String STR_METHOD_NAME = "testCreateSearchCondDataContainer_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "createSearchCondDataContainer",
                    new Class[]{String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new String("160030005")};
            l_method.invoke(l_impl, l_obj);
            
            fail();

        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3SystemLayerException.class, l_exITE.getTargetException().getClass());
            WEB3SystemLayerException l_exception = (WEB3SystemLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exception.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * パラメータ.銘柄コード＝NULLの場合、NULLを返却する
     */
    public void testCreateSearchCondDataContainer_C0003()
    {
        final String STR_METHOD_NAME = "testCreateSearchCondDataContainer_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "createSearchCondDataContainer",
                    new Class[]{String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new String("")};
            String[] l_strReturnValue = (String[])l_method.invoke(l_impl, l_obj);
            
            assertNull(l_strReturnValue);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * キー項目が銘柄コードであった場合、
     */
    public void testSortContractReferenceUnit_C0001()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceUnit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "sortContractReferenceUnit",
                    new Class[]{WEB3FuturesContractReferenceUnit[].class, WEB3FuturesOptionsSortKey[].class});
            l_method.setAccessible(true);

            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit[2];
            l_contractReferenceUnit[0] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[0].futProductCode = "1";
            l_contractReferenceUnit[1] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[1].futProductCode = "2";
            
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[1];
            l_sortKey[0] = new WEB3FuturesOptionsSortKey();
            l_sortKey[0].ascDesc = "D";
            l_sortKey[0].keyItem = "futProductCode";

            Object[] l_obj = {l_contractReferenceUnit, l_sortKey};
            WEB3FuturesContractReferenceUnit[] l_strReturnValue = (WEB3FuturesContractReferenceUnit[])l_method.invoke(l_impl, l_obj);
            
            assertEquals("2", l_strReturnValue[0].futProductCode);
            assertEquals("1", l_strReturnValue[1].futProductCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * キー項目が損益であった場合、
     */
    public void testSortContractReferenceUnit_C0002()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceUnit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "sortContractReferenceUnit",
                    new Class[]{WEB3FuturesContractReferenceUnit[].class, WEB3FuturesOptionsSortKey[].class});
            l_method.setAccessible(true);

            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit[2];
            l_contractReferenceUnit[0] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[0].income = "1";
            l_contractReferenceUnit[1] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[1].income = "2";
            
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[1];
            l_sortKey[0] = new WEB3FuturesOptionsSortKey();
            l_sortKey[0].ascDesc = "D";
            l_sortKey[0].keyItem = "income";

            Object[] l_obj = {l_contractReferenceUnit, l_sortKey};
            WEB3FuturesContractReferenceUnit[] l_strReturnValue = (WEB3FuturesContractReferenceUnit[])l_method.invoke(l_impl, l_obj);
            
            assertEquals("2", l_strReturnValue[0].income);
            assertEquals("1", l_strReturnValue[1].income);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * キー項目が建日であった場合、
     */
    public void testSortContractReferenceUnit_C0003()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceUnit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "sortContractReferenceUnit",
                    new Class[]{WEB3FuturesContractReferenceUnit[].class, WEB3FuturesOptionsSortKey[].class});
            l_method.setAccessible(true);

            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit[2];
            l_contractReferenceUnit[0] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[0].openDate = WEB3DateUtility.getDate("20080101","yyyyMMdd");
            l_contractReferenceUnit[1] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[1].openDate = WEB3DateUtility.getDate("20080202","yyyyMMdd");
            
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[1];
            l_sortKey[0] = new WEB3FuturesOptionsSortKey();
            l_sortKey[0].ascDesc = "A";
            l_sortKey[0].keyItem = "openDate";

            Object[] l_obj = {l_contractReferenceUnit, l_sortKey};
            WEB3FuturesContractReferenceUnit[] l_strReturnValue = (WEB3FuturesContractReferenceUnit[])l_method.invoke(l_impl, l_obj);
            
            assertEquals(WEB3DateUtility.getDate("20080101","yyyyMMdd"), l_strReturnValue[0].openDate);
            assertEquals(WEB3DateUtility.getDate("20080202","yyyyMMdd"), l_strReturnValue[1].openDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    /*
     * キー項目が建区分であった場合、
     */
    public void testSortContractReferenceUnit_C0004()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceUnit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            Method l_method = WEB3FuturesContractInquiryServiceImpl.class.getDeclaredMethod(
                    "sortContractReferenceUnit",
                    new Class[]{WEB3FuturesContractReferenceUnit[].class, WEB3FuturesOptionsSortKey[].class});
            l_method.setAccessible(true);

            WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit[2];
            l_contractReferenceUnit[0] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[0].contractType = "1";
            l_contractReferenceUnit[1] = new WEB3FuturesContractReferenceUnit();
            l_contractReferenceUnit[1].contractType = "2";
            
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[1];
            l_sortKey[0] = new WEB3FuturesOptionsSortKey();
            l_sortKey[0].ascDesc = "A";
            l_sortKey[0].keyItem = "contractType";

            Object[] l_obj = {l_contractReferenceUnit, l_sortKey};
            WEB3FuturesContractReferenceUnit[] l_strReturnValue = (WEB3FuturesContractReferenceUnit[])l_method.invoke(l_impl, l_obj);
            
            assertEquals("1", l_strReturnValue[0].contractType);
            assertEquals("2", l_strReturnValue[1].contractType);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
}
@
