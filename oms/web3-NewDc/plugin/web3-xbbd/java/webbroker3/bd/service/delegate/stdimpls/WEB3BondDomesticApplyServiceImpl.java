head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������T�[�r�XImpl(WEB3BondDomesticApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.226
Revision History : 2009/08/12 ���g (���u) �����̖��No.005
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondDomesticOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (����������T�[�r�XImpl)<BR>
 * ����������T�[�r�XImpl<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondDomesticApplyService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyServiceImpl.class);

    /**
     * @@roseuid 46A473FC031C
     */
    public WEB3BondDomesticApplyServiceImpl()
    {

    }

    /**
     * ����������T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate���������咍���Asubmit���������咍��<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD702033F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3BondDomesticApplyConfirmRequest)
        {
            //����������m�F���N�G�X�g
            l_response = this.validateBondDomesticApplyOrder(
                (WEB3BondDomesticApplyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3BondDomesticApplyCompleteRequest)
        {
            //���������劮�����N�G�X�g
            l_response = this.submitBondDomesticApplyOrder(
                (WEB3BondDomesticApplyCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���������咍��)<BR>
     * ���������咍�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate���������咍���v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �@@�V�[�P���X�}�@@:validate���������咍��<BR>
     * �@@��̈ʒu�@@�@@�@@:is����t���O()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false �̏ꍇ�A��O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondDomesticApplyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD70A011C
     */
    protected WEB3BondDomesticApplyConfirmResponse validateBondDomesticApplyOrder(
        WEB3BondDomesticApplyConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateBondDomesticApplyOrder(WEB3BondDomesticApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate������t�\
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //validate����\�ڋq(�⏕���� : SubAccount)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //get������(long)
        //[get������()�ɓn������]
        //����ID�F�@@���N�G�X�g�f�[�^.����ID
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_request.productId));

        //validate���������咍��(SubAccount, ������, double)
        //[validate���������咍��(�j�ɓn������]
        //�@@�⏕�����F�@@get�⏕����()�̖߂�l
        //�@@�������F�@@get������()�̖߂�l
        //�������ʁF�@@���N�G�X�g�f�[�^.�\�����z
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateBondDomesticApplyOrder(
            l_subAccount, l_bondProduct, Double.parseDouble(l_request.applyAmount));

        // create�������������(������)
        //[create�������������()�ɓn������]
        //�������F�@@get������()�̖߂�l
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondOrderManager.createBondDomesticExecutionDateInfo(l_bondProduct);

        //��������ʔ���(�������, String)
        //�R���X�g���N�^�ɓn������]
        //������ʁF�@@OrderTypeEnum.����������
        //����F�@@35�F��W���
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT, WEB3DealTypeDef.RECRUIT_TRADING);

        //calc��������n���(��������ʔ���, BigDecimal, ������, ���������)
        //[calc��������n���()�ɓn������]
        //��������ʔ���F�@@�쐬������������ʔ���
        //���ʁF�@@���N�G�X�g�f�[�^.�\�����z
        //�������F�@@�擾����������
        //���������F�@@create�������������()�̖߂�l
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdApplyAmount = new BigDecimal(l_request.applyAmount);

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcBondDomesticEstimatedPrice(
                    l_bondOrderTypeJudge, l_bdApplyAmount, l_bondProduct, l_bondExecuteDateInfo);

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //create�g�����V�K�������e<������>(Trader, ��������ʔ���, ������, BigDecimal)
        //create�g�����V�K�������e<������>()�ɓn������]
        //�I�y���[�^�F�@@get�㗝���͎�()�̖߂�l
        //��������ʔ���F�@@�쐬������������ʔ���
        //�������F�@@�擾����������
        //���ʁF�@@���N�G�X�g�f�[�^.�\�����z
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                l_trader, l_bondOrderTypeJudge, l_bondProduct, l_bdApplyAmount);

        //�����������X�V�C���^�Z�v�^()
        WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
            new WEB3BondDomesticOrderUpdateInterceptor();

        //���b�Z�[�W �v���p�e�B�E�Z�b�g
        //�g�����V�K�������e�F�@@create�g�����V�K�������e<������>()�̖߂�l
        l_bondDomesticOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //���������F�@@create�������������()�̖߂�l
        l_bondDomesticOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);

        //����n����v�Z���ʁF�@@calc��������n���()�̖߂�l
        l_bondDomesticOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);
/*
        //�����̖��No.005
        //validate����]��
        //validate����]��()�ɓn������]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //�������e�C���^�Z�v�^�F�@@�쐬���������������X�V�C���^�Z�v�^
        //�������e�F�@@�쐬�����g�����V�K�������e
        //������ʁF�@@OrderTypeEnum.����������
        //�]�͍X�V�t���O�F�@@false
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors = {l_bondDomesticOrderUpdateInterceptor};

        Object[] l_arrNewOrderSpecs = {l_bondNewOrderSpec};

        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT,
                false);

        //is����t���O()��false �̏ꍇ
        //����]�͔��茋�ʂ��`�F�b�N����B
        //is����t���O()�̖߂�l��false�̏ꍇ�A[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����
        if (!l_result.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[");
        }
        //�����̖��No.005
*/
        //createNewOrderId( )
        long l_lngNewOrderId = l_bondOrderManager.createNewOrderId();

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3BondDomesticApplyConfirmResponse l_response =
            (WEB3BondDomesticApplyConfirmResponse)l_request.createResponse();

        //����ID = createNewOrderId()�̖߂�l
        l_response.id = l_lngNewOrderId + "";

        //���񗘎q�����z = ����n����v�Z����.�o�ߗ��q�i�~�݁j
        l_response.initialInterestAdjustAmount = WEB3StringTypeUtility.formatNumber(
            l_bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());

        //��n���z = ����n����v�Z����.��n���(�~��)
        l_response.deliveryPrice = WEB3StringTypeUtility.formatNumber(
            l_bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());

        //���� = ���������.����
        l_response.executionUpdateDate = l_bondExecuteDateInfo.getExecuteDate();

        //��n��  = ���������.��n��
        l_response.deliveryDate = l_bondExecuteDateInfo.getDeliveryDate();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���������咍��)<BR>
     * ���������咍�������o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit���������咍���v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �@@�V�[�P���X�}�@@:submit���������咍��<BR>
     * �@@��̈ʒu�@@�@@�@@:is����t���O()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false �̏ꍇ�A��O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag   : BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondDomesticApplyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD71E0216
     */
    protected WEB3BondDomesticApplyCompleteResponse submitBondDomesticApplyOrder(
        WEB3BondDomesticApplyCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitBondDomesticApplyOrder(WEB3BondDomesticApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate������t�\
        WEB3BondTradingTimeManagement.validateOrderAccept();

        // get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //validate����\�ڋq(�⏕���� : SubAccount)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //get������(long)
        //[get������()�ɓn������]
        //����ID�F�@@���N�G�X�g�f�[�^.����ID
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_request.productId));

        //validate���������咍��(SubAccount, ������, double)
        //[validate���������咍��(�j�ɓn������]
        //�@@�⏕�����F�@@get�⏕����()�̖߂�l
        //�@@�������F�@@get������()�̖߂�l
        //�������ʁF�@@���N�G�X�g�f�[�^.�\�����z
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateBondDomesticApplyOrder(
            l_subAccount, l_bondProduct, Double.parseDouble(l_request.applyAmount));

        // create�������������(������)
        //[create�������������()�ɓn������]
        //�������F�@@get������()�̖߂�l
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondOrderManager.createBondDomesticExecutionDateInfo(l_bondProduct);

        //��������ʔ���
        //�R���X�g���N�^�ɓn������]
        //������ʁF�@@OrderTypeEnum.����������
        //����F�@@35�F��W���
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT, WEB3DealTypeDef.RECRUIT_TRADING);

        //calc��������n���(��������ʔ���, BigDecimal, ������, ���������)
        //[calc��������n���()�ɓn������]
        //��������ʔ���F�@@�쐬������������ʔ���
        //���ʁF�@@���N�G�X�g�f�[�^.�\�����z
        //�������F�@@�擾����������
        //���������F�@@create�������������()�̖߂�l
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdApplyAmount = new BigDecimal(l_request.applyAmount);

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcBondDomesticEstimatedPrice(
                l_bondOrderTypeJudge, l_bdApplyAmount, l_bondProduct, l_bondExecuteDateInfo);

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //create�g�����V�K�������e<������>(Trader, ��������ʔ���, ������, BigDecimal)
        //create�g�����V�K�������e<������>()�ɓn������]
        //�I�y���[�^�F�@@get�㗝���͎�()�̖߂�l
        //��������ʔ���F�@@�쐬������������ʔ���
        //�������F�@@�擾����������
        //���ʁF�@@���N�G�X�g�f�[�^.�\�����z
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpecDomesticBond(
                l_trader, l_bondOrderTypeJudge, l_bondProduct, l_bdApplyAmount);

        //�����������X�V�C���^�Z�v�^()
        WEB3BondDomesticOrderUpdateInterceptor l_bondDomesticOrderUpdateInterceptor =
            new WEB3BondDomesticOrderUpdateInterceptor();

        //���b�Z�[�W �v���p�e�B�E�Z�b�g
        //�g�����V�K�������e�F�@@create�g�����V�K�������e<������>()�̖߂�l
        l_bondDomesticOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //���������F�@@create�������������()�̖߂�l
        l_bondDomesticOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);

        //����n����v�Z���ʁF�@@calc��������n���()�̖߂�l
        l_bondDomesticOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);
/*
        //�����̖��No.005
        //validate����]��
        //validate����]��()�ɓn������]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //�������e�C���^�Z�v�^�F�@@�쐬���������������X�V�C���^�Z�v�^
        //�������e�F�@@�쐬�����g�����V�K�������e
        //������ʁF�@@OrderTypeEnum.����������
        //�]�͍X�V�t���O�F�@@true
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors = {l_bondDomesticOrderUpdateInterceptor};

        Object[] l_arrNewOrderSpecs = {l_bondNewOrderSpec};

        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.DOMESTIC_BOND_RECRUIT,
                true);

        //is����t���O()��false �̏ꍇ
        //����]�͔��茋�ʂ��`�F�b�N����B
        //is����t���O()�̖߂�l��false�̏ꍇ�A[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����
        if (!l_tradingPowerResult.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[");
        }
        //�����̖��No.005
*/
        //setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //[����]
        //�C���^�Z�v�^�F �������������������X�V�C���^�Z�v�^
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_bondDomesticOrderUpdateInterceptor);

        //submitNewOrder(
        //arg0 : SubAccount, arg1 : ProductTypeEnum,
        //arg2 : NewOrderSpec, arg3 : long, arg4 : String, arg5 : boolean)
        //      [submitNewOrder()�ɓn������]
        //      SubAccount�F�@@get�⏕����()�̖߂�l
        //      ProductTypeEnum�F�@@ProductTypeEnum.��
        //      NewOrderSpec�F�@@create�g�����V�K�������e<������>()�̖߂�l
        //      ����ID�F�@@���N�G�X�g�f�[�^.����ID
        //      ����p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        //      isSkip�����R���F�@@true
        OrderSubmissionResult l_orderSubmissionResult = l_bondOrderManager.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_bondNewOrderSpec,
            Long.parseLong(l_request.id),
            l_request.password,
            true);

        if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder"
                + l_orderSubmissionResult.getProcessingResult().getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "Error in submitNewOrder");
        }

        //�����򏈗������N�G�X�g�f�[�^.�Љ�敪 �� null �̏ꍇ
        if (l_request.introduceStoreDiv != null)
        {
            //get�������P��By����ID(long)
            //����ID�F�@@���N�G�X�g�f�[�^.����ID
            WEB3BondOrderUnit l_bondOrderUnit =
                l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));

            //�����P�ʏЉ�敪()
            WEB3GentradeOrderUnitIntroduceDiv l_orderUnitIntroduceDiv =
                new WEB3GentradeOrderUnitIntroduceDiv();

            //���b�Z�[�W �v���p�e�B�E�Z�b�g
            //�����P�ʂh�c�F�@@�������P��.�����P��ID
            l_orderUnitIntroduceDiv.setOrderUnitId(l_bondOrderUnit.getOrderUnitId());

            //�����h�c�F�@@�������P��.����ID
            l_orderUnitIntroduceDiv.setAccountId(l_bondOrderUnit.getAccountId());

            //�����^�C�v�F�@@�������P��.�����^�C�v
            l_orderUnitIntroduceDiv.setProductType(l_bondOrderUnit.getProductType());

            //�Љ�敪�F�@@���N�G�X�g�f�[�^.�Љ�敪
            l_orderUnitIntroduceDiv.setIntroduceBranchDiv(l_request.introduceStoreDiv);

            //�Љ�X�R�[�h�F�@@���N�G�X�g�f�[�^.�Љ�X�R�[�h
            l_orderUnitIntroduceDiv.setIntroduceBranchCode(l_request.introduceStoreCode);

            //�X�V�҃R�[�h�F�@@get�㗝���͎�()�̖߂�l �� null �̏ꍇ�A����.���҃R�[�h
            //����ȊO�̏ꍇ�A�ڋq.�ڋq�R�[�h
            if (l_trader != null)
            {
                l_orderUnitIntroduceDiv.setLastUpdater(l_trader.getTraderCode());
            }
            else
            {
                l_orderUnitIntroduceDiv.setLastUpdater(
                    l_subAccount.getMainAccount().getAccountCode());
            }

            //saveNew�����P�ʏЉ�敪()
            //�����P�ʏƉ�敪��o�^����
            l_orderUnitIntroduceDiv.saveNewOrderUnitIntroduceDivRow();
        }

        //createResponse( )
        WEB3BondDomesticApplyCompleteResponse l_response =
            (WEB3BondDomesticApplyCompleteResponse)l_request.createResponse();

        //�v���p�e�B�E�Z�b�g
        //�X�V����  = ���ݓ���
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
