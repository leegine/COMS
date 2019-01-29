head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquitySmallItemDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 小項目区分 定数定義インタフェイス(WEB3AdminEquitySmallItemDivDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.define;

/**
 * 小項目区分 定数定義インタフェイス
 *
 * @@author Babu Prasad
 * @@version 1.0
 */
public interface WEB3AdminEquitySmallItemDivDef
{
    /**
     * 現物株売買停止
     */
    public final static String TRADE_STOP = "0";

    /**
     * 制度信用取引売買停止
     */
    public final static String MARGIN_SYS_TRADE_STOP = "1";

    /**
     * 一般信用取引売買停止
     */
    public final static String MARGIN_GEN_TRADE_STOP = "2";

    /**
     * 買現物停止
     */
    public final static String BUY_CASH_STOP = "3";

    /**
     * 売現物停止
     */
    public final static String SELL_CASH_STOP = "4";

    /**
     * 買制度信用停止
     */
    public final static String LONG_MARGIN_SYS_STOP = "5";

    /**
     * 売制度信用停止
     */
    public final static String SHORT_MARGIN_SYS_STOP = "6";

    /**
     * 買建返済制度信用停止
     */
    public final static String LONG_CLOSE_MARGIN_SYS_STOP = "7";

    /**
     * 売建返済制度信用停止
     */
    public final static String SHORT_CLOSE_MARGIN_SYS_STOP = "8";

    /**
     * 現引制度信用停止
     */
    public final static String LONG_SWAP_MARGIN_SYS_STOP = "9";

    /**
     * 現渡制度信用停止
     */
    public final static String SHORT_SWAP_MARGIN_SYS_STOP = "10";

    /**
     * 買一般信用停止
     */
    public final static String LONG_MARGIN_GEN_STOP = "11";

    /**
     * 売一般信用停止
     */
    public final static String SHORT_MARGIN_GEN_STOP = "12";

    /**
     * 買建返済一般信用停止
     */
    public final static String LONG_CLOSE_MARGIN_GEN_STOP = "13";

    /**
     *売建返済一般信用停止
     */
    public final static String SHORT_CLOSE_MARGIN_GEN_STOP = "14";

    /**
     * 現引一般信用停止
     */
    public final static String LONG_SWAP_MARGIN_GEN_STOP = "15";

    /**
     * 現渡一般信用停止
     */
    public final static String SHORT_SWAP_MARGIN_GEN_STOP = "16";

    /**
     * 買現物停止(成行)
     */
    public final static String BUY_SPOT_MARKET_ORD_DES_STOP = "17";

    /**
     * 売現物停止(成行)
     */
    public final static String SELL_SPOT_MARKET_ORD_DES_STOP = "18";

    /**
     * 買制度信用停止(成行)
     */
    public final static String LONG_MS_MARKET_ORD_DES_STOP = "19";

    /**
     * 売制度信用停止(成行)
     */
    public final static String SHORT_MS_MARKET_ORD_DES_STOP = "20";

    /**
     * 買建返済制度信用停止(成行)
     */
    public final static String LONG_CMS_MARKET_ORD_DES_STOP = "21";

    /**
     * 売建返済制度信用停止(成行)
     */
    public final static String SHORT_CMS_MARKET_ORD_DES_STOP = "22";

    /**
     * 買一般信用停止(成行)
     */
    public final static String LONG_MG_MARKET_ORD_DES_STOP = "23";

    /**
     * 売一般信用停止(成行)
     */
    public final static String SHORT_MG_MARKET_ORD_DES_STOP = "24";

    /**
     * 買建返済一般信用停止(成行)
     */
    public final static String LONG_CMG_MARKET_ORD_DES_STOP = "25";

    /**
     * 売建返済一般信用停止(成行)
     */
    public final static String SHORT_CMG_MARKET_ORD_DES_STOP = "26";

    /**
     * 買ミニ株停止
     */
    public final static String BUY_MINI_STOCK_STOP = "27";

    /**
     * 売ミニ株停止
     */
    public final static String SELL_MINI_STOCK_STOP = "28";

    /**
     * 銘柄名
     */
    public final static String STANDARD_NAME = "29";

    /**
     * 売買単位
     */
    public final static String LOT_SIZE = "30";

    /**
     * 強制限度単位
     */
    public final static String COMPULSIVE_LIMITED_UNIT = "31";

    /**
     * 取引方式
     */
    public final static String OPEN_OTC_DIV = "32";

    /**
     * 上場区分
     */
    public final static String LIST_TYPE = "33";

    /**
     * 即日入金規制
     */
    public final static String TODAY_DEP_FUND_REG = "34";

    /**
     * 株式ミニ投資市場
     */
    public final static String MINI_STOCK_MARKET = "35";

    /**
     * 買ミニ株制限時間
     */
    public final static String BUY_MINI_STOCK_TIME_LIMIT = "36";

    /**
     * 売ミニ株制限時間
     */
    public final static String SELL_MINI_STOCK_TIME_LIMIT = "37";

    /**
     * 制度信用銘柄区分
     */
    public final static String MARGIN_SYS_PRODUCT_TYPE = "38";

    /**
     * 一般信用銘柄区分
     */
    public final static String MARGIN_GEN_PRODUCT_TYPE = "39";

    /**
     * 買保証金率
     */
    public final static String LONG_MARGIN_DEPOSIT_RATE = "40";

    /**
     * 売保証金率
     */
    public final static String SHORT_MARGIN_DEPOSIT_RATE = "41";

    /**
     * 買現金保証金率
     */
    public final static String LONG_CASH_MARGIN_DEPOSIT_RATE = "42";

    /**
     * 売現金保証金率
     */
    public final static String SHORT_CASH_MARGIN_DEPOSIT_RATE = "43";

    /**
     * 代用掛目
     */
    public final static String MARGIN_RATIO = "44";

    /**
     * 代用評価単価
     */
    public final static String ESTIMATION_PRICE = "45";

    /**
     * 預り証券評価掛目
     */
    public final static String SECURITIES_ESTIMATION_RATIO = "46";

    /**
     * 基準値(終値)
     */
    public final static String LAST_CLOSING_PRICE = "47";

    /**
     * 値幅チェック
     */
    public final static String PRICE_RANGE_TYPE = "48";

    /**
     * 値幅
     */
    public final static String PRICE_RANGE = "49";

    /**
     * 強制値幅
     */
    public final static String COMPULSIVE_PRICE_RANGE = "50";

    /**
     * 強制値幅(値幅区分)
     */
    public final static String PRICE_RANGE_UNIT_TYPE = "51";

    /**
     * 強制値幅(下限)
     */
    public final static String LOW_COMPULSIVE_PRICE_RANGE = "52";

    /**
     * 強制値幅(上限)
     */
    public final static String HIGH_COMPULSIVE_PRICE_RANGE = "53";

	/**
	 * 優先市場
	 */
	public final static String PRIMARY_MARKET = "54";

	/**
	 * 特定口座取扱
	 */
    public final static String CAPITAL_GAIN_TAX_DEALING = "55";

    /**
     * 基準値
     */
    public final static String BASE_PRICE = "56";
}
@
