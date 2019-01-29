head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�����N���X(WEB3AdminAccInfoInsiderInfoChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ���C�g (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoInsiderInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.InsiderPK;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AdminAccInfoInsiderInfoChangeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoChangeServiceImpl.class);
        
    /**
     * @@roseuid 418F3A09035B
     */
    public WEB3AdminAccInfoInsiderInfoChangeServiceImpl() 
    {
     
    }
    
    /**
     * �����ҏ��ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ύX<BR>
     * ����ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ύX<BR>
     * �m�Fظ��Ă̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ύX<BR>
     * ����ظ��Ă̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415D01040184
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ύX����ظ��Ă̏ꍇ
        if (l_request instanceof WEB3AdminAccInfoInsiderInfoChangeInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoInsiderInfoChangeInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ύX�m�Fظ��Ă̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoInsiderInfoChangeConfirmRequest)
        {
            l_response = this.validateChange((WEB3AdminAccInfoInsiderInfoChangeConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l�������ҏ��ύX����ظ��Ă̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoInsiderInfoChangeCompleteRequest)
        {
            l_response = this.submitChange((WEB3AdminAccInfoInsiderInfoChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get���͉��)<BR>
     * �����ҏ��ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�����ҏ��ύX�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l�������ҏ��ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B645E0338
     */
    protected WEB3AdminAccInfoInsiderInfoChangeInputResponse 
        getInputScreen(WEB3AdminAccInfoInsiderInfoChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoInsiderInfoChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //5) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //6) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //7) �i�ǉ��jcreate�ڋq��{���(�ڋq : �ڋq)
        WEB3AccInfoAccountBaseInfoCreatedService l_services = 
            (WEB3AccInfoAccountBaseInfoCreatedService)Services.getService(WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoAccountBaseInfo l_accountBaseInfo = l_services.createAccountBaseInfo(l_mainAccount);
        //7) createResponse( )
        WEB3AdminAccInfoInsiderInfoChangeInputResponse l_response =
            (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_request.createResponse();
            
        //9)�i�ǉ��j�v���p�e�B�Z�b�g
        WEB3AccInfoInsiderInfo[] l_insiderInfo = l_accountBaseInfo.insiderList;
        int l_intLength = 0 ;
        if (l_insiderInfo != null)
        {
            l_intLength = l_accountBaseInfo.insiderList.length;
        }

        for (int i = 0; i < l_intLength; i++ )
        {

            if(l_request.productCode.equals(l_insiderInfo[i].productCode))
            {
                l_response.insiderInfo = l_insiderInfo[i];
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * �����ҏ��ύX�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�����ҏ��ύX�jvalidate�ύX�v�Q�ƁB <BR>
     * <BR>
     *  ===============================================     <BR>
     *          �V�[�P���X�} :  �Ǘ��҂��q�l���i�����ҏ��ύX�jvalidate�ύX             <BR>
     *          ��̈ʒu     :  1.12  �o�^�󋵂ɕύX���Ȃ��ꍇ�A <BR>
     *                                     ��O���X���[����B                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01166                    <BR>
     * ===============================================     <BR> 
     * <BR>
     * @@param l_request - �Ǘ��҂��q�l�������ҏ��ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmResponse
     * @@roseuid 415D01040186
     */
    protected WEB3AdminAccInfoInsiderInfoChangeConfirmResponse 
        validateChange(WEB3AdminAccInfoInsiderInfoChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoInsiderInfoChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) get�،����( )        
        Institution l_strInstitution = l_administrator.getInstitution();
        //9) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //5) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //6) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //7) getTradingModule(arg0�i=ProductTypeEnum.�����j : ProductTypeEnum)
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        //8) getProduct(arg0�i=�،���Ёj : Institution, arg1�i=�����R�[�h�j : String)
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        try
        {
            Product l_product = l_productManager.getProduct(l_strInstitution, l_request.insiderInfo.productCode);
            //10) get������(�ڋq, Product)
            WEB3GentradeInsider l_gentradeInsider = WEB3GentradeInsider.getInsider(l_mainAccount, l_product);
            //11) getDataSourceObject( )
            InsiderRow l_insiderRow = (InsiderRow)l_gentradeInsider.getDataSourceObject();
            //12) �o�^�󋵂ɕύX���Ȃ��ꍇ�A��O���X���[����
            if ((l_request.insiderInfo.registStateDiv).equals(l_insiderRow.getRegistDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01166, STR_METHOD_NAME);
            }
            
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        //13) createResponse( )
        WEB3AdminAccInfoInsiderInfoChangeConfirmResponse l_response = 
            (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * �����ҏ��ύX�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�����ҏ��ύX�jsubmit�ύX�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l�������ҏ��ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteResponse
     * @@roseuid 415D01040188
     */
    protected WEB3AdminAccInfoInsiderInfoChangeCompleteResponse 
        submitChange(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //5) get�،����( )
        Institution l_strInstitution = l_administrator.getInstitution();
        //11) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //6) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
                
        //7) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //8) getTradingModule(arg0�i=ProductTypeEnum.�����j : ProductTypeEnum)
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        try
        {
            //9) getProduct(Institution, �_���r���[::java::lang::String)
            Product l_product = l_productManager.getProduct(l_strInstitution, l_request.insiderInfo.productCode);
            
            //10) getProductId( )
            long l_lngProductId = l_product.getProductId();
            long l_lngBranceId = l_mainAccount.getBranch().getBranchId();
            long l_lngAccountId = l_mainAccount.getAccountId();
            Timestamp l_timeStamp = l_finApp.getTradingSystem().getSystemTimestamp();
            //12) doUpdateQuery(PrimaryKey, Map)
            QueryProcessor l_queryProcesser =Processors.getDefaultProcessor();
            Map l_map = new HashMap();
            l_map.put("regist_div", l_request.insiderInfo.registStateDiv);
            l_map.put("last_updater", l_administrator.getAdministratorCode());
            l_map.put("last_updated_timestamp", l_timeStamp);
            
            l_queryProcesser.doUpdateQuery(
                new InsiderPK(l_strInstitutionCode, l_lngBranceId, l_lngAccountId, l_lngProductId),
                l_map);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        //13) createResponse( )
        WEB3AdminAccInfoInsiderInfoChangeCompleteResponse l_response = 
            (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
