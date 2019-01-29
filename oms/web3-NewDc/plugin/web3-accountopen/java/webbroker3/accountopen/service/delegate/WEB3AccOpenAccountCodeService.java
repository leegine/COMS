head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountCodeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設顧客コード採番サービス(WEB3AccOpenAccountCodeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/14 張騰宇 (中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * (口座開設顧客コード採番サービス)<BR>
 * 口座開設顧客コード採番サービスインタフェイス<BR>
 *   
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AccOpenAccountCodeService extends Service
{
    /**
     * (get新規顧客コード )<BR>
     * 口座開設見込客の顧客コードを自動採番する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountDiv - (顧客区分)<BR>
     * 顧客区分<BR>
     * 値は、DBレイアウト「口座開設顧客コード」を参照。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getNewAccountCode(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountDiv) 
        throws WEB3BaseException;
}
@
