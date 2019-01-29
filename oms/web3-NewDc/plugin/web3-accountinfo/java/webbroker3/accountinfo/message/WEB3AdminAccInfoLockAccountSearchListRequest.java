head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���N�G�X�g(WEB3AdminAccInfoLockAccountSearchListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoSearchCondTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���N�G�X�g)<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���N�G�X�g<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchListRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLockAccountSearchListRequest.class); 
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;
    
    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j<BR>
     */
    public Date registDateFrom;
    
    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j<BR>
     */
    public Date registDateTo;
    
    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     */
    public String searchCondType;
    
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
     * �\�[�g�L�[<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;
    
    /**
     * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���N�G�X�g)<BR>
     */
    public WEB3AdminAccInfoLockAccountSearchListRequest()
    {

    }
     
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoLockAccountSearchListResponse(this);
    }
     
    /**
     * validate<BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N <BR>
     *      �P�|�P�j�����͂̏ꍇ�A�G���[���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>    
     * <BR>
     * �Q�j�@@�o�^���i���j�A�o�^���i���j�̃`�F�b�N <BR>
     * �@@�@@�@@�Q�|�P�j�@@�ǂ��炩����݂̂̓��͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02221<BR>   
     * �@@�@@�@@�@@�@@�@@�����������͂��A�������͂���Ă��Ȃ���΂Ȃ�Ȃ��B <BR>
     * �@@�@@�@@�Q�|�Q�j�o�^���i���j���o�^���i���j�̏ꍇ�A�G���[���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02222<BR>   
     * �@@�@@�@@�@@�@@�@@���o�^���i���j�A�o�^���i���j�̗����ɓ��͂�����ꍇ�̂݃`�F�b�N����B<BR> 
     * <BR>
     * �R�j�@@���������敪�̃`�F�b�N <BR>
     * �@@�@@�@@�R�|�P�j���������敪�ɓ��͂�����A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�s���ȃR�[�h�l�̏ꍇ�͗�O���X���[����B <BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02223<BR>   
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N  <BR>
     * �@@�@@�@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B  <BR>
     * �@@�@@�@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>   
     * �@@�@@�@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>   
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N  <BR>
     * �@@�@@�@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02224<BR>   
     * �@@�@@�@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>   
     * �@@�@@�@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>   
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);         
        //�P�j�@@���X�R�[�h�̃`�F�b�N <BR>
        //�P�|�P�j�����͂̏ꍇ�A�G���[���X���[����B
        if (branchCode == null || branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        
        //�Q�j�@@�o�^���i���j�A�o�^���i���j�̃`�F�b�N
        //�Q�|�P�j�@@�ǂ��炩����݂̂̓��͂̏ꍇ�A��O���X���[����B
        if ((registDateFrom == null && registDateTo != null) || (registDateFrom != null && registDateTo == null))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02221, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�o�^���i���j�Ɠo�^���i���j�����𖢓��͂��A�����ɓ��͂��Ȃ���΂Ȃ�Ȃ��B");
        }
        
        //�Q�|�Q�j�o�^���i���j���o�^���i���j�̏ꍇ�A�G���[���X���[����B
        if (WEB3DateUtility.compare(registDateFrom, registDateTo) == 1)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02222, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�o�^���i���j���o�^���i���j�𒴂��Ă��܂��B");
        }
        
        //�R�j�@@���������敪�̃`�F�b�N
        //�R�|�P�j���������敪�ɓ��͂�����A�s���ȃR�[�h�l�̏ꍇ�͗�O���X���[����B
        if (!WEB3AccInfoSearchCondTypeDef.ALL.equals(searchCondType)
                && !WEB3AccInfoSearchCondTypeDef.YACCOUNT.equals(searchCondType)
                && !WEB3AccInfoSearchCondTypeDef.ADMINLOCK.equals(searchCondType)
                && !WEB3AccInfoSearchCondTypeDef.BRANCHLOCK.equals(searchCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02223, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������敪���s���ȃR�[�h�l�ł��B");
        }
        
        
        //�S�j�@@�v���y�[�W�ԍ��`�F�b�N 
        //�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (WEB3StringTypeUtility.isEmpty(pageIndex))
        {
            this.pageIndex = "1";
        }
        //�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        //�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        long l_lngPageIndex = Long.parseLong(pageIndex);
        if (l_lngPageIndex < 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�T�j�@@�y�[�W���\���s���`�F�b�N
        //�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02224, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�y�[�W���\���s���������͂ł��B");
        }
        //�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        long l_lngPageSize = Long.parseLong(pageSize);
        if (l_lngPageSize < 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
