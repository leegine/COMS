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
filename	WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�폜�m�F���N�G�X�g (WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�Ǘ��ҁE�ڋq�����ʎ����~�폜�m�F���N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�폜�m�F���N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopDeleteConfirmRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopDeleteConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_p_m_acc_product_trade_stop_delete_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static  long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class);

    /**
     * �i���X�R�[�h�j<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     */
    public String branchCode;

    /**
     * �i�ڋq�R�[�h�j<BR>
     * <BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode;

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�L������From�j<BR>
     * �L������From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * expirationStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String expirationStartDate;

    /**
     * @@roseuid 41FD9324007D
     */
    public WEB3AdminPMAccProductTradeStopDeleteConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     *<BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���X�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3<BR>
     *<BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * �R�j�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �S�j�L������From�`�F�b�N <BR>
     * �@@�S�|�P�jthis.�L������From == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�L������From��null�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01430<BR>
     * <BR>
     * �@@�S�|�Q�jthis.�L������From�����t�^�ɕϊ��ł��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�L������From�G���[(���݂��Ȃ����t)�v�̗�O��<BR>
     * �@@�@@�@@�@@�X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *  1-1) If this.branchCode == null.<BR>
     *         Throw the exception of "branchCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     *  1-2) If this.branchCode meets with the following conditions,<BR>
     *         Throw the exception "branchCode error"<BR>
     *         �Ethis.branchCode  != numerical value<BR>
     *         �Ethis.branchCode.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)accountCode check<BR>
     * Check the followings if this.accountCode  != null<BR>
     *  2-1)  If this.accountCode meets with the following conditions,<BR>
     *         Throw the exception "accountCode error"<BR>
     *         �Ethis.accountCode  != numerical value<BR>
     *         �Ethis.accountCode.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * 3)productCode check<BR>
     * Check the followings if this.productCode  != null<BR>
     *  3-1)  If this.productCode meets with the following conditions,<BR>
     *         Throw the exception "productCode error"<BR>
     *         �Ethis.productCode != numerical value<BR>
     *         �Ethis.productCode.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 4)expirationStartDate check<BR>
     * 4-1) If this.expirationStartDateFrom == null,<BR>
     * �@@�@@�@@�@@Throw the exception "expirationStartDate is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01430<BR>
     * <BR>
     *  4-2) If it is unable to change this.expirationStartDate to Date type,<BR>
     *         Throw the exception "expirationStartDate error (date doesn't exist)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * @@roseuid 4185F22C0169
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intThree = 3;
        final int l_intSix = 6;
        final int l_intFive = 5;
        int l_branchCodeNumLength = WEB3StringTypeUtility.getByteLength(branchCode);
        int l_accountCodeNumLength = WEB3StringTypeUtility.getByteLength(accountCode);
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(productCode);

        // 1-1
        if (branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 if branch Code Not a Numeric & length Not equal to 3 throw Exception
            if ((!WEB3StringTypeUtility.isNumber(branchCode))
                || (l_branchCodeNumLength != l_intThree))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if accountCode Not a Numeric & length Not equal to 6 throw Exception
        if (accountCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(accountCode))
                || (l_accountCodeNumLength != l_intSix))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if accountCode Not a Numeric & length Not equal to 6 throw Exception
        if (this.productCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if expirationStartDate is null throw Exception.
        if (expirationStartDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01430,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 4-2 if expirationStartDate cant be formated to Datetype, throw Exception
            if (!WEB3StringTypeUtility.isDateStr(expirationStartDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01431,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopDeleteConfirmResponse(this);
    }

}
@
