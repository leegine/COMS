head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l�������������ꌏ�T�[�r�XImpl(WEB3ToStopEquityOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 ������(���u) �V�K�쐬
                   2006/01/23 ������(�k�����u) �d�l�ύX�E���f��077
                   2006/11/22 ꎉ�  (���u)     �d�l�ύX�E���f��177   
Revesion History : 2007/08/29 �đo�g(���u) �d�l�ύX�E���f��243,���f��244
Revesion History : 2009/11/19 �Ԑi    (���u) �y�g���K�[�����E���Ǘ��ҁz�t�w�l����������Q�Ή�
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginChangeOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginChangeOrderSpec;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToStopEquityOrderServiceInterceptor;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�t�w�l�������������ꌏ�T�[�r�XImpl)<BR>
 * �t�w�l�������������ꌏ�T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToStopEquityOrderUnitServiceImpl implements WEB3ToStopEquityOrderUnitService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityOrderUnitServiceImpl.class);
    
    /**
     * @@roseuid 436ACF6F0167
     */
    public WEB3ToStopEquityOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (submit���������t�w�l����)<BR>
     * ���������t�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A���������������ꌏ�T�[�r�X�jsubmit���������t�w�l�����v�Q�ƁB<BR>
     *  ========================================================== <BR>
     * 1.17.1�i����t���[�F�@@<BR>
     * ��K���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪 <BR>
     * == "��K���G���["�j�̏ꍇ�u��K���`�F�b�N�G���[�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01928<BR>
     *  ========================================================== <BR>
     *  ========================================================== <BR>
     * 1.17.2.1.1get�a����s�����i���t�j(����]�͌��� : ����]�͌���)<BR>
     * ���\�b�h�R�[����A�u���t�a����s���v�̗�O���X���[����B<BR>
     * ���擾����������͏�L��O�I�u�W�F�N�g�ɐݒ肷�邱�ƁB<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01929<BR>
     *  ========================================================== <BR>
     *  ========================================================== <BR>
     * 1.17.2.2.1get�a����s�����<BR>
     * �i���t�j(����]�͌��� : ����]�͌���, �������� : double)<BR>
     * ���\�b�h�R�[����A�u���t�a����s���v�̗�O���X���[����B<BR>
     * ���擾����������͏�L��O�I�u�W�F�N�g<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01930<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@roseuid 434C7B0003A0
     */
    public void submitEquityStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitEquityStopOrder(EqTypeOrderUnit l_orderUnit) ";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

		WEB3TPTradingPowerService l_tpTradingPowerService =
			(WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();        
        SubAccount l_subAccount = null;
        // �����P�ʂ̍Ď擾
        try
        {
	        l_orderUnit =
	            (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        { 
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            //�⏕�����F�@@�����̒����P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N
            l_subAccount = l_accountMananger.getSubAccount(l_row.getAccountId(), l_row.getSubAccountId());
            
            //1.1�w�肳�ꂽ�������t�w�l�����̑Ώۂł��邩�𔻒肷��B
            boolean l_blnProcessObject = this.isProcessObject(l_orderUnit);
            
            //1.2�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnProcessObject)
            {
                return;
            }
            //1.3�������`�F�b�N���s���B 
            //�i�����o�^���̔������ƁA���ݓ������狁�߂����������قȂ�ꍇ�͔����G���[�Ƃ���j
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);
    
            //1.4�i����t���[�F�@@�����P��.�����ID��null�̏ꍇ�̂݁j

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3GentradeTrader l_trader = null;
            WEB3GentradeMarket l_market = null;
            EqTypeProduct l_eqtypeProduct = null;
            WEB3GentradeBranch l_branch = null;
            try
            {
                if (!l_row.getTraderIdIsNull())
                {

                    //1.4.1���҃I�u�W�F�N�g���擾����B 
                    l_trader = 
                        (WEB3GentradeTrader)l_finObjectManager.getTrader(l_row.getTraderId());
                }
                
                // 1.5create�V�K���������e
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
                
                // �g���v���_�N�g�}�l�[�W��
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                    (EqTypeProduct)l_productManager.getProduct(l_row.getProductId());
                
                l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_row.getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            //is�������F�@@�����P��.������ʂ�蔻�肷��B�i�h�����������h�̏ꍇ��false�A�h�����������h�̏ꍇ��true�j
            boolean l_blnIsSellOrder = false;
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()))
            {
                l_blnIsSellOrder = false;
            }
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
            {
                l_blnIsSellOrder = true;
            }

            Long l_firstOrderUnitId = null;
            if (!l_row.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_row.getFirstOrderUnitId());
            }
            WEB3EquityNewCashBasedOrderSpec l_orderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                l_branch.getInstitution().getInstitutionCode(),  //�،���ЃR�[�h�F�@@�����P��.���XID �̕��X�I�u�W�F�N�g.�،���ЃR�[�h
                l_trader,  //���ҁF�@@�����P��.�����ID==null�̏ꍇ�́Anull�����P��.�����ID��null�̏ꍇ�́A�����ID�ɊY�����鈵�҃I�u�W�F�N�g
                l_market.getMarketCode(),  //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
                l_eqtypeProduct.getProductCode(),  //�����R�[�h�F�@@�����P��.����ID�ɊY����������R�[�h
                l_row.getQuantity(),  //�����F�@@�����P��.��������
                l_row.getLimitPrice(),  //�w�l�F�@@�����P��.�w�l
                l_row.getExecutionConditionType(),  //���s�����F�@@�����P��.���s����
                l_row.getTaxType(),  //�ŋ敪�F�@@�����P��.�ŋ敪
                l_row.getExpirationDate(),  //�����������F�@@�����P��.����������
                l_blnIsSellOrder,  //is������ :�F�@@�����P��.������ʂ�蔻�肷��B�i�h�����������h�̏ꍇ��false�A�h�����������h�̏ꍇ��true�j
                l_row.getOrderChanel(),  //�����`���l���F�@@�����P��.���񒍕��̒����`���l��
                l_row.getPriceConditionType(),  //�l�i�����F�@@�����P��.�l�i����
                l_row.getOrderConditionType(),  //���������F�@@�����P��.��������
                l_row.getOrderCondOperator(),  //�����������Z�q�F�@@�����P��.�����������Z�q
                l_row.getStopOrderPrice(),  //�t�w�l��l�F�@@�����P��.�t�w�l��l
                0,  //�iW�w�l�j�����w�l�F�@@0�i�Œ�j
                l_firstOrderUnitId);  //���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��
            
            //1.6set�萔�����i�R�[�h(�萔�����i�R�[�h : String)
            l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
            
            //1.7�萔���I�u�W�F�N�g���쐬����B
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //���X�F�@@�����P��.���XID �̕��X�I�u�W�F�N�g
            //����R�[�h�iSONAR�j�F�@@�����P��.����R�[�h�iSONAR�j
            WEB3GentradeCommission l_commission = l_orderSpec.createCommission(l_branch, l_row.getSonarTradedCode());
            
            //1.8validate������������(�⏕���� : SubAccount, �����������e : EqTypeNewCashBasedOrderSpec, is�A�����Δ��� : boolean)
            EqTypeNewOrderValidationResult l_result = l_orderManager.validateNewCashBasedOrder(l_subAccount, l_orderSpec, true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.10����������擾����B
            WEB3EquityTradedProduct l_tradeProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            
            // 1.11���蒍���̏ꍇ
            if (l_blnIsSellOrder)
            {
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                // 1.11.1���t�\���ʃ`�F�b�N
                l_orderMgrResVal.validateSellableAssetQuantity(
                    l_subAccount,
                    l_tradeProduct,
                    0D,
                    l_orderSpec.getTaxType());
            }
            
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            // calc�S�����z�v�Z�P��
            double l_dblCalcPrice = l_orderManager.calcPriceForRestraintAmount(
                l_commission,
                l_orderUnitRow.getOrderType(),
                l_orderUnitRow.getLimitPrice(),
                l_orderUnitRow.getWLimitPrice(),
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getExecutionConditionType(),
                l_orderUnitRow.getPriceConditionType(),
                l_tradeProduct,
                (WEB3GentradeSubAccount)l_subAccount,
                null);
                
            //1.14�v�Z�P�����Z�b�g����B
            l_orderSpec.setOrderUnitPrice(l_dblCalcPrice);
            
            //1.15calc�T�Z��n���(�萔�� : �萔��, �v�Z�P�� : double, �⏕���� : SubAccount, 
            //������� : �������, ���� : double, is������ : boolean, ��萔�� : double,
            //���v�����z : double, isSkip���z�`�F�b�N : boolean, is�S���l�� : boolean)
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = l_orderManager.calcEstimateDeliveryAmount(
                l_orderSpec.getCommission(),  //�萔���F�@@�����������e.get�萔��( )
                l_orderSpec.getOrderUnitPrice(),  //�v�Z�P���F�@@�����������e.get�����P��( )
                l_subAccount,  //�⏕�����F�@@�����̒����P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
                l_tradeProduct,  //��������F�@@�擾������������I�u�W�F�N�g
                l_orderSpec.getQuantity(), //�����F�@@�����������e.getQuantity( )�i���o�����̐��ʁj
                l_orderSpec.isSellOrder(),  //is�������F�@@�����������e.isSellOrder( )
                0D,  //��萔�ʁF�@@0
                0D,  //���v�����z�F�@@0
                false,  //isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j
                true);  //is�S���l���F�@@true�i�l������j
            
            //1.16�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�T�Z���z�F�@@calc�T�Z��n����߂�l�I�u�W�F�N�g.get�T�Z��n���( )
            l_orderSpec.setEstimateDeliveryAmount(l_deliveryPrice.getEstimateDeliveryAmount());

            // create�������������l�ڍ�(�����P��)
            // �������������l�ڍ׃I�u�W�F�N�g���쐬����B
            // �����P�ʁ@@�F�@@�p�����[�^.�����P��
            WEB3EquityChangeOrderUnitEntry l_entry = this.createEquityChangeOrderUnitEntry(l_orderUnit);
			
			//1.18���������������e(�����h�c : long, �������������l�ڍ� : �������������l�ڍ�, �،���ЃR�[�h : String, �����`���l�� : String, ���� : ����)
			WEB3EquityChangeOrderSpec l_changeOrderSpec = 
				new WEB3EquityChangeOrderSpec(
					l_row.getOrderId(),
					l_entry,
					l_orderSpec.getInstitutionCode(),
					l_orderSpec.getOrderChannel(),
					l_orderSpec.getTrader());
			
			//1.19�������������C���^�Z�v�^(�����o�H�敪 : String, �㗝���͎� : ����)
			WEB3EquityOrderManagerChangeOrderEventInterceptor l_intercepter =
				new WEB3EquityOrderManagerChangeOrderEventInterceptor(
					l_row.getOrderRootDiv(),
					(WEB3GentradeTrader)l_orderSpec.getTrader());
			
			//1.20create�����������e( )
            l_changeOrderSpec.createOrderSpec();
            
			WEB3EquityNewCashBasedOrderSpec l_cashBasedOrderSpec = 
				l_changeOrderSpec.getNewCachBasedOrderSpec();
			
			//1.21set�����P��(�����P�� : double)
			l_cashBasedOrderSpec.setOrderUnitPrice(l_orderSpec.getOrderUnitPrice());
			
			//1.22set�T�Z��n���(�T�Z���z : double)
			l_cashBasedOrderSpec.setEstimateDeliveryAmount(l_orderSpec.getEstimateDeliveryAmount());
			
			//1.23set�����������e(�����������e : �����������e)
			l_intercepter.setEquityOrderSpec(l_cashBasedOrderSpec);
			
			//1.24validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
			Object[] l_intercepters = { l_intercepter };
			Object[] l_cashBasedOrderSpecs = { l_changeOrderSpec };
			WEB3TPTradingPowerResult l_tradingPowerResult =
				l_tpTradingPowerService.validateTradingPower(
					(WEB3GentradeSubAccount) l_subAccount,
					l_intercepters,
					l_cashBasedOrderSpecs,
					l_row.getOrderType(),
					true);
			
			//1.25throw�]�̓G���[�ڍ׏��(����]�͌��� : ����]�͌���, ������� : OrderTypeEnum)
			l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_row.getOrderType());
            
            WEB3EquityFrontOrderService l_frontOrderService = (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strRouteDiv = 
                l_frontOrderService.getSubmitOrderRouteDiv(l_tradeProduct, l_subAccount.getInstitution().getInstitutionCode(), l_row.getSonarTradedCode());
            //1.26�i���s���ʂɉ����A�����P�ʃe�[�u����update����j
            //1.26.1(*)����I�������ꍇ
            //1.26.1.1�����n�f�[�^��update����B
            this.updateOrderData(l_orderUnit, l_orderSpec.getOrderUnitPrice(), l_orderSpec.getEstimateDeliveryAmount(), l_strRouteDiv, null);
            
            //1.26.1.2insert�������������L���[(����ID : long)
            l_orderManager.insertEquityHostOrder(l_orderUnit.getOrderId());
            
            EqTypeOrderUnit l_eqOrderUnit = null;
            try
            {
                l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            //1.26.1.3sendMQTrigger(�����P��)
            this.sendMQTrigger(l_eqOrderUnit);
            log.exiting(STR_METHOD_NAME);
            
        }
        //1.26.2(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            //1.26.2.1get�����G���[���R�R�[�h(�G���[�R�[�h : String)
            String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            //1.26.2.2�t�w�l�������������X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String)
			WEB3ToStopEquityOrderServiceInterceptor l_interceptor = new WEB3ToStopEquityOrderServiceInterceptor(l_strReasonCode);
            //1.26.2.3�����}�l�[�W���ɐ��������X�V�C�x���g�C���^�Z�v�^���Z�b�g����B
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            //1.26.2.4�������b�Z�[�W�I�u�W�F�N�g�𐶐�����B
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.26.2.5���������������s���B
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            EqTypeMarketResponseReceiverCallbackService l_callbackService = 
                (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();            
            try
            {
	            ProcessingResult l_result = l_callbackService.process(l_responseMessage);
	            if (l_result.isFailedResult())
	            {
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    l_result.getErrorInfo(),
	                    this.getClass().getName() + "." + STR_METHOD_NAME);
	            }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }                
            }

            // is�\�񒍕��m�F�v
            // �����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderConfirmRequire =
                l_orderManager.isReserveOrderConfirmRequire(l_orderUnit);

            //invalidateAll�\�񒍕��P��(long)
            //�Y���̒����ɕR�t���\�񒍕�������ꍇ�A�����̎������s���B
            //�e�����̒���ID�F�@@�����P��.����ID
            if (l_blnIsReserveOrderConfirmRequire)
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }

			//1.26.2.6�]�͍Čv�Z���s���B
			l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }        
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�V�K���t�w�l����)<BR>
     * �M�p�V�K���t�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A���������������ꌏ�T�[�r�X�jsubmit�M�p�V�K���t�w�l�����v�Q�ƁB<BR>
     *  ========================================================== <BR>
     * 1.19.1�i����t���[�F�@@��K���G���[<BR>
     * �i����]�͌���.����]�̓G���[���.����]�̓G���[�敪 ==<BR>
     *  "��K���G���["�j�̏ꍇ�u��K���G���[�v�̗�O��<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01928<BR>
     *  ========================================================== <BR>
     *  ========================================================== <BR>
     * 1.19.2�i����t���[�F�@@�a����s���G���[<BR>
     * �i����]�͌���.����]�̓G���[���.����]�̓G���[�敪 !=<BR>
     *  "��K���G���["�j�̏ꍇ�u�V�K���a����s���v�̗�O��<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01935<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C7B010006
     */
    public void submitMarginOpenContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "submitMarginOpenContractStopOrder(EqTypeOrderUnit l_orderUnit) ";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

		WEB3TPTradingPowerService l_tpTradingPowerService =
			(WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();      
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        SubAccount l_subAccount = null;
        // �����P�ʂ̍Ď擾
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        { 
	        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
		    WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
		    //�⏕�����F�@@�����̒����P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N
		    l_subAccount = l_accountMananger.getSubAccount(l_row.getAccountId(), l_row.getSubAccountId());
		    
            //1.1�w�肳�ꂽ�������t�w�l�����̑Ώۂł��邩�𔻒肷��B
            boolean l_blnProcessObject = this.isProcessObject(l_orderUnit);
            
            //1.2�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnProcessObject)
            {
                return;
            }
            
            //1.3�������`�F�b�N���s���B 
            //�i�����o�^���̔������ƁA���ݓ������狁�߂����������قȂ�ꍇ�͔����G���[�Ƃ���j
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);
    
            //1.4�i����t���[�F�@@�����P��.�����ID��null�̏ꍇ�̂݁j

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3GentradeTrader l_trader = null;
            WEB3GentradeMarket l_market = null;
            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                if (!l_row.getTraderIdIsNull())
                {

                    //1.4.1���҃I�u�W�F�N�g���擾����B 
                    l_trader = 
                        (WEB3GentradeTrader)l_finObjectManager.getTrader(l_row.getTraderId());
                }
                
                // 1.5create�V�K���������e
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
                
                // �g���v���_�N�g�}�l�[�W��
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager) l_tradingModule.getProductManager();
                l_eqtypeProduct =
                    (EqTypeProduct)l_productManager.getProduct(l_row.getProductId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            
            //is�����F�@@[�����P��.getSide() == SideEnum.BUY(��)�̏ꍇ]true���Z�b�g�B
            //[�����P��.getSide() == SideEnum.SELL(��)�̏ꍇ]false���Z�b�g�B
            boolean l_blnIsBuy = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuy = true;
            }
            else 
            {
                l_blnIsBuy = false;
            }

            Long l_firstOrderUnitId = null;
            if (!l_row.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId =  new Long(l_row.getFirstOrderUnitId());
            }
            WEB3MarginOpenContractOrderSpec l_orderSpec = WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader,  //���ҁF�@@�����P��.�����ID==null�̏ꍇ�́Anull�����P��.�����ID��null�̏ꍇ�́A�����ID�ɊY�����鈵�҃I�u�W�F�N�g
                l_blnIsBuy,  //is�����F[�����P��.getSide() == SideEnum.BUY(��)�̏ꍇ]true���Z�b�g�B[�����P��.getSide() == SideEnum.SELL(��)�̏ꍇ]false���Z�b�g�B
                l_eqtypeProduct.getProductCode(),  //�����R�[�h�F�@@�����P��.����ID�ɊY����������R�[�h
                l_market.getMarketCode(),  //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
                l_row.getQuantity(),  //�����F�@@�����P��.��������
                l_row.getLimitPrice(),  //�w�l�F�@@�����P��.�w�l
                l_row.getExecutionConditionType(),  //���s�����F�@@�����P��.���s����
                l_row.getExpirationDate(),  //�����������F�@@�����P��.����������
                l_row.getTaxType(),  //�ŋ敪�F�@@�����P��.�ŋ敪
                l_row.getPriceConditionType(),  //�l�i�����F�@@�����P��.�l�i����
                l_row.getOrderConditionType(),  //���������F�@@�����P��.��������
                l_row.getOrderCondOperator(),  //�����������Z�q�F�@@�����P��.�����������Z�q
                l_row.getStopOrderPrice(),  //�t�w�l��l�F�@@�����P��.�t�w�l��l
                0,  //�iW�w�l�j�����w�l�F�@@0�i�Œ�j
                l_row.getRepaymentType(),  //�ٍϋ敪�F�@@�����P��.�ٍϋ敪
                l_row.getRepaymentNum(),  //�ٍϊ����l�F�@@�����P��.�ٍϊ�
                l_firstOrderUnitId);  //���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��
            
            //1.6create�萔��(����ID : long)
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());
            
            //1.7set������(������ : Timestamp)
            l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            // 1.11validate��������i�M�p�j
            WEB3EquityTypeOrderManagerReusableValidations l_typeOrderMgrReusValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            WEB3EquityTradedProduct l_tradeProduct =
                (WEB3EquityTradedProduct)l_typeOrderMgrReusValidations.validateTradedProductForMarginTrading(
                    l_subAccount,
                    (WEB3EquityProduct)l_eqtypeProduct,
                    l_market,
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
                    l_orderSpec.getRepaymentType(),
                    OrderCategEnum.OPEN_MARGIN,
                    l_orderSpec.isShortOrder());
            
            // calc�S�����z�v�Z�P��
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            double l_dblCalcPrice = l_orderManager.calcPriceForRestraintAmount(
                l_commission,
                l_orderUnitRow.getOrderType(),
                l_orderUnitRow.getLimitPrice(),
                l_orderUnitRow.getWLimitPrice(),
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getExecutionConditionType(),
                l_orderUnitRow.getPriceConditionType(),
                l_tradeProduct,
                (WEB3GentradeSubAccount)l_subAccount,
                null);
            
            //1.13set�v�Z�P��(�v�Z�P�� : double)
            l_orderSpec.setCalcUnitPrice(l_dblCalcPrice);
            
            //1.14calc�����������(�萔�� : �萔��, �v�Z�P�� : double, �⏕���� : SubAccount, 
            //������� : EqTypeTradedProduct, ���� : double, ��萔�� : double, 
            //���v�����z : double, isSkip���z�`�F�b�N : boolean)
            double l_dblAmountAtOrder = l_orderManager.calcContractAmountAtOrder(
                l_commission,  //�萔���F�@@�쐬�����萔���I�u�W�F�N�g
                l_orderSpec.getCalcUnitPrice(),  //�v�Z�P���F�@@�M�p�V�K���������e.get�v�Z�P��( )
                (WEB3GentradeSubAccount)l_subAccount,  //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
                l_tradeProduct,  //��������F�@@�擾������������I�u�W�F�N�g
                l_orderSpec.getQuantity(),  //�����F�@@�@@�M�p�V�K���������e.getQuantity( )
                0,  //��萔�ʁF�@@0
                0,  //���v�����z�F�@@0
                false);  //isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
            
            //1.15set�����(����� : double)
            l_orderSpec.setContractAmount(l_dblAmountAtOrder);
            
            //1.16validate�V�K������(�⏕���� : SubAccount, �M�p�V�K���������e : EqTypeOpenContractOrderSpec, �����P�� : �����P��)
            WEB3MarginNewOrderValidationResult l_validationResult = 
                (WEB3MarginNewOrderValidationResult)l_orderManager.validateOpenContractOrder(l_subAccount, l_orderSpec, l_orderUnit);
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BusinessLayerException(l_validationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
            }
            
            //1.17calc�ϑ��萔��(�萔�� : �萔��, �⏕���� : SubAccount)
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            
            //1.18�󔄂�K���Ώۃt���O���擾����B
            boolean l_blnRestraint = l_validationResult.getShortSellingRestraint();
            
            //1.19create�V�K�������������e(����ID : long, �����P��ID : long, �����㒍������ : double, ������w�l : double, 
            //�@@�@@�����㎷�s���� : EqTypeExecutionConditionType, �����㒍�������� : Date, ������l�i���� : String, �������� : String, 
            //�@@�@@�����㔭���������Z�q : String, ������t�w�l��l : double, ������iW�w�l�j�����w�l : double, ������is�o����܂Œ��� : boolean,
            //�@@�@@�㗝���͎� : ����)
			WEB3MarginChangeOrderSpec l_changeOrderSpec =
				WEB3MarginChangeOrderSpec.createOpenMarginChangeOrderSpec(
					l_row.getOrderId(),
					l_row.getOrderUnitId(),
					l_orderSpec.getQuantity(),
					l_orderSpec.getLimitPrice(),
					l_orderSpec.getExecConditionType(),
					l_orderSpec.getOrderExpDate(),
					l_orderSpec.getPriceConditionType(),
					l_orderSpec.getOrderConditionType(),
					l_orderSpec.getOrderCondOperator(),
					l_orderSpec.getStopOrderPrice(),
					0,
					l_orderManager.isCarriedOrderUnit(l_orderUnit),
					l_orderSpec.getTrader());
			
			//1.20set������v�Z�P��(������v�Z�P�� : double)
			l_changeOrderSpec.setModifiedCalcUnitPrice(l_orderSpec.getCalcUnitPrice());
			
			//1.21set�����㌚���(����� : double)
			l_changeOrderSpec.setModifiedContractAmount(l_orderSpec.getContractAmount());
			
			//1.22�M�p�V�K�������X�V�C���^�Z�v�^(�M�p�V�K�������������e : �M�p�V�K�������������e, �󔄂�K���Ώۃt���O : boolean, �����o�H�敪 : String, �㗝���͎� : ����)
			WEB3MarginChangeOpenMarginUpdateInterceptor l_intercepter =
				new WEB3MarginChangeOpenMarginUpdateInterceptor(
					l_changeOrderSpec,
					l_blnRestraint,
					l_row.getOrderRootDiv(),
					l_trader);
			
			//1.23validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
			Object[] l_intercepters = { l_intercepter };
			Object[] l_changeOrderSpecs = { l_changeOrderSpec };
			WEB3TPTradingPowerResult l_tradingPowerResult =
				l_tpTradingPowerService.validateTradingPower(
					(WEB3GentradeSubAccount) l_subAccount,
					l_intercepters,
					l_changeOrderSpecs,
					l_row.getOrderType(),
					true);
			
			//1.24throw�]�̓G���[�ڍ׏��(����]�͌��� : ����]�͌���, ������� : OrderTypeEnum)
			l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_row.getOrderType());
            
            WEB3EquityFrontOrderService l_frontOrderService = (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strRouteDiv = 
                l_frontOrderService.getSubmitOrderRouteDiv(l_tradeProduct, l_subAccount.getInstitution().getInstitutionCode(), l_row.getSonarTradedCode());
            //1.25�i���s���ʂɉ����A�����P�ʃe�[�u����update����j
            //1.25.1(*)����I�������ꍇ
            //1.25.1.1�����n�f�[�^��update����B
            this.updateOrderData(l_orderUnit, l_orderSpec.getCalcUnitPrice(), l_orderSpec.getContractAmount(), l_strRouteDiv, new Boolean(l_blnRestraint));
            
            //1.25.1.2insert�M�p�V�K�������L���[(����ID : long)
            l_orderManager.insertMarginOpenHostOrder(l_orderUnit.getOrderId());
            
            EqTypeOrderUnit l_eqOrderUnit = null;
            try
            {
                l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            //1.25.1.3sendMQTrigger(�����P��)
            this.sendMQTrigger(l_eqOrderUnit);
            log.exiting(STR_METHOD_NAME);
            
        }
        //1.25.2(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            
            //1.25.2.1get�����G���[���R�R�[�h(�G���[�R�[�h : String)
			String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            //1.25.2.2�t�w�l�������������X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String)
			WEB3ToStopEquityOrderServiceInterceptor l_interceptor = new WEB3ToStopEquityOrderServiceInterceptor(l_strReasonCode);
            //1.25.2.3�����}�l�[�W���ɐ��������X�V�C�x���g�C���^�Z�v�^���Z�b�g����B
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            //1.25.2.4�������b�Z�[�W�I�u�W�F�N�g�𐶐�����B
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            //1.25.2.5���������������s���B
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            EqTypeMarketResponseReceiverCallbackService l_callbackService = 
                (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();            
            try
            {
	            ProcessingResult l_result = l_callbackService.process(l_responseMessage);
	            if (l_result.isFailedResult())
	            {
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    l_result.getErrorInfo(),
	                    this.getClass().getName() + "." + STR_METHOD_NAME);
	            }
	            
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is�\�񒍕��m�F�v
            // �����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderConfirmRequire =
                l_orderManager.isReserveOrderConfirmRequire(l_orderUnit);

            //invalidateAll�\�񒍕��P��(long)
            //�Y���̒����ɕR�t���\�񒍕�������ꍇ�A�����̎������s���B
            //�e�����̒���ID�F�@@�����P��.����ID
            if (l_blnIsReserveOrderConfirmRequire)
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }

			//1.25.2.6�]�͍Čv�Z���s���B
			l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }        
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�ԍϋt�w�l����)<BR>
     * �M�p�ԍϋt�w�l�����𔭒�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A���������������ꌏ�T�[�r�X�jsubmit�M�p�ԍϋt�w�l�����v�Q�ƁB<BR>
     *  ========================================================== <BR>
     * 1.4.1�i* �����c���`�F�b�N�j<BR>
     * �i�R�j �ԍϐ���(*1) �� �ԍω\�����c��(*2)�@@�̏ꍇ�A<BR>
     * �u�����c���s���G���[�v�̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00808<BR>
     *  ========================================================== <BR>
     * @@param l_orderUnit - (���������P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 434C7B010045
     */
    public void submitMarginSettleContractStopOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "submitMarginSettleContractStopOrder(EqTypeOrderUnit l_orderUnit) ";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        SubAccount l_subAccount = null;
        // �����P�ʂ̍Ď擾
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        try
        {
            //�⏕�������擾����B
            l_subAccount = l_accountMananger.getSubAccount(l_row.getAccountId(), l_row.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
                
        try
        {
            //1.1�w�肳�ꂽ�������t�w�l�����̑Ώۂł��邩�𔻒肷��B
            boolean l_blnProcessObject = this.isProcessObject(l_orderUnit);
            
            //1.2�i����t���[�F�@@is�����Ώہ�false�i�����ΏۊO�����j�̏ꍇ�j
            if (!l_blnProcessObject)
            {
                return;
            }
            
            //1.3�������`�F�b�N���s���B 
            //�i�����o�^���̔������ƁA���ݓ������狁�߂����������قȂ�ꍇ�͔����G���[�Ƃ���j
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datBizDate);
            
            //1.4�����ԍώw������擾����B
            EqTypeClosingContractSpec[] l_contractSpec = 
                ((EqTypeContractSettleOrderUnit)l_orderUnit).getContractsToClose();
            
            //1.5ArrayList�𐶐�����B
            List l_lisContractOrderEntry = new ArrayList();
            if (l_contractSpec != null)
            {
                //1.6�igetContractsToClose( )�̖߂�l�i�������ԍώw����j�v�f��(index)���ALoop�j
                for (int i = 0; i < l_contractSpec.length; i++)
                {
                    //1.6.1�i* �����c���`�F�b�N�j
                    //1.6.1.1�������擾����B
                    long l_lngContratId = l_contractSpec[i].getContractId(); 
                    WEB3EquityContract l_contract = null;
                    try
                    {
                        l_contract = (WEB3EquityContract)l_positionManager.getContract(l_lngContratId);
                    }
                    catch (NotFoundException l_ex)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                            this.getClass().getName() + STR_METHOD_NAME, 
                            l_ex.getMessage(), l_ex);
                    }
                    
                    //�i�P�j�ԍϐ���(*1)���擾����B�ԍϐ��� �� �����ԍώw����[index].�ԍϒ�������
                    double l_dblSettleQuantity = l_contractSpec[i].getQuantity();
                    if (Double.isNaN(l_dblSettleQuantity))
                    {
                        l_dblSettleQuantity = 0.0D;
                    }
                    
                    //1.6.1.2���������擾����B
                    double l_dblQuantity = l_contract.getQuantity();                   
                    if (Double.isNaN(l_dblQuantity))
                    {
                        l_dblQuantity = 0.0D;
                    }
                    
                    //1.6.1.3�����ɑ΂��錈�ϒ��������ʂ��擾����B
                    double l_dblLockedQuantity = l_contract.getLockedQuantity();
                    if (Double.isNaN(l_dblQuantity))
                    {
                        l_dblLockedQuantity = 0.0D;
                    }
                    
                    //1.6.1.4�����ɑ΂��錈�ϒ��������ʂ̂����A���Y�ԍϒ����̕��̒��������ʂ��擾����B
                    double l_dblOrderLockedQuantity = l_contract.getLockedQuantity(l_orderUnit.getOrderUnitId());
                                        
                    //�i�Q�j�ԍω\�����c��(*2)���Z�o����B�����́A�����ԍώw����[index].����ID�ɊY�����錚���I�u�W�F�N�g���擾���Ďg�p�B
                    //�ԍω\�����c�� �� ����.������ �| ����.getLockedQuantity( )
                    //�i�����b�N�����ʁj �{ ����.get���b�N������( )�i�����Y�������b�N�����ʁj
                    double l_dblContractAmount = l_dblQuantity - l_dblLockedQuantity + l_dblOrderLockedQuantity;
                    
                    //�i�R�j �ԍϐ���(*1) �� �ԍω\�����c��(*2)�@@�̏ꍇ�A�u�����c���s���G���[�v�̗�O��throw����B
                    if (l_dblSettleQuantity > l_dblContractAmount)
                    {
                        throw new WEB3BusinessLayerException(
                           WEB3ErrorCatalog.BUSINESS_ERROR_00808,
                           this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                    
                    //1.6.2EqTypeSettleContractOrderEntry�̃C���X�^���X�𐶐�����B
                    if (l_dblSettleQuantity > 0)
                    {
                        EqTypeSettleContractOrderEntry l_orderEntry = new EqTypeSettleContractOrderEntry(l_lngContratId, l_dblSettleQuantity);
                        //1.6.3��������EqTypeSettleContractOrderEntry��ArrayList�ɒǉ�����B
                        l_lisContractOrderEntry.add(l_orderEntry);
                        
                    }
                }
                //1.7EqTypeSettleContractOrderEntry�̔z��𐶐�����B
                EqTypeSettleContractOrderEntry[] l_orderEntries = new EqTypeSettleContractOrderEntry[l_lisContractOrderEntry.size()];
                l_lisContractOrderEntry.toArray(l_orderEntries);
                WEB3GentradeTrader l_trader = null;

                //1.8�i����t���[�F�@@�����P��.�����ID��null�̏ꍇ�̂݁j
                if (!l_row.getTraderIdIsNull())
                {

                    //1.8.1���҃I�u�W�F�N�g���擾����B 
                    try
                    {
                        l_trader = 
                            (WEB3GentradeTrader)l_finObjectManager.getTrader(l_row.getTraderId());

                    }
                    catch (NotFoundException l_nfe)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_nfe.getMessage(),
                            l_nfe);
                    }
                }

                Long l_firstOrderUnitId = null;
                if (!l_row.getFirstOrderUnitIdIsNull())
                {
                    l_firstOrderUnitId = new Long(l_row.getFirstOrderUnitId());
                }
                //1.9create�ԍϒ������e
                WEB3MarginSettleContractOrderSpec l_orderSpec = 
                    WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                        l_trader,  //���ҁF�@@�����P��.�����ID==null�̏ꍇ�́Anull�����P��.�����ID��null�̏ꍇ�́A�����ID�ɊY�����鈵�҃I�u�W�F�N�g
                        l_orderEntries,  //���ό����G���g���F�@@�쐬����EqTypeSettleContractOrderEntry�̔z��
                        l_row.getLimitPrice(),  //�w�l�F�@@�����P��.�w�l
                        l_row.getExecutionConditionType(),  //���s�����F�@@�����P��.���s����
                        l_row.getExpirationDate(),  //�����������F�@@�����P��.����������
                        l_row.getTaxType(),  //�ŋ敪�F�@@�����P��.�ŋ敪
                        l_row.getPriceConditionType(),  //�l�i�����F�@@�����P��.�l�i����
                        l_row.getOrderConditionType(),  //���������F�@@�����P��.��������
                        l_row.getOrderCondOperator(),  //�����������Z�q�F�@@�����P��.�����������Z�q
                        l_row.getStopOrderPrice(),  //�t�w�l��l�F�@@�����P��.�t�w�l��l
                        0,  //�iW�w�l�j�����w�l�F�@@0�i�Œ�j
                        l_row.getClosingOrderType(),  // ���Ϗ����敪
                        l_firstOrderUnitId);  //���񒍕��̒����P��ID�F�@@�����P��.���񒍕��̒����P��) 

                // 1.10�������擾����B
                WEB3EquityContract l_equityContract = null;
                try
                {
                    l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_orderEntries[0].getContractId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                
                //1.12validate�ԍϒ���(�⏕���� : SubAccount, �M�p�ԍϒ������e : EqTypeSettleContractOrderSpec, ���� : WEB3EquityContract)
                EqTypeNewOrderValidationResult l_result = 
                    l_orderManager.validateSettleContractOrder(l_subAccount, l_orderSpec, l_equityContract);
                
                if (l_result.getProcessingResult().isFailedResult())
                {
                    throw new WEB3BusinessLayerException(l_result.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
                }
                
                //1.13 ����������擾����B
                WEB3EquityTradedProduct l_tradeProduct = (WEB3EquityTradedProduct)l_equityContract.getTradedProduct();
                
                // 1.14���ϑ��������`�F�b�N
                EqtypeContractRow l_equityContractRow =
                    (EqtypeContractRow)l_equityContract.getDataSourceObject();
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                l_orderMgrResVal.validateSettleContractTotalQuantity(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_orderUnit.getOrderUnitId(),
                    l_tradeProduct,
                    l_orderSpec.getTaxType(),
                    l_equityContractRow.getRepaymentType(),
                    l_equityContractRow.getRepaymentNum(),
                    l_orderSpec.getTotalQuantity(),
                    l_equityContractRow.getContractType());
                
                //1.15create�萔��(����ID : long)
                WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderId());
                
                //1.16set������(������ : Timestamp)
                l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
                
                //1.17�T�Z���ϑ��v������擾����B
                WEB3EquityRealizedProfitAndLossPrice l_profitAndLossAmount = l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,  //�萔���F�@@�쐬�����萔���I�u�W�F�N�g
                    l_row.getLimitPrice(),  //�w�l�F�@@�����P��.�w�l
                    (WEB3GentradeSubAccount)l_subAccount,  //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
                    l_tradeProduct,  //��������F�@@�擾������������I�u�W�F�N�g
                    l_orderEntries,  //���ό����G���g���F�@@�쐬����EqTypeSettleContractOrderEntry�̔z��
                    l_orderSpec.getTotalQuantity(),  //�@@�ԍϒ������e.getTotalQuantity()
                    null,  //�����P�ʁF�@@null�i
                    0,  //�����萔�ʁF�@@0
                    0,  //������P���F�@@0
                    false);  //isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
                
                //1.18�i���s���ʂɉ����A�����P�ʃe�[�u����update����j
                //1.18.1(*)����I�������ꍇ
                //1.18.1.1�����n�f�[�^��update����B
                WEB3EquityFrontOrderService l_frontOrderService = 
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                String l_strRouteDiv = 
                    l_frontOrderService.getSubmitOrderRouteDiv(
                        l_tradeProduct, 
                        l_subAccount.getInstitution().getInstitutionCode(), 
                        l_row.getSonarTradedCode());
                //1.18.1.1�����n�f�[�^��update����B               
                this.updateOrderData(
                    l_orderUnit, 
                    l_profitAndLossAmount.getCalcUnitPrice(), 
                    l_profitAndLossAmount.getEstimatedRealizedProfitAndLossAmount(), 
                    l_strRouteDiv, 
                    null);
                
                //1.18.1.2insert�M�p�ԍϒ����L���[(����ID : long)
                l_orderManager.insertMarginCloseHostOrder(l_orderUnit.getOrderId());
                
                EqTypeOrderUnit l_eqOrderUnit = null;
                try
                {
                    l_eqOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BaseException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                
                //1.18.1.3sendMQTrigger(�����P��)
                this.sendMQTrigger(l_eqOrderUnit);
            }

        }
        //1.18.2(*)�������ɗ�O���X���[���ꂽ�ꍇ
        catch (Exception l_ex)
        { 
            log.error(l_ex.getMessage(), l_ex);
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }
            
            //1.18.2.1�X���[���ꂽ��O�ɑΉ����钍���G���[���R�R�[�h���擾����B
			String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());
            
            //1.18.2.2�t�w�l�������������X�V�C���^�Z�v�^(�����G���[���R�R�[�h : String)
			WEB3ToStopEquityOrderServiceInterceptor l_interceptor = new WEB3ToStopEquityOrderServiceInterceptor(l_strReasonCode);
            
            //1.18.2.3�����}�l�[�W���ɐ��������X�V�C�x���g�C���^�Z�v�^���Z�b�g����B
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            
            //1.18.2.4�������b�Z�[�W�I�u�W�F�N�g�𐶐�����B
            DefaultOrderInvalidatedMarketResponseMessage l_responseMessage = new DefaultOrderInvalidatedMarketResponseMessage(l_orderUnit.getOrderId());
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            EqTypeMarketResponseReceiverCallbackService l_callbackService = 
                (EqTypeMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService(); 
            
            //1.18.2.5���������������s���B
            try
            {
	            ProcessingResult l_result = l_callbackService.process(l_responseMessage);
	            if (l_result.isFailedResult())
	            {
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    l_result.getErrorInfo(),
	                    this.getClass().getName() + "." + STR_METHOD_NAME);
	            }
            }
            catch (Exception l_ex2)
            {
                String l_strErrMsg = "�����R���G���[�ƂȂ����t�w�l�����̎��������Ɏ��s���܂����B����ID�F[" + l_orderUnit.getOrderId() + "]";
                log.error(l_strErrMsg, l_ex2);
                if (l_errorInfo != null)
                {
                    if (l_errorInfo.error_tag.startsWith("SYSTEM_"))
                    {
                        throw new WEB3SystemLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_errorInfo,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strErrMsg);
                    }
                }
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_strErrMsg);
                }
            }

            // is�\�񒍕��m�F�v
            // �����P�ʁF�@@�����P��
            boolean l_blnIsReserveOrderConfirmRequire =
                l_orderManager.isReserveOrderConfirmRequire(l_orderUnit);

            //invalidateAll�\�񒍕��P��(long)
            //�Y���̒����ɕR�t���\�񒍕�������ꍇ�A�����̎������s���B
            //�e�����̒���ID�F�@@�����P��.����ID
            if (l_blnIsReserveOrderConfirmRequire)
            {
                WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
                    (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                l_updateService.invalidateAllOrderUnit(l_orderUnit.getOrderId());
            }
        }

        //1.19�]�͎c�����X�V����B
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�����f�[�^)<BR>
     * �����n�f�[�^��update���s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P�ʂ�clone���쐬����B<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B<BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�t�w�l���������iOK�j_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �R�j�@@�����f�[�^��update����B<BR>
     * �@@�g�����������}�l�[�W��.update�����f�[�^()���R�[������B<BR>
     * <BR>
     * �@@[update�����f�[�^()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�Q�j�ɂč쐬���������P��<BR>
     * �@@�@@is�����쐬�F�@@true�i�쐬����j<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_dblOrderPrice - (�����P��)<BR>
     * �T�Z��n����Z�o�Ɏg�p���������P���B<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@param l_blnShortSellingRestraint - (is�󔄂�K���Ώ�)<BR>
     * �󔄂�K���Ώۂ��ǂ����̃t���O<BR>
     * <BR>
     * true�F�@@�󔄂�K���Ώ�<BR>
     * false�F�@@�󔄂�K���ΏۂłȂ�<BR>
     * <BR>
     * ���V�K�������̏ꍇ�̂݃Z�b�g�B<BR>
     * �@@�ȊO�Anull�B<BR>
     * @@roseuid 434F72FD0270
     */
    protected void updateOrderData(
        EqTypeOrderUnit l_orderUnit, 
        double l_dblOrderPrice, 
        double l_dblEstimatedPrice, 
        String l_strOrderRootDiv, 
        Boolean l_blnShortSellingRestraint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateOrderData(EqTypeOrderUnit, double, double, String, Boolean ) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�p�����[�^.�����P�ʂ�clone���쐬����B
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams(l_row);
        
        // �Q�j�@@�P�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B
        //DB�X�V�d�l
        // �@@�u�t�w�l���������iOK�j_���������P�ʃe�[�u��.xls�v�Q�ƁB
        
        //���������ŏI�ʔ� = �i�����l�j + 1
        l_params.setLastOrderActionSerialNo(l_params.getLastOrderActionSerialNo() + 1);
        //������� = 2�F�������i�V�K�����j
        l_params.setOrderStatus(OrderStatusEnum.ORDERING);
        //�����̒����P��
        //�i* �T�Z��n����Z�o�Ɏg�p�����P���j
        l_params.setPrice(l_dblOrderPrice);
        //�����̊T�Z��n���
        //�i* �T�Z��n����v�Z���ʁj
        l_params.setEstimatedPrice(l_dblEstimatedPrice);
        
        //�V�K�������i�����J�e�S�� == "�V�K������"�j�̏ꍇ�̂݃Z�b�g�B�ȊO�A�i�����l�j
        //������is�󔄂�K���Ώ�==true�̏ꍇ�́A5�i"���i�K���Ώ�"�j�B
        //������is�󔄂�K���Ώ�==false�̏ꍇ�́A�u�����N�i"�ΏۊO"�j�B
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            if (l_blnShortSellingRestraint.booleanValue())
            {
                l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            else
            {
                l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
            }
        }
        
        //0:�����l
        l_params.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        //�����̔����o�H�敪
        l_params.setSubmitOrderRouteDiv(l_strOrderRootDiv);
        //1�F�����T�[�o
        l_params.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
        //���ݎ���
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //�t�w�l������������
        l_params.setStopOrderOrderedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�R�j�@@�����f�[�^��update����B
        //�g�����������}�l�[�W��.update�����f�[�^()���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_params);
        l_orderManager.updateOrderData(l_eqTypeOrderUnit, true);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (sendMQ�g���K)<BR>
     * MQ�g���K�𔭍s����K�v������ꍇ�́A<BR>
     * MQ�g���K�𔭍s����B<BR>
     * <BR>
     * �P�j�@@MQ�g���K�𔭍s���邩�ǂ����𔻒肷��B<BR>
     * �@@������ԊǗ�.is�g���K���s()���R�[������B<BR>
     * <BR>
     * �@@[is�g���K���s()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@null�i�Œ�j<BR>
     * �@@�@@�������P��.�����������w�肷��ƁA<BR>
     * �@@�@@�@@�@@�t�w�l����false���Ԃ����ׁB<BR>
     * <BR>
     * �Q�j�@@MQ�g���K���s�v�ۂ��擾����B<BR>
     * �@@������ؑ�.isMQ�g���K���s�o�H()���R�[������B<BR>
     * <BR>
     * �@@[isMQ�g���K���s�o�H()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F<BR>
     * �@@�@@�@@�����P��.�،����ID�ɊY������،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@�����P��.�����^�C�v<BR>
     * �@@�@@�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�����o�H�敪�F�@@�����P�ʂ̓�����<BR>
     * �@@�@@�t�����g�����V�X�e���敪�F<BR>
     * �@@�@@�@@���������T�[�r�X.get�t�����g�����V�X�e���敪(<BR>
     * �@@�@@�@@�@@�@@�����P��.�s��ID�ɊY������s��R�[�h, <BR>
     * �@@�@@�@@�@@�@@�����P��.getTradedProduct()�̎������.�X�����J�敪)<BR>
     * <BR>
     * �R�j�@@�P�j�A�Q�j�̖߂�l�������Ƃ�true�̏ꍇ�A<BR>
     * �@@�ȍ~�̏��������{����B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�������ɐݒ肷��f�[�^�R�[�h���擾����B<BR>
     * �@@�@@���������T�[�r�X.get������MQ�f�[�^�R�[�h()���R�[������B<BR>
     * <BR>
     * �@@�@@[get������MQ�f�[�^�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����o�H�敪�F�@@�����P�ʂ̓�����<BR>
     * <BR>
     * �@@�@@null���ԋp���ꂽ�ꍇ�A�������I���ireturn�j����B�@@<BR>
     * <BR>
     * �@@�R�|�Q�j�@@WEB3MQMessageSpec�𐶐�����B<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@<BR>
     * �@@�@@�@@�@@�����P��.���XID�ɊY�����镔�X.�،���ЃR�[�h<BR>
     * �@@�@@�@@�f�[�^�R�[�h�F�@@<BR>
     * �@@�@@�@@�@@���������T�[�r�X.get������MQ�f�[�^�R�[�h()�̖߂�l<BR>
     * <BR>
     * �@@�R�|�R�j�@@MQ�g���K�𔭍s����B<BR>
     * �@@�@@WEB3MQGatewayService.send()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[send()�Ɏw�肷�����]<BR>
     * �@@�@@�@@MQ���b�Z�[�W���e�F�@@�R�|�Q�j�ɂĐ��������C���X�^���X<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@roseuid 434F69C801B4
     */
    protected void sendMQTrigger(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "sendMQTrigger(EqTypeOrderUnit l_orderUnit)  ";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j�@@MQ�g���K�𔭍s���邩�ǂ����𔻒肷��B
            // �@@������ԊǗ�.is�g���K���s()���R�[������B
            boolean l_blnIsTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
            
            //�Q�j�@@MQ�g���K���s�v�ۂ��擾����B
            // �@@������ؑ�.isMQ�g���K���s�o�H()���R�[������B
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountMananger.getBranch(l_row.getBranchId());
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_row.getMarketId());
            WEB3EquityTradedProduct l_tradeProduct = (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            
            EqtypeTradedProductRow l_productRow = (EqtypeTradedProductRow)l_tradeProduct.getDataSourceObject();
            
            WEB3EquityFrontOrderService l_frontOrderService = 
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            
            String l_strSystemCode = 
                l_frontOrderService.getFrontOrderSystemCode(
                    l_market.getMarketCode(), 
                    l_productRow.getOpenOtcDiv());
            
            boolean l_blnIsEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_branch.getInstitution().getInstitutionCode(),
                l_row.getProductType(),
                l_market.getMarketCode(),
                l_row.getSubmitOrderRouteDiv(),
                l_strSystemCode);
            
            //�R�j�@@�P�j�A�Q�j�̖߂�l�������Ƃ�true�̏ꍇ�A<BR>
            // �@@�ȍ~�̏��������{����B
            if (l_blnIsTrigger && l_blnIsEnable)
            {
                //�R�|�P�j�@@�������ɐݒ肷��f�[�^�R�[�h���擾����B<BR>
                //�@@�@@���������T�[�r�X.get������MQ�f�[�^�R�[�h()���R�[������B
                String l_strDataCode = l_frontOrderService.getOrderMQDataCode(l_row.getSubmitOrderRouteDiv());
                //null���ԋp���ꂽ�ꍇ�A�������I���ireturn�j����B
                if (l_strDataCode == null)
                {
                    return;
                }
                
                //�R�|�Q�j�@@WEB3MQMessageSpec�𐶐�����B
                WEB3MQMessageSpec l_spec = new WEB3MQMessageSpec(l_branch.getInstitution().getInstitutionCode(), l_strDataCode);
                
                //�R�|�R�j�@@MQ�g���K�𔭍s����B<BR>
               // �@@�@@WEB3MQGatewayService.send()���\�b�h���R�[������B
                WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
                l_gatewayService.send(l_spec);
                
                log.exiting(STR_METHOD_NAME);
            }            
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
    }
    
    /**
     * (is�����Ώ�)<BR>
     * �w��̒������t�w�l�����̏����Ώۂł��邩�𔻒肷��B<BR>
     * <BR>
     * �����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B <BR>
     * �ȉ��̏����̂����ꂩ�ɊY������ꍇ�A<BR>
     * �����ΏۊO�Ƃ��Afalse��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �E�p�����[�^.�����P��.�����L����� != "�I�[�v��"<BR>
     * �E�p�����[�^.�����P��.�������� != "�t�w�l"<BR>
     * �E�p�����[�^.�����P��.���N�G�X�g�^�C�v == "�����T�[�o"<BR>
     * @@param l_orderUnit - (���������P��)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isProcessObject (EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isProcessObject (OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�w��̒������t�w�l�����̏����Ώۂł��邩�𔻒肷��B
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderingCondition = l_row.getOrderConditionType();
        String l_strRequestType = l_row.getRequestType();
        
        //�ȉ��̏����̂����ꂩ�ɊY������ꍇ�A
        //�����ΏۊO�Ƃ��Afalse��ԋp����B�ȊO�Atrue��ԋp����
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())
            || !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderingCondition)
            || WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
    }
    
    /**
     * �icreate���������������N�G�X�g�A�_�v�^�j<BR>
     * ���������������N�G�X�g�A�_�v�^���쐬����B<BR>
     * <BR>
     * �P�j�@@�������������������N�G�X�g�C���X�^���X�𐶐����A<BR>
     * �@@�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * �@@�����L�q�̍��ڂɂ�null���Z�b�g����B<BR>
     * <BR>
     * �@@���������F�@@�����������e.��������<BR>
     * �@@�����P���敪�F�@@�����P��.isMarketOrder() == true�̏ꍇ�A<BR>
     * �@@�@@"���s"�B�ȊO�A"�w�l"<BR>
     * �@@�����P���F�@@�����P��.�w�l <BR>
     * �@@�l�i�����F�@@�����P��.�l�i����<BR>
     * �@@���s�����F�@@���������}�l�[�W��.get���s�����iSONAR�j(�����P��.���s����)<BR>
     * �@@���������敪�F�@@���������}�l�[�W��.is�o����܂Œ����P��(�����P��) == true�̏ꍇ�A<BR>
     * �@@�@@"�o����܂Œ���"�B�ȊO�A"��������" <BR>
     * �@@�����L�������F�@@���������敪 == "�o����܂Œ���"�̏ꍇ�A<BR>
     * �@@�@@�����P��.�����������t�B�ȊO�Anull�B<BR>
     * �@@���������敪�F�@@�����P��.�������� <BR>
     * �@@�t�w�l�p���������P���F�@@�����P��.�t�w�l��l<BR>
     * �@@�t�w�l�p�����������Z�q�F�@@�����P��.�����������Z�q<BR>
     * �@@ID�F�@@�����P��.����ID<BR>
     * �@@�m�F���P���F�@@�����������e.�����P��<BR>
     * �@@�m�F���������F�@@�����������e.������<BR>
     * <BR>
     * �Q�j�@@���������������N�G�X�g�A�_�v�^.create()���\�b�h��<BR>
     * �@@�R�[�����A���������������N�G�X�g�A�_�v�^�𐶐�����B <BR>
     * <BR>
     * �@@[create()�Ɏw�肷�����]<BR> 
     * �@@�@@���N�G�X�g�F�@@�P�j�ɂč쐬�������N�G�X�g�f�[�^<BR>
     * <BR>
     * �R�j�@@�����������������������N�G�X�g�A�_�v�^��ԋp����B<BR>
     * @@param l_orderUnit - (���������P��)
     * @@param l_orderSpec - (�����������e)
     * @@return WEB3EquityChangeOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3EquityChangeOrderRequestAdapter createEquityChangeOrderRequestAdapter(
    	EqTypeOrderUnit l_orderUnit,
		WEB3EquityNewCashBasedOrderSpec l_orderSpec) 
		throws WEB3BaseException
    {
    	//�P�j�@@�������������������N�G�X�g�C���X�^���X�𐶐����A
    	//�@@�ȉ��̒ʂ�v���p�e�B���Z�b�g����B
    	//�@@�����L�q�̍��ڂɂ�null���Z�b�g����B
		WEB3EquityChangeCompleteRequest l_request = 
			new WEB3EquityChangeCompleteRequest();
		EqtypeOrderUnitRow l_row = 
			(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		WEB3EquityTradingModule l_tradingModule
			= (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityOrderManager l_eqOrderManager
			= (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
		
		//���������F�@@�����������e.��������
		l_request.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderSpec.getQuantity());
		//�����P���敪�F�@@�����P��.isMarketOrder() == true�̏ꍇ�A
		//�@@�@@"���s"�B�ȊO�A"�w�l"
		l_request.orderPriceDiv = 
			l_orderUnit.isMarketOrder()? WEB3OrderPriceDivDef.MARKET_PRICE : WEB3OrderPriceDivDef.LIMIT_PRICE;
		//�����P���F�@@�����P��.�w�l 
        if ( !l_row.getLimitPriceIsNull() )
        {
            l_request.limitPrice = WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice());
        }
		//�l�i�����F�@@�����P��.�l�i����
		l_request.priceCondType = l_row.getPriceConditionType();
		//���s�����F�@@���������}�l�[�W��.get���s�����iSONAR�j(�����P��.���s����)
		l_request.execCondType = 
			l_eqOrderManager.getExecutionConditionTypeSonar(l_row.getExecutionConditionType());
		//���������敪�F�@@���������}�l�[�W��.is�o����܂Œ����P��(�����P��) == true�̏ꍇ�A
		//�@@�@@"�o����܂Œ���"�B�ȊO�A"��������"
		l_request.expirationDateType = 
			l_eqOrderManager.isCarriedOrderUnit(l_orderUnit)? 
				WEB3OrderExpirationDateTypeDef.CARRIED_ORDER : WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
		//�����L�������F�@@���������敪 == "�o����܂Œ���"�̏ꍇ�A
		//�@@�@@�����P��.�����������t�B�ȊO�Anull�B
		if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
		{
			l_request.expirationDate = l_row.getExpirationDate();	 
		}
		//���������敪�F�@@�����P��.�������� 
		l_request.orderCondType = l_row.getOrderConditionType();
		//�t�w�l�p���������P���F�@@�����P��.�t�w�l��l
		l_request.stopOrderCondPrice = 
			WEB3StringTypeUtility.formatNumber(l_row.getStopOrderPrice());
		//�t�w�l�p�����������Z�q�F�@@�����P��.�����������Z�q
		l_request.stopOrderCondOperator = l_row.getOrderCondOperator();
		//�v�w�l�p���������P��
		l_request.wlimitOrderCondPrice = null;
		//�v�w�l�p�����������Z�q
		l_request.wlimitOrderCondOperator = null;
		//�v�w�l�p�����P���敪
		l_request.wLimitOrderPriceDiv = null;
		//W�w�l�p�����P��
		l_request.wlimitOrderCondPrice = null;
		//ID�F�@@�����P��.����ID
		l_request.id = Long.toString(l_row.getOrderId());
		//�m�F���P���F�@@�����������e.�����P��
		l_request.checkPrice = 
			WEB3StringTypeUtility.formatNumber(l_orderSpec.getOrderUnitPrice());
		//�m�F���������F�@@�����������e.������
		l_request.checkDate = l_orderSpec.getOrderBizDate();
		
		//�Q�j�@@���������������N�G�X�g�A�_�v�^.create()���\�b�h��
		//�@@�R�[�����A���������������N�G�X�g�A�_�v�^�𐶐�����B 
		WEB3EquityChangeOrderRequestAdapter l_requestAdapter = 
			WEB3EquityChangeOrderRequestAdapter.create(l_request);
		
		//�R�j�@@�����������������������N�G�X�g�A�_�v�^��ԋp����B
		return l_requestAdapter;
    }

    /**
     * (create�������������l�ڍ�)<BR>
     * ���������l�ڍ׃I�u�W�F�N�g�𐶐����A<BR>
     * �p�����[�^.�����P�ʂ̓��e���v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[is�o����܂Œ����P��()�̈���]<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �R�j�@@�������������l�ڍ׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�������������l�ڍ�()�̈���]<BR>
     * �@@�@@�@@�����㎷�s�����F�@@�����P��Row.���s����<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * �@@�@@�@@������is�o����܂Œ����F�@@is�o����܂Œ����P��()�̖߂�l<BR>
     * �@@�@@�@@������l�i�����F�@@�����P��Row.�l�i����<BR>
     * �@@�@@�@@�����㔭�������F�@@�����P��Row.��������<BR>
     * �@@�@@�@@�����㔭���������Z�q�F�@@�����P��Row.�����������Z�q<BR>
     * �@@�@@�@@������t�w�l��l�F�@@�����P��Row.�t�w�l��l<BR>
     * �@@�@@�@@������iW�w�l�j�����w�l�F�@@0<BR>
     * �@@�@@�@@�����㒍���������F�@@�����P��Row.�����������t<BR>
     * �@@�@@�@@������iW�w�l�j���s�����F�@@null<BR>
     * �@@�@@�@@�iW�w�l�j�L����ԋ敪�F�@@null<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return WEB3EquityChangeOrderUnitEntry
     * @@throws WEB3BaseException
     */
    public WEB3EquityChangeOrderUnitEntry createEquityChangeOrderUnitEntry(
        EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createEquityChangeOrderUnitEntry(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�����P��.getDataSourceObject()���R�[������
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B
        //�@@�@@�@@[is�o����܂Œ����P��()�̈���]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager  =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsOrderUntilDeadLine = l_orderManager.isCarriedOrderUnit(l_orderUnit);

        //�������������l�ڍ׃I�u�W�F�N�g�𐶐�����B
        WEB3EquityChangeOrderUnitEntry l_orderUnitEntry =
            new WEB3EquityChangeOrderUnitEntry(
                l_orderUnitRow.getExecutionConditionType(),
                l_orderUnit,
                l_blnIsOrderUntilDeadLine,
                l_orderUnitRow.getPriceConditionType(),
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrderCondOperator(),
                l_orderUnitRow.getStopOrderPrice(),
                0,
                l_orderUnitRow.getExpirationDate(),
                null,
                null);

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitEntry;
    }
}@
