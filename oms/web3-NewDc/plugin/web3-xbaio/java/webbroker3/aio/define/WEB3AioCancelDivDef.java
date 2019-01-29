head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CancelDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 韋念瓊 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 出金申込問合せ明細の取消区分　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3AioCancelDivDef
{  
    /**
     * 0 : 初期値
     */
    public static final String INITIAL_VALUE = "0";

    /**
     * 1 : 取消中
     */
    public static final String CANCELING = "1";    
    
    /**
     * 2 : 取消済
     */
    public static final String CANCElED = "2";    
    
    /**
     * 3 : 取消失敗
     */
    public static final String CANCEL_FAIL = "3";  
    
    /**
     * 4 : エラー
     */
    public static final String ERROR = "4";
    
    /**
     * 5 : 取消不可
     */
    public static final String CANCEL_NOT = "5";
    
}
@
