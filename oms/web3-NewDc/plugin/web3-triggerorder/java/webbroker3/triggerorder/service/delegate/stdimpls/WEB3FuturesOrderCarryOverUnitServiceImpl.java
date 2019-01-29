head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����J�z�P���T�[�r�X�����N���X(WEB3FuturesOrderCarryOverUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 䈋� (���u) �V�K�쐬
                 : 2006/8/15 �s�p (���u) �d�l�ύX ���f��507
Revesion History : 2007/6/30 �Ј��� (���u) �d�l�ύX ���f��756 762
Revesion History : 2007/7/17 ��іQ (���u) ���f��No.776
Revesion History : 2008/04/11 ��іQ (���u) ���f��No.277,278
Revesion History : 2008/04/24 ��іQ (���u) ���f��No.340
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3IfoOrderCarryOverUpdateInterceptor;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨�����J�zUnitServiceImpl)<BR>
 * �敨�����J�z�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �P�����Ƃ̒����J�z���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)���w�肷��B<BR>
 * @@author  ���Ō�
 * @@version 1.0
 */
public class WEB3FuturesOrderCarryOverUnitServiceImpl
    implements WEB3FuturesOrderCarryOverUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderCarryOverUnitServiceImpl.class);

    /**
     * @@roseuid 40F7A2C60157
     */
    public WEB3FuturesOrderCarryOverUnitServiceImpl()
    {

    }
    /**
     * (create�V�K����������)<BR>
     * ���������i�V�K���j���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����J�z�jcreate�V�K�����������v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ
     * @@roseuid 40A88B46039C
     */
    public void createOpenContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOpenContractNextOrder(OrderUnit, List)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            //�敨�����}�l�[�W��
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();
            //is�J�z�Ώے���(IfoOrderUnit)
            boolean l_blnIsCarryoverOrder = l_orderManager.isCarryoverOrder((IfoOrderUnit)l_orderUnit);

            // (*)�J�z�ΏۊO�����iis�J�z�Ώے��� == false�j�̏ꍇ
            if (!l_blnIsCarryoverOrder)
            {
                // update�J�z������()
                //  [update�J�z������()�Ɏw�肷�����]
                //  �����P�ʁF�@@�����P��
                //  �����G���[���R�R�[�h�F�@@�i���̑��G���[�j
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.OTHRE_ERROR);

                // return
                return;
            }

            // �敨OP�������擾����B
            Product l_product = l_orderUnit.getProduct();

            // is�J�z�X�L�b�v����
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            long l_lngTradedProductId = l_product.getProductId();
            long l_lngMarketId = ((IfoOrderUnitRow)l_orderUnit.getDataSourceObject()).getMarketId();

            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    l_lngTradedProductId,
                    l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
                return;
            }
            boolean l_blnIsCarryOverSkipProduct = l_tradedProduct.isCarryOverSkipProduct();

            // (*1) �����J�z�X�L�b�v�����iis�J�z�X�L�b�v����() == true�j�̏ꍇ
            if (l_blnIsCarryOverSkipProduct == true)
            {
                // update�J�z������()
                //  [update�J�z������()�Ɏw�肷�����]
                //  �����P�ʁF�@@�����P��
                //  �����G���[���R�R�[�h�F�@@�i�����J�z�X�L�b�v�����G���[�j
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);

                // return
                return;
            }

            // getAccountId()
            long l_lngAccountId = l_orderUnit.getAccountId();

            // getSubAccountId()
            long l_lngSubAccountId = l_orderUnit.getSubAccountId();

            // get�⏕����()(�g���A�J�E���g�}�l�[�W��::get�⏕����)
            SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);

            // getTraderId()
            long l_lngTraderId = l_orderUnit.getTraderId();

            // getSide()
            SideEnum l_side = l_orderUnit.getSide();

            // getQuantity( )(OrderUnit::getQuantity)
            double l_dblQuantity = l_orderUnit.getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }

            // getExecutedQuantity( )(OrderUnit::getExecutedQuantity)
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            // getLimitPrice( )(OrderUnit::getLimitPrice)
            double l_dblLimitPrice = l_orderUnit.getLimitPrice();
            if (Double.isNaN(l_dblLimitPrice))
            {
                l_dblLimitPrice = 0D;
            }

            // �V�K���������e�𐶐�����B
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // ���ҁF�@@�擾�������҂h�c�ɊY�����鈵��
            Trader l_trader = null;
            if (l_lngTraderId != 0)
            {
                l_trader = l_objectManager.getTrader(l_lngTraderId);
            }

            // �s��R�[�h�F�@@�����P��.�s��h�c�ɊY������s��̎s��R�[�h
            Market l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            // ���s�����F�@@�����P��.���s����
            IfoOrderExecutionConditionType l_orderExecutionConditionType =
                l_orderUnitRow.getExecutionConditionType();

            // �����������F�@@�����P��.����������
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();

            // ���������F�@@�����P��.��������
            String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();

            //�t�w�l��l�F�@@�����P��.�t�w�l��l 
            double l_blnStopOrderPrice = l_orderUnitRow.getStopOrderPrice();
            
            //�iW�w�l�j�����w�l�F�@@�����P��.�iW�w�l�j�����w�l
            double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
            if (Double.isNaN(l_dblWLimitPrice))
            {
                l_dblWLimitPrice = 0D;
            }
                                    
            //�iW�w�l�j���s�����F�@@�����P��.�iW�w�l�j���s���� 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                l_orderUnitRow.getWLimitExecCondType();
            
			// ���񒍕��̒����P��ID
			Long l_longFirstOrderUnitId = null;
			// ����J�z�̏ꍇ
			if (l_orderUnitRow.getFirstOrderUnitId() == 0 || 
				l_orderUnitRow.getFirstOrderUnitIdIsNull())
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getOrderUnitId());
			}
			// �ȊO
			else
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getFirstOrderUnitId());
			}

            //�[��O�J�z�Ώۃt���O
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr((IfoOrderUnit)l_orderUnit);

            //���������敪�F�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

            // create�V�K���������e
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_trader,
                    (SideEnum.BUY.equals(l_side)),
                    l_strMarketCode,
                    (WEB3IfoProductImpl)l_product,
                    (l_dblQuantity - l_dblExecutedQuantity),
                    l_dblLimitPrice,
                    l_orderExecutionConditionType,
                    l_datExpirationDate,
                    l_strOrderConditionType,
                    l_blnStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_longFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            // validate�V�K������(�⏕���� : SubAccount, �敨OP�V�K���������e : IfoOpenContractOrderSpec
            NewOrderValidationResult l_validationResult =
                l_orderManager.validateFuturesOpenContractOrder(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_openContractOrderSpec);

            //  getProcessingResult( )
            ProcessingResult l_processingResult = l_validationResult.getProcessingResult();

            //  isFailedResult( )(ProcessingResult::isFailedResult)
            boolean l_blnIsFailedResult = l_processingResult.isFailedResult();

            //  (*2) �����R���Ŏ��s�����ꍇ�iisFailedResult() == true�j
            if (l_blnIsFailedResult)
            {
                // getErrorInfo( )
                ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
                String l_strErrorReasonCode = null;

                //�G���[�R�[�h����G���[���R�R�[�h�ɕϊ�����B
                if (WEB3ErrorCatalog.BUSINESS_ERROR_00148.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00031.equals(l_errorInfo))
                {
                    //�l���A�Ēl�`�F�b�N�ŃG���[�ɂȂ����ꍇ
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00004.equals(l_errorInfo))
                {
                    //������������~�̏ꍇ
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00735.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00003.equals(l_errorInfo))
                {
                    //�Y���������w��s��Ŏ���ł��Ȃ��ꍇ
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
                }
                else
                {
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
                }

                // update�J�z������()
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    l_strErrorReasonCode);

                // return
                return;
            }

            //  �萔��( )(�萔��::�萔��)
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            // �v���p�e�B�Z�b�g
            //  �萔��.�����`���l�� = �����P��.���񒍕��̒����`���l��
            //  �萔��.�،����ID = �����P��.�،���Ђh�c
            //  �萔��.���XID = �����P��.���X�h�c
            //  �萔��.������ = ������ԊǗ�.get������()
            //  �萔��.����R�[�h(SONAR) = �h51�F���h
            //  �萔��.�萔�����i�R�[�h = �h50�F�敨�h
            //  �萔��.�ٍϋ敪 = �h00�F���̑��h
            //  �萔��.is�w�l = �V�K���������e.isLimitOrder()
            //  �萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            //  �萔��.���� = �V�K���������e.getQuantity()
            String l_strOrderChannel = l_orderUnitRow.getOrderChanel();
            String l_lngInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            long l_lngBranchId = l_orderUnit.getBranchId();
            Timestamp l_orderBizDate = new Timestamp(
                WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            String l_strBookValueIndexOp = WEB3CommisionProductCodeDef.INDEX_FUTURES;

            l_commission.setOrderChannel(l_strOrderChannel);
            l_commission.setInstitutionCode(l_lngInstitutionCode);
            l_commission.setBranchId(l_lngBranchId);
            l_commission.setOrderBizDate(l_orderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strBookValueIndexOp);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_openContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_product).getUnderlyingProductCode());
            
            l_commission.setQuantity(l_openContractOrderSpec.getQuantity());

            // calc�T�Z�����(�萔��, double, SubAccount, �敨OP�������, double, boolean)
            //  (�敨�����}�l�[�W��::calc�T�Z�����)
            //  calc�T�Z�����()�Ɏw�肷�����]
            //  �萔���F�@@�萔���I�u�W�F�N�g
            //  �v�Z�P���F�@@�����P��.getLimitPrice()
            //  �⏕�����F�@@�⏕�����I�u�W�F�N�g
            //  �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //  ���ʁF �V�K���������e.getQuantity()
            //  isSkip���z�`�F�b�N�F�@@true
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult =
                l_orderManager.calcEstimatePrice(
                    l_commission,
                    l_dblLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_tradedProduct,
                    l_openContractOrderSpec.getQuantity(),
                    true);

            // �敨OP�����J�z�X�V�C���^�Z�v�^(IfoOrderUnitParams, String)
            //  (�敨OP�����J�z�X�V�C���^�Z�v�^::�敨OP�����J�z�X�V�C���^�Z�v�^)
            //  [�R���X�g���N�^�̈���]
            //  (�J�z��)�����P��Params�F�@@�����P��.getDataSourceObject()�̖߂�l
            //  �����G���[���R�R�[�h�F�@@�h����h
            //36.�C���^�Z�v�^���Z�b�g����B
            WEB3IfoOrderCarryOverUpdateInterceptor l_updateInterceptor =
                new WEB3IfoOrderCarryOverUpdateInterceptor(
                    (IfoOrderUnitParams)l_orderUnit.getDataSourceObject(),
                    WEB3ErrorReasonCodeDef.NORMAL);

            // set�萔��(�萔��)(�敨OP�����J�z�X�V�C���^�Z�v�^::set�萔��)
            //  [set�萔��()�Ɏw�肷�����]
            //  �萔���F�@@�ʏ�萔���I�u�W�F�N�g
            l_updateInterceptor.setCommissionFee(l_commission);

            // set�T�Z��n����v�Z����(�敨OP�T�Z��n����v�Z����)(�敨OP�����J�z�X�V�C���^�Z�v�^::set�T�Z��n����v�Z����)
            //  [set�T�Z��n����v�Z����()�Ɏw�肷�����]
            //  �T�Z��n����v�Z���ʁF�@@calc�T�Z�����()�̖߂�l
            // �i�� �����P��.getLimitPrice()�ɂČv�Z�������ʂ��g�p�j
            l_updateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

            //set����敪
            l_updateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

            // validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //  [����]
            //  �⏕�����F �⏕����
            //  �������e�C���^�Z�v�^�F �敨OP�����J�z�X�V�C���^�Z�v�^��v�f�Ƃ����z��
            //  �������e�F �V�K���������e��v�f�Ƃ����z��
            //  ������ʁF �����P��.�������
            //  �]�͍X�V�t���O�F true
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            WEB3IfoOrderCarryOverUpdateInterceptor[] l_interceptor = {l_updateInterceptor};
            WEB3IfoOpenContractOrderSpec[] l_orderSpec = {l_openContractOrderSpec};
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_interceptor,
                l_orderSpec,
                l_orderUnit.getOrderType(),
                true);

            //(*5) �؋����`�F�b�N�ŃG���[�ɂȂ����ꍇ
            // ����]�͌���.����t���O==false �̏ꍇ�Aupdate�J�z������()�����s��A�������I������B
            if(!l_tradingPowerResult.isResultFlg())
            {
                // update�J�z������(OrderUnit, String)(�敨�����J�zUnitServiceImpl::update�J�z������)
                //  [update�J�z������()�Ɏw�肷�����]
                //  �����P�ʁF�@@�����P��
                //  �����G���[���R�R�[�h�F
                // �i�����w���I�v�V�����c���s���G���[�j
                //  �� �����G���[���R�R�[�h�ɂ��Ă�
                //  DB�X�V�d�l
                //  �敨�����J�z_�����P�ʃe�[�u���d�l.xls
                // �u�i�����J�z�⑫�j�����G���[���R�R�[�h�v�V�[�g�Q�ƁB
                //  ����]�̓T�[�r�X.validate����]��()==false�̏ꍇ�B
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR);

                // return
                return;
            }

            // setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor)(�敨�����}�l�[�W��::setThreadLocalPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);

            // createNewOrderId( )(�敨�����}�l�[�W��::createNewOrderId)
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            // submitOpenContractOrder(SubAccount, IfoOpenContractOrderSpec, long, String, boolean)(�敨�����}�l�[�W��::submitOpenContractOrder)
            //  [submitOpenContractOrder()�Ɏw�肷�����]
            //  �⏕�����F�@@�⏕����
            //  �V�K���������e�F�@@�V�K���������e
            //  �����h�c�F�@@�敨�����}�l�[�W��.createNewOrderId()
            //  ����p�X���[�h�F ����.����p�X���[�h
            //  isSkip�����R���F�@@true
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
            OrderSubmissionResult l_result = l_orderManager.submitOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_lngNewOrderId,
                l_mainAccount.getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //�J�z�Ώۂ̗\�񒍕������݂���ꍇ�i�p�����[�^.�\�񒍕��P�ʈꗗ��null�j
            if (l_lisRsvIfoOrderUnits != null)
            {
                //getOrderUnits(arg0 : long)
                //����ID�F�@@createNewOrderId()�̖߂�l
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngNewOrderId);

                //exec�A�������J�z(IfoOrderUnit, IfoOrderUnit, List)
                WEB3ToSuccIfoOrderCarryOverService l_service =
                    (WEB3ToSuccIfoOrderCarryOverService)Services.getService(
                        WEB3ToSuccIfoOrderCarryOverService.class);

                l_service.execToSuccOrderCarryOver(
                    (IfoOrderUnit)l_orderUnit,
                    (IfoOrderUnit)l_orderUnits[0],
                    l_lisRsvIfoOrderUnits);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�ԍϗ�������)<BR>
     * ���������i�ԍρj���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����J�z�jcreate�ԍϗ��������v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ 
     * @@roseuid 40A88B4603AC
     */
    public void createSettleContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractNextOrder(OrderUnit, List)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            //�敨�����}�l�[�W��
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            //is�J�z�Ώے���(IfoOrderUnit)
            boolean l_blnIsCarryoverOrder = l_orderManager.isCarryoverOrder((IfoOrderUnit)l_orderUnit);
            
            if (!l_blnIsCarryoverOrder)
            {
                // update�J�z������()
                //  [update�J�z������()�Ɏw�肷�����]
                //  �����P�ʁF�@@�����P��
                //  �����G���[���R�R�[�h�F�@@�i���̑��G���[�j
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.OTHRE_ERROR);

                // return
                return;
            }

            // �敨OP�������擾����B
            Product l_product = l_orderUnit.getProduct();

            // is�J�z�X�L�b�v����
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            long l_lngTradedProductId = l_product.getProductId();
            long l_lngMarketId = ((IfoOrderUnitRow)l_orderUnit.getDataSourceObject()).getMarketId();

            WEB3IfoTradedProductImpl l_isTradedProductImpl = null;
            try
            {
                l_isTradedProductImpl = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    l_lngTradedProductId,
                    l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
                return;
            }
            boolean l_blnIsCarryOverSkipProduct = l_isTradedProductImpl.isCarryOverSkipProduct();

            // (*1) �����J�z�X�L�b�v�����iis�J�z�X�L�b�v����() == true�j�̏ꍇ
            if (l_blnIsCarryOverSkipProduct == true)
            {
                // update�J�z������(OrderUnit, String
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);

                // return
                return;
            }

            //  getAccountId()
            long l_lngAccountId = l_orderUnit.getAccountId();

            // getSubAccountId()
            long l_lngSubAccountId = l_orderUnit.getSubAccountId();

            // get�⏕����()(�g���A�J�E���g�}�l�[�W��::get�⏕����)
            SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);

            // getTraderId()
            long l_lngTraderId = l_orderUnit.getTraderId();


            // getLimitPrice()
            double l_dblLimitPrice = l_orderUnit.getLimitPrice();
            if (Double.isNaN(l_dblLimitPrice))
            {
                l_dblLimitPrice = 0D;
            }

            //  getContractsToClose( )(IfoContractSettleOrderUnitImpl::getContractsToClose)
            IfoContractSettleOrderUnit l_contractSettleOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;
            IfoClosingContractSpec[] l_contractSpecs = l_contractSettleOrderUnit.getContractsToClose();

            //  (*) �ԍώw����v�f����LOOP����
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            int l_intContractSpecsLength = l_contractSpecs.length;
            List l_lstArray = new ArrayList();
            try
            {
                for (int i = 0; i < l_intContractSpecsLength; i++)
                {
                    double l_dblExecutedQuantity =
                        l_contractSpecs[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0D;
                    }
                    double l_dblQuantity = 
                        l_contractSpecs[i].getQuantity() - l_dblExecutedQuantity;
                    long l_lngContractId = l_contractSpecs[i].getContractId();
                    WEB3IfoContractImpl l_contract =
                        (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);
                    double l_dblBalance =
                        l_contract.getQuantity() - l_contract.getLockedQuantity();
                    if (l_dblQuantity > l_dblBalance)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                    
                    if (l_dblQuantity > 0D)
                    {
                        l_lstArray.add(
                            new SettleContractEntry(l_lngContractId, l_dblQuantity));
                    }
                }
            }
            catch (WEB3BusinessLayerException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR);
                return;
            }
            SettleContractEntry[] l_settleContractEntry =
                new SettleContractEntry[l_lstArray.size()];
            l_lstArray.toArray(l_settleContractEntry);

            // �ԍϒ������e�𐶐�����B
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // ���ҁF�@@�擾�������҂h�c�ɊY�����鈵��
            WEB3GentradeTrader l_trader = null;
            if (l_lngTraderId != 0)
            {
                l_trader = (WEB3GentradeTrader)l_objectManager.getTrader(l_lngTraderId);
            }

            //���s����
            IfoOrderExecutionConditionType l_orderExecutionConditionType = l_orderUnitRow.getExecutionConditionType();

            //����������
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();

            //��������
            String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();

            //�t�w�l��l�F�@@�����P��.�t�w�l��l 
            double l_blnStopOrderPrice = l_orderUnitRow.getStopOrderPrice();
            
            //(W�w�l)�����w�l
            double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
            if (Double.isNaN(l_dblWLimitPrice))
            {
                l_dblWLimitPrice = 0D;
            }
            
            //�iW�w�l�j���s�����F�@@�����P��.�iW�w�l�j���s���� 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                l_orderUnitRow.getWLimitExecCondType();
            
			// ���񒍕��̒����P��ID
			Long l_longFirstOrderUnitId = null;
			// ����J�z�̏ꍇ
			if (l_orderUnitRow.getFirstOrderUnitId() == 0 || 
				l_orderUnitRow.getFirstOrderUnitIdIsNull())
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getOrderUnitId());
			}
			// �ȊO
			else
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getFirstOrderUnitId());
			}

            //�[��O�J�z�Ώۃt���O
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr((IfoOrderUnit)l_orderUnit);

            //���������敪�F�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

            // create�ԍϒ������e
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_trader,
                    l_dblLimitPrice,
                    l_orderExecutionConditionType,
                    l_datExpirationDate,
                    l_settleContractEntry,
                    l_strOrderConditionType,
                    l_blnStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_longFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            // validate�敨�ԍϒ���(SubAccount, IfoSettleContractOrderSpec)(�敨�����}�l�[�W��::validate�敨�ԍϒ���)
            //  [validate�敨�ԍϒ���()�Ɏw�肷�����]
            //  �⏕�����F�@@get�⏕����()�̖߂�l
            //  �ԍϒ������e�F�@@�i���������ԍϒ������e�I�u�W�F�N�g�j
            NewOrderValidationResult l_newOrderValidationResult = l_orderManager.validateFuturesSettleContractOrder(
                (WEB3GentradeSubAccount)l_subAccount,
                l_settleContractOrderSpec);

            // getProcessingResult()
            ProcessingResult l_processingResult = l_newOrderValidationResult.getProcessingResult();

            // isFailedResult( )(ProcessingResult::isFailedResult)
            boolean l_blnIsFailedResult = l_processingResult.isFailedResult();

            // (*2) �����R���Ŏ��s�����ꍇ�iisFailedResult() == true�j
            if (l_blnIsFailedResult)
            {
                // getErrorInfo( )
                ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
                String l_strErrorReasonCode = null;

                //�G���[�R�[�h����G���[���R�R�[�h�ɕϊ�����B
                if (WEB3ErrorCatalog.BUSINESS_ERROR_00148.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00031.equals(l_errorInfo))
                {
                    //�l���A�Ēl�`�F�b�N�ŃG���[�ɂȂ����ꍇ
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00004.equals(l_errorInfo))
                {
                    //������������~�̏ꍇ
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00735.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00003.equals(l_errorInfo))
                {
                    //�Y���������w��s��Ŏ���ł��Ȃ��ꍇ
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
                }
                else
                {
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
                }

                // update�J�z������(OrderUnit, String)(�敨�����J�zUnitServiceImpl::update�J�z������)
                //  [update�J�z������()�Ɏw�肷�����]
                //  �����P�ʁF�@@�����P��
                //  �����G���[���R�R�[�h�F
                //�@@getErrorInfo()�Ŏ擾�����G���[����蔻�肵�A�Y�����钍���G���[���R�R�[�h���Z�b�g����B
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    l_strErrorReasonCode);

                // return
                return;
            }

            // �萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            // (*3) �v���p�e�B�Z�b�g
            //  �萔��.�����`���l�� = �����P��.���񒍕��̒����`���l��
            //  �萔��.�،����ID = �����P��.�،���Ђh�c
            //  �萔��.���XID = �����P��.���X�h�c
            //  �萔��.������ = ������ԊǗ�.get������()
            //  �萔��.����R�[�h(SONAR) = �h52�F�ԍρh
            //  �萔��.�萔�����i�R�[�h = �h50�F�敨�h
            //  �萔��.�ٍϋ敪 = �h00�F���̑��h
            //  �萔��.is�w�l = �ԍϒ������e.isLimitOrder()
            //  �萔��.�����Y�����R�[�h = �敨OP����.get�����Y�����R�[�h()
            //  �萔��.���v��敪 = null
            //  �萔��.���� = �ԍϒ������e.getTotalQuantity()
            String l_strOrderChannel = l_orderUnitRow.getOrderChanel();
            String l_lngInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            long l_lngBranchId = l_orderUnit.getBranchId();
            Timestamp l_orderBizDate = new Timestamp(
                WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            String l_strBookValueIndexOp = WEB3CommisionProductCodeDef.INDEX_FUTURES;

            l_commission.setOrderChannel(l_strOrderChannel);
            l_commission.setInstitutionCode(l_lngInstitutionCode);
            l_commission.setBranchId(l_lngBranchId);
            l_commission.setOrderBizDate(l_orderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strBookValueIndexOp);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());            
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_product).getUnderlyingProductCode());

            l_commission.setDayTradeType(null);

            l_commission.setQuantity(l_settleContractOrderSpec.getTotalQuantity());
            
            // calc�T�Z���ϑ��v
            //  [calc�T�Z���ϑ��v()�Ɏw�肷�����]
            //  �萔���F�@@�萔���I�u�W�F�N�g
            //  �w�l�F�@@�����P��.getLimitPrice()
            //  �⏕�����F�@@�⏕�����I�u�W�F�N�g
            //  �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
            //  �ԍό��ʃG���g��[]�F ���������ԍό��ʃG���g��
            //  ���ʁF �ԍϒ������e.getTotalQuantity()
            //  �����F �i�ȉ��̂Ƃ���j
            //  �����P��.getSide()��SideEnum.BUY(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            //  �����P��.getSide()��SideEnum.SELL(��)�i=�������ԍρj�̏ꍇ�A�h���h���Z�b�g�B
            //  isSkip���z�`�F�b�N�F�@@true
            SideEnum l_sideEnum = null;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_sideEnum = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_sideEnum = SideEnum.BUY;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult =
                l_orderManager.calcEstimateSettlementIncome(
                    l_commission,
                    l_dblLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_isTradedProductImpl,
                    l_settleContractEntry,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_sideEnum,
                    true);

            // �敨OP�����J�z�X�V�C���^�Z�v�^(IfoOrderUnitParams, String)(�敨OP�����J�z�X�V�C���^�Z�v�^::�敨OP�����J�z�X�V�C���^�Z�v�^)
            //  [�R���X�g���N�^�̈���]
            //  (�J�z��)�����P��Params�F�@@�����P��.getDataSourceObject()�̖߂�l
            //  �����G���[���R�R�[�h�F�@@�h����h
            WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
                new WEB3IfoOrderCarryOverUpdateInterceptor(
                    (IfoOrderUnitParams)l_orderUnit.getDataSourceObject(),
                    WEB3ErrorReasonCodeDef.NORMAL);

            // set�萔��(�萔��)(�敨OP�����J�z�X�V�C���^�Z�v�^::set�萔��)
            l_interceptor.setCommissionFee(l_commission);

            // set�T�Z��n����v�Z����(�敨OP�T�Z��n����v�Z����)
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

            //set����敪
            l_interceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

            // setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor)(�敨�����}�l�[�W��::setThreadLocalPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            // createNewOrderId( )(�敨�����}�l�[�W��::createNewOrderId)
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            // submitSettleContractOrder(SubAccount, IfoSettleContractOrderSpec, long, String, boolean)(�敨�����}�l�[�W��::submitSettleContractOrder)
            // [submitSettleContractOrder()�Ɏw�肷�����]
            // �⏕�����F�@@�⏕����
            // �ԍϒ������e�F�@@�ԍϒ������e
            // �����h�c�F�@@�敨�����}�l�[�W��.createNewOrderId()
            // ����p�X���[�h�F ����.����p�X���[�h
            // isSkip�����R���F�@@true
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
            OrderSubmissionResult l_result = l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec,
                l_lngNewOrderId,
                l_mainAccount.getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //�J�z�Ώۂ̗\�񒍕������݂���ꍇ�i�p�����[�^.�\�񒍕��P�ʈꗗ��null�j
            if (l_lisRsvIfoOrderUnits != null)
            {
                //getOrderUnits(arg0 : long)
                //����ID�F�@@createNewOrderId()�̖߂�l
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngNewOrderId);

                //exec�A�������J�z(IfoOrderUnit, IfoOrderUnit, List)
                WEB3ToSuccIfoOrderCarryOverService l_service =
                    (WEB3ToSuccIfoOrderCarryOverService)Services.getService(
                        WEB3ToSuccIfoOrderCarryOverService.class);

                l_service.execToSuccOrderCarryOver(
                    (IfoOrderUnit)l_orderUnit,
                    (IfoOrderUnit)l_orderUnits[0],
                    l_lisRsvIfoOrderUnits);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�J�z������)<BR>
     * �J�z���������X�V����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɊY������J�z�������̒����P�ʃ��R�[�h��update����B<BR>
     * �@@<����> <BR>
     *     �����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID <BR>
     * �@@<�X�V���e> <BR>
     * �@@�@@�����P�ʃ��R�[�h.�����G���[���R�R�[�h = �p�����[�^.�����G���[���R�R�[�h <BR>
     * �@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ��� <BR>
     * �Q�j�@@�ȉ��̏����ɊY������J�z�������̒��������́A <BR>
     * �@@�@@�@@�ŏI�������R�[�h�̒����G���[���R�R�[�h ��update����B <BR>
     * �@@<����> <BR>
     * �@@�@@�����e�[�u��.�����P��ID�@@���@@�p�����[�^.�����P��.�����P��ID�@@and <BR>
     * �@@�@@�����e�[�u��.��������ԍ��@@���@@�p�����[�^.�����P��.���������ŏI�ʔ� <BR>
     * �@@<�X�V���e> <BR>
     * �@@�@@�������R�[�h.�����G���[���R�R�[�h�@@���@@�p�����[�^.�����G���[���R�R�[�h <BR>
     * �@@�@@�������R�[�h.�X�V���t�@@���@@���ݓ��� <BR>
     * �R�j�@@�ȉ��̏����ɊY������A�J�z�������̒����i�w�b�_�j�̍X�V������update����B<BR>
     * �@@<����> <BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID�@@���@@�p�����[�^.�����P��.����ID <BR>
     * �@@<�X�V���e> <BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V���t�@@���@@���ݓ��� <BR>
     * @@param l_orderUnit - �i�J�z���j�����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderErrorReasonCode - �����G���[���R�R�[�h<BR>
     * @@roseuid 40A88B4603CB
     */
    public void updateCarryOverOriginOrder(
        OrderUnit l_orderUnit,
        String l_strOrderErrorReasonCode)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        final String STR_METHOD_NAME = "updateCarryOverOriginOrder()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        // �P�j�@@�ȉ��̏����ɊY������J�z�������̒����P�ʃ��R�[�h��update����B<BR>
        // �@@<����> <BR>
        //     �����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID <BR>
        // �@@<�X�V���e> <BR>
        // �@@�@@�����P�ʃ��R�[�h.�����G���[���R�R�[�h = �p�����[�^.�����G���[���R�R�[�h <BR>
        // �@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ��� <BR>
        StringBuffer l_sbWhereOrder = new StringBuffer();
        l_sbWhereOrder.append(" order_unit_id = ? ");

        Object[] l_objWhereOrder = {
            String.valueOf(l_orderUnit.getOrderUnitId())};

        //�����P�ʃe�[�u���̌��ʃ��X�g
        List l_lisSearchResultOrder =
            l_queryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhereOrder.toString(),
                null,
                "FOR UPDATE",
                l_objWhereOrder);

        int l_intNumOrder = l_lisSearchResultOrder.size();
        if (l_intNumOrder > 0)
        {
            for (int i=0; i<l_intNumOrder; i++)
            {
                IfoOrderUnitParams l_paramsOrder = new IfoOrderUnitParams((IfoOrderUnitRow)l_lisSearchResultOrder.get(i));
                l_paramsOrder.setErrorReasonCode(l_strOrderErrorReasonCode);
                l_paramsOrder.setLastUpdatedTimestamp( l_finApp.getTradingSystem().getSystemTimestamp());
                l_queryProcessor.doUpdateQuery(l_paramsOrder);
            }
        }

        // �Q�j�@@�ȉ��̏����ɊY������J�z�������̒��������́A <BR>
        //     �@@�ŏI�������R�[�h�̒����G���[���R�R�[�h ��update����B <BR>
        //   <����> <BR>
        //     �����e�[�u��.�����P��ID�@@���@@�p�����[�^.�����P��.�����P��ID�@@and <BR>
        //     �����e�[�u��.��������ԍ��@@���@@�p�����[�^.�����P��.���������ŏI�ʔ� <BR>
        //   <�X�V���e> <BR>
        //     �������R�[�h.�����G���[���R�R�[�h�@@���@@�p�����[�^.�����G���[���R�R�[�h <BR>
        //     �������R�[�h.�X�V���t�@@���@@���ݓ��� <BR>
        StringBuffer l_sbWhereAction = new StringBuffer();
        l_sbWhereAction.append(" order_unit_id = ? ");
        l_sbWhereAction.append(" and order_action_serial_no = ? ");

        Object[] l_objWhereAction = {
            String.valueOf(l_orderUnit.getOrderUnitId()),
            String.valueOf(l_orderUnitRow.getLastOrderActionSerialNo())};

        //�����e�[�u���̌��ʃ��X�g
        List l_lisSearchResultAction =
            l_queryProcessor.doFindAllQuery(
                IfoOrderActionRow.TYPE,
                l_sbWhereAction.toString(),
                null,
                "FOR UPDATE",
                l_objWhereAction);

        int l_intNumAction = l_lisSearchResultAction.size();
        if (l_intNumAction > 0)
        {
            for (int i=0; i<l_intNumAction; i++)
            {
                IfoOrderActionParams l_paramsAction = new IfoOrderActionParams((IfoOrderActionRow)l_lisSearchResultAction.get(i));
                l_paramsAction.setErrorReasonCode(l_strOrderErrorReasonCode);
                l_paramsAction.setLastUpdatedTimestamp( l_finApp.getTradingSystem().getSystemTimestamp());
                l_queryProcessor.doUpdateQuery(l_paramsAction);
            }
        }

        // �R�j�@@�ȉ��̏����ɊY������A�J�z�������̒����i�w�b�_�j�̍X�V������update����B<BR>
        // �@@<����> <BR>
        //     �����i�w�b�_�j�e�[�u��.����ID�@@���@@�p�����[�^.�����P��.����ID <BR>
        //   <�X�V���e> <BR>
        //   �@@�����i�w�b�_�j���R�[�h.�X�V���t�@@���@@���ݓ��� <BR>
        IfoOrderRow l_orderRow = IfoOrderDao.findRowByOrderId(l_orderUnit.getOrderId());
        IfoOrderParams l_paramsHead = new IfoOrderParams(l_orderRow);
        l_paramsHead.setLastUpdatedTimestamp( l_finApp.getTradingSystem().getSystemTimestamp());
        l_queryProcessor.doUpdateQuery(l_paramsHead);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (expire�J�z������)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�����J�z�jexpire�J�z�������v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@return void
     * @@roseuid 40A85D25035D
     */
    public void expireCarryOverOriginOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireCarryOverOrder()";
        log.entering(STR_METHOD_NAME);

        //1.1  getDataSourceObject( )(OrderUnit::getDataSourceObject)
        IfoOrderUnitParams l_orderUnitParams = (IfoOrderUnitParams)l_orderUnit.getDataSourceObject();

        //1.2 �敨OP�����J�z�X�V�C���^�Z�v�^(IfoOrderUnitParams, String)(�敨OP�����J�z�X�V�C���^�Z�v�^::�敨OP�����J�z�X�V�C���^�Z�v�^)
        //  [�R���X�g���N�^�̈���]
        //  (�J�z��)�����P��Params�F�@@�i�擾�����s�I�u�W�F�N�g�j
        //  �����G���[���R�R�[�h�F�@@0000�i����j
        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor(
                l_orderUnitParams,
                WEB3ErrorReasonCodeDef.NORMAL);

        //1.3 setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor)
        //  (�敨�����}�l�[�W��::setThreadLocalPersistenceEventInterceptor)
        //  [setThreadLocalPersistenceInterceptor()�Ɏw�肷�����]
        //  �C���^�Z�v�^�F�@@�i���������敨OP�����J�z�X�V�C���^�Z�v�^�j
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.4 getOrderId( )(OrderUnit::getOrderId)
        long l_lngOrderId = l_orderUnit.getOrderId();

        //1.5 expireOrder(long)(�敨�����}�l�[�W��::expireOrder)
        //  [expireOrder()�Ɏw�肷�����]
        //  �����h�c�F�@@�����P��.getOrderId()
        ProcessingResult l_processingResult = l_orderManager.expireOrder(l_lngOrderId);
        if (l_processingResult.isFailedResult())
        {
            ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
            WEB3BaseException l_baseException =
                new WEB3BaseException(
                    l_errorInfo,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_errorInfo.getErrorMessage());
            throw l_baseException;
        }

        //expireAll�\�񒍕��P��(long)
        //�����h�c�F�@@�����P��.getOrderId()
        WEB3ToSuccReservationIfoOrderUpdateService l_service =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_service.expireAllOrderUnit(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }
}
@
