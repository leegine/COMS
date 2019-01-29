head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PublicMarketDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 公開市場定数定義インタフェイス(WEB3PublicMarketDef.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/03 劉江涛(sinocom) 新規作成
Revesion History : 2010/09/21 趙天月 (中訊) ＤＢレイアウト No.031
*/
package webbroker3.common.define;

/**
 * 公開市場 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3PublicMarketDef
{

    /**
     * 10：東証　@　@　@　@　@　@  　@　@
     */
    public final static String TOKYO_STOCK_EXCHANGE  = "10";

    /**
     * 11：東証一部　@　@
     */
    public final static String TSE_NO_ONE_DEPT = "11";
    
    /**
     * 12：東証二部　@
     */
    public final static String TSE_NO_TWO_DEPT = "12";
    
     /**
     * 13：マザーズ　@
     */
    public final static String MOTHERS = "13";
    
     /**
     * 20：大証　@
     */
    public final static String OSAKA_SECURITIES_EXCHANGE = "20";
    
     /**
     * 21：大証一部　@
     */
    public final static String OSE_NO_ONE_DEPT = "21";
    
     /**
     * 22：大証二部　@
     */
    public final static String OSE_NO_TWO_DEPT = "22";

     /**
     * 30：名証　@
     */
    public final static String NAGOYA_STOCK_EXCHANGE = "30";
    
     /**
     * 31：名証一部　@
     */
    public final static String NSE_NO_ONE_DEPT = "31";
    
     /**
     * 32：名証二部　@
     */
    public final static String NSE_NO_TWO_DEPT = "32";
    
     /**
     * 33：セントレックス
     */
    public final static String CENTREX = "33";
    
     /**
     * 40：福証　@
     */
    public final static String FUKUOKA_STOCK_EXCHANGE = "40";
    
     /**
     * 41：Q-Board
     */
    public final static String Q_BOARD = "41";
    
     /**
     * 50：札証　@
     */
    public final static String SAPPORO_STOCK_EXCHANGE = "50";
    
     /**
     * 51：アンビシャス
     */
    public final static String AMBITIOUS = "51";

    /**
     * 90：JASDAQ（スタンダード）
     */
    public final static String JASDAQ_STANDARD = "90";

    /**
     * 91：JASDAQ（グロース）
     */
    public final static String JASDAQ_CLOSE = "91";
}@
