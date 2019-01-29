head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 取引明細サービス(WEB3HistoryTradeDetailService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 王敏 (中訊) 新規作成
*/
package webbroker3.tradehistory.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (取引明細サービス)<BR>
 * 取引明細サービスインタフェイス<BR>
 * @@author 王敏
 * @@version 1.0
 */
public interface WEB3HistoryTradeDetailService extends WEB3BusinessService 
{
    
    /**
     * 取引明細画面表示処理を行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D971D0342
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
