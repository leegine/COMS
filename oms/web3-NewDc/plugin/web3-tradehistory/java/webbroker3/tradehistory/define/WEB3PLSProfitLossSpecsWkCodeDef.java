head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsWkCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WKコード(WEB3PLSProfitLossSpecsWkCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/22 周捷(中訊) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * WKコード 定数定義インタフェイス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public interface WEB3PLSProfitLossSpecsWkCodeDef
{
    /**
     * 1000: (株式)
     */
    public final static String PLS_COMDV_1000 = "1000";
    
    /**
     * 1100: (ミニ株)
     */
    public final static String PLS_COMDV_1100 = "1100";
    
    /**
     * 1221: (ミニ株)端株売却
     */
    public final static String PLS_COMDV_1221 = "1221";
    
    /**
     * 1222: (ミニ株)有償増資
     */
    public final static String PLS_COMDV_1222 = "1222";
    
    /**
     * 1200: (ミニ株)
     */
    public final static String PLS_COMDV_1200 = "1200";
    
    /**
     * 1511: （株式）確定配当
     */
    public final static String PLS_COMDV_1511 = "1511";
    
    /**
     * 1512: （株式）預かり配当
     */
    public final static String PLS_COMDV_1512 = "1512";
    
    /**
     * 1513: （株式）権利受払金
     */
    public final static String PLS_COMDV_1513 = "1513";
    
    /**
     * 1500: （株式）
     */
    public final static String PLS_COMDV_1500 = "1500";
    
    /**
     * 2000: （投信）
     */
    public final static String PLS_COMDV_2000 = "2000";
    
    /**
     * 2100: （投信）
     */
    public final static String PLS_COMDV_2100 = "2100";
    
    /**
     * 3000: （債券）
     */
    public final static String PLS_COMDV_3000 = "3000";
    
    /**
     * 4000: （外株）
     */
    public final static String PLS_COMDV_4000 = "4000";
    
    /**
     * 4231: （外株）権利売却
     */
    public final static String PLS_COMDV_4231 = "4231";
    
    /**
     * 4200: （外株）
     */
    public final static String PLS_COMDV_4200 = "4200";
    
    /**
     * 1: 一般上場
     */
    public final static String PLS_TERDV_1 = "1";
    
    /**
     * 2: 特定信用
     */
    public final static String PLS_TERDV_2 = "2";
    
    /**
     * 3: 長期上場
     */
    public final static String PLS_TERDV_3 = "3";
    
    /**
     * 4: 長期特定
     */
    public final static String PLS_TERDV_4 = "4";
    
    /**
     * 11: 還付金(訂正)
     */
    public final static String PLS_RETDV_11 = "11";
    
    /**
     * 10: 還付金
     */
    public final static String PLS_RETDV_10 = "10";
    
    /**
     * 01: 徴収税額(訂正)
     */
    public final static String PLS_RETDV_01 = "01";
    
    /**
     * 00: 徴収税額
     */
    public final static String PLS_RETDV_00 = "00";
    
    /**
     * 90: 繰越残高
     */
    public final static String PLS_RETDV_90 = "90";
    
    /**
     * 91: 徴収税額
     */
    public final static String PLS_RETDV_91 = "91";
}
@
