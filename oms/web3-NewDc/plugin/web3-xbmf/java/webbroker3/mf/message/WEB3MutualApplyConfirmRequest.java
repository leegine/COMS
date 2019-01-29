head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��W�����m�F���N�G�X�g�N���X(WEB3MutualApplyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 ���� (���u) �V�K�쐬
                   2006/09/11 ���� �d�l�ύX�E���f��482
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ���M��W�����m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualApplyConfirmRequest extends WEB3MutualCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualApplyConfirmRequest.class);
    
    /**
     * (�����敪)<BR>
     *  �����敪<BR>
     *  0:��ʁ@@1:����<BR>
     */
    public String taxType;
    
    /**
     * (���ϕ��@@)<BR>
     *  ���ϕ��@@<BR>
     *  1:�~�݁@@2:�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (�Љ�敪)<BR>
     * �Љ�敪<BR>
     * <BR>
     * null:�w�薳��<BR> 
     * 1:���ڎ��<BR>
     * 2:�P���Љ�<BR> 
     * 3:���i�Љ�<BR> 
     * 4:������<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * �Љ�X�R�[�h<BR>
     */
    public String introduceStoreCode;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>  
     * <BR>
     * �P)�@@�����R�[�h�`�F�b�N  <BR>
     * this.�����R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00079 <BR>
     * <BR>
     * �Q)�@@�w����@@�`�F�b�N <BR>
     *   �Q�|�P)�@@this.�w����@@==null�̏ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00400 <BR>
     *   �Q�|�Q)�@@this.�w����@@���ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR> 
     *  �E�h���z�h <BR>
     *  �E�h�����h <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00073 <BR>
     * <BR>
     * �R)�@@(��W)���ʃ`�F�b�N <BR>
     *  �R�|�P)�@@this.����==null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02230 <BR>
     *  �R�|�Q)�@@this.���ʂ����l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02231 <BR>
     *  �R�|�R)�@@this.���ʁ�0�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02232 <BR>
     *  �R�|�S)�@@this.���ʂ̌�����10���̏ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_02233 <BR>
     * <BR>
     * �S)�@@�����敪�`�F�b�N<BR>
     *  �S�|�P)�@@this.�����敪==null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00604 <BR>
     *  �S�|�Q)�@@this.�����敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B <BR>
     *      �E�h��ʁh <BR>
     *      �E�h����h <BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00605 <BR>
     * <BR>
     * �T)�@@���ϕ��@@�`�F�b�N <BR>
     *  �T�|�P)�@@this.���ϕ��@@==null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00403 <BR>
     *  �T�|�Q)�@@this.���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *      �E�h�~�݁h <BR>
     *      �E�h�O�݁h<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00404 <BR>
     * <BR>
     * �U�j�@@�������̃`�F�b�N <BR>
     *  this.������==null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00406 <BR>
     * <BR>
     * �V�j�@@ID�`�F�b�N <BR>
     * this.ID��null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_01967 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@�����R�[�h�`�F�b�N 
        //this.�����R�[�h==null�̏ꍇ�A��O���X���[����B
        if (this.mutualProductCode == null)
        {
            log.debug("�����R�[�h����͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h����͂��Ă��������B");
        } 

        //�Q)�@@�w����@@�`�F�b�N 
        //�Q�|�P)�@@this.�w����@@==null�̏ꍇ�A��O���X���[����B
        if (this.specifyDiv == null)
        {
            log.debug("�w����@@�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�����w��ł��B");
        } 
        
        //�Q�|�Q)�@@this.�w����@@���ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�E�h���z�h 
        //�E�h�����h
        if (!(WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv) || 
            WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("�w����@@�̒l���A�u���z�v�u�����v�ȊO�̒l�ł���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�̒l���A�u���z�v�u�����v�ȊO�̒l�ł���B");
        }

        //�R)�@@(��W)���ʃ`�F�b�N 
        //�R�|�P)�@@this.����==null�̏ꍇ�A��O���X���[����B 
        if (this.mutualOrderQuantity == null)
        {
            log.debug("��W���ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02230,
                getClass().getName() + "." + STR_METHOD_NAME,
                "��W���ʂ����w��ł��B");
        }
        
        //�R�|�Q)�@@this.���ʂ����l�ȊO�̏ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isNumber(this.mutualOrderQuantity))
        {
            log.debug("��W���ʂ����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "��W���ʂ����l�ȊO�̒l�ł��B");
        }
        
        //�R�|�R)�@@this.���ʁ�0�̏ꍇ�A��O���X���[����B 
        if (Double.parseDouble(this.mutualOrderQuantity) <= 0)
        {
            log.debug("��W���ʂ�0�ȉ��̒l�ł���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02232,
                getClass().getName() + "." + STR_METHOD_NAME,
                "��W���ʂ�0�ȉ��̒l�ł���B");
        }
        
        //�R�|�S)�@@this.���ʂ̌�����10���̏ꍇ�A��O���X���[����B  
        if (this.mutualOrderQuantity.length() > 10)
        {
            log.debug("��W���ʂ�10���𒴂��܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02233,
                getClass().getName() + "." + STR_METHOD_NAME,
                "��W���ʂ�10���𒴂��܂����B");
        }
        
        //�S)�@@�����敪�`�F�b�N 
        //�S�|�P)�@@this.�����敪==null�̏ꍇ�A��O���X���[����B
        if (this.taxType == null)
        {
            log.debug("�����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����w��ł��B");
        } 
        
        //�S�|�Q)�@@this.�����敪���ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�E�h��ʁh 
        //�E�h����h
        if (!(WEB3TaxTypeSpecialDef.NORMAL.equals(this.taxType)
            || WEB3TaxTypeSpecialDef.SPECIAL.equals(this.taxType)))
        {
            log.debug("�����敪�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        //�T)���ϕ��@@�`�F�b�N 
        //�T�|�P)�@@this.���ϕ��@@==null�̏ꍇ�A��O���X���[����B
        if (this.settleDiv == null)
        {
            log.debug("���ϕ��@@�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00403,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@�����w��ł��B");
        }
        
        //�T�|�Q)�@@this.���ϕ��@@���ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�E�h�~�݁h 
        //�E�h�O�݁h 
        if (!(WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)))
        {
            log.debug("���ϕ��@@���h�~�݁h���́h�O�݁h�ȊO�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00404,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ");
        }

        //�U�j�������̃`�F�b�N 
        //this.������==null�̏ꍇ�A��O���X���[����B 
        if (this.orderedDate == null)
        {
            log.debug("�����������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }

        //�V�jID�`�F�b�N 
        //this.ID��null�̏ꍇ�A��O���X���[����B
        if (this.id != null)
        {
            log.debug("���M���YID���w��s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01967,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���M���Y�h�c = [" + this.id + "]");
        }   
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���M��W�����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualApplyConfirmResponse(this);
    }   
}
@
