head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票作成サービス(WEB3AccOpenVoucherCreatedService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 張学剛 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.common.WEB3BaseException;

/**
 * (口座開設伝票作成サービス)<BR>
 * 口座開設伝票作成サービスインタフェイス<BR>
 * @@author 張学剛
 * @@version 1.0 
 */
public interface WEB3AccOpenVoucherCreatedService extends Service 
{
    
    /**
     * (create口座開設伝票)<BR>
     * 指定の口座開設見込客の情報より、口座開設伝票を作成する。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return String[]
     * @@roseuid 419C58240293
     */
    public String[] createAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException;
    
    /**
     * (delete口座開設伝票)<BR>
     * 指定の口座開設見込客に関連する、口座開設伝票を削除する。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return String[]
     * @@roseuid 419C582402A3
     */
    public String[] deleteAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException;
    
    /**
     * (get変更不可項目一覧)<BR>
     * 作成済の伝票について、確定済の項目を変更させないための項目名一覧を<BR>
     * 取得する。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return String[]
     * @@roseuid 419C582402A5
     */
    public String[] getChangedImpossibleItemList(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException;
}
@
