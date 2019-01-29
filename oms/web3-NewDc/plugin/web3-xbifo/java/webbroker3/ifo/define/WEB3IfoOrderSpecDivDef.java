head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderSpecDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文内容区分インタフェイス(WEB3IfoOrderSpecDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/30 凌建平(中訊) 新規作成
                 : 2006/07/16 徐宏偉(中訊) 仕様変更No.472
*/
package webbroker3.ifo.define;

/**
 * 注文内容区分 定数定義インタフェイス<BR>
 * 00：新規注文 01：注文受付 02：新規注文(失敗) 03：訂正注文 04：訂正受付 <BR>
 * 05：訂正完了 06：訂正注文(失敗) 07：取消注文 08：取消受付  09：取消完了<BR>
 * 10：取消注文(失敗)11：一部約定 12：全部約定 13：約定取消 14：失効 15：失効取消 <BR>
 * 16：無効 17：注文繰越 18：注文繰越(失敗) 20：発注中 21：発注遅延 22:切替遅延 <BR>
 * 23：切替注文 24：切替受付 25：切替完了 26：切替注文(失敗)<BR>
 * 27：ストップ注文失効 99：その他<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3IfoOrderSpecDivDef
{
    /**
     * 00：新規注文　@　@  　@　@　@　@　@  　@　@
     */
    public final static String OPEN_ORDER = "00";

    /**
     * 01：注文受付　@
     */ 
    public final static String ORDER_ACCEPT = "01";
    
    /**
     * 02：新規注文(失敗)　@
     */ 
    public final static String OPEN_ORDER_FAIL = "02";
    
    /**
     * 03：訂正注文　@
     */ 
    public final static String CHANGE_ORDER = "03";
    
    /**
     * 04：訂正受付　@
     */ 
    public final static String CHANGE_ACCEPT = "04";
    
    /**
     * 05：訂正完了　@
     */ 
    public final static String CHANGE_FINISH = "05";
    
    /**
     * 06：訂正注文(失敗)　@
     */ 
    public final static String CHANGE_ORDER_FAIL = "06";
      
    /**
     * 07：取消注文　@
     */ 
    public final static String CANCEL_ORDER = "07";
    
    /**
     * 08：取消受付
     */ 
    public final static String CANCEL_ACCEPT = "08";

    /**
     * 09：取消完了
     */ 
    public final static String CANCEL_FINISH = "09";

    /**
     * 10：取消注文(失敗)
     */ 
    public final static String CANCEL_ORDER_FAIL = "10";

    /**
     * 11：一部約定
     */ 
    public final static String PARTIALLY_EXECUTED = "11";

    /**
     * 12：全部約定
     */ 
    public final static String FULLY_EXECUTED = "12";
    
    /**
     * 13：約定取消
     */ 
    public final static String EXCUTED_CANCEL = "13";
    
    /**
     * 14：失効
     */ 
    public final static String CLOSE = "14";
    
    /**
     * 15：失効取消
     */ 
    public final static String CLOSE_FAIL = "15";

    /**
     * 16：無効
     */ 
    public final static String INEFFECTIVE = "16";
    
    /**
     * 17：注文繰越
     */ 
    public final static String ORDER_CARRYOVER = "17";
    
    /**
     * 18：注文繰越(失敗)
     */ 
    public final static String ORDER_CARRYOVER_FAIL = "18";
    
    /**
     * 20：発注中
     */ 
    public final static String ORDERING = "20";

    /**
     * 21：発注遅延
     */ 
    public final static String ORDER_DELAY = "21";
    
    /**
     * 22:切替遅延
     */
    public final static String SWITCH_DELAY = "22";
    
    /**
     * 23：切替注文 
     */
    public final static String SWITCH_ORDER = "23";
    
    /**
     * 24：切替受付 
     */
    public final static String SWITCH_ACCEPT = "24";
    
    /**
     * 25：切替完了 
     */
    public final static String SWITCH_OVER = "25";
    
    /**
     * 26：切替注文(失敗)
     */
    public final static String SWITCH_ORDER_FAIL = "26";
    
    /**
     * 27：ストップ注文失効
     */
    public final static String STOP_ORDER_EXPIRE = "27";

    /**
     * 99：その他
     */ 
    public final static String OTHER = "99";
    
} @
