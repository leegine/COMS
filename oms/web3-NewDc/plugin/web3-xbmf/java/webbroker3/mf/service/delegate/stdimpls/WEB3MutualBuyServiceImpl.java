head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文サービス実装クラス(WEB3MutualBuyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 黄建 (中訊) レビュー
Revesion History : 2004/12/13 于美麗 (中訊) 残対応
Revesion History : 2005/10/18 黄建 (中訊) フィデリティ対応
Revesion History : 2006/06/26 周捷 (中訊) 仕様変更(モデル)：419
Revesion History : 2006/09/11 周捷 仕様変更・モデル489、496、497、ＤＢ更新仕様078
Revesion History : 2006/10/11 柴雙紅(中訊) 仕様変更 モデル No.500 ＤＢ更新仕様No.083
Revesion History : 2007/02/05 丁昭奎 (中訊) モデル No.525
Revesion History : 2007/03/26 趙林鵬 (中訊) モデル No.550
Revesion History : 2007/04/09 趙林鵬 (中訊) モデル No.556,実装005
Revesion History : 2007/04/19 張騰宇 (中訊) モデル No.566
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3CommissionDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.mf.message.WEB3MutualBuyCompleteResponse;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequest;
import webbroker3.mf.message.WEB3MutualBuyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投信買付注文サービスImpl<BR>
 * 投資信託買付注文サービス実装クラス
 *
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualBuyService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyServiceImpl.class);

    /**
     * 投資信託買付注文サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * validate買付注文()、submit買付注文()<BR>
     * いずれかのメソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555CE803C7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MutualBuyConfirmRequest)
        {
            WEB3MutualBuyConfirmResponse l_mutualBuyConfirmResponse =
                this.validateBuyOrder((WEB3MutualBuyConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_mutualBuyConfirmResponse;
        }
        else if (l_request instanceof WEB3MutualBuyCompleteRequest)
        {
            WEB3MutualBuyCompleteResponse l_mutualBuyCompleteResponse =
                this.submitBuyOrder((WEB3MutualBuyCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_mutualBuyCompleteResponse;
        }
        else
        {
            // パラメータ値が不正
            log.debug(STR_METHOD_NAME + " パラメータ値が不正する！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    /**
     * (validate買付注文)<BR>
     * 投資信託買付注文審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)買付注文審査」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)買付注文審査」: <BR>
     *         13(*7) (is判定フラグ( )＝falseの場合false)の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualBuyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555EC5031B
     */
    protected WEB3MutualBuyConfirmResponse validateBuyOrder(
        WEB3MutualBuyConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateBuyOrder(WEB3MutualBuyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1）　@入力内容チェック
        l_request.validate();

        //1.2）　@補助口座取得
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3）　@拡張投信銘柄取得
        //－拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_mutualFundProductManager = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        //－拡張投信銘柄マネージャ.get投信銘柄()をコールし、拡張投信銘柄を取得する。
        WEB3MutualFundProduct l_mutualFundProduct = null; //拡張投信銘柄
        //－拡張投信注文マネージャを取得する
        WEB3MutualFundOrderManager l_mutualFundOrderManager = null;
        l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //投信買付注文確認レスポンスオブジェクトを生成
        WEB3GenResponse l_response = l_request.createResponse();
        WEB3MutualBuyConfirmResponse l_mutualBuyInputResponse =
            (WEB3MutualBuyConfirmResponse) l_response;
        try
        {
            try
            {
                l_mutualFundProduct =
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_request.mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("no find MutualFundProduct ", l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //=================顧客別取引停止属性チェック================
            //1.4）注文チェックオブジェクトを取得する。
            //FinAppクラスのgetCommonOrderValidator()をコールし
            //注文チェックオブジェクトを取得する
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //1.5）－注文チェック.validate取引可能顧客()をコールする
            Timestamp l_datBizDate =
                new Timestamp(
                    l_request.orderedDate.getTime());

            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(
                    l_genMainAccount,
                    l_datBizDate);

            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.6）validate注文受付可能( )
            //－投信取引時間管理.validete注文受付可能()をコールする
            WEB3GentradeTradingTimeManagement.validateOrderAccept();


            //1.7）取引時間の再設定
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);

            //1.8）－受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //1.9）validate新規注文(SubAccount, 拡張投信銘柄, double, String, String, String, 拡張投信銘柄,TaxTypeEnum)
            //［validate新規注文に渡すパラメタ］
            //補助口座： 取得した補助口座オブジェクト
            //拡張投信銘柄： 取得した拡張投信銘柄オブジェクト
            //注文金額数量： リクエストデータ.数量
            //処理区分： ”1：買付”
            //受渡方法@： null
            //指定方法@： リクエストデータ.指定方法@
            //乗換先銘柄： null
            //税区分： null
            //決済方法@： リクエストデータ.決済方法@
            NewOrderValidationResult l_newOrderValidationResult =
                l_mutualFundOrderManager.validateNewOrder(
                    l_subAccount,
                    l_mutualFundProduct,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    WEB3ProcessDivDef.BUY,
                    null,
                    l_request.specifyDiv,
                    null,
                    null,
                    l_request.settleDiv);

            if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
            {
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.10）get発注日(Date)
            //確認時発注日：　@リクエストデータ.発注日
            //注文種別　@　@　@：  OrderTypeEnum．投資信託買注文
            //一括区分　@　@　@：　@取得した拡張投信銘柄．is特定日取引銘柄()
            Date l_orderBizDate =
                WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                    l_request.orderedDate,
                    OrderTypeEnum.MF_BUY,
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));

            //1.11 get約定日(Institution, String, Date)
            //証券会社： 取得した補助口座.getInstitution()の戻り値
            //銘柄コード： リクエストデータ.銘柄コード
            //発注日： 取得した発注日
            Timestamp l_tsExeecuted = l_mutualFundProductManager.getExecutedDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_orderBizDate);

            //1.12）　@受渡日取得
            Timestamp l_Timestamp = null; //受渡日
            l_Timestamp =
                l_mutualFundProductManager.getDeliveryDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    true,
                    l_tsExeecuted);
            log.debug("WEB3MutualBuyServiceImpl.validateBuyOrder::l_Timestamp = " +
                l_Timestamp);

            //1.13）リクエストデータ.口座区分の値が”1：特定”の場合、特定口座チェックを行う

            //1.13.1）get顧客()
            WEB3GentradeMainAccount l_gentradeMainAccount = null; //顧客
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //1.13.2）is特定口座開設(Date, 補助口座)
            if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_gentradeMainAccount =
                    (
                        WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                            l_subAccount.getAccountId());

                if (!l_gentradeMainAccount.isSpecialAccountEstablished(
                        l_Timestamp, l_subAccount))
                {
                    log.debug("お客様は特定口座を開設されておりません。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "お客様は特定口座を開設されておりません。");
                }
            }

            //1.14）　@概算受渡代金取得
            WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice = null;
            //概算受渡代金
            //［calc概算受渡代金に渡すパラメタ］
            //補助口座： 取得した補助口座オブジェクト
            //銘柄： 取得した拡張投信銘柄オブジェクト
            //銘柄（乗換先）： null
            //処理区分： ”1：買付”
            //注文数量： リクエストデータ.数量
            //指定方法@： リクエストデータ.指定方法@
            //決済方法@： リクエストデータ.決済方法@
            //請求方法@： null
            //口座区分： リクエストデータ.口座区分
            //注文チャネル： セッションより取得した注文チャネル
            //発注日： リクエストデータ.発注日

            //セッションより取得した注文チャネル
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            String l_strOrderChanel =
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

            l_mutualFundEstimatedPrice =
                l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_mutualFundProduct,
                    null,
                    WEB3ProcessDivDef.BUY,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_request.specifyDiv,
                    l_request.settleDiv,
                    null,
                    l_request.taxType,
                    l_strOrderChanel,
                    l_request.orderedDate);
            log.debug("WEB3MutualBuyServiceImpl.validateBuyOrder::" +
                    "l_mutualFundEstimatedPrice = " +
                    l_mutualFundEstimatedPrice.getEstimatedPrice());

            //1.15）取引余力残高チェック
            //1.15.1）投信新規注文確定インタセプタを生成する
            WEB3MutualFundNewOrderConfirmInterceptor l_mutualFundNewOrderConfirmInterceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();

            //1.15.2）投信新規注文確定インタセプタを拡張投信注文マネージャに設定する
            l_mutualFundOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_mutualFundNewOrderConfirmInterceptor);

            //　@投信新規注文確定インタセプタに受渡日を設定する
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDate(l_Timestamp);

            //　@投信新規注文確定インタセプタに注文チャネルを設定する
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannel(
                this.getLoginChannel());

            //　@投信新規注文確定インタセプタに計算基準価額を設定する
            l_mutualFundNewOrderConfirmInterceptor.setConstantValue(
                l_mutualFundProduct.getConstantValue());

            //　@投信新規注文確定インタセプタに計算基準価額（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingConstantValue(
                Double.NaN);

            //　@投信新規注文確定インタセプタに基準価額適用日を設定する
            l_mutualFundNewOrderConfirmInterceptor.setConstantValueAppDate(
                l_mutualFundProduct.getConstantValueAppDate());

            //　@投信新規注文確定インタセプタに概算受渡代金を設定する
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedPrice(
                l_mutualFundEstimatedPrice.getEstimatedPrice());

            //　@投信新規注文確定インタセプタに概算売買口数を設定する
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedQty(
                l_mutualFundEstimatedPrice.getEstimatedQty());

            //　@投信新規注文確定インタセプタに概算買付口数（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingEstimatedQty(
                Double.NaN);

            //　@投信新規注文確定インタセプタに税区分（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectTaxDivision(
                null);

            //　@投信新規注文確定インタセプタに受渡方法@を設定する
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDiv(null);

            //　@投信新規注文確定インタセプタに投信タイプを設定する
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundType(
                Integer.toString(
                    l_mutualFundProduct.getMutualFundType().intValue()));

            //　@投信新規注文確定インタセプタに投信解約区分を設定する
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundSellDiv(null);

            //　@投信新規注文確定インタセプタに約定日を設定する。
            l_mutualFundNewOrderConfirmInterceptor.setExecutionTimestamp(l_tsExeecuted);

            //　@投信新規注文確定インタセプタに決済区分を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSettlementType(
                l_request.settleDiv);

            //　@投信新規注文確定インタセプタに無手数料区分を設定する
            //(*) 拡張投信注文マネージャ.is手数料無料顧客() == true の場合、”9：無手数料”を設定する。
            //  [is手数料無料顧客()に渡すパラメータ]
            //　@　@顧客： 顧客オブジェクト
            //　@　@銘柄： 取得した拡張投信銘柄オブジェクト
            if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.NO_COMMISSION);
            }
            //取得した拡張投信銘柄.is乗換優遇対象()の戻り値が ”0：償還優遇不可”の場合、
            //ブランクを設定する
            else if (l_mutualFundProduct.isSwitchingPerferenceObject())
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.SWITCHING_PREFERENCE);
            }
            else
            {
                //取得した拡張投信銘柄.is乗換優遇対象()の戻り値がfalseの場合はブランクを設定する
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    " ");
            }

            //銘柄コード（乗換先）：null
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);

            //請求区分：null
            l_mutualFundNewOrderConfirmInterceptor.setRequestDivision(null);

            //注文経路区分：セッションより取得した同項目の値
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannelDivision(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //出金注文識別コード：null
            l_mutualFundNewOrderConfirmInterceptor.setPaymentOrderReqNumber(null);

            //一括区分：取得した拡張投信銘柄．is特定日取引銘柄
            //[is特定日取引銘柄のパラメータ]
            //注文種別 ： OrderTypeEnum．投資信託買注文
            l_mutualFundNewOrderConfirmInterceptor.setNorealDiv(
                l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));

            //注文終了日：取得した拡張投信銘柄．getDataSourceObject().get買付終了日()
            MutualFundProductRow l_fundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            l_mutualFundNewOrderConfirmInterceptor.setOrderEndDate(
                l_fundProductRow.getBuyEndDate());

            //1.15.4)拡張投信新規注文内容(Trader, boolean, String, String, String, double, QuantityTypeEnum, TaxTypeEnum)
            //　@新規注文内容の生成
            QuantityTypeEnum l_orderQuantityType = null;
            TaxTypeEnum l_taxType = null;
            if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.AMOUNT;
            }
            else if (
                WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.QUANTITY;
            }

            if (WEB3MFAccountDivDef.NORMAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            else if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.SPECIAL;
            }
            else if (WEB3MFAccountDivDef.OTHER.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.UNDEFINED;
            }

            WEB3MutualFundNewOrderSpec l_expMutualFundNewOrderSpec =
                new WEB3MutualFundNewOrderSpec(
                    this.getTrader(),
                    true,
                    l_request.mutualProductCode,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_orderQuantityType,
                    l_taxType);

            //リクエストデータ.決済区分 == ”円貨” の場合
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);

                Object[] l_arrNewOrderConfirmInterceptors =
                    {l_mutualFundNewOrderConfirmInterceptor};
                Object[] l_arrNewOrderSpecs = {l_expMutualFundNewOrderSpec};

                //1.15.5)取引余力結果オブジェクトを取得する。
                WEB3TPTradingPowerResult l_result =
                    l_tpTradingPowerService.validateTradingPower(
                        l_subAccount,
                        l_arrNewOrderConfirmInterceptors,
                        l_arrNewOrderSpecs,
                        OrderTypeEnum.MF_BUY,
                        false);
                //1.14.7)is判定フラグ( )＝falseの場合
                if(!l_result.isResultFlg())
                {
                    log.debug("余力残高エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "余力残高エラー");
                }
            }

            double l_dbEstimatedQty =
                l_mutualFundEstimatedPrice.getEstimatedQty();
            double l_dbEstimatedPrice =
                l_mutualFundEstimatedPrice.getEstimatedPrice();
            log.debug("WEB3MutualBuyServiceImpl.validateBuyOrder::" +
                    "l_dbEstimatedQty = " +
                    l_dbEstimatedQty);

            //1.16)拡張投信注文マネージャ.createNewOrderId()をコールして注文IDを取得する
            long l_orderId = l_mutualFundOrderManager.createNewOrderId();

            //1.17）　@投信買付注文確認レスポンスオブジェクトをセットし、リターンする

            // 概算受渡代金通貨コード：
            // (*) リクエストデータ.決済方法@が”1：円貨”の場合は
            // ”T0：円”を設定する。
            // (*) リクエストデータ.決済方法@が”2：外貨”の場合は
            // 取得した拡張投信銘柄オブジェクト.get通貨コード()の戻り値を設定する。
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                l_mutualBuyInputResponse.estimatedPriceCurrencyCode =
                    WEB3MFEstimatedPriceCurrencyCodeDef.T0;
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))
            {
                l_mutualBuyInputResponse.estimatedPriceCurrencyCode =
                    l_mutualFundProduct.getCurrencyCode();
            }

            //概算受渡代金
            l_mutualBuyInputResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_dbEstimatedPrice);
            //概算売買口数
            l_mutualBuyInputResponse.estimatedQty =
                WEB3StringTypeUtility.formatNumber(l_dbEstimatedQty);
            l_mutualBuyInputResponse.orderId = l_orderId + "";

            log.debug(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("no find MainAccount", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //return
        return l_mutualBuyInputResponse;
    }

    /**
     * (submit買付注文)<BR>
     * 投資信託買付注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)買付注文登録」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)買付注文登録」: <BR>
     *         21(*3) (is判定フラグ( )＝falseの場合false)の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)買付注文登録」: <BR>
     *         15((リクエストデータ.口座区分の値が”1：特定”の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00026<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualBuyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555ECB0389
     */
    protected WEB3MutualBuyCompleteResponse submitBuyOrder(
        WEB3MutualBuyCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitBuyOrder(WEB3MutualBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1）　@入力内容チェック
        l_request.validate();

        //1.2）　@補助口座取得
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3）　@拡張投信銘柄取得
        //－拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_mutualFundProductManager = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        //－拡張投信銘柄マネージャ.get投信銘柄()をコールし、拡張投信銘柄を取得する
        WEB3MutualFundProduct l_mutualFundProduct = null; //拡張投信銘柄
        WEB3MutualBuyCompleteResponse l_MutualBuyCompleteResponse = null;
        try
        {
            try
            {
                l_mutualFundProduct =
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_request.mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("no find MutualFundProduct ", l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

          //1.4）　@顧客別取引停止属性チェック
          //－拡張投信注文マネージャを取得する
          WEB3MutualFundOrderManager l_mutualFundOrderManager = null;
          l_mutualFundOrderManager =
              (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                  ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            //FinAppクラスのgetCommonOrderValidator()をコールし
            //注文チェックオブジェクトを取得する
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //－注文チェック.validate取引可能顧客()をコールする
            Timestamp l_datBizDate =
                new Timestamp(
                    l_request.orderedDate.getTime());

            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(
                    l_genMainAccount,
                    l_datBizDate);

            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.5)－validate取引パスワード( )をコールする。
            OrderValidationResult l_validationResultPassword =
                l_orderValidator.validateTradingPassword(
                    this.getTrader(),
                    l_subAccount,
                    l_request.password);

            if (l_validationResultPassword.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードが不正です");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードが不正です。");
            }

            //1.8）　@受付時間チェック、システム取引停止チェック
            //－投信取引時間管理.validete注文受付可能()をコールする
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //1.9）取引時間の再設定
            //－投信取引時間管理.reset銘柄コード()をコールし、銘柄コードを投信買付注文
            //入力リクエスト.銘柄コードに変更する
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);

            //1.10)－受付日時、日付ロールをセットする。
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //1.11）発注審査
            //－拡張投信注文マネージャ.validate新規注文()をコールし、発注審査を行う
            //［validate新規注文に渡すパラメタ］
            //補助口座： 取得した補助口座オブジェクト
            //拡張投信銘柄： 取得した拡張投信銘柄オブジェクト
            //注文金額数量： リクエストデータ.数量
            //処理区分： ”1：買付”
            //受渡方法@： null
            //指定方法@： リクエストデータ.指定方法@
            //換先銘柄： null
            //税区分： null
            //決済方法@： リクエストデータ.決済方法@

            NewOrderValidationResult l_newOrderValidationResult =
                l_mutualFundOrderManager.validateNewOrder(
                    l_subAccount,
                    l_mutualFundProduct,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    WEB3ProcessDivDef.BUY,
                    null,
                    l_request.specifyDiv,
                    null,
                    null,
                    l_request.settleDiv);

            if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
            {
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.12）　@発注日取得
            //確認時発注日：　@リクエストデータ.発注日
            //注文種別　@　@　@：  OrderTypeEnum．投資信託買注文
            //一括区分　@　@　@：　@取得した拡張投信銘柄．is特定日取引銘柄
            //[is特定日取引銘柄の引数]
            //注文種別 ： OrderTypeEnum．投資信託買注文
            Date l_orderBizDate = WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.orderedDate,
                OrderTypeEnum.MF_BUY,
                l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));

            //1.13）　@約定日取得
            Timestamp l_tsexecutedDate = null;
            l_tsexecutedDate =
                l_mutualFundProductManager.getExecutedDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    l_orderBizDate);

            //1.14）　@受渡日取得
            Timestamp l_Timestamp = null; //受渡日
            l_Timestamp =
                l_mutualFundProductManager.getDeliveryDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    true,
                    l_tsexecutedDate);
            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_executedDate = " +
                l_tsexecutedDate);
            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_Timestamp = " +
                l_Timestamp);

            //1.15）　@特定口座チェック
            WEB3GentradeMainAccount l_gentradeMainAccount = null; //顧客
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_gentradeMainAccount =
                    (WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                        l_subAccount.getAccountId());
                if (!l_gentradeMainAccount.isSpecialAccountEstablished(
                    l_Timestamp, l_subAccount))
                {
                    log.debug("is特定口座開設()が false を返す場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "特定口座チェックエラー");
                }
            }

            //1.16）　@概算受渡代金取得
            WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice = null;

            //概算受渡代金
            //［calc概算受渡代金に渡すパラメタ］
            //補助口座： 取得した補助口座オブジェクト
            //銘柄： 取得した拡張投信銘柄オブジェクト
            //銘柄（乗換先）： null
            //処理区分： ”1：買付”
            //注文数量： リクエストデータ.数量
            //指定方法@： リクエストデータ.指定方法@
            //決済方法@： リクエストデータ.決済方法@
            //請求方法@： null
            //口座区分： リクエストデータ.口座区分
            //注文チャネル： セッションより取得した注文チャネル
            //発注日： リクエストデータ.発注日

            //セッションより取得した注文チャネル
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            String l_strOrderChanel =
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

            l_mutualFundEstimatedPrice =
                l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_mutualFundProduct,
                    null,
                    WEB3ProcessDivDef.BUY,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_request.specifyDiv,
                    l_request.settleDiv,
                    null,
                    l_request.taxType,
                    l_strOrderChanel,
                    l_request.orderedDate);

            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_mutualFundEstimatedPrice = " +
                l_mutualFundEstimatedPrice);

            //1.17）　@投信新規注文確定インタセプタを生成する
            WEB3MutualFundNewOrderConfirmInterceptor l_mutualFundNewOrderConfirmInterceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();

            //1.18）　@投信新規注文確定インタセプタを拡張投信注文マネージャに設定する
            l_mutualFundOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_mutualFundNewOrderConfirmInterceptor);
            //1.19) 生成した投信新規注文確定インタセプタに以下のプロパティを設定する。

            //投信新規注文確定インタセプタに受渡日を設定する
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDate(l_Timestamp);
            //投信新規注文確定インタセプタに注文チャネルを設定する
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannel(
                this.getLoginChannel());
            //投信新規注文確定インタセプタに計算基準価額を設定する
            if (l_mutualFundProduct.isFrgnMmf())
            {
                l_mutualFundNewOrderConfirmInterceptor.setConstantValue(Double.NaN); 
            }
            else
            {
                l_mutualFundNewOrderConfirmInterceptor.setConstantValue(
                    l_mutualFundProduct.getConstantValue());
            }
            //投信新規注文確定インタセプタに計算基準価額（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingConstantValue(
                Double.NaN);
            //投信新規注文確定インタセプタに基準価額適用日を設定する
            l_mutualFundNewOrderConfirmInterceptor.setConstantValueAppDate(
                l_mutualFundProduct.getConstantValueAppDate());
            //新規注文確定インタセプタに概算受渡代金を設定する
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedPrice(
                l_mutualFundEstimatedPrice.getEstimatedPrice());
            //投信新規注文確定インタセプタに概算売買口数を設定する
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedQty(
                l_mutualFundEstimatedPrice.getEstimatedQty());
            //投信新規注文確定インタセプタに概算買付口数（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingEstimatedQty(
                Double.NaN);
            //投信新規注文確定インタセプタに税区分（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectTaxDivision(
                null);
            //投信新規注文確定インタセプタに受渡方法@を設定する
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDiv(null);
            //投信新規注文確定インタセプタに投信タイプを設定する
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundType(
                Integer.toString(
                    l_mutualFundProduct.getMutualFundType().intValue()));
            //投信新規注文確定インタセプタに投信解約区分を設定する
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundSellDiv(null);
            //投信新規注文確定インタセプタに約定日を設定する。
            l_mutualFundNewOrderConfirmInterceptor.setExecutionTimestamp(
                l_tsexecutedDate);
            //投信新規注文確定インタセプタに決済区分を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSettlementType(
                l_request.settleDiv);

            //投信新規注文確定インタセプタに無手数料区分を設定する
            //(*) 拡張投信注文マネージャ.is手数料無料顧客() == true の場合、”9：無手数料”を設定する。
            //  [is手数料無料顧客()に渡すパラメタ]
            //    顧客： 顧客オブジェクト
            //    銘柄： 取得した拡張投信銘柄オブジェクト
            if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.NO_COMMISSION);
            }
            else if (l_mutualFundProduct.isSwitchingPerferenceObject())
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.SWITCHING_PREFERENCE);
            }
            else
            {
                //取得した拡張投信銘柄.is乗換優遇対象()の戻り値がfalseの場合はブランクを設定する
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    " ");
            }

            //投信新規注文確定インタセプタに銘柄コード（乗換先）を設定する
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectMutualProductCode(
                null);
            //投信新規注文確定インタセプタに請求区分を設定する
            l_mutualFundNewOrderConfirmInterceptor.setRequestDivision(null);
            //投信新規注文確定インタセプタに注文経路区分を設定する
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannelDivision(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //⑲投信新規注文確定インタセプタに出金注文識別コードを設定する。
            //[set出金注文識別コードに渡すパラメタ]
            //出金注文識別コード： null
            l_mutualFundNewOrderConfirmInterceptor.setPaymentOrderReqNumber(null);

            //⑳投信新規注文確定インタセプタに一括区分を設定する。
            //[set一括区分に渡すパラメタ]
            // 一括区分：取得した拡張投信銘柄．is特定日取引銘柄()
            //[is特定日取引銘柄の引数]
            //注文種別 ： OrderTypeEnum．投資信託買注文
            l_mutualFundNewOrderConfirmInterceptor.setNorealDiv(
                l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));
            //21投信新規注文確定インタセプタに注文終了日を設定する。
            //[set注文終了日に渡すパラメタ]
            //注文終了日：取得した拡張投信銘柄．getDataSourceObject().get買付終了日()
            MutualFundProductRow l_fundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            l_mutualFundNewOrderConfirmInterceptor.setOrderEndDate(
                l_fundProductRow.getBuyEndDate());
            //1.20）　@新規注文内容の生成
            QuantityTypeEnum l_orderQuantityType = null;
            TaxTypeEnum l_taxType = null;
            if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.AMOUNT;
            }
            else if (
                WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.QUANTITY;
            }

            if (WEB3MFAccountDivDef.NORMAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            else if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.SPECIAL;
            }
            else if (WEB3MFAccountDivDef.OTHER.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.UNDEFINED;
            }

            WEB3MutualFundNewOrderSpec l_expMutualFundNewOrderSpec =
                new WEB3MutualFundNewOrderSpec(
                    this.getTrader(),
                    true,
                    l_request.mutualProductCode,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_orderQuantityType,
                    l_taxType);

            //リクエストデータ.決済区分 == ”円貨” の場合
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                //1.21）　@取引余力チェック・取引余力更新
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);
    
                Object[] l_arrNewOrderConfirmInterceptors =
                    {l_mutualFundNewOrderConfirmInterceptor};
                Object[] l_arrNewOrderSpecs = {l_expMutualFundNewOrderSpec};
    
                WEB3TPTradingPowerResult l_resule =
                    l_tpTradingPowerService.validateTradingPower(
                        l_subAccount,
                        l_arrNewOrderConfirmInterceptors,
                        l_arrNewOrderSpecs,
                        OrderTypeEnum.MF_BUY,
                        true);
    
                 //1.21.3)is判定フラグ()＝false の場合
                if(!l_resule.isResultFlg())
                {
                    log.debug("余力残高エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "余力残高エラー");
                }
            }

            //1.22）　@買付注文
            OrderSubmissionResult l_orderSubmissionResult = null;
            l_orderSubmissionResult =
                l_mutualFundOrderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.MUTUAL_FUND,
                    l_expMutualFundNewOrderSpec,
                    Long.parseLong(l_request.orderId),
                    l_request.password,
                    true);

            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult()== false)
            {
                log.debug(
                    "l_mutualFundOrderManager.submitNewOrder()." +
                    "getProcessingResult().isSuccessfulResult() == false");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "新規注文失敗");
            }

            //1.23.＜リクエストデータ．紹介区分　@!= NULL　@の場合＞
            if (l_request.introduceStoreDiv != null)
            {
                //1.23.1getOrderUnits(arg0 : long)
                //投信注文単位オブジェクトを取得する。
                //［getOrderUnitsに渡すパラメタ］
                //　@　@　@arg0： リクエスト.注文ID
                OrderUnit[] l_orderUnits =
                    l_mutualFundOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
                MutualFundOrderUnit[] l_mfOrderUnits = new MutualFundOrderUnit[l_orderUnits.length];
                for (int i = 0; i < l_orderUnits.length; i++)
                {
                    l_mfOrderUnits[i] = (MutualFundOrderUnit) l_orderUnits[i];
                }
                MutualFundOrderUnitRow l_orderUnitRow =
                    (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
                MutualFundOrderUnitParams l_orderUnitParams =
                    new MutualFundOrderUnitParams(l_orderUnitRow);
                //1.23.2.注文単位紹介区分( )
                OrderUnitIntroduceDivParams l_introduceDivParams = new OrderUnitIntroduceDivParams();
                //1.23.3.<プロパティセット>
                //注文単位ID   =　@取得した注文単位.注文単位ID
                l_introduceDivParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
                //口座ID         =  取得した注文単位.口座ID
                l_introduceDivParams.setAccountId(l_orderUnitParams.getAccountId());
                //銘柄タイプ     =  引数にProductTypeEnum.投資信託
                l_introduceDivParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
                //紹介区分      =  リクエスト.紹介区分
                l_introduceDivParams.setIntroduceBranchDiv(l_request.introduceStoreDiv);
                //紹介店コード  =  リクエスト.紹介店コード
                l_introduceDivParams.setIntroduceBranchCode(l_request.introduceStoreCode);
                //更新者コード
                //　@ ・顧客入力の場合
                //       　@取得した注文単位.口座IDに該当する口座コードをセットする。
                // 　@・代理入力の場合
                //       　@取得した注文単位.取引者IDに該当する扱者コードをセットする。
                if (getTrader() == null)
                {
                    l_gentradeMainAccount =
                        (WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                            l_orderUnitParams.getAccountId());
                    l_introduceDivParams.setLastUpdater(l_gentradeMainAccount.getAccountCode());
                }
                else
                {
                    FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                    Trader l_trder = l_finObjMgr.getTrader(l_orderUnitParams.getTraderId());
                    l_introduceDivParams.setLastUpdater(l_trder.getTraderCode());
                }
                //作成日付 = 現在時刻
                l_introduceDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //更新日付 = 現在時刻
                l_introduceDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //1.23.3.saveNew注文単位紹介区分( )
                //注文単位紹介区分テーブルにインサートする。
                Processors.getDefaultProcessor().doInsertQuery(l_introduceDivParams);

            }

            //1.24）getAttribute(String)
            Date l_date = null;
            l_date = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_date = " + l_date);

            //1.25）　@投信買付注文完了レスポンスオブジェクトを生成し、リターンする
            WEB3GenResponse l_response = l_request.createResponse();
            l_MutualBuyCompleteResponse =
                (WEB3MutualBuyCompleteResponse) l_response;

            //1.26)プロパティ・セット
            l_MutualBuyCompleteResponse.lastUpdatedTimestamp = l_date;
            l_MutualBuyCompleteResponse.orderActionId = l_request.orderId;
        }
        catch (NotFoundException l_ex)
        {
            log.error("no find MainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_MutualBuyCompleteResponse;
    }
}
@
