head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�����������̓��N�G�X�g(WEB3AdminFeqOrderAndExecutionInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
Revesion History : 2009/08/03 �Ԑi(���u)   ���f���@@No.507�Ή�
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
 * (�Ǘ��ҊO�����������̓��N�G�X�g)<BR>
 * �Ǘ��ҊO�����������̓��N�G�X�g�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionInputRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAndExecutionInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * <BR>
     * �����񃊃N�G�X�g�̏ꍇ�́Anull�B
     */
    public String managementCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * @@roseuid 42CE39F902AF
     */
    public WEB3AdminFeqOrderAndExecutionInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�^�p�R�[�h�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�̂݃`�F�b�N�����{����B<BR>
     * �@@�P�|�P�j�@@5���̔��p�����łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_03163<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B645CB0215
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        if (managementCode != null) 
        {
            if (!(WEB3StringTypeUtility.isDigit(managementCode) 
                    && managementCode.length() == 5)) 
            {
                log.debug("�^�p�R�[�h��5���̔��p�����ł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�^�p�R�[�h��5���̔��p�����ł͂���܂���B"); 
            } 
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
        return new WEB3AdminFeqOrderAndExecutionInputResponse(this);
    }
}
@
