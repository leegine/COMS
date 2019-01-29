head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutProductCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������(WEB3AdminFPTForceLogoutProductCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������
 * 
 * @@author ��
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutProductCondition extends Message
{
	
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutProductCondition.class);
    
    /**
     * ���ʎ�ރR�[�h�ꗗ
     */
    public String[] documentCatCodeArr;
    
    /**
     * ���ʋ敪�ꗗ
     */
    public String[] documentDivArr;
    
    /**
     * �d�q�������R�[�h�ꗗ
     */
    public String[] batoProductCodeArr;
    
    /**
     * @@roseuid 47DF46770121
     */
    public WEB3AdminFPTForceLogoutProductCondition() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
     * 
     * �P�j���ʎ�ރR�[�h�ꗗ ==null ���� �T�C�Y��1 �̏ꍇ�A
     * �@@��O�iBUSINESS_ERROR_03013�j���X���[����B
     * 
     * �Q�j���ʋ敪�ꗗ ==null ���� �T�C�Y��1 �̏ꍇ�A
     * �@@��O�iBUSINESS_ERROR_02948�j���X���[����B
     * 
     * �R�j�d�q�������R�[�h�ꗗ ==null ���� �T�C�Y��1 �̏ꍇ�A
     * �@@��O�iBUSINESS_ERROR_03009�j���X���[����B
     * @@roseuid 47CF784F01C1
     */
    public void validate()  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���ʎ�ރR�[�h�ꗗ ==null ���� �T�C�Y��1 �̏ꍇ�A
        // �@@��O�iBUSINESS_ERROR_03013�j���X���[����B
        if (this.documentCatCodeArr == null || this.documentCatCodeArr.length <1 ) 
        {
            log.debug("���ʎ�ރR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʎ�ރR�[�h�����w��ł��B");
        }
        
        //�Q�j���ʋ敪�ꗗ ==null ���� �T�C�Y��1 �̏ꍇ�A
        //�@@��O�iBUSINESS_ERROR_02948�j���X���[����B
        if (this.documentDivArr == null || this.documentDivArr.length < 1) 
        {
            log.debug("���ʋ敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʋ敪�����w��ł��B");
        }
        
        //�R�j�d�q�������R�[�h�ꗗ ==null ���� �T�C�Y��1 �̏ꍇ�A
        //�@@��O�iBUSINESS_ERROR_03009�j���X���[����B 
        if (this.batoProductCodeArr == null || this.batoProductCodeArr.length <1 ) 
        {
            log.debug("�d�q�������R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
