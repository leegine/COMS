head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynIfoCloseNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒm�T�[�r�XImpl(WEB3AsynIfoCloseNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : �����ŗ����F
Revesion History : 2004/6/18 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 001: 2004/07/23 ���Ō� (���u) WEB3HostRequestCodeDef��WEB3IfoRequestCodeTypeDef�������ւ���
Revesion History : 002: 2004/07/29 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000063 execute()���C��
Revesion History : 003: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
Revesion History : �񓯊��Ή��ŗ����F
Revesion History : 2005/03/11 ���u���@@�񓯊����s�Ή��i�V�K�N���X�j
Revesion History : 2007/04/24 �Ј���(���u) �d�l�ύX���f��No.636
Revesion History : 2008/03/20 ����(���u) �d�l�ύX���f��No.852
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�񓯊��Ή��敨OP�����ʒm���C���T�[�r�X�����N���X�j�B
 * @@author  : ���u���i���{���u�j
 * @@version : 1.0
 */
public class WEB3AsynIfoCloseNotifyServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynIfoCloseNotifyServiceImpl.class);

    /**
     * �����ʒm���N�G�X�g
     */
    private WEB3IfoCloseOrderRequest request;


    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AsynIfoCloseNotifyServiceImpl(WEB3IfoCloseOrderRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynIfoCloseNotifyServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        //2: �N�G���v���Z�b�T�̃C���X�^���X���擾����
        QueryProcessor l_queryProcessor= null;
        try
        {
            WEB3IfoCloseOrderRequest l_closeOrderRequest =
                (WEB3IfoCloseOrderRequest) request;

            try
            {
                log.debug("Get the object Processor.");
                l_queryProcessor = Processors.getDefaultProcessor();
                log.debug("Succeeded to get the object.");
            }
            catch (DataFindException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                      WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                      this.getClass().getName() + STR_METHOD_NAME);
            }
            //3: �敨OP�����ʒmTransactionCallback
            WEB3IfoCloseNotifyTransactionCallback l_callBack =
                new WEB3IfoCloseNotifyTransactionCallback();

            //set���ʃR�[�h�v���t�B�N�X�ꗗ()
            l_callBack.setOrderRequestNumberPrefixGroup(l_closeOrderRequest.orderRequestNumberPrefixGroup);

            //4: ���b�Z�[�W �����Ώۃp�����[�^�i4/22�ڍז���j�Z�b�g
            //5: DB�g�����U�N�V�������������{����
            try
            {
                log.debug("Enter the try path:db update.");
                l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW,l_callBack);
                log.debug("Succeeded to  update the database.");
            }
            catch (DataCallbackException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataRollbackException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �敨OP�����ʒmTransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3IfoCloseNotifyTransactionCallback implements TransactionCallback
    {
        /**
         * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params String[] - ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[]
            l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * (get���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }
        
        /**
         * �f�t�H���g�R���X�g���N�^
         * @@return webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyServiceImpl.WEB3IfoCloseNotifyTransactionCallback
         * @@roseuid 4088F56A0131
         */
        public WEB3IfoCloseNotifyTransactionCallback()
        {

        }

        /**
         * �����ʒm���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨OP�����ʒm�jprocess�v�Q�ƁB<BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 4088F56A0122
         */
        public Object process() throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = "Object process()";
            log.entering(STR_METHOD_NAME);
            //��萔��
            double l_dblExecQuantity  = 0;

            //�������R�R�[�h
            String l_strCloseReasonCode = null;

            //get�����ʒm�敪
            String l_strCloseNotifyType = null;

            //OrderUnit
            OrderUnit l_orderUnit = null;

            HostFotypeCloseOrderNotifyParams l_orderAcceptedParams = null;


            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("(request_code = ?");
            l_sbWhere.append("or request_code = ?)");
            l_sbWhere.append(" and status= ?");

            int l_intPrefixGroupLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intPrefixGroupLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intPrefixGroupLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intPrefixGroupLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_request_number like ?");
                }
                l_sbWhere.append(")");
            }

            Object[] l_objWhere = new Object[l_intPrefixGroupLength + 3];
            l_objWhere[0] = WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE;
            l_objWhere[1] = WEB3HostRequestCodeDef.FUTURES_CLOSE_NOTICE;
            l_objWhere[2] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objWhere[i + 3] = orderRequestNumberPrefixGroup[i] + "%";
            }

            List l_listRecords = new ArrayList();

            QueryProcessor l_queryProcessor = null;
            try
            {
                log.debug("Get the processor object.");
                l_queryProcessor = Processors.getDefaultProcessor();
                log.debug("Succeeded to get the project processor.");

                log.debug("Get the records that satisfys the specified condition.");
                l_listRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeCloseOrderNotifyRow.TYPE,
                    l_sbWhere.toString() ,
                    null,
                    null,
                    l_objWhere) ;
                log.debug("Succeeded to get the records that satisfied th specified conditon.");

            }
            catch (DataFindException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);

            }


            int l_intLength;
            l_intLength = l_listRecords.size();
            log.debug("the number of records:" + l_intLength);
            log.debug("The number of records that searched just for:" + l_intLength);
            if (l_intLength > 0)
            {
                for(int i=0 ;i < l_intLength; i++)
                {
                    try
                    {
                        l_orderAcceptedParams = new HostFotypeCloseOrderNotifyParams((HostFotypeCloseOrderNotifyRow)l_listRecords.get(i));

                        String l_strInstitutionCode = l_orderAcceptedParams.getInstitutionCode();

                        String l_strBranchCode = l_orderAcceptedParams.getBranchCode();
                        log.debug("The BranchCode is :" + l_strBranchCode);
                        String l_strAccountCode = l_orderAcceptedParams.getAccountCode();
                        log.debug("The Account Code is:" + l_strAccountCode);

                        //5: �C���^�Z�v�^��OrderManager�ɃZ�b�g����
                        //�擾OrderManager
                        TradingModule l_tradingMod =
                            l_finApp.getTradingModule(ProductTypeEnum.IFO);
                        WEB3OptionOrderManagerImpl l_orderMgr =
                            (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
                        //6: get�����P��
                        String l_strOrderRequestNumber = l_orderAcceptedParams.getOrderRequestNumber();
                        log.debug("get�����P��: " + l_strOrderRequestNumber);
                        l_orderUnit = l_orderMgr.getOrderUnit(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            ProductTypeEnum.IFO,
                            l_strOrderRequestNumber);
                        if (l_orderUnit == null)
                        {
                            log.error("The orderUnit is null.");
                            throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }

                        //7:notify����
                        //��萔��
                        // �����ʒm�L���[.�������� ! = NULL �̏ꍇ
                        if (!l_orderAcceptedParams.getCloseQuantityIsNull())
                        {
                            // �����P��.�s�ꂩ��m�F�ς݂̐���-�����ʒm�L���[.��������
                            l_dblExecQuantity =
                                l_orderUnit.getConfirmedQuantity() - l_orderAcceptedParams.getCloseQuantity();
                        }
                        // �����ʒm�L���[.�������� = NULL �̏ꍇ
                        else
                        {
                            // �敨OP�����ʒm�L���[�e�[�u��.��萔��
                            l_dblExecQuantity = l_orderAcceptedParams.getExecutedQuantity();
                        }

                        if (Double.isNaN(l_dblExecQuantity))
                        {
                            l_dblExecQuantity = 0D;
                        }
                        log.debug("��萔��:" + l_dblExecQuantity);
                        //�������R�R�[�h
                        l_strCloseReasonCode = l_orderAcceptedParams.getReasonCode();
                        log.debug("�������R�R�[�h" + l_strCloseReasonCode);
                        //get�����ʒm�敪
                        l_strCloseNotifyType = l_orderAcceptedParams.getCloseNotifyType();
                        log.debug("�����ʒm�敪:" + l_strCloseNotifyType);

                        // TransactionCallback����
                        WEB3IfoCloseNotifyNotifyCloseTransactionCallback l_transactionCallback =
                            new WEB3IfoCloseNotifyNotifyCloseTransactionCallback(
                                l_orderUnit,
                                l_dblExecQuantity,
                                l_strCloseReasonCode,
                                l_strCloseNotifyType,
                                l_orderAcceptedParams);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                    catch (Exception l_exp)
                    {
                        //--------------------
                        //�����ΏۃL���[UPDATE�@@(�G���[��)
                        //--------------------
                        if (l_exp instanceof WEB3BaseRuntimeException)
                        {
                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                            if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                            {
                                log.debug("�������b�N���s�F" + l_orderAcceptedParams.toString());
                                continue;
                            }
                        }
                        
                        if (l_exp instanceof DataCallbackException)
                        {
                            DataCallbackException l_wre = (DataCallbackException) l_exp;
                            if (WEB3ErrorCatalog.BUSINESS_ERROR_01975.equals((ErrorInfo)l_wre.getDetails()))
                            {
                                log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁF" +
                                    l_orderAcceptedParams.toString());
                                l_orderAcceptedParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                                l_queryProcessor.doUpdateQuery(l_orderAcceptedParams);
                                continue;
                            }
                        }

                        //�G���[������ȊO�̏ꍇ�@@(=>�G���[)
                        log.debug("�ꌏ�����ɂăG���[�����F" + l_orderAcceptedParams.toString());
                        l_orderAcceptedParams.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_orderAcceptedParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_orderAcceptedParams);
                    }
                }
            }
        log.debug("Exit the one loop.");
        log.exiting(STR_METHOD_NAME);
        return null;
        }
    }

}






@
