head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����V�K�����̓T�[�r�XImpl(WEB3ToSuccMarginOpenMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/8 杊��](���u) �V�K�쐬
Revesion History : 2007/01/20 �юu��(���u) �d�l�ύX���f��No.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�M�p����V�K�����̓T�[�r�XImpl)<BR>
 * �i�A���j�M�p����V�K�����̓T�[�r�X�����N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginInputServiceImpl 
    extends WEB3MarginOpenMarginInputServiceImpl 
    implements WEB3ToSuccMarginOpenMarginInputService 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF790232
     */
    public WEB3ToSuccMarginOpenMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����V�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * this.get���͉��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4328FE2B01F3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginOpenInputRequest)
        {
            l_response = this.getOpenMarginInputScreen((WEB3SuccMarginOpenInputRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�V�K�����͉��)<BR>
     * �i�A���j�M�p����V�K�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�A���j�M�p����V�K�����́jget�V�K�����͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �i�A���j�M�p����V�K�����̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccMarginOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4328FF9D0270
     */
    protected WEB3SuccMarginOpenInputResponse getOpenMarginInputScreen(WEB3SuccMarginOpenInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenMarginInputScreen(WEB3SuccMarginOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get�����e�����̒����P��(�i�e�����j����ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }

        //1.3 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4 reset�s��R�[�h(�s��R�[�h : String)
        // �s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h == null�̏ꍇ�A"���̑�"���Z�b�g�B�ȊO�A�������s��Ȃ��B
        if (null == l_request.marketCode)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
        }

        //1.5 validate�A������(�⏕���� : �⏕����, �����^�C�v : ProductTypeEnum, �敨�^�I�v�V�����敪 : String, 
        //  �A����������敪 : String, �e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrder(l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);

        //1.6 validate�A�������ő�ݒ萔(�e�����̒����P�� : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        if (null == l_request.marketCode)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        }
        
        //1.7 get�V�K�����͉��(���N�G�X�g�f�[�^ : �M�p����V�K���������̓��N�G�X�g)
        WEB3SuccMarginOpenInputResponse l_response = (WEB3SuccMarginOpenInputResponse) 
            super.getOpenMarginInputScreen(l_request);
        
        //1.8 is���Δ������(String, OrderUnit)
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);

        //1.9 �i(*) �v���p�e�B�Z�b�g�j
        //�u�i�A���j�M�p����������n�������̓��X�|���X�v�ɂ̂ݑ��݂���v���p�e�B
        //���������F�@@
        //���A�������}�l�[�W��Impl.is���Δ������()==true�̏ꍇ
        //�i�e�����̒����P��.�������ʁj���Z�b�g�B
        //���A�������}�l�[�W��Impl.is���Δ������()==false�̏ꍇ
        //  null���Z�b�g�B
        if (l_blnIsReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        }
        else 
        {
            l_response.orderQuantity = null;
        }

        //�l�i�����ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        
        //���s�����ꗗ�F�@@"������"�݂̂��Z�b�g�B
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        //�v�w�l�p���s�����ꗗ�F�@@null���Z�b�g�B
        l_response.wlimitExecCondList = null;

        //���������敪�ꗗ�F�@@"�w��Ȃ�"�݂̂��Z�b�g�B
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�V�K���\�z)<BR>
     * �V�K���\�z���擾����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����]�̓T�[�r�X.get�M�p�V�K���\�z�`�A�������`<BR>
     * (�⏕����, null)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4337DBDC0195
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //����]�̓T�[�r�X.get�M�p�V�K���\�z�`�A�������`(�����̕⏕����, null)�̖߂�l��ԋp����B
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblSuccMarginTradingPower =
            l_tradingPowerService.getSuccMarginTradingPower(l_subAccount, null);

        log.exiting(STR_METHOD_NAME);
        return l_dblSuccMarginTradingPower;
    }
}
@
