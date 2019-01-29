head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS注文マネージャ（WEB3EquityPTSOrderManager.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 トウ鋒鋼 (中訊) 新規作成 モデル1208
Revision History : 2007/12/19 金傑 (中訊) 仕様変更モデルNo.1253,1254
Revision History : 2007/12/20 金傑 (中訊) 仕様変更モデルNo.1262,1263,1270,1271
Revision History : 2008/02/13 トウ鋒鋼 (中訊) 仕様変更モデルNo.1296
Revision History : 2008/04/16 劉剣 (中訊) モデルNo.1312
Revision History : 2008/10/06 劉剣 (中訊) モデルNo.1323
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS注文マネージャ)<BR>
 * PTS注文マネージャクラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3EquityPTSOrderManager extends WEB3EquityOrderManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderManager.class);

    /**
     * @@roseuid 4766165002BF
     */
    public WEB3EquityPTSOrderManager()
    {

    }

    /**
     * (validatePTS注文訂正可能状態)<BR>
     * 訂正が可能な注文状態かをチェックする。<BR>
     * <BR>
     * PTS発注審査個別チェック.validatePTS注文訂正可能状態()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validatePTS注文訂正可能状態()に指定する引数]<BR>
     * 注文：　@パラメータ.注文<BR>
     * @@param l_order - (注文)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4734483E0368
     */
    public void validatePTSOrderForChangeability(Order l_order) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        try
        {
            // PTS発注審査個別チェック.validatePTS注文訂正可能状態()に処理を委譲（delegate）する
            l_orderMgrResVal.validatePTSOrderForChangeability(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS注文取消可能状態)<BR>
     * 取消が可能な注文状態かをチェックする。<BR>
     * <BR>
     * PTS発注審査個別チェック.validatePTS注文取消可能状態()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validatePTS注文取消可能状態()に指定する引数]<BR>
     * 注文：　@パラメータ.注文<BR>
     * @@param l_order - (注文)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473448EB0045
     */
    public void validatePTSOrderForCancellation(Order l_order) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForCancellation(Order)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();
        try
        {
            l_orderMgrResVal.validatePTSOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS注文)<BR>
     * 注文入力内容のチェックを実施する。 <BR>
     * <BR>
     * シーケンス図「(PTS注文)現物株式発注審査」参照。<BR>
     * ======================================================== <BR>
     * シーケンス図 ：(PTS注文)現物株式発注審査 <BR>
     * 具体位置：isPTS口座開設( ) <BR>
     * 　@　@　@　@　@PTS口座を開設していない場合は<BR>
     * 　@　@　@　@（isPTS口座開設()の戻り値=false）例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02967 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(PTS注文)現物株式発注審査 <BR>
     * 具体位置：is特定口座開設(受渡日 : Date, 補助口座 : 補助口座) <BR>
     * 　@　@　@　@　@特定口座を開設していない場合は<BR>
     * 　@　@　@　@（is特定口座開設()の戻り値=false）例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00637 <BR>
     * ======================================================== <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_eqTypeNewCashBasedOrderSpec - (株式注文内容)<BR>
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 4734492B02BD
     */
    public EqTypeNewOrderValidationResult validatePTSOrder(
        SubAccount l_subAccount,
        WEB3EquityNewCashBasedOrderSpec l_eqTypeNewCashBasedOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSOrder(SubAccount, WEB3EquityNewCashBasedOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqTypeNewCashBasedOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // validate注文受付可能
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        //注文チェックを取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        // 拡張株式注文マネージャを取得する
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //validate取引可能顧客
        //引数
        //顧客 : 引数の補助口座.getMainAccount()
        //発注日 : PTS取引時間管理.get発注日()

        Timestamp l_tsPtsOrderBizDate = new Timestamp(
            WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());

        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_mainAccount,
                l_tsPtsOrderBizDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_validationResult.getProcessingResult().getErrorInfo()));
        }

        // isPTS口座開設()
        boolean l_blnIsPTSAccountOpen = l_mainAccount.isPTSAccountOpen();

        //（isPTS口座開設()の戻り値=false）例外をthrowする。
        if (!l_blnIsPTSAccountOpen)
        {
            log.debug("PTS口座開設なし。");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02967));
        }

        // validate税区分（PTS）(boolean, TaxTypeEnum)税区分のチェック
        // [設定する引数]
        // is売注文：　@株式注文内容.isSellOrder( )の戻り値
        // 税区分：　@株式注文内容.getTaxType( )の戻り値
        l_orderMgrResVal.validatePTSTaxType(
            l_eqTypeNewCashBasedOrderSpec.isSellOrder(), l_eqTypeNewCashBasedOrderSpec.getTaxType());

        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

        // validate銘柄コード
        // [設定する引数]
        // 銘柄コード：　@株式注文内容.getProductCode()の戻り値
        // 証券会社コード：　@補助口座.getInstitution().getInstitutionCode()の戻り値
        WEB3EquityProduct l_web3EquityProduct = l_orderMgrResVal.validateProductCode(
            l_eqTypeNewCashBasedOrderSpec.getProductCode(),
            l_strInstitutionCode);

        // validate市場コード
        // [設定する引数]
        // 市場コード：　@株式注文内容.getMarketCode()の戻り値
        // 証券会社コード：　@補助口座.getInstitution().getInstitutionCode()の戻り値
        WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
            l_eqTypeNewCashBasedOrderSpec.getMarketCode(),
            l_strInstitutionCode);

        // validateインサイダー
        // [設定する引数]
        // 補助口座：　@引数.補助口座
        // 株式銘柄：　@validate銘柄コード()の戻り値
        l_orderMgrResVal.validateInsider(l_subAccount, l_web3EquityProduct);

        // validate顧客銘柄別取引停止（PTS）
        // [設定する引数]
        // 補助口座：　@引数.補助口座
        // 銘柄ID：　@validate銘柄コード( )の戻り値の株式銘柄オブジェクト.銘柄ID
        // 注文種別：　@売注文（株式注文内容.isSellOrder( )==true）の場合は、
        //              "現物売注文"。
        //              上記以外の場合は、"現物買注文"。

        OrderTypeEnum l_orderType = OrderTypeEnum.EQUITY_BUY;
        if (l_eqTypeNewCashBasedOrderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }

        l_orderMgrResVal.validatePTSAccountProductOrderStop(
            l_subAccount, l_web3EquityProduct.getProductId(), l_orderType);

        // validate取引銘柄
        //[設定する引数]
        // 補助口座：　@引数.補助口座
        // 株式銘柄：　@validate銘柄コード( )の戻り値
        // 市場：　@validate市場コード( )の戻り値
        // is売注文：　@株式注文内容.isSellOrder( )の戻り値
        WEB3EquityTradedProduct l_web3TradedProdcut =
            (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(
                l_subAccount,
                l_web3EquityProduct,
                l_market,
                l_eqTypeNewCashBasedOrderSpec.isSellOrder());

        // validate取扱可能PTS市場
        // [設定する引数]
        // 部店：　@補助口座.get取引店()
        // 取引銘柄：　@validate取引銘柄()の戻り値の取引銘柄オブジェクト
        l_orderMgrResVal.validateHandlingPossiblePTSMarket(
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(), l_web3TradedProdcut);

        // l_eqTypeNewCashBasedOrderSpec.getTaxType()=="特定" または "特定口座かつ源泉徴収"の場合
        TaxTypeEnum l_taxTypeEnum = l_eqTypeNewCashBasedOrderSpec.getTaxType();
        if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
        {
            // is特定口座開設
            //[設定する引数]
            // 受渡日：　@取引銘柄.getDailyDeliveryDate()
            // 補助口座：　@引数.補助口座
            boolean l_blnIsSpecialAccountEstablished  = l_mainAccount.isSpecialAccountEstablished(
                l_web3TradedProdcut.getDailyDeliveryDate(), l_subAccount);

            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("現物の特定口座開設なし。");
                log.exiting(STR_METHOD_NAME);
                return new EqTypeNewOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00637));
            }
        }

        // validate特定口座取扱規制
        // [設定する引数]
        // 税区分：　@株式注文内容.get税区分()の戻り値
        // 株式銘柄：　@validate銘柄コード( )の戻り値
        // is買注文：　@株式注文内容.isBuyOrder()の戻り値
        l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
            l_taxTypeEnum, l_web3EquityProduct, l_eqTypeNewCashBasedOrderSpec.isBuyOrder());

        // validate注文条件
        Date l_datFirstOrderBizDate = null;
        long l_intFirstOrderUnitId = 0;
        if (l_eqTypeNewCashBasedOrderSpec.getFirstOrderUnitId() != null)
        {
            l_intFirstOrderUnitId = (l_eqTypeNewCashBasedOrderSpec.getFirstOrderUnitId()).longValue();
        }

        if (l_intFirstOrderUnitId > 0)
        {
            try
            {
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_intFirstOrderUnitId);

                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_datFirstOrderBizDate = WEB3DateUtility.getDate(
                    l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。", l_nfe);
                log.exiting(STR_METHOD_NAME);
                return new EqTypeNewOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }
        }

        // 補助口座：　@引数の補助口座オブジェクト
        // 注文単位ID：　@0(：新規注文)
        // 取引銘柄：　@validate取引銘柄( )の戻り値の取引銘柄オブジェクト
        // 原注文発注日：
        //　@・株式注文内容.get初回注文の注文単位ID > 0（==繰越）の場合
        //　@　@拡張株式注文マネージャ.getOrderUnit(初回注文の注文単位ID)の
        //　@　@注文単位.発注日。
        //　@・上記以外（==新規注文登録）の場合、null
        // 注文失効日：　@株式注文内容.getOrderExpDate( )
        // 発注条件：　@株式注文内容.get発注条件( )
        // 執行条件：　@株式注文内容.getExecConditionType( )
        // is出来るまで注文：　@株式注文内容.is出来るまで注文( )
        // 信用取引区分：　@”DEFAULT”
        // 値段条件：　@株式注文内容.get値段条件( )
        // 市場コード：　@株式注文内容.getMarketCode( )
        l_orderMgrResVal.validateOrderCondition(
            (WEB3GentradeSubAccount)l_subAccount,
            0,
            l_web3TradedProdcut,
            l_datFirstOrderBizDate,
            l_eqTypeNewCashBasedOrderSpec.getOrderExpDate(),
            l_eqTypeNewCashBasedOrderSpec.getOrderCond(),
            l_eqTypeNewCashBasedOrderSpec.getExecConditionType(),
            l_eqTypeNewCashBasedOrderSpec.isOrderUntilDeadLine(),
            WEB3MarginTradingDivDef.DEFAULT,
            l_eqTypeNewCashBasedOrderSpec.getPriceConditionType(),
            l_eqTypeNewCashBasedOrderSpec.getMarketCode());

        // validate株数（PTS）
        // [設定する引数]
        // 取引銘柄：　@validate取引銘柄()の戻り値の取引銘柄オブジェクト
        // 部店：　@補助口座.get取引店()
        // 株数：　@株式注文内容.getQuantity()
        l_orderMgrResVal.validatePTSQuantity(
            l_web3TradedProdcut,
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
            l_eqTypeNewCashBasedOrderSpec.getQuantity());

        // validate指値注文（PTS）
        // [設定する引数]
        // 株式注文内容：　@引数.株式注文内容
        l_orderMgrResVal.validatePTSLimitOrder(l_eqTypeNewCashBasedOrderSpec);

        // validate注文単価（PTS）
        // [設定する引数]
        // 指値：　@株式注文内容.getLimitPrice()
        // 取引銘柄：　@validate取引銘柄()の戻り値の取引銘柄オブジェクト
        // 補助口座：　@引数.補助口座
        boolean l_blnIsValidatePTSPrice = l_orderMgrResVal.validatePTSPrice(
            l_eqTypeNewCashBasedOrderSpec.getLimitPrice(),
            l_web3TradedProdcut,
            l_subAccount);

        if (!l_blnIsValidatePTSPrice)
        {
            log.debug("注文単価チェックエラー（指値が適切ではありません）。");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00293));
        }

        // 売注文（株式注文内容.isSellOrder() == true）の場合
        if (l_eqTypeNewCashBasedOrderSpec.isSellOrder())
        {
            // validate売付可能数量
            // [設定する引数]
            // 補助口座：　@引数.補助口座
            // 取引銘柄：　@validate取引銘柄()の戻り値の取引銘柄オブジェクト
            // 株数：　@株式注文内容.getQuantity()
            // 税区分：　@株式注文内容.getTaxType()
            l_orderMgrResVal.validateSellableAssetQuantity(
                l_subAccount,
                l_web3TradedProdcut,
                l_eqTypeNewCashBasedOrderSpec.getQuantity(),
                l_eqTypeNewCashBasedOrderSpec.getTaxType());
        }

        //validate機@構預託同意(補助口座)
        l_orderMgrResVal.validateMechanismDepositAgree(l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validatePTS訂正注文)<BR>
     * 注文訂正内容のチェックを実施する。 <BR>
     * <BR>
     * シーケンス図「（PTS注文）訂正発注審査」参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図 ：（PTS注文）訂正発注審査 <BR>
     * 具体位置：isPTS口座開設( ) <BR>
     * 　@　@　@　@　@PTS口座を開設していない場合は<BR>
     * 　@　@　@　@（isPTS口座開設()の戻り値=false）例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02967 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：（PTS注文）訂正発注審査 <BR>
     * 具体位置：is特定口座開設(受渡日 : Date, 補助口座 : 補助口座) <BR>
     * 　@　@　@　@　@特定口座指定で、特定口座を開設していない場合は<BR>
     * 　@　@　@　@　@例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00637 <BR>
     * ======================================================== <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_eqChangeOrderSpec - (株式注文訂正内容)<BR>
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 4734495001EE
     */
    public EqTypeOrderValidationResult validatePTSChangeOrder(
        SubAccount l_subAccount,
        EqTypeChangeOrderSpec l_eqChangeOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSChangeOrder(SubAccount, EqTypeChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqChangeOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //get顧客
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //isPTS口座開設( )
        //（isPTS口座開設()の戻り値＝false）例外をthrowする。
        if (!l_mainAccount.isPTSAccountOpen())
        {
            log.debug("PTS口座開設なし。");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02967));
        }

        // PTS発注審査個別チェックインスタンスを取得する。
        WEB3EquityPTSOrderManagerReusableValidations l_reusableValidations =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // 注文チェックを取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //validate取引可能顧客(顧客 : 顧客, 発注日 : Timestamp)
        //引数の設定仕様は以下の通り
        //顧客 : 引数の補助口座.getMainAccount( )
        //発注日 : PTS取引時間管理.get発注日( )
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_mainAccount,
                new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime()));

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_orderValidationResult.getProcessingResult().getErrorInfo()));
        }


        Order l_order = null;
        try
        {
            //validateOrderIdForExistence(注文ID : long)
            l_order = l_reusableValidations.validateOrderIdForExistence(l_eqChangeOrderSpec.getOrderId());

            //validatePTS注文訂正可能状態(注文)
            //注文 : PTS発注審査個別チェック.validateOrderIdForExistence( )
            l_reusableValidations.validatePTSOrderForChangeability(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.error(l_processingResult.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(l_processingResult);
        }

        //注文単位一覧を取得する。
        //取得した注文単位配列の0番目の要素を訂正対象の注文単位とする。
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];

        //訂正対象の注文単位.市場IDに該当する市場マスタ
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        Market l_market = null;

        try
        {
            l_market = l_finObjectManager.getMarket(l_eqtypeOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }

        //訂正対象の注文単位.市場IDに該当する市場マスタ.市場コード
        String l_strMarketCode = l_market.getMarketCode();

        //validate市場コード(String, String)
        //市場コード : 訂正対象の注文単位.市場IDに該当する市場マスタ.市場コード
        //証券会社コード : 引数の補助口座.getInstitution( ).証券会社コード
        Market l_validateMarket = l_reusableValidations.validateMarket(
            l_strMarketCode,
            l_subAccount.getInstitution().getInstitutionCode());

        //訂正対象の注文単位.getProduct( )
        EqTypeProduct l_eqTypeProduct = (EqTypeProduct)l_eqTypeOrderUnit.getProduct();

        //validateインサイダー(補助口座, 株式銘柄)
        //補助口座 : 引数の補助口座
        //株式銘柄 : 訂正対象の注文単位.getProduct( )
        l_reusableValidations.validateInsider(
            l_subAccount,
            l_eqTypeProduct);

        //validate顧客銘柄別取引停止（PTS）(補助口座, long, OrderTypeEnum)
        //補助口座 : 引数の補助口座
        //銘柄ID : 訂正対象の注文単位.銘柄ID
        //注文種別 : 訂正対象の注文単位.注文種別
        l_reusableValidations.validatePTSAccountProductOrderStop(
            l_subAccount,
            l_eqtypeOrderUnitRow.getProductId(),
            l_eqtypeOrderUnitRow.getOrderType());

        //validate取引銘柄(株式銘柄, 市場)
        //株式銘柄 : 訂正対象の注文単位.getProduct( )
        //市場 : PTS発注審査個別チェック.validate市場コード( )
        WEB3EquityTradedProduct l_equityTradedProduct =
            (WEB3EquityTradedProduct)l_reusableValidations.validateTradedProduct(
            l_eqTypeProduct, l_validateMarket);

        //補助口座.get取引店( )
        WEB3GentradeBranch l_branch = ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();

        // validate取扱可能PTS市場(部店, 取引銘柄)
        //部店 : 引数の補助口座.get取引店( )
        //取引銘柄 : PTS発注審査個別チェック.validate取引銘柄( )
        l_reusableValidations.validateHandlingPossiblePTSMarket(
            l_branch,
            l_equityTradedProduct);

        //createPTS株式注文内容
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec = (WEB3EquityChangeOrderSpec)l_eqChangeOrderSpec;
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            l_equityChangeOrderSpec.createPTSOrderSpec();

        //[get初回注文の注文単位( )の引数設定]
        //注文単位 : 訂正対象の注文単位
        EqTypeOrderUnit l_firstEqTypeOrderUnit = this.getFirstOrderUnit(l_eqTypeOrderUnit);

        EqtypeOrderUnitRow l_firstEqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_firstEqTypeOrderUnit.getDataSourceObject();

        //原注文発注日 : PTS注文マネージャ.get初回注文の注文単位( ).発注日
        Date l_datBizDate = WEB3DateUtility.getDate(
                l_firstEqTypeOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //validate注文条件(補助口座, long, 取引銘柄, Date, Date, String,
        //EqTypeExecutionConditionType, boolean, String, String, String)
        //補助口座 : 引数の補助口座
        //注文単位ID : 訂正対象の注文単位.注文単位ID
        //取引銘柄 : PTS発注審査個別チェック.validate取引銘柄( )
        //原注文発注日 : PTS注文マネージャ.get初回注文の注文単位( ).発注日
        //　@[get初回注文の注文単位( )の引数設定]
        //　@　@注文単位 : 訂正対象の注文単位
        //注文失効日 : 株式注文内容.getOrderExpDate( )
        //発注条件 : 株式注文内容.get発注条件( )
        //執行条件 : 株式注文内容.getExecConditionType( )
        //is出来るまで注文 : 株式注文内容.is出来るまで注文( )
        //信用取引区分 : "0：DEFAULT"
        //値段条件 : 株式注文内容.get値段条件( )
        //市場コード : 訂正対象の注文単位.市場IDに該当する市場マスタ.市場コード
        l_reusableValidations.validateOrderCondition(
            (WEB3GentradeSubAccount)l_subAccount,
            l_eqtypeOrderUnitRow.getOrderUnitId(),
            l_equityTradedProduct,
            l_datBizDate,
            l_equityNewCashBasedOrderSpec.getOrderExpDate(),
            l_equityNewCashBasedOrderSpec.getOrderCond(),
            l_equityNewCashBasedOrderSpec.getExecConditionType(),
            l_equityNewCashBasedOrderSpec.isOrderUntilDeadLine(),
            WEB3MarginTradingDivDef.DEFAULT,
            l_equityNewCashBasedOrderSpec.getPriceConditionType(),
            l_strMarketCode);

        //株式注文内容.getTaxType( ) == "特定" または "特定口座かつ源泉徴収" の場合
        if (TaxTypeEnum.SPECIAL.equals(l_equityNewCashBasedOrderSpec.getTaxType())
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_equityNewCashBasedOrderSpec.getTaxType()))
        {
            //is特定口座開設(受渡日 : Date, 補助口座 : 補助口座)
            //受渡日 : PTS発注審査個別チェック.validate取引銘柄( ).getDailyDeliveryDate( )
            //補助口座 : 引数の補助口座
            boolean l_blnIsSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(
                l_equityTradedProduct.getDailyDeliveryDate(),
                l_subAccount);

            //特定口座指定で、特定口座を開設していない場合は例外をthrowする。
            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("現物の特定口座開設なし。");
                log.exiting(STR_METHOD_NAME);
                return new EqTypeOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00637));
            }
        }

        //validate株数（PTS）(取引銘柄, 部店, double)
        //取引銘柄 : PTS発注審査個別チェック.validate取引銘柄( )
        //部店 : 引数の補助口座.get取引店( )
        //株数 : 株式注文内容.getQuantity( )
        l_reusableValidations.validatePTSQuantity(
            l_equityTradedProduct,
            l_branch,
            l_equityNewCashBasedOrderSpec.getQuantity());

        //validate指値注文（PTS）(株式注文内容)
        //株式注文内容 : 引数の株式注文訂正内容.createPTS株式注文内容( )
        l_reusableValidations.validatePTSLimitOrder(l_equityNewCashBasedOrderSpec);

        //validate注文単価（PTS）(double, 取引銘柄, 補助口座)
        //指値 : 株式注文内容.getLimitPrice( )
        //取引銘柄 : PTS発注審査個別チェック.validate取引銘柄( )
        //補助口座 : 引数の補助口座
        boolean l_blnIsValidatePTSPrice = l_reusableValidations.validatePTSPrice(
            l_equityNewCashBasedOrderSpec.getLimitPrice(),
            l_equityTradedProduct,
            l_subAccount);

        if (!l_blnIsValidatePTSPrice)
        {
            log.debug("注文単価チェックエラー（指値が適切ではありません）。");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00293));
        }

        //株式注文訂正内容.get訂正値詳細( )
        WEB3EquityChangeOrderUnitEntry l_eqChangeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry)l_equityChangeOrderSpec.getChangeOrderUnitEntry();

        //validate訂正項目（PTS）
        //注文単位 : 訂正対象の注文単位
        //訂正後株数 : 株式注文訂正内容.get訂正値詳細( ).getAfterChangeOriginalQuantity( )
        //訂正後指値 : 株式注文訂正内容.get訂正値詳細( ).getAfterChangePrice( )
        //訂正後執行条件 : 株式注文訂正内容.get訂正値詳細( ).get訂正後執行条件( )
        //訂正後値段条件 : 株式注文訂正内容.get訂正値詳細( ).get訂正後値段条件( )
        //訂正後発注条件 : 株式注文訂正内容.get訂正値詳細( ).get訂正後発注条件( )
        //訂正後発注条件演算子 : 株式注文訂正内容.get訂正値詳細( ).get訂正後発注条件演算子( )
        //訂正後逆指値基準値 : 株式注文訂正内容.get訂正値詳細( ).get訂正後逆指値基準値( )
        //訂正後（W指値）訂正指値 : 株式注文訂正内容.get訂正値詳細( ).get訂正後（W指値）訂正指値( )
        //訂正後（W指値）執行条件 : 株式注文訂正内容.get訂正値詳細( ).get訂正後（W指値）執行条件( )
        //訂正後is出来るまで注文 : 株式注文訂正内容.get訂正値詳細( ).get訂正後is出来るまで注文( )
        //訂正後注文失効日 : 株式注文訂正内容.get訂正値詳細( ).get訂正後注文失効日( )
        //訂正後決済指定エントリ : Null
        l_reusableValidations.validatePTSChangeItem(
            l_eqTypeOrderUnit,
            l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
            l_eqChangeOrderUnitEntry.getAfterChangePrice(),
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType(),
            l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType(),
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondType(),
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondOperator(),
            l_eqChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice(),
            l_eqChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice(),
            l_eqChangeOrderUnitEntry.getModifiedWlimitExecCondType(),
            l_eqChangeOrderUnitEntry.getChangeAfterIsOrderUntilDeadLine(),
            l_eqChangeOrderUnitEntry.getModifiedExpirationDate(),
            null);

        //validate訂正時注文Rev上限（PTS）
        //訂正前注文単位 : 訂正対象の注文単位
        //訂正後株数 : 株式注文訂正内容.get訂正値詳細( ).getAfterChangeOriginalQuantity( )
        //訂正後指値 : 株式注文訂正内容.get訂正値詳細( ).getAfterChangePrice( )
        //訂正後執行条件 : 株式注文訂正内容.get訂正値詳細( ).get訂正後執行条件( )
        //訂正後値段条件 : 株式注文訂正内容.get訂正値詳細( ).get訂正後値段条件( )
        l_reusableValidations.validatePTSChangeOrderRevUpperLimit(
            l_eqTypeOrderUnit,
            l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
            l_eqChangeOrderUnitEntry.getAfterChangePrice(),
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType(),
            l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType());

        //株式発注審査結果(ProcessingResult, boolean)
        //発注審査結果 : ProcessingResult.SUCCESS_RESULT（発注審査OK）
        //空売り規制対象フラグ : false（固定）
        WEB3EquityOrderValidationResult l_equityOrderValidationResult =
            new WEB3EquityOrderValidationResult(
                ProcessingResult.SUCCESS_RESULT,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_equityOrderValidationResult;

    }

    /**
     * (validatePTS取消注文)<BR>
     * 注文取消内容のチェックを実施する。 <BR>
     * <BR>
     * シーケンス図「（PTS注文）取消発注審査」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_eqTypeCancelOrderSpec - (株式注文取消内容)<BR>
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 4734495A0095
     */
    public EqTypeOrderValidationResult validatePTSCancelOrder(
        SubAccount l_subAccount,
        EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSCancelOrder(SubAccount, EqTypeCancelOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqTypeCancelOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderPtsMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        //注文チェックを取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //validate取引可能顧客
        //引数
        //顧客 : 引数の補助口座.getMainAccount()
        //発注日 : PTS取引時間管理.get発注日()

        Timestamp l_tsPtsOrderBizDate = new Timestamp(
            WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());

        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount(),
                l_tsPtsOrderBizDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_validationResult.getProcessingResult().getErrorInfo()));
        }

        Order l_order = null;
        // validateOrderIdForExistence(注文ID : long)
        // 引数:
        // 注文ID：株式注文取消内容.getOrderId()
        try
        {
            l_order =
                l_orderPtsMgrResVal.validateOrderIdForExistence(l_eqTypeCancelOrderSpec.getOrderId());
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.error(l_processingResult.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(l_processingResult);
        }

        // validatePTS注文取消可能状態(注文)
        try
        {
            l_orderPtsMgrResVal.validatePTSOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.error(l_processingResult.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(l_processingResult);
        }

        //注文単位一覧を取得する。
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit  = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //validate市場コード(String, String)
        //引数:
        //市場コード : 取消対象の注文単位.市場IDに該当する市場マスタ.市場コード
        //証券会社コード : 引数の補助口座.getInstitution( ).証券会社コード

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        String l_strMarketCode = null;
        try
        {
            //市場コードを取得する。
            l_strMarketCode =
                (l_finObjectManager.getMarket(l_orderUnitRow.getMarketId())).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。", l_nfe);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        WEB3GentradeMarket l_market =
            (WEB3GentradeMarket)l_orderPtsMgrResVal.validateMarket(
                l_strMarketCode,
                l_subAccount.getInstitution().getInstitutionCode());

        //validate取引銘柄(株式銘柄, 市場)
        //引数:
        //株式銘柄 : 取消対象の注文単位.getProduct()
        //市場 : PTS発注審査個別チェック.validate市場コード()

        WEB3EquityTradedProduct l_tradedProduct = null;

        l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderPtsMgrResVal.validateTradedProduct(
                (EqTypeProduct)l_orderUnit.getProduct(), l_market);

        //validate取扱可能PTS市場(部店, 取引銘柄)
        // 引数:
        //部店 : 引数の補助口座.get取引店()
        //取引銘柄 : PTS発注審査個別チェック.validate取引銘柄()
        l_orderPtsMgrResVal.validateHandlingPossiblePTSMarket(
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(), l_tradedProduct);

        log.exiting(STR_METHOD_NAME);
        return new EqTypeOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }


    /**
     * (validatePTS市場別取引可能上限金額)<BR>
     * 概算金額値が、会社・部店・市場で一度に<BR>
     * 　@取引可能な上限金額を超えていないかチェックを行う。 <BR>
     * （* PTS発注審査個別チェック.validatePTS市場別取引可能上限金額( )に委譲する。）<BR>
     * @@param l_branch - (部店)<BR>
     * @@param l_market - (市場)<BR>
     * @@param l_dblRestraintTurnover - (拘束売買代金)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473449630381
     */
    public void validatePTSMarketMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSMarketMaxHandlingPrice(Branch, Market, double)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // PTS発注審査個別チェック.validatePTS市場別取引可能上限金額( )に委譲する。
        l_orderMgrResVal.validatePTSMarketMaxHandlingPrice(l_branch, l_market, l_dblRestraintTurnover);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate顧客銘柄別取引停止（PTS）)<BR>
     * 顧客銘柄別取引停止チェックを行う。<BR>
     * <BR>
     * （* PTS発注審査チェック.validate顧客銘柄別取引停止（PTS）( )に委譲する。）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID。<BR>
     * 銘柄を特定しない場合は、0（全銘柄）をセット。<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@throws WEB3BaseException
     * @@roseuid 474E0A510298
     */
    public void validatePTSAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSAccountProductOrderStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // PTS発注審査チェック.validate顧客銘柄別取引停止（PTS）( )に委譲する。
        l_orderMgrResVal.validatePTSAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderType);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取扱可能PTS市場)<BR>
     * 会社部店の取扱可能市場かをチェックする。 <BR>
     * <BR>
     * （* PTS発注審査チェック.validate取扱可能PTS市場( )に委譲する。）<BR>
     * @@param l_branch - (部店)<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 474FD91E0017
     */
    public void validateHandlingPossiblePTSMarket(
        Branch l_branch,
        WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossiblePTSMarket(Branch, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // 株式発注審査チェック.株式発注審査チェック.validate取扱可能PTS市場()に委譲する。
        l_orderMgrResVal.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is権利落ち日（PTS）)<BR>
     * 発注日が権利落ち日であるかどうかの判定を行う。<BR>
     * <BR>
     * 発注日＝権利落ち日の場合はtrueを、<BR>
     * 上記以外の場合はfalseを返す。<BR>
     * （* PTS発注審査個別チェック.is権利落ち日(PTS)( )に委譲する。）<BR>
     * @@param l_tsOrderBizDate - (発注日)<BR>
     * 発注日。<BR>
     * @@param l_tsYearlyBooksClosingDate - (権利確定日)<BR>
     * 権利確定日を指定する。通常は、【株式銘柄テーブル】決算日が指定される。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4753F64302B1
     */
    public boolean isPTSDevidendRightDate(
        Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSDevidendRightDate(Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        log.exiting(STR_METHOD_NAME);
        // 株式発注審査個別チェック.is権利落ち日(PTS)()に委譲する
        return l_orderMgrResVal.isPTSDevidendRightDate(
            l_tsOrderBizDate,
            l_tsYearlyBooksClosingDate);
    }

    /**
     * (get値幅上限値（PTS）)<BR>
     * 値幅上限値を取得する。 <BR>
     * <BR>
     * １）　@基準値取得 <BR>
     * 　@PTS発注審査個別チェック.calc基準値（PTS値幅チェック用）をコールする。 <BR>
     * <BR>
     * 　@[引数設定]<BR>
     * 　@取引銘柄：　@引数.取引銘柄 <BR>
     * <BR>
     * ２）　@値幅取得 <BR>
     * 　@PTS発注審査個別チェック.calc値幅()をコールする。 <BR>
     * <BR>
     * 　@[引数設定]<BR>
     * 　@取引銘柄：　@引数.取引銘柄 <BR>
     * 　@基準値：　@１）calc基準値（値幅チェック用）()の戻り値 <BR>
     * 　@上限/下限区分：　@上限 <BR>
     * <BR>
     * ３）　@指値単位取得 <BR>
     * 　@拡張プロダクトマネージャ.get刻み値（）をコールする。 <BR>
     * <BR>
     * 　@[引数設定]<BR>
     * 　@取引銘柄：　@引数.取引銘柄 <BR>
     * 　@基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値　@<BR>
     * 　@　@　@　@＋　@２）calc値幅()の戻り値 <BR>
     * <BR>
     * ４）　@値幅上限値取得 <BR>
     * 　@PTS発注審査個別チェック.calc値幅上限(基準値,値幅,指値単位)をコールする。 <BR>
     * <BR>
     * 　@[引数設定] <BR>
     * 　@基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値 <BR>
     * 　@値幅：　@２）calc値幅()の戻り値 <BR>
     * 　@指値単位：　@３）get刻み値()の戻り値 <BR>
     * <BR>
     * ５）　@４）calc値幅上限()の戻り値を返却する。 <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 475FBAE800A8
     */
    public double getPTSStopHighPrice(WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPTSStopHighPrice(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // 1)　@基準値取得
        // PTS発注審査個別チェック.calc基準値（PTS値幅チェック用）をコールする。
        //　@[引数設定]
        //　@取引銘柄：　@引数.取引銘柄

        double l_dblBasePrice = l_orderMgrResVal.calcBasePriceForPTSPriceRange(l_tradedProduct);

        BigDecimal l_bdBasePrice = new BigDecimal(String.valueOf(l_dblBasePrice));

        // 2)　@値幅取得
        // PTS発注審査個別チェック.calc値幅()をコールする
        // [引数設定]
        // 取引銘柄：　@引数.取引銘柄
        // 基準値：　@１）calc基準値（値幅チェック用）()の戻り値
        // 上限/下限区分：　@上限

        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MAXIMUM);

        // 3)　@指値単位取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        //[引数設定]
        //　@取引銘柄：　@引数.取引銘柄
        //　@基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値　@＋　@２）calc値幅()の戻り値
        double l_dblbase = (l_bdBasePrice.add(new BigDecimal(String.valueOf(l_dblPriceRange)))).doubleValue();
        double l_dblTickValue = l_productManager.getTickValue(
            l_tradedProduct,
            l_dblbase);

        // 4)　@値幅上限値取得
        //　@[引数設定]
        // 　@基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値
        // 　@値幅：　@２）calc値幅()の戻り値
        // 　@指値単位：　@３）get刻み値()の戻り値
        double l_dblStopHighPrice = l_orderMgrResVal.calcStopHighPrice(
            l_dblBasePrice,
            l_dblPriceRange,
            l_dblTickValue);

        log.exiting(STR_METHOD_NAME);
        // ５）　@４）calc値幅上限()の戻り値を返却する。
        return l_dblStopHighPrice;
    }

    /**
     * (get値幅下限値（PTS）)<BR>
     * 値幅下限値を取得する。 <BR>
     * <BR>
     * １）　@基準値取得 <BR>
     * 　@PTS発注審査個別チェック.calc基準値（PTS値幅チェック用）()をコールする。 <BR>
     * <BR>
     * 　@[引数設定]<BR>
     * 　@取引銘柄：　@引数.取引銘柄 <BR>
     * <BR>
     * ２）　@値幅取得 <BR>
     * 　@PTS発注審査個別チェック.calc値幅()をコールする。 <BR>
     * <BR>
     * 　@[引数設定]<BR>
     * 　@取引銘柄：　@引数.取引銘柄 <BR>
     * 　@基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値 <BR>
     * 　@上限/下限区分：　@下限 <BR>
     * <BR>
     * ３）　@値幅下限値取得 <BR>
     * 　@３－１）　@（基準値－値幅）≦0の場合、<BR>
     * 　@　@　@　@1を返却する。 <BR>
     * <BR>
     * 　@３－２）　@３－１）以外の場合、<BR>
     * 　@　@　@　@（基準値－値幅）を返却する。 <BR>
     * <BR>
     * 　@※基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値 <BR>
     * 　@※値幅：　@２）calc値幅()の戻り値 <BR>
     * 　@※少数点以下切り捨てとする。　@ <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 475FBC2B00F5
     */
    public double getPTSStopLowPrice(WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPTSStopLowPrice(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // 1)　@基準値取得
        // PTS発注審査個別チェック.calc基準値（PTS値幅チェック用）()をコールする。
        //　@[引数設定]
        // 　@取引銘柄：　@引数.取引銘柄

        double l_dblBasePrice = l_orderMgrResVal.calcBasePriceForPTSPriceRange(l_tradedProduct);

        BigDecimal l_bdBasePrice = new BigDecimal(String.valueOf(l_dblBasePrice));

        // 2)　@値幅取得
        //　@[引数設定]
        // 　@取引銘柄：　@引数.取引銘柄
        // 　@基準値：　@１）calc基準値（PTS値幅チェック用）()の戻り値
        // 　@上限/下限区分：　@下限

        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MINIMUM);

        double l_dblStopLowPriceTemp = (l_bdBasePrice.subtract(
            new BigDecimal(String.valueOf(l_dblPriceRange)))).doubleValue();

        // 3)　@値幅下限値取得
        double l_dblStopLowPrice = Math.floor(l_dblStopLowPriceTemp);
        //　@３－１）　@（基準値－値幅）≦0の場合
        //　@　@1を返却する。
        if (l_dblStopLowPrice <= 0.0D)
        {
            l_dblStopLowPrice = 1.0D;
        }

        //　@３－２）　@３－１）以外の場合
        //　@　@　@（基準値－値幅）を返却する。

        log.exiting(STR_METHOD_NAME);
        return l_dblStopLowPrice;
    }
}
@
