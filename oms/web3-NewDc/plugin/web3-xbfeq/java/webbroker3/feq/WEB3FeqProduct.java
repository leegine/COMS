head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄(WEB3FeqProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
Revesion History : 2008/01/16 柴双紅(中訊) モデルNo.372
*/

package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
/**
 * (外国株式銘柄) <BR>
 * 外国株式銘柄
 *
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3FeqProduct extends FeqProductImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTradedProduct.class);

    FeqProductRow l_feqProductRow = (FeqProductRow)this.getDataSourceObject();

    public FeqTradedProductRow l_feqTPRow = null;

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqProduct(ProductRow l_prow)
        throws DataQueryException, DataNetworkException
    {
        super(l_prow);
    }

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqProduct(FeqProductRow l_row)
        throws DataQueryException, DataNetworkException
    {
        super(l_row);
    }

    /**
     * (is特定口座取扱規制) <BR>
     * 特定口座取扱規制中かを判定する。trueの場合、規制中， <BR>
     * falseの場合取引可能。 <BR>
     *  <BR>
     * this.外株銘柄Params.特定口座取扱規制 == 0:  <BR>
     * 　@　@　@特定口座にて取扱不可 の場合、trueを返却する。 <BR>
     * 以外、falseを返却する。 <BR>
     * @@return boolean
     * @@roseuid 428052650150
     */
    public boolean isCapitalGainTaxDealingsReg()
    {
        final String STR_METHOD_NAME = "isCapitalGainTaxDealingsReg() ";
        log.entering(STR_METHOD_NAME);
        if (l_feqProductRow.getCapitalGainTaxDealingsReg() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

    }

    /**
     * (get権利落日) <BR>
     * 権利落日を取得する。 <BR>
     *  <BR>
     * this.外株銘柄Params.権利落日を返却する。 <BR>
     * @@return Date
     * @@roseuid 4282B77300BE
     */
    public Date getExRightDate()
    {
        return this.l_feqProductRow.getExRightDate();
    }

    /**
     * (get市場) <BR>
     * （getMarket） <BR>
     * 市場オブジェクトを取得する。 <BR>
     *  <BR>
     * 拡張金融オブジェクトマネージャ.getMarket()にて市場オブジェクトを取得し返却する。 <BR>
     *  <BR>
     * 　@[getMarket()に指定する引数] <BR>
     * 　@証券会社（Institution）：　@this.getInstitution() <BR>
     * 　@市場コード（String）：　@this.get市場コード() <BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 4282C52A00AF
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        try
        {
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finObjectManager.getMarket(this.getInstitution(), this.getMarketCode());
            log.exiting(STR_METHOD_NAME);
            return l_market;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
    }

    /**
     * (get市場コード) <BR>
     * （getMarketCode） <BR>
     * 市場コードを取得する。 <BR>
     *  <BR>
     * this.外株銘柄Params.市場コードを返却する。 <BR>
     * @@return String
     * @@roseuid 4282C5310310
     */
    public String getMarketCode()
    {
        return l_feqProductRow.getMarketCode();
    }

    /**
     * (get通貨コード) <BR>
     * （getcurrencyCode） <BR>
     * 通貨コードを取得する。 <BR>
     *  <BR>
     * this.外株銘柄Params.通貨コードを返却する。 <BR>
     * @@return String
     * @@roseuid 428330A700CE
     */
    public String getCurrencyCode()
    {
        return l_feqProductRow.getCurrencyCode();
    }

    /**
     * (get取引銘柄) <BR>
     * （getFeqTradedProduct） <BR>
     * 取引銘柄を取得する。 <BR>
     *  <BR>
     * １）　@市場コードリセット <BR>
     * 　@取引時間管理.reset市場コード()にて、市場をセットする。 <BR>
     *  <BR>
     * 　@[reset市場コード()に指定する引数] <BR>
     * 　@市場コード：　@this.get市場コード() <BR>
     *  <BR>
     * ２）　@取引銘柄取得 <BR>
     * 　@外国株式プロダクトマネージャ.getFeqTradedProduct()にて <BR>
     * 　@外国株式取引銘柄を取得し返却する。 <BR>
     *  <BR>
     * 　@[getFeqTradedProduct()に指定する引数] <BR>
     * 　@証券会社：　@this.getInstitution() <BR>
     * 　@銘柄コード：　@this.getProductCode() <BR>
     * 　@市場コード：　@this.get市場コード() <BR>
     * @@return WEB3FeqTradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 4282C53802E1
     */
    public WEB3FeqTradedProduct getFeqTradedProduct() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFeqTradedProduct() ";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeTradingTimeManagement.resetMarketCode(this.getMarketCode());
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productManager = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        try
        {
            WEB3FeqTradedProduct l_tradedProduct =
                (WEB3FeqTradedProduct)l_productManager.
                getFeqTradedProduct(this.getInstitution(),
                    this.getProductCode(), this.getMarketCode());

            log.exiting(STR_METHOD_NAME);

            return l_tradedProduct;
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (get通貨) <BR>
     * 通貨を取得する。 <BR>
     *  <BR>
     * （共通）通貨オブジェクトを生成し返却する。 <BR>
     *  <BR>
     * [コンストラクタに指定する引数] <BR>
     * 証券会社コード：　@getInstitution().getInstitutionCode() <BR>
     * 通貨コード：　@get通貨コード() <BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 42898A9001D4
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrency() ";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCurrency l_currency =
            WEB3GentradeCurrency.genCurrency(
                this.getInstitution().getInstitutionCode(),
                this.getCurrencyCode());

        log.exiting(STR_METHOD_NAME);
        return l_currency;
    }

    /**
     * (get現地銘柄コード) <BR>
     * 現地銘柄コードを取得する。 <BR>
     *  <BR>
     * this.外株銘柄Params.現地銘柄コードを返却する。 <BR>
     * @@return String
     * @@roseuid 42A975E603D7
     */
    public String getOffshoreProductCode()
    {
        return this.l_feqProductRow.getOffshoreProductCode();
    }

    /**
     * (get表示銘柄名) <BR>
     * 表示のための銘柄名を取得する。 <BR>
     *  <BR>
     * １）this.外株銘柄Params.銘柄名（漢字） != null の場合 <BR>
     *  <BR>
     *    this.外株銘柄Params.銘柄名（漢字）を返却する。 <BR>
     *  <BR>
     * ２）それ以外の場合 <BR>
     *  <BR>
     *    this.外株銘柄Params.銘柄名（カナ）を返却する。 <BR>
     * @@return String
     * @@roseuid 42B0DAF70057
     */
    public String getDisplayProductName()
    {
        final String STR_METHOD_NAME = "getDisplayProductName()";
        log.entering(STR_METHOD_NAME);
        if (this.l_feqProductRow.getStandardNameKanji() != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.l_feqProductRow.getStandardNameKanji();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.l_feqProductRow.getStandardNameKana();
        }
    }
}
@
