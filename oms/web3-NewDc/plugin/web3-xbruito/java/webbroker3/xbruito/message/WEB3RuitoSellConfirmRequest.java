head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����m�F���N�G�X�g�N���X(WEB3RuitoSellConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * �ݓ����m�F���N�G�X�g<BR>
 */
public class WEB3RuitoSellConfirmRequest extends WEB3RuitoCommonRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellConfirmRequest.class);
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
        
    /**
     * �w����@@<BR>
     * 2�F�S���A3�F���z�A4�F����<BR>
     */
    public String specifyDiv;
    /**
     * ��n���@@<BR>
     * 1�F��s�U���݁A2�F�،���������<BR>
     */
    public String deliveryDiv;
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 40762CC30299
     */
    public WEB3RuitoSellConfirmRequest()
    {
    }
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�������ʃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�w����@@���g�S���h���A<BR>
     *               this.�������ʂ�null�ȊO�̒l<BR>
     *              �ł���ꍇ�A ��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00093<BR>
     * �@@�P�|�Q�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *              this.�������ʂ�null�ł���ꍇ�A��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00094<BR>
     *   �P�|�R�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *           this.�������ʂ����l�ȊO�ł���ꍇ�A��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00095<BR>
     * �@@�P�|�S�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *           this.�������ʂ�0�ȉ��̒l�ł���ꍇ�A��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00096<BR>
     * �@@�P�|�T�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *               this.�������ʂ�11���𒴂���l�ł���ꍇ�A<BR>
     *              ��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00097<BR>
     * <BR>
     * �Q�j�@@�w����@@�`�F�b�N<BR>
     * �@@ this.�w����@@���A�g�S���h�A�g���z�h�A�g�����h�ȊO�ł���ꍇ�A<BR>
     * �@@ ��O���X���[����B<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_00073<BR>
     *    code     : 110<BR>
     * <BR>
     * �R�j�@@��n���@@�`�F�b�N<BR>
     *    this.��n���@@���A�g��s�U���݁h�܂��́g�،����������h<BR>
     *    �ȊO�ł���ꍇ�A��O���X���[����B<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_00099<BR>
     *    code     : 111<BR>
     * �S�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@ this.�����R�[�h��null�̒l�ł���Η�O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     *      code     : 91<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073675B021F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�w����@@���g�S���h���A�������ʂ�null�ȊO�ł���ꍇ�A ��O���X���[����
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv) && 
            this.ruitoOrderQuantity != null &&
            WEB3StringTypeUtility.isNotEmpty(this.ruitoOrderQuantity))
        {
            log.debug("�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00093,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B");            
        }

        //�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ�null�ł���ꍇ�A��O���X���[����
        if ((WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
            && this.ruitoOrderQuantity == null)
        {
            log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ����w��ł��B");            
        }

        //�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ����l�ȊO�ł���ꍇ�A��O���X���[����
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            try
            {
                double l_dblTemp = Double.parseDouble(this.ruitoOrderQuantity);
            }
            catch (NumberFormatException l_ex)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�������ʂ����l�ȊO�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00095,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�������ʂ����l�ȊO�ł��B");
            }
        }

        //�w����@@���g���z�h�܂��́g�����h�ł��芎��,�������ʂ�0�ȉ��̒l�ł���ꍇ�A��O���X���[����
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            if (Double.parseDouble(this.ruitoOrderQuantity) <= 0)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ�0�ȉ��̒l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00096,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎��,�������ʂ�0�ȉ��̒l�ł���ꍇ");
            }
        }

        //�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ�11���𒴂���l�ł���ꍇ�A��O���X���[����
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            if (this.ruitoOrderQuantity.length() > 11)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł���A���A�������ʂ�11���𒴂���l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00097,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ�11���𒴂���l�ł���ꍇ");
            }
        }

        //�w����@@���A�g�S���h�A�g���z�h�A�g�����h�ȊO�ł���ꍇ�A��O���X���[����
        if (!(WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("�w����@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�w����@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //��n���@@���A�g��s�U���݁h�܂��́g�،����������h�ȊO�ł���ꍇ�A��O���X���[����
        if (!(WEB3PaymentMethodDef.BANK_TRANSFER.equals(this.deliveryDiv)
            || WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT.equals(
                this.deliveryDiv)))
        {
            log.debug("��n���@@���A�g��s�U���݁h�܂��́g�،����������h�ȊO�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "��n���@@���A�g��s�U���݁h�܂��́g�،����������h�ȊO�ł��B");
        }

        //�����R�[�h��null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("�����R�[�h�����͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�����R�[�h�����͂���Ă��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ����m�F���X�|���X���쐬����<BR>
     * <BR>
     * ������ԊǗ�.get������()�Ŏ擾���������������N�G�X�g�f�[�^.<BR>
     * �m�F���������ɐݒ肷��B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762B4200E4
     */
    public WEB3GenResponse createResponse()
    {
        final String STR_METHOD_NAME = "createResponse()";
        log.entering(STR_METHOD_NAME);
        //�������擾
        Date l_orderBizDate = null;

        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044
//        try
//        {
//            l_orderBizDate =
//                WEB3GentradeTradingTimeManagement.getOrderBizDate();
//        }
//        catch (WEB3SystemLayerException l_ex)
//        {
//            log.error(
//                "__an NullPoint error__",
//                new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_00078,
//                    this.getClass().getName() + STR_METHOD_NAME));
//        }
//        log.debug(
//            "end l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate()");
        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044

        WEB3RuitoSellConfirmResponse l_RuitoSellConfirmResponse =
            new WEB3RuitoSellConfirmResponse(this);

        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044
//        l_RuitoSellConfirmResponse.checkDate = l_orderBizDate;
        // ----------------------------------------------- delete according to QA:WEB3-RUITO-A-CD-0044

        log.exiting(STR_METHOD_NAME);
        return l_RuitoSellConfirmResponse;
    }
}
@
