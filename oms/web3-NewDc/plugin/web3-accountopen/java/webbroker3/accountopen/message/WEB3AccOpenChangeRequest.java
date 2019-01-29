head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenChangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐؑփ��N�G�X�g�iWEB3AccOpenChangeRequest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 ���g (���u) �V�K�쐬 ���f��No.164
*/
package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenUpdateItemDef;
import webbroker3.accountopen.define.WEB3AccOpenUpdateStatusDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐؑփ��N�G�X�g<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccOpenChangeRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenChangeRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_change";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908131101L;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     * <BR>
     * 1�F����ؑ�<BR>
     * 2�F��̐ؑ�<BR>
     * 3�F�폜�ؑ�<BR>
     */
    public String updateItem;

    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     * <BR>
     * 1�F�����i�폜�j/�����/��̍�<BR>
     * 0�F�L��/�����/�����<BR>
     */
    public String updateStatus;

    /**
     * (�Ǘ��Ҍ����J�ݐؑփ��N�G�X�g)<BR>
     * �Ǘ��Ҍ����J�ݐؑփ��N�G�X�g<BR>
     */
    public WEB3AccOpenChangeRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenChangeResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * �Q�j�@@�X�V���ڃ`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03169<BR>
     * �@@�Q�|�Q�j�@@���L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03170<BR>
     * �@@�@@�@@1�F����ؑ�<BR>
     * �@@�@@�@@2�F��̐ؑ�<BR>
     * �@@�@@�@@3�F�폜�ؑ�<BR>
     * <BR>
     * �R�j�@@�X�V���ԃ`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03171<BR>
     * �@@�R�|�Q�j�@@���L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03172<BR>
     * �@@�@@�@@1�F�����i�폜�j/�����/��̍�<BR>
     * �@@�@@�@@0�F�L��/�����/�����<BR>
     * @@ throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ʃR�[�h�`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.requestNumber))
        {
            log.debug("���ʃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ʃR�[�h�����w��ł��B");
        }

        //�Q�j�@@�X�V���ڃ`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.updateItem))
        {
            log.debug("�X�V���ڂ������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03169,
                this.getClass().getName() + STR_METHOD_NAME,
                "�X�V���ڂ������͂ł��B");
        }

        //�Q�|�Q�j�@@���L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //1�F����ؑ�
        //2�F��̐ؑ�
        //3�F�폜�ؑ�
        if (!(WEB3AccOpenUpdateItemDef.PRINT_CHANGE.equals(this.updateItem)
            || WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE.equals(this.updateItem)
            || WEB3AccOpenUpdateItemDef.DELETE_CHANGE.equals(this.updateItem)))
        {
            log.debug("�X�V���ڂ����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03170,
                this.getClass().getName() + STR_METHOD_NAME,
                "�X�V���ڂ����݂��Ȃ��R�[�h�l�ł��B");
        }
        //�R�j�@@�X�V���ԃ`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.updateStatus))
        {
            log.debug("�X�V���Ԃ������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03171,
                this.getClass().getName() + STR_METHOD_NAME,
                "�X�V���Ԃ������͂ł��B");
        }

        //�R�|�Q�j�@@���L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //1�F�����i�폜�j/�����/��̍�
        //0�F�L��/�����/�����
        if (!(WEB3AccOpenUpdateStatusDef.DELETE.equals(this.updateStatus)
            || WEB3AccOpenUpdateStatusDef.DEFAULT.equals(this.updateStatus)))
        {
            log.debug("�X�V���Ԃ����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03172,
                this.getClass().getName() + STR_METHOD_NAME,
                "�X�V���Ԃ����݂��Ȃ��R�[�h�l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
