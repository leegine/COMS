head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��ꗗ���ʃ��N�G�X�g(WEB3AdminFEqConAccountOpenMngListCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������J�݊Ǘ��ꗗ���ʃ��N�G�X�g)<BR>
 * �O�������J�݊Ǘ��ꗗ���ʃ��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AdminFEqConAccountOpenMngListCommonRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list_common";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * (�X�e�[�^�X�敪)<BR>
     * �X�e�[�^�X<BR>
     * <BR>
     * 0�F �����J�ݒ�<BR>
     * 1�F �����J�݊���<BR>
     * 2�F �����J�݃G���[<BR>
     * 9�F �폜<BR>
     * <BR>
     * �� �S�X�e�[�^�X�I���̏ꍇ�́Anull
     */
    public String statusDiv;
    
    /**
     * (�\�����i���j)<BR>
     * ��ʂɂē��͂��ꂽ�\�����i���j<BR>
     * (YYYYMMDDhh)<BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String applyDateFrom;
    
    /**
     * (�\�����i���j)<BR>
     * ��ʂɂē��͂��ꂽ�\�����i���j<BR>
     * (YYYYMMDDhh)<BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String applyDateTo;
    
    /**
     * @@roseuid 423552EB0271
     */
    public WEB3AdminFEqConAccountOpenMngListCommonRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngListCommonRequest.class);
    
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
     * �Q�j�@@�X�e�[�^�X�敪�̃`�F�b�N<BR>
     * �@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E"�����J�ݒ�"<BR>
     * �@@�@@�E"�����J�݊���"<BR>
     * �@@�@@�E"�����J�݃G���["<BR>
     * �@@�@@�E"�폜"<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01758<BR>
     * <BR>
     * �R�j�@@�\����(��)�̃`�F�b�N<BR>
     * �@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�j�@@�\�����i���j�̓��t����(YYYYMMDDhh)��<BR>
     * �@@�@@Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01760<BR>
     * <BR>
     * �S�j�@@�\����(��)�̃`�F�b�N<BR>
     * �@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�j�@@�\�����i���j�̓��t����(YYYYMMDDhh)��<BR>
     * �@@Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01761<BR>
     * <BR>
     * �T�j�@@�\����(���`��)�������̃`�F�b�N<BR>
     * �@@�\����(��)�A�\����(��)�����ɖ����͂łȂ��ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�j�@@�\����(��) > �\����(��)�̏ꍇ�A��O���X���[����B<BR>]
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01762<BR>
     * <BR>
     * �@@�@@����L��r�́A�������r�ł悢�B
     * @@throws WEB3BaseException
     * @@roseuid 41F8D48302E6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h
        //this.���X�R�[�h == null or 
        //this.���X�R�[�h.length == 0
        //�̏ꍇ�A��O���X���[����B 
        if(this.branchCode == null || this.branchCode.length ==0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h == null or ���X�R�[�h.length == 0�B");
        }

        //�Q�j�@@�X�e�[�^�X�敪�̃`�F�b�N 
        //�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        //�@@�Q�|�P�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //�@@�@@�E"�����J�ݒ�" 
        //�@@�@@�E"�����J�݊���" 
        //�@@�@@�E"�����J�݃G���[" 
        //�@@�@@�E"�폜" 
        if(!WEB3StringTypeUtility.isEmpty(this.statusDiv))
        {
            if(!(WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.DELETE.equals(this.statusDiv))
                )
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�e�[�^�X�敪[" + this.statusDiv + "]�B");
            }
        }

        //�R�j�@@�\����(��)�̃`�F�b�N 
        //�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        //�@@�R�|�P�j�@@�\�����i���j�̓��t����(YYYYMMDDhh)�� 
        //�@@�@@Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B 
        if(!WEB3StringTypeUtility.isEmpty(this.applyDateFrom))
        {
			if(!WEB3StringTypeUtility.isDateStr(this.applyDateFrom.substring(0,8), "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01760,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Date�^�ɕϊ��ł��Ȃ������B");
            }
            
        }
        
        //�S�j�@@�\����(��)�̃`�F�b�N 
        //�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        //�@@�S�|�P�j�@@�\�����i���j�̓��t����(YYYYMMDDhh)�� 
        //�@@Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B 
        if(!WEB3StringTypeUtility.isEmpty(this.applyDateTo))
        {
			if(!WEB3StringTypeUtility.isDateStr(this.applyDateTo.substring(0,8),"yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01761,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "Date�^�ɕϊ��ł��Ȃ������B");
            }
        }
        
        //�T�j�@@�\����(���`��)�������̃`�F�b�N 
        //�@@�\����(��)�A�\����(��)�����ɖ����͂łȂ��ꍇ�A 
        //�@@�ȉ��̃`�F�b�N���s���B 
        //�@@�T�|�P�j�@@�\����(��) > �\����(��)�̏ꍇ�A��O���X���[����B 
        //�@@�@@����L��r�́A�������r�ł悢�B 
        if(!WEB3StringTypeUtility.isEmpty(this.applyDateFrom) 
            && !WEB3StringTypeUtility.isEmpty(this.applyDateTo))
        {
            if(this.applyDateFrom.compareTo(this.applyDateTo) > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01762,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\����(��)[" + this.applyDateFrom + "] > " + 
                    "�\����(��)[" + this.applyDateTo + "]�B");
            }
        }
          
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 423552EB02BF
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
