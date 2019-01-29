head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayInputProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�\�����͖�������(WEB3TrialCalcPortfolioDisplayInputProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.define.WEB3TrialCalcInputCapitalGainDivDef;
import webbroker3.trialcalc.define.WEB3TrialCalcMarketCodeDef;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�\�����͖������ׁj<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�\�����͖������ׁB<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayInputProductUnit<BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayInputProductUnit extends Message
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioDisplayInputProductUnit.class);

    /**
     * maxLength int
     */
    private final static int MAXLENGTH = 8;

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * Product code.<BR>
     */
    public String productCode;

    /**
     * �i�s��R�[�h�j<BR>
     * <BR>
     * �s��R�[�h�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * �i���t�P���j<BR>
     * <BR>
     * ���t�P���B<BR>
     * <BR>
     * Buy unit Price<BR>
     */
    public String buyPrice;

    /**
     * �i���́^����敪�j<BR>
     * <BR>
     * ���́^����敪�B<BR>
     * �i1�F���́@@2�F����j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * inputCapitalGainDiv<BR>
     * (1 : Input  2 : Specific)<BR>
     */
    public String inputCapitalGainDiv;

    /**
     * �i�]���P���j<BR>
     * <BR>
     * �]���P���B<BR>
     * �i�����w��̏ꍇ��null���Z�b�g�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * evaluationPrice<BR>
     * (When current price is specified null is set)<BR>
     */
    public String evaluationPrice;


    /**
     * @@roseuid 41C81B1003BA
     */
    public WEB3TrialCalcPortfolioDisplayInputProductUnit()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
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
     * �R�j�@@���t�P���`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.���t�P����null�A����<BR>
     * �@@�@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�Ethis.���t�P�������� (�u���t�P���������ȊO�v�̗�O��throw�B)<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * �@@�@@�@@�@@�Ethis.���t�P����0�@@�@@  (�u���t�P����0�ȉ��v�̗�O��throw�B)<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * �@@�@@�@@�@@�Ethis.���t�P����8���𒴂��鐔��<BR>
     * �@@�@@�@@�@@�@@�@@(�u���t�P���̌�����8���𒴉߁v�̗�O��throw�B)<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * �S�j�@@���́^����敪�`�F�b�N<BR>
     * <BR>
     * �@@�S�|�P�j�@@this.���́^����敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���́^����敪��null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01410<BR>
     * <BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.���́^����敪��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���́^����敪������`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F����"<BR>
     * �@@�@@�@@�@@�E"2�F����"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01411<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
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
     * <BR>
     * 3) buyPrice check<BR>
     *  3-1) If "this.buyPrice is not null"<BR>
     *   3-1-1) If "this.buyPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * <BR>
     *   3-1-2) If "this.buyPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * <BR>
     *   3-1-3) If "this.buyPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * 4) inputCapitalGainDiv check<BR>
     *  4-1) If "this.inputCapitalGainDiv = null"<BR>
     *       throw the following exception.<BR>
     *       [inputCapitalGainDiv is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01410<BR>
     * <BR>
     * <BR>
     *  4-2) If "this.inputCapitalGainDiv is not null" and<BR>
     *          "this.inputCapitalGainDiv is not one of the following values"<BR>
     * �@@�@@�@@�@@�E"1�Finput"<BR>
     * �@@�@@�@@�@@�E"2�FSpecific"<BR>
     *       throw the following exception.<BR>
     *       [inputCapitalGainDiv is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01411<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4198093C0198
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
            /**
             * To check if marketCode is not one of the following :
             * TOKYO,OSAKA,NAGOYA,FUKUOKA,SAPPORO,NNM,JASDAQ
             */
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
        if (this.buyPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.buyPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.buyPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (WEB3StringTypeUtility.getByteLength(this.buyPrice) > MAXLENGTH)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01393,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "overflow : buyPrice = " + this.buyPrice);
            }
        }
        if (this.inputCapitalGainDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01410,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcInputCapitalGainDivDef.INPUT.equals(this.inputCapitalGainDiv))
                && (!WEB3TrialCalcInputCapitalGainDivDef.SPECIFIC.equals(this.inputCapitalGainDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01411,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
