head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�����T�[�r�X�����N���X(WEB3MutualBuyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ���� (���u) ���r���[
Revesion History : 2004/12/13 ������ (���u) �c�Ή�
Revesion History : 2005/10/18 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/06/26 ���� (���u) �d�l�ύX(���f��)�F419
Revesion History : 2006/09/11 ���� �d�l�ύX�E���f��489�A496�A497�A�c�a�X�V�d�l078
Revesion History : 2006/10/11 �ęԍg(���u) �d�l�ύX ���f�� No.500 �c�a�X�V�d�lNo.083
Revesion History : 2007/02/05 ������ (���u) ���f�� No.525
Revesion History : 2007/03/26 ��іQ (���u) ���f�� No.550
Revesion History : 2007/04/09 ��іQ (���u) ���f�� No.556,����005
Revesion History : 2007/04/19 �����F (���u) ���f�� No.566
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
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
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3CommissionDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.mf.message.WEB3MutualBuyCompleteResponse;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequest;
import webbroker3.mf.message.WEB3MutualBuyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ���M���t�����T�[�r�XImpl<BR>
 * �����M�����t�����T�[�r�X�����N���X
 *
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualBuyService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyServiceImpl.class);

    /**
     * �����M�����t�����T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate���t����()�Asubmit���t����()<BR>
     * �����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555CE803C7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_request instanceof WEB3MutualBuyConfirmRequest)
        {
            WEB3MutualBuyConfirmResponse l_mutualBuyConfirmResponse =
                this.validateBuyOrder((WEB3MutualBuyConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_mutualBuyConfirmResponse;
        }
        else if (l_request instanceof WEB3MutualBuyCompleteRequest)
        {
            WEB3MutualBuyCompleteResponse l_mutualBuyCompleteResponse =
                this.submitBuyOrder((WEB3MutualBuyCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_mutualBuyCompleteResponse;
        }
        else
        {
            // �p�����[�^�l���s��
            log.debug(STR_METHOD_NAME + " �p�����[�^�l���s������I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    /**
     * (validate���t����)<BR>
     * �����M�����t�����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)���t�����R���v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�����R���v: <BR>
     *         13(*7) (is����t���O( )��false�̏ꍇfalse)�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualBuyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555EC5031B
     */
    protected WEB3MutualBuyConfirmResponse validateBuyOrder(
        WEB3MutualBuyConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateBuyOrder(WEB3MutualBuyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1�j�@@���͓��e�`�F�b�N
        l_request.validate();

        //1.2�j�@@�⏕�����擾
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3�j�@@�g�����M�����擾
        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_mutualFundProductManager = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        //�|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�g�����M�������擾����B
        WEB3MutualFundProduct l_mutualFundProduct = null; //�g�����M����
        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundOrderManager l_mutualFundOrderManager = null;
        l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //���M���t�����m�F���X�|���X�I�u�W�F�N�g�𐶐�
        WEB3GenResponse l_response = l_request.createResponse();
        WEB3MutualBuyConfirmResponse l_mutualBuyInputResponse =
            (WEB3MutualBuyConfirmResponse) l_response;
        try
        {
            try
            {
                l_mutualFundProduct =
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_request.mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("no find MutualFundProduct ", l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //=================�ڋq�ʎ����~�����`�F�b�N================
            //1.4�j�����`�F�b�N�I�u�W�F�N�g���擾����B
            //FinApp�N���X��getCommonOrderValidator()���R�[����
            //�����`�F�b�N�I�u�W�F�N�g���擾����
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //1.5�j�|�����`�F�b�N.validate����\�ڋq()���R�[������
            Timestamp l_datBizDate =
                new Timestamp(
                    l_request.orderedDate.getTime());

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

            //1.6�jvalidate������t�\( )
            //�|���M������ԊǗ�.validete������t�\()���R�[������
            WEB3GentradeTradingTimeManagement.validateOrderAccept();


            //1.7�j������Ԃ̍Đݒ�
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);

            //1.8�j�|��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //1.9�jvalidate�V�K����(SubAccount, �g�����M����, double, String, String, String, �g�����M����,TaxTypeEnum)
            //�mvalidate�V�K�����ɓn���p�����^�n
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //�g�����M�����F �擾�����g�����M�����I�u�W�F�N�g
            //�������z���ʁF ���N�G�X�g�f�[�^.����
            //�����敪�F �h1�F���t�h
            //��n���@@�F null
            //�w����@@�F ���N�G�X�g�f�[�^.�w����@@
            //�抷������F null
            //�ŋ敪�F null
            //���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@
            NewOrderValidationResult l_newOrderValidationResult =
                l_mutualFundOrderManager.validateNewOrder(
                    l_subAccount,
                    l_mutualFundProduct,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    WEB3ProcessDivDef.BUY,
                    null,
                    l_request.specifyDiv,
                    null,
                    null,
                    l_request.settleDiv);

            if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
            {
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.10�jget������(Date)
            //�m�F���������F�@@���N�G�X�g�f�[�^.������
            //������ʁ@@�@@�@@�F  OrderTypeEnum�D�����M��������
            //�ꊇ�敪�@@�@@�@@�F�@@�擾�����g�����M�����Dis������������()
            Date l_orderBizDate =
                WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                    l_request.orderedDate,
                    OrderTypeEnum.MF_BUY,
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));

            //1.11 get����(Institution, String, Date)
            //�،���ЁF �擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
            //�������F �擾����������
            Timestamp l_tsExeecuted = l_mutualFundProductManager.getExecutedDate(
                l_subAccount.getInstitution(),
                l_request.mutualProductCode,
                l_orderBizDate);

            //1.12�j�@@��n���擾
            Timestamp l_Timestamp = null; //��n��
            l_Timestamp =
                l_mutualFundProductManager.getDeliveryDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    true,
                    l_tsExeecuted);
            log.debug("WEB3MutualBuyServiceImpl.validateBuyOrder::l_Timestamp = " +
                l_Timestamp);

            //1.13�j���N�G�X�g�f�[�^.�����敪�̒l���h1�F����h�̏ꍇ�A��������`�F�b�N���s��

            //1.13.1�jget�ڋq()
            WEB3GentradeMainAccount l_gentradeMainAccount = null; //�ڋq
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //1.13.2�jis��������J��(Date, �⏕����)
            if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_gentradeMainAccount =
                    (
                        WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                            l_subAccount.getAccountId());

                if (!l_gentradeMainAccount.isSpecialAccountEstablished(
                        l_Timestamp, l_subAccount))
                {
                    log.debug("���q�l�͓���������J�݂���Ă���܂���B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���q�l�͓���������J�݂���Ă���܂���B");
                }
            }

            //1.14�j�@@�T�Z��n����擾
            WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice = null;
            //�T�Z��n���
            //�mcalc�T�Z��n����ɓn���p�����^�n
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //�����F �擾�����g�����M�����I�u�W�F�N�g
            //�����i�抷��j�F null
            //�����敪�F �h1�F���t�h
            //�������ʁF ���N�G�X�g�f�[�^.����
            //�w����@@�F ���N�G�X�g�f�[�^.�w����@@
            //���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@
            //�������@@�F null
            //�����敪�F ���N�G�X�g�f�[�^.�����敪
            //�����`���l���F �Z�b�V�������擾���������`���l��
            //�������F ���N�G�X�g�f�[�^.������

            //�Z�b�V�������擾���������`���l��
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            String l_strOrderChanel =
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

            l_mutualFundEstimatedPrice =
                l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_mutualFundProduct,
                    null,
                    WEB3ProcessDivDef.BUY,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_request.specifyDiv,
                    l_request.settleDiv,
                    null,
                    l_request.taxType,
                    l_strOrderChanel,
                    l_request.orderedDate);
            log.debug("WEB3MutualBuyServiceImpl.validateBuyOrder::" +
                    "l_mutualFundEstimatedPrice = " +
                    l_mutualFundEstimatedPrice.getEstimatedPrice());

            //1.15�j����]�͎c���`�F�b�N
            //1.15.1�j���M�V�K�����m��C���^�Z�v�^�𐶐�����
            WEB3MutualFundNewOrderConfirmInterceptor l_mutualFundNewOrderConfirmInterceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();

            //1.15.2�j���M�V�K�����m��C���^�Z�v�^���g�����M�����}�l�[�W���ɐݒ肷��
            l_mutualFundOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_mutualFundNewOrderConfirmInterceptor);

            //�@@���M�V�K�����m��C���^�Z�v�^�Ɏ�n����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDate(l_Timestamp);

            //�@@���M�V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannel(
                this.getLoginChannel());

            //�@@���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setConstantValue(
                l_mutualFundProduct.getConstantValue());

            //�@@���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z�i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingConstantValue(
                Double.NaN);

            //�@@���M�V�K�����m��C���^�Z�v�^�Ɋ���z�K�p����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setConstantValueAppDate(
                l_mutualFundProduct.getConstantValueAppDate());

            //�@@���M�V�K�����m��C���^�Z�v�^�ɊT�Z��n�����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedPrice(
                l_mutualFundEstimatedPrice.getEstimatedPrice());

            //�@@���M�V�K�����m��C���^�Z�v�^�ɊT�Z����������ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedQty(
                l_mutualFundEstimatedPrice.getEstimatedQty());

            //�@@���M�V�K�����m��C���^�Z�v�^�ɊT�Z���t�����i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingEstimatedQty(
                Double.NaN);

            //�@@���M�V�K�����m��C���^�Z�v�^�ɐŋ敪�i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectTaxDivision(
                null);

            //�@@���M�V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDiv(null);

            //�@@���M�V�K�����m��C���^�Z�v�^�ɓ��M�^�C�v��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundType(
                Integer.toString(
                    l_mutualFundProduct.getMutualFundType().intValue()));

            //�@@���M�V�K�����m��C���^�Z�v�^�ɓ��M���敪��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundSellDiv(null);

            //�@@���M�V�K�����m��C���^�Z�v�^�ɖ�����ݒ肷��B
            l_mutualFundNewOrderConfirmInterceptor.setExecutionTimestamp(l_tsExeecuted);

            //�@@���M�V�K�����m��C���^�Z�v�^�Ɍ��ϋ敪��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSettlementType(
                l_request.settleDiv);

            //�@@���M�V�K�����m��C���^�Z�v�^�ɖ��萔���敪��ݒ肷��
            //(*) �g�����M�����}�l�[�W��.is�萔�������ڋq() == true �̏ꍇ�A�h9�F���萔���h��ݒ肷��B
            //  [is�萔�������ڋq()�ɓn���p�����[�^]
            //�@@�@@�ڋq�F �ڋq�I�u�W�F�N�g
            //�@@�@@�����F �擾�����g�����M�����I�u�W�F�N�g
            if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.NO_COMMISSION);
            }
            //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l�� �h0�F���җD���s�h�̏ꍇ�A
            //�u�����N��ݒ肷��
            else if (l_mutualFundProduct.isSwitchingPerferenceObject())
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.SWITCHING_PREFERENCE);
            }
            else
            {
                //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l��false�̏ꍇ�̓u�����N��ݒ肷��
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    " ");
            }

            //�����R�[�h�i�抷��j�Fnull
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);

            //�����敪�Fnull
            l_mutualFundNewOrderConfirmInterceptor.setRequestDivision(null);

            //�����o�H�敪�F�Z�b�V�������擾���������ڂ̒l
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannelDivision(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //�o���������ʃR�[�h�Fnull
            l_mutualFundNewOrderConfirmInterceptor.setPaymentOrderReqNumber(null);

            //�ꊇ�敪�F�擾�����g�����M�����Dis������������
            //[is�������������̃p�����[�^]
            //������� �F OrderTypeEnum�D�����M��������
            l_mutualFundNewOrderConfirmInterceptor.setNorealDiv(
                l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));

            //�����I�����F�擾�����g�����M�����DgetDataSourceObject().get���t�I����()
            MutualFundProductRow l_fundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            l_mutualFundNewOrderConfirmInterceptor.setOrderEndDate(
                l_fundProductRow.getBuyEndDate());

            //1.15.4)�g�����M�V�K�������e(Trader, boolean, String, String, String, double, QuantityTypeEnum, TaxTypeEnum)
            //�@@�V�K�������e�̐���
            QuantityTypeEnum l_orderQuantityType = null;
            TaxTypeEnum l_taxType = null;
            if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.AMOUNT;
            }
            else if (
                WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.QUANTITY;
            }

            if (WEB3MFAccountDivDef.NORMAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            else if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.SPECIAL;
            }
            else if (WEB3MFAccountDivDef.OTHER.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.UNDEFINED;
            }

            WEB3MutualFundNewOrderSpec l_expMutualFundNewOrderSpec =
                new WEB3MutualFundNewOrderSpec(
                    this.getTrader(),
                    true,
                    l_request.mutualProductCode,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_orderQuantityType,
                    l_taxType);

            //���N�G�X�g�f�[�^.���ϋ敪 == �h�~�݁h �̏ꍇ
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);

                Object[] l_arrNewOrderConfirmInterceptors =
                    {l_mutualFundNewOrderConfirmInterceptor};
                Object[] l_arrNewOrderSpecs = {l_expMutualFundNewOrderSpec};

                //1.15.5)����]�͌��ʃI�u�W�F�N�g���擾����B
                WEB3TPTradingPowerResult l_result =
                    l_tpTradingPowerService.validateTradingPower(
                        l_subAccount,
                        l_arrNewOrderConfirmInterceptors,
                        l_arrNewOrderSpecs,
                        OrderTypeEnum.MF_BUY,
                        false);
                //1.14.7)is����t���O( )��false�̏ꍇ
                if(!l_result.isResultFlg())
                {
                    log.debug("�]�͎c���G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�]�͎c���G���[");
                }
            }

            double l_dbEstimatedQty =
                l_mutualFundEstimatedPrice.getEstimatedQty();
            double l_dbEstimatedPrice =
                l_mutualFundEstimatedPrice.getEstimatedPrice();
            log.debug("WEB3MutualBuyServiceImpl.validateBuyOrder::" +
                    "l_dbEstimatedQty = " +
                    l_dbEstimatedQty);

            //1.16)�g�����M�����}�l�[�W��.createNewOrderId()���R�[�����Ē���ID���擾����
            long l_orderId = l_mutualFundOrderManager.createNewOrderId();

            //1.17�j�@@���M���t�����m�F���X�|���X�I�u�W�F�N�g���Z�b�g���A���^�[������

            // �T�Z��n����ʉ݃R�[�h�F
            // (*) ���N�G�X�g�f�[�^.���ϕ��@@���h1�F�~�݁h�̏ꍇ��
            // �hT0�F�~�h��ݒ肷��B
            // (*) ���N�G�X�g�f�[�^.���ϕ��@@���h2�F�O�݁h�̏ꍇ��
            // �擾�����g�����M�����I�u�W�F�N�g.get�ʉ݃R�[�h()�̖߂�l��ݒ肷��B
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                l_mutualBuyInputResponse.estimatedPriceCurrencyCode =
                    WEB3MFEstimatedPriceCurrencyCodeDef.T0;
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))
            {
                l_mutualBuyInputResponse.estimatedPriceCurrencyCode =
                    l_mutualFundProduct.getCurrencyCode();
            }

            //�T�Z��n���
            l_mutualBuyInputResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_dbEstimatedPrice);
            //�T�Z��������
            l_mutualBuyInputResponse.estimatedQty =
                WEB3StringTypeUtility.formatNumber(l_dbEstimatedQty);
            l_mutualBuyInputResponse.orderId = l_orderId + "";

            log.debug(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("no find MainAccount", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //return
        return l_mutualBuyInputResponse;
    }

    /**
     * (submit���t����)<BR>
     * �����M�����t�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)���t�����o�^�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�����o�^�v: <BR>
     *         21(*3) (is����t���O( )��false�̏ꍇfalse)�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�����o�^�v: <BR>
     *         15((���N�G�X�g�f�[�^.�����敪�̒l���h1�F����h�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00026<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualBuyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555ECB0389
     */
    protected WEB3MutualBuyCompleteResponse submitBuyOrder(
        WEB3MutualBuyCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitBuyOrder(WEB3MutualBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1�j�@@���͓��e�`�F�b�N
        l_request.validate();

        //1.2�j�@@�⏕�����擾
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3�j�@@�g�����M�����擾
        //�|�g�����M�����}�l�[�W�����擾����
        WEB3MutualFundProductManager l_mutualFundProductManager = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        //�|�g�����M�����}�l�[�W��.get���M����()���R�[�����A�g�����M�������擾����
        WEB3MutualFundProduct l_mutualFundProduct = null; //�g�����M����
        WEB3MutualBuyCompleteResponse l_MutualBuyCompleteResponse = null;
        try
        {
            try
            {
                l_mutualFundProduct =
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_request.mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("no find MutualFundProduct ", l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

          //1.4�j�@@�ڋq�ʎ����~�����`�F�b�N
          //�|�g�����M�����}�l�[�W�����擾����
          WEB3MutualFundOrderManager l_mutualFundOrderManager = null;
          l_mutualFundOrderManager =
              (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                  ProductTypeEnum.MUTUAL_FUND).getOrderManager();

            //FinApp�N���X��getCommonOrderValidator()���R�[����
            //�����`�F�b�N�I�u�W�F�N�g���擾����
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //�|�����`�F�b�N.validate����\�ڋq()���R�[������
            Timestamp l_datBizDate =
                new Timestamp(
                    l_request.orderedDate.getTime());

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

            //1.5)�|validate����p�X���[�h( )���R�[������B
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

            //1.8�j�@@��t���ԃ`�F�b�N�A�V�X�e�������~�`�F�b�N
            //�|���M������ԊǗ�.validete������t�\()���R�[������
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //1.9�j������Ԃ̍Đݒ�
            //�|���M������ԊǗ�.reset�����R�[�h()���R�[�����A�����R�[�h�𓊐M���t����
            //���̓��N�G�X�g.�����R�[�h�ɕύX����
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_request.mutualProductCode);

            //1.10)�|��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //1.11�j�����R��
            //�|�g�����M�����}�l�[�W��.validate�V�K����()���R�[�����A�����R�����s��
            //�mvalidate�V�K�����ɓn���p�����^�n
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //�g�����M�����F �擾�����g�����M�����I�u�W�F�N�g
            //�������z���ʁF ���N�G�X�g�f�[�^.����
            //�����敪�F �h1�F���t�h
            //��n���@@�F null
            //�w����@@�F ���N�G�X�g�f�[�^.�w����@@
            //��������F null
            //�ŋ敪�F null
            //���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@

            NewOrderValidationResult l_newOrderValidationResult =
                l_mutualFundOrderManager.validateNewOrder(
                    l_subAccount,
                    l_mutualFundProduct,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    WEB3ProcessDivDef.BUY,
                    null,
                    l_request.specifyDiv,
                    null,
                    null,
                    l_request.settleDiv);

            if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
            {
                throw new WEB3BusinessLayerException(
                    l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.12�j�@@�������擾
            //�m�F���������F�@@���N�G�X�g�f�[�^.������
            //������ʁ@@�@@�@@�F  OrderTypeEnum�D�����M��������
            //�ꊇ�敪�@@�@@�@@�F�@@�擾�����g�����M�����Dis������������
            //[is�������������̈���]
            //������� �F OrderTypeEnum�D�����M��������
            Date l_orderBizDate = WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.orderedDate,
                OrderTypeEnum.MF_BUY,
                l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));

            //1.13�j�@@�����擾
            Timestamp l_tsexecutedDate = null;
            l_tsexecutedDate =
                l_mutualFundProductManager.getExecutedDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    l_orderBizDate);

            //1.14�j�@@��n���擾
            Timestamp l_Timestamp = null; //��n��
            l_Timestamp =
                l_mutualFundProductManager.getDeliveryDate(
                    l_subAccount.getInstitution(),
                    l_request.mutualProductCode,
                    true,
                    l_tsexecutedDate);
            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_executedDate = " +
                l_tsexecutedDate);
            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_Timestamp = " +
                l_Timestamp);

            //1.15�j�@@��������`�F�b�N
            WEB3GentradeMainAccount l_gentradeMainAccount = null; //�ڋq
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_gentradeMainAccount =
                    (WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                        l_subAccount.getAccountId());
                if (!l_gentradeMainAccount.isSpecialAccountEstablished(
                    l_Timestamp, l_subAccount))
                {
                    log.debug("is��������J��()�� false ��Ԃ��ꍇ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������`�F�b�N�G���[");
                }
            }

            //1.16�j�@@�T�Z��n����擾
            WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice = null;

            //�T�Z��n���
            //�mcalc�T�Z��n����ɓn���p�����^�n
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //�����F �擾�����g�����M�����I�u�W�F�N�g
            //�����i�抷��j�F null
            //�����敪�F �h1�F���t�h
            //�������ʁF ���N�G�X�g�f�[�^.����
            //�w����@@�F ���N�G�X�g�f�[�^.�w����@@
            //���ϕ��@@�F ���N�G�X�g�f�[�^.���ϕ��@@
            //�������@@�F null
            //�����敪�F ���N�G�X�g�f�[�^.�����敪
            //�����`���l���F �Z�b�V�������擾���������`���l��
            //�������F ���N�G�X�g�f�[�^.������

            //�Z�b�V�������擾���������`���l��
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            String l_strOrderChanel =
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

            l_mutualFundEstimatedPrice =
                l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_mutualFundProduct,
                    null,
                    WEB3ProcessDivDef.BUY,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_request.specifyDiv,
                    l_request.settleDiv,
                    null,
                    l_request.taxType,
                    l_strOrderChanel,
                    l_request.orderedDate);

            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_mutualFundEstimatedPrice = " +
                l_mutualFundEstimatedPrice);

            //1.17�j�@@���M�V�K�����m��C���^�Z�v�^�𐶐�����
            WEB3MutualFundNewOrderConfirmInterceptor l_mutualFundNewOrderConfirmInterceptor =
                new WEB3MutualFundNewOrderConfirmInterceptor();

            //1.18�j�@@���M�V�K�����m��C���^�Z�v�^���g�����M�����}�l�[�W���ɐݒ肷��
            l_mutualFundOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_mutualFundNewOrderConfirmInterceptor);
            //1.19) �����������M�V�K�����m��C���^�Z�v�^�Ɉȉ��̃v���p�e�B��ݒ肷��B

            //���M�V�K�����m��C���^�Z�v�^�Ɏ�n����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDate(l_Timestamp);
            //���M�V�K�����m��C���^�Z�v�^�ɒ����`���l����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannel(
                this.getLoginChannel());
            //���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z��ݒ肷��
            if (l_mutualFundProduct.isFrgnMmf())
            {
                l_mutualFundNewOrderConfirmInterceptor.setConstantValue(Double.NaN); 
            }
            else
            {
                l_mutualFundNewOrderConfirmInterceptor.setConstantValue(
                    l_mutualFundProduct.getConstantValue());
            }
            //���M�V�K�����m��C���^�Z�v�^�Ɍv�Z����z�i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingConstantValue(
                Double.NaN);
            //���M�V�K�����m��C���^�Z�v�^�Ɋ���z�K�p����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setConstantValueAppDate(
                l_mutualFundProduct.getConstantValueAppDate());
            //�V�K�����m��C���^�Z�v�^�ɊT�Z��n�����ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedPrice(
                l_mutualFundEstimatedPrice.getEstimatedPrice());
            //���M�V�K�����m��C���^�Z�v�^�ɊT�Z����������ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setEstimatedQty(
                l_mutualFundEstimatedPrice.getEstimatedQty());
            //���M�V�K�����m��C���^�Z�v�^�ɊT�Z���t�����i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingEstimatedQty(
                Double.NaN);
            //���M�V�K�����m��C���^�Z�v�^�ɐŋ敪�i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectTaxDivision(
                null);
            //���M�V�K�����m��C���^�Z�v�^�Ɏ�n���@@��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setDeliveryDiv(null);
            //���M�V�K�����m��C���^�Z�v�^�ɓ��M�^�C�v��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundType(
                Integer.toString(
                    l_mutualFundProduct.getMutualFundType().intValue()));
            //���M�V�K�����m��C���^�Z�v�^�ɓ��M���敪��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setMutualFundSellDiv(null);
            //���M�V�K�����m��C���^�Z�v�^�ɖ�����ݒ肷��B
            l_mutualFundNewOrderConfirmInterceptor.setExecutionTimestamp(
                l_tsexecutedDate);
            //���M�V�K�����m��C���^�Z�v�^�Ɍ��ϋ敪��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSettlementType(
                l_request.settleDiv);

            //���M�V�K�����m��C���^�Z�v�^�ɖ��萔���敪��ݒ肷��
            //(*) �g�����M�����}�l�[�W��.is�萔�������ڋq() == true �̏ꍇ�A�h9�F���萔���h��ݒ肷��B
            //  [is�萔�������ڋq()�ɓn���p�����^]
            //    �ڋq�F �ڋq�I�u�W�F�N�g
            //    �����F �擾�����g�����M�����I�u�W�F�N�g
            if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.NO_COMMISSION);
            }
            else if (l_mutualFundProduct.isSwitchingPerferenceObject())
            {
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    WEB3CommissionDivDef.SWITCHING_PREFERENCE);
            }
            else
            {
                //�擾�����g�����M����.is�抷�D���Ώ�()�̖߂�l��false�̏ꍇ�̓u�����N��ݒ肷��
                l_mutualFundNewOrderConfirmInterceptor.setNoCommissionDivision(
                    " ");
            }

            //���M�V�K�����m��C���^�Z�v�^�ɖ����R�[�h�i�抷��j��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setSwitchingSubjectMutualProductCode(
                null);
            //���M�V�K�����m��C���^�Z�v�^�ɐ����敪��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setRequestDivision(null);
            //���M�V�K�����m��C���^�Z�v�^�ɒ����o�H�敪��ݒ肷��
            l_mutualFundNewOrderConfirmInterceptor.setOrderChannelDivision(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //�R���M�V�K�����m��C���^�Z�v�^�ɏo���������ʃR�[�h��ݒ肷��B
            //[set�o���������ʃR�[�h�ɓn���p�����^]
            //�o���������ʃR�[�h�F null
            l_mutualFundNewOrderConfirmInterceptor.setPaymentOrderReqNumber(null);

            //�S���M�V�K�����m��C���^�Z�v�^�Ɉꊇ�敪��ݒ肷��B
            //[set�ꊇ�敪�ɓn���p�����^]
            // �ꊇ�敪�F�擾�����g�����M�����Dis������������()
            //[is�������������̈���]
            //������� �F OrderTypeEnum�D�����M��������
            l_mutualFundNewOrderConfirmInterceptor.setNorealDiv(
                l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));
            //21���M�V�K�����m��C���^�Z�v�^�ɒ����I������ݒ肷��B
            //[set�����I�����ɓn���p�����^]
            //�����I�����F�擾�����g�����M�����DgetDataSourceObject().get���t�I����()
            MutualFundProductRow l_fundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            l_mutualFundNewOrderConfirmInterceptor.setOrderEndDate(
                l_fundProductRow.getBuyEndDate());
            //1.20�j�@@�V�K�������e�̐���
            QuantityTypeEnum l_orderQuantityType = null;
            TaxTypeEnum l_taxType = null;
            if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.AMOUNT;
            }
            else if (
                WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    l_request.specifyDiv))
            {
                l_orderQuantityType = QuantityTypeEnum.QUANTITY;
            }

            if (WEB3MFAccountDivDef.NORMAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            else if (WEB3MFAccountDivDef.SPECIAL.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.SPECIAL;
            }
            else if (WEB3MFAccountDivDef.OTHER.equals(l_request.taxType))
            {
                l_taxType = TaxTypeEnum.UNDEFINED;
            }

            WEB3MutualFundNewOrderSpec l_expMutualFundNewOrderSpec =
                new WEB3MutualFundNewOrderSpec(
                    this.getTrader(),
                    true,
                    l_request.mutualProductCode,
                    Double.parseDouble(l_request.mutualOrderQuantity),
                    l_orderQuantityType,
                    l_taxType);

            //���N�G�X�g�f�[�^.���ϋ敪 == �h�~�݁h �̏ꍇ
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
            {
                //1.21�j�@@����]�̓`�F�b�N�E����]�͍X�V
                WEB3TPTradingPowerService l_tpTradingPowerService =
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);
    
                Object[] l_arrNewOrderConfirmInterceptors =
                    {l_mutualFundNewOrderConfirmInterceptor};
                Object[] l_arrNewOrderSpecs = {l_expMutualFundNewOrderSpec};
    
                WEB3TPTradingPowerResult l_resule =
                    l_tpTradingPowerService.validateTradingPower(
                        l_subAccount,
                        l_arrNewOrderConfirmInterceptors,
                        l_arrNewOrderSpecs,
                        OrderTypeEnum.MF_BUY,
                        true);
    
                 //1.21.3)is����t���O()��false �̏ꍇ
                if(!l_resule.isResultFlg())
                {
                    log.debug("�]�͎c���G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�]�͎c���G���[");
                }
            }

            //1.22�j�@@���t����
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

            //1.23.�����N�G�X�g�f�[�^�D�Љ�敪�@@!= NULL�@@�̏ꍇ��
            if (l_request.introduceStoreDiv != null)
            {
                //1.23.1getOrderUnits(arg0 : long)
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
                //1.23.2.�����P�ʏЉ�敪( )
                OrderUnitIntroduceDivParams l_introduceDivParams = new OrderUnitIntroduceDivParams();
                //1.23.3.<�v���p�e�B�Z�b�g>
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
                if (getTrader() == null)
                {
                    l_gentradeMainAccount =
                        (WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                            l_orderUnitParams.getAccountId());
                    l_introduceDivParams.setLastUpdater(l_gentradeMainAccount.getAccountCode());
                }
                else
                {
                    FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                    Trader l_trder = l_finObjMgr.getTrader(l_orderUnitParams.getTraderId());
                    l_introduceDivParams.setLastUpdater(l_trder.getTraderCode());
                }
                //�쐬���t = ���ݎ���
                l_introduceDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //�X�V���t = ���ݎ���
                l_introduceDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //1.23.3.saveNew�����P�ʏЉ�敪( )
                //�����P�ʏЉ�敪�e�[�u���ɃC���T�[�g����B
                Processors.getDefaultProcessor().doInsertQuery(l_introduceDivParams);

            }

            //1.24�jgetAttribute(String)
            Date l_date = null;
            l_date = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_date = " + l_date);

            //1.25�j�@@���M���t�����������X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
            WEB3GenResponse l_response = l_request.createResponse();
            l_MutualBuyCompleteResponse =
                (WEB3MutualBuyCompleteResponse) l_response;

            //1.26)�v���p�e�B�E�Z�b�g
            l_MutualBuyCompleteResponse.lastUpdatedTimestamp = l_date;
            l_MutualBuyCompleteResponse.orderActionId = l_request.orderId;
        }
        catch (NotFoundException l_ex)
        {
            log.error("no find MainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_MutualBuyCompleteResponse;
    }
}
@
