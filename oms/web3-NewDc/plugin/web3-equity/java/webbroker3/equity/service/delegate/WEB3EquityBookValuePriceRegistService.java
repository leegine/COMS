head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookValuePriceRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式簿価単価登録サービスインタフェイス(WEB3EquityBookValuePriceRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * （現物株式簿価単価登録サービスインタフェイス）。<BR>
 * <BR>
 * 現物株式簿価単価登録サービスインタフェイス<BR>
 */
public interface WEB3EquityBookValuePriceRegistService extends WEB3BusinessService 
{
   
   /**
    * 現物株式簿価単価登録処理を行う。<BR>
    * @@param l_request - リクエスト<BR>
    * @@return WEB3GenResponse<BR>
    * @@throws WEB3BaseException<BR>
    * @@roseuid 41B9251F0378
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
