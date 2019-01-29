head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTypeOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式発注審査個別チェック(WEB3FeqTypeOrderManagerReusableValidations.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  魏馨(中訊) 新規作成
Revesion History : 2005/07/28  呉艶飛(中訊) レビュー
Revesion History : 2006/09/14  齊珂(中訊) レビュー
Revesion History : 2006/10/12  徐宏偉(中訊) バグ3063の対応
Revesion History : 2006/10/30  徐大方(中訊) 仕様変更モデル296
Revesion History : 2006/12/04  李 俊(中訊) 仕様変更モデル308
Revesion History : 2007/03/15  齊珂(中訊) 仕様変更モデル347
Revesion History : 2008/01/17  柴双紅(中訊) 実装No.007、モデル378、モデル372
Revesion History : 2008/02/01  馮海濤 (中訊) モデル No.390
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.466
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerErrorCatalog;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3MarketPreferencesValueDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.feq.data.FCashBalanceRow;
import webbroker3.feq.data.FeqTickValuesMstRow;
import webbroker3.feq.util.WEB3FeqDateUtility;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式発注審査個別チェック)<BR>
 * 外国株式発注審査個別チェック<BR>
 * 
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqTypeOrderManagerReusableValidations extends FeqProductTypeOrderManagerReusableValidations 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTypeOrderManagerReusableValidations.class);
        
    /**
     * @@roseuid 42CE39EC00BB
     */
    public WEB3FeqTypeOrderManagerReusableValidations() 
    {
     
    }
    
    /**
     * スーパークラスに自身のインスタンスを登録する。 <BR>
     * <BR>
     * （プラグイン初期化時にコールされる） <BR>
     * <BR>
     * --- <BR>
     * super.setInstance(this); <BR>
     * --- <BR>
     * @@roseuid 4280923D020C
     */
    public static void register() 
    {
        //外国株式発注審査個別チェック.setInstance()をコールする
        FeqProductTypeOrderManagerReusableValidations.setInstance(
            new WEB3FeqTypeOrderManagerReusableValidations());
    }
    
    /**
     * (validate外国株式口座開設)<BR>
     * 顧客が外国株式口座を開設しているかをチェックする。 <BR>
     * <BR>
     * １）　@顧客オブジェクト取得 <BR>
     * 　@補助口座.getMainAccount()にて顧客オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@口座開設区分チェック <BR>
     * 　@顧客が外国株式口座を開設していない場合<BR>
     * 　@（顧客.顧客Params.外国証券口座開設区分 !=  1：開設）、<BR>
     * 　@例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01944<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException 
     * @@roseuid 428093EC01CD
     */
    public void validateFeqAccountEstablish(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateFeqAccountEstablish(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //顧客オブジェクトを取得する
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        MainAccountRow l_row = (MainAccountRow)l_mainAccount.getDataSourceObject();        
        String l_strForeignSecAccOpenDiv = l_row.getForeignSecAccOpenDiv();
        
        //顧客.顧客Params.外国証券口座開設区分 !=  1：開設例外をスローする
        if (!(WEB3AccountOpenDef.OPEN.equals(l_strForeignSecAccOpenDiv)))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01944.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01944,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取引銘柄)<BR>
     * （validateTradedProductのオーバーロード）<BR>
     * <BR>
     * １）　@this.validate取引銘柄()をコールする。<BR>
     * <BR>
     * 　@[validate取引銘柄()に指定する引数]<BR>
     * 　@　@外国株式銘柄：　@パラメータ.外国株式銘柄<BR>
     * 　@　@市場：　@パラメータ.市場<BR>
     * <BR>
     * ２）　@取引規制チェック<BR>
     * 　@　@（外国株式取引銘柄.is取引規制(is買注文) == true）の場合、<BR>
     * 　@　@取引規制中と判定し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02087<BR>
     * @@param l_feqProduct - (外国株式銘柄)<BR>
     * 外国株式銘柄オブジェクト
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト
     * @@param l_blnIsBuyOrder - （is買注文）<BR>
     * 買注文かの判定<BR>
     * <BR>
     * true：買<BR>
     * false：売
     * 
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 42805E6800D3
     */
    public TradedProduct validateTradedProduct(FeqProduct l_feqProduct,
        Market l_market, boolean l_blnIsBuyOrder) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTradedProduct(FeqProduct, Market, boolean)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqProduct == null || l_market == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //this.validate取引銘柄()をコールする
        WEB3FeqTradedProduct l_feqTradedProduct = (WEB3FeqTradedProduct) this.validateTradedProduct(l_feqProduct, l_market);

        //外国株式取引銘柄.is取引規制(is買注文)
        boolean l_blnIsTradingSuspended = l_feqTradedProduct.isTradingSuspended(l_blnIsBuyOrder);
        if(l_blnIsTradingSuspended)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02087.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02087,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        
        log.exiting(STR_METHOD_NAME); 
        return l_feqTradedProduct;
    }
    
    /**
     * (validate取引銘柄)<BR>
     * （validateTradedProductのオーバーライド）<BR>
     * <BR>
     * １）　@super.validateTradedProduct()をコールし、<BR>
     * 　@　@外国株式取引銘柄オブジェクトを取得する。<BR>
     * 　@　@－外国株式取引銘柄が取得できなかった場合、<BR>
     * 　@　@取引銘柄なしの例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02088<BR>
     * 　@　@－OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTED<BR>
     * 　@　@がスローされた場合、<BR>
     * 　@　@　@非上場の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02089<BR>
     * <BR>
     * ２）　@上場期間チェック<BR>
     * 　@　@（外国株式取引銘柄.is上場期間内() == false）の場合、<BR>
     * 　@　@取引可能でないと判定し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02090<BR>
     * @@param l_feqProduct - (外国株式銘柄)<BR>
     * 外国株式銘柄オブジェクト
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 42C20D1A006B
     */
    public TradedProduct validateTradedProduct(FeqProduct l_feqProduct,
        Market l_market) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTradedProduct(FeqProduct, Market)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqProduct == null || l_market == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        WEB3FeqTradedProduct l_feqTradedProduct = null;
        try
        {
            l_feqTradedProduct = (WEB3FeqTradedProduct) super.validateTradedProduct(l_feqProduct, l_market);
        }
        //OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTEDがスローされた場合非上場の例外をスローする
        
        catch (OrderValidationException l_ex)
        {
            if (OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTED.equals(
                    l_ex.getValidationResult().getProcessingResult().getErrorInfo()))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02089.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02089, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);
            }
            else
            {
                log.error(l_ex.getMessage());
            }
        }
        //外国株式取引銘柄が取得できなかった場合 取引銘柄なしの例外をスローする
        if (l_feqTradedProduct == null)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02088.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088, 
                getClass().getName() + "." + STR_METHOD_NAME);
        }                               
        boolean l_blnIsOpen = l_feqTradedProduct.isOpen();
        //（外国株式取引銘柄.is上場期間内() == false）の場合取引可能でないと判定し例外をスローする
        if (!l_blnIsOpen)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02090.getErrorMessage());
            //例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02090,
                getClass().getName() + "." + STR_METHOD_NAME);
        }                     
        log.exiting(STR_METHOD_NAME);    
        
        return l_feqTradedProduct;
    }
    
    /**
     * (validate外株銘柄)<BR>
     * （validateFeqProductのオーバーライド）<BR>
     * <BR>
     * １）　@super.validateFeqProduct()をコールし、<BR>
     * 　@　@外国株式銘柄オブジェクトを取得する。<BR>
     * 　@　@－外国株式銘柄が取得できなかった場合、<BR>
     * 　@　@銘柄なしの例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00391<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード
     * @@return FeqProduct
     * @@throws OrderValidationException
     * @@roseuid 428061B201FC
     */
    public FeqProduct validateFeqProduct(Institution l_institution,
        String l_strProductCode) throws OrderValidationException 
    {
        final String STR_METHOD_NAME = " validateFeqProduct(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //外国株式銘柄オブジェクトを取得する
            l_feqProduct = (WEB3FeqProduct) super.validateFeqProduct(l_institution, l_strProductCode);
        }
        catch (OrderValidationException l_ex)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_02142);
        }

        //外国株式銘柄が取得できなかった場合、外国株式銘柄が取得できないの例外をスローする
        if (l_feqProduct == null)
        {
            log.debug("外国株式銘柄が取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_02142);
        }
        log.exiting(STR_METHOD_NAME);
        return l_feqProduct;
    }
    
    /**
     * (validate注文単価)<BR>
     * （validatePriceのオーバーライド）<BR>
     * <BR>
     * １）　@指値／成行チェック<BR>
     * 　@－（is成行注文 == true && 注文単価 != 0）の場合、<BR>
     * 　@成行注文で単価が指定されたと判定し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02091
     * <BR>
     * 　@－（is成行注文 == false && 注文単価 == 0）の場合、<BR>
     * 　@指値注文で単価が未指定と判定し例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02092<BR>
     * <BR>
     * ※　@２）以降の処理は、指値注文の場合<BR>
     * 　@（is成行注文 == false）のみ実施。<BR>
     * <BR>
     * ２）　@注文単価サイズチェック<BR>
     * 　@整数部６桁，小数部５桁の範囲内でない場合、<BR>
     * 　@サイズが不正と判定し例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02093<BR>
     * <BR>
     * ３）　@呼値チェック<BR>
     * 　@this.validate呼値()をコールする。<BR>
     * <BR>
     * 　@[validate呼値()に指定する引数]<BR>
     * 　@市場コード：　@取引銘柄.getMarket().getMarketCode()<BR>
     * 　@注文単価：　@注文単価<BR>
     * <BR>
     * ４）　@値幅チェック<BR>
     * 　@this.validate値幅()をコールする。<BR>
     * <BR>
     * 　@[validate値幅()に指定する引数]<BR>
     * 　@外国株式取引銘柄：　@取引銘柄<BR>
     * 　@注文単価：　@注文単価<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 外国株式取引銘柄オブジェクト
     * 
     * @@param l_dblOrderPrice - (注文単価)<BR>
     * 注文単価
     * @@param l_blnIsMarketOrder - （is成行注文）<BR>
     * 成行注文かを判定する。<BR>
     * <BR>
     * true：成行注文<BR>
     * false：指値注文
     * 
     * @@throws OrderValidationException
     * @@roseuid 4280712003A2
     */
    public void validatePrice(TradedProduct l_tradedProduct,
        double l_dblOrderPrice, boolean l_blnIsMarketOrder) throws OrderValidationException 
    {
        final String STR_METHOD_NAME = 
                " validatePrice(TradedProduct, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）　@指値／成行チェック 
        //  －（is成行注文 == true && 注文単価 != 0）の場合、成行注文で単価が指定されたと判定し例外をスローする。 
        //  －（is成行注文 == false && 注文単価 == 0）の場合、指値注文で単価が未指定と判定し例外をスローする。
        try
        {
            if (l_blnIsMarketOrder && l_dblOrderPrice != 0)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02091.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02091,
                        getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (!l_blnIsMarketOrder && l_dblOrderPrice == 0)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02092.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02092,
                    getClass().getName() + "." + STR_METHOD_NAME);   
            }    
            //※　@２）以降の処理は、指値注文の場合（is成行注文 == false）のみ実施。 
            //
            //２）　@注文単価サイズチェック 
            if (!l_blnIsMarketOrder)
            {
                //this.validate呼値()をコールする。 
                String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
                try
                {
                    //３）　@呼値チェック
                    this.validateTickValue(l_strMarketCode, l_dblOrderPrice);
                    //４）　@値幅チェック
                    this.validatePriceRange((FeqTradedProduct)l_tradedProduct, l_dblOrderPrice);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.debug(l_ex.getErrorInfo().getErrorMessage());
                    throw new OrderValidationException(l_ex.getErrorInfo());
                }
            }
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getErrorInfo().getErrorMessage());
            throw new OrderValidationException(l_ex.getErrorInfo());
        }   
         
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文株数)<BR>
     * （validateQuantity）<BR>
     * <BR>
     * １）　@注文株数サイズチェック <BR>
     * 　@１－１）　@市場プリファ@レンステーブルを読んでチェック桁数を特定。 <BR>
     * 　@　@　@　@市場プリファ@レンステーブルを以下のキー（findRowByPｋ()に指定する引数）<BR>
     * 　@　@　@　@で読み存在するレコードを獲得する。<BR>
     * 　@　@　@　@　@　@市場ID　@　@　@　@　@　@　@：市場.getMarketid() <BR>
     * 　@　@　@　@　@　@プリファ@レンス項目名：feq.order.quantity.size <BR>
     * 　@　@　@　@　@　@項目名連番　@　@　@　@：　@買付の場合（引数.is買注文 == true）　@：　@1<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@売付の場合（引数.is買注文 == false）　@：　@2<BR>
     * <BR>
     *  　@１－２）　@レコードが存在しない場合、以下のチェックを行う。<BR> 
     *  　@　@　@　@　@９桁以内の整数値でない場合、サイズが不正と判定し例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00903<BR>
     *  <BR>
     *  　@１－３）　@レコードが存在する場合、以下のチェックを行う。 <BR>
     *  　@　@　@　@　@プリファ@レンスの値の桁数以内の整数値で無い場合、サイズが不正と判定し例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00903<BR>
     * <BR>
     * ２）　@最低注文株数チェック <BR>
     * 　@２－１）　@最低注文株数を取得する。 <BR>
     * 　@　@－買付の場合（is買注文 == true）、BR> 
     *    外国株式取引銘柄.getBuyOrderMinimumQuantity() を最低注文株数とする。<BR> 
     *　@ 　@－売付の場合（is買注文 == false）、BR> 
     *    外国株式取引銘柄.getSellOrderMinimumQuantity() を最低注文株数とする。<BR> 
     *  <BR>
     *　@ ２－２）　@最低注文株数以上のチェック <BR>
     *　@　@ （最低注文株数　@> 注文株数）の場合、BR> 
     *      注文株数が最低注文株数未満であると判定し、例外をスローする。 <BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:  BUSINESS_ERROR_02094<BR>
     *  <BR>
     * ３）　@売買単位チェック <BR>
     *　@ ３－１）　@売買単位を取得する <BR>
     *　@　@ －買付注文の場合（is買注文 == true）、BR> 
     *     外国株式取引銘柄.get買付単位()　@を売買単位とする。 <BR>
     *　@　@ －売付注文の場合（is買注文 == false）、BR> 
     *     外国株式取引銘柄.get売付単位()　@を売買単位とする。 <BR>
     *  <BR>
     * 　@３－２）　@売買単位の整数倍かのチェック <BR>
     *　@ 　@（注文株数％売買単位 != 0）の場合、BR> 
     *      注文株数が売買単位の整数倍でないと判定し、例外をスローする。 <BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:  BUSINESS_ERROR_00708<BR>
     * <BR>   
     * ４）　@最大注文株数チェック <BR>
     * 　@４－１）　@市場プリファ@レンステーブルを読んでチェック対象の市場か否か判定。<BR>
     * 　@　@　@市場プリファ@レンステーブルを以下のキー（findRowByPｋ()に指定する引数）<BR>
     * 　@　@　@で読み存在するレコードを獲得する。<BR>
     * 　@　@　@　@　@　@市場ID　@　@　@　@　@　@　@：市場.getMarketid()<BR>
     * 　@　@　@　@　@　@プリファ@レンス項目名：feq.order.quantity.limit <BR>
     * 　@　@　@　@　@　@項目名連番　@　@　@　@：　@買付の場合（引数.is買注文 == true）　@：　@1<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@売付の場合（引数.is買注文 == false）　@：　@2<BR>
     * <BR>
     * 　@４－２）　@レコードが存在しない場合、以下の処理を行わずに終了する。<BR>
     * <BR>
     * 　@４－３）　@レコードが存在する場合、以下のチェックを行う。<BR>
     * 　@　@　@　@　@　@注文株数＞プリファ@レンスの値×売買単位（上記３－１　@で獲得したもの）の場合<BR>
     * 　@　@　@　@　@　@注文株数が最大注文株数を超えると判定し、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:  BUSINESS_ERROR_00160<BR>
     * @@param l_feqTradedProduct - (外国株式取引銘柄)<BR>
     * 外国株式取引銘柄オブジェクト
     * @@param l_dblOrderNumber - (注文株数)<BR>
     * 注文株数
     * @@param l_blnIsBuyOrder - （is買注文）<BR>
     * 買注文かの判定<BR>
     * <BR>
     * true：買<BR>
     * false：売
     * @@throws OrderValidationException 
     * 
     * @@throws OrderValidationException
     * @@roseuid 42807125024A
     */
    public void validateQuantity(FeqTradedProduct l_feqTradedProduct,
        double l_dblOrderNumber, boolean l_blnIsBuyOrder) throws OrderValidationException
    {

        final String STR_METHOD_NAME = 
            " validateQuantity(FeqTradedProduct, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        MarketPreferencesRow l_row = null;
        
        if (l_feqTradedProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        // １）　@注文株数サイズチェック
        // 　@１－１）　@市場プリファ@レンステーブルを読んでチェック桁数を特定。
        //　@　@　@　@市場プリファ@レンステーブルを以下のキー（findRowByPｋ()に指定する引数）
        //        で読み存在するレコードを獲得する。
        //　@　@　@　@　@　@市場ID　@　@　@　@　@　@　@：市場.getMarketid()
        //　@　@　@　@　@　@プリファ@レンス項目名：feq.order.quantity.size
        //            項目名連番　@　@　@　@：　@買付の場合（引数.is買注文 == true）　@：　@1 
        //　@　@ 　@                          売付の場合（引数.is買注文 == false）　@：　@2 

        long l_lngMarketId = l_feqTradedProduct.getMarket().getMarketId();
        String l_strPreferencesName = WEB3MarketPreferencesNameDef.FEQ_ORDER_QUANTITY_SIZE;
        int l_intSerialNumber = 0;
        if (l_blnIsBuyOrder == true)
        {
            l_intSerialNumber = 1;
        }
        else if (l_blnIsBuyOrder == false)
        {
            l_intSerialNumber = 2;
        }

        try
        {
            l_row = MarketPreferencesDao.findRowByPk(
                l_lngMarketId,
                l_strPreferencesName,
                l_intSerialNumber);
        }
        catch (DataFindException l_ex)
        {
            log.debug("Error in DataFindException..");
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strOrderNumber = WEB3StringTypeUtility.formatNumber(l_dblOrderNumber);
        int l_intIntegerLength = WEB3StringTypeUtility.getIntegerDigits(l_strOrderNumber);
        //　@１－２）　@レコードが存在しない場合、以下のチェックを行う。
        //　@　@　@　@　@９桁以内の整数値でない場合、サイズが不正と判定し例外をスローする。
        if (l_row == null)
        {
            if (!(WEB3StringTypeUtility.isInteger(l_strOrderNumber)) || l_intIntegerLength > 9)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00903.getErrorMessage());
                //例外をスローする
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00903);
            }
        }

        //　@１－３）　@レコードが存在する場合、以下のチェックを行う。
        //　@　@　@　@　@プリファ@レンスの値の桁数以内の整数値で無い場合、サイズが不正と判定し例外をスローする。
        else
        {
            if (!(WEB3StringTypeUtility.isInteger(l_strOrderNumber)) ||
                l_intIntegerLength > Integer.parseInt(l_row.getValue()))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00903.getErrorMessage());
                //例外をスローする
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00903);
            }
        }

        //２）　@最低注文株数チェック 
        //２－１）　@最低注文株数を取得する。
        double l_dblQuantity = 0D;
        //－買付の場合（is買注文 == true）、外国株式取引銘柄.getBuyOrderMinimumQuantity() を最低注文株数とする
        if (l_blnIsBuyOrder)
        {
            l_dblQuantity = l_feqTradedProduct.getBuyOrderMinimumQuantity();
        }
        //－売付の場合（is買注文 == false）、外国株式取引銘柄.getSellOrderMinimumQuantity() を最低注文株数とする。 
        else
        {
            l_dblQuantity = l_feqTradedProduct.getSellOrderMinimumQuantity();
        }
        //　@２－２）　@最低注文株数以上のチェック 
        //（最低注文株数　@> 注文株数）の場合、注文株数が最低注文株数未満であると判定し、例外をスローする。
        if (l_dblQuantity > l_dblOrderNumber)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02094.getErrorMessage());
            //例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_02094);
        }        
        //３）　@売買単位チェック 
        // ３－１）　@売買単位を取得する 
        double l_dblLotSize = 0D;
        if (l_blnIsBuyOrder)
        {
            //－買付注文の場合（is買注文 == true）、外国株式取引銘柄.get買付単位()　@を売買単位とする。
            l_dblLotSize = ((WEB3FeqTradedProduct)l_feqTradedProduct).getBuyOrderLotSize();
        }
        else
        {
            //－売付注文の場合（is買注文 == false）、外国株式取引銘柄.get売付単位()　@を売買単位とする。
            l_dblLotSize = ((WEB3FeqTradedProduct)l_feqTradedProduct).getSellOrderLotSize();
        }
        //　@３－２）　@売買単位の整数倍かのチェック 
        //　@（注文株数％売買単位 != 0）の場合、注文株数が売買単位の整数倍でないと判定し、例外をスローする。
        if (!WEB3FeqOrderUtility.isRemainderZero(l_dblOrderNumber, l_dblLotSize))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00708.getErrorMessage());
            //例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00708);

        }
        
        //４）　@最大注文株数チェック
        //  ４－１）　@市場プリファ@レンステーブルを読んでチェック対象の市場か否か判定。
        // 　@　@　@市場プリファ@レンステーブルを以下のキー（findRowByPｋ()に指定する引数）
        // 　@　@　@で読み存在するレコードを獲得する。
        // 　@　@　@　@　@　@市場ID　@　@　@　@　@　@　@：市場.getMarketid()
        // 　@　@　@　@　@　@プリファ@レンス項目名：feq.order.quantity.limit 
        //            項目名連番　@　@　@　@：　@買付の場合（引数.is買注文 == true）　@：　@1 
        //　@　@ 　@                          売付の場合（引数.is買注文 == false）　@：　@2 
        String l_strName = WEB3MarketPreferencesNameDef.FEQ_ORDER_QUANTITY_LIMIT;
        
        try
		{
			l_row = MarketPreferencesDao.findRowByPk(
				l_lngMarketId,
				l_strName,
                l_intSerialNumber);
		}
        catch (DataFindException l_ex)
        {
            log.debug("Error in DataFindException..");
            l_row = null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // ４－３）　@レコードが存在する場合、以下のチェックを行う。
        if (l_row != null)
        {
        	//注文株数＞プリファ@レンスの値×売買単位（上記３－１　@で獲得したもの）の場合
            //注文株数が最大注文株数を超えると判定し、例外をスローする。
        	BigDecimal l_bdValue = new BigDecimal(l_row.getValue());
        	BigDecimal l_bdLotSize = new BigDecimal(l_dblLotSize);
        	
        	BigDecimal l_bdTemp = l_bdValue.multiply(l_bdLotSize);
        	
        	BigDecimal l_bdOrderNumber = new BigDecimal(l_dblOrderNumber);
        	
        	if (l_bdOrderNumber.compareTo(l_bdTemp) > 0)
        	{            
        		log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00160.getErrorMessage());
	            //例外をスローする
	            log.exiting(STR_METHOD_NAME);
	            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00160);
        	}
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    
    /**
     * (validate呼値)<BR>
     * （validateTickValue）<BR>
     * 呼値チェックを行う。<BR>
     * <BR>
     * １）　@刻み値取得 <BR>
     * 　@外株呼値テーブルを以下の条件で読み、該当行.刻み値を取得する。<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@外株呼値テーブル.市場コード = 市場コード And<BR>
     * 　@外株呼値テーブル.下限値　@< 注文単価 And<BR>
     * 　@外株呼値テーブル.上限値　@>= 注文単価<BR>
     * <BR>
     * ２）　@呼値チェック<BR>
     * 　@注文単価が刻み値で割り切れる値かをチェックする。 <BR>
     * <BR>
     * 　@（注文単価／刻み値）が整数でない場合、<BR> 
     * 　@呼値エラーの例外をスローする。<BR> 
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00148<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード
     * @@param l_dblOrderPrice - (注文単価)<BR>
     * 注文単価
     * @@throws WEB3BaseException
     * @@roseuid 428074510112
     */
    public void validateTickValue(String l_strMarketCode, double l_dblOrderPrice) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateTickValue(String, double)";
        log.entering(STR_METHOD_NAME);
        
        QueryProcessor l_qp = null;
        List l_lisFeqTickValuesMst = null;
        try
        {            
            String l_strWhere = " market_code = ?  and low_price < ?  and cap_price >= ? ";
            Object[] l_bindValues = {
                l_strMarketCode,
                new Double(l_dblOrderPrice),
                new Double(l_dblOrderPrice)};
            
           
            l_qp = Processors.getDefaultProcessor();
            
            l_lisFeqTickValuesMst = l_qp.doFindAllQuery(
                FeqTickValuesMstRow.TYPE, 
                l_strWhere, 
                l_bindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        double l_dblTickValue = 0D;
        if (l_lisFeqTickValuesMst == null || l_lisFeqTickValuesMst.isEmpty())
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        if (l_lisFeqTickValuesMst.size() > 1)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80004.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        
        FeqTickValuesMstRow l_feqTickValuesMstRow = (FeqTickValuesMstRow) l_lisFeqTickValuesMst.get(0);
        l_dblTickValue = l_feqTickValuesMstRow.getTickValue();
        
        if(!WEB3FeqOrderUtility.isRemainderZero(l_dblOrderPrice, l_dblTickValue))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00148.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00148,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate値幅)<BR>
     * （validatePriceRange）<BR>
     * <BR>
     * ***（終値評価項目追加対応案件により、終値が設定されてしまうため）<BR>
     * 　@　@　@　@以下の内容は全てコメントアウトとする***<BR>
     * <BR>
     * １）　@終値取得<BR>
     * 　@外国株式取引銘柄.get終値()にて終値を取得する。<BR>
     * 　@終値が取得できなかった場合（NaNまたは0）、<BR>
     * 　@以降の処理を行わずに終了する。<BR>
     * <BR>
     * ２）　@市場コード取得<BR>
     * 　@外国株式取引銘柄.getMarket().getMarketCode()<BR>
     * 　@にて市場コードを取得する。<BR>
     * <BR>
     * ３）　@値幅基準値計算<BR>
     * 　@this.calc値幅補正値()にて、刻み値で割り切れる値にした終値<BR>
     * 　@（値幅基準値）を取得する。<BR>
     * <BR>
     * 　@[calc値幅補正値()に指定する引数]<BR>
     * 　@市場コード：　@市場コード<BR>
     * 　@基準値：　@終値<BR>
     * <BR>
     * ４）　@制限値幅取得<BR>
     * 　@外株値幅テーブルを以下の条件で読み、取得行.値幅を取得する。<BR>
     * 　@（制限値幅）<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@外国株式値幅テーブル.市場コード = ２）で取得した市場コード And<BR>
     * 　@外国株式値幅テーブル.下限値　@<= ３）で取得した制限値幅 And<BR>
     * 　@外国株式値幅テーブル.上限値　@> ３）で取得した制限値幅<BR>
     * <BR>
     * ５）　@値幅上限計算<BR>
     * 　@this.calc値幅補正値()にて、刻み値で割り切れる値にした<BR>
     * 　@値幅上限を取得する。<BR>
     * 　@<BR>
     * 　@[calc値幅補正値()に指定する引数]<BR>
     * 　@市場コード：　@市場コード<BR>
     * 　@基準値：　@（値幅基準値　@＋　@制限値幅）の計算結果<BR>
     * <BR>
     * 　@※値幅基準値：　@３）の計算結果<BR>
     * 　@※制限値幅：　@４）の計算結果<BR>
     * <BR>
     * ６）　@値幅下限計算<BR>
     * 　@this.calc値幅補正値()にて、刻み値で割り切れる値にした<BR>
     * 　@値幅下限を取得する。<BR>
     * 　@<BR>
     * 　@[calc値幅補正値()に指定する引数]<BR>
     * 　@市場コード：　@市場コード<BR>
     * 　@基準値：　@（値幅基準値　@－　@制限値幅）の計算結果<BR>
     * <BR>
     * 　@※値幅基準値：　@３）の計算結果<BR>
     * 　@※制限値幅：　@４）の計算結果<BR>
     * <BR>
     * ７）　@値幅の範囲内かをチェックする<BR>
     * <BR>
     * 　@（値幅下限 <= 注文単価 <= 値幅上限）でない場合、<BR>
     * 　@値幅の範囲内でないと判定し、例外をスローする。 <BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00031<BR>
     * <BR>
     * 　@※値幅上限：　@５）の計算結果<BR>
     * 　@※値幅下限：　@６）の計算結果<BR>
     * @@param l_feqTradedProduct - (外国株式取引銘柄)<BR>
     * 外国株式取引銘柄オブジェクト
     * @@param l_dblOrderPrice - (注文単価)<BR>
     * 注文単価
     * @@throws WEB3BaseException
     * @@roseuid 428076850102
     */
    public void validatePriceRange(FeqTradedProduct l_feqTradedProduct,
        double l_dblOrderPrice) throws WEB3BaseException 
    {
//        final String STR_METHOD_NAME = 
//            " validatePriceRange(FeqTradedProduct, double)";
//        log.entering(STR_METHOD_NAME);
//        
//        if (l_feqTradedProduct == null)
//        {
//            log.debug(" パラメータ値がNULL ");
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                this.getClass().getName() + "." + STR_METHOD_NAME, 
//                "パラメータ値がNULL");
//        }
//        // １）　@終値取得 
//        //  外国株式取引銘柄.get終値()にて終値を取得する。 
//        //  終値が取得できなかった場合（NaNまたは0）、以降の処理を行わずに終了する。 
//        double l_dblLastClosingPrice = ((WEB3FeqTradedProduct)l_feqTradedProduct).getLastClosingPrice();
//        if (Double.isNaN(l_dblLastClosingPrice) || l_dblLastClosingPrice == 0)
//        {
//            log.debug("return");
//            return;
//        }
//        
//        // ２）　@市場コード取得 
//        //   外国株式取引銘柄.getMarket().getMarketCode()にて市場コードを取得する。
//        String l_strMarketCode = l_feqTradedProduct.getMarket().getMarketCode();
//        
//        // ３）　@値幅基準値計算 
//        //   this.calc値幅補正値()にて、刻み値で割り切れる値にした終値（値幅基準値）を取得する。 
//        BigDecimal l_bdCalcRangeRevisionValue =
//            new BigDecimal(String.valueOf(this.calcRangeRevisionValue(l_strMarketCode, l_dblLastClosingPrice)));
//        
//        //４）　@制限値幅取得 
//        // 外株値幅テーブルを以下の条件で読み、取得行.値幅を取得する。（制限値幅） 
//        //
//        // [条件]  
//        //  外国株式値幅テーブル.市場コード = ２）で取得した市場コード And 
//        //  外国株式値幅テーブル.下限値　@<= ３）で取得した制限値幅 And 
//        //  外国株式値幅テーブル.上限値　@> ３）で取得した制限値幅
//        QueryProcessor l_qp = null;
//        List l_lisFeqLimitPriceRangeMst = null;
//        try
//        {
//            String l_strWhere = " market_code = ?  and low_price <= ?  and cap_price > ? ";
//                       
//            Object[] l_bindValues = {
//                l_strMarketCode,
//                l_bdCalcRangeRevisionValue,
//                l_bdCalcRangeRevisionValue};
//            
//            l_qp = Processors.getDefaultProcessor();
//            
//            l_lisFeqLimitPriceRangeMst = 
//                l_qp.doFindAllQuery(FeqLimitPriceRangeMstRow.TYPE,l_strWhere, l_bindValues);
//        }
//        catch (DataFindException l_ex)
//        {
//            log.error("DBへのアクセスに失敗しました: ", l_ex);
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.error("DBへのアクセスに失敗しました: ", l_ex);
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.error("DBへのアクセスに失敗しました: ", l_ex);
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        if (l_lisFeqLimitPriceRangeMst == null || l_lisFeqLimitPriceRangeMst.isEmpty())
//        {
//            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + "." + STR_METHOD_NAME);   
//        }
//        if (l_lisFeqLimitPriceRangeMst.size() > 1)
//        {
//            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80004.getErrorMessage());
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
//                getClass().getName() + "." + STR_METHOD_NAME);   
//        }
//
//        FeqLimitPriceRangeMstRow l_feqLimitPriceRangeMstRow = 
//            (FeqLimitPriceRangeMstRow)l_lisFeqLimitPriceRangeMst.get(0);
//        BigDecimal l_bdRange =
//            new BigDecimal(String.valueOf(l_feqLimitPriceRangeMstRow.getRange()));
//        
//        //５）　@値幅上限計算 
//        // this.calc値幅補正値()にて、刻み値で割り切れる値にした値幅上限を取得する。 
//        //　@ 
//        // [calc値幅補正値()に指定する引数] 
//        // 市場コード：　@市場コード 
//        // 基準値：　@（値幅基準値　@＋　@制限値幅）の計算結果 
//        //
//        // ※値幅基準値：　@３）の計算結果 
//        // ※制限値幅：　@４）の計算結果
//
//        double l_dblCapPrice = 
//            this.calcRangeRevisionValue(l_strMarketCode, l_bdCalcRangeRevisionValue.add(l_bdRange).doubleValue());
//         
//        //６）　@値幅下限計算 
//        // this.calc値幅補正値()にて、刻み値で割り切れる値にした値幅下限を取得する。 
//        //　@ 
//        // [calc値幅補正値()に指定する引数] 
//        // 市場コード：　@市場コード 
//        // 基準値：　@（値幅基準値　@－　@制限値幅）の計算結果 
//        //
//        // ※値幅基準値：　@３）の計算結果 
//        // ※制限値幅：　@４）の計算結果 
//
//        double l_dblLowPrice = 
//            this.calcRangeRevisionValue(l_strMarketCode, l_bdCalcRangeRevisionValue.subtract(l_bdRange).doubleValue());
//
//        //７）　@値幅の範囲内かをチェックする 
//        //
//        // （値幅下限 <= 注文単価 <= 値幅上限）でない場合、値幅の範囲内でないと判定し、例外をスローする。  
//        //
//        //  ※値幅上限：　@５）の計算結果 
//        //  ※値幅下限：　@６）の計算結果
//        if (!(l_dblOrderPrice >= l_dblLowPrice && l_dblOrderPrice <= l_dblCapPrice))
//        {
//            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00031.getErrorMessage());
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.BUSINESS_ERROR_00031,
//                getClass().getName() + "." + STR_METHOD_NAME);               
//        } 
//        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc値幅補正値)<BR>
     * 値幅補正値（※刻み値で割り切れるように補正した単価値）を計算する。<BR>
     * <BR>
     * １）　@刻み値取得<BR>
     * 　@外株呼値テーブルを以下の条件で読み、該当行.刻み値，<BR>
     * 　@小数部桁数を取得する。<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@外株呼値テーブル.市場コード = 市場コード And<BR>
     * 　@外株呼値テーブル.下限値　@< 基準値 And<BR>
     * 　@外株呼値テーブル.上限値　@>= 基準値<BR>
     * <BR>
     * ２）　@基準値を補正して値幅基準値を計算する<BR>
     * <BR>
     * ○（（基準値／刻み値）の余り　@>=　@刻み値／2）の場合 <BR>
     * 　@以下の計算結果を返却する。 <BR>
     * <BR>
     * 　@[計算式] <BR>
     * 　@（基準値／刻み値(*1)）×刻み値 <BR>
     * 　@(*1)　@除算の計算結果の小数部桁数未満切り上げ。 <BR>
     * <BR>
     * ○上記以外の場合 <BR>
     * 　@以下の計算結果を返却する。 <BR>
     * <BR>
     * 　@[計算式] <BR>
     * 　@（基準値／刻み値(*2)）×刻み値 <BR>
     * 　@(*2)　@除算の計算結果を小数部桁数未満切り捨て。 <BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード
     * @@param l_dblBasicValue - (基準値)<BR>
     * 基準値
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 428079F20170
     */
    protected double calcRangeRevisionValue(String l_strMarketCode,
        double l_dblBasicValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " calcRangeRevisionValue(String, double)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@刻み値取得 
        // 外株呼値テーブルを以下の条件で読み、該当行.刻み値，小数部桁数を取得する。 
        //
        // [条件]  
        // 外株呼値テーブル.市場コード = 市場コード And 
        // 外株呼値テーブル.下限値　@< 基準値 And 
        // 外株呼値テーブル.上限値　@>= 基準値 
        QueryProcessor l_qp = null;
        List l_lisFeqTickValuesMst = null;
        try
        {
            String l_strWhere = " market_code = ?  and low_price < ?  and cap_price >= ? ";

            Object[] l_bindValues = {
                l_strMarketCode,
                new Double(l_dblBasicValue),
                new Double(l_dblBasicValue)};

            l_qp = Processors.getDefaultProcessor();
            
            l_lisFeqTickValuesMst = l_qp.doFindAllQuery(FeqTickValuesMstRow.TYPE, l_strWhere, l_bindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisFeqTickValuesMst == null || l_lisFeqTickValuesMst.isEmpty())
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        if (l_lisFeqTickValuesMst.size() > 1)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80004.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        
        int l_intScale = 0;

        FeqTickValuesMstRow l_feqTickValuesMstRow = (FeqTickValuesMstRow) l_lisFeqTickValuesMst.get(0);
        //刻み値
        BigDecimal l_bdTickValue =
            new BigDecimal(String.valueOf(l_feqTickValuesMstRow.getTickValue()));
        //小数部桁数
        l_intScale = l_feqTickValuesMstRow.getScale();
        
        //２）　@基準値を補正して値幅基準値を計算する 
        BigDecimal l_bdBasicValue = new BigDecimal(String.valueOf(l_dblBasicValue));
        double l_dblBasePrice = l_dblBasicValue; //基準値
        
        if (WEB3FeqOrderUtility.getRemainder(l_dblBasicValue ,l_bdTickValue.doubleValue())
            >= l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_EVEN).doubleValue())
        {
            //除算の計算結果の小数部桁数未満切り上げ
            l_dblBasePrice =
                l_bdBasicValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        else
        {
            //除算の計算結果を小数部桁数未満切り捨て
            l_dblBasePrice =
                l_bdBasicValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }
    
    /**
     * (validate外貨決済)<BR>
     * 顧客が指定した通貨で決済が可能かどうかをチェックする。<BR>
     * <BR>
     * １）外国株式口座が開設されているかどうかをチェックする。<BR>
     * <BR>
     *     this.validate外国株式口座開設()をコールする。<BR>
     * <BR>
     *     [引数]<BR>
     *     補助口座： 引数.補助口座<BR>
     * <BR>
     * ２）以下の条件で、外貨顧客勘定残高からレコードを取得する。<BR>
     * <BR>
     *    [検索条件]<BR>
     *    口座ID： 引数.補助口座.getAccountId()の戻り値<BR>
     *    補助口座ID： 引数.補助口座.getSubAccountId()の戻り値<BR>
     *    通貨コード： 引数.通貨コード<BR>
     * <BR>
     *    レコードが取得できなかった場合は、「外貨決済不可能」<BR>
     * 　@　@の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02095<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード
     * @@throws WEB3BaseException 
     * @@roseuid 428C5B4802A6
     */
    public void validateFcSettle(WEB3GentradeSubAccount l_subAccount,
        String l_strCurrencyCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateFcSettle(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）外国株式口座が開設されているかどうかをチェックする。 
        
        //this.validate外国株式口座開設()をコールする。
        this.validateFeqAccountEstablish(l_subAccount); 
        //２）以下の条件で、外貨顧客勘定残高からレコードを取得する。 

        //   [検索条件] 
        //   口座ID： 引数.補助口座.getAccountId()の戻り値 
        //   補助口座ID： 引数.補助口座.getSubAccountId()の戻り値 
        //   通貨コード： 引数.通貨コード
        
        QueryProcessor l_qp = null;
        List l_lisFCashBalance = null;
        try
        {
            String l_strWhere = " account_id = ?  and sub_account_id = ?  and currency_code = ? ";
            Object[] l_bindValues = {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                l_strCurrencyCode};

            l_qp = Processors.getDefaultProcessor();
            
            l_lisFCashBalance = l_qp.doFindAllQuery(FCashBalanceRow.TYPE, l_strWhere, l_bindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        //レコードが取得できなかった場合は、「外貨決済不可能」の例外をスローする
        if(l_lisFCashBalance == null || l_lisFCashBalance.isEmpty())
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02095.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02095,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate特定口座開設)<BR>
     * 特定口座のチェックを行う。<BR>
     * <BR>
     * １）引数.発注日の3営業日後を算出する。<BR>
     * <BR>
     * ２）顧客オブジェクトを取得する。<BR>
     * <BR>
     *    引数.補助口座.getMainAccount()をコールする。<BR>
     * <BR>
     * ３）特定口座の開設チェックを行う。<BR>
     * <BR>
     *    顧客.is特定口座開設()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    受渡日： １）で算出した日付<BR>
     *    補助口座： 引数.補助口座<BR>
     * <BR>
     *    戻り値 == false の場合、「特定口座未開設」の例外をスローする。<BR>
     *   class: WEB<BR>
     *   tag:  BUSINESS_ERROR_02096<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日
     * @@throws WEB3BaseException 
     * @@roseuid 428C6CBD00A2
     */
    public void validateSpecialAccountEstablish(WEB3GentradeSubAccount l_subAccount,
        Date l_datBizDate) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            " validateSpecialAccountEstablish(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）引数.発注日の3営業日後を算出する。 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strMarketCode = l_context.getMarketCode();
        
        WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));                
        Date l_datThreeDateLater = l_date.roll(3);
        //２）顧客オブジェクトを取得する。 
        //   引数.補助口座.getMainAccount()をコールする。 
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //３）特定口座の開設チェックを行う。 
        //
        //   顧客.is特定口座開設()をコールする。 
        boolean l_blnIsSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_datThreeDateLater, l_subAccount);
        if (!l_blnIsSpecialAccountEstablished)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02096.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02096,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate為替登録)<BR>
     * 為替レートが登録されているかどうかをチェックする。<BR>
     * <BR>
     * １）通貨オブジェクトを取得する。<BR>
     * <BR>
     *    外国株式銘柄.get通貨()をコールする。<BR>
     * <BR>
     * ２）為替レートを取得する。<BR>
     * <BR>
     *    通貨.get為替レート()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    is買付： 引数.is買付<BR>
     *    is約定計算： 引数.is約定<BR>
     *    入力為替レート： 0<BR>
     * <BR>
     * ３）取得したレート == 0 の場合、<BR>
     *    「為替レート未登録」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02097<BR>
     * @@param l_feqProduct - (外国株式銘柄)<BR>
     * 外国株式銘柄オブジェクト
     * 
     * @@param l_blnIsBuy - (is買付)<BR>
     * 買付注文かどうかの判定<BR>
     * <BR>
     * true： 買付<BR>
     * false： 売付<BR>
     * 
     * 
     * @@param l_blnIsExecuted - (is約定)<BR>
     * 約定かどうかの判定<BR>
     * <BR>
     * true： 約定<BR>
     * false： 注文<BR>
     * @@roseuid 428C6ED50054
     */
    public void validateRateRegistration(WEB3FeqProduct l_feqProduct,
        boolean l_blnIsBuy, boolean l_blnIsExecuted) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateRateRegistration(WEB3FeqProduct, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        //１）通貨オブジェクトを取得する。 
        //   外国株式銘柄.get通貨()をコールする。 
        WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();
        
        //２）為替レートを取得する。 
        //   通貨.get為替レート()をコールする。 
        double l_dlbFxRate = l_genCurrency.getExchangeRate(l_blnIsBuy, l_blnIsExecuted, 0);

        //３）取得したレート  0 の場合、 
        //   「為替レート未登録」の例外をスローする。
        if (l_dlbFxRate == 0)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02097.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02097,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate約定日)<BR>
     * 約定日のチェックを行う。<BR>
     * <BR>
     * １）　@営業日チェック<BR>
     * 　@約定日が営業日でない場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02149<BR>
     * <BR>
     * ２）　@既約定日チェック<BR>
     * 　@注文単位.getExecutions()にて、関連する約定オブジェクトを取得する。<BR>
     * 　@約定[0].getExecutionTimestamp()の日付部分が、<BR>
     * 　@引数の約定日と一致しない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02098<BR>
     * 
     * 　@
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@param l_datExecutionDate - (約定日)<BR>
     * 約定日
     * @@throws WEB3BaseException 
     * @@roseuid 4292A1270227
     */
    public void validateExecutionDate(WEB3FeqOrderUnit l_orderUnit,
        Date l_datExecutionDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecutionDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        if(l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）　@営業日チェック 
        //  約定日が営業日でない場合、例外をスローする。
        boolean l_blnIsFeqBizDate = WEB3FeqDateUtility.isFeqBizDate(new Timestamp(l_datExecutionDate.getTime()));
        if (!l_blnIsFeqBizDate)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02149.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        //２）　@既約定日チェック 
        //  注文単位.getExecutions()にて、関連する約定オブジェクトを取得する。 
        //  約定[0].getExecutionTimestamp()の日付部分が、引数の約定日と一致しない場合、例外をスローする。
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        if (l_orderExecutions == null || l_orderExecutions.length == 0)
        {
            return;
        }
        int l_intFlay = WEB3DateUtility.compareToDay(l_orderExecutions[0].getExecutionTimestamp(), l_datExecutionDate);
        if (l_intFlay != 0)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02098.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02098,
                this.getClass().getName()  + "." + STR_METHOD_NAME); 
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate現地受渡日)<BR>
     * 現地受渡日をチェックする。<BR>
     * <BR>
     * １）　@既約定現地受渡日取得<BR>
     * 　@注文単位.getExecutions()にて、既約定を取得する。<BR>
     * <BR>
     * ２）　@現地受渡日取得<BR>
     * 　@取得した各約定の現地受渡日と引数の現地受渡日を比較し、<BR>
     * 　@一つでも不一致があれば例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02099<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@param l_datFDeliveryDate - (現地受渡日)<BR>
     * 現地受渡日
     * @@throws WEB3BaseException 
     * @@roseuid 42B6678C029F
     */
    public void validateFDeliveryDate(WEB3FeqOrderUnit l_orderUnit,
        Date l_datFDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateFDeliveryDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）　@既約定現地受渡日取得 
        //  注文単位.getExecutions()にて、既約定を取得する。
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
         
        //２）　@現地受渡日取得 
        //  取得した各約定の現地受渡日と引数の現地受渡日を比較し、一つでも不一致があれば例外をスローする。 
        for (int i = 0; i < l_orderExecutions.length; i++)
        {
            int l_intFlay = WEB3DateUtility.compareToDay(l_orderExecutions[i].getDeliveryDate(), l_datFDeliveryDate);
            if (l_intFlay != 0)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02099.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02099,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate約定数量)<BR>
     * 約定数量をチェックする。<BR>
     * <BR>
     * １）　@既約定数量取得<BR>
     * 　@注文単位.getExecutedQuantity()にて、既約定数量を取得する。<BR>
     * <BR>
     * ２）　@注文数量取得<BR>
     * 　@注文単位.getConfirmedQuantity()にて、市場から確認済の数量を取得する。<BR>
     * <BR>
     * ３）　@数量比較<BR>
     * 　@約定数量が注文数量を超過している場合（既約定数量＋約定数量 > <BR>
     * 市場から確認済の数量）、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00300<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@param l_dblExecutedQuantity - (約定数量)<BR>
     * 約定数量（今回）
     * @@roseuid 4292A13000C0
     */
    public void validateExecutedQuantity(WEB3FeqOrderUnit l_orderUnit,
        double l_dblExecutedQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecutedQuantity(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）　@既約定数量取得 
        //  注文単位.getExecutedQuantity()にて、既約定数量を取得する。 
        double l_dblExeQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExeQuantity))
        {
            l_dblExeQuantity = 0.0D;
        }
        //２）　@注文数量取得 
        //  注文単位.getConfirmedQuantity()にて、市場から確認済の数量を取得する。 
        double l_dblQuantity = l_orderUnit.getConfirmedQuantity();
        //３）　@数量比較 
        //  約定数量が注文数量を超過している場合（既約定数量＋約定数量 > 市場から確認済の数量）、例外をスローする。 
        if (l_dblExeQuantity + l_dblExecutedQuantity > l_dblQuantity)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00300.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate約定単価)<BR>
     * 約定単価をチェックする。<BR>
     * <BR>
     * １）　@注文単価取得<BR>
     * 　@注文単位.getConfirmedLimitPrice()にて、注文単価を取得する。<BR>
     * 　@（注文単価 == 0）の場合、処理を終了する。（return;）<BR>
     * <BR>
     * ２）　@注文種別取得<BR>
     * 　@注文単位.getOrderType()にて、注文種別を取得する。<BR>
     * <BR>
     * ３）　@単価比較<BR>
     * 　@　@－買注文の場合（注文単位.getOrderType() == <BR>
     * 　@　@注文種別.701：外株買い）<BR>
     * 　@　@　@（注文単位.getConfirmedLimitPrice() < 約定単価）の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02100<BR>
     * 　@　@<BR>
     * 　@　@－売注文の場合（注文単位.getOrderType() == <BR>
     * 　@　@注文種別.702：外株売り）<BR>
     * 　@　@　@（注文単位.getConfirmedLimitPrice() > 約定単価）の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:    BUSINESS_ERROR_02101<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@param l_dblExecutedPrice - (約定単価)<BR>
     * 約定単価
     * @@throws WEB3BaseException 
     * @@roseuid 42C39E5C00EF
     */
    public void validateExecutedPrice(WEB3FeqOrderUnit l_orderUnit,
        double l_dblExecutedPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecutedPrice(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        //１）　@注文単価取得 
        // 注文単位.getConfirmedLimitPrice()にて、注文数量を取得する。 
        //（注文単価 == 0）の場合、処理を終了する。（return;） 
        double l_dblLimitPrice = l_orderUnit.getConfirmedPrice();
        if (l_dblLimitPrice == 0)
        {
            log.debug("return");
            return;
        }
         
        //２）　@注文種別取得 
        // 注文単位.getOrderCateg()にて、注文種別を取得する。 
        //３）　@単価比較 
        // 買注文の場合（注文単位.getOrderCateg() == 注文種別.701：外株買い） 
        //（注文単位.getConfirmedLimitPrice() < 約定単価）の場合、例外をスローする。
        OrderTypeEnum l_orderTypeEnum = l_orderUnit.getOrderType(); 
        if (OrderTypeEnum.FEQ_BUY.equals(l_orderTypeEnum) && l_dblLimitPrice < l_dblExecutedPrice)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02100.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02100,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        //－売注文の場合（注文単位.getOrderCateg() == 注文種別.702：外株売り） 
        //（注文単位.getConfirmedLimitPrice() > 約定単価）の場合、例外をスローする。 
        else if(OrderTypeEnum.FEQ_SELL.equals(l_orderTypeEnum) && l_dblLimitPrice > l_dblExecutedPrice)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02101.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02101,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文訂正可能状態)<BR>
     * 訂正が可能な注文状態であるかどうかをチェックする。<BR>
     * <BR>
     * this.validate注文取消可能状態(パラメータ.注文ID)に委譲(delegate)する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@throws WEB3BaseException 
     * @@roseuid 4295FBF40109
     */
    public void validateOrderChangePossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateOrderChangePossibleStatus(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate注文取消可能状態(パラメータ.注文ID)に委譲(delegate)する。 
        this.validateOrderCancelPossibleStatus(l_lngOrderId);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文取消可能状態)<BR>
     * 取消が可能な注文状態であるかどうかをチェックする。<BR>
     * <BR>
     * １）注文単位オブジェクトの取得<BR>
     * <BR>
     *    外国株式注文マネージャ.getOrderUnits()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    注文ID： 引数.注文ID<BR>
     * <BR>
     *    取得した配列の最初の要素を取得する。<BR>
     * <BR>
     * ２）注文有効状態のチェック<BR>
     * <BR>
     *    注文単位.注文有効状態 != ”OPEN”<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00858<BR>
     * <BR>
     * ３）注文状態、メール送信状況のチェック<BR>
     * <BR>
     * 市場開局／閉局によって以下の通りチェックを行う。<BR>
     * <BR>
     * ３－１）市場開局時間帯（取引時間管理.<BR>
     * 　@　@is市場開局時間帯( )== true）の場合<BR>
     * <BR>
     *    注文状態が以下のいずれかに該当する場合は取消不可とし、<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00155<BR>
     * <BR>
     *       ACCEPTED(*)<BR>
     *       CANCEL_ACCEPTED<BR>
     *       CANCELLING<BR>
     *       MODIFY_ACCEPTED<BR>
     *       MODIFYING<BR>
     *       MODIFIEDかつ市場から確認済の数量=null<BR>
     *       ORDERING<BR>
     * <BR>
     *       (*)注文単位.発注条件 == "逆指値"の場合は、取消可能とする。<BR>
     * <BR>
     * ３－２）市場閉局時間帯（取引時間管理.<BR>
     * 　@is市場開局時間帯( ) == false）の場合<BR>
     * <BR>
     *    注文状態が以下のいずれかに該当する場合は取消不可とし、<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00155<BR>
     * <BR>
     *       CANCEL_ACCEPTED<BR>
     *       CANCELLING<BR>
     *       MODIFY_ACCEPTED<BR>
     *       MODIFYING<BR>
     *       ORDERING<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@throws WEB3BaseException 
     * @@roseuid 42A7A8C50285
     */
    public void validateOrderCancelPossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateOrderCancelPossibleStatus(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);
        
        //１）注文単位オブジェクトの取得 
        //
        //   外国株式注文マネージャ.getOrderUnits()をコールする。 
        //
        //   [引数] 
        //   注文ID： 引数.注文ID 
        //
        //   取得した配列の最初の要素を取得する。 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        OrderUnit[] l_orderUnits = l_feqOrderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        boolean l_confirmedPrice = l_orderUnit.isConfirmedPriceMarketOrder();
                        
        //２）注文有効状態のチェック 
        //
        //   注文単位.注文有効状態 != ”OPEN” 
        //
        //   の場合、例外をスローする。 
        if (!(OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00858.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00858,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        //３）注文状態、メール送信状況のチェック 
        //市場開局／閉局によって以下の通りチェックを行う。 
        
        //３－１）市場開局時間帯（取引時間管理.isトリガ発行( ) == true）の場合 
        //   注文状態が以下のいずれかに該当する場合は取消不可とし、例外をスローする。 
        //
        //      ACCEPTED(*) 
        //      CANCEL_ACCEPTED 
        //      CANCELLING 
        //      MODIFY_ACCEPTED 
        //      MODIFYING 
        //      ORDERING 
        //
        //      (*)注文単位.発注条件 == "逆指値"の場合は、取消可能とする。
        OrderStatusEnum l_orderStatusEnum = l_orderUnit.getOrderStatus(); 
        if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null))
        {
            if (OrderStatusEnum.ACCEPTED.equals(l_orderStatusEnum) && 
                !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                ((FeqOrderUnitRow)l_orderUnit.getDataSourceObject()).getOrderConditionType())
                || (OrderStatusEnum.MODIFIED.equals(l_orderStatusEnum) && l_confirmedPrice)
                || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.CANCELLING.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum)
                || OrderStatusEnum.ORDERING.equals(l_orderStatusEnum))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02203.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02203,
                    this.getClass().getName() + "." + STR_METHOD_NAME);    
            }
        }  
        //３－２）市場閉局時間帯（取引時間管理.isトリガ発行( ) == false）の場合 
        //
        //   注文状態が以下のいずれかに該当する場合は取消不可とし、例外をスローする。 
        //
        //      CANCEL_ACCEPTED 
        //      CANCELLING 
        //      MODIFY_ACCEPTED 
        //      MODIFYING 
        //      ORDERING 
        else
        {
            if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.CANCELLING.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum)
                || OrderStatusEnum.ORDERING.equals(l_orderStatusEnum))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02203.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02203,
                    this.getClass().getName() + "." + STR_METHOD_NAME);    
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate市場)<BR>
     * 市場のチェックを行う。<BR>
     * <BR>
     * 市場.取引停止 == ”停止中”の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01747<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト
     * @@throws WEB3BaseException 
     * @@roseuid 429608960241
     */
    public void validateMarket(WEB3GentradeMarket l_market) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateMarket(WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);
        
        if(l_market == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //市場.取引停止 == ”停止中”の場合、例外をスローする。 
        MarketRow l_row = (MarketRow) l_market.getDataSourceObject();
        
        if (WEB3SuspensionDef.SUSPENSION.equals(l_row.getSuspension()))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01747.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01747,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate訂正内容)<BR>
     * 訂正入力値が妥当であるかをチェックする。<BR>
     * <BR>
     * １）数量のチェック<BR>
     * <BR>
     *    isChange数量()をコールし、<BR>
     *    数量に訂正が入ったかを判定、訂正数量の妥当性をチェックする。<BR>
     * <BR>
     *    [isChange数量()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正数量： 訂正数量<BR>
     * <BR>
     * ２）注文単価の判定<BR>
     * <BR>
     *    isChange単価()をコールし、<BR>
     *    注文単価に訂正が入ったかを判定する。<BR>
     * <BR>
     *    [isChange単価()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正単価： 訂正単価 <BR>
     * <BR>
     * 3）執行条件の判定<BR>
     * <BR>
     *    isChange執行条件()をコールし、<BR>
     *    執行条件に訂正が入ったかを判定する。<BR>
     * <BR>
     *    [isChange執行条件()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正執行条件： 訂正執行条件<BR>
     * <BR
     * ４）同時訂正チェック（同時訂正不可の市場のみ）<BR>
     *   ４－１）<BR>
     * 注文単位.市場ＩＤに該当する市場オブジェクトを取得する。<BR> 
     *   ４－2）<BR>
     * 取得した市場.同時訂正可能区分==”複数項目同時訂正不可” and<BR>
     * （（ １）の結果 == true and ２）の結果 == true ） <BR>
     * or （ １）の結果 == true and ３）の結果 == true ））<BR>
     * の場合、例外をスローする。    <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00687<BR>    
>
     * ５）発注条件が訂正されていないことのチェック<BR>
     * <BR>
     *    isChange発注条件()をコールし、発注条件に訂正が入ったかを判定する。<BR>
     * <BR>
     *    [isChange発注条件()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正発注条件： 発注条件<BR>
     * <BR>
     * ６）出来るまで注文が訂正されていないことのチェック<BR>
     * <BR>
     *    ＜訂正元注文について＞ <BR>
     *     引数.注文単位.初回注文の注文単位ID == null の場合、当日限り <BR>
     *     引数.注文単位.初回注文の注文単位ID != null の場合、出来るまで注文<BR>
     *    ＜訂正後の注文について＞<BR>
     *     引数.注文期限区分 == ”当日限り” の場合、当日限り <BR>
     *     引数.注文期限区分 == ”出来るまで注文” の場合、出来るまで注文<BR> 
     *    訂正元と訂正後の注文期限区分（出来るまで注文かどうか）<BR>
     *    が一致しない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02102<BR>
     * <BR>
     * ７）（発注済みの）逆指値注文が訂正されていないことのチェック<BR>
     * <BR>
     *    isChange逆指値注文()をコールし、<BR>
     *    逆指値注文に訂正が入ったかを判定する。<BR>
     * <BR>
     *    [isChange逆指値注文()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正発注条件： 発注条件<BR>
     *    訂正発注条件演算子： 訂正発注条件演算子<BR>
     *    訂正発注条件単価： 訂正発注条件単価<BR>
     * <BR>
     * ８）W指値注文が訂正されていないことのチェック<BR>
     * <BR>
     *    isChangeW指値注文()をコールし、<BR>
     *    W指値注文に訂正が入ったかを判定する。<BR>
     * <BR>
     *    [isChangeW指値注文()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正発注条件： 訂正発注条件<BR>
     *    訂正発注条件演算子： 訂正発注条件演算子<BR>
     *    訂正発注条件単価： 訂正発注条件単価<BR>
     *    訂正（W指値）訂正指値： 訂正（W指値）訂正指値<BR>
     * <BR>
     * ９）注文有効期限が訂正されていないことのチェック<BR>
     * <BR>
     *    isChange注文有効期限()をコールし、 <BR>
     *    注文有効期限に訂正が入ったかを判定する。 <BR>
     * <BR>
     *    [isChange注文有効期限()に指定する引数]<BR>
     *    注文単位： 注文単位<BR>
     *    訂正注文有効期限： 訂正注文有効期限<BR>
     * <BR>
     * １０）訂正が入っているかのチェック<BR>
     * <BR>
     *    isChange数量()、isChange単価()、<BR>
     *    isChange執行条件()、isChange逆指値注文()、<BR>
     *    isChangeW指値注文()、isChange注文有効期限()<BR>
     * <BR>
     *    のすべてがfalseを返却した場合、<BR> 
     *    訂正元注文から何も訂正されていないと判断し、<BR>
     *    「訂正入力なし」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02103<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_dblChangeQuantity - (訂正数量)<BR>
     * 訂正数量
     * 
     * @@param l_dblChangePrice - (訂正単価)<BR>
     * 訂正単価
     * 
     * @@param l_strChangeExecCondType - (訂正執行条件)<BR>
     * 訂正執行条件
     * 
     * @@param l_strChangeOrderExpirationDiv - ( 訂正注文期限区分)<BR>
     * 訂正注文期限区分 
     * 
     * @@param l_datChangeExpirationDate - (訂正注文有効期限)<BR>
     * 訂正注文有効期限
     * 
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件
     * 
     * @@param l_strOrderCondOperator - (訂正発注条件演算子)<BR>
     * 訂正発注条件演算子
     * 
     * @@param l_dblChangeOrderConditionTypePrice - (訂正発注条件単価)<BR>
     * 訂正発注条件単価
     * 
     * @@param l_dblChangeWLimitPrice - (訂正（W指値）訂正指値)<BR>
     * 訂正（W指値）訂正指値
     * @@throws WEB3BaseException 
     * @@roseuid 42974965002F
     */
    public void validateChangeSpec(FeqOrderUnit l_orderUnit,
        double l_dblChangeQuantity, 
        double l_dblChangePrice, 
        String l_strChangeExecCondType,
        String l_strChangeOrderExpirationDiv, 
        Date l_datChangeExpirationDate, 
        String l_strOrderConditionType, 
        String l_strOrderCondOperator, 
        double l_dblChangeOrderConditionTypePrice, 
        double l_dblChangeWLimitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangeSpec()";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）数量のチェック 
        //
        //   isChange数量()をコールし、 
        //   数量に訂正が入ったかを判定、訂正数量の妥当性をチェックする。 
        //
        //   [isChange数量()に指定する引数] 
        //   注文単位： 注文単位 
        //   訂正数量： 訂正数量 
        boolean l_blnIsQuantity = this.isChangeQuantity(l_orderUnit, l_dblChangeQuantity);
        //２）注文単価の判定 
        //
        //   isChange単価()をコールし、 
        //   注文単価に訂正が入ったかを判定する。 
        //
        //   [isChange単価()に指定する引数] 
        //   注文単位： 注文単位 
        //    訂正単価： 訂正単価 
        boolean l_blnIsPrice = this.isChangePrice(l_orderUnit, l_dblChangePrice);
        //３）執行条件の判定 
        //
        //   isChange執行条件()をコールし、 
        //   執行条件に訂正が入ったかを判定する。 
        //
        //   [isChange執行条件()に指定する引数] 
        //   注文単位： 注文単位 
        //   訂正執行条件： 訂正執行条件 
        boolean l_blnIsConditionType =
            this.isChangeExecutionConditionType(l_orderUnit, l_strChangeExecCondType);
        //４）同時訂正チェック（同時訂正不可の市場のみ） 
        //
        //４－１） 
        //   注文単位.市場ＩＤに該当する市場オブジェクトを取得する。         
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMarket l_market = null;
        FeqOrderUnitRow l_feqOrderUnitRow = null;
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();  
        
        l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        try
        {

            l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_feqOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }                                    

        //４－２） 
        //   取得した市場.同時訂正可能区分==”複数項目同時訂正不可” and 
        //   （（ １）の結果 == true and ２）の結果 == true ） or （ １）の結果 == true and ３）の結果 == true ）） 
        //
        //   の場合、例外をスローする。 
        MarketRow l_marketRow = (MarketRow) l_market.getDataSourceObject();
        if ((WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(l_marketRow.getChangeableType())) && 
                ((l_blnIsQuantity && l_blnIsPrice) || (l_blnIsQuantity && l_blnIsConditionType)))

        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00687.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //５）発注条件が訂正されていないことのチェック 
        //
        //   isChange発注条件()をコールし、発注条件に訂正が入ったかを判定する。 
        //
        //   [isChange発注条件()に指定する引数] 
        //   注文単位： 注文単位 
        //   訂正発注条件： 発注条件 
        this.isChangeOrderConditionType(l_orderUnit, l_strOrderConditionType);
        //６）注文期限区分が訂正されていないことのチェック 
        //
        //   ＜訂正元注文について＞ 
        //      引数.注文単位.初回注文の注文単位ID == null の場合、当日限り 
        //      引数.注文単位.初回注文の注文単位ID != null の場合、出来るまで注文 
        //
        //   ＜訂正後の注文について＞ 
        //      引数.注文期限区分 == ”当日限り” の場合、当日限り 
        //      引数.注文期限区分 == ”出来るまで注文” の場合、出来るまで注文 
        //
        //   訂正元と訂正後の注文期限区分（出来るまで注文かどうか）が一致しない場合、例外をスローする。 
        if((l_feqOrderUnitRow.getFirstOrderUnitIdIsNull() && WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strChangeOrderExpirationDiv))
            || (!l_feqOrderUnitRow.getFirstOrderUnitIdIsNull() && WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strChangeOrderExpirationDiv)))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02102.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02102,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        //７）（発注済みの）逆指値注文が訂正されていないことのチェック 
        //
        //   isChange逆指値注文()をコールし、 
        //   逆指値注文に訂正が入ったかを判定する。 
        //
        //   [isChange逆指値注文()に指定する引数] 
        //   注文単位： 注文単位 
        //   訂正発注条件： 発注条件 
        //   訂正発注条件演算子： 訂正発注条件演算子 
        //   訂正発注条件単価： 訂正発注条件単価 
        boolean l_blnIsStopCond =
            this.isChangeStopCond(l_orderUnit, l_strOrderConditionType, l_strOrderCondOperator, l_dblChangeOrderConditionTypePrice);            
        //８）W指値注文が訂正されていないことのチェック 
        //
        //   isChangeW指値注文()をコールし、 
        //   W指値注文に訂正が入ったかを判定する。 
        //
        //   [isChangeW指値注文()に指定する引数] 
        //   注文単位： 注文単位 
        //   訂正発注条件： 訂正発注条件 
        //   訂正発注条件演算子： 訂正発注条件演算子 
        //   訂正発注条件単価： 訂正発注条件単価 
        //   訂正（W指値）訂正指値： 訂正（W指値）訂正指値 
        boolean l_blnIsWLimitPriceCond = this.isChangeWLimitPriceCond(l_orderUnit, 
            l_strOrderConditionType, 
            l_strOrderCondOperator, 
            l_dblChangeOrderConditionTypePrice,
            l_dblChangeWLimitPrice);
        //９）注文有効期限が訂正されていないことのチェック 
        //
        //   isChange注文有効期限()をコールし、  
        //   注文有効期限に訂正が入ったかを判定する。  
        //
        //   [isChange注文有効期限()に指定する引数] 
        //   注文単位： 注文単位 
        //   訂正注文有効期限： 訂正注文有効期限 
        boolean l_blnIsOrderExpirationDate =
            this.isChangeOrderExpirationDate(l_orderUnit, l_datChangeExpirationDate);
        //１０）訂正が入っているかのチェック 
        //
        //   isChange数量()、isChange単価()、 
        //   isChange執行条件()、isChange逆指値注文()、 
        //   isChangeW指値注文()、isChange注文有効期限() 
        //
        //   のすべてがfalseを返却した場合、  
        //   訂正元注文から何も訂正されていないと判断し、 
        //   「訂正入力なし」の例外をスローする。 
        if (!l_blnIsQuantity && !l_blnIsPrice && !l_blnIsConditionType &&
            !l_blnIsStopCond  && !l_blnIsWLimitPriceCond && !l_blnIsOrderExpirationDate)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02103.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02103,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (isChange数量)<BR>
     * 数量に訂正が入っているか判定する。<BR>
     * <BR>
     * １）注文単位.getQuantity == 訂正数量 の場合<BR>
     * <BR>
     *    falseを返却する。<BR>
     * <BR>
     * ２）以外の場合<BR>
     * <BR>
     *    以下のチェックを行い、trueを返却する。<BR>
     * <BR>
     *    －（約定数量(*1)　@＞　@訂正数量）の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00142<BR>
     * <BR>
     *    －（注文数量(*2)　@＜　@訂正数量）の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00143<BR>
     * <BR>
     * <BR>
     *    (*1) 約定数量<BR>
     *    注文単位.getExecutedQuantity()<BR>
     * <BR>
     *    (*2) 注文数量<BR>
     *    注文単位.getQuantity()<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_dblChangeQuantity - (訂正数量)<BR>
     * 訂正数量
     * 
     * @@return Boolean
     * @@roseuid 42974DA900AC
     */
    protected boolean isChangeQuantity(FeqOrderUnit l_orderUnit,
        double l_dblChangeQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isChangeQuantity(FeqOrderUnit l_orderUnit, double l_dblChangeQuantity)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //１）注文単位.getQuantity == 訂正数量 の場合 
        //   falseを返却する。
        double l_dblQuantity = l_orderUnit.getQuantity(); 
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }
        if (l_dblQuantity == l_dblChangeQuantity)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //２）以外の場合 
        //
        //   以下のチェックを行い、trueを返却する。 
        //
        //   －（約定数量(*1)　@＞　@訂正数量）の場合、例外をスローする。 
        //   －（注文数量(*2)　@＜　@訂正数量）の場合、例外をスローする。 
        //
        //   (*1) 約定数量 
        //   注文単位.getExecutedQuantity()     
        //
        //   (*2) 注文数量 
        //   注文単位.getQuantity() 
        else 
        {
            if (l_dblExecutedQuantity > l_dblChangeQuantity)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02104.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if(l_dblQuantity < l_dblChangeQuantity)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02105.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00143,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
   
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }        
    }
    
    /**
     * (isChange単価)<BR>
     * 単価に訂正が入っているか判定する。<BR>
     * <BR>
     * 注文単位.getLimitPrice == 訂正単価 の場合、falseを返却する。<BR>
     * 以外、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_dblChangePrice - (訂正単価)<BR>
     * 訂正単価
     * 
     * @@return Boolean
     * @@roseuid 42974EA702DE
     */
    protected boolean isChangePrice(FeqOrderUnit l_orderUnit, double l_dblChangePrice) 
    {
        final String STR_METHOD_NAME = 
            " isChangePrice(FeqOrderUnit l_orderUnit, double l_dblChangePrice)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        //注文単位.getLimitPrice == 訂正単価 の場合、falseを返却する。 
        //以外、trueを返却する。
        double l_dblLimitPrice = l_orderUnit.getLimitPrice();
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0;
        }
        if (l_dblLimitPrice == l_dblChangePrice)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }   
    }
    
    /**
     * (isChange執行条件)<BR>
     * 執行条件に訂正が入っているか判定する。<BR>
     * <BR>
     * 注文単位.執行条件 == 訂正執行条件 の場合、falseを返却する。<BR>
     * 以外、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_strChangeExecCondType - (訂正執行条件)<BR>
     * 訂正執行条件
     * 
     * @@return Boolean
     * @@throws WEB3BaseException 
     * @@roseuid 42974ED5038A
     */
    protected boolean isChangeExecutionConditionType(FeqOrderUnit l_orderUnit,
        String l_strChangeExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isChangeExecutionConditionType(FeqOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //注文単位.執行条件 == 訂正執行条件 の場合、falseを返却する。 
        //以外、trueを返却する。
        
        FeqExecutionConditionType l_execCondType 
                        = l_orderUnit.getExecutionConditionType();
        String l_strExecCondType = new Integer(l_execCondType.intValue()).toString();
        
        if (l_strExecCondType.equals(l_strChangeExecCondType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        } 
        
    }
    
    /**
     * (isChange発注条件)<BR>
     * 発注条件に訂正が入っているか判定する。<BR>
     * <BR>
     * 注文単位.発注条件 == 訂正発注条件 の場合、falseを返却する。<BR>
     * 以外、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02106<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_strChangeOrderCondType - (訂正発注条件)<BR>
     * 訂正発注条件
     * 
     * @@return boolean
     * @@throws WEB3BaseException 
     * @@roseuid 42974F47035B
     */
    protected boolean isChangeOrderConditionType(FeqOrderUnit l_orderUnit,
        String l_strChangeOrderCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isChangeOrderConditionType(FeqOrderUnit l_orderUnit, String l_strChangeOrderCondType)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //注文単位.発注条件 == 訂正発注条件 の場合、falseを返却する。 
        //以外、例外をスローする。 
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();

        if (l_strChangeOrderCondType.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;            
        }
        else
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02106.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02106,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
               
    }
    
    /**
     * (isChange逆指値条件)<BR>
     * 逆指値の条件（発注条件演算子、発注条件単価）<BR>
     * 　@に訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）引数.訂正発注条件 != ”逆指値”の場合は、<BR>
     * 　@以降の処理は行わずtrueを返す。<BR>
     * <BR>
     * ２）引数.訂正発注条件 == ”逆指値”の場合<BR>
     * <BR>
     *    発注済みの逆指値注文（注文単位.<BR>
     * 　@市場から確認済みの数量 != Double.NaN）の場合、 <BR>
     * <BR>
     *    注文単位.発注条件 == 訂正発注条件 and<BR>
     *    注文単位.発注条件演算子 == 訂正発注条件演算子 and<BR>
     *    注文単位.逆指値基準値 == 訂正発注条件単価<BR>
     * <BR>
     *    上記条件にあてはまらない場合は例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02107<BR>
     * <BR>
     * ３）<BR>
     *    注文単位.発注条件 == 訂正発注条件 and<BR>
     *    注文単位.発注条件演算子 == 訂正発注条件演算子 and<BR>
     *    注文単位.逆指値基準値 == 訂正発注条件単価<BR>
     * <BR>
     *    の場合、falseを返却する。<BR>
     *    以外は、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_strChangeOrderCondType - (訂正発注条件)<BR>
     * 訂正発注条件
     * 
     * @@param l_strChangeOrderCondOperator - (訂正発注条件演算子)<BR>
     * 訂正発注条件演算子
     * 
     * @@param l_dblChangeOrderCondPrice - (訂正発注条件単価)<BR>
     * 訂正発注条件単価
     * 
     * @@return Boolean
     * @@roseuid 42974FA401D5
     */
    protected boolean isChangeStopCond(FeqOrderUnit l_orderUnit,
        String l_strChangeOrderCondType,
        String l_strChangeOrderCondOperator,
        double l_dblChangeOrderCondPrice) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = 
            " isChangeStopCond(FeqOrderUnit, String, String, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        //１）引数.訂正発注条件 != ”逆指値”の場合は、以降の処理は行わずtrueを返す。 
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strChangeOrderCondType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;   
        }
        //２）引数.訂正発注条件 == ”逆指値”の場合 
        //
        //   発注済みの逆指値注文（注文単位.市場から確認済みの数量 != Double.NaN）の場合、  
        //
        //   注文単位.発注条件 == 訂正発注条件 and 
        //   注文単位.発注条件演算子 == 訂正発注条件演算子 and 
        //   注文単位.逆指値基準値 == 訂正発注条件単価 
        //
        //   上記条件にあてはまらない場合は例外をスローする。
        else 
        {
            if (!Double.isNaN(l_orderUnit.getConfirmedQuantity()))
            {
                if (!(l_strChangeOrderCondType.equals(l_feqOrderUnitRow.getOrderConditionType()) &&
                    l_strChangeOrderCondOperator.equals(l_feqOrderUnitRow.getOrderCondOperator()) &&
                    l_feqOrderUnitRow.getStopOrderPrice() == l_dblChangeOrderCondPrice))
                {
                    log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02107.getErrorMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02107,
                        this.getClass().getName() + "." + STR_METHOD_NAME);   
                }
            }
        }
  
        //
        //３） 
        //   注文単位.発注条件 == 訂正発注条件 and 
        //   注文単位.発注条件演算子 == 訂正発注条件演算子 and 
        //   注文単位.逆指値基準値 == 訂正発注条件単価 
        //
        //   の場合、falseを返却する。 
        //   以外は、trueを返却する。 
        if (l_strChangeOrderCondType.equals(l_feqOrderUnitRow.getOrderConditionType()) &&
            l_strChangeOrderCondOperator.equals(l_feqOrderUnitRow.getOrderCondOperator()) &&
              l_feqOrderUnitRow.getStopOrderPrice() == l_dblChangeOrderCondPrice)
        {
            log.exiting(STR_METHOD_NAME);
            return false;                
        }
        else 
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }     
    }
    
    /**
     * (isChangeW指値条件)<BR>
     * W指値の条件（発注条件演算子、発注条件単価、<BR>
     * 　@（W指値）訂正指値）に訂正が入ったかをチェックする。<BR>
     * <BR>
     * １）引数.訂正発注条件 != ”W指値”の場合は、<BR>
     * 　@以降の処理は行わずtrueを返す。<BR>
     * <BR>
     * ２）引数.訂正発注条件 == ”W指値”の場合<BR>
     * <BR>
     *    注文単位.発注条件 == 訂正発注条件 and<BR>
     *    注文単位.発注条件演算子 == 訂正発注条件演算子 and<BR>
     *    注文単位.逆指値基準値 == 訂正発注条件単価<BR>
     *    注文単位.（W指値）訂正指値 == 訂正（W指値）訂正指値<BR>
     * <BR>
     *    の場合、falseを返却する。<BR>
     *    以外は、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_strChangeBizCondType - (訂正発注条件)<BR>
     * 訂正発注条件
     * 
     * @@param l_strChangeBizCondOperation - (訂正発注条件演算子)<BR>
     * 訂正発注条件演算子
     * 
     * @@param l_dblChangeBizCondPrice - (訂正発注条件単価)<BR>
     * 訂正発注条件単価
     * 
     * @@param l_dblWLimitPrice - (訂正（W指値）訂正指値)<BR>
     * 訂正（W指値）訂正指値
     * 
     * @@return Boolean
     * @@roseuid 429752140252
     */
    protected boolean isChangeWLimitPriceCond(FeqOrderUnit l_orderUnit,
        String l_strChangeBizCondType,
        String l_strChangeBizCondOperation, 
        double l_dblChangeBizCondPrice, 
        double l_dblWLimitPrice) 
    {
        final String STR_METHOD_NAME = 
            " isChangeWLimitPriceCond(FeqOrderUnit, String, String, double, double)";
        log.entering(STR_METHOD_NAME);
       
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        //１）引数.訂正発注条件 != ”W指値”の場合は、以降の処理は行わずtrueを返す。 
        if (!(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strChangeBizCondType)))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //２）引数.訂正発注条件 == ”W指値”の場合 
        //
        //   注文単位.発注条件 == 訂正発注条件 and 
        //   注文単位.発注条件演算子 == 訂正発注条件演算子 and 
        //   注文単位.逆指値基準値 == 訂正発注条件単価 
        //   注文単位.（W指値）訂正指値 == 訂正（W指値）訂正指値 
        //
        //   の場合、falseを返却する。 
        //   以外は、trueを返却する。 
        else
        {
            if (l_strChangeBizCondType.equals(l_feqOrderUnitRow.getOrderConditionType()) &&
                    l_strChangeBizCondOperation.equals(l_feqOrderUnitRow.getOrderCondOperator()) &&
                    l_feqOrderUnitRow.getStopOrderPrice() == l_dblChangeBizCondPrice &&
                    l_feqOrderUnitRow.getWLimitPrice() == l_dblWLimitPrice)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
    }
    
    /**
     * (isChange注文有効期限)<BR>
     * 注文有効期限（注文失効日）に訂正が入っているか判定する。<BR>
     * <BR>
     * 注文単位.注文失効日付 == 訂正注文有効期限 の場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * 
     * @@param l_datChangeOrderExpirationDate - (訂正注文有効期限)<BR>
     * 訂正注文有効期限
     * 
     * @@return Boolean
     * @@roseuid 429752B40252
     */
    protected boolean isChangeOrderExpirationDate(FeqOrderUnit l_orderUnit, Date l_datChangeOrderExpirationDate) 
    {
        final String STR_METHOD_NAME = 
            " isChangeOrderExpirationDate(FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        //注文単位.注文失効日付 == 訂正注文有効期限 の場合、falseを返却する。 
        //以外、trueを返却する。
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        int l_intCompare = WEB3DateUtility.compareToDay(l_feqOrderUnitRow.getExpirationDate(), l_datChangeOrderExpirationDate);
        if (l_intCompare == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (validate顧客銘柄別取引停止)<BR>
     * 顧客銘柄別取引停止チェックを行う。<BR>
     * <BR>
     * １）引数の補助口座オブジェクトより、顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）顧客.is取引停止銘柄( )をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    銘柄ID： 引数.銘柄ID<BR>
     *    注文種別： 引数.注文種別<BR>
     * <BR>
     *    戻り値 == true の場合は「顧客は指定銘柄の該当取引停止中」<BR>
     * 　@の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01357<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * 
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別
     * @@throws WEB3BaseException 
     * @@roseuid 429E863A0175
     */
    public void validateAccountProductTradedStop(SubAccount l_subAccount, 
        long l_lngProductId,
        OrderTypeEnum l_orderType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateAccountProductTradedStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //１）引数の補助口座オブジェクトより、顧客オブジェクトを取得する。 
        //
        //２）顧客.is取引停止銘柄( )をコールする。 
        //
        //   [引数] 
        //   銘柄ID： 引数.銘柄ID 
        //   注文種別： 引数.注文種別 
        //
        //   戻り値 == true の場合は「顧客は指定銘柄の該当取引停止中」の例外をスローする。
        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        boolean l_blnIsTradeStopProduct = l_account.isTradeStopProduct(l_lngProductId, l_orderType);
         
        if (l_blnIsTradeStopProduct)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01357.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01357,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取扱可能市場)<BR>
     * 会社部店の取扱可能市場かをチェックする。 <BR>
     * <BR>
     * １）（部店市場別.外株）取扱条件オブジェクトを生成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@部店コード：　@パラメータ.部店コード<BR>
     * 　@　@市場コード：　@パラメータ.市場コード<BR>
     * <BR>
     * ２）生成したオブジェクト.is取扱可能()メソッドをコールする。<BR>
     * <BR>
     * 　@[is取扱可能()に指定する引数]<BR>
     * 　@　@銘柄タイプ：　@ProductTypeEnum.外国株式<BR>
     * <BR>
     * ３）２）の戻り値 == falseの場合、<BR>
     * 　@「市場取扱不可」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02108<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード
     * @@throws WEB3BaseException 
     * @@roseuid 429EF02D00D7
     */
    public void validateHandlingPossibleMarket(String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strMarketCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateHandlingPossibleMarket(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）（部店市場別.外株）取扱条件オブジェクトを生成する。 
        //
        //[コンストラクタに指定する引数] 
        //証券会社コード：　@パラメータ.証券会社コード 
        //部店コード：　@パラメータ.部店コード 
        //市場コード：　@パラメータ.市場コード 
        WEB3GentradeFeqBranchMarketDealtCond l_cond =
            new WEB3GentradeFeqBranchMarketDealtCond(l_strInstitutionCode, l_strBranchCode, l_strMarketCode);
        
        //２）生成したオブジェクト.is取扱可能()メソッドをコールする。 
        //
        //[is取扱可能()に指定する引数] 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        boolean l_blnIsHandlingPossible = l_cond.isHandlingPossible(ProductTypeEnum.FOREIGN_EQUITY);
        
        //３）２）の戻り値 == falseの場合、 
        //「市場取扱不可」の例外をスローする。 
        if (!l_blnIsHandlingPossible)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02108.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02108,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc基準値)<BR>
     * 清算値の値が刻み値で割り切れる値になるように計算する。<BR>
     * <BR>
     * ○（（清算値／刻み値）の余り　@>=　@刻み値／2）の場合<BR>
     *    以下の計算結果を返却する。<BR>
     * <BR>
     *    [計算式]<BR>
     *    （清算値／刻み値(*1)）×刻み値<BR>
     *    (*1)除算の計算結果を切り上げ。<BR>
     * <BR>
     * ○上記以外の場合<BR>
     *    以下の計算結果を返却する。<BR>
     * <BR>
     *    [計算式]<BR>
     *    （清算値／刻み値(*2)）×刻み値<BR>
     *    (*2)除算の計算結果を切り捨て。<BR>
     * <BR>
     * @@param l_dblLiquidationPrice - (清算値)<BR>
     * 清算値
     * 
     * @@param l_dblTickValue - (刻み値)<BR>
     * 刻み値
     * 
     * @@return double
     * @@throws WEB3BaseException 
     * @@roseuid 42A8249001E3
     */
    public double calcBasePrice(double l_dblLiquidationPrice, double l_dblTickValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " calcBasePrice(double, double)";
        log.entering(STR_METHOD_NAME);
        
        //○（（清算値／刻み値）の余り　@>=　@刻み値／2）の場合 
        //   以下の計算結果を返却する。 
        //
        //   [計算式] 
        //   （清算値／刻み値(*1)）×刻み値 
        //   (*1)除算の計算結果を切り上げ。 
        BigDecimal l_bdLiquidationPrice = new BigDecimal(String.valueOf(l_dblLiquidationPrice));
        BigDecimal l_bdTickValue = new BigDecimal(String.valueOf(l_dblTickValue));
        double l_dblBasePrice = 0D; //基準値
        if (WEB3FeqOrderUtility.getRemainder(l_dblLiquidationPrice, l_dblTickValue)
            >= l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_EVEN).doubleValue())
        {
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        //○上記以外の場合 
        //   以下の計算結果を返却する。 
        //
        //   [計算式] 
        //   （清算値／刻み値(*2)）×刻み値 
        //   (*2)除算の計算結果を切り捨て。 

        else
        {
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }
    
    /**
     * (calc値幅上限)<BR>
     * 呼値単位の値幅上限値を計算する。<BR>
     * <BR>
     * １）値幅上限値計算<BR>
     * <BR>
     *    値幅上限値 = 引数.基準値＋引数.制限値幅<BR>
     * <BR>
     * ２）刻み値取得<BR>
     * <BR>
     *    外国株式プロダクトマネージャ.get刻み値()にて刻み値を取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    外国株式銘柄： 引数.外国株式銘柄<BR>
     *    単価： １）で算出した値幅上限値<BR>
     * <BR>
     * ３）呼値単位の値幅上限値計算<BR>
     * <BR>
     * ○（値幅上限値／刻み値）の余り　@>=　@刻み値／2）の場合<BR>
     * <BR>
     *    以下の計算結果を返却する。<BR>
     * <BR>
     *    [計算式]<BR>
     *    （値幅上限値／刻み値(*1)）×刻み値<BR>
     *    (*1)除算の計算結果の小数部桁数未満切り上げ<BR>
     * <BR>
     * ○上記以外の場合<BR>
     * <BR>
     *    以下の計算結果を返却する。<BR>
     * <BR>
     *    [計算式]<BR>
     *    （値幅上限値／刻み値(*2)）×刻み値<BR>
     *    (*2)除算の計算結果の小数部桁数未満切り捨て<BR>
     * @@param l_dblBasicValue - (基準値)<BR>
     * 基準値
     * 
     * @@param l_dblDeregulatedPriceRange - (制限値幅)<BR>
     * 制限値幅
     * 
     * @@param l_feqProduct - (外国株式銘柄)<BR>
     * 外国株式銘柄オブジェクト
     * 
     * @@return double
     * @@throws WEB3BaseException 
     * @@roseuid 42A8252802CD
     */
    public double calcRangeCap(double l_dblBasicValue,
        double l_dblDeregulatedPriceRange, WEB3FeqProduct l_feqProduct)throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " calcRangeCap(double, double, WEB3FeqProduct)";
        log.entering(STR_METHOD_NAME);
        
        //１）値幅上限値計算 
        //
        //   値幅上限値 = 引数.基準値＋引数.制限値幅 
        BigDecimal l_bdCapValue =
            new BigDecimal(String.valueOf(l_dblBasicValue)).add(new BigDecimal(String.valueOf(l_dblDeregulatedPriceRange)));  
        
        //２）刻み値取得 
        //
        //   外国株式プロダクトマネージャ.get刻み値()にて刻み値を取得する。 
        //
        //   [引数] 
        //   外国株式銘柄： 引数.外国株式銘柄 
        //   単価： １）で算出した値幅上限値 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_manager = (WEB3FeqProductManager) l_tradingModule.getProductManager();
        BigDecimal l_bdTickValue =
            new BigDecimal(String.valueOf(l_manager.getTickValue(l_feqProduct, l_bdCapValue.doubleValue())));
        
        //３）呼値単位の値幅上限値計算 
        //
        //○（値幅上限値／刻み値）の余り　@>=　@刻み値／2）の場合 
        //
        //   以下の計算結果を返却する。 
        //
        //   [計算式] 
        //   （値幅上限値／刻み値(*1)）×刻み値 
        //   (*1)除算の計算結果の小数部桁数未満切り上げ 
        //
        int l_intScale =  l_feqProduct.getCurrency().getScale();
        double l_dblBasePrice = l_bdCapValue.doubleValue(); //基準値
        
        if (WEB3FeqOrderUtility.getRemainder(l_bdCapValue.doubleValue(), l_bdTickValue.doubleValue())
            >= l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_EVEN).doubleValue())
        {
            l_dblBasePrice =
                l_bdCapValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();                        
        }
        //○上記以外の場合 
        //
        //   以下の計算結果を返却する。 
        //
        //   [計算式] 
        //   （値幅上限値／刻み値(*2)）×刻み値 
        //   (*2)除算の計算結果の小数部桁数未満切り捨て 
        else
        {
            l_dblBasePrice =
                l_bdCapValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }
    
    /**
     * (validate売付可能数量)<BR>
     * 売付に十分な数量の資産を所有しているかをチェックする。<BR>
     * <BR>
     * １）保有資産オブジェクトを取得する。<BR>
     * <BR>
     *    外国株式ポジションマネージャ.get保有資産()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    口座ID： 補助口座.getAccountId()の戻り値<BR>
     *    補助口座ID： 補助口座.getSubAccountId()の戻り値<BR>
     *    銘柄ID： 引数.銘柄ID<BR>
     *    税区分： 引数.税区分<BR>
     * <BR>
     * ２）ロック中数量を取得する。<BR>
     * <BR>
     *    保有資産.getLockedQuantity()をコールする。<BR>
     * <BR>
     * ３）以下の条件が成立する場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02109<BR>
     * <BR>
     *   （保有資産オブジェクト.数量－ロック中数量） ＜ 引数.数量<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * 
     * @@param l_lngProcutId - (銘柄ID)<BR>
     * 銘柄ID
     * 
     * @@param l_dblQuantity - (数量)<BR>
     * 注文数量
     * 
     * @@param l_taxType - (税区分)<BR>
     * 税区分
     * @@throws WEB3BaseException 
     * @@roseuid 42BA59500128
     */
    public void validateSellPossQuantity(WEB3GentradeSubAccount l_subAccount,
        long l_lngProcutId,
        double l_dblQuantity,
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateSellPossQuantity(WEB3GentradeSubAccount, String, double, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //売付に十分な数量の資産を所有しているかをチェックする。 
        //
        //１）保有資産オブジェクトを取得する。 
        //
        //   外国株式ポジションマネージャ.get保有資産()をコールする。 
        //
        //   [引数] 
        //   口座ID： 補助口座.getAccountId()の戻り値 
        //   補助口座ID： 補助口座.getSubAccountId()の戻り値 
        //   銘柄ID： 引数.銘柄ID 
        //   税区分： 引数.税区分
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_positionManager =
            (WEB3FeqPositionManager) l_tradingModule.getPositionManager();
        FeqAssetImpl l_asset =  
            (FeqAssetImpl)l_positionManager.getAsset(l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(), l_lngProcutId, l_taxType);

        //２）ロック中数量を取得する。 
        //
        //   保有資産.getLockedQuantity()をコールする。 
        double l_dblLockedQuantity = l_asset.getLockedQuantity();
        //３）以下の条件が成立する場合、例外をスローする。 
        //
        //  （保有資産オブジェクト.数量－ロック中数量） ＜ 引数.数量 
        double l_dblQuant = l_asset.getQuantity();
        if (l_dblQuant - l_dblLockedQuantity < l_dblQuantity)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02109.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02109,
                this.getClass().getName() + "." + STR_METHOD_NAME);      
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文条件)<BR>
     * 以下のチェックを行う。<BR>
     * <BR>
     *・執行条件チェック<BR>
     *・発注条件チェック<BR>
     *・出来るまで注文取扱のチェック<BR>
     *・出来るまで注文有効期限のチェック<BR>
     * <BR>
     *１）取扱可能注文条件を取得する。<BR>
     * <BR>
     * [コンストラクタにセットする引数]<BR>
     * 証券会社コード：　@引数.証券会社コード<BR> 
     * 銘柄タイプ：　@”外国株式”<BR>
     * 先物／オプション区分：　@DEFAULT<BR> 
     * 信用取引区分：　@DEFAULT<BR>
     * 市場コード：　@引数の市場コード<BR>
     * <BR>
     * ２）執行条件のチェック<BR>
     * <BR>
     * 取扱可能注文条件.is取扱可能執行条件()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 執行条件： 引数.執行条件<BR>
     * <BR>
     * ※戻り値がfalseの場合、「執行条件チェックエラー」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00150<BR> 
     * <BR> 
     * ３）発注条件のチェック<BR>
     * <BR> 
     * 取扱可能注文条件.is取扱可能発注条件()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 発注条件： 引数.発注条件<BR>
     * <BR>
     *  ※戻り値がfalseの場合、「発注条件チェックエラー」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00151<BR> 
     * <BR>
     * ４）出来るまで注文チェック<BR>
     * <BR>
     * 引数.is出来るまで注文 == true の場合のみ、以下を実行する。<BR>
     * <BR>
     * ４－１）出来るまで注文取扱可能チェック<BR>
     * <BR>
     * 取扱可能注文条件.is出来るまで注文取扱可能()をコールする。<BR>
     * <BR>
     * ※戻り値がfalseの場合、「出来るまで注文取扱チェックエラー」の例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00704<BR>  
     * ４－２）出来るまで注文有効期限チェック<BR>
     * <BR>
     * 取扱可能注文条件.is出来るまで注文可能日()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * ※引数.原注文発注日 == null の場合<BR>
     * 注文失効日： 引数.注文失効日<BR>
     * ※引数.原注文 != null の場合<BR>
     * 注文失効日： 引数.注文失効日<BR>
     * 原注文発注日： 引数.原注文発注日<BR>
     * <BR>
     * ※戻り値がfalseの場合、「出来るまで注文有効期限チェックエラー」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01815<BR>  
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * 
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード
     * 
     * @@param l_datOldOrderBizDate - (原注文発注)<BR>
     * 原注文発注日
     * 
     * @@param l_datExpirationDate - (注文失効日)<BR>
     * 注文失効日
     * 
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件
     * 
     * @@param l_executionConditionType - (執行条件)<BR>
     * 執行条件
     * 
     * @@param l_blnIsCarriedOrder - (is出来るまで注文)<BR>
     * is出来るまで注文
     * @@throws WEB3BaseException 
     */
    public void validateOrderCondition(String l_strInstitutionCode,
        String l_strMarketCode, 
        Date l_datOldOrderBizDate, 
        Date l_datExpirationDate, 
        String l_strOrderConditionType, 
        FeqExecutionConditionType l_executionConditionType, 
        boolean l_blnIsCarriedOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateOrderCondition(String, String, Date, Date, String, FeqExecutionConditionTypes, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１）取扱可能注文条件を取得する。 
        //
        //   [コンストラクタにセットする引数] 
        //   証券会社コード：　@引数.証券会社コード  
        //   銘柄タイプ：　@”外国株式” 
        //   先物／オプション区分：　@DEFAULT  
        //   信用取引区分：　@DEFAULT 
        //   市場コード：　@引数の市場コード 
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);
        //２）執行条件のチェック 
        //
        //   取扱可能注文条件.is取扱可能執行条件()をコールする。 
        //
        //   [引数] 
        //   執行条件： 引数.執行条件 
        //
        //   ※戻り値がfalseの場合、「執行条件チェックエラー」の例外をスローする。 
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionConditionType))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00150.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //３）発注条件のチェック 
        //
        //   取扱可能注文条件.is取扱可能発注条件()をコールする。 
        //
        //   [引数] 
        //   発注条件： 引数.発注条件 
        //
        //   ※戻り値がfalseの場合、「発注条件チェックエラー」の例外をスローする。 
        if (!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderConditionType))
        {            
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00151.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //４）出来るまで注文チェック 
        //   引数.is出来るまで注文 == true の場合のみ、以下を実行する。 
        //
        //４－１）出来るまで注文取扱可能チェック 
        //
        //   取扱可能注文条件.is出来るまで注文取扱可能()をコールする。 
        //
        //   ※戻り値がfalseの場合、「出来るまで注文取扱チェックエラー」の例外をスローする。 
        if (l_blnIsCarriedOrder)
        {
            if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleHandling())
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00704.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00704,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //４－２）出来るまで注文有効期限チェック 
            //
            //   取扱可能注文条件.is出来るまで注文可能日()をコールする。 
            //
            //   [引数] 
            //   ※引数.原注文発注日 == null の場合 
            //      注文失効日： 引数.注文失効日 
            //   ※引数.原注文発注日 != null の場合 
            //      注文失効日： 引数.注文失効日 
            //      原注文発注日： 引数.原注文発注日 
            //
            //   ※戻り値がfalseの場合、「出来るまで注文有効期限チェックエラー」の例外をスローする。 

            if (l_datOldOrderBizDate != null)
            {
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate, l_datOldOrderBizDate))
                {
                    log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01815.getErrorMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            else
            {
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate))
                {
                    log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01815.getErrorMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }            
            }
        }                
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate訂正可能市場)<BR>
     * 訂正が可能な市場であるかどうかをチェックする。 <BR>
     * <BR>
     * １）該当する注文単位を取得する。 <BR>
     * <BR>
     *    外国株式注文マネージャ.get注文単位ByOrderId()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    注文ID： 引数.注文ID <BR>
     * <BR>
     * ２）市場用プリファ@レンスから以下の条件でレコードを取得する。 <BR>
     * <BR>
     *    [条件] <BR>
     *    市場ID = １）で取得した注文単位.市場ID <BR>
     *    プリファ@レンス項目名 = "feq.order.change" <BR>
     *    項目名連番 = 1 <BR>
     * ３）プリファ@レンスの値のチェック <BR>
     * <BR>
     *   ２）で取得したレコードのプリファ@レンスの値 == "false"（訂正不可） の場合、 <BR>
     *    注文訂正不可市場として例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02204<BR>  
     * @@l_lngOrderId -(注文ID)
     * @@throws WEB3BaseException
     */
    public void validateChangePossMarket(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateChangePossMarket(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        //１）該当する注文単位を取得する。
        //外国株式注文マネージャ.get注文単位ByOrderId()をコールする。 
        FeqOrderUnit l_orderUnit = l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);
        FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        //２）市場用プリファ@レンスから以下の条件でレコードを取得する。
        //[条件] 
        //市場ID = １）で取得した注文単位.市場ID 
        //プリファ@レンス項目名 = "feq.order.change" 
        //項目名連番 = 1 
        QueryProcessor l_qp = null;
        List l_lisMarketPreferences = null;
        Long l_lngNameSerialNo = new Long(1);
        try
        {
            String l_strWhere = " market_id = ?  and name = ?  and name_serial_no = ? ";
            Object[] l_bindValues = {
                new Long(l_orderUnitRow.getMarketId()),
                WEB3MarketPreferencesNameDef.FEQ_ORDER_CHANGE,
                l_lngNameSerialNo};

            l_qp = Processors.getDefaultProcessor();
            
            l_lisMarketPreferences = l_qp.doFindAllQuery(MarketPreferencesRow.TYPE, l_strWhere, l_bindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        if (!(l_lisMarketPreferences == null || l_lisMarketPreferences.isEmpty()))
        {         
            MarketPreferencesRow l_row = (MarketPreferencesRow)l_lisMarketPreferences.get(0);
            //３）プリファ@レンスの値のチェック
            //２）で取得したレコードのプリファ@レンスの値 == "false"（訂正不可） の場合、 
            //注文訂正不可市場として例外をスローする。
            if (WEB3MarketPreferencesValueDef.UNABLE.equals(l_row.getValue()))
            {
                log.debug("注文訂正不可の市場です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02204,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate当日為替レート)<BR>
     * 当日の約定為替レートが登録されているかを判定する。<BR>
     * <BR>
     * １）　@(共通)通貨オブジェクトを取得する。<BR>
     * 　@引数.外国株式注文単位.get通貨()をコールする。<BR>
     * <BR>
     * ２）　@現在日付(yyyymmdd)と約定為替更新日付(yyyymmdd)を比較する。<BR>
     * <BR>
     * 　@①@注文単位.get通貨().約定為替更新日付　@＝　@現在日付<BR>
     * 　@②注文単位.get通貨().約定為替更新日付　@≠　@現在日付<BR>
     * <BR>
     * 　@①@の場合は、trueを返す。<BR>
     * 　@②の場合は、falseを返す。<BR>
     * <BR>
     * 　@※<BR>
     * 　@現在日付：GtlUtils.getSystemTimeStamp()の年月日部分<BR>
     * <BR>
     * @@param l_orderUnit - 外国株式注文単位<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean validateDayExchange(WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateDayExchange(WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        
        // １）　@(共通)通貨オブジェクトを取得する。
        // 引数.外国株式注文単位.get通貨()をコールする。
        WEB3GentradeCurrency l_currency = l_orderUnit.getCurrency();
        
        // ２）　@現在日付(yyyymmdd)と約定為替更新日付(yyyymmdd)を比較する。
        // ①@注文単位.get通貨().約定為替更新日付　@＝　@現在日付
        // ②注文単位.get通貨().約定為替更新日付　@≠　@現在日付
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        GenCurrencyRow l_genCurrencyRow = 
            (GenCurrencyRow)l_currency.getDataSourceObject();
        Timestamp l_tsExecRateUpdateTimestamp = 
            l_genCurrencyRow.getExecRateUpdateTimestamp();

        if (WEB3DateUtility.compareToDay(l_tsSystemTimestamp, l_tsExecRateUpdateTimestamp) == 0)
        {
            // ①@の場合は、trueを返す。
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            // ②の場合は、falseを返す。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}@
