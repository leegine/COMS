head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���N�G�X�g (WEB3AdminPMAccProductTradeStopListRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���N�G�X�g�j<BR>
 * <BR>
 * �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopListRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
	public final static String PTYPE = "admin_p_m_acc_product_trade_stop_list";
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopListRequest.class);

    /**
     * �i���X�R�[�h�ꗗ�j<BR>
     * <BR>
     * ���X�R�[�h�ꗗ<BR>
     * <BR>
     * �Ǘ��҂̕��X�R�[�h�ꗗ<BR>
     * <BR>
     * �����X�����̃`�F�b�N�Ɏg�p�B<BR>
     * <BR>
     * ------<English>----------<BR>
     * <BR>
     * branchCodeList<BR>
     * <BR>
     * branchCode list<BR>
     * <BR>
     * ��it is used for validateBranchPermission<BR>
     * <BR>
     */
    public String[] branchCodeList;

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
     * �����R�[�h<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�v���y�[�W�ԍ��j<BR>
     * <BR>
     * �v���y�[�W�ԍ�<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * �i�y�[�W���\���s���j<BR>
     * <BR>
     * �y�[�W���\���s��<BR>
     * <BR>
     * pageSize<BR>
     * <BR>
     */
    public String pageSize;

    /**
     * �i�\�[�g�L�[�j<BR>
     * <BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * sortKeys<BR>
     * <BR>
     */
    public WEB3AdminPMAccTradeStopSortKey[] sortKeys;

    /**
     * intMinValue int
     */
    private int intMinValue = 0;

    /**
     * @@roseuid 41FD92FB029F
     */
    public WEB3AdminPMAccProductTradeStopListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�ꗗ�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h�ꗗ == null�̏ꍇ<BR>�A
     * �@@�@@�@@�@@�@@�u���X�R�[�h�ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01429<BR>
     * <BR>
     * �Q�j���X�R�[�h�`�F�b�N<BR>
     * �@@this.���X�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���X�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �R�j�ڋq�R�[�h�`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * �S�j�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �T�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�T�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ <BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�\�[�g�L�[.�v�f�� == 0�������ꍇ <BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�T�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��� <BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B <BR>
     * �@@�@@�T�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �U�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�U�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�U�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�U�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �V�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�V�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�V�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�V�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCodeList check<BR>
     * �@@1-1) If this.branchCodeList == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "branchCodeList is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01429<BR>
     * <BR>
     * 2)branchCode check<BR>
     * �@@Check the followings if this.branchCode != null<BR>
     * �@@2-1) If this.branchCode meets with the following conditions,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "branchCode error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.branchCode != numerical value<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.branchCode.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 3)accountCode check<BR>
     * �@@Check the followings if this.accountCode != null<BR>
     * �@@3-1) If this.accountCode meets with the following conditions,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "accountCode error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.accountCode != numerical value<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.accountCode.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * 4)productCode check<BR>
     * �@@Check the followings if this.productCode != null<BR>
     * �@@4-1) If this.productCode meets with the following conditions,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "productCode error"<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.productCode != numerical value<BR>
     * �@@�@@�@@�@@�@@�@@�Ethis.productCode.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 5)sortKeys check<BR>
     * �@@5-1) If this.sortKeys == null <BR>
     * �@@�@@�@@�@@Throw the exception "sortKeys is null" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@5-2) If this.sortKeys.element number == 0 <BR>
     * �@@�@@�@@�@@Throw the exception "sortKeys.element number is 0" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@5-3)Check the following on all elements of this.sortKeys<BR>
     * �@@�@@5-3-1) Call sortKeys.validate() <BR>
     * <BR>
     * 6)pageIndex check<BR>
     * �@@6-1) If this.pageIndex == null <BR>
     * �@@�@@�@@�@@Throw the exception "pageIndex is null" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@6-2) If this.pageIndex is not a numerical value, <BR>
     * �@@�@@�@@�@@Throw the exception "pageIndex is not a numerical value" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@6-3)If this.pageIndex <= 0 <BR>
     * �@@�@@�@@�@@Throw the exception "pageIndex is less than 0" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * 7)pageSize check<BR>
     * �@@7-1) If this.pageSize == null <BR>
     * �@@�@@�@@�@@Throw the exception "pageSize is null" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@7-2) If this.pageSize is not a numerical value <BR>
     * �@@�@@�@@�@@Throw the exception "pageSize is not a numerical value" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@7-3) If this.pageSize <= 0 <BR>
     * �@@�@@�@@�@@Throw the exception "pageSize is less than 0" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4185F0B60169
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_accountCodeNumLength = WEB3StringTypeUtility.getByteLength(this.accountCode);
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        final int l_intThree = 3;
        final int l_intSix = 6;
        final int l_intFive = 5;

        // 1-1 if branchCodeList is null throw Exception.
        if (branchCodeList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 if branchCode is not Numeric & its length Not equal to 3, throw Exception.
		for (int i = 0; i < branchCodeList.length; i++)
		{
			if (branchCodeList[i] != null)
			{
				if ((!WEB3StringTypeUtility.isNumber(branchCodeList[i]))
					|| (WEB3StringTypeUtility.getByteLength(branchCodeList[i]) != l_intThree))
				{
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00779,
						this.getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
		}

        // 3-1 if accountCode is not Numeric & its length Not equal to 6, throw Exception.
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

        // 4-1 if productCode is not Numeric & its length Not equal to 5, throw Exception.
        if (productCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if sortKeys is null throw Exception.
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 5-2 if sortKeys length is 0, throw Exception.
            if (sortKeys.length == intMinValue)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //  5-3-1 Call sortKeys.validate()
        for (int i = intMinValue; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
        }

        // 6-1 if pageIndex is null throw Exception.
        if (pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
            // 6-2 if pageIndex is Not Numeic, throw Exception.
            if (!WEB3StringTypeUtility.isNumber(pageIndex))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
                // 6-3 if pageIndex is <= 0, throw Exception.
                if (Integer.parseInt(pageIndex) <= intMinValue)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }

        // 7-1 if pageSize is null, throw Exception.
        if (pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 7-2 if pageSize is Not Number, throw Exception
            if (!WEB3StringTypeUtility.isNumber(pageSize))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-3 if pageSize is < = 0, throw Exception.
        if (Integer.parseInt(pageSize) <= intMinValue)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopListResponse(this);
    }
}
@
