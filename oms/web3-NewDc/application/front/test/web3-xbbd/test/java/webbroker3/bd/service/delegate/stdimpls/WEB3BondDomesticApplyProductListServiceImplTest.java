head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyProductListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 国内債券応募銘柄一覧サービスImpl<BR>
 * @@author 謝旋
 * 2007/08/02
 */
public class WEB3BondDomesticApplyProductListServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyProductListServiceImplTest.class);

    public WEB3BondDomesticApplyProductListServiceImplTest(String arg0)
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

    public void testCreateQueryString_0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" host_recruit_start_date <= ? ");
        l_strWhere.append(" AND host_recruit_end_date >= ? ");
        l_strWhere.append(" AND bond_type <> ? ");
        l_strWhere.append(" AND trade_handle_div = ? ");

        String l_strBondDiv = "3";

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        String l_strReturnString = l_impl.createQueryString(l_strBondDiv);

        log.exiting(TEST_END + STR_METHOD_NAME);
        assertEquals(l_strWhere.toString(), l_strReturnString);
    }
    
    public void testCreateQueryString_0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" host_recruit_start_date <= ? ");
        l_strWhere.append(" AND host_recruit_end_date >= ? ");
        l_strWhere.append(" AND bond_type <> ? ");
        l_strWhere.append(" AND trade_handle_div = ? ");
        l_strWhere.append(" AND bond_type <> ? ");

        String l_strBondDiv = "1";
        
        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        String l_strReturnString = l_impl.createQueryString(l_strBondDiv);

        log.exiting(TEST_END + STR_METHOD_NAME);
        assertEquals(l_strWhere.toString(), l_strReturnString);
    }

    public void testCreateQueryString_0003()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" host_recruit_start_date <= ? ");
        l_strWhere.append(" AND host_recruit_end_date >= ? ");
        l_strWhere.append(" AND bond_type <> ? ");
        l_strWhere.append(" AND trade_handle_div = ? ");
        l_strWhere.append(" AND bond_type = ? ");

        String l_strBondDiv = "2";

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        String l_strReturnString = l_impl.createQueryString(l_strBondDiv);

        log.exiting(TEST_END + STR_METHOD_NAME);
        assertEquals(l_strWhere.toString(), l_strReturnString);
    }

    public void testCreateQueryDataContainer_0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Timestamp l_tsNewTime = GtlUtils.getSystemTimestamp();
        
        Object[] l_obj = new Object[]{
            l_tsNewTime,
            WEB3DateUtility.toDay(l_tsNewTime),
            BondTypeEnum.FOREIGN_BOND,
            "2"};
        
        String l_strBondDiv = "0";

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        Object[] l_objValue = l_impl.createQueryDataContainer(l_strBondDiv);

        assertEquals(l_obj.length, l_objValue.length);

        for (int i = 0; i < l_obj.length; i++)
        {
            assertEquals(l_obj[i], l_objValue[i]);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Timestamp l_tsNewTime = GtlUtils.getSystemTimestamp();
        
        Object[] l_obj = new Object[]{
            l_tsNewTime,
            WEB3DateUtility.toDay(l_tsNewTime),
            BondTypeEnum.FOREIGN_BOND,
            "2",
            BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND};
        
        String l_strBondDiv = "5";

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        Object[] l_objValue = l_impl.createQueryDataContainer(l_strBondDiv);
        
        assertEquals(l_obj.length, l_objValue.length);
        
        for (int i = 0; i < l_obj.length; i++)
        {
            assertEquals(l_obj[i], l_objValue[i]);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0001()
    {
        final String STR_METHOD_NAME = " testCreateSortCondString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strExp = " redemption_term ASC,  host_product_issue_code ASC ";
        
        String l_strBondDiv = "2";
        
        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        String l_strValue = l_impl.createSortCondString(l_strBondDiv);
        
        assertEquals(l_strExp, l_strValue);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSortCondString_0002()
    {
        final String STR_METHOD_NAME = " testCreateSortCondString_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strExp = " host_product_code ASC,  host_product_issue_code ASC ";
        
        String l_strBondDiv = "0";
        
        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        String l_strValue = l_impl.createSortCondString(l_strBondDiv);
        
        assertEquals(l_strExp, l_strValue);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();

        try
        {
            l_impl.execute(null);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = null;
        try
        {
            l_impl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = "23";
        l_request.pageSize = "10";

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context1 = new WEB3GentradeTradingClendarContext();
        l_context1 = l_context;
        l_context1.setBranchCode(null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context1);
        try
        {
            l_impl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = " testExecute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = "23";
        l_request.pageSize = "10";
        l_request.bondDiv = "0";
        WEB3BondDomesticApplyProductListResponse l_response = null;

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context1 = new WEB3GentradeTradingClendarContext();
        l_context1 = l_context;
        l_context1.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context1);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            l_response = (WEB3BondDomesticApplyProductListResponse)l_impl.execute(l_request);

            assertEquals(0, l_response.bondDomesticApplyProductList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0005()
    {
        final String STR_METHOD_NAME = " testExecute_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = "23";
        l_request.pageSize = "10";
        l_request.bondDiv = "0";
        WEB3BondDomesticApplyProductListResponse l_response = null;

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context1 = new WEB3GentradeTradingClendarContext();
        l_context1 = l_context;
        l_context1.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context1);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
            new Class[] {String.class},
            "2");
        
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("99980101", "yyyyMMdd"));
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            l_bondProductParams.setProductName("あああ");
            TestDBUtility.deleteAll(l_bondProductParams.getRowType());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            TestDBUtility.deleteAll(l_taxRateTableParams.getRowType());
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            l_response = (WEB3BondDomesticApplyProductListResponse)l_impl.execute(l_request);

            assertEquals(1, l_response.bondDomesticApplyProductList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            
            //銘柄ID
            assertEquals(String.valueOf(3304140763000L), l_response.bondDomesticApplyProductList[0].productId);
            //銘柄名
            assertEquals("あああ", l_response.bondDomesticApplyProductList[0].productName);
            //応募開始日
            assertEquals(WEB3DateUtility.getDate("20040101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].recruitStartDate.toLocaleString());
            //応募終了日
            assertEquals(WEB3DateUtility.getDate("20070101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].recruitEndDate.toLocaleString());
            //利率
            assertEquals(String.valueOf(0.08D), l_response.bondDomesticApplyProductList[0].coupon);
            //(利率(課税後))
            assertEquals(WEB3StringTypeUtility.formatNumber(0.04000), l_response.bondDomesticApplyProductList[0].rateAfterTax);
            //応募単価
            assertEquals("0", l_response.bondDomesticApplyProductList[0].applyPrice);
            //申込単位
            assertEquals(WEB3StringTypeUtility.formatNumber(100D), l_response.bondDomesticApplyProductList[0].applyUnit);
            //発行日
            assertEquals(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd").toLocaleString(), l_response.bondDomesticApplyProductList[0].issueDate.toLocaleString());
            //償還日
            assertEquals(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd").toLocaleString(), l_response.bondDomesticApplyProductList[0].maturityDate.toLocaleString());
            //利払日１
            assertEquals(null, l_response.bondDomesticApplyProductList[0].couponPaymentDate1);
            //利払日２
            assertEquals(null, l_response.bondDomesticApplyProductList[0].couponPaymentDate2);
            //年間利払回数
            assertEquals("2", l_response.bondDomesticApplyProductList[0].yearlyInterestPayments);
            //取扱開始日時
            assertEquals(WEB3DateUtility.getDate("20040101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].tradeStartDate.toLocaleString());
            //取扱終了日時
            assertEquals(WEB3DateUtility.getDate("20070101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].tradeEndDate.toLocaleString());
            //取引可能区分
            assertEquals("0", l_response.bondDomesticApplyProductList[0].tradingPossDiv);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0006()
    {
        final String STR_METHOD_NAME = " testExecute_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = "23";
        l_request.pageSize = "10";
        l_request.bondDiv = "0";
        WEB3BondDomesticApplyProductListResponse l_response = null;

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context1 = new WEB3GentradeTradingClendarContext();
        l_context1 = l_context;
        l_context1.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context1);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
            new Class[] {String.class},
            "0");
        
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("99980101", "yyyyMMdd"));
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20100101", "yyyyMMdd"));
            l_bondProductParams.setProductName("あああ");
            l_bondProductParams.setTradeType("3");
            TestDBUtility.deleteAll(l_bondProductParams.getRowType());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            TestDBUtility.deleteAll(l_taxRateTableParams.getRowType());
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);

            TestDBUtility.deleteAll(BondBranchConditionRow.TYPE);
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(33381L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("0");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = this.getBondBranchRecruitLimitRow();
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            l_response = (WEB3BondDomesticApplyProductListResponse)l_impl.execute(l_request);
            
            assertEquals(1, l_response.bondDomesticApplyProductList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            
            //銘柄ID
            assertEquals(String.valueOf(3304140763000L), l_response.bondDomesticApplyProductList[0].productId);
            //銘柄名
            assertEquals("あああ", l_response.bondDomesticApplyProductList[0].productName);
            //応募開始日
            assertEquals(WEB3DateUtility.getDate("20040101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].recruitStartDate.toLocaleString());
            //応募終了日
            assertEquals(WEB3DateUtility.getDate("20100101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].recruitEndDate.toLocaleString());
            //利率
            assertEquals(String.valueOf(0.08D), l_response.bondDomesticApplyProductList[0].coupon);
            //(利率(課税後))
            assertEquals(WEB3StringTypeUtility.formatNumber(0.04000), l_response.bondDomesticApplyProductList[0].rateAfterTax);
            //応募単価
            assertEquals("0", l_response.bondDomesticApplyProductList[0].applyPrice);
            //申込単位
            assertEquals(WEB3StringTypeUtility.formatNumber(100D), l_response.bondDomesticApplyProductList[0].applyUnit);
            //発行日
            assertEquals(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd").toLocaleString(), l_response.bondDomesticApplyProductList[0].issueDate.toLocaleString());
            //償還日
            assertEquals(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd").toLocaleString(), l_response.bondDomesticApplyProductList[0].maturityDate.toLocaleString());
            //利払日１
            assertEquals(null, l_response.bondDomesticApplyProductList[0].couponPaymentDate1);
            //利払日２
            assertEquals(null, l_response.bondDomesticApplyProductList[0].couponPaymentDate2);
            //年間利払回数
            assertEquals("2", l_response.bondDomesticApplyProductList[0].yearlyInterestPayments);
            //取扱開始日時
            assertEquals(WEB3DateUtility.getDate("20040101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].tradeStartDate.toLocaleString());
            //取扱終了日時
            assertEquals(WEB3DateUtility.getDate("20100101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].tradeEndDate.toLocaleString());
            //取引可能区分
            assertEquals("1", l_response.bondDomesticApplyProductList[0].tradingPossDiv);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0007()
    {
        final String STR_METHOD_NAME = " testExecute_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = "23";
        l_request.pageSize = "10";
        l_request.bondDiv = "0";
        WEB3BondDomesticApplyProductListResponse l_response = null;

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context1 = new WEB3GentradeTradingClendarContext();
        l_context1 = l_context;
        l_context1.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context1);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
            new Class[] {String.class},
            "1");
        
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("99980101", "yyyyMMdd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("99980101", "yyyyMMdd"));
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20100101", "yyyyMMdd"));
            l_bondProductParams.setProductName("あああ");
            l_bondProductParams.setTradeType("3");
            TestDBUtility.deleteAll(l_bondProductParams.getRowType());
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            TestDBUtility.deleteAll(l_taxRateTableParams.getRowType());
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);

            TestDBUtility.deleteAll(BondBranchConditionRow.TYPE);
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(33381L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("0");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);
            
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
//            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = this.getBondBranchRecruitLimitRow();
//            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);
            
            l_response = (WEB3BondDomesticApplyProductListResponse)l_impl.execute(l_request);

            
            assertEquals(1, l_response.bondDomesticApplyProductList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            
            //銘柄ID
            assertEquals(String.valueOf(3304140763000L), l_response.bondDomesticApplyProductList[0].productId);
            //銘柄名
            assertEquals("あああ", l_response.bondDomesticApplyProductList[0].productName);
            //応募開始日
            assertEquals(WEB3DateUtility.getDate("20040101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].recruitStartDate.toLocaleString());
            //応募終了日
            assertEquals(WEB3DateUtility.getDate("20100101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].recruitEndDate.toLocaleString());
            //利率
            assertEquals(String.valueOf(0.08D), l_response.bondDomesticApplyProductList[0].coupon);
            //(利率(課税後))
            assertEquals(WEB3StringTypeUtility.formatNumber(0.04000), l_response.bondDomesticApplyProductList[0].rateAfterTax);
            //応募単価
            assertEquals("0", l_response.bondDomesticApplyProductList[0].applyPrice);
            //申込単位
            assertEquals(WEB3StringTypeUtility.formatNumber(100D), l_response.bondDomesticApplyProductList[0].applyUnit);
            //発行日
            assertEquals(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd").toLocaleString(), l_response.bondDomesticApplyProductList[0].issueDate.toLocaleString());
            //償還日
            assertEquals(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd").toLocaleString(), l_response.bondDomesticApplyProductList[0].maturityDate.toLocaleString());
            //利払日１
            assertEquals(null, l_response.bondDomesticApplyProductList[0].couponPaymentDate1);
            //利払日２
            assertEquals(null, l_response.bondDomesticApplyProductList[0].couponPaymentDate2);
            //年間利払回数
            assertEquals("2", l_response.bondDomesticApplyProductList[0].yearlyInterestPayments);
            //取扱開始日時
            assertEquals(WEB3DateUtility.getDate("20060101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].tradeStartDate.toLocaleString());
            //取扱終了日時
            assertEquals(WEB3DateUtility.getDate("99980101", "yyyyMMdd").toLocaleString(), l_response.bondDomesticApplyProductList[0].tradeEndDate.toLocaleString());
            //取引可能区分
            assertEquals("2", l_response.bondDomesticApplyProductList[0].tradingPossDiv);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0008()
    {
        final String STR_METHOD_NAME = " testExecute_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
        WEB3BondDomesticApplyProductListRequest l_request = new WEB3BondDomesticApplyProductListRequest();
        l_request.pageIndex = "23";
        l_request.pageSize = "10";
        l_request.bondDiv = "0";
        WEB3BondDomesticApplyProductListResponse l_response = null;

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context1 = new WEB3GentradeTradingClendarContext();
        l_context1 = l_context;
        l_context1.setOrderAcceptTransaction("07");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context1);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
            new Class[] {String.class},
            "11");

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("99980101", "yyyyMMdd"));
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            l_bondProductParams.setProductIssueCode("2");
            BondProductParams l_bondProductParams1 = TestDBUtility.getBondProductRow();
            l_bondProductParams1.setProductId(3304140563001L);
            l_bondProductParams1.setInstitutionCode("0D");
            l_bondProductParams1.setHostRecruitStartDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams1.setHostRecruitEndDate(WEB3DateUtility.getDate("99980101", "yyyyMMdd"));
            l_bondProductParams1.setBondType(BondTypeEnum.DOMESTIC_BOND);
            l_bondProductParams1.setTradeHandleDiv("2");
            l_bondProductParams1.setTradeEndDate(WEB3DateUtility.getDate("20060101", "yyyyMMdd"));
            l_bondProductParams1.setProductIssueCode("2");
            l_bondProductParams1.setProductCode("A01010725");
            TestDBUtility.insertWithDel(l_bondProductParams);
            TestDBUtility.insertWithDel(l_bondProductParams1);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(3304140563001L);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_productParams1);

            l_response = (WEB3BondDomesticApplyProductListResponse)l_impl.execute(l_request);

            assertEquals(2, l_response.bondDomesticApplyProductList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("2", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0009()
    {
        final String STR_METHOD_NAME = " testExecute_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3BondDomesticApplyProductListServiceImpl l_impl = new WEB3BondDomesticApplyProductListServiceImpl();
            WEB3AdminBondOrderReceiveHistoryRequest l_request = new WEB3AdminBondOrderReceiveHistoryRequest();

            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private TaxRateTableParams getTaxRateTableRow()
    {
        TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
        //証券会社コード
        l_taxRateTableParams.setInstitutionCode("0D");
        //税種類
        l_taxRateTableParams.setTaxType("70");
        //適用開始年月日
        l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20050101", "yyyyMMdd"));
        //適用終了年月日
        l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("30010101", "yyyyMMdd"));
        //税率
        l_taxRateTableParams.setTaxRate(50L);
        //作成日付
        l_taxRateTableParams.setCreatedTimestamp(new Date());
        //更新日付
        l_taxRateTableParams.setLastUpdatedTimestamp(new Date());
        
        return l_taxRateTableParams;
    }
    
    private BondBranchRecruitLimitParams getBondBranchRecruitLimitRow()
    {
        BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
        l_bondBranchRecruitLimitParams.setProductId(3304140763000L);
        l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
        l_bondBranchRecruitLimitParams.setBranchCode("---");
        l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
        l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
        l_bondBranchRecruitLimitParams.setRecruitLimit(6.6D);
        
        return l_bondBranchRecruitLimitParams;
    }
}
@
