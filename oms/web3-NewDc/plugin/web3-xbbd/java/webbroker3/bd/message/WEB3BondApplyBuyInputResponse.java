head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.43.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付入力レスポンス(WEB3BondApplyBuyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
                       : 2006/09/29 張騰宇 (中訊) モデル 098
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券応募/買付入力レスポンス)<BR>
 * 債券応募/買付入力レスポンス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_applyBuyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609051106L;

    /**
     * (買付可能額)<BR>
     * 買付可能額<BR>
     */
    public String tradingPower;

    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;

    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String bondCategCode;

    /**
     * (S&P)<BR>
     * S&P<BR>
     */
    public String sAndP;

    /**
     * (Moody's)<BR>
     * Moody's<BR>
     */
    public String moodys;

    /**
     * (利率)<BR>
     * 利率<BR>
     */
    public String coupon;

    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (利払日1)<BR>
     * 利払日1<BR>
     */
    public String interestPaymentDay1;

    /**
     * (利払日2)<BR>
     * 利払日2<BR>
     */
    public String interestPaymentDay2;

    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;

    /**
     * (申込単位)<BR>
     * 申込単位<BR>
     */
    public String tradeUnit;

    /**
     * (最低申込数量)<BR>
     * 最低申込数量<BR>
     */
    public String minOrderQuantity;

    /**
     * (最高申込数量)<BR>
     * 最高申込数量<BR>
     */
    public String maxOrderQuantity;

    /**
     * (応募開始日)<BR>
     * 応募開始日<BR>
     */
    public Date recruitStartDate;

    /**
     * (応募終了日)<BR>
     * 応募終了日<BR>
     */
    public Date recruitEndDate;

    /**
     * (買付単価)<BR>
     * 買付単価<BR>
     */
    public String buyPrice;

    /**
     * (発行日)<BR>
     * 発行日<BR>
     */
    public Date issueDate;

    /**
     * (償還日)<BR>
     * 償還日<BR>
     */
    public Date maturityDate;

    /**
     * (買付基準為替レート)<BR>
     * 買付基準為替レート<BR>
     */
    public String buyBaseFxRate;

    /**
     * (決済区分一覧)<BR>
     * 決済区分一覧<BR>
     * <BR>
     * 1：円貨<BR>
     * 2：外貨<BR>
     */
    public String[] settleDivList;

    /**
     * (仕入時の為替レート)<BR>
     * 仕入時の為替レート<BR>
     */
    public String fxRateAtStock;

    /**
     * (債券応募/買付入力レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44C59B670129
     */
    public WEB3BondApplyBuyInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondApplyBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
