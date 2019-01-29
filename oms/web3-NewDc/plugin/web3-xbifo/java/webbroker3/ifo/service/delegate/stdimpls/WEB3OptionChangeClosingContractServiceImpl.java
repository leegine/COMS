head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeClosingContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍσT�[�r�X����(WEB3OptionChangeClosingContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
              001: 2004/07/19 ���Ō� (���u) �o�b�O�C��
              002: 2004/07/23 ���Ō� (���u) WEB3TransactionTypeSONARDef��WEB3IfoTransactionTypeDef�������ւ���
              003: 2004/07/28 ���Ō�  �Ή��o�b�O WEB3_IFO_UT-000039 WEB3_IFO_UT-000040
              004: 2004/07/30 ���Ō� �Ή��o�b�O WEB3_IFO_UT-000081�A82�A83
              005: 2004/07/30 ���Ō� �Ή��o�b�O WEB3_IFO_UT-0000120
              006: 2004/08/09 ���Ō� (Sinocom) �Ή�����:�yWEB3-XBIFO-A-CD-0082�z
              007: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              008: 2004/08/14 ���Ō� �Ή��o�O BUG143
              009: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802
              010: 2004/08/15 ���Ō� �Ή��o�O BUG83
              011: 2006/07/14 ���@@�r�@@(���u)�@@ �d�l�ύX�@@���f��476
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.659
Revesion History : 2007/06/21 ��іQ (���u) �d�l�ύX���f��No.740
Revesion History : 2007/11/20 �����q (���u) �d�l�ύX���f��No.807, No.815, No.819
Revesion History : 2008/04/14 �����F(���u) ���f��856
Revesion History : 2008/08/18 ���� (���u) IFO�����_�Ή�
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP�����ԍσT�[�r�XImpl)<BR>
 * �����w���I�v�V���������ԍσT�[�r�X�����N���X<BR>
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3OptionChangeClosingContractServiceImpl extends WEB3OptionClientRequestService
    implements WEB3OptionChangeClosingContractService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionChangeClosingContractServiceImpl.class);

    /**
     * @@roseuid 40C0BAF000EA
     */
    public WEB3OptionChangeClosingContractServiceImpl()
    {

    }

    /**
     * �����w���I�v�V���������ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́A<BR>submit����()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056CCF702FB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3OptionsCloseMarginChangeConfirmRequest)
        {
            WEB3OptionsCloseMarginChangeConfirmRequest l_confirmRequest =
                (WEB3OptionsCloseMarginChangeConfirmRequest)l_request;
            WEB3OptionsCloseMarginChangeConfirmResponse l_confirmResponse =
                this.validateOrder(l_confirmRequest);

            log.exiting(STR_METHOD_NAME);
            return l_confirmResponse;
        }
        else if (l_request instanceof WEB3OptionsCloseMarginChangeCompleteRequest)
        {
            WEB3OptionsCloseMarginChangeCompleteRequest l_completeRequest =
                (WEB3OptionsCloseMarginChangeCompleteRequest)l_request;
            WEB3OptionsCloseMarginChangeCompleteResponse l_completeResponse =
                this.submitOrder(l_completeRequest);

            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
    }

    /**
     * (validate����)<BR>
     * �����w���I�v�V�����̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �u�iOP�����ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V���������ԍϊm�F���N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056CD2E0193
     */
    protected WEB3OptionsCloseMarginChangeConfirmResponse validateOrder(WEB3OptionsCloseMarginChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder()";
        log.entering(STR_METHOD_NAME);

        log.debug("�V�[�P���X�}�u�iOP�����ԍσT�[�r�X�jvalidate�����P�v�Q��");
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        try
        {
            //1.1 validate()
            l_request.validate();

            //1.3 getOrderUnits( )(IfoOrderImpl::getOrderUnits)
            OrderUnit l_orderUnit = l_orderManager.getOrderUnits(Long.parseLong(l_request.id))[0];

            //1.2 IfoOrderImpl(IfoOrderRow)
            IfoOrderUnitRow l_ifoOrderUnitRow = 
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.4 create�ԍό��ʃG���g��(long, double, �ԍό���[])(OP�����}�l�[�W��::create�ԍό��ʃG���g��)
            //  [create�ԍό��ʃG���g��()�Ɏw�肷�����]
            //  �����P��ID = �i�擾���������P��.getOrderUnitId()�j
            //  �������� = ���N�G�X�g�f�[�^.��������
            //  ����ID[] = ���N�G�X�g�f�[�^.�ԍό���[]
            long l_lngOrderUnitId  = l_orderUnit.getOrderUnitId();
            //  �����P��.getDateSourceObject().getClosingOrder() != 0�i�����_���j
            //  and (���N�G�X�g�f�[�^.�������� == 0 or ���N�G�X�g�f�[�^.�������� == null)
            //  �̏ꍇ�A��O���X���[����B
            double l_dblOrderQuantity = 0D;
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                if (l_request.opOrderQuantity == null || Double.parseDouble(l_request.opOrderQuantity) == 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                        STR_METHOD_NAME);
                }
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngOrderUnitId,
                    l_dblOrderQuantity,
                    l_request.closeMarginContractUnits);

            //1.5  get������( )(������ԊǗ�::get������)
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.6 create�ԍϒ������e(long, long, double, SettleContractEntry[], 
            //      IfoOrderExecutionConditionType, Date, Date, String,
            //      String, String, double, double, IfoOrderExecutionConditionType, 
            //      String, String, boolean)
			//����ID�F�@@�����P��.����ID 
			//�����P��ID�F�@@�����P��.�����P��ID 
			//�����w�l�F�@@���N�G�X�g�f�[�^.�����P�� 
			//�ԍό��ʃG���g��[]�F�@@create�ԍό��ʃG���g���̖߂�l 
			//�������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l 

            long l_lngOrderId = l_orderUnit.getOrderId();
            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // �������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
            // OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            Date l_changeExpirationDate = null;
            // ���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ
            if (l_request.expirationDate != null)
            {
                // �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                Market l_market = null; 
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // �敨OP����(*1).�����R�[�h
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // �s��.getMarketCode()
                l_market = l_ifoProductImpl.getPrimaryMarket();
                String l_strMarketCode = l_market.getMarketCode();

                // �����������F
                l_changeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            // ���N�G�X�g�f�[�^.�����L������ == null�̏ꍇ�Aget������()�̖߂�l
            else
            {
                l_changeExpirationDate = l_orderBizDate;
            }
			//���������F�@@���N�G�X�g�f�[�^.���������敪
            String l_strOrderCond = l_request.orderCondType;
			//�����������Z�q�F�@@ 
			//�@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q  
			//�@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�����������Z�q 
			//�t�w�l��l�^�C�v�F�@@ 
			//�@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�v���~�A���^�����Y���i 
			//�@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�v���~�A���^�����Y���i 
			//�t�w�l��l�F�@@ 
			//�@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p���������P�� 
			//�@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p���������P�� 
			//�����������Z�q�F�@@ 
            String l_strOrderCondOperator = null;
            //�t�w�l��l�^�C�v�F
            String l_strStopOrderBasePriceType = null;
            //�t�w�l��l�F
            double l_dblStopOrderBasePrice = 0.0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.stopPremium_underlyingAssets;
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {                
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.wlimitPremium_underlyingAssets;
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                }
            }
            
			//�iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� 
            double l_dblWLimitPriceChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPriceChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
			//�iW�w�l�j���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����) 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
			
            //�iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
			//�����㒍�������敪�F�@@���N�G�X�g�f�[�^.���������敪                
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(
            //���N�G�X�g�f�[�^.���������敪�A�����P��.���XID)
            boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_orderUnit.getBranchId());
            
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                 WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                       l_lngOrderId,
                       l_lngOrderUnitId,
                       l_dblLimitPrice,
                       l_eqOrderEntry,
                       l_changeExecCondType,
                       l_changeExpirationDate,
                       l_orderBizDate,
                       l_strOrderCond,
                       l_strOrderCondOperator,
                       l_strStopOrderBasePriceType,
                       l_dblStopOrderBasePrice,
                       l_dblWLimitPriceChange,
                       l_wLimitExecCondType,
                       l_strWLimitEnableStatusDiv,
                       l_strExpirationDateType,
                       l_blnEveningSessionCarryoverFlag);

            //1.7 get�⏕����( )(OP�����ԍσT�[�r�XImpl::get�⏕����)
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            
            //1.8 validate�ԍϒ�������(SubAccount, IfoChangeSettleContractOrderSpec)(OP�����}�l�[�W��::validate�ԍϒ�������)
            OrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(
                l_subAccount, 
                l_ifoOrderSpec);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }

            //1.9 getAfterChangeTotalQuantity( )(�ԍϒ������e::getAfterChangeTotalQuantity)
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            if (Double.isNaN(l_dblTotalQuantity))
            {
                l_dblTotalQuantity = 0D;
            }

            //1.10 �敨OP����(long)(�敨OP����::�敨OP����)
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);

            //1.11 getTradedProduct( )(�敨OP����::getTradedProduct)
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();

            //1.12�@@���������擾����
            WEB3IfoProductQuote l_currentInfo = l_ifoTradedProductImpl.getCurrentInfo(null);

            //1.13  �萔��( )(�萔��::�萔��)
            WEB3GentradeCommission l_ifoGentradeCommission = new WEB3GentradeCommission();

            //1.14  �v���p�e�B�Z�b�g(*1)
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;

            l_ifoGentradeCommission.setOrderChannel(this.getLoginChannel());
            String l_strInstitutionCode = String.valueOf(l_subAccountRow.getInstitutionCode());
            l_ifoGentradeCommission.setInstitutionCode(l_strInstitutionCode);
            l_ifoGentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            l_ifoGentradeCommission.setOrderBizDate(new Timestamp(l_orderBizDate.getTime()));
            l_ifoGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            l_ifoGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            //�ٍϋ敪���Z�b�g����B
            l_ifoGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnIsLimitPrice = false;
            }
            else
            {
                l_blnIsLimitPrice = true;
            }
            l_ifoGentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            l_ifoGentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            l_ifoGentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            l_ifoGentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            
            //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
            l_ifoGentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���v��敪 = �����P��.���v��敪
            l_ifoGentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());
            
            //�萔��.���� = �ԍϒ������e.getAfterChangeTotalQuantity()
            l_ifoGentradeCommission.setQuantity(l_dblTotalQuantity);

            //1.15 getSide( )(IfoBaseOrderUnitImpl::getSide)
            SideEnum l_side = l_orderUnit.getSide();

            //1.16 getExecutedQuantity( )(IfoBaseOrderUnitImpl::getExecutedQuantity)
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            //1.17 getExecutedAmount( )(IfoBaseOrderUnitImpl::getExecutedAmount)
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;
            }

            //1.18 calc�������T�Z��n���(�萔��, double, SubAccount, �敨OP�������, double, SideEnum, boolean, double, double, boolean)
            //  (OP�����}�l�[�W��::calc�������T�Z��n���)
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
            l_estimateDeliveryAmountCalcResult =
                l_orderManager.calcChangeEstimateDeliveryAmount(
                    l_ifoGentradeCommission,
                    l_dblLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_ifoTradedProductImpl,
                    l_dblTotalQuantity,
                    l_side,
                    true,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);
            //1.19 �iOP�����ԍσT�[�r�X�jvalidate�����Q�i�Q�Ɓj        
            //1.2 createResponse( )(�����w���I�v�V���������ԍϊm�F���N�G�X�g::createResponse)            
            WEB3OptionsCloseMarginChangeConfirmResponse l_response =
                (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            
            WEB3FuturesOptionsContractUnit l_contractUnit = null;
            WEB3IfoContractImpl l_ifoContract = null;
            ArrayList l_list = new ArrayList();
            for (int i = 0; i < l_eqOrderEntry.length; i++)
            {
                //1.3 ���ʖ���( )(���ʖ���::���ʖ���)
                l_contractUnit = new WEB3FuturesOptionsContractUnit();

                //1.4 getContractId( )(SettleContractEntry::getContractId)
                long l_lngcontractId = l_eqOrderEntry[i].getContractId();

                //1.5 getQuantity( )(SettleContractEntry::getQuantity)
                double l_dblQuantity = l_eqOrderEntry[i].getQuantity();
                if (Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0D;
                }

                //1.6 �敨OP����(long)(�敨OP����::�敨OP����)
                l_ifoContract = new WEB3IfoContractImpl(l_lngcontractId);

                //1.7 getOpenDate( )(�敨OP����::getOpenDate)
                Date l_datOpenDate = l_ifoContract.getOpenDate();

                //1.8 getContractPrice( )(�敨OP����::getContractPrice)
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                if (Double.isNaN(l_dblContractPrice))
                {
                    l_dblContractPrice = 0D;
                }

                //1.9 get�������(double)(�敨OP����::get�������)
                double l_dblContractExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                if (Double.isNaN(l_dblContractExecutedAmount))
                {
                    l_dblContractExecutedAmount = 0D;
                }
                
                //get���萔��() + get���萔�������()
                BigDecimal l_bdContractCommission = new BigDecimal(
                    l_ifoContract.getContractCommission(l_dblQuantity, l_lngOrderUnitId) + "");
                BigDecimal l_bdContractCommissionConsumptionTax = new BigDecimal(
                    l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity, l_lngOrderUnitId) + "");
                double l_dblContractCommission =
                    l_bdContractCommission.add(l_bdContractCommissionConsumptionTax).doubleValue();

                //�������擾����B
                double l_dblPrice = 0D;
                if(l_currentInfo != null)
                {
                    l_dblPrice = l_currentInfo.getCurrentPrice();
                }

                //1.10 get�]�����v(double, double)(�敨OP����::get�]�����v)
                double l_dblIncome = l_ifoContract.getEvaluateIncome(l_dblPrice,l_dblQuantity);
                if (Double.isNaN(l_dblIncome))
                {
                    l_dblIncome = 0D;
                }

                //1.11  getQuantity( )(�敨OP����::getQuantity)
                double l_dblGetQuantity = l_ifoContract.getQuantity();
                if (Double.isNaN(l_dblGetQuantity))
                {
                    l_dblGetQuantity = 0D;
                }

                //1.12  get�ԍϖ��ϐ���(long)(�敨OP����::get�ԍϖ��ϐ���)
                double l_dblClosingExecuteContractCnt = l_ifoContract.getClosingExecuteContractCnt(l_orderUnit.getOrderUnitId());

                //1.13  (*2)�v���p�e�B�Z�b�g
                l_contractUnit.id                    = String.valueOf(l_lngcontractId);
                l_contractUnit.openDate              = WEB3DateUtility.toDay(l_datOpenDate);
                l_contractUnit.contractPrice         = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_contractUnit.contractQuantity      = WEB3StringTypeUtility.formatNumber(l_dblGetQuantity);
                l_contractUnit.contractExecPrice     = WEB3StringTypeUtility.formatNumber(l_dblContractExecutedAmount);
                l_contractUnit.contractCommission    = WEB3StringTypeUtility.formatNumber(l_dblContractCommission);
                l_contractUnit.income                = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                l_contractUnit.incomeCost            = WEB3StringTypeUtility.formatNumber(
                    new BigDecimal(l_dblIncome + "").subtract(new BigDecimal(l_dblContractCommission + "")).doubleValue());
                l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                l_contractUnit.contractExecQuantity  = WEB3StringTypeUtility.formatNumber(l_dblClosingExecuteContractCnt);
                l_contractUnit.settlePriority        = String.valueOf(i + 1);
                //���ʖ���.����敪 = ����.����敪
                l_contractUnit.sessionType =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();
                
                l_list.add(l_contractUnit);
            }

            WEB3FuturesOptionsContractUnit[] l_ContractUnitArray = (WEB3FuturesOptionsContractUnit[])l_list.toArray(new WEB3FuturesOptionsContractUnit[l_list.size()]);

            //1.14 getProduct( )(�敨OP����::getProduct)
            l_ifoContractImpl.getProduct();

            //1.15 get�s��ǌx���w��(���X, String)(������ԊǗ�::get�s��ǌx���w��)
            WEB3GentradeBranch l_gentradeBranch = null;

            // throws NotFoundException
            l_gentradeBranch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();

            String[] l_strWEB3GentradeTradingTimeManagement =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    WEB3FuturesOptionDivDef.OPTION);

            //1.16 get�T�Z��n���( )(�敨OP�T�Z��n����v�Z����::get�T�Z��n���)
            double l_dblEstimateDeliveryAmount =l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
            if (Double.isNaN(l_dblEstimateDeliveryAmount))
            {
                l_dblEstimateDeliveryAmount = 0D;
            }
            double l_dblCalcUnitPrice = l_estimateDeliveryAmountCalcResult.getCalcUnitPrice();
            if (Double.isNaN(l_dblCalcUnitPrice))
            {
                l_dblCalcUnitPrice = 0D;
            }

            //1.17 get�萔���R�[�X( )(�敨OP�T�Z��n����v�Z����::get�萔���R�[�X)
            String l_strCommissionCourse = l_estimateDeliveryAmountCalcResult.getCommissionCourse();
            
            //1.18 get�萔��( )(�敨OP�T�Z��n����v�Z����::get�萔��)
            double l_dblCommission = l_estimateDeliveryAmountCalcResult.getCommission();

            //1.19 get�萔�������( )(�敨OP�T�Z��n����v�Z����::get�萔�������)
            double l_dblCommissionTax = l_estimateDeliveryAmountCalcResult.getCommissionTax();

            //1.20  (*3)�v���p�e�B�Z�b�g
            l_response.contractUnits = l_ContractUnitArray;
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmount);
            l_response.commissionCourse = l_strCommissionCourse;
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_dblCommission);
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblCommissionTax);
            l_response.messageSuspension = l_strWEB3GentradeTradingTimeManagement;
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
            l_response.checkDate = WEB3DateUtility.toDay(l_orderBizDate);
            // ���X�|���X.�����L������ = �ԍϒ������e.����������
            l_response.expirationDate = l_ifoOrderSpec.getChangeExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (submit����)<BR>
     * �����w���I�v�V�����̒����ԍϒ�����o�^����B<BR>
     * <BR>
     * �u�iOP�����ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V���������ԍϊ������N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056CD2E01B3
     */
    protected WEB3OptionsCloseMarginChangeCompleteResponse submitOrder(WEB3OptionsCloseMarginChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder()";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1.1  validate
            l_request.validate();

            //1.2 IfoOrderImpl
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnits(Long.parseLong(l_request.id))[0];

            //1.3 getOrderUnits
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //1.4 create�ԍό��ʃG���g��(long, double, �ԍό���[])(OP�����}�l�[�W��::create�ԍό��ʃG���g��)
            //  [create�ԍό��ʃG���g��()�Ɏw�肷�����]
            //  �����P��ID = �i�擾���������P��.getOrderUnitId()�j
            //  �������� = ���N�G�X�g�f�[�^.��������
            //  ����ID[] = ���N�G�X�g�f�[�^.�ԍό���[]
            long l_lngOrderId      = l_orderUnit.getOrderId();
            long l_lngOrderUnitId  = l_orderUnit.getOrderUnitId();
            double l_dblOrderQuantity = 0D;
            if (l_request.opOrderQuantity != null)
            {
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);                
            }
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngOrderUnitId,
                    l_dblOrderQuantity,
                    l_request.closeMarginContractUnits);

            //1.5  get������( )(������ԊǗ�::get������)
            Date l_orderBizDate = null;
            if (l_request.checkDate == null)
            {
                l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            
            //1.6 create�ԍϒ������e(long, long, double, SettleContractEntry[], 
            //      IfoOrderExecutionConditionType, Date, Date, String,
            //      String, String, double, double, IfoOrderExecutionConditionType, 
            //      String, String, boolean)
			//����ID�F�@@�����P��.����ID 
			//�����P��ID�F�@@�����P��.�����P��ID 
			//�����w�l�F�@@���N�G�X�g�f�[�^.�����P�� 
			//�ԍό��ʃG���g��[]�F�@@create�ԍό��ʃG���g���̖߂�l 
			//�������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l 

            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // �������s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            // OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            Date l_changeExpirationDate = null;
            // ���N�G�X�g�f�[�^.�����L������ != null�̏ꍇ
            if (l_request.expirationDate != null)
            {
                // �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID)�ɂĎ擾
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                Market l_market = null; 
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // �敨OP����(*1).�����R�[�h
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // �s��.getMarketCode()
                l_market = l_ifoProductImpl.getPrimaryMarket();
                String l_strMarketCode = l_market.getMarketCode();

                // �����������F
                l_changeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            // ���N�G�X�g�f�[�^.�����L������ == null�̏ꍇ
            else
            {
                l_changeExpirationDate = l_orderBizDate;
            }
			//���������F�@@���N�G�X�g�f�[�^.���������敪
            String l_strOrderCond = l_request.orderCondType;
			//�����������Z�q�F�@@ 
			//�@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q  
			//�@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�����������Z�q 
			//�t�w�l��l�^�C�v�F�@@ 
			//�@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p�v���~�A���^�����Y���i 
			//�@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p�v���~�A���^�����Y���i 
			//�t�w�l��l�F�@@ 
			//�@@���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.�t�w�l�p���������P�� 
			//�@@���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ�A���N�G�X�g�f�[�^.W�w�l�p���������P�� 
			//�����������Z�q�F�@@ 
            String l_strOrderCondOperator = null;
            //�t�w�l��l�^�C�v�F
            String l_strStopOrderBasePriceType = null;
            //�t�w�l��l�F
            double l_dblStopOrderBasePrice = 0.0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.stopPremium_underlyingAssets;
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }
            }
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType ))
            {                
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.wlimitPremium_underlyingAssets;
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                }
            }
            
			//�iW�w�l�j�����w�l�F�@@���N�G�X�g�f�[�^.W�w�l�p�����P�� 
            double l_dblWLimitPriceChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPriceChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
			//�iW�w�l�j���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.�v�w�l�p���s����) 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
			
            //�iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
			//�����㒍�������敪�F�@@���N�G�X�g�f�[�^.���������敪                
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(
            //���N�G�X�g�f�[�^.���������敪�A�����P��.���XID)
            boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_orderUnit.getBranchId());
            
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                 WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                       l_lngOrderId,
                       l_lngOrderUnitId,
                       l_dblLimitPrice,
                       l_eqOrderEntry,
                       l_changeExecCondType,
                       l_changeExpirationDate,
                       l_orderBizDate,
                       l_strOrderCond,
                       l_strOrderCondOperator,
                       l_strStopOrderBasePriceType,
                       l_dblStopOrderBasePrice,
                       l_dblWLimitPriceChange,
                       l_wLimitExecCondType,
                       l_strWLimitEnableStatusDiv,
                       l_strExpirationDateType,
                       l_blnEveningSessionCarryoverFlag);
            //1.6 get�⏕����
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            //1.7 validate�ԍϒ�������
            OrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(l_subAccount,l_ifoOrderSpec);

            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }
            
            //1.8 getAfterChangeTotalQuantity
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            if (Double.isNaN(l_dblTotalQuantity))
            {
                l_dblTotalQuantity = 0D;
            }
            
            //1.9 �敨OP����
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);

            //1.10 getTradedProduct
            l_ifoContractImpl.getTradedProduct();

            //1.11 �萔��
            WEB3GentradeCommission l_ifoGentradeCommission = new WEB3GentradeCommission();

            //1.12 �v���p�e�B�Z�b�g
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;
            l_ifoGentradeCommission.setOrderChannel(this.getLoginChannel());
            l_ifoGentradeCommission.setInstitutionCode(l_subAccountRow.getInstitutionCode());
            l_ifoGentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //�萔��.������ = ������ԊǗ�.get������()
            l_ifoGentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            l_ifoGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            l_ifoGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            //�ٍϋ敪���Z�b�g����B
            l_ifoGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnIsLimitPrice = false;
            }
            else
            {
                l_blnIsLimitPrice = true;
            }
            l_ifoGentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            l_ifoGentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            l_ifoGentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            l_ifoGentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            
            //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
            l_ifoGentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());
            
            //�萔��.���v��敪 = �����P��.���v��敪
            l_ifoGentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());
            
            //�萔��.���� = �ԍϒ������e.getAfterChangeTotalQuantity()
            l_ifoGentradeCommission.setQuantity(l_dblTotalQuantity);
            
            //1.13 getSide
            SideEnum l_side = l_orderUnit.getSide();

            //1.14 getExecutedQuantity
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }
            
            //1.15 getExecutedAmount
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;
            }

            //1.16 calc�������T�Z��n��� 
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();
            double l_dblPrice = 0D;
            if (l_request.checkPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_request.checkPrice); 
            }
            else
            {
                if (l_request.limitPrice != null)
                {
                    l_dblPrice = Double.parseDouble(l_request.limitPrice); 
                }
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
            l_estimateDeliveryAmountCalcResult = l_orderManager.calcChangeEstimateDeliveryAmount(
                l_ifoGentradeCommission,
                l_dblPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                l_ifoTradedProductImpl,
                l_dblTotalQuantity,
                l_side,
                true,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                false);
            
            //1.17 �iOP�����ԍσT�[�r�X�jsubmit�����Q�i�Q�Ɓj

            //1.3 �敨OP�ԍϒ����X�V�C���^�Z�v�^
            WEB3IfoSettleContractChangeUpdateInterceptor l_ifoUpdateInterceptor = 
                new WEB3IfoSettleContractChangeUpdateInterceptor(l_ifoOrderSpec);

            //1.4 (*1)�v���p�e�B�Z�b�g
            l_ifoUpdateInterceptor.setCommision(l_ifoGentradeCommission);
            l_ifoUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
            l_ifoUpdateInterceptor.setOrderCond(l_request.orderCondType);
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_ifoUpdateInterceptor.setWLimitPriceChange(l_ifoOrderSpec.getWLimitPriceChange());
            Trader l_trader = this.getTrader();   
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_ifoUpdateInterceptor.setTraderId(l_lngTraderId);

			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_ifoUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);

            //1.5 setThreadLocalPersistenceEventInterceptor
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoUpdateInterceptor);

            //1.6 submitChangeSettleContractOrder
            log.debug("submit�f�[�^�x�[�X �J�n");
            OrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitChangeSettleContractOrder(
                    l_subAccount, 
                    l_ifoOrderSpec, 
                    l_request.password, 
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            log.debug("submit�f�[�^�x�[�X �I��");

            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
                {
                    //1.7  �]�͍Čv�Z
                    WEB3TPTradingPowerService l_tradingPowerService = 
                        (WEB3TPTradingPowerService)Services.getService(
                            WEB3TPTradingPowerService.class);
                    l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
                }

                //is�\�񒍕��m�F�v(IfoOrderUnit)
                boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist((IfoOrderUnit)l_orderUnit);

                //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
                List l_lisOpenReserveIfoOrderUnits = null;
                if (l_blnIsReserveOrderExist)
                {
                    //get�L���\�񒍕��P�ʈꗗ(�e�����̒���ID : long)
                    WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                        (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationIfoOrderUpdateService.class);
                    l_lisOpenReserveIfoOrderUnits =
                        l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(((IfoOrderUnit)l_orderUnit).getOrderId());
                }

                //1.8  WEB3GenResponse
                WEB3GenResponse l_genResponse = l_request.createResponse();
                WEB3OptionsCloseMarginChangeCompleteResponse l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_genResponse;

                //1.9 (*2) �v���p�e�B�Z�b�g
                l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
                l_response.orderActionId = l_request.id;
                if (l_lisOpenReserveIfoOrderUnits != null)
                {
                    l_response.succSettingFlag = true;
                }
                else
                {
                    l_response.succSettingFlag = false;
                }
                log.debug("l_response.lastUpdateTimestamp = " + l_response.lastUpdatedTimestamp);
                log.debug("l_response.orderActionId = "+ l_response.orderActionId);

                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            else
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }
}
@
