head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������������ʒm�T�[�r�X�����N���X(WEB3RuitoTradeOrderNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 �m�X (���u) �V�K�쐬
Revesion History : 2007/12/26 ���g (���u) �d�l�ύX No097
Revesion History : 2008/01/09 ���� (SRA) �d�l�ύX No098
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;

import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyService;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse;
import webbroker3.xbruito.data.HostRuitoOrderNotifyRow;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;

/**
 * �ݐϓ������������ʒm�T�[�r�X�����N���X <BR>
 */
public class WEB3RuitoTradeOrderNotifyServiceImpl
    implements WEB3RuitoTradeOrderNotifyService
{
       /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyServiceImpl.class);

    final String STR_METHOD_NAME =
        "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";

    /**
     * @@roseuid 40B3EE9800AB
     */
    public WEB3RuitoTradeOrderNotifyServiceImpl()
    {

    }
   /**
    * �ݐϓ������������ʒm�T�[�r�X���������{����B<BR>
    * <BR>
    * �V�[�P���X�}�u�i�ݐϓ����j���������ʒm�v�Q�ƁB<BR>
    * <BR>
    * @@param l_request - ���N�G�X�g�f�[�^ <BR>
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 405A4E0300E9
    */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";

        log.entering(STR_METHOD_NAME);

        if(l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
        }
        WEB3RuitoDealingOrderNotifyRequest l_execOrderNotifyRequest =
            (WEB3RuitoDealingOrderNotifyRequest) l_request;

        //create���X�|���X( )
        WEB3RuitoDealingOrderNotifyResponse l_execEndNotifyResponse =
            (WEB3RuitoDealingOrderNotifyResponse)
                l_execOrderNotifyRequest.createResponse();

        // �ݓ����������ʒm���s��
        try
        {
            //�N�G���v���Z�b�T�̃C���X�^���X���擾����B
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            WEB3BaseException l_baseExp =
                (WEB3BaseException) l_qp.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new WEB3RuitoTradeOrderNotifyTransactionCallback());
            if (l_baseExp != null)
            {
                throw l_baseExp;
            }
        }
        catch (DataException l_ex)
        {
            log.error("_DataException_");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                "�ݓ����������ʒm�L���[�e�[�u���ւ̃A�N�Z�X�Ɏ��s���܂����B",
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_execEndNotifyResponse;

    }

    public class WEB3RuitoTradeOrderNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * �f�t�H���g�R���X�g���N�^ <BR>
         * @@roseuid 40876C14028B
         */
        public WEB3RuitoTradeOrderNotifyTransactionCallback()
        {

        }

        /**
         * ������t���������{����B<BR>
         * <BR>
         * �V�[�P���X�}�u�i�ݓ������ʒm�jprocess�v�Q�ƁB<BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40876C10002A
         */
        public Object process()
            throws
                DataNetworkException,
                DataQueryException,
                DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            String l_strWhere = "status=?";
            String l_strCondition = null;

            log.entering(STR_METHOD_NAME);
            // 1.1 �����Ώۃ��R�[�h�擾
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisHostRuitoOrderNotify = l_qp.doFindAllQuery(
                HostRuitoOrderNotifyRow.TYPE,
                l_strWhere,
                l_strCondition,
                new Object[] { WEB3StatusDef.NOT_DEAL });
            // 1.2�@@�ݓ����������ʒmUnitService���擾����B
            WEB3RuitoTradeOrderNotifyUnitService l_ruitoUnitService =
                (WEB3RuitoTradeOrderNotifyUnitService) Services.getService(
                    WEB3RuitoTradeOrderNotifyUnitService.class);
            int l_size = l_lisHostRuitoOrderNotify.size();

            // 1.3�@@�ݓ����������ʒm�m��C���^�Z�v�^�𐶐�����B
            WEB3RuitoTradedOrderNotifyDecisionInterceptor l_ruitoInterceptor =
                new WEB3RuitoTradedOrderNotifyDecisionInterceptor();
            //1.4 ���[�v����
            for (int i = 0; i < l_size; i++)
            {
                HostRuitoOrderNotifyParams l_orderNotifyParams =
                    (HostRuitoOrderNotifyParams) l_lisHostRuitoOrderNotify.get(i);
                HashMap l_map = new HashMap();
                String l_strUpdateWhere = " institution_code = ? "
                        + " and branch_code = ? " + " and order_request_number = ? ";
                String[] l_strUpdateWhereValues = {
                        l_orderNotifyParams.getInstitutionCode(),
                        l_orderNotifyParams.getBranchCode(),
                        l_orderNotifyParams.getOrderRequestNumber() };

                try
                {
                    //getInstitution(�،���ЃR�[�h)
                    // ��ЃR�[�h�F �ݓ������ʒm�L���[�e�[�u��Params.get�،���ЃR�[�h()
                    Institution l_institution = null;
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeAccountManager l_accMgr =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    l_institution = l_accMgr.getInstitution(l_orderNotifyParams.getInstitutionCode());

                    try
                    {
                        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                        //�،���ЃR�[�h�F���M�����ʒm�L���[�e�[�u��Params�Dget�،���ЃR�[�h()
                        //���X�R�[�h�F���M�����ʒm�L���[�e�[�u��Params�Dget���X�R�[�h()
                        //�����R�[�h�F ���M�����ʒm�L���[�e�[�u��Params�Dget�ڋq�R�[�h()
                        l_accMgr.getMainAccount(
                            l_orderNotifyParams.getInstitutionCode(),
                            l_orderNotifyParams.getBranchCode(),
                            l_orderNotifyParams.getAccountCode());
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        l_map.put("status", WEB3StatusDef.DEALT);

                        l_qp.doUpdateAllQuery(
                            HostRuitoOrderNotifyRow.TYPE,
                            l_strUpdateWhere,
                            l_strUpdateWhereValues,
                            l_map);

                        continue;
                    }

                    try
                    {
                        //get�ݓ������iwith�R�[�Xand�v�����j(Institution, String, String)
                        //[����]
                        //  �،���ЁF �擾�����،���ЃI�u�W�F�N�g
                        //  �R�[�X�F �ݓ������ʒm�L���[Params.get�R�[�X()�̖߂�l
                        //  �v�����F �ݓ������ʒm�L���[Params.get�v����()�̖߂�l
                        TradingModule l_tradingModule =
                            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
                        WEB3RuitoProductManager l_ruitoProductManager =
                            (WEB3RuitoProductManager)l_tradingModule.getProductManager();

                        l_ruitoProductManager.getRuitoProductWithCoursePlan(
                            l_institution,
                            l_orderNotifyParams.getCourse(),
                            l_orderNotifyParams.getPlan());
                    }
                    catch (WEB3BaseRuntimeException l_ex)
                    {
                        if(WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo())){
                            l_map.put("status", WEB3StatusDef.DEALT);

                            l_qp.doUpdateAllQuery(
                                HostRuitoOrderNotifyRow.TYPE,
                                l_strUpdateWhere,
                                l_strUpdateWhereValues,
                                l_map);

                            continue;
                        }
                    }

                    // TransactionCallback����
                    WEB3RuitoTradeOrderNotifyNormalTransactionCallback l_transactionCallback =
                    new WEB3RuitoTradeOrderNotifyNormalTransactionCallback(
                        l_orderNotifyParams,
                        l_ruitoInterceptor);

                    // doTransaction()
                    l_qp.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_transactionCallback);
                }
                catch (Exception l_ex)
                {
                    if(l_ex instanceof WEB3BaseRuntimeException)
                    {
                        //�������b�N�Ɏ��s���ăG���[�����������ꍇ�A
                        //�����ΏۃL���[���X�V���Ȃ��B
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("�������b�N���s�F" + l_orderNotifyParams.toString());
                            continue;
                        }
                    }
                    //����ȊO�̗�O���X���[�����ꍇ�A
                    log.debug("__an Exception__ ");
                    log.error("�ꌏ�����ɂė�O�����������ꍇ",l_ex);
                    //�����Ώۂ̗ݓ������ʒm�L���[���R�[�h.�����敪�Ɂh9�F�G���[�h���Z�b�g���X�V����
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    //1.4.2 �L���[�e�[�u���ɏ����ς��X�V
                    l_qp.doUpdateAllQuery(
                    HostRuitoOrderNotifyRow.TYPE,
                    l_strUpdateWhere,
                    l_strUpdateWhereValues,
                    l_map);
                }
            }           
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
