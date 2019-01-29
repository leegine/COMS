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
filename	WEB3FaqConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ����⍇���m�F���N�G�X�g(WEB3FaqConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�⍇���Ǘ����⍇���m�F���N�G�X�g)<BR>
 * �⍇���Ǘ����⍇���m�F���N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqConfirmRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqConfirmRequest.class);   
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "faq_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171305L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�⍇�����)<BR>
     * �⍇�����<BR>
     */
    public WEB3FaqInfo faqInfo;

    /**
     * @@roseuid 41C25C06029F
     */
    public WEB3FaqConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FaqConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�⍇�����̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01689<BR>
     * �@@�P�|�Q�j�@@�⍇�����.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6F08902EA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�⍇�����̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        
        if (this.faqInfo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01689, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�⍇����񂪖��w��ł��B");  
        }
        
        //�P�|�Q�j�@@�⍇�����.validate()���R�[������B
        this.faqInfo.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
}
@