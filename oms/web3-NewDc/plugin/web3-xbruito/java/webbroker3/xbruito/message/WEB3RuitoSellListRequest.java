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
filename	WEB3RuitoSellListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約一覧リクエストクラス(WEB3RuitoSellListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累投解約一覧リクエスト<BR>
 */
public class WEB3RuitoSellListRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40762C49020D
     */
    public WEB3RuitoSellListRequest()
    {

    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投解約可能一覧照会レスポンスを作成する<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762AA30038
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoSellListResponse(this);
    }
}
@
