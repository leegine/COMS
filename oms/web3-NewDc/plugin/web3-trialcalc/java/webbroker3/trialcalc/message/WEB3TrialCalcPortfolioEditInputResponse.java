head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���̓��X�|���X(WEB3TrialCalcPortfolioEditInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���̓��X�|���X�j<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�i�|�[�g�t�H���I�ҏW���͉�ʕ\���j�̃��X�|���X
 * �f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditInputResponse<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioEditInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_portfolioedit_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101100L;

    /**
     * �i�������͖��׈ꗗ�B�j<BR>
     * <BR>
     * portfolioEditProductInputUnit<BR>
     */
    public WEB3TrialCalcPortfolioEditProductInputUnit[] portfolioEditProductInputUnit;

    /**
     * @@roseuid 41C811E40149
     */
    public WEB3TrialCalcPortfolioEditInputResponse()
    {

    }
    /**
     *
     * @@param l_request WEB3TrialCalcPortfolioEditInputRequest
     */
    public WEB3TrialCalcPortfolioEditInputResponse(WEB3TrialCalcPortfolioEditInputRequest l_request)
    {
        super(l_request);
    }
}
@
