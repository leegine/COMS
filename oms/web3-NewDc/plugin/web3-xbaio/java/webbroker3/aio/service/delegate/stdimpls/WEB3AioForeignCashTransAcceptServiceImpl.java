head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݓ��o����t�T�[�r�XImpl(WEB3AioForeignCashTransAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 ���G�� (���u) �V�K�쐬
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
import webbroker3.aio.data.HostFCashTransOrderAcceptParams;
import webbroker3.aio.data.HostFCashTransOrderAcceptRow;
import webbroker3.aio.message.WEB3AioForeignCashTransAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioForeignCashTransAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�ݓ��o����t�T�[�r�XImpl)<BR>
 * �O�ݓ��o����t�T�[�r�X�����N���X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptServiceImpl 
    implements WEB3AioForeignCashTransAcceptService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransAcceptServiceImpl.class);

    /**
     * �O�ݓ��o����t�������s���B<BR> 
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u�i���o����t�j�O�ݓ��o����t�v�Q�� <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)�O�ݓ��o����t�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B 
        WEB3AioForeignCashTransAcceptTransactionCallback l_aioForeignCashTransAcceptTransactionCallback =
            new WEB3AioForeignCashTransAcceptTransactionCallback();

        try
        {
            // 1.2)�N�G���v���Z�b�T���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.3)doTransaction(�g�����U�N�V�������� : int, �g�����U�N�V�����R�[���o�b�N : TransactionCallback)
            //DB�g�����U�N�V�������������{����B  
            //
            //[doTransaction()�Ɏw�肷�����]  
            //�g�����U�N�V���������F�@@TX_CREATE_NEW  
            //�g�����U�N�V�����R�[���o�b�N�F�@@�O�ݓ��o����tTransactionCallback�C���X�^���X 
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, 
                l_aioForeignCashTransAcceptTransactionCallback);

            // 1.4)createResponse( )(�O�ݓ�����t���N�G�X�g::createResponse)
            WEB3AioForeignCashTransAcceptResponse l_response =
                (WEB3AioForeignCashTransAcceptResponse)l_request.createResponse();

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
     * (�O�ݓ��o����tTransactionCallback )<BR>
     * �O�ݓ��o����t�g�����U�N�V�����R�[���o�b�N�N���X
     */
    public class WEB3AioForeignCashTransAcceptTransactionCallback implements TransactionCallback
    {
        /**
         * ���O�o�̓��[�e�B���e�B�B
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioForeignCashTransAcceptTransactionCallback.class);

        /**
         * (�O�ݓ�����tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         */
        public WEB3AioForeignCashTransAcceptTransactionCallback()
        {

        }

        /**
         * ������t���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�O�ݓ��o����t�jprocess�v�Q�� <BR>
         * @@return Object<BR>
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "WEB3AioForeignCashTransAcceptTransactionCallback::process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1)�O�ݓ��o���`�[��t�L���[�e�[�u���Ǎ���
            //(*1) �ȉ��̏����� �O�ݓ��o���`�[��t�L���[�e�[�u������s���b�N�I�v�V�����ɂă��R�[�h���擾����
            //[��������]
            //�f�[�^�R�[�h = "GI80D"
            //�����敪 = "0"�i�������j
            String l_strWhere = " request_code = ? and status = ? ";

            String l_strCondition = null;

            Object[] l_objWhereValues = {
                WEB3HostRequestCodeDef.F_CASH_TRANS_ORDER_ACCEPT,
                WEB3StatusDef.NOT_DEAL};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisResults =
                l_queryProcessor.doFindAllQuery(
                    HostFCashTransOrderAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            int l_intResultLength = 0;

            if(!l_lisResults.isEmpty())
            {
                l_intResultLength = l_lisResults.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            // 1.2)�擾�������R�[�h����Loop����
            for(int i = 0; i < l_intResultLength; i++)
            {
                HostFCashTransOrderAcceptRow l_row = 
                    (HostFCashTransOrderAcceptRow)l_lisResults.get(i);
                HostFCashTransOrderAcceptParams l_hostFCashTransOrderAcceptParams =
                    new HostFCashTransOrderAcceptParams(l_row);

                String l_strInstitutionCode = 
                    l_hostFCashTransOrderAcceptParams.getInstitutionCode();
                String l_strBranchCode = 
                    l_hostFCashTransOrderAcceptParams.getBranchCode();
                String l_strAccountCode = 
                    l_hostFCashTransOrderAcceptParams.getAccountCode();
                String l_strOrderRequestNumber = 
                    l_hostFCashTransOrderAcceptParams.getOrderRequestNumber();

                boolean l_blnExc = false;
                try
                {
                    // 1.2.1)�����P�ʃI�u�W�F�N�g���擾����B  
                    //[����]  
                    //�،���ЃR�[�h�F �O�ݓ��o���`�[��t�L���[�e�[�u��.�،���ЃR�[�h  
                    //���X�R�[�h�F �O�ݓ��o���`�[��t�L���[�e�[�u��.���X�R�[�h  
                    //�ڋq�R�[�h�F �O�ݓ��o���`�[��t�L���[�e�[�u��.�ڋq�R�[�h  
                    //���ʃR�[�h�F �O�ݓ��o���`�[��t�L���[�e�[�u��.���ʃR�[�h  
                    //�⏕�����^�C�v�F 1�i�a��������j 

                    AioOrderUnit l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strOrderRequestNumber,
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    WEB3AioForeignCashTransAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AioForeignCashTransAcceptNormalTransactionCallback(
                            l_aioOrderUnit,
                            l_hostFCashTransOrderAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

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
                catch(Exception l_ex)
                {
                    m_log.debug("__an Exception__ ", l_ex);
                    l_blnExc = true;
                }

                //1.2.4)�O�ݓ��o���`�[��t�L���[�e�[�u���̏����敪�̍X�V
                //�O�ݓ��o���`�[��t�L���[�e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
                //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
                HashMap l_hashMap = new HashMap();

                if(l_blnExc)
                {
                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strRequestCode = 
                        l_hostFCashTransOrderAcceptParams.getRequestCode();

                    String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
                        "and branch_code = ? and account_code = ? and order_request_number = ? ";

                    Object[] l_objUpdaeWereValues = {
                        l_strRequestCode, 
                        l_strInstitutionCode,
                        l_strBranchCode, 
                        l_strAccountCode, 
                        l_strOrderRequestNumber};

                    l_queryProcessor.doUpdateAllQuery(
                        HostFCashTransOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_objUpdaeWereValues,
                        l_hashMap);
                }
            }

            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
