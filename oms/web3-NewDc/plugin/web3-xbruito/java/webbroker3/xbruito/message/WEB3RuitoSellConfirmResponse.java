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
filename	WEB3RuitoSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約確認レスポンスクラス(WEB3RuitoSellConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
                   2004/12/03 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累投解約確認レスポンス<BR>
 */
public class WEB3RuitoSellConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_confirm";
    
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
    protected WEB3RuitoSellConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * 確認時発注日<BR>
     * <BR>
     * 完了リクエストで送信する値を格納する。<BR>
     * （画面では非表示）<BR>
     */
    public Date checkDate;

    /**
     * 注文ID<BR>
     */
    public String orderId;

    
    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922BFB0157
     */
    public WEB3RuitoSellConfirmResponse()
    {

    }
}
@
