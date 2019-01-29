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
filename	WEB3EquityOrderExecNotifyMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒm���C���T�[�r�XImpl(WEB3EquityOrderExecNotifyMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 �������F(SRA) �V�K�쐬
                   2005/01/05 �����a��(SRA) �������b�N�Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2005/03/09 ���iFLJ�j�L���[�e�[�u���ɂ�鉺�菈���̃g�����U�N�V��������ύX�A
 �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�񓯊���������WEB3AsynEquityOrderExecNotifyMainServiceImpl�ֈڂ��i�V�K�N���X�j
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.message.WEB3EquityExecNotifyMainRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyMainService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;

/**
 * �i�����o���ʒm���C���T�[�r�X�����N���X�j�B
 * @@author  : �������F
 * @@version : 1.0
 */
public class WEB3EquityOrderExecNotifyMainServiceImpl implements WEB3EquityOrderExecNotifyMainService
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyMainServiceImpl.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityOrderExecNotifyMainServiceImpl()
    {
    }

    /**
     * �����o���ʒm���C���T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����o���ʒm���C���T�[�r�X�j�o���ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3EquityOrderExecNotifyMainServiceImpl�Fexecute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityExecNotifyMainRequest l_execNotifyRequest =
            (WEB3EquityExecNotifyMainRequest)l_request;

        // 1.1. validate()
        l_execNotifyRequest.validate();
        // 1.2. �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_execNotifyRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityOrderExecNotifyMainServiceImpl(
            l_execNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

//>>>>>>�ȍ~����WEB3AsynEquityOrderExecNotifyMainServiceImpl�ֈڂ��B2005/03/09  ���iFLJ�j
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
//        WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
//            new WEB3EquityOrderExecNotifyMainTransactionCallback();
//
//        // 1.4. set���ʃR�[�h�v���t�B�N�X�ꗗ()
//        l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_execNotifyRequest.orderRequestNumberPrefixGroup);
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
//     * (�����o���ʒm���C��TransactionCallback)<BR>
//     * <BR>
//     * �g�����U�N�V�������������{��������N���X�B<BR>
//     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
//     */
//    public class WEB3EquityOrderExecNotifyMainTransactionCallback implements TransactionCallback
//    {
//        /**
//         * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * �R���X�g���N�^�B<BR>
//         */
//        public WEB3EquityOrderExecNotifyMainTransactionCallback()
//        {
//        }
//
//        /**
//         * (set�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
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
//         * (get�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
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
//         * (process)<BR>
//         * <BR>
//         * �g�����U�N�V�������������{����B<BR>
//         * <BR>
//         * �V�[�P���X�}<BR>
//         * �u�i�����o���ʒm���C���T�[�r�X�jprocess�v�Q�ƁB<BR>
//         * @@return Object
//         * @@throws DataQueryException, DataNetworkException, IllegalStateException
//         */
//        public Object process()
//            throws DataQueryException, DataNetworkException, IllegalStateException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            // 1.2. �����o���ʒm�L���[�e�[�u���ǂݍ���
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
//            l_objWhere[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_EXEC_NOTICE;
//            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;
//            for (int i = 0;i < l_intLength;i++)
//            {
//                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
//            }
//            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
//            List l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
//                HostEquityOrderExecNotifyRow.TYPE,
//                l_sbWhere.toString(),
//                null,
//                "for update",
//                l_objWhere);
//
//            // 1.3. �擾�����L���[�e�[�u���̃��R�[�h�����ALoop
//            HostEquityOrderExecNotifyParams l_params = null;
//
//            int l_intNum = l_lisSearchResult.size();
//
//            for (int i = 0; i < l_intNum;i++)
//            {
//                try
//                {
//                    log.debug("�����o���ʒm���C���FLoop���� i = " + i);
//
//                    l_params = (HostEquityOrderExecNotifyParams)l_lisSearchResult.get(i);
//                    log.debug("�@@�@@���ʃR�[�h�F [" + l_params.getOrderRequestNumber() + "]");
//                    // 1.3.1. get�����P��(�،���ЃR�[�h, ���X�R�[�h, ���i�^�C�v, ���ʔԍ�)
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
//                    // �����o���ʒm�ꌏTransactionCallback����
//                    WEB3EquityOrderExecNotifyTransactionCallback l_transactionCallback =
//                        new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_params);
//
//                    // doTransaction()
//                    l_QueryProcessor.doTransaction(
//                        QueryProcessor.TX_CREATE_NEW,
//                        l_transactionCallback);
//
//                    l_params.setStatus(WEB3StatusDef.DEALT);
//                    l_QueryProcessor.doUpdateQuery(l_params);
//                }
//                catch (WEB3BaseException l_exp)
//                {
//                    log.error(l_exp.getMessage(), l_exp);
//                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    l_QueryProcessor.doUpdateQuery(l_params);
//                }
//                catch (DataCallbackException l_exp)
//                {
//                    ErrorInfo l_errorInfo = (ErrorInfo)l_exp.getDetails();
//                    if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01734))
//                    {
//                        // �����������̃L���[�͏o�������X�L�b�v
//                        log.debug("�o�������X�L�b�v�i�Y�������������j " + l_params.toString());
//                        continue;
//                    }
//                    else if (l_errorInfo.equals(WEB3ErrorCatalog.SYSTEM_ERROR_80076))
//                    {
//                        log.debug("�������b�N���s�F" + l_params.toString());
//                        continue;
//                    }
//                    else if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01931))
//                    {
//                        log.debug("�ۗL���Y�c���ʃ`�F�b�N�G���[�F" + l_params.toString());
//                        continue;
//                    }
//                    else if (l_errorInfo.equals(WEB3ErrorCatalog.BUSINESS_ERROR_01934))
//                    {
//                        log.debug("�����c���ʃ`�F�b�N�G���[�F" + l_params.toString());
//                        continue;
//                    }
//
//                    log.error(l_exp.getMessage(),l_exp);
//                    if (l_errorInfo.error_class.equals(WEB3BusinessLayerException.class.getName()))
//                    {
//	                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
//                    }
//                    else
//                    {
//	                    l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
//                    }
//                    l_QueryProcessor.doUpdateQuery(l_params);
//                }
//                catch (Exception l_exp)
//                {
//                    log.error(l_exp.getMessage(), l_exp);
//	                l_params.setStatus(WEB3StatusDef.PROGRAM_ERROR);
//	                l_QueryProcessor.doUpdateQuery(l_params);
//                }
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//    }

}
@
