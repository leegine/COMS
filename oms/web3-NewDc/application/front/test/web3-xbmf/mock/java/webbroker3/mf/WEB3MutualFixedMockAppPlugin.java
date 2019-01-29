head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImplForMock;

public class WEB3MutualFixedMockAppPlugin extends Plugin {

	public WEB3MutualFixedMockAppPlugin() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void plug()
    throws Exception
	{
	    plug(WEB3MutualFixedMockAppPlugin.class);
	}
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
	public static void onPlug()
	    throws Exception
	{
		TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // �g���g�����U�N�V�����E�}�l�[�W���[��
        // �I�[�o�[���C�h���\�b�h���������ߊg��������W���[���N���X���쐬��
        // �g��������W���[���N���X���Őݒ�
        l_finApp.uninstallTradingModule("xb-mf-pdbt");
        l_finApp.installTradingModule(new WEB3MutualFundTradingModuleForMock());

        l_tradingModule = l_finApp.getTradingModule("xb-mf-pdbt");
        // �g���v���_�N�g�E�}�l�[�W���[
        l_tradingModule.overrideProductManager(new WEB3MutualFundProductManagerForMock());

        // �v�Z�T�[�r�X�N���X
        l_tradingModule.overrideBizLogicProvider(new WEB3MutualFundBizLogicProviderForMock());
        
        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3MutualFundOrderManagerForMock());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(new WEB3MutualFundPositionManagerForMock());

        // ���M������tUnitServiceImplForMock
        Services.overrideService(WEB3MutualOrderAcceptUnitService.class,
                new WEB3MutualOrderAcceptUnitServiceImplForMock());

        // �����M�������R���ʃ`�F�b�NForMock
        WEB3MutualFundOrderManagerReusableValidationsCheckForMock.register();
        
        // WEB3MutualFixedBuyCommonServiceImplForMock
        Services.overrideService(WEB3MutualFixedBuyCommonService.class,
                new WEB3MutualFixedBuyCommonServiceImplForMock());
        
        //���M�莞��z���t���������o�^�T�[�r�XForMock
        Services.overrideService(WEB3MutualFixedBuyConditionService.class,
                new WEB3MutualFixedBuyConditionServiceImplForMock());

        //�Ǘ��ғ��M���������o�^�T�[�r�XForMock
        Services.overrideService(WEB3AdminMutualConditionsService.class,
                new WEB3AdminMutualConditionsServiceImplForMock());

        //���M�Ǘ��җ]�͒�������T�[�r�XForMock
        Services.overrideService(WEB3AdminMutualTPACancelService.class,
                new WEB3AdminMutualTPACancelServiceImplForMock());
        
        Services.overrideService(WEB3MutualTradeOrderNotifyUnitService.class,
                new WEB3MutualTradeOrderNotifyUnitServiceImplForMock());
        
	}
}
@
