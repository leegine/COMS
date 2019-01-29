head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正返済入力サービス(WEB3ToSuccMarginChangeCloseMarginInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 譚漢江(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;

/**
 * （（連続）信用取引訂正返済入力サービス)<BR>
 * （連続）信用取引訂正返済入力サービスインターフェイス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public interface WEB3ToSuccMarginChangeCloseMarginInputService extends WEB3MarginChangeCloseMarginInputService 
{
    
    /**
     * （連続）信用取引訂正返済入力サービス処理を実施する。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BBADD0304
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
