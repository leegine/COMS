head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IoDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3IoDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 出入区分　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3IoDivDef
{
    /**
     * 1:出
     */
    public static final String OUTPUT = "1";

    /**
     * 2:入（受渡金額の出入）
     */
    public static final String INPUT = "2";

}
@
