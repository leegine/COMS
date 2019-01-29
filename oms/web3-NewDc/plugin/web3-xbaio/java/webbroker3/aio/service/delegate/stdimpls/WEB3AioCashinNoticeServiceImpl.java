head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���T�[�r�XImpl(WEB3AioCashinNoticeServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬  
                   2004/10/25 ���� (���u) ���r���[ 
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.aio.data.DepositInformParams;
import webbroker3.aio.message.WEB3AioCashinNoticeCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeCompleteResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeMailSendService;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MailSendDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����A���T�[�r�XImpl)<BR>
 * �����A���T�[�r�X�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashinNoticeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeServiceImpl.class);
    
    /**
     * �����A���T�[�r�X���������{����B <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�A��()�܂��́A<BR>
     * submit�A��()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EBFBFA03AA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (l_request instanceof WEB3AioCashinNoticeConfirmRequest)
        {
            WEB3AioCashinNoticeConfirmResponse l_aioCashinNoticeConfirmResponse = 
                this.validateNotice((WEB3AioCashinNoticeConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
            return l_aioCashinNoticeConfirmResponse;
        }
        else if(l_request instanceof WEB3AioCashinNoticeCompleteRequest)
        {
            WEB3AioCashinNoticeCompleteResponse l_aioCashinNoticeCompleteResponse = 
                this.submitNotice((WEB3AioCashinNoticeCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_aioCashinNoticeCompleteResponse;
        }
        else
        {
            log.debug(" �p�����[�^�l���s������I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (validate�A��)<BR>
     * �����A���̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����A���jvalidate�A���v  �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �����A�� �v<BR>
     * �i�����A���jvalidate�A��<BR>
     *    : 1.6) WEB3StringTypeUtility.isMailAddress()���R�[������B <BR>
     *     �߂�l��false�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00777<BR>
     * <BR>
     *  ========================================================<BR>
     * <BR>
     *  =========================================================<BR>
     * �@@�@@:  1.10.���Z�@@��(String, String)<BR>   
     *     �C���X�^���X���擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00778<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AioCashinNoticeConfirmResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC0503AA
     */
    protected WEB3AioCashinNoticeConfirmResponse validateNotice(WEB3AioCashinNoticeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "validateNotice(WEB3AioCashinNoticeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 1.2)������t�\���ǂ����̃`�F�b�N���s���B 
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.3)�⏕�������擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.4)�،���ЃI�u�W�F�N�g���擾����
        Institution l_institution = l_subAccount.getInstitution();
        
        // 1.5)validate���[�����M(Institution)
        boolean l_blnIsMailSendEnable = this.validateMailSend(l_institution);
        
        // 1.6)���[���A�h���X�`�F�b�N 
        if (l_blnIsMailSendEnable && !WEB3StringTypeUtility.isMailAddress(l_request.emailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�`�F�b�N[" + l_request.emailAddress +
                "]�G���[");
        }
        
        // 1.7)�ڋq�I�u�W�F�N�g���擾����
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        
        // 1.8)�����ԍ��i�ڋq�R�[�h�j���擾����
        String l_strAccountCode = l_gentradeMainAccount.getAccountCode().substring(0, 6);
        
        // 1.9)�ڋq�����擾����B
        String l_personNameDetails = l_gentradeMainAccount.getDisplayAccountName();
        
        
        // 1.10)���Z�@@�փC���X�^���X�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �،����.getInstitutionCode() 
        //���Z�@@�փR�[�h�F ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h
        WEB3GentradeFinInstitution l_gentradeFinInstitution = 
            new WEB3GentradeFinInstitution(
                l_institution.getInstitutionCode(),
                l_request.finInstitutionCode);
        
        // 1.11)���X�|���X�f�[�^�𐶐�����
        WEB3AioCashinNoticeConfirmResponse l_aioCashinNoticeConfirmResponse
            = (WEB3AioCashinNoticeConfirmResponse) l_request.createResponse();
        
        //���X�|���X.�ڋq�� = �ڋq.get�ڋq�\����()�̖߂�l
        l_aioCashinNoticeConfirmResponse.accountName = l_personNameDetails;
        
        //���X�|���X.�ڋq�R�[�h = �ڋq.getAccountCode()�̖߂�l
        l_aioCashinNoticeConfirmResponse.accountCode = l_strAccountCode;
        
        //���X�|���X.�U���� = ���N�G�X�g�f�[�^.�U����
        l_aioCashinNoticeConfirmResponse.transferDate = 
            WEB3DateUtility.formatDate(l_request.transferDate,"yyyyMMdd");
        
        //���X�|���X.�U������Z�@@�փR�[�h = ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h
        l_aioCashinNoticeConfirmResponse.finInstitutionCode = l_request.finInstitutionCode;
        
        //���X�|���X.�U������Z�@@�֖� = ���Z�@@��.get���Z�@@�֖��i�����j()�̖߂�l
        l_aioCashinNoticeConfirmResponse.finInstitutionName 
            = l_gentradeFinInstitution.getFinInstitutionNameKanji();
        
        //���X�|���X.�������z = ���N�G�X�g�f�[�^.�������z
        l_aioCashinNoticeConfirmResponse.cashinAmt = l_request.cashinAmt;
        
        //���X�|���X.���[���A�h���X = ���N�G�X�g�f�[�^.���[���A�h���X
        l_aioCashinNoticeConfirmResponse.emailAddress = l_request.emailAddress;
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeConfirmResponse;
    }
    
    /**
     * (submit�A��)<BR>
     * �����A���̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����A���jsubmit�A���v  �Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(���o���T�[�r�X���f��) / �����A�� �v<BR>
     * �i�����A���jsubmit�A�� )<BR>
     * �@@�@@:  1.5.validate����p�X���[�h<BR>   
     *     �p�X���[�h�`�F�b�N���G���[�������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00009<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *    : 1.8) WEB3StringTypeUtility.isMailAddress()���R�[������B <BR>
     *     �߂�l��false�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00777<BR>
     * <BR>
     *  ========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashinNoticeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC10005E
     */
    protected WEB3AioCashinNoticeCompleteResponse submitNotice(WEB3AioCashinNoticeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "submitNotice(WEB3AioCashinNoticeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        // 1.2)������t�\���ǂ����̃`�F�b�N���s���B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.3)�⏕�������擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        // 1.4)�㗝���͎҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();
        
        // 1.5)�p�X���[�h�̃`�F�b�N���s���B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);   
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, 
            l_request.password);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����" + 
                l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.6)�،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution= l_subAccount.getInstitution();
        
        // 1.7)�Y������،���Ђ������A�����[�����M�����{���Ă��邩�ǂ������`�F�b�N����B
        //[����] 
        //�،���ЁF getInstitution()�̖߂�l 
        boolean l_blnMailSend = this.validateMailSend(l_institution);
        
        // 1.8)���[���A�h���X�`�F�b�N 
        if (l_blnMailSend && !WEB3StringTypeUtility.isMailAddress(l_request.emailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�`�F�b�N[" + l_request.emailAddress +
                "]�G���[");
        }

        // 1.9)�ڋq�I�u�W�F�N�g���擾����B 
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
                
        // 1.10)�����ԍ��i�ڋq�R�[�h�j���擾����B 
        String l_strAccoundcode = l_mainAccount.getAccountCode();
        
        // 1.11)�ڋq�����擾����B 
        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount; 
        String l_personNameDetails = l_gentradeMainAccount.getDisplayAccountName();
        
        //1.12)���X�I�u�W�F�N�g���擾����B
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
        
        // 1.13)���Z�@@�փC���X�^���X�𐶐�����B 
        //[����] 
        //�،���ЃR�[�h�F �،����.getInstitutionCode() 
        //���Z�@@�փR�[�h�F ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h 
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        WEB3GentradeFinInstitution l_gentradeFinInstitution 
            = new WEB3GentradeFinInstitution(l_strInstitutionCode,                
                l_request.finInstitutionCode);
        
        // 1.14)�V�K�̎��ʃR�[�h���擾����B
        //[����] 
        //�،���ЃR�[�h�F �،����.getInstitutionCode()�̖߂�l 
        //���X�R�[�h�F ���X.getBranchCode()�̖߂�l 
        //�����^�C�v�F 5�i�����j 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class); 
        String l_strBranchCode = l_gentradeBranch.getBranchCode();
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.CASH);
        log.debug("�V�K�̎��ʃR�[�h = " + l_strNewNumber);
        log.debug("InstitutionCode = " + l_strInstitutionCode);
        log.debug("BranchCode = " + l_strBranchCode);
        
        
        //*1)�����A��Params�I�u�W�F�N�g�𐶐����A�ȉ��̂Ƃ���v���p�e�B���Z�b�g����B
        DepositInformParams l_depositInformParams = new DepositInformParams();
        
        //�����A��Params.�،���ЃR�[�h = �،����.getInstitutionCode()�̖߂�l
        l_depositInformParams.setInstitutionCode(l_strInstitutionCode);
        
        //�����A��Params.���X�R�[�h = ���X.getBranchCode()�̖߂�l
        l_depositInformParams.setBranchCode(l_strBranchCode);
        
        //�����A��Params.�ڋq�R�[�h = �ڋq.getAccountCode()�̖߂�l
        l_depositInformParams.setAccountCode(l_strAccoundcode);
        
        //�����A��Params.���ʃR�[�h = �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
        l_depositInformParams.setOrderRequestNumber(l_strNewNumber);
        
        //�����A��Params.��Ɠ��� = �V�X�e���^�C���X�^���v
        l_depositInformParams.setWorkedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�����A��Params.�U���� = ���N�G�X�g�f�[�^.�U����
        l_depositInformParams.setTransferDate(l_request.transferDate);
        
        //�����A��Params.���Z�@@�փR�[�h = ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h
        l_depositInformParams.setFinInstitutionCode(l_request.finInstitutionCode);
        
        //�����A��Params.�����z = ���N�G�X�g�f�[�^.�������z
        l_depositInformParams.setAmount(Long.parseLong(l_request.cashinAmt));
        
        try
        {
            // 1.15)�����A���e�[�u���ɓ����A��Params�̓��e��o�^����B
            //[����] 
            //�����A��Params�F �����A��Params�I�u�W�F�N�g 
            WEB3DataAccessUtility.insertRow(l_depositInformParams);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.16)���[�����M�������s���B 
        if(l_blnMailSend)
        {           
            WEB3AioCashinNoticeMailSendService l_aioCashinNoticeMailSendService =
            (WEB3AioCashinNoticeMailSendService) Services.getService(
                WEB3AioCashinNoticeMailSendService.class);
        
            l_aioCashinNoticeMailSendService.createMail(
                    l_depositInformParams, l_request.emailAddress);
        }
        
        // 1.17)���X�|���X�f�[�^�𐶐�����B
        WEB3AioCashinNoticeCompleteResponse l_aioCashinNoticeCompleteResponse = 
            (WEB3AioCashinNoticeCompleteResponse) l_request.createResponse();
        
        // 1.18) (*)�ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����
        //���X�|���X.�ڋq�� = �ڋq.get�ڋq�\����()�̖߂�l
        l_aioCashinNoticeCompleteResponse.accountName = l_personNameDetails;
        
        //���X�|���X.�ڋq�R�[�h = �ڋq.getAccountCode()�̖߂�l
        l_aioCashinNoticeCompleteResponse.accountCode = l_strAccoundcode.substring(0, 6);
        
        //���X�|���X.�U���� = ���N�G�X�g�f�[�^.�U����
        l_aioCashinNoticeCompleteResponse.transferDate = 
            WEB3DateUtility.formatDate(l_request.transferDate, "yyyyMMdd");
        
        //���X�|���X.�U������Z�@@�փR�[�h = ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h
        l_aioCashinNoticeCompleteResponse.finInstitutionCode = l_request.finInstitutionCode;
        
        //���X�|���X.�U������Z�@@�֖� = ���Z�@@��.get���Z�@@�֖��i�����j()�̖߂�l
        l_aioCashinNoticeCompleteResponse.finInstitutionName = l_gentradeFinInstitution.getFinInstitutionNameKanji();
        
        //���X�|���X.�������z = ���N�G�X�g�f�[�^.�������z
        l_aioCashinNoticeCompleteResponse.cashinAmt = l_request.cashinAmt;
        
        //���X�|���X.���[���A�h���X = ���N�G�X�g�f�[�^.���[���A�h���X
        l_aioCashinNoticeCompleteResponse.emailAddress = l_request.emailAddress;
        
        //���X�|���X.�X�V����= �����A��Params.��Ɠ���
        l_aioCashinNoticeCompleteResponse.lastUpdatedTimestamp = l_depositInformParams.worked_timestamp;
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeCompleteResponse;
    }
    
    /**
     * (validate���[�����M)<BR>
     * �Y������،���Ђ������A�����[����<BR>
     * ���M����Ƃ����ݒ�ɂȂ��Ă��邩�ǂ����ƃ`�F�b�N����B<BR>
     * <BR>
     * �P�j�،���Ѓe�[�u���̃��R�[�h���擾����B<BR>
     * <BR>
     *   ����.�،����.getDataSourceObject()<BR>
     * <BR>
     * �Q�j�،����Params.�����A�����[�����M = "0"�i�����{�j��<BR>
     * �ꍇ�́Afalse��Ԃ��B<BR>
     *    �،����Params.�����A�����[�����M = "1"�i���{�j��<BR>
     * �ꍇ�́Atrue��Ԃ��B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@return boolean<BR>
     * @@roseuid 40FDCFAB03D6
     */
    protected boolean validateMailSend(Institution l_institution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "validateMailSend(Institution l_institution)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�،���Ѓe�[�u���̃��R�[�h���擾����
        InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
        
        //�Q�j�،����Params.�����A�����[�����M = "0"�i�����{�j�̏ꍇ�́Afalse��Ԃ��B 
        //�،����Params.�����A�����[�����M = "1"�i���{�j�̏ꍇ�́Atrue��Ԃ��B
        if(WEB3MailSendDef.NOT_ENFORCEMENT.equals(l_institutionRow.getDepositInformMailSend()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else if(WEB3MailSendDef.ENFORCEMENT.equals(l_institutionRow.getDepositInformMailSend()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug(" �p�����[�^�l���s������I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
}
@
