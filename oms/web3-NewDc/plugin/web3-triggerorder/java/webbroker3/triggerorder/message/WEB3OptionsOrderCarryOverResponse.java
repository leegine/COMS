head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionsOrderCarryOverResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文繰越レスポンス(WEB3OptionsOrderCarryOverResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 鄒鋭 (中訊) 新規作成
Revesion History : 2006/06/21 孟亜南 (中訊) 仕様変更 モデル 670
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3BackRequest;


/**
 * (株価指数オプション注文繰越レスポンス)<BR>
 * 株価指数オプション注文繰越レスポンスクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsOrderCarryOverResponse extends WEB3IfoOrderCarryOverMainResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderCarryOver";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111040L;

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsOrderCarryOverResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }

}
@
