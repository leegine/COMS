head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCloseOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�T�[�r�XImpl(WEB3EquityReceiveCloseOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 ���j (���u) �V�K�쐬
                   2004/09/20 羐� (���u) �C��
                   2004/12/13 ���� (SRA) �c�Č��Ή��̂��ߏC��
                   2005/01/06 ���� (SRA) JavaDoc�C��
                   2005/03/09 ���iFLJ�j�L���[�e�[�u���ɂ�鉺�菈���̃g�����U�N�V��������ύX�A
 �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�񓯊���������WEB3AsynEquityReceiveCloseOrderServiceImpl�ֈڂ��i�V�K�N���X�j

*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
/**
 * �i���������ʒm�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���������ʒm�T�[�r�X�����N���X�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author 羐�
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderServiceImpl
    implements WEB3EquityReceiveCloseOrderService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCloseOrderServiceImpl.class);

    /**
     * @@roseuid 40AD626100FC
     */
    public WEB3EquityReceiveCloseOrderServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����ʒm�T�[�r�X�j�����ʒm�v�Q��<BR>
     * @@param l_request - �����ʒm���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3EquityCloseOrderResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CFEA0176
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1 validate()
        WEB3EquityCloseOrderRequest l_equityCloseOrderRequest = (WEB3EquityCloseOrderRequest)l_request;
        l_equityCloseOrderRequest.validate();

        // 1.2. �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_equityCloseOrderRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityReceiveCloseOrderServiceImpl(
            l_equityCloseOrderRequest));

        log.exiting(STR_METHOD_NAME);
        return l_equityCloseOrderRequest.createResponse();
    }
//>>>>>>�ȍ~����WEB3AsynEquityReceiveCloseOrderServiceImpl�ֈڂ��B2005/03/09  ���iFLJ�j

//        // ���������ʒm�������s��
//        try
//        {
//            //1.2 getDefaultProcesser
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//
//            //1.3 ���������ʒmTransactionCallback()
//            WEB3EquityReceiveCloseOrderTransactionCallback l_callback
//                         = new WEB3EquityReceiveCloseOrderTransactionCallback();
//
//            //1.4 set���ʃR�[�h�v���t�B�N�X (���ʃR�[�h�v���t�B�N�X�ꗗ : String[])
//            l_callback.setOrderRequestNumberPrefixGroup(l_equityCloseOrderRequest.orderRequestNumberPrefixGroup);
//
//            //1.5 doTransaction()�i1�����ƂɃR�~�b�g����悤�Ƀg�����U�N�V����������ݒ�B�j
//            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callback);
//        }
//        catch (DataException l_exp)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_exp.getMessage(),
//                l_exp);
//        }
//
//        log.exiting(STR_METHOD_NAME);
//
//        return l_request.createResponse();
//    }
//
//    /**
//     * (���������ʒmTransactionCallback)<BR>
//     * ���������ʒm�n���h���������s���g�����U�N�V�����E�R�[���o�b�N�N���X�B<BR>
//     */
//    private class WEB3EquityReceiveCloseOrderTransactionCallback
//        implements TransactionCallback
//    {
//
//        /**
//         * (���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * (���������ʒmTransactionCallback)<BR>
//         * ���������ʒmTransactionCallback�N���X<BR>
//         * �R���X�g���N�^�B
//         * @@return
//         * webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderTransactionCallback
//         * .WEB3EquityReceiveCloseOrderTransactionCallback
//         */
//        public WEB3EquityReceiveCloseOrderTransactionCallback()
//        {
//
//        }
//
//        /**
//         * �g�����U�N�V�������������{����B<BR>
//         * <BR>
//         * �V�[�P���X�}<BR>
//         * �u�i�����ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
//         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j
//         * @@throws DataNetworkException
//         * @@throws DataQueryException
//         * @@throws DataCallbackException
//         * @@roseuid 4137CFEA0290
//         */
//        public Object process()
//            throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME =
//                "WEB3EquityReceiveCloseOrderTransactionCallback.process()";
//            log.entering(STR_METHOD_NAME);
//
//            HostEqtypeCloseOrderNotifyRow l_hostEqtypeCloseOrderNotifyRow;
//            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams;
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            WEB3EquityOrderManager l_orderMgr =
//                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
//
//            // ----------------------------------
//            // 1.1 get���ʃR�[�h�v���t�B�N�X�ꗗ()
//            // ----------------------------------
//            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
//
//            // ----------------------------------
//            // 1.2 ���������ʒm�L���[�e�[�u������
//            // ----------------------------------
//            //     �f�[�^�R�[�h =  ���������ʒm
//            //     �����敪 = ������
//            //     ���ʃR�[�h�̐擪2�����Aget���ʃR�[�h�v���t�B�N�X�ꗗ()�̖߂�l�z��̂����ꂩ�ƈ�v
//            String l_strWhere = "request_code=? and status=?";
//            String l_strCondition = "for update";
//
//            int l_intLength = 0;
//            if (l_orderRequestNumberPrefixGroup != null)
//            {
//                l_intLength = l_orderRequestNumberPrefixGroup.length;
//            }
//            if (l_intLength > 0)
//            {
//                l_strWhere = l_strWhere + " and (";
//                for(int i = 0;i < l_intLength;i++)
//                {
//                    if (i == 0)
//                    {
//                        l_strWhere = l_strWhere + "order_request_number like ?";
//                    }
//                    else if (0 < i)
//                    {
//                        l_strWhere = l_strWhere + " or order_request_number like ?";
//                    }
//                    log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ�F" + l_orderRequestNumberPrefixGroup[i]);
//                }
//                l_strWhere = l_strWhere + ")";
//            }
//            log.debug("���������F[" + l_strWhere + "]");
//
//            Object l_objWhere[] = new Object[l_intLength + 2];
//            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE;
//            l_objWhere[1] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
//            for (int i = 0;i < l_intLength;i++)
//            {
//                l_objWhere[i + 2] = l_orderRequestNumberPrefixGroup[i] + "%";
//            }
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            List l_lisRecords =
//                l_queryProcessor.doFindAllQuery(
//                    HostEqtypeCloseOrderNotifyRow.TYPE,
//                    l_strWhere,
//                    l_strCondition,
//                    l_objWhere);
//
//            //�擾�����L���[���R�[�h������Loop����
//            int l_intSize = l_lisRecords.size();
//            for (int i = 0; i < l_intSize; i++)
//            {
//                log.debug("���������ʒm�FLoop���� i = " + i);
//
//                //�����敪
//                String l_strStatus;
//
//                //get ���������ʒm�L���[Params
//                l_hostEqtypeCloseOrderNotifyRow =
//                    (HostEqtypeCloseOrderNotifyRow)l_lisRecords.get(i);
//                l_hostEqtypeCloseOrderNotifyParams =
//                    new HostEqtypeCloseOrderNotifyParams(l_hostEqtypeCloseOrderNotifyRow);
//                log.debug("�@@�@@���ʃR�[�h�F [" + l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber() + "]");
//
//                try
//                {
//                    // ---------------------------------
//                    // 1.3.2. �����Ώے����P�ʂ��擾����
//                    // ---------------------------------
//                    //       �،���ЃR�[�h�F�@@���������ʒm�L���[Params.�،���ЃR�[�h
//                    //       ���X�R�[�h�F�@@���������ʒm�L���[Params.���X�R�[�h
//                    //       ���i�^�C�v�F�@@ProductTypeEnum.����
//                    //       ���ʃR�[�h�F�@@���������ʒm�L���[Params.���ʃR�[�h
//                    EqTypeOrderUnit l_eqtypeOrderUnit = l_orderMgr.getOrderUnit(
//                        l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
//                        l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
//                        ProductTypeEnum.EQUITY,
//                        l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber());
//                    log.debug("�@@�@@�����P��ID�F [" + l_eqtypeOrderUnit.getOrderUnitId() + "]");
//
//                    //�o���ʒm�҂��̏ꍇ
//                    EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
//                    if (l_orderUnitRow.getExecutedQuantity() < l_hostEqtypeCloseOrderNotifyParams.getExecutedQuantity())
//                    {
//                        //set  �����敪 = ������
//                        l_strStatus = WEB3StatusDef.DEALING;
//                    }
//                    else
//                    {
//                        //set  �����敪 = ������
//                        l_strStatus = WEB3StatusDef.DEALT;
//                    }
//
//                    WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callback =
//                        new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
//                            l_hostEqtypeCloseOrderNotifyParams,
//                            l_eqtypeOrderUnit);
//
//                    l_queryProcessor.doTransaction(
//                        QueryProcessor.TX_CREATE_NEW,
//                        l_callback);
//                }
//                catch (DataException l_dex)
//                {
//                    log.debug("�����������������ɃG���[�̏ꍇ", l_dex);
//                    //set �����敪 = �G���[
//                    l_strStatus = WEB3StatusDef.DATA_ERROR;
//                }
//                catch (WEB3BaseException wbe)
//                {
//                    log.debug("�����������������ɃG���[�̏ꍇ",wbe);
//                    //set �����敪 = �G���[
//                    l_strStatus = WEB3StatusDef.DATA_ERROR;
//                }
//
//                // ------------------------------------------------------------
//                // 1.3.3. ���������ʒm�L���[�e�[�u��.�����敪��update��commit����
//                // ------------------------------------------------------------
//                l_hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
//                l_queryProcessor.doUpdateQuery(l_hostEqtypeCloseOrderNotifyParams);
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//
//
//        /**
//         * (get���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
//         * <BR>
//         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B
//         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
//         */
//        public String[] getOrderRequestNumberPrefixGroup()
//        {
//            return this.orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * (set���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
//         * <BR>
//         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B
//         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
//        {
//            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
//        }
//    }
//
//    /**
//     * (���������ʒm�ꌏTransactionCallback)<BR>
//     * <BR>
//     * ���������ʒm�ꌏTransactionCallback�N���X�B<BR>
//     */
//    public class WEB3EquityReceiveCloseOrderUnitTransactionCallback
//        implements TransactionCallback
//    {
//        /**
//         * (���������ʒm�L���[Params�B)
//         */
//        HostEqtypeCloseOrderNotifyParams hostEqtypeCloseOrderNotifyParams;
//
//        /**
//         * (�����P�ʁB)
//         */
//        EqTypeOrderUnit orderUnit;
//
//        /**
//         * (���������ʒm�ꌏTransactionCallback)<BR>
//         * <BR>
//         * �R���X�g���N�^�B<BR>
//         * �����̒l�𓯖��v���p�e�B�ɃZ�b�g����B<BR>
//         * @@param l_hostEqtypeCloseOrderNotifyParams - (���������ʒm�L���[Params)<BR>
//         * �y���������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
//         * @@param l_orderUnit - (�����P��)<BR>
//         * �����P�ʃI�u�W�F�N�g<BR>
//         */
//        public WEB3EquityReceiveCloseOrderUnitTransactionCallback(
//            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
//            EqTypeOrderUnit l_orderUnit)
//        {
//            hostEqtypeCloseOrderNotifyParams = l_hostEqtypeCloseOrderNotifyParams;
//            orderUnit = l_orderUnit;
//        }
//
//        /**
//         * (process)<BR>
//         * <BR>
//         * �g�����U�N�V�������������{����B<BR>
//         *
//         * �V�[�P���X�}<BR>
//         * �u�i�����ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
//
//         */
//        public Object process()
//            throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            try
//            {
//                // exec����()
//                WEB3EquityReceiveCloseOrderUnitService l_service =
//                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
//                l_service.execCloseOrder(hostEqtypeCloseOrderNotifyParams, orderUnit);
//
//                // �]�͍Čv�Z�i�j
//                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//                WEB3GentradeAccountManager l_accountManager =
//                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
//                WEB3GentradeSubAccount l_subAccount = null;
//                l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
//                    orderUnit.getAccountId(),
//                    orderUnit.getSubAccountId());
//                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
//                    (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
//                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
//            }
//            catch (NotFoundException l_nfe)
//            {
//                log.error(l_nfe.getMessage(), l_nfe);
//                throw new DataCallbackException();
//            }
//            catch (WEB3BaseException l_exp)
//            {
//                log.error(STR_METHOD_NAME, l_exp);
//                throw new DataCallbackException();
//            }
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//    }
}
@
