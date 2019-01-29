head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �ݐϓ���MRF������t�T�[�r�X�����N���X(WEB3RuitoMRFOrderAcceptServiceImpl)
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
import webbroker3.xbruito.data.HostMrfOrderAcceptParams;
import webbroker3.xbruito.data.HostMrfOrderAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;

/**
 * �ݐϓ���MRF������t�T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoMRFOrderAcceptServiceImpl implements
        WEB3RuitoMRFOrderAcceptService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3RuitoMRFOrderAcceptServiceImpl.class);

    /**
     * �ݐϓ���MRF������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ��jMRF������t�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����B<BR>
     * <BR>
     * �Q�j �ݓ�MRF������tTransactionCallback��<BR>
     *    �C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@DB�g�����U�N�V�������������{����B<BR>
     *  �@@[doTransaction�ɓn���p�����^]<BR>
     * �@@ �g�����U�N�V���������F TX_CREATE_NEW<BR>
     * �@@ �g�����U�N�V�����R�[���o�b�N�F <BR>
     *    �ݓ�MRF������tTransactionCallback�C���X�^���X<BR>
     * <BR>
     * �S�j�@@�ݓ�MRF������t���X�|���X�𐶐����A���^�[������B<BR>
     * �@@�|�ݓ�MRF������t���N�G�X�g.create���X�|���X()<BR>
     *    ���R�[�����A�ݓ�MRF������t���X�|���X�I�u�W�F�N�g<BR>
     *    �𐶐�����B<BR>
     * �@@�|���������ݓ�MRF������t���X�|���X��Ԃ��B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405814D1013B
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
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        // 1)�N�G���v���Z�b�T�̃C���X�^���X���擾����
        QueryProcessor l_queryProcessor = null; //�N�G���v���Z�b�T
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j �ݓ�MRF������tTransactionCallback�̃C���X�^���X�𐶐�����B
        WEB3RuitoMRFOrderAcceptTransactionCallback l_ruitoMRFOrderAcceptTransactionCallback = null;
        l_ruitoMRFOrderAcceptTransactionCallback = new WEB3RuitoMRFOrderAcceptTransactionCallback();

        // 3)DB�g�����U�N�V�������������{����
        try
        {
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ruitoMRFOrderAcceptTransactionCallback);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@�ݓ�MRF������t���X�|���X�𐶐����A���^�[������B
        WEB3RuitoMRFOrderAcceptRequest l_ruitoMRFOrderAcceptRequest = (WEB3RuitoMRFOrderAcceptRequest) l_request;

        WEB3RuitoMRFOrderAcceptResponse l_ruitoMRFOrderAcceptResponse = (WEB3RuitoMRFOrderAcceptResponse) l_ruitoMRFOrderAcceptRequest
                .createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_ruitoMRFOrderAcceptResponse;

    }

    /**
     * �ݐϓ���MRF������t�T�[�r�X���������{����B<BR>
     */
    public class WEB3RuitoMRFOrderAcceptTransactionCallback implements
            TransactionCallback
    {

        /**
         * �ݐϓ���MRF������t�T�[�r�X���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�ݓ�MRF������t�jprocess�v�Q�ƁB<BR>
         * <BR>
         * �P�j�@@�����Ώۃ��R�[�h�擾<BR>
         * �@@MRF������t�L���[�e�[�u�����A<BR>
         *   �s���b�N�iselect for update�j�ɂēǂݍ��ށB<BR>
         * <BR>
         * �@@�m���������n<BR>
         * �@@�����敪 = �h0�F�������h<BR>
         * �@@�mcondition�n<BR>
         * �@@�hfor update�h<BR>
         * <BR>
         * �Q�j�@@�ݓ�MRF������tUnitService���擾����B<BR>
         * <BR>
         * �R�j�@@�ݓ���t�m��C���^�Z�v�^�𐶐�����B<BR>
         * <BR>
         *   �ȍ~�̏����́A<BR>
         *   �������ʂ̗ݓ�MRF������t�L���[�e�[�u����<BR>
         *   �e�s�ɑ΂��Ď��{����B<BR>
         * <BR>
         * �S�j�@@���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g.<BR>
         *    set�����G���[���R�R�[�h()��<BR>
         * �@@�R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B<BR>
         * �@@[set�����G���[���R�R�[�h�ɓn���p�����^]<BR>
         * �@@�����G���[���R�R�[�h�F <BR>
         *    MRF������t�L���[Params.get�G���[�R�[�h()�̖߂�l<BR>
         * <BR>
         * �T�j �L���[���R�[�h�ɊY������ݓ������P�ʃI�u�W�F�N�g<BR>
         *    ���擾����B<BR>
         * �@@ �g���ݓ������}�l�[�W��.get�����P��()���R�[�����āA<BR>
         *    �ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
         *  �@@�mget�����P�ʂɓn���p�����^�n<BR>
         * �@@�@@�،���ЃR�[�h�F<BR>
         *      MRF������t�L���[Params.get�،���ЃR�[�h()�̖߂�l<BR>
         * �@@�@@���X�R�[�h�F <BR>
         *     MRF������t�L���[Params.get���X�R�[�h()�̖߂�l<BR>
         * �@@�@@�ڋq�R�[�h�F <BR>
         *      MRF������t�L���[Params.get�ڋq�R�[�h()�̖߂�l<BR>
         * �@@�@@���ʃR�[�h�F <BR>
         *      MRF������t�L���[Params.get���ʃR�[�h()�̖߂�l<BR>
         * <BR>
         * �U�j�@@������t�������s���B<BR>
         * �@@�|���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g.set�G���[�R�[�h()<BR>
         *     ���R�[�����A�G���[�R�[�h�̐ݒ���s���B<BR>
         * �@@ �@@[set�G���[�R�[�h�ɓn���p�����^]<BR>
         * �@@ �@@�G���[�R�[�h�F <BR>
         *      MRF������t�L���[Params.get�G���[�R�[�h()�̖߂�l<BR>
         * <BR>
         * �@@ �|MRF������t�L���[Params.get��t�ʒm�敪()<BR>
         *     �̖߂�l���h1�F������t�����h�̏ꍇ�A<BR>
         * �@@�@@�ݓ�MRF������tUnitService.notify������t����()<BR>
         *     ���R�[������B<BR>
         * �@@�@@[notify������t�����ɓn���p�����^]<BR>
         * �@@�@@�ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
         * �@@�@@�ݓ���t�m��C���^�Z�v�^�F <BR>
         *     ���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g<BR>
         * <BR>
         * �@@ �|MRF������t�L���[Params.get��t�ʒm�敪()<BR>
         *     �̖߂�l���h1�F������t�����h�łȂ��ꍇ�A<BR>
         * �@@ �ݓ�MRF������tUnitService.notify������t���s()<BR>
         *     ���R�[������B<BR>
         * �@@  [notify������t���s�ɓn���p�����^]<BR>
         * �@@  �ݓ������P�ʁF �擾�����ݓ������P�ʃI�u�W�F�N�g<BR>
         * �@@�@@�ݓ���t�m��C���^�Z�v�^�F <BR>
         *     ���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g<BR>
         * <BR>
         * �V�j�@@�L���[�e�[�u���̏����敪���X�V<BR>
         *    ����I�������ꍇ�A<BR>
         * �����Ώۂ�MRF������t�L���[���R�[�h.�����敪�� �h1�F�����ρh���Z�b�g���X�V����B<BR>
         * <BR>
         *    ��O���X���[�����ꍇ�A<BR>
         * �����Ώۂ�MRF������t�L���[���R�[�h.�����敪�Ɂh9�F�G���[�h���Z�b�g���X�V����B <BR>
         * �A���A�T�[�r�X�C���^�Z�v�^onCall�ŗ�O�������́A�����敪���X�V���Ȃ�<BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 408769A200E9
         */
        public Object process() throws DataNetworkException,
                DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "Object process()";
            log.entering(STR_METHOD_NAME);

            //�P�j�@@�����Ώۃ��R�[�h�擾
            String l_strWhere = "status = ?";
            String l_strCondition = null;
            Object[] l_objWhere = { WEB3StatusDef.NOT_DEAL };
            log.debug("where status = " + WEB3StatusDef.NOT_DEAL);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                HostMrfOrderAcceptRow.TYPE, l_strWhere, null, l_strCondition,
                l_objWhere);

            //�Q�j�@@�ݓ�MRF������tUnitService���擾����B
            WEB3RuitoMRFOrderAcceptUnitService l_unitService =
                (WEB3RuitoMRFOrderAcceptUnitService) Services.getService(WEB3RuitoMRFOrderAcceptUnitService.class);
            //�ȍ~�̏����́A�������ʂ̗ݓ�MRF�����t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            WEB3RuitoOrderManager l_ruitoOrderMgr = (WEB3RuitoOrderManager) l_tm.getOrderManager();

            String l_strAcceptStatus;
            HostMrfOrderAcceptParams l_params = null;
            int i = 0;
            int l_intSize = 0;
            l_intSize = l_lisSearchResult.size();
            log.debug("Output the number of search records = " + l_intSize);
            for (i = 0; i < l_intSize; i++)
            {
                log.debug("Enter for path.");

                l_params = (HostMrfOrderAcceptParams) l_lisSearchResult.get(i);

                //�R�j�@@�ݓ���t�m��C���^�Z�v�^�𐶐�����B
                WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor =
                    new WEB3RuitoAcceptedDecisionInterceptor();
                l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(l_ruitoAcceptedDecisionInterceptor);
                boolean l_blnIsFail = false;
                try
                {
                    // �S�j�@@���������ݓ���t�m��
                    l_ruitoAcceptedDecisionInterceptor
                            .setOrderErrorReasonCode(l_params.error_code);

                    //�T�j �L���[���R�[�h�ɊY������ݓ������P�ʃI�u�W�F�N�g���擾����B

                    log.debug("HostMrfOrderAccept.BranchCode = "
                            + l_params.getBranchCode());
                    log.debug("HostMrfOrderAccept.RequstOrderNumber = "
                            + l_params.getOrderRequestNumber());

                    RuitoOrderUnit l_ruitoOrderUnit = null;
                    //NotFoundException
                    l_ruitoOrderUnit = l_ruitoOrderMgr.getRuitoOrderUnit(
                        l_params.getInstitutionCode(),
                        l_params.getBranchCode(),
                        l_params.getOrderRequestNumber());

                    // TransactionCallback����
                    WEB3RuitoMRFOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoMRFOrderAcceptNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
                            l_params);

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
							log.debug("�������b�N���s�F" + l_params.toString());
							continue;
						}
					}
					//����ȊO�̃G���[�����������ꍇ
                    log.debug("__an Exception__ ",l_ex);
					log.debug("�ꌏ�����ɂė�O�����������ꍇ");
         	        // �V�j�@@�L���[�e�[�u���̏����敪���X�V
            		String l_strUpdateWhere = " institution_code = ?"
                        + " and branch_code = ?"
                        + " and order_request_number = ?";
                	String[] l_strArrayUpdateParams = {
                        l_params.getInstitutionCode(),
                        l_params.getBranchCode(),
                        l_params.getOrderRequestNumber() };
                	// �����Ώۂ�MRF�����t�L���[���R�[�h.�����敪�h9�F�G���[�h���Z�b�g���X�V����
                	HashMap l_map = new HashMap();
                    l_map.put(
                        "status", WEB3StatusDef.DATA_ERROR);

                    // �V�j�@@�����Ώۂ̎����t�L���[���R�[�h.�����敪��ݒ�h
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrfOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * �f�t�H���g�R���X�g���N�^�B<BR>
         * @@roseuid 40877B150128
         */
        public WEB3RuitoMRFOrderAcceptTransactionCallback()
        {

        }
    }
}@
