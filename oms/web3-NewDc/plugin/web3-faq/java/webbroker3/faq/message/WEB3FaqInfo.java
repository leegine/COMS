head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇�����(WEB3FaqInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�⍇�����)<BR>
 * �⍇�����<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqInfo extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInfo.class);
        
    /**
     * (�⍇���R�[�h)<BR>
     * �⍇���R�[�h<BR>
     */
    public String faqCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;
    
    /**
     * (�⍇������)<BR>
     * �⍇������<BR>
     */
    public Date faqDate;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String subject;
    
    /**
     * (�@@�\�h�c)<BR>
     * �@@�\�h�c<BR>
     * <BR>
     * �� �e�Љ�ʂŔC�ӂɎg�p����R�[�h�B<BR>
     */
    public String transactionId;
    
    /**
     * (�⍇�����e)<BR>
     * �⍇�����e<BR>
     */
    public String faqText;
    
    /**
     * (�⍇�����)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@return webbroker3.faq.message.WEB3FaqInfo
     * @@roseuid 41AC2A9F030C
     */
    public WEB3FaqInfo() 
    {
     
    }
    
    /**
     * �⍇����񃊃N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ڋq���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01690<BR>
     * <BR>
     * �Q�j�@@�����̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�̂�<BR>
     * �@@�Q�|�P�j�@@�����T�C�Y��1,000byte���傫���ꍇ�ilength > 1000�j�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01692<BR>
     * <BR>
     * �R�j�@@�⍇�����e�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01694<BR>
     * �@@�R�|�Q�j�@@�����T�C�Y��2,000byte���傫���ꍇ�ilength > 2000�j�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01695<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6F38C03A6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ڋq���̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.accountName))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01690, 
                this.getClass().getName(),
                "�ڋq�����w��ł��B");
        }
        
        //�Q�j�@@�����̃`�F�b�N �����͂�����ꍇ�̂�
        //�Q�|�P�j�@@�����T�C�Y��1,000byte���傫���ꍇ�ilength > 1000�j�A��O���X���[����B
        if (WEB3StringTypeUtility.isNotEmpty(this.subject) && WEB3StringTypeUtility.getByteLength(this.subject) > 1000)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01692, 
                this.getClass().getName(),
                "�⍇�������̒l������l�𒴂��Ă��܂��B[����l] = 1000 " + 
                "[�⍇������.length] = " + WEB3StringTypeUtility.getByteLength(this.subject));
        }
        
        //�R�j�@@�⍇�����e�̃`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.faqText))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01694, 
                this.getClass().getName(),
                "�⍇�����e�����w��ł��B");
        }
        
        //�R�|�Q�j�@@�����T�C�Y��2,000byte���傫���ꍇ�ilength > 2000�j�A��O���X���[����B
        if (WEB3StringTypeUtility.isNotEmpty(this.faqText) && WEB3StringTypeUtility.getByteLength(this.faqText) > 2000)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01695, 
                this.getClass().getName(),
                "�⍇�����e�̒l������l�𒴂��Ă��܂��B[����l] = 2000 " + 
                "[�⍇�����e.length] = " + WEB3StringTypeUtility.getByteLength(this.faqText));
        }
       

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (validate���[���A�h���X)<BR>
     * ���[���A�h���X�̕K�{���̓`�F�b�N���s���B<BR>
     * �i���@@�����O�C���̏ꍇ�̂ݎg�p����j<BR>
     * <BR>
     * �P�j�@@���[���A�h���X�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01700<BR>
     * 
     * @@roseuid 41AD5A480073
     */
    public void validateMailAddress() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateMailAddress() ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���[���A�h���X�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B

        if (WEB3StringTypeUtility.isEmpty(this.mailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700, 
                this.getClass().getName(),
                "���[���A�h���X�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
