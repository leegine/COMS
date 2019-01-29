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
filename	WEB3RuitoSellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約完了レスポンスクラス(WEB3RuitoSellCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累投解約完了レスポンス<BR>
 */
public class WEB3RuitoSellCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3RuitoSellCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * 更新時間<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * 識別番号<BR>
     */
    public String orderActionId;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C0E034B
     */
    public WEB3RuitoSellCompleteResponse()
    {

    }
}
@
