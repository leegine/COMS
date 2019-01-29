head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�����`�[�X�V���ʃ��N�G�X�g(WEB3AdminInformProfDistVoucherUpdateCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.054
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�����`�[�X�V���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE�����`�[�X�V���ʃ��N�G�X�g
 */
public class WEB3AdminInformProfDistVoucherUpdateCommonRequest extends WEB3GenRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherUpdateCommonRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�A�����)<BR>
     * �A�����
     */
    public String informType;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;

    /**
     * @@roseuid 4663A9D60129
     */
    public WEB3AdminInformProfDistVoucherUpdateCommonRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N<BR>
     * <BR>
     * �P�j���X�R�[�h == null �̏ꍇ�A��O���X���[<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_02174<BR>
     * <BR>
     * �Q�j�A����� == null �̏ꍇ�A��O���X���[<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_01817<BR>
     * <BR>
     * �R�j���ʃR�[�h == null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@throws  WEB3BaseException
     * @@roseuid 46524B44017B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j���X�R�[�h == null �̏ꍇ�A��O���X���[
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }

        // �Q�j�A����� == null �̏ꍇ�A��O���X���[
        if (this.informType == null)
        {
            log.debug("�A����ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        // �R�j���ʃR�[�h == null�̏ꍇ�A��O���X���[
        if (this.requestNumber == null)
        {
            log.debug("���ʃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ʃR�[�h�����w��ł��B");
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
        return null;
    }
}
@
