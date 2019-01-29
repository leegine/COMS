head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundOrderManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信注文マネージャForMock(WEB3MutualFundOrderManagerForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信注文マネージャForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManagerForMock extends WEB3MutualFundOrderManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundOrderManagerForMock.class);

    /**
     * (get注文単位(Mock))<BR>
     * 投信注文単位オブジェクトを返す。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_strRequestNumber - 識別コード<BR>
     */
    public MutualFundOrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber)
        throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(String , String , String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderUnit",
            new Class[] {String.class, String.class, String.class},
            new Object[]{l_strInstitutionCode, l_strBranchCode, l_strRequestNumber});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderUnit",
            new Class[] {String.class, String.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            return (MutualFundOrderUnit)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getOrderUnit(l_strInstitutionCode, l_strBranchCode, l_strRequestNumber);
    }

    /**
     * (validate新規注文(Mock))<BR>
     * （validateNewOrderのオーバーロード）<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_web3MutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取 5：募集 <BR>
     * @@param l_strPaymentMethod - (受渡方法@)<BR>
     * 1：銀行振込み　@2：証券口座入金<BR>
     * 
     * @@param l_strDesignateMethod - (指定方法@)<BR>
     * 2：全部　@3：金額　@4：口数<BR>
     * @@param l_switchingSubject - (乗換先銘柄)<BR>
     * @@param l_taxType - (税区分) <BR>
     * 
     * 乗換先の拡張投信銘柄オブジェクト<BR>
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strPaymentMethod,
        String l_strDesignateMethod,
        WEB3MutualFundProduct l_switchingSubject,
        TaxTypeEnum l_taxType,
        String l_strsettleDiv)     
        
    {
        String STR_METHOD_NAME = "validateNewOrder()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateNewOrder",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                double.class, String.class,
                String.class, String.class,
                WEB3MutualFundProduct.class,
                TaxTypeEnum.class,
                String.class},
            new Object[]{
                l_subAccount,
                l_web3MutualFundProduct,
                new Double(l_dblOrderQuantity),
                l_strProcessDiv,
                l_strPaymentMethod,
                l_strDesignateMethod,
                l_switchingSubject,
                l_taxType,
                l_strsettleDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateNewOrder",
            new Class[] {SubAccount.class,
                    WEB3MutualFundProduct.class,
                    double.class, String.class,
                    String.class, String.class,
                    WEB3MutualFundProduct.class,
                    TaxTypeEnum.class,
                    String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);

            //3)MockFor --〉 asVoid
            return (NewOrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "validateNewOrder",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    double.class, String.class,
                    String.class, String.class,
                    WEB3MutualFundProduct.class,
                    TaxTypeEnum.class,
                    String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateNewOrder(l_subAccount,
            l_web3MutualFundProduct,
            l_dblOrderQuantity,
            l_strProcessDiv,
            l_strPaymentMethod,
            l_strDesignateMethod,
            l_switchingSubject,
            l_taxType,
            l_strsettleDiv);
    }

    /**
     * (calc概算受渡代金(Mock))<BR>
     * 　@(3-3) 概算受渡代金に、引数の注文数量をセットする。<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_web3MutualFundProduct - (拡張投信銘柄)<BR>
     * @@param l_strProcessDiv - 処理区分<BR>
     * <BR>
     * １：買付<BR>
     * ２：解約<BR>
     * ３：乗換<BR>
     * ４：募集<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * <BR>
     * 口数指定の場合は注文口数、金額指定の場合は注文金額<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * <BR>
     * ３：金額指定<BR>
     * ４：口数指定<BR>
     * @@param l_strSettlementMethod - 決済方法@<BR>
     * １：円貨<BR>
     * ２：外貨<BR>
     * @@return webbroker3.mf.WEB3MutualFundEstimatedPrice
     */
    protected WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class, String.class,
                String.class},
            new Object[]{
                l_subAccount,
                l_web3MutualFundProduct,
                l_strProcessDiv,
                new Double(l_dblOrderQuantity),
                l_strDesignateMethod,
                l_strSettlementMethod});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class, String.class,
                String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class, String.class,
                    String.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (WEB3MutualFundEstimatedPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class, String.class,
                    String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimateDeliveryAmount(l_subAccount,
            l_web3MutualFundProduct,
            l_strProcessDiv,
            l_dblOrderQuantity,
            l_strDesignateMethod,
            l_strSettlementMethod);
    }

    /**
     * (calc概算受渡代金(Mock))
     * 概算受渡代金（概算売買代金、概算売買口数）を算出し、 <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_swtProduct - 銘柄（乗換先）
     * @@param l_strProcessDiv - 処理区分
     * @@param l_dblOrderQuantity - 注文数量
     * @@param l_strDesignateMethod - 指定方法@
     * @@param l_strSettlementMethod - 決済方法@
     * @@param l_strRequestMethod - 請求方法@
     * @@param l_strAccountDiv - 口座区分
     * @@param l_strOrderChannel - 注文チャネル
     * @@param l_datBizDate - 発注日
     * @@throws WEB3BaseException
     */
    public WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundProduct l_swtProduct, 
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        String l_strOrderChannel, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount(SubAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundProduct, String," +
            "double, String, String, String, String, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class},
            new Object[]{
                l_subAccount,
                l_mutualFundProduct,
                l_swtProduct, 
                l_strProcessDiv,
                new Double(l_dblOrderQuantity),
                l_strDesignateMethod,
                l_strSettlementMethod, 
                l_strRequestMethod, 
                l_strAccountDiv, 
                l_strOrderChannel, 
                l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "calcEstimateDeliveryAmount",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (WEB3MutualFundEstimatedPrice)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "calcEstimateDeliveryAmount",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcEstimateDeliveryAmount(
            l_subAccount,
            l_mutualFundProduct,
            l_swtProduct, 
            l_strProcessDiv,
            l_dblOrderQuantity,
            l_strDesignateMethod,
            l_strSettlementMethod, 
            l_strRequestMethod, 
            l_strAccountDiv, 
            l_strOrderChannel, 
            l_datBizDate);
    }

    public OrderSubmissionResult submitNewOrder(
        SubAccount subAccount,
        ProductTypeEnum productType,
        NewOrderSpec inSpec,
        long orderId,
        String tradingPassword,
        boolean skipOrderValidation)
    {

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "submitNewOrder",
            new Class[] {
                SubAccount.class,
                ProductTypeEnum.class,
                NewOrderSpec.class,
                long.class,
                String.class,
                boolean.class},
            new Object[]{
                subAccount,
                productType,
                inSpec,
                new Long(orderId),
                tradingPassword,
                new Boolean(skipOrderValidation)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "submitNewOrder",
            new Class[] {
                SubAccount.class,
                ProductTypeEnum.class,
                NewOrderSpec.class,
                long.class,
                String.class,
                boolean.class}))
        {

            //2)MockFor --〉 asObject
            return (OrderSubmissionResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "submitNewOrder",
                new Class[] {
                    SubAccount.class,
                    ProductTypeEnum.class,
                    NewOrderSpec.class,
                    long.class,
                    String.class,
                    boolean.class}).asObject();
        }

        return super.submitNewOrder(
                subAccount,
                productType,
                inSpec,
                orderId,
                tradingPassword,
                skipOrderValidation);
    }

    /**
     * (validate取消注文(Mock))<BR>
     * （validateCancelOrderの実装）<BR>
     * @@param l_mutualCancelOrderSpec - 投信取消注文内容<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_mutualCancelOrderSpec)
    {
        String STR_METHOD_NAME = "validateCancelOrder()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class},
            new Object[]{l_subAccount, l_mutualCancelOrderSpec});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "validateCancelOrder",
            new Class[] {SubAccount.class, CancelOrderSpec.class}))
        {

            //2)MockFor --〉 asObject
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "validateCancelOrder",
                new Class[] {SubAccount.class, CancelOrderSpec.class}).asObject();
        }

        return super.validateCancelOrder(l_subAccount, l_mutualCancelOrderSpec);
    }

    /**
     * (get注文数量区分(Mock))<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位オブジェクト<BR>
     * @@return String
     */
    public String getOrderQuantityDiv(MutualFundOrderUnit l_mutualFundOrderUnit)            
    {
        String STR_METHOD_NAME = "getOrderQuantityDiv()-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderQuantityDiv",
            new Class[] {MutualFundOrderUnit.class},
            new Object[]{l_mutualFundOrderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getOrderQuantityDiv",
            new Class[] {MutualFundOrderUnit.class}))
        {

            //2)MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderQuantityDiv",
                new Class[] {MutualFundOrderUnit.class}).asObject();
        }

        return super.getOrderQuantityDiv(l_mutualFundOrderUnit);
    }

    /**
     * (get概算受渡代金通貨コード(Mock))<BR>
     * 投信注文単位より、その注文の概算数量区分を判定して返却する。<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位オブジェクト<BR>
     * @@return String
     */
    public String getEstimateDeliveryAmountCurrencyCode(
        MutualFundOrderUnit l_mutualFundOrderUnit)
    {
        String STR_METHOD_NAME = "getEstimateDeliveryAmountCurrencyCode()-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getEstimateDeliveryAmountCurrencyCode",
            new Class[] {MutualFundOrderUnit.class},
            new Object[]{l_mutualFundOrderUnit});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManager",
            "getEstimateDeliveryAmountCurrencyCode",
            new Class[] {MutualFundOrderUnit.class}))
        {
            //3)MockFor --〉 asObject
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getEstimateDeliveryAmountCurrencyCode",
                new Class[] {MutualFundOrderUnit.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getEstimateDeliveryAmountCurrencyCode(l_mutualFundOrderUnit);
    }

}
@
