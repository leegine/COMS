head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefRefRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g(WEB3AdminToIfoOrderRefRefRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15�@@�]�V�q(���u) �V�K�쐬
                 : 2006/12/04  ������(���u)�@@���f��No.069
*/

package webbroker3.admintriggerorder.message;

import webbroker3.admintriggerorder.define.WEB3AdminToIfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefRefRequest extends WEB3AdminToOrderRefRefCommonRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderRefRefRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
    
    /**
     * (�w�����)<BR>
     * �w�����<BR>
     * <BR>
     * 0005�F�@@TOPIX <BR>
     * 0018�F�@@���o225 <BR>
     * 0016�F�@@���o300 <BR>
     * 0019�F�~�j���o225<BR>
     */
    public String targetProductCode = null;
    
    /**
     * (����)<BR>
     * ����<BR>
     * (YYYYMM)<BR>
     */
    public String delivaryMonth = null;
    
    /**
     * (�s�g���i)<BR>
     * �s�g���i<BR>
     */
    public String strikePrice = null;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * �I�v�V�������i�敪<BR>
     * <BR>
     * P�F�v�b�g�I�v�V���� <BR>
     * C�F�R�[���I�v�V���� <BR>
     * <BR>
     * ���敨�̏ꍇ�́Anull���Z�b�g�B<BR>
     */
    public String opProductType = null;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C80261
     */
    public WEB3AdminToIfoOrderRefRefRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate()���R�[������B <BR>
     * <BR>
     * �Q�j�@@�w����ʃ`�F�b�N<BR>
     * �@@this.�w����ʁ�null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02441<BR>
     * �@@�Q�|�Q�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02442<BR>
     * <BR>
     * �R�j�@@�����`�F�b�N<BR>
     * �@@this.������null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�����������̏ꍇ�A<BR>
     * �@@�u�����������ȊO�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02351<BR>
     * <BR>
     * �@@�R�|�Q�jthis.������YYYYMM�`���̒l�ł������ꍇ�A<BR>
     * �@@�u�������t�`���G���[�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00268<BR>
     * <BR>
     * �S�j�@@�s�g���i�`�F�b�N <BR>
     * �@@this.�s�g���i��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�S�|�P�jthis.�s�g���i�������̏ꍇ�A<BR>
     * �@@�@@�u�s�g���i�������ȊO�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00272<BR>
     * <BR>
     * �@@�S�|�Q�jthis.�s�g���i <= 0 �̏ꍇ�A<BR>
     * �@@�@@�u�s�g���i��0�ȉ��v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00273<BR>
     * <BR>
     * �@@�S�|�R�jthis.�s�g���i > 8���̒l�ł������ꍇ�A<BR>
     * �@@�@@�u�s�g���i�����G���[�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00274<BR>
     * <BR>
     * �T�j�@@�I�v�V�������i�敪�`�F�b�N <BR>
     * �@@this.�I�v�V�������i�敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�T�|�P�jthis.�I�v�V�������i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A <BR>
     * �@@�@@�@@�@@�u�I�v�V�������i�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"�v�b�g" <BR>
     * �@@�@@�@@�@@�E"�R�[��" <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00270<BR>
     * <BR>
     * �U�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�U�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�@@�U�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO�� <BR>
     * �@@�@@�@@�ݒ肳��Ă�����A <BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�E�u���X�R�[�h�v<BR>
     * �@@�@@�@@�E�u�ڋq�R�[�h�v<BR>
     * �@@�@@�@@�E�u�����R�[�h�v<BR>
     * �@@�@@�@@�E�u�s��R�[�h�v<BR>
     * �@@�@@�@@�E�u�����敪�v<BR>
     * �@@�@@�@@�E�u���i�敪�v<BR>
     * �@@�@@�@@�E�u����敪�v<BR>
     * �@@�@@�@@�E�u���s�����v<BR>
     * �@@�@@�@@�E�u�����L�������v<BR>
     * �@@�@@�@@�E�u�������ԁv<BR>
     * �@@�@@�@@�E�u�������v<BR>
     * �@@�@@�@@�E�u��n���v<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00086<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DF1E9E0073
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�X�[�p�[�N���X��validate()���R�[������B 
        super.validate();
        
        //�Q�j�@@�w����ʃ`�F�b�N
        if (this.targetProductCode != null)
        {
            //�@@�Q�|�P�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.targetProductCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02441,
                    getClass().getName() + "validate",
                    "�w����ʂ������ȊO�̒l�ł��B");
            }
            
            //�@@�Q�|�Q�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B
            if (this.targetProductCode.length() != 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02442,
                    getClass().getName() + "validate",
                    "�w����ʂ̃T�C�Y���s���ł��B");
            }
        }
        
        //�R�j�@@�����`�F�b�N
        //�@@this.������null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.delivaryMonth != null)
        {
            //�@@�R�|�P�jthis.�����������̏ꍇ�A
            //�@@�u�����������ȊO�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.delivaryMonth))
            {
                log.debug("�����������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02351,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����������ȊO�̒l�ł��B");
            }
            
            // �@@�R�|�Q�jthis.������YYYYMM�`���̒l�ł������ꍇ�A
            //�@@�u�������t�`���G���[�v�̗�O���X���[����B 
            if (!WEB3StringTypeUtility.isDateStr(this.delivaryMonth, "yyyyMM"))
            {
                log.debug("�������x�x�x�x�l�l�`���œ��͂��Ă��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00268,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������x�x�x�x�l�l�`���œ��͂��Ă��������B");
            }
        }
        
        //�S�j�@@�s�g���i�`�F�b�N 
        //�@@this.�s�g���i��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (this.strikePrice != null)
        {
            //�S�|�P�jthis.�s�g���i�������̏ꍇ�A
            //�@@�u�s�g���i�������ȊO�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.strikePrice))
            {
                log.debug("�s�g���i�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00272,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s�g���i�������ȊO�̒l�ł��B");
            }
            
            //�S�|�Q�jthis.�s�g���i <= 0 �̏ꍇ�A
            //�@@�u�s�g���i��0�ȉ��v�̗�O���X���[����B 
            if (Double.parseDouble(this.strikePrice) <= 0)
            {
                log.debug("�s�g���i��0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00273,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s�g���i��0�ȉ��̒l�ł��B");
            }
            
            //�S�|�R�jthis.�s�g���i > 8���̒l�ł������ꍇ�A
            //�@@�u�s�g���i�����G���[�v�̗�O���X���[����B
            if (this.strikePrice.length() > 8)
            {
                log.debug("�s�g���i�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00274,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s�g���i�̃T�C�Y���s���ł��B");
            }
        }
        
        //�T�j�@@�I�v�V�������i�敪�`�F�b�N
        //�@@this.�I�v�V�������i�敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.opProductType != null)
        {
            //�@@�T�|�P�jthis.�I�v�V�������i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
            //�@@�@@�@@�@@�u�I�v�V�������i�敪������`�̒l�v�̗�O���X���[����B
            //�@@�@@�@@�@@�E"�v�b�g" 
            //�@@�@@�@@�@@�E"�R�[��"
            if (!(WEB3IfoProductTypeDef.PUT_OPTIONS.equals(this.opProductType)
                ||WEB3IfoProductTypeDef.CALL_OPTIONS.equals(this.opProductType)))
            {
                log.debug("�I�v�V�������i�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�I�v�V�������i�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        // �V�j�@@�\�[�g�L�[�`�F�b�N 
        //�@@�V�|�P�jthis.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
        //�@@�@@�V�|�P�|�P�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��
        //�@@�@@�@@�ݒ肳��Ă�����A 
        //�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E�u���X�R�[�h�v
        //�@@�@@�@@�E�u�ڋq�R�[�h�v
        //�@@�@@�@@�E�u�����R�[�h�v
        //�@@�@@�@@�E�u�s��R�[�h�v
        //�@@�@@�@@�E�u�����敪�v
        //�@@�@@�@@�E�u���i�敪�v
        //�@@�@@�@@�E�u����敪�v
        //�@@�@@�@@�E�u���s�����v
        //�@@�@@�@@�E�u�����L�������v
        //�@@�@@�@@�E�u�������ԁv
        //�@@�@@�@@�E�u�������v
        //�@@�@@�@@�E�u��n���v
        int l_intLen = this.sortKeys.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!(WEB3AdminToIfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.PRODUCT_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3AdminToIfoKeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }    

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToIfoOrderRefRefResponse(this);
    }
}
@
