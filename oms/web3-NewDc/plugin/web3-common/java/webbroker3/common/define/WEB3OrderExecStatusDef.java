head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExecStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderExecStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 受渡代金　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderExecStatusDef
{
    /**
     * 0 : 未約定
     */
    public static final String UNEXECUTED = "0";

    /**
     * 1 : 一部約定
     */
    public static final String PARTIALLY_EXECUTED = "1";

    /**
     * 2 : 約定済
     */
    public static final String EXECUTED = "2";

    /**
     * 3 : 失効
     */
    public static final String CLOSE = "3";

    /**
     * 4 : 一部失効
     */
    public static final String PARTIALLY_CLOSE = "4";
}
@
