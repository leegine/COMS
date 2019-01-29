head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録共通レスポンス(WEB3MutualFixedBuyConditionCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 安陽(中訊) 新規作成 モデル605
Revision History : 2008/07/17 安陽(中訊) 仕様変更 モデル617
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (投信定時定額買付銘柄条件登録共通レスポンス)<BR>
 * 投信定時定額買付銘柄条件登録共通レスポンス<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_common";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101420L;

    /**
     * (投信定時定額買付積立登録内容)<BR>
     * 投信定時定額買付積立登録内容<BR>
     */
    public WEB3MutualFixedBuyConditionUnit[] conditionList;

    /**
     * (投信定時定額買付新規追加内容)<BR>
     * 投信定時定額買付新規追加<BR>
     */
    public WEB3MutualFixedBuyConditionUnit[] addConditionList;

    /**
     * (投信定時定額引落口座)<BR>
     * 投信定時定額引落口座<BR>
     */
    public WEB3MutualFixedBuyAccountInfo acountInfo;

    /**
     * (投信定時定額買付金額合計)<BR>
     * 投信定時定額買付金額合計<BR>
     */
    public WEB3MutualFixedBuyTotalUnit[] totalList;

    /**
     * (投信銘柄カテゴリー一覧)<BR>
     * 投信銘柄カテゴリー一覧<BR>
     */
    public WEB3MutualProductCategoryUnit[] categoryList;

    /**
     * (投信定時定額買付銘柄条件登録共通レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 484D05730287
     */
    public WEB3MutualFixedBuyConditionCommonResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3MutualFixedBuyConditionCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
