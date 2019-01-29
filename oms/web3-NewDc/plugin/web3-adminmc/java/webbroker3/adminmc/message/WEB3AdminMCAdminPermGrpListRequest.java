head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.47.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g(WEB3AdminMCAdminPermGrpListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  �Ɍ��t (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.adminmc.define.WEB3AdminMCAdminTypeUnitDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��Ҍ����O���[�v�ꗗ���N�G�X�g<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpListRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * ( �v���y�[�W�ԍ�)<BR>
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
    public WEB3AdminMCSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 4198641E003E
     */
    public WEB3AdminMCAdminPermGrpListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�P�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00090              <BR>
     * �@@�P�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00616            <BR>
     * <BR>
     * �Q�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00091         <BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00092             <BR>
     * �@@�Q�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00617          <BR>
     * <BR>
     * �R�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�R�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00231          <BR>
     * �@@�R�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00232          <BR>
     * �@@�R�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�R�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�R�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B <BR>
     * �@@�@@�@@�@@ �Ǘ��҃^�C�v���.�������x���R�[�h <BR>
     * �@@�@@�@@�@@ �Ǘ��҃^�C�v���.�������x������<BR>
     * �@@�@@�@@�@@ �Ǘ��҃^�C�v���.DIR�Ǘ��҃t���O<BR>
     * �@@�@@�@@�@@ �Ǘ��҃^�C�v���.�S���X���t���O<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00086          <BR>
     * <BR>
     * <BR>
     * @@roseuid 4177409802AF
     */
    public void validate() throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�v���y�[�W�ԍ��`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
           this.pageIndex = "1"; 
            
        }
                 
        //�P�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if(!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" �����ȊO�̕������܂܂��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
                 
        //�P�|�R�j �}�C�i�X�l�̏ꍇ�A��O���X���[����B
        int l_pageIndex = Integer.parseInt(this.pageIndex);
        if (l_pageIndex <= 0)
        {
            log.error(" �}�C�i�X�l�̏ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                            this.getClass().getName() + STR_METHOD_NAME);
        }  

        //�Q�j�@@�y�[�W���\���s���`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("�y�[�W���\���s���`�F�b�N������.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }  
        
        //�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if(!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error(" �����ȊO�̕������܂܂��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                            this.getClass().getName() + STR_METHOD_NAME);
        } 
        
        //�Q�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        long l_pageSize = Long.parseLong(this.pageSize);
        if (l_pageSize <= 0)
        {
            log.error(" �}�C�i�X�l�̏ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                            this.getClass().getName() + STR_METHOD_NAME);
        }  
        
        //�R�j�@@�\�[�g�L�[�̃`�F�b�N 
        //�R�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null || "".equals(this.sortKeys))
        {
            log.error("�\�[�g�L�[�̃`�F�b�N������.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }        
        //�R�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        int l_length = this.sortKeys.length;
        if (l_length == 0 )
        {
            log.error("�\�[�g�L�[�̗v�f�� == 0.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                            this.getClass().getName() + STR_METHOD_NAME);            
        } 
        //�R�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        for(int i = 0 ; i < l_length; i++)
        {
            //�R�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            this.sortKeys[i].validate();
            
            //�R�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
            if(!(WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminTypeUnitDef.DIR_ADMIN_FLAG.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminTypeUnitDef.ALL_BRANCH_PERMISSION_FLAG.equals(this.sortKeys[i].keyItem)))
            {
                log.error("�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ.");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                                this.getClass().getName() + STR_METHOD_NAME);                              
            }            
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641E008C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpListResponse(this);
    }
}
@
