head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������ʃ��N�G�X�g(WEB3FeqCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������������ʃ��N�G�X�g)<BR>
 * �O�������������ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqCommonRequest extends WEB3GenRequest 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     * <BR>
     * �������P���敪���h�w�l�h�̏ꍇ�A�ݒ�B<BR>
     */
    public String limitPrice;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F�����Ȃ�<BR>
     * 3�F��t<BR>
     * 4�F����<BR>
     * 7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 1�F��������<BR>
     * 2�F�o����܂Œ���<BR>
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     * <BR>
     * �����������敪���h�o����܂Œ����h�̏ꍇ�A�ݒ�<BR>
     */
    public Date expirationDate;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * �t�w�l�p���������P��<BR>
     * <BR>
     * �������������h�t�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * �t�w�l�p�����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�<BR>
     * 2�F�ȉ�<BR>
     * <BR>
     * �������������h�t�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (W�w�l�p���������P��)<BR>
     * W�w�l�p���������P��<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (W�w�l�p�����������Z�q)<BR>
     * W�w�l�p�����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�<BR>
     * 2�F�ȉ�<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W�w�l�p�����P���敪)<BR>
     * W�w�l�p�����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W�w�l�p�����P��)<BR>
     * W�w�l�p�����P��<BR>
     * <BR>
     * ��W�w�l�p�����P���敪���h�w�l�h�̏ꍇ�A�ݒ�B<BR>
     */
    public String wLimitPrice;
    
    /**
     * @@roseuid 42CE3A0702AF
     */
    public WEB3FeqCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�������ʂ̃`�F�b�N<BR>
     * <BR>
     *    this.�������� == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00074<BR>
     *    this.�������� != ���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00075<BR>
     *    this.�������� <= 0 or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00076<BR>
     *    this.��������.length > 9<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     *    �̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �Q�j�����P���敪�`�F�b�N<BR>
     * <BR>
     * �Q�|�P�j<BR>
     *    this.�����P���敪 == null<BR>
     * <BR>
     *    �̏ꍇ�A�u�����P���敪��null�v�̗�O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00184<BR>
     * <BR>
     * �Q�|�Q�j<BR>
     *    this.�����P���敪 != �i�h���s�h or �h�w�l�h�j<BR>
     * <BR>
     *    �̏ꍇ�A�u�����P���敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00185<BR>
     * <BR>
     * �R�j���s�����`�F�b�N<BR>
     * <BR>
     * �R�|�P�j<BR>
     *    this.���s���� == null<BR>
     * <BR>
     *    �̏ꍇ�A�u���s������null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00197<BR>
     * <BR>
     * �R�|�Q�j<BR>
     *    this.���s���� != �i�h�����Ȃ��h or �h��t�h or �h�����h or<BR>
     * �h�s�o���������s�h�j<BR>
     * <BR> 
     *    �̏ꍇ�A�u���s����������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02115<BR>
     * <BR>
     * �S�j���������敪�`�F�b�N<BR>
     * <BR>
     * �S�|�P�j<BR>
     *    this.���������敪 == null<BR>
     * <BR>
     *    �̏ꍇ�A�u���������敪��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00208<BR>
     * <BR>
     * �S�|�Q�j<BR>
     *    this.���������敪 != �i�h��������h or �h�o����܂Œ����h�j<BR>
     * <BR>
     *    �̏ꍇ�A�u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00209<BR>
     * <BR>
     * �T�j���������`�F�b�N<BR>
     * <BR>
     * �T�|�P�j<BR>
     *    this.�������� == null<BR>
     * <BR>
     *    �̏ꍇ�A�u����������null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02116<BR>
     * <BR>
     * �T�|�Q�j<BR>
     *    this.�������� != �i�h�w��Ȃ��h or �h�t�w�l�h or �hW�w�l�h�j<BR>
     * <BR>
     *    �̏ꍇ�A�u��������������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02117<BR>
     * <BR>
     * �U�j�����P���敪�E�P�� �̐������`�F�b�N<BR>
     * <BR>
     * �U�|�P�j<BR>
     *    this.�����P���敪 == �h�w�l�h and<BR>
     *    �i<BR>
     *     this.�����P�� == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02118<BR>
     *     this.�����P�� != ���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02119<BR>
     *     this.�����P�� <= 0 or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02120<BR>
     *     this.�����P��.length > 8<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02093<BR>
     *    �j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>�@@�@@ 
     * �U�|�Q�j<BR>
     *    this.�����P���敪 == �h���s�h and<BR>
     *    this.�����P�� != �inull or �h0�h�j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00116<BR>
     * <BR>
     * �V�j�����̃`�F�b�N<BR>
     * <BR>
     * �V�|�P�j<BR>
     *    this.���������敪 == �h��������h and<BR>
     *    this.�����L������ != null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00117<BR>
     * <BR>
     * �V�|�Q�j<BR>
     *    this.���������敪 == �h�o����܂Œ����h and<BR>
     *    this.�����L������ == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00210<BR>
     * <BR>
     * �W�j���������̃`�F�b�N�P�i�h�w��Ȃ��h�j<BR>
     * <BR>
     *    this.�������� == �h�w��Ȃ��h and<BR>
     *    (<BR>
     *     this.�t�w�l�p���������P�� != �inull or �h0�h�j or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01872<BR>
     *     this.�t�w�l�p�����������Z�q != null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01873<BR>
     *     this.W�w�l�p���������P�� != �inull or �h0�h�j or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01874<BR>
     *     this.W�w�l�p�����������Z�q != null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01875<BR>
     *     this.W�w�l�p�����P���敪 != null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01876<BR>
     *     this.W�w�l�p�����P�� != �inull or �h0�h�j<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01877<BR>
     *    )<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �X�j���������̃`�F�b�N�Q�i�h�t�w�l�h�j <BR>
     * <BR>
     * �X�|�P)<BR>
     *    this.�������� == �h�t�w�l�h and<BR>
     *    (<BR>
     *     this.W�w�l�p���������P�� != �inull or �h0�h�j or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01878<BR>
     *     this.W�w�l�p�����������Z�q != null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01879<BR>
     *     this.W�w�l�p�����P���敪 != null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01880<BR>
     *     this.W�w�l�p�����P�� != �inull or �h0�h�j<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01881<BR>
     *    )<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �X�|�Q)<BR>
     *    this.�������� == �h�t�w�l�h and<BR>
     *    (<BR>
     *     this.�t�w�l�p���������P�� == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02121<BR>
     *     this.�t�w�l�p���������P�� != ���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02123<BR>
     *     this.�t�w�l�p���������P�� <= 0 or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02122<BR>
     *     this.�t�w�l�p���������P��,length > 8 or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02124<BR>
     *     this.�t�w�l�p�����������Z�q == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02125<BR>
     *     this.�t�w�l�p�����������Z�q != �i�h�ȏ�h or �h�ȉ��h�j<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02126<BR>
     *    )<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�O�j���������̃`�F�b�N�R�i�hW�w�l�h�j<BR>
     * <BR>
     * �P�O�|�P�j<BR>
     *    this.�������� == �hW�w�l�h and<BR>
     *    (<BR>
     *     this.�t�w�l�p���������P�� != �inull or �h0�h�j or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01882<BR>
     *     this.�t�w�l�p�����������Z�q != null<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01883<BR>
     *    )<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�O�|�Q�j<BR>
     *    this.�������� == �hW�w�l�h and<BR>
     *    (<BR>
     *     this.W�w�l�p�����������Z�q == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02127<BR>
     *     this.W�w�l�p�����������Z�q != �i�h�ȏ�h or �h�ȉ��h�j or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02128<BR>
     *     this.W�w�l�p�����P���敪 == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02129<BR>
     *     this.W�w�l�p�����P���敪 != �i�h���s�h or �h�w�l�h�j or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02130<BR>
     *     this.W�w�l�p���������P�� == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02131<BR>
     *     this.W�w�l�p���������P�� != ���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02132<BR>
     *     this.W�w�l�p���������P�� <= 0 or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02133<BR>
     *     this.W�w�l�p���������P��.length > 8<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02134<BR>
     *    )<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�P�jW�w�l�p�����P���敪�EW�w�l�p�����P�� �̐������`�F�b�N<BR>
     * <BR>
     * �P�P�|�P�j<BR>
     *    this.W�w�l�p�����P���敪 == �h�w�l�h and<BR>
     *    (<BR>
     *     this.W�w�l�p�����P�� == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02135<BR>
     *     this.W�w�l�p�����P�� != ���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02136<BR>
     *     this.W�w�l�p�����P�� <= 0 or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02137<BR>
     *     this.W�w�l�p�����P��.length > 8<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02138<BR>
     *    )<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�P�|�Q�j<BR>
     *    this.W�w�l�p�����P���敪 == �h���s�h and<BR>
     *    this.W�w�l�p�����P�� != �inull or �h0�h�j<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02139<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�Q�j���������E���s�����̃`�F�b�N<BR>
     *    this.���������敪 == �h�o����܂Œ����h and<BR>
     *    this.���s���� != �h�����Ȃ��h<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00125<BR>
     * <BR>
     * �P�R�j�����P���E���s�����̃`�F�b�N <BR>
     *   this.�����P���敪 != �h�w�l�h and <BR>
     *   this.���s���� == �h�s�o���������s�h<BR> 
     *<BR>
     *  �̏ꍇ�A��O���X���[����<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00114<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C33EC0361
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�������ʂ̃`�F�b�N 
        //this.�������� == null or 
        if (this.orderQuantity == null)
        {
            log.debug("�������ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������ʂ����w��ł��B" + this.orderQuantity);
        }
        
        //this.�������� != ���� or 
        if (!WEB3StringTypeUtility.isInteger(this.orderQuantity))
        {
            log.debug("�������ʂ������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������ʂ������ȊO�̒l�ł��B" + this.orderQuantity);
        }
        
        //this.�������� <= 0 or 
        if (Integer.parseInt(this.orderQuantity) <= 0)
        {
            log.debug("�������ʂ�0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������ʂ�0�ȉ��̒l�ł��B" + this.orderQuantity);
        }
        
        //this.��������.length > 9�̏ꍇ�́A��O���X���[����B
        if (this.orderQuantity.length() > 9)
        {
            log.debug("�������ʂ̌������s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������ʂ̌������s���ł��B" + this.orderQuantity.length());
        }
        
        //�Q�j�����P���敪�`�F�b�N 
        //�Q�|�P�j 
        //this.�����P���敪 == null 
        //�̏ꍇ�A�u�����P���敪��null�v�̗�O���X���[����B  
        if (this.orderPriceDiv == null)
        {
            log.debug("�����P���敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����P���敪�����w��ł��B" + this.orderPriceDiv);
        }
        
        //�Q�|�Q�j 
        //this.�����P���敪 != �i�h���s�h or �h�w�l�h�j 
        //�̏ꍇ�A�u�����P���敪������`�̒l�v�̗�O���X���[����B 
        if (!(WEB3OrderPriceDivDef.MARKET_PRICE).equals(this.orderPriceDiv) &&
            !(WEB3OrderPriceDivDef.LIMIT_PRICE).equals(this.orderPriceDiv) )
        {
            log.debug("�����P���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����P���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B" + 
                this.orderPriceDiv);
        }
    
        //�R�j���s�����`�F�b�N 
        //�R�|�P�j 
        //this.���s���� == null 
        //�̏ꍇ�A�u���s������null�v�̗�O���X���[����B 
        if (this.execCondType == null)
        {
            log.debug("���s���������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00197,
                this.getClass().getName() + STR_METHOD_NAME,
                "���s���������w��ł��B" + this.execCondType);
        }
        
        //�R�|�Q�j 
        //this.���s���� != �i�h�����Ȃ��h or �h��t�h or �h�����h or �h
        //���������s�h�j �̏ꍇ�A�u���s����������`�̒l�v�̗�O���X���[����B 
        if (!(WEB3ExecutionConditionDef.NO_CONDITION).equals(this.execCondType)
            && !(WEB3ExecutionConditionDef.AT_MARKET_OPEN).equals(this.execCondType)
            && !(WEB3ExecutionConditionDef.AT_MARKET_CLOSE).equals(this.execCondType)
            && !(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED).equals(this.execCondType))
        {
            log.debug("���s����������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02115,
                this.getClass().getName() + STR_METHOD_NAME,
                "���s����������`�̒l�ł��B" + this.execCondType);
        }
        
        //�S�j���������敪�`�F�b�N 
        //�S�|�P�j 
        //this.���������敪 == null 
        //�̏ꍇ�A�u���������敪��null�v�̗�O���X���[����B 
        if (this.expirationDateType == null)
        {
            log.debug("���������敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00208,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������敪�����w��ł��B" + this.expirationDateType);
        }
        
        //�S�|�Q�j 
        //   this.���������敪 != �i�h��������h or �h�o����܂Œ����h�j 
        //   �̏ꍇ�A�u���������敪������`�̒l�v�̗�O���X���[����B 
        if (!(WEB3OrderExpirationDateTypeDef.DAY_LIMIT).equals(this.expirationDateType)
            && !(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER).equals(this.expirationDateType))
        {
            log.debug("���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B" + 
                this.expirationDateType);
        }
        
        //�T�j���������`�F�b�N 
        //�T�|�P�j 
        //   this.�������� == null 
        //   �̏ꍇ�A�u����������null�v�̗�O���X���[����B
        if (this.orderCondType == null)
        {
            log.debug("����������null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02116,
                this.getClass().getName() + STR_METHOD_NAME,
                "����������null�ł��B" + this.orderCondType);
        } 
        
        //�T�|�Q�j 
        //   this.�������� != �i�h�w��Ȃ��h or �h�t�w�l�h or �hW�w�l�h�j 
        //   �̏ꍇ�A�u��������������`�̒l�v�̗�O���X���[����B 
        if (!(WEB3OrderingConditionDef.DEFAULT).equals(this.orderCondType)
            && !(WEB3OrderingConditionDef.STOP_LIMIT_PRICE).equals(this.orderCondType)
            && !(WEB3OrderingConditionDef.W_LIMIT_PRICE).equals(this.orderCondType))
        {
            log.debug("��������������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02117,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������������`�̒l�ł��B" + this.orderCondType);
        }

        //�U�j�����P���敪�E�P�� �̐������`�F�b�N 
        //�U�|�P�j 
        //this.�����P���敪 == �h�w�l�h and 
        //�i 
        
        if ((WEB3OrderPriceDivDef.LIMIT_PRICE).equals(this.orderPriceDiv))
        {
            //this.�����P�� == null or 
            if (this.limitPrice == null)
            {
                log.debug("�����P�������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02118,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����P�������w��ł��B" + this.limitPrice);
            }
            
            //this.�����P�� != ���� or 
            if (!WEB3StringTypeUtility.isNumber(this.limitPrice))
            {
                log.debug("�����P�������l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02119,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����P�������l�ȊO�̒l�ł��B" + this.limitPrice);
            }
            
            //this.�����P�� < 0 or
            if (Double.parseDouble(this.limitPrice) <= 0)
            {
                log.debug("�����P����0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02120,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����P����0�ȉ��̒l�ł��B" + this.limitPrice);
            }

            //this.�����P��-������ > 6�� or this.�����P��-������ > 5�� 
            int l_limitPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.limitPrice);   
            int l_limitPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.limitPrice);
            if (l_limitPriceIntegerLength > 6 || l_limitPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02093.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02093,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����P�� = "   
                        + this.limitPrice);
            }
			
        }

        //�U�|�Q�j 
        //this.�����P���敪 == �h���s�h and 
        if ((WEB3OrderPriceDivDef.MARKET_PRICE).equals(this.orderPriceDiv))
        {
            //this.�����P�� != �inull or �h0�h�j 
            //�̏ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isEmpty(this.limitPrice) 
                && !("0").equals(this.limitPrice))
            {
                log.debug("�����P���敪���g0�F���s�h�̏ꍇ�́A�����P���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00116,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����P���敪���g0�F���s�h�̏ꍇ�́A�����P���w��s�ł��B"
                        + "this.�����P���敪 = "
                        + this.orderPriceDiv
                        + "this.�����P�� = "
                        + this.limitPrice);
            }
        }
         
        //�V�j�����̃`�F�b�N 
        //�V�|�P�j 
        //this.���������敪 == �h��������h and 
        //this.�����L������ != null 
        //�̏ꍇ�A��O���X���[����B 
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType) 
            && this.expirationDate != null)
        {
            log.debug("���������敪���g1�F��������h�̏ꍇ�́A�����L�������w��s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00117,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������敪���g1�F��������h�̏ꍇ�́A�����L�������w��s�ł��B"
                    + "this.���������敪 = "
                    + this.expirationDateType
                    + "this.�����L������"
                    + this.expirationDate);
        }
        
        //�V�|�Q�j 
        //this.���������敪 == �h�o����܂Œ����h and 
        //this.�����L������ == null 
        //�̏ꍇ�A��O���X���[����B 
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && this.expirationDate == null)
        {
            log.debug("�o����܂Œ����̏ꍇ�́A�����L���������w�肵�Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00210,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o����܂Œ����̏ꍇ�́A�����L���������w�肵�Ă��������B"
                    + "this.���������敪 = "
                    + this.expirationDateType
                    + "this.�����L������ = "
                    + this.expirationDate);
        }
        
        //�W�j���������̃`�F�b�N�P�i�h�w��Ȃ��h�j 
        //this.�������� == �h�w��Ȃ��h and 
        //( 
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            //this.�t�w�l�p���������P�� != �inull or �h0�h�j or 
            if ((this.stopOrderCondPrice != null) &&
                !"0".equals(stopOrderCondPrice))
            {
                log.debug("���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "�t�w�l�p���������P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "�t�w�l�p���������P�����w��s�ł��B"
                        + "this.�������� = "
                        + this.orderCondType
                        + "this.�t�w�l�p���������P�� = "
                        + this.stopOrderCondPrice);
            }
            
            //this.�t�w�l�p�����������Z�q != null or 
            if (this.stopOrderCondOperator != null)
            {
                log.debug("���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "�t�w�l�p�����������Z�q���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "�t�w�l�p�����������Z�q���w��s�ł��B"
                        + "this.�t�w�l�p�����������Z�q = "
                        + this.stopOrderCondOperator);
            }
            
            //this.W�w�l�p���������P�� != �inull or �h0�h�j or 
            if ((this.wlimitOrderCondPrice != null) &&
                !("0".equals(this.wlimitOrderCondPrice)))
            {
                log.debug("���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p���������P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p���������P�����w��s�ł��B"
                        + "this.W�w�l�p���������P�� = "
                        + this.wlimitOrderCondPrice);
            }
       
            //this.W�w�l�p�����������Z�q != null or 
            if (this.wlimitOrderCondOperator != null)
            {
                log.debug("���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p�����������Z�q���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p�����������Z�q���w��s�ł��B"
                        + "this.W�w�l�p�����������Z�q = "
                        + this.wlimitOrderCondOperator);
            }
        
            //this.W�w�l�p�����P���敪 != null or 
            if (this.wLimitOrderPriceDiv != null)
            {
                log.debug("���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p�����P���敪���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01876,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p�����P���敪���w��s�ł��B"
                        + "this.W�w�l�p�����P���敪 = "
                        + this.wLimitOrderPriceDiv);
            }
        
            //this.W�w�l�p�����P�� != �inull or �h0�h�j 
            //) 
            //�̏ꍇ�A��O���X���[����B 
            if ((this.wLimitPrice != null) &&
                !"0".equals(this.wLimitPrice))
            {
                log.debug("���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p�����P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A" +
                    "W�w�l�p�����P�����w��s�ł��B"
                        + "this.W�w�l�p�����P�� = "
                        + this.wLimitPrice);
            }
        }

        //�X�j���������̃`�F�b�N�Q�i�h�t�w�l�h�j  
        //�X�|�P) 
        //this.�������� == �h�t�w�l�h and 
        //( 
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            //this.W�w�l�p���������P�� != �inull or �h0�h�j or 
            if ((this.wlimitOrderCondPrice != null) 
                && !"0".equals(this.wlimitOrderCondPrice))
            {
                log.debug("���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p���������P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p���������P�����w��s�ł��B"
                        + "this.W�w�l�p���������P�� = "
                        + this.wlimitOrderCondPrice);
            }
            
            //this.W�w�l�p�����������Z�q != null or 
            if (this.wlimitOrderCondOperator != null)
            {
                log.debug("���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p�����������Z�q���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p�����������Z�q���w��s�ł��B"
                        + "this.W�w�l�p�����������Z�q = "
                        + this.wlimitOrderCondOperator);
            }
        
            //this.W�w�l�p�����P���敪 != null or 
            if (this.wLimitOrderPriceDiv != null)
            {
                log.debug("���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p�����������Z�q���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01880,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p�����P���敪���w��s�ł��B"
                        + "this.W�w�l�p�����P���敪 = "
                        + this.wLimitOrderPriceDiv);
            }
        
            //this.W�w�l�p�����P�� != �inull or �h0�h�j 
            //) 
            //�̏ꍇ�A��O���X���[����B 
            if ((this.wLimitPrice != null) &&
                !"0".equals(this.wLimitPrice))
            {
                log.debug("���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p�����P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g1�F�t�w�l�h�̏ꍇ�́A" +
                    "W�w�l�p�����P�����w��s�ł��B"
                        + "this.W�w�l�p�����P�� = "
                        + this.wLimitPrice);
            }
            
            //�X�|�Q) 
            //this.�������� == �h�t�w�l�h and 
            //( 
            //this.�t�w�l�p���������P�� == null or
            if (this.stopOrderCondPrice == null)
            {
                log.debug("���������敪���g�t�w�l�h�Ȃ̂ɁA" +
                    "�t�w�l�p���������P�������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02121,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�Ȃ̂ɁA" +
                    "�t�w�l�p���������P�������w��ł��B"
                        + "this.�t�w�l�p���������P�� = "
                        + this.stopOrderCondPrice);
            }
             
            //this.�t�w�l�p���������P�� != ���� or 
            if (!WEB3StringTypeUtility.isNumber(this.stopOrderCondPrice))
            {
                log.debug("���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p���������P�������l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02123,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p���������P�������l�ȊO�̒l�ł��B"
                        + "this.�t�w�l�p���������P�� = "
                        + this.stopOrderCondPrice);
            }
            
            //this.�t�w�l�p���������P�� < 0 or 
            if (Double.parseDouble(this.stopOrderCondPrice) <= 0)
            {
                log.debug("���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p���������P����0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02122,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p���������P����0�ȉ��̒l�ł��B"
                        + "this.�t�w�l�p���������P�� = "
                        + this.stopOrderCondPrice);
            }

            //this.�t�w�l�p���������P��-������ > 6�� or this.�t�w�l�p���������P��-������ > 5�� 
            int l_stopOrderCondPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.stopOrderCondPrice);   
            int l_stopOrderCondPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.stopOrderCondPrice);
            if (l_stopOrderCondPriceIntegerLength > 6 || l_stopOrderCondPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02124.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02124,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�t�w�l�p���������P�� = "   
                        + this.stopOrderCondPrice);
            }
            
            //this.�t�w�l�p�����������Z�q == null or 
            if (this.stopOrderCondOperator == null)
            {
                log.debug("���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p�����������Z�q�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02125,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p�����������Z�q�����w��ł��B"
                        + "this.�t�w�l�p�����������Z�q = "
                        + this.stopOrderCondPrice);
            }

            //this.�t�w�l�p�����������Z�q != �i�h�ȏ�h or �h�ȉ��h�j 
            //) 
            //�̏ꍇ�A��O���X���[����B 
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(
                this.stopOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(
                    this.stopOrderCondOperator))
            {
                log.debug("���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p�����������Z�q������`�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02126,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�ŁA" +
                    "�t�w�l�p�����������Z�q������`�̒l�ł��B"
                        + "this.�t�w�l�p�����������Z�q = "
                        + this.stopOrderCondPrice);
            }    
        }

        //�P�O�j���������̃`�F�b�N�R�i�hW�w�l�h�j 
        //�P�O�|�P�j 
        //this.�������� == �hW�w�l�h and 
        //( 
        if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            //this.�t�w�l�p���������P�� != �inull or �h0�h�j or 
            if((this.stopOrderCondPrice != null) 
                && !"0".equals(this.stopOrderCondPrice))
            {
                log.debug("���������敪���g2�FW�w�l�h�̏ꍇ�́A" +
                    "�t�w�l�p���������P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g2�FW�w�l�h�̏ꍇ�́A" +
                    "�t�w�l�p���������P�����w��s�ł��B"
                        + "this.�t�w�l�p�����������Z�q = "
                        + this.stopOrderCondPrice);
            }
            
            //this.�t�w�l�p�����������Z�q != null 
            //) �̏ꍇ�A��O���X���[����B 
            if(this.stopOrderCondOperator != null)
            {
                log.debug("���������敪���g2�FW�w�l�h�̏ꍇ�́A" +
                    "�t�w�l�p�����������Z�q���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g2�FW�w�l�h�̏ꍇ�́A" +
                    "�t�w�l�p�����������Z�q���w��s�ł��B"
                        + "this.�t�w�l�p�����������Z�q = "
                        + this.stopOrderCondOperator);
            }
            
            //�P�O�|�Q�j 
            //this.�������� == �hW�w�l�h and 
            //( 
            //this.W�w�l�p�����������Z�q == null or 
            if(this.wlimitOrderCondOperator == null)
            {
                log.debug("���������敪���gW�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����������Z�q�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02127,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����������Z�q�����w��ł��B" 
                    + "this.W�w�l�p�����������Z�q = "
                     + this.wlimitOrderCondOperator);
            }
            
            //this.W�w�l�p�����������Z�q != �i�h�ȏ�h or �h�ȉ��h�j or 
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
            {
                log.debug("���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p�����������Z�q������`�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02128,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p�����������Z�q������`�̒l�ł��B" 
                    + "this.W�w�l�p�����������Z�q = " 
                    + this.wlimitOrderCondOperator);
            }
            
            //this.W�w�l�p�����P���敪 == null or 
            if(this.wLimitOrderPriceDiv == null)
            {
                log.debug("���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p�����P���敪�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02129,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����P���敪�����w��ł��B" 
                    + "this.W�w�l�p�����P���敪 = " 
                    + this.wLimitOrderPriceDiv);
            }
            
            //this.W�w�l�p�����P���敪 != �i�h���s�h or �h�w�l�h�j or 
            if (!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
                && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv))
            {
                log.debug("���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p�����P���敪������`�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02130,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p�����P���敪������`�̒l�ł��B" 
                    + "this.W�w�l�p�����P���敪 = " 
                    + this.wLimitOrderPriceDiv);
            }
            
            //this.W�w�l�p���������P�� == null or 
            if(this.wlimitOrderCondPrice == null)
            {
                log.debug("���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p���������P�������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02131,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p���������P�������w��ł��B" 
                    + "this.W�w�l�p���������P�� = " 
                    + this.wlimitOrderCondPrice);
            }
            
            //this.W�w�l�p���������P�� != ���� or 
            if(!WEB3StringTypeUtility.isNumber(this.wlimitOrderCondPrice))
            {
                log.debug("���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p���������P�������l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02132,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p���������P�������l�ȊO�̒l�ł��B" 
                    + "this.W�w�l�p���������P�� = " 
                    + this.wlimitOrderCondPrice);
            }
            
            //this.W�w�l�p���������P�� < 0 or 
            if(Double.parseDouble(this.wlimitOrderCondPrice) <= 0)
            {
                log.debug("���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p���������P����0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02133,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���gW�w�l�h�ŁA" +
                    "W�w�l�p���������P����0�ȉ��̒l�ł��B" 
                    + "this.W�w�l�p���������P�� = " 
                    + this.wlimitOrderCondPrice);
            }

            //this.W�w�l�p���������P��-������ > 6�� or this.W�w�l�p���������P��-������ > 5�� 
            int l_wlimitOrderCondPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.wlimitOrderCondPrice);   
            int l_wlimitOrderCondPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.wlimitOrderCondPrice);
            if (l_wlimitOrderCondPriceIntegerLength > 6 || l_wlimitOrderCondPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02134.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02134,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "W�w�l�p���������P�� = "   
                        + this.wlimitOrderCondPrice);
            }
            
        }

        //�P�P�jW�w�l�p�����P���敪�EW�w�l�p�����P�� �̐������`�F�b�N 
        //�P�P�|�P�j 
        //this.W�w�l�p�����P���敪 == �h�w�l�h and 
        //( 
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            //this.W�w�l�p�����P�� == null or 
            if (this.wLimitPrice == null)
            {
                log.debug("W�w�l�p�����P���敪���h�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����P�������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02135,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W�w�l�p�����P���敪���h�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����P�������w��ł��B" 
                    + "this.W�w�l�p�����P��  = " 
                    + this.wLimitPrice);
            }
            
            //this.W�w�l�p�����P�� != ���� or 
            if (!WEB3StringTypeUtility.isNumber(this.wLimitPrice))
            {
                log.debug("W�w�l�p�����P���敪���h�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����P�������l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02136,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W�w�l�p�����P���敪���h�w�l�h�ŁA" +
                    "W�w�l�p�����P�������l�ȊO�̒l�ł��B" 
                    + "this.W�w�l�p�����P��  = " 
                    + this.wLimitPrice);
            }
            
            //this.W�w�l�p�����P�� < 0 or 
            if (Double.parseDouble(this.wLimitPrice) <= 0)
            {
                log.debug("W�w�l�p�����P���敪���h�w�l�h�Ȃ̂ɁA" +
                    "W�w�l�p�����P����0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02137,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W�w�l�p�����P���敪���h�w�l�h�ŁA" +
                    "W�w�l�p�����P����0�ȉ��̒l�ł��B" 
                    + "this.W�w�l�p�����P��  = " 
                    + this.wLimitPrice);
            }            
            //this.W�w�l�p�����P��-������ > 6�� or this.W�w�l�p�����P��-������ > 5�� 
            int l_wLimitPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.wLimitPrice);   
            int l_wLimitPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.wLimitPrice);
            if (l_wLimitPriceIntegerLength > 6 || l_wLimitPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02138.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02138,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "W�w�l�p�����P�� = "   
                        + this.wLimitPrice);
            }
           
        }

        //�P�P�|�Q�j 
        //this.W�w�l�p�����P���敪 == �h���s�h and 
        if ((WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)))
        {
            //this.W�w�l�p�����P�� != �inull or �h0�h�j 
            //�̏ꍇ�A��O���X���[����B
            if(this.wLimitPrice != null && !"0".equals(this.wLimitPrice))
            {
                log.debug("W�w�l�p�����P���敪���h���s�h �Ȃ̂ŁA" +
                    "W�w�l�p�����P�����w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02139,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W�w�l�p�����P���敪���h���s�h �Ȃ̂ŁA" +
                    "W�w�l�p�����P�����w��s�ł��B" 
                    + "this.W�w�l�p�����P��  = " 
                    + this.wLimitPrice);
            }
        }
        
        //�P�Q�j���������E���s�����̃`�F�b�N 
        //this.���������敪 == �h�o����܂Œ����h and 
        //this.���s���� != �h�����Ȃ��h 
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                log.debug("���������敪���g2�F�o����܂Œ����h�̏ꍇ�́A���s�����Ɂg" +
                    "1�F�������h��ݒ肵�ĉ�����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00125,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���g2�F�o����܂Œ����h�̏ꍇ�́A���s�����Ɂg" +
                    "1�F�������h��ݒ肵�ĉ������B" 
                    + "this.���s����  = " 
                    + this.execCondType);
            }
        }

        //�P�R�j�����P���E���s�����̃`�F�b�N 
        //this.�����P���敪 != �h�w�l�h and 
        //this.���s���� == �h�s�o���������s�h 
        //�̏ꍇ�A��O���X���[����B 
        if(!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType))
            {
                log.debug("���s�������g7�F�s�o���������s�h�̏ꍇ�́A�����P���敪���g" +
                    "1�F�w�l�h�ɂȂ�܂���i�s�o���������s�́u�w�l�v�̂ݎw��\�j");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00114,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���s�������g7�F�s�o���������s�h�̏ꍇ�́A�����P���敪���g" +
                    "1�F�w�l�h�ɂȂ�܂���i�s�o���������s�́u�w�l�v�̂ݎw��\�j" 
                    + "this.���s����  = " 
                    + this.execCondType);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }    

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
