head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・為替登録完了レスポンス (WEB3AdminTMExchangeRegistCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・為替登録完了レスポンス)<BR>
 * 管理者・為替登録完了レスポンスクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_tm_exchange_regist_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200610101425L;

    /**
     * コンストラクタ
     */
    public WEB3AdminTMExchangeRegistCompleteResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTMExchangeRegistCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
