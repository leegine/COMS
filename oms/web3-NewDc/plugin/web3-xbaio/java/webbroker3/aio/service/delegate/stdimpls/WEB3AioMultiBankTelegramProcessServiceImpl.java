head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMultiBankTelegramProcessServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �}���`�o���N�d�������T�[�r�XImpl�N���X(WEB3AioMultiBankTelegramProcessServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 �����(���u) �V�K�쐬
Revesion History : 2004/10/26 ���� (���u) ���r���[  
Revesion History : 2004/12/08 ���E (���u) �c�Ή�    
Revesion History : 2005/02/17 ���� (���u) �c�Ή� 
Revesion History : 2006/04/14 �юu��(���u)�@@�d�l�ύX�E���f��529
Revesion History : 2006/04/26 WeiXin (���u) �d�l�ύX�E���f��543�A545
Revesion History : 2006/05/22 �юu��(���u) �d�l�ύX�E���f��587�@@
Revesion History : 2007/06/19 �đo�g (���u) �d�l�ύX�E���f���@@No.728
Revesion History : 2007/07/12 �Ј��� (���u)  ���f��No.734
Revesion History : 2007/07/28 �Ј���(���u) �d�l�ύX���f��745
Revesion History : 2008/03/12 �Ԑi(���u) �d�l�ύX���f��831
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioCareerShopId;
import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioCompanySettleInstitutionConditions;
import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.define.WEB3AioTelegramFormatDef;
import webbroker3.aio.define.WEB3AioTelegramHttpRequestDef;
import webbroker3.aio.define.WEB3AioTelegramReturnCodeDef;
import webbroker3.aio.service.delegate.WEB3AioMultiBankTelegramProcessService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�}���`�o���N�d�������T�[�r�XImpl)<BR>
 * �}���`�o���N�d�������T�[�r�X�����N���X
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AioMultiBankTelegramProcessServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3AioMultiBankTelegramProcessService 
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMultiBankTelegramProcessServiceImpl.class);

    /**
     * �L�[ - �h��n���h
     */
    private String DELIVER_DATE = "DeliveryDate";
    
    /**
     * �L�[ - �h�U�����h�h
     */
    private String TRANSFER_DATE = "TransferDate";

    /**
     * @@roseuid 415A48CD0322
     */
    public WEB3AioMultiBankTelegramProcessServiceImpl() 
    {
     
    }
    
    /**
     * �}���`�o���N�d���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�}���`�o���N�d�������jexecute�v �Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@param l_response - ���X�|���X�f�[�^
     * @@roseuid 41255C8703DC
     */
    public void execute(HttpServletRequest l_request, HttpServletResponse l_response)
        throws ServletException, IOException
    {
        final String l_strMethodName = "execute(HttpServletRequest l_request, "
            + "HttpServletResponse l_response)";
        log.entering(l_strMethodName);
        
        //1.1. ReturnCode = "OK"
        String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
        //1.2.createHashMapFrom��M�d��(HttpServletRequest)
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);
        HashMap l_map = null;
        try
        {
            l_map = l_service.createHashMapFromReceiptTelegram(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            // Exception throws
            log.debug("Error IN createHashMapFrom��M�d��(HttpServletRequest)");
            l_strReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        if (l_map == null)
        {
            log.debug("Error IN createHashMapFrom��M�d��(HttpServletRequest) with return null!");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + l_strMethodName);
        }
        
        //=======remain zhou-yong NO.5 ��Q�[ U00621 begin ============
        
        //set�،���ЃR�[�h
        //�،���ЃR�[�h���Z�b�g����B
        //[����]
        //�،���ЃR�[�h�F HashMap.get("cpy")�̖߂�l
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)  
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        String l_strInstitutionCode = (String)l_map.get(WEB3AioTelegramFormatDef.cpy);
        l_context.setInstitutionCode(l_strInstitutionCode); 
        
        //set���X�R�[�h
        //���X�R�[�h���Z�b�g����B
        //[����]
        //���X�R�[�h�F HashMap.get("btn")�̖߂�l
        String l_strBranchCode = (String)l_map.get(WEB3AioTelegramFormatDef.btn);
        l_context.setBranchCode(l_strBranchCode);

        try
        {
            //-ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            //setTimestamp( )
            //��t�����A���t���[�����Z�b�g����B 
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        } 
        
        String l_strAccountCode = (String)l_map.get(WEB3AioTelegramFormatDef.acc);
        long l_accountId = -1;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(
                l_strInstitutionCode);

            long l_lngInstitutionId = l_institution.getInstitutionId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode
                );
            l_accountId = l_mainAccount.getAccountId();

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }

        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.setAccountIdCtx(l_accountId);
        }

        String[] l_strTelegramSendDatas = null;
        //1.3 �����v����t�̏ꍇ
        String l_strWeb3Request = (String)l_map.get(WEB3AioTelegramFormatDef.web3Request);
        // test log
        if (WEB3AioTelegramHttpRequestDef.ORDERDEMAND.equals(l_strWeb3Request))
        {
            //1.3.1 �����v����t�������s���B
            l_strTelegramSendDatas = this.orderRequireAccept(l_strReturnCode, l_map);
        }
        //1.4 ���ϊJ�n��t�̏ꍇ
        if (WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(l_strWeb3Request))
        {
            //1.4.1 ���ϊJ�n��t�������s���B
            l_strTelegramSendDatas = this.settlementStartAccept(l_strReturnCode, l_map);
        }
        //1.5 ���ό��ʒʒm�̏ꍇ
        if (WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(l_strWeb3Request))
        {
            //1.5.1 ���ό��ʒʒm�������s���B
            l_strTelegramSendDatas = this.settlementResultNotify(l_strReturnCode, l_map);
        }
        //1.6  create���M�d��(HttpServletResponse, String[])
        // ------------- ���M�d���𐶐�����B
        log.debug("l_strTelegramSendDatas = " + l_strTelegramSendDatas);
        l_service.createSendTelegram(l_response, l_strTelegramSendDatas);
        
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

        log.exiting(l_strMethodName);
    }
    
    /**
     * (�����v����t)<BR>
     * �����v����t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�}���`�o���N�d�������j�����v����t�v �Q��<BR>
     * @@param l_strReturnCode - (���^�[���R�[�h)
     * @@param l_receiptTelegramData - (��M�d���f�[�^)
     * @@return String[]
     * @@roseuid 41255E2E0246
     */
    protected String[] orderRequireAccept(String l_strReturnCode, HashMap l_receiptTelegramData) 
    {
        final String l_strMethodName = "orderRequireAccept(String l_strReturnCode, "
            + " HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        
        //1.1.  returnCode = ����.returnCode
        String l_strTargetReturnCode = l_strReturnCode;
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);

        WEB3AioFinInstitutionCashTransStatus l_finInstiCashTransStatus = null;
        boolean l_blnCashTransStatus = false;
        BankCashTransferStatusRow l_bankStatusRow = null;
        
        try
        {
            // 1.2 ���Z�@@�֘A�g���o����(String, String, String)
            //�m�����n 
            // �،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
            // ���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
            // ���ʃR�[�h�F ��M�d���f�[�^.get("req")�̖߂�l
            l_finInstiCashTransStatus = 
                new WEB3AioFinInstitutionCashTransStatus(
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
            // test log

            // [���Z�@@�֘A�g���o����.�����敪 == �O�F������ AND ����FLAG�i�����j== �O�F������] �ȊO�̏ꍇ
            if (!(WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankStatusRow.getTransactionStatus())
                && WEB3OrderStatusFlagDef.NOT_DEAL.equals(l_bankStatusRow.getOrderStatusFlag())))
            {
                log.debug("Error in [���Z�@@�֘A�g���o����.�����敪 == �O�F������ AND ����FLAG�i�����j== �O�F������]�ȊO�̏ꍇ");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
            l_blnCashTransStatus = true;
        }
        // �C���X�^���X�̃G���\�̏ꍇ
        catch (WEB3BaseException l_ex)
        {
            log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�̃G���\", l_ex);
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        // 1.3 returnCode = OK�̏ꍇ
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            //1.3.1 ��M�d���̐��������`�F�b�N����B 
            //�m�����n 
            // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            l_strTargetReturnCode = l_service.validateReceiptTelegram(l_receiptTelegramData, l_finInstiCashTransStatus);
        }
        // 1.4 insert�������v��(HashMap)
        // �������v���e�[�u���Ƀ��R�[�h��ǉ�����B 
        //�m�����n 
        // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
        try
        {
            l_service.insertOrderInfoRequire(l_receiptTelegramData);
        }
        // �G���[�F�������v���e�[�u���Ƀ��R�[�h��ǉ�����B
        catch (WEB3BaseException l_ex)
        {
            log.debug("�G���[�F�������v���e�[�u���Ƀ��R�[�h��ǉ�����B");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        
        //���Z�@@�֘A�g���o���󋵃C���X�^���X���擾�ł��Ă��� and ����FLAG(����)='0'�̏ꍇ
        if (l_blnCashTransStatus && WEB3TransactionStatusDef.NOT_DEAL.equals(
                l_bankStatusRow.getOrderStatusFlag()))
        {
            // 1.5 createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
            
            // 1.6 update�����v����t���(String, String, ���Z�@@�֘A�g���o����)
            // ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕��v����t�������̃X�e�[�^�X�ɍX�V����B
            // �m�����n 
            // returnCode�F returnCode 
            // .com�f�r�b�g���ώ���ԍ��F ��M�d���f�[�^.get("centerPayId")�̖߂�l 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            try
            {
                l_service.updateOrderRequireAccept(
                        l_strTargetReturnCode, 
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId),
                        l_finInstiCashTransStatus);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕��v����t�������̃X�e�[�^�X�ɍX�V����B");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.7 returnCode = "OK"�̏ꍇ
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            try
            {
                //1.7.1 ���Z�@@�֘A�g���o���󋵃C���X�^���X���Ď擾����B 
                //�m�����n 
                //�،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
                //���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
                //���ʃR�[�h�F ��M�d���f�[�^.get("req")�̖߂�l 
                l_finInstiCashTransStatus = 
                    new WEB3AioFinInstitutionCashTransStatus(
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�̃G���\", l_ex);
            }
            //1.7.2 �X�V�p�̍s�I�u�W�F�N�g�𐶐�����B 
            l_finInstiCashTransStatus.createForUpdateParams();
            
            //1.7.3 update�����v���������(���Z�@@�֘A�g���o����)
            // ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕��v����t�����I���i�������M�j�̃X�e�[�^�X�ɍX�V����B 
            //�m�����n 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            try
            {
                l_service.updateOrderRequireResponse(l_finInstiCashTransStatus);
            }
            // �G���[�Fupdate�����v���������(���Z�@@�֘A�g���o����)�B
            catch (WEB3BaseException l_ex)
            {
                log.debug("�G���[�Fupdate�����v���������(���Z�@@�֘A�g���o����)");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.8 create�����v�������f�[�^(String, HashMap)
        // ���M�d���ɃZ�b�g����f�[�^�𐶐�����B
        // �m�����n 
        //  returnCode�F returnCode 
        //  ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
        //  ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
        String[] l_strReturns = 
            this.createOrderRequireResponseData(
                    l_strTargetReturnCode, 
                    l_receiptTelegramData, 
                    l_finInstiCashTransStatus);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (���ϊJ�n��t)<BR>
     * ���ϊJ�n��t�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�}���`�o���N�d�������j���ϊJ�n��t�v �Q��<BR>
     * @@param l_strReturnCode - (���^�[���R�[�h)<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@return String[]
     * @@roseuid 41255E0C0033
     */
    protected String[] settlementStartAccept(String l_strReturnCode, HashMap l_receiptTelegramData)  
    {
        final String l_strMethodName = "settlementStartAccept(String l_strReturnCode, "
            + " HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        
        //1.1  returnCode = ����.returnCode
        String l_strTargetReturnCode = l_strReturnCode;
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);
        
        WEB3AioFinInstitutionCashTransStatus l_finInstiCashTransStatus = null;
        boolean l_blnCashTransStatus = false;
        BankCashTransferStatusRow l_bankStatusRow = null;
            
        try
        {
            // 1.2 ���Z�@@�֘A�g���o����(String, String, String)
            //�m�����n 
            // �،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
            // ���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
            // ���ʃR�[�h�F ��M�d���f�[�^.get("req")�̖߂�l
            l_finInstiCashTransStatus = 
                new WEB3AioFinInstitutionCashTransStatus(
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
            if (!(WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankStatusRow.getTransactionStatus())
                && WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankStatusRow.getOrderStatusFlag())
                && WEB3StartStatusFlgDef.NOT_DEAL.equals(l_bankStatusRow.getStartStatusFlag())))
            {
                log.debug(
                    "�u���Z�@@�֘A�g���o����.�����敪 == �O�F������"
                    + " AND ����FLAG�i�����j== �Q�F�������M"
                    + " AND ����FLAG�i���ϊJ�n�j == �O�F�������v�ȊO�̏ꍇ");

                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
            l_blnCashTransStatus = true;
        }
        // �C���X�^���X�̃G���\�̏ꍇ
        catch (WEB3BaseException l_ex)
        {
            log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�̃G���\");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        // 1.3 returnCode = OK�̏ꍇ
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            //1.3.1 ��M�d���̐��������`�F�b�N����B 
            //�m�����n 
            // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            l_strTargetReturnCode = l_service.validateReceiptTelegram(l_receiptTelegramData, l_finInstiCashTransStatus);
        }
        // 1.4 insert���ϊJ�n�v��(HashMap)
        // ���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B 
        //�m�����n 
        // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
        try
        {
            l_service.insertSettleStartRequire(l_receiptTelegramData);
        }
        // �G���[�F���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B
        catch (WEB3BaseException l_ex)
        {
            log.debug("�G���[�F���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        
        //���Z�@@�֘A�g���o���󋵃C���X�^���X���擾�ł��Ă��� and ����FLAG(���ϊJ�n)='0'�̏ꍇ
        if (l_blnCashTransStatus && WEB3TransactionStatusDef.NOT_DEAL.equals(
                l_bankStatusRow.getStartStatusFlag()))
        {
            // 1.5 createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
            
            // 1.6 update���ϊJ�n��t���(String, ���Z�@@�֘A�g���o����)
            // ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n��t�������̃X�e�[�^�X�ɍX�V����B
            // �m�����n 
            // returnCode�F returnCode 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            try
            {
                l_service.updateSettleStartAccept(
                        l_strTargetReturnCode, 
                        l_finInstiCashTransStatus);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n��t�������̃X�e�[�^�X�ɍX�V����B");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.7 returnCode = "OK"�̏ꍇ
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            try
            {
                //1.7.1 ���Z�@@�֘A�g���o���󋵃C���X�^���X���Ď擾����B 
                //�m�����n 
                //�،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
                //���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
                //���ʃR�[�h�F ��M�d���f�[�^.get("req")�̖߂�l 
                l_finInstiCashTransStatus = 
                    new WEB3AioFinInstitutionCashTransStatus(
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�̃G���\");
            }
            
            //1.7.2 createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
            
            //1.7.3 update���ϊJ�n�������(���Z�@@�֘A�g���o����)
            // ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n��t�����I���i�������M�j�̃X�e�[�^�X�ɍX�V����B 
            //�m�����n 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            try
            {
                l_service.updateSettleStartResponse(l_finInstiCashTransStatus);
            }
            // �G���[�Fupdate���ϊJ�n�������(���Z�@@�֘A�g���o����)�B
            catch (WEB3BaseException l_ex)
            {
                log.debug("�G���[�Fupdate���ϊJ�n�������(���Z�@@�֘A�g���o����)");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.8 create���ϊJ�n�����f�[�^
        // ���M�d���ɃZ�b�g����f�[�^�𐶐�����B
        // �m�����n 
        //  returnCode�F returnCode 
        //  ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
        String[] l_strReturns = 
            this.createSettlementStartResponseData(l_strTargetReturnCode, l_receiptTelegramData);
        
        log.exiting(l_strMethodName);
        return l_strReturns; 
    }
    
    /**
     * (���ό��ʒʒm)<BR>
     * ���ό��ʒʒm���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�}���`�o���N�d�������j���ό��ʒʒm�v �Q��<BR>
     * @@param l_strReturnCode - (���^�[���R�[�h)<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@return String[]
     * @@roseuid 41255E37011D
     */
    protected String[] settlementResultNotify(String l_strReturnCode, HashMap l_receiptTelegramData)
    {
        final String l_strMethodName = "settlementResultNotify(String l_strReturnCode, "
            + " HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //�،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
        //���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
        //�����R�[�h�F ��M�d���f�[�^.get("acc")�̖߂�l
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
        try
        {
            l_gentradeAccountManager.lockAccount(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc));
        }
        catch (Exception l_ex)
        {
            log.exiting(l_strMethodName);
            return null;
        }

        // 1.1 returnCode = "ERROR"�̏ꍇ�@@ 'NG'���Z�b�g����
        String l_strTargetReturnCode = null;
        if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
        {
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
        }
        // ����ȊO�̏ꍇ ��M�d���f�[�^.get("payStatus")�̖߂�l���Z�b�g����
        else
        {
            l_strTargetReturnCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus);
        }
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);

        WEB3AioFinInstitutionCashTransStatus l_finInstiCashTransStatus = null;
        BankCashTransferStatusRow l_bankStatusRow = null;
        boolean l_blnCashTransStatus = false;
        
        try
        {
            // 1.2 ���Z�@@�֘A�g���o����(String, String, String)
            //�m�����n 
            // �،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
            // ���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
            // ���ʃR�[�h�F ��M�d���f�[�^.get("req")�̖߂�l
            l_finInstiCashTransStatus = 
                new WEB3AioFinInstitutionCashTransStatus(
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
            if (!(WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankStatusRow.getTransactionStatus())
                && WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankStatusRow.getOrderStatusFlag())
                && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(l_bankStatusRow.getStartStatusFlag())
                && WEB3ResultStatusFlagDef.NOT_DEAL.equals(l_bankStatusRow.getResultStatusFlag())))
            {
                log.debug(
                    "�u�C���X�^���X�̐����Ɏ��s�����i�Y�����R�[�h�Ȃ��j�v or" +
                    "�u(�����敪 == '0' and ����FLAG�i�����j == '2' and" +
                    " ����FLAG�i���ϊJ�n�j == '2' and ����FLAG�i���ό��ʁj == '0')�ȊO�v�̏ꍇ�A" +
                    "returnCode��'NG'���Z�b�g");
                
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
            }
            l_blnCashTransStatus = true;
        }
        // �C���X�^���X�̃G���\�̏ꍇ
        catch (WEB3BaseException l_ex)
        {
            log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�̃G���\", l_ex);
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
        }
        // 1.3 returnCode != NG�̏ꍇ
        if(!WEB3AioTelegramReturnCodeDef.NG.equals(l_strTargetReturnCode))
        {
            // 1.3.1 ��M�d���̐��������`�F�b�N����B 
            //�m�����n 
            // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
            // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            String l_strValidateReturnCode = 
                l_service.validateReceiptTelegram(l_receiptTelegramData, l_finInstiCashTransStatus);
            // �߂�l�́@@�I���@@'OK'�̏ꍇ�@@'NG'���Z�b�g����
            if(!WEB3AioTelegramReturnCodeDef.OK.equals(l_strValidateReturnCode))
            {
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
            }
        }
        // 1.4 insert���ό��ʒʒm(HashMap)
        // ���ό��ʒʒm�e�[�u���Ƀ��R�[�h��ǉ�����B 
        //�m�����n 
        // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
        try
        {
            l_service.insertSettleResultNotify(l_receiptTelegramData);
        }
        // �G���[�F���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B
        catch (WEB3BaseException l_ex)
        {
            log.debug("�G���[�F���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
        }
        // 1.5 returnCode = "COMPLETE"�@@and ����FLAG(���ό���) = '0' �̏ꍇ�@@�@@
        HashMap l_mapDeliveryAndTransferDate = null;
        
        if (WEB3AioTelegramReturnCodeDef.COMPLETE.equals(l_strTargetReturnCode) 
            && WEB3ResultStatusFlagDef.NOT_DEAL.equals(l_bankStatusRow.getResultStatusFlag()))
        {
            // 1.5.1 submit����(HashMap)
            // �m�����n 
            // ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
            try
            {
                l_mapDeliveryAndTransferDate = this.submitOrder(l_receiptTelegramData);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in �����̓o�^���s���B", l_ex);
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        
        // 1.6. ���Z�@@�֘A�g���o���󋵃C���X�^���X���擾�ł��Ă��� and ����FLAG(���ό���)='0'�̏ꍇ
        if (l_blnCashTransStatus && WEB3TransactionStatusDef.NOT_DEAL.equals(
                l_bankStatusRow.getResultStatusFlag()))
        {
            // 1.6.1  createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
        
            // 1.6.2 update���ό��ʒʒm���(String, Date, Date, ���Z�@@�֘A�g���o����)
            // ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʒʒm�������̃X�e�[�^�X�ɍX�V����B 
            //�m�����n 
            // returnCode�F returnCode 
            // ��n�\����F [submit����()�̖߂�l].get(�h��n���h)�̖߂�l 
            // �U�������\����F [submit����()�̖߂�l].get(�h�U�����h)�̖߂�l 
            //���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
            try
            {
                if (l_mapDeliveryAndTransferDate == null)
                {
                    l_service.updateSettleResultNotify(
                            l_strTargetReturnCode,
                            null,
                            null,
                            l_finInstiCashTransStatus);
                }
                else
                {
                    l_service.updateSettleResultNotify(
                            l_strTargetReturnCode, 
                            (Date)l_mapDeliveryAndTransferDate.get(this.DELIVER_DATE),
                            (Date)l_mapDeliveryAndTransferDate.get(this.TRANSFER_DATE),
                            l_finInstiCashTransStatus);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʒʒm�������̃X�e�[�^�X�ɍX�V����B");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        
            //1.6.3 ���Z�@@�֘A�g���o���󋵃C���X�^���X���Ď擾����B 
            //�m�����n 
            //�،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
            //���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
            //���ʃR�[�h�F ��M�d���f�[�^.get("req")�̖߂�l 
            try
            {
                l_finInstiCashTransStatus = 
                    new WEB3AioFinInstitutionCashTransStatus(
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            }
            // �C���X�^���X�̃G���\�̏ꍇ
            catch (WEB3BaseException l_ex)
            {
                log.debug("���Z�@@�֘A�g���o���󋵃C���X�^���X�̃G���\", l_ex);
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
    
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
    
            //����FLAG�i���ό��ʁj= "1"�̏ꍇ
            if (WEB3ResultStatusFlagDef.NOTIFY_RECEIPT.equals(l_bankStatusRow.getResultStatusFlag()))
            {
                //1.6.4 createForUpdateParams( )
                l_finInstiCashTransStatus.createForUpdateParams();
                
                //1.6.5 update���ό��ʉ������(���Z�@@�֘A�g���o����)
                // ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʒʒm�����I���i�������M�j�̃X�e�[�^�X�ɍX�V����B
                //�m�����n 
                // ���o���󋵁F ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g 
                try
                {
                    l_service.updateSettleResultResponse(l_finInstiCashTransStatus);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.debug("Error in ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʒʒm�����I���i�������M�j�̃X�e�[�^�X�ɍX�V����B");
                    l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }
        }
        
        // 1.7  create���ό��ʉ����f�[�^
        // ���M�d���ɃZ�b�g����f�[�^�𐶐�����B
        // �m�����n 
        //  returnCode�F returnCode 
        //  ��M�d���f�[�^�F ���N�G�X�g�f�[�^.��M�d���f�[�^ 
        String[] l_strReturns = 
            this.createSettlementResultResponseData(l_receiptTelegramData);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (create�����v�������f�[�^)<BR>
     * ���M�d���ɃZ�b�g����f�[�^�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̕���������X�g�ɓo�^����B<BR>
     * <BR>
     *    "protocolVersion=V1.0"<BR>
     *    "date=[����.��M�d���f�[�^.get("date")�̖߂�l]"<BR>
     *    "centerPayId=[����.��M�d���f�[�^.get("centerPayId")�̖߂�l]"<BR>
     *    "returnCode=[����.returnCode]"<BR>
     * <BR>
     * �R�j����.returnCode = "OK" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����.<BR>
     * <BR>
     *    "ComOndebiPayMode=10"<BR>
     *    "ComOndebiTypicalGoodsName=�،������U��"<BR>
     *    "amount=[����.���o����.���z]"<BR>
     *    "ComOndebiTax=0"<BR>
     * <BR>
     * �S�j����.returnCode = "ERROR" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B<BR>
     * <BR>
     *    "description=�������܃V�X�e���G���[���������܂�����<BR>
     * �ŏ����𒆎~���܂��B<BR>
     * ���戵�X�ɂ��m�F�������B"<BR>
     * <BR>
     * �T�j�ȉ��̕���������X�g�ɓo�^����B<BR> 
     * <BR>
     * "cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]"<BR> 
     * "errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" <BR>
     * "prsid=[����.��M�d���f�[�^.get("prsid")�̖߂�l]" <BR>
     * "praid=[����.��M�d���f�[�^.get("praid")�̖߂�l]" <BR>
     * "praarsid=[����.��M�d���f�[�^.get("praarsid")�̖߂�l]" <BR>
     * "prssid=[����.��M�d���f�[�^.get("prssid")�̖߂�l]" <BR>
     * "praadpdv=[����.��M�d���f�[�^.get("prdpdv")�̖߂�l]" <BR>
     * "apsid=[����.��M�d���f�[�^.get("apsid")�̖߂�l]" <BR>
     * "cpy=[����.��M�d���f�[�^.get("cpy")�̖߂�l]" <BR>
     * "btn=[����.��M�d���f�[�^.get("btn")�̖߂�l]" <BR>
     * "acc=[����.��M�d���f�[�^.get("acc")�̖߂�l]" <BR>
     * "req=[����.��M�d���f�[�^.get("req")�̖߂�l]" <BR>
     * "rdiv=[����.��M�d���f�[�^.get("rdiv")�̖߂�l]"<BR> 
     * "web3Request=SettleStart" <BR>
     * <BR>
     * �U�j�����XID�̎擾<BR> 
     *   �U�|�P�j�}���`�o���N���ϐ���T�[�r�XImpl.get�L�����A�敪�i����.��M�d���f�[�^.get("rdiv")�j�ŁA<BR>
     *          �L�����A�敪���擾����B<BR>
     * <BR>
     *   �U�|�Q�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B<BR>
     * <BR> 
     * [�R���X�g���N�^�̈���] <BR>
     * �،���ЃR�[�h = ����.��M�d���f�[�^.get("cpy")�̖߂�l<BR>
     * ���X�R�[�h = ����.��M�d���f�[�^.get("btn")�̖߂�l<BR> 
     * �L�����A�敪 = get�L�����A�敪�i�j�Ŏ擾�����l<BR> 
     * <BR>
     * ��URL�F�@@�L�����A�ʉ����XID.get���^�[��URL()�ɂĎ擾�����l <BR>
     * <BR>
     * <BR> 
     * *1�F PR�w�Z�b�V�����L�[�i ����.��M�d���f�[�^.get("prsid")�̖߂�l �j <BR>
     * *2�F PR�w�A�v���P�[�V����ID�i ����.��M�d���f�[�^.get("praid")�̖߂�l �j <BR>
     * *3�F PR�w�Đ����T�[�r�XID�i ����.��M�d���f�[�^.get("praarsid")�̖߂�l �j <BR>
     * *4�F PR�wSSID�i ����.��M�d���f�[�^.get("prssid")�̖߂�l �j <BR>
     * *5�F PR�w�\���敪�i ����.��M�d���f�[�^.get("prdpdv")�̖߂�l �j <BR>
     * *6�F AP�w�Z�b�V����ID�i ����.��M�d���f�[�^.get("apsid")�̖߂�l �j <BR>
     * *7�F �،���ЃR�[�h�i ����.��M�d���f�[�^.get("cpy")�̖߂�l �j <BR>
     * *8�F ���X�R�[�h�i ����.��M�d���f�[�^.get("btn")�̖߂�l �j <BR>
     * *9�F �ڋq�R�[�h�i ����.��M�d���f�[�^.get("acc")�̖߂�l �j <BR>
     * *10�F ���ʃR�[�h�i ����.��M�d���f�[�^.get("req")�̖߂�l �j<BR>
     * *11�F �����o�H�敪�i ����.��M�d���f�[�^.get("rdiv")�̖߂�l �j<BR>
     * <BR>
     * �V�j���X�g����z����擾���āA�Ԃ��B 
     * @@param l_strReturnCode - (���^�[���R�[�h)<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@param l_finTransStatus - ���o����
     * @@return String[]
     * @@roseuid 41255F4302F2
     */
    protected String[] createOrderRequireResponseData(
            String l_strReturnCode, 
            HashMap l_receiptTelegramData,
            WEB3AioFinInstitutionCashTransStatus l_finTransStatus) 
    {
        final String l_strMethodName = "createOrderRequireResponseData(String " +
                "l_strReturnCode, HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        // �P�j���ArrayList�𐶐�����B 
        ArrayList l_lisReturn = new ArrayList(); 
        // �Q�j�ȉ��̕���������X�g�ɓo�^����B 
        // "protocolVersion=V1.0"
        l_lisReturn.add(WEB3AioTelegramFormatDef.protocolVersion + "=" + WEB3AioTelegramHttpRequestDef.V1DOT0);
        // "date=[����.��M�d���f�[�^.get("date")�̖߂�l]" 
        String l_strDateInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date);
        l_lisReturn.add("date=" + l_strDateInMap);
        // "centerPayId=[����.��M�d���f�[�^.get("centerPayId")�̖߂�l]" 
        String l_strCenterPayIdInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId);
        l_lisReturn.add("centerPayId=" + l_strCenterPayIdInMap);
        // "returnCode=[����.returnCode]" 
        l_lisReturn.add("returnCode=" + l_strReturnCode);
        // �R�j����.returnCode = "OK" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B 
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            // "ComOndebiPayMode=10" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.ComOndebiPayMode + "=10");
            // "ComOndebiTypicalGoodsName=�،������U��" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.ComOndebiTypicalGoodsName + "=�،������U��");
            // "amount=[����.���o����.���z]" 
            long l_lngAmount = 
                ((BankCashTransferStatusRow)l_finTransStatus.getDataSourceObject()).getAmount();
            l_lisReturn.add(WEB3AioTelegramFormatDef.amount + "=" + l_lngAmount);
            // "ComOndebiTax=0" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.ComOndebiTax + "=0");
        }
        // �S�j����.returnCode = "ERROR" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B 
        if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
        {
            // "description=�������܃V�X�e���G���[���������܂����̂ŏ����𒆎~���܂��B<BR>���戵�X�ɂ��m�F�������B" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.description + 
                "=�������܃V�X�e���G���[���������܂����̂ŏ����𒆎~���܂��B<BR>���戵�X�ɂ��m�F�������B");
        }
        
        // �T�j�ȉ��̕���������X�g�ɓo�^����B         
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(
                WEB3AioMultiBankSettleControlService.class);
        
        // *1�F PR�w�Z�b�V�����L�[�i ����.��M�d���f�[�^.get("prsid")�̖߂�l �j
        String l_strPrSessionKey = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prsid);
        
        // *2�F PR�w�A�v���P�[�V����ID�i ����.��M�d���f�[�^.get("praid")�̖߂�l �j
        String l_strPrSoftWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praid);
        
        // *3�F PR�w�Đ����T�[�r�XID�i ����.��M�d���f�[�^.get("praarsid")�̖߂�l �j 
        String l_strPrAgainServiceWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praarsid);
        
        // *4�F PR�wSSID�i ����.��M�d���f�[�^.get("prssid")�̖߂�l �j 
        String l_strPrSsId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prssid);
        
        // *5�F PR�w�\���敪�i ����.��M�d���f�[�^.get("prdpdv")�̖߂�l �j
        String l_strPrDpdv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prdpdv);
        
        // *6�F AP�w�Z�b�V����ID�i ����.��M�d���f�[�^.get("apsid")�̖߂�l �j 
        String l_strAPSessionID = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.apsid);
        
        // *7�F �،���ЃR�[�h�i ����.��M�d���f�[�^.get("cpy")�̖߂�l �j 
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        
        // *8�F ���X�R�[�h�i ����.��M�d���f�[�^.get("btn")�̖߂�l �j 
        String l_strBranchCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn);
        
        // *9�F �ڋq�R�[�h�i ����.��M�d���f�[�^.get("acc")�̖߂�l �j 
        String l_strAccountCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc);
        
        // *10�F ���ʃR�[�h�i ����.��M�d���f�[�^.get("req")�̖߂�l �j 
        String l_strRequestCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req);
        
        // *11�F �����o�H�敪�i ����.��M�d���f�[�^.get("rdiv")�̖߂�l �j
        String l_strOrderRootDiv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv);
        
        // �U�j�����XID�̎擾
        // �U�|�P�j�}���`�o���N���ϐ���T�[�r�XImpl.get�L�����A�敪�i����.��M�d���f�[�^.get("rdiv")�j�ŁA�L�����A�敪���擾����B
        String l_strCareerDiv = l_aioMultiBankSettleControlService.getCareerDiv(l_strOrderRootDiv);
        // �U�|�Q�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B
        WEB3AioCareerShopId l_aioCareerShopId = null;
        try
        {
            l_aioCareerShopId = new WEB3AioCareerShopId(l_strInstitutionCode, l_strBranchCode, l_strCareerDiv);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        } 
         
        String l_strReturnUrl = l_aioCareerShopId.getReturnURL();

        //"cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]"  
        l_lisReturn.add(WEB3AioTelegramFormatDef.cancelURL + "=" + l_strReturnUrl + "?" +
            WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS2 + "&" + 
            WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" + 
            WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" + 
            WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
            WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" + 
            WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" + 
            WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" + 
            WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
            WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
            WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
            WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //"errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.errorURL + "=" + l_strReturnUrl + "?" +
            WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS1 + "&" +
            WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" +
            WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" +
            WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
            WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" +
            WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" +
            WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" +
            WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
            WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
            WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
            WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //prsid=[����.��M�d���f�[�^.get("prsid")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prsid + "=" + l_strPrSessionKey);
        
        //"praid=[����.��M�d���f�[�^.get("praid")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.praid + "=" + l_strPrSoftWareId);
        
        //"praarsid=[����.��M�d���f�[�^.get("praarsid")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.praarsid + "=" + l_strPrAgainServiceWareId);
            
        //"prssid=[����.��M�d���f�[�^.get("prssid")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.prssid + "=" + l_strPrSsId);
        
        //"praadpdv=[����.��M�d���f�[�^.get("prdpdv")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prdpdv + "=" + l_strPrDpdv);
        
        //"apsid=[����.��M�d���f�[�^.get("apsid")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID);
        
        //"cpy=[����.��M�d���f�[�^.get("cpy")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode);
        
        //"btn=[����.��M�d���f�[�^.get("btn")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode);
        
        //"acc=[����.��M�d���f�[�^.get("acc")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode);
        
        //"req=[����.��M�d���f�[�^.get("req")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //"rdiv=[����.��M�d���f�[�^.get("rdiv")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.rdiv+ "=" + l_strOrderRootDiv);
        
        //"web3Request=SettleStart"
        l_lisReturn.add(WEB3AioTelegramFormatDef.web3Request + "=" + WEB3AioTelegramHttpRequestDef.SETTLE_START);
        
        String[] l_strReturns = new String[l_lisReturn.size()];
        
        // test log
        log.debug("���M�d���ɃZ�b�g����f�[�^ l_strReturns = " + l_strReturns);
        l_lisReturn.toArray(l_strReturns);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (create���ϊJ�n�����f�[�^)<BR>
     * ���M�d���ɃZ�b�g����f�[�^�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̕���������X�g�ɓo�^����B<BR>
     * <BR>
     *    "protocolVersion=V1.0"<BR>
     *    "date=[����.��M�d���f�[�^.get("date")�̖߂�l]"<BR>
     *    "centerPayId=[����.��M�d���f�[�^.get("centerPayId")�̖߂�l]"<BR>
     *    "returnCode=[����.returnCode]"<BR>
     * <BR>
     * �R�j����.returnCode = "OK" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B<BR>
     * <BR>
     * "returnURL=[URL]?io_rturl=0&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]&rdiv=[*11]"<BR>
     * <BR>
     * �S�j����.returnCode = "ERROR" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B<BR>
     * <BR>
     *    "description=�������܃V�X�e���G���[���������܂����̂ŏ����𒆎~���܂��B<BR>
     * ���戵�X�ɂ��m�F�������B" <BR>
     * <BR>
     * �T�j�ȉ��̕���������X�g�ɓo�^����B<BR> 
     * <BR>
     * "errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" 
     * "prsid=[����.��M�d���f�[�^.get("prsid")�̖߂�l]" 
     * "praid=[����.��M�d���f�[�^.get("praid")�̖߂�l]" 
     * "praarsid=[����.��M�d���f�[�^.get("praarsid")�̖߂�l]" 
     * "prssid=[����.��M�d���f�[�^.get("prssid")�̖߂�l]" 
     * "prdpdv=[����.��M�d���f�[�^.get("prdpdv")�̖߂�l]" 
     * "apsid=[����.��M�d���f�[�^.get("apsid")�̖߂�l]" 
     * "cpy=[����.��M�d���f�[�^.get("cpy")�̖߂�l]" 
     * "btn=[����.��M�d���f�[�^.get("btn")�̖߂�l]" 
     * "acc=[����.��M�d���f�[�^.get("acc")�̖߂�l]" 
     * "req=[����.��M�d���f�[�^.get("req")�̖߂�l]" 
     * "rdiv=[����.��M�d���f�[�^.get("rdiv")�̖߂�l]" 
     * "web3Request=SettleResult" <BR>
     * <BR>
     * �U�j�����XID�̎擾<BR> 
     *   �U�|�P�j�}���`�o���N���ϐ���T�[�r�XImpl.get�L�����A�敪�i����.��M�d���f�[�^.get("rdiv")�j�ŁA<BR>
     *          �L�����A�敪���擾����B<BR>
     * <BR>
     *   �U�|�Q�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B<BR>
     * <BR> 
     * [�R���X�g���N�^�̈���] <BR>
     * �،���ЃR�[�h = ����.��M�d���f�[�^.get("cpy")�̖߂�l<BR>
     * ���X�R�[�h = ����.��M�d���f�[�^.get("btn")�̖߂�l<BR> 
     * �L�����A�敪 = get�L�����A�敪�i�j�Ŏ擾�����l<BR> 
     * <BR>
     * ��URL�F�@@�L�����A�ʉ����XID.get���^�[��URL()�ɂĎ擾�����l <BR>
     * <BR>
     * *1�F PR�w�Z�b�V�����L�[�i ����.��M�d���f�[�^.get("prsid")�̖߂�l �j <BR> 
     * *2�F PR�w�A�v���P�[�V����ID�i ����.��M�d���f�[�^.get("praid")�̖߂�l �j <BR> 
     * *3�F PR�w�Đ����T�[�r�XID�i ����.��M�d���f�[�^.get("praarsid")�̖߂�l �j <BR> 
     * *4�F PR�wSSID�i ����.��M�d���f�[�^.get("prssid")�̖߂�l �j <BR> 
     * *5�F PR�w�\���敪�i ����.��M�d���f�[�^.get("prdpdv")�̖߂�l �j <BR> 
     * *6�F AP�w�Z�b�V����ID�i ����.��M�d���f�[�^.get("apsid")�̖߂�l �j <BR> 
     * *7�F �،���ЃR�[�h�i ����.��M�d���f�[�^.get("cpy")�̖߂�l �j <BR> 
     * *8�F ���X�R�[�h�i ����.��M�d���f�[�^.get("btn")�̖߂�l �j <BR> 
     * *9�F �ڋq�R�[�h�i ����.��M�d���f�[�^.get("acc")�̖߂�l �j <BR> 
     * *10�F ���ʃR�[�h�i ����.��M�d���f�[�^.get("req")�̖߂�l �j <BR>
     * *11�F �����o�H�敪�i ����.��M�d���f�[�^.get("rdiv")�̖߂�l �j<BR>
     * <BR>
     * �U�j���X�g����z����擾���āA�Ԃ��B<BR>
     * @@param l_strReturnCode - (���^�[���R�[�h)<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@return String[]
     * @@roseuid 41255F6F036F
     */
    protected String[] createSettlementStartResponseData(
            String l_strReturnCode, HashMap l_receiptTelegramData) 
    {
        final String l_strMethodName = "createSettlementStartResponseData(String " +
                "l_strReturnCode, HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        // �P�j���ArrayList�𐶐�����B 
        ArrayList l_lisReturn = new ArrayList(); 
        // �Q�j�ȉ��̕���������X�g�ɓo�^����B 
        // "protocolVersion=V1.0"
        l_lisReturn.add(WEB3AioTelegramFormatDef.protocolVersion + "=" + WEB3AioTelegramHttpRequestDef.V1DOT0);
        // "date=[����.��M�d���f�[�^.get("date")�̖߂�l]" 
        String l_strDateInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date);
        l_lisReturn.add(WEB3AioTelegramFormatDef.date + "=" + l_strDateInMap);
        // "centerPayId=[����.��M�d���f�[�^.get("centerPayId")�̖߂�l]" 
        String l_strCenterPayIdInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId);
        l_lisReturn.add(WEB3AioTelegramFormatDef.centerPayId + "=" + l_strCenterPayIdInMap);
        // "returnCode=[����.returnCode]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.returnCode + "=" + l_strReturnCode);
        
        //========remain zhou-yong NO.2 begin ===========
        
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(
                WEB3AioMultiBankSettleControlService.class);
        
        // *1�F PR�w�Z�b�V�����L�[�i ����.��M�d���f�[�^.get("prsid")�̖߂�l �j 
        String l_strPrSessionKey = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prsid);
        
        // *2�F PR�w�A�v���P�[�V����ID�i ����.��M�d���f�[�^.get("praid")�̖߂�l �j
        String l_strPrSoftWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praid);
        
        // *3�F PR�w�Đ����T�[�r�XID�i ����.��M�d���f�[�^.get("praarsid")�̖߂�l �j 
        String l_strPrAgainServiceWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praarsid);
        
        // *4�F PR�wSSID�i ����.��M�d���f�[�^.get("prssid")�̖߂�l �j 
        String l_strPrSsId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prssid);
        
        // *5�F PR�w�\���敪�i ����.��M�d���f�[�^.get("prdpdv")�̖߂�l �j
        String l_strPrDpdv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prdpdv);
        
        // *6�F AP�w�Z�b�V����ID�i ����.��M�d���f�[�^.get("apsid")�̖߂�l �j 
        String l_strAPSessionID = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.apsid);
        
        // *7�F �،���ЃR�[�h�i ����.��M�d���f�[�^.get("cpy")�̖߂�l �j 
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        
        // *8�F ���X�R�[�h�i ����.��M�d���f�[�^.get("btn")�̖߂�l �j 
        String l_strBranchCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn);
        
        // *9�F �ڋq�R�[�h�i ����.��M�d���f�[�^.get("acc")�̖߂�l �j 
        String l_strAccountCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc);
        
        // *10�F ���ʃR�[�h�i ����.��M�d���f�[�^.get("req")�̖߂�l �j 
        String l_strRequestCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req);
        
        // *11�F �����o�H�敪�i ����.��M�d���f�[�^.get("rdiv")�̖߂�l �j
        String l_strOrderRootDiv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv);
        
        // �U�j�����XID�̎擾
        // �U�|�P�j�}���`�o���N���ϐ���T�[�r�XImpl.get�L�����A�敪�i����.��M�d���f�[�^.get("rdiv")�j�ŁA�L�����A�敪���擾����B
        String l_strCareerDiv = l_aioMultiBankSettleControlService.getCareerDiv(l_strOrderRootDiv);
        // �U�|�Q�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B
        WEB3AioCareerShopId l_aioCareerShopId = null;
        try
        {
            l_aioCareerShopId = new WEB3AioCareerShopId(l_strInstitutionCode, l_strBranchCode, l_strCareerDiv);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        } 
         
        String l_strReturnUrl = l_aioCareerShopId.getReturnURL();

        //�R�j����.returnCode = "OK" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B 
        //"returnURL=[URL]?io_rturl=0&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]&rdiv=[*11]"  
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            l_lisReturn.add(WEB3AioTelegramFormatDef.returnURL + "=" + l_strReturnUrl + "?" +  
                WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS0 + "&" + 
                WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" + 
                WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" + 
                WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
                WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" + 
                WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" + 
                WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" + 
                WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
                WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
                WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
                WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode + "&" +
                WEB3AioTelegramFormatDef.rdiv + "=" + l_strOrderRootDiv);
        }
        
        // �S�j����.returnCode = "ERROR" �̏ꍇ�A�ȉ��̕���������X�g�ɓo�^����B 
        if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
        {
            // "description=�������܃V�X�e���G���[���������܂����̂ŏ����𒆎~���܂��B<BR>���戵�X�ɂ��m�F�������B" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.description + 
                "=�������܃V�X�e���G���[���������܂����̂ŏ����𒆎~���܂��B<BR>���戵�X�ɂ��m�F�������B");
        }
        
        //�T�j�ȉ��̕���������X�g�ɓo�^����B
        //"errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.errorURL + "=" + l_strReturnUrl + "?" +
            WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS1 + "&" + 
            WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" + 
            WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" + 
            WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
            WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" + 
            WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" + 
            WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" + 
            WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
            WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
            WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
            WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);

        //prsid=[����.��M�d���f�[�^.get("prsid")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prsid + "=" + l_strPrSessionKey);
        
        //"praid=[����.��M�d���f�[�^.get("praid")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.praid + "=" + l_strPrSoftWareId);
        
        //"praarsid=[����.��M�d���f�[�^.get("praarsid")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.praarsid + "=" + l_strPrAgainServiceWareId);
            
        //"prssid=[����.��M�d���f�[�^.get("prssid")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.prssid + "=" + l_strPrSsId);
        
        //"prdpdv=[����.��M�d���f�[�^.get("prdpdv")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prdpdv + "=" + l_strPrDpdv);        
        
        //"apsid=[����.��M�d���f�[�^.get("apsid")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID);
        
        //"cpy=[����.��M�d���f�[�^.get("cpy")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode);
        
        //"btn=[����.��M�d���f�[�^.get("btn")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode);
        
        //"acc=[����.��M�d���f�[�^.get("acc")�̖߂�l]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode);
        
        //"req=[����.��M�d���f�[�^.get("req")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //"rdiv=[����.��M�d���f�[�^.get("rdiv")�̖߂�l]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.rdiv + "=" + l_strOrderRootDiv);
        
        //"web3Request=SettleResult"
        l_lisReturn.add(WEB3AioTelegramFormatDef.web3Request + "=" + WEB3AioTelegramHttpRequestDef.SETTLE_RESULT);
        
        //========remain zhou-yong NO.2 end ===========
        
        // �U�j���X�g����z����擾���āA�Ԃ��B 
        String[] l_strReturns = new String[l_lisReturn.size()];
        l_lisReturn.toArray(l_strReturns);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (create���ό��ʉ����f�[�^)<BR>
     * ���M�d���ɃZ�b�g����f�[�^�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�ȉ��̕���������X�g�ɓo�^����B<BR>
     * <BR>
     *    "protocolVersion=V1.0"<BR>
     *    "date=[����.��M�d���f�[�^.get("date")�̖߂�l]"<BR>
     *    "centerPayId=[����.��M�d���f�[�^.get("centerPayId")�̖߂�l]"<BR>
     *    "returnCode=OK"<BR>
     * <BR>
     * �R�j���X�g����z����擾���āA�Ԃ��B<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)
     * @@return String[]
     * @@roseuid 41255F8C0014
     */
    protected String[] createSettlementResultResponseData(HashMap l_receiptTelegramData) 
    {
        final String l_strMethodName = "createSettlementResultResponseData(HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        // �P�j���ArrayList�𐶐�����B 
        ArrayList l_lisReturn = new ArrayList(); 
        // �Q�j�ȉ��̕���������X�g�ɓo�^����B 
        // "protocolVersion=V1.0" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.protocolVersion + "=" + WEB3AioTelegramHttpRequestDef.V1DOT0);
        // "date=[����.��M�d���f�[�^.get("date")�̖߂�l]" 
        String l_strDateInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date);
        l_lisReturn.add(WEB3AioTelegramFormatDef.date + "=" + l_strDateInMap);
        // "centerPayId=[����.��M�d���f�[�^.get("centerPayId")�̖߂�l]" 
        String l_strCenterPayIdInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId);
        l_lisReturn.add(WEB3AioTelegramFormatDef.centerPayId + "=" + l_strCenterPayIdInMap);
        // "returnCode=OK" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.returnCode + "=" + WEB3AioTelegramReturnCodeDef.OK);

        // �R�j���X�g����z����擾���āA�Ԃ��B
        String[] l_strReturns = new String[l_lisReturn.size()];
        l_lisReturn.toArray(l_strReturns);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (submit����)<BR>
     * �I�����C�������̓��e����A������o�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�}���`�o���N�d�������jsubmit�����v �Q��
     * @@param l_receiptTelegramData - ��M�d���f�[�^
     * @@return HashMap
     * @@roseuid 41255F8C0023
     */
    protected HashMap submitOrder(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "submitOrder(HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);

        // 1.2) get������( ) --- ���������擾����B
        Date l_dteOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        // �،���ЃR�[�h
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        // ���X�R�[�h
        String l_strBranchCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn);
        // �ڋq�R�[�h
        String l_strAccountCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc);
        
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        
        // �،����
        Institution l_institution = null;
        // ���X
        Branch l_branch = null; 
        // �ڋq
        MainAccount l_mainAccount = null; 
        // �⏕����
        SubAccount l_subAccount = null;
        try
        {
            // 1.3) InstitutionImpl(�،���ЃR�[�h : String) --- �،���ЃC���X�^���X�𐶐�����B
            // �m�����n 
            // �،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            // 1.4) MainAccountImpl(�،����ID : long, ���X�R�[�h : String, �ڋq�R�[�h : String)
            // �m�����n 
            // �،����ID�F �،����.getInstitutionId()�̖߂�l 
            // ���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
            // �ڋq�R�[�h�F ��M�d���f�[�^.get("acc")�̖߂�l 
            l_branch = l_accountManager.getBranch(l_institution, l_strBranchCode);
            l_mainAccount = 
                l_accountManager.getMainAccount(l_institution, l_branch, l_strAccountCode);
            // 1.5) getSubAccount(�⏕�����^�C�v : SubAccountTypeEnum) --- �⏕�����I�u�W�F�N�g���擾����B 
            // [����] 
            // �⏕�����^�C�v�F 1�i�a��������j 
            l_subAccount = 
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�،���ЃC���X�^���X�𐶐�����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        // 1.6) get���iID(Institution)  --- ���o���p�̏��iID���擾����B
        // [����] 
        // �،���ЁF �،���ЃI�u�W�F�N�g 
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
        long l_lngProductId = l_aioOrderManager.getProductId(l_institution);
        // ��M�d���f�[�^.get("ComOndebiCaptureDate")�̖߂�l��null�̏ꍇ
//        Date l_datTransferDate = (Date)l_receiptTelegramData.get("ComOndebiCaptureDate");
        Object l_objComOndebiCaptureDateFromTelegram = 
            l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiCaptureDate);
        Date l_datComOndebiCaptureDate = null;
        if (l_objComOndebiCaptureDateFromTelegram instanceof Date)
        {
            l_datComOndebiCaptureDate = (Date)l_objComOndebiCaptureDateFromTelegram;
        }
        else if (l_objComOndebiCaptureDateFromTelegram instanceof String)
        {
            String l_strComOndebiCaptureDate = (String)l_objComOndebiCaptureDateFromTelegram;
//            l_datComOndebiCaptureDate = WEB3DateUtility.getDate(l_strComOndebiCaptureDate, "yyyyMMddHHmmss");
            if (l_strComOndebiCaptureDate.length() >= 8)
            {
                l_datComOndebiCaptureDate = 
                    WEB3DateUtility.getDate(l_strComOndebiCaptureDate.substring(0, 8), "yyyyMMdd");
            }
        }
        // test log
        if ( l_datComOndebiCaptureDate == null)
        {
            // ���ϋ@@��ID
            String l_strPaySchemeId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId);
            // 1.7)  get�U����(String, String, String)
            // �m�����n 
            // �،���ЃR�[�h�F ��M�d���f�[�^.get("cpy")�̖߂�l 
            // ���X�R�[�h�F ��M�d���f�[�^.get("btn")�̖߂�l 
            // ���ϋ@@��ID�F ��M�d���f�[�^.get("paySchemeId")�̖߂�l 
            l_datComOndebiCaptureDate = this.getTransferDate(l_strInstitutionCode, l_strBranchCode, l_strPaySchemeId);
            // test log
        }
        Object l_objAmount = l_receiptTelegramData.get(WEB3AioTelegramFormatDef.amount);
        if (l_objAmount == null || !WEB3StringTypeUtility.isNumber((String)l_objAmount))
        {
            log.debug("���z�F ��M�d���f�[�^.get(\"amount\")�̖߂�l is NOT NUMBER!!! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + l_strMethodName);
        }
        double l_dblAmount = Double.parseDouble((String)l_objAmount);
        // 1.8) ���o���������e(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double, String, Date, String, long)
        // --- �����������e�C���X�^���X�𐶐�����B
        //[����] 
        // �㗝���͎ҁF null 
        // ������ʁF 1002�i���������j 
        // �U�փ^�C�v�F 1�i�����j 
        // ���iID�F get���iID()�̖߂�l 
        // ���z�F ��M�d���f�[�^.get("amount")�̖߂�l 
        // �L�q�F null 
        // �U�֗\����F ��M�d���f�[�^.get("ComOndebiCaptureDate")�̖߂�l 
        // �߂�l��null�̏ꍇ�́A�}���`�o���N�d�������T�[�r�X.get�U����()�̖߂�l 
        // ���ϋ@@��ID�F ��M�d���f�[�^.get("paySchemeId")�̖߂�l 
        // ����ID�F null 
        WEB3AioNewOrderSpec l_orderSpec = 
            new WEB3AioNewOrderSpec(
                    null,
                    OrderTypeEnum.CASH_IN,
                    AssetTransferTypeEnum.CASH_IN,
                    l_lngProductId,
                    l_dblAmount,
                    null,
                    l_datComOndebiCaptureDate,
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc),
                    null);
        //1.9) createNewOrderId( ) --- �V�K����ID���擾����B
        long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();
        //1.10)  get��n��(Date, Date) -- 
        // �m�����n 
        //�U���\����F ���o���������e.�U�֗\��� 
        //�������F get������()�̖߂�l 
        Date l_datDeliveryDate = 
            this.getDeliveryDate(l_orderSpec.getEstimatedTransferDate(), l_dteOrderBizDate);

        // is�M�p�����J��(�ٍϋ敪 : String)
        boolean l_blnIsMarginAccountEstablished =
            ((WEB3GentradeMainAccount)l_mainAccount).isMarginAccountEstablished(
            WEB3GentradeRepaymentDivDef.DEFAULT);

        log.debug("is�M�p�����J�݂̕ԋp�l = " + l_blnIsMarginAccountEstablished);

        if (l_blnIsMarginAccountEstablished)
        {
            // �ڋq���M�p�������J�݂��Ă���iis�M�p�����J��()==TRUE�j�ꍇ
            WEB3MarginTransferService l_service =
                (WEB3MarginTransferService)Services.getService(
                WEB3MarginTransferService.class);

            //submit�ۏ؋��U��(�ڋq, Date, double, String, Trader)
            l_service.submitMarginTransfer(
                (WEB3GentradeMainAccount)l_mainAccount,
                l_datDeliveryDate,
                l_dblAmount,
                (new WEB3Crypt()).decrypt(l_mainAccount.getTradingPassword()),
                null);
        }

        //1.11) ���o�������X�V�C���^�Z�v�^(���o���������e)
        WEB3AioCashTransOrderUpdateInterceptor l_orderUpdateInterceptor = 
            new WEB3AioCashTransOrderUpdateInterceptor(l_orderSpec);
        // 1.12) �v���p�e�B�Z�b�g
        // �C���^�Z�v�^.��n�� =  get��n���̖߂�l
        l_orderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
        // �C���^�Z�v�^.com�f�r�b�g���ώ���ԍ� = ��M�d���f�[�^.get("centerPayId")�̖߂�l
        l_orderUpdateInterceptor.setComDebitNumber(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        // �C���^�Z�v�^.�ۏ؋��敪 = " " �u�����N
        l_orderUpdateInterceptor.setGuaranteeDiv(" ");
        // �C���^�Z�v�^.������ =  get��n���̖߂�l
        l_orderUpdateInterceptor.setBizDate(l_datDeliveryDate);
        // �C���^�Z�v�^.���ʃR�[�h = ��M�d���f�[�^.get("req")�̖߂�l
        l_orderUpdateInterceptor.setOrderRequestNumber(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
       // �C���^�Z�v�^.�����o�H�敪 = ��M�d���f�[�^.get("rdiv")�̖߂�l
        l_orderUpdateInterceptor.setOrderRootDiv(
            (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv));
        
        // 1.13) setThreadLocalPersistenceEventInterceptor(���o�������X�V�C���^�Z�v�^ :
        // AioOrderManagerPersistenceEventInterceptor)
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_orderUpdateInterceptor);

        // 1.14) submitNewOrder(�⏕���� : SubAccount, ���i�^�C�v : ProductTypeEnum,
        // ���o���������e : NewOrderSpec, ����ID : long, �p�X���[�h : String, isSkip�����R�� : boolean)
        // [����]
        // �⏕�����F�@@�⏕�����I�u�W�F�N�g
        // ���i�^�C�v�F�@@5�i�����j
        // ���o���������e�F�@@���o���������e�I�u�W�F�N�g
        // �����h�c�F�@@createNewOrderId()�̖߂�l
        // �p�X���[�h�F�@@�ڋq.getTradingPassword()�̖߂�l��WEB3C��������.decrypt()�ŕ�����������
        // isSkip�����R���F�@@true
        OrderSubmissionResult l_submitResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_orderSpec,
                l_lngNewOrderId,
                (new WEB3Crypt()).decrypt( l_mainAccount.getTradingPassword()),
                true);
        if (l_submitResult.getProcessingResult().isFailedResult())
        {
            log.debug("submitNewOrder Error" + l_submitResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                    l_submitResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + l_strMethodName);
        }

        // 1.15) �]�͍Čv�Z(�⏕���� �F �⏕����)
        // [����]
        // �⏕�����F�@@�⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);

        //1.16.HashMap( )
        HashMap l_mapReturn = new HashMap();
        
        // 1.17. put(�L�[ : Object, �l : Object)
        // ��n����o�^����B
        // [����] 
        //  �L�[�F �h��n���h 
        //  �l�F get��n��()�̖߂�l
        l_mapReturn.put(this.DELIVER_DATE, l_datDeliveryDate);
        
        // 1.18. put(�L�[ : Object, �l : Object)
        // �U������o�^����B 
        // [����] 
        // �L�[�F �h�U�����h 
        // �l�F get�U����()�̖߂�l
        l_mapReturn.put(this.TRANSFER_DATE, l_datComOndebiCaptureDate);
        
        log.exiting(l_strMethodName);
        // 1.19. ����HashMap��ԋp��
        return l_mapReturn;
    }
    
    /**
     * (get��n��)<BR>
     * ��n�����擾����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A���ݓ������擾����B<BR>
     * �@@    ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     *       ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �Q�j�@@�����̉c�Ɠ��敪����B<BR>
     *  �@@�擾�������ݓ����̗j�����擾���A�y�j���܂��͓��j����<BR>
     * �ꍇ�͉c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B<BR>
     *  �@@�ȊO�̏ꍇ�A�J�����_�e�[�u�������ݓ����̓��t�����Ō������A<BR>
     * �s�̉c�Ɠ��敪���擾����B<BR>
     *    �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �R�j��n���̎Z�o<BR>
     * <BR>
     * �R�|�P�j����.�U���\��� <= ����.������ �̏ꍇ<BR>
     * <BR>
     *    ��n���Ƃ��āA����.��������ԋp����B<BR>
     * <BR>
     * �Q�j����.�U���\��� > ����.������ �̏ꍇ<BR>
     * <BR>
     *    ��n���Ƃ��āA����.�U���\�����ԋp����B<BR>
     *    ������.�U���\�������c�Ɠ��̏ꍇ�́A���̗��c�Ɠ���ԋp����B<BR>
     * @@param l_datEstTransferDate - (�U���\���)
     * @@param l_datBizDate - (������)
     * @@return Date
     * @@roseuid 41255F8C0033
     */
    protected Date getDeliveryDate(Date l_datEstTransferDate, Date l_datBizDate)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getDeliveryDate(Date l_datEstTransferDate, "
            + "Date l_datBizDate)";
        log.entering(l_strMethodName);
//        //  �P�j�@@ThreadLocalSystemAttributesRegistry���A���ݓ������擾����B 
//        // �@@ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") 
//        Timestamp l_dteCurrentDate = GtlUtils.getSystemTimestamp();
//        // �Q�j�@@�����̉c�Ɠ��敪����B 
//        // �@@�擾�������ݓ����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�͉c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B 
//        // �@@�ȊO�̏ꍇ�A�J�����_�e�[�u�������ݓ����̓��t�����Ō������A�s�̉c�Ɠ��敪���擾����B 
//        // �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B 
//        WEB3GentradeTradingTimeManagement.getBizDateType(l_dteCurrentDate);
        // test log
        log.debug("get��n��#�U���\���l_datEstTransferDate = " + l_datEstTransferDate);
        log.debug("get��n��#l_datBizDate = " + l_datBizDate);
        // �R�j��n���̎Z�o 
        // �R�|�P�j����.�U���\��� <= ����.������ �̏ꍇ 
        if(WEB3DateUtility.compareToDay(l_datEstTransferDate, l_datBizDate) <= 0)
        {
            // ��n���Ƃ��āA����.��������ԋp����B 
            log.exiting(l_strMethodName);
            return l_datBizDate;
        }
        // �Q�j����.�U���\��� > ����.������ �̏ꍇ 
        else
        {
            // ��n���Ƃ��āA����.�U���\�����ԋp����B 
            // ������.�U���\�������c�Ɠ��̏ꍇ�́A���̗��c�Ɠ���ԋp����B 
            String l_strEstTransferDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(new Timestamp(l_datEstTransferDate.getTime()));
            // test log
            log.debug("����.�U���\��� > ����.������ �̏ꍇ....");
            log.debug("����.�U���\����̉c�Ɠ��敪l_strEstTransferDateType = " + l_strEstTransferDateType);

            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strEstTransferDateType))
            {
                Date l_returnDate = new WEB3GentradeBizDate(new Timestamp(l_datEstTransferDate.getTime())).roll(1);
                log.exiting(l_strMethodName);
                return l_returnDate;
            }
            else
            {
                return l_datEstTransferDate;
            }
        }
    }
    
    /**
     * (get�U����)<BR>
     * �U�������擾����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry���A���ݓ������擾����B<BR>
     *      ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     *      ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * �Q�j�@@�����̉c�Ɠ��敪����B<BR>
     *  �@@�擾�������ݓ����̗j�����擾���A�y�j���܂��͓��j����<BR>
     * �ꍇ�͉c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B<BR>
     *  �@@�ȊO�̏ꍇ�A�J�����_�e�[�u�������ݓ����̓��t�����Ō������A<BR>
     * �s�̉c�Ɠ��敪���擾����B<BR>
     *    �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B<BR>
     * <BR>
     * �R�j�U�����̎Z�o<BR>
     * <BR>
     * �R�|�P�j���ݓ������h��c�Ɠ��h�̏ꍇ<BR>
     * <BR>
     *    ���ݓ����̗��c�Ɠ���ԋp����B<BR>
     * <BR>
     * �R�|�Q�j���ݓ������h�ʏ�c�Ɠ��h�̏ꍇ<BR>
     * <BR>
     * �R�|�Q�|�P�j��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����B<BR>
     * <BR>
     *    �m�R���X�g���N�^�ւ̈����n<BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *    ���X�R�[�h�F ����.���X�R�[�h<BR>
     *    ���ϋ@@��ID�F ����.���ϋ@@��ID<BR>
     * <BR>
     * �R�|�Q�|�Q�j���؎��Ԃ��擾����B<BR>
     * <BR>
     *    ��Еʌ��ϋ@@�֏���.get���؎���()<BR>
     * <BR>
     * �R�|�Q�|�R�j���ݓ����̎��ԕ��� < ���؎��� �̏ꍇ<BR>
     * <BR>
     *    ���ݓ����̓��t������ԋp����B<BR>
     * <BR>
     * �R�|�Q�|�S�j���ݓ����̎��ԕ��� >= ���؎��� �̏ꍇ<BR>
     * <BR>
     *    ���ݓ����̗��c�Ɠ���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)<BR>
     * @@return Date
     * @@roseuid 4125E8D20026
     */
    protected Date getTransferDate(String l_strInstitutionCode, String l_strBranchCode, String l_strPaySchemeId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getTransferDate(String l_strInstitutionCode, "
            + "String l_strBranchCode, String l_strPaySchemeId)";
        log.entering(l_strMethodName);
        // �P�j�@@ThreadLocalSystemAttributesRegistry���A���ݓ������擾����B 
        // �@@ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") 
        Timestamp l_dteCurrentDate = GtlUtils.getSystemTimestamp();
        // �Q�j�@@�����̉c�Ɠ��敪����B 
        // �@@�擾�������ݓ����̗j�����擾���A�y�j���܂��͓��j���̏ꍇ�͉c�Ɠ��敪���h��c�Ɠ��h�Ƃ���B 
        // �@@�ȊO�̏ꍇ�A�J�����_�e�[�u�������ݓ����̓��t�����Ō������A�s�̉c�Ɠ��敪���擾����B 
        // �s���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��敪���h�ʏ�c�Ɠ��h�Ƃ���B 
        String l_strCurrentBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_dteCurrentDate);
        
        // test log
        log.debug("����: " + l_dteCurrentDate);
        log.debug("�����̉c�Ɠ��敪l_strCurrentBizDateType = " + l_strCurrentBizDateType);

        // �R�j�U�����̎Z�o 
        // �R�|�P�j���ݓ������h��c�Ɠ��h�̏ꍇ 
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strCurrentBizDateType))
        {
            //// ���ݓ����̗��c�Ɠ���ԋp����B 
            Date l_dteTransferDate = 
                new WEB3GentradeBizDate(new Timestamp(l_dteCurrentDate.getTime())).roll(1);
            log.exiting(l_strMethodName);
            return l_dteTransferDate;
        }
        else
        {
            // �R�|�Q�j���ݓ������h�ʏ�c�Ɠ��h�̏ꍇ 
            // �R�|�Q�|�P�j��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����B 
            // �m�R���X�g���N�^�ւ̈����n 
            // �،���ЃR�[�h�F ����.�،���ЃR�[�h 
            // ���X�R�[�h�F ����.���X�R�[�h 
            // ���ϋ@@��ID�F ����.���ϋ@@��ID 
            WEB3AioCompanySettleInstitutionConditions l_companyConditions = 
                new WEB3AioCompanySettleInstitutionConditions(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strPaySchemeId);
            // �R�|�Q�|�Q�j���؎��Ԃ��擾����B 
            // ��Еʌ��ϋ@@�֏���.get���؎���() ---- (HHMMSS)
            String l_strLimitTime = l_companyConditions.getLimitTime();
            // test log
            log.debug("��Еʌ��ϋ@@�֏���.get���؎���()l_strLimitTime = " + l_strLimitTime);
            String l_strCurrentDate = 
                WEB3DateUtility.formatDate(l_dteCurrentDate, "HHmmss");
            // �R�|�Q�|�R�j���ݓ����̎��ԕ��� < ���؎��� �̏ꍇ 
            if (l_strCurrentDate.compareTo(l_strLimitTime) < 0)
            {
                // ���ݓ����̓��t������ԋp����B
                log.exiting(l_strMethodName);
                return l_dteCurrentDate;
            }
            // �R�|�Q�|�S�j���ݓ����̎��ԕ��� >= ���؎��� �̏ꍇ 
            else
            {
                // ���ݓ����̗��c�Ɠ���ԋp����B 
                Date l_dteTransferDate = 
                    new WEB3GentradeBizDate(new Timestamp(l_dteCurrentDate.getTime())).roll(1);
                log.exiting(l_strMethodName);
                return l_dteTransferDate;
            }
        }
    }
}@
