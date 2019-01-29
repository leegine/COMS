head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション出来通知サービス(WEB3OptionOrderExecNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (OP出来通知サービス)<BR>
 * <BR>
 * 株価指数オプション出来通知サービスインターフェイス<BR>
 *                                                                     
 * @@author 鄒鋭
 * @@version 1.0
 */
public interface WEB3OptionOrderExecNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * 株価指数オプション出来通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OPサービス）オプション出来通知」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057BDE903DA
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
