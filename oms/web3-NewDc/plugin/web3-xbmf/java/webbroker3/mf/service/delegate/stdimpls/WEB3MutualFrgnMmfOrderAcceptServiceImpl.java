head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��MMF������t�T�[�r�XImpl(WEB3MutualFrgnMmfOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 �đo�g (���u) �V�K�쐬 (���f��534)
*/

package webbroker3.mf.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptParams;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptRow;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualFrgnMmfOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O��MMF������t�T�[�r�XImpl)<BR>
 * �O��MMF������t�T�[�r�X�����N���X<BR>
 * 
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualFrgnMmfOrderAcceptService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptServiceImpl.class);

    /**
     * @@roseuid 45C440D700B2
     */
    public WEB3MutualFrgnMmfOrderAcceptServiceImpl() 
    {
     
    }

    /**
     * �O��MMF������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�O��MMF�j������t�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B9C837011B
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_processor = Processors.getDefaultProcessor();

            //�O��MMF������tTransactionCallback( )
            WB3MutualFrgnMmfOrderAcceptTransactionCallback l_callback =
                new WB3MutualFrgnMmfOrderAcceptTransactionCallback();

            //doTransaction(int, TransactionCallback)
            //[doTransaction�ɓn���p�����^]
            //�@@�@@�g�����U�N�V���������F TX_CREATE_NEW
            //�@@�@@�g�����U�N�V�����R�[���o�b�N�F �O��MMF������tTransactionCallback�C���X�^���X
            l_processor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW, l_callback);
        }
        catch(DataException l_exc)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",l_exc);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }

        //create���X�|���X( )
        WEB3MutualFrgnMmfOrderAcceptResponse l_response =
            (WEB3MutualFrgnMmfOrderAcceptResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�O��MMF������tTransactionCallback)<BR>
     * �O��MMF������t�T�[�r�X���������{����B<BR>
     */
    public class WB3MutualFrgnMmfOrderAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * (�O��MMF������tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@roseuid 45B9C87F033E
         */
        public WB3MutualFrgnMmfOrderAcceptTransactionCallback()
        {
         
        }

        /**
         * �O��MMF������t�T�[�r�X���������{����B <BR>
         * <BR>
         * �V�[�P���X�} �u�i�O��MMF������t�jprocess�v�Q�ƁB <BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         * @@roseuid 45B9C8AF034E
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            //�����Ώۃ��R�[�h�擾 �O��MMF������t�L���[�e�[�u��
            String l_strWhere = " status = ? ";
            Object[] l_objWhereValues = {WEB3StatusDef.NOT_DEAL};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            /*����*/
            List l_lisMFOrderAccepResult =
                l_queryProcessor.doFindAllQuery(
                    HostFrgnMmfOrderAcceptRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);

            //���M������tUnitService���擾����
            WEB3MutualOrderAcceptUnitService l_orderAcceptUnitService =
                (WEB3MutualOrderAcceptUnitService)Services.getService(
                    WEB3MutualOrderAcceptUnitService.class);

            //���M��t�m��C���^�Z�v�^( )
            WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();

            //��LOOP�������������ʂ̊O��MMF������t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����
            int l_intResultLength = 0;
            if (l_lisMFOrderAccepResult != null && l_lisMFOrderAccepResult.size() != 0)
            {
                l_intResultLength = l_lisMFOrderAccepResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            HostFrgnMmfOrderAcceptParams l_orderAcceptParams = null;
            for (int i = 0; i < l_intResultLength; i++)
            {
                // �O��MMF������t�L���[Params�̎擾
                l_orderAcceptParams =
                    (HostFrgnMmfOrderAcceptParams)l_lisMFOrderAccepResult.get(i);

                HashMap l_map = new HashMap();
                String l_strUpdateWhere =
                    " institution_code = ? " +
                    " and branch_code = ? " +
                    " and order_request_number = ? ";
                String[] l_strUpdateParams =
                    {l_orderAcceptParams.getInstitutionCode(),
                     l_orderAcceptParams.getBranchCode(),
                     l_orderAcceptParams.getOrderRequestNumber()};

                try
                {
                    //set�����G���[���R�R�[�h(String)
                    //[set�����G���[���R�R�[�h�ɓn���p�����^]
                    //�@@�����G���[���R�R�[�h�F �O��MMF������t�L���[Params.get�G���[�R�[�h()�̖߂�l
                    l_confirmInterceptor.setOrderErrorReasonCode(
                        l_orderAcceptParams.getErrorMessage());

                    //get�����P��(String, String, String)
                    //�mget�����P�ʂɓn���p�����^�n
                    //�@@�@@�،���ЃR�[�h�F �O��MMF������t�L���[Params.get�،���ЃR�[�h()�̖߂�l
                    //�@@�@@���X�R�[�h�F �O��MMF������t�L���[Params.get���X�R�[�h()�̖߂�l
                    //�@@  ���ʃR�[�h�F �O��MMF������t�L���[Params.get���ʃR�[�h()�̖߂�l
                    MutualFundOrderUnit l_mutualFundOrderUnit =
                        l_orderManager.getOrderUnit(
                            l_orderAcceptParams.getInstitutionCode(),
                            l_orderAcceptParams.getBranchCode(),
                            l_orderAcceptParams.getOrderRequestNumber());

                    //�O��MMF��t���폈���ꌏTransactionCallback(
                    // MutualFundOrderUnit, ���M��t�m��C���^�Z�v�^, �O��MMF������t�L���[Params)
                    WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback l_normalTransactionCallback =
                        new WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
                            l_mutualFundOrderUnit,
                            l_confirmInterceptor,
                            l_orderAcceptParams);

                    //doTransaction(int, TransactionCallback)
                    //[doTransaction�ɓn���p�����^]
                    //�@@�@@�g�����U�N�V���������F TX_CREATE_NEW
                    //�@@�@@�g�����U�N�V�����R�[���o�b�N�F
                    //       �O��MMF������t���폈���ꌏTransactionCallback�C���X�^���X
                    l_queryProcessor.doTransaction(
                        TransactionalInterceptor.TX_CREATE_NEW,
                        l_normalTransactionCallback);
                }
                catch(Exception l_exc)
                {
                    //�������b�N�Ɏ��s���ăG���[�����������ꍇ�A
                    //�����ΏۃL���[���X�V���Ȃ��B
                    if(l_exc instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException)l_exc;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            continue;
                        }
                    }

                    // ����ȊO�̃G���[�����������ꍇ�A
                    // �L���[�e�[�u���̏����敪�h9�F�G���[�h���Z�b�g���X�V����
                    log.error("�ꌏ�����ɂė�O�����������ꍇ", l_exc);
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    // do update
                    l_queryProcessor.doUpdateAllQuery(
                        HostFrgnMmfOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateParams,
                        l_map);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
