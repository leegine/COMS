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
filename	WEB3AsynEquityReceiveCloseOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�T�[�r�XImpl(WEB3AsynEquityReceiveCloseOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
                   �����Ή��ŗ����F
Revesion History : 2004/05/24 ���j (���u) �V�K�쐬
Revesion History : 2004/09/20 羐� (���u) �C��
Revesion History : 2004/12/13 ���� (SRA) �c�Č��Ή��̂��ߏC��
Revesion History : 2005/01/06 ���� (SRA) JavaDoc�C��
�@@�@@�@@�@@�@@�@@�@@�@@�@@  �񓯊��Ή��ŗ����F
Revesion History : 2005/03/09 ���iFLJ�j�񓯊����s�Ή��i�V�K�N���X�j
Revesion History : 2006/11/20 �����F(���u) ���f�� 1046
Revesion History : 2007/04/17 ��іQ (���u) ���f�� 1139
Revesion History : 2007/06/11 ������ (���u) ���f�� 1179
Revesion History : 2008/03/20 ���� (���u) ���f�� 1308
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyRow;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.message.WEB3EquityCloseOrderRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * �i���������ʒm�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���������ʒm�T�[�r�X�����N���X�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@author 羐�
 * @@version 1.0
 */
public class WEB3AsynEquityReceiveCloseOrderServiceImpl
    implements Runnable
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynEquityReceiveCloseOrderServiceImpl.class);


    /**
     *�@@���������ʒm�������N�G�X�g
     */
    private WEB3EquityCloseOrderRequest l_equityCloseOrderRequest;

    /**
     * @@roseuid 40AD626100FC
     */
    public WEB3AsynEquityReceiveCloseOrderServiceImpl(WEB3EquityCloseOrderRequest
        l_equityCloseOrderRequest)
    {
        this.l_equityCloseOrderRequest=l_equityCloseOrderRequest;
    }

    /**
     * �������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����ʒm�T�[�r�X�j�����ʒm�v�Q��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CFEA0176
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        // ���������ʒm�������s��
        try
        {
            //1.2 getDefaultProcesser
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            //1.3 ���������ʒmTransactionCallback()
            WEB3EquityReceiveCloseOrderTransactionCallback l_callback
                         = new WEB3EquityReceiveCloseOrderTransactionCallback();

            //1.4 set���ʃR�[�h�v���t�B�N�X (���ʃR�[�h�v���t�B�N�X�ꗗ : String[])
            l_callback.setOrderRequestNumberPrefixGroup(l_equityCloseOrderRequest.orderRequestNumberPrefixGroup);

            //1.5 doTransaction()�i1�����ƂɃR�~�b�g����悤�Ƀg�����U�N�V����������ݒ�B�j
            l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callback);

        }
        catch (DataException l_exp)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("",new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp));
        }
        catch (Exception l_e)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e));
        }

        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(l_equityCloseOrderRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (���������ʒmTransactionCallback)<BR>
     * ���������ʒm�n���h���������s���g�����U�N�V�����E�R�[���o�b�N�N���X�B<BR>
     */
    private class WEB3EquityReceiveCloseOrderTransactionCallback
        implements TransactionCallback
    {

        /**
         * (���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (���������ʒmTransactionCallback)<BR>
         * ���������ʒmTransactionCallback�N���X<BR>
         * �R���X�g���N�^�B
         * @@return
         * webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderTransactionCallback
         * .WEB3EquityReceiveCloseOrderTransactionCallback
         */
        public WEB3EquityReceiveCloseOrderTransactionCallback()
        {

        }

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�����ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4137CFEA0290
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME =
                "WEB3EquityReceiveCloseOrderTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            HostEqtypeCloseOrderNotifyRow l_hostEqtypeCloseOrderNotifyRow;
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            // ----------------------------------
            // 1.1 get���ʃR�[�h�v���t�B�N�X�ꗗ()
            // ----------------------------------
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();

            // ----------------------------------
            // 1.2 ���������ʒm�L���[�e�[�u������
            // ----------------------------------
            //     �f�[�^�R�[�h =  ���������ʒm
            //     �����敪 = ������
            //     ���ʃR�[�h�̐擪2�����Aget���ʃR�[�h�v���t�B�N�X�ꗗ()�̖߂�l�z��̂����ꂩ�ƈ�v
            String l_strWhere = "request_code=? and status=?";
            //String l_strCondition = "for update";
            String l_strCondition = null;
            int l_intLength = 0;
            if (l_orderRequestNumberPrefixGroup != null)
            {
                l_intLength = l_orderRequestNumberPrefixGroup.length;
            }
            if (l_intLength > 0)
            {
                l_strWhere = l_strWhere + " and (";
                for(int i = 0;i < l_intLength;i++)
                {
                    if (i == 0)
                    {
                        l_strWhere = l_strWhere + "order_request_number like ?";
                    }
                    else if (0 < i)
                    {
                        l_strWhere = l_strWhere + " or order_request_number like ?";
                    }
                    log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ�F" + l_orderRequestNumberPrefixGroup[i]);
                }
                l_strWhere = l_strWhere + ")";
            }
            log.debug("���������F[" + l_strWhere + "]");

            Object l_objWhere[] = new Object[l_intLength + 2];
            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE;
            l_objWhere[1] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
            for (int i = 0;i < l_intLength;i++)
            {
                l_objWhere[i + 2] = l_orderRequestNumberPrefixGroup[i] + "%";
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    HostEqtypeCloseOrderNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_objWhere);

            //�擾�����L���[���R�[�h������Loop����
            int l_intSize = l_lisRecords.size();
            for (int i = 0; i < l_intSize; i++)
            {
                log.debug("���������ʒm�FLoop���� i = " + i);

                //�����敪
                String l_strStatus = null;

                //get ���������ʒm�L���[Params
                l_hostEqtypeCloseOrderNotifyRow =
                    (HostEqtypeCloseOrderNotifyRow)l_lisRecords.get(i);
                l_hostEqtypeCloseOrderNotifyParams =
                    new HostEqtypeCloseOrderNotifyParams(l_hostEqtypeCloseOrderNotifyRow);
                log.debug("�@@�@@���ʃR�[�h�F [" + l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber() + "]");

                WEB3GentradeAccountManager l_accountMgr =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                try
                {
                    // ---------------------------------
                    // 1.3.2. �����Ώے����P�ʂ��擾����
                    // ---------------------------------
                    //       �،���ЃR�[�h�F�@@���������ʒm�L���[Params.�،���ЃR�[�h
                    //       ���X�R�[�h�F�@@���������ʒm�L���[Params.���X�R�[�h
                    //       ���i�^�C�v�F�@@ProductTypeEnum.����
                    //       ���ʃR�[�h�F�@@���������ʒm�L���[Params.���ʃR�[�h
                    EqTypeOrderUnit l_eqtypeOrderUnit = l_orderMgr.getOrderUnit(
                        l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
                        l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
                        ProductTypeEnum.EQUITY,
                        l_hostEqtypeCloseOrderNotifyParams.getOrderRequestNumber());
                    log.debug("�@@�@@�����P��ID�F [" + l_eqtypeOrderUnit.getOrderUnitId() + "]");

                    //���������ʒm�L���[�e�[�u��.�����敪�X�V������WEB3EquityReceiveCloseOrderUnitTransactionCallback�����ڂ�
                    WEB3EquityReceiveCloseOrderUnitTransactionCallback l_callback =
                        new WEB3EquityReceiveCloseOrderUnitTransactionCallback(
                            l_hostEqtypeCloseOrderNotifyParams,
                            l_eqtypeOrderUnit);
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_callback);
                }
                catch (Exception l_e)
                {
                    if (l_e instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                        {
                            //�������b�N()�Ɏ��s�����ꍇ
                            log.debug(l_wre.getMessage(), l_wre);
                            continue;
                        }
                    }
                    else if (l_e instanceof DataCallbackException)
                    {
                        DataCallbackException l_dce = 
                            (DataCallbackException) l_e;
                        
                        if (l_dce.getDetails() != null)
                        {
                            WEB3BaseException l_wbe = (WEB3BaseException) l_dce.getDetails();                            
                            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_01975)
                            {
                                //�u��������t����/�������v�̗�O�̏ꍇ
                                log.debug(l_wbe.getMessage(), l_wbe);
                                continue;
                            }
                        }
                    }
                    else if (l_e instanceof WEB3SystemLayerException)
                    {
                        WEB3SystemLayerException l_sle = (WEB3SystemLayerException)l_e;
                        if (l_sle.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80005 &&
                            l_sle.getException() == null)
                        {
                            try
                            {
                                //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                                //�،���ЃR�[�h�F�@@���������ʒm�L���[Params.�،���ЃR�[�h
                                //���X�R�[�h�F�@@�@@�@@�@@���������ʒm�L���[Params.���X�R�[�h
                                //�����R�[�h�F�@@�@@�@@�@@���������ʒm�L���[Params�����R�[�h
                                l_accountMgr.getMainAccount(
                                    l_hostEqtypeCloseOrderNotifyParams.getInstitutionCode(),
                                    l_hostEqtypeCloseOrderNotifyParams.getBranchCode(),
                                    l_hostEqtypeCloseOrderNotifyParams.getAccountCode());
                            }
                            catch(WEB3BaseException l_ex)
                            {
                                log.debug(l_ex.getMessage(), l_ex);
                                l_strStatus = WEB3StatusDef.DEALT;
                                l_hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
                                l_hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(
                                    GtlUtils.getSystemTimestamp());
                                l_queryProcessor.doUpdateQuery(l_hostEqtypeCloseOrderNotifyParams);
                                continue;
                            }
                        }
                    }

                    log.error(l_e.getMessage(), l_e);
                    l_strStatus = WEB3HostStatusDef.DATA_ERROR;
                }

                // ------------------------------------------------------------
                // 1.3.3. ���������ʒm�L���[�e�[�u��.�����敪��update��commit����
                // ------------------------------------------------------------
                if(WEB3StatusDef.DATA_ERROR.equals(l_strStatus)){
                    l_hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
                    l_hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_hostEqtypeCloseOrderNotifyParams);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }


        /**
         * (get���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * (set���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B
         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
        {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
        
    }

    /**
     * (���������ʒm�ꌏTransactionCallback)<BR>
     * <BR>
     * ���������ʒm�ꌏTransactionCallback�N���X�B<BR>
     */
    public class WEB3EquityReceiveCloseOrderUnitTransactionCallback
        implements TransactionCallback
    {
        /**
         * (���������ʒm�L���[Params�B)
         */
        private HostEqtypeCloseOrderNotifyParams hostEqtypeCloseOrderNotifyParams;

        /**
         * (�����P�ʁB)
         */
        private EqTypeOrderUnit orderUnit;

        /**
         * (���������ʒm�ꌏTransactionCallback)<BR>
         * <BR>
         * �R���X�g���N�^�B<BR>
         * �����̒l�𓯖��v���p�e�B�ɃZ�b�g����B<BR>
         * @@param l_hostEqtypeCloseOrderNotifyParams - (���������ʒm�L���[Params)<BR>
         * �y���������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
         * @@param l_orderUnit - (�����P��)<BR>
         * �����P�ʃI�u�W�F�N�g<BR>
         */
        public WEB3EquityReceiveCloseOrderUnitTransactionCallback(
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams,
            EqTypeOrderUnit l_orderUnit)
        {
            hostEqtypeCloseOrderNotifyParams = l_hostEqtypeCloseOrderNotifyParams;
            orderUnit = l_orderUnit;
        }

        /**
         * (process)<BR>
         * <BR>
         * �g�����U�N�V�������������{����B<BR>
         *
         * �V�[�P���X�}<BR>
         * �u�i�����ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            try
            {
                // notify����()
                WEB3EquityReceiveCloseOrderUnitService l_service =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                l_service.notifyCloseOrder(hostEqtypeCloseOrderNotifyParams, orderUnit);

                // �]�͍Čv�Z�i�j
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                WEB3GentradeSubAccount l_subAccount = null;
                l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    orderUnit.getAccountId(),
                    orderUnit.getSubAccountId());
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new DataCallbackException();
            }
            catch (WEB3BaseException l_exp)
            {
                log.debug(STR_METHOD_NAME, l_exp);
                throw new DataCallbackException(null, l_exp);
            }
            log.exiting(STR_METHOD_NAME);

            // ------------------------------------------------------------
            // ���������ʒm�L���[�e�[�u��.�����敪��update��commit����
            // ------------------------------------------------------------
            String l_strStatus = null;
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit = null;
            try
            {
                l_orderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new DataCallbackException();
            }
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            
            // <�����ʒm�L���[.�������ʂɒl���Z�b�g����Ă���ꍇ>
            //  �����ʒm�L���[.�������� < (�����P��.�s�ꂩ��m�F�ς݂̐���-�����P��.��萔��)
            // <�����ʒm�L���[.�������ʂɒl���Z�b�g����Ă��Ȃ��ꍇ>
            // �����P��.��萔�� < �����ʒm�L���[.��萔��
            if ((!hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                    && (hostEqtypeCloseOrderNotifyParams.getCloseQuantity() < (l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity())))
                || (hostEqtypeCloseOrderNotifyParams.getCloseQuantityIsNull()
                    && (l_orderUnitRow.getExecutedQuantity() < hostEqtypeCloseOrderNotifyParams.getExecutedQuantity())))
            {
                l_strStatus = WEB3StatusDef.DEALING;
            }
            // �ȏ�łȂ��ꍇ
            else
            {
                l_strStatus = WEB3StatusDef.DEALT;   
            }
            hostEqtypeCloseOrderNotifyParams.setStatus(l_strStatus);
            hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doUpdateQuery(hostEqtypeCloseOrderNotifyParams);
            return null;
        }
    }
}@
