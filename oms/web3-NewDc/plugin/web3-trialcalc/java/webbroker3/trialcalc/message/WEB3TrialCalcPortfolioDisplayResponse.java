head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ表示レスポンス(WEB3TrialCalcPortfolioDisplayResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービスポートフォリオ表示レスポンス）<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayResponse<BR>
 * <BR>
 * 計算サービスポートフォリオサービス（ポートフォリオ表示）のレスポンスデータ。<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_portfolio_display";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101155L;

    /**
     * （表示対象）<BR>
     * <BR>
     * 表示対象の指定。<BR>
     * （1：ポートフォリオ登録分のみ　@2：特定分のみ　@3：両方合せる）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * displayTarget<BR>
     * (1 : Portfolio registration only  2 : Specific only  3 : Both)<BR>
     */
    public String displayTarget;

    /**
     * （銘柄明細一覧）<BR>
     * <BR>
     * portfolioDisplayProductUnit<BR>
     */
    public WEB3TrialCalcPortfolioDisplayProductUnit[] portfolioDisplayProductUnit;

    /**
     * （合計買付代金）<BR>
     * <BR>
     * totalBuyAmount<BR>
     */
    public String totalBuyAmount;

    /**
     * （合計評価額）<BR>
     * <BR>
     * totalAppraisalPrice<BR>
     */
    public String totalAppraisalPrice;

    /**
     * （合計評価損益）<BR>
     * <BR>
     * totalAppraisalProfitLoss<BR>
     */
    public String totalAppraisalProfitLoss;

    /**
     * （合計評価損益率）<BR>
     * <BR>
     * 合計評価損益率。（％単位）<BR>
     * <BR>
     * The total Appraisal Profit and Loss rate. (Units are in percent)<BR>
     */
    public String totalAppraisalProfitLossRate;

    /**
     * theWEB3TrialCalcPortfolioDisplayProductUnit
     * WEB3TrialCalcPortfolioDisplayProductUnit
     */
    public WEB3TrialCalcPortfolioDisplayProductUnit[] theWEB3TrialCalcPortfolioDisplayProductUnit;

    /**
     * @@roseuid 41C811D903D9
     */
    public WEB3TrialCalcPortfolioDisplayResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3TrialCalcPortfolioDisplayRequest
     */
    public WEB3TrialCalcPortfolioDisplayResponse(WEB3TrialCalcPortfolioDisplayRequest l_request)
    {
        super(l_request);
    }
}
@
