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
filename	WEB3EquityPTSOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����}�l�[�W���iWEB3EquityPTSOrderManager.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 �g�E�N�| (���u) �V�K�쐬 ���f��1208
Revision History : 2007/12/19 ���� (���u) �d�l�ύX���f��No.1253,1254
Revision History : 2007/12/20 ���� (���u) �d�l�ύX���f��No.1262,1263,1270,1271
Revision History : 2008/02/13 �g�E�N�| (���u) �d�l�ύX���f��No.1296
Revision History : 2008/04/16 ���� (���u) ���f��No.1312
Revision History : 2008/10/06 ���� (���u) ���f��No.1323
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS�����}�l�[�W��)<BR>
 * PTS�����}�l�[�W���N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3EquityPTSOrderManager extends WEB3EquityOrderManager
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderManager.class);

    /**
     * @@roseuid 4766165002BF
     */
    public WEB3EquityPTSOrderManager()
    {

    }

    /**
     * (validatePTS���������\���)<BR>
     * �������\�Ȓ�����Ԃ����`�F�b�N����B<BR>
     * <BR>
     * PTS�����R���ʃ`�F�b�N.validatePTS���������\���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validatePTS���������\���()�Ɏw�肷�����]<BR>
     * �����F�@@�p�����[�^.����<BR>
     * @@param l_order - (����)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4734483E0368
     */
    public void validatePTSOrderForChangeability(Order l_order) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        try
        {
            // PTS�����R���ʃ`�F�b�N.validatePTS���������\���()�ɏ������Ϗ��idelegate�j����
            l_orderMgrResVal.validatePTSOrderForChangeability(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS��������\���)<BR>
     * ������\�Ȓ�����Ԃ����`�F�b�N����B<BR>
     * <BR>
     * PTS�����R���ʃ`�F�b�N.validatePTS��������\���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validatePTS��������\���()�Ɏw�肷�����]<BR>
     * �����F�@@�p�����[�^.����<BR>
     * @@param l_order - (����)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473448EB0045
     */
    public void validatePTSOrderForCancellation(Order l_order) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForCancellation(Order)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();
        try
        {
            l_orderMgrResVal.validatePTSOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS����)<BR>
     * �������͓��e�̃`�F�b�N�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u(PTS����)�������������R���v�Q�ƁB<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(PTS����)�������������R�� <BR>
     * ��̈ʒu�FisPTS�����J��( ) <BR>
     * �@@�@@�@@�@@�@@PTS�������J�݂��Ă��Ȃ��ꍇ��<BR>
     * �@@�@@�@@�@@�iisPTS�����J��()�̖߂�l=false�j��O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02967 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(PTS����)�������������R�� <BR>
     * ��̈ʒu�Fis��������J��(��n�� : Date, �⏕���� : �⏕����) <BR>
     * �@@�@@�@@�@@�@@����������J�݂��Ă��Ȃ��ꍇ��<BR>
     * �@@�@@�@@�@@�iis��������J��()�̖߂�l=false�j��O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00637 <BR>
     * ======================================================== <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_eqTypeNewCashBasedOrderSpec - (�����������e)<BR>
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 4734492B02BD
     */
    public EqTypeNewOrderValidationResult validatePTSOrder(
        SubAccount l_subAccount,
        WEB3EquityNewCashBasedOrderSpec l_eqTypeNewCashBasedOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSOrder(SubAccount, WEB3EquityNewCashBasedOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqTypeNewCashBasedOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // validate������t�\
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        //�����`�F�b�N���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        // �g�����������}�l�[�W�����擾����
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //validate����\�ڋq
        //����
        //�ڋq : �����̕⏕����.getMainAccount()
        //������ : PTS������ԊǗ�.get������()

        Timestamp l_tsPtsOrderBizDate = new Timestamp(
            WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());

        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_mainAccount,
                l_tsPtsOrderBizDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_validationResult.getProcessingResult().getErrorInfo()));
        }

        // isPTS�����J��()
        boolean l_blnIsPTSAccountOpen = l_mainAccount.isPTSAccountOpen();

        //�iisPTS�����J��()�̖߂�l=false�j��O��throw����B
        if (!l_blnIsPTSAccountOpen)
        {
            log.debug("PTS�����J�݂Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02967));
        }

        // validate�ŋ敪�iPTS�j(boolean, TaxTypeEnum)�ŋ敪�̃`�F�b�N
        // [�ݒ肷�����]
        // is�������F�@@�����������e.isSellOrder( )�̖߂�l
        // �ŋ敪�F�@@�����������e.getTaxType( )�̖߂�l
        l_orderMgrResVal.validatePTSTaxType(
            l_eqTypeNewCashBasedOrderSpec.isSellOrder(), l_eqTypeNewCashBasedOrderSpec.getTaxType());

        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

        // validate�����R�[�h
        // [�ݒ肷�����]
        // �����R�[�h�F�@@�����������e.getProductCode()�̖߂�l
        // �،���ЃR�[�h�F�@@�⏕����.getInstitution().getInstitutionCode()�̖߂�l
        WEB3EquityProduct l_web3EquityProduct = l_orderMgrResVal.validateProductCode(
            l_eqTypeNewCashBasedOrderSpec.getProductCode(),
            l_strInstitutionCode);

        // validate�s��R�[�h
        // [�ݒ肷�����]
        // �s��R�[�h�F�@@�����������e.getMarketCode()�̖߂�l
        // �،���ЃR�[�h�F�@@�⏕����.getInstitution().getInstitutionCode()�̖߂�l
        WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
            l_eqTypeNewCashBasedOrderSpec.getMarketCode(),
            l_strInstitutionCode);

        // validate�C���T�C�_�[
        // [�ݒ肷�����]
        // �⏕�����F�@@����.�⏕����
        // ���������F�@@validate�����R�[�h()�̖߂�l
        l_orderMgrResVal.validateInsider(l_subAccount, l_web3EquityProduct);

        // validate�ڋq�����ʎ����~�iPTS�j
        // [�ݒ肷�����]
        // �⏕�����F�@@����.�⏕����
        // ����ID�F�@@validate�����R�[�h( )�̖߂�l�̊��������I�u�W�F�N�g.����ID
        // ������ʁF�@@�������i�����������e.isSellOrder( )==true�j�̏ꍇ�́A
        //              "����������"�B
        //              ��L�ȊO�̏ꍇ�́A"����������"�B

        OrderTypeEnum l_orderType = OrderTypeEnum.EQUITY_BUY;
        if (l_eqTypeNewCashBasedOrderSpec.isSellOrder())
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }

        l_orderMgrResVal.validatePTSAccountProductOrderStop(
            l_subAccount, l_web3EquityProduct.getProductId(), l_orderType);

        // validate�������
        //[�ݒ肷�����]
        // �⏕�����F�@@����.�⏕����
        // ���������F�@@validate�����R�[�h( )�̖߂�l
        // �s��F�@@validate�s��R�[�h( )�̖߂�l
        // is�������F�@@�����������e.isSellOrder( )�̖߂�l
        WEB3EquityTradedProduct l_web3TradedProdcut =
            (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(
                l_subAccount,
                l_web3EquityProduct,
                l_market,
                l_eqTypeNewCashBasedOrderSpec.isSellOrder());

        // validate�戵�\PTS�s��
        // [�ݒ肷�����]
        // ���X�F�@@�⏕����.get����X()
        // ��������F�@@validate�������()�̖߂�l�̎�������I�u�W�F�N�g
        l_orderMgrResVal.validateHandlingPossiblePTSMarket(
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(), l_web3TradedProdcut);

        // l_eqTypeNewCashBasedOrderSpec.getTaxType()=="����" �܂��� "������������򒥎�"�̏ꍇ
        TaxTypeEnum l_taxTypeEnum = l_eqTypeNewCashBasedOrderSpec.getTaxType();
        if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
        {
            // is��������J��
            //[�ݒ肷�����]
            // ��n���F�@@�������.getDailyDeliveryDate()
            // �⏕�����F�@@����.�⏕����
            boolean l_blnIsSpecialAccountEstablished  = l_mainAccount.isSpecialAccountEstablished(
                l_web3TradedProdcut.getDailyDeliveryDate(), l_subAccount);

            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("�����̓�������J�݂Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return new EqTypeNewOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00637));
            }
        }

        // validate��������戵�K��
        // [�ݒ肷�����]
        // �ŋ敪�F�@@�����������e.get�ŋ敪()�̖߂�l
        // ���������F�@@validate�����R�[�h( )�̖߂�l
        // is�������F�@@�����������e.isBuyOrder()�̖߂�l
        l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
            l_taxTypeEnum, l_web3EquityProduct, l_eqTypeNewCashBasedOrderSpec.isBuyOrder());

        // validate��������
        Date l_datFirstOrderBizDate = null;
        long l_intFirstOrderUnitId = 0;
        if (l_eqTypeNewCashBasedOrderSpec.getFirstOrderUnitId() != null)
        {
            l_intFirstOrderUnitId = (l_eqTypeNewCashBasedOrderSpec.getFirstOrderUnitId()).longValue();
        }

        if (l_intFirstOrderUnitId > 0)
        {
            try
            {
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_intFirstOrderUnitId);

                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_datFirstOrderBizDate = WEB3DateUtility.getDate(
                    l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
                log.exiting(STR_METHOD_NAME);
                return new EqTypeNewOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }
        }

        // �⏕�����F�@@�����̕⏕�����I�u�W�F�N�g
        // �����P��ID�F�@@0(�F�V�K����)
        // ��������F�@@validate�������( )�̖߂�l�̎�������I�u�W�F�N�g
        // �������������F
        //�@@�E�����������e.get���񒍕��̒����P��ID > 0�i==�J�z�j�̏ꍇ
        //�@@�@@�g�����������}�l�[�W��.getOrderUnit(���񒍕��̒����P��ID)��
        //�@@�@@�����P��.�������B
        //�@@�E��L�ȊO�i==�V�K�����o�^�j�̏ꍇ�Anull
        // �����������F�@@�����������e.getOrderExpDate( )
        // ���������F�@@�����������e.get��������( )
        // ���s�����F�@@�����������e.getExecConditionType( )
        // is�o����܂Œ����F�@@�����������e.is�o����܂Œ���( )
        // �M�p����敪�F�@@�hDEFAULT�h
        // �l�i�����F�@@�����������e.get�l�i����( )
        // �s��R�[�h�F�@@�����������e.getMarketCode( )
        l_orderMgrResVal.validateOrderCondition(
            (WEB3GentradeSubAccount)l_subAccount,
            0,
            l_web3TradedProdcut,
            l_datFirstOrderBizDate,
            l_eqTypeNewCashBasedOrderSpec.getOrderExpDate(),
            l_eqTypeNewCashBasedOrderSpec.getOrderCond(),
            l_eqTypeNewCashBasedOrderSpec.getExecConditionType(),
            l_eqTypeNewCashBasedOrderSpec.isOrderUntilDeadLine(),
            WEB3MarginTradingDivDef.DEFAULT,
            l_eqTypeNewCashBasedOrderSpec.getPriceConditionType(),
            l_eqTypeNewCashBasedOrderSpec.getMarketCode());

        // validate�����iPTS�j
        // [�ݒ肷�����]
        // ��������F�@@validate�������()�̖߂�l�̎�������I�u�W�F�N�g
        // ���X�F�@@�⏕����.get����X()
        // �����F�@@�����������e.getQuantity()
        l_orderMgrResVal.validatePTSQuantity(
            l_web3TradedProdcut,
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
            l_eqTypeNewCashBasedOrderSpec.getQuantity());

        // validate�w�l�����iPTS�j
        // [�ݒ肷�����]
        // �����������e�F�@@����.�����������e
        l_orderMgrResVal.validatePTSLimitOrder(l_eqTypeNewCashBasedOrderSpec);

        // validate�����P���iPTS�j
        // [�ݒ肷�����]
        // �w�l�F�@@�����������e.getLimitPrice()
        // ��������F�@@validate�������()�̖߂�l�̎�������I�u�W�F�N�g
        // �⏕�����F�@@����.�⏕����
        boolean l_blnIsValidatePTSPrice = l_orderMgrResVal.validatePTSPrice(
            l_eqTypeNewCashBasedOrderSpec.getLimitPrice(),
            l_web3TradedProdcut,
            l_subAccount);

        if (!l_blnIsValidatePTSPrice)
        {
            log.debug("�����P���`�F�b�N�G���[�i�w�l���K�؂ł͂���܂���j�B");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00293));
        }

        // �������i�����������e.isSellOrder() == true�j�̏ꍇ
        if (l_eqTypeNewCashBasedOrderSpec.isSellOrder())
        {
            // validate���t�\����
            // [�ݒ肷�����]
            // �⏕�����F�@@����.�⏕����
            // ��������F�@@validate�������()�̖߂�l�̎�������I�u�W�F�N�g
            // �����F�@@�����������e.getQuantity()
            // �ŋ敪�F�@@�����������e.getTaxType()
            l_orderMgrResVal.validateSellableAssetQuantity(
                l_subAccount,
                l_web3TradedProdcut,
                l_eqTypeNewCashBasedOrderSpec.getQuantity(),
                l_eqTypeNewCashBasedOrderSpec.getTaxType());
        }

        //validate�@@�\�a������(�⏕����)
        l_orderMgrResVal.validateMechanismDepositAgree(l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (validatePTS��������)<BR>
     * �����������e�̃`�F�b�N�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�iPTS�����j���������R���v�Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�iPTS�����j���������R�� <BR>
     * ��̈ʒu�FisPTS�����J��( ) <BR>
     * �@@�@@�@@�@@�@@PTS�������J�݂��Ă��Ȃ��ꍇ��<BR>
     * �@@�@@�@@�@@�iisPTS�����J��()�̖߂�l=false�j��O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02967 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�iPTS�����j���������R�� <BR>
     * ��̈ʒu�Fis��������J��(��n�� : Date, �⏕���� : �⏕����) <BR>
     * �@@�@@�@@�@@�@@��������w��ŁA����������J�݂��Ă��Ȃ��ꍇ��<BR>
     * �@@�@@�@@�@@�@@��O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00637 <BR>
     * ======================================================== <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_eqChangeOrderSpec - (���������������e)<BR>
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 4734495001EE
     */
    public EqTypeOrderValidationResult validatePTSChangeOrder(
        SubAccount l_subAccount,
        EqTypeChangeOrderSpec l_eqChangeOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSChangeOrder(SubAccount, EqTypeChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqChangeOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //get�ڋq
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //isPTS�����J��( )
        //�iisPTS�����J��()�̖߂�l��false�j��O��throw����B
        if (!l_mainAccount.isPTSAccountOpen())
        {
            log.debug("PTS�����J�݂Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02967));
        }

        // PTS�����R���ʃ`�F�b�N�C���X�^���X���擾����B
        WEB3EquityPTSOrderManagerReusableValidations l_reusableValidations =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // �����`�F�b�N���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //validate����\�ڋq(�ڋq : �ڋq, ������ : Timestamp)
        //�����̐ݒ�d�l�͈ȉ��̒ʂ�
        //�ڋq : �����̕⏕����.getMainAccount( )
        //������ : PTS������ԊǗ�.get������( )
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_mainAccount,
                new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime()));

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_orderValidationResult.getProcessingResult().getErrorInfo()));
        }


        Order l_order = null;
        try
        {
            //validateOrderIdForExistence(����ID : long)
            l_order = l_reusableValidations.validateOrderIdForExistence(l_eqChangeOrderSpec.getOrderId());

            //validatePTS���������\���(����)
            //���� : PTS�����R���ʃ`�F�b�N.validateOrderIdForExistence( )
            l_reusableValidations.validatePTSOrderForChangeability(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.error(l_processingResult.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(l_processingResult);
        }

        //�����P�ʈꗗ���擾����B
        //�擾���������P�ʔz���0�Ԗڂ̗v�f������Ώۂ̒����P�ʂƂ���B
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];

        //�����Ώۂ̒����P��.�s��ID�ɊY������s��}�X�^
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        Market l_market = null;

        try
        {
            l_market = l_finObjectManager.getMarket(l_eqtypeOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }

        //�����Ώۂ̒����P��.�s��ID�ɊY������s��}�X�^.�s��R�[�h
        String l_strMarketCode = l_market.getMarketCode();

        //validate�s��R�[�h(String, String)
        //�s��R�[�h : �����Ώۂ̒����P��.�s��ID�ɊY������s��}�X�^.�s��R�[�h
        //�،���ЃR�[�h : �����̕⏕����.getInstitution( ).�،���ЃR�[�h
        Market l_validateMarket = l_reusableValidations.validateMarket(
            l_strMarketCode,
            l_subAccount.getInstitution().getInstitutionCode());

        //�����Ώۂ̒����P��.getProduct( )
        EqTypeProduct l_eqTypeProduct = (EqTypeProduct)l_eqTypeOrderUnit.getProduct();

        //validate�C���T�C�_�[(�⏕����, ��������)
        //�⏕���� : �����̕⏕����
        //�������� : �����Ώۂ̒����P��.getProduct( )
        l_reusableValidations.validateInsider(
            l_subAccount,
            l_eqTypeProduct);

        //validate�ڋq�����ʎ����~�iPTS�j(�⏕����, long, OrderTypeEnum)
        //�⏕���� : �����̕⏕����
        //����ID : �����Ώۂ̒����P��.����ID
        //������� : �����Ώۂ̒����P��.�������
        l_reusableValidations.validatePTSAccountProductOrderStop(
            l_subAccount,
            l_eqtypeOrderUnitRow.getProductId(),
            l_eqtypeOrderUnitRow.getOrderType());

        //validate�������(��������, �s��)
        //�������� : �����Ώۂ̒����P��.getProduct( )
        //�s�� : PTS�����R���ʃ`�F�b�N.validate�s��R�[�h( )
        WEB3EquityTradedProduct l_equityTradedProduct =
            (WEB3EquityTradedProduct)l_reusableValidations.validateTradedProduct(
            l_eqTypeProduct, l_validateMarket);

        //�⏕����.get����X( )
        WEB3GentradeBranch l_branch = ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();

        // validate�戵�\PTS�s��(���X, �������)
        //���X : �����̕⏕����.get����X( )
        //������� : PTS�����R���ʃ`�F�b�N.validate�������( )
        l_reusableValidations.validateHandlingPossiblePTSMarket(
            l_branch,
            l_equityTradedProduct);

        //createPTS�����������e
        WEB3EquityChangeOrderSpec l_equityChangeOrderSpec = (WEB3EquityChangeOrderSpec)l_eqChangeOrderSpec;
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec =
            l_equityChangeOrderSpec.createPTSOrderSpec();

        //[get���񒍕��̒����P��( )�̈����ݒ�]
        //�����P�� : �����Ώۂ̒����P��
        EqTypeOrderUnit l_firstEqTypeOrderUnit = this.getFirstOrderUnit(l_eqTypeOrderUnit);

        EqtypeOrderUnitRow l_firstEqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_firstEqTypeOrderUnit.getDataSourceObject();

        //������������ : PTS�����}�l�[�W��.get���񒍕��̒����P��( ).������
        Date l_datBizDate = WEB3DateUtility.getDate(
                l_firstEqTypeOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //validate��������(�⏕����, long, �������, Date, Date, String,
        //EqTypeExecutionConditionType, boolean, String, String, String)
        //�⏕���� : �����̕⏕����
        //�����P��ID : �����Ώۂ̒����P��.�����P��ID
        //������� : PTS�����R���ʃ`�F�b�N.validate�������( )
        //������������ : PTS�����}�l�[�W��.get���񒍕��̒����P��( ).������
        //�@@[get���񒍕��̒����P��( )�̈����ݒ�]
        //�@@�@@�����P�� : �����Ώۂ̒����P��
        //���������� : �����������e.getOrderExpDate( )
        //�������� : �����������e.get��������( )
        //���s���� : �����������e.getExecConditionType( )
        //is�o����܂Œ��� : �����������e.is�o����܂Œ���( )
        //�M�p����敪 : "0�FDEFAULT"
        //�l�i���� : �����������e.get�l�i����( )
        //�s��R�[�h : �����Ώۂ̒����P��.�s��ID�ɊY������s��}�X�^.�s��R�[�h
        l_reusableValidations.validateOrderCondition(
            (WEB3GentradeSubAccount)l_subAccount,
            l_eqtypeOrderUnitRow.getOrderUnitId(),
            l_equityTradedProduct,
            l_datBizDate,
            l_equityNewCashBasedOrderSpec.getOrderExpDate(),
            l_equityNewCashBasedOrderSpec.getOrderCond(),
            l_equityNewCashBasedOrderSpec.getExecConditionType(),
            l_equityNewCashBasedOrderSpec.isOrderUntilDeadLine(),
            WEB3MarginTradingDivDef.DEFAULT,
            l_equityNewCashBasedOrderSpec.getPriceConditionType(),
            l_strMarketCode);

        //�����������e.getTaxType( ) == "����" �܂��� "������������򒥎�" �̏ꍇ
        if (TaxTypeEnum.SPECIAL.equals(l_equityNewCashBasedOrderSpec.getTaxType())
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_equityNewCashBasedOrderSpec.getTaxType()))
        {
            //is��������J��(��n�� : Date, �⏕���� : �⏕����)
            //��n�� : PTS�����R���ʃ`�F�b�N.validate�������( ).getDailyDeliveryDate( )
            //�⏕���� : �����̕⏕����
            boolean l_blnIsSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(
                l_equityTradedProduct.getDailyDeliveryDate(),
                l_subAccount);

            //��������w��ŁA����������J�݂��Ă��Ȃ��ꍇ�͗�O��throw����B
            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("�����̓�������J�݂Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                return new EqTypeOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00637));
            }
        }

        //validate�����iPTS�j(�������, ���X, double)
        //������� : PTS�����R���ʃ`�F�b�N.validate�������( )
        //���X : �����̕⏕����.get����X( )
        //���� : �����������e.getQuantity( )
        l_reusableValidations.validatePTSQuantity(
            l_equityTradedProduct,
            l_branch,
            l_equityNewCashBasedOrderSpec.getQuantity());

        //validate�w�l�����iPTS�j(�����������e)
        //�����������e : �����̊��������������e.createPTS�����������e( )
        l_reusableValidations.validatePTSLimitOrder(l_equityNewCashBasedOrderSpec);

        //validate�����P���iPTS�j(double, �������, �⏕����)
        //�w�l : �����������e.getLimitPrice( )
        //������� : PTS�����R���ʃ`�F�b�N.validate�������( )
        //�⏕���� : �����̕⏕����
        boolean l_blnIsValidatePTSPrice = l_reusableValidations.validatePTSPrice(
            l_equityNewCashBasedOrderSpec.getLimitPrice(),
            l_equityTradedProduct,
            l_subAccount);

        if (!l_blnIsValidatePTSPrice)
        {
            log.debug("�����P���`�F�b�N�G���[�i�w�l���K�؂ł͂���܂���j�B");
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00293));
        }

        //���������������e.get�����l�ڍ�( )
        WEB3EquityChangeOrderUnitEntry l_eqChangeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry)l_equityChangeOrderSpec.getChangeOrderUnitEntry();

        //validate�������ځiPTS�j
        //�����P�� : �����Ώۂ̒����P��
        //�����㊔�� : ���������������e.get�����l�ڍ�( ).getAfterChangeOriginalQuantity( )
        //������w�l : ���������������e.get�����l�ڍ�( ).getAfterChangePrice( )
        //�����㎷�s���� : ���������������e.get�����l�ڍ�( ).get�����㎷�s����( )
        //������l�i���� : ���������������e.get�����l�ڍ�( ).get������l�i����( )
        //�����㔭������ : ���������������e.get�����l�ڍ�( ).get�����㔭������( )
        //�����㔭���������Z�q : ���������������e.get�����l�ڍ�( ).get�����㔭���������Z�q( )
        //������t�w�l��l : ���������������e.get�����l�ڍ�( ).get������t�w�l��l( )
        //������iW�w�l�j�����w�l : ���������������e.get�����l�ڍ�( ).get������iW�w�l�j�����w�l( )
        //������iW�w�l�j���s���� : ���������������e.get�����l�ڍ�( ).get������iW�w�l�j���s����( )
        //������is�o����܂Œ��� : ���������������e.get�����l�ڍ�( ).get������is�o����܂Œ���( )
        //�����㒍�������� : ���������������e.get�����l�ڍ�( ).get�����㒍��������( )
        //�����㌈�ώw��G���g�� : Null
        l_reusableValidations.validatePTSChangeItem(
            l_eqTypeOrderUnit,
            l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
            l_eqChangeOrderUnitEntry.getAfterChangePrice(),
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType(),
            l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType(),
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondType(),
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondOperator(),
            l_eqChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice(),
            l_eqChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice(),
            l_eqChangeOrderUnitEntry.getModifiedWlimitExecCondType(),
            l_eqChangeOrderUnitEntry.getChangeAfterIsOrderUntilDeadLine(),
            l_eqChangeOrderUnitEntry.getModifiedExpirationDate(),
            null);

        //validate����������Rev����iPTS�j
        //�����O�����P�� : �����Ώۂ̒����P��
        //�����㊔�� : ���������������e.get�����l�ڍ�( ).getAfterChangeOriginalQuantity( )
        //������w�l : ���������������e.get�����l�ڍ�( ).getAfterChangePrice( )
        //�����㎷�s���� : ���������������e.get�����l�ڍ�( ).get�����㎷�s����( )
        //������l�i���� : ���������������e.get�����l�ڍ�( ).get������l�i����( )
        l_reusableValidations.validatePTSChangeOrderRevUpperLimit(
            l_eqTypeOrderUnit,
            l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
            l_eqChangeOrderUnitEntry.getAfterChangePrice(),
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType(),
            l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType());

        //���������R������(ProcessingResult, boolean)
        //�����R������ : ProcessingResult.SUCCESS_RESULT�i�����R��OK�j
        //�󔄂�K���Ώۃt���O : false�i�Œ�j
        WEB3EquityOrderValidationResult l_equityOrderValidationResult =
            new WEB3EquityOrderValidationResult(
                ProcessingResult.SUCCESS_RESULT,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_equityOrderValidationResult;

    }

    /**
     * (validatePTS�������)<BR>
     * ����������e�̃`�F�b�N�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�iPTS�����j��������R���v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_eqTypeCancelOrderSpec - (��������������e)<BR>
     * @@return EqTypeOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 4734495A0095
     */
    public EqTypeOrderValidationResult validatePTSCancelOrder(
        SubAccount l_subAccount,
        EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSCancelOrder(SubAccount, EqTypeCancelOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqTypeCancelOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderPtsMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        //�����`�F�b�N���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //validate����\�ڋq
        //����
        //�ڋq : �����̕⏕����.getMainAccount()
        //������ : PTS������ԊǗ�.get������()

        Timestamp l_tsPtsOrderBizDate = new Timestamp(
            WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());

        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount(),
                l_tsPtsOrderBizDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_validationResult.getProcessingResult().getErrorInfo()));
        }

        Order l_order = null;
        // validateOrderIdForExistence(����ID : long)
        // ����:
        // ����ID�F��������������e.getOrderId()
        try
        {
            l_order =
                l_orderPtsMgrResVal.validateOrderIdForExistence(l_eqTypeCancelOrderSpec.getOrderId());
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.error(l_processingResult.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(l_processingResult);
        }

        // validatePTS��������\���(����)
        try
        {
            l_orderPtsMgrResVal.validatePTSOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            ProcessingResult l_processingResult =
                l_ex.getValidationResult().getProcessingResult();
            log.error(l_processingResult.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(l_processingResult);
        }

        //�����P�ʈꗗ���擾����B
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit  = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //validate�s��R�[�h(String, String)
        //����:
        //�s��R�[�h : ����Ώۂ̒����P��.�s��ID�ɊY������s��}�X�^.�s��R�[�h
        //�،���ЃR�[�h : �����̕⏕����.getInstitution( ).�،���ЃR�[�h

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        String l_strMarketCode = null;
        try
        {
            //�s��R�[�h���擾����B
            l_strMarketCode =
                (l_finObjectManager.getMarket(l_orderUnitRow.getMarketId())).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        WEB3GentradeMarket l_market =
            (WEB3GentradeMarket)l_orderPtsMgrResVal.validateMarket(
                l_strMarketCode,
                l_subAccount.getInstitution().getInstitutionCode());

        //validate�������(��������, �s��)
        //����:
        //�������� : ����Ώۂ̒����P��.getProduct()
        //�s�� : PTS�����R���ʃ`�F�b�N.validate�s��R�[�h()

        WEB3EquityTradedProduct l_tradedProduct = null;

        l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderPtsMgrResVal.validateTradedProduct(
                (EqTypeProduct)l_orderUnit.getProduct(), l_market);

        //validate�戵�\PTS�s��(���X, �������)
        // ����:
        //���X : �����̕⏕����.get����X()
        //������� : PTS�����R���ʃ`�F�b�N.validate�������()
        l_orderPtsMgrResVal.validateHandlingPossiblePTSMarket(
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(), l_tradedProduct);

        log.exiting(STR_METHOD_NAME);
        return new EqTypeOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }


    /**
     * (validatePTS�s��ʎ���\������z)<BR>
     * �T�Z���z�l���A��ЁE���X�E�s��ň�x��<BR>
     * �@@����\�ȏ�����z�𒴂��Ă��Ȃ����`�F�b�N���s���B <BR>
     * �i* PTS�����R���ʃ`�F�b�N.validatePTS�s��ʎ���\������z( )�ɈϏ�����B�j<BR>
     * @@param l_branch - (���X)<BR>
     * @@param l_market - (�s��)<BR>
     * @@param l_dblRestraintTurnover - (�S���������)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473449630381
     */
    public void validatePTSMarketMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSMarketMaxHandlingPrice(Branch, Market, double)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // PTS�����R���ʃ`�F�b�N.validatePTS�s��ʎ���\������z( )�ɈϏ�����B
        l_orderMgrResVal.validatePTSMarketMaxHandlingPrice(l_branch, l_market, l_dblRestraintTurnover);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�ڋq�����ʎ����~�iPTS�j)<BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B<BR>
     * <BR>
     * �i* PTS�����R���`�F�b�N.validate�ڋq�����ʎ����~�iPTS�j( )�ɈϏ�����B�j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID�B<BR>
     * ��������肵�Ȃ��ꍇ�́A0�i�S�����j���Z�b�g�B<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 474E0A510298
     */
    public void validatePTSAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSAccountProductOrderStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // PTS�����R���`�F�b�N.validate�ڋq�����ʎ����~�iPTS�j( )�ɈϏ�����B
        l_orderMgrResVal.validatePTSAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderType);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�戵�\PTS�s��)<BR>
     * ��Е��X�̎戵�\�s�ꂩ���`�F�b�N����B <BR>
     * <BR>
     * �i* PTS�����R���`�F�b�N.validate�戵�\PTS�s��( )�ɈϏ�����B�j<BR>
     * @@param l_branch - (���X)<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 474FD91E0017
     */
    public void validateHandlingPossiblePTSMarket(
        Branch l_branch,
        WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossiblePTSMarket(Branch, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // ���������R���`�F�b�N.���������R���`�F�b�N.validate�戵�\PTS�s��()�ɈϏ�����B
        l_orderMgrResVal.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�����������iPTS�j)<BR>
     * �������������������ł��邩�ǂ����̔�����s���B<BR>
     * <BR>
     * �������������������̏ꍇ��true���A<BR>
     * ��L�ȊO�̏ꍇ��false��Ԃ��B<BR>
     * �i* PTS�����R���ʃ`�F�b�N.is����������(PTS)( )�ɈϏ�����B�j<BR>
     * @@param l_tsOrderBizDate - (������)<BR>
     * �������B<BR>
     * @@param l_tsYearlyBooksClosingDate - (�����m���)<BR>
     * �����m������w�肷��B�ʏ�́A�y���������e�[�u���z���Z�����w�肳���B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4753F64302B1
     */
    public boolean isPTSDevidendRightDate(
        Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSDevidendRightDate(Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        log.exiting(STR_METHOD_NAME);
        // ���������R���ʃ`�F�b�N.is����������(PTS)()�ɈϏ�����
        return l_orderMgrResVal.isPTSDevidendRightDate(
            l_tsOrderBizDate,
            l_tsYearlyBooksClosingDate);
    }

    /**
     * (get�l������l�iPTS�j)<BR>
     * �l������l���擾����B <BR>
     * <BR>
     * �P�j�@@��l�擾 <BR>
     * �@@PTS�����R���ʃ`�F�b�N.calc��l�iPTS�l���`�F�b�N�p�j���R�[������B <BR>
     * <BR>
     * �@@[�����ݒ�]<BR>
     * �@@��������F�@@����.������� <BR>
     * <BR>
     * �Q�j�@@�l���擾 <BR>
     * �@@PTS�����R���ʃ`�F�b�N.calc�l��()���R�[������B <BR>
     * <BR>
     * �@@[�����ݒ�]<BR>
     * �@@��������F�@@����.������� <BR>
     * �@@��l�F�@@�P�jcalc��l�i�l���`�F�b�N�p�j()�̖߂�l <BR>
     * �@@���/�����敪�F�@@��� <BR>
     * <BR>
     * �R�j�@@�w�l�P�ʎ擾 <BR>
     * �@@�g���v���_�N�g�}�l�[�W��.get���ݒl�i�j���R�[������B <BR>
     * <BR>
     * �@@[�����ݒ�]<BR>
     * �@@��������F�@@����.������� <BR>
     * �@@��l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l�@@<BR>
     * �@@�@@�@@�@@�{�@@�Q�jcalc�l��()�̖߂�l <BR>
     * <BR>
     * �S�j�@@�l������l�擾 <BR>
     * �@@PTS�����R���ʃ`�F�b�N.calc�l�����(��l,�l��,�w�l�P��)���R�[������B <BR>
     * <BR>
     * �@@[�����ݒ�] <BR>
     * �@@��l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l <BR>
     * �@@�l���F�@@�Q�jcalc�l��()�̖߂�l <BR>
     * �@@�w�l�P�ʁF�@@�R�jget���ݒl()�̖߂�l <BR>
     * <BR>
     * �T�j�@@�S�jcalc�l�����()�̖߂�l��ԋp����B <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 475FBAE800A8
     */
    public double getPTSStopHighPrice(WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPTSStopHighPrice(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // 1)�@@��l�擾
        // PTS�����R���ʃ`�F�b�N.calc��l�iPTS�l���`�F�b�N�p�j���R�[������B
        //�@@[�����ݒ�]
        //�@@��������F�@@����.�������

        double l_dblBasePrice = l_orderMgrResVal.calcBasePriceForPTSPriceRange(l_tradedProduct);

        BigDecimal l_bdBasePrice = new BigDecimal(String.valueOf(l_dblBasePrice));

        // 2)�@@�l���擾
        // PTS�����R���ʃ`�F�b�N.calc�l��()���R�[������
        // [�����ݒ�]
        // ��������F�@@����.�������
        // ��l�F�@@�P�jcalc��l�i�l���`�F�b�N�p�j()�̖߂�l
        // ���/�����敪�F�@@���

        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MAXIMUM);

        // 3)�@@�w�l�P�ʎ擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        //[�����ݒ�]
        //�@@��������F�@@����.�������
        //�@@��l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l�@@�{�@@�Q�jcalc�l��()�̖߂�l
        double l_dblbase = (l_bdBasePrice.add(new BigDecimal(String.valueOf(l_dblPriceRange)))).doubleValue();
        double l_dblTickValue = l_productManager.getTickValue(
            l_tradedProduct,
            l_dblbase);

        // 4)�@@�l������l�擾
        //�@@[�����ݒ�]
        // �@@��l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l
        // �@@�l���F�@@�Q�jcalc�l��()�̖߂�l
        // �@@�w�l�P�ʁF�@@�R�jget���ݒl()�̖߂�l
        double l_dblStopHighPrice = l_orderMgrResVal.calcStopHighPrice(
            l_dblBasePrice,
            l_dblPriceRange,
            l_dblTickValue);

        log.exiting(STR_METHOD_NAME);
        // �T�j�@@�S�jcalc�l�����()�̖߂�l��ԋp����B
        return l_dblStopHighPrice;
    }

    /**
     * (get�l�������l�iPTS�j)<BR>
     * �l�������l���擾����B <BR>
     * <BR>
     * �P�j�@@��l�擾 <BR>
     * �@@PTS�����R���ʃ`�F�b�N.calc��l�iPTS�l���`�F�b�N�p�j()���R�[������B <BR>
     * <BR>
     * �@@[�����ݒ�]<BR>
     * �@@��������F�@@����.������� <BR>
     * <BR>
     * �Q�j�@@�l���擾 <BR>
     * �@@PTS�����R���ʃ`�F�b�N.calc�l��()���R�[������B <BR>
     * <BR>
     * �@@[�����ݒ�]<BR>
     * �@@��������F�@@����.������� <BR>
     * �@@��l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l <BR>
     * �@@���/�����敪�F�@@���� <BR>
     * <BR>
     * �R�j�@@�l�������l�擾 <BR>
     * �@@�R�|�P�j�@@�i��l�|�l���j��0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@1��ԋp����B <BR>
     * <BR>
     * �@@�R�|�Q�j�@@�R�|�P�j�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�i��l�|�l���j��ԋp����B <BR>
     * <BR>
     * �@@����l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l <BR>
     * �@@���l���F�@@�Q�jcalc�l��()�̖߂�l <BR>
     * �@@�������_�ȉ��؂�̂ĂƂ���B�@@ <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 475FBC2B00F5
     */
    public double getPTSStopLowPrice(WEB3EquityTradedProduct l_tradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPTSStopLowPrice(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityPTSOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityPTSOrderManagerReusableValidations)
                WEB3EquityPTSOrderManagerReusableValidations.getInstance();

        // 1)�@@��l�擾
        // PTS�����R���ʃ`�F�b�N.calc��l�iPTS�l���`�F�b�N�p�j()���R�[������B
        //�@@[�����ݒ�]
        // �@@��������F�@@����.�������

        double l_dblBasePrice = l_orderMgrResVal.calcBasePriceForPTSPriceRange(l_tradedProduct);

        BigDecimal l_bdBasePrice = new BigDecimal(String.valueOf(l_dblBasePrice));

        // 2)�@@�l���擾
        //�@@[�����ݒ�]
        // �@@��������F�@@����.�������
        // �@@��l�F�@@�P�jcalc��l�iPTS�l���`�F�b�N�p�j()�̖߂�l
        // �@@���/�����敪�F�@@����

        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MINIMUM);

        double l_dblStopLowPriceTemp = (l_bdBasePrice.subtract(
            new BigDecimal(String.valueOf(l_dblPriceRange)))).doubleValue();

        // 3)�@@�l�������l�擾
        double l_dblStopLowPrice = Math.floor(l_dblStopLowPriceTemp);
        //�@@�R�|�P�j�@@�i��l�|�l���j��0�̏ꍇ
        //�@@�@@1��ԋp����B
        if (l_dblStopLowPrice <= 0.0D)
        {
            l_dblStopLowPrice = 1.0D;
        }

        //�@@�R�|�Q�j�@@�R�|�P�j�ȊO�̏ꍇ
        //�@@�@@�@@�i��l�|�l���j��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_dblStopLowPrice;
    }
}
@
