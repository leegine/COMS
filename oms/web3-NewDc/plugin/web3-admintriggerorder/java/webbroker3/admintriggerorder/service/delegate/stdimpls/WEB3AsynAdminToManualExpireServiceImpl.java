head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminToManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�񓯊��j�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl(WEB3AsynAdminToManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/17�@@�]�V�q(���u) �V�K�쐬
Revesion History : 2006/7/5 ���G�� (���u) �d�l�ύX���f��072
Revesion History : 2006/08/24 �юu�� (���u) �d�l�ύX���f��068,086,087,090,092,DB�X�V�d�l010,013,014,016,017
Revesion History : 2006/10/19 ������ (���u) ���f��095
Revesion History : 2006/11/28�@@����(���u) �d�l�ύX���f��No.116
Revesion History : 2006/11/20 ����(���u) ���f�� 098�A099�A108�i�X�V�d�l�jNo.018�A019�A020
Revesion History : 2006/11/30 ꎉ�(���u) ���f�� 113�A114�i�X�V�d�l�jNo.024
Revesion History : 2006/12/19�@@�����(���u) �d�l�ύX���f��No.124               
Revesion History : 2006/01/30�@@�֔�(���u) �d�l�ύX���f��No.126
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.WEB3EquityEstimatedPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�񓯊��j�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl)<BR>
 * �iWEB3AsynAdminToManualExpireServiceImpl�j<BR>
 * �i�񓯊��j�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�����N���X<BR>
 * <BR>
 * ����������񓯊��ōs���B<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AsynAdminToManualExpireServiceImpl implements Runnable
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynAdminToManualExpireServiceImpl.class);
    
    /**
     * (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminToManualLapseMainRequest request;
    
    /**
     * @@roseuid 441932780138
     */
    public WEB3AsynAdminToManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * (�i�񓯊��j�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl)<BR>
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * <BR>
     * ������this.���N�G�X�g�f�[�^�ɃZ�b�g����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@roseuid 440BCD74017F
     */
    public WEB3AsynAdminToManualExpireServiceImpl(WEB3AdminToManualLapseMainRequest l_request) 
    {
        this.request = l_request;
    }
    
    /**
     * �i�񓯊��j�蓮�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�񓯊��j�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jrun�v�Q�ƁB<BR>
     * @@roseuid 440BC62A020C
     */
    public void run()
    {
        final String STR_METHOD_NAME = " run()";
        log.entering(STR_METHOD_NAME);
        
        // �g���K�[�����Ǘ��ҁE�蓮�����f�[�����g���K�[TransactionCallback(long)
        WEB3AdminToManualExpireDaemonTriggerTransactionCallback l_daemonTriggerTransactionCallback =
            new WEB3AdminToManualExpireDaemonTriggerTransactionCallback(
                this.request.threadNo.longValue());
                    
        QueryProcessor l_queryProcessor = null;
        List l_lisDaemonTrigger = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDaemonTrigger = (List)l_queryProcessor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_daemonTriggerTransactionCallback);
                
            //null���ԋp���ꂽ�ꍇ�́A�������I������B
            if (l_lisDaemonTrigger == null)
            {
                log.debug("�g���K�[�����Ǘ��ҁE�蓮�����f�[�����g���K�[�̖߂�l��null�B");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        
        //1.1 �g���K�[�����Ǘ��ҁE�蓮�����I�����C�����s����TransactionCallback(
        //String, long, long)
        WEB3AdminToManualExpireOnlineRunStatusTransactionCallback l_runStatusTransactionCallback = 
            new WEB3AdminToManualExpireOnlineRunStatusTransactionCallback(
                this.request.institutionCode, 
                this.request.rangeFrom.longValue(), 
                this.request.rangeTo.longValue());
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
        
        try
        {
            //1.2 doTransaction(arg0 : int, arg1 : TransactionCallback)
            //�g�����U�N�V���������́ATX_CREATE_NEW�B
            //���X�V���e�𑼂̃v���Z�X����Q�Ɖ\�Ƃ���ׁB
            //�@@����I�����́A�I�����C�����s���ʃI�u�W�F�N�g��
            //�@@�ԋp�����B
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus) l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, 
                l_runStatusTransactionCallback);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        
        //���s�X�e�[�^�X�敪�F�@@"������"
        String l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        OrderUnit[] l_orderUnits = null;
        
        try
        {
            //1.3 get�蓮�����Ώے����P�ʈꗗ(Long, �،����, String[], String[],  
            //String[],String[], String, String, Long, Long)
            //(*)�����ݒ�d�l
            //����ID�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�،���ЁF�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h�ɊY������،����
            //���X�R�[�h�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //����������ʈꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //���i�敪�ꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�s��R�[�h�ꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�����R�[�h�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //����IDFrom�F�@@this.���N�G�X�g�f�[�^.From����ID
            //����IDTo�F�@@this.���N�G�X�g�f�[�^.To����ID
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(this.request.institutionCode);

            Long l_orderId = null;
            if (WEB3StringTypeUtility.isNotEmpty(this.request.lapseTargetOrderCondition.id))
            {
                l_orderId = Long.valueOf(this.request.lapseTargetOrderCondition.id);
            }
            
            l_orderUnits = WEB3AdminToDataManager.getManualExpireOrderUnits(
                l_orderId,
                l_institution,
                this.request.lapseTargetOrderCondition.branchCode,
                this.request.lapseTargetOrderCondition.triggerOrderTypeList,
                this.request.lapseTargetOrderCondition.productDivList,
                this.request.lapseTargetOrderCondition.marketList,
                this.request.lapseTargetOrderCondition.productCode,
                this.request.lapseTargetOrderCondition.accountCode,
                this.request.rangeFrom,
                this.request.rangeTo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        
        //1.4 (*)get�蓮�����Ώے����P�ʈꗗ()�̖߂�l�̗v�f�i=OrderUnit�j����Loop�����B
        //�߂�l��null�̏ꍇ��Loop�������Ȃ��B
        if (l_orderUnits != null)
        {
            log.debug("����ID�����W�iFrom�`To�j = [" + this.request.rangeFrom + "�`" + this.request.rangeTo + "]");
            log.debug("***** �蓮���������Ώۑ�����:[" + l_orderUnits.length + "] *****");
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                String l_strtradingTimeType = null;
                //Product l_product = l_orderUnits[i].getProduct();
                ProductTypeEnum l_productType = null;
                try
                {
                    //1.4.1 reset����J�����_�R���e�L�X�g(String, 
                    //ProductTypeEnum, Long, Long, Long, String)
                    if (l_orderUnits[i] instanceof EqTypeOrderUnit)
                    {
                        l_strtradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                        EqtypeOrderUnitRow l_eqTypeOrderUnitRow = 
                            (EqtypeOrderUnitRow) l_orderUnits[i].getDataSourceObject();
                        l_productType = l_eqTypeOrderUnitRow.getProductType();
                        WEB3AdminToDataManager.resetTradingCalContext(
                            this.request.institutionCode,
                            l_productType,
                            new Long(l_eqTypeOrderUnitRow.getBranchId()),
                            new Long(l_eqTypeOrderUnitRow.getMarketId()),
                            null,
                            l_strtradingTimeType);
                    }
                    else if (l_orderUnits[i] instanceof IfoOrderUnit)
                    {
                        l_strtradingTimeType = WEB3TradingTimeTypeDef.INDEX_FUTURE_OP;
                        IfoOrderUnitRow l_ifoOrderUnitRow = 
                            (IfoOrderUnitRow) l_orderUnits[i].getDataSourceObject();
                        l_productType = ((IfoOrderUnit) l_orderUnits[i]).getProductType();
                        WEB3AdminToDataManager.resetTradingCalContext(
                            this.request.institutionCode,
                            l_productType,
                            new Long(l_ifoOrderUnitRow.getBranchId()),
                            new Long(l_ifoOrderUnitRow.getMarketId()),
                            new Long(l_ifoOrderUnitRow.getProductId()),
                            l_strtradingTimeType);
                    }
                
                    log.debug("***** �蓮���������F" + (i + 1) + "���� *****");
                    log.debug("���������F[" + GtlUtils.getSystemTimestamp() + "]");
                    log.debug("����ID�F[" + l_orderUnits[i].getOrderId() + "]");
                    log.debug("����ID�F[" + l_orderUnits[i].getAccountId() + "]");
                    log.debug("������ʁF[" + l_orderUnits[i].getOrderType() + "]");
                    log.debug("�����^�C�v�F[" + l_productType + "]");
                    
                    //1.4.2 �g���K�[�����Ǘ��ҁE�蓮����TransactionCallback(
                    //ProductTypeEnum, OrderUnit)
                    WEB3AdminToManualExpireTransactionCallback l_transactionCallback = 
                        new WEB3AdminToManualExpireTransactionCallback(
                            l_productType, 
                            l_orderUnits[i]);

                    //1.4.3 doTransaction(arg0 : int, arg1 : 
                    //TransactionCallback)��O���X���[���ꂽ�ꍇ�́A
                    //����ID�Ɩ����^�C�v��ERROR�Ń��O�o�͂��A
                    //���̗v�f�֏������ڍs����B
                    l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallback);
                }
                catch (Exception l_ex)
                {
                    log.error("����ID:" + l_orderUnits[i].getOrderId());
                    log.error("�����^�C�v:" + l_productType);
                    l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
                    continue;
                }
            }
        }
        
        //1.5 (*)ThreadLocal��TIMESTAMP_TAG��null�Ń��Z�b�g����B
        //�e�e�[�u���̍X�V���t�Ɍ��ݎ������Z�b�g����ׁB
        //�����Z�b�g���Ȃ���΁A�Ō�Ɏ������������Ɠ����������g�p�����B
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        try
        {
            //1.6 update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)
            //(*)�����ݒ�d�l�i�擾�ς݂̃I�u�W�F�N�g�ɑ΂��ăR�[���B�j
            //���s�X�e�[�^�X�敪�F�@@"������"
            //�@@�����������ŗ�O���X���[���ꂽ�ꍇ�́A"�G���["�B
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
        
            //�r�����b�N�����f�[�����g���K�[�e�[�u���̃��R�[�h��"���ғ�"��update����B
            DaemonTriggerParams l_daemonTriggerParams =
                new DaemonTriggerParams((DaemonTriggerRow) l_lisDaemonTrigger.get(0));
            
            //������Ԃ�"���ғ�"��set����B
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            //���ݎ�����set����B
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getSystemTimestamp());
            
            WEB3DataAccessUtility.updateRow((DaemonTriggerRow)l_daemonTriggerParams);
        
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    new ErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataException l_ex)
        {

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                new ErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�g���K�[�����Ǘ��ҁE�蓮����TransactionCallback)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮����TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
     * <BR>
     */
    public class WEB3AdminToManualExpireTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (�����^�C�v)<BR>
         * �����^�C�v<BR>
         */
        public ProductTypeEnum productType;
        
        /**
         * (�����P��)<BR>
         * �����P�ʃI�u�W�F�N�g<BR>
         */
        public OrderUnit orderUnit;
        
        /**
         * @@roseuid 4419334C0128
         */
        public WEB3AdminToManualExpireTransactionCallback() 
        {
         
        }
        
        /**
         * (�g���K�[�����Ǘ��ҁE�蓮����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_productType - (�����^�C�v)<BR>
         * �����^�C�v<BR>
         * @@param l_orderUnit - (�����P��)<BR>
         * �����P�ʃI�u�W�F�N�g<BR>
         * @@roseuid 440C0FB90373
         */
        public WEB3AdminToManualExpireTransactionCallback(ProductTypeEnum l_productType, OrderUnit l_orderUnit) 
        {
            this.productType = l_productType;
            this.orderUnit = l_orderUnit;
        }
        
        /**
         * �蓮�����������s���B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�g���K�[�����Ǘ��ҁE�蓮����TransactionCallback�jprocess�v�Q�ƁB<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 440B949A0393
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            MainAccount l_mainAccount = null;
            try
            {
                //1.1 getMainAccount(arg0 : long)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_mainAccount = l_accountManager.getMainAccount(this.orderUnit.getAccountId());

                //1.2 lock����(�،���ЃR�[�h : String, ���X�R�[�h : String,
                //�����R�[�h : String)
                l_accountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.3 (*)(*)�s�ꑗ�M�ς�W�w�l�������A�s��J�ǒ���W�w�l�����̏ꍇ
            //�ithis.�����P��.�������� == "W�w�l"&& �s�ꂩ��m�F�ς݂̐��� != null
            //�@@&& ������ԊǗ�.is�s��J�ǎ��ԑ�() == true�j
            String l_strOrderConditionType = null;
            boolean l_dblConfirmedQuantityIsNull = false;
            if (ProductTypeEnum.EQUITY.equals(this.productType))
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)this.orderUnit.getDataSourceObject();
                l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
                l_dblConfirmedQuantityIsNull = l_orderUnitRow.getConfirmedQuantityIsNull();
            }
            else if (ProductTypeEnum.IFO.equals(this.productType))
            {
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)this.orderUnit.getDataSourceObject();
                l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
                l_dblConfirmedQuantityIsNull = l_orderUnitRow.getConfirmedQuantityIsNull();
            }
            boolean l_blnIsTradeOpenTimeZone = false;
            try
            {
                l_blnIsTradeOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType)
                && !l_dblConfirmedQuantityIsNull && l_blnIsTradeOpenTimeZone)
            {
                //1.3.1 invalidate�X�g�b�v����( )
                this.invalidateStopOrder();
            }
            else
            {
                //1.4 (*)��L�ȊO�̏ꍇ
                //1.4.1 invalidate����( )
                this.invalidateOrder();
            }

            try
            {
                //1.8 getSubAccount(arg0 : long, arg1 : long)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                SubAccount l_subAccount = l_accountManager.getSubAccount(
                    this.orderUnit.getAccountId(),
                    this.orderUnit.getSubAccountId());

                //1.9 (*)�؋��������ȊO�igetSubAccount()�̖߂�l.�⏕�����^�C�v !=
                //"�؋�������"�j�̏ꍇ
                if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
                {
                    //1.9.1 �]�͍Čv�Z(�⏕���� : �⏕����)
                    WEB3TPTradingPowerService l_tpTradingPowerService =
                        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                    l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getSubAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (invalidate����)<BR>
         * �������������i�����N���[�Y�j���s���B<BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�g���K�[�����Ǘ��ҁE�蓮����TransactionCallback�jinvalidate�����v�Q�ƁB<BR>
         * @@throws DataNetworkException
         * @@throws DataQueryException
         */
        public void invalidateOrder() throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = " invalidateOrder()";
            log.entering(STR_METHOD_NAME);

            //1.1) getMainAccount
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = l_accountManager.getMainAccount(this.orderUnit.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

            //1.2)(*)Eqtype�ithis.�����^�C�v == "����"�j�̏ꍇ
            if (ProductTypeEnum.EQUITY.equals(this.productType))
            {
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)this.orderUnit.getDataSourceObject();

                //1.2.1)is�L���[���R�[�h�m�F�v()
                //1.2.2)(*)is�L���[���R�[�h�m�F�v()�̖߂�l == true�̏ꍇ
                HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = null;
                if (isHostRecordConfirmRequire())
                {
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    try
                    {
                        //1.2.2.1 lock������������L���[
                        l_frontOrderService.lockHostEqtypeOrderAll((EqTypeOrderUnit)this.orderUnit);

                        //1.2.2.2 get������������L���[
                        l_hostEqtypeOrderAllParams =
                            l_frontOrderService.getHostEqtypeOrderAll((EqTypeOrderUnit)this.orderUnit);
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        log.error(l_ex.getErrorMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BaseRuntimeException(
                            l_ex.getErrorInfo(),
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //1.2.2.3 (*)�������̃L���[���R�[�h���擾�ł��Ȃ������ꍇ
                    if (l_hostEqtypeOrderAllParams == null)
                    {
                        log.info("�����x�������ɊY�����関�����̒����L���[���擾�ł��܂���ł����̂ŁA��������܂���B"
                            + "�����^�C�v:[" + this.productType + "] ����ID�F["
                            + this.orderUnit.getOrderId() + "] ���ʃR�[�h:[" + l_orderUnitRow.getOrderRequestNumber() + "]");
                        return;
                    }
                }

                //1.2.3)(*)���������ʒm�L���[Params�̃C���X�^���X�����A�y�уv���p�e�B�Z�b�g
                HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                    new HostEqtypeCloseOrderNotifyParams();

                //�f�[�^�R�[�h
                l_hostEqtypeCloseOrderNotifyParams.setRequestCode(
                    WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);

                //�،���ЃR�[�h
                l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());

                //���X�R�[�h
                l_hostEqtypeCloseOrderNotifyParams.setBranchCode(l_mainAccountRow.getBranchCode());

                //�����R�[�h
                l_hostEqtypeCloseOrderNotifyParams.setAccountCode(l_mainAccountRow.getAccountCode());

                //���҃R�[�h
                l_hostEqtypeCloseOrderNotifyParams.setTraderCode(null);

                //���ʃR�[�h
                l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber(
                    l_orderUnitRow.getOrderRequestNumber());

                //��萔��
                if (!l_orderUnitRow.getExecutedQuantityIsNull())
                {
                    l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(
                        l_orderUnitRow.getExecutedQuantity());
                }

                //�������R�R�[�h
                l_hostEqtypeCloseOrderNotifyParams.setReasonCode(
                    WEB3ExpirationReasonCodeDef.ORDER_CANCEL);

                //�����ʒm�敪
                l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType(
                    WEB3CloseNotifyTypeDef.CLOSE);

                //�G���[���b�Z�[�W
                l_hostEqtypeCloseOrderNotifyParams.setErrorMessage(
                    WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);

                //�����敪
                l_hostEqtypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.NOT_DEAL);

                WEB3EquityReceiveCloseOrderUnitService l_unitService =
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                        WEB3EquityReceiveCloseOrderUnitService.class);

                try
                {
                    //1.2.4) exec����()
                    l_unitService.execCloseOrder(
                        l_hostEqtypeCloseOrderNotifyParams,
                        (EqTypeOrderUnit)this.orderUnit);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //1.2.5 (*)������������L���[�e�[�u���̃��R�[�h���擾���Ă���ꍇ�́Aupdate���s���B
                //�蓮����_������������L���[�e�[�u��.xls
                if (l_hostEqtypeOrderAllParams != null)
                {
                    //��������ԍ�
                    l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                        l_hostEqtypeOrderAllParams.getOrderActionSerialNo() + 1);

                    //�����敪�i�X�e�[�^�X�j
                    l_hostEqtypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);

                    //�X�V���t
                    l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());

                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_hostEqtypeOrderAllParams);
                }
            }

            //1.3) (*)Ifo�ithis.�����^�C�v == "�敨�I�v�V����"�j�̏ꍇ
            else if (ProductTypeEnum.IFO.equals(this.productType))
            {
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)this.orderUnit.getDataSourceObject();

                //1.3.1 is�L���[���R�[�h�m�F�v()
                //1.3.2 (*)is�L���[���R�[�h�m�F�v()�̖߂�l == true�̏ꍇ
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
                if (isHostRecordConfirmRequire())
                {
                    WEB3IfoFrontOrderService l_ifoFrontOrderService =
                        (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                    try
                    {
                        //1.3.2.1 lock�敨OP��������L���[(�����P�� : IfoOrderUnit)
                        l_ifoFrontOrderService.lockHostFotypeOrderAll((IfoOrderUnit)this.orderUnit);

                        //1.3.2.2 get�敨OP��������L���[(�����P�� : IfoOrderUnit)
                        l_hostFotypeOrderAllParams =
                            l_ifoFrontOrderService.getHostFotypeOrderAll((IfoOrderUnit)this.orderUnit);
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        log.error(l_ex.getErrorMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BaseRuntimeException(
                            l_ex.getErrorInfo(),
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }


                    if (l_hostFotypeOrderAllParams == null)
                    {
                        log.info("�����x�������ɊY�����関�����̒�������L���[���擾�ł��܂���ł����̂ŁA��������܂���B"
                            + "�����^�C�v:[" + this.productType + "] ����ID�F["
                            + this.orderUnit.getOrderId() + "] ���ʃR�[�h:[" + l_orderUnitRow.getOrderRequestNumber() + "]");
                        return;
                    }

                }

                WEB3IfoCloseNotifyUnitService l_ifoCloseNotifyUnitService =
                    (WEB3IfoCloseNotifyUnitService)Services.getService(
                        WEB3IfoCloseNotifyUnitService.class);

                try
                {
                    //1.3.3)notify����()
                    l_ifoCloseNotifyUnitService.notifyClose(
                        this.orderUnit,
                        l_orderUnitRow.getExecutedAmount(),
                        WEB3ExpirationReasonCodeDef.ORDER_CANCEL,
                        WEB3CloseNotifyTypeDef.CLOSE);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_optionOrderMgr =
                    (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

                try
                {
                    IfoOrderUnit l_ifoOrderUnit =
                        (IfoOrderUnit)l_optionOrderMgr.getOrderUnit(
                             this.orderUnit.getOrderUnitId());

                    IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(
                        (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject());
                    l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);
                    l_ifoOrderUnit = (IfoOrderUnit)l_optionOrderMgr.toOrderUnit(l_ifoOrderUnitParams);

                    //1.3.4) update�����f�[�^()
                    l_optionOrderMgr.updateOrderData(l_ifoOrderUnit, false);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("error in l_ifoOrderUnit.getOrderUnit", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //1.3.5) (*)�敨OP��������L���[�e�[�u���̃��R�[�h���擾���Ă���ꍇ�́Aupdate���s���B
                if (l_hostFotypeOrderAllParams != null)
                {
                    // ��������L���[���R�[�h��update���s���B
                    // ��������ԍ�
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(
                            l_hostFotypeOrderAllParams.getOrderActionSerialNo() + 1);
                    // �����敪
                    l_hostFotypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                    
                    //�X�V���t
                    l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());

                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                }

                //1.3.6) reset����J�����_�R���e�L�X�g
                try
                {
                    WEB3AdminToDataManager.resetTradingCalContext(
                        l_mainAccountRow.getInstitutionCode(),
                        this.productType,
                        new Long(l_orderUnitRow.getBranchId()),
                        new Long(l_orderUnitRow.getMarketId()),
                        new Long(l_orderUnitRow.getProductId()),
                        WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (invalidate�X�g�b�v����)<BR>
         * �X�g�b�v�������������i���������N���A�j���s���B<BR>
         * <BR>
         * �V�[�P���X�} <BR>
         * �u�i�g���K�[�����Ǘ��ҁE�蓮����TransactionCallback�jinvalidate�X�g�b�v�����v�Q�ƁB<BR>
         */
        public void invalidateStopOrder()
        {
            final String STR_METHOD_NAME = " invalidateStopOrder()";
            log.entering(STR_METHOD_NAME);

            //1.1)getSubAccount()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = l_accountManager.getSubAccount(
                    this.orderUnit.getAccountId(),
                    this.orderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getSubAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.2)(*)Eqtype�ithis.�����^�C�v == "����"�j�̏ꍇ
            if (ProductTypeEnum.EQUITY.equals(this.productType))
            {
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_equityOrderMgr = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

                // notify���[���G���W���T�[�o
                try
                {
                    EqTypeOrderUnit l_eqtypeOrderUnit =
                        (EqTypeOrderUnit)l_equityOrderMgr.getOrderUnit(
                             this.orderUnit.getOrderUnitId());
                    l_equityOrderMgr.notifyRLS(l_eqtypeOrderUnit, OrderManagerPersistenceContext.ORDER_EXPIRED);

                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[", l_ex);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("error in l_eqtypeOrderUnit.getOrderUnit", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                try
                {
                    //get�X�g�b�v�����������T�Z����v�Z����
                    //[����] 
                    //�����P�ʁF�@@this.�����P�� 
                    //�⏕�����F�@@this.�����P��.getSubAccount
                    TradingModule l_tradingMod =
                        l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityOrderManager l_orderManager =
                        (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
                    
                    WEB3EquityEstimatedPrice l_estimatedPrice = 
                        l_orderManager.getStopOrderExpireEstimatedPrice(
                            (EqTypeOrderUnit)this.orderUnit, 
                            l_subAccount);
                    
                    EqTypeOrderUnit l_eqTypeOrderUnit = this.updateEqTypeOrderUnitRow(
                        (EqTypeOrderUnit)this.orderUnit,
                        l_estimatedPrice
                        );

                    //create��������(�����P�� : EqTypeOrderUnit, �C�x���g�^�C�v : OrderEventTypeEnum)
                    //[����] 
                    //�����P�ʁF�@@�N���[���Ƃ��Đݒ肳�ꂽ�����P�ʍX�V�l 
                    //�C�x���g�^�C�v�F�@@"92�F����"
                    EqTypeOrderAction l_eqTypeOrderAction =
                        l_orderManager.createOrderAction(
                            l_eqTypeOrderUnit, OrderEventTypeEnum.EXPIRE);
                    
                    //update�����f�[�^(�����P�� : EqTypeOrderUnit, �������� : EqTypeOrderAction)
                    //[����] 
                    //�����P�ʁF�@@�N���[���Ƃ��Đݒ肳�ꂽ�����P�ʍX�V�l 
                    //���������F�@@create��������()�̖߂�l
                    l_orderManager.updateOrderData(l_eqTypeOrderUnit, l_eqTypeOrderAction);
                }

                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }

            //1.3) (*)Ifo�ithis.�����^�C�v == "�敨�I�v�V����"�j�̏ꍇ
            else if (ProductTypeEnum.IFO.equals(this.productType))
            {
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_optionOrderMgr =
                    (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

                try
                {
                    // notify���[���G���W���T�[�o
                    l_optionOrderMgr.notifyRLS(
                        (IfoOrderUnit)this.orderUnit, OrderManagerPersistenceContext.ORDER_EXPIRED);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[", l_ex);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                IfoOrderUnitRow l_ifoOrderUnitRow =
                    (IfoOrderUnitRow)this.orderUnit.getDataSourceObject();

                //�敨OP�T�Z��n����v�Z����
                WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = null;

                TradingModule l_tradingMod =
                    l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                    (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

                try
                {
                    //1.3.2)(*)this.�����P��.������� == "OP�V�K��������"�̏ꍇ
                    if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitRow.getOrderType()))
                    {
                        //get�X�g�b�v�����������T�Z����v�Z����(�����P�� : IfoOrderUnit, �⏕���� : �⏕����)
                        //[����]
                        //�����P�ʁF�@@this.�����P��
                        //�⏕�����F�@@getSubAccount()�̖߂�l
                        l_ifoEstimateDeliveryAmountCalcResult =
                            l_optionOrderManagerImpl.getStopOrderExpireEstimatedPrice(
                                (IfoOrderUnit)this.orderUnit,
                                (WEB3GentradeSubAccount)l_subAccount);
                    }

                    IfoOrderUnit l_ifoOrderUnit = updateIfoOrderUnitRow(
                        (IfoOrderUnit)this.orderUnit,
                        l_ifoEstimateDeliveryAmountCalcResult);

                    //1.3.3) update�����f�[�^()
                    l_optionOrderManagerImpl.updateOrderData(l_ifoOrderUnit, true);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (is�L���[���R�[�h�m�F�v)<BR>
         * �L���[���R�[�h�̊m�F�i�����j���K�v�Ȓ������ǂ��� <BR>
         * ���ʂ���B<BR>
         * <BR>
         * �P�j�@@this.�����P�ʂ̌^��instanceof�ɂĔ��ʂ��A<BR>
         * �@@�ȉ��̂����ꂩ�ɃL���X�g����B<BR>
         * �@@�E���������P��<BR>
         * �@@�E�敨OP�����P��<BR>
         * <BR>
         *�@@�L���X�g��A�����P�ʂ��ȉ��̍��ڂ��擾����B<BR>
         * �@@�E�������� <BR>
         * �@@�E���N�G�X�g�^�C�v<BR>
         * �@@�E�s�ꂩ��m�F�ς̐���<BR>
         * <BR>
         * �Q�j�@@�L���[���R�[�h���m�F����K�v�����邩�ǂ������ʂ���B<BR>
         * <BR>
         * �@@[�������� == "�t�w�l"�̏ꍇ]<BR>
         * �@@[this.�����^�C�v == "����"�̏ꍇ]<BR>
         * �@@�@@���N�G�X�g�^�C�v == "�����T�[�o"�ł���΁A<BR>
         * �@@�@@true��ԋp����B�ȊO�Afalse��ԋp����B<BR>
         * �@@�@@����ԂɎ蓮�������ꂽ�ꍇ�̍l���B<BR>
         * <BR>
         * �@@[�������� == "W�w�l"�̏ꍇ]<BR>
         * �@@�@@�s�ꂩ��m�F�ς݂̐��� == null�ł���΁A<BR>
         * �@@�@@true��ԋp����B�ȊO�Afalse��ԋp����B<BR>
         * �@@�@@���s�ꖢ���M��W�w�l�����B<BR>
         * <BR>
         * �@@[��L�ȊO]<BR>
         * �@@�@@false��ԋp����B<BR>
         * <BR>
         * @@return boolean
         */
        private boolean isHostRecordConfirmRequire()
        {
            final String STR_METHOD_NAME = " isHostRecordConfirmRequire()";
            log.entering(STR_METHOD_NAME);

            //�P�j�@@this.�����P�ʂ̌^��instanceof�ɂĔ��ʂ��A
            //�ȉ��̂����ꂩ�ɃL���X�g����B
            //�@@�@@�E���������P��
            //�@@�@@�E�敨OP�����P��
            String l_strOrderConditionType = null;
            String l_strRequestType = null;         
            boolean l_blnConfirmedQuantityIsNull = false;
            if (this.orderUnit instanceof EqTypeOrderUnit)
            {
                EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)this.orderUnit;
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();

                //�@@�L���X�g��A�����P�ʂ��ȉ��̍��ڂ��擾����B
                //�@@�@@�E��������
                //�@@�@@�E���N�G�X�g�^�C�v
                //�@@�@@�E�s�ꂩ��m�F�ς̐���
                l_strOrderConditionType = l_eqtypeOrderUnitRow.getOrderConditionType();
                l_strRequestType = l_eqtypeOrderUnitRow.getRequestType();            
                l_blnConfirmedQuantityIsNull =
                    l_eqtypeOrderUnitRow.getConfirmedQuantityIsNull();
            }
            else if (this.orderUnit instanceof IfoOrderUnit)
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)this.orderUnit;
                IfoOrderUnitRow l_ifoOrderUnitRow =
                    (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

                //�@@�L���X�g��A�����P�ʂ��ȉ��̍��ڂ��擾����B
                //�@@�@@�E��������
                //�@@�@@�E���N�G�X�g�^�C�v
                //�@@�@@�E�s�ꂩ��m�F�ς̐���
                l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
                l_strRequestType = l_ifoOrderUnitRow.getRequestType();              
                l_blnConfirmedQuantityIsNull =
                    l_ifoOrderUnitRow.getConfirmedQuantityIsNull();
            }

            //�Q�j�@@�L���[���R�[�h���m�F����K�v�����邩�ǂ������ʂ���B
            //�@@[�������� == "�t�w�l"�̏ꍇ]
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                //���N�G�X�g�^�C�v == "�����T�[�o"�ł����
                //true��ԋp����B�ȊO�Afalse��ԋp����
                //����ԂɎ蓮�������ꂽ�ꍇ�̍l��
                if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            //�@@[�������� == "W�w�l"�̏ꍇ]
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                //�@@�@@�s�ꂩ��m�F�ς݂̐��� == null�ł���΁A
                //�@@�@@true��ԋp����B�ȊO�Afalse��ԋp����B
                //�@@�@@���s�ꖢ���M��W�w�l�����B
                if (l_blnConfirmedQuantityIsNull)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            //�@@[��L�ȊO]
            //�@@�@@false��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        /**
         * DB�X�V<BR>
         * �u�蓮����_���������P�ʃe�[�u��.xls�v<BR>
         * @@param l_orderUnit - (�����P��)<BR>
         * @@param l_estimatedDeliveryPrice - (�T�Z����v�Z����)<BR>
         * @@return EqTypeOrderUnit
         */
        private EqTypeOrderUnit updateEqTypeOrderUnitRow(
            EqTypeOrderUnit l_orderUnit,
            WEB3EquityEstimatedPrice l_estimatedDeliveryPrice
            )
        {
            final String STR_METHOD_NAME =
                "updateOrderUnitRow(EqTypeOrderUnit, WEB3EquityEstimatedPrice)";
            log.entering(STR_METHOD_NAME);

            EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                new EqtypeOrderUnitParams(l_eqTypeOrderUnitRow);

            //���������ŏI�ʔ�
            if (l_eqTypeOrderUnitParams.getLastOrderActionSerialNoIsSet())
            {
                int l_intLastOrderActionSerialNo = 
                    l_eqTypeOrderUnitParams.getLastOrderActionSerialNo();
                l_eqTypeOrderUnitParams.setLastOrderActionSerialNo(l_intLastOrderActionSerialNo + 1);
            }
            
            //��������
            String l_strOrderConditionType = l_eqTypeOrderUnitParams.getOrderConditionType();
            
            //����������
            l_eqTypeOrderUnitParams.setOrgOrderConditionType(l_strOrderConditionType);
            
            l_eqTypeOrderUnitParams.setOrderConditionType(
                WEB3OrderingConditionDef.DEFAULT);

            //�����������Z�q
            String l_strOrderCondOperator = l_eqTypeOrderUnitParams.getOrderCondOperator();
            
            //�������������Z�q
            l_eqTypeOrderUnitParams.setOrgOrderCondOperator(l_strOrderCondOperator);
            
            l_eqTypeOrderUnitParams.setOrderCondOperator(null);

            //���t�w�l��l
            if (l_eqTypeOrderUnitParams.getStopOrderPriceIsNull())
            {
                l_eqTypeOrderUnitParams.setOrgStopOrderPrice(null);
            }
            else
            {
                double l_dblStopOrderPrice = l_eqTypeOrderUnitParams.getStopOrderPrice();
                l_eqTypeOrderUnitParams.setOrgStopOrderPrice(l_dblStopOrderPrice);
            }

            //�t�w�l��l
            l_eqTypeOrderUnitParams.setStopOrderPrice(null);

            //���iW�w�l�j�����w�l
            if (l_eqTypeOrderUnitParams.getWLimitPriceIsNull())
            {
                l_eqTypeOrderUnitParams.setOrgWLimitPrice(null);
            }
            else
            {
                double l_dblWLimitPrice = l_eqTypeOrderUnitParams.getWLimitPrice();
                l_eqTypeOrderUnitParams.setOrgWLimitPrice(l_dblWLimitPrice);
            }
            
            //�iW�w�l�j�����w�l
            l_eqTypeOrderUnitParams.setWLimitPrice(null);
            
            //���iW�w�l�j���s����
            EqTypeExecutionConditionType l_exeType = l_eqTypeOrderUnitParams.getWLimitExecCondType(); 
            if (l_exeType == null)
            {
                l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(null);
            }
            else
            {
                l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(l_exeType);
            }
            
            //�iW�w�l�j���s����
            l_eqTypeOrderUnitParams.setWLimitExecCondType(null);

            if (l_estimatedDeliveryPrice != null)
            {
                //�����P��
                double l_dblCalcUnitPrice =
                    l_estimatedDeliveryPrice.getCalcUnitPrice();
                l_eqTypeOrderUnitParams.setPrice(l_dblCalcUnitPrice); 
                
                //�T�Z��n���
                double l_dblEstimateDeliveryAmount =
                    l_estimatedDeliveryPrice.getEstimateDeliveryAmount();
                l_eqTypeOrderUnitParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);
                
                //�s�ꂩ��m�F�ς݂̒����P��
                l_eqTypeOrderUnitParams.setConfirmedOrderPrice(l_dblCalcUnitPrice);

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_eqTypeOrderUnitParams.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);
            }

            //�����G���[���R�R�[�h
            l_eqTypeOrderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);

            //���N�G�X�g�^�C�v
            l_eqTypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

            //�X�V���t
            l_eqTypeOrderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeOrderManagerImpl l_orderManagerImpl =
                (EqTypeOrderManagerImpl)l_tradingModule.getOrderManager();

            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderManagerImpl.toOrderUnit(
                    l_eqTypeOrderUnitParams);

            log.exiting(STR_METHOD_NAME);
            return l_eqTypeOrderUnit;
        }

        /**
         * DB�X�V<BR>
         * �u�蓮����_�敨OP�����P�ʃe�[�u��.xls�v<BR>
         * @@param l_orderUnit - (�����P��)<BR>
         * @@param l_ifoEstimateDeliveryAmountCalcResult
         *  - (�敨OP�T�Z��n����v�Z����)<BR>
         * @@return IfoOrderUnit
         */
        private IfoOrderUnit updateIfoOrderUnitRow(
            IfoOrderUnit l_orderUnit,
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult)
        {
            final String STR_METHOD_NAME =
                "updateOrderUnitRow(IfoOrderUnit, WEB3IfoEstimateDeliveryAmountCalcResult)";
            log.entering(STR_METHOD_NAME);

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_ifoOrderUnitParams =
                new IfoOrderUnitParams(l_ifoOrderUnitRow);

            //���������ŏI�ʔ�
            l_ifoOrderUnitParams.setLastOrderActionSerialNo(
                l_ifoOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //��������
            String l_strOrderConditionType = l_ifoOrderUnitParams.getOrderConditionType();
            l_ifoOrderUnitParams.setOrderConditionType(
                WEB3OrderingConditionDef.DEFAULT);

            //�����������Z�q
            String l_strOrderCondOperator = l_ifoOrderUnitParams.getOrderCondOperator();
            l_ifoOrderUnitParams.setOrderCondOperator(null);

            //�t�w�l��l�^�C�v
            String l_strStopPriceType = l_ifoOrderUnitParams.getStopPriceType();
            l_ifoOrderUnitParams.setStopPriceType(null);

            //���t�w�l��l
            if (!l_ifoOrderUnitParams.getStopOrderPriceIsNull())
            {
                l_ifoOrderUnitParams.setOrgStopOrderPrice(l_ifoOrderUnitParams.getStopOrderPrice());
            }
            else
            {
                l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
            }

            //�t�w�l��l
            l_ifoOrderUnitParams.setStopOrderPrice(null);

            //���iW�w�l�j�����w�l
            if (!l_ifoOrderUnitParams.getWLimitPriceIsNull())
            {
                l_ifoOrderUnitParams.setOrgWLimitPrice(l_ifoOrderUnitParams.getWLimitPrice());
            }
            else
            {
                l_ifoOrderUnitParams.setOrgWLimitPrice(null);
            }

            //�iW�w�l�j�����w�l
            l_ifoOrderUnitParams.setWLimitPrice(null);

            //this.������� == "OP�V�K��������"�̏ꍇ
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnitParams.getOrderType()))
            {
                //�����P��
                double l_dblCalcUnitPrice =
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice();
                l_ifoOrderUnitParams.setPrice(l_dblCalcUnitPrice);

                //�T�Z��n���
                double l_dblEstimateDeliveryAmount =
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
                l_ifoOrderUnitParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

                //�s�ꂩ��m�F�ς݂̒����P��
                l_ifoOrderUnitParams.setConfirmedOrderPrice(l_dblCalcUnitPrice);

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_ifoOrderUnitParams.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);
            }

            //�����G���[���R�R�[�h
            l_ifoOrderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);

            //���N�G�X�g�^�C�v
            l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

            //�X�V���t
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());

            //����������
            l_ifoOrderUnitParams.setOrgOrderConditionType(l_strOrderConditionType);

            //�������������Z�q
            l_ifoOrderUnitParams.setOrgOrderCondOperator(l_strOrderCondOperator);

            //���t�w�l��l�^�C�v
            l_ifoOrderUnitParams.setOrgStopPriceType(l_strStopPriceType);

            //���iW�w�l�j���s����
            l_ifoOrderUnitParams.setOrgWLimitExecCondType(
                l_ifoOrderUnitParams.getWLimitExecCondType());

            //�iW�w�l�j���s����
            l_ifoOrderUnitParams.setWLimitExecCondType(null);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnit l_ifoOrderUnit =
                (IfoOrderUnit)l_optionOrderManagerImpl.toOrderUnit(
                    l_ifoOrderUnitParams);

            log.exiting(STR_METHOD_NAME);
            return l_ifoOrderUnit;

        }
    }

    /**
     * (�g���K�[�����Ǘ��ҁE�蓮�����I�����C�����s����TransactionCallback)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����I�����C�����s����TransactionCallback<BR>
     * <BR>
     * �蓮�����̏����J�n�^�I����<BR>
     * �I�����C�����s���ʃe�[�u���ɋL�^����B<BR>
     */
    public class WEB3AdminToManualExpireOnlineRunStatusTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (�،���ЃR�[�h)<BR>
         * �iinstitutionCode�j<BR>
         * �،���ЃR�[�h<BR>
         */
        public String institutionCode;
        
        /**
         * (From����ID)<BR>
         * �irangeFrom�j<BR>
         * From����ID<BR>
         */
        public long rangeFrom;
        
        /**
         * (To����ID)<BR>
         * �irangeTo�j<BR>
         * To����ID<BR>
         */
        public long rangeTo;
        
        /**
         * @@roseuid 44193394005D
         */
        public WEB3AdminToManualExpireOnlineRunStatusTransactionCallback() 
        {
         
        }
        
        /**
         * (�g���K�[�����Ǘ��ҁE�蓮�����I�����C�����s����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From����ID<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID<BR>
         * @@roseuid 440E825C00C3
         */
        public WEB3AdminToManualExpireOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode, 
            long l_lngRangeFrom, 
            long l_lngRangeTo) 
        {
            this.institutionCode = l_strInstitutionCode;
            this.rangeFrom = l_lngRangeFrom;
            this.rangeTo = l_lngRangeTo;
        }
        
        /**
         * �蓮���������J�n���I�����C�����s���ʃe�[�u���ɋL�^����B<BR>
         * <BR>
         * �P�j�@@�I�����C�����s����.set������()���\�b�h���R�[������B<BR>
         * �@@[set������()�ɃZ�b�g����p�����[�^]<BR>
         * �@@�@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
         * �@@�@@�����^�C�v�F�@@"���̑�"<BR>
         * �@@�@@�敨�^�I�v�V�����敪�F�@@"DEFAULT"<BR>
         * �@@�@@�I�����C���T�[�r�X�敪�F�@@"�蓮����"<BR>
         * �@@�@@From����ID�F�@@this.From����ID<BR>
         * �@@�@@To����ID�F�@@this.To����ID<BR>
         * <BR>
         * �Q�j�@@set������()�̖߂�l��ԋp����B<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 440E82F50111
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            //�P�j�@@�I�����C�����s����.set������()���\�b�h���R�[������B
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode, 
                    ProductTypeEnum.OTHER, 
                    WEB3FuturesOptionDivDef.DEFAULT, 
                    WEB3OnlineServiceDiv.MANUAL_EXPIRE, 
                    this.rangeFrom, 
                    this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            log.exiting(STR_METHOD_NAME);
            
            //�Q�j�@@set������()�̖߂�l��ԋp����B
            return l_onlineRunStatus;
        }
    }
    
    /**
     * (�g���K�[�����Ǘ��ҁE�蓮�����f�[�����g���K�[TransactionCallback)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����f�[�����g���K�[TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_JOIN_EXISTING�j<BR>
     * <BR>
     */
    public class WEB3AdminToManualExpireDaemonTriggerTransactionCallback implements TransactionCallback 
    {
        
        /**
         * �X���b�hNo<BR>
         */
        public long threadNo;
        
        public WEB3AdminToManualExpireDaemonTriggerTransactionCallback() 
        {
         
        }
        
        /**
         * (�g���K�[�����Ǘ��ҁE�蓮�����f�[�����g���K�[TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_lngThreadNo - (�X���b�hNo)<BR>
         * �X���b�hNo<BR>
         */
        public WEB3AdminToManualExpireDaemonTriggerTransactionCallback(
            long l_lngThreadNo) 
        {
            this.threadNo = l_lngThreadNo;
        }
        
        /**
         * this.�X���b�hNo�ɊY������f�[�����g���K�[�e�[�u����<BR>
         * ���R�[�h�����b�N����B<BR>
         * <BR>
         * �P�j�@@DB����<BR>
         * �@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����<BR>
         * �@@���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�@@�X���b�h�ԍ� = �p�����[�^.�X���b�hNo<BR>
         * <BR>
         * �Q�j�@@�������ʂ�ԋp����B<BR>
         * �@@���������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��<BR>
         * �@@��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            //�P�j�@@DB����
            //�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����
            //���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB
            List l_lisRows = null;

            try
            {
                String l_strWhere = " thread_no = ? ";
                String l_strCondition = " for update nowait ";
                Object l_bindVars[] = { new Long(this.threadNo) };
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    l_strCondition, 
                    l_bindVars);
            }
            catch (DataQueryException l_dataQueryException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataQueryException.getMessage(),
                    l_dataQueryException);
            }
            catch (DataNetworkException l_dataNetworkException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataNetworkException.getMessage(),
                    l_dataNetworkException);
            }
        
            // �������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��
            // ��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B
            if (l_lisRows.isEmpty())
            {
                log.error("�����X���b�h�̐�L���b�N�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
    }
}
@
