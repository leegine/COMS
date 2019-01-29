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
filename	WEB3EquitySellOrderInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文入力サービス(WEB3EquitySellOrderInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （現物株式売付注文入力サービス）。<BR>
 * <BR>
 * 現物株式売付注文入力サービスインターフェイス
 * @@version 1.0
 */
public interface WEB3EquitySellOrderInputService extends WEB3BusinessService 
{   
   /**
    * 現物株式売付注文入力サービスを実施する。
    * @@param l_request - リクエストデータ
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 40628BEF02CA
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
       throws WEB3BaseException;
}
@
