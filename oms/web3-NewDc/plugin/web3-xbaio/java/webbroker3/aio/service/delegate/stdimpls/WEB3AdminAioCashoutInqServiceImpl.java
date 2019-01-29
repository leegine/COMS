head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���T�[�r�X�����N���X(WEB3AdminAioCashoutInqServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ��O�� (���u) �V�K�쐬
Revesion History : 2004/10/25 �����(���u) ���r���[
Revesion History : 2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2006/06/14 ��O�� (���u) ���f��No.593
Revesion History : 2006/07/31 ����� (���u) ����̍X ���f��604
Revesion History : 2006/08/04 ����� (���u) ����̍X ���f��609
Revesion History : 2006/09/04 �Ԑi   (���u) ����̍X ���f��No.628�A643
Revesion History : 2006/10/12 �Ԑi   (���u) ����̍X ���f��No.658
Revesion History : 2007/02/07 ���G��(���u) �d�l�ύX���f�� 699
Revesion History : 2007/02/13 ���G��(���u) �d�l�ύX���f�� 707
Revesion History : 2010/02/02 ���g (���u)���f��No.1262
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AdminAioCashoutInqDownloadCsv;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.define.WEB3AioCancelDivDef;
import webbroker3.aio.define.WEB3AioCurrencyCodeDef;
import webbroker3.aio.define.WEB3AioInputDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.aio.define.WEB3AioTransferDivDef;
import webbroker3.aio.define.WEB3OrderCancelPossibleDef;
import webbroker3.aio.define.WEB3OrderSendPossibleDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqListRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqListResponse;
import webbroker3.aio.message.WEB3AioBranchCodeComparator;
import webbroker3.aio.message.WEB3AioCashoutInqUnit;
import webbroker3.aio.message.WEB3AioOrderDateComparator;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqService;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3DesignateDivDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o���\���⍇���T�[�r�XImpl)<BR>
 * �o���\���⍇���T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashoutInqServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminAioCashoutInqService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqServiceImpl.class);

    /**
     * �o���\���⍇���T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Acreate�ꗗ()�Avalidate���M()�A<BR>
     * submit���M()�Avalidate���()�Asubmit���()�A<BR>
     * get�_�E�����[�h�t�@@�C��()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410105F603C8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_request instanceof WEB3AdminAioCashoutInqListRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�o���\���⍇���ꗗ���N�G�X�g�v�̏ꍇ
            WEB3AdminAioCashoutInqListResponse l_listResponse = 
                this.createList((WEB3AdminAioCashoutInqListRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_listResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�o���\���⍇���o���m�F���N�G�X�g�v�̏ꍇ
            WEB3AdminAioCashoutInqConfirmResponse l_comfirmResponse = 
                this.validateSend((WEB3AdminAioCashoutInqConfirmRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_comfirmResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�o���\���⍇���o���������N�G�X�g�v�̏ꍇ
            WEB3AdminAioCashoutInqCompleteResponse l_completeResponse = 
                this.submitSend((WEB3AdminAioCashoutInqCompleteRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqCancelConfirmRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�o���\���⍇������m�F���N�G�X�g�v�̏ꍇ
            WEB3AdminAioCashoutInqCancelConfirmResponse l_cancelConfirmResponse = 
                this.validateCancel((WEB3AdminAioCashoutInqCancelConfirmRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_cancelConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqCancelCompleteRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�o���\���⍇������������N�G�X�g�v�̏ꍇ
            WEB3AdminAioCashoutInqCancelCompleteResponse l_cancelConfirmResponse = 
                this.submitCancel((WEB3AdminAioCashoutInqCancelCompleteRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_cancelConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqDownloadRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u�o���\���⍇���_�E�����[�h���N�G�X�g�v�̏ꍇ
        	WEB3AdminAioCashoutInqDownloadResponse l_cashoutInqDownloadResponse = 
                this.getDownloadFile((WEB3AdminAioCashoutInqDownloadRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_cashoutInqDownloadResponse;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (create�ꗗ)<BR>
     * �o���\���⍇���ꗗ��ʕ\���f�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���j�ꗗ��ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129CF150026
     */
    protected WEB3AdminAioCashoutInqListResponse createList(
        WEB3AdminAioCashoutInqListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createList(" + 
            "WEB3AdminAioCashoutInqListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //1.2 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 �Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B
        //validate����(String, boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hB0101�h
        //is�X�V�F false
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //1.4 �Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B
        //validate���X����(String[])
        //[����]
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_web3Administrator.validateBranchPermission(l_request.branchCode);

        AccountManager l_accountManager = GtlUtils.getAccountManager();
        Branch l_branch = null;
        long l_lngBranchId = 0L;

        //1.5 ���X�g�̃C���X�^���X�𐶐�����B
        long[] l_lngBranchIds = 
            new long[l_request.branchCode.length];

        //1.6 �،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution = l_web3Administrator.getInstitution();

        //1.7 (*1) ���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f����Loop����
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            //1.7.1 ���X�C���X�^���X�𐶐�����B
            //�m�����n
            //�،���ЁF get�،����()�̖߂�l
            //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
            try
            {
                l_branch = l_accountManager.getBranch(
                    l_institution, l_request.branchCode[i]);
            }
            catch (NotFoundException l_ex)
            {
                log.error("���X�C���X�^���X�𐶐�����:", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.7.2 ���XID���擾����B
            l_lngBranchId = l_branch.getBranchId();

            //1.7.3 ���XID�����X�g�ɒǉ�����B
            //[����]
            //arg0�F ���XID
            l_lngBranchIds[i] = l_lngBranchId;
        }

        //1.9 �f�[�^�擾����������𐶐�����B
        //�m�����n
        //���XID�F ���XID�̔z��
        //��n���F ���N�G�X�g�f�[�^.��n��
        //������t�敪�F ���N�G�X�g�f�[�^.������t�敪
        //���͋敪:  ���N�G�X�g�f�[�^.���͋敪
        //�ʉ݃R�[�h�F�@@���N�G�X�g�f�[�^.�ʉ݃R�[�h

        String l_strWhereClause = null;
        l_strWhereClause = this.createGetCondCharacterString(
            l_lngBranchIds, 
            l_request.deliveryDate, 
            l_request.orderDiv, 
            l_request.inputDiv, 
            l_request.currencyCode);

        //1.10 �擾�����f�[�^�R���e�i�𐶐�����B
        //�m�����n
        //���XID�F ���XID�̔z��
        //��n���F ���N�G�X�g�f�[�^.��n��
        //������t�敪�F ���N�G�X�g�f�[�^.������t�敪
        //���͋敪:  ���N�G�X�g�f�[�^.���͋敪
        //�ʉ݃R�[�h�F�@@���N�G�X�g�f�[�^.�ʉ݃R�[�h

        Object l_bindVars[] = this.createGetCondDataContainer(
            l_lngBranchIds, 
            l_request.deliveryDate, 
            l_request.orderDiv, 
            l_request.inputDiv, 
            l_request.currencyCode);

        //1.11  1.12 �����P�ʃe�[�u������A���R�[�h���擾����B
        //�m�����n
        // arg0�F �����P��Row.TYPE
        // arg1�F create�擾����������()�̖߂�l
        // arg2�F "branch_id asc , received_date_time asc"
        // arg3�F null
        // arg4�F create�擾�����f�[�^�R���e�i()�̖߂�l
        List l_lisRows = null;
        String l_strSort = " branch_id asc , received_date_time asc";
        try
        {
            l_lisRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, 
                    l_strWhereClause, 
                    l_strSort, 
                    null, 
                    l_bindVars);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.13 ArrayList�C���X�^���X�𐶐�����B
        List l_lisAioCashoutInqUnit = null;
        l_lisAioCashoutInqUnit = new ArrayList();

        log.debug("search �����P��.size : = " + l_lisRows.size());

        //1.14 �擾�����v�f����Loop����
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_lisRows.get(i);

            //1.14.1 �o���\���⍇�����׃I�u�W�F�N�g�𐶐�����B
            //[����]
            //�����P��Params�F �����P��Params

            WEB3AioCashoutInqUnit l_aioCashoutInqUnit = this.createCashoutInqUnit(l_aioOrderUnitRow);

            //=========remain zhou-yong NO.1 begin ========

            //(*3) ���N�G�X�g�f�[�^.�U����敪 != 0�i�S�āj �̏ꍇ
            //
            //�P�j�ȉ��̏����ŁA�U������Z�@@�փe�[�u�����烌�R�[�h���擾����B
            //   [����]
            //   ���X�R�[�h�F ���X�i�����P��Params.���XID�j.getBranchCode()�̖߂�l
            //   �،���ЃR�[�h�F ���X.getInstitution().getInstitutionCode()�̖߂�l
            //   �ڋq�R�[�h�F �����i�����P��Params.����ID�j.getAccountCode()�̖߂�l
            //   �w��敪: 'A'
            if (!WEB3AioTransferDivDef.ALL.equals(l_request.transferDiv))
            {
                Branch l_unitBranch = null;
                MainAccount l_unitMainAccount = null;
                TransferedFinInstitutionRow l_transferedFinInstitutioRow = null;

                try
                {
                    l_unitBranch = l_accountManager.getBranch(
                        l_aioOrderUnitRow.getBranchId());
                    l_unitMainAccount = l_accountManager.getMainAccount(
                        l_aioOrderUnitRow.getAccountId());

                    l_transferedFinInstitutioRow = 
                        TransferedFinInstitutionDao.findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv(
                        l_unitBranch.getInstitution().getInstitutionCode(), 
                        l_unitBranch.getBranchCode(), 
                        l_unitMainAccount.getAccountCode(), 
                        WEB3TransferRangeDef.SALE_TURNOVER);

                }
                catch (NotFoundException l_ex)
                {
                    log.error("__NotFoundException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�Q�j�ȉ��̏����𖞂����ꍇ�AList�֗v�f��ǉ�����B
                //    �����N�G�X�g�f�[�^�D�U����敪 = 1�i�X���j�̏ꍇ�A�U������Z�@@��Params.�U���敪 = 5�i�X�֐U���j
                //    �����N�G�X�g�f�[�^�D�U����敪 = 2�i���̑��j�̏ꍇ�A�U������Z�@@��Params.�U���敪 != 5�i�X�֐U���j

                if(WEB3AioTransferDivDef.POSTAL_SAVINGS.equals(l_request.transferDiv))
                {
                    if(l_transferedFinInstitutioRow != null 
                        && WEB3FinTransferDivDef.POST_TRANSFER.equals(l_transferedFinInstitutioRow.getTransferDiv()))
                    {
                        l_lisAioCashoutInqUnit.add(l_aioCashoutInqUnit);
                    }

                }
                if(WEB3AioTransferDivDef.OTHERS.equals(l_request.transferDiv))
                {
                    if(l_transferedFinInstitutioRow != null
                        && !WEB3FinTransferDivDef.POST_TRANSFER.equals(l_transferedFinInstitutioRow.getTransferDiv()))
                    {
                        l_lisAioCashoutInqUnit.add(l_aioCashoutInqUnit);
                    }
                }

            }
            else
            {
                l_lisAioCashoutInqUnit.add(l_aioCashoutInqUnit);
            }
            //=========remain zhou-yong NO.1 end ========

        } //end for

        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnits = 
            new WEB3AioCashoutInqUnit[l_lisAioCashoutInqUnit.size()];

        //1.15 �o���\���⍇�����ׂ̔z����擾����B
        l_lisAioCashoutInqUnit.toArray(l_aioCashoutInqUnits);

        //=========remain zhou-yong NO.2 begin ============
        //WEB3PageIndexInfo
        //[�R���X�g���N�^�Ɏw�肷�����]
        // l_objs �F toArray()�����l
        // l_intRequestPageIndex �F ���N�G�X�g�f�[�^.�v���y�[�W���\���s��
        // l_intRequestPageSize �F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ�

        //�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);

        //�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_aioCashoutInqUnits,
                l_intPageIndex,
                l_intPageSize);

        //getArrayReturned
        //�w�肵���s�����A���R�[�h��ԋp����B
        //[����]
        //l_classType �F �o���⍇�����ז��׃N���X.class
        WEB3AioCashoutInqUnit[] l_arrayReturneds =
            (WEB3AioCashoutInqUnit[])l_pageIndexInfo.getArrayReturned(WEB3AioCashoutInqUnit.class);

        //set�o���]��
        //�o���]�͂��擾���A�Z�b�g����B
        //�m�����n
        //�،���ЃR�[�h �F get�،����()�̖߂�l
        //�o���⍇������ �F getArrayReturned()�̖߂�l
        this.setPaymentPower(l_institution.getInstitutionCode(), l_arrayReturneds);

        //1.17) createResponse( )(�o���\���⍇���ꗗ���N�G�X�g::createResponse)
        //�A�C�e���̒�`
        //���X�|���X�f�[�^�𐶐�����
        WEB3AdminAioCashoutInqListResponse l_response = 
            (WEB3AdminAioCashoutInqListResponse)l_request.createResponse();

        //1.18) (*5) �v���p�e�B�Z�b�g
        //(*5) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_response.branchCode = l_request.branchCode;

        //���X�|���X.��n�� = ���N�G�X�g�f�[�^.��n��
        l_response.deliveryDate = l_request.deliveryDate;

        //���X�|���X.������t�敪 = ���N�G�X�g�f�[�^.������t�敪
        l_response.orderDiv = l_request.orderDiv;

        //���X�|���X.�U����敪 = ���N�G�X�g�f�[�^.�U����敪
        l_response.transferDiv = l_request.transferDiv;

        //���X�|���X.���͋敪 = ���N�G�X�g�f�[�^.���͋敪
        l_response.inputDiv = l_request.inputDiv;

        //���X�|���X.�o���\���⍇������ = �o���⍇�����׈ꗗ
        l_response.cashoutInqUnits = l_arrayReturneds;

        //���X�|���X.�\���y�[�W�ԍ� = toIndex / ���N�G�X�g�f�[�^.�y�[�W���e�\���s��  �������_�ȉ��؂�グ
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";

        //���X�|���X.���y�[�W�� = �i�o���\���⍇�����ׁm�n.length()�̖߂�l�j / ���N�G�X�g�f�[�^.�y�[�W���e�\���s��
        //�������_�ȉ��؂�グ
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";

        //���X�|���X.�����R�[�h�� = �o���\���⍇�����ׁm�n.length()�̖߂�l
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        //���X�|���X.�Ǘ��ҏ����t���O = �،����Params.�o���\���Ǘ��҃^�X�N
        InstitutionParams l_institutionParams = (InstitutionParams)
            l_institution.getDataSourceObject();

        l_response.adminProcessingDiv = l_institutionParams.getPaymentApplyAdminTask();

        //���X�|���X.�ʉ݃R�[�h = ���N�G�X�g�f�[�^.�ʉ݃R�[�h
        l_response.currencyCode = l_request.currencyCode;

        //=========remain zhou-yong NO.2 end ============

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���M)<BR>
     * �o�����M�̊m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���jvalidate���M�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129CFAD0016
     */
    protected WEB3AdminAioCashoutInqConfirmResponse validateSend(
        WEB3AdminAioCashoutInqConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSend(" +
            "WEB3AdminAioCashoutInqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �����`�F�b�N���s���B 
        //[����] 
        // �@@�\�J�e�S���R�[�h�F �hB0101�h 
        // is�X�V�F true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            true);
        
        //1.4 �Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h         
        l_web3Administrator.validateBranchPermission(l_request.branchCode);

        //1.5 ArrayList�C���X�^���X�𐶐�����B
        List l_lisAioCashoutInqUnit = new ArrayList();

        int l_intIndicationLisLength = l_request.directionsList.length;
        AioOrderUnitRow l_aioOrderUnitRow = null;
        
        //1.6 (*1) ���N�G�X�g�f�[�^.�w�����X�g�̗v�f����Loop����
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 �����P�ʂ��擾����B 
            //���z���1�Ԗڂ̗v�f���擾
            //[����] 
            //����ID�F ���N�G�X�g�f�[�^.�w�����X�g�̗v�f 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                           
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];
            l_aioOrderUnitRow = (AioOrderUnitRow) 
                l_aioOrderUnit.getDataSourceObject();
                        
            //1.6.2 �o���\���⍇�����ׂ𐶐�����B 

            //[����] 
            //�����P��Params�F �����P��.getDataSourceObject()�̖߂�l
            
            WEB3AioCashoutInqUnit l_web3AioCashoutInqUnit = this.createCashoutInqUnit(l_aioOrderUnitRow); 
                        
            //1.6.3 �o�����������M�\���ǂ����̃`�F�b�N���s���B 

            //[����] 
            //�����P�ʁF getOrderUnits()�Ŏ擾���������P�� 
            
            String l_strSendPossible =     
                l_web3AioOrderMgr.validateOrderSendPossible(l_aioOrderUnit);
            
            //�߂�l���o���\���⍇������.�`�F�b�N���ʂɃZ�b�g����B
            l_web3AioCashoutInqUnit.checkResult = l_strSendPossible;

            log.debug("�o���\���⍇������.�`�F�b�N���� : = " + l_web3AioCashoutInqUnit.checkResult);
            
            //1.6.4 List�ɗv�f��ǉ�����B
            //[����]
            //arg0�F create�o���\���⍇������()�̖߂�l
            l_lisAioCashoutInqUnit.add(l_web3AioCashoutInqUnit);

        }
                
        WEB3AioCashoutInqUnit[] l_web3AioCashoutInqUnits =  
            new WEB3AioCashoutInqUnit[l_lisAioCashoutInqUnit.size()];
            
        //1.7 List����z����擾����B 
        l_lisAioCashoutInqUnit.toArray(l_web3AioCashoutInqUnits);

        //�o���]�͂��擾���A�Z�b�g����B
        //�m�����n
        //�،���ЃR�[�h �F ���O�C�����.get�،����()�̖߂�l
        //�o���⍇������ �F toArray()�ŕϊ������l
        this.setPaymentPower(l_web3Administrator.getInstitutionCode(), l_web3AioCashoutInqUnits);

        //1.8 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAioCashoutInqConfirmResponse l_adminAioCashoutInqConfirmResponse = null;
        l_adminAioCashoutInqConfirmResponse = 
                (WEB3AdminAioCashoutInqConfirmResponse) 
                    l_request.createResponse();
                
        //1.9  �v���p�e�B�Z�b�g        
        //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_adminAioCashoutInqConfirmResponse.branchCode = l_request.branchCode;
               
        //log.debug("���X�|���X.���X�R�[�h = " + l_adminAioCashoutInqConfirmResponse.branchCode);
        
        //���X�|���X.��n�� = ���N�G�X�g�f�[�^.��n�� 
        l_adminAioCashoutInqConfirmResponse.deliveryDate = l_request.deliveryDate;
               
        log.debug("���X�|���X.��n�� = " + l_adminAioCashoutInqConfirmResponse.deliveryDate);
        
        //���X�|���X.������t�敪 = ���N�G�X�g�f�[�^.������t�敪 
        l_adminAioCashoutInqConfirmResponse.orderDiv = l_request.orderDiv;  
                
        log.debug("���X�|���X.������t�敪 = " + l_adminAioCashoutInqConfirmResponse.orderDiv);
        
        //===========remain zhou-yong NO.3 begin ===========
        
        //���X�|���X.�U����敪 = ���N�G�X�g�f�[�^.�U����敪
        l_adminAioCashoutInqConfirmResponse.transferDiv = l_request.transferDiv;
        
        //���X�|���X.�o���\���⍇������ = �o���\���⍇�����ׂ̔z��
        l_adminAioCashoutInqConfirmResponse.cashoutInqUnits = l_web3AioCashoutInqUnits;
        
        //===========remain zhou-yong NO.3 end ===========

        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqConfirmResponse;
    }
    
    /**
     * (submit���M)<BR>
     * �o�������̑��M���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���jsubmit���M�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129D03F0045
     */
    protected WEB3AdminAioCashoutInqCompleteResponse submitSend(
        WEB3AdminAioCashoutInqCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSend(" +
            "WEB3AdminAioCashoutInqCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �����`�F�b�N���s���B 
        //[����] 
        // �@@�\�J�e�S���R�[�h�F �hB0101�h 
        // is�X�V�F true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            true);
        
        //1.4 �Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //validate���X����(String[])
        //[����]
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 �Ïؔԍ��̃`�F�b�N���s���B
        //�m�����n 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        WEB3AioMarketRequestSenderServiceImpl l_aioMarketSenderService = 
            (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();
                      
        String l_strInstitutionCode = "";
        String l_strBranchCode = "";                
        String l_strAccountCode = ""; 
        
        int l_intIndicationLisLength = l_request.directionsList.length;
        log.debug("�w�����X�g.size = " + l_intIndicationLisLength);
        
        //1.6 ���N�G�X�g�f�[�^.�w�����X�g�̗v�f����Loop����
               
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 �����P�ʂ��擾����B 
            //���z���1�Ԗڂ̗v�f���擾
            //[����] 
            //����ID�F ���N�G�X�g�f�[�^.�w�����X�g�̗v�f 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];            
            
            //1.6.2 �o�����������M�\���ǂ����̃`�F�b�N���s���B

            //[����] 
            //�����P�ʁF getOrderUnits()�Ŏ擾���������P�� 
            
            String l_strSendPossible = 
                l_web3AioOrderMgr.validateOrderSendPossible(l_aioOrderUnit);
            
            //validate�������M�\()�̖߂�l = �hOK�h �̏ꍇ�A���{
            
            if (WEB3OrderSendPossibleDef.SEND_OK.equals(l_strSendPossible))
            {                
                log.debug("validate�������M�\()�̖߂�l = �hOK�h �̏ꍇ");
                try
                {                
                    AccountManager l_accountManager = GtlUtils.getAccountManager();  
                    MainAccount l_mainAccount = l_accountManager.getMainAccount(
                        l_aioOrderUnit.getAccountId());
                    Branch l_branch = l_accountManager.getBranch(
                        l_aioOrderUnit.getBranchId());
                        
                    l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode(); 
                    l_strBranchCode = l_branch.getBranchCode();
                    l_strAccountCode = l_mainAccount.getAccountCode();                     
                          
                }
                catch (NotFoundException l_ex)
                {
                    log.error("__NotFoundException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //1.6.3 �o�����������L���[�e�[�u���̊Y�����R�[�h�̏����敪���������ɍX�V����B 

                //[����] 
                //�،���ЃR�[�h�F ���X.getInstitution().getInstitutionCode()�̖߂�l 
                //���X�R�[�h�F ���X�i�����P��.���XID�j.getBranchCode()�̖߂�l 
                //�ڋq�R�[�h�F �����i�����P��.����ID�j.getAccountCode()�̖߂�l 
                //���ʃR�[�h�F �����P��.���ʃR�[�h
                //�����P��ID�F �����P��.�����P��ID
                        
                AioOrderUnitRow l_aioOrderUnitRow = 
                    (AioOrderUnitRow) l_aioOrderUnit.getDataSourceObject();
                
                l_aioMarketSenderService.updateStatus(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_aioOrderUnitRow.getOrderRequestNumber(), 
                    l_aioOrderUnitRow.getOrderUnitId());
            }            

        }//end for
        
        //1.7 �g���K�𔭍s����B 

        //[����] 
        //�،���ЃR�[�h�F�@@���X�i�����P��.���XID�j.getInstitution().getInstitutionCode()�̖߂�l 
        //�f�[�^�R�[�h�F�@@�hGI801T�h
        
        l_aioMarketSenderService.submitTrigger(
            l_strInstitutionCode, WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T");

        
        //1.8 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAioCashoutInqCompleteResponse l_adminAioCashoutInqCompleteResponse = 
            (WEB3AdminAioCashoutInqCompleteResponse) 
                l_request.createResponse();
                
        //1.9  �v���p�e�B�Z�b�g  
        //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_adminAioCashoutInqCompleteResponse.branchCode = l_request.branchCode;
       
        //���X�|���X.��n�� = ���N�G�X�g�f�[�^.��n��
        l_adminAioCashoutInqCompleteResponse.deliveryDate = l_request.deliveryDate;
        log.debug("���X�|���X.��n�� = " + l_adminAioCashoutInqCompleteResponse.deliveryDate);
                
        //���X�|���X.������t�敪 = ���N�G�X�g�f�[�^.������t�敪
        l_adminAioCashoutInqCompleteResponse.orderDiv = l_request.orderDiv;  

        log.debug("���X�|���X.������t�敪 = " + l_adminAioCashoutInqCompleteResponse.orderDiv);
        
        //���X�|���X.�U����敪 = ���N�G�X�g�f�[�^.�U����敪               
        l_adminAioCashoutInqCompleteResponse.transferDiv = 
            l_request.transferDiv;
        
        log.debug("���X�|���X.�U������Z�@@�փR�[�h = " + 
                l_adminAioCashoutInqCompleteResponse.transferDiv);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqCompleteResponse;
    
    }
    
    /**
     * (validate���)<BR>
     * �o������̊m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���jvalidate����v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129D0E60381
     */
    protected WEB3AdminAioCashoutInqCancelConfirmResponse validateCancel(
        WEB3AdminAioCashoutInqCancelConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancel(" +
            "WEB3AdminAioCashoutInqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �����`�F�b�N���s���B 
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �hB0101�h 
        //is�X�V�F true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            true);
        
        //1.4 �Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //validate���X����(String[])
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 ArrayList�C���X�^���X�𐶐�����B
        List l_lisAioCashoutInqUnit =  new ArrayList();

        int l_intIndicationLisLength = l_request.directionsList.length;

        AioOrderUnitParams l_aioOrderUnitParams = null;
        
        //1.6 �擾�����v�f����Loop����
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 �����P�ʂ��擾����B 
            //���z���1�Ԗڂ̗v�f���擾
            //[����] 
            //����ID�F ���N�G�X�g�f�[�^.�w�����X�g�̗v�f 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
                        
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                            
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];
            l_aioOrderUnitParams = (AioOrderUnitParams) 
                l_aioOrderUnit.getDataSourceObject();
                        
            //1.6.2 �o���\���⍇�����ׂ𐶐�����B 

            //[����] 
            //�����P��Params�F �����P��.getDataSourceObject()�̖߂�l
            
            WEB3AioCashoutInqUnit l_web3AioCashoutInqUnit = new WEB3AioCashoutInqUnit();
            l_web3AioCashoutInqUnit = this.createCashoutInqUnit(l_aioOrderUnitParams); 
                        
            //1.6.3 �o������������\���ǂ����̃`�F�b�N���s���B 
            //[����] 
            //�����P�ʁF getOrderUnits()�Ŏ擾���������P�� 
            
            String l_strCancelPossible =  
                l_web3AioOrderMgr.validateOrderCancelPossible(l_aioOrderUnit);
            log.debug("�o������������\ : " + l_strCancelPossible);
            
            //�߂�l���o���\���⍇������.�`�F�b�N���ʂɃZ�b�g����B
            l_web3AioCashoutInqUnit.checkResult = l_strCancelPossible;
            log.debug("�o���\���⍇������.�`�F�b�N���� : " + l_web3AioCashoutInqUnit.checkResult);
            
            //1.6.4 List�ɗv�f��ǉ�����B 
            //[����] 
            //arg0�F create�o���\���⍇������()�̖߂�l 
            l_lisAioCashoutInqUnit.add(l_web3AioCashoutInqUnit); 
            
        }
                
        WEB3AioCashoutInqUnit[] l_web3AioCashoutInqUnits =  
            new WEB3AioCashoutInqUnit[l_lisAioCashoutInqUnit.size()];
            
        //1.7 List����z����擾����B 
        l_lisAioCashoutInqUnit.toArray(l_web3AioCashoutInqUnits);        

        //�o���]�͂��擾���A�Z�b�g����B
        //�m�����n
        //�،���ЃR�[�h �F ���O�C�����.get�،����()�̖߂�l
        //�o���⍇������ �F toArray()�ŕϊ������l
        this.setPaymentPower(l_web3Administrator.getInstitutionCode(), l_web3AioCashoutInqUnits);

        //1.8 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAioCashoutInqCancelConfirmResponse l_adminAioCashoutInqCancelConfirmResponse = null;
        l_adminAioCashoutInqCancelConfirmResponse = 
                (WEB3AdminAioCashoutInqCancelConfirmResponse) 
                    l_request.createResponse();
                
        //1.9  �v���p�e�B�Z�b�g
        //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_adminAioCashoutInqCancelConfirmResponse.branchCode = l_request.branchCode;
               
        //���X�|���X.��n�� = ���N�G�X�g�f�[�^.��n�� 
        l_adminAioCashoutInqCancelConfirmResponse.deliveryDate = l_request.deliveryDate;        
        log.debug("���X�|���X.��n�� : " + l_adminAioCashoutInqCancelConfirmResponse.deliveryDate);
               
        //���X�|���X.������t�敪 = ���N�G�X�g�f�[�^.������t�敪 
        l_adminAioCashoutInqCancelConfirmResponse.orderDiv = l_request.orderDiv;          
        log.debug("���X�|���X.������t�敪 : " + l_adminAioCashoutInqCancelConfirmResponse.orderDiv);       
        
        //���X�|���X.�U����敪 = ���N�G�X�g�f�[�^.�U����敪
        l_adminAioCashoutInqCancelConfirmResponse.transferDiv = l_request.transferDiv;
        
        //���X�|���X.�o���\���⍇������ = �o���\���⍇�����ׂ̔z��
        l_adminAioCashoutInqCancelConfirmResponse.cashoutInqUnits = l_web3AioCashoutInqUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqCancelConfirmResponse;
    }
    
    /**
     * (submit���)<BR>
     * �o�������̎�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���jsubmit����v �Q��
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AdminAioCashoutInqCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129D0E60391
     */
    protected WEB3AdminAioCashoutInqCancelCompleteResponse submitCancel(
        WEB3AdminAioCashoutInqCancelCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel(" +
            "WEB3AdminAioCashoutInqCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean)(�Ǘ���::validate����)
        //�A�C�e���̒�`
        //�Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �hB0101�h 
        //is�X�V�F true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ ,true);
        
        //1.4 validate���X����(String[]): 
        //�Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 validate����p�X���[�h(String): �Ïؔԍ��̃`�F�b�N���s���B
        //�m�����n 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�]
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        int l_intIndicationLisLength = l_request.directionsList.length;
        //1.6 ���N�G�X�g�f�[�^.�w�����X�g�̗v�f����Loop����
               
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 �����P�ʂ��擾����B 
            //���z���1�Ԗڂ̗v�f���擾
            //[����] 
            //����ID�F ���N�G�X�g�f�[�^.�w�����X�g�̗v�f 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];            
            
            //1.6.2 �o������������\���ǂ����̃`�F�b�N���s���B
            //[����] 
            //�����P�ʁF getOrderUnits()�Ŏ擾���������P�� 
            
            String l_strCancelPossible = 
                l_web3AioOrderMgr.validateOrderCancelPossible(l_aioOrderUnit);
            
            //=========remain zhou-yong NO.4 begin ===========
            
            //validate��������\()�̖߂�l = �hOK�h �̏ꍇ�A���{
            //1.6.3
            if (WEB3OrderCancelPossibleDef.CANCEL_OK.equals(l_strCancelPossible))
            {
                log.debug("validate��������\()�̖߂�l = �hOK�h �̏ꍇ");

                //1.6.3.1) execute(AioOrderUnit,String)(�o�����UnitServiceImpl::execute)
                //�A�C�e���̒�`
                //����������e�C���X�^���X�𐶐�����B
                //[����] 
                //����ID�F ���N�G�X�g�f�[�^.�w�����X�g�̗v�f 
                //�p�X���[�h:���N�G�X�g.�p�X���[�h
                WEB3AioCashoutCancelUnitService l_service =
                    (WEB3AioCashoutCancelUnitService) Services.getService(
                        WEB3AioCashoutCancelUnitService.class);  
                
                l_service.execute(l_aioOrderUnit,l_request.password);
            }

        }//end for
        
        //1.7 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminAioCashoutInqCancelCompleteResponse l_adminAioCashoutInqCancelCompleteResponse =
            (WEB3AdminAioCashoutInqCancelCompleteResponse)
                l_request.createResponse();
                
        //=========remain zhou-yong NO.4 end ===========
        
        //1.8  �v���p�e�B�Z�b�g     
        //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_adminAioCashoutInqCancelCompleteResponse.branchCode = l_request.branchCode;
               
        //���X�|���X.��n�� = ���N�G�X�g�f�[�^.��n�� 
        l_adminAioCashoutInqCancelCompleteResponse.deliveryDate = l_request.deliveryDate;
               
        //���X�|���X.������t�敪 = ���N�G�X�g�f�[�^.������t�敪 
        l_adminAioCashoutInqCancelCompleteResponse.orderDiv = l_request.orderDiv;  
        
        //���X�|���X.�U����敪 = ���N�G�X�g�f�[�^.�U����敪                  
        l_adminAioCashoutInqCancelCompleteResponse.transferDiv = l_request.transferDiv;
        
        log.exiting(STR_METHOD_NAME);        
        return l_adminAioCashoutInqCancelCompleteResponse;
    }
    
    /**
	 * (create�擾����������)<BR>
	 * ���N�G�X�g�f�[�^����A�f�[�^�擾����������𐶐�����B<BR>
	 * <BR>
	 * �P�j��̕�����𐶐�����B<BR>
	 * <BR>
	 * �Q�j�������<BR>
	 * <BR>
	 * ����������F "order_type=?"<BR>
	 * <BR>
	 * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
	 * <BR>
	 * �R�j�o���\���敪<BR>
	 * <BR>
	 * ����������F " and payment_application_div=null"<BR>
	 * <BR>
	 * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
	 * <BR>
	 * �S�j���XID<BR>
	 * <BR>
	 * �S�|�P�j����.���XID�̗v�f�� = 1 �̏ꍇ <BR>
	 * <BR>
	 * ����������F " and branch_id=?" <BR>
	 * <BR>
	 * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
	 * <BR>
	 * �S�|�Q�j����.���XID�̗v�f�� > 1 �̏ꍇ <BR>
	 * <BR>
	 * ����������F " and (branch_id=? or branch_id=? or ... or branch_id=?)" <BR>
	 * �����ʓ��́ubranch_id=?�v�̐����v�f��������悤�ɂ��� <BR>
	 * <BR>
	 * ��L��������P�j�̕�����̖����ɒǉ�����B <BR>
	 * <BR>
	 * �T�j��n��<BR>
	 * <BR>
	 * ����.��n�� != null �̏ꍇ<BR>
	 * <BR>
	 * ����������F " and delivery_date=?"<BR>
	 * <BR>
	 * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
	 * <BR>
	 * �U�j������t�敪<BR>
	 * <BR>
	 * ����.������t�敪 != 3�i���ׂāj�̏ꍇ <BR>
	 * <BR>
	 * ����������F " and order_status=? and cancel_type=?" <BR>
	 * <BR>
	 * ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
	 * <BR>
     * �V�j���͋敪 <BR>
     * ����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =1�i�ڋq�j�̏ꍇ<BR>
     * <BR>
     * ����������F " and order_root_div in (? , ? , ? , ? , ?)"<BR>
     * <BR>
     * ����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =2�iSONAR�j�̏ꍇ<BR>
     * <BR>
     * ����������F " and order_root_div = ?"<BR>
     * <BR>
     * ��L��������P�j�̕�����̖����ɒǉ�����B <BR>
     * <BR>
     * �W�j�ʉ݃R�[�h<BR>
     * �@@����.�ʉ݃R�[�h != 0�i���ׂāj ���� �ʉ݃R�[�h = null(�~��) �̏ꍇ<BR>
     * <BR>
�@@�@@ * �@@�@@����������F " and currency_code is null"<BR>
     * <BR>
     * �@@����.�ʉ݃R�[�h !=0�i���ׂāj ���� �ʉ݃R�[�h != null �̏ꍇ<BR>
     * <BR>
�@@�@@ * �@@�@@����������F " and currency_code = ?"<BR>
     * <BR>
     * �@@��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �X�j���������������ԋp����B<BR>
     * <BR>
	 * @@param l_lngBranchID -
	 *            (���XID)
	 * @@param l_datDeliveryDate -
	 *            (��n��)
	 * @@param l_strOrderAcceptedDiv -
	 *            (������t�敪)
	 * @@param l_strInputDiv -
	 * 			  (���͋敪)
     * @@param l_strCurrencyCode -
     *            (�ʉ݃R�[�h)               
	 * @@return String
	 * @@roseuid 4108E3FB0167
	 */
    protected String createGetCondCharacterString(
        long[] l_lngBranchIDs, 
        Date l_datDeliveryDate, 
        String l_strOrderAcceptedDiv, 
        String l_strInputDiv, 
        String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString(" + 
            "long l_lngBranchID, Date l_datDeliveryDate, String l_strOrderAcceptedDiv, " +
            "String l_strInputDiv, String l_strCurrencyCode)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j��̕�����𐶐�����B
        String l_strWhereCondition = "";
        
        //�Q�j�������
        //����������F "order_type=?"         

        l_strWhereCondition += "order_type = ?";
        
        //�R�j�o���\���敪 
        //����������F " and payment_application_div=null" 

        l_strWhereCondition += " and payment_application_div is null";
        
        //�S�j���XID 

        //�S�|�P�j����.���XID�̗v�f�� = 1 �̏ꍇ 
        //����������F " and branch_id=?" 
        //��L��������P�j�̕�����̖����ɒǉ�����B 

        if (l_lngBranchIDs.length == 1)
        {
            l_strWhereCondition += " and branch_id = ?";
        }

        //�S�|�Q�j����.���XID�̗v�f�� > 1 �̏ꍇ 
        //����������F " and (branch_id=? or branch_id=? or ... or branch_id=?)" 
        //�����ʓ��́ubranch_id=?�v�̐����v�f��������悤�ɂ��� 
        //��L��������P�j�̕�����̖����ɒǉ�����B 

        else if (l_lngBranchIDs.length > 1)
        {
            l_strWhereCondition += " and (branch_id = ?";
            for (int i = 1; i < l_lngBranchIDs.length; i++)
            {
                l_strWhereCondition += " or branch_id = ?";                
            }
            l_strWhereCondition += ")";
        }
        
        //�T�j��n�� 
        //����.��n�� != null �̏ꍇ 
        //����������F " and delivery_date=?" 
        if (l_datDeliveryDate != null)
        {        
            l_strWhereCondition += " and delivery_date = ?";
        }
        
        //�U�j������t�敪 
        //����.������t�敪 != 3�i���ׂāj�̏ꍇ 
        //����������F " and order_status=? and cancel_type=?" 
        
        if (!WEB3AioOrderAcceptedDivDef.ALL.equals(l_strOrderAcceptedDiv))
        {
            l_strWhereCondition += " and order_status = ? and cancel_type = ?";
        }        

        // �V�j���͋敪
        // ����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =1�i�ڋq�j�̏ꍇ
        // ����������F " and order_root_div in (? , ? , ? , ? , ?)"
        // ����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =2�iSONAR�j�̏ꍇ
        // ����������F " and order_root_div = ?"
        if (!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.CUSTOMER.equals(l_strInputDiv))
        {
        	l_strWhereCondition += " and order_root_div in (? , ? , ? , ? , ?)";
        }
        else if(!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.SONAR.equals(l_strInputDiv))
        {
        	l_strWhereCondition += " and order_root_div = ?";
        }
        
        //�W�j�ʉ݃R�[�h
        //����.�ʉ݃R�[�h != 0�i���ׂāj ���� �ʉ݃R�[�h = null(�~��) �̏ꍇ
        //����������:"and currency_code is null"
        //����.�ʉ݃R�[�h !=0�i���ׂāj ���� �ʉ݃R�[�h != null �̏ꍇ
        //����������F " and currency_code = ?"
        //��L��������P�j�̕�����̖����ɒǉ�����B
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_strCurrencyCode) && 
            l_strCurrencyCode == null)
        {
            l_strWhereCondition += " and currency_code is null";
        }
        else if(!WEB3AioCurrencyCodeDef.ALL.equals(l_strCurrencyCode) && 
            l_strCurrencyCode != null)
        {
            l_strWhereCondition += " and currency_code = ?";
        }
        
        //9�j���������������ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_strWhereCondition;
    }
    
    /**
	 * (create�擾�����f�[�^�R���e�i)<BR>
	 * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B<BR>
	 * <BR>
	 * �Q�j������� <BR>
	 * 1001�i�o�������j���P�j�̃��X�g�ɒǉ�����B<BR>
	 * <BR>
	 * �R�j���XID <BR>
	 * ����.���XID�̊e�v�f���P�j�̃��X�g�ɒǉ�����B <BR>
	 * <BR>
	 * �S�j��n�� <BR>
	 * ����.��n�� != null �̏ꍇ <BR>
	 * ����.��n�����P�j�̃��X�g�ɒǉ�����B <BR>
	 * <BR>
	 * �T�j������� <BR>
	 * �T�|�P�j����.������t�敪 = 0�i��t���ρj�̏ꍇ <BR>
	 * 1�i��t�ρi�V�K�����j�j���P�j�̃��X�g�ɒǉ�����B<BR>
	 * <BR>
	 * �T�|�Q�j����.������t�敪 = 1�i��t�ρj�̏ꍇ <BR>
	 * 3�i�����ρi�V�K�����j�j���P�j�̃��X�g�ɒǉ�����B<BR>
	 * <BR>
	 * �T�|�R�j����.������t�敪 = 2�i��t�G���[�j�̏ꍇ <BR>
	 * 6�i�������s�i�V�K�����j�j���P�j�̃��X�g�ɒǉ�����B<BR>
	 * <BR>
	 * �U�j��������敪 <BR>
	 * ����.������t�敪 != 3�i���ׂāj�̏ꍇ <BR>
	 * 0�i�����l�j���P�j�̃��X�g�ɒǉ�����B<BR>
	 * <BR>
	 * �V�j���͋敪 <BR>
	 * ����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =1�i�ڋq�j�̏ꍇ<BR>
	 * <BR>
	 * 2�iPC�j�A3�i�X�����O�V���b�g�j�A4�ii-mode�j�A5�iVodafone�j�A6�iAU�j<BR>
	 * <BR>
	 * ���P�j�̃��X�g�ɒǉ�����B<BR>
	 * <BR>
	 * ����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =2�iSONAR�j�̏ꍇ<BR>
	 * <BR>
	 * 9�iHOST�j���P�j�̃��X�g�ɒǉ�����B <BR>
     * <BR>
     * �W�j�ʉ݃R�[�h<BR>
     * �@@����.�ʉ݃R�[�h !=0�i���ׂāj ���� �ʉ݃R�[�h != null �̏ꍇ<BR>
     * <BR>
     * �@@����.�ʉ݃R�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * <BR>
	 * �X�j���X�g����z����擾���A�ԋp����B<BR>
	 * <BR>
	 * @@param l_lngBranchIDs -
	 *            (���XID)
	 * @@param l_datDeliveryDate -
	 *            (��n��)
	 * @@param l_strOrderAcceptedDiv -
	 *            (������t�敪)
	 * @@param l_strInputDiv - 
	 * 			  (���͋敪)
     * @@param l_strCurrencyCode -
     *            (�ʉ݃R�[�h)  
	 * @@return Object[]
	 * @@roseuid 4108EA2D02BF
	 */
    protected Object[] createGetCondDataContainer(
        long[] l_lngBranchIDs, 
        Date l_datDeliveryDate, 
        String l_strOrderAcceptedDiv, 
        String l_strInputDiv, 
        String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer(long[] l_lngBranchIDs, " +
            "Date l_datDeliveryDate, " + 
            "String l_strOrderAcceptedDiv, " + 
            "String l_strInputDiv, " + 
            "String l_strCurrencyCode)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j���ArrayList�𐶐�����B 
        List l_lisAioSettleReportUnit = null;
        l_lisAioSettleReportUnit = new ArrayList();

        //�Q�j������� 
        //1001�i�o�������j���P�j�̃��X�g�ɒǉ�����B 
        l_lisAioSettleReportUnit.add(OrderTypeEnum.CASH_OUT);

        //�R�j���XID
        //����.���XID�̊e�v�f���P�j�̃��X�g�ɒǉ�����B 
        for (int i = 0; i < l_lngBranchIDs.length; i++)
        {
            l_lisAioSettleReportUnit.add(new Long(l_lngBranchIDs[i]));
        }

        //�S�j��n�� 

        //����.��n�� != null �̏ꍇ 
        //����.��n�����P�j�̃��X�g�ɒǉ�����B
        
        if (l_datDeliveryDate != null)
        {
            l_lisAioSettleReportUnit.add(l_datDeliveryDate);
        }
        
        //�T�j������� 

        //�T�|�P�j����.������t�敪 = 0�i��t���ρj�̏ꍇ 
        //1�i��t�ρi�V�K�����j�j���P�j�̃��X�g�ɒǉ�����B 
        if (WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(OrderStatusEnum.ACCEPTED);
        }

        //�T�|�Q�j����.������t�敪 = 1�i��t�ρj�̏ꍇ 
        //3�i�����ρi�V�K�����j�j���P�j�̃��X�g�ɒǉ�����B 
        if (WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(OrderStatusEnum.ORDERED);
        }

        //�T�|�R�j����.������t�敪 = 2�i��t�G���[�j�̏ꍇ 
        //6�i�������s�i�V�K�����j�j���P�j�̃��X�g�ɒǉ�����B 
        if (WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(OrderStatusEnum.NOT_ORDERED);
        }
        
        //�U�j��������敪
        //����.������t�敪 != 3�i���ׂāj�̏ꍇ
        //0�i�����l�j���P�j�̃��X�g�ɒǉ�����B
        if (!WEB3AioOrderAcceptedDivDef.ALL.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        }
        
        //�V�j���͋敪 
        //����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =1�i�ڋq�j�̏ꍇ 
        //2�iPC�j�A3�i�X�����O�V���b�g�j�A4�ii-mode�j�A5�iVodafone�j�A6�iAU�j 
        //���P�j�̃��X�g�ɒǉ�����B 
        //����.���͋敪 !=0�i���ׂāj ���� ���͋敪 =2�iSONAR�j�̏ꍇ 
        //9�iHOST�j���P�j�̃��X�g�ɒǉ�����B
        if (!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.CUSTOMER.equals(l_strInputDiv))
        {
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.PC);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.SLINGSHOT);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.I_MODE);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.VODAFONE);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.AU);
        }
        else if(!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.SONAR.equals(l_strInputDiv))
        {
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.HOST);
        }
        
        //�W�j�ʉ݃R�[�h
        //����.�ʉ݃R�[�h !=0�i���ׂāj ���� �ʉ݃R�[�h != null �̏ꍇ
        //����.�ʉ݃R�[�h���P�j�̃��X�g�ɒǉ�����B
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_strCurrencyCode) && 
            l_strCurrencyCode != null)
        {
            l_lisAioSettleReportUnit.add(l_strCurrencyCode);
        }
              
        //�X�j���X�g����z����擾���A�ԋp����B 
        Object[] l_bindVars = 
            new Object[l_lisAioSettleReportUnit.size()];
            
        l_lisAioSettleReportUnit.toArray(l_bindVars);
        
        log.exiting(STR_METHOD_NAME);        
        return l_bindVars;
    }
    
    /**
     * (get������t�敪)<BR>
     * ������t�敪���擾����B<BR>
     * <BR>
     * �P�j�P�j�i������� = 1�i��t�ρj and ��������敪 = 0�i�����l�j <BR>
     *  and MQ�X�e�[�^�X = 0�i�����M�j�j or�i������� = 14�i�����ρi��������j�j <BR>
     *  and ��������敪 = 3�i�S����������j�j �̏ꍇ<BR>
     * <BR>
     *    0�i��t���ρj��ԋp����B<BR>
     * <BR>
     * �Q�j������� = 3�i�����ρj and ��������敪 = 0�i�����l�j �̏ꍇ<BR>
     * <BR>
     *    1�i��t�ρj��ԋp����B<BR>
     * <BR>
     * �R�j������� = 6�i�������s�j and ��������敪 = 0�i�����l�j �̏ꍇ<BR>
     * <BR>
     *    2�i��t�G���[�j��ԋp����B<BR>
     * <BR>
     * �S�j������� = 1�i��t�ρj and ��������敪 = 0�i�����l�j and<BR> 
     *   MQ�X�e�[�^�X = 1�i���M�ς݁j �̏ꍇ <BR>
     *   4�i��t���j��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderStatus - (�������)
     * @@param l_strCancelType - (��������敪)
     * @@param l_strMqStatus - (MQ�X�e�[�^�X)
     * @@return String
     * @@roseuid 4108F6B20290
     */
    protected String getOrderAcceptType(long l_lngOrderStatus, String l_strCancelType, String l_strMqStatus) 
    {
        final String STR_METHOD_NAME = "getOrderAcceptType()";
        log.entering(STR_METHOD_NAME);
        
        //=========remain zhou-yong NO.6 begin ========
        
        String l_strOrderAcceptType = null;
        //�P�j�i������� = 1�i��t�ρj and ��������敪 = 0�i�����l�j and MQ�X�e�[�^�X = 0�i�����M�j�j or 
        // �i������� = 14�i�����ρi��������j�j and ��������敪 = 3�i�S����������j�j �̏ꍇ
        //  0�i��t���ρj��ԋp����B 
        if(((OrderStatusEnum.ACCEPTED.intValue() == l_lngOrderStatus) 
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
            && WEB3SubmitMarketTriggerDef.NOT_TRIGGER.equals(l_strMqStatus))
            || ((OrderStatusEnum.CANCELLED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.CANCELED.equals(l_strCancelType)))
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED;
        }
        
        //�Q�j������� = 3�i�����ρj and ��������敪 = 0�i�����l�j �̏ꍇ 
        //  1�i��t�ρj��ԋp����B 
        if((OrderStatusEnum.ORDERED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.ACCEPTED;
        }
        
        //�R�j������� = 6�i�������s�j and ��������敪 = 0�i�����l�j �̏ꍇ 
        //  2�i��t�G���[�j��ԋp����B 
        if((OrderStatusEnum.NOT_ORDERED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR;
        }
        
        //�S�j������� = 1�i��t�ρj and ��������敪 = 0�i�����l�j and MQ�X�e�[�^�X = 1�i���M�ς݁j �̏ꍇ 
        //  4�i��t���j��ԋp����B
        if((OrderStatusEnum.ACCEPTED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
            && WEB3SubmitMarketTriggerDef.TRIGGER.equals(l_strMqStatus) )
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.ACCEPTING;
        }
        
        //=========remain zhou-yong NO.6 end ========
        
        log.exiting(STR_METHOD_NAME);
        return l_strOrderAcceptType;
    }
    
    /**
     * (get����敪)<BR>
     * ����敪���擾����B<BR>
     * <BR>
     * �P�j������� = 14�i�����ρi��������j�j and <BR>
     * ��������敪 = 3�i�S����������j �̏ꍇ<BR>
     * <BR>
     *    2�i����ρj��ԋp����B<BR>
     * <BR>
     * �Q�j����ȊO�̏ꍇ<BR>
     * <BR>
     *    0�i�����l�j��ԋp����B<BR>
     * @@param l_lngOrderStatus - (�������)
     * @@param l_strCancelType - (��������敪)
     * @@return String
     * @@roseuid 4108F9BE008D
     */
    protected String getCancelDivision(long l_lngOrderStatus, String l_strCancelType) 
    {
        final String STR_METHOD_NAME = "getCancelDivision()";
        log.entering(STR_METHOD_NAME);        
       
        //�P�j������� = 14�i�����ρi��������j�j and ��������敪 = 3�i�S����������j �̏ꍇ
        String l_strCancelDiv = null;
        
        if (l_lngOrderStatus == OrderStatusEnum.CANCELLED.intValue() &&
            WEB3ModifyCancelTypeDef.CANCELED.equals(l_strCancelType)) 
        {
            //2�i����ρj��ԋp����B 
            l_strCancelDiv = WEB3ModifyCancelTypeDef.PART_CANCELED;
        }
        //�Q�j����ȊO�̏ꍇ 
        else
        {
            //0�i�����l�j��ԋp����B 
            l_strCancelDiv = WEB3ModifyCancelTypeDef.INITIAL_VALUE;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCancelDiv;
    }
    
    /**
     * (create�o���\���⍇������)<BR>
     * �o���\���⍇�����׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇���jcreate�o���\���⍇�����ׁv �Q��<BR>
     * @@param l_aioOrderUnitParams - (�����P��Params)<BR>
     *     �����P��Params�I�u�W�F�N�g <BR>
     * @@return WEB3AioCashoutInqUnit
     * @@throws WEB3BaseException
     * @@roseuid 4109B3DA0250
     */
    protected WEB3AioCashoutInqUnit createCashoutInqUnit(
        AioOrderUnitRow l_aioOrderUnitRow) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "createCashoutInqUnit(" +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnitRow == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 �o���\���⍇�����׃C���X�^���X�𐶐�����B 
        WEB3AioCashoutInqUnit l_web3AioCashoutInqUnit = new WEB3AioCashoutInqUnit();        
        AccountManager l_accountManager = GtlUtils.getAccountManager();        
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        String l_strBranchCode = null;
        String l_strMainAccountCode = null;
        MainAccount l_mainAccount = null;
        
        try
        {  
            //1.2 ���X�I�u�W�F�N�g���擾����B
            Branch l_branch = l_accountManager.getBranch(
                l_aioOrderUnitRow.getBranchId());
            
            //1.3 ���X�R�[�h���擾����B 
            l_strBranchCode = l_branch.getBranchCode();
            
            //1.4 �ڋq�I�u�W�F�N�g���擾����B           
            l_mainAccount = l_accountManager.getMainAccount(
                l_aioOrderUnitRow.getAccountId());
            
            //1.5 �ڋq�R�[�h���擾����B 
            l_strMainAccountCode = l_mainAccount.getAccountCode().substring(0, 6);
            
            //1.6 �ڋq�̖��̂��擾����B
            l_gentradeMainAccount =
                (WEB3GentradeMainAccount) l_mainAccount;  
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        String l_strMainAccountName = l_gentradeMainAccount.getDisplayAccountName();
        
        //1.7 get�U������Z�@@�փ��R�[�h(�ڋq)
        //������������擾�����U������Z�@@�փ��R�[�h��List�^�ŕԋp����B
        List l_lisTransferedFinInstitution = this.getTransferedFinInstitutionRecord(l_mainAccount);
        
        //1.8 create�U������Z�@@�֏��
        //�U������Z�@@�ւ̏����o���\���⍇�����׃I�u�W�F�N�g�Ɋi�[����B
        this.createTransferedFinInstitutionInfo(l_lisTransferedFinInstitution, l_web3AioCashoutInqUnit);
        
        // =============remain zhou-yong NO.7 begin =========
        
        //1.9 ������t�敪���擾����B 
        //[����] 
        //������ԁF ����.�����P��Params.������� 
        //��������敪�F ����.�����P��Params.��������敪 
        //MQ�X�e�[�^�X�F ����.�����P��Params.MQ�X�e�[�^�X

        log.debug("������ԁF" + l_aioOrderUnitRow.getOrderStatus().intValue());
        log.debug("��������敪�F" + l_aioOrderUnitRow.getCancelType());
        
        String l_strOrderAcceptType = this.getOrderAcceptType(
            l_aioOrderUnitRow.getOrderStatus().intValue(),
            l_aioOrderUnitRow.getCancelType(),
            l_aioOrderUnitRow.getMqStatus());
        // =============remain zhou-yong NO.7 end =========
        
        //1.10 ����敪���擾����B 
        //[����] 
        //������ԁF ����.�����P��Params.������� 
        //��������敪�F ����.�����P��Params.��������敪 
        String l_strCancelDiv = this.getCancelDivision(
            l_aioOrderUnitRow.getOrderStatus().intValue(),
            l_aioOrderUnitRow.getCancelType());
        
        //1.13 getTrader(����ID : long)
        //�����P��.�����ID�̈��҃R�[�h���擾����B
        //[����]
        //����ID�F �����P��Params.�����ID

        String l_strTraderCode = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //�����P��.�����ID��null�̏ꍇ
        if(!l_aioOrderUnitRow.getTraderIdIsNull())
        {
            try
            {
                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()
                Trader l_trader = null;
                l_trader = l_finObjectManager.getTrader(l_aioOrderUnitRow.getTraderId());
                l_strTraderCode = l_trader.getTraderCode();
            }
            catch (NotFoundException l_nfe)
            {
                //�����P��.�����ID�ɊY������f�[�^�����҃e�[�u���ɑ��݂��Ȃ��ꍇ
                l_strTraderCode = null;
            }
        }
        
        //1.14 �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����B
                
        //�o���\���⍇������. ����ID = ����.�����P��Params.����ID
        l_web3AioCashoutInqUnit.orderId = l_aioOrderUnitRow.getOrderId() + "";
        log.debug("�o���\���⍇������. ����ID =" + l_web3AioCashoutInqUnit.orderId);
        
        //�o���\���⍇������. ���X�R�[�h = ���X.getBranchCode()�̖߂�l
        l_web3AioCashoutInqUnit.branchCode = l_strBranchCode;
        log.debug("�o���\���⍇������. ���X�R�[�h =" + l_web3AioCashoutInqUnit.branchCode);
                
        //�o���\���⍇������. �ڋq�R�[�h = �ڋq.getAccountCode()�̖߂�l
        l_web3AioCashoutInqUnit.accountCode = l_strMainAccountCode;   
        log.debug("�o���\���⍇������. �ڋq�R�[�h =" + l_web3AioCashoutInqUnit.accountCode);
             
        //�o���\���⍇������. �ڋq�� = �ڋq.get�ڋq�\����()�̖߂�l
        l_web3AioCashoutInqUnit.accountName = l_strMainAccountName;
        log.debug("�o���\���⍇������. �ڋq�� =" + l_web3AioCashoutInqUnit.accountName);
        
        //�o���\���⍇������. �������� = ����.�����P��Params.�����o�H�敪
        l_web3AioCashoutInqUnit.orderRootDiv = l_aioOrderUnitRow.getOrderRootDiv();
        log.debug("�o���\���⍇������. �����o�H�敪 =" + l_web3AioCashoutInqUnit.orderRootDiv);
        
        //�o���\���⍇������. ���҃R�[�h = getTrader().getTraderCode
        l_web3AioCashoutInqUnit.traderCode = l_strTraderCode;
        log.debug("�o���\���⍇������. ���҃R�[�h =" + l_web3AioCashoutInqUnit.traderCode);
                
        //�o���\���⍇������. �������� = ����.�����P��Params.��t����
        l_web3AioCashoutInqUnit.orderDate = l_aioOrderUnitRow.getReceivedDateTime();
        log.debug("�o���\���⍇������. �������� =" + l_web3AioCashoutInqUnit.orderDate);
        
        //�o���\���⍇������. ��n�� = ����.�����P��Params.��n��
        l_web3AioCashoutInqUnit.deliveryDate = l_aioOrderUnitRow.getDeliveryDate();
        log.debug("�o���\���⍇������. ��n�� =" + l_web3AioCashoutInqUnit.deliveryDate);
        
        //�o���\���⍇������. �o���z = ����.�����P��Params.����
        l_web3AioCashoutInqUnit.cashoutAmt = WEB3StringTypeUtility.formatNumber(
                l_aioOrderUnitRow.getQuantity());
        log.debug("�o���\���⍇������. �o���z =" + l_web3AioCashoutInqUnit.cashoutAmt);
        
        //�o���\���⍇������. ������t�敪 = �o���\���⍇���T�[�r�XImpl.get������t�敪()�̖߂�l
        l_web3AioCashoutInqUnit.orderDiv = l_strOrderAcceptType;
        log.debug("�o���\���⍇������. ������t�敪 =" + l_web3AioCashoutInqUnit.orderDiv);
        
        //�o���\���⍇������. ����敪 = �o���\���⍇���T�[�r�XImpl.get����敪()�̖߂�l
        l_web3AioCashoutInqUnit.cancelDiv = l_strCancelDiv;
        log.debug("�o���\���⍇������. ����敪 =" + l_web3AioCashoutInqUnit.cancelDiv);
        
        //�o���\���⍇������. ������� = �i�ȉ��̂Ƃ���j
        //     �o���\���⍇������. ����敪 = 0�i�����l�j�̏ꍇ�Anull
        //     �o���\���⍇������. ����敪 = 2�i����ρj�̏ꍇ�A����.�����P��Params.�X�V���t
        if (WEB3AioCancelDivDef.INITIAL_VALUE.equals(l_web3AioCashoutInqUnit.cancelDiv))
        {
            l_web3AioCashoutInqUnit.cancelDate = null;
        }
        else if (WEB3AioCancelDivDef.CANCElED.equals(l_web3AioCashoutInqUnit.cancelDiv))
        {
            l_web3AioCashoutInqUnit.cancelDate = l_aioOrderUnitRow.getLastUpdatedTimestamp();
        }
        log.debug("�o���\���⍇������. ������� =" + l_web3AioCashoutInqUnit.cancelDate);
        
        //�o���\���⍇������. �ʉ݃R�[�h = ����.�����P��Params.�ʉ݃R�[�h
        l_web3AioCashoutInqUnit.currencyCode = l_aioOrderUnitRow.getCurrencyCode();
        log.debug("�o���\���⍇������. �ʉ݃R�[�h =" + l_web3AioCashoutInqUnit.currencyCode);
          
        //�o���\���⍇������. �`�F�b�N���� = null
        l_web3AioCashoutInqUnit.checkResult = null;
        
        log.exiting(STR_METHOD_NAME);
        return l_web3AioCashoutInqUnit;

    }
    
    /**
     * (sort�o���\���⍇������)<BR>
     * �o���\���⍇�����ׂ̔z����\�[�g����B<BR>
     * <BR>
     * �\�[�g���F ���X�R�[�h�i�����j �� ���������i�����j<BR>
     * <BR>
     * �P�j���ArrayList�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j���X�R�[�hComparator�𐶐�����B<BR>
     *    ����.orderBy�ɂ́AA�i�����j��ݒ肷��B<BR>
     * <BR>
     *    ��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j��������Comparator�𐶐�����B<BR>
     *    ����.orderBy�ɂ́AA�i�����j��ݒ肷��B<BR>
     * <BR>
     *    ��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �S�jArrayList����Comparator�̔z����擾����B<BR>
     * <BR>
     * �T�jComparator�̔z�񏇂̃\�[�g�������s���B<BR>
     *    WEB3ArraysUtility.sort(����.����, Comparator[]) <BR>
     * <BR>
     * �U�j�\�[�g���ꂽ���ׂ̔z���ԋp����B<BR>
     * <BR>
     * @@param l_cashoutInqUnit - (�o���\���⍇�����ׂ̔z��)
     * @@return WEB3AioCashoutInqUnit
     * @@roseuid 4109B7340146
     */
    protected WEB3AioCashoutInqUnit[] sortCashoutInqUnit(
        WEB3AioCashoutInqUnit[] l_cashoutInqUnit) 
    {
        final String STR_METHOD_NAME = "sortCashoutInqUnit()";
        log.entering(STR_METHOD_NAME);
           
        //�P�j���ArrayList�C���X�^���X�𐶐�����B        
        ArrayList l_lisComparator = new ArrayList();
        
        //�Q�j���X�R�[�hComparator�𐶐�����B
        WEB3AioBranchCodeComparator l_branchCodeComparator = 
            new WEB3AioBranchCodeComparator(WEB3AscDescDef.ASC);
            
        l_lisComparator.add(l_branchCodeComparator);
            
        //�R�j��������Comparator�𐶐�����B
        WEB3AioOrderDateComparator l_orderDateComparator = 
            new WEB3AioOrderDateComparator(WEB3AscDescDef.ASC);
        
        l_lisComparator.add(l_orderDateComparator);
        
        //�S�jArrayList����Comparator�̔z����擾����B
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        
        //�T�jComparator�̔z�񏇂̃\�[�g�������s���B 
        //WEB3ArraysUtility.sort(����.����, Comparator[]) 
        WEB3ArraysUtility.sort(l_cashoutInqUnit, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
        return l_cashoutInqUnit;
    }
    
    /**
     * (get�\������)<BR>
     * �o���\���⍇�����ׂ̔z�񂩂�v���y�[�W���ɕ\������閾��<BR>
     * �̔z����擾���ĕԋp����B<BR>
     * <BR>
     * �P�j���ArrayList�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�o���\���⍇������[����.fromIndex]����o���\���⍇��<BR>
     * ���ׁm����.toIndex]�̗v�f���A�P�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�jArrayList����z����擾����B<BR>
     * <BR>
     * �S�j���������z���ԋp����B<BR>
     * @@param l_cashoutInqUnits - (�o���\���⍇�����ׂ̔z��)
     * @@param l_intFromIndex - (�\���Ώۂ̊J�n�ʒu�̃C���f�b�N�X�ԍ�)
     * @@param l_intToIndex - (�\���Ώۂ̏I���ʒu�̃C���f�b�N�X�ԍ�)
     * @@return WEB3AioCashoutInqUnit[]
     * @@roseuid 4109E31D00BA
     */
    protected WEB3AioCashoutInqUnit[] getIndicationDetails(
        WEB3AioCashoutInqUnit[] l_cashoutInqUnits, 
        int l_intFromIndex, 
        int l_intToIndex)
    {
        final String STR_METHOD_NAME = "getIndicationDetails" + 
            "WEB3AioCashoutInqUnit[] l_cashoutInqUnits, " +
            "int l_intFromIndex, int l_intToIndex)";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashoutInqUnits == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        if (l_cashoutInqUnits.length == 0)
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���ArrayList�C���X�^���X�𐶐�����B        
        List l_lisCashoutInqUnits = null;
        l_lisCashoutInqUnits = new ArrayList();
        
        //�Q�j�o���\���⍇������[����.fromIndex]����o���\���⍇��
        //���ׁm����.toIndex]�̗v�f���A�P�j��ArrayList�ɒǉ�����B
        for (int i = l_intFromIndex; i <= l_intToIndex; i++)
        {          
            l_lisCashoutInqUnits.add(l_cashoutInqUnits[i]);
        }
        
        //�R�jArrayList����z����擾����B
        WEB3AioCashoutInqUnit[] l_web3AioCashoutInqUnits =  
            new WEB3AioCashoutInqUnit[l_lisCashoutInqUnits.size()];
            
        //1.5 List����z����擾����B 
        l_lisCashoutInqUnits.toArray(l_web3AioCashoutInqUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_web3AioCashoutInqUnits;
    }
    
    /**
	 * (get�U������Z�@@�փ��R�[�h )<BR>
	 * ������������擾�����U������Z�@@�փ��R�[�h��List�^�ŕԋp����B<BR>
	 * <BR>
	 * �P�jdoFindAllQuery()���g�p���ĐU������Z�@@�փe�[�u�����ȉ��̏����Ō����B<BR>
	 * <BR>
	 * [����] 
	 * �U������Z�@@�փe�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
	 * �U������Z�@@�փe�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() 
	 * �U������Z�@@�փe�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()
	 * �U������Z�@@�փe�[�u��.�w��敪 = 'A'
	 * <BR>
	 * 2�j�������ʂ�ԋp<BR>
	 * <BR>
	 * @@param l_mainAccount - (�ڋq)
	 * @@return List
	 * @@roseuid 4109E31D00BA
	 */
    protected List getTransferedFinInstitutionRecord(MainAccount l_mainAccount)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransferedFinInstitutionRecord" + 
        	"(MainAccount l_mainAccount) ";
        log.entering(STR_METHOD_NAME);
    	
    	//[����]  
    	//�U������Z�@@�փe�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()  
    	//�U������Z�@@�փe�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode()  
    	//�U������Z�@@�փe�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()  
    	//�U������Z�@@�փe�[�u��.�w��敪 = 'A'  
    	String l_strWhere = null;
		l_strWhere = "institution_code = ? and branch_code = ? " +
			"and account_code = ? and designate_div = ?";
        Object[] l_bindVars = 
            new Object[]
                {l_mainAccount.getInstitution().getInstitutionCode(),
        		l_mainAccount.getBranch().getBranchCode(),
        		l_mainAccount.getAccountCode(),	
        		WEB3DesignateDivDef.SALE_TURNOVER};
        
        List l_lisRows = null;
        try
		{
			l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
				TransferedFinInstitutionRow.TYPE, 
				l_strWhere, 
				null,
				l_bindVars);
		}
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    	return l_lisRows;
    }
    
    /**
	 * (create�U������Z�@@�֏��)<BR>
	 * �U������Z�@@�ւ̏����o���\���⍇�����׃I�u�W�F�N�g�Ɋi�[����B<BR>
	 * <BR>
	 * �P�j����:�U������Z�@@�փ��R�[�h.size() == 0 �̏ꍇ�A<BR>
	 * <BR>
	 * �P�|�P�j �o���⍇������.���Z�@@�փR�[�h = null<BR>
	 * <BR>
	 * �P�|�Q�j �o���⍇������.�U����x�X�R�[�h = null<BR>
	 * <BR>
	 * �P�|�R�j �o���⍇������.�a���敪 = null<BR>
	 * <BR>
	 * �P�|�S�j �o���⍇������.�U��������ԍ� = null<BR>
	 * <BR>
	 * �P�|�T�j �o���⍇������.�U����������`�l = null<BR>
	 * <BR>
	 * <BR>
	 * �Q�j����:�U������Z�@@�փ��R�[�h.size() != 0 �̏ꍇ�A<BR>
	 * <BR>
	 * �Q�|�P�j �o���⍇������.���Z�@@�փR�[�h = ����:�U������Z�@@�փ��R�[�h.get(0).get��s�R�[�h<BR>
	 * <BR>
	 * �Q�|�Q�j �o���⍇������.�U����x�X�R�[�h = ����:�U������Z�@@�փ��R�[�h.get(0).get�x�X�R�[�h<BR>
	 * <BR>
	 * �Q�|�R�j �o���⍇������.�a���敪 = ����:�U������Z�@@�փ��R�[�h.get(0).get�a���敪<BR>
	 * <BR>
	 * �Q�|�S�j �o���⍇������.�U��������ԍ� = ����:�U������Z�@@�փ��R�[�h.get(0).get�����ԍ�<BR>
	 * <BR>
	 * �Q�|�T�j �o���⍇������.�U����������`�l = ����:�U������Z�@@�փ��R�[�h.get(0).get�������`�l<BR>
	 * <BR>
	 * @@param l_lstTransferedFinInstitution - (�U������Z�@@�փ��R�[�h)
	 * @@param l_cashoutInqUnit - (�o���\���⍇������)
	 * @@roseuid 4109E31D00BA
	 */
    protected void createTransferedFinInstitutionInfo(List l_lstTransferedFinInstitution,
    	WEB3AioCashoutInqUnit l_cashoutInqUnit)
    {
        final String STR_METHOD_NAME = "createTransferedFinInstitutionInfo" + 
    	"(List l_lstTransferedFinInstitution, WEB3AioCashoutInqUnit l_cashoutInqUnit)";
        log.entering(STR_METHOD_NAME);
    
    	//�P�j����:�U������Z�@@�փ��R�[�h.size() == 0 �̏ꍇ
    	if(l_lstTransferedFinInstitution == null || l_lstTransferedFinInstitution.size() ==0)
    	{
    		//�P�|�P�j �o���⍇������.���Z�@@�փR�[�h = null
    		l_cashoutInqUnit.financialInstitutionCode = null;
    		
    		//�P�|�Q�j �o���⍇������.�U����x�X�R�[�h = null
    		l_cashoutInqUnit.transferBranchCode = null;
    		
    		//�P�|�R�j �o���⍇������.�a���敪 = null
    		l_cashoutInqUnit.transferAccountDiv = null;
    		
    		//�P�|�S�j �o���⍇������.�U��������ԍ� = null
    		l_cashoutInqUnit.transferAccountNumber = null;
    		
    		//�P�|�T�j �o���⍇������.�U����������`�l = null
    		l_cashoutInqUnit.transferAccountName = null;
    	}
    	//�Q�j����:�U������Z�@@�փ��R�[�h.size() != 0 �̏ꍇ
    	else
    	{
    		TransferedFinInstitutionRow l_transferedFinInstitutionRow = 
    			((TransferedFinInstitutionRow)l_lstTransferedFinInstitution.get(0));
			// �Q�|�P�j �o���⍇������.���Z�@@�փR�[�h = ����:�U������Z�@@�փ��R�[�h.get(0).get��s�R�[�h
    		l_cashoutInqUnit.financialInstitutionCode = 
    			l_transferedFinInstitutionRow.getFinInstitutionCode();
    		
			// �Q�|�Q�j �o���⍇������.�U����x�X�R�[�h = ����:�U������Z�@@�փ��R�[�h.get(0).get�x�X�R�[�h
    		l_cashoutInqUnit.transferBranchCode =
    			l_transferedFinInstitutionRow.getFinBranchCode();
    		
			// �Q�|�R�j �o���⍇������.�a���敪 = ����:�U������Z�@@�փ��R�[�h.get(0).get�a���敪
    		l_cashoutInqUnit.transferAccountDiv = 
    			l_transferedFinInstitutionRow.getFinSaveDiv();
    		
			// �Q�|�S�j �o���⍇������.�U��������ԍ� = ����:�U������Z�@@�փ��R�[�h.get(0).get�����ԍ�
    		l_cashoutInqUnit.transferAccountNumber =
    			l_transferedFinInstitutionRow.getFinAccountNo();
    		
			// �Q�|�T�j �o���⍇������.�U����������`�l = ����:�U������Z�@@�փ��R�[�h.get(0).get�������`�l
    		l_cashoutInqUnit.transferAccountName =
    			l_transferedFinInstitutionRow.getFinAccountName();
		}
    }
    
    /**
	 * (get�_�E�����[�h�t�@@�C��)<BR>
	 * �o���\���⍇���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
	 * <BR>
	 * �V�[�P���X�} �u�i�o���\���⍇���jget�_�E�����[�h�t�@@�C���v �Q��<BR>
	 * <BR>
	 * @@param WEB3AdminAioCashoutInqDownloadRequest - (���N�G�X�g�f�[�^)<BR>
	 *     �o���\���⍇���_�E�����[�h���N�G�X�g�I�u�W�F�N�g<BR>
	 * @@return WEB3AdminAioCashoutInqDownloadResponse - (�o���\���⍇���_�E�����[�h���X�|���X)<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 4109E31D00BA
	 */
    protected WEB3AdminAioCashoutInqDownloadResponse getDownloadFile(
    	WEB3AdminAioCashoutInqDownloadRequest l_request)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile" + 
        	"(WEB3AdminAioCashoutInqDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
    	
        //1.1 validate()
        l_request.validate();
        
    	//1.2 �Ǘ��҃C���X�^���X���擾����B
		WEB3Administrator l_web3Administrator = 
			WEB3Administrator.getInstanceFromLoginInfo();
		
        //1.3 validate����(String, boolean)(�Ǘ���::validate����)
        //�A�C�e���̒�`
        //�Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �hB0101�h 
        //is�X�V�F true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ, true);

        //1.4 validate���X����(String[]): 
        //�Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        Branch l_branch = null;
        long l_lngBranchId = 0L;
        //1.5 ���X�g�̃C���X�^���X�𐶐�����B
        long[] l_lngBranchIds = 
            new long[l_request.branchCode.length];
        
        //1.6 �،���ЃI�u�W�F�N�g���擾����B 
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.7 (*1) ���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f����Loop����
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            //1.7.1 ���X�C���X�^���X�𐶐�����B 
            //�m�����n 
            //�،���ЁF get�،����()�̖߂�l
            //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h                        
            try
            {
                l_branch = l_accountManager.getBranch(
                    l_institution, l_request.branchCode[i]);
            }
            catch (NotFoundException l_ex)
            {
                log.error("���X�C���X�^���X�𐶐�����:", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.7.2 ���XID���擾����B 
            l_lngBranchId = l_branch.getBranchId();    
                
            //1.7.3 ���XID�����X�g�ɒǉ�����B 
            //[����] 
            //arg0�F ���XID
            l_lngBranchIds[i] = l_lngBranchId;
        }
        	
        // 1.8 toArray()
        	
        // 1.9 �o���\���⍇�킹�_�E�����[�hCSV()
        // 1.9.1 create�L�[�w�b�_()
        // 1.9.2 create�J�����w�b�_()
        WEB3AdminAioCashoutInqDownloadCsv l_cashoutInqDownloadCsv = 
        	new WEB3AdminAioCashoutInqDownloadCsv();
         
        // 1.10 �f�[�^�擾����������𐶐�����B
        // �m�����n
        // ���XID�F ���XID�̔z��
        // ��n���F ���N�G�X�g�f�[�^.��n��
        // ������t�敪�F ���N�G�X�g�f�[�^.������t�敪
        // ���͋敪�F�@@���N�G�X�g�f�[�^.���͋敪
        // �ʉ݃R�[�h�F�@@null
        String l_strWhereClause = null;
        l_strWhereClause = this.createGetCondCharacterString(
            l_lngBranchIds, 
            l_request.deliveryDate,
            l_request.orderDiv,
            l_request.inputDiv, 
            null);

        // 1.11 �擾�����f�[�^�R���e�i�𐶐�����B
        // �m�����n
        // ���XID�F ���XID�̔z��
        // ��n���F ���N�G�X�g�f�[�^.��n��
        // ������t�敪�F ���N�G�X�g�f�[�^.������t�敪
        // ���͋敪�F�@@���N�G�X�g�f�[�^.���͋敪
        // �ʉ݃R�[�h�F�@@null
        Object l_bindVars[] = this.createGetCondDataContainer(
            l_lngBranchIds, 
            l_request.deliveryDate, 
            l_request.orderDiv,
            l_request.inputDiv, 
            null);
            
        //1.12  1.13 �����P�ʃe�[�u������A���R�[�h���擾����B
		//�m�����n 
		//arg0�F �����P��Row.TYPE 
		//arg1�F create�擾����������()�̖߂�l 
		//arg2�F "branch_id asc , received_date_time asc" 
		//arg3�F null 
		//arg4�F create�擾�����f�[�^�R���e�i()�̖߂�l 
        List l_lisRows = null;
        String l_strSort = " branch_id asc , received_date_time asc";
        try
        {
            l_lisRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,                    
                    l_strSort,
                    null,
                    l_bindVars);                                        
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        	
        //1.15 (*2)�擾�������R�[�h����Loop����
        int l_lisRowsSize = 0;
        if (l_lisRows != null)
        {
        	l_lisRowsSize = l_lisRows.size();
        }
        AioOrderUnitRow l_aioOrderUnitRow = null;
        for (int i = 0;i < l_lisRowsSize;i ++)
        {
        	l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(i);
        	//1.15.1 add���׍s( )
        	l_cashoutInqDownloadCsv.addRow();
        	
        	//1.15.2 set���X(int, String)
        	l_cashoutInqDownloadCsv.setBranch(
        		i, 
        		l_aioOrderUnitRow.getBranchId() + "");
        	
        	//1.15.3 set�ڋq(int, long)
        	l_cashoutInqDownloadCsv.setAccount(
        		i, 
        		l_aioOrderUnitRow.getAccountId());
        	
        	//1.15.4 set��������(int, Date)
        	l_cashoutInqDownloadCsv.setOrderDate(
        		i, 
        		l_aioOrderUnitRow.getReceivedDateTime());
        	
        	//1.15.5 set��n��(int, Date)
        	l_cashoutInqDownloadCsv.setDeliveryDate(
        		i, 
        		l_aioOrderUnitRow.getDeliveryDate());
        	
        	//1.15.6 set�o���z(int, double)
        	l_cashoutInqDownloadCsv.setCashoutAmount(
        		i, 
        		l_aioOrderUnitRow.getQuantity());
        	
        	//1.15.7 get������t�敪(long, String, String)
        	String l_strOrderAcceptType =
        		this.getOrderAcceptType(
        			l_aioOrderUnitRow.getOrderStatus().intValue(),
        			l_aioOrderUnitRow.getCancelType(), 
        			l_aioOrderUnitRow.getMqStatus());
        	
        	//1.15.8 set�������(int, String)
        	l_cashoutInqDownloadCsv.setOrderStatus(i, l_strOrderAcceptType);
        	
        	//1.15.9 get����敪(long, String)
        	String l_strCancelDivision = 
        		this.getCancelDivision(
        			l_aioOrderUnitRow.getOrderStatus().intValue(), 
        			l_aioOrderUnitRow.getCancelType());
        	
        	//1.15.10 set����敪(int, String)
        	l_cashoutInqDownloadCsv.setCancelDiv(i, l_strCancelDivision);
        	
        	//1.15.11 set�������(int, Date)
        	if(WEB3AioCancelDivDef.INITIAL_VALUE.equals(l_strCancelDivision))
        	{
        		l_cashoutInqDownloadCsv.setCancelDate(i, null);
        	}
        	else if(WEB3AioCancelDivDef.CANCElED.equals(l_strCancelDivision))
        	{
        		l_cashoutInqDownloadCsv.setCancelDate(
        			i, 
        			l_aioOrderUnitRow.getLastUpdatedTimestamp());
        	}
        	
        	//1.15.12 MainAccountImpl(arg0 : long)              
        	MainAccount l_mainAccount = null;
        	try
			{
				l_mainAccount = 
					l_accountManager.getMainAccount(
						l_aioOrderUnitRow.getAccountId());

		        //1.14 ArrayList�C���X�^���X�𐶐�����B
				//1.15.13 get�U������Z�@@�փ��R�[�h(�ڋq)
				List l_lisTransferedFinInstitutionRow = new ArrayList();
				l_lisTransferedFinInstitutionRow = this.getTransferedFinInstitutionRecord(l_mainAccount);

				if (l_lisTransferedFinInstitutionRow == null || l_lisTransferedFinInstitutionRow.size() == 0)
				{
					//1.15.14 set�U�����s�R�[�h(int, String)
					l_cashoutInqDownloadCsv.setTransferBankCode(i, null);

					//1.15.15 set�U����x�X�R�[�h(int, String)
					l_cashoutInqDownloadCsv.setTransferBranchCode(i, null);

					//1.15.16 set�a���敪(int, String)
					l_cashoutInqDownloadCsv.setDepositDiv(i, null);

					//1.15.17 set�U��������ԍ�(int, String)
					l_cashoutInqDownloadCsv.setTransferAccountNumber(i, null);

					//1.15.18 set�U���於�`�l(int, String)
					l_cashoutInqDownloadCsv.setTransferAccountName(i, null);
				}
				else
				{
					TransferedFinInstitutionRow l_transferedFinInstitutionRow =
						((TransferedFinInstitutionRow) l_lisTransferedFinInstitutionRow.get(0));
					//1.15.14 set�U�����s�R�[�h(int, String)
					l_cashoutInqDownloadCsv.setTransferBankCode(
						i, 
						l_transferedFinInstitutionRow.getFinInstitutionCode());

					//1.15.15 set�U����x�X�R�[�h(int, String)
					l_cashoutInqDownloadCsv.setTransferBranchCode(
						i, 
						l_transferedFinInstitutionRow.getFinBranchCode());

					//1.15.16 set�a���敪(int, String)
					l_cashoutInqDownloadCsv.setDepositDiv(
						i, 
						l_transferedFinInstitutionRow.getFinSaveDiv());

					//1.15.17 set�U��������ԍ�(int, String)	
					l_cashoutInqDownloadCsv.setTransferAccountNumber(
						i,
						l_transferedFinInstitutionRow.getFinAccountNo());

					//1.15.18 set�U���於�`�l(int, String)
					l_cashoutInqDownloadCsv.setTransferAccountName(
						i,
						l_transferedFinInstitutionRow.getFinAccountName());
				}

				//1.15.19 is�M�p�����J��(�ٍϋ敪 : String)
				WEB3GentradeMainAccount l_gentradeMainAcc = (WEB3GentradeMainAccount) l_mainAccount;
				boolean l_blnIsMarginAccountEstablished = l_gentradeMainAcc.isMarginAccountEstablished(
					WEB3GentradeRepaymentDivDef.DEFAULT);
				
				//1.15.20 getSubAccount(SubAccountTypeEnum)
				//�⏕�������擾����B  
				//[����]  
				//�⏕�����^�C�v�F�@@SubAccountTypeEnum.EQUITY_SUB_ACCOUNT 
				SubAccount l_subAccount = null;
				l_subAccount = l_gentradeMainAcc.getSubAccount(
					SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
				
				WEB3TPTradingPowerService l_tradingPowerService = 
					(WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
				//1.15.22 is�M�p�����J��( ) == true�̏ꍇ
				if (l_blnIsMarginAccountEstablished)
				{				
					//1.15.22.1 get���Y�]�͏��<�M�p�ڋq>(�⏕���� : �⏕����)
					WEB3GentradeSubAccount l_gentradeSubAccount = 
						(WEB3GentradeSubAccount)l_subAccount;
					WEB3TPTradingPowerCalcMargin l_tPTradingPowerCalcMargin = 
			        	l_tradingPowerService.getTradingPowerCalcMargin(l_gentradeSubAccount);
				
			        //1.15.22.2 set�M�p�ڋq�]�͏��(���Y�]�͏��<�M�p�ڋq>, int)
			        l_cashoutInqDownloadCsv.setTPTradingPowerCalcMargin(
			        	l_tPTradingPowerCalcMargin, i);
				}
				//1.15.21 is�M�p�����J��( ) == false�̏ꍇ
				else
				{	
					//1.15.21.1 get���Y�]�͏��<�����ڋq>(�⏕���� : �⏕����)
					WEB3GentradeSubAccount l_gentradeSubAccount = 
						(WEB3GentradeSubAccount)l_subAccount;
					WEB3TPTradingPowerCalcEquity l_tPTradingPowerCalcEquity = 
			        	l_tradingPowerService.getTradingPowerCalcEquity(l_gentradeSubAccount);
				
			        //1.15.21.2 set�����ڋq�]�͏��(���Y�]�͏��<�����ڋq>, int)
			        l_cashoutInqDownloadCsv.setTPTradingPowerCalcEquity(
			        	l_tPTradingPowerCalcEquity, i);
				}
			}
			catch (NotFoundException l_ex)
			{
				log.error("�،���ЃC���X�^���X�𐶐�����", l_ex);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(), l_ex);
			}
		}
        //1.16 getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_cashoutInqDownloadCsv.getCsvFileLines();
        
        //1.17 createResponse( )
        WEB3AdminAioCashoutInqDownloadResponse l_response = 
        	(WEB3AdminAioCashoutInqDownloadResponse)l_request.createResponse();
 
        //1.18 �i*5�j�v���p�e�B�Z�b�g
        l_response.downloadFile = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
		log.exiting(STR_METHOD_NAME);
		return l_response;
    }

    /**
     * (set�o���]��)<BR>
     * �o���]�͂̌v�Z���A�Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@����:�o���⍇�����ׂ̗v�f���ALoop�������s���B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�g���A�J�E���g�}�l�[�W��.get�ڋq�i�j�ɂČڋq�I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@����:�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@�o���⍇������[index].���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@�o���⍇������[index].�����R�[�h<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�g���A�J�E���g�}�l�[�W��.get�⏕�����i�j�ɂĕ⏕�����I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@�ڋq�I�u�W�F�N�g����擾��������ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * <BR>
     * �@@�P�|�R�j�@@����]�̓T�[�r�XImpl.get�o���\�z�`�o�����͉�ʕ\���p�`()�@@�ɂďo���\�z���擾<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@get�⏕�����i�j�Ŏ擾�����l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�@@�o���⍇������[index].��n��<BR>
     * <BR>
     * �@@�P�|�S�j�@@�o���⍇������[index].�o���]��=�P�|�R�j�Ŏ擾�����l<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_aioCashoutInqUnit - (�o���⍇�����׈ꗗ)<BR>
     * �o���⍇�����׈ꗗ<BR>
     * @@throws WEB3BaseException
     */
    protected void setPaymentPower(
        String l_strInstitutionCode,
        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setPaymentPower(String, WEB3AioCashoutInqUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_aioCashoutInqUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        int l_intAioCashoutInqUnit = l_aioCashoutInqUnit.length;

        try
        {        
            //�P�j�@@����:�o���⍇�����ׂ̗v�f���ALoop�������s���B
            for (int i = 0; i < l_intAioCashoutInqUnit; i++)
            {
                //�@@�P�|�P�j�@@�g���A�J�E���g�}�l�[�W��.get�ڋq�i�j�ɂČڋq�I�u�W�F�N�g�̎擾
                //�@@�@@�@@�@@�@@�@@[����]
                //�@@�@@�@@�@@�@@�@@�@@�E�@@����:�،���ЃR�[�h
                //�@@�@@�@@�@@�@@�@@�@@�E�@@�o���⍇������[index].���X�R�[�h
                //�@@�@@�@@�@@�@@�@@�@@�E�@@�o���⍇������[index].�����R�[�h
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                MainAccount l_mainAccount =
                    l_web3GentradeAccountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_aioCashoutInqUnit[i].branchCode,
                        l_aioCashoutInqUnit[i].accountCode);

                //�P�|�Q�j�@@�g���A�J�E���g�}�l�[�W��.get�⏕�����i�j�ɂĕ⏕�����I�u�W�F�N�g�̎擾
                //�@@�@@�@@�@@�@@�@@[����]
                //�@@�@@�@@�@@�@@�@@�@@�E�@@�ڋq�I�u�W�F�N�g����擾��������ID
                //�@@�@@�@@�@@�@@�@@�@@�E�@@SubAccountTypeEnum.EQUITY_SUB_ACCOUNT
                SubAccount l_subAccount =
                    l_web3GentradeAccountManager.getSubAccount(
                        l_mainAccount.getAccountId(),
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                //�@@�P�|�R�j�@@����]�̓T�[�r�XImpl.get�o���\�z�`�o�����͉�ʕ\���p�`()�@@�ɂďo���\�z���擾
                //�@@�@@�@@�@@�@@�@@[����]
                //�@@�@@�@@�@@�@@�@@�@@�E�@@get�⏕�����i�j�Ŏ擾�����l
                //�@@�@@�@@�@@�@@�@@�@@�E�@@�o���⍇������[index].��n��
                WEB3TPTradingPowerService l_tradingPowerService = 
                    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

                double l_dblPaymentTradingPower =
                    l_tradingPowerService.getPaymentTradingPowerAioCashoutInput(
                        (WEB3GentradeSubAccount)l_subAccount,
                        l_aioCashoutInqUnit[i].deliveryDate);

                //�@@�P�|�S�j�@@�o���⍇������[index].�o���]��=�P�|�R�j�Ŏ擾�����l
                l_aioCashoutInqUnit[i].paymentPower =
                    WEB3StringTypeUtility.formatNumber(l_dblPaymentTradingPower);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
