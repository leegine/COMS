head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文履歴照会レスポンス(WEB3OptionsOrderHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション注文履歴照会レスポンス)<BR>
 * 株価指数オプション注文履歴照会レスポンスクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsOrderHistoryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderHistory";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141405L;

    /**
     * 注文履歴一覧行
     */
    public WEB3OptionsChangeCancelHistoryGroup[] opChangeCancelHistoryGroups;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsOrderHistoryResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsOrderHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
