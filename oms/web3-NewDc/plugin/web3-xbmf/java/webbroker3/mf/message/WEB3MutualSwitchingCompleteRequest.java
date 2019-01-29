head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷�������N�G�X�g�N���X(WEB3MutualSwitchingCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/25 ���� (���u) ���r���[
                   2004/12/07 ������ (���u) �c�Ή�
                   2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
                   2006/10/27 �����F (���u) ���f�� 482
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M���抷�������N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingCompleteRequest extends WEB3MutualCommonRequest
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingCompleteRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;

    /**
     * �������@@<BR>
     * <BR>
     * 0:��񐿋��@@1:���搿��<BR>
     */
    public String sellBuyDiv;

    /**
     * �����R�[�h�i�抷��j
     */
    public String switchingProductCode;

    /**
     * ���t�����敪<BR>
     * <BR>
     * 0:��ʁ@@1:����<BR>
     */
    public String switchingTaxType;

    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * ����ID<BR>
     */
    public String orderId;

    /**
     * (�Љ�敪)<BR>
     * �Љ�敪 <BR>
     * <BR>
     * null:�w�薳�� <BR>
     * 1:���ڎ�� <BR>
     * 2:�P���Љ� <BR>
     * 3:���i�Љ� <BR>
     * 4:������<BR>
     */
    public String introduceStoreDiv;

    /**
     * (�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h<BR>
     */
    public String introduceStoreCode;

    /**
     * (���M�抷�������N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8A7E600E7
     */
    public WEB3MutualSwitchingCompleteRequest()
    {

    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�抷�������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A80001D1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualSwitchingCompleteResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �Q)�@@�w����@@�`�F�b�N<BR>
     * �@@�Q�|�P)�@@this.�w����@@��null�̏ꍇ�A��O���X���[����B<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00400<BR>
     * �@@�Q�|�Q)�@@this.�w����@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h�S���h<BR>
     * �@@�@@�@@�@@�E�h���z�h<BR>
     * �@@�@@�@@�@@�E�h�����h<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00073<BR>
     * <BR>
     * �R�j�@@(�抷)���ʃ`�F�b�N <BR>
     * �@@�R�|�P�j�@@this.�w����@@���g�S���h���Athis.���ʂ�null�ȊO�̒l <BR>
     * �@@�@@�@@�@@�@@�ł���ꍇ�A ��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00093<BR>
     * �@@�R�|�Q�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʂ�null�ł���ꍇ�A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00094<BR>
     * �@@�R�|�R�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʂ����l�ȊO�ł���ꍇ�A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00095<BR>
     * �@@�R�|�S�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʁ�0�̏ꍇ�A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00096<BR>
     * �@@�R�|�T�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʂ̌�����11���̏ꍇ�A��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00097<BR>
     * <BR>
     * �S)�@@�������@@�`�F�b�N<BR>
     * �@@�S�|�P)�@@this.�������@@��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00401<BR>
     * �@@�S�|�Q)�@@this.�������@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h��񐿋��h<BR>
     * �@@�@@�@@�@@�E�h���搿���h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00402<BR>
     * <BR>
     * �T)�@@�����R�[�h�i�抷��j�`�F�b�N<BR>
     * �@@this.�����R�[�h�i�抷��j��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00414<BR>
     * <BR>
     * �U)�@@���t�����敪�`�F�b�N<BR>
     * �@@�U�|�P)�@@this.���t�����敪��null�̏ꍇ�A��O���X���[����B<BR> 
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00415<BR>
     * �@@�U�|�Q)�@@this.���t�����敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h��ʁh<BR>
     * �@@�@@�@@�@@�E�h����h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00416<BR>
     * <BR>
     * �V�j�@@�������̃`�F�b�N<BR>
     *    this.��������null�ł���ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00406<BR>
     * �W)�@@����ID�`�F�b�N <BR>
     * �@@this.����ID==null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * �X�j�@@ID�`�F�b�N<BR> 
     * �@@this.ID==null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * @@roseuid 40A8A81202BB
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@�����R�[�h�`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode))
        {
            log.debug("�����R�[�h����͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h����͂��Ă��������B");
        }

        //�Q)�@@�w����@@�`�F�b�N
        //�Q�|�P)�@@this.�w����@@��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.specifyDiv))
        {
            log.debug("�w����@@�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�����w��ł��B");
        }
        else
        {
            //�Q�|�Q)�@@this.�w����@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����
            if (!WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv)
                && !WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)
                && !WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv))
            {
                log.debug("�w����@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[�S��] [���z] [����]");
            }
        }

        // �R�j�@@(�抷)���ʃ`�F�b�N <BR>
        // �@@�R�|�P�j�@@this.�w����@@���g�S���h���Athis.����!=null�̏ꍇ
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(this.specifyDiv))
        {
            if (this.mutualOrderQuantity != null)
            {
                log.debug("�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00093,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B");
            }
        }
        else
        {

            //�R�|�Q�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�Athis.����==null�̏ꍇ
            if (WEB3StringTypeUtility.isEmpty(this.mutualOrderQuantity))
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�A" +
                    "�������ʂ����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�A" +
                    "�������ʂ����w��ł��B");
            }

            //�R�|�R�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�Athis.���ʂ����l�ȊO�̏ꍇ
            if (!WEB3StringTypeUtility.isNumber(this.mutualOrderQuantity))
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h" +
                    "�ł��芎�������ʂ����l�ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00095,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h" +
                    "�ł��芎�������ʂ����l�ȊO�ł��B");
            }

            //�R�|�S�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�Athis.���ʁ�0�̏ꍇ
            double l_dblMutualOrderQuantity =
                Double.parseDouble(this.mutualOrderQuantity);
            if (l_dblMutualOrderQuantity <= 0)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�A" +
                    "�������ʂ�0�ȉ��̒l�ł�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�A" +
                    "�������ʂ�0�ȉ��̒l�ł�");
            }

            //�R�|�T�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�Athis.���ʂ̌�����10���̏ꍇ
            if (WEB3StringTypeUtility.getByteLength(this.mutualOrderQuantity)
                > 10)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł���A���A" +
                    "�������ʂ�10���𒴂���l�ł�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00097,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h�ł���A���A" +
                    "�������ʂ�10���𒴂���l�ł�");
            }
        }

        //�S)�@@�������@@�`�F�b�N
        //�S�|�P)�@@this.�������@@��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.sellBuyDiv))
        {
            log.debug("�������@@�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00401,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������@@�����w��ł��B");
        }
        else
        {
            //�S�|�Q)�@@this.�������@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����
            if (!WEB3ClaimDivDef.SELL.equals(this.sellBuyDiv)
                && !WEB3ClaimDivDef.BUY.equals(this.sellBuyDiv))
            {
                log.debug("�������@@���h��񐿋��h�h���搿���h�ȊO�̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00402,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������@@���h��񐿋��h�h���搿���h�ȊO�̏ꍇ");
            }
        }

        //�T)�@@�����R�[�h�i�抷��j�`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.switchingProductCode))
        {
            log.debug("�����R�[�h�i�抷��j�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�i�抷��j�����w��ł��B");
        }

        //�U)�@@���t�����敪�`�F�b�N
        //�U�|�P)�@@this.���t�����敪��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.switchingTaxType))
        {
            log.debug("���t�����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00415,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����敪�����w��ł��B");
        }
        else
        {
            //�U�|�Q)�@@this.���t�����敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����
            if (!WEB3TaxTypeSpecialDef.NORMAL.equals(this.switchingTaxType)
                && !WEB3TaxTypeSpecialDef.SPECIAL.equals(this.switchingTaxType))
            {
                log.debug("���t�����敪���h��ʁh�A�h����h�ȊO�̏ꍇ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00416,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t�����敪���h��ʁh�A�h����h�ȊO�̏ꍇ");
            }
        }

        //�V�j�@@�������̃`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.orderedDate, "yyyyMMdd")))
        {
            log.debug("�����������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }
        
        //�W)�@@����ID�`�F�b�N
        if(this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }

        // �X�j�@@ID�`�F�b�N
        // �@@this.ID==null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("���M���Y�h�c�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���M���Y�h�c�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
