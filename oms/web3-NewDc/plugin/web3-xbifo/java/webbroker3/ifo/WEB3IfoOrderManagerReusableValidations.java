head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����R���ʃ`�F�b�N(WEB3IfoOrderManagerReusableValidations.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11   ���Ō� (Sinocom) �V�K�쐬
Revesion History : 2006/07/12   �юu�� (���u) �d�l�ύX���f��455,469,473,496,516,519
Revesion History : 2006/09/22 �s�p (���u) �d�l�ύX ���f��549
Revesion History : 2006/10/9 ������(���u)�@@���f��No.555
Revesion History : 2006/11/29 �����(���u)�@@���f��No.582
Revesion History : 2007/01/25 �����F (���u) ���f��No.589,596,604
Revesion History : 2007/04/25 �����Q (���u) �d�l�ύX���f��No.635
Revesion History : 2007/06/08 �юu�� (���u) �d�l�ύX���f��No.641,668,683,699,703,714,720,732
Revesion History : 2007/06/21 ���� (���u) �d�l�ύX���f��No.748,749,750
Revesion History : 2007/06/22 ���� (���u) �d�l�ύX���f��No.754
Revesion History : 2007/06/23 �юu�� (���u) �d�l�ύX���f��No.755
Revesion History : 2007/11/27 ���n (���u) �d�l�ύX���f��No.820
Revesion History : 2008/04/22 �����F (���u) ���f��No.879,882
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
Revesion History : 2008/09/03 ���z(���u) IFO�����_�Ή�
Revesion History : 2008/12/19 ������(���u) ���f��No.904
*/

package webbroker3.ifo;

import java.math.BigDecimal;
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
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DateRangeQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContract;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MultiChangeabilityDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TriggerorderWlimitorderCheckOrderCondPriceDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨OP�����R���ʃ`�F�b�N)<BR>
 * �敨OP�����R���ʃ`�F�b�N�N���X<BR>
 * �����R���̌X�̃`�F�b�N�������B<BR>
 *�iIfoProductTypeOrderManagerReusableValidations�̊g���N���X�j<BR>
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3IfoOrderManagerReusableValidations extends IfoProductTypeOrderManagerReusableValidations
{

    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderManagerReusableValidations.class);

    /**
     * (validate�敨OP�����J��)<BR>
     *  �ڋq���敨�I�v�V�����������J�݂��Ă��邩���`�F�b�N����B<BR>
     *�P�j�@@�ڋq�I�u�W�F�N�g�擾<BR>
     *  �⏕����.getMainAccount()�ɂČڋq�I�u�W�F�N�g���擾����B<BR>
     *�Q�j�@@�����J�݋敪�`�F�b�N<BR>
     *  �ڋq.is�敨OP�����J��() == false �̏ꍇ�A��O���X���[����B<BR>
     *  [�ڋq.is�敨OP�����J��()�ɓn������]<BR>
     *      �敨�^�I�v�V�����敪�F����.�敨�^�I�v�V�����敪<BR>
     *  �敨�̏ꍇ�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00284<BR>
     *  �I�v�V�����̏ꍇ�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00283<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_strFuturesOptionDivision - �敨�^�I�v�V�����敪<BR>
     * 1�F�敨 2�F�I�v�V����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4055484303E4
     */
    public void validateFuturesOptionAccountOpen(SubAccount l_subAccount, String l_strFuturesOptionDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFuturesOptionAccountOpen(l_subAccount,l_strFuturesOptionDivision)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_strFuturesOptionDivision == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        //�ڋq�I�u�W�F�N�g���擾����
        WEB3GentradeMainAccount l_mainAccount = null;
        l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        //�ڋqROW�I�u�W�F�N�g���擾����
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuturesOptionDivision))
        {
            //�敨�^�I�v�V�����敪�F�敨�̏ꍇ
            if(!l_mainAccount.isIfoAccountOpen(l_strFuturesOptionDivision))
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00284);
                //��O���X���[����
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00284,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        else
        {
            //�敨�^�I�v�V�����敪�F�I�v�V�����̏ꍇ
            if(!l_mainAccount.isIfoAccountOpen(l_strFuturesOptionDivision))
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00283);
                //��O���X���[����
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00283,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������)<BR>
     * �������ʂ�������ʂ𒴂��ĂȂ����`�F�b�N���s���B<BR>
     * <BR>
     * �i���� > ������ʁj�̏ꍇ�ɗ�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00144<BR>
     * @@param l_dblMaxQuantity - �������<BR>
     * @@param l_dblQuantity - ��������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40611F5B0145
     */
    public boolean validateMaxQuantity(double l_dblMaxQuantity, double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMaxQuantity(l_dblMaxQuantity,l_dblQuantity)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_dblMaxQuantity = " + l_dblMaxQuantity);
        log.debug("l_dblQuantity = " + l_dblQuantity);

        boolean l_blnIsValidateMaxQuantity = false;

        if (l_dblQuantity > l_dblMaxQuantity)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00144);
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00144, this.getClass().getName() + STR_METHOD_NAME);
        }
        else
        {
            l_blnIsValidateMaxQuantity = true;
        }

        return l_blnIsValidateMaxQuantity;
    }

    /**
     * (validate�������)<BR>
     * ��������̃`�F�b�N���s���A<BR>
     * �敨OP��������I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateTradedProduct�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �P�j�@@�敨OP��������擾<BR>
     * �X�[�p�[�N���X�̏����ɂĎ�������I�u�W�F�N�g���擾����B <BR>
     * �擾�ł��Ȃ��ꍇ�́A��������Ɣ��f���A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00735<BR>
     * <BR>
     * �Q�j�@@������~�`�F�b�N<BR>
     * �敨OP�������.is����K���iis����, is�V�K���j<BR>
     * �ɂĔ����K�������𔻒肷��B<BR>
     * true���ԋp���ꂽ�ꍇ�A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00004<BR>
     * <BR>
     * �R�j�@@�����Ԓ����̃`�F�b�N<BR>
     * �敨OP�������.is�����ԓ�()��false��ԋp�����ꍇ�͗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00145<BR>
     * @@param l_ifoProduct - �敨OP����
     * @@param l_market - �s��
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * <BR>
     * �������ǂ����̔���B<BR>
     * �����̏ꍇtrue�A�����̏ꍇfalse�B<BR>
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * <BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoTradedProductImpl
     * @@throws WEB3BaseException
     * @@roseuid 40638D7701A7
     */
    public WEB3IfoTradedProductImpl validateTradedProduct(WEB3IfoProductImpl l_ifoProduct, WEB3GentradeMarket l_market, boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProduct(l_ifoProduct,l_market,l_blnIsBuyToOpenOrder,l_blnIsOpenContract)";
        log.entering(STR_METHOD_NAME);
        if (l_ifoProduct == null || l_market == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("ProductCode = " + l_ifoProduct.getProductCode());
        log.debug("InstitutionID = " + l_market.getInstitution().getInstitutionId());
        log.debug("MarketCode = " + l_market.getMarketCode());
        log.debug("l_blnIsBuyToOpenOrder = " + l_blnIsBuyToOpenOrder);
        log.debug("l_blnIsOpenContract = " + l_blnIsOpenContract);

        //�敨OP��������I�u�W�F�N�g
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
        try
        {
            l_ifoTradedProductImpl =
                (WEB3IfoTradedProductImpl) ((WEB3IfoProductManagerImpl) super.getIfoProductManager()).getIfoTradedProduct(
                    l_market.getInstitution(),
                    l_ifoProduct.getProductCode(),
                    l_market.getMarketCode());

        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00735,
                STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
        }

        log.debug("TradedProductID = " + l_ifoTradedProductImpl.getTradedProductId());

        boolean l_blnIsTradingSuspended = l_ifoTradedProductImpl.isTradingSuspended(l_blnIsBuyToOpenOrder, l_blnIsOpenContract);

        log.debug("isTradingSuspended = " + l_blnIsTradingSuspended);

        //������~�`�F�b�N<BR>
        if (l_blnIsTradingSuspended)
        {
            //true���ԋp���ꂽ�ꍇ
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00004,
                this.getClass().getName() + STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR);
        }

        boolean l_blnIsInListedTerm = l_ifoTradedProductImpl.isInListedTerm();

        log.debug("l_blnIsListed = " + l_blnIsInListedTerm);

        //�����Ԓ����̃`�F�b�N
        if (l_blnIsInListedTerm == false)
        {
            //�����ԈȊO�̏ꍇ
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00145);
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00145, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_ifoTradedProductImpl;
    }

    /**
     * (validate����)<BR>
     * ���ʂ̃`�F�b�N���s���B<BR>
     * �@@�|���ʂ��O�܂��̓}�C�i�X�l�łȂ����ƁB <BR>
     * �@@�|���ʂ�����P�ʂ𒴂��Ă��Ȃ����ƁB <BR>
     * �ivalidateQuantity�̃I�[�o�[���[�h�j <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jvalidate���ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoProduct - �敨OP�������<BR>
     * �敨OP��������I�u�W�F�N�g<BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * <BR>
     * �������ǂ����̔���B<BR>
     * �����̏ꍇtrue�A�����̏ꍇfalse�B<BR>
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * <BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     *
     * @@throws WEB3BaseException
     * @@roseuid 40642B4100BC
     */
    public void validateQuantity(WEB3GentradeSubAccount l_subAccount, WEB3IfoTradedProductImpl l_ifoTradedProduct, double l_dblQuantity, boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQuantity(l_subAccount,l_ifoProduct,l_dblQuantity,l_blnIsBuyToOpenOrder," + "l_blnIsOpenContract)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_ifoTradedProduct == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�����I�u�W�F�N�g���擾����
        Product l_product = l_ifoTradedProduct.getProduct();

        //�敨OP�����I�u�W�F�N�g���擾
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl) l_product;

        try
        {
            //���ʃ`�F�b�N
            super.validateQuantity(l_dblQuantity);
        }
        catch (OrderValidationException l_ovex)
        {
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorMessage(l_ovex.getValidationResult().toString());
            log.error(STR_METHOD_NAME, l_ovex);

            //��O���X���[����
            throw new WEB3BaseException(l_errorInfo, getClass().getName() + STR_METHOD_NAME);
        }

        //�����I�u�W�F�N�g���擾����
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //���X�I�u�W�F�N�g���擾����
        Branch l_branch = l_mainAccount.getBranch();

        //�i���X�w���ʁj�戵�����I�u�W�F�N�g�𐶐�����
        WEB3GentradeBranchIndexDealtCond l_handlingCondBranchIndex = new WEB3GentradeBranchIndexDealtCond(l_branch.getBranchCode(), l_ifoTradedProduct);

        //�����̏�����ʎ擾����
        double l_dblMaxQuantity = 0D;
        l_dblMaxQuantity = l_handlingCondBranchIndex.getMaxQuantity(l_blnIsBuyToOpenOrder, l_blnIsOpenContract);

        String l_strMaxQuantity = WEB3StringTypeUtility.formatNumber(l_dblMaxQuantity);
        log.debug("�����̏������ = " + l_strMaxQuantity);

        //�s�����ʃ`�F�b�N
        //���L�����̏ꍇ�A��O���X���[����B
        //����.���ʁ������̏������
        if( l_dblQuantity > l_dblMaxQuantity)
        {
            if(l_blnIsBuyToOpenOrder && l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02001);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02001.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME); 
            }
            else if(!l_blnIsBuyToOpenOrder && l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02002);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02002.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            else if(!l_blnIsBuyToOpenOrder && !l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02003);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02003.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            else if(l_blnIsBuyToOpenOrder && !l_blnIsOpenContract)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02004);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02004.addText(l_strMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
        }   
        
        if ((l_blnIsOpenContract && l_blnIsBuyToOpenOrder && l_ifoProductImpl.isOptionProduct())
            || !l_blnIsOpenContract)
        {
            //�I�v�V�����̐V�K�����܂��͕ԍς̏ꍇ�����I��
            log.debug("�I�v�V�����̐V�K�����܂��͕ԍς̏ꍇ�����I��");
            return;
        }

        //������������ʂ��擾
        double l_dblTotalOpenSellMaxQuantity = 0D;
        l_dblTotalOpenSellMaxQuantity = l_handlingCondBranchIndex.getTotalOpenSellMaxQuantity();
        log.debug("������������� = " + l_dblTotalOpenSellMaxQuantity);

        //�|�W�V�����}�l�[�W�����擾����
        WEB3IfoPositionManagerImpl l_ifoPositionManager = (WEB3IfoPositionManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        List l_lisContracts = null;
        l_lisContracts = l_ifoPositionManager.getContracts(l_mainAccount, DefaultSortKeySpec.NULL_SORT_KEY_SPEC, ProductTypeEnum.IFO);
        //���ʂ̐��ʂ̍��v�l
        double l_dblTotalContractQuantityCnt = 0D;
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {
            l_dblTotalContractQuantityCnt = 0D;
        }
        else
        {
            int i = 0;
            //���ʃ��X�g�̒���
            int l_intContractsLen = 0;
            l_intContractsLen = l_lisContracts.size();

            //���ʃI�u�W�F�N�g
            IfoContract l_ifoContract = null;
            //�敨OP�����I�u�W�F�N�g_����
            WEB3IfoProductImpl l_ifoProductImpl_Con = null;

            for (i = 0; i < l_intContractsLen; i++)
            {
                //���ʃI�u�W�F�N�g�����X�g����擾
                l_ifoContract = (IfoContract) l_lisContracts.get(i);

                //�敨OP����
                l_ifoProductImpl_Con = (WEB3IfoProductImpl) l_ifoContract.getProduct();

                log.debug("l_ifoContract.isShort() = " + l_ifoContract.isShort());
                log.debug("l_ifoContract.getQuantity() = " + l_ifoContract.getQuantity());
                log.debug("l_dblTotalContractQuantityCnt = " + l_dblTotalContractQuantityCnt);

                if (l_ifoProductImpl.isOptionProduct() == l_ifoProductImpl_Con.isOptionProduct() &&
                        l_ifoProductImpl.getUnderlyingProductCode().equals(l_ifoProductImpl_Con.getUnderlyingProductCode()))
                {
                    //��������^�C�v�������ꌴ���Y�����R�[�h
                    if ((l_ifoProductImpl.isOptionProduct() && l_ifoContract.isShort())
                        || l_ifoProductImpl.isFuturesProduct())
                    {
                        //�I�v�V�����̏ꍇ�A������(����.isShort() == true)�݂̂����v����B
                        //�敨�̏ꍇ�A���ׂĂ̌��ʐ��ʂ����v����B
                        l_dblTotalContractQuantityCnt = l_dblTotalContractQuantityCnt + l_ifoContract.getQuantity();
                    }
                }
            }
            log.debug("After For l_dblTotalContractQuantityCnt = " + l_dblTotalContractQuantityCnt);
        }

        //OP�����}�l�[�W���I�u�W�F�N�g���擾����
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        //�����P�ʃ��X�g�I�u�W�F�N�g
        List l_lisOrderUnits = null;
        l_lisOrderUnits =
            l_optionOrderManagerImpl.getOpenOrderUnits(l_subAccount, DateRangeQueryParamsSpec.ALL_DATE_RANGES, PaginationQueryParamsSpec.ALL_PAGES, DefaultSortKeySpec.NULL_SORT_KEY_SPEC);

        //��������萔�ʂ̍��v���v�Z����
        double l_dblOpenContractNotAcceptedCnt = 0D;

        if (l_lisOrderUnits == null || l_lisOrderUnits.size() == 0)
        {
            l_dblOpenContractNotAcceptedCnt = 0D;
        }
        else
        {
            //�����P�ʃ��X�g�I�u�W�F�N�g
            OrderUnit l_orderUnit = null;

            int j = 0;
            //�����P�ʃ��X�g�̒���
            int l_intOrdersLen = 0;
            l_intOrdersLen = l_lisOrderUnits.size();

            for (j = 0; j < l_intOrdersLen; j++)
            {
                l_orderUnit = (OrderUnit) l_lisOrderUnits.get(j);

                //��萔�ʂ��擾����B
                double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
                //�������ʂ��擾����B
                double l_dblOrderQuantity = l_orderUnit.getQuantity();

                if (Double.isNaN(l_dblExecutedQuantity))
                {
                    l_dblExecutedQuantity = 0;
                }
                
                //���ꌴ���Y�����R�[�h(*2)�̏ꍇ�A����萔�ʂ����v����B
                //(*2)�敨OP����.get�����Y�����R�[�h() == �����P��.get����().get�����Y�����R�[�h()
                if (l_ifoProductImpl.getUnderlyingProductCode().equals(
                        ((WEB3IfoProductImpl)l_orderUnit.getProduct()).getUnderlyingProductCode()))
                {
                    if (l_ifoProductImpl.isOptionProduct() == true)
                    {
                        if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderUnit.getOrderType()))
                        {
                            //�I�v�V�����V�K���������̏ꍇ
                            log.debug("�I�v�V�����V�K���������̏ꍇ");
                            //(*4) ����萔�ʁ�1�����v����
                            //��1 ����萔�ʂ̌v�Z�������ʁigetQuantity�j�@@�|�@@��萔�ʁigetExecutedQuantity()�j
                            l_dblOpenContractNotAcceptedCnt += (l_dblOrderQuantity - l_dblExecutedQuantity);
                        }
                    }
                    else if (l_ifoProductImpl.isFuturesProduct() == true)
                    {
                        if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderUnit.getOrderType())
                                || OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()))
                        {
                            //�敨�V�K���������̏ꍇ
                            log.debug("�敨�V�K���������̏ꍇ");
                            //(*4) ����萔�ʁ�1�����v����
                            ////��1 ����萔�ʂ̌v�Z�������ʁigetQuantity�j�@@�|�@@��萔�ʁigetExecutedQuantity()�j
                            l_dblOpenContractNotAcceptedCnt += (l_dblOrderQuantity - l_dblExecutedQuantity);
                        }
                    }   
                }
                log.debug("l_dblOpenContractNotAcceptedCnt = " + l_dblOpenContractNotAcceptedCnt);
            }
        }

        //�`�F�b�N���鐔��
        //����.���� �{ ���v�����ʐ��� �{ ��������萔�ʂ̍��v
        double l_dblCheckQuantity = 0D;
        l_dblCheckQuantity = l_dblQuantity + l_dblTotalContractQuantityCnt + l_dblOpenContractNotAcceptedCnt;

        log.debug("����.���� �{ ���v�����ʐ��� �{ ��������萔�ʂ̍��v = " + l_dblCheckQuantity);

        //���v�����ʐ��ʃ`�F�b�N
        //���L�����̏ꍇ�i���v�����ʐ��ʂ𒴂���ꍇ�j�A��O���X���[����B
        //�i����.���� �{ ���v�����ʐ��� �{ ��������萔�ʂ̍��v�j���������������
        String l_strTotalOpenSellMaxQuantity = WEB3StringTypeUtility.formatNumber(l_dblTotalOpenSellMaxQuantity);
        log.debug("������������� = " + l_strTotalOpenSellMaxQuantity);
        
        if( l_dblCheckQuantity > l_dblTotalOpenSellMaxQuantity)
        {
            if(l_ifoProductImpl.isOptionProduct())
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02005);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02005.addText(l_strTotalOpenSellMaxQuantity),
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_02006);
                throw new WEB3BusinessLayerException
                    (WEB3ErrorCatalog.BUSINESS_ERROR_02006.addText(l_strTotalOpenSellMaxQuantity), 
                     this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate�����P��)<BR>
     * �w�l�̃`�F�b�N���s���B<BR>
     * �ivalidatePrice�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �@@�|�Ēl�`�F�b�N<BR>
     * �@@�|�l���`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jvalidate�����P���v�Q�ƁB<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * @@param l_ifoTradedProduct - �敨OP��������I�u�W�F�N�g
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 4064306400FB
     */
    public void validateOrderUnitPrice(double l_dblLimitPrice, WEB3IfoTradedProductImpl l_ifoTradedProduct, SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderUnitPrice(l_dblLimitPrice,l_ifoTradedProduct,l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoTradedProduct == null || l_subAccount == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        log.debug(STR_METHOD_NAME + "l_dblLimitPrice = " + l_dblLimitPrice);
        try
        {
            //�w�l���`�F�b�N����
            if (l_dblLimitPrice == 0)
            {
                //�O�̏ꍇ�������s��Ȃ�
                log.debug(STR_METHOD_NAME + "�w�l���O�̏ꍇ");

            }
            else
            {
                //�O�ȊO�̏ꍇ�ȉ��������s��
                log.debug(STR_METHOD_NAME + "�w�l���O�ȊO�̏ꍇ");

                //WEB3IfoTradedProductImpl.java�I�u�W�F�N�g���擾����
                //DatafindException,DataQueryException ,DateNetWorkException
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl) (l_ifoTradedProduct.getProduct());

                WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

                double l_dblTickValue = 0; //���ݒl
                //���ݒl���擾����
                l_dblTickValue = l_productManagerImpl.getTickValue(l_ifoProductImpl, l_dblLimitPrice);
                log.debug("���ݒl" + " l_dblTickValue = " + l_dblTickValue);

                //�Ăђl���`�F�b�N����
                this.validateTickValueDef(l_dblTickValue, l_dblLimitPrice);

                //�l���`�F�b�N���{����
                boolean l_isPriceRangeChecked = false;
                l_isPriceRangeChecked = l_ifoTradedProduct.isPriceRangeChecked();

                log.debug("�l���`�F�b�N" + " l_isPriceRangeChecked = " + l_isPriceRangeChecked);

                if (l_isPriceRangeChecked)
                {
                    //Is�l���`�F�b�N���{��True�̏ꍇ
                    //�ȉ����������{����
                    log.debug(STR_METHOD_NAME + "Is�l���`�F�b�N���{��True�̏ꍇ");

                    //���Z�l���擾����
                    double l_dblLiquidationPrice = 0D;
                    l_dblLiquidationPrice = l_ifoTradedProduct.getLiquidationPrice();

                    log.debug("���Z�l" + " l_dblLiquidationPrice = " + l_dblLiquidationPrice);

                    //�����Y�������擾����
                    double l_dblUnderlyingCurrentPrice = 0D;
                    l_dblUnderlyingCurrentPrice = l_ifoTradedProduct.getUnderlyingCurrentPrice();

                    log.debug("�����Y����" + " l_dblUnderlyingCurrentPrice = " + l_dblUnderlyingCurrentPrice);

                    //��l(�P��)���擾����
                    double l_dblBasePrice = 0D;
                    l_dblBasePrice = this.calcBasePrice(l_dblLiquidationPrice, l_dblTickValue);

                    log.debug("��l(�P��)" + " l_dblBasePrice = " + l_dblBasePrice);

                    //��l�������l�������擾����
                    double l_dblBasePriceDeregPriceRange = 0D;
                    //��l
                    double l_dblTempBasePrice = 0D;
                    IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();
                    if (WEB3FuturesOptionDivDef.FUTURES.equals(l_ifoProductRow.getFutureOptionDiv()))
                    {
                        //�敨�n�o����.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ�Aget���Z�l()�̖߂�l
                        l_dblTempBasePrice = l_dblLiquidationPrice;
                    }
                    else if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductRow.getFutureOptionDiv()))
                    {
                        //�敨�n�o����.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ�Aget�����Y����()�̖߂�l
                        l_dblTempBasePrice = l_dblUnderlyingCurrentPrice;
                    }
                    l_dblBasePriceDeregPriceRange =
                        this.calcBasePriceDeregPriceRange(l_dblTempBasePrice, l_dblTickValue);

                    log.debug("��l�������l����" + " l_dblBasePriceDeregPriceRange = " + l_dblBasePriceDeregPriceRange);

                    //�����l�����擾����B
                    //[get�����l��()�Ɏw�肷�����]
                    //�敨OP�����F�@@�igetProduct()�̖߂�l�j
                    //�P���F�@@�icalc��l�������l����()�̖߂�l�j
                    double l_dblDeregulatedPriceRange = 0D;
                    l_dblDeregulatedPriceRange =
                        l_productManagerImpl.getDeregulatedPriceRange(l_ifoProductImpl, l_dblBasePriceDeregPriceRange);

                    log.debug("�����l��" + " l_dblDeregulatedPriceRange = " + l_dblDeregulatedPriceRange);

                    //�l��������擾����
                    double l_dblStopHighPrice = 0D;
                    l_dblStopHighPrice = this.calcStopHighPrice(l_dblBasePrice, l_dblDeregulatedPriceRange, l_ifoProductImpl);

                    //�l���������擾����
                    double l_dblStopLowPrice = 0D;
                    l_dblStopLowPrice = this.calcStopLowPrice(l_dblBasePrice, l_dblDeregulatedPriceRange, l_ifoProductImpl);

                    log.debug("�l�����" + " l_dblStopHighPrice = " + l_dblStopHighPrice);
                    log.debug("�l������" + " l_dblStopLowPrice = " + l_dblStopLowPrice);

                    //�l�����`�F�b�N����
                    this.validatePriceRange(l_dblLimitPrice, l_dblStopHighPrice, l_dblStopLowPrice);
                }
                else
                {
                    //Is�l���`�F�b�N���{��False�̏ꍇ
                    //�������Ȃ�
                    log.debug(STR_METHOD_NAME + "Is�l���`�F�b�N���{��False�̏ꍇ");
                }
            }
        }
        catch (WEB3BaseException l_webx)
        {
            throw new WEB3BaseException(
                l_webx.getErrorInfo(),
                STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�戵�\�w��)<BR>
     * ���X�Ŏ戵�\�Ȏw�����𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�i���X�w���ʁj�戵�\�����𐶐�����B<BR>
     * [�R���X�g���N�^�̈���]<BR>
     * ���X�R�[�h�F�@@���X�R�[�h<BR>
     * �敨OP��������F�@@�敨OP�������<BR>
     * <BR>
     * �Q�j�@@������{<BR>
     * �擾�����I�u�W�F�N�g.is.�戵�\() == false<BR>
     * �̏ꍇ�͎戵�s�Ɣ��肵��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00147<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_ifoTradedProduct - �敨OP�������
     * @@throws WEB3BaseException
     * @@roseuid 406434C603CA
     */
    public void validateHandlingIndex(String l_strBranchCode, WEB3IfoTradedProductImpl l_ifoTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingIndex(l_strBranchCode,l_ifoTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoTradedProduct == null)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        //�i���X�w���ʁj�戵�\�����𐶐�����B
        //        WEB3GentradeHandlingCondBranchIndex l_handlingCondBranchIndex =
        //            new WEB3GentradeHandlingCondBranchIndex(l_strBranchCode,l_ifoTradedProduct);
        WEB3GentradeBranchIndexDealtCond l_handlingCondBranchIndex = new WEB3GentradeBranchIndexDealtCond(l_strBranchCode, l_ifoTradedProduct);

        if (l_handlingCondBranchIndex == null)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_handlingCondBranchIndex.isHandlingPossible())
        {
            //is.�戵�\() == true
        }
        else
        {
            //is.�戵�\() == true
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00147);
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00147, this.getClass().getName() + STR_METHOD_NAME);

        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate�Ăђl)<BR>
     * ���ݒl�Ŋ���؂��l�����`�F�b�N����B<BR>
     * <BR>
     * �i�w�l�^���ݒl�j�������łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00148<BR>
     * @@param l_dblTickValue - ���ݒl<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4067B83900F4
     */
    public void validateTickValueDef(double l_dblTickValue, double l_dblLimitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTickValueDef(l_dblTickValue,l_dblLimitPrice)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdLimitPrice = new BigDecimal(l_dblLimitPrice + "");
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        if (l_dblTickValue <= 0 || l_dblLimitPrice < 0)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("���ݒl = " + l_dblTickValue);
        log.debug("�w�l = " + l_dblLimitPrice);
        log.debug("�i�w�l�^���ݒl�j = " + remainder(l_bdLimitPrice, l_bdTickValue).doubleValue());

        if (remainder(l_bdLimitPrice, l_bdTickValue).compareTo(new BigDecimal("0")) != 0)
        {
            //�i�w�l�^���ݒl�j�������łȂ��ꍇ
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00148);
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00148, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (calc��l)<BR>
     * ���Z�l�̒l�����ݒl�Ŋ���؂��l�ɂȂ�悤�Ɍv�Z����B<BR>
     * <BR>
     * ���i�i���Z�l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i���Z�l�^���ݒl(*1)�j�~���ݒl<BR>
     * �@@(*1)�@@���Z�̌v�Z���ʂ�؂�グ�B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i���Z�l�^���ݒl(*2)�j�~���ݒl<BR>
     * �@@(*2)�@@���Z�̌v�Z���ʂ�؂�̂āB<BR>
     * @@param l_dblLiquidationPrice - ���Z�l<BR>
     * @@param l_dblTickValue - ���ݒl<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4067BE3502D8
     */
    public double calcBasePrice(double l_dblLiquidationPrice, double l_dblTickValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcBasePrice(l_dblLiquidationPrice,l_dblTickValue)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdLiquidationPrice = new BigDecimal(l_dblLiquidationPrice + "");
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        if (l_dblTickValue <= 0 || l_dblLiquidationPrice < 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        double l_dblBasePrice = 0D; //��l

        log.debug(STR_METHOD_NAME + "�i���Z�l�^���ݒl�j�̗]�� = " + remainder(l_bdLiquidationPrice, l_bdTickValue).doubleValue());
        log.debug(STR_METHOD_NAME + "���ݒl�^2 = " + l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

        if (remainder(l_bdLiquidationPrice, l_bdTickValue).compareTo(
            l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
        {
            //���Z�̌v�Z���ʂ�؂�グ
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        else
        {
            //���Z�̌v�Z���ʂ�؂�̂�
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }

        log.debug(STR_METHOD_NAME + "�߂�̐��Z�l " + l_dblBasePrice);

        log.exiting(STR_METHOD_NAME);

        return l_dblBasePrice;
    }

    /**
     * (calc��l�������l����)<BR>
     * ��l�̒l�����ݒl�Ŋ���؂��l�ɂȂ�悤�Ɍv�Z����B<BR>
     * <BR>
     * ���i�i��l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i��l�^���ݒl(*1)�j�~���ݒl<BR>
     * �@@(*1)�@@���Z�̌v�Z���ʂ�؂�グ<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[��l]<BR>
     * �@@�i��l�^���ݒl(*2)�j�~���ݒl<BR>
     * �@@(*2)�@@���Z�̌v�Z���ʂ�؂�̂�<BR>
     * @@param l_dblBasePrice - ��l<BR>
     * @@param l_dblTickValue - ���ݒl<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcBasePriceDeregPriceRange(double l_dblBasePrice, double l_dblTickValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcBasePriceDeregPriceRange(double, double)";
        log.entering(STR_METHOD_NAME);

        if (l_dblBasePrice < 0 || l_dblTickValue < 0 || GtlUtils.Double.isZero(l_dblTickValue))
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //��l�������l����
        double l_dblBasePriceDeregPriceRange = 0D;
        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        log.debug(STR_METHOD_NAME + "�i��l�^���ݒl�j�̗]�� = " + remainder(l_bdBasePrice, l_bdTickValue).doubleValue());
        log.debug(STR_METHOD_NAME + "���ݒl�^2 = " + l_bdTickValue.divide(
            new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

        if (remainder(l_bdBasePrice, l_bdTickValue).compareTo(
            l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
        {
            //���Z�̌v�Z���ʂ�؂�グ
            l_dblBasePriceDeregPriceRange =
                l_bdBasePrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        else
        {
            //���Z�̌v�Z���ʂ�؂�̂�
            l_dblBasePriceDeregPriceRange =
                l_bdBasePrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }

        log.debug(STR_METHOD_NAME + "�߂�̊�l�������l���� " + l_dblBasePriceDeregPriceRange);

        log.exiting(STR_METHOD_NAME);

        return l_dblBasePriceDeregPriceRange;
    }

    /**
     * (calc�l�����)<BR>
     * �Ēl�P�ʂ̒l������l���v�Z����B<BR>
     * <BR>
     * �P�j�@@�l������l�v�Z<BR>
     * �@@�l������l = ��l�{�����l��<BR>
     * <BR>
     * �Q�j�@@���ݒl�擾<BR>
     * �敨OP�v���_�N�g�}�l�[�W��.get���ݒl)�ɂč��ݒl���擾����B<BR>
     * <BR>
     * [get���ݒl()�Ɏw�肷�����]<BR>
     * �敨OP�����F�@@�敨OP����<BR>
     * �P���F�@@�i�P�j�ŎZ�o�����l������l�j<BR>
     * <BR>
     * �R�j�@@�Ēl�P�ʂ̒l������l�v�Z<BR>
     * <BR>
     * ���i�l������l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i�l������l�^���ݒl(*1)�j�~���ݒl<BR>
     * �@@(*1)�@@���Z�̌v�Z���ʂ�؂�グ�B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i�l������l�^���ݒl(*2)�j�~���ݒl<BR>
     * �@@(*2)�@@���Z�̌v�Z���ʂ�؂�̂āB<BR>
     * @@param l_dblBasePrice - ��l<BR>
     * @@param l_dblDeregPriceRange - �����l��<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4067C0720142
     */
    public double calcStopHighPrice(double l_dblBasePrice, double l_dblDeregPriceRange, WEB3IfoProductImpl l_ifoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcStopHighPrice(l_dblBasePrice,l_dblDeregPriceRange,l_ifoProduct)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdDeregPriceRange = new BigDecimal(l_dblDeregPriceRange + "");

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        if (l_ifoProduct == null || l_dblBasePrice < 0 || l_dblDeregPriceRange < 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        //�l������l
        double l_dblStopHighPrice = 0D;
        //�l������l = ��l�{�����l��
        BigDecimal l_bdStopHighPrice = l_bdBasePrice.add(l_bdDeregPriceRange);
        l_dblStopHighPrice = l_bdStopHighPrice.doubleValue();
        log.debug("�l������l = " + l_dblStopHighPrice);

        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        double l_dblTickValue = 0D; //���ݒl
        //���ݒl���擾����
        l_dblTickValue = l_productManagerImpl.getTickValue(l_ifoProduct, l_dblStopHighPrice);
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

        log.debug("���ݒl = " + l_dblTickValue);

        log.debug("�i�l������l�^���ݒl�j�̗]�� = " + remainder(l_bdStopHighPrice, l_bdTickValue).doubleValue());
        log.debug("���ݒl�^2 = " + l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

        if (l_dblTickValue <= 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        if (remainder(l_bdStopHighPrice, l_bdTickValue).compareTo(
            l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
        {
            //���Z�̌v�Z���ʂ�؂�グ
            l_bdStopHighPrice =
                l_bdStopHighPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue);
            l_dblStopHighPrice = l_bdStopHighPrice.doubleValue();
        }
        else
        {
            //���Z�̌v�Z���ʂ�؂�̂�
            l_bdStopHighPrice =
                l_bdStopHighPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue);
            l_dblStopHighPrice = l_bdStopHighPrice.doubleValue();
        }

        log.debug("�߂�̒l������l = " + l_dblStopHighPrice);
        log.exiting(STR_METHOD_NAME);

        return l_dblStopHighPrice;
    }

    /**
     * (calc�l������)<BR>
     * �Ēl�P�ʂ̒l�������l���v�Z����B<BR>
     * <BR>
     * �P�j�@@�l�������l�v�Z<BR>
     * �@@�l�������l = ��l�|�����l��<BR>
     * ���i��L�̌v�Z���� <= 0�j�̏ꍇ�A0��ԋp���ď������I������B<BR>
     * <BR>
     * �Q�j�@@���ݒl�擾<BR>
     * �敨OP�v���_�N�g�}�l�[�W��.get���ݒl()�ɂč��ݒl���擾����B<BR>
     * <BR>
     * [get���ݒl()�Ɏw�肷�����]<BR>
     * �敨OP�����F�@@�敨OP����<BR>
     * �P���F�@@�i�P�j�ŎZ�o�����l�������l�j<BR>
     * <BR>
     * �R�j�@@�Ēl�P�ʂ̒l�������l�v�Z<BR>
     * <BR>
     * ���i�l�������l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * �@@�A���A�v�Z���ʂ��}�C�i�X�l�ɂȂ����ꍇ��0��ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i�l�������l�^���ݒl(*1)�j�~���ݒl<BR>
     * �@@(*1)�@@���Z�̌v�Z���ʂ�؂�グ�B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * �@@�A���A�v�Z���ʂ��}�C�i�X�l�ɂȂ����ꍇ��0��ԋp����B<BR>
     * <BR>
     * �@@[�v�Z��]<BR>
     * �@@�i�l�������l�^���ݒl(*2)�j�~���ݒl<BR>
     * �@@(*2)�@@���Z�̌v�Z���ʂ�؂�̂āB<BR>
     * @@param l_dblBasePrice - ��l<BR>
     * @@param l_dblDeregPriceRange - �����l��<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4067CB430384
     */
    public double calcStopLowPrice(double l_dblBasePrice, double l_dblDeregPriceRange, WEB3IfoProductImpl l_ifoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcStopLowPrice(l_dblBasePrice,l_dblDeregPriceRange,l_ifoProduct)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdDeregPriceRange = new BigDecimal(l_dblDeregPriceRange + "");

        if (l_ifoProduct == null || l_dblBasePrice < 0 || l_dblDeregPriceRange < 0)
        {
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�l�������l
        double l_dblStopLowPrice = 0D;
        BigDecimal l_bdStopLowPrice = new BigDecimal("0");
        //�l�������l = ��l - �����l��
        l_bdStopLowPrice = l_bdBasePrice.subtract(l_bdDeregPriceRange);
        l_dblStopLowPrice = l_bdStopLowPrice.doubleValue();
        log.debug("�l�������l = " + l_dblStopLowPrice);

        if (l_dblStopLowPrice <= 0)
        {
            //�l�������l��0�ȉ��̏ꍇ

            l_dblStopLowPrice = 0;
        }
        else
        {
            //�l�������l��0���傫���l�̏ꍇ

            WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            double l_dblTickValue = 0D; //���ݒl
            //���ݒl���擾����
            l_dblTickValue = l_productManagerImpl.getTickValue(l_ifoProduct, l_dblStopLowPrice);
            BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");

            log.debug("���ݒl" + l_dblTickValue);
            log.debug("�i�l�������l�^���ݒl�j�̗]�� = " + remainder(l_bdStopLowPrice, l_bdTickValue).doubleValue());
            log.debug("���ݒl�^2 = " + l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP).doubleValue());

            if (l_dblTickValue <= 0)
            {
                log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

                //��O���X���[����
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
            }

            if (remainder(l_bdStopLowPrice, l_bdTickValue).compareTo(
                l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_UP)) >= 0)
            {
                //���Z�̌v�Z���ʂ�؂�グ
                l_bdStopLowPrice =
                    l_bdStopLowPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue);
                l_dblStopLowPrice = l_bdStopLowPrice.doubleValue();
            }
            else
            {
                //���Z�̌v�Z���ʂ�؂�̂�
                l_bdStopLowPrice =
                    l_bdStopLowPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue);
                l_dblStopLowPrice = l_bdStopLowPrice.doubleValue();
            }
        }

        log.debug("�߂�̒l�������l = " + l_dblStopLowPrice);
        log.exiting(STR_METHOD_NAME);

        return l_dblStopLowPrice;

        //return 0;
    }

    /**
     * (validate�l��)<BR>
     * �l���͈͓̔������`�F�b�N����B<BR>
     *  <BR>
     * �i�l������ <= �w�l <= �l������j�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00031<BR>
     * @@param l_dblLimitPrice - �w�l<BR>
     * @@param l_dblStopHighPrice - �l�����<BR>
     * @@param l_dblStopLowPrice - �l������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4067CBB6023C
     */
    public void validatePriceRange(double l_dblLimitPrice, double l_dblStopHighPrice, double l_dblStopLowPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePriceRange(l_dblLimitPrice,l_dblStopHighPrice,l_dblStopLowPrice)";
        log.entering(STR_METHOD_NAME);

        log.debug(STR_METHOD_NAME + "�l����� = " + l_dblStopHighPrice);
        log.debug(STR_METHOD_NAME + "�w�l = " + l_dblLimitPrice);
        log.debug(STR_METHOD_NAME + "�l������ = " + l_dblStopLowPrice);

        if (l_dblLimitPrice < 0 || l_dblStopHighPrice < 0 || l_dblStopLowPrice < 0 || l_dblStopHighPrice < l_dblStopLowPrice)
        {
            //�ȉ���O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_dblLimitPrice >= l_dblStopLowPrice && l_dblLimitPrice <= l_dblStopHighPrice)
        {
            //�l������ <= �w�l <= �l����� �̏ꍇ
            log.debug("�l������ <= �w�l <= �l����� �̏ꍇ");
            //�����ł�
        }
        else
        {
            //�w�l > �l����� �@@�Ɓ@@�w�l < �l�������̏ꍇ
            log.debug("�w�l > �l����� �@@�Ɓ@@�w�l < �l�������̏ꍇ");
            log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00031);
            //�ȉ���O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00031, this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �X�[�p�[�N���X�Ɏ��g�̃C���X�^���X��o�^����B<BR>
     * <BR>
     * �i�v���O�C�����������ɃR�[�������j<BR>
     * <BR>
     * ---<BR>
     * super.setInstance(this);<BR>
     * ---<BR>
     * @@roseuid 407630CF030C
     */
    public void register()
    {
        super.setInstance(this);
    }

    /**
     * (validate��������\���)<BR>
     * �����̎�����\��������Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * �ivalidateOrderForCancellation()�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃI�u�W�F�N�g�̗v�f�����A <BR>
     * �����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B <BR>
     * <BR>
     * �Q�|�P�j �����L����Ԃ̃`�F�b�N <BR>
     * �@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͎���s�Ƃ��A <BR>
     * �@@�@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00858<BR>
     * <BR>
     * �Q�|�Q�j ������Ԃ̃`�F�b�N <BR>
     * �s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B <BR>
     * <BR>
     * ==========================================================================  <BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ] <BR>
     * �@@�@@���@@�������������i������ԊǗ�.is������x�e���ԑ�( ) == false�j�̏ꍇ]  <BR>
     * <BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A <BR>
     * �@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00155<BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@ACCEPTED(*) <BR>
     * �@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@ORDERING <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@(*1)�����P��.����������"�t�w�l"�̏ꍇ�݂̂́A����\�Ƃ���B <BR>
     * <BR>
     * �@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj <BR>
     *        �̏ꍇ�͎���s�Ƃ��A��O��throw����B  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00155<BR>
     * �@@�@@�@@�������P��.����������"�t�w�l"�̏ꍇ�̂݁B <BR>
     * <BR>
     * ==========================================================================  <BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j  <BR>
     * �@@�@@���@@��������x�e���Ԓ��i������ԊǗ�.is������x�e���ԑ�( ) == true�j�̏ꍇ]  <BR>
     * <BR>
     * �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A  <BR>
     * �@@�@@�@@��O��throw����B  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * �@@�@@�@@CANCEL_ACCEPTED  <BR>
     * �@@�@@�@@CANCELLING  <BR>
     * �@@�@@�@@MODIFYING  <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * <BR>
     * �@@�@@�E������Ԃ�MODIFY_ACCEPTED�̏ꍇ�A  <BR>
     * �@@�@@�@@�@@this.validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j()���R�[������B  <BR>
     * �@@�@@�@@�@@�����ݒ�F�@@�����P�ʁD���XID  <BR>
     * <BR>
     * �@@�@@�E�敨OP�����T�[�r�X.is�s��ʒm������IN�x�e���ԑ�() == true�i���ʒm���j�̏ꍇ��  <BR>
     * �@@�@@�@@����s�Ƃ��A��O��throw����B  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * ==========================================================================  <BR>
     * �|[�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ] <BR>
     * <BR>
     *  �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A <BR>
     * �@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00155<BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@ORDERING <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * <BR>
     * ========================================================================== <BR>
     * �R�j�@@�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j  <BR>
     * �@@�@@�@@���@@�s�ꔭ���ς̒���(*2)  <BR>
     * �@@�@@�@@�̏ꍇ�̂݁A  <BR>
     * �@@�@@�@@����Ώے����̔����o�H������\���ǂ����`�F�b�N����B  <BR>
     * <BR>
     * �@@�@@(*2)�s�ꔭ���ς̒���  <BR>
     * �@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A  <BR>
     * �@@�@@�@@�@@�s�ꔭ���ς̒����Ɣ��肷��B  <BR>
     * <BR>
     * �R�|�P�j�@@�敨OP�����T�[�r�X.get������ؑ�()�ɂāA������ؑփN���X���擾����B  <BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * �@@�@@�@@��get������ؑ�()�F�����ݒ�d�l��  <BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��  <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * <BR>
     * �R�|�Q�j�@@�R�|�P�j�̖߂�l==null�̏ꍇ(*3)�́A���������ɂ��̂܂�return����B  <BR>
     * �@@�@@�@@�@@�@@�R�|�P�j�Ŏ擾�����C���X�^���X.is��������\( )==false�̏ꍇ�͎���s�Ƃ��A<BR>  
     * �@@�@@�@@�@@�@@��O��throw����B  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�@@(*3)�߂�l==null�̏ꍇ�F�ȉ����Y������B  <BR>
     * �@@�@@�@@�@@�|����Ώۂ̒����P��.�����o�H�敪=="������~"�̏ꍇ  <BR>
     * �@@�@@�@@�@@�|����Ώۂ̒����P�ʂ��A�t�����g�����Ή��s��ɑ΂��钍���ŁA  <BR>
     * �@@�@@�@@�@@�@@���@@SONAR���͒����̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�iSONAR���͒����̏ꍇ�A�����o�H�敪�ɂ�SONAR�̌o�H���ݒ肳��Ă���j<BR>
     * @@param l_order - ����<BR>
     * @@throws OrderValidationException
     * @@roseuid 40763AEB012E
     */
    public void validateOrderForCancellation(Order l_order) throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(l_order)";
        log.entering(STR_METHOD_NAME);
        if (l_order == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        //�����P�ʃI�u�W�F�N�g���擾����
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();

        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        
        //�����P�ʃI�u�W�F�N�g���擾����
        l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        //�����P�ʃI�u�W�F�N�g�̗v�f�����A
        //�����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        int l_intOrderUnitsLen = l_orderUnits.length;
        //������ԊǗ�.is�s��J�ǎ��ԑ�( )
        boolean l_result;
        try
        {
            l_result = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            log.error("",l_exp);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_exp.getErrorInfo());
        }
        WEB3IfoFrontOrderService l_frontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        for (int i = 0; i < l_intOrderUnitsLen; i++)
        {
            OrderOpenStatusEnum l_orderOpenStatusEnum = null;
            l_orderOpenStatusEnum = l_orderUnits[i].getOrderOpenStatus();

            // �����L����Ԃ̃`�F�b�N 
            //�@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͎���s�Ƃ��A 
            //�@@�@@�@@�@@��O��throw����B 
            if (!OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusEnum))
            {
                log.debug("����s�F�����L����Ԃ�OPEN�ȊO");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00858);
            }
            log.debug("�����L����ԁFOPEN");

            //������Ԃ̃`�F�b�N
            //�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B
            int l_intStatus = l_orderUnit.getOrderStatus().intValue();
            // �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ]
            if (l_result)
            {
                try
                {
                    //�������������i������ԊǗ�.is������x�e���ԑ�( ) == false�j�̏ꍇ]
                    if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                    {
                        //CANCEL_ACCEPTED CANCELLING MODIFY_ACCEPTED MODIFYING ORDERING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                            || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
                        {
                            log.debug("�Y������������s�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                        }
                        // ������Ԃ�ACCEPTED�ŁA����������"�t�w�l"�̏ꍇ�̂݁A����\�Ƃ���B
                        if (l_intStatus == OrderStatusEnum.IntValues.ACCEPTED
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("�Y������������s�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                        }
                        //�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj
                        //�@@�@@�̏ꍇ�͒����s�Ƃ��A��O��throw����B
                        if (l_orderUnitRow.getConfirmedQuantityIsNull()
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("�Y������������s�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                        }
                    }
                    //��������x�e���Ԓ��i������ԊǗ�.is������x�e���ԑ�( ) == true�j�̏ꍇ
                    else
                    {
                        //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A��O��throw����B
                        //�@@�@@�@@CANCEL_ACCEPTED CANCELLING MODIFYING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING))
                        {
                            log.debug("��������t�����Ԃł͂���܂���B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }

                        //������Ԃ�MODIFY_ACCEPTED�̏ꍇ�A
                        //this.validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j()���R�[������B
                        //�����ݒ�F�@@�����P��.���XID
                        if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                        {
                            this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(
                                l_orderUnit.getBranchId());
                        }
                        //�敨OP�����T�[�r�X.is�s��ʒm������IN�x�e���ԑ�() == true�i���ʒm���j�̏ꍇ��
                        //  �����s�Ƃ��A��O��throw����B
                        if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone(
                            (IfoOrderUnit)l_orderUnit))
                        {
                            log.debug("��������t�����Ԃł͂���܂���B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }
                    }
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(l_ex.getErrorInfo());
                }
            }
            // [�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ]
            else
            {
                //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A
                //�@@�@@�@@��O��throw����B
                if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                    || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                    || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED))
                {
                    log.debug("�Y������������s�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00155);
                }
            }
        }

        //�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j
        //�@@�@@�@@���@@�s�ꔭ���ς̒���(*2)
        //�@@�@@  �̏ꍇ�̂݁A
        //�@@  �@@�����Ώے����̔����o�H�������\���ǂ����`�F�b�N����B
        if (l_result && !l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            //�敨OP�����T�[�r�X.get������ؑ�()�ɂāA������ؑփN���X���擾����B
            WEB3GentradeOrderSwitching l_orderSwitching = null;

            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((IfoOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }

            //�߂�l==null�̏ꍇ(*3)�́A���������ɂ��̂܂�return����B
            //�@@�Ŏ擾�����C���X�^���X.is��������\( )==false�̏ꍇ�͒����s�Ƃ��A
            //�@@��O��throw����B
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���������\���)<BR>
     * �����̒������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�����P�ʃI�u�W�F�N�g�̗v�f�����A <BR>
     * �����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B <BR>
     * <BR>
     * �Q�|�P�j �����L����Ԃ̃`�F�b�N <BR>
     * �@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00858<BR>
     * <BR>
     * �Q�|�Q�j ������Ԃ̃`�F�b�N <BR>
     * �s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B <BR>
     * <BR>
     * ========================================================================== <BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ <BR>
     * �@@���@@�������������i������ԊǗ�.is������x�e���ԑ�( ) == false�j�̏ꍇ] <BR>
     * <BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@ACCEPTED(*) <BR>
     * �@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@ORDERING <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@(*1)�����P��.����������"�t�w�l"�̏ꍇ�݂̂́A�����\�Ƃ���B <BR>
     * <BR>
     * �@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj <BR>
     * �@@�@@�@@�@@�̏ꍇ�͒����s�Ƃ��A��O��throw����B  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * �@@�@@�@@�@@�������P��.����������"�t�w�l"�̏ꍇ�̂݁B <BR>
     * <BR>
     * ========================================================================== <BR>
     * �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ <BR>
     * �@@���@@��������x�e���Ԓ��i������ԊǗ�.is������x�e���ԑ�( ) == true�j�̏ꍇ] <BR>
     * <BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * <BR>
     * �@@�@@�@@�E������Ԃ�MODIFY_ACCEPTED�̏ꍇ�A <BR>
     * �@@�@@�@@�@@this.validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j()���R�[������B <BR>
     * �@@�@@�@@�@@�����ݒ�F�@@�����P��.���XID <BR>
     * <BR>
     * �@@�@@�@@�E�敨OP�����T�[�r�X.is�s��ʒm������IN�x�e���ԑ�() == true�i���ʒm���j�̏ꍇ�� <BR>
     * �@@�@@�@@�@@�����s�Ƃ��A��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * ========================================================================== <BR>
     * �|[�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ] <BR>
     * <BR>
     *  �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@ORDERING <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * <BR>
     * ========================================================================== <BR>
     * �R�j�@@�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j  <BR>
     * �@@�@@�@@���@@�s�ꔭ���ς̒���(*2)  <BR>
     * �@@�@@�@@�̏ꍇ�̂݁A  <BR>
     * �@@�@@�@@�����Ώے����̔����o�H�������\���ǂ����`�F�b�N����B  <BR>
     * <BR>
     * �@@�@@(*2)�s�ꔭ���ς̒���  <BR>
     * �@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς݂̐��ʁ�NaN�̏ꍇ�A  <BR>
     * �@@�@@�@@�@@�s�ꔭ���ς̒����Ɣ��肷��B  <BR>
     * <BR>
     * �R�|�P�j�@@�敨OP�����T�[�r�X.get������ؑ�()�ɂāA������ؑփN���X���擾����B  <BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * �@@�@@�@@��get������ؑ�()�F�����ݒ�d�l��  <BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��  <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * <BR>
     * �R�|�Q�j�@@�R�|�P�j�̖߂�l==null�̏ꍇ(*3)�́A���������ɂ��̂܂�return����B  <BR>
     * �@@�@@�@@�@@�@@�R�|�P�j�Ŏ擾�����C���X�^���X.is��������\( )==false�̏ꍇ�͒����s�Ƃ��A  <BR>
     * �@@�@@�@@�@@�@@��O��throw����B  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�@@(*3)�߂�l==null�̏ꍇ�F�ȉ����Y������B  <BR>
     * �@@�@@�@@�@@�|�����Ώۂ̒����P��.�����o�H�敪=="������~"�̏ꍇ  <BR>
     * �@@�@@�@@�@@�|�����Ώۂ̒����P�ʂ��A�t�����g�����Ή��s��ɑ΂��钍���ŁA  <BR>
     * �@@�@@�@@�@@�@@���@@SONAR���͒����̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�iSONAR���͒����̏ꍇ�A�����o�H�敪�ɂ�SONAR�̌o�H���ݒ肳��Ă���j  <BR>
     * <BR>
     * �S�j �x���󋵂̃`�F�b�N <BR>
     * �@@����.isSkip�x���󋵃`�F�b�N==false�̏ꍇ�̂݁A�ȉ��������s�Ȃ��B <BR>
     * �@@�iW�w�l�ؑ֏�������R�[�����ꂽ�ꍇ�̂݃X�L�b�v����B�j <BR>
     * <BR>
     * �@@OP�����}�l�[�W��.is�������x������() == true�ł���Β����s�Ƃ��A��O��throw����B <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00156<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * �@@�@@�@@��is�������x������()�F�����ݒ�d�l��  <BR>
     * <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�Ŏ擾���������P��  <BR>
     * �@@�@@�@@------------------------------------------------  <BR>
     * @@param l_order - (����)
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * Skip�x���󋵃`�F�b�N <BR>
     * <BR>
     * true�F�x���󋵃`�F�b�N���X�L�b�v����B�iW�w�l�ؑ֏�������R�[�����ꂽ�ꍇ�j <BR>
     * false�F�x���󋵃`�F�b�N���X�L�b�v���Ȃ��B<BR>
     * @@throws OrderValidationException
     */
    public void validateOrderForChangeability(Order l_order, boolean l_blnIsSkipDelayStatusCheck)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        {
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        //�����P�ʃI�u�W�F�N�g���擾����
        OrderUnit[] l_orderUnits = null;
        l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];

        //�����P�ʃI�u�W�F�N�g�̗v�f�����A
        //�����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        int l_intOrderUnitsLen = l_orderUnits.length;
        //������ԊǗ�.is�s��J�ǎ��ԑ�( )
        boolean l_result;
        try
        {
            l_result = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_ex.getErrorInfo());
        }
        WEB3IfoFrontOrderService l_frontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

        for (int i = 0; i < l_intOrderUnitsLen; i++)
        {
            //�����L����Ԃ̃`�F�b�N
            //�@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͒����s�Ƃ��A
            //�@@�@@�@@�@@��O��throw����
            OrderOpenStatusEnum l_orderOpenStatusEnum = null;
            l_orderOpenStatusEnum = l_orderUnits[i].getOrderOpenStatus();
            if (!OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusEnum))
            {
                //�����L����Ԃ�OPEN�ȊO�̏ꍇ
                log.debug("�����s�F�����L����Ԃ�OPEN�ȊO");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00858);
            }

            //������Ԃ̃`�F�b�N
            //�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B
            int l_intStatus = l_orderUnit.getOrderStatus().intValue();
            // �|[�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ]
            if (l_result)
            {
                try
                {
                    //�������������i������ԊǗ�.is������x�e���ԑ�( ) == false�j�̏ꍇ]
                    if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
                    {
                        //CANCEL_ACCEPTED CANCELLING MODIFY_ACCEPTED MODIFYING ORDERING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                            || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
                        {
                            log.debug("�Y�������������s�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                        }
                        // ������Ԃ�ACCEPTED�ŁA����������"�t�w�l"�̏ꍇ�̂݁A�����\�B
                        if (l_intStatus == OrderStatusEnum.IntValues.ACCEPTED
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("�Y�������������s�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                        }
                        //�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj
                        //�@@�@@�̏ꍇ�͒����s�Ƃ��A��O��throw����B
                        if (l_orderUnitRow.getConfirmedQuantityIsNull()
                            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                                l_orderUnitRow.getOrderConditionType()))
                        {
                            log.debug("�Y�������������s�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                        }
                    }
                    //��������x�e���Ԓ��i������ԊǗ�.is������x�e���ԑ�( ) == true�j�̏ꍇ
                    else
                    {
                        //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A��O��throw����B
                        //�@@�@@�@@CANCEL_ACCEPTED CANCELLING MODIFYING
                        if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                            || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                            || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING))
                        {
                            log.debug("��������t�����Ԃł͂���܂���B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }

                        //������Ԃ�MODIFY_ACCEPTED�̏ꍇ�A
                        //this.validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j()���R�[������B
                        //�����ݒ�F�@@�����P��.���XID
                        if (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                        {
                            this.validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(
                                l_orderUnit.getBranchId());
                        }
                        //�敨OP�����T�[�r�X.is�s��ʒm������IN�x�e���ԑ�() == true�i���ʒm���j�̏ꍇ��
                        //  �����s�Ƃ��A��O��throw����B
                        if (l_frontOrderService.isMarketNotifyingOrderInBreakTimeZone(
                            (IfoOrderUnit)l_orderUnit))
                        {
                            log.debug("��������t�����Ԃł͂���܂���B");
                            log.exiting(STR_METHOD_NAME);
                            throw new OrderValidationException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                        }
                    }
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(l_ex.getErrorInfo());
                }
            }
            // [�s��ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ]
            else
            {
                //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A
                //�@@�@@�@@��O��throw����B
                if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                    || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                    || (l_intStatus == OrderStatusEnum.IntValues.ORDERING)
                    || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED))
                {
                    log.debug("�Y�������������s�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00156);
                }
            }
        }

        //�s��J�ǎ��ԑсi������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j
        //�@@�@@�@@���@@�s�ꔭ���ς̒���(*2)
        //�@@�@@  �̏ꍇ�̂݁A
        //�@@  �@@�����Ώے����̔����o�H�������\���ǂ����`�F�b�N����B
        if (l_result && !l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            //�敨OP�����T�[�r�X.get������ؑ�()�ɂāA������ؑփN���X���擾����B
            WEB3GentradeOrderSwitching l_orderSwitching = null;

            try
            {
                l_orderSwitching =
                    l_frontOrderService.getOrderSwitching((IfoOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }

            //�߂�l==null�̏ꍇ(*3)�́A���������ɂ��̂܂�return����B
            //�Ŏ擾�����C���X�^���X.is��������\( )==false�̏ꍇ�͒����s�Ƃ��A
            //��O��throw����B
            if (l_orderSwitching != null && !l_orderSwitching.isChangeCancelEnable())
            {
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        //�x���󋵂̃`�F�b�N
        //�@@����.isSkip�x���󋵃`�F�b�N == false �̏ꍇ�̂݁A�ȉ��������s���B
        //�@@�iW�w�l�ؑ֏�������R�[�����ꂽ�ꍇ�̂݃X�L�b�v����j
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        if (!l_blnIsSkipDelayStatusCheck)
        {
            //OP�����}�l�[�W��.is�������x������() == true�ł���Β����s�Ƃ��A��O��throw����B
            boolean l_blnIsNotOrderedDelay = false;

            l_blnIsNotOrderedDelay =
                l_orderManager.isNotOrderedDelay((IfoOrderUnit)l_orderUnit);

            if (l_blnIsNotOrderedDelay)
            {
                log.debug("�Y�������������s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00156);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���������\���)<BR>
     * �������\�Ȓ�����Ԃ����`�F�b�N����B  <BR>
     * �ivalidateOrderForChangeability( )�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * this.validate���������\���()�ɏ������Ϗ��idelegate�j����B  <BR>
     * <BR>
     * [validate���������\���()�Ɏw�肷�����]   <BR>
     * �@@�����F�@@�p�����[�^.����   <BR>
     * �@@isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * @@param l_order - (����)<BR>
     * @@throws OrderValidationException
     * @@roseuid 40763AEB0137
     */
    public void validateOrderForChangeability(Order l_order) throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);

        //this.validate���������\���()�ɏ������Ϗ��idelegate�j����B
        this.validateOrderForChangeability(l_order, false);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�s��R�[�h)<BR>
     * �s��R�[�h�̃`�F�b�N�����{����B<BR>
     * ���݂���ꍇ�͎s��I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateMarket�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j<BR>
     * �ɂĊY������s��I�u�W�F�N�g�𐶐�����B<BR>
     * �s�ꂪ�擾�ł��Ȃ��ꍇ�́A�Y���s��Ȃ��Ɣ��f���A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00003<BR>
     * <BR>
     *  �Q�j�@@�s��I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@return Market
     * @@throws WEB3BaseException
     * @@roseuid 40763B0E0222
     */
    public Market validateMarket(String l_strMarketCode, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarket(l_strMarketCode,l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_strMarketCode = " + l_strMarketCode);
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        Market l_market = null;

        try
        {
            //throw NotFoundException
            l_market = l_gentradeFinObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME + l_nfex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (validate�����R�[�h)<BR>
     * �����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����I�u�W�F�N�g�擾<BR>
     * �敨�v���_�N�g�}�l�[�W��.get����()�ɂĐ敨OP�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * [get����()�Ɏw�肷�����]<BR>
     * �،���ЁF�@@�،���ЃR�[�h�ɊY������،���ЃI�u�W�F�N�g<BR>
     * �����R�[�h�F�@@�����R�[�h<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@throws WEB3BaseException
     * @@roseuid 407640B3028F
     */
    public WEB3IfoProductImpl validateProductCode(String l_strProductCode, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_strProductCode = " + l_strProductCode);
        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�敨�v���_�N�g�}�l�[�W���I�u�W�F�N�g���擾����
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        //�،���ЃI�u�W�F�N�g���擾����
        Institution l_institution = null;
        try
        {
            l_institution = l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);

            //�敨OP�����I�u�W�F�N�g���擾����
            WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_strProductCode);

            log.exiting(STR_METHOD_NAME);
            return l_ifoProductImpl;
        }
        catch (NotFoundException l_nfex)
        {
            //�،���Б��݂��Ȃ��ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00301);
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate����\������z)<BR>
     * ����������A��ЁE���X�ň�x�Ɏ���\��<BR>
     * ������z�𒴂��Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�����́u�����^�C�v�v�A�u�敨/�I�v�V�����敪�v���A����\����l�����肷��B<BR>
     *      �E�����^�C�v���l�A�J�E���g �̏ꍇ<BR>
     *      �敨/�I�v�V�����敪���h�I�v�V�����h�̏ꍇ�F ���X.����\���z����l�i�l�EOP�j<BR>
     *      �敨/�I�v�V�����敪���h�敨�h�̏ꍇ�F      ���X.����\���z����l�i�l�E�敨�j<BR>
     *      �E�����^�C�v�����p�A�J�E���g�A�@@�l�A�J�E���g �̏ꍇ<BR>
     *      �敨/�I�v�V�����敪���h�I�v�V�����h�̏ꍇ�F ���X.����\���z����l�i�@@�l�EOP�j<BR>
     *      �敨/�I�v�V�����敪���h�敨�h�̏ꍇ�F      ���X.����\���z����l�i�@@�l�E�敨�j<BR>
     *      �������^�C�v�A�敨/�I�v�V�����敪����L�ȊO�̏ꍇ�F ��O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00149<BR>
     * <BR>
     * �Q�j�@@�i�P�j�Ō��肵������\���z����l�@@���@@��������j�̏ꍇ�A<BR>
     * ����\����l�𒴉߂��Ă���Ɣ��f���A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00161<BR>
     * @@param l_branch - ���X<BR>
     * ���X�I�u�W�F�N�g<BR>
     *
     * @@param l_dblTurnover - ����������w�肷��B<BR>
     * @@param l_mainAccountType - �����^�C�v
     * @@param String - �敨�^�I�v�V�����敪<BR>
     * 0�FDEFAULT�i�敨�I�v�V�����ȊO�j
     * 1�F�敨
     * 2�F�I�v�V����
     * @@throws WEB3BaseException
     * @@roseuid 40765EA0009B
     */
    public void validateOrderMaxAmount(WEB3GentradeBranch l_branch, double l_dblTurnover, MainAccountTypeEnum l_mainAccountType, String l_futuresOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderMaxPrice(l_branch,l_market,l_dblTurnover,l_mainAccountType)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_mainAccountType == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("���X = " + l_branch.getBranchId());
        log.debug("������� = " + l_dblTurnover);
        log.debug("�����^�C�v = " + l_mainAccountType);

        long l_dblOrderMaxPrice = 0L;
        BranchRow l_branchRow = (BranchRow) l_branch.getDataSourceObject();

        //�u�����^�C�v�v���A����\����l�����肷��
        if (MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountType))
        {
            //�����^�C�v���l�A�J�E���g �̏ꍇ
            log.debug("�����^�C�v���l�A�J�E���g �̏ꍇ");
            //����\���z����l�i�l�j���擾����
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceIndOptionIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceIndOption();
                }
            }
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceIndFutureIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceIndFuture();
                }
            }

        }
        else if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountType) || MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountType))
        {
            //�����^�C�v�����p�A�J�E���g�A�@@�l�A�J�E���g �̏ꍇ
            log.debug("�����^�C�v�����p�A�J�E���g�A�@@�l�A�J�E���g �̏ꍇ");
            //���X.����\���z����l�i�@@�l�j
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceCorpOptionIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceCorpOption();
                }
            }
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_futuresOptionDiv))
            {
                if (l_branchRow.getMaxHandlingPriceCorpFutureIsNull() == false)
                {
                    l_dblOrderMaxPrice = l_branchRow.getMaxHandlingPriceCorpFuture();
                }
            }
        }
        else
        {
            //�����^�C�v����L�ȊO�̏ꍇ
            log.debug("�����^�C�v����L�ȊO�̏ꍇ");
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00149);
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00149, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("����\���z����l = " + l_dblOrderMaxPrice);
        if (l_dblOrderMaxPrice < l_dblTurnover)
        {
            //����\���z����l�@@���@@��������̏ꍇ
            log.debug("����\���z����l�@@���@@��������̏ꍇ");
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00161);
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00161, getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��������)<BR>
     * ���������̃`�F�b�N���s���B<BR>
     * �@@�|���s�����̃`�F�b�N<BR>
     * �@@�|���������̃`�F�b�N<BR>
     * �@@�|���ꎷ�s�����戵��~�`�F�b�N<BR>
     * �@@�|�o����܂Œ����̃`�F�b�N<BR>
     * �@@�|���s�K���`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jvalidate���������v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�戵�\���������擾<BR>
     * �戵�\���������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * [�R���X�g���N�^�̈���]<BR>
     * �،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * �����^�C�v�F�@@ProductTypeEnum.�h�敨�I�v�V�����h<BR>
     * �敨�^�I�v�V�����敪�F�@@<BR>
     * �@@�敨OP�������.get����().�敨�^�I�v�V�����敪<BR>
     * �@@<BR>
     * �Q�j�@@���s�����̃`�F�b�N<BR>
     * �iis�戵�\���s����() == false�j�̏ꍇ�A<BR>
     * �戵�\�Ȏ��s�����ł͂Ȃ��Ɣ��肵<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00150<BR>
     * <BR>
     * �R�j�@@���������̃`�F�b�N<BR>
     * �iis�戵�\��������() == false�j�̏ꍇ�A<BR>
     * �戵�\�Ȕ��������ł͂Ȃ��Ɣ��肵<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00151<BR>
     * <BR>
     * �S�j�@@���ꎷ�s�����戵��~�̃`�F�b�N<BR> 
     * ���ꎷ�s�����戵��~�e�[�u�����������A <BR>
     * �Ώۂ̏��i�A�����������h��~���h�������ꍇ�A<BR> 
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02433<BR>
     * <BR>
     * �T�j�@@�o����܂Œ����̃`�F�b�N<BR>
     * ���i����.���������敪���o����܂Œ����j�̏ꍇ�̂ݎ��{����B<BR>
     * �T�|�P�j�@@�����戵�\�`�F�b�N<BR>
     * �iis�o����܂Œ����戵�\() == false�j�̏ꍇ�A<BR>
     * �o����܂Œ����戵���s�Ɣ��肵�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00151<BR>
     * �T�|�Q�j�@@�������̃`�F�b�N�i��������������null�j<BR>
     * �iis�o����܂Œ����\�� == false�j�̏ꍇ�A<BR>
     * �s���ȓ��t���w�肳�ꂽ�Ɣ��肵�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00152<BR>
     * �T�|�R�j�@@�������̃`�F�b�N�i��������������null�j<BR>
     * �iis�o����܂Œ����\�� == false�j�̏ꍇ�A<BR>
     * �s���ȓ��t���w�肳�ꂽ�Ɣ��肵�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00152<BR>
     * �T�|�S�j�@@�����ŏI���̃`�F�b�N<BR>
     * �i���������� > �敨OP�������.�����ŏI���j�̏ꍇ�A<BR>
     * ���������G���[�Ɣ��肵�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00152<BR>
     * �T�|�T�j�@@�����J�z�X�L�b�v�����`�F�b�N<BR>
     * �iis�J�z�X�L�b�v����() == true�j�̏ꍇ�A<BR>
     * �����J�z�X�L�b�v�����̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00684<BR>
     * <BR>
     * �U�j�@@���s�K���`�F�b�N<BR>
     * �����s�w��(*1)�̏ꍇ�̂ݎ��{<BR>
     * �iis���s�����\() == false�j�̏ꍇ�A<BR>
     * ���s�K�����Ɣ��肵�A��O���X���[����B<BR>
     * <BR>
     * (*1)���s�w��̔���<BR>
     * �P�jis���s==true<BR>
     * �Q�j�iis���s==false�j�����i����.���s����==�s�o���������s�j<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00154<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_blnIsMarketOrder - (is���s)<BR>
     * �iisMarketOrder�j<BR>
     * ���s���w�肳�ꂽ���ǂ����̔���B<BR>
     * ���s�̏ꍇtrue�A�w�l�̏ꍇfalse�B<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)
     * �敨OP��������I�u�W�F�N�g
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * �iisOpenContract�j<BR>
     * �V�K��������ǂ����̔���B<BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B<BR>
     *
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * �iisBuyToOpenOrder�j<BR>
     * ����������ǂ����̔���B<BR>
     * �����̏ꍇtrue�A�����̏ꍇfalse�B<BR>
     * @@param l_datOrderBizDate - ������������<BR>
     * @@param l_datExpirationDate - ����������<BR>
     * @@param l_strOrderCond - ��������<BR>
     * �@@0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l<BR>
     * @@param l_executionConditionType - ���s����<BR>
     * @@param l_strExpirationDateType - ���������敪<BR>
     * @@param l_firstOrderUnitId - ���񒍕��̒����P��ID<BR>
     * ���񒍕��̒����P��ID�B<BR>
     * �i�J�z���ɂ̂ݐݒ肳���B�ȊO��null�j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40766AB1029F
     */
    public void validateOrderCond(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        boolean l_blnIsMarketOrder,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyToOpenOrder,
        Date l_datOrderBizDate,
        Date l_datExpirationDate,
        String l_strOrderCond,
        IfoOrderExecutionConditionType l_executionConditionType,
        String l_strExpirationDateType,
        Long l_firstOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCond(WEB3GentradeSubAccount,long,boolean,"
                + "WEB3IfoTradedProductImpl,boolean,boolean,Date,Date,"
                + "String,IfoOrderExecutionConditionType,String,Long)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_ifoTradedProduct == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("�،���ЃR�[�h = " + l_subAccount.getInstitution().getInstitutionCode());
        log.debug("�����̒����P��ID = " + l_lngOrderUnitId);
        log.debug("is���s = " + l_blnIsMarketOrder);
        log.debug("�敨OP�������.�������ID = " + l_ifoTradedProduct.getTradedProductId());
        log.debug("is�V�K�� = " + l_blnIsOpenContract);
        log.debug("is���� = " + l_blnIsBuyToOpenOrder);
        log.debug("������������ = " + l_datOrderBizDate);
        log.debug("���������� = " + l_datExpirationDate);
        log.debug("�������� = " + l_strOrderCond);
        log.debug("���s���� = " + l_executionConditionType);
        log.debug("���������敪 = " + l_strExpirationDateType);

        IfoProductRow l_ifoProductRow = (IfoProductRow) (
            (WEB3IfoProductImpl) l_ifoTradedProduct.getProduct()).getDataSourceObject();

        //�P�j�@@�戵�\���������擾
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                l_ifoProductRow.getFutureOptionDiv(),
                WEB3MarginTradingDivDef.DEFAULT);

        //�Q�j�@@���s�����̃`�F�b�N
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionConditionType))
        {
            log.debug("is�戵�\���s����() == false�̏ꍇ");
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("is�戵�\���s����() == true�̏ꍇ");

        //�R�j�@@���������̃`�F�b�N
        if (!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderCond))
        {
            log.debug("is�戵�\��������() == false�̏ꍇ");
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("is�戵�\��������() == true�̏ꍇ");

        //�S�jvalidate���ꎷ�s�����戵��~()
        this.validateTriggerOrderStop(
            l_subAccount,
            l_lngOrderUnitId,
            l_strOrderCond,
            l_ifoProductRow.getFutureOptionDiv()
            );

        //�@@�������蒍���̏ꍇ 
        if(l_strExpirationDateType.equals(WEB3OrderExpirationDateTypeDef.DAY_LIMIT))
        {
            log.debug("�������蒍���̏ꍇ");
        }
        //�@@�o����܂Œ����̏ꍇ 
        else if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            log.debug("�o����܂Œ����̏ꍇ");
            //�T�j�@@�o����܂Œ����̃`�F�b�N

            //set����ŏI��()
            l_handlingOrderCond.setTradingEndDate(l_ifoTradedProduct.getLastTradingDate());

            //is�o����܂Œ����戵�\<����ŏI���l����>( )
            if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering())
            {
                log.debug("is�o����܂Œ����戵�\() == false�̏ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                    getClass().getName() + STR_METHOD_NAME);
            }

            log.debug("is�o����܂Œ����戵�\() == true�̏ꍇ");

            //�T�|�Q�j�A�T�|�R�j�@@�������̃`�F�b�N
            
            // ������������ != null �̏ꍇ
            if (l_datOrderBizDate != null)
            {
                log.debug("������������ != null");
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate, l_datOrderBizDate))
                {
                    log.debug("is�o����܂Œ����\�� == false�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00152,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            // ������������ == null �̏ꍇ
            else
            {
                log.debug("������������ == null");
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate))
                {
                    log.debug("is�o����܂Œ����\�� == false�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00152,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            log.debug("is�o����܂Œ����\�� == true�̏ꍇ");

            //�T�|�S�j�@@�����ŏI���̃`�F�b�N
            
            //���������� > �敨OP�������.�����ŏI���̏ꍇ�A���������G���[
            if (WEB3DateUtility.compareToDay(l_datExpirationDate, l_ifoTradedProduct.getLastTradingDate()) > 0)
            {
                log.debug("���������� > �敨OP�������.�����ŏI���̏ꍇ"); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00152,
                    getClass().getName() + STR_METHOD_NAME);
            }
            log.debug("���������� <= �敨OP�������.�����ŏI���̏ꍇ");    

            //�T�|�T�j�@@�����J�z�X�L�b�v�����`�F�b�N
            if (l_lngOrderUnitId == 0)
            {
                //�V�K�����̏ꍇ�̂݃`�F�b�N����B(�p�����[�^.�����P��ID == 0)
                if (l_ifoTradedProduct.isCarryOverSkipProduct())
                {
                    log.debug("is�J�z�X�L�b�v����() == true�̏ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00684,
                        getClass().getName() + STR_METHOD_NAME);
                }
                log.debug("is�J�z�X�L�b�v����() == false�̏ꍇ");
            }
        }

        //validate�[��܂Œ����戵�\(boolean, String, long, Long)
        this.validateEveningSessionOrderPossibleHandling(
            l_handlingOrderCond.isEveningSessionOrderPossibleHandling(),
            l_strExpirationDateType,
            l_lngOrderUnitId,
            l_firstOrderUnitId);

        //validate�����ŏI��<�[��>(�敨OP�������, String)
        this.validateEveningSessionLastTradingDate(l_ifoTradedProduct, l_strExpirationDateType,l_datOrderBizDate);

        //���s�A�܂��́A�s�o���������s�̏ꍇ
        if ((l_blnIsMarketOrder)||
            ((!l_blnIsMarketOrder)&&l_executionConditionType.equals(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED)))
        {
            log.debug("���s�A�܂��́A�s�o���������s�̏ꍇ");
            
            //�U�j�@@���s�K���`�F�b�N
            if (!l_handlingOrderCond.isMarketOrderPossible(l_blnIsOpenContract, l_blnIsBuyToOpenOrder))
            {
                log.debug("is���s�����\() == false�̏ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00154,
                    getClass().getName() + STR_METHOD_NAME);
            }
            log.debug("is���s�����\() == true�̏ꍇ");
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get���s���v�Z�P��)<BR>
     * ���s���̌v�Z�P�����擾����B<BR>
     * <BR>
     * �葱���ɂ��ẮA<BR>
     * �u��{�݌v�v�Z�����i�����w��OP�j.doc�v�@@6.���s���v�Z�P���Q�ƁB<BR>
     * @@param l_ifoTradedProduct - �敨OP��������I�u�W�F�N�g
     * @@param l_branch - ���X�I�u�W�F�N�g
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4076AB7C0118
     */
    public double getMakeOrderCalcUnitPrice(WEB3IfoTradedProductImpl l_ifoTradedProduct, WEB3GentradeBranch l_branch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMakeOrderCalcUnitPrice(l_ifoTradedProduct,l_branch)";

        log.entering(STR_METHOD_NAME);
        //�p�����[�^���`�F�b�N
        if (l_ifoTradedProduct == null || l_branch == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("�敨OP��������I�u�W�F�N�g = " + l_ifoTradedProduct.getTradedProductId());
        log.debug("���X�I�u�W�F�N�g = " + l_branch.getBranchId());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�P��
        double l_dblUnitPrice = 0D;

        //���XID���擾����
        long l_lngBranchID = 0L;
        l_lngBranchID = l_branch.getBranchId();

        //�敨OP�������擾����
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl) l_ifoTradedProduct.getProduct();

        //�萔�����i�R�[�h���擾����
        String l_strCommissionProductCode = null;
        l_strCommissionProductCode = l_ifoProductImpl.getCommissionProductCode();
        log.debug("�萔�����i�R�[�h = " + l_strCommissionProductCode);

        //�@@ �v�Z�����擾
        //��Е��X���i�e�[�u���I�u�W�F�N�g
        InstBranchProductRow l_instBranchProductRow = null;

        //��Е��X���i�I�u�W�F�N�g���擾����
        try
        {
            //throw DataFindException,DataQueryException,DataNetworkException
            l_instBranchProductRow = InstBranchProductDao.findRowByPk(l_lngBranchID, l_strCommissionProductCode);

            //OP�����}�l�[�W���I�u�W�F�N�g���擾����
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            if (l_instBranchProductRow.getEstimatePriceCalcFormIsNull() == false)
            {

                //�T�Z���z�v�Z����������ꍇ
                log.debug("�T�Z���z�v�Z����������ꍇ");
                log.debug("�T�Z���z�v�Z���� = " + l_instBranchProductRow.getEstimatePriceCalcForm());

                if (WEB3PremiumRestraintRateDef.PREMIUM_RESTRANT == l_instBranchProductRow.getEstimatePriceCalcForm())
                {
                    //�T�Z���z�v�Z���� = �g�����S�������h�̏ꍇ
                    log.debug("�T�Z���z�v�Z���� = �g�����S�������h�̏ꍇ");
                    //�������擾����
                    l_dblUnitPrice = l_ifoProductManagerImpl.getCurrentPrice(l_ifoTradedProduct);
                }
                else if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_instBranchProductRow.getEstimatePriceCalcForm())
                {
                    //�T�Z���z�v�Z���� = �gSTOP���S�������h�̏ꍇ
                    log.debug("�T�Z���z�v�Z���� = �gSTOP���S�������h�̏ꍇ");
                    //STOP���Z�o��l���擾
                    double l_dblStopQuantityBasePrice = 0D;

                    if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() == true)
                    {
                        //�ꒆ�̏ꍇ
                        log.debug("�ꒆ�̏ꍇ");
                        //�敨OP�������.��l�i�I�l�j ��STOP���Z�o��l�Ƃ���
                        l_dblStopQuantityBasePrice = ((IfoTradedProductRow) l_ifoTradedProduct.getDataSourceObject()).getLastClosingPrice();
                    }
                    else
                    {
                        //������̏ꍇ
                        log.debug("������̏ꍇ");
                        //�敨OP�v���_�N�g�}�l�[�W��.get����()(*1)�ɂĎ������擾���ASTOP���Z�o��l�Ƃ���
                        l_dblStopQuantityBasePrice = l_ifoProductManagerImpl.getCurrentPrice(l_ifoTradedProduct);
                    }

                    //STOP���Z�o

                    //�����l�����擾
                    double l_dblDeregulatedPriceRange = 0D;
                    l_dblDeregulatedPriceRange = l_ifoProductManagerImpl.getDeregulatedPriceRange(l_ifoProductImpl, l_dblStopQuantityBasePrice);
                    log.debug("�����l�� = " + l_dblDeregulatedPriceRange);

                    //���ݒl���擾
                    double l_dblTickValue = 0D;
                    l_dblTickValue = l_ifoProductManagerImpl.getTickValue(l_ifoProductImpl, l_dblStopQuantityBasePrice);
                    log.debug("���ݒl = " + l_dblTickValue);

                    //�l����l���擾
                    double l_dblBasePrice = 0D;
                    l_dblBasePrice = this.calcBasePrice(l_dblStopQuantityBasePrice, l_dblTickValue);
                    log.debug("�l����l = " + l_dblBasePrice);

                    //�l������iSTOP���j�Z�o
                    l_dblUnitPrice = this.calcStopHighPrice(l_dblBasePrice, l_dblDeregulatedPriceRange, l_ifoProductImpl);
                    log.debug("�l������iSTOP���j = " + l_dblUnitPrice);
                }
                else
                {
                    //��O���X���[����
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, getClass().getName() + STR_METHOD_NAME);
                }
            }
            else
            {
                //��O���X���[����
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (DataFindException l_dfe)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblUnitPrice;
    }

    /**
     * (validate�����h�c)<BR>
     * �����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����I�u�W�F�N�g�擾<BR>
     * �敨�v���_�N�g�}�l�[�W��.getProduct()<BR>
     * �ɂĐ敨OP�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * [getProduct()�Ɏw�肷�����]<BR>
     * �����h�c�F�@@�����R�[�h<BR>
     * @@param l_lngProductID - �����h�c<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@throws WEB3BaseException
     * @@roseuid 407CDF9D0138
     */
    public WEB3IfoProductImpl validateProductID(long l_lngProductID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�敨�v���_�N�g�}�l�[�W���I�u�W�F�N�g���擾����
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;
        try
        {
            l_ifoProductImpl = (WEB3IfoProductImpl) l_ifoProductManagerImpl.getProduct(l_lngProductID);
        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, getClass().getName() + STR_METHOD_NAME);
        }

        return l_ifoProductImpl;
    }

    /**
     * (validate�s��h�c)<BR>
     * �s��̃`�F�b�N�����{����B<BR>
     * ���݂���ꍇ�͎s��I�u�W�F�N�g��ԋp����B<BR>
     * �ivalidateMarket�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j<BR>
     * �ɂĊY������s��I�u�W�F�N�g�𐶐�����B<BR>
     * �s�ꂪ�擾�ł��Ȃ��ꍇ�́A�Y���s��Ȃ��Ɣ��f���A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00003<BR>
     * <BR>
     * �Q�j�@@�s��I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lngMarketID - �s��h�c<BR>
     * @@return Market
     * @@throws WEB3BaseException
     * @@roseuid 407CE7AD033C
     */
    public Market validateMarketID(long l_lngMarketID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMarketID(l_lngMarketID)";
        log.entering(STR_METHOD_NAME);

        log.debug("l_lngMarketID = " + l_lngMarketID);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Market l_market = null;
        try
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketID);

        }
        catch (NotFoundException l_nfex)
        {
            //�s�ꂪ�擾�ł��Ȃ��ꍇ
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + STR_METHOD_NAME,
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
        }

        return l_market;
    }

    /**
     * (validate�������e)<BR>
     * �������͒l���Ó��ł��邩���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���ʂ̃`�F�b�N<BR>
     * isChange����()���R�[�����A<BR>
     * ���ʂɒ��������������𔻒�A�������ʂ̑Ó������`�F�b�N����B<BR>
     * <BR>
     * [isChange����()�Ɏw�肷�����]<BR>
     * �����P�ʁF�@@�����P��<BR>
     * �������ʁF�@@��������<BR>
     * <BR>
     * �Q�j�@@�����P���̔���<BR>
     * isChange�P��()���R�[�����A<BR>
     * �����P���ɒ��������������𔻒肷��B<BR>
     * <BR>
     * [isChange�P��()�Ɏw�肷�����]<BR>
     * �����P�ʁF�@@�����P��<BR>
     * �����w�l�F�@@�����w�l<BR>
     * <BR>
     * �R�j�@@���s�����̔���<BR>
     * isChange���s����()���R�[�����A<BR>
     * ���s�����ɒ��������������𔻒肷��B<BR>
     * <BR>
     * [isChange���s����()�Ɏw�肷�����]<BR>
     * �����P�ʁF�@@�����P��<BR>
     * �������s�����F�@@�������s����<BR>
     * <BR>
     * �S�j�@@���������`�F�b�N <BR>
     * �i���ʂƒ����P���܂��͎��s������������Ă���ꍇ�̂݁j <BR>
     * <BR>
     * �@@�S�|�P�j��������x�e���ԑ�(*1)���O�ꔭ���ϒ���(*2)�̏ꍇ <BR>
     * <BR>
     * �@@�s��ɒʒm�ς݂̒l���g�p���A�����������ł��邩�ǂ������肷��B <BR>
     * <BR>
     * �@@�i�����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@!=�@@�������ʁj && <BR>
     * �@@�i�����P��.�s�ꂩ��m�F�ς݂̎w�l�@@!=�@@�����w�l�j �܂��� <BR>
     * �@@�i�����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@!=�@@�������ʁj && <BR>
     * �@@�i�����P��.�s�ꂩ��m�F�ς݂̎��s�����@@!=�@@�������s�����j�̏ꍇ�A <BR>
     * �����������Ɣ��肷��B <BR>
     * <BR>
     * �@@�@@(*1)��������x�e���ԑт̏ꍇ <BR>
     * �@@�@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B <BR>
     * <BR>
     * �@@�@@(*2)�O�ꔭ���ϒ��� <BR>
     * �@@�@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς̐��� != NaN�̏ꍇ�́A�O�ꔭ���ϒ����ł���Ɣ��肷��B <BR>
     * <BR>
     * �@@�S�|�Q�j�@@�S�|�P�j�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�ithis.isChange����()�@@==�@@true�j�@@&&�@@�ithis.isChange�P��()�@@==�@@true�j�A�܂��́A <BR>
     * �@@�ithis.isChange����()�@@==�@@true�j�@@&&�@@�ithis.isChange���s����()�@@==�@@ture�j�̏ꍇ�A<BR>
     * �@@�����������Ɣ��肷��B <BR>
     * <BR>
     * �@@�S�|�R�j�@@�S�|�P�j�܂��͂S�|�Q�j�Łu���������v�Ɣ��肳�ꂽ�ꍇ�A <BR>
     * �ȉ��������s�Ȃ��B <BR>
     * �@@�����P��.�s��h�c�ɊY������s��I�u�W�F�N�g���擾����B <BR>
     * �@@�擾�����s��.���������\�敪==�h�������ړ��������s�h�̏ꍇ <BR>
     * �@@��O���X���[����B <BR>
     * �@@�@@class :  WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@ :  BUSINESS_ERROR_00687<BR>
     * �@@���x�e���ԑс^�x�e���ԑшȊO�ŃG���[���b�Z�[�W�𕪂���B<BR>
     * �T�j�@@������������������Ă��Ȃ����Ƃ̃`�F�b�N <BR>
     * isChange��������()���R�[�����A<BR>
     * ���������ɒ��������������𔻒肷��B <BR>
     *<BR>
     * [isChange��������()�Ɏw�肷�����] <BR>
     * �����P�ʁF�@@�����P�� <BR>
     * �������������F�@@������������ <BR>
     *<BR>
     *<BR>
     * �U�j�@@���������敪����������Ă��Ȃ����Ƃ̃`�F�b�N  <BR>
     * �������̒��������敪(*)������.�������������敪�̏ꍇ <BR>
     * ��O���X���[���邱�ƁB <BR>
     * <BR>
     *  (*)�������̒��������敪 <BR>
     *�@@ �@@�敨OP�f�[�^�A�_�v�^.get���������敪()���R�[������B <BR>
     *<BR>
     * �@@�@@[get���������敪()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@����.�����P�� <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_02102 <BR>
     *<BR>
     * �V�j�@@�i�����ς݂́j�t�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N <BR>
     * isChange�t�w�l����()���R�[�����A <BR>
     * �t�w�l�����ɒ��������������𔻒肷��B <BR>
     *<BR>
     * [isChange�t�w�l����()�Ɏw�肷�����] <BR>
     * �����P�ʁF�@@�����P�� <BR>
     * �������������F�@@������������ <BR>
     * ���������������Z�q�F�@@���������������Z�q <BR>
     * �����t�w�l��l�^�C�v�F�@@�����t�w�l��l�^�C�v <BR>
     * �����t�w�l��l�F�@@�����t�w�l��l <BR>
     *<BR>
     *<BR>
     * �W�j�@@W�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N <BR>
     * isChangeW�w�l����()���R�[�����A <BR>
     * W�w�l�����ɒ��������������𔻒肷��B<BR>
     *<BR>
     * [isChangeW�w�l����()�Ɏw�肷�����] <BR>
     * �����P�ʁF�@@�����P�� <BR>
     * �������������F�@@������������ <BR>
     * ���������������Z�q�F�@@���������������Z�q <BR>
     * �����t�w�l��l�^�C�v�F�@@�����t�w�l��l�^�C�v <BR>
     * �����t�w�l��l�F�@@�����t�w�l��l <BR>
     * �����iW�w�l�j�����w�l�F�@@�����iW�w�l�j�����w�l <BR>
     * �����iW�w�l�j���s�����F�@@�����iW�w�l�j���s���� <BR>
     *<BR>
     * �X�j�@@�����������t����������Ă��Ȃ����Ƃ̃`�F�b�N <BR>
     * isChange����������()���R�[�����A <BR>
     * �����������t�ɒ��������������𔻒肷��B <BR>
     *<BR>
     * [isChange����������()�Ɏw�肷�����] <BR>
     * �����P�ʁF�@@�����P�� <BR>
     * ���������������F�@@�������������� <BR>
     *<BR>
     * �P�O�j�@@�ԍϐ��ʓ��󂪒�������Ă��Ȃ����Ƃ̃`�F�b�N <BR>
     * isChange�ԍϐ��ʓ���()���R�[�����A <BR>
     * �ԍϐ��ʓ���ɒ��������������𔻒肷��B <BR>
     *<BR>
     * [isChange�ԍϐ��ʓ���()�Ɏw�肷�����] <BR>
     * �����P�ʁF�@@�����P�� <BR>
     * �����ԍό��ʃG���g���F�@@�����ԍό��ʃG���g�� <BR>
     *<BR>
     * �P�P�j�@@�����������Ă��邩�̃`�F�b�N <BR>
     * isChange����()�AisChange�P��()�A<BR>
     * isChange���s����()�AisChange�t�w�l����()�A <BR>
     * isChangeW�w�l����()�AisChange����������()�A <BR>
     * isChange�ԍϐ��ʓ���()<BR>
     * �̂��ׂĂ�false��ԋp�����ꍇ�A <BR>
     *�@@ �������������牽����������Ă��Ȃ��Ɣ��f���A<BR>
     *�@@ �u�������͂Ȃ��v�̗�O���X���[����B <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00039 <BR>
     *<BR>
     * @@param l_orderUnit - �����P��<BR>
     * �������i�����������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_dblQuantityAfterChange - ��������
     * @@param l_dblLimitPrice - �����w�l
     * @@param l_executionConditionType - �������s����
     * @@param l_strOrderConditionType - ������������
     * @@param l_strOrderCondOperator - ���������������Z�q
     * @@param l_strStopPriceType - �����t�w�l��l�^�C�v
     * @@param l_dblStopPrice - �����t�w�l��l
     * @@param l_dblWStopPrice - �����iW�w�l�j�����w�l
     * @@param l_wLimitExecCondType - �����iW�w�l�j���s����
     * @@param l_datExpriationDate - ��������������
     * @@param l_strExpirationDateType - �������������敪
     * @@param l_modifiedSettleContractEntries - �����ԍό��ʃG���g��
     * @@throws WEB3BaseException
     * @@roseuid 407CF81803DC
     */
    public void validateOrderChangeSpec(
        OrderUnit l_orderUnit,
        double l_dblQuantityAfterChange,
        double l_dblLimitPrice,
        IfoOrderExecutionConditionType l_executionConditionType,
        String l_strOrderConditionType,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopPrice,
        double l_dblWStopPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        Date l_datExpriationDate,
        String l_strExpirationDateType,
        SettleContractEntry[] l_modifiedSettleContractEntries)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderChangeSpec(OrderUnit, double,double,IfoOrderExecutionConditionType," +
                "String ,String ,String ,double ,double ,Date ,String ,SettleContractEntry[] )";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        //�P�j�@@���ʂ̃`�F�b�N 
        boolean l_blnIsChangeQuantity = 
            this.isChangeQuantity(l_orderUnit, l_dblQuantityAfterChange);
        
        //�Q�j�@@�����P���̔��� 
        //isChange�P��()���R�[�����A 
        //�����P���ɒ��������������𔻒肷��B 
        boolean l_blnIsChangeUnitPrice = 
            this.isChangeUnitPrice(l_orderUnit, l_dblLimitPrice);
        
        //�R�j�@@���s�����̔��� 
        boolean l_blnIsChangeExecCondType = 
            this.isChangeExecCondType(l_orderUnit, l_executionConditionType);

        //���������`�F�b�N
        //�i���ʂƒ����P���܂��͎��s������������Ă���ꍇ�̂݁j
        //��������x�e���ԑ�(*1)���O�ꔭ���ϒ���(*2)�̏ꍇ
        //�s��ɒʒm�ς݂̒l���g�p���A�����������ł��邩�ǂ������肷��B
        //  �i�����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@!=�@@�������ʁj &&
        //�@@�i�����P��.�s�ꂩ��m�F�ς݂̎w�l�@@!=�@@�����w�l�j �܂���
        //�@@�i�����P��.�s�ꂩ��m�F�ς݂̐��ʁ@@!=�@@�������ʁj &&
        //�@@�i�����P��.�s�ꂩ��m�F�ς݂̎��s�����@@!=�@@�������s�����j�̏ꍇ�A
        //�����������Ɣ��肷��B
        //    (*1)��������x�e���ԑт̏ꍇ
        //�@@�@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ�́A�x�e���ԑтł���Ɣ��肷��B
        //�@@�@@(*2)�O�ꔭ���ϒ���
        //�@@�@@�@@�@@�@@�����P��.�s�ꂩ��m�F�ς̐��� != NaN�̏ꍇ�́A�O�ꔭ���ϒ����ł���Ɣ��肷��B
        boolean l_blnIsSameTimeChange = false;
        boolean l_blnIsTradeCoseTimeZone =
            WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_blnIsTradeCoseTimeZone && !l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            if ((l_orderUnitRow.getConfirmedQuantity() != l_dblQuantityAfterChange
                && l_orderUnitRow.getConfirmedPrice() != l_dblLimitPrice)
                || (l_orderUnitRow.getConfirmedQuantity() != l_dblQuantityAfterChange
                && l_orderUnitRow.getExecutionConditionType() != l_executionConditionType))
            {
                l_blnIsSameTimeChange = true;
            }
        }
        //�ȊO�̏ꍇ
        //�ithis.isChange����()�@@==�@@true�j�@@&&�@@�ithis.isChange�P��()�@@==�@@true�j�A�܂��́A
        //�@@�ithis.isChange����()�@@==�@@true�j�@@&&�@@�ithis.isChange���s����()�@@==�@@ture�j�̏ꍇ�A
        //�@@�����������Ɣ��肷��B
        else if ((this.isChangeQuantity(l_orderUnit, l_dblQuantityAfterChange)
            && this.isChangeUnitPrice(l_orderUnit, l_dblLimitPrice))
            || (this.isChangeQuantity(l_orderUnit, l_dblQuantityAfterChange)
            && this.isChangeExecCondType(l_orderUnit, l_executionConditionType)))
        {
            l_blnIsSameTimeChange = true;
        }

        //�u���������v�Ɣ��肳�ꂽ�ꍇ�A
        //�ȉ��������s�Ȃ��B
        if (l_blnIsSameTimeChange)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market = null;
            try
            {
                //�����P��.�s��h�c�ɊY������s��I�u�W�F�N�g���擾����B
                l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                //�s�ꂪ�擾�ł��Ȃ��ꍇ
                //��O���X���[����
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();

            //�擾�����s��.���������\�敪==�h�������ړ��������s�h�̏ꍇ
            //��O���X���[����
            //���x�e���ԑс^�x�e���ԑшȊO�ŃG���[���b�Z�[�W�𕪂���B 
            if (WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(
                l_marketRow.getChangeableType()))
            {
                //�x�e���ԑ�
                if (l_blnIsTradeCoseTimeZone)
                {
                    log.debug("�������ړ��������s�i�x�e���ԑсj�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02197,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
                //�x�e���ԑшȊO
                else
                {
                    log.debug("�������ړ��������s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        //�T�j�@@������������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //isChange��������()���R�[�����A 
        //���������ɒ��������������𔻒肷��B 
        this.isChangeOrderCondType(l_orderUnit, l_strOrderConditionType);
        
        //�U�j�@@���������敪����������Ă��Ȃ����Ƃ̃`�F�b�N 
        //(*)�������̒��������敪 
        //�@@�敨OP�f�[�^�A�_�v�^.get���������敪()���R�[������B 
        String l_strOrgExpirationDateType =
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

        //�������̒��������敪(*)������.�������������敪�̏ꍇ 
        //��O���X���[���邱�ƁB 
        if (!l_strOrgExpirationDateType.equals(l_strExpirationDateType))
        {
            log.debug("���������敪����v���Ȃ����߁A�����s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02102,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������敪����v���Ȃ����߁A�����s�ł��B");
        }

        //�V�j�@@�t�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //isChange�t�w�l����()���R�[�����A 
        //�t�w�l�����ɒ��������������𔻒肷��B 
        boolean l_blnIsChangeStopCond = 
            isChangeStopCond(
                l_orderUnit,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceType,
                l_dblStopPrice);
        
        //�W�j�@@W�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //isChangeW�w�l����()���R�[�����A 
        //W�w�l�����ɒ��������������𔻒肷��B 
        //
        //[isChangeW�w�l����()�Ɏw�肷�����] 
        //�����P�ʁF�@@�����P�� 
        //�������������F�@@������������ 
        //���������������Z�q�F�@@���������������Z�q 
        //�����t�w�l��l�^�C�v�F�@@�����t�w�l��l�^�C�v 
        //�����t�w�l��l�F�@@�����t�w�l��l 
        //�����iW�w�l�j�����w�l�F�@@�����iW�w�l�j�����w�l 
        //�����iW�w�l�j���s�����F�@@�����iW�w�l�j���s���� 
        boolean l_blnIsChangeWStopCond = 
            isChangeWStopCond(
                l_orderUnit,
                l_strOrderConditionType,
                l_strOrderCondOperator,
                l_strStopPriceType,
                l_dblStopPrice,
                l_dblWStopPrice,
                l_wLimitExecCondType);
        
        //�X�j�@@�����������t����������Ă��Ȃ����Ƃ̃`�F�b�N 
        //isChange����������()���R�[�����A 
        //�����������t�ɒ��������������𔻒肷��B 
        boolean l_blnIsChangeExpirationDate = 
            isChangeExpirationDate(l_orderUnit,l_datExpriationDate);
        
        //�P�O�j�@@�ԍϐ��ʓ��󂪒�������Ă��Ȃ����Ƃ̃`�F�b�N 
        //isChange�ԍϐ��ʓ���()���R�[�����A 
        //�ԍϐ��ʓ���ɒ��������������𔻒肷��B 
        //
        //[isChange�ԍϐ��ʓ���()�Ɏw�肷�����] 
        //�����P�ʁF�@@�����P�� 
        //������ԍό��ʃG���g���F�@@������ԍό��ʃG���g�� 
        boolean l_blnIsChangeEachQuantityOfCloseContract =
            isChangeEachQuantityOfCloseContract(l_orderUnit,l_modifiedSettleContractEntries);
       
        //�P�P�j�@@�����������Ă��邩�̃`�F�b�N 
        //isChange����()�AisChange�P��()�A 
        //isChange���s����()�AisChange�t�w�l����()�A 
        //isChangeW�w�l����()�AisChange����������()�A 
        //isChange�ԍϐ��ʓ���() 
        //�̂��ׂĂ�false��ԋp�����ꍇ�A  
        //�@@�������������牽����������Ă��Ȃ��Ɣ��f���A  
        //�@@�u�������͂Ȃ��v�̗�O���X���[����B  
        if (!l_blnIsChangeQuantity && !l_blnIsChangeUnitPrice
            && !l_blnIsChangeExecCondType && !l_blnIsChangeStopCond
            && !l_blnIsChangeWStopCond && !l_blnIsChangeExpirationDate
            && !l_blnIsChangeEachQuantityOfCloseContract) 
        {
            log.debug("�������ꂽ���ڂ��P������܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00039,
                this.getClass().getName() + STR_METHOD_NAME);
        }
       
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isChange����)<BR>
     * ���ʂɒ����������Ă��邩���肷��B<BR>
     * <BR>
     *�i�����P��.getQuantity == �������ʁj�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s���Atrue��ԋp����B<BR>
     * �|�i���ϐ���(*1)�@@���@@�������ʁj�̏ꍇ�A��O���X���[����B<BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00142 <BR>
     * �|�i��������(*2)�@@���@@�������ʁj�̏ꍇ�A��O���X���[����B<BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00143 <BR>
     * <BR>
     * (*1) ��萔��<BR>
     * �����P��.getExecutedQuantity()<BR>
     * (*2) ��������<BR>
     * �����P��.getQuantity()<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �������i�����������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_dblChangeQuantity - ��������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D098E0023
     */
    protected boolean isChangeQuantity(
        OrderUnit l_orderUnit,
        double l_dblChangeQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeQuantity(l_orderUnit,l_dblChangeQuantity)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //��萔��
        double l_dblExcutedQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExcutedQuantity))
        {
            l_dblExcutedQuantity = 0;
        }

        //��������
        double l_dblQuantity = l_orderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }

        log.debug("��萔�� = " + l_dblExcutedQuantity);
        log.debug("�������� = " + l_dblQuantity);
        log.debug("�������� = " + l_dblChangeQuantity);

        // �����P��.getQuantity == �������ʁj�̏ꍇ
        boolean l_blnIsChangeQuantity = true;
        if (l_dblQuantity == l_dblChangeQuantity)
        {
            //false��ԋp����
            l_blnIsChangeQuantity = false;
        }
        // �ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s���Atrue��ԋp����
        else
        {
            //�i���ϐ��ʁ��@@�������ʁj�̏ꍇ
            if (l_dblExcutedQuantity > l_dblChangeQuantity)
            {
                //��O���X���[����
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00142,
                    this.getClass().getName() + STR_METHOD_NAME);

            }

            //�i��������  ���@@�������ʁj�̏ꍇ
            if (l_dblQuantity < l_dblChangeQuantity)
            {
                //��O���X���[����
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00143,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsChangeQuantity;
    }

    /**
     * (isChange�P��)<BR>
     * �P���ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �i�����P��.getLimitPrice == �����w�l�j�̏ꍇ�Afalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �������i�����������j�̒����P�ʃI�u�W�F�N�g<BR>
     *
     * @@param l_dblChangeLimitPrice - �����w�l<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D118C02E2
     */
    protected boolean isChangeUnitPrice(OrderUnit l_orderUnit, double l_dblChangeLimitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeUnitPrice(l_orderUnit,l_dblChangeLimitPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, getClass().getName() + STR_METHOD_NAME);
        }

        boolean l_blnIsChangeUnitPrice = false;

        //�w�l���擾����
        double l_dblLimitPrice = 0D;

        if (((IfoOrderUnitRow) l_orderUnit.getDataSourceObject()).getLimitPriceIsNull())
        {
            return false;
        }
        else
        {
            l_dblLimitPrice = l_orderUnit.getLimitPrice();
        }

        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("l_dblChangeLimitPrice = " + l_dblChangeLimitPrice);

        if (l_dblLimitPrice == l_dblChangeLimitPrice)
        {
            l_blnIsChangeUnitPrice = false;
        }
        else
        {
            l_blnIsChangeUnitPrice = true;
        }

        log.debug("l_blnIsChangeUnitPrice = " + l_blnIsChangeUnitPrice);

        log.exiting(STR_METHOD_NAME);

        return l_blnIsChangeUnitPrice;
    }

    /**
     * (isChange���s����)<BR>
     * ���s�����ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     *�i�����P��.���s���� == �������s�����j�̏ꍇ�Afalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - �������i�����������j�̒����P�ʃI�u�W�F�N�g
     * @@param l_orderExecutionConditionType - �������s����
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeExecCondType(
        OrderUnit l_orderUnit,
        IfoOrderExecutionConditionType l_orderExecutionConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeExecCondType(l_orderUnit,l_orderExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null || l_orderExecutionConditionType == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        // �����P��.���s����
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        IfoOrderExecutionConditionType l_executionConditionType =
            l_ifoOrderUnitRow.getExecutionConditionType();

        //�i�����P��.���s���� == �������s�����j�̏ꍇ�Afalse��ԋp����B
        // �ȊO�Atrue��ԋp����B
        boolean isChangeExecCondType = true;
        if (l_orderExecutionConditionType.equals(l_executionConditionType))
        {
            isChangeExecCondType = false;
        }

        log.exiting(STR_METHOD_NAME);
        return isChangeExecCondType;
    }

    /**
     * �戵�\�ȐV�K���������ǂ����̃`�F�b�N���s���B<BR>
     *�P�j�⏕�����I�u�W�F�N�g����A�⏕�����^�C�v���擾����B<BR>
     *�Q�j�ȉ��̏����ƈ�v����ꍇ�A��O���X���[����B
     *�⏕�����^�C�v != 7�i�敨�؋��������j andis���� == false<BR>
     * @@param l_subAccount
     * @@param l_blnIsBuy
     */
    public void validateHandlingOpenContractOrder(SubAccount l_subAccount, boolean l_blnIsBuy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingOpenContractOrder";
        log.entering(STR_METHOD_NAME);
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()) && !l_blnIsBuy)
        {
            //��O���X���[����
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01094);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01094, getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (isChange��������)<BR>
     * ���������ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �i�����P��.�������� == �������������j�̏ꍇ�A <BR>
     * <BR>
     * false��ԋp����B <BR>
     * <BR>
     * �ȊO�A��O���X���[����B <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00071 <BR>
     * @@param l_orderUnit - �������i�����������j�̒����P�ʃI�u�W�F�N�g <BR>
     * @@param l_strChangeOrderCondType - ������������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeOrderCondType(OrderUnit l_orderUnit, String l_strChangeOrderCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeOrderCond(OrderUnit l_orderUnit, String l_strChangeOrderCondType)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null || l_strChangeOrderCondType == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_strChangeOrderCondType.equals(l_orderUnitRow.getOrderConditionType()))
        {
            return false;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00071,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (isChange�t�w�l����)<BR>
     * �t�w�l�̏����i�t�w�l��l�A�����������Z�q�j�ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������������ ���h�t�w�l�h�̏ꍇ�́A<BR>
     *  �ȍ~�̏����͍s�킸false��Ԃ��B<BR>
     * <BR>
     * �Q�j����.������������ ���h�t�w�l�h�̏ꍇ�A <BR>
     *  �����ς݂̋t�w�l���� <BR>
     * �i�����P��.���N�G�X�g�^�C�v == �h1�F�����T�[�o�h�j�̏ꍇ�A <BR>
     * <BR>
     * �i�����P��.�������� == ������������ && <BR>
     * �@@�����P��.�����������Z�q == ���������������Z�q && <BR>
     * �@@�����P��.�t�w�l��l�^�C�v == �����t�w�l��l�^�C�v && <BR>
     * �@@�����P��.�t�w�l��l == �����t�w�l��l�j�̏ꍇ�A<BR>
     * <BR>
     * ��L�����ɂ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B <BR>
     *    class:  WEB3BusinessLayerException<BR>
     *    tag: BUSINESS_ERROR_00141 <BR>
     * <BR>
     * �R�j�i�����P��.�������� == ������������ ���� <BR>
     * �@@�@@�@@�����P��.�����������Z�q == ���������������Z�q ���� <BR>
     * �@@�@@�@@�����P��.�t�w�l��l�^�C�v == �����t�w�l��l�^�C�v ���� <BR>
     * �@@�@@�@@�����P��.�t�w�l��l == �����t�w�l��l�j �̏ꍇ�A <BR>
     * <BR>
     *      false��ԋp����B�ȊO��true��ԋp����B <BR>
     * @@param l_orderUnit - �������i�����������j�̒����P�ʃI�u�W�F�N�g  <BR>
     * @@param l_strOrderExecutionConditionType - ������������<BR>
     * @@param l_strOrderCondOperator - ���������������Z�q<BR>
     * @@param l_strStopPriceType - �����t�w�l��l�^�C�v<BR>
     * @@param l_dblStopPrice - �����t�w�l��l<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeStopCond(
        OrderUnit l_orderUnit,
        String l_strOrderExecutionConditionType,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeStopCond(l_orderUnit, l_strOrderConditionType, " +
                "l_strOrderCondOperator, l_strStopPriceType, l_dblStopPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        // �P�j����.������������ ���h�t�w�l�h�̏ꍇ�́A
        //     �ȍ~�̏����͍s�킸false��Ԃ��B
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
            l_strOrderExecutionConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // �Q�j�@@����.������������ ���h�t�w�l�h�̏ꍇ�A�����ς݂̋t�w�l����
        else
        {
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // �i�����P��.���N�G�X�g�^�C�v == �h1�F�����T�[�o�h�j�̏ꍇ�A
            if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
            {
                // �����P��.�������� == ������������ &&
                // �����P��.�����������Z�q == ���������������Z�q &&
                // �����P��.�t�w�l��l�^�C�v == �����t�w�l��l�^�C�v &&
                // �����P��.�t�w�l��l == �����t�w�l��l�̏ꍇ�A
                // ��L�����ɂ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B
                if (!(isEquals(l_strOrderExecutionConditionType, l_orderUnitRow.getOrderConditionType())
                    && isEquals(l_strOrderCondOperator, l_orderUnitRow.getOrderCondOperator())
                    && isEquals(l_strStopPriceType, l_orderUnitRow.getStopPriceType())
                    && l_orderUnitRow.getStopOrderPrice() == l_dblStopPrice))
                {
                    //��O���X���[����
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00141,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            // ���������̏ꍇ�Afalse��ԋp����B
            // �ȊO��true��ԋp����B
            if (isEquals(l_strOrderExecutionConditionType, l_orderUnitRow.getOrderConditionType())
                && isEquals(l_strOrderCondOperator, l_orderUnitRow.getOrderCondOperator())
                && isEquals(l_strStopPriceType, l_orderUnitRow.getStopPriceType())
                && l_orderUnitRow.getStopOrderPrice() == l_dblStopPrice)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    /**
     * (isChangeW�w�l����)<BR>
     * W�w�l�̏����i�����������Z�q�A�t�w�l��l�A�iW�w�l�j�����w�l�j��  <BR>
     * �����������������`�F�b�N����B  <BR>
     * <BR>
     * �P�j�@@����.������������ ���hW�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸false��Ԃ��B<BR>  
     * <BR>
     * �Q�j�@@����.������������ ���hW�w�l�h�̏ꍇ�A <BR>
     * �@@�@@�i�����P��.�����������Z�q�j == �i���������������Z�q�j ���A<BR>
     * �@@�@@�i�����P��.�t�w�l��l�^�C�v�j == �i�����t�w�l��l�^�C�v�j���A<BR>
     * �@@�@@�i�����P��.�t�w�l��l�j == �i�����t�w�l��l�j���A<BR>
     * �@@�@@�i�����P��.�iW�w�l�j�����w�l�j == �i�����iW�w�l�j�����w�l�j����<BR>
     * �@@�@@�i�����P��.�iW�w�l�j���s�����j == �i�����iW�w�l�j���s�����j�̏ꍇ�A<BR>
     * �@@�@@false��ԋp����B�ȊO��true��ԋp����B <BR>
     * <BR>
     * @@param l_orderUnit - �������i�����������j�̒����P�ʃI�u�W�F�N�g  <BR>
     * @@param l_strOrderExecutionConditionType - ������������<BR>
     * @@param l_strOrderCondOperator - ���������������Z�q<BR>
     * @@param l_strStopPriceType - �����t�w�l��l�^�C�v<BR>
     * @@param l_dblStopPrice - �����t�w�l��l<BR>
     * @@param l_dblWStopPrice -  �����iW�w�l�j�����w�l<BR>
     * @@param l_wLimitExecCondType - �����iW�w�l�j���s����<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeWStopCond(
        OrderUnit l_orderUnit, 
        String l_strOrderExecutionConditionType,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopPrice,
        double l_dblWStopPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeWStopCond";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@����.������������ ���hW�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸false��Ԃ��B
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderExecutionConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@����.������������ ���hW�w�l�h�̏ꍇ
        else
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            boolean l_blnIsWLimitExecCondType = false;
            IfoOrderExecutionConditionType l_WLimitExecCondType2 = 
                l_orderUnitRow.getWLimitExecCondType();
            if (l_WLimitExecCondType2 == null && l_wLimitExecCondType == null)
            {
                l_blnIsWLimitExecCondType = true;
            }
            else if (l_WLimitExecCondType2 == null || l_wLimitExecCondType == null)
            {
                l_blnIsWLimitExecCondType = false;
            }
            else 
            {
                l_blnIsWLimitExecCondType = l_WLimitExecCondType2.equals(l_wLimitExecCondType);
            }

            if ((isEquals(l_orderUnitRow.getOrderCondOperator(), l_strOrderCondOperator))
                && (isEquals(l_orderUnitRow.getStopPriceType(), l_strStopPriceType))
                && (l_orderUnitRow.getStopOrderPrice() == l_dblStopPrice)
                && (l_orderUnitRow.getWLimitPrice() == l_dblWStopPrice)
                && l_blnIsWLimitExecCondType)
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
    }

    /**
     * (isChange����������)<BR>
     * �����������ɒ����������������`�F�b�N����B <BR>
     *<BR>
     *�i�����P��.�����������t�j == �i���������������j<BR>
     * false��Ԃ��B <BR>
     *<BR>
     * �ȊO�̏ꍇ�Atrue��ԋp����B <BR>
     * @@param l_orderUnit - �������i�����������j�̒����P�ʃI�u�W�F�N�g  <BR>
     * @@param l_datExpirationDate - ��������������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407D120A0245
     */
    protected boolean isChangeExpirationDate(OrderUnit l_orderUnit, Date l_datExpirationDate)
    {
        final String STR_METHOD_NAME = "isChangeExpirationDate(OrderUnit l_orderUnit, Date l_datExpirationDate)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3DateUtility.compareToDay(l_orderUnitRow.getExpirationDate(),l_datExpirationDate) == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * �iisChange�ԍϐ��ʓ���j�B<BR>
     * <BR>
     * �ԍϐ��ʓ���ɒ����������������`�F�b�N����B<BR>
     * ����ɒ���������ꍇ��true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �P�j�V�K�������̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����O�̕ԍώw����I�u�W�F�N�g���擾����B<BR>
     * �@@�����̒����P��.getContractsToClose( )�Ŏ擾�B<BR>
     * <BR>
     * �R�j�@@�l�̔�r<BR>
     * �@@�����̒�����ԍώw��G���g���̗v�f����LOOP���A�l�̔�r���s���B<BR>
     * �@@�R�|�P�j�@@�i�����O�̕ԍώw����.�ԍϒ������ʁI=�@@�����̒�����ԍό��ʃG���g��.�����㊔���j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�R�|�Q�j�@@�R�|�P�j�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit�i�����P�ʁj<BR>
     * �@@�@@�@@�������i�����������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_modifiedSettleContractEntries�i������ԍό��ʃG���g���j<BR>
     * �@@�@@�@@������ԍό��ʃG���g���̔z��B<BR>
     * @@return boolean
     * @@throws WEB3BusinessLayerException
     */
    public boolean isChangeEachQuantityOfCloseContract(
    OrderUnit l_orderUnit,
        SettleContractEntry[] l_modifiedSettleContractEntries)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "isChangeEachQuantityOfCloseContract(OrderUnit, SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCateg) ||
            OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderCateg))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        
        IfoClosingContractSpec[] l_closingContractSpecs = null;
            IfoContractSettleOrderUnit l_ifoOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;
                
        l_closingContractSpecs = l_ifoOrderUnit.getContractsToClose();

        if (l_closingContractSpecs.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
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
     * (validate���ꎷ�s�����戵��~)
     * <BR>
     * ���ꎷ�s���������̎戵��~���ݒ肳��Ă��邩�ǂ������`�F�b�N����B<BR> 
     * <BR>
     * �P�j�@@���������w��Ȃ�<BR> 
     * �@@�i�p�����[�^.�������� == "DEFAULT"�j�̏ꍇ�A<BR> 
     * �@@�������I������B <BR>
     * <BR>
     * �Q�j�@@�����i�p�����[�^.�����P��ID != 0�j�̏ꍇ�A<BR> 
     * �@@�ȉ��̏������s���B <BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�����P��ID�ɊY�����钍���P�ʂ��擾����B<BR> 
     * <BR>
     * �@@�Q�|�Q�j�@@�����ς̋t�w�l�����̏ꍇ�A<BR> 
     * �@@�@@�i�p�����[�^.�������� == "�t�w�l" ����<BR> 
     * �@@�@@�@@�擾���������P��.���N�G�X�g�^�C�v == "�����T�[�o"�j<BR> 
     * �@@�@@�@@�������I������B <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�X�g�b�v�����֐ؑ֍ς�W�w�l�����̏ꍇ�A<BR> 
     * �@@�@@�i�p�����[�^.�������� == "W�w�l" ���� <BR>
     * �@@�@@�@@OP�����}�l�[�W��.is�X�g�b�v�����L��() == true�j <BR>
     * �@@�@@�@@�������I������B <BR>
     * <BR>
     * �@@�@@�@@[is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�擾���������P�� <BR>
     * <BR>
     * �R�j�@@DB����<BR> 
     * �@@�ȉ��̏����ɂāA���ꎷ�s�����戵��~�e�[�u������������B<BR> 
     * �@@[����] <BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�⏕����..get�،����().get�،���ЃR�[�h()<BR> 
     * �@@�@@�@@�@@And�@@���X�R�[�h = �p�����[�^.�⏕����.get����X().get���X�R�[�h ()<BR> 
     * �@@�@@�@@�@@And �ݒ�Ώێ�� = "���i" <BR>
     * �@@�@@�@@�@@And �L�[��� = <BR>
     * �@@�@@�@@�@@�@@[�p�����[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]<BR> 
     * �@@�@@�@@�@@�@@�@@"�敨" <BR>
     * �@@�@@�@@�@@�@@[��L�ȊO] <BR>
     * �@@�@@�@@�@@�@@�@@"�I�v�V����" <BR>
     * �@@�@@�@@�@@And �폜�t���O = "DEFAULT"<BR> 
     * <BR>
     * �S�j�@@�R�j�̌������ʂ̓��A�p�����[�^.����������<BR> 
     * �@@�Ή�����e�[�u�����ڂ̂����ꂩ��"��~��"�ƂȂ��Ă����ꍇ�A<BR> 
     * �@@�u�w�肳�ꂽ�����t�����ł̏��i�͎戵��~���v�̗�O���X���[����B<BR> 
     * <BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�A�������I������B<BR> 
     * <BR>
     * �@@[�Ή����鍀��]<BR> 
     * �@@�@@�p�����[�^.�������� == "�t�w�l" �� �t�w�l������~�t���O<BR> 
     * �@@�@@�p�����[�^.�������� == "W�w�l" �� W�w�l������~�t���O<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_lngOrderUnitId - �i�����P��ID�j<BR>
     * �����P��ID<BR>
     * @@param l_strOrderCond - (��������)<BR>
     * ��������<BR>
     * @@param l_futureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateTriggerOrderStop(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        String l_strOrderCond,
        String l_futureOptionDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTriggerOrderStop(WEB3GentradeSubAccount, long, String, String)";
        log.entering(STR_METHOD_NAME);

        //���������w��Ȃ��̏ꍇ�A�������I������B
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCond))
        {
            log.debug("���������w��Ȃ��Ȃ̂ŁA�����I��");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //���������̏ꍇ
        if (l_lngOrderUnitId != 0)
        {
            //�p�����[�^.�����P��ID�ɊY�����钍���P�ʂ��擾����B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����P��ID:[" + l_lngOrderUnitId + "]�ɊY�����郌�R�[�h������܂���");
            }

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            
            //�����ς݂̋t�w�l�����̏ꍇ�A�������I������B
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCond) &&
                WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
            {
                log.debug("�����ς̋t�w�l�����Ȃ̂ŁA�����I��");
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //�X�g�b�v�����֐ؑ֍ς�W�w�l�����̏ꍇ,�������I������B
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCond) &&
                l_orderManager.isStopOrderValid(l_ifoOrderUnit))
            {
                log.debug("�X�g�b�v�����֐ؑ֍ς�W�w�l�����̏ꍇ�A�����I��");
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

        //DB����
        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_lstBind = new ArrayList();

        //�،���ЃR�[�h
        l_sbWhere.append(" institution_code = ? ");
        l_lstBind.add(l_subAccount.getInstitution().getInstitutionCode());
        //���X�R�[�h
        l_sbWhere.append(" and branch_code = ? ");
        l_lstBind.add(l_subAccount.getWeb3GenBranch().getBranchCode());
        //�ݒ�Ώێ��
        l_sbWhere.append(" and target_type = ? ");
        l_lstBind.add(WEB3TargetTypeDef.COMMODITY);
        //�L�[���
        l_sbWhere.append(" and key = ? ");
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_futureOptionDiv))
        {
            //�p�����[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ
            l_lstBind.add(WEB3CommodityDivDef.FUTURE);
        }
        else
        {
            //�p�����[�^.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ
            l_lstBind.add(WEB3CommodityDivDef.OPTION);
        }
        //�폜�t���O
        l_sbWhere.append(" and delete_flag = ? ");
        l_lstBind.add(BooleanEnum.FALSE);

        List l_lisTriggerOrderStop = null;
        try
        {
            //���ꎷ�s������~�e�[�u������������B
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisTriggerOrderStop =
                l_qp.doFindAllQuery(
                    TriggerOrderStopRow.TYPE,
                    l_sbWhere.toString(),
                    l_lstBind.toArray()
                    );
            
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�A�N�Z�X�Ɏ��s���܂���");
        }

        //���������ʂ��擾�ł��Ȃ������ꍇ�A�������I������B
        if (l_lisTriggerOrderStop.isEmpty())
        {
            log.debug("�Y���f�[�^�Ȃ��Ȃ̂ŁA�����I��");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�������ʂ̓��A�p�����[�^.���������ɑΉ�����e�[�u�����ڂ�
        //�����ꂩ��"��~��"�ƂȂ��Ă����ꍇ�A��O��throw����B
        for (int i = 0; i < l_lisTriggerOrderStop.size(); i++)
        {
            TriggerOrderStopRow l_triggerOrderStopRow =
                (TriggerOrderStopRow) l_lisTriggerOrderStop.get(i);

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCond) &&
                BooleanEnum.TRUE.intValue() == l_triggerOrderStopRow.getStopOrderStopFlag())
            {
                log.debug("�t�w�l�����F ��~��");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02433,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCond) &&
                BooleanEnum.TRUE.intValue() == l_triggerOrderStopRow.getWlimitOrderStopFlag())
            {
                log.debug("�v�w�l�����F ��~��");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02433,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.debug("���ꎷ�s�����戵��~�Ȃ�");

        log.exiting(STR_METHOD_NAME);
    }

    private static boolean isEquals(String l_obj1, String l_obj2)
    {
        if (l_obj1 == null && l_obj2 == null)
        {
            return true;
        }

        if (l_obj1 == null || l_obj2 == null)
        {
            return false;
        }

        return l_obj1.equals(l_obj2);
    }
    
    /**
     * (validateW�w�l����)<BR>
     * ���������Ƃ���W�w�l���w�肳�ꂽ�����ɂ��āA <BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �|�iW�w�l�j�L����ԃ`�F�b�N�@@���������̂� <BR>
     * �|�iW�w�l�j�����w�l���}�C�i�X�l�łȂ����� �B <BR>
     * �|�iW�w�l�j�����w�l�̌Ēl�`�F�b�N <BR>
     * �|�iW�w�l�j�����w�l�̒l���`�F�b�N <BR>
     * �|�iW�w�l�j���s�����戵�\�`�F�b�N <BR>
     * �|�iW�w�l�j�����w�l�����P���敪�`�F�b�N <BR>
     * �|�iW�w�l�j���s�����\�`�F�b�N <BR>
     * �|���������P�������`�F�b�N <BR>
     * �|���������P�����̓`�F�b�N <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�敨OP�����jvalidateW�w�l�����v�Q�ƁB<BR>
     * <BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�敨OP�����jvalidateW�w�l�����v <BR>
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
     * <BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�敨OP�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.5is�戵�\���s����(���s���� : IfoExecutionConditionType)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*)�iW�w�l�j���s�����戵�\�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@false���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u���͂��ꂽ�iW�w�l�j���s�����͎戵�s�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02495<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�敨OP�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.6(*)���~�b�g�����E�����P���敪�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���~�b�g�����̒����P�������s�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�i�p�����[�^.�w�l == 0�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�v�w�l�����̃��~�b�g�����͐��s�w��s�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B�@@�@@<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02496<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�敨OP�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.7is���s�����\(is�V�K�� : boolean, is���� : boolean)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*)�iW�w�l�j�����w�l���s�\�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@false���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u���s�����͎戵�s�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_00154<BR>
     * =====================================================<BR>
     * <BR>
     * =====================================================<BR>
     * �V�[�P���X�} : �u�i�敨OP�����jvalidateW�w�l�����v <BR>
     * ��̈ʒu�@@�@@ : 1.8.1(*)�w�l�ƁiW�w�l�j�����w�l�̃`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*)�p�����[�^.�w�l == �p�����[�^.�iW�w�l�j�����w�l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�u�����P���ƁiW�w�l�j�����w�l�����l�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02498<BR>
     * =====================================================<BR>
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
     * <BR>
     * ���������̂ݎg�p�B�V�K�����o�^���́A <BR>
     * �@@null�Œ�B<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP��������I�u�W�F�N�g<BR>
     * @@param l_blnIsOpenContract - (is�V�K��)<BR>
     * �iisOpenContract�j <BR>
     * �V�K��������ǂ����̔���B <BR>
     * �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B <BR>
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * �iisBuyToOpenOrder�j <BR>
     * ����������ǂ����̔���B <BR>
     * �����̏ꍇtrue�A�����̏ꍇfalse�B <BR>
     * @@throws WEB3BaseException 
     */
    public void validateWLimitPriceOrder(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderUnitId,
        double l_dblLimitPrice,
        String l_strOrderCondition,
        double l_dblOrderCondPrice,
        String l_strWLimitPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        String l_strWlimitEnableStatusDiv,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyToOpenOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateWLimitPriceOrder(WEB3GentradeSubAccount, long, double, String, "
            + "double, String, IfoOrderExecutionConditionType, boolean, "
            + "WEB3IfoTradedProductImpl, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ifoTradedProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        // 1.1(*)�p�����[�^.�������� != "W�w�l"�̏ꍇ,�������I������B
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        IfoOrderUnit l_ifoOrderUnit = null;
        
        //1.2(*)�����i�p�����[�^.�����P��ID != 0�j�̏ꍇ
        if (l_lngOrderUnitId != 0)
        {
          
            //1.2.1 (*)�X�g�b�v�����֐ؑ֍ς�W�w�l�����i�p�����[�^.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v�����L��"�j�̏ꍇ
            if (WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //1.2.2 (*)�X�g�b�v���������ς�W�w�l�����i�p�����[�^.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v����������"�j�̏ꍇ
            if (WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE.equals(l_strWlimitEnableStatusDiv))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //1.2.3 getOrderUnit(arg0 : long)
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����P��ID:[" + l_lngOrderUnitId + "]�ɊY�����郌�R�[�h������܂���");
            }

            //1.2.4 get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)(
            String l_strWlimitEnableStatusDiv2 = 
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);
            
            //1.2.5 �iW�w�l�j�L����ԃ`�F�b�N
            if (!isEquals(l_strWlimitEnableStatusDiv, l_strWlimitEnableStatusDiv2))
            {
                log.debug("��������W�w�l�����L����Ԃ��ύX�ƂȂ����ׁA�����s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02494,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "��������W�w�l�����L����Ԃ��ύX�ƂȂ����ׁA�����s�B");  
            }
                
        }
        
        //1.3  validate�����P��()
        this.validateOrderUnitPrice(
            Double.parseDouble(l_strWLimitPrice), l_ifoTradedProduct, l_subAccount);
   
        IfoProductRow l_ifoProductRow = (IfoProductRow) (
            (WEB3IfoProductImpl) l_ifoTradedProduct.getProduct()).getDataSourceObject();
        
        //1.4 �戵�\��������()
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                l_ifoProductRow.getFutureOptionDiv(),         
                WEB3MarginTradingDivDef.DEFAULT);
        
        //1.5is�戵�\���s����()
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_wLimitExecCondType))
        {
            log.debug("���͂��ꂽ�iW�w�l�j���s�����͎戵�s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02495,
                this.getClass().getName() + STR_METHOD_NAME,
                "���͂��ꂽ�iW�w�l�j���s�����͎戵�s�B"); 
        }
        
        //1.6 (*)���~�b�g�����E�����P���敪�`�F�b�N
        if (l_dblLimitPrice == 0)
        {
            log.debug("�v�w�l�����̃��~�b�g�����͐��s�w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02496,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�����̃��~�b�g�����͐��s�w��s�B"); 
        }
        
        //1.7(*)�X�g�b�v���������s�����̏ꍇ
        double l_dblWLimitPrice = Double.parseDouble(l_strWLimitPrice);
        
        if (l_dblWLimitPrice == 0 || (l_dblWLimitPrice != 0 && 
            (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED).equals(l_wLimitExecCondType)))
        {
            if (!l_handlingOrderCond.isMarketOrderPossible(
                l_blnIsOpenContract, l_blnIsBuyToOpenOrder))          
            {   
                log.debug("���s�����͎戵�s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00154,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���s�����͎戵�s�B"); 
            }
        }
        
        //1.8  (*)�iW�w�l�j�����w�l���w�l�i�p�����[�^.�iW�w�l�j�����w�l != 0�j�̏ꍇ
        if (l_dblWLimitPrice != 0)
        {
            //1.8.1 (*)�w�l�ƁiW�w�l�j�����w�l�̃`�F�b�N
            if (l_dblWLimitPrice == l_dblLimitPrice)
            {
                log.debug("�����P���ƁiW�w�l�j�����w�l�����l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02498,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����P���ƁiW�w�l�j�����w�l�����l�B"); 
            }
        }
        
        //1.9 validate���������P��
        this.validateOrderCondPrice(
            l_subAccount.getWeb3GenBranch(),
            l_dblLimitPrice,
            l_dblOrderCondPrice,
            l_ifoTradedProduct,
            l_blnIsBuyToOpenOrder);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���������P��)<BR>
     * �����������w�肳�ꂽ�����ɂ��āA<BR> 
     * ���������P���̃`�F�b�N���s���B <BR>
     * <BR>
     * �|���������P�������`�F�b�N <BR>
     * �|���������P�����̓`�F�b�N <BR>
     * ���v�Z�����͑S��BigDecimal�^�Ōv�Z���邱�ƁB<BR> 
     * <BR>
     * �P�j�@@���������P���`�F�b�N���{���Ȃ����X�̏ꍇ�A���������P���`�F�b�N���s��Ȃ��B<BR>
     * �@@�P�|�P�j�@@���X�v���t�@@�����X�e�[�u������A���������P���`�F�b�N���{�敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���XID = �p�����[�^.���X.���XID<BR>
     * �@@�@@�@@�@@And�@@�v���t�@@�����X�� = �v���t�@@�����X��.W�w�l�����E���������P���`�F�b�N���{�敪<BR>
     * �@@�@@�@@�@@And�@@�v���t�@@�����X���̘A�� = 2�i�F�敨�E�I�v�V�����j<BR>
     * <BR>
     * �@@�@@�@@����L�����Ń��R�[�h���擾�ł��Ȃ������ꍇ�A"�`�F�b�N���Ȃ�"�Ƃ���B<BR>
     * <BR>
     * �@@�P�|�Q�j���������P���`�F�b�N���{�敪��"�`�F�b�N���Ȃ�"�̏ꍇ���^�[������B<BR>
     * <BR>
     * �Q�j�@@���������P�������`�F�b�N <BR>
     * �@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ̊Ԃ� <BR>
     * �@@���ݒl�̐����{�ȏ�̘��������邩�ǂ����`�F�b�N����B <BR>
     * �@@�Q�|�P�j�@@���ݒl���擾����B <BR>
     * �@@�@@�敨OP�v���_�N�g�}�l�[�W��.get���ݒl()���R�[������B<BR> 
     * <BR>
     * �@@�@@[get���ݒl()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�敨OP�����F�@@�p�����[�^.�敨OP�������.getProduct() <BR>
     * �@@�@@�@@�P���F�@@�p�����[�^.�w�l <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����l���擾����B <BR>
     * �@@�@@�����l = (�p�����[�^.���������P�� - �p�����[�^.�w�l)�̐�Βl <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����l�����ݒl*�{���ȏォ�ǂ����`�F�b�N����B <BR>
     * �@@�@@�����l���A�ȉ��̏����ɊY�����Ȃ��ꍇ�A <BR>
     * �@@�@@�u���������P�����̓G���[�i�����l���w��̔{�������j�v�� <BR>
     * �@@�@@��O���X���[����B <BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag  :BUSINESS_ERROR_02492<BR>
     * <BR>
     * �@@�@@�@@�����l >= �i���ݒl * �{��(*1)�j <BR>
     * <BR>
     * �@@�@@(*1)�{�� <BR>
     * �@@�@@�@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������A <BR>
     * �@@�@@�@@�擾�������R�[�h.�v���t�@@�����X�̒l��{���Ƃ���B <BR>
     * <BR>
     * �@@�@@�@@[����]<BR> 
     * �@@�@@�@@�@@���XID = �p�����[�^.���X.���XID<BR>  
     * �@@�@@�@@�@@And�@@�v���t�@@�����X�� = �v���t�@@�����X��.W�w�l�����E���������P�������{��<BR> 
     * �@@�@@�@@�@@And�@@�v���t�@@�����X���̘A�� = 2�i�F�敨�E�I�v�V�����j <BR>
     * <BR>
     * �@@�@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�{�� = 1�Ƃ���B<BR> 
     * <BR>
     * �R�j�@@���������P�����̓`�F�b�N<BR> 
     * �@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ�<BR> 
     * �@@���������ݍ���ł��邩�ǂ����`�F�b�N����B <BR>
     * �@@�R�|�P�j�@@�������擾����B <BR>
     * �@@�@@�敨OP�v���_�N�g�}�l�[�W��.get����()���R�[������B<BR> 
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�敨OP��������F�@@�p�����[�^.�敨OP�������<BR> 
     * <BR>
     * �@@�R�|�Q�j�@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ�<BR> 
     * �@@�@@���������ݍ���ł��邩�ǂ����`�F�b�N����B <BR>
     * �@@�@@�ȉ��̏����𖞂����Ȃ��ꍇ�A <BR>
     * �@@�@@�u���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�v��<BR> 
     * �@@�@@��O���X���[����B <BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag  :BUSINESS_ERROR_02493<BR>
     * <BR>
     * �@@�@@[���i�p�����[�^.is���� == true�j�̏ꍇ]<BR> 
     * �@@�@@�@@�p�����[�^.�w�l <= �擾�������� < �p�����[�^.���������P�� <BR>
     * <BR>
     * �@@�@@[���i��L�ȊO�j�̏ꍇ]<BR> 
     * �@@�@@�@@�p�����[�^.�w�l >= �擾�������� > �p�����[�^.���������P��<BR> 
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_dblOrderCondPrice - (���������P��)<BR>
     * ��������<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP��������I�u�W�F�N�g<BR>
     * @@param l_blnIsBuyToOpenOrder - (is����)<BR>
     * �iisBuyToOpenOrder�j <BR>
     * ����������ǂ����̔���B <BR>
     * �����̏ꍇtrue�A�����̏ꍇfalse�B <BR>
     * @@throws WEB3BaseException 
     */
    protected void validateOrderCondPrice(
        WEB3GentradeBranch l_branch,
        double l_dblLimitPrice,
        double l_dblOrderCondPrice,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        boolean l_blnIsBuyToOpenOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrderCondPrice(WEB3GentradeBranch, double, double," + 
            " WEB3IfoTradedProductImpl, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoTradedProduct == null || l_branch == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //���������P���`�F�b�N���{���Ȃ����X�̏ꍇ�A���������P���`�F�b�N���s��Ȃ��B
        //    ���X�v���t�@@�����X�e�[�u������A���������P���`�F�b�N���{�敪���擾����B
        //    [����]  
        //    ���XID = �p�����[�^.���X.���XID  
        //    And�@@�v���t�@@�����X�� = �v���t�@@�����X��.W�w�l�����E���������P���`�F�b�N���{�敪 
        //    And�@@�v���t�@@�����X���̘A�� = 2�i�F�敨�E�I�v�V�����j 
        //    ����L�����Ń��R�[�h���擾�ł��Ȃ������ꍇ�A"�`�F�b�N���Ȃ�"�Ƃ���B
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_branch.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_WLIMITORDER_CHECK_ORDER_COND_PRICE,
                    2);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //���������P���`�F�b�N���{�敪��"�`�F�b�N���Ȃ�"�̏ꍇ���^�[������B
        if (l_branchPreferencesRow == null
            || WEB3TriggerorderWlimitorderCheckOrderCondPriceDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            return;
        }

        //�P�j�@@���������P�������`�F�b�N  
        //�@@�P�|�P�j�@@���ݒl���擾����B 
        //�@@�@@�敨OP�v���_�N�g�}�l�[�W��.get���ݒl()���R�[������B 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
 
        WEB3IfoProductManagerImpl l_productMgr = 
            (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        double l_dblTickValue = 
            l_productMgr.getTickValue(
                (WEB3IfoProductImpl)l_ifoTradedProduct.getProduct(),
                l_dblLimitPrice);
        
        //�@@�P�|�Q�j�@@�����l���擾����B 
        //�@@�@@�����l = (�p�����[�^.���������P�� - �p�����[�^.�w�l)�̐�Βl 
        BigDecimal l_bdOrderCondPrice = new BigDecimal(l_dblOrderCondPrice + "");
        BigDecimal l_bdLimitPrice = new BigDecimal(l_dblLimitPrice + "");
        
        //�����l
        BigDecimal l_bdEstrangePrice = 
            (l_bdOrderCondPrice.subtract(l_bdLimitPrice)).abs();
        
        //���ݒl
        BigDecimal l_bdTickValue = new BigDecimal(l_dblTickValue + "");
        
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
                    2);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
        //�@@�@@�敨OP�v���_�N�g�}�l�[�W��.get����()���R�[������B 
        double l_dblCurrentPrice = l_productMgr.getCurrentPrice(l_ifoTradedProduct);
        BigDecimal l_bdCurrentPrice = new BigDecimal(l_dblCurrentPrice + "");
        //�@@�Q�|�Q�j�@@�p�����[�^.�w�l�̒l�ƁA�p�����[�^.���������P���Ƃ� 
        //�@@�@@���������ݍ���ł��邩�ǂ����`�F�b�N����B 
        //�@@�@@�ȉ��̏����𖞂����Ȃ��ꍇ�A 
        //�@@�@@�u���������P���^�����P�����̓G���[�i�����̋��ݍ��ݕs���j�v�� 
        //�@@�@@��O���X���[����B 
        //�@@�@@[���i�p�����[�^.is���� == true�j�̏ꍇ] 
        //�@@�@@�@@�p�����[�^.�w�l <= �擾�������� < �p�����[�^.���������P�� 
        if (l_blnIsBuyToOpenOrder)
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
     * (validate�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j)<BR>
     * ���x�ݒ��Ɏs�ꑗ�M�ϒ����̕�����������\���ǂ������肷��B <BR>
     * <BR>
     * �@@�P�j���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B <BR>
     * �@@�@@[����]  <BR>
     * �@@�@@�@@���XID = �p�����[�^.���XID And <BR>
     * �@@�@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.�s�ꑗ�M�ϒ�������������\�i�x�e���Ԓ��j And<BR>
     * �@@�@@�@@�v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �@@�Q�j�Y�����R�[�h�����݂���ꍇ�݈̂ȉ����s���B <BR>
     * �@@�@@�擾���R�[�h�D�v���t�@@�����X�̒l�������s�\�̏ꍇ�A <BR>
     * �@@�@@��O��throw����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00032<BR>
     * @@param l_lngBranchId - (���XID)
     * @@throws WEB3BaseException
     * @@throws OrderValidationException
     */
    public void validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(long l_lngBranchId)
        throws WEB3BaseException, OrderValidationException
    {
        final String STR_METHOD_NAME =
            "validateMultiChangeabilityOfMarketNotifiedOrderInBreakTime(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //���X�v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
            BranchPreferencesRow l_row =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.MULTI_CHANGEABILITY_OF_MARKET_NOTIFIED_ORDER_IN_BREAK_TIME,
                    1);

            //�Y�����R�[�h�����݂���ꍇ�݈̂ȉ����s���B
            //�擾���R�[�h�D�v���t�@@�����X�̒l�������s�\�̏ꍇ�A��O��throw����B
            if (l_row != null)
            {
                if (WEB3MultiChangeabilityDef.NOT_CHANGEABLE.equals(l_row.getValue()))
                {
                    log.debug("��������t�����Ԃł͂���܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����������Rev���)<BR>
     * �������̒���Rev������𒴂��Ȃ����ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�����̒����P�ʃI�u�W�F�N�g��clone�I�u�W�F�N�g�𐶐����A <BR>
     * �@@�@@�@@�����̒�����̒l�����ꂼ��Y�����鍀�ڂɃZ�b�g����B <BR>
     * <BR>
     * �@@�@@�@@�������ʁF�@@�@@�@@�@@�������� <BR>
     * �@@�@@�@@�����w�l�F�@@�@@�@@�@@�w�l <BR>
     * �@@�@@�@@�������s�����F�@@���s���� <BR>
     * <BR>
     * �Q�j�@@�敨OP�����T�[�r�X.get����������Rev()���R�[������B <BR>
     * �@@�@@�@@�����ɂ́A�P�j�ō쐬���������P�ʃI�u�W�F�N�g���Z�b�g����B <BR>
     * �@@�@@�@@����O�i����񐔃I�[�o�[���j��throw���ꂽ�ꍇ�́A <BR>
     * �@@�@@�@@�����̗�O�����̂܂�throw����B<BR>
     * @@param l_ifoOrderUnit - �����P��
     * �����O�̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_dblQuantity - (��������)<BR>
     * ��������<BR>
     * @@param l_dblPriceChange - (�����w�l)<BR>
     * �����w�l<BR>
     * @@param l_changeExecCondType - (�������s����)<BR>
     * �������s����<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangeOrderRevLimit(
        IfoOrderUnit l_ifoOrderUnit,
        double l_dblQuantity,
        double l_dblPriceChange,
        IfoOrderExecutionConditionType l_changeExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeOrderRevLimit(" +
            "IfoOrderUnit, double, double, IfoOrderExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //�����̒����P�ʃI�u�W�F�N�g��clone�I�u�W�F�N�g�𐶐����A
        //�����̒�����̒l�����ꂼ��Y�����鍀�ڂɃZ�b�g����B
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        IfoOrderUnitParams l_afterIfoOrderUnitParams =
            new IfoOrderUnitParams(l_ifoOrderUnitRow);

        l_afterIfoOrderUnitParams.setQuantity(l_dblQuantity);
        l_afterIfoOrderUnitParams.setLimitPrice(l_dblPriceChange);
        l_afterIfoOrderUnitParams.setExecutionConditionType(l_changeExecCondType);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        IfoOrderUnit l_changeAfterOrderUnit =
            (IfoOrderUnit)l_orderManager.toOrderUnit(l_afterIfoOrderUnitParams);

        //�敨OP�����T�[�r�X.get����������Rev()���R�[������B
        WEB3IfoFrontOrderService l_frontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        try
        {
            l_frontOrderService.getChangeOrderRev(l_changeAfterOrderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�����ŏI��<�[��>)<BR>
     * �ȉ������̏ꍇ�A�������Ɣ����ŏI���̃`�F�b�N���s�Ȃ��B<BR>
     * �|�[��܂Œ����A�o����܂Œ����̏ꍇ <BR>
     * �|�[�ꎞ�ԑт̒����̏ꍇ <BR>
     * <BR>
     * �P�j�������擾 <BR>
     * �@@������ԊǗ�.get������()�ɂĔ��������擾����B<BR>
     *<BR>
     * �Q�j�`�F�b�N����
     * �@@�E�i������ԊǗ�.is�[�ꎞ�ԑ�()==ture�j�̏ꍇ�A <BR>
     * �@@�R�j�ȍ~�̏������s�Ȃ��B <BR>
     * <BR>
     * �@@�E(����.���������敪==�h�[��܂Œ����h)�̏ꍇ�A <BR>
     * �@@�R�j�ȍ~�̏������s�Ȃ��B <BR>
     * <BR>
     * �E(����.���������敪==�h�o����܂Œ����h) ���A <BR>
     *    ���񒍕��i�����̌����������� == �P�j�Ŏ擾�����������@@�܂��� <BR>�@@
     *    �����̌����������� == null�j�̏ꍇ�A<BR>
     * �@@�R�j�ȍ~�̏������s�Ȃ��B <BR>
     * <BR>
     * <BR>
     * �R�j�����������Ԓ����� <BR>
     * <BR>
     * �@@�E�P�j�Ŏ擾�����������@@==�@@�����̐敨OP�������.�����ŏI�� <BR>
     * �@@�E�P�j�Ŏ擾�����������@@==�@@�����̐敨OP�������.���(�o�^)�p�~���̑O�c�Ɠ�(*) <BR>
     * �@@��L�����ꂩ�ɂ��Ă͂܂�ꍇ��<BR>
     * �@@�w���͂��ꂽ�����͌��݁A�����Ԓ��ƂȂ��Ă���܂���B�x�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00145<BR>
     * <BR>
     * (*)�c�Ɠ��v�Z.calc�c�Ɠ�()�ɂđO�c�Ɠ����Z�o����B <BR>
     * <BR>
     * [�c�Ɠ��v�Z.calc�c�Ɠ�()�ւ̈���]  <BR>
     * �@@��� �F �����̐敨OP�������.���(�o�^)�p�~��  <BR>
     * �@@���Z�^���Z���� �F -1�i�O�c�Ɠ��j<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP�������<BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * ���������敪<BR>
     * @@param l_datOrgOrderBizDate - (������������)<BR>
     * �������̔�����<BR>
     * @@throws WEB3BaseException
     */
    public void validateEveningSessionLastTradingDate(
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        String l_strExpirationDateType,
        Date l_datOrgOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateEveningSessionLastTradingDate("
            + "WEB3IfoTradedProductImpl, String, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoTradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j�������擾
        //������ԊǗ�.get������()�ɂĔ��������擾����B
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //���񒍕�
        boolean l_blnIsFirstOrderUnit = false;

        //�����̌����������� == �P�j�Ŏ擾�����������@@�܂��͈����̌����������� == null
        if ((WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datOrgOrderBizDate) == 0)
            || l_datOrgOrderBizDate == null)
        {
            l_blnIsFirstOrderUnit = true;
        }

        //�Q�j�`�F�b�N����
        //������ԊǗ�.is�[�ꎞ�ԑ�()==ture�̏ꍇ�A
        //����.���������敪==�h�[��܂Œ����h)�̏ꍇ
        //����.���������敪==�h�o����܂Œ����h) ���A���񒍕��̏ꍇ
        if (WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
            || (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType)
            || (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType)
                && l_blnIsFirstOrderUnit)))
        {
            //�R�j�����������Ԓ�����
            // �E�P�j�Ŏ擾���������� == �����̐敨OP�������.�����ŏI��
            // �E�P�j�Ŏ擾���������� == �����̐敨OP�������.���(�o�^)�p�~���̑O�c�Ɠ�(*)
            IfoTradedProductRow l_ifoTradedProductRow =
                (IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();

            //�����̐敨OP�������.���(�o�^)�p�~���̑O�c�Ɠ�
            Date l_datPreUnlistedDate = new WEB3GentradeBizDate(l_ifoTradedProductRow.getUnlistedDate()).roll(-1);
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_ifoTradedProduct.getLastTradingDate()) == 0
                || WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datPreUnlistedDate) == 0)
            {
                //�w���͂��ꂽ�����͌��݁A�����Ԓ��ƂȂ��Ă���܂���B�x�̗�O���X���[����B
                log.debug("���͂��ꂽ�����͌��݁A�����Ԓ��ƂȂ��Ă���܂���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00145,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͂��ꂽ�����͌��݁A�����Ԓ��ƂȂ��Ă���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�[��܂Œ����戵�\)<BR>
     * �[��܂Œ����戵�\�`�F�b�N���s���B <BR>
     * �|�u�[��܂Œ����v�戵�\��Ђł��邱�ƁB <BR>
     * �|�i�V�K�����o�^�̏ꍇ�̂݁j���݂̎��ԑт��[��ȊO�ł��邱�ƁB<BR>
     * �@@�@@�����������⒍���J�z�A�t�w�l�����AW�w�l�ؑցA�A�����������������͑ΏۊO�ƂȂ�B<BR>
     * �@@�@@���\�񒍕������̏ꍇ�́A�e�����T�[�r�X���Ń`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A����������return����B<BR>
     * <BR>
     * �@@�@@�@@�E���������^W�w�l�ؑ֎� <BR>
     * �@@�@@�@@�@@����.�����P��ID != 0 �̏ꍇ�B <BR>
     * <BR>
     * �@@�@@�@@�E�����J�z�� <BR>
     * �@@�@@�@@�@@����.���񒍕��̒����P��ID != null�@@���� <BR>
     * �@@�@@�@@�@@����.���񒍕��̒����P��ID > 0 �̏ꍇ�B <BR>
     * <BR>
     * �@@�@@�@@�E�t�w�l������ <BR>
     * �@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()�ɂ� <BR>
     * �@@�@@�@@�@@�@@�X�L�b�vvalidate���ꎷ�s�����戵��~ ���擾�����l == TRUE �̏ꍇ�B<BR>
     * �@@�@@�@@�@@�@@-ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * �@@�@@�@@�@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP) <BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�E�\�񒍕������^�A�������������� <BR>
     * �@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()�ɂ� <BR>
     * �@@�@@�@@�@@�@@�\�񒍕������t���O���擾�����l == TRUE �̏ꍇ�B <BR>
     * �@@�@@�@@�@@�@@-ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * �@@�@@�@@�@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER) <BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A�[��܂Œ����戵�\�`�F�b�N���s���B<BR>
     * <BR>
     * �@@(����.���������敪=�h�[��܂Œ����h)�̏ꍇ�̂݁A <BR>
     * �@@�ȉ��`�F�b�N���s���B�@@ <BR>
     * <BR>
     * �@@(����.is�[��܂Œ����戵�\==false) <BR>
     * �@@�܂��� <BR>
     * �@@(������ԊǗ�.is�[�ꎞ�ԑ�()==true) <BR>
     * �@@�̂����ꂩ�ɂ��Ă͂܂�ꍇ�A <BR>
     * �@@�w�[��܂Œ����͎�舵���܂���B�x�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02816<BR>
     * @@param l_blnIsEveningSessionOrderHandlingPossible - (is�[��܂Œ����戵�\)<BR>
     * false�F�[��܂Œ����戵�s�\<BR>
     * true�F�[��܂Œ����戵�\<BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * ���������敪<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID <BR>
     * �i�������ɁA�����Ώۂ̒����P��ID���Z�b�g�����B<BR>
     * �@@�V�K�����o�^�̏ꍇ�́A0�B�j<BR>
     * @@param l_firstOrderUnitId - (���񒍕��̒����P��ID)<BR>
     * ���񒍕��̒����P��ID�B <BR>
     * �i�J�z���ɂ̂ݐݒ肳���B�ȊO��null�j<BR>
     * @@throws WEB3BaseException
     */
    public void validateEveningSessionOrderPossibleHandling(
        boolean l_blnIsEveningSessionOrderHandlingPossible,
        String l_strExpirationDateType,
        long l_lngOrderUnitId,
        Long l_firstOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateEveningSessionOrderPossibleHandling("
            + "boolean, String, long, Long)";
        log.entering(STR_METHOD_NAME);

        //���������^W�w�l�ؑ֎�
        //����.�����P��ID != 0 �̏ꍇ
        if (l_lngOrderUnitId != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�����J�z��
        //����.���񒍕��̒����P��ID != null�@@����
        //����.���񒍕��̒����P��ID > 0 �̏ꍇ�B
        if (l_firstOrderUnitId != null && l_firstOrderUnitId.longValue() > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�t�w�l������
        //�@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()�ɂ�
        //�@@�@@�@@�@@�@@�X�L�b�vvalidate���ꎷ�s�����戵��~ ���擾�����l == TRUE �̏ꍇ
        //�@@�@@�@@�@@�@@-ThreadLocalSystemAttributesRegistry.getAttribute(
        //�@@�@@�@@�@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP)
        Object l_objSkipTriggerOrderStop = ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SKIP_TRIGGER_ORDER_STOP);

        if (BooleanEnum.TRUE.equals(l_objSkipTriggerOrderStop))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // �@@�@@�@@�E�\�񒍕������^�A��������������
        // �@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()�ɂ�
        // �@@�@@�@@�@@�@@�\�񒍕������t���O���擾�����l == TRUE �̏ꍇ�B
        // �@@�@@�@@�@@�@@-ThreadLocalSystemAttributesRegistry.getAttribute(
        // �@@�@@�@@�@@�@@�@@�@@WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER)
        Object l_objSuccChangeOrder = ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.SUCC_CHANGE_ORDER);

        if (BooleanEnum.TRUE.equals(l_objSuccChangeOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�Q�j�@@��L�ȊO�̏ꍇ�A�[��܂Œ����戵�\�`�F�b�N���s��
        //
        //�@@(����.���������敪=�h�[��܂Œ����h)�̏ꍇ�̂�
        //�@@�ȉ��`�F�b�N���s��
        if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType))
        {
            //�@@(����.is�[��܂Œ����戵�\==false)
            //�@@�܂���
            //�@@(������ԊǗ�.is�[�ꎞ�ԑ�()==true)
            //�@@�̂����ꂩ�ɂ��Ă͂܂�ꍇ
            //�@@�w�[��܂Œ����͎�舵���܂���B�x�̗�O���X���[����B
            if (!l_blnIsEveningSessionOrderHandlingPossible
                || WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
            {
                log.debug("�[��܂Œ����͎�舵���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02816,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�[��܂Œ����͎�舵���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��̈����̗]���^�Z������B<BR>
     * @@param l_bdA - (�폜��)<BR>
     * �폜��<BR>
     * @@param l_bdB - (����)<BR>
     * ����<BR>
     * @@return BigDecimal
     */
    private BigDecimal remainder(BigDecimal l_bdA, BigDecimal l_bdB)
    {
        final String STR_METHOD_NAME = "remainder(BigDecimal, BigDecimal)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdN = l_bdA.divide(l_bdB, 0, BigDecimal.ROUND_DOWN);
        BigDecimal l_bdReturnValue = l_bdA.subtract(l_bdB.multiply(l_bdN));

        log.exiting(STR_METHOD_NAME);
        return l_bdReturnValue;
    }

}
@
