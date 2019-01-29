head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionSettleContractOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オプション返済注文サービス(WEB3OptionSettleContractOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 張宝楠 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (オプション返済注文サービス)<BR>
 * <BR>
 * オプション返済注文サービスインタフェース<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3OptionSettleContractOrderService extends WEB3BusinessService 
{
    
    /**
     * 株価指数オプション返済注文サービス処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40551343030C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
