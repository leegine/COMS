head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建注文サービス(WEB3ToSuccFuturesOpenContractService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 于瀟(中訊) 新規作成モデル257
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;


/**
 * (（連続）先物新規建注文サービス)<BR>
 * （連続）先物新規建注文サービスインターフェイス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public interface WEB3ToSuccFuturesOpenContractService extends WEB3FuturesOpenContractService
{

    /**
     * （連続）先物新規建注文サービス処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A98197014C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
