head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP���������e(WEB3IfoNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 nakazato(ACT) �V�K�쐬
*/
package webbroker3.ifodeposit;

import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradingModuleImpl;

/**
 * (�敨OP���������e)<BR>
 * �؋����v�Z���g�p���錻�������e��\������B<BR>
 * �i�V�K���]�̓`�F�b�N���Ɏg�p�j<BR>
 */
public class WEB3IfoNewOrderSpec
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoNewOrderSpec.class);

    public final static long DEFAULT_NEW_ID = -1;

    /**
     * (����ID)<BR>
     * 
     * �����V�K���̏ꍇ�A�Y���̒���ID�B<BR>
     * �V�K���̏ꍇ�A-1�B<BR>
     */
    public long orderId;

    /**
     * (�����P��ID)<BR>
     * 
     * �����V�K���̏ꍇ�A�Y���̒����P��ID�B<BR>
     * �V�K���̏ꍇ�A-1�B<BR>
     */
    public long orderUnitId;

    /**
     * (����ID)
     */
    public long productId;

    /**
     * (�s��ID)
     */
    public long marketId;

    /**
     * (���敪)<BR>
     * 
     * 1�F����<BR>
     * 2�F����<BR>
     */
    public ContractTypeEnum contractType;

    /**
     * (������)
     */
    public Date orderBizDate;

    /**
     * (��n��)
     */
    public Date deliveryDate;

    /**
     * (�敨�I�v�V�������i)<BR>
     * 
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 2�F�v�b�g�I�v�V����<BR>
     */
    public IfoDerivativeTypeEnum ifoDerivativeType;

    /**
     * (��������)
     */
    public double quantity = 0;

    /**
     * (�T�Z��n���)
     */
    public double estimatedNetAmount;

    /**
     * (�����Y�����R�[�h)
     */
    public String underlyingProductCode;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3IfoNewOrderSpec()
    {

    }

    /**
     * (create�敨�n�o���������e)<BR>
     * (static���\�b�h)<BR>
     * �������e�C���^�Z�v�^�ƒ������e���A�敨OP���������e�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�敨OP���������e)create�敨OP���������e�v�Q�ƁB <BR>
     * <BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@param l_interceptor - (�������e�C���^�Z�v�^)
     * @@param l_orderspec - (�������e)
     * @@param l_orderTypeEnum - (�������)
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoNewOrderSpec
     */
    public static WEB3IfoNewOrderSpec createWEB3IfoNewOrderSpec(
        WEB3GentradeSubAccount l_subAccount,
        Object l_interceptor,
        Object l_orderspec,
        OrderTypeEnum l_orderTypeEnum)
    {
        final String STR_METHOD_NAME = "WEB3IfoNewOrderSpec.createWEB3IfoNewOrderSpec(WEB3GentradeSubAccount, Object, Object, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoNewOrderSpec l_newOrderSpec = null;
            l_newOrderSpec = new WEB3IfoNewOrderSpec();
            IfoOrderUnitParams l_orderUnitParams = new IfoOrderUnitParams();

            //�敨�I�v�V�����V�K�������̏ꍇ
            if (l_orderspec instanceof IfoOpenContractOrderSpec)
            {
                IfoOpenContractOrderSpec l_ifoOrderspec = (IfoOpenContractOrderSpec)l_orderspec;

                //�ڋq����
                l_orderUnitParams.setAccountId(l_subAccount.getAccountId());
                l_orderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderUnitParams.setBranchId(
                    l_subAccount.getMainAccount().getBranch().getBranchId());

                //���������쐬
                l_orderUnitParams.setOrderId(DEFAULT_NEW_ID);
                l_orderUnitParams.setOrderUnitId(DEFAULT_NEW_ID);
                OrderCategEnum orderCateg =
                    IfoDerivativeTypeEnum.FUTURES.equals(l_ifoOrderspec.getDerivativeType())
                        ? OrderCategEnum.IDX_FUTURES_OPEN
                        : OrderCategEnum.IDX_OPTIONS_OPEN;
                l_orderUnitParams.setOrderCateg(orderCateg);
                l_orderUnitParams.setOrderType(l_orderTypeEnum);
                l_orderUnitParams.setQuantity(l_ifoOrderspec.getQuantity());
                l_orderUnitParams.setTaxType(l_ifoOrderspec.getTaxType());

                //���������쐬
                TradedProduct l_tp =
                    getIFOTradedProduct(
                        l_subAccount,
                        l_ifoOrderspec.getUnderlyingProductCode(),
                        l_ifoOrderspec.getDerivativeType(),
                        l_ifoOrderspec.getMonthOfDelivery(),
                        l_ifoOrderspec.getStrikePrice(),
                        l_ifoOrderspec.getMarketCode());

                Product l_p = l_tp.getProduct();
                l_orderUnitParams.setProductId(l_p.getProductId());
                l_orderUnitParams.setProductType(l_p.getProductType());
                l_orderUnitParams.setDeliveryDate(l_tp.getDailyDeliveryDate());
                l_orderUnitParams.setMarketId(l_tp.getMarket().getMarketId());
                Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(l_tp.getTradedProductId()).getCurrentBizDate();
                l_orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor)l_interceptor;
                l_orderUnitParams =
                    l_persistenceInterceptor.mutate(
                        OrderManagerPersistenceType.INSERT,
                        OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER,
                        l_orderUnitParams);

            }
            //�敨�I�v�V�����V�K�����������̏ꍇ
            else if (l_orderspec instanceof IfoChangeOpenContractOrderSpec)
            {
                IfoChangeOpenContractOrderSpec l_ifoChangeOrderspec =
                    (IfoChangeOpenContractOrderSpec)l_orderspec;

                IfoOrderRow orderRow = IfoOrderDao.findRowByPk(l_ifoChangeOrderspec.getOrderId());
                List orderUnits = IfoOrderUnitDao.findRowsByOrderId(orderRow);
                l_orderUnitParams = new IfoOrderUnitParams((IfoOrderUnitRow)orderUnits.get(0));

                //���������쐬
                l_orderUnitParams.setQuantity(
                    l_ifoChangeOrderspec.getAfterChangeOriginalQuantity());
                l_orderUnitParams.setPrice(l_ifoChangeOrderspec.getAfterChangePrice());

                //�T�Z����ȂǃJ�X�^�}�C�Y�����쐬
                IfoOrderManagerPersistenceEventInterceptor l_persistenceInterceptor =
                    (IfoOrderManagerPersistenceEventInterceptor)l_interceptor;
                l_orderUnitParams =
                    l_persistenceInterceptor.mutate(
                        OrderManagerPersistenceType.UPDATE,
                        OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED,
                        l_orderUnitParams);
            }
            //���̑��̏ꍇ�A���Ή����߈ُ픭��
            else
            {
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            /*
             * �敨OP���������e�ɒl���Z�b�g
             */
            //����ID
            l_newOrderSpec.orderId = l_orderUnitParams.getOrderId();
            //�����P��ID
            l_newOrderSpec.orderUnitId = l_orderUnitParams.getOrderUnitId();
            //����ID
            l_newOrderSpec.productId = l_orderUnitParams.getProductId();
            //�s��ID
            l_newOrderSpec.marketId = l_orderUnitParams.getMarketId();
            //������
            l_newOrderSpec.orderBizDate =
                WEB3DateUtility.getDate(l_orderUnitParams.getBizDate(), "yyyyMMdd");
            //��n��
            l_newOrderSpec.deliveryDate = l_orderUnitParams.getDeliveryDate();
            //��������
            l_newOrderSpec.quantity = l_orderUnitParams.getQuantity();
            //�T�Z��n���
            l_newOrderSpec.estimatedNetAmount = l_orderUnitParams.getEstimatedPrice();

            /*
             * ���敪
             */
            //"�敨�V�K��������"�܂��́A"�I�v�V�����V�K��������"�̏ꍇ
            if (l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()
                || l_orderTypeEnum.intValue() == OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue())
            {
                //�������Z�b�g
                l_newOrderSpec.contractType = ContractTypeEnum.LONG;
            }
            //�ȊO�̏ꍇ
            else
            {
                //�������Z�b�g
                l_newOrderSpec.contractType = ContractTypeEnum.SHORT;
            }

            /*
             * �敨�I�v�V�������i�敪
             */
            IfoProductManager l_pm =
                (IfoProductManager) (GtlUtils
                    .getTradingModule(IfoTradingModuleImpl.TRADING_MODULE_NAME)
                    .getProductManager());
            IfoProduct l_product = (IfoProduct)l_pm.getProduct(l_orderUnitParams.getProductId());
            l_newOrderSpec.ifoDerivativeType = l_product.getDerivativeType();

            /*
             * �����Y�����R�[�h
             */
            l_newOrderSpec.underlyingProductCode = l_product.getUnderlyingProductCode();

            log.exiting(STR_METHOD_NAME);
            return l_newOrderSpec;

        }
        catch(NotFoundException nfe)
        {
            nfe.printStackTrace();
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME, nfe
                        .getMessage(), nfe);
        }
        catch(DataException de)
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, de
                        .getMessage(), de);
        }
    }

    /**
     * (get�敨�I�v�V�����������())<BR>
     * <BR>
     * �敨�I�v�V��������������擾����B<BR>
     * @@param l_subAccount   �⏕����
     * @@param l_sUnderlyingProductCode   �����R�[�h
     * @@param�@@l_derivativeType�@@��n�^�C�v
     * @@param l_sMonthOfDelivery   ����
     * @@param l_dnlStrikePrice   �s�g���i
     * @@param l_sMarketCode  Market�R�[�h
     * @@return TradedProduct  - �������
     */
    private static TradedProduct getIFOTradedProduct(
        SubAccount l_subAccount,
        String l_sUnderlyingProductCode,
        IfoDerivativeTypeEnum l_derivativeType,
        String l_sMonthOfDelivery,
        double l_dnlStrikePrice,
        String l_sMarketCode)
        throws NotFoundException
    {
        String l_tmName = IfoTradingModuleImpl.TRADING_MODULE_NAME;
        IfoProductManager l_pm =
            (IfoProductManager) (GtlUtils.getTradingModule(l_tmName).getProductManager());
        TradedProduct tradedProduct =
            l_pm.getIfoTradedProduct(
                l_subAccount.getInstitution(),
                l_sUnderlyingProductCode,
                l_derivativeType,
                l_sMonthOfDelivery,
                l_dnlStrikePrice,
                l_sMarketCode);
        return tradedProduct;
    }
}@
