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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���X�|���X(WEB3TrialCalcPortfolioEditResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���X�|���X�j<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�i�|�[�g�t�H���I�ҏW�j�̃��X�|���X�f�[�^�B<BR>
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
     * �i�G���[�������׈ꗗ�j<BR>
     * <BR>
     * �G���[�������׈ꗗ�B<BR>
     * �i�G���[�Ȃ��̏ꍇ��null���Z�b�g�j<BR>
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
