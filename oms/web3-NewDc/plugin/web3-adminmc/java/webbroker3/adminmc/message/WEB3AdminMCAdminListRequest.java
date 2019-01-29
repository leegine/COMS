head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g(WEB3AdminMCAdminListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.adminmc.define.WEB3AdminMCAdminRegistUnitDef;


/**
 * (�Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��҈ꗗ���N�G�X�g<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminListRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     */
    public String administratorCode;
    
    /**
     * (�Ǘ��Җ�)<BR>
     * �Ǘ��Җ�<BR>
     */
    public String administratorName;
    
    /**
     * (�������x���R�[�h)<BR>
     * �������x���R�[�h<BR>
     */
    public String permissionLevel;
    
    /**
     * (�G���[��)<BR>
     * �G���[��<BR>
     * <BR>
     * �����O�C���G���[��<BR>
     * <BR>
     */
    public String errorCount;
    
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
    public WEB3AdminMCSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 41986419034B
     */
    public WEB3AdminMCAdminListRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   �@@BUSINESS_ERROR_00834            <BR>
     * <BR>
     * �Q�j�@@�Ǘ��Җ��̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�o�C�g����100byte���傫���ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   �@@BUSINESS_ERROR_01209            <BR>
     * <BR>
     * �R�j�@@�G���[�񐔂̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01211           <BR>
     * �@@�R�|�Q�j�@@�L���l�� 0�`9 �͈͓̔��łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_01212             <BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00090              <BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00616            <BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00091         <BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00092<BR>  
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00617          <BR>
     * <BR>
     * �U�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00231          <BR>
     * �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00232          <BR>
     * �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B <BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.���X�R�[�h <BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.�Ǘ��҃R�[�h <BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.���[���A�h���X <BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.�������x���R�[�h<BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.�G���[��<BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.�X�V����<BR>
     * �@@�@@�@@�@@ �Ǘ��ғo�^���.�X�V�҃R�[�h<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_00086             <BR>
     * <BR>
     * @@roseuid 41773E97000F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        // �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        // �@@�P�|�P�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   �@@BUSINESS_ERROR_01210            <BR>
        if (!(this.branchCode == null) && !(this.branchCode == "") && WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            log.error(" ��������3byte�łȂ��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                            this.getClass().getName() + STR_METHOD_NAME);
        }         

        // �Q�j�@@�Ǘ��Җ��̃`�F�b�N<BR>
        // �@@�Q�|�P�j�@@�o�C�g����100byte���傫���ꍇ�A��O���X���[����B<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   �@@BUSINESS_ERROR_01209            <BR>
        if (!(this.administratorName == null) && !(this.administratorName == "") && WEB3StringTypeUtility.getByteLength(this.administratorName) > 100)
        {
            log.error(" ��������100byte���傫���ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01209,
                            this.getClass().getName() + STR_METHOD_NAME);
        }         

        // �R�j�@@�G���[�񐔂̃`�F�b�N<BR>
        // �@@�R�|�P�j�@@�����łȂ��ꍇ�A��O���X���[����B<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :   BUSINESS_ERROR_01211           <BR>
        if (this.errorCount != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.errorCount))
            {
                log.error(" �����łȂ��ꍇ .");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01211,
                                this.getClass().getName() + STR_METHOD_NAME);
            }        
               
            // �@@�R�|�Q�j�@@�L���l�� 0�`9 �͈͓̔��łȂ��ꍇ�A��O���X���[����B<BR>
            //                class :  WEB3BusinessLayerException <BR>
            //                tag :  BUSINESS_ERROR_01212             <BR>
            int l_errorCount = Integer.parseInt(this.errorCount);
            if ((l_errorCount > 9) || (l_errorCount < 0))
            {
                log.error(" �L���l�� 0�`9 �͈͓̔��łȂ��ꍇ .");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01212,
                                this.getClass().getName() + STR_METHOD_NAME);
            }        

        }
        

        // �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        // �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
        if (this.pageIndex == null )
        {
           this.pageIndex = "1"; 
            
        }   

        // �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00090              <BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" �����ȊO�̕������܂܂��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00616            <BR>
        int l_pageIndex = Integer.parseInt(this.pageIndex);
        if (l_pageIndex <= 0)
        {
            log.error(" �}�C�i�X�l�̏ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
        // �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00091         <BR>
        if (this.pageSize == null )
        {
            log.error("�y�[�W���\���s���`�F�b�N������.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   

        // �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00092<BR>  
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error(" �����ȊO�̕������܂܂��ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00617          <BR>
        int l_pageSize = Integer.parseInt(this.pageSize);
        if (l_pageSize <= 0)
        {
            log.error(" �}�C�i�X�l�̏ꍇ .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                            this.getClass().getName() + STR_METHOD_NAME);
        }        

        // �U�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
        // �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00231          <BR>
        if (this.sortKeys == null )
        {
            log.error("�\�[�g�L�[�̃`�F�b�N������.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   

        // �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :    BUSINESS_ERROR_00232          <BR>
        int l_length = this.sortKeys.length;
        if (l_length == 0 )
        {
            log.error("�\�[�g�L�[�̗v�f�� == 0.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   

        // �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
        for (int i = 0 ; i < l_length; i++)
        {
        // �@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
            this.sortKeys[i].validate();

        // �@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B <BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.���X�R�[�h <BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.�Ǘ��҃R�[�h <BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.���[���A�h���X <BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.�������x���R�[�h<BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.�G���[��<BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.�X�V����<BR>
        // �@@�@@�@@�@@ �Ǘ��ғo�^���.�X�V�҃R�[�h<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :  BUSINESS_ERROR_00086             <BR> 
              
            if (!(WEB3AdminMCAdminRegistUnitDef.BRANCHCODE.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.ADMINISTRATORCODE.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.MAILADDRESS.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.PERMISSIONLEVEL.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.ERRORCOUNT.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.UPDATETIMESTAMP.equals(this.sortKeys[i].keyItem)) &&
               !(WEB3AdminMCAdminRegistUnitDef.UPDATERCODE.equals(this.sortKeys[i].keyItem)))
            {
                log.error("�\�[�g�L�[.�L�[���ڂ̒l���Ǘ��ғo�^���ȊO�̏ꍇ�̃G���[.");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                                this.getClass().getName() + STR_METHOD_NAME);                        	   
            }
        }
    } 
    /**
     * @@return WEB3GenResponse
     * @@roseuid 419864190399
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminListResponse(this);
    }
}
@
