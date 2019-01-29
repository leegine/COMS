head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ編集レスポンス(WEB3TrialCalcPortfolioEditResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービスポートフォリオ編集レスポンス）<BR>
 * <BR>
 * 計算サービスポートフォリオサービス（ポートフォリオ編集）のレスポンスデータ。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditResponse<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioEditResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_portfolioedit";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
     * （エラー銘柄明細一覧）<BR>
     * <BR>
     * エラー銘柄明細一覧。<BR>
     * （エラーなしの場合はnullをセット）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * portfolioErrorProductUnit<BR>
     * (Set null when there is no error. )<BR>
     * <BR>
     */
    public WEB3TrialCalcPortfolioErrorProductUnit[] portfolioErrorProductUnit;

    /**
     * @@roseuid 41C811F3037B
     */
    public WEB3TrialCalcPortfolioEditResponse()
    {

    }
    /**
     * Constructor with WEB3TrialCalcPortfolioEditRequest argument
     * @@param l_request WEB3TrialCalcPortfolioEditRequest
     */
    public WEB3TrialCalcPortfolioEditResponse(WEB3TrialCalcPortfolioEditRequest l_request)
    {
        super(l_request);
    }
}
@
