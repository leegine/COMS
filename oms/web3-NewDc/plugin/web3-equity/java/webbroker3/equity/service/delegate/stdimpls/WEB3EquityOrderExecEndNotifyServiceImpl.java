head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecEndNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���I���ʒm�T�[�r�XImpl(WEB3EquityOrderExecEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2006/11/20 �����F(���u) ���f�� 1024
Revesion History : 2008/01/25 ��іQ(���u) ���f��NO.1287
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.equity.message.WEB3EquityExecEndNotifyRequest;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecEndNotifyService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�o���I���ʒm�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �o���I���ʒm�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityOrderExecEndNotifyServiceImpl
    implements WEB3EquityOrderExecEndNotifyService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderExecEndNotifyServiceImpl.class);

    /**
     * @@roseuid 40B1BACD02E9
     */
    public WEB3EquityOrderExecEndNotifyServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �o���I���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���I���ʒm�T�[�r�X�j�o���I���ʒm�v�Q��<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4137CFA802E4
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityExecEndNotifyRequest l_execEndNotifyRequest =
            (WEB3EquityExecEndNotifyRequest)l_request;
        l_execEndNotifyRequest.validate();

        try
        {
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            OnlineTransactionCallback l_onTransactionCallback =
                new OnlineTransactionCallback(
                    l_execEndNotifyRequest.institutionCode,
                    l_execEndNotifyRequest.rangeFrom,
                    l_execEndNotifyRequest.rangeTo);
            l_onlineRunStatus =
                (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onTransactionCallback);
            ServiceTransactionCallback l_transactionCallback =
                new ServiceTransactionCallback(
                    l_execEndNotifyRequest.institutionCode,
                    l_execEndNotifyRequest.rangeFrom,
                    l_execEndNotifyRequest.rangeTo,
                    l_onlineRunStatus);
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);
        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_execEndNotifyRequest.createResponse();
    }
    
    /**
     * (�I�����C�����s����TransactionCallback)<BR>
     * �I�����C�����s����TransactionCallback�N���X�B<BR>
     */
    public class OnlineTransactionCallback
        implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)�B<BR>
         * �o���I���Ώۂ̏،���ЃR�[�h�B<BR>
         */
        private String institutionCode;
    
        /**
         * (From����ID)�B<BR>
         * From����ID�B<BR>
         */
        private long rangeFrom;
    
        /**
         * (To����ID)�B<BR>
         * To����ID�B<BR>
         */
        private long rangeTo;

        /**
         * (�I�����C�����s����TransactionCallback)�B<BR>
         * �R���X�g���N�^�B<BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h�B<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From����ID�B<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID�B<BR>
         */
        public OnlineTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
        }
        
        /**
         * (process)<BR>
         * �I�����C�����s���ʃe�[�u����"������"�ݒ���s���B<BR>
         * �V�[�P���X�}
         *  �u�i�o���I���ʒm�T�[�r�X�jprocess�v�Q�ƁB 
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40B1C28501F0
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "OnlineTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            String l_strStatus = WEB3RunStatusDivDef.DEALED;
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode, ProductTypeEnum.EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT, WEB3OnlineServiceDiv.ORDER_EXEC_END,
                    this.rangeFrom, this.rangeTo);
            }
            catch (WEB3BaseException l_be)
            {
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(l_be.getMessage(), l_be);
            }
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
    /**
     * (�o���I���ʒmTransactionCallback)<BR>
     * �o���I���ʒmTransactionCallback�N���X�B<BR>
     */
    public class ServiceTransactionCallback
        implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)�B<BR>
         * �o���I���Ώۂ̏،���ЃR�[�h�B<BR>
         */
        private String institutionCode;
    
        /**
         * (From����ID)�B<BR>
         * From����ID�B<BR>
         */
        private long rangeFrom;
    
        /**
         * (To����ID)�B<BR>
         * To����ID�B<BR>
         */
        private long rangeTo;
        
        /**
         * (�I�����C�����s����)�B<BR>
         * �I�����C�����s���ʁB<BR>
         */
        private WEB3GentradeOnlineRunStatus onlineRunStatus;
        
        /**
         * (�o���I���ʒmTransactionCallback)�B<BR>
         * �R���X�g���N�^�B<BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h�B<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From����ID�B<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID�B<BR>
         * @@param l_onTransactionCallback - (�I�����C�����s����)<BR>
         * �I�����C�����s���ʁB<BR>
         */
        public ServiceTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            WEB3GentradeOnlineRunStatus l_onTransactionCallback)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
            onlineRunStatus = l_onTransactionCallback;
        }
        
        /**
         * (process)<BR>
         * �o���I���ʒm���������{����B<BR>
         * �V�[�P���X�}
         *  �u�i�o���I���ʒm�T�[�r�X�jprocess�v�Q�ƁB 
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40B1C28501F0
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "ServiceTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);

            String l_strStatus = WEB3RunStatusDivDef.DEALED;
            try
            {
                // set����J�����_�R���e�L�X�g()
                setTradingClendarContext();

                // get�����Ώیڋq�ꗗ()
                WEB3GentradeMainAccount[] l_accounts = getMainAccounts();
                
                // �擾�����ڋq�I�u�W�F�N�g�����ALoop
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                int l_intSize = 0;
                if (l_accounts != null)
                {
                    l_intSize = l_accounts.length;
                }
                for (int i = 0; i < l_intSize; i++)
                {
                    // ���ݓ������Z�b�g
                    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                    TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                    ThreadLocalSystemAttributesRegistry.setAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

                    java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
                    ThreadLocalSystemAttributesRegistry.setAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_processTime);

                    log.debug("����ID[" + i + "]:[" + l_accounts[i].getAccountId() + "]");
                    AccTransactionCallback l_transactionCallback =
                        new AccTransactionCallback(l_accounts[i]);
                    try
                    {
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                    catch (DataCallbackException l_dce)
                    {
                        log.error("�ڋq�P�ʏo���I���G���[���� :����ID[" + l_accounts[i].getAccountId() + "]");
                        WEB3BaseException l_wbe =
                            (WEB3BaseException)l_dce.getDetails();
                        log.error(l_wbe.getMessage(), l_wbe);
                        l_strStatus = WEB3RunStatusDivDef.ERROR;
                    }
                    catch (Exception l_exp)
                    {
                        // RuntimeException��throw���ꂽ�ꍇ�����s�X�e�[�^�X�敪���h9:�G���[�h
                        // �ɂ��Ȃ���΂Ȃ�Ȃ��̂ŁAException��catch����B
                        log.error("�ڋq�P�ʏo���I���G���[���� :����ID[" + l_accounts[i].getAccountId() + "]");
                        log.error(l_exp.getMessage(), l_exp);
                        l_strStatus = WEB3RunStatusDivDef.ERROR;
                    }
                }
            }
            catch (Exception l_exp)
            {
                // RuntimeException��throw���ꂽ�ꍇ�����s�X�e�[�^�X�敪���h9:�G���[�h
                // �ɂ��Ȃ���΂Ȃ�Ȃ��̂ŁAException��catch����B
                log.error(l_exp.getMessage(), l_exp);
                l_strStatus = WEB3RunStatusDivDef.ERROR;
            }

            // system_preference��shift.system.timestamp��
            // �I�����C�����s���ʃe�[�u��.�X�V���t�ɐݒ肷�邽�߂�
            // TIMESTAMP_TAG�����Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
            try
            {
                onlineRunStatus.updateRunStatusDiv(l_strStatus);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get�����Ώیڋq�ꗗ)<BR>
         * �L�����������ڋq�̈ꗗ���쐬���Ԃ��B<BR>
         * <BR>
         * �P�j�@@�s��ID�ꗗ���쐬����B<BR>
         * <BR>
         * �@@�@@�P�|�P�j�i���X�s��ʁj�戵����.get�i���X�s��ʁj�戵����( )���R�[�����āA<BR>
         * �@@�@@�@@�@@�@@�戵�����I�u�W�F�N�g�̔z����擾����B<BR>
         * <BR>
         * �@@�@@�@@�@@�@@��get�i���X�s��ʁj�戵����( )�ɃZ�b�g���������<BR>
         * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h:�@@�v���p�e�B�̏،���ЃR�[�h<BR>
         * <BR>
         * �@@�@@�P�|�Q�j�擾�����戵�����I�u�W�F�N�g�̑S�v�f�ɂ��Ĉȉ��̏������s���B<BR>
         * <BR>
         * �@@�@@�@@�@@�@@�i���X�s��ʁj�戵����.get�s��R�[�h( )�ɂ��s��R�[�h���擾����B<BR>
         * �@@�@@�@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��( )���R�[�����A<BR>
         * �@@�@@�@@�@@�@@�擾�����s��I�u�W�F�N�g�̎s��ID�����X�g�ɒǉ�����B<BR>
         * �@@�@@�@@�@@�@@���������A���Ɏs��ID�����X�g�ɑ��݂���ꍇ�͒ǉ����Ȃ��B<BR>
         * <BR>
         * �@@�@@�@@�@@�@@[get�s��( )�̈����ݒ�]<BR>
         * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h:�@@�v���p�e�B�̏،���ЃR�[�h<BR>
         * �@@�@@�@@�@@�@@�@@�s��R�[�h:�@@�i���X�s��ʁj�戵����.get�s��R�[�h( )<BR>
         * <BR>
         * �Q�j�@@���E�M�p�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
         * <BR>
         * �@@�@@�@@�N�G���v���Z�b�T���g�p���A�ȉ��̏����ɍ��v���钍���P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
         * �@@�@@�@@�i����ID�����w��j<BR>
         * <BR>
         * �@@�@@�@@----------------------------------------------------------<BR>
         * �@@�@@�@@�����o������<BR>
         * <BR>
         * �@@�@@�@@�@@�@@�@@���XID in �v���p�e�B�̏،���ЃR�[�h�ɊY������،����.getBranches( )�̖߂�l�̂����ꂩ<BR>
         * �@@�@@�@@���@@�����^�C�v == "����"<BR>
         * �@@�@@�@@���@@������� != �i"�����~�j��������"�A"�����~�j��������"�j<BR>
         * �@@�@@�@@���@@�����L����� == "�I�[�v��"<BR>
         * �@@�@@�@@���@@�v���p�e�B��From����ID �� ����ID<BR>
         * �@@�@@�@@���@@����ID �� �v���p�e�B��To����ID<BR>
         * �@@�@@�@@���@@������ �� ������ԊǗ�.get������( )(*1)<BR>
         * �@@�@@�@@���@@�s��ID in �P�j�Ŏ擾�����s��ID�ꗗ<BR>
         * <BR>
         * �@@�@@�@@��(*1)�����̏ꒆ�ɔ������������݂̂��擾���邽�߂̏����B<BR>
         * �@@�@@�@@���@@�@@�@@�����ȍ~���L���ȏo����܂Œ������擾����B�i��胁�[�����M�̂��߁j<BR>
         * �@@�@@�@@----------------------------------------------------------<BR>
         * <BR>
         * �R�j�@@�ڋq�I�u�W�F�N�g�̈ꗗ���쐬����B<BR>
         * <BR>
         * �@@�@@�@@�܂��A�Q�j�Ŏ擾���������P�ʃI�u�W�F�N�g.����ID�̈�ӂȈꗗ���쐬����B<BR>
         * �@@�@@�@@�e����ID���ɁA�Y������ڋq�I�u�W�F�N�g���擾���A<BR>
         * �@@�@@�@@�ԋp�p�̌ڋq�I�u�W�F�N�g�̈ꗗ�ɒǉ����Ă����B<BR>
         * <BR>
         * �S�j�@@�쐬�����ڋq�I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
         * �@@�@@�@@���Y���f�[�^�Ȃ�����null��ԋp����B<BR>
         * @@return WEB3GentradeMainAccount[]
         * @@throws WEB3BaseException
         */
        protected WEB3GentradeMainAccount[] getMainAccounts() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccounts()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //�P�|�P�j�i���X�s��ʁj�戵����.get�i���X�s��ʁj�戵����( )���R�[�����āA
            //�戵�����I�u�W�F�N�g�̔z����擾����B
            WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(this.institutionCode);

            //�P�|�Q�j�擾�����戵�����I�u�W�F�N�g�̑S�v�f�ɂ��Ĉȉ��̏������s���B
            Long[] l_marketIds = null;
            int l_intMarketSize = 0;
            if (l_handingCondBranchMarkets != null && l_handingCondBranchMarkets.length > 0)
            {
                l_intMarketSize = l_handingCondBranchMarkets.length;
                List l_lisMarketIds = new ArrayList();
                for (int i = 0; i < l_intMarketSize; i++)
                {
                    //�i���X�s��ʁj�戵����.get�s��R�[�h( )�ɂ��s��R�[�h���擾����B
                    String l_strMarkerCode = l_handingCondBranchMarkets[i].getMarketCode();

                    //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��( )���R�[����
                    //[get�s��( )�̈����ݒ�]
                    //�،���ЃR�[�h:�@@�v���p�e�B�̏،���ЃR�[�h
                    //�s��R�[�h:�@@�i���X�s��ʁj�戵����.get�s��R�[�h( )
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    WEB3GentradeMarket l_market = null;
                    try
                    {
                        l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            this.institutionCode,
                            l_strMarkerCode);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                        log.exiting(STR_METHOD_NAME);
                         throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //�������A���Ɏs��ID�����X�g�ɑ��݂���ꍇ�͒ǉ����Ȃ��B
                    if (!l_lisMarketIds.contains(new Long(l_market.getMarketId())))
                    {
                        l_lisMarketIds.add(new Long(l_market.getMarketId()));
                    }
                }

                l_marketIds = new Long[l_lisMarketIds.size()];
                l_lisMarketIds.toArray(l_marketIds);
            }

            WEB3GentradeMainAccount[] l_accounts = null;
        
            StringBuffer l_sbWhere = new StringBuffer();
            
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = null;
            try
            {
                l_institution = l_accountManager.getInstitution(institutionCode);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            Branch[] l_branchs = l_institution.getBranches();
            if (l_branchs != null && l_branchs.length > 0)
            {
                l_sbWhere.append("branch_id in (" + l_branchs[0].getBranchId());
                for (int i = 1;i < l_branchs.length;i++)
                {
                    l_sbWhere.append("," + l_branchs[i].getBranchId());
                }
                l_sbWhere.append(") and ");
            }
            l_sbWhere.append("product_type = ?");
            l_sbWhere.append(" and order_type not in (?,?)");
            l_sbWhere.append(" and order_open_status = ?");
            l_sbWhere.append(" and ? <= account_id");
            l_sbWhere.append(" and account_id <= ?");
            l_sbWhere.append(" and biz_date < ?");

            if (l_marketIds != null && l_marketIds.length > 0)
            {
                l_sbWhere.append(" and market_id in (" + l_marketIds[0]);
                for (int i = 1; i < l_marketIds.length; i++)
                {
                    l_sbWhere.append("," + l_marketIds[i]);
                }
                l_sbWhere.append(")");
            }

            String l_strBizDate =
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate());
            log.debug("������:[" + l_strBizDate + "]");
            Object[] l_objWhere =
            {
                ProductTypeEnum.EQUITY,
                OrderTypeEnum.MINI_STOCK_BUY,
                OrderTypeEnum.MINI_STOCK_SELL,
                OrderOpenStatusEnum.OPEN,
                new Long(rangeFrom),
                new Long(rangeTo),
                l_strBizDate
            };
        
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRecord =
                    l_queryProcessor.doFindAllQuery(
                        EqtypeOrderUnitRow.TYPE,
                        l_sbWhere.toString(),
                        "account_id asc",
                        null,
                        l_objWhere);
                if (l_lisRecord != null && !l_lisRecord.isEmpty())
                {
                    long l_accountId = 0L;
                    int l_intSize = l_lisRecord.size();
                    List l_lisAccount = new ArrayList();
                    for (int i = 0;i < l_intSize;i++)
                    {
                        EqtypeOrderUnitRow l_orderUnitRow =
                            (EqtypeOrderUnitRow)l_lisRecord.get(i);
                        if (l_accountId != l_orderUnitRow.getAccountId())
                        {
                            l_accountId = l_orderUnitRow.getAccountId();
                            WEB3GentradeMainAccount l_account =
                                new WEB3GentradeMainAccount(l_accountId);
                            l_lisAccount.add(l_account);
                        }
                    }
                    l_accounts = new WEB3GentradeMainAccount[l_lisAccount.size()];
                    l_lisAccount.toArray(l_accounts);
                }
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            log.exiting(STR_METHOD_NAME);
            return l_accounts;
        }
        
        /**
         * (set����J�����_�R���e�L�X�g)<BR>
         * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
         * <BR>
         * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
         * <BR>
         * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �v���p�e�B�̓�����<BR>
         * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �v���p�e�B�̏،���ЃR�[�h�ɊY������<BR>
         * �@@�@@�@@�،���ЃI�u�W�F�N�g.getBrahchs( )�̖߂�l��0�Ԗڂ̗v�f�̕��X�I�u�W�F�N�g.���X�R�[�h<BR>
         * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h�����h�i�Œ�j<BR>
         * �@@������ԃR���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
         * �@@������ԃR���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
         * <BR>
         * �@@��������t���i�A������t�g�����U�N�V�����͐ݒ�s�v�B<BR>
         * <BR>
         * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
         * �ݒ�L�[�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
         * <BR>
         * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
         * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
         * <BR>
         * @@throws WEB3BaseException
         */
        protected void setTradingClendarContext() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "setTradingClendarContext()";
            log.entering(STR_METHOD_NAME);
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            
            // �،���ЃR�[�h
            l_context.setInstitutionCode(institutionCode);
            
            // ���X�R�[�h
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = null;
            try
            {
                l_institution = l_accountManager.getInstitution(institutionCode);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            l_context.setBranchCode(l_institution.getBranches()[0].getBranchCode());
            
            // �s��R�[�h
            l_context.setMarketCode (WEB3MarketCodeDef.TOKYO);
            
            // ��t���ԋ敪
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            // �����R�[�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //��t�����A���t���[�����Z�b�g����
            WEB3GentradeTradingTimeManagement.setTimestamp();
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (�ڋq�P�ʏo���I��TransactionCallback)<BR>
         * �ڋq�P�ʏo���I��TransactionCallback�N���X�B<BR>
         */
        public class AccTransactionCallback
            implements TransactionCallback
        {
            /**
             * (�ڋq)�B<BR>
             * �ڋq�I�u�W�F�N�g�B<BR>
             */
            private WEB3GentradeMainAccount mainAccount;
            
            /**
             * (�ڋq�P�ʏo���I��TransactionCallback)<BR>
             * �R���X�g���N�^�B<BR>
             * @@param l_account - (�ڋq)<BR>
             * �ڋq�I�u�W�F�N�g�B<BR>
             */
            public AccTransactionCallback(WEB3GentradeMainAccount l_account)
            {
                mainAccount = l_account;
            }
            
            /**
             * (�ڋq�P�ʏo���I�����s)<BR>
             * �ڋq�P�ʂ̏o���I���������s���B<BR>
             * <BR>
             * �����̃V�[�P���X�́A<BR>
             * �V�[�P���X�}�u�i�o���I���ʒm�j�ڋq�P�ʏo���I�����s�v���Q�ƁB<BR>
             * @@return Object
             * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
             * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
             * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
             */
            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                final String STR_METHOD_NAME = "process()";
                log.entering(STR_METHOD_NAME);
                
                try
                {
                    // lock����()
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeAccountManager l_accountManager =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    MainAccountRow l_accountRow = (MainAccountRow)mainAccount.getDataSourceObject();
                    l_accountManager.lockAccount(
                        l_accountRow.getInstitutionCode(),
                        l_accountRow.getBranchCode(),
                        l_accountRow.getAccountCode());

                    // is�M�p�����J��()
                    boolean l_isMarginAccountEstablished =
                        mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                    
                    // getSubAccount()
                    WEB3GentradeSubAccount l_subAccount;
                    if (l_isMarginAccountEstablished)
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    else
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }

                    //(���X�s��ʁj�戵����.get�戵�\�s��(���X,  �����^�C�v)�̖߂�l�z����A
                    //�s��ID�ꗗ���쐬����B
                    //[get�戵�\�s��(���X,  �����^�C�v)�̈����ݒ�]
                    //�@@���X�F�@@�ڋq.getBranch( )�̖߂�l
                    //�@@�����^�C�v�F�@@"����"
                    String[] l_strHandlingPossibleMarkets =
                        WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                            (WEB3GentradeBranch)this.mainAccount.getBranch(),
                            ProductTypeEnum.EQUITY);

                    Long[] l_marketIds = null;
                    int l_intMarketSize = 0;
                    if (l_strHandlingPossibleMarkets != null && l_strHandlingPossibleMarkets.length > 0)
                    {
                        l_intMarketSize = l_strHandlingPossibleMarkets.length;
                        List l_lisMarketIds = new ArrayList();
                        for (int i = 0; i < l_intMarketSize; i++)
                        {
                            //get�s��( )
                            WEB3GentradeFinObjectManager l_finObjectManager =
                                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                            WEB3GentradeMarket l_market = null;
                            try
                            {
                                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                                    l_accountRow.getInstitutionCode(),
                                    l_strHandlingPossibleMarkets[i]);
                            }
                            catch (NotFoundException l_ex)
                            {
                                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                                log.exiting(STR_METHOD_NAME);
                                 throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                            }

                            //�������A���Ɏs��ID�����X�g�ɑ��݂���ꍇ�͒ǉ����Ȃ��B
                            if (!l_lisMarketIds.contains(new Long(l_market.getMarketId())))
                            {
                                l_lisMarketIds.add(new Long(l_market.getMarketId()));
                            }
                        }

                        l_marketIds = new Long[l_lisMarketIds.size()];
                        l_lisMarketIds.toArray(l_marketIds);
                    }

                    // get�����P�ʈꗗ()
                    StringBuffer l_sbWheres = new StringBuffer();
                    l_sbWheres.append("order_type not in (?,?)");
                    l_sbWheres.append(" and order_open_status = ?");
                    l_sbWheres.append(" and biz_date < ?");

                    if (l_marketIds != null && l_marketIds.length > 0)
                    {
                        l_sbWheres.append(" and market_id in (" + l_marketIds[0]);
                        for (int i = 1; i < l_marketIds.length; i++)
                        {
                            l_sbWheres.append("," + l_marketIds[i]);
                        }
                        l_sbWheres.append(")");
                    }

                    String l_strBizDate =
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                            WEB3GentradeTradingTimeManagement.getOrderBizDate());
                    Object[] l_objWhere =
                    {
                        OrderTypeEnum.MINI_STOCK_BUY,
                        OrderTypeEnum.MINI_STOCK_SELL,
                        OrderOpenStatusEnum.OPEN,
                        l_strBizDate
                    };
                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityOrderManager l_orderManager =
                        (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                    List l_lisOrderUnits = l_orderManager.getOrderUnits(
                        l_subAccount,
                        ProductTypeEnum.EQUITY,
                        l_sbWheres.toString(),
                        l_objWhere,
                        null);
                    
                    // �擾���������P�ʃI�u�W�F�N�g������Loop����
                    int l_intSize = 0;
                    if (l_lisOrderUnits != null)
                    {
                        l_intSize = l_lisOrderUnits.size();
                    }
                    WEB3EquityExecutedMailSenderService l_execMailSenderService =
                        (WEB3EquityExecutedMailSenderService)Services.getService(WEB3EquityExecutedMailSenderService.class);
                    for (int i = 0; i < l_intSize; i++)
                    {
                        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);
                        log.debug("�����P��ID[" + i + "]:[" + l_orderUnit.getOrderUnitId() + "]");
                        
                        // �����A�܂��͈ꕔ���̏ꍇ�̂ݎ��s
                        if (l_orderUnit.isUnexecuted() || l_orderUnit.isPartiallyExecuted())
                        {
                            l_execMailSenderService.sendMailProcess(l_orderUnit, null, true);
                        }
                        
                        if (l_orderManager.isReserveOrderConfirmRequire(l_orderUnit))
                        {
                            WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                                (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                                    WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                            l_updateService.expireAllOrderUnit(l_orderUnit.getOrderId());
                        }
                        
                        // �o���I���Ώۂł͂Ȃ��ꍇ�́A���̒����P�ʂ���������icontinue�j
                        if (!isOrderExecEnd(l_orderUnit))
                        {
                            log.debug("�o���I���Ώۂł͂Ȃ��B");
                            continue;
                        }
                        
                        // �X�V�C�x���g�C���^�Z�v�^()
                        WEB3MarginUpdateEventInterceptor l_interceptor =
                            new WEB3MarginUpdateEventInterceptor();
                        
                        // setThreadLocalPersistenceEventInterceptor()
                        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
                        
                        // expireOrder()
                        ProcessingResult l_result = l_orderManager.expireOrder(l_orderUnit.getOrderId());
                        if (l_result.isFailedResult())
                        {
                            throw new WEB3BaseException(
                                l_result.getErrorInfo(),
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }
                    }
                    
                    // �]�͍Čv�Z()
                    WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                        (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
                    l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                }
                catch (NotFoundException l_nfe)
                {
                    WEB3SystemLayerException l_wse =
                        new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                    throw new DataCallbackException(l_wse.getMessage(), l_wse);
                }
                catch (WEB3BaseException l_wbe)
                {
                    throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
                }
                
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            /**
             * (is�o���I�������P��)<BR>
             * �w��̒����P�ʃI�u�W�F�N�g���o���I���Ώۂ��ǂ����𔻒肷��B<BR> 
             * �i�����\�b�h�͎s��ǌ�ɃR�[�������B�j <BR>
             * <BR>
             * �����̒����P��.���������� < ������ԊǗ�.get������( )(*1) <BR>
             * �܂��́A�g�����������}�l�[�W��.is�������x������(����.�����P��) == true<BR> 
             * �܂��́A�����̒����P��.���N�G�X�g�^�C�v == "����" �@@�̏ꍇ�́A<BR> 
             * �o���I���Ώۂł���Ɣ��肵true��Ԃ��B <BR>
             * ��L�ȊO�̏ꍇ�́Afalse��Ԃ��B <BR>
             * <BR>
             * (*1)�����L�������������܂ł̒���<BR>
             * <BR>
             * @@param l_orderUnit - (�����P��)<BR>
             * �����P�ʃI�u�W�F�N�g�B<BR>
             * @@return �o���I���Ώۂ̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B
             */
            protected boolean isOrderExecEnd(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "isOrderExecEnd(EqTypeOrderUnit)";
                log.entering(STR_METHOD_NAME);
                
                Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                EqtypeOrderUnitRow l_row = 
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject(); 
                
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                
                log.exiting(STR_METHOD_NAME);
                if (l_datBizDate.compareTo(l_orderUnit.getExpirationTimestamp()) > 0 ||
                    l_orderManager.isNotOrderedDelayOrder(l_orderUnit) ||
                    WEB3RequestTypeDef.INVALIDATE.equals(l_row.getRequestType()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
}
@
