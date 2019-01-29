head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）OP返済注文サービス(WEB3ToSuccOptionSettleContractOrderService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 楊夫志(中訊) 新規作成 モデル283
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;

/**
 * (（連続）OP返済注文サービス)<BR>
 * （連続）オプション返済注文サービスインターフェイス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public interface WEB3ToSuccOptionSettleContractOrderService extends WEB3OptionSettleContractOrderService
{

    /**
     * （連続）オプション返済サービス処理を実施する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A97A100291
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
