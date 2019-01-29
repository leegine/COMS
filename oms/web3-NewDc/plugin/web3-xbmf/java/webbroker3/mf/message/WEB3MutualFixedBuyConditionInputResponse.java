head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録入力レスポンス(WEB3MutualFixedBuyConditionInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 安陽(中訊) 新規作成 モデル605
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (投信定時定額買付銘柄条件登録入力レスポンス)<BR>
 * 投信定時定額買付銘柄条件登録入力レスポンス
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionInputResponse extends WEB3MutualFixedBuyConditionCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101432L;

    /**
     * (投信定時定額買付銘柄条件登録入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 484CB98E02ED
     */
    public WEB3MutualFixedBuyConditionInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3MutualFixedBuyConditionInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
