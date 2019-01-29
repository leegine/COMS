head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\�����ʃ��N�G�X�g(WEB3SLRepayCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�،��S�ۃ��[���ԍϐ\�����ʃ��N�G�X�g)<BR>
 * �S�ۃ��[���ԍϋ��ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayCommonRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SLRepayCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_common";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121532L;

    /**
     * (�ԍϗ\���)<BR>
     * ��ʂɂđI�����ꂽ�ԍϗ\���<BR>
     */
    public Date repayScheduledDate;

    /**
     * (�ԍϊz)<BR>
     * ��ʂɂē��͂��ꂽ�ԍϊz<BR>
     */
    public String repayAmt;

    /**
     * @@roseuid 46E89086003B
     */
    public WEB3SLRepayCommonRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�ԍϊz�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�ԍϊz�ɐ����ȊO�̕������܂܂�� or<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02909<BR>
     * ���N�G�X�g�f�[�^.�ԍϊz = null or<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02910<BR>
     * ���N�G�X�g�f�[�^.�ԍϊz <= 0 or<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02911<BR>
     * ���N�G�X�g�f�[�^.�ԍϊz.length() > 12<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02912<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j�ԍϗ\����`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�ԍϗ\��� = null<BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02913<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46CA39520149
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^.�ԍϊz = null
        if (this.repayAmt == null)
        {
            log.debug("�ԍϊz�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02910,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍϊz�����w��ł��B");
        }
        // ���N�G�X�g�f�[�^.�ԍϊz�ɐ����ȊO�̕������܂܂��
        if (!WEB3StringTypeUtility.isNumber(this.repayAmt))
        {
            log.debug("�ԍϊz�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02909,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍϊz�������ȊO�̒l�ł��B");
        }
        // ���N�G�X�g�f�[�^.�ԍϊz <= 0
        if (Double.parseDouble(this.repayAmt) <= 0)
        {
            log.debug("�ԍϊz�̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02911,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍϊz�̒l��0�ȉ��ł��B");
        }
        // ���N�G�X�g�f�[�^.�ԍϊz.length() > 12
        if (this.repayAmt.length() > 12)
        {
            log.debug("�ԍϊz�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02912,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍϊz�̃T�C�Y���s���ł��B");
        }
        // �Q�j�ԍϗ\����`�F�b�N
        // ���N�G�X�g�f�[�^.�ԍϗ\��� = null�̏ꍇ
        if (this.repayScheduledDate == null)
        {
            log.debug("�ԍϗ\��������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02913,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍϗ\��������w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse<BR>
     * @@roseuid 46BC080901A7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLRepayCommonResponse(this);
    }
}
@
