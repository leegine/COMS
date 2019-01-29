head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M������t�T�[�r�XImpl(WEB3MutualOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/23 ����� (���u) ���r���[
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
import webbroker3.mf.data.HostXbmfOrderAcceptParams;
import webbroker3.mf.data.HostXbmfOrderAcceptRow;
import webbroker3.mf.message.WEB3MutualOrderAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M������t�T�[�r�XImpl)
 * �����M��������t�T�[�r�X�����N���X<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualOrderAcceptServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualOrderAcceptService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderAcceptServiceImpl.class);

    /**
     * �����M��������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����M���j������t�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40566483009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            // �P�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �Q�j ���M������tTransactionCallback�̃C���X�^���X�𐶐�����
            WEB3MutualOrderAcceptTransactionCallback l_transactionCallback =
                new WEB3MutualOrderAcceptTransactionCallback();

            //�R�jDB�g�����U�N�V�������������{����
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
            WEB3MutualOrderAcceptResponse l_response =
                (WEB3MutualOrderAcceptResponse) l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    public class WEB3MutualOrderAcceptTransactionCallback
        implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3MutualOrderAcceptTransactionCallback.class);

        /**
         * (���M������tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@roseuid 40A4332C0137
         */
        public WEB3MutualOrderAcceptTransactionCallback()
        {

        }

        /**
         * ���M������t�T�[�r�X���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i���M������t�jprocess�v�Q�ƁB<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A4342E0166
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            //�P�j�@@�����Ώۃ��R�[�h�擾
            // ���M������t�L���[�e�[�u�����A�s���b�N
            // select for update�j�ɂēǂݍ���
            String l_strWhere = " status = ?";
            String l_strCondition = null;
            Object[] l_objWhereValues = { WEB3StatusDef.NOT_DEAL };
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            /*����*/
            List l_lisMFOrderAcceptUnitResult =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfOrderAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            // �Q�j�@@���M������tUnitService���擾����
            WEB3MutualOrderAcceptUnitService l_OrderAcceptUnitService =
                (WEB3MutualOrderAcceptUnitService) Services.getService(
                    WEB3MutualOrderAcceptUnitService.class);

            //�R�j�@@���M��t�m��C���^�Z�v�^�𐶐�����B
            WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();

            //�������ʂ̓��M������t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����

            int l_intResultLength = 0;
            if (l_lisMFOrderAcceptUnitResult != null &&
                l_lisMFOrderAcceptUnitResult.size() != 0)
            {
                l_intResultLength = l_lisMFOrderAcceptUnitResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            HostXbmfOrderAcceptParams l_orderAcceptParams = null;

            for (int i = 0; i < l_intResultLength; i++)
            {
                // ���M������t�L���[Params�̎擾
                l_orderAcceptParams =
                    (HostXbmfOrderAcceptParams) l_lisMFOrderAcceptUnitResult.get(i);
                HashMap l_map = new HashMap();
                String l_strUpdateWhere =
                    " institution_code = ? " +
                    " and branch_code = ? " +
                    " and order_request_number = ?";
                String[] l_updateParams =
                    {l_orderAcceptParams.getInstitutionCode(),
                     l_orderAcceptParams.getBranchCode(),
                     l_orderAcceptParams.getOrderRequestNumber()};

                try
                {
                    //�S�j�@@�����������M��t�m��C���^�Z�v�^�I�u�W�F�N�g.set�����G���[���R�R�[�h()��
                    //  �R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B
                    //  [set�����G���[���R�R�[�h�ɓn���p�����^]
                    //  �����G���[���R�R�[�h�F ���M������t�L���[Params.get�G���[�R�[�h()�̖߂�l
                    l_confirmInterceptor.setOrderErrorReasonCode(
                        l_orderAcceptParams.getErrorMessage());

                    //�T�j �L���[���R�[�h�ɊY�����铊�M�����P�ʃI�u�W�F�N�g���擾����B
                    //  �g�����M�����}�l�[�W��.get�����P��()���R�[�����āA���M�����P�ʃI�u�W�F�N�g���擾����B
                    // �mget�����P�ʂɓn���p�����^�n
                    //  �،���ЃR�[�h�F ���M������t�L���[Params.get�،���ЃR�[�h()�̖߂�l
                    //  ���X�R�[�h�F ���M������t�L���[Params.get���X�R�[�h()�̖߂�l
                    //  �ڋq�R�[�h�F ���M������t�L���[Params.get�ڋq�R�[�h()�̖߂�l
                    //  ���ʃR�[�h�F ���M������t�L���[Params.get���ʃR�[�h()�̖߂�l
                    MutualFundOrderUnit l_mfOrderUnit =
                        l_orderManager.getOrderUnit(
                            l_orderAcceptParams.getInstitutionCode(),
                            l_orderAcceptParams.getBranchCode(),
                            l_orderAcceptParams.getOrderRequestNumber());

                    //�U�j�@@������t�������s���B
                    //  �|���M������t�L���[Params.get������t����()�̖߂�l���h1�F������t�����h��
                    //  �ꍇ ���M������tUnitService.notify������t����()���R�[������B
                    //  [notify������t�����ɓn���p�����^]
                    //  ���M�����P�ʁF �擾�������M�����P�ʃI�u�W�F�N�g
                    //  ���M��t�m��C���^�Z�v�^�F �����������M��t�m��C���^�Z�v�^�I�u�W�F�N�g
                    String l_strAcceptStatus =
                        l_orderAcceptParams.getAcceptStatus();

                    // TransactionCallback����
                    WEB3MutualOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3MutualOrderAcceptNormalTransactionCallback(
                            l_mfOrderUnit,
                            l_confirmInterceptor,
                            l_orderAcceptParams);

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
							log.debug("�������b�N���s�F" + l_orderAcceptParams.toString());
							continue;
						}
					}
					// ����ȊO�̃G���[�����������ꍇ�A
					// �L���[�e�[�u���̏����敪�h9�F�G���[�h���Z�b�g���X�V����
                    log.debug("__an Exception__ ");
					log.error("�ꌏ�����ɂė�O�����������ꍇ",l_ex);
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    // do update
                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);
                }
            }
			m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}@
