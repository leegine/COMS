head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RestraintDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拘束金種別定数定義インタフェイス(WEB3RestraintDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/27 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 拘束金種別定数定義インタフェイス<BR>
 * （その他拘束金（仮拘束）テーブルの拘束金種別の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3RestraintDivDef
{
    /**
     * 1：投信乗換売却代金
     */
    public final static String MF_SWITCHING_SELL_AMOUNT  = "1";

    /**
     * 2：債券買付代金
     */
    public final static String BOND_BUY_AMOUNT = "2";
}
@
