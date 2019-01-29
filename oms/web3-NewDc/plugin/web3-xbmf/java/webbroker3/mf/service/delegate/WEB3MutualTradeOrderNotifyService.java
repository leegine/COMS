head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託売買注文通知サービスインターフェイス(WEB3MutualTradeOrderNotifyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/24 王蘭芬 (中訊) レビュー
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (投信売買注文通知サービス)
 * 投資信託売買注文通知サービスインターフェイス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3MutualTradeOrderNotifyService 
    extends WEB3BackBusinessService 
{
    /**
     * 投資信託売買注文通知サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40567C3B02A9
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
