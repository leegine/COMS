head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundOrderManagerReusableValidationsCheckForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託発注審査個別チェックForMock(WEB3MutualFundOrderManagerReusableValidationsCheckForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託発注審査個別チェックForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManagerReusableValidationsCheckForMock extends
    WEB3MutualFundOrderManagerReusableValidationsCheck
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundOrderManagerReusableValidationsCheckForMock.class);

    /**
     * (validate緊急停止(Mock))<BR>
     * @@param l_mutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取　@5：募集<BR>
     * @@throws WEB3BaseException
     */
    public void validateEmergencyStop(
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strProcessDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEmergencyStop(WEB3MutualFundProduct ,String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateEmergencyStop",
            new Class[] {WEB3MutualFundProduct.class, String.class},
            new Object[]{l_mutualFundProduct, l_strProcessDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateEmergencyStop",
            new Class[] {WEB3MutualFundProduct.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateEmergencyStop(l_mutualFundProduct, l_strProcessDiv);
    }

    /**
     * (validate外貨MMF二重注文(Mock))<BR>
     * 当該顧客で、すでに外貨MMFの注文が存在するかチェックする。<BR>
     * @@param l_subAccunt - 補助口座<BR>
     * 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * 拡張投信銘柄<BR>
     * @@param l_datBizDate - 発注日<BR>
     * 発注日<BR>
     * @@throws WEB3BaseException 
     */
    public void validateFrgnMmfDoubleOrder(
        SubAccount l_subAccunt,
        WEB3MutualFundProduct l_mfProduct,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFrgnMmfDoubleOrder(SubAccount, WEB3MutualFundProduct, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateFrgnMmfDoubleOrder",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
            new Object[]{l_subAccunt, l_mfProduct, l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "validateFrgnMmfDoubleOrder",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class}).asVoid();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateFrgnMmfDoubleOrder(l_subAccunt, l_mfProduct, l_datBizDate);
    }

    /**
     * 当クラスのインスタンスを登録するstaticメソッド。<BR>
     */
    public static void register()
    {
        final String STR_METHOD_NAME = "register()-->ForMock";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFundOrderManagerReusableValidationsCheckForMock.setInstance(
            new WEB3MutualFundOrderManagerReusableValidationsCheckForMock());
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is解約口数拘束率超過(Mock))<BR>
     * @@param l_subAccunt - 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * @@param l_swtProduct - 銘柄（乗換先）<BR>
     * @@param l_strAssetId - 資産ID<BR>
     * @@param l_dblSellPossQty - 解約可能残高口数<BR>
     * @@param l_strProcessDiv - 処理区分<BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_strSpecifyMethod - 指定方法@<BR>
     * @@param l_strSettleMethod - 決済方法@<BR>
     * @@param l_strRequestMethod - 請求方法@<BR>
     * @@param l_strAccountDiv - 口座区分<BR>
     * @@param l_datBizDate - 発注日<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isSellQtyLimitRateExcess(
        SubAccount l_subAccunt, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        String l_strAssetId, 
        double l_dblSellPossQty, 
        String l_strProcessDiv, 
        double l_dblQuantity, 
        String l_strSpecifyMethod, 
        String l_strSettleMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isSellQtyLimitRateExcess(SubAccount, WEB3MutualFundProduct, " +
            "WEB3MutualFundProduct, String, double, String, double, " +
            "String, String, String, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "isSellQtyLimitRateExcess",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class},
            new Object[]{
                l_mfProduct, 
                l_swtProduct, 
                l_strAssetId, 
                new Double(l_dblSellPossQty), 
                l_strProcessDiv, 
                new Double(l_dblQuantity), 
                l_strSpecifyMethod, 
                l_strSettleMethod, 
                l_strRequestMethod, 
                l_strAccountDiv, 
                l_datBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
            "isSellQtyLimitRateExcess",
            new Class[] {
                SubAccount.class,
                WEB3MutualFundProduct.class,
                WEB3MutualFundProduct.class,
                String.class,
                double.class,
                String.class,
                double.class,
                String.class,
                String.class,
                String.class,
                String.class,
                Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "isSellQtyLimitRateExcess",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            return (boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "isSellQtyLimitRateExcess",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    WEB3MutualFundProduct.class,
                    String.class,
                    double.class,
                    String.class,
                    double.class,
                    String.class,
                    String.class,
                    String.class,
                    String.class,
                    Date.class}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isSellQtyLimitRateExcess(
            l_subAccunt, 
            l_mfProduct, 
            l_swtProduct, 
            l_strAssetId, 
            l_dblSellPossQty, 
            l_strProcessDiv, 
            l_dblQuantity, 
            l_strSpecifyMethod, 
            l_strSettleMethod, 
            l_strRequestMethod, 
            l_strAccountDiv, 
            l_datBizDate);
    }
}
@
