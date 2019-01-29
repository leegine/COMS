head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�XImpl(WEB3AdminSrvRegiAccountChangeInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerSearchCondition;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeInputService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�X�����N���X<BR>                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountChangeInputService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountChangeInputServiceImpl.class);

    /**
     * @@roseuid 416F3928009C
     */
    public WEB3AdminSrvRegiAccountChangeInputServiceImpl() 
    {
     
    }
    
    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX���̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�ύX���́v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E5D5006E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
                              
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3AdminSrvRegiCustomerChangeInputRequest l_srvRegiCustomerChangeInputRequest = 
            (WEB3AdminSrvRegiCustomerChangeInputRequest)l_request;
        
        //1.1  validate( )
        l_srvRegiCustomerChangeInputRequest.validate();
        
        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
                
        //1.5 �،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //�T�[�r�X���Ǘ�
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();

        //1.6 get�T�[�r�X�}�X�^�[
        //�T�[�r�X�敪
        String l_strServiceDiv = l_srvRegiCustomerChangeInputRequest.serviceDiv;
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = 
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);
            
        //1.7 get�T�[�r�X����( )
        String l_strSrvName = l_srvRegiServiceMaster.getSrvName();
        
        //1.8 <*1>�J��Ԃ�����
        WEB3AdminSrvRegiCustomerSearchCondition[] searchConditions = l_srvRegiCustomerChangeInputRequest.searchConditions;
        int l_intArrayLengh = searchConditions.length;
        WEB3AdminSrvRegiCustomerChangeGroup[] l_srvRegiCustomerChangeGroup = new WEB3AdminSrvRegiCustomerChangeGroup[l_intArrayLengh];
        
    
        for (int i = 0; i < l_intArrayLengh; i++)
        {
            //1.8.1 validate���X����(String[])           
            l_admin.validateBranchPermission(l_srvRegiCustomerChangeInputRequest.searchConditions[i].branchCode);
            
            //���X�R�[�h
            String l_strBranchCode = l_srvRegiCustomerChangeInputRequest.searchConditions[i].branchCode;
            
            //�ڋq�R�[�h
            String l_strAccountCode = l_srvRegiCustomerChangeInputRequest.searchConditions[i].accountCode;
                       
            //�g���A�J�E���g�}�l�[�W��
            //�U���̃��N�G�X�g�f�[�^.�ڋq�R�[�h����V���̌ڋq�R�[�h���擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
            //1.8.2  get�ڋq(String, String, String)  
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
                
            //1.8.3 getAccountCode()   
            String l_strDbAccountCode = l_mainAccount.getAccountCode();         
            
            //�T�[�r�X���p�\���o�^�T�[�r�X
            WEB3SrvRegiRegistService l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

            //1.8.4 get�T�[�r�X�\���o�^(String, String, String, String, long, )
            //�\���o�^ID
            long l_lngApplyRegId = Long.parseLong(l_srvRegiCustomerChangeInputRequest.searchConditions[i].applyRegId);
            //�T�[�r�X�\���o�^
            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = 
                l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, 
                l_strServiceDiv, l_strDbAccountCode, l_lngApplyRegId, false);
            
            //1.8.5 �T�[�r�X���p�Ǘ��Ҍڋq�ύX�ꗗ�s()
            l_srvRegiCustomerChangeGroup[i] = new WEB3AdminSrvRegiCustomerChangeGroup();
            
            //1.8.6 <�v���p�e�B�E�Z�b�g>

            //�T�[�r�X�\���o�^�s
            SrvRegiApplicationParams l_srvRegiApplicationParams = 
                (SrvRegiApplicationParams)l_gentradeSrvRegiApplication.getDataSourceObject();
                
            //�\���o�^ID=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get�\���o�^ID( )�̖߂�l    
            l_srvRegiCustomerChangeGroup[i].applyRegId = String.valueOf(l_srvRegiApplicationParams.getRegistId());
            
            //���I�敪=�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�\���v�T�[�r�X.get���I�ݒ�( )�̖߂�l
            WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService = l_srvRegiServiceMaster.getAppliRequiredSrv(true);
            
            if(l_srvRegiApplicationRequiredService == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);                   
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     getClass().getName() + STR_METHOD_NAME);

            }
            
            l_srvRegiCustomerChangeGroup[i].lotteryDiv = l_srvRegiApplicationRequiredService.getLotDiv();
            
            //���X�R�[�h=���N�G�X�g�f�[�^.��������.���X�R�[�h 
            l_srvRegiCustomerChangeGroup[i].branchCode = l_strBranchCode;
            
            //�ڋq�R�[�h=�ڋq�I�u�G�W�F�N�g.get�\���ڋq�R�[�h( )�̖߂�l 
            l_srvRegiCustomerChangeGroup[i].accountCode = l_mainAccount.getDisplayAccountCode();
            
            //�\�����I�敪=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get�\�����I�敪( )�̖߂�l
            l_srvRegiCustomerChangeGroup[i].applyLotteryDiv = l_srvRegiApplicationParams.getAppliLotDiv();
            
            //�\����=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get�\����( )�̖߂�l���Z�b�g����B
            l_srvRegiCustomerChangeGroup[i].applyDate = l_srvRegiApplicationParams.getAppliDate();
            
            //�K�p�J�n��=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get�K�p�J�n��( )�̖߂�l
            l_srvRegiCustomerChangeGroup[i].trialStartDate = l_srvRegiApplicationParams.getAppliStartDate();
            
            //�K�p�I����=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get�K�p�I����( )�̖߂�l 
            l_srvRegiCustomerChangeGroup[i].trialEndDate = l_srvRegiApplicationParams.getAppliEndDate();
            
            //�o�^�敪=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get�o�^�敪( )�̖߂�l 
            l_srvRegiCustomerChangeGroup[i].registDiv = l_srvRegiApplicationParams.getPaymentDiv();
            
            //���p����=�T�[�r�X�\���o�^�s�I�u�W�F�N�g.get���p����( )�̖߂�l
            String l_strChargeAmt = null;
            if(!l_srvRegiApplicationParams.getUseAmtIsNull())
            {
                l_strChargeAmt = String.valueOf(l_srvRegiApplicationParams.getUseAmt());
            }
            l_srvRegiCustomerChangeGroup[i].chargeAmt = l_strChargeAmt;
            
        }
        
        //1.9 create���X�|���X( )
        WEB3AdminSrvRegiCustomerChangeInputResponse l_srvRegiCustomerChangeInputResponse = 
            (WEB3AdminSrvRegiCustomerChangeInputResponse)l_srvRegiCustomerChangeInputRequest.createResponse();
        
        l_srvRegiCustomerChangeInputResponse.serviceName = l_strSrvName;
        l_srvRegiCustomerChangeInputResponse.chgCustomerList = l_srvRegiCustomerChangeGroup;
                        
        return l_srvRegiCustomerChangeInputResponse;
    }
}
@
