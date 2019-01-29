head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.12.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistSellTransSrcListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.DirectDebitParams;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcCondition;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcSortKey;
import webbroker3.inform.message.WEB3AdminInformProfDistTransferInfo;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金・売却代金振込先一覧サービス実装クラス)<BR>
 * 利金・分配金・売却代金振込先一覧サービス実装クラス<BR>
 */
public class WEB3AdminInformProfDistSellTransSrcListServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListServiceImplTest.class);
    WEB3AdminInformProfDistSellTransSrcListServiceImpl interceptor = null;
    public WEB3AdminInformProfDistSellTransSrcListServiceImplTest(String arg0)
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
    
    /**
     * (利金・分配金・売却代金振込先一覧サービス処理を行う。)<BR>
     * testExecute_0001<BR>
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GenRequest l_request = new WEB3AdminInformProfDistSellTransSrcInpRequest();
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl()
            {
                protected WEB3AdminInformProfDistSellTransSrcInpResponse getInputScreen(
                    WEB3AdminInformProfDistSellTransSrcInpRequest l_request) throws WEB3BaseException
                {
                    return new WEB3AdminInformProfDistSellTransSrcInpResponse();
                }
                protected WEB3AdminInformProfDistSellTransSrcListResponse getListScreen(
                    WEB3AdminInformProfDistSellTransSrcListRequest l_request) throws WEB3BaseException
                {
                    return new WEB3AdminInformProfDistSellTransSrcListResponse();
                }
            };
            WEB3GenResponse l_response = interceptor.execute(l_request);
            
            assertTrue(l_response instanceof WEB3AdminInformProfDistSellTransSrcInpResponse);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (利金・分配金・売却代金振込先一覧サービス処理を行う。)<BR>
     * testExecute_0002<BR>
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GenRequest l_request = new WEB3AdminInformProfDistSellTransSrcListRequest();
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl()
            {
                protected WEB3AdminInformProfDistSellTransSrcInpResponse getInputScreen(
                        WEB3AdminInformProfDistSellTransSrcInpRequest l_request) throws WEB3BaseException
                    {
                        return new WEB3AdminInformProfDistSellTransSrcInpResponse();
                    }
                    protected WEB3AdminInformProfDistSellTransSrcListResponse getListScreen(
                        WEB3AdminInformProfDistSellTransSrcListRequest l_request) throws WEB3BaseException
                    {
                        return new WEB3AdminInformProfDistSellTransSrcListResponse();
                    }
            };
            WEB3GenResponse l_response = interceptor.execute(l_request);
            
            assertTrue(l_response instanceof WEB3AdminInformProfDistSellTransSrcListResponse);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * (利金・分配金・売却代金振込先一覧サービス処理を行う。)<BR>
     * testExecute_0003<BR>
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GenRequest l_request = new WEB3GenRequest(){

                public WEB3GenResponse createResponse()
                {
                    // TODO Auto-generated method stub
                    return null;
                }
                };
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl()
            {
                protected WEB3AdminInformProfDistSellTransSrcInpResponse getInputScreen(
                        WEB3AdminInformProfDistSellTransSrcInpRequest l_request) throws WEB3BaseException
                    {
                        return new WEB3AdminInformProfDistSellTransSrcInpResponse();
                    }
                    protected WEB3AdminInformProfDistSellTransSrcListResponse getListScreen(
                        WEB3AdminInformProfDistSellTransSrcListRequest l_request) throws WEB3BaseException
                    {
                        return new WEB3AdminInformProfDistSellTransSrcListResponse();
                    }
            };
            interceptor.execute(l_request);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(e instanceof WEB3SystemLayerException);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, ((WEB3SystemLayerException)e).getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    /**
     * get入力画面<BR>
     * testGetInputScreen_0001<BR>
     */
    public void testGetInputScreen_0001()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);
            
            WEB3AdminInformProfDistSellTransSrcInpRequest l_request = 
                new WEB3AdminInformProfDistSellTransSrcInpRequest();
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            WEB3AdminInformProfDistSellTransSrcInpResponse l_response = interceptor.getInputScreen(l_request);
            assertTrue(l_response.registDateFrom != null);
            assertTrue(l_response.registDateTo != null);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }
    
//    /**
//     * get一覧画面<BR>
//     * testGetListScreen_0001<BR>
//     */
//    public void testGetListScreen_0001()
//    {
//        final String STR_METHOD_NAME = " testGetListScreen_0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            //1
//            DirectDebitParams l_directDebitRow = new DirectDebitParams();
//
//            l_directDebitRow.setInstitutionCode("0D");
//
//          l_directDebitRow.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", 
//              "yyyyMMdd"));
//
//          l_directDebitRow.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505"
//          , "yyyyMMdd"));
//            l_directDebitRow.setBranchCode("381");
//            l_directDebitRow.setAccountCode("2512238");
//            l_directDebitRow.setTraderCode("11127");
//            l_directDebitRow.setDesignateDiv("1");
//            l_directDebitRow.setComodity("21");
//            l_directDebitRow.setFundCode("38");
//            l_directDebitRow.setTransferDiv("1");
//            l_directDebitRow.setFinInstitutionCode("1234");
//            l_directDebitRow.setFinInstitutionName("lmz");
//            l_directDebitRow.setFinBranchCode("00381");
//            l_directDebitRow.setFinBranchName("gsyh");
//            l_directDebitRow.setFinSaveDiv("1");
//            l_directDebitRow.setFinAccountNo("8888");
//            l_directDebitRow.setFinAccountName("cai");
//            l_directDebitRow.setTransCommission("1");
//            l_directDebitRow.setTransDealDiv("2");
//            l_directDebitRow.setLastUpdater("01268");
//            l_directDebitRow.setCreatedTimestamp(WEB3DateUtility.getDate("20050505",
//          "yyyyMMdd"));
//
//          l_directDebitRow.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505",
//          "yyyyMMdd"));
//          TestDBUtility.deleteAll(DirectDebitRow.TYPE);
//          TestDBUtility.insertWithDel(l_directDebitRow);
//            //2
//          DirectDebitParams l_directDebitRow1 = new DirectDebitParams();
//
//          l_directDebitRow1.setInstitutionCode("1D");
//
//        l_directDebitRow1.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", 
//            "yyyyMMdd"));
//
//        l_directDebitRow1.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505"
//        , "yyyyMMdd"));
//          l_directDebitRow1.setBranchCode("381");
//          l_directDebitRow1.setAccountCode("2512238");
//          l_directDebitRow1.setTraderCode("11127");
//          l_directDebitRow1.setDesignateDiv("1");
//          l_directDebitRow1.setComodity("21");
//          l_directDebitRow1.setFundCode("38");
//          l_directDebitRow1.setTransferDiv("1");
//          l_directDebitRow1.setFinInstitutionCode("1234");
//          l_directDebitRow1.setFinInstitutionName("lmz");
//          l_directDebitRow1.setFinBranchCode("00381");
//          l_directDebitRow1.setFinBranchName("gsyh");
//          l_directDebitRow1.setFinSaveDiv("1");
//          l_directDebitRow1.setFinAccountNo("8888");
//          l_directDebitRow1.setFinAccountName("cai");
//          l_directDebitRow1.setTransCommission("1");
//          l_directDebitRow1.setTransDealDiv("2");
//          l_directDebitRow1.setLastUpdater("01268");
//          l_directDebitRow1.setCreatedTimestamp(WEB3DateUtility.getDate("20050505",
//        "yyyyMMdd"));
//
//        l_directDebitRow1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505",
//        "yyyyMMdd"));
//        TestDBUtility.insertWithDel(l_directDebitRow1);
//        //#
//            
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//            
//            WEB3AdministratorForMock.mockValidateAuthority(
//                l_expectAdministrator, "A0105", false, true);
//            WEB3AdministratorForMock.mockValidateBranchPermission(
//                l_expectAdministrator, "381", true);
//            
//            
//            //data of xie xuan's
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//
//            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_gentradeMainAccount);
//            
//            Institution l_Institution = null;
//            try
//            {
//                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//                TestDBUtility.deleteAll(l_institutionParams.getRowType());
//                TestDBUtility.insertWithDel(l_institutionParams);
//
//                l_Institution = new WEB3GentradeInstitution("0D");
//            }
//            catch (Exception l_ex)
//            {
//                log.error(TEST_END + STR_METHOD_NAME, l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getInstitution", 
//                new Class[] {String.class},
//                l_Institution);
//
//            WEB3MutualFundProduct l_mutualFundProduct = null;
//            try
//            {
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setStandardName("連絡管理");
//                TestDBUtility.deleteAll(ProductRow.TYPE);
//                TestDBUtility.insertWithDel(l_productParams);
//                MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//                l_mutualFundProductParams.setProductId(3304148080000L);
//                TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
//                TestDBUtility.insertWithDel(l_mutualFundProductParams);
//
//                l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
//            }
//            catch (Exception l_ex)
//            {
//                log.error(TEST_END + STR_METHOD_NAME, l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFundProductManager",
//                "getMutualFundProduct",
//                new Class[]{ Institution.class, String.class },
//                l_mutualFundProduct);
//            // end
//
//            WEB3AdminInformProfDistSellTransSrcListRequest l_request = 
//                new WEB3AdminInformProfDistSellTransSrcListRequest()
//            {
//                public void validate() throws WEB3BaseException
//                {
//                }
//            };
//            WEB3AdminInformProfDistSellTransSrcCondition searchCondition = new WEB3AdminInformProfDistSellTransSrcCondition();
//            searchCondition.branchCode = "381";
//            
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey1 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey1.keyItem = "branchCode";
//          l_sortKey1.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey2 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey2.keyItem = "branchCode";
//          l_sortKey2.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey3 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey3.keyItem = "traderCode";
//          l_sortKey3.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey4 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey4.keyItem = "traderCode";
//          l_sortKey4.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey5 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey5.keyItem = "accountCode";
//          l_sortKey5.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey6 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey6.keyItem = "accountCode";
//          l_sortKey6.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey7 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey7.keyItem = "productCode";
//          l_sortKey7.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey8 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey8.keyItem = "productCode";
//          l_sortKey8.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey9 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey9.keyItem = "registDate";
//          l_sortKey9.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey10 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey10.keyItem = "registDate";
//          l_sortKey10.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey11 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey11.keyItem = "sonota";
//          l_sortKey11.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey12 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey12.keyItem = "sonota";
//          l_sortKey12.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys =
//          {
//                  l_sortKey1,
//                  l_sortKey2,
//                  l_sortKey3,
//                  l_sortKey4,
//                  l_sortKey5,
//                  l_sortKey6,
//                  l_sortKey7,
//                  l_sortKey8,
//                  l_sortKey9,
//                  l_sortKey10,
//                  l_sortKey11,
//                  l_sortKey12
//          };
//            
//            l_request.searchCondition = searchCondition;
//            l_request.pageSize = "1";
//            l_request.pageIndex = "1";
//            l_request.sortKeys = l_sortKeys;
//            
//            
//            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//            WEB3AdminInformProfDistSellTransSrcListResponse l_response = interceptor.getListScreen(l_request);
//            assertTrue(l_response.transferInfo != null);
//            assertEquals("0", l_response.pageIndex);
//            assertEquals("1", l_response.totalPages);
//            assertEquals("1", l_response.totalRecords);
//            log.exiting(TEST_START + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            e.printStackTrace();
//            fail();
//        }
//        finally
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        }
//    }
//    
//    /**
//     * get一覧画面<BR>
//     * testGetListScreen_0002<BR>
//     */
//    public void testGetListScreen_0002()
//    {
//        final String STR_METHOD_NAME = " testGetListScreen_0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            DirectDebitParams l_directDebitRow = new DirectDebitParams();
//
//            l_directDebitRow.setInstitutionCode("0D");
//
//          l_directDebitRow.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", 
//              "yyyyMMdd"));
//
//          l_directDebitRow.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505"
//          , "yyyyMMdd"));
//            l_directDebitRow.setBranchCode("381");
//            l_directDebitRow.setAccountCode("2512238");
//            l_directDebitRow.setTraderCode("11127");
//            l_directDebitRow.setDesignateDiv("1");
//            l_directDebitRow.setComodity("21");
//            l_directDebitRow.setFundCode("38");
//            l_directDebitRow.setTransferDiv("1");
//            l_directDebitRow.setFinInstitutionCode("1234");
//            l_directDebitRow.setFinInstitutionName("lmz");
//            l_directDebitRow.setFinBranchCode("00381");
//            l_directDebitRow.setFinBranchName("gsyh");
//            l_directDebitRow.setFinSaveDiv("1");
//            l_directDebitRow.setFinAccountNo("8888");
//            l_directDebitRow.setFinAccountName("cai");
//            l_directDebitRow.setTransCommission("1");
//            l_directDebitRow.setTransDealDiv("2");
//            l_directDebitRow.setLastUpdater("01268");
//            l_directDebitRow.setCreatedTimestamp(WEB3DateUtility.getDate("20050505",
//          "yyyyMMdd"));
//
//          l_directDebitRow.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505",
//          "yyyyMMdd"));
//          TestDBUtility.deleteAll(DirectDebitRow.TYPE);
//          TestDBUtility.insertWithDel(l_directDebitRow);
//          //2
//          DirectDebitParams l_directDebitRow1 = new DirectDebitParams();
//
//          l_directDebitRow1.setInstitutionCode("1D");
//
//        l_directDebitRow1.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", 
//            "yyyyMMdd"));
//
//        l_directDebitRow1.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505"
//        , "yyyyMMdd"));
//          l_directDebitRow1.setBranchCode("381");
//          l_directDebitRow1.setAccountCode("2512238");
//          l_directDebitRow1.setTraderCode("11127");
//          l_directDebitRow1.setDesignateDiv("1");
//          l_directDebitRow1.setComodity("21");
//          l_directDebitRow1.setFundCode("38");
//          l_directDebitRow1.setTransferDiv("1");
//          l_directDebitRow1.setFinInstitutionCode("1234");
//          l_directDebitRow1.setFinInstitutionName("lmz");
//          l_directDebitRow1.setFinBranchCode("00381");
//          l_directDebitRow1.setFinBranchName("gsyh");
//          l_directDebitRow1.setFinSaveDiv("1");
//          l_directDebitRow1.setFinAccountNo("8888");
//          l_directDebitRow1.setFinAccountName("cai");
//          l_directDebitRow1.setTransCommission("1");
//          l_directDebitRow1.setTransDealDiv("2");
//          l_directDebitRow1.setLastUpdater("01268");
//          l_directDebitRow1.setCreatedTimestamp(WEB3DateUtility.getDate("20050505",
//        "yyyyMMdd"));
//
//        l_directDebitRow1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505",
//        "yyyyMMdd"));
//        TestDBUtility.insertWithDel(l_directDebitRow1);
//        //#
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setInstitutionCode("0D");//BooleanEnum.TRUE
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//            
//            WEB3AdministratorForMock.mockValidateAuthority(
//                l_expectAdministrator, "A0105", false, true);
//            WEB3AdministratorForMock.mockValidateBranchPermission(
//                l_expectAdministrator, "381", true);
//            
//            //data of xie xuan's
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//
//            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_gentradeMainAccount);
//            
//            Institution l_Institution = null;
//            try
//            {
//                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//                TestDBUtility.deleteAll(l_institutionParams.getRowType());
//                TestDBUtility.insertWithDel(l_institutionParams);
//
//                l_Institution = new WEB3GentradeInstitution("0D");
//            }
//            catch (Exception l_ex)
//            {
//                log.error(TEST_END + STR_METHOD_NAME, l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getInstitution", 
//                new Class[] {String.class},
//                l_Institution);
//
//            WEB3MutualFundProduct l_mutualFundProduct = null;
//            try
//            {
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setStandardName("連絡管理");
//                TestDBUtility.deleteAll(ProductRow.TYPE);
//                TestDBUtility.insertWithDel(l_productParams);
//                MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//                l_mutualFundProductParams.setProductId(3304148080000L);
//                TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
//                TestDBUtility.insertWithDel(l_mutualFundProductParams);
//
//                l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
//            }
//            catch (Exception l_ex)
//            {
//                log.error(TEST_END + STR_METHOD_NAME, l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.mf.WEB3MutualFundProductManager",
//                "getMutualFundProduct",
//                new Class[]{ Institution.class, String.class },
//                l_mutualFundProduct);
//            // end
//
//            WEB3AdminInformProfDistSellTransSrcListRequest l_request = 
//                new WEB3AdminInformProfDistSellTransSrcListRequest()
//            {
//                public void validate() throws WEB3BaseException
//                {
//                }
//            };
//            WEB3AdminInformProfDistSellTransSrcCondition searchCondition = new WEB3AdminInformProfDistSellTransSrcCondition();
//            searchCondition.branchCode = null;
//            
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey1 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey1.keyItem = "branchCode";
//          l_sortKey1.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey2 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey2.keyItem = "branchCode";
//          l_sortKey2.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey3 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey3.keyItem = "traderCode";
//          l_sortKey3.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey4 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey4.keyItem = "traderCode";
//          l_sortKey4.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey5 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey5.keyItem = "accountCode";
//          l_sortKey5.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey6 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey6.keyItem = "accountCode";
//          l_sortKey6.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey7 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey7.keyItem = "productCode";
//          l_sortKey7.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey8 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey8.keyItem = "productCode";
//          l_sortKey8.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey9 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey9.keyItem = "registDate";
//          l_sortKey9.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey10 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey10.keyItem = "registDate";
//          l_sortKey10.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey11 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey11.keyItem = "sonota";
//          l_sortKey11.ascDesc = "A";
//          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey12 = new WEB3AdminInformProfDistSellTransSrcSortKey();
//          l_sortKey12.keyItem = "sonota";
//          l_sortKey12.ascDesc = "D";
//          
//          WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys =
//          {
//                  l_sortKey1,
//                  l_sortKey2,
//                  l_sortKey3,
//                  l_sortKey4,
//                  l_sortKey5,
//                  l_sortKey6,
//                  l_sortKey7,
//                  l_sortKey8,
//                  l_sortKey9,
//                  l_sortKey10,
//                  l_sortKey11,
//                  l_sortKey12
//          };
//            
//            l_request.searchCondition = searchCondition;
//            l_request.pageSize = "1";
//            l_request.pageIndex = "1";
//            l_request.sortKeys = l_sortKeys;
//            
//            
//            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//            WEB3AdminInformProfDistSellTransSrcListResponse l_response = interceptor.getListScreen(l_request);
//            assertTrue(l_response.transferInfo != null);
//            assertEquals("0", l_response.pageIndex);
//            assertEquals("1", l_response.totalPages);
//            assertEquals("1", l_response.totalRecords);
//            log.exiting(TEST_START + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            e.printStackTrace();
//            fail();
//        }
//        finally
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        }
//    }
    
    /**
     * get一覧画面<BR>
     * testGetListScreen_0003<BR>
     */
    public void testGetListScreen_0003()
    {
        final String STR_METHOD_NAME = " testGetListScreen_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            DirectDebitParams l_directDebitRow = new DirectDebitParams();

            l_directDebitRow.setInstitutionCode("0D");

          l_directDebitRow.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", 
              "yyyyMMdd"));

          l_directDebitRow.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505"
          , "yyyyMMdd"));
            l_directDebitRow.setBranchCode("381");
            l_directDebitRow.setAccountCode("2512238");
            l_directDebitRow.setTraderCode("11127");
            l_directDebitRow.setDesignateDiv("1");
            l_directDebitRow.setComodity("21");
            l_directDebitRow.setFundCode("38");
            l_directDebitRow.setTransferDiv("1");
            l_directDebitRow.setFinInstitutionCode("1234");
            l_directDebitRow.setFinInstitutionName("lmz");
            l_directDebitRow.setFinBranchCode("00381");
            l_directDebitRow.setFinBranchName("gsyh");
            l_directDebitRow.setFinSaveDiv("1");
            l_directDebitRow.setFinAccountNo("8888");
            l_directDebitRow.setFinAccountName("cai");
            l_directDebitRow.setTransCommission("1");
            l_directDebitRow.setTransDealDiv("2");
            l_directDebitRow.setLastUpdater("01268");
            l_directDebitRow.setCreatedTimestamp(WEB3DateUtility.getDate("20050505",
          "yyyyMMdd"));

          l_directDebitRow.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505",
          "yyyyMMdd"));
          TestDBUtility.deleteAll(DirectDebitRow.TYPE);
          TestDBUtility.insertWithDel(l_directDebitRow);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");//BooleanEnum.TRUE
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_expectAdministrator, "381", true);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            
            //data of xie xuan's
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_gentradeMainAccount);
            
            Institution l_Institution = null;
            try
            {
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                TestDBUtility.deleteAll(l_institutionParams.getRowType());
                TestDBUtility.insertWithDel(l_institutionParams);

                l_Institution = new WEB3GentradeInstitution("0D");
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution", 
                new Class[] {String.class},
                l_Institution);

            WEB3MutualFundProduct l_mutualFundProduct = null;
            try
            {
                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setStandardName("連絡管理");
                TestDBUtility.deleteAll(ProductRow.TYPE);
                TestDBUtility.insertWithDel(l_productParams);
                MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
                l_mutualFundProductParams.setProductId(3304148080000L);
                TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
                TestDBUtility.insertWithDel(l_mutualFundProductParams);

                l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[]{ Institution.class, String.class },
                null);
            // end

            WEB3AdminInformProfDistSellTransSrcListRequest l_request = 
                new WEB3AdminInformProfDistSellTransSrcListRequest()
            {
                public void validate() throws WEB3BaseException
                {
                }
            };
            WEB3AdminInformProfDistSellTransSrcCondition searchCondition = new WEB3AdminInformProfDistSellTransSrcCondition();
            searchCondition.branchCode = null;
            
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey1 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey1.keyItem = "branchCode";
          l_sortKey1.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey2 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey2.keyItem = "branchCode";
          l_sortKey2.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey3 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey3.keyItem = "traderCode";
          l_sortKey3.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey4 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey4.keyItem = "traderCode";
          l_sortKey4.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey5 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey5.keyItem = "accountCode";
          l_sortKey5.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey6 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey6.keyItem = "accountCode";
          l_sortKey6.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey7 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey7.keyItem = "productCode";
          l_sortKey7.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey8 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey8.keyItem = "productCode";
          l_sortKey8.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey9 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey9.keyItem = "registDate";
          l_sortKey9.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey10 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey10.keyItem = "registDate";
          l_sortKey10.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey11 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey11.keyItem = "sonota";
          l_sortKey11.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey12 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey12.keyItem = "sonota";
          l_sortKey12.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys =
          {
                  l_sortKey1,
                  l_sortKey2,
                  l_sortKey3,
                  l_sortKey4,
                  l_sortKey5,
                  l_sortKey6,
                  l_sortKey7,
                  l_sortKey8,
                  l_sortKey9,
                  l_sortKey10,
                  l_sortKey11,
                  l_sortKey12
          };
            
            l_request.searchCondition = searchCondition;
            l_request.pageSize = "100";
            l_request.pageIndex = "81";
            l_request.sortKeys = l_sortKeys;
            
            
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            WEB3AdminInformProfDistSellTransSrcListResponse l_response = interceptor.getListScreen(l_request);
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(e instanceof WEB3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01380, ((WEB3BusinessLayerException)e).getErrorInfo());
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }
    
    /**
     * get一覧画面<BR>
     * testGetListScreen_0004<BR>
     */
    public void testGetListScreen_0004()
    {
        final String STR_METHOD_NAME = " testGetListScreen_0004";
        log.entering(TEST_START + STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            DirectDebitParams l_directDebitRow = new DirectDebitParams();

            l_directDebitRow.setInstitutionCode("0D");

          l_directDebitRow.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", 
              "yyyyMMdd"));

          l_directDebitRow.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505"
          , "yyyyMMdd"));
            l_directDebitRow.setBranchCode("381");
            l_directDebitRow.setAccountCode("2512238");
            l_directDebitRow.setTraderCode("11127");
            l_directDebitRow.setDesignateDiv("1");
            l_directDebitRow.setComodity("21");
            l_directDebitRow.setFundCode("38");
            l_directDebitRow.setTransferDiv("1");
            l_directDebitRow.setFinInstitutionCode("1234");
            l_directDebitRow.setFinInstitutionName("lmz");
            l_directDebitRow.setFinBranchCode("00381");
            l_directDebitRow.setFinBranchName("gsyh");
            l_directDebitRow.setFinSaveDiv("1");
            l_directDebitRow.setFinAccountNo("8888");
            l_directDebitRow.setFinAccountName("cai");
            l_directDebitRow.setTransCommission("1");
            l_directDebitRow.setTransDealDiv("2");
            l_directDebitRow.setLastUpdater("01268");
            l_directDebitRow.setCreatedTimestamp(WEB3DateUtility.getDate("20050505",
          "yyyyMMdd"));

          l_directDebitRow.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505",
          "yyyyMMdd"));
          TestDBUtility.deleteAll(DirectDebitRow.TYPE);
//          TestDBUtility.insertWithDel(l_directDebitRow);
            
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_expectAdministrator, "381", true);
            
            
            //data of xie xuan's
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_gentradeMainAccount);
            
            Institution l_Institution = null;
            try
            {
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                TestDBUtility.deleteAll(l_institutionParams.getRowType());
                TestDBUtility.insertWithDel(l_institutionParams);

                l_Institution = new WEB3GentradeInstitution("0D");
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution", 
                new Class[] {String.class},
                l_Institution);

            WEB3MutualFundProduct l_mutualFundProduct = null;
            try
            {
                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setStandardName("連絡管理");
                TestDBUtility.deleteAll(ProductRow.TYPE);
                TestDBUtility.insertWithDel(l_productParams);
                MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
                l_mutualFundProductParams.setProductId(3304148080000L);
                TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
                TestDBUtility.insertWithDel(l_mutualFundProductParams);

                l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[]{ Institution.class, String.class },
                null);
            // end

            WEB3AdminInformProfDistSellTransSrcListRequest l_request = 
                new WEB3AdminInformProfDistSellTransSrcListRequest()
            {
                public void validate() throws WEB3BaseException
                {
                }
            };
            WEB3AdminInformProfDistSellTransSrcCondition searchCondition = new WEB3AdminInformProfDistSellTransSrcCondition();
            searchCondition.branchCode = "381";
            
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey1 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey1.keyItem = "branchCode";
          l_sortKey1.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey2 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey2.keyItem = "branchCode";
          l_sortKey2.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey3 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey3.keyItem = "traderCode";
          l_sortKey3.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey4 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey4.keyItem = "traderCode";
          l_sortKey4.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey5 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey5.keyItem = "accountCode";
          l_sortKey5.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey6 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey6.keyItem = "accountCode";
          l_sortKey6.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey7 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey7.keyItem = "productCode";
          l_sortKey7.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey8 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey8.keyItem = "productCode";
          l_sortKey8.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey9 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey9.keyItem = "registDate";
          l_sortKey9.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey10 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey10.keyItem = "registDate";
          l_sortKey10.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey11 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey11.keyItem = "sonota";
          l_sortKey11.ascDesc = "A";
          WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey12 = new WEB3AdminInformProfDistSellTransSrcSortKey();
          l_sortKey12.keyItem = "sonota";
          l_sortKey12.ascDesc = "D";
          
          WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys =
          {
                  l_sortKey1,
                  l_sortKey2,
                  l_sortKey3,
                  l_sortKey4,
                  l_sortKey5,
                  l_sortKey6,
                  l_sortKey7,
                  l_sortKey8,
                  l_sortKey9,
                  l_sortKey10,
                  l_sortKey11,
                  l_sortKey12
          };
            
            l_request.searchCondition = searchCondition;
            l_request.pageSize = "100";
            l_request.pageIndex = "81";
            l_request.sortKeys = l_sortKeys;
            
            
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            WEB3AdminInformProfDistSellTransSrcListResponse l_response = interceptor.getListScreen(l_request);
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(e instanceof WEB3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, ((WEB3BusinessLayerException)e).getErrorInfo());
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }
    /**
     * create検索条件文字列
     * testCreateQueryString_0001
     */
    public void testCreateQueryString_0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition =
                new WEB3AdminInformProfDistSellTransSrcCondition();
            l_profDistSellTransSrcCondition.branchCode = "123456";
            l_profDistSellTransSrcCondition.traderCode = "3254";
            l_profDistSellTransSrcCondition.accountCode = "45654546";
            l_profDistSellTransSrcCondition.specifyDiv = "AS";
            l_profDistSellTransSrcCondition.product = "sd";
            l_profDistSellTransSrcCondition.transferDiv = "asda";
            l_profDistSellTransSrcCondition.productCode = "345641";
            l_profDistSellTransSrcCondition.registDateFrom = new Date(20070502L);
            l_profDistSellTransSrcCondition.registDateTo = new Date(20070530L);
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            Method method = WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformProfDistSellTransSrcCondition.class}
                );
            method.setAccessible(true);
            String l_strReturned = (String)method.invoke(interceptor, new Object[]{l_profDistSellTransSrcCondition});
            String l_strExpect = "institution_code=? and branch_code=? and trader_code=? and account_code like ? || '%' and designate_div=? and comodity like ? || '%' and transfer_div=? and fund_code=? and sonar_created_timestamp>=? and sonar_created_timestamp<?";
            assertEquals(l_strExpect, l_strReturned);
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * create検索条件文字列
     * testCreateQueryString_0002
     */
    public void testCreateQueryString_0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition =
                new WEB3AdminInformProfDistSellTransSrcCondition();
            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            Method method = WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminInformProfDistSellTransSrcCondition.class}
                );
            method.setAccessible(true);
            String l_strReturned = (String)method.invoke(interceptor, new Object[]{l_profDistSellTransSrcCondition});
            String l_strExpect = "institution_code=?";
            assertEquals(l_strExpect, l_strReturned);
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * create検索条件データコンテナ
     * testcreateQueryDataContainer_0001
     */
    public void testcreateQueryDataContainer_0001()
    {
        final String STR_METHOD_NAME = " testcreateQueryDataContainer_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "l_strInstitutionCode";
            WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition =
                new WEB3AdminInformProfDistSellTransSrcCondition();
            l_profDistSellTransSrcCondition.branchCode = "123456";
            l_profDistSellTransSrcCondition.traderCode = "3254";
            l_profDistSellTransSrcCondition.accountCode = "45654546";
            l_profDistSellTransSrcCondition.specifyDiv = "AS";
            l_profDistSellTransSrcCondition.product = "sd";
            l_profDistSellTransSrcCondition.transferDiv = "asda";
            l_profDistSellTransSrcCondition.productCode = "345641";
            l_profDistSellTransSrcCondition.registDateFrom = WEB3DateUtility.getDate("20070501", "yyyyMMdd");
            l_profDistSellTransSrcCondition.registDateTo = WEB3DateUtility.getDate("20070531", "yyyyMMdd");

            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            Method method = WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformProfDistSellTransSrcCondition.class}
                );
            method.setAccessible(true);
            Object[] l_queryContainer = (Object[])method.invoke(interceptor, new Object[]{l_strInstitutionCode, l_profDistSellTransSrcCondition});
            assertEquals(10, l_queryContainer.length);
            assertEquals("l_strInstitutionCode", l_queryContainer[0]);
            assertEquals("123456", l_queryContainer[1]);
            assertEquals("3254", l_queryContainer[2]);
            assertEquals("45654546", l_queryContainer[3]);
            assertEquals("AS", l_queryContainer[4]);
            assertEquals("s", l_queryContainer[5]);
            assertEquals("asda", l_queryContainer[6]);
            assertEquals("345641", l_queryContainer[7]);
            assertEquals("20070501", WEB3DateUtility.formatDate((Date)l_queryContainer[8], "yyyyMMdd"));
            assertEquals("20070601", WEB3DateUtility.formatDate((Date)l_queryContainer[9], "yyyyMMdd"));
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * create検索条件データコンテナ
     * testcreateQueryDataContainer_0002
     */
    public void testcreateQueryDataContainer_0002()
    {
        final String STR_METHOD_NAME = " testcreateQueryDataContainer_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "l_strInstitutionCode";
            WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition =
                new WEB3AdminInformProfDistSellTransSrcCondition();

            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            Method method = WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformProfDistSellTransSrcCondition.class}
                );
            method.setAccessible(true);
            Object[] l_queryContainer = (Object[])method.invoke(interceptor, new Object[]{l_strInstitutionCode, l_profDistSellTransSrcCondition});
            assertEquals(1, l_queryContainer.length);
            assertEquals("l_strInstitutionCode", l_queryContainer[0]);
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * create検索条件データコンテナ
     * testcreateQueryDataContainer_0003
     */
    public void testcreateQueryDataContainer_0003()
    {
        final String STR_METHOD_NAME = " testcreateQueryDataContainer_0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "l_strInstitutionCode";
            WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition =
                new WEB3AdminInformProfDistSellTransSrcCondition();
            l_profDistSellTransSrcCondition.product = "";

            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            Method method = WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminInformProfDistSellTransSrcCondition.class}
                );
            method.setAccessible(true);
            Object[] l_queryContainer = (Object[])method.invoke(interceptor, new Object[]{l_strInstitutionCode, l_profDistSellTransSrcCondition});
            assertEquals(1, l_queryContainer.length);
            assertEquals("l_strInstitutionCode", l_queryContainer[0]);
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * createソート条件文字列
     * testCreateSortCondString_0001
     */
    public void testCreateSortCondString_0001()
    {
        final String STR_METHOD_NAME = " testCreateSortCondString_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey1 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey1.keyItem = "branchCode";
            l_sortKey1.ascDesc = "A";
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey2 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey2.keyItem = "branchCode";
            l_sortKey2.ascDesc = "D";
            
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey3 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey3.keyItem = "traderCode";
            l_sortKey3.ascDesc = "A";
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey4 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey4.keyItem = "traderCode";
            l_sortKey4.ascDesc = "D";
            
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey5 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey5.keyItem = "accountCode";
            l_sortKey5.ascDesc = "A";
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey6 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey6.keyItem = "accountCode";
            l_sortKey6.ascDesc = "D";
            
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey7 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey7.keyItem = "productCode";
            l_sortKey7.ascDesc = "A";
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey8 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey8.keyItem = "productCode";
            l_sortKey8.ascDesc = "D";
            
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey9 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey9.keyItem = "registDate";
            l_sortKey9.ascDesc = "A";
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey10 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey10.keyItem = "registDate";
            l_sortKey10.ascDesc = "D";
            
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey11 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey11.keyItem = "sonota";
            l_sortKey11.ascDesc = "A";
            WEB3AdminInformProfDistSellTransSrcSortKey l_sortKey12 = new WEB3AdminInformProfDistSellTransSrcSortKey();
            l_sortKey12.keyItem = "sonota";
            l_sortKey12.ascDesc = "D";
            
            WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys =
            {
                    l_sortKey1,
                    l_sortKey2,
                    l_sortKey3,
                    l_sortKey4,
                    l_sortKey5,
                    l_sortKey6,
                    l_sortKey7,
                    l_sortKey8,
                    l_sortKey9,
                    l_sortKey10,
                    l_sortKey11,
                    l_sortKey12
            };

            interceptor = new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
            Method method = WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "createSortCondString",
                new Class[]{WEB3AdminInformProfDistSellTransSrcSortKey[].class}
            );
            method.setAccessible(true);
            String l_strSortCondReturned = (String)method.invoke(interceptor, new Object[]{l_sortKeys});
            String l_strExpect = "branch_code, branch_code desc, trader_code, trader_code desc, account_code, account_code desc, fund_code, fund_code desc, sonar_created_timestamp, sonar_created_timestamp desc";
            assertEquals(l_strExpect, l_strSortCondReturned);
            log.exiting(TEST_START + STR_METHOD_NAME);
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }
    
//    public void testCreateTransferInfo_0001()
//    {
//        String STR_METHOD_NAME = "testCreateTransferInfo_0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//
//        WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeAccountManager",
//            "getMainAccount",
//            new Class[] {String.class, String.class, String.class},
//            l_gentradeMainAccount);
//        
//        Institution l_Institution = null;
//        try
//        {
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.deleteAllAndCommit(l_institutionParams.getRowType());
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
//
//            l_Institution = new WEB3GentradeInstitution("0D");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeAccountManager",
//            "getInstitution", 
//            new Class[] {String.class},
//            l_Institution);
//
//        WEB3MutualFundProduct l_mutualFundProduct = null;
//        try
//        {
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setStandardName("連絡管理");
//            TestDBUtility.deleteAllAndCommit(l_productParams.getRowType());
//            TestDBUtility.insertWithDelAndCommit(l_productParams);
//            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//            l_mutualFundProductParams.setProductId(3304148080000L);
//            TestDBUtility.deleteAllAndCommit(l_mutualFundProductParams.getRowType());
//            TestDBUtility.insertWithDelAndCommit(l_mutualFundProductParams);
//
//            l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.mf.WEB3MutualFundProductManager",
//            "getMutualFundProduct",
//            new Class[]{ Institution.class, String.class },
//            l_mutualFundProduct);
//        
//        String l_strProductCode = "2345";
//
//        try
//        {
//            DirectDebitParams l_directDebitParams = new DirectDebitParams();
//            
//            l_directDebitParams.setInstitutionCode("0D");
//            l_directDebitParams.setSonarLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
//            l_directDebitParams.setSonarCreatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
//            l_directDebitParams.setBranchCode("381");
//            l_directDebitParams.setAccountCode("2512238");
//            l_directDebitParams.setTraderCode("11127");
//            l_directDebitParams.setDesignateDiv("1");
//            l_directDebitParams.setComodity("21");
//            l_directDebitParams.setFundCode(l_strProductCode);
//            l_directDebitParams.setTransferDiv("1");
//            l_directDebitParams.setFinInstitutionCode("1234");
//            l_directDebitParams.setFinInstitutionName("lmz");
//            l_directDebitParams.setFinBranchCode("00381");
//            l_directDebitParams.setFinBranchName("gsyh");
//            l_directDebitParams.setFinSaveDiv("1");
//            l_directDebitParams.setFinAccountNo("8888");
//            l_directDebitParams.setFinAccountName("cai");
//            l_directDebitParams.setTransCommission("1");
//            l_directDebitParams.setTransDealDiv("2");
//            l_directDebitParams.setLastUpdater("01268");
//            l_directDebitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
//            l_directDebitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
//            
//            WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
//                new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//            
//            Method thisMethod = 
//                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
//                    "createTransferInfo",
//                    new Class[]{
//                        DirectDebitRow.class});
//            thisMethod.setAccessible(true);
//            thisMethod.invoke(
//                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{l_directDebitParams});
//
//            Object[] l_objParamsValue = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.mf.WEB3MutualFundProductManager",
//                "getMutualFundProduct",
//                new Class[] {Institution.class, String.class}).getFirstCalled();
//            
//            Object[] l_objParamsVlaue1 = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager",
//                    "getMainAccount",
//                    new Class[] {String.class, String.class, String.class}).getFirstCalled();
//
//            assertEquals("0D", l_objParamsVlaue1[0]);
//            assertEquals("381", l_objParamsVlaue1[1]);
//            assertEquals("2512238", l_objParamsVlaue1[2]);
//
//            assertTrue(l_objParamsValue[0] instanceof Institution);
//            assertEquals(l_strProductCode, l_objParamsValue[1]);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//
//    }
    
    public void testSetAccountInfo_0002()
    {
        String STR_METHOD_NAME = "testSetAccountInfo_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_gentradeMainAccount);
        
        WEB3AdminInformProfDistTransferInfo info = new WEB3AdminInformProfDistTransferInfo();
        
        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
        
        try
        {
            Method thisMethod = 
                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                    "setAccountInfo",
                    new Class[]{
                        String.class,
                        String.class,
                        String.class,
                        WEB3AdminInformProfDistTransferInfo.class});
            thisMethod.setAccessible(true);
            thisMethod.invoke(
                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"111", "111", "111", info});
            
            assertEquals("内藤　@四郎", info.accountName);
            assertEquals("ﾅｲﾄｳ ｼﾛｳ", info.accountNameKana);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetProductInfo_0001()
    {
        String STR_METHOD_NAME = "testSetProductInfo_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformProfDistTransferInfo info = new WEB3AdminInformProfDistTransferInfo();
        
        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
        
        try
        {
            Method thisMethod = 
                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                    "setProductInfo",
                    new Class[]{
                        String.class,
                        String.class,
                        String.class,
                        WEB3AdminInformProfDistTransferInfo.class});
            thisMethod.setAccessible(true);
            thisMethod.invoke(
                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"111", "111", null, info});
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
//    public void testSetProductInfo_0002()
//    {
//        String STR_METHOD_NAME = "testSetProductInfo_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        
//        Institution l_Institution = null;
//        try
//        {
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.deleteAll(l_institutionParams.getRowType());
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            l_Institution = new WEB3GentradeInstitution("0D");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeAccountManager",
//            "getInstitution", 
//            new Class[] {String.class},
//            l_Institution);
//
//        WEB3MutualFundProduct l_mutualFundProduct = null;
//        try
//        {
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setStandardName("連絡管理");
//            TestDBUtility.deleteAll(l_productParams.getRowType());
//            TestDBUtility.insertWithDel(l_productParams);
//            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//            l_mutualFundProductParams.setProductId(3304148080000L);
//            TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
//            TestDBUtility.insertWithDel(l_mutualFundProductParams);
//
//            l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.mf.WEB3MutualFundProductManager",
//            "getMutualFundProduct",
//            new Class[]{ Institution.class, String.class },
//            new NotFoundException());
//        
//        WEB3AdminInformProfDistTransferInfo info = new WEB3AdminInformProfDistTransferInfo();
//        
//        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
//            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//        
//        String l_strProductCode = "2345";
//        try
//        {
//            Method thisMethod = 
//                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
//                    "setProductInfo",
//                    new Class[]{
//                        String.class,
//                        String.class,
//                        String.class,
//                        WEB3AdminInformProfDistTransferInfo.class});
//            thisMethod.setAccessible(true);
//            thisMethod.invoke(
//                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"111", "222", l_strProductCode, info});
//
//        }
//        catch (InvocationTargetException l_ex)
//        {
//            Object[] l_objParamsValue = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.mf.WEB3MutualFundProductManager",
//                "getMutualFundProduct",
//                new Class[] {Institution.class, String.class}).getFirstCalled();
//
//            assertTrue(l_objParamsValue[0] instanceof Institution);
//            assertEquals(l_strProductCode, l_objParamsValue[1]);
//
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testSetProductInfo_0003()
//    {
//        String STR_METHOD_NAME = "testSetProductInfo_0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        
//        Institution l_Institution = null;
//        try
//        {
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.deleteAll(l_institutionParams.getRowType());
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            l_Institution = new WEB3GentradeInstitution("0D");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeAccountManager",
//            "getInstitution", 
//            new Class[] {String.class},
//            l_Institution);
//
//        WEB3MutualFundProduct l_mutualFundProduct = null;
//        try
//        {
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setStandardName("連絡管理");
//            TestDBUtility.deleteAll(l_productParams.getRowType());
//            TestDBUtility.insertWithDel(l_productParams);
//            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//            l_mutualFundProductParams.setProductId(3304148080000L);
//            l_mutualFundProductParams.setStandardName("連絡管理");
//            TestDBUtility.deleteAll(l_mutualFundProductParams.getRowType());
//            TestDBUtility.insertWithDel(l_mutualFundProductParams);
//
//            l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.mf.WEB3MutualFundProductManager",
//            "getMutualFundProduct",
//            new Class[] {Institution.class, String.class},
//            l_mutualFundProduct);
//        
//        WEB3AdminInformProfDistTransferInfo info = new WEB3AdminInformProfDistTransferInfo();
//        
//        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
//            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//        
//        String l_strProductCode = "2345";
//        try
//        {
//            Method thisMethod = 
//                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
//                    "setProductInfo",
//                    new Class[]{
//                        String.class,
//                        String.class,
//                        String.class,
//                        WEB3AdminInformProfDistTransferInfo.class});
//            thisMethod.setAccessible(true);
//            thisMethod.invoke(
//                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"111", "222", l_strProductCode, info});
//
//            Object[] l_objParamsValue = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.mf.WEB3MutualFundProductManager",
//                "getMutualFundProduct",
//                new Class[] {Institution.class, String.class}).getFirstCalled();
//
//            assertEquals("連絡管理", info.productName);
//            assertTrue(l_objParamsValue[0] instanceof Institution);
//            assertEquals(l_strProductCode, l_objParamsValue[1]);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testSetProductInfo_0004()
//    {
//        String STR_METHOD_NAME = "testSetProductInfo_0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        
//        Institution l_Institution = null;
//        try
//        {
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.deleteAll(l_institutionParams.getRowType());
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            l_Institution = new WEB3GentradeInstitution("0D");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeAccountManager",
//            "getInstitution", 
//            new Class[] {String.class},
//            l_Institution);
//
//        BondProductParams l_BondProductParams = TestDBUtility.getBondProductRow();
//        WEB3BondProduct l_bondProduct = null;
//        ProductParams l_productParams = TestDBUtility.getProductRow();
//        l_productParams.setProductId(3304140763000L);
//        try
//        {
//            TestDBUtility.deleteAll(l_productParams.getRowType());
//            TestDBUtility.insertWithDel(l_productParams);
//
//            l_bondProduct = new WEB3BondProduct(l_BondProductParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.bd.WEB3BondProductManager",
//            "getBondProduct",
//            new Class[] {Institution.class, String.class},
//            new NotFoundException());
//        
//        WEB3AdminInformProfDistTransferInfo info = new WEB3AdminInformProfDistTransferInfo();
//        
//        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
//            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//        
//        String l_strProductCode = "2345";
//        try
//        {
//            Method thisMethod = 
//                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
//                    "setProductInfo",
//                    new Class[]{
//                        String.class,
//                        String.class,
//                        String.class,
//                        WEB3AdminInformProfDistTransferInfo.class});
//            thisMethod.setAccessible(true);
//            thisMethod.invoke(
//                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"111", null, l_strProductCode, info});
//
//            fail();
//        }
//        catch (InvocationTargetException l_ex)
//        {
//            Object[] l_objParamsValue = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.bd.WEB3BondProductManager",
//                    "getBondProduct",
//                new Class[] {Institution.class, String.class}).getFirstCalled();
//            
//            assertTrue(l_objParamsValue[0] instanceof Institution);
//            assertEquals(l_strProductCode, l_objParamsValue[1]);
//
//            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testSetProductInfo_0005()
//    {
//        String STR_METHOD_NAME = "testSetProductInfo_0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        
//        Institution l_Institution = null;
//        try
//        {
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.deleteAll(l_institutionParams.getRowType());
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            l_Institution = new WEB3GentradeInstitution("0D");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeAccountManager",
//            "getInstitution", 
//            new Class[] {String.class},
//            l_Institution);
//
//        BondProductParams l_BondProductParams = TestDBUtility.getBondProductRow();
//        l_BondProductParams.setProductName("連絡管理");
//        WEB3BondProduct l_bondProduct = null;
//        ProductParams l_productParams = TestDBUtility.getProductRow();
//        l_productParams.setProductId(3304140763000L);
//        try
//        {
//            TestDBUtility.deleteAll(l_productParams.getRowType());
//            TestDBUtility.insertWithDel(l_productParams);
//
//            l_bondProduct = new WEB3BondProduct(l_BondProductParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.bd.WEB3BondProductManager",
//            "getBondProduct",
//            new Class[] {Institution.class, String.class},
//            l_bondProduct);
//        
//        WEB3AdminInformProfDistTransferInfo info = new WEB3AdminInformProfDistTransferInfo();
//        
//        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
//            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
//        
//        String l_strProductCode = "2345";
//        try
//        {
//            Method thisMethod = 
//                WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
//                    "setProductInfo",
//                    new Class[]{
//                        String.class,
//                        String.class,
//                        String.class,
//                        WEB3AdminInformProfDistTransferInfo.class});
//            thisMethod.setAccessible(true);
//            thisMethod.invoke(
//                l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"111", null, l_strProductCode, info});
//
//            Object[] l_objParamsValue = 
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.bd.WEB3BondProductManager",
//                    "getBondProduct",
//                new Class[] {Institution.class, String.class}).getFirstCalled();
//
//            assertEquals("連絡管理", info.productName);
//            assertTrue(l_objParamsValue[0] instanceof Institution);
//            assertEquals(l_strProductCode, l_objParamsValue[1]);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    public void testIsMutualProduct_0001()
    {
        try
        {
        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
        Method thisMethod = 
            WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "isMutualProduct",
                new Class[]{String.class});
        thisMethod.setAccessible(true);
        Boolean l_blnFlag = (Boolean)thisMethod.invoke(
            l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{null});
        
        assertFalse(l_blnFlag.booleanValue());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    
    public void testIsMutualProduct_0002()
    {
        try
        {
        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
        Method thisMethod = 
            WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "isMutualProduct",
                new Class[]{String.class});
        thisMethod.setAccessible(true);
        Boolean l_blnFlag = (Boolean)thisMethod.invoke(
            l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"234"});
        
        assertTrue(l_blnFlag.booleanValue());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    
    public void testIsMutualProduct_0003()
    {
        try
        {
        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
        Method thisMethod = 
            WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "isMutualProduct",
                new Class[]{String.class});
        thisMethod.setAccessible(true);
        Boolean l_blnFlag = (Boolean)thisMethod.invoke(
            l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"R34"});
        
        assertTrue(l_blnFlag.booleanValue());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    
    public void testIsMutualProduct_0004()
    {
        try
        {
        WEB3AdminInformProfDistSellTransSrcListServiceImpl l_adminInformProfDistSellTransSrcListServiceImpl = 
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl();
        Method thisMethod = 
            WEB3AdminInformProfDistSellTransSrcListServiceImpl.class.getDeclaredMethod(
                "isMutualProduct",
                new Class[]{String.class});
        thisMethod.setAccessible(true);
        Boolean l_blnFlag = (Boolean)thisMethod.invoke(
            l_adminInformProfDistSellTransSrcListServiceImpl, new Object[]{"334"});
        
        assertFalse(l_blnFlag.booleanValue());
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }

}
@
