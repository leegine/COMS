head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC�I�y���[�^�ύX�T�[�r�XImpl(WEB3AdminMCCCOperatorChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/30 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderPK;

import webbroker3.common.define.WEB3AccountOrderFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorChangeService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteResponse;

/**
 * (�Ǘ��҃��j���[����CC�I�y���[�^�ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҃��j���[����CC�I�y���[�^�ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCCCOperatorChangeServiceImpl implements WEB3AdminMCCCOperatorChangeService 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorChangeServiceImpl.class);      

    /**
     * @@roseuid 4198640D03A9
     */
    public WEB3AdminMCCCOperatorChangeServiceImpl() 
    {
     
    }
    
    /**
     * CC�I�y���[�^�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��ύX����ظ��Ă̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��ύX�m�Fظ��Ă̏ꍇ <BR>
     * �@@�|validate����()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��ύX����ظ��Ă̏ꍇ <BR>
     * �@@�|submit����()���R�[������B <BR>
     * <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F824101A5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCCCOperatorChangeInputRequest)
        {            
            l_response = this.getInputScreen((WEB3AdminMCCCOperatorChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorChangeConfirmRequest)
        {
            l_response = this.validateTrader((WEB3AdminMCCCOperatorChangeConfirmRequest)l_request);           
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorChangeCompleteRequest)
        {
            l_response = this.submitTrader((WEB3AdminMCCCOperatorChangeCompleteRequest)l_request);           
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);           
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * CC�I�y���[�^�ύX���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�ύX�jget���͉�ʁv�Q�ƁB <BR>
     * <BR>
     * =========================================================== <BR>
     *   �V�[�P���X�} :  �u�Ǘ��҃��j���[����iCC�ύX�jget���͉�ʁv<BR>
     *   ��̈ʒu    : 1.6 getTrader(arg0�i=�،���Ёj : <BR> 
     *                       Institution, arg1�i=���X�R�[�h�j : String, <BR>
     *                       arg2�i=���҃R�[�h�j : String) <BR>
     *                       �� �����f�[�^�`�F�b�N<BR>
     *                      ���͂��ꂽ���҃R�[�h�i�I�y���[�^�R�[�h�j�ɂāA<BR>
     *                      ���҃I�u�W�F�N�g�𐶐�����B<BR>
     *                      �����ł��Ȃ��ꍇ�A�ύX�ΏۃI�y���[�^���Ȃ��Ɣ��f���A<BR>
     *                      ��O���X���[����B<BR>
     * <BR>
     *                       1.7 (*1) ���҂����݂��Ȃ��ꍇ<BR>
     *                       �i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A<BR>
     *                       ��O���X���[����B <BR>
     *                       class :  WEB3BusinessLayerException <BR>
     *                       tag :    BUSINESS_ERROR_01191           <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ύX����ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F824101A7
     */
    protected WEB3AdminMCCCOperatorChangeInputResponse getInputScreen(WEB3AdminMCCCOperatorChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCCCOperatorChangeInputRequest l_request)";         
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();       

        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�I�y���[�^�Ǘ��j : String, is�X�V�i=true�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,true);
        
        //1.4 get�،����( )
        Institution l_institution = l_web3Administrator.getInstitution(); 
        
        //1.5 validate���X����(String)
        l_web3Administrator.validateBranchPermission(l_request.branchCode);               

        //1.6 getTrader
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //1.7 (*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����B    
        try
        {        
            l_gentradeFinObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("���҂����݂��Ȃ��B",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);              
        }
        
        //1.8 createResponse()
        WEB3AdminMCCCOperatorChangeInputResponse l_response = (WEB3AdminMCCCOperatorChangeInputResponse)l_request.createResponse();
                                   
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * CC�I�y���[�^�ύX�m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�ύX�jvalidate���ҁv�Q�ƁB <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :  �u�Ǘ��҃��j���[����iCC�ύX�jvalidate���ҁv<BR>
     *         ��̈ʒu    : 1.6 getTrader(arg0�i=�،���Ёj : <BR>
     *         Institution, arg1�i=���X�R�[�h�j : String, <BR>
     *         arg2�i=���҃R�[�h�j : String)<BR>
     *         �� �����f�[�^�`�F�b�N <BR>
     *         ���͂��ꂽ���҃R�[�h�i�I�y���[�^�R�[�h�j�ɂāA<BR>
     *         ���҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�ύX�ΏۃI�y���[�^���Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01191           <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :  �u�Ǘ��҃��j���[����iCC�ύX�jvalidate���ҁv<BR>
     *         ��̈ʒu    :1.7(*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01191          <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ύX�m�Fظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmResponse
     * @@roseuid 417F824101B4
     */
    protected WEB3AdminMCCCOperatorChangeConfirmResponse validateTrader(WEB3AdminMCCCOperatorChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTrader(WEB3AdminMCCCOperatorChangeConfirmRequest l_request)";         
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();       
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();  
        
        //1.3 validate����(String,boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR, true);        

        //1.4 get�،����( )
        Institution l_institution = l_web3Administrator.getInstitution();

        //1.5 validate���X����(String)
        l_web3Administrator.validateBranchPermission(l_request.ccOperatorRegistUnit.branchCode);  
              
        //1.6 getTrader
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //1.7 (*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����B    
        try
        {        
            l_gentradeFinObjectManager.getTrader(l_institution, l_request.ccOperatorRegistUnit.operatorCode, l_request.ccOperatorRegistUnit.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("���҂����݂��Ȃ��B",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);              
        }
        
        //1.8 createResponse()
        WEB3AdminMCCCOperatorChangeConfirmResponse l_response = (WEB3AdminMCCCOperatorChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * CC�I�y���[�^�ύX�������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�ύX�jsubmit���ҁv�Q�ƁB <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :  �u�Ǘ��҃��j���[����iCC�ύX�jsubmit���ҁv<BR>
     *         ��̈ʒu    :  1.7 getTrader(arg0�i=�،���Ёj :<BR>
     *         Institution, arg1�i=���X�R�[�h�j : String, <BR>
     *         arg2�i=���҃R�[�h�j : String)<BR>
     *         �� �����f�[�^�`�F�b�N<BR>
     *         ���͂��ꂽ���҃R�[�h�i�I�y���[�^�R�[�h�j�ɂāA<BR>
     *         ���҃I�u�W�F�N�g�𐶐�����B<BR>
     *         �����ł��Ȃ��ꍇ�A�ύX�ΏۃI�y���[�^���Ȃ��Ɣ��f���A<BR>
     *         ��O���X���[����B<BR>
     *          class :  WEB3BusinessLayerException <BR>
     *          tag :   BUSINESS_ERROR_01191          <BR>
     * ========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         �V�[�P���X�} :  �u�Ǘ��҃��j���[����iCC�ύX�jsubmit���ҁv<BR>
     *         ��̈ʒu    : 1.8(*1) ����t���[<BR>
     *         ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A<BR>
     *         ��O���X���[����B<BR>
     *          class :  WEB3BusinessLayerException <BR>
     *          tag :    BUSINESS_ERROR_01191           <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��ύX����ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteResponse
     * @@roseuid 417F824101B6
     */
    protected WEB3AdminMCCCOperatorChangeCompleteResponse submitTrader(WEB3AdminMCCCOperatorChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitTrader(WEB3AdminMCCCOperatorChangeCompleteRequest l_request)";         
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();   
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�I�y���[�^�Ǘ��j : String, is�X�V�i=true�j : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR, true);    
        
        //1.4 validate����p�X���[�h(String)
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.5 get�،����( )
        Institution l_institution = l_web3Administrator.getInstitution();  
        
        //1.6 validate���X����(String)
        l_web3Administrator.validateBranchPermission(l_request.ccOperatorRegistUnit.branchCode);                           

        //1.7 getTrader
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //1.8 (*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����B
        Trader l_trader = null;    
        try
        {        
            l_trader = l_gentradeFinObjectManager.getTrader(l_institution, l_request.ccOperatorRegistUnit.operatorCode, l_request.ccOperatorRegistUnit.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("���҂����݂��Ȃ��B",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);              
        }
        
        //1.9 getTraderId( )
        long l_lngTraderId = l_trader.getTraderId();
        
        //1.10 doUpdateQuery
        Map l_mapChanges = new HashMap();
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_mapChanges.put("family_name", l_request.ccOperatorRegistUnit.operatorName);
        l_mapChanges.put("account_order_flag", l_request.ccOperatorRegistUnit.accountOrderDiv);
        l_mapChanges.put("department_code", l_request.ccOperatorRegistUnit.departmentCode);
        l_mapChanges.put("last_updater", l_web3Administrator.getAdministratorCode());
        l_mapChanges.put("last_updated_timestamp", l_tsSystemTime);
        try 
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doUpdateQuery(new TraderPK(l_lngTraderId),l_mapChanges);
        }
        catch (DataFindException l_ex)
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
        
        //1.11 getLoginId( )
        long l_lngLoginId = l_trader.getLoginId();
        
        //1.12 getBranch( )
        Branch l_branch = l_trader.getBranch();
        
        BranchRow l_branchRow = null;              
        l_branchRow = (BranchRow)l_branch.getDataSourceObject();  
        
        long l_lngAccountGroupId = 0;
        if (l_branchRow != null)
        {
            l_lngAccountGroupId = l_branchRow.getAccountGroupId();     
        }
        
        //1.15 createResponse()
        WEB3AdminMCCCOperatorChangeCompleteResponse l_response = (WEB3AdminMCCCOperatorChangeCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME); 
        return l_response;
    }
}
@
