head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文入力サービス実装クラス(WEB3MutualBuyInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 黄建 (中訊) レビュー
Revesion History : 2004/12/10 黄建 (中訊) 残対応
Revesion History : 2005/10/18 黄建 (中訊) フィデリティ対応
Revesion History : 2006/05/15 肖志偉(中訊) 仕様変更（モデル) :411,415
Revesion History : 2006/10/11 柴雙紅(中訊) 仕様変更 モデル No.500
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル524
Revesion History : 2007/04/06 唐性峰 (中訊) 実装005, モデル558, 560
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyInputService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託買付注文入力サービス実装クラス<BR>
 *
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyInputServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualBuyInputService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyInputServiceImpl.class);
    /**
     * (execute)<BR>
     *  シーケンス図 <BR>
     *「(投信)買付注文入力」参照。 <BR>
     *<BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)買付注文入力」: <BR>
     *         17(( is買付制限有り( )＝falseの場合false)の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)買付注文入力」: <BR>
     *         is外貨MMF == false かつ<BR>
     *         ( is外国投信() == true（外国銘柄）　@又は、isFWF() == true) で、<BR>
     *         is外国証券口座開設() == false（外国証券口座未開設）<BR>
     *         の場合、「外国証券口座未開設エラー」として例外をスロー<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01341<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B150C601AA
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
        WEB3MutualBuyInputRequest l_mutualBuyInputRequest = null;
        if (l_request instanceof WEB3MutualBuyInputRequest)
        {
            //リクエストデータの具象データ型が「投信買付注文入力リクエスト」の場合
            l_mutualBuyInputRequest = (WEB3MutualBuyInputRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualBuyInputRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1）　@入力内容チェック
        l_mutualBuyInputRequest.validate();

        //1.2）　@補助口座取得
        SubAccount l_subAccount = this.getSubAccount();

        //1.3）　@拡張投信銘柄取得
        //拡張投信銘柄マネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualBuyInputResponse l_response = null;
        try
        {
            WEB3MutualFundProduct l_product =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getProduct(
                    Long.parseLong(l_mutualBuyInputRequest.id));
            MutualFundProductRow l_mfProductRow =
                (MutualFundProductRow) l_product.getDataSourceObject();
            if (l_mfProductRow == null)
            {
                log.debug("ProductRow is null");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張投信銘柄取得できない場合エラー");
            }
            //1.4）拡張投信銘柄を取得する
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                l_mfProductRow);

            //1.5）is外国投信( )
            boolean l_blnIsForeignFund = l_mutualFundProduct.isForeignFund();

            //1.6）isFWF( )
            boolean l_blnIsFWF = l_mutualFundProduct.isFWF();

            //1.7) is再投資銘柄( )
            boolean l_blnIsPlowbackProduct = l_mutualFundProduct.isPlowbackProduct();

            //顧客
            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

            //is外貨MMF( )
            boolean l_blnIsFrgnMmf = l_mutualFundProduct.isFrgnMmf();

            //1.8）if is外国投信() == true　@又は　@isFWF() == true 又は is外貨MMF() == true
            if (l_blnIsForeignFund || l_blnIsFWF || l_blnIsFrgnMmf)
            {
                //1.8.1）is外国証券口座開設( )
                //is外国証券口座開設( )==false（外国証券口座未開設）の場合
                //「外国証券口座未開設エラー」として例外をスローする
                if (!l_genMainAccount.isForeignAccountOpen())
                {
                    log.debug("当該顧客は外国証券口座開設なし");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "当該顧客は外国証券口座開設なし。");
                }
            }

            // 1.9) if is再投資銘柄() == true
            if (l_blnIsPlowbackProduct)
            {
                log.debug("is再投資銘柄() == true");
                // 1.9.1) is累投口座開設()
                if (!l_genMainAccount.isRuitoAccountOpen())
                {
                    log.error(" __累投口座未開設エラー__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __累投口座未開設エラー__");
                }
            }

            //1.10) リクエスト.電子鳩チェックフラグ == True の場合、目論見書閲覧チェックを実施する
            WEB3GentradeProspectusResult l_validateBataResult = null;
            if(l_mutualBuyInputRequest.batoCheckFlag)
            {
                // 1.10.1) validate目論見書閲覧(String, String)
                //[引数]
                //  種別コード：リクエスト.種別コード
                //  銘柄コード：取得した拡張投信銘柄.getProductCode()の戻り値
                WEB3GentradeBatoClientService l_bataService =
                    (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

                l_validateBataResult =
                    l_bataService.validateProspectus(
                        l_mutualBuyInputRequest.typeCode,
                        l_mutualFundProduct.getProductCode());
            }

            //1.11）注文チェックオブジェクトを取得する  　@
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //1.12）現在の時刻が投信の受付時間区分タイプの取引時間に該当するか、
            //また緊急停止されていないか、あるいはバッチ処理中でないかチェックする。
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //1.13）取引時間の再設定
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_mutualFundProduct.getProductCode());

            //1.14）setTimestamp( )
            WEB3MutualFundTradingTimeManagement.setTimestamp();

            //1.15） get投信発注日( )
            //注文種別 ： OrderTypeEnum．投資信託買注文
            //一括区分 ： 取得した拡張投信銘柄．is特定日取引銘柄()の戻り値
            //[is特定日取引銘柄の引数]
            //注文種別 ： OrderTypeEnum．投資信託買注文
            Date l_datOrderBizDate =
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(
                    OrderTypeEnum.MF_BUY,
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));
            log.debug("l_datOrderBizDate = " + l_datOrderBizDate);

            //=============顧客別取引停止属性チェック==================start
            //1.16）－注文チェック.validate取引可能顧客()をコールする。
            OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(
                    l_genMainAccount,
                    new Timestamp(l_datOrderBizDate.getTime()));
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //=============顧客別取引停止属性チェック==================End

            //1.17）取扱可能銘柄チェック
            if (!l_mutualFundProduct.isSystemHandling())
            {
                log.debug(" __取扱不可銘柄エラー__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "__取扱不可銘柄エラー__");
            }

            //1.18） is買付可能(Date)
            boolean l_blnSellSwitchingPossible =
                l_mutualFundProduct.isAcquiredPossible(l_datOrderBizDate);
            if (!l_blnSellSwitchingPossible)
            {
                log.debug("is買付可能()が false を返す場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引不可銘柄エラー");
            }
            //1.19）is買付制限有り( )
            boolean l_blnAcquiredDeregExistence =
                l_mutualFundProduct.isAcquiredDeregExistence();
            if (l_blnAcquiredDeregExistence)
            {
                log.debug("is買付制限有り()が trueを返す場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引不可銘柄エラー");
            }
            //1.20）validate緊急停止(拡張投信銘柄, String)
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
                (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            try
            {
                l_validationsCheck.validateEmergencyStop(
                                l_mutualFundProduct,
                                WEB3ProcessDivDef.BUY);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("緊急停止エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "緊急停止エラー");
            }

            //1.21）validate注文受付可能( )
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();

            //validate外貨MMF二重注文(補助口座, 拡張投信銘柄, Date)
            //［validate外貨MMF二重注文に渡すパラメタ］ 
            // 補助口座       ： 取得した補助口座 
            //　@　@　@拡張投信銘柄 ： 取得した拡張投信銘柄 
            //      発注日          ： 取得した投信発注日
            l_validationsCheck.validateFrgnMmfDoubleOrder(
                l_subAccount,
                l_mutualFundProduct,
                l_datOrderBizDate);

            //1.22）約定日取得
            //［get約定日に渡すパラメタ］
            //証券会社： 取得した補助口座.getInstitution()の戻り値
            //銘柄コード： 取得した拡張投信銘柄.getProductCode()の戻り値
            //発注日    ：取得した投信発注日
            Timestamp l_tsExecutedDate =
                l_mutualFundProductManager.getExecutedDate(
                    l_subAccount.getInstitution(),
                    l_mutualFundProduct.getProductCode(),
                    l_datOrderBizDate);

            //1.23）　@受渡日取得
            //［get受渡日に渡すパラメタ］
            //証券会社： 取得した補助口座.getInstitution()の戻り値
            //銘柄コード： 取得した拡張投信銘柄.getProductCode()の戻り値
            //is買付： true
            //約定日　@： 取得した約定日

            Timestamp l_tsDeliveryDate =
                l_mutualFundProductManager.getDeliveryDate(
                    l_subAccount.getInstitution(),
                    l_mutualFundProduct.getProductCode(),
                    true,
                    l_tsExecutedDate);

            // 1.24）get投資信託買付可能額
            //買付可能額を取得する。
            //［get投資信託買付可能額に渡すパラメタ］
            //補助口座： 取得した補助口座オブジェクト
            //受渡日：　@取得した受渡日
            //注文種別：　@201：投資信託買注文

            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            double l_buyPossibleAmount =
                l_tpTradingPowerService.getMutualFundBuyTradingPower(
                    l_gentradeSubAccount,
                    l_tsDeliveryDate,
                    OrderTypeEnum.MF_BUY);

            //1.25）投信買付注文入力レスポンスオブジェクトを生成し、リターンする
            l_response =  (WEB3MutualBuyInputResponse) l_request.createResponse();

            //1.26）投信買付注文入力レスポンスオブジェクトには、以下の値を設定する
            l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_buyPossibleAmount);
            l_response.mutualProductCode = l_mutualFundProduct.getProductCode();
            l_response.mutualProductName =
                l_mutualFundProduct.getMutualProductName();
            l_response.constantValueCurrencyCode =
                l_mutualFundProduct.getCurrencyCode();
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            if (!l_mutualFundProductRow.getBuyConstantValueIsNull())
            {
                l_response.constantValue =
                    WEB3StringTypeUtility.formatNumber(l_mutualFundProduct.getConstantValue());
            }
            else
            {
                l_response.constantValue = null;
            }
            l_response.constantValueAppDate =
                l_mutualFundProduct.getConstantValueAppDate();
            //拡張アカウントマネージャ取得する
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する
            WEB3GentradeMainAccount l_gentradeMainAccount =
                (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());

            //    1) 拡張投信銘柄.is外貨MMF = trueの場合 
            //    　@　@ 2:その他を設定する。 
            // 　@ 2) 拡張投信銘柄.is外貨MMF = falseの場合 
            //     　@－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する。 
            //     　@　@［get顧客に渡すパラメタ］ 
            // 　@    　@　@口座ID： 取得した補助口座.getAccountId()の戻り値 
            //     　@－取得した顧客オブジェクト.is特定口座開設()をコールする。 
            // 　@    　@［is特定口座開設に渡すパラメタ］ 
            // 　@　@    　@補助口座： 取得した補助口座オブジェクト 
            // 　@　@　@    受渡日： 取得した受渡日 
            //     　@－is特定口座開設()が true かつ is株式型()がtrueを返す場合は、 
            // 　@    　@　@”0：一般”と”1：特定”を設定する。 
            //   　@  －is特定口座開設()が true かつ is株式型()が falseを返す場合、あるいは 
            //   　@  　@ is特定口座開設()が false を返す場合は、”0：一般”を設定する。
            boolean l_specialAccountEstablished =
                l_gentradeMainAccount.isSpecialAccountEstablished(
                    l_tsDeliveryDate,
                    l_subAccount);
            if (l_blnIsFrgnMmf)
            {
                String l_arrAccountDivDefs[] = new String[1];
                l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.OTHER;
                l_response.taxTypeList = l_arrAccountDivDefs;
            }
            else
            {
                if (l_specialAccountEstablished == true)
                {
                    if(l_mutualFundProduct.isStockType() == true)
                    {
                        String l_arrAccountDivDefs[] = new String[2];
                        l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.SPECIAL;
                        l_arrAccountDivDefs[1] = WEB3MFAccountDivDef.NORMAL;
                        l_response.taxTypeList = l_arrAccountDivDefs;
                    }
                    else
                    {
                        String l_arrAccountDivDefs[] = new String[1];
                        l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.NORMAL;
                        l_response.taxTypeList = l_arrAccountDivDefs;
                    }
                }
                else
                {
                    if(l_specialAccountEstablished == false)
                    {
                        String l_arrAccountDivDefs[] = new String[1];
                        l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.NORMAL;
                        l_response.taxTypeList = l_arrAccountDivDefs;
                    }
                }
            }

            //指定方法@一覧設定
            if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                l_mutualFundProduct.getBuySelectable()))
            {
                l_response.specifyDivList =
                    new String[] {
                        WEB3SellDivDef.COUNT_DESIGNATE,
                        WEB3SellDivDef.MONEY_DESIGNATE };
            }
            else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    l_mutualFundProduct.getBuySelectable()))
            {
                l_response.specifyDivList =
                    new String[] { WEB3SellDivDef.MONEY_DESIGNATE };
            }
            else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    l_mutualFundProduct.getBuySelectable()))
            {
                l_response.specifyDivList =
                    new String[] { WEB3SellDivDef.COUNT_DESIGNATE };
            }
            //決済方法@一覧設定
            if (WEB3BuySellSettlementDivDef.SELECT_DESIGNATE.equals(
                l_mutualFundProduct.getAcquiredSettlement()))
            {
                l_response.settleDivList =
                    new String[] {
                        WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY,
                        WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
            }
            else if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(
                    l_mutualFundProduct.getAcquiredSettlement()))
            {
                l_response.settleDivList =
                    new String[] { WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY };
            }
            else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(
                    l_mutualFundProduct.getAcquiredSettlement()))
            {
                l_response.settleDivList =
                    new String[] { WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
            }

            try
            {
                // 保有資産テーブルを検索。
                List l_lisAssets = new ArrayList();

                String l_strWhere =
                    "account_id = ? and sub_account_id = ? "
                    + "and product_id = ? ";

                Object[] l_objWhereValues = {
                    new Long(l_subAccount.getAccountId()),
                    new Long(l_subAccount.getSubAccountId()),
                    new Long(l_mutualFundProduct.getProductId()),
                };
                // -保有資産テーブルを検索し、保有資産ParamsのListを取得する。
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisAssets = l_queryProcessor.doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);

                // 1件もない場合、新規買付とする。
                if(l_lisAssets.size() <= 0)
                {
                    //買付時単位口数,新規買付の場合
                    l_response.buyUnitQty =
                        Long.toString(l_mutualFundProduct.getNewBuyUnitQty());
                    //買付時最低口数,新規買付の場合
                    l_response.buyMinQty =
                       Long.toString(l_mutualFundProduct.getNewBuyMinQty());
                    //買付時単位金額,新規買付の場合
                    l_response.buyUnitAmt =
                        Long.toString(l_mutualFundProduct.getNewBuyUnitAmt());
                    //買付時最低金額,新規買付の場合
                    l_response.buyMinAmt =
                        Long.toString(l_mutualFundProduct.getNewBuyMinAmt());

                    //・買付時外貨単位金額：
                    //　@(*) 新規買付の場合、取得した拡張投信銘柄.get外貨単位金額（新規買付）()の
                    //　@　@　@戻り値を設定する。
                    if (!l_mutualFundProductRow.getFrgnNewBuyUnitAmtIsNull())
                    {
                        l_response.buyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnNewBuyUnitAmt() + "";
                    }

                    //・買付時外貨最低金額：
                    //　@(*) 新規買付の場合、取得した拡張投信銘柄.get外貨最低金額（新規買付）()の
                    //　@　@　@戻り値を設定する。
                    if (!l_mutualFundProductRow.getFrgnNewBuyMinAmtIsNull())
                    {
                        l_response.buyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnNewBuyMinAmt() + "";
                    }
                }
                // 1件以上ある場合、追加買付とする。
                else if (l_lisAssets.size() > 0)
                {
                    //買付時単位口数,追加買付の場合
                    l_response.buyUnitQty =
                        Long.toString(l_mutualFundProduct.getAddBuyUnitQty());
                    //買付時最低口数,追加買付の場合
                    l_response.buyMinQty =
                        Long.toString(l_mutualFundProduct.getAddBuyMinQty());
                    //買付時単位金額,追加買付の場合
                    l_response.buyUnitAmt =
                        Long.toString(l_mutualFundProduct.getAddBuyUnitAmt());
                    //買付時最低金額,追加買付の場合
                    l_response.buyMinAmt =
                        Long.toString(l_mutualFundProduct.getAddBuyMinAmt());

                    //・買付時外貨単位金額：
                    //　@(*) 追加買付の場合、取得した拡張投信銘柄.get外貨単位金額（追加買付）()の
                    //　@　@　@戻り値を設定する。
                    if (!l_mutualFundProductRow.getFrgnAddBuyUnitAmtIsNull())
                    {
                        l_response.buyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnAddBuyUnitAmt() + "";
                    }

                    //・買付時外貨最低金額：
                    //　@(*) 追加買付の場合、取得した拡張投信銘柄.get外貨最低金額（追加買付）()の
                    //　@　@　@戻り値を設定する。
                    if (!l_mutualFundProductRow.getFrgnAddBuyMinAmtIsNull())
                    {
                        l_response.buyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnAddBuyMinAmt() + "";
                    }
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In 保有資産テーブルを検索し ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            catch (DataQueryException l_ex)
            {
                log.error("Error In 保有資産テーブルを検索し ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            //発注日設定
            l_response.orderBizDate = l_datOrderBizDate;
            //約定日設定
            l_response.executionTimestamp = l_tsExecutedDate;
            //受渡日設定
            l_response.deliveryDate = l_tsDeliveryDate;

            //目論見書閲覧チェック結果：
            //　@リクエスト.電子鳩チェックフラグ==falseの場合は、nullセット
            //　@リクエスト.電子鳩チェックフラグ==trueの場合、validate目論見書閲覧()の結果オブジェクトをセットする。
            l_response.prospectusResult = l_validateBataResult;

            //・円転基準価額 
            //　@　@1）投信銘柄.get通貨コード( )がT0　@または
            //　@　@　@拡張投信銘柄.is外貨MMF = true　@の場合
            //　@　@　@　@nullをセットする。
            //　@　@2）投信銘柄.get通貨コード( )がT0でない場合
            //　@　@　@　@拡張投信銘柄.get円転基準価額(WEB3MFProcessDivDef.買付)をセットする。
            if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mutualFundProduct.getCurrencyCode())
                || l_blnIsFrgnMmf)
            {
                l_response.yenConstantValue = null;
            }
            else
            {
                l_response.yenConstantValue =
                    l_mutualFundProduct.getYenConstantValue(WEB3MFProcessDivDef.BUY);
            }
            
            // ・参考レート
            //     1）投信銘柄.get通貨コード( )がT0の場合
            //        nullをセットする。
            //     2）投信銘柄.get通貨コード( )がT0でない場合
            //        2-1) 投信銘柄.is外貨MMF ＝ trueの場合
            //               拡張投信銘柄.get外貨MMF為替レート()の戻り値外貨MMF為替レートParamsのTTS
            //               をセットする。（小数第３位で四捨五入）
            //        2-2) 投信銘柄.is外貨MMF ＝ falseの場合
            //               拡張投信銘柄.get為替レート()の戻り値為替レートParamsのTTS / 同為替レート計算単位
            //               をセットする。（小数第３位で四捨五入）
            // ・参考レート確定日
            //     1）投信銘柄.get通貨コード( )がT0の場合
            //        nullをセットする。
            //     2）投信銘柄.get通貨コード( )がT0でない場合
            //        2-1) 投信銘柄.is外貨MMF ＝ trueの場合
            //             拡張投信銘柄.get外貨MMF為替レート()の戻り値
            //             外貨MMF為替レートParamsの為替レート確定日をセットする。
            //        2-2) 投信銘柄.is外貨MMF ＝ falseの場合
            //             拡張投信銘柄.get為替レート()の戻り値
            //             為替レートParamsの為替レート確定日をセットする。
            if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mutualFundProduct.getCurrencyCode()))
            {
                l_response.referenceRate = null;
                l_response.referenceRateFixedDay = null;
            }
            else
            {
                if (l_blnIsFrgnMmf)
                {
                    BigDecimal l_bdTtSellingRate =
                        new BigDecimal(l_mutualFundProduct.getFrgnMmfExchangeRate().getTtSellingRate() + "");
                    l_response.referenceRate =
                        WEB3StringTypeUtility.formatNumber(
                            l_bdTtSellingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    l_response.referenceRateFixedDay =
                        l_mutualFundProduct.getFrgnMmfExchangeRate().getExecTimestamp();
                }
                else
                {
                    BigDecimal l_bdTtSellingRate =
                        new BigDecimal(l_mutualFundProduct.getExchangeRate().getTtSellingRate());
                    BigDecimal l_bdExchangeCalcUnit =
                        new BigDecimal(l_mutualFundProduct.getExchangeRate().getExchangeCalcUnit());
                    l_response.referenceRate =
                        WEB3StringTypeUtility.formatNumber(
                            l_bdTtSellingRate.divide(
                                l_bdExchangeCalcUnit,
                                2,
                                BigDecimal.ROUND_HALF_UP).doubleValue());
                    
                    l_response.referenceRateFixedDay =
                        l_mutualFundProduct.getExchangeRate().getExecTimestamp();
                }
            } 
        }
        catch (NotFoundException e)
        {
            log.error("not found Product or MainAccountexception");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
