head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAccProductTradeStopChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者顧客銘柄別取引停止変更サービスインタフェイス
                        (WEB3AdminEquityAccProductTradeStopChangeService)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * （管理者顧客銘柄別取引停止変更サービスインタフェイス）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止変更サービスインタフェイス<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopChangeService interface<BR>
 * <BR>
 * @@author shruti
 * @@version 1.0
 */
public interface WEB3AdminEquityAccProductTradeStopChangeService extends WEB3BusinessService
{
   /**
    * 顧客銘柄別取引停止変更処理を行う。<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopChangeService process<BR>
    * <BR>
    * @@param l_request - （リクエスト）<BR>
    * <BR>
    * リクエスト<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 419969A30147
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
