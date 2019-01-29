head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�U�ֈꗗ���N�G�X�g(WEB3AdminFXTransferListRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                  : 2006/08/04 ���(SCS)�@@���f�� No595,610�Ή�
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁEFX�U�ֈꗗ���N�G�X�g) <BR>
 * �Ǘ��ҁEFX�U�ֈꗗ���N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXTransferListRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (���X�R�[�h) <BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h <BR>
     * �������͂̏ꍇ�́APR�w�ŕێ����Ă��� <BR>
     * �Ǘ��Ҏ戵�\���X�̈ꗗ���Z�b�g�B
     */
    public String[] branchCodeList;

    /**
     * (�ڋq�R�[�h) <BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�h <BR>
     * ��null�F�w��Ȃ�
     */
    public String accountCode;

    /**
     * (�U�֋敪) <BR>
     * ��ʂɂđI�����ꂽ�U�֋敪 <BR>
     * <BR>
     * 1:����(FX)<BR> 
     * 2:�o��(FX)<BR> 
     * 3:�o��(��OP)<BR> 
     * 4:����(��OP)<BR> 
     * <BR>
     * ���S�Ă̏ꍇ�́Anull
     */
    public String fxTransferDiv;

    /**
     * (��t���i���j) <BR>
     * ��ʂɂē��͂��ꂽ��t���i���j <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date receiptDateFrom;

    /**
     * (��t���i���j) <BR>
     * ��ʂɂē��͂��ꂽ��t���i���j <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date receiptDateTo;

    /**
     * (�U�֓�) <BR>
     * ��ʂɂē��͂��ꂽ�U�֓� <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date transferDate;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * ��ʂɂđI�����ꂽ�X�e�[�^�X <BR>
     * <BR>
     * 1�F���ϊ��� <BR>
     * 5�F���̑� <BR>
     * <BR>
     * ���S�X�e�[�^�X�̏ꍇ�́Anull
     */
    public String statusDiv;

    /**
     * (�v���y�[�W�ԍ�) <BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��) <BR>
     * �y�[�W���\���s��
     */
    public String pageSize;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E7904C005D
     */
    public WEB3AdminFXTransferListRequest()
    {
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferListRequest.class);
        
    /**
     * (validate) <BR>
     * ���N�G�X�g�f�[�^�̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j���X�R�[�h <BR>
     * <BR>
     * this.���X�R�[�h == null or <BR>
     * this.���X�R�[�h.length() == 0 <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01757 <BR>
     * <BR>
     * �Q�j�U�֋敪 <BR>
     * <BR>
     * this.�U�֋敪 != null and <BR>
     * this.�U�֋敪 != ('1' or '2'or '3'or '4') <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01772 <BR>
     * <BR>
     * �R�j��t�� <BR>
     * <BR>
     * this.��t���i���j != null and <BR>
     * this.��t���i���j != null and <BR>
     * this.��t���i���j >= this.��t���i���j <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01773 <BR>
     * <BR>
     * �S�j�X�e�[�^�X�敪 <BR>
     * <BR>
     * this.�X�e�[�^�X�敪 != null and <BR>
     * this.�X�e�[�^�X�敪 != ('1' or '5') <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01758 <BR>
     * <BR>
     * �T�j�v���y�[�W�ԍ� <BR>
     * <BR>
     * this.�v���y�[�W�ԍ� == null <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00089 <BR>
     * <BR>
     * �U�j�y�[�W���\���s�� <BR>
     * <BR>
     * this.�y�[�W���\���s�� == null <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00091 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h 
        //  this.���X�R�[�h == null or 
        //  this.���X�R�[�h.length() == 0 
        //  �̏ꍇ�A��O���X���[����B 
        if (this.branchCodeList == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        if (this.branchCodeList.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f�����O�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f�����O�ł��B");
        }
 
        //�Q�j�U�֋敪 
        //  this.�U�֋敪 != null and 
        //  this.�U�֋敪 != ('1' or '2' or '3' or '4') 
        //  �̏ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isEmpty(this.fxTransferDiv)
            && !(WEB3AioTransferOperationDivDef.CASH_IN.equals(this.fxTransferDiv)
                || WEB3AioTransferOperationDivDef.CASH_OUT.equals(this.fxTransferDiv)
                || WEB3AioTransferOperationDivDef.FUOP_OUT.equals(this.fxTransferDiv)
                || WEB3AioTransferOperationDivDef.FUOP_IN.equals(this.fxTransferDiv)))
        {
            log.debug("�U�֋敪�����݂��Ȃ��R�[�h�l�ł�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01772,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�u1�F����(FX)�v�u2�F�o��(FX)�v�u3�F�o��(��OP)�v�u4�F����(��OP)�v");
        }
        
        //�R�j��t�� 
        //  this.��t���i���j != null and 
        //  this.��t���i���j != null and 
        //  this.��t���i���j >= this.��t���i���j 
        //  �̏ꍇ�A��O���X���[����B 
        if (this.receiptDateFrom != null && (this.receiptDateTo != null))
        {
            if (WEB3DateUtility.compareToDay(this.receiptDateFrom, this.receiptDateTo) >= 0)
            {
                log.debug("��t���i���j�͎�t���i���j�ȏ�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01773,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��t���i���j�͎�t���i���j�ȏ�ł��B");
            }
        }
        
        //�S�j�X�e�[�^�X�敪 
        //  this.�X�e�[�^�X�敪 != null and 
        //  this.�X�e�[�^�X�敪 != ('1' or '5') 
        //  �̏ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isEmpty(this.statusDiv)
            && !(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(this.statusDiv)
                || WEB3TransferStatusDivDef.OTHER.equals(this.statusDiv)))
        {
            log.debug("�X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�u1�F���ϊ��� �v�u 5�F���̑� �v");
        }

        //�T�j�v���y�[�W�ԍ� 
        //  this.�v���y�[�W�ԍ� == null 
        //  �̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�U�j�y�[�W���\���s�� 
        //  this.�y�[�W���\���s�� == null 
        //  �̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�U�ֈꗗ���X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferListResponse(this);
    }
}@
