head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderBuyInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式買付注文入力サービス(WEB3ToSuccEquityOrderBuyInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;


/**
 * (（連続）現物株式買付注文入力サービス)<BR>
 * （連続）現物株式買付注文入力サービスインタフェース。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3ToSuccEquityOrderBuyInputService extends WEB3EquityOrderBuyInputService 
{
    
    /**
     * （連続）現物株式買付注文入力サービス処理を実施する。
     * @@param l_request - ()<BR>
     * リクエストデータ。
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431C28650152
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
