head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeOpenContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物訂正新規建サービス（WEB3ToSuccFuturesChangeOpenContractService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 トウ鋒鋼 (中訊) 新規作成 モデルNo.269
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (（連続）先物訂正新規建サービス)<BR>
 * （連続）先物訂正新規建サービスインターフェイス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesChangeOpenContractService extends WEB3ToSuccFuturesOpenContractService
{

    /**
     * （連続）先物訂正新規建サービス処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A982930058
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
