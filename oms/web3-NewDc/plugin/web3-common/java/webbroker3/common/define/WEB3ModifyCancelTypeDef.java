head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ModifyCancelTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文訂正・取消区分定数定義クラス(WEB3ModifyCancelTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24  周玲玲　@(sinocom) 新規作成
Revesion History : 2006/07/12 栄イ (中訊)【先物オプション】仕様変更・ＤＢレイアウトNo.043
*/
package webbroker3.common.define;

/**
 * 注文訂正・取消区分定数定義クラス
 *
 * @@author 周玲玲(sinocom)
 * @@version 1.0
 */
public interface WEB3ModifyCancelTypeDef
{

    /**
     * 初期値<BR>
     */
    public final static String INITIAL_VALUE = "0";

    /**
     * 取消中<BR>
     */
    public final static String CANCELING = "1";
    
    /**
     * 一部取消完了<BR>
     */
    public final static String PART_CANCELED = "2";
    
    /**
     * 全部取消完了<BR>
     */
    public final static String CANCELED = "3";
    
    /**
     * 取消失敗<BR>
     */
    public final static String CANCEL_ERROR = "4";
    
    /**
     * 訂正中<BR>
     */
    public final static String CHANGING = "5";
    
    /**
     * 一部訂正完了<BR>
     */
    public final static String PARTIALLY_CHANGED = "6";                    
    
    /**
     * 全部訂正完了<BR>
     */
    public final static String CHANGED = "7";    
    
    /**
     * 訂正失敗<BR>
     */
    public final static String CHANGE_ERROR = "8";    
    
    /**
     * エラー<BR>
     */
    public final static String ERROR = "9";            
    
    /**
     * A:W指値注文切替中<BR>
     */
    public final static String W_LIMIT_TRANSFERING = "A";
    
    /**
     * B:W指値注文一部切替完了<BR>
     */
    public final static String W_LIMIT_PARTIALLY_TRANSFERED = "B";                    
    
    /**
     * C:W指値注文全部切替完了<BR>
     */
    public final static String W_LIMIT_TRANSFERED = "C";    
    
    /**
     * D:W指値注文切替失敗<BR>
     */
    public final static String W_LIMIT_TRANSFER_ERROR = "D";    
}
@
