head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�����m�F���N�G�X�g�N���X(WEB3MutualBuyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2006/09/11 ���� �d�l�ύX�E���f��482
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��535
*/
package webbroker3.mf.message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����t�����m�F���N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyConfirmRequest extends WEB3MutualCommonRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_confirm";

    /**
     * �����敪<BR>
     * <BR>
     * 0:��ʁ@@1:����@@2:���̑�<BR>
     */
    public String taxType;
    
    /**
     * ���ϕ��@@<BR>
     * <BR>
     * 1:�~�݁@@2:�O��<BR>
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
     * (�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h<BR>
     */
    public String introduceStoreCode;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;   
       
    /**
     * (���M���t�����m�F���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8807803E0
     */
    public WEB3MutualBuyConfirmRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyConfirmRequest.class); 
        
           
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M���t�����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     * @@roseuid 40A8808703E0
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualBuyConfirmResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * �Q)�@@�w����@@�`�F�b�N<BR>
     * �@@�Q�|�P)�@@this.�w����@@��null�̏ꍇ�A��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00400 <BR>
     * �@@�Q�|�Q)�@@this.�w����@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h���z�h<BR>
     * �@@�@@�@@�@@�E�h�����h<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00073 <BR>
     * <BR>
     * �R)�@@(���t)���ʃ`�F�b�N<BR>
     * �@@�R�|�P)�@@�R�|�P)�@@this.����==null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00418 <BR>
     * �@@�R�|�Q)�@@this.���ʂ����l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00419 <BR>
     * �@@�R�|�R)�@@this.���ʁ�0�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00420 <BR>
     *�@@ �R�|�S)�@@this.���ʂ̌�����11���̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00421 <BR>
     * <BR>
     * �S)�@@�����敪�`�F�b�N<BR>
     * �@@�S�|�P)�@@this.�����敪��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00415 <BR>
     * �@@�S�|�Q)�@@this.�����敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h��ʁh<BR>
     * �@@�@@�@@�@@�E�h����h<BR>
     * �@@�@@�@@�@@�E�h���̑��h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00416 <BR>
     * <BR>
     * �T)�@@���ϕ��@@�`�F�b�N<BR>
     * �@@�T�|�P)�@@this.���ϕ��@@��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00403 <BR>
     * �@@�T�|�Q)�@@this.���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h�~�݁h<BR>
     * �@@�@@�@@�@@�E�h�O�݁h<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00404 <BR>
     * <BR>
     * �U�j�@@�������̃`�F�b�N<BR>
     *    this.��������null�ł���ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00406 <BR>
     * �V�j�@@ID�`�F�b�N<BR> 
     *    this.ID��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00XXX <BR>
     * <BR>
     * @@roseuid 40A8974102C6
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);       
         
        //this.�����R�[�h��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode))
        {
            log.debug("�����R�[�h����͂��Ă��������B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h����͂��Ă��������B");
        }
        
        //�Q�|�P)�@@this.�w����@@��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.specifyDiv))
        {
            log.debug("�w����@@�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�����w��ł��B");
        }
        
        //�Q�|�Q)�@@this.�w����@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����
        if (!(WEB3SellDivDef.COUNT_DESIGNATE.equals(this.specifyDiv)
            || WEB3SellDivDef.MONEY_DESIGNATE.equals(this.specifyDiv)))
        {
            log.debug("�w����@@�̒l���A�u���z�v�u�����v�ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�̒l���A�u���z�v�u�����v�ȊO�̒l�ł���B");
        }
        
        //�R�|�P)�@@this.����==null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.mutualOrderQuantity))
        {
            log.debug("�i���t�j���ʂ����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00418,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�i���t�j���ʂ����w��ł��B");
        }
        
        //�R�|�Q)�@@this.���ʂ����l�ȊO�̏ꍇ�A��O���X���[����
        try
        {
            Double.parseDouble(this.mutualOrderQuantity);
        }
        catch (NumberFormatException l_ex)
        {
            log.error("���ʂ����l�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00419,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���ʂ����l�ȊO�̏ꍇ");
        }
        
        //�R�|�R)�@@this.���ʁ�0�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.mutualOrderQuantity) <= 0)
        {
            log.debug("(���t)���ʂ�0�ȉ��̒l�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00420,
                getClass().getName() + "." + STR_METHOD_NAME,
                "(���t)���ʂ�0�ȉ��̒l�̏ꍇ");
        }
        
        //�R�|�S)�@@this.���ʂ̌�����10���̏ꍇ�A��O���X���[����B 
        if (this.mutualOrderQuantity.length() > 10)
        {
            log.debug("(���t)���ʂ�10���𒴂���l�ł���");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00421,
                getClass().getName() + "." + STR_METHOD_NAME,
                "(���t)���ʂ�10���𒴂���l�ł���");
        }
        
        //�S�|�P)�@@this.�����敪��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.taxType))
        {
            log.debug("���t�����敪�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00415,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����敪�����w��ł��B");
        }
        
        //�S�|�Q)�@@this.�����敪���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����
        if (!(WEB3MFAccountDivDef.NORMAL.equals(this.taxType)
            || WEB3MFAccountDivDef.SPECIAL.equals(this.taxType)
            || WEB3MFAccountDivDef.OTHER.equals(this.taxType)))
        {
            log.debug("���t�����敪���h��ʁh�A�h����h�A�h���̑��h�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00416,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����敪���h��ʁh�A�h����h�A�h���̑��h�ȊO�̏ꍇ");
        }
        
        //�T�|�P)�@@this.���ϕ��@@��null�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.settleDiv))
        {
            log.debug("���ϕ��@@�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00403,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@�����w��ł��B");
        }
        
        //�T�|�Q)�@@this.���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����
        if (!(WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)))
        {
            log.debug("���ϕ��@@���h�~�݁h���́h�O�݁h�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00404,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ");
        }
        
        //this.��������null�ł���ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.orderedDate, "yyyyMMdd")))
        {
            log.debug("�����������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }
        
        // �V�j�@@ID�`�F�b�N
        // �@@this.ID��null�̏ꍇ�A��O���X���[����B
        if (this.id != null)
        {
            log.debug("���M���YID���w��s�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01967,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���M���Y�h�c = [" + this.id + "]");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
