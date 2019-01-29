head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�����������͊������N�G�X�g(WEB3AdminFeqOrderAndExecutionCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҊO�����������͊������N�G�X�g)<BR>
 * �Ǘ��ҊO�����������͊������N�G�X�g�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionCompleteRequest extends WEB3AdminFeqOrderAndExecutionCommonRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAndExecutionComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 42CE39F90177
     */
    public WEB3AdminFeqOrderAndExecutionCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�����͏��̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02072<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7E2050177
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        if (orderAndExecutionUnit == null) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02072,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����͏��null�̏ꍇ"); 
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
        return new WEB3AdminFeqOrderAndExecutionCompleteResponse(this);
    }
}
@
