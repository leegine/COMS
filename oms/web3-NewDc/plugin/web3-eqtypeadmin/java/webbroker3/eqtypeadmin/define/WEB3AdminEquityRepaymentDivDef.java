head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityRepaymentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 弁済区分定数定義インタフェイス(WEB3AdminEquityRepaymentDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 張騰宇(中訊) 新規作成 仕様変更モデルNo.171
*/
package webbroker3.eqtypeadmin.define;

/**
 * 弁済区分 定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityRepaymentDivDef
{
    /**
     * "制度信用"
     */
    public static final String REPAYMENT_DIV_MARGIN_SYS = "制度信用";

    /**
     * "一般信用"
     */
    public static final String REPAYMENT_DIV_MARGIN_GEN = "一般信用";
}
@
