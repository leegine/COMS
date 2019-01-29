head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続）株式注文サービス(WEB3ToSuccEquityOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderService;


/**
 * (（連続）株式注文サービス)<BR>
 * （連続）株式注文サービスインタフェース。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3ToSuccEquityOrderService extends WEB3EquityOrderService 
{
    
    /**
     * （連続）株式注文サービス処理を実施する。
     * @@param l_request - リクエストデータ。
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D3A0E009B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
