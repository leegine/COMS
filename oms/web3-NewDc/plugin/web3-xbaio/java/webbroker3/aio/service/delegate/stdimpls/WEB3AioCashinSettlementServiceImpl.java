head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettlementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ψ˗��T�[�r�X�����N���X(WEB3AioCashinSettlementServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���z (���u) �V�K�쐬
                   2004/10/23 ������ (���u) ���r���[
                   2005/1/11 ���E (���u) �c�Ή�
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import webbroker3.aio.WEB3AioCompanySettleInstitutionConditions;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.message.WEB3AioCashinConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinConfirmResponse;
import webbroker3.aio.message.WEB3AioCashinSettlementRequest;
import webbroker3.aio.message.WEB3AioCashinSettlementResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettlementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���ψ˗��T�[�r�XImpl)<BR>
 * ���ψ˗��T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettlementServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashinSettlementService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettlementServiceImpl.class);    
    
    /**
     * ���ψ˗��T�[�r�X�����{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Astart����()<BR>
     * ���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F26055035D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        //���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Astart����()
        if (l_request instanceof WEB3AioCashinConfirmRequest)
        {
            l_response = 
                validateSettlement((WEB3AioCashinConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3AioCashinSettlementRequest)
        {
            l_response =
                startSettlement((WEB3AioCashinSettlementRequest)l_request);
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
     * (validate����)<BR>
     * ���ψ˗��̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�I�����C���������ψ˗��jvalidate���ρv  �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C�������j���ψ˗� ( )�v) <BR>
     * �@@: �i�I�����C�������j���ψ˗� / �i�I�����C���������ψ˗��jvalidate����<BR>   
     *    �C���X�^���X�̐����Ɏ��s�����ꍇ�́A<BR>
     *     ���N�G�X�g�f�[�^�̌��ϋ@@��ID�ɖ�肪������̂Ƃ��āA<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00763<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C�������j���ψ˗� ( )�v) <BR>
     * �@@�@@�@@�@@: �i�I�����C�������j���ψ˗� / �i�I�����C���������ψ˗��jvalidate����<BR>   
     *     �߂�l�� false �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00826<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AioCashinConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F2631E00DC
     */
    protected WEB3AioCashinConfirmResponse validateSettlement(
        WEB3AioCashinConfirmRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "validateSettlement(WEB3AioCashinConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����
        //[����] 
        //�⏕�����^�C�v�F1�i�a��������j 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                
        //1.3 �������ʃ`�F�b�N�����{����
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        FinApp l_finApp = GtlUtils.getFinApp();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //l_aioOrderManager.validateOrder(l_subAccount);
		
		//1.3 ������t�\���ǂ����̃`�F�b�N���s��
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 ��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        
        //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        String l_strPaySchemeId = l_request.paySchemeId;
        //�C���X�^���X�̐����Ɏ��s�����ꍇ�́A
        //���N�G�X�g�f�[�^�̌��ϋ@@��ID�ɖ�肪������̂Ƃ��āA��O���X���[����
        WEB3AioCompanySettleInstitutionConditions l_aioCompanySettleInstitutionConditions = null;
        try
        {
            l_aioCompanySettleInstitutionConditions =
                new WEB3AioCompanySettleInstitutionConditions(
                    l_strInstitutionCode, l_strBranchCode, l_strPaySchemeId);      
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        //1.5 ���ϋ@@�ւ���t���ԓ����ǂ������`�F�b�N����
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.���X�R�[�h 
        //���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        //�߂�l�� false �̏ꍇ�A
        //��O���X���[����
        if (!l_aioOrderManager.validatePaySchemeAcceptPossible(
            l_strInstitutionCode,
            l_strBranchCode,
            l_request.paySchemeId))
        {
            log.debug("���ϋ@@�ւ���t���ԓ����ǂ������`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00826,
                this.getClass().getName() + "." + l_strMethodName,
                "���ϋ@@�ւ���t���ԊO");
        }
        
        //1.6 �����z�̑Ó������`�F�b�N����
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        //�����z�F���N�G�X�g�f�[�^.�����z 
        String l_strCashinAmt = l_request.cashinAmt;
        l_aioOrderManager.validateCreditAmount(
            l_subAccount, l_strPaySchemeId, Double.parseDouble(l_strCashinAmt));
        
        //1.7 ��g���ϋ@@�փC���X�^���X�𐶐�����
        //[����] 
        //���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        WEB3AioSettleInstitution l_aioSettleInstitution =
            new WEB3AioSettleInstitution(l_strPaySchemeId);
            
        //1.8 ���ϋ@@�֖����擾����
        String l_strInstitutionName = l_aioSettleInstitution.getName();
        
        //========remain zhou-yong NO.2 begin =========
        
        //1.9 get�I�����C������������(String)
        //�A�C�e���̒�`
        //���ϋ@@�ւ̔��������擾����B
        //[����] 
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        Date l_onlineCashinBizDate = 
            l_aioOrderManager.getOnlineCashinBizDate(l_request.paySchemeId);
                
        //1.10 �����_�ł�1���̑������z���擾����
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        //�������Fget�I�����C������������()�̖߂�l  
        double l_dblTotalCreditAmount = 
            l_aioOrderManager.getTotalCreditAmount(
                l_subAccount, 
                l_strPaySchemeId, 
                l_onlineCashinBizDate);
            
        //1.11 �����_�ł�1���̓����񐔂��擾����
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        //�������F get�I�����C������������()�̖߂�l  
        long l_lngCashinOrderCount = 
            l_aioOrderManager.getCashinOrderCount(
                l_subAccount, 
                l_strPaySchemeId, 
                l_onlineCashinBizDate);
        
        //1.12 ���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinConfirmResponse l_aioCashinConfirmResponse = 
            (WEB3AioCashinConfirmResponse)l_request.createResponse();
            
        //1.13 �ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
        //���X�|���X.���ϋ@@��ID = ���N�G�X�g�f�[�^.���ϋ@@��ID
        l_aioCashinConfirmResponse.paySchemeId = l_strPaySchemeId;
        
        //���X�|���X.���ϋ@@�֖� = ��g���ϋ@@��.get����()�̖߂�l
        l_aioCashinConfirmResponse.paySchemeName = l_strInstitutionName;
        
        //���X�|���X.�����z = ���N�G�X�g�f�[�^.�����z
        l_aioCashinConfirmResponse.cashinAmt = l_strCashinAmt;
        
        //���X�|���X.������ = AIO�����}�l�[�W��.get����������()�̖߂�l + 1
        l_aioCashinConfirmResponse.cashinTimes = String.valueOf(l_lngCashinOrderCount + 1);
        
        //���X�|���X.�������v���z = AIO�����}�l�[�W��.get�������z()�̖߂�l + ���N�G�X�g�f�[�^�����z
        l_aioCashinConfirmResponse.cashinTotalAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblTotalCreditAmount + Double.parseDouble(l_strCashinAmt));
        
        //���X�|���X.�m�F�������� = get�I�����C������������()�̖߂�l 
        l_aioCashinConfirmResponse.checkDate = l_onlineCashinBizDate;

        //========remain zhou-yong NO.2 end =========
        
        log.exiting(l_strMethodName);
        
        return l_aioCashinConfirmResponse;
    }
    
    /**
     * (start����)<BR>
     * ���ψ˗��������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>s
     * �u�i�I�����C���������ψ˗��jstart���ρv  �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C�������j���ψ˗� ( )�v)<BR>
     * �@@�@@: �i�I�����C�������j���ψ˗� / �i�I�����C���������ψ˗��jstart����<BR>   
     *     �C���X�^���X�̐����Ɏ��s�����ꍇ�́A
           ���N�G�X�g�f�[�^�̌��ϋ@@��ID�ɖ�肪������̂Ƃ��āA<BR>
     *     ��O���X���[����B<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �i�I�����C�������j���ψ˗� ( )�v)<BR>
     * �@@�@@: �i�I�����C�������j���ψ˗� / �i�I�����C���������ψ˗��jstart����<BR>  
     *     �߂�l�� false �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00826<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AioCashinSettlementResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F2633302A1
     */
    protected WEB3AioCashinSettlementResponse startSettlement(WEB3AioCashinSettlementRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "startSettlement(WEB3AioCashinSettlementRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����
        //[����] 
        //�⏕�����^�C�v�F1�i�a��������j 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 �������ʃ`�F�b�N�����{����
        //[����] 
        //�⏕�����Fget�⏕����()�̖߂�l 
        FinApp l_finApp = GtlUtils.getFinApp();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //l_aioOrderManager.validateOrder(l_subAccount);
		// 1.3 ������t�\���ǂ����̃`�F�b�N���s���B 
		WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.4 ��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        String l_strPaySchemeId = l_request.paySchemeId;
        //�C���X�^���X�̐����Ɏ��s�����ꍇ�́A���N�G�X�g�f�[�^�̌��ϋ@@��ID�ɖ�肪������̂Ƃ��āA��O���X���[����            
        WEB3AioCompanySettleInstitutionConditions l_aioCompanySettleInstitutionConditions = null;
        try
        {
            l_aioCompanySettleInstitutionConditions =
                new WEB3AioCompanySettleInstitutionConditions(
                l_strInstitutionCode, l_strBranchCode, l_strPaySchemeId);      
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        //1.5 ���ϋ@@�ւ���t���ԓ����ǂ������`�F�b�N����
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.���X�R�[�h 
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        //�߂�l�� false �̏ꍇ�A
        //��O���X���[����
        boolean l_validatePayAcceptPossible = 
            l_aioOrderManager.validatePaySchemeAcceptPossible(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strPaySchemeId);
        if (!l_validatePayAcceptPossible)
        {
            log.debug("���ϋ@@�ւ���t���ԓ����ǂ������`�F�b�N");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00826,
                this.getClass().getName() + "." + l_strMethodName,
                "���ϋ@@�ւ���t���ԊO");   
        }
        
        //=========remain zhou-yong NO.3 begin ===========
        
        //1.6 get�I�����C������������(String)
        //�A�C�e���̒�`
        //���ϋ@@�ւ̔��������擾����B
        //[����] 
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        Date l_onlineCashinBizDate = 
            l_aioOrderManager.getOnlineCashinBizDate(l_request.paySchemeId);
        
        //�擾���������� != ���N�G�X�g�f�[�^.�m�F�������� �̏ꍇ�A
        //��O���X���[����B
        if(WEB3DateUtility.compare(l_request.checkDate, l_onlineCashinBizDate) != 0)
        {
            log.debug("�擾���������� != ���N�G�X�g�f�[�^.�m�F��������");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                this.getClass().getName() + "." + l_strMethodName,
                "�擾����������[" + l_onlineCashinBizDate +  "] != " + 
                "���N�G�X�g�f�[�^.�m�F��������["+ l_request.checkDate + "]");   

        }
        
        //=========remain zhou-yong NO.3 end ===========
        
        //1.7 �����z�̑Ó������`�F�b�N����
        //[����] 
        
        //�⏕�����Fget�⏕����()�̖߂�l 
        //���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        //�����z�F���N�G�X�g�f�[�^.�����z
        String l_strCashinAmt = l_request.cashinAmt;
        
        l_aioOrderManager.validateCreditAmount(
            l_subAccount, l_strPaySchemeId, Double.parseDouble(l_strCashinAmt));
        
        //1.8 �㗝���͎҃I�u�W�F�N�g���擾����
        Trader l_trader = this.getTrader();
        
        //1.9 �p�X���[�h�̃`�F�b�N���s��
        //[����] 
        //�㗝���͎ҁFget�㗝���͎�()�̖߂�l 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //�p�X���[�h�F���N�G�X�g�f�[�^.�Ïؔԍ�
        String l_strPassword = l_request.password;
        
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, l_strPassword); 
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�p�X���[�h�̃`�F�b�N���s��");
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + l_strMethodName);                          
        }
        
        //1.10 �V�K�̎��ʃR�[�h���擾����
        //[����] 
        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        //�����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.CASH);
        
        //=======remain zhou-yong NO.1 begin ===========
        
		//1.11 ���������擾����B 
		Date l_datOrderBizDate =  
			WEB3GentradeTradingTimeManagement.getOrderBizDate();
		log.debug("���������擾���� = " + l_datOrderBizDate);           

        //1.12 ���Z�@@�֘A�g���o���󋵃e�[�u���Ƀ��R�[�h��ǉ�����
        //�m�����n 
        //�㗝���͎ҁFget�㗝���͎�()�̖߂�l 
        //�⏕�����Fget�⏕����()�̖߂�l 
        //���ϋ@@��ID�F���N�G�X�g�f�[�^.���ϋ@@��ID 
        //���z�F���N�G�X�g�f�[�^.�������z 
        //�������Fget������()�̖߂�l
        //���ʃR�[�h�Fget�V�K���ʃR�[�h()�̖߂�l 

        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService)Services.getService(WEB3AioMultiBankSettleControlService.class);
        l_aioMultiBankSettleControlService.insertCashTransSituation(
            l_trader, 
            l_subAccount, 
            l_strPaySchemeId, 
            l_strCashinAmt, 
			l_datOrderBizDate, 
            l_strNewNumber);
        
        //1.13 create���ψ˗�URL(PR�w�ێ����, SubAccount, String, String)
        //�A�C�e���̒�`
        //���ψ˗��ɂă��_�C���N�g����URL�𐶐�����B
        //�m�����n 
        //PR�w�Z�b�V����ID�F ���N�G�X�g�f�[�^.PR�w�ێ���� 
        //�⏕�����F get�⏕����()�̖߂�l 
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        //���ʃR�[�h�F get�V�K���ʃR�[�h()�̖߂�l 
        
        String l_strSettlementRequestURL = 
            l_aioMultiBankSettleControlService.createSettlementRequestURL(
                l_request.prSessionUnit,
                l_subAccount,
                l_strPaySchemeId,
                l_strNewNumber);    
            
        //========remain zhou-yong NO.1 end ===========
        
        //1.14 ���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinSettlementResponse l_aioCashinSettlementResponse = 
            (WEB3AioCashinSettlementResponse)l_request.createResponse();
            
        //1.15 �ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
        //���X�|���X.URL = create���ψ˗�URL()�̖߂�l
        l_aioCashinSettlementResponse.url = l_strSettlementRequestURL;

        log.exiting(l_strMethodName);
        return l_aioCashinSettlementResponse;
    }
}
@
