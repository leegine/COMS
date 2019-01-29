head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文リクエストアダプタ(WEB3ToSuccEquityOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 鄭海良(中訊) 新規作成
Revesion History : 2007/01/10 徐宏偉(中訊) モデル215
Revesion History : 2007/01/17 徐宏偉(中訊) モデル221
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (（連続）株式注文リクエストアダプタ)<BR>
 * （連続）株式注文リクエストアダプタ。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderRequestAdapter extends WEB3EquityOrderRequestAdapter 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderRequestAdapter.class);
    
    /**
     * (親注文の注文単位)<BR>
     * 親注文の注文単位。
     */
    public EqTypeOrderUnit parentOrderUnit;
    
    /**
     * (単価)<BR>
     * 単価。<BR>
     * （指値／0（＝成行）／執行単価（±指値））<BR>
     * ※成行注文の場合の概算受渡代金計算に使用した時価は、<BR>
     * ※株式注文内容.get注文単価()で取得する。<BR>
     */
    public double price;

    /**
     * @@roseuid 4348E92D037A
     */
    private WEB3ToSuccEquityOrderRequestAdapter() 
    {
    
    }
    
    /**
     * （連続）株式注文リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成する。<BR>
     * <BR>
     * ２）　@親注文の注文単位オブジェクトを取得する。<BR>
     * 　@　@連続注文マネージャImpl.get株式親注文の注文単位(<BR>
     * 　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。<BR>
     * <BR>
     * ３）　@生成したインスタンスに、引数のリクエストデータ、<BR>
     * 　@　@及び取得した親注文の注文単位オブジェクトをセットする。<BR>
     * 　@　@プロパティの単価には0（初期値）をセットする。<BR>
     * <BR>
     * ４）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     * 本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト
     * @@return WEB3EquityOrderRequestAdapter
     * @@roseuid 432775960357
     */
    public static WEB3EquityOrderRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME =" create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@本インスタンスを生成する。
        WEB3ToSuccEquityOrderRequestAdapter l_adapter = new WEB3ToSuccEquityOrderRequestAdapter();
        
        //２）　@親注文の注文単位オブジェクトを取得する。
        //　@　@連続注文マネージャImpl.get株式親注文の注文単位(
        //　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)l_request;
            l_succCommonInfo = l_buyConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)l_request;
            l_succCommonInfo = l_buyCompleteRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)l_request;
            l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)l_request;
            l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
        }
        else
        {
            log.error("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccEquityOrderRequestAdapter" + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);

        //３）　@生成したインスタンスに、引数のリクエストデータ、
        //　@　@及び取得した親注文の注文単位オブジェクトをセットする。
        //　@　@プロパティの単価には0（初期値）をセットする。
        l_adapter.request = l_request;
        l_adapter.parentOrderUnit = l_orderUnit;
        l_adapter.price = 0;
        
        //４）　@インスタンスを返却する。

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }
    
    /**
     * (get税区分)<BR>
     * AP層で使用する税区分（TaxTypeEnum）を返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １） 売り注文の場合（is売注文( )==true）は、<BR>
     * 　@確定残に対する売と、親注文に対する反対売の場合とで分岐する。<BR>
     * <BR>
     * 　@連続注文マネージャ.is反対売買取引(<BR>
     * 　@リクエスト.連続注文共通情報.連続注文取引区分, this.親注文の注文単位)==trueの<BR>
     * 場合は、<BR>
     * 　@親注文に対する反対売と判定する。<BR>
     * 　@falseの場合は、確定残に対する売注文と判定する。<BR>
     * <BR>
     * １－１）　@親注文に対する反対売の場合は、親注文の該当する税区分を返却する。<BR>
     * 　@　@　@親注文の注文種別=="現物買注文"の場合は、親注文の注文単位.税区分を<BR>
     * 返却する。<BR>
     * 　@　@　@親注文の注文種別=="現引注文"の場合は、親注文の注文単位.税区分<BR>
     * （現引現渡）を返却する。<BR>
     * <BR>
     * １－２）　@確定残に対する売注文の場合は、対象Assetの税区分を返却する。<BR>
     * <BR>
     * 　@　@対象Assetの税区分：株式ポジションマネージャ.getAsset(リクエスト.ID).税区分<BR>
     * <BR>
     * ２）　@買い注文の場合（is売注文( )==false）、以下の処理を行う。<BR>
     * <BR>
     * ２－１）　@一般口座のセット<BR>
     * 　@リクエスト.口座区分が”一般”の場合、TaxTypeEnum.”一般”を返却する。<BR>
     * <BR>
     * ２－２）　@特定口座のセット<BR>
     * 　@リクエスト.口座区分が”特定”の場合、TaxTypeEnum.”特定”を返却する。<BR>
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 432775960367
     */
    public TaxTypeEnum getTaxDivision() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getTaxDivision()";
        log.entering(STR_METHOD_NAME);
        
        //１） 売り注文の場合（is売注文( )==true）は、
        //　@確定残に対する売と、親注文に対する反対売の場合とで分岐する。
        //　@連続注文マネージャ.is反対売買取引(
        //　@リクエスト.連続注文共通情報.連続注文取引区分, this.親注文の注文単位)==trueの
        //  場合は、親注文に対する反対売と判定する。
        //　@falseの場合は、確定残に対する売注文と判定する。
        boolean l_blnSellOrder = this.isSellOrder();
        if (l_blnSellOrder)
        {
            WEB3SuccCommonInfo l_succCommonInfo = null;
            long l_lngAssetId = 0;
            if (super.request instanceof WEB3SuccEquitySellConfirmRequest)
            {
                WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                    (WEB3SuccEquitySellConfirmRequest)super.request;
                l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
                if (!WEB3StringTypeUtility.isEmpty(l_sellConfirmRequest.id))
                {
                    l_lngAssetId = Long.parseLong(l_sellConfirmRequest.id);
                }
            }
            else if (super.request instanceof WEB3SuccEquitySellCompleteRequest)
            {
                WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                    (WEB3SuccEquitySellCompleteRequest)super.request;
                l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
                if (!WEB3StringTypeUtility.isEmpty(l_sellCompleteRequest.id))
                {
                    l_lngAssetId = Long.parseLong(l_sellCompleteRequest.id);
                }
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "パラメータタイプ不正。");
            }

            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
                l_succCommonInfo.succTradingType, 
                this.parentOrderUnit);
            if (l_blnReversingTrade)
            {
                //１－１）　@親注文に対する反対売の場合は、親注文の該当する税区分を返却する。
                //　@　@　@親注文の注文種別=="現物買注文"の場合は、親注文の注文単位.税区分を返却する。
                //　@　@　@親注文の注文種別=="現引注文"の場合は、親注文の注文単位.税区分（現引現渡）を返却する。
                if (this.parentOrderUnit == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME, 
                        "予期しないシステムエラーが発生しました。");
                }
                if (OrderTypeEnum.EQUITY_BUY.equals(this.parentOrderUnit.getOrderType()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return this.parentOrderUnit.getTaxType();
                }
                else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.parentOrderUnit.getOrderType()))
                {
                    EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)this.parentOrderUnit.getDataSourceObject();
                    log.exiting(STR_METHOD_NAME);
                    return l_row.getSwapTaxType();
                }
            }
            else
            {
                //１－２）　@確定残に対する売注文の場合は、対象Assetの税区分を返却する。
                //　@　@対象Assetの税区分：株式ポジションマネージャ.getAsset(リクエスト.ID).税区分
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityPositionManager l_positionManager =
                    (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                Asset l_asset = null;
                try
                {
                    l_asset = l_positionManager.getAsset(l_lngAssetId);
                }
                catch (NotFoundException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                log.exiting(STR_METHOD_NAME);
                return l_asset.getTaxType();
            }
        }
        else
        {        
            String l_strTaxType = null;
            if (super.request instanceof WEB3SuccEquityBuyConfirmRequest)
            {
                WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                    (WEB3SuccEquityBuyConfirmRequest)super.request;
                l_strTaxType = l_buyConfirmRequest.taxType;
            }
            else if (super.request instanceof WEB3SuccEquityBuyCompleteRequest)
            {
                WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                    (WEB3SuccEquityBuyCompleteRequest)super.request;
                l_strTaxType = l_buyCompleteRequest.taxType;
            }
            //２）　@買い注文の場合（is売注文( )==false）、以下の処理を行う。
            //２－１）　@一般口座のセット
            //　@リクエスト.口座区分が”一般”の場合、TaxTypeEnum.”一般”を返却する。
            if (WEB3TaxTypeDivDef.NORMAL.equals(l_strTaxType))
            { 
                log.exiting(STR_METHOD_NAME);
                return TaxTypeEnum.NORMAL;
            }
            //２－２）　@特定口座のセット
            //　@リクエスト.口座区分が”特定”の場合、TaxTypeEnum.”特定”を返却する。
            else if (WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE.equals(l_strTaxType)
                || WEB3TaxTypeDivDef.SPECIAL_SOURCE.equals(l_strTaxType))
            { 
                log.exiting(STR_METHOD_NAME);
                return TaxTypeEnum.SPECIAL;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get単価)<BR>
     * 単価をリクエストオブジェクトより取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@確認リクエストの場合（買付確認リクエスト／売付確認リクエスト）<BR>
     * <BR>
     * 　@プロパティの単価 == 0の場合、<BR>
     * 　@連続注文マネージャ.get株式予約注文執行単価()をコールし、<BR>
     * 　@戻り値をプロパティの単価にセットする。<BR>
     * 　@※親注文が成行注文の場合、時価を基準とした執行単価を計算しているため、<BR>
     * 　@※処理中の時価の変動を考慮し、執行単価を記憶しておく。<BR>
     * <BR>
     * 　@---------------------------------------------------------<BR>
     * 　@＜get株式予約注文執行単価()：引数設定仕様＞<BR>
     * <BR>
     * 　@親注文の注文単位：　@親注文の注文単位<BR>
     * 　@指値：　@super.get単価()の戻り値<BR>
     * 　@単価調整値：　@リクエスト.単価調整値情報==nullの場合は、null。<BR>
     * 　@　@　@　@　@　@　@　@　@　@以外、リクエスト.単価調整値情報.get単価調整値()。<BR>
     * 　@株式取引銘柄：　@親注文の注文単位.getTradedProduct()<BR>
     * 　@---------------------------------------------------------<BR>
     * <BR>
     * 　@１－２）プロパティの単価を返却する。 <BR>
     * <BR>
     * ２）　@完了リクエストの場合（買付完了リクエスト／売付完了リクエスト）<BR>
     * <BR>
     * 　@リクエスト.単価調整値情報==null（指値／成行）の場合は、<BR>
     * 　@super.get単価()の戻り値を返却する。<BR>
     * 　@リクエスト.単価調整値情報≠null（±指値指定）の場合は、<BR>
     * 　@リクエスト.調整後単価を返却する。<BR>
     * 　@※調整後単価がnullの場合は、「執行単価がnull」の例外をthrowする。<BR>
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02707<BR>
     * 　@※調整後単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 431E563800B0
     */
    public double getPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getPrice()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@確認リクエストの場合（買付確認リクエスト／売付確認リクエスト）
        //　@　@プロパティの単価 == 0の場合は、
        //　@　@連続注文マネージャ.get株式予約注文執行単価()をコールし、
        //　@　@戻り値をプロパティの単価にセットする。
        //　@　@※親注文が成行注文の場合、時価を基準とした執行単価を計算しているため、
        //　@　@※処理中の時価の変動を考慮し、執行単価を記憶しておく。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            
        boolean l_blnConfirm = false;
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = null;
        if (super.request instanceof WEB3SuccEquityBuyConfirmRequest)
        {
            WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                (WEB3SuccEquityBuyConfirmRequest)super.request;
            l_priceAdjustmentValueInfo = l_buyConfirmRequest.priceAdjustmentValueInfo;
            l_blnConfirm = true;
        }
        else if (super.request instanceof WEB3SuccEquitySellConfirmRequest)
        {
            WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                (WEB3SuccEquitySellConfirmRequest)super.request;
            l_priceAdjustmentValueInfo = l_sellConfirmRequest.priceAdjustmentValueInfo;
            l_blnConfirm = true;
        }
        if (l_blnConfirm)
        {
            if (this.price == 0)
            {
                Double l_dblPriceAdjustmentValue = null;
                if (l_priceAdjustmentValueInfo != null)
                {
                    l_dblPriceAdjustmentValue = new Double(l_priceAdjustmentValueInfo.getPriceAdjustmentValue());                    
                }

                double l_dblReserveEqtypeOrderExecPrice = l_toOrderManager.getReserveEqtypeOrderExecPrice(
                    this.parentOrderUnit,
                    super.getPrice(),
                    l_dblPriceAdjustmentValue,
                    (WEB3EquityTradedProduct)this.parentOrderUnit.getTradedProduct());

                this.price = l_dblReserveEqtypeOrderExecPrice;
                
            }
            log.exiting(STR_METHOD_NAME);
            return this.price;
        }

        //２）　@完了リクエストの場合（買付完了リクエスト／売付完了リクエスト）
        //　@リクエスト.単価調整値情報==null（指値／成行）の場合は、
        // super.get単価()の戻り値を返却する。
        // 　@リクエスト.単価調整値情報≠null（±指値指定）の場合は、
        // リクエスト.調整後単価を返却する。
        boolean l_blnComplete = false;
        double l_dblAfterAdjustmentPrice = 0;
        String l_strAfterAdjustmentPrice = null;
        if (super.request instanceof WEB3SuccEquityBuyCompleteRequest)
        {
            WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                (WEB3SuccEquityBuyCompleteRequest)super.request;
            l_strAfterAdjustmentPrice = l_buyCompleteRequest.afterAdjustmentPrice;

            if (l_strAfterAdjustmentPrice != null)
            {
                l_dblAfterAdjustmentPrice = Double.parseDouble(l_strAfterAdjustmentPrice);    
            }

            l_priceAdjustmentValueInfo = l_buyCompleteRequest.priceAdjustmentValueInfo;
            l_blnComplete = true;
        }
        else if (super.request instanceof WEB3SuccEquitySellCompleteRequest)
        {
            WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                (WEB3SuccEquitySellCompleteRequest)super.request;
            l_strAfterAdjustmentPrice = l_sellCompleteRequest.afterAdjustmentPrice;

            if (l_strAfterAdjustmentPrice != null)
            {
                l_dblAfterAdjustmentPrice = Double.parseDouble(l_strAfterAdjustmentPrice);    
            }

            l_priceAdjustmentValueInfo = l_sellCompleteRequest.priceAdjustmentValueInfo;
            l_blnComplete = true;
        }
        if (l_blnComplete)
        {
            if (l_priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME);
                return super.getPrice();
            }
            else
            {
                if (l_strAfterAdjustmentPrice == null)
                {
                    log.debug("執行単価がnull。 ");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "執行単価がnull。 ");
                }
                else if (l_dblAfterAdjustmentPrice <= 0)
                {
                    log.debug("執行単価が0以下。 " + l_dblAfterAdjustmentPrice);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "執行単価が0以下。 ");
                }
                log.exiting(STR_METHOD_NAME);
                return l_dblAfterAdjustmentPrice;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * (get銘柄コード)<BR>
     * 銘柄コードを取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * this.get銘柄()で取得した株式銘柄オブジェクト.銘柄コードを返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4327B95B02DA
     */
    public String getProductCode() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getProductCode()";
        log.entering(STR_METHOD_NAME);
        
        String  l_strProductCode = this.getProduct().getProductCode(); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductCode;
    }
    
    /**
     * (get銘柄)<BR>
     * 株式銘柄オブジェクトを取得する。<BR>
     * <BR>
     * １）　@買注文の場合（is売注文()==false）<BR>
     * <BR>
     * 　@　@EQTYPEの拡張プロダクトマネージャ.getProduct(<BR>
     * 　@　@親注文の注文単位.部店IDに該当する部店.getInstitution(), <BR>
     * 　@　@リクエスト.銘柄コード)の戻り値を返却する。<BR>
     * <BR>
     * ２）　@売注文の場合（is売注文()==true）<BR>
     * <BR>
     * 　@　@親注文に対する反対売買かどうかで分岐する。<BR>
     * 　@　@－連続注文マネージャ.is反対売買取引(リクエスト.連続注文取引区分, <BR>
     * 親注文の注文単位)<BR>
     * 　@　@　@==trueであれば、親注文の反対売買であると判定する。<BR>
     * 　@　@－falseであれば、親注文の反対売買でないと判定する。<BR>
     * <BR>
     * 　@　@※is反対売買取引()の引数は、インスタンスプロパティより設定する。<BR>
     * <BR>
     * ２－１）　@親注文の反対売買の場合<BR>
     * <BR>
     * 　@　@親注文の注文単位.getProduct()の戻り値を返却する。<BR>
     * <BR>
     * ２－２）　@親注文の反対売買でない場合<BR>
     * <BR>
     * 　@　@株式ポジションマネージャ.getAsset(リクエスト.ID（==保有資産ID）)により、<BR>
     * 　@　@保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@取得した保有資産.getProduct()の戻り値を返却する。<BR>
     * @@return WEB3EquityProduct
     * @@throws WEB3BaseException
     * @@roseuid 4327BD8003A5
     */
    public WEB3EquityProduct getProduct() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getProduct()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager
            = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            //１）　@買注文の場合（is売注文()==false）
            //　@　@EQTYPEの拡張プロダクトマネージャ.getProduct(
            //　@　@親注文の注文単位.部店IDに該当する部店.getInstitution(), 
            //　@　@リクエスト.銘柄コード)の戻り値を返却する。
            if (!this.isSellOrder())
            {
                String l_strProductCode = null;
                if (super.request instanceof WEB3SuccEquityBuyConfirmRequest)
                {
                    WEB3SuccEquityBuyConfirmRequest l_buyConfirmRequest = 
                        (WEB3SuccEquityBuyConfirmRequest)super.request;
                    l_strProductCode = l_buyConfirmRequest.productCode;
                }
                else if (super.request instanceof WEB3SuccEquityBuyCompleteRequest)
                {
                    WEB3SuccEquityBuyCompleteRequest l_buyCompleteRequest = 
                        (WEB3SuccEquityBuyCompleteRequest)super.request;
                    l_strProductCode = l_buyCompleteRequest.productCode;
                }
                Branch l_branch = l_accountManager.getBranch(this.parentOrderUnit.getBranchId());
                WEB3EquityProduct l_product = null;
                try
                {
                    l_product = (WEB3EquityProduct)l_productManager.getProduct(
                        l_branch.getInstitution(), 
                        l_strProductCode);
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }

                log.exiting(STR_METHOD_NAME);
                return l_product;
            }
            else
            {
                //２）　@売注文の場合（is売注文()==true）
                //　@　@親注文に対する反対売買かどうかで分岐する。
                //　@　@－連続注文マネージャ.is反対売買取引(リクエスト.連続注文取引区分,親注文の注文単位)
                //　@　@　@==trueであれば、親注文の反対売買であると判定する。
                //　@　@－falseであれば、親注文の反対売買でないと判定する。
                //　@　@※is反対売買取引()の引数は、インスタンスプロパティより設定する。
                WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                WEB3SuccCommonInfo l_succCommonInfo = null;
                long l_lngAssetId = 0;
                if (super.request instanceof WEB3SuccEquitySellConfirmRequest)
                {
                    WEB3SuccEquitySellConfirmRequest l_sellConfirmRequest = 
                        (WEB3SuccEquitySellConfirmRequest)super.request;
                    l_succCommonInfo = l_sellConfirmRequest.succCommonInfo;
                    if (!WEB3StringTypeUtility.isEmpty(l_sellConfirmRequest.id))
                    {
                        l_lngAssetId = Long.parseLong(l_sellConfirmRequest.id);
                    }
                }
                else if (super.request instanceof WEB3SuccEquitySellCompleteRequest)
                {
                    WEB3SuccEquitySellCompleteRequest l_sellCompleteRequest = 
                        (WEB3SuccEquitySellCompleteRequest)super.request;
                    l_succCommonInfo = l_sellCompleteRequest.succCommonInfo;
                    if (!WEB3StringTypeUtility.isEmpty(l_sellCompleteRequest.id))
                    {
                        l_lngAssetId = Long.parseLong(l_sellCompleteRequest.id);
                    }
                }

                boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
                    l_succCommonInfo.succTradingType, 
                    this.parentOrderUnit);
                    
                //２－１）　@親注文の反対売買の場合
                if (l_blnReversingTrade) 
                {
                    //親注文の注文単位.getProduct()の戻り値を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return (WEB3EquityProduct)this.parentOrderUnit.getProduct();
                }
                else
                {
                    //２－２）　@親注文の反対売買でない場合
                    //　@　@株式ポジションマネージャ.getAsset(リクエスト.ID（==保有資産ID）)により、
                    //　@　@保有資産オブジェクトを取得する。
                    //　@　@取得した保有資産.getProduct()の戻り値を返却する。
                    WEB3EquityPositionManager l_positionManager =
                        (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                    Asset l_asset = null;
                    try
                    {
                        l_asset = l_positionManager.getAsset(l_lngAssetId);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    
                    log.exiting(STR_METHOD_NAME);
                    return (WEB3EquityProduct)l_asset.getProduct();
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
    }
}
@
