head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理入力レスポンス(WEB3AdminBondDomesticRecruitLimitManageInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.215
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者国内債券部店別応募枠管理入力レスポンス)<BR>
 * 管理者国内債券部店別応募枠管理入力レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageInputResponse
    extends WEB3AdminBondDomesticRecruitLimitManageCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231848L;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (回号コード)<BR>
     * 回号コード<BR>
     */
    public String productIssueCode;

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
     * (発行年月日)<BR>
     * 発行年月日<BR>
     */
    public Date issueDate;

    /**
     * (発行価格)<BR>
     * 発行価格<BR>
     */
    public String issuePrice;

    /**
     * (年利率)<BR>
     * 年利率<BR>
     */
    public String annualRate;

    /**
     * (利払月日１)<BR>
     * 利払月日１<BR>
     */
    public String couponPaymentDate1;

    /**
     * (利払月日２)<BR>
     * 利払月日２<BR>
     */
    public String couponPaymentDate2;

    /**
     * (償還年月日)<BR>
     * 償還年月日<BR>
     */
    public Date maturityDate;

    /**
     * (応募開始日時)<BR>
     * 応募開始日時<BR>
     */
    public Date recruitStartDate;

    /**
     * (応募終了日)<BR>
     * 応募終了日<BR>
     */
    public Date recruitEndDate;

    /**
     * (管理者国内債券部店別応募枠管理入力レスポンス)<BR>
     * コンストラクタ
     * @@roseuid 4684B9D7021E
     */
    public WEB3AdminBondDomesticRecruitLimitManageInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondDomesticRecruitLimitManageInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
