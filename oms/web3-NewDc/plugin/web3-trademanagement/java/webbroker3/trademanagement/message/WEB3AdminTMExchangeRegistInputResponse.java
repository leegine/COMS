head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・為替登録入力レスポンスクラス(WEB3AdminTMExchangeRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・為替登録入力レスポンスクラス)<BR>
 * 管理者・為替登録入力レスポンスクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_tm_exchange_regist_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200610101425L;

    /**
     * (為替情報一覧)<BR>
     * 為替情報の配列
     */
    public WEB3AdminTMExchangeInfoUnit[] exchangeInfoUnit;

    /**
     * コンストラクタ
     */
    public WEB3AdminTMExchangeRegistInputResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTMExchangeRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
