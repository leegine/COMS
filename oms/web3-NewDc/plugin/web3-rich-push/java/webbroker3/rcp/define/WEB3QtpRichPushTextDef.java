head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.15.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3QtpRichPushTextDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QtpRichPushTextDefクラス(WEB3QtpRichPushTextDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 孫(FLJ) 新規作成
 */
package webbroker3.rcp.define;

/**
 * 表示文字の定数定義クラス<br>
 *
 * @@author　@孫(FLJ)
 * @@version 1.0
 */
public interface WEB3QtpRichPushTextDef
{
   
    /**
     * TITLE文字列　@最新の約定
     */
    public static final String TITLE_EXECUTION = "【最新の約定】";

    /**
     * 単価　@タイトル
     */
    public static final String PRICE_TITLE = " 単価:";

    /**
     * 数量　@タイトル
     */
    public static final String QUANTITY_TITLE = " 数量:";
    
    /**
     * 信用現引現渡の単価0の時の表示
     */
    public static final String PRICE_ZERO = " - ";
    
    /**
     * 単価　@単位
     */
    public static final String PRICE_UNIT = "円";
    
    /**
     * 数量　@単位　@株式
     */
    public static final String QUANTITY_UNIT_EQ = "株";
    
    
    /**
     * 数量　@単位　@先物オプション
     */
    public static final String QUANTITY_UNIT_IFO = "枚";
    
    /**
     * 注文照会画面
     */
    public static final String LINK_TITLE = "＜注文照会画面＞";
    
    /**
     * レスポンスのステータス　@成功状態
     */
    public static final String RESPONSE_SUCCESS = "SUCCESS";
    
    
}
@
