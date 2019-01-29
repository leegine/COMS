head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest extends WEB3GenRequest
{
    
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082162L;

    /**
     * (�h�c)<BR>
     * �ϑ��萔���ύX�\���h�c<BR>
     */
    public String id;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 418F39F001F4
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�h�c�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413EC2B902F3
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
                this.getClass().getName() + STR_METHOD_NAME, "�h�c�����͂̏ꍇ");
        }
        log.exiting(STR_METHOD_NAME);

    }
}
@
