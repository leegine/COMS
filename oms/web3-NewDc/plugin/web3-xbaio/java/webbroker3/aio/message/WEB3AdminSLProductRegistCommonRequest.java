head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^���ʃ��N�G�X�g(WEB3AdminSLProductRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ��іQ(���u) �V�K�쐬 ���f��No.760
Revision History : 2007/10/11 ���^�](���u) �d�l�ύX���f��No.805
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
 * (�S�ۖ����o�^���ʃ��N�G�X�g)<BR>
 * �S�ۖ����o�^���ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminSLProductRegistCommonRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductRegistCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141600L;

    /**
     * (�����o�^���)<BR>
     * �S�ۖ������o�^���I�u�W�F�N�g<BR>
     */
    public WEB3SLProductInfoUnit stockLoanProductInfo;

    /**
     * @@roseuid 46E8908402AC
     */
    public WEB3AdminSLProductRegistCommonRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@���N�G�X�g.�����o�^���.�����R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���N�G�X�g.�����o�^���.�����R�[�h�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00815<BR>
     * <BR>
     * �Q�j�@@�����^�C�v�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@���N�G�X�g.�����o�^���.�����^�C�v��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01109<BR>
     * <BR>
     * �R�j�@@�K�i�敪�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@���N�G�X�g.�����o�^���.�K�i�敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02930<BR>
     * <BR>
     * �S�j�@@�|�ڂ̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@���N�G�X�g.�����o�^���.�|�� != null ���� ���p�����ȊO<BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02924<BR>
     * <BR>
     * �T�j�@@�K�p����from�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@���N�G�X�g.�����o�^���.�K�p����from��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01444<BR>
     * <BR>
     * �U�j�@@�K�p���ԑ召�`�F�b�N<BR>
     * �@@�U�|�P�j���N�G�X�g.�����o�^���.�K�p����to != null�̏ꍇ�A<BR>�@@
     * �@@�@@�@@�@@�@@�@@���N�G�X�g.�����o�^���.�K�p����from ><BR>
     * �@@�@@�@@�@@�@@�@@���N�G�X�g.�����o�^���.�K�p����to<BR>
     * �@@�@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01446<BR>
     * <BR>
     * �V�j�@@���R�̃`�F�b�N<BR>
     * �@@�V�|�P�j�@@���N�G�X�g.�����o�^���.���R != null<BR>
     * �@@�@@�@@�@@�@@�@@����200Byte�ȏ�̏ꍇ�A��O���X���[<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02926<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D646A10119
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g.�����o�^���.�����R�[�h��null�̏ꍇ�A
        //��O���X���[
        if (this.stockLoanProductInfo.productCode == null)
        {
            log.debug("�����R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B");
        }

        //���N�G�X�g.�����o�^���.�����R�[�h�����p�����ȊO�̏ꍇ
        //��O���X���[
        if (!WEB3StringTypeUtility.isDigit(this.stockLoanProductInfo.productCode))
        {
            log.debug("�����R�[�h�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�������ȊO�̒l�ł��B");
        }

        //���N�G�X�g.�����o�^���.�����^�C�v��null�̏ꍇ�A
        //��O���X���[
        if (this.stockLoanProductInfo.productType == null)
        {
            log.debug("�����^�C�v�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01109,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�C�v�敪�����w��ł��B");
        }

        //���N�G�X�g.�����o�^���.�K�i�敪��null�̏ꍇ�A
        //��O���X���[
        if (this.stockLoanProductInfo.qualifiedDiv == null)
        {
            log.debug("�K�i�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02930,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�i�敪�����w��ł��B");
        }

        //���N�G�X�g.�����o�^���.�|�� != null ���� ���p�����ȊO
        //�̏ꍇ�A��O���X���[
        if (this.stockLoanProductInfo.weight != null
            && !WEB3StringTypeUtility.isDigit(this.stockLoanProductInfo.weight))
        {
            log.debug("�|�ڂ����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02924,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�|�ڂ����p�����ȊO�̒l�ł��B");
        }

        //���N�G�X�g.�����o�^���.�K�p����from��null�̏ꍇ�A
        //��O���X���[
        if (this.stockLoanProductInfo.targetPeriodFrom == null)
        {
            log.debug("���t�����̓G���[(�K�p����From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���t�����̓G���[(�K�p����From)");
        }

        //���N�G�X�g.�����o�^���.�K�p����to != null�̏ꍇ�A
        //���N�G�X�g.�����o�^���.�K�p����from > ���N�G�X�g.�����o�^���.�K�p����to
        //�̏ꍇ�A��O���X���[
        if (this.stockLoanProductInfo.targetPeriodTo != null
            && WEB3DateUtility.compareToDay(this.stockLoanProductInfo.targetPeriodFrom,
                this.stockLoanProductInfo.targetPeriodTo) > 0)
        {
            log.debug("�K�p����From/To�������G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�p����From/To�������G���[");
        }

        //���N�G�X�g.�����o�^���.���R != null ����200Byte�ȏ�̏ꍇ�A��O���X���[
        if (this.stockLoanProductInfo.reason != null
            && WEB3StringTypeUtility.getByteLength(this.stockLoanProductInfo.reason) >= 200)
        {
            log.debug("���R�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02926,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�̃T�C�Y���s���ł��B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
