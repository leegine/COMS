head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashinNoticeConfirmService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認サービスインターフェイス(WEB3AdminAioCashinNoticeConfirmService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成     
                   2004/10/22 黄建 (中訊) レビュー                        
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (入金連絡確認サービス)<BR>
 * 入金連絡確認サービスインターフェイス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public interface WEB3AdminAioCashinNoticeConfirmService extends WEB3BusinessService 
{
    
    /**
     * 入金連絡確認サービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 410755400349
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
