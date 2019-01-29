head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�ύX�m�F���N�G�X�g(WEB3AdminSLProductChangeConfirmRequest.java)
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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�S�ۖ����o�^�ύX�m�F���N�G�X�g)<BR>
 * �S�ۖ����o�^�ύX�m�F���N�G�X�g�N���X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminSLProductChangeConfirmRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductChangeConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_change_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141050L;

    /**
     * (�ύX������o�^���)<BR>
     * �S�ۖ����o�^���I�u�W�F�N�g<BR>
     */
    public WEB3SLProductInfoUnit changedStockLoanProductInfo;

    /**
     * (�S�ۖ��������L�[)<BR>
     * �S�ۖ��������L�[<BR>
     */
    public WEB3SLProductSearchConditions searchConditions;

    /**
     * @@roseuid 46E8908500C7
     */
    public WEB3AdminSLProductChangeConfirmRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���N�G�X�g.�S�ۖ��������L�[�̃`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@���N�G�X�g.�S�ۖ��������L�[��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02917<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���N�G�X�g.�S�ۖ��������L�[.�K�p����from��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01444<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g.�ύX������o�^���̃`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���N�G�X�g.�ύX������o�^���null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02923<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���N�G�X�g.�ύX������o�^���.�����R�[�h��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���N�G�X�g.�ύX������o�^���.�����R�[�h�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00815<BR>
     * <BR>
     * �@@�Q�|�S�j�@@���N�G�X�g.�ύX������o�^���.�����^�C�v��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02394<BR>
     * <BR>
     * �@@�Q�|�T�j�@@���N�G�X�g.�ύX������o�^���.��������null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01062<BR>
     * <BR>
     * �@@�Q�|�U�j�@@���N�G�X�g.�ύX������o�^���.�K�i�敪��null�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02930<BR>
     * <BR>
     * �@@�Q�|�V�j�@@���N�G�X�g.�ύX������o�^���.�|�� != null ���� ���p�����ȊO<BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02924<BR>
     * <BR>
     * �@@�Q�|�W�j�@@���N�G�X�g.�ύX������o�^���.�K�p����from��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01444<BR>
     * <BR>
     * �@@�Q�|�X�j�@@���N�G�X�g.�ύX������o�^���.�K�p����from > <BR>
     * �@@�@@�@@�@@�@@�@@�@@���N�G�X�g.�ύX������o�^���.�K�p����to�@@�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01446<BR>
     * <BR>
     * �@@�Q�|�P�O�j�@@���N�G�X�g.�ύX������o�^���.���R != null<BR>
     *              ����200Byte�ȏ�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02926<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DD0EB2038B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���N�G�X�g.�S�ۖ��������L�[�̃`�F�b�N
        //�@@�P�|�P�j�@@���N�G�X�g.�S�ۖ��������L�[��null�̏ꍇ�A��O���X���[
        if (this.searchConditions == null)
        {
            log.debug("�S�ۖ��������L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02917,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�S�ۖ��������L�[�����w��ł��B");
        }

        //�@@�P�|�Q�j�@@���N�G�X�g.�S�ۖ��������L�[.�K�p����from��null�̏ꍇ�A��O���X���[
        if (this.searchConditions.targetPeriodFrom == null)
        {
            log.debug("���t�����̓G���[(�K�p����From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����̓G���[(�K�p����From)");
        }

        //�Q�j�@@���N�G�X�g.�ύX������o�^���̃`�F�b�N
        //�@@�Q�|�P�j�@@���N�G�X�g.�ύX������o�^���null�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo == null)
        {
            log.debug("�ύX������o�^��񂪖��w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02923,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX������o�^��񂪖��w��ł��B");
        }

        //�@@�Q�|�Q�j�@@���N�G�X�g.�ύX������o�^���.�����R�[�h��null�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.productCode == null)
        {
            log.debug("�����R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B");
        }

        //�@@�Q�|�R�j�@@���N�G�X�g.�ύX������o�^���.�����R�[�h�����p�����ȊO�̏ꍇ�A
        //            ��O���X���[
        if (!WEB3StringTypeUtility.isDigit(this.changedStockLoanProductInfo.productCode))
        {
            log.debug("�����R�[�h�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�������ȊO�̒l�ł��B");
        }

        //�@@�Q�|�S�j�@@���N�G�X�g.�ύX������o�^���.�����^�C�v��null�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.productType == null)
        {
            log.debug("�����^�C�v�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02394,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�C�v�����w��ł��B");
        }

        //�@@�Q�|�T�j�@@���N�G�X�g.�ύX������o�^���.��������null�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.productName == null)
        {
            log.debug("�����������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01062,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }

        //�@@�Q�|�U�j�@@���N�G�X�g.�ύX������o�^���.�K�i�敪��null�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.qualifiedDiv == null)
        {
            log.debug("�K�i�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02930,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�i�敪�����w��ł��B");
        }

        //�@@�Q�|�V�j�@@���N�G�X�g.�ύX������o�^���.�|�� != null ���� ���p�����ȊO
        //          �@@�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.weight != null
            && !WEB3StringTypeUtility.isDigit(this.changedStockLoanProductInfo.weight))
        {
            log.debug("�|�ڂ����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02924,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�|�ڂ����p�����ȊO�̒l�ł��B");
        }

        //�@@�Q�|�W�j�@@���N�G�X�g.�ύX������o�^���.�K�p����from��null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@��O���X���[
        if (this.changedStockLoanProductInfo.targetPeriodFrom == null)
        {
            log.debug("���t�����̓G���[(�K�p����From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����̓G���[(�K�p����From)");
        }

        //�@@�Q�|�X�j�@@���N�G�X�g.�ύX������o�^���.�K�p����from >
        // �@@�@@�@@�@@�@@�@@ ���N�G�X�g.�ύX������o�^���.�K�p����to�@@�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.targetPeriodTo != null
            && WEB3DateUtility.compareToDay(this.changedStockLoanProductInfo.targetPeriodFrom,
            this.changedStockLoanProductInfo.targetPeriodTo) > 0)
        {
            log.debug("�K�p����From/To�������G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p����From/To�������G���[");
        }

        //�@@�Q�|�P�O�j�@@���N�G�X�g.�ύX������o�^���.���R != null
        // ����200Byte�ȏ�̏ꍇ�A��O���X���[
        if (this.changedStockLoanProductInfo.reason != null
            && WEB3StringTypeUtility.getByteLength(this.changedStockLoanProductInfo.reason) >= 200)
        {
            log.debug("���R�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02926,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�̃T�C�Y���s���ł��B");
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
        return new WEB3AdminSLProductChangeConfirmResponse(this);
    }
}
@
