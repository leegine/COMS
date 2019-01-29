head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文リクエストアダプタ(WEB3EquityOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 石炎 (中訊) 修正
Revesion History : 2006/11/02 唐性峰　@(中訊)モデルNo.988
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1090,No.1098
Revesion History : 2007/06/14 何文敏(中訊) モデルNo.1173
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文リクエストアダプタ）。<BR>
 * <BR>
 * 画面に依存した処理をラップするアダプタクラス。<BR>
 * <BR>
 * 当該クラスは、各証券会社の画面用件によって、<BR>
 * 複数作成されることを前提とする。
 * @@version 1.0
 */
public class WEB3EquityOrderRequestAdapter
{

    /**
     * (リクエストデータ)<BR>
     */
    public WEB3GenRequest request;

    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderRequestAdapter.class);

    /**
     * @@roseuid 40A1E05202FD
     */
    protected WEB3EquityOrderRequestAdapter()
    {

    }

    /**
     * 株式注文リクエストアダプタインスタンスを生成する。 <BR>
     * <BR>
     * １）　@本インスタンスを生成しする。 <BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。 <BR>
     * ３）　@インスタンスを返却する。 <BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、 <BR>
     * 本メソッドによってインスタンス化するように制限する） <BR>
     * <BR>
     * @@param l_request - (リクエスト) <BR>
     * リクエストデータオブジェクト <BR>
     * @@return webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapter
     * @@roseuid 401885E30243
     */
    public static WEB3EquityOrderRequestAdapter create(WEB3GenRequest l_request)
    {
        // １）　@本インスタンスを生成しする。
        WEB3EquityOrderRequestAdapter l_adapter = new WEB3EquityOrderRequestAdapter();
        
        // ２）　@生成したインスタンスに引数のリクエストデータをセットする。
        l_adapter.request = l_request;

        // ３）　@インスタンスを返却する。
        return l_adapter;
    }

    /**
     * (get執行条件) <BR>
     * 画面の執行条件より、<BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 張株式注文マネージャ.get執行条件(リクエストデータ.執行条件)にdelegateする。<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 40188A6E00EB
     */
    public EqTypeExecutionConditionType getExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        WEB3EquityCommonRequest l_request = (WEB3EquityCommonRequest)request;
        return l_orderManager.getExecutionConditionType(l_request.execCondType);
    }

    /**
     * (get税区分)<BR>
     * <BR>
     * AP層で使用する税区分（TaxTypeEnum）を返却する。<BR>
     * <BR>
     * １） 売り注文の場合（this.is売注文( )==true）、対象Assetの税区分を返却する。<BR>
     * <BR>
     * 　@　@対象Assetの税区分：株式ポジションマネージャ.getAsset(リクエストデータ.ID).税区分<BR>
     * <BR>
     * ２）　@買い注文の場合（this.is売注文( )==false）、以下の処理を行う。<BR>
     * <BR>
     * ２－１） 一般口座のセット<BR>
     * 　@リクエストデータ.口座区分が”一般”の場合、TaxTypeEnum.”一般”を返却する。<BR>
     * <BR>
     * ２－２）　@特定口座のセット<BR>
     * 　@リクエストデータ.口座区分が”特定”の場合、TaxTypeEnum.”特定”を返却する。<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 40188F9902D0
     */
    public TaxTypeEnum getTaxDivision() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxDivision()";
        log.entering(STR_METHOD_NAME);

        TaxTypeEnum l_taxType = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // １） 売り注文の場合
        if (this.isSellOrder())
        {
            long l_lngAssetId;
            if (request instanceof WEB3EquitySellConfirmRequest)
            {
                l_lngAssetId = Long.parseLong(((WEB3EquitySellConfirmRequest)request).id);
            }
            else
            {
                l_lngAssetId = Long.parseLong(((WEB3EquitySellCompleteRequest)request).id);
            }
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            try
            {
                l_taxType = l_positionManager.getAsset(l_lngAssetId).getTaxType();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        // ２）　@買い注文の場合
        else
        {
            String l_strTaxType;
            if (request instanceof WEB3EquityBuyConfirmRequest)
            {
                l_strTaxType = ((WEB3EquityBuyConfirmRequest)request).taxType;
            }
            else
            {
                l_strTaxType = ((WEB3EquityBuyCompleteRequest)request).taxType;
            }
            // ２－１） 一般口座のセット
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            // ２－２）　@特定口座のセット
            else
            {
            	l_taxType = TaxTypeEnum.SPECIAL;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_taxType;
    }

    /**
     * (is売注文) <BR>
     * リクエストデータの型により、 <BR>
     * AP層で使用するis売注文を返却する。 <BR>
     * <BR>
     * リクエストデータが現物株式売付リクエストの場合はtrue、 <BR>
     * 現物株式買付リクエストの場合はfalseを返却する。 <BR>
     * <BR>
     * 以外は例外をスローする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00170 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40188FC6008D
     */
    public boolean isSellOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSellOrder()";
        log.entering(STR_METHOD_NAME);

        if (request instanceof WEB3EquitySellCompleteRequest || 
            request instanceof WEB3EquitySellConfirmRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if (request instanceof WEB3EquityBuyCompleteRequest || 
                  request instanceof WEB3EquityBuyConfirmRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.error("__isSellOrderでリクエストデータの売買以外の場合でエラー__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00170,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get単価) <BR>
     * 単価をリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエスト.注文単価区分=="指値"の場合は、<BR>
     * リクエストデータ.注文単価を返却する。<BR>
     * <BR>
     * リクエスト.注文単価区分=="成行"の場合は、<BR>
     * 0を返却する。<BR>
     * <BR>
     * @@return double
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@throws WEB3BaseException
     */
    public double getPrice() throws WEB3BaseException
    {
        double l_dbLimitPrice = 0D;
        WEB3EquityCommonRequest l_request = (WEB3EquityCommonRequest)request;
        // リクエスト.注文単価区分=="指値"の場合
        if (l_request.orderPriceDiv.equals(WEB3OrderPriceDivDef.LIMIT_PRICE))
        {
            l_dbLimitPrice = Double.parseDouble(l_request.limitPrice);
            return l_dbLimitPrice;
        }
        return l_dbLimitPrice;
    }
    
    /**
     * (get銘柄コード)<BR>
     * 銘柄コードを取得する。<BR>
     * <BR>
     * １）　@買注文の場合（is売注文()==false）<BR>
     * <BR>
     * 　@　@リクエスト.銘柄コードを返却する。<BR>
     * <BR>
     * ２）　@売注文の場合（is売注文()==true）<BR>
     * <BR>
     * 　@　@株式ポジションマネージャ.getAsset(リクエスト.ID（==保有資産ID）)により、<BR>
     * 　@　@保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@取得した保有資産.getProduct()より、銘柄コードを返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getProductCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductCode()";
        log.entering(STR_METHOD_NAME);
        
        String l_strProductCode = null;
        
        if (!this.isSellOrder())
        {
            if (request instanceof WEB3EquityBuyConfirmRequest)
            {
                l_strProductCode =
                    ((WEB3EquityBuyConfirmRequest)request).productCode;
            }
            else if (request instanceof WEB3EquityBuyCompleteRequest)
            {
                l_strProductCode =
                    ((WEB3EquityBuyCompleteRequest)request).productCode;
            }
        }
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            long l_lngAssetId = 0L;
            String l_strId = null;
            if (request instanceof WEB3EquitySellConfirmRequest)
            {
                l_strId = ((WEB3EquitySellConfirmRequest)request).id;
            }
            else if (request instanceof WEB3EquitySellCompleteRequest)
            {
                l_strId = ((WEB3EquitySellCompleteRequest)request).id;
            }
            if (l_strId != null)
            {
                l_lngAssetId = Long.parseLong(l_strId);
            }
            Asset l_asset = null;
            try
            {
                l_asset = l_positionManager.getAsset(l_lngAssetId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_asset.getProduct();
            l_strProductCode = l_product.getProductCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductCode;
    }
    
    /**
     * (get（Ｗ指値）執行条件)<BR>
     * 画面の執行条件より、<BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.（Ｗ指値）執行条件)にdelegateする。<BR>
     * <BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        if (this.request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3EquitySellConfirmRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3EquitySellCompleteRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3EquityCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

    }

    /**
     * (get市場コード)<BR>
     * 市場コードを取得する。<BR>
     * <BR>
     * １）　@リクエストデータ.市場コード != "99：優先市場"の場合<BR>
     * 　@　@リクエストデータ.市場コードを返却する。 <BR>
     * <BR>
     * ２）　@上記以外の場合<BR>
     * <BR>
     * 　@２－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。<BR>
     * 　@　@　@　@　@　@［getProduct()に設定する引数]<BR>
     * 　@　@　@　@　@　@　@証券会社コード：　@取引カレンダコンテキスト.証券会社コード<BR>
     * 　@　@　@　@　@　@　@銘柄コード　@　@　@：　@this.get銘柄コード( )の戻り値<BR>
     * <BR>
     * 　@２－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。<BR>
     * <BR>
     * 　@２－３）　@２－２）で取得した市場がnullの場合、例外をthrowする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_02702<BR>
     * 　@　@　@　@　@　@以外、市場.getMarketCode()の戻り値を返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getMarketCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);

        //１）　@リクエストデータ.市場コード != "99：優先市場"の場合
        //　@　@リクエストデータ.市場コードを返却する。
        String l_strMarketCode = null;
        if (request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_strMarketCode =
                ((WEB3EquityBuyConfirmRequest)request).marketCode;
        }
        else if (request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_strMarketCode =
                ((WEB3EquityBuyCompleteRequest)request).marketCode;
        }
        else if (request instanceof WEB3EquitySellConfirmRequest)
        {
            l_strMarketCode =
                ((WEB3EquitySellConfirmRequest)request).marketCode;
        }
        else if (request instanceof WEB3EquitySellCompleteRequest)
        {
            l_strMarketCode =
                ((WEB3EquitySellCompleteRequest)request).marketCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            return l_strMarketCode;
        }

        //２）　@上記以外の場合
        //　@２－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。
        //　@　@　@　@　@　@［getProduct()に設定する引数]
        //　@　@　@　@　@　@　@証券会社コード：　@取引カレンダコンテキスト.証券会社コード
        //　@　@　@　@　@　@　@銘柄コード　@　@　@：　@this.get銘柄コード( )の戻り値
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        String l_strInstitutionCode = l_context.getInstitutionCode();
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution, this.getProductCode());
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
        //２－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。
        Market l_market = l_eqTypeProduct.getPrimaryMarket();
        //２－３）　@２－２）で取得した市場がnullの場合、例外をthrowする。
        //以外、市場.getMarketCode()の戻り値を返却する。
        if (l_market == null)
        {
            log.debug("優先市場が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "優先市場が未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
    }

    /**
     * (get注文有効期限)<BR>
     * 注文有効期限を取得する。<BR>
     * <BR>
     * １）　@リクエストデータ.注文有効期限＝nullの場合<BR>
     * 　@　@　@(当日限りの注文の場合)<BR>
     * 　@　@　@　@nullを返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合<BR>
     * 　@　@拡張株式注文マネージャ.get注文有効期限()をコールし、<BR>
     * 　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@　@[get注文有効期限の引数設定]<BR>
     * 　@　@　@注文有効期限：　@リクエストデータ.注文有効期限<BR>
     * 　@　@　@銘柄コード：　@this.get銘柄コード()の戻り値<BR>
     * 　@　@　@市場コード：　@this.get市場コード()の戻り値<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate = null;
        if (request instanceof WEB3EquityBuyConfirmRequest)
        {
            l_datExpirationDate =
                ((WEB3EquityBuyConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3EquityBuyCompleteRequest)
        {
            l_datExpirationDate =
                ((WEB3EquityBuyCompleteRequest)request).expirationDate;
        }
        else if (request instanceof WEB3EquitySellConfirmRequest)
        {
            l_datExpirationDate =
                ((WEB3EquitySellConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3EquitySellCompleteRequest)
        {
            l_datExpirationDate =
                ((WEB3EquitySellCompleteRequest)request).expirationDate;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        if (l_datExpirationDate == null)
        {
            // リクエストデータ.注文有効期限＝nullの場合
            // 　@　@　@　@nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // ２）　@上記以外の場合
            // 銘柄コード：　@this.get銘柄コード()の戻り値
            String l_strProductCode = this.getProductCode();
            // 市場コード：　@this.get市場コード()の戻り値
            String l_strMarketCode = this.getMarketCode();
            // 拡張株式注文マネージャ.get注文有効期限()をコールし、戻り値を返却する。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            l_datExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
