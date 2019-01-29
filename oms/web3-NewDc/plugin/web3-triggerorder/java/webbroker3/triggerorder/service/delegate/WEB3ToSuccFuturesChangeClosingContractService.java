head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物訂正返済サービス（WEB3ToSuccFuturesChangeClosingContractService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 トウ鋒鋼 (中訊) 新規作成 モデルNo.270
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (（連続）先物訂正返済サービス)<BR>
 * （連続）先物訂正返済サービスインタフェース<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesChangeClosingContractService
    extends WEB3ToSuccFuturesSettleContractOrderService
{

    /**
     * （連続）先物訂正返済サービス処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9842D006C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
