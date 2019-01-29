head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�����������}�l�[�W��(WEB3FeqOrderManager.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 �����F (���u) �V�K�쐬
                   2005/07/27 䈋��@@(���u) ���r���[
                   2006/10/17 �����q (���u) ���r���[
                   2006/11/20 ����� (���u) �d�l�ύX ���f��301
Revesion History : 2007/11/06 �����q (���u) �d�l�ύX ���f��359
Revesion History : 2007/11/20 �����q (���u) �d�l�ύX ���f��365
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.478
Revesion History : 2009/07/31 �З��(���u) ���f��No.517�ANo.518�ANo.521
Revesion History : 2010/09/08 ��V��(���u) ���f��No.544
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrder;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * (�O�����������}�l�[�W��)<BR>
 * �O�����������}�l�[�W���N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqOrderManager extends FeqOrderManagerImpl
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderManager.class);

    /**
     * @@roseuid 42CE39EA032C
     */
    public WEB3FeqOrderManager()
    {

    }

    /**
     * (validate����)<BR>
     * �������ʃ`�F�b�N�����{����B<BR>
     * <BR>
     * �ȉ��̃`�F�b�N���s���B<BR>
     * �@@�|��t���ԃ`�F�b�N<BR>
     * �@@�|�V�X�e����~���`�F�b�N<BR>
     * �@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j<BR>
     * �@@�|�O�����������J�݃`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jvalidate�����v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428B13C9022B
     */
    public void validateOrder(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        // 1.1. getInstance()
        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();

        // 1.2. validate������t�\( )
        WEB3FeqTradingTimeManagement.validateOrderAccept();

        // 1.3. validate����\�ڋq(�⏕���� : SubAccount)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();
        OrderValidationResult l_result = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        if (!OrderValidationResult.VALIDATION_OK_RESULT.equals(l_result))
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                + l_result.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.4. validate�O�����������J��(�⏕����)
        l_orderMgrResVal.validateFeqAccountEstablish(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�O������)<BR>
     * �����`�F�b�N���s���B <BR>
     * �i* �O�����������R���`�F�b�N.validate�O������()�ɈϏ�����B�j <BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return FeqProduct
     * @@throws WEB3BaseException
     * @@roseuid 428B19840131
     */
    public FeqProduct validateFeqProduct(
        WEB3GentradeInstitution l_institution,
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFeqProduct(WEB3GentradeInstitution, String)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        FeqProduct l_feqProduct;

        try
        {
            l_feqProduct = l_orderMgrResVal.validateFeqProduct(l_institution, l_strProductCode);
        }
        catch (OrderValidationException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3BusinessLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqProduct;
    }

    /**
     * (validate�������)<BR>
     * ��������̃`�F�b�N���s���B<BR>
     * <BR>
     *   �E���݃`�F�b�N<BR>
     *   �E���`�F�b�N<BR>
     * �@@�E����K���`�F�b�N<BR>
     * <BR>
     * �i* �O�����������R���ʃ`�F�b�N.validate�������()�ɈϏ�����B�j<BR>
     * @@param l_feqProduct - (�O����������)<BR>
     * �O�����������I�u�W�F�N�g<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * @@param l_blnIsBuyOrder - (is������)<BR>
     * �iisBuyOrder�j<BR>
     * ���������̔���<BR>
     * <BR>
     * true�F��<BR>
     * false�F��<BR>
     *
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 428B1B970160
     */
    public TradedProduct validateTradedProduct(
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        boolean l_blnIsBuyOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(WEB3FeqProduct, WEB3GentradeMarket, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        TradedProduct l_tradedProduct = l_orderMgrResVal.validateTradedProduct(
            l_feqProduct,
            l_market,
            l_blnIsBuyOrder);

        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }

    /**
     * (validate�������)<BR>
     * ��������̃`�F�b�N���s���B<BR>
     * <BR>
     *   �E���݃`�F�b�N<BR>
     *   �E���`�F�b�N<BR>
     * <BR>
     * �i* �O�����������R���ʃ`�F�b�N.validate�������(�O����������, �s��)<BR>
     * �ɈϏ�����B�j<BR>
     * @@param l_feqProduct - (�O����������)<BR>
     * �O�����������I�u�W�F�N�g<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 42C20D0201F1
     */
    public TradedProduct validateTradedProduct(
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(WEB3FeqProduct, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        TradedProduct l_tradedProduct = l_orderMgrResVal.validateTradedProduct(
            l_feqProduct,
            l_market);

        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }

    /**
     * (validate�s��)<BR>
     * �s��̃`�F�b�N���s���B<BR>
     * �i* �O�����������R���ʃ`�F�b�N.validate�s��()�ɈϏ�����B�j<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429608F5035A
     */
    public void validateMarket(WEB3GentradeMarket l_market) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarket(WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateMarket(l_market);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�V�K����)<BR>
     * �ivalidateNewOrder�̃I�[�o�[���C�h�j<BR>
     * �V�K�����̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jvalidate�V�K�����v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     *
     * @@param l_orderSpec - (�������e)<BR>
     * �������e�I�u�W�F�N�g<BR>
     *
     * @@return NewOrderValidationResult
     * @@roseuid 428B390600D9
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        NewOrderSpec l_orderSpec)
    {
        final String STR_METHOD_NAME = "validateNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_orderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3FeqNewOrderSpec l_feqNewOrderSpec = (WEB3FeqNewOrderSpec)l_orderSpec;

        try
        {
            // 1.1. getInstance( )
            WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3FeqTypeOrderManagerReusableValidations)
                WEB3FeqTypeOrderManagerReusableValidations.getInstance();

            // 1.2. validate����(�⏕����)
            // [����] 
            //  �⏕�����F ����.�⏕���� 
            this.validateOrder(l_genSubAccount);

            // 1.3. getProductCode( )
            String l_strProductCode = l_feqNewOrderSpec.getProductCode();

            WEB3GentradeInstitution l_genInstitution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            // 1.4. validate�O������(�،����, String)
            // [����] 
            //  �،���ЁF �⏕����.getInstitutuin()�̖߂�l 
            //  �����R�[�h�F getProductCode()�̖߂�l 
            WEB3FeqProduct l_feqProduct =
                (WEB3FeqProduct)this.validateFeqProduct(
                    l_genInstitution,
                    l_strProductCode);

            // 1.5. get�s��( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();

            // 1.6. validate�s��(�s��)
            // [����] 
            //  �s��F get�s��()�̖߂�l 
            this.validateMarket(l_market);

            // 1.7. validate�戵�\�s��(String, String, String)
            // [����] 
            //  �،���ЃR�[�h�F �،����.getInstitutionCode()�̖߂�l 
            //  ���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
            //  �s��R�[�h�F �s��.getMarketCode()�̖߂�l 
            this.validateHandlingPossibleMarket(
                l_genInstitution.getInstitutionCode(),
                l_genSubAccount.getWeb3GenBranch().getBranchCode(),
                l_market.getMarketCode());

            // 1.8. isBuyOrder( )
            boolean l_blnIsBuyOrder = l_feqNewOrderSpec.isBuyOrder();
            log.debug("is���t���� = " + l_blnIsBuyOrder);

            // 1.9. validate�������(�O����������, �s��, boolean)
            // [����] 
            //  �O�����������F validate�O������()�̖߂�l 
            //  �s��F get�s��()�̖߂�l 
            //  is�������F isBuyOrder()�̖߂�l 
            FeqTradedProduct l_tradedProduct = (FeqTradedProduct)this.validateTradedProduct(
                l_feqProduct,
                l_market,
                l_blnIsBuyOrder);

            // 1.10. validate�ڋq�����ʎ����~(SubAccount, long, OrderTypeEnum)
            // [����] 
            //  �⏕�����F ����.�⏕���� 
            //  ����ID�F �O����������.����ID 
            //  ������ʁF �i�ȉ��̂Ƃ���j 
            //     isBuyOrder()�̖߂�l == true �̏ꍇ�A�h�O�������h 
            //     isBuyOrder()�̖߂�l == false �̏ꍇ�A�h�O������h
            OrderTypeEnum l_orderTypeEnum = (l_blnIsBuyOrder) ? OrderTypeEnum.FEQ_BUY : OrderTypeEnum.FEQ_SELL;
            l_orderMgrResVal.validateAccountProductTradedStop(
                l_subAccount,
                l_feqProduct.getProductId(),
                l_orderTypeEnum);

            // 1.11. getQuantity( )
            double l_dblQuantity = l_feqNewOrderSpec.getQuantity();
            log.debug("�������� = " + l_dblQuantity);

            // 1.12. validate��������(FeqTradedProduct, double, boolean)
            // [����] 
            //  �O��������������F �O��������������I�u�W�F�N�g 
            //  ���������F getQuantity()�̖߂�l 
            //  is�������F isBuyOrder()�̖߂�l 
            l_orderMgrResVal.validateQuantity(
                l_tradedProduct,
                l_dblQuantity,
                l_blnIsBuyOrder);

            // 1.13. getTaxType( )
            TaxTypeEnum l_taxTypeEnum = l_feqNewOrderSpec.getTaxType();

            // 1.14. (*1) isBuyOrder()�̖߂�l == false �̏ꍇ
            if(!l_blnIsBuyOrder)
            {
                // 1.14.1. validate���t�\����(�⏕����, long, double, TaxTypeEnum)
                // [����] 
                //  �⏕�����F ����.�⏕���� 
                //  ��������F �O����������.����ID 
                //  ���ʁF getQuantity()�̖߂�l 
                //  �ŋ敪�F getTaxType()�̖߂�l 
                l_orderMgrResVal.validateSellPossQuantity(
                    l_genSubAccount,
                    l_feqProduct.getProductId(),
                    l_dblQuantity,
                    l_taxTypeEnum);
            }

            // 1.15. getLimitPrice( )
            double l_dblLimitPrice = l_feqNewOrderSpec.getLimitPrice();
            log.debug("�w�l = " + l_dblLimitPrice);

            // 1.16. isMarketOrder( )
            boolean l_blnIsMarketOrder = l_feqNewOrderSpec.isMarketOrder();
            log.debug("isMarketOrder = " + l_blnIsMarketOrder);

            // 1.17. validate�����P��(TradedProduct, double, boolean)
            // [����] 
            //  ��������F �O��������������I�u�W�F�N�g 
            //  �����P���F getLimitPrice()�̖߂�l 
            //  is���s�����F isMarketOrder()�̖߂�l 
            l_orderMgrResVal.validatePrice(
                l_tradedProduct,
                l_dblLimitPrice,
                l_blnIsMarketOrder);

            // 1.18. get�iW�w�l�j�����w�l( )
            double l_dblWLimitPrice = l_feqNewOrderSpec.getWLimitPrice();
            log.debug("�iW�w�l�j�����w�l = " + l_dblWLimitPrice);

            // 1.19. (*2) get�iW�w�l�j�����w�l != 0 �̏ꍇ
            if (l_dblWLimitPrice != 0.0D)
            {
                // 1.19.1. validate�����P��(TradedProduct, double, boolean)
                // [����] 
                //  ��������F �O��������������I�u�W�F�N�g 
                //  �����P���F get�iW�w�l�j�����w�l()�̖߂�l 
                //  is���s�����F false
                l_orderMgrResVal.validatePrice(
                    l_tradedProduct,
                    l_dblWLimitPrice,
                    false);
            }

            // 1.20. get���ϋ敪( )
            String l_strSettleDiv = l_feqNewOrderSpec.getSettleDiv();
            log.debug("���ϋ敪 = " + l_strSettleDiv);

            // 1.21. (*3) get���ϋ敪()�̖߂�l == �h�O�݁h �̏ꍇ�A���{
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_strSettleDiv))
            {
                // 1.21.1. getCurrencyCode( )
                String l_strCurrencyCode = l_feqNewOrderSpec.getCurrencyCode();
                log.debug("�ʉ݃R�[�h = " + l_strCurrencyCode);

                // 1.21.2. validate�O�݌���(�⏕����, String)
                // [����] 
                //  �⏕�����F �⏕�����I�u�W�F�N�g 
                //  �ʉ݃R�[�h�F getCurrencyCode()�̖߂�l 
                l_orderMgrResVal.validateFcSettle(
                    l_genSubAccount,
                    l_strCurrencyCode);
            }

            // 1.22. (*4) getTaxType()�̖߂�l == �h����h �̏ꍇ�A���{
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum))
            {
                // 1.22.1. get������( )
                Date l_datBizDate = l_feqNewOrderSpec.getBizDate();
                log.debug("������ = " + l_datBizDate);

                // 1.22.2. validate��������J��(�⏕����, Date)
                // [����] 
                //  �⏕�����F �⏕�����I�u�W�F�N�g 
                //  �������F get������()�̖߂�l 
                l_orderMgrResVal.validateSpecialAccountEstablish(
                    l_genSubAccount,
                    l_datBizDate);
            }

            // 1.23. validate�ב֓o�^(�O����������, boolean, boolean)
            // [����] 
            //  �O�����������F �O�����������I�u�W�F�N�g 
            //  is���t�F isBuyOrder()�̖߂�l 
            //  is���F false 
            l_orderMgrResVal.validateRateRegistration(
                l_feqProduct,
                l_blnIsBuyOrder,
                false);

            // 1.24. validate��������(String, String, Date, Date, String, FeqExecutionConditionType, boolean)
            // [����] 
            //  �،���ЃR�[�h�F �،����.getInstitutionCode()�̖߂�l 
            //  �s��R�[�h�F �s��.getMarketCode()�̖߂�l 
            //  �������������F �i�ȉ��̂Ƃ���j 
            //    �E�V�K�������e.���񒍕��̒����P��ID > 0 and 
            //      ���񒍕��̒����P��ID != null(���J�z) �̏ꍇ �A���񒍕��̒����P��ID�ɑΉ����钍���P��.��������ݒ�  
            //    �E����ȊO�̏ꍇ�Anull��ݒ�  
            //  �����������F�@@�O�������V�K�������e.getOrderExpDate()�̖߂�l 
            //  ���������F �O�������V�K�������e.get��������()�̖߂�l 
            //  ���s�����F�@@�O�������V�K�������e.getExecutionConditionType()�̖߂�l 
            //  is�o����܂Œ����F�@@�O�������V�K�������e.is�o����܂Œ���()�̖߂�l 

            // ������������
            Date l_datOrgBizDate = null;
            Long l_lngFirstUnitId = l_feqNewOrderSpec.getFirstOrderUnitId();
            if (l_lngFirstUnitId != null && l_lngFirstUnitId.longValue() > 0)
            {
                FeqOrderUnitRow l_orderUnitRowOrg = null;
                try
                {
                    FeqOrderUnit l_orderUnitOrg = (FeqOrderUnit)this.getOrderUnit(l_lngFirstUnitId.longValue());
                    if (l_orderUnitOrg == null)
                    {
                        log.debug(STR_METHOD_NAME + "�\�����Ȃ��V�X�e���G���[���������܂����B");
                        log.exiting(STR_METHOD_NAME);
                        return new NewOrderValidationResult(
                            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002));
                    }
                    l_orderUnitRowOrg = (FeqOrderUnitRow)l_orderUnitOrg.getDataSourceObject();
                    l_datOrgBizDate = WEB3DateUtility.getDate(
                        l_orderUnitRowOrg.getBizDate(),
                        "yyyyMMdd");
                }
                catch (NotFoundException l_ex)
                {
                    log.debug(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    return new NewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80002));
                }
            }
            log.debug("������������ = " + l_datOrgBizDate);

            // �����������F�@@�O�������V�K�������e.getOrderExpDate()�̖߂�l
            Date l_datOrdExpDate = l_feqNewOrderSpec.getOrderExpDate();
            log.debug("���������� = " + l_datOrdExpDate);

            // ���������F �O�������V�K�������e.get��������()�̖߂�l
            String l_strOrdCondType = l_feqNewOrderSpec.getOrderConditionType();
            log.debug("�������� = " + l_strOrdCondType);

            // ���s�����F�@@�O�������V�K�������e.getExecutionConditionType()�̖߂�l
            FeqExecutionConditionType l_feqExeCondType = l_feqNewOrderSpec.getExecutionConditionType();
            log.debug("���s���� = " + l_feqExeCondType);

            // is�o����܂Œ����F�@@�O�������V�K�������e.is�o����܂Œ���()�̖߂�l
            boolean l_blnIsOrdUntDeaLine = l_feqNewOrderSpec.isOrderUntilDeadLine();
            log.debug("is�o����܂Œ��� = " + l_blnIsOrdUntDeaLine);

            l_orderMgrResVal.validateOrderCondition(
                l_genInstitution.getInstitutionCode(),
                l_market.getMarketCode(),
                l_datOrgBizDate,
                l_datOrdExpDate,
                l_strOrdCondType,
                l_feqExeCondType,
                l_blnIsOrdUntDeaLine);
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate�V�K����", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                    l_ex.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate�V�K����", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        // 1.25. NewOrderValidationResult(arg0 : ProcessingResult)
        log.exiting(STR_METHOD_NAME);
        return new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validate��������)<BR>
     * �ivalidateChangeOrder�̃I�[�o�[���C�h�j<BR>
     * ���������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jvalidate���������v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_orderSpec - (�������e)<BR>
     * �������e�I�u�W�F�N�g<BR>
     *
     * @@return OrderValidationResult
     * @@roseuid 429740FA036B
     */
    public OrderValidationResult validateChangeOrder(SubAccount l_subAccount, ChangeOrderSpec l_orderSpec)
    {
        final String STR_METHOD_NAME = "validateChangeOrder(SubAccount, ChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_orderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3FeqChangeOrderSpec l_feqChangeOrderSpec = (WEB3FeqChangeOrderSpec)l_orderSpec;

        try
        {
            // 1.1. getInstance( )
            WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3FeqTypeOrderManagerReusableValidations)
                WEB3FeqTypeOrderManagerReusableValidations.getInstance();

            // 1.2. validate����(�⏕����)
            // [����] 
            //  �⏕�����F ����.�⏕����
            this.validateOrder(l_genSubAccount);

            // 1.3. getOrderId( )
            long l_lngOrderId = l_feqChangeOrderSpec.getOrderId();
            //1.4.validate�����\�s��
            this.validateChangePossMarket(l_lngOrderId);
            // 1.5. validate���������\���(long)
            // [����] 
            //  ����ID�F getOrderId()�̖߂�l 
            this.validateOrderChangePossibleStatus(l_lngOrderId);

            // 1.6. getOrderUnitId( )
            long l_lngOrderUnitId = l_feqChangeOrderSpec.getOrderUnitId();
            log.debug("�����P��ID = " + l_lngOrderUnitId);

            // 1.7. getOrderUnit(�����P��ID : long)
            // [����] 
            //  �����P��ID�F getOrderUnitId()�̖߂�l 
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
            if (l_orderUnit == null)
            {
                log.debug(STR_METHOD_NAME + "�Y�������P�ʃf�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            // 1.8. get�O����������(long)
            // [����] 
            //  ����ID�F �����P��.����ID 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FeqProductManager l_productManager =
                (FeqProductManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getProductManager();

            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_productManager.getProduct(
                l_orderUnitRow.getProductId());
            if (l_feqProduct == null)
            {
                log.debug(STR_METHOD_NAME + "�Y���O�����������f�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            // 1.9. getProductCode( )
            String l_strProductCode = l_feqProduct.getProductCode();
            log.debug("�O�����������R�[�h = " + l_strProductCode);

            // 1.10. validate�O������(�،����, String)
            // [����] 
            //  �،���ЁF �⏕����.getInstitutuin()�̖߂�l 
            //  �����R�[�h�F getProductCode()�̖߂�l 
            WEB3GentradeInstitution l_genInstitution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            l_feqProduct = (WEB3FeqProduct)this.validateFeqProduct(
                l_genInstitution,
                l_strProductCode);

            // 1.11. get�s��( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();

            // 1.12. validate�s��(�s��)
            // [����] 
            //  �s��F get�s��()�̖߂�l 
            this.validateMarket(l_market);

            // 1.13. validate�戵�\�s��(String, String, String)
            // [����] 
            //  �،���ЃR�[�h�F �،����.getInstitutionCode()�̖߂�l 
            //  ���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
            //  �s��R�[�h�F �s��.getMarketCode()�̖߂�l
            this.validateHandlingPossibleMarket(
                l_genInstitution.getInstitutionCode(),
                l_genSubAccount.getWeb3GenBranch().getBranchCode(),
                l_market.getMarketCode());

            // 1.14. is���t( )
            boolean l_blnIsBuy = l_orderUnit.isBuy();
            log.debug("is���t = " + l_blnIsBuy);

            // 1.15. validate�������(�O����������, �s��, boolean)
            // [����] 
            //  �O�����������F validate�O������()�̖߂�l 
            //  �s��F get�s��()�̖߂�l 
            //  is�������F is���t()�̖߂�l 
            WEB3FeqTradedProduct l_feqTradedProduct = (WEB3FeqTradedProduct)this.validateTradedProduct(
                l_feqProduct,
                l_market,
                l_blnIsBuy);

            // 1.16. validate�ڋq�����ʎ����~(SubAccount, long, OrderTypeEnum)
            // [����] 
            //  �⏕�����F ����.�⏕���� 
            //  ����ID�F �O����������.����ID 
            //  ������ʁF �����P��.������� 
            l_orderMgrResVal.validateAccountProductTradedStop(
                l_subAccount,
                l_feqProduct.getProductId(),
                l_orderUnit.getOrderType());

            // 1.17. getAfterChangeOriginalQuantity( )
            double l_dblAftChaOriQuantity = l_feqChangeOrderSpec.getAfterChangeOriginalQuantity();
            log.debug("getAfterChangeOriginalQuantity() = " + l_dblAftChaOriQuantity);

            // 1.18. validate��������(FeqTradedProduct, double, boolean)
            // [����] 
            //  �O��������������F �O��������������I�u�W�F�N�g 
            //  ���������F getAfterChangeOriginalQuantity()�̖߂�l 
            //  is�������F is���t()�̖߂�l
            l_orderMgrResVal.validateQuantity(
                l_feqTradedProduct,
                l_dblAftChaOriQuantity,
                l_blnIsBuy);

            // 1.19. getAfterChangePrice( )
            double l_dblAftChaPrice = l_feqChangeOrderSpec.getAfterChangePrice();
            log.debug("getAfterChangePrice() = " + l_dblAftChaPrice);

            // 1.20. isAfterChangePriceMarket( )
            boolean l_blnIsAftChaPriMarket = l_feqChangeOrderSpec.isAfterChangePriceMarket();
            log.debug("isAfterChangePriceMarket() = " + l_blnIsAftChaPriMarket);

            // 1.21. validate�����P��(TradedProduct, double, boolean)
            // [����] 
            //  ��������F �O��������������I�u�W�F�N�g 
            //  �����P���F getAfterChangePrice()�̖߂�l 
            //  is���s�����F isAfterChangePriceMarket()�̖߂�l 
            l_orderMgrResVal.validatePrice(
                l_feqTradedProduct,
                l_dblAftChaPrice,
                l_blnIsAftChaPriMarket);

            // 1.22. get�����iW�w�l�j�����w�l( )
            double l_dblChaWLimitPrice = l_feqChangeOrderSpec.getChangeWLimitPrice();
            log.debug("�����iW�w�l�j�����w�l = " + l_dblChaWLimitPrice);

            // 1.23. (*2) get�����iW�w�l�j�����w�l != 0 �̏ꍇ�A���{
            if (l_dblChaWLimitPrice != 0)
            {
                // 1.23.1. validate�����P��(TradedProduct, double, boolean)
                // [����] 
                //  ��������F �O��������������I�u�W�F�N�g 
                //  �����P���F get�iW�w�l�j�����w�l()�̖߂�l 
                //  is���s�����F false 
                l_orderMgrResVal.validatePrice(
                    l_feqTradedProduct,
                    l_dblChaWLimitPrice,
                    false);
            }

            // 1.24. getConfirmedQuantity( )
            double l_dblConfirmedQuantity = l_orderUnit.getConfirmedQuantity();
            log.debug("getConfirmedQuantity() = " + l_dblConfirmedQuantity);

            // 1.25. (*3) getConfirmedQuantity()�̖߂�l != NaN �̏ꍇ�A���{
            if (!Double.isNaN(l_dblConfirmedQuantity))
            {
                // 1.25.1. validate�ǌ���������t�\(ProductTypeEnum)
                // [����] 
                //  �����^�C�v�F �h�O�������h 
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.FOREIGN_EQUITY);
            }

            // 1.26. get�������s����( )
            FeqExecutionConditionType l_feqExecutionConditionType = l_feqChangeOrderSpec.getChangeExecutionCondition();
            log.debug("�������s���� = " + l_feqExecutionConditionType);

            // 1.27. get�������������敪( )
            String l_strChangeOrdExpDiv = l_feqChangeOrderSpec.getChangeOrderExpirationDiv();
            log.debug("�������������敪 = " + l_strChangeOrdExpDiv);

            // 1.28. get���������L������( )
            Date l_datChaOrdExpDate = l_feqChangeOrderSpec.getChangeOrderExpirationDate();
            log.debug("���������L������ = " + l_datChaOrdExpDate);

            // 1.29. get��������( )
            String l_strOrdCondType = l_feqChangeOrderSpec.getOrderConditionType();
            log.debug("�������� = " + l_strOrdCondType);

            // 1.30. get���������������Z�q( )
            String l_strChaOrdCondOperator = l_feqChangeOrderSpec.getChangeOrderCondOperator();
            log.debug("���������������Z�q = " + l_strChaOrdCondOperator);

            // 1.31. get�������������P��( )
            double l_dblChaOrdCondPrice = l_feqChangeOrderSpec.getChangeOrderCondPrice();
            log.debug("�������������P�� = " + l_dblChaOrdCondPrice);
            
            // validate�ב֓o�^(�O������, boolean, boolean)
            // [����]
            // �O�����������F�@@�O�����������I�u�W�F�N�g
            // is���t�F�@@isBuyOrder()�̖߂�l
            // is���F�@@false
            l_orderMgrResVal.validateRateRegistration(
                l_feqProduct,
                l_blnIsBuy,
                false);

            // 1.32. validate��������(String, String, Date, Date,
            //          String, FeqExecutionConditionType, boolean)
            // [����] 
            //  �،���ЃR�[�h�F �،����.getInstitutionCode()�̖߂�l 
            //  �s��R�[�h�F �s��.getMarketCode()�̖߂�l 
            //  �������������F �����P��.������  
            //  �����������F�@@�����������e.get������������()�̖߂�l 
            //  ���������F �����������e.get��������()�̖߂�l 
            //  ���s�����F�@@�����������e.get�������s����()�̖߂�l 
            //  is�o����܂Œ����F�@@�i�ȉ��̂Ƃ���j 
            //    �����������e.get�������������敪 == �h�o����܂Œ����h �̏ꍇ�Atrue 
            //    �����������e.get�������������敪 == �h��������h �̏ꍇ�Afalse

            // �����������e.get�������������敪
            String l_strChaOrdExpDiv = l_feqChangeOrderSpec.getChangeOrderExpirationDiv();
            log.debug("�����������e.get�������������敪 = " + l_strChaOrdExpDiv);
            l_orderMgrResVal.validateOrderCondition(
                l_genInstitution.getInstitutionCode(),
                l_market.getMarketCode(),
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"),
                l_datChaOrdExpDate,
                l_strOrdCondType,
                l_feqExecutionConditionType,
                (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strChaOrdExpDiv))
                ? true : false);

            // 1.33. validate�������e(FeqOrderUnit, double, double,
            //          String, String, Date, String, String, double, double)
            // [����] 
            //  �����P�ʁF �����P�ʃI�u�W�F�N�g 
            //  �������ʁF getAfterChangeOriginalQuantity()�̖߂�l 
            //  �����P���F getAfterChangePrice()�̖߂�l 
            //  �������s�����F get�������s����()�̖߂�l
            //  �������������敪�F get�������������敪()�̖߂�l
            //  ���������L�������F get���������L������()�̖߂�l 
            //  ���������F get��������()�̖߂�l 
            //  ���������������Z�q�F get���������������Z�q()�̖߂�l 
            //  �������������P���F get�������������P��()�̖߂�l 
            //  �����iW�w�l�j�����w�l�F get�����iW�w�l�j�����w�l()�̖߂�l 
            l_orderMgrResVal.validateChangeSpec(
                l_orderUnit,
                l_dblAftChaOriQuantity,
                l_dblAftChaPrice,
                new Integer(l_feqExecutionConditionType.intValue()).toString(),
                l_strChangeOrdExpDiv,
                l_datChaOrdExpDate,
                l_strOrdCondType,
                l_strChaOrdCondOperator,
                l_dblChaOrdCondPrice,
                l_dblChaWLimitPrice);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate��������", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        catch (NotFoundException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate��������", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate��������", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                l_ex.getValidationResult().getProcessingResult());
        }

        log.exiting(STR_METHOD_NAME);
        // 1.34. OrderValidationResult(arg0 : ProcessingResult)
        return new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validate�������)<BR>
     * �ivalidateCancelOrder�̃I�[�o�[���C�h�j<BR>
     * ��������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jvalidate��������v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_orderSpec - (�������e)<BR>
     * �������e�I�u�W�F�N�g<BR>
     *
     * @@return NewOrderValidationResult
     * @@roseuid 429B4F3A0118
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_orderSpec)
    {
        final String STR_METHOD_NAME = "validateCancelOrder(SubAccount, CancelOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_orderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_genSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);

        try
        {
            // 1.1. getInstance( )
            WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3FeqTypeOrderManagerReusableValidations)
                WEB3FeqTypeOrderManagerReusableValidations.getInstance();

            // 1.2. validate����(�⏕����)
            // [����] 
            //  �⏕�����F ����.�⏕���� 
            this.validateOrder(l_genSubAccount);

            // 1.3. getOrderId( )
            long l_lngOrderId = l_orderSpec.getOrderId();

            // 1.4. validate��������\���(long)
            // [����] 
            //  ����ID�F getOrderId()�̖߂�l 
            this.validateOrderCancelPossibleStatus(l_lngOrderId);

            // 1.5. get�����P��ByOrderId(long)
            // [����] 
            //  ����ID�F getOrderUnitId()�̖߂�l 
            WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)this.getOrderUnitByOrderId(l_lngOrderId);

            // 1.6. get�O����������(long)
            // [����] 
            //  ����ID�F �����P��.����ID 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FeqProductManager l_productManager =
                (FeqProductManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getProductManager();

            FeqOrderUnitRow l_ordUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_productManager.getProduct(
                l_ordUnitRow.getProductId());
            if (l_feqProduct == null)
            {
                log.debug(STR_METHOD_NAME + "�Y���O�����������f�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            // 1.7. getProductCode( )
            String l_strProductCode = l_feqProduct.getProductCode();
            log.debug("�O�����������R�[�h" + l_strProductCode);

            // 1.8. validate�O������(�،����, String)
            // [����] 
            //  �،���ЁF �⏕����.getInstitutuin()�̖߂�l 
            //  �����R�[�h�F getProductCode()�̖߂�l 
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
            l_feqProduct = (WEB3FeqProduct)this.validateFeqProduct(l_institution, l_strProductCode);

            // 1.9. get�s��( )
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();

            // 1.10. validate�s��(�s��)
            // [����] 
            //  �s��F get�s��()�̖߂�l 
            this.validateMarket(l_market);

            // 1.11. validate�戵�\�s��(String, String, String)
            // [����] 
            //  �،���ЃR�[�h�F �،����.getInstitutionCode()�̖߂�l 
            //  ���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
            //  �s��R�[�h�F �s��.getMarketCode()�̖߂�l 
            this.validateHandlingPossibleMarket(
                l_institution.getInstitutionCode(),
                l_genSubAccount.getWeb3GenBranch().getBranchCode(),
                l_market.getMarketCode());

            // 1.12. validate�������(�O����������, �s��, boolean)
            // [����] 
            //  �O�����������F validate�O������()�̖߂�l 
            //  �s��F get�s��()�̖߂�l 
            //  is�������F�@@�����P��.is���t()�̖߂�l 
            boolean l_blnIsBuy = l_feqOrderUnit.isBuy();
            log.debug("is������" + l_blnIsBuy);
            this.validateTradedProduct(
                l_feqProduct,
                l_market,
                l_blnIsBuy);

            // 1.13. validate�ڋq�����ʎ����~(SubAccount, long, OrderTypeEnum)
            // [����] 
            //  �⏕�����F ����.�⏕���� 
            //  ����ID�F �O����������.����ID 
            //  ������ʁF �����P��.������� 
            l_orderMgrResVal.validateAccountProductTradedStop(
                l_subAccount,
                l_feqProduct.getProductId(),
                l_feqOrderUnit.getOrderType());

            // 1.14. getConfirmedQuantity( )
            double l_dblConfirmedQuantity = l_feqOrderUnit.getConfirmedQuantity();
            log.debug("getConfirmedQuantity()" + l_dblConfirmedQuantity);

            // 1.15  (*) getConfirmedQuantity()�̖߂�l != NaN �̏ꍇ
            if (!Double.isNaN(l_dblConfirmedQuantity))
            {
                // 1.15.1. validate�ǌ���������t�\(ProductTypeEnum)
                // [����] 
                //  �����^�C�v�F �h�O�������h 
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.FOREIGN_EQUITY);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate�������", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        catch (NotFoundException l_ex)
        {
            log.debug(STR_METHOD_NAME + "Error in validate�������", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }

        log.exiting(STR_METHOD_NAME);
        // 1.16. OrderValidationResult(arg0 : ProcessingResult)
        return new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validate�O�݌���)<BR>
     * �ڋq���w�肵���ʉ݂Ō��ς��\���ǂ������`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate�O�݌���()�ɈϏ��ideligate�j����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B6779102DE
     */
    public void validateFcSettle(
        WEB3GentradeSubAccount l_subAccount,
        String l_strCurrencyCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFcSettle(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateFcSettle(
            l_subAccount,
            l_strCurrencyCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��������J��)<BR>
     * ��������̃`�F�b�N���s���B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate��������J��()�ɈϏ�<BR>
     * �ideligate�j����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B676630203
     */
    public void validateSpecialAccountEstablish(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSpecialAccountEstablish(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateSpecialAccountEstablish(
            l_subAccount,
            l_datBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����)<BR>
     * �����̃`�F�b�N���s���B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate����()�ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_datExecDate - (����)<BR>
     * ����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292A2EC02D3
     */
    public void validateExecutionDate(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_datExecDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecutionDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateExecutionDate(
            l_orderUnit,
            l_datExecDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���n��n��)<BR>
     * ���n��n�����`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate���n��n��()<BR>
     * �ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_datFDeliveryDate - (���n��n��)<BR>
     * ���n��n��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B66851038A
     */
    public void validateFDeliveryDate(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_datFDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFDeliveryDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateFDeliveryDate(
            l_orderUnit,
            l_datFDeliveryDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��萔��)<BR>
     * ��萔�ʂ��`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate��萔��()�ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_dblQuantity - (��萔��)<BR>
     * ��萔�ʁi����j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292A2EC02E3
     */
    public void validateExecutedQuantity(
        WEB3FeqOrderUnit l_orderUnit,
        double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecutedQuantity(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateExecutedQuantity(
            l_orderUnit,
            l_dblQuantity);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���P��)<BR>
     * ���P�����`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate���P��()�ɈϏ��ideligate�j����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_dblPrice - (���P��)<BR>
     * ���P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42C39F0D0331
     */
    public void validateExecutedPrice(
        WEB3FeqOrderUnit l_orderUnit,
        double l_dblPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecutedPrice(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateExecutedPrice(
            l_orderUnit,
            l_dblPrice);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����P��ByOrderId)<BR>
     * �����h�c�ɊY�����钍���P�ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�����I�u�W�F�N�g�擾<BR>
     * �@@getOrder(�����h�c)�ɂĒ����I�u�W�F�N�g���擾����B<BR>
     * �@@�����h�c�ɊY�����钍���P�ʂ����݂��Ȃ��ꍇ�͗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02011<BR>
     * <BR>
     * �Q�j�@@�����P�ʎ擾<BR>
     * �@@����.getOrderUnits()�ɂĒ����P�ʂ̔z����擾����B<BR>
     * �@@�����h�c�ɊY�����钍���P�ʂ��Q���ȏ゠�����ꍇ<BR>
     * �@@�i�z��̗v�f�����P�łȂ��ꍇ�j�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02012<BR>
     * <BR>
     * �@@�擾���������P�ʂ�ԋp����B<BR>
     * @@param l_lngOrderId - (�����h�c)<BR>
     * �����h�c<BR>
     * @@return FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 428BF9050248
     */
    public FeqOrderUnit getOrderUnitByOrderId(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnitByOrderId(long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����I�u�W�F�N�g�擾
        FeqOrder l_feqOrder = null;
        try
        {
            l_feqOrder = (FeqOrder)getOrder(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.debug(STR_METHOD_NAME + "�Y�����������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                WEB3ErrorCatalog.BUSINESS_ERROR_02011.getErrorMessage(),
                l_ex);
        }
        if (l_feqOrder == null)
        {
            log.debug(STR_METHOD_NAME + "�Y�����������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j�@@�����P�ʎ擾
        OrderUnit[] l_orderUnits = l_feqOrder.getOrderUnits();
        if (l_orderUnits == null || l_orderUnits.length != 1)
        {
            log.debug(STR_METHOD_NAME + "�Y�������P�ʗv�f�����P�łȂ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02012,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return (FeqOrderUnit)l_orderUnits[0];
    }

    /**
     * (get�L�������P��By�^�p�R�[�h)<BR>
     * �^�p�R�[�h�ɊY�����钍���P�ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�����P�ʎ擾<BR>
     * �@@�ȉ��̏����ɂĒ����P�ʍs���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�������P��.������ =������ And<BR>
     * �@@�O�������P��.�^�p�R�[�h = �^�p�R�[�h And<BR>
     * �@@�O�������P��.�����L����� = 1�FOPEN And<BR>
     * �@@�O�������P��.�،���ЃR�[�h = �Ǘ��҂̏،���ЃR�[�h()��<BR>
     * <BR>
     * �@@�Y������s�����݂��Ȃ��ꍇ�́A�Y�������Ȃ��̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02086<BR>
     * <BR>
     * �@@�� �Ǘ��҂̏،���ЃR�[�h<BR>
     * �@@�Z�b�V���������擾����B<BR>
     * �@@�Z�b�V�������Ǘ��҂��擾�ł��Ȃ������ꍇ�́A���Y�������w�肵�Ȃ��B<BR>
     * <BR>
     * �Q�j�@@�����P�ʎ擾<BR>
     * �@@toOrderUnit()�ɂĒ����P�ʃI�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �@@[toOrderUnit()�Ɏw�肷�����]<BR>
     * �@@row�F�@@�P�j�Ŏ擾�����s�I�u�W�F�N�g<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@param l_strOrderEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@return FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 428BFBE10083
     */
    public FeqOrderUnit getValidOrderUnitByOrderEmpCode(
        Date l_datBizDate,
        String l_strOrderEmpCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValidOrderUnitByOrderEmpCode(Date, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����P�ʎ擾
        FeqOrderUnit l_orderUnit = null;
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisQueryVars = new ArrayList();
        l_sbWhere.append(" biz_date = ? ");                 //������
        l_alisQueryVars.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); //������
        l_sbWhere.append(" and order_emp_code = ? ");       //�^�p�R�[�h
        l_alisQueryVars.add(l_strOrderEmpCode);             //�^�p�R�[�h
        l_sbWhere.append(" and order_open_status = ? ");    //�����L�����
        l_alisQueryVars.add(OrderOpenStatusEnum.OPEN);      //�����L�����

        WEB3Administrator l_administrator;
        try
        {
            //�Z�b�V�������Ǘ��҂��擾
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //�Z�b�V�������Ǘ��҂��擾�ł����ꍇ
        if (l_administrator != null)
        {
            l_sbWhere.append(" and institution_code = ? ");                 //�،���ЃR�[�h
            l_alisQueryVars.add(l_administrator.getInstitutionCode());      //�،���ЃR�[�h
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_alisQueryVars.size()];
            l_alisQueryVars.toArray(l_objVars);
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �Y������s�����݂��Ȃ��ꍇ�́A�Y�������Ȃ��̗�O���X���[����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�Y�����钍��ID�f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_lisRecords.size() > 1)
        {
            log.debug("�Y�����钍��ID�f�[�^�͕s��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j�@@�����P�ʎ擾
        l_orderUnit = (FeqOrderUnit)this.toOrderUnit((FeqOrderUnitRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get�����P��By�^�p�R�[�h)<BR>
     * �^�p�R�[�h�ɊY�����钍���P�ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�����P�ʎ擾<BR>
     * �@@�ȉ��̏����ɂĒ����P�ʍs���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�������P��.������ =������ And<BR>
     * �@@�O�������P��.�^�p�R�[�h = �^�p�R�[�h And<BR>
     * �@@�O�������P��.�،���ЃR�[�h = �Ǘ��҂̏،���ЃR�[�h()��<BR>
     * <BR>
     * �@@�Y������s�����݂��Ȃ��ꍇ�́A�Y�������Ȃ��̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02086<BR>
     * <BR>
     * �@@�� �Ǘ��҂̏،���ЃR�[�h<BR>
     * �@@�Z�b�V���������擾����B<BR>
     * �@@�Z�b�V�������Ǘ��҂��擾�ł��Ȃ������ꍇ�́A���Y�������w�肵�Ȃ��B<BR>
     * <BR>
     * �Q�j�@@�����P�ʎ擾<BR>
     * �@@toOrderUnit()�ɂĒ����P�ʃI�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �@@[toOrderUnit()�Ɏw�肷�����]<BR>
     * �@@row�F�@@�P�j�Ŏ擾�����s�I�u�W�F�N�g<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@param l_strOrderEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@return FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 42AFD0E401C4
     */
    public FeqOrderUnit getOrderUnitByOrderEmpCode(
        Date l_datBizDate,
        String l_strOrderEmpCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnitByOrderEmpCode(Date, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����P�ʎ擾
        FeqOrderUnit l_orderUnit = null;
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisQueryVars = new ArrayList();
        l_sbWhere.append(" biz_date = ? ");                                 //������
        l_alisQueryVars.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd")); //������
        l_sbWhere.append(" and order_emp_code = ? ");                       //�^�p�R�[�h
        l_alisQueryVars.add(l_strOrderEmpCode);                             //�^�p�R�[�h

        WEB3Administrator l_administrator;
        try
        {
            //�Z�b�V�������Ǘ��҂��擾
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //�Z�b�V�������Ǘ��҂��擾�ł����ꍇ
        if (l_administrator != null)
        {
            l_sbWhere.append(" and institution_code = ? ");                 //�،���ЃR�[�h
            l_alisQueryVars.add(l_administrator.getInstitutionCode());      //�،���ЃR�[�h
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_alisQueryVars.size()];
            l_alisQueryVars.toArray(l_objVars);
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �Y������s�����݂��Ȃ��ꍇ�́A�Y�������Ȃ��̗�O���X���[����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�Y�����钍��ID�f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_lisRecords.size() > 1)
        {
            log.debug("�Y�����钍��ID�f�[�^�͕s��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j�@@�����P�ʎ擾
        l_orderUnit = (FeqOrderUnit)this.toOrderUnit((FeqOrderUnitRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get�L�����By���ԍ�)<BR>
     * ���ԍ��ɊY����������擾����B<BR>
     * <BR>
     * �P�j�@@���擾<BR>
     * �@@�ȉ��̏����ɂĖ��s���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�����.�����P�ʂh�c = �����P�ʂh�c And<BR>
     * �@@�O�����.��菇�ԍ� = ���ԍ� And<BR>
     * �@@�O�����.�폜�t���O = BooleanEnum.FALSE<BR>
     * <BR>
     * �@@�Y������s�����݂��Ȃ��ꍇ�͗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01321<BR>
     * <BR>
     * �Q�j�@@���擾<BR>
     * �@@toOrderExecution()�ɂĖ��I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �@@[toOrderExecution()�Ɏw�肷�����]<BR>
     * �@@row�F�@@�P�j�Ŏ擾�����s�I�u�W�F�N�g<BR>
     * @@param l_lngOrderUnit�hd - (�����P�ʂh�c)<BR>
     * �����P�ʂh�c<BR>
     * @@param l_intExecSerialNo - (���ʔ�)<BR>
     * ���ʔ�<BR>
     * @@return OrderExecution
     * @@throws WEB3BaseException
     * @@roseuid 4292DAF601AA
     */
    public OrderExecution getValidExecByExecNo(
        long l_lngOrderUnitId,
        int l_intExecSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getValidExecByExecNo(long, int)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���擾
        OrderExecution l_orderExec = null;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_unit_id = ? ");            //�����P�ʂh�c
        l_sbWhere.append(" and exec_serial_no = ? ");       //���ʔ�
        l_sbWhere.append(" and delete_flag = ? ");          //�폜�t���O

        Object[] l_objVars = {
            new Long(l_lngOrderUnitId),                     //�����P�ʂh�c
            new Long(l_intExecSerialNo),                    //���ʔ�
            BooleanEnum.FALSE};                             //�폜�t���O

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderExecutionRow.TYPE,
                l_sbWhere.toString(),
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �Y������s�����݂��Ȃ��ꍇ�͗�O���X���[����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�Y�����钍���P��ID�A���ʔԃf�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01321,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_lisRecords.size() > 1)
        {
            log.debug("�Y�����钍���P��ID�A���ʔԃf�[�^�͕s��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j�@@���擾
        l_orderExec = (OrderExecution)this.toOrderExecution(
            (FeqOrderExecutionRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderExec;
    }

    /**
     * (get���)<BR>
     * �����C�P���ɊY����������擾����B<BR>
     * <BR>
     * �P�j�@@���擾<BR>
     * �@@�ȉ��̏����ɂĖ��s���擾����B<BR>
     * �@@�������� ����, ��菇�ԍ� �����Ń\�[�g����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�����.�����P�ʂh�c = �����P�ʂh�c<BR>
     * �@@�O�����.��萔�� = ��萔��<BR>
     * �@@�O�����.���P�� = ���P��<BR>
     * �@@�O�����.�폜�t���O = "0�FFALSE"<BR>
     * <BR>
     * �@@�Y������s�����݂��Ȃ��ꍇ�͗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01321<BR>
     * <BR>
     * �Q�j�@@���擾<BR>
     * �@@toOrderExecution()�ɂĖ��I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �@@[toOrderExecution()�Ɏw�肷�����]<BR>
     * �@@row�F�@@�P�j�Ŏ擾�����s�I�u�W�F�N�g��0�Ԗڂ̗v�f<BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)<BR>
     * �����P�ʂh�c<BR>
     * @@param l_dblQuantity - (��萔��)<BR>
     * ��萔��<BR>
     * @@param l_dblPrice - (���P��)<BR>
     * ���P��<BR>
     * @@return OrderExecution
     * @@throws WEB3BaseException
     * @@roseuid 4292DBFD01E9
     */
    public OrderExecution getExecution(
        long l_lngOrderUnitId,
        double l_dblQuantity,
        double l_dblPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecution(long, double, double)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���擾
        OrderExecution l_orderExec = null;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_unit_id = ? ");            //�����P�ʂh�c
        l_sbWhere.append(" and exec_quantity = ? ");        //��萔��
        l_sbWhere.append(" and exec_price = ? ");           //���P��
        l_sbWhere.append(" and delete_flag = ? ");          //�폜�t���O

        // �\�[�g����
        String l_strOrderBy = " exec_timestamp asc, exec_serial_no asc ";

        Object[] l_objVars = {
            new Long(l_lngOrderUnitId),                     //�����P�ʂh�c
            new Double(l_dblQuantity),                      //��萔��
            new Double(l_dblPrice),                         //���P��
            BooleanEnum.FALSE};                             //�폜�t���O

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderExecutionRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // �Y������s�����݂��Ȃ��ꍇ�͗�O���X���[����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�Y�����钍���P��ID�f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01321,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �Q�j�@@���擾
        l_orderExec = (OrderExecution)toOrderExecution((FeqOrderExecutionRow) l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_orderExec;
    }

    /**
     * (get�v�Z�p�����P��)<BR>
     * �v�Z�p�����P�����擾����B<BR>
     * <BR>
     * �P�j����.�����P���敪 == �h���s�h �̏ꍇ<BR>
     * <BR>
     *    �������擾����B<BR>
     * <BR>
     *    �O�������v���_�N�g�}�l�[�W��.get����()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ��������F ����.�������<BR>
     * <BR>
     * �Q�j�v�Z�p�����P�������肷��B<BR>
     * <BR>
     * �Q�|�P�j����.is���t == true �̏ꍇ<BR>
     * <BR>
     *    �ȉ��̒�����A��ԋ��z�̑傫�����̂Ƃ���B<BR>
     * <BR>
     *    ����.�����P��<BR>
     *    ����.�����P��<BR>
     *    �P�j�̎擾����<BR>
     * <BR>
     * �Q�|�Q�j����.is���t == false �̏ꍇ<BR>
     * <BR>
     *    ����.�����P���敪 == �h�w�l�h �̏ꍇ�A����.�����P��<BR>
     *    ����.�����P���敪 == �h���s�h �̏ꍇ�A�P�j�Ŏ擾��������<BR>
     * <BR>
     *    �Ƃ���B<BR>
     *
     * �R�j�T�Z���z�v�Z�����̍l��<BR>
     * <BR>
     *    ����.is���t == true and<BR>
     *    �Q�j�Ō��肵���v�Z�p�����P�����P�j�̎擾����<BR>
     * <BR>
     *    �̏ꍇ�A�������s���B<BR>
     *    ���v�Z�����u�v�Z�p�����P���v�Q��<BR>
     * <BR>
     * �S�j�l��ԋp����B<BR>
     * @@param l_feqProduct - (�������)<BR>
     * �O��������������I�u�W�F�N�g
     *
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g
     *
     * @@param l_strOrderPriceDiv - (�����P���敪)<BR>
     * �����P���敪
     *
     * @@param l_dblPrice - (�����P��)<BR>
     * �����P��
     *
     * @@param l_dblChangePrice - (�����P��)<BR>
     * �����P��
     *
     * @@param l_blnIsBuy - (is���t)<BR>
     * ���t�������ǂ����𔻒f����t���O<BR>
     * <BR>
     * true�F ���t����<BR>
     * false�F ���t����<BR>
     *
     * @@return double
     * @@roseuid 428C90420070
     */
    public double getUnitPrice(
        WEB3FeqTradedProduct l_feqProduct,
        WEB3GentradeBranch l_branch,
        String l_strOrderPriceDiv,
        double l_dblPrice,
        double l_dblChangePrice,
        boolean l_blnIsBuy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitPrice(WEB3FeqTradedProduct, WEB3GentradeBranch, String, "
            + "double, double, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_feqProduct == null || l_branch == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //�P�j����.�����P���敪 == �h���s�h �̏ꍇ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productManager =
            (WEB3FeqProductManager)l_tradingModule.getProductManager();

        double l_dblCurrentPrice = 0.0D;
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_strOrderPriceDiv))
        {
            //�������擾����B
            l_dblCurrentPrice = l_productManager.getCurrentPrice(l_feqProduct);
        }
        log.debug("���� = " + l_dblCurrentPrice);

        // �Q�j�v�Z�p�����P�������肷��B
        // �Q�|�P�j����.is���t == true �̏ꍇ
        double l_dblMaxPrice = 0.0D;
        if (l_blnIsBuy)
        {
            // �ȉ��̒�����A��ԋ��z�̑傫�����̂Ƃ���B
            l_dblMaxPrice = Math.max(Math.max(l_dblPrice, l_dblChangePrice), l_dblCurrentPrice);
        }
        // �Q�|�Q�j����.is���t == false �̏ꍇ
        else
        {
            // ����.�����P���敪 == �h�w�l�h �̏ꍇ�A����.�����P��
            // ����.�����P���敪 == �h���s�h �̏ꍇ�A�P�j�Ŏ擾��������
            l_dblMaxPrice = WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv)
                ? l_dblPrice : l_dblCurrentPrice;
        }
        log.debug("��ԋ��z�̑傫�� = " + l_dblMaxPrice);

        // �R�j�T�Z���z�v�Z�����̍l��
        // ����.is���t == true and �Q�j�Ō��肵���v�Z�p�����P�����P�j�̎擾����
        // �̏ꍇ�A�������s���B
        double l_dblReturn = l_dblMaxPrice;
        if (l_blnIsBuy && l_dblMaxPrice == l_dblCurrentPrice)
        {
            // ���v�Z�����u�v�Z�p�����P���v�Q��
            // 2.1.  �v�Z�����擾
            // ��Е��X���i�s�I�u�W�F�N�g�擾
            InstBranchProductRow l_instBraProRow = null;
            try
            {
                // ��Е��X���i.���X�h�c = ���X.getBranchId() and
                // ��Е��X���i.�萔�����i�R�[�h = �g�O�������h
                l_instBraProRow = InstBranchProductDao.findRowByPk(
                    l_branch.getBranchId(),
                    WEB3CommisionProductCodeDef.FOREIGN_EQITY.toString());

                if (l_instBraProRow == null)
                {
                    log.debug("�Y�����镔�X�h�c�A�萔�����i�R�[�h�f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch(DataFindException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            // get�T�Z���z�v�Z����()
            int l_intEstPriceCalcForm = l_instBraProRow.getEstimatePriceCalcForm();
            log.debug("�T�Z���z�v�Z���� = " + l_intEstPriceCalcForm);

            // 2.2.  �v�Z���@@����A�v�Z�P���ԋp
            //  1. �T�Z���z�v�Z���� = �g�����S�������h�̏ꍇ
            if (WEB3PremiumRestraintRateDef.PREMIUM_RESTRANT == l_intEstPriceCalcForm)
            {
                // �P�j��l�擾
                double l_dblBase = l_dblMaxPrice;
                log.debug("��l = " + l_dblBase);

                // �Q�j�����S�����̔��f
                l_dblReturn =
                    new BigDecimal(String.valueOf(l_dblBase))
                        .multiply(new BigDecimal(String.valueOf(l_instBraProRow.getPremiumRestraintRate())))
                        .doubleValue();
                log.debug("�����S�����̔��f = " + l_dblReturn);
            }
            //  2. �T�Z���z�v�Z���� = �gSTOP���S�������h�̏ꍇ
            else if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_intEstPriceCalcForm)
            {
                // �P�jSTOP���Z�o��l�擾
                double l_dblStopHighPrice =
                    (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()) ?
                    l_feqProduct.getLastClosingPrice() :
                    l_dblMaxPrice;
                log.debug("STOP���Z�o��l = " + l_dblStopHighPrice);

                // 2) STOP���Z�o
                // �i*2-1�j�O�����������擾
                WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqProduct.getProduct();
                // �i*2-2�j�����l���擾
                double l_dblLimitRange = l_productManager.getLimitRange(l_product, l_dblStopHighPrice);
                log.debug("�����l�� = " + l_dblLimitRange);

                // �i*2-3�j���ݒl�擾
                double l_dblTickValue = l_productManager.getTickValue(l_product, l_dblStopHighPrice);
                log.debug("���ݒl = " + l_dblTickValue);

                // �i*2-4�j�l����l�擾
                WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3FeqTypeOrderManagerReusableValidations)
                    WEB3FeqTypeOrderManagerReusableValidations.getInstance();
                double l_dblBasePrice = l_orderMgrResVal.calcBasePrice(l_dblStopHighPrice, l_dblTickValue);
                log.debug("�l����l = " + l_dblBasePrice);

                // �i*2-5�j�l������iSTOP���j�Z�o
                l_dblReturn = l_orderMgrResVal.calcRangeCap(
                    l_dblBasePrice,
                    l_dblLimitRange,
                    l_product);
                log.debug("�l������iSTOP���j = " + l_dblReturn);
            }
        }
        log.debug("�v�Z�p�����P�� = " + l_dblReturn);

        log.exiting(STR_METHOD_NAME);
        return l_dblReturn;
    }

    /**
     * (update�T�Z��n���)<BR>
     * �T�Z��n����Čv�Z���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������jupdate�T�Z��n����v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4292B16A015C
     */
    public void updateEstimatedPrice(
        WEB3FeqOrderUnit l_orderUnit,
        Date l_ExecDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEstimatedPrice(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        // 1.1. getOrderOpenStatus( )
        OrderOpenStatusEnum l_ordOpenStatue = l_orderUnit.getOrderOpenStatus();
        double l_dblNetAmount = 0.0D;
        double l_dblNetAmountFc = 0.0D;
        double l_dblConfirmedNetAmount = 0.0D;
        double l_dblConfirmedNetAmountFc = 0.0D;
        // 1.2. (*) �������N���[�Y���Ă���ꍇ�igetOrderOpenStatus() == "�N���[�Y"�j
        FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStatue) &&
            !l_orderUnit.isUnexecuted())
        {

            WEB3FeqFinTransactionManager l_finTransactionMgr =
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();

            // 1.2.1. get��n������v�i�O�݁j(�O�����������P��)
            // [get��n������v�i�O�݁j()�Ɏw�肷�����] 
            //  �����P�ʁF�@@�����P�� 
            l_dblNetAmountFc = Math.abs(l_finTransactionMgr.getNetAmountFc(l_orderUnit));

            // 1.2.2. get��n������v(�O�����������P��)
            // [get��n������v()�Ɏw�肷�����] 
            //  �����P�ʁF�@@�����P�� 
            l_dblNetAmount = Math.abs(l_finTransactionMgr.getNetAmount(l_orderUnit));

            FeqOrderUnitParams l_orderUnitParams = new FeqOrderUnitParams(l_orderUnitRow);
            // �T�Z��n���
            l_orderUnitParams.setEstimatedPrice(l_dblNetAmount);
            // �T�Z��n����i�O�݁j
            l_orderUnitParams.setFEstimatedPrice(l_dblNetAmountFc);
            // �s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(l_dblNetAmount);
            // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_dblNetAmountFc);
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                // 1.2.3 doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)
                l_queryProcessor.doUpdateQuery(l_orderUnitParams);

                // ���������i�����������ŏI�ʔԂ̗����j���擾����B
                // ���������ŏI�ʔԂ��擾����B
                // ��������Row���擾����B
                String l_strWhere = " order_unit_id = ?  and order_action_serial_no = ? ";
                Object[] l_objVars = {
                    new Long(l_orderUnit.getOrderUnitId()),
                    new Integer(l_orderUnitRow.getLastOrderActionSerialNo())};
                List l_lisOrderActions = l_queryProcessor.doFindAllQuery(
                    FeqOrderActionRow.TYPE,
                    l_strWhere,
                    l_objVars);

                if (l_lisOrderActions == null || l_lisOrderActions.isEmpty())
                {
                    log.debug("�Y�����钍���P��ID�A���������ŏI�ʔԃf�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                if (l_lisOrderActions.size() > 1)
                {
                    log.debug("�Y�����钍���P��ID�A���������ŏI�ʔԃf�[�^�͕s��������B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                FeqOrderActionRow l_orderActionRow = (FeqOrderActionRow) l_lisOrderActions.get(0);
                FeqOrderActionParams l_orderActionParams = new FeqOrderActionParams(l_orderActionRow);
                
                // �T�Z��n���
                l_orderActionParams.setEstimatedPrice(l_dblNetAmount);
                // �T�Z��n����i�O�݁j
                l_orderActionParams.setFEstimatedPrice(l_dblNetAmountFc);
                // �s�ꂩ��m�F�ς݂̊T�Z��n���
                l_orderActionParams.setConfirmedEstimatedPrice(l_dblNetAmount);
                // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
                l_orderActionParams.setConfirmedFEstimatedPrice(l_dblNetAmountFc);
                
                //1.2.3 doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)                
                l_queryProcessor.doUpdateQuery(l_orderActionParams);
            }
            catch (DataException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 1.2.4. return
            return;
        }

        // 1.3. get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = l_orderUnit.getSubAccount();

        // 1.4. getProduct( )
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnit.getProduct();

        // 1.5. get�s��( )
        WEB3GentradeMarket l_market = l_orderUnit.getMarket();

        // 1.6. getQuantity( )
        double l_dblQuantity = l_orderUnitRow.getQuantity();
        log.debug("�������� = " + l_dblQuantity);

        //getConfirmedQuantity( )
        double l_dblConfirmedQuantity = l_orderUnitRow.getConfirmedQuantity();

        //getConfirmedOrderPrice( )
        double l_dblConfirmedOrderPrice = l_orderUnitRow.getConfirmedOrderPrice();

        // 1.7. getExecutedQuantity( )
        double l_dblExecQuantity = l_orderUnitRow.getExecutedQuantity();
        log.debug("��萔�� = " + l_dblExecQuantity);

        // 1.8. get�ʉ�( )
        WEB3GentradeCurrency l_genCurrency = l_orderUnit.getCurrency();

        // 1.9. is���t( )
        boolean l_blnIsBuy = l_orderUnit.isBuy();
        log.debug("is���t = " + l_blnIsBuy);

        // get�K�p�בփ��[�g(�O�����������P��)
        // �g�����U�N�V�������擾����B
        // �����P�ʁF�@@�����P��
        WEB3FeqFinTransactionManager l_finTransactionMgr =
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        Double l_fxRate = l_finTransactionMgr.getFxRate(l_orderUnit);

        // 1.10. get�בփ��[�g(boolean, boolean, double)
        // [get�בփ��[�g()�Ɏw�肷�����] 
        //  is���t�F�@@is���t() 
        //  is���v�Z�F�@@false 
        //  ���͈בփ��[�g�F�@@get�K�p�בփ��[�g���擾�����K�p�בփ��[�g
        double l_dbReplaceRecord = l_fxRate.doubleValue();
        double l_dblRxRate = l_genCurrency.getExchangeRate(l_blnIsBuy, false, l_dbReplaceRecord);
        log.debug("�בփ��[�g = " + l_dblRxRate);

        // 1.11. get�v�Z�p�����P��(�O�������������, ���X, String, double, double, boolean)
        // [����]  
        //  ��������F �����P��.getProduct().get�������()�̖߂�l  
        //  ���X�F �⏕����.get����X()�̖߂�l 
        //  �����P���敪�F  
        // �@@�i�����P��.is�w�l() == true�j�̏ꍇ�A�h�w�l�h 
        // �@@�ȊO�A�h���s�h 
        //  �����P���F 
        //  �@@�����P��.getQuantity( ) - �����P��.getExecutedQuantity( ) < 0�@@�̏ꍇ�A 
        //  �@@�@@�@@�����P�ʁD�s�ꂩ��m�F�ς݂̒����P��
        //  �@@�ȊO�̏ꍇ�A 
        //  �@@�@@�@@�����P�ʁD�����P��
        //  �����P���F �����P��.�iW�w�l�j�����w�l 
        //  is���t�F is���t() 
        double l_dblWPrice = 0.0D;
        if ((l_dblQuantity - l_dblExecQuantity) < 0)
        {
            l_dblWPrice = l_dblConfirmedOrderPrice;
        }
        else
        {
            l_dblWPrice = l_orderUnitRow.getPrice();
        }
        log.debug("�����P��.�����P�� = " + l_dblWPrice);
        double l_dblCalcOrdPrice = this.getUnitPrice(
            l_product.getFeqTradedProduct(),
            l_subAccount.getWeb3GenBranch(),
            l_orderUnit.isLimitOrder()
                ? WEB3OrderPriceDivDef.LIMIT_PRICE
                : WEB3OrderPriceDivDef.MARKET_PRICE,
            l_dblWPrice,
            l_orderUnitRow.getWLimitPrice(),
            l_blnIsBuy);
        log.debug("�v�Z�p�����P�� = " + l_dblCalcOrdPrice);

        // 1.12. calc�������(double, double)
        // [calc�������()�Ɏw�肷�����] 
        //  �����F�@@ 
        //  �@@�����P��.getQuantity( ) - �����P��.getExecutedQuantity( ) < 0�@@�̏ꍇ�A 
        //  �@@�@@�@@�����P��.getConfirmedQuantity( ) - �����P��.getExecutedQuantity( ) 
        //  �@@�ȊO�̏ꍇ�A 
        //  �@@�@@�@@�����P��.getQuantity( ) - �����P��.getExecutedQuantity( )
        //  �P���F�@@get�v�Z�p�����P��() 
        double l_dblWQuantity = 0.0D;
        if ((l_dblQuantity - l_dblExecQuantity) < 0)
        {
            l_dblWQuantity = l_dblConfirmedQuantity - l_dblExecQuantity;
        }
        else
        {
            l_dblWQuantity = l_dblQuantity - l_dblExecQuantity;
        }
        log.debug("���� = " + l_dblQuantity);
        log.debug("���ϐ��� = " + l_dblExecQuantity);
        WEB3FeqBizLogicProvider l_feqBizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblUnExedAmount = l_feqBizLogicProvider.calcExecutionAmount(
            l_dblWQuantity,
            l_dblCalcOrdPrice);
        BigDecimal l_bdUnExedAmount = new BigDecimal(l_dblUnExedAmount);
        int l_intScale = l_genCurrency.getScale();
        l_bdUnExedAmount = l_bdUnExedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
        log.debug("����蕪�̔������ = " + l_bdUnExedAmount.doubleValue());

        // 1.13. getExecutions( )
        OrderExecution[] l_ordExecutions = l_orderUnit.getExecutions();
        BigDecimal l_bdExedAmountSum = new BigDecimal("0");
        if (l_ordExecutions != null)
        {
            // 1.14. (*) ���igetExecution()�̖߂�l�j�̐���LOOP����
            for (int i = 0; i < l_ordExecutions.length; i++)
            {
                BigDecimal l_bdExedAmount = new BigDecimal("0");
                WEB3FeqOrderExecution l_orderExec = (WEB3FeqOrderExecution)l_ordExecutions[i];
                FeqOrderExecutionRow l_orderExecRow = (FeqOrderExecutionRow)l_orderExec.getDataSourceObject();
    
                // 1.14.1. is�폜��( )
                boolean l_blnIsDeleted = l_orderExec.isDeleted();
    
                // 1.14.2. (*) �폜�ς݂łȂ��ꍇ�iis�폜��() == false�j
                if (!l_blnIsDeleted)
                {
                    // 1.14.2.1. getExecutionQuantity( )
                    double l_dblExecutionQuantity = l_orderExecRow.getExecQuantity();
                    log.debug("���.��萔�� = " + l_dblExecutionQuantity);
    
                    // 1.14.2.2. getExecutionPrice( )
                    double l_dblExecPrice = l_orderExecRow.getExecPrice();
                    log.debug("���.���P�� = " + l_dblExecPrice);
    
                    // 1.14.2.3. calc�������(double, double)
                    // [calc�������()�Ɏw�肷�����] 
                    //  �����F�@@getExecutionQuantity() 
                    //  �P���F�@@getExecutionPrice() 
                    double l_dblExedAmount = l_feqBizLogicProvider.calcExecutionAmount(
                        l_dblExecutionQuantity,
                        l_dblExecPrice);
                    l_bdExedAmount = new BigDecimal(l_dblExedAmount);
                    l_bdExedAmount = l_bdExedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
                }
                log.debug("����� = " + l_bdExedAmount.doubleValue());
                l_bdExedAmountSum = l_bdExedAmountSum.add(l_bdExedAmount);
            }
        }
        log.debug("���ς̔������ = " + l_bdExedAmountSum.doubleValue());

        // 1.15. calc�O���������z(�⏕����, �O����������, �s��, Date, double, double,
        //           boolean, boolean, boolean, String)
        // [calc�O���������z()�Ɏw�肷�����] 
        //  �⏕�����F�@@get�⏕����() 
        //  �O�����������F�@@getProduct() 
        //  �s��F�@@get�s��() 
        //  ����F�@@��� 
        //  �����F�@@�p�����[�^�D����
        //  ��������F�@@����蕪�̔�������{���ς̔���������v�l�iSUM�j 
        //  �בփ��[�g�F�@@get�K�p�בփ��[�g���擾�����K�p�בփ��[�g 
        //  is���t�F�@@is���t() 
        //  is���v�Z�F�@@false 
        //  is�w�l�F�@@�����P��.is�w�l()�̖߂�l 
        //  �����`���l���F�@@�����P��.���񒍕��̒����`���l��
        Date l_datBaseDate = WEB3DateUtility.getDate(l_orderUnit.getBizDate(), "yyyyMMdd");
        WEB3FeqAmountCalcResult l_result = l_feqBizLogicProvider.calcFeqAmount(
            l_subAccount,
            l_product,
            l_market,
            l_datBaseDate,
            l_ExecDate,
            l_bdUnExedAmount.add(l_bdExedAmountSum).doubleValue(),
            l_dblRxRate,
            l_blnIsBuy,
            false,
            l_orderUnit.isLimitOrder(),
            l_orderUnitRow.getOrderChanel());

        // get�v�Z�p�����P��(�O�������������, ���X, String, double, double, boolean)
        // [get�v�Z�p�����P��()�Ɏw�肷�����]
        // �@@������� : �����P��.getProduct( ).get�������( )
        // �@@���X : �⏕����.get����X( )
        // �@@�����P���敪 : 
        // �@@�@@�i�����P��.is�w�l() == true�j�̏ꍇ�A�h�w�l�h
        // �@@�@@�ȊO�A�h���s�h
        // �@@�����P�� : �����P��.�s�ꂩ��m�F�ς݂̒����P��
        // �@@�����P�� : �����P��.�iW�w�l�j�����w�l
        // �@@is���t : �����P��.is���t( )
        log.debug("�����P��.�s�ꂩ��m�F�ς݂̒����P�� = " + l_dblConfirmedOrderPrice);
        double l_dblCalcConfirmOrdPrice = this.getUnitPrice(
                l_product.getFeqTradedProduct(), 
                l_subAccount.getWeb3GenBranch(), 
                l_orderUnit.isLimitOrder() 
                    ? WEB3OrderPriceDivDef.LIMIT_PRICE 
                    : WEB3OrderPriceDivDef.MARKET_PRICE, 
                l_dblConfirmedOrderPrice, 
                l_orderUnitRow.getWLimitPrice(), 
                l_blnIsBuy);
        log.debug("�v�Z�p�����P���i�s�ꂩ��m�F�ς݁j = " + l_dblCalcConfirmOrdPrice);
        
        // calc�������(double, double)
        // [calc�������()�Ɏw�肷�����]
        // �@@���� : �����P��.getConfirmedQuantity( ) - �����P��.getExecutedQuantity( ) 
        // �@@�P�� : �v�Z�p�����P���i�s�ꂩ��m�F�ς݁j
        double l_dblWConfirmedQuantity = l_dblConfirmedQuantity - l_dblExecQuantity;
        log.debug("�s�ꂩ��m�F�ς݂̐��� = " + l_dblConfirmedQuantity);
        log.debug("���ϐ��� = " + l_dblExecQuantity);
        double l_dblConfimedUnExedAmount = l_feqBizLogicProvider.calcExecutionAmount(
                l_dblWConfirmedQuantity, 
                l_dblCalcConfirmOrdPrice);
        BigDecimal l_bdConfimedUnExedAmount = new BigDecimal(l_dblConfimedUnExedAmount);
        l_bdConfimedUnExedAmount = l_bdConfimedUnExedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
        log.debug("����蕪�̔�������i�s�ꂩ��m�F�ς݁j = " + l_bdConfimedUnExedAmount.doubleValue());
        
        // calc�O���������z(�⏕����, �O����������, �s��, Date, Date, double, double, boolean, boolean, boolean, String)(
        // [calc�O���������z()�Ɏw�肷�����]
        // �@@�⏕���� : get�⏕����( )
        // �@@�O���������� : getProduct( )
        // �@@�s�� : get�s��( )
        // �@@��� : ���
        // �@@���� : �p�����[�^�D����
        // �@@��������i�O�݁j : ����蕪�̔�������i�s�ꂩ��m�F�ς݁j�{���ς̔���������v�l�iSUM�j
        // �@@�בփ��[�g : get�K�p�בփ��[�g���擾�����K�p�בփ��[�g 
        // �@@is���t : is���t( )
        // �@@is���v�Z : false
        // �@@is�w�l : �����P��.is�w�l( )
        // �@@�����`���l�� : �����P��.���񒍕��̒����`���l��
        WEB3FeqAmountCalcResult l_confirmedResult = l_feqBizLogicProvider.calcFeqAmount(
            l_subAccount, 
            l_product, 
            l_market, 
            l_datBaseDate, 
            l_ExecDate, 
            l_bdConfimedUnExedAmount.add(l_bdExedAmountSum).doubleValue(), 
            l_dblRxRate, 
            l_blnIsBuy, 
            false, 
            l_orderUnit.isLimitOrder(), 
            l_orderUnitRow.getOrderChanel());
        
        // 1.16. doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)
        // get��n���
        l_dblNetAmount = l_result.getNetAmount();
        // get��n����i�O�݁j
        l_dblNetAmountFc = l_result.getNetAmountFc();
        // get��n����i�s�ꂩ��m�F�ς݁j
        l_dblConfirmedNetAmount = l_confirmedResult.getNetAmount();
        // get��n����i�O�݁j�i�s�ꂩ��m�F�ς݁j
        l_dblConfirmedNetAmountFc = l_confirmedResult.getNetAmountFc();

        FeqOrderUnitParams l_orderUnitParams = new FeqOrderUnitParams(l_orderUnitRow);
        // �T�Z��n���
        l_orderUnitParams.setEstimatedPrice(l_dblNetAmount);
        // �T�Z��n����i�O�݁j
        l_orderUnitParams.setFEstimatedPrice(l_dblNetAmountFc);
        // �s�ꂩ��m�F�ς݂̊T�Z��n���
        l_orderUnitParams.setConfirmedEstimatedPrice(l_dblConfirmedNetAmount);
        // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
        l_orderUnitParams.setConfirmedFEstimatedPrice(l_dblConfirmedNetAmountFc);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);

            // ���������i�����������ŏI�ʔԂ̗����j���擾����B
            // ���������ŏI�ʔԂ��擾����B
            // ��������Row���擾����B
            String l_strWhere = " order_unit_id = ?  and order_action_serial_no = ? ";
            Object[] l_objVars = {
                new Long(l_orderUnit.getOrderUnitId()),
                new Integer(l_orderUnitRow.getLastOrderActionSerialNo())};
            List l_lisOrderActions = l_queryProcessor.doFindAllQuery(
                FeqOrderActionRow.TYPE,
                l_strWhere,
                l_objVars);

            if (l_lisOrderActions == null || l_lisOrderActions.isEmpty())
            {
                log.debug("�Y�����钍���P��ID�A���������ŏI�ʔԃf�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_lisOrderActions.size() > 1)
            {
                log.debug("�Y�����钍���P��ID�A���������ŏI�ʔԃf�[�^�͕s��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            FeqOrderActionRow l_orderActionRow = (FeqOrderActionRow) l_lisOrderActions.get(0);
            FeqOrderActionParams l_orderActionParams = new FeqOrderActionParams(l_orderActionRow);
            // �T�Z��n���
            l_orderActionParams.setEstimatedPrice(l_dblNetAmount);
            // �T�Z��n����i�O�݁j
            l_orderActionParams.setFEstimatedPrice(l_dblNetAmountFc);
            // �s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderActionParams.setConfirmedEstimatedPrice(l_dblConfirmedNetAmount);
            // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
            l_orderActionParams.setConfirmedFEstimatedPrice(l_dblConfirmedNetAmountFc);
            l_queryProcessor.doUpdateQuery(l_orderActionParams);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���������\���)<BR>
     * �������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate���������\���()��<BR>
     * �Ϗ��ideligate�j����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4295FF7A02BE
     */
    public void validateOrderChangePossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderChangePossibleStatus(long)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateOrderChangePossibleStatus(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��������\���)<BR>
     * ������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate��������\���()��<BR>
     * �Ϗ��ideligate�j����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A7A8850091
     */
    public void validateOrderCancelPossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderCancelPossibleStatus(long)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateOrderCancelPossibleStatus(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�o����܂Œ����P��)<BR>
     * �����̒����P�ʂ��o����܂Œ������ǂ����𔻒肷��B<BR>
     * <BR>
     * ����.�����P��.���񒍕��̒����P��ID != null �̏ꍇ�́Atrue<BR>
     * ����.�����P��.���񒍕��̒����P��ID == null �̏ꍇ�́Afalse<BR>
     * <BR>
     * ��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     *
     * @@return boolean
     * @@roseuid 42961128006C
     */
    public boolean isCarriedOrderUnit(FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "isCarriedOrderUnit(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsNull = l_row.getFirstOrderUnitIdIsNull();

        log.exiting(STR_METHOD_NAME);
        return (!l_blnIsNull) ? true : false;
    }

    /**
     * (get���񒍕��̒����P��)<BR>
     * ���񒍕��̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j����.�����P��.���񒍕��̒����P��ID == �inull or 0�j �̏ꍇ<BR>
     * <BR>
     *    ����.�����P�ʂ�ԋp����B<BR>
     * <BR>
     * �Q�j����.�����P��.���񒍕��̒����P��ID != �inull or 0�j �̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j�O�����������}�l�[�W��.getOrderUnit()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �����P��ID�F ����.�����P��.���񒍕��̒����P��ID<BR>
     * <BR>
     * �Q�|�Q�j�擾���������P�ʂ�ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     *
     * @@return FeqOrderUnit
     * @@roseuid 429612C50176
     */
    public FeqOrderUnit getFirstOrderUnit(FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "getFirstOrderUnit(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngFirstOrderUnitId = l_row.getFirstOrderUnitId();
        boolean l_blnIsNull = l_row.getFirstOrderUnitIdIsNull();
        // �P�j����.�����P��.���񒍕��̒����P��ID == �inull or 0�j �̏ꍇ
        if (l_blnIsNull || l_lngFirstOrderUnitId == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_orderUnit;
        }
        // �Q�j����.�����P��.���񒍕��̒����P��ID != �inull or 0�j �̏ꍇ
        else
        {
            FeqOrderUnit l_ordUnit = null;
            try
            {
                // �Q�|�P�j�O�����������}�l�[�W��.getOrderUnit()���R�[������B
                l_ordUnit = (FeqOrderUnit)this.getOrderUnit(l_lngFirstOrderUnitId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_ordUnit == null)
            {
                log.debug(STR_METHOD_NAME + "�Y�������P�ʂ����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            log.exiting(STR_METHOD_NAME);
            // �Q�|�Q�j�擾���������P�ʂ�ԋp����B
            return l_ordUnit;
        }
    }

    /**
     * (get���s����)<BR>
     * �����̎��s�������AWEB3�̎��s�������擾���ԋp����B<BR>
     * <BR>
     * �P�j�����̎��s�����iSONAR�j���h�������h �̏ꍇ<BR>
     * <BR>
     *    FeqExecutionConditionType.NONE�i�����Ȃ��j��Ԃ��B<BR>
     * <BR>
     * �Q�j�����̎��s�����iSONAR�j���h��t�h �̏ꍇ<BR>
     * <BR>
     *    FeqExecutionConditionType.AT_MARKET_OPEN�i���j��Ԃ��B<BR>
     * <BR>
     * �R�j�����̎��s�����iSONAR�j���h�����h �̏ꍇ<BR>
     * <BR>
     *    FeqExecutionConditionType.AT_MARKET_CLOSE�i�����j��Ԃ��B<BR>
     * <BR>
     * �S�j�����̎��s�����iSONAR�j���h�o�����Έ���(�s��)�h �̏ꍇ<BR>
     * <BR>
     *    FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * �i�s�o���������s�j��Ԃ��B<BR>
     * <BR>
     * �T�j�����̎��s��������L�ȊO�̏ꍇ<BR>
     * <BR>
     *    ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00019<BR>
     * @@param l_strExecutionConditionType - (���s����)<BR>
     * SONAR�̎��s����<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType
     * @@throws WEB3BaseException
     * @@roseuid 4296E5D801E8
     */
    public FeqExecutionConditionType getExecutionCondition(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionCondition(String)";
        log.entering(STR_METHOD_NAME);

        FeqExecutionConditionType l_feqExecCondTypeReturn;
        // �P�j�����̎��s�����iSONAR�j���h�������h �̏ꍇ
        if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.NONE;
        }
        // �Q�j�����̎��s�����iSONAR�j���h��t�h �̏ꍇ
        else if(WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.AT_MARKET_OPEN;
        }
        // �R�j�����̎��s�����iSONAR�j���h�����h �̏ꍇ
        else if(WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.AT_MARKET_CLOSE;
        }
        // �S�j�����̎��s�����iSONAR�j���h�o�����Έ���(�s��)�h �̏ꍇ
        else if(WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(l_strExecutionConditionType))
        {
            l_feqExecCondTypeReturn = FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        // �T�j�����̎��s��������L�ȊO�̏ꍇ
        else
        {
            log.debug(STR_METHOD_NAME + "�����̎��s��������L�ȊO�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00019,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug(STR_METHOD_NAME + ".get���s���� : " + l_feqExecCondTypeReturn.stringValue());
        log.exiting(STR_METHOD_NAME);
        return l_feqExecCondTypeReturn;
    }

    /**
     * (get���s�����iSONAR�j)<BR>
     * �����̎��s�������ASONAR�̎��s�������擾���ԋp����B<BR>
     * <BR>
     * �P�j�����̎��s������FeqExecutionConditionType.NONE�i�����Ȃ��j<BR>
     *   �̏ꍇ<BR>
     * <BR>
     *    �h�������h��Ԃ��B<BR>
     * <BR>
     * �Q�j�����̎��s������FeqExecutionConditionType.AT_MARKET_OPEN<BR>
     *  �i���j �̏ꍇ<BR>
     * <BR>
     *    �h��t�h��Ԃ��B<BR>
     * <BR>
     * �R�j�����̎��s������FeqExecutionConditionType.AT_MARKET_CLOSE<BR>
     *  �i�����j �̏ꍇ
     * <BR>
     *    �h�����h��Ԃ��B<BR>
     * <BR>
     * �S�j�����̎��s������EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     *  �i�s�o���������s�j �̏ꍇ<BR>
     * <BR>
     *    �h�o�����Έ���(�s��)�h��Ԃ��B<BR>
     * <BR>
     * �T�j�����̎��s��������L�ȊO�̏ꍇ<BR>
     * <BR>
     *    ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00019<BR>
     * @@param l_strExecutionConditionType - (���s����)<BR>
     * WEB3�̎��s����<BR>
     *
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public String getExecutionConditionTypeSonar(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionTypeSonar(String)";
        log.entering(STR_METHOD_NAME);

        String l_strReturn;
        // �P�j�����̎��s������FeqExecutionConditionType.NONE�i�����Ȃ��j�̏ꍇ
        if (new Integer(
            FeqExecutionConditionType.NONE.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
        }
        // �Q�j�����̎��s������FeqExecutionConditionType.AT_MARKET_OPEN�i���j �̏ꍇ
        else if (new Integer(
            FeqExecutionConditionType.AT_MARKET_OPEN.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
        }
        // �R�j�����̎��s������FeqExecutionConditionType.AT_MARKET_CLOSE�i�����j �̏ꍇ
        else if (new Integer(
            FeqExecutionConditionType.AT_MARKET_CLOSE.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
        }
        // �S�j�����̎��s������EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED�i�s�o���������s�j �̏ꍇ
        else if (new Integer(
            FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.intValue()).toString().equals(
                l_strExecutionConditionType))
        {
            l_strReturn = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
        }
        // �T�j�����̎��s��������L�ȊO�̏ꍇ
        else
        {
            log.debug(STR_METHOD_NAME + "�����̎��s��������L�ȊO�̏ꍇ�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00019,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug(STR_METHOD_NAME + ".get���s�����iSONAR�j : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (is���e�ʒm�ϒ���)<BR>
     * �����ϒ����ł��邩�𔻒肷��B<BR>
     * �����̒����P�ʂ��ȉ��̏����ɓ��Ă͂܂�ꍇtrue�A<BR>
     * �ȊO��false��ԋp����B<BR>
     * <BR>
     * [�����ϒ����̏���]<BR>
     * �����P��.���s���� == �����P��.�s�ꂩ��m�F�ς̎��s����<BR>
     * �����P��.�w�l == �����P��.�s�ꂩ��m�F�ς̎w�l<BR>
     * �����P��.�������� == �����P��.�s�ꂩ��m�F�ς̐���<BR>
     * @@param l_orderUnitRow - (�����P�ʍs)<BR>
     * �����P�ʍs�I�u�W�F�N�g<BR>
     *
     * @@return boolean
     * @@roseuid 429D62EE009B
     */
    public boolean isNotifyEndOrder(FeqOrderUnitRow l_orderUnitRow)
    {
        final String STR_METHOD_NAME = "isNotifyEndOrder(FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitRow == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        boolean l_blnReturn =
            l_orderUnitRow.getExecutionConditionType().equals(
                l_orderUnitRow.getConfirmedExecConditionType()) &&
            l_orderUnitRow.getLimitPrice() == l_orderUnitRow.getConfirmedPrice() &&
            l_orderUnitRow.getQuantity() == l_orderUnitRow.getConfirmedQuantity();
        log.debug(STR_METHOD_NAME + ".is���e�ʒm�ϒ��� : " + l_blnReturn);

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * (validate�ڋq�����ʎ����~)<BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B<BR>
     * �i* �O�����������R���`�F�b�N.validate�ڋq�����ʎ����~()�ɈϏ�����B�j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     *
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     *
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429E879202DD
     */
    public void validateAccountProductTradedStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountProductTradedStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateAccountProductTradedStop(
            l_subAccount,
            l_lngProductId,
            l_orderType);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�戵�\�s��)<BR>
     * ��Е��X�̎戵�\�s�ꂩ���`�F�b�N����B<BR>
     * �i* �O�����������R���`�F�b�N.validate�戵�\�s��( )�ɈϏ�����B�j <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429EEFE001B1
     */
    public void validateHandlingPossibleMarket(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossibleMarket(String, String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateHandlingPossibleMarket(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strMarketCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����P�ʈꗗ)<BR>
     * �igetOrderUnits�̃I�[�o�[���[�h�j<BR>
     * �w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B<BR>
     * �@@�Q�|�P�j�@@�p�����[�^.��������������̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@�@@"account_id = ? and sub_account_id = ? and <BR>
     * �@@�@@�@@�@@�@@�@@�@@product_type = ?"��t������B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�p�����[�^.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@���p�����[�^.�⏕�����A�y�уp�����[�^.�����^�C�v���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �����P�ʃI�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(�O�����������P��Row.TYPE<BR>
     *                     �Q�|�P�j�̌�������������,<BR>
     *                     �p�����[�^.�\�[�g����,<BR>
     *                     null,<BR>
     *                     �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �S�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �T�j�@@�R�j�̖߂�l�̗v�f(=FeqOrderUnitRow)�����ȉ��̏�����Loop����B<BR>
     * �@@�@@�@@�@@�@@�O�����������}�l�[�W��.toOrderUnit((*1)�O�����������P��Row)<BR>
     * ���\�b�h���R�[������B<BR>
     * �@@�@@�@@�A�@@�@@�̖߂�l��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@(*1)�O�����������P��Row�E�E�E�����Ώۂ̗v�f��<BR>
     * FeqOrderUnitRow�ɃL���X�g����B<BR>
     * <BR>
     * �U�j�@@ArrayList��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * (ProductTypeEnum�ɂĒ�`)<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     * @@roseuid 42A3D70B00F6
     */
    public ArrayList getOrderUnits(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strQueryString,
        String[] l_strQueryContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnits(SubAccount, "
            + "ProductTypeEnum, String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        // �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B
        ArrayList l_alisReturn = new ArrayList();

        // �Q�j�@@����������ǉ�����B
        //�@@�Q�|�P�j�@@�p�����[�^.��������������̐擪�ɁA
        //�@@�@@�@@�@@�@@�@@"account_id = ? and sub_account_id = ? and
        //�@@�@@�@@�@@�@@�@@�@@product_type = ?"��t������B
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and sub_account_id = ? ");
        l_sbWhere.append(" and product_type = ? ");
        if (l_strQueryString != null)
        {
            l_sbWhere.append(l_strQueryString);
        }

        //�@@�Q�|�Q�j�@@�p�����[�^.���������f�[�^�R���e�i�̐擪�ɁA
        // �@@�@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B
        // �@@�@@�@@�@@�@@�@@���p�����[�^.�⏕�����A�y�уp�����[�^.�����^�C�v���ݒ肷��B
        ArrayList l_alisVars = new ArrayList();
        l_alisVars.add(new Long(l_subAccount.getMainAccount().getAccountId()));
        l_alisVars.add(new Long(l_subAccount.getSubAccountId()));
        l_alisVars.add(l_productType);
        if (l_strQueryContainers != null)
        {
            for (int i = 0; i < l_strQueryContainers.length; i++)
            {
                l_alisVars.add(l_strQueryContainers[i]);
            }
        }

        List l_lisRecords = null;
        try
        {
            //�R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����P�ʃI�u�W�F�N�g��List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_alisVars.size()];
            l_alisVars.toArray(l_objVars);
            //�@@doFindAllQuery(�O�����������P��Row.TYPE
            //                  �Q�|�P�j�̌�������������,
            //                  �p�����[�^.�\�[�g����,
            //                  null,
            //                  �Q�|�Q�j�̌��������f�[�^�R���e�i)
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strSortCond,
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�@@���Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�T�j�@@�R�j�̖߂�l�̗v�f(=FeqOrderUnitRow)�����ȉ��̏�����Loop����B
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            //�@@�@@�@@�O�����������}�l�[�W��.toOrderUnit((*1)�O�����������P��Row)���\�b�h���R�[������B
            //�@@(*1)�O�����������P��Row�E�E�E�����Ώۂ̗v�f��FeqOrderUnitRow�ɃL���X�g����B
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)this.toOrderUnit(l_orderUnitRow);
            //�@@�A�@@�@@�̖߂�l��ArrayList�ɒǉ�����B
            l_alisReturn.add(l_orderUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return l_alisReturn;
    }

    /**
     * (remove�J�z�������P��)<BR>
     * �����̒����P�ʃI�u�W�F�N�g�̃��X�g����A<BR>
     * �J�z���̒����P�ʃI�u�W�F�N�g���������A<BR>
     * ������̃��X�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�@@�����Ώۂ̔���<BR>
     * <BR>
     * �@@�ȉ��A�p�����[�^.�����P�ʈꗗ�̗v�f������Loop�����B<BR>
     * <BR>
     * �@@[�O�����������}�l�[�W��.is�o����܂Œ����P��<BR>
     * �@@(�����Ώۂ̒����P��) == false�̏ꍇ]<BR>
     * �@@(�������蒍���̏ꍇ)<BR>
     * �@@�@@�@@���X�g�ɂ��̂܂܎c���B<BR>
     * <BR>
     * �@@[�O�����������}�l�[�W��.is�o����܂Œ����P��<BR>
     * �@@(�����Ώۂ̒����P��) == true�̏ꍇ]<BR>
     * �@@(�o����܂Œ����̏ꍇ)<BR>
     * �@@�@@[���񒍕��̏ꍇ]<BR>
     * �@@�@@(�����Ώۂ̒����P��.���񒍕��̒����P��ID == 0�̏ꍇ)<BR>
     * �@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̒����P��.�����P��ID ==<BR>
     * �@@�@@�@@�@@�@@�@@���X�g���̒����P��.���񒍕��̒����P��ID<BR>
     * <BR>
     * �@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A���g�������ΏۂƂ���B<BR>
     * �@@�@@�@@���J�z��̒��������݂���ׁB<BR>
     *
     * �@@�@@[�J�z�ϒ����̏ꍇ]<BR>
     * �@@�@@(�����Ώۂ̒����P��.���񒍕��̒����P��ID != 0�̏ꍇ)<BR>
     * �@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����Ώۂ̒����P��.���񒍕��̒����P��ID ==<BR>
     * �@@�@@�@@�@@�@@�@@���X�g���̒����P��.���񒍕��̒����P��ID<BR>
     * <BR>
     * �@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A�쐬�����r���A<BR>
     * �@@�@@�@@�ŐV�̒����P�ʈȊO��S�ď����ΏۂƂ���B<BR>
     * �@@�@@�@@���ŐV�̌J�z�����݂̂�\������ׁB<BR>
     * <BR>
     * �R�j�@@���X�g����̏����ΏۂƔ��肳�ꂽ�J�z���̒����P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@�����P�ʈꗗ����S�ď�������B<BR>
     * �@@�@@�@@���p�����[�^.�����P�ʈꗗ�̕��я��͌ڋq�w��<BR>
     * �@@�@@�@@�̃\�[�g�����ɂ�邽�߁A<BR>
     * �@@�@@�@@�@@�����͍Ō�ɓZ�߂čs���K�v������B<BR>
     * <BR>
     * �S�j�@@�����ς̒����P�ʈꗗ��ԋp����B<BR>
     * �@@�������P�ʈꗗ�̗v�f����0�ɂȂ����ꍇ��null��ԋp����B<BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * �O�����������P�ʃI�u�W�F�N�g�̔z��<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@roseuid 42A3DB210106
     */
    public WEB3FeqOrderUnit[] removeCarryOverOriginOrder(WEB3FeqOrderUnit[] l_orderUnits)
    {
        final String STR_METHOD_NAME = "removeCarryOverOriginOrder(WEB3FeqOrderUnit[])";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�Anull��ԋp���ďI������B
        if (l_orderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        int l_intCarryoverOrderUnitCount = 0;
        // is�o����܂Œ����P�ʂ̔���
        boolean[] l_blnIsCarriedOrderUnit = new boolean[l_orderUnits.length];

        // �Q�j�@@�����Ώۂ̔���
        // �ȉ��A�p�����[�^.�����P�ʈꗗ�̗v�f������Loop�����B
        for (int i = 0; i < l_orderUnits.length; i++)
        {
            l_blnIsCarriedOrderUnit[i] = true;

            // [�O�����������}�l�[�W��.is�o����܂Œ����P��(�����Ώۂ̒����P��) == false�̏ꍇ]
            //  (�������蒍���̏ꍇ)
            if (!this.isCarriedOrderUnit(l_orderUnits[i]))
            {
                continue;
            }

            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnits[i].getDataSourceObject();
            // [�O�����������}�l�[�W��.is�o����܂Œ����P��(�����Ώۂ̒����P��) == true�̏ꍇ]
            //   (�o����܂Œ����̏ꍇ)
            // [���񒍕��̏ꍇ]
            if (l_orderUnitRow.getFirstOrderUnitId() == 0L)
            {
                for (int j = 0; j < l_orderUnits.length; j++)
                {
                    // �����Ώۂ̒����P��.�����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID
                    FeqOrderUnitRow l_orderUnitRowj =
                        (FeqOrderUnitRow)l_orderUnits[j].getDataSourceObject();
                    if (l_orderUnitRow.getOrderUnitId() == l_orderUnitRowj.getFirstOrderUnitId())
                    {
                        l_blnIsCarriedOrderUnit[i] = false;
                        l_intCarryoverOrderUnitCount++;
                        break;
                    }
                }
            }
            // [�J�z�ϒ����̏ꍇ]
            else
            {
                for (int j = 0; j < l_orderUnits.length; j++)
                {
                    // �����Ώۂ̒����P��.���񒍕��̒����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID
                    FeqOrderUnitRow l_orderUnitRowj = (FeqOrderUnitRow)l_orderUnits[j].getDataSourceObject();

                    if (l_orderUnitRow.getFirstOrderUnitId() == l_orderUnitRowj.getFirstOrderUnitId())
                    {
                        // �쐬�����r���A�ŐV�̒����P�ʈȊO��S�ď����ΏۂƂ���B
                        if (WEB3DateUtility.compareToSecond(
                            l_orderUnitRowj.getCreatedTimestamp(),
                            l_orderUnitRow.getCreatedTimestamp()) > 0)
                        {
                            l_blnIsCarriedOrderUnit[i] = false;
                            l_intCarryoverOrderUnitCount++;
                            break;
                        }
                    }
                }
            }
        }

        //�@@�������P�ʈꗗ�̗v�f����0�ɂȂ����ꍇ��null��ԋp����B
        WEB3FeqOrderUnit[] l_orderUnitReturn = null;
        // �R�j�@@���X�g����̏����ΏۂƔ��肳�ꂽ�J�z���̒����P�ʃI�u�W�F�N�g���A�����P�ʈꗗ����S�ď�������B
        int l_intLength = l_orderUnits.length - l_intCarryoverOrderUnitCount;
        if (l_intLength > 0)
        {
            l_orderUnitReturn =
                new WEB3FeqOrderUnit[l_intLength];
            int j = 0;
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                if (l_blnIsCarriedOrderUnit[i])
                {
                    l_orderUnitReturn[j] = l_orderUnits[i];
                    j++;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitReturn;
    }

    /**
     * (is�J�z�����P��)<BR>
     * �����J�z�œo�^���ꂽ�������ǂ����𔻒肷��B<BR>
     * �u�J�z�����v�̏ꍇ��true���A�u�J�z�����v�ł͂Ȃ��ꍇ��false���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.���񒍕��̒����P��ID > 0�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�@@�@@�����̒����P��.���񒍕��̒����P��ID��<BR>
     * �@@�@@�@@�inull or 0�j�̏ꍇ�́Afalse��Ԃ��B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@roseuid 42A414E600A8
     */
    public boolean isCarryOverOrderUnit(WEB3FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "isCarryOverOrderUnit(WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        long l_lngFirstOrderUnitId = l_row.getFirstOrderUnitId();

        log.exiting(STR_METHOD_NAME);
        return (l_lngFirstOrderUnitId > 0) ? true : false;
    }

    /**
     * (get�o���I���Ώے����P��)<BR>
     * �o���I���ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ɂĊO�������P�ʃe�[�u������������B<BR>
     * �@@���������ɑΉ�����p�����[�^(����ID��،���ЃR�[�h�Ȃ�)��<BR>
     * �@@null�̏ꍇ�A���̏����͌����������珜�O����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID = �p�����[�^.����ID And<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�@@�s��ID = �p�����[�^.�s��R�[�h�ɊY������s��ID And<BR>
     * �@@�@@�����^�C�v = ProductTypeEnum.�O������ And<BR>
     * �@@�@@������ = �p�����[�^.������ And<BR>
     * �@@�@@(�����L����� = "�I�[�v��" or<BR>
     * �@@�@@�@@(�����L����� = "�N���[�Y" And ��萔�� != (null or 0))) And<BR>
     * �@@�@@�o���I���������� is null<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42B6596401F6
     */
    public WEB3FeqOrderUnit[] getOrderExecEndObjectOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode,
        String l_strMarketCode,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecEndObjectOrderUnit(long, String, String, Date)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@DB����
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisObjs = new ArrayList();
        if (l_lngAccountId != null)
        {
            l_sbWhere.append(" account_id = ? ");
            l_alisObjs.add(l_lngAccountId);
        }

        if (l_strInstitutionCode != null)
        {
            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and ");
            }
            l_sbWhere.append(" institution_code = ? ");
            l_alisObjs.add(l_strInstitutionCode);
        }

        if (l_strMarketCode != null)
        {
            if (l_strInstitutionCode == null)
            {
                log.debug(" �p�����[�^�l�،���ЃR�[�h��NULL ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�l�،���ЃR�[�h��NULL");
            }
            MarketRow l_marketRow = null;
            try
            {
                // �p�����[�^.�s��R�[�h�ɊY������s��ID�擾����
                l_marketRow = MarketDao.findRowByInstitutionCodeMarketCode(
                    l_strInstitutionCode, l_strMarketCode);
            }
            catch (DataFindException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_marketRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and ");
            }
            l_sbWhere.append(" market_id = ? ");
            long l_lngMarketId = l_marketRow.getMarketId();
            l_alisObjs.add(new Long(l_lngMarketId));
        }

        if (l_sbWhere.length() != 0)
        {
            l_sbWhere.append(" and ");
        }
        l_sbWhere.append(" product_type = ? ");
        l_alisObjs.add(ProductTypeEnum.FOREIGN_EQUITY);

        if (l_datBizDate != null)
        {
            l_sbWhere.append(" and biz_date = ? ");
            String l_datBizDates = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
            l_alisObjs.add(l_datBizDates);
        }

        l_sbWhere.append(" and (order_open_status = ? ");
        l_sbWhere.append(" or (order_open_status = ? ");
        l_sbWhere.append(" and (quantity != ? and quantity is not NULL))) ");
        l_sbWhere.append(" and exec_end_timestamp is NULL ");
        l_alisObjs.add(OrderOpenStatusEnum.OPEN);
        l_alisObjs.add(OrderOpenStatusEnum.CLOSED);
        l_alisObjs.add(new Integer(0));

        Object[] l_objVars = new Object[l_alisObjs.size()];
        l_alisObjs.toArray(l_objVars);
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@���Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // �Q�j�@@�������ʂ�ԋp����B
        int l_intCnt = l_lisRecords.size();
        WEB3FeqOrderUnit[] l_returns = new WEB3FeqOrderUnit[l_intCnt];
        ArrayList l_alisReturn = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)this.toOrderUnit(l_orderUnitRow);
            l_alisReturn.add(l_orderUnit);
        }
        l_alisReturn.toArray(l_returns);

        log.exiting(STR_METHOD_NAME);
        return l_returns;
    }

    /**
     * (get�J�z�Ώے����P��)<BR>
     * �o���I���ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ɂĊO�������P�ʃe�[�u������������B<BR>
     * �@@���������ɑΉ�����p�����[�^(����ID��،���ЃR�[�h�Ȃ�)��<BR>
     * �@@null�̏ꍇ�A���̏����͌����������珜�O����B<BR>
     * �@@���������ʂ́A�󒍓��� �����Ń\�[�g���邱�ƁB<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID = �p�����[�^.����ID And<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�@@�����^�C�v = ProductTypeEnum.�O������ And<BR>
     * �@@�@@������ = �Ɩ����t(*1) And<BR>
     * �@@�@@�����L����� = "�I�[�v��" And<BR>
     * �@@�@@�����������t > �Ɩ����t And<BR>
     * �@@�@@�o���I������ is not null<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42B8B0A30114
     */
    public WEB3FeqOrderUnit[] getCarryOverOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCarryOverOrderUnit(long, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@DB����
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_alisObjs = new ArrayList();
        if (l_lngAccountId != null)
        {
            l_sbWhere.append(" account_id = ? ");
            l_alisObjs.add(l_lngAccountId);
        }

        if (l_strInstitutionCode != null)
        {
            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and ");
            }
            l_sbWhere.append(" institution_code = ? ");
            l_alisObjs.add(l_strInstitutionCode);
        }
        if (l_sbWhere.length() != 0)
        {

            l_sbWhere.append(" and product_type = ? ");
        }
        else
        {
            l_sbWhere.append("product_type = ? ");
        }
        
        l_sbWhere.append(" and biz_date = ? ");
        l_sbWhere.append(" and order_open_status = ? ");
        l_sbWhere.append(" and expiration_date > ? ");
        l_sbWhere.append(" and exec_end_timestamp is not NULL ");
        
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        l_alisObjs.add(ProductTypeEnum.FOREIGN_EQUITY);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");
        l_alisObjs.add(l_strBizDate);
        l_alisObjs.add(OrderOpenStatusEnum.OPEN);
        l_alisObjs.add(l_datBizDate);

        Object[] l_objVars = new Object[l_alisObjs.size()];
        l_alisObjs.toArray(l_objVars);
        
        List l_lisRecords = null;
        
        //�������ʂ́A�󒍓��� �����Ń\�[�g���邱�ƁB
        String l_strOrderBy = "received_date_time";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objVars);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@���Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // �Q�j�@@�������ʂ�ԋp����B
        int l_intCnt = l_lisRecords.size();
        WEB3FeqOrderUnit[] l_returns = new WEB3FeqOrderUnit[l_intCnt];
        ArrayList l_alisReturn = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)toOrderUnit(l_orderUnitRow);
            l_alisReturn.add(l_orderUnit);
        }
        l_alisReturn.toArray(l_returns);

        log.exiting(STR_METHOD_NAME);
        return l_returns;
    }
    /**
     * (validate�����\�s�� )<BR>
     * �������\�Ȏs��ł��邩�ǂ������`�F�b�N����B <BR>
     * <BR>
     * �O�����������R���ʃ`�F�b�N.validate�����\�s��()�ɈϏ��ideligate�j����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * @@throws WEB3BaseException 
     */
    public void validateChangePossMarket(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangePossMarket(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3FeqTypeOrderManagerReusableValidations)
            WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        l_orderMgrResVal.validateChangePossMarket(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�����^����G���[�������s)<BR>
     * �Y�������������^������Ŋ��S�o���̏ꍇ�A<BR>
     * �z������������^����G���[�ɂ���B
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������j�����^����G���[�������s�v�Q�ƁB<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * @@throws WEB3BaseException
     */
    public void executeChangeCancelOrderRejected(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "executeChangeCancelOrderRejected(long)";
        log.entering(STR_METHOD_NAME);
        
        FeqOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = (FeqOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            if (l_orderUnit.isFullyExecuted())
            {
                long l_lngOrderId = l_orderUnit.getOrderId();
                MarketResponseMessage l_marketResponseMessage;
                if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
                    || OrderStatusEnum.MODIFYING.equals(l_orderStatus))
                {
                    l_marketResponseMessage =
                        new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
                }
                else
                {
                    l_marketResponseMessage =
                        new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
                }
                FeqOrderManagerPersistenceEventInterceptor l_currentInterceptor =
                    this.getThreadLocalPersistenceEventInterceptor();
                FeqOrderManagerPersistenceEventInterceptor l_updateInterceptor = 
                    new WEB3FeqOrderAcceptUpdateInterceptor(l_marketResponseMessage);
                this.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
                TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
                FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                    (FeqMarketResponseReceiverCallbackService)
                        l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
                l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
                this.setThreadLocalPersistenceEventInterceptor(l_currentInterceptor);
            }
        }
    }
      
    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager#toOrderUnit(com.fitechlabs.dbind.Row)
     */
    public OrderUnit toOrderUnit(Row l_row)
    {
        if(!(l_row instanceof FeqOrderUnitRow))
        {
            throw new IllegalArgumentException("Row is not of FeqOrderUnitRow type");
        }
        else
        {
            FeqOrderUnitRow l_our = (FeqOrderUnitRow)l_row;
            return new WEB3FeqOrderUnit(l_our);
        }
    }
    
    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager#toOrderAction(com.fitechlabs.dbind.Row)
     */
    public OrderAction toOrderAction(Row l_row)
    {
        if(!(l_row instanceof FeqOrderActionRow))
        {
            throw new IllegalArgumentException("Row is not of FeqOrderActionRow type");
        }
        else
        {
            FeqOrderActionRow l_our = (FeqOrderActionRow)l_row;
            return new WEB3FeqOrderAction(l_our);
        }
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager#toOrderExecution(com.fitechlabs.dbind.Row)
     */
    public OrderExecution toOrderExecution(Row l_row)
    {
        if(!(l_row instanceof FeqOrderExecutionRow))
        {
            throw new IllegalArgumentException("Row is not of FeqOrderExecutionRow type");
        }
        else
        {
            FeqOrderExecutionRow l_our = (FeqOrderExecutionRow)l_row;
            return new WEB3FeqOrderExecution(l_our);
        }
    }
    
    public Order getOrder(long l_lngOrderId)
        throws NotFoundException
    {
        try
        {
            FeqOrderRow l_orderRow = FeqOrderDao.findRowByPk(l_lngOrderId);
            if (l_orderRow == null)
            {
                throw new NotFoundException("FeqOrder not found with OrderId:" + l_lngOrderId);
            }
            return this.toOrder(l_orderRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrder not found with OrderId:" + l_lngOrderId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error while during getOrder for FeqOrder with orderId:" + l_lngOrderId;           
            throw new RuntimeSystemException(l_strMsg, l_dex);            
        }        
    }

    public OrderUnit getOrderUnit(long l_lngOrderUnitId)
        throws NotFoundException
    {
        try
        {
            FeqOrderUnitRow l_orderUnitRow = FeqOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            if (l_orderUnitRow == null)
            {
                throw new NotFoundException("FeqOrderUnit not found with OrderUnitId:" + l_lngOrderUnitId);
            }
            return this.toOrderUnit(l_orderUnitRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrderUnit not found with OrderUnitId:" + l_lngOrderUnitId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error during getOrderUnit for FeqOrderUnit with orderUnitId:" + l_lngOrderUnitId;           
            throw new RuntimeSystemException(l_strMsg, l_dex);            
        }
    }
    
    public OrderAction getOrderAction(long l_lngOrderActionId)
        throws NotFoundException
    {        
        try
        {
            FeqOrderActionRow l_orderActinRow = FeqOrderActionDao.findRowByPk(l_lngOrderActionId);
            if (l_orderActinRow == null)
            {
                throw new NotFoundException("FeqOrderAction not found with OrderActionId:" + l_lngOrderActionId);
            }
            return this.toOrderAction(l_orderActinRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrderAction not found with OrderActionId:" + l_lngOrderActionId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error while during getOrderAction for FeqOrderAction " +
                    "with orderActionId:" + l_lngOrderActionId;           
            throw new RuntimeException(l_strMsg, l_dex);            
        }
    }
    
    public OrderExecution getOrderExecution(long l_lngOrderExecutionId)
        throws NotFoundException
    {        
        try
        {
            FeqOrderExecutionRow l_orderExecutionRow = 
                FeqOrderExecutionDao.findRowByPk(l_lngOrderExecutionId);
            if (l_orderExecutionRow == null)
            {
                throw new NotFoundException("FeqOrderExecution not found " +
                    "with OrderExecutionId:" + l_lngOrderExecutionId);    
            }
            return this.toOrderExecution(l_orderExecutionRow);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException("FeqOrderExecution not found " +
                    "with OrderExecutionId:" + l_lngOrderExecutionId);    
        }
        catch (DataException l_dex)
        {
            String l_strMsg = "Error while during getOrderExecution for FeqOrderExecution " +
                    "with orderExecutionId:" + l_lngOrderExecutionId;         
            throw new RuntimeException(l_strMsg, l_dex);            
        }
    }
    
    /**
     * (�����F�؎���������s)<BR>
     * �Y���������V�K���������ς������͒����^����ς̏ꍇ�A<BR> 
     * �Y�������𒍕��F�؎��������B<BR>
     * <BR>
     * �V�[�P���X�}<BR> 
     * �u�i�O�������j�����F�؎���������s�v�Q�ƁB<BR>
     * @@param  - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     */
    public void executeOrderAcceptCancel(WEB3FeqOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "executeChangeCancelAcceptCancel(WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        // 1.�����F�؎���������s(�O�����������P��)
        // 1.1 getOrderStatus( )
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        
        //������������t�ς������͒����^����ς̏ꍇ�i������� == "������(�V�K����)"
        //���͒������ == "������(��������)"����"������(�������)"�j
        if (OrderStatusEnum.ORDERED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFIED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLED.equals(l_orderStatus))
        {
            MarketResponseMessage l_marketResponseMessage = null;
            long l_lngOrderId = l_orderUnit.getOrderId();

            //������������t�ς̏ꍇ
            //DefaultNewOrderSentMarketResponseMessage(arg0 : long)
            if (OrderStatusEnum.ORDERED.equals(l_orderStatus))
            {
                l_marketResponseMessage = 
                    new DefaultNewOrderSentMarketResponseMessage(l_lngOrderId);
            }

            // 1.2.1 (*) �����������ς̏ꍇ
            // 1.2.1.1 DefaultChangeOrderSentMarketResponseMessage(arg0 : long)
            else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
            {
                l_marketResponseMessage = 
                    new DefaultChangeOrderSentMarketResponseMessage(l_lngOrderId);
            }
            
            // 1.2.2 (*) ����������ς̏ꍇ
            // 1.2.2.1 DefaultCancelOrderSentMarketResponseMessage(arg0 : long)
            else
            {
                l_marketResponseMessage = 
                    new DefaultCancelOrderSentMarketResponseMessage(l_lngOrderId);
            }
            
            // 1.2.3 getThreadLocalPersistenceEventInterceptor( )
            FeqOrderManagerPersistenceEventInterceptor l_currentInterceptor =
                this.getThreadLocalPersistenceEventInterceptor();
            
            // 1.2.4 �O������������t�X�V�C�x���g�C���^�Z�v�^(MarketResponseMessage)
            FeqOrderManagerPersistenceEventInterceptor l_updateInterceptor = 
                new WEB3FeqOrderAcceptUpdateInterceptor(l_marketResponseMessage);
            
            // 1.2.5 setThreadLocalPersistenceEventInterceptor
            this.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            FeqMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (FeqMarketResponseReceiverCallbackService)
                    l_tradingModule.getMarketAdapter().getMarketResponseReceiverCallbackService();
            // 1.2.6 process(arg0 : NewOrderSentMarketResponseMessage)
            l_marketResponseReceiverCallbackService.process(l_marketResponseMessage);
            
            // 1.2.7 setThreadLocalPersistenceEventInterceptor(arg0 : FeqOrderManagerPersistenceEventInterceptor)
            this.setThreadLocalPersistenceEventInterceptor(l_currentInterceptor);
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    /**
     * (update���v�����z�i�~�j)<BR>
     * ���v�����z�i�~�j�̍Čv�Z���s�Ȃ��B<BR>
     * <BR>
     * �P�j�@@this.getOrderUnit( )���R�[�����A�����P�ʂ��擾����B<BR>
     * �@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��ID<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����( )���R�[�����A<BR>
     * �@@�@@�@@�P�j�Ŏ擾���������P�ʂɊY������g�����U�N�V�������擾����B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�O�����������P�ʁF�@@�P�j�Ŏ擾���������P��<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �R�j�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����( )�̖߂�l�̗v�f�������[�v���A<BR>
     * �@@�@@�@@�e�v�f���Ɉȉ����e�Ōv�Z���s���A�v�Z���ʂ̍��v�l���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@���@@�g�����U�N�V����.���P���@@�~�@@�g�����U�N�V����.��萔�ʁ@@�~�@@�g�����U�N�V����.�K�p�בփ��[�g<BR>
     * <BR>
     * �@@�@@�@@����L�̌v�Z���ʂ́A�����_�ȉ���ʉ�.�~�݊��Z�ۂߕ����ɏ]���Ċۂ߂��s���B<BR>
     * <BR>
     * �S�j�@@�R�j�Ŏ擾�������v�l�ŁA�����P�ʃe�[�u��.���v�����z�i�~�j���X�V����B<BR>
     * <BR>
     * @@param l_orderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateExecutedAmountYen(long l_orderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcExecutedAmountYen(long l_orderUnitId)";
        log.entering(STR_METHOD_NAME);

        try 
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY);

            // �P�j�@@this.getOrderUnit( )���R�[�����A�����P�ʂ��擾����B
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)this.getOrderUnit(l_orderUnitId);
            
            // �Q�j�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����( )���R�[�����A
            // �P�j�Ŏ擾���������P�ʂɊY������g�����U�N�V�������擾����B
            WEB3FeqFinTransactionManager l_finTransactionManager = 
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            
            List l_lisTransaction = 
                l_finTransactionManager.getTransactions(l_feqOrderUnit);

            if (l_lisTransaction == null || l_lisTransaction.isEmpty())
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // �R�j�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����( )�̖߂�l�̗v�f�������[�v���A
            // �e�v�f���Ɉȉ����e�Ōv�Z���s���A�v�Z���ʂ̍��v�l���v�Z����B
            WEB3FeqBizLogicProvider l_bizLogicProvider =
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_feqOrderUnit.getProduct();
            WEB3GentradeCurrency l_genCurrency = l_product.getCurrency();
            int l_intScale = l_genCurrency.getScale();
            String l_strChangeJpyRoundDiv = l_genCurrency.getChangeJpyRoundDiv();
            
            int l_intSize = l_lisTransaction.size();
            BigDecimal l_bdExecutedAmountTotal = new BigDecimal("0");
            for (int i = 0; i < l_intSize; i++)
            {
                FeqFinTransactionParams l_feqFinTransactionParams = 
                    (FeqFinTransactionParams)l_lisTransaction.get(i);
                double l_dbExecutedAmount = 
                    l_feqFinTransactionParams.getPrice() * l_feqFinTransactionParams.getQuantity();
                BigDecimal l_bdExecutedAmount = new BigDecimal(l_dbExecutedAmount + "");
                l_bdExecutedAmount = l_bdExecutedAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
                double l_dbExecutedAmountYen =
                    l_bizLogicProvider.calcJPYAmount(
                        l_bdExecutedAmount.doubleValue(), 
                        l_feqFinTransactionParams.getFxRate(), 
                        l_strChangeJpyRoundDiv);
                l_bdExecutedAmountTotal = 
                    l_bdExecutedAmountTotal.add(new BigDecimal(l_dbExecutedAmountYen + ""));
            }
            
            // �S�j�@@�R�j�Ŏ擾�������v�l�ŁA�����P�ʃe�[�u��.���v�����z�i�~�j���X�V����B
            FeqOrderUnitParams l_feqOrderUnitParams =
                new FeqOrderUnitParams((FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject());
            l_feqOrderUnitParams.setExecutedAmountYen(l_bdExecutedAmountTotal.doubleValue());
            l_queryProcesser.doUpdateQuery(l_feqOrderUnitParams);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        } 
        catch (DataFindException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        } 
        catch (DataQueryException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�l�b�e�B���Ώے����P��)<BR>
     * �l�b�e�B���O�ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@�����P�ʌ���<BR>
     * �ȉ��̏����ɂĊO�������P�ʃe�[�u������������B<BR>
     * ���������ɑΉ�����p�����[�^(����ID�A�،���ЃR�[�h) ��null�̏ꍇ�A���̏����͌����������珜�O����B<BR>
     * <BR>
     * [����]<BR>
     * ����ID = �p�����[�^.����ID And <BR>
     * �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And <BR>
     * �����^�C�v = ProductTypeEnum.�O������ And <BR>
     * ������ = �p�����[�^.������ And <BR>
     * (�����L����� = "�N���[�Y" And ��萔�� != (null or 0)) <BR>
     * <BR>
     * �Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_lngAccountId-(����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode-(�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_datBizDate-(������)<BR>
     * ������<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FeqOrderUnit[] getNettingOrderUnit(
        Long l_lngAccountId,
        String l_strInstitutionCode,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNettingOrderUnit(Long, String, Date)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisContainer = new ArrayList();
        List l_lisOrderUnitRows = null;
        List l_lisWEB3FeqOrderUnit = new ArrayList();
        WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = null;

        // ���������Ɍ���ID��null�̏ꍇ�A���̏����͌����������珜�O����B
        if (l_lngAccountId != null)
        {
            l_sbWhere.append("account_id = ?");
            l_lisContainer.add(l_lngAccountId);
        }

        // ���������ɏ،���ЃR�[�h��null�̏ꍇ�A���̏����͌����������珜�O����B
        if (WEB3StringTypeUtility.isNotEmpty(l_strInstitutionCode))
        {
            if (l_sbWhere.length() != 0)
            {
                l_sbWhere.append(" and institution_code = ?");
            }
            else
            {
                l_sbWhere.append("institution_code = ?");
            }

            l_lisContainer.add(l_strInstitutionCode);
        }

        if (l_sbWhere.length() != 0)
        {
            l_sbWhere.append(" and product_type = ?");
        }
        else
        {
            l_sbWhere.append("product_type = ?");
        }

        l_sbWhere.append(" and biz_date = ?");
        l_sbWhere.append(" and order_open_status = ?");
        l_sbWhere.append(" and executed_quantity is not null");
        l_sbWhere.append(" and executed_quantity <> 0");

        l_lisContainer.add(ProductTypeEnum.FOREIGN_EQUITY);
        l_lisContainer.add(WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
        l_lisContainer.add(OrderOpenStatusEnum.CLOSED);

        Object[] l_arrayContainer = l_lisContainer.toArray();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_arrayContainer);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_arrayWEB3FeqOrderUnit = new WEB3FeqOrderUnit[l_lisOrderUnitRows.size()];

            for (int i = 0; i < l_lisOrderUnitRows.size(); i++)
            {
                FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_lisOrderUnitRows.get(i);
                WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)this.toOrderUnit(l_row);
                l_lisWEB3FeqOrderUnit.add(l_feqOrderUnit);
            }

            l_lisWEB3FeqOrderUnit.toArray(l_arrayWEB3FeqOrderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_arrayWEB3FeqOrderUnit;
        }
    }

    /**
     * (update�l�b�e�B���O�����X�V���t)<BR>
     * �l�b�e�B���O�����̍X�V���t��update����B<BR>
     * <BR>
     * �@@�P�j�@@this.getOrderUnit( )���R�[�����A�����P�ʂ��擾����B<BR>
     * �@@�@@�@@�m�����n<BR>
     * �@@�@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��ID<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�ȉ��̏����ɊY������l�b�e�B���O�����̒����P�ʃ��R�[�h��update����B<BR>
     * �@@�@@<����><BR>
     * �@@�@@�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��ID <BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�ȉ��̏����ɊY������l�b�e�B���O�����̒��������́A�ŏI�������R�[�h�̍X�V���t��update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����e�[�u��.�����P��ID = �p�����[�^.�����P��ID�@@���� <BR>
     * �@@�@@�����e�[�u��.��������ԍ� = �P�j�Ŏ擾���������P��.���������ŏI�ʔ� <BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�������R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�ȉ��̏����ɊY������A�o���I�������̒����i�w�b�_�j�̍X�V������update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID = �P�j�Ŏ擾���������P��.����ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * @@param l_lngOrderUnitId-(�����P��ID)<BR>
     * �����P��ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateNettingOrderLastUpdatedTimestamp(Long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateNettingOrderLastUpdatedTimestamp(Long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            // �P�jthis.getOrderUnit( )���R�[�����A�����P�ʂ��擾����B
            // ���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B
            FeqOrderUnit l_feqOrderUnit = (FeqOrderUnit)this.getOrderUnit(l_lngOrderUnitId.longValue());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �Q�|�P�j�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��ID�̏�����
            // �Y������l�b�e�B���O�����̒����P�ʃ��R�[�h��update����B
            PrimaryKey l_feqOrderUnitPK = new FeqOrderUnitPK(l_lngOrderUnitId.longValue());
            // �����P�ʃ��R�[�h.�X�V���t = ���ݓ���
            Map l_mapNewValues = new HashMap();
            l_mapNewValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_queryProcessor.doUpdateQuery(l_feqOrderUnitPK, l_mapNewValues);

            // �Q�|�Q�j�ȉ��̏����ɊY������l�b�e�B���O�����̒��������́A�ŏI�������R�[�h�̍X�V���t��update����B
            // �����e�[�u��.�����P��ID = �p�����[�^.�����P��ID�@@���� 
            // �����e�[�u��.��������ԍ� = �P�j�Ŏ擾���������P��.���������ŏI�ʔ�
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
            // �������R�[�h.�X�V���t = ���ݓ���
            l_queryProcessor.doUpdateAllQuery(
                FeqOrderActionParams.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                new Object[]{l_lngOrderUnitId, new Integer(l_feqOrderUnitRow.getLastOrderActionSerialNo())},
                l_mapNewValues);

            // �Q�|�R�j�����i�w�b�_�j�e�[�u��.����ID = �P�j�Ŏ擾���������P��.����ID�̏����ɊY������
            // �o���I�������̒����i�w�b�_�j�̍X�V������update����B
            long l_lngOrderId = l_feqOrderUnitRow.getOrderId();
            // �����i�w�b�_�j���R�[�h.�X�V���t = ���ݓ���
            PrimaryKey l_feqOrderPK = new FeqOrderPK(l_lngOrderId);
            l_queryProcessor.doUpdateQuery(l_feqOrderPK, l_mapNewValues);
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����h�c�ɊY�����钍���P�ʂ����݂��܂���B",
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
