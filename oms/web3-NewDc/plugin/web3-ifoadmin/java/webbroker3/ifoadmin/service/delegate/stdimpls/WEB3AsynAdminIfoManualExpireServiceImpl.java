head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminIfoManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl(WEB3AsynAdminIfoManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
Revision History : 2007/04/05�@@�Ӑ�(���u) �yDEOS�z����e�X�g��Q�A���iU03004�j
*/

package webbroker3.ifoadmin.service.delegate.stdimpls;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl)<BR>
 *�iWEB3AsynAdminIfoManualExpireServiceImpl�j<BR>
 *�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�����N���X<BR>
 * <BR>
 * ����������񓯊��ōs���B<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */

public class WEB3AsynAdminIfoManualExpireServiceImpl implements Runnable
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AsynAdminIfoManualExpireServiceImpl.class);

    /**
     * (���N�G�X�g�f�[�^)<BR>
     * �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�������C�����N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminIfoManualLapseMainRequest request;

    /**
     * @@roseuid 447ACD830157
     */
    public WEB3AsynAdminIfoManualExpireServiceImpl()
    {
     
    }

    /**
     * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl)<BR>
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * <BR>
     * ������this.���N�G�X�g�f�[�^�ɃZ�b�g����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�������C�����N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AsynAdminIfoManualExpireServiceImpl
     * @@roseuid 44696851025E
     */
    public WEB3AsynAdminIfoManualExpireServiceImpl(WEB3AdminIfoManualLapseMainRequest l_request)
    {
        this.request = l_request;
    }

    /**
     * �i�񓯊��j�蓮�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�񓯊��j�Ǘ��Ґ敨OP�蓮�����T�[�r�X�jrun�v�Q�ƁB<BR>
     * @@roseuid 44693CE60111
     */
    public void run()
    {

        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        //���s�X�e�[�^�X�敪�F�@@"������"
        String l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;        
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;    
        List l_lisDaemonTrigger = null;
        try
        {
            // �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����f�[�����g���K�[TransactionCallback�𐶐�����B
            WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback l_triggerTransactionCallBack = 
                new WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback(request.threadNo.longValue());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �g�����U�N�V�������J�n����B
            //[����] 
            //arg0�F�@@TX_JOIN_EXISTING 
            //arg1�F�@@��������TransactionCallback
            l_lisDaemonTrigger = (List)l_queryProcessor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING, 
                l_triggerTransactionCallBack);
            
            //�g�����U�N�V���������́ATX_JOIN_EXISTING�B
            //�����R�[�h�̃��b�N��{�X���b�h���L���Ƃ���ׁBnull���ԋp���ꂽ�ꍇ�́A�������I������B
            if (l_lisDaemonTrigger == null)
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����I�����C�����s����TransactionCallback�𐶐�����B
            //[����] 
            //�،���ЃR�[�h�F�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h 
            //����IDFrom�F�@@this.���N�G�X�g�f�[�^.From����ID 
            //����IDTo�F�@@this.���N�G�X�g�f�[�^.To����ID
            WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback l_statusTransactionCallBack =
                new WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback(
                    request.institutionCode, request.accountIdFrom.longValue(), request.accountIdTo.longValue());
            
            // �g�����U�N�V�������J�n����B 
            l_onlineRunStatus = (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_statusTransactionCallBack);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataCallbackException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataCallbackException.getMessage(),
                l_dataCallbackException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        IfoOrderUnitRow[] l_orderUnitRows = null;
        try
        {
            WEB3GentradeInstitution l_institution =
                (WEB3GentradeInstitution)l_accountManager.getInstitution(this.request.institutionCode);

            // get����(�،����, String, String, String, String, String)
            WEB3IfoProductImpl l_ifoProductImpl = WEB3AdminIfoDataManager.getProduct(l_institution,
                request.ifoLapseTargetOrderCondition.fuOpDiv,
                request.ifoLapseTargetOrderCondition.targetProductCode,
                request.ifoLapseTargetOrderCondition.delivaryMonth,
                request.ifoLapseTargetOrderCondition.strikePrice,
                request.ifoLapseTargetOrderCondition.opProductType);

            String l_strProductId = null;
            if (l_ifoProductImpl != null) 
            {
                l_strProductId = Long.toString(l_ifoProductImpl.getProductId());
            }

            // �蓮�����ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B
            //(*)�����ݒ�d�l
            //�،���ЁF�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h�ɊY������،����
            //���X�R�[�h�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //����ID�F�@@get����()�̖̂߂�l.����ID�@@���������擾�ł����ꍇ
            //����敪�ꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //����IDFrom�F�@@this.���N�G�X�g�f�[�^.From����ID
            //����IDTo�F�@@this.���N�G�X�g�f�[�^.To����ID
            l_orderUnitRows = WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution,
                this.request.ifoLapseTargetOrderCondition.branchCode,
                l_strProductId,
                this.request.ifoLapseTargetOrderCondition.tradingTypeList,
                this.request.ifoLapseTargetOrderCondition.accountCode,
                this.request.accountIdFrom,
                this.request.accountIdTo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }

        // (*)get�蓮�����Ώے����P�ʈꗗ()�̖߂�l�̗v�f�i=IfoOrderUnitRow�j����Loop����
        if (l_orderUnitRows != null)
        {
            for (int i = 0; i < l_orderUnitRows.length; i++)
            {
                IfoOrderUnitRow l_ifoOrderUnitRow = l_orderUnitRows[i];

                try
                {
                    // ����J�����_�R���e�L�X�g�̒l���A�����P�ʂ̃f�[�^�ōX�V����B
                    //(*)�����ݒ�d�l
                    //�،���ЃR�[�h�F�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h
                    //���XID�F�@@�����Ώۂ̗v�f.���XID
                    //�s��ID�F�@@�����Ώۂ̗v�f.����ID
                    WEB3AdminIfoDataManager.resetTradingCalContext(
                        this.request.institutionCode,
                        new Long(l_ifoOrderUnitRow.getBranchId()),
                        new Long(l_ifoOrderUnitRow.getProductId()));

                    // �Ǘ��ҁE�敨OP�蓮����TransactionCallback�𐶐�����B 
                    WEB3AdminIfoManualExpireTransactionCallback l_expireTransactionCallback = 
                        new WEB3AdminIfoManualExpireTransactionCallback(l_ifoOrderUnitRow);

                    // �g�����U�N�V�������J�n����B
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_expireTransactionCallback);
                }
                //��O���X���[���ꂽ�ꍇ�́A����ID��ERROR�Ń��O�o�͂��A���̗v�f�֏������ڍs����B
                catch (Exception l_ex)
                {
                    log.error("����ID:" + l_ifoOrderUnitRow.getOrderId());
                    l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
                    continue;
                }
            }                                                             
        }
    
        // (*)ThreadLocal��TIMESTAMP_TAG��null�Ń��Z�b�g����B
        //�e�e�[�u���̍X�V���t�Ɍ��ݎ������Z�b�g����ׁB
        //�����Z�b�g���Ȃ���΁A�Ō�Ɏ������������Ɠ����������g�p�����B
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        try
        {   
            // update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)
            //(*)�����ݒ�d�l�i�擾�ς݂̃I�u�W�F�N�g�ɑ΂��ăR�[���B�j
            //���s�X�e�[�^�X�敪�F�@@"������"
            //�@@�����������ŗ�O���X���[���ꂽ�ꍇ�́A"�G���["�B
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
            
            // (*)�r�����b�N�����f�[�����g���K�[�e�[�u���̃��R�[�h��"���ғ�"��update����B
            DaemonTriggerParams l_daemonTriggerParams =
                new DaemonTriggerParams((DaemonTriggerRow) l_lisDaemonTrigger.get(0));
            //�X�V�d�l�́A�u�����蓮����_�f�[�����g���K�[�e�[�u��.xls�v��
            //�u�i�蓮�����j[�I����]�f�[�����g���K�[�e�[�u���v�V�[�g�Q�ƁB
            //������Ԃ�"���ғ�"��set����B
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            //���ݎ�����set����B
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getSystemTimestamp());
            
            WEB3DataAccessUtility.updateRow(l_daemonTriggerParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                new ErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮����TransactionCallback)<BR>
     * �i�񓯊��j�Ǘ��ҁE�敨OP�蓮����TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
     */
    public class WEB3AdminIfoManualExpireTransactionCallback implements TransactionCallback 
    {
        /**
         * (�����P��Row)<BR>
         * �����P��Row�I�u�W�F�N�g
         */
        public IfoOrderUnitRow ifoOrderUnitRow;
        
        /**
         * @@roseuid 447ACD830196
         */
        public WEB3AdminIfoManualExpireTransactionCallback() 
        {
         
        }
        
        /**
         * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
         * �����P��Row�I�u�W�F�N�g
         * @@return 
         * WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireTransactionCallback
         * @@roseuid 44693E570399
         */
        public WEB3AdminIfoManualExpireTransactionCallback(IfoOrderUnitRow l_ifoOrderUnitRow) 
        {
            this.ifoOrderUnitRow = l_ifoOrderUnitRow;
        }
        
        /**
         * �蓮�����������s���B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�Ǘ��Ҋ����蓮����TransactionCallback�jprocess�v�Q�ƁB<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 44693E5703B8
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
                //�ڋq���擾����B 
                //[����] 
                //arg0�F�@@this.�����P��Row.����ID
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(this.ifoOrderUnitRow.getAccountId());
                
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
                //���������b�N����B
                //[����] 
                //�،���ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h 
                //���X�R�[�h�F�@@getMainAccount()�̖߂�l.���X�R�[�h 
                //�����R�[�h�F�@@getMainAccount()�̖߂�l.�����R�[�h
                l_accountManager.lockAccount(
                    l_mainAccountRow.getInstitutionCode(), 
                    l_mainAccountRow.getBranchCode(), 
                    l_mainAccountRow.getAccountCode());
                
                // lock�敨OP��������L���[(IfoOrderUnit)
                WEB3IfoFrontOrderService l_ifoFrontOrderService = 
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                
                IfoOrderManagerImpl l_ifoOrderManagerImpl = 
                    (IfoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_ifoOrderManagerImpl.toOrderUnit(ifoOrderUnitRow);
                l_ifoFrontOrderService.lockHostFotypeOrderAll(l_ifoOrderUnit);
                
                // get�敨OP��������L���[(IfoOrderUnit)
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = 
                    l_ifoFrontOrderService.getHostFotypeOrderAll(l_ifoOrderUnit);
                
                // (*)�L���[���R�[�h���擾�ł��Ȃ������ꍇ
                if (l_hostFotypeOrderAllParams == null )
                {
                    // (*)"�Y���L���[���R�[�h�Ȃ�"�̕����ƒ���ID���f�o�b�O���O�ɏo�͂��A�������I������B
                    log.debug("�Y���L���[���R�[�h�Ȃ�");
                    log.debug("����ID = " + this.ifoOrderUnitRow.getOrderId());
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                
                //(*) notify����(OrderUnit, double, String, String)
                WEB3IfoCloseNotifyUnitService l_ifoCloseNotifyUnitService = 
                    (WEB3IfoCloseNotifyUnitService)Services.getService(WEB3IfoCloseNotifyUnitService.class);
                l_ifoCloseNotifyUnitService.notifyClose(l_ifoOrderUnit,
                    l_ifoOrderUnit.getExecutedQuantity(),
                    WEB3ExpirationReasonCodeDef.ORDER_CANCEL,
                    WEB3CloseNotifyTypeDef.CLOSE);
                
                // reset����J�����_�R���e�L�X�g(String, Long, Long)
                WEB3AdminIfoDataManager.resetTradingCalContext(l_mainAccount.getInstitution().getInstitutionCode(),
                    new Long(ifoOrderUnitRow.getBranchId()),
                    new Long(ifoOrderUnitRow.getProductId()));

                // update�����f�[�^(IfoOrderUnit, boolean)
                l_ifoOrderUnit = (IfoOrderUnit)l_ifoOrderManagerImpl.getOrderUnit(ifoOrderUnitRow.getOrderUnitId());

                IfoOrderUnitRow l_ifoOrderUnitRowCopy = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
                IfoOrderUnitParams l_ifoOrderUnitParamsCopy = new IfoOrderUnitParams(l_ifoOrderUnitRowCopy);
                l_ifoOrderUnitParamsCopy.setErrorReasonCode(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED);

                IfoOrderUnit l_ifoOrderUnitCopy = (IfoOrderUnit)l_ifoOrderManagerImpl.toOrderUnit(l_ifoOrderUnitParamsCopy);

                WEB3OptionOrderManagerImpl l_orderManager = 
                    (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager(); 
                l_orderManager.updateOrderData(l_ifoOrderUnitCopy, false);
                
                // getSubAccount(arg0 : long, arg1 : long)
                //�⏕�������擾����
                WEB3GentradeSubAccount l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        ifoOrderUnitRow.getAccountId(), 
                        ifoOrderUnitRow.getSubAccountId());
                
                // (*)�؋��������ȊO�igetSubAccount()�̖߂�l.�⏕�����^�C�v != "�؋�������"�j�̏ꍇ
                SubAccountTypeEnum l_subAccountTypeEnum = l_subAccount.getSubAccountType();
                if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountTypeEnum)) 
                {
                    //�]�͍Čv�Z(�⏕���� : �⏕����)
                    WEB3TPTradingPowerService l_tpTradingPowerService =
                        (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                    l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
                }
                
                //(*) �擾����OP��������L���[��update����B
                //�X�V���e�ɂ��ẮADB�X�V�d�l
                //�u�敨OP�蓮����_�敨OP��������L���[�e�[�u��.xls�v
                //�Q�ƁB
                QueryProcessor l_queryProcessor = null;
                l_queryProcessor = Processors.getDefaultProcessor();
                l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_hostFotypeOrderAllParams.getOrderActionSerialNo() + 1);
                l_hostFotypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_queryProcessor.doUpdateQuery(l_hostFotypeOrderAllParams);
            }
            catch (NotFoundException l_dqex)
            {

                //��O���X���[����
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (WEB3BaseException l_dqex)
            {
                //��O���X���[����
                log.error( getClass().getName() + "." + STR_METHOD_NAME ,l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_dqex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����I�����C�����s����TransactionCallback)<BR>
     * �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����I�����C�����s����TransactionCallback<BR>
     * <BR>
     * �蓮�����̏����J�n�^�I����<BR>
     * �I�����C�����s���ʃe�[�u���ɋL�^����B<BR>
     */
    public class WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h
         */
        public String institutionCode;
        
        /**
         * (From����ID)<BR>
         * From����ID
         */
        public long accountIdFrom;
        
        /**
         * (To����ID)<BR>
         * To����ID
         */
        public long accountIdTo;
        
        /**
         * @@roseuid 447ACD8301E4
         */
        public WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback() 
        {
         
        }
        
        /**
         * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����I�����C�����s����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h
         * @@param l_lngAccountIdFrom - (From����ID)<BR>
         * From����ID
         * @@param l_lngAccountIdTo - (To����ID)<BR>
         * To����ID
         * @@return 
         * WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback
         * @@roseuid 44693F0300BA
         */
        public WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode, 
            long l_lngAccountIdFrom, 
            long l_lngAccountIdTo) 
        {
            this.institutionCode = l_strInstitutionCode;
            this.accountIdFrom = l_lngAccountIdFrom;
            this.accountIdTo = l_lngAccountIdTo;
        }
        
        /**
         * �I�����C�����s���ʃe�[�u���̊Y�����R�[�h��<BR>
         * "������"��update����B<BR>
         * <BR>
         * �P�j�@@�I�����C�����s����.set������()���\�b�h���R�[������B<BR>
         * �@@[set������()�ɃZ�b�g����p�����[�^]<BR>
         * �@@�@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
         * �@@�@@�����^�C�v�F�@@"�敨OP"<BR>
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
         * @@roseuid 44693F0300D9
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
                    ProductTypeEnum.IFO, 
                    WEB3FuturesOptionDivDef.DEFAULT, 
                    WEB3OnlineServiceDiv.MANUAL_EXPIRE, 
                    this.accountIdFrom, 
                    this.accountIdTo);
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
                        
            //�Q�j�@@set������()�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
    
    /**
     * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����f�[�����g���K�[TransactionCallback)<BR>
     * �i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����f�[�����g���K�[TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_JOIN_EXISTING�j<BR>
     */
    public class WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (�X���b�hNo)<BR>
         * �X���b�hNo
         */
        public long threadNo;
        
        /**
         * @@roseuid 447ACD830222
         */
        public WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback() 
        {
         
        }
        
        /**
         * (�i�񓯊��j�Ǘ��ҁE�敨OP�蓮�����f�[�����g���K�[TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_lngThreadNo - (�X���b�hNo)<BR>
         * �X���b�hNo
         * @@return 
         * WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireD
         * aemonTriggerTransactionCallback
         * @@roseuid 44744A1901B1
         */
        public WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback(long l_lngThreadNo) 
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
         * �@@�@@�X���b�h�ԍ� = this.�X���b�hNo<BR>
         * <BR>
         * �Q�j�@@�������ʂ�ԋp����B<BR>
         * �@@���������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��<BR>
         * �@@��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B<BR>
         * @@return java.lang.Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 44744A860106
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            //�P�j�@@DB����
            //�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����
            //���R�[�h���A"for update nowait"�I�v�V�����œǂݍ��ށB
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            String l_strWhere = " thread_no = ? ";
            String l_strCondition = " for update nowait ";
            Object l_bindVars[] = { new Long(threadNo) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                DaemonTriggerRow.TYPE,
                l_strWhere,
                l_strCondition, 
                l_bindVars);
            //�Q�j�@@�������ʂ�ԋp����B
            //�������ʂ��擾�ł��Ȃ������ꍇ�A�����X���b�h��<BR>
            // ��L���b�N�Ɏ��s�����|��ERROR�Ń��O�ɏo�͂��Anull��ԋp����B
            if (l_lisRows.isEmpty())
            {
                log.error("�����X���b�h�̐�L���b�N�Ɏ��s����");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            else
            {            
                log.exiting(STR_METHOD_NAME);
                return l_lisRows;
            }

        }
    }
}
@
