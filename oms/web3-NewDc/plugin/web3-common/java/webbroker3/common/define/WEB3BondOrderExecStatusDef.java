head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondOrderExecStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文約定区分定数定義インタフェイス(WEB3BondOrderExecStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 注文約定区分　@定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3BondOrderExecStatusDef
{
    /**
     * 0：未約定
     */
    public static final String UNEXECUTED = "0";

    /**
     * 1：約定済
     */
    public static final String EXECUTED = "1";

    /**
     * 2：取消済
     */
    public static final String CANCELED = "2";
}
@
