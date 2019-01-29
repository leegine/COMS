head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g(WEB3AdminAccOpenStateInquiryDetailRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇���ڍ׃��N�G�X�g<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryDetailRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryDetailRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081607L;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * @@roseuid 41B45E760280
     */
    public WEB3AdminAccOpenStateInquiryDetailRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenStateInquiryDetailResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���������̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@���ʃR�[�h�C�ڋq�R�[�h�̗����������͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01332<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C33A6008F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@���������̃`�F�b�N
        //�P�|�P�j�@@���ʃR�[�h�C�ڋq�R�[�h�̗����������͂̏ꍇ�A��O���X���[����B
        if ((this.requestNumber == null || "".equals(this.requestNumber.trim())) && 
        (this.accountCode == null || "".equals(this.accountCode.trim())))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01332,
                getClass().getName() + STR_METHOD_NAME,
                " ���ʃR�[�h�A�ڋq�R�[�h�̗��������w��ł��B" + 
                this.requestNumber + ", " + this.accountCode);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

}
@
