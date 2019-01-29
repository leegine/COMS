head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ���(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ���)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ���<BR>
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadCancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082138L;
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.class);


    /**
     * (�A�b�v���[�h�h�c)<BR>
     * �A�b�v���[�h�h�c<BR>
     */
    public String uploadID;

    /**
     * @@roseuid 418F38680186
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�A�b�v���[�h�h�c�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 415BCA5301CA
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�A�b�v���[�h�h�c�̃`�F�b�N<BR>
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(this.uploadID == null || "".equals(this.uploadID))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                this.getClass().getName(),
                " �A�b�v���[�hID��null�̏ꍇ�G���[");
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
