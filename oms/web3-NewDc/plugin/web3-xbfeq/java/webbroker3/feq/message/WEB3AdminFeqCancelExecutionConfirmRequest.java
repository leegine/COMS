head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o��������m�F���N�G�X�g(WEB3AdminFeqCancelExecutionConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
Revesion History : 2009/08/03 �Ԑi(���u)   ���f���@@No.504�Ή�
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������o��������m�F���N�G�X�g)<BR>
 * �Ǘ��ҊO�������o��������m�F���N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionConfirmRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_cancelExecutionConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h
     */
    public String managementCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (���ԍ�)<BR>
     * ���ԍ�
     */
    public String execNo;
    
    /**
     * @@roseuid 42CE39FD01F4
     */
    public WEB3AdminFeqCancelExecutionConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�^�p�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02032<BR>
     * �@@�P�|�Q�j�@@5���̔��p�����łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_03163<BR>
     * <BR>
     * �Q�j�@@���ԍ��̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02039<BR>
     * �@@�Q�|�Q�j�@@�L������3���ȓ��̐��̐����l�ł��邱�ƁB<BR>
     * �@@�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02040<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4294319502C7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�^�p�R�[�h�̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.managementCode))
        {
            log.debug("�^�p�R�[�h�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h�������͂ł��B");
        }
        
        // �P�|�Q�j�@@5���̔��p�����łȂ��ꍇ�A��O���X���[����B
        if (this.managementCode.length() != 5 || 
            !WEB3StringTypeUtility.isDigit(this.managementCode))
        {
            log.debug("�^�p�R�[�h��5���̔��p�����ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h��5���̔��p�����ł͂���܂���B");
        }
        
        // �Q�j�@@���ԍ��̃`�F�b�N
        // �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.execNo))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02039,
                getClass().getName() + STR_METHOD_NAME,
                "���ԍ��������͂ł��B");
        }

        // �@@�Q�|�Q�j�@@�L������3���ȓ��̐��̐����l�ł��邱�ƁB�ȊO�̏ꍇ�A��O���X���[����B
        final int EXECNO_LEN = 3;
        final int ZERO = 0;
        if (!WEB3StringTypeUtility.isDigit(this.execNo)
            || this.execNo.length() > EXECNO_LEN
            || ZERO == Integer.parseInt(this.execNo))
        {
            log.debug("���ԍ� = " + this.execNo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02040,
                getClass().getName() + STR_METHOD_NAME,
                "���ԍ����L������3���ȓ��̐��̐����l�ł͂���܂���B");
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
        return new WEB3AdminFeqCancelExecutionConfirmResponse(this);
    }
}
@
