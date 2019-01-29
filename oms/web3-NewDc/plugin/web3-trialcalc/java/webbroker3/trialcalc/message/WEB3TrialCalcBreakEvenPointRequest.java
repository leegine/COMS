head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBreakEvenPointRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v����_�v�Z���N�G�X�g(WEB3TrialCalcBreakEvenPointRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import java.math.BigDecimal;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.define.WEB3TrialCalcOrderFormDef;

/**
 * �i�v�Z�T�[�r�X���v����_�v�Z���N�G�X�g�j<BR>
 * <BR>
 * �v�Z�T�[�r�X���v����_�v�Z�T�[�r�X�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3TrialCalcBreakEvenPointRequest<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcBreakEvenPointRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_breakevenpoint";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 2005033001100L;

    /**
     *
     *tenAsDenominator int
     */
    private final static int TEN_AS_DENOMINATOR = 10;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcBreakEvenPointRequest.class);
    /**
     * �i�����j<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * �i���P���j<BR>
     * <BR>
     * buyPrice<BR>
     */
    public String buyPrice;

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
     * <BR>
     */
    public String orderForm;

    /**
     * �i�����^�~�j���敪�j<BR>
     * <BR>
     * �����^�~�j���敪�B<BR>
     * �i1�F���������@@2�F�~�j���j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * equityMiniDiv<BR>
     * (1 : equity  2 : Mini stock division)<BR>
     * <BR>
     */
    public String equityMiniDiv;

    /**
     * �i�����P�ʁj<BR>
     * <BR>
     * �����P�ʁB<BR>
     * �i�~�j���̏ꍇ�̂ݎw��j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * dealingUnit<BR>
     * (Specify only with "Mini stock division")<BR>
     * <BR>
     */
    public String dealingUnit;

    /**
     * @@roseuid 41C815B1035C
     */
    public WEB3TrialCalcBreakEvenPointRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.������null�̏ꍇ�A�u������null�v�̗�O��throw����B
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.������null�A����
     * �ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B<BR>
     * �@@�@@�@@�Ethis.����������<BR>
     * �@@�@@�@@�@@�@@�@@�u�����������ȊO�v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00075<BR>
     * <BR>
     * �@@�@@�@@�Ethis.������0<BR>
     * �@@�@@�@@�@@�@@�@@�u������0�ȉ��v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00076<BR>
     * <BR>
     * �@@�@@�@@�Ethis.������8���𒴂��鐔��<BR>
     * �@@�@@�@@�@@�@@�@@�u�����̌�����8���𒴉߁v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00077<BR>
     * <BR>
     * <BR>
     * �Q�j�@@���P���`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.���P����null�̏ꍇ�A�u���P����null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01390<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.���P����null�A����
     * �ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B<BR>
     * �@@�@@�@@�Ethis.���P��������<BR>
     * �@@�@@�@@�@@�@@�@@�u���P���������ȊO�v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * �@@�@@�@@�Ethis.���P����0<BR>
     * �@@�@@�@@�@@�@@�@@�u���P����0�ȉ��v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * �@@�@@�@@�Ethis.���P����8���𒴂��鐔��<BR>
     * �@@�@@�@@�@@�@@�@@�u���P���̌�����8���𒴉߁v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * �R�j�@@�����`�ԃ`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.�����`�ԁ�null�̏ꍇ�A�u�����`�Ԃ�null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException
     * tag : BUSINESS_ERROR_01394
     * <BR>
     * �@@�R�|�Q�j�@@this.�����`�ԁ�null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����`�Ԃ�����`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F�C���^�[�l�b�g"<BR>
     * �@@�@@�@@�@@�E"2�F�R�[���Z���^�["<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * �S�j�@@�����^�~�j���敪�`�F�b�N<BR>
     * <BR>
     * �@@�S�|�P�j�@@this.�����^�~�j���敪��null�̏ꍇ�A�u�����^�~�j���敪��null�v�̗�O?
     * throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.�����^�~�j���敪��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�����^�~�j���敪������`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"2�F�~�j��"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * �T�j�@@�����^�~�j���敪�E�����P�ʃ`�F�b�N<BR>
     * <BR>
     * �@@�T�|�P�j�@@this.�����^�~�j���敪��"2�F�~�j��"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@this.�����P�ʁ�null�ł���΁u�����P�ʂ�null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01398<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�����P�ʁ�null�A����
     * �ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B<BR>
     * �@@�@@�@@�Ethis.�����P�ʁ�����<BR>
     * �@@�@@�@@�@@�@@�@@�u�����P�ʂ������ȊO�v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01399<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����P�ʁ�0<BR>
     * �@@�@@�@@�@@�@@�@@�u�����P�ʂ�0�ȉ��v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01400<BR>
     * <BR>
     * <BR>
     * �U�j�@@�����E�����^�~�j���敪�E�����P�ʃ`�F�b�N<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�����^�~�j���敪��"2�F�~�j��"�̏ꍇ�A����<BR>
     * �@@�@@�@@�@@this.�������ithis.�����P�ʁ�10�j��0
     * �̏ꍇ�i�����Z�ŗ]�肪�o��ꍇ�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�����������P�ʂ�10����1�ɑ΂��Ĕ񐮐��{�v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01401<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) orderQuantity check<BR>
     *  1-1) If "this.orderQuantity = null"<BR>
     *       throw the following exception.<BR>
     *       [orderQuantity is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * <BR>
     *  1-2) If "this.orderQuantity is not null"<BR>
     *   1-2-1) If "this.orderQuantity is not a number"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00075<BR>
     * <BR>
     * <BR>
     *   1-2-2) If "this.orderQuantity is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     *   1-2-3) If "this.orderQuantity has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00077<BR>
     * <BR>
     * <BR>
     * 2) buyPrice check<BR>
     *  2-1) If "this.buyPrice = null"<BR>
     *       throw the following exception.<BR>
     *       [buyPrice is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01390<BR>
     * <BR>
     * <BR>
     *  2-2) If "this.buyPrice is not null"<BR>
     *   2-2-1) If "this.buyPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01391<BR>
     * <BR>
     * <BR>
     *   2-2-2) If "this.buyPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01392<BR>
     * <BR>
     * <BR>
     *   2-2-3) If "this.buyPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [buyPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01393<BR>
     * <BR>
     * <BR>
     * 3) orderForm check<BR>
     *  3-1) If "this.orderForm = null<BR>
     *       throw the following exception.<BR>
     *       [orderForm is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException
     * tag : BUSINESS_ERROR_01394
     * <BR>
     * <BR>
     *  3-2) If "this.orderForm is not null" and <BR>
     *           "this.orderForm is not one of the following values".<BR>
     *              �E"1�FInternet"<BR>
     *              �E"2�FCall Center"<BR>
     *         throw the following exception. [orderForm is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * 4) equityMiniDiv check<BR>
     *  4-1) If "this.equityMiniDiv = null<BR>
     *       throw the following exception.<BR>
     *       [equityMiniDiv is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * <BR>
     *  4-2) If "this.equityMiniDiv is not null" and <BR>
     *          "this.equityMiniDiv is not one of the following values".<BR>
     *              �E"1�Fequity"<BR>
     *              �E"2�FMini stock division"<BR>
     *        throw the following exception. [equityMiniDiv is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * 5) equityMiniDiv / dealingUnit check<BR>
     *  5-1) If "this.equityMiniDiv = '2'(Mini stock division)" and<BR>
     *          "this.dealingUnit = null"<BR>
     *       throw the following exception. [dealingUnit is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01398<BR>
     * <BR>
     * <BR>
     *  5-2) If "this.dealingUnit is not null" and <BR>
     *          "this.dealingUnit is one of the following values".<BR>
     * <BR>
     *   5-2-1) If "this.dealingUnit is not a number"<BR>
     *          throw the following exception.<BR>
     *          [dealingUnit is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01399<BR>
     * <BR>
     * <BR>
     *   5-2-2) If "this.buyPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [dealingUnit is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01400<BR>
     * <BR>
     * <BR>
     * 6) orderQuantity, equityMiniDiv, dealingUnit check<BR>
     *  6-1) If "this.equityMiniDiv = '2'(Mini stock division)" and<BR>
     *          "this.orderQuantitiy%(this.dealingUnit / 10) > 0 (=There is
     *  remainder)<BR>
     *          throw the following exception.<BR>
     *          [The number of stocks is non-integral multiples against <BR>
     *           1/10 at each buying and selling. ]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01401<BR>
     * <BR>
     *  @@throws WEB3BaseException WEB3BaseException
     * <BR>
     * @@roseuid 419084B200BF
     */
    public void validate() throws WEB3BaseException
    {
        final int l_intEight = 8;
        int l_intDealingUnit = 0;
        int l_intOrderQuantity = 0;
        BigDecimal l_bdDealingUnit;
        BigDecimal l_bdOrderQuantity;
        BigDecimal l_bdOneTenth;
        BigDecimal l_bdTen;
        final int l_intTen = TEN_AS_DENOMINATOR;
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderQuantity))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.orderQuantity) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (WEB3StringTypeUtility.getByteLength(this.orderQuantity) > l_intEight)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Overflow : orderQuantity = " + this.orderQuantity);
            }
        }
        if (this.buyPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01390,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
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
            if (WEB3StringTypeUtility.getByteLength(this.buyPrice) > l_intEight)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01393,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Overflow : buyPrice = " + this.buyPrice);
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
        if (this.equityMiniDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01396,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcEquityMiniDivDef.EQUITY.equals(this.equityMiniDiv))
                && (!WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals(this.equityMiniDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01397,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        if (this.dealingUnit == null && WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals
                (this.equityMiniDiv))
        {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01398,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        } else if (this.dealingUnit != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.dealingUnit))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01399,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else if (Double.parseDouble(this.dealingUnit) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01400,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

       if (WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION.equals(this.equityMiniDiv))
       {
           l_bdDealingUnit = new BigDecimal(this.dealingUnit);
           l_bdOrderQuantity = new BigDecimal(this.orderQuantity);
           double l_dblCalc = 0;
           l_bdTen = new BigDecimal(l_intTen);
           l_bdOneTenth = l_bdDealingUnit.divide(l_bdTen, 2);
           l_dblCalc = l_bdOrderQuantity.doubleValue() % l_bdOneTenth.doubleValue();
           if (l_dblCalc > 0)
           {
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01401,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
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
        return new WEB3TrialCalcBreakEvenPointResponse(this);
    }
}
@
