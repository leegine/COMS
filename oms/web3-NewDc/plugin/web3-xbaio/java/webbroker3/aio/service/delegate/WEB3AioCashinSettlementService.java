head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSettlementService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済依頼サービスインターフェイス(WEB3AioCashinSettlementService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 屈陽 (中訊) 新規作成
                   2004/10/23 于美麗 (中訊) レビュー
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (決済依頼サービス)<BR>
 * 決済依頼サービスインターフェイス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashinSettlementService extends WEB3BusinessService 
{
    
    /**
     * 決済依頼サービスを実施する。
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FE653202F5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
