head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正新規建サービス(WEB3ToSuccMarginChangeOpenMarginService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17　@呉　@鈞(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (（連続）信用取引訂正新規建サービス)<BR>
 * （連続）信用取引訂正新規建サービスインターフェイス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public interface WEB3ToSuccMarginChangeOpenMarginService extends WEB3ToSuccMarginOpenMarginService 
{
    
    /**
     * （連続）信用取引訂正新規建サービス処理を実施する<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCCC401EA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
