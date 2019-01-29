head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFCancelAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF取消受付リクエストクラス(WEB3RuitoMRFCancelAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 累積投資MRF取消受付リクエストクラス<BR>
 */
public class WEB3RuitoMRFCancelAcceptRequest extends WEB3BackRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_mrf_cancel_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922B9101C5
     */
    public WEB3RuitoMRFCancelAcceptRequest()
    {

    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投MRF取消受付レスポンスオブジェクトを生成して返す。<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 408624BB010F
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3RuitoMRFCancelAcceptResponse(this);
    }
}
@
