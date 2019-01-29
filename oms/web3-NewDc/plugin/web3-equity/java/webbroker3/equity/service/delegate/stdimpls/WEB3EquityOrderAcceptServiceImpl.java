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
filename	WEB3EquityOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t�T�[�r�X�N���X(WEB3EquityOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 �����@@���F(SRA) �V�K�쐬
                   2004/09/20 羐� (���u) �C��
                   2004/11/05 �@@�� �C��
                   2004/12/15 �X��  (SRA) �c�Č��Ή�
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2005/03/09 ���iFLJ�j�L���[�e�[�u���ɂ�鉺�菈���̃g�����U�N�V��������ύX�A
 �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�񓯊���������WEB3AsynEquityOrderAcceptServiceImpl�ֈڂ��i�V�K�N���X�j

*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.equity.message.WEB3EquityOrderAcceptRequest;
import webbroker3.equity.message.WEB3EquityOrderAcceptResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;

/**
 * �i����������t�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ����������t�T�[�r�X�N���X�B
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptServiceImpl implements WEB3EquityOrderAcceptService
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptServiceImpl.class);

    /**
     * �i�R���X�g���N�^�j�B
     * @@roseuid 4042ED5D016D
     */
    public WEB3EquityOrderAcceptServiceImpl()
    {
    }

    /**
     * �i������t���������{����j�B<BR>
     * <BR>
     * �V�[�P���X�}�u�i����������t�T�[�r�X�j������t�v�Q�ƁB
     * @@param l_request ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3EquityOrderAcceptResponse
     * @@roseuid 4042ED5D016E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityOrderAcceptRequest l_eqOrderAcceptRequest =
            (WEB3EquityOrderAcceptRequest)l_request;
        WEB3EquityOrderAcceptResponse l_eqOrderAcceptResponse =
            (WEB3EquityOrderAcceptResponse)l_eqOrderAcceptRequest.createResponse();

        //validate
        l_eqOrderAcceptRequest.validate();

        // 1.2. �X���b�h�J�n
        new WEB3GentradeDaemonTriggerManager().startThread(l_eqOrderAcceptRequest.threadNo.longValue());
        // 1.3. �񓯊����s
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynEquityOrderAcceptServiceImpl(
            l_eqOrderAcceptRequest));

        log.exiting(STR_METHOD_NAME);
        return l_eqOrderAcceptResponse;
    }

//>>>>>>�ȍ~����WEB3AsynEquityOrderAcceptServiceImpl�ֈڂ��B2005/03/09  ���iFLJ�j

//
//        try
//        {
//            //getDefaultProcessor
//            QueryProcessor l_qp = Processors.getDefaultProcessor();
//
//            //����������tTransactionCallback
//            WEB3EquityOrderAcceptServiceTransactionCallback l_eqOdrAptSrvTranCallback;
//            l_eqOdrAptSrvTranCallback = new WEB3EquityOrderAcceptServiceTransactionCallback();
//
//            //set���ʃR�[�h�v���t�B�N�X�ꗗ
//            l_eqOdrAptSrvTranCallback.setOrderRequestNumberPrefixGroup(l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);
//
//			//doTransaction
//			WEB3BaseException l_baseExp =
//							  (WEB3BaseException)l_qp.doTransaction(
//								  QueryProcessor.TX_JOIN_EXISTING,
//								  l_eqOdrAptSrvTranCallback);
//            if (l_baseExp != null)
//            {
//                log.exiting(STR_METHOD_NAME);
//                throw l_baseExp;
//            }
//        }
//        catch (DataException l_de)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_de.getMessage(),
//                l_de);
//        }
//
//        log.exiting(STR_METHOD_NAME);
//        return l_eqOrderAcceptResponse;
//    }
//
//    /**
//     * �i�����敪��update����j�B
//     * @@param l_hostEqtypeOrderAcceptParams ����������t�L���[Params
//     * @@param l_strStatus �����敪
//     * @@roseuid 4042ED5D016E
//     */
//    public void updateStatus(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams,String l_strStatus)
//       throws WEB3SystemLayerException
//    {
//        final String STR_METHOD_NAME =
//            "updateStatus(HostEqtypeOrderAcceptParams, String)";
//		log.entering(STR_METHOD_NAME);
//        if (l_hostEqtypeOrderAcceptParams == null)
//        {
//            throw new WEB3BaseRuntimeException(
//               WEB3ErrorCatalog.SYSTEM_ERROR_80019,
//               this.getClass().getName() + "." + STR_METHOD_NAME);
//        }
//
//        try
//        {
//            l_hostEqtypeOrderAcceptParams.setStatus(l_strStatus);
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            l_queryProcessor.doUpdateQuery(l_hostEqtypeOrderAcceptParams);
//        }
//        catch (DataException l_de)
//        {
//            log.error(STR_METHOD_NAME,l_de);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_de.getMessage(),
//                l_de);
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    /**
//     * �i����������tTransactionCallback�j�B<BR>
//     * <BR>
//     * ����������t�������s���g�����U�N�V�����E�R�[���o�b�N�N���X�B
//     * @@version 1.0
//     */
//    private class WEB3EquityOrderAcceptServiceTransactionCallback implements TransactionCallback
//    {
//
//        /**
//         * �i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B
//         */
//        private String[] orderRequestNumberPrefixGroup;
//
//
//        /**
//         * �iget���ʃR�[�h�v���t�B�N�X�ꗗ�j�B<BR>
//         * <BR>
//         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B
//         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
//         */
//        public String[] getOrderRequestNumberPrefixGroup() {
//            return this.orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * �iset���ʃR�[�h�v���t�B�N�X�ꗗ�j�B<BR>
//         * <BR>
//         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B
//         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
//         */
//        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
//            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
//        }
//
//        /**
//         * �iprocess�j�B<BR>
//         * <BR>
//         * �g�����U�N�V�������������{����B <BR>
//         * <BR>
//         * �V�[�P���X�} <BR>
//         * �u�i����������t�T�[�r�X�jprocess�v�Q�ƁB <BR>
//         * <BR>
//         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j<BR>
//         * @@throws DataNetworkException
//         * @@throws DataQueryException
//         * @@throws DataCallbackException
//         * @@roseuid 4050215102CD
//         */
//
//        public Object process()
//            throws DataNetworkException, DataQueryException, DataCallbackException
//        {
//            final String STR_METHOD_NAME =
//                "WEB3EquityOrderAcceptServiceTransactionCallback.process()";
//            log.entering(STR_METHOD_NAME);
//            HostEqtypeOrderAcceptRow l_hostEqtypeOrderAcceptRow;
//            HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams;
//
//            //get���ʃR�[�h�v���t�B�N�X�ꗗ
//            String[] l_odrReqNumPfxGrp = this.getOrderRequestNumberPrefixGroup();
//
//
//            //������t�L���[�e�[�u���Ǎ�
//            List l_lisRecords;                          //�L���[�e�[�u�����R�[�h�̃��X�g
//            String l_strWhereQuery = null;
//            Object l_objWhereQuerys[] = null;
//            final String l_strCondition = "for update";
//
//            final int l_iOdrReqNumPfxCnt       = l_odrReqNumPfxGrp.length;
//            final String l_strDataCodePart      = "request_code = ?";
//            final String l_strReqCodeCondPart   = "order_request_number like ?";
//            final String l_strStatusPart        = "status = ?";
//            int l_iQueryParamCnt;
//
//            if (l_iOdrReqNumPfxCnt > 0)
//            {
//                //where��p�^�[��������̐���
//                l_strWhereQuery = l_strReqCodeCondPart;
//
//                for (int i = 1; i < l_iOdrReqNumPfxCnt; i++)
//                {
//                    l_strWhereQuery += " or " + l_strReqCodeCondPart;
//                }
//
//                l_strWhereQuery =
//                    l_strDataCodePart + " and (" + l_strWhereQuery + ") and " + l_strStatusPart;
//
//
//                //where��p�����[�^�̐���
//                l_objWhereQuerys = new Object[l_iOdrReqNumPfxCnt + 2];
//
//                l_objWhereQuerys[0] = WEB3HostRequestCodeDef.EQUITY_ORDER_ACCEPT;
//                l_iQueryParamCnt = 1;
//
//				for (int i = 0; i < l_iOdrReqNumPfxCnt; i++)
//                {
//                    l_objWhereQuerys[l_iQueryParamCnt] = l_odrReqNumPfxGrp[i] + "%";
//                    l_iQueryParamCnt++;
//                }
//
//                l_objWhereQuerys[l_iQueryParamCnt] = WEB3HostStatusDef.NOT_STARTED_PROCESS;
//
//                //Log�o��
//                log.debug("doFindAllQuery(arg1, arg2, arg3, arg4)");
//                log.debug("arg1 = [" + HostEqtypeOrderAcceptRow.TYPE.toString() + "]");
//                log.debug("arg2 = [" + l_strWhereQuery + "]");
//                log.debug("arg3 = [" + l_strCondition + "]");
//                String l_strParamListJoinedForLog = l_objWhereQuerys[0].toString();
//                for (int i = 0; i < l_objWhereQuerys.length - 1; i++)
//                {
//                    l_strParamListJoinedForLog += l_objWhereQuerys[i].toString() + ",";
//                }
//                l_strParamListJoinedForLog += l_objWhereQuerys[l_objWhereQuerys.length - 1].toString();
//                log.debug("arg4 = [" + l_strParamListJoinedForLog + "]");
//
//                // l_list
//                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//                l_lisRecords = l_queryProcessor.doFindAllQuery(
//                        HostEqtypeOrderAcceptRow.TYPE, l_strWhereQuery, l_strCondition, l_objWhereQuerys);
//            }
//            else
//            {
//                l_lisRecords = null;
//            }
//
//            //2) get ����������t�ꌏ�T�[�r�X
//            WEB3EquityOrderAcceptUnitService l_service =
//                (WEB3EquityOrderAcceptUnitService)Services.getService(
//                    WEB3EquityOrderAcceptUnitService.class);
//
//            //�擾�����L���[�e�[�u���̃��R�[�h�����ALoop����
//            int l_intSize = 0 ;
//            if (l_lisRecords != null)
//            {
//                l_intSize = l_lisRecords.size();
//            }
//            for (int i = 0; i < l_intSize; i++)
//            {
//                //�����敪
//                String l_strStatus = WEB3HostStatusDef.COMPLETE_PROCESS;
//
//                l_hostEqtypeOrderAcceptRow =
//                    (HostEqtypeOrderAcceptRow) l_lisRecords.get(i);
//                l_hostEqtypeOrderAcceptParams =
//                    new HostEqtypeOrderAcceptParams(l_hostEqtypeOrderAcceptRow);
//
//                try
//                {
//                    //3)  ����������t�ꌏ�T�[�r�X.notify������t
//                    l_service.notifyOrderAccept(l_hostEqtypeOrderAcceptParams);
//                }
//                catch (Exception l_e)
//                {
//                    //--------------------
//                    //�����ΏۃL���[UPDATE�@@(�G���[��)
//                    //--------------------
//                    if (l_e instanceof WEB3BaseRuntimeException)
//                    {
//                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_e;
//                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
//                        {
//                            log.debug("�������b�N���s�F" + l_hostEqtypeOrderAcceptParams.toString());
//                            continue;
//                        }
//                    }
//
//                    //�G���[������ȊO�̏ꍇ�@@(=>�G���[)
//                    log.debug("�ꌏ�����ɂăG���[�����F" + l_hostEqtypeOrderAcceptParams.toString());
//                    l_strStatus = WEB3HostStatusDef.DATA_ERROR;
//                }
//
//                //����������t�L���[�e�[�u��.�����敪��update��commit����
//                try
//                {
//                    updateStatus(l_hostEqtypeOrderAcceptParams, l_strStatus);
//                }
//                catch (WEB3SystemLayerException l_wse)
//                {
//                    log.error(STR_METHOD_NAME,l_wse);
//                    return l_wse;
//                }
//            }
//            log.exiting(STR_METHOD_NAME);
//            return null;
//
//        }
//    }

}
@
