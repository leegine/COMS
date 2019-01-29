head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐����ʒm�T�[�r�XImpl(WEB3AccTransChangeRequestNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
Revesion History : 2004/10/26 ���E(���u) ���r���[
Revesion History : 2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2005/05/06 �����F�i���u�j����e�X�g��Q�[U02004�Ή�
Revesion History : 2007/01/31 �Ԑi (���u) �d�l�ύX ���f�� 691
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.aio.data.HostTransferReceiptRow;
import webbroker3.aio.message.WEB3AccTransChangeRequestNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3DepositTypeDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RemarkCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�U�֐����ʒm�T�[�r�XImpl)<BR>
 * �U�֐����ʒm�T�[�r�X�����N���X<BR>
 *
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyServiceImpl
    implements WEB3AccTransChangeRequestNotifyService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyServiceImpl.class);

    /**
     * �U�֐����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�U�֐����ʒm�j�U�֐����ʒm�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     *
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C2C8C01A7
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // �P�j �U�֐����ʒm�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����
            WEB3AccTransChangeRequestNotifyTransactionCallback l_transactionCallback =
                new WEB3AccTransChangeRequestNotifyTransactionCallback();

            // �Q�j�@@�N�G���v���Z�b�T���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �R�jDB�g�����U�N�V�������������{����B
            // [doTransaction()�Ɏw�肷�����]
            // �g�����U�N�V���������F�@@TX_CREATE_NEW
            // �g�����U�N�V�����R�[���o�b�N�F�@@�U�֐����ʒmTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3AccTransChangeRequestNotifyResponse l_response =
            (WEB3AccTransChangeRequestNotifyResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�U�֐����ʒmTransactionCallback�N���X)
     */
    public class WEB3AccTransChangeRequestNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(
                WEB3AccTransChangeRequestNotifyTransactionCallback.class);

        /**
         * (�U�֐����ʒmTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@return WEB3AccTransChangeRequestNotifyTransactionCallback<BR>
         * @@roseuid 413C253A00EB
         */
        public WEB3AccTransChangeRequestNotifyTransactionCallback()
        {

        }

        /**
         * �U�֐����ʒm���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�U�֐����ʒm�jprocess�v�Q��<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C2C2602C0
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            l_log.entering(STR_METHOD_NAME);

            // 1.1)  �U�֓��͒ʒm�e�[�u���Ǎ���
            String l_strWhere = "request_code = ? " +
                                "and status = ?";
            // ���u�� U01504�̎b��Ή� start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // ���u�� U01504�̎b��Ή� end

            Object[] l_objWhere = { WEB3HostRequestCodeDef.AIO_TRANSFER_INPUT_NOTIFY ,
                WEB3StatusDef.NOT_DEAL };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    HostTransferReceiptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            int l_intResultLength = 0;
            if (l_lisSearchResult != null && l_lisSearchResult.size() != 0)
            {
                l_intResultLength = l_lisSearchResult.size();
            }

            HostTransferReceiptParams l_hostTransferReceiptParams = null;

            // 1.2) �擾�������R�[�h����Loop����
            for (int i = 0; i < l_intResultLength; i++)
            {
                // �U�֓��͒ʒm�L���[Params�̎擾
                HostTransferReceiptRow l_hostTransferAcceptRow =
                    (HostTransferReceiptRow)l_lisSearchResult.get(i);
                l_hostTransferReceiptParams =
                    new HostTransferReceiptParams(l_hostTransferAcceptRow);

                boolean l_blnIsFailProcess = false;
                Map l_map = new Hashtable();

                try
                {
                    // 1.2.1) ������ʂ��擾����B
                    // �m�����n
                    // �a����敪�F �U�֓��͒ʒm�L���[Params.�a����敪
                    // �E�v�R�[�h�F �U�֓��͒ʒm�L���[Params.�E�v�R�[�h
                    log.debug("�U�֓��͒ʒm�L���[Params.�a����敪 = " +
                            l_hostTransferReceiptParams.getDepositTypeDiv());
                    log.debug("�U�֓��͒ʒm�L���[Params.�E�v�R�[�h = " +
                            l_hostTransferReceiptParams.getRemarkCode());

                    OrderTypeEnum l_orderType = this.getOrderType(
                        l_hostTransferReceiptParams.getDepositTypeDiv(),
                        l_hostTransferReceiptParams.getRemarkCode());

                    log.debug("������� = " + l_orderType);

                    // ���u�� U01504�̎b��Ή� start
                    WEB3AccTransChangeRequestNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AccTransChangeRequestNotifyNormalTransactionCallback(
                            l_hostTransferReceiptParams,
                            l_orderType);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    //1.2.2)
//                    if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
//                        OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
//                        OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType) ||
//                        OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
//                    {
//                        AssetTransferTypeEnum l_assetTransferType = null;
//                        //�@@�E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A2�i�o���j
//                        //1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�))
//                        //1017(���̑��U�֒���(�a���������X))
//                        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderType) ||
//                            OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderType))
//                        {
//                            l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;
//                        }
//                        //�E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A1�i�����j
//                        //1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����))
//                        //1018(���̑��U�֒���(X����a�����))
//                        else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderType) ||
//                                OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderType))
//                        {
//                            l_assetTransferType = AssetTransferTypeEnum.CASH_IN;
//                        }
//                        // 1.2.2.1) �V�K�ב֕ۏ؋��U�֒����̓o�^���s���B
//                        // [����]
//                        // �U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
//                        // ������ʁF get�������()�̖߂�l
//                        // �U�փ^�C�v�F
//                        //  �E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A2�i�o���j
//                        //      1011(�ב֕ۏ؋��U�֒���(�a���������ב֕ۏ؋�))
//                        //      1017(���̑��U�֒���(�a���������X))
//                        //  �E������ʂ��ȉ��̂����ꂩ�̏ꍇ�A1�i�����j
//                        //      1012(�ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a�����))
//                        //      1018(���̑��U�֒���(X����a�����))
//                        this.createOrder(
//                            l_hostTransferReceiptParams,
//                            l_orderType,
//                            l_assetTransferType);
//                    }
//                    //1.2.3
//                    else
//                    {
//                        //1.2.3.1 �V�K���������̓o�^���s���B
//                        //�U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
//                        //������ʁF get�������()�̖߂�l
//                        //�U�փ^�C�v�F 1�i�����j
//                        this.createOrder(
//                            l_hostTransferReceiptParams,
//                            l_orderType,
//                            AssetTransferTypeEnum.CASH_IN);
//
//                        //1.2.3.2 �V�K�o�������̓o�^���s���B
//                        //[����]
//                        //�U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
//                        //������ʁF get�������()�̖߂�l
//                        //�U�փ^�C�v�F 2�i�o���j
//                        this.createOrder(
//                            l_hostTransferReceiptParams,
//                            l_orderType,
//                            AssetTransferTypeEnum.CASH_OUT);
//                    }
                    // ���u�� U01504�̎b��Ή� end
                }
                catch (WEB3BaseException l_ex)
                {
                    l_log.debug("__an WEB3BaseException__", l_ex);
                    l_blnIsFailProcess = true;
                }
                // ���u�� U01504�̎b��Ή� start
                catch (Exception l_ex)
                {
                    l_log.debug("__an Exception__", l_ex);
                    l_blnIsFailProcess = true;
                }
                // ���u�� U01504�̎b��Ή� end

                // ���u�� U01504�̎b��Ή� start
//                if (l_blnIsFailProcess)
//                {
//                    // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
//                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
//                    l_log.debug("__an WEB3BaseException__ ");
//                }
//                else
//                {
//                    // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
//                    l_map.put("status", WEB3StatusDef.DEALT);
//                }
//
//                // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
//                String l_strUpdateWhere = " request_code = ? "
//                                   + " and institution_code = ? "
//                                   + " and branch_code = ? "
//                                   + "and account_code = ? "
//                                   + "and order_request_number = ? ";
//
//                String[] l_strArrayUpdateParams = {
//                    l_hostTransferReceiptParams.getRequestCode(),
//                    l_hostTransferReceiptParams.getInstitutionCode(),
//                    l_hostTransferReceiptParams.getBranchCode(),
//                    l_hostTransferReceiptParams.getAccountCode(),
//                    l_hostTransferReceiptParams.getOrderRequestNumber()
//                };
//
//                // 1.2.4�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
//                l_queryProcessor.doUpdateAllQuery(
//                    HostTransferReceiptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_strArrayUpdateParams,
//                    l_map);

                if (l_blnIsFailProcess)
                {
                    // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    // �����Ώۂ̐U�֓��͒ʒm�e�[�u��.�����敪��ݒ�p
                    String l_strUpdateWhere = "rowid = ? ";

                    String[] l_strArrayUpdateParams = {l_hostTransferReceiptParams.getRowid()};

                    // 1.2.4�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
                    l_queryProcessor.doUpdateAllQuery(
                        HostTransferReceiptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
                // ���u�� U01504�̎b��Ή� end

            }

            l_log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get�������)<BR>
         * ������ʂ��擾����B<BR>
         * <BR>
         * �P�j <BR>
         * ����.�a����敪 = 01�i�a����j and <BR>
         * ����.�E�v�R�[�h = 01�i�M�p�ۏ؋��j <BR>
         * <BR>
         * �̏ꍇ�A1006�i�U�֒����i�M�p�ۏ؋�����a����j�j��ԋp����B <BR>
         * <BR>
         * �Q�j <BR>
         * ����.�a����敪 = 01�i�a����j and <BR>
         * ����.�E�v�R�[�h = 72�i����؋����j <BR>
         * <BR>
         * �̏ꍇ�A1008�i�U�֒����i����؋�������a����j�j��ԋp����B <BR>
         * <BR>
         * �R�j <BR>
         * �@@ ����.�a����敪 = 01�i�a����j and <BR>
         * ����.�E�v�R�[�h = 86�i�ב֕ۏ؋��j <BR>
         * <BR>
         * �̏ꍇ�A1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j��ԋp����B <BR>
         * <BR>
         * �S�j <BR>
         * ����.�a����敪 = 04�i�ۏ؋��j <BR>
         * <BR>
         * �̏ꍇ�A1005�i�U�֒����i�a�������M�p�ۏ؋��j�j��ԋp����B <BR>
         * <BR>
         * �T�j <BR>
         * ����.�a����敪 = 05�i����؋����j <BR>
         * <BR>
         * �̏ꍇ�A1007�i�U�֒����i�a������犔��؋����j�j��ԋp����B <BR>
         * <BR>
         * �U�j <BR>
�@@       * ����.�a����敪 != 01�i�a����j and <BR>
         * ����.�E�v�R�[�h = 86�i�ב֕ۏ؋��j <BR>
         * <BR>
         * �̏ꍇ�A1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j��ԋp����B <BR>
         * <BR>
         * �V�j
         * ����.�a����敪 = 01�i�a����j and <BR>
         * ����.�E�v�R�[�h = (*)�̂����ꂩ <BR>
         * <BR>
         * �̏ꍇ�A1018�i���̑��U�֒����iX����a����j�j��ԋp����B <BR>
         * <BR>
         * �W�j <BR>
         * ����.�a����敪 != 01�i�a����j and <BR>
         * ����.�E�v�R�[�h = (*)�̂����ꂩ <BR>
         * <BR>
         * �̏ꍇ�A1017�i���̑��U�֒����i�a�������X�j�j��ԋp����B <BR>
         * <BR>
         * (*)���̑��U�֒����ɊY������E�v�R�[�h <BR>
         * <BR>
         * 02�F�i���j�ϑ��ۏ؋� <BR>
         * 03�F�[������ <BR>
         * 06�F���`������ <BR>
         * 07�F�i�ہj�����Ǘ��� <BR>
         * 08�F�i�O���j�����Ǘ��� <BR>
         * 09�F�i���j�����Ǘ��� <BR>
         * 10�F�i���敨�j�ϑ��ۏ؋� <BR>
         * 11�F�i�����敨�j�ϑ��ۏ؋� <BR>
         * 14�F�i�I�v�V�����j�ϑ��ۏ؋� <BR>
         * 19�F�i�����I�v�V�����j�ϑ��ۏ؋� <BR>
         * 24�F��s�U���萔�� <BR>
         * 42�F�X�[�p�[G�i�p���j���j <BR>
         * 43�F�X�[�p�[G�i�p���j�Ηj <BR>
         * 44�F�X�[�p�[G�i�p���j���j <BR>
         * 45�F�X�[�p�[G�i�p���j�ؗj <BR>
         * 46�F�X�[�p�[G�i�p���j���j <BR>
         * 47�F�����~ 1���� <BR>
         * 48�F�����~ 3���� <BR>
         * 49�F�����~ 6���� <BR>
         * 50�F�����~ 1�N <BR>
         * 52�F�������t�@@���h <BR>
         * 53�F�i�ݓ��j�����Ǘ��� <BR>
         * 54�F�������t�@@���h�L���b�V���O <BR>
         * 55�FMMF�L���b�V���O <BR>
         * 71�F�i�敨�I�v�V���� ���؁j <BR>
         * 73�F�i�敨�I�v�V���� ���؁j <BR>
         * 74�F�i�敨�I�v�V���� ���v���o�j <BR>
         * 93�F���̑��a��� <BR>
         * 97�F�O�ݐU�� <BR>
         * 98�F�E�v���͓��e �p��03 <BR>
         * 99�FBLANK <BR>
         * <BR>
         * @@param l_strDepositDivision - (�a����敪)<BR>
         * @@param l_strRemarkCode - (�E�v�R�[�h)<BR>
         * @@return OrderTypeEnum <BR>
         * @@throws WEB3BaseException
         * @@roseuid 413D3A1002D6
         */
        protected OrderTypeEnum getOrderType(
            String l_strDepositDivision,
            String l_strRemarkCode)
                throws WEB3BaseException
        {
            String STR_METHOD_NAME = "getOrderType(" +
                    "String l_strDepositDivision," +
                    "String l_strRemarkCode)";
            l_log.entering(STR_METHOD_NAME);

            //�P�j����.�a����敪 = 01�i�a����j and
            //   ����.�E�v�R�[�h = 01�i�M�p�ۏ؋��j
            //   �̏ꍇ�A1006�i�U�֒����i�M�p�ۏ؋�����a����j�j��ԋp����B
            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && WEB3RemarkCodeDef.MARGIN_GUARANTEE.equals(l_strRemarkCode))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT;  //1006
            }

            //�Q�j����.�a����敪 = 01�i�a����j and
            //   ����.�E�v�R�[�h = 72�i����؋����j
            //   �̏ꍇ�A1008�i�U�֒����i����؋�������a����j�j��ԋp����B

            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && (WEB3RemarkCodeDef.STOCK_FUTURES_MARGIN.equals(l_strRemarkCode)))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;    //1008
            }

            //�R�j����.�a����敪 = 01�i�a����j and
            //   ����.�E�v�R�[�h = 86�i�ב֕ۏ؋��j
            //   �̏ꍇ�A1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j��ԋp����B
            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && (WEB3RemarkCodeDef.EXCHANGE_GUARANTEE.equals(l_strRemarkCode)))
            {
                log.debug("1012�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j��ԋp����");
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;  //1012
            }

            //�S�j����.�a����敪 = 04�i�ۏ؋��j
            //   �̏ꍇ�A1005�i�U�֒����i�a�������M�p�ۏ؋��j�j��ԋp����B
            if(WEB3DepositTypeDivDef.GUARANTEE.equals(l_strDepositDivision))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;//1005
            }

            //�T�j����.�a����敪 = 05�i����؋����j
            //   �̏ꍇ�A1007�i�U�֒����i�a������犔��؋����j�j��ԋp����B
            if(WEB3DepositTypeDivDef.STOCK_FUTURES_MARGIN.equals(l_strDepositDivision))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;    //1007
            }

            //�U�j����.�a����敪 != 01�i�a����j and
            //    ����.�E�v�R�[�h = 86�i�ב֕ۏ؋��j
            //   �̏ꍇ�A1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j��ԋp����B
            if(!WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && (WEB3RemarkCodeDef.EXCHANGE_GUARANTEE.equals(l_strRemarkCode)))
            {
                log.debug("1011�i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j��ԋp����");
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;  //1011
            }

            //�V�j����.�a����敪 = 01�i�a����j and ����.�E�v�R�[�h = (*)�̂����ꂩ
            //�̏ꍇ�A1018�i���̑��U�֒����iX����a����j�j��ԋp����B
            if(WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && isRemarkCode(l_strRemarkCode))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.FROM_OTHER_TRANSFER;  //1018
            }

            //�W�j����.�a����敪 != 01�i�a����j and ����.�E�v�R�[�h = (*)�̂����ꂩ
            //�̏ꍇ�A1017�i���̑��U�֒����i�a�������X�j�j��ԋp����B
            if(!WEB3DepositTypeDivDef.FROM_DEPOSIT.equals(l_strDepositDivision)
                && isRemarkCode(l_strRemarkCode))
            {
                l_log.exiting(STR_METHOD_NAME);
                return OrderTypeEnum.TO_OTHER_TRANSFER;  //1017
            }
            l_log.exiting(STR_METHOD_NAME);
            return null;
        }
       /**
        * is���̑��U�֒����ɊY������E�v�R�[�h <BR>
        * <BR>
        * �E�v�R�[�h = (*)�̂����ꂩ �̏ꍇ�Atrue��ԋp���� <BR>
        * �ȊO�̏ꍇ�Afalse��ԋp���� <BR>
        * <BR>
        * (*)���̑��U�֒����ɊY������E�v�R�[�h <BR>
        * <BR>
        * 02�F�i���j�ϑ��ۏ؋� <BR>
        * 03�F�[������ <BR>
        * 06�F���`������ <BR>
        * 07�F�i�ہj�����Ǘ��� <BR>
        * 08�F�i�O���j�����Ǘ��� <BR>
        * 09�F�i���j�����Ǘ��� <BR>
        * 10�F�i���敨�j�ϑ��ۏ؋� <BR>
        * 11�F�i�����敨�j�ϑ��ۏ؋� <BR>
        * 14�F�i�I�v�V�����j�ϑ��ۏ؋� <BR>
        * 19�F�i�����I�v�V�����j�ϑ��ۏ؋� <BR>
        * 24�F��s�U���萔�� <BR>
        * 42�F�X�[�p�[G�i�p���j���j <BR>
        * 43�F�X�[�p�[G�i�p���j�Ηj <BR>
        * 44�F�X�[�p�[G�i�p���j���j <BR>
        * 45�F�X�[�p�[G�i�p���j�ؗj <BR>
        * 46�F�X�[�p�[G�i�p���j���j <BR>
        * 47�F�����~ 1���� <BR>
        * 48�F�����~ 3���� <BR>
        * 49�F�����~ 6���� <BR>
        * 50�F�����~ 1�N <BR>
        * 52�F�������t�@@���h <BR>
        * 53�F�i�ݓ��j�����Ǘ��� <BR>
        * 54�F�������t�@@���h�L���b�V���O <BR>
        * 55�FMMF�L���b�V���O <BR>
        * 71�F�i�敨�I�v�V���� ���؁j <BR>
        * 73�F�i�敨�I�v�V���� ���؁j <BR>
        * 74�F�i�敨�I�v�V���� ���v���o�j <BR>
        * 93�F���̑��a��� <BR>
        * 97�F�O�ݐU�� <BR>
        * 98�F�E�v���͓��e �p��03 <BR>
        * 99�FBLANK <BR>
        * <BR>
        * <BR>
        * @@param l_strRemarkCode (�E�v�R�[�h) <BR>
        * @@return boolean <BR>
        */
        private boolean isRemarkCode(String l_strRemarkCode)
        {
            String[] l_strRemarkCodes = {
                    WEB3RemarkCodeDef.BIZ_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.STOCK_REF,
                    WEB3RemarkCodeDef.NAME_TRANSFER,
                    WEB3RemarkCodeDef.SAFE_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.FOREIGN_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.CASH_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.BOND_FUTURE_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.EQUITY_FUTURE_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.OPTION_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.STOCK_BOND_OPTION_CONSIGN_GUARANTEE,
                    WEB3RemarkCodeDef.BANK_TRANSFER_COMMISSIONFEE,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_MONDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_TUESDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_WEDNESDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_THURSDAY,
                    WEB3RemarkCodeDef.SUPER_MARKET_G_CONTINUE_FRIDAY,
                    WEB3RemarkCodeDef.SAVING_ONE_MONTH,
                    WEB3RemarkCodeDef.SAVING_THREE_MONTHS,
                    WEB3RemarkCodeDef.SAVING_SIX_MONTHS,
                    WEB3RemarkCodeDef.SAVING_ONE_YEAR,
                    WEB3RemarkCodeDef.MEDIUM_TERM_NATIONAL_BONDS_FUNDS,
                    WEB3RemarkCodeDef.RUITO_ACCOUNT_MANAGE,
                    WEB3RemarkCodeDef.MEDIUM_TERM_NATIONAL_BONDS_FUNDS_CASHING,
                    WEB3RemarkCodeDef.MMF_CASHING,
                    WEB3RemarkCodeDef.FUTURES_OPTION_TOKYO_STOCK_EXCHANGE,
                    WEB3RemarkCodeDef.FUTURES_OPTION_NAGOYA_STOCK_EXCHANGE,
                    WEB3RemarkCodeDef.FUTURES_OPTION_PROFIT_PAY,
                    WEB3RemarkCodeDef.DEFAULT_FROM_DEPOSIT,
                    WEB3RemarkCodeDef.FOREIGN_TRANSFER,
                    WEB3RemarkCodeDef.REMARK_INPUT_REQUEST_CONTINUE_THREE,
                    WEB3RemarkCodeDef.BLANK
                    };

            for (int i = 0; i < l_strRemarkCodes.length; i++)
            {
                if (l_strRemarkCodes[i].equals(l_strRemarkCode))
                {
                    return true;
                }
            }
            return false;
        }

        /**
         * (create����)<BR>
         * �U�֒����𐶐�����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�U�֐����ʒm�jcreate�����v �Q��<BR>
         * @@param l_hostTransferReceiptParams - (�U�֓��͒ʒm�L���[Params�I�u�W�F�N�g)<BR>
         * @@param l_orderType - (�������)<BR>
         * @@param l_changeType - (�U�փ^�C�v)<BR>
         * @@throws WEB3BaseException
         * @@roseuid 413D3C370036
         */
        protected void createOrder(
            HostTransferReceiptParams l_hostTransferReceiptParams,
            OrderTypeEnum l_orderType,
            AssetTransferTypeEnum l_changeType)
                throws WEB3BaseException
        {
            String STR_METHOD_NAME = "createOrder(" +
                "HostTransferReceiptParams l_hostTransferReceiptParams," +
                "OrderTypeEnum l_orderType," +
                "AssetTransferTypeEnum l_changeType)";

            l_log.entering(STR_METHOD_NAME);

            // 1) �V�K�����̓o�^���s���B
            // [����]
            // �U�֓��͒ʒm�L���[Params�F �U�֓��͒ʒm�L���[Params�I�u�W�F�N�g
            // ������ʁF ����.�������
            // �U�փ^�C�v�F ����.�U�փ^�C�v
            WEB3AccTransChangeRequestNotifyUnitService l_notifyUnitService =
                (WEB3AccTransChangeRequestNotifyUnitService)Services.getService(
                    WEB3AccTransChangeRequestNotifyUnitService.class);
            long l_lngOrderID = l_notifyUnitService.submitOrder(
                    l_hostTransferReceiptParams,
                    l_orderType,
                    l_changeType);
            log.debug("l_lngOrderID = " + l_lngOrderID);
            // 2) �����P�ʂ��擾����B
            // (*�z���1�Ԗڂ̗v�f���擾����j
            // [����]
            // ����ID�F submit����()�̖߂�l
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderID);
            AioOrderUnit l_orderUnit = (AioOrderUnit) l_orderUnits[0];

            // 3�j�U�֎�tDB�X�V�������s���B
            // [����]
            // �����P�ʁF �����P�ʃI�u�W�F�N�g
            // �G���[�R�[�h�F "0000"�i����j
            // ��t�ʒm�敪�F "1"�i��t�����j
            WEB3AccTransChangeAcceptUnitService l_acceptUnitService =
                (WEB3AccTransChangeAcceptUnitService) Services.getService(
                    WEB3AccTransChangeAcceptUnitService.class);

            l_acceptUnitService.execute(
                l_orderUnit,
                WEB3ErrorReasonCodeDef.NORMAL,
                WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);

            // 4) �U�֊��������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
            // [����]
            // �����P�ʁF �����P�ʃI�u�W�F�N�g
            WEB3AccTransChangeCompleteUnitService l_completeUnitService =
                (WEB3AccTransChangeCompleteUnitService) Services.getService(
                WEB3AccTransChangeCompleteUnitService.class);
            l_completeUnitService.completeChange(l_orderUnit);

            // 5) �⏕�����I�u�W�F�N�g���擾����B
            // �m�����n
            // ����ID�F �����P��.����ID
            // �⏕����ID�F �����P��.�⏕����ID
            WEB3GentradeAccountManager l_accManage =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = l_accManage.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                l_log.error("�⏕�����I�u�W�F�N�g���擾����:",l_ex);
                l_log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }

            if(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                l_subAccount.getSubAccountType()))
            {
                //=========remain zhou-yong NO.1 begin ===========

                // 6) �]�͍Čv�Z(�⏕���� : �⏕����)
                //�A�C�e���̒�`
                //�]�͂̍X�V���s���B
                //[����]
                //�⏕�����F get�⏕����()�̖߂�l
                WEB3TPTradingPowerService l_service =
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);

                WEB3GentradeSubAccount l_gentradeSubAccount =
                    (WEB3GentradeSubAccount)l_subAccount;

                l_service.reCalcTradingPower(l_gentradeSubAccount);

                //=========remain zhou-yong NO.1 end ===========

            }
            l_log.exiting(STR_METHOD_NAME);
        }
    }
}
@
