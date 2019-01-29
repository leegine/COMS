head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��o���ꗗ�T�[�r�XImpl(WEB3AdminAioListServiceImpl)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 �Ԑi (���u) �V�K�쐬 �d�l�ύX�E���f��694 701 702 704 705
Revision History : 2007/02/19 �������I (SCS) �d�l�ύX�E�����̖�� No.008
Revision History : 2009/03/11 ���u�� (���u) �d�l�ύX�E���f�� No.1118,1138,1153,1155
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AdminAioListDownloadCSV;
import webbroker3.aio.define.WEB3AIOAccountTypeDef;
import webbroker3.aio.define.WEB3AdminAioCashStatusDef;
import webbroker3.aio.define.WEB3AdminAioDivDef;
import webbroker3.aio.define.WEB3AdminAioOrderTypeDef;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListResponse;
import webbroker3.aio.message.WEB3AioAdminCashTransferListUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ғ��o���ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��ғ��o���ꗗ�T�[�r�X�����N���X<BR>
 *
 * @@author �Ԑi(���u)
 * @@version 1.0
 */
public class WEB3AdminAioListServiceImpl implements WEB3AdminAioListService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioListServiceImpl.class);

    /**
     * @@roseuid 45C425950295
     */
    public WEB3AdminAioListServiceImpl()
    {

    }

    /**
     * �Ǘ��ғ��o���ꗗ�������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B8122A0296
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAioCashTransferListRequest)
        {
            l_response =
                this.getListScreen((WEB3AdminAioCashTransferListRequest)l_request);

        }
        else if (l_request instanceof WEB3AdminAioCashTransferListInputRequest)
        {
            l_response =
                this.getInputScreen((WEB3AdminAioCashTransferListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAioCashTransferListDownloadRequest)
        {
            l_response =
                this.getDownloadFile((WEB3AdminAioCashTransferListDownloadRequest)l_request);
        }
        else
        {
           log.exiting(STR_METHOD_NAME);
           log.debug("�p�����[�^�^�C�v�s��");
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80018,
               this.getClass().getName() + "." + STR_METHOD_NAME);
         }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * ���o���ꗗ�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ғ��o���ꗗ�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���o���ꗗ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAioCashTransferListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B8122E02D5
     */
    protected WEB3AdminAioCashTransferListInputResponse getInputScreen(
        WEB3AdminAioCashTransferListInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAioCashTransferListInputRequest)";
        log.entering(STR_METHOD_NAME);

        //�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��҂̌����`�F�b�N���s���B
        //�@@�\�J�e�S���[�R�[�h = �@@�\�J�e�S���R�[�h�F�@@"B0101"
        //is�X�V = false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAioCashTransferListInputResponse l_response =
            (WEB3AdminAioCashTransferListInputResponse)l_request.createResponse();

        //���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        l_response.deliveryDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�ꗗ���)<BR>
     * ���o���ꗗ���ʉ�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ғ��o���ꗗ�jget�ꗗ��ʁv�Q��<BR>
     * ========================================================== <BR>
     * �V�[�P���X�}(�u�i�Ǘ��ғ��o���ꗗ�jget�ꗗ��ʁv) <BR>
     * �iget�ꗗ��ʁjgetListScreen <BR>
     * :1.9.1 �ڋq�}�X�^����A���R�[�h���擾�Ȃ��̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01987<BR>
     * <BR>
     * :1.13 �����P�ʃe�[�u������A���R�[�h���擾�Ȃ��̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���o���ꗗ���ʃ��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAioCashTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B8128603B0
     */
    protected WEB3AdminAioCashTransferListResponse getListScreen(
        WEB3AdminAioCashTransferListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminAioCashTransferListRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��҂̌����`�F�b�N���s���B
        //�@@�\�J�e�S���[�R�[�h = �@@�\�J�e�S���R�[�h�F�@@"B0101"
        //is�X�V = false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //�Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);

        //���X�g�̃C���X�^���X�𐶐�����B
        List l_lisBranchIds = new ArrayList();

        //�،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution = l_administrator.getInstitution();

        //���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��length
        int l_intSize = l_request.branchCode.length;

        try
        {
            //���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f���Ƃ�LOOP����
            for (int i = 0; i < l_intSize; i++)
            {
               // ���X�C���X�^���X�𐶐�����B
               //�m�����n
               //�،���ЁF get�،����()�̖߂�l
               //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h�̗v�f
               BranchImpl l_branchImpl = new BranchImpl(l_institution, l_request.branchCode[i]);

               //���XID���擾����B
               long l_lngBranchId = l_branchImpl.getBranchId();

               //���XID�����X�g�ɒǉ�����B
               //[����]
               //arg0�F ���XID
               l_lisBranchIds.add(new Long(l_lngBranchId));

            }

        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���XID�̔z����擾����B
        int l_intListSize = l_lisBranchIds.size();
        Long[] l_lonBranchIds = new Long [l_intListSize];
        l_lisBranchIds.toArray(l_lonBranchIds);
        long[] l_lngBranchIds = new long[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_lngBranchIds[i] = l_lonBranchIds[i].longValue();
        }

        MainAccount l_mainAccount = null;
        //���N�G�X�g�f�[�^.�ڋq�R�[�h != nul �̏ꍇ�A�ȉ��̏������s���B
        if (l_request.accountCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)
                l_finApp.getAccountManager();
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            //���X�R�[�h�F�@@���N�G�X�g.���X�R�[�h[0]
            String l_strBranceCode = l_request.branchCode[0];
            //�ڋq�R�[�h�F�@@���N�G�X�g.�ڋq�R�[�h
            String l_strAccountCode = l_request.accountCode;

            try
            {
                l_mainAccount =
                    l_accountManager.getMainAccount(
                        l_strInstitutionCode, l_strBranceCode, l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�ڋq�R�[�h�ɑΉ�����ڋq�͓o�^����Ă��܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        // �f�[�^��������������𐶐�����B
        //�m�����n
        // ���XID�F ���XID�̔z��
        // �ڋq�F �ڋq
        // ��n���F ���N�G�X�g�f�[�^.��n��
        // ������ʁF ���N�G�X�g�f�[�^.�������
        // �X�e�[�^�X�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X
        String l_strCondition =
            this.createQueryString(
                l_lngBranchIds,
                l_mainAccount,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        log.debug("create�������������� = " + l_strCondition);

        //���������f�[�^�R���e�i�𐶐�����B
        //�m�����n
        //���XID�F ���XID�̔z��
        //�ڋqID�F �ڋq.getAccountId()
        //��n���F ���N�G�X�g�f�[�^.��n��
        //������ʁF ���N�G�X�g�f�[�^.�������
        //�X�e�[�^�X�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X
        Object[] l_strContainer =
            this.createQueryContainer(
                l_lngBranchIds,
                l_mainAccount,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        //�N�G���v���Z�b�T���擾����B
        //�����P�ʃe�[�u������A���R�[�h���擾����B
        //�m�����n
        //arg0�F �����P��Row.TYPE
        //arg1�F create��������������()�̖߂�l
        //arg2�F "branch_id asc , received_date_time desc"
        //arg3�F null
        //arg4�F create���������f�[�^�R���e�i()�̖߂�l
        List l_lisRows = null;
        String l_strSort = "branch_id asc , received_date_time desc";
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strCondition,
                    l_strSort,
                    null,
                    l_strContainer);

            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAioCashTransferListResponse l_response =
            (WEB3AdminAioCashTransferListResponse)l_request.createResponse();

        //���X�g�̃C���X�^���X�𐶐�����B
        List l_lisDetails = new ArrayList();

        //�����P�ʃ��R�[�h�̗v�f���ALOOP�������s���B
        int l_intLength = l_lisRows.size();
        for (int i = 0; i < l_intLength; i++)
        {
            //������ʖ��̃T�}���v�Z�������s���B
            //[����]
            //�����P��Params�F �����P�ʃ��R�[�h�̗v�f
            //���X�|���X�f�[�^�F ���o���ꗗ���ʃ��X�|���X
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(i);
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_aioOrderUnitRow);
            this.calTotalAmount(l_orderUnitParams, l_response);

            //���o���ꗗ���׃I�u�W�F�N�g�𐶐�����B
            //[����]
            //�����P��Params�F �����P��Params
            WEB3AioAdminCashTransferListUnit l_tranList =
                this.createAioListDetails(l_orderUnitParams);

            //�u���o���ꗗ���ׁv�����X�g�ɒǉ�����B
            //[����]
            //Object�F ���o���ꗗ���׃I�u�W�F�N�g
            l_lisDetails.add(l_tranList);

        }

        //���o���ꗗ���ׂ̔z����擾����B
        WEB3AioAdminCashTransferListUnit[] l_TransferListUnit =
            new WEB3AioAdminCashTransferListUnit[l_lisDetails.size()];
        l_lisDetails.toArray(l_TransferListUnit);

        //�v���y�[�W�ŕ\���ΏۂƂȂ閾�ׂ��擾����B
        //�m�����n
        //���ׁF ���o���ꗗ���ׂ̔z��
        //fromIndex�F (*6)�|�P�j�̌v�Z����
        //toIndex�F (*6)�|�Q�j�̌v�Z����

        //�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        //�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_TransferListUnit, l_intPageIndex, l_intPageSize);

        WEB3AioAdminCashTransferListUnit[] l_cashTransferListUnits  = 
            (WEB3AioAdminCashTransferListUnit[])l_pageIndexInfo.getArrayReturned(WEB3AioAdminCashTransferListUnit.class);
        //�v���p�e�B�Z�b�g
        l_response.cashTransferListUnits = l_cashTransferListUnits;
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * ���o���ꗗ�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ғ��o���ꗗ�jget�_�E�����[�h�t�@@�C���v�Q��<BR>
     * ========================================================== <BR>
     * �V�[�P���X�}(�u�i�Ǘ��ғ��o���ꗗ�jget�_�E�����[�h�t�@@�C���v) <BR>
     * �iget�_�E�����[�h�t�@@�C���jgetDownloadFile <BR>
     * :1.9.1 �ڋq�}�X�^����A���R�[�h���擾�Ȃ��̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01987<BR>
     * <BR>
     * :1.13 �����P�ʃe�[�u������A���R�[�h���擾�Ȃ��̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���o���ꗗ�_�E�����[�h���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAioCashTransferListDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B813C100B8
     */
    protected WEB3AdminAioCashTransferListDownloadResponse getDownloadFile(
        WEB3AdminAioCashTransferListDownloadRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAioCashTransferListDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��҂̌����`�F�b�N���s���B
        //�@@�\�J�e�S���[�R�[�h = �@@�\�J�e�S���R�[�h�F�@@"B0101"
        //is�X�V = false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //�Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);

        //���X�g�̃C���X�^���X�𐶐�����B
        List l_lisBranchIds = new ArrayList();

        //�،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution = l_administrator.getInstitution();
        //���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��length
        int l_intSize = l_request.branchCode.length;

        try
        {
            //���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f���Ƃ�LOOP����
            for (int i = 0; i < l_intSize; i++)
            {
               // ���X�C���X�^���X�𐶐�����B
               //�m�����n
               //�،���ЁF get�،����()�̖߂�l
               //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h�̗v�f
               BranchImpl l_branchImpl = new BranchImpl(l_institution, l_request.branchCode[i]);

               //���XID���擾����B
               long l_lngBranchId = l_branchImpl.getBranchId();

               //���XID�����X�g�ɒǉ�����B
               //[����]
               //arg0�F ���XID
               l_lisBranchIds.add(new Long(l_lngBranchId));
            }
        }

        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���XID�̔z����擾����B
        int l_intListSize = l_lisBranchIds.size();
        Long[] l_lonBranchIds = new Long [l_intListSize];
        l_lisBranchIds.toArray(l_lonBranchIds);
        long[] l_lngBranchIds = new long[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_lngBranchIds[i] = l_lonBranchIds[i].longValue();
        }

        MainAccount l_mainAccountTemp = null;
        //���N�G�X�g�f�[�^.�ڋq�R�[�h != nul �̏ꍇ�A�ȉ��̏������s���B
        if (l_request.accountCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)
                l_finApp.getAccountManager();
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            //���X�R�[�h�F�@@���N�G�X�g.���X�R�[�h[0]
            String l_strBranceCode = l_request.branchCode[0];
            //�ڋq�R�[�h�F�@@���N�G�X�g.�ڋq�R�[�h
            String l_strAccountCode = l_request.accountCode;

            try
            {
                l_mainAccountTemp =
                    l_accountManager.getMainAccount(
                        l_strInstitutionCode, l_strBranceCode, l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�ڋq�R�[�h�ɑΉ�����ڋq�͓o�^����Ă��܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        // �f�[�^��������������𐶐�����B
        //�m�����n
        // ���XID�F ���XID�̔z��
        // �ڋqID�F �ڋq.getAccountId()
        // ��n���F ���N�G�X�g�f�[�^.��n��
        // ������ʁF ���N�G�X�g�f�[�^.�������
        // �X�e�[�^�X�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X
        String l_strCondition =
            this.createQueryString(
                l_lngBranchIds,
                l_mainAccountTemp,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        log.debug("create�������������� = " + l_strCondition);

        //���������f�[�^�R���e�i�𐶐�����B
        //�m�����n
        //���XID�F ���XID�̔z��
        //�ڋqID�F �ڋq.getAccountId()
        //��n���F ���N�G�X�g�f�[�^.��n��
        //������ʁF ���N�G�X�g�f�[�^.�������
        //�X�e�[�^�X�F�@@���N�G�X�g�f�[�^.�X�e�[�^�X
        Object[] l_strContainer =
            this.createQueryContainer(
                l_lngBranchIds,
                l_mainAccountTemp,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        //�N�G���v���Z�b�T���擾����B
        //�����P�ʃe�[�u������A���R�[�h���擾����B
        //�m�����n
        //arg0�F �����P��Row.TYPE
        //arg1�F create��������������()�̖߂�l
        //arg2�F "branch_id asc , received_date_time desc"
        //arg3�F null
        //arg4�F create���������f�[�^�R���e�i()�̖߂�l
        List l_lisRows = null;
        String l_strSort = "branch_id asc , received_date_time desc";
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strCondition,
                    l_strSort,
                    null,
                    l_strContainer);

            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R���X�g���N�^�B
        WEB3AdminAioListDownloadCSV l_aioListDownLoadCsv =
            new WEB3AdminAioListDownloadCSV();

        //�����P�ʃ��R�[�h�̗v�f���ALOOP�������s���B
        int l_intLength = l_lisRows.size();

        try
        {
            for (int i = 0; i < l_intLength; i++)
            {
               AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(i);
               //���׍s��ǉ�����B
               l_aioListDownLoadCsv.addRow();

               //���X�I�u�W�F�N�g���擾����B
               //�m�����n
               //���XID�F �����P��Row.get���XID()
               BranchImpl l_branchImpl = new BranchImpl(l_aioOrderUnitRow.getBranchId());

               //���X�R�[�h���擾����B
               String l_strBranchCode = l_branchImpl.getBranchCode();

               //�A�J�E���g�}�l�[�W���擾����
               FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
               AccountManager l_accMgr = l_finApp.getAccountManager();
               WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                   l_accMgr.getMainAccount(l_aioOrderUnitRow.getAccountId());
               //�ڋq�R�[�h�i�\���p�j���擾����B
               String l_strAccountCode = l_mainAccount.getDisplayAccountCode();
               //�ڋq�̖��̂��擾����
               String l_strAccountName = l_mainAccount.getDisplayAccountName();

               //������ʂ��Z�b�g����B
               //[set�������()�Ɏw�肷�����]
               //�s�ԍ��F�@@�����Ώ�index
               //������ʁF�@@�����P��Row.get�������( )
               //�����o�H�敪�F�@@�����P��Row.get�����o�H�敪( )
               //�@@.com�f�r�b�g���ώ���ԍ��F�����P��Row.get.com�f�r�b�g���ώ���ԍ�( )
               l_aioListDownLoadCsv.setOrderType(i,
                   l_aioOrderUnitRow.getOrderType().intValue() + "",
                   l_aioOrderUnitRow.getOrderRootDiv(),
                   l_aioOrderUnitRow.getComDebitNumber());

               //��n�����Z�b�g����B
               //[set��n��()�Ɏw�肷�����]
               // �s�ԍ��F�@@�����Ώ�index
               // ��n���F�@@�����P��Row.get��n��( )
               l_aioListDownLoadCsv.setDeliveryDate(i, l_aioOrderUnitRow.getDeliveryDate());

               //���X�R�[�h���Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //���X�h�c�F�@@���X.getBranchCode()�̖߂�l
               l_aioListDownLoadCsv.setBranchCode(i, l_strBranchCode);

               //�ڋq�R�[�h���Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //�����h�c�F�@@�ڋq.get�\���ڋq�R�[�h()�̖߂�l
               l_aioListDownLoadCsv.setAccountCode(i, l_strAccountCode);

               //�ڋq�����Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //�����h�c�F�@@�ڋq.get�ڋq�\����()�̖߂�l
               l_aioListDownLoadCsv.setAccountName(i, l_strAccountName);

               //�����������Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //���������F�@@�����P��Row.get�󒍓���( )
               l_aioListDownLoadCsv.setOrderDate(i, l_aioOrderUnitRow.getReceivedDateTime());

               //�X�e�[�^�X���Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //�X�e�[�^�X�F�@@�����P��Row.get�X�e�[�^�X( )
               //��������敪�F�@@�����P��Row.get��������敪( )
               l_aioListDownLoadCsv.setStatus(i,
                   l_aioOrderUnitRow.getOrderStatus().intValue() + "",
                   l_aioOrderUnitRow.getCancelType());

               //����.������ʂ�����o���𔻕ʂ��A�ԋp����B
               //�m�����n
               //�ڋq�F�@@�����P��Row.get�������( )
               String l_strAioDev = this.getAioDev(
                   String.valueOf(l_aioOrderUnitRow.getOrderType().intValue()));

               //�������z�A�o�����z���Z�b�g����B
               //[set�������z()�Ɏw�肷�����]
               //�s�ԍ��F�@@�����Ώ�index
               //���z�F�@@�����P��Row.get��������( )
               //���o���敪�F�@@get���o���敪()�̖߂�l
               l_aioListDownLoadCsv.setCash(i,
                   WEB3StringTypeUtility.formatNumber(l_aioOrderUnitRow.getQuantity()),
                   l_strAioDev);

               //���͎҂��Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //�E�����P��Row.get�����o�H�敪() == "1(�R�[���Z���^�[)"�̏ꍇ�A 
               //���͎ҁF�@@�����P��Row.get����҂h�c() 
               //�E�����P��Row.get�����o�H�敪() == "9(HOST)"�̏ꍇ�A 
               //���͎ҁF�@@�����P��Row.get���҃R�[�h() 
               //�E��L�ȊO�́A�e���ڂ�null���Z�b�g
               String l_strTrader = null;
               if (WEB3OrderRootDivDef.CALLCENTER.equals(
                   l_aioOrderUnitRow.getOrderRootDiv()))
               {
                   l_strTrader = l_aioOrderUnitRow.getTraderId() + "";
               }
               else if (WEB3OrderRootDivDef.HOST.equals(
                   l_aioOrderUnitRow.getOrderRootDiv()))
               {
                   l_strTrader = l_aioOrderUnitRow.getSonarTraderCode() + "";
               }
               l_aioListDownLoadCsv.setTrader(i,l_strTrader);

               //���͌o�H���Z�b�g����B
               //�s�ԍ��F�@@�����Ώ�index
               //���͌o�H�F�@@�����P��Row.get���͌o�H( )
               l_aioListDownLoadCsv.setInputRoot(i,
                   l_aioOrderUnitRow.getOrderRootDiv());

               String l_strBrankCode = null;
               String l_strBranchCd = null;
               String l_strAccountType = null;
               String l_strAccountNumber = null;

               List l_lisFinIns = new ArrayList();
               //�����P��Row.get������� == "1001(�o������)"�̏ꍇ�A�ȉ��̏������s���B
               if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitRow.getOrderType()))
               {
                   //������������擾�����U������Z�@@�փ��R�[�h��List�^�ŕԋp����B
                   //�m�����n
                   //�ڋq�F�@@MainAccountImpl�i�j�Ő��������ڋq�I�u�W�F�N�g
                   l_lisFinIns = this.getTransferedFinInstitutionRecord(l_mainAccount);
               }

               //(*7)�ȉ��̏����̏ꍇ�A�������s���B
               //( �����P��Row.������� == "1011(�ב֕ۏ؋��U�֒���(�a�������ב֕ۏ؋�))"
               //or �����P��Row.������� == "1012(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))" )
               //and �����P��Row.�E�v�� != null
               if ((OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                   l_aioOrderUnitRow.getOrderType())
                   || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                       l_aioOrderUnitRow.getOrderType()))
                   && l_aioOrderUnitRow.getRemarkName() != null)
               {
                   //set�������(int)
                   //[set�������()�Ɏw�肷�����]
                   //�s�ԍ��F�@@�����Ώ�index
                   l_aioListDownLoadCsv.setAccountInfo(i);
               }
               //�i*7�j�ȊO�̏ꍇ�A�ȉ��̏������s��
               else
               {
                   //�����P��Row.get�������() == "1002(��������)"���A
                   //�����P��Row.get�����o�H�敪 == "D(�����A�g)"�̏ꍇ
                   if (OrderTypeEnum.CASH_IN.equals(l_aioOrderUnitRow.getOrderType()) &&
                       WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_aioOrderUnitRow.getOrderRootDiv()))
                   {
                       String l_strDescription = l_aioOrderUnitRow.getDescription();
                       if (WEB3StringTypeUtility.isNotEmpty(l_strDescription))
                       {
                           //��s�R�[�h�F�@@�����P��Row.get�U�֋L�q()��1���ځ`4����(substring(0,4))
                           l_strBrankCode = l_strDescription.substring(0, 4);

                           //�x�X�R�[�h�F�@@�����P��Row.get�U�֋L�q()��5���ځ`7����(substring(4,7))
                           l_strBranchCd = l_strDescription.substring(4, 7);

                           //������ʁF�@@�����P��Row.get�U�֋L�q()��8����(substring(7,8))
                           l_strAccountType = l_strDescription.substring(7, 8);

                           //�����ԍ��F�@@�����P��Row.get�U�֋L�q()��9���ځ`15����(substring(8,15))
                           l_strAccountNumber = l_strDescription.substring(8, 15);
                       }
                   }

                   //�����P��Row.get�������() == "1001(�o������)"���A
                   //get�U������Z�@@��()�̖߂�l�̗v�f�� != 0 �̏ꍇ�A
                   if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitRow.getOrderType()) &&
                       l_lisFinIns.size() != 0)
                   {
                       TransferedFinInstitutionRow l_transferedFinInstitutioRow
                               = (TransferedFinInstitutionRow)l_lisFinIns.get(0);
                       //��s�R�[�h�F�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get��s�R�[�h
                       l_strBrankCode = l_transferedFinInstitutioRow.getFinInstitutionCode();

                       //�x�X�R�[�h�F�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get�x�X�R�[�h
                       l_strBranchCd = l_transferedFinInstitutioRow.getFinBranchCode();

                       //������ʁF�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get�a���敪
                       l_strAccountType = l_transferedFinInstitutioRow.getFinSaveDiv();

                       //�����ԍ��F�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get�����ԍ�
                       l_strAccountNumber = l_transferedFinInstitutioRow.getFinAccountNo();
                   }

                   l_aioListDownLoadCsv.setAccountInfo(
                       i,
                       l_strBrankCode,
                       l_strBranchCd,
                       l_strAccountType,
                       l_strAccountNumber);
               }

            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //CSV�t�@@�C���s���擾����B
        String[] l_strFileLines = l_aioListDownLoadCsv.getCsvFileLines();

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAioCashTransferListDownloadResponse l_response =
            (WEB3AdminAioCashTransferListDownloadResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
       l_response.downloadFile = l_strFileLines;
       l_response.currentDate = GtlUtils.getSystemTimestamp();

       log.exiting(STR_METHOD_NAME);
       return l_response;
    }

    /**
     * (create���o���ꗗ����)<BR>
     * ���o���ꗗ���׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ғ��o���ꗗ�jcreate���o���ꗗ���ׁv�Q��<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P��Params�I�u�W�F�N�g<BR>
     * @@return WEB3AioAdminCashTransferListUnit
     * @@throws WEB3BaseException
     * @@roseuid 45BD54910278
     */
    protected WEB3AioAdminCashTransferListUnit createAioListDetails(
        AioOrderUnitParams l_orderUnitParams)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAioListDetails(AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //�C���X�^���X�𐶐�����B
        WEB3AioAdminCashTransferListUnit l_aioAdminCashTransferListUnit =
            new WEB3AioAdminCashTransferListUnit();

        BranchImpl l_branchImpl = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        String l_strBranchCode = null;
        try
        {
            //���X�I�u�W�F�N�g���擾����B
            //�m�����n
            //���XID�F ����.�����P��Params.���XID
            l_branchImpl = new BranchImpl(l_orderUnitParams.getBranchId());

            //���X�R�[�h���擾����
            l_strBranchCode = l_branchImpl.getBranchCode();

            //�A�J�E���g�}�l�[�W���擾����
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());

        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�ڋq�R�[�h�i�\���p�j���擾����B
        String l_strAccountCode = l_mainAccount.getDisplayAccountCode();
        //�ڋq�̖��̂��擾����
        String l_strAccountName = l_mainAccount.getDisplayAccountName();

        //����.������ʂ�����o���𔻕ʂ��A�ԋp����B
        //�ڋq�F�@@����.�����P��Params.�������
        String l_strAioDev = this.getAioDev(
            String.valueOf(l_orderUnitParams.getOrderType().intValue()));
        List l_lisFinIns = new ArrayList();
        //�����P��Row.get������� == "1001(�o������)"�̏ꍇ�A�ȉ��̏������s���B
        if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitParams.getOrderType()))
        {
            //������������擾�����U������Z�@@�փ��R�[�h��List�^�ŕԋp����B
            //�m�����n
            //�ڋq�F�@@MainAccountImpl�i�j�Ő��������ڋq�I�u�W�F�N�g
            l_lisFinIns = this.getTransferedFinInstitutionRecord(l_mainAccount);

        }

        //���o���ꗗ�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        String l_strOrderType = null;
        String l_strStatus = null;
        String l_strInCash = null;
        String l_strOutCash = null;
        String l_strOperatorCode = null;
        String l_strBrankCode = null;
        String l_strBranchCd = null;
        String l_strAccountType = null;
        String l_strAccountNumber = null;
        if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()))
        {
            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitParams.getOrderRootDiv()))
            {
                l_strOrderType = WEB3AdminAioOrderTypeDef.SONAR_CASHIN;
            }
            else if (WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(
                l_orderUnitParams.getOrderRootDiv()))
            {
                l_strOrderType = WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN;
            }
            else if (l_orderUnitParams.getComDebitNumber() != null)
            {
                l_strOrderType = WEB3AdminAioOrderTypeDef.NET_CASHIN;
            }
        }
        else if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHIN_TRANSFER_DEPOSIT_TO_MARGIN;
        }
        else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHIN_FX_GUARANTY_TO_DESPOSIT;
        }
        else if (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHIN_OTHER_X_TO_ACCOUNT_BALANCE;
        }
        else if (OrderTypeEnum.CASH_OUT.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT;
        }
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN;
        }
        else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT_FX_GUARANTY_TO_DESPOSIT;
        }
        else if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE;
        }

        OrderStatusEnum l_strOrderStatus = l_orderUnitParams.getOrderStatus();
        String l_strOrderCancelDiv = l_orderUnitParams.getCancelType();
        if (OrderStatusEnum.ORDERED.equals(l_strOrderStatus) &&
            WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strStatus = WEB3AdminAioCashStatusDef.COMPLETE;
        }
        else if ((OrderStatusEnum.ACCEPTED.equals(l_strOrderStatus)  ||
                OrderStatusEnum.ORDERING.equals(l_strOrderStatus))&&
            WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strStatus = WEB3AdminAioCashStatusDef.NOT_TRANSACTION;
        }
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_strOrderStatus))
        {
            l_strStatus = WEB3AdminAioCashStatusDef.ERROR;
        }

        if (WEB3AdminAioDivDef.CASH_IN.equals(l_strAioDev))
        {
            l_strInCash = WEB3StringTypeUtility.formatNumber(l_orderUnitParams.getQuantity());
        }

        if (WEB3AdminAioDivDef.CASH_OUT.equals(l_strAioDev))
        {
            l_strOutCash = WEB3StringTypeUtility.formatNumber(l_orderUnitParams.getQuantity());
        }

        if (WEB3OrderRootDivDef.CALLCENTER.equals(l_orderUnitParams.getOrderRootDiv()))
        {
            l_strOperatorCode = l_orderUnitParams.getTraderId() + "";
        }
        else if ((WEB3OrderRootDivDef.HOST.equals(l_orderUnitParams.getOrderRootDiv())))
        {
            l_strOperatorCode = l_orderUnitParams.getSonarTraderCode();
        }

        if (WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(l_strOrderType))
        {
            String l_strDescription = l_orderUnitParams.getDescription();
            if (WEB3StringTypeUtility.isNotEmpty(l_strDescription))
            {
                //��s�R�[�h�F�@@�����P��Row.get�U�֋L�q()��1���ځ`4����(substring(0,4))
                l_strBrankCode = l_strDescription.substring(0, 4);

                //�x�X�R�[�h�F�@@�����P��Row.get�U�֋L�q()��5���ځ`7����(substring(4,7))
                l_strBranchCd = l_strDescription.substring(4, 7);

                //������ʁF�@@�����P��Row.get�U�֋L�q()��8����(substring(7,8))
                l_strAccountType = l_strDescription.substring(7, 8);

                //�����ԍ��F�@@�����P��Row.get�U�֋L�q()��9���ځ`15����(substring(8,15))
                l_strAccountNumber = l_strDescription.substring(8, 15);
            }

        }

        //�������() == "201���A
        //get�U������Z�@@��()�̖߂�l�̗v�f�� != 0 �̏ꍇ�A
        if (WEB3AdminAioOrderTypeDef.CASHOUT.equals(l_strOrderType) &&
            l_lisFinIns.size() != 0)
        {
            TransferedFinInstitutionRow l_transferedFinInstitutioRow
                = (TransferedFinInstitutionRow)l_lisFinIns.get(0);
            //��s�R�[�h�F�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get��s�R�[�h
            l_strBrankCode = l_transferedFinInstitutioRow.getFinInstitutionCode();

            //�x�X�R�[�h�F�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get�x�X�R�[�h
            l_strBranchCd = l_transferedFinInstitutioRow.getFinBranchCode();

            //������ʁF�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get�a���敪
            l_strAccountType = l_transferedFinInstitutioRow.getFinSaveDiv();

            //�����ԍ��F�@@get�U������Z�@@�փ��R�[�h�̖߂�l.get(0).get�����ԍ�
            l_strAccountNumber = l_transferedFinInstitutioRow.getFinAccountNo();
        }

        //�����P��params.������� == "1011(�ב֕ۏ؋��U�֒���(�a�������ב֕ۏ؋�))"
        //�܂��́A�����P��params.������� == "1012(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))"
        //�E����.�����P��params.�E�v�� != null
        //������ʁF�@@5:���FX
        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnitParams.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                l_orderUnitParams.getOrderType()))
        {
            if (l_orderUnitParams.getRemarkName() != null)
            {
                l_strAccountType = WEB3AIOAccountTypeDef.OSAKA_FX;
            }
        }

        l_aioAdminCashTransferListUnit.orderType = l_strOrderType;
        l_aioAdminCashTransferListUnit.deliveryDate = l_orderUnitParams.getDeliveryDate();
        l_aioAdminCashTransferListUnit.branchCode = l_strBranchCode;
        l_aioAdminCashTransferListUnit.accountCode = l_strAccountCode;
        l_aioAdminCashTransferListUnit.accountName = l_strAccountName;
        l_aioAdminCashTransferListUnit.orderDate = l_orderUnitParams.getReceivedDateTime();
        l_aioAdminCashTransferListUnit.cashinoutStatus = l_strStatus;

        l_aioAdminCashTransferListUnit.cashinAmt = l_strInCash;
        l_aioAdminCashTransferListUnit.cashoutAmt = l_strOutCash;
        l_aioAdminCashTransferListUnit.operatorCode = l_strOperatorCode;
        l_aioAdminCashTransferListUnit.orderRoutDiv = l_orderUnitParams.getOrderRootDiv();
        l_aioAdminCashTransferListUnit.financialInstitutionCode = l_strBrankCode;
        l_aioAdminCashTransferListUnit.financialBranchCode = l_strBranchCd;
        l_aioAdminCashTransferListUnit.accountTypeCode = l_strAccountType;
        l_aioAdminCashTransferListUnit.financialAccountCode = l_strAccountNumber;

        log.exiting(STR_METHOD_NAME);
        return l_aioAdminCashTransferListUnit;
    }

    /**
     * (create��������������)<BR>
     * ���N�G�X�g�f�[�^����A�f�[�^��������������𐶐�����B<BR>
     * <BR>
     * �P�j�߂�l����  <BR>
     * �@@�E�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐��B <BR>
     * <BR>
     * �Q�j���XID<BR>
     * �@@�E���X������ǉ�����B<BR>
     * <BR>
     * �@@�Q�|�P�j����.���XID�̗v�f�� = 1 �̏ꍇ<BR>
     * <BR>
     * �@@  "branch_id=?"<BR>
     * <BR>
     * �@@�Q�|�Q�j����.���XID�̗v�f�� > 1 �̏ꍇ<BR>
     * <BR>
     * �@@"branch_id in(?,?,? ... ?)"<BR>
     *   �����ʓ��́u?�v�̐����v�f��������悤�ɂ���<BR>
     * <BR>
     * �R�j����ID<BR>
     * �@@�E����.�ڋq�I�u�W�F�N�g != null �̏ꍇ�A����ID������ǉ�����B<BR>
     * <BR>
     * �@@  " and account_id=?"<BR>
     * <BR>
     * �S�j��n��<BR>
     *   �E��n��������ǉ�����B<BR>
     * <BR>
     * �@@  " and delivery_date=?"<BR>
     * <BR>
     * �T�j�������<BR>
     * �@@�E������ʎw��̏ꍇ�i����.������� != null�j�A������ʏ�����ǉ�����B<BR>
     * <BR>
     * �@@�T�|�P�j����.������� = "000(�S��)" �̏ꍇ<BR>
     * �@@�@@" and ((order_type=? and order_root_div=?) or <BR>
     * �@@�@@�@@�@@�@@ (order_type=? and order_root_div=?) or <BR>
     * �@@�@@�@@�@@�@@ (order_type=? and com_debit_number is not null) or <BR>
     * �@@�@@�@@�@@�@@ order_type in(?,?,?,?,?,?,?))"<BR>
     * <BR>
     * �@@�T�|�Q�j����.������� = "100(����_�S��)" �̏ꍇ<BR>
     * �@@  " and ((order_type=? and order_root_div=?) or <BR>
     * �@@�@@�@@�@@�@@ (order_type=? and order_root_div=?) or <BR>
     * �@@�@@�@@�@@�@@ (order_type=? and com_debit_number is not null) or <BR>
     * �@@�@@�@@�@@�@@ order_type in(?,?,?))"<BR>
     * <BR>
     * �@@�T�|�R�j����.������� = "101(SONAR����)" or "102(�o�[�`��������)" �̏ꍇ<BR>
     * �@@  " and order_type=? and order_root_div=?"<BR>
     * <BR>
     * �@@�T�|�S�j����.������� = "103(�l�b�g����)"  �̏ꍇ<BR>
     * �@@  " and order_type=? and com_debit_number is not null"<BR>
     * <BR>
     * �@@�T�|�T�j����.������� = "200(�o��_�S��)" �̏ꍇ<BR>
     * �@@  " and order_type in(?,?,?,?)"<BR>
     * <BR>
     * �@@�T�|�U�j��L�ȊO �̏ꍇ<BR>
     * �@@  " and order_type=?"<BR>
     * <BR>
     * �U�j�X�e�[�^�X<BR>
     * �@@�E����.�X�e�[�^�X != null �̏ꍇ�A�X�e�[�^�X������ǉ�����B<BR>
     * <BR>
     * �@@�U�|�P�j����.�X�e�[�^�X = "0(�S��)" �̏ꍇ<BR>
     * <BR>
     * �@@  " and ((order_status in(?,?,?) and cancel_type=?) or order_status=?")<BR>
     * <BR>
     * �@@�U�|�Q�j����.�X�e�[�^�X = "1(������)" �̏ꍇ<BR>
     * �@@  " and order_status in(?,?) and cancel_type=?"<BR>
     * <BR>
     * �@@�U�|�R�j����.�X�e�[�^�X = "2(����)" �̏ꍇ<BR>
     * �@@  " and order_status=? and cancel_type=?"<BR>
     * <BR>
     * �@@�U�|�S�j����.�X�e�[�^�X = "9(�G���[)" �̏ꍇ<BR>
     * �@@  " and order_status=?"<BR>
     * <BR>
     * �V�j�@@������C���X�^���X��ԋp<BR>
     * @@param l_lngBranchIds - (���XID)<BR>
     * ���XID�̔z��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_strOrderType - (�������)<BR>
     * �������<BR>
     * <BR>
     * @@param l_strOrderStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * <BR>
     * @@return String
     * @@roseuid 45B83D82013A
     */
    protected String createQueryString(long[] l_lngBranchIds,
        MainAccount l_mainAccount,
        Date l_datDeliveryDate,
        String l_strOrderType,
        String l_strOrderStatus)
    {
        final String STR_METHOD_NAME = "createQueryString(long[], MainAccount, Date, String, String)";
        log.entering(STR_METHOD_NAME);

        // ���N�G�X�g�f�[�^����A�f�[�^��������������𐶐�����B
        StringBuffer l_strBuffer = new StringBuffer();

        // �Q�|�P�j����.���XID�̗v�f�� = 1 �̏ꍇ
        //  " branch_id=?"
        if (l_lngBranchIds.length == 1)
        {
            l_strBuffer.append(" branch_id=?");
        }

        // �@@�Q�|�Q�j����.���XID�̗v�f�� > 1 �̏ꍇ
        //�@@" and branch_id in(?,?,? ... ?)"
        if (l_lngBranchIds.length > 1)
        {
            l_strBuffer.append(" branch_id in ( ?");
            for (int i = 1; i < l_lngBranchIds.length; i++)
            {
                l_strBuffer.append(", ? ");
            }
            l_strBuffer.append(")");
        }

        // �R�j����ID
        // �E����.�ڋq�I�u�W�F�N�g != null �̏ꍇ�A����ID������ǉ�����
        // " account_id=?"
        if (l_mainAccount != null)
        {
            l_strBuffer.append(" and account_id=?");
        }      
        //   �E��n��������ǉ�����B
        // " and delivery_date=?"
        l_strBuffer.append(" and delivery_date=?");

        // ������ʎw��̏ꍇ�i����.������� != null�j�A������ʏ�����ǉ�����B
        if (l_strOrderType != null)
        {
            // �T�|�P�j����.������� = "000(�S��)" �̏ꍇ
            // " and ((order_type=? and order_root_div=?) or
            // (order_type=? and order_root_div=?) or
            // (order_type=? and com_debit_number is not null) or
            // order_type in(?,?,?,?,?,?,?))"
            if (WEB3AdminAioOrderTypeDef.ALL.equals(l_strOrderType))
            {
                l_strBuffer.append(" and ((order_type=? and order_root_div=?) or" +
                    "  (order_type=? and order_root_div=?) or" +
                    "  (order_type=? and com_debit_number is not null) or" +
                    "  order_type in(?,?,?,?,?,?,?))");
            }
            // �T�|�Q�j����.������� = "100(����_�S��)" �̏ꍇ
            // " and ((order_type=? and order_root_div=?) or
            // (order_type=? and order_root_div=?) or
            // (order_type=? and com_debit_number is not null) or
            // order_type in(?,?,?))"
            else if (WEB3AdminAioOrderTypeDef.CASHIN_ALL.equals(l_strOrderType))
            {
                l_strBuffer.append(" and ((order_type=? and order_root_div=?) or" +
                    "  (order_type=? and order_root_div=?) or" +
                    "  (order_type=? and com_debit_number is not null) or" +
                    "  order_type in(?,?,?))");
            }
            // �T�|�R�j����.������� = "101(SONAR����)" or "102(�o�[�`��������)" �̏ꍇ
            // " and order_type=? and order_root_div=?"
            else if (WEB3AdminAioOrderTypeDef.SONAR_CASHIN.equals(l_strOrderType) ||
                WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(l_strOrderType))
            {
                l_strBuffer.append(" and order_type=? and order_root_div=?");
            }
            // �T�|�S�j����.������� = "103(�l�b�g����)"  �̏ꍇ
            // " and order_type=? and com_debit_number is not null"
            else if (WEB3AdminAioOrderTypeDef.NET_CASHIN.equals(l_strOrderType))
            {
                l_strBuffer.append(" and order_type=? and com_debit_number is not null");
            }
            // �T�|�T�j����.������� = "200(�o��_�S��)" �̏ꍇ
            // " and order_type in(?,?,?,?)"
            else if (WEB3AdminAioOrderTypeDef.CASHOUT_ALL.equals(l_strOrderType))
            {
                l_strBuffer.append(" and order_type in(?,?,?,?)");
            }
            // �T�|�U�j��L�ȊO �̏ꍇ
            // " and order_type=?"
            else
            {
                l_strBuffer.append(" and order_type=?");
            }
        }

        // �E����.�X�e�[�^�X != null �̏ꍇ�A�X�e�[�^�X������ǉ�����B
        if (l_strOrderStatus != null)
        {
            // �U�|�P�j����.�X�e�[�^�X = "0(�S��)" �̏ꍇ
            // " and ((order_status in(?,?,?) and cancel_type=?) or order_status=?")
            if (WEB3AdminAioCashStatusDef.ALL.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and ((order_status in(?,?,?) and cancel_type=?) or order_status=?)");
            }
            // �U�|�Q�j����.�X�e�[�^�X = "2(������)" �̏ꍇ
            // " and order_status in(?,?) and cancel_type=?"
            else if (WEB3AdminAioCashStatusDef.NOT_TRANSACTION.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and order_status in(?,?) and cancel_type=?");
            }
            // �U�|�R�j����.�X�e�[�^�X = "1(����)" �̏ꍇ
            // " and order_status=? and cancel_type=?"
            else if (WEB3AdminAioCashStatusDef.COMPLETE.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and order_status=? and cancel_type=?");
            }
            // �U�|�S�j����.�X�e�[�^�X = "9(�G���[)" �̏ꍇ
            // " and order_status=?"
            else if (WEB3AdminAioCashStatusDef.ERROR.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and order_status=?");
            }
        }

        // �V�j�@@������C���X�^���X��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_strBuffer.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B<BR>
     * <BR>
     * �P�j�߂�l����  <BR>
     * �@@�E�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�  <BR>
     * <BR>
     * �Q�j�u���XID�v�����ǉ� <BR>
     * �@@�E����.���XID[]���̕��XID�����ׂă��X�g�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����]�@@ <BR>
     * �@@�@@���XID<BR>
     * <BR>
     * �R�j�u����ID�v�����ǉ� <BR>
     * �@@�E�����h�c�w��̏ꍇ�i����.����ID != null�j�A����ID�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@����.�ڋq.getAccountId()<BR>
     * <BR>
     * �S�j�u��n���v�����ǉ�  <BR>
     * �@@�E��n�������X�g�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@����.��n��<BR>
     * <BR>
     * �T�j�u������ʁv�����ǉ�  <BR>
     * �@@�E������ʎw��̏ꍇ�i����.������� != null�j�A������ʂ����X�g�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �@@�T�|�P�j����.������� = "000(�S��)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.9�FHOST�A<BR>
     * �@@�@@OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.D:�����A�g�A<BR>
     * �@@�@@OrderTypeEnum.1002�F���������A<BR>
     * �@@�@@OrderTypeEnum.1008�F�U�֒���(����؋�������a���)�A<BR>
     * �@@�@@OrderTypeEnum.1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�A<BR>
     * �@@�@@OrderTypeEnum.1018�F���̑��U�֒����iX����a����j�A<BR>
     * �@@�@@OrderTypeEnum.1001�F�o�������A<BR>
     * �@@�@@OrderTypeEnum.1007�F�U�֒���(�a������犔��؋���)�A<BR>
     * �@@�@@OrderTypeEnum.1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�A<BR>
     * �@@�@@OrderTypeEnum.1017�F���̑��U�֒����i�a�������X�j<BR>
     * <BR>
     * �@@�T�|�Q�j����.������� = "100(����_�S��)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.9�FHOST�A<BR>
     * �@@�@@OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.D:�����A�g�A<BR>
     * �@@�@@OrderTypeEnum.1002�F���������A<BR>
     * �@@�@@OrderTypeEnum.1008�F�U�֒���(����؋�������a���)�A<BR>
     * �@@�@@OrderTypeEnum.1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�A<BR>
     * �@@�@@OrderTypeEnum.1018�F���̑��U�֒����iX����a����j<BR>
     * <BR>
     * �@@�T�|�R�j����.������� = "101(SONAR����)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.9�FHOST<BR>
     * <BR>
     * �@@�T�|�S�j����.������� = "102(�o�[�`��������)"  �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.D:�����A�g<BR>
     * <BR>
     * �@@�T�|�T�j����.������� = "103(�l�b�g����)"  �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1002�F��������<BR>
     * <BR>
     * �@@�T�|�U�j����.������� = "104(�U��(����؋�������a���))"  �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1008�F�U�֒���(����؋�������a���)<BR>
     * <BR>
     * �@@�T�|�V�j����.������� = "105(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))"
     * �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j<BR>
     * <BR>
     * �@@�T�|�W�j����.������� = "106(���̑��U��(X����a���))"  �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1018�F���̑��U�֒����iX����a����j<BR>
     * <BR>
     * �@@�T�|�X�j����.������� = "200(�o��_�S��)"  �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1001�F�o�������A<BR>
     * �@@�@@OrderTypeEnum.1007�F�U�֒���(�a������犔��؋���)�A<BR>
     * �@@�@@OrderTypeEnum.1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�A<BR>
     * �@@�@@OrderTypeEnum.1017�F���̑��U�֒����i�a�������X�j<BR>
     * <BR>
     * �@@�T�|�P�O�j����.������� = "201(�o��)"  �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1001�F�o������<BR>
     * <BR>
     * �@@�T�|�P�P�j����.������� = "202(�U��(�a������犔��؋���))" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1007�F�U�֒���(�a������犔��؋���)<BR>
     * <BR>
     * �@@�T�|�P�Q�j����.������� = "203(�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�))"
     * �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j<BR>
     * <BR>
     * �@@�T�|�P�R�j����.������� = "204(���̑��U��(�a�������X))" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderTypeEnum.1017�F���̑��U�֒����i�a�������X�j<BR>
     * <BR>
     * �U�j�u�X�e�[�^�X�v�����ǉ� <BR>
     * �@@�E�X�e�[�^�X�w��̏ꍇ�i����.�X�e�[�^�X != <BR>
     * null�j�A�X�e�[�^�X�����X�g�ɒǉ�����B <BR>
     * <BR>
     * �@@�U�|�P�j����.�X�e�[�^�X = "0(�S��)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@�@@OrderStatusEnum.1:��t�ρi�V�K�����j�AOrderStatusEnum.2:�������i�V�K�����j�A<BR>
     * OrderStatusEnum.3:�����ρi�V�K�����j�A0:�����l�AOrderStatusEnum.6:�������s<BR>
     * �i�V�K�����j<BR>
     * <BR>
     * �@@�U�|�Q�j����.�X�e�[�^�X = "1(������)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * <BR>
     * OrderStatusEnum.1:��t�ρi�V�K�����j�AOrderStatusEnum.2:�������i�V�K�����j�A
     * 0:�����l<BR>
     * <BR>
     * �@@�U�|�R�j����.�X�e�[�^�X = "2(����)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@  OrderStatusEnum.3:�����ρi�V�K�����j�A0:�����l<BR>
     * <BR>
     * �@@�U�|�S�j����.�X�e�[�^�X = "9(�G���[)" �̏ꍇ<BR>
     * <BR>
     * �@@�@@[add()�Ɏw�肷�����] <BR>
     * �@@  OrderStatusEnum.6:�������s�i�V�K�����j<BR>
     * <BR>
     * �V�j�z���ԋp<BR>
     * �@@�E�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * @@param l_lngBranchIds - (���XID)<BR>
     * ���XID�̔z��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_strOrderType - (�������)<BR>
     * �������<BR>
     * <BR>
     * @@param l_strOrderStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * <BR>
     * @@return Object[]
     * @@roseuid 45B83D86006F
     */
    protected Object[] createQueryContainer(
        long[] l_lngBranchIds,
        MainAccount l_mainAccount,
        Date l_datDeliveryDate,
        String l_strOrderType,
        String l_strOrderStatus)
    {
        final String STR_METHOD_NAME = "createQueryContainer(long[], MainAccount, Date, String, String)";
        log.entering(STR_METHOD_NAME);

        // �߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisValue = new ArrayList();

        // ����.���XID[]���̕��XID�����ׂă��X�g�ɒǉ�����B
        for (int i = 0; i < l_lngBranchIds.length; i++)
        {
            Long l_lngBranchId = new Long(l_lngBranchIds[i]);
            l_lisValue.add(l_lngBranchId);
        }

        // �����h�c�w��̏ꍇ�i����.����ID != null�j�A����ID�����X�g�ɒǉ�����B
        if (l_mainAccount!= null)
        {
            // [add()�Ɏw�肷�����]
            // ����.����ID
            l_lisValue.add(new Long(l_mainAccount.getAccountId()));
        }

        // ��n�������X�g�ɒǉ�����B
        l_lisValue.add(l_datDeliveryDate);

        // ������ʎw��̏ꍇ�i����.������� != null�j�A������ʏ�����ǉ�����B
        if (l_strOrderType != null)
        {
            // ����.������� = "000(�S��)" �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.ALL.equals(l_strOrderType))
            {
                // [add()�Ɏw�肷�����]
                // OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.9�FHOST�A
                // OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.D:�����A�g�A
                // OrderTypeEnum.1002�F���������A
                // OrderTypeEnum.1008�F�U�֒���(����؋�������a���)�A
                // OrderTypeEnum.1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�A
                // OrderTypeEnum.1018�F���̑��U�֒����iX����a����j�A
                // OrderTypeEnum.1001�F�o�������A
                // OrderTypeEnum.1007�F�U�֒���(�a������犔��؋���)�A
                // OrderTypeEnum.1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�A
                // OrderTypeEnum.1017�F���̑��U�֒����i�a�������X�j
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.HOST);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
                l_lisValue.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
                l_lisValue.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
                l_lisValue.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            }

            // �T�|�Q�j����.������� = "100(����_�S��)" �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHIN_ALL.equals(l_strOrderType))
            {
                // [add()�Ɏw�肷�����]
                // OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.9�FHOST�A
                // OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.D:�����A�g�A
                // OrderTypeEnum.1002�F���������A
                // OrderTypeEnum.1008�F�U�֒���(����؋�������a���)�A
                // OrderTypeEnum.1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�A
                // OrderTypeEnum.1018�F���̑��U�֒����iX����a����j
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.HOST);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
                l_lisValue.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
            }

            // �T�|�R�j����.������� = "101(SONAR����)" �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.SONAR_CASHIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.9�FHOST
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.HOST);
            }

            // �T�|�S�j����.������� = "102(�o�[�`��������)"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1002�F���������AWEB3OrderRootDivDef.D:�����A�g
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
            }

            // �T�|�T�j����.������� = "103(�l�b�g����)"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.NET_CASHIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1002�F��������
                l_lisValue.add(OrderTypeEnum.CASH_IN);
            }

            // �T�|�U�j����.������� = "104(�U��(����؋�������a���))"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHIN_TRANSFER_DEPOSIT_TO_MARGIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1008�F�U�֒���(����؋�������a���)
                l_lisValue.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            }

            // ����.������� = "105(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHIN_FX_GUARANTY_TO_DESPOSIT.equals(l_strOrderType))
            {
                // OrderTypeEnum.1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j
                l_lisValue.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            }

            // ����.������� = "106(���̑��U��(X����a���))"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHIN_OTHER_X_TO_ACCOUNT_BALANCE.equals(l_strOrderType))
            {
                // OrderTypeEnum.1018�F���̑��U�֒����iX����a����j
                l_lisValue.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
            }

            // �T�|�X�j����.������� = "200(�o��_�S��)"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHOUT_ALL.equals(l_strOrderType))
            {
                // OrderTypeEnum.1001�F�o�������A
                // OrderTypeEnum.1007�F�U�֒���(�a������犔��؋���)�A
                // OrderTypeEnum.1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�A
                // OrderTypeEnum.1017�F���̑��U�֒����i�a�������X�j
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
                l_lisValue.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
                l_lisValue.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            }

            // �T�|�P�O�j����.������� = "201(�o��)"  �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHOUT.equals(l_strOrderType))
            {
                // OrderTypeEnum.1001�F�o������
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
            }

            // �T�|�P�P�j����.������� = "202(�U��(�a������犔��؋���))" �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1007�F�U�֒���(�a������犔��؋���)
                l_lisValue.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            }

            // �T�|�P�Q�j����.������� = "203(�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�))" �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHOUT_FX_GUARANTY_TO_DESPOSIT.equals(l_strOrderType))
            {
                // OrderTypeEnum.1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j
                l_lisValue.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            }

            // �T�|�P�R�j����.������� = "204(���̑��U��(�a�������X))" �̏ꍇ
            if (WEB3AdminAioOrderTypeDef.CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE.equals(l_strOrderType))
            {
                // OrderTypeEnum.1017�F���̑��U�֒����i�a�������X�j
                l_lisValue.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            }
        }

        // �E�X�e�[�^�X�w��̏ꍇ�i����.�X�e�[�^�X != null�j�A�X�e�[�^�X�����X�g�ɒǉ�����B
        if (l_strOrderStatus != null)
        {
            // �U�|�P�j����.�X�e�[�^�X = "0(�S��)" �̏ꍇ
            if (WEB3AdminAioCashStatusDef.ALL.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.1:��t�ρi�V�K�����j�AOrderStatusEnum.2:�������i�V�K�����j�A
                // OrderStatusEnum.3:�����ρi�V�K�����j�A0:�����l�AOrderStatusEnum.6:�������s�i�V�K�����j
                l_lisValue.add(OrderStatusEnum.ACCEPTED);
                l_lisValue.add(OrderStatusEnum.ORDERING);
                l_lisValue.add(OrderStatusEnum.ORDERED);
                l_lisValue.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
                l_lisValue.add(OrderStatusEnum.NOT_ORDERED);
            }

            // �U�|�Q�j����.�X�e�[�^�X = "1(����)" �̏ꍇ
            if (WEB3AdminAioCashStatusDef.COMPLETE.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.3:�����ρi�V�K�����j�A0:�����l
                l_lisValue.add(OrderStatusEnum.ORDERED);
                l_lisValue.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            }

            // �U�|�R�j����.�X�e�[�^�X = "2(������)" �̏ꍇ
            if (WEB3AdminAioCashStatusDef.NOT_TRANSACTION.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.1:��t�ρi�V�K�����j�AOrderStatusEnum.2:�������i�V�K�����j�A0:�����l
                l_lisValue.add(OrderStatusEnum.ACCEPTED);
                l_lisValue.add(OrderStatusEnum.ORDERING);
                l_lisValue.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            }

            // �U�|�S�j����.�X�e�[�^�X = "9(�G���[)" �̏ꍇ
            if (WEB3AdminAioCashStatusDef.ERROR.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.6:�������s�i�V�K�����j
                l_lisValue.add(OrderStatusEnum.NOT_ORDERED);
            }
        }

        // �߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B
        Object[] l_objValue = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_objValue);

        log.exiting(STR_METHOD_NAME);
        return l_objValue;
    }

    /**
     * (calc���v���z)<BR>
     * ������ʖ��̃T�}���v�Z�������s���B<BR>
     * <BR>
     * �P�j�@@����:�����P��Params.������� == "1002(��������)"�@@���@@<BR>
     * �@@�@@�@@����:�����P��Params.�����o�H�敪 == "9(HOST)"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.SONAR�������v���z�Ɉ���:�����P��Params.�������ʂ����Z<BR>
     * <BR>
     * �Q�j�@@����:�����P��Params.������� == "1002(��������)"�@@����<BR>
     * �@@�@@�@@����:�����P��Params.�����o�H�敪 == "D(�����A�g)"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�o�[�`�����������v���z�Ɉ���:�����P��Params.�������ʂ����Z<BR>
     * <BR>
     * �R�j�@@����:�����P��Params.������� == "1002(��������)"�@@����<BR>
     * �@@�@@�@@����:�����P��Params.com�f�r�b�g���ώ���ԍ� is not null�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�l�b�g�������v���z�Ɉ���:�����P��Params.�������ʂ����Z<BR>
     * <BR>
     * �S�j�@@����:�����P��Params.������� =="1008(�U��(����؋�������a���))"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�U��(����؋�������a���)���v���z�Ɉ���:�����P��Params<BR>
     *       .�������ʂ����Z<BR>
     * <BR>
     * �T�j�@@����:�����P��Params.������� == "1012(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)���v���z�Ɉ���:����<BR>
     *       �P��Params.�������ʂ����Z<BR>
     * <BR>
     * �U�j�@@����:�����P��Params.������� == "1018�i���̑��U��(X����a���))"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.���̑��U��(X����a���)���v���z�Ɉ���:�����P��Params.��<BR>
     *       �����ʂ����Z<BR>
     * <BR>
     * �V�j�@@����:�����P��Params.������� == "1001(�o������)"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�o�����v���z�Ɉ���:�����P��Params.�������ʂ����Z<BR>
     * <BR>
     * �W�j�@@����:�����P��Params.������� == "1007(�U��(�a������犔��؋���))"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�U��(�a������犔��؋���)���v���z�Ɉ���:�����P��Params<BR>
     *       .�������ʂ����Z<BR>
     * <BR>
     * �X�j�@@����:�����P��Params.������� == "1011(�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�))"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)���v���z�Ɉ���:����<BR>
     *       �P��Params.�������ʂ����Z<BR>
     * <BR>
     * �P�O�j����:�����P��Params.������� == "1017(���̑��U��(�a�������X))"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�|���X�|���X�f�[�^.���̑��U��(�a�������X)���v���z�Ɉ���:�����P��Params.��<BR>
     *       �����ʂ����Z<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P��Params�I�u�W�F�N�g<BR>
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * ���X�|���X�f�[�^<BR>
     * @@return WEB3AdminAioCashTransferListResponse
     * @@roseuid 45B888CD019D
     */
    private WEB3AdminAioCashTransferListResponse calTotalAmount(
        AioOrderUnitParams l_orderUnitParams,
        WEB3AdminAioCashTransferListResponse l_response)
    {
        final String STR_METHOD_NAME = "calTotalAmount(AioOrderUnitParams, " +
            "WEB3AdminAioCashTransferListResponse)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����:�����P��Params.������� == "1002(��������)"�@@����
        //�@@����:�����P��Params.�����o�H�敪 == "9(HOST)"�̏ꍇ
        //�@@�|���X�|���X�f�[�^.SONAR�������v���z�Ɉ���:�����P��Params.�������ʂ����Z
        if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()) &&
            WEB3OrderRootDivDef.HOST.equals(l_orderUnitParams.getOrderRootDiv()))
        {
            double l_dblSonarCashinTotal = 0.0D;
            
            if(WEB3StringTypeUtility.isNotEmpty(l_response.sonarCashinTotal))
            {
                l_dblSonarCashinTotal = Double.parseDouble(l_response.sonarCashinTotal);
            }
            l_dblSonarCashinTotal = l_dblSonarCashinTotal + l_orderUnitParams.getQuantity();

            l_response.sonarCashinTotal = WEB3StringTypeUtility.formatNumber(l_dblSonarCashinTotal);
        }

        //�Q�j�@@����:�����P��Params.������� == "1002(��������)"�@@����
        //����:�����P��Params.�����o�H�敪 == "D(�����A�g)"�̏ꍇ
        //�|���X�|���X�f�[�^.�o�[�`�����������v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()) &&
            WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_orderUnitParams.getOrderRootDiv()))
        {
            double l_dblVirtualCashinTotal = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.virtualCashinTotal))
            {
                l_dblVirtualCashinTotal = Double.parseDouble(l_response.virtualCashinTotal);
            }
            l_dblVirtualCashinTotal = l_dblVirtualCashinTotal + l_orderUnitParams.getQuantity();

            l_response.virtualCashinTotal = WEB3StringTypeUtility.formatNumber(l_dblVirtualCashinTotal);
        }

        //�R�j�@@����:�����P��Params.������� == "1002(��������)"�@@����
        //�@@�@@����:�����P��Params..com�f�r�b�g���ώ���ԍ� is not null�̏ꍇ
        //�@@�@@�|���X�|���X�f�[�^.�l�b�g�������v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()) &&
            l_orderUnitParams.getComDebitNumber() != null)
        {
            double l_dblNetCashinTotal = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.netCashinTotal))
            {
                l_dblNetCashinTotal = Double.parseDouble(l_response.netCashinTotal);
            }
            l_dblNetCashinTotal = l_dblNetCashinTotal + l_orderUnitParams.getQuantity();

            l_response.netCashinTotal = WEB3StringTypeUtility.formatNumber(l_dblNetCashinTotal);
        }

        //�S�j�@@����:�����P��Params.������� == "1008(�U��(����؋�������a���))"�̏ꍇ
        // �|���X�|���X�f�[�^.�U��(����؋�������a���)���v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblTransferTotalMarginToDeposit = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.transferTotalMarginToDeposit))
            {
                l_dblTransferTotalMarginToDeposit =
                    Double.parseDouble(l_response.transferTotalMarginToDeposit);
            }

            l_dblTransferTotalMarginToDeposit = l_dblTransferTotalMarginToDeposit +
                l_orderUnitParams.getQuantity();

            l_response.transferTotalMarginToDeposit =
                WEB3StringTypeUtility.formatNumber(l_dblTransferTotalMarginToDeposit);
        }

        //�T�j�@@����:�����P��Params.������� == "1012(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))"�̏ꍇ�A
        // �|���X�|���X�f�[�^.�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)���v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblFxTotalGuarantyToDeposit = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.fxTotalGuarantyToDeposit))
            {
                l_dblFxTotalGuarantyToDeposit =
                    Double.parseDouble(l_response.fxTotalGuarantyToDeposit);
            }

            l_dblFxTotalGuarantyToDeposit = l_dblFxTotalGuarantyToDeposit +
                l_orderUnitParams.getQuantity();

            l_response.fxTotalGuarantyToDeposit =
                WEB3StringTypeUtility.formatNumber(l_dblFxTotalGuarantyToDeposit);
        }

        //�U�j�@@����:�����P��Params.������� == "1018�i���̑��U��(X����a���))"�̏ꍇ�A
        //�|���X�|���X�f�[�^.���̑��U��(X����a���)���v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblOtherTotalXToAccountBalance = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.otherTotalXToAccountBalance))
            {
                l_dblOtherTotalXToAccountBalance =
                    Double.parseDouble(l_response.otherTotalXToAccountBalance);
            }

            l_dblOtherTotalXToAccountBalance = l_dblOtherTotalXToAccountBalance +
                l_orderUnitParams.getQuantity();

            l_response.otherTotalXToAccountBalance =
                WEB3StringTypeUtility.formatNumber(l_dblOtherTotalXToAccountBalance);
        }

        //�V�j�@@����:�����P��Params.������� == "1001(�o������)"�̏ꍇ
        //�|���X�|���X�f�[�^.�o�����v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblCashoutTotal = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.cashoutTotal))
            {
                l_dblCashoutTotal =
                    Double.parseDouble(l_response.cashoutTotal);
            }

            l_dblCashoutTotal = l_dblCashoutTotal +
                l_orderUnitParams.getQuantity();

            l_response.cashoutTotal = WEB3StringTypeUtility.formatNumber(l_dblCashoutTotal);
        }

        //�W�j�@@����:�����P��Params.������� == "1007(�U��(�a������犔��؋���))"�̏ꍇ
        //�|���X�|���X�f�[�^.�U��(�a������犔��؋���)���v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblTransferTotalDepositToMargin = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.transferTotalDepositToMargin))
            {
                l_dblTransferTotalDepositToMargin =
                    Double.parseDouble(l_response.transferTotalDepositToMargin);
            }

            l_dblTransferTotalDepositToMargin = l_dblTransferTotalDepositToMargin +
                l_orderUnitParams.getQuantity();

            l_response.transferTotalDepositToMargin =
                WEB3StringTypeUtility.formatNumber(l_dblTransferTotalDepositToMargin);
        }

        //�X�j�@@����:�����P��Params.������� == "1011(�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�))"�̏ꍇ
        //�|���X�|���X�f�[�^.�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)���v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblFxTotalDepositToGuaranty = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.fxTotalDepositToGuaranty))
            {
                l_dblFxTotalDepositToGuaranty =
                    Double.parseDouble(l_response.fxTotalDepositToGuaranty);
            }

            l_dblFxTotalDepositToGuaranty = l_dblFxTotalDepositToGuaranty +
                l_orderUnitParams.getQuantity();

            l_response.fxTotalDepositToGuaranty =
                WEB3StringTypeUtility.formatNumber(l_dblFxTotalDepositToGuaranty);
        }

        //�P�O�j����:�����P��Params.������� == "1017(���̑��U��(�a�������X))"�̏ꍇ
        //�|���X�|���X�f�[�^.���̑��U��(�a�������X)���v���z�Ɉ���:�����P��Params.�������ʂ����Z
        else if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblOtherTotalAccountBalanceToX = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.otherTotalAccountBalanceToX))
            {
                l_dblOtherTotalAccountBalanceToX =
                    Double.parseDouble(l_response.otherTotalAccountBalanceToX);
            }

            l_dblOtherTotalAccountBalanceToX = l_dblOtherTotalAccountBalanceToX +
                l_orderUnitParams.getQuantity();

            l_response.otherTotalAccountBalanceToX =
                WEB3StringTypeUtility.formatNumber(l_dblOtherTotalAccountBalanceToX);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�U������Z�@@�փ��R�[�h)<BR>
     * ������������擾�����U������Z�@@�փ��R�[�h��List�^�ŕԋp����B<BR>
     * <BR>
     * �P�j�@@doFindAllQuery()���g�p���ĐU������Z�@@�փe�[�u�����ȉ��̏����Ō����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�U������Z�@@�փe�[�u��.�،���ЃR�[�h = <BR>
     *   �ڋq.getInstitution().getInstitutionCode() <BR>
     * �@@�U������Z�@@�փe�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() <BR>
     * �@@�U������Z�@@�փe�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode() <BR>
     * �@@�U������Z�@@�փe�[�u��.�w��敪 = 'A' <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 45B9B1C80313
     */
    protected List getTransferedFinInstitutionRecord(MainAccount l_mainAccount)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getTransferedFinInstitutionRecord(MainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lisFinInstitutio = null;

        String l_strCondition = "institution_code=? and branch_code=? and account_code=? and designate_div=?";

        Object[] l_objContainer = new Object[]{l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode(),
            WEB3TransferRangeDef.SALE_TURNOVER };

        try
        {
            l_lisFinInstitutio = Processors.getDefaultProcessor().doFindAllQuery(
                TransferedFinInstitutionRow.TYPE,
                l_strCondition,
                null,
                l_objContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFinInstitutio;
    }

    /**
     * (get�\������)<BR>
     * ���o���ꗗ���ׂ̔z�񂩂�v���y�[�W����<BR>
     * �\������閾�ׂ̔z����擾���ĕԋp����B<BR>
     * <BR>
     * �P�j���ArrayList�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���o���ꗗ����[����.fromIndex]����<BR>
     * �@@�@@���o���ꗗ���ׁm����.toIndex]�̗v�f���A�P�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�jArrayList����z����擾����B<BR>
     * <BR>
     * �S�j���������z���ԋp����B<BR>
     * <BR>
     * @@param l_details - (����)<BR>
     * �o���\���⍇�����ׂ̔z��<BR>
     * <BR>
     * @@param l_intFromIndex - �\���Ώۂ̊J�n�ʒu�̃C���f�b�N�X�ԍ�<BR>
     * <BR>
     * @@param l_intToIndex - �\���Ώۂ̏I���ʒu�̃C���f�b�N�X�ԍ�<BR>
     * <BR>
     * @@return WEB3AioAdminCashTransferListUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 45BD986D0284
     */
    protected WEB3AioAdminCashTransferListUnit[] getIndicationDetails(
        WEB3AioAdminCashTransferListUnit[] l_details,
        int l_intFromIndex,
        int l_intToIndex) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIndicationDetails(" +
            "WEB3AioAdminCashTransferListUnit[], l_intFromIndex, l_intToIndex)";
        log.entering(STR_METHOD_NAME);

        if (l_details == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_details.length == 0 || (l_intFromIndex > l_intToIndex) ||
            l_intToIndex >= l_details.length)
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���ArrayList�C���X�^���X�𐶐�����B
        List l_lisArrayList = new ArrayList();

        //  ���o���ꗗ����[����.fromIndex]����
        //�@@���o���ꗗ���ׁm����.toIndex]�̗v�f���A�P�j��ArrayList�ɒǉ�����B
        for (int i = l_intFromIndex; i <= l_intToIndex; i++)
        {
            l_lisArrayList.add(l_details[i]);
        }

        //ArrayList����z����擾����B
        WEB3AioAdminCashTransferListUnit[] l_l_detailArrays=
            new WEB3AioAdminCashTransferListUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_l_detailArrays);

        log.exiting(STR_METHOD_NAME);
        //���������z���ԋp����B
        return l_l_detailArrays;
    }

    /**
     * (get���o���敪)<BR>
     * ����.������ʂ�����o���𔻕ʂ��A�ԋp����B<BR>
     * <BR>
     * �P�j�@@����.������ʂ��ȉ��̏ꍇ�A"0(����)"��ԋp����B<BR>
     * �@@�@@�@@�E1002(��������)<BR>
     * �@@�@@�@@�E1008(�U�֒���(����؋�������a���))<BR>
     * �@@�@@�@@�E1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a���))<BR>
     * �@@�@@�@@�E1018(���̑��U�֒���(X����a���)<BR>
     * <BR>
     * �Q�j�@@����.������ʂ��ȉ��̏ꍇ�A"1(�o��)"��ԋp����B<BR>
     * �@@�@@�@@�E1001(�o������)<BR>
     * �@@�@@�@@�E1007(�U�֒���(�a������犔��؋���))<BR>
     * �@@�@@�@@�E1011(�ב֕ۏ؋��U�֒���(�a�������ב֕ۏ؋�))<BR>
     * �@@�@@�@@�E1017(���̑��U�֒���(�a�������X))<BR>
     * @@param l_strOrderType - (�������)<BR>
     * �������<BR>
     * <BR>
     * @@return String
     * @@roseuid 45BED17C02B2
     */
    private String getAioDev(String l_strOrderType)
    {
        String STR_METHOD_NAME = " getAioDev(String)";
        log.entering(STR_METHOD_NAME);
        
        if ((OrderTypeEnum.CASH_IN.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.FROM_OTHER_TRANSFER.intValue() + "").equals(l_strOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioDivDef.CASH_IN;
        }
        else if ((OrderTypeEnum.CASH_OUT.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.TO_OTHER_TRANSFER.intValue() + "").equals(l_strOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioDivDef.CASH_OUT;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
