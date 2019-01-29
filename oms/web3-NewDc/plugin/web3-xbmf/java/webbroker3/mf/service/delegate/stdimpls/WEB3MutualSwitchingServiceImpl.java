head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷�T�[�r�X�����N���X(WEB3MutualSwitchingServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
Revesion History : 2004/08/24 ��O�� (���u) ���r���[
Revesion History : 2004/12/10 ������ (���u) �c�Ή�
Revesion History : 2005/10/19 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/03/08 �ʉ� (SRA) �d�l�ύX(���f��)�F400
Revesion History : 2006/06/26 ���H�n (���u) �d�l�ύX(���f��)�F419
Revesion History : 2006/07/05 ��O�� (���u) �d�l�ύX(���f��)�F465
Revesion History : 2006/07/18 �R�� (SRA) �d�l�ύX(���f��)�F468
Revesion History : 2006/08/10 �R�� (SRA) �d�l�ύX(���f��)�F485
Revesion History : 2006/10/27 �����F (���u) ���f�� 489 497 �c�a�X�V�d�lNo.078,082
Revesion History : 2007/04/09 �����F (���u) ���f��557�@@����005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3CommissionDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundBizLogicProvider;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundNewOrderSwtConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmRequest;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ( ���M�抷�T�[�r�XImpl)<BR>
 * �����M���抷�T�[�r�X�����N���X
 *
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualSwitchingService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingServiceImpl.class);

    /**
     * �����M���抷�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate()�抷�܂��́A<BR>
     * submit�抷()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40557DB000CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3MutualSwitchingConfirmRequest)
        {
            //validate()�抷���\�b�h
            l_response =
                this.validateSwitching(
                    (WEB3MutualSwitchingConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MutualSwitchingCompleteRequest)
        {
            //submit�抷()���\�b�h
            l_response =
                this.submitSwitching(
                    (WEB3MutualSwitchingCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + " __Request "
                    + " WEB3MutualSwitchingCompleteRequest "
                    + " �� WEB3MutualSwitchingConfirmRequestt�ȊO�ł���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�抷)<BR>
     * �����M���抷�R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���M�j�抷�R���v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�抷�R���v: <BR>
     *        21((�ۗL�c���������߃`�F�b�N <BR>
     *        �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�� <BR>
     *         �擾�������\�c�������A�̏ꍇ�͗�O���X���[����B<BR>
     *        �i�ۗL�c���������߃G���[�j<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�抷�R���v: <BR>
     *        11) ���\�c���������擾����
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�抷�R���v: <BR>
     *       15.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����<BR>
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40557DF801D3
     */
    protected WEB3MutualSwitchingConfirmResponse validateSwitching(
         WEB3MutualSwitchingConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSwitching(WEB3MutualSwitchingConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1�j�@@���͓��e�`�F�b�N
        l_request.validate();

        // 1.2�j�@@�⏕�����擾
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //�g�����M�����擾
        // �@@�|�g�����M�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        // �|�g���A�J�E���g�}�l�[�W���̎擾
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_swtProduct = null;
        try
        {
            // 1.3) �抷�������I�u�W�F�N�g�̎擾
            //   �g�����M�����}�l�[�W��.get���M����()
            l_mfProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);

            // 1.4�j�@@�抷������I�u�W�F�N�g�̎擾
            // �@@�g�����M�����}�l�[�W��.get���M����()
            l_swtProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_mfProduct.getInstitution(),
                    l_request.switchingProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �j�@@�ڋq�ʎ����~�����`�F�b�N
        //  �g�����M�����}�l�[�W�����擾����
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //FinApp�N���X��getCommonOrderValidator()���R�[����
        //1.5) �����`�F�b�N�I�u�W�F�N�g���擾����
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        Timestamp l_datBizDate =
            new Timestamp(l_request.orderedDate.getTime());

        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        // 1.6)validate����\�ڋq
        OrderValidationResult l_validationResult =
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        // 1.7) ���M������ԊǗ�.validete������t�\()���R�[������
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // ������Ԃ̍Đݒ�
        // 1.8) ���M������ԊǗ�.reset�����R�[�h()���R�[��
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        // ��t�����A���t���[�����Z�b�g����
        // 1.9) ���M������ԊǗ�.setTimestamp()���R�[������
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //  �|�g�����M�|�W�V�����}�l�[�W���̎擾
        WEB3MutualFundPositionManager l_mutualFundPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                 ProductTypeEnum.MUTUAL_FUND).getPositionManager();

        // ���\�c���������擾����
        // 1.10) �@@�g�����M�|�W�V�����}�l�[�W��.calc���\�c������()
        double l_dblSellPossiblePositionQty =
            l_mutualFundPositionManager.calcSellPossiblePositionQty(
                l_subAccount,
                l_mfProduct,
                l_request.id);

        // 1.11) ���\�c������==0 �̏ꍇ�A��O���X���[����
        if(l_dblSellPossiblePositionQty == 0)
        {
            log.error("���\�c������������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }

        // 1.12)getAsset
        Asset l_asset = null;
        try
        {
            l_asset = l_mutualFundPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_asset == null)
        {
            log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ����G���[�B");
        }

        // �����敪
        String l_strProcessDiv = null;

        if (WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
        {
            l_strProcessDiv = WEB3ProcessDivDef.SELL;
        }
        else
        {
            if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }
            else
            {
                log.debug("���M�抷�m�F���N�G�X�g.�������@@�s��");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        // 1.13) validate�V�K����(�⏕����, �g�����M����, ��������, �����敪, ��n���@@, �w����@@, �抷�����)(

        //�mvalidate�V�K�����ɓn���p�����^�n
        //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
        //�g�����M�����F �擾�����抷���g�����M�����I�u�W�F�N�g
        //�������z���ʁF ���N�G�X�g�f�[�^.����
        //�����敪�F
        //(*) ���N�G�X�g�f�[�^.�������@@�̒l���h0�F��񐿋��h�̏ꍇ�� �h2�F���h���w��
        //(*) ���N�G�X�g�f�[�^.�������@@�̒l���h1�F���搿���h�̏ꍇ�� �h4�F����h���w��
        //��n���@@�F null
        //�w����@@�F ���N�G�X�g�f�[�^.�w����@@
        //�抷������F �擾�����抷������I�u�W�F�N�g
        //�ŋ敪�F �擾�����ۗL���Y.�ŋ敪
        //���ϕ��@@�F null 
        double l_dblOrderQuantity = 0.0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
        {
            l_dblOrderQuantity =
                Double.parseDouble(l_request.mutualOrderQuantity);
        }
        NewOrderValidationResult l_newOrderResult =
            l_orderManager.validateNewOrder(
                l_subAccount,
                l_mfProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                null,
                l_request.specifyDiv,
                l_swtProduct,
                l_asset.getTaxType(),
                null);
        ProcessingResult l_processingResult =
            l_newOrderResult.getProcessingResult();

        if (l_processingResult.isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }

        //1.14) is���z�w����
        //[����]
        // �⏕�����F �擾�����⏕�����I�u�W�F�N�g
        // �g�����M�����F �擾�����抷���̓��M�����I�u�W�F�N�g
        // �ŋ敪�F �擾�����ۗL���Y.�ŋ敪
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        boolean l_blnIsPriceDesignateCancelling =
            l_validationsCheck.isPriceDesignateCancelling(
                l_subAccount,
                l_mfProduct,
                l_asset.getTaxType());

        //1.15) ����F���N�G�X�g.�w����@@!=�h�S���h and �i ���N�G�X�g.�w����@@==�h���z�h
        //or is���z�w����()�̖߂�l==true �j �̏ꍇ�A
        //�������S�����𒴉߂��Ă��Ȃ����`�F�b�N����B

        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }

        boolean l_blnIsSellQtyLimitRateExcess = false;
        if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) &&
            (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) ||
              l_blnIsPriceDesignateCancelling))
        {
            //1.15.1)is�������S��������
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //�����F �擾�����抷���g�����M�����I�u�W�F�N�g
            //�����i�抷��j�F �擾�����抷��g�����M�����I�u�W�F�N�g
            //���YID�F���N�G�X�g.ID
            //���\�c�������Fcalc���\�c������()�̖߂�l
            //�����敪�F �h�抷�h
            //�������ʁF ���N�G�X�g.����
            //�w����@@�F ���N�G�X�g.�w����@@
            //���ϕ��@@�F �h�~�݁h
            //�������@@�F ���N�G�X�g.�������@@
            //�����敪�F �i�ȉ��̂Ƃ���j
            //�擾�����ۗL���Y.�ŋ敪 == �h��ʁh �̏ꍇ�A�h��ʁh
            //�擾�����ۗL���Y.�ŋ敪 == �h����h �̏ꍇ�A�h����h
            //�������F ���N�G�X�g.������
            l_blnIsSellQtyLimitRateExcess =
                l_validationsCheck.isSellQtyLimitRateExcess(
                    l_subAccount,
                    l_mfProduct,
                    l_swtProduct,
                    l_request.id,
                    l_dblSellPossiblePositionQty,
                    WEB3ProcessDivDef.SWITCHING,
                    l_dblOrderQuantity,
                    l_request.specifyDiv,
                    WEB3SettlementDivDef.JAPANESE_CURRENCY,
                    l_request.sellBuyDiv,
                    l_strTaxType,
                    l_request.orderedDate);

            //1.15.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����
            if (l_blnIsSellQtyLimitRateExcess)
            {
                log.debug("�������S�����𒴉߂��Ă���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������S�����𒴉߂��Ă���B");
            }
        }

        //1.16)���N�G�X�g�f�[�^.�w����@@�̒l���h2�F�S���h�̏ꍇ�A
        //���N�G�X�g�f�[�^.���ʂɎ擾�������\�c��������ݒ肷��B
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_dblOrderQuantity = l_dblSellPossiblePositionQty;
        }

        //1.17) get�抷������
        // �抷�����̔��������擾����
        // [����]
        // �抷�������R�[�h�F ���N�G�X�g.�����R�[�h
        // �抷������R�[�h�F ���N�G�X�g.�����R�[�h�i�抷��)
        // �m�F���������F���N�G�X�g.������
        WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
            l_request.mutualProductCode, l_request.switchingProductCode, l_request.orderedDate);

        //1.18�jget�抷����
        //�m�����n
        //�،���ЁF �⏕����.getInstitution()�̖߂�l
        //�抷�������R�[�h�F ���N�G�X�g.�����R�[�h
        //�抷������R�[�h�F ���N�G�X�g.�����R�[�h�i�抷��j
        Timestamp l_tsSwtExecutedDate =
            l_productManager.getSwtExecutedDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 1.19�jget�抷��n��
        //�m�����n
        //�،���ЁF �⏕����.getInstitution()�̖߂�l
        //�抷�������R�[�h�F ���N�G�X�g.�����R�[�h
        //�抷������R�[�h�F ���N�G�X�g.�����R�[�h�i�抷��j
        Timestamp l_tsDeliveryDate =
            l_productManager.getSwtDeliveryDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 1.20�j�@@�T�Z��n����擾
        //�mcalc�T�Z��n����ɓn���p�����^�n
        //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
        //�����F �擾�����抷���g�����M�����I�u�W�F�N�g
        //�����i�抷��j�F �擾�����抷��g�����M�����I�u�W�F�N�g
        //�����敪�F �h�抷�h
        //�������ʁF ���N�G�X�g�f�[�^.����
        //�w����@@�F
        //(*) ���N�G�X�g�f�[�^.�w����@@���h�S���h�̏ꍇ�́h�����h��ݒ肷��B
        //(*) �����łȂ��ꍇ�̓��N�G�X�g�f�[�^.�w����@@��ݒ肷��B
        //���ϕ��@@�F �h�~�݁h
        //�������@@�F ���N�G�X�g�f�[�^.�������@@
        //�����敪�F �i�ȉ��̂Ƃ���j
        //�擾�����ۗL���Y.�ŋ敪 == �h��ʁh �̏ꍇ�A�h��ʁh
        //�擾�����ۗL���Y.�ŋ敪 == �h����h �̏ꍇ�A�h����h
        //�����`���l���F �Z�b�V��������擾���������`���l��
        //�������F ���N�G�X�g�f�[�^.������
        String l_strSpecifyDiv = null;
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        }
        else
        {
            l_strSpecifyDiv = l_request.specifyDiv;
        }

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        // �@@�|�g�����M�����}�l�[�W��.calc�T�Z��n���()���R�[�����āA
        //   �T�Z��n����I�u�W�F�N�g���擾����

        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mfProduct,
                l_swtProduct,
                WEB3ProcessDivDef.SWITCHING,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                WEB3SettlementDivDef.JAPANESE_CURRENCY,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate
            );

        // �ۗL�c���������߃`�F�b�N
        // 1.21) �i�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������( ) �� �擾�������\�c�������j�@@�̏ꍇ
        if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
        {
            log.debug("�ۗL�c���������߃G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL�c���������߃G���[");
        }

        // ��������`�F�b�N
        // 1.22) ���M�抷�m�F���N�G�X�g.���t�����敪�̒l���h1�F����h�̏ꍇ
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.switchingTaxType))
        {
            try
            {
                //1.22.1) �|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����
                WEB3GentradeMainAccount l_account =
                    (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                        l_subAccount.getAccountId());

                // 1.22.2) �擾�����ڋq�I�u�W�F�N�g.is��������J��()���R�[������
                boolean l_lbnIsSpecialAccountEstablished =
                    l_account.isSpecialAccountEstablished(
                        l_tsDeliveryDate,
                        l_subAccount);

                if (!l_lbnIsSpecialAccountEstablished)
                {
                    log.debug("��������`�F�b�N�G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������`�F�b�N�G���[");
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.23) validate�抷�攃�t�Œ���z
        //[����]
        // �⏕�����F �⏕�����I�u�W�F�N�g
        // �w��敪�F ���N�G�X�g.�w����@@
        // �T�Z��������F �抷�������̊T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l
        // �抷������F �抷��̖����I�u�W�F�N�g
        l_orderManager.validateSwtBuyMinAmt(
            l_subAccount,
            l_request.specifyDiv,
            l_mfEstimatedPrice.getEstimatedPrice(),
            l_swtProduct);

        // 1.24) calc�T�Z���t����()
        // �抷������̊T�Z���t�������Z�o����B

        //�mcalc�T�Z���t�����ɓn���p�����^�n
        //�����F �擾�����抷��g�����M�����I�u�W�F�N�g
        //�������ʁF �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l

        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();

        double l_swtEstimatedQty =
            l_mfBizLogicProvider.calcEstimatedBuyQty(
                l_swtProduct,
                l_mfEstimatedPrice.getEstimatedPrice());

        //1.25 �抷��̊T�Z���t������0�̏ꍇ�͗�O���X���[����B
        if(l_swtEstimatedQty == 0)
        {
            log.error("�抷��̔��t�\���ʂ�0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02000,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷��̔��t�\���ʂ�0�ȉ��ł��B");
        }

        //1.26) get���򒥎��S����
        //[����]
        // �⏕�����F �⏕�����I�u�W�F�N�g
        // �����敪�F ���N�G�X�g.�������@@
        // �ۗL���YID�F ���N�G�X�g.ID
        // �T�Z��n����F �抷�������̊T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l
        Double l_dblSourceRestraintPrice =
            l_orderManager.getWithholdingTaxRestriction(
                l_subAccount,
                l_request.sellBuyDiv,
                l_request.id,
                l_mfEstimatedPrice.getEstimatedPrice());

        //1.27) �g�����M�V�K�������e
        //[����]
        // �㗝���͎ҁF this.get�㗝���͎�()�̖߂�l
        // is���t�F false
        // �����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
        // �������ʁF
        // (*) ���N�G�X�g�f�[�^.�w����@@==�h�S���h and
        // is���z�w����()�̖߂�l==true �̏ꍇ��
        // 0���w��B
        // (*) ����ȊO�̏ꍇ��
        // ���N�G�X�g�f�[�^.���ʂ��w��B
        // �������ʃ^�C�v�F
        //(*) ���N�G�X�g�f�[�^.�w����@@==�h�S���h or �h�����h�̏ꍇ��
        //QuantityTypeEnum.���ʂ��w��B
        //(*) ���N�G�X�g�f�[�^.�w����@@���h���z�h�̏ꍇ��
        //QuantityTypeEnum.���z���w��B
        // �ŋ敪�F �ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l

        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) &&
            l_blnIsPriceDesignateCancelling)
        {
            l_dblOrderQuantity = 0;
        }
        QuantityTypeEnum l_quantityType = null;
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) ||
            WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.QUANTITY;
        }
        else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_quantityType = QuantityTypeEnum.AMOUNT;
        }

        WEB3MutualFundNewOrderSpec l_mfNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                this.getTrader(),
                false,
                l_request.mutualProductCode,
                l_dblOrderQuantity,
                l_quantityType,
                l_asset.getTaxType());

        //1.28) ���M�V�K�����m��C���^�Z�v�^�i�抷�j
        WEB3MutualFundNewOrderSwtConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderSwtConfirmInterceptor();

        //1.29) (*)�v���p�e�B�Z�b�g
        //�@@��n����ݒ肷��B
        //�@@�@@[set��n���ɓn���p�����^]
        //�@@�@@��n���F �擾������n��
        l_interceptor.setDeliveryDate(l_tsDeliveryDate);

        //�A�����`���l����ݒ肷��B
        //�@@�@@[set�����`���l���ɓn���p�����^]
        //�@@�@@�����`���l���F this.get���O�C���`���l��()�̖߂�l
        l_interceptor.setOrderChannel(this.getLoginChannel());

        //�B�v�Z����z��ݒ肷��B
        //�@@�@@[set�v�Z����z�ɓn���p�����^]
        //�@@�@@�v�Z����z�F �擾�����g�����M�����I�u�W�F�N�g.get��񉿊z()�̖߂�l
        l_interceptor.setConstantValue(l_mfProduct.getSellValue());

        //�C�v�Z����z�i�抷��j��ݒ肷��B
        //�@@�@@[set�v�Z����z�i�抷��j�ɓn���p�����^]
        //�@@�@@�v�Z����z�i�抷��j�F �擾�����抷������I�u�W�F�N�g.get���t����z()�̖߂�l
        l_interceptor.setSwitchingConstantValue(l_swtProduct.getConstantValue());

        //�D����z�K�p����ݒ肷��B
        //�@@�@@[set����z�K�p���ɓn���p�����^]
        //�@@�@@����z�K�p���F �擾�����g�����M�����I�u�W�F�N�g.get����z�K�p��()�̖߂�l
        l_interceptor.setConstantValueAppDate(l_mfProduct.getConstantValueAppDate());

        //�E�T�Z��n�����ݒ肷��B
        //[set�T�Z��n����ɓn���p�����^]
        //�T�Z��n����F  �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l���w��B
        l_interceptor.setEstimatedPrice(l_mfEstimatedPrice.getEstimatedPrice());

        //�F�T�Z����������ݒ肷��B
        //[set�T�Z���������ɓn���p�����^]
        //�T�Z���������F�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l���w��B
        l_interceptor.setEstimatedQty(l_mfEstimatedPrice.getEstimatedQty());

        //�G�T�Z���t�����i�抷��j��ݒ肷��B
        //[set�T�Z���t�����i�抷��j�ɓn���p�����^]�@@
        //�T�Z���t�����i�抷��j�F�擾�����抷��̊T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l���w��B
        l_interceptor.setSwitchingEstimatedQty(l_swtEstimatedQty);

        //�H�ŋ敪�i�抷��j��ݒ肷��B
        //[set�ŋ敪�i�抷��j�ɓn���p�����^]
        //�ŋ敪�i�抷��j�F
        //(*) ���N�G�X�g�f�[�^.���t�����敪���h0�F��ʁh�̏ꍇ�A
        //TaxTypeEnum.NORMAL��ݒ肷��B
        //(*) ���N�G�X�g�f�[�^.���t�����敪���h1�F����h�̏ꍇ�A
        //TaxTypeEnum.SPECIAL��ݒ肷��B
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.switchingTaxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.switchingTaxType))
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        l_interceptor.setSwitchingSubjectTaxDivision(l_taxType);

        //�I��n���@@��ݒ肷��B
        //[set��n���@@�ɓn���p�����^]
        //��n���@@�F null
        l_interceptor.setDeliveryDiv(null);

        //�J���M�^�C�v��ݒ肷��B
        //[set���M�^�C�v�ɓn���p�����^]
        //���M�^�C�v�F �擾�����g�����M����.getMutualFundType()�̖߂�l
        l_interceptor.setMutualFundType(l_mfProduct.getMutualFundType().intValue() + "");

        //�K���M���敪��ݒ肷��B
        //[set���M���敪�ɓn���p�����^]
        //���M���敪�F ���N�G�X�g�f�[�^.�w����@@
        l_interceptor.setMutualFundSellDiv(l_request.specifyDiv);

        //�L������ݒ肷��B
        //[set�����ɓn���p�����^]
        //�����F �擾��������
        l_interceptor.setExecutionTimestamp(l_tsSwtExecutedDate);

        //�M���ϋ敪��ݒ肷��B
        //[set���ϋ敪�ɓn���p�����^]
        //���ϋ敪�F �h1�F�~�݁h
        l_interceptor.setSettlementType(WEB3SettlementDivDef.JAPANESE_CURRENCY);

        //�N���萔���敪��ݒ肷��B
        //[set���萔���敪�ɓn���p�����^]
        //���萔���敪�F
        //�g�����M�����}�l�[�W��.is�萔�������ڋq() == true �̏ꍇ�A�h���萔���h
        //����ȊO�̏ꍇ�A�u�����N

        //�@@�@@[is�萔�������ڋq�ɓn���p�����[�^]
        //�@@�@@�ڋq�F �⏕����.getMainAccount()
        //�@@�@@�����F �擾�����抷������I�u�W�F�N�g
        if (l_orderManager.isCommissionFreeAccount(l_genMainAccount, l_swtProduct))
        {
            l_interceptor.setNoCommissionDivision(WEB3CommissionDivDef.NO_COMMISSION);
        }
        else
        {
            l_interceptor.setNoCommissionDivision(" ");
        }

        //�O�����R�[�h�i�抷��j��ݒ肷��B
        //[set�����R�[�h�i�抷��j�ɓn���p�����^]
        //�����R�[�h�i�抷��j�F �擾�����抷������I�u�W�F�N�g.getProductCode()�̖߂�l
        l_interceptor.setSwitchingSubjectMutualProductCode(l_swtProduct.getProductCode());

        //�P�����敪��ݒ肷��B
        //[set�����敪�ɓn���p�����^]
        //�����敪�F ���N�G�X�g�f�[�^.�������@@
        l_interceptor.setRequestDivision(l_request.sellBuyDiv);

        //�Q�����o�H�敪��ݒ肷��B
        //[set�����o�H�敪�ɓn���p�����^]
        //�����o�H�敪�F �Z�b�V��������擾���������ڂ̒l
        l_interceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //�R���򒥎��S������ݒ肷��B
        //[set���򒥎��S�����ɓn���p�����^]
        //���򒥎��S�����F get���򒥎��S����()�̖߂�l
        l_interceptor.setWithholdingTaxRestriction(l_dblSourceRestraintPrice);

        //�S�o���������ʃR�[�h��ݒ肷��B
        //[set�o���������ʃR�[�h�ɓn���p�����^]
        //�o���������ʃR�[�h�F null
        l_interceptor.setPaymentOrderReqNumber(null);

        //��������ݒ肷��B
        //   [set�������ɓn���p�����^]
        //   �������F���N�G�X�g�f�[�^.������
        String l_strOrderedDate = WEB3DateUtility.formatDate(l_request.orderedDate, "yyyyMMdd");
        l_interceptor.setBizDate(l_strOrderedDate);

        //1.30) validate����]��
        //[����]
        // �⏕�����F �⏕�����I�u�W�F�N�g
        // �������e�C���^�Z�v�^�F ���M�V�K�����m��C���^�Z�v�^�i�抷�j��v�f�Ƃ����z��
        // �������e�F �g�����M�V�K�������e��v�f�Ƃ����z��
        // ������ʁF OrderTypeEnum.�����M���抷����
        // �]�͍X�V�t���O�F false
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_interceptor};

        Object[] l_arrNewOrderSpecs = {l_mfNewOrderSpec};
        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_SWITCHING,
                false);

        //is����t���O()�̖߂�l��false�̏ꍇ�A[���򒥎��S�����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����
        if(!l_result.isResultFlg())
        {
            log.debug("���򒥎��S�����]�̓`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02324,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���򒥎��S�����]�̓`�F�b�N�G���[");
        }

        //1.31) �g�����M�����}�l�[�W��.createNewOrderId()���R�[�����Ē���ID���擾����B
        long l_lngOrderId =
            l_orderManager.createNewOrderId();

        // 1.32) ���M�抷�m�F���X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
        WEB3MutualSwitchingConfirmResponse l_response =
            (WEB3MutualSwitchingConfirmResponse) l_request.createResponse();

        // 1.33) ���M�抷�m�F���X�|���X�I�u�W�F�N�g�ɂ́A�ȉ��̒l��ݒ肷��B

        // �@@�@@��񒍕����e�x���敪�F
        if (l_blnIsSellQtyLimitRateExcess)
        {
            l_response.sellWarningType = "1";
        }
        else
        {
            l_response.sellWarningType = null;
        }

        // �@@�@@�T�Z���t�����i�抷��j�F
        //�擾�����抷��̊T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l��ݒ肷��
        l_response.switchingEstimatedQty =
            WEB3StringTypeUtility.formatNumber(l_swtEstimatedQty);

        //�T�Z��n����F
        //���N�G�X�g.�w����@@ == �h�����w��h �̏ꍇ�A
        //�擾�����抷���̊T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l��ݒ肷��B
        if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_response.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_mfEstimatedPrice.getEstimatedPrice());
        }

        //���ʁF ���M�抷�m�F���N�G�X�g.����
        l_response.mutualOrderQuantity =
            WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
        l_response.orderId = l_lngOrderId + "";

        return l_response;
    }

    /**
     * (submit�抷)<BR>
     * �����M���抷�o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���M�j�抷�o�^�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�抷�o�^�v: <BR>
     *        23((�ۗL�c���������߃`�F�b�N <BR>
     *        �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�� <BR>
     *         �擾�������\�c�������A�̏ꍇ�͗�O���X���[����B<BR>
     *        �i�ۗL�c���������߃G���[�j<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00387<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�抷�o�^�v: <BR>
     *        14((�ۗL���Y�̎擾 <BR>
     *        ���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������ <BR>
     *         �������ʂ�0���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00204<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���R���v: <BR>
     *        13) ���\�c���������擾����
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00390<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�抷�R���v: <BR>
     *       17.1) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����<BR>
     *         ���\�c������==0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02269<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40557E2A002D
     */
    protected WEB3MutualSwitchingCompleteResponse submitSwitching(
         WEB3MutualSwitchingCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSwitching(WEB3MutualSwitchingCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �P�j�@@���͓��e�`�F�b�N
        l_request.validate();

        // �Q�j�@@�⏕�����擾
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        // �R�j�@@�g�����M�����擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //  �|�g�����M�|�W�V�����}�l�[�W���̎擾
        WEB3MutualFundPositionManager l_mutualFundPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();

        // �|�g���A�J�E���g�}�l�[�W���̎擾
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3MutualFundProduct l_mfProduct = null;
        WEB3MutualFundProduct l_swtProduct = null;

        try
        {
            // �|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�g�����M�������擾����
            l_mfProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode);

            // �S�j�@@�抷������I�u�W�F�N�g�̎擾
            // �@@�g�����M�����}�l�[�W��.get���M����()���R�[�����āA�抷������̊g�����M����
            l_swtProduct =
                (WEB3MutualFundProduct) l_productManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_request.switchingProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�g�����M�����擾�ł��Ȃ��ꍇ�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �T�j�@@�ڋq�ʎ����~�����`�F�b�N
        //FinApp�N���X��getCommonOrderValidator()���R�[����
        //�����`�F�b�N�I�u�W�F�N�g���擾����
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        Timestamp l_datBizDate =
            new Timestamp(l_request.orderedDate.getTime());
        // 6�jvalidate����\�ڋq(�ڋq, ������)
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.8)�|validate����p�X���[�h( )���R�[������B
        OrderValidationResult l_validationResultPassword =
            l_orderValidator.validateTradingPassword(
                this.getTrader(),
                l_subAccount,
                l_request.password);

        if (l_validationResultPassword.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���s���ł�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���s���ł��B");
        }

        // 9�j�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
        // �@@�|���M������ԊǗ�.validete������t�\()���R�[������
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // 10�j������Ԃ̍Đݒ�
        // �@@�|���M������ԊǗ�.reset�����R�[�h()���R�[��
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        //11�j �@@�|��t�����A���t���[�����Z�b�g����
        // �@@�@@�@@���M������ԊǗ�.setTimestamp()���R�[������
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        // 1.12�j�@@���\�c���������擾����
        // �@@�g�����M�|�W�V�����}�l�[�W��.calc���\�c������()
        double l_dblSellPossiblePositionQty =
            l_mutualFundPositionManager.calcSellPossiblePositionQty(
                l_subAccount,
                l_mfProduct,
                l_request.id);

        //1.13 ���\�c������==0 �̏ꍇ�A��O���X���[����B
        if(l_dblSellPossiblePositionQty == 0)
        {
            log.error("���\�c������������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00390,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }

        //1.14�j�@@�ۗL���Y�̎擾
        //�|���M�g���|�W�V�����}�l�[�W��.getAsset( )���R�[������B
        //�@@[����]
        //�@@arg0�F ���N�G�X�g.ID
        //�������ʂ�0���̏ꍇ�A��O���X���[����B
        Asset l_asset = null;
        try
        {
            l_asset = l_mutualFundPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_asset == null)
        {
            log.error("�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���Y�I�u�W�F�N�g�f�[�^�Ȃ����G���[�B");
        }

        // �����敪
        String l_strProcessDiv = "";

        if (WEB3ClaimDivDef.SELL.equals(l_request.sellBuyDiv))
        {
            l_strProcessDiv = WEB3ProcessDivDef.SELL;
        }
        else
        {
            if (WEB3ClaimDivDef.BUY.equals(l_request.sellBuyDiv))
            {
                l_strProcessDiv = WEB3ProcessDivDef.PURCHASE;
            }
            else
            {
                log.debug("���M�抷�������N�G�X�g.�������@@�s��");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        // 1.15)�@@�|�g�����M�����}�l�[�W��.validate�V�K����()
        //�mvalidate�V�K�����ɓn���p�����^�n
        // �⏕�����F �擾�����⏕�����I�u�W�F�N�g
        // �g�����M�����F �擾�����抷���g�����M�����I�u�W�F�N�g
        // �������z���ʁF ���N�G�X�g�f�[�^.����
        // �����敪�F
        // (*) ���N�G�X�g�f�[�^.�������@@�̒l���h0�F��񐿋��h�̏ꍇ��
        // �h2�F���h���w��
        // (*) ���N�G�X�g�f�[�^.�������@@�̒l���h1�F���搿���h�̏ꍇ��
        // �h4�F����h���w��
        // ��n���@@�F null
        // �w����@@�F ���N�G�X�g�f�[�^.�w����@@
        // �抷������F �擾�����抷������I�u�W�F�N�g
        // �ŋ敪�F �擾�����ۗL���Y.�ŋ敪
        // ���ϕ��@@�F null 
        double l_dblOrderQuantity = 0.0;
        if (!WEB3StringTypeUtility.isEmpty(l_request.mutualOrderQuantity))
        {
            l_dblOrderQuantity =
                Double.parseDouble(l_request.mutualOrderQuantity);
        }

        NewOrderValidationResult l_newOrderResult =
            l_orderManager.validateNewOrder(
                l_subAccount,
                l_mfProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                null,
                l_request.specifyDiv,
                l_swtProduct,
                l_asset.getTaxType(),
                null);
        ProcessingResult l_orderProcessResult =
            l_newOrderResult.getProcessingResult();
        if (l_orderProcessResult.isFailedResult())
        {
            log.debug("�����R���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderProcessResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R���`�F�b�N�G���[");
        }

        //1.16) is���z�w����
        //[����]
        // �⏕�����F �擾�����⏕�����I�u�W�F�N�g
        // �g�����M�����F �擾�����抷���̓��M�����I�u�W�F�N�g
        // �ŋ敪�F �擾�����ۗL���Y.�ŋ敪
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        boolean l_blnIsPriceDesignateCancelling =
            l_validationsCheck.isPriceDesignateCancelling(
                l_subAccount,
                l_mfProduct,
                l_asset.getTaxType());

        //1.17) ����F���N�G�X�g.�w����@@!=�h�S���h and �i ���N�G�X�g.�w����@@==�h���z�h
        //or is���z�w����()�̖߂�l==true �j �̏ꍇ�A
        //�������S�����𒴉߂��Ă��Ȃ����`�F�b�N����B

        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if(TaxTypeEnum.SPECIAL.equals(l_asset.getTaxType()))
        {
            l_strTaxType = WEB3TaxTypeSpecialDef.SPECIAL;
        }

        if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) &&
          (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv) || l_blnIsPriceDesignateCancelling))
        {
            //1.17.1)is�������S��������
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //�����F �擾�����g�����M�����I�u�W�F�N�g
            //�����i�抷��j�F�擾�����抷��g�����M�����I�u�W�F�N�g
            //���YID�F���N�G�X�g.ID
            //���\�c�������Fcalc���\�c������()�̖߂�l
            //�����敪�F �h�抷�h
            //�������ʁF ���N�G�X�g.����
            //�w����@@�F ���N�G�X�g.�w����@@
            //���ϕ��@@�F �h�~�݁h
            //�������@@�F ���N�G�X�g.�������@@
            //�����敪�F ���N�G�X�g.�����敪
            //�擾�����ۗL���Y.�ŋ敪 == �h��ʁh �̏ꍇ�A�h��ʁh
            //�擾�����ۗL���Y.�ŋ敪 == �h����h �̏ꍇ�A�h����h
            //�������F ���N�G�X�g.������
            boolean l_blnIsSellQtyLimitRateExcess =
                l_validationsCheck.isSellQtyLimitRateExcess(
                     l_subAccount,
                     l_mfProduct,
                     l_swtProduct,
                     l_request.id,
                     l_dblSellPossiblePositionQty,
                     WEB3ProcessDivDef.SWITCHING,
                     l_dblOrderQuantity,
                     l_request.specifyDiv,
                     WEB3SettlementDivDef.JAPANESE_CURRENCY,
                     l_request.sellBuyDiv,
                     l_strTaxType,
                     l_request.orderedDate);

            //1.17.2) is�������S��������()�̖߂�l��true�̏ꍇ�A��O���X���[����
            if (l_blnIsSellQtyLimitRateExcess)
            {
                log.error("�������S�����𒴉߂��Ă���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02269,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������S�����𒴉߂��Ă���B");
            }
        }

        //1.18)���N�G�X�g�f�[�^.�w����@@�̒l���h2�F�S���h�̏ꍇ�A
        //���N�G�X�g�f�[�^.���ʂɎ擾�������\�c��������ݒ肷��B
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_dblOrderQuantity= l_dblSellPossiblePositionQty;
        }

        // 19) get�抷������
        // �抷�������������擾����B
        // [����]
        //�抷�������R�[�h�F ���N�G�X�g.�����R�[�h
        //�抷������R�[�h�F ���N�G�X�g.�����R�[�h�i�抷��j
        //�m�F���������F���N�G�X�g.������
        WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
            l_request.mutualProductCode, l_request.switchingProductCode, l_request.orderedDate);

        // 20�jget�抷����
        //�m�����n
        //�،���ЁF �⏕����.getInstitution()�̖߂�l
        //�抷�������R�[�h�F ���N�G�X�g.�����R�[�h
        //�抷������R�[�h�F ���N�G�X�g.�����R�[�h�i�抷��j
        Timestamp l_datExecutedDate =
            l_productManager.getSwtExecutedDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 21�jget�抷��n��
        //�m�����n
        //�،���ЁF �⏕����.getInstitution()�̖߂�l
        //�抷�������R�[�h�F ���N�G�X�g.�����R�[�h
        //�抷������R�[�h�F ���N�G�X�g.�����R�[�h�i�抷��j
        Timestamp l_datDeliveryDate =
            l_productManager.getSwtDeliveryDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_request.switchingProductCode);

        // 22�j�@@�T�Z��n����擾
        //�mcalc�T�Z��n����ɓn���p�����^�n
        // �⏕�����F �擾�����⏕�����I�u�W�F�N�g
        // �����F �擾�����抷���g�����M�����I�u�W�F�N�g
        // �����i�抷��j�F �擾�����抷��g�����M�����I�u�W�F�N�g
        // �����敪�F �h�抷�h
        // �������ʁF ���N�G�X�g�f�[�^.����
        // �w����@@�F
        // (*) ���N�G�X�g�f�[�^.�w����@@���h�S���h�̏ꍇ�́h�����h��ݒ肷��B
        // (*) �����łȂ��ꍇ�̓��N�G�X�g�f�[�^.�w����@@��ݒ肷��B
        // ���ϕ��@@�F �h�~�݁h
        // �������@@�F ���N�G�X�g�f�[�^.�������@@
        // ���敪�F �i�ȉ��̂Ƃ���j
        //�擾�����ۗL���Y.�ŋ敪 == �h��ʁh �̏ꍇ�A�h��ʁh
        //�擾�����ۗL���Y.�ŋ敪 == �h����h �̏ꍇ�A�h����h
        // �����`���l���F �Z�b�V��������擾���������`���l��
        // �������F ���N�G�X�g�f�[�^.������

        String l_strSpecifyDiv = "";
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_strSpecifyDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        }
        else
        {
            l_strSpecifyDiv = l_request.specifyDiv;
        }
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice =
            l_orderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mfProduct,
                l_swtProduct,
                WEB3ProcessDivDef.SWITCHING,
                l_dblOrderQuantity,
                l_strSpecifyDiv,
                WEB3SettlementDivDef.JAPANESE_CURRENCY,
                l_request.sellBuyDiv,
                l_strTaxType,
                l_strOrderChanel,
                l_request.orderedDate);

        // 23�j�@@�ۗL�c���������߃`�F�b�N
        if (l_mfEstimatedPrice.getEstimatedQty() > l_dblSellPossiblePositionQty)
        {
            log.debug("�ۗL�c���������߃G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00387,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL�c���������߃G���[");
        }

        // 24�j�@@��������`�F�b�N
        // �@@���M�抷�������N�G�X�g.���t�����敪�̒l���h1�F����h�̏ꍇ
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.switchingTaxType))
        {
            try
            {
                //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����
                WEB3GentradeMainAccount l_account =
                    (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                        l_subAccount.getAccountId());

                // �@@�|�擾�����ڋq�I�u�W�F�N�g.is��������J��()���R�[������
                boolean l_blnIsSpecialAccountEstablished =
                    l_account.isSpecialAccountEstablished(
                        l_datDeliveryDate,
                        l_subAccount);
                if (!l_blnIsSpecialAccountEstablished)
                {
                    log.debug("��������`�F�b�N�G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������`�F�b�N�G���[");
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.25) validate�抷�攃�t�Œ���z
        //[����]
        // �⏕�����F �⏕�����I�u�W�F�N�g
        // �w��敪�F ���N�G�X�g.�w����@@
        // �T�Z��������F �抷�������̊T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l
        // �抷������F �抷��̖����I�u�W�F�N�g
        l_orderManager.validateSwtBuyMinAmt(
            l_subAccount,
            l_request.specifyDiv,
            l_mfEstimatedPrice.getEstimatedPrice(),
            l_swtProduct);

        // 1.26) calc�T�Z���t����()
        // �抷������̊T�Z���t�������Z�o����B

        //�mcalc�T�Z���t�����ɓn���p�����^�n
        //�����F �擾�����抷��g�����M�����I�u�W�F�N�g
        //�������ʁF �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l

        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();

        double l_swtEstimatedQty =
            l_mfBizLogicProvider.calcEstimatedBuyQty(
                l_swtProduct,
                l_mfEstimatedPrice.getEstimatedPrice());


        //1.27�抷��̊T�Z���t������0�̏ꍇ�͗�O���X���[����B
        if(l_swtEstimatedQty == 0)
        {
            log.error("�抷��̔��t�\���ʂ�0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02000,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷��̔��t�\���ʂ�0�ȉ��ł��B"
                );
        }

        //1.28) get���򒥎��S����
        //[����]
        // �⏕�����F �⏕�����I�u�W�F�N�g
        // �����敪�F ���N�G�X�g.�������@@
        // �ۗL���YID�F ���N�G�X�g.ID
        // �T�Z��n����F �抷�������̊T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l
        Double l_dblSourceRestraintPrice =
            l_orderManager.getWithholdingTaxRestriction(
                l_subAccount,
                l_request.sellBuyDiv,
                l_request.id,
                l_mfEstimatedPrice.getEstimatedPrice());

        //1.29) get����
        //�抷�������̖������擾����B
        //[����]
        // �،���ЁF �⏕����.getInstitution()�̖߂�l
        // �����R�[�h�F ���N�G�X�g.�����R�[�h
        // �������F���N�G�X�g.������
        Date l_datSwtExecutedDate = l_productManager.getExecutedDate(
            l_subAccount.getInstitution(),
            l_request.mutualProductCode,
            l_request.orderedDate);

        //1.30)  ���M�V�K�����m��C���^�Z�v�^�i�抷�j
        WEB3MutualFundNewOrderSwtConfirmInterceptor l_interceptor =
            new WEB3MutualFundNewOrderSwtConfirmInterceptor();

        //1.31)setThreadLocalPersistenceEventInterceptor(arg0 : MutualFundOrderManagerPersistenceEventInterceptor)
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.32�j�e�p�����[�^�̃Z�b�g
        //���M�V�K�����m��C���^�Z�v�^�Ɏ�n����ݒ肷��
        //���M�V�K�����m��C���^�Z�v�^.set��n��()���R�[������
        Timestamp l_tsDeliveryDate = new Timestamp(l_datDeliveryDate.getTime());
        l_interceptor.setDeliveryDate(l_tsDeliveryDate);

        //���M�V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��
        //���M�V�K�����m��C���^�Z�v�^.set�����`���l��()
        l_interceptor.setOrderChannel(this.getLoginChannel());

        //���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z��ݒ肷��
        //���M�V�K�����m��C���^�Z�v�^.set�v�Z����z()���R�[������
        l_interceptor.setConstantValue(
            l_mfProduct.getSellValue());

        //���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z�i�抷��j��ݒ肷��
        //���M�V�K�����m��C���^�Z�v�^.set�v�Z����z�i�抷��j()���R�[������
        l_interceptor.setSwitchingConstantValue(
            l_swtProduct.getConstantValue());

        //���M�V�K�����m��C���^�Z�v�^�Ɋ���z�K�p����ݒ肷��
        //���M�V�K�����m��C���^�Z�v�^.set����z�K�p��()���R�[������
        l_interceptor.setConstantValueAppDate(
            l_mfProduct.getConstantValueAppDate());

        //���M�V�K�����m��C���^�Z�v�^�ɊT�Z��n�����ݒ肷��
        //���M�V�K�����m��C���^�Z�v�^.set�T�Z��n���()���R�[������
        l_interceptor.setEstimatedPrice(l_mfEstimatedPrice.getEstimatedPrice());

        //���M�V�K�����m��C���^�Z�v�^�ɊT�Z����������ݒ肷��
        // ���M�V�K�����m��C���^�Z�v�^.set�T�Z��������()���R�[������
        l_interceptor.setEstimatedQty(l_mfEstimatedPrice.getEstimatedQty());

        //���M�V�K�����m��C���^�Z�v�^�ɊT�Z���t�����i�抷��j��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set�T�Z���t�����i�抷��j()���R�[������B
        l_interceptor.setSwitchingEstimatedQty(l_swtEstimatedQty);

        //���M�V�K�����m��C���^�Z�v�^�ɐŋ敪�i�抷��j��ݒ肷��B
        TaxTypeEnum l_taxTypeEnum = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.switchingTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        //���M�V�K�����m��C���^�Z�v�^.set�ŋ敪�i�抷��j()���R�[������B
        l_interceptor.setSwitchingSubjectTaxDivision(
            l_taxTypeEnum);

        //���M�V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set��n���@@()���R�[������B
        l_interceptor.setDeliveryDiv(null);

        //���M�V�K�����m��C���^�Z�v�^�ɓ��M�^�C�v��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set���M�^�C�v()���R�[������B
        l_interceptor.setMutualFundType(
            l_mfProduct.getMutualFundType().intValue() + "");

        //���M�V�K�����m��C���^�Z�v�^�ɓ��M���敪��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set���M���敪()���R�[������B
        l_interceptor.setMutualFundSellDiv(
            l_request.specifyDiv);

        //���M�V�K�����m��C���^�Z�v�^�ɖ�����ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set����()���R�[������B
        Timestamp l_tsExecutedDate = new Timestamp(l_datExecutedDate.getTime());
        l_interceptor.setExecutionTimestamp(l_tsExecutedDate);

        //���M�V�K�����m��C���^�Z�v�^�Ɍ��ϋ敪��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set���ϋ敪()���R�[������B
        l_interceptor.setSettlementType(
            WEB3SettlementDivDef.JAPANESE_CURRENCY);

        //���M�V�K�����m��C���^�Z�v�^�ɖ��萔���敪��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set���萔���敪()���R�[������B
        //[set���萔���敪�ɓn���p�����^]
        //�@@���萔���敪�F
        //    �g�����M�����}�l�[�W��.is�萔�������ڋq() == true �̏ꍇ�A�h���萔���h
        //    ����ȊO�̏ꍇ�A�u�����N

        //    [is�萔�������ڋq�ɓn���p�����[�^]
        //    �ڋq�F �⏕����.getMainAccount()
        //    �����F  �擾�����抷������I�u�W�F�N�g
        if (l_orderManager.isCommissionFreeAccount(l_genMainAccount, l_swtProduct))
        {
            l_interceptor.setNoCommissionDivision(WEB3CommissionDivDef.NO_COMMISSION);
        }
        else
        {
            l_interceptor.setNoCommissionDivision(" ");
        }

        //���M�V�K�����m��C���^�Z�v�^�ɖ����R�[�h�i�抷��j��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set�����R�[�h�i�抷��j()���R�[������B
        l_interceptor.setSwitchingSubjectMutualProductCode(
            l_swtProduct.getProductCode());

        //���M�V�K�����m��C���^�Z�v�^�ɐ����敪��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set�����敪()���R�[������B
        l_interceptor.setRequestDivision(l_request.sellBuyDiv);

        //���M�V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��B
        //���M�V�K�����m��C���^�Z�v�^.set�����o�H�敪()���R�[������B
        l_interceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //�R���򒥎��S������ݒ肷��B
        // �@@[set���򒥎��S�����ɓn���p�����^]
        //�@@�@@���򒥎��S�����F get���򒥎��S����()�̖߂�l
        l_interceptor.setWithholdingTaxRestriction(l_dblSourceRestraintPrice);

        //�S�o���������ʃR�[�h��ݒ肷��B
        // �@@[set�o���������ʃR�[�h�ɓn���p�����^]
        //�@@�@@�o���������ʃR�[�h�F null
        l_interceptor.setPaymentOrderReqNumber(null);

        //�抷��������ݒ肷��B
        // �@@[set�抷�������ɓn���p�����^]
        //�@@�@@�抷�������F get����()�̖߂�l
        Timestamp l_tsSwtchingExecutedDate = new Timestamp(l_datSwtExecutedDate.getTime());
        l_interceptor.setSwitchingExecutionTimestamp(l_tsSwtchingExecutedDate);

        //��������ݒ肷��B
        //   [set�������ɓn���p�����^]
        //   �������F���N�G�X�g�f�[�^.������
        String l_strOrderedDate = WEB3DateUtility.formatDate(l_request.orderedDate, "yyyyMMdd");
        l_interceptor.setBizDate(l_strOrderedDate);

        //�������ʃ^�C�v
        QuantityTypeEnum l_qtyTypeEnum = null;
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
        }
        else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_qtyTypeEnum = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_qtyTypeEnum = QuantityTypeEnum.QUANTITY;
        }

        //1.33�j�V�K�������e�̐���
        //�g�����M�V�K�������e�𐶐�����B
        //[�g�����M�V�K�������e�̃R���X�g���N�^�ɓn���p�����^]
        //�㗝���͎ҁF this.get�㗝���͎�()�̖߂�l
        //is���t�F false
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
        //�������ʁF
        //(*) ���N�G�X�g�f�[�^.�w����@@==�h�S���h and
        //is���z�w����()�̖߂�l==true �̏ꍇ��
        //0���w��B
        //(*) ����ȊO�̏ꍇ��
        //���N�G�X�g�f�[�^.���ʂ��w��B
        //�������ʃ^�C�v�F
        //(*) ���N�G�X�g�f�[�^.�w����@@���h2�F�S���h�̏ꍇ��
        //QuantityTypeEnum.���ʂ��w��B
        //(*) ���N�G�X�g�f�[�^.�w����@@���h3�F���z�h�̏ꍇ��
        //QuantityTypeEnum.���z���w��B
        //(*) ���N�G�X�g�f�[�^.�w����@@���h4�F�����h�̏ꍇ��
        //QuantityTypeEnum.���ʂ��w��B
        //�ŋ敪�F �ۗL���Y�e�[�u��Params.get�ŋ敪()�̖߂�l
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_request.specifyDiv) && l_blnIsPriceDesignateCancelling)
        {
            l_dblOrderQuantity = 0;
        }

        WEB3MutualFundNewOrderSpec l_mfNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                this.getTrader(),
                false,
                l_request.mutualProductCode,
                l_dblOrderQuantity,
                l_qtyTypeEnum,
                l_asset.getTaxType());

        //1.34) validate����]��
        //[����]
        // �⏕�����F �⏕�����I�u�W�F�N�g
        // �������e�C���^�Z�v�^�F ���M�V�K�����m��C���^�Z�v�^�i�抷�j��v�f�Ƃ����z��
        // �������e�F �g�����M�V�K�������e��v�f�Ƃ����z��
        // ������ʁF OrderTypeEnum.�����M���抷����
        // �]�͍X�V�t���O�F false
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_interceptor};

        Object[] l_arrNewOrderSpecs = {l_mfNewOrderSpec};
        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_SWITCHING,
                true);

        //is����t���O()�̖߂�l��false�̏ꍇ�A[���򒥎��S�����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����
        if(!l_result.isResultFlg())
        {
            log.debug("���򒥎��S�����]�̓`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02324,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���򒥎��S�����]�̓`�F�b�N�G���[");
        }

        //1.35�j�抷
        //�g�����M�����}�l�[�W��.submitNewOrder()���R�[������B
        OrderSubmissionResult l_orderSubmissionResult =
            l_orderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_mfNewOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                true);
        //�g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l����
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("�V�K�������s");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�V�K�������s");
        }

        //1.36���N�G�X�g�f�[�^�D�Љ�敪�@@!= NULL�@@�̏ꍇ
        if (!WEB3StringTypeUtility.isEmpty(l_request.introduceStoreDiv))
        {
            //1.36.1 getOrderUnits(long)
            //���M�����P�ʃI�u�W�F�N�g���擾����B
            //�mgetOrderUnits�ɓn���p�����^�n
            //�@@�@@�@@arg0�F ���N�G�X�g.����ID
            OrderUnit[] l_orderUnits =
                l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));

            MutualFundOrderUnit[] l_mfOrderUnits =
                new MutualFundOrderUnit[l_orderUnits.length];
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                l_mfOrderUnits[i] = (MutualFundOrderUnit) l_orderUnits[i];
            }
            MutualFundOrderUnitRow l_mfOrderUnitRow =
                (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
            MutualFundOrderUnitParams l_mfOrderUnitParams =
                new MutualFundOrderUnitParams(l_mfOrderUnitRow);

            //1.36.2�����P�ʏЉ�敪( )
            OrderUnitIntroduceDivParams l_introduceDivParams =
                new OrderUnitIntroduceDivParams();

            //1.36.3���v���p�e�B�Z�b�g��
            //�����P��ID   =�@@�擾���������P��.�����P��ID
            //����ID         =  �擾���������P��.����ID
            //�����^�C�v     =  ������ProductTypeEnum.�����M��
            //�Љ�敪      =  ���N�G�X�g.�Љ�敪
            //�Љ�X�R�[�h  =  ���N�G�X�g.�Љ�X�R�[�h
            //�X�V�҃R�[�h
            //�@@ �E�ڋq���͂̏ꍇ
            //       �@@�擾���������P��.����ID�ɊY����������R�[�h���Z�b�g����B
            // �@@�E�㗝���͂̏ꍇ
            //       �@@�擾���������P��.�����ID�ɊY�����鈵�҃R�[�h���Z�b�g����B
            l_introduceDivParams.setOrderUnitId(l_mfOrderUnitParams.getOrderId());
            l_introduceDivParams.setAccountId(l_mfOrderUnitParams.getAccountId());
            l_introduceDivParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_introduceDivParams.setIntroduceBranchDiv(l_request.introduceStoreDiv);
            l_introduceDivParams.setIntroduceBranchCode(l_request.introduceStoreCode);
            if (this.getTrader() == null)
            {
                try
                {
                    l_genMainAccount =
                        (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                            l_mfOrderUnitParams.getAccountId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_genMainAccount.getAccountCode());
            }
            else
            {
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Trader l_trder = null;
                try
                {
                    l_trder = l_finObjMgr.getTrader(l_mfOrderUnitParams.getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_trder.getTraderCode());
            }

            //�쐬���t = ���ݎ���
            l_introduceDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //�X�V���t = ���ݎ���
            l_introduceDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //1.36.4 saveNew�����P�ʏЉ�敪( )
            try
            {
                Processors.getDefaultProcessor().doInsertQuery(l_introduceDivParams);
            }
            catch (DataFindException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.37�j���������̎擾
        Date l_datProcessDate = null;
        l_datProcessDate =
            (Date) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3MutualFundTradingTimeManagement.TIMESTAMP_TAG);

        //1.38�j���M�抷�������X�|���X�I�u�W�F�N�g�𐶐����A���^�[������B
        //���M�抷�������X�|���X�I�u�W�F�N�g�ɂ́A�ȉ��̒l��ݒ肷��B
        WEB3MutualSwitchingCompleteResponse l_response =
            (WEB3MutualSwitchingCompleteResponse) l_request.createResponse();

        //1.39�j�v���p�e�B�Z�b�g
        //�X�V���ԁF �擾������������
        l_response.lastUpdatedTimestamp = l_datProcessDate;
        //���ʔԍ��F ���N�G�X�g�f�[�^.����ID
        l_response.orderActionId = l_request.orderId;

        return l_response;
    }
}
@
