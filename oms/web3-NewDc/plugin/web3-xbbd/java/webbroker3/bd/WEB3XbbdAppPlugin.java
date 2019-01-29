head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3XbbdAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Xbbd �v���O�C���N���X(WEB3XbbdAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23  �����@@(���u) �V�K�쐬
Revesion History : 2007/07/13  �đo�g�@@(���u)�@@���f��No.193
Revesion History : 2007/07/17 �Ӑ� (���u) �d�l�ύX�E���f��208
Revesion History : 2007/08/03 ���g (���u) �d�l�ύX�E���f��No.214,No.217,No.224,No.225,No.226
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondAccountDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondMasterDatabaseExtensions;

import webbroker3.bd.data.WEB3BondMasterDatabaseExtensions;
import webbroker3.bd.handler.WEB3AdminBondDomesticProductListHandler;
import webbroker3.bd.handler.WEB3AdminBondDomesticProductRegistHandler;
import webbroker3.bd.handler.WEB3AdminBondDomesticRecruitLimitManageHandler;
import webbroker3.bd.handler.WEB3AdminBondExecuteCancelHandler;
import webbroker3.bd.handler.WEB3AdminBondExecuteChangeHandler;
import webbroker3.bd.handler.WEB3AdminBondOrderAndExecuteHandler;
import webbroker3.bd.handler.WEB3AdminBondOrderLockStatusUpdateHandler;
import webbroker3.bd.handler.WEB3AdminBondOrderReceiveHistoryHandler;
import webbroker3.bd.handler.WEB3AdminBondProductListHandler;
import webbroker3.bd.handler.WEB3AdminBondProductRegisterHandler;
import webbroker3.bd.handler.WEB3BondAutoExecuteHandler;
import webbroker3.bd.handler.WEB3BondBalanceReferenceHandler;
import webbroker3.bd.handler.WEB3BondCancelHandler;
import webbroker3.bd.handler.WEB3BondDomesticApplyHandler;
import webbroker3.bd.handler.WEB3BondDomesticApplyInputHandler;
import webbroker3.bd.handler.WEB3BondDomesticApplyProductListHandler;
import webbroker3.bd.handler.WEB3BondExecuteReferenceHandler;
import webbroker3.bd.handler.WEB3BondRecruitBuyHandler;
import webbroker3.bd.handler.WEB3BondRecruitBuyInputHandler;
import webbroker3.bd.handler.WEB3BondRecruitBuyProductListHandler;
import webbroker3.bd.handler.WEB3BondSellHandler;
import webbroker3.bd.handler.WEB3BondSellInputHandler;
import webbroker3.bd.marketadaptor.WEB3XbbdMarketAdaptorAppPlugin;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCommonRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCommonResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputResponse;
import webbroker3.bd.message.WEB3AdminBondExecCalculateRequest;
import webbroker3.bd.message.WEB3AdminBondExecCalculateResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeCommonRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputResponse;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecInputCommonRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockRequest;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockResponse;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistCommonRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchListRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchListResponse;
import webbroker3.bd.message.WEB3BondApplyBuyCommonRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmRequest;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListResponse;
import webbroker3.bd.message.WEB3BondAutoExecRequest;
import webbroker3.bd.message.WEB3BondAutoExecResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelCompleteResponse;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.message.WEB3BondSellCompleteRequest;
import webbroker3.bd.message.WEB3BondSellCompleteResponse;
import webbroker3.bd.message.WEB3BondSellConfirmRequest;
import webbroker3.bd.message.WEB3BondSellConfirmResponse;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductListService;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductRegistService;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticRecruitLimitManageService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteCancelService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteChangeService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderAndExecuteService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderLockStatusUpdateService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderReceiveHistoryService;
import webbroker3.bd.service.delegate.WEB3AdminBondProductListService;
import webbroker3.bd.service.delegate.WEB3AdminBondProductRegisterService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteUnitService;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.bd.service.delegate.WEB3BondCancelService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyProductListService;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyService;
import webbroker3.bd.service.delegate.WEB3BondExecuteReferenceService;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyInputService;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyProductListService;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyService;
import webbroker3.bd.service.delegate.WEB3BondSellInputService;
import webbroker3.bd.service.delegate.WEB3BondSellService;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductRegistServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticRecruitLimitManageServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteCancelServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteChangeServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteNotifyServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderAndExecuteServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderLockStatusUpdateServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderReceiveHistoryServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondProductRegisterServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondAutoExecuteServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondAutoExecuteUnitServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondBalanceReferenceServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondCancelServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDataManagerServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyInputServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondExecuteReferenceServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyInputServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondSellInputServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondSellServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Xbbd �v���O�C���N���X�B
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public final class WEB3XbbdAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3XbbdAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3XbbdAppPlugin()
    {

    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3XbbdAppPlugin.class);

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
            l_finApp.uninstallTradingModule("xb-bd-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        try
        {
            log.info("Installing TradingModule : web3-xbbd");
            l_finApp.installTradingModule(new WEB3XbbdTradingModule());
            log.info("Installed TradingModule : web3-xbbd");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.BOND);

        //���v�Z�T�[�r�X 
        l_tradingModule.overrideBizLogicProvider(new WEB3BondBizLogicProvider());

        //�g���������}�l�[�W�� 
        l_tradingModule.overrideOrderManager(new WEB3BondOrderManager());

        //���v���_�N�g�}�l�[�W�� 
        l_tradingModule.overrideProductManager(new WEB3BondProductManager());

        //���|�W�V�����}�l�[�W�� 
        l_tradingModule.overridePositionManager(new WEB3BondPositionManager());

        //�����R���ʃ`�F�b�N
        WEB3BondOrderManagerReusableValidationsCheck.register();
        
        // Webbroker3-Xbbd-MarketAdaptor �v���O�C��
        WEB3XbbdMarketAdaptorAppPlugin.plug();
        
        // DatabaseExtensions �̃v���O�C������ ----------------------
        BondMasterDatabaseExtensions.plug();
        BondAccountDatabaseExtensions.plug();
        WEB3BondMasterDatabaseExtensions.plug();
        
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

        log.exiting(METHOD_NAME);
    }
    
    /**
     * Service �̓o�^����
     * @@throws Exception
     */
    private static void plugServices() throws Exception
    {
        //==============�Ǘ��҂̃T�[�r�X=======================
        
        //���f�[�^�}�l�[�W���[�T�[�r�X 
        Services.registerService(WEB3BondDataManagerService.class,
            new WEB3BondDataManagerServiceImpl());
        
        //���Ǘ��҃w���p�[�T�[�r�X
        Services.registerService(WEB3AdminBondHelperService.class,
            new WEB3AdminBondHelperServiceImpl());
        
        //�Ǘ��ҍ�������T�[�r�X 
        Services.registerService(WEB3AdminBondExecuteCancelService.class,
            new WEB3AdminBondExecuteCancelServiceImpl());
        
        //�Ǘ��Җ��ύX�T�[�r�X
        Services.registerService(WEB3AdminBondExecuteChangeService.class,
            new WEB3AdminBondExecuteChangeServiceImpl());
        
        //�Ǘ��ҐV�K�����̓T�[�r�X
        Services.registerService(WEB3AdminBondOrderAndExecuteService.class,
            new WEB3AdminBondOrderAndExecuteServiceImpl());
        
        //���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X 
        Services.registerService(WEB3AdminBondOrderLockStatusUpdateService.class,
            new WEB3AdminBondOrderLockStatusUpdateServiceImpl());
      
        //�Ǘ��ҍ������ꗗ�T�[�r�X
        Services.registerService(WEB3AdminBondProductListService.class,
            new WEB3AdminBondProductListServiceImpl());
        
        //�Ǘ��ҍ������o�^�T�[�r�X
        Services.registerService(WEB3AdminBondProductRegisterService.class,
            new WEB3AdminBondProductRegisterServiceImpl());
        
        //�����ʒm�T�[�r�X 
        Services.registerService(WEB3AdminBondExecuteNotifyService.class,
            new WEB3AdminBondExecuteNotifyServiceImpl());
        
        //������/���t���̓T�[�r�X
        Services.registerService(WEB3BondRecruitBuyInputService.class,
            new WEB3BondRecruitBuyInputServiceImpl());
        
        //������/���t�T�[�r�X
        Services.registerService(WEB3BondRecruitBuyService.class,
            new WEB3BondRecruitBuyServiceImpl());
        
        //������/���t�����ꗗ�T�[�r�X
        Services.registerService(WEB3BondRecruitBuyProductListService.class,
            new WEB3BondRecruitBuyProductListServiceImpl());
        
        //�����p���̓T�[�r�X
        Services.registerService(WEB3BondSellInputService.class,
            new WEB3BondSellInputServiceImpl());
        
        //�����p�T�[�r�X
        Services.registerService(WEB3BondSellService.class,
            new WEB3BondSellServiceImpl());        
        
        //������T�[�r�X
        Services.registerService(WEB3BondCancelService.class,
            new WEB3BondCancelServiceImpl());
        
        //���������Ɖ�T�[�r�X
        Services.registerService(WEB3BondExecuteReferenceService.class,
            new WEB3BondExecuteReferenceServiceImpl());
        
        //���c���Ɖ�T�[�r�X
        Services.registerService(WEB3BondBalanceReferenceService.class,
            new WEB3BondBalanceReferenceServiceImpl());
        
        //���������T�[�r�X
        Services.registerService(WEB3BondAutoExecuteService.class,
            new WEB3BondAutoExecuteServiceImpl());
        
        //���������Unit�T�[�r�X
        Services.registerService(WEB3BondAutoExecuteUnitService.class,
            new WEB3BondAutoExecuteUnitServiceImpl());

        //�Ǘ��ҍ����������o�^�T�[�r�X
        Services.registerService(WEB3AdminBondDomesticProductRegistService.class,
            new WEB3AdminBondDomesticProductRegistServiceImpl());

        //�Ǘ��ҍ����������ꗗ�T�[�r�X
        Services.registerService(WEB3AdminBondDomesticProductListService.class,
            new WEB3AdminBondDomesticProductListServiceImpl());

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X
        Services.registerService(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new WEB3AdminBondDomesticRecruitLimitManageServiceImpl());

        //�Ǘ��Ғ�����t�����Ɖ�T�[�r�X
        Services.registerService(WEB3AdminBondOrderReceiveHistoryService.class,
            new WEB3AdminBondOrderReceiveHistoryServiceImpl());

        //��������������ꗗ�T�[�r�X
        Services.registerService(WEB3BondDomesticApplyProductListService.class,
            new WEB3BondDomesticApplyProductListServiceImpl());

        //������������̓T�[�r�X
        Services.registerService(WEB3BondDomesticApplyInputService.class,
            new WEB3BondDomesticApplyInputServiceImpl());

        //����������T�[�r�X
        Services.registerService(WEB3BondDomesticApplyService.class,
            new WEB3BondDomesticApplyServiceImpl());
    }

    /**
     * Service �� Interceptor �ݒ菈�� <BR>
     * �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
     * @@throws Exception
     */
    private static void plugLogSysTimeInterceptors() throws Exception
    {
        //==============�Ǘ��҂̃T�[�r�X=======================
        
        //���f�[�^�}�l�[�W���[�T�[�r�X 
        Services.addInterceptor(WEB3BondDataManagerService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���Ǘ��҃w���p�[�T�[�r�X
        Services.addInterceptor(WEB3AdminBondHelperService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��ҍ�������T�[�r�X 
        Services.addInterceptor(WEB3AdminBondExecuteCancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��Җ��ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminBondExecuteChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��ҐV�K�����̓T�[�r�X
        Services.addInterceptor(WEB3AdminBondOrderAndExecuteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X 
        Services.addInterceptor(WEB3AdminBondOrderLockStatusUpdateService.class,
            new WEB3LogSysTimeInterceptor());
      
        //�Ǘ��ҍ������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminBondProductListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��ҍ������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminBondProductRegisterService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����ʒm�T�[�r�X 
        Services.addInterceptor(WEB3AdminBondExecuteNotifyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //������/���t���̓T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //������/���t�T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //������/���t�����ꗗ�T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyProductListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����p���̓T�[�r�X
        Services.addInterceptor(WEB3BondSellInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����p�T�[�r�X
        Services.addInterceptor(WEB3BondSellService.class,
            new WEB3LogSysTimeInterceptor());
        
        //������T�[�r�X
        Services.addInterceptor(WEB3BondCancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���������Ɖ�T�[�r�X
        Services.addInterceptor(WEB3BondExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���c���Ɖ�T�[�r�X
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���������T�[�r�X
        Services.addInterceptor(WEB3BondAutoExecuteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���������Unit�T�[�r�X 
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҍ����������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticProductRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҍ����������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticProductListService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ғ�����t�����Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminBondOrderReceiveHistoryService.class,
            new WEB3LogSysTimeInterceptor());

        //��������������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyProductListService.class,
            new WEB3LogSysTimeInterceptor());

        //������������̓T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyInputService.class,
            new WEB3LogSysTimeInterceptor());

        //����������T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyService.class,
            new WEB3LogSysTimeInterceptor());
    }

    /**
     * Service �� Interceptor �ݒ菈�� <BR>
     * ServiceInterceptor�ݒ�
     * @@throws Exception
     */
    private static void plugServiceInterceptors() throws Exception
    {
        //==============�Ǘ��҂̃T�[�r�X=======================
        
        //�Ǘ��ҍ�������T�[�r�X 
        Services.addInterceptor(WEB3AdminBondExecuteCancelService.class,
            new WEB3AdminBondExecuteCancelServiceInterceptor());
        
        //�Ǘ��Җ��ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminBondExecuteChangeService.class,
            new WEB3AdminBondExecuteChangeInterceptor());
        
        //�Ǘ��ҐV�K�����̓T�[�r�X
        Services.addInterceptor(WEB3AdminBondOrderAndExecuteService.class,
            new WEB3AdminBondOrderAndExecuteInterceptor());
        
        //���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X 
        Services.addInterceptor(WEB3AdminBondOrderLockStatusUpdateService.class,
            new WEB3AdminBondOrderLockStatusUpdateInterceptor());
        
        //�Ǘ��ҍ������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminBondProductRegisterService.class,
            new WEB3AdminBondProductRegisterInterceptor());
        
        //������/���t���̓T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyInputService.class,
            new WEB3BondRecruitBuyServiceInterceptor());
        
        //������/���t�T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyService.class,
            new WEB3BondRecruitBuyServiceInterceptor());
        
        //������/���t�����ꗗ�T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyProductListService.class,
            new WEB3BondRecruitBuyProductListServiceInterceptor());
        
        //�����p���̓T�[�r�X
        Services.addInterceptor(WEB3BondSellInputService.class,
            new WEB3BondSellInputServiceInterceptor());
        
        //�����p�T�[�r�X
        Services.addInterceptor(WEB3BondSellService.class,
            new WEB3BondSellServiceInterceptor());
        
        //������T�[�r�X
        Services.addInterceptor(WEB3BondCancelService.class,
            new WEB3BondCancelServiceInterceptor());
        
        //���������Ɖ�T�[�r�X
        Services.addInterceptor(WEB3BondExecuteReferenceService.class,
            new WEB3BondExecuteReferenceServiceInterceptor());
        
        //���c���Ɖ�T�[�r�X
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new WEB3BondBalanceReferenceServiceInterceptor());
        
        //���������T�[�r�X
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new WEB3BondDescendRacCtxInterceptor());
        
        //���������Unit�T�[�r�X 
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new WEB3BondAutoExecuteUnitServiceInterceptor());

        //�Ǘ��ҍ����������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticProductRegistService.class,
            new WEB3AdminBondDomesticProductRegistServiceInterceptor());

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new WEB3AdminBondDomesticRecruitLimitManageServiceInterceptor());

        //��������������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyProductListService.class,
            new WEB3BondDomesticApplyProductListServiceInterceptor());

        //������������̓T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyInputService.class,
            new WEB3BondDomesticApplyInputServiceInterceptor());

        //����������T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyService.class,
            new WEB3BondDomesticApplyServiceInterceptor());
    }

    /**
     * Service �� Interceptor �ݒ菈�� <BR>
     * �����g�����U�N�V�����ݒ�
     * @@throws Exception
     */
    private static void plugTransactionalInterceptors() throws Exception
    {
        //==============�Ǘ��҂̃T�[�r�X=======================
        
        //���f�[�^�}�l�[�W���[�T�[�r�X 
        Services.addInterceptor(WEB3BondDataManagerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���Ǘ��҃w���p�[�T�[�r�X
        Services.addInterceptor(WEB3AdminBondHelperService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҍ�������T�[�r�X 
        Services.addInterceptor(WEB3AdminBondExecuteCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��Җ��ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminBondExecuteChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҐV�K�����̓T�[�r�X
        Services.addInterceptor(WEB3AdminBondOrderAndExecuteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X 
        Services.addInterceptor(WEB3AdminBondOrderLockStatusUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
      
        //�Ǘ��ҍ������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminBondProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҍ������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminBondProductRegisterService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�����ʒm�T�[�r�X 
        Services.addInterceptor(WEB3AdminBondExecuteNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //������/���t���̓T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //������/���t�T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //������/���t�����ꗗ�T�[�r�X
        Services.addInterceptor(WEB3BondRecruitBuyProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�����p���̓T�[�r�X
        Services.addInterceptor(WEB3BondSellInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�����p�T�[�r�X
        Services.addInterceptor(WEB3BondSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //������T�[�r�X
        Services.addInterceptor(WEB3BondCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���������Ɖ�T�[�r�X
        Services.addInterceptor(WEB3BondExecuteReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���c���Ɖ�T�[�r�X
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���������T�[�r�X
        Services.addInterceptor(WEB3BondAutoExecuteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //���������Unit�T�[�r�X 
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҍ����������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticProductRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҍ����������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X
        Services.addInterceptor(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ғ�����t�����Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminBondOrderReceiveHistoryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //��������������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //������������̓T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //����������T�[�r�X
        Services.addInterceptor(WEB3BondDomesticApplyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    /**
     * Message �̓o�^����
     * @@throws Exception
     */
    private static void plugMessages() throws Exception
    {
        //==============�Ǘ��҃��b�Z�[�W=======================
        
        //�Ǘ��Җ�����m�F���N�G�X�g 
        regClass(WEB3AdminBondExecCancelConfirmRequest.class);
        //�Ǘ��Җ�����m�F���X�|���X 
        regClass(WEB3AdminBondExecCancelConfirmResponse.class);
        //�Ǘ��Җ�����������N�G�X�g 
        regClass(WEB3AdminBondExecCancelCompleteRequest.class);
        //�Ǘ��Җ�����������X�|���X 
        regClass(WEB3AdminBondExecCancelCompleteResponse.class);
        
        //�Ǘ��ҐV�K���m�F���N�G�X�g 
        regClass(WEB3AdminBondExecConfirmRequest.class);
        //�Ǘ��ҐV�K���m�F���X�|���X 
        regClass(WEB3AdminBondExecConfirmResponse.class);
        //�Ǘ��ҐV�K��芮�����N�G�X�g 
        regClass(WEB3AdminBondExecCompleteRequest.class);
        //�Ǘ��ҐV�K��芮�����X�|���X 
        regClass(WEB3AdminBondExecCompleteResponse.class);
        //��n����v�Z���N�G�X�g 
        regClass(WEB3AdminBondExecCalculateRequest.class);
        //��n����v�Z���X�|���X 
        regClass(WEB3AdminBondExecCalculateResponse.class);
        //�Ǘ��ҐV�K�����̓��N�G�X�g 
        regClass(WEB3AdminBondExecInputRequest.class);
        //�Ǘ��ҐV�K�����̓��X�|���X 
        regClass(WEB3AdminBondExecInputResponse.class);
        //�Ǘ��ҐV�K�����͋��ʃ��N�G�X�g 
        regClass(WEB3AdminBondExecInputCommonRequest.class);
        
        //�Ǘ��Җ��ύX�m�F���N�G�X�g 
        regClass(WEB3AdminBondExecChangeConfirmRequest.class);
        //�Ǘ��Җ��ύX�m�F���X�|���X 
        regClass(WEB3AdminBondExecChangeConfirmResponse.class);
        //�Ǘ��Җ��ύX�������N�G�X�g 
        regClass(WEB3AdminBondExecChangeCompleteRequest.class);
        //�Ǘ��Җ��ύX�������X�|���X 
        regClass(WEB3AdminBondExecChangeCompleteResponse.class);
        //�Ǘ��Җ��ύX���ʃ��N�G�X�g 
        regClass(WEB3AdminBondExecChangeCommonRequest.class);
        //�Ǘ��Җ��ύX���̓��N�G�X�g 
        regClass(WEB3AdminBondExecChangeInputRequest.class);
        //�Ǘ��Җ��ύX���̓��X�|���X 
        regClass(WEB3AdminBondExecChangeInputResponse.class);
        
        //�Ǘ��ҍ��������b�N�敪�X�V���N�G�X�g 
        regClass(WEB3AdminBondOrderLockUnlockRequest.class);
        //�Ǘ��ҍ��������b�N�敪�X�V���X�|���X 
        regClass(WEB3AdminBondOrderLockUnlockResponse.class);
        
        //�Ǘ��ҍ������ꗗ��ʕ\�����N�G�X�g 
        regClass(WEB3AdminBondProductSearchInputRequest.class);
        //�Ǘ��ҍ������ꗗ��ʕ\�����X�|���X 
        regClass(WEB3AdminBondProductSearchInputResponse.class);
        //�Ǘ��ҍ������ꗗ�������N�G�X�g 
        regClass(WEB3AdminBondProductSearchListRequest.class);
        //�Ǘ��ҍ������ꗗ�������X�|���X 
        regClass(WEB3AdminBondProductSearchListResponse.class);
        
        //�Ǘ��ҍ������o�^�m�F���N�G�X�g 
        regClass(WEB3AdminBondProductRegistConfirmRequest.class);
        //�Ǘ��ҍ������o�^�m�F���X�|���X 
        regClass(WEB3AdminBondProductRegistConfirmResponse.class);
        //�Ǘ��ҍ������o�^�������N�G�X�g 
        regClass(WEB3AdminBondProductRegistCompleteRequest.class);
        //�Ǘ��ҍ������o�^�������X�|���X 
        regClass(WEB3AdminBondProductRegistCompleteResponse.class);
        //�Ǘ��ҍ������o�^���ʃ��N�G�X�g 
        regClass(WEB3AdminBondProductRegistCommonRequest.class);
        //�Ǘ��ҍ������o�^���̓��N�G�X�g 
        regClass(WEB3AdminBondProductRegistInputRequest.class);
        //�Ǘ��ҍ������o�^���̓��X�|���X 
        regClass(WEB3AdminBondProductRegistInputResponse.class);
        
        //������/���t�����ꗗ���N�G�X�g
        regClass(WEB3BondApplyBuyProductListRequest.class);       
        //������/���t�����ꗗ���X�|���X
        regClass(WEB3BondApplyBuyProductListResponse.class);
        
        //������/���t���ʃ��N�G�X�g
        regClass(WEB3BondApplyBuyCommonRequest.class);
        //������/���t���̓��N�G�X�g
        regClass(WEB3BondApplyBuyInputRequest.class);
        //������/���t���̓��X�|���X
        regClass(WEB3BondApplyBuyInputResponse.class);
        //������/���t�m�F���N�G�X�g
        regClass(WEB3BondApplyBuyConfirmRequest.class);        
        //������/���t�m�F���X�|���X
        regClass(WEB3BondApplyBuyConfirmResponse.class);        
        //������/���t�������N�G�X�g
        regClass(WEB3BondApplyBuyCompleteRequest.class);
        //������/���t�������X�|���X
        regClass(WEB3BondApplyBuyCompleteResponse.class);

        //�����p���̓��N�G�X�g
        regClass(WEB3BondSellInputRequest.class);
        //�����p���̓��X�|���X
        regClass(WEB3BondSellInputResponse.class);
        //�����p�m�F���N�G�X�g
        regClass(WEB3BondSellConfirmRequest.class);
        //�����p�m�F���X�|���X
        regClass(WEB3BondSellConfirmResponse.class);
        //�����p�������N�G�X�g
        regClass(WEB3BondSellCompleteRequest.class);
        //�����p�������X�|���X
        regClass(WEB3BondSellCompleteResponse.class);   
        
        //������m�F���N�G�X�g
        regClass(WEB3BondCancelConfirmRequest.class); 
        //������m�F���X�|���X
        regClass(WEB3BondCancelConfirmResponse.class);  
        //������������N�G�X�g
        regClass(WEB3BondCancelCompleteRequest.class);  
        //������������X�|���X
        regClass(WEB3BondCancelCompleteResponse.class);  
        
        //���������Ɖ�N�G�X�g
        regClass(WEB3BondExecuteReferenceRequest.class);  
        //���������Ɖ�X�|���X
        regClass(WEB3BondExecuteReferenceResponse.class);  
        
        //���c���Ɖ�N�G�X�g
        regClass(WEB3BondBalanceReferenceRequest.class);  
        //���c���Ɖ�X�|���X
        regClass(WEB3BondBalanceReferenceResponse.class);  

        //���c���Ɖ�c�����v���N�G�X�g
        regClass(WEB3BondBalanceReferenceTotalRequest.class);  
        //���c���Ɖ�c�����v���X�|���X
        regClass(WEB3BondBalanceReferenceTotalResponse.class);  

        //��������胊�N�G�X�g
        regClass(WEB3BondAutoExecRequest.class);  
        //��������背�X�|���X
        regClass(WEB3BondAutoExecResponse.class); 

        //�Ǘ��ҍ����������o�^���̓��X�|���X
        regClass(WEB3AdminBondDomesticProductRegistInputResponse.class);
        //�Ǘ��ҍ����������o�^���̓��N�G�X�g
        regClass(WEB3AdminBondDomesticProductRegistInputRequest.class);

        //�Ǘ��ҍ����������o�^�m�F���X�|���X
        regClass(WEB3AdminBondDomesticProductRegistConfirmResponse.class);
        //�Ǘ��ҍ����������o�^�m�F���N�G�X�g
        regClass(WEB3AdminBondDomesticProductRegistConfirmRequest.class);

        //�Ǘ��ҍ����������o�^�������X�|���X
        regClass(WEB3AdminBondDomesticProductRegistCompleteResponse.class);
        //�Ǘ��ҍ����������o�^�������N�G�X�g
        regClass(WEB3AdminBondDomesticProductRegistCompleteRequest.class);

        //�Ǘ��ҍ����������ꗗ��ʕ\�����X�|���X
        regClass(WEB3AdminBondDomesticProductListDisplayResponse.class);
        //�Ǘ��ҍ����������ꗗ��ʕ\�����N�G�X�g
        regClass(WEB3AdminBondDomesticProductListDisplayRequest.class);

        //�Ǘ��ҍ����������ꗗ������ʕ\�����X�|���X
        regClass(WEB3AdminBondDomesticProductListSearchDisplayResponse.class);
        //�Ǘ��ҍ����������ꗗ������ʕ\�����N�G�X�g
        regClass(WEB3AdminBondDomesticProductListSearchDisplayRequest.class);

        //��������������ꗗ���N�G�X�g
        regClass(WEB3BondDomesticApplyProductListRequest.class);
        //��������������ꗗ���X�|���X
        regClass(WEB3BondDomesticApplyProductListResponse.class);

        //������������̓��N�G�X�g
        regClass(WEB3BondDomesticApplyInputRequest.class);
        //������������̓��X�|���X
        regClass(WEB3BondDomesticApplyInputResponse.class);

        //����������m�F���N�G�X�g
        regClass(WEB3BondDomesticApplyConfirmRequest.class);
        //����������m�F���X�|���X
        regClass(WEB3BondDomesticApplyConfirmResponse.class);

        //���������劮�����N�G�X�g
        regClass(WEB3BondDomesticApplyCompleteRequest.class);
        //���������劮�����X�|���X
        regClass(WEB3BondDomesticApplyCompleteResponse.class);

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ����̓��N�G�X�g
        regClass(WEB3AdminBondDomesticRecruitLimitManageInputRequest.class);
        //�Ǘ��ҍ��������X�ʉ���g�Ǘ����̓��X�|���X
        regClass(WEB3AdminBondDomesticRecruitLimitManageInputResponse.class);

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��m�F���N�G�X�g
        regClass(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.class);
        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��m�F���X�|���X
        regClass(WEB3AdminBondDomesticRecruitLimitManageConfirmResponse.class);

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��������N�G�X�g
        regClass(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.class);
        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��������X�|���X
        regClass(WEB3AdminBondDomesticRecruitLimitManageCompleteResponse.class);

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ����ʃ��N�G�X�g
        regClass(WEB3AdminBondDomesticRecruitLimitManageCommonRequest.class);
        //�Ǘ��ҍ��������X�ʉ���g�Ǘ����ʃ��X�|���X
        regClass(WEB3AdminBondDomesticRecruitLimitManageCommonResponse.class);

        //�Ǘ��Ғ�����t�����Ɖ�N�G�X�g
        regClass(WEB3AdminBondOrderReceiveHistoryRequest.class);
        //�Ǘ��Ғ�����t�����Ɖ�X�|���X
        regClass(WEB3AdminBondOrderReceiveHistoryResponse.class);
    }

    /**
     * Handler �̓o�^����
     * @@throws Exception
     */
    private static void plugHandlers() throws Exception
    {
        //==============�Ǘ��҃��b�Z�[�W=======================
        
        //�Ǘ��ҍ�������n���h�� 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCancelConfirmRequest.class,
            WEB3AdminBondExecuteCancelHandler.class,
            "confirmExecuteCancel");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCancelCompleteRequest.class,
            WEB3AdminBondExecuteCancelHandler.class,
            "completeExecuteCancel");
        
        //�Ǘ��Җ��ύX�n���h�� 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecChangeInputRequest.class,
            WEB3AdminBondExecuteChangeHandler.class,
            "inputExecuteChange");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecChangeConfirmRequest.class,
            WEB3AdminBondExecuteChangeHandler.class,
            "confirmExecuteChange");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecChangeCompleteRequest.class,
            WEB3AdminBondExecuteChangeHandler.class,
            "completeExecuteChange");
        
        //�Ǘ��ҐV�K�����̓n���h�� 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecInputRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "inputOrderAndExecute");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecConfirmRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "confirmOrderAndExecute");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCompleteRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "completeOrderAndExecute");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCalculateRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "calcEstimatedPrice");
        
        //���Ǘ��Ғ������b�N�敪�X�V�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondOrderLockUnlockRequest.class,
            WEB3AdminBondOrderLockStatusUpdateHandler.class,
            "updateBondOrderLockStatus");
        
        //���Ǘ��Җ����ꗗ�n���h�� 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductSearchInputRequest.class,
            WEB3AdminBondProductListHandler.class,
            "inputProductList");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductSearchListRequest.class,
            WEB3AdminBondProductListHandler.class,
            "searchProductList");
        
        //���Ǘ��Җ����o�^�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductRegistInputRequest.class,
            WEB3AdminBondProductRegisterHandler.class,
            "inputProductRegister");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductRegistConfirmRequest.class,
            WEB3AdminBondProductRegisterHandler.class,
            "confirmProductRegister");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductRegistCompleteRequest.class,
            WEB3AdminBondProductRegisterHandler.class,
            "completeProductRegister");
        
        //������/���t�����ꗗ�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyProductListRequest.class,
            WEB3BondRecruitBuyProductListHandler.class,
            "bondRecruitBuyProductList");
        
        //������/���t���̓n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyInputRequest.class,
            WEB3BondRecruitBuyInputHandler.class,
            "inputBondRecruitBuy");
        
        //������/���t�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyConfirmRequest.class,
            WEB3BondRecruitBuyHandler.class,
            "confirmBondRecruitBuy");
        
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyCompleteRequest.class,
            WEB3BondRecruitBuyHandler.class,
            "completeBondRecruitBuy");        
        
        //�����p���̓n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondSellInputRequest.class,
            WEB3BondSellInputHandler.class,
            "inputBondSell");
        
        //�����p�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondSellConfirmRequest.class,
            WEB3BondSellHandler.class,
            "confirmBondSell");
        
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondSellCompleteRequest.class,
            WEB3BondSellHandler.class,
            "completeBondSell");        
        
        //������T�[�r�X
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondCancelConfirmRequest.class,
            WEB3BondCancelHandler.class,
            "confirmCancel");
        
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondCancelCompleteRequest.class,
            WEB3BondCancelHandler.class,
            "completeCancel");    
        
        //���������Ɖ�T�[�r�X
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondExecuteReferenceRequest.class,
            WEB3BondExecuteReferenceHandler.class,
            "getExecuteReference");
        
        //���c���Ɖ�T�[�r�X
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondBalanceReferenceRequest.class,
            WEB3BondBalanceReferenceHandler.class,
            "getBalanceReference");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondBalanceReferenceTotalRequest.class,
            WEB3BondBalanceReferenceHandler.class,
            "getBalanceTotal");

        //���������T�[�r�X
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondAutoExecRequest.class,
            WEB3BondAutoExecuteHandler.class,
            "completeAutoExecute");

        //�Ǘ��ҍ����������o�^�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductRegistInputRequest.class,
            WEB3AdminBondDomesticProductRegistHandler.class,
            "inputProductRegist");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductRegistConfirmRequest.class,
            WEB3AdminBondDomesticProductRegistHandler.class,
            "validateProductRegist");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductRegistCompleteRequest.class,
            WEB3AdminBondDomesticProductRegistHandler.class,
            "submitProductRegist");

        //�Ǘ��ҍ����������ꗗ�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductListDisplayRequest.class,
            WEB3AdminBondDomesticProductListHandler.class,
            "getProductListScreenDisplay");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductListSearchDisplayRequest.class,
            WEB3AdminBondDomesticProductListHandler.class,
            "getSearchScreenDisplay");

        //�Ǘ��ҍ��������X�ʉ���g�Ǘ��n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticRecruitLimitManageInputRequest.class,
            WEB3AdminBondDomesticRecruitLimitManageHandler.class,
            "inputRecruitLimitManage");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.class,
            WEB3AdminBondDomesticRecruitLimitManageHandler.class,
            "validateRecruitLimitManage");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.class,
            WEB3AdminBondDomesticRecruitLimitManageHandler.class,
            "submitRecruitLimitManage");

        //�Ǘ��Ғ�����t�����Ɖ�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondOrderReceiveHistoryRequest.class,
            WEB3AdminBondOrderReceiveHistoryHandler.class,
            "orderReceiveHistory");

        //��������������ꗗ�n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyProductListRequest.class,
            WEB3BondDomesticApplyProductListHandler.class,
            "bondDomesticApplyProductList");

        //������������̓n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyInputRequest.class,
            WEB3BondDomesticApplyInputHandler.class,
            "bondDomesticApplyInput");

        //����������n���h��
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyConfirmRequest.class,
            WEB3BondDomesticApplyHandler.class,
            "bondDomesticApplyConfirm");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyCompleteRequest.class,
            WEB3BondDomesticApplyHandler.class,
            "bondDomesticApplyComplete");
    }
}@
