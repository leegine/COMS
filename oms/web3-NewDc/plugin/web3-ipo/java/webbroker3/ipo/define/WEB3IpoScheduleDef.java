head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoScheduleDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3IpoScheduleDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11  李頴淵(sinocom)　@新規作成
*/
package webbroker3.ipo.define;

/**
 * IPOスケジュール
 *                                                                     
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3IpoScheduleDef
{

    /**
     * 1：　@ブックビルディング開始前
     */
    public static final String BOOKBUIDING_START_BEFORE = "1";

    /**
     * 2：　@ブックビルディング期間中
     */
    public static final String BOOKBUIDING_TERM = "2";
    
    /**
     * 3：　@ブックビルディング終了
     */
    public static final String BOOKBUIDING_END = "3";
    
    /**
     * 4：　@公開価格決定
     */
    public static final String PUBLIC_PRICE_DECISION = "4";
    
    /**
     * 5：　@抽選終了
     */
    public static final String LOTTERY_END = "5";
    
    /**
     * 6：　@購入申込期間中
     */
    public static final String PURCHASE_APPLICATION_TERM = "6";
    
    /**
     * 7：　@購入申込終了
     */
    public static final String PURCHASE_APPLICATION_END = "7";
    
    /**
     * 8：　@公開
     */
    public static final String PUBLIC = "8";
    
}
@
