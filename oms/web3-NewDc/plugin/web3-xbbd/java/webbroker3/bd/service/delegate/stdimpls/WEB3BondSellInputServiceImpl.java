head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却入力サービスImpl(WEB3BondSellInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 唐性峰 (中訊) 新規作成
                 : 2006/09/29 趙林鵬 (中訊) モデル 094
                   2006/10/12 柴雙紅 (中訊)  WEBⅢ開発標準の見直しの対応（new BigDecimal部分）
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedAssetCalcResult;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondPositionManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.bd.service.delegate.WEB3BondSellInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券売却入力サービスImpl)<BR>
 * 債券売却入力サービス実装クラス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellInputServiceImpl extends WEB3BondClientRequestService implements WEB3BondSellInputService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellInputServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A0177
     */
    public WEB3BondSellInputServiceImpl()
    {

    }

    /**
     * 債券売却入力サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図「債券売却入力」参照。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C0280C0299
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (! (l_request instanceof WEB3BondSellInputRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3BondSellInputRequest l_inputRequest = (WEB3BondSellInputRequest)l_request;

        //1.1 validate( )
        //リクエストデータの整合性をチェックする。
        l_inputRequest.validate();

        //1.2 getAsset(ID : long)
        //保有資産を取得する。
        //[引数]
        //ID： リクエストデータ.ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_bondPositionManager.getAsset(Long.parseLong(l_inputRequest.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 get債券銘柄(long)
        //債券銘柄を取得する。
        //[引数]
        //銘柄ID： 取得した保有資産.銘柄ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_asset.getProduct().getProductId());

        //1.4 validate注文受付可能(String)
        //受付時間チェック、緊急停止チェック、バッチ処理中チェックを行なう。
        //[引数]
        //債券銘柄：　@取得した債券銘柄
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.5 get補助口座( )
        //補助口座を取得する。
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate取引可能顧客(補助口座 : SubAccount)
        //顧客別取引停止属性チェックを行なう。
        //[引数]
        //補助口座： 取得した補助口座
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //1.7 validate外国証券口座開設(SubAccount, 債券銘柄)
        //外国証券口座開設チェックを行う。
        //[引数]
        //補助口座： 取得した補助口座
        //債券銘柄： 取得した債券銘柄
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateFeqAccountOpen(l_subAccount, l_bondProduct);

        //1.8 validate顧客取扱可能銘柄(債券銘柄, String)
        //取扱可能銘柄チェックを行う。
        //[引数]
        //債券銘柄： 取得した債券銘柄
        //取引区分： "売却"
        l_bondOrderManager.validateAccountHandlingPossibleProduct(
            l_bondProduct,
            WEB3BondDealDivDef.SELL);

        //1.9  calc概算評価額(補助口座, double, 債券銘柄, boolean, boolean)
        //債券概算評価額を算出する。
        //[引数]
        //補助口座： 取得した補助口座
        //数量： 保有資産.数量 － 保有資産.getロック中数量
        //債券銘柄： 取得した債券銘柄
        //is買付： false
        //is約定計算： false
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3BondEstimatedAssetCalcResult l_calcEstimatedAsset =
            l_bizLogicProvider.calcEstimatedAsset(
                l_subAccount,
                l_asset.getQuantity() - l_asset.getLockedQuantity(),
                l_bondProduct,
                false,
                false);

        //1.10 is外貨建( )
        //外貨建銘柄かどうかを判定する。
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.11 ＜分岐処理＞is外貨建()の戻り値 == true の場合
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.11.1 get通貨( )
            //（共通）通貨を取得する。
            WEB3GentradeCurrency l_currency = l_bondProduct.getCurrency();

            //1.11.2 get為替レート(is買付 : boolean, is約定計算 : boolean, 入力為替レート : double)
            //為替レートを取得する。
            //[引数]
            //is買付： false
            //is約定計算： false
            //入力為替レート： 0
            l_dblExchangeRate = l_currency.getExchangeRate(false, false, 0);
        }

        //1.12 createResponse( )
        //レスポンスデータを生成する。
        WEB3BondSellInputResponse l_response =
            (WEB3BondSellInputResponse)l_inputRequest.createResponse();

        //1.13 プロパティセット
        //売却可能額面金額　@　@＝　@保有資産.数量 －保有資産.getロック中数量
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_asset.getQuantity()));
        BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_asset.getLockedQuantity()));
        BigDecimal l_bdSellAbleFaceAmount = l_bdQuantity.subtract(l_bdLockedQuantity);
        l_response.sellAbleFaceAmount =
            WEB3StringTypeUtility.formatNumber(l_bdSellAbleFaceAmount.doubleValue());

        //概算評価額（円貨）　@＝　@債券概算評価額.get概算評価額（円貨）()の戻り値
        l_response.yenEstimatedAsset =
            WEB3StringTypeUtility.formatNumber(
                l_calcEstimatedAsset.getEstimatedAsset().doubleValue());

        //概算評価額（外貨）　@＝　@債券概算評価額.get概算評価額（外貨）()の戻り値
        if (l_calcEstimatedAsset.getForeignEstimatedAsset() == null)
        {
            l_response.foreignEstimatedAsset = null;
        }
        else
        {
            l_response.foreignEstimatedAsset =
                WEB3StringTypeUtility.formatNumber(
                    l_calcEstimatedAsset.getForeignEstimatedAsset().doubleValue());
        }

        //銘柄名　@　@　@　@　@　@　@＝　@債券銘柄.銘柄名
        l_response.productName = l_bondProduct.getProductName();

        //(*1)債券銘柄.is転換社債()の戻り値 == true　@の場合のみセットする。それ以外の場合、null。
        //口座(*1)　@　@　@　@　@　@＝　@保有資産.税区分 == "一般"の場合、"一般"をセット。
        //      　@　@"特定" or "特定口座かつ源泉徴収"の場合、"特定"をセット。
        if (l_bondProduct.isExperienceDivWt())
        {
            if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_asset.getTaxType()))
            {
                l_response.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
        }
        else
        {
            l_response.taxType = null;
        }

        //通貨コード　@　@　@　@　@＝　@債券銘柄.通貨コード
        l_response.currencyCode = l_bondProduct.getCurrencyCode();

        //売却（評価）単価　@　@＝　@債券銘柄.売却単価
        l_response.sellEvaluationPrice =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getSellPrice());

        //発行日　@　@　@　@　@　@　@＝　@債券銘柄.発行日
        l_response.issueDate = l_bondProduct.getIssueDate();

        //償還日　@　@　@　@　@　@　@＝　@債券銘柄.償還日
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //利払回数　@　@　@　@　@　@＝　@債券銘柄.年間利払回数
        l_response.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";

        //利払日１　@　@　@　@　@　@＝　@債券銘柄.利払日１
        l_response.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();

        //利払日２　@　@　@　@　@　@＝　@債券銘柄.利払日２
        l_response.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();

        //利率　@　@　@　@　@　@　@　@＝　@債券銘柄.利率
        l_response.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //申込単位　@　@　@　@　@　@＝　@債券銘柄.申込単位
        l_response.tradeUnit =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());

        //売却基準為替(*2)　@　@＝　@get為替レート()の戻り値
        //(*2)債券銘柄.is外貨建()の戻り値 == true　@の場合のみセットする。それ以外の場合、null。
        if (l_blnIsForeignCurrency)
        {
            l_response.sellBaseFx = WEB3StringTypeUtility.formatNumber(l_dblExchangeRate);
        }
        else
        {
            l_response.sellBaseFx = null;
        }

        //決済区分一覧　@　@　@　@＝　@債券銘柄.is外貨建()の戻り値 == false　@の場合、｢円貨｣をセット
        //　@　@　@　@　@　@　@　@　@　@　@　@債券銘柄.is外貨建()の戻り値 == true　@の場合、｢円貨｣、｢外貨｣をセット
        if (l_blnIsForeignCurrency)
        {
            l_response.settleDivList =
                new String[]{WEB3SettlementDivDef.JAPANESE_CURRENCY, WEB3SettlementDivDef.FOREIGN_CURRENCY};
        }
        else
        {
            l_response.settleDivList =
                new String[] {WEB3SettlementDivDef.JAPANESE_CURRENCY};
        }

        //1.14 レスポンスデータ

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
