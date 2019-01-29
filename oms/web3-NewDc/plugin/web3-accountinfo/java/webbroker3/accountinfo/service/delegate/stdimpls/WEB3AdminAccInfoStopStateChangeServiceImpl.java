head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�����N���X(WEB3AdminAccInfoStopStateChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ���C�g (���u) �V�K�쐬
                   2006/01/16 ������(�k�����u) �d�l�ύX�E���f��081
*/

package webbroker3.accountinfo.service.delegate.stdimpls;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoLockAccYAccRegisterRelease;
import webbroker3.accountinfo.data.HostLockRegistParams;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoStopStateChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountLockDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�Ǘ��҂��q�l����~�󋵕ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l����~�󋵕ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AdminAccInfoStopStateChangeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoStopStateChangeServiceImpl.class);
        
    /**
     * @@roseuid 418F3A0A0138
     */
    public WEB3AdminAccInfoStopStateChangeServiceImpl() 
    {
     
    }
    
    /**
     * ��~�󋵕ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����~�󋵕ύX<BR>
     * ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����~�󋵕ύX<BR>
     * �m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����~�󋵕ύX<BR>
     * �������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41634C8C0269
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����~�󋵕ύX���̓��N�G�X�g�̏ꍇ
        if(l_request instanceof WEB3AdminAccInfoStopStateChangeInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoStopStateChangeInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����~�󋵕ύX�m�F���N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoStopStateChangeConfirmRequest)
        {
            l_response = this.validateChange((WEB3AdminAccInfoStopStateChangeConfirmRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l����~�󋵕ύX�������N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3AdminAccInfoStopStateChangeCompleteRequest)
        {
            l_response = this.submitChange((WEB3AdminAccInfoStopStateChangeCompleteRequest)l_request);
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
     * ��~�󋵕ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i��~�󋵕ύX�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����~�󋵕ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B32C2016B
     */
    protected WEB3AdminAccInfoStopStateChangeInputResponse 
        getInputScreen(WEB3AdminAccInfoStopStateChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoStopStateChangeInputRequest)";
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
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_gentradeMainAccount.getDataSourceObject();
        
        //6) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
                
        //7) create��~���(�ڋq : �ڋq)
        WEB3AccInfoAccountBaseInfoCreatedService l_accInfoAccountBaseInfoCreatedService =
            (WEB3AccInfoAccountBaseInfoCreatedService) Services.getService(
                WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoStopInfo l_accInfoStopInfo = l_accInfoAccountBaseInfoCreatedService.createStopInfo(l_gentradeMainAccount);
        
        //8) �Ǘ��҂��q�l����~�󋵕ύX���̓��X�|���X(WEB3GenRequest)
        WEB3AdminAccInfoStopStateChangeInputResponse l_response =
            (WEB3AdminAccInfoStopStateChangeInputResponse)l_request.createResponse();
        l_response.currentDate = l_finApp.getTradingSystem().getSystemTimestamp();
        l_response.accountName = l_mainAccountRow.getFamilyName();
        l_response.traderCode = l_mainAccountRow.getSonarTraderCode();
        l_response.stopInfo = l_accInfoStopInfo;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * ��~�󋵕ύX�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i��~�󋵕ύX�jvalidate�ύX�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����~�󋵕ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeConfirmResponse
     * @@roseuid 41634C8C026B
     */
    protected WEB3AdminAccInfoStopStateChangeConfirmResponse 
        validateChange(WEB3AdminAccInfoStopStateChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoStopStateChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //5) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //6) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //7) validateChange��~���(�ڋq, ��~���)
        validateChangeStopInfo(l_gentradeMainAccount, l_request.stopInfo);
        
        //8) createResponse( )
        WEB3AdminAccInfoStopStateChangeConfirmResponse l_response = 
            (WEB3AdminAccInfoStopStateChangeConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * ��~�󋵕ύX�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i��~�󋵕ύX�jsubmit�ύX�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l����~�󋵕ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeCompleteResponse
     * @@roseuid 41634C8C026D
     */
    protected WEB3AdminAccInfoStopStateChangeCompleteResponse 
        submitChange(WEB3AdminAccInfoStopStateChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoStopStateChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate( )
        l_request.validate();
        
        //2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, true);
        
        //4) validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //5) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //6) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            
        //7) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //8) validateChange��~���(�ڋq, ��~���)
        WEB3AccInfoStopInfo l_stopInfo = l_request.stopInfo;
        validateChangeStopInfo(l_gentradeMainAccount, l_stopInfo);
        
        //9���X�I�u�W�F�N�g���擾����B        
        WEB3GentradeBranch l_branch = null;
        try
        {
            Institution l_institution = 
                l_gentradeAccountManager.getInstitution(l_strInstitutionCode);
            l_branch = 
                (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(l_institution, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        long l_lngBranchId = 0;
        if (l_branch != null)
        {
            l_lngBranchId = l_branch.getBranchId();
        }
        
        //10���A���A�g���s�����ǂ������肷��B 
        boolean l_blnIsRealCooperation = this.IsRealCooperation(l_lngBranchId);
        
        //11���b�Z�[�W ���A���A�g���s���ꍇ�iis���A���A�g()==true�j
        if (l_blnIsRealCooperation)
        {
            //12�ύX�����������ڂ݂̂̃��b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐�����B
            WEB3AccInfoLockAccYAccRegisterRelease l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.createChangeLockAccYAccRegisterRelease(l_gentradeMainAccount, l_stopInfo);
            
            if (l_release != null)
            {
                HostLockRegistParams l_registParams = (HostLockRegistParams)l_release.getDataSourceObject();
                
                //13createChange���b�N�qY�q�o�^�����i�j�̖߂�l.���� != null �̏ꍇ�AY�q�o�^������V�K�쐬����B
                if (l_registParams.getAttribute() != null)
                {
                    //13.1���b�N�qY�q�o�^�����e�[�u�����X�V����B(DB insert)  
                    l_release.saveNewYAccRegisterRelease(l_gentradeMainAccount, l_release);
                }
                
                //14createChange���b�N�qY�q�o�^�����i�j�̖߂�l.�Ǘ������敪�A�x�X���b�N�A
                //�����F�̍��ڂ̂����ꂩ�� != null �̏ꍇ�A���b�N�q�o�^��V�K�쐬����B
                if (l_registParams.getMngLockCancelDiv() != null
                        || l_registParams.getBranchLock() != null
                        || l_registParams.getOrderPermission() != null)
                {
                    //14.1���b�N�qY�q�o�^�����e�[�u�����X�V����B(DB insert)  
                    l_release.saveNewLockAccRegisterRelease(l_gentradeMainAccount, l_release);
                }
            }
        }

        //15) getDataSourceObject( )
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_gentradeMainAccount.getDataSourceObject();
        MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
        QueryProcessor l_queryProcesser = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            //�x�q�敪
            l_mainAccountParams.setYellowCustomer(l_stopInfo.yellowAccountDiv);
            //�Ǘ����b�N
            l_mainAccountParams.setMngLockFlag(l_stopInfo.mngLockDiv);
            //�Ǘ����b�N�����J�n��
            l_mainAccountParams.setMngLockOffStartDate(l_stopInfo.mngLockCancelStartDate);
            //�Ǘ����b�N�����I����
            l_mainAccountParams.setMngLockOffEndDate(l_stopInfo.mngLockCancelEndDate);
            
//            BooleanEnum l_booleanEnum = null;
//            if (l_stopInfo.mngExpenseLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //�Ǘ����b�N���R�t���O�i���֋��j
//            l_mainAccountParams.setMngLockFlagAdvance(l_booleanEnum);
//            
//            if(l_stopInfo.mngDepositLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //�Ǘ����b�N���R�t���O�i�ۏ؋������j
//            l_mainAccountParams.setMngLockFlagUnpayMargin(l_booleanEnum);
//            
//            if (l_stopInfo.mngCollateralLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j
//            l_mainAccountParams.setMngLockFlagShortSecurity(l_booleanEnum);
//            
//            if (l_stopInfo.mngReceiptLockReasonFlag)
//            {
//                l_booleanEnum = BooleanEnum.TRUE;
//            }
//            else
//            {
//                l_booleanEnum = BooleanEnum.FALSE;
//            }
//            //�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj
//            l_mainAccountParams.setMngLockFlagUnsubstitDepo(l_booleanEnum);
            //�x�X���b�N
            l_mainAccountParams.setBranchLock(l_stopInfo.branchLockDiv);
            //�����F��
            l_mainAccountParams.setOrderPermission(l_stopInfo.orderPermitDiv);
            //��~�󋵍X�V�҃R�[�h
            l_mainAccountParams.setEnableOrderLastUpdater(l_administrator.getAdministratorCode());
            
            Timestamp l_tsSystemTime = l_finApp.getTradingSystem().getSystemTimestamp();
            //��~�󋵍X�V����
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(l_tsSystemTime);
            //�X�V����
            l_mainAccountParams.setLastUpdatedTimestamp(l_tsSystemTime);
            //��~�󋵓o�^���R = ��~���.��~�󋵓o�^���R
            l_mainAccountParams.setLockRegistrationReason(l_stopInfo.stopStateRegistReason);
            
            l_queryProcesser.doUpdateQuery(l_mainAccountParams);
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
        //createResponse( )
        WEB3AdminAccInfoStopStateChangeCompleteResponse l_response = 
            (WEB3AdminAccInfoStopStateChangeCompleteResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (validateChange��~���)<BR>
     * �ύX���ڂ̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�Ǘ����b�N�����J�n���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@���ԓ��ł��邩�̃`�F�b�N<BR>
     * �@@�@@������(*1) <= ��~���.�Ǘ����b�N�����J�n�� <= <BR>
     * ��������1������(*2)�ł��邩���`�F�b�N����B<BR>
     * �@@���ԓ��ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01171<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�c�Ɠ��`�F�b�N<BR>
     * �@@�@@�Ǘ����b�N�����J�n�����c�Ɠ��łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01172<BR>
     * <BR>
     * �Q�j�@@�Ǘ����b�N�����I�����̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@���ԓ��ł��邩�̃`�F�b�N<BR>
     * �@@�@@������(*1) <= ��~���.�Ǘ����b�N�����I���� <= <BR>
     * ��������1������(*2)�ł��邩���`�F�b�N����B<BR>
     * �@@���ԓ��ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01173<BR>     
     * <BR>
     * �@@�Q�|�Q�j�@@�c�Ɠ��`�F�b�N<BR>
     * �@@�@@�Ǘ����b�N�����I�������c�Ɠ��łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01174<BR>
     * <BR>
     * �@@(*1)�@@������<BR>
     * �@@TradingSystem.getBizDate()<BR>
     * <BR>
     * �@@(*2)�@@��������1������<BR>
     * �@@TradingSystem.getBizDate()�̗������t�̑O���B<BR>
     * �@@�� ������=2004/11/23�̏ꍇ�A2004/12/22<BR>
     * <BR>
     * �@@�A���A�������t�̑O���������ɂȂ�Ȃ��ꍇ�́A�����ɕ␳����B<BR>
     * �@@�� ������=2005/1/31�̏ꍇ�A2005/2/28�ɕ␳����B<BR>
     * <BR>
     * �R�j�@@�Ǘ����b�N�����J�n���^�I�����̊֘A�`�F�b�N<BR>
     * �@@�Ǘ����b�N�����J�n���C�Ǘ����b�N�����I�����̂ǂ�����������ł���ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01175<BR>
     * <BR>
     * �S�j�@@�ύX���ڊ����l�`�F�b�N<BR>
     * �@@��~���̈ȉ��̍��ڂɂ��āA�ڋq�s�i�ڋq.getDataSourceObject()�j��<BR>
     * �Ή����ڂ̒l�Ɣ�r����B<BR>
     * �@@���ׂē����l�ł���΁A�ύX���ڂ��Ȃ��Ɣ��肵��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01176<BR>     
     * <BR>
     * �@@�x�q�敪<BR>
     * �@@�Ǘ����b�N�敪�@@���ڋq�s.�Ǘ����b�N<BR>
     * �@@�Ǘ����b�N���R�t���O�i���֋��j<BR>
     * �@@�Ǘ����b�N���R�t���O�i�ۏ؋������j<BR>
     * �@@�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j<BR>
     * �@@�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj<BR>
     * �@@�Ǘ����b�N�����J�n��<BR>
     * �@@�Ǘ����b�N�����I����<BR>
     * �@@�x�X���b�N�敪�@@���ڋq�s.�x�X���b�N<BR>
     * �@@�����F�敪�@@���ڋq�s.�����F��<BR>
     * <BR>
     * �@@�� �Ǘ����b�N���R�t���O�e���ڂɂ��āB<BR>
     * �@@�@@�@@BooleanEnum.TRUE�̏ꍇ��true�CBooleanEnum.FALSE�̏ꍇ��<BR>
     * false�ł���Γ����l�Ɣ��f���邱�ƁB<BR>
     * 
     * �T�j��~�󋵓o�^���R�ύX�\�`�F�b�N <BR>
     * �@@ ��~���.�Ǘ����b�N�����J�n�� == �ڋq�s.�Ǘ����b�N�����J�n�� AND <BR>
     *    ��~���.�Ǘ����b�N�����I���� == �ڋq�s.�Ǘ����b�N�����I���� AND <BR>
     *    ��~���.�x�X���b�N�敪 == �ڋq�s.�x�X���b�N AND <BR>
     *    ��~���.�����F�敪 == �ڋq�s.�����F�� AND <BR>
     * �@@ ��~���.��~�󋵓o�^���R != �ڋq�s.��~�󋵓o�^���R �̏ꍇ�A��O�i��~�󋵓o�^���R�ύX�s�G���[�B�j���X���[����B<BR> 
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_02237
     * <BR>
     * �U�j�@@���b�N�qY�q�o�^�����L���[�����M�f�[�^�̃`�F�b�N <BR>
     * �@@�U�|�P�j�@@��~���.Y�q�敪 != �ڋq�s.Y�q�敪 �̏ꍇ�A�ȉ��̏��������{����B <BR>
     * �@@�@@�@@�U�|�P�|�P�j���b�N�qY�q�o�^����.get���b�N�qY�q�o�^�����i�j���R�[�����A���b�N�qY�q�o�^�����I�u�W�F�N�g���擾����B<BR> 
     * �@@�@@�@@[get���b�N�qY�q�o�^�����i�j�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�ڋq = ����.�ڋq <BR>
     * �@@�@@�@@�@@�f�[�^�R�[�h�@@= GI847(WEB3HostRequestCodeDef) <BR>
     * <BR>
     * �@@�@@�@@�U�|�P�|�Q�j�擾�������b�N�qY�q�o�^�����I�u�W�F�N�g.�����敪 == 0:�������̏ꍇ�A<BR>
     *          ��O�iSONAR�����M�f�[�^�����邽�ߕύX�s�G���[�B�j���X���[����B<BR> 
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_02238 
     * <BR>
     * �@@�U�|�Q�j�@@��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� OR <BR>
     *              ��~���.�Ǘ����b�N�����I���� != �ڋq�s.�Ǘ����b�N�����I���� OR <BR>
     *              ��~���.�x�X���b�N�敪 != �ڋq�s.�x�X���b�N OR <BR>
     *              ��~���.�����F�敪 != �ڋq�s.�����F�� �̏ꍇ�A�ȉ��̏��������{����B <BR>
     * �@@�@@�@@�U�|�Q�[�P�j���b�N�qY�q�o�^����.get���b�N�qY�q�o�^�����i�j���R�[�����A���b�N�qY�q�o�^�����I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@[get���b�N�qY�q�o�^�����i�j�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�ڋq = ����.�ڋq <BR>
     * �@@�@@�@@�@@�f�[�^�R�[�h�@@= GI846(WEB3HostRequestCodeDef) <BR>
     * <BR>
     * �@@�@@�@@�U�|�Q�[�Q�j�擾�������b�N�qY�q�o�^�����I�u�W�F�N�g.�����敪 == 0:�������̏ꍇ�A<BR>
     *          ��O�iSONAR�����M�f�[�^�����邽�ߕύX�s�G���[�B�j���X���[����B <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_02238  
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g<BR>
     * @@param l_stopInfo - ��~��񃁃b�Z�[�W�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41635BCB00B4
     */
    protected void validateChangeStopInfo(WEB3GentradeMainAccount l_mainAccount, WEB3AccInfoStopInfo l_stopInfo) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChangeStopInfo(WEB3GentradeMainAccount, WEB3AccInfoStopInfo)";
        log.entering(STR_METHOD_NAME);
        
        /*
        * �P�j�@@�Ǘ����b�N�����J�n���̃`�F�b�N<BR>
        * �@@�P�|�P�j�@@���ԓ��ł��邩�̃`�F�b�N<BR>
        * �@@�@@������(*1) <= ��~���.�Ǘ����b�N�����J�n�� <= <BR>
        * ��������1������(*2)�ł��邩���`�F�b�N����B<BR>
        * �@@���ԓ��ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01171<BR>
        */
        //������
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Date l_datBizDate = l_finApp.getTradingSystem().getBizDate();
        //��~���.�Ǘ����b�N�����J�n��
        Date l_datMngLockCancelStartDate = l_stopInfo.mngLockCancelStartDate;
        //��~���.�Ǘ����b�N�����I����
        Date l_datMngLockCancelEndDate = l_stopInfo.mngLockCancelEndDate;
        if(l_datMngLockCancelStartDate != null && l_datMngLockCancelEndDate != null)
        {
            //��������1������
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_datBizDate);
            int l_intDay = l_calendar.get(Calendar.DATE);
            l_calendar.add(Calendar.MONTH, 1);
            Date l_dateNextMonthDay;
            if (l_intDay > l_calendar.getActualMaximum(Calendar.DATE))
            {
                l_dateNextMonthDay = l_calendar.getTime();
            }
            else
            {
                l_calendar.add(Calendar.DATE, -1);
                l_dateNextMonthDay = l_calendar.getTime();
            }
        
            int l_intCompare1 = WEB3DateUtility.compareToDay(l_datMngLockCancelStartDate, l_datBizDate);
            int l_intCompare2 = WEB3DateUtility.compareToDay(l_datMngLockCancelStartDate, l_dateNextMonthDay);
            if (l_intCompare1 < 0 || l_intCompare2 > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01171, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        
            /*
            * �@@�P�|�Q�j�@@�c�Ɠ��`�F�b�N<BR>
            * �@@�@@�Ǘ����b�N�����J�n�����c�Ɠ��łȂ��ꍇ�A��O���X���[����B<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01172<BR>
            */
            String l_strBizDateType1 = WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datMngLockCancelStartDate.getTime()));
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType1))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01172, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        
           /* 
            * �Q�j�@@�Ǘ����b�N�����I�����̃`�F�b�N<BR>
            * �@@�Q�|�P�j�@@���ԓ��ł��邩�̃`�F�b�N<BR>
            * �@@�@@������(*1) <= ��~���.�Ǘ����b�N�����I���� <= <BR>
            * ��������1������(*2)�ł��邩���`�F�b�N����B<BR>
            * �@@���ԓ��ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01173<BR>
            */
        

            int l_intCompare3 = WEB3DateUtility.compareToDay(l_datMngLockCancelEndDate, l_datBizDate);
            int l_intCompare4 = WEB3DateUtility.compareToDay(l_datMngLockCancelEndDate, l_dateNextMonthDay);
            if (l_intCompare3 < 0 || l_intCompare4 > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01173, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
       
           /* 
            * �@@�Q�|�Q�j�@@�c�Ɠ��`�F�b�N<BR>
            * �@@�@@�Ǘ����b�N�����I�������c�Ɠ��łȂ��ꍇ�A��O���X���[����B<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01174<BR>
            */
            String l_strBizDateType2 = WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datMngLockCancelEndDate.getTime()));
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01174, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        
           /* 
            * �R�j�@@�Ǘ����b�N�����J�n���^�I�����̊֘A�`�F�b�N<BR>
            * �@@�Ǘ����b�N�����J�n���C�Ǘ����b�N�����I�����̂ǂ�����������ł���ꍇ�A<BR>
            * ��O���X���[����B<BR>
            *         class: WEB3BusinessLayerException<BR>
            *         tag:   BUSINESS_ERROR_01175<BR>
            */
            l_calendar.setTime(l_datMngLockCancelStartDate);
            int l_intMaxStartDate = l_calendar.getActualMaximum(Calendar.DATE);
            int l_intStartDate = l_calendar.get(Calendar.DATE);
            l_calendar.setTime(l_datMngLockCancelEndDate);
            int l_intMaxEndDate = l_calendar.getActualMaximum(Calendar.DATE);
            int l_intEndDate = l_calendar.get(Calendar.DATE);
        
            if (l_intMaxStartDate == l_intStartDate && l_intMaxEndDate == l_intEndDate)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01175, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
        }

        
       /* 
        * �S�j�@@�ύX���ڊ����l�`�F�b�N<BR>
        * �@@��~���̈ȉ��̍��ڂɂ��āA�ڋq�s�i�ڋq.getDataSourceObject()�j��<BR>
        * �Ή����ڂ̒l�Ɣ�r����B<BR>
        * �@@���ׂē����l�ł���΁A�ύX���ڂ��Ȃ��Ɣ��肵��O���X���[����B<BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01176<BR>
        * <BR>
        * �@@�x�q�敪<BR>
        * �@@�Ǘ����b�N�敪�@@���ڋq�s.�Ǘ����b�N<BR>
        * �@@�Ǘ����b�N���R�t���O�i���֋��j<BR>
        * �@@�Ǘ����b�N���R�t���O�i�ۏ؋������j<BR>
        * �@@�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j<BR>
        * �@@�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj<BR>
        * �@@�Ǘ����b�N�����J�n��<BR>
        * �@@�Ǘ����b�N�����I����<BR>
        * �@@�x�X���b�N�敪�@@���ڋq�s.�x�X���b�N<BR>
        * �@@�����F�敪�@@���ڋq�s.�����F��<BR>
        * <BR>
        * �@@�� �Ǘ����b�N���R�t���O�e���ڂɂ��āB<BR>
        * �@@�@@�@@BooleanEnum.TRUE�̏ꍇ��true�CBooleanEnum.FALSE�̏ꍇ��<BR>
        * false�ł���Γ����l�Ɣ��f���邱�ƁB<BR>
        */
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //�x�q�敪
        String l_strYellowAccount = l_stopInfo.yellowAccountDiv;
        //�Ǘ����b�N�敪
        String l_strMngLockDiv = l_stopInfo.mngLockDiv;
        //�Ǘ����b�N���R�t���O�i���֋��j
        boolean l_blnMngExpenseLockReasonFlag = l_stopInfo.mngExpenseLockReasonFlag;
        //�Ǘ����b�N���R�t���O�i�ۏ؋������j
        boolean l_blnMngDepositLockReasonFlag = l_stopInfo.mngDepositLockReasonFlag;
        //�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j
        boolean l_blnMngCollateralLockReasonFlag = l_stopInfo.mngCollateralLockReasonFlag;
        //�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj
        boolean l_blnMngReceiptLockReasonFlag = l_stopInfo.mngReceiptLockReasonFlag;
        //�Ǘ����b�N�����J�n��
        Date l_datMngLockCancelStart = l_stopInfo.mngLockCancelStartDate;
        //�Ǘ����b�N�����I����
        Date l_datMngLockCancelEnd = l_stopInfo.mngLockCancelEndDate;
        //�x�X���b�N�敪
        String l_strBranchLock = l_stopInfo.branchLockDiv;
        //�����F�敪
        String l_strOrderPermit = l_stopInfo.orderPermitDiv;
        if (l_strYellowAccount.equals(l_mainAccountRow.getYellowCustomer()) &&
            l_strMngLockDiv.equals(l_mainAccountRow.getMngLockFlag()) &&
            l_blnMngExpenseLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagAdvance()) &&
            l_blnMngDepositLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagUnpayMargin()) &&
            l_blnMngCollateralLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagShortSecurity()) &&
            l_blnMngReceiptLockReasonFlag == isTrue(l_mainAccountRow.getMngLockFlagUnsubstitDepo()) &&
            WEB3DateUtility.compareToDay(l_datMngLockCancelStart, l_mainAccountRow.getMngLockOffStartDate()) == 0 &&
            WEB3DateUtility.compareToDay(l_datMngLockCancelEnd, l_mainAccountRow.getMngLockOffEndDate()) == 0 &&
            l_strBranchLock.equals(l_mainAccountRow.getBranchLock()) && 
            l_strOrderPermit.equals(l_mainAccountRow.getOrderPermission())
            )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01176, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        
        /*
         * �T�j��~�󋵓o�^���R�ύX�\�`�F�b�N 
         * �@@ ��~���.�Ǘ����b�N�����J�n�� == �ڋq�s.�Ǘ����b�N�����J�n�� AND 
         *    ��~���.�Ǘ����b�N�����I���� == �ڋq�s.�Ǘ����b�N�����I���� AND 
         *    ��~���.�x�X���b�N�敪 == �ڋq�s.�x�X���b�N AND 
         *    ��~���.�����F�敪 == �ڋq�s.�����F�� AND 
         * �@@ ��~���.��~�󋵓o�^���R != �ڋq�s.��~�󋵓o�^���R �̏ꍇ�A��O�i��~�󋵓o�^���R�ύX�s�G���[�B�j���X���[����B
         */
        String l_checkStopStateRestReason = null;
        if (l_stopInfo.stopStateRegistReason != null && !"".equals(l_stopInfo.stopStateRegistReason))
        {
            l_checkStopStateRestReason = l_stopInfo.stopStateRegistReason;
        }
        if (WEB3DateUtility.compare(l_datMngLockCancelStart, l_mainAccountRow.getMngLockOffStartDate()) == 0
                && WEB3DateUtility.compare(l_datMngLockCancelEnd, l_mainAccountRow.getMngLockOffEndDate()) == 0
                && l_strBranchLock.equals(l_mainAccountRow.getBranchLock())
                && l_strOrderPermit.equals(l_mainAccountRow.getOrderPermission())
                && !WEB3Toolkit.isEquals(l_checkStopStateRestReason,l_mainAccountRow.getLockRegistrationReason()))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02237, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }
        
        /*
         * �U�j�@@���b�N�qY�q�o�^�����L���[�����M�f�[�^�̃`�F�b�N 
         * �@@�U�|�P�j�@@��~���.Y�q�敪 != �ڋq�s.Y�q�敪 �̏ꍇ�A�ȉ��̏��������{����B 
         * �@@�@@�@@�U�|�P�|�P�j���b�N�qY�q�o�^����.get���b�N�qY�q�o�^�����i�j���R�[�����A���b�N�qY�q�o�^�����I�u�W�F�N�g���擾����B
         * �@@�@@�@@[get���b�N�qY�q�o�^�����i�j�Ɏw�肷�����] 
         * �@@�@@�@@�@@�ڋq = ����.�ڋq 
         * �@@�@@�@@�@@�f�[�^�R�[�h�@@= GI847(WEB3HostRequestCodeDef) 
         */
        if (!l_stopInfo.yellowAccountDiv.equals(l_mainAccountRow.getYellowCustomer()))
        {
            WEB3AccInfoLockAccYAccRegisterRelease l_reqisterRelease = 
                WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(
                    l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);
            
            /*
             * �U�|�P�|�Q�j�擾�������b�N�qY�q�o�^�����I�u�W�F�N�g.�����敪 == 0:�������̏ꍇ�A
             *     ��O�iSONAR�����M�f�[�^�����邽�ߕύX�s�G���[�B�j���X���[����B
             */
            if (l_reqisterRelease != null)
            {
                if (WEB3StatusDef.NOT_DEAL.equals(l_reqisterRelease.getStatus()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02238, 
                        this.getClass().getName() + STR_METHOD_NAME); 
                } 
            }

        }
        
        /*
         * �U�|�Q�j�@@��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� OR 
         *      ��~���.�Ǘ����b�N�����I���� != �ڋq�s.�Ǘ����b�N�����I���� OR 
         *      ��~���.�x�X���b�N�敪 != �ڋq�s.�x�X���b�N OR 
         *      ��~���.�����F�敪 != �ڋq�s.�����F�� �̏ꍇ�A�ȉ��̏��������{����B 
         */
        if (WEB3DateUtility.compare(l_datMngLockCancelStart, l_mainAccountRow.getMngLockOffStartDate()) != 0
                || WEB3DateUtility.compare(l_datMngLockCancelEnd, l_mainAccountRow.getMngLockOffEndDate()) != 0
                || !l_strBranchLock.equals(l_mainAccountRow.getBranchLock())
                || !l_strOrderPermit.equals(l_mainAccountRow.getOrderPermission()))
        {
            /*
             * �U�|�Q�[�P�j���b�N�qY�q�o�^����.get���b�N�qY�q�o�^�����i�j���R�[�����A���b�N�qY�q�o�^�����I�u�W�F�N�g���擾����B 
             * �@@�@@�@@[get���b�N�qY�q�o�^�����i�j�Ɏw�肷�����] 
             * �@@�@@�@@�@@�ڋq = ����.�ڋq 
             * �@@�@@�@@�@@�f�[�^�R�[�h�@@= GI846(WEB3HostRequestCodeDef) 
             */
            WEB3AccInfoLockAccYAccRegisterRelease l_reqisterRelease = 
                WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(
                    l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
            
            /*
             * �U�|�Q�[�Q�j�擾�������b�N�qY�q�o�^�����I�u�W�F�N�g.�����敪 == 0:�������̏ꍇ�A
             *      ��O�iSONAR�����M�f�[�^�����邽�ߕύX�s�G���[�B�j���X���[����B 
             */
            if (l_reqisterRelease != null)
            {
                if (WEB3StatusDef.NOT_DEAL.equals(l_reqisterRelease.getStatus()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02238, 
                        this.getClass().getName() + STR_METHOD_NAME); 
                } 
            }

        }
        
        
    }
    
    /**
     * �ڋq���b�N���̃��A���A�g���s�����ǂ����𔻕ʂ���B <BR>
     * <BR>
     * [�߂�l] <BR>
     * true�F ���A���A�g���s�� <BR>
     * false�F ���A���A�g���s��Ȃ� <BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * [����] <BR>
     * ���XID = ������.���XID <BR>
     * �v���t�@@�����X�� = "account.lock.real.cooperation" <BR>
     * �v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�ڋq���b�N���̃��A���A�g���s���B�h �̏ꍇ�Atrue ��ԋp����B <BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�́Afalse��ԋp����B <BR>
     *    �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB <BR>
     * @@param l_lngBranchId<BR>
     * @@return boolean <BR>
     */
    
    protected boolean IsRealCooperation(long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " IsRealCooperation(long l_lngBranchId)";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" branch_id = ? ");
        l_sbWhere.append(" and name = ? ");
        l_sbWhere.append(" and name_serial_no = ? ");
        Object[] l_objWhere =
            {new Long(l_lngBranchId),
             WEB3BranchPreferencesNameDef.ACCOUNT_LOCK_REAL_COOPERATION,
             "1" };
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                BranchPreferencesRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB 
        if(l_lstRecords == null || l_lstRecords.size() <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            BranchPreferencesRow l_row = (BranchPreferencesRow)l_lstRecords.get(0);
            //�Q�j�擾�������R�[�h.�v���t�@@�����X�̒l == �h�ڋq���b�N���̃��A���A�g���s���B�h �̏ꍇ�Atrue ��ԋp����B 
            if (WEB3AccountLockDef.LOCK_REAL.equals(l_row.getValue()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            //�R�j����ȊO�̏ꍇ�́Afalse��ԋp����B 
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
        }
       
    }
    private boolean isTrue(BooleanEnum l_booleanEnum)
    {
        
        return BooleanEnum.TRUE.equals(l_booleanEnum) ? true : false;
        
    }
}
@
