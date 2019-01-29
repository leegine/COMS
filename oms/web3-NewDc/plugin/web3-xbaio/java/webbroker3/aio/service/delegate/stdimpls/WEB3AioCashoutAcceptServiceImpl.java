head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o����t�T�[�r�X�����N���X(WEB3AioCashoutAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���E (���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostPaymentAcceptParams;
import webbroker3.aio.data.HostPaymentAcceptRow;
import webbroker3.aio.message.WEB3AioCashoutAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o����t�T�[�r�XImpl)<BR>
 * �o����t�T�[�r�X�����N���X
 *
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashoutAcceptServiceImpl implements WEB3AioCashoutAcceptService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutAcceptServiceImpl.class);

    /**
     * �o����t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o����t�j�o����t�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3SystemLayerException
     * @@roseuid 40FC7D5D0260
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)�o����t�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B
        WEB3AioCashoutAcceptTransactionCallback l_aioCashoutAcceptTransactionCallback =
            new WEB3AioCashoutAcceptTransactionCallback();

        try
        {
            // 1.2)�N�G���v���Z�b�T���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // 1.3)DB�g�����U�N�V�������������{����B
            //[doTransaction()�Ɏw�肷�����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@�o����tTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW,
                l_aioCashoutAcceptTransactionCallback);

            // 1.4)createResponse( )(�o����t���N�G�X�g::createResponse)
            WEB3AioCashoutAcceptResponse l_response =
                (WEB3AioCashoutAcceptResponse) l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }

    /**
     * (�o����tTransactionCallback)<BR>
     * �o����t�g�����U�N�V�����R�[���o�b�N�N���X
     */
    public class WEB3AioCashoutAcceptTransactionCallback implements TransactionCallback
    {
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioCashoutAcceptTransactionCallback.class);

        /**
         * (�o����tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@roseuid 40FC7EAA029D
         */
        public WEB3AioCashoutAcceptTransactionCallback()
        {

        }

        /**
         * ������t���������{����B<BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�o����t�jprocess�v�Q��<BR>
         * @@return Object
         * @@throws WEB3BusinessLayerException
         * @@throws WEB3SystemLayerException
         * @@roseuid 40FC7EDF01D2
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "WEB3AioCashoutAcceptTransactionCallback::process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1)�o��������t�L���[�e�[�u���Ǎ���
            //�ȉ��̏����� �o��������t�L���[�e�[�u������s���b�N�I�v�V�����ɂă��R�[�h���擾����B
            //[��������]
            //�f�[�^�R�[�h = "GI80A"�i�o��������t�j
            //�����敪 = "0"�i�������j
            String l_strWhere = " request_code = ? and status = ? ";
            // ���u�� U01832�̎b��Ή� start
//            String l_strCondition = " for update";
            String l_strCondition = null;
            // ���u�� U01832�̎b��Ή� end
            Object[] l_objWhereValues = {WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ACCEPT,
                     WEB3StatusDef.NOT_DEAL};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisHostPaymentAcceptResult =
                l_queryProcessor.doFindAllQuery(
                    HostPaymentAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            int l_intHostPaymentAcceptResultLength = 0;

            if(!l_lisHostPaymentAcceptResult.isEmpty())
            {
                l_intHostPaymentAcceptResultLength = l_lisHostPaymentAcceptResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            // ���u�� U01832�̎b��Ή� start
//            WEB3AioCashTransferAcceptUnitService l_aioCashTransferAcceptUnitService =
//                (WEB3AioCashTransferAcceptUnitService) Services.getService(
//                    WEB3AioCashTransferAcceptUnitService.class);
//
//            WEB3AioCashTransferCompleteUnitService l_aioCashTransferCompleteUnitService =
//                (WEB3AioCashTransferCompleteUnitService) Services.getService(
//                    WEB3AioCashTransferCompleteUnitService.class);
            // ���u�� U01832�̎b��Ή� end

            // 1.2)�擾�������R�[�h����Loop����
            for(int i=0; i<l_intHostPaymentAcceptResultLength; i++)
            {
                HostPaymentAcceptParams l_hostPaymentAcceptParams =
                    (HostPaymentAcceptParams) l_lisHostPaymentAcceptResult.get(i);
                // 1.2.1)�����P�ʃI�u�W�F�N�g���擾����B
                //[����]
                //�،���ЃR�[�h�F �o��������t�L���[�e�[�u��.�،���ЃR�[�h
                //���X�R�[�h�F �o��������t�L���[�e�[�u��.���X�R�[�h
                //�ڋq�R�[�h�F �o��������t�L���[�e�[�u��.�ڋq�R�[�h
                //���ʃR�[�h�F �o��������t�L���[�e�[�u��.���ʃR�[�h
                //�⏕�����^�C�v�F 1�i�a��������j
                String l_strInstitutionCode = l_hostPaymentAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostPaymentAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostPaymentAcceptParams.getAccountCode();
                String l_strOrderRequestNumber = l_hostPaymentAcceptParams.getOrderRequestNumber();


                boolean l_blnExc = false;
                try
                {
                    //get the AioOrderUnit
                    AioOrderUnit l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strOrderRequestNumber,
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    // ���u�� U01832�̎b��Ή� start
                    WEB3AioCashoutAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AioCashoutAcceptNormalTransactionCallback(
                            l_aioOrderUnit,
                            l_hostPaymentAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);


//                    // 1.2.2)���o����tDB�X�V�������s���B
//                    //[����]
//                    //�����P�ʁF �擾���������P�ʃI�u�W�F�N�g
//                    //�G���[�R�[�h�F ���o���`�[��t�L���[�e�[�u��.�G���[���b�Z�[�W
//                    //��t�ʒm�敪�F ���o���`�[��t�L���[�e�[�u��.��t�ʒm�敪
//                    l_aioCashTransferAcceptUnitService.execute(l_aioOrderUnit,
//                        l_hostPaymentAcceptParams.getErrorMessage(),
//                        l_hostPaymentAcceptParams.getAcceptDiv());
//
//                    //�o��������t�L���[�e�[�u��.��t�ʒm�敪 = "1"�i��t�����j�̏ꍇ
//                    if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(l_hostPaymentAcceptParams.getAcceptDiv()))
//                    {
//                        //1.2.3)��t�����̏ꍇ
//                        //���o�����������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
//                        //[����]
//                        //�����P�ʁF get�����P��()�Ŏ擾���������P��
//                        l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
//                    }
                    // ���u�� U01832�̎b��Ή� end
                }
                catch(WEB3BaseException l_ex)
                {
                    m_log.debug("__an WEB3BaseException ", l_ex);
                    l_blnExc = true;
                }
                catch(NotFoundException l_ex)
                {
                    m_log.debug("__an NotFoundException__ ", l_ex);
                    l_blnExc = true;
                }
                // ���u�� U01832�̎b��Ή� start
                catch(Exception l_ex)
                {
                    m_log.debug("__an Exception__ ", l_ex);
                    l_blnExc = true;
                }
                // ���u�� U01832�̎b��Ή� end

                //(*3)�o��������t�L���[�e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
                //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
                //"1"�i�����ρj�F ����ȊO�̏ꍇ
                HashMap l_hashMap = new HashMap();
                // ���u�� U01832�̎b��Ή� start
//                if(l_blnExc)
//                {
//                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);
//                }
//                else
//                {
//                    l_hashMap.put("status", WEB3StatusDef.DEALT);
//                }
//
//                String l_strRequestCode = l_hostPaymentAcceptParams.getRequestCode();
//
//                String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
//                    "and branch_code = ? and account_code = ? and order_request_number = ? ";
//
//                Object[] l_objUpdateWhereValues = {l_strRequestCode, l_strInstitutionCode,
//                        l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};
//
//                l_queryProcessor.doUpdateAllQuery(
//                    HostPaymentAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_objUpdateWhereValues,
//                    l_hashMap);

                if(l_blnExc)
                {
                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strRequestCode = l_hostPaymentAcceptParams.getRequestCode();

                    String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
                        "and branch_code = ? and account_code = ? and order_request_number = ? ";

                    Object[] l_objUpdateWhereValues = {l_strRequestCode, l_strInstitutionCode,
                            l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};

                    l_queryProcessor.doUpdateAllQuery(
                        HostPaymentAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_objUpdateWhereValues,
                        l_hashMap);
                }
                // ���u�� U01832�̎b��Ή� end
            }

            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
