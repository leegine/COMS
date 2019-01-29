head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M����W�����T�[�r�X�����N���X(WEB3MutualRecruitOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 ���� (���u) �V�K�쐬
Revesion History : 2006/06/26 ���H�n (���u) �d�l�ύX(���f��)�F419
Revesion History : 2006/09/12 ��іQ (���u) �d�l�ύX�E���f��489�A�c�a�X�V�d�l078�A079
Revesion History : 2006/10/12 ��іQ (���u) �d�l�ύX�E���f��503�A�c�a�X�V�d�l083
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3CommissionDivDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderApplyConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualApplyCompleteRequest;
import webbroker3.mf.message.WEB3MutualApplyCompleteResponse;
import webbroker3.mf.message.WEB3MutualApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualApplyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M����W�����T�[�r�X�����N���X
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderServiceImpl
        extends WEB3MutualClientRequestService implements WEB3MutualRecruitOrderService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderServiceImpl.class);

    /**
     * ���N�G�X�g�f�[�^�ɂ���āA�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * �@@validate��W����() <BR>
     * �@@submit��W����() <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MutualApplyConfirmRequest)
        {
            //validate��W����
            l_response =
                this.validateOrder((WEB3MutualApplyConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MutualApplyCompleteRequest)
        {
            //submit��W����
            l_response =
                this.submitOrder((WEB3MutualApplyCompleteRequest) l_request);
        }
        else
        {
            log.debug(" �p�����[�^�l���s������I");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate��W����)<BR>
     * <BR>
     * �����M����W�����R�����s���B<BR>
     * �V�[�P���X�} <BR>
     *�u(���M)��W�����R���v�Q�ƁB<BR>
     *<BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)��W�����R���v: <BR>
     *    1.13.1((is��������J�� ��������`�F�b�N<BR>
     *    �|is��������J��()��false�̏ꍇ�A��O���X���[����<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_02096<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)��W�����R���v: <BR>
     *    1.22(is����t���O( )<BR>
     *    is����t���O()�̖߂�l��false�̏ꍇ�A[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���M��W�����m�F���N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3MutualApplyConfirmResponse validateOrder(WEB3MutualApplyConfirmRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOrder(WEB3MutualApplyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 get���M����(Institution, String)
        Institution l_institution = l_subAccount.getInstitution();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.mutualProductCode);
        }
        //�g�����M�������擾�o���Ȃ��ꍇ�A��O���X���[����
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.4 getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.5 validate����\�ڋq(�ڋq, Timestamp)
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        Timestamp l_tsOrderedDate = new Timestamp(l_request.orderedDate.getTime());

        OrderValidationResult l_validationResult =
            l_orderValidator.validateAccountForTrading(l_genMainAccount, l_tsOrderedDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.7 reset�����R�[�h(String)
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        //1.8 setTimestamp( )
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //1.9 validate�V�K����
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        NewOrderValidationResult l_newOrderValidationResult =
            l_mutualFundOrderManager.validateNewOrder(
                l_subAccount,
                l_mutualFundProduct,
                Double.parseDouble(l_request.mutualOrderQuantity),
                WEB3ProcessDivDef.RECRUIT,
                null,
                l_request.specifyDiv,
                null,
                null,
                null);

        if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.10�ꊇ�敪�擾
        //    �P�j���X�p�v���t�@@�����X����u���M��W�����ꊇ���M�敪�v���擾����B
        //    [���X�p�v���t�@@�����X�̎擾����]
        //       ���XID �F �擾�����⏕����.getMainAccount().getBranchId()
        //       �v���t�@@�����X�� �F mf.recruit.mq.send.div
        //       �v���t�@@�����X���̘A�� �F 1
        //  �����R�[�h�����́u�ꊇ���M����v
        //
        //�Q�j�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ���ꊇ�敪�փZ�b�g����B
        //    ���X�p�v���t�@@�����X.���M��W�����ꊇ���M�敪 == �u�ꊇ���M����v
        boolean l_blnMfRecruitMqSendDiv = true;
        BranchPreferencesRow l_row = null;
        try
        {
            l_row = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_subAccount.getMainAccount().getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_row.getValue()))
        {
            l_blnMfRecruitMqSendDiv = true;
        }
        else
        {
            l_blnMfRecruitMqSendDiv = false;
        }

        //1.11 get������(Date, OrderTypeEnum, boolean)
        //�������F�@@���N�G�X�g�f�[�^.������
        //������ʁF�@@OrderTypeEnum.�����M����W����
        //�ꊇ�敪�F�@@�擾�����ꊇ�敪
        Date l_datOrderBizDate =
            WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.orderedDate,
                OrderTypeEnum.MF_RECRUIT,
                l_blnMfRecruitMqSendDiv);

        //1.12 get��W�I����
        Timestamp l_tsRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

        //1.13 get��W�I�����iSONAR�j( )
        Timestamp l_tsApplyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();

        //1.14 get�ڋq()
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����
            l_genMainAccount =
                (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.15 if ���N�G�X�g�f�[�^.�����敪 == �h1:����h
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            //1.15.1 is��������J��
            //��������`�F�b�N is��������J��()��false�̏ꍇ�A��O���X���[����
            boolean l_blnIsSpecialAccountEstablished =
                l_genMainAccount.isSpecialAccountEstablished(
                    l_tsRecruitEndDate, l_subAccount);

            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("����������J�݃G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����������J�݃G���[�B");
            }
        }

        //1.16 calc�T�Z��n���
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice =
            l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mutualFundProduct,
                null,
                WEB3MFProcessDivDef.RECRUIT,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_request.specifyDiv,
                l_request.settleDiv,
                null,
                l_request.taxType,
                l_strOrderChanel,
                l_request.orderedDate);

        //1.17 ���M�V�K�����m��C���^�Z�v�^�i��W�j
        WEB3MutualFundNewOrderApplyConfirmInterceptor l_mfNewOrderApplyConfirmInterceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        //1.18 ���M�V�K�����m��C���^�Z�v�^�i��W�j�ւ̃v���p�e�B�Z�b�g
        //��n���F
        //�擾�����ꊇ�敪==true�Ȃ�擾������W�I����(SONAR)�A
        //�擾�����ꊇ�敪==false�Ȃ�擾������W�I����
        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsRecruitEndDate);
        }

        //�����`���l���Fthis.get���O�C���`���l��()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannel(this.getLoginChannel());

        //�v�Z����z�F�擾�����g�����M�����I�u�W�F�N�g.get��W���z()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setConstantValue(
            l_mutualFundProduct.getRecruitConstantValue());

        //�v�Z����z�i�抷��j�FDouble.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingConstantValue(Double.NaN);

        //����z�K�p���Fnull
        l_mfNewOrderApplyConfirmInterceptor.setConstantValueAppDate(null);

        //�T�Z��n����F�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedPrice(
            l_mutualFundEstimatedPrice.getEstimatedPrice());

        //�T�Z���������F�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedQty(
            l_mutualFundEstimatedPrice.getEstimatedQty());

        //�T�Z���t�����i�抷��j�FDouble.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingEstimatedQty(Double.NaN);

        //�ŋ敪�i�抷��j�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectTaxDivision(null);

        //��n���@@�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setDeliveryDiv(null);

        //���M�^�C�v�F�擾�����g�����M����.getMutualFundType()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundType(
            Integer.toString(l_mutualFundProduct.getMutualFundType().intValue()));

        //���M���敪�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundSellDiv(null);

        //�����F�擾����������
        l_mfNewOrderApplyConfirmInterceptor.setExecutionTimestamp(
            new Timestamp(l_datOrderBizDate.getTime()));

        //���ϋ敪�F���N�G�X�g�f�[�^.���ϋ敪
        l_mfNewOrderApplyConfirmInterceptor.setSettlementType(l_request.settleDiv);

        //���萔���敪�F(*)
        //�g�����M�����}�l�[�W��.is�萔�������ڋq() == true �̏ꍇ�A�h9�F���萔���h��ݒ肷��B
        //  [is�萔�������ڋq()�ɓn���p�����[�^]
        //    �ڋq�F �ڋq�I�u�W�F�N�g
        //    �����F �擾�����g�����M�����I�u�W�F�N�g
        if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.NO_COMMISSION);
        }
        //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l��
        //    �h0�F���җD���s�h�̏ꍇ�A�u�����N��ݒ肷��B
        //    ����ȊO�̏ꍇ�A�h5�F�抷�D���h��ݒ肷��B
        else if (l_mutualFundProduct.isSwitchingPerferenceObject())
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.SWITCHING_PREFERENCE);
        }
        else
        {
            //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l��false�̏ꍇ�̓u�����N��ݒ肷��
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(" ");
        }

        //�����R�[�h�i�抷��j�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);

        //�����敪�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setRequestDivision(null);

        //�����o�H�敪�F�Z�b�V��������擾���������ڂ̒l
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //�o���������ʃR�[�h�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setPaymentOrderReqNumber(null);

        //�ꊇ�敪�F�擾�����ꊇ�敪
        l_mfNewOrderApplyConfirmInterceptor.setNorealDiv(l_blnMfRecruitMqSendDiv);

        //�����I�����F�擾������W�I����
        l_mfNewOrderApplyConfirmInterceptor.setOrderEndDate(l_tsRecruitEndDate);

        //1.19 set������(Timestamp)
        //�擾�����ꊇ�敪==true�Ȃ�
        //�擾������W�I����(SONAR)
        //�擾�����ꊇ�敪==false�Ȃ�
        //�擾�����������̗��c�Ɠ�
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_gentradeBizDate.roll(1));
        }

        //1.20 get�㗝���͎�( )
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

        //1.21 �g�����M�V�K�������e
        //[����]
        //�㗝���͎ҁF this.get�㗝���͎�()�̖߂�l
        //is���t�F true
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
        //�������ʁF ���N�G�X�g�f�[�^.����
        //�������ʃ^�C�v�F
        //(*) ���N�G�X�g�f�[�^.�w����@@���h3�F���z�h�̏ꍇ��
        //QuantityTypeEnum.���z���w��B
        //(*) ���N�G�X�g�f�[�^.�w����@@���h4�F�����h�̏ꍇ��
        //QuantityTypeEnum.���ʂ��w��B
        QuantityTypeEnum l_orderQuantityType = null;
        if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.QUANTITY;
        }
        //�ŋ敪�F
        //(*) ���N�G�X�g�f�[�^.�����敪���h0�F��ʁh�̏ꍇ�A
        //TaxTypeEnum.NORMAL��ݒ肷��B
        //(*) ���N�G�X�g�f�[�^.�����敪���h1�F����h�̏ꍇ�A
        //TaxTypeEnum.SPECIAL��ݒ肷��B
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        WEB3MutualFundNewOrderSpec l_mutualFundNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                l_trader,
                true,
                l_request.mutualProductCode,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_orderQuantityType,
                l_taxType);

        //1.22 validate����]��
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_mfNewOrderApplyConfirmInterceptor};

        Object[] l_arrNewOrderSpecs = {l_mutualFundNewOrderSpec};
        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_RECRUIT,
                false);

        //1.23 is����t���O()��false �̏ꍇ
        //is����t���O()�̖߂�l��false�̏ꍇ�A[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����
        if(!l_result.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[");
        }

        //1.24 createNewOrderId( )
        long l_lngNewOrderId = l_mutualFundOrderManager.createNewOrderId();

        //1.25 createResponse( )
        WEB3MutualApplyConfirmResponse l_response =
            (WEB3MutualApplyConfirmResponse)l_request.createResponse();

        //1.26 �v���p�e�B�Z�b�g
        //���M��W�����m�F���X�|���X�ɒl��ݒ肷��B

        //�T�Z��n����ʉ݃R�[�h�F
        //(*)���N�G�X�g�f�[�^.���ϕ��@@���h1�F�~�݁h�̏ꍇ�́hT0�F�~�h��ݒ肷��B
        if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
        {
            l_response.estimatedPriceCurrencyCode =
                WEB3MFEstimatedPriceCurrencyCodeDef.T0;
        }
        else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))
        {
            //(*)���N�G�X�g�f�[�^.���ϕ��@@���h2�F�O�݁h�̏ꍇ��
            //�擾�����g�����M�����I�u�W�F�N�g.get�ʉ݃R�[�h()�̖߂�l��ݒ肷��B
            l_response.estimatedPriceCurrencyCode = l_mutualFundProduct.getCurrencyCode();
        }

        //�T�Z��n����F �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()��ݒ肷��B
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(
                l_mutualFundEstimatedPrice.getEstimatedPrice());

        //�T�Z���������F �擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l��ݒ肷��B
        l_response.estimatedQty =
            WEB3StringTypeUtility.formatNumber(
                l_mutualFundEstimatedPrice.getEstimatedQty());

        //����ID�F�g�����M�����}�l�[�W��.createNewOrderId())�̖߂�l
        l_response.orderId = l_lngNewOrderId + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit��W����)<BR>
     * <BR>
     * �����M����W�����o�^���s���B<BR>
     * �V�[�P���X�} <BR>
     *�u(���M)��W�����o�^�v�Q��<BR>
     *<BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)��W�����o�^�v: <BR>
     *     1.15((is��������J��(Date, �⏕����) ��������`�F�b�N<BR>
     *     �|is��������J��()��false�̏ꍇ�A�i��������`�F�b�N�G���[�j�Ƃ��ė�O���X���[����<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02096<BR>
     *==========================================================<BR>
     * <BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)��W�����o�^�v: <BR>
     *     1.25((if is����t���O() == false)<BR>
     *     is����t���O()�̖߂�l��false�̏ꍇ�A[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01306<BR>
     *==========================================================<BR>
     * @@param l_request - (���M��W�����������N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3MutualApplyCompleteResponse submitOrder(WEB3MutualApplyCompleteRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitOrder(WEB3MutualApplyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 get�⏕����( )
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 get���M����(Institution, String)
        Institution l_institution = l_subAccount.getInstitution();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.mutualProductCode);
        }
        //�g�����M�������擾�o���Ȃ��ꍇ�A��O���X���[����
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //1.4 getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.5 validate����\�ڋq(�ڋq, Timestamp)
        WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        Timestamp l_tsOrderedDate = new Timestamp(l_request.orderedDate.getTime());
        OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(l_genMainAccount, l_tsOrderedDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //1.7 validate����p�X���[�h
        OrderValidationResult l_validationResultPassword =
            l_orderValidator.validateTradingPassword(
                l_trader,
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

        //1.8 validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.9 reset�����R�[�h(String)
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        //1.10 setTimestamp( )
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //1.11 validate�V�K����
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        NewOrderValidationResult l_newOrderValidationResult =
            l_mutualFundOrderManager.validateNewOrder(
                l_subAccount,
                l_mutualFundProduct,
                Double.parseDouble(l_request.mutualOrderQuantity),
                WEB3ProcessDivDef.RECRUIT,
                null,
                l_request.specifyDiv,
                null,
                null,
                null);

        if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.12�ꊇ�敪�擾
        //    �P�j���X�p�v���t�@@�����X����u���M��W�����ꊇ���M�敪�v���擾����B
        //    [���X�p�v���t�@@�����X�̎擾����]
        //       ���XID �F �擾�����⏕����.getMainAccount().getBranchId()
        //       �v���t�@@�����X�� �F mf.recruit.mq.send.div
        //       �v���t�@@�����X���̘A�� �F 1
        //  �����R�[�h�����́u�ꊇ���M����v
        //
        //�Q�j�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ���ꊇ�敪�փZ�b�g����B
        //    ���X�p�v���t�@@�����X.���M��W�����ꊇ���M�敪 == �u�ꊇ���M����v
        boolean l_blnMfRecruitMqSendDiv = true;
        BranchPreferencesRow l_row = null;
        try
        {
            l_row = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_subAccount.getMainAccount().getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_row.getValue()))
        {
            l_blnMfRecruitMqSendDiv = true;
        }
        else
        {
            l_blnMfRecruitMqSendDiv = false;
        }

        //1.13 get������(Date, OrderTypeEnum, boolean)
        //�������F�@@���N�G�X�g�f�[�^.������
        //������ʁF�@@OrderTypeEnum.�����M����W����
        //�ꊇ�敪�F�@@�擾�����ꊇ�敪
        Date l_datOrderBizDate =
            WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.orderedDate,
                OrderTypeEnum.MF_RECRUIT,
                l_blnMfRecruitMqSendDiv);

        //1.14 get��W�I����
        Timestamp l_tsRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

        //1.15 get��W�I�����iSONAR�j( )
        Timestamp l_tsApplyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();

        //1.16 get�ڋq()
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����
            l_genMainAccount =
                (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.17 if ���N�G�X�g�f�[�^.�����敪 == �h1:����h
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            //1.17.1 is��������J��
            //��������`�F�b�N is��������J��()��false�̏ꍇ�A��O���X���[����
            boolean l_blnIsSpecialAccountEstablished =
                l_genMainAccount.isSpecialAccountEstablished(
                    l_tsRecruitEndDate, l_subAccount);

            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("����������J�݃G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����������J�݃G���[�B");
            }
        }

        //1.18 calc�T�Z��n���
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice =
            l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mutualFundProduct,
                null,
                WEB3MFProcessDivDef.RECRUIT,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_request.specifyDiv,
                l_request.settleDiv,
                null,
                l_request.taxType,
                l_strOrderChanel,
                l_request.orderedDate);

        //1.19 ���M�V�K�����m��C���^�Z�v�^�i��W�j
        WEB3MutualFundNewOrderApplyConfirmInterceptor l_mfNewOrderApplyConfirmInterceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        //1.20 set������(Timestamp)
        //�擾�����ꊇ�敪==true�Ȃ�
        //�擾������W�I����(SONAR)
        //�擾�����ꊇ�敪==false�Ȃ�
        //�擾�����������̗��c�Ɠ�
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_gentradeBizDate.roll(1));
        }

        //1.21 setThreadLocalPersistenceEventInterceptor(MutualFundOrderManagerPersistenceEventInterceptor)
        l_mutualFundOrderManager.setThreadLocalPersistenceEventInterceptor(l_mfNewOrderApplyConfirmInterceptor);

        //1.22 ���M�V�K�����m��C���^�Z�v�^�i��W�j�ւ̃v���p�e�B�Z�b�g

        //��n���F
        //�擾�����ꊇ�敪==true�Ȃ�擾������W�I����(SONAR)�A
        //�擾�����ꊇ�敪==false�Ȃ�擾������W�I����
        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsRecruitEndDate);
        }

        //�����`���l���Fthis.get���O�C���`���l��()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannel(this.getLoginChannel());

        //�v�Z����z�F�擾�����g�����M�����I�u�W�F�N�g.get��W���z()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setConstantValue(
            l_mutualFundProduct.getRecruitConstantValue());

        //�v�Z����z�i�抷��j�FDouble.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingConstantValue(Double.NaN);

        //����z�K�p���Fnull
        l_mfNewOrderApplyConfirmInterceptor.setConstantValueAppDate(null);

        //�T�Z��n����F�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��n���()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedPrice(
            l_mutualFundEstimatedPrice.getEstimatedPrice());

        //�T�Z���������F�擾�����T�Z��n����I�u�W�F�N�g.get�T�Z��������()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedQty(
            l_mutualFundEstimatedPrice.getEstimatedQty());

        //�T�Z���t�����i�抷��j�FDouble.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingEstimatedQty(Double.NaN);

        //�ŋ敪�i�抷��j�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectTaxDivision(null);

        //��n���@@�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setDeliveryDiv(null);

        //���M�^�C�v�F�擾�����g�����M����.getMutualFundType()�̖߂�l
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundType(
            Integer.toString(l_mutualFundProduct.getMutualFundType().intValue()));

        //���M���敪�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundSellDiv(null);

        //�����F�擾����������
        l_mfNewOrderApplyConfirmInterceptor.setExecutionTimestamp(
            new Timestamp(l_datOrderBizDate.getTime()));

        //���ϋ敪�F���N�G�X�g�f�[�^.���ϋ敪
        l_mfNewOrderApplyConfirmInterceptor.setSettlementType(l_request.settleDiv);

        //���萔���敪�F(*)
                //�g�����M�����}�l�[�W��.is�萔�������ڋq() == true �̏ꍇ�A�h9�F���萔���h��ݒ肷��B
        //  [is�萔�������ڋq()�ɓn���p�����[�^]
        //    �ڋq�F �ڋq�I�u�W�F�N�g
        //    �����F �擾�����g�����M�����I�u�W�F�N�g
                if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.NO_COMMISSION);
        }
        //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l��
        //�h0�F���җD���s�h�̏ꍇ�A�u�����N��ݒ肷��B
        //����ȊO�̏ꍇ�A�h5�F�抷�D���h��ݒ肷��B
        else if (l_mutualFundProduct.isSwitchingPerferenceObject())
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.SWITCHING_PREFERENCE);
        }
        else
        {
            //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l��false�̏ꍇ�̓u�����N��ݒ肷��
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(" ");
        }

        //�����R�[�h�i�抷��j�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);

        //�����敪�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setRequestDivision(null);

        //�����o�H�敪�F �Z�b�V��������擾���������ڂ̒l
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //�o���������ʃR�[�h�Fnull
        l_mfNewOrderApplyConfirmInterceptor.setPaymentOrderReqNumber(null);

        //�ꊇ�敪�F�擾�����ꊇ�敪
        l_mfNewOrderApplyConfirmInterceptor.setNorealDiv(l_blnMfRecruitMqSendDiv);

        //�����I�����F�擾������W�I����
        l_mfNewOrderApplyConfirmInterceptor.setOrderEndDate(l_tsRecruitEndDate);

        //1.21 �g�����M�V�K�������e
        //[����]
        //�㗝���͎ҁF this.get�㗝���͎�()�̖߂�l
        //is���t�F true
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
        //�������ʁF ���N�G�X�g�f�[�^.����
        //�������ʃ^�C�v�F
        //(*) ���N�G�X�g�f�[�^.�w����@@���h3�F���z�h�̏ꍇ��
        //QuantityTypeEnum.���z���w��B
        //(*) ���N�G�X�g�f�[�^.�w����@@���h4�F�����h�̏ꍇ��
        //QuantityTypeEnum.���ʂ��w��B
        QuantityTypeEnum l_orderQuantityType = null;
        if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.QUANTITY;
        }

        //�ŋ敪�F
        //(*) ���N�G�X�g�f�[�^.�����敪���h0�F��ʁh�̏ꍇ�A
        //TaxTypeEnum.NORMAL��ݒ肷��B
        //(*) ���N�G�X�g�f�[�^.�����敪���h1�F����h�̏ꍇ�A
        //TaxTypeEnum.SPECIAL��ݒ肷��B
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        WEB3MutualFundNewOrderSpec l_expMutualFundNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                l_trader,
                true,
                l_request.mutualProductCode,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_orderQuantityType,
                l_taxType);

        //1.22 validate����]��
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_mfNewOrderApplyConfirmInterceptor};

        Object[] l_arrNewOrderSpecs = {l_expMutualFundNewOrderSpec};

        WEB3TPTradingPowerResult l_resule =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_RECRUIT,
                true);

        //1.23 is����t���O( )
        //1.24 is����t���O()��false �̏ꍇ
        //is����t���O()�̖߂�l��false�̏ꍇ�A[����]�̓`�F�b�N�G���[]�Ƃ��ė�O���X���[����
        if(!l_resule.isResultFlg())
        {
            log.debug("����]�̓`�F�b�N�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����]�̓`�F�b�N�G���[");
        }

        //1.25 submitNewOrder
        //[����]
        // �⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
        // �����^�C�v�F ProductTypeEnum.�����M��
        // �V�K�������e�F �g�����M�V�K�������e
        // ����ID�F ���N�G�X�g�f�[�^.����ID
        // ����p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        // is�����R���ȗ��F true
        OrderSubmissionResult l_orderSubmissionResult = null;
        l_orderSubmissionResult =
            l_mutualFundOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_expMutualFundNewOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult()== false)
        {
            log.debug(
                "l_mutualFundOrderManager.submitNewOrder()." +
                "getProcessingResult().isSuccessfulResult() == false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�V�K�������s");
        }

        //1.26.�����N�G�X�g�f�[�^�D�Љ�敪�@@!= NULL�@@�̏ꍇ��
        if (l_request.introduceStoreDiv != null)
        {
            //1.26.1getOrderUnits(arg0 : long)
            //���M�����P�ʃI�u�W�F�N�g���擾����B
            //�mgetOrderUnits�ɓn���p�����^�n
            //�@@�@@�@@arg0�F ���N�G�X�g.����ID
            OrderUnit[] l_orderUnits =
                l_mutualFundOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
            MutualFundOrderUnit[] l_mfOrderUnits = new MutualFundOrderUnit[l_orderUnits.length];
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                l_mfOrderUnits[i] = (MutualFundOrderUnit) l_orderUnits[i];
            }
            MutualFundOrderUnitRow l_orderUnitRow =
                (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
            MutualFundOrderUnitParams l_orderUnitParams =
                new MutualFundOrderUnitParams(l_orderUnitRow);
            //1.26.2.�����P�ʏЉ�敪( )
            OrderUnitIntroduceDivParams l_introduceDivParams = new OrderUnitIntroduceDivParams();
            //1.26.3.<�v���p�e�B�Z�b�g>
            //�����P��ID   =�@@�擾���������P��.�����P��ID
            l_introduceDivParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            //����ID         =  �擾���������P��.����ID
            l_introduceDivParams.setAccountId(l_orderUnitParams.getAccountId());
            //�����^�C�v     =  ������ProductTypeEnum.�����M��
            l_introduceDivParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            //�Љ�敪      =  ���N�G�X�g.�Љ�敪
            l_introduceDivParams.setIntroduceBranchDiv(l_request.introduceStoreDiv);
            //�Љ�X�R�[�h  =  ���N�G�X�g.�Љ�X�R�[�h
            l_introduceDivParams.setIntroduceBranchCode(l_request.introduceStoreCode);
            //�X�V�҃R�[�h
            //�@@ �E�ڋq���͂̏ꍇ
            //       �@@�擾���������P��.����ID�ɊY����������R�[�h���Z�b�g����B
            // �@@�E�㗝���͂̏ꍇ
            //       �@@�擾���������P��.�����ID�ɊY�����鈵�҃R�[�h���Z�b�g����B
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            if (getTrader() == null)
            {
                try
                {
                    l_gentradeMainAccount =
                        (WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                            l_orderUnitParams.getAccountId());
                } catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_gentradeMainAccount.getAccountCode());
            }
            else
            {
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Trader l_trder = null;
                try
                {
                    l_trder = l_finObjMgr.getTrader(l_orderUnitParams.getTraderId());
                } catch (NotFoundException l_ex)
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
            //1.26.3.saveNew�����P�ʏЉ�敪( )
            //�����P�ʏЉ�敪�e�[�u���ɃC���T�[�g����B
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

        //1.27 getAttribute(String)
        Date l_datAttribute = null;
        l_datAttribute = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_datAttribute = " + l_datAttribute);

        //1.28 createResponse( )
        WEB3MutualApplyCompleteResponse l_response =
            (WEB3MutualApplyCompleteResponse)l_request.createResponse();

        //1.29 �v���p�e�B�Z�b�g
        //�ȉ��̃v���p�e�B���Z�b�g����B

        //�X�V���ԁF�擾������������
        l_response.lastUpdatedTimestamp = l_datAttribute;

        //���ʔԍ��F���N�G�X�g�̒���ID
        l_response.orderActionId = l_request.orderId;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
