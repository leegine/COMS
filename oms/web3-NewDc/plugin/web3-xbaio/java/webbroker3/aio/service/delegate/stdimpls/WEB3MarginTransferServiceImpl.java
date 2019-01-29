head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3MarginTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金への振替サービスImpl(WEB3MarginTransferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/12 張騰宇 (中訊) 新規作成 仕様変更モデル736 739 DB更新仕様No.140、141、142、143
Revision History : 2007/07/28 孟亜南 (中訊) 仕様変更モデル741
Revision History : 2007/08/01 張騰宇 (中訊) 仕様変更モデル748
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (保証金への振替サービスImpl)<BR>
 * 保証金への振替サービス実装クラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3MarginTransferServiceImpl implements WEB3MarginTransferService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginTransferServiceImpl.class);

    /**
     * (submit保証金振替)<BR>
     * 保証金への振替サービス処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「保証金への振替サービス」参照。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_dblCashinAmt - (入金額)<BR>
     * 入金額<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * 暗証番号<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者
     * @@throws WEB3BaseException
     */
    public void submitMarginTransfer(
        WEB3GentradeMainAccount l_mainAccount,
        Date l_datDeliveryDate,
        double l_dblCashinAmt,
        String l_strPassword,
        Trader l_trader) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitMarginTransfer(WEB3GentradeMainAccount, Date, double, String, Trader)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GentradeSubAccount l_subAccount1 = null;
        WEB3GentradeSubAccount l_subAccount2 = null;
        try
        {
            //getSubAccount(arg0 : SubAccountTypeEnum)
            //[引数] arg0： 1（株式取引口座（預り金））
            l_subAccount1 =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //getSubAccount(arg0 : SubAccountTypeEnum)
            //[引数] arg0： 2（株式信用取引口座（保証金））
            l_subAccount2 =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get保証金への振替額(補助口座 : 補助口座, 受渡日 : Date, 入金額 : double)
        //補助口座： getSubAccount()の戻り値（補助口座①@[預り金口座]）
        //受渡日：　@引数.受渡日
        //入金額：　@引数.入金額
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblMarginPowerTransferAmount =
            l_tpTradingPowerService.getTransferAmountToDeposit(
                l_subAccount1,
                l_datDeliveryDate,
                l_dblCashinAmt);

        log.debug("get保証金への振替額 = " + l_dblMarginPowerTransferAmount);

        if (l_dblMarginPowerTransferAmount > 0)
        {
            //get商品ID(Institution)
            //[引数]
            //証券会社： 補助口座①@[預り金口座].get取引店().getInstitution()の戻り値
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
            long l_lngProductId =
                l_aioOrderManager.getProductId(l_subAccount1.getWeb3GenBranch().getInstitution());

            //get新規識別コード(証券会社コード:String, 部店コード:String, 銘柄タイプ:ProductTypeEnum)
            //[引数]
            //証券会社コード： 補助口座①@[預り金口座].getInstitution().証券会社コード
            //部店コード：　@補助口座①@[預り金口座].get取引店().getBranchCode()
            //銘柄タイプ： 5（現金）
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount1.getInstitution().getInstitutionCode(),
                    l_subAccount1.getWeb3GenBranch().getBranchCode(),
                    ProductTypeEnum.CASH);

            //入出金注文内容
            //[引数]
            //代理入力者： 引数.代理入力者
            //注文種別： 1005（振替注文（預り金から信用保証金））
            //振替タイプ： 2（出金）
            //商品ID： get商品ID()の戻り値
            //金額： get保証金振替可能額()の戻り値
            //記述： null
            //振替予定日： 引数.受渡日
            //決済機@関ID： null
            //注文ID： null
            WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                l_dblMarginPowerTransferAmount,
                null,
                l_datDeliveryDate,
                null,
                null);

            //振替注文更新インタセプタ(入出金注文内容)
            //[引数の設定]
            //入出金注文内容：　@入出金注文内容（振替注文①@）
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
            l_transferOrderUpdateInterceptor1.setBizDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber);
            l_transferOrderUpdateInterceptor1.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);

            //入出金注文内容
            //[引数]
            //代理入力者： 引数.代理入力者
            //注文種別： 1005（振替注文（預り金から信用保証金））
            //振替タイプ： 1（入金）
            //商品ID： get商品ID()の戻り値
            //金額： get保証金振替可能額()の戻り値
            //記述： null
            //振替予定日： 引数.受渡日
            //決済機@関ID： null
            //注文ID： null
            WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                AssetTransferTypeEnum.CASH_IN,
                l_lngProductId,
                l_dblMarginPowerTransferAmount,
                null,
                l_datDeliveryDate,
                null,
                null);

            //振替注文更新インタセプタ(入出金注文内容)
            //[引数の設定]
            //入出金注文内容：　@入出金注文内容（振替注文②）
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);
            l_transferOrderUpdateInterceptor2.setBizDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datDeliveryDate);
            l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewNumber);
            l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);

            //createNewOrderId
            //新規注文IDを採番する。（注文ID①@）
            long l_lngOrderId1 = l_aioOrderManager.createNewOrderId();

            //submit振替注文
            //信用振替注文（振替元）を登録する
            //[引数]
            //補助口座： getSubAccount()の戻り値（補助口座①@[預り金口座]）
            //銘柄タイプ： 5（現金）
            //注文種別： 1005（振替注文（預り金から信用保証金））
            //注文内容： 入出金注文内容（振替注文①@）
            //インタセプタ： 振替注文更新インタセプタ（振替注文①@）
            //注文ID： createNewOrderId()の戻り値（注文ID①@）
            //パスワード： 引数.暗証番号
            l_aioOrderManager.submitTransferOrder(
                l_subAccount1,
                ProductTypeEnum.CASH,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                l_aioNewOrderSpec1,
                l_transferOrderUpdateInterceptor1,
                l_lngOrderId1,
                l_strPassword);

            //createNewOrderId
            //新規注文IDを採番する。（注文ID②）
            long l_lngOrderId2 = l_aioOrderManager.createNewOrderId();

            //submit振替注文
            //信用振替の反対注文（振替先）を登録する
            //[引数]
            //補助口座： getSubAccount()の戻り値（補助口座②[保証金口座]）
            //銘柄タイプ： 5（現金）
            //注文種別： 1005（振替注文（預り金から信用保証金））
            //注文内容： 入出金注文内容（振替注文②）
            //インタセプタ： 振替注文更新インタセプタ（振替注文②）
            //注文ID： createNewOrderId()の戻り値（注文ID②）
            //パスワード： 引数.暗証番号
            l_aioOrderManager.submitTransferOrder(
                l_subAccount2,
                ProductTypeEnum.CASH,
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                l_aioNewOrderSpec2,
                l_transferOrderUpdateInterceptor2,
                l_lngOrderId2,
                l_strPassword);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
