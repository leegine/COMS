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
filename	WEB3FeqAcceptDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付区分 定数定義インタフェイス(WEB3FeqAcceptDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 受付区分(管理者外国株式注文受付取消認証入力リクエスト用) 定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqAcceptDivDef
{
    /**
     * 0：注文受付中
     */
    public static final String ORDER_ACCEPTING = "0";
    
    /**
     * 1：訂正取消中
     */
    public static final String CHANGING_CANCELING = "1";

    /**
     * 2：注文受付済
     */
    public static final String ORDER_ACCEPTED = "2";
}
@
