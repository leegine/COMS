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
filename	WEB3EquityChangeCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������t�T�[�r�XImpl(WEB3EquityChangeCancelAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 �����(���u) �V�K�쐬
                   2004/09/14 ���C�g(���u) �C��
                   2004/09/22 Ḗ@@��(���u) �C��
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�R�S�Q
                   2004/12/21 �����a��(SRA) JavaDoc�C��
                   2005/01/05 �����a��(SRA) �������b�N�C���Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2005/03/09 ���iFLJ�j�L���[�e�[�u���ɂ�鉺�菈���̃g�����U�N�V��������ύX�A
 �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�񓯊���������WEB3AsynEquityChangeCancelAcceptServiceImpl�ֈڂ��i�V�K�N���X�j
*/
package webbroker3.equity.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;


/**
 *  (�������������t�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �������������t�T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptServiceImpl
    implements WEB3EquityChangeCancelAcceptService
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B)�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityChangeCancelAcceptServiceImpl.class);

    /**
     * <p>�iexecute�j�B</p>
     * <p>�������������t���������{����B<br>
     * <br>
     * �V�[�P���X�}�u�i�������������t�T�[�r�X�j���������t�v�Q�ƁB</p>
     * @@param l_request ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3EquityChangeCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CF3500E0
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityChangeCancelAcceptRequest l_eqOrderAcceptRequest =
            (WEB3EquityChangeCancelAcceptRequest) l_request;

        //1.1 validate()
        l_eqOrderAcceptRequest.validate();

        // 1.2. �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_eqOrderAcceptRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityChangeCancelAcceptServiceImpl(
            l_eqOrderAcceptRequest));

        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }

//>>>>>>�ȍ~����WEB3AsynEquityChangeCancelAcceptServiceImpl�ֈڂ��B2005/03/09  ���iFLJ�j

//        // �������������t�������s��
//        try
//        {
//            //1.2 getDefaultProcessor()
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//            //1.3 �������������tTransactionCallback()
//            WEB3EquityChangeCancelAcceptServiceTransactionCallback l_callBack = new WEB3EquityChangeCancelAcceptServiceTransactionCallback();
//            //1.4 set���ʃR�[�h�v���t�B�N�X�ꗗ(���ʃR�[�h�v���t�B�N�X�ꗗ : String[])
//            l_callBack.setOrderRequestNumberPrefixGroup(l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);
//            //1.5 doTransaction((�g�����U�N�V���������i=TX_CREATE_NEW) : int, arg1 : TransactionCallback)
//            WEB3BaseException l_baseExp = (WEB3BaseException) l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callBack);
//            if (l_baseExp != null)
//            {
//                log.exiting(STR_METHOD_NAME);
//                throw l_baseExp;
//            }
//        }
//        catch (DataException l_exp)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
//        }
//
//        WEB3EquityChangeCancelAcceptResponse l_eqOrderAcceptResponse =
//            (WEB3EquityChangeCancelAcceptResponse) l_eqOrderAcceptRequest.createResponse();
//        log.exiting(STR_METHOD_NAME);
//        return l_eqOrderAcceptResponse;
//    }
//
//    /**
//     * <p>�i�������������tTransactionCallback�j�B</p>
//     * <p>�������������tTransactionCallback�N���X�B</p>
//     */
//    private class WEB3EquityChangeCancelAcceptServiceTransactionCallback
//        implements TransactionCallback
//    {
//
//        /**
//         * <p>�i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
//         * <p>���ʃR�[�h�v���t�B�N�X�ꗗ�B</p>
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//        /**
//         * <p>�i�������������tTransactionCallback �j�B</p>
//         * <p>�R���X�g���N�^�B</p>
//         */
//        public WEB3EquityChangeCancelAcceptServiceTransactionCallback()
//        {
//        }
//
//        /**
//         * <p>�iprocess�j�B</p>
//         * <p>�g�����U�N�V�������������{����B<br>
//         * <br>
//         * �V�[�P���X�}<br>
//         * �u�i�������������t�T�[�r�X�jprocess�v�Q�ƁB</p>
//         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j<BR>
//         * @@throws DataCallbackException
//         * @@throws DataNetworkException
//         * @@throws DataQueryException
//         * @@roseuid 4137CF3501A8
//         */
//        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME = "process()";
//            log.entering(STR_METHOD_NAME);
//
//            // 1.1 get���ʃR�[�h�v���t�B�N�X�ꗗ()
//            log.debug("1.1 get���ʃR�[�h�v���t�B�N�X�ꗗ");
//            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
//
//            // 1.2 [��������]������t�L���[�e�[�u��.�f�[�^�R�[�h == "�����������"�iAI80B�j
//            // ������t�L���[�e�[�u��.�����敪 == "������"
//            log.debug("1.2 ������t�L���[�e�[�u���ǂݍ���");
//
//            StringBuffer l_sbWhere = new StringBuffer();
//            l_sbWhere.append(" request_code = ? ");
//            l_sbWhere.append(" and status = ? ");
//            int l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;
//            l_sbWhere.append(" and (");
//            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
//            {
//                if(i == 0)
//                {
//                    l_sbWhere.append(" substr(order_request_number , 1 , 2) = ?");
//                } else
//                {
//                    l_sbWhere.append(" or substr(order_request_number , 1 , 2) = ?");
//                }
//            }
//            l_sbWhere.append(" )");
//            Object[] l_objEquityOrderUnitWhere = new Object[l_intRequestNumberPrefixGroupCnt + 2];
//            l_objEquityOrderUnitWhere[0] = WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL;
//            l_objEquityOrderUnitWhere[1] = WEB3StatusDef.NOT_DEAL;
//            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
//            {
//                l_objEquityOrderUnitWhere[i + 2] = l_orderRequestNumberPrefixGroup[i];
//                log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ�F" + l_orderRequestNumberPrefixGroup[i]);
//            }
//
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//            Map l_mapChanges = new HashMap();
//
//            // ������t�L���[�e�[�u���̓ǂݍ��ݎ��s
//            List l_lisHostEqtypeOrderAccept =
//                l_qp.doFindAllQuery(
//                    HostEqtypeOrderAcceptRow.TYPE,
//                    l_sbWhere.toString(),
//                    null,
//                    "FOR UPDATE",
//                    l_objEquityOrderUnitWhere);
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tm =
//            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            //WEB3EquityAccountManager l_tm
//            WEB3GentradeAccountManager l_accMa = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
//            WEB3EquityChangeCancelAcceptUnitService l_changeCancelService =
//                (WEB3EquityChangeCancelAcceptUnitService) Services.getService(WEB3EquityChangeCancelAcceptUnitService.class);
//            if(l_lisHostEqtypeOrderAccept != null)
//            {
//                Iterator l_iterator = l_lisHostEqtypeOrderAccept.iterator();
//                HostEqtypeOrderAcceptParams l_params;
//
//                // 1.3 * �擾�����L���[�e�[�u���̃��R�[�h�����ALoop����
//                log.debug("1.3 �擾�����L���[�e�[�u���̃��R�[�h�����ALoop����");
//                while (l_iterator.hasNext())
//                {
//                    l_params = new HostEqtypeOrderAcceptParams((HostEqtypeOrderAcceptRow)l_iterator.next());
//                    try
//                    {
//                        // 1.3.1 notify���������t(����������t�L���[Params : ����������t�L���[Params)
//                        log.debug("1.3.1 notify���������t(����������t�L���[Params : ����������t�L���[Params)");
//                        l_changeCancelService.notifyChangeCancelAccept(l_params);
//
//                        // 1.3.2 * ����������t�L���[�e�[�u��.�����敪��update����
//                        log.debug("1.3.2 ����������t�L���[�e�[�u��.�����敪��update����");
//                        l_mapChanges.put("status", WEB3StatusDef.DEALT);
//                        l_qp.doUpdateQuery(l_params.getPrimaryKey(),l_mapChanges);
//                    }
//                    catch(Exception l_ex)
//                    {
//                        if (l_ex instanceof WEB3BaseRuntimeException)
//                        {
//                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
//                            if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
//                            {
//                                log.debug("�������b�N���s�F" + l_params.toString());
//                                continue;
//                            }
//                        }
//                        // 1.3.2 * ����������t�L���[�e�[�u��.�����敪��update����
//                        log.debug("1.3.2 ����������t�L���[�e�[�u��.�����敪��update����");
//                        l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
//                        l_qp.doUpdateQuery(l_params.getPrimaryKey(),l_mapChanges);
//                    }
//                }
//            }
//
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }
//
//
//        /**
//         * <p>�iget���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
//         * <p>���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B</p>
//         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
//         */
//        public String[] getOrderRequestNumberPrefixGroup() {
//            return this.orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * <p>�iset���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
//         * <p>�����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B</p>
//         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
//            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
//        }
//
//    }

}
@
