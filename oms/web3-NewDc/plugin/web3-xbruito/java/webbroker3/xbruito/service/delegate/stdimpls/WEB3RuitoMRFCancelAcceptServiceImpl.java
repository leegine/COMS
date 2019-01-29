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
filename	WEB3RuitoMRFCancelAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���MRF�����t�T�[�r�X�����N���X(WEB3RuitoMRFCancelAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.data.HostMrfCancelAcceptParams;
import webbroker3.xbruito.data.HostMrfCancelAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptUnitService;

/**
 * �ݐϓ���MRF�����t�T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoMRFCancelAcceptServiceImpl implements
                WEB3RuitoMRFCancelAcceptService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptServiceImpl.class);

    /**
     * �ݐϓ���MRF�����t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ��jMRF�����t�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����B<BR>
     *
     * �Q�j �ݓ�MRF�����tTransactionCallback��<BR>
     *      �C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@DB�g�����U�N�V�������������{����B<BR>
     * �@@[doTransaction�ɓn���p�����^]<BR>
     * �@@�@@�g�����U�N�V���������F TX_CREATE_NEW<BR>
     * �@@�@@�g�����U�N�V�����R�[���o�b�N�F <BR>
     *     �ݓ�MRF�����tTransactionCallback�C���X�^���X<BR>
     * <BR>
     * �S�j�@@�ݓ�MRF�����t���X�|���X�𐶐����A���^�[������B<BR>
     * �@@  �|�ݓ�MRF�����t���N�G�X�g.create���X�|���X()���R�[�����A<BR>
     *     �ݓ�MRF�����t���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@  �|���������ݓ�MRF�����t���X�|���X��Ԃ��B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 405812110198
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        // 1)�N�G���v���Z�b�T�̃C���X�^���X���擾����
        QueryProcessor l_queryProcessor = null; //�N�G���v���Z�b�T
        WEB3RuitoMRFCancelAcceptTransactionCallback
            l_ruitoMRFOrderAcceptTransactionCallback = null;
        try
        {
            //DataFindException,DataNetworkException
            l_queryProcessor = Processors.getDefaultProcessor();

            //�Q�j �ݓ�MRF������tTransactionCallback�̃C���X�^���X�𐶐�����B
            l_ruitoMRFOrderAcceptTransactionCallback =
                    new WEB3RuitoMRFCancelAcceptTransactionCallback();

            log.debug("doTransaction");
            // 3)DB�g�����U�N�V�������������{����
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ruitoMRFOrderAcceptTransactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME + " __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__");
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@�ݓ�MRF������t���X�|���X�𐶐����A���^�[������B
        WEB3RuitoMRFCancelAcceptRequest l_ruitoMRFCancelAcceptRequest =
            (WEB3RuitoMRFCancelAcceptRequest) l_request;

        WEB3RuitoMRFCancelAcceptResponse l_ruitoMRFCancelAcceptResponse =
            (WEB3RuitoMRFCancelAcceptResponse)
        l_ruitoMRFCancelAcceptRequest.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_ruitoMRFCancelAcceptResponse;
    }

    /**
     * �ݐϓ���MRF�����t�T�[�r�X���������{����B<BR>
     */
    public class WEB3RuitoMRFCancelAcceptTransactionCallback
        implements TransactionCallback
    {

        /**
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@roseuid 40890EFC001E
         */
        public WEB3RuitoMRFCancelAcceptTransactionCallback()
        {

        }

        /**
         * �ݐϓ���MRF�����t�T�[�r�X���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�ݓ�MRF�����t�jprocess�v�Q�ƁB<BR>
         * <BR>
         * �P�j�@@�����Ώۃ��R�[�h�擾<BR>
         * �@@MRF�����t�L���[�e�[�u�����A<BR>
         *    �s���b�N�iselect for update�j�ɂēǂݍ��ށB<BR>
         * <BR>
         * �@@�m���������n<BR>
         * �@@�����敪 = �h0�F�������h<BR>
         * �@@�mcondition�n<BR>
         * �@@�hfor update�h<BR>
         *
         * �Q�j�@@�ݓ�MRF�����tUnitService���擾����B<BR>
         * <BR>
         * �R�j�@@�ݓ���t�m��C���^�Z�v�^�𐶐�����B<BR>
         *     �ȍ~�̏����́A�������ʂ̗ݓ�MRF�����t�L���[�e�[�u��<BR>
         *     �̊e�s�ɑ΂��Ď��{����B<BR>
         * <BR>
         * �S�j ���������ݓ���t�m��<BR>
         *     �C���^�Z�v�^�I�u�W�F�N�g.set�����G���[���R�R�[�h()<BR>
         *     ���R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B<BR>
         * �@@�@@[set�����G���[���R�R�[�h�ɓn���p�����^]<BR>
         * �@@�@@�����G���[���R�R�[�h�F<BR>
         *      MRF������t�L���[Params.get�G���[�R�[�h()�̖߂�l<BR>
         * <BR>
         * �T�j �L���[���R�[�h�ɊY������ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
         * �@@  �g���ݓ������}�l�[�W��.get�����P��()���R�[�����āA<BR>
         *     �ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
         *   �@@�mget�����P�ʂɓn���p�����^�n<BR>
         * �@@�@@�،���ЃR�[�h�F<BR>
         *      MRF�����t�L���[Params.get�،���ЃR�[�h()�̖߂�l<BR>
         * �@@�@@���X�R�[�h�F MRF�����t�L���[Params.get���X�R�[�h()�̖߂�l<BR>
         * �@@�@@���ʃR�[�h�F MRF�����t�L���[Params.get���ʃR�[�h()�̖߂�l<BR>
         * <BR>
         * �U�j�@@������t�������s���B<BR>
         * �@@   �|MRF�����t�L���[Params.get����ʒm�敪()�̖߂�l���h<BR>
         *       1�F��������h�̏ꍇ�A<BR>
         * �@@ �@@�ݓ�MRF�����tUnitService.notify�����t����()<BR>
         *       ���R�[������B<BR>
         * �@@ �@@[notify�����t�����ɓn���p�����^]<BR>
         * �@@ �@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
         * �@@ �@@�ݓ���t�m��C���^�Z�v�^�F <BR>
         *      ���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g<BR>
         *
         *   �@@ �|MRF�����t�L���[Params.get����ʒm�敪()�̖߂�l���h<BR>
         *      1�F��������h�łȂ��ꍇ�A<BR>
         * �@@ �@@�ݓ�MRF�����tUnitService.notify�����t���s()<BR>
         *       ���R�[������B<BR>
         * �@@ �@@[notify�����t���s�ɓn���p�����^]<BR>
         * �@@�@@�@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
         * �@@�@@�@@�@@�ݓ���t�m��C���^�Z�v�^�F <BR>
         *      ���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g<BR>
         * <BR>
         * �V�j�L���[�e�[�u���̏����敪���X�V <BR>
         *   �|�����t����������I�������ꍇ�A�����Ώۂ�MRF�����t�L���[���R�[�h.�����敪�� <BR>�@@
         * �@@�h1�F�����ρh���Z�b�g���X�V����B <BR>
         *   �|�S�j����U�j�̏����Ŏ����t��������O���X���[�����ꍇ�A<BR>
         *         �����Ώۂ�MRF�����t�L���[ <BR>
         *  ���A���A�T�[�r�X�C���^�Z�v�^onCall�ŗ�O�������́A�����敪���X�V���Ȃ� <BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40890F1B033B
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //�P�j�@@�����Ώۃ��R�[�h�擾
            String l_strWhere = "status = ?";
            String l_strCondition = null;

            Object[] l_objWhere = {WEB3StatusDef.NOT_DEAL};
            log.debug("where status = " + WEB3StatusDef.NOT_DEAL);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                    l_queryProcessor.doFindAllQuery(
                    HostMrfCancelAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);
            log.debug("doFindAllQuery is completed.");

            //�Q�j�@@�ݓ�MRF�����tUnitService���擾����B
            WEB3RuitoMRFCancelAcceptUnitService l_unitService =
                (WEB3RuitoMRFCancelAcceptUnitService) Services.getService(
                    WEB3RuitoMRFCancelAcceptUnitService.class);


            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            WEB3RuitoOrderManager l_web3RuitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();

            //�R�j�@@�ݓ���t�m��C���^�Z�v�^�𐶐�����B
            WEB3RuitoAcceptedDecisionInterceptor
                l_ruitoAcceptedDecisionInterceptor =
                new WEB3RuitoAcceptedDecisionInterceptor();

            l_web3RuitoOrderMgr.setThreadLocalPersistenceEventInterceptor
                (l_ruitoAcceptedDecisionInterceptor);

            //�ȍ~�̏����́A�������ʂ̗ݓ�MRF�����t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B
            String l_strAcceptStatus;
            HostMrfCancelAcceptParams l_mrfCancelAcceptParams = null;
            int i = 0;
            int l_intSize = 0;
            l_intSize = l_lisSearchResult.size();
            log.debug("Output the number of search records = "  + l_intSize);

            for ( i = 0; i < l_intSize; i ++)
            {
                log.debug("Enter for path.");
                l_mrfCancelAcceptParams = (HostMrfCancelAcceptParams)l_lisSearchResult.get(i);
                boolean l_blnIsFail = false;
                try
                {
                    log.debug("l_mrfCancelAcceptParams.getErrorCode()=" +l_mrfCancelAcceptParams.getErrorCode());
                    // �S�j�@@���������ݓ���t�m��
                    l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode
                        (l_mrfCancelAcceptParams.getErrorCode());

                    //�T�j �L���[���R�[�h�ɊY������ݓ������P�ʃI�u�W�F�N�g���擾����B
                    log.debug("InstitutionCode" + l_mrfCancelAcceptParams.getInstitutionCode());
                    log.debug("BranchCode:" + l_mrfCancelAcceptParams.getBranchCode());
                    log.debug("Requst Order Number:"+ l_mrfCancelAcceptParams.getOrderRequestNumber());

                    RuitoOrderUnit l_ruitoOrderUnit = null;
                    l_ruitoOrderUnit =
                        l_web3RuitoOrderMgr.getRuitoOrderUnit(
                        l_mrfCancelAcceptParams.getInstitutionCode(),
                        l_mrfCancelAcceptParams.getBranchCode(),
                        l_mrfCancelAcceptParams.getOrderRequestNumber());

                    // TransactionCallback����
                    WEB3RuitoMRFCancelAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoMRFCancelAcceptNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
                            l_mrfCancelAcceptParams);

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
							log.debug("�������b�N���s�F" + l_mrfCancelAcceptParams.toString());
							continue;
						}
					}
					// ����ȊO�̃G���[�����������ꍇ
                    log.debug("__an Exception ",l_ex);
					log.debug("�ꌏ�����ɂė�O�����������ꍇ");
	                // �����Ώۂ̎����t�L���[���R�[�h.�����敪�h9�F�G���[�h���Z�b�g
	                HashMap l_map = new HashMap();
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    String l_strUpdateWhere = "institution_code = ? "
                                       + "and branch_code = ? "
                                       + "and order_request_number = ?";
                    String[] l_strArrayUpdateParams = {
                        l_mrfCancelAcceptParams.getInstitutionCode(),
                        l_mrfCancelAcceptParams.getBranchCode(),
                        l_mrfCancelAcceptParams.getOrderRequestNumber()};
                    // �V�j�@@�����Ώۂ�MRF�����t�L���[���R�[�h.�����敪�h9�F�G���[�h���Z�b�g���X�V����
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrfCancelAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            log.entering(STR_METHOD_NAME);
            return null;
        }
    }
}
@
