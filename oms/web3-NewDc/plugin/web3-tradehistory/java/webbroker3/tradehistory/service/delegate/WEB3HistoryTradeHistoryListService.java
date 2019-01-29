head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧サービス(WEB3HistoryTradeHistoryListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 範慧琴 (中訊) 新規作成
*/

package webbroker3.tradehistory.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (取引履歴一覧サービス)<BR>
 * 取引履歴一覧サービスインタフェイス<BR>
 */
public interface WEB3HistoryTradeHistoryListService extends WEB3BusinessService 
{
    
    /**
     * 取引履歴一覧画面表示処理を行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413C2E7D00A9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
