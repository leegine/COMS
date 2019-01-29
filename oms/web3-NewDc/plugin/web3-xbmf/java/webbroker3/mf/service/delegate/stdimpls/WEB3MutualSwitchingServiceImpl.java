head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換サービス実装クラス(WEB3MutualSwitchingServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
Revesion History : 2004/08/24 韋念瓊 (中訊) レビュー
Revesion History : 2004/12/10 于美麗 (中訊) 残対応
Revesion History : 2005/10/19 黄建 (中訊) フィデリティ対応
Revesion History : 2006/03/08 玉岡 (SRA) 仕様変更(モデル)：400
Revesion History : 2006/06/26 張秋穎 (中訊) 仕様変更(モデル)：419
Revesion History : 2006/07/05 韋念瓊 (中訊) 仕様変更(モデル)：465
Revesion History : 2006/07/18 山下 (SRA) 仕様変更(モデル)：468
Revesion History : 2006/08/10 山下 (SRA) 仕様変更(モデル)：485
Revesion History : 2006/10/27 張騰宇 (中訊) モデル 489 497 ＤＢ更新仕様No.078,082
Revesion History : 2007/04/09 張騰宇 (中訊) モデル557　@実装005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
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
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3CommissionDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundBizLogicProvider;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundNewOrderSwtConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmRequest;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ( 投信乗換サービスImpl)<BR>
 * 投資信託乗換サービス実装クラス
 *
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualSwitchingService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingServiceImpl.class);

    /**
     * 投資信託乗換サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate()乗換または、<BR>
     * submit乗換()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40557DB000CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3MutualSwitchingConfirmRequest)
        {
            //validate()乗換メソッド
            l_response =
                this.validateSwitching(
                    (WEB3MutualSwitchingConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MutualSwitchingCompleteRequest)
        {
            //submit乗換()メソッド
            l_response =
                this.submitSwitching(
                    (WEB3MutualSwitchingCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + " __Request "
                    + " WEB3MutualSwitchingCompleteRequest "
                    + " と WEB3MutualSwitchingConfirmRequestt以外である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate乗換)<BR>
     * 投資信託乗換審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投信）乗換審査」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信）乗換審査」: <BR>
     *        21((保有残高口数超過チェック <BR>
     *        取得した概算受渡代金オブジェクト.get概算売買口数()＞ <BR>
     *         取得した解約可能残高口数、の場合は例外をスローする。<BR>
     *        （保有残高口数超過エラー）<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信）乗換審査」: <BR>
     *        11) 解約可能残高口数を取得する
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信）乗換審査」: <BR>
     *       15.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする<BR>
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40557DF801D3
     */
    protected WEB3MutualSwitchingConfirmResponse validateSwitching(
         WEB3MutualSwitchingConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSwitching(WEB3MutualSwitchingConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1）　@入力内容チェック
        l_request.validate();

        // 1.2）　@補助口座取得
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //拡張投信銘柄取得
        // 　@－拡張投信銘柄マネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        // －拡張アカウントマネージャの取得
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_swtProduct = null;
        try
        {
            // 1.3) 乗換元銘柄オブジェクトの取得
            //   拡張投信銘柄マネージャ.get投信銘柄()
            l_mfProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);

            // 1.4）　@乗換先銘柄オブジェクトの取得
            // 　@拡張投信銘柄マネージャ.get投信銘柄()
            l_swtProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_mfProduct.getInstitution(),
                    l_request.switchingProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ）　@顧客別取引停止属性チェック
        //  拡張投信注文マネージャを取得する
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //FinAppクラスのgetCommonOrderValidator()をコールし
        //1.5) 注文チェックオブジェクトを取得する
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        Timestamp l_datBizDate =
            new Timestamp(l_request.orderedDate.getTime());

        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        // 1.6)validate取引可能顧客
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

        // 受付時間チェック、システム取引停止チェック
        // 1.7) 投信取引時間管理.validete注文受付可能()をコールする
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // 取引時間の再設定
        // 1.8) 投信取引時間管理.reset銘柄コード()をコール
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        // 受付日時、日付ロールをセットする
        // 1.9) 投信取引時間管理.setTimestamp()をコールする
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //  －拡張投信ポジションマネージャの取得
        WEB3MutualFundPositionManager l_mutualFundPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getPositionManager();

        // 解約可能残高口数を取得する
        // 1.10) 　@拡張投信ポジションマネージャ.calc解約可能残高口数()
        double l_dblSellPossiblePositionQty =
            l_mutualFundPositionManager.calcSellPossiblePositionQty(
                l_subAccount,
                l_mfProduct,
                l_request.id);

        // 1.11) 解約可能残高口数==0 の場合、例外をスローする
        if(l_dblSellPossiblePositionQty == 0)
        {
            log.error("解約可能残高口数がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }

        // 1.12)getAsset
        Asset l_asset = null;
        try
        {
            l_asset = l_mutualFundPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("保有資産オブジェクトデータなし時エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_asset == null)
        {
            log.error("保有資産オブジェクトデータなし時エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産オブジェクトデータなし時エラー。");
        }

        // 処理区分
        String l_strProcessDiv = null;

        if (WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
        {
            l_strProcessDiv = WEB3ProcessDivDef.SELL;
        }
        else
        {
            if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }
            else
            {
                log.debug("投信乗換確認リクエスト.請求方法@不正");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        // 1.13) validate新規注文(補助口座, 拡張投信銘柄, 注文数量, 処理区分, 受渡方法@, 指定方法@, 乗換先銘柄)(

        //［validate新規注文に渡すパラメタ］
        //補助口座： 取得した補助口座オブジェクト
        //拡張投信銘柄： 取得した乗換元拡張投信銘柄オブジェクト
        //注文金額数量： リクエストデータ.数量
        //処理区分：
        //(*) リクエストデータ.請求方法@の値が”0：解約請求”の場合は ”2：解約”を指定
        //(*) リクエストデータ.請求方法@の値が”1：買取請求”の場合は ”4：買取”を指定
        //受渡方法@： null
        //指定方法@： リクエストデータ.指定方法@
        //乗換先銘柄： 取得した乗換先銘柄オブジェクト
        //税区分： 取得した保有資産.税区分
        //決済方法@： null 
        double l_dblOrderQuantity = 0.0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
        {
            l_dblOrderQuantity =
                Double.parseDouble(l_request.mutualOrderQuantity);
        }
        NewOrderValidationResult l_newOrderResult =
            l_orderManager.validateNewOrder(
                l_subAccount,
                l_mfProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                null,
                l_request.specifyDiv,
                l_swtProduct,
                l_asset.getTaxType(),
                null);
        ProcessingResult l_processingResult =
            l_newOrderResult.getProcessingResult();

        if (l_processingResult.isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }

        //1.14) is金額指定解約中
        //[引数]
        // 補助口座： 取得した補助口座オブジェクト
        // 拡張投信銘柄： 取得した乗換元の投信銘柄オブジェクト
        // 税区分： 取得した保有資産.税区分
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        boolean l_blnIsPriceDesignateCancelling =
            l_validationsCheck.isPriceDesignateCancelling(
                l_subAccount,
                l_mfProduct,
                l_asset.getTaxType());

        //1.15) 分岐：リクエスト.指定方法@!=”全部” and （ リクエスト.指定方法@==”金額”
        //or is金額指定解約中()の戻り値==true ） の場合、
        //解約口数拘束率を超過していないかチェックする。

        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }

        boolean l_blnIsSellQtyLimitRateExcess = false;
        if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) &&
            (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) ||
              l_blnIsPriceDesignateCancelling))
        {
            //1.15.1)is解約口数拘束率超過
            //補助口座： 取得した補助口座オブジェクト
            //銘柄： 取得した乗換元拡張投信銘柄オブジェクト
            //銘柄（乗換先）： 取得した乗換先拡張投信銘柄オブジェクト
            //資産ID：リクエスト.ID
            //解約可能残高口数：calc解約可能残高口数()の戻り値
            //処理区分： ”乗換”
            //注文数量： リクエスト.数量
            //指定方法@： リクエスト.指定方法@
            //決済方法@： ”円貨”
            //請求方法@： リクエスト.請求方法@
            //口座区分： （以下のとおり）
            //取得した保有資産.税区分 == ”一般” の場合、”一般”
            //取得した保有資産.税区分 == ”特定” の場合、”特定”
            //発注日： リクエスト.発注日
            l_blnIsSellQtyLimitRateExcess =
                l_validationsCheck.isSellQtyLimitRateExcess(
                    l_subAccount,
                    l_mfProduct,
                    l_swtProduct,
                    l_request.id,
                    l_dblSellPossiblePositionQty,
                    WEB3ProcessDivDef.SWITCHING,
                    l_dblOrderQuantity,
                    l_request.specifyDiv,
                    WEB3SettlementDivDef.JAPANESE_CURRENCY,
                    l_request.sellBuyDiv,
                    l_strTaxType,
                    l_request.orderedDate);

            //1.15.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする
            if (l_blnIsSellQtyLimitRateExcess)
            {
                log.debug("解約口数拘束率を超過している。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "解約口数拘束率を超過している。");
            }
        }

        //1.16)リクエストデータ.指定方法@の値が”2：全部”の場合、
        //リクエストデータ.数量に取得した解約可能残高口数を設定する。
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_dblOrderQuantity = l_dblSellPossiblePositionQty;
        }

        //1.17) get乗換発注日
        // 乗換注文の発注日を取得する
        // [引数]
        // 乗換元銘柄コード： リクエスト.銘柄コード
        // 乗換先銘柄コード： リクエスト.銘柄コード（乗換先)
        // 確認時発注日：リクエスト.発注日
        WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
            l_request.mutualProductCode, l_request.switchingProductCode, l_request.orderedDate);

        //1.18）get乗換約定日
        //［引数］
        //証券会社： 補助口座.getInstitution()の戻り値
        //乗換元銘柄コード： リクエスト.銘柄コード
        //乗換先銘柄コード： リクエスト.銘柄コード（乗換先）
        Timestamp l_tsSwtExecutedDate =
            l_productManager.getSwtExecutedDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 1.19）get乗換受渡日
        //［引数］
        //証券会社： 補助口座.getInstitution()の戻り値
        //乗換元銘柄コード： リクエスト.銘柄コード
        //乗換先銘柄コード： リクエスト.銘柄コード（乗換先）
        Timestamp l_tsDeliveryDate =
            l_productManager.getSwtDeliveryDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 1.20）　@概算受渡代金取得
        //［calc概算受渡代金に渡すパラメタ］
        //補助口座： 取得した補助口座オブジェクト
        //銘柄： 取得した乗換元拡張投信銘柄オブジェクト
        //銘柄（乗換先）： 取得した乗換先拡張投信銘柄オブジェクト
        //処理区分： ”乗換”
        //注文数量： リクエストデータ.数量
        //指定方法@：
        //(*) リクエストデータ.指定方法@が”全部”の場合は”口数”を設定する。
        //(*) そうでない場合はリクエストデータ.指定方法@を設定する。
        //決済方法@： ”円貨”
        //請求方法@： リクエストデータ.請求方法@
        //口座区分： （以下のとおり）
        //取得した保有資産.税区分 == ”一般” の場合、”一般”
        //取得した保有資産.税区分 == ”特定” の場合、”特定”
        //注文チャネル： セッションから取得した注文チャネル
        //発注日： リクエストデータ.発注日
        String l_strSpecifyDiv = null;
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        }
        else
        {
            l_strSpecifyDiv = l_request.specifyDiv;
        }

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        // 　@－拡張投信注文マネージャ.calc概算受渡代金()をコールして、
        //   概算受渡代金オブジェクトを取得する

        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mfProduct,
                l_swtProduct,
                WEB3ProcessDivDef.SWITCHING,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                WEB3SettlementDivDef.JAPANESE_CURRENCY,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate
            );

        // 保有残高口数超過チェック
        // 1.21) （取得した概算受渡代金オブジェクト.get概算売買口数( ) ＞ 取得した解約可能残高口数）　@の場合
        if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
        {
            log.debug("保有残高口数超過エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有残高口数超過エラー");
        }

        // 特定口座チェック
        // 1.22) 投信乗換確認リクエスト.買付口座区分の値が”1：特定”の場合
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.switchingTaxType))
        {
            try
            {
                //1.22.1) －拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する
                WEB3GentradeMainAccount l_account =
                    (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                        l_subAccount.getAccountId());

                // 1.22.2) 取得した顧客オブジェクト.is特定口座開設()をコールする
                boolean l_lbnIsSpecialAccountEstablished =
                    l_account.isSpecialAccountEstablished(
                        l_tsDeliveryDate,
                        l_subAccount);

                if (!l_lbnIsSpecialAccountEstablished)
                {
                    log.debug("特定口座チェックエラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "特定口座チェックエラー");
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.23) validate乗換先買付最低金額
        //[引数]
        // 補助口座： 補助口座オブジェクト
        // 指定区分： リクエスト.指定方法@
        // 概算売買代金： 乗換元銘柄の概算受渡代金オブジェクト.get概算受渡代金()の戻り値
        // 乗換先銘柄： 乗換先の銘柄オブジェクト
        l_orderManager.validateSwtBuyMinAmt(
            l_subAccount,
            l_request.specifyDiv,
            l_mfEstimatedPrice.getEstimatedPrice(),
            l_swtProduct);

        // 1.24) calc概算買付口数()
        // 乗換先銘柄の概算買付口数を算出する。

        //［calc概算買付口数に渡すパラメタ］
        //銘柄： 取得した乗換先拡張投信銘柄オブジェクト
        //注文数量： 取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値

        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();

        double l_swtEstimatedQty =
            l_mfBizLogicProvider.calcEstimatedBuyQty(
                l_swtProduct,
                l_mfEstimatedPrice.getEstimatedPrice());

        //1.25 乗換先の概算買付口数が0の場合は例外をスローする。
        if(l_swtEstimatedQty == 0)
        {
            log.error("乗換先の買付可能数量が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02000,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換先の買付可能数量が0以下です。");
        }

        //1.26) get源泉徴収拘束金
        //[引数]
        // 補助口座： 補助口座オブジェクト
        // 請求区分： リクエスト.請求方法@
        // 保有資産ID： リクエスト.ID
        // 概算受渡代金： 乗換元銘柄の概算受渡代金オブジェクト.get概算受渡代金()の戻り値
        Double l_dblSourceRestraintPrice =
            l_orderManager.getWithholdingTaxRestriction(
                l_subAccount,
                l_request.sellBuyDiv,
                l_request.id,
                l_mfEstimatedPrice.getEstimatedPrice());

        //1.27) 拡張投信新規注文内容
        //[引数]
        // 代理入力者： this.get代理入力者()の戻り値
        // is買付： false
        // 銘柄コード： リクエストデータ.銘柄コード
        // 注文数量：
        // (*) リクエストデータ.指定方法@==”全部” and
        // is金額指定解約中()の戻り値==true の場合は
        // 0を指定。
        // (*) それ以外の場合は
        // リクエストデータ.数量を指定。
        // 注文数量タイプ：
        //(*) リクエストデータ.指定方法@==”全部” or ”口数”の場合は
        //QuantityTypeEnum.数量を指定。
        //(*) リクエストデータ.指定方法@が”金額”の場合は
        //QuantityTypeEnum.金額を指定。
        // 税区分： 保有資産テーブルParams.get税区分()の戻り値

        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) &&
            l_blnIsPriceDesignateCancelling)
        {
            l_dblOrderQuantity = 0;
        }
        QuantityTypeEnum l_quantityType = null;
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) ||
            WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.QUANTITY;
        }
        else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.AMOUNT;
        }

        WEB3MutualFundNewOrderSpec l_mfNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                this.getTrader(),
                false,
                l_request.mutualProductCode,
                l_dblOrderQuantity,
                l_quantityType,
                l_asset.getTaxType());

        //1.28) 投信新規注文確定インタセプタ（乗換）
        WEB3MutualFundNewOrderSwtConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderSwtConfirmInterceptor();

        //1.29) (*)プロパティセット
        //①@受渡日を設定する。
        //　@　@[set受渡日に渡すパラメタ]
        //　@　@受渡日： 取得した受渡日
        l_interceptor.setDeliveryDate(l_tsDeliveryDate);

        //②注文チャネルを設定する。
        //　@　@[set注文チャネルに渡すパラメタ]
        //　@　@注文チャネル： this.getログインチャネル()の戻り値
        l_interceptor.setOrderChannel(this.getLoginChannel());

        //③計算基準価額を設定する。
        //　@　@[set計算基準価額に渡すパラメタ]
        //　@　@計算基準価額： 取得した拡張投信銘柄オブジェクト.get解約価額()の戻り値
        l_interceptor.setConstantValue(l_mfProduct.getSellValue());

        //④計算基準価額（乗換先）を設定する。
        //　@　@[set計算基準価額（乗換先）に渡すパラメタ]
        //　@　@計算基準価額（乗換先）： 取得した乗換先銘柄オブジェクト.get買付基準価額()の戻り値
        l_interceptor.setSwitchingConstantValue(l_swtProduct.getConstantValue());

        //⑤基準価額適用日を設定する。
        //　@　@[set基準価額適用日に渡すパラメタ]
        //　@　@基準価額適用日： 取得した拡張投信銘柄オブジェクト.get基準価額適用日()の戻り値
        l_interceptor.setConstantValueAppDate(l_mfProduct.getConstantValueAppDate());

        //⑥概算受渡代金を設定する。
        //[set概算受渡代金に渡すパラメタ]
        //概算受渡代金：  取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値を指定。
        l_interceptor.setEstimatedPrice(l_mfEstimatedPrice.getEstimatedPrice());

        //⑦概算売買口数を設定する。
        //[set概算売買口数に渡すパラメタ]
        //概算売買口数：取得した概算受渡代金オブジェクト.get概算売買口数()の戻り値を指定。
        l_interceptor.setEstimatedQty(l_mfEstimatedPrice.getEstimatedQty());

        //⑧概算買付口数（乗換先）を設定する。
        //[set概算買付口数（乗換先）に渡すパラメタ]　@
        //概算買付口数（乗換先）：取得した乗換先の概算受渡代金オブジェクト.get概算売買口数()の戻り値を指定。
        l_interceptor.setSwitchingEstimatedQty(l_swtEstimatedQty);

        //⑨税区分（乗換先）を設定する。
        //[set税区分（乗換先）に渡すパラメタ]
        //税区分（乗換先）：
        //(*) リクエストデータ.買付口座区分が”0：一般”の場合、
        //TaxTypeEnum.NORMALを設定する。
        //(*) リクエストデータ.買付口座区分が”1：特定”の場合、
        //TaxTypeEnum.SPECIALを設定する。
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.switchingTaxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.switchingTaxType))
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        l_interceptor.setSwitchingSubjectTaxDivision(l_taxType);

        //⑩受渡方法@を設定する。
        //[set受渡方法@に渡すパラメタ]
        //受渡方法@： null
        l_interceptor.setDeliveryDiv(null);

        //⑪投信タイプを設定する。
        //[set投信タイプに渡すパラメタ]
        //投信タイプ： 取得した拡張投信銘柄.getMutualFundType()の戻り値
        l_interceptor.setMutualFundType(l_mfProduct.getMutualFundType().intValue() + "");

        //⑫投信解約区分を設定する。
        //[set投信解約区分に渡すパラメタ]
        //投信解約区分： リクエストデータ.指定方法@
        l_interceptor.setMutualFundSellDiv(l_request.specifyDiv);

        //⑬約定日を設定する。
        //[set約定日に渡すパラメタ]
        //約定日： 取得した約定日
        l_interceptor.setExecutionTimestamp(l_tsSwtExecutedDate);

        //⑭決済区分を設定する。
        //[set決済区分に渡すパラメタ]
        //決済区分： ”1：円貨”
        l_interceptor.setSettlementType(WEB3SettlementDivDef.JAPANESE_CURRENCY);

        //⑮無手数料区分を設定する。
        //[set無手数料区分に渡すパラメタ]
        //無手数料区分：
        //拡張投信注文マネージャ.is手数料無料顧客() == true の場合、”無手数料”
        //それ以外の場合、ブランク

        //　@　@[is手数料無料顧客に渡すパラメータ]
        //　@　@顧客： 補助口座.getMainAccount()
        //　@　@銘柄： 取得した乗換先銘柄オブジェクト
        if (l_orderManager.isCommissionFreeAccount(l_genMainAccount, l_swtProduct))
        {
            l_interceptor.setNoCommissionDivision(WEB3CommissionDivDef.NO_COMMISSION);
        }
        else
        {
            l_interceptor.setNoCommissionDivision(" ");
        }

        //⑯銘柄コード（乗換先）を設定する。
        //[set銘柄コード（乗換先）に渡すパラメタ]
        //銘柄コード（乗換先）： 取得した乗換先銘柄オブジェクト.getProductCode()の戻り値
        l_interceptor.setSwitchingSubjectMutualProductCode(l_swtProduct.getProductCode());

        //⑰請求区分を設定する。
        //[set請求区分に渡すパラメタ]
        //請求区分： リクエストデータ.請求方法@
        l_interceptor.setRequestDivision(l_request.sellBuyDiv);

        //⑱注文経路区分を設定する。
        //[set注文経路区分に渡すパラメタ]
        //注文経路区分： セッションから取得した同項目の値
        l_interceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //⑲源泉徴収拘束金を設定する。
        //[set源泉徴収拘束金に渡すパラメタ]
        //源泉徴収拘束金： get源泉徴収拘束金()の戻り値
        l_interceptor.setWithholdingTaxRestriction(l_dblSourceRestraintPrice);

        //⑳出金注文識別コードを設定する。
        //[set出金注文識別コードに渡すパラメタ]
        //出金注文識別コード： null
        l_interceptor.setPaymentOrderReqNumber(null);

        //発注日を設定する。
        //   [set発注日に渡すパラメタ]
        //   発注日：リクエストデータ.発注日
        String l_strOrderedDate = WEB3DateUtility.formatDate(l_request.orderedDate, "yyyyMMdd");
        l_interceptor.setBizDate(l_strOrderedDate);

        //1.30) validate取引余力
        //[引数]
        // 補助口座： 補助口座オブジェクト
        // 注文内容インタセプタ： 投信新規注文確定インタセプタ（乗換）を要素とした配列
        // 注文内容： 拡張投信新規注文内容を要素とした配列
        // 注文種別： OrderTypeEnum.投資信託乗換注文
        // 余力更新フラグ： false
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_interceptor};

        Object[] l_arrNewOrderSpecs = {l_mfNewOrderSpec};
        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_SWITCHING,
                false);

        //is判定フラグ()の戻り値がfalseの場合、[源泉徴収拘束金余力チェックエラー]として例外をスローする
        if(!l_result.isResultFlg())
        {
            log.debug("源泉徴収拘束金余力チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02324,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "源泉徴収拘束金余力チェックエラー");
        }

        //1.31) 拡張投信注文マネージャ.createNewOrderId()をコールして注文IDを取得する。
        long l_lngOrderId =
            l_orderManager.createNewOrderId();

        // 1.32) 投信乗換確認レスポンスオブジェクトを生成し、リターンする
        WEB3MutualSwitchingConfirmResponse l_response =
            (WEB3MutualSwitchingConfirmResponse) l_request.createResponse();

        // 1.33) 投信乗換確認レスポンスオブジェクトには、以下の値を設定する。

        // 　@　@解約注文内容警告区分：
        if (l_blnIsSellQtyLimitRateExcess)
        {
            l_response.sellWarningType = "1";
        }
        else
        {
            l_response.sellWarningType = null;
        }

        // 　@　@概算買付口数（乗換先）：
        //取得した乗換先の概算受渡代金オブジェクト.get概算売買口数()の戻り値を設定する
        l_response.switchingEstimatedQty =
            WEB3StringTypeUtility.formatNumber(l_swtEstimatedQty);

        //概算受渡代金：
        //リクエスト.指定方法@ == ”口数指定” の場合、
        //取得した乗換元の概算受渡代金オブジェクト.get概算受渡代金()の戻り値を設定する。
        if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_response.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_mfEstimatedPrice.getEstimatedPrice());
        }

        //数量： 投信乗換確認リクエスト.数量
        l_response.mutualOrderQuantity =
            WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
        l_response.orderId = l_lngOrderId + "";

        return l_response;
    }

    /**
     * (submit乗換)<BR>
     * 投資信託乗換登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投信）乗換登録」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信）乗換登録」: <BR>
     *        23((保有残高口数超過チェック <BR>
     *        取得した概算受渡代金オブジェクト.get概算売買口数()＞ <BR>
     *         取得した解約可能残高口数、の場合は例外をスローする。<BR>
     *        （保有残高口数超過エラー）<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信）乗換登録」: <BR>
     *        14((保有資産の取得 <BR>
     *        投信拡張ポジションマネージャ.getAsset( )をコールする <BR>
     *         検索結果が0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00204<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)解約審査」: <BR>
     *        13) 解約可能残高口数を取得する
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信）乗換審査」: <BR>
     *       17.1) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする<BR>
     *         解約可能残高口数==0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40557E2A002D
     */
    protected WEB3MutualSwitchingCompleteResponse submitSwitching(
         WEB3MutualSwitchingCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSwitching(WEB3MutualSwitchingCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）　@入力内容チェック
        l_request.validate();

        // ２）　@補助口座取得
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        // ３）　@拡張投信銘柄取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //－拡張投信銘柄マネージャを取得する
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //－拡張投信注文マネージャを取得する
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //  －拡張投信ポジションマネージャの取得
        WEB3MutualFundPositionManager l_mutualFundPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();

        // －拡張アカウントマネージャの取得
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_swtProduct = null;

        try
        {
            // －拡張投信銘柄マネージャ.get投信銘柄()をコールし、拡張投信銘柄を取得する
            l_mfProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);

            // ４）　@乗換先銘柄オブジェクトの取得
            // 　@拡張投信銘柄マネージャ.get投信銘柄()をコールして、乗換先銘柄の拡張投信銘柄
            l_swtProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.switchingProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("拡張投信銘柄取得できない場合エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ５）　@顧客別取引停止属性チェック
        //FinAppクラスのgetCommonOrderValidator()をコールし
        //注文チェックオブジェクトを取得する
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        Timestamp l_datBizDate =
            new Timestamp(l_request.orderedDate.getTime());
        // 6）validate取引可能顧客(顧客, 発注日)
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

        //1.8)－validate取引パスワード( )をコールする。
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

        // 9）　@受付時間チェック、システム取引停止チェック
        // 　@－投信取引時間管理.validete注文受付可能()をコールする
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // 10）取引時間の再設定
        // 　@－投信取引時間管理.reset銘柄コード()をコール
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        //11） 　@－受付日時、日付ロールをセットする
        // 　@　@　@投信取引時間管理.setTimestamp()をコールする
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        // 1.12）　@解約可能残高口数を取得する
        // 　@拡張投信ポジションマネージャ.calc解約可能残高口数()
        double l_dblSellPossiblePositionQty =
            l_mutualFundPositionManager.calcSellPossiblePositionQty(
                l_subAccount,
                l_mfProduct,
                l_request.id);

        //1.13 解約可能残高口数==0 の場合、例外をスローする。
        if(l_dblSellPossiblePositionQty == 0)
        {
            log.error("解約可能残高口数がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }

        //1.14）　@保有資産の取得
        //－投信拡張ポジションマネージャ.getAsset( )をコールする。
        //　@[引数]
        //　@arg0： リクエスト.ID
        //検索結果が0件の場合、例外をスローする。
        Asset l_asset = null;
        try
        {
            l_asset = l_mutualFundPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("保有資産オブジェクトデータなし時エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_asset == null)
        {
            log.error("保有資産オブジェクトデータなし時エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産オブジェクトデータなし時エラー。");
        }

        // 処理区分
        String l_strProcessDiv = "";

        if (WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
        {
            l_strProcessDiv = WEB3ProcessDivDef.SELL;
        }
        else
        {
            if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }
            else
            {
                log.debug("投信乗換完了リクエスト.請求方法@不正");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        // 1.15)　@－拡張投信注文マネージャ.validate新規注文()
        //［validate新規注文に渡すパラメタ］
        // 補助口座： 取得した補助口座オブジェクト
        // 拡張投信銘柄： 取得した乗換元拡張投信銘柄オブジェクト
        // 注文金額数量： リクエストデータ.数量
        // 処理区分：
        // (*) リクエストデータ.請求方法@の値が”0：解約請求”の場合は
        // ”2：解約”を指定
        // (*) リクエストデータ.請求方法@の値が”1：買取請求”の場合は
        // ”4：買取”を指定
        // 受渡方法@： null
        // 指定方法@： リクエストデータ.指定方法@
        // 乗換先銘柄： 取得した乗換先銘柄オブジェクト
        // 税区分： 取得した保有資産.税区分
        // 決済方法@： null 
        double l_dblOrderQuantity = 0.0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
        {
            l_dblOrderQuantity =
                Double.parseDouble(l_request.mutualOrderQuantity);
        }

        NewOrderValidationResult l_newOrderResult =
            l_orderManager.validateNewOrder(
                l_subAccount,
                l_mfProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                null,
                l_request.specifyDiv,
                l_swtProduct,
                l_asset.getTaxType(),
                null);
        ProcessingResult l_orderProcessResult =
            l_newOrderResult.getProcessingResult();
        if (l_orderProcessResult.isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderProcessResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }

        //1.16) is金額指定解約中
        //[引数]
        // 補助口座： 取得した補助口座オブジェクト
        // 拡張投信銘柄： 取得した乗換元の投信銘柄オブジェクト
        // 税区分： 取得した保有資産.税区分
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        boolean l_blnIsPriceDesignateCancelling =
            l_validationsCheck.isPriceDesignateCancelling(
                l_subAccount,
                l_mfProduct,
                l_asset.getTaxType());

        //1.17) 分岐：リクエスト.指定方法@!=”全部” and （ リクエスト.指定方法@==”金額”
        //or is金額指定解約中()の戻り値==true ） の場合、
        //解約口数拘束率を超過していないかチェックする。

        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }

        if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) &&
          (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) || l_blnIsPriceDesignateCancelling))
        {
            //1.17.1)is解約口数拘束率超過
            //補助口座： 取得した補助口座オブジェクト
            //銘柄： 取得した拡張投信銘柄オブジェクト
            //銘柄（乗換先）：取得した乗換先拡張投信銘柄オブジェクト
            //資産ID：リクエスト.ID
            //解約可能残高口数：calc解約可能残高口数()の戻り値
            //処理区分： ”乗換”
            //注文数量： リクエスト.数量
            //指定方法@： リクエスト.指定方法@
            //決済方法@： ”円貨”
            //請求方法@： リクエスト.請求方法@
            //口座区分： リクエスト.口座区分
            //取得した保有資産.税区分 == ”一般” の場合、”一般”
            //取得した保有資産.税区分 == ”特定” の場合、”特定”
            //発注日： リクエスト.発注日
            boolean l_blnIsSellQtyLimitRateExcess =
                l_validationsCheck.isSellQtyLimitRateExcess(
                     l_subAccount,
                     l_mfProduct,
                     l_swtProduct,
                     l_request.id,
                     l_dblSellPossiblePositionQty,
                     WEB3ProcessDivDef.SWITCHING,
                     l_dblOrderQuantity,
                     l_request.specifyDiv,
                     WEB3SettlementDivDef.JAPANESE_CURRENCY,
                     l_request.sellBuyDiv,
                     l_strTaxType,
                     l_request.orderedDate);

            //1.17.2) is解約口数拘束率超過()の戻り値がtrueの場合、例外をスローする
            if (l_blnIsSellQtyLimitRateExcess)
            {
                log.error("解約口数拘束率を超過している。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "解約口数拘束率を超過している。");
            }
        }

        //1.18)リクエストデータ.指定方法@の値が”2：全部”の場合、
        //リクエストデータ.数量に取得した解約可能残高口数を設定する。
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_dblOrderQuantity= l_dblSellPossiblePositionQty;
        }

        // 19) get乗換発注日
        // 乗換注文発注日を取得する。
        // [引数]
        //乗換元銘柄コード： リクエスト.銘柄コード
        //乗換先銘柄コード： リクエスト.銘柄コード（乗換先）
        //確認時発注日：リクエスト.発注日
        WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
            l_request.mutualProductCode, l_request.switchingProductCode, l_request.orderedDate);

        // 20）get乗換約定日
        //［引数］
        //証券会社： 補助口座.getInstitution()の戻り値
        //乗換元銘柄コード： リクエスト.銘柄コード
        //乗換先銘柄コード： リクエスト.銘柄コード（乗換先）
        Timestamp l_datExecutedDate =
            l_productManager.getSwtExecutedDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 21）get乗換受渡日
        //［引数］
        //証券会社： 補助口座.getInstitution()の戻り値
        //乗換元銘柄コード： リクエスト.銘柄コード
        //乗換先銘柄コード： リクエスト.銘柄コード（乗換先）
        Timestamp l_datDeliveryDate =
            l_productManager.getSwtDeliveryDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 22）　@概算受渡代金取得
        //［calc概算受渡代金に渡すパラメタ］
        // 補助口座： 取得した補助口座オブジェクト
        // 銘柄： 取得した乗換元拡張投信銘柄オブジェクト
        // 銘柄（乗換先）： 取得した乗換先拡張投信銘柄オブジェクト
        // 処理区分： ”乗換”
        // 注文数量： リクエストデータ.数量
        // 指定方法@：
        // (*) リクエストデータ.指定方法@が”全部”の場合は”口数”を設定する。
        // (*) そうでない場合はリクエストデータ.指定方法@を設定する。
        // 決済方法@： ”円貨”
        // 請求方法@： リクエストデータ.請求方法@
        // 座区分： （以下のとおり）
        //取得した保有資産.税区分 == ”一般” の場合、”一般”
        //取得した保有資産.税区分 == ”特定” の場合、”特定”
        // 注文チャネル： セッションから取得した注文チャネル
        // 発注日： リクエストデータ.発注日

        String l_strSpecifyDiv = "";
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        }
        else
        {
            l_strSpecifyDiv = l_request.specifyDiv;
        }
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mfProduct,
                l_swtProduct,
                WEB3ProcessDivDef.SWITCHING,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                WEB3SettlementDivDef.JAPANESE_CURRENCY,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate);

        // 23）　@保有残高口数超過チェック
        if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
        {
            log.debug("保有残高口数超過エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有残高口数超過エラー");
        }

        // 24）　@特定口座チェック
        // 　@投信乗換完了リクエスト.買付口座区分の値が”1：特定”の場合
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.switchingTaxType))
        {
            try
            {
                //－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する
                WEB3GentradeMainAccount l_account =
                    (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                        l_subAccount.getAccountId());

                // 　@－取得した顧客オブジェクト.is特定口座開設()をコールする
                boolean l_blnIsSpecialAccountEstablished =
                    l_account.isSpecialAccountEstablished(
                        l_datDeliveryDate,
                        l_subAccount);
                if (!l_blnIsSpecialAccountEstablished)
                {
                    log.debug("特定口座チェックエラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "特定口座チェックエラー");
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.25) validate乗換先買付最低金額
        //[引数]
        // 補助口座： 補助口座オブジェクト
        // 指定区分： リクエスト.指定方法@
        // 概算売買代金： 乗換元銘柄の概算受渡代金オブジェクト.get概算受渡代金()の戻り値
        // 乗換先銘柄： 乗換先の銘柄オブジェクト
        l_orderManager.validateSwtBuyMinAmt(
            l_subAccount,
            l_request.specifyDiv,
            l_mfEstimatedPrice.getEstimatedPrice(),
            l_swtProduct);

        // 1.26) calc概算買付口数()
        // 乗換先銘柄の概算買付口数を算出する。

        //［calc概算買付口数に渡すパラメタ］
        //銘柄： 取得した乗換先拡張投信銘柄オブジェクト
        //注文数量： 取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値

        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();

        double l_swtEstimatedQty =
            l_mfBizLogicProvider.calcEstimatedBuyQty(
                l_swtProduct,
                l_mfEstimatedPrice.getEstimatedPrice());


        //1.27乗換先の概算買付口数が0の場合は例外をスローする。
        if(l_swtEstimatedQty == 0)
        {
            log.error("乗換先の買付可能数量が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02000,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換先の買付可能数量が0以下です。"
                );
        }

        //1.28) get源泉徴収拘束金
        //[引数]
        // 補助口座： 補助口座オブジェクト
        // 請求区分： リクエスト.請求方法@
        // 保有資産ID： リクエスト.ID
        // 概算受渡代金： 乗換元銘柄の概算受渡代金オブジェクト.get概算受渡代金()の戻り値
        Double l_dblSourceRestraintPrice =
            l_orderManager.getWithholdingTaxRestriction(
                l_subAccount,
                l_request.sellBuyDiv,
                l_request.id,
                l_mfEstimatedPrice.getEstimatedPrice());

        //1.29) get約定日
        //乗換元銘柄の約定日を取得する。
        //[引数]
        // 証券会社： 補助口座.getInstitution()の戻り値
        // 銘柄コード： リクエスト.銘柄コード
        // 発注日：リクエスト.発注日
        Date l_datSwtExecutedDate = l_productManager.getExecutedDate(
            l_subAccount.getInstitution(),
            l_request.mutualProductCode,
            l_request.orderedDate);

        //1.30)  投信新規注文確定インタセプタ（乗換）
        WEB3MutualFundNewOrderSwtConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderSwtConfirmInterceptor();

        //1.31)setThreadLocalPersistenceEventInterceptor(arg0 : MutualFundOrderManagerPersistenceEventInterceptor)
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.32）各パラメータのセット
        //投信新規注文確定インタセプタに受渡日を設定する
        //投信新規注文確定インタセプタ.set受渡日()をコールする
        Timestamp l_tsDeliveryDate = new Timestamp(l_datDeliveryDate.getTime());
        l_interceptor.setDeliveryDate(l_tsDeliveryDate);

        //投信新規注文確定インタセプタに注文チャネルを設定する
        //投信新規注文確定インタセプタ.set注文チャネル()
        l_interceptor.setOrderChannel(this.getLoginChannel());

        //投信新規注文確定インタセプタに計算基準価額を設定する
        //投信新規注文確定インタセプタ.set計算基準価額()をコールする
        l_interceptor.setConstantValue(
            l_mfProduct.getSellValue());

        //投信新規注文確定インタセプタに計算基準価額（乗換先）を設定する
        //投信新規注文確定インタセプタ.set計算基準価額（乗換先）()をコールする
        l_interceptor.setSwitchingConstantValue(
            l_swtProduct.getConstantValue());

        //投信新規注文確定インタセプタに基準価額適用日を設定する
        //投信新規注文確定インタセプタ.set基準価額適用日()をコールする
        l_interceptor.setConstantValueAppDate(
            l_mfProduct.getConstantValueAppDate());

        //投信新規注文確定インタセプタに概算受渡代金を設定する
        //投信新規注文確定インタセプタ.set概算受渡代金()をコールする
        l_interceptor.setEstimatedPrice(l_mfEstimatedPrice.getEstimatedPrice());

        //投信新規注文確定インタセプタに概算売買口数を設定する
        // 投信新規注文確定インタセプタ.set概算売買口数()をコールする
        l_interceptor.setEstimatedQty(l_mfEstimatedPrice.getEstimatedQty());

        //投信新規注文確定インタセプタに概算買付口数（乗換先）を設定する。
        //投信新規注文確定インタセプタ.set概算買付口数（乗換先）()をコールする。
        l_interceptor.setSwitchingEstimatedQty(l_swtEstimatedQty);

        //投信新規注文確定インタセプタに税区分（乗換先）を設定する。
        TaxTypeEnum l_taxTypeEnum = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.switchingTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        //投信新規注文確定インタセプタ.set税区分（乗換先）()をコールする。
        l_interceptor.setSwitchingSubjectTaxDivision(
            l_taxTypeEnum);

        //投信新規注文確定インタセプタに受渡方法@を設定する。
        //投信新規注文確定インタセプタ.set受渡方法@()をコールする。
        l_interceptor.setDeliveryDiv(null);

        //投信新規注文確定インタセプタに投信タイプを設定する。
        //投信新規注文確定インタセプタ.set投信タイプ()をコールする。
        l_interceptor.setMutualFundType(
            l_mfProduct.getMutualFundType().intValue() + "");

        //投信新規注文確定インタセプタに投信解約区分を設定する。
        //投信新規注文確定インタセプタ.set投信解約区分()をコールする。
        l_interceptor.setMutualFundSellDiv(
            l_request.specifyDiv);

        //投信新規注文確定インタセプタに約定日を設定する。
        //投信新規注文確定インタセプタ.set約定日()をコールする。
        Timestamp l_tsExecutedDate = new Timestamp(l_datExecutedDate.getTime());
        l_interceptor.setExecutionTimestamp(l_tsExecutedDate);

        //投信新規注文確定インタセプタに決済区分を設定する。
        //投信新規注文確定インタセプタ.set決済区分()をコールする。
        l_interceptor.setSettlementType(
            WEB3SettlementDivDef.JAPANESE_CURRENCY);

        //投信新規注文確定インタセプタに無手数料区分を設定する。
        //投信新規注文確定インタセプタ.set無手数料区分()をコールする。
        //[set無手数料区分に渡すパラメタ]
        //　@無手数料区分：
        //    拡張投信注文マネージャ.is手数料無料顧客() == true の場合、”無手数料”
        //    それ以外の場合、ブランク

        //    [is手数料無料顧客に渡すパラメータ]
        //    顧客： 補助口座.getMainAccount()
        //    銘柄：  取得した乗換先銘柄オブジェクト
        if (l_orderManager.isCommissionFreeAccount(l_genMainAccount, l_swtProduct))
        {
            l_interceptor.setNoCommissionDivision(WEB3CommissionDivDef.NO_COMMISSION);
        }
        else
        {
            l_interceptor.setNoCommissionDivision(" ");
        }

        //投信新規注文確定インタセプタに銘柄コード（乗換先）を設定する。
        //投信新規注文確定インタセプタ.set銘柄コード（乗換先）()をコールする。
        l_interceptor.setSwitchingSubjectMutualProductCode(
            l_swtProduct.getProductCode());

        //投信新規注文確定インタセプタに請求区分を設定する。
        //投信新規注文確定インタセプタ.set請求区分()をコールする。
        l_interceptor.setRequestDivision(l_request.sellBuyDiv);

        //投信新規注文確定インタセプタに注文経路区分を設定する。
        //投信新規注文確定インタセプタ.set注文経路区分()をコールする。
        l_interceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //⑲源泉徴収拘束金を設定する。
        // 　@[set源泉徴収拘束金に渡すパラメタ]
        //　@　@源泉徴収拘束金： get源泉徴収拘束金()の戻り値
        l_interceptor.setWithholdingTaxRestriction(l_dblSourceRestraintPrice);

        //⑳出金注文識別コードを設定する。
        // 　@[set出金注文識別コードに渡すパラメタ]
        //　@　@出金注文識別コード： null
        l_interceptor.setPaymentOrderReqNumber(null);

        //乗換元約定日を設定する。
        // 　@[set乗換元約定日に渡すパラメタ]
        //　@　@乗換元約定日： get約定日()の戻り値
        Timestamp l_tsSwtchingExecutedDate = new Timestamp(l_datSwtExecutedDate.getTime());
        l_interceptor.setSwitchingExecutionTimestamp(l_tsSwtchingExecutedDate);

        //発注日を設定する。
        //   [set発注日に渡すパラメタ]
        //   発注日：リクエストデータ.発注日
        String l_strOrderedDate = WEB3DateUtility.formatDate(l_request.orderedDate, "yyyyMMdd");
        l_interceptor.setBizDate(l_strOrderedDate);

        //注文数量タイプ
        QuantityTypeEnum l_qtyTypeEnum = null;
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
        }
        else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_qtyTypeEnum = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
        }

        //1.33）新規注文内容の生成
        //拡張投信新規注文内容を生成する。
        //[拡張投信新規注文内容のコンストラクタに渡すパラメタ]
        //代理入力者： this.get代理入力者()の戻り値
        //is買付： false
        //銘柄コード： リクエストデータ.銘柄コード
        //注文数量：
        //(*) リクエストデータ.指定方法@==”全部” and
        //is金額指定解約中()の戻り値==true の場合は
        //0を指定。
        //(*) それ以外の場合は
        //リクエストデータ.数量を指定。
        //注文数量タイプ：
        //(*) リクエストデータ.指定方法@が”2：全部”の場合は
        //QuantityTypeEnum.数量を指定。
        //(*) リクエストデータ.指定方法@が”3：金額”の場合は
        //QuantityTypeEnum.金額を指定。
        //(*) リクエストデータ.指定方法@が”4：口数”の場合は
        //QuantityTypeEnum.数量を指定。
        //税区分： 保有資産テーブルParams.get税区分()の戻り値
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && l_blnIsPriceDesignateCancelling)
        {
            l_dblOrderQuantity = 0;
        }

        WEB3MutualFundNewOrderSpec l_mfNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                this.getTrader(),
                false,
                l_request.mutualProductCode,
                l_dblOrderQuantity,
                l_qtyTypeEnum,
                l_asset.getTaxType());

        //1.34) validate取引余力
        //[引数]
        // 補助口座： 補助口座オブジェクト
        // 注文内容インタセプタ： 投信新規注文確定インタセプタ（乗換）を要素とした配列
        // 注文内容： 拡張投信新規注文内容を要素とした配列
        // 注文種別： OrderTypeEnum.投資信託乗換注文
        // 余力更新フラグ： false
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_interceptor};

        Object[] l_arrNewOrderSpecs = {l_mfNewOrderSpec};
        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_SWITCHING,
                true);

        //is判定フラグ()の戻り値がfalseの場合、[源泉徴収拘束金余力チェックエラー]として例外をスローする
        if(!l_result.isResultFlg())
        {
            log.debug("源泉徴収拘束金余力チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02324,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "源泉徴収拘束金余力チェックエラー");
        }

        //1.35）乗換
        //拡張投信注文マネージャ.submitNewOrder()をコールする。
        OrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_mfNewOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                true);
        //拡張投信注文マネージャ.submitNewOrder()の戻り値判定
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("新規注文失敗");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "新規注文失敗");
        }

        //1.36リクエストデータ．紹介区分　@!= NULL　@の場合
        if (!WEB3StringTypeUtility.isEmpty(l_request.introduceStoreDiv))
        {
            //1.36.1 getOrderUnits(long)
            //投信注文単位オブジェクトを取得する。
            //［getOrderUnitsに渡すパラメタ］
            //　@　@　@arg0： リクエスト.注文ID
            OrderUnit[] l_orderUnits =
                l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));

            MutualFundOrderUnit[] l_mfOrderUnits =
                new MutualFundOrderUnit[l_orderUnits.length];
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                l_mfOrderUnits[i] = (MutualFundOrderUnit) l_orderUnits[i];
            }
            MutualFundOrderUnitRow l_mfOrderUnitRow =
                (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
            MutualFundOrderUnitParams l_mfOrderUnitParams =
                new MutualFundOrderUnitParams(l_mfOrderUnitRow);

            //1.36.2注文単位紹介区分( )
            OrderUnitIntroduceDivParams l_introduceDivParams =
                new OrderUnitIntroduceDivParams();

            //1.36.3＜プロパティセット＞
            //注文単位ID   =　@取得した注文単位.注文単位ID
            //口座ID         =  取得した注文単位.口座ID
            //銘柄タイプ     =  引数にProductTypeEnum.投資信託
            //紹介区分      =  リクエスト.紹介区分
            //紹介店コード  =  リクエスト.紹介店コード
            //更新者コード
            //　@ ・顧客入力の場合
            //       　@取得した注文単位.口座IDに該当する口座コードをセットする。
            // 　@・代理入力の場合
            //       　@取得した注文単位.取引者IDに該当する扱者コードをセットする。
            l_introduceDivParams.setOrderUnitId(l_mfOrderUnitParams.getOrderId());
            l_introduceDivParams.setAccountId(l_mfOrderUnitParams.getAccountId());
            l_introduceDivParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_introduceDivParams.setIntroduceBranchDiv(l_request.introduceStoreDiv);
            l_introduceDivParams.setIntroduceBranchCode(l_request.introduceStoreCode);
            if (this.getTrader() == null)
            {
                try
                {
                    l_genMainAccount =
                        (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                            l_mfOrderUnitParams.getAccountId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_genMainAccount.getAccountCode());
            }
            else
            {
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Trader l_trder = null;
                try
                {
                    l_trder = l_finObjMgr.getTrader(l_mfOrderUnitParams.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_trder.getTraderCode());
            }

            //作成日付 = 現在時刻
            l_introduceDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //更新日付 = 現在時刻
            l_introduceDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //1.36.4 saveNew注文単位紹介区分( )
            try
            {
                Processors.getDefaultProcessor().doInsertQuery(l_introduceDivParams);
            }
            catch (DataFindException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.37）処理日時の取得
        Date l_datProcessDate = null;
        l_datProcessDate =
            (Date) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3MutualFundTradingTimeManagement.TIMESTAMP_TAG);

        //1.38）投信乗換完了レスポンスオブジェクトを生成し、リターンする。
        //投信乗換完了レスポンスオブジェクトには、以下の値を設定する。
        WEB3MutualSwitchingCompleteResponse l_response =
            (WEB3MutualSwitchingCompleteResponse) l_request.createResponse();

        //1.39）プロパティセット
        //更新時間： 取得した処理日時
        l_response.lastUpdatedTimestamp = l_datProcessDate;
        //識別番号： リクエストデータ.注文ID
        l_response.orderActionId = l_request.orderId;

        return l_response;
    }
}
@
