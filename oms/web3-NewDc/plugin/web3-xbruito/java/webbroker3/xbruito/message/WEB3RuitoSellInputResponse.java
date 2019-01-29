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
filename	WEB3RuitoSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約入力レスポンスクラス(WEB3RuitoSellInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累投解約入力レスポンス<BR>
 */
public class WEB3RuitoSellInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_input";
    
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
    protected WEB3RuitoSellInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * 銘柄名<BR>
     * 累積投資のファ@ンド名。<BR>
     */
    public String ruitoProductName;

    /**
     * 解約可能残高<BR>
     */
    public String ruitoSellPossBalance;

    /**
     * 指定方法@一覧<BR>
     * 2：全部、3：金額指定、4：口数指定<BR>
     */
    public String[] specifyDivList;

    /**
     * 受渡方法@一覧<BR>
     * 1：銀行振込み、2：証券口座入金<BR>
     */
    public String[] deliveryDivList;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C27031C
     */
    public WEB3RuitoSellInputResponse()
    {

    }
}
@
