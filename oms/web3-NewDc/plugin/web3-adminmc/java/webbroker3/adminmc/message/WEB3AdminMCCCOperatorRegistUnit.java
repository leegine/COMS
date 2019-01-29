head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.49.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : CC�I�y���[�^�o�^���(WEB3AdminMCCCOperatorRegistUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 ���z (���u) �V�K�쐬   
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
 * (CC�I�y���[�^�o�^���)<BR>
 * CC�I�y���[�^�o�^���<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistUnit extends Message 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistUnit.class);
    
    /**
     * (���X�R�[�h) <BR>
     * ���X�R�[�h <BR>
     */
    public String branchCode;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h<BR>
     */
    public String operatorCode;
    
    /**
     * (�I�y���[�^��)<BR>
     * �I�y���[�^��<BR>
     */
    public String operatorName;
    
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
     * (��s�����\�敪)<BR>
     * ��s�����\�敪<BR>
     * <BR>
     * 0�F�@@�s��<BR>
     * 1�F�@@�\<BR>
     */
    public String accountOrderDiv;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String departmentCode;
    
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
     * @@roseuid 41986429033C
     */
    public WEB3AdminMCCCOperatorRegistUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_00833          <BR>
     * �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01024         <BR>
     * <BR>
     * �Q�j�@@�I�y���[�^�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01192         <BR>
     * <BR>
     * �R�j�@@�I�y���[�^���̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01193         <BR>
     * �@@�R�|�Q�j�@@�o�C�g����40byte���傫���ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01194         <BR>
     * <BR>
     * �S�j�@@��s�����\�敪�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01195         <BR>
     * �@@�S�|�Q�j�@@�R�[�h�l���h�\�h�܂��́A�h�s�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01196         <BR>
     * <BR>
     * �T�j�@@�����R�[�h�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01197         <BR>
     * �@@�T�|�Q�j�@@��������2�����łȂ��ꍇ�A��O���X���[����B<BR>
     *       class         :  WEB3BusinessLayerException<BR>
     *       tag            : BUSINESS_ERROR_01198         <BR>
     * <BR>
     * @@roseuid 417E239802A8
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //�P�j���X�R�[�h�̃`�F�b�N
        //�P�|�P�j�����͂̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_00833    
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("���X�R�[�h������");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + l_strMethodName);      
        }
        //�P�|�Q�j��������3byte�łȂ��ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01024  
        else if (WEB3StringTypeUtility.getByteLength(this.branchCode) != 3)
        {
            log.error("���X�R�[�h��������3byte�łȂ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + l_strMethodName,
                "���X�R�[�h�̃T�C�Y���s���ł��B" +
                " [���X�R�[�h] = " + this.branchCode);                 
        }
        
        //�Q�j�I�y���[�^�R�[�h�̃`�F�b�N
        //�Q�|�P�j�����͂̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01192        
        if (this.operatorCode == null || "".equals(this.operatorCode))
        {
            log.error("�I�y���[�^�R�[�h������");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01192,
                this.getClass().getName() + "." + l_strMethodName);                  
        }
       
        //�R�j�I�y���[�^���̃`�F�b�N
        //�R�|�P�j�����͂̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01193         
        if (this.operatorName == null || "".equals(this.operatorName))
        {
            log.error("�I�y���[�^��������");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01193,
                this.getClass().getName() + "." + l_strMethodName);         
        }
        //�R�|�Q�j�o�C�g����40byte���傫���ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01194         
        else if (WEB3StringTypeUtility.getByteLength(this.operatorName) > 40)
        {
            log.error("�o�C�g����40byte���傫��");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01194,
                this.getClass().getName() + "." + l_strMethodName);    
        }
        
        //�S�j��s�����\�敪�̃`�F�b�N
        //�S�|�P�j�����͂̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01195       
        if (this.accountOrderDiv == null || "".equals(this.accountOrderDiv))
        {
            log.error("��s�����\�敪������");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01195,
                this.getClass().getName() + "." + l_strMethodName);                
        }
        //�S�|�Q�j�R�[�h�l���h�\�h�܂��́A�h�s�h�ȊO�̏ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01196       
        else if (!(this.accountOrderDiv.equals("0")  || this.accountOrderDiv.equals("1")))
        {
            log.error("��s�����\�敪�����̓R�[�h�l���h�\�h�܂��́h�s�h�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01196,
                this.getClass().getName() + "." + l_strMethodName);                
        }
        
        //�T�j�����R�[�h�̃`�F�b�N
        //�T�|�P�j�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        //       class:WEB3BusinessLayerException
        //         tag:BUSINESS_ERROR_01197 
        if (this.departmentCode != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.departmentCode))
            {
                log.error("�����R�[�h�����ȊO�̕������܂܂��");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01197,
                    this.getClass().getName() + "." + l_strMethodName);                
            
            }
            //�T�|�Q�j��������2�����łȂ��ꍇ�A��O���X���[����B
            //       class:WEB3BusinessLayerException
            //         tag:BUSINESS_ERROR_01198    
            else if (WEB3StringTypeUtility.getByteLength(this.departmentCode) != 2)
            {
                log.error("�����R�[�h��������2�����łȂ�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01198,
                    this.getClass().getName() + "." + l_strMethodName);                  
            }
        }
                    
        log.exiting(l_strMethodName);
    }
    
    /**
     * (validate�p�X���[�h)<BR>
     * �p�X���[�h�P�C�Q���`�F�b�N����<BR>
     * <BR>
     * �P�j�@@�p�X���[�h�P�C�p�X���[�h�Q�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *              class :  WEB3BusinessLayerException <BR>
     *              tag :    BUSINESS_ERROR_01910 <BR> 
     *              class :  WEB3BusinessLayerException <BR>
     *              tag :    BUSINESS_ERROR_019101 <BR>
     * �@@�P�|�Q�j�@@�p�X���[�h�P�ƃp�X���[�h�Q������łȂ��ꍇ�A��O���X���[����B <BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :    BUSINESS_ERROR_90211            <BR>
     * <BR>
     * @@roseuid 417E253E00B4
     */
    public void validatePassword() throws WEB3BaseException
    {
        String l_strMethodName = "validatePassword()";
        log.entering(l_strMethodName);
        
        //�P�j�p�X���[�h�P�C�p�X���[�h�Q�̃`�F�b�N
        //�P�|�P�j�����͂̏ꍇ�A��O���X���[����B 
        //       class:WEB3BusinessLayerException          
        //         tag:BUSINESS_ERROR_01199     
        if (this.password1 == null || "".equals(this.password1.trim()))
        {
            //��O
            log.error("�u�p�X���[�h�P�����́v�̗�O���X���[����B");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01910,
                this.getClass().getName() + l_strMethodName);
        }
         
        if (this.password2 == null || "".equals(this.password2.trim()))
        {
            //��O
            log.error("�u�p�X���[�h�Q�����́v�̗�O���X���[����B");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01911,
                this.getClass().getName() + l_strMethodName);
        }


        //�P�|�Q�j�p�X���[�h�P�ƃp�X���[�h�Q������łȂ��ꍇ�A��O���X���[����B 
        //       class:WEB3BusinessLayerException           
        //         tag:BUSINESS_ERROR_90211  
        else if (!this.password1.equals(this.password2))
        {
            log.error("�p�X���[�h�P�ƃp�X���[�h�Q������łȂ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90211,
                this.getClass().getName() + "." + l_strMethodName);                    
        }                   
        
        log.exiting(l_strMethodName); 
    }
}
@
