head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminEquityManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl(WEB3AsynAdminEquityManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/30 ������ (���u) �V�K�쐬
                   2006/06/08�@@�s�p(���u) �d�l�ύX ���f��109
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.HostEqtypeSwapParams;
import webbroker3.equity.data.HostEqtypeSwapRow;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl)<BR>
 *�iWEB3AsynAdminEquityManualExpireServiceImpl�j<BR>
 *�i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�X�����N���X<BR>
 * <BR>
 * ����������񓯊��ōs���B<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */

public class WEB3AsynAdminEquityManualExpireServiceImpl implements Runnable 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AsynAdminEquityManualExpireServiceImpl.class);
     
    /**
     * (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮�������C�����N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminEquityManualLapseMainRequest request;
    
    /**
     * @@roseuid 447ACD830157
     */
    public WEB3AsynAdminEquityManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * (�i�񓯊��j�Ǘ��ҁE�����蓮�����T�[�r�XImpl)<BR>
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * <BR>
     * ������this.���N�G�X�g�f�[�^�ɃZ�b�g����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮�������C�����N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AsynAdminEquityManualExpireServiceImpl
     * @@roseuid 44696851025E
     */
    public WEB3AsynAdminEquityManualExpireServiceImpl(WEB3AdminEquityManualLapseMainRequest l_request) 
    {
        this.request = l_request;
    }
    
    /**
     * �i�񓯊��j�蓮�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�񓯊��j�Ǘ��Ҋ����蓮�����T�[�r�X�jrun�v�Q�ƁB<BR>
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
            //1.1�Ǘ��ҁE�����蓮�����f�[�����g���K�[TransactionCallback�𐶐�����B 
            WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback l_triggerTransactionCallBack 
                = new WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback(request.threadNo.longValue()); 
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 
            
            //1.2�g�����U�N�V�������J�n����B
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

            //1.3�Ǘ��ҁE�����蓮�����I�����C�����s����TransactionCallback�𐶐�����B
            //[����] 
            //�،���ЃR�[�h�F�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h 
            //����IDFrom�F�@@this.���N�G�X�g�f�[�^.From����ID 
            //����IDTo�F�@@this.���N�G�X�g�f�[�^.To����ID
            WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback l_statusTransactionCallBack = 
                new WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback(
                    request.institutionCode, request.accountIdFrom.longValue(), request.accountIdTo.longValue());
            
            //1.4�g�����U�N�V�������J�n����B 
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
           
        EqtypeOrderUnitRow[] l_orderUnitRows = null;           
        try
        {
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution)l_accountManager.getInstitution(this.request.institutionCode);
           
            //1.5�蓮�����ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B
            //(*)�����ݒ�d�l
            //�،���ЁF�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h�ɊY������،����
            //���X�R�[�h�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�����R�[�h�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�s��R�[�h�ꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //����敪�ꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�ٍϋ敪�ꗗ�F�@@this.���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //�ڋq�R�[�h�F�@@���N�G�X�g�f�[�^.�����Ώے��������̓�������
            //����IDFrom�F�@@this.���N�G�X�g�f�[�^.From����ID
            //����IDTo�F�@@this.���N�G�X�g�f�[�^.To����ID
            l_orderUnitRows = WEB3AdminPMEquityDataManager.getManualExpireOrderUnits(
                l_institution,
                this.request.equityLapseTargetOrderCondition.branchCode,
                this.request.equityLapseTargetOrderCondition.productCode,
                this.request.equityLapseTargetOrderCondition.marketList,
                this.request.equityLapseTargetOrderCondition.tradingTypeList,
                this.request.equityLapseTargetOrderCondition.repaymentList,
                this.request.equityLapseTargetOrderCondition.accountCode,
                this.request.accountIdFrom,
                this.request.accountIdTo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
       
        //1.6(*)get�蓮�����Ώے����P�ʈꗗ()�̖߂�l�̗v�f�i=EqtypeOrderUnitRow�j����Loop����
        if (l_orderUnitRows != null)
        {
            for (int i = 0; i < l_orderUnitRows.length; i++)
            {
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow = l_orderUnitRows[i]; 
                String l_strtradingTimeType = null;
               
                if (OrderCategEnum.SWAP_MARGIN.equals(l_eqtypeOrderUnitRow.getOrderCateg()))
                {
                    l_strtradingTimeType = WEB3TradingTimeTypeDef.SWAP;
                }
                else
                {
                    l_strtradingTimeType = WEB3TradingTimeTypeDef.EQUITY;
                }
                                                        
                try
                {
                    //1.6.1����J�����_�R���e�L�X�g�̒l���A�����P�ʂ̃f�[�^�ōX�V����B 
                    //(*)�����ݒ�d�l
                    //�،���ЃR�[�h�F�@@this.���N�G�X�g�f�[�^.�،���ЃR�[�h
                    //���XID�F�@@�����Ώۂ̗v�f.���XID
                    //�s��ID�F�@@�����Ώۂ̗v�f.�s��ID
                    //��t���ԋ敪�F�@@
                    //�@@[�����Ώۂ̗v�f.�����J�e�S�� == "�������n����"�̏ꍇ]
                    //�@@�@@"�����E���n"���Z�b�g�B
                    //�@@[��L�ȊO]
                    //�@@�@@"�����E�M�p"���Z�b�g�B 
                    WEB3AdminPMEquityDataManager.resetTradingCalContext(
                        this.request.institutionCode,
                        new Long(l_eqtypeOrderUnitRow.getBranchId()),
                        new Long(l_eqtypeOrderUnitRow.getMarketId()),
                        l_strtradingTimeType);
                   
                    //1.6.2�Ǘ��ҁE�����蓮����TransactionCallback�𐶐�����B
                    WEB3AdminEquityManualExpireTransactionCallback l_expireTransactionCallback = 
                        new WEB3AdminEquityManualExpireTransactionCallback(l_eqtypeOrderUnitRow);
                   
                    //1.6.3�g�����U�N�V�������J�n����B
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 
                    l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_expireTransactionCallback);
                }
                //��O���X���[���ꂽ�ꍇ�́A����ID��ERROR�Ń��O�o�͂��A���̗v�f�֏������ڍs����B
                catch (Exception l_ex)
                {
                    log.error("����ID:" + l_eqtypeOrderUnitRow.getOrderId());
                    l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
                    continue;
                }
            }                                                             
        }
    
        //1.7 (*)ThreadLocal��TIMESTAMP_TAG��null�Ń��Z�b�g����B
        //�e�e�[�u���̍X�V���t�Ɍ��ݎ������Z�b�g����ׁB
        //�����Z�b�g���Ȃ���΁A�Ō�Ɏ������������Ɠ����������g�p�����B
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        try
        {   
            //1.8 update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)
            //(*)�����ݒ�d�l�i�擾�ς݂̃I�u�W�F�N�g�ɑ΂��ăR�[���B�j
            //���s�X�e�[�^�X�敪�F�@@"������"
            //�@@�����������ŗ�O���X���[���ꂽ�ꍇ�́A"�G���["�B
            l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
            
            //1.9 (*)�r�����b�N�����f�[�����g���K�[�e�[�u���̃��R�[�h��"���ғ�"��update����B
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
     * (�Ǘ��ҁE�����蓮����TransactionCallback)<BR>
     * �Ǘ��ҁE�����蓮����TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
     */
    public class WEB3AdminEquityManualExpireTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (�����P��Row)<BR>
         * �����P��Row�I�u�W�F�N�g
         */
        public EqtypeOrderUnitRow eqtypeOrderUnitRow;
        
        /**
         * @@roseuid 447ACD830196
         */
        public WEB3AdminEquityManualExpireTransactionCallback() 
        {
         
        }
        
        /**
         * (�Ǘ��ҁE�����蓮����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
         * �����P��Row�I�u�W�F�N�g
         * @@return 
         * WEB3AsynAdminEquityManualExpireServiceImpl.WEB3AdminEquityManualExpireTransactionCallback
         * @@roseuid 44693E570399
         */
        public WEB3AdminEquityManualExpireTransactionCallback(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) 
        {
            this.eqtypeOrderUnitRow = l_eqtypeOrderUnitRow;
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
                
                //1.1�ڋq���擾����B 
                //[����] 
                //arg0�F�@@this.�����P��Row.����ID
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(this.eqtypeOrderUnitRow.getAccountId());
                
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
                //1.2���������b�N����B
                //[����] 
                //�،���ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h 
                //���X�R�[�h�F�@@getMainAccount()�̖߂�l.���X�R�[�h 
                //�����R�[�h�F�@@getMainAccount()�̖߂�l.�����R�[�h
                l_accountManager.lockAccount(
                    l_mainAccountRow.getInstitutionCode(), 
                    l_mainAccountRow.getBranchCode(), 
                    l_mainAccountRow.getAccountCode());
                
                List l_listRecords = new ArrayList();
                
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append("institution_code = ? ");
                l_sbWhere.append("and branch_code = ? ");
                l_sbWhere.append("and account_code = ? ");
                l_sbWhere.append("and order_request_number = ? ");
                l_sbWhere.append("and status= ?");
                
                Object[] l_objWhere = {
                    l_mainAccountRow.getInstitutionCode(), 
                    l_mainAccountRow.getBranchCode(), 
                    l_mainAccountRow.getAccountCode(),
                    this.eqtypeOrderUnitRow.getOrderRequestNumber(),
                    WEB3StatusDef.NOT_DEAL};
                
                String l_strCondition = " for update nowait ";
                
                QueryProcessor l_queryProcessor = null;
                l_queryProcessor = Processors.getDefaultProcessor();
                
                //1.3�������n�����ithis.�����P��Row.�����J�e�S�� == "�������n����"�j�̏ꍇ
                if (OrderCategEnum.SWAP_MARGIN.equals(this.eqtypeOrderUnitRow.getOrderCateg()))
                {
                    //1.3.1(*)�������n�L���[�e�[�u���̊Y�����R�[�h�����b�N����B
                    //(*)�ȉ��̏����ɂČ������n�L���[�e�[�u����
                    //�s���b�N�I�v�V�����i"for update nowait"�j�œǂݍ��ށB
                    //�،���ЃR�[�h�@@���@@getMainAccount()�̖߂�l.�،���ЃR�[�h
                    //���X�R�[�h�@@���@@getMainAccount()�̖߂�l.���X�R�[�h
                    //�����R�[�h�@@���@@getMainAccount()�̖߂�l.�����R�[�h
                    //���ʃR�[�h�@@���@@this.�����P��Row.���ʃR�[�h
                    //�����敪    �@@���@@"������"                    
                    l_listRecords = l_queryProcessor.doFindAllQuery(
                        HostEqtypeSwapRow.TYPE, l_sbWhere.toString(), l_strCondition, l_objWhere);
                }
                //1.4(*)��L�ȊO�̏ꍇ
                else
                {
                    //1.4.1(*)������������L���[�e�[�u���̊Y�����R�[�h�����b�N����B
                    //(*)�ȉ��̏����ɂĊ�����������L���[�e�[�u����
                    //�s���b�N�I�v�V�����i"for update nowait"�j�œǂݍ��ށB
                    //�،���ЃR�[�h�@@���@@getMainAccount()�̖߂�l.�،���ЃR�[�h
                    //���X�R�[�h�@@���@@getMainAccount()�̖߂�l.���X�R�[�h
                    //�����R�[�h�@@���@@getMainAccount()�̖߂�l.�����R�[�h
                    //���ʃR�[�h�@@���@@this.�����P��Row.���ʃR�[�h
                    //�����敪�i�X�e�[�^�X�j�@@���@@"������"
                    l_listRecords = l_queryProcessor.doFindAllQuery(
                        HostEqtypeOrderAllRow.TYPE, l_sbWhere.toString(), l_strCondition, l_objWhere);
                }
                
                //1.5(*)�L���[���R�[�h���擾�ł��Ȃ������ꍇ
                if (l_listRecords.isEmpty())
                {
                    //1.5.1(*)"�Y���L���[���R�[�h�Ȃ�"�̕����ƒ���ID���f�o�b�O���O�ɏo�͂��A�������I������B
                    log.debug("�Y���L���[���R�[�h�Ȃ�");
                    log.debug("����ID = " + this.eqtypeOrderUnitRow.getOrderId());
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                
                //(*)1.6���������ʒm�L���[Params�𐶐�����B
                HostEqtypeCloseOrderNotifyParams l_orderNotityParams = new HostEqtypeCloseOrderNotifyParams();
                //(*)���������ʒm�L���[Params�C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
                //�f�[�^�R�[�h  ���@@"AI813"�i���������ʒm�j
                l_orderNotityParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
                //�،���ЃR�[�h ���@@getMainAccount()�̖߂�l.�،���ЃR�[�h
                l_orderNotityParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());
                //���X�R�[�h       ���@@getMainAccount()�̖߂�l.���X�R�[�h
                l_orderNotityParams.setBranchCode(l_mainAccountRow.getBranchCode());
                //�����R�[�h       ���@@getMainAccount()�̖߂�l.�����R�[�h
                l_orderNotityParams.setAccountCode(l_mainAccountRow.getAccountCode());                
                //���҃R�[�h       ���@@null�i�Œ�j
                l_orderNotityParams.setTraderCode(null);
                //���ʃR�[�h       ���@@this.�����P��Row.���ʃR�[�h
                l_orderNotityParams.setOrderRequestNumber(this.eqtypeOrderUnitRow.getOrderRequestNumber());
                //��萔��        ���@@this.�����P��Row.��萔��
                l_orderNotityParams.setExecutedQuantity(this.eqtypeOrderUnitRow.getExecutedQuantity());
                //�������R�R�[�h ���@@"�������j��"
                l_orderNotityParams.setReasonCode(WEB3ExpirationReasonCodeDef.ORDER_CANCEL);
                //�����ʒm�敪  ���@@"����"
                l_orderNotityParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
                //�G���[���b�Z�[�W    ���@@"���Ǘ��Ҏ�����"
                //�@@���G���[���b�Z�[�W�́A�����P��.�����G���[���R�R�[�h�ɋL�^�����B
                l_orderNotityParams.setErrorMessage(WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED);
                //�����敪        ���@@"������"
                l_orderNotityParams.setStatus(WEB3StatusDef.NOT_DEAL);
                
                WEB3EquityReceiveCloseOrderUnitService l_orderUnitService = 
                    (WEB3EquityReceiveCloseOrderUnitService)Services.getService(WEB3EquityReceiveCloseOrderUnitService.class);
                //1.7�����������s���B
                //[����]
                //���������ʒm�L���[Params�F�@@���������L���[Params
                //�����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(this.�����P��Row)
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderMgr = 
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderMgr.toOrderUnit(this.eqtypeOrderUnitRow);
                
                l_orderUnitService.execCloseOrder(l_orderNotityParams, l_orderUnit);
                
                //1.8�⏕�������擾����B
                //[����]
                //arg0�F�@@this.�����P��Row.����ID
                //arg1�F�@@this.�����P��Row.�⏕����ID
                WEB3GentradeSubAccount l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    this.eqtypeOrderUnitRow.getAccountId(), 
                    this.eqtypeOrderUnitRow.getSubAccountId());
                
                //1.9�]�͍Čv�Z���s���B
                //[����]
                //�⏕�����F�@@getSubAccount()�̖߂�l
                WEB3TPTradingPowerService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                
                //(*)1.10�擾�����L���[���R�[�h��update����B
                //(*)�擾����������������L���[�܂��́A�������n�L���[��
                //update����B
                //�X�V���e�ɂ��ẮADB�X�V�d�l
                //�����蓮����_������������L���[�e�[�u��.xls�v
                //�u�����蓮����_�������n�L���[�e�[�u��.xls�v
                //�Q�ƁB
                if (OrderCategEnum.SWAP_MARGIN.equals(this.eqtypeOrderUnitRow.getOrderCateg()))
                {
                    for (int i = 0; i < l_listRecords.size(); i++)
                    {
                        HostEqtypeSwapRow l_swapRow = (HostEqtypeSwapRow)l_listRecords.get(i);
                        HostEqtypeSwapParams l_swapParams = new HostEqtypeSwapParams(l_swapRow);
                        
                        //��������ԍ� = �i�����l�j + 1
                        l_swapParams.setOrderActionSerialNo(l_swapRow.getOrderActionSerialNo() + 1);
                        //�����敪 = 4�F�Ǘ��Ҏ蓮������
                        l_swapParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                        //�X�V���t = ���ݎ���
                        l_swapParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_swapParams);
                    }

                }
                else
                {
                    for (int i = 0; i < l_listRecords.size(); i++)
                    {
                        HostEqtypeOrderAllRow l_orderAllRow = (HostEqtypeOrderAllRow)l_listRecords.get(i);
                        HostEqtypeOrderAllParams l_orderAllParams = new HostEqtypeOrderAllParams(l_orderAllRow);
                        
                        //��������ԍ� = �i�����l�j + 1
                        l_orderAllParams.setOrderActionSerialNo(l_orderAllRow.getOrderActionSerialNo() + 1);
                        //�����敪 = 4�F�Ǘ��Ҏ蓮������
                        l_orderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                        //�X�V���t = ���ݎ���
                        l_orderAllParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_orderAllParams);
                    }
                }
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
     * (�Ǘ��ҁE�����蓮�����I�����C�����s����TransactionCallback)<BR>
     * �Ǘ��ҁE�����蓮�����I�����C�����s����TransactionCallback<BR>
     * <BR>
     * �蓮�����̏����J�n�^�I����<BR>
     * �I�����C�����s���ʃe�[�u���ɋL�^����B<BR>
     */
    public class WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback implements TransactionCallback 
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
        public WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback() 
        {
         
        }
        
        /**
         * (�Ǘ��ҁE�����蓮�����I�����C�����s����TransactionCallback)<BR>
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
         * WEB3AsynAdminEquityManualExpireServiceImpl.WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback
         * @@roseuid 44693F0300BA
         */
        public WEB3AdminEquityManualExpireOnlineRunStatusTransactionCallback(
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
         * �@@�@@�����^�C�v�F�@@"����"<BR>
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
                    ProductTypeEnum.EQUITY, 
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
     * (�Ǘ��ҁE�����蓮�����f�[�����g���K�[TransactionCallback)<BR>
     * �Ǘ��ҁE�����蓮�����f�[�����g���K�[TransactionCallback<BR>
     * �i�g�����U�N�V���������FTX_JOIN_EXISTING�j<BR>
     */
    public class WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback implements TransactionCallback 
    {
        
        /**
         * (�X���b�hNo)<BR>
         * �X���b�hNo
         */
        public long threadNo;
        
        /**
         * @@roseuid 447ACD830222
         */
        public WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback() 
        {
         
        }
        
        /**
         * (�Ǘ��ҁE�����蓮�����f�[�����g���K�[TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���������g�̓������ڂɃZ�b�g����B<BR>
         * @@param l_lngThreadNo - (�X���b�hNo)<BR>
         * �X���b�hNo
         * @@return 
         * WEB3AsynAdminEquityManualExpireServiceImpl.WEB3AdminEquityManualExpireD
         * aemonTriggerTransactionCallback
         * @@roseuid 44744A1901B1
         */
        public WEB3AdminEquityManualExpireDaemonTriggerTransactionCallback(long l_lngThreadNo) 
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
