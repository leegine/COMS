head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g(WEB3AdminTraderAdminIPControlUpdateCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g)<BR>
 * �Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateCompleteRequest
    extends WEB3AdminTraderAdminIPControlUpdateCommonRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlUpdateCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221831L;

    /**
     * (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * <BR>
     * 0�F �Ώ�<BR>
     * 1�F ����<BR>
     * 2�F �ΏۊO<BR>
     */
    public String status;

    /**
     * (�K�p�J�n����)<BR>
     * �K�p�J�n����<BR>
     */
    public Date startDate;

    /**
     * (�K�p�I������)<BR>
     * �K�p�I������<BR>
     */
    public Date endDate;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 48D75CD70231
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�X�e�[�^�X�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�X�e�[�^�X == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00889<BR>
     * �@@�Q�|�Q�j�@@this.�X�e�[�^�X�����p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03123<BR>
     * <BR>
     * �R�j�@@�K�p�J�n�����`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�K�p�J�n���� == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03124<BR>
     * <BR>
     * �S�j�@@�K�p�I�������`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.�K�p�I������ == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03125<BR>
     * �@@�S�|�Q�j�@@this.�K�p�I������ ��= ���ݓ����̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03126<BR>
     * <BR>
     * �T�j�@@�K�p�����`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.�K�p�J�n���� > this.�K�p�I�������̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03127<BR>
     * @@roseuid 48C8B7590077
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@super.validate()���R�[������B
        super.validate();

        //�Q�j�@@�X�e�[�^�X�`�F�b�N
        //�Q�|�P�j�@@this.�X�e�[�^�X == null�̏ꍇ�A��O���X���[����B
        if (this.status == null)
        {
            log.debug("�X�e�[�^�X�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�e�[�^�X�����w��ł��B");
        }

        //�Q�|�Q�j�@@this.�X�e�[�^�X�����p�����ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.status))
        {
            log.debug("�X�e�[�^�X�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03123,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�e�[�^�X�����p�����ȊO�̒l�ł��B");
        }

        //�R�j�@@�K�p�J�n�����`�F�b�N
        //�R�|�P�j�@@this.�K�p�J�n���� == null�̏ꍇ�A��O���X���[����
        if (this.startDate == null)
        {
            log.debug("�K�p�J�n���������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03124,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p�J�n���������w��ł��B");
        }

        //�S�j�@@�K�p�I�������`�F�b�N
        //�S�|�P�j�@@this.�K�p�I������ == null�̏ꍇ�A��O���X���[����B
        if (this.endDate == null)
        {
            log.debug("�K�p�I�����������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03125,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p�I�����������w��ł��B");
        }

        //�S�|�Q�j�@@this.�K�p�I������ ��= ���ݓ����̏ꍇ�A��O���X���[����B
        if (WEB3DateUtility.compareToSecond(this.endDate, GtlUtils.getSystemTimestamp()) <= 0)
        {
            log.debug("�K�p�I�������G���[(�K�p�I������ ��= ���ݓ���)�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03126,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p�I�������G���[(�K�p�I������ ��= ���ݓ���)�B");
        }

        //�T�j�@@�K�p�����`�F�b�N
        //�T�|�P�j�@@this.�K�p�J�n���� > this.�K�p�I�������̏ꍇ�A��O���X���[����B
        if (WEB3DateUtility.compareToSecond(this.startDate, this.endDate) > 0)
        {
            log.debug("�K�p�J�n�����͓K�p�I���������傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03127,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p�J�n�����͓K�p�I���������傫���ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlUpdateCompleteResponse(this);
    }
}
@
