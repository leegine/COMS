head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F�ꗗ���N�G�X�g(WEB3AdminAioCashinConfirmListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3OutPutDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����A���m�F�ꗗ���N�G�X�g)<BR>
 * �����A���m�F�ꗗ���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinConfirmListRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_confirm_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101855L;      
    
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * ��ʂɂē��͂��ꂽ�����ԍ��i�ڋq�R�[�h�j�i���j
     */
    public String minAccountCode;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * ��ʂɂē��͂��ꂽ�����ԍ��i�ڋq�R�[�h�j�i���j
     */
    public String maxAccountCode;
    
    /**
     * (�A�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�A�������i���j
     */
    public Date minNoticeDate;
    
    /**
     * (�A�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�A�������i���j<BR>
     */
    public Date maxNoticeDate;
    
    /**
     * (�U�����i���j)<BR>
     * ��ʂɂē��͂��ꂽ�U�����i���j
     */
    public Date minTransferDate;
    
    /**
     * (�U�����i���j)<BR>
     * ��ʂɂē��͂��ꂽ�U�����i���j
     */
    public Date maxTransferDate;
    
    /**
     * (�U������Z�@@�փR�[�h)<BR>
     * ��ʂɂđI�����ꂽ���Z�@@�փR�[�h<BR>
     * <BR>
     * �u���ׂāv���I�����ꂽ�ꍇ�́Anull<BR>
     */
    public String finInstitutionCode;
    
    /**
     * (�o�͋敪)<BR>
     * ��ʂɂđI�����ꂽ�o�͋敪<BR>
     * <BR>
     * 0�F �ꗗ<BR>
     * 1�F CSV<BR>
     */
    public String outputDiv;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     */
    public String pageSize;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAioCashinConfirmListRequest.class);  
    
    /**
     * @@roseuid 4158EB630170
     */
    public WEB3AdminAioCashinConfirmListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.���X�R�[�h�ɐ����ȊO�̕��������� or<BR>
     *   ���N�G�X�g�f�[�^.���X�R�[�h = null or<BR>
     *   ���N�G�X�g�f�[�^.���X�R�[�h.length() != 3  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * <BR>
     * �Q�j�ڋq�R�[�h�`�F�b�N<BR>
     * �Q�|�P�j<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and<BR>
     *   (���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j.length() != 6 or<BR>
     *    ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�ɐ����ȊO�̕���������)  �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00789<BR>
     * <BR>
     * <BR>
     * �Q�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and<BR>
     *   (���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j.length() != 6 or<BR>
     *    ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�ɐ����ȊO�̕���������)  �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00794<BR>
     * <BR>
     * <BR>
     * �Q�|�R�j<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j > ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�i���j  <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00795<BR>
     * <BR>
     * <BR>
     * �Q�|�S�j 
     *    ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j == null and<BR> 
     *    ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and<BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   WEB3ErrorCatalog.BUSINESS_ERROR_01385<BR>
     * <BR>
     * <BR>
     * �R�j�A�������`�F�b�N<BR>
     * �R�|�P�j<BR>
     *  (���N�G�X�g�f�[�^.�A�������i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�A�������i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�A�������i���j > ���N�G�X�g�f�[�^.�A�������i���j)  <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00796<BR>
     * <BR>
     * <BR>
     * �R�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^.�A�������i���j = null and<BR>
     *   ���N�G�X�g�f�[�^.�A�������i���j != null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00797<BR>
     * <BR>
     *   <BR>
     * �S�j�U�����`�F�b�N<BR>
     * �S�|�P�j<BR>
     *   ���N�G�X�g�f�[�^.�U�����i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�U�����i���j != null and<BR>
     *   ���N�G�X�g�f�[�^.�U�����i���j > ���N�G�X�g�f�[�^.�U�����i���j  �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00799<BR>
     * <BR>
     * <BR>
     * �S�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^.�U�����i���j = null and<BR>
     *   ���N�G�X�g�f�[�^.�U�����i���j != null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00800<BR>
     * <BR>
     * <BR>
     * �T�j�U������Z�@@�փR�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null and<BR>
     *   ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() != 15  �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00776<BR>
     * <BR>
     * <BR>
     * �U�j�o�͋敪�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�o�͋敪 != 0�i�ꗗ�j and<BR>
     *   ���N�G�X�g�f�[�^.�o�͋敪 != 1�i�������j  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00801<BR>
     * <BR>
     * <BR>
     * �V�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �V�|�P�j<BR>
     *   ���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and<BR>
     *   �i���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null or<BR>
     *    ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0�j  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00802<BR>
     * <BR>
     * <BR>
     * �V�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and<BR>
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕���������  �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00803<BR>
     * <BR>
     * <BR>
     * �W�j�y�[�W���\���s���`�F�b�N<BR>
     * �W�|�P�j<BR>
     *   ���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and<BR>
     *   �i���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or<BR>
     *    ���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0�j   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00804<BR>
     * <BR>
     * <BR>
     * �W�|�Q�j<BR>
     *   ���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and<BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���������  �̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00805<BR>
     * <BR>
     * @@roseuid 40E281A80288
     */
    public void validate() throws WEB3BaseException
    {  
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        //�P�j���X�R�[�h�`�F�b�N
        //���N�G�X�g�f�[�^.���X�R�[�h�ɐ����ȊO�̕��������� or
        //���N�G�X�g�f�[�^.���X�R�[�h = null or
        //���N�G�X�g�f�[�^.���X�R�[�h.length() != 3  �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00779
        if ((WEB3StringTypeUtility.isNumber(this.branchCode) == false) ||
            WEB3StringTypeUtility.isEmpty(this.branchCode)||
             (this.branchCode.length() != 3))  
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���X�R�[�h�ɐ����ȊO�̕��������� or " +
                "���N�G�X�g�f�[�^.���X�R�[�h = null or " +
                "���N�G�X�g�f�[�^.���X�R�[�h.length() != 3 , " +
                "���N�G�X�g�f�[�^.���X�R�[�h = " + this.branchCode);  
        }
        
        //�Q�j�ڋq�R�[�h�`�F�b�N
        //�Q�|�P�j
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and
        //(���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j.length() != 6 or
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�ɐ����ȊO�̕���������)  �̏ꍇ�A
        //��O���X���[����B
         //class: WEB3BusinessLayerException
         //tag:   BUSINESS_ERROR_00789
        if (WEB3StringTypeUtility.isNotEmpty(this.minAccountCode) && 
            ((this.minAccountCode.length() != 6) ||
            (WEB3StringTypeUtility.isNumber(this.minAccountCode) == false)))       
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00789,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "(���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j.length() != 6 or" +
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�ɐ����ȊO�̕���������)" +
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j= " + this.minAccountCode);   
        }                 
              
        //�Q�|�Q�j
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and
        //(���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j.length() != 6 or
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�ɐ����ȊO�̕���������)  �̏ꍇ�A
        //��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00794
        if (WEB3StringTypeUtility.isNotEmpty(this.maxAccountCode) && 
            ((this.maxAccountCode.length() != 6) ||
            (WEB3StringTypeUtility.isNumber(this.maxAccountCode) == false)))       
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00794,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "(���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j.length() != 6 or" +
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�ɐ����ȊO�̕���������)," +
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j= " + this.maxAccountCode);   
        }              
              
        // �Q�|�R�j
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j > ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j
        //�̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00795
        if (WEB3StringTypeUtility.isNotEmpty(this.minAccountCode) && 
            WEB3StringTypeUtility.isNotEmpty(this.maxAccountCode) &&
            (Double.parseDouble(this.minAccountCode)) > 
            (Double.parseDouble(this.maxAccountCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00795,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j[" + this.minAccountCode + 
                "] > ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j[" + this.maxAccountCode +"]");    
        }      

        //=======remain zhou-yong NO.1 begin ========
        
        //�Q�|�S�j
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j == null and 
        //���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.minAccountCode) && 
            WEB3StringTypeUtility.isNotEmpty(this.maxAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01385,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j[" + this.minAccountCode + 
                "] > ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j[" + this.maxAccountCode +"]");    
            
        }

        //=======remain zhou-yong NO.1 end ========
        
        //�R�j�A�������`�F�b�N
       
        // �R�|�P�j
        //(���N�G�X�g�f�[�^.�A�������i���j != null and
        //���N�G�X�g�f�[�^.�A�������i���j != null and
        // ���N�G�X�g�f�[�^.�A�������i���j > ���N�G�X�g�f�[�^.�A�������i���j)  
        // �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          // tag:   BUSINESS_ERROR_00796
        if ((this.minNoticeDate != null) && 
            (this.maxNoticeDate != null) &&
            (WEB3DateUtility.compare((this.minNoticeDate), (this.maxNoticeDate)) > 0))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00796,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�A�������i���j[" + this.minNoticeDate + "]" +
                " > ���N�G�X�g�f�[�^.�A�������i���j[" + this.maxNoticeDate + "]");  
        }    
           
        //�R�|�Q�j
        //���N�G�X�g�f�[�^.�A�������i���j = null and
        //���N�G�X�g�f�[�^.�A�������i���j != null  �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00797
        if ((this.minNoticeDate == null) && (this.maxNoticeDate != null))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00797,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�A�������i���j = null and ���N�G�X�g�f�[�^.�A�������i���j != null "
                + ", ���N�G�X�g�f�[�^.�A�������i���j= " + this.maxNoticeDate);  
        }    
      
        // �S�j�U�����`�F�b�N
      
        // �S�|�P�j
        //���N�G�X�g�f�[�^.�U�����i���j != null and
        //���N�G�X�g�f�[�^.�U�����i���j != null and
        //���N�G�X�g�f�[�^.�U�����i���j > ���N�G�X�g�f�[�^.�U�����i���j  �̏ꍇ�A
        //��O���X���[����B
         //class: WEB3BusinessLayerException
         //tag:   BUSINESS_ERROR_00799
        if ((this.minTransferDate != null) && 
            (this.maxTransferDate != null) &&
            (WEB3DateUtility.compareToDay((this.minTransferDate), (this.maxTransferDate)) > 0))   
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00799,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�����i���j[" + this.minTransferDate + "] " +
                "> ���N�G�X�g�f�[�^.�U�����i���j[" + this.maxTransferDate + "]");  
        }   
         
        //�S�|�Q�j
        //���N�G�X�g�f�[�^.�U�����i���j = null and
        //���N�G�X�g�f�[�^.�U�����i���j != null  �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00800
        if ((this.minTransferDate) == null && 
            (this.maxTransferDate != null))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00800,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U�����i���j = null and ���N�G�X�g�f�[�^.�U�����i���j != null" +
                ", ���N�G�X�g�f�[�^.�U�����i���j = " + this.maxTransferDate);  
        }    
          
        //�T�j�U������Z�@@�փR�[�h�`�F�b�N
        //���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null and
        //���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() != 15  �̏ꍇ�A
        //��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00776
        if (WEB3StringTypeUtility.isNotEmpty(this.finInstitutionCode) && 
            (this.finInstitutionCode.length() != 15))
        {
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00776,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              "���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null " +
              "and ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() != 15" + 
              ", ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() = " + 
              this.finInstitutionCode.length());
        } 
         
        //�U�j�o�͋敪�`�F�b�N
        //���N�G�X�g�f�[�^.�o�͋敪 != 0�i�ꗗ�j and
        //���N�G�X�g�f�[�^.�o�͋敪 != 1�i�������j  �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00801
        if (!WEB3OutPutDivDef.LIST_VIEW.equals(this.outputDiv) && 
            !WEB3OutPutDivDef.CSV_VIEW.equals(this.outputDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00801,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 != 0�i�ꗗ�j and" +
                "���N�G�X�g�f�[�^.�o�͋敪 != 1�i�������j, " +
                "���N�G�X�g�f�[�^.�o�͋敪 = " + this.outputDiv);
        }    
 
        //�V�j�v���y�[�W�ԍ��`�F�b�N
        
        //�V�|�P   
        //���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null�̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00802 
        if ((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv) &&
            WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00802,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�jand ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null");
        } 
        
        //�V�|�Q�j
        //���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕���������  �̏ꍇ�A
        //��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00803
        if (((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv)) && 
            (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00803,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕���, " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = " + this.pageIndex);
        }   
        
        //�V�|�R�j
        //���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0�j  �̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00802 
        if (((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv)) &&
            (Double.parseDouble(this.pageIndex) <= 0))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00802,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0, " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = " + this.pageIndex);
            
        }
        //�W�j�y�[�W���\���s���`�F�b�N
        
        //�W�|�P�j���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and ���N�G�X�g�f�[�^.�y�[�W���\���s�� = null
          //class: WEB3BusinessLayerException
         //tag:   BUSINESS_ERROR_00804
        if ((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv) && 
            WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00804,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and ���N�G�X�g�f�[�^.�y�[�W���\���s�� = null");
        }  
        
        //�W�|�Q�j
        //���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and
        //���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���������  �̏ꍇ�A
        //��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00805
        if (((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv)) && 
            (WEB3StringTypeUtility.isNumber(this.pageSize) == false))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00805,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and " +
                "���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���," +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = this.pageSize");
        } 
        
        // �W�|�R�j
        //���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and
        //���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0�j   �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00804
        if ((WEB3OutPutDivDef.LIST_VIEW).equals(this.outputDiv) && 
             Double.parseDouble(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00804,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�o�͋敪 = 0�i�ꗗ�j and" +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0, " +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = " + this.pageSize);
        }    
    }
   
   /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����A���m�F�ꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6301A2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinConfirmListResponse(this);
    }
}
@
