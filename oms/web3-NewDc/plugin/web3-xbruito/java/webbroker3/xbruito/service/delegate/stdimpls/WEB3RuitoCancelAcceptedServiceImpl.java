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
filename	WEB3RuitoCancelAcceptedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ��������t�T�[�r�X (WEB3RuitoCancelAcceptedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  �����F (���u) �V�K�쐬
Revesion History : 2009/01/23  ���u�� (���u) �d�l�ύX���f��No.099
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
import webbroker3.xbruito.data.HostRuitoCancelAcceptParams;
import webbroker3.xbruito.data.HostRuitoCancelAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;

/**
 * �ݐϓ��������t�T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoCancelAcceptedServiceImpl
        implements WEB3RuitoCancelAcceptedService
{
    private static WEB3LogUtility log =
    WEB3LogUtility.getInstance(WEB3RuitoCancelAcceptedServiceImpl.class);

   /**
    * �ݐϓ��������t�T�[�r�X���������{����B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�i�ݓ��j�����t�v�Q�ƁB<BR>
    * <BR>
    * �P�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����B<BR>
    * <BR>
    * �Q�j �ݓ������tTransactionCallback�̃C���X�^���X�𐶐�����B<BR>
    * <BR>
    * �R�j�@@DB�g�����U�N�V�������������{����B<BR>
    * �@@   [doTransaction�ɓn���p�����^]<BR>
    * �@@�@@ �g�����U�N�V���������F TX_CREATE_NEW<BR>
    * �@@�@@ �g�����U�N�V�����R�[���o�b�N�F
    * �ݓ������tTransactionCallback�C���X�^���X<BR>
    * <BR>
    * �S�j�@@�ݓ������t���X�|���X�𐶐����A���^�[������B<BR>
    * �@@�|�ݓ������t���N�G�X�g.create���X�|���X()���R�[�����A<BR>
    *      �ݓ������t���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    * �@@�|���������ݓ������t���X�|���X��Ԃ��B<BR>
    *
    * @@param l_request
    *  - ���N�G�X�g�f�[�^ <BR>
    *
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 405820C8012B
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
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1)�N�G���v���Z�b�T�̃C���X�^���X���擾����
        QueryProcessor l_queryProcessor = null; //�N�G���v���Z�b�T
        WEB3RuitoCancelAcceptedTransactionCallback
            l_ruitoOrderAcceptTransactionCallback = null;
        try
        {
            //DataFindException,DataNetworkException
            l_queryProcessor = Processors.getDefaultProcessor();

            //�Q�j�ݓ�������tTransactionCallback�̃C���X�^���X�𐶐�����B

            l_ruitoOrderAcceptTransactionCallback =
                    new WEB3RuitoCancelAcceptedTransactionCallback();

            //�R�jDB�g�����U�N�V�������������{����
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ruitoOrderAcceptTransactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]");
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
        //�S�j�ݓ�������t���X�|���X�𐶐����A���^�[������B
        WEB3RuitoCancelAcceptRequest l_ruitoCancelAcceptRequest =
            (WEB3RuitoCancelAcceptRequest) l_request;

        WEB3RuitoCancelAcceptResponse l_ruitoCancelAcceptResponse =
            (WEB3RuitoCancelAcceptResponse)
        l_ruitoCancelAcceptRequest.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_ruitoCancelAcceptResponse;
    }

   /**
    * �ݐϓ��������t�T�[�r�X���������{����B<BR>
    */
    public class WEB3RuitoCancelAcceptedTransactionCallback
    implements TransactionCallback
    {

      /**
       * �ݓ����������ʒmUnitServiceImpl <BR>
       * @@roseuid 40BFE5BC0192
       */
        public WEB3RuitoCancelAcceptedTransactionCallback()
        {

        }

      /**
       * �ݐϓ��������t�T�[�r�X���������{����B<BR>
       * <BR>
       * �V�[�P���X�}�u�i�ݓ������t�jprocess�v�Q�ƁB <BR>
       * <BR>
       * @@return Object
       * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
       * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
       * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
       * @@roseuid 408893DD0232
       */
        public Object process() throws
            DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1�@@�����Ώۃ��R�[�h�擾
            String l_strWhere = "status = ?";
            String l_strCondition = null;

            Object[] l_objWhere = {WEB3StatusDef.NOT_DEAL};
            log.debug("where status = " + WEB3StatusDef.NOT_DEAL);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult =
                    l_queryProcessor.doFindAllQuery(
                    HostRuitoCancelAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhere);

            //�@@�ݓ������tUnitService���擾����B
            WEB3RuitoCancelAcceptedUnitService l_cancelAcceptUnitService =
                (WEB3RuitoCancelAcceptedUnitService) Services.getService(
                    WEB3RuitoCancelAcceptedUnitService.class);

            //1.2�@@�ݓ���t�m��C���^�Z�v�^�𐶐�����B
            WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor =
                new WEB3RuitoAcceptedDecisionInterceptor();
            //�ȍ~�̏����́A�������ʂ̗ݓ������t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            WEB3RuitoOrderManager l_ruitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();

            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor
                (l_ruitoAcceptedDecisionInterceptor);

            String l_strAcceptStatus = null;
            HostRuitoCancelAcceptParams l_cancelAcceptParams = null;

            int l_intSize = l_lisSearchResult.size();
            //1.3
            for (int i = 0; i < l_intSize; i++)
            {
                l_cancelAcceptParams =
                    (HostRuitoCancelAcceptParams) l_lisSearchResult.get(i);
                boolean l_blnIsFail = false;
                try
                {
                    //1.3.1 ���������ݓ���t�m��C���^�Z�v�^�I�u�W�F�N�g.set�����G���[���R�R�[�h()��
                    //�R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B
                    l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(
                            l_cancelAcceptParams.getErrorCode());

                    //1.3.2 �L���[���R�[�h�ɊY������ݓ������P�ʃI�u�W�F�N�g���擾����B
                    RuitoOrderUnit l_ruitoOrderUnit = l_ruitoOrderMgr.getRuitoOrderUnit(
                        l_cancelAcceptParams.getInstitutionCode(),
                        l_cancelAcceptParams.getBranchCode(),
                        l_cancelAcceptParams.getOrderRequestNumber());

                    //1.3.3 �����t�������s���B
                    l_strAcceptStatus = l_cancelAcceptParams.getAcceptStatus();
                    log.debug("��t�ʒm�敪:l_strAcceptStatus = "
                                    + l_strAcceptStatus);

                    // TransactionCallback����
                    WEB3RuitoCancelAcceptedNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoCancelAcceptedNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
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

                    if (l_ex instanceof WEB3SystemLayerException)
                    {
                        //�Y���������݂��Ȃ��ꍇ�A�X�L�b�v�������s���B
                        //�����Ώۂ̗ݓ������t�L���[���R�[�h.�����敪���h�P�F�����ρh�ɍX�V���A����1.3�֖߂�B
                        WEB3SystemLayerException l_systemLayerException =
                            (WEB3SystemLayerException)l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(
                            l_systemLayerException.getErrorInfo()))
                        {
                            HashMap l_map = new HashMap();
                            l_map.put("status", WEB3StatusDef.DEALT);

                            String l_strUpdateWhere = " institution_code = ? "
                                + " and branch_code = ? "
                                + " and order_request_number = ? ";
                            String[] l_strArrayUpdateParams = {
                                l_cancelAcceptParams.getInstitutionCode(),
                                l_cancelAcceptParams.getBranchCode(),
                                l_cancelAcceptParams.getOrderRequestNumber()};

                            l_queryProcessor.doUpdateAllQuery(
                                HostRuitoCancelAcceptRow.TYPE,
                                l_strUpdateWhere,
                                l_strArrayUpdateParams,
                                l_map);

                            continue;
                        }
                    }

					// ����ȊO�̃G���[�����������ꍇ
                    log.debug("__an Exception__ ",l_ex);
					log.debug("�ꌏ�����ɂė�O�����������ꍇ");
	                //1.3.4 �L���[�e�[�u���̏����敪�h9�F�G���[�h���Z�b�g���X�V����
    	            HashMap l_map = new HashMap();
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    String l_strUpdateWhere = " institution_code = ? "
                            + " and branch_code = ? "
                            + " and order_request_number = ?";
                    String[] l_strArrayUpdateParams = {
                            l_cancelAcceptParams.getInstitutionCode(),
                            l_cancelAcceptParams.getBranchCode(),
                            l_cancelAcceptParams.getOrderRequestNumber() };
                    l_queryProcessor.doUpdateAllQuery(
                        HostRuitoCancelAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_strArrayUpdateParams,
                        l_map);
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
