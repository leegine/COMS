head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g(WEB3AdminAccOpenApplyDataDelCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.160
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AdminAccOpenApplyDataDelCheckFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCmpRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cmp";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812111635L;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�m�F�t���O)<BR>
     * �m�F�t���O<BR>
     * <BR>
     * 0�F���`�F�b�N<BR>
     * 1�F�`�F�b�N<BR>
     */
    public String checkFlag;

    /**
     * @@roseuid 4940F22B0158
     */
    public WEB3AdminAccOpenApplyDataDelCmpRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_00829�j��O���X���[����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_01820�j��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�m�F�t���O�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�m�F�t���O�����`�F�b�N�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_03141�j��O���X���[����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 492E6D980205
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ʃR�[�h�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A
        //�iBUSINESS_ERROR_00829�j��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            log.debug("���ʃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʃR�[�h�����w��ł��B");
        }
        //�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A
        //�iBUSINESS_ERROR_01820�j��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.requestNumber))
        {
            log.debug("���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01820,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
        }

        //�Q�j�@@�m�F�t���O�̃`�F�b�N
        //�Q�|�P�j�@@this.�m�F�t���O�����`�F�b�N�̏ꍇ�A
        //�iBUSINESS_ERROR_03141�j��O���X���[����B
        if (WEB3AdminAccOpenApplyDataDelCheckFlagDef.UNCHECK_FLAG.equals(this.checkFlag))
        {
            log.debug("�m�F�t���O�����`�F�b�N�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03141,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F�t���O�����`�F�b�N�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyDataDelCmpResponse(this);
    }
}
@
