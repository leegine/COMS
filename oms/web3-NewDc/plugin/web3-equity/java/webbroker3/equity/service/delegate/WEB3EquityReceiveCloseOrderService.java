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
filename	WEB3EquityReceiveCloseOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知サービス(WEB3EquityReceiveCloseOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 李綱 (中訊) 新規作成
                   2004/12/15 水落 (SRA) 残案件対応のため修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * （株式失効通知サービス）。<BR>
 * <BR>
 * 株式失効通知サービス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3EquityReceiveCloseOrderService extends WEB3BackBusinessService
{
   /**
    * (execute)<BR>
    * 失効処理を実施する。<BR>
    * @@param l_request - (l_request)<BR>
    * 失効通知リクエストオブジェクト<BR>
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 403C348C01D7
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
