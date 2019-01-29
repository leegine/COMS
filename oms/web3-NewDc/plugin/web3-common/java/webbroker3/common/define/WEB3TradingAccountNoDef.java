head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingAccountNoDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引口座Ｎｏ．(WEB3TradingAccountNoDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 取引口座Ｎｏ． 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3TradingAccountNoDef
{

    /**
     * 01：保護預り
     */
    public final static String SAFE_CUSTODY  = "01";

    /**
     * 02：外国証券
     */
    public final static String FOREIGN_SECURITIES  = "02";

    /**
     * 03：金取引
     */
    public final static String CASH_TRADING  = "03";

    /**
     * 04：信用取引
     */
    public final static String MARGIN_TRADING  = "04";

    /**
     * 05：発効日取引
     */
    public final static String EFFECTIVE_DATE_TRADING  = "05";

    /**
     * 06：国債先物
     */
    public final static String NATIONAL_BOND_FUTURE  = "06";

    /**
     * 07：株式先物
     */
    public final static String EQUITY_FUTURE  = "07";

    /**
     * 09：株オプション
     */
    public final static String EQUITY_OPTIONS  = "09";

    /**
     * 13：外国ワラント取引
     */
    public final static String FOREIGN_WARRANT_TRADING  = "13";

    /**
     * 14：店頭取引
     */
    public final static String OTC_TRADING  = "14";

    /**
     * 17：新ＴＢ取引
     */
    public final static String NEW_TB_TRADING  = "17";

    /**
     * 22：国内ワラント取引
     */
    public final static String DOMESTIC_WARRANT_TRADING  = "22";

    /**
     * 30：店頭信用
     */
    public final static String OTC_MARGIN  = "30";

    /**
     * 40：東証マザーズ
     */
    public final static String TSE_MOTHERS  = "40";

    /**
     * 41：ナスダックＪ
     */
    public final static String NASDAQJ  = "41";

    /**
     * 42：大証新市場
     */
    public final static String OSE_NEW_MARKET  = "42";

    /**
     * 43：名証成長
     */
    public final static String NSE_GROWTH  = "43";

    /**
     * 44：札幌アンビシヤス
     */
    public final static String SAPPORO  = "44";

    /**
     * 45：福岡QBOARD
     */
    public final static String FUKUOKA_QBOARD  = "45";

    /**
     * 46：店頭２号
     */
    public final static String OTC_NUMBER_TWO  = "46";

    /**
     * 91：先オプ・東証
     */
    public final static String FO_TSE  = "91";

    /**
     * 92：先オプ・大証
     */
    public final static String FO_OSE  = "92";

    /**
     * 93：先オプ・名証
     */
    public final static String FO_NSE  = "93";

}@
