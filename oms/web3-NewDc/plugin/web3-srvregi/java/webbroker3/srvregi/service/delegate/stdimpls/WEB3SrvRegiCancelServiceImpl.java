head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p����T�[�r�XImpl(WEB3SrvRegiCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiCancelService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p����T�[�r�XImpl)<BR>
 * �T�[�r�X���p����T�[�r�X�����N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiCancelServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiCancelService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiCancelServiceImpl.class);

    /**
     * @@roseuid 416F39270242
     */
    public WEB3SrvRegiCancelServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p����������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate���( )�܂��́A <BR>
     * submit���( )���\�b�h���R�[������B <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EDE80010
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request instanceof WEB3SrvRegiCancelConfirmRequest)
        {
            log.debug("WEB3SrvRegiCancelConfirmRequest");
            WEB3SrvRegiCancelConfirmResponse l_response =
                validateCancel((WEB3SrvRegiCancelConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3SrvRegiCancelCompleteRequest)
        {
            log.debug("WEB3SrvRegiCancelCompleteRequest");
            WEB3SrvRegiCancelCompleteResponse l_response =
                submitCancel((WEB3SrvRegiCancelCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate���)<BR>
     * �T�[�r�X���p����R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�\������R���v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�\������R���v): <BR>
     *         1.6.getServiceRegist(String, String, String, String, String, String, )<BR>
     *          �T�[�r�X�\���o�^�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00908<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�\������R���v): <BR>
     *          1.7.is����\( )<BR>
     *          ����۔���<BR>
     *        �@@false���ԋp���ꂽ�ꍇ�A��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01010<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�\������R���v): <BR>
     *          1.9.1.1 get���I�ݒ�( )<BR>
     *          get���I�ݒ�( )="��"�̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00878<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p����m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EE0C007E
     */
    protected WEB3SrvRegiCancelConfirmResponse validateCancel(WEB3SrvRegiCancelConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateCancel(WEB3SrvRegiCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate������t�\
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        log.debug("get�⏕����");
        //1.5 validate����\�ڋq
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate����\�ڋq");
            WEB3SrvRegiCancelConfirmResponse l_genResponse = (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();
            l_genResponse.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_genResponse.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_genResponse;
        }

        //1.6 get�T�[�r�X�\���o�^
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        
		//��Q�Ή� NO_U01542
		WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
			l_srvRegiRegistService.getServiceRegistCancelUnit(l_strInstitutionCode, l_strBranchCode, l_request.serviceDiv,
			l_strAccountCode, false);

        if (l_gentradeSrvRegiApplication == null)
        {
            log.debug("BUSINESS_ERROR_00908");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00908,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.7 get�\���o�^ID
        long l_lngRegistId = l_gentradeSrvRegiApplication.getRegistId();

        //1.8 is����\
        boolean l_blnCancelPossible = l_srvRegiRegistService.isCancelPossible(l_strInstitutionCode, l_strBranchCode,
            l_request.serviceDiv, l_strAccountCode, l_lngRegistId);
        if (!l_blnCancelPossible)
        {
            log.debug("BUSINESS_ERROR_01010");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.9 get�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.9.1 get�\���v�T�[�r�X
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.9.1.1 get���I�ݒ�
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("BUSINESS_ERROR_00878");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00878,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.10 get�\����
        Timestamp l_tsAppliDate = l_gentradeSrvRegiApplication.getAppliDate();

        //1.11 get�T�[�r�X���I���
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_tsAppliDate, 0);

        //1.12 get�^�p�敪
        String l_strInvestDiv = l_srvRegiServiceLotInfo.getInvestDiv();

        //1.13 <���򏈗�>
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfoBefore = null;
        if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv))
        {
            log.debug("get�T�[�r�X���I���");
            //1.13.1 get�T�[�r�X���I���
            l_srvRegiServiceLotInfoBefore =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_tsAppliDate, -1);
        }

        //1.14 create���X�|���X
        WEB3SrvRegiCancelConfirmResponse l_response = (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();

        //1.15  <���X�|���X�E�Z�b�g>
        //�����I�敪�@@�@@=�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get���I�ݒ�( )
        l_response.lotteryDiv = l_strLotDiv;
        //���^�p�敪�@@�@@=�\���Ώۂ̃T�[�r�X���I���.get�^�p�敪( )
        l_response.useDiv = l_srvRegiServiceLotInfo.getInvestDiv();
        //���T�[�r�X���� =�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
        l_response.serviceName = l_srvRegiServiceMaster.getSrvName();
        //���T�[�r�X���e =�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X���e( )
        l_response.serviceContent = l_srvRegiApplicationRequiredService.getSrvContents();
        //����W�g�@@�@@�@@�@@�@@ =�\���Ώۂ̃T�[�r�X���I���.get��W�g( )
        if (l_srvRegiServiceLotInfo.getPublicOfferingQty() == null)
        {
            log.debug("l_response.applyMax = null;");
            l_response.applyMax = null;
        }
        else
        {
            log.debug("����W�g");
            l_response.applyMax = l_srvRegiServiceLotInfo.getPublicOfferingQty().toString();
        }

        //���\�����ԁi���j�@@=�\���Ώۂ̃T�[�r�X���I���.get�\�����ԁi���j( )
        l_response.applyStartDate = l_srvRegiServiceLotInfo.getAppliDateFrom();
        //���\�����ԁi���j�@@=�\���Ώۂ̃T�[�r�X���I���.get�\�����ԁi���j( )
        l_response.applyEndDate = l_srvRegiServiceLotInfo.getAppliDateTo();
        //�����I���@@�@@�@@�@@�@@ =�\���Ώۂ̃T�[�r�X���I���.get���I��( )
        l_response.lotteryDate = l_srvRegiServiceLotInfo.getLotDate();
        //���K�p�J�n���@@�@@ =�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�J�n��( )
        l_response.trialStartDate = l_gentradeSrvRegiApplication.getAppliStartDate();
        //���K�p�I�����@@�@@ =�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )
        l_response.trialEndDate = l_gentradeSrvRegiApplication.getAppliEndDate();
        //�����D�P�ʁ@@�@@�@@�@@=�\���Ώۂ̃T�[�r�X���I���.get���D�P��( )
        if (l_srvRegiServiceLotInfo.getBiddingPrice() == null)
        {
            log.debug("l_response.biddingPriceUnit = null;");
            l_response.biddingPriceUnit = null;
        }
        else
        {
            log.debug("���D�P��");
            l_response.biddingPriceUnit = l_srvRegiServiceLotInfo.getBiddingPrice().toString();
        }

        if (l_srvRegiServiceLotInfo.isAuctionSetting() &&
            l_gentradeSrvRegiApplication.getUseAmt() != null &&
            l_gentradeSrvRegiApplication.getUseAmt().doubleValue() > 0)
        {
            log.debug("(*1-1) ���I�敪='�L'�ł���A ���擾�����T�[�r�X���I���.is�I�[�N�V�����ݒ�( )=true�̏ꍇ");

            //�ŗ��I�u�W�F�N�g�𐶐�����B
            WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                WEB3DutyTypeDef.CONSUMPTION_TAX,
                l_srvRegiServiceLotInfo.getPaymentDate());

            //�����p����
            double l_dblTaxRate = l_taxRate.getTaxRate();//�ŗ�

            //WEB3-SRVREGI-A-�e�s-0136
            l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(
                Math.rint(l_srvRegiServiceLotInfo.getUseAmt() / (1 + l_dblTaxRate / 100)));

            //���\�������@@
            if (l_gentradeSrvRegiApplication.getUseAmt() == null)
            {
                l_response.applyAmt = null;
            }
            else
            {
                //WEB3-SRVREGI-A-�e�s-0136
                l_response.applyAmt = WEB3StringTypeUtility.formatNumber(
                    Math.rint(l_gentradeSrvRegiApplication.getUseAmt().doubleValue() / (1 + l_dblTaxRate / 100)));
            }
        }
        else
        {
            //�����p�����@@�@@�@@�@@=�\���Ώۂ̃T�[�r�X���I���.get���p����( )
            l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
            //���\�������@@�@@�@@�@@=�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get���p����( )
            if (l_gentradeSrvRegiApplication.getUseAmt() == null)
            {
                log.debug("l_response.applyAmt = null;");
                l_response.applyAmt = null;
            }
            else
            {
                log.debug("�\������");
                l_response.applyAmt = WEB3StringTypeUtility.formatNumber(l_gentradeSrvRegiApplication.getUseAmt().doubleValue());
            }
        }

        //*---<�O�񕪂̃T�[�r�X���I���=null�̏ꍇ>---*
        if (l_srvRegiServiceLotInfoBefore == null)
        {
            log.debug("�O�񕪂̃T�[�r�X���I���=null�̏ꍇ");
            //���O��̍ō����D�z�@@=null
            l_response.maxSuccBidding = null;
            //���O��̍Œᗎ�D�z�@@=null
            l_response.minSuccBidding = null;
            //���O��̉��d���ϊz�@@=null
            l_response.weightedAverage = null;
        }
        //*---<�O�񕪂̃T�[�r�X���I���!=null�̏ꍇ>---*
        else
        {
            log.debug("�O�񕪂̃T�[�r�X���I���!=null�̏ꍇ");
            //���O��̍ō����D�z�@@=�O�񕪂̃T�[�r�X���I���.get�ō����D�z( )
            if (l_srvRegiServiceLotInfoBefore.getHighSuccessBid() == null)
            {
                log.debug("l_response.maxSuccBidding = null;");
                l_response.maxSuccBidding = null;
            }
            else
            {
                log.debug("�O��̍ō����D�z");
                l_response.maxSuccBidding = l_srvRegiServiceLotInfoBefore.getHighSuccessBid().toString();
            }

            //���O��̍Œᗎ�D�z�@@=�O�񕪂̃T�[�r�X���I���.get�Œᗎ�D�z( )
            if (l_srvRegiServiceLotInfoBefore.getLowSuccessBid() == null)
            {
                log.debug("l_response.minSuccBidding = null;");
                l_response.minSuccBidding = null;
            }
            else
            {
                log.debug("�O��̍Œᗎ�D�z");
                l_response.minSuccBidding = l_srvRegiServiceLotInfoBefore.getLowSuccessBid().toString();
            }

            //���O��̉��d���ϊz�@@=�O�񕪂̃T�[�r�X���I���.get���d���ϊz( )
            if (l_srvRegiServiceLotInfoBefore.getAverageSuccessBid() == null)
            {
                log.debug("l_response.weightedAverage = null;");
                l_response.weightedAverage = null;
            }
            else
            {
                log.debug("�O��̉��d���ϊz");
                l_response.weightedAverage = l_srvRegiServiceLotInfoBefore.getAverageSuccessBid().toString();
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���)<BR>
     * �T�[�r�X���p����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�\������v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�\������v): <BR>
     *         1.8.getServiceRegist(String, String, String, String, String, String, )  <BR>
     *          ����ΏۂƂȂ�T�[�r�X�\���o�^�I�u�W�F�N�g��<BR>
     *          �擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01009<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�\������v): <BR>
     *         1.9.is����\( )  <BR>
     *          ����۔���<BR>
     *        �@@false���ԋp���ꂽ�ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01010<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�\������v): <BR>
     *          1.11.1.1 get���I�ݒ�( )<BR>
     *          get���I�ݒ�( )="��"�̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00878<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p����������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EE0C008D
     */
    protected WEB3SrvRegiCancelCompleteResponse submitCancel(WEB3SrvRegiCancelCompleteRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitCancel(WEB3SrvRegiCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate������t�\
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate����\�ڋq
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate����\�ڋq");
            WEB3SrvRegiCancelCompleteResponse l_genResponse = (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_genResponse.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_genResponse.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_genResponse;
        }

        //1.6 get�㗝���͎�
        Trader l_trader = this.getTrader();

        //1.7 validate����p�X���[�h
        OrderValidationResult l_orderValidationResult2 =
            l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if(l_orderValidationResult2.getProcessingResult().isFailedResult())
        {
            log.debug("validate����p�X���[�h");
            WEB3SrvRegiCancelCompleteResponse l_genResponse = (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_genResponse.errorInfo = l_orderValidationResult2.getProcessingResult().getErrorInfo();
            l_genResponse.errorMessage = l_orderValidationResult2.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_genResponse;
        }

        //1.8get�T�[�r�X�\���o�^
        log.debug("get�T�[�r�X�\���o�^");
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();

        //��Q�Ή� NO_U01542
		WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
			l_srvRegiRegistService.getServiceRegistCancelUnit(l_strInstitutionCode, l_strBranchCode, l_request.serviceDiv,
			l_strAccountCode, true);
			
        if (l_gentradeSrvRegiApplication == null)
        {
            log.debug("BUSINESS_ERROR_01009");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01009,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.9 get�\���o�^ID
        long l_lngRegistId = l_gentradeSrvRegiApplication.getRegistId();

        //1.10 is����\
        boolean l_blnCancelPossible = l_srvRegiRegistService.isCancelPossible(l_strInstitutionCode, l_strBranchCode,
            l_request.serviceDiv, l_strAccountCode, l_lngRegistId);
        if (!l_blnCancelPossible)
        {
            log.debug("BUSINESS_ERROR_01010");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.11 get�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.11.1 get�\���v�T�[�r�X
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.11.1.1 get���I�ݒ�
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("BUSINESS_ERROR_00878");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00878,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.12 get���p����
        Double l_useAmt = l_gentradeSrvRegiApplication.getUseAmt();

        //1.13 <�]�͉������>
        if (!(l_useAmt == null || l_useAmt.doubleValue() == 0))
        {
            log.debug("�]�͉������");
            //1.13.1 get����ID
            long l_lngOrderId = 0;
            if (l_gentradeSrvRegiApplication.getOrderId() != null)
            {
                l_lngOrderId = l_gentradeSrvRegiApplication.getOrderId().longValue();
            }

            //1.13.2 submit�]�͉��
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_srvRegiRegistService.submitRemainingPowerRelease((WEB3GentradeSubAccount)l_subAccount, l_lngOrderId,
                l_crypt.decrypt(l_request.password));
        }

        //1.14 set����敪
        l_gentradeSrvRegiApplication.setCancelDiv(WEB3SrvRegiCancelDivDef.CANCEL);

        //1.15 set�ŏI�X�V��
        if (l_trader != null)
        {
            l_gentradeSrvRegiApplication.setLastUpdater(l_trader.getTraderCode());
        }
        else
        {
        	//��Q�Ή� NO_2051
            l_gentradeSrvRegiApplication.setLastUpdater(l_strAccountCode.substring(0,6));
        }

        //1.16 save�T�[�r�X�\���o�^
        l_gentradeSrvRegiApplication.saveSrvRegiApplication();

        //1.17  create���X�|���X
        WEB3SrvRegiCancelCompleteResponse l_response = (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_response.lastUpdatedTimestamp = l_tsSystemTimestamp;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
