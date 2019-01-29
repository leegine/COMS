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
filename	WEB3RuitoBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文入力レスポンスクラス(WEB3RuitoBuyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累積投資買付注文入力レスポンス<BR>
 */
public class WEB3RuitoBuyInputResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_buy_input";
    
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
    protected WEB3RuitoBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
    
    /**
     * 銘柄コード名称一覧<BR>
     */
    public WEB3RuitoProductCodeNameUnit[] ruitoProductCodeNames;

    /**
     * 買付可能金額<BR>
     */
    public String tradingPower;

    /**
     * 指定方法@一覧<BR>
     * 3：金額、4：口数<BR>
     */
    public String[] specifyDivList;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CD10232
     */
    public WEB3RuitoBuyInputResponse()
    {
    }
}
@
