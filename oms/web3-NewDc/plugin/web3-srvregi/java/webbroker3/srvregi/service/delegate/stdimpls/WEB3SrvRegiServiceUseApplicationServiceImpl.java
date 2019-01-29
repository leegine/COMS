head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUseApplicationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���T�[�r�XImpl(WEB3SrvRegiServiceUseApplicationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 ���o�� �V�K�쐬
Revesion History : 2007/06/05 ��іQ (���u) ���f��244,245,246,�c�a�X�V�d�l 037
Revesion History : 2007/06/07 ��іQ (���u) ���f��263
Revesion History : 2007/06/21 �����Q (���u) ���f��265
Revesion History : 2007/06/26 �����Q (���u) ���f��272
Revesion History : 2007/06/29 �����Q (���u) ���f��277
Revesion History : 2007/07/25 ����   (���u) ���f��295
Revesion History : 2007/07/27 ����   (���u) ���f��297,�c�a�X�V�d�l 040,041
Revesion History : 2008/02/25 ���n�m (���u) �d�l�ύX�E���f��311,325
Revesion History : 2008/03/03 ���g (���u) �d�l�ύX ���f��331,341
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvAppliAttributeProcDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiApplyKindDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiExecSendMailService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceUseApplicationService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (�T�[�r�X���p�\���T�[�r�XImpl)<BR>
 * �T�[�r�X���p�\���T�[�r�X�����N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUseApplicationServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiServiceUseApplicationService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceUseApplicationServiceImpl.class);

    /**
     * @@roseuid 416F392502EE
     */
    public WEB3SrvRegiServiceUseApplicationServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�\���������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate���p�\��( )�܂��́A <BR>
     * submit���p�\��( )���\�b�h���R�[������B <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F05B0149
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request instanceof WEB3SrvRegiApplyConfirmRequest)
        {
            WEB3SrvRegiApplyConfirmResponse l_response =
                validateUseAppli((WEB3SrvRegiApplyConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3SrvRegiApplyCompleteRequest)
        {
            WEB3SrvRegiApplyCompleteResponse l_response =
                submitUseAppli((WEB3SrvRegiApplyCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate���p�\��)<BR>
     * �T�[�r�X���p�\���R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j���p�\���R���v�Q��<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu  1.9.4.1.�ȉ��̏����̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������() == null �̏ꍇ�A���́A<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��)<BR>
     * �@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.9.4.3.get�����Ώۊ���( ) == null<BR>�@@
     * �@@�@@�@@�@@�̏ꍇ��O���X���[����<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.9.5.1get�T�[�r�X�\���������().�\�������敪 == <BR>
     * �@@�@@�@@�@@'1'(�����Ώ�)�@@�Ⴕ����<BR>
     * �@@�@@�@@�@@'2'(�\���s��) �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�\���m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F07D01B6
     */
    protected WEB3SrvRegiApplyConfirmResponse validateUseAppli(WEB3SrvRegiApplyConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateUseAppli(WEB3SrvRegiApplyConfirmRequest)";
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
        log.debug("validate������t�\");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        log.debug("getCommonOrderValidator");
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4  get�⏕����
        log.debug("get�⏕����");
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate����\�ڋq
        log.debug("validate����\�ڋq");
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiApplyConfirmResponse l_response = (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.6 get������
        log.debug("get������");
        Date l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

        //1.7�����ݓ��t�̎擾��
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //1.7 validate�\���o�^
        log.debug("validate�\���o�^");
        Long l_dblChangeId = null;
        Double l_dblBidAmt = null;

        if (l_request.chargeId != null)
        {
            l_dblChangeId = new Long(l_request.chargeId);
        }
        if (l_request.bidAmt != null)
        {
            l_dblBidAmt = Double.valueOf(l_request.bidAmt);
        }

        this.validateAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv, l_dblChangeId,
            l_dblBidAmt, l_tsSystemTimestamp, l_request.applyKindDiv, null);

        //1.8 <�u�K�p�J�n���v�u�K�p�I�����v�̎擾>
        //1.8.1 get�T�[�r�X�}�X�^�[
        log.debug("get�T�[�r�X�}�X�^�[");
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.8.1.1 get�\���v�T�[�r�X
        log.debug("get�\���v�T�[�r�X");
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //get�T�[�r�X�\���������(String, String, String, String, String)
        //�،���ЃR�[�h=�擾�����⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
        //���X�R�[�h=�擾�����⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
        //�����R�[�h=�擾�����⏕�����I�u�W�F�N�g.getMainAccount( ).getAccountCode( )
        //�T�[�r�X�敪=���N�G�X�g�f�[�^.ID
        //�A�b�v���[�h�敪=null
        List l_lisServiceAppliAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_request.serviceDiv,
            null);

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        String l_strFreeTargetPeriod = null;

        if (l_lisServiceAppliAttributeInfo != null)
        {
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
        }
        //���N�G�X�g�f�[�^.���������\���敪 = '1'�@@�̏ꍇ
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //�ȉ��̏����̏ꍇ�A��O���X���[����B
            //get�T�[�r�X�\���������() == null �̏ꍇ�A���́A
            //get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��) �̏ꍇ

            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(l_srvAppliAttributeRow.getAppliAttribute()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");
            }

            // get�����Ώۊ���( )
            l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get�����Ώۊ���( ) == null�@@�̏ꍇ��O���X���[����B
            if (l_strFreeTargetPeriod == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����Ώۊ��Ԃ����w��ł��B");
            }
        }

        //1.8.1.1.1.  get���I�ݒ�
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //1.8.2 <���򏈗� *1>
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_tsSystemTimestamp, 0);

        Timestamp l_tsAppliStartDate = null;
        Timestamp l_tsAppliEndDate = null;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
            !WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv))
        {
            log.debug("//1.8.2.1 calc�K�p�I����");
            l_tsAppliStartDate = l_tsSystemTimestamp;
            //1.8.2.1 calc�K�p�I����
            WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
            
            //���N�G�X�g�f�[�^.���������\���敪 = '1'�@@�̏ꍇ ���́A
			//����.�\����ʋ敪 == "���p�\��" �̏ꍇ
            String l_chargeId = null;
            
            if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(l_request.freeAttributeApplyDiv) || 
				(WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_request.applyKindDiv))) 
            {
            	l_chargeId = "0";
            }
            else
            {
            	l_chargeId = l_request.chargeId;
            }
            l_tsAppliEndDate = l_srvRegiRegistService.calcAppliEndDate(l_strInstitutionCode, l_strBranchCode,
                    l_request.serviceDiv, l_strAccountCode, l_tsSystemTimestamp, Long.parseLong(l_chargeId), l_request.specialDiv, l_request.freeAttributeApplyDiv);
        }
        
        //1.8.3
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("//1.8.3.1 get�K�p�J�n��");
            //1.8.3.1 get�K�p�J�n��
            l_tsAppliStartDate = l_srvRegiServiceLotInfo.getAppliStartDate();

            //1.8.3.2 get�K�p�I����
            l_tsAppliEndDate = l_srvRegiServiceLotInfo.getAppliEndDate();
        }
        else if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
            WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv))
        {
            l_tsAppliStartDate = l_tsSystemTimestamp;
            
			log.debug("�K�p�I����");
			//���K�p�I����=����.�\�����ɁA�P�����𑫂������t
			Calendar l_caleAppliStartDate = Calendar.getInstance();
			Calendar l_caleNewAppliEndDate = Calendar.getInstance();
				
			//�K�p�I�����̌v�Z
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String l_stAppliEndDate = formatter.format(l_tsAppliStartDate);
			Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
			l_caleAppliStartDate.setTime(l_datAppliEndDate);
			l_caleNewAppliEndDate.setTime(l_datAppliEndDate);
			int l_maxDay = 0;
			boolean l_maxDayFlag = false;
				
			//�K�p�I�����i�\�������ꃖ����j
			l_caleNewAppliEndDate.add(Calendar.MONTH, 1);
			log.debug("�y���������O�zl_caleNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
				
			if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
			{
				l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
				l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
				l_maxDayFlag = true;
			}
			
			//�ꃖ����̑O�������߂�
			if (!l_maxDayFlag)
			{
				l_caleNewAppliEndDate.add(Calendar.DATE, -1);
			}
                
			log.debug("�y����������zl_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
			Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();
			l_tsAppliEndDate = (new Timestamp(l_datNewAppliEndDate.getTime()));

        }

        //1.10  create���X�|���X
        WEB3SrvRegiApplyConfirmResponse l_response = (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();

        //1.11 <���X�|���X�E�Z�b�g>
        log.debug("<���X�|���X�E�Z�b�g>");
        //���K�p�J�n��=<�u�K�p�J�n���v�u�K�p�I�����v�̎擾>�Ŏ擾�����u�K�p�J�n���v
        l_response.trialStartDate = WEB3DateUtility.toDay(l_tsAppliStartDate);
        //���K�p�I����=<�u�K�p�J�n���v�u�K�p�I�����v�̎擾>�Ŏ擾�����u�K�p�I�����v
        l_response.trialEndDate = WEB3DateUtility.toDay(l_tsAppliEndDate);
        //���m�F��������=get������( )�̖߂�l
        l_response.checkDate = l_datOrderBizDate;
        //���ō����D�z��(*)
        if (l_srvRegiServiceLotInfo != null && l_srvRegiServiceLotInfo.isAuctionSetting() &&
            l_request.bidAmt != null &&  Double.valueOf(l_request.bidAmt).doubleValue() > 0)
        {
            //WEB3-SRVREGI-A-�e�s-0136
            //�ŗ��I�u�W�F�N�g�𐶐�����B
            WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                WEB3DutyTypeDef.CONSUMPTION_TAX,
                l_srvRegiServiceLotInfo.getPaymentDate());
            if (l_dblBidAmt == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //�u�ō����D�z�v�ւ̐ݒ�l���u�i���N�G�X�g�f�[�^.���D�z�~�i�P�{�擾�����ŗ��I�u�W�F�N�g.get�ŗ�()�̖߂�l/100�j�j���l�̌ܓ��������́v�ɕύX����B
            l_response.taxBidAmt = WEB3StringTypeUtility.formatNumber(Math.rint(l_dblBidAmt.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
        }
        else
        {
            log.debug("�T�[�r�X���I���.is�I�[�N�V�����ݒ�( )==false�̏ꍇ");
            log.debug("�ō����D�z��Null���Z�b�g����B");
            l_response.taxBidAmt = null;
        }

        //[���N�G�X�g�f�[�^.���������\���敪 = '1' �̏ꍇ]
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //���\�������敪 = get�T�[�r�X�\���������().�\�������敪
            l_response.applyAttributeDiv = l_srvAppliAttributeRow.getAppliAttribute();

            //�������Ώۊ��� = get�����Ώۊ���()�̖߂�l
            l_response.freeTargetPeriod = l_strFreeTargetPeriod;

            //�����������\���敪 = '1'(���������\���j
            l_response.freeAttributeApplyDiv = WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���p�\��)<BR>
     * �T�[�r�X���p�\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j���p�\���o�^�v�Q��<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu  1.12.1.�ȉ��̏����̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������() == null �̏ꍇ�A���́A<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��)<BR>
     * �@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.12.3.get�����Ώۊ���( ) == null<BR>�@@
     * �@@�@@�@@�@@�̏ꍇ��O���X���[����<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.13.1get�T�[�r�X�\���������().�\�������敪 == <BR>
     * �@@�@@�@@�@@'1'(�����Ώ�)�@@�Ⴕ����<BR>
     * �@@�@@�@@�@@'2'(�\���s��) �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�\���������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F07D01C6
     */
    protected WEB3SrvRegiApplyCompleteResponse submitUseAppli(WEB3SrvRegiApplyCompleteRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitUseAppli(WEB3SrvRegiApplyCompleteRequest)";
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
        log.debug("validate");
        l_request.validate();

        //1.2 validate������t�\
        log.debug("validate������t�\");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4  get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

        //1.5 validate����\�ڋq
        log.debug("validate����\�ڋq");
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiApplyCompleteResponse l_response = (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.6 get�㗝���͎�
        log.debug("get�㗝���͎�");
        Trader l_trader = this.getTrader();

        //1.7 validate����p�X���[�h
        log.debug("validate����p�X���[�h");
        OrderValidationResult l_orderValidationResult2 = l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if(l_orderValidationResult2.getProcessingResult().isFailedResult())
        {
            log.debug("validate����p�X���[�h error");
            WEB3SrvRegiApplyCompleteResponse l_response = (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult2.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult2.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.8 get������
        //Date l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //1.9 validate�\���o�^
        log.debug("validate�\���o�^");
        //Timestamp l_tsCheckDate = new Timestamp(l_request.checkDate.getTime());
        Long l_dblChangeId = null;
        Double l_dblBidAmt = null;

        if (l_request.chargeId != null)
        {
            l_dblChangeId = new Long(l_request.chargeId);
        }
        if (l_request.bidAmt != null)
        {
            l_dblBidAmt = Double.valueOf(l_request.bidAmt);
        }

        this.validateAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv, l_dblChangeId,
            l_dblBidAmt, l_tsSystemTimestamp, l_request.applyKindDiv, l_request.password);

        // get�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        // get�\���v�T�[�r�X
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //get�T�[�r�X�\���������(String, String, String, String, String)
        //�،���ЃR�[�h=�擾�����⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
        //���X�R�[�h=�擾�����⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
        //�����R�[�h=�擾�����⏕�����I�u�W�F�N�g.getMainAccount( ).getAccountCode( )
        //�T�[�r�X�敪=���N�G�X�g�f�[�^.ID
        //�A�b�v���[�h�敪=null
        List l_lisServiceAppliAttributeInfo =
            l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_request.serviceDiv,
                null);

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        String l_strFreeTargetPeriod = null;

        if (l_lisServiceAppliAttributeInfo != null)
        {
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
        }
        //���N�G�X�g�f�[�^.���������\���敪 = '1'�@@�̏ꍇ
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //get�T�[�r�X�\���������() == null �̏ꍇ�A���́A
            //get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��) �̏ꍇ��O���X���[����B
            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(l_srvAppliAttributeRow.getAppliAttribute()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");
                }

            // get�����Ώۊ���( )
            l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get�����Ώۊ���( ) == null�@@�̏ꍇ��O���X���[����B
            if (l_strFreeTargetPeriod == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����Ώۊ��Ԃ����w��ł��B");
            }
        }

        //1.10 <�L���������菈��>
        String l_strChargeAmt = null;
        Timestamp l_tsPaymentDate = null;
        //1.10.1 <*1 ���򏈗�>
        if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_request.applyKindDiv)
            || WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv)
            || WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
                l_request.freeAttributeApplyDiv))
        {
            log.debug("1.10.1 <*1 ���򏈗�>");
            l_strChargeAmt = null;
        }
        //1.10.2  <*2 ���򏈗�>
        else
        {
            log.debug("1.10.2  <*2 ���򏈗�>");

            //1.10.2.1.1.1 get���I�ݒ�
            String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

            //1.10.2.2 <*3 ���򏈗�>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug("//1.10.2.2.1 get�T�[�r�X���p���ԗ���");
                //1.10.2.2.1 get�T�[�r�X���p���ԗ���
                WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt =
                    l_srvRegiServiceMaster.getSrvUseTermAmt(Long.parseLong(l_request.chargeId), false);

                //1.10.2.2.2 get���p����
                l_strChargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt.getUseAmt());
                //Model No.120
                Date l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

                if (l_datOrderBizDate != null)
                {
                    //1.8:�c�Ɠ��v�Z(Timestamp)
                    WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

                    //roll(int)
                    l_tsPaymentDate = l_datBizDate.roll(1);
                }
                else
                {
                    l_tsPaymentDate = null;
                }
            }
            //1.10.2.3  <*4 ���򏈗�>
            else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                log.debug("//1.10.2.3.1 get�T�[�r�X���I���");
                //1.10.2.3.1 get�T�[�r�X���I���
                WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                    l_srvRegiServiceInfoManagement.getSrvLotInfo(l_srvRegiServiceMaster.getInstitutionCode(),
                    l_request.serviceDiv, l_tsSystemTimestamp, 0);

                //1.10.2.3.2 get�o����
                l_tsPaymentDate = l_srvRegiServiceLotInfo.getPaymentDate();

                //1.10.2.3.3 get�^�p�敪
                String l_strInvestDiv = l_srvRegiServiceLotInfo.getInvestDiv();

                //1.10.2.3.4 <*5 ���򏈗�>
                if (WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(l_strInvestDiv) ||
                    WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(l_strInvestDiv))
                {
                    log.debug("//1.10.2.3.4.1 get���p����");
                    //1.10.2.3.4.1 get���p����
                    l_strChargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
                }
                //1.10.2.3.5 <*6 ���򏈗�>
                else if(WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv)
                         && l_request.bidAmt != null &&  Double.valueOf(l_request.bidAmt).doubleValue() > 0)
                {
                    log.debug("1.10.2.3.5 <*6 ���򏈗�>");

                    //1.10.2.3.5.1 calc�����
                    //WEB3-SRVREGI-A-�e�s-0136
                    //�ŗ��I�u�W�F�N�g�𐶐�����B
                    WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                        WEB3DutyTypeDef.CONSUMPTION_TAX,
                        l_srvRegiServiceLotInfo.getPaymentDate());

                    if (l_dblBidAmt == null)
                    {
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //�u���p�����v�̐ݒ���ucalc�����()�v����u�i���N�G�X�g�f�[�^.���D�z�i�P�{�擾�����ŗ��I�u�W�F�N�g.get�ŗ�()�̖߂�l/100�j�j
                    //���l�̌ܓ��������́v�Ƃ���B
                    l_strChargeAmt = WEB3StringTypeUtility.formatNumber(Math.rint(l_dblBidAmt.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
                }
            }
        }
        //1.11  <�]�͍S������>
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
        if (!(l_strChargeAmt == null ||
            Long.parseLong(l_strChargeAmt) == 0))
        {
            log.debug("1 get���O�C���`���l��");
            //1.11.1 get���O�C���`���l��
            String l_strLoginChannel = this.getLoginChannel();

            //1.11.2 submit�]�͍S��
            WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strPassword = l_crypt.decrypt(l_request.password);
            long l_lngRemainingPowerRestraint =
                l_srvRegiRegistService.submitRemainingPowerRestraint((WEB3GentradeSubAccount)l_subAccount, l_trader,
                Double.parseDouble(l_strChargeAmt), l_tsPaymentDate, l_request.serviceDiv, l_strLoginChannel,l_strPassword);

            //1.12 submit�T�[�r�X�\���o�^
            if (l_request.chargeId == null)
            {
                l_gentradeSrvRegiApplication =
                    this.submitSrvAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    null, Double.valueOf(l_strChargeAmt),
                    l_tsSystemTimestamp, l_tsPaymentDate, new Long(l_lngRemainingPowerRestraint), l_request.applyKindDiv,
                    l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
            else
            {
                l_gentradeSrvRegiApplication =
                    this.submitSrvAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    new Long(l_request.chargeId), Double.valueOf(l_strChargeAmt),
                    l_tsSystemTimestamp, l_tsPaymentDate, new Long(l_lngRemainingPowerRestraint), l_request.applyKindDiv,
					l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
        }
        else
        {
            //1.12 submit�T�[�r�X�\���o�^
            if (l_request.chargeId == null)
            {
                l_gentradeSrvRegiApplication = this.submitSrvAppliRegist(
                    (WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    null, null,
                    l_tsSystemTimestamp, l_tsPaymentDate, null, l_request.applyKindDiv, l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
            else
            {
                l_gentradeSrvRegiApplication = this.submitSrvAppliRegist(
                    (WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    new Long(l_request.chargeId), null,
                    l_tsSystemTimestamp, l_tsPaymentDate, null, l_request.applyKindDiv, l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
        }
        //U00861
        if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_srvRegiApplicationRequiredService.getStartMailDiv()) &&
            !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()))
        {
            log.debug("1.13 sendMailProcess");
            //1.13 sendMailProcess
            WEB3SrvRegiExecSendMailService l_srvRegiExecSendMailService =
                (WEB3SrvRegiExecSendMailService)Services.getService(WEB3SrvRegiExecSendMailService.class);
            l_srvRegiExecSendMailService.sendMailProcess(l_gentradeSrvRegiApplication);
        }

        //1.14 create���X�|���X
        WEB3SrvRegiApplyCompleteResponse l_response = (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
        l_response.lastUpdatedTimestamp = l_tsSystemTimestamp;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\���o�^)<BR>
     * ���p�\�������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jvalidate�\���o�^�v�Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     * 1.2 <�⑫���̓`�F�b�N><BR>
     * 1) get���I�ݒ�( )="��"�̏ꍇ
     * 1-1) ����.���p����ID=null�̏ꍇ�A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01075<BR>
     * 1-2) ����.���D�z!=null�̏ꍇ�A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01076<BR>
     * 1-3) ����.�\����ʋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
     *�@@�@@"�ʏ�\��"
     *   �@@"�p���\��"
     *�@@�@@"���p�\��"
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00980<BR>
     *
     * 2) get���I�ݒ�( )="�L"�̏ꍇ
     * 2-1) ����.���p����ID!=null�̏ꍇ�A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01077<BR>
     * 2-2) ����.���D�z!=null�̏ꍇ�A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01862<BR>
     * 2-3) ����.�\����ʋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
     *    �@@"�ʏ�\��"
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00980<BR>
     * =========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.3.isMainAccountAppli(�⏕����, WEB3SrvRegiServiceMaster)<BR>
     *         is�ڋq�\���\( )=false�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * =========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.4.2.1 is�萔������<BR>
     *         is�萔������( )=false�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01177<BR>
     * =========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.5.1get�\�����I�敪( )<BR>
     *         �ȉ��̏����ɍ��v�����ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         ������.�\����ʋ敪=="�ʏ�\��"�̏ꍇ<BR>
     *        �@@-get�T�[�r�X�\���o�^( )�̖߂�l!=null�ł���A<BR>
     *         get�\�����I�敪( )="���p"�ȊO�̏ꍇ<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01012<BR>
     * <BR>
     *         ������.�\����ʋ敪=="�p���\��"�̏ꍇ<BR>
     *        �@@-get�T�[�r�X�\���o�^( )�̖߂�l=null�̏ꍇ<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01013<BR>
     *        �@@-get�T�[�r�X�\���o�^( )�̖߂�l!=null�ł���A<BR>
     *         get�\�����I�敪( )="���I�^�{�\��"�ȊO�̏ꍇ<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01014<BR>
     * <BR>
     *         ������.�\����ʋ敪=="���p�\��"�̏ꍇ<BR>
     *          �@@-get�T�[�r�X�\���o�^( )�̖߂�l!=null�̏ꍇ<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01015<BR>
     *
     *         ������.�\����ʋ敪=="�����\��"�̏ꍇ<BR>
     *          �@@-get�T�[�r�X�\���o�^( )�̖߂�l!=null�̏ꍇ<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01178<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.7.1.1.isTrialAppliPossible(String, String, String, String)<BR>
     *         is���p�\���\( )=false�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01016<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.8.1<���򏈗� *5><BR>
     *          <���򏈗� *5><BR>
     *         get�T�[�r�X�\���o�^( )!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01017<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.8.2getSrvLotInfo(String, String, Timestamp, int)<BR>
     *         �T�[�r�X���I���I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01018<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���o�^�v): <BR>
     *         1.8.4<���򏈗� *7><BR>
     *          <���򏈗� *7><BR>
     *          �ȉ��̏����ɍ��v����ꍇ�A��O���X���[����B<BR>
     *          ������.���D�z���Œ���D�z�i*)�ł͂Ȃ��ꍇ<BR>
     *          �܂���<BR>
     *          ������.���D�z mod �擾�����T�[�r�X���I���I�u�W�F�N�g.get���D�P��( )=0�ł͂Ȃ��ꍇ<BR>
     * <BR>
     *         (*) �擾�����T�[�r�X���I���I�u�W�F�N�g.get���p����( )<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01019<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����@@�I�u�W�F�N�g<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_srvUsePeriodId - (���p����ID)<BR>
     * @@param l_biddingPrice - (���D�z)<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@param l_strAppliTpyeDiv - (�\����ʋ敪)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@roseuid 413E611202E8
     */
    private void validateAppliRegist(WEB3GentradeSubAccount l_subAccount, String l_strSrvDiv, Long l_srvUsePeriodId,
        Double l_biddingPrice, Timestamp l_tsAppliDate, String l_strAppliTpyeDiv, String l_strPassword)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateAppliRegist(WEB3GentradeSubAccount, String, Long, Double, Timestamp, String, String)";
        log.entering(STR_METHOD_NAME);
        //1.1  get�T�[�r�X�}�X�^�[
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.1 get�\���v�T�[�r�X
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        String l_strLotDiv = null;
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.1.1. get���I�ݒ�
        l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //1.2 <�⑫���̓`�F�b�N>

        //1) get���I�ݒ�( )="��"�̏ꍇ
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            //1-1) ����.���D�z!=null�̏ꍇ�A��O���X���[����B
            if (l_biddingPrice != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01076,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //1-2) ����.�\����ʋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            if (!WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00980,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        //2) get���I�ݒ�( )="�L"�̏ꍇ
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //2-1) ����.���p����ID!=null�̏ꍇ�A��O���X���[����B
            if (l_srvUsePeriodId != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01077,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //2-2) ����.���D�z==null�̏ꍇ�A��O���X���[����B
            //U00836
            //2-3) ����.�\����ʋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            if (!WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00980,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.3 is�ڋq�\���\
        boolean l_blnAccountAppliPossible =
            l_srvRegiServiceInfoManagement.isAccountAppliPossible(l_subAccount, l_srvRegiServiceMaster);
        if (!l_blnAccountAppliPossible)
        {
            log.debug("is�ڋq�\���\");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                getClass().getName() + STR_METHOD_NAME);
        }

        //�T�[�r�X�}�X�^.���ꏈ���敪 = null�i�ʏ�T�[�r�X�j �ȊO�̏ꍇ
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        if (l_strSpecialProcessDiv != null)
        {
            //�擾�����⏕�����I�u�W�F�N�g
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            //is�V�K�\��(�⏕����, String)
            //[����]
            //�⏕���� = �擾�����⏕�����I�u�W�F�N�g
            //�T�[�r�X�敪=����.�T�[�r�X�敪
            boolean l_blnIsNewApply =
                l_srvRegiServiceInfoManagement.isNewApply(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_strSrvDiv);

            //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
            //[����]
            //�T�[�r�X�}�X�^ = �擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g
            //�،���ЃR�[�h = ����.�⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
            //���X�R�[�h = ����.�⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
            //�����R�[�h = ����.�⏕�����I�u�W�F�N�g.getMainAccount( ).getAccountCode( )
            //�V�K�\���敪 = is�V�K�\��( ) �̖߂�l
            l_srvRegiServiceInfoManagement.validateSpecialApply(
                l_srvRegiServiceMaster,
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode(),
                l_blnIsNewApply);
        }

        //validate�d�q������()
        l_srvRegiServiceInfoManagement.validateBatoAgreement(l_srvRegiServiceMaster);
                
        //1.4  <����.�\����ʋ敪="�����\��"�̏ꍇ>
        if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv))
        {
            log.debug("<����.�\����ʋ敪=�����\���̏ꍇ>");
            //1.4.1  get�񋟌`��
            String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();

            //1.4.2  <get�񋟌`��( )!=null�̏ꍇ>
            if (l_strProvidModel != null)
            {
                log.debug("get�񋟌`��( )!=null�̏ꍇ");
                //1.4.2.1 is�萔������
                boolean l_blnCommCond = l_srvRegiServiceInfoManagement.isCommCond(l_subAccount, l_srvRegiServiceMaster);
                if (!l_blnCommCond)
                {
                    log.debug("!l_blnCommCond");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01177,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.4 get�T�[�r�X�\���o�^
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);

        //1.7 <���򏈗� *1>
        if (l_gentradeSrvRegiApplication != null)
        {
            log.debug("get�T�[�r�X�\���o�^");
            //1.7.1 get�\�����I�敪
            String l_strAppliLotDiv = l_gentradeSrvRegiApplication.getAppliLotDiv();
            //1.7.2 get���������\���敪
            String l_freeSrvDiv = l_gentradeSrvRegiApplication.getFreeSrvDiv();
            
            //������.�\����ʋ敪=="�ʏ�\��"�̏ꍇ -get�T�[�r�X�\���o�^( )�̖߂�l!=null�ł���Aget�\�����I�敪( )="���p"�ȊO�̏ꍇ
            //U00867
            if (WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(l_strAppliTpyeDiv) &&
                WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
                !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_strAppliLotDiv))
            {
                log.debug("BUSINESS_ERROR_01012");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01012,
                    getClass().getName() + STR_METHOD_NAME);
            }
            if (WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_strAppliLotDiv))
            {
                log.debug("BUSINESS_ERROR_01014");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01013,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv)) 
            {
                log.debug("BUSINESS_ERROR_01015");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01015,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.debug("BUSINESS_ERROR_01178");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01178,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        else
        {
            if (WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.debug("BUSINESS_ERROR_01013");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01013,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        long l_lngUseAmt = 0;
        String l_strInvestDiv = null;
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = null;

        //init�����
        double l_dblSalesTax = 0;
        //1.7 <���򏈗� *2>
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)");
            //1.7.1 <���򏈗� *3>
            if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.debug("WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv)");
                //1.7.1.1 is���p�\���\
                boolean l_blnTrialAppliPossible =
                    l_srvRegiServiceInfoManagement.isTrialAppliPossible(l_strInstitutionCode, l_strBranchCode,
                    l_strSrvDiv, l_strAccountCode);
                if(!l_blnTrialAppliPossible)
                {
                    log.debug("is���p�\���\");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01016,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        //1.8 <���򏈗� *4>
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv)");
            //U00867
            //1.8.1 <���򏈗� *5>

            //1.8.2 get�T�[�r�X���I���
            l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);
            if (l_srvRegiServiceLotInfo == null)
            {
                log.debug("l_srvRegiServiceLotInfo == null");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01018,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //U00867
            if (l_gentradeSrvRegiApplication != null)
            {
                long l_lngConsecutiveNumbersA = l_srvRegiServiceLotInfo.getConsecutiveNumbers();

                WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfoB =
                    l_srvRegiServiceInfoManagement.getSrvLotInfo(
                    l_strInstitutionCode, l_strSrvDiv, l_gentradeSrvRegiApplication.getAppliDate(), 0);

                long l_lngConsecutiveNumbersB = l_srvRegiServiceLotInfoB.getConsecutiveNumbers();

                if (l_lngConsecutiveNumbersA == l_lngConsecutiveNumbersB)
                {
                    log.debug("���ꒊ�I���ւ̏d���\���G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01926,
                        getClass().getName() + STR_METHOD_NAME,
                        "���ꒊ�I���ւ̏d���\���G���[�B");
                }
            }

            //1.8.2.1 get�^�p�敪
            l_strInvestDiv = l_srvRegiServiceLotInfo.getInvestDiv();

            //1.8.3 <���򏈗� *6>
            if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv)
                && (l_biddingPrice != null && l_biddingPrice.doubleValue() >= 0))
            {
                log.debug(" 2:�ʏ�^�p�i���I�L-�I�[�N�V�����j");

                //1.8.3.1 calc�����

                //1.8.3.2 get���D�P��
                Long l_getBiddingPrice = l_srvRegiServiceLotInfo.getBiddingPrice();

                //1.8.3.3 get���p����
                l_lngUseAmt = l_srvRegiServiceLotInfo.getUseAmt();

                //1.8.4<���򏈗� *7>
                //WEB3-SRVREGI-A-�e�s-0136
                //�u�i����.���D�z�~�i�P�{�擾�����ŗ��I�u�W�F�N�g.get�ŗ�()�̖߂�l/100�j�j���l�̌ܓ��������́��Œ���D�z(*)�ł͂Ȃ��ꍇ�v
                //�ŗ��I�u�W�F�N�g�𐶐�����B
                WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.CONSUMPTION_TAX,
                    l_srvRegiServiceLotInfo.getPaymentDate());

                if (l_biddingPrice == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                l_dblSalesTax = (Math.rint(l_biddingPrice.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
                //���v�Z�T�[�r�X.calc�����( )�̖߂�l���Œ���D�z�i*)�ł͂Ȃ��ꍇ �܂���
                //������.���D�z mod �擾�����T�[�r�X���I���I�u�W�F�N�g.get���D�P��( )=0�ł͂Ȃ��ꍇ
                long l_lngBiddingPrice = 0;
                if (l_getBiddingPrice != null)
                {
                    log.debug("l_getBiddingPrice != null");
                    l_lngBiddingPrice = l_getBiddingPrice.longValue();
                }
                double l_dblBiddingPrice = 0;
                if (l_biddingPrice != null)
                {
                    log.debug("l_biddingPrice != null");
                    l_dblBiddingPrice = l_biddingPrice.doubleValue();
                }
                if (l_dblSalesTax < l_lngUseAmt ||
                    (l_dblBiddingPrice % l_lngBiddingPrice) != 0)
                {
                    log.debug("BUSINESS_ERROR_01019");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01019,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt = null;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) && l_srvUsePeriodId != null)
        {
            l_srvRegiServiceUsePeriodAmt =
                l_srvRegiServiceMaster.getSrvUseTermAmt(l_srvUsePeriodId.longValue(), false);
        }

        //1.9 <���򏈗� *8>
        if ((WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
            !(WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv) ||
            WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv)) &&
            l_srvRegiServiceUsePeriodAmt != null &&
            l_srvRegiServiceUsePeriodAmt.getUseAmt() > 0)
            ||
            (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv) &&
            (WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(l_strInvestDiv) ||
            WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(l_strInvestDiv)) &&
            l_lngUseAmt > 0)
            ||
            (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv) &&
            WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv) &&
            l_biddingPrice.doubleValue() > 0))
        {
            log.debug("<���򏈗� *8>");
            //1.9.1 get�㗝���͎�
            Trader l_trader = this.getTrader();

            //1.9.2 get���O�C���`���l��
            String l_strLoginChannel = this.getLoginChannel();

            //1.9.4 ��get���I�ݒ�()��"��"�̏ꍇ��
            Date l_datOrderBizDate = null;
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                //1.9.4.1getget������
                l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();
            }

            //1.9.4 validate����]��
            double l_dblUseAmt = 0;
            Timestamp l_ts = null;
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug(" (*-1) get���I�ݒ�( )='��'�̏ꍇ�A�T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p����( )�̖߂�l���Z�b�g����B");
                if (l_srvRegiServiceUsePeriodAmt != null)
                {
                    l_dblUseAmt = l_srvRegiServiceUsePeriodAmt.getUseAmt();
                }
                if (l_datOrderBizDate != null)
                {
                    // �c�Ɠ��v�Z(Timestamp)
                    WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

                    l_ts = l_datBizDate.roll(1);
                }
            }
            else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv) &&
                WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv))
            {
                //WEB3-SRVREGI-A-�e�s-0136
                //�ŗ��I�u�W�F�N�g�𐶐�����B
                WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.CONSUMPTION_TAX,
                    l_srvRegiServiceLotInfo.getPaymentDate());

                //�u���p�����v�ւ̐ݒ�l���A�u�i����.���D�z�~�i�P�{�擾�����ŗ��I�u�W�F�N�g.get�ŗ�()�̖߂�l/100�j�j���l�̌ܓ��������́v
				l_dblUseAmt = (Math.rint(l_biddingPrice.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
            }
            else
            {
                log.debug(" (*-3) ��L�ȊO�̏ꍇ�Aget�T�[�r�X���I���I�u�W�F�N�g.get���p����( )�̖߂�l���Z�b�g����B");
                l_dblUseAmt = l_lngUseAmt;
            }

            if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                log.debug("l_ts = l_srvRegiServiceLotInfo.getPaymentDate();");
                l_ts = l_srvRegiServiceLotInfo.getPaymentDate();
            }

            //WEB3Crypt l_crypt = new WEB3Crypt();
            l_srvRegiRegistService.validateTradingPower(l_subAccount, l_trader, l_dblUseAmt, l_ts, l_strSrvDiv,
                l_strLoginChannel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�T�[�r�X�\���o�^)<BR>
     * �T�[�r�X���p�\���̍X�V�������s���A<BR>
     * �\���o�^ID��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jsubmit�T�[�r�X�\���o�^�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����@@�I�u�W�F�N�g<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_srvUsePeriodId - (���p����ID)<BR>
     * @@param l_useAmt - (���p����)<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_orderId - (����ID)<BR>
     * @@param l_strAppliTypeDiv - (�\����ʋ敪)<BR>
     * @@param l_strSpecialDiv - (����\���敪)<BR>
     * @@param l_strFreeAttributeApplyDiv - (���������\���敪)<BR>
     * @@roseuid 413E611703B3
     */
    private WEB3GentradeSrvRegiApplication submitSrvAppliRegist(WEB3GentradeSubAccount l_subAccount, String l_strSrvDiv,
    					Long l_srvUsePeriodId, Double l_useAmt, Timestamp l_tsAppliDate, Timestamp l_tsPaymentDate, Long l_orderId, String l_strAppliTypeDiv, String l_strSpecialDiv, String l_strFreeAttributeApplyDiv)
        						throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitSrvAppliRegist(WEB3GentradeSubAccount, String, Long, Double, Timestamp, Long, String)";
        log.entering(STR_METHOD_NAME);
        //1.1  get�T�[�r�X�}�X�^�[
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.2 get�T�[�r�X�\���o�^
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                null, WEB3EffectiveDivDef.EFFECTIVE, true);

        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplicationNew = null;

        log.debug("createNew�\���o�^");
        l_gentradeSrvRegiApplicationNew =
            WEB3GentradeSrvRegiApplication.createNewSrvRegiApplication
            (l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

        //1.4 get�\���v�T�[�r�X
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        //1.4.1get���I�ݒ�
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        Timestamp l_tsAppliDateFormat = new Timestamp(WEB3DateUtility.toDay(l_tsAppliDate).getTime());

        //1.9.1 get�㗝���͎�
        Trader l_trader = this.getTrader();

        String l_strTraderCode = null;

        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }

        List l_lisServiceAppliAttributeInfo = null;
        String l_strFreeTargetPeriod = null;

        // is�V�K�\��(�⏕����, String)
        // [����]
        // �⏕���� = �擾�����⏕�����I�u�W�F�N�g
        // �T�[�r�X�敪=����.�T�[�r�X�敪
        boolean l_blnIsNewApply =
            l_srvRegiServiceInfoManagement.isNewApply(
                (WEB3GentradeSubAccount)l_subAccount,
                l_strSrvDiv);

        //1.5 <���򏈗� *1>
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)");

            //1.5.3 <���򏈗� *2>
            if (!WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTypeDiv))
            {

                //get�T�[�r�X�\���������(String, String, String, String, String)
                //�،���ЃR�[�h�Fget�T�[�r�X�}�X�^�[�ꗗ( )�̖߂�l�̃T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�،���ЃR�[�h( )
                //���X�R�[�h�F�擾�����⏕�����I�u�W�F�N�g.getMainAccount( ).getBranch( ).getBranchCode( )
                //�����R�[�h=�擾�����⏕�����I�u�W�F�N�g.getMainAccount( ).getAccountCode( )
                //�T�[�r�X�敪�Fget�T�[�r�X�}�X�^�[�ꗗ( )�̖߂�l�̃T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                //�A�b�v���[�h�敪=null
                l_lisServiceAppliAttributeInfo =
                    l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                        l_srvRegiServiceMaster.getInstitutionCode(),
                        l_subAccount.getMainAccount().getBranch().getBranchCode(),
                        l_subAccount.getMainAccount().getAccountCode(),
                        l_srvRegiServiceMaster.getSrvDiv(),
                        null);

                // get�T�[�r�X�\���������() != null �̏ꍇ
                if (l_lisServiceAppliAttributeInfo != null)
                {
                    //get�����Ώۊ���( )
                    l_strFreeTargetPeriod =
                        l_srvRegiApplicationRequiredService.getFreeTargetPeriod();
                }

				//���N�G�X�g�f�[�^.���������\���敪 = '1'�@@�̏ꍇ ���́A
				//����.�\����ʋ敪 == "���p�\��" �̏ꍇ
                long l_chargeId = 0;
				if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(l_strFreeAttributeApplyDiv) || 
					(WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTypeDiv))) 
                {
                	l_chargeId = Long.parseLong("0");                	
                }
                else
                {
                	l_chargeId = l_srvUsePeriodId.longValue();
                }
                
                log.debug("calc�K�p�I����");
                //1.5.3.1 calc�K�p�I����
                Timestamp l_tsAppliEndDate =
                    l_srvRegiRegistService.calcAppliEndDate(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                    l_tsAppliDate, l_chargeId, l_strSpecialDiv, l_strFreeAttributeApplyDiv);

                //1.5.3.2 <�{�\���E�p���\���̃v���p�e�B�E�Z�b�g>
                //���K�p�J�n��=����.�\����
                l_gentradeSrvRegiApplicationNew.setAppliStartDate(l_tsAppliDateFormat);
                //���K�p�I����=this.calc�K�p�I����( )�̖߂�l
                l_gentradeSrvRegiApplicationNew.setAppliEndDate(new Timestamp(WEB3DateUtility.toDay(l_tsAppliEndDate).getTime()));
                
                //���\����=����.�\����
                l_gentradeSrvRegiApplicationNew.setAppliDate(l_tsAppliDate);
                //���o�^�敪=(*1)
                // (*1-1) ����.���p����=null�̏ꍇ "����"���Z�b�g����B
                if (l_useAmt == null)
                {
                    log.debug("����.���p����=null");
                    l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.FREE);
                }
                else
                {
                    log.debug("����.���p���� != null");
                    l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.CHARGE);
                }

                //  �����p����=����.���p����
                l_gentradeSrvRegiApplicationNew.setUseAmt(l_useAmt);
                //  ���o����=(*2)
                //      (*2-1) ����.���p����=null�̏ꍇ null���Z�b�g����B
                if (l_useAmt == null)
                {
                    log.debug("����.���p����=null");
                    l_gentradeSrvRegiApplicationNew.setPaymentDate(null);
                }
                //(*2-2) ����.���p����!=null�̏ꍇ
                //  this.get������( )�̖߂�l���Z�b�g����B
                else
                {
                    //Model No.120
                    log.debug("����.���p����!=null");
                    //�X�c����̎w��
                    l_gentradeSrvRegiApplicationNew.setPaymentDate(l_tsPaymentDate);
                }
                //���������I���������=null
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(null);

                //set�ŏI�X�V��
                if (l_trader != null)
                {
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strTraderCode);
                }
                else
                {
					//��Q�Ή� NO_2051
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strAccountCode.substring(0,6));
                }

                //�����������\���敪=
                //get�T�[�r�X�\���������() != null ������ get�����Ώۊ���() != null�̏ꍇ
                // '1'�F���������\�����Z�b�g�B
                if (l_lisServiceAppliAttributeInfo != null && l_strFreeTargetPeriod != null)
                {
                    l_gentradeSrvRegiApplicationNew.setFreeSrvDiv(
                        WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY);
                }
                
                //������ID�A�\�����I�敪�̃v���p�e�B�Z�b�g
                //�\����ʋ敪 = "���p�\��"�̏ꍇ�ƁA"���p�\��"�ȊO�̏ꍇ
                if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTypeDiv))
                {
                	//������ID=null
                	l_gentradeSrvRegiApplicationNew.setOrderId(null);
                    //���\�����I�敪="���p"
                    l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.TRIAL_APPLI);
                }
                else
                {
                    //������ID=����.����ID
                    l_gentradeSrvRegiApplicationNew.setOrderId(l_orderId);
                    //���\�����I�敪="�{�\��"
                    l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI);
                }


                //get���I�ݒ�( )="��"�̏ꍇ�A���A�T�[�r�X�}�X�^�I�u�W�F�N�g.���ꏈ���敪 = 1�i�O���A�g�T�[�r�X�j �̏ꍇ
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)
                    && WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    // submit�O���A�g���(String, String, String, String, Timestamp, Timestamp, boolean)
                    // �O���A�g���Ǘ��e�[�u����UPDATE���s���B
                    // [����]
                    // �،���ЃR�[�h=�擾�����⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
                    //���X�R�[�h=�擾�����⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
                    //�����R�[�h=�擾�����⏕�����I�u�W�F�N�g.getMainAccoutn( ).getAccountCode( )
                    //�T�[�r�X�敪 = ����.�T�[�r�X�敪
                    //�K�p�J�n�� = ���ݓ��t
                    //�K�p�I���� = calc�K�p�I����( )�̖߂�l
                    //�V�K�\���敪 = is�V�K�\��( ) �̖߂�l
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_subAccount.getWeb3GenBranch().getBranchCode(),
                        l_subAccount.getMainAccount().getAccountCode(),
                        l_strSrvDiv,
                        GtlUtils.getTradingSystem().getSystemTimestamp(),
                        l_tsAppliEndDate,
                        l_blnIsNewApply);
                }
            }

            //1.6.3 <���򏈗� *4>
            if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTypeDiv))
            {
                log.debug("�����\���̃v���p�e�B�E�Z�b�g");
                //1.6.3.1  <�����\���̃v���p�e�B�E�Z�b�g>
                //���K�p�J�n��=����.�\����
                l_gentradeSrvRegiApplicationNew.setAppliStartDate(l_tsAppliDateFormat);
                
				log.debug("�K�p�I����");
                //���K�p�I����=����.�\�����ɁA�P�����𑫂������t
				Calendar l_caleAppliStartDate = Calendar.getInstance();
				Calendar l_caleNewAppliEndDate = Calendar.getInstance();
				
				//�K�p�I�����̌v�Z
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String l_stAppliEndDate = formatter.format(l_tsAppliDateFormat);
				Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
				l_caleAppliStartDate.setTime(l_datAppliEndDate);
				l_caleNewAppliEndDate.setTime(l_datAppliEndDate);
				int l_maxDay = 0;
				boolean l_maxDayFlag = false;
				
				//�K�p�I�����i�\�������ꃖ����j
				l_caleNewAppliEndDate.add(Calendar.MONTH, 1);
				log.debug("�y���������O�zl_caleNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
				
				if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
				{
					l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
					l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
					l_maxDayFlag = true;
				}
				
				//�ꃖ����̑O�������߂�
				if (!l_maxDayFlag)
				{
					l_caleNewAppliEndDate.add(Calendar.DATE, -1);
				}
                
				log.debug("�y����������zl_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
				Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();
				l_gentradeSrvRegiApplicationNew.setAppliEndDate(new Timestamp(l_datNewAppliEndDate.getTime()));                
                
                //������ID=null
                l_gentradeSrvRegiApplicationNew.setOrderId(null);
                //���\����=����.�\����
                l_gentradeSrvRegiApplicationNew.setAppliDate(l_tsAppliDate);
                //���o�^�敪="����"
                l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.FREE);
                //���\�����I�敪="�{�\��"
                l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI);
                //�����p����=null
                l_gentradeSrvRegiApplicationNew.setUseAmt(null);
                //���o����=null
                l_gentradeSrvRegiApplicationNew.setPaymentDate(null);
                //���������I���������=null
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(null);

                //set�ŏI�X�V��
                if (l_trader != null)
                {
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strTraderCode);
                }
                else
                {
					//��Q�Ή� NO_2051
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strAccountCode.substring(0,6));
                }
                
                //get���I�ݒ�( )="��"�̏ꍇ�A���A�T�[�r�X�}�X�^�I�u�W�F�N�g.���ꏈ���敪 = 1�i�O���A�g�T�[�r�X�j �̏ꍇ
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)
                    && WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    // submit�O���A�g���(String, String, String, String, Timestamp, Timestamp, boolean)
                    // �O���A�g���Ǘ��e�[�u����UPDATE���s���B
                    // [����]
                    // �،���ЃR�[�h=�擾�����⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
                    //���X�R�[�h=�擾�����⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
                    //�����R�[�h=�擾�����⏕�����I�u�W�F�N�g.getMainAccoutn( ).getAccountCode( )
                    //�T�[�r�X�敪 = ����.�T�[�r�X�敪
                    //�K�p�J�n�� = ���ݓ��t
                    //�K�p�I���� = calc�K�p�I����( )�̖߂�l
                    //�V�K�\���敪 = is�V�K�\��( ) �̖߂�l
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_subAccount.getWeb3GenBranch().getBranchCode(),
                        l_subAccount.getMainAccount().getAccountCode(),
                        l_strSrvDiv,
                        GtlUtils.getTradingSystem().getSystemTimestamp(),
                        new Timestamp(l_datNewAppliEndDate.getTime()),
                        l_blnIsNewApply);
                }
            }
			//U00877
			//��Q�Ή� NO_2047
        }

        //  ���N�G�X�g�f�[�^.���������\���敪 = '1'�@@�̏ꍇ
		if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(l_strFreeAttributeApplyDiv))
        {
            // update�ڋq�T�[�r�X�\������

            Integer l_intUpdateCount = this.updateSrvApplyAttribute(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strSrvDiv);

            // update�Ώۂ̃��R�[�h���Ȃ������ꍇ�A��O���X���[����B
            if (l_intUpdateCount == null)
            {
                log.debug("���R�[�h�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strBranchCode + "." + l_strSrvDiv + "." + l_strAccountCode);
            }
        }

        //1.7 <���򏈗� *5>
        if(WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("1.6.1 get�T�[�r�X���I���");
            //1.6.1 get�T�[�r�X���I���
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);

            //1.6.2 <���I�L�T�[�r�X�̃v���p�e�B�E�Z�b�g>
            //���K�p�J�n��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�J�n��( )
            l_gentradeSrvRegiApplicationNew.setAppliStartDate(l_srvRegiServiceLotInfo.getAppliStartDate());
            //���K�p�I����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�I����( )
            l_gentradeSrvRegiApplicationNew.setAppliEndDate(l_srvRegiServiceLotInfo.getAppliEndDate());
            //������ID=����.����ID
            l_gentradeSrvRegiApplicationNew.setOrderId(l_orderId);
            //���\����=����.�\����
            l_gentradeSrvRegiApplicationNew.setAppliDate(l_tsAppliDate);
            //���o�^�敪=(*1)
            //(*1-1) ����.���p����=null�̏ꍇ      "����"���Z�b�g����B
            if (l_useAmt == null)
            {
                log.debug("���p����=null�̏ꍇ");
                l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.FREE);
            }
            //(*1-2) ����.���p����!=null�̏ꍇ      "�L��"���Z�b�g����B
            else
            {
                log.debug("����.���p����!=null�̏ꍇ");
                l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.CHARGE);
            }
            //���\�����I�敪=(*2)
            //(*2-1) �擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪="���������I"�̏ꍇ  "�������I"���Z�b�g����B
            //���������I���������=(*4)
            if (WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(l_srvRegiServiceLotInfo.getInvestDiv()))
            {
                log.debug("�������I���Z�b�g����");
                l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.AUTO_ELECTION);
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(l_srvRegiServiceLotInfo.getAppliDateTo());
            }
            //(*2-2) �擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪="���������I"�ȊO�̏ꍇ "�\��"���Z�b�g����B
            else
            {
                log.debug("�\�����Z�b�g����");
                l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.APPLI);
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(null);
            }
            //�����p����=����.���p�������Z�b�g����B
            l_gentradeSrvRegiApplicationNew.setUseAmt(l_useAmt);
            //���o����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�o����( )
            l_gentradeSrvRegiApplicationNew.setPaymentDate(l_srvRegiServiceLotInfo.getPaymentDate());

            //set�ŏI�X�V��
            if (l_trader != null)
            {
                l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strTraderCode);
            }
            else
            {
				//��Q�Ή� NO_2051
                l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strAccountCode.substring(0,6));
            }
        }
 
  		//��Q�Ή� NO_2047		  		       
        //is�V�K�\���̖߂�l��false�̏ꍇ�A�ȉ��葱�������{�B
		if (!l_blnIsNewApply)
		{
			log.debug("<�����s�̖���������>");
			//1.8.1 set�L���敪
			l_gentradeSrvRegiApplication.setEffectiveDiv(WEB3EffectiveDivDef.INEFFECTIVE);

            //[WEB3-SRVREGI-A-FT-0166�̍Ď���]�Ƃ���QA�ɂ��C��
			//1.8.2 set�ŏI�X�V��
			if (l_trader != null)
			{
				l_gentradeSrvRegiApplication.setLastUpdater(l_strTraderCode);
			}
			else
			{
				//��Q�Ή� NO_2051
				l_gentradeSrvRegiApplication.setLastUpdater(l_strAccountCode.substring(0,6));
			}

			//1.8.2 save�T�[�r�X�\���o�^
			l_gentradeSrvRegiApplication.saveSrvRegiApplication();
		}

        //1.7 saveNew�T�[�r�X�\���o�^
        log.debug("saveNew�T�[�r�X�\���o�^");
        l_gentradeSrvRegiApplicationNew.saveNewSrvRegiApplication();

        //U00877
        //1.8 <�����s�̖���������>


        //1.9 get�����\���敪
        String l_strInitializeAppliDiv =
            l_srvRegiRegistService.getInitializeAppliDiv(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

        //get���O�C���`���l��
        String l_strLoginChannel = this.getLoginChannel();

        try
        {
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strInitializeAppliDiv))
            {
                //1.10 �u�\�������Ǘ��e�[�u���v�o�^����
                SrvRegiHistoryParams l_srvRegiHistoryParams = new SrvRegiHistoryParams();

                OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountManager = l_finApp.getAccountManager();

                //�،���ЃR�[�h
                String l_strInstitutionId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID);
                String l_strInstitutionCodeSession =
                    l_accountManager.getInstitution(Long.parseLong(l_strInstitutionId)).getInstitutionCode();
                l_srvRegiHistoryParams.setInstitutionCode(l_strInstitutionCodeSession);

                //���X�R�[�h
                String l_strBranchId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.BRANCH_ID);
                String l_strBranchCodeSession = l_accountManager.getBranch(Long.parseLong(l_strBranchId)).getBranchCode(); //NotFoundException
                l_srvRegiHistoryParams.setBranchCode(l_strBranchCodeSession);

                //�T�[�r�X�敪
                l_srvRegiHistoryParams.setSrvDiv(l_strSrvDiv);

                //�����R�[�h
                l_srvRegiHistoryParams.setAccountCode(l_strAccountCode);

                //�\����
                Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_srvRegiHistoryParams.setRegistDate(l_tsSystemTimestamp);

                //�\���o�H�敪
                //U00793
                if (WEB3ChannelDef.CALL_CENTER.equals(l_strLoginChannel))
                {
                    l_srvRegiHistoryParams.setOrderRootDiv(WEB3OrderRootDivDef.CALLCENTER);
                }
                else
                {
                    l_srvRegiHistoryParams.setOrderRootDiv(WEB3OrderRootDivDef.PC);
                }

                //�X�V�҃R�[�h
                if (l_trader != null)
                {
                    l_srvRegiHistoryParams.setLastUpdater(l_strTraderCode);
                }
                else
                {
                    l_srvRegiHistoryParams.setLastUpdater(l_strAccountCode);
                }

                l_srvRegiHistoryParams.setCreatedTimestamp(l_tsSystemTimestamp);
                l_srvRegiHistoryParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                l_queryProcesser.doInsertQuery(l_srvRegiHistoryParams);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        

        log.exiting(STR_METHOD_NAME);
        return l_gentradeSrvRegiApplicationNew;
    }

    /**
     * ���͂������t�Ɏw�肵���������v���X���A�ԋp���܂��B
     *
     * @@param l_dat      ���t
     * @@param l_intMonth ����
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    private Date addMonth(Date l_dat, int l_intMonth)
    {
        String STR_METHOD_NAME = " addMonth(Date, int)";
        log.entering(STR_METHOD_NAME);

        if (l_dat == null)
        {
            return null;
        }

        Calendar l_cal = new GregorianCalendar();
        l_cal.setTime(l_dat);

        l_cal.add(Calendar.MONTH, l_intMonth);

        log.exiting(STR_METHOD_NAME);
        return l_cal.getTime();
    }

    /**
     * ���͂������t�Ɏw�肵���N�����v���X���A�ԋp���܂��B
     *
     * @@param l_dat      ���t
     * @@param l_intYear �N��
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    private Date addYear(Date l_dat, int l_intYear)
    {
        String STR_METHOD_NAME = " addYear(Date, int)";
        log.entering(STR_METHOD_NAME);
        if (l_dat == null)
        {
            return null;
        }

        Calendar l_cal = new GregorianCalendar();
        l_cal.setTime(l_dat);

        l_cal.add(Calendar.YEAR, l_intYear);

        log.exiting(STR_METHOD_NAME);
        return l_cal.getTime();
    }
    
    /**
     * (update�ڋq�T�[�r�X�\������)<BR>
     * �T�[�r�X�\�������e�[�u����UPDATE���s���B<BR>
     * <BR>
     * �P�j �T�[�r�X�\�������e�[�u����UPDATE<BR>
     * �@@�P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ� <BR>
     *�@@�@@�@@�@@�@@Object[0]�i�����j�،���ЃR�[�h  <BR>
     *�@@�@@�@@�@@�@@Object[1]�i�����j���X�R�[�h  <BR>
     *�@@�@@�@@�@@�@@Object[2]�i�����j�����R�[�h <BR>
     *�@@�@@�@@�@@�@@Object[3]�i�����j�T�[�r�X�敪<BR>
     * <BR>
     * �@@�Q�j�T�[�r�X�\�������e�[�u����UPDATE���s���B<BR>
     *�@@�@@�@@�@@QueryProcessor.doUpdateQuery()���\�b�h���R�[������B<BR>
     * <BR>
     *�@@�@@�@@ [doUpdateQuery()�ɃZ�b�g����p�����[�^] <BR>
     *�@@�@@�@@�@@arg0�F  �T�[�r�X�\�������e�[�u��RowType <BR>
     *�@@�@@�@@�@@arg1�F  "institution_code=?  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@and branch_code=?  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@and account_code=?  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@and srv_div=?  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@and (proc_div = '0' or proc_div is null)"  <BR>
     *�@@�@@�@@�@@arg2�F  �P�j�ō쐬����Object�z�� <BR>
     * <BR>
     * �R�j �Q�j�̖߂�l > 0 �̏ꍇ�A�Q�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * �S�j �Q�j�̖߂�l = 0 �̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@return Integer
     * @@throws WEB3BaseException
     */
    private Integer updateSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSrvApplyAttribute(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@Object�z��𐶐����A�ȉ���v�f�ɐݒ�
        Object[] l_bindVars = new Object[4];

        // Object[0]�i�����j�،���ЃR�[�h
        l_bindVars[0] = l_strInstitutionCode;

        // Object[1]�i�����j���X�R�[�h
        l_bindVars[1] = l_strBranchCode;

        // Object[2]�i�����j�����R�[�h
        l_bindVars[2] = l_strMainAccountCode;

        // Object[3]�i�����j�T�[�r�X�敪
        l_bindVars[3] = l_strServiceDiv;

        //�Q�j�T�[�r�X�\�������e�[�u����UPDATE���s��

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and account_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and (proc_div = '0' or proc_div is null)");

        Map l_mapUpdateChanes = new HashMap();

        // �X�V�҃R�[�h���X�V����

        Trader l_trader = this.getTrader();

        // �R�[���Z���^�[����̓��͂̏ꍇ
        if (l_trader != null)
        {
            l_mapUpdateChanes.put("last_updater", l_trader.getTraderCode());
        }

        // �ڋq���͂̏ꍇ
        else
        {
            l_mapUpdateChanes.put("last_updater", l_strMainAccountCode.substring(0, 6));
        }
        // �X�V���t���X�V����
        l_mapUpdateChanes.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

        // �����敪���X�V����
        l_mapUpdateChanes.put("proc_div", WEB3SrvAppliAttributeProcDivDef.PROCESSED);

        int l_intCount = 0;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_intCount = l_queryProcessor.doUpdateAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_sbWhere.toString(),
                l_bindVars,
                l_mapUpdateChanes);
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
        if (l_intCount == 0)
        {
            return null;
        }
        return new Integer(l_intCount);
    }
}

@
