head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者株式注文約定照会Unit (WEB3AdminOREquityOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/08/30 張騰宇(中訊) モデル 057
Revision History : 2006/11/20 黄建(中訊) モデル 081
Revision History : 2006/12/19 張騰宇 (中訊) 仕様変更・モデル087
Revision History : 2007/06/05 柴双紅 (中訊) 仕様変更・モデル099
Revision History : 2008/01/23 トウ鋒鋼 (中訊) 仕様変更・モデル112
*/
package webbroker3.adminorderexecinquiry.message;

/**
 * (管理者株式注文約定照会Unit)<BR>
 * <BR>
 * 管理者株式注文約定照会Unitクラス<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon
{
    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     */
    public String productName;

    /**
     * (市場コード)<BR>
     * <BR>
     * 市場コード<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (口座区分)<BR>
     * <BR>
     * 0：一般　@1：特定　@5：ストックオプション<BR>
     * <BR>
     */
    public String taxType = null;

    /**
     * (弁済区分)<BR>
     * <BR>
     * 弁済区分<BR>
     * <BR>
     * 1：　@制度信用<BR>
     * 2：　@一般信用 <BR>
     * <BR>
     * ※信用注文の場合セットする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * repaymentDiv<BR>
     * <BR>
     * 1: Def.REPAYMENT_DIV_MARGIN_SYS<BR>
     * 2: Def.REPAYMENT_DIV_MARGIN_GEN<BR>
     * ※set if marginOrder<BR>
     * <BR>
     */
    public String repaymentDiv = null;

    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値<BR>
     * 7:成行残数取消<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * priceCondType<BR>
     * 0: Def.DEFAULT　@1: Def.PRESENT_VALUE_LIMIT_PRICE_ORDER　@<BR>
     * 3: Def.PRIORITY_LIMIT_PRICE_ORDER<BR>　@5: Def.PARTIALLY_LIMIT_PRICE_ORDER<BR>
     * 7: Def.PARTIALLY_CANCEL_ORDER<BR>
     * <BR>
     */
    public String priceCondType;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     * <BR>
     */
    public String wlimitExecCondType = null;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効　@2：ストップ注文失効済<BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     * <BR>
     */
    public String wlimitEnableStatusDiv = null;

    /**
     * (強制決済理由)<BR>
     * 強制決済理由<BR>
     * <BR>
     * 0：　@決済期日到来<BR>
     * 1：　@保証金維持率割（オンライン開始前・軽度）<BR>
     * 2：　@保証金維持率割（オンライン開始前・重度）<BR>
     * 3：　@保証金維持率割（場間）<BR>
     * 9：　@手動強制決済<BR>
     * <BR>
     * ※強制決済注文でない場合はnullがセットされる。<BR>
     */
    public String forcedSettleReason;

    /**
     * (強制失効区分)<BR>
     * 強制失効区分<BR>
     * <BR>
     * 0：　@オープン<BR>
     * 1：　@強制失効済<BR>
     */
    public String forcedLapseDiv;

    /**
     * (出来入力可能フラグ)<BR>
     * 出来入力可能フラグ<BR>
     * <BR>
     * true：出来入力可能　@　@false：出来入力不可
     */
    public boolean inputExecFlag;

    /**
     * (出来取消可能フラグ)<BR>
     * 出来取消可能フラグ<BR>
     * <BR>
     * true：出来取消可能　@　@false：出来取消不可
     */
    public boolean cancelExecFlag;

    /**
     * (管理者株式注文約定照会Unit)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * WEB3AdminOREquityOrderExecutionRefUnit<BR>
     * <BR>
     * constructor<BR>
     * <BR>
     * webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefUnit
     * @@roseuid 41B40F2303A4
     */
    public WEB3AdminOREquityOrderExecutionRefUnit()
    {

    }
}
@
