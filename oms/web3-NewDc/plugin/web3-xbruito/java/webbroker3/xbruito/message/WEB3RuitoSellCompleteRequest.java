head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ���񊮗����N�G�X�g�N���X(WEB3RuitoSellCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �� �E (���u) �V�K�쐬
                   2004/12/03 ��O�� (���u) �c�Ή�
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
 * �ݓ���񊮗����N�G�X�g<BR>
 */
public class WEB3RuitoSellCompleteRequest extends WEB3RuitoCommonRequest
{
    /**
    * ���O�o�̓��[�e�B���e�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellCompleteRequest.class);
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      
    /**
     * �m�F��������<BR>
     * <BR>
     * �m�F���X�|���X�̏����Ŏg�p�����l���i�[����B<BR>
     */
    public Date checkDate;
    /**
     * �Ïؔԍ�<BR>
     */
    public String password;
    /**
     * ��n���@@<BR>
     * 1�F��s�U���݁A2�F�،���������<BR>
     */
    public String deliveryDiv;
    /**
     * �w����@@<BR>
     * 2�F�S���A3�F���z�A4�F����<BR>
     */
    public String specifyDiv;
    /**
     * ����ID<BR>
     */
    public String orderId;
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 40762CE703B2
     */
    public WEB3RuitoSellCompleteRequest()
    {
    }
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�������ʃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�w����@@���g�S���h���A<BR>
     *               this.�������ʂ�null�ȊO�ł���<BR>
     *               �ꍇ�A ��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00093<BR>
     * �@@�P�|�Q�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *              this.�������ʂ�null�ł���ꍇ�A��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00094<BR>
     *   �P�|�R�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *              this.�������ʂ����l�ȊO�ł���ꍇ�A��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00095<BR>
     * �@@�P�|�S�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *              this.�������ʂ�0�ȉ��̒l�ł���ꍇ�A<BR>
     *              ��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00096<BR>
     * �@@�P�|�T�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A<BR>
     *              this.�������ʂ�11���𒴂���l�ł���ꍇ�A<BR>
     *              ��O���X���[����B<BR>
     *               class    : WEB3BusinessLayerException<BR>
     *               tag      : BUSINESS_ERROR_00097<BR>
     * <BR>
     * �Q�j�@@�w����@@�`�F�b�N<BR>
     * �@@ this.�w����@@���A�g�S���h�A�g���z�h�A�g�����h�ȊO�ł���ꍇ�A<BR>
     * �@@ ��O���X���[����B<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_00073<BR>
     * 
     * �R�j�@@��n���@@�`�F�b�N<BR>
     *    this.��n���@@���A�g��s�U���݁h�܂��́g�،����������h<BR>
     *    �ȊO�ł���ꍇ�A��O���X���[����B<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_00099<BR>
     * 
     * �S�j�@@�m�F���������̃`�F�b�N<BR>
     *    this.�m�F����������null�ł���ꍇ�A��O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00078<BR>
     * <BR>
     * �T�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@ this.�����R�[�h��null�̒l�ł���Η�O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �U�j�@@����ID�`�F�b�N <BR>
     * �@@�@@ this.����ID �� null�̏ꍇ�A��O���X���[����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407367600328
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
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
        {
            if (WEB3StringTypeUtility.isEmpty(this.ruitoOrderQuantity))
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ����w��ł��B");
            }
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

        //�m�F����������null�ł���ꍇ�A��O���X���[����
        if (this.checkDate == null)
        {
            log.debug("�m�F�������������͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�m�F�������������͂���Ă��܂���B");
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
        
        //�U�j�@@����ID�`�F�b�N
        //this.����ID �� null�ł���ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.debug("����ID�����͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "����ID�����͂���Ă��܂���B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ���񊮗����X�|���X���쐬����<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762B620067
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoSellCompleteResponse(this);
    }
}
@
