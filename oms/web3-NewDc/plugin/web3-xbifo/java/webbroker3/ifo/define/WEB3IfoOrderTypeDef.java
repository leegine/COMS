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
filename	WEB3IfoOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文状態 定数定義インタフェイス(WEB3IfoOrderTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 凌建平(中訊) 作成
*/
package webbroker3.ifo.define;

/**
 * 注文状態　@定数定義インタフェイス
 *                                                                     
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3IfoOrderTypeDef {

    /**
     * "00"（新規注文）
     */
    public static final String NEW_ORDER = "00";

    /**
     *  "01"（訂正）
     */
    public static final String CHANGE_ORDER = "01";
    
    /**
     *  "02"（取消）
     */
    public static final String CANCEL_ORDER = "02";
    
    /**
     * "03"（一部約定）
     */
    public static final String ONE_ORDERED = "03";

    /**
     * "04"（全部約定）
     */
    public static final String ALL_ORDERED = "04";
    
    
    /**
     * "05"（一部失効）
     */
    public static final String ONE_LAPSE = "05";

    /**
     * "06"（全部失効）
     */
    public static final String ALL_LAPSE = "06";

    /**
     * "07"（注文繰越）
     */
    public static final String ORDER_OVER = "07";

    /**
     * "08"（約定取消）
     */
    public static final String ORDERED_CANCEL = "08";

    /**
     * "99"（その他）
     */
    public static final String OTHER = "99";
    
}
@
