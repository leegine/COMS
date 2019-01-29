head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���̓��N�G�X�g(WEB3TrialCalcPortfolioEditInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���̓��N�G�X�g�j<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�i�|�[�g�t�H���I�ҏW���͉�ʕ\���j�̃��N�G�X�g
 * �f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditInputRequest<BR>
 * @@author Prabhu
 * @@version 1.0
 * <BR>
 */
public class WEB3TrialCalcPortfolioEditInputRequest extends WEB3GenRequest
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
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioEditInputRequest.class);

    /**
     * �i�|�[�g�t�H���I�R�[�h�j<BR>
     * <BR>
     * portfolioCode<BR>
     */
    public String portfolioCode;

    /**
     * @@roseuid 41C811C1003F
     */
    public WEB3TrialCalcPortfolioEditInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�|�[�g�t�H���I�R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�|�[�g�t�H���I�R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�|�[�g�t�H���I�R�[�h��null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) portfolioCode check<BR>
     *  1-1) If "this.portfolioCode = null"<BR>
     *         throw the following exception.<BR>
     *         [portfolioCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     *  @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4194C14D02EC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.portfolioCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01418,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcPortfolioEditInputResponse(this);
    }
}
@
