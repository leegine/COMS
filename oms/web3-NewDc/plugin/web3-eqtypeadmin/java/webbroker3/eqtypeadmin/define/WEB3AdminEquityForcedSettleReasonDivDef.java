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
filename	WEB3AdminEquityForcedSettleReasonDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済理由定数定義インタフェイス(WEB3AdminEquityForcedSettleReasonDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 張騰宇(中訊) 新規作成 仕様変更モデルNo.171
Revision History : 2008/11/07 姜丹(中訊) 仕様変更モデルNo.211
*/
package webbroker3.eqtypeadmin.define;

/**
 * 強制決済理由 定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleReasonDivDef
{
    /**
     * "決済期日到来"
     */
    public final static String FIXED_DATE_COMING = "決済期日到来";

    /**
     * "30%割れ7日以上"
     */
    public final static String ADDDEPOSIT_FIRST_DATE_EXCESS = "30%割れ7日以上";

    /**
     * "20%割れ期日超過"
     */
    public final static String ADDDEPOSIT_SECOND_DATE_EXCESS = "20%割れ期日超過";

    /**
     * "手動強制決済"
     */
    public final static String MANUAL_FORCED_SETTLE = "手動強制決済";
}
@
