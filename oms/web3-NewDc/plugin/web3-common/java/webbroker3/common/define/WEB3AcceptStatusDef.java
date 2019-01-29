head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AcceptStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 注文受付結果　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3AcceptStatusDef
{
    /**
     * 1 : 注文受付完了
     */
    public static final String OVER = "1";

    /**
     * 2 : エラー
     */
    public static final String ERROR = "2";

    /**
     * 3 : 前場受付時間外エラー
     */
    public static final String MORN_SESS_ACCEPT_OVERTIME_ERROR = "3";
}
@
