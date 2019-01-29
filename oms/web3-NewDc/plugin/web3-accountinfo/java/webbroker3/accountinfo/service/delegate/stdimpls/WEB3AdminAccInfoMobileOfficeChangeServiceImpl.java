head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�XImpl(WEB3AdminAccInfoMobileOfficeChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
Revesion History : 2006/02/23 ������ (���u) ���f��No.086
Revesion History : 2006/03/20 ������ (���u) ���f��No.098  
Revesion History : 2006/10/9  ꎉ�   (���u) ���f��No.124    
Revesion History : 2006/10/30 ꎉ�   (���u) ���f��No.139
Revesion History : 2006/11/22 ����  (���u) �c�a�X�V�d�lNo.034 �����̖��No.004
Revesion History : 2006/11/28 ����  (���u) ���f��No.146 �c�a�X�V�d�lNo.039
Revesion History : 2007/01/18 �����q (���u) ���f��No.160,No.161,�c�a�X�V�d�lNo.042  
Revesion History : 2007/02/10 �Ӑ� (���u) �d�l�ύX���f��188                
Revesion History : 2009/02/12 SCS�哈 �d�l�ύX���f��254                
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMaster;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.WEB3AccInfoOccupationChangeRegistVoucherCreated;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistPK;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountInfoAcceptStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3OccupationcodeUpdateDef;
import webbroker3.common.define.WEB3RealnameUpdateDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstPK;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�XImpl)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�����N���X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMobileOfficeChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeChangeServiceImpl.class);

    
    /**
     * @@roseuid 418F3A05003E
     */
    public WEB3AdminAccInfoMobileOfficeChangeServiceImpl() 
    {
     
    }
    
    /**
     * �g�єԍ��E�Ζ�����ύX�\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B<BR> 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX()���R�[������B<BR> 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4148FC3000FD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistInputRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminAccInfoMobileOfficeRegistInputRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistConfirmRequest)
        {
            
            l_response = this.validateChange((WEB3AdminAccInfoMobileOfficeRegistConfirmRequest) l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistCompleteRequest)
        {
            
            l_response = this.submitChange((WEB3AdminAccInfoMobileOfficeRegistCompleteRequest) l_request);
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
     * �g�єԍ��E�Ζ�����ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�g�єԍ��E�Ζ�����ύX�jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse
     * @@roseuid 4148FC30011D
     */
    protected WEB3AdminAccInfoMobileOfficeRegistInputResponse getInputScreen(WEB3AdminAccInfoMobileOfficeRegistInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);     
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount = 
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);        
        
        //1.6) ���b�Z�[�W validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.7) get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist = 
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);    

        //1.10) ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X(l_request : WEB3GenRequest)
        WEB3AdminAccInfoMobileOfficeRegistInputResponse l_response = 
            (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
        
        //1.8) �ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A�ύX����𐶐�����   
        if(l_accInfoMobileOfficeInfoRegist != null)
        {    
            
            //1.8.1) getDataSourceObject( )
            MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow = 
                (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();
                
            //1.8.2) get�\���󋵋敪( )
             String l_strAccInfoRegistStateDiv = 
                 l_accInfoMobileOfficeInfoRegist.getRegistStateDiv();
                 
            //1.8.3) �g�єԍ��E�Ζ�����( )
            WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo =
                new WEB3AccInfoMobileOfficeInfo();    
            
            //1.8.4) �v���p�e�B�Z�b�g                
            //�g�єԍ��E�Ζ�����.�g�єԍ� = �ύX�\�����.�A����d�b�ԍ��i�g�сj
            l_accInfoMobileOfficeInfo.mobileTelephone = l_mobileOfficeInfoRegistRow.getMobile();
            
            //�g�єԍ��E�Ζ�����.�Ζ��於�� = �ύX�\�����.�Ζ��於��
            l_accInfoMobileOfficeInfo.officeName = l_mobileOfficeInfoRegistRow.getOffice();
            
            //�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = �ύX�\�����.�Ζ���X�֔ԍ�
            l_accInfoMobileOfficeInfo.officeZipCode = l_mobileOfficeInfoRegistRow.getOfficeZipCode();
            
            //�g�єԍ��E�Ζ�����.�Ζ���Z�� = �ύX�\�����.�Ζ���Z��
            l_accInfoMobileOfficeInfo.officeAdress = l_mobileOfficeInfoRegistRow.getOfficeAddress();
            
            //�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = �ύX�\�����.�Ζ���d�b�ԍ�
            l_accInfoMobileOfficeInfo.officeTelephone = l_mobileOfficeInfoRegistRow.getOfficeTelephone();
            
            //�g�єԍ��E�Ζ�����.��E�� = �ύX�\�����.��E
            l_accInfoMobileOfficeInfo.position = l_mobileOfficeInfoRegistRow.getPost();   
            
            //�g�єԍ��E�Ζ�����.�A����D�揇�� 1�� = �ύX�\�����.�A����D�揇�� 1��
            l_accInfoMobileOfficeInfo.contactPriority1 = l_mobileOfficeInfoRegistRow.getContactPriority1();
            
            //�g�єԍ��E�Ζ�����.�A����D�揇�� 2�� = �ύX�\�����.�A����D�揇�� 2��
            l_accInfoMobileOfficeInfo.contactPriority2 = l_mobileOfficeInfoRegistRow.getContactPriority2();
            
            //�g�єԍ��E�Ζ�����.�A����D�揇�� 3�� = �ύX�\�����.�A����D�揇�� 3��
            l_accInfoMobileOfficeInfo.contactPriority3 = l_mobileOfficeInfoRegistRow.getContactPriority3();
            
            //�g�єԍ��E�Ζ�����.�������̂P = �ύX�\�����.�������̂P  
            l_accInfoMobileOfficeInfo.accountRealName1 = l_mobileOfficeInfoRegistRow.getRealName1();
            
            //�g�єԍ��E�Ζ�����.�������̂Q = �ύX�\�����.�������̂Q 
            l_accInfoMobileOfficeInfo.accountRealName2 = l_mobileOfficeInfoRegistRow.getRealName2();
            
            //�g�єԍ��E�Ζ�����.�E�Ƌ敪 = �ύX�\�����.�E��
            l_accInfoMobileOfficeInfo.occupationDiv = l_mobileOfficeInfoRegistRow.getOccupationDiv();
            
            //�g�єԍ��E�Ζ�����.���� = �ύX�\�����.����
            l_accInfoMobileOfficeInfo.department = l_mobileOfficeInfoRegistRow.getDepartment();
            
            //�g�єԍ��E�Ζ�����.���� = �ύX�\�����.����  
            l_accInfoMobileOfficeInfo.nationality = l_mobileOfficeInfoRegistRow.getNationality();
            
            //�g�єԍ��E�Ζ�����.���Ђ��̑� = �ύX�\�����.���Ђ��̑� 
            l_accInfoMobileOfficeInfo.nationalityOther = l_mobileOfficeInfoRegistRow.getNationalityOther();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �ύX�\�����.��\�Җ��i�����j��
            l_accInfoMobileOfficeInfo.representFamilyName = l_mobileOfficeInfoRegistRow.getRepresentFamilyName();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �ύX�\�����.��\�Җ��i�����j��
            l_accInfoMobileOfficeInfo.representName = l_mobileOfficeInfoRegistRow.getRepresentGivenName();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �ύX�\�����.��\�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.representFamilyNameKana = l_mobileOfficeInfoRegistRow.getRepresentFamilyNameAlt1();
            
            //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �ύX�\�����.��\�Җ��i�J�i�j��  
            l_accInfoMobileOfficeInfo.representNameKana = l_mobileOfficeInfoRegistRow.getRepresentGivenNameAlt1();
            
            //�g�єԍ��E�Ζ�����.��\�Ҍ� = �ύX�\�����.��\�Ҍ�
            l_accInfoMobileOfficeInfo.representPower = l_mobileOfficeInfoRegistRow.getRepresentPower();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �ύX�\�����.�����ӔC�Җ��i�����j��
            l_accInfoMobileOfficeInfo.directorFamilyName = l_mobileOfficeInfoRegistRow.getDirectorFamilyName();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �ύX�\�����.�����ӔC�Җ��i�����j��
            l_accInfoMobileOfficeInfo.directorName = l_mobileOfficeInfoRegistRow.getDirectorGivenName();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �ύX�\�����.�����ӔC�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.directorFamilyNameKana = l_mobileOfficeInfoRegistRow.getDirectorFamilyNameAlt1();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �ύX�\�����.�����ӔC�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.directorNameKana = l_mobileOfficeInfoRegistRow.getDirectorGivenNameAlt1();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = �ύX�\�����.����ӔC�ҏ�������
            l_accInfoMobileOfficeInfo.directorDepartment = l_mobileOfficeInfoRegistRow.getDirectorDepartment();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = �ύX�\�����.����ӔC�Җ�E
            l_accInfoMobileOfficeInfo.directorPosition = l_mobileOfficeInfoRegistRow.getDirectorPost();
            
            //�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = �ύX�\�����.�����ӔC�җX�֔ԍ�
            l_accInfoMobileOfficeInfo.directorZipCode = l_mobileOfficeInfoRegistRow.getDirectorZipCode();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = �ύX�\�����.�����ӔC�ҏZ���P
            l_accInfoMobileOfficeInfo.directorAddress1 = l_mobileOfficeInfoRegistRow.getDirectorAddress1();

            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = �ύX�\�����.�����ӔC�ҏZ���Q
            l_accInfoMobileOfficeInfo.directorAddress2 = l_mobileOfficeInfoRegistRow.getDirectorAddress2();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = �ύX�\�����.�����ӔC�ҏZ���R
            l_accInfoMobileOfficeInfo.directorAddress3 = l_mobileOfficeInfoRegistRow.getDirectorAddress3();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = �ύX�\�����.�����ӔC�Ґ��N�����N��
            l_accInfoMobileOfficeInfo.directorEraBorn = l_mobileOfficeInfoRegistRow.getDirectorEraBorn();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = �ύX�\�����.�����ӔC�Ґ��N����
            l_accInfoMobileOfficeInfo.directorBornDate = l_mobileOfficeInfoRegistRow.getDirectorBornDate();
            
            //�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = �ύX�\�����.�����ӔC�҉�В��ʔԍ�
            l_accInfoMobileOfficeInfo.directorCorpDirect = l_mobileOfficeInfoRegistRow.getDirectorCorpTelephone();
            
            //�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = �ύX�\�����.���̑��̘A����i�g�сA����j  
            l_accInfoMobileOfficeInfo.directorOtherContact = l_mobileOfficeInfoRegistRow.getOtherContact();
            
            //�g�єԍ��E�Ζ�����.FAX�ԍ� = �ύX�\�����.FAX�ԍ�
            l_accInfoMobileOfficeInfo.faxTelephone = l_mobileOfficeInfoRegistRow.getFax();
            
            //�g�єԍ��E�Ζ�����.�N�� = �ύX�\�����.�N��
            l_accInfoMobileOfficeInfo.annualIncomeDiv = l_mobileOfficeInfoRegistRow.getAnnualIncomeDiv();
            
            //�g�єԍ��E�Ζ�����.���Z���Y�z = �ύX�\�����.���Z���Y�z
            l_accInfoMobileOfficeInfo.assetValueDiv = l_mobileOfficeInfoRegistRow.getAssetValueDiv();
            
            //�g�єԍ��E�Ζ�����.�^�p�\��z = �ύX�\�����.�^�p�\��z
            l_accInfoMobileOfficeInfo.fundBundgetAmountDiv = l_mobileOfficeInfoRegistRow.getFundBudgetAmountDiv();
            
            //�g�єԍ��E�Ζ�����.�����ړI = �ύX�\�����.�����ړI
            l_accInfoMobileOfficeInfo.investPurposeDiv = l_mobileOfficeInfoRegistRow.getInvestPurposeDiv();
            
            //�g�єԍ��E�Ζ�����.�����\����� = �ύX�\�����.�����\�����
            l_accInfoMobileOfficeInfo.investPlanPeriodDiv = l_mobileOfficeInfoRegistRow.getInvestPlanPeriodDiv();
            
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = �ύX�\�����.�����o���̗L���i�P�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag1IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag1= l_mobileOfficeInfoRegistRow.getExperienceFlag1() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = �ύX�\�����.�����o���̗L���i�Q�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag2IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag2= l_mobileOfficeInfoRegistRow.getExperienceFlag2() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = �ύX�\�����.�����o���̗L���i�R�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag3IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag3= l_mobileOfficeInfoRegistRow.getExperienceFlag3() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = �ύX�\�����.�����o���̗L���i�S�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag4IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag4= l_mobileOfficeInfoRegistRow.getExperienceFlag4() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = �ύX�\�����.�����o���̗L���i�T�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag5IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag5= l_mobileOfficeInfoRegistRow.getExperienceFlag5() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = �ύX�\�����.�����o���̗L���i�U�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag6IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag6= l_mobileOfficeInfoRegistRow.getExperienceFlag6() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = �ύX�\�����.�����o���̗L���i�V�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag7IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag7= l_mobileOfficeInfoRegistRow.getExperienceFlag7() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = �ύX�\�����.�����o���̗L���i�W�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag8IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag8= l_mobileOfficeInfoRegistRow.getExperienceFlag8() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = �ύX�\�����.�����o���̗L���i�X�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag9IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag9= l_mobileOfficeInfoRegistRow.getExperienceFlag9() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = �ύX�\�����.�����o���̗L���i�P�O�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag10IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag10= l_mobileOfficeInfoRegistRow.getExperienceFlag10() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���i�P�j = �ύX�\�����.�����o���i�P�j
            l_accInfoMobileOfficeInfo.experienceDiv1= l_mobileOfficeInfoRegistRow.getExperienceDiv1();
            
            //�g�єԍ��E�Ζ�����.�����o���i�Q�j = �ύX�\�����.�����o���i�Q�j
            l_accInfoMobileOfficeInfo.experienceDiv2= l_mobileOfficeInfoRegistRow.getExperienceDiv2();
            
            //�g�єԍ��E�Ζ�����.�����o���i�R�j = �ύX�\�����.�����o���i�R�j
            l_accInfoMobileOfficeInfo.experienceDiv3= l_mobileOfficeInfoRegistRow.getExperienceDiv3();
            
            //�g�єԍ��E�Ζ�����.�����o���i�S�j = �ύX�\�����.�����o���i�S�j
            l_accInfoMobileOfficeInfo.experienceDiv4= l_mobileOfficeInfoRegistRow.getExperienceDiv4();
            
            //�g�єԍ��E�Ζ�����.�����o���i�T�j = �ύX�\�����.�����o���i�T�j
            l_accInfoMobileOfficeInfo.experienceDiv5= l_mobileOfficeInfoRegistRow.getExperienceDiv5();
            
            //�g�єԍ��E�Ζ�����.�����o���i�U�j = �ύX�\�����.�����o���i�U�j
            l_accInfoMobileOfficeInfo.experienceDiv6= l_mobileOfficeInfoRegistRow.getExperienceDiv6();
            
            //�g�єԍ��E�Ζ�����.�����o���i�V�j = �ύX�\�����.�����o���i�V�j
            l_accInfoMobileOfficeInfo.experienceDiv7= l_mobileOfficeInfoRegistRow.getExperienceDiv7();
            
            //�g�єԍ��E�Ζ�����.�����o���i�W�j = �ύX�\�����.�����o���i�W�j
            l_accInfoMobileOfficeInfo.experienceDiv8= l_mobileOfficeInfoRegistRow.getExperienceDiv8();
            
            //�g�єԍ��E�Ζ�����.�����o���i�X�j = �ύX�\�����.�����o���i�X�j
            l_accInfoMobileOfficeInfo.experienceDiv9= l_mobileOfficeInfoRegistRow.getExperienceDiv9();
            
            //�g�єԍ��E�Ζ�����.�����o���i�P�O�j = �ύX�\�����.�����o���i�P�O�j
            l_accInfoMobileOfficeInfo.experienceDiv10= l_mobileOfficeInfoRegistRow.getExperienceDiv10();
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�P�j = �ύX�\�����.����̎�ށi�P�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.interest1= l_mobileOfficeInfoRegistRow.getInterestFlag1() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = �ύX�\�����.����̎�ށi�Q�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.interest2= l_mobileOfficeInfoRegistRow.getInterestFlag2() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�R�j = �ύX�\�����.����̎�ށi�R�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.interest3= l_mobileOfficeInfoRegistRow.getInterestFlag3() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�S�j = �ύX�\�����.����̎�ށi�S�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.interest4= l_mobileOfficeInfoRegistRow.getInterestFlag4() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�T�j = �ύX�\�����.����̎�ށi�T�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.interest5= l_mobileOfficeInfoRegistRow.getInterestFlag5() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�U�j = �ύX�\�����.����̎�ށi�U�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.interest6= l_mobileOfficeInfoRegistRow.getInterestFlag6() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�V�j = �ύX�\�����.����̎�ށi�V�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.interest7= l_mobileOfficeInfoRegistRow.getInterestFlag7() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�W�j = �ύX�\�����.����̎�ށi�W�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.interest8= l_mobileOfficeInfoRegistRow.getInterestFlag8() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�X�j = �ύX�\�����.����̎�ށi�X�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.interest9= l_mobileOfficeInfoRegistRow.getInterestFlag9() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = �ύX�\�����.����̎�ށi�P�O�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.interest10= l_mobileOfficeInfoRegistRow.getInterestFlag10() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = �ύX�\�����.�����J�݂̓��@@
            l_accInfoMobileOfficeInfo.appliMotivatDiv= l_mobileOfficeInfoRegistRow.getAppliMotivatDiv();
            
            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = �ύX�\�����.�����J�݂̓��@@�̏ڍ�
            l_accInfoMobileOfficeInfo.appliMotivatDetail= l_mobileOfficeInfoRegistRow.getAppliMotivatDivDetail();
            
            //�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = �ύX�\�����.���ݗ��p���Ă���،����
            l_accInfoMobileOfficeInfo.useInstitutionDiv= l_mobileOfficeInfoRegistRow.getUseInstitutionDiv();
            
            //�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = �ύX�\�����.�C���^�[�l�b�g����敪
            l_accInfoMobileOfficeInfo.internetTradeDiv= l_mobileOfficeInfoRegistRow.getInternetTradeDiv();
            
            //�g�єԍ��E�Ζ�����.�Љ�x�X = �ύX�\�����.�Љ�x�X����敪
            l_accInfoMobileOfficeInfo.introduceBranch= l_mobileOfficeInfoRegistRow.getIntroduceBranchCode();
            
            //1.11) �v���p�e�B�Z�b�g                
            l_response.changedMobileOfficeInfo = l_accInfoMobileOfficeInfo;
            
            //�ȊO�Aget�\���󋵋敪()
            l_response.applyStateDiv = l_strAccInfoRegistStateDiv;
            if (l_request.judgementFlag)
            {
            	MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);
            	//����m�F���t���O 1�FTRUE�i����m�F���j
            	l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.TRUE);
            	//�X�V�҃R�[�h �Ǘ���.�Ǘ��҃R�[�h
            	l_mobileOfficeInfoRegistParams.setLastUpdater(l_administrator.getAdministratorCode());
            	//�X�V���� ���������@@��TradingSystem.getSystemTimestamp() 
            	l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                QueryProcessor l_queryProcessor;
                try 
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
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
            }          
        }        
        else
        {
            
            //�ύX�\����񂪂Ȃ��ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() == null�j�Anull
            l_response.changedMobileOfficeInfo = null;
            
            //���X�|���X�f�[�^.�\���󋵋敪
            //�|�ύX�\����񂪂Ȃ��ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() == null�j�Anull�B
            l_response.applyStateDiv = null;
        }

        //1.9) create�ڋq��{���(�ڋq : �ڋq)
        WEB3AccInfoAccountBaseInfoCreatedService l_service = 
            (WEB3AccInfoAccountBaseInfoCreatedService)Services.getService(WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoAccountBaseInfo l_accInfoAccountBaseInfo = 
            l_service.createAccountBaseInfo(l_mainAccount); 
            
        //���X�|���X�f�[�^.�ύX�O���F�@@create�ڋq��{���().�g�єԍ��E�Ζ�����
        l_response.preMobileOfficeInfo = l_accInfoAccountBaseInfo.mobileOfficeInfo;
        //���X�|���X�f�[�^.�����^�C�v�F�@@create�ڋq��{���().�����^�C�v
        l_response.accountType = l_accInfoAccountBaseInfo.accountType;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * �g�єԍ��E�Ζ�����ύX�m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�g�єԍ��E�Ζ�����ύX�jvalidate�ύX�v�Q�ƁB <BR>
     * ===================================================================== <BR>
     * �V�[�P���X�} �u�Ǘ��҂��q�l���i�g�єԍ��E�Ζ�����ύX�jvalidate�ύX�v<BR>
     * (validate�ύX)<BR>
     * 1.2 �ύX���ږ��̏ꍇ�iis�ύX���ځi�j==FALSE�j�A�u�ύX���ږ����G���[�v��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02688 <BR>
     * ===================================================================== <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse
     * @@roseuid 4148FC30012C
     */
    protected WEB3AdminAccInfoMobileOfficeRegistConfirmResponse validateChange(
        WEB3AdminAccInfoMobileOfficeRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1  is�ύX����(�g�єԍ��E�Ζ�����, �g�єԍ��E�Ζ�����)
        boolean l_blnIsChangedItem = WEB3AccInfoMobileOfficeInfoRegist.isChangedItem(
            l_request.preMobileOfficeInfo,
            l_request.changedMobileOfficeInfo);
        
        //1.2 �ύX���ږ��̏ꍇ�iis�ύX���ځi�j== FALSE�j�A�u�ύX���ږ����G���[�v��O���X���[����B 
        if (!l_blnIsChangedItem)
        {
            log.debug("�ύX���ږ����G���[");
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ږ����G���[");
        }
        
        //1.3) validate( )
        l_request.validate();
        
        //1.4) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.5) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.6) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.7) get�ڋq(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
               
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);
        
        //1.8) is�A����D�揇��
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.9) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.10) get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.11) �ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A����m�F���t���O���X�V����
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.11.1) getDataSourceObject( )
            MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());
            
            //1.11.2) doUpdateQuery(PrimaryKey, String, Object[], Map)
            l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.TRUE);
            l_mobileOfficeInfoRegistParams.setLastUpdater(l_administrator.getAdministratorCode());
            l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();              
                l_queryProcessor.doUpdateQuery(l_mobileOfficeInfoRegistParams);             
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }          
        }
   
        //1.12) createResponse( )
        WEB3AdminAccInfoMobileOfficeRegistConfirmResponse l_response = 
            (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
         
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * �g�єԍ��E�Ζ�����ύX�����������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i�g�єԍ��E�Ζ�����ύX�jsubmit�ύX�v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse
     * @@roseuid 4148FC30014B
     */
    protected WEB3AdminAccInfoMobileOfficeRegistCompleteResponse submitChange(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        
        //1.4) get�ڋq(String, String, String)
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);
        
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //���X�R�[�h
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //�ڋq�R�[�h
        String l_accountCode = l_mainAccount.getAccountCode();
        
        //1.5) lock����             
        l_accManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_accountCode);
        
        
        //1.6) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.7) validate����p�X���[�h(String)
        l_administrator.validateTradingPassword(l_request.password);
         
        //1.8) is�A����D�揇��
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.9) validate���X����(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.10) get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.11) get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams;
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParamsTemp;
        //1.12) �ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A��������X�V����
        if(l_accInfoMobileOfficeInfoRegist != null)
        {        
            //1.12.1) createForUpdateParams( )
            l_accInfoMobileOfficeInfoRegist.createForUpdateParams();
            
            //1.12.2) set����(String, String)
            l_accInfoMobileOfficeInfoRegist.setDecision(l_strAdministratorCode,l_request.judgmentResultDiv);
            
            //1.12.3) getDataSourceObject( )
            l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());

            l_mobileOfficeInfoRegistParamsTemp = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());

            l_mobileOfficeInfoRegistParams.setMobile(l_request.changedMobileOfficeInfo.mobileTelephone);
            l_mobileOfficeInfoRegistParams.setOffice(l_request.changedMobileOfficeInfo.officeName);
            l_mobileOfficeInfoRegistParams.setOfficeZipCode(l_request.changedMobileOfficeInfo.officeZipCode);
            l_mobileOfficeInfoRegistParams.setOfficeAddress(l_request.changedMobileOfficeInfo.officeAdress);
            l_mobileOfficeInfoRegistParams.setOfficeTelephone(l_request.changedMobileOfficeInfo.officeTelephone);
            l_mobileOfficeInfoRegistParams.setPost(l_request.changedMobileOfficeInfo.position);
            l_mobileOfficeInfoRegistParams.setContactPriority1(l_request.changedMobileOfficeInfo.contactPriority1);
            l_mobileOfficeInfoRegistParams.setContactPriority2(l_request.changedMobileOfficeInfo.contactPriority2);
            l_mobileOfficeInfoRegistParams.setContactPriority3(l_request.changedMobileOfficeInfo.contactPriority3);
            
            //DB�X�V�d�l.: �Ǘ��ҁE�g�єԍ��E�Ζ�����ύX_�g�єԍ��E�Ζ�����ύX�\���e�[�u���A(CITI��)
            //�ڋq�������̂P: �g�єԍ��E�Ζ�����.�ڋq�������̂P�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRealName1(l_request.changedMobileOfficeInfo.accountRealName1);
            //�ڋq�������̂Q: �g�єԍ��E�Ζ�����.�ڋq�������̂Q�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRealName2(l_request.changedMobileOfficeInfo.accountRealName2);
            //�E�Ƌ敪: �g�єԍ��E�Ζ�����.�E�Ƌ敪�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setOccupationDiv(l_request.changedMobileOfficeInfo.occupationDiv);
            //����: �g�єԍ��E�Ζ�����.�����i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDepartment(l_request.changedMobileOfficeInfo.department);
            //����: �g�єԍ��E�Ζ�����.���Ёi* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setNationality(l_request.changedMobileOfficeInfo.nationality);
            //���Ђ��̑�: �g�єԍ��E�Ζ�����.���Ђ��̑��i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setNationalityOther(l_request.changedMobileOfficeInfo.nationalityOther);
            //��\�Җ��i�����j��: �g�єԍ��E�Ζ�����.��\�Җ��i�����j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRepresentFamilyName(l_request.changedMobileOfficeInfo.representFamilyName);
            //��\�Җ��i�����j��: �g�єԍ��E�Ζ�����.��\�Җ��i�����j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRepresentGivenName(l_request.changedMobileOfficeInfo.representName);
            //��\�Җ��i�J�i�j��: �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRepresentFamilyNameAlt1(l_request.changedMobileOfficeInfo.representFamilyNameKana);
            //��\�Җ��i�J�i�j��: �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRepresentGivenNameAlt1(l_request.changedMobileOfficeInfo.representNameKana);
            //��\�Ҍ�: �g�єԍ��E�Ζ�����.��\�Ҍ��i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setRepresentPower(l_request.changedMobileOfficeInfo.representPower);
            //����ӔC�Җ��i�����j��: �g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorFamilyName(l_request.changedMobileOfficeInfo.directorFamilyName);
            //����ӔC�Җ��i�����j��: �g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorGivenName(l_request.changedMobileOfficeInfo.directorName);
            //����ӔC�Җ��i�J�i�j��: �g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorFamilyNameAlt1(l_request.changedMobileOfficeInfo.directorFamilyNameKana);
            //����ӔC�Җ��i�J�i�j��: �g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorGivenNameAlt1(l_request.changedMobileOfficeInfo.directorNameKana);
            //����ӔC�ҁ@@��������: �g�єԍ��E�Ζ�����.����ӔC�ҁ@@���������i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorDepartment(l_request.changedMobileOfficeInfo.directorDepartment);
            //����ӔC�ҁ@@��E: �g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorPost(l_request.changedMobileOfficeInfo.directorPosition);
            //����ӔC�җX�֔ԍ�: �g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ��i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorZipCode(l_request.changedMobileOfficeInfo.directorZipCode);
            //����ӔC�ҏZ���P: �g�єԍ��E�Ζ�����.����ӔC�ҏZ���P�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorAddress1(l_request.changedMobileOfficeInfo.directorAddress1);
            //����ӔC�ҏZ���Q: �g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorAddress2(l_request.changedMobileOfficeInfo.directorAddress2);
            //����ӔC�ҏZ���R: �g�єԍ��E�Ζ�����.����ӔC�ҏZ���R�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorAddress3(l_request.changedMobileOfficeInfo.directorAddress3);
            //����ӔC�Ґ��N�����@@�N��: �g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorEraBorn(l_request.changedMobileOfficeInfo.directorEraBorn);
            //����ӔC�Ґ��N����: �g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorBornDate(l_request.changedMobileOfficeInfo.directorBornDate);
            //����ӔC�҉�В��ʔԍ�: �g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ��i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setDirectorCorpTelephone(l_request.changedMobileOfficeInfo.directorCorpDirect);
            //���̑��A����i�g�сA����j: �g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setOtherContact(l_request.changedMobileOfficeInfo.directorOtherContact);
            //FAX�ԍ�: �g�єԍ��E�Ζ�����.FAX�ԍ��i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setFax(l_request.changedMobileOfficeInfo.faxTelephone);
            //�N��: �g�єԍ��E�Ζ�����.�N���i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setAnnualIncomeDiv(l_request.changedMobileOfficeInfo.annualIncomeDiv);
            //���Z���Y�z: �g�єԍ��E�Ζ�����.���Z���Y�z�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setAssetValueDiv(l_request.changedMobileOfficeInfo.assetValueDiv);
            //�^�p�\��z: �g�єԍ��E�Ζ�����.�^�p�\��z�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setFundBudgetAmountDiv(l_request.changedMobileOfficeInfo.fundBundgetAmountDiv);
            //�����ړI: �g�єԍ��E�Ζ�����.�����ړI�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setInvestPurposeDiv(l_request.changedMobileOfficeInfo.investPurposeDiv);
            //�����\�����: �g�єԍ��E�Ζ�����.�����\����ԁi* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setInvestPlanPeriodDiv(l_request.changedMobileOfficeInfo.investPlanPeriodDiv);
            //�����o���̗L���i�P�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�P�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag1 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag1 != null)
            {
                l_intexperienceFlag1 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag1);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag1(l_intexperienceFlag1);
            //�����o���̗L���i�Q�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag2 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag2 != null)
            {
                l_intexperienceFlag2 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag2);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag2(l_intexperienceFlag2);
            //�����o���̗L���i�R�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�R�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag3 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag3 != null)
            {
                l_intexperienceFlag3 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag3);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag3(l_intexperienceFlag3);
            //�����o���̗L���i�S�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�S�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag4 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag4 != null)
            {
                l_intexperienceFlag4 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag4);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag4(l_intexperienceFlag4);
            //�����o���̗L���i�T�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�T�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag5 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag5 != null)
            {
                l_intexperienceFlag5 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag5);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag5(l_intexperienceFlag5);
            //�����o���̗L���i�U�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�U�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag6 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag6 != null)
            {
                l_intexperienceFlag6 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag6);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag6(l_intexperienceFlag6);
            //�����o���̗L���i�V�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�V�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag7 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag7 != null)
            {
                l_intexperienceFlag7 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag7);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag7(l_intexperienceFlag7);
            //�����o���̗L���i�W�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�W�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag8 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag8 != null)
            {
                l_intexperienceFlag8 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag8);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag8(l_intexperienceFlag8);
            //�����o���̗L���i�X�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�X�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag9 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag9 != null)
            {
                l_intexperienceFlag9 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag9);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag9(l_intexperienceFlag9);
            //�����o���̗L���i�P�O�j: �g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j�i* ��ʓ��͒l�j
            Integer l_intexperienceFlag10 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag10 != null)
            {
                l_intexperienceFlag10 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag10);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag10(l_intexperienceFlag10);
            //�����o���i�P�j: �g�єԍ��E�Ζ�����.�����o���i�P�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv1(l_request.changedMobileOfficeInfo.experienceDiv1);
            //�����o���i�Q�j: �g�єԍ��E�Ζ�����.�����o���i�Q�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv2(l_request.changedMobileOfficeInfo.experienceDiv2);
            //�����o���i�R�j: �g�єԍ��E�Ζ�����.�����o���i�R�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv3(l_request.changedMobileOfficeInfo.experienceDiv3);
            //�����o���i�S�j: �g�єԍ��E�Ζ�����.�����o���i�S�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv4(l_request.changedMobileOfficeInfo.experienceDiv4);
            //�����o���i�T�j: �g�єԍ��E�Ζ�����.�����o���i�T�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv5(l_request.changedMobileOfficeInfo.experienceDiv5);
            //�����o���i�U�j: �g�єԍ��E�Ζ�����.�����o���i�U�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv6(l_request.changedMobileOfficeInfo.experienceDiv6);
            //�����o���i�V�j: �g�єԍ��E�Ζ�����.�����o���i�V�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv7(l_request.changedMobileOfficeInfo.experienceDiv7);
            //�����o���i�W�j: �g�єԍ��E�Ζ�����.�����o���i�W�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv8(l_request.changedMobileOfficeInfo.experienceDiv8);
            //�����o���i�X�j: �g�єԍ��E�Ζ�����.�����o���i�X�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv9(l_request.changedMobileOfficeInfo.experienceDiv9);
            //�����o���i�P�O�j: �g�єԍ��E�Ζ�����.�����o���i�P�O�j�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setExperienceDiv10(l_request.changedMobileOfficeInfo.experienceDiv10);
            //����̎�ށi�P�j: �g�єԍ��E�Ζ�����.����̎�ށi�P�j�i* ��ʓ��͒l�j
            Integer l_intInterest1 = null;
            if (l_request.changedMobileOfficeInfo.interest1 != null)
            {
                l_intInterest1 = new Integer(l_request.changedMobileOfficeInfo.interest1);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag1(l_intInterest1);
            //����̎�ށi�Q�j: �g�єԍ��E�Ζ�����.����̎�ށi�Q�j�i* ��ʓ��͒l�j
            Integer l_intInterest2 = null;
            if (l_request.changedMobileOfficeInfo.interest2 != null)
            {
                l_intInterest2 = new Integer(l_request.changedMobileOfficeInfo.interest2);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag2(l_intInterest2);
            //����̎�ށi�R�j: �g�єԍ��E�Ζ�����.����̎�ށi�R�j�i* ��ʓ��͒l�j
            Integer l_intInterest3 = null;
            if (l_request.changedMobileOfficeInfo.interest3 != null)
            {
                l_intInterest3 = new Integer(l_request.changedMobileOfficeInfo.interest3);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag3(l_intInterest3);
            //����̎�ށi�S�j: �g�єԍ��E�Ζ�����.����̎�ށi�S�j�i* ��ʓ��͒l�j
            Integer l_intInterest4 = null;
            if (l_request.changedMobileOfficeInfo.interest4 != null)
            {
                l_intInterest4 = new Integer(l_request.changedMobileOfficeInfo.interest4);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag4(l_intInterest4);
            //����̎�ށi�T�j: �g�єԍ��E�Ζ�����.����̎�ށi�T�j�i* ��ʓ��͒l�j
            Integer l_intInterest5 = null;
            if (l_request.changedMobileOfficeInfo.interest5 != null)
            {
                l_intInterest5 = new Integer(l_request.changedMobileOfficeInfo.interest5);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag5(l_intInterest5);
            //����̎�ށi�U�j: �g�єԍ��E�Ζ�����.����̎�ށi�U�j�i* ��ʓ��͒l�j
            Integer l_intInterest6 = null;
            if (l_request.changedMobileOfficeInfo.interest6 != null)
            {
                l_intInterest6 = new Integer(l_request.changedMobileOfficeInfo.interest6);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag6(l_intInterest6);
            //����̎�ށi�V�j: �g�єԍ��E�Ζ�����.����̎�ށi�V�j�i* ��ʓ��͒l�j
            Integer l_intInteres7 = null;
            if (l_request.changedMobileOfficeInfo.interest7 != null)
            {
                l_intInteres7 = new Integer(l_request.changedMobileOfficeInfo.interest7);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag7(l_intInteres7);
            //����̎�ށi�W�j: �g�єԍ��E�Ζ�����.����̎�ށi�W�j�i* ��ʓ��͒l�j
            Integer l_intInteres8 = null;
            if (l_request.changedMobileOfficeInfo.interest8 != null)
            {
                l_intInteres8 = new Integer(l_request.changedMobileOfficeInfo.interest8);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag8(l_intInteres8);
            //����̎�ށi�X�j: �g�єԍ��E�Ζ�����.����̎�ށi�X�j�i* ��ʓ��͒l�j
            Integer l_intInteres9 = null;
            if (l_request.changedMobileOfficeInfo.interest9 != null)
            {
                l_intInteres9 = new Integer(l_request.changedMobileOfficeInfo.interest9);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag9(l_intInteres9);
            //����̎�ށi�P�O�j: �g�єԍ��E�Ζ�����.����̎�ށi�P�O�j�i* ��ʓ��͒l�j
            Integer l_intInteres10 = null;
            if (l_request.changedMobileOfficeInfo.interest10 != null)
            {
                l_intInteres10 = new Integer(l_request.changedMobileOfficeInfo.interest10);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag10(l_intInteres10);
            //�����J�݂̓��@@: �g�єԍ��E�Ζ�����.�����J�݂̓��@@�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setAppliMotivatDiv(l_request.changedMobileOfficeInfo.appliMotivatDiv);
            //�����J�݂̓��@@�̏ڍ�: �g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍׁi* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setAppliMotivatDivDetail(l_request.changedMobileOfficeInfo.appliMotivatDetail);
            //���ݗ��p���Ă���،����: �g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���Ёi* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setUseInstitutionDiv(l_request.changedMobileOfficeInfo.useInstitutionDiv);
            //�C���^�[�l�b�g����敪: �g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setInternetTradeDiv(l_request.changedMobileOfficeInfo.internetTradeDiv);
            //�Љ�x�X: �g�єԍ��E�Ζ�����.�Љ�x�X�i* ��ʓ��͒l�j
            l_mobileOfficeInfoRegistParams.setIntroduceBranchCode(l_request.changedMobileOfficeInfo.introduceBranch);
            
            //1.12.4) doUpdateQuery(Row)
            try
            {
                
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }     
        }
            
        //1.13) �ύX�\����񂪂Ȃ��ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() == null�j�A�ύX�\������V�K�쐬����
        else
        {
            
            //1.13.1) createNew�g�єԍ��E�Ζ�����ύX�\��(�ڋq, �g�єԍ��E�Ζ�����, String)
            WEB3AccInfoMobileOfficeInfoRegist l_createNewMobileOfficeInfoRegist =
                WEB3AccInfoMobileOfficeInfoRegist.createNewMobileOfficeInfoRegist(
                    l_mainAccount,
                    l_request.changedMobileOfficeInfo,
                    l_strAdministratorCode);
            
            //1.13.2) set����(String, String)
            l_createNewMobileOfficeInfoRegist.setDecision(l_strAdministratorCode,l_request.judgmentResultDiv);
            
            //1.13.3) getDataSourceObject()
            l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_createNewMobileOfficeInfoRegist.getDataSourceObject());

            l_mobileOfficeInfoRegistParamsTemp = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_createNewMobileOfficeInfoRegist.getDataSourceObject());
            
            //1.13.4) doInsertQuery(String, Row)
            try
            {
                
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doInsertQuery(l_mobileOfficeInfoRegistParams);
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
        } 
        
        if(WEB3JudgmentResultDivDef.CONSENT.equals(l_request.judgmentResultDiv))
        {
        
            //1.14.1) getDataSourceObject( )
            MainAccountParams l_mainAccountParams = 
                new MainAccountParams((MainAccountRow)l_mainAccount.getDataSourceObject());
        
            //1.14.2) doUpdateQuery(PrimaryKey, String, Object[], Map)
            Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
        
            l_mainAccountParams.setMobile(l_mobileOfficeInfoRegistParams.mobile);
            l_mainAccountParams.setOffice(l_mobileOfficeInfoRegistParams.office);
            l_mainAccountParams.setOfficeZipCode(l_mobileOfficeInfoRegistParams.office_zip_code);
            l_mainAccountParams.setOfficeAddress(l_mobileOfficeInfoRegistParams.office_address);
            l_mainAccountParams.setOfficeTelephone(l_mobileOfficeInfoRegistParams.office_telephone);
            l_mainAccountParams.setPost(l_mobileOfficeInfoRegistParams.post);
            l_mainAccountParams.setMbOffLastUpdater(l_strAdministratorCode);
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(l_systemTimestamp);
            l_mainAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
        
            try
            {
            
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doUpdateQuery(l_mainAccountParams);
            }
            catch (DataFindException l_ex)
            {
            
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
            
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
            
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  

            // get�⏕����(����ID : , �⏕�����^�C�v : )
            // �⏕�������擾
            // [get�⏕�����i�j�Ɏw�肷�����]
            // ����ID �F �ڋq�I�u�W�F�N�g.getAccountId()
            // �⏕�����^�C�v �F SubAccountTypeEnum EQUITY_SUB_ACCOUNT
            SubAccountTypeEnum l_subAccountTyoeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            long l_lngAccountId = l_mainAccount.getAccountId();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_gentradeAccountManager.getSubAccount(l_lngAccountId,l_subAccountTyoeEnum);
            }
            catch (NotFoundException l_ex)
            {
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // get���X�v���t�@@�����X(�⏕����)
            // �ڋq�I�u�W�F�N�g���A���X�p�v���t�@@�����X�e�[�u������
            // �ڋq�������̍X�V�A�E�ƃR�[�h�X�V�`�F�b�N���擾����B
            // [get���X�v���t�@@�����X()�Ɏw�肷�����]
            // �⏕���� �F get�⏕�����i�j�̖߂�l
            String[] l_intBranchPreferences = this.getBranchPreferences(l_subAccount);

            //1.14.3)�������}�X�^�f�[�^���擾����B
            WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
            WEB3AccInfoMaster l_accInfoMasterTemp = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
            
            //1.15)����t���[
            //�������}�X�^��񂪂���ꍇ�iget�������}�X�^() != null�j�A�������}�X�^�����X�V����B
            AccountInfoMstParams l_accountInfoMstParams = null;
            if (l_accInfoMaster != null)
            {
                //1.15.1)�������}�X�^�s�I�u�W�F�N�g���擾����B
                l_accountInfoMstParams = 
                    new AccountInfoMstParams((AccountInfoMstParams)l_accInfoMaster.getDataSourceObject());
                //1.15.2)�������}�X�^�e�[�u�����X�V����B
                //(*4) �������}�X�^�e�[�u���X�V
                //�������}�X�^�����X�V�iDB Update�j����B
                //�@@�X�V���e�́y��Trade�z�⑫����.DB�X�V�u�������}�X�^�e�[�u���X�V�d�l.xls�i�u�������}�X�^�v�X�V�d�l�iUpdate�j�V�[�g�j�v�Q�ƁB
                
                //�X�V���� = ���������@@��TradingSystem.getSystemTimestamp()
                l_accountInfoMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //�X�V�҃R�[�h = �Ǘ���.�Ǘ��҃R�[�h
                l_accountInfoMstParams.setLastUpdater(l_strAdministratorCode);
                
                //this.get�v���t�@@�����X()�̖߂�l.index(1)��"1"�̏ꍇ�A
                //�g�єԍ��E�Ζ�����ύX�\��.�������̂P
                //����ȊO�A�����l
                if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                {
                    l_accountInfoMstParams.setRealName1(l_request.changedMobileOfficeInfo.accountRealName1);
                }

                //this.get�v���t�@@�����X()�̖߂�l.index(1)��"1"�̏ꍇ�A
                //�g�єԍ��E�Ζ�����ύX�\��.�������̂Q
                //����ȊO�A�����l
                if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                {
                    l_accountInfoMstParams.setRealName2(l_request.changedMobileOfficeInfo.accountRealName2);
                }

                //this.get�v���t�@@�����X()�̖߂�l.index(0)��"1"�̏ꍇ�A
                //�g�єԍ��E�Ζ�����ύX�\��.�E��
                //����ȊO�A�����l
                if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                {
                    l_accountInfoMstParams.setOccupationDiv(l_request.changedMobileOfficeInfo.occupationDiv);
                }

                //��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�����j��
                l_accountInfoMstParams.setRepresentFamilyName(l_request.changedMobileOfficeInfo.representFamilyName);
                
                //��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�����j��
                l_accountInfoMstParams.setRepresentGivenName(l_request.changedMobileOfficeInfo.representName);
                
                //��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�J�i�j��
                l_accountInfoMstParams.setRepresentFamilyNameAlt1(l_request.changedMobileOfficeInfo.representFamilyNameKana);
                
                //��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����ύX�\��.��\�Җ��i�J�i�j��
                l_accountInfoMstParams.setRepresentGivenNameAlt1(l_request.changedMobileOfficeInfo.representNameKana);
                
                //��\�Ҍ� = �g�єԍ��E�Ζ�����ύX�\��.��\�Ҍ�
                l_accountInfoMstParams.setRepresentPower(l_request.changedMobileOfficeInfo.representPower);
                
                //����ӔC�Җ��i�����j�� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�����j��
                l_accountInfoMstParams.setDirectorFamilyName(l_request.changedMobileOfficeInfo.directorFamilyName);
                
                //����ӔC�Җ��i�����j�� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�����j��
                l_accountInfoMstParams.setDirectorGivenName(l_request.changedMobileOfficeInfo.directorName);
                
                //����ӔC�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�J�i�j��
                l_accountInfoMstParams.setDirectorFamilyNameAlt1(l_request.changedMobileOfficeInfo.directorFamilyNameKana);
                
                //����ӔC�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Җ��i�J�i�j��
                l_accountInfoMstParams.setDirectorGivenNameAlt1(l_request.changedMobileOfficeInfo.directorNameKana);
                
                //����ӔC�ҁ@@�������� = �g�єԍ��E�Ζ�����ύX�\��.����ӔC�ҏ�������
                l_accountInfoMstParams.setDirectorDepartment(l_request.changedMobileOfficeInfo.directorDepartment);
                
                //����ӔC�ҁ@@��E = �g�єԍ��E�Ζ�����ύX�\��.����ӔC�Җ�E
                l_accountInfoMstParams.setDirectorPost(l_request.changedMobileOfficeInfo.directorPosition);
                
                //����ӔC�җX�֔ԍ� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�җX�֔ԍ�
                l_accountInfoMstParams.setDirectorZipCode(l_request.changedMobileOfficeInfo.directorZipCode);
                
                //����ӔC�ҏZ���P = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�ҏZ���P
                l_accountInfoMstParams.setDirectorAddress1(l_request.changedMobileOfficeInfo.directorAddress1);
                
                //����ӔC�ҏZ���Q = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�ҏZ���Q
                l_accountInfoMstParams.setDirectorAddress2(l_request.changedMobileOfficeInfo.directorAddress2);
                
                //����ӔC�ҏZ���R = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�ҏZ���R
                l_accountInfoMstParams.setDirectorAddress3(l_request.changedMobileOfficeInfo.directorAddress3);
                
                //����ӔC�Ґ��N�����@@�N�� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Ґ��N�����N��
                l_accountInfoMstParams.setDirectorEraBorn(l_request.changedMobileOfficeInfo.directorEraBorn);
                
                //����ӔC�Ґ��N���� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�Ґ��N����
                l_accountInfoMstParams.setDirectorBornDate(l_request.changedMobileOfficeInfo.directorBornDate);
                
                //����ӔC�҉�В��ʔԍ� = �g�єԍ��E�Ζ�����ύX�\��.�����ӔC�҉�В��ʔԍ�
                l_accountInfoMstParams.setDirectorCorpTelephone(l_request.changedMobileOfficeInfo.directorCorpDirect);
                
                //���̑��A����i�g�сA����j = �g�єԍ��E�Ζ�����ύX�\��.���̑��̘A����
                l_accountInfoMstParams.setOtherContact(l_request.changedMobileOfficeInfo.directorOtherContact);
                
                //���� = �g�єԍ��E�Ζ�����ύX�\��.����
                l_accountInfoMstParams.setDepartment(l_request.changedMobileOfficeInfo.department);
                
                //�A����D�揇�� 1�� = �g�єԍ�.�Ζ�����ύX�\���D�A����D�揇�� 1��
                l_accountInfoMstParams.setContactPriority1(l_request.changedMobileOfficeInfo.contactPriority1);
                
                //�A����D�揇�� 2�� = �g�єԍ�.�Ζ�����ύX�\���D�A����D�揇�� 2��
                l_accountInfoMstParams.setContactPriority2(l_request.changedMobileOfficeInfo.contactPriority2);
                
                //�A����D�揇�� 3�� = �g�єԍ�.�Ζ�����ύX�\���D�A����D�揇�� 3��
                l_accountInfoMstParams.setContactPriority3(l_request.changedMobileOfficeInfo.contactPriority3);
                
                //���� = �g�єԍ�.�Ζ�����ύX�\���D����
                l_accountInfoMstParams.setNationality(l_request.changedMobileOfficeInfo.nationality);
                
                //���Ђ��̑� = �g�єԍ�.�Ζ�����ύX�\���D���Ђ��̑�
                l_accountInfoMstParams.setNationalityOther(l_request.changedMobileOfficeInfo.nationalityOther);
                
                //�������}�X�^Params.FAX�ԍ� = �g�єԍ��E�Ζ�����.FAX�ԍ�      
                l_accountInfoMstParams.setFax(l_request.changedMobileOfficeInfo.faxTelephone);      
                        
                //   �@@�������}�X�^Params.�N�� = �g�єԍ��E�Ζ�����.�N�� 
                l_accountInfoMstParams.setAnnualIncomeDiv(l_request.changedMobileOfficeInfo.annualIncomeDiv);       
                        
                //   �@@�������}�X�^Params.���Z���Y�z = �g�єԍ��E�Ζ�����.���Z���Y�z 
                l_accountInfoMstParams.setAssetValueDiv(l_request.changedMobileOfficeInfo.assetValueDiv);       
                        
                //   �@@�������}�X�^Params.�^�p�\��z = �g�єԍ��E�Ζ�����.�^�p�\��z 
                l_accountInfoMstParams.setFundBudgetAmountDiv(l_request.changedMobileOfficeInfo.fundBundgetAmountDiv);      
                        
                //   �@@�������}�X�^Params.�����ړI = �g�єԍ��E�Ζ�����.�����ړI 
                l_accountInfoMstParams.setInvestPurposeDiv(l_request.changedMobileOfficeInfo.investPurposeDiv);     
                        
                //   �@@�������}�X�^Params.�����\����� = �g�єԍ��E�Ζ�����.�����\����� 
                l_accountInfoMstParams.setInvestPlanPeriodDiv(l_request.changedMobileOfficeInfo.investPlanPeriodDiv);       
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�P�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�j 
                Integer l_intExperienceFlag1 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag1 != null)      
                {       
                    l_intExperienceFlag1 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag1);      
                }       
                l_accountInfoMstParams.setExperienceFlag1(l_intExperienceFlag1);        
                        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�Q�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j
                Integer l_intExperienceFlag2 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag2 != null)      
                {       
                    l_intExperienceFlag2 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag2);      
                }       
                l_accountInfoMstParams.setExperienceFlag2(l_intExperienceFlag2);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�R�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�R�j
                Integer l_intExperienceFlag3 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag3 != null)      
                {       
                    l_intExperienceFlag3 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag3);      
                }       
                l_accountInfoMstParams.setExperienceFlag3(l_intExperienceFlag3);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�S�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�S�j
                Integer l_intExperienceFlag4 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag4 != null)      
                {       
                    l_intExperienceFlag4 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag4);      
                }       
                l_accountInfoMstParams.setExperienceFlag4(l_intExperienceFlag4);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�T�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�T�j 
                Integer l_intExperienceFlag5 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag5 != null)      
                {       
                    l_intExperienceFlag5 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag5);      
                }       
                l_accountInfoMstParams.setExperienceFlag5(l_intExperienceFlag5);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�U�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�U�j 
                Integer l_intExperienceFlag6 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag6 != null)      
                {       
                    l_intExperienceFlag6 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag6);      
                }       
                l_accountInfoMstParams.setExperienceFlag6(l_intExperienceFlag6);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�V�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�V�j
                Integer l_intExperienceFlag7 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag7 != null)      
                {       
                    l_intExperienceFlag7 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag7);      
                }       
                l_accountInfoMstParams.setExperienceFlag7(l_intExperienceFlag7);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�W�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�W�j
                Integer l_intExperienceFlag8 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag8 != null)      
                {       
                    l_intExperienceFlag8 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag8);      
                }       
                l_accountInfoMstParams.setExperienceFlag8(l_intExperienceFlag8);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�X�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�X�j 
                Integer l_intExperienceFlag9 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag9 != null)      
                {       
                    l_intExperienceFlag9 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag9);      
                }       
                l_accountInfoMstParams.setExperienceFlag9(l_intExperienceFlag9);        
                        
                //   �@@�������}�X�^Params.�����o���̗L���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j
                Integer l_intExperienceFlag10 = null;       
                if (l_request.changedMobileOfficeInfo.experienceFlag10 != null)     
                {       
                    l_intExperienceFlag10 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag10);        
                }       
                l_accountInfoMstParams.setExperienceFlag10(l_intExperienceFlag10);      
                        
                //   �@@�������}�X�^Params.�����o���i�P�j = �g�єԍ��E�Ζ�����.�����o���i�P�j 
                l_accountInfoMstParams.setExperienceDiv1(l_request.changedMobileOfficeInfo.experienceDiv1);     
                        
                //   �@@�������}�X�^Params.�����o���i�Q�j = �g�єԍ��E�Ζ�����.�����o���i�Q�j
                l_accountInfoMstParams.setExperienceDiv2(l_request.changedMobileOfficeInfo.experienceDiv2);     
                        
                //   �@@�������}�X�^Params.�����o���i�R�j = �g�єԍ��E�Ζ�����.�����o���i�R�j
                l_accountInfoMstParams.setExperienceDiv3(l_request.changedMobileOfficeInfo.experienceDiv3);     
                        
                //   �@@�������}�X�^Params.�����o���i�S�j = �g�єԍ��E�Ζ�����.�����o���i�S�j
                l_accountInfoMstParams.setExperienceDiv4(l_request.changedMobileOfficeInfo.experienceDiv4);     
                        
                //   �@@�������}�X�^Params.�����o���i�T�j = �g�єԍ��E�Ζ�����.�����o���i�T�j 
                l_accountInfoMstParams.setExperienceDiv5(l_request.changedMobileOfficeInfo.experienceDiv5);     
                        
                //   �@@�������}�X�^Params.�����o���i�U�j = �g�єԍ��E�Ζ�����.�����o���i�U�j
                l_accountInfoMstParams.setExperienceDiv6(l_request.changedMobileOfficeInfo.experienceDiv6);     
                        
                //   �@@�������}�X�^Params.�����o���i�V�j = �g�єԍ��E�Ζ�����.�����o���i�V�j 
                l_accountInfoMstParams.setExperienceDiv7(l_request.changedMobileOfficeInfo.experienceDiv7);     
                        
                //   �@@�������}�X�^Params.�����o���i�W�j = �g�єԍ��E�Ζ�����.�����o���i�W�j
                l_accountInfoMstParams.setExperienceDiv8(l_request.changedMobileOfficeInfo.experienceDiv8);     
                        
                //   �@@�������}�X�^Params.�����o���i�X�j = �g�єԍ��E�Ζ�����.�����o���i�X�j
                l_accountInfoMstParams.setExperienceDiv9(l_request.changedMobileOfficeInfo.experienceDiv9);     
                        
                //   �@@�������}�X�^Params.�����o���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���i�P�O�j 
                l_accountInfoMstParams.setExperienceDiv10(l_request.changedMobileOfficeInfo.experienceDiv10);       
                        
                //   �@@�������}�X�^Params.����̎�ށi�P�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�j
                Integer l_intInterest1 = null;      
                if (l_request.changedMobileOfficeInfo.interest1 != null)        
                {       
                    l_intInterest1 = new Integer(l_request.changedMobileOfficeInfo.interest1);      
                }       
                l_accountInfoMstParams.setInterestFlag1(l_intInterest1);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�Q�j = �g�єԍ��E�Ζ�����.����̎�ށi�Q�j
                Integer l_intInterest2 = null;      
                if (l_request.changedMobileOfficeInfo.interest2 != null)        
                {       
                    l_intInterest2 = new Integer(l_request.changedMobileOfficeInfo.interest2);      
                }       
                l_accountInfoMstParams.setInterestFlag2(l_intInterest2);        
                        
                        
                //   �@@�������}�X�^Params.����̎�ށi�R�j = �g�єԍ��E�Ζ�����.����̎�ށi�R�j
                Integer l_intInterest3 = null;      
                if (l_request.changedMobileOfficeInfo.interest3 != null)        
                {       
                    l_intInterest3 = new Integer(l_request.changedMobileOfficeInfo.interest3);      
                }       
                l_accountInfoMstParams.setInterestFlag3(l_intInterest3);        
                        
                        
                //   �@@�������}�X�^Params.����̎�ށi�S�j = �g�єԍ��E�Ζ�����.����̎�ށi�S�j
                Integer l_intInterest4 = null;      
                if (l_request.changedMobileOfficeInfo.interest4 != null)        
                {       
                    l_intInterest4 = new Integer(l_request.changedMobileOfficeInfo.interest4);      
                }       
                l_accountInfoMstParams.setInterestFlag4(l_intInterest4);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�T�j = �g�єԍ��E�Ζ�����.����̎�ށi�T�j
                Integer l_intInterest5 = null;      
                if (l_request.changedMobileOfficeInfo.interest5 != null)        
                {       
                    l_intInterest5 = new Integer(l_request.changedMobileOfficeInfo.interest5);      
                }       
                l_accountInfoMstParams.setInterestFlag5(l_intInterest5);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�U�j = �g�єԍ��E�Ζ�����.����̎�ށi�U�j 
                Integer l_intInterest6 = null;      
                if (l_request.changedMobileOfficeInfo.interest6 != null)        
                {       
                    l_intInterest6 = new Integer(l_request.changedMobileOfficeInfo.interest6);      
                }       
                l_accountInfoMstParams.setInterestFlag6(l_intInterest6);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�V�j = �g�єԍ��E�Ζ�����.����̎�ށi�V�j 
                Integer l_intInterest7 = null;      
                if (l_request.changedMobileOfficeInfo.interest7 != null)        
                {       
                    l_intInterest7 = new Integer(l_request.changedMobileOfficeInfo.interest7);      
                }       
                l_accountInfoMstParams.setInterestFlag7(l_intInterest7);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�W�j = �g�єԍ��E�Ζ�����.����̎�ށi�W�j
                Integer l_intInterest8 = null;      
                if (l_request.changedMobileOfficeInfo.interest8 != null)        
                {       
                    l_intInterest8 = new Integer(l_request.changedMobileOfficeInfo.interest8);      
                }       
                l_accountInfoMstParams.setInterestFlag8(l_intInterest8);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�X�j = �g�єԍ��E�Ζ�����.����̎�ށi�X�j 
                Integer l_intInterest9 = null;      
                if (l_request.changedMobileOfficeInfo.interest9 != null)        
                {       
                    l_intInterest9 = new Integer(l_request.changedMobileOfficeInfo.interest9);      
                }       
                l_accountInfoMstParams.setInterestFlag9(l_intInterest9);        
                        
                //   �@@�������}�X�^Params.����̎�ށi�P�O�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�O�j 
                Integer l_intInterest10 = null;     
                if (l_request.changedMobileOfficeInfo.interest10 != null)       
                {       
                    l_intInterest10 = new Integer(l_request.changedMobileOfficeInfo.interest10);        
                }       
                l_accountInfoMstParams.setInterestFlag10(l_intInterest10);      
                        
                //   �@@�������}�X�^Params.�����J�݂̓��@@ = �g�єԍ��E�Ζ�����.�����J�݂̓��@@ 
                l_accountInfoMstParams.setAppliMotivatDiv(l_request.changedMobileOfficeInfo.appliMotivatDiv);       
                        
                //   �@@�������}�X�^Params.�����J�݂̓��@@�̏ڍ� = �g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� 
                l_accountInfoMstParams.setAppliMotivatDivDetail(l_request.changedMobileOfficeInfo.appliMotivatDetail);      
                        
                //   �@@�������}�X�^Params.���ݗ��p���Ă���،���� = �g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� 
                l_accountInfoMstParams.setUseInstitutionDiv(l_request.changedMobileOfficeInfo.useInstitutionDiv);       
                        
                //   �@@�������}�X�^Params.�C���^�[�l�b�g����敪 = �g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 
                l_accountInfoMstParams.setInternetTradeDiv(l_request.changedMobileOfficeInfo.internetTradeDiv);     
                        
                //   �@@�������}�X�^Params.�Љ�x�X = �g�єԍ��E�Ζ�����.�Љ�x�X 
                l_accountInfoMstParams.setIntroduceBranchCode(l_request.changedMobileOfficeInfo.introduceBranch);       
                
                try
                {
                
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_accountInfoMstParams);
                }
                catch (DataFindException l_ex)
                {
                
                    log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                
                    log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                
                    log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }  
            }
            //1.16)(*5) �������}�X�^��񂪂Ȃ��ꍇ�iget�������}�X�^() == null�j�A�������}�X�^����V�K�쐬����B
            else
            {
                //1.16.1)�������}�X�^�I�u�W�F�N�g��V�K�쐬����B
                l_accInfoMaster = WEB3AccInfoMaster.createNewAccInfoMaster(
                    l_mainAccount, 
                    l_request.changedMobileOfficeInfo, 
                    l_strAdministratorCode);

                //1.16.2)�������}�X�^�s�I�u�W�F�N�g���擾����B
                l_accountInfoMstParams = (AccountInfoMstParams)l_accInfoMaster.getDataSourceObject();
                try
                {
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    //1.16)�������}�X�^�e�[�u���ɐV�K�s��}������B
                    l_processor.doInsertQuery(l_accountInfoMstParams);
                }
                catch (DataFindException l_ex)
                {
                
                    log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                
                    log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                
                    log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }                
            }

            boolean l_blnVoucherCreated = 
                WEB3AccInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(l_mobileOfficeInfoRegistParamsTemp, l_accInfoMasterTemp);

            // is�`�[�쐬()��true�̏ꍇ
            if (l_blnVoucherCreated)
            {
                //get���X�v���t�@@�����X�i�j�̖߂�lindex[0]�̒l = "1" �̏ꍇ�A�ȉ��̏��������s
                if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                {
                    //�E�ƕύX�\���`�[�쐬( )
                    WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
                        new WEB3AccInfoOccupationChangeRegistVoucherCreated();

                    // create�E�ƕύX�`�[(long, String)
                    // �E�ƕύX�ɔ�����c�E�d�q��t�E��������`�[�iGI844�j���쐬����B
                    // [create�E�ƕύX�`�[�i�j�Ɏw�肷�����]
                    // �ύX�\��ID �F �g�єԍ��E�Ζ�����ύX�\��params.�g�єԍ��E�Ζ�����ύX�\��ID
                    // �E�ƃR�[�h �F ���N�G�X�g�f�[�^.�g�єԍ��E�Ζ�����.�E��
                    //�ڋq�I�u�W�F�N�g �F get�ڋq�i�j�̖߂�l
                    long l_lngChangeRegistID = l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId();
                    String l_strOccupationCode = l_request.changedMobileOfficeInfo.occupationDiv;
                    l_accInfoOccupationChangeRegistVoucherCreated.createOccupationChangeVoucher(
                        l_lngChangeRegistID, l_strOccupationCode, l_mainAccount);
                    
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();
                        //�����`�[�쐬�̏ꍇ�A�g�єԍ�.�Ζ�����ύX�\���e�[�u���̎�t���ʋ敪�� 
                        //"0�F��t����"�ɍX�V����B 
                        Map l_updateMap = new HashMap(); 
                        l_updateMap.put("accept_status", WEB3AccountInfoAcceptStatusDef.NOT_ACCEPT);
                        l_processor.doUpdateQuery(
                            new MobileOfficeInfoRegistPK(l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId()), 
                            l_updateMap);

                        //�������}�X�^�̐E�ƍX�V�������X�V����B
                        Map l_updateMap1 = new HashMap();
                        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                        l_updateMap1.put("occupation_updated_timestamp", l_tradingSys.getSystemTimestamp());
                        l_processor.doUpdateQuery(
                            new AccountInfoMstPK(l_mobileOfficeInfoRegistParams.getAccountId()),
                            l_updateMap1);
                    }
                    catch (DataFindException l_ex)
                    {
                    
                        log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                    
                        log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataQueryException l_ex)
                    {
                    
                        log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                }
            }
        }
        
        //1.17) createResponse( )
        WEB3AdminAccInfoMobileOfficeRegistCompleteResponse l_response = 
            (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���X�v���t�@@�����X )<BR>  
     * �ڋq�I�u�W�F�N�g���A���X�p�v���t�@@�����X�e�[�u������<BR>
     * �ڋq�������̍X�V�A�E�ƃR�[�h�X�V�`�F�b�N���擾����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̗v�f�̔z��𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�|�P�j�@@DB���� �i�E�ƃR�[�h�X�V�j<BR>
     * �@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �⏕����.getBranch().getBranchId() And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.���q�l���E�ƃR�[�h�X�V And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B<BR>
     * <BR>
     * �P�|�Q�j�@@DB���� �i�ڋq�������̍X�V�j<BR>
     * �@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �⏕����.getBranch().getBranchId() And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.���q�l���ڋq�������̍X�V And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return l_strBranchPerferences
     * 
     */
    private String[] getBranchPreferences(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        String[] l_strBranchPerferences = new String[2];
        try
        {
            // �P�|�P�jDB���� �i�E�ƃR�[�h�X�V�j
            // ���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����B
            // ���XID = �⏕����.getBranch().getBranchId() And
            // �v���t�@@�����X�� = �v���t�@@�����X��.���q�l���E�ƃR�[�h�X�V And
            // �v���t�@@�����X���̘A�� = 1
            BranchPreferencesRow l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.OCCUPATIONCODE_UPDATE,
                    1);

            //�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B
            if (l_branchReferencesRow == null)
            {
                l_strBranchPerferences[0] = "";
            }
            else
            {
                l_strBranchPerferences[0] = l_branchReferencesRow.getValue();
            }

            // �P�|�Q�j�@@DB���� �i�ڋq�������̍X�V�j
            // ���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō������v���t�@@�����X�̒l���擾����
            // ���XID = �⏕����.getBranch().getBranchId() And
            // �v���t�@@�����X�� = �v���t�@@�����X��.���q�l���ڋq�������̍X�V And
            // �v���t�@@�����X���̘A�� = 1
            BranchPreferencesRow l_branchReferenceRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.REALNAME_UPDATE,
                    1);

            //�������ʂ��擾�ł��Ȃ������ꍇ�A""(�󕶎�)���Z�b�g����B
            if (l_branchReferenceRow == null)
            {
                l_strBranchPerferences[1] = "";
            }
            else
            {
                l_strBranchPerferences[1] = l_branchReferenceRow.getValue();
            }
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

        log.exiting(STR_METHOD_NAME);
        return l_strBranchPerferences;
    }
}
@
