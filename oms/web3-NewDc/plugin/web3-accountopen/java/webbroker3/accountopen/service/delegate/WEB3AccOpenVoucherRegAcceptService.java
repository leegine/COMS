head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付サービス(WEB3AccOpenVoucherRegAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (口座開設伝票登録受付サービス)<BR>
 * 口座開設伝票登録受付サービスインタフェイス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3AccOpenVoucherRegAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * 口座開設伝票登録受付処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A44DA8024A
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
