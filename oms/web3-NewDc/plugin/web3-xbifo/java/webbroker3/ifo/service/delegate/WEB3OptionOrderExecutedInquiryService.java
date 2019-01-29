head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecutedInquiryService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//ソース ファ@イル: C:\\web3ModelDetalRelease\\srcGen\\webbroker3\\ifo\\service\\delegate\\WEB3OptionOrderExecutedInquiryService.java

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (OP注文約定照会サービス)<BR>
 * 株価指数オプション注文約定照会サービスインターフェイス<BR>
 */
public interface WEB3OptionOrderExecutedInquiryService extends WEB3BusinessService 
{
    
    /**
     * 株価指数オプション注文約定照会サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057FB43034E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
