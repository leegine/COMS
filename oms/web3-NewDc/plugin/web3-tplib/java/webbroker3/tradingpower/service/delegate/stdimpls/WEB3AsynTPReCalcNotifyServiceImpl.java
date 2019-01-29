head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AsynTPReCalcNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�񓯊��T�[�r�XImpl(WEB3AsynTPReCalcNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyRow;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

/**
 * (�]�͌v�Z�ʒm�T�[�r�XImpl)
 */
public class WEB3AsynTPReCalcNotifyServiceImpl implements Runnable
{
    /**
     * �i���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynTPReCalcNotifyServiceImpl.class);

    /**
     * �]�͌v�Z�ʒm���C�����N�G�X�g
     */
    private WEB3TPReCalcNotifyRequest request;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3AsynTPReCalcNotifyServiceImpl(WEB3TPReCalcNotifyRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * (run)<BR>
     * <BR>
     * �V�[�P���X�}�u�i�]�͍Čv�Z�ʒm�񓯊��T�[�r�XImpl�jrun�v�Q�ƁB<BR>
     *  <BR>
     */
    public void run()
    {
        final String STR_METHOD_NAME = "WEB3AsynTPReCalcNotifyServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //getDefaultProcessor()
            try
            {
                Processors.getDefaultProcessor();
            }
            catch (DataFindException l_dfe)
            {
                log.error(l_dfe.getMessage(), l_dfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }

            //�]�͌v�Z�ʒmTransactionCallback()
            WEB3TPReCalcNotifyTransactionCallback l_transactionCallBack =
                new WEB3TPReCalcNotifyTransactionCallback();

            //seFrom�����h�c()
            l_transactionCallBack.setFromAccountId(this.request.fromAccountId.longValue());
            //se�s�������h�c()
            l_transactionCallBack.setToAccountId(this.request.toAccountId.longValue());

            // 1.5. doTransaction()
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);
            }
            catch (DataFindException l_dfe)
            {
                log.error(l_dfe.getMessage(), l_dfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataCallbackException l_dce)
            {
                log.error(l_dce.getMessage(), l_dce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dce.getMessage(),
                    l_dce);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(this.request.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�]�͌v�Z�ʒm�g�����U�N�V�����R�[���o�b�N)
     */
    public class WEB3TPReCalcNotifyTransactionCallback implements TransactionCallback
    {
        /**
         * (From����ID)
         */
        protected long fromAccountId;

        /**
         * (To����ID)
         */
        protected long toAccountId;

        /**
         * (�R���X�g���N�^)
         * @@roseuid 4235477D0375
         */
        public WEB3TPReCalcNotifyTransactionCallback()
        {

        }

        /**
         * (process)
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 41F5CBF90150
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "WEB3TPReCalcNotifyTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            /*
             * �]�͍Čv�Z�L���[�e�[�u��������
             */
            //���������F
            //�@@����ID >= From����ID
            //�@@����ID <= To����ID
            //�@@�����敪 >= 0�F������
            String l_strWhere = "account_id >= ? and account_id <= ? and status = ?";
            Object[] l_bindVars = new Object[3];
            l_bindVars[0] = new Long(this.fromAccountId);
            l_bindVars[1] = new Long(this.toAccountId);
            l_bindVars[2] = WEB3TPStatusDef.NOT_DEAL;

            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);

            //�\�[�g����:�����敪�A�쐬���t
            String l_strOrderBy = "occurred_div, created_timestamp";
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(
                    TpCalcResultExecNotifyRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);

            /*
             * �]�͍Čv�Z�̍ő又���������擾
             */
            String l_strMaxCount =
                GtlUtils.getTradingSystem().getPreference("system.tradingpower.process.count");
            int l_intMaxCount = Integer.MAX_VALUE;
            if(l_strMaxCount != null)
            {
                l_intMaxCount = Integer.parseInt(l_strMaxCount);
            }

            /*
             * �]�͌v�Z�ʒm�ꌏ�T�[�r�X�I�u�W�F�N�g���擾
             */
            WEB3TPReCalcNotifyUnitService l_service = null;

            try
            {
                l_service =
                    (WEB3TPReCalcNotifyUnitService)Services.getService(
                        WEB3TPReCalcNotifyUnitService.class);
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }

            /*
             * ����������iMIN(�ő又������, �����s��)�jLOOP����
             */
            int l_intCount = Math.min(l_intMaxCount, l_lisRows.size());
            for (int i = 0; i < l_intCount; i++)
            {
                TpCalcResultExecNotifyRow l_row = (TpCalcResultExecNotifyRow)l_lisRows.get(i);
                TpCalcResultExecNotifyParams l_params = new TpCalcResultExecNotifyParams(l_row);

                /*
                 * �]�͌v�Z�ʒm�ꌏ�T�[�r�X�I�u�W�F�N�g.notifyReCalc�i�j���R�[��
                 */
                try
                {
                    //�⏕�������擾
                    WEB3GentradeSubAccount l_subAccount = this.getSubAccount(l_params.getAccountId());
                    //notifyReCalc�i�j���R�[��
                    l_service.notifyReCalc(l_subAccount, l_params);
                }
                catch (WEB3BaseRuntimeException bre)
                {
                    /*
                     * �V�X�e���^�C�v�X�^���v���擾
                     */
                    TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
                    Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();

                    log.error(bre.getMessage(), bre);
                    l_params.setStatus(WEB3TPStatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(l_systemTimeStamp);
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_params);
                }
                catch (Exception e)
                {
                    /*
                     * �V�X�e���^�C�v�X�^���v���擾
                     */
                    TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
                    Timestamp l_systemTimeStamp = tradingSystem.getSystemTimestamp();

                    log.error(e.getMessage(), e);
                    l_params.setStatus(WEB3TPStatusDef.PROGRAM_ERROR);
                    l_params.setLastUpdatedTimestamp(l_systemTimeStamp);
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (getFrom����ID)
         * this.From����ID��ԋp����B
         * @@return long
         * @@roseuid 41F5CED5022B
         */
        public long getFromAccountId()
        {
            return this.fromAccountId;
        }

        /**
         * (getTo����ID)
         * this.To����ID��ԋp����B
         * @@return long
         * @@roseuid 4230F63A0259
         */
        public long getToAccountId()
        {
            return this.toAccountId;
        }

        /**
         * (setFrom����ID)
         * ����.From����ID��this.From����ID�ɃZ�b�g����B
         * @@param l_lngFromAccountId - (From����ID)
         * @@roseuid 41F5CE6F0037
         */
        public void setFromAccountId(long l_lngFromAccountId)
        {
            this.fromAccountId = l_lngFromAccountId;
        }

        /**
         * (setTo����ID)
         * ����.To����ID��this.To����ID�ɃZ�b�g����B
         * @@param l_lngToAccountId - (To����ID)
         * @@roseuid 4230F63A0278
         */
        public void setToAccountId(long l_lngToAccountId)
        {
            this.toAccountId = l_lngToAccountId;
        }

        /**
         * �iget�⏕�����j
         * 
         * �P�j�ڋq�I�u�W�F�N�g�𐶐�����B 
         * �@@[����]
         * �@@�@@����ID�F����.����ID
         * 
         * �Q�j�ڋq.is�M�p�����J��()�̔���
         * �@@[����]
         *   �@@�ٍϋ敪�F�h�w��Ȃ��h
         * 
         * �@@�����J�݂̏ꍇ(�ڋq.is�M�p�����J��()==false)
         * �@@�@@�@@�⏕����<�����������(�a���)>�I�u�W�F�N�g���擾���A���^�[������B
         * 
         * �@@���J�݂̏ꍇ(�ڋq.is�M�p�����J��()==true)
         * �@@�@@�@@�⏕����<�����M�p�������(�ۏ؋�)>�I�u�W�F�N�g���擾���A���^�[������B
         * @@param l_lngAccountId - (����ID)
         * @@return webbroker3.gentrade.WEB3GentradeSubAccount
         * @@roseuid 41F5CCEF0075
         */
        protected WEB3GentradeSubAccount getSubAccount(long l_lngAccountId)
        {
            final String STR_METHOD_NAME =
                "WEB3TPReCalcNotifyTransactionCallback.getSubAccount(long)";
            log.entering(STR_METHOD_NAME);

            try
            {
                //�ڋq
                WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngAccountId);
                //�⏕����
                SubAccount l_subAccount = null;

                //�M�p�����J�݂̔���
                if (!l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
                {
                    //�����ڋq�@@�����^�C�v�F�����������(�a���)
                    l_subAccount =
                        l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                }
                else
                {
                    //�M�p�ڋq�@@�����^�C�v�F�����M�p�������(�ۏ؋�)>
                    l_subAccount =
                        l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                }

                //�⏕������ԋp
                log.exiting(STR_METHOD_NAME);
                return new WEB3GentradeSubAccount(
                    (SubAccountRow)l_subAccount.getDataSourceObject());

            }
            catch (NotFoundException nfe)
            {
                log.error(nfe.getMessage(), nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            catch (DataQueryException dqe)
            {
                log.error(dqe.getMessage(), dqe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dqe.getMessage(),
                    dqe);
            }
            catch (DataNetworkException dne)
            {
                log.error(dne.getMessage(), dne);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dne.getMessage(),
                    dne);
            }
        }
    }
}
@
