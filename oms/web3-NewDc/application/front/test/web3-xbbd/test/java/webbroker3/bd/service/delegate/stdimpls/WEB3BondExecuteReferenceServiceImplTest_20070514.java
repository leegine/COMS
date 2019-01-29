head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondExecuteReferenceServiceImplTest_20070514.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondExecuteReferenceServiceImplTest_20070514.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/14 徐宏偉 (中訊) 新規作成
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.common.WEB3SystemLayerException;
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
public class WEB3BondExecuteReferenceServiceImplTest_20070514 extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceServiceImplTest_20070514.class);

    WEB3BondExecuteReferenceServiceImpl l_impl = new WEB3BondExecuteReferenceServiceImpl();
    WEB3BondExecuteReferenceServiceImplForTest l_implForTest = new WEB3BondExecuteReferenceServiceImplForTest();

    public WEB3BondExecuteReferenceServiceImplTest_20070514(String arg0)
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
     *確認結果看輸出
     *
     */
    public void testGetExecuteReference()
    {
        final String STR_METHOD_NAME = " testGetExecuteReference";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            for (int i = 0; i < 2; i++)
            {
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                BondOrderUnitParams l_bondOrderunitParams = TestDBUtility.getBondOrderUnitRow();
                l_bondOrderunitParams.setOrderUnitId(1232);
                l_bondOrderunitParams.setOrderId(123);
                l_bondOrderunitParams.setAccountId(333812512203L);
                l_bondOrderunitParams.setSubAccountId(33381251220301L);
                l_bondOrderunitParams.setOrderType(OrderTypeEnum.BOND_BUY);
                l_bondOrderunitParams.setDealType("92");
                l_bondOrderunitParams.setOrderExecStatus("3");
                l_bondOrderunitParams.setBizDate("20080908");
                l_bondOrderunitParams.setExecDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
                l_bondOrderunitParams.setForeignExecDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
                l_bondOrderunitParams.setDeliveryDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
                l_bondOrderunitParams.setForeignDeliveryDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
                l_bondOrderunitParams.setPaymentDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
                
                if (i == 0)
                {
                    l_bondOrderunitParams.setLimitPrice(0);
                }
                else if (i == 1)
                {
                    l_bondOrderunitParams.setLimitPrice(10);
                }
                
                l_bondOrderunitParams.setProductId(3304140763000L);

                TestDBUtility.insertWithDel(l_bondOrderunitParams);

                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setProductId(3304140763000L);
                TestDBUtility.insertWithDel(l_productParams);

                BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
                l_bondProductParams.setTradingTimeCheckDiv("1");
                l_bondProductParams.setProductId(3304140763000L);
                l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
                l_bondProductParams.setTradeType("4");
                l_bondProductParams.setTradeHandleDiv("2");
                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20080908", "yyyyMMdd"));
                l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
                l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));

                TestDBUtility.insertWithDel(l_bondProductParams);

                l_bondProductParams.setInstitutionCode("0D");
                l_bondProductParams.setTradeHandleDiv("2");
                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20050505", "yyyyMMdd"));
                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20090505", "yyyyMMdd"));

                l_bondProductParams.setProductCode("123");
                l_bondProductParams.setHostProductName1("33334");
                l_bondProductParams.setInterestPaymentDay1st("0808");
                l_bondProductParams.setInterestPaymentDay2nd("0808");
                l_bondProductParams.setCoupon(1);
                l_bondProductParams.setIsinCode("456");
                l_bondProductParams.setProductId(2222222);
                TestDBUtility.insertWithDel(l_bondProductParams);

                WEB3GentradeTradingTimeManagementForMock.getClendarContext();
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(333812512203L);
                TestDBUtility.insertWithDel(l_mainAccountParams);

                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(333812512203L);
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                TestDBUtility.insertWithDel(l_subAccountParams);

                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                l_branchParams.setBranchId(33381L);
                TestDBUtility.insertWithDel(l_branchParams);

                BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
                l_bondBranchConditionParams.setBranchId(33381L);

                TestDBUtility.insertWithDel(l_bondBranchConditionParams);

                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long(333812512203L));
                Date l_bizDate = WEB3DateUtility.getDate("20080908", "yyyyMMdd");
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateSubAccountForTrading",
                    new Class[] {SubAccount.class}, OrderValidationResult.VALIDATION_OK_RESULT);

                WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
                l_tradingPowerResult.setResultFlg(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOtherTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Date.class}, new Double(133));
                
                WEB3BondExecuteReferenceRequest l_request = new WEB3BondExecuteReferenceRequest();
                l_request.referenceType = "0";
                l_request.pageIndex = "1";
                l_request.pageSize = "1";
                WEB3BondSortKey[] l_sortKey = new WEB3BondSortKey[1];
                l_sortKey[0] = new WEB3BondSortKey();
                l_sortKey[0].keyItem = "productName";
                l_sortKey[0].ascDesc = "A"; 
                l_request.sortKeys = l_sortKey;
                
                WEB3BondExecuteReferenceResponse l_response =
                    (WEB3BondExecuteReferenceResponse)this.l_implForTest.getExecuteReference(l_request);

                if (i == 0)
                {
                    System.out.println("pageIndex=" + l_response.pageIndex);
                    System.out.println("totalPages=" + l_response.totalPages);
                    System.out.println("totalRecords=" + l_response.totalRecords);
                    System.out.println("details=" + l_response.details);
                }
                else if (i == 1)
                {
                    System.out.println("----------------------------------");
                    System.out.println("pageIndex=" + l_response.pageIndex);
                    System.out.println("totalPages=" + l_response.totalPages);
                    System.out.println("totalRecords=" + l_response.totalRecords);
                    System.out.println("details=" + l_response.details);
                }
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3BondExecuteReferenceServiceImplForTest extends WEB3BondExecuteReferenceServiceImpl
    {
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
    }
}
@
