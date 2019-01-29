head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者パスワードロック解除サービス(WEB3AdminMCAdminPwdLockCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;


/**
 * (管理者メニュー制御管理者パスワードロック解除サービス)<BR>
 * 管理者メニュー制御管理者パスワードロック解除サービスインタフェイス<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public interface WEB3AdminMCAdminPwdLockCancelService extends WEB3BusinessService 
{
    
    /**
     * 管理者パスワードロック解除処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DE48903E4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
