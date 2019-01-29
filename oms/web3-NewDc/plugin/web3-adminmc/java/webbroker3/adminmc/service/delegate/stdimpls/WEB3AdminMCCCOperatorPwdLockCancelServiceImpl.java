head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorPwdLockCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽Impl(WEB3AdminMCCCOperatorPwdLockCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 Tongwei(���u) �V�K�쐬
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽Impl)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ��������޽�����N���X<BR>
 * 
 * @@author Tongwei
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorPwdLockCancelServiceImpl implements WEB3AdminMCCCOperatorPwdLockCancelService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorPwdLockCancelServiceImpl.class);      

    
    /**
     * @@roseuid 4198640B0399
     */
    public WEB3AdminMCCCOperatorPwdLockCancelServiceImpl() 
    {
     
    }
    
    /**
     * CC�I�y���[�^�p�X���[�h���b�N�������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ�މ����m�Fظ��Ă̏ꍇ<BR> 
     * �@@�|validate����()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ���������ظ��Ă̏ꍇ <BR>
     * �@@�|submit����()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F2396003A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        
        //�P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
        if(l_request instanceof WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest)
        {
           //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ�މ����m�Fظ��Ă̏ꍇ
           //�@@�|validate����()���R�[������B 
            l_response = this.validateCancel((WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest)
        {
            //�� �����̃��N�G�X�g�f�[�^���A�Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ���������ظ��Ă̏ꍇ 
            // �@@�|submit����()���R�[������B
            l_response = this.submitCancel((WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest)l_request);
        }    
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * CC�I�y���[�^�p�X���[�h���b�N�����m�F���������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�p�X���[�h���b�N�����jvalidate�����v�Q�ƁB <BR>    
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} :�u�Ǘ��҃��j���[����iCC�p�X���[�h���b�N�����jvalidate�����v <BR>
     * ��̈ʒu    : 1.7 (*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����B<BR>
     *  �����f�[�^�`�F�b�N<BR>
     * ���͂��ꂽ���҃R�[�h�i�I�y���[�^�R�[�h�j�ɂāA���҃I�u�W�F�N�g�𐶐�����B<BR>
     * �����ł��Ȃ��ꍇ�A�ΏۃI�y���[�^���Ȃ��Ɣ��f���A��O���X���[����B<BR>
     *  class         :  WEB3BusinessLayerException<BR>
     *  tag            :   BUSINESS_ERROR_01191        <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ������m�Fظ��ăf�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse
     * @@roseuid 417F2396024D
     */
    protected WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse validateCancel(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateCancel(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
        
        //1.2getInstanceFrom���O�C�����
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�I�y���[�^�Ǘ��j : String, is�X�V�i=true�j : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR, true);  
        
        //1.4 get�،����
        Institution l_institution = l_administartor.getInstitution();
        
        //1.5 validate���X����(String[])
        l_administartor.validateBranchPermission(l_request.branchCode);
        
        //1.6 getTrader(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        try
        {
            l_finObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            // 1.7 (*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����B           
            log.error("���҂����݂��Ȃ��ꍇ�̃G���[" ,  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException( WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);
        } 

        //1.8 createResponse( )    
        WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse l_response = (WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse)l_request.createResponse();    
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * CC�I�y���[�^�p�X���[�h���b�N�����������������{����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҃��j���[����iCC�p�X���[�h���b�N�����jsubmit�����v�Q�ƁB <BR>
     * <BR>
     * =============================================== <BR>
     * �V�[�P���X�} :�u�Ǘ��҃��j���[����iCC�p�X���[�h���b�N�����jsubmit�����v<BR>
     * ��̈ʒu    : 1.8(*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����<BR>
     * �� �����f�[�^�`�F�b�N<BR>
     * ���͂��ꂽ���҃R�[�h�i�I�y���[�^�R�[�h�j�ɂāA���҃I�u�W�F�N�g�𐶐�����B<BR>
     * �����ł��Ȃ��ꍇ�A�ΏۃI�y���[�^���Ȃ��Ɣ��f���A��O���X���[����B<BR>
     *  class         :  WEB3BusinessLayerException<BR>
     *  tag            :   BUSINESS_ERROR_01191        <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҃��j���[����CC���ڰ��߽ܰ��ۯ���������ظ��ăf�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse
     * @@roseuid 417F239700B7
     */
    protected WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse submitCancel(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest l_request)";
                log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
        
        //1.2getInstanceFrom���O�C�����
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�I�y���[�^�Ǘ��j : String, is�X�V�i=true�j : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR, true);  
        
        //1.4 get�،����
        Institution l_institution = l_administartor.getInstitution();
        
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = l_request.branchCode;
        
        //1.5 validate���X����(String[])
        l_administartor.validateBranchPermission(l_strBranchCodes);
        
        //1.6 validate����p�X���[�h(String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.7getTrader(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        Trader l_trader = null;
        try
        {
            l_trader = l_finObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            // 1.8 (*1) ���҂����݂��Ȃ��ꍇ�i�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�j�A��O���X���[����B           
            log.error("���҂����݂��Ȃ��ꍇ�̃G���[" ,  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException( WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);
        } 
        
        // 1.9 getLoginId( )
        long l_lngLoginid = l_trader.getLoginId();
        
        // 1.10 clearBadPassowrdHistory(long)
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        l_opLoginAdminService.clearBadPassowrdHistory(l_lngLoginid);
        
        // 1.11 enableLogin(long)
        l_opLoginAdminService.enableLogin(l_lngLoginid);
        
        //1.12 createResponse( )    
        WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse l_response = (WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse)l_request.createResponse();    
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
}
@
