head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���N�G�X�g(WEB3TrialCalcPortfolioEditRequest.java)
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
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�ҏW���N�G�X�g�j<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�i�|�[�g�t�H���I�ҏW�j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditRequest<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioEditRequest extends WEB3GenRequest
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
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioEditRequest.class);

    /**
     * �i�|�[�g�t�H���I�R�[�h�j<BR>
     * <BR>
     * portfolioCode<BR>
     */
    public String portfolioCode;

    /**
     * �i�������׈ꗗ�j<BR>
     * <BR>
     * portfolioEditProductUnit<BR>
     */
    public WEB3TrialCalcPortfolioEditProductUnit[] portfolioEditProductUnit;

    /**
     * @@roseuid 41C811CB0187
     */
    public WEB3TrialCalcPortfolioEditRequest()
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
     * �Q�j�@@�������׈ꗗ�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�������׈ꗗ��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@this.�������׈ꗗ�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j�@@�������׈ꗗ.validate()���R�[������B <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) portfolioCode check<BR>
     *  1-1) If "this.portfolioCode = null"<BR>
     *       throw the following exception.<BR>
     *       [portfolioCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * 2) portfolioEditProductUnit check<BR>
     *  2-1)If this.portfolioEditProductUnit is not null<BR>
     *        Check for all the values of this.portfolioEditProductUnit<BR>
     *   2-1-1) Call portfolioEditProductUnit.validate()<BR>
     * @@roseuid 41989124026B
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_length;

        if (this.portfolioCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01418,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (this.portfolioEditProductUnit != null)
        {
            l_length = portfolioEditProductUnit.length;
            for (int i = 0; i < l_length; i++)
            {
                portfolioEditProductUnit[i].validate();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcPortfolioEditResponse(this);
    }
}
@
