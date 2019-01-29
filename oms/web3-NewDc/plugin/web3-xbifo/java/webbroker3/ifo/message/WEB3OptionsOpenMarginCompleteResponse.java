head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文完了レスポンス(WEB3OptionsOpenMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション新規建注文完了レスポンス)<BR>
 * 株価指数オプション新規建注文完了レスポンスクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginCompleteResponse extends WEB3GenResponse
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141508L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginComplete";

    /**
     * 更新時間
     */
    public java.util.Date lastUpdatedTimestamp;

    /**
     * 識別番号<BR>    
     * 注文履歴ＩＤ<BR>
     */
    public String orderActionId;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsOpenMarginCompleteResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsOpenMarginCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
