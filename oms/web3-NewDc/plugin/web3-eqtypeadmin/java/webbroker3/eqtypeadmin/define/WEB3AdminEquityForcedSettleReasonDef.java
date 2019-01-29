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
filename	WEB3AdminEquityForcedSettleReasonDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済理由定数定義インタフェイス(WEB3AdminEquityForcedSettleReasonDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 張騰宇(中訊) 新規作成 仕様変更モデルNo.185
*/
package webbroker3.eqtypeadmin.define;

/**
 * 強制決済理由 定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleReasonDef
{
    /**
     * 90：　@追証(第一)期日超過<BR>
     * 追証(第一)期日超過　@・・・ 保証金維持率割（オンライン開始前・軽度）<BR>
     * 　@or 保証金維持率割（オンライン開始前・重度）のこと。<BR>
     */
    public final static String ADDDEPOSIT_FIRST_DATE_EXCESS = "90";

    /**
     * 91：　@追証(第二)期日超過<BR>
     * 追証(第二)期日超過　@・・・ 保証金維持率割（場間） <BR>
     * 　@or 保証金維持率割（オンライン開始前・法@定）のこと。<BR>
     */
    public final static String ADDDEPOSIT_SECOND_DATE_EXCESS = "91";
}
@
