head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ԍσT�[�r�XImpl(WEB3MarginCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
Revesion History : 2004/12/09 �X��   (SRA) �c�Č��Ή�
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2006/11/27 �đo�g(���u) ���f��1010
Revesion History : 2007/01/17 ������@@(���u)���f��No.1107
Revesion History : 2007/06/04 �����q (���u) �d�l�ύX���f��No.1154,���f��No.1161
Revesion History : 2007/06/14 �����q(���u) �d�l�ύX���f��1171
Revesion History : 2007/08/08 ���g(���u) �d�l�ύX���f��1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.define.WEB3MarginBeforeRequestDivDef;
import webbroker3.equity.message.WEB3MarginAfterRepayCalcInfoResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginAttentionUnit;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPAttentionObjection;
import webbroker3.tradingpower.WEB3TPTradingPowerAfterRepayService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * �i�M�p����ԍσT�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����ԍσT�[�r�X�����N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginCloseMarginService
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginServiceImpl.class);

    /**
     * (execute)�B<BR>
     * <BR>
     * �M�p����ԍσT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()���\�b�h�A<BR>
     * submit����()���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40569A440057
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        if (l_request == null)
        {
            log.error(STR_METHOD_NAME + "�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        if (l_request instanceof WEB3MarginCloseMarginConfirmRequest) //validate����
        {
            l_response = validateOrder((WEB3MarginCloseMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginCloseMarginCompleteRequest) //submitOrder����
        {
            l_response = submitOrder((WEB3MarginCloseMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����)�B<BR>
     * <BR>
     * �M�p����ԍϔ����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����ԍσT�[�r�X�jvalidate�����P�v�y��<BR>
     * �u�i�M�p����ԍσT�[�r�X�jvalidate�����Q�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3MarginCloseMarginConfirmResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40569A8E0299
     */
    protected WEB3GenResponse validateOrder(WEB3MarginCloseMarginConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginCloseMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginConfirmResponse l_response = null;
        try
        {
            //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
            l_request.validate();
            //�⏕�������擾����
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
            //�㗝���͎҂̈��҃I�u�W�F�N�g���擾����B
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

            // validate�蓮�������ω\
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            boolean l_blnIsManualForcedSettleFlag = l_request.manualForcedSettleFlag;
            l_orderMgrResValidations.validateManualForcedSettlePossible(l_blnIsManualForcedSettleFlag, l_trader);

            //�M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�𐶐�����B
            WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_request);

            //���s�������擾����B
            EqTypeExecutionConditionType l_conditionType = l_adapter.getExecCondType();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3EquityBizLogicProvider l_provide = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
                        
            //�����I�u�W�F�N�g���擾����B
            WEB3EquityContract l_contract = l_adapter.getContract();
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

            //get�i�v�w�l�j���s����
            //�i�v�w�l�j���s�����F
            //�E���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
            //�@@�E���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A
            //�@@�@@�@@�M�p����ԍσ��N�G�X�g.get�i�v�w�l�j���s����( )���Z�b�g�B
            EqTypeExecutionConditionType l_eqyTypeExecutionConditonType = null;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_eqyTypeExecutionConditonType = l_adapter.getWLimitExecCondType();
            }

            //�s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_contract.getMarketId());
            //������ԃR���e�L�X�g�Ɏs��R�[�h���Z�b�g����B
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //���ό����G���g�����쐬����B
            EqTypeSettleContractOrderEntry[] l_orderEntry =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_adapter);

            //�����������F�@@���N�G�X�g.�����L�������@@���h��������h�����̏ꍇ�Anull���Z�b�g�����B
            Date l_datExpirationDate = null;
            
            Long l_lngOrderUnitId = null;
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
            {
                l_datExpirationDate = null;
                l_lngOrderUnitId = null;
            }
            else
            {
                // �M�p����ԍσ��N�G�X�g�A�_�v�^.get�����L������() 
                l_datExpirationDate = l_adapter.getExpirationDate();
                l_lngOrderUnitId = new Long(0);
            }
            //�t�w�l��l
            double l_dblStopOrderPrice = 0D;
            //�����������Z�q�F
            String l_strOrderCondOperator = null;
            //�iW�w�l�j�����w�l�F
            double l_dblWLimitPrice = 0D;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                if (l_request.wLimitPrice != null)
                {
                    l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
                }
            }
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                l_dblWLimitPrice = 0D;
            }
            else
            {
                l_strOrderCondOperator = "0";
                l_dblStopOrderPrice = 0D;
                l_dblWLimitPrice = 0D;
            }

            //�ԍϒ������e���쐬����B
            //���񒍕��̒����P��ID�F
            //�E���N�G�X�g.���������敪���h��������h�̏ꍇ�́Anull���Z�b�g�B
            //�E���N�G�X�g.���������敪���h�o����܂Œ����h�̏ꍇ�́A0���Z�b�g�B
            WEB3MarginSettleContractOrderSpec l_orderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_orderEntry,
                    l_adapter.getPrice(),
                    l_conditionType,
                    l_datExpirationDate,
                    l_contractRow.getTaxType(),
                    l_request.priceCondType,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_request.closingOrder,
                    l_lngOrderUnitId,
                    l_eqyTypeExecutionConditonType);

            //�ԍϒ����̔����R�����s���B
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                this.validateSettleContractOrder(l_subAccount, l_orderSpec, l_adapter);

            //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
            //�����R�����ʁF�@@validate�ԍϒ���()�̖߂�l
            //�،���ЁF�@@�⏕����.getInstitution()
            //�����R�[�h�F�@@����.getProduct().getProductCode()
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
            l_orderManager.throwOrderValidationResultErrorInfo(
                l_eqTypeNewOrderValidationResult,
                l_subAccount.getInstitution(),
                l_eqtypeProduct.getProductCode());

            //���������擾����B
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //�萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission =
                l_provide.createCommission(
                    l_subAccount,
                    l_market.getMarketCode(),
                    l_orderBizDate,
                    this.getLoginChannel(),
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    OrderCategEnum.CLOSE_MARGIN);

            //�w�l���ǂ������萔���t���O�ɃZ�b�g����B
            l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());

            //��������I�u�W�F�N�g���擾����B
            WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();

            //�T�Z���ϑ��v������擾����B
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult =
                this.getEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_adapter.getPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_orderEntry,
                    l_orderSpec.getTotalQuantity(),
                    null,
                    0D,
                    0D,
                    false,
                    l_adapter);

            // �M�p�ԍύX�V�C���^�Z�v�^����
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            String l_strOrderRootDiv =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_orderSpec,
                    l_commission,
                    l_profitAndLossCalcResult,
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    this.getLoginChannel(),
                    l_strOrderRootDiv);
            
            //�ԍό�]�͕\������̃��N�G�X�g�̏ꍇ
            if (l_request.requestFromType != null &&
                WEB3MarginBeforeRequestDivDef.AFTER_REPAY.equals(l_request.requestFromType))
            {
                WEB3MarginAfterRepayCalcInfoResponse l_afterRepayResponse =
                    new WEB3MarginAfterRepayCalcInfoResponse(l_request);
                l_afterRepayResponse.interceptor = l_updateInterceptor;
                l_afterRepayResponse.orderSpec = l_orderSpec;
                log.exiting(STR_METHOD_NAME);
                return l_afterRepayResponse;
            }
            
            //���X�|���X�f�[�^�𐶐�����B
            l_response = (WEB3MarginCloseMarginConfirmResponse)l_request.createResponse();
            
            WEB3MarginContractUnit[] l_contractUnits =
                this.createMarginContractUnitList(
                    l_orderEntry,
                    l_profitAndLossCalcResult.getCalcUnitPrice(),
                    l_adapter);
            
            //�M�p����萔�������쐬
            WEB3MarginCommissionInfoUnit l_commissionInfoUnit = new WEB3MarginCommissionInfoUnit();
            //�M�p����萔�����.�萔���R�[�X  ���@@�萔��.get�萔���R�[�X�R�[�h
            //�M�p����萔�����.�萔��       ���@@�萔��.get�萔�����z( )
            //�M�p����萔�����.�萔�������    ���@@�����v�Z�T�[�r�X.calc�����( )
            l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
            l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
            l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(
                l_provide.calcSalesTax(l_commission.getCommission(), new Timestamp(l_orderBizDate.getTime()), l_subAccount));
            
            //�Ǌԋ߂̎s��R�[�h���擾����B
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_contractRow.getRepaymentType());

            //�T�Z���ϑ��v������擾����B
            double l_dblLossAmount = l_profitAndLossCalcResult.getEstimatedRealizedProfitAndLossAmount();
            //�v�Z�P�����擾����B
            double l_dblUnitPrice = l_profitAndLossCalcResult.getCalcUnitPrice();

            //�V�K����ID���擾����B
            long l_lngOrderId = l_orderManager.createNewOrderId();
           
            // create�ԍώ����ӕ���
            WEB3TPAttentionObjection l_attentionObjection =
                this.createCloseMarginAttentionWording(
                    l_subAccount,
	                l_updateInterceptor,
	                l_orderSpec);
            
            //set�P��(�M�p����ԍσ��N�G�X�g�A�_�v�^, WEB3GenResponse)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�M�p����ԍσ��N�G�X�g�A�_�v�^�F�@@�������������I�u�W�F�N�g
            //���X�|���X�F�����������X�|���X
            setPrice(l_adapter, l_response);

            //���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B��ݒ肷��B
            //���X�|���X.�m�F���������F�@@������ԊǗ�.get������(void)�̖߂�l
            l_response.checkDate = WEB3DateUtility.toDay(l_orderBizDate);
            //���X�|���X.�T�Z��n����F�@@�T�Z���ϑ��v����v�Z����.get�T�Z���ϑ��v���()�̖߂�l
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblLossAmount);
            //���X�|���X.����I���x���s��R�[�h�ꗗ�F�@@������ԊǗ�.get�s��ǌx���s��()�̖߂�l�z��
            l_response.messageSuspension = l_strCloseMarket;
            //���X�|���X.�������׈ꗗ�F�@@(*��L�ŕҏW�����������׃I�u�W�F�N�g�̔z��)
            l_response.contractUnits = l_contractUnits;
            //���X�|���X.�萔�����F�@@(*��L�ŕҏW�����M�p�萔�����I�u�W�F�N�g�j
            l_response.commissionInfo = l_commissionInfoUnit;
            //���X�|���X.�m�F���P���F�@@�T�Z���ϑ��v����v�Z����.get�v�Z�P��()�̖߂�l
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
            //���X�|���X.����ID�F�@@�g�����������}�l�[�W��.createNewOrderId( )�̖߂�l
            l_response.orderId = "" + l_lngOrderId;
            //���X�|���X.�C���T�C�_�[�x���\���t���O�F�@@�g�����������}�l�[�W���[.is�C���T�C�_�[�x���\��()�̖߂�l
			l_response.insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_contractRow.getProductId());

            WEB3MarginCloseMarginAttentionUnit l_attentionUnit = new WEB3MarginCloseMarginAttentionUnit();
			// ���X�|���X.�ԍώ����ӕ���.���ӕ����\���敪
            if (l_attentionObjection == null)
            {
                //this.create�ԍώ����ӕ���()�̖߂�l == null�̏ꍇ�Anull���Z�b�g(�A��������)
                l_attentionUnit.attentionDispDiv = null;
            }
            else
            {
                //this.create�ԍώ����ӕ���()�̖߂�l != null�̏ꍇ�A�߂�l���Z�b�g
                l_attentionUnit.attentionDispDiv = l_attentionObjection.attentionObjectionType;
            }

            // ���X�|���X.�ԍώ����ӕ���.���������z
            if (l_attentionObjection == null)
            {
                //this.create�ԍώ����ӕ���()�̖߂�l == null�̏ꍇ�Anull���Z�b�g(�A��������)
                l_attentionUnit.payClaimAmount = null;
            }
            else if (l_attentionUnit.attentionDispDiv.equals(WEB3TPAttentionObjectionTypeDef.NO_ATTENTION))
            {
                // ���ӕ����\���Ȃ��̏ꍇ��null���Z�b�g
	            l_attentionUnit.payClaimAmount = null;
            }
            else
            {
                // �ȊO�Acreate�ԍώ����ӕ���()�̖߂�l.���������z
	            l_attentionUnit.payClaimAmount = WEB3StringTypeUtility.formatNumber(l_attentionObjection.demandAmount);
            }

            // ���X�|���X.�ԍώ����ӕ��� = �v���p�e�B�Z�b�g�����ԍώ����ӕ���
            l_response.closeMarginAttention = l_attentionUnit;

            //���X�|���X.�����L������
            l_response.expirationDate = l_adapter.getExpirationDate();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)�B<BR>
     * <BR>
     * �M�p����ԍϒ����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p����ԍσT�[�r�X�jsubmit�����P�v�y��<BR>
     * �u�i�M�p����ԍσT�[�r�X�jsubmit�����Q�v�Q�ƁB<BR>
     * @@param l_requestOrder - ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3MarginCloseMarginCompleteResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40569ADB028A
     */
    protected WEB3MarginCloseMarginCompleteResponse submitOrder(WEB3MarginCloseMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginCloseMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginCompleteResponse l_response = null;
        try
        {
            //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
            l_request.validate();
            //�⏕�������擾����
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
            //�㗝���͎҂̈��҃I�u�W�F�N�g���擾����B
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

            //validate�蓮�������ω\
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            boolean l_blnIsManualForcedSettleFlag = l_request.manualForcedSettleFlag;
            l_orderMgrResValidations.validateManualForcedSettlePossible(l_blnIsManualForcedSettleFlag, l_trader);

            //�M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�𐶐�����B
            WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_request);

            //���s�������擾����B
            EqTypeExecutionConditionType l_conditionType = l_adapter.getExecCondType();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3EquityBizLogicProvider l_provide = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
           
            //�����I�u�W�F�N�g���擾����B
            WEB3EquityContract l_contract = l_adapter.getContract();
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

            //get�i�v�w�l�j���s����
            //�i�v�w�l�j���s�����F
            //�E���N�G�X�g.���������敪���i�h�w��Ȃ��h�A�h�t�w�l�h�j�̏ꍇ�́Anull���Z�b�g�B
            //�@@�E���N�G�X�g.���������敪���hW�w�l�h�̏ꍇ�́A
            //�@@�@@�@@�M�p����ԍσ��N�G�X�g.get�i�v�w�l�j���s����( )���Z�b�g�B
            EqTypeExecutionConditionType l_eqyTypeExecutionConditonType = null;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_eqyTypeExecutionConditonType = l_adapter.getWLimitExecCondType();
            }

            //�s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_contract.getMarketId());
            //������ԃR���e�L�X�g�Ɏs��R�[�h���Z�b�g����B
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //���ό����G���g�����쐬����B
            EqTypeSettleContractOrderEntry[] l_orderEntry =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_adapter);

            //�����������F�@@���N�G�X�g.�����L�������@@���h��������h�����̏ꍇ�Anull���Z�b�g�����B
            Date l_datExpirationDate = null;
            Long l_lngOrderUnitId = null;
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
            {
                l_datExpirationDate = null;
                l_lngOrderUnitId = null;
            }
            else
            {
                // �M�p����ԍσ��N�G�X�g�A�_�v�^.get�����L������() 
                l_datExpirationDate = l_adapter.getExpirationDate();
                l_lngOrderUnitId = new Long(0);
            }
            //�t�w�l��l
            double l_dblStopOrderPrice = 0D;
            //�����������Z�q�F
            String l_strOrderCondOperator = null;
            //�iW�w�l�j�����w�l�F
            double l_dblWLimitPrice = 0D;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                if (l_request.wLimitPrice != null)
                {
                    l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
                }
            }
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                l_dblWLimitPrice = 0D;
            }
            else
            {
                l_strOrderCondOperator = "0";
                l_dblStopOrderPrice = 0D;
                l_dblWLimitPrice = 0D;
            }
            //�ԍϒ������e���쐬����B
            //���񒍕��̒����P��ID�F
            //�E���N�G�X�g.���������敪���h��������h�̏ꍇ�́Anull���Z�b�g�B
            //�E���N�G�X�g.���������敪���h�o����܂Œ����h�̏ꍇ�́A0���Z�b�g�B
            WEB3MarginSettleContractOrderSpec l_orderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_orderEntry,
                    l_adapter.getPrice(),
                    l_conditionType,
                    l_datExpirationDate,
                    l_contractRow.getTaxType(),
                    l_request.priceCondType,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_request.closingOrder,
                    l_lngOrderUnitId,
                    l_eqyTypeExecutionConditonType);

            //�ԍϒ����̔����R�����s���B
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                this.validateSettleContractOrder(l_subAccount, l_orderSpec, l_adapter);

            //throw�����R�����ʃG���[���(OrderValidationResult, �،����, String)
            //�����R�����ʁF�@@validate�ԍϒ���()�̖߂�l
            //�،���ЁF�@@�⏕����.getInstitution()
            //�����R�[�h�F�@@����.getProduct().getProductCode()
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
            l_orderManager.throwOrderValidationResultErrorInfo(
                l_eqTypeNewOrderValidationResult,
                l_subAccount.getInstitution(),
                l_eqtypeProduct.getProductCode());

            //���������擾����B
            if (l_request.checkDate == null)
            {
                l_request.checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

            //�萔���I�u�W�F�N�g�𐶐�����B
            WEB3GentradeCommission l_commission =
                l_provide.createCommission(
                    l_subAccount,
                    l_market.getMarketCode(),
                    l_orderBizDate,
                    this.getLoginChannel(),
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    OrderCategEnum.CLOSE_MARGIN);

            //�w�l���ǂ������萔���t���O�ɃZ�b�g����B
            l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());

            //��������I�u�W�F�N�g���擾����B
            WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            
            //�T�Z���ϑ��v������擾����B
            if (l_request.checkPrice == null)
            {
                l_request.checkPrice =
                    WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            }
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult =
                this.getEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    Double.parseDouble(l_request.checkPrice),
                    l_subAccount,
                    l_tradedProduct,
                    l_orderEntry,
                    l_orderSpec.getTotalQuantity(),
                    null,
                    0D,
                    0D,
                    false,
                    l_adapter);

            //�ԍϒ����o�^�������s���B
            if (l_request.orderId == null)
            {
                l_request.orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            this.submitSettleContractOrder(
                l_subAccount,
                l_orderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                l_commission,
                l_profitAndLossCalcResult,
                l_adapter);

            //�]�͍Čv�Z���s���B
            this.execReCalcTradingPower(l_subAccount);

            l_response = (WEB3MarginCloseMarginCompleteResponse)l_request.createResponse();
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.orderId;
            l_response.insiderWarningFlag =
                l_orderManager.isInsiderMessageSuspension(
                    l_subAccount,
                    l_contract.getProduct().getProductId()); 
            //���X�|���X.�����L������
            l_response.expirationDate = l_adapter.getExpirationDate();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^.create(�����̃��N�G�X�g)���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3MarginCloseMarginRequestAdapter
     */
    protected WEB3MarginCloseMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor =
            WEB3MarginCloseMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ���ό����G���g�����쐬����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.create���ό����G���g��()���R�[������B<BR>
     * <BR>
     * [create���ό����G���g��()�Ɏw�肷�����]<BR>
     * �����P��ID�F�@@0(�V�K����)<BR>
     * ���������F�@@�p�����[�^.���N�G�X�g�A�_�v�^.get��������()�߂�l<BR>
     * ���ό������׈ꗗ[]�F�@@�p�����[�^.���ό������׈ꗗ[]<BR>
     * @@param l_closeMarginContractUnits - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
            l_orderManager.createClosingContractEntry(
                0L,
                l_requestAdaptor.getOrderQuantity(),
                l_closeMarginContractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }
    
    /**
     * (get�T�Z���ϑ��v���)<BR>
     * �T�Z���ϑ��v������擾����B<BR>
     * <BR>
     * �g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[������B<BR>
     * <BR>
     * [calc�T�Z���ϑ��v���()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�萔���F�@@�p�����[�^�̓�����<BR>
     * �@@�w�l�F�@@�p�����[�^�̓�����<BR>
     * �@@�⏕�����F�@@�p�����[�^�̓�����<BR>
     * �@@��������F�@@�p�����[�^�̓�����<BR>
     * �@@���ό����G���g���F �@@�p�����[�^�̓�����<BR>
     * �@@���ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�����P�ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�����萔�ʁF�@@�p�����[�^�̓�����<BR>
     * �@@������P���F�@@�p�����[�^�̓�����<BR>
     * �@@isSkip���z�`�F�b�N�F�@@�p�����[�^�̓�����<BR>
     * �@@�����F�@@nul<BR>
     * @@param l_genCommission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l�B<BR>
     * ���s�̏ꍇ��0���Z�b�g�B
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_equityTradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B
     * @@param l_settleContractOrderEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_dblQuantity - (����)<BR>
     * ���ʁB
     * @@param l_orderUnit - (�����P��)<BR>
     * �������^���Ώہ^������Ώے����̒����P�ʃI�u�W�F�N�g<BR>
     * �i�V�K�̒����o�^����null���Z�b�g�j
     * @@param l_dblNowExecQuantity - (�����萔��)<BR>
     * �����萔��<BR>
     * �i���^������̏ꍇ�ɕҏW�j
     * @@param l_dblNowExecPrice - (������P��)<BR>
     * ������P��<BR>
     * �i���^������̏ꍇ�ɕҏW�j
     * @@param l_isSkipAmountRange - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g<BR>
     * @@return WEB3EquityRealizedProfitAndLossPrice
     * @@throws WEB3BaseException
     */
    protected WEB3EquityRealizedProfitAndLossPrice getEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_orderUnit,
        double l_dblNowExecQuantity,
        double l_dblNowExecPrice,
        boolean l_isSkipAmountRange,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getEstimatedRealizedProfitAndLossAmount(WEB3GentradeCommission, " +
            "double, SubAccount, WEB3EquityTradedProduct, " +
            "EqTypeSettleContractOrderEntry[], double, EqTypeOrderUnit, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice =
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntrys,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_isSkipAmountRange,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_realizedProfitAndLossPrice;
    }
    
    /**
     * (create�������׈ꗗ)<BR>
     * ���ό����G���g�����M�p����������ׂ̈ꗗ��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���ό����G���g���̗v�f�����A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�Q�|�P�j�@@�����|�W�V�����}�l�[�W��.get����()�ɂ�<BR>
     * �@@�@@�������擾����B<BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@����ID�F�@@�����Ώۂ̗v�f.getContractId()<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�M�p����������׃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@ID�F�@@����.����ID<BR>
     * �@@�@@���� = ����.����<BR>
     * �@@�@@���P�� = ����.���P��<BR>
     * �@@�@@������ = ����.������<BR>
     * �@@�@@����� = ����.get�����(��������(*1))<BR>
     * �@@�@@�]�����v =<BR>
     * �@@�@@�@@����.get�]�����v�i�������o��l���j(�v�Z�P��(*2), ��������)<BR>
     * �@@�@@�������� = ��������<BR>
     * �@@�@@���o������ = null<BR>
     * �@@�@@���Ϗ��� = index + 1<BR>
     * <BR>
     * �@@�@@(*1)���������E�E�E�����Ώۂ̗v�f.getQuantity()<BR>
     * <BR>
     * �@@�@@(*2)�v�Z�P��<BR>
     * �@@�@@�@@�@@���s�����i�����̃��N�G�X�g�A�_�v�^.get�P��()==0�j�̏ꍇ�A�����̌v�Z�P���i�������j�B<BR>
     * �@@�@@�@@�@@�w�l�����̏ꍇ�A�������擾���ăZ�b�g�B<BR>
     * �@@�@@�@@�@@�@@�i�g���v���_�N�g�}�l�[�W��.get����(�������)�B<BR>
     * �@@�@@�@@�@@�@@�@@��������́A ����.getTradedProduct()�Ŏ擾����B�j<BR>
     * <BR>
     * �@@�Q�|�S�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_settleContractOrderEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_dblUnitPrice - (�v�Z�P��)<BR>
     * �v�Z�P���B
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblUnitPrice,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(EqTypeSettleContractOrderEntry[], double, WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        int l_intOrderEntryLength = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intOrderEntryLength = l_settleContractOrderEntrys.length;
        }
        
        double l_dblCalcUnitPrice = 0.0D;
        for (int i = 0; i < l_intOrderEntryLength; i++)
        {
            WEB3EquityContract l_equityContract = null;
            try
            {
                l_equityContract =
                    (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
            l_contractUnit.id = Long.toString(l_equityContract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_equityContract.getOpenDate());
            double l_dblContractPrice = l_equityContract.getContractPrice();
            if (Double.isNaN(l_dblContractPrice))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
            double l_dblContractQuantity = l_equityContract.getQuantity();
            if (Double.isNaN(l_dblContractQuantity))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            double l_dblQuantity = l_settleContractOrderEntrys[i].getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(
                l_equityContract.getContractAmount(l_dblQuantity));
                
            if (l_dblCalcUnitPrice == 0.0D)
            {
	            if (l_requestAdaptor.getPrice() == 0.0D)
	            {
	                l_dblCalcUnitPrice = l_dblUnitPrice;
	            }
	            else
	            {
	                l_dblCalcUnitPrice =
	                    l_productManager.getCurrentPrice(
	                        (EqTypeTradedProduct)l_equityContract.getTradedProduct());
	            }
            }
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_equityContract.getAppraisalProfitOrLossExpenses(
                        l_dblCalcUnitPrice, l_dblQuantity));

            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            l_contractUnit.partContQuantity = null;
            l_contractUnit.settlePriority = Integer.toString(i + 1);
            
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginContractUnit[] l_contractUnits =
            new WEB3MarginContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (validate�ԍϒ���)<BR>
     * �M�p����ԍϒ��������R�����s���B<BR>
     * <BR>
     * �g�����������}�l�[�W��.validate�ԍϒ���()<BR>
     * ���R�[������B<BR>
     * <BR>
     * [validate�ԍϒ���()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�M�p�ԍϒ������e�F�@@�p�����[�^.�M�p�ԍϒ������e<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected EqTypeNewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        EqTypeSettleContractOrderSpec l_orderSpec,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSettleContractOrder(WEB3GentradeSubAccount, EqTypeSettleContractOrderSpec, WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeNewOrderValidationResult l_result =
            l_orderManager.validateSettleContractOrder(l_subAccount, l_orderSpec);

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (submit�ԍϒ���)<BR>
     * �M�p����ԍϒ�����o�^����B<BR>
     * <BR>
     * �P�j�@@�M�p�ԍύX�V�C���^�Z�v�^�𐶐����A<BR>
     * �@@�g�����������}�l�[�W����ThreadLocal�ɃZ�b�g����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�M�p�ԍϒ������e�F�@@�M�p�ԍϒ������e<BR>
     * �@@�@@�萔���F�@@�萔��<BR>
     * �@@�@@�T�Z���ϑ��v����v�Z���ʁF�@@�v�Z����<BR>
     * �@@�@@�ٍϋ敪�F�@@����(*1).�ٍϋ敪<BR>
     * �@@�@@�ٍϊ����l�F�@@����.�ٍϊ����l<BR>
     * �@@�@@���񒍕��̒����`���l���F�@@this.get���O�C���`���l��()<BR>
     * �@@�@@�����o�H�敪�F�@@�Z�b�V��������擾<BR>
     * �@@�@@�蓮�������σt���O�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�蓮�������σt���O<BR>
     * <BR>
     * �@@�@@(*1)�ԍσ��N�G�X�g�A�_�v�^.get����()�ɂĎ擾�B<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.submitSettleContractOrder(<BR>
     * �⏕����, �M�p�ԍϒ������e, ����ID, ����p�X���[�h, true�i�������R�����X�L�b�v����j)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_orderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_commission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B
     * @@param l_profitAndLossCalcResult - (�v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g�B
     * @@param l_requestAdaptor - (�ԍσ��N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void submitSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3GentradeCommission l_commission,
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSettleContractOrder(WEB3GentradeSubAccount, WEB3MarginSettleContractOrderSpec, long, String, WEB3GentradeCommission, WEB3EquityRealizedProfitAndLossPrice, WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_orderRootDiv = l_opLoginSec.getSessionProperty(
            WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityContract l_contract = l_requestAdaptor.getContract();
        EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();
        // �蓮�������σt���O�F�@@�����̃��N�G�X�g�A�_�v�^.���N�G�X�g.�蓮�������σt���O
        WEB3MarginCloseMarginCompleteRequest l_closeMarginComfireRequest =
            (WEB3MarginCloseMarginCompleteRequest)l_requestAdaptor.request;
        boolean l_blnIsManualForcedSettleFlag = l_closeMarginComfireRequest.manualForcedSettleFlag;
        WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor =
            new WEB3MarginCloseMarginUpdateInterceptor(
                l_orderSpec,
                l_commission,
                l_profitAndLossCalcResult,
                l_contractRow.getRepaymentType(),
                l_contractRow.getRepaymentNum(),
                this.getLoginChannel(),
                l_orderRootDiv,
                l_blnIsManualForcedSettleFlag);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�ԍώ����ӕ���)<BR>
     * ���X�|���X�ɐݒ肷��A�ԍώ����ӕ������擾����B<BR>
     * <BR>
     * �ԍό�]�̓T�[�r�X.create�ԍώ����ӕ���()���R�[�����A <BR>
     * �߂�l��ԋp����B<BR>
     * <BR>
     * [�ԍώ����ӕ���()�Ɏw�肷�����]<BR> 
     * �⏕�����F�@@�����̕⏕����  <BR>
     * �������e�C���^�Z�v�^�F�@@�����̐M�p�ԍύX�V�C���^�Z�v�^<BR>  
     * �������e�F�@@�����̕ԍϒ������e<BR>
     * @@param�@@l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_updateInterceptor - (�M�p�ԍύX�V�C���^�Z�v�^)<BR>
     * �M�p�ԍύX�V�C���^�Z�v�^
     * @@param l_orderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e
     * @@return WEB3TPAttentionObjection
     * @@throws WEB3BaseException
     */
    protected WEB3TPAttentionObjection createCloseMarginAttentionWording(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor,
        WEB3MarginSettleContractOrderSpec l_orderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginAttentionWording(WEB3GentradeSubAccount l_subAccount, WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor, WEB3MarginSettleContractOrderSpec l_orderSpec)";
        log.entering(STR_METHOD_NAME);

        // �ԍό�]�̓T�[�r�X�擾
        WEB3TPTradingPowerAfterRepayService l_tpAfterRepay =
            (WEB3TPTradingPowerAfterRepayService)Services.getService(WEB3TPTradingPowerAfterRepayService.class);

        // create�ԍώ����ӕ���
        WEB3TPAttentionObjection l_tpAttObj = 
            l_tpAfterRepay.createWEB3TPAttentionObjection(
                l_subAccount,
                l_updateInterceptor,
                l_orderSpec);
            
        log.exiting(STR_METHOD_NAME);
        return l_tpAttObj;
    }

    /**
     * (exec�]�͍Čv�Z)<BR>
     * �]�͍Čv�Z���s���B<BR>
     * <BR>
     * �����͈ȉ��̒ʂ�w�肷��B <BR>
     * <BR>
     * �⏕�����F�@@get�⏕����()�̖߂�l<BR>
     * <BR>
     * @@param�@@l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    protected void execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        //����]�̓T�[�r�X.�]�͍Čv�Z�i�⏕�����j
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        l_tradingPowerService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�P��)<BR>
     * ���������Ƀ��^�[������B<BR>
     * <BR>
     * @@param l_requestAdapter - (�M�p����ԍσ��N�G�X�g�A�_�v�^)<BR>
     * �M�p����ԍσ��N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3MarginCloseMarginRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        
    }
}
@
