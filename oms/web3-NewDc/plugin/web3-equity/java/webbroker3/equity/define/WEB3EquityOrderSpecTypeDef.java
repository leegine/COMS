head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderSpecTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文内容区分　@定数定義インタフェイス(WEB3EquityOrderSpecTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 中尾寿彦(SRA) 新規作成
Revesion History : 2006/11/07 唐性峰　@(中訊)モデル No.990
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1164
Revesion History : 2007/07/31 何文敏 (中訊) 仕様変更・モデル1188
Revesion History : 2007/10/10 何文敏 (中訊) 仕様変更モデルNo.1194
*/
package webbroker3.equity.define;

/**
 * 注文内容区分　@定数定義インタフェイス
 * 
 * @@author 中尾寿彦
 * @@version 1.0
 */
public interface WEB3EquityOrderSpecTypeDef {
    
    /**
     * 00：新規注文
     */
    public static final String NEW_ORDER = "00";
    
    /**
     * 01：注文受付
     */
    public static final String NEW_ORDER_ACCEPT = "01";
    
    /**
     * 02：新規注文(失敗)
     */
    public static final String NEW_ORDER_REJECT = "02";
    
    /**
     * 03：訂正注文
     */
    public static final String CHANGE = "03";
    
    /**
     * 04：訂正受付
     */
    public static final String CHANGE_ACCEPT = "04";
    
    /**
     * 05：訂正完了
     */
    public static final String CHANGE_COMPLETE = "05";
    
    /**
     * 06：訂正注文(失敗)
     */
    public static final String CHANGE_REJECT = "06";
    
    /**
     * 07：取消注文
     */
    public static final String CANCEL = "07";
    
    /**
     * 08：取消受付
     */
    public static final String CANCEL_ACCEPT = "08";
    
    /**
     * 09：取消完了
     */
    public static final String CANCEL_COMPLETE = "09";
    
    /**
     * 10：取消注文(失敗)
     */
    public static final String CANCEL_REJECT = "10";
    
    /**
     * 11：一部約定
     */
    public static final String PART_EXECUTE = "11";

    /**
     * 12：全部約定
     */
    public static final String FULL_EXECUTE = "12";
    
    /**
     * 13：約定取消
     */
    public static final String UNDO_EXECUTE = "13";
    
    /**
     * 14：失効
     */
    public static final String EXPIRE = "14";

    /**
     * 15：失効取消
     */
    public static final String UNDO_EXPIRE = "15";
    
    /**
     * 16：無効
     */
    public static final String INVALID = "16";
    
    /**
     * 17：注文繰越
     */
    public static final String ORDER_CARRY_OVER = "17";
    
    /**
     * 18：注文繰越(失敗)
     */
    public static final String ORDER_CARRY_OVER_REJECT = "18";
    
    /**
     * 19：約定取消(現引現渡)
     */
    public static final String UNDO_EXECUTE_SWAP = "19";
    
    /**
     * 20：発注中
     */
    public static final String NEW_ORDER_ORDERING = "20";
    
    /**
     * 21：発注遅延
     */
    public static final String ORDER_DELAY = "21";

    /**
     * 22：切替遅延
     */
    public static final String SWITCH_DELAY = "22";

    /**
     * 23：切替注文
     */
    public static final String SWITCH_ORDER = "23";

    /**
     * 24：切替受付
     */
    public static final String SWITCH_ACCEPT = "24";

    /**
     * 25：切替完了
     */
    public static final String SWITCH_OVER = "25";

    /**
     * 26：切替注文(失敗)
     */
    public static final String SWITCH_ORDER_FAIL = "26";

    /**
     * 27：ストップ注文失効
     */
    public static final String STOP_ORDER_EXPIRE = "27";

    /**
     * 29：強制失効
     */
    public static final String FORCED_EXPIRE = "29";

    /**
     * 30：承認済
     */
    public static final String APPROVED = "30";

    /**
     * 99：その他
     */
    public static final String OTHER = "99";
}
@
