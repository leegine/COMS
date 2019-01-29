head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񃁁[���A�h���X�ύX�������N�G�X�g(WEB3AccInfoMailAddressChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
Revesion History : 2007/08/28 ���g (���u) �d�l�ύX�E���f��No.217
Revesion History : 2010/02/21 ���g (���u) �d�l�ύX�E���f��No.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l��񃁁[���A�h���X�ύX�������N�G�X�g)<BR>
 * ���q�l��񃁁[���A�h���X�ύX�������N�G�X�g<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082158L;

    /**
     * (�ύX�チ�[���A�h���X)<BR>
     * �ύX�チ�[���A�h���X<BR>
     */
    public String changedMailAddress;
    
    /**
     * (���[���A�h���X�폜�t���O)<BR>
     * <BR>
     * true�F�@@�폜<BR>
     * false�F�@@�폜�łȂ�<BR>
     */
    public boolean mailAddressDelFlag;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�����A�h���X���)<BR>
     * �����A�h���X���<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo multiMailAddressInfo;

    /**
     * (�����A�h���X���X�g)<BR>
     * �����A�h���X���X�g<BR>
     */
    public WEB3AccInfoMultiMailAddressList multiMailAddressList;

    /**
     * @@roseuid 418F39F30128
     */
    public WEB3AccInfoMailAddressChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMailAddressChangeCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ύX�チ�[���A�h���X�C���[���A�h���X�폜�t���O�̃`�F�b�N<BR>
     *�@@�P�|�P�j�@@�i���[���A�h���X�폜�t���O == false�j�̏ꍇ�A<BR>
     *      �ύX�チ�[���A�h���X�������͂ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01153<BR>
     *�@@�P�|�Q�j�@@�i���[���A�h���X�폜�t���O == true�j�̏ꍇ�A<BR>
     *      �ύX�チ�[���A�h���X�ɓ��͂�����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01154<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D420201AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (!this.mailAddressDelFlag)
        {
            if (this.changedMailAddress == null || "".equals(this.changedMailAddress))
            {
                log.error("�ύX�チ�[���A�h���X�����͂̏ꍇ�A��O���X���[");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01153, getClass().getName() + STR_METHOD_NAME, "�ύX�チ�[���A�h���X������");

            }
        }
        else
        {
            if (this.changedMailAddress != null && !("".equals(this.changedMailAddress)))
            {
                log.error("�ύX�チ�[���A�h���X���͂̏ꍇ�A��O���X���[");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01154, getClass().getName() + STR_METHOD_NAME, "�ύX�チ�[���A�h���X����");

            }

        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
