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
filename	WEB3AdminFPTForceLogoutMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��� ���ʖ����� �������O�A�E�g���C�����N�G�X�g(WEB3AdminFPTForceLogoutMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g���C�����N�G�X�g<BR>
 * 
 * @@author ��
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutMainRequest extends WEB3BackRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutMainRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_main";
    
    /**
     * �،���ЃR�[�h
     */
    public String institutionCode;
    
    /**
     * �X���b�hNo
     */
    public Long threadNo;
    
    /**
     * From����ID
     */
    public Long accountIdFrom;
    
    /**
     * To����ID
     */
    public Long accountIdTo;
    
    /**
     * �Ǘ���ID
     */
    public Long adminId;
    
    /**
     * �Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������
     */
    public WEB3AdminFPTForceLogoutProductCondition forceLogoutProductCondition;
    
    /**
     * @@roseuid 47DF4FB701EE
     */
    public WEB3AdminFPTForceLogoutMainRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
     * 
     * �@@�P�|�P�j�@@this.�،���ЃR�[�h == null�̏ꍇ�A
     * �@@�@@�u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B
     * 
     * �Q�j�@@�X���b�hNo�`�F�b�N
     * �@@�Q�|�P�j�@@this.�X���b�hNo == null�̏ꍇ�A
     * �@@�@@�u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B
     * 
     * �R�j�@@From����ID�`�F�b�N
     * �@@�R�|�P�j�@@this.From����ID == null�̏ꍇ�A
     * �@@�@@�uFrom����ID�������́v�̗�O���X���[����B
     * 
     * �S�j�@@To����ID�`�F�b�N
     * �@@�S�|�P�j�@@this.To����ID == null�̏ꍇ�A
     * �@@�@@�uTo����ID�i���j�������́v�̗�O���X���[����B
     * 
     * �T�j�@@�Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ�������
     * �@@�T�|�P�j�@@this.�Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ������� == null�̏ꍇ�A
     * �@@�@@�@@��O���X���[����B
     * 
     * �@@�T�|�Q�j�@@this.�Ǘ��� ���ʖ����� 
     * �������O�A�E�g�Ώۖ�������.validate()���R�[������B
     * @@roseuid 47CF77BE01B3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�،���ЃR�[�h�`�F�b�N 
        //�@@�P�|�P�j�@@this.�،���ЃR�[�h == null�̏ꍇ�A 
        //�@@�@@�u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B 
        if (this.institutionCode == null) 
        {
            log.debug("�،���ЃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }
        
        //�Q�j�@@�X���b�hNo�`�F�b�N 
        //�@@�Q�|�P�j�@@this.�X���b�hNo == null�̏ꍇ�A 
        //�@@�@@�u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B 
        if (this.threadNo == null) 
        {
            log.debug("�X���b�h�ԍ��̎w��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X���b�h�ԍ��̎w��Ȃ��B");
        }
        
        //�R�j�@@From����ID�`�F�b�N 
        //�@@�R�|�P�j�@@this.From����ID == null�̏ꍇ�A 
        //�@@�@@�uFrom����ID�������́v�̗�O���X���[����B 
        if (this.accountIdFrom == null) 
        {
            log.debug("From����ID�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02421,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "From����ID�������͂ł��B");
        }
        
        //�S�j�@@To����ID�`�F�b�N 
        //�@@�S�|�P�j�@@this.To����ID == null�̏ꍇ�A 
        //�@@�@@�uTo����ID�i���j�������́v�̗�O���X���[����B 
        if (this.accountIdTo == null) 
        {
            log.debug("To����ID�i���j�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02422,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "To����ID�i���j�������͂ł��B");
        }
        
        //�T�j�@@�Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ��������`�F�b�N 
        //�@@�T�|�P�j�@@this.�Ǘ��� ���ʖ����� �������O�A�E�g�Ώۖ������� == null�̏ꍇ�A 
        //�@@�@@�u���ʖ������������O�A�E�g�Ώۖ��������̃`�F�b�N�G���[�v�̗�O���X���[����B 
        if (this.forceLogoutProductCondition == null) 
        {
            log.debug("���ʖ������������O�A�E�g�Ώۖ��������̃`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420, //??
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʖ������������O�A�E�g�Ώۖ��������̃`�F�b�N�G���[�B");
        }
        
        //�@@�T�|�Q�j�@@this.�Ǘ��ҏ��ʖ������������O�A�E�g�Ώۖ�������.validate()���R�[������B
        this.forceLogoutProductCondition.validate();
        
        log.exiting(STR_METHOD_NAME);
     
    }

    /* (�� Javadoc)
     * @@see webbroker3.common.message.WEB3BackRequest#createResponse()
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminFPTForceLogoutMainResponse();
    }
}
@
