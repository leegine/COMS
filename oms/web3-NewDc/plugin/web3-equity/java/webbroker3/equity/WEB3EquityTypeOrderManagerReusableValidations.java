head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTypeOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������R���ʃ`�F�b�N(WEB3EquityTypeOrderManagerReusableValidations.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/16 �����@@���F(SRA) �V�K�쐬
                 : 2006/08/29 �����F(���u) ���f�� 970
                 : 2006/11/01 �юu�� (���u) �d�l�ύX���f��951,990,995,1007,1027,1034
                 : 2006/11/28 ������ (���u) �d�l�ύX���f��1073�i���y���ʁz�d�l�ύX�Ǘ��i�c�a���C�A�E�g�jNo.447���Q�Ɓj
                 : 2007/07/27 �]���@@���u(SRA) �d�l�ύX���f�� 1181,1182,1183,1185,1186,1187
Revesion History : 2007/04/27 �Ӑ�(���u) ���f�� 1142
Revesion History : 2007/06/04 �����q (���u) �d�l�ύX���f��No.1149,���f��No.1155,���f��No.1161
Revesion History : 2007/06/15 ���g (���u) ���f��No.1175
Revesion History : 2007/07/26 �����q (���u) �d�l�ύX���f��No.1190
Revesion History : 2007/08/30 �đo�g (���u) �d�l�ύX���f��No.1193
Revesion History : 2007/12/10 ��іQ (���u) �d�l�ύX���f��No.1239
Revesion History : 2009/09/10 �И��� (���u) �d�l�ύX���f��No.1331 �v�Z����No.022
Revesion History : 2009/09/21 �Ԑi (���u) ���f�� No.1333 No.1339 No.1343 No.1344 No.1348
                                          �v�Z���� No.023 No.024 No.025 No.026 No.028 No.029 No.030
                                                  No.031 No.032 No.036 No.037 No.038
Revesion History : 2009/10/14 �����F (���u) ���f�� No.1352 �v�Z����No.041
Revesion History : 2009/10/20 �����F (���u) ���f�� No.1353
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EquityProductTypeOrderManagerReusableValidations;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3InsiderRegistDivDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MarginGenProductTypeDef;
import webbroker3.common.define.WEB3MarginSysProductTypeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.common.define.WEB3MultiChangeabilityDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.common.define.WEB3QualifiedInstInvestorDivDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ShortMarginRestrainDivDef;
import webbroker3.common.define.WEB3ShortSellingCountMethodDivDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TriggerorderWlimitorderCheckOrderCondPriceDef;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.ShortSellingRestraintTimeRow;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchListmarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * �i���������R���ʃ`�F�b�N�j�B<BR>
 * <BR>
 * �����R���̌X�̃`�F�b�N�������B<BR>
 * �iEquityTypeOrderManagerReusableValidations�̊g���N���X�j
 * @@version 1.0
 */
public class WEB3EquityTypeOrderManagerReusableValidations
    extends EquityProductTypeOrderManagerReusableValidations
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityTypeOrderManagerReusableValidations.class);

    /**
     * �i�G���[�^�O�j�B
     */
    public static String ORDER_ERROR_TAG = "WEB3ErrorReasonCode";  
    
    /**
     * �i���������R���ʃ`�F�b�N�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
    public WEB3EquityTypeOrderManagerReusableValidations()
    {

    }

    /**
     * �i�X�[�p�[�N���X�Ɏ��g�̃C���X�^���X��o�^����B�j�B<BR>
     * <BR>
     * �i�v���O�C�����������ɃR�[�������j<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---
     */
    public void register()
    {
        super.setInstance(this);
    }

    /**
     * �ivalidate�s��R�[�h�j�B<BR>
     * <BR>
     * �s��R�[�h�̃`�F�b�N�����{����B<BR>
     * ���݂���ꍇ�͎s��I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateMarket�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j�ɂĊY������<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�s�ꂪ�擾�ł��Ȃ��ꍇ�́A�Y���s��Ȃ��Ɣ��f���A��O��<BR>
     * �@@�@@�@@�X���[����B<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00003<BR>
     * <BR>
     * �Q�j�@@�s��.�����~ == �h��~���h�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@-��~��-<br>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00013<BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g��ԋp����B
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@return Market �s��
     * @@throws WEB3BaseException
     */
    public Market validateMarket(
        String l_strMarketCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarket(String, String)";
        log.entering(STR_METHOD_NAME);

        Market l_market;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j�ɂĊY������
        //�s��I�u�W�F�N�g�𐶐�����B
        //�s�ꂪ�擾�ł��Ȃ��ꍇ�́A�Y���s��Ȃ��Ɣ��f���A��O���X���[����B
        try
        {
            l_market =
                (WEB3GentradeMarket) l_gentradeFinObjectManager.getMarket(
                    l_strInstitutionCode,
                    l_strMarketCode);
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "not find : market_code = " + l_strMarketCode + " , institution_code = " + l_strInstitutionCode);
        }

        MarketParams l_params = (MarketParams) l_market.getDataSourceObject();

        if (l_params.getSuspension().equals(WEB3SuspensionDef.SUSPENSION))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01747,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * �ivalidate��������j�B<BR>
     * <BR>
     * ��������̃`�F�b�N���s���B<BR>
     * �ivalidateTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@this.validate�������(��������, �s��)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�O���،������J�݃`�F�b�N<BR>
     * �@@�@@�@@�������.���敪���h�O�������h�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@���Y�ڋq���O���،��������J�݂��Ă��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�ڋq.is�O���،������J��( )==false�i�O���،������J�݂Ȃ��j�̏ꍇ��<BR>
     * �@@�@@�@@�u���Y�ڋq�͊O���،������J�݂Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * �R�j�@@�������is���������K���iis�������j�ɂĔ����K�������𔻒肷��B<BR>
     * �@@�@@true���ԋp���ꂽ�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�|��~���ɂ́h��~���i���ЋK���j�h�A�h��~���h�i������K���j�̂Q��ނ���A<BR>
     * �@@�@@�@@�G���[���b�Z�[�W�𕪂���B<BR>
     * �@@�@@�@@-��~���i���ЋK���j- <BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00014<BR>
     * �@@�@@�@@-��~���i������K���j- <BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00015
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�B
     * @@param l_eqTypeProduct (��������)<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param l_market (�s��)<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@param l_isSellOrder (is������)<BR>
     * �@@�@@�@@�������A�������̃t���O�B<BR>
     * �@@�@@�@@�������̏ꍇtrue�A�������̏ꍇfalse���w�肷��B
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        SubAccount l_subAccount,
        EqTypeProduct l_eqTypeProduct,
        Market l_market,
        boolean l_isSellOrder)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedProduct(SubAccount, EqTypeProduct, Market, boolean)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@this.validate�������(��������, �s��)���R�[������B
        WEB3EquityTradedProduct l_eqTypeTradedProduct =
            (WEB3EquityTradedProduct)this.validateTradedProduct(l_eqTypeProduct, l_market);

        // �Q�j�@@�O���،������J�݃`�F�b�N
        // �@@�@@�@@�������.���敪���h�O�������h�̏ꍇ�̂݁A
        // �@@�@@�@@���Y�ڋq���O���،��������J�݂��Ă��邩�ǂ����`�F�b�N����B
        EqtypeTradedProductRow l_eqtypeTradedProductRow =
            (EqtypeTradedProductRow)l_eqTypeTradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.FOREIGN_SECITION.equals(l_eqtypeTradedProductRow.getListType()))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = null;
            try
            {
                l_account =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_subAccount.getAccountId());
            }
            catch (NotFoundException l_exp)
            {
                log.error("�ڋq�f�[�^�̎擾�Ɏ��s���܂����B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
            }
            if (l_account.isForeignAccountOpen() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // �R�j�@@�������is���������K���iis�������j�ɂĔ����K�������𔻒肷��B
        // �@@�@@true���ԋp���ꂽ�ꍇ�A��O���X���[����B
        if (l_eqTypeTradedProduct.isSpotTradeControl(l_isSellOrder))
        {
            if (l_isSellOrder)
            {
                if (l_eqtypeTradedProductRow.getSellCashStop()
                    == Integer.parseInt(WEB3TradeStopDef.STOP_MARKET_DEREG))
                {
                    // ��~���i������K���j
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (
                l_eqtypeTradedProductRow.getSellCashStop()
                        == Integer.parseInt(WEB3TradeStopDef.STOP_COMPANY_DEREG))
                {                    // ��~���i���ЋK���j
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            else
            {
                if (l_eqtypeTradedProductRow.getBuyCashStop()
                    == Integer.parseInt(WEB3TradeStopDef.STOP_MARKET_DEREG))
                {
                    // ��~���i������K���j
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (
                l_eqtypeTradedProductRow.getBuyCashStop()
                        == Integer.parseInt(WEB3TradeStopDef.STOP_COMPANY_DEREG))
                {
                    // ��~���i���ЋK���j
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeTradedProduct;
    }

    /**
     * �ivalidate��������戵�K���j�B<BR>
     * <BR>
     * ��������ɂĎ戵�\�Ȗ����ł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@��������w��łȂ��ꍇ�A�����I��<BR>
     * �@@�@@�@@�w�肳�ꂽ�Ŏ�ʂ��h����h�܂��́A�h������������򒥎��h<BR>
     * �@@�@@�@@�łȂ��ꍇ�́A���\�b�h���I������B<BR>
     * <BR>
     * �Q�j�@@��������K�����ł��邩�̔���<BR>
     * �@@�@@�@@��������.��������戵�K��==�h��������ɂĎ戵�s�h<BR>
     * �@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00005
     * @@param l_taxTypeEnum <BR>
     * �@@�@@�@@0:���̑�,�@@1:���,�@@2:����,�@@3:������������򒥎�<BR>
     * �@@�@@�@@�iTaxTypeEnum�ɂĒ�`�j
     * @@param l_eqTypeProduct ���������I�u�W�F�N�g
     * @@param l_isBuyOrder ���������ǂ����̃t���O�B<BR>
     * �@@�@@�@@�������̏ꍇtrue�A�������̏ꍇ��false���w�肷��B
     * @@throws WEB3BaseException
     */
    public void validateCapitalGainTaxDealingsReg(
        TaxTypeEnum l_taxTypeEnum,
        EqTypeProduct l_eqTypeProduct,
        boolean l_isBuyOrder)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateCapitalGainTaxDealingsReg(TaxTypeEnum , EqTypeProduct , boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_taxTypeEnum == null || l_eqTypeProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //��������w��łȂ��ꍇ�A�����I��
        //�w�肳�ꂽ�ŋ敪���h����h�܂��́A�h������������򒥎��h<BR>
        // �@@�@@�@@�łȂ��ꍇ<BR>
        // �@@�@@�@@�i�ŋ敪 = ��ʌ��� or �X�g�b�N�I�v�V�������� or ���̑��@@�̏ꍇ�j<BR>
        //�@@�@@�@@�́A���\�b�h���I������B<BR>
        if (l_taxTypeEnum.equals(TaxTypeEnum.NORMAL)
            || l_taxTypeEnum.equals(TaxTypeEnum.UNDEFINED)
            || l_taxTypeEnum.equals(TaxTypeEnum.STOCK_OPTION))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        EqtypeProductParams l_params =
            (EqtypeProductParams) l_eqTypeProduct.getDataSourceObject();
        log.debug("l_params.getProductId()="+l_params.getProductId());
         
        //��������K�����ł��邩�̔���<BR>
        //��������.��������戵�K��==�h��������ɂĎ戵�s�h
        //�̏ꍇ�A��O���X���[����
        if (WEB3DealtDef.CAN_NOT_DEALT.equals("" + l_params.getCapitalGainTaxDealingsReg()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�����j�B<BR>
     * <BR>
     * �����̃`�F�b�N���s���B<BR>
     * �@@�|�������O�܂��̓}�C�i�X�l�łȂ����ƁB<BR>
     * �@@�|�������s��̔�������P�ʂ𒴂��Ă��Ȃ����ƁB<BR>
     * �@@�|������HOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ƁB<BR>
     * �ivalidateQuantity�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̏����ivalidateQuantity(����)�j�ɂāA<BR>
     *       ������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B<BR>
     * <BR>
     * �Q�j�@@�X�[�p�[�N���X�̏����icheckLotSize(�������.�����P��, ����)�j�ɂāA<BR>
     *       �����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �R�j�@@this.validate�����i�w��\����l�j(�����̕��X, �����̊���))�ɂāA<BR>
     * �@@�@@�@@�����������AHOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �S�j�@@�s��̔�������������v�Z����B<BR>
     * <BR>
     * �i�v�Z���j<BR>
     * �������.�����P�ʁ@@�~�@@�������x�P��(*1)�@@���@@�s��̔����������<BR>
     * <BR>
     * (*1) �������x�P�ʁF<BR>
     * �@@�@@�@@�|�������.�������x�P�� != null �̏ꍇ�́A�������.�������x�P�� ���g�p����B<BR>
     * �@@�@@�@@�|�������.�������x�P�� == null �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���X.get�������x�P�ʁi�s��R�[�h, �������.���敪�j�ɂĎ擾�����l���g�p����B<BR>
     * <BR>
     * �T�j�@@�i�s��̔���������� �� �����j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00160
     * @@param l_tradedProduct �������
     * @@param l_branch ���X
     * @@param l_dblStockQuantity ����
     * @@throws WEB3BaseException
     */
    public void validateQuantity(
        TradedProduct l_tradedProduct,
        WEB3GentradeBranch l_branch,
        double l_dblStockQuantity)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateQuantity(TradedProduct , WEB3GentradeBranch, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null || l_branch == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblStockQuantity < 0.0D)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);

        }

        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        try
        {
            // �P�j�@@�X�[�p�[�N���X�̏����ivalidateQuantity(����)�j�ɂāA
            // �@@�@@������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B
            super.validateQuantity(l_dblStockQuantity);
        }
        catch (OrderValidationException ove)
        {
                 throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {           
            // �Q�j�@@�X�[�p�[�N���X�̏����icheckLotSize(�������.�����P��, ����)�j�ɂāA
            // �@@�@@�����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B
            super.checkLotSize(
                l_tradedProductRow.getLotSize(),
                l_dblStockQuantity);       
        }
        catch (OrderValidationException ove)
        {
            throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00708,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �R�j�@@this.validate�����i�w��\����l�j(�����̕��X, �����̊���))�ɂāA
        // �@@�@@�@@�����������AHOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B
        this.validateQuantity(l_branch, l_dblStockQuantity);

        //  �S�j�@@�s��̔�������������v�Z����B
        // �������x�P�ʂ��擾
        double l_dblLimitedUnit = 0.0D;
        if (l_tradedProductRow.getCompulsiveLimitedUnitIsNull())
        {
            l_dblLimitedUnit =
                l_branch.getDealingLimitUnit(
                    l_tradedProduct.getMarket().getMarketCode(),
                    l_tradedProductRow.getListType());
        }
        else
        {
            l_dblLimitedUnit = l_tradedProductRow.getCompulsiveLimitedUnit();
        } 
        // �������.�����P�ʁ@@�~�@@�������x�P�ʁ@@���@@�s��̔���������� 
        double l_dblResultHighQuantity = l_tradedProductRow.getLotSize() * l_dblLimitedUnit;

        //  �T�j�@@�i�s��̔�����������@@< �����j�̏ꍇ�A��O���X���[����B
        if (l_dblResultHighQuantity < l_dblStockQuantity)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00160,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�戵�\�s��j�B<BR>
     * <BR>
     * ��Е��X�̎戵�\�s�ꂩ���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X.is�戵�\�s��( )�ɂāA�����̎戵�s�ꂩ�𔻒肷��B<BR>
     * false���Ԃ��ꂽ�ꍇ�́A���X�̎戵�\�s��łȂ��Ɣ��肵�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00158<BR>
     * <BR>
     * �Q�j�@@JASDAQ�戵�`�F�b�N<BR>
     * �@@�|�������.validateJASDAQ�����戵�\( )���R�[������B
     * @@param  l_branch ���X�I�u�W�F�N�g�i�ڋq�̎���X�j
     * @@param  l_tradedProduct ������������I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch,
        TradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateHandlingMarket(WEB3GentradeBranch, TradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j�@@���X.is�戵�\�s��( )�ɂāA�����̎戵�s�ꂩ�𔻒肷��B
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        boolean l_isStockHandlingMarket =
            l_branch.isHandlingPossibleMarket(
                l_strMarketCode,
                ProductTypeEnum.EQUITY);
        // false���Ԃ��ꂽ�ꍇ�́A���X�̎戵�\�s��łȂ���
        // ���肵�A��O���X���[����B
        if (!l_isStockHandlingMarket)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00158,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j�@@JASDAQ�戵�`�F�b�N
        //�@@�|�������.validateJASDAQ�����戵�\( )���R�[������B
        WEB3EquityTradedProduct l_equityTradedProduct = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        try
        {
            l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_tradedProduct.getTradedProductId());
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_equityTradedProduct.validateJASDAQProductHandling(l_branch);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate����\������z�j�B<BR>
     * �T�Z���z�l���A��ЁE���X�ň�x�Ɏ���\�ȏ�����z�𒴂��Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�����́u�����^�C�v�v���A����\����l�����肷��B<BR>
     * �@@�@@�����^�C�v���l�A�J�E���g�A���p�A�J�E���g �̏ꍇ�F���X.����\���z����l�i�l�j<BR>
     * �@@�@@�����^�C�v���@@�l�A�J�E���g �̏ꍇ�F���X.����\���z����l�i�@@�l�j<BR>
     * �@@�@@�����^�C�v����L�ȊO�̏ꍇ�F��O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00149<BR>
     * <BR>
     * �Q�j�@@�i �P�j�Ō��肵������\���z����l�@@���@@�S����������j�̏ꍇ�A<BR>
     * �@@�@@����\����l�𒴉߂��Ă���Ɣ��f���A��O���X���[����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00161<BR>
     * @@param l_branch
     * @@param l_market
     * @@param l_dblRestraintTurnover �S������������w�肷��B 
     * @@param l_mainAccountTypeEnum �����^�C�v
     * @@throws WEB3BaseException
     */
    public void validateMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover,
        MainAccountTypeEnum l_mainAccountTypeEnum)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMaxHandlingPrice(Branch, Market, double, MainAccountTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null
            || l_mainAccountTypeEnum == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblRestraintTurnover < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�i�P�j�����́u�����^�C�v�v���A����\����l�����肷��
        double l_dblMaxHandlingPrice = 0;
        if (MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountTypeEnum) ||
            MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountTypeEnum))
        {
            //�����^�C�v���l�A�J�E���g�A���p�A�J�E���g �̏ꍇ�F���X.����\���z����l�i�l�j 
            l_dblMaxHandlingPrice =
                ((BranchParams) l_branch.getDataSourceObject()).getMaxHandlingPriceInd();
        }
        else if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountTypeEnum))
        {
            //�����^�C�v���@@�l�A�J�E���g �̏ꍇ�F���X.����\���z����l�i�@@�l�j
            l_dblMaxHandlingPrice =
                ((BranchParams) l_branch.getDataSourceObject()).getMaxHandlingPriceCorp();
        }
        else
        {
            //�����^�C�v����L�ȊO�̏ꍇ�F��O��throw����B
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00149,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�i�P�j�Ō��肵������\���z����l�@@���@@�S����������j�̏ꍇ�A
        //����\����l�𒴉߂��Ă���Ɣ��f���A��O���X���[����
        if (l_dblMaxHandlingPrice < l_dblRestraintTurnover)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00161,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iget�T�Z���z�v�Z�����j�B<BR>
     * <BR>
     * �y��Е��X���i�e�[�u���z����A�T�Z���z�v�Z�������擾����B<BR>
     * �擾�����v�Z�����́ASTOP���v�Z�v�ۂ̔���Ɏg�p����B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[2]���s���v�Z�P���Z�o�v<BR>
     * ���Q�ƁB
     * @@param l_strCommissionProductCode �y��Е��X���i�e�[�u���z�������Ɏg�p����B
     * @@param l_subAccount �y��Е��X���i�e�[�u���z�������́A���XID�w��Ɏg�p����B
     * @@return long
     * @@throws WEB3SystemLayerException
     */
    public long getEstimatePriceCalcForm(
        String l_strCommissionProductCode,
        SubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getEstimatePriceCalcForm(String, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_strCommissionProductCode == null || l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_strCommissionProductCode.length() == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        long l_lngEstimatePriceCalcForm;
        long l_lngBranchId;
        long l_lngAccountId = l_subAccount.getAccountId();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        try
        {

            l_lngBranchId =
                l_accountManager.getMainAccount(l_lngAccountId).getBranch().getBranchId();
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        try
        {
            //��Е��X���i�e�[�u�����T�Z���z�v�Z�����擾
            l_lngEstimatePriceCalcForm =
                InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                        l_lngBranchId,
                        l_strCommissionProductCode).getEstimatePriceCalcForm();
        }
        catch (DataException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngEstimatePriceCalcForm;
    }

    /**
     * �iis�����������j�B<BR>
     * <BR>
     * �������������������ł��邩�ǂ����̔�����s���B<BR>
     * �������������������̏ꍇ��true���A<BR>
     * ��L�ȊO�̏ꍇ��false��Ԃ��B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[������4]�����������`�F�b�N�v���Q�ƁB
     * @@param l_tsOrderBizDate ������
     * @@param l_tsYearlyBooksClosingDate �����m���<BR>
     * �@@�@@�@@�����m������w�肷��B�ʏ�́A�y���������e�[�u���z���Z�����w�肳���B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isDevidendRightDate(
        Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDevidendRightDate(String, Timestamp)";
        log.entering(STR_METHOD_NAME);

        if (l_tsOrderBizDate == null || l_tsYearlyBooksClosingDate == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        boolean l_boDevidendRightDate = false;

        // �����������Z�o����
        WEB3GentradeBizDate l_bizDateCalcUtil =
            new WEB3GentradeBizDate(l_tsYearlyBooksClosingDate);

        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-2);
      
        if(l_tsOrderBizDate.compareTo(l_tsDevidendRightDate)==0)
        {
            l_boDevidendRightDate = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_boDevidendRightDate;
    }

    /**
     * �icalc�l������j�B<BR>
     * <BR>
     * ��w�l�����̏ꍇ�FSTOP�����Y���������񌠗��������̏ꍇ�� STOP��<BR>
     * ���z�Z�o���A�w�l�����̏ꍇ�F�l���`�F�b�N�Ɏg�p����l������Z�o���A<BR>
     * ���ꂼ��s���B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[������3]�l������Z�o�v<BR>
     * ���Q�ƁB<BR>
     * <BR>
     * Parameter �l���̊�l<BR>
     * Parameter ��l���狖�e�����l��<BR>
     * Parameter (��l�{�l��)�ɑ΂��鍏�ݒl��ݒ肷��B
     * @@param l_dblBasePrice
     * @@param l_dblPriceRange
     * @@param l_dblLimitPriceUnit
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcStopHighPrice(
        double l_dblBasePrice,
        double l_dblPriceRange,
        double l_dblLimitPriceUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcStopHighPrice(double, double, double)";
        log.entering(STR_METHOD_NAME);

        if (l_dblBasePrice < 0
            || l_dblPriceRange < 0
            || l_dblLimitPriceUnit < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        BigDecimal l_bdStopHighPrice;

        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice);
        BigDecimal l_bdPriceRange = new BigDecimal(l_dblPriceRange);
        BigDecimal l_bdLimitPriceUnit = new BigDecimal(l_dblLimitPriceUnit);

        BigDecimal l_bdPrice = l_bdBasePrice.add(l_bdPriceRange);

        if ((l_bdPrice.doubleValue() % l_bdLimitPriceUnit.doubleValue()) == 0)
        {
            l_bdStopHighPrice = l_bdPrice;
        }
        else
        {
            BigDecimal l_bdResult1 = l_bdPrice.add(l_bdLimitPriceUnit);
            BigDecimal l_bdResult2 = l_bdResult1.subtract(new BigDecimal(1));
            BigDecimal l_bdResult3 =
                l_bdResult2.divide(l_bdLimitPriceUnit, BigDecimal.ROUND_DOWN);

            l_bdStopHighPrice = l_bdResult3.multiply(l_bdLimitPriceUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdStopHighPrice.doubleValue();
    }

    /**
     * �ivalidate�����R�[�h�j�B<BR>
     * <BR>
     * �����R�[�h�̑��݃`�F�b�N�����{����B<BR>
     * ���݂���ꍇ�͖����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̖����R�[�h�A�،���ЃR�[�h�ɊY������A��������<BR>
     * �@@�@@�@@�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@��������.������~ == �h��~���h�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�|��~���ɂ́h��~���i���ЋK���j�h�A�h��~���h�i������K���j��<BR>
     * �@@�@@�@@�Q��ނ���A�G���[���b�Z�[�W�𕪂���B<BR>
     * �@@�@@�@@-��~���i���ЋK���j- <BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00014<BR>
     * <BR>
     * �@@�@@�@@-��~���i������K���j- <BR>
     * �@@�@@�@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag      : BUSINESS_ERROR_00015<BR>
     * <BR>
     * �R�j�@@���������I�u�W�F�N�g��ԋp����B
     * @@param l_strProductCode �����R�[�h
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@return WEB3EquityProduct �����I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityProduct l_equityProduct;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�P�j�@@�����̖����R�[�h�A�،���ЃR�[�h�ɊY������A��������
        // �I�u�W�F�N�g�𐶐�����B
        try
        {
            //get �،����
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution(
                    l_strInstitutionCode);

            WEB3EquityProductManager l_eqTypeProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //get ����
            l_equityProduct =
                (WEB3EquityProduct) l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        }
        catch (NotFoundException nfe)
        {
            WEB3BusinessLayerException l_businessException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            throw l_businessException;
        }

        EqtypeProductParams l_eqProductParams =
            (EqtypeProductParams)l_equityProduct.getDataSourceObject();

        //�Q�j�@@��������.������~ == �h��~���h�̏ꍇ�A��O���X���[����B
        int l_intTradeStop = l_eqProductParams.getTradeStop();
        if (l_intTradeStop
            == Integer.parseInt(WEB3TradeStopDef.STOP_MARKET_DEREG))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (
            l_intTradeStop
                == Integer.parseInt(WEB3TradeStopDef.STOP_COMPANY_DEREG))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityProduct;
    }

    /**
     * �ivalidate�����P���j�B<BR>
     * <BR>
     * �w�l���K�؂ł��邩�ǂ����̃`�F�b�N���s���B<BR>
     * �ivalidatePrice�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@����.�w�l�̃}�C�i�X�l�`�F�b�N���s���B<BR>
     * <BR>
     * �Q�j�@@����.�w�l�̃`�F�b�N���s���B<BR>
     * ����.�w�l���K�؂ł��邩�̃`�F�b�N���s���A�K�؂ł����true��ԋp����B<BR>
     * �K�؂łȂ��ꍇ�́A��O���X���[����B<BR>
     * ���s�����i����.�w�l��0�j�̏ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * �����\�b�h�A�y�ѓ����\�b�h����R�[������郁�\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u1.1.  �����P���`�F�b�N�v���Q�ƁB<BR>
     * �葱���V�[�P���X�ɂ��Ắu�i�����jvalidate�����P���v���Q�ƁB
     * @@param l_dblLimitPrice �w�l�B
     * @@param l_tradedProduct (�������)<BR>
     * �@@�@@�@@���i�E�s��֘A�̊e��G���e�B�e�B����f�[�^���擾����ۂɎg�p����B
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�y��Е��X���i�e�[�u���z����T�Z���z�v�Z�������擾����ۂɎg�p����B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean validatePrice(
        double l_dblLimitPrice, 
        WEB3EquityTradedProduct l_tradedProduct, 
        SubAccount l_subAccount)
            throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            "validatePrice(double, WEB3EquityTradedProduct, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        if (Double.isNaN(l_dblLimitPrice) || l_dblLimitPrice == 0)
        {
            return true;
        }
        
        //�Ēl�`�F�b�N       
        this.isTickValueDef((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice);
                
        //�������Row                
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        
        //��������Row���擾
        EqtypeProductRow l_productRow =
            (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();

        //is����������
        Timestamp l_tsOrderBizDate = 
              new Timestamp(WEB3DateUtility.getDate(
              l_tradedProductRow.getValidUntilBizDate(), "yyyyMMdd").getTime());

        boolean l_blnIsDevidendRightDate = 
              this.isDevidendRightDate(l_tsOrderBizDate, l_productRow.getYearlyBooksClosingDate());

        //Is�l���`�F�b�N�ΏۊO
        boolean l_blnIsPriceRangeCheck = false;
        if (WEB3PriceRangeTypeDef.CHECK.equals(l_tradedProductRow.getPriceRangeType()))
        {
            l_blnIsPriceRangeCheck = true;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        //�������i�i���́j�������.�L�����j����t�����̗��c�Ɠ��ł���Ɣ��f����B
        boolean l_blnIsOrderNextDate = false;
        //�������i�i���́j�������.�L�����j
        Date l_datOrderBizDate = WEB3DateUtility.getDate(
            l_tradedProductRow.getValidUntilBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //��t�����̗��c�Ɠ�
        Timestamp l_orderTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        Date l_datGentradeBizDate =
            new WEB3GentradeBizDate(l_orderTime).roll(1);
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_orderTime);
        //�������i�i���́j�������.�L�����j����t�����̗��c�Ɠ��ł���Ɣ��f����
        //��t�����͉c�Ɠ�
        //�g���v���_�N�g�}�l�[�W�� .is������l��M�i�������.�،���ЃR�[�h�j
        if (l_blnIsDevidendRightDate && WEB3DateUtility.compare(WEB3DateUtility.toDay(l_datOrderBizDate),
            WEB3DateUtility.toDay(l_datGentradeBizDate)) == 0  &&
            !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
            !l_equityProductManager.isNextDayBasePriceMail(l_tradedProductRow.getInstitutionCode()))
        {
            l_blnIsOrderNextDate = true;
        }

        if (!l_blnIsOrderNextDate && l_blnIsPriceRangeCheck)
        {
            return this.isPriceRange((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * �i�Ēl�`�F�b�N�j�B<BR>
     * <BR>
     * �w�l�����ݒl�̐����{���ǂ����i�w�l�����ݒl�Ŋ���؂�邩�ǂ����j��<BR>
     * �`�F�b�N����B<BR>
     * �񐮐��{�̏ꍇ�͗�O���X���[����<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_00030<BR>
     * <BR>
     * Parameter �y��Е��X���i�e�[�u���z�������Ɏg�p����B<BR>
     * Parameter ���i�E�s��֘A�̊e��G���e�B�e�B����f�[�^���擾����ۂ�<BR>
     * �g�p���܂��B<BR>
     * Parameter �w�l
     * @@param l_tradedProduct
     * @@param l_dblLimitOrder
     * @@throws WEB3BaseException
     */
    private void isTickValueDef(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblLimitOrder)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTickValueDef(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblLimitOrder < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityProductManager l_productManager = null;
        l_productManager =
            (WEB3EquityProductManager) GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        BigDecimal l_bdTickValue = null;
        try
        {
            //���ݒl�擾
            double l_dblTickValue =
                l_productManager.getTickValue(l_tradedProduct, l_dblLimitOrder);
            l_bdTickValue = new BigDecimal(l_dblTickValue);
        }
        catch (WEB3BaseException l_ble)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_dblLimitOrder % l_bdTickValue.doubleValue() != 0)
        {
            // �Ēl�G���[
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �i�l���`�F�b�N�j�B<BR>
     * <BR>
     * �y�l���e�[�u���z����l�����擾���Ēl������^�l���������Z�o���A<BR>
     * �w�l���l���͈͓̔��ł��邩�ǂ������`�F�b�N����B<BR>
     * �w�l���l���͈͓̔��ł���ꍇ��true���A����ȊO�̏ꍇ��false���A<BR>
     * ���ꂼ��Ԃ��B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[3]�l���`�F�b�N�v���Q�ƁB<BR>
     * <BR>
     * ��L�������A�X���[�����O�B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_00031
     * @@param l_eqtypeTradedProduct �������<BR>
     * �@@�@@�@@�l���敪�y�ы����l���擾�A�y��get�����l��()����<BR>
     * �@@�@@�@@�ݒ�Ɏg�p����B
     * @@param l_dblOrderPrice �����P�� <BR>
     * �@@�@@�@@�`�F�b�N�Ώۂ̒����P���i�w�l�j���Z�b�g����B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isPriceRange(
        WEB3EquityTradedProduct l_eqtypeTradedProduct,
        double l_dblOrderPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isPriceRange(WEB3GentradeTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        BigDecimal l_bdBasePrice = null;
        BigDecimal l_bdResultPriceHigh = null;
        BigDecimal l_bdResultPriceLow = null;
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        // �����P��
        BigDecimal l_bdOrderPrice = new BigDecimal(l_dblOrderPrice);

        //(1) [������5]��l�Z�o�@@���s��
        l_bdBasePrice =
            new BigDecimal(this.calcBasePrice(l_eqtypeTradedProduct));
        log.debug("*** �u��l�v �F  " + l_bdBasePrice.doubleValue());

        //(2) (1)�ŎZ�o������l���g�p���A�l������v�Z����
        // �g�p����l����[������2]�l���Z�o �ɂ��v�Z����B
        BigDecimal l_bdPriceRangeHigh =
            new BigDecimal(
                this.calcPriceRange(
                    l_eqtypeTradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MAXIMUM));
        log.debug("*** �u�l��(����v�Z��)�v �F  " + l_bdPriceRangeHigh.doubleValue());

        //(3) �i(1)�Ŏ擾������l�{(2)�Ŏ擾�����l���j���u��l�v
        // �Ƃ���[������1]���ݒl�擾�@@���s���A
        // �l������ɑ΂��鍏�ݒl���擾����B
        BigDecimal l_bdTickValue =
            new BigDecimal(
                l_productManager.getTickValue(
                    l_eqtypeTradedProduct,
                    l_bdBasePrice.add(l_bdPriceRangeHigh).doubleValue()));
        log.debug("*** �u���ݒl(����v�Z��)�v �F  " + l_bdTickValue.doubleValue());
                    
        //(4) (3)�Ŏ擾�������ݒl���w�l�P�ʂƂ��A(2)�Ŏ擾����
        // �l������v�Z���Ɏg�p����l��������[������3]�l������Z�o 
        // ���s���A�l��������Z�o����
        l_bdResultPriceHigh =
            new BigDecimal(
                this.calcStopHighPrice(
                    l_bdBasePrice.doubleValue(),
                    l_bdPriceRangeHigh.doubleValue(),
                    l_bdTickValue.doubleValue()));
        log.debug("*** �u�l������v �F  " + l_bdResultPriceHigh.doubleValue());

        //(5) (1)�ŎZ�o������l���g�p���A�l�������v�Z���Ɏg�p����l��
        // ��[������2]�l���Z�o �ɂ��v�Z����
        BigDecimal l_bdPriceRangeLow =
            new BigDecimal(
                this.calcPriceRange(
                    l_eqtypeTradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MINIMUM));
        log.debug("*** �u�l��(�����v�Z��)�v �F  " + l_bdPriceRangeLow.doubleValue());

        //(6) (4)�Ŏ擾�����l������A�y��(5)�Ŏ擾�����l�������v�Z����
        // �g�p����l�������ɁA�l���������Z�o����B
        //(��l�|�l��)��0 �̏ꍇ�@@�ˁ@@�l��������1�Ƃ���B
        //��L�ȊO�̏ꍇ�@@�ˁ@@�l��������(��l�|�l��)�@@
        // (�����_�ȉ���؂�̂�)�Ƃ���B
        l_bdResultPriceLow = l_bdBasePrice.subtract(l_bdPriceRangeLow);
        if (l_bdResultPriceLow.compareTo(new BigDecimal(0)) <= 0)
        {
            l_bdResultPriceLow = new BigDecimal(1);
        }
        else
        {
            l_bdResultPriceLow = new BigDecimal(l_bdResultPriceLow.longValue());
        }
        log.debug("*** �u�l�������v �F  " + l_bdResultPriceLow.doubleValue());

        //�l���`�F�b�N
        if ((l_bdOrderPrice.compareTo(l_bdResultPriceHigh) > 0)
            || (l_bdOrderPrice.compareTo(l_bdResultPriceLow) < 0))
        {
            String l_strMessage = "(�������=" + l_eqtypeTradedProduct.getTradedProductId()
                + ")�l������F " + l_bdResultPriceHigh
                + "�A�l�������F" + l_bdResultPriceLow
                + "�A�����P���F" + l_bdOrderPrice;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00031,
                this.getClass().getName() + "."
                + STR_METHOD_NAME,WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                + WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR
                );
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * �icalc�l���j�B<BR>
     * <BR>
     * �l���敪�A�����l���i����l�j�܂��͋����l���i�����l�j�A<BR>
     * �����l�����g�p���A�l�����Z�o���ĕԂ��B <BR>
     * �l���敪�A�����l���i����l�j�A�����l���i�����l�j�́i���́j�������<BR> 
     * ����擾����B <BR>
     * �����̊�l�ɑ΂��鐧���l����get�����l��()���\�b�h���R�[�����Ď擾����B<BR> 
     *  <BR>
     * �����\�b�h�̏ڍׂ� <BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[������2]�l���Z�o�v���Q�ƁB
     * @@param l_tradedProduct �������<BR>
     * �@@�@@�@@�l���敪�A�����l���i����l�j�A�����l���i�����l�j�̎擾�A<BR>
     * �@@�@@�@@�y��get�����l��()�����ݒ�Ɏg�p����B
     * @@param l_dblBasePrice ��l<BR>
     * �@@�@@�@@get�����l��()�̈����ɐݒ肷���l���w�肷��B
     * @@param l_intHighLowDivision ����^�����敪 <BR>
     * �@@�@@�@@�v�Z����l�����A�l������Z�o�^�l�������Z�o�@@<BR>
     * �@@�@@�@@�̂ǂ���Ɏg�p����̂���ݒ肷��B
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double calcPriceRange(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblBasePrice,
        int l_intHighLowDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcPriceRange(WEB3GentradeTradedProduct, double, int)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblBasePrice < 0 || l_intHighLowDivision < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        BigDecimal l_dbRange;
        BigDecimal l_bdResult;

        WEB3EquityProductManager l_productManager = null;
        l_productManager =
            (WEB3EquityProductManager) GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        EqtypeTradedProductParams l_params =
            (EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject();

        // �����l�� �F ���@@�\���Ły�l���e�[�u���z���擾����
        BigDecimal l_bdDeregPriceRange =
            new BigDecimal(
                l_productManager.getDeregPriceRange(
                    l_tradedProduct,
                    l_dblBasePrice));

        // �l���敪 �F �i���́j��������D�l���敪
        String l_strPriceRangeId = l_params.getPriceRangeUnitType();
        if (l_strPriceRangeId == null)
        {
            l_strPriceRangeId = WEB3PriceRangeIdDef.DEFAULT;
        }
        
        //�����l���i����l�j �F �i���́j��������D�����l���i����l�j
        BigDecimal l_bdHighCompulsivePriceRange =
            new BigDecimal(l_params.getHighCompulsivePriceRange());
        
        //�����l���i�����l�j �F �i���́j��������D�����l���i�����l�j
        BigDecimal l_bdLowCompulsivePriceRange =
            new BigDecimal(l_params.getLowCompulsivePriceRange());

        //[�u�l���敪�v���~�@@�̏ꍇ]
        if (l_strPriceRangeId.equals(WEB3PriceRangeIdDef.YEN))
        {
            //���u����^�����敪�v���u1�F����v�@@�̏ꍇ��
            if (l_intHighLowDivision == WEB3MaxMinFlagDef.MAXIMUM)
            {
                //�l�����u�����l���i����l�j�v
                l_dbRange = l_bdHighCompulsivePriceRange;
                log.debug("***�l���Z�o ***    �l�� = �����l���i����l�j�F" + l_dbRange);
            }
            //���u����^�����敪�v���u2�F�����v�@@�̏ꍇ��
            else
            {
                //�l�����u�����l���i�����l�j�v
                l_dbRange = l_bdLowCompulsivePriceRange;
                log.debug("***�l���Z�o ***    �l�� = �����l���i�����l�j�F" + l_dbRange);
            }

        }
        //[�u�l���敪�v�����@@�̏ꍇ]
        else if (l_strPriceRangeId.equals(WEB3PriceRangeIdDef.PERCENT))
        {
            //���u����^�����敪�v���u1�F����v�@@�̏ꍇ��
            if (l_intHighLowDivision == WEB3MaxMinFlagDef.MAXIMUM)
            {
                //�l�����u�����l���v�~�u�����l���i����l�j�v��100
                l_bdResult =
                    l_bdDeregPriceRange.multiply(l_bdHighCompulsivePriceRange);
                l_dbRange =
                    (l_bdResult.divide(new BigDecimal(100), BigDecimal.ROUND_UP));
                log.debug("***�l���Z�o ***    �l�� = �u�����l���v�~�u�����l���i����l�j�v��100  �F "
                    + l_dbRange);
            }
            //���u�E����^�����敪�v���u2�F�����v�@@�̏ꍇ��
            else
            {
                //�l�����u�����l���v�~�u�����l���i�����l�j�v��100
                l_bdResult =
                    l_bdDeregPriceRange.multiply(l_bdLowCompulsivePriceRange);
                l_dbRange =
                    (l_bdResult.divide(new BigDecimal(100), BigDecimal.ROUND_UP));
                log.debug("***�l���Z�o ***    �l�� ���u�����l���v�~�u�����l���i�����l�j�v��100  �F "
                    + l_dbRange);         
            }
        }
        //[�u�l���敪�v��1/XX�@@�̏ꍇ]
        else if (l_strPriceRangeId.equals(WEB3PriceRangeIdDef.FRACTION))
        {
            //���u����^�����敪�v���u1�F����v�@@�̏ꍇ��
            if (l_intHighLowDivision == WEB3MaxMinFlagDef.MAXIMUM)
            {
                //�l�����u�����l���v�~1���u�����l���i����l�j�v 
                l_dbRange =
                    (l_bdDeregPriceRange.divide(
                        l_bdHighCompulsivePriceRange,
                        BigDecimal.ROUND_UP));
                log.debug("***�l���Z�o ***    �l�� ���u�����l���v�~1���u�����l���i����l�j�v  �F "
                    + l_dbRange);  
            }
            //  ���u����^�����敪�v���u2�F�����v�@@�̏ꍇ��
            else
            {
                //�l�����u�����l���v�~1���u�����l���i�����l�j�v 
                l_dbRange =
                    (l_bdDeregPriceRange.divide(
                        l_bdLowCompulsivePriceRange,
                        BigDecimal.ROUND_UP));
                log.debug("***�l���Z�o ***    �l�� ���u�����l���v�~1���u�����l���i�����l�j�v   �F "
                    + l_dbRange);  
            }
        }
        //[�u�l���敪�v���ݒ�Ȃ��@@�̏ꍇ]
        else
        {
            //�l�����u�@@�����l���v
            l_dbRange = l_bdDeregPriceRange;
            log.debug("***�l���Z�o ***    �l�� ���u�����l���v  �F " + l_dbRange);  
        }

        log.exiting(STR_METHOD_NAME);
        return l_dbRange.doubleValue();
    }

    /**
     * �icalc��l�j<BR>
     * <BR>
     * �����l�����擾����ۂɎg�p�����l���Z�o����B <BR>
     * ������̏ꍇ�A�܂��́i���́j��������D��l ���ݒ肳��Ă��Ȃ��ꍇ�́A <BR>
     * �I�l�e�[�u���⎞���T�[�o����K�v�Ȓl���擾���ĎZ�o���s���B <BR>
     * <BR>
     * �����T�[�o����̒l�̎擾��EqTypeQuoteData�C���^�t�F�[�X���g�p���čs���B <BR>
     * EqTypeQuoteData�C���^�t�F�[�X��getter��"0"��Ԃ����ۂɂ́A <BR>
     * ���Y���ڂ́u�l�t�����ł���v�Ɣ��肷��B <BR>
     * <BR>
     * SONAR�ʒm���󂯂Ẳ��菈���i�o���ʒm�A��������ʒm���j��<BR>
     * ������ɓ���������̂��ꒆ�̈����Ŋ�l���Z�o����K�v������B<BR>
     * LocalThread�̐ݒ�L�[�FBACK_SERVICE_IN_ONLINE�̒l==1�i���菈���ŕK���ꒆ�����j��<BR>
     * �ꍇ�́A�s��ǎ��ԑтł����Ă��J�ǎ��ԑш����ŏ������s���B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ� <BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u������5]��l�Z�o�i�j�v���Q�ƁB <BR>
     * @@param l_tradedProduct <BR>
     * �@@�@@�@@�y������������}�X�^�[�e�[�u���z����̊�l�擾�A<BR>
     * �@@�@@�@@�܂��͎����T�[�o����̌��ݒl�A���C�z�l�A���C�z�l�擾��<BR>
     * �@@�@@�@@�g�p����B
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double calcBasePrice(WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcBasePrice(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        EqTypeQuoteData l_eqTypeQuoteData;
        double l_dblBasePrice;
        
        //�����T�[�o���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
        {
            l_eqTypeQuoteData =
                (EqTypeQuoteData)l_tradingModule.getQuoteDataSupplierService().getQuote(l_tradedProduct);
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        boolean l_isTradeOpenTimeZone = false;
        String l_strBackSeriveOnline =
            (String)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE);
        if (l_strBackSeriveOnline != null)
        {
            if (WEB3EquityBackServiceOnlineDef.ONLINE.equals(l_strBackSeriveOnline))
            {
                l_isTradeOpenTimeZone = true;
            }
        }
        else
        {
            if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                l_isTradeOpenTimeZone = true;
            }
        }
        //�ꒆ����
        if (l_isTradeOpenTimeZone)
        {
            //�ȉ��̗D��x�Œl���t���Ă���l����l�Ƃ���B
            //��l(�I�l) �� ���ݒl �� ���C�z�l �� ���C�z�l

            // �������.��l
            l_dblBasePrice = 
                ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getBasePrice();
            if(l_dblBasePrice == 0)
            {
                //���ݒl
                l_dblBasePrice = l_eqTypeQuoteData.getCurrentPrice();
            }
            if (l_dblBasePrice == 0)
            {
                // ���C�z�l
                l_dblBasePrice = l_eqTypeQuoteData.getBidPrice();
            }
            if (l_dblBasePrice == 0)
            {
                // ���C�z�l
                l_dblBasePrice = l_eqTypeQuoteData.getAskPrice();
            }
        }
        else
        {
            EqTypeProduct l_product = (EqTypeProduct) l_tradedProduct.getProduct();

            // ���������擾
            String l_strOrderBizDate =
                ((EqtypeTradedProductParams)l_tradedProduct.getDataSourceObject()).getValidUntilBizDate().trim();

            Timestamp l_tsOrderBizDate = new Timestamp(
                WEB3DateUtility.getDate(l_strOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());  

            // �����m����擾
            Timestamp l_tsYearlyBooksClosingDate =
                ((EqtypeProductParams) l_product.getDataSourceObject()).getYearlyBooksClosingDate();

            //is����������
            boolean l_blnDevidendRightDate =
                this.isDevidendRightDate(
                    l_tsOrderBizDate,
                    l_tsYearlyBooksClosingDate);

            //�v���Z�X�Ǘ��e�[�u���Ɂu0012�F�����I�l�����M�v�f�[�^�����݂���ꍇ
            //���͌����������i�����������`�F�b�N���ʁ�true�j
            //��l���i���́j��������D��l
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager) l_tradingModule.getProductManager();
            if (l_equityProductManager.isBasePriceRecCompleted(l_tradedProduct.getInstitutionCode())
                || l_blnDevidendRightDate)
            {
                l_dblBasePrice = ((EqtypeTradedProductParams)l_tradedProduct.getDataSourceObject()).getBasePrice();
            }
            else
            {
                //�ȉ��̗D��x�Œl���t���Ă���l����l�Ƃ���B
                //�����I�l�@@���@@���ݒl�@@���@@���C�z�l�@@���@@���C�z�l�@@���@@��l�i�I�l�j
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();

                WEB3GentradeBizDate l_genBizDate =
                    new WEB3GentradeBizDate(l_tsOrderBizDate);
                
                // �����I�l
                l_dblBasePrice = l_productManager.getLastClosingPrice(
                    l_tradedProduct.getProduct().getProductId(),
                    l_tradedProduct.getMarketCode(),
                    l_genBizDate.roll(-1));
                    
                //���ݒl
                if (l_dblBasePrice == 0)
                {
                    l_dblBasePrice = l_eqTypeQuoteData.getCurrentPrice();
                }
                if (l_dblBasePrice == 0)
                {
                    // ���C�z�l
                    l_dblBasePrice = l_eqTypeQuoteData.getBidPrice();
                }
                if (l_dblBasePrice == 0)
                {
                    // ���C�z�l
                    l_dblBasePrice = l_eqTypeQuoteData.getAskPrice();
                }
                if (l_dblBasePrice == 0)
                {
                    //�������.��l
                    l_dblBasePrice = 
                        ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getBasePrice();
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;      
    }

    /**
     * (validate���������\���)<BR>
     * �������\�Ȓ�����Ԃ����`�F�b�N����B<BR> 
     * �ivalidateOrderForChangeability( )�̃I�[�o�[���C�h�j<BR> 
     * <BR>
     * this.validate���������\���()�ɏ������Ϗ��idelegate�j����B<BR>  
     * <BR>
     * [validate���������\���()�Ɏw�肷�����] <BR> 
     * �@@�����F�@@�p�����[�^.����  <BR>
     * �@@isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * @@param l_order - ����<BR>
     * @@throws OrderValidationException 
     */
    public void validateOrderForChangeability(Order l_order) 
        throws OrderValidationException
    {
        validateOrderForChangeability(l_order, false);
    }
    
    /**
     * �ivalidate���������\��ԁj�B<BR>
     * <BR>
     * �������\�Ȓ�����Ԃ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�ȉ��A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̏����̂����ꂩ�P�ɂł��Y������ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * <BR>
     * �@@�@@�i�P�j�����P��.�����J�e�S�� == "�������n����"�̏ꍇ<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00821<BR>
     * <BR>
     * �@@�@@�i�Q�j�����P��.������� == "����������" ���� ����R�[�h�iSONAR�j == "����O����"�̏ꍇ<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_01933<BR>
     * <BR>
     * �R�j�@@�����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B<BR>
     * <BR>
     * �R�|�P�j �����L����Ԃ̃`�F�b�N<BR>
     * �@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * �R�|�Q�j ������Ԃ̃`�F�b�N<BR>
     * �s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B<BR>
     * <BR>
     * ==========================================================================<BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j<BR>
     * �@@�@@���@@�������������i������ԊǗ�.is������x�e���ԑ�( ) == false�j�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@ACCEPTED(*1)<BR>
     * �@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@ORDERING<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@(*1)�����P��.����������"�t�w�l"�̏ꍇ�݂̂́A�����\�Ƃ���B<BR>
     * <BR>
     * �@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj�̏ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@�������P��.����������"�t�w�l"�̏ꍇ�̂݁B<BR>
     * <BR>
     * �@@�@@�E�����P��.�l�i���� == �i"���ݒl�w�l����", "�D��l�w�l����"�j�̂����ꂩ�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̎w�l == 0�i���l�i���m��j �̏ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@�u�l�i�����t�������E�l�i���m��v�̗�O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_01340<BR>
     * <BR>
     * ==========================================================================<BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j<BR>
     * �@@�@@���@@��������x�e���Ԓ��i������ԊǗ�.is������x�e���ԑ�( ) == true�j�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �@@�@@�E������Ԃ�MODIFY_ACCEPTED�̏ꍇ�A<BR>
     * �@@�@@�@@�@@this.validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j()���R�[������B<BR>
     * �@@�@@�@@�@@�����ݒ�F�@@�����P�ʁD���XID<BR>
     * <BR>
     * �@@�@@�E���������T�[�r�X.is�s��ʒm������IN�x�e���ԑ�() == true�i���ʒm���j�̏ꍇ��<BR>
     * �@@�@@�@@�����s�Ƃ��A��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * ==========================================================================<BR>
     * �|[�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ]<BR>
     * <BR>
     *  �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@ORDERING<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * ==========================================================================<BR>
     * <BR>
     * �S�j�@@�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j<BR>
     * �@@�@@�@@���@@�s�ꔭ���ς̒���(*2)<BR>
     * �@@�@@�@@�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�����Ώے����̔����o�H�������\���ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@(*2)�s�ꔭ���ς̒���<BR>
     * �@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�s�ꔭ���ς̒����Ɣ��肷��B<BR>
     * <BR>
     * �S�|�P�j�@@���������T�[�r�X.get������ؑ�()�ɂāA������ؑփN���X���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��get������ؑ�()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�j�@@�S�|�P�j�̖߂�l==null�̏ꍇ(*3)�́A���������ɂ��̂܂�return����B<BR>
     * �@@�@@�@@�@@�@@�S�|�P�j�Ŏ擾�����C���X�^���X.is��������\( )==false�̏ꍇ�͒����s�Ƃ��A<BR>
     * �@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�@@(*3)�߂�l==null�̏ꍇ�F�ȉ����Y������B<BR>
     * �@@�@@�@@�@@�|�����Ώۂ̒����P��.�����o�H�敪=="������~"�̏ꍇ<BR>
     * �@@�@@�@@�@@�|�����Ώۂ̒����P�ʂ��A�t�����g�����Ή��s��ɑ΂��钍���ŁA<BR>
     * �@@�@@�@@�@@�@@���@@SONAR���͒����̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�iSONAR���͒����̏ꍇ�A�����o�H�敪�ɂ�SONAR�̌o�H���ݒ肳��Ă���j<BR>
     * <BR>
     * �T�j�@@�x���󋵂̃`�F�b�N <BR>
     * �@@����.isSkip�x���󋵃`�F�b�N == false �̏ꍇ�̂݁A�ȉ��������s���B<BR> 
     * �@@�iW�w�l�ؑ֏�������R�[�����ꂽ�ꍇ�̂݃X�L�b�v����j <BR>
     * <BR>
     * �@@�g�����������}�l�[�W��.is�������x������() == true �̏ꍇ <BR>
     * �@@�����s�Ƃ��A��O��throw����B <BR>
     * �@@�@@�@@class:OrderValidationException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@��is�������x������()�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P�� <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �U�j�@@�������ϒ����̃`�F�b�N<BR>
     * �@@�U�|�P�j�@@�����Ώۂ̔���<BR>
     * �@@�@@�g�����������}�l�[�W��.is�������ϒ���() == true�̏ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     * �@@  [����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * <BR>
     * �@@�U�|�Q�j�@@�㗝���͎҂̎擾<BR>
     * �@@�@@OpLoginSecurityService���擾���A���O�C���h�c���擾����B<BR>
     * �@@�@@�擾�������O�C��ID����A�㗝���͎҂��擾����B<BR>
     * �@@�@@�@@����O��catch���ď����𑱂��邱�ƁB<BR>
     * <BR>
     * �@@�U�|�R�j�@@�����\�`�F�b�N<BR>
     * �@@�@@�U�|�R�|�P�j�@@���҃��O�C���̏ꍇ<BR>
     * �@@�@@�@@�㗝���͎҂��擾�o�����ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g�����������}�l�[�W��.is�����F�������ϒ���() == true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�㗝���͎҂́A�����F�̋������ϒ����͒����ł��܂���v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@: BUSINESS_ERROR_02811<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * <BR>
     * �@@�@@�U�|�R�|�Q�j�@@�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�u�������ϒ����͒����ł��܂���v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@: BUSINESS_ERROR_02811<BR>
     * <BR>
     * @@param l_order ����<BR>
     * @@param l_blnIsSkipDelayCheck isSkip�x���󋵃`�F�b�N<BR>
     * @@throws OrderValidationException
     */
    public void validateOrderForChangeability(Order l_order, boolean l_blnIsSkipDelayCheck)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        { 
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        // �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        // �Q�j�@@�ȉ��̏����̂����ꂩ�P�ɂł��Y������ꍇ�͒����s�Ƃ��A
        //        ��O��throw����B
        // �@@�P�j�����P��.�����J�e�S�� == "�������n����"�̏ꍇ
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00821);           
        }
        
        // �@@�Q�j�����P��.������� == "����������" ���� ����R�[�h�iSONAR�j == "����O����"�̏ꍇ
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
        if (l_orderUnit.getOrderType().equals(OrderTypeEnum.EQUITY_BUY)
                && l_orderUnitRow.getSonarTradedCode().equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET))
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01933);           
        }

        // �R�j�@@�����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B
        // �R�|�P�j �����L����Ԃ̃`�F�b�N
        // �@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͒����s�Ƃ��A��O��throw����B
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
        }

        // �R�|�Q�j ������Ԃ̃`�F�b�N
        //  �s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B<BR>
        //�ꒆ����
        boolean l_result;
        try
        {
            l_result = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            throw new OrderValidationException(l_exp.getErrorInfo());
        }

        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        int l_intStatus = l_orderUnit.getOrderStatus().intValue();
        // �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ]
        if (l_result)
        {
            try
            {
                if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                {
                    if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                       || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)) 
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    // ������Ԃ�ACCEPTED�ŁA����������"�t�w�l"�̏ꍇ�̂݁A�����\�B
                    if (l_intStatus == OrderStatusEnum.IntValues.ACCEPTED &&
                        !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    if (l_orderUnitRow.getConfirmedQuantity() == 0.0D &&
                        !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    //  �@@�@@�E�����P��.�l�i���� == �i"���ݒl�w�l����", "�D��l�w�l����"�j�̂����ꂩ�̏ꍇ�̂݁A
                    // �@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̎w�l == 0�i���l�i���m��j �̏ꍇ�͒����s�Ƃ��A
                    // �@@�@@�@@�u�l�i�����t�������E�l�i���m��v�̗�O��throw����B
                    if (WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()) ||
                        WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
                    {
                        if ((l_orderUnitRow.getConfirmedPriceIsNull() == true)
                        || (l_orderUnitRow.getConfirmedPrice() == 0))
                        {
                            throw new OrderValidationException(
                                 WEB3ErrorCatalog.BUSINESS_ERROR_01340);
                        }
                    }
                }
                else
                {
                    if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING))

                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }

                    if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED) 
                    {
                        this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(l_orderUnit.getBranchId());
                    }

                    if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone((EqTypeOrderUnit)l_orderUnit))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                }
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
        }
        // �|[�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ]
        else
        {
            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
               || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
               || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
               || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)
               || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)) 
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        
        if (l_result && l_orderUnitRow.getConfirmedQuantity() != 0.0D)
        {
            WEB3GentradeOrderSwitching l_orderSwitching = null;
            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        //�T�j�@@�x���󋵂̃`�F�b�N
        //�@@����.isSkip�x���󋵃`�F�b�N == false �̏ꍇ�̂݁A�ȉ��������s���B
        //�@@�iW�w�l�ؑ֏�������R�[�����ꂽ�ꍇ�̂݃X�L�b�v����j
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        if (!l_blnIsSkipDelayCheck)
        {
            //�@@�g�����������}�l�[�W��.is�������x������() == true �̏ꍇ
            //�@@�����s�Ƃ��A��O��throw����B
            boolean l_blnIsNotOrderedDelayOrder = false;
            try
            {
                l_blnIsNotOrderedDelayOrder =
                    l_orderManager.isNotOrderedDelayOrder((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.debug("exception in l_orderManager.isNotOrderedDelayOrder()");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }

            if (l_blnIsNotOrderedDelayOrder)
            {
                log.debug("�g�����������}�l�[�W��.is�������x������() == true �̏ꍇ"
                    + " �����s�Ƃ��A��O��throw����B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        // �U�|�P�j�@@�����Ώۂ̔���
        // �g�����������}�l�[�W��.is�������ϒ���() == true�̏ꍇ�A�ȉ��������s���B
        boolean l_blnIsForcedSettleOrder =
            l_orderManager.isForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
        if (l_blnIsForcedSettleOrder)
        {
            // �U�|�Q�j�@@�㗝���͎҂̎擾
            // OpLoginSecurityService���擾���A���O�C���h�c���擾����B
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_lngLoginId = l_opLoginSecurityService.getLoginId();
            Trader l_trader = null;
            try
            {
                // �擾�������O�C��ID����A�㗝���͎҂��擾����B
                l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(l_lngLoginId);
            }
            catch (NotFoundException l_ex)
            {
                // ��O��catch���ď����𑱂��邱�ƁB
                log.debug("��O��catch���ď����𑱂��邱�ƁB");
            }

            // �U�|�R�|�P�j�@@���҃��O�C���̏ꍇ
            // �㗝���͎҂��擾�o�����ꍇ�A�ȉ��̏������s���B
            // �g�����������}�l�[�W��.is�����F�������ϒ���()
            if (l_trader != null)
            {
                boolean l_blnIsApproveForcedSettleOrder =
                    l_orderManager.isApproveForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
                if (l_blnIsApproveForcedSettleOrder)
                {
                    log.debug("�㗝���͎҂́A�����F�̋������ϒ����͒����ł��܂���");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02810);
                }
            }
            else
            {
                // �U�|�R�|�Q�j�@@�ȊO�̏ꍇ
                //�u�������ϒ����͒����ł��܂���v�̗�O��throw����B
                log.debug("�������ϒ����͒����ł��܂���");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02811);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate��������\��ԁj�B<BR>
     * <BR>
     * �����̎�����\��������Ԃł��邩�ǂ������`�F�b�N����B <BR>
     * �ivalidateOrderForCancellation( )�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�ȉ��A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B <BR>
     * <BR>
     * �Q�j�@@�����L����ԁiorder_open_status�j�A <BR>
     * �y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B <BR>
     * <BR>
     * �Q�|�P�j �����L����Ԃ̃`�F�b�N <BR>
     * �@@�@@�@@�E�����������́A�S�����ɂ��N���[�Y�ς̌������n����(*)�ȊO�̏ꍇ�ŁA<BR> 
     * �@@�@@�@@�@@�����L����Ԃ�CLOSED�̏ꍇ�͎���s�Ƃ��A��O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@(*)�����P��.�����J�e�S�� == �h�����E���n�����h�A���A <BR>
     * �@@�@@�@@�@@�����P��.isFullyExecuted == true�A���A<BR>
     * �@@�@@�@@�@@�����P��.������ == ������ԊǗ�.get������() �̏ꍇ<BR>
     * �@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag   : BUSINESS_ERROR_00820<BR>
     * <BR> 
     * �@@�@@�@@�@@�������������̌������n�̑S����蒍���̏ꍇ�A���Y�`�F�b�N�͍s��Ȃ��B <BR>
     * �@@�@@�@@�@@(�����L����Ԃ�CLOSED�ł������) <BR>
     * <BR>
     * �Q�|�Q�j ������Ԃ̃`�F�b�N<BR>
     * �s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B<BR>
     * <BR>
     * ==========================================================================<BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j<BR>
     * �@@�@@���@@�������������i������ԊǗ�.is������x�e���ԑ�( ) == false�j�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@ACCEPTED(*1)<BR>
     * �@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@ORDERING<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@(*1)�ȉ��̂����ꂩ�̏����ɍ��v����ꍇ�́A����\�Ƃ���B<BR>
     * �@@�@@�@@�@@�@@�@@�E�����P��.����������"�t�w�l"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�E�����P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj�̏ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@��(*1)�̏ꍇ�͎���\�Ƃ���B<BR>
     * <BR>
     * �@@�@@�E�����P��.�l�i���� == �i"���ݒl�w�l����", "�D��l�w�l����"�j�̂����ꂩ�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̎w�l == 0�i���l�i���m��j �̏ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@�u�l�i�����t�������E�l�i���m��v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01340<BR>
     * <BR>
     * ==========================================================================<BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j<BR>
     * �@@�@@���@@��������x�e���Ԓ��i������ԊǗ�.is������x�e���ԑ�( ) == true�j�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �@@�@@�E������Ԃ�MODIFY_ACCEPTED�̏ꍇ�A<BR>
     * �@@�@@�@@�@@this.validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j()���R�[������B<BR>
     * �@@�@@�@@�@@�����ݒ�F�@@�����P�ʁD���XID<BR>
     * <BR>
     * �@@�@@�E���������T�[�r�X.is�s��ʒm������IN�x�e���ԑ�() == true�i���ʒm���j�̏ꍇ��<BR>
     * �@@�@@�@@����s�Ƃ��A��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00032<BR>
     * <BR>
     * ==========================================================================<BR>
     * �|[�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ]<BR>
     * <BR>
     *  �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@ORDERING<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �R�j�@@�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j<BR>
     * �@@�@@�@@���@@�s�ꔭ���ς̒���(*2)<BR>
     * �@@�@@�@@�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@����Ώے����̔����o�H������\���ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@(*2)�s�ꔭ���ς̒���<BR>
     * �@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�s�ꔭ���ς̒����Ɣ��肷��B<BR>
     * <BR>
     * �R�|�P�j�@@���������T�[�r�X.get������ؑ�()�ɂāA������ؑփN���X���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��get������ؑ�()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@�R�|�P�j�̖߂�l==null�̏ꍇ(*3)�́A���������ɂ��̂܂�return����B<BR>
     * �@@�@@�@@�@@�@@�R�|�P�j�Ŏ擾�����C���X�^���X.is��������\( )==false�̏ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@�@@�@@��O��throw����B<BR>
     * <BR>
     * �@@�@@(*3)�߂�l==null�̏ꍇ�F�ȉ����Y������B<BR>
     * �@@�@@�@@�@@�|����Ώۂ̒����P��.�����o�H�敪=="������~"�̏ꍇ<BR>
     * �@@�@@�@@�@@�|����Ώۂ̒����P�ʂ��A�t�����g�����Ή��s��ɑ΂��钍���ŁA<BR>
     * �@@�@@�@@�@@���@@SONAR���͒����̏ꍇ<BR>
     * �@@�@@�@@�@@�iSONAR���͒����̏ꍇ�A�����o�H�敪�ɂ�SONAR�̌o�H���ݒ肳��Ă���j<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00032<BR>
     * <BR>
     * �S�j�@@�������ϒ����̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����Ώۂ̔���<BR>
     * �@@�@@�g�����������}�l�[�W��.is�������ϒ���() == true�̏ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     * �@@  [����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�㗝���͎҂̎擾<BR>
     * �@@�@@OpLoginSecurityService���擾���A���O�C���h�c���擾����B<BR>
     * �@@�@@�擾�������O�C��ID����A�㗝���͎҂��擾����B<BR>
     * �@@�@@�@@����O��catch���ď����𑱂��邱�ƁB<BR>
     * <BR>
     * �@@�S�|�R�j�@@����\�`�F�b�N<BR>
     * �@@�@@�S�|�R�|�P�j�@@���҃��O�C���̏ꍇ<BR>
     * �@@�@@�@@�㗝���͎҂��擾�o�����ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g�����������}�l�[�W��.is�����F�������ϒ���() == true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�㗝���͎҂́A�����F�̋������ϒ����͎���ł��܂���v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@: BUSINESS_ERROR_02840<BR>
     * <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * <BR>
     * �@@�@@�S�|�R�|�Q�j�@@�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�u�������ϒ����͎���ł��܂���v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@: BUSINESS_ERROR_02841<BR>
     * <BR>
     * @@param l_order ����
     * @@throws OrderValidationException
     */
    public void validateOrderForCancellation(Order l_order)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(Order)";
        log.entering(STR_METHOD_NAME);
        if (l_order == null)
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        //�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits.length != 1)
        {
            // �����P�ʃI�u�W�F�N�g�����P�ȊO�B
            log.error(
                STR_METHOD_NAME
                    + "ERROR�F����ID�F["
                    + l_order.getOrderId()
                    + "] �����P�ʃI�u�W�F�N�g���F["
                    + l_orderUnits.length
                    + "]");
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
     
        // �����L����Ԃ̃`�F�b�N
        // �����L����Ԃ�CLOSED�̏ꍇ�͎���s�Ƃ��A��O��throw����B
        // �i�������A�S�����ɂ��CLOSE�ς̌������n�����ɂ��ẮA�����������Ɍ������Ƃ���j
        Date l_datOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        try
        {
            if ((OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()) == true) &&
                (l_orderUnit.isFullyExecuted() == true) &&
                (l_datOrderBizDate.equals(WEB3GentradeTradingTimeManagement.getOrderBizDate()) == true))
            {
                ;
            }
            else
            {
                if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
                {
                    throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00820);
                }
            }
        }
        catch (WEB3BaseException l_bex)
        {
            throw new OrderValidationException(
                l_bex.getErrorInfo());            
        }

        //������Ԃ̃`�F�b�N
        //�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s��
        //�ꒆ����
        boolean l_result;
        try
        {
            l_result =
                (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (WEB3SystemLayerException e)
        {
            throw new OrderValidationException(e.getErrorInfo());
        }
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        int l_intStatus = l_orderUnit.getOrderStatus().intValue();
        if (l_result) //������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true
        {
            try
            {
                if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                {
                    //�����P��.�����������擾
                    String l_orderConditionType =
                        l_orderUnitRow.getOrderConditionType();
                    String l_sonarTradedCode = l_orderUnitRow.getSonarTradedCode();
                    if (((l_intStatus == OrderStatusEnum.IntValues.ACCEPTED)
                        && (!l_orderConditionType.equals(WEB3OrderingConditionDef.STOP_LIMIT_PRICE))
                        && (!l_sonarTradedCode.equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET)))
                        || (l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                        || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                        || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                        || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                        || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    if (l_orderUnitRow.getConfirmedQuantity() == 0.0D &&
                        !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) &&
                        !WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_sonarTradedCode))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                    if(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()) ||
                        WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()))
                    {
                        if ((l_orderUnitRow.getConfirmedPriceIsNull() == true)
                        || (l_orderUnitRow.getConfirmedPrice() == 0))
                        {
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01340);
                        }
                    }
                }
                else
                {
                    if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                       || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                       || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)) 
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }

                    if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED) 
                    {
                        this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(l_orderUnit.getBranchId());
                    }
                    
                    if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone((EqTypeOrderUnit)l_orderUnit))
                    {
                        throw new OrderValidationException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                    }
                }
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
        }
        else //������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false
            {
            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        
        if (l_result && l_orderUnitRow.getConfirmedQuantity() != 0.0D)
        {
            WEB3GentradeOrderSwitching l_orderSwitching = null;
            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new OrderValidationException(l_wbe.getErrorInfo());
            }
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        // �S�|�P�j�@@�����Ώۂ̔���
        // �g�����������}�l�[�W��.is�������ϒ���() == true�̏ꍇ�A�ȉ��������s���B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        boolean l_blnIsForcedSettleOrder =
            l_orderManager.isForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
        if (l_blnIsForcedSettleOrder)
        {
            // �S�|�Q�j�@@�㗝���͎҂̎擾
            // OpLoginSecurityService���擾���A���O�C���h�c���擾����B
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_lngLoginId = l_opLoginSecurityService.getLoginId();
            Trader l_trader = null;
            try
            {
                // �擾�������O�C��ID����A�㗝���͎҂��擾����B
                l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(l_lngLoginId);
            }
            catch (NotFoundException l_ex)
            {
                // ��O��catch���ď����𑱂��邱�ƁB
                log.debug("��O��catch���ď����𑱂��邱�ƁB");
            }

            // �S�|�R�|�P�j�@@���҃��O�C���̏ꍇ
            // �㗝���͎҂��擾�o�����ꍇ�A�ȉ��̏������s���B
            // �g�����������}�l�[�W��.is�����F�������ϒ���()
            if (l_trader != null)
            {
                boolean l_blnIsApproveForcedSettleOrder =
                    l_orderManager.isApproveForcedSettleOrder((EqTypeOrderUnit)l_orderUnit);
                if (l_blnIsApproveForcedSettleOrder)
                {
                    log.debug("�㗝���͎҂́A�����F�̋������ϒ����͎���ł��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02840);
                }
            }
            else
            {
                // �S�|�R�|�Q�j�@@�ȊO�̏ꍇ
                //�u�������ϒ����͒����ł��܂���v�̗�O��throw����B
                log.debug("�������ϒ����͎���ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02841);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icalc�����j�B<BR>
     * <BR>
     * �������Z�o����B <BR>
     * �Z�o���������͌v�Z�P���Ƃ��āA <BR>
     * calc�T�Z��n���()���Ŏg�p����B <BR>
     * <BR>
     * �����\�b�h�̏ڍׂ� <BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[3]�����Z�o�v���Q�ƁB <BR>
     * �葱���V�[�P���X�ɂ��Ắu�i�����jcalc�����v���Q�ƁB <BR>
     * <BR>
     * isSTOP���l����true�̏ꍇ�A<BR>
     * �����̎萔�����i�R�[�h��null���������O��throw<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00736
     * @@param l_strCommissionProductCode �萔�����i�R�[�h<BR>
     * �@@�@@�@@�y��Е��X���i�e�[�u���z�������Ɏg�p����B<BR> 
     * <BR>
     * �@@�@@�@@�����́uisSTOP���l���v��false�̏ꍇ�́A�y��Е��X���i�e�[�u���z���Q�Ƃ��Ȃ����߁A<BR> 
     * �@@�@@�@@null�ݒ���B
     * @@param l_tradedProduct �������<BR>
     * �@@�@@�@@�y������������e�[�u���z����̎����擾�Ɏg�p����B
     * @@param l_subAccount �⏕����<BR>
     * �@@�@@�@@�y��Е��X���i�e�[�u���z����́u�T�Z���z�v�Z�����v�擾���́u���XID�v�̎w��Ɏg�p����B
     * @@param l_isStopPriceConsideration isSTOP���l��<BR>
     * �@@�@@�@@�y��Е��X���i�e�[�u���z�T�Z���z�v�Z������STOP���S�� �̐ݒ��<BR>
     * �@@�@@�@@�l�����邩�ǂ����̃t���O�B<BR> 
     * �@@�@@�@@�T�Z���z�v�Z���� �̐ݒ�����̂܂܎g�p����ꍇ��true���A<BR> 
     * �@@�@@�@@��������ꍇ��false���A���ꂼ��ݒ肷��B
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcCurrentPrice(
        String l_strCommissionProductCode,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3GentradeSubAccount l_subAccount,
        boolean l_isStopPriceConsideration)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCurrentPrice(String, WEB3EquityTradedProduct, SubAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null
            || l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_isStopPriceConsideration && l_strCommissionProductCode == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                     
        EqTypeQuoteData l_eqTypeQuoteData;
        double l_dblResultPrice = 0D;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        boolean l_priceFlg = true;
        
        //1.1 ������isSTOP���l��==true�i�l���v�j�̏ꍇ�̂�
        if (l_isStopPriceConsideration)
        {
            //1.1.1 get�T�Z���z�v�Z����(String, �⏕����)
            long l_lngEstimatePriceCalcForm =
                this.getEstimatePriceCalcForm(
                    l_strCommissionProductCode,
                    l_subAccount);

            
            //1.1.2�擾�����T�Z��n����v�Z������STOP���S�������̏ꍇ�̂�
            if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_lngEstimatePriceCalcForm)
            {
                //1.1.2.1 getProduct()
                EqTypeProduct l_product = (EqTypeProduct) l_tradedProduct.getProduct();            

                // �����m����擾
                Timestamp l_tsYearlyBooksClosingDate =
                    ((EqtypeProductParams) l_product.getDataSourceObject()).getYearlyBooksClosingDate();
        
                // �������擾
                String l_strOrderBizDate =
                    ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getValidUntilBizDate().trim();
                Timestamp l_tsOrderBizDate = new Timestamp(
                    WEB3DateUtility.getDate(l_strOrderBizDate, "yyyyMMdd").getTime());    
            
                //1.1.2.2 is����������
                boolean l_blnDevidendRightDate =
                    this.isDevidendRightDate(
                        l_tsOrderBizDate,
                        l_tsYearlyBooksClosingDate);
                        
                if (l_blnDevidendRightDate)
                {
                    //�������i�i���́j�������.�L�����j
                    Date l_datOrderBizDate = WEB3DateUtility.getDate(
                        l_strOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                    //��t�����̗��c�Ɠ�
                    Timestamp l_orderTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                    Date l_datGentradeBizDate =
                        new WEB3GentradeBizDate(l_orderTime).roll(1);
                    String l_strBizDateType = 
                        WEB3GentradeTradingTimeManagement.getBizDateType(l_orderTime);

                    //�������i�i���́j�������.�L�����j����t�����̗��c�Ɠ��ł���Ɣ��f����
                    //��t�����͉c�Ɠ��ł���B
                    //�g���v���_�N�g�}�l�[�W�� .is������l��M�i�������.�،���ЃR�[�h�j
                    if (WEB3DateUtility.compare(WEB3DateUtility.toDay(l_datOrderBizDate),
                        WEB3DateUtility.toDay(l_datGentradeBizDate)) == 0 &&
                        !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                        !l_equityProductManager.isNextDayBasePriceMail(l_tradedProduct.getInstitutionCode()))
                    {
                        l_priceFlg = true;
                    }
                    else
                    {
                        l_priceFlg = false;
                    }

                }
                else
                {
                    l_priceFlg = false;
                }
            }
            else
            {
                l_priceFlg = true;
            }
        }
        
        // �g���v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager) GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();     
            
        boolean l_isMarketOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        // �s��ǎ��ԑт̏ꍇ
        double l_dblLastClosingPrice = 0D;
        if (l_isMarketOpenTimeZone == false)
        {
            // ���������擾
            String l_strOrderBizDate =
                ((EqtypeTradedProductParams) l_tradedProduct.getDataSourceObject()).getValidUntilBizDate().trim();
            WEB3GentradeBizDate l_genBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(WEB3DateUtility.getDate(l_strOrderBizDate, "yyyyMMdd").getTime()));
            
            // �����I�l���擾
            l_dblLastClosingPrice = l_productManager.getLastClosingPrice(
                l_tradedProduct.getProduct().getProductId(),
                l_tradedProduct.getMarketCode(),
                l_genBizDate.roll(-1));
        }
        
        if (l_priceFlg == true)
        {
            try
            {
                l_eqTypeQuoteData =
                    (EqTypeQuoteData) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService().getQuote(
                        l_tradedProduct);
            }
            catch (NotFoundException nfe)
            {
                log.error(STR_METHOD_NAME,nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            log.debug("*** �����I�l=" + l_dblLastClosingPrice);
            log.debug("*** ���ݒl=" + l_eqTypeQuoteData.getCurrentPrice());
            log.debug("*** ���C�z�l=" + l_eqTypeQuoteData.getBidPrice());
            log.debug("*** ���C�z�l=" + l_eqTypeQuoteData.getAskPrice());
            log.debug("*** �I�l=" + l_tradedProduct.getLastClosingPrice());

            // �����I�l
            l_dblResultPrice = l_dblLastClosingPrice;
            if (l_dblResultPrice == 0)
            {
                // ���ݒl
                l_dblResultPrice = l_eqTypeQuoteData.getCurrentPrice();
            }
            
            if (l_dblResultPrice == 0)
            {
                // ���C�z�l
                l_dblResultPrice = l_eqTypeQuoteData.getBidPrice();
            }

            if (l_dblResultPrice == 0)
            {
                // ���C�z�l
                l_dblResultPrice = l_eqTypeQuoteData.getAskPrice();
            }

            if (l_dblResultPrice == 0)
            {
                // ��l�i�I�l�j
                l_dblResultPrice = l_tradedProduct.getLastClosingPrice();
            }
        }
        else
        {
            //�u�T�Z���z�v�Z�����v��STOP���S�������A���񌠗��������i�����������`�F�b�N���ʁ�false�j�̏ꍇ
            //��l�Z�o
            double l_dblBasePrice = this.calcBasePrice(l_tradedProduct);

            //�l���Z�o
            double l_dblRangePrice =
                this.calcPriceRange(
                    l_tradedProduct,
                    l_dblBasePrice,
                    WEB3MaxMinFlagDef.MAXIMUM);

            //���ݒl�擾
            BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice);

            double l_dbltickValue =
                l_productManager.getTickValue(
                    l_tradedProduct,
                    (l_bdBasePrice.add(new BigDecimal(l_dblRangePrice))).doubleValue());

            //�l������Z�o
            l_dblResultPrice =
                this.calcStopHighPrice(
                    l_dblBasePrice,
                    l_dblRangePrice,
                    l_dbltickValue);
        }        
        return l_dblResultPrice;
    }
    
    /**
     * �ivalidate���t�\���ʁj�B<BR>
     * <BR>
     * ���t�ɏ\���Ȑ��ʂ̎��Y�����L���Ă��邩���`�F�b�N����B <BR>
     * �ivalidateSellableAssetQuantity( )�̃I�[�o�[���[�h�j <BR>
     * <BR>
     * �P�j�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y(����ID, �⏕����ID, ����ID,
     * �ŋ敪)�ɂ��A <BR>
     * �@@�@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�ۗL���Y.getLockedQuantity(���YID)�ɂ��A���b�N�����ʂ���
     * ������B <BR>
     * <BR>
     * �R�j�@@�i�ۗL���Y�I�u�W�F�N�g.���ʁ|���b�N�����ʁj������.�����̏ꍇ�́A<BR>
     * ��O��throw����B  <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00167
     * @@param l_subAccount �⏕����
     * @@param l_tradeProduct �������
     * @@param l_dblQuantity (����) <BR>
     * �@@�@@�@@���蒍���̐���
     * @@param l_taxTypeEnum �ŋ敪
     * @@throws WEB3BaseException
     */
    public void validateSellableAssetQuantity(
        SubAccount l_subAccount,
        TradedProduct l_tradeProduct,
        double l_dblQuantity,
        TaxTypeEnum l_taxTypeEnum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSellableAssetQuantity(SubAccount, TradedProduct, double, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null
            || l_tradeProduct == null
            || l_taxTypeEnum == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblQuantity < 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�����|�W�V�����}�l�[�W�����擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        //�P�j�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y(����ID, �⏕����ID, ����ID,
        // �ŋ敪)�ɂ��A �ۗL���Y�I�u�W�F�N�g���擾����B
        WEB3EquityAsset l_asset = null;

        l_asset =
            (WEB3EquityAsset)l_positionManager.getAsset(
                l_subAccount.getMainAccount().getAccountId(),
                l_subAccount.getSubAccountId(),
                l_tradeProduct.getProduct().getProductId(),
                l_taxTypeEnum);

        double l_dblLockedQuantity = 0D;
        double l_remainQuantity = 0D;
        //�Q�j�@@�ۗL���Y.getLockedQuantity(���YID)�ɂ��A���b�N�����ʂ��擾����B
        if (l_asset != null)
        {
            l_dblLockedQuantity = l_asset.getLockedQuantity();
            l_remainQuantity = l_asset.getQuantity() - l_dblLockedQuantity;
        }
        else
        {
            WEB3BusinessLayerException l_businessException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00167,
                    this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                    "�����ɊY������ۗL���Y�Ȃ�");
            throw l_businessException;
        }

        //�R�j�@@�i�ۗL���Y�I�u�W�F�N�g.���ʁ|���b�N�����ʁj������.�����̏ꍇ�́A
        // ��O��throw����B
        if (l_remainQuantity < l_dblQuantity)
        {
            WEB3BusinessLayerException l_businessException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00167,
                    this.getClass().getName() + "."
                    + STR_METHOD_NAME,WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                    + WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR);
            throw l_businessException;
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iisChangeIs�o����܂Łj�B<BR>
     * <BR>
     * �o����܂Œ������ǂ����i�u�o����܂Œ����v�łȂ�(����������)<BR>
     * �^�u�o����܂Œ����v�j����������Ă��Ȃ����ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�������̔���<BR>
     * ���������������e.get�����������P��( )�ɂĒ����P�ʃI�u�W�F�N�g���擾���A<BR>
     * ���������u�o����܂Œ����v(*1)���ǂ����𔻒肷��B<BR>
     * <BR>
     * (*1) �g�����������}�l�[�W��.is�o����܂Œ����P��<BR>
     * (�����P�ʃI�u�W�F�N�g)==true��<BR>
     * �@@�@@�@@�ꍇ�A�u�o����܂Œ����v�ł���B<BR>
     * �@@�@@�@@false�̏ꍇ�A�u�o����܂Œ����v�łȂ��B<BR>
     * <BR>
     * �Q�j�@@�������͂̔���<BR>
     * ���������������e.get�����l�ڍ�( )�ɂāA<BR>
     * �������������l�ڍ׃I�u�W�F�N�g���擾���A<BR>
     * �������������l�ڍ�.get������is�o����܂Œ���( )�̖߂�l�Ŕ��肷��B<BR>
     * �i�߂�l==true�̏ꍇ�́u�o����܂Œ����v�A<BR>
     * false�̏ꍇ�́u�o����܂Œ����v�łȂ��B�j<BR>
     * <BR>
     * �R�j�@@�u�o����܂Œ����v�w��ύX�`�F�b�N<BR>
     * �@@�@@�|�i�������̎w��j != �i�������͂̎w��j�̏ꍇ�́A<BR>
     *   �@@��O��throw����B <BR>
     * �@@�@@�@@class: WEB3BaseException<BR>
     * �@@�@@�@@tag:    BUSINESS_ERROR_00070
     * @@param l_changeOrderSpec (���������������e)<BR>
     * �@@�@@�@@���������������e�I�u�W�F�N�g
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeIsOrderUntilDeadLine(WEB3EquityChangeOrderSpec l_changeOrderSpec)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "isChangeIsOrderUntilDeadLine(WEB3EquityChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_changeOrderSpec == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�����P�ʃI�u�W�F�N�g���擾����
        EqTypeOrderUnit l_orderUnit =
            (EqTypeOrderUnit) l_changeOrderSpec.getOrgChangeOrderUnit();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        boolean l_isDeadLineOrderUnit = false;
        try
        {
            l_isDeadLineOrderUnit =
                l_orderManager.isCarriedOrderUnit(l_orderUnit);

        }
        catch (WEB3SystemLayerException l_sle)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00070,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �������͂̔���
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry) l_changeOrderSpec.getChangeOrderUnitEntry();
        boolean l_isChangeAfterIsOrderUntilDeadLine =
            l_changeOrderUnitEntry.getChangeAfterIsOrderUntilDeadLine();
        if (l_isDeadLineOrderUnit != l_isChangeAfterIsOrderUntilDeadLine)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00070,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * �ivalidate�o����܂Œ�����~�����j�B<BR>
     * <BR>
     * �����J�z�X�L�b�v�����ɑ΂��A���������Ɂu�o����܂Œ����v��<BR>
     * �w�肳��Ă��Ȃ����Ƃ̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�����J�z�X�L�b�v�����`�F�b�N <BR>
     * �o����܂Œ����w��̏ꍇ(*1)�A�������.is�J�z�X�L�b�v����( )��<BR>
     * �X�L�b�v�������ǂ����𔻒肷��B�߂�l��true�̏ꍇ�A<BR>
     * ��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException <BR>
     * �@@tag:   BUSINESS_ERROR_00153 <BR>
     * (*1) �����������Ɣ��������Ⴄ�ꍇ�A�o����܂�<BR>
     * �����w��Ɣ��f����B
     * @@param l_exepireDate ����������
     * @@param l_eqTradedProduct �������
     * @@throws WEB3BaseException
     */
    protected void validateExecutedOrderStopProduct(
        Date l_exepireDate,
        WEB3EquityTradedProduct l_eqTradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateExecutedOrderStopProduct(Date, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        EqtypeTradedProductRow l_eqTradedProductRow =
            (EqtypeTradedProductRow)l_eqTradedProduct.getDataSourceObject();

        // �������擾
        String l_strOrderBizDate = l_eqTradedProductRow.getValidUntilBizDate().trim();
        String l_strExepireDate = WEB3DateUtility.formatDate(l_exepireDate, "yyyyMMdd");
        if (!l_strOrderBizDate.equals(l_strExepireDate))
        {
            boolean l_isSkipProduct = l_eqTradedProduct.isTransferSkipProduct();
            if (l_isSkipProduct)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00153,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate��������i�M�p�j�j�B<BR>
     * <BR>
     * �戵�\�`�F�b�N�A�y�ѕٍϋ敪�ʂ̔�����~�`�F�b�N�i�M�p�j�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A��������I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateTradedProductForMarginTrading( )�̃I�[�o�[���[�h�j<BR>
     * ----------------------------------------<BR>
     * �����x�M�p�����̎戵<BR>
     * �@@�|�ݎؖ����ł���΁A�����^�������ɉB<BR>
     * �@@�|��ݎؖ����̏ꍇ�́A��ݎؖ����̎戵���X�ł���΁A�����͉B<BR>
     * �@@�|��ݎؖ����̏ꍇ�́A�����͕s�B<BR>
     * <BR>
     * ����ʐM�p�����̎戵<BR>
     * �@@�|��ʐM�p�����ł����Ă��A�����s�����̏ꍇ�́A�����͕s�B�i�����͉j<BR>
     * ----------------------------------------<BR>
     * <BR>
     * �P�j�@@this.validate�������(��������, �s��)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�O���،������J�݃`�F�b�N<BR>
     * �@@�@@�@@�������.���敪���h�O�������h�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@���Y�ڋq���O���،��������J�݂��Ă��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�ڋq.is�O���،������J��( )==false�i�O���،������J�݂Ȃ��j�̏ꍇ��<BR>
     * �@@�@@�@@�u���Y�ڋq�͊O���،������J�݂Ȃ��v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * �R�j�@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�����ٍ̕ϋ敪�̒l�ɂ��A�`�F�b�N���e�𕪂���B<BR>
     * <BR>
     * �R�|�P�j�@@�戵�\�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@���戵�\�`�F�b�N�́A�����̒����J�e�S����"�V�K��"�iOPEN_MARGIN�j�̏ꍇ�̂�<BR>
     * �@@�@@�@@�@@�@@�����{����B<BR>
     * �@@----------------------------------------------------<BR>
     * �@@�������ٍ̕ϋ敪��DEFAULT�i�w��Ȃ��j �̏ꍇ��<BR>
     * <BR>
     * �@@---------------<BR>
     * �@@[���x�M�p�戵�\�`�F�b�N]<BR>
     * �@@�@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A���x�M�p�戵�s�\�Ƃ���B(*1)<BR>
     * <BR>
     * �@@�|�������.���x�M�p�����敪���u���x�M�p�����łȂ��v<BR>
     * �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A���A<BR>
     * �@@�@@�����̕��X�I�u�W�F�N�g.��ݎؖ����戵���u�戵�s�v<BR>
     * �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A���A<BR>
     * �@@�@@ ������is������true�̏ꍇ<BR>
     * �@@---------------<BR>
     * �@@[��ʐM�p�戵�\�`�F�b�N]<BR>
     * �@@�@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��ʐM�p�戵�s�\�Ƃ���B(*2)<BR>
     * <BR>
     * �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����łȂ��v�̏ꍇ<BR>
     * �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����i�����s�����j�v�A���A<BR>
     * �@@�@@ ������is������true�̏ꍇ<BR>
     * �@@---------------<BR>
     * <BR>
     * �@@[�戵�\�`�F�b�N]<BR>
     * �@@�@@���x�M�p�戵�s�\(*1)�A���A��ʐM�p�戵�s�\(*2)�̏ꍇ�A<BR>
     * �@@�@@�u�M�p����̎戵�s�����v�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00697<BR>
     * �@@----------------------------------------------------<BR>
     * �@@�������ٍ̕ϋ敪�����x�M�p �̏ꍇ��<BR>
     * <BR>
     * �@@�|�������.���x�M�p�����敪���u���x�M�p�����łȂ��v�̏ꍇ�́A<BR>
     * �@@�@@�u���x�M�p�̎戵�s�����v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00696<BR>
     * <BR>
     * �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A<BR>
     * �@@�@@���@@�����̕��X�I�u�W�F�N�g.��ݎؖ����戵���u�戵�s�v�ł���΁A<BR>
     * �@@�@@�u��ݎؖ����̎戵�s�v�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00695<BR>
     * <BR>
     * �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A<BR>
     * �@@�@@���@@������is������true�̏ꍇ�́A<BR>
     * �@@�@@�u�����s��(��ݎؖ���)�v�̗�O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00694<BR>
     * �@@----------------------------------------------------<BR>
     * �@@�������ٍ̕ϋ敪����ʐM�p �̏ꍇ��<BR>
     * <BR>
     * �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����łȂ��v�̏ꍇ�́A<BR>
     * �@@�@@�u��ʐM�p�̎戵�s�����v�̗�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00693<BR>
     * <BR>
     * �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����i�����s�����j�v�A<BR>
     * �@@�@@���@@������is������true�̏ꍇ�́A<BR>
     * �@@�@@�u�����s��(�����s����)�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00692<BR>
     * �@@----------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�j�@@������is������~�`�F�b�N==true�i������~�`�F�b�N������j�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@������~�`�F�b�N�F�S�Ă̒����J�e�S���ɑ΂����{����B<BR>
     * <BR>
     * �@@�������.is�M�p�����K��(�����ٍ̕ϋ敪, �����̒����J�e�S��, ������is����)��<BR>
     * �@@�R�[������B<BR>
     * �@@�߂�l==true�̏ꍇ�A�������.get�M�p�����K����~(�����ٍ̕ϋ敪, �����̒����J�e�S��, <BR>
     * �@@������is����)�ɂ��<BR>
     * �@@��~���̓��e�i���ЋK���^������K���j���擾���A�K���̓��e�ʂɗ�O���X���[����B<BR>
     * �@@�i* ��~���ɂ́h��~���i���ЋK���j�h�A�h��~���h�i������K���j�̂Q��ނ���A<BR>
     * �@@�@@�@@�G���[���b�Z�[�W�𕪂���B�j<BR>
     * �@@�@@�@@�E�h��~��(���ЋK��)�h�̏ꍇ�@@�@@ ���@@�u�����̔�����~��(���ЋK��)�v�̗�O���X���[�B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00014<BR>
     * �@@�@@�@@�E�h��~��(������K��)�h�̏ꍇ�@@���@@�u�����̔�����~��(������K��)�v�̗�O���X���[�B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00015<BR>
     * <BR>
     * �S�j�@@��������I�u�W�F�N�g��ԋp����B
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�B
     * @@param l_product (��������)<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param l_market (�s��)<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@param l_branch (���X)<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�B
     * @@param l_strRepaymentType (�ٍϋ敪)<BR>
     * �@@�@@�@@�ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_orderCateg (�����J�e�S��)<BR>
     * �@@�@@�@@�����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isShort (is����)<BR>
     * �@@�@@�@@�����^�����̃t���O�B<BR>
     * �@@�@@�@@�����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B
     * @@param l_isTradeStopCheck (is������~�`�F�b�N)<BR>
     * �@@�@@�@@������~�`�F�b�N���{�L���t���O�B<BR>
     * �@@�@@�@@�itrue�F�`�F�b�N����Afalse�F�`�F�b�N���Ȃ��j
     * @@return WEB3EquityTradedProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product,
        WEB3GentradeMarket l_market,
        WEB3GentradeBranch l_branch,
        String l_strRepaymentType,
        OrderCategEnum l_orderCateg,
        boolean l_isShort,
        boolean l_isTradeStopCheck)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradedProduct(SubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch, String, OrderCategEnum, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@this.validate�������(��������, �s��)���R�[������
        WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct)this.validateTradedProduct(
            (EqTypeProduct)l_product, l_market);

        // �Q�j�@@�O���،������J�݃`�F�b�N
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.FOREIGN_SECITION.equals(l_tradedProductRow.getListType()))
        {
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = null;
            try
            {
                l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_subAccount.getAccountId());
            }
            catch (NotFoundException l_exp)
            {
                log.error("�ڋq�f�[�^�̎擾�Ɏ��s���܂����B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp);
            }
            if (l_account.isForeignAccountOpen() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

        // �R�j�@@�ȉ��̃`�F�b�N���s���B
        // �@@�@@�@@�����ٍ̕ϋ敪�̒l�ɂ��A�`�F�b�N���e�𕪂���B
        // �R�|�P�j�@@�戵�\�`�F�b�N
        // �@@�@@�@@�@@�@@���戵�\�`�F�b�N�́A�����̒����J�e�S����"�V�K��"�iOPEN_MARGIN�j�̏ꍇ�̂�
        // �@@�@@�@@�@@�@@�����{����B
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
        {
            // �@@�������ٍ̕ϋ敪��DEFAULT�i�w��Ȃ��j �̏ꍇ��
            if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
            {
                // �@@[���x�M�p�戵�\�`�F�b�N]
                // �@@�@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A���x�M�p�戵�s�\�Ƃ���B(*1)
                boolean l_blnIsMarginSysEnable = true;
                if (WEB3MarginSysProductTypeDef.NOT_MARGIN_SYS_PRODUCT.equals(l_tradedProductRow.getMarginSysProductType()))
                {
                    // �@@�|�������.���x�M�p�����敪���u���x�M�p�����łȂ��v
                    l_blnIsMarginSysEnable = false;
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && WEB3DealtDef.CAN_NOT_DEALT.equals(Integer.toString(l_branchRow.getHandlingNotLoanTransStock())))
                {
                    // �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A���A
                    // �@@�@@�����̕��X�I�u�W�F�N�g.��ݎؖ����戵���u�戵�s�v
                    l_blnIsMarginSysEnable = false;
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && l_isShort)
                {
                    // �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A���A
                    // �@@�@@ ������is������true�̏ꍇ
                    l_blnIsMarginSysEnable = false;
                }

                // �@@[��ʐM�p�戵�\�`�F�b�N]
                // �@@�@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��ʐM�p�戵�s�\�Ƃ���B(*2)
                boolean l_blnIsMarginGenEnable = true;
                if (WEB3MarginGenProductTypeDef.NOT_MARGIN_GEN_PRODUCT.equals(l_tradedProductRow.getMarginGenProductType()))
                {
                    // �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����łȂ��v�̏ꍇ
                    l_blnIsMarginGenEnable = false;
                }
                else if (WEB3MarginGenProductTypeDef.MARGIN_GEN_PRODUCT_NO_OPEN_SELL.equals(
                    l_tradedProductRow.getMarginGenProductType())
                    && l_isShort)
                {
                    // �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����i�����s�����j�v�A���A
                    // �@@�@@ ������is������true�̏ꍇ
                    l_blnIsMarginGenEnable = false;
                }

                // �@@[�戵�\�`�F�b�N]
                if (!l_blnIsMarginSysEnable && !l_blnIsMarginGenEnable)
                {
                    // �@@�@@���x�M�p�戵�s�\(*1)�A���A��ʐM�p�戵�s�\(*2)�̏ꍇ�A
                    // �@@�@@�u�M�p����̎戵�s�����v�̗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00697,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
            {
                // �@@�������ٍ̕ϋ敪�����x�M�p �̏ꍇ��
                if (WEB3MarginSysProductTypeDef.NOT_MARGIN_SYS_PRODUCT.equals(l_tradedProductRow.getMarginSysProductType()))
                {
                    // �@@�|�������.���x�M�p�����敪���u���x�M�p�����łȂ��v�̏ꍇ�́A
                    // �@@�@@�u���x�M�p�̎戵�s�����v�̗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00696,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && WEB3DealtDef.CAN_NOT_DEALT.equals(Integer.toString(l_branchRow.getHandlingNotLoanTransStock())))
                {
                    // �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A
                    // �@@�@@���@@�����̕��X�I�u�W�F�N�g.��ݎؖ����戵���u�戵�s�v�ł���΁A
                    // �@@�@@�u��ݎؖ����̎戵�s�v�̗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00695,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN.equals(
                    l_tradedProductRow.getMarginSysProductType())
                    && l_isShort)
                {
                    // �@@�|�������.���x�M�p�����敪���u���x�M�p�����i��ݎؖ����j�v�A
                    // �@@�@@���@@������is������true�̏ꍇ�́A
                    // �@@�@@�u�����s��(��ݎؖ���)�v�̗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00694,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
            {
                // �@@�������ٍ̕ϋ敪����ʐM�p �̏ꍇ��
                if (WEB3MarginGenProductTypeDef.NOT_MARGIN_GEN_PRODUCT.equals(
                    l_tradedProductRow.getMarginGenProductType()))
                {
                    // �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����łȂ��v�̏ꍇ�́A
                    // �@@�@@�u��ʐM�p�̎戵�s�����v�̗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00693,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3MarginGenProductTypeDef.MARGIN_GEN_PRODUCT_NO_OPEN_SELL.equals(
                    l_tradedProductRow.getMarginGenProductType())
                    && l_isShort)
                {
                    // �@@�|�������.��ʐM�p�����敪���u��ʐM�p�����i�����s�����j�v�A
                    // �@@�@@���@@������is������true�̏ꍇ�́A
                    // �@@�@@�u�����s��(�����s����)�v�̗�O���X���[����B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00692,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        if (l_isTradeStopCheck)
        {
            // �@@�������.is�M�p�����K��()
            boolean l_blnIsMarginTradeControl = l_tradedProduct.isMarginTradeControl(
                l_strRepaymentType, l_orderCateg, l_isShort);

            if (l_blnIsMarginTradeControl)
            {
                //�M�p�����K����~���e
                String l_strMarginTradeControlStop =
                    l_tradedProduct.getMarginTradeControlStop(l_strRepaymentType, l_orderCateg, l_isShort);

                if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(l_strMarginTradeControlStop))
                {
                    //�@@�@@�@@�E�h��~��(���ЋK��)�h�̏ꍇ�@@�@@ ���@@�u�����̔�����~��(���ЋK��)�v�̗�O���X���[�B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00014,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(l_strMarginTradeControlStop))
                {
                    // �@@�@@�@@�E�h��~��(������K��)�h�̏ꍇ�@@���@@�u�����̔�����~��(������K��)�v�̗�O���X���[�B
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00015,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);

        return l_tradedProduct;
    }
    
    /**
     * �ivalidate�����R�[�h�i�M�p�j�j�B<BR>
     * <BR>
     * �����R�[�h�̑��݃`�F�b�N�y�є�����~�`�F�b�N�i�M�p�j�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A���������I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateProductCode( )�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�����̖����R�[�h�A�،���ЃR�[�h�ɊY������A���������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̃`�F�b�N���s���B�����ٍ̕ϋ敪�̒l�ɂ��A�`�F�b�N���e�𕪂���B<BR>
     * �@@----------------------------------------------------<BR>
     * �@@�������ٍ̕ϋ敪��DEFAULT�i�w��Ȃ��j �̏ꍇ��<BR>
     * <BR>
     * �@@�|��������.���x�M�p���������~ == �h��~���h�A���A<BR>
     * �@@�@@ ��������.��ʐM�p���������~ == �h��~���h�̏ꍇ�A<BR>
     * �@@�@@�@@(*)��O���X���[����B<BR>
     * <BR>
     * �@@----------------------------------------------------<BR>
     * �@@�������ٍ̕ϋ敪�����x�M�p �̏ꍇ��<BR>
     * <BR>
     * �@@�|��������.���x�M�p���������~ == �h��~���h�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * <BR>
     * �@@----------------------------------------------------<BR>
     * �@@�������ٍ̕ϋ敪����ʐM�p �̏ꍇ��<BR>
     * <BR>
     * �@@�|��������.��ʐM�p���������~ == �h��~���h�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * <BR>
     * �@@----------------------------------------------------<BR>
     * �@@�i*)��~���ɂ́h��~���i���ЋK���j�h�A�h��~���i������K���j�h�̂Q��ނ���A<BR>
     * �@@�@@�@@�G���[���b�Z�[�W�𕪂���B<BR>
     * �@@�@@�@@�E�h��~��(���ЋK��)�h�̏ꍇ�@@�@@ ���@@�u�����̔�����~��(���ЋK��)�v�̗�O���X���[�B<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00014<BR>
     * <BR>
     * �@@�@@�@@�E�h��~��(������K��)�h�̏ꍇ�@@���@@�u�����̔�����~��(������K��)�v�̗�O���X���[�B<BR>
     * <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00015<BR>
     *       �E�h��~��(������K��)�h���� �h��~��(���ЋK��)�h�̏ꍇ�@@���@@<BR>
     *        �u�����̔�����~��(������K��/���ЋK��)�v�̗�O���X���[�B<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00700<BR>
     * <BR>
     * �R�j�@@���������I�u�W�F�N�g��ԋp����B
     * @@param l_strProductCode �����R�[�h
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@return WEB3EquityProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode, 
        String l_strInstitutionCode, 
        String l_strRepaymentType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode,l_strRepaymentType)";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
            //�g���v���_�N�g�}�l�[�W��
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
            Institution l_institution = l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);
        
            //�����̖����R�[�h�A�،���ЃR�[�h�ɊY������A���������I�u�W�F�N�g�𐶐�����B           
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_productManager.getProduct(l_institution, l_strProductCode);
            
            //����Row�I�u�W�F�N�g
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
             
            if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
            {
                // �������ٍ̕ϋ敪��DEFAULT�i�w��Ȃ��j �̏ꍇ��
                if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //��������.���x�M�p���������~  == �h��~��(������K��)�h�̏ꍇ
                    //��������.��ʐM�p���������~  == �h��~��(������K��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00015,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //��������.���x�M�p���������~ == �h��~��(���ЋK��)�h�̏ꍇ
                    //��������.��ʐM�p���������~ == �h��~��(���ЋK��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00014,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //��������.���x�M�p���������~ == �h��~��(������K��)�h�̏ꍇ
                    //��������.��ʐM�p���������~ == �h��~��(���ЋK��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00700,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginSysTradeStop())
                    && WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //��������.���x�M�p���������~ == �h��~��(���ЋK��)�h�̏ꍇ
                    //��������.��ʐM�p���������~ == �h��~��(������K��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00700,STR_METHOD_NAME);
                }                                
                
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
            {
                //�������ٍ̕ϋ敪�����x�M�p �̏ꍇ�� 
                if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginSysTradeStop()))
                {
                    //�h��~��(������K��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00015,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginSysTradeStop()))
                {
                    //�h��~��(���ЋK��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00014,STR_METHOD_NAME);
                }
            }
            else if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
            {
                //�������ٍ̕ϋ敪����ʐM�p �̏ꍇ�� 
                if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //�h��~��(������K��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00015,STR_METHOD_NAME);
                }
                else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals("" + l_productRow.getMarginGenTradeStop()))
                {
                    //�h��~��(���ЋK��)�h�̏ꍇ
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00014,STR_METHOD_NAME);
                }                
            }
            log.exiting(STR_METHOD_NAME);
            return l_product;            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301,STR_METHOD_NAME);
        }       

    }
    
    /**
     * �ivalidate�戵�\�s��i�M�p�j�j�B<BR>
     * <BR>
     * ��Е��X���A�M�p����̎w��ٍϋ敪�E�ٍϊ�����<BR>
     * �戵�\�s�ꂩ���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�i���X�s��ٍϕʁj�戵�����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * [�i���X�s��ٍϕʁj�戵�����E�R���X�g���N�^����]<BR>
     * �،���ЃR�[�h�F�@@�����̕��X.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F�@@�����̕��X.���X�R�[�h<BR>
     * �s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * �ٍϋ敪�F�@@�����ٍ̕ϋ敪<BR>
     * �ٍϊ����l�F�@@�����ٍ̕ϊ����l<BR>
     * <BR>
     * �Q�j�@@���������C���X�^���X.is�戵�\( )�ɂāA�戵�\�s�ꂩ�𔻒肷��B<BR>
     * �@@�@@�@@false���Ԃ��ꂽ�ꍇ�́A�戵�\�s��łȂ��Ɣ��肵�A<BR>
     * �@@�@@�@@�u�戵�\�s��`�F�b�N�G���[(���X�E�ٍ�)�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00703<BR>
     * <BR>
     * �R�j�@@JASDAQ�戵�`�F�b�N<BR>
     * �@@�|�����̎������.validateJASDAQ�����戵�\( )���R�[������B
     * @@param l_branch (���X)<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�i�ڋq�̎���X�j
     * @@param l_tradedProduct (�������)<BR>
     * �@@�@@�@@������������I�u�W�F�N�g
     * @@param l_strMarketCode (�s��R�[�h)<BR>
     * �@@�@@�@@�s��R�[�h�B
     * @@param l_strRepaymentType (�ٍϋ敪)<BR>
     * �@@�@@�@@�ٍϋ敪�B<BR>
     * �@@�@@�@@�@@0�FDEFAULT(�w��Ȃ�)<BR>
     * �@@�@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@�@@2�F��ʐM�p
     * @@param l_dblRepaymentNum (�ٍϊ����l)<BR>
     * �@@�@@�@@�ٍϊ����l
     * @@throws WEB3BaseException
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch, 
        WEB3EquityTradedProduct l_tradedProduct, 
        String l_strMarketCode, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateHandlingMarket(WEB3GentradeBranch, WEB3EquityTradedProduct, String, String, double)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�i���X�s��ٍϕʁj�戵�����̃C���X�^���X�𐶐�����B
        WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
            new WEB3GentradeBranchMarketRepayDealtCond(l_branch.getInstitution().getInstitutionCode(),
            l_branch.getBranchCode(),
            l_strMarketCode,
            l_strRepaymentType,
            l_dblRepaymentNum);

        //  �Q�j�@@���������C���X�^���X.is�戵�\( )�ɂāA�戵�\�s�ꂩ�𔻒肷��B
        if (!l_branchMarketRepayDealtCond.isHandlingPossible())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00703,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �R�j�@@JASDAQ�戵�`�F�b�N
        l_tradedProduct.validateJASDAQProductHandling(l_branch);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate���������j�B<BR>
     * <BR>
     * ���������̃`�F�b�N���s���B<BR>
     * �@@�|�l�i�����̃`�F�b�N<BR>
     * �@@�|���s�����̃`�F�b�N<BR>
     * �@@�|���������̃`�F�b�N<BR>
     * �@@�|�o����܂Œ����戵�̃`�F�b�N<BR>
     * �@@�|�o����܂Œ����L�������̃`�F�b�N<BR>
     * �@@�|�����J�z�X�L�b�v�����̃`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����jvalidate���������v�Q�ƁB<BR>
     * <BR>
     * �P�D�����ꂩ�̃`�F�b�N��false���Ԃ��ꂽ�ꍇ�́A��O��throw����B <BR>
     * ���`�F�b�N�̎�ޖ��ɗ�O�N���X�𕪂���B <BR>
     * �@@�E�l�i�����̃`�F�b�N�@@���@@�u�l�i�����̃`�F�b�N�G���[�v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_01342
     * <BR>
     * �@@�E���s�����̃`�F�b�N�@@���@@�u���s�����̃`�F�b�N�G���[�v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00150<BR>
     * <BR>
     * �@@�E���������̃`�F�b�N�@@���@@�u���������̃`�F�b�N�G���[�v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00151<BR>
     * <BR>
     * �@@�E�o����܂Œ����戵�̃`�F�b�N�@@�� <BR>
     * �@@�@@�@@�u�o����܂Œ����戵�̃`�F�b�N�G���[�v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00704<BR>
     * <BR>
     * �@@�E�o����܂Œ����L�������̃`�F�b�N�@@�� <BR>
     * �@@�@@�@@�u�o����܂Œ��������L�������̃`�F�b�N�G���[�v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00704<BR>
     * �@@�E�����t���ŏI����̎������w��s�B�@@�� <BR>
     * �@@�@@�@@�u�����t���ŏI����̎������w��s�B�v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_02836<BR>
     * <BR>
     * �Q�D�ȉ��̃`�F�b�N��true���Ԃ��ꂽ�ꍇ�́A�A��O��throw����B <BR>
     * ���`�F�b�N�̎�ޖ��ɗ�O�N���X�𕪂���B <BR>
     * �@@�E�����J�z�X�L�b�v�����̃`�F�b�N�@@�� <BR>
     * �@@�@@�@@�u�����J�z�s�̖����v�̗�O <BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00684<BR>
     * �@@�@@���iis�J�z�X�L�b�v����() == true�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�o����܂Œ�����~�����ł��邽�߁B
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j�B
     * @@param l_lngOrderUnitId (�����P��ID)<BR>
     * �@@�@@�@@�����P��ID<BR>
     * <BR>
     * �@@�@@�@@�V�K�����̏ꍇ��0���Z�b�g�B
     * @@param l_tradedProduct (�������)<BR>
     * �@@�@@�@@��������I�u�W�F�N�g�B
     * @@param l_datOrderBizDate (������������)<BR>
     * �@@�@@�@@�������̔�����<BR>
     * <BR>
     * �@@�@@�@@�o����܂Œ����̒����̏ꍇ�̂ݐݒ�B
     * @@param l_datExpirationDate (����������)<BR>
     * �@@�@@�@@�����������B
     * @@param l_strOrderConditionType (��������)<BR>
     * �@@�@@�@@��������<BR>
     * �@@�@@�@@0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l
     * @@param l_executionCondition (���s����)<BR>
     * �@@�@@�@@���s�����B
     * @@param l_isCarriedOrder (is�o����܂Œ���)<BR>
     * �@@�@@�@@�o����܂Œ����̏ꍇtrue�A�ȊOfalse�B
     * @@param l_strMarginTradeType (�M�p����敪)<BR>
     * �@@�@@�@@0�FDAFAULT�i�M�p����ȊO�j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_strPriceConditionType (�l�i����)<BR>
     * �@@�@@�@@Web�V�̒l�i�����B�i������̃R�[�h�̌n�Ɠ���j<BR>
     * �@@�@@�@@0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * �@@�@@�@@1�F�@@���ݒl�w�l����<BR>
     * �@@�@@�@@3�F�@@�D��w�l����<BR>
     * �@@�@@�@@5�F�@@���s�c���w�l����<BR>
     * �@@�@@�@@7�F�@@���s�c���������
     * @@param l_strMarketCode (�s��R�[�h)<BR>
     * �@@�@@�@@0�F  DEFAULT<BR>
     * �@@�@@�@@1�F  ����<BR>
     * �@@�@@�@@2�F  ���<BR>
     * �@@�@@�@@3�F  ���É�<BR>
     * �@@�@@�@@6�F  ����<BR>
     * �@@�@@�@@8�F  �D�y<BR>
     * �@@�@@�@@9�F  NNM<BR>
     * �@@�@@�@@10�F JASDAQ
     * @@throws WEB3BaseException
     */
    public void validateOrderCondition(
        WEB3GentradeSubAccount l_subAccount, 
        long l_lngOrderUnitId, 
        WEB3EquityTradedProduct l_tradedProduct, 
        Date l_datOrderBizDate, 
        Date l_datExpirationDate, 
        String l_strOrderConditionType, 
        EqTypeExecutionConditionType l_executionCondition, 
        boolean l_isCarriedOrder, 
        String l_strMarginTradeType,
        String l_strPriceConditionType,
        String l_strMarketCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCondition(WEB3GentradeSubAccount, long, WEB3EquityTradedProduct, Date, Date, String, EqTypeExecutionConditionType, boolean, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // 1.1. �戵�\��������()
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);

        // ���X.is�����t���ŏI���`�F�b�N()==true�̏ꍇ
        // ���X���擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();

        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        if (l_branch.isEqtypeFinalDayWithRight())
        {
            Institution l_institution = l_tradedProduct.getInstitution();
            String l_strProductCode = l_tradedProduct.getProductCode();
            //get�����t���ŏI��()
            WEB3EquityProduct l_product = null;
            try
            {
                l_product =
                    (WEB3EquityProduct)l_equityProductManager.getProduct(
                        l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            Date l_datRightCondOrderLastDay = l_product.getRightCondOrderEndDay();

            // ������
            Date l_datOrderBizDat = null;
            if (l_datOrderBizDate != null)
            {
                // �p�����[�^������������
                l_datOrderBizDat = l_datOrderBizDate;
            }
            else
            {
                // ���ݓ������Z�o����������
                l_datOrderBizDat = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            // ������(*) <= ��������.get�����t���ŏI���̏ꍇ
            if (WEB3DateUtility.compare(l_datOrderBizDat, l_datRightCondOrderLastDay) <= 0)
            {
                //set����ŏI��
                l_handlingOrderCond.setTradingEndDate(l_datRightCondOrderLastDay);
            }
        }

        // 1.2. is�戵�\�l�i����()
        if (!l_handlingOrderCond.isHandlingPriceCond(l_strPriceConditionType))
        {
            log.debug("!l_handlingOrderCond.isHandlingPossiblePriceConditionType(l_strPriceConditionType)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01342,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 1.3. is�戵�\���s����()
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionCondition))
        {
            log.debug("!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionCondition)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // 1.4. is�戵�\��������()
        if (!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderConditionType))
        {
            log.debug("!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderConditionType)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                this.getClass().getName() + STR_METHOD_NAME);
        }
                    
        // validate���ꎷ�s�����戵��~()
        validateTriggerOrderStop(
            l_subAccount,
            l_lngOrderUnitId,
            l_tradedProduct,
            l_strOrderConditionType,
            l_strMarginTradeType,
            l_strMarketCode);

        // 1.5. �o����܂Œ����̏ꍇ
        if (l_isCarriedOrder)
        {
            log.debug("l_isCarriedOrder=true");
            // 1.5.1. is�o����܂Œ����戵�\()
            if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleHandling())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00704,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            // 1.5.2. �p�����[�^.������������ != null�̏ꍇ
            if (l_datOrderBizDate != null)
            {
                log.debug("l_datOrderBizDate != null"); 
                // 1.5.2.1. is�o����܂Œ����\��()
                log.debug("l_datExpirationDate="+l_datExpirationDate);
                log.debug("l_datOrderBizDate="+l_datOrderBizDate);
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate, l_datOrderBizDate))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            // 1.5.3. �p�����[�^.������������ == null�̏ꍇ
            else
            {
                log.debug("l_datOrderBizDate == null"); 
                // 1.5.3.1. is�o����܂Œ����\��()
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + STR_METHOD_NAME);
                }            
            }

            // 1.5.4. is�J�z�X�L�b�v����()
            if (l_lngOrderUnitId == 0)
            {
                log.debug("l_lngOrderUnitId == 0");
                if (l_tradedProduct.isTransferSkipProduct())
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00684,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            // get�o����܂Œ����ŏI��<����ŏI���l��>(������������ : Date)
            Date l_datEndDateConsidering =
                l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datOrderBizDate);

            // ������������get�o����܂Œ����ŏI��<����ŏI���l��>�̖߂�l���r����
            int l_intCompareToDay = WEB3DateUtility.compareToDay(l_datExpirationDate, l_datEndDateConsidering);

            // ���������� > get�o����܂Œ����ŏI��<����ŏI���l��>�̖߂�l�̏ꍇ
            if (l_intCompareToDay > 0)
            {
                log.debug("�����t���ŏI����̎������w��s�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����t���ŏI����̎������w��s�B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �iis�󔄂�K���j�B<BR>
     * <BR>
     * �󔄂�K���`�F�b�N�A�y�щ��i�K���Ώێ��̃`�F�b�N���s���B<BR>
     * ��DB�ݒ�_���t�H���_�̎Q�l�����u�󔄂�K���`�F�b�N.xls�v�����킹�ĎQ�Ƃ̂��ƁB<BR>
     * -------------------<BR>
     * ���󔄂�K���`�F�b�N<BR>
     * �@@�|�i�����s�̕��X�̏ꍇ�j�F��������i�����{�s��j�̋󔄂�i�����j���ʂ�<BR>
     * �@@�@@�@@�󔄂�K�����ʂ𒴂��Ă���ꍇ�A�G���[�Ƃ���O��throw����B<BR>
     * �@@�@@�@@�����Ă��Ȃ��ꍇ�́A�󔄂�K���`�F�b�NOK�Ƃ�false�i�󔄂�K���ΏۊO�j��Ԃ��B<BR>
     * �@@�|�i�����̕��X�̏ꍇ�j�F��������i�����{�s��j�̋󔄂�i�����j���ʂ�<BR>
     * �@@�@@�@@�󔄂�K�����ʂ𒴂��Ă���ꍇ�A���s�w��s���̒ǉ��`�F�b�N���s���B<BR>
     * �@@�@@�@@�ǉ��`�F�b�N�ň�ł��G���[�����������ꍇ�́A�G���[�Ƃ���O��throw����B<BR>
     * �@@�@@�@@�ǉ��`�F�b�N���ʂ��S��OK�̏ꍇ�́Atrue�i�󔄂�K���Ώہj��Ԃ��B<BR>
     * �@@�|�K�i�@@�֓����Ƃ̏ꍇ�A��ɋ󔄂�K�����ʂ𒴂��Ă���ꍇ�Ɠ��������Ƃ���B<BR>
     * -------------------<BR>
     * �����i�K���Ώێ��`�F�b�N<BR>
     * �@@�|���i�K���Ώۂ̏ꍇ�A<BR>
     * �@@�@@�@@���s�w��s���̒ǉ��`�F�b�N���s���B<BR>
     * �@@�@@�@@�ǉ��`�F�b�N�ň�ł��G���[�����������ꍇ�́A�G���[�Ƃ���O��throw����B<BR>
     * �@@�@@�@@�ǉ��`�F�b�N���ʂ��S��OK�̏ꍇ�́Atrue�i�󔄂�K���Ώہj��Ԃ��B<BR>
     * -------------------<BR>
     * <BR>
     * �P�j�@@�����̒�����ʁ��h�V�K���������h�iMARGIN_SHORT�j �̏ꍇ�́A<BR>
     * �@@�@@�@@���������ɂ��̂܂�return����B<BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�����̕⏕����.get����X( )�ŁA���X�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g��<BR>
     * �@@�@@�@@�@@�󔄂�K�����{�`�ԋ敪<BR>
     * �@@�@@�@@�@@�󔄂�K�����x�P��<BR>
     * �@@�@@�@@�@@�󔄂�K���K�p�͈͎���<BR>
     * �@@�@@�@@�@@�K�i�@@�֓����Ɗm�F���{�敪<BR>
     * �@@�@@�@@���擾����B<BR>
     * <BR>
     * �@@�@@�@@�ȉ��A�󔄂�K�����{�`�ԋ敪��"�����s��"�̏ꍇ�́u�����s�̕��X�v�A<BR>
     * �@@�@@�@@�󔄂�K�����{�`�ԋ敪��"������"�̏ꍇ�́u�����̕��X�v�ƋL�ڂ���B<BR>
     * <BR>
     * �R�j�@@�󔄂�K���`�F�b�N���s���B<BR>
     * �@@�@@�@@�ڋq����ʓ����Ƃ̏ꍇ�A<BR>
     * �@@�@@�@@�󔄂�K���`�F�b�N�ΏۂƂȂ钍���̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * <BR>
     * �R�|�P�j�@@�󔄂萔�ʌv����@@���擾����<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�󔄂�K������()���R�[������B <BR>
     * <BR>
     * �R�|�Q�j�@@�擾�����󔄂萔�ʌv����@@��"�J�ǈ����Ōv��"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�ȉ��̏����Œ����P�ʃI�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�@@�@@�������A�s�ꂪ��v����V�K���������̂����A�󔄂�K���K�p�͈͎��ԓ��̒������擾����B<BR>
     * �@@�@@�@@�@@�@@���i�������������̋t�w�l�����͏��O����B�j<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@���L�������̒����P�ʃI�u�W�F�N�g�擾�i�s��J�ǎ��ԑсj��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j����ID�i"product_id"�j�A�s��ID�i"market_id"�j�A������ʁi"order_type"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�������i"biz_date"�j�A��������("order_condition_type")�A<BR>
     * �@@�@@�@@�@@�@@�@@�쐬���t�i"created_timestamp"�j�A�t�w�l��������("stop_order_ordered_timestamp")���ȉ��̂悤�Ɏw�肷��B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@��"product_id = ? and market_id = ? and order_type = ? and biz_date = ?"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�Q�j�Ŏ擾�������X.�󔄂�K���K�p�͈͎��ԁ�ALL9 �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@ + " and ( order_condition_type != ? or stop_order_ordered_timestamp is not null )"<BR>
     * �@@�@@�@@�@@�@@�@@�����łȂ��ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@ + " and ( order_condition_type != ? and stop_order_ordered_timestamp is null and created_timestamp >= ?"
     * �@@�@@�@@�@@�@@�@@�@@�@@ + " or stop_order_ordered_timestamp is not null and stop_order_ordered_timestamp >= ? )"
     * <BR>
     * �@@�@@�@@�@@�@@�@@�܂��A�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j����ID�F�@@�����̎������.����ID�̒l���A���������f�[�^�R���e�i�ɂ��̂܂ܐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�F�@@�����̎������.�s��ID�̒l���A���������f�[�^�R���e�i�ɂ��̂܂ܐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@������ʁF�@@�����̒�����ʂ�intValue��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�������F�@@������ԊǗ�.get������(void) �̖߂�l��ݒ肷��B <BR>
     * �@@�@@�@@�@@�@@�@@��������1�F�@@�t�w�l����<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�Q�j�Ŏ擾�������X.�󔄂�K���K�p�͈͎��ԁ�ALL9 �ꍇ�A�ȉ����ݒ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n����1�F�@@GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̓�������A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P�j�Ŏ擾�������X.�󔄂�K���K�p�͈͎��� �̕��iminute�j�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���Z�������� ��ݒ肷��B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�K�p�J�n����2�F�@@�K�p�J�n����1�Ɠ����l<BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �R�|�R�j�@@�擾�����󔄂萔�ʌv����@@��"�ǈ����Ōv��"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�J�ǌ�Ɏs��ɔ�������邱�ƂɂȂ�A�V�K���������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B <BR>
     * �@@�@@�@@�@@�@@�������A�s�ꂪ��v����V�K���������̂����A�J�ǌ�Ɏs��ɔ��������\���̂��钍�����擾����B <BR>
     * �@@�@@�@@�@@�@@���i�����������t�w�l�����Ƃ��ČJ��z����钍���͏��O����B�j<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@���L�������̒����P�ʃI�u�W�F�N�g�擾�i�s��ǎ��ԑсj��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j����ID�i"product_id"�j�A�s��ID�i"market_id"�j�A������ʁi"order_type"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁi"order_open_status"�j�A�����������t�i"expiration_date"�j�A���������i"order_condition_type"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�t�w�l��������("stop_order_ordered_timestamp")��and�����Ŏw�肷��B <BR>
     * �@@�@@�@@�@@�@@�@@��"product_id = ? and market_id = ? and order_type = ? and order_open_status = ? and expiration_date => ?"<BR>
     * �@@�@@�@@�@@�@@�@@�{" and not (order_condition_type = ? and stop_order_ordered_timestamp is null)" <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�܂��A�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����^�t�w�l���������j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j����ID�F�@@�����̎������.����ID�̒l���A���������f�[�^�R���e�i�ɂ��̂܂ܐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�F�@@�����̎������.�s��ID�̒l���A���������f�[�^�R���e�i�ɂ��̂܂ܐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@������ʁF�@@OrderTypeEnum.�h�V�K���������h�iMARGIN_SHORT�j��intValue��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�����������t�F�@@������ԊǗ�.get������(void) �̖߂�l��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@���������F�@@�t�w�l����<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �R�|�S�j�@@�擾�����󔄂萔�ʌv����@@��"�x�e���ԑш����Ōv��"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���ɐV���Ɏs��ɔ�������邱�ƂɂȂ�A�V�K���������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * �@@�@@�@@�@@�@@�������A�s�ꂪ��v����V�K���������̂����A�J�ǌ�ɐV���Ɏs��ɔ��������\���̂��钍�����擾����B<BR>
     * �@@�@@�@@�@@�@@���i�������������̋t�w�l�����͏��O����B�j<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@���L�������̒����P�ʃI�u�W�F�N�g�擾�i�x�e���ԑтɓo�^�������j��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j����ID�i"product_id"�j�A�s��ID�i"market_id"�j�A������ʁi"order_type"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁi"order_open_status"�j�A�쐬���t�i"created_timestamp"�j�A��������("order_condition_type")�A<BR>
     * �@@�@@�@@�@@�@@�@@�t�w�l��������("stop_order_ordered_timestamp")��and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"product_id = ? and market_id = ? and order_type = ? and order_open_status = ? and created_timestamp => ?"<BR>
     * �@@�@@�@@�@@�@@�@@�{" and not (order_condition_type = ? and stop_order_ordered_timestamp is null)"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�܂��A�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����^�t�w�l���������j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j����ID�F�@@�����̎������.����ID�̒l���A���������f�[�^�R���e�i�ɂ��̂܂ܐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�F�@@�����̎������.�s��ID�̒l���A���������f�[�^�R���e�i�ɂ��̂܂ܐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@������ʁF�@@OrderTypeEnum.�h�V�K���������h�iMARGIN_SHORT�j��intValue��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�쐬���t�F�@@������ԊǗ�.get������(void)��YYYYMMDD<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�R�|�P�j�Ŏ擾�����󔄂�K�����ԃe�[�u��.�J�n���ԁiHHMMSS�j��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@���������F�@@�t�w�l����<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�R�j�Ŏ擾�����S�Ă̒����P�ʃI�u�W�F�N�g����󔄂�K�����ʂ��W�v����B <BR>
     * <BR>
     * �S�|�P�j�@@�@@�����P�ʂ��J��z���������̏ꍇ�i��������������ԊǗ�.get������(void) �j<BR>
     * �@@�@@�P�j�@@�s�ꖢ���M�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�������ʂ��W�v����B<BR>
     * �@@�@@�Q�j�@@�s�ꑗ�M�ς݂̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@����萔�ʁi�s�ꂩ��̊m�F�ς̐��ʁ|��萔�ʁj���W�v����B<BR>
     * <BR>
     * �S�|�Q�j�@@�擾�����󔄂萔�ʌv����@@��"�x�e���ԑш����Ōv��"�̏ꍇ<BR>
     * �@@�@@�S�|�P�j�@@�ɓ����B<BR>
     * <BR>
     * �S�|�R�j�@@�@@��L�ȊO�̏ꍇ�i������ԑс^���������j<BR>
     * �@@�@@�@@�@@�@@�@@���������ʂ��W�v����B<BR>
     * <BR>
     * �T�j�@@�w������̋K�����ʂ��v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�K������ �� �����̎������.�����P�� �~ �Q�j�Ŏ擾�������X.�󔄂�K�����x�P��<BR>
     * <BR>
     * �U�j�@@�ڋq�����ɂ��A���i�K���`�F�b�N�Ώۂ��ǂ����̔�����s���B<BR>
     * <BR>
     * �@@�@@�@@�����̕⏕����.getMainAccount( ).�K�i�@@�֓����Ƌ敪���K�i�@@�֓����Ƃł���<BR>
     * �@@�@@�@@�@@�@@�F�K�i�@@�֓����ƂƔ��肷��B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@�F��ʓ����ƂƔ��肷��B<BR>
     * <BR>
     * �U�|�P�j�@@�ڋq����ʓ����Ƃ̏ꍇ<BR>
     * <BR>
     * �@@�@@�E�i�i�S�j�ŏW�v��������萔�� �{ �����̊����j <= �T�j�Ōv�Z�����K�����ʁj�̏ꍇ�́A<BR>
     * �@@�@@�@@�󔄂�K���`�F�b�NOK�Ƃ�false�i�󔄂�K���ΏۊO�j��Ԃ��B<BR>
     * �@@�@@�@@����ʓ����Ƃ̏ꍇ�A���ʂ����x���̏ꍇ�͉��i�K���ΏۂƂȂ炸�A<BR>
     * �@@�@@�@@�����i�K�����`�F�b�N���s�v�B<BR>
     * <BR>
     * �@@�@@�E�i�i�S�j�ŏW�v��������萔�� �{ �����̊����j > �T�j�Ōv�Z�����K�����ʁj�ɊY������ꍇ�A<BR>
     * �@@�@@�@@�V�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �U�|�Q�j�@@�ڋq���K�i�@@�֓����Ƃ̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�������łV�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �V�j�@@�����^�s�̔���A�y�ђ����̏ꍇ�͉��i�K���Ώێ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �V�|�P�j�@@�����s�̕��X�̏ꍇ�́u�w������̋󔄂�K�����ʂ𒴉߁v�̗�O��throw����B<BR>
     * <BR>
     * �V�|�Q�j�@@�����̕��X�̏ꍇ<BR>
     * <BR>
     * �@@�@@�E�Q�j�Ŏ擾�����擾�������X.�K�i�@@�֓����Ɗm�F���{�敪�����{���� �̏ꍇ�A<BR>
     * �@@�@@�@@�ȉ��̂����ꂩ��ɂł��Y�������ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�i�P�j�����̒l�i������"�w��Ȃ�"�A���� ������is���s��true�i���s�����j<BR>
     * �@@�@@�@@�@@�i�Q�j�����̎��s������"�s�o���������s"<BR>
     * �@@�@@�@@�@@�i�R�j�����̒l�i�������i"�D��l�w�l"�A"���s�c���w�l"�A"���s�c�����"�j��<BR>
     * �@@�@@�@@�@@�@@�@@�����ꂩ�ɊY������<BR>
     * <BR>
     * �@@�@@�@@�@@�G���[�R�[�h�͈ȉ��̂R�ʂ�ɕ�����B<BR>
     * �@@�@@�@@�@@�i�P�j�@@�u���s�w��s�i�󔄂�K�����ʒ��ߎ��j�v<BR>
     * �@@�@@�@@�@@�i�Q�j�@@�u���s������"�s�o���������s"�w��s�i�󔄂�K�����ʒ��ߎ��j�v<BR>
     * �@@�@@�@@�@@�i�R�j�@@�u�l�i������"�D��l�w�l"�A"���s�c���w�l"�A"���s�c�����"�w��s��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�󔄂�K�����ʒ��ߎ��j�v<BR>
     * <BR>
     * �@@�@@�Etrue�i�󔄂�K���Ώہj��Ԃ��B<BR>
     * <BR>
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j�B
     * @@param l_tradedProduct (�������)<BR>
     * �@@�@@�@@��������I�u�W�F�N�g�B
     * @@param l_dblQuantity (����)<BR>
     * �@@�@@�@@���������B
     * @@param l_orderType (�������)<BR>
     * �@@�@@�@@������ʁB�ixTrade��OrderTypeEnum�ɂĒ�`�j
     * @@param isMarketOrder (is���s)<BR>
     * �@@�@@�@@���s�����̏ꍇ�Atrue�B�ȊO�Afalse�B
     * @@param l_execCondType (���s����)<BR>
     * �@@�@@�@@���s�����B
     * @@param l_strPriceConditionType (�l�i����)<BR>
     * �@@�@@�@@�l�i�����B
     * @@param l_changeOrderUnit<BR>
     *       �����Ώے����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public boolean isShortSellingRestraint(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityTradedProduct l_tradedProduct, 
        double l_dblQuantity, 
        OrderTypeEnum l_orderType,
        boolean isMarketOrder,
        EqTypeExecutionConditionType l_execCondType,
        String l_strPriceConditionType,
        EqtypeOrderUnitRow l_changeOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isShortSellingRestraint(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double, OrderTypeEnum, boolean, EqTypeExecutionConditionType, String)";
        log.entering(STR_METHOD_NAME);

        //  �P�j�@@�����̒�����ʁ��h�V�K���������h�iMARGIN_SHORT�j �̏ꍇ�́A
        // �@@�@@�@@���������ɂ��̂܂�return����B
        if (!OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            return false;
        }

        // �Q�j�@@�����̕⏕����.get����X( )�ŁA���X�I�u�W�F�N�g���擾
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();      
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        // �󔄂�K�����{�`�ԋ敪
        String l_strShortMarginRestrainDiv = l_branchRow.getShortMarginRestrainDiv();
        // �󔄂�K�����x�P�� 
        double l_dblShortMarginRestrainUnit = l_branchRow.getShortMarginRestrainUnit();
        // �󔄂�K���K�p�͈͎��� 
        int l_intShortSellOrderValidMinute = l_branchRow.getShortSellOrderValidMinute();
        // �K�i�@@�֓����Ɗm�F���{�敪
        String l_strQualifiedInvestorConfirmDiv = l_branchRow.getQualifiedInvestorConfirmDiv();

        // �R�j�@@�󔄂�K���`�F�b�N���s���B
        // �@@�@@�@@��ʓ����Ƃ̏ꍇ�A�󔄂萧���`�F�b�N�ΏۂƂȂ钍���P�ʃI�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        MainAccountRow l_accountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        if (WEB3QualifiedInstInvestorDivDef.QUALIFIED_INSTITUTIONAL_INVESTOR.equals(
            l_accountRow.getQualifiedInstInvestorDiv()) == false)
        {
			// �󔄂萔�ʌv����@@���擾����
			ShortSellingRestraintTimeRow l_shortSellingResTimeRow =
				l_orderManager.getShortSellingRestraintTime();

			String l_strShortSelCountMethodDiv =
				l_shortSellingResTimeRow.getShortSellingCountMethodDiv();

			Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
                        
			StringBuffer l_sbWhere = new StringBuffer(
				" product_id = ? and market_id = ? and order_type = ?");
            Object[] l_objWheres = null;
            List l_lisWheres = new ArrayList();
            List l_lisOrderUnits;
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
    
            // �擾�����󔄂萔�ʌv����@@ == "�J�ǈ����Ōv��"�̏ꍇ
            if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_OPEN.equals(l_strShortSelCountMethodDiv))
            {
                l_sbWhere.append(" and biz_date = ?");
                
                l_lisWheres.add(String.valueOf(l_tradedProduct.getProduct().getProductId()));
                l_lisWheres.add(String.valueOf(l_tradedProduct.getMarket().getMarketId()));
                l_lisWheres.add(l_orderType);
                l_lisWheres.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
                l_lisWheres.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
    
                // �擾�������X.�󔄂�K���K�p�͈͎��ԁ�ALL9�̏ꍇ
                if (l_intShortSellOrderValidMinute == 99999)
                {
                    l_sbWhere.append(" and (order_condition_type != ? or stop_order_ordered_timestamp is not null)");
                }
                else
                {
                    l_sbWhere.append(" and (( order_condition_type != ? and stop_order_ordered_timestamp is null and created_timestamp >= ? )");
                    l_sbWhere.append(" or (stop_order_ordered_timestamp is not null and stop_order_ordered_timestamp >= ? ))");
                    
                    Date l_datFrom = WEB3DateUtility.addMinute(l_tsSystemTime, -l_intShortSellOrderValidMinute);
                    l_lisWheres.add(new Timestamp(l_datFrom.getTime()));
                    l_lisWheres.add(new Timestamp(l_datFrom.getTime()));
                }
                
                //�����̒����̏ꍇ�͒����Ώے��������O����B
                if (l_changeOrderUnit != null)
                {
                    //and �����P��ID <> ?
                    l_sbWhere.append(" and order_unit_id <> ? "); 
                    //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
                    l_lisWheres.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
                }
                
                log.debug("l_strWhere=" + l_sbWhere.toString());  
                l_objWheres = new Object[l_lisWheres.size()];
                log.debug("String.valueOf(l_tradedProduct.getProduct().getProductId())="
                    + String.valueOf(l_tradedProduct.getProduct().getProductId())); 
                log.debug("String.valueOf(l_tradedProduct.getMarket().getMarketId()"
                    + String.valueOf(l_tradedProduct.getMarket().getMarketId()));
                log.debug("String.valueOf(l_orderType.intValue())=" + String.valueOf(l_orderType.intValue())); 
                log.debug("String.valueOf(OrderOpenStatusEnum.OPEN.intValue())="
                    + String.valueOf(OrderOpenStatusEnum.OPEN.intValue())); 
                log.debug("ll_lisWheres.size()=" + l_lisWheres.size()); 
    
                l_lisWheres.toArray(l_objWheres);
    
                l_lisOrderUnits = l_orderManager.getOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_sbWhere.toString(),
                    l_objWheres,
                    null);
                log.debug("l_lisOrderUnits=" + l_lisOrderUnits);
            }
            // �擾�����󔄂萔�ʌv����@@ == "�ǈ����Ōv��"�̏ꍇ
			else if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_CLOSE.equals(l_strShortSelCountMethodDiv))
            {
                l_sbWhere.append(" and order_open_status = ?");
                l_sbWhere.append(" and expiration_date >=? ");
                l_sbWhere.append(" and not (order_condition_type = ?");
                l_sbWhere.append(" and stop_order_ordered_timestamp is null)");
    
                l_lisWheres.add(String.valueOf(l_tradedProduct.getProduct().getProductId()));
                l_lisWheres.add(String.valueOf(l_tradedProduct.getMarket().getMarketId()));
                l_lisWheres.add(OrderTypeEnum.MARGIN_SHORT);
                l_lisWheres.add(OrderOpenStatusEnum.OPEN);
                l_lisWheres.add(new Timestamp(l_datBizDate.getTime()));
                l_lisWheres.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
    
                //�����̒����̏ꍇ�͒����Ώے��������O����B
                if (l_changeOrderUnit != null)
                {
                    //and �����P��ID <> ?
                    l_sbWhere.append(" and order_unit_id <> ? "); 
                    //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
                    l_lisWheres.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
                }
    
                l_objWheres = new Object[l_lisWheres.size()];
                l_lisWheres.toArray(l_objWheres);
    
                l_lisOrderUnits = l_orderManager.getOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_sbWhere.toString(),
                    l_objWheres,
                    null);
                log.debug("l_lisOrderUnits=" + l_lisOrderUnits);
            }
			//�擾�����󔄂萔�ʌv����@@=="�x�e���ԑш����Ōv��"�̏ꍇ
			else
			{
				//���ɐV���Ɏs��ɔ�������邱�ƂɂȂ�A�V�K���������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
				l_sbWhere.append(" and order_open_status = ?");
				l_sbWhere.append(" and created_timestamp >= ?");
				l_sbWhere.append(" and not (order_condition_type = ?");
				l_sbWhere.append(" and stop_order_ordered_timestamp is null)");
	        	
				l_lisWheres.add(String.valueOf(l_tradedProduct.getProduct().getProductId()));
				l_lisWheres.add(String.valueOf(l_tradedProduct.getMarket().getMarketId()));
				l_lisWheres.add(String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()));
				l_lisWheres.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));
				String l_strbizDate = 
					WEB3DateUtility.formatDate(
						l_datBizDate, 
						WEB3GentradeTimeDef.DATE_FORMAT_YMD) +
							l_shortSellingResTimeRow.getStartTime();
				Date l_dtBizDate =
					WEB3DateUtility.getDate(
						l_strbizDate,
						WEB3GentradeTimeDef.DATE_FORMAT_YMD + 
						WEB3GentradeTimeDef.TIME_FORMAT_HMS);
				l_lisWheres.add(new Timestamp(l_dtBizDate.getTime()));
				l_lisWheres.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
	        	
				//�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B
				if (l_changeOrderUnit != null)
				{
					l_sbWhere.append(" and order_unit_id != ?");
					l_lisWheres.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
				}
	        	
				l_objWheres = new Object[l_lisWheres.size()];
				l_lisWheres.toArray(l_objWheres);
	        	
				l_lisOrderUnits = l_orderManager.getOrderUnits(
					l_subAccount, 
					ProductTypeEnum.EQUITY, 
					l_sbWhere.toString(), 
					l_objWheres, 
					null);
				log.debug("l_lisOrderUnits=" + l_lisOrderUnits);
			}    

            // �S�j�@@�R�j�Ŏ擾�����S�Ă̒����P�ʃI�u�W�F�N�g����󔄂�K�����ʂ��W�v����
            EqTypeOrderUnit l_orderUnit;
            EqtypeOrderUnitRow l_orderUnitRow;
            double l_dblTotalUnExecQuantity = 0.0D;
    
            int l_intLength = 0;
            if (l_lisOrderUnits != null)
            {
                l_intLength = l_lisOrderUnits.size();
            }
    
            for (int i = 0; i < l_intLength; i ++)
            {
                l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);
                l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
                // �����P�ʂ��J�z�������̏ꍇ�i��������������ԊǗ�.get������(void)�j
                // �擾�����󔄂萔�ʌv����@@��"�x�e���ԑш����Ōv��"�̏ꍇ
				if (!l_orderUnitRow.getBizDate().equals(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")) ||
                    WEB3ShortSellingCountMethodDivDef.COUNT_AS_REST_TIME_ZONE.equals(l_strShortSelCountMethodDiv))
                {
                    // �s�ꖢ���M�̏ꍇ
                    if (l_orderUnitRow.getConfirmedQuantity() == 0)
                    {
                        // �������ʂ��W�v
                        l_dblTotalUnExecQuantity += l_orderUnitRow.getQuantity();
                        log.debug("�������ʂ��W�v");
                        log.debug("l_orderUnitRow.getQuantity()=" + l_orderUnitRow.getQuantity());
                    }
                    // �s�ꑗ�M�ς̏ꍇ
                    else
                    {
                        // ����萔�ʁi�s�ꂩ��m�F�ς̐��� - ��萔�ʁj���W�v
                        l_dblTotalUnExecQuantity += l_orderUnitRow.getConfirmedQuantity()
                            - l_orderUnitRow.getExecutedQuantity();
                        log.debug("����萔�ʁi�s�ꂩ��m�F�ς̐��� - ��萔�ʁj���W�v");
                        log.debug("l_orderUnitRow.getConfirmedQuantity()=" + l_orderUnitRow.getConfirmedQuantity());
                        log.debug("l_orderUnitRow.getExecutedQuantity()=" + l_orderUnitRow.getExecutedQuantity());
                    }
                }
                // ��L�ȊO�̏ꍇ
                else
                {
                    // ���������ʂ��W�v
                    l_dblTotalUnExecQuantity += l_orderUnitRow.getOriginalQuantity();
                    log.debug("���������ʂ��W�v");
                    log.debug("l_orderUnitRow.getOriginalQuantity()=" + l_orderUnitRow.getOriginalQuantity());
                }
                log.debug("l_dblTotalUnExecQuantity="+l_dblTotalUnExecQuantity);
            }
    
            // �T�j�@@�w������̋K�����ʂ��v�Z����B
            // �@@�@@�@@�K������ �� �����̎������.�����P�� �~ �Q�j�Ŏ擾�������X.�󔄂�K�����x�P��
            double l_dblValidateQuantity = ((EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject()).getLotSize()
                * l_dblShortMarginRestrainUnit;
            log.debug("�w������̋K������ = �������.�����P�� �~ ���X.�󔄂�K�����x�P��");
            log.debug("�������.�����P�ʁF" + ((EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject()).getLotSize());
            log.debug("���X.�󔄂�K�����x�P�ʁF" + l_dblShortMarginRestrainUnit);
            log.debug("�w������̋K�����ʁF" + l_dblValidateQuantity);
            
            // �U�j�@@�ڋq�����ɂ��A�󔄂�K���Ώۂ��ǂ����̔�����s���B
            // �U�|�P�j�@@�ڋq����ʓ����Ƃ̏ꍇ
            // �@@�@@�E�i�i�S�j�ŏW�v��������萔�� �{ �����̊����j <= �T�j�Ōv�Z�����K�����ʁj�̏ꍇ�́A
            // �@@�@@�@@�󔄂�K���`�F�b�NOK�Ƃ�false�i�󔄂�K���ΏۊO�j��Ԃ��B
            //  �@@�@@����ʓ����Ƃ̏ꍇ�A���ʂ����x���̏ꍇ�͉��i�K���ΏۂƂȂ炸�A
            //  �@@�@@�����i�K�����`�F�b�N���s�v�B
            // �@@�@@�E�i�i�S�j�ŏW�v��������萔�� �{ �����̊����j > �T�j�Ōv�Z�����K�����ʁj�ɊY������ꍇ
            // �@@�@@�@@�V�j�ȍ~�̏������s���B
            //
            // �U�|�Q�j�@@�ڋq���K�i�@@�֓����Ƃ̏ꍇ
            // �@@�@@�@@�������łV�j�ȍ~�̏������s���B
            if ((l_dblTotalUnExecQuantity + l_dblQuantity) <= l_dblValidateQuantity)
            {
                log.debug("(����萔�� + �����̊���) <= �K�����ʂ̏ꍇ");
                log.debug("����萔��" + l_dblTotalUnExecQuantity);
                log.debug("����" + l_dblQuantity);
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        // �V�j�@@�����^�s�̔���A�y�ђ����̏ꍇ�͉��i�K���Ώێ��̃`�F�b�N���s���B
        // �V�|�P�j�@@�����s�̕��X�̏ꍇ�́u�w������̋󔄂�K�����ʂ𒴉߁v�̗�O��throw����B
        if (WEB3ShortMarginRestrainDivDef.DISABLE_ORDER.equals(l_strShortMarginRestrainDiv))
        {
            log.debug("�w������̋󔄂�K�����ʂ𒴉�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00734,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        else
        {
            // �V�|�Q�j�@@�����̕��X�̏ꍇ
            //    ���X.�K�i�@@�֓����Ɗm�F���{�敪�����{���� �̏ꍇ�̂݁A���i�K���Ώێ��̃`�F�b�N���s���B
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_strQualifiedInvestorConfirmDiv) == true)
            {
                // �i�P�j�����̒l�i������"�w��Ȃ�"�A���� ������is���s��true�i���s�����j
                if ((WEB3PriceConditionDef.DEFAULT.equals(l_strPriceConditionType) == true) &&
                    (isMarketOrder == true))
                {
                    log.debug("���s�w��s�i�󔄂�K�����ʒ��ߎ��j");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01345,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                // �i�Q�j�����̎��s������"�s�o���������s"
                if (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execCondType))
                {
                    log.debug("���s�����Ɂh�s�o���������s�h�w��s�i�󔄂�K�����ʒ��ߎ��j");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01346,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                // �i�R�j�����̒l�i�������i"�D��l�w�l"�A"���s�c���w�l"�A"���s�c�����"�j
                if (WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType) ||
                    WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType) ||
                    WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_strPriceConditionType))
                {
                    log.debug("�l�i�����Ɂh�D��l�w�l�h�A�h���s�c���w�l�h�A�h���s�c������h�w��s�i�󔄂�K�����ʒ��ߎ��j");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01347,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * �ivalidate�V�K���������j�B<BR>
     * <BR>
     * �V�K���������`�F�b�N���s���B<BR>
     * �@@�|���������𒴂��Ă��Ȃ����`�F�b�N����B�����Ă���ꍇ�̓G���[�Ƃ���B<BR>
     *   �|���������̎�ނ́A��������^��������^�s������ʏ���^�P����� �̂S��ށB<BR>
     * �@@�@@�S��ނ̂����ꂩ�P��ނŏ���𒴂��Ă���ꍇ�A��O��throw����B<BR>
     * �@@�@@�i����̎�ޖ��ɗ�O�N���X�𕪂���j<BR>
     * <BR>
     * �P�j�@@�����̒�����ʁ��i�h�V�K���������h�iMARGIN_LONG�j�A�h�V�K���������h�iMARGIN_SHORT�j ��<BR> 
     * �@@�@@�@@�ꍇ�́A�ȉ��̏������s���B<BR>
     * �@@�@@�@@�ȊO�́A���������ɂ��̂܂�return����B<BR>
     * <BR>
     * �Q�j�@@�����̕⏕�������A�ڋq�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�ڋq�I�u�W�F�N�g��<BR>
     * �@@�@@�@@�@@�����^�C�v<BR>
     * �@@�@@�@@���擾����B<BR>
     * <BR>
     * �R�j�@@�����̕⏕����.get����X( )�ŁA���X�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g��<BR>
     * �@@�@@�@@�@@���������l�i�l�E���j�@@�@@�@@�@@�@@�F�i*1*�j<BR>
     * �@@�@@�@@�@@���������l�i�@@�l�E���j�@@�@@�@@�@@�@@�F�i*2*�j<BR>
     * �@@�@@�@@�@@���������l�i�l�E�����P�ʁj�F�i*3*�j<BR>
     * �@@�@@�@@�@@���������l�i�@@�l�E�����P�ʁj�F�i*4*�j<BR>
     * �@@�@@�@@�@@���������l�i�l�E����j�@@�@@�@@�F�i*5*�j<BR>
     * �@@�@@�@@�@@���������l�i�@@�l�E����j�@@�@@�@@�F�i*6*�j<BR>
     * �@@�@@�@@���擾����B<BR>
     * <BR>
     * �@@�@@�@@�Q�j�Ŏ擾�����ڋq.�����^�C�v���h�@@�l�A�J�E���g�h�̏ꍇ�́A<BR>
     * �@@�@@�@@��L�i*1*�j�i*3*�j�i*5*�j�̍��ڂ��ȍ~�̃`�F�b�N�Ŏg�p����B<BR>
     * �@@�@@�@@�Q�j�Ŏ擾�����ڋq.�����^�C�v���h�@@�l�A�J�E���g�h�̏ꍇ�́A<BR> 
     * �@@�@@�@@��L�i*2*�j�i*4*�j�i*6*�j�̍��ڂ��ȍ~�̃`�F�b�N�Ŏg�p����B<BR>
     * <BR>
     * �S�j�@@����������`�F�b�N��<BR>
     * <BR>
     * �@@�S�|�P�j�@@�Y���ڋq�́A�S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B<BR>
     * <BR>�@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������I�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get�����ꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�������i"quantity"�j���w�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"quantity > ?"<BR>
     * �@@�@@�@@�i*2�j�������̌��������w��l�Ƃ��āA"0"��ݒ肷��B<BR>
     * <BR>�@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������I�u�W�F�N�g�S�Ắi������ �~ ���P���j�̒l���W�v����B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�Y���ڋq�́A�V�K���������i��萬���҂��j�̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������P�ʃI�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�����J�e�S���i"order_categ"�j�A�����L����ԁi"order_open_status"�j���A<BR>
     * �@@�@@�@@�@@�@@�@@and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * �@@�@@�@@�@@�e�����P�ʃI�u�W�F�N�g���A�ȉ��̒ʂ�ɒl���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�i�����P��.�s�ꂩ��m�F�ς݂̐��� �| �����P��.��萔�ʁj<BR>
     * �@@�@@�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������S�������̕��X�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���e�����P�ʂ̌v�Z���ʂɁA�i���s�����̏ꍇ�̂݁j�����S�������|���A<BR>
     * �@@�@@�@@�@@���~������؂�̂Ă������ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * <BR>
     * �@@�S�|�R�j�@@�i�����̌����(*1) �{ �S�|�P�j�Ōv�Z��������� �{ �S�|�Q�j�Ōv�Z��������j<BR>
     * �@@�@@�@@�@@�@@> ���X.���������l�i���j�i*�j �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u������̏���l����(��)�v�̗�O��throw����B<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00741<BR>
     * �@@�@@�@@�i*�j���X.���������l�i���j�F�R�j�Ōڋq.�����^�C�v�ɂ�蔻�肵���A�i*1*�j�܂��́i*2*�j�̒l�B<BR>
     * �@@�@@�@@(*1)�����̌�����F <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P��.���v�����z���T���������ʂ��g�p����B<BR>
     * <BR>
     * �T�j�@@����������`�F�b�N��<BR>
     * <BR>
     * �@@�T�|�P�j�@@�Y���ڋq�́A�Y�������̑S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������I�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get�����ꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j����ID�i"product_id"�j�A�������i"quantity"�j���A<BR>
     * �@@�@@�@@�@@�@@�@@and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"product_id = ? and quantity > ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j����ID�̌��������w��l�Ƃ��āA�����̎������.����ID��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�������̌��������w��l�Ƃ��āA"0"��ݒ肷��B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������I�u�W�F�N�g�S�Ắi������ �~ ���P���j�̒l���W�v����B<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�Y���ڋq�́A�V�K���������i��萬���҂��j�̊Y�������̒����P�ʃI�u�W�F�N�g��S��<BR>
     * �@@�@@�@@�@@�@@�擾����B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������P�ʃI�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j����ID�i"product_id"�j�A�����J�e�S���i"order_categ"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁi"order_open_status"�j���A<BR>
     * �@@�@@�@@�@@�@@�@@and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"product_id = ? and order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j����ID�F�@@�����̎������.����ID��ݒ�<BR>
     * �@@�@@�@@�@@�@@�@@�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * �@@�@@�@@�@@�e�����P�ʃI�u�W�F�N�g���A�ȉ��̒ʂ�ɒl���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�i�����P��.�s�ꂩ��m�F�ς݂̐��� �| �����P��.��萔�ʁj<BR>
     * �@@�@@�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������S�������̕��X�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���e�����P�ʂ̌v�Z���ʂɁA�i���s�����̏ꍇ�̂݁j�����S�������|���A<BR>
     * �@@�@@�@@�@@���~������؂�̂Ă������ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * <BR>
     * �@@�T�|�R�j�@@�i�����̌����(*1) �{ �T�|�P�j�Ōv�Z��������� �{ �T�|�Q�j�Ōv�Z��������j > <BR>
     * �@@�@@�@@�@@�@@���X.���������l�i�����P�ʁj�i*�j �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u������̏���l����(�����P��)�v�̗�O��throw����B<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00742<BR>
     * �@@�@@�@@�i*�j���X.���������l�i�����P�ʁj�F�R�j�Ōڋq.�����^�C�v�ɂ�蔻�肵���A�i*3*�j�܂��́i*4*�j�̒l�B<BR>
     * �@@�@@�@@(*1)�����̌�����F <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P��.���v�����z���T���������ʂ��g�p����B<BR>
     * <BR>
     * �U�j�@@���s������ʏ���`�F�b�N��<BR>
     * <BR>
     * �@@�U�|�P)�@@�s������ʌ�����̏�����z���擾����B<BR>
     * <BR>
     * �@@�@@�U�|�P�|�P)�@@(���X�s����敪��)�戵�����𐶐�����B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@���X�h�c�@@�F�@@�R�j�Ŏ擾�������X�I�u�W�F�N�g.���XID<BR>
     * �@@�@@�@@�@@�@@�s��h�c�@@�F�@@�����̎������.�s��h�c<BR>
     * �@@�@@�@@�@@�@@���敪�@@�F�@@�����̎������.���敪<BR>
     * �@@�@@�@@�@@�@@�V�s��敪�@@�F�@@�����̎������.�V�s��敪<BR>
     * �@@�@@�@@�@@�@@�X�����J�敪�@@�F�@@�����̎������.�X�����J�敪<BR>
     * <BR>
     * �@@�@@�U�|�P�|�Q)�@@(���X�s����敪��)�戵����.get���������l()���R�[������B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�����ڋq�I�u�W�F�N�g<BR>
     * <BR>
     * �@@�@@�U�|�P�|�R)�@@�ȉ��ɊY������ꍇ�A�s������ʏ���`�F�b�N���X�L�b�v����B<BR>
     * �@@�@@�@@�@@�U�|�P�|�P)�ŃG���[���X���[���ꂽ�ꍇ<BR>
     * �@@�@@�@@�@@�U�|�P�|�Q)��get���������l()�̖߂�l��null�̏ꍇ<BR>
     * <BR>
     * �@@�U�|�Q�j�@@�Y���ڋq�E�s��E�����̑S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������I�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get�����ꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�s��ID("market_id")�A����ID�i"product_id"�j�A�������i"quantity"�j��<BR>
     * �@@�@@�@@�@@�@@�@@and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"market_id = ? and product_id = ? and quantity > ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j�s��ID�̌��������w��l�Ƃ��āA�����̎������.�s��ID��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@����ID�̌��������w��l�Ƃ��āA�����̎������.����ID��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@�������̌��������w��l�Ƃ��āA"0"��ݒ肷��B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������I�u�W�F�N�g�S�Ắi������ �~ ���P���j�̒l���W�v����B<BR>
     * <BR>
     * �@@�U�|�R�j�@@�Y���ڋq�E�s��E�����̐V�K���������i��萬���҂��j�̒����P�ʃI�u�W�F�N�g��S��<BR>
     * �@@�@@�@@�@@�@@�@@�擾����B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������P�ʃI�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�s��ID("market_id")�A����ID�i"product_id"�j�A�����J�e�S���i"order_categ"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁi"order_open_status"�j���A<BR>
     * �@@�@@�@@�@@�@@�@@and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j�s��ID�F�@@�����̎������.�s��ID��ݒ�<BR>
     * �@@�@@�@@�@@�@@�@@����ID�F�@@�����̎������.����ID��ݒ�<BR>
     * �@@�@@�@@�@@�@@�@@�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * �@@�@@�@@�@@�e�����P�ʃI�u�W�F�N�g���A�ȉ��̒ʂ�ɒl���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�i�����P��.�s�ꂩ��m�F�ς݂̐��� �| �����P��.��萔�ʁj<BR>
     * �@@�@@�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������S�������̕��X�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���e�����P�ʂ̌v�Z���ʂɁA�i���s�����̏ꍇ�̂݁j�����S�������|���A<BR>
     * �@@�@@�@@�@@���~������؂�̂Ă������ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * <BR>
     * �@@�U�|�S�j�@@�i�����̌�����i*1�j �{ �U�|�Q�j�Ōv�Z��������� �{ �U�|�R�j�Ōv�Z��������j ><BR>
     * �@@�@@�@@�@@�@@�@@���������l�i*2�j �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�u������̏���l����(�s�������)�v�̗�O��throw����B<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02870<BR>
     * �@@�@@�@@(*1)�����̌�����F<BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P��.���v�����z���T���������ʂ��g�p����B<BR>
     * �@@�@@�@@�i*2�j�U�|�P�|�Q�j�Ŏ擾�����l<BR>
     * <BR>
     * �V�j�@@���������`�F�b�N��<BR>
     * <BR>
     * �@@�V�|�P�j�@@�Y���ڋq�́A�������`�F�b�N�̑Ώۂ̒����P�ʃI�u�W�F�N�g��S�Ď擾���A<BR>
     * �@@�@@�@@�@@�@@��������W�v����B<BR>
     * <BR>
     * �@@�@@�V�|�P�|�P�j�@@�Y���ڋq�́A�w������̐V�K�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������P�ʃI�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�����J�e�S���i"order_categ"�j�A�������i"biz_date"�j�A������ԁi"order_status"�j���A<BR>
     * �@@�@@�@@�@@�@@�@@and�����i�������A������Ԃ�NOT EQUAL�w��j�Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"order_categ = ? and biz_date = ? and order_status != ?"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue<BR>
     * �@@�@@�@@�@@�@@�@@�������F�@@������ԊǗ�.get������(void)�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@������ԁF�@@OrderStatusEnum.�h�������s�i�V�K�����j�h�iNOT_ORDERED�j��intValue<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * �@@�@@�@@�@@�e�����P�ʃI�u�W�F�N�g�̒����L����ԁA�s�ꂩ��m�F�ς݂̐��ʂɂ��A�ȉ��̒ʂ�Ɍv�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�����L����ԁ�OrderOpenStatusEnum.�h�N���[�Y�h�iCLOSED�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����P��.���v�����z ���g�p����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E�����P��.�����L����ԁ�OrderOpenStatusEnum.�h�N���[�Y�h�iCLOSED�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���o�����͒����P�����|�����l���A�o�����͍��v�����z���g�p����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ��<BR>
     * �@@�@@�@@�@@�@@�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ��<BR>
     * �@@�@@�@@�@@�@@�@@�i�i�i�����P��.�s�ꂩ��m�F�ς̐��� �| �����P��.��萔�ʁj<BR>
     * �@@�@@�@@�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j<BR>
     * �@@�@@�@@�@@�@@�@@�{ �����P��.���v�����z�j ���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�������S�������̕��X�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@���e�����P�ʂ̖���蕪�̌v�Z���ʂɁA�i���s�����̏ꍇ�̂݁j�����S�������|���A<BR>
     * �@@�@@�@@�@@���~������؂�̂Ă������ʂ��W�v����B<BR>
     * �@@�@@�@@--------------------------------<BR>
     * <BR>
     * �@@�@@�V�|�P�|�Q�j�@@�s��ǎ��ԑсi�������j�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�i������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�̏ꍇ�j�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ȍ~���L���ȁA�V�K���́u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�Ď擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���s��J�ǎ��ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���i������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�̏ꍇ�j�́A�擾���X�L�b�v����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�������P�ʃI�u�W�F�N�g�擾��<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̕⏕����, <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����^�C�v�i�����iEQUITY�j�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������������i*1�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���������f�[�^�R���e�i�i*2�j,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@null)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�����J�e�S���i"order_categ"�j�A�����L����ԁi"order_open_status"�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�������i"biz_date"�j�A�����������t�i"expiration_date"�j���A<BR>
     * �@@�@@�@@�@@�@@�@@and�����Ŏw�肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��"order_categ = ? and order_open_status = ? and biz_date = ?<BR>
     * �@@�@@�@@�@@�@@�@@��and expiration_date >= ? and first_order_unit_id is not null"�i*3�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�i"order_unit_id"�j��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * �@@�@@�@@�@@�@@�@@��" and order_unit_id != ?"<BR>
     * <BR>
     * �@@�@@�@@�i*2�j�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue<BR>
     * �@@�@@�@@�@@�@@�@@�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue<BR>
     * �@@�@@�@@�@@�@@�@@�������F�@@������ԊǗ�.get������(void)�̑O�c�Ɠ�<BR>
     * �@@�@@�@@�@@�@@�@@�����������t�F�@@������ԊǗ�.get������(void) �̖߂�l<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �@@�@@�@@�@@�@@�@@�����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@�@@�i*3�j�o����܂Œ����w����A���񒍕��̒����P��ID is not null �w��ɂ��s���B<BR>
     * �@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾���������P�ʃI�u�W�F�N�g�S�ẮA<BR>
     * �@@�@@�@@�i�i�����P��.�������� �| �����P��.��萔�ʁj �~ �����P��.�����P���j��<BR>
     * �@@�@@�@@�l���W�v����B<BR>
     * <BR>
     * �@@�@@�@@�������S�������̕��X�̏ꍇ�́A<BR>
     * �@@�@@�@@���e�����P�ʂ̌v�Z���ʂɁA�i���s�����̏ꍇ�̂݁j�����S�������|���A<BR>
     * �@@�@@�@@���~������؂�̂Ă������ʂ��W�v����B<BR>
     * <BR>
     * �@@�V�|�Q�j�@@�i�����̌���� �{ �V�|�P�|�P�j�Ōv�Z�����V�K�������̌����<BR>
     * �@@�@@�@@�@@�@@�{ �V�|�P�|�Q�j�Ōv�Z���������ȍ~���L���ȏo����܂Œ����̌�����j ><BR>
     * �@@�@@�@@�@@�@@���X.���������l�i����j�i*�j �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u������̏���l����(���)�v�̗�O��throw����B<BR>
     * <BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00743<BR>
     * �@@�@@�@@�i*�j���X.���������l�i����j�F�R�j�Ōڋq.�����^�C�v�ɂ�蔻�肵���A�i*5*�j�܂��́i*6*�j�̒l�B<BR>
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j�B
     * @@param l_dblAmount ������B
     * @@param l_orderType ������ʁB�ixTrade��OrderTypeEnum�ɂĒ�`�j<BR>
     * �@@�@@�@@�i�����������^�V�K���������^���������j
     * @@param l_tradedProduct ��������B
     * @@param l_changeOrderUnit �����Ώے����P�ʃI�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateMaxOpenMarginAmount(
        WEB3GentradeSubAccount l_subAccount, 
        double l_dblAmount, 
        OrderTypeEnum l_orderType, 
        WEB3EquityTradedProduct l_tradedProduct,
        EqtypeOrderUnitRow l_changeOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMaxOpenMarginAmount(l_subAccount,l_dblAmount,l_orderType,l_tradedProduct,l_changeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        long l_lngProductId;
        l_lngProductId = l_tradedProduct.getProduct().getProductId();
        // �������Row
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        if (!OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) && !OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            //�����̒�����ʁ��i�h�V�K���������h�iMARGIN_LONG�j�A�h�V�K���������h�iMARGIN_SHORT�j �� 
            //�ꍇ�́A�ȉ��̏������s���B �ȊO�́A���������ɂ��̂܂�return����B 
            return;
        }
        
        double l_dblNotExecutedContractAmount = l_dblAmount;
        if (l_changeOrderUnit != null)
        {
            l_dblNotExecutedContractAmount -= l_changeOrderUnit.getExecutedAmount();
        }
        log.debug("l_dblNotExecutedContractAmount="+l_dblNotExecutedContractAmount);
        
        //�ڋq���擾
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //���X���擾
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

        // ��Е��X���i�e�[�u����ǂݍ���
        InstBranchProductRow l_instBranchProductRow;
        try
        {
            l_instBranchProductRow =
                InstBranchProductDao.findRowByPk(
                    l_branch.getBranchId(),
                    WEB3CommisionProductCodeDef.LISTING_STOCK);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_instBranchProductRow == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��Е��X���i�e�[�u���ɊY������f�[�^������܂���B");
        }
        //�����S�������擾�iSTOP���S���������X�̏ꍇ�́A"1"���ݒ肳��Ă���j
        BigDecimal l_bdPremiumRestraintRate =
            new BigDecimal(l_instBranchProductRow.getPremiumRestraintRate());
        
        //���������l�E��
        double l_dblMaxContPriceAll = 0D;
        double l_dblMaxContPriceProduct = 0D;
        double l_dblMaxContPrice1day = 0D;
        
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
        {
            //�ڋq.�����^�C�v���h�@@�l�A�J�E���g�h�̏ꍇ�́A 
            //���������l�i�@@�l�E���j�@@
            //���������l�i�@@�l�E�����P�ʁj
            //���������l�i�@@�l�E����j
            //�̍��ڂ��ȍ~�̃`�F�b�N�Ŏg�p����B 
            l_dblMaxContPriceAll = l_branchRow.getMaxContPriceAllCorp() ;
            l_dblMaxContPriceProduct = l_branchRow.getMaxContPriceProductCorp();
            l_dblMaxContPrice1day = l_branchRow.getMaxContPrice1dayCorp();
        }
        else
        {
            //�ڋq.�����^�C�v���h�@@�l�A�J�E���g�h�̏ꍇ�́A 
            //���������l�i�l�E��
            //���������l�i�l�E�����P�ʁj
            //���������l�i�l�E����j
            //�̍��ڂ��ȍ~�̃`�F�b�N�Ŏg�p����B
            l_dblMaxContPriceAll = l_branchRow.getMaxContPriceAllInd() ;
            l_dblMaxContPriceProduct = l_branchRow.getMaxContPriceProductInd();
            l_dblMaxContPrice1day = l_branchRow.getMaxContPrice1dayInd();             
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //�g���v���_�N�g�}�l�[�W�����擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
        
        //�Y���ڋq�́A�S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B
        
        //�������������� quantity > ?
        String l_strWhere = " and quantity > ?";
        //���������f�[�^�R���e�i  0
        String[] l_objWhere = {"0"};
        
        List l_lisEffictiveContracts = l_positionManager.getContracts(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere, l_objWhere);
        
        int l_intLen = 0;
        if (l_lisEffictiveContracts != null)
        {
            l_intLen = l_lisEffictiveContracts.size();    
        }
        
        //�擾���������I�u�W�F�N�g�S�Ắi������ �~ ���P���j�̒l���W�v����B
        double l_dblTotalEffictiveAmount = 0D;
        for (int i = 0; i < l_intLen; i++)
        {
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisEffictiveContracts.get(i);
            
            l_dblTotalEffictiveAmount += Math.floor(l_contractRow.getQuantity() * l_contractRow.getContractPrice());
        }
        log.debug("l_dblTotalEffictiveAmount="+l_dblTotalEffictiveAmount);
                
        //�Y���ڋq�́A�V�K���������i��萬���҂��j�̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
        //(�����̒����̏ꍇ�͒����Ώے��������O����B)
        
        List l_lisWheres2 = new ArrayList();
        
        //�������������� �����J�e�S�� = ? and �����L����� = ?
        String l_strWhere2 = "order_categ = ? and order_open_status = ?";
        //���������f�[�^�R���e�i:   
        //�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j 
        //�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j
        
        l_lisWheres2.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_lisWheres2.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));        
        
        if (l_changeOrderUnit != null)
        {
           //and �����P��ID <> ?
           l_strWhere2 += " and order_unit_id <> ?";             
           //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
           l_lisWheres2.add(Long.toString(l_changeOrderUnit.getOrderUnitId())); 
        }
        Object[] l_objWhere2 = new Object[l_lisWheres2.size()];
        l_lisWheres2.toArray(l_objWhere2);
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        List l_lisOpenOrderUnits = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere2, l_objWhere2,null);
        
        if (l_lisOpenOrderUnits == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisOpenOrderUnits.size();
        }
        
        double l_dblTotalOpenQuantity = 0D;
        //�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B
        for (int i = 0; i < l_intLen; i ++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOpenOrderUnits.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            double l_dblQuantity;
            double l_dblPrice;
            if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
            {
                //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ
                //�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B
                l_dblQuantity = l_orderUnitRow.getQuantity();
                l_dblPrice = l_orderUnitRow.getPrice();
            }
            else
            {
                //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ <BR>
                //�@@�i�i�����P��.�s�ꂩ��m�F�ς̐��� �| �����P��.��萔�ʁj
                //�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j ���v�Z����B
                l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
            }
            double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
            if (l_orderUnit.isMarketOrder())
            {
                l_dblOpenAmount = Math.floor(
                    new BigDecimal(l_dblOpenAmount)
                    .multiply(l_bdPremiumRestraintRate).doubleValue());
            }
            l_dblTotalOpenQuantity += l_dblOpenAmount;
        }
        log.debug("l_dblTotalOpenQuantity="+l_dblTotalOpenQuantity);
        log.debug("������i�����jl_dblNotExecutedContractAmount + l_dblTotalEffictiveAmount + l_dblTotalOpenQuantity="
            + (l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmount + l_dblTotalOpenQuantity));

        if ((l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmount + l_dblTotalOpenQuantity) > l_dblMaxContPriceAll)
        {
            //������̏���l����(��)�̏ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00741,STR_METHOD_NAME);
        }
        
        //��������`�F�b�N���s���B
        
        //�Y���ڋq�́A�Y�������̑S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B
        
        //��������������: ����ID�i"product_id"�j = ?�A�������i"quantity"�j> ?
        String l_strWhere3 = " and product_id = ? and quantity > ?";
        //���������f�[�^�R���e�i: 
        //����ID = �����̖���ID
        //������ = "0"
        String[] l_objWhere3 = {"" + l_lngProductId,"0"};
        
        List l_lisEffictiveContractsForProduct =
            l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strWhere3, l_objWhere3);
        
        if (l_lisEffictiveContractsForProduct == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisEffictiveContractsForProduct.size();
        }
        //�擾���������I�u�W�F�N�g�S�Ắi������ �~ ���P���j�̒l���W�v����B         
        double l_dblTotalEffictiveAmountForProduct = 0D;        
        for (int i = 0; i < l_intLen; i ++)
        {
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisEffictiveContractsForProduct.get(i);
            
            l_dblTotalEffictiveAmountForProduct +=
                Math.floor(l_contractRow.getQuantity() * l_contractRow.getContractPrice());
        }
        log.debug("l_dblTotalEffictiveAmountForProduct="+l_dblTotalEffictiveAmountForProduct);
        
        //�Y���ڋq�́A�V�K���������i��萬���҂��j�̊Y�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
        //(�����̒����̏ꍇ�͒����Ώے��������O����B)
        
        //�������������� ����ID = ? �����J�e�S�� = ? and �����L����� = ?
        String l_strWhere4 = "product_id = ? and order_categ = ? and order_open_status = ?";
        //���������f�[�^�R���e�i:   
        //����ID�F�@@�����̖���ID
        //�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j 
        //�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j
        
        List l_lisWheres4 = new ArrayList();
        l_lisWheres4.add(String.valueOf(String.valueOf(l_lngProductId)));
        l_lisWheres4.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));        
        l_lisWheres4.add(String.valueOf(String.valueOf(OrderOpenStatusEnum.OPEN.intValue())));
        
        if (l_changeOrderUnit != null)
        {
            //and �����P��ID <> ?
            l_strWhere4 += " and order_unit_id <> ?";             
            //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
            l_lisWheres4.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
        }
        Object[] l_objWhere4 = new Object[l_lisWheres4.size()];
        l_lisWheres4.toArray(l_objWhere4);
               
        List l_lisOpenOrderUnitsForProduct = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere4, l_objWhere4,null);
        
        if (l_lisOpenOrderUnitsForProduct == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisOpenOrderUnitsForProduct.size();
        }
        
        double l_dblTotalOpenQuantityForProdcut = 0D;
        //�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B
        for (int i = 0; i < l_intLen; i ++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOpenOrderUnitsForProduct.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            double l_dblQuantity;
            double l_dblPrice;
            if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
            {
                //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ
                //�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B
                l_dblQuantity = l_orderUnitRow.getQuantity();
                l_dblPrice = l_orderUnitRow.getPrice();
            }
            else
            {
                //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ <BR>
                //�@@�i�i�����P��.�s�ꂩ��m�F�ς̐��� �| �����P��.��萔�ʁj
                //�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j ���v�Z����B
                l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
            }
            double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
            if (l_orderUnit.isMarketOrder())
            {
                l_dblOpenAmount = Math.floor(
                    new BigDecimal(l_dblOpenAmount)
                    .multiply(l_bdPremiumRestraintRate).doubleValue());
            }

            l_dblTotalOpenQuantityForProdcut += l_dblOpenAmount;
        }        
        log.debug("l_dblTotalOpenQuantityForProdcut="+l_dblTotalOpenQuantityForProdcut);
        log.debug("������i�����P�ʁjl_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct + l_dblTotalOpenQuantityForProdcut="
            + (l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct + l_dblTotalOpenQuantityForProdcut));

        if ((l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct + l_dblTotalOpenQuantityForProdcut)
             > l_dblMaxContPriceProduct)
        {
            //������̏���l����(�����P��)�̏ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00742,STR_METHOD_NAME);
        }
        
        //�s������ʏ���`�F�b�N���s���B        
        //�s������ʌ�����̏�����z���擾����B
        Double l_dblMaxAmountByMarketProduct = null;
        try
        { 
            //(���X�s����敪��)�戵�����𐶐�����
            WEB3GentradeBranchListmarketDealtCond l_BranchListmarketDealtCondwk
                = new WEB3GentradeBranchListmarketDealtCond(
                    l_branchRow.getBranchId(),
                    l_tradedProduct.getMarket().getMarketId(),
                    l_tradedProductRow.getListType(),
                    l_tradedProductRow.getNewListType(),
                    l_tradedProductRow.getOpenOtcDiv());

            l_dblMaxAmountByMarketProduct = l_BranchListmarketDealtCondwk.getMaxContPrice(l_mainAccount);
        }
        catch (WEB3SystemLayerException e) {}
        
        if (l_dblMaxAmountByMarketProduct != null)
        {
            //�Y���ڋq�́A�Y�������̑S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B        
            //��������������: �s��ID�i"market_id"�j = ?�A����ID�i"product_id"�j = ?�A�������i"quantity"�j> ?
            String l_strWhere7 = " and market_id = ? and product_id = ? and quantity > ?";
            //���������f�[�^�R���e�i: 
            //�s��ID = �����̎s��ID
            //����ID = �����̖���ID
            //������ = "0"
            String[] l_objWhere7 = {"" + l_tradedProductRow.getMarketId(),"" + l_lngProductId,"0"};
            List l_lisEffictiveContractsForProduct2 =
                l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strWhere7, l_objWhere7);
            if (l_lisEffictiveContractsForProduct2 == null)
            {
                l_intLen = 0;
            }
            else
            {
                l_intLen = l_lisEffictiveContractsForProduct2.size();
            }
            //�擾���������I�u�W�F�N�g�S�Ắi������ �~ ���P���j�̒l���W�v����B
            double l_dblTotalEffictiveAmountForProduct2 = 0D;
            for (int i = 0; i < l_intLen; i ++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisEffictiveContractsForProduct2.get(i);

                l_dblTotalEffictiveAmountForProduct2 +=
                    Math.floor(l_contractRow.getQuantity() * l_contractRow.getContractPrice());
            }
            log.debug("l_dblTotalEffictiveAmountForProduct2=" + l_dblTotalEffictiveAmountForProduct2);

            //�Y���ڋq�́A�V�K���������i��萬���҂��j�̊Y�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
            //(�����̒����̏ꍇ�͒����Ώے��������O����B)
            //�������������� �s��ID = ? ����ID = ? �����J�e�S�� = ? and �����L����� = ?
            String l_strWhere8 = "market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?";
            //���������f�[�^�R���e�i:   
            //�s��ID�F  �����̎s��ID
            //����ID�F�@@�����̖���ID
            //�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j 
            //�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j

            List l_lisWheres8 = new ArrayList();
            l_lisWheres8.add(String.valueOf(l_tradedProductRow.getMarketId()));
            l_lisWheres8.add(String.valueOf(l_lngProductId));
            l_lisWheres8.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
            l_lisWheres8.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));

            if (l_changeOrderUnit != null)
            {
                //and �����P��ID <> ?
                l_strWhere8 += " and order_unit_id <> ?";             
                //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
                l_lisWheres8.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
            }
            Object[] l_objWhere8 = new Object[l_lisWheres8.size()];
            l_lisWheres8.toArray(l_objWhere8);

            List l_lisOpenOrderUnitsForProduct2 = l_orderManager.getOrderUnits(
                l_subAccount, ProductTypeEnum.EQUITY, l_strWhere8, l_objWhere8, null);

            if (l_lisOpenOrderUnitsForProduct2 == null)
            {
                l_intLen = 0;
            }
            else
            {
                l_intLen = l_lisOpenOrderUnitsForProduct2.size();
            }

            double l_dblTotalOpenQuantityForProdcut2 = 0D;
            //�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B
            for (int i = 0; i < l_intLen; i ++)
            {
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOpenOrderUnitsForProduct2.get(i);
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

                double l_dblQuantity;
                double l_dblPrice;
                if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
                {
                    //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ
                    //�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B
                    l_dblQuantity = l_orderUnitRow.getQuantity();
                    l_dblPrice = l_orderUnitRow.getPrice();
                }
                else
                {
                    //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ <BR>
                    //�@@�i�i�����P��.�s�ꂩ��m�F�ς̐��� �| �����P��.��萔�ʁj
                    //�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j ���v�Z����B
                    l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                    l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
                }
                double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
                if (l_orderUnit.isMarketOrder())
                {
                    l_dblOpenAmount = Math.floor(
                    new BigDecimal(l_dblOpenAmount)
                    .multiply(l_bdPremiumRestraintRate).doubleValue());
                }

                l_dblTotalOpenQuantityForProdcut2 += l_dblOpenAmount;
            }
            log.debug("l_dblTotalOpenQuantityForProdcut2=" + l_dblTotalOpenQuantityForProdcut2);
            log.debug("������i�s������P�ʁjl_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct2 + l_dblTotalOpenQuantityForProdcut2="
                + (l_dblNotExecutedContractAmount +
                    l_dblTotalEffictiveAmountForProduct2 +
                    l_dblTotalOpenQuantityForProdcut2));
            double l_dblMaxAmountByMarketProductwk = l_dblMaxAmountByMarketProduct.doubleValue();
            if ((l_dblNotExecutedContractAmount + l_dblTotalEffictiveAmountForProduct2 + l_dblTotalOpenQuantityForProdcut2)
                > l_dblMaxAmountByMarketProductwk)
            {
                //������̏���l����(�s������P��)�̏ꍇ
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02870,STR_METHOD_NAME);
            }
        }
        //�������`�F�b�N���s���B
        //�Y���ڋq�́A�������`�F�b�N�̑Ώۂ̒����P�ʃI�u�W�F�N�g��S�Ď擾���A 
        //��������W�v����B 
                
        //��������������:
        //�����J�e�S���i"order_categ"�j = ? and �������i"biz_date"�j = ? and ������ԁi"order_status"�j!= ?
        //(�����̒����̏ꍇ�͒����Ώے��������O����B)
        
        String l_strWhere5= "order_categ = ? and biz_date = ? and order_status <> ?";
        //���������f�[�^�R���e�i:
        //�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j
        //�������F�@@������ԊǗ�.get������()
        //������ԁF�@@OrderStatusEnum.�h�������s�i�V�K�����j�h�iNOT_ORDERED�j
        String l_strdate = WEB3DateUtility.formatDate(WEB3GentradeTradingTimeManagement.getOrderBizDate(),
            "yyyyMMdd"); 
        List l_lisWheres5 = new ArrayList();
        
        l_lisWheres5.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_lisWheres5.add(l_strdate);        
        l_lisWheres5.add(String.valueOf(OrderStatusEnum.NOT_ORDERED.intValue()));
        
        if (l_changeOrderUnit != null)
        {
            //and �����P��ID <> ?
            l_strWhere5 += " and order_unit_id <> ?";             
            //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
            l_lisWheres5.add(Long.toString(l_changeOrderUnit.getOrderUnitId())); 
        }
        Object[] l_objWhere5 = new Object[l_lisWheres5.size()];
        l_lisWheres5.toArray(l_objWhere5);
                
        //�Y���ڋq�́A�w������̐V�K�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
        List l_lisOrderUnitsFor1Day = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhere5, l_objWhere5, null);
        
        if (l_lisOrderUnitsFor1Day == null)
        {
            l_intLen = 0;
        }
        else
        {
            l_intLen = l_lisOrderUnitsFor1Day.size();
        }
        
        //�擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B 
        double l_dblTotalAmountFor1Day = 0D;
        for (int i = 0; i < l_intLen; i++)
        {
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnitsFor1Day.get(i);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
            {
                //�����P��.�����L����ԁ�OrderOpenStatusEnum.�h�N���[�Y�h�iCLOSED�j�̏ꍇ�́A 
                //�����P��.���v�����z ���g�p����B 
                l_dblTotalAmountFor1Day += l_orderUnitRow.getExecutedAmount();
            }
            else
            {
                double l_dblQuantity;
                double l_dblPrice;

                //�����P��.�����L����ԁ�OrderOpenStatusEnum.�h�N���[�Y�h�iCLOSED�j�̏ꍇ�́A  
                //���o�����͒����P�����|�����l���A�o�����͍��v�����z���g�p����B 
                
                if (l_orderUnitRow.getConfirmedQuantityIsNull() == true)
                {
                    //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ
                    //�i�����P��.�������� �~ �����P��.�����P���j ���v�Z����B
                    l_dblQuantity = l_orderUnitRow.getQuantity();
                    l_dblPrice = l_orderUnitRow.getPrice();
                }
                else
                {
                    //�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ <BR>
                    //�@@�i�i�����P��.�s�ꂩ��m�F�ς̐��� �| �����P��.��萔�ʁj
                    //�@@�@@�@@�@@�~ �����P��.�s�ꂩ��m�F�ς̒����P���j 
                    //�{ �����P��.���v�����z�j���v�Z����B
                    l_dblQuantity = l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                    l_dblPrice = l_orderUnitRow.getConfirmedOrderPrice();
                }
                double l_dblOpenAmount = l_dblQuantity * l_dblPrice;
                if (l_orderUnit.isMarketOrder())
                {
                    l_dblOpenAmount = Math.floor(
                        new BigDecimal(l_dblOpenAmount)
                        .multiply(l_bdPremiumRestraintRate).doubleValue());
                }
                if (OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus()))
                {
                    ;
                }
                else
                {
                    l_dblOpenAmount += l_orderUnitRow.getExecutedAmount();
                }

                l_dblTotalAmountFor1Day += l_dblOpenAmount;
            }
        }
        log.debug("l_dblTotalAmountFor1Day�i���������j="+l_dblTotalAmountFor1Day);
        
        double l_dblTotalAmountForNextDay = 0D;
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
        {
            //�i������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�̏ꍇ�j�̂݁A 
            
            //��������������:
            //�����J�e�S���i"order_categ"�j = ? and �����L����ԁi"order_open_status"�j = ?
            //and �������i"biz_date"�j= ?  
            //and �����������t�i"expiration_date"�j>= ? and ���񒍕��̒����P�ʂh�c != null
            //�o����܂Œ����w����A���񒍕��̒����P��ID is not null �w��ɂ��s���B 
            //(�����̒����̏ꍇ�͒����Ώے��������O����B)
            
            String l_strWhere6 =
                "order_categ = ? and order_open_status = ? "
                + "and biz_date = ? "
                + "and to_char(expiration_date,'yyyyMMdd') >= ? and first_order_unit_id is not null";
            //���������f�[�^�R���e�i:
            //�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue 
            //�����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue
            //�������F�@@������ԊǗ�.get������(void)�̑O�c�Ɠ� 
            //�����������t�F�@@������ԊǗ�.get������(void) �̖߂�l
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            WEB3GentradeBizDate l_dateCalc = new WEB3GentradeBizDate(new Timestamp(l_orderBizDate.getTime()));
            Date l_prevBizDate = l_dateCalc.roll(-1);
            String l_strPrevBizDate = WEB3DateUtility.formatDate(l_prevBizDate, "yyyyMMdd");
            String l_strdate2 = WEB3DateUtility.formatDate(l_orderBizDate,"yyyyMMdd");
              
            List l_lisWheres6 = new ArrayList();
                        
            l_lisWheres6.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
            l_lisWheres6.add(String.valueOf(String.valueOf(OrderOpenStatusEnum.OPEN.intValue())));
            l_lisWheres6.add(l_strPrevBizDate);
            l_lisWheres6.add(l_strdate2);
            
            if (l_changeOrderUnit != null)
            {
                //and �����P��ID <> ?
                l_strWhere6 += " and order_unit_id <> ?";             
                //�����P��ID�F  �����̒����Ώے����P�ʃI�u�W�F�N�g.�����P��ID
                l_lisWheres6.add(Long.toString(l_changeOrderUnit.getOrderUnitId())); 
            }
            
            Object[] l_objWhere6 = new Object[l_lisWheres6.size()];
            l_lisWheres6.toArray(l_objWhere6);
                        
            //�����ȍ~���L���ȁA�V�K���́u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
            List l_lisOrderUnitsForNextDay = l_orderManager.getOrderUnits(
                l_subAccount, ProductTypeEnum.EQUITY, l_strWhere6, l_objWhere6, null);
            
            if (l_lisOrderUnitsForNextDay == null)
            {
                l_intLen = 0;
            }
            else
            {
                l_intLen = l_lisOrderUnitsForNextDay.size();
            }
            
            //�擾���������P�ʃI�u�W�F�N�g�S�ẮA 
            //�i�i�����P��.�������� �| �����P��.��萔�ʁj �~ �����P��.�����P���j�� �l���W�v����B 
            for (int i = 0; i < l_intLen; i++)
            {
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnitsForNextDay.get(i);
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                
                double l_dblOpenAmount = 
                    (l_orderUnitRow.getQuantity() - l_orderUnitRow.getExecutedQuantity())
                    * l_orderUnitRow.getPrice();
                if (l_orderUnit.isMarketOrder())
                {
                    l_dblOpenAmount = Math.floor(
                        new BigDecimal(l_dblOpenAmount)
                        .multiply(l_bdPremiumRestraintRate).doubleValue());
                }
                l_dblTotalAmountForNextDay += l_dblOpenAmount;
            }
        }
        log.debug("l_dblTotalAmountForNextDay�i�����\�蕪�i������j�j="+l_dblTotalAmountForNextDay);
        log.debug("������i����P�ʁjl_dblAmount + l_dblTotalAmountFor1Day + l_dblTotalAmountForNextDay="
            + (l_dblAmount + l_dblTotalAmountFor1Day + l_dblTotalAmountForNextDay));

        if ((l_dblAmount + l_dblTotalAmountFor1Day + l_dblTotalAmountForNextDay) > l_dblMaxContPrice1day)
        {
            //������̏���l����(���)�̏ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00743,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate�����i�M�p�j�j�B<BR>
     * <BR>
     * �����̃`�F�b�N���s���B<BR>
     * �@@�|�������O�܂��̓}�C�i�X�l�łȂ����ƁB<BR>
     * �@@�|�����������P�ʂ̐����{�ł��邱�ƁB<BR>
     * �@@�|������HOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ƁB<BR>
     * �@@�|�������A�s��{�ٍϋ敪�{������ʖ��̔�������P�ʂ𒴂��Ă��Ȃ����ƁB<BR>
     * �@@�@@�i���V�K���^�ԍς̏ꍇ�̂݁j<BR>
     * �ivalidateQuantity�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����jvalidate�����i�M�p�j�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̏����ivalidateQuantity(�����̊���)�j�ɂāA<BR>
     * �@@�@@�@@������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B<BR>
     * �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�u������0�܂��̓}�C�i�X�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00709<BR>
     * <BR>
     * �Q�j�@@�X�[�p�[�N���X�̏����icheckLotSize(�������.�����P��, �����̊���)�j�ɂāA<BR>
     * �@@�@@�@@�����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B<BR>
     * �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�u�����������P�ʂ̐����{�łȂ��v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00708<BR>
     * <BR>
     * �R�j�@@this.validate�����i�w��\����l�j(�����̕��X, �����̊���))�ɂāA<BR>
     * �@@�@@�@@�����������AHOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�uHOST����t�\�Ȋ�������l�𒴉߁v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00707<BR>
     * <BR>
     * �S�j�@@�����̒�����ʂ��ȉ��̂����ꂩ�ɊY������ꍇ�̂݁A�T�j�ȍ~�̏����𑱍s����B<BR>
     * �@@�@@�@@--------------------------------------------------------------<BR>
     * �@@�@@�@@�h�V�K���������h�iMARGIN_LONG�j<BR>
     * �@@�@@�@@�h�V�K���������h�iMARGIN_SHORT�j<BR>
     * �@@�@@�@@�h�����ԍϒ����h�iCLOSE_MARGIN_LONG�j<BR>
     * �@@�@@�@@�h�����ԍϒ����h�iCLOSE_MARGIN_SHORT�j<BR>
     * �@@�@@�@@--------------------------------------------------------------<BR>
     * �@@�@@�@@�����̒�����ʂ���L�̂�����ł��Ȃ��ꍇ�́Areturn���������I������B<BR>
     * <BR>
     * �T�j�@@��������������v�Z����B<BR>
     * <BR>
     * �i�v�Z���j<BR>
     * �������.�����P�ʁ@@�~�@@�������x�P��(*1)�@@���@@�����������<BR>
     * <BR>
     * (*1) �������x�P�ʁF<BR>
     * �@@�@@�@@�|�������.�������x�P�� != null �̏ꍇ�́A�������.�������x�P�� ���g�p����B<BR>
     * �@@�@@�@@�|�������.�������x�P�� == null �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���X.get�������x�P�ʁi�s��R�[�h, ���敪, �ٍϋ敪, �ٍϊ����l, is�V�K��, is�����j<BR>
     * �@@�@@�@@�@@�@@�ɂĎ擾�����l���g�p����B<BR>
     * <BR>
     * [���X.get�������x�P��( )�����ݒ�l]<BR>
     * �s��R�[�h�F�@@�������.getMarket().�s��R�[�h<BR>
     * ���敪�F�@@�������.���敪<BR>
     * �ٍϋ敪�F�@@�����ٍ̕ϋ敪<BR>
     * �ٍϊ����l�F�@@�����ٍ̕ϊ����l<BR>
     * is�V�K���F�@@�����̒�����ʁ��h�V�K���������h�iMARGIN_LONG�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��́h�V�K���������h�iMARGIN_SHORT�j�̏ꍇ��true�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����̒�����ʁ��h�����ԍϒ����h�iCLOSE_MARGIN_LONG�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��́h�����ԍϒ����h�iCLOSE_MARGIN_SHORT�j�̏ꍇ��false�B<BR>
     * is�����F�@@�����̒�����ʁ��h�V�K���������h�iMARGIN_LONG�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��́h�����ԍϒ����h�iCLOSE_MARGIN_LONG�j�̏ꍇ��true�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����̒�����ʁ��h�V�K���������h�iMARGIN_SHORT�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��́h�����ԍϒ����h�iCLOSE_MARGIN_SHORT�j�̏ꍇ��false�B<BR>
     * <BR>
     * �U�j�@@�i����������� �� �����̊����j�̏ꍇ�A<BR>
     * �@@�@@�@@�u����������߃G���[�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00706
     * @@param l_tradedProduct ��������I�u�W�F�N�g
     * @@param l_branch ���X�I�u�W�F�N�g
     * @@param l_dblQuantity (����)<BR>
     * �@@�@@�@@���͊���
     * @@param l_orderType ������ʁB�ixTrade��OrderTypeEnum�ɂĒ�`�j
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT(�w��Ȃ�)<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_dblRepaymentNum �ٍϊ����l
     * @@throws WEB3BaseException
     */
    public void validateQuantity(
        WEB3EquityTradedProduct l_tradedProduct, 
        WEB3GentradeBranch l_branch, 
        double l_dblQuantity, 
        OrderTypeEnum l_orderType, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateQuantity(l_tradedProduct,l_branch,l_dblQuantity,l_orderType,l_strRepaymentType,l_dblRepaymentNum)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�X�[�p�[�N���X�̏����ivalidateQuantity(�����̊���)�j�ɂāA
        // �@@�@@�@@������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B
        // �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�u������0�܂��̓}�C�i�X�v�̗�O���X���[����B
        try
        {
            super.validateQuantity(l_dblQuantity);                        
        }
        catch (OrderValidationException l_ovex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00709,STR_METHOD_NAME);
        }

        // �Q�j�@@�X�[�p�[�N���X�̏����icheckLotSize(�������.�����P��, �����̊���)�j�ɂāA
        // �@@�@@�@@�����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B
        // �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�u�����������P�ʂ̐����{�łȂ��v�̗�O���X���[����B
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        try
        {
            super.checkLotSize(l_tradedProductRow.getLotSize(), l_dblQuantity);                     
        }
        catch (OrderValidationException l_ovex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00708,STR_METHOD_NAME);
        }        

        // �R�j�@@this.validate�����i�w��\����l�j(�����̕��X, �����̊���))�ɂāA
        // �@@�@@�@@�����������AHOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B
        // �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�uHOST����t�\�Ȋ�������l�𒴉߁v�̗�O���X���[����B
        try
        {
            this.validateQuantity(l_branch,l_dblQuantity);                  
        }
        catch (WEB3BaseException l_wbex)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00707,STR_METHOD_NAME);
        }          

        // �S�j�@@�����̒�����ʂ��ȉ��̂����ꂩ�ɊY������ꍇ�̂݁A�T�j�ȍ~�̏����𑱍s����B
        // �@@�@@�@@--------------------------------------------------------------
        // �@@�@@�@@�h�V�K���������h�iMARGIN_LONG�j
        // �@@�@@�@@�h�V�K���������h�iMARGIN_SHORT�j
        // �@@�@@�@@�h�����ԍϒ����h�iCLOSE_MARGIN_LONG�j
        // �@@�@@�@@�h�����ԍϒ����h�iCLOSE_MARGIN_SHORT�j
        // �@@�@@�@@--------------------------------------------------------------
        // �@@�@@�@@�����̒�����ʂ���L�̂�����ł��Ȃ��ꍇ�́Areturn���������I������B
        if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType)
            || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType)
            || OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType)
            || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        { 
            // �T�j�@@��������������v�Z����B
            boolean l_isOpenContract = false;
            if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) 
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                // �����̒�����ʁ��h�V�K���������h�iMARGIN_LONG�j 
                // �܂��́h�V�K���������h�iMARGIN_SHORT�j�̏ꍇ��true�B 
                l_isOpenContract = true;
            }

            boolean l_isBuy = false;
            if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) 
                || OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
                
            {
                // �����̒�����ʁ��h�V�K���������h�iMARGIN_LONG�j 
                // �܂��́h�����ԍϒ����h�iCLOSE_MARGIN_LONG�j�̏ꍇ��true�B 
                l_isBuy = true;
            }

            double l_dblDealingLimitUnit = 0.0D;
            if (l_tradedProductRow.getCompulsiveLimitedUnitIsNull())
            {
                l_dblDealingLimitUnit = 
                    l_branch.getDealingLimitUnit(
                        l_tradedProduct.getMarketCode(), 
                        l_tradedProductRow.getListType(), 
                        l_strRepaymentType, 
                        l_dblRepaymentNum, 
                        l_isOpenContract, 
                        l_isBuy);
            }
            else
            {
                l_dblDealingLimitUnit = l_tradedProductRow.getCompulsiveLimitedUnit();
            }
         
            // �U�j�@@�i������������@@< �����̊����j�̏ꍇ�A
            // �@@�@@�@@�u����������߃G���[�v�̗�O��throw����B
            double l_dblDealingLimitQuantity = l_tradedProductRow.getLotSize() * l_dblDealingLimitUnit;
            if (l_dblDealingLimitQuantity < l_dblQuantity)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00160,
                    this.getClass().getName() + STR_METHOD_NAME);
            }       
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate�����i�w��\����l�j�j�B<BR>
     * <BR>
     * �����������AHOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B<BR>
     * �ivalidateQuantity�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�،���ЃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����̕��X.getInstitution( )<BR>
     * <BR>
     * �Q�j�@@�i�،����.�w��\��������l �� �����̊����j�̏ꍇ�A<BR>
     * �@@�@@�u������ʒ��߃G���[�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00682
     * @@param l_branch ���X�I�u�W�F�N�g
     * @@param l_dblQuantity ���͊���
     * @@throws WEB3BaseException
     */
    protected void validateQuantity(
        WEB3GentradeBranch l_branch, 
        double l_dblQuantity) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQuantity(l_branch,l_dblQuantity)";
        log.entering(STR_METHOD_NAME);
        
        //�،���ЃI�u�W�F�N�g���擾����B
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_branch.getInstitution();
        //�،����Row�I�u�W�F�N�g���擾����B
        InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
      
        if (l_institutionRow.getMaxOrderQuantity() < l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00682,STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate���n�\�����j�B<BR>
     * <BR>
     * �w��ڋq�����n�\�Ȋ�����ێ����Ă��邩�ǂ����̃`�F�b�N���s���B<BR>
     * �@@�|SubAccount���M�p������� �́A�Y�������{�ŋ敪�ۗ̕L���Y���擾����B<BR>
     * �@@�|�Y�������ۗ̕L�Ȃ��^�ۗL�����s�� �̏ꍇ�́A��O��throw����B<BR>
     * �ivalidateSwappableAssetQuantity( )�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�M�p����������A�Y�������{�ŋ敪�ۗ̕L���Y�I�u�W�F�N�g���擾���A
     * �@@�@@�@@�ۗL�����`�F�b�N���s���B<BR>
     * <BR>
     * �P�|�P�j�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@���ۗL���Y�I�u�W�F�N�g�̎擾��<BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y(�����̕⏕����.����ID,<BR>
     * �@@�@@�@@�@@�����̕⏕����.�⏕����ID, �����̎������.����ID,<BR>
     * �@@�@@�@@�@@�����̐ŋ敪�i�������n�j)<BR>
     * <BR>
     * �P�|�Q�j�@@���n�\���ʂ��v�Z����B<BR>
     * <BR>
     * �@@�@@�@@���n�\���� �� �i�P�|�P�j�Ŏ擾�����ۗL���Y.���� �| �ۗL���Y.getLockedQuantity( )�j<BR>
     * �@@�@@�@@���P�|�P�j�ŊY���f�[�^�Ȃ��̏ꍇ�́A���n�\���ʁ�0�Ƃ���B<BR>
     * <BR>
     * �P�|�R�j�@@�i�i�P�|�Q�j�Ōv�Z�������n�\���ʁj �� �����̊����j�̏ꍇ��<BR>
     * �@@�@@�@@�@@�@@���n�\������ۗL���Ă���Ɣ��f���Areturn����B<BR>
     * �@@�@@�@@�@@�@@��L�ȊO�̏ꍇ�́A�u���n�Ώۂۗ̕L���Y�̐��ʕs���v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00744
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j�B
     * @@param l_tradedProduct ��������I�u�W�F�N�g�B
     * @@param l_dblQuantity (����)<BR>
     * �@@�@@�@@���͊����B
     * @@param l_swapTaxType (�ŋ敪�i�������n�j)<BR>
     * �@@�@@�@@�������n�̐ŋ敪�B
     * @@throws WEB3BaseException
     */
    public void validateSwappableAssetQuantity(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityTradedProduct l_tradedProduct, 
        double l_dblQuantity, 
        TaxTypeEnum l_swapTaxType) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSwappableAssetQuantity(l_subAccount,l_tradedProduct,l_dblQuantity,l_swapTaxType)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblSwappableAssetQuantituy = 0D;
       
        //�M�p����������A�Y�������{�ŋ敪�ۗ̕L���Y�I�u�W�F�N�g���擾���A�ۗL�����`�F�b�N���s���B 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
                   
        //�ۗL���Y�I�u�W�F�N�g���擾����B

        EqTypeAsset l_asset = l_positionManager.getAsset(
            l_subAccount.getAccountId(), l_subAccount.getSubAccountId(),
            l_tradedProduct.getProduct().getProductId(),l_swapTaxType);
        log.debug("l_dblSwappableAssetQuantituy="+l_dblSwappableAssetQuantituy);
        
        //���n�\���ʂ��v�Z����
        if (l_asset != null)
        {
            l_dblSwappableAssetQuantituy = l_asset.getQuantity() - l_asset.getLockedQuantity();
            log.debug("l_dblSwappableAssetQuantituy="+l_dblSwappableAssetQuantituy);
        }
        
        if (l_dblSwappableAssetQuantituy < l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00744,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate�ŋ敪�i�������n�j�j�B<BR>
     * <BR>
     * �w�肳�ꂽ�ŋ敪�A�ŋ敪�i�������n�j�̑Ó����`�F�b�N���s���B<BR>
     * �@@�|�����̏ꍇ�A��ʌ����̌������������ɓ���邱�Ƃ͕s�B�i�ŋ敪�̕ǂ���j<BR>
     * �@@�|��L�ȊO�̏ꍇ�́A��������\�B�i�ŋ敪�̕ǂȂ��j<BR>
     * <BR>
     * �P�j�@@������is������true�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * �@@�@@�@@�ȊO�́A����������return����B<BR>
     * <BR>
     * �Q�j�@@�����̐ŋ敪��TaxTypeEnum.�h��ʌ����h�A<BR>
     * �@@�@@�@@���@@�����̐ŋ敪�i�������n�j���iTaxTypeEnum.�h��������h�܂��́h<BR>
     *       ������������򒥎��h�j<BR>
     * �@@�@@�@@�̏ꍇ�A�u��ʌ����̌����͓�������ւ̍�������s�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00710
     * @@param l_taxType �ŋ敪�B<BR>
     * �@@�@@�@@�i* �����̐ŋ敪�j
     * @@param l_swapTaxType �ŋ敪�i�������n�j�B<BR>
     * �@@�@@�@@�i* ������^���n���̐ŋ敪�j
     * @@param l_blnIsLong (is����)<BR>
     * �@@�@@�@@�������n�Ώۂ̌����̔����^�����������t���O�B<BR>
     * �@@�@@�@@�����̏ꍇtrue�A�ȊOfalse�B
     * @@throws WEB3BaseException
     */
    public void validateSwapTaxType(
        TaxTypeEnum l_taxType, 
        TaxTypeEnum l_swapTaxType, 
        boolean l_blnIsLong) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSwapTaxType(l_taxType,l_swapTaxType,l_blnIsLong)";
        log.entering(STR_METHOD_NAME);
        
        if (!l_blnIsLong)
        {
            //������is���� �� false�̏ꍇ�́A����������return����B
            return;
        }
        
        if (TaxTypeEnum.NORMAL.equals(l_taxType) 
            && (TaxTypeEnum.SPECIAL.equals(l_swapTaxType) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_swapTaxType)))
        {
            //�����̐ŋ敪��TaxTypeEnum.�h��ʌ����h�A 
            //���@@�����̐ŋ敪�i�������n�j���iTaxTypeEnum.�h��������h�܂��́h������������򒥎��h�j�̏ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00710,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate��������J�݁i�M�p�j�j�B<BR>
     * <BR>
     * �M�p����̓���������J�݂��Ă��邩�ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�����̐ŋ敪���iTaxTypeEnum.�h��������h�܂��́h������������򒥎��h�j�̏ꍇ�́A<BR>
     * �@@�@@�@@����������return����B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ڋq.is��������J��(�����̎�n��, �����̕⏕����, "2�F�M�p���")���R�[������B<BR>
     * �@@�@@�@@�߂�l��false�̏ꍇ�́A�u��������̊J�݂Ȃ��v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01703
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�B
     * @@param l_taxType �ŋ敪�B<BR>
     * �@@�@@�@@�i* �ڋq�}�X�^.�M�p����ŋ敪�j
     * @@param l_datDeliveryDate ��n���B
     * @@throws WEB3BaseException
     * �i�������.��n���j
     */
    public void validateMarginSpecialAccountOpen(
        WEB3GentradeSubAccount l_subAccount, 
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMarginSpecialAccountOpen(l_subAccount,l_taxType,l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        if (!TaxTypeEnum.SPECIAL.equals(l_taxType) && !TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            //�����̐ŋ敪���iTaxTypeEnum.�h��������h�܂��́h������������򒥎��h�j�̏ꍇ�́A����������return����B
            return;
        }
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        if (!l_mainAccount.isSpecialAccountEstablished(
            l_datDeliveryDate,l_subAccount, WEB3GentradeEquityMarginDivDef.MARGIN))
        {
            //�ڋq.is��������J��(�����̎�n��, �����̕⏕����, "2�F�M�p���")���R�[������B
            //�߂�l��false�̏ꍇ��
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01703,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * �ivalidate���s�w��K���i�M�p�j�j�B<BR>
     * <BR>
     * ���s�w��K���`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ȉ��̂�����̏����ɂ����v���Ȃ��ꍇ�́A����������return����B<BR>
     * <BR>
     * �@@�@@�@@������is���s��true<BR>
     * �@@�@@�@@�����̎��s�������s�o���������s<BR>
     * <BR>
     * �@@�@@�@@��L�̂����ꂩ�̏����ɍ��v����ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@���s�w��K���`�F�b�N<BR>
     * �@@�@@�@@�������.is�M�p���s�w��K��(�����ٍ̕ϋ敪, �����̒����J�e�S��, ������is����)���R�[������B<BR>
     * �@@�@@�@@�߂�l��true�̏ꍇ�́A�u�M�p����E���s�w��K�����v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00712<BR>
     * <BR>
     * �R�j�@@MM�������s�w��`�F�b�N<BR>
     * �@@�@@�@@�s��(*).getMarketCode( )�ɂ��s��R�[�h���擾����B<BR>
     * �@@�@@�@@�擾�����s��R�[�h���hJASDAQ�h�A���@@�������.�X�����J�敪���h�}�[�P�b�g���C�N�����h<BR>
     * �@@�@@�@@�̏ꍇ�́A�uMM�����͐��s�w��s�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01352<BR>
     * <BR>
     * �@@�@@�@@(*)�s��I�u�W�F�N�g�́A�������.getMarket()�ɂ��擾����B
     * @@param l_tradedProduct ��������I�u�W�F�N�g�B
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_orderCateg �����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_blnIsMarketOrder (is���s)<BR>
     * �@@�@@�@@���s�����̏ꍇtrue�A�ȊOfalse�B
     * @@param l_blnIsShort (is����)<BR>
     * �@@�@@�@@�����^�����̃t���O�B<BR>
     * �@@�@@�@@�����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B<BR>
     * @@param l_executionCondition (���s����)<BR>
     * �@@�@@�@@���s�����B<BR>
     * @@throws WEB3BaseException
     */
    public void validateMarketOrderRestraint(
        WEB3EquityTradedProduct l_tradedProduct, 
        String l_strRepaymentType, 
        OrderCategEnum l_orderCateg, 
        boolean l_blnIsMarketOrder, 
        boolean l_blnIsShort,
        EqTypeExecutionConditionType l_executionCondition) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMarketOrderRestraint(l_tradedProduct,l_strRepaymentType,l_orderCateg,l_isMarketOrder,l_isShort,l_executionCondition)";
        log.entering(STR_METHOD_NAME);

        if ((l_blnIsMarketOrder == true) ||
            (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondition)))
        {
            ;
        }
        else
        {
            //�P�j�@@�i���s���� �܂��� �s�o���������s�����j�ȊO�̏ꍇ�́A����������return����B
            return;
        }

        // �Q�j�@@���s�w��K���`�F�b�N
        boolean l_blnIsMarketOrderDesinateCtrl =
            l_tradedProduct.isMarginMarketOrderDesignateCtrl(l_strRepaymentType, l_orderCateg, l_blnIsShort);     
        if (l_blnIsMarketOrderDesinateCtrl)
        {
            // �@@�@@�@@�������.is�M�p���s�w��K��(�����ٍ̕ϋ敪, �����̒����J�e�S��, ������is����)���R�[������B
            // �@@�@@�@@�߂�l��true�̏ꍇ�́A�u�M�p����E���s�w��K�����v�̗�O��throw����B
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00712,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �R�j�@@MM�������s�w��`�F�b�N
        if (WEB3MarketCodeDef.JASDAQ.equals(l_tradedProduct.getMarketCode()))
        {
            // �@@�@@�@@�擾�����s��R�[�h���hJASDAQ�h�A���@@�������.�X�����J�敪���h�}�[�P�b�g���C�N�����h
            // �@@�@@�@@�̏ꍇ�́A�uMM�����͐��s�w��s�v�̗�O��throw����B
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            if (WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01352,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate�������ځj�B<BR>
     * <BR>
     * ���������ɂāA�������ꂽ���ڂ������������`�F�b�N����B<BR>
     * �G���[���e�͈ȉ��̒ʂ�B<BR>
     * �@@�|�����������̊��������������������ꍇ�B<BR>
     * �@@�|�������������ꕔ�o���̏�ԂŁA������������萔�ȉ��̏ꍇ�B<BR>
     * �@@�|���������s�̎s��ŁA�����Ɠ��������Ώۍ��ڂ������ɒ�������Ă���ꍇ�B<BR>
     * �@@�|�h��������h���h�o����܂Œ����h�ցA�܂��́h�o����܂Œ����h���h��������h��<BR>
     * �@@�@@�@@��������Ă���ꍇ�B<BR>
     * �@@�|������������������Ă���ꍇ�B<BR>
     * �@@�|�s�ꔭ���ς̒����́A�t�w�l�����̏�������������Ă���ꍇ�B<BR>
     * �@@�|�������������牽�������������Ă��Ȃ��ꍇ�B<BR>
     * <BR>
     * -------------------------------------------------------------------<BR>
     * �P�j�@@���������`�F�b�N<BR>
     * �@@isChange�����i�����P�ʁA�����㊔���j���R�[�����A<BR>
     * �@@���������l�̃`�F�b�N���s���B<BR>
     * <BR>
     * �Q�j�@@�P���`�F�b�N<BR>
     * �@@isChange�P���i�����P�ʁA������w�l�j���R�[�����A<BR>
     * �@@�P�������l�̃`�F�b�N���s���B<BR>
     * <BR>
     * �R�j�@@���s�����ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange���s�����i�����P�ʁA�����㎷�s�����j���R�[������B<BR>
     * <BR>
     * �S�j�@@�����Ɠ��������Ώۍ���(*1)�𓯎��ɒ������Ă���ꍇ�A���������\�Ȏs�ꂩ�ǂ����̃`�F�b�N<BR>
     * <BR>
     * �@@(*1)���������Ώۍ���<BR>
     * �@@�@@�@@�E�P���i�w�l�j<BR>
     * �@@�@@�@@�E���s����<BR>
     * <BR>
     * �@@=======================================================================<BR>
     * �@@�S�|�P�j�@@��������x�e���ԑ�(*2)�A���� �O�ꔭ���ϒ���(*3)�̏ꍇ<BR>
     * <BR>
     * �@@�@@�s��ɒʒm�ς̒l���g�p���A�u���������v�ł��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@�i�����P��.�s�ꂩ��m�F�ς̐��� != �����㊔���j�̏ꍇ�̂݁A<BR>
     * �@@�@@�o�i�����P��.�s�ꂩ��m�F�ς̎w�l != ������w�l�j �܂���<BR>
     * �@@�@@�@@�i�����P��.�s�ꂩ��m�F�ς̎��s���� != �����㎷�s�����j�p�̂����ꂩ�ɊY������ꍇ��<BR>
     * �@@�@@�u���������v�Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*2)��������x�e���ԑт̏ꍇ<BR>
     * �@@�@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)�O�ꔭ���ϒ���<BR>
     * �@@�@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς̐��� != NaN�̏ꍇ�́A�O�ꔭ���ϒ����ł���Ɣ��肷��B<BR>
     * <BR>
     * �@@=======================================================================<BR>
     * �@@�S�|�Q�j�@@�S�|�P�j�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�����������P�ʂ̒l���g�p���A�u���������v�ł��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@isChange����( )��true��ԋp�����ꍇ�̂݁A<BR>
     * �@@�@@{isChange�P��( )�AisChange���s����( )}�̂����ꂩ��ł�true��ԋp���Ă����<BR>
     * �@@�@@�u���������v�Ɣ��肷��B<BR>
     * �@@=======================================================================<BR>
     * <BR>
     * �@@�u���������v�ɊY������ꍇ�̂݁A�g���v���_�N�g�}�l�[�W��.getMarket(�����P��.�s��ID)�ɂ�<BR>
     * �@@�s��I�u�W�F�N�g���擾����B<BR>
     * �@@�s��I�u�W�F�N�g.���������\�敪���h�������ړ��������s�h�̏ꍇ<BR>
     * �@@�́A�u�������ړ��������s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00687<BR>
     * <BR>
     * �T�j�@@�o����܂Œ������ǂ����i���������敪�j�ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChangeIs�o����܂Œ����i�����P�ʁA������is�o����܂Œ��� )���R�[������B<BR>
     * <BR>
     * �U�j�@@�l�i�����ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange�l�i����(�����P�ʁA������l�i�����A������w�l)���R�[������B<BR>
     * <BR>
     * �V�j�@@���������i�h�w��Ȃ��h�^�h�t�w�l�h�^�h�v�w�l�h�j�ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange���������i�����P�ʁA�����㔭������)���R�[������B<BR>
     * <BR>
     * �W�j�@@�t�w�l�����i�����������Z�q�A�t�w�l��l�j�ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange�t�w�l�����i�����P�ʁA�����㔭�������A������t�w�l��l�A�����㔭���������Z�q)���R�[������B<BR>
     * �@@<BR>
     * �X�j�@@�v�w�l�����i�����������Z�q�A�t�w�l��l�A�i�v�w�l�j�����w�l�j�ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange�v�w�l�����i�����P�ʁA�����㔭�������A�����㔭���������Z�q�A<BR>
     * �@@������t�w�l��l�A������iW�w�l�j�����w�l�A������iW�w�l�j���s���� )���R�[������B <BR>
     * <BR>
     * �P�O�j�@@�����L�������i�����㒍���������j�ɒ����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange����������(�����P�ʁA�����㒍��������)���R�[������B<BR>
     * <BR>
     * �P�P�j�@@�X�̌��ɑ΂���ԍώw�萔�ʁi�ԍϐ��ʓ���j�ɒ����������Ă��邩�̃`�F�b�N���s���B<BR>
     * �@@isChange���ϐ��ʓ���(�����P�ʁA�����㌈�ώw��G���g��)���R�[������B<BR>
     * <BR>
     * �P�Q�j�@@�����������Ă��邩�̃`�F�b�N<BR>
     * �@@isChange����( )�AisChange�P��( )�A<BR>
     * �@@isChange���s����( )�AisChange�l�i����( )�A<BR>
     * �@@isChange�t�w�l����( )�AisChange�v�w�l����( )�A<BR>
     * �@@isChange����������( )�AisChange���ϐ��ʓ���( )�̂��ׂĂ�false��ԋp�����ꍇ�A<BR>
     * �@@�������������牽����������Ă��Ȃ��Ɣ��f���A<BR>
     * �@@�u�������͂Ȃ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00039<BR>
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_dblModifiedQuantity (�����㊔��)<BR>
     * �@@�@@�@@�����㊔���B
     * @@param l_dblModifiedLimitPrice (������w�l)<BR>
     * �@@�@@�@@������w�l�B
     * @@param l_modifiedExecutionType (�����㎷�s����)<BR>
     * �@@�@@�@@�����㎷�s�����B
     * @@param l_strModifiedPriceConditionType (������l�i����)<BR>
     * �@@�@@�@@������l�i�����B
     * @@param l_strModifiedOrderConditionType (�����㔭������)<BR>
     * �@@�@@�@@�����㔭�������B
     * @@param l_strModifiedOrderCondOperator (�����㔭���������Z�q)<BR>
     * �@@�@@�@@�����㔭���������Z�q�B
     * @@param l_dblModifiedStopOrderPrice (������t�w�l��l)<BR>
     * �@@�@@�@@������t�w�l��l�B
     * @@param l_dblModifiedWLimitPrice (������iW�w�l�j�����w�l)<BR>
     * �@@�@@�@@������iW�w�l�j�����w�l�B
     * @@param l_modifiedWLimitExecCondType (������iW�w�l�j���s����)<BR>
     * �@@�@@�@@������iW�w�l�j���s����<BR>
     * @@param l_modifiedIsCarriedOrder (������is�o����܂Œ���)<BR>
     * �@@�@@�@@������̒������o����܂Œ������ǂ����𔻕ʂ���t���O�B<BR>
     * �@@�@@�@@true�F�@@�o����܂Œ���<BR>
     * �@@�@@�@@false�F�@@��������<BR>
     * @@param l_datModifiedExpirationDate (�����㒍��������)<BR>
     * �@@�@@�@@�����㒍���������B<BR>
     * @@param l_modifiedSettleContractEntries�i�����㌈�ώw��G���g���j<BR>
     * �@@�@@�@@�����㌈�ώw��G���g���̔z��B���ϒ����̏ꍇ�̂݃Z�b�g����B
     * @@throws WEB3BaseException
     */
    public void validateChangeItem(
        EqTypeOrderUnit l_orderUnit, 
        double l_dblModifiedQuantity, 
        double l_dblModifiedLimitPrice, 
        EqTypeExecutionConditionType l_modifiedExecutionType, 
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType, 
        String l_strModifiedOrderCondOperator, 
        double l_dblModifiedStopOrderPrice, 
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedWLimitExecCondType,
        boolean l_modifiedIsCarriedOrder,
        Date l_datModifiedExpirationDate,
        EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateChangeItem(l_orderUnit,l_dblModifiedQuantity,l_dblModifiedLimitPrice,l_modifiedExecutionType"
            + "l_strModifiedOrderConditionType,l_strModifiedOrderCondOperator,"
            + "l_dblModifiedStopOrderPrice,l_dblModifiedWLimitPrice,l_wLimitExecCondType,l_modifiedIsCarriedOrder";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //1)���������`�F�b�N
        boolean l_blnIsChangeQuantity = this.isChangeQuantity(l_orderUnit, l_dblModifiedQuantity);
        
        //2)�P���`�F�b�N
        boolean l_blnIsChangePrice = this.isChangePrice(l_orderUnit, l_dblModifiedLimitPrice);
        
        //3)���s�����ɒ����������Ă��邩�̃`�F�b�N 
        boolean l_blnIsChangeExecutionCondition =
            this.isChangeExecutionCondition(l_orderUnit, l_modifiedExecutionType);
        
        //4)�����Ɠ��������Ώۍ��ڂ𓯎��ɒ������Ă���ꍇ�A���������\�Ȏs�ꂩ�ǂ����̃`�F�b�N
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsSynChange = false;
        boolean l_blnIsInBreakAndOrdered = false;
        if (WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone() &&
            l_orderUnitRow.getConfirmedQuantity() != 0.0D)
        {
            l_blnIsInBreakAndOrdered = true;
            if (l_orderUnitRow.getConfirmedQuantity() != l_dblModifiedQuantity)
            {
                if ((l_orderUnitRow.getConfirmedPrice() != l_dblModifiedLimitPrice) ||
                    !(l_orderUnitRow.getConfirmedExecConditionType().equals(l_modifiedExecutionType)))
                {
                    l_blnIsSynChange = true;
                }
            }
        }
        else
        {
            if (l_blnIsChangeQuantity)
            {
                if (l_blnIsChangePrice || l_blnIsChangeExecutionCondition)
                {
                    l_blnIsSynChange = true;
                }
            }
        }
        if (l_blnIsSynChange)
        {
            try
            {
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                if (WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(l_marketRow.getChangeableType()))
                {
                    if (l_blnIsInBreakAndOrdered)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02197,
                            STR_METHOD_NAME);
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                            STR_METHOD_NAME);
                    }
                }              
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            }
        }
        
        //5)�o����܂Œ������ǂ����i���������敪�j�ɒ����������Ă��邩�̃`�F�b�N 
        boolean l_blnIsChangeCarrideOrder =
            this.isChangeIsCarriedOrder(l_orderUnit, l_modifiedIsCarriedOrder);
        
        //6)�l�i�����ɒ����������Ă��邩�̃`�F�b�N
        boolean l_blnIsChangePriceConditionType =
            this.isChangePriceConditionType(l_orderUnit, l_strModifiedPriceConditionType,
            l_dblModifiedLimitPrice);
                
        //7)���������i�h�w��Ȃ��h�^�h�t�w�l�h�^�h�v�w�l�h�j�ɒ����������Ă��邩�̃`�F�b�N 
        boolean l_blnIsChangeOrderConditionType =
            this.isChangeOrderConditionType(l_orderUnit, l_strModifiedOrderConditionType);
        
        //8)�t�w�l�����i�����������Z�q�A�t�w�l��l�j�ɒ����������Ă��邩�̃`�F�b�N 
        boolean l_blnIsChangeStopOrderCondition =
            this.isChangeStopOrderCondition(l_orderUnit, l_strModifiedOrderConditionType,
            l_dblModifiedStopOrderPrice, l_strModifiedOrderCondOperator);
        
        //9)isChange�v�w�l�����i�����P�ʁA�����㔭�������A�����㔭���������Z�q�A
        //       ������t�w�l��l�A������iW�w�l�j�����w�l�A������iW�w�l�j���s����)
        boolean l_blnIsChangeWLimitCondition =
            this.isChangeWLimitCondition(l_orderUnit, l_strModifiedOrderConditionType,
            l_strModifiedOrderCondOperator, l_dblModifiedStopOrderPrice, l_dblModifiedWLimitPrice, l_modifiedWLimitExecCondType);
        
        //10)�����L�������i�����㒍���������j�ɒ����������Ă��邩�̃`�F�b�N
        boolean l_blnIsChangeExpirationDate =
            this.isChangeExpirationDate(l_orderUnit, l_datModifiedExpirationDate);
        
        //11)�X�̌��ɑ΂���ԍώw�萔�ʁi�ԍϐ��ʓ���j�ɒ����������Ă��邩�̃`�F�b�N
        boolean l_blnIsChangeEachQuantityOfCloseContract =
            this.isChangeEachQuantityOfCloseContract(l_orderUnit, l_modifiedSettleContractEntries);
        
        //11)�����������Ă��邩�̃`�F�b�N 
        if (!l_blnIsChangeQuantity && !l_blnIsChangePrice 
            && !l_blnIsChangeExecutionCondition && !l_blnIsChangePriceConditionType
            && !l_blnIsChangeStopOrderCondition && !l_blnIsChangeWLimitCondition
            && !l_blnIsChangeExpirationDate && !l_blnIsChangeEachQuantityOfCloseContract)
        {
            // �@@isChange����( )�AisChange�P��( )�A
            // �@@isChange���s����( )�AisChange�l�i����( )�A
            // �@@isChange�t�w�l����( )�AisChange�v�w�l����( )�A
            // �@@isChange����������( )�AisChange���ϐ��ʓ���( )�̂��ׂĂ�false��ԋp�����ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00039,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �iisChange�����j�B<BR>
     * <BR>
     * �����ɒ����������������`�F�b�N����B�����ɒ���������ꍇ��true�A�ȊO��<BR>false��ԋp����B<BR>
     * �܂��A�����̒����l���s���ȏꍇ�͗�O���X���[����B<BR>
     * �����̒����l���s���ȏꍇ�͈ȉ��̒ʂ�B<BR>
     * �@@�|�����������̊��������������������ꍇ�B<BR>
     * �@@�|�������������ꕔ�o���̏�ԂŁA������������萔�ȉ��̏ꍇ�B<BR>
     * <BR>
     * �P�j�@@�����O�̊����𒴂��Ă��Ȃ����`�F�b�N���s���B<BR>
     * �i��������(*1)�@@���@@����.�����㊔���j�̏ꍇ�A<BR>
     * �@@�@@�u�����㊔�������������𒴉߁v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00036
     * <BR>
     * �Q�j�@@��萔���������������Ȃ��Ȃ����`�F�b�N���s���B<BR>
     * �i���ϊ���*2)�@@���@@����.�����㊔���j�̏ꍇ�A<BR>
     * �@@�@@�u�����㊔�������ϊ��������v�̗�O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00037<BR>
     * <BR>
     * �R�j�@@�l�̔�r<BR>
     * �@@�i�������� == ����.�����㊔���j�̏ꍇ�Afalse��ԋp����B�ȊO��true��ԋp����B<BR>
     * <BR>
     * (*1) ��������<BR>
     * ����.�����P��.getQuantity()<BR>
     * <BR>
     * (*2) ���ϊ���<BR>
     * ����.�����P��.getExecutedQuantity()
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g
     * @@param l_dblModifiedQuantity �����㊔��
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeQuantity(EqTypeOrderUnit l_orderUnit, double l_dblModifiedQuantity)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isChangeQuantity(EqTypeOrderUnit l_orderUnit, double l_dblModifiedQuantity)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //�����O�̊����𒴂��Ă��Ȃ����`�F�b�N���s���B 
        if (l_orderUnit.getQuantity() < l_dblModifiedQuantity)
        {
            //�i��������(*1)�@@���@@����.�����㊔���j�̏ꍇ�A
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00036,STR_METHOD_NAME);
        }
        
        //��萔���������������Ȃ��Ȃ����`�F�b�N���s���B 
        if (l_orderUnitRow.getExecutedQuantity() > l_dblModifiedQuantity)
        {
            //�i���ϊ���*2)�@@���@@����.�����㊔���j�̏ꍇ�A 
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00037,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);        
        if (l_orderUnit.getQuantity() == l_dblModifiedQuantity)
        {
            //�i�������� == ����.�����㊔���j�̏ꍇ�Afalse��ԋp����B
            return false;    
        }
        else
        {
            //�ȊO��true��ԋp����B 
            return true;
        }
    }
    
    /**
     * �iisChange�P���j�B<BR>
     * <BR>
     * �P���ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �i����.�����P��.getLimitPrice == ����.������w�l�j�̏ꍇ�A<BR>
     * false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g
     * @@param l_dblModifiedLimitPrice ������w�l
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangePrice(EqTypeOrderUnit l_orderUnit, double l_dblModifiedLimitPrice) 
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "isChangePrice(EqTypeOrderUnit l_orderUnit, double l_dblModifiedLimitPrice) ";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
                
        if (l_orderUnit.getLimitPrice() == l_dblModifiedLimitPrice)
        {
            //����.�����P��.getLimitPrice == ����.������w�l�j�̏ꍇ false��ԋp����B
            return false;         
        }
        else
        {
            return true;
        }
    }
    
    /**
     * �iisChange���s�����j�B<BR>
     * <BR>
     * ���s�����ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �E����.�����P��.���s������(�����O�̎��s����)�Ƃ���B<BR>
     * �E����.�����㎷�s�������i������̎��s�����j�Ƃ���B<BR>
     * <BR>
     * �P�j�@@���s�����ύX�`�F�b�N<BR>
     * �@@�|���������u�o����܂Œ����v(*1)�ł���A<BR>
     * �@@�@@�@@�i�����O�̎��s�����j�Ɓi������̎��s�����j����v���Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�u�o����܂Œ����͎��s�����̒����s�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00806<BR>
     * <BR>
     * �@@�|���������u�o����܂Œ����v(*1)�łȂ���΁A<BR>
     * �@@�@@�@@�i���Y�ڋq�̏،���ЂŎ戵�\�Ȏ��s����(*2)�j��<BR>
     * �@@�@@�@@�����ꂩ�ɂ̂ݕύX���邱�Ƃ��ł���B<BR>
     * �@@�@@�@@�i������̎��s�����j���ύX�ł��Ȃ����s�����̏ꍇ�́A<BR>
     * �@@�@@�@@�u�،���Ђ��戵�s�Ȏ��s�����v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00807<BR>   
     * <BR>
     * �@@(*1) �g�����������}�l�[�W��.is�o����܂Œ����P��(����.�����P��.�����P��ID) == true��<BR>
     * �@@�@@�@@�@@�ꍇ�́A�o����܂Œ����B<BR>
     * �@@�@@�@@�@@false�̏ꍇ�́A�o����܂Œ����ł͂Ȃ��B<BR>
     * �@@(*2)�戵�\��������.�戵�\���s�����擾( )�Ŏ擾�����l���A<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get���s����( )�ɂĕϊ�����Enum�l���g�p����B<BR>
     * <BR>
     * �Q�j�@@�����I��<BR>
     * �@@�i�����O�̎��s�����j == �i������̎��s�����j �̏ꍇfalse�A<BR>
     * �@@�ȊO��true��ԋp����B
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_modifiedExecutionType (�����㎷�s����)<BR>
     * �@@�@@�@@�����㎷�s�����B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeExecutionCondition(
        EqTypeOrderUnit l_orderUnit, 
        EqTypeExecutionConditionType l_modifiedExecutionType)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "isChangeExecutionCondition(EqTypeOrderUnit l_orderUnit, EqTypeExecutionConditionType l_modifiedExecutionType)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        // �@@�|���������u�o����܂Œ����v
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            //�@@�i�����O�̎��s�����j�Ɓi������̎��s�����j����v���Ă��Ȃ��ꍇ�́A
            // �u�o����܂Œ����͎��s�����̒����s�v�̗�O��throw����B
            if (l_modifiedExecutionType == null ||
                !l_modifiedExecutionType.equals(l_orderUnitRow.getExecutionConditionType()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00806,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        // �@@�|���������u�o����܂Œ����v(*1)�łȂ����
        else
        {  
            boolean l_blnreturn = false;
            AccountManager l_accountManager = (AccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = l_accountManager.getMainAccount(l_orderUnitRow.getAccountId());
            }
            catch (NotFoundException l_exp)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            log.debug("InstitutionCode() = " + l_strInstitutionCode);

            ProductTypeEnum l_productType = ProductTypeEnum.EQUITY;
            String l_strFuturesOptionDiv = WEB3MarginTradingDivDef.DEFAULT;
            String l_strMarginTradingDiv = WEB3MarginTradingDivDef.DEFAULT;
            String l_strMarketCode = null;
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                Market l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                l_strMarketCode = l_market.getMarketCode();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            WEB3GentradeHandlingOrderCond l_tradeOrderCond =
                new WEB3GentradeHandlingOrderCond(
                    l_strInstitutionCode,
                    l_productType,
                    l_strFuturesOptionDiv,
                    l_strMarginTradingDiv,
                    l_strMarketCode);
            
            // �戵�\��������.is�戵�\���s����()
            // (�d�l�Ɠ����ʂ𓾂���ׁA���Y���\�b�h�g�p)
            l_blnreturn = l_tradeOrderCond.isHandlingPossibleExecCond(l_modifiedExecutionType);
            // �i������̎��s�����j���ύX�ł��Ȃ����s�����̏ꍇ�́A
            // �u�،���Ђ��戵�s�Ȏ��s�����v�̗�O��throw����B
            if (!l_blnreturn)
            {         
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00807,
                    this.getClass().getName() + STR_METHOD_NAME);                        
            }
        }

        // �Q�j�@@�����I��
        // �@@�i�����O�̎��s�����j == �i������̎��s�����j �̏ꍇfalse�A
        // �@@�ȊO��true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        if (l_modifiedExecutionType.equals(l_orderUnitRow.getExecutionConditionType()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * �iisChangeIs�o����܂Œ����j�B<BR>
     * <BR>
     * �o����܂Œ������ǂ����i�u�o����܂Œ����v�łȂ�(����������)�^�u�o����܂Œ����v�j����������Ă��Ȃ����ǂ����`�F�b�N<BR>
     * ����B<BR>
     * <BR>
     * �P�j�@@�����������̔���<BR>
     * �������������u�o����܂Œ����v(*1)���ǂ����𔻒肷��B<BR>
     * <BR>
     * (*1)�g�����������}�l�[�W��.is�o����܂Œ����P��(����.�����P��)==true�̏ꍇ�A<BR>�u�o����܂Œ����v�ł���B<BR>
     * �@@�@@false�̏ꍇ�A�u�o����܂Œ����v�łȂ��B<BR>
     * <BR>
     * �Q�j�@@�������͂̔���<BR>
     * �����������u�o����܂Œ����v(*2)���ǂ����𔻒肷��B<BR>
     * <BR>
     * (*2)����.������is�o����܂Œ�����true�̏ꍇ�A�u�o����܂Œ����v�ł���B<BR>
     * false�̏ꍇ�A�u�o����܂Œ����v�łȂ��B<BR>
     * <BR>
     * �R�j�@@�u�o����܂Œ����v�w��ύX�`�F�b�N<BR>
     * �@@�i�����������̎w��j != �i�������͂̎w��j�̏ꍇ�́A<BR>
     * �@@�@@�u�o����܂Œ����̎w��͒����s�v�̗�O��throw����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00070
     * @@param l_orderUnit �����P��<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g
     * @@param l_modifiedIsCarriedOrder ������is�o����܂Œ���<BR>
     * �@@�@@�@@������̒������o����܂Œ������ǂ����𔻕ʂ���t���O�B<BR>
     * <BR>
     * �@@�@@�@@true�F�@@�o����܂Œ���<BR>
     * �@@�@@�@@false�F�@@��������
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeIsCarriedOrder(
        EqTypeOrderUnit l_orderUnit, 
        boolean l_modifiedIsCarriedOrder)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "isChangeIsCarriedOrder(EqTypeOrderUnit l_orderUnit, boolean l_modifiedIsCarriedOrder)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                
        //�����������̔��� 
        //�������������u�o����܂Œ����v(*1)���ǂ����𔻒肷��B 
               
        boolean l_blnOrginIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnit);
        if (l_blnOrginIsCarriedOrderUnit != l_modifiedIsCarriedOrder)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00070,STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * �iisChange���������j�B<BR>
     * <BR>
     * ���������i�h�w��Ȃ��h�^�h�t�w�l�h�^�h�v�w�l�h�j�ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����������̔����������擾����B<BR>
     * ����.�����P��.�����������擾����B�i�����O�̔��������j<BR>
     * <BR>
     * �Q�j�@@�������͂̔����������擾����B<BR>
     * ����.�����㔭�������i������̔��������j<BR>
     * <BR>
     * �R�j�@@���������ύX�`�F�b�N<BR>
     * �@@�i�����O�̔��������j != �i������̔��������j�̏ꍇ�́A<BR>
     * �@@�@@�u���������͒����s�v�̗�O��throw����B�ȊO�Afalse��ԋp����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00071<BR>
     * <BR>
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g
     * @@param l_strModifiedOrderConditionType �����㔭������
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeOrderConditionType(
        EqTypeOrderUnit l_orderUnit, 
        String l_strModifiedOrderConditionType)
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "isChangeOrderConditionType(EqTypeOrderUnit l_orderUnit, String l_strModifiedOrderConditionType) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //���������ύX�`�F�b�N
        if (l_strModifiedOrderConditionType == null ||
            !l_strModifiedOrderConditionType.equals(l_orderUnitRow.getOrderConditionType()))
        {
            //�i�����O�̔��������j != �i������̔��������j�̏ꍇ�́A
            //�u���������͒����s�v�̗�O��throw����B�ȊO�Afalse��ԋp����B
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00071,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        
        return false;
    }
    
    /**
     * �iisChange�t�w�l�����j�B<BR>
     * <BR>
     * �t�w�l�̏����i�t�w�l��l�A�����������Z�q�j�ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����.�����㔭������ ���h�t�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸false��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@�����������̋t�w�l��l�A�����������Z�q���擾����B<BR>
     * �@@�|����.�����P��.�t�w�l��l���擾�B�i�����O�̋t�w�l��l�j<BR>
     * �@@�|����.�����P��.�����������Z�q���擾�B�i�����O�̔����������Z�q�j<BR>
     * <BR>
     * �R�j�@@�������͂̋t�w�l��l�A�����������Z�q���擾����B<BR>
     * �@@�|����.������t�w�l��l�i������̋t�w�l��l�j<BR>
     * �@@�|����.�����㔭���������Z�q�i������̔����������Z�q�j<BR>
     * <BR>
     * �S�j�@@�t�w�l��l�A�����������Z�q�ύX�`�F�b�N<BR>
     * �@@�|�������������s�ꔭ����(*1)�̏ꍇ�̂݁A<BR>
     * �@@�@@�i�����O�̋t�w�l��l�j != �i������̋t�w�l��l�j�܂��́A<BR>
     * �@@�@@�i�����O�̔����������Z�q�j != �i������̔����������Z�q�j�ł���΁A<BR>
     * �@@�@@�u�s�ꔭ���ϒ����̋t�w�l��l�y�сA�����������Z�q�͒����s�v��<BR>
     * �@@�@@��O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00690<BR>
     * <BR>
     * (*1)�@@����.�����P��.���N�G�X�g�^�C�v == "�����T�[�o"�̏ꍇ�́A<BR>
     * �@@�@@�@@�������������s�ꔭ���ςƔ��肷��B<BR>
     * <BR>
     * �T�j�@@�����I��<BR>
     * �@@�i�����O�̋t�w�l��l�j == �i������̋t�w�l��l�j���A<BR>
     * �@@�i�����O�̔����������Z�q�j == �i������̔����������Z�q�j�@@�̏ꍇ<BR>
     * �@@false���A�ȊO��true��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����������i�������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strModifiedOrderConditionType - (�����㔭������)<BR>
     * �����㔭������<BR>
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * ������t�w�l��l<BR>
     * @@param l_strModifiedOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeStopOrderCondition(
        EqTypeOrderUnit l_orderUnit, 
        String l_strModifiedOrderConditionType, 
        double l_dblModifiedStopOrderPrice, 
        String l_strModifiedOrderCondOperator) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isChangeStopOrderCondition(EqTypeOrderUnit l_orderUnit, String l_strModifiedOrderConditionType, double l_dblModifiedStopOrderPrice, String l_strModifiedOrderCondOperator)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                 
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strModifiedOrderConditionType))
        {
            //����.�����㔭������ ���h�t�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸false��Ԃ��B    
            return false;
        }
        
        //�t�w�l��l�A�����������Z�q�ύX�`�F�b�N         
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
        {
            //�������������s�ꔭ����
            

            
            if ((l_strModifiedOrderCondOperator != null &&
                !l_strModifiedOrderCondOperator.equals(l_orderUnitRow.getOrderCondOperator()))
                || (l_strModifiedOrderCondOperator == null && l_orderUnitRow.getOrderCondOperator() != null)
                || l_dblModifiedStopOrderPrice != l_orderUnitRow.getStopOrderPrice())
            {
                //�i�����O�̋t�w�l��l�j != �i������̋t�w�l��l�j�܂��́A 
                //�i�����O�̔����������Z�q�j != �i������̔����������Z�q�j�ł���΁A 
                //�u�s�ꔭ���ϒ����̋t�w�l��l�y�сA�����������Z�q�͒����s�v�� ��O��throw����B 
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00690,STR_METHOD_NAME);
            }            
        }        
        //�����I��
        log.exiting(STR_METHOD_NAME);
        if (l_strModifiedOrderCondOperator.equals(l_orderUnitRow.getOrderCondOperator()) 
            && l_dblModifiedStopOrderPrice == l_orderUnitRow.getStopOrderPrice())
        {
            return false;            
        }
        else
        {
            return true;
        }
    }
    
    /**
     * �iisChangeW�w�l�����j<BR>
     * <BR>
     * W�w�l�̏����i�����������Z�q�A�t�w�l��l�A�iW�w�l�j�����w�l�j��<BR>
     * �����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����.�����㔭���������hW�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸false��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@�����������̔����������Z�q�A�t�w�l��l�A�iW�w�l�j�����w�l�A<BR>
     * �@@�@@�iW�w�l�j���s�������擾����B <BR>
     * ���������������e.get�����������P��( )�ɂĒ����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�|����.�����P��.�����������Z�q���擾�B�i�����O�̔����������Z�q�j<BR>
     * �@@�|����.�����P��.�t�w�l��l���擾�B�i�����O�̋t�w�l��l�j<BR>
     * �@@�|����.�����P��.�iW�w�l�j�����w�l���擾�B�i�����O�́iW�w�l�j�����w�l�j<BR>
     * �@@�|����.�����P��.�iW�w�l�j���s�������擾�B�i�����O�́iW�w�l�j���s�����j <BR>
     * <BR>
     * �R�j�@@�������͂̔����������Z�q�A�������͋t�w�l��l�A�������́iW�w�l�j�����w�l�A<BR>
     * �������́iW�w�l�j���s�������擾����B<BR>
     * �@@�|����.�����㔭���������Z�q�i������̔����������Z�q�j<BR>
     * �@@�|����.������t�w�l��l�i������̋t�w�l��l�j<BR>
     * �@@�|����.������iW�w�l�j�����w�l�i������́iW�w�l�j�����w�l�j<BR>
     * �@@�|����.������iW�w�l�j���s�����i������́iW�w�l�j���s�����j<BR>
     * <BR>
     * �S�j�@@�����I��<BR>
     * �@@�i�����O�̔����������Z�q�j == �i������̔����������Z�q�j ���A<BR>
     * �@@�i�����O�̋t�w�l��l�j == �i������̋t�w�l��l�j���A<BR>
     * �@@�i�����O�́iW�w�l�j�����w�l�j == �i������́iW�w�l�j�����w�l�j���A<BR>
     * �@@�i�����O�́iW�w�l�j���s�����j == �i������́iW�w�l�j���s�����j<BR> 
     * �@@�̏ꍇfalse�A�ȊO��true��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����������i�������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strModifiedOrderConditionType - (�����㔭������)<BR>
     * �����㔭������<BR>
     * @@param l_strModifiedOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q<BR>
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * ������t�w�l��l<BR>
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * ������iW�w�l�j�����w�l<BR>
     * @@param l_wLimitExecCondType - (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isChangeWLimitCondition(
        EqTypeOrderUnit l_orderUnit, 
        String l_strModifiedOrderConditionType, 
        String l_strModifiedOrderCondOperator, 
        double l_dblModifiedStopOrderPrice, 
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_wLimitExecCondType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isChangeWLimitCondition(EqTypeOrderUnit l_orderUnit, String l_strModifiedOrderConditionType, "
            + "String l_strModifiedOrderCondOperator, double l_dblModifiedStopOrderPrice, double l_dblModifiedWLimitPrice"
            + "EqTypeExecutionConditionType l_wLimitExecCondType)";
        log.entering(STR_METHOD_NAME);
          
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
  
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strModifiedOrderConditionType))
        {
            //����.�����㔭���������hW�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸false��Ԃ��B 
            return false;
        }
        
        //�����I��
        log.exiting(STR_METHOD_NAME);
        
        if (((l_strModifiedOrderCondOperator != null &&
            l_strModifiedOrderCondOperator.equals(l_orderUnitRow.getOrderCondOperator()))
            || l_strModifiedOrderCondOperator== null && l_orderUnitRow.getOrderCondOperator()==null)
            && l_dblModifiedStopOrderPrice == l_orderUnitRow.getStopOrderPrice()
            && l_dblModifiedWLimitPrice == l_orderUnitRow.getWLimitPrice()
            && l_wLimitExecCondType.equals(l_orderUnitRow.getWLimitExecCondType()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * �ivalidate��������J�݁j�B<BR>
     * <BR>
     * ���������̓���������J�݂��Ă��邩�ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�����̐ŋ敪���iTaxTypeEnum.�h��������h�܂��́h������������򒥎��h�j�̏ꍇ�́A<BR>
     * �@@�@@�@@����������return����B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�ڋq.is��������J��(�����̎�n��, �����̕⏕����, "1�F��������")���R�[������B<BR>
     * �@@�@@�@@�߂�l��false�̏ꍇ�́A�u��������̊J�݂Ȃ��v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00711
     * @@param l_subAccount �⏕�����I�u�W�F�N�g�B
     * @@param l_taxType �ŋ敪�B<BR>
     * �@@�@@�@@�i* �ڋq�}�X�^.�ŋ敪�j
     * @@param l_datDeliveryDate ��n���B<BR>
     * �@@�@@�@@�i�������.��n���j
     * @@throws WEB3BaseException
     */
    public void validateSpecialAccountEstablish(
        WEB3GentradeSubAccount l_subAccount, 
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSpecialAccountEstablish(l_subAccount,l_taxType,l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME);
        }
                
        if (!TaxTypeEnum.SPECIAL.equals(l_taxType) && !TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            //�����̐ŋ敪���iTaxTypeEnum.�h��������h�܂��́h������������򒥎��h�j�̏ꍇ��
            //����������return����
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        boolean l_blnEstablished =
            l_mainAccount.isSpecialAccountEstablished(
            l_datDeliveryDate, l_subAccount, WEB3GentradeEquityMarginDivDef.EQUITY);
        if (!l_blnEstablished)
        {
            //�ڋq.is��������J��(�����̎�n��, �����̕⏕����, "1�F��������")���R�[������
            //�߂�l��false�̏ꍇ�́A�u��������̊J�݂Ȃ��v�̗�O��throw����B
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                STR_METHOD_NAME,WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                + WEB3ErrorReasonCodeDef.SPEC_ACCOUNT_ERROR);
        }        
    }

    /**
     * �ivalidate�����R�[�h�i�~�j���j�j�B<BR>
     * <BR>
     * �~�j�������R�[�h�̃`�F�b�N���s���B<BR>
     * <BR>
     * �����̖����R�[�h�C�،���ЃR�[�h�ɊY�����銔�������I�u�W�F�N�g���擾���ԋp����B<BR>
     * ���������I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00717<BR>
     * @@param l_strProductCode �����R�[�h�B
     * @@param l_strInstitutionCode �،���ЃR�[�h�B
     * @@return WEB3EquityProduct 
     */
    public WEB3EquityProduct validateMiniStockProductCode(String l_strProductCode, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMiniStockProductCode(String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityProduct l_equityProduct;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        try
      {
            //get �،����
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution(
                    l_strInstitutionCode);

            WEB3EquityProductManager l_eqTypeProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //get ����
            l_equityProduct =
                (WEB3EquityProduct) l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
      }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00717, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }
        log.exiting(STR_METHOD_NAME);
        return l_equityProduct;
    }
    
    /**
     * �ivalidate��������i�~�j���j�j�B<BR>
     * <BR>
     * �~�j����������̃`�F�b�N���s���B<BR>
     * �@@�|�������~�j������舵���Ă��邩�̃`�F�b�N�i�~�j���s��`�F�b�N�j<BR>
     * �@@�|�~�j����������~����Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * <BR>
     * �P�j�@@�~�j���s��擾<BR>
     * �@@��������.get�~�j���s��()�ɂāA�s��I�u�W�F�N�g���擾����B<BR>
     * null���ԋp���ꂽ�ꍇ�́A�~�j������舵���Ă��Ȃ������ł���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00718<BR>
     * <BR>
     * �Q�j�@@��������擾<BR>
     * �@@�v���_�N�g�}�l�[�W��.getTradedProduct()�ɂāA��������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getTradedProduct()�Ɏw�肷�����]<BR>
     * �@@product�F�@@��������<BR>
     * �@@market�F�@@�s��<BR>
     * <BR>
     * �R�j�@@�~�j����������~����Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * �@@�i�������.is�~�j�������K��() == true�j�̏ꍇ�A�~�j������~���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00719<BR>
     * <BR>
     * �@@[is�~�j�������K��()�Ɏw�肷�����]<BR>
     * �@@is�������F�@@is������<BR>
     * <BR>
     * �@@��������I�u�W�F�N�g��ԋp����B
     * @@param l_product ���������B
     * @@param l_blnIsSellOrder is�������B<BR>
     * �@@�@@�@@���������ǂ����𔻒肷��t���O <BR>
     * <BR>
     * �@@�@@�@@true�F�@@������ <BR>
     * �@@�@@�@@false�F�@@������
     * @@return WEB3EquityTradedProduct 
     */
    public WEB3EquityTradedProduct validateMiniStockTradedProduct(
        WEB3EquityProduct l_product, boolean l_blnIsSellOrder)  
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMiniStockTradedProduct(WEB3EquityProduct, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_product == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);             
        }
        Market l_market;
        WEB3EquityTradedProduct l_equityTradedProduct;
        try 
        {
            l_market = l_product.getMiniStockMarket();
            
            if (l_market == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00718, 
                        this.getClass().getName() + STR_METHOD_NAME);
            }
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            
            WEB3EquityProductManager l_eqTypeProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_eqTypeProductManager.getTradedProduct(l_product, l_market);
        } 
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
        if ( l_equityTradedProduct.isMiniStockTradeControl(l_blnIsSellOrder) )
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00719,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityTradedProduct;
    }

    /**
     * �ivalidate�~�j�����t�����j�B<BR>
     * <BR>
     * �~�j�����t���������̃`�F�b�N���s���B<BR>
     * �@@�|0�܂��̓}�C�i�X�l�łȂ����Ƃ̃`�F�b�N<BR>
     * �@@�|�����̔����P�ʂ𒴂��Ă��Ȃ����̃`�F�b�N�i������ʃ`�F�b�N�j<BR>
     * �@@�|�~�j���̔����P�ʂ̐����{�ɂȂ��Ă��邩�̃`�F�b�N�i�����P�ʃ`�F�b�N�j<BR>
     * <BR>
     * �P�j�@@0�܂��̓}�C�i�X�l�łȂ����Ƃ̃`�F�b�N<BR>
     * �@@super.validateQuantity(�����Fdouble)�ɂāA<BR>
     * ������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B<BR>
     * <BR>
     * �Q�j�@@������ʃ`�F�b�N<BR>
     * �@@�������.get�����P��()�ɂāA���������P�ʂ��擾����B<BR>
     * �@@�i�������.get�����P��() <= �����j�̏ꍇ�A<BR>
     * �@@��������𒴂��Ă���Ɣ��肵��O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00720<BR>
     * <BR>
     * �R�j�@@�����P�ʃ`�F�b�N  <BR>
     * �@@�������.get�~�j�������P��()�ɂāA�~�j���̔����P�ʂ��擾����B<BR>
     * �@@���ʂ������P�ʂŊ���؂�Ȃ��i���� % �������.get�~�j�������P��() != 0�j�ꍇ�A<BR>
     * �@@�����P�ʂ��s���ł���Ɣ��肵��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00721<BR>
     * @@param l_tradedProduct �������
     * @@param l_dblQuantity ����
     */
    public void validateMiniStockBuyQuantity(WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMiniStockBuyQuantity(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        try 
        {
            super.validateQuantity(l_dblQuantity);
        } 
        catch (OrderValidationException l_ovex)
        {
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00709,STR_METHOD_NAME);
        }    
        
        if (l_tradedProduct.getLotSize() <= l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00720,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_dblQuantity % l_tradedProduct.getMiniStockLotSize() != 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00721,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * �ivalidate�~�j�����t�����j�B<BR>
     * <BR>
     * �~�j�����t���������̃`�F�b�N���s���B<BR>
     * �@@�|0�܂��̓}�C�i�X�l�łȂ����Ƃ̃`�F�b�N<BR>
     * �@@�|���t�\�����`�F�b�N<BR>
     * �@@�|�����P�ʃ`�F�b�N�@@�����ʂ������P�ʂłȂ��ꍇ�̂݁B<BR>
     * <BR>
     * �P�j�@@0�܂��̓}�C�i�X�l�łȂ����Ƃ̃`�F�b�N<BR>
     * �@@super.validateQuantity(�����Fdouble)�ɂāA<BR>
     *   ������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B<BR>
     *  <BR>
     * �Q�j�@@��c�������v�i�ۗL�����j�擾<BR>
     * �@@�|�W�V�����}�l�[�W��.get�~�j���ۗL����()�ɂāA��c�������v���擾����B<BR>
     * <BR>
     * �@@[get�~�j���ۗL����()�Ɏw�肷�����]<BR>
     * �@@�����h�c�F�@@�⏕����.getAccountId()<BR>
     * �@@�⏕�����h�c�F�@@�⏕����.getSubAccountId()<BR>
     * �@@�����h�c�F�@@��������.getProductId()<BR>
     * <BR>
     * �R�j�@@���t�����������擾<BR>
     * �@@�����}�l�[�W��.get�~�j������������()�ɂāA���t�������������擾����B<BR>
     *  <BR>
     * �@@[get�~�j������������()�Ɏw�肷�����]<BR>
     * �@@�����h�c�F�@@�⏕����.getAccountId()<BR>
     * �@@�⏕�����h�c�F�@@�⏕����.getSubAccountId()<BR>
     * �@@�����h�c�F�@@��������.getProductId()<BR>
     * �@@is�������F�@@true<BR>
     * <BR>
     * �S�j�@@���t�\�����v�Z<BR>
     * �@@�ȉ��̌v�Z���ŁA���t�\�������擾����B<BR>
     * <BR>
     * �@@���t�\���� = ��c�������v�i�ۗL�����j - ���t����������<BR>
     * <BR>
     * �T�j�@@���t�\�����`�F�b�N<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�΁A���t�\���ʂ𒴉߂��Ă���Ɣ��肵�A
     * �@@��O���X���[����B<BR>
     *  <BR>
     * �@@[Error����]<BR>
     * �@@�i���t�\�����@@< �����j<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00167<BR>
     * <BR>
     * �U�j�@@�����P�ʃ`�F�b�N<BR>
     * �@@�����ʂ������P�ʂłȂ��i���� % �������.get�~�j�������P��() != 0�j�ꍇ
     *   �̂ݓ��Y�`�F�b�N���s���B<BR>
     *   <BR>
     *  �@@�ȉ��̏����ɓ��Ă͂܂�΁A�����P�ʂ��s���ł���Ɣ��肵��O���X���[����B<BR>
     *   <BR>
     *  �@@[Error����]<BR>
     *  �@@�i���t�\���� �� �������.get�~�j�������P��()�j != <BR>
     * �@@�i���� �� �������.get�~�j�������P��()�j  <BR> 
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00723<BR>
     * @@param l_subAccount (�⏕����)>
     * @@param l_tradedProduct �������
     * @@param l_dblQuantity ����
     */
    public void validateMiniStockSellQuantity(
        WEB3GentradeSubAccount l_subAccount,WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMiniStockBuyQuantity(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        try 
        {
            super.validateQuantity(l_dblQuantity);
        } 
        catch (OrderValidationException l_ovex)
        {
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00709,STR_METHOD_NAME);
        }    
        if (l_tradedProduct.getLotSize() <= l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00720,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�����|�W�V�����}�l�[�W�����擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        // �Q�j�@@��c�������v�i�ۗL�����j�擾
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_tradedProduct.getProduct();
        double l_dblMiniStockQuantity = l_positionManager.getMiniStockQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_equityProduct.getProductId()
            );
        //�R�j�@@���t�����������擾
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        double l_dblOrderingQuantity = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_equityProduct.getProductId(),
            true
            );
        //�S�j�@@���t�\�����v�Z
        double l_dblSellpossibleQuantity = l_dblMiniStockQuantity - l_dblOrderingQuantity;
        
        if (l_dblSellpossibleQuantity < l_dblQuantity)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00167,STR_METHOD_NAME);
        }
        if (l_dblQuantity % l_tradedProduct.getMiniStockLotSize() != 0)
        {
            if ( (l_dblSellpossibleQuantity % l_tradedProduct.getMiniStockLotSize()) != 
                (l_dblQuantity % l_tradedProduct.getMiniStockLotSize()))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00723,STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�~�j���d�������j�B<BR>
     * <BR>
     * ������ɓ�������̃~�j����������Ă��Ȃ����`�F�b�N���s���B<BR>
     * �� �~�j���́A������ɓ�������𔄔��ł��Ȃ��B<BR>
     * <BR>
     * �ȉ��̏����Ŋ��������P�ʃe�[�u������������B<BR>
     *  <BR>
     * �@@[����]<BR>
     * �@@���������P��.�����h�c = �⏕����.getAccountId() And<BR>
     * �@@���������P��.�⏕�����h�c = �⏕����.getSubAccountId() And<BR>
     * �@@���������P��.�����h�c = �������.getProduct().getProductId() And<BR>
     * �@@���������P��.��n�� = �������.getDailyDeliveryDate() And<BR>
     * �@@���������P��.������� != OrderStatusEnum.CANCELLED And<BR>
     * �@@���������P��.�����敪 != INVALIDATED_BY_MKT And<BR>
     * �@@�i���������P��.������� = OrderTypeEnum.MINI_STOCK_BUY Or<BR>
     * �@@�@@���������P��.������� = OrderTypeEnum.MINI_STOCK_SELL�j<BR>
     *  <BR>
     * �@@�Y������s�����݂���ꍇ�́A<BR>
     * �@@�@@������ɓ�������̒���������Ă���Ɣ��肵��O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00728<BR>
     * @@param l_subAccount (�⏕����)
     * @@param l_tradedProduct (�������)
     */
    public void validateMiniStockDuplicateOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "validateMiniStockDuplicateOrder(WEB3GentradeSubAccount, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");                                   //�����h�c
        l_sbWhere.append(" and sub_account_id = ? ");                        //�⏕�����h�c
        l_sbWhere.append(" and product_id = ? ");                              //�����h�c
        l_sbWhere.append(" and delivery_date = ? ");                         //��n��
        l_sbWhere.append(" and order_status <> ? ");                          //�������
        l_sbWhere.append(" and expiration_status <> ? ");                    //�����敪
        l_sbWhere.append(" and ( order_type = ? or order_type = ?)"); //�������

            
        String l_lngAccountId = String.valueOf(l_subAccount.getAccountId());
        String l_lngSubAccountId = String.valueOf(l_subAccount.getSubAccountId());
        String l_lngProductId = String.valueOf(l_tradedProduct.getProduct().getProductId());
        Date l_datDailyDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        String  l_orderStatus = String.valueOf(OrderStatusEnum.CANCELLED.intValue());
        String  l_orderExpirationStatus = String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue());
        String l_orderTypeBuy =String.valueOf( OrderTypeEnum.MINI_STOCK_BUY.intValue());
        String  l_orderTypeSell = String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue());
        
        Object[] l_objEqtypeOrderUnitWhere = { 
            l_lngAccountId,            
            l_lngSubAccountId,        
            l_lngProductId,
            l_datDailyDeliveryDate,
            l_orderStatus,
            l_orderExpirationStatus,
            l_orderTypeBuy,
            l_orderTypeSell   
            }; 

        List l_lisRecords = null;
        QueryProcessor l_QueryProcessor = null;
        try 
        {
            l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objEqtypeOrderUnitWhere);
        }
        catch (DataFindException l_e) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }

        if (l_lisRecords.size() > 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00728, STR_METHOD_NAME);
        }
            
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�~�j�����t�������Ԓ��������j�B<BR>
     * <BR>
     * ���t�������Ԓ��̏ꍇ�A�P�ʊ����ȏ�̒���������Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���������I�u�W�F�N�g�擾<BR>
     * �@@�������.getProduct()�ɂāA���������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�������Ԃ��̔���<BR>
     * �@@�|��������.get��������()�ɂČ����������擾����B<BR>
     * �@@�|�������ԓ����łȂ���΁A�������I������ireturn�j�B<BR>
     *   <BR>
     * �@@�� �������ԓ�<BR>
     * �@@���������̂S�c�Ɠ��O <= �������.getBaseDate() <= ���������̂P�c�Ɠ��O<BR>
     * <BR>
     * �R�j�@@�����P�ʂ��擾����B<BR>
     * �@@�������.get�����P��()�ɂāA�����P�ʂ��擾����B<BR>
     * <BR>
     * �S�j�@@�P�ʖ����������擾����B<BR>
     * �@@this.get�P�ʖ�������()�ɂāA�P�ʖ����������擾����B<BR>
     * <BR>
     * �@@[get�P�ʖ�������()�Ɏw�肷�����] <BR
     * �@@�⏕�����F�@@�⏕����<BR>
     * �@@���������F�@@��������<BR>
     * �@@�����P�ʁF�@@�����P��<BR>
     * <BR>
     * �T�j�@@�������`�F�b�N����<BR>
     * �@@�@@�@@�ȉ��̏����ɂ��Ă͂܂�ꍇ�A<BR>
     * �@@�@@�@@���t�������Ԓ��ɒP�ʊ����ȏ㒍�����ꂽ�Ɣ��肵��O���X���[����B<BR>
     * �@@�@@�@@[Error����]<BR>
     * �@@�@@�@@�i�P�ʖ������� + �����j >= �������.get�����P��()<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00724
     * @@param l_subAccount (�⏕����)
     * @@param l_tradedProduct (�������)
     * @@param l_dblQuantity (����)<BR>
     * ��������
     */
    public void validateMiniStockBuyDeregTermQuantity(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMiniStockBuyDeregTermQuantity(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@���������I�u�W�F�N�g�擾
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_tradedProduct.getProduct();
        //�Q�j�@@�������Ԃ��̔���
        Date l_datDevidendRightDate = l_equityProduct.getDevidendRightDate();
        Timestamp l_tsLast = new Timestamp(l_datDevidendRightDate.getTime());
        WEB3GentradeBizDate l_bizDateLast = new WEB3GentradeBizDate(l_tsLast);
        Date l_datLast = l_bizDateLast.roll(-4);
        Timestamp l_tsNext = new Timestamp(l_datDevidendRightDate.getTime());
        WEB3GentradeBizDate l_bizDateNext = new WEB3GentradeBizDate(l_tsNext);
        Date l_datNext = l_bizDateNext.roll(-1);
                
        int l_intResultsLast = WEB3DateUtility.compareToDay(
            l_datLast, 
            l_tradedProduct.getBaseDate()
            );
        int l_intResultsNext = WEB3DateUtility.compareToDay(
            l_datNext, 
            l_tradedProduct.getBaseDate()
            );    
        if (l_intResultsLast <= 0 && l_intResultsNext >= 0)
        {
            //�S�j�@@�P�ʖ����������擾����
            double l_oddLotQuantity = this.getOddLotQuantity(
                l_subAccount,
                l_equityProduct,
                l_tradedProduct.getLotSize()
                );
            //�T�j�@@�������`�F�b�N����
            if (l_oddLotQuantity + l_dblQuantity >= l_tradedProduct.getLotSize())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00724, STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�~�j�����t�������Ԓ��������j�B<BR>
     * <BR>
     * ���t�������Ԓ��̏ꍇ�A�P�ʊ��̔�����������Ă��Ȃ����`�F�b�N���s���B<BR>
     *  <BR>
     * �P�j�@@���������I�u�W�F�N�g�擾<BR>
     * �@@�������.getProduct()�ɂāA���������I�u�W�F�N�g���擾����B<BR>
     *  <BR>
     * �Q�j�@@�������Ԃ��̔���<BR>
     * �@@�|��������.get��������()�ɂČ����������擾����B<BR>
     * �@@�|�������ԓ����łȂ���΁A�������I������ireturn�j�B<BR>
     *  <BR>
     * �@@�� �������ԓ�<BR>
     * �@@�������� <= �������.getBaseDate() <= ���������̂R�c�Ɠ��� <BR>
     *  <BR>
     * �R�j�@@�����P�ʂ��擾����B<BR>
     * �@@�������.get�����P��()�ɂāA�����P�ʂ��擾����B<BR>
     *  <BR>
     * �S�j�@@�P�ʖ����������擾����B<BR>
     * �@@this.get�P�ʖ�������()�ɂāA�P�ʖ����������擾����B<BR>
     *  <BR>
     * �@@[get�P�ʖ�������()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�⏕����<BR>
     * �@@���������F�@@��������<BR>
     * �@@�����P�ʁF�@@�����P��<BR>
     *  <BR>
     * �T�j�@@�������`�F�b�N����<BR>
     * �@@�ȉ��̏����ɂ��Ă͂܂�ꍇ�A
     *   ���t�������Ԓ��ɒP�ʊ��̔����������ꂽ�Ɣ��肵��O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00725<BR>
     *   <BR>
     * �@@[Error����]  <BR>
     * �@@�P�ʖ������� �� ���� <BR>
     * @@param l_subAccount (�⏕����)
     * @@param l_tradedProduct (�������)
     * @@param l_dblQuantity (����)
     * �@@�@@�@@��������
     */
    public void validateMiniStockSellDeregTermQuantity(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct, double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMiniStockSellDeregTermQuantity(WEB3GentradeSubAccount, WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);             
        }
        //�P�j�@@���������I�u�W�F�N�g�擾
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_tradedProduct.getProduct();
        //�Q�j�@@�������Ԃ��̔���
        Date l_datLast = l_equityProduct.getDevidendRightDate();
        Timestamp l_tsLast = new Timestamp(l_datLast.getTime());
        WEB3GentradeBizDate l_bizDateLast = new WEB3GentradeBizDate(l_tsLast);
        Date l_datNext = l_bizDateLast.roll(3);
        int l_intResultsLast = WEB3DateUtility.compareToDay(
            l_datLast, 
            l_tradedProduct.getBaseDate()
            );
        int l_intResultsNext = WEB3DateUtility.compareToDay(
            l_datNext, 
            l_tradedProduct.getBaseDate()
            );    
        if (l_intResultsLast <= 0 && l_intResultsNext >= 0)
        {
            //�S�j�@@�P�ʖ����������擾����
            double l_oddLotQuantity = this.getOddLotQuantity(
                l_subAccount,
                l_equityProduct,
                l_tradedProduct.getLotSize()
                );
            //�T�j�@@�������`�F�b�N����

            if (l_oddLotQuantity < l_dblQuantity)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00725, STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * �iget�P�ʖ��������j�B<BR>
     * <BR>
     * �P�ʖ����������擾����B<BR>
     * <BR>
     * �P�j�@@��c�������v�i�ۗL�����j�擾 <BR>
     * �@@�|�W�V�����}�l�[�W��.get�~�j���ۗL����()�ɂāA��c�������v���擾����B<BR>
     * <BR>
     * �@@[get�~�j���ۗL����()�Ɏw�肷�����]<BR>
     * �@@�����h�c�F�@@�⏕����.getAccountId()<BR>
     * �@@�⏕�����h�c�F�@@�⏕����.getSubAccountId()<BR>
     * �@@�����h�c�F�@@��������.getProductId()<BR>
     * <BR>
     * �Q�j�@@���t�����������擾<BR>
     * �@@�����}�l�[�W��.get�~�j������������()�ɂāA���t�������������擾����B<BR>
     * <BR> 
     * �@@[get�~�j������������()�Ɏw�肷�����]<BR>
     * �@@�����h�c�F�@@�⏕����.getAccountId()<BR>
     * �@@�⏕�����h�c�F�@@�⏕����.getSubAccountId()<BR>
     * �@@�����h�c�F�@@��������.getProductId()<BR>
     * �@@is�������F�@@false<BR>
     * <BR>
     * �R�j�@@���t�����������擾<BR>
     * �@@�����}�l�[�W��.get�~�j������������()�ɂāA���t�������������擾����B<BR>
     * <BR>
     * �@@[get�~�j������������()�Ɏw�肷�����]<BR>
     * �@@�����h�c�F�@@�⏕����.getAccountId()<BR>
     * �@@�⏕�����h�c�F�@@�⏕����.getSubAccountId()<BR>
     * �@@�����h�c�F�@@��������.getProductId()<BR>
     * �@@is�������F�@@true<BR>
     * <BR>
     * �S�j�@@�P�ʖ��������v�Z<BR>
     * �@@�ȉ��̌v�Z���ɂāA�P�ʖ����������v�Z���ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i��c�������v + ���t���������� - ���t�����������j �� �����P��
     * @@param l_subAccount (�⏕����)
     * @@param l_product (��������)
     * @@param l_dblLotSize (�����P��)
     * @@return double
     */
    public double getOddLotQuantity(
        WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product, double l_dblLotSize) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOddLotQuantity(WEB3GentradeSubAccount, WEB3EquityProduct, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_product == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);             
        }
        //�����|�W�V�����}�l�[�W�����擾
         FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         TradingModule l_tradingModule =
             l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
         WEB3EquityPositionManager l_positionManager =
             (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        double l_dblMiniStockQuantity = l_positionManager.getMiniStockQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_product.getProductId()
            );
            
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();    
        double l_orderingQuantityBuy = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_product.getProductId(),
            false        
            );
        double l_orderingQuantitySell = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(),
            l_product.getProductId(),
            true        
            );
        double l_oddLotQuantity =
            (l_dblMiniStockQuantity + l_orderingQuantityBuy - l_orderingQuantitySell) % l_dblLotSize;
        
        log.exiting(STR_METHOD_NAME);     
        return l_oddLotQuantity;
    }

    /**
     * �iisChange�����������j�B<BR>
     * <BR>
     * �����������ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �E����.�����P��.�����������t��(�����O�̒���������)�Ƃ���B<BR>
     * �E����.�����㒍�����������i������̒����������j�Ƃ���B<BR>
     * <BR>
     * �i�����O�̒����������j == �i������̒����������j �̏ꍇfalse�A<BR>
     * �ȊO��true��ԋp����B
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_datExpirationDate (�����㒍��������)<BR>
     * �@@�@@�@@�����㒍���������B
     * @@return boolean
     */
    protected boolean isChangeExpirationDate(
        EqTypeOrderUnit l_orderUnit,
        Date l_datExpirationDate)
    {
        final String STR_METHOD_NAME = "isChangeExpirationDate(EqTypeOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        Timestamp l_tsExpirationDate = new Timestamp(l_datExpirationDate.getTime());
        log.exiting(STR_METHOD_NAME);
        return (l_orderUnit.getExpirationTimestamp().equals(l_tsExpirationDate) == false);
    }

    /**
     * �iisChange�l�i�����j�B<BR>
     * <BR>
     * �l�i�����ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����������̒l�i�����A�w�l���擾����B<BR>
     * ����.�����P��.�l�i�������擾����B�i�����O�̒l�i�����j<BR>
     * ����.�����P��.�w�l���擾����B�i�����O�̎w�l�j<BR>
     * <BR>
     * �Q�j�@@�������͂̒l�i�����A�w�l���擾����B<BR>
     * ����.������l�i�����i������̒l�i�����j<BR>
     * ����.������w�l�i������̎w�l�j<BR>
     * <BR>
     * �R�j�@@�l�i�����ύX�`�F�b�N<BR>
     * <BR>
     * �R�|�P�j�@@�i�����O�̒l�i�����j == �i������̒l�i�����j�̏ꍇ�́Afalse��ԋp����B<BR>
     * <BR>
     * �R�|�Q�j�@@�i�����̒����P��.�s�ꂩ��m�F�ς̐��� != Double.NaN�j�i���ꒆ�̓��������j<BR>
     * �@@�@@�@@�@@�@@�̏ꍇ�́A�i������̎w�l�j��0�i���w�l�����j�̏ꍇ�̂݁A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�i�����O�̒l�i�����j != �i������̒l�i�����j�ł����Ă��A<BR>
     * �@@�@@�@@�@@�@@�i�����O�̎w�l�j == �i������̎w�l�j�̏ꍇ�́Afalse��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���l�𒍕����w�l�����ւ̒������ɂ́A�P���̒������Ȃ��ꍇ�́u�����Ȃ��v�Ƃ���B<BR>
     * �@@�@@�@@�@@�@@���l�i�������̒����i���s�c���w�l�����s�c������A���j�́u��������v�Ƃ���B<BR>
     * <BR>
     * �R�|�R�j�@@��L�ȊO�̏ꍇ�́Atrue��ԋp����B
     * @@param l_orderUnit (�����P��)<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_strPriceConditionType (������l�i����)<BR>
     * �@@�@@�@@������l�i�����B
     * @@param l_dblLimitPrice (������w�l)<BR>
     * �@@�@@�@@������w�l�B
     * @@return boolean
     */
    protected boolean isChangePriceConditionType(
        EqTypeOrderUnit l_orderUnit,
        String l_strPriceConditionType,
        double l_dblLimitPrice)
    {
        final String STR_METHOD_NAME = "isChangePriceConditionType(EqTypeOrderUnit, String, double)";
        log.entering(STR_METHOD_NAME);

        // �R�j�@@�l�i�����ύX�`�F�b�N
        // �R�|�P�j�@@�i�����O�̒l�i�����j == �i������̒l�i�����j�̏ꍇ�́Afalse��ԋp����B
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_strPriceConditionType == null ||
            l_strPriceConditionType.equals(l_orderUnitRow.getPriceConditionType()))
        {
            return false;
        }

        // �R�|�Q�j�@@�i�����̒����P��.�s�ꂩ��m�F�ς̐��� != Double.NaN�j�i���ꒆ�̓��������j
        // �@@�@@�@@�@@�@@�̏ꍇ�́A�i������̎w�l�j��0�i���w�l�����j�̏ꍇ�̂݁A�ȉ��̃`�F�b�N���s���B
        // �@@�@@�@@�@@�@@�i�����O�̒l�i�����j != �i������̒l�i�����j�ł����Ă��A
        // �@@�@@�@@�@@�@@�i�����O�̎w�l�j == �i������̎w�l�j�̏ꍇ�́Afalse��ԋp����B
        if ((l_orderUnitRow.getConfirmedQuantityIsNull() == false)
        && (l_dblLimitPrice != 0))
        {
            if (l_dblLimitPrice == l_orderUnitRow.getLimitPrice())
            {
                return false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        // �R�|�R�j�@@��L�ȊO�̏ꍇ�́Atrue��ԋp����B  
        return true;
    }
    
    /**
     * �iisChange���ϐ��ʓ���j�B<BR>
     * <BR>
     * ���ϐ��ʓ���ɒ����������������`�F�b�N����B<BR>
     * ����ɒ���������ꍇ��true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �P�j�@@�i�ԍϒ����A�������n�����j�ȊO�̏ꍇ��false��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����O�̕ԍώw����I�u�W�F�N�g���擾����B<BR>
     * �@@�����̒����P��.getContractsToClose( )�Ŏ擾�B<BR>
     * <BR>
     * �R�j�@@�l�̔�r<BR>
     * �@@�����̒����㌈�ώw��G���g���̗v�f����LOOP���A�l�̔�r���s���B<BR>
     * �@@�i�����O�̕ԍώw����.�ԍϒ������� != �����̒����㌈�ώw��G���g��.�����㊔���j�̏ꍇ�A<BR>
     * �@@true��ԋp����B<BR>
     * <BR>
     * �S�j�@@false��ԋp����B<BR>
     * @@param l_orderUnit�i�����P�ʁj<BR>
     * �@@�@@�@@�����������i�������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_modifiedSettleContractEntries�i�����㌈�ώw��G���g���j<BR>
     * �@@�@@�@@�����㌈�ώw��G���g���̔z��B<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     */
    protected boolean isChangeEachQuantityOfCloseContract(
        EqTypeOrderUnit l_orderUnit,
        EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "isChangeEachQuantityOfCloseContract(EqTypeOrderUnit, EqTypeSettleContractOrderEntry[])";
        log.entering(STR_METHOD_NAME);

        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        if (!OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg) &&
            !OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
        {
            EqTypeContractSettleOrderUnit l_closingOrderUnit =
                (EqTypeContractSettleOrderUnit)l_orderUnit;
            l_closingContractSpecs = l_closingOrderUnit.getContractsToClose();
        }
        else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            EqTypeContractSwapOrderUnit l_swapOrderUnit =
                (EqTypeContractSwapOrderUnit)l_orderUnit;
            l_closingContractSpecs = l_swapOrderUnit.getContractsToClose();
        }
        if (l_closingContractSpecs.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00659,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        for (int i = 0; i < l_closingContractSpecs.length; i++)
        {
            if (l_closingContractSpecs[i].getQuantity() !=
                l_modifiedSettleContractEntries[i].getQuantity())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * �ivalidate���s�w��K���j�B<BR>
     * <BR>
     * ���s�w��K���`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ȉ��̂�����̏����ɂ����v���Ȃ��ꍇ�́A����������return����B<BR>
     * <BR>
     * �@@�@@�@@������is���s��true<BR>
     * �@@�@@�@@�����̎��s�������s�o���������s<BR>
     * <BR>
     * �@@�@@�@@��L�̂����ꂩ�̏����ɍ��v����ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@���s�w��K���`�F�b�N<BR>
     * �@@�@@�@@�������.is�������s�w��K��(������is������)���R�[������B<BR>
     * �@@�@@�@@�߂�l��true�̏ꍇ�́A�u���������E���s�w��K�����v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01355<BR>
     * <BR>
     * �R�j�@@MM�������s�w��`�F�b�N<BR>
     * �@@�@@�@@�s��(*).getMarketCode( )�ɂ��s��R�[�h���擾����B<BR>
     * �@@�@@�@@�擾�����s��R�[�h���hJASDAQ�h�A���@@�������.�X�����J�敪���h�}�[�P�b�g���C�N�����h<BR>
     * �@@�@@�@@�̏ꍇ�́A�uMM�����͐��s�w��s�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01352<BR>
     * <BR>
     * �@@�@@�@@(*)�s��I�u�W�F�N�g�́A�������.getMarket()�ɂ��擾����B
     * @@param l_tradedProduct (�������)<BR>
     * �@@�@@�@@��������I�u�W�F�N�g�B
     * @@param isMarketOrder (is���s)<BR>
     * �@@�@@�@@���s�����̏ꍇtrue�A�ȊOfalse�B
     * @@param isSellOrder (is������)<BR>
     * �@@�@@�@@���蒍���^���������̃t���O�B<BR>
     * �@@�@@�@@���蒍���̏ꍇtrue�A���������̏ꍇfalse���w�肷��B<BR>
     * @@param l_executionCondition (���s����)<BR>
     * �@@�@@�@@���s�����B<BR>
     * @@throws WEB3BaseException
     */
    public void validateMarketOrderDesignateCtrl(
        WEB3EquityTradedProduct l_tradedProduct,
        boolean isMarketOrder,
        boolean isSellOrder,
        EqTypeExecutionConditionType l_executionCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMarketOrderDesignateCtrl(EqTypeOrderUnit, boolean, boolean, EqTypeExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        if ((isMarketOrder == true) ||
            (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondition)))
        {
            ;
        }
        else
        {
            //�P�j�@@�i���s���� �܂��� �s�o���������s�����j�ȊO�̏ꍇ�́A����������return����B
            return;
        }

        // �Q�j�@@���s�w��K���`�F�b�N
        if (l_tradedProduct.isSpotMarketOrderDesignateCtrl(isSellOrder))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01355,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �R�j�@@MM�������s�w��`�F�b�N
        if (WEB3MarketCodeDef.JASDAQ.equals(l_tradedProduct.getMarketCode()))
        {
            // �擾�����s��R�[�h���hJASDAQ�h�A���@@�������.�X�����J�敪���h�}�[�P�b�g���C�N�����h
            // �̏ꍇ�́A�uMM�����͐��s�w��s�v�̗�O��throw����B
            EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            if (WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01352,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�C���T�C�_�[�j�B<BR>
     * <BR>
     * �C���T�C�_�[�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@������.get������(�ڋq, ��������)�ɂ��A�����҃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get������( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�ڋq�F�@@�����̕⏕����.getMainAccount( )<BR>
     * �@@�@@�@@���������F�@@�����̊�������<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"������~"�̏ꍇ�́A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01356<BR>
     * <BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�A<BR>
     * �@@�@@�@@�܂��͎擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"������~"�̏ꍇ�́A<BR>
     * �@@�@@�@@���̂܂�return����B
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕����
     * @@param l_eqtypeProduct (��������)<BR>
     * �@@�@@�@@���������I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateInsider(
        SubAccount l_subAccount,
        EqTypeProduct l_eqtypeProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInsider(SubAccount, EqTypeProduct)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@������.get������(�ڋq, ��������)�ɂ��A�����҃I�u�W�F�N�g���擾����B
        WEB3GentradeInsider l_insider = null;
        try
        {
            l_insider =
                WEB3GentradeInsider.getInsider(
                    new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject()),
                    l_eqtypeProduct);
        }
        catch (WEB3BaseException l_exp)
        {
            // �Y���f�[�^�����̏ꍇ�́A���̂܂܃��^�[������B
            return;
        }

        // �Q�j�@@�擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"������~"�̏ꍇ�́A
        // �@@�@@�@@��O��throw����B
        InsiderRow l_insiderRow = (InsiderRow)l_insider.getDataSourceObject();
        if (WEB3InsiderRegistDivDef.ORDER_STOP.equals(l_insiderRow.getRegistDiv()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01356,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate�ڋq�����ʎ����~�j�B<BR>
     * <BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�����̕⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�ڋq.is�����~����( )���R�[������B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��is�����~����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̖���ID<BR>
     * �@@�@@�@@������ʁF�@@�����̒������<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�߂�l == true�̏ꍇ�́u�ڋq�͎w������̊Y�������~���v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01357
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕����
     * @@param l_lngProductId (����ID)<BR>
     * �@@�@@�@@����ID
     * @@param l_orderType (�������)<BR>
     * �@@�@@�@@�������
     * @@throws WEB3BaseException
     */
    public void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountProductOrderStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̕⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_account =
            new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject());

        // �Q�j�@@�ڋq.is�����~����( )���R�[������B
        //�@@�@@�@@�߂�l == true�̏ꍇ�́u�ڋq�͎w������̊Y�������~���v�̗�O��throw����B
        if (l_account.isTradeStopProduct(l_lngProductId, l_orderType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01357,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�F[" + l_account.getAccountId()
                + "] ����ID�F[" + l_lngProductId
                + "] ������ʁF[" + l_orderType + "]");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate��������j�B<BR>
     * <BR>
     * ��������̃`�F�b�N���s���B<BR>
     * �ivalidateTradedProduct(Product, Market)�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̏����ɂĎ�������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���`�F�b�N<BR>
     * �@@�@@�@@�������.���敪���h����h�̏ꍇ�A<BR>
     * �@@�@@�@@�u���Y�����͔���v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01307<BR>
     * <BR>
     * �R�j�@@�擾������������I�u�W�F�N�g��ԋp����B
     * @@param l_eqTypeProduct (��������)<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param l_market (�s��)<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        EqTypeProduct l_eqTypeProduct,
        Market l_market)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedProduct(EqTypeProduct , Market)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�X�[�p�[�N���X�̏����ɂĎ�������I�u�W�F�N�g���擾����B
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)super.validateTradedProduct(
                    l_eqTypeProduct,
                    l_market);
        }
        catch (OrderValidationException l_ove)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ove.getValidationResult().getProcessingResult().getErrorInfo().getErrorMessage()
                + "����ID�F[" + l_eqTypeProduct.getProductId() + "]" + " �s��ID�F[" + l_market.getMarketId() + "]");
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            if ( l_bre.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005) )
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_bre.getErrorMessage(),
                    l_bre
                );
            }
            else
            {
                throw l_bre;
            }
        }

        //  �Q�j�@@���`�F�b�N
        // �@@�@@�@@�������.���敪���h����h�̏ꍇ�A�u���Y�����͔���v�̗�O���X���[����B
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.UNLISTING.equals(l_tradedProductRow.getListType()))
        {
            log.debug("�������i����ID�F[" + l_tradedProductRow.getProductId() + "]�j�́A����ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01307,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�F[" + l_tradedProductRow.getProductId() + "]");
        }

        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }

    /**
     * �ivalidate����O������t�\�j�B<BR>
     * <BR>
     * �w�肳�ꂽ����O���������������\���`�F�b�N����B<BR>
     * �@@�|�w�蕪������t�\���ԑт̏ꍇ�́A�擾��������O���������I�u�W�F�N�g��Ԃ��B<BR>
     * �@@�|�w�蕪������t�\���ԑтłȂ��ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �P�j�@@�Y�����闧��O���������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@���g���v���_�N�g�}�l�[�W��.get�����\����O��������( )�R�[���ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�����\����O��������( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̕⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�����R�[�h�F�@@�����̖����R�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@��get�����\����O��������( )�����O��throw���ꂽ�ꍇ�́A<BR>
     * �@@�@@�@@����O�����̂܂�raise����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * �Q�j�@@�߂�l��null�̏ꍇ�́A��t�\�Ɣ��肵�߂�l�̗���O���������I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�߂�l��null�̏ꍇ�́A<BR>
     * �@@�@@�@@�u�w�蕪���͎�t�\���ԊO�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01369
     * @@param l_lngProductId (����ID)<BR>
     * �@@�@@�@@����ID�B
     * @@param l_lngMarketId (�s��ID)<BR>
     * �@@�@@�@@�s��ID�B
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����B
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams validateOffFloorOrderPossible(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOffFloorOrderPossible(long, long, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3EquityProduct l_product = null;
        Market l_market = null;
        try
        {
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        OffFloorOrderProductParams l_params = l_productManager.getCanOrderOffFloorOrderProduct(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_product.getProductCode(),
            l_market.getMarketCode());
        if (l_params == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01369,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * �ivalidate����O�������������j�B<BR>
     * <BR>
     * �w�肳�ꂽ����O���������ɑ΂��A���ɒ������o�^�ς��ǂ������`�F�b�N����B<BR>
     * �@@�|�����o�^�ς̏ꍇ�́A��O��throw����B<BR>
     * <BR>
     * �P�j�@@�Y�����闧��O�����̒����P�ʃf�[�^���擾����B<BR>
     * �@@�@@�@@���g�����������}�l�[�W��.get����O���������P�ʈꗗ( )�R�[���ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get����O���������P�ʈꗗ( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�@@�@@����ID�F�@@�����̖���ID<BR>
     * �@@�@@�@@�s��ID�F�@@�����̎s��ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�Y���f�[�^�Ȃ��̏ꍇ�́A�������o�^�Ɣ��肵return����B<BR>
     * �@@�@@�@@�Y���f�[�^�����݂���ꍇ�́A�u�w��̗���O�����ɑ΂��钍�����o�^�ρv�̗�O��<BR>
     * �@@�@@�@@throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01370
     * @@param l_lngProductId (����ID)<BR>
     * �@@�@@�@@����ID
     * @@param l_lngMarketId (�s��ID)<BR>
     * �@@�@@�@@�s��ID
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕����
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public void validateOffFloorDuplicateOrder(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOffFloorDuplicateOrder(long, long, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        List l_lisOrderUnits = l_orderManager.getOffFloorOrderUnits(
            l_subAccount,
            l_lngProductId,
            l_lngMarketId);
        if (l_lisOrderUnits != null && l_lisOrderUnits.isEmpty() == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01370,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �ivalidate���ϑ��������j<BR>
     * <BR>
     * ���ϒ������̑��������̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���������`�F�b�N�̑ΏۂƂȂ錚���I�u�W�F�N�g��S�Ď擾����B <BR>
     * �@@�@@�@@�i�����̋Ɩ��L�[�̂����A�����A�����A���P�����O���Č�������j <BR>
     * <BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get�����ꗗ(�⏕����, �����^�C�v, ��������������,  <BR>
     * �@@�@@�@@���������f�[�^�R���e�i)�R�[���ɂ��擾����B <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@��get�����ꗗ( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̕⏕���� <BR>
     * �@@�@@�@@�����^�C�v�F�@@ProductTypeEnum."����" <BR>
     * �@@�@@�@@��������������F�@@�s��ID�A���敪�A����ID�A�ŋ敪�A�ٍϋ敪�A�ٍϊ����l ���w�肷�� <BR>
     * �@@�@@�@@���������f�[�^�R���e�i�F�@@�����̎������.�s��ID�A�����̌��敪�A�����̎������.����ID�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����̐ŋ敪�A�����ٍ̕ϋ敪�A�����ٍ̕ϊ����l �̒l���w�肷�� <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�j�@@���������`�F�b�N�Ώۂ̌����ɑ΂���A���������ʂ̍��v�l���Z�o����B <BR>
     * <BR>
     * �@@�@@�@@�P�j�Ŏ擾���������I�u�W�F�N�g�̐���Loop�F <BR>
     * �@@�@@�@@�@@�@@����.getLockedQuantity( )���R�[�����A�擾���ʂ̍��v�����߂�B <BR>
     * <BR>
     * �R�j�@@���ω\�����������Z�o����B <BR>
     * <BR>
     * �@@�@@�@@���ω\�������� <BR>
     * �@@�@@�@@�@@�@@�� 1�j�Ŏ擾�����S�Ă̌���.������ �̍��v�l �| �Q�j�Ŏ擾�������������ʂ̍��v�l <BR>
     * �@@�@@�@@�@@�@@�@@�@@�{ �������̕��̒���������(*1) <BR>
     * <BR>
     * �@@�@@�@@(*1)�������̕��̒��������� <BR>
     * �@@�@@�@@�@@�@@�����̒����P��ID == 0�i���V�K�����j�̏ꍇ�́A0���w��B <BR>
     * �@@�@@�@@�@@�@@�����̒����P��ID != 0�i�������j�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�g�����������}�l�[�W��.get����萔��(�����̒����P��ID�ɊY�����钍���P��)�� <BR>
     * �@@�@@�@@�@@�@@�߂�l���w��B <BR>
     * �@@�@@�@@�@@�@@�������̏ꍇ�A�����Ώے����ŉ������Ă��镪�̐��ʂ����ω\���������Ɋ܂߂�B <BR>
     * <BR>
     * �S�j�@@�`�F�b�N�Ώۂ̊������Z�o����B<BR>
     * <BR>
     * �@@�@@�@@�`�F�b�N�Ώۂ̊����F<BR>
     * �@@�@@�@@�@@�@@�����̒����P��ID == 0�i���V�K�����j�̏ꍇ�́A�����̊����B<BR>
     * �@@�@@�@@�@@�@@�����̒����P��ID != 0�i�������j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�i�����̊��� �| �����̒����P��ID�ɊY�����钍���P��.��萔�ʁj�B<BR>
     * <BR>
     * �T�j�@@���������`�F�b�N���s���B <BR>
     * <BR>
     * �@@�@@�@@�i�`�F�b�N�Ώۂ̊��� �� ���ω\���������j �̏ꍇ�A <BR>
     * �@@�@@�@@�u���������`�F�b�N�G���[�v�̗�O��throw����B <BR>
     * <BR>
     * �U�j�@@return����B <BR>
     * <BR>
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j�B<BR>
     * @@param l_lngOrderUnitId �i�����P��ID�j<BR>
     * �@@�@@�@@�����P��ID�B <BR>
     * <BR>
     * �@@�@@�@@�V�K�����̏ꍇ��0���Z�b�g�B<BR>
     * @@param l_equityTradedProduct �i��������j<BR>
     * �@@�@@�@@��������I�u�W�F�N�g�B<BR>
     * @@param l_taxTypeEnum �i�ŋ敪�j<BR>
     * �@@�@@�@@�ŋ敪�B�ixTrade��TaxTypeEnum�ɂĒ�`�j<BR>
     * @@param l_strRepaymentType �i�ٍϋ敪�j<BR>
     * �@@�@@�@@�ٍϋ敪�B <BR>
     * �@@�@@�@@0�FDEFAULT(�w��Ȃ�) <BR>
     * �@@�@@�@@1�F���x�M�p <BR>
     * �@@�@@�@@2�F��ʐM�p<BR>
     * @@param l_dblRepaymentNum �i�ٍϊ����l�j<BR>
     * �@@�@@�@@�ٍϊ����l�B <BR>
     * @@param l_dblQuantity �i�����j<BR>
     * �@@�@@�@@���͊����B <BR>
     * @@param l_contractType �i���敪�j<BR>
     * �@@�@@�@@���敪�B <BR>
     * @@throws WEB3BaseException<BR>
     */
     public void validateSettleContractTotalQuantity(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        WEB3EquityTradedProduct l_equityTradedProduct,
        TaxTypeEnum l_taxTypeEnum,
        String l_strRepaymentType,
        double l_dblRepaymentNum,
        double l_dblQuantity,
        ContractTypeEnum l_contractType) throws WEB3BaseException
     {
        final String STR_METHOD_NAME =
            "validateSettleContractTotalQuantity(WEB3GentradeSubAccount, long, WEB3EquityTradedProduct, TaxTypeEnum, String, double, double, ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�����I�u�W�F�N�g�̎擾
        // ��������������
        String l_strQueryString = "and market_id = ? " +
            "and contract_type = ? " +
            "and product_id = ? " +
            "and tax_type = ? " +
            "and repayment_type = ? " +
            "and repayment_num = ? ";
            
        // ���������f�[�^�R���e�i
        long l_lngMarketId = l_equityTradedProduct.getMarket().getMarketId();
        long l_lngProductId = l_equityTradedProduct.getProduct().getProductId();
        String[] l_strQueryDataContainer = {String.valueOf(l_lngMarketId),
            String.valueOf(l_contractType.intValue()),
            String.valueOf(l_lngProductId),
            String.valueOf(l_taxTypeEnum.intValue()),
            l_strRepaymentType,
            String.valueOf(l_dblRepaymentNum)};
        
        // �����|�W�V�����}�l�[�W���擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // get�����ꗗ()
        List l_lstContracts = l_positionManager.getContracts(l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strQueryString,
            l_strQueryDataContainer);
        
        if (l_lstContracts == null || l_lstContracts.size() == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY�����錚���f�[�^���擾�o���܂���ł����B��������:[" +
                l_strQueryString + "] ���������f�[�^�R���e�i:[" + l_strQueryDataContainer.toString() + "]");
        }
        
        // �Q�j���������ʂ̍��v�l���Z�o
        double l_dblTotalLockedQuantity = 0.0D;
        double l_dblTotalQuantity = 0.0D;
        int l_intCount = l_lstContracts.size();
        for (int i = 0; i < l_intCount; i++)
        {
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)l_lstContracts.get(i);
            WEB3EquityContract l_equityContract = new WEB3EquityContract(l_eqtypeContractRow);
            
            l_dblTotalLockedQuantity += l_equityContract.getLockedQuantity();
            l_dblTotalQuantity += l_equityContract.getQuantity();
        }
        
        // �������̕��̒��������ʁA�y�і�萔�ʂ��擾
        double l_dblLockedQuantityCurOrder = 0.0D;
        double l_dblExecutedQuantity = 0.0D;
        if (l_lngOrderUnitId != 0)
        {
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            // �����P��ID�ɊY�����钍���P�ʂ��擾
            EqTypeOrderUnit l_eqtypeOrderUnit = null;
            try
            {
                l_eqtypeOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ɊY�����钍���P�ʃf�[�^���擾�o���܂���ł����B�����P��ID:[" +
                    l_lngOrderUnitId + "]");
            }
            
            // get����萔��
            l_dblLockedQuantityCurOrder = l_orderManager.getUnExecutedQuantity(l_eqtypeOrderUnit);

            l_dblExecutedQuantity = l_eqtypeOrderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0.0D;
            }
        }
        
        // �R�j���ω\�����������Z�o
        double l_dblSettlePosTotalQuantity =
            l_dblTotalQuantity - l_dblTotalLockedQuantity + l_dblLockedQuantityCurOrder;
            
        // �S�j�`�F�b�N�Ώۂ̊������Z�o
        double l_dblQuantityForCheck = l_dblQuantity - l_dblExecutedQuantity;
        
        // �T�j���������`�F�b�N
        if (l_dblQuantityForCheck > l_dblSettlePosTotalQuantity)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01932,
                STR_METHOD_NAME,
                "�����������A���ω\���������𒴂��܂����B ��������:[" +
                l_dblQuantity + "] ���ω\��������:[" + l_dblSettlePosTotalQuantity + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
        return;
     }
     
    /**
     * (validate����������Rev���)<BR>
     * �������̒���Rev������𒴂��Ȃ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����̒����P�ʃI�u�W�F�N�g��clone�I�u�W�F�N�g�𐶐����A<BR>
     * �@@�@@�@@�����̒�����̒l�����ꂼ��Y�����鍀�ڂɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����㊔���F�@@�@@�@@�@@��������<BR>
     * �@@�@@�@@������w�l�F�@@�@@�@@�@@�w�l<BR>
     * �@@�@@�@@�����㎷�s�����F�@@���s����<BR>
     * �@@�@@�@@������l�i�����F�@@�l�i����<BR>
     * <BR>
     * �Q�j�@@���������T�[�r�X.get����������Rev()���R�[������B<BR>
     * �@@�@@�@@�����ɂ́A�P�j�ō쐬���������P�ʃI�u�W�F�N�g���Z�b�g����B<BR>
     * �@@�@@�@@����O�i����񐔃I�[�o�[���j��throw���ꂽ�ꍇ�́A<BR>
     * �@@�@@�@@�����̗�O�����̂܂�throw����B<BR>
     * @@param l_orderUnit - (�����O�����P��)<BR>
     * �����O�����P�ʃI�u�W�F�N�g
     * @@param l_dblQuantity - (������w�l)<BR>
     * ������w�l
     * @@param l_dblLimitPrice - (������w�l)<BR>
     * ������w�l
     * @@param l_executionConditionType - (�����㎷�s����)<BR>
     * �����㎷�s����
     * @@param l_strPriceConditionType - (������l�i����)<BR>
     * ������l�i����
     * @@throws WEB3BaseException
     */
    public void validateChangeOrderRevUpperLimit(
        EqTypeOrderUnit l_orderUnit,
        double l_dblQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_executionConditionType,
        String l_strPriceConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeOrderRevUpperLimit(EqTypeOrderUnit, double, double, EqTypeExecutionConditionType, String)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams =
            new EqtypeOrderUnitParams(l_orderUnitRow);
        l_orderUnitParams.setQuantity(l_dblQuantity);
        l_orderUnitParams.setLimitPrice(l_dblLimitPrice);
        l_orderUnitParams.setExecutionConditionType(l_executionConditionType);
        l_orderUnitParams.setPriceConditionType(l_strPriceConditionType);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_changeAfterOrderUnit =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
        
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        l_frontOrderService.getChangeOrderRev(l_changeAfterOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������i�M�p�j)<BR>
     * �戵�\�`�F�b�N�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A��������I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateTradedProductForMarginTrading( )�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * this.validate��������i�M�p�j(�����̕⏕����, �����̊�������, �����̎s��, �����̕��X,<BR>
     * �����ٍ̕ϋ敪, �����̒����J�e�S��, ������is����, true�i��������~�`�F�b�N������j)��<BR>
     * delegate����B<BR>
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�B
     * @@param l_product (��������)<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param l_market (�s��)<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@param l_branch (���X)<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�B
     * @@param l_strRepaymentType (�ٍϋ敪)<BR>
     * �@@�@@�@@�ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_orderCateg (�����J�e�S��)<BR>
     * �@@�@@�@@�����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isShort (is����)<BR>
     * �@@�@@�@@�����^�����̃t���O�B<BR>
     * �@@�@@�@@�����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B
     * @@return WEB3EquityTradedProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product,
        WEB3GentradeMarket l_market,
        WEB3GentradeBranch l_branch,
        String l_strRepaymentType,
        OrderCategEnum l_orderCateg,
        boolean l_isShort)
        throws WEB3BaseException
    {
        return this.validateTradedProductForMarginTrading(
            l_subAccount,
            l_product,
            l_market,
            l_branch,
            l_strRepaymentType,
            l_orderCateg,
            l_isShort,
            true);
    }
    
    /**
     * (validate���ꎷ�s�����戵��~)<BR>
     * ���ꎷ�s���������̎戵��~���ݒ肳��Ă��邩�ǂ�����<BR>
     * ���i�A�����A�s��P�ʂŃ`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���������w��Ȃ�<BR>
     * �@@�i�p�����[�^.�������� == "DEFAULT"�j�̏ꍇ�A<BR>
     * �@@�������I������B<BR>
     * <BR>
     * �Q�j�@@�����i�p�����[�^.�����P��ID != 0�j�̏ꍇ�A<BR>
     * �@@�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�����P��ID�ɊY�����钍���P�ʂ��擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����ς̋t�w�l�^�ؑ֍ς�W�w�l�����̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�i�g�����������}�l�[�W��.is�����������i�j == false�j<BR> 
     * �@@�@@�@@�@@�@@�������I������B <BR>
     * <BR>
     * �@@�@@�@@[is����������()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�擾���������P�� <BR>
     * <BR>
     * �R�j�@@�N���T�[�r�X�`�F�b�N���s���B <BR>
     * �@@�R�|�P�j�ȉ����擾����B <BR>
     * �@@�@@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * �@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP<BR> 
     * �@@�@@�@@�@@�@@�j <BR>
     * �@@�R�|�Q�j�R�|�P�Ŏ擾�����l != NULL �̏ꍇ�ȉ��̏������s���B <BR>
     * �@@�@@�@@�R�|�P�Ŏ擾�����l = BooleanEnum.TRUE �̏ꍇ�A�������I������B<BR> 
     * <BR>
     * �S�j�@@DB����<BR>
     * �@@�ȉ��̏����ɂāA���ꎷ�s�����戵��~�e�[�u������������B<BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@And (<BR>
     * �@@�@@�@@-- ���i�ʏ���<BR>
     * �@@�@@�@@(���X�R�[�h = �p�����[�^.�⏕����.get����X().���X�R�[�h<BR>
     * �@@�@@�@@�@@And �ݒ�Ώێ�� = "���i"<BR>
     * �@@�@@�@@�@@And �L�[��� = <BR>
     * �@@�@@�@@�@@�@@[�p�����[�^.�M�p����敪 == "DEFAULT"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@"��������"<BR>
     * �@@�@@�@@�@@�@@[��L�ȊO]<BR>
     * �@@�@@�@@�@@�@@�@@"�M�p���"<BR>
     * �@@�@@�@@Or<BR>
     * �@@�@@�@@-- �s��ʏ���<BR>
     * �@@�@@�@@(���X�R�[�h = "000"<BR>
     * �@@�@@�@@�@@And �ݒ�Ώێ�� = "�s��"<BR>
     * �@@�@@�@@�@@And �L�[��� = �p�����[�^.�s��R�[�h)<BR>
     * �@@�@@�@@Or<BR>
     * �@@�@@�@@-- �����ʏ���<BR>
     * �@@�@@�@@(���X�R�[�h = "000"<BR>
     * �@@�@@�@@�@@And �ݒ�Ώێ�� = "����"<BR>
     * �@@�@@�@@�@@And �L�[��� = �p�����[�^.�������.����ID�ɊY���������.�����R�[�h<BR>
     * �@@�@@�@@�@@And �L������From <= ������(*1)<BR>
     * �@@�@@�@@�@@And �L������To >= ������)<BR>
     * �@@�@@) And �폜�t���O = "DEFAULT"<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�ݒ�Ώێ�� ����<BR>
     * <BR>
     * �T�j�@@�S�j�̌������ʂ̓��A�p�����[�^.����������<BR>
     * �@@�Ή�����e�[�u�����ڂ̂����ꂩ��"��~��"�ƂȂ��Ă����ꍇ�A<BR>
     * �@@�戵��~���ݒ肳��Ă��郌�R�[�h.�ݒ�Ώێ�ʂ�<BR>
     * �@@�Ή������O���X���[����B<BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�A�������I������B<BR>
     * <BR>
     * �@@[�Ή����鍀��]<BR>
     * �@@�@@�p�����[�^.�������� == "�t�w�l" �� �t�w�l������~�t���O<BR>
     * �@@�@@�p�����[�^.�������� == "W�w�l" �� W�w�l������~�t���O<BR>
     * <BR>
     * �@@�@@���������ʂ̗v�f.�ݒ�Ώێ�� == "�s��"�@@���@@�L�[��� == "JASDAQ"�@@����<BR>
     * �@@�@@�@@�ǉ���� != null�̏ꍇ�A<BR>
     * �@@�@@�@@�ǉ���� == �p�����[�^.�������.�X�����J�敪�ƂȂ�<BR>
     * �@@�@@�@@�v�f�̒�����~�t���O�݂̂��擾����B<BR>
     * �@@�@@�@@�iJASDAQ�̎戵��~�́A�u�I�[�N�V���������̂݁v or �u�}�[�P�b�g���C�N�����̂݁v or<BR>
     * �@@�@@�@@�@@�u�����v�̂����ꂩ�œo�^���邱�Ƃ��ł���ׁB�@@���ǉ���� == null�̏ꍇ�́u�����v�j<BR>
     * �@@�@@�@@<BR>
     * �@@[�ݒ�Ώێ�ʂɑΉ������O]<BR>
     * �@@�@@�ݒ�Ώێ�� == "���i"�̏ꍇ<BR>
     * �@@�@@�@@�ˁ@@�u�w�肳�ꂽ�����t�����ł̏��i�͎戵��~���v<BR>
     * <BR>
     * �@@�@@�ݒ�Ώێ�� == "�s��"�̏ꍇ<BR>
     * �@@�@@�@@�ˁ@@�u�w�肳�ꂽ�����t�����ł̎s��͎戵��~���v<BR>
     * <BR>
     * �@@�@@�ݒ�Ώێ�� == "����"�̏ꍇ<BR>
     * �@@�@@�@@�ˁ@@�u�w�肳�ꂽ�����t�����ł̖����͎戵��~���v<BR>
     * <BR>
     * (*1)������<BR>
     * �@@������ԊǗ�.get������()�ɂĎ擾����B<BR>
     * @@param l_subAccount - (�⏕����)
     * �⏕�����I�u�W�F�N�g
     * @@param l_lngOrderUnitId - (�����P��ID)
     * �����P��ID
     * 
     * �V�K�����̏ꍇ��0���Z�b�g�B
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * 0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l<BR>
     * @@param l_strMarginTradedType - (�M�p����敪)<BR>
     * 0�FDAFAULT�i�M�p����ȊO�j<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * 0�F  DEFAULT<BR>
     * 1�F  ����<BR>
     * 2�F  ���<BR>
     * 3�F  ���É�<BR>
     * 4�F  ���s<BR>
     * 5�F  �L��<BR>
     * 6�F  ����<BR>
     * 8�F  �D�y<BR>
     * 9�F  NNM<BR>
     * 10�F JASDAQ<BR>
     * @@throws WEB3BaseException
     */
    public void validateTriggerOrderStop(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        WEB3EquityTradedProduct l_tradedProduct,
        String l_strOrderConditionType,
        String l_strMarginTradedType,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTriggerOrderStop(WEB3GentradeSubAccount, " +
            "long, WEB3EquityTradedProduct, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // ���������w��Ȃ��̏ꍇ�A�������I������B
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("���������w��Ȃ��Ȃ̂ŁA�����I��");
	        log.exiting(STR_METHOD_NAME);
	        return;
        }
        
        // ���������̏ꍇ
        if (l_lngOrderUnitId != 0)
        {
	        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
	        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit = null;
            try
            {
	            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����P��ID:[" + l_lngOrderUnitId + "]�ɊY�����郌�R�[�h������܂���");
            }

            // �����ς̋t�w�l�^�ؑ֍ς�W�w�l�����̏ꍇ
            //�i�g�����������}�l�[�W��.is�����������i�j == false�j
	        if (!l_orderManager.isNotOrderedOrder(l_orderUnit))
	        {
	            log.debug("�����ς̋t�w�l�^�ؑ֍ς�W�w�l�����̏ꍇ�A�����I��");
	            log.exiting(STR_METHOD_NAME);
	            return;
	        }
	    }

        // �N���T�[�r�X�`�F�b�N���s���B
        Object l_objSkipTriggerOrderStop = 
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP);
        
        if(l_objSkipTriggerOrderStop != null) 
        {
            if(l_objSkipTriggerOrderStop.equals(BooleanEnum.TRUE)){
                log.debug("�X�L�b�v�ΏۃT�[�r�X�Ȃ̂ŁA�����I��");
                log.exiting(STR_METHOD_NAME);
                return;
            }

        }

        // DB����
        // �،���ЃR�[�h
        String l_strWhere = "institution_code = ? and (";
        ArrayList l_lisBindVal = new ArrayList();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        l_lisBindVal.add(l_branch.getInstitution().getInstitutionCode());
        
        // ���i�ʏ���
        l_strWhere += "(branch_code = ? "
            + "and target_type = ? "
            + "and key = ?) ";
            
        l_lisBindVal.add(l_branch.getBranchCode());
        l_lisBindVal.add(WEB3TargetTypeDef.COMMODITY);
        if (WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginTradedType))
        {
            l_lisBindVal.add(WEB3CommodityDivDef.EQUITY);
        }
        else
        {
            l_lisBindVal.add(WEB3CommodityDivDef.MARGIN);
        }
        
        // �s��ʏ���
        l_strWhere += "or (branch_code = ? "
            + "and target_type = ? "
            + "and key = ?) ";
            
        l_lisBindVal.add(WEB3BranchCodeDef.DEFAULT);
        l_lisBindVal.add(WEB3TargetTypeDef.MARKET);
        l_lisBindVal.add(l_strMarketCode);
        
        // �����ʏ���
        l_strWhere += "or (branch_code = ? "
            + "and target_type = ? "
            + "and key = ? "
            + "and valid_term_from <= ? "
            + "and valid_term_to >= ?) ";
            
        l_lisBindVal.add(WEB3BranchCodeDef.DEFAULT);
        l_lisBindVal.add(WEB3TargetTypeDef.PRODUCT);
        EqTypeProduct l_product = (EqTypeProduct)l_tradedProduct.getProduct();
        l_lisBindVal.add(l_product.getProductCode());
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_lisBindVal.add(l_datBizDate);
        l_lisBindVal.add(l_datBizDate);
        
        l_strWhere += ") and delete_flag = ? ";
        l_lisBindVal.add(BooleanEnum.FALSE);
        
        QueryProcessor l_queryProcessor = null;
        List l_lisTriggerOrderStop = null;
        try
        {
	        l_queryProcessor = Processors.getDefaultProcessor();
	        l_lisTriggerOrderStop = l_queryProcessor.doFindAllQuery(
                TriggerOrderStopRow.TYPE,
                l_strWhere,
                "target_type",
                null,
                l_lisBindVal.toArray());
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�A�N�Z�X�Ɏ��s���܂���");
        }
        
        // �������ʂ��擾�ł��Ȃ������ꍇ�A�����I��
        if (l_lisTriggerOrderStop == null ||
            l_lisTriggerOrderStop.size() == 0)
        {
            log.debug("�Y���f�[�^�Ȃ��Ȃ̂ŁA�����I��");
	        log.exiting(STR_METHOD_NAME);
            return;
        }
        
        for (int i = 0; i < l_lisTriggerOrderStop.size(); i++)
        {
            TriggerOrderStopRow l_triggerOrderStopRow =
                (TriggerOrderStopRow)l_lisTriggerOrderStop.get(i);
            int l_intStopFlag = 0;
            // ���������ɑΉ�����戵��~�t���O���擾
            // �ݒ�Ώێ�� == "�s��" ���� �L�[��� == "JASDAQ" ���� �ǉ���� != null�̏ꍇ
            if (WEB3TargetTypeDef.MARKET.equals(l_triggerOrderStopRow.getTargetType()) &&
                WEB3MarketCodeDef.JASDAQ.equals(l_triggerOrderStopRow.getKey()) &&
                l_triggerOrderStopRow.getAddition() != null)
            {
                EqtypeTradedProductRow l_tradedProductRow =
                    (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                if (!l_triggerOrderStopRow.getAddition().equals(l_tradedProductRow.getOpenOtcDiv()))
                {
                    continue;
                }
            }
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_intStopFlag = l_triggerOrderStopRow.getStopOrderStopFlag();
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_intStopFlag = l_triggerOrderStopRow.getWlimitOrderStopFlag();
            }
            
            if (BooleanEnum.TRUE.intValue() == l_intStopFlag)
            {
                // �ݒ�Ώێ�ʂɑΉ������O���X���[
                String l_strTragetType = l_triggerOrderStopRow.getTargetType();
                if (WEB3TargetTypeDef.COMMODITY.equals(l_strTragetType))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02433,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (WEB3TargetTypeDef.MARKET.equals(l_strTragetType))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02434,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if (WEB3TargetTypeDef.PRODUCT.equals(l_strTragetType))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02435,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j)<BR>
     * 
     * ���x�ݒ��Ɏs�ꑗ�M�ϒ����̕�����������\���ǂ������肷��B<BR>
     * <BR>
     * �@@�P�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@���XID = �p�����[�^.���XID And<BR>
     * �@@�@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j And<BR>
     * �@@�@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�Q�j�Y�����R�[�h�����݂���ꍇ�݈̂ȉ����s���B<BR>
     * �@@�@@�擾���R�[�h�D�v���t�@@�����X�̒l�������s�̏ꍇ�A<BR>
     * �@@�@@��O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01352<BR>
     * <BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID
     * @@throws OrderValidationException
     */
    public void validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(
        long l_lngBranchId)
        throws WEB3BaseException, OrderValidationException
    {
        final String STR_METHOD_NAME =
            "validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            BranchPreferencesRow l_row = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId, 
                    WEB3BranchPreferencesNameDef.
                        MULTI_CHANGEABILITY_OF_MARKET_NOTIFIED_ORDER_IN_BREAK_TIME,
                    1);                 
            
            if (l_row != null)
            {
                if (WEB3MultiChangeabilityDef.NOT_CHANGEABLE.equals(l_row.getValue()))
                {
                    throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                }
            }
            
        }
        catch (DataNetworkException l_dne)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch (DataFindException l_dfe)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80004);
        }
        catch (DataQueryException l_dqe)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * (validate���ό����G���g���������P�� )<BR>
     * 
     * validate���ό����G���g���������P��
     * 
     * ���ό����G���g���ꗗ�̊����������P�ʂ̐����{�ł��邱�Ƃ��`�F�b�N���� <BR>
     *  <BR>
     * �@@�P�j����������甄���P�ʂ��擾���� <BR>
     *  <BR>
     * �@@�Q�j���ό����G���g���ꗗ�̑S�v�f�ɑ΂��āA�����P�ʂ̃`�F�b�N���s�� <BR>
     * �@@�@@�@@�X�[�p�[�N���X�̏����icheckLotSize(�����P��, ���ό����G���g���DgetQuantity�i�j�j�ɂāA <BR>
     * �@@�@@�@@�����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B <BR>
     * �@@�@@�@@�`�F�b�NNG�̏ꍇ�́A�u�����������P�ʂ̐����{�łȂ��v�̗�O���X���[����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00708<BR>
     * <BR>
     * @@param l_equityTradedProduct - (�������)<BR>
     * @@param l_eqTypeSettleContractOrderEntrys - (���ό����G���g���ꗗ)<BR>
     * 
     * @@throws WEB3BaseException
     */
    public void validateEverySettleContractOrderEntryLotSize(
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEverySettleContractOrderEntryLotSize(WEB3EquityTradedProduct,EqTypeSettleContractOrderEntry)";
        log.entering(STR_METHOD_NAME);
        
        double l_lotSize = l_equityTradedProduct.getLotSize();
        
        try
        {
            for(int i = 0; i < l_eqTypeSettleContractOrderEntrys.length; i++)
            {
                super.checkLotSize(
                    l_lotSize,
                    l_eqTypeSettleContractOrderEntrys[i].getQuantity());
            }
        }
        catch (OrderValidationException ove)
        {
            throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00708,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateW�w�l����)<BR>
     * ���������Ƃ���W�w�l���w�肳�ꂽ�����ɂ��āA<BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �|�iW�w�l�j�L����ԃ`�F�b�N�@@���������̂� <BR>
     * �|�iW�w�l�j�����w�l���}�C�i�X�l�łȂ����� �B<BR>
     * �|�iW�w�l�j�����w�l�̌Ēl�`�F�b�N  <BR>
     * �|�iW�w�l�j�����w�l�̒l���`�F�b�N <BR>
     * �|�iW�w�l�j���s�����戵�\�`�F�b�N  <BR>
     * �|�iW�w�l�j�����w�l�����P���敪�`�F�b�N <BR>
     * �|�iW�w�l�j���s�����\�`�F�b�N <BR>
     * �|���������P�������`�F�b�N  <BR>
     * �|���������P�����̓`�F�b�N<BR>
     * �|�󔄂�K���`�F�b�N <BR>
     * <BR>
     * �V�[�P���X�}  <BR>
     * �u�i�����jvalidateW�w�l�����v�Q�ƁB <BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.2.5�iW�w�l�j�L����ԃ`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*)���͉�ʕ\�����ƌ��݂̒����P�ʂƂ�W�w�l�����L����Ԃɑ��Ⴊ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�iget�v�w�l�p�L����ԋ敪()�̖߂�l != �p�����[�^.�iW�w�l�j�L����ԋ敪�ł���j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u��������W�w�l�����L����Ԃ��ύX�ƂȂ����ׁA�����s�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����́ˊm�F�ˊ����̊ԂŃX�g�b�v�������L���܂��́A�����ƂȂ��Ă��܂���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�iW�w�l�ؑ֏������{�ς܂��̓g���K�[�����Ǘ��Ҏ蓮�����ς́j�ꍇ���l���B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02494<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.3 (*)���~�b�g�����E�����P���敪�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���~�b�g�����̒����P�������s�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�i�p�����[�^.�w�l == 0�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�v�w�l�����̃��~�b�g�����͐��s�w��s�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02496<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.6 is�戵�\���s����()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@(*)�iW�w�l�j���s�����戵�\�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@false���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u���͂��ꂽ�iW�w�l�j���s�����͎戵�s�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02495<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.9.1 (*)�w�l�ƁiW�w�l�j�����w�l�̃`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*)�p�����[�^.�w�l == �p�����[�^.�iW�w�l�j�����w�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�����P���ƁiW�w�l�j�����w�l�����l�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02498<BR>
     * =====================================================<BR>
     * <BR>
     * �������́uis�������v�́A"���敪"�ł͂Ȃ��A"���"�ɂ��Z�b�g�����B<BR>
     * �@@�������t�A�M�p�V�K�����A�M�p���ԍρi�����j�̏ꍇ�Atrue <BR>
     * �@@�������t�A�M�p�V�K����/�M�p���ԍρi�����j�̏ꍇ�Afalse <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)<BR>
     * �����P�ʂh�c<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_strOrderCondition - (��������)<BR>
     * ��������<BR>
     * @@param l_dblOrderCondPrice - (���������P��)<BR>
     * ��������<BR>
     * @@param l_strWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     * @@param l_wLimitExecCondType - (�iW�w�l�j���s����)<BR>
     * �iW�w�l�j���s����<BR>
     * @@param l_strWlimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     * ���V�K�����o�^����null�B<BR>
     * @@param l_equityTradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g
     * @@param l_blnIsBuyToOpenOrder - (is������)<BR>
     * �iisBuyToOpenOrder�j<BR>
     * ������������ǂ����̔���B<BR>
     * �������̏ꍇtrue�A�������̏ꍇfalse�B<BR>
     * @@param l_strRepaymentType - (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * @@param l_orderCateg - (�����J�e�S��)<BR>
     * �����J�e�S��<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i����<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@throws WEB3BaseException
     */
    public void validateWLimitPriceOrder(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        double l_dblLimitPrice,
        String l_strOrderCondition,
        double l_dblOrderCondPrice,
        String l_strWLimitPrice,
        EqTypeExecutionConditionType l_wLimitExecCondType,
        String l_strWlimitEnableStatusDiv,
        WEB3EquityTradedProduct l_equityTradedProduct,
        boolean l_blnIsBuyToOpenOrder,
        String l_strRepaymentType,
        OrderCategEnum l_orderCateg,
        double l_dblQuantity,
        String l_strPriceConditionType,
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateWLimitPriceOrder(WEB3GentradeSubAccount, long, double, String, "
            + "double, String, EqTypeExecutionConditionType, String, "
            + "WEB3EquityTradedProduct, boolean, String, OrderCategEnum, double, String, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_equityTradedProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        // (*)�p�����[�^.�������� != "W�w�l"�̏ꍇ
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit = null;

        //(*)�����i�p�����[�^.�����P��ID != 0�j�̏ꍇ
        if (l_lngOrderUnitId != 0)
        {

            //(*)�X�g�b�v�����֐ؑ֍ς�W�w�l�����i�p�����[�^.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v�����L��"�j�̏ꍇ
            if (WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //(*)�X�g�b�v���������ς�W�w�l�����i�p�����[�^.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v����������"�j�̏ꍇ
            if (WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //getOrderUnit(arg0 : long)
            try
            {
                l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����P��ID:[" + l_lngOrderUnitId + "]�ɊY�����郌�R�[�h������܂���");
            }

            //get�v�w�l�p�L����ԋ敪(�����P�� : EqTypeOrderUnit)
            String l_strWlimitEnableStatusDiv2 =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_eqTypeOrderUnit);

            //�iW�w�l�j�L����ԃ`�F�b�N
            if (!WEB3Toolkit.isEquals(l_strWlimitEnableStatusDiv, l_strWlimitEnableStatusDiv2))
            {
                log.debug("��������W�w�l�����L����Ԃ��ύX�ƂȂ����ׁA�����s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02494,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "��������W�w�l�����L����Ԃ��ύX�ƂȂ����ׁA�����s�B");
            }
        }

        // (*)���~�b�g�����E�����P���敪�`�F�b�N
        if (l_dblLimitPrice == 0)
        {
            log.debug("�v�w�l�����̃��~�b�g�����͐��s�w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02496,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�����̃��~�b�g�����͐��s�w��s�B");
        }

        double l_dblWLimitPrice = Double.parseDouble(l_strWLimitPrice);
        // validate�����P��(double, �������, �⏕����)
        this.validatePrice(l_dblWLimitPrice, l_equityTradedProduct, l_subAccount);

        //�戵�\��������(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum,
        //      �敨�^�I�v�V�����敪 : String, �M�p����敪 : String, �s��R�[�h : String)
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        EqtypeTradedProductRow l_equityTradedProductRow =
            (EqtypeTradedProductRow)l_equityTradedProduct.getDataSourceObject();
        WEB3GentradeMarket l_gentradeMarket = null;
        try
        {
            l_gentradeMarket =
                (WEB3GentradeMarket)l_gentradeFinObjectManager.getMarket(
                    l_equityTradedProductRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("error in l_gentradeFinObjectManager.getMarket(MarketId)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����郌�R�[�h������܂���");
        }

        WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
            l_subAccount.getInstitution().getInstitutionCode(),
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT,
            l_gentradeMarket.getMarketCode());

        // is�戵�\���s����(���s���� : EqTypeExecutionConditionType)
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_wLimitExecCondType))
        {
            log.debug("���͂��ꂽ�iW�w�l�j���s�����͎戵�s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02495,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂��ꂽ�iW�w�l�j���s�����͎戵�s�B");
        }

        //is���s
        boolean l_blnIsMarketPrice = false;

        //[�p�����[�^.�iW�w�l�j�����w�l == 0 �̏ꍇ]
        //true���Z�b�g
        if (l_dblWLimitPrice == 0)
        {
            l_blnIsMarketPrice = true;
        }

        // (*)�p�����[�^.�����J�e�S�� == "��������"�̏ꍇ
        if (OrderCategEnum.ASSET.equals(l_orderCateg))
        {
            // validate���s�w��K��
            //  (�������, is���s(boolean), is������(boolean), EqTypeExecutionConditionType)
            this.validateMarketOrderDesignateCtrl(
                l_equityTradedProduct,
                l_blnIsMarketPrice,
                !l_blnIsBuyToOpenOrder,
                l_wLimitExecCondType);
        }

        // (*)�p�����[�^.�����J�e�S�� �I= "��������"�̏ꍇ
        else
        {
            //is����
            boolean l_blnIsOpenSell = true;

            //�����J�e�S�� == "�V�K������"
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
            {
                //�p�����[�^.is��������true�̏ꍇ
                if (l_blnIsBuyToOpenOrder)
                {
                    l_blnIsOpenSell = false;
                }
            }
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                l_blnIsOpenSell = l_blnIsBuyToOpenOrder;
            }

            this.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                l_orderCateg,
                l_blnIsMarketPrice,
                l_blnIsOpenSell,
                l_wLimitExecCondType);
        }

        // (*)�iW�w�l�j�����w�l���w�l�i�p�����[�^.�iW�w�l�j�����w�l != 0�j�̏ꍇ
        if (l_dblWLimitPrice != 0)
        {
            // (*)�w�l�ƁiW�w�l�j�����w�l�̃`�F�b�N
            if (l_dblLimitPrice == l_dblWLimitPrice)
            {
                log.debug("�����P���ƁiW�w�l�j�����w�l�����l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02498,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����P���ƁiW�w�l�j�����w�l�����l�B");
            }
        }

        // validate���������P��(���X, double, double, �������, boolean)
        this.validateOrderCondPrice(
            l_subAccount.getWeb3GenBranch(),
            l_dblLimitPrice,
            l_dblOrderCondPrice,
            l_equityTradedProduct,
            l_blnIsBuyToOpenOrder);

        // is�󔄂�K��(�⏕����, �������, double, OrderTypeEnum,
        //       boolean, EqTypeExecutionConditionType, String, �����P��)
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        if (l_lngOrderUnitId != 0)
        {
            l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        }
        this.isShortSellingRestraint(
            l_subAccount,
            l_equityTradedProduct,
            l_dblQuantity,
            l_orderType,
            l_blnIsMarketPrice,
            l_wLimitExecCondType,
            l_strPriceConditionType,
            l_eqtypeOrderUnitRow);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���������P��)<BR>
     * �����������w�肳�ꂽ�����ɂ��āA<BR>
     * ���������P���̃`�F�b�N���s���B  <BR>
     * <BR>
     * �|���������P�������`�F�b�N<BR>
     * �|���������P�����̓`�F�b�N<BR>
     * ���v�Z�����͑S��BigDecimal�^�Ōv�Z���邱�ƁB<BR>
     * <BR>
     * �P�j�@@���������P���`�F�b�N���{���Ȃ����X�̏ꍇ�A���������P���`�F�b�N���s��Ȃ��B<BR>
     *�@@�P�|�P�j�@@���X�v���t�@@�����X�e�[�u������A���������P���`�F�b�N���{�敪���擾����B<BR>
     * <BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@���XID = �p�����[�^.���X.���XID <BR>
     *�@@�@@�@@�@@And�@@�v���t�@@�����X�� = �v���t�@@�����X��.W�w�l�����E���������P���`�F�b�N���{�敪<BR>
     *�@@�@@�@@�@@And�@@�v���t�@@�����X���̘A�� = 1�i�F���E�M�p�j<BR>
     * <BR>
     *�@@�@@�@@����L�����Ń��R�[�h���擾�ł��Ȃ������ꍇ�A"�`�F�b�N���Ȃ�"�Ƃ���B<BR>
     * <BR>
     *�@@�P�|�Q�j���������P���`�F�b�N���{�敪��"�`�F�b�N���Ȃ�"�̏ꍇ���^�[������B<BR>
     * <BR>
     * �Q�j�@@���������P�������`�F�b�N<BR>
     * �@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ̊Ԃ�<BR>
     * �@@���ݒl�̐����{�ȏ�̘��������邩�ǂ����`�F�b�N����B<BR>
     * �@@�Q�|�P�j�@@���ݒl���擾����B  <BR>
     * �@@�@@�g���v���_�N�g�}�l�[�W��.get���ݒl()���R�[������B<BR>
     * <BR>
     * �@@�@@[get���ݒl()�Ɏw�肷�����]<BR>
     * �@@�@@�@@��������F�@@�p�����[�^.������� <BR>
     * �@@�@@�@@��l�F�@@�p�����[�^.�w�l  <BR>
     *
     * �@@�Q�|�Q�j�@@�����l���擾����B<BR>
     * �@@�@@�����l = (�p�����[�^.���������P�� - �p�����[�^.�w�l)�̐�Βl<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����l�����ݒl*�{���ȏォ�ǂ����`�F�b�N����B<BR>
     * �@@�@@�����l���A�ȉ��̏����ɊY�����Ȃ��ꍇ�A  <BR>
     * �@@�@@�u���������P�����̓G���[�i�����l���w��̔{�������j�v�� <BR>
     * �@@�@@��O���X���[����B  <BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag  :BUSINESS_ERROR_02492<BR>
     * <BR>
     * �@@�@@�@@�����l >= �i���ݒl * �{��(*1)�j<BR>
     * <BR>
     * �@@�@@(*1)�{��<BR>
     * �@@�@@�@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������A<BR>
     * �@@�@@�@@�擾�������R�[�h.�v���t�@@�����X�̒l��{���Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���XID = �p�����[�^.���X.���XID<BR>
     * �@@�@@�@@�@@And�@@�v���t�@@�����X�� = �v���t�@@�����X��.W�w�l�����E���������P�������{��<BR>
     * �@@�@@�@@�@@And�@@�v���t�@@�����X���̘A�� = 1�i�F���E�M�p�j  <BR>
     * <BR>
     * �@@�@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�{�� = 1�Ƃ���B<BR>
     * <BR>
     * �R�j�@@���������P�����̓`�F�b�N<BR>
     * �@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ� <BR>
     * �@@���������ݍ���ł��邩�ǂ����`�F�b�N����B  <BR>
     * �@@�R�|�P�j�@@�������擾����B  <BR>
     * �@@�@@�g���v���_�N�g�}�l�[�W��.get����()���R�[������B <BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����]  <BR>
     * �@@�@@�@@��������F�@@�p�����[�^.�������  <BR>
     * <BR>
     * �@@�R�|�Q�j�@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ�<BR>
     * �@@�@@���������ݍ���ł��邩�ǂ����`�F�b�N����B  <BR>
     * �@@�@@�ȉ��̏����𖞂����Ȃ��ꍇ�A  <BR>
     * �@@�@@�u���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�v�� <BR>
     * �@@�@@��O���X���[����B  <BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag  :BUSINESS_ERROR_02493<BR>
     * <BR>
     * �@@�@@[���i�p�����[�^.is������ == true�j�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�w�l <= �擾�������� < �p�����[�^.���������P�� <BR>
     * <BR>
     * �@@�@@[���i��L�ȊO�j�̏ꍇ]  <BR>
     * �@@�@@�@@�p�����[�^.�w�l >= �擾�������� > �p�����[�^.���������P��<BR>
     *
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_dblOrderCondPrice - (���������P��)<BR>
     * ��������<BR>
     * @@param l_equityTradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g<BR>
     * @@param l_blnIsBuyOrder - (is������)<BR>
     * �iisBuyToOpenOrder�j<BR>
     * ������������ǂ����̔���B<BR>
     * �������̏ꍇtrue�A�������̏ꍇfalse�B<BR>
     * @@throws WEB3BaseException
     */
    public void validateOrderCondPrice(
        WEB3GentradeBranch l_branch,
        double l_dblLimitPrice,
        double l_dblOrderCondPrice,
        WEB3EquityTradedProduct l_equityTradedProduct,
        boolean l_blnIsBuyOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCondPrice(WEB3GentradeBranch, double, double,"
            + " WEB3EquityTradedProduct, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_equityTradedProduct == null || l_branch == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�@@���������P���`�F�b�N���{���Ȃ����X�̏ꍇ�A���������P���`�F�b�N���s��Ȃ��B
        //�@@�P�|�P�j�@@���X�v���t�@@�����X�e�[�u������A���������P���`�F�b�N���{�敪���擾����B
        //�@@�@@�@@[����]
        //�@@�@@�@@�@@���XID = �p�����[�^.���X.���XID
        //�@@�@@�@@�@@And�@@�v���t�@@�����X�� = �v���t�@@�����X��.W�w�l�����E���������P���`�F�b�N���{�敪
        //�@@�@@�@@�@@And�@@�v���t�@@�����X���̘A�� = 1�i�F���E�M�p�j
        //
        //�@@�@@�@@����L�����Ń��R�[�h���擾�ł��Ȃ������ꍇ�A"�`�F�b�N���Ȃ�"�Ƃ���B
        BranchPreferencesRow l_brReferencesRow = null;
        try
        {
            l_brReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_branch.getBranchId(),
                WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_CHECK_ORDER_COND_PRICE,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@�P�|�Q�j���������P���`�F�b�N���{�敪��"�`�F�b�N���Ȃ�"�̏ꍇ���^�[������B
        if (l_brReferencesRow == null
            || WEB3TriggerorderWlimitorderCheckOrderCondPriceDef.DEFAULT.equals(
                l_brReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�P�j�@@���������P�������`�F�b�N
        //�@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ̊Ԃ�
        //�@@���ݒl�̐����{�ȏ�̘��������邩�ǂ����`�F�b�N����B
        //�@@�P�|�P�j�@@���ݒl���擾����B
        //�@@�@@�g���v���_�N�g�}�l�[�W��.get���ݒl()���R�[������B
        //�@@�@@[get���ݒl()�Ɏw�肷�����]
        //�@@�@@�@@��������F�@@�p�����[�^.�������
        //�@@�@@�@@��l�F�@@�p�����[�^.�w�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        double l_dblTickValue =
            l_productMgr.getTickValue(l_equityTradedProduct, l_dblLimitPrice);

        //�@@�P�|�Q�j�@@�����l���擾����B
        //�@@�@@�����l = (�p�����[�^.���������P�� - �p�����[�^.�w�l)�̐�Βl
        BigDecimal l_bdOrderCondPrice = new BigDecimal(l_dblOrderCondPrice);
        BigDecimal l_bdLimitPrice = new BigDecimal(l_dblLimitPrice);

        //�����l
        BigDecimal l_bdEstrangePrice =
            (l_bdOrderCondPrice.subtract(l_bdLimitPrice)).abs();

        //���ݒl
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue);

        //�@@�@@(*1)�{��
        //�@@�@@�@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������A
        //�@@�@@�@@�擾�������R�[�h.�v���t�@@�����X�̒l��{���Ƃ���B
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_branch.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_DIVERGENCERATE,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        BigDecimal l_bdRate = null;
        if (l_branchReferencesRow == null)
        {
            //���������ʂ��擾�ł��Ȃ������ꍇ�A�{�� = 1�Ƃ���B
            l_bdRate = new BigDecimal(1);
        }
        else
        {

            l_bdRate = new BigDecimal(l_branchReferencesRow.getValue());
        }

        //�@@�P�|�R�j�@@�����l�����ݒl*�{���ȏォ�ǂ����`�F�b�N����B
        //�@@�@@�����l���A�ȉ��̏����ɊY�����Ȃ��ꍇ�A
        //�@@�@@�u���������P�����̓G���[�i�����l���w��̔{�������j�v��
        //�@@�@@��O���X���[����B
        //
        //�@@�@@�@@�����l >= �i���ݒl * �{��(*1)�j
        if (l_bdEstrangePrice.compareTo(l_bdTickValue.multiply(l_bdRate)) < 0)
        {
            log.debug("���������P�����̓G���[�i�����l���w��̔{�������j�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02492,
                getClass().getName() + STR_METHOD_NAME,
                "���������P�����̓G���[�i�����l���w��̔{�������j�B");
        }

        //�Q�j�@@���������P�����̓`�F�b�N
        //�@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ�
        //�@@���������ݍ���ł��邩�ǂ����`�F�b�N����B
        //�@@�Q�|�P�j�@@�������擾����B
        //�@@�@@�g���v���_�N�g�}�l�[�W��.get����()���R�[������B
        //�@@�@@[get����()�Ɏw�肷�����]
        //�@@�@@�@@��������F�@@�p�����[�^.�������
        double l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_equityTradedProduct);
        BigDecimal l_bdCurrentPrice = new BigDecimal(l_dblCurrentPrice);

        //�@@�Q�|�Q�j�@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ�
        //�@@�@@���������ݍ���ł��邩�ǂ����`�F�b�N����B
        //�@@�@@�ȉ��̏����𖞂����Ȃ��ꍇ�A
        //�@@�@@�u���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�v��
        //�@@�@@��O���X���[����B
        //�@@�@@[���i�p�����[�^.is������ == true�j�̏ꍇ]
        //�@@�@@�@@�p�����[�^.�w�l <= �擾�������� < �p�����[�^.���������P��
        if (l_blnIsBuyOrder)
        {
            if (!(l_bdLimitPrice.compareTo(l_bdCurrentPrice) <= 0
                && l_bdCurrentPrice.compareTo(l_bdOrderCondPrice) < 0))
            {
                log.debug("���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02493,
                    getClass().getName() + STR_METHOD_NAME,
                    "���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�B");
            }
        }

        //�@@�@@[���i��L�ȊO�j�̏ꍇ]
        //�@@�@@�@@�p�����[�^.�w�l >= �擾�������� > �p�����[�^.���������P��
        else
        {
            if (!(l_bdLimitPrice.compareTo(l_bdCurrentPrice) >= 0
                && l_bdCurrentPrice.compareTo(l_bdOrderCondPrice) > 0))
            {
                log.debug("���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02493,
                    getClass().getName() + STR_METHOD_NAME,
                    "���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * <BR>
     * �ivalidate�V�K����������j<BR>
     * �s������ʂ̌���������̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�s������ʌ��P�����̏���P�������擾����B<BR>
     *  �P�|�P�j�@@(���X�s����敪��)�戵�����𐶐�����B<BR>
     *       [����]<BR>
�@@�@@ *        ���X�h�c�@@�F�@@���X�I�u�W�F�N�g.���X�h�c�@@(*1)<BR>
     *        �s��h�c�@@�F�@@�����̎������.�s��h�c<BR>
     *        ���敪�@@�F�@@�����̎������.���敪<BR>
     *        �V�s��敪�@@�F�@@�����̎������.�V�s��敪<BR>
     *        �X�����J�敪�@@�F�@@�����̎������.�X�����J�敪<BR>
     * <BR>
     *        (*1)�����̕⏕����.get����X( )�ɂ�蕔�X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�|�Q�j�@@(���X�s����敪��)�戵����.get���P��������l()���R�[������B<BR>
     * <BR>
     * �P�|�R�j�@@�ȉ��ɊY������ꍇ�A�s������ʂ̃`�F�b�N���X�L�b�v����B<BR>
     *     �P�|�P�j�ŃG���[���X���[���ꂽ�ꍇ<BR>
     *     �P�|�Q�j��get���P��������l�̖߂�l��null�̏ꍇ<BR>
     * <BR>
     * �Q�j�@@�s������ʌ���������`�F�b�N���s���B<BR>
     * <BR>
     * �Q�|�P�j�@@�Y���ڋq�E�s��E�����̑S�Ă̗L���Ȍ����I�u�W�F�N�g���擾����B<BR>
     * ------------------------------------------------------------------<BR>
     * �������I�u�W�F�N�g�擾��<BR>
     *   �����|�W�V�����}�l�[�W��.get�����ꗗ(<BR>
     *   �����̕⏕����, <BR>
     *   �����^�C�v�i�����iEQUITY�j�j,<BR>
     *   ��������������i*1�j,<BR>
     *   ���������f�[�^�R���e�i�i*2�j)<BR>
     *   �ɂ��擾����B<BR>
     * <BR>
     *  �i*1�j�s��ID�E����ID�E��������and�����Ŏw�肷��B<BR>
     *   ��"market_id = ? and product_id = ? and quantity > ?"<BR>
     * <BR>
     *  �i*2�j�s��ID�̌��������w��l�Ƃ��āA�����̎������.�s��ID��ݒ肷��B<BR>
     *   ����ID�̌��������w��l�Ƃ��āA�����̎������.����ID��ݒ肷��B<BR>
     *   �������̌��������w��l�Ƃ��āA"0"��ݒ肷��B<BR>
     * ------------------------------------------------------------------<BR>
     * <BR>
     * �擾���������I�u�W�F�N�g�S�Ă̌��������W�v����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�Y���ڋq�E�s��E�����̐V�K���������i��萬���҂��j�̒����P�ʃI�u�W�F�N�g��<BR>
     *           �S�Ď擾����B<BR>
     * ------------------------------------------------------------------<BR>
     * �������P�ʃI�u�W�F�N�g�擾��<BR>
     * �g�����������}�l�[�W��.get�����P�ʈꗗ(<BR>
     *       �����̕⏕����, <BR>
     *       �����^�C�v�i�����iEQUITY�j�j,<BR>
     *       ��������������i*1�j,<BR>
     *       ���������f�[�^�R���e�i�i*2�j,<BR>
     *       null)<BR>
     *       �ɂ��擾����B<BR>
     * <BR>
     *�i*1�j�s��ID�E����ID�E�����J�e�S���E�����L����Ԃ�and�����Ŏw�肷��B<BR>
     * ��"market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?"<BR>
     * <BR>
     * �����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �����P��ID��not�w���ǉ��w�肵�A�����Ώے��������O����B<BR>
     * ��" and order_unit_id != ?"
     * <BR>
     *�i*2�j�s��ID�F�@@�����̎������.�s��ID��ݒ�<BR>
     * ����ID�F�@@�����̎������.����ID��ݒ�<BR>
     * �����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j��intValue<BR>
     * �����L����ԁF�@@OrderOpenStatusEnum.�h�I�[�v���h�iOPEN�j��intValue<BR>
     * <BR>
     * �����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�̂݁A�ȉ����ݒ�B<BR>
     * �����P��ID�F�@@�����̒����P��.�����P��ID<BR>
     * ------------------------------------------------------------------<BR>
     * <BR>
     * �擾���������P�ʃI�u�W�F�N�g�S�ẮA�ȉ��̌v�Z���ʂ��W�v����B<BR>
     * --------------------------------<BR>
     * �e�����P�ʃI�u�W�F�N�g���A�ȉ��̒ʂ�ɒl���擾����B<BR>
     * <BR>
     * �E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     *       �����P��.�������ʂ��擾����B<BR>
     * <BR>
     * �E�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�null�̏ꍇ<BR>
     *       �i�����P��.�s�ꂩ��m�F�ς݂̐��� �| �����P��.��萔�ʁj���v�Z����B<BR>
     * <BR>
     * --------------------------------<BR>
     * <BR>
     * �Q�|�R�j�@@�i�@@�����̒�������(*) �{ �Q�|�P�j�Ŏ擾���������� �{ �Q�|�R�j�Ŏ擾�����������@@�j > <BR>
     * �i�@@�P�|�Q�j�Ŏ擾�������P��������l�@@�~�@@�����̎������.�����P�ʁ@@�j�̏ꍇ�́A<BR>
     * �u�������̏���l����(�s�������)�v�̗�O��throw����B<BR>
     * <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_02871<BR>
     * (*)�����̒������ʁF<BR>
     * �����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �����̒����P��.��萔�ʂ��T���������ʂ��g�p����B<BR>
     * (*)�����̒������ʁF<BR>
     * �����̒����P�ʃI�u�W�F�N�g��null�i�������̒����j�̏ꍇ�́A<BR>
     * �����̒����P��.��萔�ʂ��T���������ʂ��g�p����B<BR>
     * <BR>
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j�B<BR>
     * @@param l_dblQuantity �������ʁB<BR>
     * @@param l_orderType ������ʁB�ixTrade��OrderTypeEnum�ɂĒ�`�j<BR>
     * �@@�@@�@@�i�����������^�V�K���������^���������j<BR>
     * @@param l_tradedProduct ��������B<BR>
     * @@param l_changeOrderUnit �����Ώے����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void validateMaxOpenMarginQuantity(
        WEB3GentradeSubAccount l_subAccount, 
        double l_dblQuantity,
        OrderTypeEnum l_orderType,
        WEB3EquityTradedProduct l_tradedProduct,
        EqtypeOrderUnitRow l_changeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMaxOpenMarginQuantity(l_subAccount,l_dblQuantity,l_orderType,l_tradedProduct,l_changeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //--------------------------------------------------------------------------------
        // �G���e�B�e�B�^�G���e�B�e�B���ڒl�̎擾
        //--------------------------------------------------------------------------------
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        Product l_product = l_tradedProduct.getProduct();
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            
        long l_lngBranchId = l_branch.getBranchId();
        long l_lngMarketId = l_tradedProductRow.getMarketId();
        String l_strListType = l_tradedProductRow.getListType();
        String l_strNewListType = l_tradedProductRow.getNewListType();
        String l_strOpenOtcDiv = l_tradedProductRow.getOpenOtcDiv();

        long l_lngProductId = l_product.getProductId();
        double l_dblLotSize = l_tradedProductRow.getLotSize();

        //--------------------------------------------------------------------------------
        // �����E�s��ʂ̌��P��������l���擾����
        //--------------------------------------------------------------------------------
        Double l_dblMaxOpenMarginUnitsByMarketProduct = null;
        try
        {
            WEB3GentradeBranchListmarketDealtCond l_branchListmarketDealtCond
                = new WEB3GentradeBranchListmarketDealtCond(
                    l_lngBranchId, l_lngMarketId, l_strListType, l_strNewListType, l_strOpenOtcDiv);
            l_dblMaxOpenMarginUnitsByMarketProduct = l_branchListmarketDealtCond.getMaxContUnit(l_mainAccount);
        }
        catch (WEB3SystemLayerException l_e) {}
        
        if (l_dblMaxOpenMarginUnitsByMarketProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //--------------------------------------------------------------------------------
        // �W�v�Ώی������擾����
        //--------------------------------------------------------------------------------
        String l_strWhereForGetContract = " and market_id = ? and product_id = ? and quantity > ?";
        String[] l_containerListForGetContract = {
            String.valueOf(l_lngMarketId), String.valueOf(l_lngProductId), "0"};
            
        List l_contractListByMarketProduct = l_positionManager.getContracts(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhereForGetContract, l_containerListForGetContract);

        //--------------------------------------------------------------------------------
        // �������猚�������W�v����
        //--------------------------------------------------------------------------------
        double l_dblTotalContractQuantityByMarketProduct = 0D;
        if (l_contractListByMarketProduct != null)
        {
            for (int i = 0; i < l_contractListByMarketProduct.size(); i++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contractListByMarketProduct.get(i);
                l_dblTotalContractQuantityByMarketProduct += l_contractRow.getQuantity();
            }
        }
        
        //--------------------------------------------------------------------------------
        // �W�v�Ώے������擾����
        //--------------------------------------------------------------------------------
        String l_strWhereForGetOrder =
            "market_id = ? and product_id = ? and order_categ = ? and order_open_status = ?";
        String[] l_containerListForGetOrder = null;
        
        List l_list = new ArrayList();
        l_list.add(String.valueOf(l_lngMarketId));
        l_list.add(String.valueOf(l_lngProductId));
        l_list.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
        l_list.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));
        if (l_changeOrderUnit != null)
        {
            l_strWhereForGetOrder += " and order_unit_id <> ?";
            l_list.add(Long.toString(l_changeOrderUnit.getOrderUnitId()));
        }
        l_containerListForGetOrder = new String[l_list.size()];
        l_list.toArray(l_containerListForGetOrder);

        List l_orderListByMarketProduct = l_orderManager.getOrderUnits(
            l_subAccount, ProductTypeEnum.EQUITY, l_strWhereForGetOrder, l_containerListForGetOrder, null);
        
        //--------------------------------------------------------------------------------
        // ��������V�K���������ʂ��W�v����
        //--------------------------------------------------------------------------------
        double l_dblTotalOrderQuantityByMarketProduct = 0D;
        if (l_orderListByMarketProduct != null)
        {
            for (int i = 0; i < l_orderListByMarketProduct.size(); i++)
            {
                OrderUnit l_orderUnit = (OrderUnit) l_orderListByMarketProduct.get(i);
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
                if (l_orderUnitRow.getConfirmedQuantityIsNull())
                {
                    l_dblTotalOrderQuantityByMarketProduct += l_orderUnitRow.getQuantity();
                }
                else
                {
                    l_dblTotalOrderQuantityByMarketProduct +=
                        l_orderUnitRow.getConfirmedQuantity() - l_orderUnitRow.getExecutedQuantity();
                }
                log.debug("l_dblTotalOrderQuantityByMarketProduct=" + l_dblTotalOrderQuantityByMarketProduct);
            }
        }

        //--------------------------------------------------------------------------------
        // ���񒍕��̖���萔�ʂƌ������ƐV�K���������ʂ����v���A�s������ʂ̌����������߂�
        //--------------------------------------------------------------------------------
        double l_dblUnExecutedQuantity = 0D;
        if (l_changeOrderUnit == null)
        {
            l_dblUnExecutedQuantity = l_dblQuantity;
        }
        else{
            l_dblUnExecutedQuantity = l_dblQuantity - l_changeOrderUnit.getExecutedQuantity();
        }
        
        double l_totalOpenMarginQuantityByMarketProduct = l_dblUnExecutedQuantity +
            l_dblTotalContractQuantityByMarketProduct + l_dblTotalOrderQuantityByMarketProduct;
        log.debug("l_totalOpenMarginQuantityByMarketProduct=" + l_totalOpenMarginQuantityByMarketProduct);
        
        //--------------------------------------------------------------------------------
        // �s������ʂ̌���������l���Z�o����
        //--------------------------------------------------------------------------------
        double l_dblMaxOpenMarginQuantityByMarketProduct =
            l_dblMaxOpenMarginUnitsByMarketProduct.doubleValue() * l_dblLotSize;
        log.debug("l_dblMaxOpenMarginQuantityByMarketProduct=" + l_dblMaxOpenMarginQuantityByMarketProduct);
        
        //--------------------------------------------------------------------------------
        // �s������ʂ̌������@@���@@�s������ʂ̌���������l�̏ꍇ�G���[�Ƃ���
        //--------------------------------------------------------------------------------
        if (l_totalOpenMarginQuantityByMarketProduct > l_dblMaxOpenMarginQuantityByMarketProduct)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02871, STR_METHOD_NAME);
        } 
        log.debug("�������i�s������P�ʁjl_dblUnExecutedQuantity + l_dblTotalContractQuantityByMarketProduct + l_dblTotalOrderQuantityByMarketProduct="
            + (l_dblUnExecutedQuantity +
            l_dblTotalContractQuantityByMarketProduct +
            l_dblTotalOrderQuantityByMarketProduct));
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���ϊ�������)<BR>
     * �����̌����̌��ϊ������߂��`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���ϊ������߂̃`�F�b�N<BR>
     * �@@����.����.���� �� ������(*)�̏ꍇ�A<BR>
     * �@@�u�����������ϊ����𒴉߁v�̗�O��throw����B<BR>
     * �@@class:WEB3BusinessLayerException<BR>
     * �@@tag�@@:BUSINESS_ERROR_00748<BR>
     * <BR>
     * �@@(*)������ԊǗ�.������()<BR>
     * <BR>
     * @@param l_equityContract - (����)<BR>
     * ����
     * @@throws WEB3BaseException
     */
    public void validateCloseDateExcess(WEB3EquityContract l_equityContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateCloseDateExcess(WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);

        // ������ԊǗ�.������()
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        // ����.����.����
        Date l_datCloseDate = l_equityContract.getCloseDate();

        // ����.����.���� �� ������(*)�̏ꍇ
        if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datCloseDate) > 0)
        {
            log.debug("�����������ϊ����𒴉�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00748,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y�������̔����������ϊ����𒴂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�蓮�������ω\)<BR>
     * �蓮�������ϒ����̉�/�s�𔻒肷��B<BR>
     * �蓮�������ς͈��҂̂ݓo�^�\�B<BR>
     * <BR>
     * �P�j�@@�����Ώۂ̔���<BR>
     * �@@����.�蓮�������σt���O == false�̏ꍇ�A�������I������B<BR>
     * �@@�@@���蓮�������ϒ����ȊO�̓`�F�b�N���Ȃ�<BR>
     * <BR>
     * �Q�j�@@�蓮�������ω�/�s�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@���҂̃`�F�b�N<BR>
     * �@@�@@����.���� == null�̏ꍇ�A<BR>
     * �@@�@@�u���҈ȊO�͎蓮�������ϕs�v�̗�O��throw����B<BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:BUSINESS_ERROR_02809<BR>
     * <BR>
     * @@param l_blnManualForcedSettleFlag - (�蓮�������σt���O)<BR>
     * �蓮�������σt���O
     * @@param l_trader - (����)<BR>
     * ����
     * @@throws WEB3BaseException
     */
    public void validateManualForcedSettlePossible(boolean l_blnManualForcedSettleFlag, Trader l_trader)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualForcedSettlePossible(boolean, Trader)";
        log.entering(STR_METHOD_NAME);

        // ����.�蓮�������σt���O == false�̏ꍇ�A�������I������B
        if (!l_blnManualForcedSettleFlag)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // �@@�Q�|�P�j�@@���҂̃`�F�b�N
        // ����.���� == null�̏ꍇ�A
        if (l_trader == null)
        {
            // �u���҈ȊO�͎蓮�������ϕs�v�̗�O��throw����B
            log.debug("���҈ȊO�͎蓮�������ϕs��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02809,
                this.getClass().getName() + STR_METHOD_NAME,
                "���҈ȊO�͎蓮�������ϕs��");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�@@�\�a������)<BR>
     * �@@�\�a���ɓ��ӂ��Ă���ڋq�̂ݎ���\�Ƃ���`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ېU�i�@@�\�a���j���Ӄ`�F�b�N<BR>
     * �@@����.�⏕��������ڋq���擾����B<BR>
     * <BR>
     * �@@�擾�����ڋq.is�ېU�i�@@�\�a���j����() �� false�̏ꍇ�A<BR>
     * �@@�u�@@�\�a���ɓ��ӂ���Ă��Ȃ����߁A����ł��܂���v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  :�@@BUSINESS_ERROR_02964<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@throws WEB3BaseException
     */
    public void validateMechanismDepositAgree(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMechanismDepositAgree(SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����.�⏕��������ڋq���擾����B
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //�擾�����ڋq.is�ېU�i�@@�\�a���j����() �� false�̏ꍇ
        //�u�@@�\�a���ɓ��ӂ���Ă��Ȃ����߁A����ł��܂���v�̗�O��throw����B
        if (!l_gentradeMainAccount.isOrgDepositAgree())
        {
            log.debug("�@@�\�a���ɓ��ӂ���Ă��Ȃ����߁A����ł��܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02964,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�@@�\�a���ɓ��ӂ���Ă��Ȃ����߁A����ł��܂���");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
