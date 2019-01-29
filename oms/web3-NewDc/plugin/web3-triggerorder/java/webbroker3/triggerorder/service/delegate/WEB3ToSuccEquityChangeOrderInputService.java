head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式注文訂正入力サービス(WEB3ToSuccEquityChangeOrderInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;


/**
 * (（連続）現物株式注文訂正入力サービス)<BR>
 * （連続）現物株式注文訂正入力サービスインタフェース。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3ToSuccEquityChangeOrderInputService extends WEB3EquityChangeOrderInputService 
{
    
    /**
     * （連続）現物株式注文訂正入力サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ。
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4337C11600FC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
