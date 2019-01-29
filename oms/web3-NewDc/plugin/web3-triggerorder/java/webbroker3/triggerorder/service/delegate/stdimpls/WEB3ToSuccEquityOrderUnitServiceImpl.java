head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���������������ꌏ�T�[�r�XImpl(WEB3ToSuccEquityOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
Revesion History : 2008/05/05 ���z(���u) �d�l�ύX���f��No.314
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerPersistenceEventInterceptor;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapMarginUpdateInterceptor;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderUnitService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A���������������ꌏ�T�[�r�XImpl)<BR>
 * �A���������������ꌏ�T�[�r�X�����N���X�B<BR>
 * <BR>
 * @@author ������ <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccEquityOrderUnitServiceImpl implements WEB3ToSuccEquityOrderUnitService 
{
    
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToSuccEquityOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToSuccEquityOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * (submit������������)<BR>
     * �������������𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A�����������ꌏ�T�[�r�X�jsubmit�������������v���Q�ƁB<BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    public void submitEquityOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitEquityOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�����\�񒍕��P��
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;

        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�����\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
		{
        	//get������(�m�F�������� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �m�F���������F�@@�����̊����\�񒍕��P��.������
        	l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
        	WEB3GentradeTradingTimeManagement.getOrderBizDate(
    			WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));
        	
        	//����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
        	WEB3GentradeTrader l_trader = null;
        	if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
        	{
            	//getTrader(�����ID : long)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
                //  �����ID�F�@@�\�񒍕��P��.�����ID
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnitRow.getTraderId());
        	}

        	//get�\�񒍕����s�P��( )(�����\�񒍕��P��Impl::get�\�񒍕����s�P��)
            double l_dblRsvOrderExecPrice = l_rsvEqOrderUnit.getRsvOrderExecPrice();
        	
            //create�������e(�،���ЃR�[�h : String, ���� : ����, �s��R�[�h : String, �����R�[�h : String, 
            //		���� : double, �w�l : double, ���s���� : EqTypeExecutionConditionType, �ŋ敪 : TaxTypeEnum, 
            //		���������� : Timestamp, is������ : boolean, �����`���l�� : String, �l�i���� : String, 
            //		�������� : String, �����������Z�q : String, �t�w�l��l : double, �iW�w�l�j�����w�l : double, 
            //		���񒍕��̒����P��ID : Long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            //���X���擾
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_rsvEqOrderUnit.getBranchId());
            //�s����擾
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_rsvEqOrderUnitRow.getMarketId());
            //�������擾
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
            Product l_product = l_productManager.getProduct(l_rsvEqOrderUnitRow.getProductId());
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();

            Long l_lngFirstOrderUnitId = null;
            if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_lngFirstOrderUnitId = new Long(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
            }
            else
            {
                l_lngFirstOrderUnitId = null;
            }

            WEB3EquityNewCashBasedOrderSpec l_orderSpec =
                WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
            		l_branch.getInstitution().getInstitutionCode(),
            		l_trader,
            		l_market.getMarketCode(),
                    l_productRow.getProductCode(),
            		l_rsvEqOrderUnit.getQuantity(),
            		l_dblRsvOrderExecPrice,
            		EqTypeExecutionConditionType.NONE,
    				l_rsvEqOrderUnit.getTaxType(),
    				l_rsvEqOrderUnit.getExpirationTimestamp(),
    				l_rsvEqOrderUnit.isSellOrder(),
    				l_rsvEqOrderUnitRow.getOrderChanel(),
                    WEB3PriceConditionDef.DEFAULT,
    				WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0,
                    0,
                    l_lngFirstOrderUnitId);

            //set�萔�����i�R�[�h(�萔�����i�R�[�h : String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �萔�����i�R�[�h.��ꊔ���i�Œ�ŃZ�b�g�j 
            l_orderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
            
            //create�萔��(���X : ���X, ����R�[�h�iSONAR�j : String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  ���X�F�@@�\�񒍕��P��.���XID �̕��X�I�u�W�F�N�g  
            //  ����R�[�h�iSONAR�j�F�@@"���ʊ���" 
            WEB3GentradeCommission l_commission = l_orderSpec.createCommission(
                l_branch, 
                WEB3TransactionTypeSONARDef.MARKET_TRADING);
            
            //validate������������(�⏕���� : SubAccount, �����������e : EqTypeNewCashBasedOrderSpec)
            //  �����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //      �⏕�����F�@@�����̗\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g  
            //      �����������e�F�@@create�������e( )�ō쐬���������������e�I�u�W�F�N�g 
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(
                l_rsvEqOrderUnit.getAccountId(),
                l_rsvEqOrderUnit.getSubAccountId());
            EqTypeNewOrderValidationResult l_orderValidationResult =
                l_orderManager.validateNewCashBasedOrder(
                    l_subAccount, 
                    l_orderSpec);

            //{validate������������()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate������������()}�����s�̏ꍇ�B");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get�������( )(�����\�񒍕��P��Impl::get�������)
            TradedProduct l_tradedProduct = l_rsvEqOrderUnit.getTradedProduct();
            
            //is���s����( )(�����\�񒍕��P��Impl::is���s����)
            boolean l_blnIsMarketOrder = l_rsvEqOrderUnit.isMarketOrder();
            
            //����t���[�F�@@���s�����̏ꍇ
            double l_dblCalcCurrentPrice = 0;
            if (l_blnIsMarketOrder)
            {
                //calc����(�萔�����i�R�[�h : String, ������� : �������, �⏕���� : �⏕����, isSTOP���l�� : boolean)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
                //  �萔�����i�R�[�h�F�@@�萔��.get�萔�����i�R�[�h( )  
                //  ��������F�@@�\�񒍕��P��.get�������()  
                //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g  
                //  isSTOP���l���F�@@�����������e.isBuyOrder( )==true�i���������j�̏ꍇ�Atrue�B  
                //�@@�@@�@@�@@�@@            �ȊO�Afalse�B
                boolean l_blnIsStop = false;
                if (l_orderSpec.isBuyOrder())
                {
                    l_blnIsStop = true;
                }

                l_dblCalcCurrentPrice = l_orderManager.calcCurrentPrice(
                    l_commission.getCommissionProductCode(),
                    (WEB3EquityTradedProduct)l_tradedProduct,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_blnIsStop);
            }

            //set�����P��(�����P�� : double)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //���s�����̏ꍇ 
            //  �����P���F�@@EQTYPE�̊g�����������}�l�[�W��.calc����()�̖߂�l 
            //�w�l�����̏ꍇ 
            //  �����P���F�@@�����\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            if (l_blnIsMarketOrder)
            {
                l_orderSpec.setOrderUnitPrice(l_dblCalcCurrentPrice);
            }
            else
            {
                l_orderSpec.setOrderUnitPrice(l_dblRsvOrderExecPrice);
            }

            //calc�T�Z��n���(�萔�� : �萔��, �v�Z�P�� : double, �⏕���� : SubAccount, 
            //  ������� : �������, ���� : double, is������ : boolean, ��萔�� : double, 
            //  ���v�����z : double, isSkip���z�`�F�b�N : boolean, is�S���l�� : boolean)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �萔���F�@@�����������e.get�萔��( )  
            //  �v�Z�P���F�@@�����������e.get�����P��( )  
            //  �⏕�����F�@@�����̗\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g  
            //  ��������F�@@�擾������������I�u�W�F�N�g  
            //  �����F�@@�����������e.getQuantity( ) 
            //  is�������F�@@�����������e.isSellOrder( )  
            //  ��萔�ʁF�@@0  
            //  ���v�����z�F�@@0  
            //  isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j  
            //  is�S���l���F�@@true�i�l������j
            WEB3EquityEstimatedDeliveryPrice l_estimateDeliveryAmount = l_orderManager.calcEstimateDeliveryAmount(
                l_orderSpec.getCommission(),
                l_orderSpec.getOrderUnitPrice(),
                l_subAccount,
                (WEB3EquityTradedProduct)l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                0,
                0,
                false,
                true);

            //set�T�Z��n���(�T�Z���z : double)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //�T�Z���z�F�@@calc�T�Z��n����߂�l�I�u�W�F�N�g.get�T�Z��n���( )
            l_orderSpec.setEstimateDeliveryAmount(l_estimateDeliveryAmount.getEstimateDeliveryAmount());

            //���������C���^�Z�v�^()
            WEB3EquityOrderManagerPersistenceEventInterceptor l_interceptor = 
                new WEB3EquityOrderManagerPersistenceEventInterceptor();

            //set�����������e(�����������e : �����������e)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �����������e�F�@@�������������������e�I�u�W�F�N�g
            l_interceptor.setEquityOrderSpec(l_orderSpec);

            //set�����o�H�敪(�����o�H�敪 : String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �����o�H�敪�F�@@�����̗\�񒍕��P�ʂ̓����� 
            l_interceptor.setOrderRootDiv(l_rsvEqOrderUnitRow.getOrderRootDiv());
            
            //set�󒍓���(�󒍓��� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �󒍓����F�@@�����̗\�񒍕��P�ʂ̓�����
            l_interceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], 
            //  ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�����̗\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g  
            //  �������e�C���^�Z�v�^�F�@@�����������������C���^�Z�v�^  
            //  �������e�F�@@�����R���Ɏg�p���������������e�I�u�W�F�N�g  
            //  ������ʁF�@@�\�񒍕��P��.get�������()
            //  �]�͍X�V�t���O�F�@@true�i�������j
            WEB3TPTradingPowerService l_tradingpowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingpowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                new Object[]{l_interceptor},
                new Object[]{l_orderSpec},
                l_rsvEqOrderUnit.getOrderType(),
                true);

            if (!l_tradingPowerResult.isResultFlg())
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_orderManager.throwTpErrorInfo(l_tradingPowerResult, l_rsvEqOrderUnit.getOrderType());
            }

            //setThreadLocalPersistenceEventInterceptor(���������C���^�Z�v�^ : EqTypeOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            //submitNewCashBasedOrder(arg0 : SubAccount, arg1 : EqTypeNewCashBasedOrderSpec, arg2 : long, 
            //  arg3 : �_���r���[::java::lang::String, arg4 : boolean)
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();
            EqTypeOrderSubmissionResult l_submissionResult = l_orderManager.submitNewCashBasedOrder(
                l_subAccount,
                l_orderSpec,
                l_rsvEqOrderUnitRow.getOrderId(),
                l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                true);

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����B
            if (l_submissionResult.getProcessingResult().isSuccessfulResult())
            {
                //1.21.1 set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
                //  �����^�C�v�F�@@�\�񒍕��P��.�����^�C�v���Z�b�g
                //  ����ID�F�@@�����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnitRow.getProductType(),
                    l_rsvEqOrderUnitRow.getOrderId());
            }
            //���s���ʃG���[�B
            else
            {
                log.debug("{submitNewCashBasedOrder()}���G���[�B");

                throw new WEB3BaseException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
		}
		catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�����\�񒍕��P�ʍs : �����\�񒍕��P��Row, �����G���[�R�[�h : String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //�����\�񒍕��P�ʍs�F�@@�����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g 
            //�����G���[�R�[�h�F�@@����������O�I�u�W�F�N�g��ErrorInfo.error_code  
            //�@@�@@�@@�@@�@@�@@            �i�G���[�̌����̓��肪�\��BusinessError�^SystemError�̃G���[�R�[�h�j
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�V�K������)<BR>
     * �M�p�V�K�������𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A���������������ꌏ�T�[�r�X�j<BR>
     * submit�M�p�V�K�������v���Q�ƁB<BR>
     * <BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 433298A400C5
     */
    public void submitMarginOpenContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMarginOpenContractOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        //�����\�񒍕��P��
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;

        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�����\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
        {
            //get������(�m�F�������� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�m�F���������F�@@�����̊����\�񒍕��P��.������
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(�����ID : long)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����
                //  �����ID�F�@@�\�񒍕��P��.�����
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnit.getTraderId());
            }

            //is������()
            boolean l_blnIsBuyOrder = l_rsvEqOrderUnit.isBuyOrder();
            
            //get����( )(�����\�񒍕��P��Impl::get����)
            //�A�C�e���̒�`
            //  ���������I�u�W�F�N�g���擾����B
            Product l_product = l_rsvEqOrderUnit.getProduct();

            //get�s��( )(�����\�񒍕��P��Impl::get�s��)
            //�A�C�e���̒�`
            //  �s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_market = l_rsvEqOrderUnit.getMarket();

            //get�\�񒍕����s�P��( )(�����\�񒍕��P��Impl::get�\�񒍕����s�P��)
            //�A�C�e���̒�`
            //  �\�񒍕��̎��s�P�����擾����B
            double l_dblRsvOrderExecPrice = l_rsvEqOrderUnit.getRsvOrderExecPrice();

            //[create�V�K���������e( )�F�����ݒ�d�l]
            //���ҁF�@@�\�񒍕��P��.�����ID==null�̏ꍇ�́Anull
            //        �\�񒍕��P��.�����ID��null�̏ꍇ�́A�����ID�ɊY�����鈵�҃I�u�W�F�N�g
            //is�����F�@@�\�񒍕��P��.is������()�̖߂�l
            //�����R�[�h�F�@@�\�񒍕��P��.get����().�����R�[�h
            //�s��R�[�h�F�@@�\�񒍕��P��.get�s��().�s��R�[�h
            //���ʁF�@@�\�񒍕��P��.��������
            //�w�l�F�@@�\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            //���s�����F�@@"�����Ȃ�"
            //�����������F�@@�\�񒍕��P��.����������
            //�ŋ敪�F�@@�\�񒍕��P��.�ŋ敪
            //�l�i�����F�@@"�����Ȃ�"
            //���������F�@@"�����Ȃ�"
            //�����������Z�q�F�@@null
            //�t�w�l��l�F�@@0
            //�iW�w�l�j�����w�l�F�@@0
            //�ٍϋ敪�F�@@�\�񒍕��P��.�ٍϋ敪
            //�ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l
            //���񒍕��̒����P��ID�F�@@�\�񒍕��P��.���񒍕��̒����P��ID
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
            //�ٍϋ敪
            String l_strRepaymentType = l_rsvEqOrderUnitRow.getRepaymentType();
            //�ٍϊ����l
            int l_intRepaymentNum = l_rsvEqOrderUnitRow.getRepaymentNum();
            //�V�K���������e
            Long firstOrderUnitId = null;
            if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                firstOrderUnitId = new Long(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
            }
            WEB3MarginOpenContractOrderSpec l_openContractOrderSpec = 
                WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                    l_trader, 
                    l_blnIsBuyOrder, 
                    l_productRow.getProductCode(), 
                    l_market.getMarketCode(), 
                    l_rsvEqOrderUnit.getQuantity(), 
                    l_dblRsvOrderExecPrice,
                    EqTypeExecutionConditionType.NONE,
                    l_rsvEqOrderUnitRow.getExpirationDate(), 
                    l_rsvEqOrderUnit.getTaxType(), 
                    WEB3PriceConditionDef.DEFAULT,
                    WEB3OrderingConditionDef.DEFAULT,
                    null, 
                    0, 
                    0, 
                    l_strRepaymentType, 
                    l_intRepaymentNum, 
                    firstOrderUnitId);

            //set������(������ : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �������F�@@������ԊǗ�.get������(Date)�̖߂�l
            l_openContractOrderSpec.setBizDate(l_datOrderBizDate);

            //create�萔��(�⏕���� : �⏕����, �s��R�[�h : String, ������ : Date, 
            //  �����`���l�� : String, �M�p����敪 : String, �ٍϊ����l : double, �����J�e�S�� : OrderCategEnum)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�\�񒍕��P�ʂ̌���ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
            //  �s��R�[�h�F�@@�\�񒍕��P��.get�s��().�s��R�[�h  
            //  �������F�@@�\�񒍕��P��.������ 
            //  �����`���l���F�@@�\�񒍕��P��.���񒍕��̒����`���l�� 
            //  �M�p����敪�F�@@�\�񒍕��P��.�ٍϋ敪  
            //  �ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l 
            //  �����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getBizLogicProvider();
                    
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            //�⏕�����F�@@�\�񒍕��P�ʂ̌���ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_rsvEqOrderUnit.getAccountId(),
                    l_rsvEqOrderUnit.getSubAccountId());
            //�萔��
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
                l_subAccount, 
                l_market.getMarketCode(), 
                l_datOrderBizDate, 
                l_rsvEqOrderUnitRow.getOrderChanel(), 
                l_strRepaymentType, 
                l_intRepaymentNum, 
                OrderCategEnum.OPEN_MARGIN);
                
            //isLimitOrder()
            boolean l_blnIsLimitOrder = l_openContractOrderSpec.isLimitOrder();
            
            //setIs�w�l(is�w�l : boolean)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //is�w�l�F�@@�M�p�V�K���������e.isLimitOrder()�̖߂�l
            l_commission.setIsLimitPrice(l_blnIsLimitOrder);
            
            //isMarketOrder()
            boolean l_blnIsMarketOrder = l_openContractOrderSpec.isMarketOrder();
            
            //get�������()(�����\�񒍕��P��Impl::get�������)
            WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct)l_rsvEqOrderUnit.getTradedProduct();
            
            //get�萔�����i�R�[�h()
            String l_strCommissionProductCode = l_commission.getCommissionProductCode();
            
            //[calc�V�K���v�Z�P��( )�F�����ݒ�d�l]
            //  is�V�K���F�@@true�Œ�
            //  is�����F�@@�\�񒍕��P��.is������()�̖߂�l
            //  is���s�F�@@�M�p�V�K���������e.isMarketOrder()�̖߂�l
            //  �w�l�F�@@�\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            //  ��������F�@@�\�񒍕��P��.get�������()
            //  �萔�����i�R�[�h�F�@@�萔��.get�萔�����i�R�[�h()
            double l_dblOpenMarginCalcUnitPrice =
                l_orderManager.calcOpenMarginCalcUnitPrice(
                    true,
                    l_blnIsBuyOrder,
                    l_blnIsMarketOrder,
                    l_dblRsvOrderExecPrice,
                    l_subAccount,
                    l_tradedProduct,
                    l_strCommissionProductCode);

            //set�v�Z�P��(�v�Z�P�� : double)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //�v�Z�P���F�@@�g�����������}�l�[�W��.calc�V�K���v�Z�P��()�̖߂�l
            l_openContractOrderSpec.setCalcUnitPrice(l_dblOpenMarginCalcUnitPrice);

            //[calc�����������( )�F�����ݒ�d�l]
            //  �萔���F�@@�쐬�����萔���I�u�W�F�N�g
            //  �v�Z�P���F�@@�M�p�V�K���������e.get�v�Z�P��()
            //  �⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
            //  ��������F�@@�擾������������I�u�W�F�N�g
            //  �����F�@@�@@�M�p�V�K���������e.getQuantity()
            //  ��萔�ʁF�@@0
            //  ���v�����z�F�@@0
            //  isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j�Œ�
            double l_dblContractAmountAtOrder = l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_openContractOrderSpec.getCalcUnitPrice(),
                l_subAccount,
                l_tradedProduct,
                l_openContractOrderSpec.getQuantity(),
                0,
                0,
                false);

            //set�����(����� : double)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  ������F�@@�g�����������}�l�[�W��.calc�����������()�̖߂�l
            l_openContractOrderSpec.setContractAmount(l_dblContractAmountAtOrder);
            
            //validate�V�K������(�⏕���� : SubAccount, �M�p�V�K���������e : EqTypeOpenContractOrderSpec)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
            //  �M�p�V�K���������e�F�@@�쐬�����M�p�V�K���������e�I�u�W�F�N�g
            WEB3MarginNewOrderValidationResult l_orderValidationResult =
                (WEB3MarginNewOrderValidationResult)l_orderManager.validateOpenContractOrder(
                    l_subAccount,
                    l_openContractOrderSpec);

            //{validate�V�K������()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�V�K������()}�����s�̏ꍇ�B");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //calc�ϑ��萔��(�萔�� : �萔��, �⏕���� : SubAccount)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �萔���F�@@�쐬�����萔���I�u�W�F�N�g 
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            l_bizLogicProvider.calcCommission(
                l_commission,
                l_subAccount);
                
            //get�󔄂�K���Ώۃt���O()
            boolean l_blnShortSellingRestraint = l_orderValidationResult.getShortSellingRestraint();

            //�M�p�V�K���X�V�C���^�Z�v�^(�M�p�V�K���������e : �M�p�V�K���������e, �萔�� : �萔��, 
            //  ���񒍕��̒����`���l�� : String, �����o�H�敪 : String, �󔄂�K���Ώۃt���O : boolean)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �M�p�V�K���������e�F�@@�쐬�����M�p�V�K���������e�I�u�W�F�N�g  
            //  �萔���F�@@�쐬�����萔���I�u�W�F�N�g  
            //  ���񒍕��̒����`���l���F�@@�\�񒍕��P��.���񒍕��̒����`���l�� 
            //  �����o�H�敪�F�@@�\�񒍕��P��.�����o�H�敪  
            //  �󔄂�K���Ώۃt���O�F�@@validate�V�K������( )�̖߂�l��  
            //   �M�p�V�K���V�K���������R�����ʃI�u�W�F�N�g.get�󔄂�K���Ώۃt���O( )
            WEB3MarginOpenMarginUpdateInterceptor l_openMarginUpdateInterceptor =
                new WEB3MarginOpenMarginUpdateInterceptor(
                    l_openContractOrderSpec,
                    l_commission,
                    l_rsvEqOrderUnitRow.getOrderChanel(),
                    l_rsvEqOrderUnitRow.getOrderRootDiv(),
                    l_blnShortSellingRestraint);
                
            //set�󒍓���(�󒍓��� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �󒍓����F�@@�\�񒍕��P��.�󒍓���
            l_openMarginUpdateInterceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], 
            //  ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g  
            //  �������e�C���^�Z�v�^�F�@@���������M�p�V�K���X�V�C���^�Z�v�^  
            //  �������e�F�@@�����R���Ɏg�p�����M�p�V�K���������e�I�u�W�F�N�g  
            //  ������ʁF�@@�\�񒍕��P��.get�������() 
            //  �]�͍X�V�t���O�F�@@true�i�������j
            WEB3TPTradingPowerService l_tradingpowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_objOpenMarginUpdateInterceptor = new Object[]{l_openMarginUpdateInterceptor};
            Object[] l_objOpenContractOrderSpec = new Object[]{l_openContractOrderSpec};
            WEB3TPTradingPowerResult l_tpResult = l_tradingpowerService.validateTradingPower(
                l_subAccount,
                l_objOpenMarginUpdateInterceptor,
                l_objOpenContractOrderSpec,
                l_rsvEqOrderUnit.getOrderType(),
                true);

            //is����t���O()
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //����t���[�F�@@����]�͌���.is����t���O( )==true�̏ꍇ�̂�
            EqTypeOrderSubmissionResult l_orderSubmissionResult = null;
            if (l_blnIsResultFlg)
            {
                //setThreadLocalPersistenceEventInterceptor(�M�p�V�K���X�V�C���^�Z�v�^ : 
                //  EqTypeOrderManagerPersistenceEventInterceptor)
                l_orderManager.setThreadLocalPersistenceEventInterceptor(l_openMarginUpdateInterceptor);

                //[submitOpenContractOrder( )�F�����ݒ�d�l]
                //  arg0�i�⏕�����j�F�@@�擾�����⏕�����I�u�W�F�N�g
                //  arg1�i�M�p�V�K���������e�j�F�@@create�V�K���������e()�̖߂�l
                //  arg2�i�����h�c�j�F�@@�\�񒍕��P��.get����ID()
                //  arg3�i����p�X���[�h�j�F�@@�ڋq.getTradingPassword()�̖߂�l��decrypt�����l
                //  arg4�iisSkip�����R���j�F�@@true
                MainAccount l_mainAccount = l_subAccount.getMainAccount();
                WEB3Crypt l_crypt = new WEB3Crypt();
                l_orderSubmissionResult =
                    l_orderManager.submitOpenContractOrder(
                        l_subAccount,
                        l_openContractOrderSpec,
                        l_rsvEqOrderUnit.getOrderId(),
                        l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                        true);
            }
            else
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_orderManager.throwTpErrorInfo(l_tpResult, l_rsvEqOrderUnit.getOrderType());
            }

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::
                //  set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
                //  �����^�C�v�F�@@�����̗\�񒍕��P��.�����^�C�v 
                //  ����ID�F�@@�����̗\�񒍕��P��.get����ID()
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnit.getProductType(),
                    l_rsvEqOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitOpenContractOrder()}���G���[�ꍇ�B");

                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�����\�񒍕��P�ʍs : �����\�񒍕��P��Row, �����G���[�R�[�h : String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //�����\�񒍕��P�ʍs�F�@@�����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g 
            //�����G���[�R�[�h�F�@@����������O�I�u�W�F�N�g��ErrorInfo.error_code  
            //�@@�@@�@@�@@�@@�@@            �i�G���[�̌����̓��肪�\��BusinessError�^SystemError�̃G���[�R�[�h�j
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�ԍϒ���)<BR>
     * �M�p�ԍϒ����𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A���������������ꌏ�T�[�r�X�jsubmit�M�p�ԍϒ����v���Q�ƁB<BR>
     * <BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 434622340223
     */
    public void submitMarginSettleContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMarginSettleContractOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�����\�񒍕��P��
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        
        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�����\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
        {
            //get������(�m�F�������� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�m�F���������F�@@�����̊����\�񒍕��P��.������
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //get�����\�񌚊��ԍώw����ꗗ( )(�����\�񒍕��P��Impl::get�����\�񌚊��ԍώw����ꗗ)
            RsvEqClosingContractSpecRow[] l_arrClosingContractSpec = l_rsvEqOrderUnit.getContractsToClose();
            if (l_arrClosingContractSpec == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //create���ώw��G���g��(�����\�񌚊��ԍώw����s[])(�A���������������ꌏ�T�[�r�XImpl::create���ώw��G���g��)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �\��ԍώw����ꗗ�F�@@�����\�񒍕��P��.get�����\�񌚊��ԍώw����ꗗ()�̖߂�l
            EqTypeSettleContractOrderEntry[] l_arrSettleContractOrderEntry = 
                this.createSettleContractEntries(l_arrClosingContractSpec);
            
            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(�����ID : long)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����
                //  �����ID�F�@@�\�񒍕��P��.�����
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnit.getTraderId());
            }
            
            //get�\�񒍕����s�P��( )(�����\�񒍕��P��Impl::get�\�񒍕����s�P��)
            double l_dblRsvOrderExecPrice = l_rsvEqOrderUnit.getRsvOrderExecPrice();

            //[create�ԍϒ������e( )�F�����ݒ�d�l]
            //���ҁF�@@�\�񒍕��P��.�����ID==null�̏ꍇ�́Anull
            //          �\�񒍕��P��.�����ID��null�̏ꍇ�́A�����ID�ɊY�����鈵�҃I�u�W�F�N�g
            //���ό����G���g���F�@@create���ώw��G���g��()�̖߂�l
            //�w�l�F�@@�\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            //���s�����F�@@"�����Ȃ�"
            //�����������F�@@�\�񒍕��P��.����������
            //�ŋ敪�F�@@�\�񒍕��P��.�ŋ敪
            //�l�i�����F�@@"�����Ȃ�"
            //���������F�@@"�����Ȃ�"
            //�����������Z�q�F�@@null
            //�t�w�l��l�F�@@0
            //�iW�w�l�j�����w�l�F�@@0
            //���Ϗ����敪�F�@@�\�񒍕��P��.���Ϗ����敪
            //���񒍕��̒����P��ID�F�@@�\�񒍕��P��.���񒍕��̒����P��ID
            Long l_firstOrderUnitId = null;
            if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                l_firstOrderUnitId = new Long(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
            }
            WEB3MarginSettleContractOrderSpec l_closeMarginOrderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_arrSettleContractOrderEntry,
                    l_dblRsvOrderExecPrice,
                    EqTypeExecutionConditionType.NONE,
                    l_rsvEqOrderUnitRow.getExpirationDate(), 
                    l_rsvEqOrderUnit.getTaxType(), 
                    WEB3PriceConditionDef.DEFAULT,
                    WEB3OrderingConditionDef.DEFAULT,
                    null,
                    0,
                    0,
                    l_rsvEqOrderUnitRow.getClosingOrderType(),
                    l_firstOrderUnitId);
                    
            //validate�ԍϒ���(�⏕���� : SubAccount, �M�p�ԍϒ������e : EqTypeSettleContractOrderSpec)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g  
            //  �M�p�ԍϒ������e�F�@@�쐬�����M�p�ԍϒ������e�I�u�W�F�N�g
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();
            //�⏕�����F�@@�\�񒍕��P�ʂ̌���ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_rsvEqOrderUnit.getAccountId(),
                    l_rsvEqOrderUnit.getSubAccountId());

            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_closeMarginOrderSpec);

            //{validate�ԍϒ���()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�ԍϒ���()}�����s�̏ꍇ�B");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get�s��( )(�����\�񒍕��P��Impl::get�s��)
            WEB3GentradeMarket l_market = l_rsvEqOrderUnit.getMarket();

            //create�萔��(�⏕���� : �⏕����, �s��R�[�h : String, ������ : Date, �����`���l�� : String, 
            //  �M�p����敪 : String, �ٍϊ����l : double, �����J�e�S�� : OrderCategEnum)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�\�񒍕��P�ʂ̌���ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
            //  �s��R�[�h�F�@@�\�񒍕��P��.get�s��().�s��R�[�h  
            //  �������F�@@�\�񒍕��P��.������ 
            //  �����`���l���F�@@�\�񒍕��P��.���񒍕��̒����`���l�� 
            //  �M�p����敪�F�@@�\�񒍕��P��.�ٍϋ敪  
            //  �ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l 
            //  �����J�e�S���F�@@OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getBizLogicProvider();
            //�萔�����擾
            WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
                l_subAccount, 
                l_market.getMarketCode(), 
                l_datOrderBizDate, 
                l_rsvEqOrderUnitRow.getOrderChanel(), 
                l_rsvEqOrderUnitRow.getRepaymentType(), 
                l_rsvEqOrderUnitRow.getRepaymentNum(), 
                OrderCategEnum.CLOSE_MARGIN);

            //isLimitOrder()
            boolean l_blnIsLimitOrder = l_closeMarginOrderSpec.isLimitOrder();

            //setIs�w�l(is�w�l : boolean)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  is�w�l�F�@@�M�p�ԍϒ������e.isLimitOrder()�̖߂�l
            l_commission.setIsLimitPrice(l_blnIsLimitOrder);

            //get�������()(�����\�񒍕��P��Impl::get�������)
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_rsvEqOrderUnit.getTradedProduct();

            //[calc�T�Z���ϑ��v���( )�F�����ݒ�d�l]
            //  �萔���F�@@�쐬�����萔���I�u�W�F�N�g
            //  �w�l�F�@@�\�񒍕��P��.get�\�񒍕����s�P��()�̖߂�l
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            //  ��������F�@@�擾������������I�u�W�F�N�g
            //  ���ό����G���g���F�@@create���ώw��G���g��()�̖߂�l
            //  ���ʁF�@@�ԍϒ������e.getTotalQuantity()
            //  �����P�ʁF�@@null�i�Œ�j
            //  �����萔�ʁF�@@0
            //  ������P���F�@@0
            //  isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice = 
                l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_dblRsvOrderExecPrice,
                    l_subAccount,
                    l_tradedProduct,
                    l_arrSettleContractOrderEntry,
                    l_closeMarginOrderSpec.getTotalQuantity(),
                    null,
                    0,
                    0,
                    false);

            //[�M�p�ԍύX�V�C���^�Z�v�^( )�F�����ݒ�d�l]
            //  �M�p�ԍϒ������e�F�@@create�ԍϒ������e()�̖߂�l
            //  �萔���F�@@�쐬�����萔���I�u�W�F�N�g
            //  �T�Z���ϑ��v����v�Z���ʁF�@@calc�T�Z���ϑ��v���()�̖߂�l
            //  �ٍϋ敪�F�@@�\�񒍕��P��.�ٍϋ敪
            //  �ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l
            //  ���񒍕��̒����`���l���F�@@�\�񒍕��P��.���񒍕��̒����`���l��
            //  �����o�H�敪�F�@@�\�񒍕��P��.�����o�H�敪
            WEB3MarginCloseMarginUpdateInterceptor l_closeMarginUpdateInterceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_closeMarginOrderSpec,
                    l_commission,
                    l_profitAndLossPrice,
                    l_rsvEqOrderUnitRow.getRepaymentType(), 
                    l_rsvEqOrderUnitRow.getRepaymentNum(), 
                    l_rsvEqOrderUnitRow.getOrderChanel(), 
                    l_rsvEqOrderUnitRow.getOrderRootDiv());

            //set�󒍓���(�󒍓��� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �󒍓����F�@@�\�񒍕��P��.�󒍓���
            l_closeMarginUpdateInterceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //setThreadLocalPersistenceEventInterceptor(�M�p�ԍύX�V�C���^�Z�v�^ : 
            //  EqTypeOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_closeMarginUpdateInterceptor);

            //[submitSettleContractOrder( )�F�����ݒ�d�l]
            //  arg0�i�⏕�����j�F�@@�擾�����⏕�����I�u�W�F�N�g
            //  arg1�i�ԍϒ������e�j�F�@@create�ԍϒ������e()�̖߂�l
            //  arg2�i�����h�c�j�F�@@�\�񒍕��P��.get����ID()
            //  arg3�i����p�X���[�h�j�F�@@�ڋq.getTradingPassword()�̖߂�l��decrypt�����l
            //  arg4�iisSkip�����R���j�F�@@true
            MainAccount l_mainAccount = l_subAccount.getMainAccount();
            WEB3Crypt l_crypt = new WEB3Crypt();

            EqTypeOrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitSettleContractOrder(
                    l_subAccount,
                    l_closeMarginOrderSpec,
                    l_rsvEqOrderUnit.getOrderId(),
                    l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);

            //�]�͍Čv�Z(�⏕���� : �⏕����)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
                //  �����^�C�v�F�@@�����̗\�񒍕��P��.�����^�C�v 
                //  ����ID�F�@@�����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnit.getProductType(),
                    l_rsvEqOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitSettleContractOrder()}���G���[�ꍇ�B");

                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�����\�񒍕��P�ʍs : �����\�񒍕��P��Row, �����G���[�R�[�h : String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //�����\�񒍕��P�ʍs�F�@@�����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g 
            //�����G���[�R�[�h�F�@@����������O�I�u�W�F�N�g��ErrorInfo.error_code  
            //�@@�@@�@@�@@�@@�@@            �i�G���[�̌����̓��肪�\��BusinessError�^SystemError�̃G���[�R�[�h�j
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�������n����)<BR>
     * �M�p�������n�����𔭒�����B<BR>
     * �V�[�P���X�}�u�i�A���������������ꌏ�T�[�r�X�j<BR>
     * submit�M�p�������n�����v���Q�ƁB<BR>
     * <BR>
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g�B<BR>
     * @@exception WEB3BaseException<BR>
     * @@roseuid 4346224A02FD
     */
    public void submitMarginSwapContractOrder(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMarginSwapContractOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);
        
        //�����\�񒍕��P��
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = null;
        
        //�G���[�R�[�h
        String l_strErrorCode = null;

        //�����\�񒍕��X�V�T�[�r�X
        WEB3ToSuccReservationEqTypeOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);

        try
        {
            //get������(�m�F�������� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�m�F���������F�@@�����̊����\�񒍕��P��.������
            l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd"));

            //get�����\�񌚊��ԍώw����ꗗ( )(�����\�񒍕��P��Impl::get�����\�񌚊��ԍώw����ꗗ)
            RsvEqClosingContractSpecRow[] l_arrClosingContractSpec = l_rsvEqOrderUnit.getContractsToClose();
            if (l_arrClosingContractSpec == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02339,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //create���ώw��G���g��(�����\�񌚊��ԍώw����s[])(�A���������������ꌏ�T�[�r�XImpl::create���ώw��G���g��)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �\��ԍώw����ꗗ�F�@@�����\�񒍕��P��.get�����\�񌚊��ԍώw����ꗗ()�̖߂�l
            EqTypeSettleContractOrderEntry[] l_arrSettleContractOrderEntry = 
                this.createSettleContractEntries(l_arrClosingContractSpec);
            
            //����t���[�F�@@�\�񒍕��P��.�����ID��null�̏ꍇ�̂�
            WEB3GentradeTrader l_trader = null;
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                //getTrader(�����ID : long)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����
                //  �����ID�F�@@�\�񒍕��P��.�����
                FinObjectManager l_finMng = GtlUtils.getFinObjectManager();
                l_trader = (WEB3GentradeTrader)l_finMng.getTrader(l_rsvEqOrderUnit.getTraderId());
            }
            
            //[create�������n�������e( )�F�����ݒ�d�l]
            //  ���ҁF�@@�\�񒍕��P��.�����ID==null�̏ꍇ�́Anull
            //          �\�񒍕��P��.�����ID��null�̏ꍇ�́A�����ID�ɊY�����鈵�҃I�u�W�F�N�g
            //  ���ό����G���g���F�@@create���ώw��G���g��()�̖߂�l
            //  ���Ϗ����敪�F�@@�\�񒍕��P��.���Ϗ����敪
            //  �ŋ敪�F�@@�\�񒍕��P��.�ŋ敪
            //  �ŋ敪�i�������n�j�F�@@�\�񒍕��P��.�ŋ敪�i�������n�j
            WEB3MarginSwapContractOrderSpec l_swapMarginOrderSpec =
                WEB3MarginSwapContractOrderSpec.createSwapMarginOrderSpec(
                    l_trader,
                    l_arrSettleContractOrderEntry,
                    l_rsvEqOrderUnitRow.getClosingOrderType(),
                    l_rsvEqOrderUnitRow.getTaxType(),
                    l_rsvEqOrderUnitRow.getSwapTaxType());
            
            //validate�������n����(�⏕���� : SubAccount, �M�p�������n�������e : EqTypeSwapContractOrderSpec)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
            //  �M�p�������n�������e�F�@@�쐬�����������n�������e�I�u�W�F�N�g
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();
            //�⏕�����F�@@�\�񒍕��P�ʂ̌���ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_rsvEqOrderUnit.getAccountId(),
                    l_rsvEqOrderUnit.getSubAccountId());

            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSwapContractOrder(
                    l_subAccount,
                    l_swapMarginOrderSpec);

            //{validate�������n����()}�����s�̏ꍇ�B
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("{validate�������n����()}�����s�̏ꍇ�B");
                
                throw new WEB3BaseException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //calc�T�Z��n����i�������n�j(���ό����G���g�� : EqtypeSettleContractOrderEntry[], 
            //  ���� : double, �����P�� : �����P��)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  ���ό����G���g���F�@@create���ώw��G���g��()�̖߂�l 
            //  ���ʁF�@@�쐬�����M�p�������n�������e.getTotalQuantity( )  
            //  �����P�ʁF�@@null�i�Œ�j�F�V�K�����̓o�^�Ȃ̂�null 
            double l_dblTotalQuantity = l_swapMarginOrderSpec.getTotalQuantity();
            double l_dblEstimatedSwapPrice = l_orderManager.calcEstimatedSwapPrice(
                l_arrSettleContractOrderEntry,
                l_dblTotalQuantity,
                null);

            //get����(����ID : long)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  ����ID�F�@@create���ώw��G���g��()�̖߂�l[0].����ID
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getPositionManager();
            if (l_arrSettleContractOrderEntry == null || l_arrSettleContractOrderEntry.length == 0)
            {
                log.debug("�f�[�^�s�����G���[�B");            
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
            WEB3EquityContract l_equityContract = (WEB3EquityContract)l_positionManager.getContract(
                l_arrSettleContractOrderEntry[0].getContractId());

            //isShort()
            boolean l_blnIsShort = l_equityContract.isShort();

            //����t���[�F����.isShort()==true�i�����n�����j�̏ꍇ�̂�
            //���n���v
            double l_dblCapitaGain = 0;
            
            //���n�v��
            double l_dblCapitalGainTax = 0;

            if (l_blnIsShort)
            {
                //calc���n���v(���z : double, ������ : double, ����ID : long, �⏕���� : SubAccount, �ŋ敪 : TaxTypeEnum)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B 
                //  ���z�F�@@calc�T�Z��n����i�������n�j() 
                //  �����ʁF�@@�M�p�������n�������e.getTotalQuantity() 
                //  ����ID�F�@@�\�񒍕��P��.����ID 
                //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
                //  �ŋ敪�F�@@�M�p�������n�������e.get�ŋ敪�i�������n�j()
                WEB3EquityBizLogicProvider l_logicProvider = 
                    (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(
                        ProductTypeEnum.EQUITY).getBizLogicProvider();
                TaxTypeEnum l_swapTaxType = l_swapMarginOrderSpec.getSwapTaxType();
                l_dblCapitaGain = l_logicProvider.calcCapitaGain(
                    l_dblEstimatedSwapPrice,
                    l_dblTotalQuantity,
                    l_rsvEqOrderUnitRow.getProductId(),
                    l_subAccount,
                    l_swapTaxType);
                    
                //get�������()(�����\�񒍕��P��Impl::get�������)
                WEB3EquityTradedProduct l_tradedProduct =
                    (WEB3EquityTradedProduct)l_rsvEqOrderUnit.getTradedProduct();

                //get��n���ŋ敪(��n�� : Date)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B  
                //           �������.getDailyDeliveryDate( )�i����n���j
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                TaxTypeEnum l_deliveryDateTaxType = l_mainAccount.getDeliveryDateTaxType(
                    l_tradedProduct.getDailyDeliveryDate());

                //calc���n�v��(�⏕���� : �⏕����, �ŋ敪 : TaxTypeEnum, ���z : double, 
                //  ��� : Timestamp, �ڋq�ŋ敪 : TaxTypeEnum)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B  
                //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
                //  �ŋ敪�F�@@�M�p�������n�������e.get�ŋ敪�i�������n�j()  
                //  ���z�F�@@�����v�Z�T�[�r�X.calc���n���v()  
                //  ����F�@@�\�񒍕��P��.get�������()�Ŏ擾����  
                //            �������.getDailyDeliveryDate()�i����n���j  
                //  �ڋq�ŋ敪�F�@@�ڋq.get��n���ŋ敪()
                l_dblCapitalGainTax = l_logicProvider.calcCapitalGainTax(
                    (SubAccount)l_subAccount,
                    l_swapTaxType,
                    l_dblCapitaGain,
                    new Timestamp(l_tradedProduct.getDailyDeliveryDate().getTime()),
                    l_deliveryDateTaxType);
            }

            //get�i���X�s��ٍϕʁj�戵����()
            WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
                l_equityContract.getBranchMarketRepayDealtCond();

            //�M�p�������n�X�V�C���^�Z�v�^(�M�p�������n�������e : �M�p�������n�������e, 
            //  �ٍϋ敪�iSONAR�j : String, �T�Z��n��� : double, �ٍϋ敪 : String, �ٍϊ����l : double, 
            //  ���n�v���z : double, ���n�v�Ŋz : double, ���񒍕��̒����`���l�� : String, �����o�H�敪 : String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //�������I�u�W�F�N�g�́A�����|�W�V�����}�l�[�W��.get����()�̖߂�l���g�p�B 
            //  �M�p�������n�������e�F�@@�쐬�����M�p�������n�������e�I�u�W�F�N�g  
            //  �ٍϋ敪�iSONAR�j�F�@@����.get�i���X�s��ٍϕʁj�戵����( )�Ŏ擾����  
            //    �i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.�ٍϋ敪�iSONAR�j  
            //  �T�Z��n����F�@@calc�T�Z��n����i�������n�j()�̖߂�l 
            //  �ٍϋ敪�F�@@�\�񒍕��P��.�ٍϋ敪  
            //  �ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l  
            //  ���n�v���z�F�@@����.isShort()==true�i���n�j�̏ꍇ�́A�����v�Z�T�[�r�X.calc���n���v()�̖߂�l�B 
            //�@@�@@�@@            �ȊO�A0�B 
            //  ���n�v�Ŋz�F�@@����.isShort()==true�i���n�j�̏ꍇ�́A�����v�Z�T�[�r�X.calc���n�v��()�̖߂�l�B 
            //�@@�@@�@@            �ȊO�A0�B 
            //  ���񒍕��̒����`���l���F�@@�\�񒍕��P��.���񒍕��̒����`���l�� 
            //  �����o�H�敪�F�@@�\�񒍕��P��.�����o�H�敪
            BranchMarketRepayDealtCondRow l_BranchMarketRepayDealtCondRow =
                (BranchMarketRepayDealtCondRow)l_branchMarketRepayDealtCond.getDataSourceObject();
            WEB3MarginSwapMarginUpdateInterceptor l_swapMarginUpdateInterceptor =
                new WEB3MarginSwapMarginUpdateInterceptor(
                    l_swapMarginOrderSpec,
                    l_BranchMarketRepayDealtCondRow.getSonarRepaymentType(),
                    l_dblEstimatedSwapPrice,
                    l_rsvEqOrderUnitRow.getRepaymentType(),
                    l_rsvEqOrderUnitRow.getRepaymentNum(),
                    l_dblCapitaGain,
                    l_dblCapitalGainTax,
                    l_rsvEqOrderUnitRow.getOrderChanel(),
                    l_rsvEqOrderUnitRow.getOrderRootDiv());

            //set�󒍓���(�󒍓��� : Date)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �󒍓����F�@@�\�񒍕��P��.�󒍓���
            l_swapMarginUpdateInterceptor.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());

            //validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], 
            //  ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //  �⏕�����F�@@�\�񒍕��P��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g 
            //  �������e�C���^�Z�v�^�F�@@���������M�p�������n�X�V�C���^�Z�v�^  
            //  �������e�F�@@�쐬�����M�p�������n�������e�I�u�W�F�N�g  
            //  ������ʁF�@@�\�񒍕��P��.get�������() 
            //  �]�͍X�V�t���O�F�@@true�i�������j
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objSwapMarginUpdateInterceptor = new Object[]{l_swapMarginUpdateInterceptor};
            Object[] l_objSwapMarginOrderSpec = new Object[]{l_swapMarginOrderSpec};
            WEB3TPTradingPowerResult l_tpResult =
                l_tradingPowerService.validateTradingPower(
                    l_subAccount,
                    l_objSwapMarginUpdateInterceptor,
                    l_objSwapMarginOrderSpec, 
                    l_rsvEqOrderUnitRow.getOrderType(),
                    true);

            //is����t���O( )
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //����]�͌���.is����t���O( )==true�̏ꍇ�̂�
            EqTypeOrderSubmissionResult l_orderSubmissionResult = null;
            if (l_blnIsResultFlg)
            {
                //setThreadLocalPersistenceEventInterceptor(�M�p�������n�X�V�C���^�Z�v�^ : EqTypeOrderManagerPersistenceEventInterceptor)
                l_orderManager.setThreadLocalPersistenceEventInterceptor(l_swapMarginUpdateInterceptor);

                //[submitSwapContractOrder( )�F�����ݒ�d�l]
                //  arg0�i�⏕�����j�F�@@�擾�����⏕�����I�u�W�F�N�g
                //  arg1�i�������n�������e�j�F�@@create�������n�������e()�̖߂�l
                //  arg2�i�����h�c�j�F�@@�\�񒍕��P��.get����ID()
                //  arg3�i����p�X���[�h�j�F�@@�ڋq.getTradingPassword()�̖߂�l��decrypt�����l
                //  arg4�iisSkip�����R���j�F�@@true
                MainAccount l_mainAccount = l_subAccount.getMainAccount();
                WEB3Crypt l_crypt = new WEB3Crypt();
                l_orderSubmissionResult =
                    l_orderManager.submitSwapContractOrder(
                        l_subAccount,
                        l_swapMarginOrderSpec,
                        l_rsvEqOrderUnit.getOrderId(),
                        l_crypt.decrypt(l_mainAccount.getTradingPassword()),
                        true);
            }
            else
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                l_orderManager.throwTpErrorInfo(l_tpResult, l_rsvEqOrderUnitRow.getOrderType());
            }

            //���s���ʂɉ����A�\�񒍕��P�ʂ�update����
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                //set������To�\�񒍕��P��(ProductTypeEnum, long)(�A�������}�l�[�W��Impl::set������To�\�񒍕��P��)
                //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
                //  �����^�C�v�F�@@�����̗\�񒍕��P��.�����^�C�v 
                //  ����ID�F�@@�����̗\�񒍕��P��.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                l_orderManagerImpl.setOrderedToOrderUnit(
                    l_rsvEqOrderUnit.getProductType(),
                    l_rsvEqOrderUnit.getOrderId());
            }
            else
            {
                log.debug("{submitSwapContractOrder()}���G���[�̏ꍇ�B");

                throw new WEB3BaseException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            if (l_ex instanceof WEB3BaseException)
            {
                l_strErrorCode = ((WEB3BaseException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_strErrorCode = ((WEB3BaseRuntimeException)l_ex).getErrorInfo().getErrorCode();
            }
            else if (l_ex instanceof NotFoundException)
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorCode();
            }
            else
            {
                l_strErrorCode = WEB3ErrorCatalog.SYSTEM_ERROR_80002.getErrorCode();
            }

            //invalidate�\�񒍕��P��(�����\�񒍕��P�ʍs : �����\�񒍕��P��Row, �����G���[�R�[�h : String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //�����\�񒍕��P�ʍs�F�@@�����̗\�񒍕��P�ʂ̍s�I�u�W�F�N�g 
            //�����G���[�R�[�h�F�@@����������O�I�u�W�F�N�g��ErrorInfo.error_code  
            //�@@�@@�@@�@@�@@�@@            �i�G���[�̌����̓��肪�\��BusinessError�^SystemError�̃G���[�R�[�h�j
            l_orderUpdateService.invalidateOrderUnit(
                l_rsvEqOrderUnitRow,
                l_strErrorCode);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create���ώw��G���g��)<BR>
     * �����̊����\�񌚊��ԍώw����ꗗ���A<BR>
     * ���ϒ����i�ԍρ^�������n�j�̊e���ւ�<BR>
     * ���ώw��G���g���̔z����쐬���ԋp����B<BR>
     * <BR>
     * �����̗\��ԍώw����ꗗ�̗v�f�iindex�j�����A<BR>
     * �ȉ��̏������J��Ԃ��B<BR>
     * LOOP�I����A�쐬�����C���X�^���X�̔z���ԋp����B<BR>
     * <BR>
     * ���������@@START LOOP�@@��������<BR>
     * <BR>
     * �P�j�@@�ԍώw��Ώۂ̌����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@EQTYPE�̊����|�W�V�����}�l�[�W��.get����<BR>
     *       (�\��ԍώw����ꗗ[index].����ID)��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�ԍϐ��ʂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�ԍϐ���(*1) = �\��ԍώw����ꗗ[index].�ԍϒ�������<BR>
     * <BR>
     * �@@�@@�@@�ԍϐ���(*1) == 0�̏ꍇ�́A���̗v�f�̏������s���icontinue�j�B<BR>
     * <BR>
     * �R�j�@@�ԍω\�����c�����v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�ԍω\�����c��(*2) = �P�j�Ŏ擾����<BR>
     *       ����.������ �| ����.getLockedQuantity()<BR>
     * <BR>
     * �S�j�@@�ԍϐ��ʂ��`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�i�ԍϐ���(*1) �� �ԍω\�����c��(*2)�j �̏ꍇ�A<BR>
     * �@@�@@�@@�u�����c���s���G���[�v�̗�O��throw����B<BR>
     * <BR>
     * �T�j�@@EqTypeSettleContractOrderEntry�̃C���X�^���X�𐶐����A<BR>
     * �@@�@@�@@�߂�l��append����B<BR>
     * <BR>
     * �@@�@@�@@[�R���X�g���N�^�����ݒ�d�l]<BR>
     * �@@�@@�@@����ID�F�@@�\��ԍώw����ꗗ[index].����ID<BR>
     * �@@�@@�@@�ԍϐ��ʁF�@@�ԍϐ���(*1)<BR>
     * <BR>
     * ���������@@ENDLOOP�@@��������<BR>
     * @@param l_rsvEqClosingContractSpec - (�\��ԍώw����ꗗ)<BR>
     * �����\�񌚊��ԍώw����s�I�u�W�F�N�g�̔z��B<BR>
     * @@return EqTypeSettleContractOrderEntry[]<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 434C7376006C
     */
    protected EqTypeSettleContractOrderEntry[] createSettleContractEntries(RsvEqClosingContractSpecRow[] l_rsvEqClosingContractSpec) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntries(RsvEqClosingContractSpecRow[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_rsvEqClosingContractSpec == null)
        {
            log.debug("�p�����[�^�l���s���ł��B");            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }
        
        //���ώw��G���g�����X�g����`
        List l_lisContractOrderEntry = new ArrayList();

        try
        {
            //���ώw��G���g���z��̒��x
            int l_intContractSpecLth = l_rsvEqClosingContractSpec.length;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getPositionManager();

            //���������@@START LOOP�@@��������
            for (int i = 0; i < l_intContractSpecLth; i++)
            {
                //�P�j�@@�ԍώw��Ώۂ̌����I�u�W�F�N�g���擾����B
                //�@@�@@�@@EQTYPE�̊����|�W�V�����}�l�[�W��.get����
                //      (�\��ԍώw����ꗗ[index].����ID)���R�[������B
                long l_lngContractId = l_rsvEqClosingContractSpec[i].getContractId();
                Contract l_contract = l_positionManager.getContract(l_lngContractId);

                //�Q�j�@@�ԍϐ��ʂ��擾����B<BR>
                //�@@�@@�@@�ԍϐ���(*1) = �\��ԍώw����ꗗ[index].�ԍϒ�������<BR>
                //�@@�@@�@@�ԍϐ���(*1) == 0�̏ꍇ�́A���̗v�f�̏������s���icontinue�j�B<BR>
                double l_dblQuantity = l_rsvEqClosingContractSpec[i].getQuantity();
                if (l_dblQuantity == 0)
                {
                    continue;
                }

                //�R�j�@@�ԍω\�����c�����v�Z����B<BR>
                //�@@�@@�@@�ԍω\�����c��(*2) = �P�j�Ŏ擾����<BR>
                //      ����.������ �| ����.getLockedQuantity()<BR>
                double l_dblCloseContractQuantity = l_contract.getQuantity() - l_contract.getLockedQuantity();

                //�S�j�@@�ԍϐ��ʂ��`�F�b�N����B<BR>
                // �@@�@@�i�ԍϐ���(*1) �� �ԍω\�����c��(*2)�j �̏ꍇ�A<BR>
                // �@@�@@�u�����c���s���G���[�v�̗�O��throw����B<BR>
                if (l_dblQuantity > l_dblCloseContractQuantity)
                {
                    log.debug("�u�����c���s���G���[�v");

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00808,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //�T�j�@@EqTypeSettleContractOrderEntry�̃C���X�^���X�𐶐����A<BR>
                //�@@�@@�@@�߂�l��append����B<BR>
                //�@@�@@�@@[�R���X�g���N�^�����ݒ�d�l]<BR>
                //�@@�@@�@@����ID�F�@@�\��ԍώw����ꗗ[index].����ID<BR>
                //�@@�@@�@@�ԍϐ��ʁF�@@�ԍϐ���(*1)<BR>
                EqTypeSettleContractOrderEntry l_contractOrderEntry = 
                    new EqTypeSettleContractOrderEntry(l_lngContractId, l_dblQuantity);
                    
                l_lisContractOrderEntry.add(l_contractOrderEntry);
            }
            //���������@@ENDLOOP�@@��������
        }
        catch (NotFoundException l_nfe)
        {
            log.debug(l_nfe.getMessage(), l_nfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //���ώw��G���g�����X�g��null�ꍇ
        if (l_lisContractOrderEntry == null || l_lisContractOrderEntry.size() == 0)
        {
            return null;
        }

        EqTypeSettleContractOrderEntry[] l_arrContractOrderEntry =
            new EqTypeSettleContractOrderEntry[l_lisContractOrderEntry.size()];
        l_lisContractOrderEntry.toArray(l_arrContractOrderEntry);

        log.exiting(STR_METHOD_NAME);
        return l_arrContractOrderEntry;
    }
}
@
