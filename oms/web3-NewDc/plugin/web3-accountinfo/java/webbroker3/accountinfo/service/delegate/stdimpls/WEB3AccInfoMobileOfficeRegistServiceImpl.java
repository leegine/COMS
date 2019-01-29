head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�XImpl(WEB3AccInfoMobileOfficeRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/02/23 ������ (���u) ���f��No.086
                   2006/03/20 ������ (���u) ���f��No.098    
                   2006/10/9  ꎉ�   (���u) ���f��No.124      
                   2006/10/30 ����� (���u) �d�l�ύX���f��No.139
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMobileOfficeRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�XImpl)<BR>
 * ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X�����N���X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AccInfoMobileOfficeRegistServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoMobileOfficeRegistService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistServiceImpl.class);
    
    /**
     * @@roseuid 418F3A070128
     */
    public WEB3AccInfoMobileOfficeRegistServiceImpl() 
    {
     
    }
    
    /**
     * �g�єԍ��E�Ζ�����ύX�\�����������{����B<BR>
     * <BR>
     * �����ϑ��萔���R�[�X�ύX�\�����������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�\�����̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A���q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�\���m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�|validate�ύX�\��()���R�[������B<BR> 
     * �� �����̃��N�G�X�g�f�[�^���A���q�l���g�єԍ��E�Ζ�����<BR>
     * �ύX�\���������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX�\��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413FEDFC0070
     */ 
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //�����̃��N�G�X�g�f�[�^���A���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g�̏ꍇ
        if(l_request instanceof WEB3AccInfoMobileOfficeRegistInputRequest)
        {  
            
            l_response = this.getInputScreen((WEB3AccInfoMobileOfficeRegistInputRequest)l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3AccInfoMobileOfficeRegistConfirmRequest)
        {
            
            l_response = this.validateRegist((WEB3AccInfoMobileOfficeRegistConfirmRequest)l_request);
        }
        
        //�����̃��N�G�X�g�f�[�^���A���q�l���g�єԍ��E�Ζ�����ύX�\���������N�G�X�g�̏ꍇ
        else if(l_request instanceof WEB3AccInfoMobileOfficeRegistCompleteRequest)
        {
            
            l_response = this.submitRegist((WEB3AccInfoMobileOfficeRegistCompleteRequest)l_request);
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
     * �g�єԍ��E�Ζ�����ύX�\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���jget���͉�ʁv�Q�ƁB <BR>
     *  <BR>
     *  ===============================================     <BR>
     *          �V�[�P���X�} :  ���q�l���i�g�єԍ��E�Ζ�����ύX�\���jget���͉��             <BR>
     *          ��̈ʒu     :  1.4  ����m�F���̏ꍇ�iis����m�F��() == true�j�A <BR>
     *                                     ��O���X���[����B                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01167                    <BR>
     *  ===============================================     <BR> 
     *  <BR>
     * @@param l_request - ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse
     * @@roseuid 413FEECA0274
     */
    protected WEB3AccInfoMobileOfficeRegistInputResponse getInputScreen(WEB3AccInfoMobileOfficeRegistInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate������t�\ ()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2) get�ڋq ()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //1.3) get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.7) ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��X�|���X(WEB3GenRequest)
        WEB3AccInfoMobileOfficeRegistInputResponse l_response = 
            (WEB3AccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            
        //1.6) �ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A�ύX����𐶐�����
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.4) is����m�F��( )
            boolean l_blnIsDeciding = l_accInfoMobileOfficeInfoRegist.isDeciding();
        
            //1.5)  ����m�F���̏ꍇ�iis����m�F��() == true�j�A��O���X���[����
            if(l_blnIsDeciding)
            {
            
                log.error(" ����m�F���ł�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01167,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ����m�F���ł�");
            }
            
            //1.6.1) �g�єԍ��E�Ζ�����( )
            WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();
            
            //1.6.2) �v���p�e�B�Z�b�g
            MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow = 
                (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();
                
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
            
            //�g�єԍ��E�Ζ�����.�ڋq��������1 = �ύX�\�����.��������1
            l_accInfoMobileOfficeInfo.accountRealName1 = l_mobileOfficeInfoRegistRow.getRealName1();
            
            //�g�єԍ��E�Ζ�����.�ڋq��������2 = �ύX�\�����.��������2
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
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ��1 = �ύX�\�����.�����ӔC�ҏZ��1
            l_accInfoMobileOfficeInfo.directorAddress1 = l_mobileOfficeInfoRegistRow.getDirectorAddress1();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ��2 = �ύX�\�����.�����ӔC�ҏZ��2
            l_accInfoMobileOfficeInfo.directorAddress2 = l_mobileOfficeInfoRegistRow.getDirectorAddress2();
            
            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ��3 = �ύX�\�����.�����ӔC�ҏZ��3
            l_accInfoMobileOfficeInfo.directorAddress3 = l_mobileOfficeInfoRegistRow.getDirectorAddress3();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = �ύX�\�����.�����ӔC�Ґ��N�����N��
            l_accInfoMobileOfficeInfo.directorEraBorn = l_mobileOfficeInfoRegistRow.getDirectorEraBorn();
            
            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = �ύX�\�����.�����ӔC�Ґ��N����
            l_accInfoMobileOfficeInfo.directorBornDate = l_mobileOfficeInfoRegistRow.getDirectorBornDate();
            
            //�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = �ύX�\�����.�����ӔC�҉�В��ʔԍ�
            l_accInfoMobileOfficeInfo.directorCorpDirect = l_mobileOfficeInfoRegistRow.getDirectorCorpTelephone();
            
            //�g�єԍ��E�Ζ�����.���̑��A����(�g�сA���) = �ύX�\�����.���̑��A����(�g�сA���)
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
            	l_accInfoMobileOfficeInfo.experienceFlag1 = l_mobileOfficeInfoRegistRow.getExperienceFlag1() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = �ύX�\�����.�����o���̗L���i�Q�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag2IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag2 = l_mobileOfficeInfoRegistRow.getExperienceFlag2() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = �ύX�\�����.�����o���̗L���i�R�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag3IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag3 = l_mobileOfficeInfoRegistRow.getExperienceFlag3() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = �ύX�\�����.�����o���̗L���i�S�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag4IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag4 = l_mobileOfficeInfoRegistRow.getExperienceFlag4() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = �ύX�\�����.�����o���̗L���i�T�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag5IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag5 = l_mobileOfficeInfoRegistRow.getExperienceFlag5() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = �ύX�\�����.�����o���̗L���i�U�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag6IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag6 = l_mobileOfficeInfoRegistRow.getExperienceFlag6() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = �ύX�\�����.�����o���̗L���i�V�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag7IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag7 = l_mobileOfficeInfoRegistRow.getExperienceFlag7() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = �ύX�\�����.�����o���̗L���i�W�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag8IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag8 = l_mobileOfficeInfoRegistRow.getExperienceFlag8() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = �ύX�\�����.�����o���̗L���i�X�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag9IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag9 = l_mobileOfficeInfoRegistRow.getExperienceFlag9() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = �ύX�\�����.�����o���̗L���i�P�O�j
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag10IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag10 = l_mobileOfficeInfoRegistRow.getExperienceFlag10() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����o���i�P�j = �ύX�\�����.�����o���i�P�j
            l_accInfoMobileOfficeInfo.experienceDiv1 = l_mobileOfficeInfoRegistRow.getExperienceDiv1();
            
            //�g�єԍ��E�Ζ�����.�����o���i�Q�j = �ύX�\�����.�����o���i�Q�j
            l_accInfoMobileOfficeInfo.experienceDiv2 = l_mobileOfficeInfoRegistRow.getExperienceDiv2();
            
            //�g�єԍ��E�Ζ�����.�����o���i�R�j = �ύX�\�����.�����o���i�R�j
            l_accInfoMobileOfficeInfo.experienceDiv3 = l_mobileOfficeInfoRegistRow.getExperienceDiv3();
            
            //�g�єԍ��E�Ζ�����.�����o���i�S�j = �ύX�\�����.�����o���i�S�j
            l_accInfoMobileOfficeInfo.experienceDiv4 = l_mobileOfficeInfoRegistRow.getExperienceDiv4();
            
            //�g�єԍ��E�Ζ�����.�����o���i�T�j = �ύX�\�����.�����o���i�T�j
            l_accInfoMobileOfficeInfo.experienceDiv5 = l_mobileOfficeInfoRegistRow.getExperienceDiv5();
            
            //�g�єԍ��E�Ζ�����.�����o���i�U�j = �ύX�\�����.�����o���i�U�j
            l_accInfoMobileOfficeInfo.experienceDiv6 = l_mobileOfficeInfoRegistRow.getExperienceDiv6();
            
            //�g�єԍ��E�Ζ�����.�����o���i�V�j = �ύX�\�����.�����o���i�V�j
            l_accInfoMobileOfficeInfo.experienceDiv7 = l_mobileOfficeInfoRegistRow.getExperienceDiv7();
            
            //�g�єԍ��E�Ζ�����.�����o���i�W�j = �ύX�\�����.�����o���i�W�j
            l_accInfoMobileOfficeInfo.experienceDiv8 = l_mobileOfficeInfoRegistRow.getExperienceDiv8();
            
            //�g�єԍ��E�Ζ�����.�����o���i�X�j = �ύX�\�����.�����o���i�X�j
            l_accInfoMobileOfficeInfo.experienceDiv9 = l_mobileOfficeInfoRegistRow.getExperienceDiv9();
            
            //�g�єԍ��E�Ζ�����.�����o���i�P�O�j = �ύX�\�����.�����o���i�P�O�j
            l_accInfoMobileOfficeInfo.experienceDiv10 = l_mobileOfficeInfoRegistRow.getExperienceDiv10();
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�P�j = �ύX�\�����.����̎�ށi�P�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.interest1 = l_mobileOfficeInfoRegistRow.getInterestFlag1() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = �ύX�\�����.����̎�ށi�Q�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.interest2 = l_mobileOfficeInfoRegistRow.getInterestFlag2() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�R�j = �ύX�\�����.����̎�ށi�R�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.interest3 = l_mobileOfficeInfoRegistRow.getInterestFlag3() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�S�j = �ύX�\�����.����̎�ށi�S�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.interest4 = l_mobileOfficeInfoRegistRow.getInterestFlag4() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�T�j = �ύX�\�����.����̎�ށi�T�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.interest5 = l_mobileOfficeInfoRegistRow.getInterestFlag5() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�U�j = �ύX�\�����.����̎�ށi�U�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.interest6 = l_mobileOfficeInfoRegistRow.getInterestFlag6() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�V�j = �ύX�\�����.����̎�ށi�V�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.interest7 = l_mobileOfficeInfoRegistRow.getInterestFlag7() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�W�j = �ύX�\�����.����̎�ށi�W�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.interest8 = l_mobileOfficeInfoRegistRow.getInterestFlag8() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�X�j = �ύX�\�����.����̎�ށi�X�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.interest9 = l_mobileOfficeInfoRegistRow.getInterestFlag9() + "";
            }
            
            //�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = �ύX�\�����.����̎�ށi�P�O�j
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.interest10 = l_mobileOfficeInfoRegistRow.getInterestFlag10() + "";
            }
            
            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = �ύX�\�����.�����J�݂̓��@@
            l_accInfoMobileOfficeInfo.appliMotivatDiv = l_mobileOfficeInfoRegistRow.getAppliMotivatDiv();
            
            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = �ύX�\�����.�����J�݂̓��@@�̏ڍ�
            l_accInfoMobileOfficeInfo.appliMotivatDetail = l_mobileOfficeInfoRegistRow.getAppliMotivatDivDetail();
            
            //�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = �ύX�\�����.���ݗ��p���Ă���،����
            l_accInfoMobileOfficeInfo.useInstitutionDiv = l_mobileOfficeInfoRegistRow.getUseInstitutionDiv();
            
            //�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = �ύX�\�����.�C���^�[�l�b�g����敪
            l_accInfoMobileOfficeInfo.internetTradeDiv = l_mobileOfficeInfoRegistRow.getInternetTradeDiv();
            
            //�g�єԍ��E�Ζ�����.�Љ�x�X = �ύX�\�����.�Љ�x�X����敪
            l_accInfoMobileOfficeInfo.introduceBranch = l_mobileOfficeInfoRegistRow.getIntroduceBranchCode();
            
            //1.8) �v���p�e�B�Z�b�g
            //�@@���X�|���X�f�[�^.�ύX����
            //�|�ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A���������ύX����i�F�g�єԍ��E�Ζ�����j�B        
            l_response.changedMobileOfficeInfo = l_accInfoMobileOfficeInfo;            
        }
                    
        //�|�ύX�\����񂪂Ȃ��ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() == null�j�Anull�B
        else
        {
            
            l_response.changedMobileOfficeInfo = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX�\��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���jvalidate�ύX�\���v�Q�ƁB <BR>
     * ===============================================     <BR>
     *          �V�[�P���X�} : ���q�l���i�g�єԍ��E�Ζ�����ύX�\���jvalidate�ύX�\��<BR>
     *          ��̈ʒu�@@�@@ : 1.2 �ύX���ږ��̏ꍇ�iis�ύX���ځi�j==FALSE�j�A��O���X���[����B<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag             : BUSINESS_ERROR_02688<BR>  
     * ===============================================<BR>
     * <BR>
     * ===============================================     <BR>
     *          �V�[�P���X�} : ���q�l���i�g�єԍ��E�Ζ�����ύX�\���jvalidate�ύX�\��             <BR>
     *          ��̈ʒu     :  1.9  ����m�F���̏ꍇ�iis����m�F��() == true�j�A <BR>
     *                                     ��O���X���[����B                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01167                    <BR>  
     * ===============================================     <BR>
     *  <BR>
     * @@param l_request - ���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413FEECA0293
     */
    protected WEB3AccInfoMobileOfficeRegistConfirmResponse validateRegist(WEB3AccInfoMobileOfficeRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateRegist(WEB3AccinfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)is�ύX����(�g�єԍ��E�Ζ�����, �g�єԍ��E�Ζ�����)
        //�ύX�O���ƕύX����̔�r���s���A
        //�ύX���ڂ����݂��邩�𔻒肷��B
        //[is�ύX����()�Ɏw�肷�����]
        //�ύX�O���F���N�G�X�g�f�[�^.�ύX�O���
        //�ύX����F���N�G�X�g�f�[�^.�ύX����
        boolean l_blnIsChangedItem = 
            WEB3AccInfoMobileOfficeInfoRegist.isChangedItem(
                l_request.preMobileOfficeInfo, 
                l_request.changedMobileOfficeInfo);

        //1.2)�ύX���ږ��̏ꍇ�iis�ύX���ځi�j==FALSE�j�A��O���X���[����B
        //����t���[  
        //�ύX���ږ��̏ꍇ�iis�ύX���ځi�j==FALSE�j�A  
        //�u�ύX���ږ����G���[�v��O���X���[����B 
        if (!l_blnIsChangedItem)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ڂ��ύX����Ă��Ȃ��ł��B");
        }
        
        //1.3) validate( )
        l_request.validate();
               
        //1.4) validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5) get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //1.6) is�A����D�揇��
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.7) get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
         
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.8) is����m�F��( )
            boolean l_blnIsDeciding = l_accInfoMobileOfficeInfoRegist.isDeciding();
        
            //1.9) ����m�F���̏ꍇ�iis����m�F��() == true�j�A��O���X���[����
            if(l_blnIsDeciding)
            {
            
                log.error(" ����m�F���ł�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01167,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ����m�F���ł�");            
            }
        }
        
        //1.10) createResponse( )
        WEB3AccInfoMobileOfficeRegistConfirmResponse l_response = 
            (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX�\��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\�������������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�g�єԍ��E�Ζ�����ύX�\���jsubmit�ύX�\���v�Q�ƁB <BR>
     *  ===============================================     <BR>
     *          �V�[�P���X�} : ���q�l���i�g�єԍ��E�Ζ�����ύX�\���jsubmit�ύX�\��             <BR>
     *          ��̈ʒu     :  1.7  ����m�F���̏ꍇ�iis����m�F��() == true�j�A <BR>
     *                                     ��O���X���[����B                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01167                    <BR>
     * ===============================================     <BR> 
     * <BR>
     * @@param l_request - ���q�l���g�єԍ��E�Ζ�����ύX�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413FEECA02A3
     */
    protected WEB3AccInfoMobileOfficeRegistCompleteResponse submitRegist(WEB3AccInfoMobileOfficeRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " submitRegist(WEB3AccinfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3) get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //1.4) is�A����D�揇��
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.5) getCommonOrderValidator( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.6)validate����p�X���[�h()
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
                getTrader(),
                getSubAccount(), 
                l_request.password
                );
        if(l_validationResult.getProcessingResult().isFailedResult())
        {
            
            log.debug(" �`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " �`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
        }
        
        //1.7) get�g�єԍ��E�Ζ�����ύX�\��(�ڋq)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.10) �ύX�\����񂪂���ꍇ�iget�g�єԍ��E�Ζ�����ύX�\��() != null�j�A�����\�����𖳌��ɂ���B
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.8) is����m�F��( )
            boolean l_blnIsDeciding = l_accInfoMobileOfficeInfoRegist.isDeciding();
        
            //1.9) ����m�F���̏ꍇ�iis����m�F��() == true�j�A��O���X���[����B
            if(l_blnIsDeciding)
            {
            
                log.error(" ����m�F���ł�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01167,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ����m�F���ł�");
            }
            
            //1.10.1) getDataSourceObject( )
            MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());
            
            //1.10.2) doUpdateQuery(PrimaryKey, Map)
            l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.TRUE);
            l_mobileOfficeInfoRegistParams.setLastUpdater(l_mainAccount.getAccountCode());
            l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();              
                l_queryProcessor.doUpdateQuery(l_mobileOfficeInfoRegistParams);             
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
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
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
        }
        
        //1.11) createNew�g�єԍ��E�Ζ�����ύX�\��(�ڋq, �g�єԍ��E�Ζ�����, String)
        WEB3AccInfoMobileOfficeInfoRegist l_createNewMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.createNewMobileOfficeInfoRegist(
                l_mainAccount,
                l_request.changedMobileOfficeInfo,
                l_mainAccount.getAccountCode());
      
        //1.12) getDataSourceObject( )
        MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow = 
            (MobileOfficeInfoRegistRow)l_createNewMobileOfficeInfoRegist.getDataSourceObject();
            
        //1.13) doInsertQuery(Row)
        try
        {
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_mobileOfficeInfoRegistRow);
        }
        catch (DataFindException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        
        //1.14) createResponse( )
        WEB3AccInfoMobileOfficeRegistCompleteResponse l_response = 
            (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
