head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EnableIpoQuantityChangeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO購入申込数量変更 定数定義クラス(WEB3EnableIpoQuantityChangeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/18 孟東 (sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * IPO購入申込数量変更 定数を定義する。
 *
 * @@author 孟東 (sinocom)
 * @@version 1.0
 */
public interface WEB3EnableIpoQuantityChangeDef
{

    /**
     * 不可能
     */
    public final static String CANNOT_CHANGE = "0";

    /**
     * 可能
     */
    public final static String CAN_CHANGE = "1";
}

@
