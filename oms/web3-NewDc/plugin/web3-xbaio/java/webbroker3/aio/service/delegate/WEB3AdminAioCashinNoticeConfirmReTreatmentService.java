head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知確認再処理サービスインターフェイス(WEB3AdminAioCashinNoticeConfirmReTreatmentService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/20 李俊 (中訊) 新規作成                             
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (入金通知確認再処理サービス)<BR>
 * 入金通知確認再処理サービスインターフェイス<BR>
 * 
 * @@author 李俊(中訊)
 * @@version 1.0
 */

public interface WEB3AdminAioCashinNoticeConfirmReTreatmentService extends WEB3BusinessService 
{
    
    /**
     * 入金通知確認再処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410755400349
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
