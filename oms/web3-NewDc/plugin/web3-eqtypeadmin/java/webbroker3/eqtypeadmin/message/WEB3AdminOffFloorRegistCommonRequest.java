head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������V�K�o�^���ʃ��N�G�X�g(WEB3AdminOffFloorRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������V�K�o�^���ʃ��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��җ���O���������V�K�o�^�T�[�r�X�i�m�F�^�����j���N�G�X�g�f�[�^�̃X�[�p�[�N��
 * �X�B<BR>
 * <BR>
 * ------<English>----------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminOffFloorRegistService(validate/submit) request data<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public abstract class WEB3AdminOffFloorRegistCommonRequest extends WEB3GenRequest
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorRegistCommonRequest.class);

    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����R�[�h�B<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h�B<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (��t�J�n����)<BR>
     * <BR>
     * ��t�J�n�����B<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (�������i)<BR>
     * <BR>
     * �������i�B<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (�\���������)<BR>
     * <BR>
     * �\����������B<BR>
     * �i��l������̒����\�����̏���l�j<BR>
     * <BR>
     * ----<English>-------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * �imaximum value of applyQuantity per person�j<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * @@roseuid 421AE4400226
     */
    public WEB3AdminOffFloorRegistCommonRequest()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�����R�[�h�`�F�b�N <BR>
     * <BR>
     * �@@�P�|�P�j�@@this.�����R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h��null�v�̗�O��throw����B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N <BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�s��R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O��throw����B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�s��R�[�h��null�A���� �ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O��throw����B <BR>
     *          �E"1�F����" <BR>
     *          �E"2�F���" <BR>
     *          �E"3�F���É�" <BR>
     *          �E"6�F����" <BR>
     *          �E"8�F�D�y" <BR>
     *          �E"9�FNNM" <BR>
     *          �E"10�FJASDAQ" <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * �R�j�@@��t�J�n�����`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.��t�J�n������null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u��t�J�n������null�v�̗�O��throw����B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01389<BR>
     * <BR>
     * �S�j�@@�������i�`�F�b�N<BR>
     * <BR>
     * �@@�S�|�P�j�@@this.�������i��null�A����
     * �ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B <BR>
     * �@@�@@�@@�Ethis.�������i������ <BR>
     * �@@�@@�@@�@@�@@�@@�u�������i�������ȊO�v�̗�O��throw�B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01453<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�������i��0 <BR>
     * �@@�@@�@@�@@�@@�@@�u�������i��0�ȉ��v�̗�O��throw�B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01454<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�������i��9���𒴂��鐔�� <BR>
     * �@@�@@�@@�@@�@@�@@�u�������i�̌�����9���𒴉߁v�̗�O��throw�B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01455<BR>
     * <BR>
     * �T�j�@@�\����������`�F�b�N<BR>
     * <BR>
     * �@@�T�|�P�j�@@this.�\�����������null�A����
     * �ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O��throw����B <BR>
     * �@@�@@�@@�Ethis.�\��������������� <BR>
     * �@@�@@�@@�@@�@@�@@�u�\����������������ȊO�v�̗�O��throw�B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01456<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�\�����������0 <BR>
     * �@@�@@�@@�@@�@@�@@�u�\�����������0�ȉ��v�̗�O��throw�B <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01457<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�\�����������8���𒴂��鐔�� <BR>
     * �@@�@@�@@�@@�@@�@@�u�\����������̌�����8���𒴉߁v�̗�O��throw�B<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01458<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * <BR>
     * �@@1-1)If this.productCode��null,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "productCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 2)marketCode check<BR>
     * <BR>
     * �@@2-1)If this.marketCode��null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "marketCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@2-2)If this.marketCode ��null and it has a value other than the
     * followings,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "marketCode has an undefined value"<BR>
     *          �E1: Def.TOKYO<BR>
     *          �E2: Def.OSAKA<BR>
     *          �E3: Def.NAGOYA<BR>
     *          �E6: Def.FUKUOKA<BR>
     *          �E8: Def.SAPPORO<BR>
     *          �E9: Def.NNM<BR>
     *          �E10: Def.JASDAQ<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * 3)orderStartDatetime check<BR>
     * <BR>
     * �@@3-1)If this.orderStartDatetime��null,<BR>
     * �@@�@@�@@�@@�@@Throw the exception, "orderStartDatetime is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01389<BR>
     * <BR>
     * 4)offFloorOrderPrice check<BR>
     * <BR>
     * �@@4-1)If this.offFloorOrderPrice��null and it meets either of the following
     * cases, throw the following exception<BR>
     * �@@�@@�@@�Ethis.offFloorOrderPrice��numerical value<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception, "offFloorOrderPrice is not a numerical
     * value"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01453<BR>
     * <BR>
     * �@@�@@�@@�Ethis.offFloorOrderPrice��0 <BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception, "offFloorOrderPrice is less than 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01454<BR>
     * <BR>
     * �@@�@@�@@�Ethis.offFloorOrderPrice��a numerical value with more than 9 digits<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception, "offFloorOrderPrice has more than 9 digits"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01455<BR>
     * <BR>
     * 5)maxApplyQuantity check<BR>
     * <BR>
     * �@@5-1)If this.maxApplyQuantity��null and it meets either of the following cases,
     * throw the following exception<BR>
     * �@@�@@�@@�Ethis.maxApplyQuantity��numerical value<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception, "maxApplyQuantity is not a numerical value"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01456<BR>
     * <BR>
     * �@@�@@�@@�Ethis.maxApplyQuantity��0 <BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception, "maxApplyQuantity is less than 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01457<BR>
     * <BR>
     * �@@�@@�@@�Ethis.maxApplyQuantity��a numerical value with more than 8 digits<BR>
     * �@@�@@�@@�@@�@@�@@Throw the exception, "maxApplyQuantity has more than 8 digits"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01458<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41B7D1AB025D
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intnine = 9;
        final int l_intEight = 8;
        int l_offFloorOrderPriceGetByteLength =
            WEB3StringTypeUtility.getByteLength(offFloorOrderPrice);
        int l_maxApplyQuantityGetByteLength = WEB3StringTypeUtility.getByteLength(maxApplyQuantity);

        //1-1) If this.productCode��null, throw Exception
        if (productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //2-1)If this.marketCode��null
        if (marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            /*
             * 2-2)If this.marketCode ��null and it has a value other than the
             * followings Throw the exception
             * 1: Def.TOKYO
             * 2: Def.OSAKA
             * 3: Def.NAGOYA
             * 6: Def.FUKUOKA
             * 8: Def.SAPPORO
             * 9: Def.NNM
             * 10: Def.JASDAQ
             */
        } else if (
            (marketCode != null)
                && (!WEB3MarketCodeDef.TOKYO.equals(marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(marketCode))
                && (!WEB3MarketCodeDef.FUKUOKA.equals(marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1)If this.orderStartDatetime��null Throw the exception
        if (orderStartDatetime == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01389,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        /*
         * 4-1)If this.offFloorOrderPrice��null and it meets either of the following
         *  cases, throw the following exception
         * this.offFloorOrderPrice��numerical value Throw the exception
         * maxApplyQuantity��0 Throw the exception
         * offFloorOrderPrice��a numerical value with more than 9 digits
         * Throw the exception
         */
        if (offFloorOrderPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(offFloorOrderPrice))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01453,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            int l_intoffFloorOrderPrice = Integer.parseInt(offFloorOrderPrice);
            if (l_intoffFloorOrderPrice <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01454,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            if ((WEB3StringTypeUtility.isNumber(offFloorOrderPrice))
                && (l_offFloorOrderPriceGetByteLength > l_intnine))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01455,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        /*
         * 5-1)If this.maxApplyQuantity��null and it meets either of the following
         * cases, throw the following exception
         * this.maxApplyQuantity��numerical value Throw the exception
         * maxApplyQuantity��0 Throw the exception
         * maxApplyQuantity��a numerical value with more than 9 digits Throw the exception
         */
        if (maxApplyQuantity != null)
        {
            if (!WEB3StringTypeUtility.isNumber(maxApplyQuantity))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01456,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            int l_intoffFloorOrderPrice = Integer.parseInt(maxApplyQuantity);
            if (l_intoffFloorOrderPrice <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01457,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            if ((WEB3StringTypeUtility.isNumber(maxApplyQuantity))
                && (l_maxApplyQuantityGetByteLength > l_intEight))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01458,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
      * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
      */
    public abstract WEB3GenResponse createResponse();
}
@
