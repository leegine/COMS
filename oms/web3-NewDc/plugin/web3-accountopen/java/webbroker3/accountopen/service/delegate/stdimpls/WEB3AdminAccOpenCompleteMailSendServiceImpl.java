head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݊������[�����M�T�[�r�XImpl(WEB3AdminAccOpenCompleteMailSendServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�݊������[�����M�T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�݊������[�����M�T�[�r�X�����N���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendServiceImpl implements WEB3AdminAccOpenCompleteMailSendService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendServiceImpl.class);

    /**
     * @@roseuid 41B45E7100FA
     */
    public WEB3AdminAccOpenCompleteMailSendServiceImpl() 
    {
     
    }
    
    /**
     * �����J�݊������[�����M���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���[�����M�ꗗ()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�݊������[�����M���N�G�X�g�̏ꍇ <BR>
     * �@@�|submit���[��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A194C6036D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminAccOpenCompleteMailSendListRequest)
        {
            log.debug("WEB3AdminAccOpenCompleteMailSendListRequest");
            WEB3AdminAccOpenCompleteMailSendListResponse l_response = 
                getMailSendList((WEB3AdminAccOpenCompleteMailSendListRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenCompleteMailSendRequest)
        {
            log.debug("WEB3AdminAccOpenCompleteMailSendRequest");
            WEB3AdminAccOpenCompleteMailSendResponse l_response = 
                submitMail((WEB3AdminAccOpenCompleteMailSendRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���[�����M�ꗗ)<BR>
     * �����J�݊������[�����M�ꗗ�\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�������[�����M�jget���[�����M�ꗗ�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A194C6038D
     */
    protected WEB3AdminAccOpenCompleteMailSendListResponse getMailSendList(WEB3AdminAccOpenCompleteMailSendListRequest l_request) throws WEB3BaseException 
    {       
        final String STR_METHOD_NAME = " getMailSendList(WEB3AdminAccOpenCompleteMailSendListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 is�S���X����( )
        boolean l_blnIsAuthority = l_admin.isAllBranchsPermission();
        
        //1.6 �����X�̂݉̊Ǘ��҂̏ꍇ�iis�S���X����() == false�j
        
        String l_strBranchCode = null;
        if (!l_blnIsAuthority)
        {
            log.debug("is�S���X����() == false");
            //1.6.1 get���X�R�[�h( )
            l_strBranchCode = l_admin.getBranchCode();
        }
        
        log.debug("l_strInstitutionCode="+l_strInstitutionCode);
        log.debug("l_strBranchCode="+l_strBranchCode);
        
        //1.7���[��(String, String, String)
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h() 
        //���M���[���敪�F�@@���M���[���敪.�����J�݊������[���i0202�j 
        //����ID�F�@@�h----�h 
        new WEB3GentradeMailInfo(l_strInstitutionCode, 
            WEB3SendmailDivDef.ACCOPEN_COMPLETE_MAIL, "----");//WEB3BusinessLayerException,WEB3SystemLayerException
            
        //1.8 get�����J�݊����ڋq(String, String)
        WEB3GentradeMainAccount[] l_gentradeMainAccount= 
            this.getAccOpenCompletedMainAccounts(l_strInstitutionCode, l_strBranchCode);
        
        //1.9 createResponse( )
        WEB3AdminAccOpenCompleteMailSendListResponse l_response = 
            (WEB3AdminAccOpenCompleteMailSendListResponse)l_request.createResponse();
        
        //1.10 �v���p�e�B�Z�b�g
        int l_intLength = l_gentradeMainAccount.length;
        
        log.debug("l_intLength=="+l_intLength);
        
        if (l_intLength == 0) {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[�����J�݊������[�����M�Ώیڋq]");
        }
        
        int l_dispLength = Integer.parseInt(l_request.dispSize);
        if (l_dispLength > l_intLength) {
            l_dispLength = l_intLength;
        }
        
        String[] l_strBranchCodes = new String[l_dispLength];
        String[] l_strAccountCodes = new String[l_dispLength];
        
        for (int i = 0; i < l_dispLength; i++)
        {
            l_strBranchCodes[i] = new String();
            l_strAccountCodes[i] = new String();
            
            l_strBranchCodes[i] = l_gentradeMainAccount[i].getBranch().getBranchCode();
            l_strAccountCodes[i] = l_gentradeMainAccount[i].getDisplayAccountCode();           
        }
        
        l_response.branchCode = l_strBranchCodes;
        l_response.accountCode = l_strAccountCodes;
        
        //�����R�[�h��
        l_response.totalRecords = String.valueOf(l_intLength);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���[��)<BR>
     * �����J�݊������[�����M�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�������[�����M�jsubmit���[���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�݊������[�����M���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A194C603AC
     */
    protected WEB3AdminAccOpenCompleteMailSendResponse submitMail(WEB3AdminAccOpenCompleteMailSendRequest l_request) throws WEB3BaseException 
    {      
        final String STR_METHOD_NAME = " submitMail(WEB3AdminAccOpenCompleteMailSendRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.6 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 ���[��(String, String, String)
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h() 
        //���M���[���敪�F�@@���M���[���敪.�����J�݊������[���i0202�j 
        //����ID�F�@@�h----�h 
        new WEB3GentradeMailInfo(l_strInstitutionCode, 
            WEB3SendmailDivDef.ACCOPEN_COMPLETE_MAIL, "----");//WEB3BusinessLayerException,WEB3SystemLayerException
            
        //1.8 ���N�G�X�g�f�[�^.�ڋq�R�[�h[]�e�v�f����LOOP�������{�B
        int l_accountCodeLength = l_request.accountCode.length;
        
        //�g���A�J�E���g�}�l�[�W��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        for (int i = 0; i < l_accountCodeLength; i++)
        {                
            //1.8.1 get�ڋq(String, String, String) 
            String l_strBranchCode = l_request.branchCode[i];
            String l_strAccountCode = l_request.accountCode[i];
            
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
                
            //1.8.2 sendMailProcess(�ڋq)
            WEB3AdminAccOpenCompleteMailSendUnitService l_service = 
                (WEB3AdminAccOpenCompleteMailSendUnitService)Services.getService(
                    WEB3AdminAccOpenCompleteMailSendUnitService.class);
                    
            l_service.sendMailProcess(l_mainAccount);  
        }

        //1.9 createResponse( )
        WEB3AdminAccOpenCompleteMailSendResponse l_response = 
            (WEB3AdminAccOpenCompleteMailSendResponse)l_request.createResponse();
               
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�����J�݊����ڋq)<BR>
     * �����J�݊����ڋq���擾����B<BR>
     * <BR>
     * �P�j�@@�ڋq�}�X�^����<BR>
     * �@@�ȉ��̏����Ōڋq�}�X�^����������B<BR>
     * <BR>
     * �@@�ڋq�}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And
     * �@@�ڋq�}�X�^.���X�R�[�h = ���X�R�[�h�@@And�@@�i���j<BR>
     * �@@�ڋq�}�X�^.email�A�h���X != null And<BR>
     * �@@�ڋq�}�X�^.�����J�݊������[�����M�X�e�[�^�X = 0�F�������iEmail�����M�j<BR>
     * <BR>
     * [�擾��] <BR>
     * ���X�R�[�h�i�����j�C�@@�����R�[�h�i�����j <BR>
     * <BR>
     * �@@�i���j���X�R�[�h�����́A�w�肪����ꍇ�i���X�R�[�h != null�j�̂݁A<BR>
     * �����ɒǉ�����B<BR>
     * <BR>
     * �Q�j�@@�ڋqList�i�FArrayList�j����<BR>
     * �@@�ڋqList�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �R�j�@@���O�C�����擾<BR>
     * �@@�������ʂ̊e�s�I�u�W�F�N�g�ɂ��āA�R�|�P�j�`�R�|�T�j���s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�ڋq�I�u�W�F�N�g����<BR>
     * �@@�@@�����Ώۍs�I�u�W�F�N�g���w�肵�Čڋq�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�ڋqRow�F�@@�Ώۗv�f<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���O�C���h�c�擾<BR>
     * �@@�@@�ڋq.get���O�C���h�c()�ɂāA���O�C���h�c���擾����B<BR>
     * �@@�@@���O�C���h�c���擾�ł��Ȃ������ꍇ�́A�ȍ~�̏��������{���Ȃ��B�icontinue;�j<BR>
     * <BR>
     * �@@�R�|�R�j�@@���O�C�������擾<BR>
     * �@@�@@OpLoginAdminService.getLoginAttributes()�ɂāA <BR>
     * �@@�@@���O�C�������s.���O�C���h�c�ɊY�����郍�O�C�������i�FMap�j���擾����B <BR>
     * �@@�@@���O�C���������擾�ł��Ȃ������ꍇ�́A�ȍ~�̏��������{���Ȃ��B�icontinue;�j<BR>
     * <BR>
     * �@@�R�|�S�j�@@�����p�X���[�h�`�F�b�N<BR>
     * �@@�@@�擾�������O�C�������i�FMap�j���A�ȉ��̑������ɊY�����鑮���l���擾����B<BR>
     * �@@�@@���O�C�������l���擾�ł��Ȃ������ꍇ�́A�ȍ~�̏��������{���Ȃ��B�icontinue);<BR>
     * <BR>
     * �@@�@@�������\�����p�X���[�h�iWEB3_ENCRYPTED_INITIAL_PASSWORD�j<BR>
     * <BR>
     * �@@�R�|�T�j�@@�ڋqList�i�FArrayList�j�ɗv�f�ǉ�<BR>
     * �@@�@@�R�|�S�j�Ŏ擾���������l������null�łȂ��ꍇ�A<BR>
     * �ڋqList�i�FArrayList�j�ɑΏیڋq�I�u�W�F�N�g��ǉ��iadd�j����B<BR>
     * <BR>
     * �S�j�@@�ԋp�l�擾<BR>
     * �@@�ڋqList�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@return webbroker3.gentrade.WEB3GentradeMainAccount[]
     * @@roseuid 41A53BAF01D6
     */
    protected WEB3GentradeMainAccount[] getAccOpenCompletedMainAccounts(String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccOpenCompletedMainAccounts(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ڋq�}�X�^����
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        
        if (l_strBranchCode != null && !"".equals(l_strBranchCode.trim()))
        {
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_code = ? ");
            l_sbWhere.append(" and email_address IS NOT NULL "); 
            l_sbWhere.append(" and acc_open_send_mail_status = ? ");
            
            l_objWhere = new Object[] {l_strInstitutionCode, l_strBranchCode, WEB3EmailStatusDef.EMAIL_NOT_SEND};
            //l_sbWhere.append("where institution_code = " + l_strInstitutionCode + " and  branch_code = " + l_strBranchCode + " and email_address IS NOT null and acc_open_send_mail_status = " + WEB3EmailStatusDef.EMAIL_NOT_SEND+"");
        }
        else
        {
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and email_address IS NOT NULL "); 
            l_sbWhere.append(" and acc_open_send_mail_status = ? ");
            
            l_objWhere = new Object[] {l_strInstitutionCode, WEB3EmailStatusDef.EMAIL_NOT_SEND};
        }

        List l_lisRecords = null;
        try
        {
            log.debug(MainAccountRow.TYPE + l_sbWhere.toString());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_sbWhere.toString(),
                "branch_code asc, account_code asc",
                "",
                l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME);
        }
        
        //�Q�j�ڋqList�i�FArrayList�j����
        ArrayList l_arrayListRecords = new ArrayList();
        
        //�R�j���O�C�����擾
        int l_intLength = l_lisRecords.size();
        log.debug("l_intLength==="+l_intLength);
        
        for (int i = 0; i < l_intLength; i++)
        {        
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(i);
                
            //�R�|�P�j�ڋq�I�u�W�F�N�g����
            WEB3GentradeMainAccount l_mainAccount = 
                new WEB3GentradeMainAccount(l_mainAccountRow);
            
            //�R�|�Q�j���O�C���h�c�擾
            long l_lngAccountLoninId;
            try
            {            
                l_lngAccountLoninId = l_mainAccount.getLoginId();//WEB3SystemLayerException
            }
            catch(WEB3SystemLayerException l_ex)
            {
                log.debug("���O�C���h�c���擾�ł��Ȃ������ꍇ�́A�ȍ~�̏��������{���Ȃ��B");
                continue;
            }
            
            //�R�|�R�j���O�C�������擾
            //OpLoginAdminService.getLoginAttributes()�ɂāA
            //���O�C�������s.���O�C���h�c�ɊY�����郍�O�C�������i�FMap�j���擾����B
            //���O�C���������擾�ł��Ȃ������ꍇ�́A�ȍ~�̏��������{���Ȃ��B�icontinue;�j
           
            OpLoginAdminService l_service = (OpLoginAdminService) 
                Services.getService(OpLoginAdminService.class);
               
            Map l_map = l_service.getLoginAttributes(l_lngAccountLoninId);
            if (l_map == null || l_map.size() == 0)
            {
                log.debug("l_map == null");
                continue;
            }
           
            //�R�|�S�j�����p�X���[�h�`�F�b�N
            //�擾�������O�C�������i�FMap�j���A�ȉ��̑������ɊY�����鑮���l���擾����B
            //WEB3_ENCRYPTED_INITIAL_PASSWORD

            Object l_obj = l_map.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);
            
            log.debug("Map size is " + l_map.size());
            
            if (l_obj == null)
            {
                log.debug("l_obj == null");
                continue;
            }
           
            //�R�|�T�j�ڋqList�i�FArrayList�j�ɗv�f�ǉ�
            //�R�|�S�j�Ŏ擾���������l������null�łȂ��ꍇ�A
            //�ڋqList�i�FArrayList�j�ɑΏیڋq�I�u�W�F�N�g��ǉ��iadd�j
            l_arrayListRecords.add(l_mainAccount);                
        }
        
        //�S�j�ԋp�l�擾
        WEB3GentradeMainAccount[] l_mainAccountNew = new WEB3GentradeMainAccount[l_arrayListRecords.size()];
        l_arrayListRecords.toArray(l_mainAccountNew);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_mainAccountNew;
    }
}
@
