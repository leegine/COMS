head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : Webbroker3-feq �v���O�C���N���X(WEB3FeqAppPlugin.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/19 ���� (���u) �V�K�쐬      
                 �@@2006/09/18  ����(���u) �d�l�ύX�E���f��247-255   
Revesion History : 2008/01/17 �đo�g(���u) �d�l�ύX�E���f��373�A377
Revesion History : 2009/08/03 ���@@�g(���u) �d�l�ύX�E���f��501
Revesion History : 2010/09/08 �����F(���u) �d�l�ύX�E���f��549
*/

package webbroker3.feq;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqAccountDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqMasterDatabaseExtensions;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.data.WEB3FeqAccountDatabaseExtensions;
import webbroker3.feq.data.WEB3FeqMasterDatabaseExtensions;
import webbroker3.feq.data.WEB3FeqSessionDatabaseExtensions;
import webbroker3.feq.handler.WEB3AdminFeqCalendarRegistHandler;
import webbroker3.feq.handler.WEB3AdminFeqCancelExecutionHandler;
import webbroker3.feq.handler.WEB3AdminFeqExchangeRegistHandler;
import webbroker3.feq.handler.WEB3AdminFeqExecuteResultUploadHandler;
import webbroker3.feq.handler.WEB3AdminFeqExecutionEndHandler;
import webbroker3.feq.handler.WEB3AdminFeqExecutionInputHandler;
import webbroker3.feq.handler.WEB3AdminFeqForeignCostRegistHandler;
import webbroker3.feq.handler.WEB3AdminFeqMarketLinkChangeHandler;
import webbroker3.feq.handler.WEB3AdminFeqOpenAtOrderDLHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderAcceptHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderAcceptResultUploadHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderAndExecutionHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderVoucherListHandler;
import webbroker3.feq.handler.WEB3AdminFeqProductInfoUpdateHandler;
import webbroker3.feq.handler.WEB3AdminFeqProductListHandler;
import webbroker3.feq.handler.WEB3AdminFeqRcvdQueueReferenceHandler;
import webbroker3.feq.handler.WEB3AdminFeqSendQueueRecoveryHandler;
import webbroker3.feq.handler.WEB3AdminFeqSendQueueReferenceHandler;
import webbroker3.feq.handler.WEB3AdminFeqUploadErrCancelHandler;
import webbroker3.feq.handler.WEB3FeqBalanceReferenceHandler;
import webbroker3.feq.handler.WEB3FeqBookValuePriceRegistHandler;
import webbroker3.feq.handler.WEB3FeqBuyHandler;
import webbroker3.feq.handler.WEB3FeqCancelHandler;
import webbroker3.feq.handler.WEB3FeqChangeHandler;
import webbroker3.feq.handler.WEB3FeqExecuteReferenceHandler;
import webbroker3.feq.handler.WEB3FeqNettingExchangeHandler;
import webbroker3.feq.handler.WEB3FeqOrderAcceptExecutionNotifyHandler;
import webbroker3.feq.handler.WEB3FeqOrderCarryOverHandler;
import webbroker3.feq.handler.WEB3FeqProductListHandler;
import webbroker3.feq.handler.WEB3FeqSellHandler;
import webbroker3.feq.message.*;
import webbroker3.feq.service.delegate.WEB3AdminFeqCalendarRegistService;
import webbroker3.feq.service.delegate.WEB3AdminFeqCancelExecutionService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExchangeRegistService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecuteResultUploadService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionInputService;
import webbroker3.feq.service.delegate.WEB3AdminFeqForeignCostRegistService;
import webbroker3.feq.service.delegate.WEB3AdminFeqMarketLinkChangeService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOpenAtOrderDLService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptResultUploadService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAndExecutionService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderVoucherListService;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductInfoUpdateService;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductListService;
import webbroker3.feq.service.delegate.WEB3AdminFeqRcvdQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueRecoveryService;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3AdminFeqUploadErrCancelService;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqBalanceReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.feq.service.delegate.WEB3FeqBuyService;
import webbroker3.feq.service.delegate.WEB3FeqCancelService;
import webbroker3.feq.service.delegate.WEB3FeqChangeService;
import webbroker3.feq.service.delegate.WEB3FeqExecuteReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptExecutionNotifyService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverService;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.feq.service.delegate.WEB3FeqProductListService;
import webbroker3.feq.service.delegate.WEB3FeqSellService;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqCalendarRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqCancelExecutionServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExchangeRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecuteResultUploadServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionEndServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionEndUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionInputServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqForeignCostRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqMarketLinkChangeServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOpenAtOrderDLServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAcceptResultUploadServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAcceptServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAndExecutionServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderVoucherListServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqProductInfoUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqProductListServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqRcvdQueueReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueRecoveryServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqUploadErrCancelServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBalanceReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBookValuePriceRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBuyServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqCancelServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqChangeServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecuteReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqMailSenderServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqNettingExchangeServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptExecutionNotifyServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderCarryOverServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderCarryOverUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderEmpCodeGettingServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderEmpCodeManageServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqProductListServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqSellServiceImpl;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-feq �v���O�C���N���X�B
 *
 * @@author ���� (���u)
 * @@version 1.0
 */

public final class WEB3FeqAppPlugin  extends Plugin
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3FeqAppPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3FeqAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // �g���g�����U�N�V�����E�}�l�[�W���[��
        // �I�[�o�[���C�h���\�b�h���������ߊg��������W���[���N���X���쐬��
        // �g��������W���[���N���X���Őݒ�

        try
        {
            l_finApp.uninstallTradingModule("xb-feq-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        try
        {
            log.info("Installing TradingModule : web3-feq");
            l_finApp.installTradingModule(new WEB3FeqTradingModule());
            log.info("Installed TradingModule : web3-feq");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3FeqMarketRequestSenderService());

        // �v�Z�T�[�r�X�N���X
        l_tradingModule.overrideBizLogicProvider(new WEB3FeqBizLogicProvider());

        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3FeqOrderManager());

        // FEQ�v���_�N�g�}�l�[�W�� 
        l_tradingModule.overrideProductManager(new WEB3FeqProductManager());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(new WEB3FeqPositionManager());

        //�����R���ʃ`�F�b�N
        WEB3FeqTypeOrderManagerReusableValidations.register();
        
        //�O�����������X�V�}�l�[�W��
        WEB3FeqProductTypeOrderSubmitterPersistenceManager 
            l_feqProductTypeOrderSubmitterPersistenceManager = 
                new WEB3FeqProductTypeOrderSubmitterPersistenceManager();
        l_feqProductTypeOrderSubmitterPersistenceManager.register();
        
        // DatabaseExtensions �̃v���O�C������ ----------------------
        FeqMasterDatabaseExtensions.plug();
        FeqAccountDatabaseExtensions.plug();
        WEB3FeqSessionDatabaseExtensions.plug();
        WEB3FeqMasterDatabaseExtensions.plug();
        WEB3FeqAccountDatabaseExtensions.plug();
        
        
        // Service �̓o�^���� ----------------------
        plugServices();

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        plugLogSysTimeInterceptors();
        
        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        plugServiceInterceptors();

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        plugTransactionalInterceptors();

        // Message �̓o�^���� ----------------------
        plugMessages();
        
        // Handler �̓o�^����
        plugHandlers();
        
        // �L���b�V���E�v�����[�h
        try
        {
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager)GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisInstitution =
                l_processor.doFindAllQuery(InstitutionRow.TYPE);
            if (l_lisInstitution != null)
            {
                WEB3GentradeTradingClendarContext l_context = 
                    new WEB3GentradeTradingClendarContext();
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
                l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
                int l_intInstitutionSize = l_lisInstitution.size();
                for (int i = 0;i < l_intInstitutionSize;i++)
                {
                    InstitutionRow l_institutionRow = (InstitutionRow)l_lisInstitution.get(i);
                    String l_strInstitutionCode = l_institutionRow.getInstitutionCode();
                    l_context.setInstitutionCode(l_strInstitutionCode);
                    List l_lisBranch =
                        l_processor.doFindAllQuery(
                            BranchRow.TYPE,
                            "institution_code = ?",
                            new Object[] { l_strInstitutionCode });
                    if (l_lisBranch != null)
                    {
                        int l_intBranchSize = l_lisBranch.size();
                        for (int j = 0;j < l_intBranchSize;j++)
                        {
                            BranchRow l_branchRow = (BranchRow)l_lisBranch.get(j);
                            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getFstkDiv()))
                            {
                                String l_strBranchCode = l_branchRow.getBranchCode();
                                l_context.setBranchCode(l_strBranchCode);
                                l_context.setMarketCode(null);
                                WEB3GentradeTradingTimeManagement.setTimestamp();
                                l_feqProductManager.getProduct(
                                    l_strInstitutionCode, 
                                    null,
                                    null,
                                    null,
                                    true,
                                    null);
                                break;
                            }
                        }
                    }
                }
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    null);
            }
        }
        catch (Exception l_exp)
        {
            log.error(l_exp.getMessage(), l_exp);
            Object l_objAttribute = ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            if (l_objAttribute != null)
            {
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    null);
            }
        }
        
        log.exiting(METHOD_NAME);
    }
    /**
     * Service �̓o�^����
     * @@throws Exception
     */
    private static void plugServices() throws Exception
    {
        // ------�i�O���������ʁj�O���������ʃT�[�r�X------
    
        //�O���������[�����M�T�[�r�X
        Services.registerService(
            WEB3FeqMailSenderService.class,
            new WEB3FeqMailSenderServiceImpl());
   
        //�O���������ʃ��b�Z�[�W�쐬�T�[�r�X                 
        Services.registerService(
            WEB3FeqOrderEmpCodeManageService.class,
            new WEB3FeqOrderEmpCodeManageServiceImpl());
        
        //�O��������t�X�V�T�[�r�X
        Services.registerService(
            WEB3FeqAcceptUpdateService.class,
            new WEB3FeqAcceptUpdateServiceImpl());

        //�O��������t�X�V�T�[�r�X
        Services.registerService(
            WEB3FeqOrderAndExecutionUpdateService.class,
            new WEB3FeqOrderAndExecutionUpdateServiceImpl());

        //�O�������^�p�R�[�h�擾�T�[�r�X
        Services.registerService(
            WEB3FeqOrderEmpCodeGettingService.class,
            new WEB3FeqOrderEmpCodeGettingServiceImpl());
        //�i�O���������ʁj�O���������ʃT�[�r�X---------End-------

        
        //�i�O�������j�i�ǁj�O�������T�[�r�X------
    
        //�O�������J�����_�[�o�^�T�[�r�X
        Services.registerService(
            WEB3AdminFeqCalendarRegistService.class,
            new WEB3AdminFeqCalendarRegistServiceImpl());     
      
        //�O�������ב֓o�^�T�[�r�X
        Services.registerService(
            WEB3AdminFeqExchangeRegistService.class,
            new WEB3AdminFeqExchangeRegistServiceImpl());   
        
        //�O��������t����DL�T�[�r�X                     
        Services.registerService(
            WEB3AdminFeqOpenAtOrderDLService.class,
            new WEB3AdminFeqOpenAtOrderDLServiceImpl());    
        
       //�O���������n�萔���o�^�T�[�r�X     
        Services.registerService(
            WEB3AdminFeqForeignCostRegistService.class,
            new WEB3AdminFeqForeignCostRegistServiceImpl());       

       //�O�������o���I��UnitService 
        Services.registerService(
            WEB3AdminFeqExecutionEndUnitService.class,
            new WEB3AdminFeqExecutionEndUnitServiceImpl());
        
        //�O�������o���I���T�[�r�X
        Services.registerService(
            WEB3AdminFeqExecutionEndService.class,
            new WEB3AdminFeqExecutionEndServiceImpl());  

       //�Ǘ��ҊO�������o�����̓T�[�r�X
        Services.registerService(
            WEB3AdminFeqExecutionInputService.class,
            new WEB3AdminFeqExecutionInputServiceImpl());           

       //�Ǘ��ҊO�������o��������T�[�r�X
        Services.registerService(
            WEB3AdminFeqCancelExecutionService.class,
            new WEB3AdminFeqCancelExecutionServiceImpl());  

        //�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
        Services.registerService(
            WEB3AdminFeqOrderAcceptResultUploadService.class,
            new WEB3AdminFeqOrderAcceptResultUploadServiceImpl());  

        //�Ǘ��ҊO������������t����F�؃T�[�r�X
        Services.registerService(
            WEB3AdminFeqOrderAcceptService.class,
            new WEB3AdminFeqOrderAcceptServiceImpl());  

        //�Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminFeqOrderVoucherListService.class,
            new WEB3AdminFeqOrderVoucherListServiceImpl());  

        //�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
        Services.registerService(
            WEB3AdminFeqUploadErrCancelService.class,
            new WEB3AdminFeqUploadErrCancelServiceImpl());  

        //�Ǘ��ҊO�����������ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminFeqProductListService.class,
            new WEB3AdminFeqProductListServiceImpl());  

        //�O�������������X�V�T�[�r�X
        Services.registerService(
            WEB3AdminFeqProductInfoUpdateService.class,
            new WEB3AdminFeqProductInfoUpdateServiceImpl());  

        //�Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�X
        Services.registerService(
            WEB3AdminFeqExecuteResultUploadService.class,
            new WEB3AdminFeqExecuteResultUploadServiceImpl());  

        //�Ǘ��ҊO�����������̓T�[�r�X
        Services.registerService(
            WEB3AdminFeqOrderAndExecutionService.class,
            new WEB3AdminFeqOrderAndExecutionServiceImpl());  
        
        //�Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X 
        Services.registerService(
            WEB3AdminFeqRcvdQueueReferenceService.class,
            new WEB3AdminFeqRcvdQueueReferenceServiceImpl());   
        
        //�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X 
        Services.registerService(
            WEB3AdminFeqSendQueueRecoveryService.class,
            new WEB3AdminFeqSendQueueRecoveryServiceImpl()); 
        
        //�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X 
        Services.registerService(
            WEB3AdminFeqSendQueueReferenceService.class,
            new WEB3AdminFeqSendQueueReferenceServiceImpl()); 
        
        //�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X 
        Services.registerService(
            WEB3AdminFeqMarketLinkChangeService.class,
            new WEB3AdminFeqMarketLinkChangeServiceImpl ()); 
        
        //�i�O�������j�i�ǁj�O�������T�[�r�X---------End-------

        //�i�O�������j �O�������T�[�r�X------

        //�O�������c���Ɖ�T�[�r�X
        Services.registerService(
            WEB3FeqBalanceReferenceService.class,
            new WEB3FeqBalanceReferenceServiceImpl());  

        //�O����������T�[�r�X
        Services.registerService(
            WEB3FeqCancelService.class,
            new WEB3FeqCancelServiceImpl());  

        //�O�����������J�zUnitService
        Services.registerService(
            WEB3FeqOrderCarryOverUnitService.class,
            new WEB3FeqOrderCarryOverUnitServiceImpl());  

        //�O�����������J�z�T�[�r�X
        Services.registerService(
            WEB3FeqOrderCarryOverService.class,
            new WEB3FeqOrderCarryOverServiceImpl());  

        //�O�������������Ɖ�T�[�r�X
        Services.registerService(
            WEB3FeqExecuteReferenceService.class,
            new WEB3FeqExecuteReferenceServiceImpl());  

        //�O�����������T�[�r�X
        Services.registerService(
            WEB3FeqChangeService.class,
            new WEB3FeqChangeServiceImpl());  

        //�O���������t�T�[�r�X
        Services.registerService(
            WEB3FeqBuyService.class,
            new WEB3FeqBuyServiceImpl());  

        //�O���������t�T�[�r�X
        Services.registerService(
            WEB3FeqSellService.class,
            new WEB3FeqSellServiceImpl());  

        //�O�������뉿�P���o�^�T�[�r�X
        Services.registerService(
            WEB3FeqBookValuePriceRegistService.class,
            new WEB3FeqBookValuePriceRegistServiceImpl());  

        //�O�����������ꗗ�T�[�r�X
        Services.registerService(
            WEB3FeqProductListService.class,
            new WEB3FeqProductListServiceImpl());  
        
        //�O������������t�o���ʒm�T�[�r�X
        Services.registerService(
            WEB3FeqOrderAcceptExecutionNotifyService.class,
            new WEB3FeqOrderAcceptExecutionNotifyServiceImpl());  
        
        //�O������������t�P���T�[�r�X 
        Services.registerService(
            WEB3FeqOrderAcceptUnitService.class,
            new WEB3FeqOrderAcceptUnitServiceImpl()); 
        
        //�O�������o���ʒm�P���T�[�r�X 
        Services.registerService(
            WEB3FeqExecutionNotifyUnitService.class,
            new WEB3FeqExecutionNotifyUnitServiceImpl()); 

        //�O�������בփl�b�e�B���O�T�[�r�X
        Services.registerService(
            WEB3FeqNettingExchangeService.class,
            new WEB3FeqNettingExchangeServiceImpl());
        
        //�i�O�������j  �O�������T�[�r�X---------End-------
    }
    
    /**
     * Service �� Interceptor �ݒ菈��<BR>
     * �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
     * @@throws Exception
     */
    private static void plugLogSysTimeInterceptors() throws Exception
    {
        // ------�i�O���������ʁj�O���������ʃT�[�r�X------
    
         //�O���������[�����M�T�[�r�X
         Services.addInterceptor(
             WEB3FeqMailSenderService.class,
             new WEB3LogSysTimeInterceptor());
   
        //�O���������ʃ��b�Z�[�W�쐬�T�[�r�X                 
         Services.addInterceptor(
             WEB3FeqOrderEmpCodeManageService.class,
             new WEB3LogSysTimeInterceptor());        

         //�O�������^�p�R�[�h�擾�T�[�r�X
         Services.addInterceptor(
             WEB3FeqOrderEmpCodeGettingService.class,
             new WEB3LogSysTimeInterceptor()); 

         //�i�O���������ʁj�O���������ʃT�[�r�X---------End-------

         //�i�O�������j�i�ǁj�O�������T�[�r�X------
    
         //�O�������J�����_�[�o�^�T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqCalendarRegistService.class,
             new WEB3LogSysTimeInterceptor());   
      
         //�O�������ב֓o�^�T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqExchangeRegistService.class,
             new WEB3LogSysTimeInterceptor());
        
         //�O��������t����DL�T�[�r�X                     
         Services.addInterceptor(
             WEB3AdminFeqOpenAtOrderDLService.class,
             new WEB3LogSysTimeInterceptor());
        
        //�O���������n�萔���o�^�T�[�r�X     
         Services.addInterceptor(
             WEB3AdminFeqForeignCostRegistService.class,
             new WEB3LogSysTimeInterceptor());

        //�O�������o���I��UnitService 
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndUnitService.class,
             new WEB3LogSysTimeInterceptor());
        
         //�O�������o���I���T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndService.class,
             new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҊO�������o�����̓T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqExecutionInputService.class,
             new WEB3LogSysTimeInterceptor());          

        //�Ǘ��ҊO�������o��������T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqCancelExecutionService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqOrderAcceptResultUploadService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO������������t����F�؃T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqOrderAcceptService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqOrderVoucherListService.class,
             new WEB3LogSysTimeInterceptor()); 

         //�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqUploadErrCancelService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO�����������ꗗ�T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqProductListService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�������������X�V�T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqProductInfoUpdateService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqExecuteResultUploadService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO�����������̓T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqOrderAndExecutionService.class,
             new WEB3LogSysTimeInterceptor());

         //�Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X 
         Services.addInterceptor(
             WEB3AdminFeqRcvdQueueReferenceService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X 
         Services.addInterceptor(
             WEB3AdminFeqSendQueueRecoveryService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X 
         Services.addInterceptor(
             WEB3AdminFeqSendQueueReferenceService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X 
         Services.addInterceptor(
             WEB3AdminFeqMarketLinkChangeService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�i�O�������j�i�ǁj�O�������T�[�r�X---------End-------

         //�i�O�������j �O�������T�[�r�X------

         //�O�������c���Ɖ�T�[�r�X
         Services.addInterceptor(
             WEB3FeqBalanceReferenceService.class,
             new WEB3LogSysTimeInterceptor());

         //�O����������T�[�r�X
         Services.addInterceptor(
             WEB3FeqCancelService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�����������J�zUnitService
         Services.addInterceptor(
             WEB3FeqOrderCarryOverUnitService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�����������J�z�T�[�r�X
         Services.addInterceptor(
             WEB3FeqOrderCarryOverService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�������������Ɖ�T�[�r�X
         Services.addInterceptor(
             WEB3FeqExecuteReferenceService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�����������T�[�r�X
         Services.addInterceptor(
             WEB3FeqChangeService.class,
             new WEB3LogSysTimeInterceptor());

         //�O���������t�T�[�r�X
         Services.addInterceptor(
             WEB3FeqBuyService.class,
             new WEB3LogSysTimeInterceptor());

         //�O���������t�T�[�r�X
         Services.addInterceptor(
             WEB3FeqSellService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�������뉿�P���o�^�T�[�r�X
         Services.addInterceptor(
             WEB3FeqBookValuePriceRegistService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�����������ꗗ�T�[�r�X
         Services.addInterceptor(
             WEB3FeqProductListService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�O������������t�o���ʒm�T�[�r�X
         Services.addInterceptor(
             WEB3FeqOrderAcceptExecutionNotifyService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�O������������t�P���T�[�r�X 
         Services.addInterceptor(
             WEB3FeqOrderAcceptUnitService.class,
             new WEB3LogSysTimeInterceptor());
         
         //�O�������o���ʒm�P���T�[�r�X 
         Services.addInterceptor(
             WEB3FeqExecutionNotifyUnitService.class,
             new WEB3LogSysTimeInterceptor());

         //�O�������בփl�b�e�B���O�T�[�r�X
         Services.addInterceptor(
             WEB3FeqNettingExchangeService.class,
             new WEB3LogSysTimeInterceptor());

         //�i�O�������j  �O�������T�[�r�X---------End-------
    
        }
    /**
     * Service �� Interceptor �ݒ菈��<BR>
     * ServiceInterceptor�ݒ�
     * @@throws Exception
     */
    private static void plugServiceInterceptors() throws Exception
    {
        // ------�i�O���������ʁj�O���������ʃT�[�r�X�C���^�t�F�[�X------

        //�O�������o���I��UnitService�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndUnitService.class,
             new WEB3AdminFeqExecutionEndUnitServiceInterceptor());
         
         //�O�������o���I���T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndService.class,
             new WEB3AdminFeqExecutionEndServiceInterceptor());

        //�Ǘ��ҊO�������o�����̓T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqExecutionInputService.class,
             new WEB3AdminFeqExecutionInputServiceInterceptor());    

        //�Ǘ��ҊO�������o��������T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqCancelExecutionService.class,
             new WEB3AdminFeqCancelExecutionServiceInterceptor());

         //�Ǘ��ҊO������������t����F�؃T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqOrderAcceptService.class,
             new WEB3AdminFeqOrderAcceptServiceInterceptor());

         //�Ǘ��ҊO�����������ꗗ�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqProductListService.class,
             new WEB3AdminFeqProductListServiceInterceptor());   

         //�Ǘ��ҊO�����������̓T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3AdminFeqOrderAndExecutionService.class,
             new WEB3AdminFeqOrderAndExecutionInterceptor());
         
         //�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqUploadErrCancelService.class,
             new WEB3AdminFeqUploadServiceInterceptor());

         //�Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�X
         Services.addInterceptor(
             WEB3AdminFeqExecuteResultUploadService.class,
             new WEB3AdminFeqUploadServiceInterceptor());
             
        //�Ǘ��ҊO�������A�b�v���[�h�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AdminFeqOrderAcceptResultUploadService.class,
            new WEB3AdminFeqUploadServiceInterceptor());
            
        //�O�������������X�V�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AdminFeqProductInfoUpdateService.class,
            new WEB3AdminFeqProductInfoUpdateServiceInterceptor());
        
        //�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X 
        Services.addInterceptor(
            WEB3AdminFeqSendQueueRecoveryService.class,
            new WEB3AdminFeqSendQueueRecoveryServiceInterceptor());
         
         //�i�O�������j�i�ǁj�O�������T�[�r�X-�C���^�t�F�[�X--------End-------

         //�i�O�������j �O�������T�[�r�X�C���^�t�F�[�X------

         //�O�������c���Ɖ�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqBalanceReferenceService.class,
             new WEB3FeqBalanceReferenceServiceInterceptor());

         //�O����������T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqCancelService.class,
             new WEB3FeqCancelServiceInterceptor());

         //�O�����������J�zUnitService�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqOrderCarryOverUnitService.class,
             new WEB3FeqOrderCarryOverUnitServiceInterceptor());


         //�O�������������Ɖ�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqExecuteReferenceService.class,
             new WEB3FeqExecuteReferenceServiceInterceptor());

         //�O�����������T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqChangeService.class,
             new WEB3FeqChangeServiceInterceptor());

         //�O���������t�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqBuyService.class,
             new WEB3FeqBuyServiceInterceptor());

         //�O���������t�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqSellService.class,
             new WEB3FeqSellServiceInterceptor());

         //�O�������뉿�P���o�^�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqBookValuePriceRegistService.class,
             new WEB3FeqBookValuePriceRegistServiceInterceptor());

         //�O�����������ꗗ�T�[�r�X�C���^�t�F�[�X
         Services.addInterceptor(
             WEB3FeqProductListService.class,
             new WEB3FeqProductListInterceptor());
         
         //�O������������t�P���T�[�r�X 
         Services.addInterceptor(
             WEB3FeqOrderAcceptUnitService.class,
             new WEB3FeqOrderAcceptUnitServiceInterceptor());
         
         //�O�������o���ʒm�P���T�[�r�X 
         Services.addInterceptor(
             WEB3FeqExecutionNotifyUnitService.class,
             new WEB3FeqExecutionNotifyUnitServiceInterceptor());

         //�O�������בփl�b�e�B���O�T�[�r�X
         Services.addInterceptor(
             WEB3FeqNettingExchangeService.class,
             new WEB3FeqNettingExchangeServiceInterceptor());

         //�i�O�������j  �O�������T�[�r�X�C���^�t�F�[�X---------End-------
     }
    /**
     * Service �� Interceptor �ݒ菈��<BR>
     * �����g�����U�N�V�����ݒ�
     * @@throws Exception
     */
    private static void plugTransactionalInterceptors() throws Exception
    {
        // ------�i�O���������ʁj�O���������ʃT�[�r�X------
    
        //�O���������[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3FeqMailSenderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
   
       //�O���������ʃ��b�Z�[�W�쐬�T�[�r�X                 
        Services.addInterceptor(
            WEB3FeqOrderEmpCodeManageService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�������^�p�R�[�h�擾�T�[�r�X
        Services.addInterceptor(
            WEB3FeqOrderEmpCodeGettingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

       //�O��������t�X�V�T�[�r�X
        Services.addInterceptor(
            WEB3FeqAcceptUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
       //�O�������o���^���X�V�T�[�r�X 
        Services.addInterceptor(
            WEB3FeqOrderAndExecutionUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�O���������ʁj�O���������ʃT�[�r�X---------End-------

        //�i�O�������j�i�ǁj�O�������T�[�r�X------
    
        //�O�������J�����_�[�o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqCalendarRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));   
      
        //�O�������ב֓o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqExchangeRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�O��������t����DL�T�[�r�X                     
        Services.addInterceptor(
            WEB3AdminFeqOpenAtOrderDLService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
       //�O���������n�萔���o�^�T�[�r�X     
        Services.addInterceptor(
            WEB3AdminFeqForeignCostRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

       //�O�������o���I��UnitService 
        Services.addInterceptor(
            WEB3AdminFeqExecutionEndUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //�O�������o���I���T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqExecutionEndService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

       //�Ǘ��ҊO�������o�����̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqExecutionInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));          

       //�Ǘ��ҊO�������o��������T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqCancelExecutionService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqOrderAcceptResultUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҊO������������t����F�؃T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqOrderVoucherListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqUploadErrCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҊO�����������ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�������������X�V�T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqProductInfoUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqExecuteResultUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҊO�����������̓T�[�r�X
        Services.addInterceptor(
            WEB3AdminFeqOrderAndExecutionService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X 
        Services.addInterceptor(
            WEB3AdminFeqSendQueueRecoveryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //�Ǘ��ҊO�������s��A���敪�ύX�T�[�r�X 
        Services.addInterceptor(
            WEB3AdminFeqMarketLinkChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //�i�O�������j�i�ǁj�O�������T�[�r�X---------End-------

        //�i�O�������j �O�������T�[�r�X------

        //�O�������c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3FeqBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O����������T�[�r�X
        Services.addInterceptor(
            WEB3FeqCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�����������J�zUnitService
        Services.addInterceptor(
            WEB3FeqOrderCarryOverUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�O�����������J�z�T�[�r�X
        Services.addInterceptor(
            WEB3FeqOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�������������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3FeqExecuteReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�����������T�[�r�X
        Services.addInterceptor(
            WEB3FeqChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O���������t�T�[�r�X
        Services.addInterceptor(
            WEB3FeqBuyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O���������t�T�[�r�X
        Services.addInterceptor(
            WEB3FeqSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�������뉿�P���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3FeqBookValuePriceRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�����������ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3FeqProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O������������t�P���T�[�r�X 
        Services.addInterceptor(
            WEB3FeqOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�O�������o���ʒm�P���T�[�r�X 
        Services.addInterceptor(
            WEB3FeqExecutionNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�������בփl�b�e�B���O�T�[�r�X 
        Services.addInterceptor(
            WEB3FeqNettingExchangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�O�������j  �O�������T�[�r�X---------End-------
    }

    /**
     * Message �̓o�^����
     * @@throws Exception
     */
    private static void plugMessages() throws Exception
    {
        //�O�������c�����v���N�G�X�g   
        regClass(WEB3FeqBalanceReferenceTotalRequest.class);

        //�O�������c�����v���X�|���X   
       regClass(WEB3FeqBalanceReferenceTotalResponse.class);
        
        //�O�������c���Ɖ�N�G�X�g   
       regClass(WEB3FeqBalanceReferenceRequest.class);
        
        //�O�������c���Ɖ�X�|���X   
       regClass(WEB3FeqBalanceReferenceResponse.class);
        
        //�O����������m�F���N�G�X�g   
       regClass(WEB3FeqCancelConfirmRequest.class);
        
        //�O����������m�F���X�|���X   
       regClass(WEB3FeqCancelConfirmResponse.class);
        
        //�O����������������N�G�X�g   
       regClass(WEB3FeqCancelCompleteRequest.class);
        
        //�O����������������X�|���X   
       regClass(WEB3FeqCancelCompleteResponse.class);
        
        //�O�������������ʃ��N�G�X�g   
       regClass(WEB3FeqCommonRequest.class);
        
        //�O�����������J�z���N�G�X�g   
       regClass(WEB3FeqOrderTransferRequest.class);
        
        //�O�����������J�z���X�|���X   
       regClass(WEB3FeqOrderTransferResponse.class);
        
        //�O�������������Ɖ�N�G�X�g 
       regClass(WEB3FeqExecuteReferenceRequest.class);
        
        //�O�������������Ɖ�X�|���X 
       regClass(WEB3FeqExecuteReferenceResponse.class);
        
        //�O�������������ڍ׃��N�G�X�g 
       regClass(WEB3FeqExecuteDetailsRequest.class);
        
        //�O�������������ڍ׃��X�|���X 
       regClass(WEB3FeqExecuteDetailsResponse.class);
        
        //�O������������藚�����N�G�X�g 
       regClass(WEB3FeqOrderHistoryRequest.class);
        
        //�O������������藚�����X�|���X 
       regClass(WEB3FeqOrderHistoryResponse.class);
        
        //�O�����������m�F���N�G�X�g   
       regClass(WEB3FeqChangeConfirmRequest.class);
        
        //�O�����������m�F���X�|���X   
       regClass(WEB3FeqChangeConfirmResponse.class);
        
        //�O�����������������N�G�X�g   
       regClass(WEB3FeqChangeCompleteRequest.class);
        
        //�O�����������������X�|���X   
       regClass(WEB3FeqChangeCompleteResponse.class);
        
        //�O�������������ʃ��N�G�X�g   
       regClass(WEB3FeqChangeCommonRequest.class);
        
        //�O�������������̓��N�G�X�g   
       regClass(WEB3FeqChangeInputRequest.class);
        
        //�O�������������̓��X�|���X   
       regClass(WEB3FeqChangeInputResponse.class);
        
        //�O���������͋��ʃ��X�|���X   
       regClass(WEB3FeqInputCommonResponse.class);
        
        //�O���������t�m�F���N�G�X�g   
       regClass(WEB3FeqBuyConfirmRequest.class);
        
        //�O���������t�m�F���X�|���X   
       regClass(WEB3FeqBuyConfirmResponse.class);
        
        //�O���������t�������N�G�X�g   
       regClass(WEB3FeqBuyCompleteRequest.class);
        
        //�O���������t�������X�|���X   
       regClass(WEB3FeqBuyCompleteResponse.class);
        
        //�O���������t���ʃ��N�G�X�g   
       regClass(WEB3FeqBuyCommonRequest.class);
        
        //�O���������t���̓��N�G�X�g   
       regClass(WEB3FeqBuyInputRequest.class);
        
        //�O���������t���̓��X�|���X   
       regClass(WEB3FeqBuyInputResponse.class);
       
       //�O�����������I�����N�G�X�g
       regClass(WEB3FeqBuyProductSelectRequest.class);
       
       //�O�����������I�����X�|���X
       regClass(WEB3FeqBuyProductSelectResponse.class);
       
        //�O���������t�m�F���N�G�X�g   
       regClass(WEB3FeqSellConfirmRequest.class);
        
        //�O���������t�m�F���X�|���X   
       regClass(WEB3FeqSellConfirmResponse.class);
        
        //�O���������t�������N�G�X�g   
       regClass(WEB3FeqSellCompleteRequest.class);
        
        //�O���������t�������X�|���X   
       regClass(WEB3FeqSellCompleteResponse.class);
        
        //�O���������t���ʃ��N�G�X�g   
       regClass(WEB3FeqSellCommonRequest.class);
        
        //�O���������t���̓��N�G�X�g   
       regClass(WEB3FeqSellInputRequest.class);
        //�O���������t���̓��X�|���X   
        
       regClass(WEB3FeqSellInputResponse.class);
        
        //�O�������뉿�P���o�^���N�G�X�g 
        regClass(WEB3FeqBookPriceRegistRequest.class);
        
        //�O�������뉿�P���o�^���X�|���X 
        regClass(WEB3FeqBookPriceRegistResponse.class);
        
        //�O�������뉿�P���o�^���̓��N�G�X�g   
        regClass(WEB3FeqBookPriceInputRequest.class);
        
        //�O�������뉿�P���o�^���̓��X�|���X   
        regClass(WEB3FeqBookPriceInputResponse.class);
        
        //�O�����������ꗗ���N�G�X�g   
        regClass(WEB3FeqProductListRequest.class);
        
        //�O�����������ꗗ���X�|���X   
        regClass(WEB3FeqProductListResponse.class);
        
        //�Ǘ��ҊO�������J�����_�[�����������̓��N�G�X�g 
        regClass(WEB3AdminFeqCalendarSearchCondInputRequest.class);
        
        //�Ǘ��ҊO�������J�����_�[�����������̓��X�|���X 
        regClass(WEB3AdminFeqCalendarSearchCondInputResponse.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^�m�F���N�G�X�g   
        regClass(WEB3AdminFeqCalendarRegistConfirmRequest.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^�m�F���X�|���X   
        regClass(WEB3AdminFeqCalendarRegistConfirmResponse.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^�������N�G�X�g   
        regClass(WEB3AdminFeqCalendarRegistCompleteRequest.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^�������X�|���X   
        regClass(WEB3AdminFeqCalendarRegistCompleteResponse.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^���ʃ��N�G�X�g   
        regClass(WEB3AdminFeqCalendarRegistCommonRequest.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^���̓��N�G�X�g   
        regClass(WEB3AdminFeqCalendarRegistInputRequest.class);
        
        //�Ǘ��ҊO�������J�����_�[�o�^���̓��X�|���X   
        regClass(WEB3AdminFeqCalendarRegistInputResponse.class);
        
        //�Ǘ��ҊO�������ב֓o�^�m�F���N�G�X�g  
        regClass(WEB3AdminFeqExchangeRegistConfirmRequest.class);
        
        //�Ǘ��ҊO�������ב֓o�^�m�F���X�|���X  
        regClass(WEB3AdminFeqExchangeRegistConfirmResponse.class);
        
        //�Ǘ��ҊO�������ב֓o�^�������N�G�X�g  
        regClass(WEB3AdminFeqExchangeRegistCompleteRequest.class);
        
        //�Ǘ��ҊO�������ב֓o�^�������X�|���X  
        regClass(WEB3AdminFeqExchangeRegistCompleteResponse.class);
        
        //�Ǘ��ҊO�������ב֓o�^���̓��N�G�X�g  
        regClass(WEB3AdminFeqExchangeRegistInputRequest.class);
        
        //�Ǘ��ҊO�������ב֓o�^���̓��X�|���X  
        regClass(WEB3AdminFeqExchangeRegistInputResponse.class);
        
        //�Ǘ��ҊO��������t�����_�E�����[�h���N�G�X�g  
        regClass(WEB3AdminFeqOpenAtOrderDownloadRequest.class);
        
        //�Ǘ��ҊO��������t�����_�E�����[�h���X�|���X  
        regClass(WEB3AdminFeqOpenAtOrderDownloadResponse.class);
        
        //�Ǘ��ҊO��������t�����_�E�����[�h���̓��N�G�X�g    
        regClass(WEB3AdminFeqOpenAtOrderDownloadInputRequest.class);
        
        //�Ǘ��ҊO��������t�����_�E�����[�h���̓��X�|���X    
        regClass(WEB3AdminFeqOpenAtOrderDownloadInputResponse.class);
        
        //�Ǘ��ҊO���������n�萔���o�^�m�F���N�G�X�g   
        regClass(WEB3AdminFeqForeignCostRegistConfirmRequest.class);
        
        //�Ǘ��ҊO���������n�萔���o�^�m�F���X�|���X   
        regClass(WEB3AdminFeqForeignCostRegistConfirmResponse.class);
        
        //�Ǘ��ҊO���������n�萔���o�^�������N�G�X�g   
        regClass(WEB3AdminFeqForeignCostRegistCompleteRequest.class);
        
        //�Ǘ��ҊO���������n�萔���o�^�������X�|���X   
        regClass(WEB3AdminFeqForeignCostRegistCompleteResponse.class);
        
        //�Ǘ��ҊO���������n�萔���o�^���ʃ��N�G�X�g   
        regClass(WEB3AdminFeqForeignCostRegistCommonRequest.class);
        
        //�Ǘ��ҊO���������n�萔���o�^���̓��N�G�X�g   
        regClass(WEB3AdminFeqForeignCostRegistInputRequest.class);
        
        //�Ǘ��ҊO���������n�萔���o�^���̓��X�|���X   
        regClass(WEB3AdminFeqForeignCostRegistInputResponse.class);
        
        //�Ǘ��ҊO�������o���I���m�F���N�G�X�g  
        regClass(WEB3AdminFeqExecutionEndConfirmRequest.class);
        
        //�Ǘ��ҊO�������o���I���m�F���X�|���X  
        regClass(WEB3AdminFeqExecutionEndConfirmResponse.class);
        
        //�Ǘ��ҊO�������o���I���������N�G�X�g  
        regClass(WEB3AdminFeqExecutionEndCompleteRequest.class);
        
        //�Ǘ��ҊO�������o���I���������X�|���X  
        regClass(WEB3AdminFeqExecutionEndCompleteResponse.class);
        
        //�Ǘ��ҊO�������o���I�����ʃ��N�G�X�g  
        regClass(WEB3AdminFeqExecutionEndCommonRequest.class);
        
        //�Ǘ��ҊO�������o���I�����̓��N�G�X�g  
        regClass(WEB3AdminFeqExecutionEndInputRequest.class);
        
        //�Ǘ��ҊO�������o���I�����̓��X�|���X  
        regClass(WEB3AdminFeqExecutionEndInputResponse.class);
        
        //�Ǘ��ҊO�������o�����̓��N�G�X�g    
        regClass(WEB3AdminFeqExecutionInputRequest.class);
        
        //�Ǘ��ҊO�������o�����̓��X�|���X    
        regClass(WEB3AdminFeqExecutionInputResponse.class);
        
        //�Ǘ��ҊO�������o�����͊m�F���N�G�X�g  
        regClass(WEB3AdminFeqExecutionConfirmRequest.class);
        
        //�Ǘ��ҊO�������o�����͊m�F���X�|���X  
        regClass(WEB3AdminFeqExecutionConfirmResponse.class);
        
        //�Ǘ��ҊO�������o�����͊������N�G�X�g  
        regClass(WEB3AdminFeqExecutionCompleteRequest.class);
        
        //�Ǘ��ҊO�������o�����͊������X�|���X  
        regClass(WEB3AdminFeqExecutionCompleteResponse.class);
        
        //�Ǘ��ҊO�������o�����͋��ʃ��N�G�X�g  
        regClass(WEB3AdminFeqExecutionCommonRequest.class);
        
        //�Ǘ��ҊO�������o�����͌������N�G�X�g  
        regClass(WEB3AdminFeqExecutionSearchRequest.class);
        
        //�Ǘ��ҊO�������o�����͌������X�|���X  
        regClass(WEB3AdminFeqExecutionSearchResponse.class);
        
        //�Ǘ��ҊO�������o��������m�F���N�G�X�g    
        regClass(WEB3AdminFeqCancelExecutionConfirmRequest.class);
        
        //�Ǘ��ҊO�������o��������m�F���X�|���X    
        regClass(WEB3AdminFeqCancelExecutionConfirmResponse.class);
        
        //�Ǘ��ҊO�������o��������������N�G�X�g    
        regClass(WEB3AdminFeqCancelExecutionCompleteRequest.class);
        
        //�Ǘ��ҊO�������o��������������X�|���X    
        regClass(WEB3AdminFeqCancelExecutionCompleteResponse.class);
        
        //�Ǘ��ҊO�������o����������̓��N�G�X�g    
        regClass(WEB3AdminFeqCancelExecutionInputRequest.class);
        
        //�Ǘ��ҊO�������o����������̓��X�|���X    
        regClass(WEB3AdminFeqCancelExecutionInputResponse.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptResultUploadConfirmRequest.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���X�|���X  
        regClass(WEB3AdminFeqOrderAcceptResultUploadConfirmResponse.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h�������N�G�X�g
        regClass(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h�������X�|���X  
        regClass(WEB3AdminFeqOrderAcceptResultUploadCompleteResponse.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h���~���N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptResultUploadStopRequest.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h���~���X�|���X  
        regClass(WEB3AdminFeqOrderAcceptResultUploadStopResponse.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h���̓��N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptResultUploadInputRequest.class);
        
        //�Ǘ��ҊO������������t���ʃA�b�v���[�h���̓��X�|���X  
        regClass(WEB3AdminFeqOrderAcceptResultUploadInputResponse.class);
        
        //�Ǘ��ҊO������������t����F�؊������N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptCancelCompleteRequest.class);
        
        //�Ǘ��ҊO������������t����F�؊������X�|���X  
        regClass(WEB3AdminFeqOrderAcceptCancelCompleteResponse.class);
        
        //�Ǘ��ҊO������������t����F�؋��ʃ��N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptCancelCertificationRequest.class);
        
        //�Ǘ��ҊO������������t����F�،����������̓��N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest.class);
        
        //�Ǘ��ҊO������������t����F�،����������̓��X�|���X  
        regClass(WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse.class);
        
        //�Ǘ��ҊO������������t����F�ؓ��̓��N�G�X�g  
        regClass(WEB3AdminFeqOrderAcceptCancelInputRequest.class);
        
        //�Ǘ��ҊO������������t����F�ؓ��̓��X�|���X  
        regClass(WEB3AdminFeqOrderAcceptCancelInputResponse.class);
        
        //�Ǘ��ҊO���������������`�[�ꗗ���N�G�X�g    
        regClass(WEB3AdminFeqOrderVoucherListRequest.class);
        
        //�Ǘ��ҊO���������������`�[�ꗗ���X�|���X    
        regClass(WEB3AdminFeqOrderVoucherListResponse.class);
        
        //�Ǘ��ҊO���������������`�[�ꗗ���̓��N�G�X�g  
        regClass(WEB3AdminFeqOrderVoucherListInputRequest.class);

        //�Ǘ��ҊO���������������`�[�ꗗ���̓��X�|���X  
        regClass(WEB3AdminFeqOrderVoucherListInputResponse.class);
        
        //�Ǘ��ҊO��������������۰�޴װ�����m�F���N�G�X�g   
        regClass(WEB3AdminFeqUploadErrCancelConfirmRequest.class);
        
        //�Ǘ��ҊO��������������۰�޴װ�����m�F���X�|���X   
        regClass(WEB3AdminFeqUploadErrCancelConfirmResponse.class);
        
        //�Ǘ��ҊO��������������۰�޴װ�����������N�G�X�g   
        regClass(WEB3AdminFeqUploadErrCancelCompleteRequest.class);
        
        //�Ǘ��ҊO��������������۰�޴װ�����������X�|���X   
        regClass(WEB3AdminFeqUploadErrCancelCompleteResponse.class);
        
        //�Ǘ��ҊO��������������۰�޴װ�������̓��N�G�X�g   
        regClass(WEB3AdminFeqUploadErrCancelInputRequest.class);
        
        //�Ǘ��ҊO��������������۰�޴װ�������̓��X�|���X   
        regClass(WEB3AdminFeqUploadErrCancelInputResponse.class);
        
        //�Ǘ��ҊO�������������ꗗ���N�G�X�g  
        regClass(WEB3AdminFeqProductListRequest.class);
        
        //�Ǘ��ҊO�������������ꗗ���X�|���X  
        regClass(WEB3AdminFeqProductListResponse.class);
        
        //�Ǘ��ҊO�������������X�V�m�F���N�G�X�g    
        regClass(WEB3AdminFeqProductInformationUpdateConfirmRequest.class);
        
        //�Ǘ��ҊO�������������X�V�m�F���X�|���X    
        regClass(WEB3AdminFeqProductInformationUpdateConfirmResponse.class);
        
        //�Ǘ��ҊO�������������X�V�������N�G�X�g    
        regClass(WEB3AdminFeqProductInformationUpdateCompleteRequest.class);
        
        //�Ǘ��ҊO�������������X�V�������X�|���X    
        regClass(WEB3AdminFeqProductInformationUpdateCompleteResponse.class);   
        
        //�Ǘ��ҊO�������������X�V���ʃ��N�G�X�g    
        regClass(WEB3AdminFeqProductInfomationUpdateCommonRequest.class);
        
        //�Ǘ��ҊO�������������X�V���̓��N�G�X�g    
        regClass(WEB3AdminFeqProductInformationUpdateInputRequest.class);
        
        //�Ǘ��ҊO�������������X�V���̓��X�|���X    
        regClass(WEB3AdminFeqProductInformationUpdateInputResponse.class);
        
        //�Ǘ��ҊO�������������X�V���ד��̓��N�G�X�g  
        regClass(WEB3AdminFeqProductInformationUpdateDetailsInputRequest.class);
        
        //�Ǘ��ҊO�������������X�V���ד��̓��X�|���X  
        regClass(WEB3AdminFeqProductInformationUpdateDetailsInputResponse.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h�m�F���N�G�X�g    
        regClass(WEB3AdminFeqExecuteResultUploadConfirmRequest.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h�m�F���X�|���X    
        regClass(WEB3AdminFeqExecuteResultUploadConfirmResponse.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h�������N�G�X�g    
        regClass(WEB3AdminFeqExecuteResultUploadCompleteRequest.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h�������X�|���X    
        regClass(WEB3AdminFeqExecuteResultUploadCompleteResponse.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h���~���N�G�X�g    
        regClass(WEB3AdminFeqExecuteResultUploadStopRequest.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h���~���X�|���X    
        regClass(WEB3AdminFeqExecuteResultUploadStopResponse.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h���̓��N�G�X�g    
        regClass(WEB3AdminFeqExecuteResultUploadInputRequest.class);
        
        //�Ǘ��ҊO��������茋�ʃA�b�v���[�h���̓��X�|���X    
        regClass(WEB3AdminFeqExecuteResultUploadInputResponse.class);
        
        //�Ǘ��ҊO�����������̓��N�G�X�g    
        regClass(WEB3AdminFeqOrderAndExecutionInputRequest.class);
        
        //�Ǘ��ҊO�����������̓��X�|���X    
        regClass(WEB3AdminFeqOrderAndExecutionInputResponse.class);
        
        //�Ǘ��ҊO�����������͊m�F���N�G�X�g  
        regClass(WEB3AdminFeqOrderAndExecutionConfirmRequest.class);
        
        //�Ǘ��ҊO�����������͊m�F���X�|���X  
        regClass(WEB3AdminFeqOrderAndExecutionConfirmResponse.class);
        
        //�Ǘ��ҊO�����������͊������N�G�X�g  
        regClass(WEB3AdminFeqOrderAndExecutionCompleteRequest.class);
        
        //�Ǘ��ҊO�����������͊������X�|���X  
        regClass(WEB3AdminFeqOrderAndExecutionCompleteResponse.class);
        
        //�Ǘ��ҊO�����������͋��ʃ��N�G�X�g  
        regClass(WEB3AdminFeqOrderAndExecutionCommonRequest.class);
        
        //�Ǘ��ҊO�����������͌������N�G�X�g  
        regClass(WEB3AdminFeqOrderAndExecutionSearchRequest.class);
        
        //�Ǘ��ҊO�����������͌������X�|���X  
        regClass(WEB3AdminFeqOrderAndExecutionSearchResponse.class);
        
        //�O������������t�o���ʒm���N�G�X�g
        regClass(WEB3FeqOrderAcceptExecNotifyRequest.class);
        
        //�O������������t�o���ʒm���X�|���X
        regClass(WEB3FeqOrderAcceptExecNotifyResponse.class);
        
        //�Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���N�G�X�g
        regClass(WEB3AdminFeqRcvdQueueReferenceRequest.class);
        
        //�Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���X�|���X
        regClass(WEB3AdminFeqRcvdQueueReferenceResponse.class);
        
        //�Ǘ��ҊO������RCVD�L���[�Ɖ���̓��N�G�X�g
        regClass(WEB3AdminFeqRcvdQueueReferenceInputRequest.class);
        
        //�Ǘ��ҊO������RCVD�L���[�Ɖ���̓��X�|���X
        regClass(WEB3AdminFeqRcvdQueueReferenceInputResponse.class);
        
        //�Ǘ��ҊO������SEND�L���[���J�o���������N�G�X�g
        regClass(WEB3AdminFeqSendQueueRecoveryCompleteRequest.class);
        
        //�Ǘ��ҊO������SEND�L���[���J�o���������X�|���X
        regClass(WEB3AdminFeqSendQueueRecoveryCompleteResponse.class);
        
        //�Ǘ��ҊO������SEND�L���[�Ɖ�ꗗ���N�G�X�g
        regClass(WEB3AdminFeqSendQueueReferenceRequest.class);
        
        //�Ǘ��ҊO������SEND�L���[�Ɖ�ꗗ���X�|���X
        regClass(WEB3AdminFeqSendQueueReferenceResponse.class);
        
        //�Ǘ��ҊO������SEND�L���[�Ɖ���̓��N�G�X�g
        regClass(WEB3AdminFeqSendQueueReferenceInputRequest.class);
        
        //�Ǘ��ҊO������SEND�L���[�Ɖ���̓��X�|���X
        regClass(WEB3AdminFeqSendQueueReferenceInputResponse.class);
        
        //�Ǘ��ҊO�������s��A���敪�ύX���̓��N�G�X�g
        regClass(WEB3AdminFeqMarketLinkChangeInputRequest.class);
        
        //�Ǘ��ҊO�������s��A���敪�ύX���̓��X�|���X
        regClass(WEB3AdminFeqMarketLinkChangeInputResponse.class);
        
        //�Ǘ��ҊO�������s��A���敪�ύX�m�F���N�G�X�g
        regClass(WEB3AdminFeqMarketLinkChangeConfirmRequest.class);
        
        //�Ǘ��ҊO�������s��A���敪�ύX�m�F���X�|���X
        regClass(WEB3AdminFeqMarketLinkChangeConfirmResponse.class);
        
        //�Ǘ��ҊO�������s��A���敪�ύX�������N�G�X�g
        regClass(WEB3AdminFeqMarketLinkChangeCompleteRequest.class);
        
        //�Ǘ��ҊO�������s��A���敪�ύX�������X�|���X
        regClass(WEB3AdminFeqMarketLinkChangeCompleteResponse.class);

        //�O�������뉿�P���o�^�m�F���N�G�X�g
        regClass(WEB3FeqBookPriceConfirmRequest.class);

        //�O�������뉿�P���o�^�m�F���X�|���X
        regClass(WEB3FeqBookPriceConfirmResponse.class);

        //�O�������בփl�b�e�B���O���N�G�X�g
        regClass(WEB3FeqNettingExchangeRequest.class);

        //�O�������בփl�b�e�B���O���X�|���X
        regClass(WEB3FeqNettingExchangeResponse.class);
    }

    /**
     * Handler �̓o�^����
     * @@throws Exception
     */
    private static void plugHandlers() throws Exception
    {
        //�O�������J�����_�[�o�^�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarSearchCondInputRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "getQueryCondInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarRegistInputRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarRegistConfirmRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "validateRegist");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarRegistCompleteRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "submitRegist");
        
        //�O�������ב֓o�^�n���h��    
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExchangeRegistInputRequest.class,
            WEB3AdminFeqExchangeRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExchangeRegistConfirmRequest.class,
            WEB3AdminFeqExchangeRegistHandler.class,
            "validateRateRegist");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExchangeRegistCompleteRequest.class,
            WEB3AdminFeqExchangeRegistHandler.class,
            "submitRateRegist");
        
        //�O��������t����DL�n���h��   
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOpenAtOrderDownloadInputRequest.class,
            WEB3AdminFeqOpenAtOrderDLHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOpenAtOrderDownloadRequest.class,
            WEB3AdminFeqOpenAtOrderDLHandler.class,
            "getDownloadFile");
        
        //�O���������n�萔���o�^�n���h��  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqForeignCostRegistInputRequest.class,
            WEB3AdminFeqForeignCostRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqForeignCostRegistConfirmRequest.class,
            WEB3AdminFeqForeignCostRegistHandler.class,
            "validateRegist");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqForeignCostRegistCompleteRequest.class,
            WEB3AdminFeqForeignCostRegistHandler.class,
            "submitRegist");
        
        //�O�������o���I���n���h��  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionEndInputRequest.class,
            WEB3AdminFeqExecutionEndHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionEndConfirmRequest.class,
            WEB3AdminFeqExecutionEndHandler.class,
            "validateExecEnd");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionEndCompleteRequest.class,
            WEB3AdminFeqExecutionEndHandler.class,
            "submitExecEnd");
        //�Ǘ��ҊO�������o�����̓n���h��  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionSearchRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "getQueryScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionInputRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionConfirmRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "validateExec");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionCompleteRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "submitExec");
        
        //�Ǘ��ҊO�������o��������n���h��           
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCancelExecutionInputRequest.class,
            WEB3AdminFeqCancelExecutionHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCancelExecutionConfirmRequest.class,
            WEB3AdminFeqCancelExecutionHandler.class,
            "validateCancel");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCancelExecutionCompleteRequest.class,
            WEB3AdminFeqCancelExecutionHandler.class,
            "submitCancel");
        
        //�Ǘ��ҊO������������t���ʱ���۰�ރn���h��           
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadInputRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "getUploadScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadConfirmRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "validateUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "submitUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadStopRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "undoUpload");
        //�Ǘ��ҊO������������t����F�؃n���h��        
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest.class,
            WEB3AdminFeqOrderAcceptHandler.class,
            "getQueryScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptCancelInputRequest.class,
            WEB3AdminFeqOrderAcceptHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptCancelCompleteRequest.class,
            WEB3AdminFeqOrderAcceptHandler.class,
            "submitAccept");
        
        //�Ǘ��ҊO���������������`�[�ꗗ�n���h��   
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderVoucherListInputRequest.class,
            WEB3AdminFeqOrderVoucherListHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderVoucherListRequest.class,
            WEB3AdminFeqOrderVoucherListHandler.class,
            "getListScreen");
        
        //�Ǘ��ҊO��������������۰�޴װ�����n���h��  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqUploadErrCancelInputRequest.class,
            WEB3AdminFeqUploadErrCancelHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqUploadErrCancelConfirmRequest.class,
            WEB3AdminFeqUploadErrCancelHandler.class,
            "validateRelease");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqUploadErrCancelCompleteRequest.class,
            WEB3AdminFeqUploadErrCancelHandler.class,
            "submitRelease");
        
        //�Ǘ��ҊO�����������ꗗ�n���h�� 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductListRequest.class,
            WEB3AdminFeqProductListHandler.class,
            "getListScreen");
        //�O�������������X�V�n���h�� 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateInputRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateDetailsInputRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "getDetailInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateConfirmRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "validateUpdate");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateCompleteRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "submitUpdate");
        //�Ǘ��ҊO��������茋�ʱ���۰�ރn���h��   
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadInputRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "getUploadScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadConfirmRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "validateUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadCompleteRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "submitUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadStopRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "undoUpload");
        
        //�Ǘ��ҊO�����������̓n���h��     
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionSearchRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "getQueryScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionInputRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionConfirmRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "validateExec");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionCompleteRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "submitExec");
        
        //�O�������c���Ɖ�n���h��    
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBalanceReferenceRequest.class,
            WEB3FeqBalanceReferenceHandler.class,
            "getBalanceReference");
        
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBalanceReferenceTotalRequest.class,
            WEB3FeqBalanceReferenceHandler.class,
            "getBalanceTotal");
        
        //�O����������n���h��     
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqCancelConfirmRequest.class,
            WEB3FeqCancelHandler.class,
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqCancelCompleteRequest.class,
            WEB3FeqCancelHandler.class,
            "submitOrder");
        //�O�����������J�z�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqOrderTransferRequest.class,
            WEB3FeqOrderCarryOverHandler.class,
            "submitOrderCarryOver");
        //�O�������������Ɖ�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqExecuteReferenceRequest.class,
            WEB3FeqExecuteReferenceHandler.class,
            "getOrderExecuteReference");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqExecuteDetailsRequest.class,
            WEB3FeqExecuteReferenceHandler.class,
            "getOrderExecuteDetails");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqOrderHistoryRequest.class,
            WEB3FeqExecuteReferenceHandler.class,
            "getOrderExecuteAction");
        
        //�O�����������n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqChangeInputRequest.class,
            WEB3FeqChangeHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqChangeConfirmRequest.class,
            WEB3FeqChangeHandler.class,
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqChangeCompleteRequest.class,
            WEB3FeqChangeHandler.class,
            "submitOrder");
        
        //�O���������t�n���h��
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyInputRequest.class, 
            WEB3FeqBuyHandler.class, 
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyConfirmRequest.class, 
            WEB3FeqBuyHandler.class, 
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyCompleteRequest.class, 
            WEB3FeqBuyHandler.class, 
            "submitOrder");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyProductSelectRequest.class, 
            WEB3FeqBuyHandler.class, 
            "getProductSelectScreen");
        
        //�O���������t�n���h��
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqSellInputRequest.class, 
            WEB3FeqSellHandler.class, 
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqSellConfirmRequest.class, 
            WEB3FeqSellHandler.class, 
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqSellCompleteRequest.class, 
            WEB3FeqSellHandler.class, 
            "submitOrder");
        
        //�O�������뉿�P���o�^�n���h��  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBookPriceInputRequest.class,
            WEB3FeqBookValuePriceRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBookPriceRegistRequest.class,
            WEB3FeqBookValuePriceRegistHandler.class,
            "submitBookValuePrice");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBookPriceConfirmRequest.class,
            WEB3FeqBookValuePriceRegistHandler.class,
            "validateBookValuePrice");
        
        //�O�����������ꗗ�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqProductListRequest.class,
            WEB3FeqProductListHandler.class,
            "getProductInformationList");
        
        //�O������������t�o���ʒm�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqOrderAcceptExecNotifyRequest.class,
            WEB3FeqOrderAcceptExecutionNotifyHandler.class,
            "orderAcceptExecNotify");
        
        //�Ǘ��ҊO������RCVD�L���[�Ɖ�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqRcvdQueueReferenceInputRequest.class,
            WEB3AdminFeqRcvdQueueReferenceHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqRcvdQueueReferenceRequest.class,
            WEB3AdminFeqRcvdQueueReferenceHandler.class,
            "getListScreen");
        
        //�Ǘ��ҊO������SEND�L���[���J�o���n���h�� 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqSendQueueRecoveryCompleteRequest.class,
            WEB3AdminFeqSendQueueRecoveryHandler.class,
            "submitUpdate");
        
        //�Ǘ��ҊO������SEND�L���[�Ɖ�n���h�� 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqSendQueueReferenceInputRequest.class,
            WEB3AdminFeqSendQueueReferenceHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqSendQueueReferenceRequest.class,
            WEB3AdminFeqSendQueueReferenceHandler.class,
            "getListScreen");
        
        //�Ǘ��ҊO�������s��A���敪�ύX�n���h�� 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqMarketLinkChangeInputRequest.class,
            WEB3AdminFeqMarketLinkChangeHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqMarketLinkChangeConfirmRequest.class,
            WEB3AdminFeqMarketLinkChangeHandler.class,
            "validateChange");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqMarketLinkChangeCompleteRequest.class,
            WEB3AdminFeqMarketLinkChangeHandler.class,
            "submitChange");

        //�O�������בփl�b�e�B���O�n���h��
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqNettingExchangeRequest.class,
            WEB3FeqNettingExchangeHandler.class,
            "nettingExchange");
    }
}
@
