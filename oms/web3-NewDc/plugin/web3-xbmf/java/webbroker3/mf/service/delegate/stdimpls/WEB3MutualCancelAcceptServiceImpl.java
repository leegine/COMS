head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������t�T�[�r�X�����N���X(WEB3MutualCancelAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/23 ��O�� (���u) ���r���[
Revesion History : 2009/05/13 �g�C�� (���u) �d�l�ύX No643
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
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
import webbroker3.mf.data.HostXbmfCancelAcceptParams;
import webbroker3.mf.data.HostXbmfCancelAcceptRow;
import webbroker3.mf.message.WEB3MutualCancelAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�������t�T�[�r�X�����N���X
 *
 * @@author ������(���u)
 * @@version 1.0*
 */
public class WEB3MutualCancelAcceptServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualCancelAcceptService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelAcceptServiceImpl.class);


    /**
     * �����M�������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����M���j�����t�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40565F3A039B
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // �P�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �Q�j ���M�����tTransactionCallback�̃C���X�^���X�𐶐�����
            WEB3MutualCancelAcceptTransactionCallback l_transactionCallback =
                new WEB3MutualCancelAcceptTransactionCallback();

            //�R�jDB�g�����U�N�V�������������{����
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when l_queryProcessor.doTransaction()");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3MutualCancelAcceptResponse l_response = (WEB3MutualCancelAcceptResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (���M�����tTransactionCallback)<BR>
     * ���M�����t�T�[�r�X���������{����B<BR>
     */
    public class WEB3MutualCancelAcceptTransactionCallback
        implements TransactionCallback
    {

        /**
         * ���O���[�e�B���e�B
         */
        private  WEB3LogUtility l_log =
            WEB3LogUtility.getInstance(WEB3MutualCancelAcceptTransactionCallback.class);

        /**
         * �f�t�H���g�R���X�g���N�^
         * @@roseuid 40A433050166
         */
        public WEB3MutualCancelAcceptTransactionCallback()
        {

        }

        /**
         * ���M�����t�T�[�r�X���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i���M�����t�jprocess�v�Q�ƁB <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A4341B01F3
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            l_log.entering(STR_METHOD_NAME);

            // �P�j�@@�����Ώۃ��R�[�h�擾
            // �@@���M�����t�L���[�e�[�u�����A�s���b�N�iselect for update�j�ɂēǂݍ���
            String l_strWhere = "status = ?";
            String l_strCondition = null;

            Object[] l_objWhere = { WEB3StatusDef.NOT_DEAL };
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfCancelAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            // �Q�j�@@���M�����tUnitService���擾����
            WEB3MutualCancelAcceptUnitService l_cancelAcceptUnitService =
                (WEB3MutualCancelAcceptUnitService) Services.getService(
                    WEB3MutualCancelAcceptUnitService.class);

            //�������ʂ̓��M�����t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����
            int l_intResultLength = 0;
            if (l_lisSearchResult != null && l_lisSearchResult.size() != 0)
            {
                l_intResultLength = l_lisSearchResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            HostXbmfCancelAcceptParams l_cancelAcceptParams = null;

            for (int i = 0; i < l_intResultLength; i++)
            {
                // �R�j�@@���M��t�m��C���^�Z�v�^�𐶐�����
                WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
                    new WEB3MutualFundAcceptConfirmInterceptor();

                l_orderManager.setThreadLocalPersistenceEventInterceptor(l_confirmInterceptor);

                // ���M�����t�L���[Params�̎擾
                l_cancelAcceptParams =
                    (HostXbmfCancelAcceptParams) l_lisSearchResult.get(i);

                HashMap l_map = new HashMap();
                try
                {
                    // �T�j �����������M��t�m��C���^�Z�v�^�I�u�W�F�N�g.set�����G���[���R�R�[�h()
                    l_confirmInterceptor
                            .setOrderErrorReasonCode(l_cancelAcceptParams
                                    .getErrorMessage());

                    MutualFundOrderUnit l_mfOrderUnit = null;
                    try
                    {
                        // �U�j �L���[���R�[�h�ɊY�����铊�M�����P�ʃI�u�W�F�N�g���擾����B<BR>
                        // �@@�g�����M�����}�l�[�W��.get�����P��()���R�[�����āA���M�����P�ʃI�u�W�F�N�g���擾
                        l_mfOrderUnit = l_orderManager.getOrderUnit(
                            l_cancelAcceptParams.getInstitutionCode(),
                            l_cancelAcceptParams.getBranchCode(),
                            l_cancelAcceptParams.getOrderRequestNumber());
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo()))
                        {
                            //�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�p
                            String l_strUpdateWhere = " institution_code = ? "
                                + " and branch_code = ? "
                                + " and order_request_number = ?";
                            String[] l_strArrayUpdateParams = {
                                l_cancelAcceptParams.getInstitutionCode(),
                                l_cancelAcceptParams.getBranchCode(),
                                l_cancelAcceptParams.getOrderRequestNumber()
                            };

                            l_map.put("status", WEB3StatusDef.DEALT);
                            // �����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
                            l_queryProcessor.doUpdateAllQuery(
                                HostXbmfCancelAcceptRow.TYPE,
                                l_strUpdateWhere,
                                l_strArrayUpdateParams,
                                l_map);

                             continue;
                        }
                    }
                    // �V�j �����t�������s��
                    String l_strAcceptStatus = l_cancelAcceptParams
                            .getAcceptStatus();

                    // TransactionCallback����
                    WEB3MutualCancelAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3MutualCancelAcceptNormalTransactionCallback(
                            l_mfOrderUnit,
                            l_confirmInterceptor,
                            l_cancelAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
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
							log.debug("�������b�N���s�F" + l_cancelAcceptParams.toString());
							continue;
						}
					}
					// ����ȊO�̃G���[�����������ꍇ
					// �����Ώۂ̎����t�L���[���R�[�h.�����敪�h9�F�G���[�h���Z�b�g���X�V����
                    log.debug("__an Exception__ ");
					log.error("�ꌏ�����ɂė�O�����������ꍇ",l_ex);
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strUpdateWhere = " institution_code = ? "
                                       + " and branch_code = ? "
                                       + " and order_request_number = ?";
                    String[] l_strArrayUpdateParams = {
                        l_cancelAcceptParams.getInstitutionCode(),
                        l_cancelAcceptParams.getBranchCode(),
                        l_cancelAcceptParams.getOrderRequestNumber()
                    	};
                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfCancelAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            l_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
