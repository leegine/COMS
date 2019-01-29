head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�ֈꗗ���N�G�X�g(WEB3AdminFEqConTransferListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.aio.define.WEB3AioTransferOperationDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O���U�ֈꗗ���N�G�X�g)<BR>
 * �O���U�ֈꗗ���N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_transfer_list";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h<BR>
     * �������͂̏ꍇ�́APR�w�ŕێ����Ă���<BR>
     * �@@�Ǘ��Ҏ戵�\���X�̈ꗗ���Z�b�g�B
     */
    public String[] branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�h<BR>
     * ��null�F�w��Ȃ�
     */
    public String accountCode;
    
    /**
     * (�U�֋敪)<BR>
     * ��ʂɂđI�����ꂽ�U�֋敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F�o��<BR>
     * <BR>
     * ���S�Ă̏ꍇ�́Anull
     */
    public String transferDiv;
    
    /**
     * (��t���i���j)<BR>
     * ��ʂɂē��͂��ꂽ��t���i���j<BR>
     * YYYYMMDD<BR>
     * <BR>
     * ��null�F�w��Ȃ�<BR>
     * �������b�̕����́A�����l�i00:00:00�j
     */
    public Date receiptDateFrom;
    
    /**
     * (��t���i���j)<BR>
     * ��ʂɂē��͂��ꂽ��t���i���j<BR>
     * YYYYMMDD<BR>
     * <BR>
     * ��null�F�w��Ȃ�<BR>
     * �������b�̕����́A23:59:59
     */
    public Date receiptDateTo;
    
    /**
     * (�U�֓�)<BR>
     * ��ʂɂē��͂��ꂽ�U�֓�<BR>
     * YYYYMMDD<BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date transferDate;
    
    /**
     * (�X�e�[�^�X�敪)<BR>
     * ��ʂɂđI�����ꂽ�X�e�[�^�X<BR>
     * <BR>
     * 1�F���ϊ���<BR>
     * 5�F���̑�<BR>
     * <BR>
     * ���S�X�e�[�^�X�̏ꍇ�́Anull
     */
    public String statusDiv;
    
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
     * @@roseuid 4235559F01B5
     */
    public WEB3AdminFEqConTransferListRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferListRequest.class);
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     *   this.���X�R�[�h == null or<BR>
     *   this.���X�R�[�h.length == 0<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�U�֋敪<BR>
     * <BR>
     *   this.�U�֋敪 != (null,1,2)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01772<BR>
     * <BR>
     * �R�j�X�e�[�^�X�敪<BR>
     * <BR>
     *   this.�X�e�[�^�X�敪 != (null, 1, 5)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01758<BR>
     * <BR>
     * �S�j�v���y�[�W�ԍ�<BR>
     * <BR>
     *   this.�v���y�[�W�ԍ� == null or<BR>
     *   this.�v���y�[�W�ԍ� <= 0<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�y�[�W���\���s��<BR>
     * <BR>
     *   this.�y�[�W���\���s�� == null or<BR>
     *   this.�y�[�W���\���s�� <= 0<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@roseuid 41D0BA380264
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h
        //this.���X�R�[�h == null or 
        //this.���X�R�[�h.length == 0
        //�̏ꍇ�A��O���X���[����B 
        if(this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h == null or ���X�R�[�h.length == 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h == null or ���X�R�[�h.length == 0");
        }
        
        //�Q�j�U�֋敪 
        //this.�U�֋敪 != (null,1,2)
        //�̏ꍇ�A��O���X���[����B 
        if(!(WEB3StringTypeUtility.isEmpty(this.transferDiv)
            ||WEB3AioTransferOperationDivDef.CASH_IN.equals(this.transferDiv) 
            || WEB3AioTransferOperationDivDef.CASH_OUT.equals(this.transferDiv)))
        {
            log.debug("�U�֋敪 != (1,2)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01772,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U�֋敪 != (1,2)");
        }
        
        //�R�j�X�e�[�^�X�敪 
        //this.�X�e�[�^�X�敪 != (null, 1, 5)
        //�̏ꍇ�A��O���X���[����B 
        if(!(WEB3StringTypeUtility.isEmpty(this.statusDiv) 
            || WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(this.statusDiv)
            || WEB3TransferStatusDivDef.OTHER.equals(this.statusDiv)))
        {
            log.debug("�X�e�[�^�X�敪 != (null, 1, 5)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�e�[�^�X�敪 [ = " + this.statusDiv + "]");
        }
        
        //�S�j�v���y�[�W�ԍ� 
        //this.�v���y�[�W�ԍ� == null or 
        //this.�v���y�[�W�ԍ� <= 0
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ� == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ� == null");
        }

        if(Long.parseLong(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ� <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ� <= 0");
        }
        
        //�T�j�y�[�W���\���s�� 
        //this.�y�[�W���\���s�� == null or 
        //this.�y�[�W���\���s�� <= 0
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s�� == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s�� == null");            
        }
        
        if(Long.parseLong(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s�� <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s�� <= 0");            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O���U�ֈꗗ���X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConTransferListResponse(this);
    }
}
@
