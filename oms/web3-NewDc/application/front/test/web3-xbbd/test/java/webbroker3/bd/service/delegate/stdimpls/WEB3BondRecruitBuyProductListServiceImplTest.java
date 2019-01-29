head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondRecruitBuyProductListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付銘柄一覧サービスImplのテスト(WEB3BondRecruitBuyProductListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/10 徐宏偉 (中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
Revesion History : 2007/07/17 周墨洋 (中訊) 仕様変更・モデル194
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.enum.Enumerated;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3BondRecruitBuyProductListServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyProductListServiceImplTest.class);

    private WEB3BondRecruitBuyProductListServiceImpl l_bondRecruitBuyProductListServiceImpl = null;

    public WEB3BondRecruitBuyProductListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
//    /*
//     * 
//     */
//    public void testCreateQueryString_case001()
//    {
//        final String STR_METHOD_NAME = " testCreateQueryString_case001";
//        log.entering(STR_METHOD_NAME);
//
//        for (int i = 0; i < 3; i++)
//        {
//            WEB3BondApplyBuyProductSearchConditionInfo l_bondApplyBuyProductSearchConditionInfo =
//                new WEB3BondApplyBuyProductSearchConditionInfo();
//            //債券応募/買付銘柄検索条件.照会区分 == ”応募一覧”の場合
//            if (i == 0)
//            {
//                l_bondApplyBuyProductSearchConditionInfo.referenceType = "1";    
//            }
//            else if (i == 1)
//            {
//                //債券応募/買付銘柄検索条件.通貨コード ≠ null の場合
//                //　@債券応募/買付銘柄検索条件.通貨コード == "T0" の場合
//                //　@　@　@　@" and ( 通貨コード is null or 通貨コード = ? ) " 
//                
//                //債券応募/買付銘柄検索条件.銘柄コード（WEB3) ≠ null の場合
//                //　@" and 銘柄コード(WEB3) = ? "
//                l_bondApplyBuyProductSearchConditionInfo.productCodeWEB3 = "123";
//
//                //債券応募/買付銘柄検索条件.HOST銘柄名1 ≠ null の場合
//                //　@" and HOST銘柄名1 like ? "
//                l_bondApplyBuyProductSearchConditionInfo.hostProductName1 = "333";
//
//                //債券応募/買付銘柄検索条件.発行日 ≠ null の場合
//                //　@" and 発行日 = ? "
//                l_bondApplyBuyProductSearchConditionInfo.issueDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
//
//                //債券応募/買付銘柄検索条件.償還日 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and 償還日 = ? "
//                l_bondApplyBuyProductSearchConditionInfo.maturityDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
//
//                //債券応募/買付銘柄検索条件.利払日 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and ( 利払日1 = ?　@or 利払日2 = ? ) "
//                l_bondApplyBuyProductSearchConditionInfo.interestPaymentDay = "20080808";
//
//
//                //債券応募/買付銘柄検索条件.利率 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and 利率 = ? "
//                l_bondApplyBuyProductSearchConditionInfo.coupon = "1";
//                
//                //債券応募/買付銘柄検索条件.ISINコード ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and ISINコード = ? "
//                l_bondApplyBuyProductSearchConditionInfo.isinCode = "456";
//            l_bondApplyBuyProductSearchConditionInfo.currencyCode = "T0";
//
//                //債券応募/買付銘柄検索条件.照会区分 == ”買付一覧” の場合
//                //　@" and 売買区分 in (?, ?) "
//                l_bondApplyBuyProductSearchConditionInfo.referenceType = "2";    
//            }
//            else if (i == 2)
//            {
//                //債券応募/買付銘柄検索条件.照会区分 == ”応募/買付一覧” の場合
//                //　@" and 売買区分 in (?, ?, ?) "  
//                l_bondApplyBuyProductSearchConditionInfo.referenceType = "3";    
//                
//                //債券応募/買付銘柄検索条件.通貨コード ≠ null の場合
//                //　@それ以外の場合 
//            l_bondApplyBuyProductSearchConditionInfo.currencyCode = "12";
//            }
//           
//            String l_strReturn =
//                this.l_impl.createQueryString(l_bondApplyBuyProductSearchConditionInfo);
//
//            String l_strQuery0 = " trade_handle_div = ? and trade_start_date <= ?" +
//            " and trade_end_date > ? and trade_type = ? ";
//
//            String l_strQuery1 = " trade_handle_div = ? and trade_start_date <= ?" +
//                " and trade_end_date > ? and trade_type in (?, ?)" +
//                " and (currency_code is null or currency_code = ?) " +
//                "and product_code = ?  and host_product_name_1 like ? " +
//                "and issue_date = ?  and maturity_date = ?  and " +
//                "( interest_payment_day_1st = ? or interest_payment_day_2nd = ? )  " +
//                "and coupon = ?  and isin_code = ?  ";
//            
//            String l_strQuery2 = " trade_handle_div = ? and trade_start_date <= ?" +
//            " and trade_end_date > ? and trade_type in (?, ?, ?) and currency_code = ? ";
//
//            if (i==0)
//            {
//                assertEquals(l_strQuery0, l_strReturn);
//            }
//            else if (i == 1)
//            {
//                assertEquals(l_strQuery1, l_strReturn);  
//            }
//            else if (i == 2)
//            {
//                assertEquals(l_strQuery2, l_strReturn);
//            }
//            
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testCreateQueryDataContainer()
//    {
//        final String STR_METHOD_NAME = " testCreateQueryDataContainer";
//        log.entering(STR_METHOD_NAME);
//
//        for (int i = 0; i < 3; i++)
//        {
//            WEB3BondApplyBuyProductSearchConditionInfo l_bondApplyBuyProductSearchConditionInfo =
//                new WEB3BondApplyBuyProductSearchConditionInfo();
//            //債券応募/買付銘柄検索条件.照会区分 == ”応募一覧”の場合
//            if (i == 0)
//            {
//                l_bondApplyBuyProductSearchConditionInfo.referenceType = "1";    
//            }
//            else if (i == 1)
//            {
//                //債券応募/買付銘柄検索条件.通貨コード ≠ null の場合
//                //　@債券応募/買付銘柄検索条件.通貨コード == "T0" の場合
//                //　@　@　@　@" and ( 通貨コード is null or 通貨コード = ? ) " 
//
//                //債券応募/買付銘柄検索条件.銘柄コード（WEB3) ≠ null の場合
//                //　@" and 銘柄コード(WEB3) = ? "
//                l_bondApplyBuyProductSearchConditionInfo.productCodeWEB3 = "123";
//
//                //債券応募/買付銘柄検索条件.HOST銘柄名1 ≠ null の場合
//                //　@" and HOST銘柄名1 like ? "
//                l_bondApplyBuyProductSearchConditionInfo.hostProductName1 = "333";
//
//                //債券応募/買付銘柄検索条件.発行日 ≠ null の場合
//                //　@" and 発行日 = ? "
//                l_bondApplyBuyProductSearchConditionInfo.issueDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
//
//                //債券応募/買付銘柄検索条件.償還日 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and 償還日 = ? "
//                l_bondApplyBuyProductSearchConditionInfo.maturityDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
//
//                //債券応募/買付銘柄検索条件.利払日 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and ( 利払日1 = ?　@or 利払日2 = ? ) "
//                l_bondApplyBuyProductSearchConditionInfo.interestPaymentDay = "20080808";
//
//
//                //債券応募/買付銘柄検索条件.利率 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and 利率 = ? "
//                l_bondApplyBuyProductSearchConditionInfo.coupon = "1";
//                
//                //債券応募/買付銘柄検索条件.ISINコード ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and ISINコード = ? "
//                l_bondApplyBuyProductSearchConditionInfo.isinCode = "456";
//            l_bondApplyBuyProductSearchConditionInfo.currencyCode = "T0";
//
//                //債券応募/買付銘柄検索条件.照会区分 == ”買付一覧” の場合
//                //　@" and 売買区分 in (?, ?) "
//                l_bondApplyBuyProductSearchConditionInfo.referenceType = "2";    
//            }
//            else if (i == 2)
//            {
//                //債券応募/買付銘柄検索条件.照会区分 == ”応募/買付一覧” の場合
//                //　@" and 売買区分 in (?, ?, ?) "  
//                l_bondApplyBuyProductSearchConditionInfo.referenceType = "3";    
//                
//                //債券応募/買付銘柄検索条件.通貨コード ≠ null の場合
//                //　@それ以外の場合 
//            l_bondApplyBuyProductSearchConditionInfo.currencyCode = "12";
//            }
//
//           
//            Object[] l_objReturn =
//                this.l_impl.createQueryDataContainer(l_bondApplyBuyProductSearchConditionInfo);
//
//
//            if (i==0)
//            {
//                assertEquals(4, l_objReturn.length);
//                assertEquals("2", l_objReturn[0]);
//                assertEquals("3", l_objReturn[3]);
//                System.out.println("で取得した現在日時1 " + l_objReturn[1]);
//                System.out.println("で取得した現在日時2 " + l_objReturn[2]);
//            }
//            else if (i == 1)
//            {
//                assertEquals(14, l_objReturn.length); 
//                
//                assertEquals("2", l_objReturn[0]);
//                assertEquals("1", l_objReturn[3]);
//                assertEquals("4", l_objReturn[4]);
//                assertEquals("T0", l_objReturn[5]);
//                assertEquals("123", l_objReturn[6]);
//                assertEquals("%333%", l_objReturn[7]);
//
//                assertEquals("20080808", l_objReturn[10]);
//                assertEquals("20080808", l_objReturn[11]);
//                assertEquals("1", l_objReturn[12]);
//                assertEquals("456", l_objReturn[13]);
//                System.out.println("で取得した現在日時1 " + l_objReturn[1]);
//                System.out.println("で取得した現在日時2 " + l_objReturn[2]);
//            }
//            else if (i == 2)
//            {
//                assertEquals(7, l_objReturn.length);
//                assertEquals("2", l_objReturn[0]);
//                assertEquals("3", l_objReturn[3]);
//                assertEquals("1", l_objReturn[4]);
//                assertEquals("4", l_objReturn[5]);
//                assertEquals("12", l_objReturn[6]);
//                System.out.println("で取得した現在日時1 " + l_objReturn[1]);
//                System.out.println("で取得した現在日時2 " + l_objReturn[2]);
//            }
//            
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testExcute()
//    {
//        final String STR_METHOD_NAME = " testCreateQueryDataContainer";
//        log.entering(STR_METHOD_NAME);
//
//        WEB3BondApplyBuyProductListRequest l_request = new WEB3BondApplyBuyProductListRequest();
//        
//        for (int i = 0; i < 3; i++)
//        {
//            l_request.bondApplyBuyProductSearchCondition =
//                new WEB3BondApplyBuyProductSearchConditionInfo();
//            //債券応募/買付銘柄検索条件.照会区分 == ”応募一覧”の場合
//            if (i == 0)
//            {
//                l_request.bondApplyBuyProductSearchCondition.referenceType = "1";    
//            }
//            else if (i == 1)
//            {
//                //債券応募/買付銘柄検索条件.通貨コード ≠ null の場合
//                //　@債券応募/買付銘柄検索条件.通貨コード == "T0" の場合
//                //　@　@　@　@" and ( 通貨コード is null or 通貨コード = ? ) " 
//
//                //債券応募/買付銘柄検索条件.銘柄コード（WEB3) ≠ null の場合
//                //　@" and 銘柄コード(WEB3) = ? "
//                l_request.bondApplyBuyProductSearchCondition.productCodeWEB3 = "123";
//
//                //債券応募/買付銘柄検索条件.HOST銘柄名1 ≠ null の場合
//                //　@" and HOST銘柄名1 like ? "
//                l_request.bondApplyBuyProductSearchCondition.hostProductName1 = "333";
//
//                //債券応募/買付銘柄検索条件.発行日 ≠ null の場合
//                //　@" and 発行日 = ? "
//                l_request.bondApplyBuyProductSearchCondition.issueDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
//
//                //債券応募/買付銘柄検索条件.償還日 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and 償還日 = ? "
//                l_request.bondApplyBuyProductSearchCondition.maturityDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
//
//                //債券応募/買付銘柄検索条件.利払日 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and ( 利払日1 = ?　@or 利払日2 = ? ) "
//                l_request.bondApplyBuyProductSearchCondition.interestPaymentDay = "0808";
//
//
//                //債券応募/買付銘柄検索条件.利率 ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and 利率 = ? "
//                l_request.bondApplyBuyProductSearchCondition.coupon = "1";
//                
//                //債券応募/買付銘柄検索条件.ISINコード ≠ null の場合、以下の文字列を最後尾に追加する。
//                //　@" and ISINコード = ? "
//                l_request.bondApplyBuyProductSearchCondition.isinCode = "456";
//                l_request.bondApplyBuyProductSearchCondition.currencyCode = "T0";
//
//                //債券応募/買付銘柄検索条件.照会区分 == ”買付一覧” の場合
//                //　@" and 売買区分 in (?, ?) "
//                l_request.bondApplyBuyProductSearchCondition.referenceType = "2";    
//            }
//            else if (i == 2)
//            {
//                //債券応募/買付銘柄検索条件.照会区分 == ”応募/買付一覧” の場合
//                //　@" and 売買区分 in (?, ?, ?) "  
//                l_request.bondApplyBuyProductSearchCondition.referenceType = "3";    
//                
//                //債券応募/買付銘柄検索条件.通貨コード ≠ null の場合
//                //　@それ以外の場合 
//                l_request.bondApplyBuyProductSearchCondition.currencyCode = "12";
//            }
//            l_request.pageIndex = "1";
//            l_request.pageSize = "1";
//
//            try
//            {
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductId(2222222);
//                TestDBUtility.insertWithDel(l_productParams);
//
//                BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//                l_bondProductParams.setTradingTimeCheckDiv("1");
//                l_bondProductParams.setProductId(3304140763000L);
//                l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
//                l_bondProductParams.setTradeType("4");
//                l_bondProductParams.setTradeHandleDiv("2");
//                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20080908", "yyyyMMdd"));
//                l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//                l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
//                if (i == 0)
//                {
//                    l_bondProductParams.setBuyPrice(0);
//                }
//                else if (i == 1)
//                {
//                    l_bondProductParams.setBuyPrice(100);
//                }
//                else if (i == 2)
//                {
//                    l_bondProductParams.setBuyPrice(null);
//                }
//
//                TestDBUtility.insertWithDel(l_bondProductParams);
// 
//                l_bondProductParams.setInstitutionCode("0D");
//                l_bondProductParams.setTradeHandleDiv("2");
//                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
//                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20090505", "yyyyMMdd"));
//                if (i == 0)
//                {
//                    l_bondProductParams.setTradeType("3");    
//                    l_bondProductParams.setCurrencyCode("T0");
//                }
//                else if (i == 1)
//                {
//                    l_bondProductParams.setTradeType("1");
//                    l_bondProductParams.setCurrencyCode("T0");
//                }
//                else 
//                {
//                    l_bondProductParams.setTradeType("5"); 
//                    l_bondProductParams.setCurrencyCode("12");
//                }
//
//                l_bondProductParams.setProductCode("123");
//                l_bondProductParams.setHostProductName1("33334");
//                l_bondProductParams.setInterestPaymentDay1st("0808");
//                l_bondProductParams.setInterestPaymentDay2nd("0808");
//                l_bondProductParams.setCoupon(1);
//                l_bondProductParams.setIsinCode("456");
//                l_bondProductParams.setProductId(2222222);
//                TestDBUtility.insertWithDel(l_bondProductParams);
//
//                WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//                l_mainAccountParams.setAccountId(333812512203L);
//                TestDBUtility.insertWithDel(l_mainAccountParams);
//
//                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//                l_subAccountParams.setAccountId(333812512203L);
//                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//                TestDBUtility.insertWithDel(l_subAccountParams);
//
//                BranchParams l_branchParams = TestDBUtility.getBranchRow();
//                l_branchParams.setBranchId(33381L);
//                TestDBUtility.insertWithDel(l_branchParams);
//
//                BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
//                l_bondBranchConditionParams.setBranchId(33381L);
//
//                TestDBUtility.insertWithDel(l_bondBranchConditionParams);
//                MOCK_MANAGER.setIsMockUsed(true);
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
//                    new Class[] {}, new Long(333812512203L));
//                Date l_bizDate = WEB3DateUtility.getDate("20080908", "yyyyMMdd");
//                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);
//                
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
//                    "validateSubAccountForTrading",
//                    new Class[] {SubAccount.class}, OrderValidationResult.VALIDATION_OK_RESULT);
//
//                WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
//                l_tradingPowerResult.setResultFlg(true);
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
//                    "getOtherTradingPower",
//                    new Class[] {WEB3GentradeSubAccount.class, Date.class}, new Double(133));
//                WEB3BondApplyBuyProductListResponse l_response =
//                    (WEB3BondApplyBuyProductListResponse)this.l_impl.execute(l_request);
//                if ( i == 0)
//                {
//                    assertEquals(null, l_response.productList[0].buyPrice);
//                    assertEquals("123", l_response.productList[0].productCodeWEB3);
//                    assertEquals("33334", l_response.productList[0].hostProductName1);
//                    assertEquals("456", l_response.productList[0].isinCode);
//                }
//                else if (i == 1)
//                {
//                    assertEquals("100", l_response.productList[0].buyPrice);
//                    assertEquals("123", l_response.productList[0].productCodeWEB3);
//                    assertEquals("33334", l_response.productList[0].hostProductName1);
//                    assertEquals("456", l_response.productList[0].isinCode);
//                }
//                else if (i == 2)
//                {
//                    assertEquals(null, l_response.productList);
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//                fail();
//            }
//            
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
    public void testTest()
    {
        
    }

    public void testCreateQueryString_case0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondRecruitBuyProductListServiceImpl =
            new WEB3BondRecruitBuyProductListServiceImpl();

        String l_strReferenceType = null;
        String l_strCurrencyCode = null;

        String expected =
            " trade_handle_div = ? and trade_start_date <= ? and trade_end_date > ? and bond_type = ?  ";
        String l_actual = null;

        l_actual =
            this.l_bondRecruitBuyProductListServiceImpl.createQueryString(
                l_strReferenceType,
                l_strCurrencyCode);

        assertEquals(expected, l_actual);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryData_case0001()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0001()";
        log.entering(TEST_END + STR_METHOD_NAME);

        this.l_bondRecruitBuyProductListServiceImpl =
            new WEB3BondRecruitBuyProductListServiceImpl();

        String l_strReferenceType = null;
        String l_strCurrencyCode = null;

        Object[] l_actual = null;

        l_actual =
            this.l_bondRecruitBuyProductListServiceImpl.createQueryDataContainer(
                l_strReferenceType,
                l_strCurrencyCode);

        assertEquals(4, l_actual.length);
        assertEquals("2", (String)l_actual[0]);
        assertNotNull((Timestamp)l_actual[1]);
        assertNotNull((Timestamp)l_actual[2]);
        assertEquals(BondTypeEnum.FOREIGN_BOND, (Enumerated)l_actual[3]);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

}
@
