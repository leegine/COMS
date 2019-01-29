head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeOpenContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物訂正新規建入力サービス（WEB3ToSuccFuturesChangeOpenContractInputService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/17 トウ鋒鋼 (中訊) 新規作成 モデルNo.261
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractInputService;

/**
 * (（連続）先物訂正新規建入力サービス)<BR>
 * （連続）先物訂正新規建入力サービスインターフェイス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesChangeOpenContractInputService
    extends WEB3FuturesChangeOpenContractInputService
{

    /**
     * （連続）株価指数先物訂正新規建入力サービス処理を実施する。 <BR>
     * <BR>
     * this.create入力画面をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9821D0272
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
