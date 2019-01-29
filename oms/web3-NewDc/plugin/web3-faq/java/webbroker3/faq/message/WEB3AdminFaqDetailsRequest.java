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
filename	WEB3AdminFaqDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��N�G�X�g(WEB3AdminFaqDetailsRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��N�G�X�g)<BR>
 * �Ǘ��Җ⍇���Ǘ����⍇���ڍ׃��N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminFaqDetailsRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqDetailsRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_details";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171302L;

    /**
     * (�⍇���R�[�h)<BR>
     * �⍇���R�[�h<BR>
     */
    public String faqCode;

    /**
     * (�@@�\�J�e�S���R�[�h)<BR>
     * �@@�\�J�e�S���R�[�h<BR>
     */
    public String transactionCategoryCode;

    /**
     * @@roseuid 41C25C090109
     */
    public WEB3AdminFaqDetailsRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFaqDetailsResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�⍇���R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01688<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41AC35E40007
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�⍇���R�[�h�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.faqCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01688, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�⍇���R�[�h�����w��ł��B");         
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
