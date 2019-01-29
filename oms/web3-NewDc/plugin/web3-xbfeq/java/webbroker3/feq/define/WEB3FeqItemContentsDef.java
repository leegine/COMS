head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqItemContentsDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 項目内容 定数定義インタフェイス(WEB3FeqItemContentsDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 項目内容 定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqItemContentsDef
{
    /**
     * 注文種別.買付
     */
    public static final String BUY = "買付";
    
    /**
     * 注文種別.売付
     */
    public static final String SELL = "売付";

    /**
     * 決済区分.円貨
     */
    public static final String JAPANESE_CURRENCY = "円貨";
    
    /**
     * 決済区分.外貨
     */
    public static final String FOREIGN_CURRENCY = "外貨";

    /**
     * 税区分.一般
     */
    public static final String NORMAL = "一般";
    
    /**
     * 税区分.特定
     */
    public static final String SPECIAL = "特定";

    /**
     * 執行条件.無条件
     */
    public static final String NONE = "無条件";
    
    /**
     * 執行条件.寄付
     */
    public static final String AT_MARKET_OPEN = "寄付";
    
    /**
     * 執行条件.引け
     */
    public static final String AT_MARKET_CLOSE = "引け";
    
    /**
     * 執行条件.出来ずば引成(不成)
     */
    public static final String AT_MARKET_CLOSE_NOT_EXECUTED = "出来ずば引成(不成)";

    /**
     * 注文期限区分.当日限り
     */
    public static final String DAY_LIMIT = "当日限り";
    
    /**
     * 注文期限区分.出来るまで注文
     */
    public static final String CARRIED_ORDER = "出来るまで注文";

    /**
     * 発注条件.逆指値
     */
    public static final String STOP_LIMIT_PRICE = "逆指値";
    
    /**
     * 発注条件.W指値
     */
    public static final String W_LIMIT_PRICE = "W指値";

    /**
     * 発注条件演算子.以上
     */
    public static final String ABOVE_BASE_PRICE = "以上";
    
    /**
     * 発注条件演算子.以下
     */
    public static final String BELOW_BASE_PRICE = "以下";

    /**
     * 発注条件.DEFAULT（条件指定なし）
     */
    public static final String ORDER_CONDITION_DEFAULT = "DEFAULT（条件指定なし）";
}
@
