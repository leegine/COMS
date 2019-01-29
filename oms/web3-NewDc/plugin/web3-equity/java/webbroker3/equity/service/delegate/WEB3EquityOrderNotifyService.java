head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知サービス(WEB3EquityOrderNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 欒学峰 (中訊) 新規作成
                   2004/12/15 岡村和明(SAR) 残案件対応 Ｎｏ.０７５
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * （株式注文通知サービス）。<br>
 * <br>
 * 株式注文通知サービスインターフェース<br>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3EquityOrderNotifyService extends WEB3BackBusinessService 
{
   
   /**
    * （execute）。<br>
    * <br>
    * 現物株式注文通知サービス処理を実施する<br>
    * @@param l_request - リクエスト
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 40A16D45035E
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
