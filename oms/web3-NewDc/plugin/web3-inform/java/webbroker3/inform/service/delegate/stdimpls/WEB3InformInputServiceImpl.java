head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�����̓T�[�r�XImpl(WEB3InformInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ExtMailProcParams;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformClientRequestService;
import webbroker3.inform.data.InformCtrlItemAttributeRow;
import webbroker3.inform.data.InformDivPreferencesRow;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformBlankDef;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.inform.message.WEB3InformCompleteResponse;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformConfirmResponse;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.inform.message.WEB3InformInputResponse;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.WEB3InformInputService;
import webbroker3.inform.util.WEB3InformColumnSpec;
import webbroker3.inform.util.WEB3InformTableSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�����̓T�[�r�XImpl)
 * �A�����̓T�[�r�X�����N���X
 */
public class WEB3InformInputServiceImpl
    extends WEB3InformClientRequestService
    implements WEB3InformInputService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformInputServiceImpl.class);

    /**
     * @@roseuid 41EE632C001F
     */
    public WEB3InformInputServiceImpl()
    {

    }

    /**
     * �A�����̓T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     *    get���͉��()<BR>
     *    validate�A��()<BR>
     *    submit�A��()<BR>
     * <BR>
     * ��L���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419DA13A026B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3InformInputRequest)
        {
            l_response = getInformInputDisplay((WEB3InformInputRequest)l_request);
        }
        else if (l_request instanceof WEB3InformConfirmRequest)
        {
            l_response = validateInform((WEB3InformConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3InformCompleteRequest)
        {
            l_response = submitInform((WEB3InformCompleteRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�l�s���B");            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A�����́jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.inform.message.WEB3InformInputResponse
     * @@roseuid 419DA1AB0299
     */
    protected WEB3InformInputResponse getInformInputDisplay(WEB3InformInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformInputDisplay(WEB3InformInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1 getLoginId
        //���O�C��ID���擾�ł����ꍇ�A���j���[��ʂ���A�N�Z�X���ꂽ
        //���̂Ɣ��f����B
        OpLoginSecurityService l_services = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        WEB3InformInputResponse l_response = null;
        long l_lngLoginId = 0;
        try
        {
            try
            {
                l_lngLoginId = l_services.getLoginId();       
                
                if (l_lngLoginId == 0)
                {
                    log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ");                
                }          
            }
            catch (IllegalSessionStateException l_ex)
            {          
                log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ");     
            }
            


            //1.3(*1)���j���[��ʂ���̃A�N�Z�X�i���Ƀ��O�C���ρj�̏ꍇ
            MainAccountRow l_mainAccountRow = null;
            String l_strAccountCode = "";
            if (l_lngLoginId != 0)
            {
                //1.2validate������t�\
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
                //1.3.1getAccountId( )(
                long l_lngAccountId = l_services.getAccountId();
                //1.3.2get�ڋq()(
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
                //1.3.3get�ڋq�\����( )(
                l_strAccountCode = l_mainAccount.getDisplayAccountName();
                //1.3.4getDataSourceObject(
                l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
                //1.4createResponse( )(
                l_response = (WEB3InformInputResponse)l_request.createResponse();
                //1.5(*2)�v���p�e�B�Z�b�g
                //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                //
                //�P�j���j���[��ʂ���A�N�Z�X�̏ꍇ
           
                //  ���X�|���X.���X�R�[�h = �ڋq�s�I�u�W�F�N�g.���X�R�[�h
                l_response.branchCode = l_mainAccountRow.getBranchCode();
                //  ���X�|���X.�ڋq�R�[�h = �ڋq�s�I�u�W�F�N�g.�ڋq�R�[�h
                l_response.accountCode = l_mainAccount.getDisplayAccountCode();
                //  ���X�|���X.�ڋq�� = get�ڋq�\����()�̖߂�l
                l_response.accountName = l_strAccountCode;
                //  ���X�|���X.���[���A�h���X = �ڋq�s�I�u�W�F�N�g.email�A�h���X
                l_response.mailAddress = l_mainAccountRow.getEmailAddress();
            }
            else
            {
                l_response = (WEB3InformInputResponse)l_request.createResponse();
                //�Q�j�z�[���y�[�W����A�N�Z�X�̏ꍇ

                //  ���X�|���X.���X�R�[�h = null
                l_response.branchCode = null;
                //  ���X�|���X.�ڋq�R�[�h = null
                l_response.accountCode = null;
                //  ���X�|���X.�ڋq�� = null
                l_response.accountName = null;
                //  ���X�|���X.���[���A�h���X = null
                l_response.mailAddress = null;
            }
        }
        catch(NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�A��)<BR>
     * �A���̐R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A�����́jvalidate�A���v �Q�ƁB
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.inform.message.WEB3InformConfirmResponse
     * @@roseuid 419DA13A0299
     */
    protected WEB3InformConfirmResponse validateInform(WEB3InformConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInform(WEB3InformConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1validate( 
        l_request.validate();
        //1.2�e��A��(�e��A�����)(
        WEB3Inform l_web3Inform = new WEB3Inform(l_request.informInfoUnit);
        //1.3validate�e��A�����( )(
        l_web3Inform.validateInformDetailInfoUnit();
        //1.4
        long l_lngLoginId = 0;
        OpLoginSecurityService l_services = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);  
        try
        {
            l_lngLoginId = l_services.getLoginId();       
            
            if (l_lngLoginId == 0)
            {
                log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ"); 
                //1.5
                l_context.setBranchCode(l_web3Inform.getBranchCode());
                WEB3GentradeTradingTimeManagement.setTimestamp();             
            }          
        }
        catch (IllegalSessionStateException l_ex)
        {          
            log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ");   
            //1.5
            l_context.setBranchCode(l_web3Inform.getBranchCode());
            WEB3GentradeTradingTimeManagement.setTimestamp();   
        }
        //1.6validate������t�\(
        WEB3GentradeTradingTimeManagement.validateOrderAccept(); 


        //1.7 get�t�����( )(
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_web3Inform.getInformAddInfoUnit();
        //1.6createResponse( 
        //1.7 (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //���X�|���X.�t����� = get�t�����()�̖߂�l
        WEB3InformConfirmResponse l_response = (WEB3InformConfirmResponse)l_request.createResponse();
        l_response.informAddUnit = l_informAddInfoUnit;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�A��)<BR>
     * �A���̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A�����́jsubmit�A���v �Q�ƁB
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3InformCompleteResponse
     * @@roseuid 419DA13A02B9
     */
    protected WEB3InformCompleteResponse submitInform(WEB3InformCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitInform(WEB3InformCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1validate( )(
        l_request.validate();
        //1.2�e��A��(�e��A�����)(
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);
        //1.3validate�e��A�����( )(
        l_inform.validateInformDetailInfoUnit();
        //1.4getLoginId( )(
        OpLoginSecurityService l_services = (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        Trader l_trader = null;
        WEB3GentradeSubAccount l_subAccount = null;
        long l_lngLoginId = 0;
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);  

        try
        {
            l_lngLoginId = l_services.getLoginId();       
                
            if (l_lngLoginId == 0)
            {
                log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ");  
                l_context.setBranchCode(l_inform.getBranchCode());     
                WEB3GentradeTradingTimeManagement.setTimestamp();          
            }          
        }
        catch (IllegalSessionStateException l_ex)
        {          
            log.info("�z�[���y�[�W����A�N�Z�X�̏ꍇ");     
            l_context.setBranchCode(l_inform.getBranchCode());     
            WEB3GentradeTradingTimeManagement.setTimestamp();  
        }
        //1.6validate������t�\( )(
        WEB3GentradeTradingTimeManagement.validateOrderAccept();



        
        //1.7���j���[��ʂ���A�N�Z�X�̏ꍇ        
        if (l_lngLoginId != 0)
        {       
            //1.7.1get�⏕����( )(
            l_subAccount = this.getSubAccount();
            //1.7.2get�㗝���͎�( )(
            l_trader = this.getTrader();
            
            long l_lngBranchId = l_subAccount.getWeb3GenBranch().getBranchId();
            String l_strBranchId = Long.toString(l_lngBranchId);
            String l_strInformDiv = l_inform.getInformDiv();
            
            if (this.isCheckTradePassword(l_strBranchId, l_strInformDiv))
            {
                //1.7.3validate����p�X���[�h(Trader, SubAccount, String)( 
                WEB3GentradeOrderValidator l_validator = new WEB3GentradeOrderValidator();
                OrderValidationResult l_result = l_validator.validateTradingPassword(l_trader,l_subAccount,l_request.password);
                if (l_result.getProcessingResult().isFailedResult())
                {
                    log.error(STR_METHOD_NAME + l_result.getProcessingResult().getErrorInfo().error_message);
                    throw new WEB3BaseException(
                        l_result.getProcessingResult().getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);   
                }  
            }

        }



        //1.8get�V�K���ʃR�[�h(String, String)(
        WEB3InformHostReqOrderNumberManageService l_hostReqOrderNumberManageService
            = (WEB3InformHostReqOrderNumberManageService)Services
                .getService(WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode = 
            l_hostReqOrderNumberManageService.getNewOrderRequestCode(l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.informType);

        //1.8saveNew�e��A��(String, String)(
        String l_strTraderCode = null;
        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }
        
        l_inform.saveNewInform(l_strTraderCode,l_strNewOrderRequestCode);
        //1.9createMail(�e��A�����)(
        VariousInformParams l_params = (VariousInformParams)l_inform.getDataSourceObject();
        VariousInformRow l_row = null;
        try
        {
            l_row = VariousInformDao.findRowByInstitutionCodeInformDivRequestNumberBranchCode
                (l_params.getInstitutionCode(),l_params.getInformDiv(),l_strNewOrderRequestCode,l_params.getBranchCode()
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        VariousInformParams l_newParams = new VariousInformParams(l_row);
        this.createMail(l_newParams);
        //1.10createResponse( )(
        WEB3InformCompleteResponse l_response = (WEB3InformCompleteResponse)l_request.createResponse();
        l_response.requestNumber = l_strNewOrderRequestCode;
        l_response.updateTimeStamp = l_newParams.getLastUpdatedTimestamp();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * ���[�����M�e�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h = ����.�A�����.�،���ЃR�[�h<BR>
     * ���M���[���敪 = "05" + ����.�A�����.�A�����<BR>
     * <BR>
     * �Q�j�擾�������[���e�[�u���̃��R�[�h����Loop�������s���B<BR>
     * <BR>
     * �Q�|�P�j���[�����M�e�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * ���X�V���e�ɂ��ẮADB�X�V�d�l�Q��<BR>
     * <BR>
     * �Q�|�Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * ���X�V���e�ɂ��ẮADB�X�V�d�l�Q��<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s
     * @@roseuid 41C81FEA03B4
     */
    protected void createMail(VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMail(VariousInformParams)";
        log.entering(STR_METHOD_NAME);
        //�P�j�ȉ��̏����ŁA���[���e�[�u�����烌�R�[�h���擾����B
        //
        //[����]
        //�،���ЃR�[�h = ����.�A�����.�،���ЃR�[�h
        //���M���[���敪 = "05" + ����.�A�����.�A�����
        //
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strQuery = "institution_code = ? ";
            l_strQuery += " and sendmail_div = ?";
        
            Object[] l_queryContainer = new Object[] {
                l_variousInformParams.getInstitutionCode(),
                WEB3SendmailDivDef.INFORM + l_variousInformParams.getInformDiv()};
                        
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                MailInfoRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
            //�Q�j�擾�������[���e�[�u���̃��R�[�h����Loop�������s���B
            //


            int l_lngLen = 0;
            if (l_lisRecords != null)
            {
                l_lngLen = l_lisRecords.size();
            }
            for (int i = 0;i < l_lngLen; i++)
            {
                MailProcParams l_mailProcParams = new MailProcParams();
                MailInfoParams l_mailInfoParams = (MailInfoParams)l_lisRecords.get(i);
                //�Q�|�P�j���[�����M�e�[�u���Ƀ��R�[�h��o�^����B
                //
                //���X�V���e�ɂ��ẮADB�X�V�d�l�Q��
                //
                //1   �،���ЃR�[�h     institution_code     �A�����.�،���ЃR�[�h
                l_mailProcParams.setInstitutionCode(l_variousInformParams.getInstitutionCode());
                //2   ���X�R�[�h       branch_code       �A�����.���X�R�[�h
                l_mailProcParams.setBranchCode(l_variousInformParams.getBranchCode());  
                //3   ���M���[���敪     sendmail_div    ���[���e�[�u��.���M���[���敪
                l_mailProcParams.setSendmailDiv(l_mailInfoParams.sendmail_div);
                //4   ����ID        discernment_id   ���[���e�[�u��.����ID
                l_mailProcParams.setDiscernmentId(l_mailInfoParams.discernment_id);
                //5   �����R�[�h       account_code     "�A�����.�����ԍ� != null �̏ꍇ�A�A�����.�����ԍ�;
                //�A�����.�����ԍ� == null �̏ꍇ�A"0000000"
                if (l_variousInformParams.getAccountCode()!= null)
                {
                    l_mailProcParams.setAccountCode(l_variousInformParams.getAccountCode());
                }
                else
                {
                    l_mailProcParams.setAccountCode("0000000");
                }
                //6   ���[��ID       mail_id                              18  �A�����.���ʃR�[�h
                l_mailProcParams.setMailId(Long.parseLong(l_variousInformParams.request_number));  
                //7   �N�����P        date_1                              null
                l_mailProcParams.setDate1(null);
                //8   �N�����Q        null
                l_mailProcParams.setDate2(null);
                //9   �N�����R        null
                l_mailProcParams.setDate3(null);
                //10  �N�����S        �A�����.�쐬����
                l_mailProcParams.setDate4(l_variousInformParams.created_timestamp);   
                //11  ����      quantity           18  null
                l_mailProcParams.setQuantity(null);
                //12  ���z      amount                  DECIMAL             18  null
                l_mailProcParams.setAmount(null);
                //13  ID      order_id             18  null
                l_mailProcParams.setOrderId(null);
                //14  �敪      division  1   null
                l_mailProcParams.setDivision(null);
                //15  ����1     name  50  �e��A���s.�ڋq��
                l_mailProcParams.setName1(l_variousInformParams.getAccountName());
                //16  ����2     name  50  null
                l_mailProcParams.setName2(null);
                //17  �d�q���[�����M�X�e�C�^�X        status1   0�F�������iEmail�����M�j
                l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND); 
                //18  �d�q���[�����M��       send_process_date_time                              null
                l_mailProcParams.setSendProcessDateTime(null);
                //19  �đ��敪        resend_status 1   null
                l_mailProcParams.setResendStatus(null);
                //20  �d�q���[���đ�����       resend_process_date_timeDATE                null
                l_mailProcParams.setResendProcessDateTime(null);
                //21  email�A�h���X       email_address  "���[���e�[�u��.���M��A�h���X == null �̏ꍇ�F
                //                      �A�����.���[���A�h���X���[���e�[�u��.���M��A�h���X != null �̏ꍇ�F���[���e�[�u��.���M��A�h���X"
                if (l_mailInfoParams.getSendAddress() == null)
                {
                    l_mailProcParams.setEmailAddress(l_variousInformParams.getEmailAddress()); 
                }
                else
                {
                    l_mailProcParams.setEmailAddress(l_mailInfoParams.getSendAddress());
                }
 
                //22  ���Memail�A�h���X     send_email_address  "���[���e�[�u��.���M��A�h���X == null �̏ꍇ�F���[���e�[�u��.���o�l
                //                      ���[���e�[�u��.���M��A�h���X != null �̏ꍇ�F�A�����.���[���A�h���X"
                if (l_mailInfoParams.getSendAddress() == null)
                {
                    l_mailProcParams.setSendEmailAddress(l_mailInfoParams.getMailSender()); 
                }
                else
                {
                    l_mailProcParams.setSendEmailAddress(l_variousInformParams.getEmailAddress());
                }
                //23  ����      subject          null
                l_mailProcParams.setSubject(null);
                //24  ���[���{��       mail_text          "�A�����.���l�Q���A�����.���l�Q=null�̏ꍇ�́A�u�����N"
                if (l_variousInformParams.getExtNote2() == null)
                {
                    l_mailProcParams.setMailText(WEB3InformBlankDef.BLANK);
                }
                else
                {
                    l_mailProcParams.setMailText(l_variousInformParams.getExtNote2());
                }

                //25  �폜�t���O       delete_flag                              1   0:FALSE�i�L���j
                l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE); 
                //26  �쐬����        created_timestamp                               ��������
                Timestamp l_timeStamp = GtlUtils.getSystemTimestamp();
                l_mailProcParams.setCreatedTimestamp(l_timeStamp);
                //27  �X�V����        last_updated_timestamp                              ��������
                l_mailProcParams.setLastUpdatedTimestamp(l_timeStamp);
 
                l_queryProcessor.doInsertQuery(l_mailProcParams); 

                //�Q�|�Q�j���[�����M�g���e�[�u���Ƀ��R�[�h��o�^����B
                //
                //���X�V���e�ɂ��ẮADB�X�V�d�l�Q��
                ExtMailProcParams l_extMailProcParams = new ExtMailProcParams();
                //�،���ЃR�[�h     institution_code  3   �A�����.�،���ЃR�[�h
                l_extMailProcParams.setInstitutionCode(l_variousInformParams.getInstitutionCode());
                //���X�R�[�h       branch_code                   3   �A�����.���X�R�[�h 
                l_extMailProcParams.setBranchCode(l_variousInformParams.getBranchCode()); 
                //���M���[���敪     sendmail_div  4   ���[���e�[�u��.���M���[���敪
                l_extMailProcParams.setSendmailDiv(l_mailInfoParams.getSendmailDiv()); 
                //����ID        discernment_id4   ���[���e�[�u��.����ID
                l_extMailProcParams.setDiscernmentId(l_mailInfoParams.getDiscernmentId());
                //�����R�[�h       account_code  7   "�A�����.�����ԍ� != null �̏ꍇ�A�A�����.�����ԍ�
                //�A�����.�����ԍ� == null �̏ꍇ�A"0000000"
                if (l_variousInformParams.getAccountCode() != null)
                {
                    l_extMailProcParams.setAccountCode(l_variousInformParams.getAccountCode());
                }
                else
                {
                    l_extMailProcParams.setAccountCode("0000000");
                }
 
                //���[��ID       mail_id                              18  �A�����.���ʃR�[�h  
                l_extMailProcParams.setMailId(Long.parseLong(l_variousInformParams.getRequestNumber()));
                WEB3InformTableSpec l_spec = new WEB3InformTableSpec();
                int l_intColumnLen = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName()).length;
                for (int j = 0;j < l_intColumnLen;j++)
                {
                    WEB3InformColumnSpec l_loopSpec = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName())[j];
                    if (!l_loopSpec.isCustomizeAble() || "ext_note2".equals(l_loopSpec.asHeader()))
                    {
                        continue;
                    }

                    Object l_valueObj = l_variousInformParams.getColumn(l_loopSpec.asHeader());
          
                    //���ږ�     item_name   "�A�����(�e��A��)�̗񕨗���
                    //���ΏۂƂȂ�̂́A�A�����.�敪�P�`�A�����.���l�P" 
                    l_extMailProcParams.setItemName(l_loopSpec.asHeader());

                    //���ړ��e�F�e��A���s�̍��ڂɊY������A�A���Ǘ����ڑ����e�[�u���̍��ڑ�����
                    //�P�j��ЁA���X�A�A����ʁA���ڕ������A���ڑ����l�ɊY�����鍀�ڑ�������set����B
                    StringBuffer l_strWhere = new StringBuffer();
                    l_strWhere.append(" institution_code = ? ");
                    l_strWhere.append(" and branch_code = ? ");
                    l_strWhere.append(" and inform_div = ? ");
                    l_strWhere.append(" and item_symbol_name = ? ");
                    l_strWhere.append(" and attribute_value = ? ");

                    Object[] l_objWhere = 
                    {
                        l_variousInformParams.getInstitutionCode(),
                        l_variousInformParams.getBranchCode(),
                        l_variousInformParams.getInformDiv(),
                        new String(l_loopSpec.asHeader()),
                        l_valueObj
                    };
                    List l_lisInformCtrlItemAttribute = 
                        l_queryProcessor.doFindAllQuery(
                            InformCtrlItemAttributeRow.TYPE,
                            l_strWhere.toString(),
                            l_objWhere);

                    String l_strAttributeName = null;

                    if (!l_lisInformCtrlItemAttribute.isEmpty())
                    {
                        //�Y�����R�[�h���擾�o�����ꍇ�A���ڑ�������set����B
                        l_strAttributeName = 
                            ((InformCtrlItemAttributeRow)l_lisInformCtrlItemAttribute.get(0)).getAttributeName();
                        l_extMailProcParams.setItemContents(l_strAttributeName);
                        
                    }
                    else
                    {
                        //�Q�j�P�j�ŊY�����Ȃ��ꍇ�́A���������̕��X�R�[�h��"000"�ɂ��āA�ēx�������A
                        //�@@�@@�Y�����鍀�ڑ�������set����B
                        l_objWhere[1] = "000";

                        l_lisInformCtrlItemAttribute = 
                            l_queryProcessor.doFindAllQuery(
                                InformCtrlItemAttributeRow.TYPE,
                                l_strWhere.toString(),
                                l_objWhere);

                        if (!l_lisInformCtrlItemAttribute.isEmpty())
                        {
                            //�Y�����R�[�h���擾�o�����ꍇ�A���ڑ�������set����B
                            l_strAttributeName = 
                                ((InformCtrlItemAttributeRow)l_lisInformCtrlItemAttribute.get(0)).getAttributeName();
                            l_extMailProcParams.setItemContents(l_strAttributeName);
                        
                        }
                        else
                        {
                            //�R�j�P�j�Q�j�ŊY���f�[�^�����������ꍇ�A���͒l��set����B
                            if (l_valueObj != null)
                            {
                                //���͒l�����l�^�̏ꍇ�A������ɕϊ�����B
                                l_extMailProcParams.setItemContents("" + l_valueObj);
                            }
                            else 
                            {
                                //���͒l��null�̏ꍇ�A�u�����N��set����B
                                l_extMailProcParams.setItemContents(WEB3InformBlankDef.BLANK);
                            }
                        }
                    }

                    //�폜�t���O       delete_flag                              1   0:FALSE�i�L���j
                    l_extMailProcParams.setDeleteFlag(BooleanEnum.FALSE); 
                    //�쐬����        created_timestamp                               ��������
                    l_extMailProcParams.setCreatedTimestamp(l_timeStamp);
                    //�X�V����        last_updated_timestamp                              ��������
                    l_extMailProcParams.setLastUpdatedTimestamp(l_timeStamp);   

                    l_queryProcessor.doInsertQuery(l_extMailProcParams);
                }
            }
        }
        catch (DataFindException l_e) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is����p�X���[�h�`�F�b�N )<BR> 
     * ���XID�ƘA����ʂ���A����p�X���[�h�`�F�b�N�����{���邩���肷��B<BR> 
     * <BR>
     * [�߂�l] <BR>
     * true�F ����p�X���[�h�`�F�b�N�����{���� <BR>
     * false�F ����p�X���[�h�`�F�b�N�����{���Ȃ� <BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�A����ʗp�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR> 
     * <BR>
     * [����] <BR>
     * ���XID = ����.���XID <BR>
     * �A����� = ����.�A����� <BR>
     * �v���t�@@�����X���ږ� = "inform.password.check" <BR>
     * ���ږ��A�� = 1 <BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�`�F�b�N���Ȃ��h�̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�Atrue��ԋp����B<BR> 
     * �����R�[�h���擾�o���Ȃ������ꍇ���܂ށB <BR>
     * @@param String l_strBranchId<BR>
     * @@param String l_strInformDiv<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    protected boolean isCheckTradePassword(String l_strBranchId, String l_strInformDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCheckTradePassword(String l_strBranchId, String l_strInformDiv)";
        log.entering(STR_METHOD_NAME);
        try
        {
            if (l_strBranchId == null || l_strInformDiv ==null)
            {
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //���XID�ƘA����ʂ���A����p�X���[�h�`�F�b�N�����{���邩���肷��B
            //[�߂�l]
            //true�F ����p�X���[�h�`�F�b�N�����{����
            //false�F ����p�X���[�h�`�F�b�N�����{���Ȃ�
            
            String l_strQuery = "branch_id = ? ";
            l_strQuery += " and inform_div = ?";
            l_strQuery += " and name = ?";
            l_strQuery += " and name_serial_no = ?";
        
            //�P�j�ȉ��̏����ŁA�A����ʗp�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
            //���XID = ����.���XID
            //�A����� = ����.�A�����
            //�v���t�@@�����X���ږ� = "inform.password.check"
            //    ���ږ��A�� = 1
            Long l_lngBrnchId = new Long(l_strBranchId);
            String l_strName = "inform.password.check";
            Long l_lngNameSerialNo = new Long(1);
            Object[] l_queryContainer = new Object[] {
                l_lngBrnchId,
                l_strInformDiv,
                l_strName,
                l_lngNameSerialNo};
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformDivPreferencesRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
            //�����R�[�h���擾�o���Ȃ������ꍇ���܂ށB
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�`�F�b�N���Ȃ��h�̏ꍇ�Afalse��ԋp����B
            InformDivPreferencesRow l_row = (InformDivPreferencesRow)l_lisRecords.get(0);
            if ("0".equals(l_row.getValue().trim()))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            //�R�j����ȊO�̏ꍇ�Atrue��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }
}
@
