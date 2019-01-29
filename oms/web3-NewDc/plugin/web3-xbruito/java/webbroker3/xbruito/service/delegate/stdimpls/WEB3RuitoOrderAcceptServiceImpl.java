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
filename	WEB3RuitoOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���������t�T�[�r�X�����N���X (WEB3RuitoOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  ���E (���u) �V�K�쐬
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
import webbroker3.xbruito.data.HostRuitoOrderAcceptParams;
import webbroker3.xbruito.data.HostRuitoOrderAcceptRow;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;
/**
 * �ݐϓ���������t�T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoOrderAcceptServiceImpl
    implements WEB3RuitoOrderAcceptService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptServiceImpl.class);
    /**
     * �ݐϓ���������t�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ��j������t�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����B<BR>
     * <BR>
     * �Q�j �ݓ�������tTransactionCallback�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@DB�g�����U�N�V�������������{����B<BR>
     * �@@[doTransaction�ɓn���p�����^]<BR>
     * �@@�@@�g�����U�N�V���������F TX_CREATE_NEW<BR>
     * �@@�@@�g�����U�N�V�����R�[���o�b�N�F <BR>
     *         �ݓ�������tTransactionCallback�C���X�^���X<BR>
     * <BR>
     * �S�j�@@�ݓ�������t���X�|���X�𐶐����A���^�[������B<BR>
     * �@@�|�ݓ�������t���N�G�X�g.create���X�|���X()���R�[�����A<BR>
     *       �ݓ�������t���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�|���������ݓ�������t���X�|���X��Ԃ��B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405A49A60260
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
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
        //�N�G���v���Z�b�T�̃C���X�^���X���擾����

        //�N�G���v���Z�b�T
        QueryProcessor l_queryProcessor = null;
        try
        {
            //�ݓ�������tTransactionCallback�̃C���X�^���X�𐶐�����
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW,
                    new WEB3RuitoOrderAcceptTransactionCallback());
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�ݓ�������t���X�|���X�𐶐����A���^�[������
        WEB3RuitoOrderAcceptRequest l_ruitoOrderAcceptRequest =
            (WEB3RuitoOrderAcceptRequest) l_request;
        WEB3BackResponse l_backResponse =
            l_ruitoOrderAcceptRequest.createResponse();
        return l_backResponse;
    }
    /**
     * �ݐϓ���������t�T�[�r�X���������{����B<BR>
     */
    public class WEB3RuitoOrderAcceptTransactionCallback
        implements TransactionCallback
    {
        /**
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@roseuid 4088F41D034B
         */
        public WEB3RuitoOrderAcceptTransactionCallback()
        {
        }
        /**
         * �ݐϓ���������t�T�[�r�X���������{����B<BR>
         * <BR>
         * �V�[�P���X�}�u�i�ݓ�������t�jprocess�v�Q�ƁB <BR>
         * <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 4088F45400BA
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1�@@�����Ώۃ��R�[�h�擾
            QueryProcessor l_processorObject =
                Processors.getDefaultProcessor();

            String l_strWhere = " status = ? ";
            String l_strCondition = null;
            String[] l_strBindValues = new String[1];
            l_strBindValues[0] = WEB3StatusDef.NOT_DEAL;
            List l_ruitoOrderAcceptresults =
                l_processorObject.doFindAllQuery(
                    HostRuitoOrderAcceptRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_strBindValues);

            String l_strInstatutionCode = null;     //�X�V�،���ЃR�[�h
            String l_strOrderRequestNumber = null;  //�X�V���ʃR�[�h
            String l_strBranchCode = null;          //�X�V���X�R�[�h
            RuitoOrderUnit l_ruitoOrderUnit = null; //�ݓ������P��
            String l_strAcceptStatus = null;        //��t�ʒm�敪
            String l_strErrorCode = null;           //�G���[�R�[�h

            //1.2�@@�ݓ�������tUnitService���擾����B
            WEB3RuitoOrderAcceptUnitService l_ruitoOrderAcceptUnitService =
                (WEB3RuitoOrderAcceptUnitService) Services.getService(
                    WEB3RuitoOrderAcceptUnitService.class);

            //1.3�@@�ݓ���t�m��C���^�Z�v�^�𐶐�����B
            WEB3RuitoAcceptedDecisionInterceptor
                l_ruitoAcceptedDecisionInterceptor =
                    new WEB3RuitoAcceptedDecisionInterceptor();

            int l_intSize = 0;
            if(l_ruitoOrderAcceptresults != null)
            {
                l_intSize = l_ruitoOrderAcceptresults.size();
            }

            //�ȍ~�̏����́A�������ʂ̗ݓ�������t�L���[�e�[�u����<BR>
              //�e�s�ɑ΂��Ď��{����B<BR>

            //�g���ݓ������}�l�[�W���I�擾
            FinApp l_finApp =
                (FinApp) Services.getService(FinApp.class);
            WEB3RuitoOrderManager l_web3RuitoOrderManager =
                (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();

            //�ݓ�������t�L���[Params
            HostRuitoOrderAcceptParams l_hostRuitoOrderAcceptParams = null;

            //1.4
            for (int i = 0; i < l_intSize; i++)
            {
                l_hostRuitoOrderAcceptParams =
                    (HostRuitoOrderAcceptParams) l_ruitoOrderAcceptresults.get(i);

                //�،���ЃR�[�h�I�擾
                l_strInstatutionCode =
                    l_hostRuitoOrderAcceptParams.getInstitutionCode();

                //���X�R�[�h�I�擾
                l_strBranchCode =
                    l_hostRuitoOrderAcceptParams.getBranchCode();

                //���ʃR�[�h�I�擾
                l_strOrderRequestNumber =
                    l_hostRuitoOrderAcceptParams.getOrderRequestNumber();

                //�G���[�R�[�h�I�擾
                l_strErrorCode =
                    l_hostRuitoOrderAcceptParams.getErrorMessage();

                //��t�ʒm�敪�I�擾
                l_strAcceptStatus =
                    l_hostRuitoOrderAcceptParams.getAcceptStatus();
                try
                {
                    //1.4.1�@@���������ݓ���t�m��C���^�Z�v�^�I
                     //�u�W�F�N�g.set�����G���[���R�R�[�h()��<BR>
                      //�R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B
                    l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(
                        l_strErrorCode);

                   //1.4.2 �L���[���R�[�h�ɊY������ݓ������P�ʃI�u�W�F�N�g���擾����
                    //�g���ݓ������}�l�[�W��.get�����P��()���R�[�����āA<BR>
                     //�ݓ������P�ʃI�u�W�F�N�g���擾����B
                    l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                        l_ruitoAcceptedDecisionInterceptor);
                    l_ruitoOrderUnit =
                        l_web3RuitoOrderManager.getRuitoOrderUnit(
                            l_strInstatutionCode,
                            l_strBranchCode,
                            l_strOrderRequestNumber);

                     //1.4.3�@@������t�������s���B
                    // TransactionCallback����
                    WEB3RuitoOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3RuitoOrderAcceptNormalTransactionCallback(
                            l_ruitoOrderUnit,
                            l_ruitoAcceptedDecisionInterceptor,
                            l_hostRuitoOrderAcceptParams);

                    // doTransaction()
                    l_processorObject.doTransaction(
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
							log.debug("�������b�N���s�F" + l_hostRuitoOrderAcceptParams.toString());
							continue;
						}
					}
					// ����ȊO�̃G���[�����������ꍇ
                    log.debug("__an Exception__ ",l_ex);
					log.debug("�ꌏ�����ɂė�O�����������ꍇ");
                	// 1.4.4�@@�L���[�e�[�u���̏����敪���X�V
                	String l_strUpdateWhere =
                    	" institution_code = ?" +
                    	" and branch_code = ?" +
                    	" and order_request_number = ?";
                	String[] l_strArrayUpdateParams =
                    	{   l_strInstatutionCode,
                        	l_strBranchCode,
                        	l_strOrderRequestNumber};
                	// �����Ώۂ�MRF�����t�L���[���R�[�h.�����敪�h9�F�G���[�h���Z�b�g���X�V����
                	HashMap l_map = new HashMap();
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    // do update
                    l_processorObject.doUpdateAllQuery(
                        HostRuitoOrderAcceptParams.TYPE,
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
