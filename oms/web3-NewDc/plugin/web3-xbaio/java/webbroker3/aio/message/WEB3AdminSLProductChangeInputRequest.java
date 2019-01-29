head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�ύX���̓��N�G�X�g(WEB3AdminSLProductChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �g�E�N�|(���u) �V�K�쐬 �d�l�ύX���f��760
Revision History : 2007/10/10 ���^�](���u) �d�l�ύX���f��801 ���f��802
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۖ����o�^�ύX���̓��N�G�X�g)<BR>
 * �S�ۖ����o�^�ύX���̓��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminSLProductChangeInputRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductChangeInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141040L;

    /**
     * (�S�ۖ��������L�[)<BR>
     * �S�ۖ��������L�[<BR>
     */
    public WEB3SLProductSearchConditions searchConditions;

    /**
     * @@roseuid 46E89085002B
     */
    public WEB3AdminSLProductChangeInputRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g.�S�ۖ��������L�[��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02917<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g.�S�ۖ��������L�[.�K�p����from��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_01444<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DF671B039F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���N�G�X�g.�S�ۖ��������L�[��null�̏ꍇ�A��O���X���[
        if (this.searchConditions == null)
        {
            log.debug("�S�ۖ��������L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02917,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�S�ۖ��������L�[�����w��ł��B");
        }

        //�Q�j�@@���N�G�X�g.�S�ۖ��������L�[.�K�p����from��null�̏ꍇ�A��O���X���[
        if (this.searchConditions.targetPeriodFrom == null)
        {
            log.debug("���t�����̓G���[(�K�p����From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����̓G���[(�K�p����From)");
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
        return new WEB3AdminSLProductChangeInputResponse(this);
    }
}
@