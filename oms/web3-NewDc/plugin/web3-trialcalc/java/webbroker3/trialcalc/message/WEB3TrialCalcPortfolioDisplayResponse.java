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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�\�����X�|���X(WEB3TrialCalcPortfolioDisplayResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�\�����X�|���X�j<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayResponse<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�i�|�[�g�t�H���I�\���j�̃��X�|���X�f�[�^�B<BR>
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
     * �i�\���Ώہj<BR>
     * <BR>
     * �\���Ώۂ̎w��B<BR>
     * �i1�F�|�[�g�t�H���I�o�^���̂݁@@2�F���蕪�̂݁@@3�F����������j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * displayTarget<BR>
     * (1 : Portfolio registration only  2 : Specific only  3 : Both)<BR>
     */
    public String displayTarget;

    /**
     * �i�������׈ꗗ�j<BR>
     * <BR>
     * portfolioDisplayProductUnit<BR>
     */
    public WEB3TrialCalcPortfolioDisplayProductUnit[] portfolioDisplayProductUnit;

    /**
     * �i���v���t����j<BR>
     * <BR>
     * totalBuyAmount<BR>
     */
    public String totalBuyAmount;

    /**
     * �i���v�]���z�j<BR>
     * <BR>
     * totalAppraisalPrice<BR>
     */
    public String totalAppraisalPrice;

    /**
     * �i���v�]�����v�j<BR>
     * <BR>
     * totalAppraisalProfitLoss<BR>
     */
    public String totalAppraisalProfitLoss;

    /**
     * �i���v�]�����v���j<BR>
     * <BR>
     * ���v�]�����v���B�i���P�ʁj<BR>
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
