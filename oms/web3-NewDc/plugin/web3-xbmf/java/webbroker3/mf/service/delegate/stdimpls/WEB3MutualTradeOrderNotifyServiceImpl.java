head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����M�����������ʒm�T�[�r�X�����N���X(WEB3MutualTradeOrderNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
Revesion History : 2007/12/26 ���g (���u) �d�l�ύX No589
Revesion History : 2009/05/13 �g�C�� (���u) �d�l�ύX No642
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.data.HostXbmfOrderNotifyRow;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyRequest;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * �����M�����������ʒm�T�[�r�X�����N���X<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualTradeOrderNotifyService
{

    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyServiceImpl.class);

    /**
     * �����M�����������ʒm�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����M���j���������ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40567D300345
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //If (l_request == null)
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //�P.1�j�@@�N�G���v���Z�b�T�̃C���X�^���X���擾����B
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            //�P.2�j ���M���������ʒmTransactionCallback�̃C���X�^���X�𐶐�����B
            WEB3MutualTradeOrderNotifyTransactionCallback
                l_mutualTradeOrderNotifyTransactionCallback =
                    new WEB3MutualTradeOrderNotifyTransactionCallback();

            // �P.3�j�@@DB�g�����U�N�V�������������{����B
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_mutualTradeOrderNotifyTransactionCallback);

            //���M���������ʒm���X�|���X�𐶐����A���^�[������B

            // ���M���������ʒm���N�G�X�g�擾
            WEB3MutualTradeOrderNotifyRequest l_mutualTradeOrderNotifyRequest =
                (WEB3MutualTradeOrderNotifyRequest) l_request;

            //�|���M���������ʒm���N�G�X�g.create���X�|���X()���R�[�����A
             // ���M���������ʒm���X�|���X�I�u�W�F�N�g�𐶐�����B
            WEB3BackResponse l_backResponse =
                l_mutualTradeOrderNotifyRequest.createResponse();

            //�|�����������M���������ʒm���X�|���X��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_backResponse;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̃C���X�^���X���擾���s");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̃C���X�^���X���擾���s");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    public class WEB3MutualTradeOrderNotifyTransactionCallback
        implements TransactionCallback
    {
        /**
         *  ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3MutualTradeOrderNotifyTransactionCallback.class);

        /**
         * (���M���������ʒmTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@roseuid 40A4334D0137
         */
        public WEB3MutualTradeOrderNotifyTransactionCallback()
        {

        }

        /**
         * ���M���������ʒm�T�[�r�X���������{����B <BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i���M���������ʒm�jprocess�v�Q�ƁB <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A4343E01C4
         */

        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            //1.1�j �����Ώۃ��R�[�h�擾
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            //���M�����ʒm�L���[�e�[�u�����A�s���b�N�iselect for update�j�ɂēǂݍ��ށB
            String l_strWhere = " status = ?";
            String l_strCondition = null;
            String[] l_strBindValues = new String[1];

            //���M�����ʒm�L���[Params.�����敪 = �h0�F�������h
            l_strBindValues[0] = WEB3StatusDef.NOT_DEAL;

            /*����*/
            List l_lisMFOrderNotifyresults =
                l_queryProcessor.doFindAllQuery(
                    HostXbmfOrderNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_strBindValues);

            //1.2�j�@@���M���������ʒmUnitService���擾����B
            WEB3MutualTradeOrderNotifyUnitService
                l_mutualTradeOrderNotifyUnitService =
                    (WEB3MutualTradeOrderNotifyUnitService) Services.getService(
                        WEB3MutualTradeOrderNotifyUnitService.class);
            
            int l_intSize = 0;
            if(l_lisMFOrderNotifyresults != null)
            {
                l_intSize = l_lisMFOrderNotifyresults.size();
            }

            //�ȍ~�̏����́A�������ʂ̓��M�����ʒm�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B

            //���M�����ʒm�L���[Params
            HostXbmfOrderNotifyParams l_orderNotifyParams = null;

            /*����*/
            for (int i = 0; i < l_intSize; i++)
            {
                //�������ʂ̓��M�����ʒm�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����
                l_orderNotifyParams =
                    (HostXbmfOrderNotifyParams) l_lisMFOrderNotifyresults.get(i);

                //�،���ЃR�[�h�擾
                String l_strInstatutionCode =
                    l_orderNotifyParams.getInstitutionCode();

                //���X�R�[�h�擾
                String l_strBranchCode =
                    l_orderNotifyParams.getBranchCode();

                //���ʃR�[�h�擾
                String l_strOrderRequestNumber =
                    l_orderNotifyParams.getOrderRequestNumber();

                HashMap l_map = new HashMap();         //�����敪��ݒ�
                String l_strUpdateWhere =
                    " institution_code = ? "+          //�X�V�،���ЃR�[�h
                    " and branch_code = ? "+           //�X�V���X�R�[�h
                    " and order_request_number = ? ";  //�X�V���ʃR�[�h
                String[] l_updateParams = {
                    l_strInstatutionCode,
                    l_strBranchCode,
                    l_strOrderRequestNumber };

                //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                //�،���ЃR�[�h�F���M�����ʒm�L���[�e�[�u��Params�Dget�،���ЃR�[�h()
                //���X�R�[�h�F���M�����ʒm�L���[�e�[�u��Params�Dget���X�R�[�h()
                //�����R�[�h�F ���M�����ʒm�L���[�e�[�u��Params�Dget�ڋq�R�[�h()
                String l_strAccountCode = l_orderNotifyParams.getAccountCode();
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accMgr =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                try
                {
                    l_accMgr.getMainAccount(
                        l_strInstatutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                }
                catch (WEB3BaseException l_ex)
                {
                    l_map.put("status", WEB3StatusDef.DEALT);

                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfOrderNotifyRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);

                    continue;
                }

                try
                {
                    //�،���ЃI�u�W�F�N�g���擾����B
                    //[����]
                    //��ЃR�[�h�F ���M�����ʒm�L���[�e�[�u��Params.get��ЃR�[�h()
                    Institution l_strInstitution =
                        l_accMgr.getInstitution(l_strInstatutionCode);

                    try
                    {
                        //get���M����(�،���� : Institution, �����R�[�h : String)
                        //�A�C�e���̒�`
                        //�g�����M�����I�u�W�F�N�g���擾����B
                        //[����]
                        //�،���ЁF �擾�����،���ЃI�u�W�F�N�g
                        //�����R�[�h�F ���M�����ʒm�L���[Params.get�����R�[�h()�̖߂�l
                        WEB3MutualFundProductManager l_mutualFundProductManager =
                            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                                ProductTypeEnum.MUTUAL_FUND).getProductManager();
                        String l_strProductCode = l_orderNotifyParams.getProductCode();

                        l_mutualFundProductManager.getMutualFundProduct(
                            l_strInstitution, l_strProductCode);
                    }
                    catch (NotFoundException l_ex)
                    {
                        l_map.put("status", WEB3StatusDef.DEALT);

                        l_queryProcessor.doUpdateAllQuery(
                            HostXbmfOrderNotifyRow.TYPE,
                            l_strUpdateWhere,
                            l_updateParams,
                            l_map);

                        continue;
                    }

                    // TransactionCallback����
                    WEB3MutualTradeOrderNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3MutualTradeOrderNotifyNormalTransactionCallback(
                            l_orderNotifyParams);

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
							log.debug("�������b�N���s�F" + l_orderNotifyParams.toString());
							continue;
						}
					}
                    //����ȊO�̗�O���X���[�����ꍇ�A
                    //�����Ώۂ̓��M�����ʒm�L���[���R�[�h.�����敪�Ɂh
                    //9�F�G���[�h���Z�b�g���X�V����B
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    m_log.error("�ꌏ�����ɂė�O�����������ꍇ", l_ex);

                    l_queryProcessor.doUpdateAllQuery(
                        HostXbmfOrderNotifyRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);
                }
            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
