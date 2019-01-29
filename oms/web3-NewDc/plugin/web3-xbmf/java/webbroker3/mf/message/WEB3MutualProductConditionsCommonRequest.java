head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductConditionsCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���������o�^���ʃ��N�G�X�g(WEB3MutualProductConditionsCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ���� (���u) �V�K�쐬                                   
Revesion History : 2006/05/18 ���� (���u) �d�l�ύX�E���f��414
Revesion History : 2006/10/19 ���� (���u) �d�l�ύX�E���f��499�A505
Revesion History : 2007/04/24 �����F (���u) �d�l�ύX�E���f��567
Revesion History : 2007/10/15 ���^�] (���u) �d�l�ύX�E���f��579
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3RecruitCommissionDivDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3UnitTypeProductDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M���������o�^���ʃ��N�G�X�g)<BR>
 * ���M���������o�^���ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualProductConditionsCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412011741L;
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualProductConditionsCommonRequest.class);
    
    /**
     * (�������)<BR>
     * ���M���������o�^���ʏ��
     */
    public WEB3MutualProductConditionsCommonInfo mutualProductInfo;
    
    /**
     * (���M���������o�^���ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 417745C90255
     */
    public WEB3MutualProductConditionsCommonRequest()
    {
    }
    
    /**
     * (validate)<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * 1) �������̃`�F�b�N<BR>
     * �@@ this.�������null�̏ꍇ�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01251<BR>
     * <BR>
     * 2) WEBBROKER3�戵�󋵂̃`�F�b�N <BR>
     * �@@this.WEBBROKER3�戵��!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A<BR> 
     * �@@��O���X���[����B <BR>
     * �@@�h0:WEBBROKER3�Ŏ�舵��Ȃ��h <BR>
     * �@@�h1:WEBBROKER3�Ŏ�舵���h <BR>
     * �@@�h2:�X�������̂݁h <BR>
     *  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01292<BR>
     *<BR>
     * 3) �����R�[�h�̃`�F�b�N<BR>
     * �@@ this.�������.�����R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01252<BR>
     * <BR>
     * 4) �������i�p���j�̃`�F�b�N<BR>
     * �@@ this.�������.�������i�p���j!=null�ł���A<BR>
     *    ���Z�b�g����Ă���l�ɑS�p����<BR>
     * �@@ ���܂܂�Ă���ꍇ�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01253<BR>
     * <BR>
     * 5) �w����@@�i���t�j�̃`�F�b�N<BR>
     * �@@  this.�������.�w����@@�i���t�j!=null�ł���A���Z�b�g����Ă���l��<BR>
     * �@@�@@�ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�I���w��"<BR>
     * �@@�@@�@@"���z"<BR>
     * �@@�@@�@@"����"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01254<BR>
     * <BR>
     * 6) �w����@@�i���j�̃`�F�b�N<BR>
     * �@@  this.�������.�w����@@�i���j!=null�ł���A���Z�b�g����Ă���l��<BR>
     * �@@�@@�ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�I���w��"<BR>
     * �@@�@@�@@"���z"<BR>
     * �@@�@@�@@"����"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01255<BR>
     * <BR>
     * 7) �w����@@�i�抷�j�̃`�F�b�N<BR>
     * �@@  this.�������.�w����@@�i�抷�j!=null�ł���A���Z�b�g����Ă���l��<BR>
     * �@@�@@�ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�I���w��"<BR>
     * �@@�@@�@@"���z"<BR>
     * �@@�@@�@@"����"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01256<BR>
     * <BR>
     * 8) �w����@@�i��W�j�̃`�F�b�N <BR>
     * �@@this.�������.�w����@@�i��W�j!=null�ł���A���Z�b�g����Ă���l�� <BR>
     * �@@�@@�ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@"�I���w��" <BR>
     * �@@�@@�@@"���z" <BR>
     * �@@�@@�@@"����" <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_02264<BR>
     * <BR>
     * 9) this.�������.�����R�[�h�ȊO�A�S�Ă̑�����null�������ꍇ�A<BR>
     *    ��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01257<BR>
     * <BR>
     * 10) this.�������.���t�\�敪�i�����������j!=null�ł���A<BR>
     *    ���Z�b�g����Ă���<BR>
     * �@@ �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s��"<BR>
     * �@@�@@�@@"��"<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01258<BR>
     * <BR>
     * 11) this.�������.���\�敪�i�����������j!=null�ł���A<BR>
     *     ���Z�b�g����Ă���<BR>
     * �@@  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s��"<BR>
     * �@@�@@�@@"��"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01259<BR>
     * <BR>
     * 12) this.�������.�抷�\�敪�i�����������j!=null�ł���A<BR>
     *      ���Z�b�g����Ă���<BR>
     * �@@   �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s��"<BR>
     * �@@�@@�@@"��"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01260<BR>
     * <BR>
     * 13) this.�������.��W�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� <BR>
     * �@@�l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@"�s��" <BR>
     * �@@�@@�@@"��" <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_02265<BR>
     * <BR>
     * 14) this.�������.���t�\�敪�i�����������j!=null�ł���A<BR>
     *      ���Z�b�g����Ă���<BR>
     * �@@   �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s��"<BR>
     * �@@�@@�@@"��"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01261<BR>
     * <BR>
     * 15) this.�������.���\�敪�i�����������j!=null�ł���A<BR>
     *      ���Z�b�g����Ă���<BR>
     * �@@   �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s��"<BR>
     * �@@�@@�@@"��"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01262<BR>
     * <BR>
     * 16) this.�������.�抷�\�敪�i�����������j!=null�ł���A<BR>
     *      ���Z�b�g����Ă���<BR>
     * �@@   �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�s��"<BR>
     * �@@�@@�@@"��"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01263<BR>
     * <BR>
     * 17) this.�������.��W�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� <BR>
     * �@@�l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@"�s��" <BR>
     * �@@�@@�@@"��" <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02266<BR>
     * <BR>
     * 18) ������t��~���Ԃ̃`�F�b�N<BR>
     *  18-1) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A<BR>
     *          ���l�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01264<BR>
     *  18-2) this.�������.������t��~�I�����ԁi�����j!=null�ł���A<BR>
     *          ���l�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01265<BR>
     *  18-3) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A<BR>
     *          ��������t��~�I�����ԁi�����j!=null�ł���A<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01266<BR>
     * �@@�@@     ���h�J�n���ԁ��I�����ԁh�̏ꍇ�A��O���X���[����B<BR>
     *  18-4) this.�������.������t��~�I�����ԁi�����j!=null�ł���A����<BR>
     * �@@�@@    �h�I�����ԁi�����j��235959�h�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01267<BR>
     *  18-5) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A<BR>
     *          ���l�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01268<BR>
     *  18-6) this.�������.������t��~�I�����ԁi�����j!=null�ł���A<BR>
     *          ���l�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01269<BR>
     *  18-7) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A
     *          ��������t��~�I�����ԁi�����j!=null�ł���A<BR>
     * �@@�@@     ���h�J�n���ԁ��I�����ԁh�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01270<BR>
     *  18-8) this.�������.������t��~�I�����ԁi�����j!=null�ł���A����<BR>
     * �@@�@@     �h�I�����ԁ�235959�h�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01271<BR>
     * <BR>
     * 19) �I�y���[�V�������t�̃`�F�b�N<BR>
     *      this.�������.�I�y���[�V�������t��null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01272<BR>
     * <BR>
     * 20) �������i�a���j�̃`�F�b�N <BR>
     * �@@this.�������.�������i�a���j!=null�ł���A���Z�b�g����Ă���l�ɔ��p�J�i���� <BR>
     * �@@�@@���܂܂�Ă���ꍇ�A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01996<BR>
     * <BR>
     * 21)���t�����敪�̃`�F�b�N <BR>
     *   this.�������.���t�����敪!=null�ł���A���Z�b�g����Ă���l���ȉ��̂����ꂩ <BR>
     *   �ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@"���t��" <BR>
     * �@@�@@�@@"�抷���t�̂݉�" <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02267<BR>
     * <BR>
     * 22)��n���@@�̃`�F�b�N <BR>
     * �@@this.�������,��n���@@!=null�ł���A���Z�b�g����Ă���l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A<BR> 
     * �@@��O���X���[����B <BR>
     * �@@�@@�@@"0�F�I���w��" <BR>
     * �@@�@@�@@"1�F��s�U��" <BR>
     * �@@�@@�@@"2�F�،���������"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00099<BR>
     * <BR>
     * 23)�������������敪�̃`�F�b�N <BR>
     *   this.�������������敪!=null�ł���A���Z�b�g����Ă���l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A<BR> 
     *   �@@��O���X���[����B <BR>
     *   �@@�@@�@@"�ʏ����" <BR>
     *   �@@�@@�@@"���t�̂�" <BR>
     *   �@@�@@�@@"���̂�" <BR>
     *   �@@�@@�@@"����"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02679<BR>
     * <BR>
     * 24)��W�萔���敪�̃`�F�b�N<BR>
     * �@@this.�������,��W�萔���敪!=null�ł���A���Z�b�g����Ă���l���ȉ���<BR>
     * �@@�����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"0�F�Ȃ�"<BR>
     * �@@�@@�@@"1�F���g"<BR>
     * �@@�@@�@@"2�F�O�g"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02949<BR>
     * @@throws WEB3BaseException
     * @@roseuid 417745CA00CF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1)�������̃`�F�b�N 
        //  this.�������null�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo == null)
        {
            log.debug("������񂪖��w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01251,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������񂪖��w��ł��B");
        }
        
        //2)WEBBROKER3�戵�󋵂̃`�F�b�N 
        //  this.WEBBROKER3�戵��!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A 
        //  ��O���X���[����B 
        //     �h0:WEBBROKER3�Ŏ�舵��Ȃ��h 
        //     �h1:WEBBROKER3�Ŏ�舵���h
        //     �h2:�X�������̂݁h  
        if (this.mutualProductInfo.web3TreatmentFlag != null)
        {
            if (!(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN.equals(
                    this.mutualProductInfo.web3TreatmentFlag)
                || WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN.equals(
                    this.mutualProductInfo.web3TreatmentFlag)
                || WEB3SystemHandlingDivDef.MAIL_REQUEST_ONLY.equals(
                    this.mutualProductInfo.web3TreatmentFlag)))
            {
                log.debug("WEBBROKER3�戵�󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01292,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "WEBBROKER3�戵�󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //3)�����R�[�h�̃`�F�b�N  
        //  this.�������.�����R�[�h��null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.mutualProductCode))
        {
            log.debug("�������̖����R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������̖����R�[�h�����w��ł��B");
        }
        
        //4)�������i�p���j�̃`�F�b�N 
        //  this.�������.�������i�p���j!=null�ł���A���Z�b�g����Ă���l�ɑS�p���� 
        //  ���܂܂�Ă���ꍇ�A��O���X���[����B 
        if ((this.mutualProductInfo.engProductName != null)
            && (!WEB3StringTypeUtility.isSingle(this.mutualProductInfo.engProductName)))
        {
            log.debug("�������̖������i�p���j�̒l�ɑS�p�������܂܂�Ă���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01253,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������̖������i�p���j�̒l�ɑS�p�������܂܂�Ă���B");
        }
        
        //5)�w����@@�i���t�j�̃`�F�b�N 
        //  this.�������.�w����@@�i���t�j!=null�ł���A���Z�b�g����Ă���l�� 
        //  �ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //    "�I���w��" 
        //    "���z" 
        //    "����" 
        if (this.mutualProductInfo.buySelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.buySelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.buySelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.buySelectable)))
            {
                log.debug("�������̎w����@@�i���t�j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̎w����@@�i���t�j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //6)�w����@@�i���j�̃`�F�b�N 
        //  this.�������.�w����@@�i���j!=null�ł���A���Z�b�g����Ă���l�� 
        //  �ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�I���w��" 
        //�@@ "���z" 
        //�@@ "����" 
        if (this.mutualProductInfo.sellSelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.sellSelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.sellSelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.sellSelectable)))
            {
                log.debug("�������̎w����@@�i���j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01255,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̎w����@@�i���j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //7)�w����@@�i�抷�j�̃`�F�b�N 
        //  this.�������.�w����@@�i�抷�j!=null�ł���A���Z�b�g����Ă���l�� 
        //  �ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�I���w��" 
        //�@@ "���z" 
        //�@@ "����" 
        if (this.mutualProductInfo.switchingSelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.switchingSelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.switchingSelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.switchingSelectable)))
            {
                log.debug("�������̎w����@@�i�抷�j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01256,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̎w����@@�i�抷�j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //8) �w����@@�i��W�j�̃`�F�b�N 
        //�@@this.�������.�w����@@�i��W�j!=null�ł���A���Z�b�g����Ă���l�� 
        //�@@�ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@�@@"�I���w��" 
        //�@@�@@"���z" 
        //�@@�@@"����" 
        if (this.mutualProductInfo.applySelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.applySelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.applySelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.applySelectable)))
            {
                log.debug("�������̎w����@@�i��W�j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02264,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̎w����@@�i��W�j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //9) this.�������.�����R�[�h�ȊO�A�S�Ă̑�����null�������ꍇ�A��O���X���[����B 
        if (
            WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.web3TreatmentFlag)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.jpnProductName)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.engProductName)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.categoryCode)
            && this.mutualProductInfo.buyStartDate == null
            && this.mutualProductInfo.buyEndDate == null
            && this.mutualProductInfo.sellSwitchingStartDate == null
            && this.mutualProductInfo.sellSwitchingEndDate == null
            && this.mutualProductInfo.buyClaimStartDate == null
            && this.mutualProductInfo.buyClaimEndDate == null
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.buySelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellSelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingSelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todayBuyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todaySellPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todaySwitchingPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDayBuyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDaySellPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDaySwitchingPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseStartTimeFull)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseEndTimeFull)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseStartTimeHalf)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseEndTimeHalf)
            && this.mutualProductInfo.operationDate == null
            && this.mutualProductInfo.applyAbleStartDate == null
            && this.mutualProductInfo.applyAbleEndDate == null
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applySelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todayApplyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDayApplyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.buyRestrictionDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.deliveryVariation)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.unitTypeProductDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnMinAmtBuy)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnUnitAmtBuy)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnMinAmtAdd)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnUnitAmtAdd)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnMinAmtSell)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnUnitAmtSell)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyCommissionDiv))
        {
            log.debug("�������̑S�Ă̑��������w��ł��B�i�����R�[�h�ȊO�j�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01257,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������̑S�Ă̑��������w��ł��B�i�����R�[�h�ȊO�j�B");
        }
        
        //10)this.�������.���t�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�s��" 
        //�@@ "��" 
        if (this.mutualProductInfo.todayBuyPossDiv != null)
        {
            if (!(WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(
                    this.mutualProductInfo.todayBuyPossDiv)
                || WEB3BuyPossibleDivDef.ACQUIRED.equals(
                    this.mutualProductInfo.todayBuyPossDiv)))
            {
                log.debug("�������̔��t�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01258,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̔��t�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //11) this.�������.���\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�s��" 
        //�@@ "��" 
        if (this.mutualProductInfo.todaySellPossDiv != null)
        {
            if (!(WEB3SellPossibleDivDef.NOT_SELL.equals(
                    this.mutualProductInfo.todaySellPossDiv)
                || WEB3SellPossibleDivDef.SELL.equals(
                    this.mutualProductInfo.todaySellPossDiv)))
            {
                log.debug("�������̉��\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01259,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̉��\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //12) this.�������.�抷�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�s��" 
        //�@@ "��" 
        if (this.mutualProductInfo.todaySwitchingPossDiv != null)
        {
            if (!(WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(
                    this.mutualProductInfo.todaySwitchingPossDiv)
                || WEB3SwtPossibleDivDef.SWITCHING.equals(
                    this.mutualProductInfo.todaySwitchingPossDiv)))
            {
                log.debug("�������̏抷�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01260,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̏抷�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //13) this.�������.��W�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //�@@�l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@"�s��" 
        //�@@"��" 
        if (this.mutualProductInfo.todayApplyPossDiv != null)
        {
            if (!(WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(
                    this.mutualProductInfo.todayApplyPossDiv)
                || WEB3MFRecruitPossibleDivDef.RECRUIT.equals(
                    this.mutualProductInfo.todayApplyPossDiv)))
            {
                log.debug("�������̕�W�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02265,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̕�W�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //14) this.�������.���t�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�s��" 
        //�@@ "��" 
        if (this.mutualProductInfo.nextDayBuyPossDiv != null)
        {
            if (!(WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(
                    this.mutualProductInfo.nextDayBuyPossDiv)
                || WEB3BuyPossibleDivDef.ACQUIRED.equals(
                    this.mutualProductInfo.nextDayBuyPossDiv)))
            {
                log.debug("�������̔��t�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01261,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̔��t�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //15)this.�������.���\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�s��" 
        //�@@ "��" 
        if (this.mutualProductInfo.nextDaySellPossDiv != null)
        {
            if (!(WEB3SellPossibleDivDef.NOT_SELL.equals(
                    this.mutualProductInfo.nextDaySellPossDiv)
                || WEB3SellPossibleDivDef.SELL.equals(
                    this.mutualProductInfo.nextDaySellPossDiv)))
            {
                log.debug("�������̉��\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01262,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̉��\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //16) this.�������.�抷�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //  �l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@ "�s��" 
        //�@@ "��" 
        if (this.mutualProductInfo.nextDaySwitchingPossDiv != null)
        {
            if (!(WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(
                    this.mutualProductInfo.nextDaySwitchingPossDiv)
                || WEB3SwtPossibleDivDef.SWITCHING.equals(
                    this.mutualProductInfo.nextDaySwitchingPossDiv)))
            {
                log.debug("�������̏抷�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01263,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̏抷�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //17) this.�������.��W�\�敪�i�����������j!=null�ł���A���Z�b�g����Ă��� 
        //�@@�l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@�@@"�s��" 
        //�@@�@@"��" 
        if (this.mutualProductInfo.nextDayApplyPossDiv != null)
        {
            if (!(WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(
                    this.mutualProductInfo.nextDayApplyPossDiv)
                || WEB3MFRecruitPossibleDivDef.RECRUIT.equals(
                    this.mutualProductInfo.nextDayApplyPossDiv)))
            {
                log.debug("�������̕�W�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02266,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������̕�W�\�敪�i�����������j�̒l�͋K�薇���l�͈̔͂ł͂Ȃ��ł��B");
            }
        }
        
        //18) ������t��~���Ԃ̃`�F�b�N 
        //  18-1) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A
        //      ���l�������ȊO�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo.orderCloseStartTimeFull != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseStartTimeFull))
        {
            log.debug("������t��~�J�n���ԁi�����j�������ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01264,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������t��~�J�n���ԁi�����j�������ȊO�̒l�ł���B");
        }
        
        //18-2) this.�������.������t��~�I�����ԁi�����j!=null�ł���A
        //  ���l�������ȊO�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo.orderCloseEndTimeFull != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseEndTimeFull))
        {
            log.debug("������t��~�I�����ԁi�����j�������ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01265,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������t��~�I�����ԁi�����j�������ȊO�̒l�ł���B");
        }
        
        //18-3) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A
        //  ��������t��~�I�����ԁi�����j!=null�ł���A
        //�@@���h�J�n���ԁ��I�����ԁh�̏ꍇ�A��O���X���[����B 
        if (this.mutualProductInfo.orderCloseStartTimeFull != null
            && this.mutualProductInfo.orderCloseEndTimeFull != null)
        {
            if (this.mutualProductInfo.orderCloseStartTimeFull.compareTo(
                    this.mutualProductInfo.orderCloseEndTimeFull) > 0)
            {
                log.debug("������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01266,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
            }
        }
       
        //18-4) this.�������.������t��~�I�����ԁi�����j!=null�ł���A���� 
        //�@@�@@�h�I�����ԁi�����j��235959�h�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo.orderCloseEndTimeFull != null)
        {
            if (Integer.parseInt(this.mutualProductInfo.orderCloseEndTimeFull)
                > 2359)
            {
                log.debug("������t��~�I�����ԁi�����j���g2359�h�𒴂��Ă��܂��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01267,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������t��~�I�����ԁi�����j���g2359�h�𒴂��Ă��܂��B");
            }
        }
       
        //18-5) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A
        //���l�������ȊO�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo.orderCloseStartTimeHalf != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseStartTimeHalf))
        {
            log.debug("������t��~�J�n���ԁi�����j�������ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01268,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������t��~�J�n���ԁi�����j�������ȊO�̒l�ł���B");
        }
       
        //18-6) this.�������.������t��~�I�����ԁi�����j!=null�ł���A
        //  ���l�������ȊO�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo.orderCloseEndTimeHalf != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseEndTimeHalf))
        {
            log.debug("������t��~�I�����ԁi�����j�������ȊO�̒l�ł���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01269,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������t��~�I�����ԁi�����j�������ȊO�̒l�ł���B");
        }
       
        //18-7) this.�������.������t��~�J�n���ԁi�����j!=null�ł���A
        //  ��������t��~�I�����ԁi�����j!=null�ł���A 
        //  ���h�J�n���ԁ��I�����ԁh�̏ꍇ�A��O���X���[����B 
        if (this.mutualProductInfo.orderCloseStartTimeHalf != null
            && this.mutualProductInfo.orderCloseEndTimeHalf != null)
        {
            if (this.mutualProductInfo.orderCloseStartTimeHalf.compareTo(
                    this.mutualProductInfo.orderCloseEndTimeHalf) > 0)
            {
                log.debug("������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01270,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������t��~�J�n���ԁi�����j��������t��~�I�����ԁi�����j�ȏ�ł��B");
            }
        }
        
        //18-8) this.�������.������t��~�I�����ԁi�����j!=null�ł���A���� 
        //�@@�@@�h�I�����ԁ�235959�h�̏ꍇ�A��O���X���[����B 
        if (this.mutualProductInfo.orderCloseEndTimeHalf != null)
        {
            if (Integer.parseInt(this.mutualProductInfo.orderCloseEndTimeHalf)
                > 2359)
            {
                log.debug("������t��~�I�����ԁi�����j���g2359�h�𒴂��Ă��܂��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01271,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������t��~�I�����ԁi�����j���g2359�h�𒴂��Ă��܂��B");
            }
        }
        
        //19) �I�y���[�V�������t�̃`�F�b�N 
        //  this.�������.�I�y���[�V�������t��null�̏ꍇ�A��O���X���[����B
        if (this.mutualProductInfo.operationDate == null)
        {
            log.debug("�������̃I�y���[�V�������t�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01272,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������̃I�y���[�V�������t�����w��ł��B");
        }
        
        //20)�������i�a���j�̃`�F�b�N 
        //  this.�������.�������i�a���j!=null�ł���A���Z�b�g����Ă���l�ɔ��p�J�i���� 
        //  ���܂܂�Ă���ꍇ�A��O���X���[����B 
        if ((this.mutualProductInfo.jpnProductName != null)
            && (WEB3StringTypeUtility.has1byteKana(this.mutualProductInfo.jpnProductName)))
        {
            log.debug("�������̖������i�a���j�̒l�ɔ��p�J�i�������܂܂�Ă���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01996,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������̖������i�a���j�̒l�ɔ��p�J�i�������܂܂�Ă���B");
        }
        
        //21)���t�����敪�̃`�F�b�N 
        // this.�������.���t�����敪!=null�ł���A���Z�b�g����Ă���l���ȉ��̂����ꂩ 
        //�ł͂Ȃ��ꍇ�A��O���X���[����B 
        //�@@�@@"���t��" 
        //�@@�@@"�抷���t�̂݉�" 
        if (this.mutualProductInfo.buyRestrictionDiv != null)
        {
            if (!WEB3BuyLimitDivDef.BUY_POSSIBLE.equals(this.mutualProductInfo.buyRestrictionDiv) &&
                !WEB3BuyLimitDivDef.ONLY_SWITCHING_BUY_POSSIBLE.equals(this.mutualProductInfo.buyRestrictionDiv))
            {
                log.debug("�������.���t�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02267,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������.���t�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //22)��n���@@�̃`�F�b�N 
        //�@@this.�������,��n���@@!=null�ł���A���Z�b�g����Ă���l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A
        //�@@��O���X���[����B
        //�@@�@@�@@"0�F�I���w��" 
        //�@@�@@�@@"1�F��s�U��" 
        //�@@�@@�@@"2�F�،���������"
        if(this.mutualProductInfo.deliveryVariation != null)
        {
            if(!WEB3DeliveryMethodDef.SELECT_DESIGNATE.equals(this.mutualProductInfo.deliveryVariation) 
                && !WEB3DeliveryMethodDef.BANK_TRANSFER.equals(this.mutualProductInfo.deliveryVariation) 
                && !WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL.equals(this.mutualProductInfo.deliveryVariation))
            {
                log.debug("�������.��n���@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������.��n���@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //23)�������������敪�̃`�F�b�N
        // this.�������������敪!=null�ł���A���Z�b�g����Ă���l���ȉ��̂����ꂩ�ł͂Ȃ��ꍇ�A
        // ��O���X���[����B
        //   "�ʏ����"
        //   "���t�̂�"
        //   "���̂�"
        //   "����"
        if (this.mutualProductInfo.unitTypeProductDiv != null)
        {
            if ((!WEB3UnitTypeProductDivDef.NORMAL_PRODUCT.equals(this.mutualProductInfo.unitTypeProductDiv)) &&
                (!WEB3UnitTypeProductDivDef.BUY.equals(this.mutualProductInfo.unitTypeProductDiv)) &&
                (!WEB3UnitTypeProductDivDef.OF_A_CONTRACT.equals(this.mutualProductInfo.unitTypeProductDiv)) &&
                (!WEB3UnitTypeProductDivDef.BOTH.equals(this.mutualProductInfo.unitTypeProductDiv)))
            {
                log.debug("�������������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02679,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //24)��W�萔���敪�̃`�F�b�N
        // �@@this.�������,��W�萔���敪!=null�ł���A���Z�b�g����Ă���l���ȉ���
        // �@@�����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B
        // �@@�@@�@@"0�F�Ȃ�"
        // �@@�@@�@@"1�F���g"
        // �@@�@@�@@"2�F�O�g"
        if (this.mutualProductInfo.applyCommissionDiv != null)
        {
            if ((!WEB3RecruitCommissionDivDef.NOTHING.equals(this.mutualProductInfo.applyCommissionDiv))
                && (!WEB3RecruitCommissionDivDef.WITHIN_THE_LIMIT.equals(this.mutualProductInfo.applyCommissionDiv))
                && (!WEB3RecruitCommissionDivDef.WITHOUT_THE_LIMIT.equals(
                    this.mutualProductInfo.applyCommissionDiv)))
            {
                log.debug("��W�萔���敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02949,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��W�萔���敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF816102A6
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
