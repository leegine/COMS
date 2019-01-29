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
filename	WEB3AdminEquityProductCondReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者株式銘柄条件照会サービスインタフェイス)
                       (WEB3AdminEquityProductCondReferenceService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * （管理者株式銘柄条件照会サービスインタフェイス）<BR>
 * <BR>
 * 管理者株式銘柄条件照会サービスインタフェイス<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityProductCondReferenceService interface<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public interface WEB3AdminEquityProductCondReferenceService extends WEB3BusinessService
{

   /**
    * 株式銘柄条件照会処理を行う。<BR>
    * <BR>
    * リクエストデータの型により、<BR>
    * 以下のメソッドを呼び分ける。<BR>
    * <BR>
    * ○管理者・株式銘柄条件照会銘柄入力リクエストの場合<BR>
    * 　@this.get銘柄入力画面()をコールする。<BR>
    * <BR>
    * ○管理者・株式銘柄条件照会リクエストの場合<BR>
    * 　@this.get照会画面()をコールする。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Execute WEB3AdminEquityProductCondReferenceService process<BR>
    * <BR>
    * Call one of the following methods based on the type of the argument,
    * l_request.<BR>
    * <BR>
    * ○If WEB3AdminPMProductCondRefInputRequest<BR>
    * 　@Call this.getProductInputScreen()<BR>
    * <BR>
    * ○If WEB3AdminPMProductCondRefReferenceRequest<BR>
    * 　@Call this.getReferenceScreen()<BR>
    * <BR>
    * @@param l_request - （リクエスト）<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 4190C77C0258
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
