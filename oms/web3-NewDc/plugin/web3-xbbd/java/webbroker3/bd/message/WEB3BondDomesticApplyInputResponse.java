head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募入力レスポンス(WEB3BondDomesticApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;


/**
 * (国内債券応募入力レスポンス)<BR>
 * 国内債券応募入力レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

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
     * (利率)<BR>
     * 利率<BR>
     */
    public String coupon;

    /**
     * (利率(課税後))<BR>
     * 利率(課税後)<BR>
     */
    public String couponAfterTax;

    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (利払日1)<BR>
     * 利払日1<BR>
     */
    public String couponPaymentDate1;

    /**
     * (利払日2)<BR>
     * 利払日2<BR>
     */
    public String couponPaymentDate2;

    /**
     * (応募単価)<BR>
     * 応募単価<BR>
     */
    public String applyPrice;

    /**
     * (申込単位)<BR>
     * 申込単位<BR>
     */
    public String applyUnit;

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
     * (目論見書閲覧チェック結果)<BR>
     * 目論見書閲覧チェック結果<BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;

    /**
     * (国内債券応募入力レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46665EAA005B
     */
    public WEB3BondDomesticApplyInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondDomesticApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
