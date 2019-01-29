head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�N���X(WEB3AdminDirSecHostTableSearchListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  �юu��(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���������ʃ��N�G�X�g�N���X�B
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableSearchListRequest extends WEB3AdminDirSecHostTableUpdateCommonRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableSearchListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�B
     */
    public String branchCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B
     */
    public String identityCode;
    
    /**
     * (�쐬���tFrom)<BR>
     * �쐬���tFrom(YYYYMMDDHHMM)�B
     */
    public String createDateFrom;
    
    /**
     * (�쐬���tTo)<BR>
     * �쐬���tTo(YYYYMMDDHHMM)�B
     */
    public String createDateTo;
    
    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X�B
     */
    public String status;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s���B
     */
    public String pageSize;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ��B
     */
    public String pageIndex;
    
    /**
     * (�\�[�g�L�[)
     */
    public WEB3AdminDirSecSortKey[] sortKeys;
    
    /**
     * @@roseuid 442A1C87008C
     */
    public WEB3AdminDirSecHostTableSearchListRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR> 
     * <BR>
     * �Q�j���X�R�[�h�`�F�b�N <BR>
     * �@@�Q�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * �R�j���������`�F�b�N<BR>
     * �@@(this.���X�R�[�h != null && (this,���ʃR�[�h == null <BR>
     * �@@&& this,�쐬���tFrom == null && this.�쐬���tTo == null <BR>
     * �@@&& this.�X�e�[�^�X == null))�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�u���������ɂ͕��X�R�[�h�̑��ɁA1�ȏ���͂��Ă��������B�v<BR>
     * �@@�@@�@@�@@�@@�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02426<BR>
     * <BR>
     * �S�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�S�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR> 
     * <BR>
     * <BR>
     * �T�j�\���ԍ��`�F�b�N<BR>
     * �@@�T�|�P�jthis.�\���y�[�W�ԍ� == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�T�|�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR> 
     * <BR>
     * �U�j�@@�\�[�g�L�[�`�F�b�N<BR> 
     * �@@�U�|�P�jthis.�L���[�e�[�u���\�[�g�L�[ == null�ł������ꍇ<BR> 
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�U�|�Q�jthis.�L���[�e�[�u���\�[�g�L�[.length == 0�������ꍇ<BR> 
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�U�|�R�jthis.�L���[�e�[�u���\�[�g�L�[�̑S�v�f�ɑ΂���<BR> 
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B <BR>
     * �@@�@@�U�|�R�|�P�j�L���[�e�[�u���\�[�g�L�[.validate()���R�[������B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 441652B402BF
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���R�[������B  
        super.validate();
        
        //�Q�j���X�R�[�h�`�F�b�N  
        //�@@�Q�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B  
        if (this.branchCode == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02174, 
                this.getClass().getName() + STR_METHOD_NAME, "���X�R�[�h��null�ł��B");
        }
        
        //�R�j���������`�F�b�N 
        //�@@(this.���X�R�[�h != null && (this,���ʃR�[�h == null && this,�쐬���tFrom == null &&  
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.�쐬���tTo == null && this.�X�e�[�^�X == null))�̏ꍇ�A��O���X���[ 
        //�@@�@@�@@�@@�@@�u���������ɂ͕��X�R�[�h�̑��ɁA1�ȏ���͂��Ă��������B�v�̗�O���X���[����B 
        if (this.branchCode != null && (this.identityCode == null && this.createDateFrom == null 
            && this.createDateTo == null && this.status == null))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02426, 
                this.getClass().getName() + STR_METHOD_NAME, "���������ɂ͕��X�R�[�h�̑��ɁA1�ȏ���͂��Ă��������B");
        }
        
        //�S�j�y�[�W���\���s���`�F�b�N  
        //�@@�S�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B  
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        //�@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A  
        //�@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B  
        if (!WEB3StringTypeUtility.isNumber(this.pageSize)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���������ȊO�̒l�ł��B");     
        }
        
        //�@@�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A  
        //�@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B  
        if (Integer.parseInt(this.pageSize) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���̒l��0�ȉ��ł��B");     
        }
        
        //�T�j�\���ԍ��`�F�b�N 
        //�@@�T�|�P�jthis.�\���y�[�W�ԍ� == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B 
        if (this.pageIndex == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog. BUSINESS_ERROR_00089, 
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ������w��ł��B");     
        }
        
        //�@@�T�|�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A  
        //�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B  
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");     
        }
        
        //�@@�T�|�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A  
        //�@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B  
        if (Integer.parseInt(this.pageIndex) <= 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");     
        }
        
        //�U�j�@@�\�[�g�L�[�`�F�b�N  
        //�@@�U�|�P�jthis.�L���[�e�[�u���\�[�g�L�[ == null�ł������ꍇ  
        //�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B  
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME, "�\�[�g�L�[�����w��ł��B");     
        }
        
        //�@@�U�|�Q�jthis.�L���[�e�[�u���\�[�g�L�[.length == 0�������ꍇ  
        //�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B  
        int l_intsortKeysLen = this.sortKeys.length;
        if (l_intsortKeysLen == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME, "�\�[�g�L�[�̗v�f�����O�ł��B");     
        }
        
        //�@@�U�|�R�jthis.�L���[�e�[�u���\�[�g�L�[�̑S�v�f�ɑ΂���  
        //�@@�@@�@@�@@���L�̃`�F�b�N���s���B  
        //�@@�@@�U�|�R�|�P�j�L���[�e�[�u���\�[�g�L�[.validate()���R�[������B 
        for (int i = 0 ; i < l_intsortKeysLen ; i++) 
        {
            sortKeys[i].validate();    
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
        return new WEB3AdminDirSecHostTableSearchListResponse(this);
    }

}
@
