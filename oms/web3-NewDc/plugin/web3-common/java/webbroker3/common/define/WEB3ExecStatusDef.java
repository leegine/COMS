head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExecStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ExecStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 du-min(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 約定状態　@定数定義インタフェイス
 *
 * @@author du-min
 * @@version 1.0
 */
public interface WEB3ExecStatusDef
{

    /**
     * 1 : 約定中
     */
    public static final String EXECUTED_IN_PROCESS = "1";

    /**
     * 2 : 約定済
     */
    public static final String EXECUTED_COMPLETE = "2";

    /**
     * NULL : 初期値
     */
    public static final String INITIALIZE_PRICE = null;

}
@
