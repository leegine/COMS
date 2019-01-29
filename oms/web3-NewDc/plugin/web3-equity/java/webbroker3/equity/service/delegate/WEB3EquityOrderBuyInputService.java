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
filename	WEB3EquityOrderBuyInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文買付入力サービスインターフェース (WEB3EquityOrderBuyInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/10 欒学峰 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （現物株式買付注文入力サービスインターフェイス）。
 * @@version 1.0
 */
public interface WEB3EquityOrderBuyInputService extends WEB3BusinessService 
{
   /**
    * (現物株式買付注文入力サービス処理を実施する) <BR>
    * @@param l_request - リクエストデータ
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 4062857B029B
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
