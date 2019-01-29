head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW��������(WEB3TrialCalcPortfolioEditProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.define.WEB3TrialCalcMarketCodeDef;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�ҏW�������ׁj<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW�������ׁB<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditProductUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 * <BR>
 */
public class WEB3TrialCalcPortfolioEditProductUnit extends Message
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioEditProductUnit.class);

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * productCode<BR>
     */
    public String productCode;

    /**
     * �i�����j<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * �i���P���j<BR>
     * <BR>
     * ���P���B<BR>
     * �inull�w��j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * buyPrice. (Null can be specified)<BR>
     */
    public String buyPrice;

    /**
     * �s��R�[�h�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  7 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     */
    public String marketCode;

    /**
     * @@roseuid 41C81B2D0243
     */
    public WEB3TrialCalcPortfolioEditProductUnit()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * ���������ז��ɃG���[�`�F�b�N���ʂ�Ԃ����߁A<BR>
     * �������A���P���̒l�`�F�b�N�́A�T�[�r�X�����Ŏ��{����B<BR>
     * <BR>
     * �P�j�@@�����R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�����R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h��null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�s��R�[�h��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O��throw����B<BR>
     *          �E"1�F����"<BR>
     *          �E"2�F���"<BR>
     *          �E"3�F���É�"<BR>
     *          �E"6�F����"<BR>
     *          �E"8�F�D�y"<BR>
     *          �E"9�FNNM"<BR>
     *          �E"10�FJASDAQ"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
    *For every product details an error check result is given.<BR>
    *Therefore the check for orderQuantity and buyPrice is executed in service.<BR>
     * <BR>
     * 1) productCode check<BR>
     *  1-1) If "this.productCode = null"<BR>
     *       throw the following exception.<BR>
     *       [productCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * 2) marketCode check<BR>
     *  2-1) If "this.marketCode is not null" and<BR>
     *          "this.marketCode is not one of the following values"<BR>
     *          �E"1�FTOKYO"<BR>
     *          �E"2�FOSAKA"<BR>
     *          �E"3�FNAGOYA"<BR>
     *          �E"6�FFUKUOKA"<BR>
     *          �E"8�FSAPPORO"<BR>
     *          �E"9�FNNM"<BR>
     *          �E"10�FJASDAQ"<BR>
     *       throw the following exception.<BR>
     *       [marketCode is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * @@roseuid 41986EF501E6
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (this.marketCode != null)
        {
            if ((!WEB3TrialCalcMarketCodeDef.TOKYO.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.OSAKA.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.NAGOYA.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.FUKUOKA.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.SAPPORO.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.NNM.equals(this.marketCode))
                && (!WEB3TrialCalcMarketCodeDef.JASDAQ.equals(this.marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
