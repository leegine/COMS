head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ē����[���z�M�w������m�F�v��(WEB3AdminAccInfoMailDistributionCancelConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �d�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���ē����[���z�M�w������m�F�v��)<BR>
 * �Ǘ��҂��q�l���ē����[���z�M�w������m�F�v��<BR>
 */
public class WEB3AdminAccInfoMailDistributionCancelConfirmRequest extends WEB3GenRequest
{

    
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionCancelConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistributionCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131135L;
   
    /**
     * (ID) <BR>
     *  �ē����[���z�M�w��ID<BR>
     */
    public String id;
    
    /**
     * @@roseuid 418F39F3033C
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailDistributionCancelConfirmResponse(this);
    }

    /** ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     *<BR>
     * �P�j�h�c�̃`�F�b�N
�@@   *        �P�|�P�j�����͂̏ꍇ�A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D3E5700F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�h�c�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        //        class: WEB3BusinessLayerException
        //        tag:   BUSINESS_ERROR_00080
        if (this.id == null || "".equals(this.id))
        {        
            //��O
            log.debug("[�h�c] = " + id);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + STR_METHOD_NAME,
                "�h�c������");
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
