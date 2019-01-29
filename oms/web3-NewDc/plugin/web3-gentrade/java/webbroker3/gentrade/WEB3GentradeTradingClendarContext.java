head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTradingClendarContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引カレンダコンテキスト(WEB3GentradeTradingClendarContext.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 鄒政 (中訊) 新規作成
*/

package webbroker3.gentrade;

/**
 * (取引カレンダコンテキスト)<BR>
 * 取引時間を利用するサービスのインタセプタが利用するコンテキスト。<BR>
 * （TradingCalendarContext）<BR>
 * <BR>
 * 注文処理日・受付時間を参照するサービスは、必ず当該コンテキスト<BR>
 * の内容をセット（またはクリア）するインタセプタを用意する必要がある。<BR>
 * <BR>
 * [インタセプタの仕様]<BR>
 * 　@－onCall( )にて当該コンテキストの内容を設定<BR>
 * 　@－onReturn( )にて当該コンテキストの内容をクリア<BR>
 */
public class WEB3GentradeTradingClendarContext
{
    /**
     * 証券会社コード<BR>
     */
    private String institutionCode;

    /**
     * 部店コード<BR>
     */
    private String branchCode;

    /**
     * 市場コード<BR>
     * <BR>
     * 投信は"0"。<BR>
     * 市場を指定しない場合はnullをセットする。<BR>
     */
    private String marketCode;

    /**
     * 受付時間区分<BR>
     */
    private String tradingTimeType;

    /**
     * 注文受付商品<BR>
     * （注文受付ステイタステーブル.注文受付商品）<BR>
     */
    private String orderAcceptProduct;

    /**
     * 注文受付トランザクション<BR>
     * （注文受付ステイタステーブル.注文受付トランザクション）<BR>
     */
    private String orderAcceptTransaction;

    /**
     * 銘柄コード<BR>
     * <BR>
     * 先物OP／投信のみ使用。<BR>
     * 以外は 0：DEFAULT。<BR>
     */
    private String productCode;

    /**
     * (営業日区分) <BR>
     */
    private String bizDateType;

    /**
     * (市場トリガ発行)<BR>
     */
    private String submitMarketTrigger;

    /**
     * (受付可能)<BR>
     */
    private String enableOrder;

    /**
     * (発注日計算)<BR>
     */
    private String bizdateCalcParameter;

    /**
     * (取引停止商品)<BR>
     */
    private String tradingStopProduct;

    /**
     * (取引停止トランザクション)<BR>
     */
    private String tradingStopTransaction;

    /**
     * @@roseuid 40A2018D02F3
     */
    public WEB3GentradeTradingClendarContext()
    {

    }

    /**
     * 証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@roseuid 4014B5D703B3
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * 証券会社コードを取得する。<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 4014C12103A3
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * 部店コードをセットする。<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@roseuid 4014C16C00B5
     */
    public void setBranchCode(String l_strBranchCode)
    {
        this.branchCode = l_strBranchCode;
    }

    /**
     * 部店コードを取得する。<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 4014C16C00B7
     */
    public String getBranchCode()
    {
        return this.branchCode;
    }

    /**
     * 市場コードをセットする。<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@roseuid 4014C18C026B
     */
    public void setMarketCode(String l_strMarketCode)
    {
        this.marketCode = l_strMarketCode;
    }

    /**
     * 市場コードを取得する。<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 4014C18C026D
     */
    public String getMarketCode()
    {
        return this.marketCode;
    }

    /**
     * 受付時間区分をセットする。<BR>
     * @@param l_strTradingTimeType - 受付時間区分<BR>
     * @@roseuid 401615E303D7
     */
    public void setTradingTimeType(String l_strTradingTimeType)
    {
        this.tradingTimeType = l_strTradingTimeType;
    }

    /**
     * 受付時間区分を取得する。<BR>
     * @@return java.lang.String<BR>
     * @@roseuid 401615E303E7
     */
    public String getTradingTimeType()
    {
        return this.tradingTimeType;
    }

    /**
     * すべてのクラス変数にnullをセットし、コンテキストをクリアする。<BR>
     * @@roseuid 401618DF034B
     */
    public void clear()
    {
        this.institutionCode = null;
        this.branchCode = null;
        this.marketCode = null;
        this.tradingTimeType = null;
        this.orderAcceptProduct = null;
        this.orderAcceptTransaction = null;
        this.productCode = null;
        this.bizDateType = null;
        this.submitMarketTrigger = null;
        this.enableOrder = null;
        this.bizdateCalcParameter = null;
        this.tradingStopProduct = null;
        this.tradingStopTransaction = null;

    }

    /**
     * 注文受付商品を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 404D40C20301
     */
    public String getOrderAcceptProduct()
    {
        return this.orderAcceptProduct;
    }

    /**
     * 注文受付商品をセットする。<BR>
     * @@param l_strOrderAcceptProduct - 注文受付商品<BR>
     * @@roseuid 404D40CF00BF
     */
    public void setOrderAcceptProduct(String l_strOrderAcceptProduct)
    {
        this.orderAcceptProduct = l_strOrderAcceptProduct;
    }

    /**
     * 注文受付トランザクションを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 404D40D001B9
     */
    public String getOrderAcceptTransaction()
    {
        return this.orderAcceptTransaction;
    }

    /**
     * 注文受付トランザクションをセットする。<BR>
     * @@param l_strOrderAcceptTransaction - 注文受付トランザクション<BR>
     * @@roseuid 404D40D10265
     */
    public void setOrderAcceptTransaction(String l_strOrderAcceptTransaction)
    {
        this.orderAcceptTransaction = l_strOrderAcceptTransaction;
    }

    /**
     * 銘柄コードをセットする。<BR>
     * <BR>
     * 先物OP／投信のみ使用。<BR>
     * 以外は 0：DEWFAULT。<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@roseuid 40610C2D001E
     */
    public void setProductCode(String l_strProductCode)
    {
        this.productCode = l_strProductCode;
    }

    /**
     * 銘柄コードを取得する。<BR>
     * <BR>
     * 先物OP／投信のみ使用。<BR>
     * 以外は 0：DEWFAULT。<BR>
     * @@return java.lang.String
     * @@roseuid 40610C2D0176
     */
    public String getProductCode()
    {
        return this.productCode;
    }

    /**
     * (set営業日区分)<BR>
     *<BR> 
     * 営業日区分をセットする。<BR>
     * @@param l_bizDateType - 営業日区分
     * @@roseuid 401615E303D7
     */
    public void setBizDateType(String l_bizDateType)
    {
        this.bizDateType = l_bizDateType;
    }

    /**
     * (get営業日区分)<BR>
     *<BR> 
     * 営業日区分を取得する。<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getBizDateType()
    {
        return this.bizDateType;
    }

    /**
     * (set市場トリガ発行)<BR>
     *<BR> 
     * 市場トリガ発行をセットする。<BR>
     * @@param l_submitMarketTrigger - 市場トリガ発行<BR>
     * @@roseuid 401615E303D7
     */
    public void setSubmitMarketTrigger(String l_submitMarketTrigger)
    {
        this.submitMarketTrigger = l_submitMarketTrigger;
    }

    /**
     * (get市場トリガ発行)<BR>
     *<BR> 
     * 市場トリガ発行を取得する。<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getSubmitMarketTrigger()
    {
        return this.submitMarketTrigger;
    }

    /**
     * (set発注日計算)<BR>
     *<BR> 
     * 発注日計算をセットする。<BR>
     * @@param l_bizdateCalcParameter - 発注日計算<BR>
     * @@roseuid 401615E303D7
     */
    public void setBizdateCalcParameter(String l_bizdateCalcParameter)
    {
        this.bizdateCalcParameter = l_bizdateCalcParameter;
    }

    /**
     * (get発注日計算)<BR>
     *<BR> 
     * 発注日計算を取得する。<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getBizdateCalcParameter()
    {
        return this.bizdateCalcParameter;
    }

    /**
     * (set受付可能)<BR>
     *<BR> 
     * 受付可能をセットする。<BR>
     * @@param l_enableOrder - 受付可能<BR>
     * @@roseuid 401615E303D7
     */
    public void setEnableOrder(String l_enableOrder)
    {
        this.enableOrder = l_enableOrder;
    }

    /**
     * (get受付可能)<BR>
     *<BR> 
     * 受付可能を取得する。<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getEnableOrder()
    {
        return this.enableOrder;
    }

    /**
     * (set取引停止トランザクション)<BR>
     *<BR> 
     * 取引停止トランザクションをセットする。<BR>
     * @@param l_tradingStopTransaction - 取引停止トランザクション
     * @@roseuid 401615E303D7
     */
    public void setTradingStopTransaction(String l_tradingStopTransaction)
    {
        this.tradingStopTransaction = l_tradingStopTransaction;
    }

    /**
     * (get取引停止トランザクション)<BR>
     *<BR> 
     * 取引停止トランザクションを取得する。<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getTradingStopTransaction()
    {
        return this.tradingStopTransaction;
    }

    /**
     * (set取引停止商品)<BR>
     *<BR> 
     * 取引停止商品をセットする。<BR>
     * @@param l_tradingStopProduct - 取引停止商品<BR>
     * @@roseuid 401615E303D7
     */
    public void setTradingStopProduct(String l_tradingStopProduct)
    {
        this.tradingStopProduct = l_tradingStopProduct;
    }

    /**
     * (get取引停止商品)<BR>
     *<BR> 
     * 取引停止商品を取得する。<BR>
     * @@return String
     * @@roseuid 401615E303E7
     */
    public String getTradingStopProduct()
    {
        return this.tradingStopProduct;
    }
}
@
