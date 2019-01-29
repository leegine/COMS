head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資売買注文通知サービスインターフェイス(WEB3RuitoTradeOrderNotifyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 杜森 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;

/**
 * 累積投資売買注文通知サービスインターフェイス<BR>
 */
public interface WEB3RuitoTradeOrderNotifyService extends WEB3BackBusinessService 
{
   
   /**
    * 累積投資売買注文通知サービス処理を実施する。<BR>
    * @@param l_request - リクエストデータ <BR>
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 405A4DC400D9
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
