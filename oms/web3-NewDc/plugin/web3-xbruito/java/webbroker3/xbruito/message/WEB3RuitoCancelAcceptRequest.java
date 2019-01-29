head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資取消受付リクエストクラス(WEB3RuitoCancelAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 累積投資取消受付リクエストクラス<BR>
 */
public class WEB3RuitoCancelAcceptRequest extends WEB3BackRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C6D033C
     */
    public WEB3RuitoCancelAcceptRequest()
    {
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投取消受付レスポンスオブジェクトを生成して返す。<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 408514C7032B
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3RuitoCancelAcceptResponse(this);

    }
}
@
