head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������ʒm���C���T�[�r�XImpl(WEB3EquityChangeCancelNotifyMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 �������F(SRA) �V�K�쐬
                   2005/01/05 �����a��(SRA) �������b�N�Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptRow;
import webbroker3.equity.message.WEB3EquityChangeCancelNotifyMainRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyMainService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveChangeEventService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyChangeUnitService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;

/**
 * �i������������ʒm���C���T�[�r�X�����N���X�j�B
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityChangeCancelNotifyMainServiceImpl implements WEB3EquityChangeCancelNotifyMainService
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeCancelNotifyMainServiceImpl.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityChangeCancelNotifyMainServiceImpl()
    {
    }

    /**
     * ������������ʒm���C���T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i������������ʒm���C���T�[�r�X�j��������ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3EquityChangeCancelNotifyMainServiceImpl�Fexecute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityChangeCancelNotifyMainRequest l_changeCancelNotifyRequest =
            (WEB3EquityChangeCancelNotifyMainRequest)l_request;

        // 1.1. validate()
        l_changeCancelNotifyRequest.validate();

        // 1.2. �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_changeCancelNotifyRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityChangeCancelNotifyMainServiceImpl(
            l_changeCancelNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
//        // 1.2. getDefaultProcessor()
//        try
//        {
//            Processors.getDefaultProcessor();
//        }
//        catch (DataFindException l_dfe)
//        {
//            log.error(l_dfe.getMessage(), l_dfe);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dfe.getMessage(), l_dfe);
//        }
//        catch (DataNetworkException l_dne)
//        {
//            log.error(l_dne.getMessage(), l_dne);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dne.getMessage(), l_dne);
//        }
//
//        // 1.3. ������������ʒm���C��TransactionCallback()
//        WEB3EquityChangeCancelNotifyMainTransactionCallback l_transactionCallBack =
//            new WEB3EquityChangeCancelNotifyMainTransactionCallback();
//
//        // 1.4. set���ʃR�[�h�v���t�B�N�X�ꗗ()
//        l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_changeCancelNotifyRequest.orderRequestNumberPrefixGroup);
//
//        // 1.5. doTransaction()
//        try
//        {
//            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
//            l_queryProcesser.doTransaction(
//                QueryProcessor.TX_CREATE_NEW,
//                l_transactionCallBack);
//        }
//        catch (DataFindException l_dfe)
//        {
//            log.error(l_dfe.getMessage(),l_dfe);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dfe.getMessage(), l_dfe);
//        }
//        catch (DataNetworkException l_dne)
//        {
//            log.error(l_dne.getMessage(),l_dne);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dne.getMessage(), l_dne);
//        }
//        catch (DataCallbackException l_dce)
//        {
//            log.error(l_dce.getMessage(),l_dce);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dce.getMessage(), l_dce);
//        }
//        catch (DataQueryException l_dqe)
//        {
//            log.error(l_dqe.getMessage(),l_dqe);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_dqe.getMessage(), l_dqe);
//        }
//
//        log.exiting(STR_METHOD_NAME);
//        return l_request.createResponse();
//    }
//
//    /**
//     * �i������������ʒm���C��TransactionCallback)<BR>
//     * <BR>
//     * �g�����U�N�V�������������{��������N���X�B<BR>
//     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
//     */
//    public class WEB3EquityChangeCancelNotifyMainTransactionCallback implements TransactionCallback
//    {
//        /**
//         * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * �R���X�g���N�^�B<BR>
//         */
//        public WEB3EquityChangeCancelNotifyMainTransactionCallback()
//        {
//        }
//
//        /**
//         * �iset�����̎��ʃR�[�h�v���t�B�N�X�ꗗ�j<BR>
//         * <BR>
//         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
//         * @@params String[] - ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] l_orderRequestNumberPrefixGroup)
//        {
//            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * �iget�����̎��ʃR�[�h�v���t�B�N�X�ꗗ�j<BR>
//         * <BR>
//         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B<BR>
//         * @@return String[]<BR>
//         */
//        public String[] getOrderRequestNumberPrefixGroup()
//        {
//            return orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * �g�����U�N�V�������������{����B<BR>
//         * <BR>
//         * �V�[�P���X�}<BR>
//         * �u�i������������ʒm���C���T�[�r�X�jprocess�v�Q�ƁB<BR>
//         * @@return Object
//         * @@throws DataQueryException, DataNetworkException, DataCallbackException
//         */
//        public Object process()
//            throws DataQueryException, DataNetworkException, DataCallbackException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            // 1.2. ����������������ʒm�L���[�e�[�u���ǂݍ���
//            StringBuffer l_sbWhere = new StringBuffer();
//            l_sbWhere.append("request_code = ? and status = ?");
//            int l_intLength = 0;
//            if (orderRequestNumberPrefixGroup != null)
//            {
//                l_intLength = orderRequestNumberPrefixGroup.length;
//            }
//            if (l_intLength > 0)
//            {
//                l_sbWhere.append(" and (");
//                for (int i = 0;i < l_intLength;i++)
//                {
//                    if (i > 0)
//                    {
//                        l_sbWhere.append(" or ");
//                    }
//                    l_sbWhere.append("order_request_number like ?");
//                }
//                l_sbWhere.append(")");
//            }
//            Object[] l_objWhere = new Object[l_intLength + 2];
//            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL_NOTICE;
//            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;
//            for (int i = 0;i < l_intLength;i++)
//            {
//                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
//            }
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
//                HostEqtypeOrderClmdReceiptRow.TYPE,
//                l_sbWhere.toString(),
//                null,
//                null,
//                //"for update",
//                l_objWhere);
//
//            // 1.3. �擾�����L���[�e�[�u���̃��R�[�h�����ALoop
//            int l_intNum = 0;
//            if (l_lisSearchResult != null)
//            {
//                l_intNum = l_lisSearchResult.size();
//            }
//            for (int i = 0;i < l_intNum; i++)
//            {
//                log.debug("������������ʒm���C���FLoop���� i = " + i);
//
//                HostEqtypeOrderClmdReceiptParams l_params =
//                    (HostEqtypeOrderClmdReceiptParams)l_lisSearchResult.get(i);
//                log.debug("�@@�@@���ʃR�[�h�F [" + l_params.getOrderRequestNumber() + "]");
//                try
//                {
//                    // 1.3.1. get�����P��()
//                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//                    WEB3EquityOrderManager l_orderManager =
//                        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
//                    EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
//                        l_params.getInstitutionCode(),
//                        l_params.getBranchCode(),
//                        ProductTypeEnum.EQUITY,
//                        l_params.getOrderRequestNumber()
//                    );
//                    log.debug("�@@�@@�����P��ID�F [" + l_orderUnit.getOrderUnitId() + "]");
//
//                    // 1.3.3. ������������ʒm�ꌏTransactionCallback()
//                    WEB3EquityChangeCancelNotifyUnitTransactionCallback l_transactionCallBack =
//                        new WEB3EquityChangeCancelNotifyUnitTransactionCallback(l_params, l_orderUnit);
//
//                    // 1.3.4. doTransaction()
//                    String l_strStatus =
//                        (String)l_queryProcessor.doTransaction(
//                            QueryProcessor.TX_CREATE_NEW,
//                            l_transactionCallBack);
//
//                    // 1.3.5. ��������ʒm�L���[�e�[�u��.�����敪��update����
//                    // �ʒm�L���[Params.��������ʒm�敪 == �h���������h �܂��́@@�h�������s�h
//                    //�X�e�[�^�X�X�V�͊�����������ʒm�ꌏTransactionCallback()���ֈڂ� .start
//                    //if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_params.getCanmodReceiptType()) ||
//                    //    WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_params.getCanmodReceiptType()))
//                    //{
//                    //    l_params.setStatus(WEB3StatusDef.DEALT);
//                    //}
//                    //else
//                    //{
//                    //    l_params.setStatus(l_strStatus);
//                    //}
//                    //l_queryProcessor.doUpdateQuery(l_params);
//                    // .end
//                }
//                catch (WEB3BaseException l_be)
//                {
//                    log.error(l_be.getMessage(), l_be);
//                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    l_queryProcessor.doUpdateQuery(l_params);
//                }
//                catch (DataException l_de)
//                {
//                    log.error(l_de.getMessage(), l_de);
//                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    l_queryProcessor.doUpdateQuery(l_params);
//                }
//                catch (Exception l_e)
//                {
//                    if (l_e instanceof WEB3BaseRuntimeException)
//                    {
//                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
//                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
//                        {
//                            log.debug("�������b�N���s�F" + l_params.toString());
//                            continue;
//                        }
//                    }
//                    log.error(l_e.getMessage(), l_e);
//                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    l_queryProcessor.doUpdateQuery(l_params);
//                }
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//    }
//
//    /**
//     * (������������ʒm�ꌏTransactionCallback�B)<BR>
//     */
//    public class WEB3EquityChangeCancelNotifyUnitTransactionCallback implements TransactionCallback
//    {
//        /**
//         * (������������ʒm�L���[Params)<BR>
//         * ������������ʒm�L���[Params�B<BR>
//         */
//        private HostEqtypeOrderClmdReceiptParams hostEqtypeOrderClmdReceiptParams;
//
//        /**
//         * (�����P��)<BR>
//         * �����P�ʁB<BR>
//         */
//        private EqTypeOrderUnit orderUnit;
//
//        /**
//         * (������������ʒm�ꌏTransactionCallback)<BR>
//         * <BR>
//         * �R���X�g���N�^�B<BR>
//         * �����̒l�𓯖��v���p�e�B�ɃZ�b�g����B<BR>
//         * <BR>
//         * @@param l_hostEqtypeOrderClmdReceiptParams - (������������ʒm�L���[Params)<BR>
//         * �y����������������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
//         * @@param l_orderUnit - (�����P��)<BR>
//         * �����P�ʃI�u�W�F�N�g<BR>
//         */
//        public WEB3EquityChangeCancelNotifyUnitTransactionCallback(
//            HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams,
//            EqTypeOrderUnit l_orderUnit)
//        {
//            this.hostEqtypeOrderClmdReceiptParams = l_hostEqtypeOrderClmdReceiptParams;
//            this.orderUnit = l_orderUnit;
//        }
//
//        /**
//         * (process)<BR>
//         * <BR>
//         * �g�����U�N�V�������������{����B<BR>
//         * <BR>
//         * �V�[�P���X�}<BR>
//         * �u�i������������ʒm���C���T�[�r�X�jprocess�v�Q�ƁB<BR>
//         * <BR>
//         * @@return Object
//         * @@throws DataQueryException, DataNetworkException, DataCallbackException
//         */
//        public Object process()
//            throws DataQueryException, DataNetworkException, DataCallbackException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            String l_strStatus = null;
//
//            try
//            {
//                // 1.3.4.1.1. �擾���������P�ʁ����������̏ꍇ
//                if (OrderCategEnum.ASSET.equals(orderUnit.getOrderCateg()))
//                {
//                    // 1.3.4.1.1.1. �ʒm�L���[Params.��������ʒm�敪 == �h���������h �܂��́@@�h�������s�h
//                    if (WEB3CanmodReceiptTypeDef.CHANGED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
//                        WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
//                    {
//                        // 1.3.4.1.1.1.1. notify����()
//                        WEB3EquityReceiveChangeEventService l_unitService =
//                            (WEB3EquityReceiveChangeEventService)Services.getService(
//                                WEB3EquityReceiveChangeEventService.class);
//                        l_unitService.notifyChange(hostEqtypeOrderClmdReceiptParams, orderUnit);
//                    }
//                    // 1.3.4.1.1.2. �ʒm�L���[Params.��������ʒm�敪 == �h��������h �܂��́@@�h������s�h
//                    else if (WEB3CanmodReceiptTypeDef.CANCEL.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
//                        WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
//                    {
//                        // 1.3.4.1.1.2.1. notify���()
//                        WEB3EquityReceiveCancelEventService l_unitService =
//                            (WEB3EquityReceiveCancelEventService)Services.getService(
//                                WEB3EquityReceiveCancelEventService.class);
//                        l_strStatus = l_unitService.notifyCancel(hostEqtypeOrderClmdReceiptParams, orderUnit);
//                    }
//                    // ��L�ȊO�̏ꍇ
//                    else
//                    {
//                        String l_strMessage =
//                            "������������ʒm�L���[.��������ʒm�敪���i\"��������\"or\"�������s\"or\"�������\"or\"������s\"�j";
//                        log.error(l_strMessage);
//                        throw new WEB3SystemLayerException(
//                            WEB3ErrorCatalog.SYSTEM_ERROR_80025,
//                            this.getClass().getName() + "." + STR_METHOD_NAME,
//                            l_strMessage);
//                    }
//                }
//                // 1.3.4.1.2. �擾���������P�ʁ��M�p����̏ꍇ
//                else if (OrderCategEnum.OPEN_MARGIN.equals(orderUnit.getOrderCateg()) ||
//                          OrderCategEnum.CLOSE_MARGIN.equals(orderUnit.getOrderCateg()) ||
//                          OrderCategEnum.SWAP_MARGIN.equals(orderUnit.getOrderCateg()))
//                {
//                    // 1.3.4.1.2.1. �ʒm�L���[Params.��������ʒm�敪 == �h���������h �܂��́@@�h�������s�h
//                    if (WEB3CanmodReceiptTypeDef.CHANGED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
//                        WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
//                    {
//                        // 1.3.4.1.2.1.1. notify����()
//                        WEB3MarginChangeCancelNotifyChangeUnitService l_unitService =
//                            (WEB3MarginChangeCancelNotifyChangeUnitService)Services.getService(
//                                WEB3MarginChangeCancelNotifyChangeUnitService.class);
//                        l_unitService.notifyChange(hostEqtypeOrderClmdReceiptParams, orderUnit);
//                    }
//                    // 1.3.4.1.2.2. �ʒm�L���[Params.��������ʒm�敪 == �h��������h �܂��́@@�h������s�h
//                    else if (WEB3CanmodReceiptTypeDef.CANCEL.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
//                        WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
//                    {
//                        // 1.3.4.1.2.2.1. notify���()
//                        WEB3MarginChangeCancelNotifyCancelUnitService l_unitService =
//                            (WEB3MarginChangeCancelNotifyCancelUnitService)Services.getService(
//                                WEB3MarginChangeCancelNotifyCancelUnitService.class);
//                        l_strStatus = l_unitService.notifyCancel(hostEqtypeOrderClmdReceiptParams, orderUnit);
//                    }
//                    // ��L�ȊO�̏ꍇ
//                    else
//                    {
//                        String l_strMessage =
//                            "������������ʒm�L���[.��������ʒm�敪���i\"��������\"or\"�������s\"or\"�������\"or\"������s\"�j";
//                        log.error(l_strMessage);
//                        throw new WEB3SystemLayerException(
//                            WEB3ErrorCatalog.SYSTEM_ERROR_80025,
//                            this.getClass().getName() + "." + STR_METHOD_NAME,
//                            l_strMessage);
//                    }
//                }
//                // 1.3.4.1.3. ��L�ȊO�̏ꍇ
//                else
//                {
//                    String l_strMessage =
//                        "���������P��.�����J�e�S�����i\"��������\"or\"�V�K������\"or\"�ԍϒ���\"or\"�����E���n����\"�j";
//                    log.error(l_strMessage);
//                    throw new WEB3SystemLayerException(
//                        WEB3ErrorCatalog.SYSTEM_ERROR_80025,
//                        this.getClass().getName() + "." + STR_METHOD_NAME,
//                        l_strMessage);
//                }
//            }
//            catch (WEB3BaseException l_exp)
//            {
//                throw new DataCallbackException(
//                    l_exp.getMessage(),
//                    l_exp.getErrorInfo());
//            }
//            //�X�e�[�^�X�X�V�͊�����������ʒm�ꌏTransactionCallback()���ֈڂ����� .start
//            if (WEB3CanmodReceiptTypeDef.CHANGED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()) ||
//                WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
//            {
//                hostEqtypeOrderClmdReceiptParams.setStatus(WEB3StatusDef.DEALT);
//            }
//            else
//            {
//                hostEqtypeOrderClmdReceiptParams.setStatus(l_strStatus);
//            }
//            Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeOrderClmdReceiptParams);
//            //.end
//            log.exiting(STR_METHOD_NAME);
//            return l_strStatus;
//        }
//    }
}
@
