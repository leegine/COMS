head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecEndNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出来終了通知サービスインタフェース(WEB3EquityOrderExecEndNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.WEB3BaseException;

/**
 * （出来終了通知サービスインタフェース）。
 * @@version 1.0
 */
public interface WEB3EquityOrderExecEndNotifyService extends WEB3BackBusinessService 
{
   
   /**
    * 出来終了通知サービス処理を実施する<BR>
    * @@param l_request - リクエスト<BR>
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 4056EB8E03D5
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request)
       throws WEB3BaseException;
}
@
