head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�N���X(WEB3AdminDirSecHostTableStatusCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  �юu��(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������N�G�X�g�I�u�W�F�N�g�B
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusCompleteRequest extends WEB3AdminDirSecHostTableUpdateCommonRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableStatusCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_status_complete";

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
     * �쐬���tFrom�iYYYYMMDDHHMM�j�B
     */
    public String createDateFrom;
    
    /**
     * (�쐬���tTo)<BR>
     * �쐬���tTo�iYYYYMMDDHHMM�j�B
     */
    public String createDateTo;
    
    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X�B
     */
    public String status;
    
    /**
     * (�X�V��X�e�[�^�X)<BR>
     * �X�V��X�e�[�^�X�B
     */
    public String updateStatus;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ��B
     */
    public String password;
    
    /**
     * (�\�[�g�L�[)
     */
    public WEB3AdminDirSecSortKey[] sortKeys;
    
    /**
     * @@roseuid 442A1C87031C
     */
    public WEB3AdminDirSecHostTableStatusCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�`�F�b�N<BR> 
     * �@@�Q�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * �R�j�@@�X�V��X�e�[�^�X�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�X�V��X�e�[�^�X == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�X�V��X�e�[�^�X��null�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02427<BR>
     * <BR>
     * �S�j�@@�Ïؔԍ��`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.�Ïؔԍ� == null�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�u�Ïؔԍ���null�v�̗�O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01090<BR>
     * <BR>
     * �T�j�@@�\�[�g�L�[�`�F�b�N<BR> 
     * �@@�T�|�P�jthis.�L���[�e�[�u���\�[�g�L�[ == null�ł������ꍇ<BR> 
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�L���[�e�[�u���\�[�g�L�[.length == 0�������ꍇ<BR> 
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�T�|�R�jthis.�L���[�e�[�u���\�[�g�L�[�̑S�v�f�ɑ΂��� <BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B <BR>
     * �@@�@@�T�|�R�|�P�j�L���[�e�[�u���\�[�g�L�[.validate()���R�[������B <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 4423A447013B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���R�[������B  
        super.validate();
        
        //�Q�j�@@���X�R�[�h�`�F�b�N  
        //�@@�Q�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B  
        if (this.branchCode == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02174, 
                this.getClass().getName() + STR_METHOD_NAME, "���X�R�[�h��null�ł��B");    
        }
        
        //�R�j�@@�X�V��X�e�[�^�X�`�F�b�N 
        //�@@�R�|�P�j�@@this.�X�V��X�e�[�^�X == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u�X�V��X�e�[�^�X��null�v�̗�O���X���[����B  
        if (this.updateStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02427, 
                this.getClass().getName() + STR_METHOD_NAME, "�X�V��X�e�[�^�X��null�ł��B");   
        }
        
        //�S�j�@@�Ïؔԍ��`�F�b�N 
        //�@@�S�|�P�j�@@this.�Ïؔԍ� == null�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u�Ïؔԍ���null�v�̗�O���X���[����B  
        if (this.password == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01090, 
                this.getClass().getName() + STR_METHOD_NAME, "�Ïؔԍ������w��ł��B");  
        }
        
        //�S�j�@@�\�[�g�L�[�`�F�b�N  
        //�@@�S�|�P�jthis.�L���[�e�[�u���\�[�g�L�[ == null�ł������ꍇ  
        //�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B  
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME, "�\�[�g�L�[�����w��ł��B");     
        }
        
        //�@@�S�|�Q�jthis.�L���[�e�[�u���\�[�g�L�[.length == 0�������ꍇ  
        //�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B  
        int l_intsortKeysLen = this.sortKeys.length;
        if (l_intsortKeysLen == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME, "�\�[�g�L�[�̗v�f�����O�ł��B");     
        }
        
        //�@@�S�|�R�jthis.�L���[�e�[�u���\�[�g�L�[�̑S�v�f�ɑ΂���  
        //�@@�@@�@@�@@���L�̃`�F�b�N���s���B  
        //�@@�@@�S�|�R�|�P�j�L���[�e�[�u���\�[�g�L�[.validate()���R�[������B 
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
        return new WEB3AdminDirSecHostTableStatusCompleteResponse(this);
    }
}
@
