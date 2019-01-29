head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g(WEB3AdminAccOpenInspectListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ���H�n�@@(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.accountopen.define.WEB3AccOpenFromWebSortKeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐR���ꗗ���N�G�X�g
 * 
 * @@author ���H�n
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectList";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenInspectListRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * <BR>
     * �����͂̏ꍇ�A�S���X<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * �O����v�Ō����\<BR>
     */
    public String accountCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * <BR>
     * �O����v�Ō����\
     */
    public String requestNumber;
    
    /**
     * (�������i���j)<BR>
     * �������i���j<BR>
     * <BR>
     * YYYYMMDD�`��
     */
    public Date occurredDateFrom;
    
    /**
     * (�������i���j)<BR>
     * �������i���j<BR>
     * <BR>
     * YYYYMMDD�`��
     */
    public Date occurredDateTo;
    
    /**
     * (�R�����)<BR>
     * �R�����<BR>
     * <BR>
     * 0�F�S�ā@@1�F����q�@@2�F����q�i�����j�@@3�FY�q
     */
    public String reviewCode;
    
    /**
     * (�R���S���҃R�[�h)<BR>
     * �R���S���҃R�[�h<BR>
     * <BR>
     * �O����v�ł̌����\
     */
    public String checkerCode;
    
    /**
     * (�R����)<BR>
     * �R����<BR>
     * <BR>
     * 0�F�S�ā@@1�F�R���҂��@@2�F�R���ς݁@@3�F���F�ρ@@4�F�۔F��
     */
    public String checkStatus;
    
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
     * (�����J�݃\�[�g�L�[)<BR>
     */  
    public WEB3AccOpenSortKey[] sortKeys;
    
    /**
     * @@roseuid 44912C11008C
     */
    public WEB3AdminAccOpenInspectListRequest() 
    {
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�������i���j�C�������i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�P�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02464<BR>
     * <BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�̂�<BR>
     * �@@�Q�|�P�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * <BR>
     * �R�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�̂�<BR>
     * �@@�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01331 <BR>
     * <BR>
     * <BR>
     * �S�j�@@���ʃR�[�h�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�̂�<BR>
     * �@@�S�|�P�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01820 <BR>
     * <BR>
     * <BR>
     * �T�j�@@�R���S���҃R�[�h�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�̂�<BR>
     * �@@�T�|�P�j�@@���p�p�����ȊO���܂܂��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02463 <BR>
     * <BR>
     * <BR>
     * �U�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�U�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B<BR> 
     * �@@�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00090 <BR>
     * <BR>
     * �@@�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00616 <BR>
     * <BR>
     * <BR>
     * �V�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02224 <BR>
     * <BR>
     * �@@�V�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00092 <BR>
     * <BR>
     * �@@�V�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00617 <BR>
     * <BR>
     * �W�j�@@�\�[�g�L�[�̃`�F�b�N <BR> 
     * �@@�W�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00231 <BR> 
     * <BR>
     * �@@�W�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00232 <BR> 
     * <BR> 
     * �@@�W�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  <BR>
     * �@@�@@�@@�W�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B  <BR>
     * �@@�@@�@@�W�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>  
     *�@@�@@�@@�@@�@@���ʃR�[�h�irequestNumber�j <BR>
     *�@@�@@�@@�@@�@@���������ioccurredDate�j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00086 <BR> 
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 4472BD1B0158
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�������i���j�C�������i���j�̃`�F�b�N
        //�@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�
        //�@@�P�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
        if (this.occurredDateFrom != null && this.occurredDateTo != null)
        {
        	if(WEB3DateUtility.compare(this.occurredDateFrom, this.occurredDateTo) > 0)
        	{
        		log.debug("�������i���j�͔������i���j���傫���ł��B");
        		log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02464,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�������i���j�͔������i���j���傫���ł��B");
        	}
        }
        
        //�Q�j�@@���X�R�[�h�̃`�F�b�N
        //�@@�����͂�����ꍇ�̂�
        //�@@�Q�|�P�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B
        if (this.branchCode != null)
        {
        	if (WEB3StringTypeUtility.isWbyteString(this.branchCode)
                || !WEB3StringTypeUtility.isDigit(this.branchCode))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }
        
        //�R�j�@@�ڋq�R�[�h�̃`�F�b�N
        //�@@�����͂�����ꍇ�̂�
        //�@@�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B
        if (this.accountCode != null)
        {
        	if (WEB3StringTypeUtility.isWbyteString(this.accountCode)
                || !WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("�ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01331,
                    getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B");
            }
        }
        
        //�S�j�@@���ʃR�[�h�̃`�F�b�N
        //�@@�����͂�����ꍇ�̂�
        //�@@�S�|�P�j�@@���p�����ȊO���܂܂��ꍇ�A��O���X���[����B
        if (this.requestNumber != null)
        {
        	if (WEB3StringTypeUtility.isWbyteString(this.requestNumber)
                || !WEB3StringTypeUtility.isDigit(this.requestNumber))
        	{
        		log.debug("���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                	WEB3ErrorCatalog.BUSINESS_ERROR_01820,
                    getClass().getName() + STR_METHOD_NAME,
                    "���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
            }
        }
        
        //�T�j�@@�R���S���҃R�[�h�̃`�F�b�N
        //�@@�����͂�����ꍇ�̂�
        //�@@�T�|�P�j�@@���p�p�����ȊO���܂܂��ꍇ�A��O���X���[����B
        if (this.checkerCode != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.checkerCode)
        		|| !WEB3StringTypeUtility.isLetterOrDigit(this.checkerCode))
            {
            	log.debug("�R���S���҃R�[�h�̒l�����p�p�����ȊO�̒l�ł��B");
            	log.exiting(STR_METHOD_NAME);
            	throw new WEB3BusinessLayerException(
            	    WEB3ErrorCatalog.BUSINESS_ERROR_02463,
                    getClass().getName() + STR_METHOD_NAME,
                    "�R���S���҃R�[�h�̒l�����p�p�����ȊO�̒l�ł��B");
            }
        }
        
        //�U�j�@@�v���y�[�W�ԍ��`�F�b�N 
        //�@@�U�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B 
        if (this.pageIndex == null || this.pageIndex.length() == 0)
        {
        	this.pageIndex = "1";
        }
        
        //�@@�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
        	log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�@@�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isMinus(this.pageIndex))
        {
        	log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�V�j�@@�y�[�W���\���s���`�F�b�N 
        //�@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
        	log.debug("�y�[�W���\���s���������͂ł��B");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        //�@@�V�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
        	log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //�@@�V�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isMinus(this.pageSize))
        {
        	log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        
        //�W�j�@@�\�[�g�L�[�̃`�F�b�N  
        //�W�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�W�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�W�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenSortKey l_accOpenSortKey = this.sortKeys[i];
            
            //�W�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            l_accOpenSortKey.validate();
            
            //�W�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
            //���ʃR�[�h�irequestNumber�j 
            //���������ioccurredDate�j
            if (!(WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_accOpenSortKey.keyItem)))  
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    "�L�[���ڂɎ��ʃR�[�h�A���������̍��ږ��ȊO�̒l�����݂��Ă��܂��B");         
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
        return new WEB3AdminAccOpenInspectListResponse(this);
    }

}
@
