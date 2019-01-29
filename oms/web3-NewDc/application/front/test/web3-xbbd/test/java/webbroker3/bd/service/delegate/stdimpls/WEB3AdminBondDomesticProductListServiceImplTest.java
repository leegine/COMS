head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import test.util.TestDBUtility;

import webbroker3.bd.define.WEB3BondSortKeyDef;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchConditionUnit;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCommonRequest;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者国内債券銘柄一覧サービスImplテスト)<BR>
 * 管理者国内債券銘柄一覧サービスImpl<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListServiceImplTest.class);
    WEB3AdminBondDomesticProductListServiceImpl l_impl =
        new WEB3AdminBondDomesticProductListServiceImpl();
    public WEB3AdminBondDomesticProductListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testExecute_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GenRequest l_request = null;
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0002()
    {
        final String STR_METHOD_NAME = "testExecute_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListServiceImplForTest l_impl1 =
                new WEB3AdminBondDomesticProductListServiceImplForTest();
            WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListSearchDisplayRequest();
            WEB3AdminBondDomesticProductListSearchDisplayResponse l_response =
                (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_impl1.execute(l_request);
            assertEquals("11", l_response.bondTypeList[0]);
            assertEquals("0", l_response.tradeHandleDivList[0]);
            assertEquals("2", l_response.tradeHandleDivList[1]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0003()
    {
        final String STR_METHOD_NAME = "testExecute_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListServiceImplForTest l_impl1 =
                new WEB3AdminBondDomesticProductListServiceImplForTest();
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            WEB3AdminBondDomesticProductListDisplayResponse l_response =
                (WEB3AdminBondDomesticProductListDisplayResponse)l_impl1.execute(l_request);
            assertEquals("10", l_response.pageIndex);
            assertEquals("100", l_response.totalPages);

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0004()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductRegistCommonRequest l_request =
                new WEB3AdminBondDomesticProductRegistCommonRequest();
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80018);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreenDisplay()
    {
        final String STR_METHOD_NAME = "testGetSearchScreenDisplay()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1101");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListSearchDisplayRequest();
            WEB3AdminBondDomesticProductListSearchDisplayResponse l_response =
                l_impl.getSearchScreenDisplay(l_request);
            assertEquals("11", l_response.bondTypeList[0]);
            assertEquals("12", l_response.bondTypeList[1]);
            assertEquals("0", l_response.tradeHandleDivList[0]);
            assertEquals("2", l_response.tradeHandleDivList[1]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreenDisplay_case0001()
    {
        final String STR_METHOD_NAME = "testGetSearchScreenDisplay_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListSearchDisplayRequest();
                l_impl.getSearchScreenDisplay(l_request);
                fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01056);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductListScreenDisplay_case0001()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1101");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            l_bondProductParams.setHostProductCode("01");
            l_bondProductParams.setBuyPrice(123);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            l_request.sortKeys = new WEB3BondSortKey[1];
            l_request.sortKeys[0] = new WEB3BondSortKey();
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.DESC;
            l_request.sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_request.pageIndex = "10";
            l_request.pageSize = "20";
            l_request.searchCondition =
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            l_request.searchCondition.productCode = "01";
            WEB3AdminBondDomesticProductListDisplayResponse l_response =
                l_impl.getProductListScreenDisplay(l_request);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1",  l_response.totalPages);
            assertEquals("1",l_response.totalRecords);
            assertEquals("01",l_response.productRefInfoList[0].productCode);
            assertEquals("123", l_response.productRefInfoList[0].applyPrice);
            assertEquals("0.08",l_response.productRefInfoList[0].coupon);
            assertEquals(null,l_response.productRefInfoList[0].couponPaymentDate1);
            assertEquals(null,l_response.productRefInfoList[0].couponPaymentDate2);
            assertEquals("3304140763000",l_response.productRefInfoList[0].productId);
            assertEquals("101",l_response.productRefInfoList[0].productIssueCode);
            assertEquals(null,l_response.productRefInfoList[0].productNameHost);
            assertEquals(null,l_response.productRefInfoList[0].productNameWEB3);
            assertEquals("2",l_response.productRefInfoList[0].tradeHandleDiv);
            assertEquals("2",l_response.productRefInfoList[0].yearlyInterestPayments);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    public void testGetProductListScreenDisplay_case0002()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1101");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            l_bondProductParams.setHostProductCode("01");
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            BondProductParams l_bondProductParams1 = TestDBUtility.getBondProductRow();
            l_bondProductParams1.setInstitutionCode("0D");
            l_bondProductParams1.setProductId(123);
            l_bondProductParams1.setBondType(BondTypeEnum.CORPORATE_BOND);
            l_bondProductParams1.setHostProductCode("01");
            l_bondProductParams1.setProductIssueCode("123");
            TestDBUtility.insertWithDel(l_bondProductParams1);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            l_request.sortKeys = new WEB3BondSortKey[1];
            l_request.sortKeys[0] = new WEB3BondSortKey();
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.DESC;
            l_request.sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_request.pageIndex = "10";
            l_request.pageSize = "20";
            l_request.searchCondition =
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            l_request.searchCondition.productCode = "01";
            WEB3AdminBondDomesticProductListDisplayResponse l_response =
                l_impl.getProductListScreenDisplay(l_request);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1",  l_response.totalPages);
            assertEquals("2",l_response.totalRecords);
            assertEquals("01",l_response.productRefInfoList[0].productCode);
            assertEquals(null, l_response.productRefInfoList[0].applyPrice);
            assertEquals("0.08",l_response.productRefInfoList[0].coupon);
            assertEquals(null,l_response.productRefInfoList[0].couponPaymentDate1);
            assertEquals(null,l_response.productRefInfoList[0].couponPaymentDate2);
            assertEquals("3304140763000",l_response.productRefInfoList[0].productId);
            assertEquals("101",l_response.productRefInfoList[0].productIssueCode);
            assertEquals(null,l_response.productRefInfoList[0].productNameHost);
            assertEquals(null,l_response.productRefInfoList[0].productNameWEB3);
            assertEquals("2",l_response.productRefInfoList[0].tradeHandleDiv);
            assertEquals("2",l_response.productRefInfoList[0].yearlyInterestPayments);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetProductListScreenDisplay_case0003()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            l_impl.getProductListScreenDisplay(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorMessage(), "ソートキーが未指定です。");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetProductListScreenDisplay_case0004()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            l_request.sortKeys = new WEB3BondSortKey[1];
            l_request.sortKeys[0] = new WEB3BondSortKey();
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.DESC;
            l_request.sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_request.pageIndex = "10";
            l_request.pageSize = "20";
            l_request.searchCondition =
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            l_request.searchCondition.productCode = "01";
            l_impl.getProductListScreenDisplay(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01056);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCreateQueryString_case0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListSearchConditionUnit l_unit = 
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            l_unit.bondType = "1";
            l_unit.productCode = "01";
            l_unit.productIssueCode = "02";
            l_unit.productNameHost = "03";
            l_unit.productNameWEB3 = "04";
            l_unit.issueDate = WEB3DateUtility.getDate("20070512", "yyyyMMdd");
            l_unit.maturityDate = WEB3DateUtility.getDate("20070512", "yyyyMMdd");
            l_unit.interestPaymentDay = "05";
            l_unit.tradeHandleDiv = "06";
            
            String l_strQueryString = l_impl.createQueryString(l_unit);
            assertEquals(" bond_type != ?  and bond_type = ?  and host_product_code = ?  " +
                    "and host_product_issue_code = ?  and host_product_name_1 like ?  " +
                    "and product_name like ?  and issue_date = ?  and maturity_date = ?  " +
                    "and ( interest_payment_day_1st = ?　@or interest_payment_day_2nd = ? )  " +
                    "and trade_handle_div = ? " ,l_strQueryString);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_case0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListSearchConditionUnit l_unit = 
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            
            String l_strQueryString = l_impl.createQueryString(l_unit);
            assertEquals(" bond_type != ? "  ,l_strQueryString);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_case0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListSearchConditionUnit l_unit = 
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            l_unit.bondType = "1";
            l_unit.productCode = "1";
            l_unit.productIssueCode = "02";
            l_unit.productNameHost = "112210321212";
            l_unit.productNameWEB3 = "212121042121";
            l_unit.issueDate = WEB3DateUtility.getDate("20070512", "yyyyMMdd");
            l_unit.maturityDate = WEB3DateUtility.getDate("20070512", "yyyyMMdd");
            l_unit.interestPaymentDay = "05";
            l_unit.tradeHandleDiv = "06";
            
            Object[] l_obj = l_impl.createQueryDataContainer(l_unit);
            assertEquals(new Integer(4),  l_obj[0]);
            assertEquals(new Integer(1),  l_obj[1]);
            assertEquals("1",  l_obj[2]);
            assertEquals("02",  l_obj[3]);
            assertEquals("%112210321212%",  l_obj[4]);
            assertEquals("%212121042121%",  l_obj[5]);
            assertEquals(WEB3DateUtility.getDate("20070512", "yyyyMMdd"),  l_obj[6]);
            assertEquals(WEB3DateUtility.getDate("20070512", "yyyyMMdd"),  l_obj[7]);
            assertEquals("05",  l_obj[8]);
            assertEquals("05",  l_obj[9]);
            assertEquals("06",  l_obj[10]);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
  
    public void testCreateQueryDataContainer_case0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListSearchConditionUnit l_unit = 
                new WEB3AdminBondDomesticProductListSearchConditionUnit();
            
            Object[] l_obj = l_impl.createQueryDataContainer(l_unit);
            assertEquals(new Integer(4),  l_obj[0]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortCond_case0001()
    {
        final String STR_METHOD_NAME = "testcreateSortCond_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondSortKey[] l_sortKeys = null;
            l_impl.createSortCond(l_sortKeys);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorMessage(), "パラメータ値不正。");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortCond_case0002()
    {
        final String STR_METHOD_NAME = "testcreateSortCond_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondSortKey[] l_sortKeys = new WEB3BondSortKey[]{};
            l_impl.createSortCond(l_sortKeys);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorMessage(), "パラメータ値不正。");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortCond_case0003()
    {
        final String STR_METHOD_NAME = "testcreateSortCond_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondSortKey[] l_sortKeys = new WEB3BondSortKey[5];
            l_sortKeys[0] = new WEB3BondSortKey();
            l_sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_sortKeys[1] = new WEB3BondSortKey();
            l_sortKeys[1].keyItem = WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
            l_sortKeys[2] = new WEB3BondSortKey();
            l_sortKeys[2].keyItem = WEB3BondSortKeyDef.ISSUE_DATE;
            l_sortKeys[2].ascDesc = WEB3AscDescDef.ASC;
            l_sortKeys[3] = new WEB3BondSortKey();
            l_sortKeys[3].keyItem = WEB3BondSortKeyDef.MATURITY_DATE;
            l_sortKeys[3].ascDesc = WEB3AscDescDef.ASC;
            l_sortKeys[4] = new WEB3BondSortKey();
            l_sortKeys[4].keyItem = WEB3BondSortKeyDef.COUPON;
            l_sortKeys[4].ascDesc = WEB3AscDescDef.ASC;
            String l_strSortCond = l_impl.createSortCond(l_sortKeys);
            assertEquals(" host_product_code  asc,  host_product_issue_code  asc,  issue_date  asc,  maturity_date  asc,  coupon  asc ", l_strSortCond);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortCond_case0004()
    {
        final String STR_METHOD_NAME = "testcreateSortCond_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondSortKey[] l_sortKeys = new WEB3BondSortKey[5];
            l_sortKeys[0] = new WEB3BondSortKey();
            l_sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[1] = new WEB3BondSortKey();
            l_sortKeys[1].keyItem = WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[2] = new WEB3BondSortKey();
            l_sortKeys[2].keyItem = WEB3BondSortKeyDef.ISSUE_DATE;
            l_sortKeys[2].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[3] = new WEB3BondSortKey();
            l_sortKeys[3].keyItem = WEB3BondSortKeyDef.MATURITY_DATE;
            l_sortKeys[3].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[4] = new WEB3BondSortKey();
            l_sortKeys[4].keyItem = WEB3BondSortKeyDef.COUPON;
            l_sortKeys[4].ascDesc = WEB3AscDescDef.DESC;
            String l_strSortCond = l_impl.createSortCond(l_sortKeys);
            assertEquals(" host_product_code  desc,  host_product_issue_code  desc,  issue_date  desc,  maturity_date  desc,  coupon  desc ", l_strSortCond);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortCond_case0005()
    {
        final String STR_METHOD_NAME = "testcreateSortCond_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3BondSortKey[] l_sortKeys = new WEB3BondSortKey[1];
            l_sortKeys[0] = new WEB3BondSortKey();
            l_sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.DESC;
            String l_strSortCond = l_impl.createSortCond(l_sortKeys);
            assertEquals(" host_product_code  desc ", l_strSortCond);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AdminBondDomesticProductListServiceImplForTest extends WEB3AdminBondDomesticProductListServiceImpl
    {
        protected WEB3AdminBondDomesticProductListSearchDisplayResponse getSearchScreenDisplay(
                WEB3AdminBondDomesticProductListSearchDisplayRequest l_request) throws WEB3BaseException
        {
            log.debug("----------------------get検索画面表示----------------------!");
            WEB3AdminBondDomesticProductListSearchDisplayResponse l_response =
                new WEB3AdminBondDomesticProductListSearchDisplayResponse();
            l_response.bondTypeList = new String[]{"11"};
            l_response.tradeHandleDivList = new String[]{"0", "2"};
            return l_response;
        
        }
        
        
        protected WEB3AdminBondDomesticProductListDisplayResponse getProductListScreenDisplay(
                WEB3AdminBondDomesticProductListDisplayRequest l_request) throws WEB3BaseException
        {
            log.debug("------------------get銘柄一覧画面表示--------------------");
            WEB3AdminBondDomesticProductListDisplayResponse l_response =
                new WEB3AdminBondDomesticProductListDisplayResponse();
            l_response.pageIndex = "10";
            l_response.totalPages = "100";
            
            return l_response;
        }
        
    }
    
    public void testGetProductListScreenDisplay()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setBondType(BondTypeEnum.CORPORATE_BOND);
            l_bondProductParams.setHostProductCode("01");
            l_bondProductParams.setBuyPrice(123);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            WEB3AdminBondDomesticProductListSearchConditionUnit l_unit = 
                new WEB3AdminBondDomesticProductListSearchConditionUnit();

            l_unit.productCode = "01";
            l_unit.bondType = "1";
            l_unit.productCode = "1";
            l_unit.productIssueCode = "02";
            l_unit.productNameHost = "112210321212";
            l_unit.productNameWEB3 = "212121042121";
            l_unit.issueDate = WEB3DateUtility.getDate("20070512", "yyyyMMdd");
            l_unit.maturityDate = WEB3DateUtility.getDate("20070512", "yyyyMMdd");
            l_unit.interestPaymentDay = "05";
            l_unit.tradeHandleDiv = "06";
            WEB3BondSortKey[] l_sortKeys = new WEB3BondSortKey[5];
            l_sortKeys[0] = new WEB3BondSortKey();
            l_sortKeys[0].keyItem = WEB3BondSortKeyDef.PRODUCT_CODE;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[1] = new WEB3BondSortKey();
            l_sortKeys[1].keyItem = WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[2] = new WEB3BondSortKey();
            l_sortKeys[2].keyItem = WEB3BondSortKeyDef.ISSUE_DATE;
            l_sortKeys[2].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[3] = new WEB3BondSortKey();
            l_sortKeys[3].keyItem = WEB3BondSortKeyDef.MATURITY_DATE;
            l_sortKeys[3].ascDesc = WEB3AscDescDef.DESC;
            l_sortKeys[4] = new WEB3BondSortKey();
            l_sortKeys[4].keyItem = WEB3BondSortKeyDef.COUPON;
            l_sortKeys[4].ascDesc = WEB3AscDescDef.DESC;
            String l_strQuery = l_impl.createQueryString(l_unit);
            Object[] l_obj = l_impl.createQueryDataContainer(l_unit);
            String l_str = l_impl.createSortCond(l_sortKeys);
            
            QueryProcessor l_queryProcessors = Processors.getDefaultProcessor();
            List l_lisCustodianList = l_queryProcessors.doFindAllQuery(
                    BondProductRow.TYPE,
                    l_strQuery,
                    l_str,
                    null,
                    l_obj);
            assertEquals(0 , l_lisCustodianList.size());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
}
@
