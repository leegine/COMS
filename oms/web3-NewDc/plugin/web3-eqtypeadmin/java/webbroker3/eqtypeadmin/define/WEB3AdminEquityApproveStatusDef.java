head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityApproveStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 承認状態定数定義インタフェイス(WEB3AdminEquityApproveStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 張騰宇(中訊) 新規作成 仕様変更モデルNo.171
Revision History : 2008/11/07 姜丹(中訊) 仕様変更モデルNo.211
*/
package webbroker3.eqtypeadmin.define;

/**
 * 承認状態 定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityApproveStatusDef
{
    /**
     * "未承認"
     */
    public final static String UNAPPROVED = "未承認";

    /**
     * "承認済"
     */
    public final static String APPROVED = "承認済";

    /**
     * "否認済"
     */
    public final static String NON_APPROVED = "否認済";

    /**
     * "手動承認済"
     */
    public final static String MANUAL_APPROVED = "手動承認済";

}
@
