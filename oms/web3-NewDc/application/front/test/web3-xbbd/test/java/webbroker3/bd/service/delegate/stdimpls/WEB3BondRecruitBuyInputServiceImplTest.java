head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondRecruitBuyInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondRecruitBuyInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 徐宏偉 (中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3BondRecruitBuyInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyInputServiceImplTest.class);

    WEB3BondRecruitBuyInputServiceImpl l_impl = new WEB3BondRecruitBuyInputServiceImpl();
    
    WEB3BondRecruitBuyInputServiceImplForTest l_bondRecruitBuyInputServiceImplForTest = new WEB3BondRecruitBuyInputServiceImplForTest();
    public WEB3BondRecruitBuyInputServiceImplTest(String arg0)
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
//     * 債券銘柄.買付単価=="0"または、"null"の場合
//     * 買付単価 = null
//     */
//    public void testExcute_case001()
//    {
//        final String STR_METHOD_NAME = " testExcute_case001";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3BondApplyBuyInputRequest l_bondApplyBuyInputrequest = new WEB3BondApplyBuyInputRequest();
//        l_bondApplyBuyInputrequest.tradeDiv = "2";
//
//        try
//        {
//            for (int i = 0; i < 3; i++)
//            {
//                MOCK_MANAGER.setIsMockUsed(true);
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductId(3304140763000L);
//                TestDBUtility.insertWithDel(l_productParams);
//                l_bondApplyBuyInputrequest.productId = "3304140763000";
//
//                BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//                l_bondProductParams.setTradingTimeCheckDiv("1");
//                l_bondProductParams.setProductId(3304140763000L);
//                l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
//                l_bondProductParams.setTradeType("4");
//                l_bondProductParams.setTradeHandleDiv("2");
//                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20080908", "yyyyMMdd"));
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
//                
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
//
//                WEB3BondApplyBuyInputResponse l_response =
//                    (WEB3BondApplyBuyInputResponse)l_impl.execute(l_bondApplyBuyInputrequest);
//
//                if (i == 0)
//                {
//                    assertEquals(null, l_response.buyPrice);
//                }
//                else if (i == 1)
//                {
//                    assertEquals("100", l_response.buyPrice);
//                }
//                else if (i == 2)
//                {
//                    assertEquals(null, l_response.buyPrice);
//                }
//                //約定日 = 債券約定日情報.約定日
//                assertEquals(new Date(l_bizDate.getTime()), l_response.executionUpdateDate);
//
//                //現地約定日 = 債券約定日情報.現地約定日
//                assertEquals(null, l_response.foreignExecutionDate);
//
//                //受渡日 = 債券約定日情報.受渡日
//                assertEquals("20080908", WEB3DateUtility.formatDate(l_response.deliveryDate, "yyyyMMdd"));
//
//                //現地受渡日 = 債券約定日情報.現地受渡日
//                assertEquals(null, l_response.foreignDeliveryDate);
//
//                //入金日 = 債券約定日情報.入金日
//                assertEquals("20080908", WEB3DateUtility.formatDate(l_response.paymentDate, "yyyyMMdd"));
//
//            }
//            
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
    public void testTest()
    {
        
    }

    public class WEB3BondRecruitBuyInputServiceImplForTest extends WEB3BondRecruitBuyInputServiceImpl
    {
        
    }
}
@
