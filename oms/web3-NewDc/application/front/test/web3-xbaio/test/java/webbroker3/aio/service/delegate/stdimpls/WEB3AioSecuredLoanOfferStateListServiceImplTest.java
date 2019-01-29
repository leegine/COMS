head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSecuredLoanOfferStateListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 担保ローン申込状況サービス一覧Implのテストクラス(WEB3AioSecuredLoanOfferStateListServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListResponse;
import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author 何文敏
 *
 */
public class WEB3AioSecuredLoanOfferStateListServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanOfferStateListServiceImplTest.class);
    WEB3AioSecuredLoanOfferStateListServiceImpl l_impl = new WEB3AioSecuredLoanOfferStateListServiceImpl();
    public WEB3AioSecuredLoanOfferStateListServiceImplTest(String arg0)
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

    public void testExecute_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GenRequest l_request = null;
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals("パラメータNull出来ない。", l_ex.getErrorMessage());
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
            WEB3SLAccountOpenApplyRequest l_request =
                new WEB3SLAccountOpenApplyRequest();
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
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
            WEB3AioSecuredLoanOfferStateListServiceImplForTest l_implTest =
                new WEB3AioSecuredLoanOfferStateListServiceImplForTest();
            WEB3AdminSLAccountOpenApplyListRequest l_request =
                new WEB3AdminSLAccountOpenApplyListRequest();
            WEB3AdminSLAccountOpenApplyListResponse l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_implTest.execute(l_request);
            assertEquals("100", l_response.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(123);
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("0");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("101234567");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_request.accountCode = "123456";
            l_request.stockLoanAccount = "101234567";
            l_request.applyStatus = "0";
            l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.applyDateTo = WEB3DateUtility.getDate("20070812","yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.executeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.cancelDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.closeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey sortkey = new WEB3SLSortKey();
            sortkey.keyItem = "accountCode";
            sortkey.ascDesc = "D";
            l_request.sortKeys[0] = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AdminSLAccountOpenApplyListResponse l_response = l_impl.getListScreen(l_request);
            assertEquals(l_response.totalPages, "1");
            assertEquals(l_response.totalRecords, "1");
            assertEquals(l_response.pageIndex, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].branchCode, "381");
            assertEquals(l_response.accountOpenApplyDetailList[0].accountCode, "123456");
            assertEquals(l_response.accountOpenApplyDetailList[0].stockLoanAccount, "101234567");
            assertEquals(l_response.accountOpenApplyDetailList[0].applyStatus, "0");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].applyDateFrom,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].accountOpenDate,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].executeDate,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].cancelDate,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].closeDate, "yyyyMMdd"), "20070810");
            assertEquals(l_response.accountOpenApplyDetailList[0].yellowAccountLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].examinationLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].branchLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].mngLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].mngExpenseLockReasonFlag, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].mngDepositLockReasonFlag, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].mngCollateralLockReasonFlag, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].mngReceiptLockReasonFlag, "1");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(123);
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("0");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("1012302");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            StockSecuredLoanParams l_stockSecuredLoanParams1 = new StockSecuredLoanParams();
            l_stockSecuredLoanParams1.setAccountCode("123456");
            l_stockSecuredLoanParams1.setAccountId(123);
            l_stockSecuredLoanParams1.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setAccountOpenStatus("0");
            l_stockSecuredLoanParams1.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setBranchCode("381");
            l_stockSecuredLoanParams1.setStockLoanAccountCode("1012354");
            l_stockSecuredLoanParams1.setInstitutionCode("0D");
            l_stockSecuredLoanParams1.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams1.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams1.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams1.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams1);
            
            StockSecuredLoanParams l_stockSecuredLoanParams2 = new StockSecuredLoanParams();
            l_stockSecuredLoanParams2.setAccountCode("123456");
            l_stockSecuredLoanParams2.setAccountId(123);
            l_stockSecuredLoanParams2.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setAccountOpenStatus("0");
            l_stockSecuredLoanParams2.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setBranchCode("381");
            l_stockSecuredLoanParams2.setStockLoanAccountCode("1012345");
            l_stockSecuredLoanParams2.setInstitutionCode("0D");
            l_stockSecuredLoanParams2.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams2.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams2.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams2.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams2);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_request.accountCode = "123456";
            l_request.stockLoanAccount = "10123";
            l_request.applyStatus = "0";
            l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.applyDateTo = WEB3DateUtility.getDate("20070812","yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.executeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.cancelDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.closeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey sortkey = new WEB3SLSortKey();
            sortkey.keyItem = "accountCode";
            sortkey.ascDesc = "D";
            l_request.sortKeys[0] = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "3";
            WEB3AdminSLAccountOpenApplyListResponse l_response = l_impl.getListScreen(l_request);
            assertEquals(l_response.totalPages, "1");
            assertEquals(l_response.totalRecords, "3");
            assertEquals(l_response.pageIndex, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].branchCode, "381");
            assertEquals(l_response.accountOpenApplyDetailList[0].accountCode, "123456");
            assertEquals(l_response.accountOpenApplyDetailList[0].stockLoanAccount, "1012302");
            assertEquals(l_response.accountOpenApplyDetailList[0].applyStatus, "0");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].applyDateFrom,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].accountOpenDate,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].executeDate,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].cancelDate,"yyyyMMdd"), "20070810");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].closeDate, "yyyyMMdd"), "20070810");
            assertEquals(l_response.accountOpenApplyDetailList[0].yellowAccountLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].examinationLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].branchLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].mngLockDiv, null);
            assertEquals(l_response.accountOpenApplyDetailList[0].mngExpenseLockReasonFlag, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].mngDepositLockReasonFlag, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].mngCollateralLockReasonFlag, "1");
            assertEquals(l_response.accountOpenApplyDetailList[0].mngReceiptLockReasonFlag, "1");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0003()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(123);
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("0");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("1012302");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            StockSecuredLoanParams l_stockSecuredLoanParams1 = new StockSecuredLoanParams();
            l_stockSecuredLoanParams1.setAccountCode("123456");
            l_stockSecuredLoanParams1.setAccountId(123);
            l_stockSecuredLoanParams1.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setAccountOpenStatus("0");
            l_stockSecuredLoanParams1.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setBranchCode("381");
            l_stockSecuredLoanParams1.setStockLoanAccountCode("1012354");
            l_stockSecuredLoanParams1.setInstitutionCode("0D");
            l_stockSecuredLoanParams1.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams1.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams1.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams1.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams1.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams1);
            
            StockSecuredLoanParams l_stockSecuredLoanParams2 = new StockSecuredLoanParams();
            l_stockSecuredLoanParams2.setAccountCode("123456");
            l_stockSecuredLoanParams2.setAccountId(123);
            l_stockSecuredLoanParams2.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setAccountOpenStatus("0");
            l_stockSecuredLoanParams2.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setBranchCode("381");
            l_stockSecuredLoanParams2.setStockLoanAccountCode("1012345");
            l_stockSecuredLoanParams2.setInstitutionCode("0D");
            l_stockSecuredLoanParams2.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams2.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams2.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams2.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams2.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams2);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_request.accountCode = "123456";
            l_request.stockLoanAccount = "10123";
            l_request.applyStatus = "0";
            l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.applyDateTo = WEB3DateUtility.getDate("20070812","yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.executeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.cancelDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.closeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey sortkey = new WEB3SLSortKey();
            sortkey.keyItem = "accountCode";
            sortkey.ascDesc = "D";
            l_request.sortKeys[0] = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "2";
            l_impl.getListScreen(l_request);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0004()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(123);
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("0");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("1012302");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_request.accountCode = "123456";
            l_request.stockLoanAccount = "10123";
            l_request.applyStatus = "0";
            l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.applyDateTo = WEB3DateUtility.getDate("20070812","yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.executeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.cancelDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.closeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey sortkey = new WEB3SLSortKey();
            sortkey.keyItem = "accountCode";
            sortkey.ascDesc = "D";
            l_request.sortKeys[0] = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "2";
            l_impl.getListScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals("担保ローン申込顧客明細一覧が未指定です。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0005()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_impl.getListScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testGetListScreen_case0006()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_impl.getListScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0007()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_impl.getListScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0008()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(123);
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("0");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("200708101112","yyyyMMddHHmm"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("101234567");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("200708101112","yyyyMMddHHmm"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("200708101012","yyyyMMddHHmm"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(BooleanEnum.TRUE);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_request.accountCode = "123456";
            l_request.stockLoanAccount = "101234567";
            l_request.applyStatus = "0";
            l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.applyDateTo = WEB3DateUtility.getDate("20070812","yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.executeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.cancelDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.closeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey sortkey = new WEB3SLSortKey();
            sortkey.keyItem = "accountCode";
            sortkey.ascDesc = "D";
            l_request.sortKeys[0] = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AdminSLAccountOpenApplyListResponse l_response = l_impl.getListScreen(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].applyDateFrom,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].accountOpenDate,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].executeDate,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].cancelDate,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].closeDate, "yyyyMMddHHmm"), "200708100000");

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0009()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = new StockSecuredLoanParams();
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAccountId(123);
            l_stockSecuredLoanParams.setAccountOpenDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setAccountOpenStatus("0");
            l_stockSecuredLoanParams.setAppliDate(WEB3DateUtility.getDate("200708101112","yyyyMMddHHmm"));
            l_stockSecuredLoanParams.setBranchCode("381");
            l_stockSecuredLoanParams.setStockLoanAccountCode("101234567");
            l_stockSecuredLoanParams.setInstitutionCode("0D");
            l_stockSecuredLoanParams.setOrderDataReceptionDate(WEB3DateUtility.getDate("200708101112","yyyyMMddHHmm"));
            l_stockSecuredLoanParams.setCancelDataReceptionDate(WEB3DateUtility.getDate("200708101012","yyyyMMddHHmm"));
            l_stockSecuredLoanParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setCloseDate(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070810","yyyyMMdd"));
            l_stockSecuredLoanParams.setMngLockFlagAdvance(null);
            l_stockSecuredLoanParams.setMngLockFlagShortSecurity(null);
            l_stockSecuredLoanParams.setMngLockFlagUnpayMargin(null);
            l_stockSecuredLoanParams.setMngLockFlagUnsubstitDepo(null);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            l_request.accountCode = "123456";
            l_request.stockLoanAccount = "101234567";
            l_request.applyStatus = "0";
            l_request.applyDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.applyDateTo = WEB3DateUtility.getDate("20070812","yyyyMMdd");
            l_request.accountOpenDateFrom = WEB3DateUtility.getDate("20070809","yyyyMMdd");
            l_request.accountOpenDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.executeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.executeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.cancelDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.cancelDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.closeDateFrom = WEB3DateUtility.getDate("20070808","yyyyMMdd");
            l_request.closeDateTo = WEB3DateUtility.getDate("200708012","yyyyMMdd");
            l_request.sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey sortkey = new WEB3SLSortKey();
            sortkey.keyItem = "accountCode";
            sortkey.ascDesc = "D";
            l_request.sortKeys[0] = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AdminSLAccountOpenApplyListResponse l_response = l_impl.getListScreen(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].applyDateFrom,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].accountOpenDate,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].executeDate,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].cancelDate,"yyyyMMddHHmm"), "200708100000");
            assertEquals(WEB3DateUtility.formatDate(l_response.accountOpenApplyDetailList[0].closeDate, "yyyyMMddHHmm"), "200708100000");
            assertEquals("0",l_response.accountOpenApplyDetailList[0].mngCollateralLockReasonFlag);
            assertEquals("0",l_response.accountOpenApplyDetailList[0].mngDepositLockReasonFlag);
            assertEquals("0",l_response.accountOpenApplyDetailList[0].mngExpenseLockReasonFlag);
            assertEquals("0",l_response.accountOpenApplyDetailList[0].mngReceiptLockReasonFlag);

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AioSecuredLoanOfferStateListServiceImplForTest extends WEB3AioSecuredLoanOfferStateListServiceImpl
    {
        protected WEB3AdminSLAccountOpenApplyListResponse getListScreen(
                WEB3AdminSLAccountOpenApplyListRequest l_request) throws WEB3BaseException
        {
            WEB3AdminSLAccountOpenApplyListResponse l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.pageIndex = "100";
            return l_response;
        }
    }
}
@
