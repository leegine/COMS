head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeStateReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�����ꗗ���N�G�X�g(WEB3AdminPointExchangeStateReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.point.define.WEB3AcceptDivDef;
import webbroker3.point.define.WEB3PointKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�|�C���g�����ꗗ���N�G�X�g)<BR>
 * �|�C���g�����ꗗ���N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointExchangeStateReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPointExchangeStateReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_exchangeStateReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290091L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     */
    public String[] branchCode;
    
    /**
     * (��t�敪)<BR>
     * ��t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t�ς�<BR>
     * 2�F�S�ĕ\��<BR>
     */
    public String acceptDiv;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �|�C���g�����ꗗ�\�[�g�L�[�̔z��<BR>
     */
    public WEB3PointSortKey[] sortKeys;
    
    /**
     * @@roseuid 41D1254A030D
     */
    public WEB3AdminPointExchangeStateReferenceRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     *    this.���X�R�[�h = null or<BR>
     *    this.���X�R�[�h.length() != 3<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * �Q�j��t�敪<BR>
     * <BR>
     *    this.��t�敪 != �i0, 1, 2�j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01960<BR>
     * �R�j�v���y�[�W�ԍ�<BR>
     * <BR>
     *    this.�v���y�[�W�ԍ� = null or <BR>
     *    this.�v���y�[�W�ԍ� <= 0 or<BR>
     *    this.�v���y�[�W�ԍ� != ���p����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �S�j�y�[�W���\���s��<BR>
     * <BR>
     *    this.�y�[�W���\���s�� = null or <BR>
     *    this.�y�[�W���\���s�� <= 0 or<BR>
     *    this.�y�[�W���\���s�� != ���p����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �T�j�\�[�g�L�[ <BR>
     * <BR>
     * �T�|�P�j <BR>
     * <BR>
     *    this.�\�[�g�L�[ = null or<BR>
     *    this.�\�[�g�L�[.length() = 0 <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �T�|�Q�j�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B <BR>
     * <BR>
     * �T�|�Q�|�P�j <BR>
     * <BR>
     *    �\�[�g�L�[.�L�[���� = null <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �T�|�Q�|�Q�j <BR>
     * <BR>
     *    �\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l���������ꍇ�A��O���X���[����B <BR>
     * <BR>
     *    �E���X�R�[�h<BR>
     *    �E�ڋq�R�[�h<BR>
     *    �E�i�i�ԍ�<BR>
     *    �E�i�i��<BR>
     *    �E�\������<BR>
     *    �E��t�敪<BR>
     *    �E�X�V����<BR>
     *    �E��t���[�U<BR>
     *    �E����敪<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �T�|�Q�|�R�j <BR>
     * <BR>
     *    �\�[�g�L�[.�����^�~�� = null <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * <BR>
     * �T�|�Q�|�S�j <BR>
     * <BR>
     *    �\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *    �E�hA�F�����h <BR>
     *    �E�hD�F�~���h <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * @@roseuid 418F49A702DC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j���X�R�[�h
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            String l_strMessage = "���X�R�[�h = null !";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (WEB3StringTypeUtility.getByteLength(this.branchCode[i]) != 3)
            {
                String l_strMessage = "���X�R�[�herror! " + this.branchCode;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
        }

        // �Q�j��t�敪 
        if (!WEB3AcceptDivDef.NO_FINISHED.equals(this.acceptDiv) 
            && !WEB3AcceptDivDef.FINISHED.equals(this.acceptDiv)
            && !WEB3AcceptDivDef.ALL.equals(this.acceptDiv))
        {
            String l_strMessage = "��t�敪[" + this.acceptDiv + "]�G���[! ���͂��ꂽ��t�敪���h0�F ��t���ρh�A�h1�F ��t�ς݁h�A�h2�F�S�ĕ\���h�ȊO�ł��B";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01960,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        // �R�j�v���y�[�W�ԍ� 
        if (this.pageIndex == null || "".equals(this.pageIndex.trim()))
        {
            String l_strMessage = "�v���y�[�W�ԍ�error! " + this.pageIndex;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            String l_strMessage = "�v���y�[�W�ԍ�error! " + this.pageIndex;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            String l_strMessage = "�v���y�[�W�ԍ�error! " + this.pageIndex;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        // �S�j�y�[�W���\���s��
        if (this.pageSize == null || "".equals(this.pageSize.trim()))
        {
            String l_strMessage = "�y�[�W���\���s��error! " + this.pageSize;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            String l_strMessage = "�y�[�W���\���s��error! " + this.pageSize;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        if (Integer.parseInt(this.pageSize) < 0)
        {
            String l_strMessage = "�y�[�W���\���s��error! " + this.pageSize;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        // �T�j�\�[�g�L�[ 
        // �T�|�P�j 
        if (this.sortKeys == null)
        {
            String l_strMessage = "�\�[�g�L�[error! " + this.sortKeys;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        if (this.sortKeys.length == 0)
        {
            String l_strMessage = "�\�[�g�L�[error! " + this.sortKeys;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        // �T�|�Q�j�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        int l_intSortKeyCount = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeyCount; i++)
        { 
            // �T�|�Q�|�P�j 
            if (this.sortKeys[i].keyItem == null || "".equals(this.sortKeys[i].keyItem.trim()))
            {
                String l_strMessage = "�\�[�g�L�[.�L�[����error! " + this.sortKeys[i].keyItem;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }

            // �T�|�Q�|�Q�j 
            if (!WEB3PointKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_ACCEPT_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_ACCEPT_USER.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_CANCEL_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_TIMESTAMP.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.PREMINUM_NO.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.PREMIUM_NAME.equals(this.sortKeys[i].keyItem))
            {
                String l_strMessage = "�\�[�g�L�[.�L�[����error! " + this.sortKeys[i].keyItem;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
            
            // �T�|�Q�|�R�j 
            if (this.sortKeys[i].ascDesc == null || "".equals(this.sortKeys[i].ascDesc.trim()))
            {
                String l_strMessage = "�\�[�g�L�[.�����^�~��error! " + this.sortKeys[i].ascDesc;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }

            // �T�|�Q�|�S�j 
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) 
                && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                String l_strMessage = "�\�[�g�L�[.�����^�~��error! " + this.sortKeys[i].ascDesc;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254A033C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointExchangeStateReferenceResponse(this);
    }
}
@
