head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderDLServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AdminEquityForcedSettleOrderDLServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/15 トウ鋒鋼 (中訊) 新規作成
Revesion History : 姜丹(中訊) 仕様変更モデルNo.211
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqRow;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderDLServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLServiceImplTest.class);

    WEB3AdminEquityForcedSettleOrderDLServiceImpl l_serviceImpl = null;

    public WEB3AdminEquityForcedSettleOrderDLServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
        l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImplForTest();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
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
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminForcedSettleDownloadInputRequest l_request =
                new WEB3AdminForcedSettleDownloadInputRequest();
            WEB3AdminForcedSettleDownloadInputResponse l_response =
                (WEB3AdminForcedSettleDownloadInputResponse)l_serviceImpl.execute(l_request);
            assertEquals("1", l_response.pageIndex);
            assertEquals("20", l_response.totalPages);
            assertEquals("50", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminForcedSettleDownloadRequest l_request =
                new WEB3AdminForcedSettleDownloadRequest();
            WEB3AdminForcedSettleDownloadResponse l_response =
                (WEB3AdminForcedSettleDownloadResponse)l_serviceImpl.execute(l_request);
            assertEquals("2", l_response.pageIndex);
            assertEquals("10", l_response.totalPages);
            assertEquals("100", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminForcedSettleReferenceRequest l_request =
                new WEB3AdminForcedSettleReferenceRequest();
            l_serviceImpl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
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

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl.getInputScreen(WEB3AdminForcedSettleDownloadInputRequest)'
     */
    public void testGetInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadInputRequest l_request =
                new WEB3AdminForcedSettleDownloadInputRequest();
            l_serviceImpl.getInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadInputRequest l_request =
                new WEB3AdminForcedSettleDownloadInputRequest();
            
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = new Date();
            l_request.errorReason = "9001";
            l_request.approveType = "1";
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);

            l_serviceImpl.getInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
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
     * 
     *
     */
    public void testGetInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadInputRequest l_request =
                new WEB3AdminForcedSettleDownloadInputRequest();
            
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = new Date();
            l_request.errorReason = "9001";
            l_request.approveType = "1";
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0108");
            
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);

            l_serviceImpl.getInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02985, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadInputRequest l_request =
                new WEB3AdminForcedSettleDownloadInputRequest();
            
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = WEB3DateUtility.getDate("20080216", "yyyyMMdd");
            l_request.errorReason = "9001";
            l_request.approveType = null;
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0108");
            
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ForcedSettleOrderInqRow.TYPE);
            ForcedSettleOrderInqParams l_forcedSettleOrderInqParams = TestDBUtility.getForcedSettleOrderInqRow();
            l_forcedSettleOrderInqParams.setApproveStatusType("9");
            l_forcedSettleOrderInqParams.setMarketId(3303);
            l_forcedSettleOrderInqParams.setTaxType(2);
            l_forcedSettleOrderInqParams.setAccountCode("123456");
            l_forcedSettleOrderInqParams.setApproverCode("123");
            l_forcedSettleOrderInqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080215", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setApproveTimestamp(WEB3DateUtility.getDate("20080215", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setForcedSettleReasonType("9");
            l_forcedSettleOrderInqParams.setCloseDate(WEB3DateUtility.getDate("20080216", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setErrorReasonCode("9001");
            l_forcedSettleOrderInqParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_forcedSettleOrderInqParams);

            WEB3AdminForcedSettleDownloadInputResponse l_response = l_serviceImpl.getInputScreen(l_request);
            
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl.getDownloadFile(WEB3AdminForcedSettleDownloadRequest)'
     */
    public void testGetDownloadFile_0001()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadRequest l_request = new WEB3AdminForcedSettleDownloadRequest();
            l_serviceImpl.getDownloadFile(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetDownloadFile_0002()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadRequest l_request = new WEB3AdminForcedSettleDownloadRequest();
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = new Date();
            l_request.errorReason = "9001";
            l_request.approveType = "1";
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_serviceImpl.getDownloadFile(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetDownloadFile_0003()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadRequest l_request = new WEB3AdminForcedSettleDownloadRequest();
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = new Date();
            l_request.errorReason = "9001";
            l_request.approveType = "1";
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0108");
            
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            l_serviceImpl.getDownloadFile(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02985, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    public void testGetDownloadFile_0004()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadRequest l_request = new WEB3AdminForcedSettleDownloadRequest();
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = WEB3DateUtility.getDate("20080216", "yyyyMMdd");
            l_request.errorReason = "9001";
            l_request.approveType = null;
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0108");
            
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ForcedSettleOrderInqRow.TYPE);
            ForcedSettleOrderInqParams l_forcedSettleOrderInqParams = TestDBUtility.getForcedSettleOrderInqRow();
            l_forcedSettleOrderInqParams.setApproveStatusType("9");
            l_forcedSettleOrderInqParams.setMarketId(3303);
            l_forcedSettleOrderInqParams.setTaxType(2);
            l_forcedSettleOrderInqParams.setProductCode("12235");
            l_forcedSettleOrderInqParams.setAccountCode("123456");
            l_forcedSettleOrderInqParams.setApproverCode("123");
            l_forcedSettleOrderInqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080215", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setApproveTimestamp(WEB3DateUtility.getDate("20080215", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setForcedSettleReasonType("9");
            l_forcedSettleOrderInqParams.setCloseDate(WEB3DateUtility.getDate("20080216", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setOpenDate(WEB3DateUtility.getDate("20080217", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setErrorReasonCode("9001");
            l_forcedSettleOrderInqParams.setContractQuantity(1500);
            l_forcedSettleOrderInqParams.setContractPrice(569);
            l_forcedSettleOrderInqParams.setAdditionalMarginDate(WEB3DateUtility.getDate("20080218", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setMarginMaintenanceRate(123);
            TestDBUtility.insertWithDel(l_forcedSettleOrderInqParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setContractAmount
            l_eqtypeProductParams.setStandardName("23123");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            MainAccountParams l_mainaccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainaccountParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);

            WEB3AdminForcedSettleDownloadResponse l_response = l_serviceImpl.getDownloadFile(l_request);
            String[] l_strDownloadFiles = l_response.downloadFile;
            assertEquals("" + WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss"),l_strDownloadFiles[0]);
            assertEquals("部店,顧客,顧客名,強制決済理由,市場,銘柄,銘柄名,口座,建区分,弁済,建日,決済期日,建株数,建単価,建代金,保証金率 (%),追証発生日,経過日数(日),作成日時,処理日時,承認状態,承認者",
                    l_strDownloadFiles[1]);
            assertEquals("381,123456,内藤　@四郎,手動強制決済,シンガポール,1223,23123,特定,新規買,一般信用,2008/2/17,2008/2/16,1500,569,80779973486,123,2008/2/18,,2008/2/15 0:00,2008/2/15 0:00,手動承認済,123",
                    l_strDownloadFiles[2]);
//            assertEquals();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate部店権限(部店コード : String[])
    public void testGetDownloadFile_0005()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_serviceImpl = new WEB3AdminEquityForcedSettleOrderDLServiceImpl();
            
            WEB3AdminForcedSettleDownloadRequest l_request = new WEB3AdminForcedSettleDownloadRequest();
            l_request.branchCodeList = new String[1];
            l_request.branchCodeList[0] = "381";
            l_request.accountCode = "123456";
            l_request.productCode = null;
            l_request.approveState = "9";
            l_request.checker ="123";
            l_request.createDateFrom = "200706010000";
            l_request.createDateTo = "200803010000";
            l_request.approveDateFrom = "200706010000";
            l_request.approveDateTo = "200803010000";
            l_request.forcedSettleReason = "9";
            l_request.closeDate = WEB3DateUtility.getDate("20080216", "yyyyMMdd");
            l_request.errorReason = "9001";
            l_request.approveType = null;
            l_request.taxType = "1";
            l_request.marketCode = "6";
            
            WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
            l_sort.keyItem ="contractExecPrice";
            l_sort.ascDesc ="D";
            WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
            l_request.sortKeys = l_message;
            l_request.pageIndex = "12";
            l_request.pageSize = "12";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("111");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0108");
            
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            LoginInfo l_loginInfo = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(33381330003L));
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ForcedSettleOrderInqRow.TYPE);
            ForcedSettleOrderInqParams l_forcedSettleOrderInqParams = TestDBUtility.getForcedSettleOrderInqRow();
            l_forcedSettleOrderInqParams.setApproveStatusType("9");
            l_forcedSettleOrderInqParams.setMarketId(3303);
            l_forcedSettleOrderInqParams.setTaxType(2);
            l_forcedSettleOrderInqParams.setProductCode("12235");
            l_forcedSettleOrderInqParams.setAccountCode("123456");
            l_forcedSettleOrderInqParams.setApproverCode("123");
            l_forcedSettleOrderInqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080215", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setApproveTimestamp(WEB3DateUtility.getDate("20080215", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setForcedSettleReasonType("9");
            l_forcedSettleOrderInqParams.setCloseDate(WEB3DateUtility.getDate("20080216", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setOpenDate(WEB3DateUtility.getDate("20080217", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setErrorReasonCode("9001");
            l_forcedSettleOrderInqParams.setContractQuantity(1500);
            l_forcedSettleOrderInqParams.setContractPrice(569);
            l_forcedSettleOrderInqParams.setAdditionalMarginDate(WEB3DateUtility.getDate("20080218", "yyyyMMdd"));
            l_forcedSettleOrderInqParams.setMarginMaintenanceRate(123);
            TestDBUtility.insertWithDel(l_forcedSettleOrderInqParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setContractAmount
            l_eqtypeProductParams.setStandardName("23123");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            MainAccountParams l_mainaccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainaccountParams);

            l_serviceImpl.getDownloadFile(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    private class WEB3AdminEquityForcedSettleOrderDLServiceImplForTest
        extends WEB3AdminEquityForcedSettleOrderDLServiceImpl
    {
        protected WEB3AdminForcedSettleDownloadInputResponse getInputScreen(
            WEB3AdminForcedSettleDownloadInputRequest l_request) throws WEB3BaseException
        {
            WEB3AdminForcedSettleDownloadInputResponse l_response =
                new WEB3AdminForcedSettleDownloadInputResponse();
            l_response.pageIndex = "1";
            l_response.totalPages = "20";
            l_response.totalRecords = "50";
            return l_response;
        }
        
        protected WEB3AdminForcedSettleDownloadResponse getDownloadFile(
            WEB3AdminForcedSettleDownloadRequest l_request) throws WEB3BaseException
        {
            WEB3AdminForcedSettleDownloadResponse l_response =
                new WEB3AdminForcedSettleDownloadResponse();
            l_response.pageIndex = "2";
            l_response.totalPages = "10";
            l_response.totalRecords = "100";
            return l_response;
        }
    }
}
@
