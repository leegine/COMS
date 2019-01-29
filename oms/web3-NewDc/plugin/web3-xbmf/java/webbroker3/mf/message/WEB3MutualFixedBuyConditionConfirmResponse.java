head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録確認レスポンス(WEB3MutualFixedBuyConditionConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 安陽(中訊) 新規作成 モデル605
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (投信定時定額買付銘柄条件登録確認レスポンス)<BR>
 * 投信定時定額買付銘柄条件登録確認レスポンス<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionConfirmResponse extends WEB3MutualFixedBuyConditionCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101428L;

    /**
     * (締切日アラート要求フラグ)<BR>
     * 締切日アラート要求フラグ<BR>
     * <BR>
     * true：アラート表示<BR>
     * false：アラート非表示<BR>
     * <BR>
     * 賞与締切日～通常締切日に賞与買付金額の値を変更する場合、<BR>
     * 　@反映は次回になるというアラートを表示する。<BR>
     */
    public boolean closingDateAlertFlag;

    /**
     * (投信定時定額買付銘柄条件登録確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 484CB9ED0204
     */
    public WEB3MutualFixedBuyConditionConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3MutualFixedBuyConditionConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
