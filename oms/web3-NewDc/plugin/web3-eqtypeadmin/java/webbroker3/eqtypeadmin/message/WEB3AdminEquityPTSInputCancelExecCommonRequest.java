head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputCancelExecCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g�iWEB3AdminEquityPTSInputCancelExecCommonRequest.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��172
 Revision History : 2008/02/02 ��іQ (���u) �d�l�ύX���f��No.195
 Revision History : 2008/02/13 �g�E�N�| (���u) �d�l�ύX���f��No.196
 */
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g�N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputCancelExecCommonRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputCancelExecCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_cancel_exec_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801221655L;

    /**
     * (����ID)<BR>
     */
    public String orderId;

    /**
     * (��芔��)<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     */
    public String execPrice;

    /**
     * (������)<BR>
     */
    public Date executionTimeStamp;


    /**
     * (�Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4795A0F8012C
     */
    public WEB3AdminEquityPTSInputCancelExecCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@��芔���̃`�F�b�N <BR>
     * �@@�P�|�P)�@@this.��芔�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02989<BR>
     * <BR>
     * �@@�P�|�Q)�@@this.��芔�������l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02990<BR>
     * <BR>
     * �@@�P�|�R)�@@this.��芔�� �� 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02991<BR>
     * <BR>
     * �@@�P�|�S)�@@this.��芔���̌��� �� 8�� �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02992<BR>
     * <BR>
     * �Q�j�@@���P���̃`�F�b�N<BR>
     * �@@�Q�|�P)�@@this.���P�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02021<BR>
     * <BR>
     * �@@�Q�|�Q)�@@this.���P�������l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02022<BR>
     * <BR>
     * �@@�Q�|�R)�@@this.���P�� �� 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02023<BR>
     * <BR>
     * �@@�Q�|�S)�@@this.���P���̌��� �� 8�� �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02993<BR>
     * <BR>
     * �R�j�@@�������̃`�F�b�N<BR>
     * �@@this.��������null�̏ꍇ�A<BR>
     * �@@�u��������null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02184<BR>
     * <BR>
     * �S�j�@@����ID�`�F�b�N<BR>
     * �@@this.����ID��null�̏ꍇ�A <BR>
     * �@@�u����ID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00600<BR>
     *
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4766445A02C0
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@��芔���̃`�F�b�N
        // �P�|�P)�@@this.��芔�� == null �̏ꍇ�A��O���X���[����B
        if (this.execQuantity == null)
        {
            log.debug("��芔�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02989,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��芔�������w��ł��B");
        }
        // �P�|�Q)�@@this.��芔�������l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.execQuantity))
        {
            log.debug("��芔�������l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02990,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��芔�������l�ȊO�̒l�ł��B");
        }
        // �P�|�R)�@@this.��芔�� �� 0 �̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.execQuantity) <= 0)
        {
            log.debug("��芔����0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02991,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��芔����0�ȉ��̒l�ł��B");
        }
        // �P�|�S)�@@this.��芔���̌��� �� 8�� �̏ꍇ�A��O���X���[����B
        if (this.execQuantity.length() > 8)
        {
            log.debug("��芔���̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02992,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��芔���̃T�C�Y���s���ł��B");
        }
        // �Q�j�@@���P���̃`�F�b�N
        // �Q�|�P)�@@this.���P�� == null �̏ꍇ�A��O���X���[����B
        if (this.execPrice == null)
        {
            log.debug("���P���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P���������͂ł��B");
        }
        // �Q�|�Q)�@@this.���P�������l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.execPrice))
        {
            log.debug("���P�������l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P�������l�ȊO�̒l�ł��B");
        }
        // �Q�|�R)�@@this.���P�� �� 0 �̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.execPrice) <= 0)
        {
            log.debug("���P����0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P����0�ȉ��̒l�ł��B");
        }
        // �Q�|�S)�@@this.���P���̌��� �� 8�� �̏ꍇ�A��O���X���[����B
        if (this.execPrice.length() > 8)
        {
            log.debug("���P���̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02993,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P���̃T�C�Y���s���ł��B");
        }
        // �R�j�@@�������̃`�F�b�N
        // this.��������null�̏ꍇ�A
        if (this.executionTimeStamp == null)
        {
            log.debug("�������������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02184,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������������͂ł��B");
        }
        // �S�j�@@����ID�`�F�b�N
        // this.����ID��null�̏ꍇ�A
        if (this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
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
        return new WEB3AdminEquityPTSInputCancelExecCommonResponse(this);
    }
}
@
