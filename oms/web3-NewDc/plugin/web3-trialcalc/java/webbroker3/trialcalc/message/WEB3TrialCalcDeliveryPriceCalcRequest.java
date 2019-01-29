head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcDeliveryPriceCalcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X��n����v�Z���N�G�X�g(WEB3TrialCalcDeliveryPriceCalcRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.trialcalc.define.WEB3TrialCalcDealingTypeDef;
import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.define.WEB3TrialCalcMarketCodeDef;
import webbroker3.trialcalc.define.WEB3TrialCalcOrderFormDef;

/**
 * �i�v�Z�T�[�r�X��n����v�Z���N�G�X�g�j<BR>
 * <BR>
 * �v�Z�T�[�r�X��n����v�Z�T�[�r�X�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcDeliveryPriceCalcRequest<BR>
 * @@author sararavanan
 * @@version 1.0
 */
public class WEB3TrialCalcDeliveryPriceCalcRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_estimatedamount_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcDeliveryPriceCalcRequest.class);
    /**
     * �i�����敪�j<BR>
     * <BR>
     * �����敪�B<BR>
     * �i1�F�����@@2�F����j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * dealingType<BR>
     * (1 : Buy  2 : Sell)<BR>
     */
    public String dealingType;

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
     * orderPrice<BR>
     * (Null can be specified)<BR>
     * <BR>
     */
    public String orderPrice;

    /**
     * �s��R�[�h�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j<BR>
     * �inull�w��j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     */
    public String marketCode;

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
     * @@roseuid 41C813DD00CC
     */
    public WEB3TrialCalcDeliveryPriceCalcRequest()
    {
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�����敪�`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�����敪��null�̏ꍇ�A�u�����敪��null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01402<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�����敪��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����敪������`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F����"<BR>
     * �@@�@@�@@�@@�E"2�F����"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01403<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�����R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�����R�[�h��null�̏ꍇ�A�u�����R�[�h��null�v�̗�O��throw����B
     * <BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * �R�j�@@�����`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.������null�̏ꍇ�A�u������null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.������null�A����
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
     * �S�j�@@���P���`�F�b�N<BR>
     * <BR>
     * �@@�S�|�P�j�@@this.���P����null�A����
     * �ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B<BR>
     * �@@�@@�@@�Ethis.���P��������<BR>
     * �@@�@@�@@�@@�@@�@@�u���P���������ȊO�v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01404<BR>
     * <BR>
     * �@@�@@�@@�Ethis.���P����0<BR>
     * �@@�@@�@@�@@�@@�@@�u���P����0�ȉ��v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01405<BR>
     * <BR>
     * �@@�@@�@@�Ethis.���P����8���𒴂��鐔��<BR>
     * �@@�@@�@@�@@�@@�@@�u���P���̌�����8���𒴉߁v�̗�O��throw�B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01406<BR>
     * <BR>
     * <BR>
     * �T�j�@@�s��R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�T�|�P�j�@@this.�s��R�[�h��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F����"<BR>
     * �@@�@@�@@�@@�E"2�F����"<BR>
     * �@@�@@�@@�@@�E"3�F���É�"<BR>
     * �@@�@@�@@�@@�E"6�F����"<BR>
     * �@@�@@�@@�@@�E"8�F�D�y"<BR>
     * �@@�@@�@@�@@�E"9�FNNM"<BR>
     * �@@�@@�@@�@@�E"10�FJASDAQ"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * <BR>
     * �U�j�@@�����`�ԃ`�F�b�N<BR>
     * <BR>
     * �@@�U�|�P�j�@@this.�����`�ԁ�null�̏ꍇ�A�u�����`�Ԃ�null�v�̗�O��throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * �@@�U�|�Q�j�@@this.�����`�ԁ�null�A���� �ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����`�Ԃ�����`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F�C���^�[�l�b�g"<BR>
     * �@@�@@�@@�@@�E"2�F�R�[���Z���^�["<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * �V�j�@@�����^�~�j���敪�`�F�b�N<BR>
     * <BR>
     * �@@�V�|�P�j�@@this.�����^�~�j���敪��null�̏ꍇ�A�u�����^�~�j���敪��null�v�̗�O?
     * throw����B<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     * �@@�V�|�Q�j�@@this.�����^�~�j���敪��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�����^�~�j���敪������`�̒l�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�E"1�F��������"<BR>
     * �@@�@@�@@�@@�E"2�F�~�j��"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) dealingType check<BR>
     *  1-1) If "this.dealingType = null"<BR>
     *       throw the following exception.<BR>
     *       [dealingType is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01402<BR>
     * <BR>
     * <BR>
     *  1-2) If "this.dealingType is not null" and<BR>
     *          "this.dealingType is not one of the following values"<BR>
     *         �E"1 : Buy"<BR>
     *         �E"2 : Sell"<BR>
     *       throw the following exception.<BR>
     *       [dealingType is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01403<BR>
     * <BR>
     * <BR>
     * 2) productCode check<BR>
     *  2-1) If "this.productCode = null<BR>
     *       throw the following exception.<BR>
     *       [productCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * <BR>
     * 3) orderQuantity check<BR>
     *  3-1) If "this.orderQuantity = null"<BR>
     *       throw the following exception.<BR>
     *       [orderQuantity is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00074<BR>
     * <BR>
     * <BR>
     *  3-2) If "this.orderQuantity is not null"<BR>
     *   3-2-1) If "this.orderQuantity is not a number"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00075<BR>
     * <BR>
     * <BR>
     *   3-2-2) If "this.orderQuantity is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     *   3-2-3) If "this.orderQuantity has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [orderQuantity has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00077<BR>
     * <BR>
     * <BR>
     * 4) orderPrice check<BR>
     *  4-1) If "this.orderPrice is not null"<BR>
     *   4-1-1) If "this.orderPrice is not a number"<BR>
     *          throw the following exception.<BR>
     *          [orderPrice is not a number]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01404<BR>
     * <BR>
     * <BR>
     *   4-1-2) If "this.orderPrice is 0 or smaller"<BR>
     *          throw the following exception.<BR>
     *          [orderPrice is 0 or smaller]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01405<BR>
     * <BR>
     * <BR>
     *   4-1-3) If "this.orderPrice has more than 8 digits"<BR>
     *          throw the following exception.<BR>
     *          [orderPrice has more than 8 digits]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01406<BR>
     * <BR>
     * <BR>
     * 5) marketCode check<BR>
     *  5-1) If "this.marketCode is not null" and<BR>
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
     * 6) orderForm check<BR>
     *  6-1) If "this.orderForm = null<BR>
     *       throw the following exception.<BR>
     *       [orderForm is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01394<BR>
     * <BR>
     * <BR>
     *  6-2) If "this.orderForm is not null" and <BR>
     *           "this.orderForm is not one of the following values".<BR>
     *              �E"1�FInternet"<BR>
     *              �E"2�FCall Center"<BR>
     *         throw the following exception. [orderForm is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01395<BR>
     * <BR>
     * <BR>
     * 7) equityMiniDiv check<BR>
     *  7-1) If "this.equityMiniDiv = null<BR>
     *       throw the following exception.<BR>
     *       [equityMiniDiv is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01396<BR>
     * <BR>
     *  7-2) If "this.equityMiniDiv is not null" and <BR>
     *           "this.equityMiniDiv is not one of the following values".<BR>
     *              �E"1�Fequity"<BR>
     *              �E"2�FMini stock division"<BR>
     *         throw the following exception. [equityMiniDiv is undefined]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01397<BR>
     * <BR>
     * @@exception WEB3BaseException is throwing
     * @@roseuid 418B4E250018
     */
    public void validate() throws WEB3BaseException
    {
        final int l_intEight = 8;
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.dealingType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01402,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            if ((!WEB3TrialCalcDealingTypeDef.BUY.equals(this.dealingType))
                && (!WEB3TrialCalcDealingTypeDef.SELL.equals(this.dealingType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

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

        if (this.orderPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01404,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (Double.parseDouble(this.orderPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01405,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if ((WEB3StringTypeUtility.getByteLength(this.orderPrice)) > l_intEight)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01406,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "overflow : orderPrice = " +  this.orderPrice);
            }
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcDeliveryPriceCalcResponse(this);
    }
}
@
