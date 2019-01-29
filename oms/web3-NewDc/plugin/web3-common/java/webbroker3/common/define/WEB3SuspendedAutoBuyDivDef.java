head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SuspendedAutoBuyDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 自動買付保留区分(WEB3SuspendedAutoBuyDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 自動買付保留区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3SuspendedAutoBuyDivDef
{

    /**
     * 0：自動買付を行う
     */
    public final static String DO_AUTOBUY  = "0";

    /**
     * 1：自動買付を保留する
     */
    public final static String SUSPEND_AUTOBUY  = "1";

}@
