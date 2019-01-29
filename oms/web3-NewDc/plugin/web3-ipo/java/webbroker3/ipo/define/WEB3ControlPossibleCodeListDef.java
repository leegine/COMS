head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3ControlPossibleCodeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 操作可能コード一覧定数定義インタフェイス(WEB3ControlPossibleCodeListDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 劉江涛(sinocom) 新規作成
*/
package webbroker3.ipo.define;

/**
 * 操作可能コード一覧 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3ControlPossibleCodeListDef
{

    /**
     * 1：申告　@　@
     */
    public final static String DAMAND = "1";

    /**
     * 2：訂正・取消　@ 　@　@　@ 　@　@
     */
    public final static String CHANGE_CANCEL = "2";
    
    /**
     * 3：購入申込　@　@　@　@　@
     */
    public final static String PURCHASE_APPLICATION = "3";
    
    /**
     * 4：辞退　@　@　@
     */
    public final static String DECLINE = "4";
}@
