head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRequestNumberService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設識別コード採番サービス(WEB3AccOpenRequestNumberService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (口座開設識別コード採番サービス)<BR>
 * 口座開設識別コード採番サービスインタフェイス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public interface WEB3AccOpenRequestNumberService extends Service 
{
    
    /**
     * (get新規識別コード)<BR>
     * 口座開設見込客の識別コードを自動採番する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード。
     * @@return String
     * @@roseuid 418720D9037E
     */
    public String getNewRequestNumber(String l_strInstitutionCode) throws WEB3BaseException;
}
@
