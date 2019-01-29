head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文履歴照会レスポンスクラス(WEB3FuturesOrderHistoryResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 盧法@旭 (中訊) 新規作成
              001: 2004/08/06 王暁傑 (中訊) Review修正
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数先物注文履歴照会レスポンス)<BR>
 * 株価指数先物注文履歴照会レスポンスクラス
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesOrderHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_orderHistory";
        
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407221013L;
    /**
     * (注文履歴一覧行)
     */
    public webbroker3.ifo.message.WEB3FuturesChangeCancelHistoryGroup[] futChangeCancelHistoryGroups;
    
    /**
     * @@roseuid 40F7AE0E001F
     */
    public WEB3FuturesOrderHistoryResponse() 
    {
     
    }
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンス先物クトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesOrderHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
