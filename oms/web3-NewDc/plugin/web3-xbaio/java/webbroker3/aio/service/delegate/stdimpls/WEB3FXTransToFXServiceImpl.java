head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�փT�[�r�XImpl(WEB3FXTransToFXServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/21 ���z (���u) �V�K�쐬   
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��460�A484
                 : 2006/04/27 ����(���u) �d�l�ύX NO.536�ANO.541�ANO.544�ANO.548
                 : 2006/06/05 ��؁iSCS�j �d�l�ύX No.590�EDB�X�V�d�l 091
                 : 2006/07/12 ������ (���u) �d�l�ύX�E���f��No.600
                 : 2006/08/01 ���(SCS)�@@���f��No.609�Ή�
                 : 2006/08/23 ���(SCS)�@@���f��No.630�ANO.631�Ή�
                 : 2006/08/28 ���(SCS)�@@���f��No.632�EDB�X�V�d�l 108�Ή�
                 : 2006/10/12 �����q (���u) �d�l�ύX�E���f��No.666
                 : 2006/11/08 ���(SCS)�@@���f��No.685�Ή�
Revesion History : 2007/07/12 ���^�](���u) �d�l�ύX���f��No.730
Revesion History : 2008/04/08 ���g (���u) �d�l�ύX�E���f��No.834,No.835,No.836,No.844
Revesion History : 2008/04/23 ���u�� (���u) �d�l�ύX���f��.845
Revesion History : 2008/04/28 ���u�� (���u) �d�l�ύX���f��.847
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��No.859,No.869
                 : 2008/05/23 �O�� (SCS) 
Revesion History : 2008/06/18 �đo�g (���u) �d�l�ύX�E���f��No901
Revesion History : 2008/09/09 ���u�� (���u) �d�l�ύX�E���f��No974,975,984,985,1001
Revesion History : 2008/10/02 �|�� (SCS) �d�l�ύX�E���f��1047
Revesion History : 2008/10/07 �g�C�� (���u) �d�l�ύX�E���f��No997�A1042�A1063�A1064�A1067
Revesion History : 2008/11/19 SCS�哈 �d�l�ύX���f��.1086
Revesion History : 2009/03/15 �đo�g (���u) �d�l�ύX�E���f��No1113�A1145�A1149
Revesion History : 2009/04/20 �Ԑi (���u) �d�l�ύX�E���f��1163
Revesion History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1198 1216 1220 1234
Revesion History : 2009/10/29 �����F (���u) �d�l�ύX�E���f��1249 1250 1251
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrder;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXTransConnection;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXConnCommonService;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTransferOrderUpdateInterceptor;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioCashInOutAmountDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransToFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransToFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransToFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransToFXConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioSoapConnectDivDef;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3FxDeliveryDateInsertCheckDef;
import webbroker3.common.define.WEB3FxSystemCodeDef;
import webbroker3.common.define.WEB3FxTransferMasterRemarkCodeDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftSoapResultCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3SoapConnectDivDef;
import webbroker3.common.define.WEB3SoapResultCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcDao;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�ւ̐U�փT�[�r�XImpl) <BR>
 * FX�ւ̐U�փT�[�r�X�����N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransToFXService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3FXTransToFXServiceImpl()
    {
    }

    /**
     * FX�ւ̐U�փT�[�r�X�������s���B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * �Evalidate����() <BR>
     * �Estart����() <BR>
     * �Esubmit����() <BR>
     * �Esubmit����()�iSOAP�ڑ��j
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 41C7B2080071
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B 
        // validate����() 
        // start����() 
        // submit����() 
        // submit����()�iSOAP�ڑ��j
        if (l_request instanceof WEB3FXTransToFXConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3FXTransToFXConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3FXTransToFXCompleteSoapRequest)
        {
            l_response =
                submitOrder((WEB3FXTransToFXCompleteSoapRequest) l_request);
        }
        else if (l_request instanceof WEB3FXTransToFXAskingRequest)
        {
            l_response =
                startOrder((WEB3FXTransToFXAskingRequest)l_request);
        }
        else if (l_request instanceof WEB3FXTransToFXCompleteRequest)
        {
            l_response =
                submitOrder((WEB3FXTransToFXCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (validate����) <BR>
     * �U�֒����̔����R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�ւ̐U�ցjvalidate�����v�Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�ւ̐U�� �v<BR>
     * �iFX�ւ̐U�ցjvalidate���� )<BR>
     * 1.13 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00761 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXConfirmResponse
     * @@roseuid 41C7B2080090
     */
    protected WEB3FXTransToFXConfirmResponse validateOrder(
        WEB3FXTransToFXConfirmRequest l_request)
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateOrder(WEB3FXTransToFXConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 get��Е�FX�V�X�e������(String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        //(1)produce FX�f�[�^����T�[�r�X 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        CompFxConditionParams l_params;
        try
        {
            l_params =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get��Е�FX�V�X�e������", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //�U�֋敪 = 1�F�o��
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_params.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        //1.5 validate�O���V�X�e����t�\(String)
        //[�����̐ݒ�] 
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_orderManager.validateOtherSystemAcceptPossible(l_params.getFxSystemCode());

        //validate�U�։\(SubAccount, ��Е�FX�V�X�e������Params)
        //[�����̐ݒ�]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��Е�FX�V�X�e������Params�F�@@�@@get��Еʂe�w�V�X�e������()�̖߂�l
        l_dataControlService.validateChangePoss(
            l_subAccount,
            l_params);

        //1.7 get������()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get��n��(Date, �⏕����, String)
        //[�����̐ݒ�]
        //�������F�@@get������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^().��n���ݒ�敪
        Date l_datDeliveryDate = l_dataControlService.getDeliveryDate(
            l_datOrderBizDate,
            l_subAccount,
            l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9. validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
        l_orderManager.validateTransferPossibleCount(
            l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);

        //1.10. ���iID�i����ID�j���擾����B 
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l 
        long l_lngProductId = 
            l_orderManager.getProductId(l_subAccount.getInstitution());        
        
        //1.11. ���o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF�@@null 
        //������ʁFFX�U�֏����}�X�^.�������
        //�U�փ^�C�v�F�@@2�i�o���j 
        //���iID�F�@@get���iID()�̖߂�l 
        //���z�F�@@���N�G�X�g�f�[�^.�U�֋��z 
        //�L�q�F�@@null 
        //�U�֗\����F�@@get��n��()�̖߂�l
        //���ϋ@@��ID�F�@@null 
        //����ID�F�@@null
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null, 
                l_fxTransferMasterParams.getOrderType(),
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.transferAmount), 
                null, 
                l_datDeliveryDate, 
                null, 
                null);
        
        //1.12. FX�U�֒����X�V�C���^�Z�v�^�𐶐�����B 
        //[�����̐ݒ�] 
        //���o���������e�F�@@���o���������e  
        WEB3FXTransferOrderUpdateInterceptor l_orderUpdateInterceptor =
            new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.13.(*1)�v���p�e�B�Z�b�g 
        //�������Fget������()�̖߂�l
        l_orderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);

        //��n���Fget��n��()�̖߂�l
        l_orderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
        
        //�������e�C���^�Z�v�^�̔z��
        WEB3FXTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_orderUpdateInterceptor};
            
        //�������e�̔z��
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec}; 
        
        //1.14. validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
        //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F FX�U�֒����X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        //�������e�F ���o���������e��v�f�Ƃ����z�� 
        //������ʁF FX�U�֏����}�X�^.�������
        //�]�͍X�V�t���O�F false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
        	l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                l_fxTransferMasterParams.getOrderType(),
                false);
        
        //����]�͌���.get����\�z()�̖߂�l
        double l_dblTradingPower = l_powerResult.getTradingPower();
        
		//�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
		if (l_powerResult.isResultFlg() == false)
		{
			log.debug("�U�֋��z���\�z�𒴂��Ă��܂�"); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00761,
				this.getClass().getName() + "." + l_strMethodName,
				"�U�֋��z���\�z�𒴂��Ă��܂�");     
		}
        
        //1.15. createResponse()
        WEB3FXTransToFXConfirmResponse l_response = 
            (WEB3FXTransToFXConfirmResponse)l_request.createResponse();
            
        //1.16. (*2)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�U�։\�z = ����]�͌���.get����\�z()�̖߂�l
        //��n���Fget��n��()�̖߂�l
        l_response.transferableAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblTradingPower);
        l_response.deliveryDate = l_datDeliveryDate;
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (start����) <BR>
     * �U�֒����̈˗��E�o�^�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�ւ̐U�ցjstart�����v�Q�ƁB <BR>
     * ======================================================== <BR>
     * 1.16 �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00761 <BR>
     * <BR>
     * ==========================================================
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXAskingResponse
     * @@roseuid 41C7B20800AF
     */
    protected WEB3FXTransToFXAskingResponse startOrder(
        WEB3FXTransToFXAskingRequest l_request)
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "startOrder(WEB3FXTransToFXAskingRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get�⏕����(SubAccountTypeEnum)
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 get��Е�FX�V�X�e������(String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        //(1)produce FX�f�[�^����T�[�r�X 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        CompFxConditionParams l_comConditionParams;
        try
        {
            l_comConditionParams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get��Е�FX�V�X�e������", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //�U�֋敪 = 1�F�o��
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_comConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        //1.5 validate�O���V�X�e����t�\(String)
        //[�����̐ݒ�] 
        //�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        l_orderManager.validateOtherSystemAcceptPossible(l_comConditionParams.getFxSystemCode());

        //validate�U�։\(SubAccount, ��Е�FX�V�X�e������Params)
        //[�����̐ݒ�]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��Е�FX�V�X�e������Params�F�@@�@@get��Еʂe�w�V�X�e������()�̖߂�l
        l_dataControlService.validateChangePoss(
            l_subAccount,
            l_comConditionParams);

        //1.7 get������()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //�@@�������̗��c�Ɠ�
        WEB3GentradeBizDate l_gentradeBizDate = 
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));            
        Date l_datNextDate = l_gentradeBizDate.roll(1); 

        //get��n��(Date, �⏕����, String)
        //[����]
        //�������F�@@get������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^().��n���ݒ�敪
        Date l_datDeliveryDate =
            l_dataControlService.getDeliveryDate(
                l_datOrderBizDate,
                l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9 validate�U�։\��(SubAccount, Date, OrderCategEnum)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        //�����J�e�S���F�@@15�i�ב֕ۏ؋��U�ցj
        l_orderManager.validateTransferPossibleCount(l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
        
        //1.10 get�㗝���͎�()
        Trader l_trader = this.getTrader();
        
        //1.11 validate����p�X���[�h(Trader, SubAccount, String)
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        //(1)
        WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
        //(2)
        l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        
        //1.12 get�V�K���ʃR�[�h(String, String, ProductTypeEnum)
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //�����^�C�v�F 5�i�����j
        //(1)produce �������ʃR�[�h�̔ԃT�[�r�X 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber1 = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
                
        //1.13 get���iID(Institution)
        //[����] 
        //�،���ЁF �⏕����.get����X().getInstitution()�̖߂�l
        long l_lngProductId = 
            l_orderManager.getProductId(l_subAccount.getInstitution());
        
        WEB3GentradeBranch l_genBranch = 
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();        

        //������ʁF FX�U�֏����}�X�^.�������
        OrderTypeEnum l_orderTypeEnum = l_fxTransferMasterParams.getOrderType();

        //���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
        // double, String, Date, String, Long, String, String)
        //[�����̐ݒ�] 
        // �㗝���͎ҁF�@@get�㗝���͎�()�̖߂�l 
        // ������ʁF FX�U�֏����}�X�^.�������
        // �U�փ^�C�v�F�@@2�i�o���j 
        // ���iID�F�@@get���iID()�̖߂�l 
        // ���z�F�@@���N�G�X�g�f�[�^.�U�֋��z 
        // �L�q�F null
        // �U�֗\����F�@@get��n��()�̖߂�l 
        // ���ϋ@@��ID�F�@@null 
        // ����ID�F�@@null
        // �E�v�R�[�h�F FX�U�֏����}�X�^.�E�v�R�[�h
        // �E�v���F�@@FX�U�֏����}�X�^.�E�v��
        WEB3AioNewOrderSpec l_aioWEB3AioNewOrderSpec1 =
            new WEB3AioNewOrderSpec(
                l_trader,
                l_orderTypeEnum,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.transferAmount),
                null,
                l_datDeliveryDate,
                null,
                null,
                l_fxTransferMasterParams.getRemarkCode(),
                l_fxTransferMasterParams.getRemarkName());

        //1.15 FX�U�֒����X�V�C���^�Z�v�^(���o���������e)
        //[�����̐ݒ�] 
        //���o���������e�F���o���������e�i�U�֒����@@�j
        WEB3FXTransferOrderUpdateInterceptor l_interceptor1 =
            new WEB3FXTransferOrderUpdateInterceptor(l_aioWEB3AioNewOrderSpec1);
            
        //1.16 (*)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
        //(1)�������Fget������()�̖߂�l
        l_interceptor1.setOrderBizDate(l_datOrderBizDate);
        
        //(2)��n��:get������()�̖߂�l
        l_interceptor1.setDeliveryDate(l_datDeliveryDate);

        //(3)���ʃR�[�h�Fget�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�@@�j
        l_interceptor1.setOrderRequestNumber(l_strNewNumber1);
        
        //(4)MQ�X�e�[�^�X�F0(�����M)
        l_interceptor1.setMQStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        
        //�i�⏕����.getMainAccount().is�M�p�����J�݁i�ٍϋ敪�i���h�w��Ȃ��h�j�̖߂�l��true�j  
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        WEB3FXTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_interceptor1};
        
        //�������e�̔z��
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioWEB3AioNewOrderSpec1}; 
    
        //1.17 validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
        // �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean) 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������e�C���^�Z�v�^�F FX�U�֒����X�V�C���^�Z�v�^��v�f�Ƃ����z�� 
        //�������e�F ���o���������e��v�f�Ƃ����z�� 
        //������ʁF FX�U�֏����}�X�^.�������
        //�]�͍X�V�t���O�F false 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tPTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                l_orderTypeEnum, 
                false);
        
		//�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
		if (l_powerResult.isResultFlg() == false)
		{
			log.debug("�U�֋��z���\�z�𒴂��Ă��܂�"); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00761,
				this.getClass().getName() + "." + l_strMethodName,
				"�U�֋��z���\�z�𒴂��Ă��܂�");     
		}

        double l_dblTransferAmount = 0;
        SubAccount l_subAccount2 = null;
        //�M�p�����J�ݍς̏ꍇ�A���{�B
        //  �⏕����.getMainAccount().is�M�p�����J�݁i�ٍϋ敪�i=�h�w��Ȃ��h�j�̖߂�l == true
        //  and get������()�̖߂�l = get��n��()�̖߂�l
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)
            && WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0)
        {
            //1.19.1 get�⏕����(SubAccountTypeEnum)
            //[����] 
            //�⏕�����^�C�v�F 2�i�����M�p��������i�ۏ؋��j�j
            l_subAccount2 = 
                this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);  
                
            //1.19.2 get�a����ւ̐U�֊z(�⏕���� : �⏕����, �K�v���� : double, ��n�� : Date)
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j 
            //�����K�v�����F ���N�G�X�g�f�[�^.�U�֋��z 
            //��n���F�@@get������()�̖߂�l
            l_dblTransferAmount =
                l_tPTradingPowerService.getTransferAmountToEquitySubAcount(
                    (WEB3GentradeSubAccount)l_subAccount2,
                    Double.parseDouble(l_request.transferAmount),
                    l_datOrderBizDate); 
        }
        
        //1.20 createNewOrderId()
        long l_lngNewOrderId1 = l_orderManager.createNewOrderId();    
        
        //1.21 submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, 
        //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�����^�C�v�F 5�i�����j 
        //������ʁF FX�U�֏����}�X�^.�������
        //�������e�F ���o���������e�i�U�֒����@@�j 
        //�C���^�Z�v�^�F FX�U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g�i�U�֒����@@�j 
        //����ID�F ����ID�F�@@createNewOrderId()�̖߂�l�i����ID�@@�j 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_orderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            l_orderTypeEnum,
            l_aioWEB3AioNewOrderSpec1,
            l_interceptor1,
            l_lngNewOrderId1,
            l_request.password);
            
        String l_strNewNumber2 = null;
        
		//�@@�Ɩ����t�̎擾�iyyyyMMdd�j
		String l_strBizDate = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd");
		Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
		
        //�@@��������yyyyMMdd�^�ɕύX
        String l_strOrderBizdat1 = WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
		Date l_datOrderBizDate1 = WEB3DateUtility.getDate(l_strOrderBizdat1, "yyyyMMdd");
		
		//1.22 is�ۏ؋��U��(�⏕���� l_subAccount, Date �Ɩ����t, Date �����P��.������)
		boolean l_blnIsDepositTransfer = l_orderManager.isDepositTransfer(l_subAccount, l_datBizDate, l_datOrderBizDate1);

        //�M�p�ۏ؋�����a����ւ̐U�֗v�̏ꍇ
        // �⏕����.getMainAccount().is�M�p�����J�݁i�ٍϋ敪�i=�h�w��Ȃ��h�j�̖߂�l == true
        // and get������()�̖߂�l = get��n��()�̖߂�l
        // and get�a����ւ̐U�֊z()�̖߂�l > 0
        // and is�ۏ؋��U��(�j�̖߂�l == true
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)
            && WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0
            && l_dblTransferAmount > 0
            && l_blnIsDepositTransfer)
        {
            //1.23.1 �V�[�P���X�}
            //�iFX�ւ̐U�ցj�M�p�U�֒����o�^
            
            //1.23.1.1.1
            //get�V�K���ʃR�[�h(String, String, ProductTypeEnum)(�M�p�U�֗p���ʃR�[�h)
            //[����] 
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
            //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
            //�����^�C�v�F 5�i�����j
            l_strNewNumber2 = 
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount2.getInstitution().getInstitutionCode(),
                    l_subAccount2.getMainAccount().getBranch().getBranchCode(),
                    ProductTypeEnum.CASH);
                
            //1.23.1.1.2 ���o���������e
            //[����] 
            //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�U�փ^�C�v�F 2�i�o���j 
            //���iID�F get���iID()�̖߂�l 
            //���z�F get�a����ւ̐U�֊z()�̖߂�l 
            //�L�q�F null 
            //�U�֗\����F get������()�̖߂�l 
            //���ϊ���ID�F null 
            //����ID�F null
            WEB3AioNewOrderSpec l_aioWEB3AioNewOrderSpec2 = 
                new WEB3AioNewOrderSpec(
                    l_trader,
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    AssetTransferTypeEnum.CASH_OUT,
                    l_orderManager.getProductId(l_subAccount.getInstitution()),
                    l_dblTransferAmount,
                    null,
                    l_datOrderBizDate,
                    null,
                    null);
                    
            //1.23.1.1.3 �U�֒����X�V�C���^�Z�v�^(���o���������e)
            //[�����̐ݒ�] 
            //���o���������e�F�@@���o���������e�i�U�֒����A�j
            WEB3AioTransferOrderUpdateInterceptor l_interceptor2 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioWEB3AioNewOrderSpec2);
                
            //1.23.1.1.4 �ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
            //(1)�������F�@@get������()�̖߂�l
            l_interceptor2.setBizDate(l_datOrderBizDate);
            
            //(2)��n���F�@@get������()�̖߂�l
            l_interceptor2.setDeliveryDate(l_datOrderBizDate);
            
            //(3)���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�A�j
            l_interceptor2.setOrderRequestNumber(l_strNewNumber2);
            
            //(4)MQ�X�e�[�^�X�F�@@0(�����M)
            l_interceptor2.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //1.23.1.1.5 ���o���������e
            //[����] 
            //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�U�փ^�C�v�F 1�i�����j 
            //���iID�F get���iID()�̖߂�l 
            //���z�F  get�a����ւ̐U�֊z()�̖߂�l 
            //�L�q�F null 
            //�U�֗\����F get������()�̖߂�l 
            //���ϊ���ID�F null 
            //����ID�F null
            WEB3AioNewOrderSpec l_aioWEB3AioNewOrderSpec3 = 
                new WEB3AioNewOrderSpec(
                    l_trader,
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    AssetTransferTypeEnum.CASH_IN,
                    l_orderManager.getProductId(l_subAccount.getInstitution()),
                    l_dblTransferAmount,
                    null,
                    l_datOrderBizDate,
                    null,
                    null);
                    
            //1.23.1.1.6 �U�֒����X�V�C���^�Z�v�^(���o���������e)
            //[�����̐ݒ�] 
            //���o���������e�F�@@���o���������e�i�U�֒����B�j
            WEB3AioTransferOrderUpdateInterceptor l_interceptor3 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioWEB3AioNewOrderSpec3);
                
            //1.23.1.1.7 �ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
            //(1)�������F�@@get������()�̖߂�l
            l_interceptor3.setBizDate(l_datOrderBizDate);
            
            //(2)��n���F�@@get������()�̖߂�l
            l_interceptor3.setDeliveryDate(l_datOrderBizDate);
            
            //(3)���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�A�j
            l_interceptor3.setOrderRequestNumber(l_strNewNumber2);
            
            //(4)MQ�X�e�[�^�X�F�@@0(�����M)
            l_interceptor3.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //1.23.1.1.8 createNewOrderId()
            long l_lngNewOrderId2 =  l_orderManager.createNewOrderId();
            
            //get�ۏ؋���������(SubAccountTypeEnum)
            //[����] 
            //�⏕�����^�C�v�F 2:�����M�p��������i�ۏ؋��j
            SubAccount l_marginSubAccount =
                this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            //1.23.1.1.9 submit�U�֒���
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j 
            //�����^�C�v�F 5�i�����j 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�������e�F ���o���������e�i�U�֒����A�j 
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֒����A�j 
            //����ID�F createNewOrderId()�̖߂�l�i����ID�A�j 
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
            l_orderManager.submitTransferOrder(
                l_marginSubAccount,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioWEB3AioNewOrderSpec2,
                l_interceptor2,
                l_lngNewOrderId2,
                l_request.password);
                
            //1.23.1.1.10 createNewOrderId()
            long l_lngNewOrderId3 =  l_orderManager.createNewOrderId();
            
            //1.23.1.1.11 submit�U�֒���
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l�i�a��������j 
            //�����^�C�v�F 5�i�����j 
            //������ʁF 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j 
            //�������e�F ���o���������e�i�U�֒����B�j 
            //�C���^�Z�v�^�F �U�֒����X�V�C���^�Z�v�^�i�U�֒����B�j 
            //����ID�F createNewOrderId()�̖߂�l�i����ID�B�j 
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�                                            
            l_orderManager.submitTransferOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioWEB3AioNewOrderSpec3,
                l_interceptor3,
                l_lngNewOrderId3,
                l_request.password);                                            
        }
        
        //1.24 �]�͍Čv�Z(�⏕���� : �⏕����)
        //[�����̐ݒ�] 
        //�⏕�����F�@@get�⏕����()(*)�̖߂�l 
        //�a������̕⏕�����^�C�v���w�肵�Ď擾�����⏕����
        l_tPTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        
        //1.25 getFX�ڋq(String, String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������.FX�V�X�e���R�[�h 
        //�ڋq�R�[�h�F�@@�⏕����.getMainAccount().getAccountCode()
        FxAccountParams l_fxAccountParams;
        try
        {
            l_fxAccountParams =
                l_dataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_comConditionParams.getFxSystemCode(),
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in FX�ڋqParams���擾����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.26. GFT�˗��d������()
        //GFT�˗��d�����׃I�u�W�F�N�g�𐶐�����
        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit(); 

        //1.27. get�����敪(String)
        //[get�����݋敪()�Ɏw�肷�����]  
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^�DFX�V�X�e���R�[�h
        String l_strOperationDiv = getOperationDiv( l_request.fxSystemCode);

        //get�ϊ�FX���O�C��ID(long, String, String, long)
        //[����]
        // �،����ID�F�@@�⏕����.getInstitution.getInstitutionId()
        // FX�V�X�e���R�[�h�F�@@��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        // FX���O�C��ID�������F�@@��Е�FX�V�X�e������Params.FX���O�C��ID������
        // FX���O�C��ID�F�@@FX�ڋqParams.FX���O�C��ID
        String l_strChangedFXLoginID = l_dataControlService.getChangedFXLoginID(
            l_subAccount.getInstitution().getInstitutionId(),
            l_comConditionParams.getFxSystemCode(),
            l_comConditionParams.getFxHeadOfLoginId(),
            l_fxAccountParams.getFxLoginId());

        //1.29. (*)�v���p�e�B�Z�b�g
        //(*)GFT�˗��d�����ׂɕK�v�ȃv���p�e�B���Z�b�g����i���L�ȊO�̃v���p�e�B�͐ݒ肵�Ȃ��j
        //(1)DIR��GFT���M�����F���ݎ����i�V�X�e���^�C���X�^���v�j
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
        l_gftAskingTelegramUnit.dirSendTime = 
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMddHHmmss"); 
        
        //(2)�����敪�Fget�����敪()
        l_gftAskingTelegramUnit.gftOperationDiv = l_strOperationDiv;     
        
        //(3)�ב֕ۏ؋������ԍ��F���N�G�X�g�f�[�^.FX�������.�����ԍ�
        l_gftAskingTelegramUnit.fxAccountCode = 
            l_request.fxAccInformationUnit.fxAccountCode;
        
        //(4)�������O�C��ID�Fget�ϊ�FX���O�C��ID�̖߂�l
        l_gftAskingTelegramUnit.fxFirstLoginId = l_strChangedFXLoginID;
        
        //(5)�S���敪�F��Е�FX�V�X�e������Params.�S���敪
        l_gftAskingTelegramUnit.groupName = l_comConditionParams.getGroupName();
        
        //(6)���o���z�F���N�G�X�g�f�[�^.�U�֋��z
        l_gftAskingTelegramUnit.cashinoutAmt = l_request.transferAmount;
        
        //(7)WOLF�Z�b�V�����L�[�F���N�G�X�g�f�[�^.WOLF�Z�b�V�����L�[
        l_gftAskingTelegramUnit.wolfSession = l_request.wolfSession;
        
        //(8)�A�v���P�[�V����ID�F���N�G�X�g�f�[�^.�A�v���P�[�V����ID
        l_gftAskingTelegramUnit.wolfAid = l_request.wolfAid;
        
        //(9)�Đ����T�[�r�XID�F���N�G�X�g�f�[�^.�Đ����T�[�r�XID
        l_gftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
        
        //(10)SSID�F���N�G�X�g�f�[�^.SSID
        l_gftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
        
        //(11)��ЃR�[�h�F�⏕����.�،���ЃR�[�h
        l_gftAskingTelegramUnit.institutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        
        //(12)���X�R�[�h�F�⏕����.get����X().getBranchCode()
        l_gftAskingTelegramUnit.branchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //(13)�ڋq�R�[�h�F�⏕����.getMainAccount().getAccountCode()
        l_gftAskingTelegramUnit.accountCode = 
            l_subAccount.getMainAccount().getAccountCode();
        
        //(14)���ʃR�[�h�Fget�V�K���ʃR�[�h()�̖߂�l�i���ʃR�[�h�@@�j
        l_gftAskingTelegramUnit.requestNumber = l_strNewNumber1;
        
        //(15)��n���Fget��n��()�̖߂�l
    	l_gftAskingTelegramUnit.deliveryDate =
            WEB3DateUtility.formatDate(
                l_datDeliveryDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //1.30. createGFT�d���n�b�V���l(GFT�˗��d������)
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�v���p�e�B�Z�b�g���s����GFT�˗��d������
        //(1)produce FX�d�������T�[�r�X
        WEB3FXTelegramProcessService l_telegramProcessService =
            (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
        //(2)    
        String l_strHashValue = 
            l_telegramProcessService.createGFTTelegramHashValue(l_gftAskingTelegramUnit);
        //(3)
        //createGFT�d���n�b�V���l()�̖߂�l�̃n�b�V���l��
        //GFT�˗��d������.�n�b�V���l�ɃZ�b�g����B
        l_gftAskingTelegramUnit.hashValue =  l_strHashValue;
            
        //1.31. insertGFT�d���ۑ�(GFT�˗��d������)
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������           
        l_dataControlService.insertGFTMessage(l_gftAskingTelegramUnit);

        //insertGFT�U�֏�(GFT�˗��d������, String, String, String,
        // ��Е�FX�V�X�e������Params, String)
        //[�����̐ݒ�] 
        //GFT�˗��d�����ׁF�@@�v���p�e�B�Z�b�g���s����GFT�˗��d������ 
        //�R�[�X�敪�F�@@���N�G�X�g�f�[�^.FX�������.�R�[�X�敪 
        //��n�\����F�@@get��n��()�̖߂�l 
        //�M�p�U�֗p���ʃR�[�h�F�@@(*) 
        //��Е�FX�V�X�e������Params�F ��Е�FX�V�X�e������Params
        //���o���ꗗ����敪:�@@�@@FX�U�֏����}�X�^Params.���o���ꗗ����敪
        //(*)�M�p�����J�ݍ� && �ۏ؋�����a������ւ̐U�ւ��K�v�ȏꍇ�A
        //�̔Ԃ������ʃR�[�h�A���Z�b�g�B�ȊO�Anull���Z�b�g�B
        boolean l_blnMarginTransferAccountOpenCheck = false;
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)
            && WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0
            && l_dblTransferAmount > 0
            && l_blnIsDepositTransfer)
        {
            l_blnMarginTransferAccountOpenCheck = true;
        }

		if (l_blnMarginTransferAccountOpenCheck)
        {
            l_dataControlService.insertGFTTransferStatus(
                l_gftAskingTelegramUnit,
                l_request.fxAccInformationUnit.fxCourseDiv,
                WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"),
                l_strNewNumber2,
                l_comConditionParams,
                l_fxTransferMasterParams.getIoListTradeDiv());
        }
        else
        {
            l_dataControlService.insertGFTTransferStatus(
                l_gftAskingTelegramUnit,
                l_request.fxAccInformationUnit.fxCourseDiv,
                WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"),
                null,
                l_comConditionParams,
                l_fxTransferMasterParams.getIoListTradeDiv());
        }
        
        //1.33. createResponse()
        WEB3FXTransToFXAskingResponse l_response = 
            (WEB3FXTransToFXAskingResponse)l_request.createResponse();
            
        //1.34. ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //URL�F ��Е�FX�V�X�e������Params.URL
        l_response.fxUrl = l_comConditionParams.getUrl();
        
        //GFT�˗��d�����ׁF�@@(*��L�ŕҏW���s����GFT�˗��d������)
        l_response.fxGftAskingTelegramUnit = l_gftAskingTelegramUnit;
        
        //����ID�FcreateNewOrderId()�̖߂�l�i����ID�@@�j
        l_response.orderId = String.valueOf(l_lngNewOrderId1);
        
        //�m�F���������F�@@get������()�̖߂�l    
        l_response.checkDate = l_datOrderBizDate;        
                                     
        log.exiting(l_strMethodName);            
            
        return l_response;
    }

    /**
     * (submit����) <BR>
     * �U�֒����̊����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�ւ̐U�ցjsubmit�����v�Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�ւ̐U�� �v<BR>
     * �iFX����U�ցjsubmit����) <BR>
     * : 1.3 getGFT�U�֏�(String, String, String) <BR>
     * �߂�l��null�̏ꍇ�A��O��thorw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�ב֕ۏ؋��T�[�r�X���f��) / FX�����J�� �v<BR>
     * �iFX�����J�݁jsubmit�����J��) <BR>
     * : 1.7.5 (*)��O��throw <BR>
     * �@@�̏ꍇ�F�u2�d��M�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01972 <BR>
     * <BR>
     * �A�B�C�̏ꍇ�F�u���̑���FX�V�X�e���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * �D�̏ꍇ�FGFT���ʒʒm�d������.��t���ʂɉ����Ĉȉ��̗�O�ƂȂ� <BR>
     * 00000105(�z�X�g�������ԊO)�̏ꍇ �F�u��t���ԊO�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01801 <BR>
     * <BR>
     * 00000199(�z�X�g�V�X�e���G���[)�̏ꍇ �F�u�ʐM�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01802 <BR>
     * <BR>
     * 00000204(�c���s���G���[)�̏ꍇ �F�u�c���s���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01803 <BR>
     * <BR>
     * 00000205�i�ב֌����̏����Y�c���s���j:�u�����Y�c�����s�����Ă��܂��v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02672 <BR>
     * <BR>
     * 00000206�i�ב֌����̌����c���s���j�F�u�����c�����s�����Ă��܂��v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02673 <BR>
     * <BR>
     * 00000207�i�ב֌����Ƀ}�C�i�X�ʉ݂���j�F�u�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂��B<BR>
     * �ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B�v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02674 <BR>
     * <BR>
     * 00000801(2�d���M�G���[)�̏ꍇ �F�u2�d���M�G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01799 <BR>
     * <BR>
     * 00000501�i�Y������؋������������݂��Ȃ��j�̏ꍇ�@@�F[�؋����������J�݃G���[]<BR>
     * <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02436<BR>
     * <BR>
     * 00000503�i�Y������ב֕ۏ؋����������݂��Ȃ��j�F�u�ב֌����w�̐U�ւł��B<BR>
     * ���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������ <BR>
     * ���q�邵�������p�����������Ƃ͂ǂ�������v<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02675 <BR>
     * <BR>
     * �ȊO�̎�t���ʂ̏ꍇ �F�u���̑���FX�V�X�e���G���[�v <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransToFXCompleteResponse
     * @@roseuid 41C7B20800BF
     */
    protected WEB3FXTransToFXCompleteResponse submitOrder(
        WEB3FXTransToFXCompleteRequest l_request)
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitOrder(WEB3FXTransToFXCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate() 
        l_request.validate();
        
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        //1.2. get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        SubAccount l_subAccount = getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3. insertGFT�d���ۑ�(GFT�˗��d������)
        //[�����̐ݒ�] 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
        //(1)produce FX�f�[�^����T�[�r�X 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        l_dataControlService.insertGFTMessage(l_request.fxGftResultNoticeTelegramUnit);
        
        //1.4. getGFT�U�֏�(String, String, String)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��ЃR�[�h 
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���X�R�[�h 
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���ʃR�[�h
        GftTransferStatusParams l_transferStatusParams = 
            l_dataControlService.getGFTTransferStatus(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                l_request.fxGftResultNoticeTelegramUnit.branchCode,
                l_request.fxGftResultNoticeTelegramUnit.requestNumber);
            
        if (l_transferStatusParams == null)
        {
            log.debug("GFT�U�֏󋵎擾�G���[");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "GFT�U�֏󋵎擾�G���[");
        }
        
        //produce FX�d�������T�[�r�X
        WEB3FXTelegramProcessService l_telegramProcessService =
            (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
        
        //1.5. get��Е�FX�V�X�e������(String, String, String)
        //��Е�FX�V�X�e������Params���擾����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode() 
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h
        CompFxConditionParams l_compFxCondParams = null;
        try
        {
            l_compFxCondParams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in ��Е�FX�V�X�e������Params���擾����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }        

        //getFX�U�֏����}�X�^(long, String)
        //�y�����z
        //FX�V�X�e������ID�@@= ��Е�FX�V�X�e������Params.FX�V�X�e������ID
        //�U�֋敪 = 1�F�o��
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_compFxCondParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        boolean l_blnTelegramSet = true;
        boolean l_blnPropSame = true;
        boolean l_blnMailSame = true;
        //��Е�FX�V�X�e������Params.SOAP�ڑ����{�敪== 0�FSOAP�ڑ������{ or 2�F�����J�݂̂ݎ��{�̏ꍇ
        if (WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(l_compFxCondParams.getSoapConnectDiv())
            || WEB3AioSoapConnectDivDef.TRANSFER_ENFORCEMENT.equals(l_compFxCondParams.getSoapConnectDiv()))
        {
            //1.61. isGFT�d�����ڐݒ�(GFT���ʒʒm�d������)
            //[�����̐ݒ�] 
            //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
            l_blnTelegramSet = 
                l_telegramProcessService.isGFTTelegramSet(l_request.fxGftResultNoticeTelegramUnit);
            
            //1.62. isGFT�d������������v(GFT���ʒʒm�d������)
            //[�����̐ݒ�] 
            //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
            l_blnPropSame = 
                l_telegramProcessService.isGFTTelegramLengthPropSame(l_request.fxGftResultNoticeTelegramUnit);

            //isGFT�d�����ڐݒ�()=true and isGFT�d������������v()=true �̏ꍇ�A���{
            if (l_blnTelegramSet && l_blnPropSame)
            {
                //1.63. isGFT�d������M���ڈ�v(GFT���ʒʒm�d������)
                //[�����̐ݒ�] 
                //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
                l_blnMailSame = 
                    l_telegramProcessService.isGFTTelegramSendAndReceiveValueSame(l_request.fxGftResultNoticeTelegramUnit);
            }
        }

        //�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A���{����B
        // ����Е�FX�V�X�e������Params.SOAP�ڑ����{�敪== 1�FSOAP�ڑ����{�̏ꍇ�A
        //   �B�A�C�͏����Ɋ܂߂Ȃ�
        //�@@����M�敪���s���̏ꍇ�i2�d��M�G���[�j
        //�igetGFT�U�֏�()�̖߂�l��GFT�U�֏�Params.����M�敪 �� 2(��M��)�j
        //�A����M�敪���s���̏ꍇ�i���̑��G���[�j
        //�igetGFT�U�֏�()�̖߂�l��GFT�U�֏�Params.����M�敪 != (1(���M��)�A2(��M��))
        //log for test
        log.debug("GFT�U�֏�Params.����M�敪 = " + l_transferStatusParams.getSendRcvDiv()); 
        
        //�B���ʒʒm�d�����e�̑Ó������s���̏ꍇ
        //�iisGFT�d�����ڐݒ�()��fasle�A�܂��́AisGFT�d������������v()��false�j
        //log for test
        log.debug("isGFT�d�����ڐݒ�() = " + l_blnTelegramSet);
        log.debug("isGFT�d������������v() = " + l_blnPropSame);
        
        //�C����M�d���̍��ڂ��s��v�̏ꍇ
        //�iisGFT�d������M���ڈ�v()��false�j
        //log for test
        log.debug("isGFT�d������M���ڈ�v() = " + l_blnMailSame);

        boolean l_blnIsResultNoticeTelegramPro = false;
        boolean l_blnIsTelegramSendAndReceiveSame = false;
        if (!WEB3AioSoapConnectDivDef.SOAP_CONNECT_ENFORCEMENT.equals(
            l_compFxCondParams.getSoapConnectDiv()))
        {
            if (!l_blnTelegramSet || !l_blnPropSame)
            {
                l_blnIsResultNoticeTelegramPro = true;
            }

            if (!l_blnMailSame)
            {
                l_blnIsTelegramSendAndReceiveSame = true;
            }
        }

        //�D�U�ւ�����Ɏ�t���Ȃ������ꍇ
        //(���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��t���� != 00000000�i����j�j
        log.debug("���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��t���� = " + l_request.fxGftResultNoticeTelegramUnit.resultCode);
        
        //produce ���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X   
        WEB3AioMarketRequestSenderServiceImpl l_aioMarketSenderService = 
            (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();
                        
        //��n��
        Date l_datDeliveryDate = null;
        
        if (WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
            !(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
            WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv())) ||
            l_blnIsResultNoticeTelegramPro ||
            l_blnIsTelegramSendAndReceiveSame ||
            !WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode))
        {
            //1.9.1. �@@�ȊO�̏ꍇ�A���{����
            if (!WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()))
            {
                //1.9.1.1. updateGFT�U�֏�(GFT�U�֏�Params, GFT���ʒʒm�d������, String, String)
                //[�����̐ݒ�] 
                //GFT�U�֏�Params�F�@@getGFT�U�֏�()�̖߂�l 
                //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������ 
                //�X�V���n�\����F�@@null 
                //�G���[���R�R�[�h�F�@@ 
                String l_strCode = null;
                //�A�̏ꍇ�F0001�i����M�敪�`�F�b�N�G���[�j 
                if (!(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
                    WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv())))
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.SENDRCV_ERROR;
                }
                //�B�̏ꍇ�F0002�i�p�����[�^�Ó����`�F�b�N�G���[�j 
                else if (!(l_blnTelegramSet && l_blnPropSame))
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.PARAM_VALIDITY_ERROR;
                }
                //�C�̏ꍇ�F0003�i�p�����[�^��v�`�F�b�N�G���[�j 
                else if (!l_blnMailSame)
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.PARAM_MISMATCH_ERROR;
                }
                //�D�̏ꍇ�F0004�i��t���ʃR�[�h�`�F�b�N�G���[�j
                else 
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR;
                }
                
                l_dataControlService.updateGFTTransferStatus(
                    l_transferStatusParams,
                    l_request.fxGftResultNoticeTelegramUnit,
                    null,
                    l_strCode);

            }
            
            //1.9.2. �����M��(getGFT�U�֏�()�̖߂�l��GFT�U�֏�Params.����M�敪 �� 1(���M��)�j ����
            //�U�ւ�����Ɏ�t���Ȃ�����(���N�G�X�g�f�[�^.GFT���ʒʒm�d������.��t���� != 00000000(����))
            //�ꍇ�A���{����B
            //(*)�������A��t���ʂ�2�d���M�G���[(00000801)�A�܂���GFT�ڑ��G���[(00000990)�̏ꍇ�́A���{���Ȃ��B
            if (WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) &&
            	!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
            		l_request.fxGftResultNoticeTelegramUnit.resultCode) &&
            	!(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(
            		l_request.fxGftResultNoticeTelegramUnit.resultCode)
                    || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                        l_request.fxGftResultNoticeTelegramUnit.resultCode)))
            {
                //1.9.2.1. submit�������()
                //�o�^�����U�֒����̎���������s���B 
                //[�����̐ݒ�] 
                //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
                //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
                //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h 
                //���ʃR�[�h�F�@@GFT�U�֏�Params.���ʃR�[�h 
                //�M�p�U�֗p���ʃR�[�h�F�@@GFT�U�֏�Params.�M�p�U�֗p���ʃR�[�h 
                l_dataControlService.submitCancelOrder(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    l_transferStatusParams.getMrgTrnOrderRequestNumber());
            }
            
            //1.9.3. ��O��throw����B�@@�@@
            // �@@�̏ꍇ�F�u2�d��M�G���[�v
            if (WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()))
            {
                log.debug("2�d��M�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01972,
                    this.getClass().getName() + "." + l_strMethodName);    
            }
            // �A�B�C�̏ꍇ�F�u���̑���FX�V�X�e���G���[�v
            if (!(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
                WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv())) ||
                !l_blnTelegramSet ||
                !l_blnPropSame ||
                !l_blnMailSame)
            {
                log.debug("���̑���FX�V�X�e���G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                    this.getClass().getName() + "." + l_strMethodName);    
            }
            // �D�̏ꍇ�FGFT���ʒʒm�d������.��t���ʂɉ����Ĉȉ��̗�O�ƂȂ�
            if (!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
            {
                // 00000105(�z�X�g�������ԊO)�̏ꍇ    �F�u��t���ԊO�G���[�v
                if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105.equals(
                        l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("��t���ԊO�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01801,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                // 00000199(�z�X�g�V�X�e���G���[)�̏ꍇ  �F�u�ʐM�G���[�v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�ʐM�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                // 00000204(�c���s���G���[)�̏ꍇ �F�u�c���s���G���[�v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�c���s���G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                //00000205(�ב֌����̏����Y�c���s��)�̏ꍇ    �F�u�����Y�c�����s�����Ă��܂��v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000205.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�����Y�c���s�����Ă��܂��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02672,
                        this.getClass().getName() + "." + l_strMethodName,
                        "�����Y�c���s�����Ă��܂��B");
                }
                //  00000206(�ב֌����̌����c���s��)�̏ꍇ    �F�u�����c�����s�����Ă��܂��v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000206.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�����c�����s�����Ă��܂��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02673,
                        this.getClass().getName() + "." + l_strMethodName,
                        "�����c�����s�����Ă��܂��B");
                }
                //  00000207(�ב֌����Ƀ}�C�i�X�ʉ݂���)�̏ꍇ    �F�u�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂��B
                //  �ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B�v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000207.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂�" +
                            "�ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02674,
                        this.getClass().getName() + "." + l_strMethodName,
                        "�ב֌����ɂ�����ʉ݌����c���ŁA�}�C�i�X�ʉ݂������܂�" +
                        "�ב֌����ŃR���o�[�W������ɍēx�U�ւ��������Ă������B");
                }
                // 00000801(2�d���M�G���[)�̏ꍇ �F�u2�d���M�G���[�v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("2�d���M�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01799,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
               //00000501(���o�����ɊY������؋������������݂��Ȃ�)�̏ꍇ �F�u�������J�݃G���[�v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("�Y������؋������������݂��Ȃ�");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02436,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                //00000503�i�Y������ב֕ۏ؋����������݂��Ȃ��j�F�u�ב֌����w�̐U�ւł��B
                // ���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������ 
                // ���q�邵�������p�����������Ƃ͂ǂ�������v
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000503.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                 {
                    log.debug("�ב֌����w�̐U�ւł��B" +
                            "���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������ " +
                            "���q�邵�������p�����������Ƃ͂ǂ�������B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02675,
                        this.getClass().getName() + "." + l_strMethodName,
                        "�ב֌����w�̐U�ւł��B" +
                        "���̋@@�\�͈ב֌������J�݂��A���U�֓��ӏ��ɋL���E�����t������������ " +
                        "���q�邵�������p�����������Ƃ͂ǂ�������B");
                 }
                // �ȊO�̎�t���ʂ̏ꍇ �F�u���̑���FX�V�X�e���G���[�v
                else 
                {
                    log.debug("���̑���FX�V�X�e���G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
            }
        }
        
        //1.10. get������()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();  

        //get��n��(Date, �⏕����, String)
        //[�����̐ݒ�]
        //�������F�@@get������()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //��n���ݒ�敪�F�@@getFX�U�֏����}�X�^().��n���ݒ�敪
        l_datDeliveryDate = l_dataControlService.getDeliveryDate(
            l_datOrderBizDate,
            l_subAccount,
            l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.12. get�����P��(String, String, String, String, SubAccountTypeEnum)
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
        //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
        //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h 
        //���ʃR�[�h�F�@@GFT�U�֏�Params.���ʃR�[�h 
        //�⏕�����^�C�v�F 1�i������������i�a����j�j
        AioOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = 
                l_orderManager.getOrderUnit(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get�����P��", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }           
         
        //1.13. get���o���z�敪(GFT���ʒʒm�d������)
        //[����] 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������
        String l_strCashInOutAmountDiv = 
            l_dataControlService.getCashInOutAmountDiv(l_request.fxGftResultNoticeTelegramUnit);        
        
        //1.14. (*)get���o���z�敪()�̖߂�l��1�̏ꍇ
        if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT.equals(l_strCashInOutAmountDiv))
        {
            //1.14.1 update�U�֏����敪(String, String, String, String, long, String, String)
            //�i�a���������FX�j�U�֒����̏����敪�̍X�V���s���B 

            //[�����̐ݒ�] 
            //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
            //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
            //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h 
            //���ʃR�[�h�F�@@GFT�U�֏�Params.���ʃR�[�h 
            //�����P��ID�F�@@get�����P��()�̖߂�l�̒����P��.�����P��ID 
            //�X�V�㔭�����F�@@(*) 
            //�X�V���n���F�@@(*)

            //(*)���N�G�X�g�f�[�^.�m�F�������� == get������()�̖߂�l�̏ꍇ�Anull 
            //   ����ȊO�̏ꍇ�A 
            //      �X�V�㔭�����F get������()�̖߂�l���Z�b�g�B 
            //      �X�V���n���F get��n��()�̖߂�l���Z�b�g�B 

            //   �����X�I�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.get���X()�ɂĎ擾�B 
            //      [�g���A�J�E���g�}�l�[�W��.get���X()�ɃZ�b�g�������] 
            //      �،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
            //      ���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)
            {
                l_aioMarketSenderService.updateTransferProcessDiv(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    l_orderUnit.getOrderUnitId(),
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"));
            }
            else 
            {
                l_aioMarketSenderService.updateTransferProcessDiv(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    l_orderUnit.getOrderUnitId(),
                    null,
                    null);
            }
        }

        //get���o���z�敪()�̖߂�l��2�̏ꍇ
        else if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {        
            //update�U�֏����敪(String, String, String, String, long,
            // String, String, String, String)
            //�i�a���������FX�j�U�֒����̏����敪�̍X�V���s���B 
    
            //[�����̐ݒ�] 
            //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
            //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
            //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h 
            //���ʃR�[�h�F�@@GFT�U�֏�Params.���ʃR�[�h 
            //�����P��ID�F�@@get�����P��()�̖߂�l�̒����P��.�����P��ID 
            //�X�V�㔭�����F�@@(*) 
            //�X�V���n���F�@@(*) 
            //���o���z�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2
            //�E�v�R�[�h�F�@@71�i����؋����F���؁j
            //(*)���N�G�X�g�f�[�^.�m�F�������� == get������()�̖߂�l�̏ꍇ�Anull 
            //   ����ȊO�̏ꍇ�A 
            //      �X�V�㔭�����F get������()�̖߂�l���Z�b�g�B 
            //      �X�V���n���F get��n��()�̖߂�l���Z�b�g�B 
    
            //   �����X�I�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.get���X()�ɂĎ擾�B 
            //      [�g���A�J�E���g�}�l�[�W��.get���X()�ɃZ�b�g�������] 
            //      �،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
            //      ���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
            String l_strUpdatedBizDate = null; 
            String l_strUpdatedDeliveryDate = null;
            if (WEB3DateUtility.compare(l_datOrderBizDate, l_request.checkDate) != 0)
            {
                l_strUpdatedBizDate = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strUpdatedDeliveryDate = 
                    WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd");
            }           
            
            l_aioMarketSenderService.updateTransferProcessDiv(
                l_transferStatusParams.getInstitutionCode(),
                l_transferStatusParams.getBranchCode(),
                l_transferStatusParams.getAccountCode(),
                l_transferStatusParams.getOrderRequestNumber(),
                l_orderUnit.getOrderUnitId(),
                l_strUpdatedBizDate, 
                l_strUpdatedDeliveryDate, 
                l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO);
        }

        //1.16. (*)get���o���z�敪()�̖߂�l��3�̏ꍇ
        if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {        
            Trader l_trader = this.getTrader();

            //���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum,
            // long, double, String, Date, String, Long, String, String)
            //[�����̐ݒ�]  
            //�㗝���͎ҁF�@@get�㗝���͎�()�̖߂�l  
            //������ʁF FX�U�֏����}�X�^.�������
            //�U�փ^�C�v�F�@@�@@2�i�o���j 
            //���iID�F�@@get���iID()�̖߂�l  
            //���z�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z2  
            //�L�q�F�@@null
            //�U�֗\����F�@@get��n��()�̖߂�l
            //���ϋ@@��ID�F�@@null
            //����ID�F�@@null
            //�E�v�R�[�h�F�@@71�i����؋����F���؁j
            //�E�v���F�@@FX�U�֏����}�X�^.�E�v��
            WEB3AioNewOrderSpec l_aioNewOrderSpec =
                new WEB3AioNewOrderSpec(
                    l_trader,
                    l_fxTransferMasterParams.getOrderType(),
                    AssetTransferTypeEnum.CASH_OUT,
                    l_orderManager.getProductId(l_subAccount.getInstitution()),
                    Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2),
                    null,
                    l_datDeliveryDate,
                    null,
                    null,
                    WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO,
                    l_fxTransferMasterParams.getRemarkName());

            //1.16.2 FX�U�֒����X�V�C���^�Z�v�^(���o���������e)
            //[�����̐ݒ�]  
            //���o���������e�F�@@���o���������e�I�u�W�F�N�g
            WEB3FXTransferOrderUpdateInterceptor l_fxTransferOrderUpdateInterceptor =
                new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
            
            //1.16.3 get�V�K���ʃR�[�h(�،���ЃR�[�h : String, 
            //���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
            //[����]  
            //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h  
            //���X�R�[�h�F�@@�⏕����.get����X().getBranchCode()  
            //�����^�C�v�F 5�i�����j
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class); 
            
            String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                ProductTypeEnum.CASH);
            
            //1.16.4 (*)�v���p�e�B�Z�b�g
            //�������F�@@get������()�̖߂�l
            l_fxTransferOrderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
            
            //��n���F�@@get��n��()�̖߂�l 
            l_fxTransferOrderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
            
            //���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l
            l_fxTransferOrderUpdateInterceptor.setOrderRequestNumber(l_strNewNumber);
            
            //MQ�X�e�[�^�X�F�@@1(���M�ς�)
            l_fxTransferOrderUpdateInterceptor.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            //1.16.5 createNewOrderId()
            long l_lngNewOrderId = l_orderManager.createNewOrderId();        

            //update�U�֏����敪(String, String, String, String, long,
            //  String, String, String, String)
            //[�����̐ݒ�]
            //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h
            //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h
            //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h
            //���ʃR�[�h�F�@@GFT�U�֏�Params.���ʃR�[�h
            //�����P��ID�F�@@get�����P��()�̖߂�l�̒����P��.�����P��ID
            //�X�V�㔭�����F�@@(*)
            //�X�V���n���F�@@(*)
            //���o���z�F�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������.���o���z
            //�E�v�R�[�h�F 72�i����؋����F��؁j
            //(*)���N�G�X�g�f�[�^.�m�F�������� == get������()�̖߂�l�̏ꍇ�Anull
            //����ȊO�̏ꍇ�A
            //�X�V�㔭�����F get������()�̖߂�l���Z�b�g�B
            //�X�V���n���F get��n��()�̖߂�l���Z�b�g�B
            //�����X�I�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.get���X()�ɂĎ擾�B
            //[�g���A�J�E���g�}�l�[�W��.get���X()�ɃZ�b�g�������]
            //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h
            //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h
            String l_strUpdatedBizDate = null;
            String l_strUpdatedDeliveryDate = null;
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)
            {
                l_strUpdatedBizDate =
                    WEB3DateUtility.formatDate(
                        l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_strUpdatedDeliveryDate =
                    WEB3DateUtility.formatDate(
                        l_datDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            }

            l_aioMarketSenderService.updateTransferProcessDiv(
                l_transferStatusParams.getInstitutionCode(),
                l_transferStatusParams.getBranchCode(),
                l_transferStatusParams.getAccountCode(),
                l_transferStatusParams.getOrderRequestNumber(),
                l_orderUnit.getOrderUnitId(),
                l_strUpdatedBizDate,
                l_strUpdatedDeliveryDate,
                l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_OSAKA);

            //1.16.6 submit�U�֒���(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec, 
            //AioOrderManagerPersistenceInterceptor, long, String)
            //[����]  
            //�⏕�����F get�⏕����()�̖߂�l  
            //�����^�C�v�F 5�i�����j  
            //������ʁF FX�U�֏����}�X�^.�������
            //�������e�F ���o���������e 
            //�C���^�Z�v�^�F FX�U�֒����X�V�C���^�Z�v�^�I�u�W�F�N�g 
            //����ID�F �@@createNewOrderId()�̖߂�l 
            //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
            l_orderManager.submitTransferOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_fxTransferMasterParams.getOrderType(),
                l_aioNewOrderSpec,
                l_fxTransferOrderUpdateInterceptor,
                l_lngNewOrderId,
                l_request.password);            
        }       
        
        //1.17 (*4)
        if (l_transferStatusParams.getMrgTrnOrderRequestNumber() != null)
        {
        
            //1.17.1 get�U�֒����P��(String, String, String, String)
            //[�����̐ݒ�] 
            //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
            //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
            //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h 
            //���ʃR�[�h�F�@@GFT�U�֏�Params.�M�p�U�֗p���ʃR�[�h
            AioOrderUnit[] l_orderUnits;
            try
            {
                l_orderUnits =
                    l_orderManager.getTransferOrderUnit(
                        l_transferStatusParams.getInstitutionCode(),
                        l_transferStatusParams.getBranchCode(),
                        l_transferStatusParams.getAccountCode(),
                        l_transferStatusParams.getMrgTrnOrderRequestNumber());
            }
            catch (NotFoundException l_ex)
            {
                log.error("Not found �U�֒����P��", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
                      
            //1.17.2 �擾���������P�ʂ̗v�f����Loop����
            int l_intSize = 0;
            if (l_orderUnits != null)
            {
                l_intSize = l_orderUnits.length;
            }
                
            for (int i = 0; i < l_intSize; i++)
            {
                //1.17.2.1 update�U�֏����敪(String, String, String, String, long, String, String)    
                //[�����̐ݒ�] 
                //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
                //���X�R�[�h�F�@@GFT�U�֏�Params.���X�R�[�h 
                //�ڋq�R�[�h�F�@@GFT�U�֏�Params.�ڋq�R�[�h 
                //���ʃR�[�h�F�@@GFT�U�֏�Params.�M�p�U�֗p���ʃR�[�h 
                //�����P��ID�F�@@get�U�֒����P��()�̖߂�l�̒����P��.�����P��ID 
                //�X�V�㔭�����F�@@(*) 
                //�X�V���n���F�@@(*) 
                //(*)���N�G�X�g�f�[�^.�m�F�������� != get������()�̖߂�l�̏ꍇ�Aget������()�̖߂�l���Z�b�g�B�ȊO�Anull���Z�b�g 
                if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)
                {
                    l_aioMarketSenderService.updateTransferProcessDiv(
                        l_transferStatusParams.getInstitutionCode(),
                        l_transferStatusParams.getBranchCode(),
                        l_transferStatusParams.getAccountCode(),
                        l_transferStatusParams.getMrgTrnOrderRequestNumber(),
                        l_orderUnits[i].getOrderUnitId(),
                        WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"),
                        WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"));
                }
                else 
                {
                    l_aioMarketSenderService.updateTransferProcessDiv(
                        l_transferStatusParams.getInstitutionCode(),
                        l_transferStatusParams.getBranchCode(),
                        l_transferStatusParams.getAccountCode(),
                        l_transferStatusParams.getMrgTrnOrderRequestNumber(),
                        l_orderUnits[i].getOrderUnitId(),
                        null,
                        null);
                }
            }
        }
		//1.18. is�g���K���s(String)
        //[����] 
        //���������F�@@�hDEFAULT�h
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);
        
        //1.19. (*5) is�g���K���s()�̖߂�l == true �̏ꍇ)
        if (l_blnIsSubmitTrigger)
        {        
            log.debug("���ݎ������g���K���s�����{���鎞�ԑт̏ꍇ�A�g���K�[���s����B");
            //1.19.1 �g���K���s(String, String)
            //[����] 
            //�،���ЃR�[�h�F�@@GFT�U�֏�Params.�،���ЃR�[�h 
            //�f�[�^�R�[�h�F�@@GI806�i�ۏ؋��U�֐����j            
            l_aioMarketSenderService.submitTrigger(
                l_transferStatusParams.getInstitutionCode(),
                WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER + "T");
        }
            
        //1.20. updateGFT�U�֏�(GFT�U�֏�Params, GFT���ʒʒm�d������, String, String)
        //[�����̐ݒ�] 
        //GFT�U�֏�Params�F�@@getGFT�U�֏�()�̖߂�l 
        //GFT���ʒʒm�d�����ׁF�@@���N�G�X�g�f�[�^.GFT���ʒʒm�d������ 
        //�X�V���n�\����F�@@(*) 
        //�G���[���R�R�[�h�F�@@0000�i����j 
        //(*)�ȉ��̂Ƃ���  
        //���N�G�X�g�f�[�^.�m�F�������� == get������()�̏ꍇ�Anull�B  
        //����ȊO�̏ꍇ�Aget��n��()�̖߂�l�B               
        if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)         
        {
            l_dataControlService.updateGFTTransferStatus(
            	l_transferStatusParams,
                l_request.fxGftResultNoticeTelegramUnit,
                WEB3DateUtility.formatDate(l_datDeliveryDate,"yyyyMMdd"),
                WEB3ErrorReasonCodeDef.NORMAL);
        }   
        else
        {
            l_dataControlService.updateGFTTransferStatus(
                l_transferStatusParams,
                l_request.fxGftResultNoticeTelegramUnit,
                null,
                WEB3ErrorReasonCodeDef.NORMAL);
        }          
        //1.21. getOrder(long)
        //[�����̐ݒ�] 
        //arg0�F�@@get�����P��()�̖߂�l�̒����P��.����ID
        AioOrder l_order;
        try
        {            
            l_order = (AioOrder) l_orderManager.getOrder(l_orderUnit.getOrderId());            
        }
        catch (NotFoundException l_ex)
        {   
            log.error("getOrder", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.22. createResponse()
        WEB3FXTransToFXCompleteResponse l_response = 
            (WEB3FXTransToFXCompleteResponse)l_request.createResponse();    
            
        //1.23. ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�X�V���ԁF����.�X�V����
        
        l_response.lastUpdatedTimestamp = 
            ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        
        //���ʔԍ��Fget�����P��()�̖߂�l�̒����P��.����ID
        l_response.orderActionId = String.valueOf(l_orderUnit.getOrderId());
        
        //��n���Fget��n��()�̖߂�l
        l_response.deliveryDate = l_datDeliveryDate;
      
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (create�p�����[�^���X�g) <BR>
     * �p�����[�^���X�g�𐶐�����B <BR>
     *  <BR>
     * �P�j����.�ڑ��敪 == �h�iFX�j�������Z�敨����� �U�֓o�^�h �̏ꍇ <BR>
     *  <BR>
     *   �ȉ��̗v�f�̔z��𐶐����A�ԋp����B <BR>
     *  <BR>
     *   �E����.�d������.FX���O�C��ID.substring(1) <BR>
     *   �E"1"�i�a�����j <BR>
     *   �E����.�d������.���o���z <BR>
     *   �E����.�d������.FX���O�C��ID.substring(1) + <BR>
     * �@@�@@����.������.DIR��GFT���M����.substring(2) <BR>
     *  <BR>
     * ���Y������ڑ��敪���Ȃ��ꍇ�́A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_02408<BR>
     * @@param l_strConnectDiv - (�ڑ��敪)<BR>
     * @@param l_fxGftAskingTelegramUnit - (GFT�˗��d������)<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected String[] createParamList(String l_strConnectDiv,
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " createParamList(String, WEB3FXGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);

        String[] l_strParamList = new String[4];
        if (l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j����.�ڑ��敪 == �h�iFX�j�������Z�敨����� �U�֓o�^�h �̏ꍇ
        if (WEB3SoapConnectDivDef.FX_TOKYO_TRANSFER_SUBMIT.equals(l_strConnectDiv))
        {
            //�E����.�d������.FX���O�C��ID.substring(1)
            l_strParamList[0] = l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1);
            //�E"1"�i�a�����j
            l_strParamList[1] = "1";
            //�E����.�d������.���o���z
            l_strParamList[2] = l_fxGftAskingTelegramUnit.cashinoutAmt;
            //�E����.�d������.FX���O�C��ID.substring(1) + ����.������.DIR��GFT���M����.substring(2)
            l_strParamList[3] = l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1) +
                l_fxGftAskingTelegramUnit.dirSendTime.substring(2);
        }
        //���Y������ڑ��敪���Ȃ��ꍇ�́A��O���X���[����B
        else
        {
            log.debug("�Y������ڑ��敪���Ȃ��G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02408,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������ڑ��敪���Ȃ��G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_strParamList;
    }

    /**
     * (getGFT��t���ʃR�[�h) <BR>
     * GFT���ʒʒm�d�����ׂɃZ�b�g����GFT��t���ʃR�[�h���擾����B <BR>
     *  <BR>
     * �P�j����.�ڑ��敪 == �h�iFX�j�������Z�敨����� �U�֓o�^�h �̏ꍇ <BR>
     *  <BR>
     *   ����.��t���ʃR�[�h�� <BR>
     *  <BR>
     *   �E�h����I���h �̏ꍇ�A��t���ʃR�[�h.�h���������h <BR>
     *   �E�h�p�����^�G���[�h �̏ꍇ�A��t���ʃR�[�h.�h��L�ȊO�œd�������ɋN������G���[�h <BR>
     *   �E�h�ڋq�R�[�h�s���h �̏ꍇ�A��t���ʃR�[�h.�h���o�����ɊY������؋������������݂��Ȃ��h <BR>
     *   �E�h�d���o�^�G���[�h �̏ꍇ�A��t���ʃR�[�h.�h�Q�d�����G���[�h <BR>
     *   �E�h�����z�s���h �̏ꍇ�A��t���ʃR�[�h.�h���o���z�����z�������łȂ��h <BR>
     *   �E�h�ғ����ԊO�G���[�h �̏ꍇ�A��t���ʃR�[�h.�h�z�X�g�������ԊO�h <BR>
     *   �E��L�ȊO�̏ꍇ�A��t���ʃR�[�h.�h��L�A�y�щ��L�ȊO�̃G���[�h <BR>
     *  <BR>
     * ���Y������ڑ��敪���Ȃ��ꍇ�́A����.��t���ʃR�[�h�����̂܂ܕԋp����B <BR>
     * @@param l_strConnectDiv - (�ڑ��敪)<BR>
     * @@param l_strResultCode - (��t���ʃR�[�h)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected String getGFTResultCode(String l_strConnectDiv, String l_strResultCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getGFTResultCode(String, String)";
        log.entering(STR_METHOD_NAME);

        
        String l_strGFTResultCode = null;

        //�P�j����.�ڑ��敪 == �h�iFX�j�������Z�敨����� �U�֓o�^�h �̏ꍇ
        if (WEB3SoapConnectDivDef.FX_TOKYO_TRANSFER_SUBMIT.equals(l_strConnectDiv))
        {
            //�E�h����I���h �̏ꍇ�A��t���ʃR�[�h.�h���������h
            if (WEB3SoapResultCodeDef.NORMAL.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            }
            //�E�h�p�����^�G���[�h �̏ꍇ�A��t���ʃR�[�h.�h��L�ȊO�œd�������ɋN������G���[�h
            else if (WEB3SoapResultCodeDef.PARAM_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609;
            }
            //�E�h�ڋq�R�[�h�s���h �̏ꍇ�A��t���ʃR�[�h.�h���o�����ɊY������؋������������݂��Ȃ��h
            else if (WEB3SoapResultCodeDef.ACCOUNT_CODE_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
            }
            //�E�h�d���o�^�G���[�h �̏ꍇ�A��t���ʃR�[�h.�h�Q�d�����G���[�h
            else if (WEB3SoapResultCodeDef.DUP_SUBMIT_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
            }
            //�E�h�����z�s���h �̏ꍇ�A��t���ʃR�[�h.�h���o���z�����z�������łȂ��h
            else if (WEB3SoapResultCodeDef.IN_AMOUNT_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000502;
            }
            //�E�h�ғ����ԊO�G���[�h �̏ꍇ�A��t���ʃR�[�h.�h�z�X�g�������ԊO�h
            else if (WEB3SoapResultCodeDef.WORK_TIME_OUT_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105;
            }
            //�E��L�ȊO�̏ꍇ�A��t���ʃR�[�h.�h��L�A�y�щ��L�ȊO�̃G���[�h
            else
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901;
            }
        }
        //�Y������ڑ��敪���Ȃ��ꍇ�́A����.��t���ʃR�[�h�����̂܂ܕԋp����B
        else
        {
            l_strGFTResultCode = l_strResultCode;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strGFTResultCode;
    }

    /**
     * (submit����) <BR>
     * �U�֒����̊����������s���B <BR>
     * ��SOAP�ڑ��ɂčs���B <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�ւ̐U�ցjsubmit�����iSOAP�ڑ��j�v�Q�ƁB <BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�F�ȉ��̏����ŁA�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j���烌�R�[�h���擾<BR>
     * �@@�@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�͗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_03075 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3FXTransToFXCompleteSoapResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected WEB3FXTransToFXCompleteSoapResponse submitOrder(
        WEB3FXTransToFXCompleteSoapRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitOrder(WEB3FXTransToFXCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        //1.1      ��Е�FX�V�X�e������Params���擾����B 
        //
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F get����.getInstitution().getInstitutionCode() 
        //���X�R�[�h�F�@@get����.getBranch().getBranchCode() 
        //FX�V�X�e���R�[�h�F�@@���N�G�X�g�f�[�^.FX�V�X�e���R�[�h 
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = l_fxDataControlService.getCompFxCondition(
                this.getMainAccount().getInstitution().getInstitutionCode(),
                this.getMainAccount().getBranch().getBranchCode(),
                l_request.fxSystemCode);
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

        SoapConnectPrefRpcRow l_soapConnectPrefRpcRow = null;
        try
        {
            long l_lngBranchId = this.getMainAccount().getBranch().getBranchId();
            //(��)�ȉ��̏����ŊO���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j���烌�R�[�h�擾
            //[����]
            //���XID = this.get����().getBranch().getBranchId()�̖߂�l
            //�ڑ��敪 = ��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
            l_soapConnectPrefRpcRow =
                SoapConnectPrefRpcDao.findRowByPk(l_lngBranchId, l_compFxConditionParams.getFxSystemCode());
        }
        catch (DataFindException l_ex)
        {
            //�����R�[�h���擾�ł��Ȃ������ꍇ�͗�O���X���[����B
            log.error("�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j�̃��R�[�h���擾�ł��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j�̃��R�[�h���擾�ł��܂���B",
                l_ex);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }

        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = 
        	new SoapConnectPrefRpcParams(l_soapConnectPrefRpcRow);

        WEB3FXTransToFXAskingRequest l_askRequest = new WEB3FXTransToFXAskingRequest();
        l_askRequest.wolfSession = l_request.wolfSession;
        l_askRequest.wolfAid = l_request.wolfAid;
        l_askRequest.regetServiceId = l_request.regetServiceId;
        l_askRequest.wolfSsid = l_request.wolfSsid;

        l_askRequest.fxAccInformationUnit = l_request.fxAccInformationUnit;
        l_askRequest.transferAmount = l_request.transferAmount;
        l_askRequest.password = l_request.password;
        l_askRequest.fxSystemCode = l_request.fxSystemCode;

        WEB3FXTransToFXAskingResponse l_fxTransToFXAskingResponse = null;
        try
        {
            //1.1 FX�ւ̐U��TransactionCallback(FX�ւ̐U�ֈ˗����N�G�X�g)
            WEB3FXTransToFXTransactionCallback l_transactionCallback =
                new WEB3FXTransToFXTransactionCallback(l_askRequest);
    
            //1.2 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.3 doTransaction(�g�����U�N�V�������� : int, �g�����U�N�V�����R�[���o�b�N : TransactionCallback)
            //[����]
            //�g�����U�N�V���������F�@@TX_CREATE_NEW
            //�g�����U�N�V�����R�[���o�b�N�F�@@FX�ւ̐U��TransactionCallback�C���X�^���X
            l_fxTransToFXAskingResponse = (WEB3FXTransToFXAskingResponse) l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DB�A�N�Z�X�G���[�B" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strReturnValue = null;
        String l_strResultCode = null;
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit = null;
        WEB3FXTransConnection l_aioTransChangeConnection =
            (WEB3FXTransConnection)Services.getService(WEB3FXTransConnection.class);
        //��Е�FX�V�X�e������Params.�O���ڑ��V�X�e���R�[�h = '02�FTFX'�̏ꍇ
        if (WEB3ExtConnectSystemCodeDef.TFX.equals(l_compFxConditionParams.getExtConnectSystemCode()))
        {
            boolean l_blnFlag = true;
            try
            {
                //validate�ڑ�����
                l_fxDataControlService.validateSetup(l_soapConnectPrefRpcParams);
            }
            catch(WEB3BaseException l_ex)
            {
                //validate�ڑ������ŃG���[�ƂȂ����ꍇ�A�ȉ����Z�b�g����TFX�̐ڑ��������I������B
                //GFT���ʒʒm�d�����ׂ̎�t���ʃR�[�h.�hGFT�V�X�e���N���G���[�h(00000199)�Ƃ���B
                l_strResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199;
                //��t���ʃR�[�h���hSOAP�ڑ��m�F�G���[�h(9995)�Ƃ���
                l_strReturnValue = WEB3GftSoapResultCodeDef.SOAP_CONN_CONFIRM_ERROR;
                l_blnFlag = false;
            }
            if (l_blnFlag)
            {
                //sendSOAP���b�Z�[�W(GFT�˗��d������, SoapConnectPrefRpcParams)
                //[����]
                //�d�����ׁFFX�ւ̐U�ֈ˗����X�|���X.GFT�˗��d������
                //SOAP�v���t�@@�����X�F(��)�Ŏ擾�����O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���jparams
                try
                {
                    l_strReturnValue =
                        l_fxDataControlService.sendSoapMessage(
                            l_fxTransToFXAskingResponse.fxGftAskingTelegramUnit,
                            l_soapConnectPrefRpcParams);

                    //getSOAPTFX��t���ʃR�[�h(String)
                    //[����]
                    //��t���ʃR�[�h�F sendSOAP���b�Z�[�W()�̖߂�l
                    l_strResultCode =
                        l_fxDataControlService.getSoapTFXAcceptResultCode(
                            l_strReturnValue);
                }
                catch(WEB3BaseException l_ex)
                {
                    //�E�h�O���V�X�e���ڑ��G���[�h�̗�O���X���[�����ꍇ�́A
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_02398.equals(l_ex.getErrorInfo()))
                    {
                        //GFT���ʒʒm�d�����ׂ̎�t���ʃR�[�h.�hGFT�ڑ��G���[�h�Ƃ���B
                        l_strResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                        //��t���ʃR�[�h���h�ڑ��G���[�i�V�X�e���G���[�j�h�Ƃ���
                        l_strReturnValue = WEB3GftSoapResultCodeDef.CONNECT_ERROR;
                    }
                    //�E�h�\�����Ȃ��V�X�e���G���[�h�̗�O���X���[�����ꍇ�́A
                    else if(WEB3ErrorCatalog.SYSTEM_ERROR_80002.equals(l_ex.getErrorInfo()))
                    {
                        //GFT���ʒʒm�d�����ׂ̎�t���ʃR�[�h.�hGFT�ڑ��G���[�h�Ƃ���B
                        l_strResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                        //��t���ʃR�[�h���h�ڑ��G���[�i�V�X�e���G���[�j�h�Ƃ���
                        l_strReturnValue = WEB3GftSoapResultCodeDef.CONNECT_ERROR;
                    }
                }
            }

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit = l_fxTransToFXAskingResponse.fxGftAskingTelegramUnit;
            //updateGFT�U�֏�(String, String, String, String)
            //�،���ЃR�[�h�F FX�ւ̐U�ֈ˗����X�|���X.GFT�˗��d������.��ЃR�[�h 
            //���X�R�[�h�F FX�ւ̐U�ֈ˗����X�|���X.GFT�˗��d������.���X�R�[�h 
            //���ʃR�[�h�F FX�ւ̐U�ֈ˗����X�|���X.GFT�˗��d������.���ʃR�[�h
            //��t���ʃR�[�h�F
            //�@@�@@validate�ڑ������ŃG���[���������Ă��Ȃ��ꍇ�AsendSOAP
            //    ���b�Z�[�W()�̖߂�l�̎�t���ʃR�[�h
            //�@@�@@validate�ڑ������ŃG���[�ƂȂ����ꍇ�Avalidate�ڑ������ŃZ�b�g�����t���ʃR�[�h
            l_aioTransChangeConnection.updateGFTTransferStatus(
                l_fxGftAskingTelegramUnit.institutionCode,
                l_fxGftAskingTelegramUnit.branchCode,
                l_fxGftAskingTelegramUnit.requestNumber,
                l_strReturnValue);
            
            //createGFT���ʒʒm�d������
            WEB3FXConnCommonService l_fxExtConnCommonProcessService =
                (WEB3FXConnCommonService)Services.getService(WEB3FXConnCommonService.class);
            //[����] 
            //GFT�˗��d�����ׁF FX�ւ̐U�ֈ˗����X�|���X.GFT�˗��d������
            //FX�������ꗗ�F null
            //��t���ʃR�[�h�F
            //�@@�@@validate�ڑ������ŃG���[���������Ă��Ȃ��ꍇ�A getSOAPTFX��t���ʃR�[�h()�̖߂�l
            // �@@�@@validate�ڑ������ŃG���[�ƂȂ����ꍇ�Avalidate�ڑ������ŃZ�b�g�����t���ʃR�[�h
            l_fxGftResultNoticeTelegramUnit =
                l_fxExtConnCommonProcessService.createGftResultNoticeTelegramUnit(l_fxGftAskingTelegramUnit, null, l_strResultCode);
        }
        //��Е�FX�V�X�e������Params.�O���ڑ��V�X�e���R�[�h = '01�FGFT' �̏ꍇ
        else if (WEB3ExtConnectSystemCodeDef.GFT.equals(l_compFxConditionParams.getExtConnectSystemCode()))
        {
            //do�U�֎��s
            //[����]
            //��Е�FX�V�X�e������Params�F get��Е�FX�V�X�e������()�̖߂�l
            //GFT�˗��d�����ׁF FX�ւ̐U�ֈ˗����X�|���X.GFT�˗��d������
            l_fxGftResultNoticeTelegramUnit =
                l_aioTransChangeConnection.doTransfer(l_compFxConditionParams, l_fxTransToFXAskingResponse.fxGftAskingTelegramUnit);
        }

        //1.15 (*4)�C���X�^���X����
        WEB3FXTransToFXCompleteRequest l_fxTransToFXCompleteRequest = new WEB3FXTransToFXCompleteRequest();

        //1.16 (*5)�v���p�e�B�Z�b�g
        //GFT���ʒʒm�d�����ׁF �����������ʒʒm�d������
        l_fxTransToFXCompleteRequest.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
        //����ID�F FX�ւ̐U�ֈ˗����X�|���X.����ID
        l_fxTransToFXCompleteRequest.orderId = l_fxTransToFXAskingResponse.orderId;
        //�m�F���������F FX�ւ̐U�ֈ˗����X�|���X.�m�F��������
        l_fxTransToFXCompleteRequest.checkDate = l_fxTransToFXAskingResponse.checkDate;
        //�Ïؔԍ��FFX�ւ̐U�֊������N�G�X�g�iSOAP�ڑ��j.�Ïؔԍ�
        l_fxTransToFXCompleteRequest.password = l_request.password;
        //FX�V�X�e���R�[�h�FFX�ւ̐U�֊������N�G�X�g�iSOAP�ڑ��j.FX�V�X�e���R�[�h
        l_fxTransToFXCompleteRequest.fxSystemCode = l_request.fxSystemCode;

        //1.17 submit����(FX�ւ̐U�֊������N�G�X�g)
        WEB3FXTransToFXCompleteResponse l_fxTransToFXCompleteResponse = this.submitOrder(l_fxTransToFXCompleteRequest);

        //1.18 createResponse( )
        WEB3FXTransToFXCompleteSoapResponse l_response =
            (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();

        //1.19 (*6)�v���p�e�B�Z�b�g
        //�X�V���ԁF FX�ւ̐U�֊������X�|���X.�X�V����
        l_response.lastUpdatedTimestamp = l_fxTransToFXCompleteResponse.lastUpdatedTimestamp;
        //���ʔԍ��F FX�ւ̐U�֊������X�|���X.���ʔԍ�
        l_response.orderActionId = l_fxTransToFXCompleteResponse.orderActionId;
        //��n���F FX�ւ̐U�֊������X�|���X.��n��
        l_response.deliveryDate = l_fxTransToFXCompleteResponse.deliveryDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (FX�ւ̐U��TransactionCallback�N���X)<BR>
     */
    public class WEB3FXTransToFXTransactionCallback implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3FXTransToFXTransactionCallback.class);

        /**
         * �f�t�H���g�R���X�g���N�^<BR>
         * ����.���N�G�X�g�f�[�^���Y���̕ϐ��ɕۑ�����B<BR>
         * @@param l_request - (���N�G�X�g�f�[�^)<BR>
         * @@return WEB3FXTransToFXTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3FXTransToFXTransactionCallback(WEB3FXTransToFXAskingRequest l_request)
        {
            this.l_fxTransToFXAskingRequest = l_request;
        }

        /**
         * (FX�ւ̐U�ֈ˗����N�G�X) <BR>
         * FX�ւ̐U�ֈ˗����N�G�X <BR>
         */
        public WEB3FXTransToFXAskingRequest l_fxTransToFXAskingRequest;
        
        /**
         * �U�ֈ˗��E�o�^�������s���B <BR>
         *  <BR>
         * �P�j���������b�N����B <BR>
         *  <BR>
         *    �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B <BR>
         *  <BR>
         *    ��������OpLoginSecurityService���ҏW�B <BR>
         *  <BR>
         * �Q�jFX�ւ̐U�փT�[�r�XImpl.start����()���R�[������B <BR>
         *  <BR>
         *    [����] <BR>
         *    ���N�G�X�g�f�[�^�F this.���N�G�X�g�f�[�^ <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process() throws 
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            String STR_METHOD_NAME = " process()";
            l_log.entering(STR_METHOD_NAME);

            WEB3FXTransToFXAskingResponse l_fxTransToFXAskingResponse = null;
            //�U�ֈ˗��E�o�^�������s���B
            //�P�j���������b�N����B
            //   �g���A�J�E���g�}�l�[�W��.lock����()���R�[������B
            //   ��������OpLoginSecurityService���ҏW�B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            try
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //�،���ЃR�[�h���擾����
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                //���X�R�[�h���擾����
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                //�����R�[�h���擾����
                String l_accountCode = l_mainAccount.getAccountCode(); 
                
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //�Q�jFX�ւ̐U�փT�[�r�XImpl.start����()���R�[������B
                //   [����]
                //   ���N�G�X�g�f�[�^�F this.���N�G�X�g�f�[�^
                l_fxTransToFXAskingResponse = startOrder(this.l_fxTransToFXAskingRequest);
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_log.entering(STR_METHOD_NAME);
            return l_fxTransToFXAskingResponse;
        }
    }
    
    /**
     * (get�����敪)<BR>
     * �����敪�ɃZ�b�g����l��ݒ肷��B 
     * <BR>
     * [�߂�l] <BR> 
     * String�F�����敪<BR> 
     * <BR>
     * �P�j�ȉ��̎菇�ŏ����敪��ݒ肷��B<BR> 
     * �����DFX�V�X�e���R�[�h�F06�iHits��OP�U�ցj�̏ꍇ<BR> 
     * �@@�EFX�ւ̐U�ւ̏ꍇ�A06�i�o���F��OP�j��ԋp����B<BR> 
     * <BR>
     * �Q�j����ȊO�̏ꍇ�́A04�i�o���FFX�j��ԋp����B<BR> 
     * ��������null�̏ꍇ���܂ށB<BR>  
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     * @@return string
     * @@throws WEB3BaseException 
     * 
     */
    public String getOperationDiv(String l_strFxSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOperationDiv(String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);        
        
        //�����DFX�V�X�e���R�[�h�F06�i��OP�j�̏ꍇ 
        //�@@�EFX�ւ̐U�ւ̏ꍇ�A06�i�o���F��OP�j��ԋp����B  
        if (WEB3FxSystemCodeDef.HITS_FUOP_TRANSFER.equals(l_strFxSystemCode))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GftMessageOperationDef.CASH_OUT_FUOP;        
        }
        //����ȊO�̏ꍇ�́A04�i�o���FFX�j��ԋp����B 
        //��������null�̏ꍇ���܂ށB 
        log.exiting(STR_METHOD_NAME);
        return WEB3GftMessageOperationDef.CASH_OUT_FX;
    }

    /**
     * (is��n��)<BR>
     * ��n���Z�b�g�𔻒肷��B <BR> 
     * <BR>
     * [�߂�l] <BR> 
     * true�F��n�����Z�b�g����B <BR>
     * false�F��n�����Z�b�g���Ȃ��B <BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>  
     * <BR>
     * [����]  <BR>
     * ���XID = ������.���XID <BR> 
     * �v���t�@@�����X�� = "fx.deliverydate.insert.check"<BR>  
     * �v���t�@@�����X���̘A�� = 1  <BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h��n�����Z�b�g����h �̏ꍇ�Atrue ��ԋp����B<BR>  
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B<BR>  
     * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR>
     * <BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isDeliveryDate(long l_lngBranchId) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = 
            "isDeliveryDate(long l_lngBranchId)";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B  
        //[����]  
        //���XID = ������.���XID  
        //�v���t�@@�����X�� = "fx.deliverydate.insert.check"  
        //�v���t�@@�����X���̘A�� = 1 
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(              
                l_lngBranchId,
                WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
                1);
        } 
        catch (DataNetworkException l_dqex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�擾�������R�[�h�̒l == null �̏ꍇ�Afalse��ԋp����B
        if(l_branchReferencesRow == null)
        {
        	log.exiting(STR_METHOD_NAME);
        	return false;
        }
        
        //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h��n�����Z�b�g����h �̏ꍇ�Atrue ��ԋp����B
        if (WEB3FxDeliveryDateInsertCheckDef.CHECK.equals(l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�R�j����ȊO�̏ꍇ�́Afalse��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        log.exiting(STR_METHOD_NAME);
        return false;        
    }
}
@
