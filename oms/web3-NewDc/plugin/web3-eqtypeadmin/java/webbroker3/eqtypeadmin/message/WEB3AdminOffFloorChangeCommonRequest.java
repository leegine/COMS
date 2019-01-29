head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������X�V���ʃ��N�G�X�g (WEB3AdminOffFloorChangeCommonRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������X�V���ʃ��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��җ���O���������X�V�T�[�r�X�i�m�F�^�����j���N�G�X�g�f�[�^�̃X�[�p�[�N���X�B
 * <BR>
 * <BR>
 * ------<English>---------------<BR>
 * <BR>
 * WEB3AdminOffFloorChangeCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminOffFloorChangeService(validate/submit) request data<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public abstract class WEB3AdminOffFloorChangeCommonRequest extends WEB3GenRequest
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorChangeCommonRequest.class);

    /**
     * (����O���������L�[)<BR>
     * <BR>
     * ����O���������L�[�B<BR>
     * <BR>
     * productKey<BR>
     * <BR>
     */
    public WEB3AdminOffFloorProductKey productKey;

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
     * ----<English>---------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * �imaximum value of applyQuantity per person�j<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * @@roseuid 421AE35D015A
     */
    public WEB3AdminOffFloorChangeCommonRequest()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@����O���������L�[�`�F�b�N <BR>
     * <BR>
     * �@@�P�|�P�j�@@����O���������L�[.validate( )���R�[������B<BR>
     * <BR>
     * �Q�j�@@�������i�`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�������i��null�A����
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
     * �R�j�@@�\����������`�F�b�N<BR>
     * <BR>
     * �@@�R�|�P�j�@@this.�\�����������null�A����
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
     * �@@�@@�@@�@@�@@�@@�u�\����������̌�����8���𒴉߁v�̗�O��throw�B <BR>
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
     * 1)off floor productKey check<BR>
     * <BR>
     * �@@1-1)Call off floor productKey.validate( )<BR>
     * <BR>
     * 2)offFloorOrderPrice check<BR>
     * <BR>
     * �@@2-1)If this.offFloorOrderPrice��null and it meets either of the following
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
     * 3)maxApplyQuantity check<BR>
     * <BR>
     * �@@3-1)If this.maxApplyQuantity��null and it meets either of the following cases,
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
     * @@roseuid 41B7D3A40221
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intNine = 9;
        final int l_intMin = 0;
        final int l_intEight = 8;
        int l_intoffFloorOrderPriceNumLength =
            WEB3StringTypeUtility.getByteLength(this.offFloorOrderPrice);
        int l_intMaxApplyQuantityNumLength =
            WEB3StringTypeUtility.getByteLength(this.maxApplyQuantity);

        // 1-1 Call off floor productKey.validate( )
        this.productKey.validate();

        // 2-1 if offFloorOrderPrice is Not Numeric, throw Exception
        if (this.offFloorOrderPrice != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.offFloorOrderPrice)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01453,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // if offFloorOrderPrice is less than 0, throw Exception.
                if (Integer.parseInt(this.offFloorOrderPrice) <= l_intMin)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01454,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

            // if lenght of offFloorOrderPrice is greater than 9, throw Exception.
            if (l_intoffFloorOrderPriceNumLength > l_intNine)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01455,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 maxApplyQuantity is Not a numeric, throw Exception.
        if (this.maxApplyQuantity != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.maxApplyQuantity)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01456,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // if maxApplyQuantity is less than 0, throw Exception
                if (Integer.parseInt(this.maxApplyQuantity) <= l_intMin)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01457,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

            // if length of maxApplyQuantity is greater than 8, throw Exception.
            if (l_intMaxApplyQuantityNumLength > l_intEight)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01458,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
