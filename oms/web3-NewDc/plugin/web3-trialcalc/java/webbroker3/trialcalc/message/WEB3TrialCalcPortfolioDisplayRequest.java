head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�\�����N�G�X�g(WEB3TrialCalcPortfolioDisplayRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trialcalc.define.WEB3TrialCalcDisplayTargetDef;
import webbroker3.trialcalc.define.WEB3TrialCalcKeyItemDef;
import webbroker3.trialcalc.define.WEB3TrialCalcOrderFormDef;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�\�����N�G�X�g�j<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayRequest<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�i�|�[�g�t�H���I�\���j�̃��N�G�X�g�f�[�^�B<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayRequest extends WEB3GenRequest
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
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioDisplayRequest.class);

    /**
     * �i�\���Ώہj<BR>
     * <BR>
     * �\���Ώۂ̎w��B<BR>
     * �i1�F�|�[�g�t�H���I�o�^���̂݁@@2�F���蕪�̂݁@@3�F����������j<BR>
     * �inull�w��j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * displayTarget<BR>
     * (1 : Portfolio registration only  2 : Specific only  3 : Both)<BR>
     */
    public String displayTarget;

    /**
     * �i�\�[�g�L�[�j<BR>
     * <BR>
     * �v�Z�T�[�r�X�\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * ���Ώۍ��ځ�<BR>
     * �����R�[�h<BR>
     * �s��R�[�h<BR>
     * ����<BR>
     * ����<BR>
     * ���t�P��<BR>
     * ���t���<BR>
     * �]���z<BR>
     * �]�����v<BR>
     * �]�����v��<BR>
     * ���́^����敪<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Sort keys<BR>
     * <BR>
     * List of calculation service sorting key<BR>
     * <Target Items><BR>
     * productCode<BR>
     * marketCode<BR>
     * orderQuantity<BR>
     * currentPrice<BR>
     * buyPrice<BR>
     * buyAmount<BR>
     * appraisalPrice<BR>
     * appraisalProfitLoss<BR>
     * appraisalProfitLossRate<BR>
     * inputCapitalGainDiv<BR>
     */
    public webbroker3.trialcalc.message.WEB3TrialCalcSortKeyUnit[] sortKeys;

    /**
     * �i���͖������׈ꗗ�j<BR>
     * <BR>
     * ���͖������׈ꗗ�B<BR>
     * �i�]���P�����͎��̂ݐݒ�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Input Product details list.<BR>
     * Set only when evaluationPrice is input.<BR>
     * <BR>
     */
    public WEB3TrialCalcPortfolioDisplayInputProductUnit[] portfolioDisplayInputProductUnit;

    /**
     * �i�|�[�g�t�H���I�R�[�h�j<BR>
     * <BR>
     * portfolioCode<BR>
     */
    public String portfolioCode;

    /**
     * �i�����`�ԁj<BR>
     * <BR>
     * �����`�ԁB<BR>
     * �i1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�[�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderForm<BR>
     * (1 : Internet  2 : Call center)<BR>
     */
    public String orderForm;

    /**
     * theWEB3TrialCalcSortKeyUnit WEB3TrialCalcSortKeyUnit
     */
    public WEB3TrialCalcSortKeyUnit[] theWEB3TrialCalcSortKeyUnit;

    /**
     * theWEB3TrialCalcPortfolioDisplayInputProductUnit
     * WEB3TrialCalcPortfolioDisplayInputProductUnit
     */
    public WEB3TrialCalcPortfolioDisplayInputProductUnit[]
                theWEB3TrialCalcPortfolioDisplayInputProductUnit;

    /**
     * intMinValue int
     */
    private int intMinValue = 0;

    /**
     * @@roseuid 41C811B40223
     */
    public WEB3TrialCalcPortfolioDisplayRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�\���Ώۃ`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�\���Ώہ�null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\���Ώۂ�����`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F�|�[�g�t�H���I�o�^���̂�"<BR>
     * �@@�@@�@@�@@�E"2�F���蕪�̂�"<BR>
     * �@@�@@�@@�@@�E"3�F����������"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01415<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�\�[�g�L�[��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�\�[�g�L�[.�v�f����0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�����R�[�h�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�s��R�[�h�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�����v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�����v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u���t�P���v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u���t����v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�]���z�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�]�����v�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u�]�����v���v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�u���́^����敪�v<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * <BR>
     * �R�j�@@���͖������׈ꗗ�`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.���͖������׈ꗗ��null�̏ꍇ�A�v�f����0�̏ꍇ��<BR>
     * �@@�@@�@@�@@�u���͖������׈ꗗ.�v�f����0�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00793<BR>
     * <BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.���͖������׈ꗗ�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�R�|�Q�|�P�j�@@���͖������׈ꗗ.validate()���R�[������B<BR>
     * <BR>
     * �S�j�@@�|�[�g�t�H���I�R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�S�|�P�j�@@this.�|�[�g�t�H���I�R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�|�[�g�t�H���I�R�[�h��null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * �T�j�@@�����`�ԃ`�F�b�N<BR>
     * <BR>
     * �@@�T�|�P�j�@@this.�����`�ԁ�null�̏ꍇ�A�u�����`�Ԃ�null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�����`�ԁ�null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����`�Ԃ�����`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F�C���^�[�l�b�g"<BR>
     * �@@�@@�@@�@@�E"2�F�R�[���Z���^�["<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * �U�j�@@���͖������׈ꗗ�E�|�[�g�t�H���I�R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�U�|�P�j�@@this.���͖������׈ꗗ��null�A����
     * this.�|�[�g�t�H���I�R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�Ώۖ��ׂ̎w��Ȃ��v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01419<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * 1) displayTarget check<BR>
     *  1-1) If "this.displayTarget is not null" and<BR>
     *           "this.displayTarget is not one of the following values.<BR>
     *         �E"1�FONLY PORTFOLIO"<BR>
     *         �E"2�FONLY SPECIAL ACOUNT"<BR>
     *         �E"3�FBOTH"<BR>
     *         throw the following exception. [displayTarget is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01415<BR>
     * <BR>
     * <BR>
     * 2) sortKey check<BR>
     *  2-1) If "this.sortKey = null"<BR>
     *         throw the following exception. [sortKey is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * <BR>
     *  2-2) If "this.sortKeys.(number of elements) = 0<BR>
     *         throw the following exception.<BR>
     *         [sortKey.(number of elements) is 0]       <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * <BR>
     *  2-3) Check for all the values of this.sortKeys<BR>
     *   2-3-1) Call "sortKeys.validate()"<BR>
     * <BR>
     *   2-3-2) If things except the following item are set in<BR>
     *            sortKeys.(key item) <BR>
     *               �E[productCode]<BR>
     *               �E[marketCode]<BR>
     *               �E[orderQuantity]<BR>
     *               �E[currentPrice]<BR>
     *               �E[buyPrice]<BR>
     *               �E[buyAmount]<BR>
     *               �E[appraisalPrice]<BR>
     *               �E[appraisalProfitLoss]<BR>
     *               �E[appraisalProfitLossRate]<BR>
     *               �E[inputCapitalGainDiv]<BR>
     *            throw the following exception<BR>
     *            [sortKey.(number of elements) is 0]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * <BR>
     * 3) portfolioDisplayInputProductUnit check<BR>
     *  3-1) If "this.portfolioDisplayInputProductUnit is not null" and<BR>
     *           "number of elements = 0"<BR>
     *            throw the following exception.<BR>
     *            [portfolioDisplayInputProductUnit.(number of elements) is 0]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00793<BR>
     * <BR>
     * <BR>
     *  3-2)  Check for all the values of this.portfolioDisplayInputProductUnit.<BR>
     *   3-2-1) Call portfolioDisplayInputProductUnit.validate().<BR>
     * <BR>
     * 4) portfolioCode check<BR>
     *  4-1) If "this.portfolioCode = null"<BR>
     *        throw the following exception. [portfolioCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * 5) orderForm check<BR>
     *  5-1) If "this.orderForm = null"<BR>
     *        throw the following exception. [orderForm is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * <BR>
     *  5-2) If "this.orderForm is not null" and <BR>
     *           "this.orderForm is not one of the following values".<BR>
     *              �E"1�FInternet"<BR>
     *              �E"2�FCall Center"<BR>
     *         throw the following exception. [orderForm is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * 6) portfolioDisplayInputProductUnit / portfolioCode check<BR>
     *  6-1) If "this.portfolioDisplayInputProductUnit = null" and<BR>
     *           "this.portfolioCode = null"<BR>
     *         throw the following exception. <BR>
     *         [The object details are not specified]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01419<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 418B4E1900C4
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.displayTarget != null)
        {
            if ((!WEB3TrialCalcDisplayTargetDef.ONLY_PORTFOLIO.equals(this.displayTarget))
                && (!WEB3TrialCalcDisplayTargetDef.ONLY_SPECIAL_ACCOUNT.equals(this.displayTarget))
                && (!WEB3TrialCalcDisplayTargetDef.BOTH.equals(this.displayTarget)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01415,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (sortKeys.length == intMinValue)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
            this.getClass().getName() + "." + STR_METHOD_NAME);

        }
        for (int i = intMinValue; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
            if (!WEB3TrialCalcKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.ORDER_QUANTITY.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.CURRENT_PRICE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.BUY_AMOUNT.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.BUY_PRICE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.APPRAISAL_PRICE.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.APPRAISAL_PROFIT_LOSS.equals(this.sortKeys[i].keyItem)
				&& !WEB3TrialCalcKeyItemDef.INPUT_CAPITALCAIN_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3TrialCalcKeyItemDef.APPRAISAL_PROFIT_LOSS_RATE.equals(
                    this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.portfolioDisplayInputProductUnit != null
            && this.portfolioDisplayInputProductUnit.length == intMinValue)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00793,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.portfolioDisplayInputProductUnit != null)
        {
            for (int i = intMinValue; i < portfolioDisplayInputProductUnit.length; i++)
            {
                portfolioDisplayInputProductUnit[i].validate();
            }
        }
        if (this.portfolioCode == null)
        {
            if (this.portfolioDisplayInputProductUnit != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01418,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01419,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.orderForm == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01394,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcOrderFormDef.INTERNET.equals(this.orderForm))
                && (!WEB3TrialCalcOrderFormDef.CALL_CENTER.equals(this.orderForm)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01395,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
}

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcPortfolioDisplayResponse(this);
    }
}
@
