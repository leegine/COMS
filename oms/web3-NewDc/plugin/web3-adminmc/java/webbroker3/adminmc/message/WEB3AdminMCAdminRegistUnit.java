head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��ғo�^���(WEB3AdminMCAdminRegistUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ғo�^���)<BR>
 * �Ǘ��ғo�^���<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistUnit extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistUnit.class);

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
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;
    
    /**
     * (�p�X���[�h�P)<BR>
     * �p�X���[�h�P<BR>
     */
    public String password1;
    
    /**
     * (�p�X���[�h�Q)<BR>
     * �p�X���[�h�Q<BR>
     * <BR>
     * ���@@�p�X���[�h�i�m�F�j<BR>
     */
    public String password2;
    
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
     */
    public String errorCount;
    
    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     */
    public Date updateTimeStamp;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * @@roseuid 4198642203A9
     */
    public WEB3AdminMCAdminRegistUnit() 
    {
     
    }
    
    /**
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_00833           <BR>
     * <BR>
     * �Q�j�@@�Ǘ��҃R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01215           <BR>
     * <BR>
     * �R�j�@@�Ǘ��Җ��̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01219            <BR>
     * �@@�R�|�Q�j�@@�o�C�g����100byte���傫���ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :   BUSINESS_ERROR_01209            <BR>
     * <BR>
     * �S�j�@@�������x���R�[�h�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag : BUSINESS_ERROR_01201              <BR>
     * �@@�S�|�Q�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01202           <BR>
     * �@@�S�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01203          <BR>
     * <BR>
     * @@roseuid 417CB83C02B9
     */
    public void validate() throws WEB3BaseException
    {
         final String STR_METHOD_NAME = " validate()"; 
         log.entering(STR_METHOD_NAME);  
         
         // �P�j�@@���X�R�[�h�����͂̏ꍇ
         if (this.branchCode == null || "".equals(this.branchCode))
         {
             //��O
             log.error("�u���X�R�[�h�����́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                                              this.getClass().getName() + STR_METHOD_NAME);
         }
         // �Q�j �Ǘ��҃R�[�h�����͂̏ꍇ
         if (this.administratorCode == null || "".equals(this.administratorCode))
         {
             //��O
             log.error("�u�Ǘ��҃R�[�h�����́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_01215,
                                              this.getClass().getName() + STR_METHOD_NAME);
         }
         // �R�j �Ǘ��Җ��̃`�F�b�N
         // �R�|�P�j�@@�����͂̏ꍇ
         if (this.administratorName == null || "".equals(this.administratorName))
         {
             //��O
             log.error("�u�Ǘ��Җ������́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                              WEB3ErrorCatalog.BUSINESS_ERROR_01219,
                                              this.getClass().getName() + STR_METHOD_NAME);
         }
         // �R�|�Q�j�@@�o�C�g����100byte���傫���ꍇ
         if (WEB3StringTypeUtility.getByteLength(this.administratorName) > 100)
         {
             log.error("�o�C�g����100byte���傫��");
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01209,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         // �S�j�@@�������x���R�[�h�̃`�F�b�N
         // �S�|�P�j�@@�����͂̏ꍇ
         if (this.permissionLevel == null || "".equals(this.permissionLevel))
         {
             //��O
             log.error("�u�������x���R�[�h�����́v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01201,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         // �S�|�Q�j�@@��������3byte�łȂ��ꍇ
         if (WEB3StringTypeUtility.getByteLength(this.permissionLevel) != 3)
         {
             //��O
             log.error("�u�������x���R�[�h��������3byte�łȂ��ꍇ�v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01202,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         // �S�|�R�j�@@�����ȊO�̕������܂܂��ꍇ
         if (!WEB3StringTypeUtility.isNumber(this.permissionLevel))
         {
             //��O
             log.error("�u�������x���R�[�h�����ȊO�̕������܂܂��ꍇ�v�̗�O���X���[����B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01203,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�p�X���[�h)<BR>
     * �p�X���[�h�P�C�Q���`�F�b�N����<BR>
     * <BR>
     * �P�j�@@�p�X���[�h�P�C�p�X���[�h�Q�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01910 <BR> 
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_019101 <BR>
     * �@@�P�|�Q�j�@@�p�X���[�h�P�ƃp�X���[�h�Q������łȂ��ꍇ�A��O���X���[����B <BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :  BUSINESS_ERROR_90211             <BR>
     * <BR>
     * @@roseuid 417DAB780280
     */
    public void validatePassword() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePassword()"; 
        log.entering(STR_METHOD_NAME);  
        // �P�j�@@�p�X���[�h�P�C�p�X���[�h�Q�̃`�F�b�N
        // �P�|�P�j�@@�����͂̏ꍇ
        if (this.password1 == null || "".equals(this.password1.trim()))
        {
            //��O
            log.error("�u�p�X���[�h�P�����́v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01910,
                this.getClass().getName() + STR_METHOD_NAME);
        }
         
        if (this.password2 == null || "".equals(this.password2.trim()))
        {
            //��O
            log.error("�u�p�X���[�h�Q�����́v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01911,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �P�|�Q�j�@@�p�X���[�h�P�ƃp�X���[�h�Q������łȂ��ꍇ
        if (!this.password1.equals(this.password2))
        {
            //��O
            log.error("�u�p�X���[�h�P�ƃp�X���[�h�Q������łȂ��ꍇ�v�̗�O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_90211,
               this.getClass().getName() + STR_METHOD_NAME);
        }
         
         log.exiting(STR_METHOD_NAME);
    }
}
@
