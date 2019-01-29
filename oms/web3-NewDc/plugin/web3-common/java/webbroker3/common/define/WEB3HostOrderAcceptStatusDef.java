head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostOrderAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブル注文受付結果定数定義インタフェイス(WEB3HostOrderAcceptStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * キューテーブル注文受付結果定数定義インタフェイス。<BR>
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3HostOrderAcceptStatusDef
{

    /**
     * 注文受付完了。<BR>
     */
    public static final String ORDER_ACCEPT_COMPLETE = "1";

    /**
     * エラー。<BR>
     */
    public static final String ORDER_ACCEPT_ERROR = "2";
}
@
