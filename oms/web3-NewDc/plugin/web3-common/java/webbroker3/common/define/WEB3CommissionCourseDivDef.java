head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionCourseDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料コースコード(WEB3CommissionCourseDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
Revision History : 2008/08/20 趙林鵬(中訊) ＤＢレイアウトNo.640
*/

package webbroker3.common.define;

/**
 * 手数料コースコード 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3CommissionCourseDivDef
{

    /**
     * 02：定率手数料（スタンダード）<BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ）<BR>
     */
    public final static String FIXED_RATE_COMMISSION_STANDARD  = "02";   

    /**
     * 03：約定代金合計<BR>
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR>
     */
    public final static String EXECUTED_TURNOVER_COUNT = "03";

    /**
     * 04：約定回数
     */
    public final static String EXECUTED_TIMES = "04";

    /**
     * 05：一日定額制
     */
    public final static String FIXED_AMOUNT = "05";
    
    /**
     * 06：少額ボックス
     */
    public final static String SMALL_AMOUNT_BOX = "06";

    /**
     * 07：　@現物1日合計＋信用1日注文毎
     */
    public final static String EQUITY_ONE_DAY_TOTAL_ADD_MARGIN_ONE_DAY_ORDER = "07";
    
    /**
     * 08：　@現物1日注文毎＋信用1日合計
     */
    public final static String EQUITY_ONE_DAY_ORDER_ADD_MARGIN_ONE_DAY_TOTAL = "08";
    
    /**
     * 16：少額ボックス（キャンペーン）
     */
    public final static String SMALL_AMOUNT_BOX_CAMPAIGN = "16";

    /**
     * 99：　@上記以外（リテラ・岩井のみ）
     */
    public final static String OTHER = "99";
}@
