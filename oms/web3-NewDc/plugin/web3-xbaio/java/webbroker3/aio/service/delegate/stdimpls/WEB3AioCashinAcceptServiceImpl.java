head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������t�T�[�r�X�����N���X(WEB3AioCashinAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬
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
import webbroker3.aio.data.HostCashTransOrderAcceptParams;
import webbroker3.aio.data.HostCashTransOrderAcceptRow;
import webbroker3.aio.message.WEB3AioCashinAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinAcceptService;
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
 * (������t�T�[�r�XImpl)<BR>
 * ������t�T�[�r�X�����N���X
 *
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinAcceptServiceImpl implements WEB3AioCashinAcceptService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinAcceptServiceImpl.class);

    /**
     * ������t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o����t�j������t�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FC785D0174
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("WEB3BackRequest is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)������t�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����
        WEB3AioCashinAcceptTransactionCallback l_aioCashinAcceptTransactionCallback =
            new WEB3AioCashinAcceptTransactionCallback();

        try
        {
            // 1.2)�N�G���v���Z�b�T���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // 1.3)DB�g�����U�N�V�������������{����B
            //[doTransaction()�Ɏw�肷�����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@������tTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(l_aioCashinAcceptTransactionCallback);

            // 1.4)createResponse( )(������t���N�G�X�g::createResponse)
            WEB3AioCashinAcceptResponse l_response =
                (WEB3AioCashinAcceptResponse) l_request.createResponse();

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
     * (������tTransactionCallback)<BR>
     * ������t�g�����U�N�V�����R�[���o�b�N�N���X
     */
    public class WEB3AioCashinAcceptTransactionCallback implements TransactionCallback
    {
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioCashinAcceptTransactionCallback.class);

        /**
         * (������tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@return WEB3AioCashinAcceptTransactionCallback
         * @@roseuid 40F7C57E004D
         */
        public WEB3AioCashinAcceptTransactionCallback()
        {

        }

        /**
         * ������t���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i������t�jprocess�v�Q��<BR>
         * @@return Object<BR>
         * @@roseuid 40F7C59C0109
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "WEB3AioCashinAcceptTransactionCallback::process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1)���o���`�[��t�L���[�e�[�u���Ǎ���
            //(*1) �ȉ��̏����� ���o���`�[��t�L���[�e�[�u������s���b�N�I�v�V�����ɂă��R�[�h���擾����
            //[��������]
            //�f�[�^�R�[�h = "GI80C"�i���o���`�[��t�j
            //�����敪 = "0"�i�������j
            String l_strWhere = " request_code = ? and status = ? ";
            // ���u�� U01832�̎b��Ή� start
//            String l_strCondition = " for update";
            String l_strCondition = null;
            // ���u�� U01832�̎b��Ή� end
            Object[] l_objWhereValues = {WEB3HostRequestCodeDef.AIO_SLIP_ACCEPT,
                     WEB3StatusDef.NOT_DEAL};


            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisResult =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransOrderAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            int l_intResultLength = 0;

            if(!l_lisResult.isEmpty())
            {
                l_intResultLength = l_lisResult.size();
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
            for(int i = 0; i < l_intResultLength; i++)
            {
                HostCashTransOrderAcceptParams l_hostCashTransOrderAcceptParams =
                    (HostCashTransOrderAcceptParams) l_lisResult.get(i);

                String l_strInstitutionCode = l_hostCashTransOrderAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostCashTransOrderAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostCashTransOrderAcceptParams.getAccountCode();
                String l_strOrderRequestNumber = l_hostCashTransOrderAcceptParams.getOrderRequestNumber();

                boolean l_blnExc = false;
                try
                {
                    // 1.2.1)�����P�ʃI�u�W�F�N�g���擾����B
                    //[����]
                    //�،���ЃR�[�h�F ���o���`�[��t�L���[�e�[�u��.�،���ЃR�[�h
                    //���X�R�[�h�F ���o���`�[��t�L���[�e�[�u��.���X�R�[�h
                    //�ڋq�R�[�h�F ���o���`�[��t�L���[�e�[�u��.�ڋq�R�[�h
                    //���ʃR�[�h�F ���o���`�[��t�L���[�e�[�u��.���ʃR�[�h
                    //�⏕�����^�C�v�F 1�i�a��������j
                    AioOrderUnit l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strOrderRequestNumber,
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    // ���u�� U01832�̎b��Ή� start
                    WEB3AioCashinAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AioCashinAcceptNormalTransactionCallback(
                            l_aioOrderUnit,
                            l_hostCashTransOrderAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    // 1.2.2)���o����tDB�X�V�������s���B
//                    //[����]
//                    //�����P�ʁF �擾���������P�ʃI�u�W�F�N�g
//                    //�G���[�R�[�h�F �o��������t�L���[�e�[�u��.�G���[���b�Z�[�W
//                    //��t�ʒm�敪�F �o��������t�L���[�e�[�u��.��t�ʒm�敪
//                    l_aioCashTransferAcceptUnitService.execute(
//                        l_aioOrderUnit,
//                        l_hostCashTransOrderAcceptParams.getErrorMessage(),
//                        l_hostCashTransOrderAcceptParams.getAcceptDiv());
//
//                    // ���o���`�[��t�L���[�e�[�u��.��t�ʒm�敪 = "1"�i��t�����j�̏ꍇ
//                    if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
//                        l_hostCashTransOrderAcceptParams.getAcceptDiv()))
//                    {
//                        //1.2.3)��t�����̏ꍇ
//                        // 1.2.3.1)���o�����������ɔ��������f�[�^�̍X�V�ƃg�����U�N�V�����f�[�^�̐������s���B
//                        //[����]
//                        //�����P�ʁF get�����P��()�Ŏ擾���������P�ʃI�u�W�F�N�g
//                        l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
//
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

                //1.2.4)�L���[�e�[�u���̏����敪�̍X�V
                //���o���`�[��t�L���[�e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
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
//                String l_strRequestCode = l_hostCashTransOrderAcceptParams.getRequestCode();
//
//                String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
//                    "and branch_code = ? and account_code = ? and order_request_number = ? ";
//
//                Object[] l_objUpdaeWereValues = {l_strRequestCode, l_strInstitutionCode,
//                        l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};
//
//                l_queryProcessor.doUpdateAllQuery(
//                    HostCashTransOrderAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_objUpdaeWereValues,
//                    l_hashMap);

                if(l_blnExc)
                {
                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strRequestCode = l_hostCashTransOrderAcceptParams.getRequestCode();

                    String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
                        "and branch_code = ? and account_code = ? and order_request_number = ? ";

                    Object[] l_objUpdaeWereValues = {l_strRequestCode, l_strInstitutionCode,
                            l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};

                    l_queryProcessor.doUpdateAllQuery(
                        HostCashTransOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_objUpdaeWereValues,
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
