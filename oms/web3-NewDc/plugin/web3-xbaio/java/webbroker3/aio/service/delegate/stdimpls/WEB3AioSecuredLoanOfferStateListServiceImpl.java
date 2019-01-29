head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanOfferStateListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۃ��[���\���󋵃T�[�r�X�ꗗImpl(WEB3AioSecuredLoanOfferStateListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.756�C782, 785
Revision History : 2007/10/22 �đo�g (���u) �d�l�ύX�E���f��No.810, No.812
Revision History : 2008/01/10 �đo�g (���u) �d�l�ύX�E���f��No.829
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListResponse;
import webbroker3.aio.message.WEB3SLAccountOpenApplyDetailUnit;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanOfferStateListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�S�ۃ��[���\���󋵃T�[�r�X�ꗗImpl)<BR>
 * �S�ۃ��[���\���󋵃T�[�r�X�����N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSecuredLoanOfferStateListServiceImpl implements WEB3AioSecuredLoanOfferStateListService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanOfferStateListServiceImpl.class);

    /**
     * @@roseuid 46E0BE470157
     */
    public WEB3AioSecuredLoanOfferStateListServiceImpl()
    {

    }

    /**
     * �S�ۃ��[���\���󋵈ꗗ�\���������s���B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE2D950240
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminSLAccountOpenApplyListRequest)
        {
            l_response =
                this.getListScreen((WEB3AdminSLAccountOpenApplyListRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���)<BR>
     * �S�ۃ��[���\���󋵈ꗗ�\���������s���B<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}:�S�ۃ��[���\���󋵈ꗗ / �S�ۃ��[���\���󋵈ꗗ<BR>
     * get�ꗗ���<BR>
     * �ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A���X�|���X.�S�ۃ��[���\���ڋq���׈ꗗ<BR>
     * (�S�ۃ��[���\���ڋq���׈ꗗ�s[ ])��null���Z�b�g����O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02908<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����J�݃f�[�^�̍s�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩������<BR>
     * @@return WEB3AdminSLAccountOpenApplyListResponse
     * @@throws WEB3BaseException
     * @@roseuid 46CE3E2B0350
     */
    protected WEB3AdminSLAccountOpenApplyListResponse getListScreen(
        WEB3AdminSLAccountOpenApplyListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminAioStockSecuredLoanAppliAccListRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        // �@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�����J�݊Ǘ�(�،��S�ۃ��[��)
        // is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_ACCOUNT_OPEN_MANAGE, false);

        // validate���X����(���X�R�[�h : String[])
        // ���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h
        l_administrator.validateBranchPermission(l_request.branchCode);

        // validate
        l_request.validate();

        // get�،���ЃR�[�h( )
        // this.�Ǘ��ҍs.�،���ЃR�[�h��ԋp����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // �I�u�W�F�N�g�z��[16]�𐶐����A�ȉ��̒l��ݒ肷��B
        Object[] l_objValues = new Object[16];

        // [0]--�،���ЃR�[�h�Fget�،���ЃR�[�h()�̖߂�l
        l_objValues[0] = l_strInstitutionCode;

        // [1]--���X�R�[�h�F���N�G�X�g.���X�R�[�h
        l_objValues[1] = l_request.branchCode;

        // [2]--�ڋq�R�[�h�F���N�G�X�g.�ڋq�R�[�h
        l_objValues[2] = l_request.accountCode;

        // [3]--�X�g�b�N���[�������ԍ��F���N�G�X�g.�X�g�b�N���[�������ԍ�
        l_objValues[3] = l_request.stockLoanAccount;

        // [4]--�\���󋵁F���N�G�X�g.�\����
        l_objValues[4] = l_request.applyStatus;

        // [5]--�\����From�F���N�G�X�g.�\����From
        l_objValues[5] = l_request.applyDateFrom;

        // [6]--�\����To�F���N�G�X�g.�\����To
        l_objValues[6] = l_request.applyDateTo;

        // [7]--�J�ݓ�From�F���N�G�X�g.�J�ݓ�From
        l_objValues[7] = l_request.accountOpenDateFrom;

        // [8]--�J�ݓ�To�F���N�G�X�g.�J�ݓ�To
        l_objValues[8] = l_request.accountOpenDateTo;

        // [9]--�����From�F���N�G�X�g.�����From
        l_objValues[9] = l_request.executeDateFrom;

        // [10]--�����To�F���N�G�X�g.�����To
        l_objValues[10] = l_request.executeDateTo;

        // [11]--����From�F���N�G�X�g.����From
        l_objValues[11] = l_request.cancelDateFrom;

        // [12]--����To�F���N�G�X�g.����To
        l_objValues[12] = l_request.cancelDateTo;

        // [13]--����From�F���N�G�X�g.����From
        l_objValues[13] = l_request.closeDateFrom;

        // [14]--����To�F���N�G�X�g.����To
        l_objValues[14] = l_request.closeDateTo;

        // [15]--�\�[�g����[]�F���N�G�X�g.�S�ۃ��[���\�[�g�L�[
        l_objValues[15] = l_request.sortKeys;

        // get�����S�ۃ��[���ꗗ(�����S�ۃ��[���z��[])
        // �����S�ۃ��[���z��[]�F��L�Ő��������I�u�W�F�N�g�z��[16]
        WEB3AioSecuredLoanDataControlService l_aioSecuredLoanDataControlService =
            (WEB3AioSecuredLoanDataControlService)Services.getService(
                WEB3AioSecuredLoanDataControlService.class);
        StockSecuredLoanParams[] l_stockSecureLoanParams =
            l_aioSecuredLoanDataControlService.getStockSecuredLoanList(l_objValues);
        int l_intStockSecureLoanLength = l_stockSecureLoanParams.length;
        List l_lisResults = new ArrayList();
        for (int i = 0; i < l_intStockSecureLoanLength; i++)
        {
            //  �S�ۃ��[���\���ڋq���׈ꗗ�s( )
            WEB3SLAccountOpenApplyDetailUnit l_slAccountOpenDatailUnit = new WEB3SLAccountOpenApplyDetailUnit();

            // (*)�v���p�e�B�Z�b�g
            // �����X�R�[�h=�����S�ۃ��[��Params.get���X�R�[�h( )
            l_slAccountOpenDatailUnit.branchCode = l_stockSecureLoanParams[i].getBranchCode();

            // ���ڋq�R�[�h=�����S�ۃ��[��Params.get�ڋq�R�[�h( )
            l_slAccountOpenDatailUnit.accountCode = l_stockSecureLoanParams[i].getAccountCode();

            // ���X�g�b�N���[�������ԍ�=�����S�ۃ��[��Params.get�X�g�b�N���[�������ԍ�( )
            l_slAccountOpenDatailUnit.stockLoanAccount =
                l_stockSecureLoanParams[i].getStockLoanAccountCode();

            // ���\����=�����S�ۃ��[��Params.get�J�ݏ�( )
            l_slAccountOpenDatailUnit.applyStatus =
                l_stockSecureLoanParams[i].getAccountOpenStatus();

            // ���\����=�����S�ۃ��[��Params.get�\����( )
            l_slAccountOpenDatailUnit.applyDateFrom =
                WEB3DateUtility.toDay(l_stockSecureLoanParams[i].getAppliDate());

            // ���J�ݓ�=�����S�ۃ��[��Params.get�J�ݓ�( )
            l_slAccountOpenDatailUnit.accountOpenDate =
                l_stockSecureLoanParams[i].getAccountOpenDate();

            // �������=�����S�ۃ��[��Params.get����f�[�^��M��( )
            l_slAccountOpenDatailUnit.executeDate =
                WEB3DateUtility.toDay(l_stockSecureLoanParams[i].getOrderDataReceptionDate());

            // ������=�����S�ۃ��[��Params.get���f�[�^��M��( )
            l_slAccountOpenDatailUnit.cancelDate =
                WEB3DateUtility.toDay(l_stockSecureLoanParams[i].getCancelDataReceptionDate());

            // ������=�����S�ۃ��[��Params.get����( )
            l_slAccountOpenDatailUnit.closeDate = l_stockSecureLoanParams[i].getCloseDate();

            // ��Y�q���b�N=�����S�ۃ��[��Params.get�\����Y�q���( )
            l_slAccountOpenDatailUnit.yellowAccountLockDiv =
                l_stockSecureLoanParams[i].getYCustomerData();

            // ���l�����b�N=�����S�ۃ��[��Params.get�\�������b�N�q���i�l�����b�N�j( )
            l_slAccountOpenDatailUnit.examinationLockDiv =
                l_stockSecureLoanParams[i].getExaminLockFlag();

            // ���x�X���b�N=�����S�ۃ��[��Params.get�\�������b�N�q���i�x�X���b�N�j( )
            l_slAccountOpenDatailUnit.branchLockDiv =
                l_stockSecureLoanParams[i].getBranchLock();

            // ���Ǘ����b�N=�����S�ۃ��[��Params.get�\�������b�N�q���i�Ǘ����b�N�j( )
            l_slAccountOpenDatailUnit.mngLockDiv =
                l_stockSecureLoanParams[i].getMngLockFlag();

            //(��1)�Ǘ����b�N���R�̂S�Ɋւ��ẮAnull�̏ꍇ�� 0 ��������B
            // ���Ǘ����b�N���R(���֋�)=
            // �����S�ۃ��[��Params.get�\�������b�N�q���i�Ǘ����b�N���R�E���֋��j( )
            if (l_stockSecureLoanParams[i].getMngLockFlagAdvance() == null)
            {
                l_slAccountOpenDatailUnit.mngExpenseLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngExpenseLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagAdvance().intValue() + "";
            }

            // ���Ǘ����b�N���R(�ۏ؋�����)=
            // �����S�ۃ��[��Params.get�\�������b�N�q���i�Ǘ����b�N���R�E�ۏ؋������j( )
            if (l_stockSecureLoanParams[i].getMngLockFlagUnpayMargin() == null)
            {
                l_slAccountOpenDatailUnit.mngDepositLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngDepositLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagUnpayMargin().intValue() + "";
            }

            // ���Ǘ����b�N���R(�K�i�S�ەs��)=
            // �����S�ۃ��[��Params.get�\�������b�N�q���i�Ǘ����b�N���R�E�K�i�S�ەs���j( )
            if (l_stockSecureLoanParams[i].getMngLockFlagShortSecurity() == null)
            {
                l_slAccountOpenDatailUnit.mngCollateralLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngCollateralLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagShortSecurity().intValue() + "";
            }

            // ���Ǘ����b�N���R(�a��ؒ���������)=
            // �����S�ۃ��[��Params.get�\�������b�N�q���i�Ǘ����b�N���R�E�a��ؒ����������j( )
            if (l_stockSecureLoanParams[i].getMngLockFlagUnsubstitDepo() == null)
            {
                l_slAccountOpenDatailUnit.mngReceiptLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngReceiptLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagUnsubstitDepo().intValue() + "";
            }

            l_lisResults.add(l_slAccountOpenDatailUnit);
        }

        WEB3SLAccountOpenApplyDetailUnit[] l_slAccountOpenDatailUnits =
            new WEB3SLAccountOpenApplyDetailUnit[l_lisResults.size()];
        l_lisResults.toArray(l_slAccountOpenDatailUnits);

        // createResponse( )
        WEB3AdminSLAccountOpenApplyListResponse l_response =
            (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();

        // ���y�[�W���O���䁄
        // �P�j���X�|���X�̈ȉ��̍��ڂ�ݒ肷��B
        // �����X�|���X.���y�[�W�����S�ۃ��[���\���ڋq���׈ꗗ�̗v�f�������N�G�X�g.�y�[�W���\���s��0
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_slAccountOpenDatailUnits,
                l_intPageIndex,
                l_intPageSize);

        // ���X�|���X.���y�[�W��
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";

        // ���X�|���X.�����R�[�h��
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        // ���X�|���X.�\���y�[�W�ԍ�
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";

        // �Q�j�ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A���X�|���X.�S�ۃ��[���\���ڋq���׈ꗗ
        // �@@(�S�ۃ��[���\���ڋq���׈ꗗ�s[ ])��null���Z�b�g����O���X���[����B
        if (l_pageIndexInfo.getTotalPages() == 0)
        {
            l_response.accountOpenApplyDetailList = null;
            log.debug("�S�ۃ��[���\���ڋq���׈ꗗ�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02908,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�S�ۃ��[���\���ڋq���׈ꗗ�����w��ł��B");
        }

        //���X�|���X.�\���y�[�W�ԍ�
        int l_intPageIndexs = l_pageIndexInfo.getPageIndex();

        // �y�m�肵���S�ۃ��[���\���ڋq���׈ꗗ�̂����A���X�|���X�ɐݒ肷�閾�ׂ����߂�B�z
        // �P)�@@(���N�G�X�g.�y�[�W���\���s���~(���X�|���X.�\���y�[�W�ԍ�-1)�����A
        // �m�肵���S�ۃ��[���\���ڋq���׈ꗗ�̗v�f���X�L�b�v����B
        int l_intIndex = l_intPageSize * (l_intPageIndexs - 1);

        // (�S�ۃ��[���\���ڋq���׈ꗗ�̗v�f�ԍ��{���N�G�X�g.�y�[�W���\���s��)
        int l_intIndexOf = l_intIndex + l_intPageSize;
        if (l_slAccountOpenDatailUnits.length < l_intIndexOf)
        {
            l_intIndexOf = l_slAccountOpenDatailUnits.length;
        }

        // �Y������S�ۃ��[���\���ڋq���׈ꗗ���A���X�|���X�f�[�^.�S�ۃ��[���\���ڋq���׈ꗗ�s�Z�b�g����B
        List l_lisAccOpenDetailUnits = new ArrayList();
        for (int i = l_intIndex; i < l_intIndexOf; i++)
        {
            l_lisAccOpenDetailUnits.add(l_slAccountOpenDatailUnits[i]);
        }

        WEB3SLAccountOpenApplyDetailUnit[] l_slAccountOpenDatailUnitSecs =
            new WEB3SLAccountOpenApplyDetailUnit[l_lisAccOpenDetailUnits.size()];
        l_lisAccOpenDetailUnits.toArray(l_slAccountOpenDatailUnitSecs);

        // �����X�|���X.�S�ۃ��[���\���ڋq���׈ꗗ�s���y�[�W���O������s����
        // �m�肳�����S�ۃ��[���\���ڋq���׈ꗗ�̔z��
        l_response.accountOpenApplyDetailList = l_slAccountOpenDatailUnitSecs;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
