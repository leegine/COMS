head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenJudgeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査サービス (WEB3AdminAccOpenJudgeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 周捷(中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者口座開設審査サービス)<BR>
 * 管理者口座開設審査サービスインタフェイス<BR>
 *   
 * @@author 周捷
 * @@version 1.0
 */
public interface WEB3AdminAccOpenJudgeService extends WEB3BusinessService 
{
    
    /**
     * 口座開設審査処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AC3A005B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
