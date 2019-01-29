head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderManagerPersistenceEventInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������C���^�Z�v�^(WEB3EquityOrderManagerPersistenceEventInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 �Ή� (���u) �C��
Revesion History : 2004/09/27 �@@�� (���u) �C��
Revesion History : 2004/11/30 SRA�����@@�@@ �C��
Revesion History : 2005/01/05 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/07/19 �юu�� (���u) �c�a�X�V�d�l156
Revesion History : 2006/08/01 ���r (���u) �c�a�X�V�d�l163
Revesion History : 2006/11/02 �đo�g (���u) �c�a�X�V�d�lNo.168,No.174�ANo.180
Revesion History : 2007/04/26 �Ӑ� (���u) �c�a�X�V�d�lNo.198
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.define.WEB3EquityVoucherNoDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������A���������P�ʁA�������������e�[�u��<BR>
 * �̃J�X�^�}�C�Y���ڂɒl���Z�b�g����B<BR>
 *�iEqTypeOrderManagerPersistenceEventInterceptor�̎����j
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityOrderManagerPersistenceEventInterceptor
    implements EqTypeOrderManagerPersistenceEventInterceptor
{

    /**
     * (�����������e) <BR>
     * �����������e�I�u�W�F�N�g <BR>
     */
    private WEB3EquityNewCashBasedOrderSpec equityNewCashBasedOrderSpec;

    /**
     * (�J�z�������P��)
     */
    private EqTypeOrderUnit l_eqtypeOrderUnit;
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪�B
     */
    private String orderRootDiv;
    
    /**
     * (�󒍓���)<BR>
     * �󒍓����B
     */
    private Date receivedDateTime;
    
    /**
     * (���������C���^�Z�v�^) <BR>
     * �R���X�g���N�^�B <BR>
     * @@roseuid 4010C4A700BC
     */
    public WEB3EquityOrderManagerPersistenceEventInterceptor()
    {
    }

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderManagerPersistenceEventInterceptor.class);

    /**
     * (�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �P�j �������e���� <BR>
     * �@@this.�����������e�v���p�e�B��null�̏ꍇ�� <BR>
     *   �p�����[�^.�����P��Params��ԋp���������I������B <BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g <BR>
     * �@@this.�����������e�v���p�e�B����A <BR>
     *   �p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �X�V���e�́A <BR>
     * �u�����������t_���������P�ʃe�[�u��.xls�v�A <BR>
     * �u�����������t_���������P�ʃe�[�u��.xls�v
     * �u�i�����J�z[�J�z]�j�����P�ʃe�[�u���v�A�Q�ƁB <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v) <BR>
     * INSERT�܂��́AUPDATE�B <BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B <BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����) <BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j <BR>
     * @@param l_eqtypeOrderUnitParams - (�����P��Params) <BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B <BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4010C4A700B0
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if(l_eqtypeOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // ��莞�͏������s��Ȃ�
        if (this.equityNewCashBasedOrderSpec == null)
        {
            return l_eqtypeOrderUnitParams;
        }

        // �����������e
        WEB3EquityNewCashBasedOrderSpec l_orderSpec =
            this.getEquityOrderSpec();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // ���������擾
        Timestamp l_processTime =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        try
        {
            //���t�A���t�̏ꍇ
            if ((l_orderSpec.getFirstOrderUnitId() == null)
              ||(l_orderSpec.getFirstOrderUnitId().longValue() <= 0))
            {

                // ���X�I�u�W�F�N�g�擾             
                AccountManager l_accMgr = l_finApp.getAccountManager();
                Branch l_branch =
                    l_accMgr.getBranch(l_eqtypeOrderUnitParams.branch_id);

                //���ʃR�[�h�iMMDDXXXXX�j�̔�
                WEB3HostReqOrderNumberManageService l_numberService =
                    (WEB3HostReqOrderNumberManageService) Services.getService(
                        WEB3HostReqOrderNumberManageService.class);
                String l_newOrderRequestNumber =
                    l_numberService.getNewNumber(
                        l_orderSpec.getInstitutionCode(),
                        l_branch.getBranchCode(),
                        ProductTypeEnum.EQUITY);

                //���n�v���z
                double l_dblCapitalGain = 0;
                l_eqtypeOrderUnitParams.setCapitalGain(l_dblCapitalGain);
                
                //���n�v�Ŋz
                double l_dblCapitalGainTax = 0;
                l_eqtypeOrderUnitParams.setCapitalGainTax(l_dblCapitalGainTax);
                
                //�l�i����
                l_eqtypeOrderUnitParams.setPriceConditionType(l_orderSpec.getPriceConditionType());

                //�iW�w�l�j���s����
                //�����������e.get�iW�w�l�j���s����
                //�������A�����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�B
                if (WEB3OrderingConditionDef.DEFAULT.equals(l_eqtypeOrderUnitParams.getOrderConditionType())
                    || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_eqtypeOrderUnitParams.getOrderConditionType()))
                {
                    l_eqtypeOrderUnitParams.setWLimitExecCondType(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setWLimitExecCondType(l_orderSpec.getWlimitExecCondType());
                }

                //��������
                l_eqtypeOrderUnitParams.setOrderConditionType(
                    l_orderSpec.getOrderCond());                    
                
                //�i����������"DEFAULT�i�����w��Ȃ��j"�̏ꍇ��null�Z�b�g
                if (l_orderSpec.getOrderCond().equals(WEB3OrderingConditionDef.DEFAULT))
                {                    
                    l_eqtypeOrderUnitParams.setOrderCondOperator(null); // �����������Z�q                    
                    l_eqtypeOrderUnitParams.setStopOrderPrice(null);    // �t�w�l��l                    
                    l_eqtypeOrderUnitParams.setWLimitPrice(null);       // �iW�w�l�j�����w�l
                }
                else
                {
                    // �����������Z�q
                    l_eqtypeOrderUnitParams.setOrderCondOperator(
                        l_orderSpec.getOrderCondOperator());
                    //�t�w�l��l
                    l_eqtypeOrderUnitParams.setStopOrderPrice(
                        l_orderSpec.getStopLimitPriceBasePrice());
                    // �iW�w�l�j�����w�l    �i�i0�FDEFAULT�A1�F�t�w�l�j�̏ꍇ��null�Z�b�g�j
                    if (l_orderSpec.getOrderCond().equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
                    {
                        l_eqtypeOrderUnitParams.setWLimitPrice(null);
                    }
                    else
                    {
                        l_eqtypeOrderUnitParams.setWLimitPrice(
                            l_orderSpec.getWLimitPriceChange());                        
                    }
                } 

                // �ŋ敪�i�������n�j
                l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                
                //�ٍϋ敪
                l_eqtypeOrderUnitParams.setRepaymentType(null);
                
                //�ٍϊ����l
                l_eqtypeOrderUnitParams.setRepaymentNum(null);
                
                //�ٍϋ敪�iSONAR�j
                l_eqtypeOrderUnitParams.setSonarRepaymentType(null);
                
                //���񒍕��̒����`���l��
                l_eqtypeOrderUnitParams.setOrderChanel(
                    l_orderSpec.getOrderChannel());

                //�󒍓���
                if (this.receivedDateTime != null)
                {
                    l_eqtypeOrderUnitParams.setReceivedDateTime(this.receivedDateTime);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setReceivedDateTime(l_processTime);
                }

                //�`�[No ("9" + ���ʃR�[�h�̉��R��)
                String l_strNewOrderRequestNumber_sub =
                    l_newOrderRequestNumber.substring(
                        l_newOrderRequestNumber.length() - 3,
                        l_newOrderRequestNumber.length());
                String l_strVoucherNo =
                    WEB3EquityVoucherNoDef.VoucherNo
                        + l_strNewOrderRequestNumber_sub;
                l_eqtypeOrderUnitParams.setVoucherNo(l_strVoucherNo);

                //���񒍕��̎萔��No
                l_eqtypeOrderUnitParams.setCommTblNo(
                    l_orderSpec.getCommission().getCommissionNo());

                //���񒍕��̎萔��No�}��
                l_eqtypeOrderUnitParams.setCommTblSubNo(
                    l_orderSpec.getCommission().getCommissionRevNo());

                //���҃R�[�h�iSONAR�j
                MainAccount l_mainAccount =
                    l_accMgr.getMainAccount(l_eqtypeOrderUnitParams.account_id);
                MainAccountRow l_accRow =
                    (MainAccountParams) l_mainAccount.getDataSourceObject();
                String l_strSonarTraderCode = l_accRow.getSonarTraderCode();
                l_eqtypeOrderUnitParams.setSonarTraderCode(
                    l_strSonarTraderCode);

                // �����P��
                l_eqtypeOrderUnitParams.setPrice(
                    l_orderSpec.getOrderUnitPrice());

                // ���ʃR�[�h
                l_eqtypeOrderUnitParams.setOrderRequestNumber(
                    l_newOrderRequestNumber);

                // �T�Z��n���
                l_eqtypeOrderUnitParams.setEstimatedPrice(
                    l_orderSpec.getEstimateDeliveryAmount());

                //���n�v���z
                l_eqtypeOrderUnitParams.setCapitalGain(0D);
                
                //���n�v�Ŋz
                l_eqtypeOrderUnitParams.setCapitalGainTax(0D);
                
                // ����R�[�h�iSONAR�j
                WEB3GentradeCommission l_comm =
                    equityNewCashBasedOrderSpec.getCommission();
                l_eqtypeOrderUnitParams.setSonarTradedCode(
                    l_comm.getSonarTradedCode());

                // �s��R�[�h�iSONAR�j
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Market l_market =
                    l_finObjMgr.getMarket(l_eqtypeOrderUnitParams.market_id.longValue());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_eqtypeOrderUnitParams.setSonarMarketCode(
                    l_marketRow.getSonarMarketCode());

                // �萔�����i�R�[�h(10�F��ꊔ�� or 11�F�X������)
                l_eqtypeOrderUnitParams.setCommProductCode(
                    l_orderSpec.getCommissionProductCode());

                //�󔄃t���O
                l_eqtypeOrderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
                
                //���������E����敪
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.INITIAL_VALUE);

                //�����o�H�敪
                String l_strOrderRootDiv = null;
                try
                {
                    OpLoginSecurityService l_securityService =
                        (OpLoginSecurityService)Services.getService(
                            OpLoginSecurityService.class);
                    l_strOrderRootDiv =
                        l_securityService.getSessionProperty(
                            WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                }
                catch (Exception l_exp)
                {
                }
                if (l_strOrderRootDiv != null)
                {
                    l_eqtypeOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setOrderRootDiv(this.orderRootDiv);
                }

                //�����o�H�敪
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingMod.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_eqtypeOrderUnitParams.getProductId(),
                            l_eqtypeOrderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_eqtypeOrderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.error(l_wbe.getMessage(), l_wbe);
                    throw new WEB3BaseRuntimeException(
                        l_wbe.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_wbe.getMessage(), l_wbe);
                }
                l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
                
                //�s�ꂩ��m�F�ς݂̒����P��
                l_eqtypeOrderUnitParams.setConfirmedOrderPrice(null);

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(null);

                //�s�ꂩ��m�F�ς݂̎��s����
                l_eqtypeOrderUnitParams.setConfirmedExecConditionType(null);
                
                //�s�ꂩ��m�F�ς݂̒l�i����
                l_eqtypeOrderUnitParams.setConfirmedPriceConditionType(null);
                
                //���Ϗ����敪
                l_eqtypeOrderUnitParams.setClosingOrderType(null);

                //�����G���[���R�R�[�h
                l_eqtypeOrderUnitParams.setErrorReasonCode(
                    WEB3ErrorReasonCodeDef.NORMAL);

                //���N�G�X�g�^�C�v
                //�����������e.get��������( )��"�t�w�l"�܂���"W�w�l"�̏ꍇ�F�@@0�FDEFAULT
                //��L�ȊO�̏ꍇ�Fnull
                if ((WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderSpec.getOrderCond()))
                    || (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderSpec.getOrderCond())))
                {
                    l_eqtypeOrderUnitParams.setRequestType(
                        WEB3RequestTypeDef.DEFAULT);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setRequestType(null);
                }

                //����Rev.
                l_eqtypeOrderUnitParams.setOrderRev("00");
                
                //�s�ꂩ��m�F�ς݂̒���Rev.
                l_eqtypeOrderUnitParams.setConfirmedOrderRev("00");
                
                //���񒍕��̒����P�ʂh�c
                l_eqtypeOrderUnitParams.setFirstOrderUnitId(
                    l_orderSpec.getFirstOrderUnitId());

                //�����x���t���O
                //0�F�x���Ȃ��i0�FDEFAULT�j
                l_eqtypeOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.FALSE);
                
                //�������ϗ��R�敪
                l_eqtypeOrderUnitParams.setForcedSettleReasonType(null);
                
                //���F��ԋ敪
                l_eqtypeOrderUnitParams.setApproveStatusType(null);
                
                //���F�҃R�[�h
                l_eqtypeOrderUnitParams.setApproverCode(null);
                
                //���F�^�񏳔F����
                l_eqtypeOrderUnitParams.setApproveTimestamp(null);
                
                //�ۏ؋��ێ���
                l_eqtypeOrderUnitParams.setMarginMaintenanceRate(null);
                
                //�Ǐؔ�����
                l_eqtypeOrderUnitParams.setAdditionalMarginDate(null);
                
                //�Ǐ،o�ߓ���
                l_eqtypeOrderUnitParams.setAdditionalMarginAccruedDays(null);
                
                //���������敪
                //0�F�I�[�v���i0�FDEFAULT�j
                l_eqtypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);
            }
            else
            {
                //�����J�z�̏ꍇ

                //get���񒍕��̒����P�ʂh�c
                long l_lngCarryoverOrderUnitId =
                    l_orderSpec.getFirstOrderUnitId().longValue();
                //get�J�z�������P��
                TradingModule l_tradingMod =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                EqtypeOrderUnitRow l_carryoverOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();

                //���X�I�u�W�F�N�g�擾             
                AccountManager l_accMgr = l_finApp.getAccountManager();
                Branch l_branch =
                    l_accMgr.getBranch(l_carryoverOrderUnitRow.getBranchId());

                //���ʃR�[�h�iMMDDXXXXX�j�̔�
                WEB3HostReqOrderNumberManageService l_numberService =
                    (WEB3HostReqOrderNumberManageService) Services.getService(
                        WEB3HostReqOrderNumberManageService.class);
                String l_newOrderRequestNumber =
                    l_numberService.getNewNumber(
                        l_branch.getInstitution().getInstitutionCode(),
                        l_branch.getBranchCode(),
                        ProductTypeEnum.EQUITY);
                
                //����҂h�c
                if (!l_carryoverOrderUnitRow.getTraderIdIsNull())
                {
                    l_eqtypeOrderUnitParams.setTraderId(l_carryoverOrderUnitRow.getTraderId());
                }
                
                //�l�i����
                l_eqtypeOrderUnitParams.setPriceConditionType(
                    l_orderSpec.getPriceConditionType());
                
                //��������
                l_eqtypeOrderUnitParams.setOrderConditionType(
                    l_carryoverOrderUnitRow.getOrderConditionType());
                //�����������Z�q
                l_eqtypeOrderUnitParams.setOrderCondOperator(
                    l_carryoverOrderUnitRow.getOrderCondOperator());
                //�t�w�l��l
	            if (l_carryoverOrderUnitRow.getStopOrderPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setStopOrderPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setStopOrderPrice(
                        l_carryoverOrderUnitRow.getStopOrderPrice());
	            }
                //�iW�w�l�j�����w�l
	            if (l_carryoverOrderUnitRow.getWLimitPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setWLimitPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setWLimitPrice(
	                    l_carryoverOrderUnitRow.getWLimitPrice());
	            }

                //�ŋ敪�i�������n�j
                l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                
                //�ٍϋ敪
                l_eqtypeOrderUnitParams.setRepaymentType(null);
                
                //�ٍϊ����l
                l_eqtypeOrderUnitParams.setRepaymentNum(null);
                
                //�ٍϋ敪�iSONAR�j
                l_eqtypeOrderUnitParams.setSonarRepaymentType(null);
                
                //���񒍕��̒����`���l��
                l_eqtypeOrderUnitParams.setOrderChanel(
                    l_orderSpec.getOrderChannel());

                //�󒍓���
                l_eqtypeOrderUnitParams.setReceivedDateTime(
                    l_carryoverOrderUnitRow.getReceivedDateTime());

                //�`�[No
                String l_strNewOrderRequestNumber_sub =
                    l_newOrderRequestNumber.substring(
                        l_newOrderRequestNumber.length() - 3,
                        l_newOrderRequestNumber.length());
                String l_strVoucherNo =
                    WEB3EquityVoucherNoDef.VoucherNo
                        + l_strNewOrderRequestNumber_sub;
                l_eqtypeOrderUnitParams.setVoucherNo(l_strVoucherNo);

                //���񒍕��̎萔��No
                l_eqtypeOrderUnitParams.setCommTblNo(
                    l_orderSpec.getCommission().getCommissionNo());

                //���񒍕��̎萔��No�}��
                l_eqtypeOrderUnitParams.setCommTblSubNo(
                    l_orderSpec.getCommission().getCommissionRevNo());

                //���҃R�[�h�iSONAR�j
                MainAccount l_mainAccount =
                    l_accMgr.getMainAccount(l_eqtypeOrderUnitParams.account_id);
                MainAccountRow l_accRow =
                    (MainAccountParams) l_mainAccount.getDataSourceObject();
                String l_strSonarTraderCode = l_accRow.getSonarTraderCode();
                l_eqtypeOrderUnitParams.setSonarTraderCode(
                    l_strSonarTraderCode);

                //�����P��
                l_eqtypeOrderUnitParams.setPrice(
                    l_orderSpec.getOrderUnitPrice());

                //���ʃR�[�h
                l_eqtypeOrderUnitParams.setOrderRequestNumber(
                    l_newOrderRequestNumber);

                //�T�Z��n���
                l_eqtypeOrderUnitParams.setEstimatedPrice(
                    l_orderSpec.getEstimateDeliveryAmount());
                
                //���n�v���z
                l_eqtypeOrderUnitParams.setCapitalGain(0D);
                
                //���n�v�Ŋz
                l_eqtypeOrderUnitParams.setCapitalGainTax(0D);
                
                // ����R�[�h�iSONAR�j
                WEB3GentradeCommission l_comm =
                    equityNewCashBasedOrderSpec.getCommission();
                l_eqtypeOrderUnitParams.setSonarTradedCode(
                    l_comm.getSonarTradedCode());

                // �s��R�[�h�iSONAR�j
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Market l_market =
                    l_finObjMgr.getMarket(l_eqtypeOrderUnitParams.market_id.longValue());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_eqtypeOrderUnitParams.setSonarMarketCode(
                    l_marketRow.getSonarMarketCode());

                // �萔�����i�R�[�h
                l_eqtypeOrderUnitParams.setCommProductCode(
                    l_orderSpec.getCommissionProductCode());
                
                //�󔄃t���O
                l_eqtypeOrderUnitParams.setShortSellOrderFlag(
                    WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
                
                //���������E����敪
                l_eqtypeOrderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.INITIAL_VALUE);

                //�����o�H�敪
                l_eqtypeOrderUnitParams.setOrderRootDiv(
                    l_carryoverOrderUnitRow.getOrderRootDiv());

                //�����o�H�敪
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingMod.getProductManager();
                String l_strSubmitOrderRouteDiv = null;
                try
                {
                    EqTypeTradedProduct l_tradedProduct =
                        (EqTypeTradedProduct)l_productManager.getTradedProduct(
                            l_eqtypeOrderUnitParams.getProductId(),
                            l_eqtypeOrderUnitParams.getMarketId());
                    l_strSubmitOrderRouteDiv =
                        l_frontOrderService.getSubmitOrderRouteDiv(
                            l_tradedProduct,
                            l_branch.getInstitution().getInstitutionCode(),
                            l_eqtypeOrderUnitParams.getSonarTradedCode());
                }
                catch (NotFoundException l_nfe)
                {
                    log.error(l_nfe.getMessage(), l_nfe);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_nfe.getMessage(), l_nfe);
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.error(l_wbe.getMessage(), l_wbe);
                    throw new WEB3BaseRuntimeException(
                        l_wbe.getErrorInfo(), 
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_wbe.getMessage(), l_wbe);
                }
                l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
                
                //�s�ꂩ��m�F�ς݂̒����P��
                l_eqtypeOrderUnitParams.setConfirmedOrderPrice(null);

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(null);

                //�s�ꂩ��m�F�ς݂̎��s����
                l_eqtypeOrderUnitParams.setConfirmedExecConditionType(null);

                //�s�ꂩ��m�F�ς݂̒l�i����
                l_eqtypeOrderUnitParams.setConfirmedPriceConditionType(null);
                
                //���Ϗ����敪
                l_eqtypeOrderUnitParams.setClosingOrderType(null);

                //�����G���[���R�R�[�h
                l_eqtypeOrderUnitParams.setErrorReasonCode(
                    WEB3ErrorReasonCodeDef.NORMAL);

                //���N�G�X�g�^�C�v
                if (WEB3OrderingConditionDef.DEFAULT.equals(
                    l_eqtypeOrderUnitParams.getOrderConditionType()))
                {
                    l_eqtypeOrderUnitParams.setRequestType(
                        l_carryoverOrderUnitRow.getRequestType());
                }
                else
                {
                    l_eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
                }

                //����Rev.
                l_eqtypeOrderUnitParams.setOrderRev("00");
                
                //�s�ꂩ��m�F�ς݂̒���Rev.
                l_eqtypeOrderUnitParams.setConfirmedOrderRev("00");
                
                //���񒍕��̒����P�ʂh�c
                l_eqtypeOrderUnitParams.setFirstOrderUnitId(
                    l_lngCarryoverOrderUnitId);
                
                //����������
                l_eqtypeOrderUnitParams.setOrgOrderConditionType(
                    l_carryoverOrderUnitRow.getOrgOrderConditionType());
	            
	            //�������������Z�q
                l_eqtypeOrderUnitParams.setOrgOrderCondOperator(
                    l_carryoverOrderUnitRow.getOrgOrderCondOperator());
	            
	            //���t�w�l��l
	            if (l_carryoverOrderUnitRow.getOrgStopOrderPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setOrgStopOrderPrice(null);
	            }
	            else 
	            {
	                l_eqtypeOrderUnitParams.setOrgStopOrderPrice(
	                    l_carryoverOrderUnitRow.getOrgStopOrderPrice());
	            }
	            //���iW�w�l�j�����w�l
	            if (l_carryoverOrderUnitRow.getOrgWLimitPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setOrgWLimitPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setOrgWLimitPrice(
	                    l_carryoverOrderUnitRow.getOrgWLimitPrice());
	            }
	            //���iW�w�l�j���s����
	            l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(
	                l_carryoverOrderUnitRow.getOrgWLimitExecCondType());
	            //�iW�w�l�j���s����
	            l_eqtypeOrderUnitParams.setWLimitExecCondType(
	                l_carryoverOrderUnitRow.getWLimitExecCondType());
	            //�iW�w�l�j�֑ؑO�w�l
	            if (l_carryoverOrderUnitRow.getWLimitBeforeLimitPriceIsNull())
	            {
	                l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(null);
	            }
	            else
	            {
	                l_eqtypeOrderUnitParams.setWLimitBeforeLimitPrice(
	                    l_carryoverOrderUnitRow.getWLimitBeforeLimitPrice());
	            }
	            //�iW�w�l�j�֑ؑO���s����
	            l_eqtypeOrderUnitParams.setWLimitBeforeExecCondType(
	                l_carryoverOrderUnitRow.getWLimitBeforeExecCondType());

                //�����x���t���O
                l_eqtypeOrderUnitParams.setSubmitOrderDelayFlag(
                    l_carryoverOrderUnitRow.getSubmitOrderDelayFlag());
            }
        }
        catch (NotFoundException l_nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        catch (WEB3BaseException l_wbe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }

    /**
     * (�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �����̒����P��Params.����ID�A<BR>
     * �����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�������G���[���R�R�[�h<BR>
     * �@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�Ɂh0000:����h���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�����̑��̍���<BR>
     * �@@�@@�p�����[�^.��������Params�̊g�����ڂɁA<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A�ԋp����B<BR>
     * @@param l_orderManagerPersistenceType
     * @@param l_orderManagerPersistenceContext
     * @@param l_eqtypeOrderActionParams
     * @@return EqtypeOrderActionParams
     * @@roseuid 4143DB7D0042
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_eqtypeOrderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        // �����P�ʎ擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        EqtypeOrderUnitParams l_orderUnitParams = null;

        try
        {
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_eqtypeOrderActionParams.order_unit_id);
            l_orderUnitParams = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        
        // ����҂h�c�i�����P�ʃe�[�u���D����҂h�c�j
        if (l_orderUnitParams.getTraderIdIsNull())
        {
            l_eqtypeOrderActionParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setTraderId(l_orderUnitParams.getTraderId());
        }
        
        // �l�i�����i�����P�ʃe�[�u���D�l�i�����j
        l_eqtypeOrderActionParams.setPriceConditionType(
            l_orderUnitParams.getPriceConditionType());

        // �������� (�����P�ʃe�[�u��.��������)
        l_eqtypeOrderActionParams.setOrderConditionType(
            l_orderUnitParams.getOrderConditionType()
            );

        // �����������Z�q (�����P�ʃe�[�u��.�����������Z�q)
        l_eqtypeOrderActionParams.setOrderCondOperator(
            l_orderUnitParams.getOrderCondOperator()
            );

        //�t�w�l��l
        if (l_orderUnitParams.getStopOrderPriceIsNull())
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(
                l_orderUnitParams.getStopOrderPrice());
        }

        //�iW�w�l�j�����w�l
        if (l_orderUnitParams.getWLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setWLimitPrice(
            l_orderUnitParams.getWLimitPrice());
        }

        // �����������t (�����P�ʃe�[�u��.�����������t)
        l_eqtypeOrderActionParams.setExpirationDate(
            l_orderUnitParams.getExpirationDate()
            );

        // �T�Z��n��� (���������P�ʃe�[�u��.�T�Z��n���)
        if(l_orderUnitParams.getEstimatedPriceIsNull())
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(
                l_orderUnitParams.getEstimatedPrice()
                );
        }


        // ���������E����敪 (���������P�ʃe�[�u��.���������E����敪)
        l_eqtypeOrderActionParams.setModifyCancelType(
            l_orderUnitParams.getModifyCancelType()
            );

        // �����o�H�敪�i�����P�ʃe�[�u���D�����o�H�敪�j
        l_eqtypeOrderActionParams.setOrderRootDiv(l_orderUnitParams.getOrderRootDiv());

        // ���Ϗ��� (���������P�ʃe�[�u��.���Ϗ���)
        l_eqtypeOrderActionParams.setClosingOrderType(
            l_orderUnitParams.getClosingOrderType()
            );

        // �����G���[���R�R�[�h (���������P�ʃe�[�u��.�����G���[���R�R�[�h)
        l_eqtypeOrderActionParams.setErrorReasonCode(
            WEB3ErrorReasonCodeDef.NORMAL
            );

        // ���N�G�X�g�^�C�v (���������P�ʃe�[�u��.���N�G�X�g�^�C�v)
        l_eqtypeOrderActionParams.setRequestType(
            l_orderUnitParams.getRequestType()
            );
        
        // IP�A�h���X
		OpLoginSecurityService l_securityService = 
			(OpLoginSecurityService) Services.getService(
				OpLoginSecurityService.class);
		try 
		{
			String l_strIpAddress = 
				l_securityService.getSessionProperty(
					WEB3SessionAttributeDef.IP_ADDRESS);
			l_eqtypeOrderActionParams.setIpAddress(l_strIpAddress);
		} 
		catch (IllegalSessionStateException e) 
		{
			l_eqtypeOrderActionParams.setIpAddress(null);
		}
        
           //����������
        l_eqtypeOrderActionParams.setOrgOrderConditionType(
            l_orderUnitParams.getOrgOrderConditionType());
        
        //�������������Z�q
        l_eqtypeOrderActionParams.setOrgOrderCondOperator(
            l_orderUnitParams.getOrgOrderCondOperator());
        
        //���t�w�l��l
        if (l_orderUnitParams.getOrgStopOrderPriceIsNull())
        {
            l_eqtypeOrderActionParams.setOrgStopOrderPrice(null);
        }
        else 
        {
            l_eqtypeOrderActionParams.setOrgStopOrderPrice(
                l_orderUnitParams.getOrgStopOrderPrice());
        }
        
        //���iW�w�l�j�����w�l
        if (l_orderUnitParams.getOrgWLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setOrgWLimitPrice(null);
        }
        else 
        {
            l_eqtypeOrderActionParams.setOrgWLimitPrice(
                l_orderUnitParams.getOrgWLimitPrice());
        }
        
        //���iW�w�l�j���s����
        l_eqtypeOrderActionParams.setOrgWLimitExecCondType(
            l_orderUnitParams.getOrgWLimitExecCondType());
        
        //�iW�w�l�j���s����
        l_eqtypeOrderActionParams.setWLimitExecCondType(
            l_orderUnitParams.getWLimitExecCondType());
        
        //�iW�w�l�j�֑ؑO�w�l
        if (l_orderUnitParams.getWLimitBeforeLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else 
        {
            l_eqtypeOrderActionParams.setWLimitBeforeLimitPrice(
                l_orderUnitParams.getWLimitBeforeLimitPrice());
        }
        
        //�iW�w�l�j�֑ؑO���s����
        l_eqtypeOrderActionParams.setWLimitBeforeExecCondType(
            l_orderUnitParams.getWLimitBeforeExecCondType());

        //�s�ꂩ��m�F�ς݂̎��s����
        //�����P�ʃe�[�u��.�s�ꂩ��m�F�ς݂̎��s�������ҏW
        l_eqtypeOrderActionParams.setConfirmedExecConditionType(
            l_orderUnitParams.getConfirmedExecConditionType());

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderActionParams;
    }

    /**
     * (get�����������e) <BR>
     * �����������e�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * @@return  - �����������e <BR>
     */
    public WEB3EquityNewCashBasedOrderSpec getEquityOrderSpec() 
    {
        return this.equityNewCashBasedOrderSpec;
    }
    
    /**
     * (set�����������e) <BR>
     * �����������e�I�u�W�F�N�g���Z�b�g����B <BR>
     * <BR>
     * @@param l_equityOrderSpec - �����������e <BR>
     * @@roseuid 4010C4A700BD
     */
    public void setEquityOrderSpec(WEB3EquityNewCashBasedOrderSpec l_equityOrderSpec)
    {
        this.equityNewCashBasedOrderSpec = l_equityOrderSpec;
    }


    /**
     *�imutate���\�b�h�̎����j<BR>
     *�������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     *(*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     *�P�j �g�����ڃZ�b�g<BR>
     *�@@this.���ʃR�[�h����p�����[�^.�������Params�̊g�����ڂɒl���Z�b�g����B<BR>
     *�@@this.����������p�����[�^.�������Params�̊g�����ڂɒl���Z�b�g����B<BR>
     *�@@this.��n������p�����[�^.�������Params�̊g�����ڂɒl���Z�b�g����B<BR>
     *�@@this.����������p�����[�^.�������Params�̊g�����ڂɒl���Z�b�g����B<BR>
     *  �Q�j �p�����[�^.�������Params��ԋp����B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType
     * @@param l_orderManagerPersistenceContext
     * @@param l_eqtypeOrderExecutionParams
     * @@return EqtypeOrderExecutionParams
     * @@roseuid 4143DB7D01D2
     */
    public EqtypeOrderExecutionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams)
    {
        return null;
    }

    /**
     * @@param arg0
     * @@param arg1
     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
     * @@roseuid 40A02C9200AB
     */
    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType arg0, 
        Class arg1)
    {
        return null;
    }
    
    /**
     * (get�J�z�������P��)<BR>
     * �v���p�e�B�u�J�z�������P�ʁv���擾����B
     * @@return EqTypeOrderUnit
     * @@roseuid 4112F5220352
     */
    public EqTypeOrderUnit getCarryoverOrderUnit() 
    {
       return this.l_eqtypeOrderUnit;
    }
    
    /**
     * (set�J�z�������P��)<BR>
     * �������A�v���p�e�B�u�J�z�������P�ʁv�ɃZ�b�g����B<BR>
     * @@param �J�z�������P�� - �J�z�������P�ʁB
     * @@roseuid 4112F5220371
     */
    public void setCarryoverOrderUnit(EqTypeOrderUnit l_eqTypeOrderUnit) 
    {
        this.l_eqtypeOrderUnit = l_eqTypeOrderUnit;    
    }
    
    /**
     * (set�����o�H�敪)<BR>
     * �����o�H�敪���Z�b�g����B
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪�B
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }
    
    /**
     * (get�����o�H�敪)<BR>
     * �����o�H�敪���擾����B
     * @@return String
     */
    public String getOrderRootDiv()
    {
        return this.orderRootDiv;
    }
    
    /**
     * (set�󒍓���)<BR>
     * ���������Z�b�g����B
     * @@param l_datReceivedDateTime - (�󒍓���)<BR>
     * �󒍓����B
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime)
    {
        this.receivedDateTime = l_datReceivedDateTime;
    }
    
    /**
     * (get�󒍓���)<BR>
     * �󒍓������擾����B
     * @@return Date
     */
    public Date getReceivedDateTime()
    {
        return this.receivedDateTime;
    }
}
@
