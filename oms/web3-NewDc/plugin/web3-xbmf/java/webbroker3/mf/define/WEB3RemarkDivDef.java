head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RemarkDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 王蘭芬(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 解約可能区分/買取可能区分/乗換可能区分/備考区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3RemarkDivDef
{
    /**
     * 1 : 全部解約中
     */
    public static final String All_SELLING = "1";

    /**
     * 2 : 取扱不可(WEB取扱不可)
     */
    public static final String HANDLING_WEB_DISABLE = "2";

    /**
     * 3 : 取引不可(買付停止中)
     */
    public static final String HANDLING_DISABLE = "3";
    
    /**
     * 4 : 緊急停止中
     */
    public static final String EMERGENCY_STOP = "4";

    /**
     * 5 : 取引時間外注文停止中(受付時間外)　@ 
     */
    public static final String OUT_TRADINGTIME_ORDER_STOP = "5";
    
    /**
     * 6:募集期間中　@ 
     */
    public static final String RECRUIT_BETWEEN = "6";
    
    
     

}
@
