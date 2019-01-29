head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���ꗗ���N�G�X�g�N���X(WEB3AdminAioCashoutInqListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2004/12/10 ���E (���u) �c�Ή�
                   2006/07/31 ����� (���u) ����̍X ���f��604
                   2006/09/04 �Ԑi (���u) ����̍X ���f��No.629                 
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioInputDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.aio.define.WEB3AioTransferDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o���\���⍇���ꗗ���N�G�X�g)<BR>
 * �o���\���⍇���ꗗ���N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqListRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121248L;      
    /**
     * (���X�R�[�h)<BR>
     * (��ʂɂē��͂��ꂽ���X�R�[�h)
     */
    public String[] branchCode;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (������t�敪)<BR>
     * ��ʂɂđI�����ꂽ������t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t��<BR>
     * 2�F ��t�G���[<BR>
     * 3�F �S��<BR>
     * <BR>
     */
    public String orderDiv;
    
    //===========remain zhou-yong NO.1 begin ========
    
    /**
     * (�U����敪)<BR>
     * ��ʂɂđI�����ꂽ�U����敪<BR>
     * <BR>
     * 0�F �h�S�āh<BR> 
     * 1�F �h�X���h<BR> 
     * 2�F �h���̑��h�i�X���ȊO�j 
     */
    public String transferDiv;
    
    //===========remain zhou-yong NO.1 end ========   
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s�� 
     */
    public String pageSize;   
    
    /**
     * (���͋敪)<BR>
     * <BR>
     * 0�F�S��<BR>
     * 1�F�ڋq<BR>
     * 2�FSONAR<BR>
     */
    public String inputDiv;   
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
	 * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
	 * <BR>
	 * �P�j���X�R�[�h�`�F�b�N <BR>
	 * <BR>
	 * �P�|�P�j���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 or <BR>
	 * ���N�G�X�g�f�[�^.���X�R�[�h = null�̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00779<BR>
	 * <BR>
	 * �P�|�Q�j�z��̊e�v�f�ɂ��� <BR>
	 * �P�|�Q�|�P�j�e�v�f.length() != 3 �̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00834 <BR>
	 * <BR>
	 * �P�|�Q�|�Q�j�e�v�f�ɐ����ȊO�̕���������̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00834 <BR>
	 * <BR>
	 * �Q�j������t�敪�`�F�b�N<BR>
	 * ���N�G�X�g�f�[�^.������t�敪 != 0�i��t���ρj and<BR>
	 * ���N�G�X�g�f�[�^.������t�敪 != 1�i��t�ρj and<BR>
	 * ���N�G�X�g�f�[�^.������t�敪 != 2�i��t�G���[�j and<BR>
	 * ���N�G�X�g�f�[�^.������t�敪 != 3�i�S�āj �̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00790<BR>
	 * <BR>
	 * <BR>
	 * �R�j�U����敪�`�F�b�N <BR>
	 * <BR>
	 * ���N�G�X�g�f�[�^.�U����敪 != (0, 1, 2) <BR>
	 * �̏ꍇ�A��O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01363<BR>
	 * <BR>
	 * <BR>
	 * �S�j�v���y�[�W�ԍ��`�F�b�N<BR>
	 * �S�|�P�j<BR>
	 * ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null or<BR>
	 * ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0 �̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00786<BR>
	 * <BR>
	 * <BR>
	 * �S�|�Q�j<BR>
	 * ���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕��������� �̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00090<BR>
	 * <BR>
	 * <BR>
	 * �T�j�y�[�W���\���s���`�F�b�N<BR>
	 * �T�|�P�j<BR>
	 * ���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00091<BR>
	 * ���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0 �̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00617<BR>
	 * <BR>
	 * <BR>
	 * �T�|�Q�j<BR>
	 * ���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���������̏ꍇ�A��O���X���[����B<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00092<BR>
	 * �U�j���͋敪�`�F�b�N<BR>
	 * <BR>
	 * ���N�G�X�g�f�[�^.���͋敪 != 0�i�S�āj and <BR>
	 * ���N�G�X�g�f�[�^.���͋敪 != 1�i�ڋq�j and <BR>
	 * ���N�G�X�g�f�[�^.���͋敪 != 2�iSONAR�j<BR>
	 * <BR>
	 * �̏ꍇ�A��O���X���[����B <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02522<BR>
	 * <BR>
     * @@throws WEB3BaseException 
	 * @@roseuid 40E53E8A0254
	 */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h�`�F�b�N 

        //�P�|�P�j���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 or 
        //       ���N�G�X�g�f�[�^.���X�R�[�h = null �̏ꍇ�A��O���X���[����B         
       
        if (this.branchCode.length == 0 ||
            this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 or " +
                "���N�G�X�g�f�[�^.���X�R�[�h = null");
        }      
        
        //�P�|�Q�j�z��̊e�v�f�ɂ��� 

        //�P�|�Q�|�P�j�e�v�f.length() != 3 �̏ꍇ�A��O���X���[����B 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (this.branchCode[i].length() != 3)
            {                
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834 ,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }

        //�P�|�Q�|�Q�j�e�v�f�ɐ����ȊO�̕���������̏ꍇ�A��O���X���[����B 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }
        
        //�Q�j������t�敪�`�F�b�N 

        //���N�G�X�g�f�[�^.������t�敪 != 0�i��t���ρj and 
        //���N�G�X�g�f�[�^.������t�敪 != 1�i��t�ρj and 
        //���N�G�X�g�f�[�^.������t�敪 != 2�i��t�G���[�j and 
        //���N�G�X�g�f�[�^.������t�敪 != 3�i�S�āj         
        
        if (!WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ALL.equals(this.orderDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00790,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.������t�敪 != ( 0�i��t���ρj, 1�i��t�ρj,2�i��t�G���[�j,3�i�S�āj)," +
                "���N�G�X�g�f�[�^.������t�敪 = " + this.orderDiv);
        }
        
        //=========remain zhou-yong NO.2 begin =============
        
        //�R�j�U����敪�`�F�b�N
        //���N�G�X�g�f�[�^.�U����敪 != (0, 1, 2)
        //�̏ꍇ�A��O���X���[����B 
        if(!(WEB3AioTransferDivDef.ALL.equals(this.transferDiv) || 
            WEB3AioTransferDivDef.POSTAL_SAVINGS.equals(this.transferDiv) || 
            WEB3AioTransferDivDef.OTHERS.equals(this.transferDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U����敪 != (0, 1, 2), " +
                "���N�G�X�g�f�[�^.�U����敪 = " + this.transferDiv);
            
        }
        
        //=========remain zhou-yong NO.2 end =============
        
        //�S�|�P�j 
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null");
        }

        //�S�|�Q�j
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕��������� 
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ��ɐ����ȊO�̕���������, " +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = " + this.pageIndex);
        }
        
        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0");
        }
        
        //�T�j�y�[�W���\���s���`�F�b�N 

        //�T�|�P�j
        //���N�G�X�g�f�[�^.�y�[�W���\���s�� = null
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = null");
        }
        
        //�T�|�Q�j 
        //���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕��������� 
        
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�y�[�W���\���s���ɐ����ȊO�̕���������," +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = " + this.pageSize);
        }
        
        //���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0 
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0," +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = " + this.pageSize);
        }
        
        //�U�j���͋敪�`�F�b�N 
        //���N�G�X�g�f�[�^.���͋敪 != 0�i�S�āj and 
        //���N�G�X�g�f�[�^.���͋敪 != 1�i�ڋq�j and 
        //���N�G�X�g�f�[�^.���͋敪 != 2�iSONAR�j 
        if (!WEB3AioInputDivDef.ALL.equals(this.inputDiv) &&
            !WEB3AioInputDivDef.CUSTOMER.equals(this.inputDiv) &&
            !WEB3AioInputDivDef.SONAR.equals(this.inputDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02522,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͋敪�����݂��Ȃ��R�[�h�l�ł��B " +
                "���N�G�X�g�f�[�^.���͋敪 = " + this.inputDiv);
        }
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqListResponse(this);
    }
}
@
