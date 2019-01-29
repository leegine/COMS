head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteChangeButtonDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定変更ボタン区分インタフェイス(WEB3BondExecuteChangeButtonDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 黄建(中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 約定変更ボタン区分 定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0 
 */
public interface WEB3BondExecuteChangeButtonDivDef
{
    /**
     * 0：非表示
     */
    public static final String DISPLAY_NO = "0";
    
    /**
     * 1：約定ボタン　@
     */
    public static final String EXECUTE_BUTTON = "1";
    
    /**
     * 2：変更ボタン
     */
    public static final String CHANGE_BUTTON = "2";
}
@
