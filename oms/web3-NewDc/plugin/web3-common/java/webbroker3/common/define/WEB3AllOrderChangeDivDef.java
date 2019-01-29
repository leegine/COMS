head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AllOrderChangeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 全訂正処理区分定数定義インタフェイス(WEB3AllOrderChangeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 全訂正処理区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3AllOrderChangeDivDef
{
    /**
     * 0：全訂正以外
     */
    public static final String EXCEPT_ALL_ORDER_CHANGE = "0";

    /**
     * 1：全訂正処理
     */
    public static final String ALL_ORDER_CHANGE = "1";
}
@
