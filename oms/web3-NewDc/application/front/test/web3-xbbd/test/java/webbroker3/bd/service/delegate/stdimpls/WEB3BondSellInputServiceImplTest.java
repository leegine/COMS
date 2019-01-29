head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondSellInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : //TODO(WEB3BondSellInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 ™GˆÌ (’†u) V‹Kì¬
Revesion History : 2007/07/05  yWEB3zyCITIƒtƒƒ“ƒg“±“üiÂŒ”jzˆÄŒæÁC’{‘ãáù
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;

/**
 * XXXXXXƒNƒ‰ƒX//TODO
 *
 * @@author ™GˆÌ(’†u)
 * @@version 1.0
 */
public class WEB3BondSellInputServiceImplTest extends TestBaseForMock
{

    WEB3BondSellInputServiceImpl l_impl = new WEB3BondSellInputServiceImpl();
    public WEB3BondSellInputServiceImplTest(String arg0)
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

//    /*
//     * 
//     */
//    public void testExcute()
//    {
//        WEB3BondSellInputRequest l_bondSellInputRequest = new WEB3BondSellInputRequest();
//        l_bondSellInputRequest.id = "1001";
//        try
//        {
//            for (int i = 0; i < 3; i++)
//            {
//                MOCK_MANAGER.setIsMockUsed(true);
//                AssetParams l_assetParams = TestDBUtility.getAssetRow();
//                l_assetParams.setProductType(ProductTypeEnum.BOND);
//                l_assetParams.setAssetId(1001);
//                l_assetParams.setProductId(123456);
//                l_assetParams.setQuantity(5000);
//                TestDBUtility.insertWithDel(l_assetParams);
//
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductType(ProductTypeEnum.BOND);
//                l_productParams.setProductId(123456);
//                TestDBUtility.insertWithDel(l_productParams);
//
//                TestDBUtility.deleteAll(BondProductParams.TYPE);
//                BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//                l_bondProductParams.setTradingTimeCheckDiv("1");
//                l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
//                l_bondProductParams.setTradeType("4");
//                l_bondProductParams.setTradeHandleDiv("2");
//                l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//                l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20080908", "yyyyMMdd"));
//                l_bondProductParams.setProductId(123456);
//                l_bondProductParams.setParValue(5);
//                l_bondProductParams.setCurrencyCode("01");
//                if (i == 0)
//                {
//                    l_bondProductParams.setSellPrice(0);
//                }
//                else if (i == 1)
//                {
//                    l_bondProductParams.setSellPrice(100);
//                }
//                else if (i == 2)
//                {
//                    l_bondProductParams.setSellPrice(null);
//                }
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
//                GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
//                l_genCurrencyParams.setInstitutionCode("0D");
//                l_genCurrencyParams.setCurrencyCode("01");
//                TestDBUtility.insertWithDel(l_genCurrencyParams);
//
//                BranchParams l_branchParams = TestDBUtility.getBranchRow();
//                l_branchParams.setBranchId(33381L);
//                TestDBUtility.insertWithDel(l_branchParams);
//                
//                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//                l_institutionParams.setInstitutionId(33L);
//                TestDBUtility.insertWithDel(l_institutionParams);
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
//
//                WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//                WEB3BondSellInputResponse l_response =
//                    (WEB3BondSellInputResponse)l_impl.execute(l_bondSellInputRequest);
//
//                if (i == 0)
//                {
//                    assertEquals(null, l_response.yenEstimatedAsset); 
//                    assertEquals(null, l_response.foreignEstimatedAsset);
//                    assertEquals(null, l_response.sellEvaluationPrice);
//                }
//                if (i == 1)
//                {
//                    assertEquals("0", l_response.yenEstimatedAsset);
//                    assertEquals("100000", l_response.foreignEstimatedAsset);
//                    assertEquals("100", l_response.sellEvaluationPrice);
//                }
//                if (i == 2)
//                {
//                    assertEquals(null, l_response.yenEstimatedAsset); 
//                    assertEquals(null, l_response.foreignEstimatedAsset);
//                    assertEquals(null, l_response.sellEvaluationPrice);
//                }
//                
//                //–ñ’è“ú = ÂŒ”–ñ’è“úî•ñ.–ñ’è“ú
//                assertEquals(new Date(l_bizDate.getTime()), l_response.executionUpdateDate);
//
//                //Œ»’n–ñ’è“ú = ÂŒ”–ñ’è“úî•ñ.Œ»’n–ñ’è“ú
//                assertEquals(null, l_response.foreignExecutionDate);
//
//                //ó“n“ú = ÂŒ”–ñ’è“úî•ñ.ó“n“ú
//                assertEquals("20080908", WEB3DateUtility.formatDate(l_response.deliveryDate, "yyyyMMdd"));
//
//                //Œ»’nó“n“ú = ÂŒ”–ñ’è“úî•ñ.Œ»’nó“n“ú
//                assertEquals(null, l_response.foreignDeliveryDate);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            fail();
//        }
//    }
    public void testTest()
    {
        
    }
    
}
@
