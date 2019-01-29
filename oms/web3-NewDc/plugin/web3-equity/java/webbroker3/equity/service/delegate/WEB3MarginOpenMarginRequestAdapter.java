head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建リクエストアダプタ(WEB3MarginOpenMarginRequestAdapter)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 王暁傑(Sinocom) 新規作成
Revesion History : 2006/11/24 唐性峰(Sinocom)　@モデルNo.1001
Revesion History : 2006/12/25 張騰宇 (中訊) モデル 1090
Revesion History : 2007/06/13 何文敏 (中訊) モデル 1169
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCommonRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;

/**
 * （信用取引新規建リクエストアダプタ）。<BR>
 * <BR>
 * 信用取引新規建リクエストアダプタクラス
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MarginOpenMarginRequestAdapter 
{
    /**
     * (ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginRequestAdapter.class);
            
    /**
     * (リクエストデータ)。<BR>
     */
    public WEB3GenRequest request;
    
    /**
     * (コンストラクタ)。
     * @@roseuid 4142B32D0153
     */
    protected WEB3MarginOpenMarginRequestAdapter() 
    {
    }
    
    /**
     * (create)。<BR>
     * <BR>
     * 信用取引新規建リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、本メソッドによってインスタンス化するように制限する）<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト
     * @@return WEB3MarginOpenMarginRequestAdapter
     * @@roseuid 40AAC1B700F0
     */
    public static WEB3MarginOpenMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        //本インスタンスを生成しする。
        WEB3MarginOpenMarginRequestAdapter l_openMarginRequestAdapter = new WEB3MarginOpenMarginRequestAdapter();
        //成したインスタンスに引数のリクエストデータをセットする。
        l_openMarginRequestAdapter.request = l_request;
        //インスタンスを返却する。        
        return l_openMarginRequestAdapter;
    }
    
    /**
     * (get執行条件)。<BR>
     * <BR>
     * リクエストデータ.執行条件より、AP層で使用する執行条件（<BR>
     * EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * リクエストデータ.執行条件より、 <BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR> 
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.執行条件)にdelegateする。<BR>
     * <BR> 
     * @@return EqTypeExecutionConditionType
     * @@roseuid 40AAC1B700F2
     */
    public EqTypeExecutionConditionType getExecutionCondition()
    {
        final String STR_METHOD_NAME = "getExecutionCondition()";
        log.entering(STR_METHOD_NAME);
        
        String l_executionCondition = null;
        
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_executionCondition = ((WEB3MarginOpenMarginConfirmRequest)this.request).execCondType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_executionCondition = ((WEB3MarginOpenMarginCompleteRequest)this.request).execCondType;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
        EqTypeExecutionConditionType l_EqTypeExecutionConditionType;
        try {
            l_EqTypeExecutionConditionType = l_orderManager.getExecutionConditionType(l_executionCondition);
        }
        catch (WEB3BaseException l_wbe) {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00019, STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_EqTypeExecutionConditionType;
    }
    
    /**
     * (get税区分)。<BR>
     * <BR>
     * リクエストデータ.口座区分より、AP層で使用する税区分（TaxTypeEnum）を返却する。 <BR>
     * <BR>
     * １） 一般口座のセット <BR>
     * 　@・リクエストデータ.口座区分が”一般”の場合、TaxTypeEnum.”一般”を返却する。 <BR>
     * <BR>
     * ２）　@特定口座のセット <BR>
     * 　@・リクエストデータ.口座区分が”特定”の場合、 TaxTypeEnum.”特定”を返却する。<BR>
     * <BR>
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 40AAC1B700F3
     */
    public TaxTypeEnum getTaxType() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxType()";
        log.entering(STR_METHOD_NAME);
        
        String l_strTaxType = null;
        
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strTaxType = ((WEB3MarginOpenMarginConfirmRequest)this.request).taxType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strTaxType = ((WEB3MarginOpenMarginCompleteRequest)this.request).taxType;
        }
        
        TaxTypeEnum l_taxTypeEnum = null;
        log.debug("l_strTaxType = " + l_strTaxType);
        
        //1)一般口座のセット
        if (WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        //２）特定口座のセット
        else if (WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
        {
        	l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }
    
    /**
     * (is買建)。<BR>
     * <BR>
     * リクエストデータ.建区分より、AP層で使用するis買建を返却する。<BR>
     * <BR>
     * リクエストデータ.取引区分＝”新規買建注文”の場合はtrue、<BR>
     * リクエストデータ.取引区分＝”新規売建注文”の場合はfalseを返却する。<BR>
     * <BR>
     * リクエストデータ.取引区分が上記以外の場合は、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00639<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40AAC1B700F4
     */
    public boolean isLong() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginConfirmRequest)this.request).tradingType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginCompleteRequest)this.request).tradingType;
        }
        
        if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_strTradingType))
        {
            //リクエストデータ.取引区分＝”新規買建注文”の場合はtrue、
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_strTradingType))
        {
            //リクエストデータ.取引区分＝”新規売建注文”の場合はfalseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else 
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00639, STR_METHOD_NAME);
        }        
    }
    
    /**
     * (is売建)。<BR>
     * <BR>
     * リクエストデータ.建区分より、AP層で使用するis売建を返却する。<BR>
     * <BR>
     * リクエストデータ.取引区分＝”新規売建注文”の場合はtrue、<BR>
     * リクエストデータ.取引区分＝”新規買建注文”の場合はfalseを返却する。<BR>
     * <BR>
     * リクエストデータ.取引区分が上記以外の場合は、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00640<BR>     
     * <BR>
     * @@return boolean
     * @@roseuid 40B42854022A
     */
    public boolean isShort() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginConfirmRequest)this.request).tradingType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strTradingType = ((WEB3MarginOpenMarginCompleteRequest)this.request).tradingType;
        }
        
        if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_strTradingType))
        {
            //リクエストデータ.取引区分＝”新規売建注文”の場合はtrue、
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_strTradingType))
        {
            //リクエストデータ.取引区分＝”新規買建注文”の場合はfalseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else 
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00640, STR_METHOD_NAME);
        }   
    }
    
    /**
     * (get単価)<BR>
     * 単価をリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエストデータ.注文単価区分=="指値"の場合は、<BR>
     * リクエストデータ.注文単価を返却する。<BR>
     * <BR>
     * リクエストデータ.注文単価区分=="成行"の場合は、<BR>
     * 0を返却する。<BR>
     * @@return double
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderPriceDiv = null; 
        if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginOpenMarginConfirmRequest)this.request).orderPriceDiv;
        }
        else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginOpenMarginCompleteRequest)this.request).orderPriceDiv;
        }
        
        double l_dblPrice = 0.0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            String l_strLimitPrice = null; 
            if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginOpenMarginConfirmRequest)this.request).limitPrice;
            }
            else if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginOpenMarginCompleteRequest)this.request).limitPrice;
            }
            if (l_strLimitPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_strLimitPrice);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }

    /**
     * (get（Ｗ指値）執行条件)<BR>
     * リクエストデータ.執行条件より、<BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.（Ｗ指値）執行条件)にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        if (this.request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3MarginCommonRequest)this.request).wlimitExecCondType);
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeExecutionConditionType;
        }
        else if (this.request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_eqTypeExecutionConditionType =
                l_orderManager.getExecutionConditionType(
                    ((WEB3MarginCommonRequest)this.request).wlimitExecCondType);
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
     * (get市場コード )<BR>
     * 市場コードを取得する。 <BR>
     * <BR>
     * １）　@リクエストデータ.市場コード != "99：優先市場"の場合 <BR>
     * 　@　@リクエストデータ.市場コードを返却する。 <BR>
     * <BR>
     * ２）　@上記以外の場合 <BR>
     * <BR>
     * 　@２－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。 <BR>
     * 　@　@　@　@　@　@[getProduct()に設定する引数] <BR>
     * 　@　@　@　@　@　@　@証券会社コード：　@取引カレンダコンテキスト.証券会社コード <BR>
     * 　@　@　@　@　@　@　@銘柄コード　@　@　@：　@リクエストデータ.銘柄コード <BR>
     * <BR>
     * 　@２－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。 <BR>
     * <BR>
     * 　@２－３）　@２－２）で取得した市場がnullの場合、例外をthrowする。 <BR>
     * 　@　@　@　@　@　@以外、市場.getMarketCode()の戻り値を返却する。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:    BUSINESS_ERROR_02702<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getMarketCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);

        String l_strMarketCode = "";
        String l_strProductCode = "";
        //１）　@リクエストデータ.市場コード != "99：優先市場"の場合
        //　@　@リクエストデータ.市場コードを返却する。
        if (request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_strMarketCode = ((WEB3MarginOpenMarginConfirmRequest)request).marketCode;
            l_strProductCode = ((WEB3MarginOpenMarginConfirmRequest)request).productCode;
        }
        else if (request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_strMarketCode = ((WEB3MarginOpenMarginCompleteRequest)request).marketCode;
            l_strProductCode = ((WEB3MarginOpenMarginCompleteRequest)request).productCode;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }

        //　@２－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。
        //　@　@　@　@　@　@[getProduct()に設定する引数]
        //　@　@　@　@　@　@　@証券会社コード：　@取引カレンダコンテキスト.証券会社コード
        //　@　@　@　@　@　@　@銘柄コード　@　@　@：　@リクエストデータ.銘柄コード
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
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_context.getInstitutionCode());
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(
                    l_institution,
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

        //２－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。
        Market l_market = l_eqTypeProduct.getPrimaryMarket();

        //２－３）　@２－２）で取得した市場がnullの場合、例外をthrowする。
        //　@　@　@　@　@　@以外、市場.getMarketCode()の戻り値を返却する。
        if (l_market == null)
        {
            log.debug("優先市場が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "優先市場が未指定です。");
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_market.getMarketCode();
        }
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
     * 　@　@　@銘柄コード：　@リクエストデータ.銘柄コード<BR>
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
        String l_strProductCode = null;
        if (request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginConfirmRequest)request).expirationDate;
            l_strProductCode = ((WEB3MarginOpenMarginConfirmRequest)request).productCode;
        }
        else if (request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginCompleteRequest)request).expirationDate;
            l_strProductCode = ((WEB3MarginOpenMarginCompleteRequest)request).productCode;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        // リクエストデータ.注文有効期限＝nullの場合
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // 上記以外の場合
            // 拡張株式注文マネージャ
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();

            // 市場コード：　@this.get市場コード()の戻り値
            String l_strMarketCode = this.getMarketCode();

            // 注文有効期限
            l_datExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
