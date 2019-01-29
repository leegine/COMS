head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransferAbleAmtDisplayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替可能額表示サービス(WEB3FXTransferAbleAmtDisplayService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 張騰宇 (中訊) 新規作成 仕様変更・モデル1174
Revision History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1210
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (振替可能額表示サービス)<BR>
 * 振替可能額表示サービスインターフェイス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3FXTransferAbleAmtDisplayService extends Service
{
    /**
     * (getFXから振替可能額（チェックなし）)<BR>
     * FXから振替可能額（チェックなし）を取得する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@return WEB3FXTransferAbleAmtUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit[] getFXTransferAbleAmtNoCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException;

    /**
     * (getFXから振替可能額（チェックあり）)<BR>
     * FXから振替可能額（チェックあり）を取得する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@param l_strTransferAmount - (振替金額)<BR>
     * 振替金額<BR>
     * @@param l_strCourseDiv - (コース区分)<BR>
     * コース区分<BR>
     * @@return WEB3FXTransferAbleAmtUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit getFXTransferAbleAmtCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams,
        String l_strTransferAmount, String l_strCourseDiv)
        throws WEB3BaseException;
}
@
