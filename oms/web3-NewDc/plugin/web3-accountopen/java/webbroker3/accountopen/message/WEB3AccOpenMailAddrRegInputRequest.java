head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddrRegInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^���̓��N�G�X�g(WEB3AccOpenMailAddrRegInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �����F(���u) �V�K�쐬 ���f�� 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�݃��[���A�h���X�o�^���̓��N�G�X�g)<BR>
 * �����J�݃��[���A�h���X�o�^���̓��N�G�X�g<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccOpenMailAddrRegInputRequest extends WEB3GenRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddrRegInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_mail_addr_reg_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908101112L;

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
    *
     */
    public WEB3AccOpenMailAddrRegInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenMailAddrRegInputResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �@@�P�j�@@�،���ЃR�[�h�̃`�F�b�N <BR>
     * �@@�@@�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02775<BR>
     * �@@�Q�j�@@���X�R�[�h�̃`�F�b�N <BR>
     * �@@�@@�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02174<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h�̃`�F�b�N
        //�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            log.debug("�،���ЃR�[�h��null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02775,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h��null�B");
        }

        //���X�R�[�h�̃`�F�b�N
        //�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
