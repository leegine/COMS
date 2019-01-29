head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInfoCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ��쐬�T�[�r�XImpl(WEB3AccOpenInfoCreatedServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 �s�p (���u) �V�K�쐬
                 : 2006/06/08 ����(���u) �d�l�ύX�E���f��048�A050
                 : 2006/07/13 ����(���u) �d�l�ύX�E���f��072�A075�A078 �c�a�X�V�d�l007            
                 : 2006/08/14 �����F(���u) �d�l�ύX�E���f��087       
Revesion History : 2009/08/12 �����F(���u) �c�a���C�A�E�g053�A056
Revesion History : 2009/08/13 ���g (���u) �d�l�ύX ���f�� No.172
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenInvalidValues;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.WEB3AccOpenMailAddressDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenTelNumberDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.data.AccOpenInvalidValuesParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.define.WEB3AccOpenApplyInfoItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.message.WEB3AccOpenAgencyInfo;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenForeignSaveInfo;
import webbroker3.accountopen.message.WEB3AccOpenGpInfo;
import webbroker3.accountopen.message.WEB3AccOpenInsiderInfo;
import webbroker3.accountopen.message.WEB3AccOpenInterestDealingInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvestmentExperienceInfo;
import webbroker3.accountopen.message.WEB3AccOpenListedFeqInfo;
import webbroker3.accountopen.message.WEB3AccOpenOfficeInfo;
import webbroker3.accountopen.message.WEB3AccOpenPostalTransferInfo;
import webbroker3.accountopen.message.WEB3AccOpenTransferBankInfo;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExistenceDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

/**
 * (�����J�ݏ��쐬�T�[�r�XImpl)<BR>
 * �����J�ݏ��쐬�T�[�r�X�����N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AccOpenInfoCreatedServiceImpl implements WEB3AccOpenInfoCreatedService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenInfoCreatedServiceImpl.class);

    /**
     * @@roseuid 41B45E7203C8
     */
    public WEB3AccOpenInfoCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (to�����J�ݐ\�����)<BR>
     * �����J�݌����q�I�u�W�F�N�g���A�����J�ݐ\�����𐶐�����B<BR>
     * <BR>
     * �P�j�@@�I�u�W�F�N�g����<BR>
     * �@@�����J�ݐ\�����I�u�W�F�N�g�C�����o�����I�u�W�F�N�g�C<BR>
     * �����̂��������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �@@�����J�݌����q.�����J�݌����q�s�I�u�W�F�N�g���A��������<BR>
     * ���b�Z�[�W�I�u�W�F�N�g�̑Ή����鍀�ڂɒl���Z�b�g���ԋp����B<BR>
     * �@@�A���A�Ïؔԍ��i�����p�X���[�h�j�͕ҏW���Ȃ��B<BR>
     * <BR>
     * �@@�� BooleanEnum�^�̍��ڂ́A�Ή�����boolean�l�ɕϊ����邱�ƁB<BR>
     * �@@�� �i�����J�݌����q�s.�U�֋敪 == ��s�U���j�̏ꍇ�̂݁A<BR>
     * �U�����s���I�u�W�F�N�g�𐶐����v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�ȊO�̏ꍇ�A�����J�ݐ\�����.�U�����s����null���Z�b�g����B<BR>
     * �@@�� �i�����J�݌����q�s.�U�֋敪 == �X���U�ցj�̏ꍇ�̂݁A<BR>
     * �X�֐U�֏��I�u�W�F�N�g�𐶐����v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�ȊO�̏ꍇ�A�����J�ݐ\�����.�X�֐U�֏���null���Z�b�g����B<BR>
     *  �� �Ïؔԍ��i�����p�X���[�h�j�́A�������iWEB3Crypt.decrypt()�j���ăZ�b�g����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInfo
     * @@roseuid 4191A5FE01AA
     */
    public WEB3AccOpenApplyInfo toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) 
    {
        final String STR_METHOD_NAME = " toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenExpAccountOpen == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@�I�u�W�F�N�g����
        WEB3AccOpenApplyInfo l_applyInfo = new WEB3AccOpenApplyInfo();
        WEB3AccOpenInvestmentExperienceInfo l_investmentExperienceInfo = 
            new WEB3AccOpenInvestmentExperienceInfo();
        WEB3AccOpenInterestDealingInfo l_interestDealingInfo = 
            new WEB3AccOpenInterestDealingInfo();
        WEB3AccOpenInsiderInfo l_insiderInfo = new WEB3AccOpenInsiderInfo();
        WEB3AccOpenGpInfo l_gpInfo = new WEB3AccOpenGpInfo();
        WEB3AccOpenListedFeqInfo l_listedFeqInfo = new WEB3AccOpenListedFeqInfo();
        WEB3AccOpenForeignSaveInfo l_foreignSaveInfo = new WEB3AccOpenForeignSaveInfo();
        
        //�Q�j�@@�v���p�e�B�Z�b�g
        ExpAccountOpenRow l_row = (ExpAccountOpenRow)
            l_accOpenExpAccountOpen.getDataSourceObject();
        //����������
        l_applyInfo.infoClaimDate = l_row.getInfomationClaimDatetime();
    
        //�X�V��
        l_applyInfo.updateDate = l_row.getLastUpdatedTimestamp();
    
        //�X�V�҃R�[�h
        l_applyInfo.updaterCode = l_row.getLastUpdater();
    
        //���ʃR�[�h
        l_applyInfo.requestNumber = l_row.getAccOpenRequestNumber();
        
        //���͋敪
        l_applyInfo.inputDiv = l_row.getOrderDiv();
    
        //�쐬�҃R�[�h
        l_applyInfo.creatorCode = l_row.getCreator();
    
        //���������t���O
        if (BooleanEnum.TRUE.equals(l_row.getExAccountFlag()))
        {
            l_applyInfo.exAccountFlag = true;
        }
        else
        {
            l_applyInfo.exAccountFlag = false;
        }
    
        //���������R�[�h
        l_applyInfo.exAccountCode = l_row.getExAccountCode();
    
        //�����������X��
        l_applyInfo.exAccountBranchName = l_row.getExBranchName();
    
        //�،���ЃR�[�h
        l_applyInfo.institutionCode = l_row.getInstitutionCode();
    
        //���X�R�[�h
        l_applyInfo.branchCode = l_row.getBranchCode();
    
        //�����敪
        l_applyInfo.accountType = l_row.getAccountDiv();
    
        //�ڋq�R�[�h
        l_applyInfo.accountCode = l_row.getAccountCode();
    
        //���҃R�[�h�iSONAR�j
        l_applyInfo.traderCode = l_row.getSonarTraderCode();
    
        //���[���A�h���X
        l_applyInfo.mailAddress = l_row.getEmailAddress();
    
        //���[���A�h���X�i�g�сj
        l_applyInfo.mobileMailAddress = l_row.getEmailAddressAlt1();
    
        //�ڋq���i�����j
        l_applyInfo.accountFamilyName = l_row.getFamilyName();
    
        //�ڋq���i�����j
        l_applyInfo.accountName = l_row.getGivenName();
    
        //�ڋq���i�J�i�j
        l_applyInfo.accountFamilyNameKana = l_row.getFamilyNameAlt1();
    
        //�ڋq���i�J�i�j
        l_applyInfo.accountNameKana = l_row.getGivenNameAlt1();
    
        //���N�����N��
        l_applyInfo.eraBorn = l_row.getEraBorn();
    
        //���N����
        l_applyInfo.bornDate = l_row.getBornDate();
    
        //����
        l_applyInfo.sex = l_row.getSex();
        
        //�Ïؔԍ�
        WEB3Crypt l_crypt = new WEB3Crypt();
        l_applyInfo.password = l_crypt.decrypt(l_row.getInitialPassword());
    
        //�X�֔ԍ�
        l_applyInfo.zipCode = l_row.getZipCode();
    
        //�Z���P�i�J�i�j
        l_applyInfo.addressKana1 = l_row.getAddressLine1Kana();
    
        //�Z���Q�i�J�i�j
        l_applyInfo.addressKana2 = l_row.getAddressLine2Kana();
    
        //�Z���R�i�J�i�j
        l_applyInfo.addressKana3 = l_row.getAddressLine3Kana();
    
        //�Z���P�i�����j
        l_applyInfo.address1 = l_row.getAddressLine1();
    
        //�Z���Q�i�����j
        l_applyInfo.address2 = l_row.getAddressLine2();
    
        //�Z���R�i�����j
        l_applyInfo.address3 = l_row.getAddressLine3();
    
        //�d�b�ԍ�
        l_applyInfo.telephone = l_row.getTelephone();
    
        //�g�єԍ�
        l_applyInfo.mobileTelephone = l_row.getMobile();
    
        //�e�`�w�ԍ�
        l_applyInfo.faxTelephone = l_row.getFax();
    
        //�Ζ�����
        WEB3AccOpenOfficeInfo l_officeInfo = new WEB3AccOpenOfficeInfo();
        
        //�E�Ƌ敪
        l_officeInfo.occupationDiv = l_row.getOccupationDiv();
    
        //�Ζ��於��
        l_officeInfo.officeName = l_row.getOffice();
    
        //�Ζ���X�֔ԍ�<BR>
        l_officeInfo.officeZipCode = l_row.getOfficeZipCode();
    
        //�Ζ���Z��
        l_officeInfo.officeAdress = l_row.getOfficeAddress();
    
        //�Ζ���d�b�ԍ�
        l_officeInfo.officeTelephone = l_row.getOfficeTelephone();
    
        //�Ζ���e�`�w�ԍ�
        l_officeInfo.officeFaxTelephone = l_row.getOfficeFax();
    
        //��������
        l_officeInfo.department = l_row.getDepartment();
    
        //��E��
        l_officeInfo.position = l_row.getPost();
        
        l_applyInfo.officeInfo = l_officeInfo;
    
        //�A����Z��
        l_applyInfo.contactAddress = l_row.getContactAddress();
    
        //�A����d�b�ԍ�
        l_applyInfo.contactTelephone = l_row.getContactTelephone();
    
        //�����敪
        l_applyInfo.familyRelationDiv = l_row.getFamilyRelationship();
    
        //�����i���̑��j
        l_applyInfo.familyRelationEtc = l_row.getFamilyRelationshipEtc();
    
        //���ю喼�i�����j
        l_applyInfo.houseHolderName = l_row.getHouseholder();
    
        //���ю喼�i�J�i�j
        l_applyInfo.houseHolderNameKana = l_row.getHouseholderKana();
    
        //���ю�Ζ�����
        WEB3AccOpenOfficeInfo l_officeInfo2 = new WEB3AccOpenOfficeInfo();
        
        //�E�Ƌ敪
        l_officeInfo2.occupationDiv = l_row.getHouseholderOccupationDiv();
            
        //�Ζ��於��
        l_officeInfo2.officeName = l_row.getHouseholderOffice();
            
        //�Ζ���Z��
        l_officeInfo2.officeAdress = l_row.getHouseholderOfficeAddress();
            
        //�Ζ���d�b�ԍ�
        l_officeInfo2.officeTelephone = l_row.getHouseholderOfficeTel();
            
        //�Ζ���e�`�w�ԍ�
        l_officeInfo2.officeFaxTelephone = l_row.getHouseholderOfficeFax();
            
        //��������
        l_officeInfo2.department = l_row.getHouseholderDepartment();
            
        //��E��
        l_officeInfo2.position = l_row.getHouseholderPost(); 
        
        l_applyInfo.houseHolderOfficeInfo = l_officeInfo2;
    
        //���Z�i���{�j�^�񋏏Z�i���{�ȊO�j�敪
        l_applyInfo.residentDiv = l_row.getResident();
    
        //�U�֋敪
        l_applyInfo.transferDiv = l_row.getTransferDiv();
    
        //�������`�l
        l_applyInfo.financialAccountName = l_row.getFinAccountName();
    
        //�����ړI�敪
        l_applyInfo.investPurposeDiv = l_row.getInvestPurposeDiv();
    
        //������@@�敪
        l_applyInfo.appliMotivatDiv = l_row.getAppliMotivatDiv();
    
        //�N���敪
        l_applyInfo.annualIncomeDiv = l_row.getAnnualIncomeDiv();
    
        //���Z���Y�敪
        l_applyInfo.assetValueDiv = l_row.getAssetValueDiv();
    
        //�^�p�\��z�敪
        l_applyInfo.fundBundgetAmountDiv = l_row.getFundBudgetAmountDiv();
    
        //���Y�̐��i�敪
        l_applyInfo.fundBundgetDiv = l_row.getFundBudgetDiv();
    
        //�����̐��i�i���̑�
        l_applyInfo.fundBundgetEtc = l_row.getFundBudgetEtc();
    
        //�����p�敪
        if (BooleanEnum.TRUE.equals(l_row.getIdConfirmFlag()))
        {
            l_applyInfo.searchDiv = WEB3ExistenceDivDef.HAVING;
        }
        else
        {
            l_applyInfo.searchDiv = WEB3ExistenceDivDef.NOT_HAVING;
        }
    
        //�{�l�m�F���ދ敪
        l_applyInfo.idConfirmDocDiv = l_row.getIdConfirmDocDiv();
    
        //�{�l�m�F���ށi���̑�
        l_applyInfo.idConfirmDocEtc = l_row.getIdConfirmDocEtc();
    
        //�������������敪
        l_applyInfo.equityTaxType = l_row.getSpecialAcc();
    
        //�M�p��������敪
        l_applyInfo.marginTaxType = l_row.getSpecialAccMargin();
    
        //�����ғo�^�t���O
        if (BooleanEnum.TRUE.equals(l_row.getInsiderFlag()))
        {
            l_applyInfo.insiderFlag = true;
        }
        else
        {
            l_applyInfo.insiderFlag = false;
        }
    
        //�����Җ�����
        l_applyInfo.insiderProductName = l_row.getProductName();
    
        //���t��X�֔ԍ�
        l_applyInfo.sendZipCode = l_row.getSendZipCode();
    
        //���t��Z���P
        l_applyInfo.sendAddress1 = l_row.getSendAddressLine1();
    
        //���t��Z���Q
        l_applyInfo.sendAddress2 = l_row.getSendAddressLine2();
    
        //���t��Z���R
        l_applyInfo.sendAddress3 = l_row.getSendAddressLine3();
    
        //�敪1
        l_applyInfo.extItemDiv1 = l_row.getExtItemDiv1();
    
        //�敪2
        l_applyInfo.extItemDiv2 = l_row.getExtItemDiv2();
    
        //�敪3
        l_applyInfo.extItemDiv3 = l_row.getExtItemDiv3();
    
        //�敪4
        l_applyInfo.extItemDiv4 = l_row.getExtItemDiv4();
    
        //�敪5
        l_applyInfo.extItemDiv5 = l_row.getExtItemDiv5();
    
        //�敪6
        l_applyInfo.extItemDiv6 = l_row.getExtItemDiv6();
    
        //�敪7
        l_applyInfo.extItemDiv7 = l_row.getExtItemDiv7();
    
        //�敪8
        l_applyInfo.extItemDiv8 = l_row.getExtItemDiv8();
    
        //�敪9
        l_applyInfo.extItemDiv9 = l_row.getExtItemDiv9();
    
        //�敪10
        l_applyInfo.extItemDiv10 = l_row.getExtItemDiv10();
    
        //�t���O�P
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag1()))
        {
            l_applyInfo.extItemFlag1 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag1()))
        {
            l_applyInfo.extItemFlag1 = false;
        }

        //�t���O2
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag2()))
        {
            l_applyInfo.extItemFlag2 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag2()))
        {
            l_applyInfo.extItemFlag2 = false;
        }
    
        //�t���O3
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag3()))
        {
            l_applyInfo.extItemFlag3 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag3()))
        {
            l_applyInfo.extItemFlag3 = false;
        }
    
        //�t���O4
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag4()))
        {
            l_applyInfo.extItemFlag4 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag4()))
        {
            l_applyInfo.extItemFlag4 = false;
        }
    
        //�t���O5
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag5()))
        {
            l_applyInfo.extItemFlag5 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag5()))
        {
            l_applyInfo.extItemFlag5 = false;
        }
    
        //�t���O6
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag6()))
        {
            l_applyInfo.extItemFlag6 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag6()))
        {
            l_applyInfo.extItemFlag6 = false;
        }
    
        //�t���O7
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag7()))
        {
            l_applyInfo.extItemFlag7 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag7()))
        {
            l_applyInfo.extItemFlag7 = false;
        }
    
        //�t���O8
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag8()))
        {
            l_applyInfo.extItemFlag8 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag8()))
        {
            l_applyInfo.extItemFlag8 = false;
        }
    
        //�t���O9
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag9()))
        {
            l_applyInfo.extItemFlag9 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag9()))
        {
            l_applyInfo.extItemFlag9 = false;
        }
    
        //�t���O10
        if (BooleanEnum.TRUE.equals(l_row.getExtItemFlag10()))
        {
            l_applyInfo.extItemFlag10 = true;
        }
        else if (BooleanEnum.FALSE.equals(l_row.getExtItemFlag10()))
        {
            l_applyInfo.extItemFlag10 = false;
        }
    
        //�e�L�X�g1
        l_applyInfo.extItemText1 = l_row.getExtItemText1();
    
        //�e�L�X�g2
        l_applyInfo.extItemText2 = l_row.getExtItemText2();
    
        //�e�L�X�g3
        l_applyInfo.extItemText3 = l_row.getExtItemText3();
    
        //�e�L�X�g4
        l_applyInfo.extItemText4 = l_row.getExtItemText4();
    
        //�e�L�X�g5
        l_applyInfo.extItemText5 = l_row.getExtItemText5();
    
        //�e�L�X�g6
        l_applyInfo.extItemText6 = l_row.getExtItemText6();
    
        //�e�L�X�g7
        l_applyInfo.extItemText7 = l_row.getExtItemText7();
    
        //�e�L�X�g8
        l_applyInfo.extItemText8 = l_row.getExtItemText8();
    
        //�e�L�X�g9
        l_applyInfo.extItemText9 = l_row.getExtItemText9();
        
        //�e�L�X�g10
        l_applyInfo.extItemText10 = l_row.getExtItemText10();
    
        //�U�����s���
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_applyInfo.transferDiv))
        {
            WEB3AccOpenTransferBankInfo l_transferBankInfo = 
                new WEB3AccOpenTransferBankInfo();
                
            //��s�R�[�h
            l_transferBankInfo.financialInstitutionCode = l_row.getFinInstitutionCode();
    
            //��s��
            l_transferBankInfo.financialInstitutionName = l_row.getFinInstitutionName();
    
            // �x�X�R�[�h
            l_transferBankInfo.financialBranchCode = l_row.getFinBranchCode();
    
            //�x�X��
            l_transferBankInfo.financialBranchName = l_row.getFinBranchName();
    
            //�a���敪
            l_transferBankInfo.financialAccountDiv = l_row.getFinSaveDiv();
    
            //�����ԍ�
            l_transferBankInfo.financialAccountCode = l_row.getFinAccountNo();
    
            //�U�֎萔���敪
            l_transferBankInfo.transferCommissionDiv = l_row.getTransCommission();
        
            l_applyInfo.transferBankInfo = l_transferBankInfo;
        }
        else
        {
            l_applyInfo.transferBankInfo = null;
        }
        
    
        //�X�֐U�֏��
        if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_applyInfo.transferDiv))
        {
            WEB3AccOpenPostalTransferInfo l_postalTransferInfo = 
                new WEB3AccOpenPostalTransferInfo();
            
            //�ʒ��ԍ�
            l_postalTransferInfo.passbookCode = l_row.getPostalSaveNo();
    
            //�ʒ��L��
            l_postalTransferInfo.passbookSign = l_row.getPostalSaveCode();
            
            l_applyInfo.postalTransferInfo = l_postalTransferInfo;
        }
        else
        {
            l_applyInfo.postalTransferInfo = null;
        }
    
        //�����o�����
        //��������
        l_investmentExperienceInfo.experienceEquityDiv = l_row.getExperienceDivEquity();
    
        //�M�p���
        l_investmentExperienceInfo.experienceMarginDiv = l_row.getExperienceDivMargin();
    
        //��
        l_investmentExperienceInfo.experienceBondDiv = l_row.getExperienceDivBond();
    
        //�]���Ѝ�
        l_investmentExperienceInfo.experienceWtDiv = l_row.getExperienceDivWt();
    
        //�����M���i�����j
        l_investmentExperienceInfo.experienceFundSkDiv = l_row.getExperienceDivFundSk();
    
        //�����M���i���j
        l_investmentExperienceInfo.experienceFundBdDiv = l_row.getExperienceDivFundBd();
    
        //�敨�E�I�v�V����
        l_investmentExperienceInfo.experienceFoDiv = l_row.getExperienceDivFo();
    
        //�O���،�
        l_investmentExperienceInfo.experienceFEquityDiv = l_row.getExperienceDivFEquity();
    
        //���̑�
        l_investmentExperienceInfo.experienceEtcDiv = l_row.getExperienceDivEtc();
        
        l_applyInfo.investExpInfo = l_investmentExperienceInfo;
    
        //�����̂��������
        //���������t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagEquity()))
        {
            l_interestDealingInfo.interestEquityFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestEquityFlag = false;
        }
    
        //�����~�j�����t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagMinistock()))
        {
            l_interestDealingInfo.interestMstkFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestMstkFlag = false;
        }
    
        //�M�p����t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagMargin()))
        {
            l_interestDealingInfo.interestMarginFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestMarginFlag = false;
        }
    
        //���t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagBond()))
        {
            l_interestDealingInfo.interestBondFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestBondFlag = false;
        }
    
        //�����M���t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagFund()))
        {
            l_interestDealingInfo.interestFundFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestFundFlag = false;
        }
    
        //�敨�E�I�v�V�����t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagFo()))
        {
            l_interestDealingInfo.interestFoFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestFoFlag = false;
        }
    
        //�O���،��t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagFEquity()))
        {
            l_interestDealingInfo.interestFEquityFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestFEquityFlag = false;
        }
    
        //���̑��t���O
        if (BooleanEnum.TRUE.equals(l_row.getInterestFlagEtc()))
        {
            l_interestDealingInfo.interestEtcFlag = true;
        }
        else
        {
            l_interestDealingInfo.interestEtcFlag = false;
        }

        l_applyInfo.interestDealInfo = l_interestDealingInfo;
        //�ڋq�������̍쐬�敪
        l_applyInfo.accountRealNameDiv = l_row.getRealNameVoucherDiv();
        //�ڋq��������1
        l_applyInfo.accountRealName1 = l_row.getRealName1();
        //�ڋq��������2
        l_applyInfo.accountRealName2 = l_row.getRealName2();
        //����ƈ��҃R�[�h
        l_applyInfo.brokerageCode = l_row.getBrokerageTraderCode();
        //�敪�P�P
        l_applyInfo.extItemDiv11 = l_row.getExtItemDiv11();
        //�敪�P�Q
        l_applyInfo.extItemDiv12 = l_row.getExtItemDiv12();
        //�敪�P�R
        l_applyInfo.extItemDiv13 = l_row.getExtItemDiv13();
        //�敪�P�S
        l_applyInfo.extItemDiv14 = l_row.getExtItemDiv14();
        //�敪�P�T
        l_applyInfo.extItemDiv15 = l_row.getExtItemDiv15();
        
        //�����ҏ��
        l_insiderInfo.createDiv = l_row.getInsiderVoucherDiv();
        l_insiderInfo.productCode = l_row.getInsiderProductCode();
        l_insiderInfo.relationCode = l_row.getInsiderRelationDiv();
        l_insiderInfo.executive = l_row.getInsiderOfficerName();
        l_insiderInfo.positionCode = l_row.getInsiderPostCode();
        l_insiderInfo.position = l_row.getInsiderPostName();
        l_applyInfo.insiderInfo = l_insiderInfo;
        
        //GP���
        l_gpInfo.createDiv = l_row.getGpVoucherDiv();
        l_gpInfo.course = l_row.getGpCourse();
        l_gpInfo.plan = l_row.getGpPlan();
        l_gpInfo.targetFigure = l_row.getGpTargetFigure();
        l_gpInfo.targetYear = l_row.getGpTargetYear();
        l_gpInfo.targetMonth = l_row.getGpTargetMonth();
        l_gpInfo.installmentFigure = l_row.getGpInstallmentFigure();
        l_gpInfo.depositCycle = l_row.getGpDepositCycle();
        l_gpInfo.paymentRoot = l_row.getGpPaymentRoot();
        l_gpInfo.reinvestDiv = l_row.getGpReinvestDiv();
        l_gpInfo.taxType = l_row.getGpTaxDiv();
        l_gpInfo.taxfreeLimit = l_row.getGpTaxfreeLimit();
        l_gpInfo.specialTaxfreeLimit = l_row.getGpSpecialTaxfreeLimit();
        l_gpInfo.subscrSummary = l_row.getGpSubscrSummary();
        l_gpInfo.productCode = l_row.getGpProductCode();
        l_gpInfo.mortgageCustomer = l_row.getGpMortgageCustomer();
        l_gpInfo.mixCustomer = l_row.getGpMixCustomer();
        l_gpInfo.contract = l_row.getGpContract();
        l_applyInfo.gpInfo = l_gpInfo;
        
        //���O�����
        l_listedFeqInfo.createDiv = l_row.getStkVoucherDiv();
        l_listedFeqInfo.taxationTran = l_row.getStkTaxationTranDiv();
        l_listedFeqInfo.addressKana = l_row.getStkAddressLineKana();
        l_listedFeqInfo.transferDiv = l_row.getStkTransferDiv();
        l_listedFeqInfo.financialInstitutionCode = l_row.getStkFinInstitutionCode();
        l_listedFeqInfo.financialBranchCode = l_row.getStkFinBranchCode();
        l_listedFeqInfo.financialAccountDiv = l_row.getStkFinSaveDiv();
        l_listedFeqInfo.financialAccountCode = l_row.getStkFinAccountNo();
        l_applyInfo.listedFeqInfo = l_listedFeqInfo;
        
        //�O�ݗa���������
        l_foreignSaveInfo.financialAccountCode = l_row.getForeignAccountNo();
        l_foreignSaveInfo.financialAccountDiv = l_row.getForeignSaveDiv();
        l_foreignSaveInfo.financialAccountName = l_row.getForeignAccountName();
        l_foreignSaveInfo.financialAccountNameAlpha = l_row.getForeignAccountNameEng();
        l_applyInfo.foreignSaveInfo = l_foreignSaveInfo;

        //�폜�t���O
        if (l_row.getDeleteFlag() != null)
        {
            l_applyInfo.deleteFlag = l_row.getDeleteFlag().intValue() + "";
        }

        //�폜����
        l_applyInfo.deleteDate = l_row.getDeleteTimestamp();

        //����t���O
        l_applyInfo.printFlag = l_row.getPrintFlag();

        //��̃t���O
        if (l_row.getReceiptFlag() != null)
        {
            l_applyInfo.receiveFlag = l_row.getReceiptFlag().intValue() + "";
        }

        //�����t���O
        if (l_row.getAgreementFlag() != null)
        {
            l_applyInfo.approveFlag = l_row.getAgreementFlag().intValue() + "";
        }

        //�O���l�t���O
        if (l_row.getForeignFlag() != null)
        {
            l_applyInfo.foreignerFlag = l_row.getForeignFlag().intValue() + "";
        }

        //�@@�\�ʒm���
        WEB3AccOpenAgencyInfo l_agencyInfo= new WEB3AccOpenAgencyInfo();
        //�t���K�i1
        l_agencyInfo.agencyAccNameKana1 = l_row.getAgencyAccNameKana1();
        //�t���K�i2
        l_agencyInfo.agencyAccNameKana2 = l_row.getAgencyAccNameKana2();
        //����1
        l_agencyInfo.agencyAccName1 = l_row.getAgencyAccName1();
        //����2
        l_agencyInfo.agencyAccName2 = l_row.getAgencyAccName2();
        //�Z��1
        l_agencyInfo.agencyAddress1 = l_row.getAgencyAddressLine1();
        //�Z��2
        l_agencyInfo.agencyAddress2 = l_row.getAgencyAddressLine2();
        //��\�҂̖�E
        l_agencyInfo.agencyRepPost = l_row.getAgencyRepPost();
        //��\�҂̃t���K�i1
        l_agencyInfo.agencyRepNameKana1 = l_row.getAgencyRepNameKana1();
        //��\�҂̃t���K�i2
        l_agencyInfo.agencyRepNameKana2 = l_row.getAgencyRepNameKana2();
        //��\�҂̎���1
        l_agencyInfo.agencyRepName1 = l_row.getAgencyRepName1();
        //��\�҂̎���2
        l_agencyInfo.agencyRepName2 = l_row.getAgencyRepName2();
        l_applyInfo.agencyInfo = l_agencyInfo;

        log.exiting(STR_METHOD_NAME); 
        return l_applyInfo;
    }
    
    /**
     * (to�����J�݌����q)<BR>
     * �����J�ݐ\�����I�u�W�F�N�g���A�����J�݌����q�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g���� <BR>
     * �@@�f�t�H���g�R���X�g���N�^�ɂāA�����J�݌����q�s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g <BR>
     * �@@�����J�ݐ\����񃁃b�Z�[�W�I�u�W�F�N�g���A��������
     *�@@�@@�����J�݌����q�s�I�u�W�F�N�g�̑Ή����鍀�ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�� boolean�^�̍��ڂ́A�Ή�����BooleanEnum�l�ɕϊ����邱�ƁB�@@<BR>
     * �@@�� �i�U�����s��� != null�j�̏ꍇ�A�U�֋敪�Ɂu1�F��s�U���v���Z�b�g����B<BR>
     * �@@�� �i�X�֐U�֏�� != null�j�̏ꍇ�A�U�֋敪�Ɂu5�F�X���U���v���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �R�j�@@�I�u�W�F�N�g����<BR>
     * �@@���������s�I�u�W�F�N�g���w�肵�A�����J�݌����q�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_accOpenApplyInfo - �����J�ݐ\����񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 41945E74032F
     */
    public WEB3AccOpenExpAccountOpen toAccOpenExpAccountOpen(WEB3AccOpenApplyInfo l_accOpenApplyInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenApplyInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@�s�I�u�W�F�N�g����
        ExpAccountOpenParams l_params = new ExpAccountOpenParams();

        //�Q�j�@@�v���p�e�B�Z�b�g
        //����������
        l_params.setInfomationClaimDatetime(l_accOpenApplyInfo.infoClaimDate); 
    
        //�X�V��
        l_params.setLastUpdatedTimestamp(l_accOpenApplyInfo.updateDate);
    
        //�X�V�҃R�[�h
        l_params.setLastUpdater(l_accOpenApplyInfo.updaterCode);
    
        //���ʃR�[�h
        l_params.setAccOpenRequestNumber(l_accOpenApplyInfo.requestNumber);
        
        //���͋敪
        l_params.setOrderDiv(l_accOpenApplyInfo.inputDiv);
        
        //�쐬�҃R�[�h
        l_params.setCreator(l_accOpenApplyInfo.creatorCode);
    
        //���������t���O
        if (l_accOpenApplyInfo.exAccountFlag)
        {
            l_params.setExAccountFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExAccountFlag(BooleanEnum.FALSE);
        }
    
        //���������R�[�h
        l_params.setExAccountCode(l_accOpenApplyInfo.exAccountCode);
    
        //�����������X��
        l_params.setExBranchName(l_accOpenApplyInfo.exAccountBranchName);
    
        //�،���ЃR�[�h
        l_params.setInstitutionCode(l_accOpenApplyInfo.institutionCode);
    
        //���X�R�[�h
        l_params.setBranchCode(l_accOpenApplyInfo.branchCode);
    
        //�����敪
        l_params.setAccountDiv(l_accOpenApplyInfo.accountType);
    
        //�ڋq�R�[�h
        l_params.setAccountCode(l_accOpenApplyInfo.accountCode);
    
        //���҃R�[�h�iSONAR�j
        l_params.setSonarTraderCode(l_accOpenApplyInfo.traderCode);
    
        //���[���A�h���X
        l_params.setEmailAddress(l_accOpenApplyInfo.mailAddress);
    
        // ���[���A�h���X�i�g�сj
        l_params.setEmailAddressAlt1(l_accOpenApplyInfo.mobileMailAddress);
    
        //�ڋq���i�����j
        l_params.setFamilyName(l_accOpenApplyInfo.accountFamilyName);
    
        //�ڋq���i�����j
        l_params.setGivenName(l_accOpenApplyInfo.accountName);
    
        //�ڋq���i�J�i�j
        l_params.setFamilyNameAlt1(l_accOpenApplyInfo.accountFamilyNameKana);
    
        //�ڋq���i�J�i�j
        l_params.setGivenNameAlt1(l_accOpenApplyInfo.accountNameKana);
    
        //���N�����N��
        l_params.setEraBorn(l_accOpenApplyInfo.eraBorn);
    
        //���N����
        l_params.setBornDate(l_accOpenApplyInfo.bornDate);
    
        //����
        l_params.setSex(l_accOpenApplyInfo.sex);
        
        //�Ïؔԍ�
        WEB3Crypt l_crypt = new WEB3Crypt();
        l_params.setInitialPassword(l_crypt.encrypt(l_accOpenApplyInfo.password));
    
        //�X�֔ԍ�
        l_params.setZipCode(l_accOpenApplyInfo.zipCode);
    
        //�Z���P�i�J�i�j
        l_params.setAddressLine1Kana(l_accOpenApplyInfo.addressKana1);
    
        //�Z���Q�i�J�i�j
        l_params.setAddressLine2Kana(l_accOpenApplyInfo.addressKana2);
    
        //�Z���R�i�J�i�j
        l_params.setAddressLine3Kana(l_accOpenApplyInfo.addressKana3);
    
        //�Z���P�i�����j
        l_params.setAddressLine1(l_accOpenApplyInfo.address1);
    
        //�Z���Q�i�����j
        l_params.setAddressLine2(l_accOpenApplyInfo.address2);
    
        //�Z���R�i�����j
        l_params.setAddressLine3(l_accOpenApplyInfo.address3);
    
        //�d�b�ԍ�
        l_params.setTelephone(l_accOpenApplyInfo.telephone);
    
        //�g�єԍ�
        l_params.setMobile(l_accOpenApplyInfo.mobileTelephone);
    
        //�e�`�w�ԍ�
        l_params.setFax(l_accOpenApplyInfo.faxTelephone);
    
        //�Ζ�����
        WEB3AccOpenOfficeInfo l_officeInfo = l_accOpenApplyInfo.officeInfo; 
        
        if (l_officeInfo != null)
        {
            //�E�Ƌ敪
            l_params.setOccupationDiv(l_officeInfo.occupationDiv);
    
            //�Ζ��於��
            l_params.setOffice(l_officeInfo.officeName);
    
            // �Ζ���X�֔ԍ�<BR>
            l_params.setOfficeZipCode(l_officeInfo.officeZipCode);
    
            //�Ζ���Z��
            l_params.setOfficeAddress(l_officeInfo.officeAdress);
    
            //�Ζ���d�b�ԍ�
            l_params.setOfficeTelephone(l_officeInfo.officeTelephone);
    
            //�Ζ���e�`�w�ԍ�
            l_params.setOfficeFax(l_officeInfo.officeFaxTelephone);
    
            //��������
            l_params.setDepartment(l_officeInfo.department);
    
            //��E��
            l_params.setPost(l_officeInfo.position);
        }
    
        //�A����Z��
        l_params.setContactAddress(l_accOpenApplyInfo.contactAddress);
    
        //�A����d�b�ԍ�
        l_params.setContactTelephone(l_accOpenApplyInfo.contactTelephone);
    
        //�����敪
        l_params.setFamilyRelationship(l_accOpenApplyInfo.familyRelationDiv);
    
        //�����i���̑��j
        l_params.setFamilyRelationshipEtc(l_accOpenApplyInfo.familyRelationEtc);
    
        //���ю喼�i�����j
        l_params.setHouseholder(l_accOpenApplyInfo.houseHolderName);
    
        //���ю喼�i�J�i�j
        l_params.setHouseholderKana(l_accOpenApplyInfo.houseHolderNameKana);
    
        //���ю�Ζ�����
        l_officeInfo = l_accOpenApplyInfo.houseHolderOfficeInfo;
        
        if (l_officeInfo != null)
        {
            //�E�Ƌ敪
            l_params.setHouseholderOccupationDiv(l_officeInfo.occupationDiv);
            
            //�Ζ��於��
            l_params.setHouseholderOffice(l_officeInfo.officeName);
            
            //�Ζ���Z��
            l_params.setHouseholderOfficeAddress(l_officeInfo.officeAdress);
            
            //�Ζ���d�b�ԍ�
            l_params.setHouseholderOfficeTel(l_officeInfo.officeTelephone);
            
            //�Ζ���e�`�w�ԍ�
            l_params.setHouseholderOfficeFax(l_officeInfo.officeFaxTelephone);
            
            //��������
            l_params.setHouseholderDepartment(l_officeInfo.department);
            
            //��E��
            l_params.setHouseholderPost(l_officeInfo.position); 
        }
    
        //���Z�i���{�j�^�񋏏Z�i���{�ȊO�j�敪
        l_params.setResident(l_accOpenApplyInfo.residentDiv);
    
        //�U�֋敪
        if (l_accOpenApplyInfo.transferBankInfo != null)
        {
            l_params.setTransferDiv(WEB3TransferDivDef.BANK_TRANSFER);
        }
        if (l_accOpenApplyInfo.postalTransferInfo != null)
        {
            l_params.setTransferDiv(WEB3TransferDivDef.POSTAL_TRANSFER);
        }
        
        //�������`�l
        l_params.setFinAccountName(l_accOpenApplyInfo.financialAccountName);
    
        //�����ړI�敪
        l_params.setInvestPurposeDiv(l_accOpenApplyInfo.investPurposeDiv);
    
        //������@@�敪
        l_params.setAppliMotivatDiv(l_accOpenApplyInfo.appliMotivatDiv);
    
        //�N���敪
        l_params.setAnnualIncomeDiv(l_accOpenApplyInfo.annualIncomeDiv);
    
        //���Z���Y�敪
        l_params.setAssetValueDiv(l_accOpenApplyInfo.assetValueDiv);
    
        //�^�p�\��z�敪
        l_params.setFundBudgetAmountDiv(l_accOpenApplyInfo.fundBundgetAmountDiv);
    
        //���Y�̐��i�敪
        l_params.setFundBudgetDiv(l_accOpenApplyInfo.fundBundgetDiv);
    
        //�����̐��i�i���̑�
        l_params.setFundBudgetEtc(l_accOpenApplyInfo.fundBundgetEtc);
    
        //�����p�敪
        if (WEB3ExistenceDivDef.HAVING.equals(l_accOpenApplyInfo.searchDiv))
        {
            l_params.setIdConfirmFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setIdConfirmFlag(BooleanEnum.FALSE);
        }
    
        //�{�l�m�F���ދ敪
        l_params.setIdConfirmDocDiv(l_accOpenApplyInfo.idConfirmDocDiv);
    
        //�{�l�m�F���ށi���̑�
        l_params.setIdConfirmDocEtc(l_accOpenApplyInfo.idConfirmDocEtc);
    
        //�������������敪
        l_params.setSpecialAcc(l_accOpenApplyInfo.equityTaxType);
    
        //�M�p��������敪
        l_params.setSpecialAccMargin(l_accOpenApplyInfo.marginTaxType);
    
        //�����ғo�^�t���O
        if (l_accOpenApplyInfo.insiderFlag)
        {
            l_params.setInsiderFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setInsiderFlag(BooleanEnum.FALSE);
        }
    
        //�����Җ�����
        l_params.setProductName(l_accOpenApplyInfo.insiderProductName);
    
        //���t��X�֔ԍ�
        l_params.setSendZipCode(l_accOpenApplyInfo.sendZipCode);
    
        //���t��Z���P
        l_params.setSendAddressLine1(l_accOpenApplyInfo.sendAddress1);
    
        //���t��Z���Q
        l_params.setSendAddressLine2(l_accOpenApplyInfo.sendAddress2);
    
        //���t��Z���R
        l_params.setSendAddressLine3(l_accOpenApplyInfo.sendAddress3);
    
        //�敪1
        l_params.setExtItemDiv1(l_accOpenApplyInfo.extItemDiv1);
    
        //�敪2
        l_params.setExtItemDiv2(l_accOpenApplyInfo.extItemDiv2);
    
        //�敪3
        l_params.setExtItemDiv3(l_accOpenApplyInfo.extItemDiv3);
    
        //�敪4
        l_params.setExtItemDiv4(l_accOpenApplyInfo.extItemDiv4);
    
        //�敪5
        l_params.setExtItemDiv5(l_accOpenApplyInfo.extItemDiv5);
    
        //�敪6
        l_params.setExtItemDiv6(l_accOpenApplyInfo.extItemDiv6);
    
        //�敪7
        l_params.setExtItemDiv7(l_accOpenApplyInfo.extItemDiv7);
    
        //�敪8
        l_params.setExtItemDiv8(l_accOpenApplyInfo.extItemDiv8);
    
        //�敪9
        l_params.setExtItemDiv9(l_accOpenApplyInfo.extItemDiv9);
    
        //�敪10
        l_params.setExtItemDiv10(l_accOpenApplyInfo.extItemDiv10);
    
        //�t���O�P
        if (l_accOpenApplyInfo.extItemFlag1)
        {
            l_params.setExtItemFlag1(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag1(BooleanEnum.FALSE);
        }
    
        //�t���O2
        if (l_accOpenApplyInfo.extItemFlag2)
        {
            l_params.setExtItemFlag2(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag2(BooleanEnum.FALSE);
        }
    
        //�t���O3
        if (l_accOpenApplyInfo.extItemFlag3)
        {
            l_params.setExtItemFlag3(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag3(BooleanEnum.FALSE);
        }
    
        //�t���O4
        if (l_accOpenApplyInfo.extItemFlag4)
        {
            l_params.setExtItemFlag4(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag4(BooleanEnum.FALSE);
        }
    
        //�t���O5
        if (l_accOpenApplyInfo.extItemFlag5)
        {
            l_params.setExtItemFlag5(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag5(BooleanEnum.FALSE);
        }
    
        //�t���O6
        if (l_accOpenApplyInfo.extItemFlag6)
        {
            l_params.setExtItemFlag6(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag6(BooleanEnum.FALSE);
        }
    
        //�t���O7
        if (l_accOpenApplyInfo.extItemFlag7)
        {
            l_params.setExtItemFlag7(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag7(BooleanEnum.FALSE);
        }
    
        //�t���O8
        if (l_accOpenApplyInfo.extItemFlag8)
        {
            l_params.setExtItemFlag8(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag8(BooleanEnum.FALSE);
        }
    
        //�t���O9
        if (l_accOpenApplyInfo.extItemFlag9)
        {
            l_params.setExtItemFlag9(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag9(BooleanEnum.FALSE);
        }
    
        //�t���O10
        if (l_accOpenApplyInfo.extItemFlag10)
        {
            l_params.setExtItemFlag10(BooleanEnum.TRUE);
        }
        else
        {
            l_params.setExtItemFlag10(BooleanEnum.FALSE);
        }
    
        //�e�L�X�g1
        l_params.setExtItemText1(l_accOpenApplyInfo.extItemText1);
    
        //�e�L�X�g2
        l_params.setExtItemText2(l_accOpenApplyInfo.extItemText2);
    
        //�e�L�X�g3
        l_params.setExtItemText3(l_accOpenApplyInfo.extItemText3);
    
        //�e�L�X�g4
        l_params.setExtItemText4(l_accOpenApplyInfo.extItemText4);
    
        //�e�L�X�g5
        l_params.setExtItemText5(l_accOpenApplyInfo.extItemText5);
    
        //�e�L�X�g6
        l_params.setExtItemText6(l_accOpenApplyInfo.extItemText6);
    
        //�e�L�X�g7
        l_params.setExtItemText7(l_accOpenApplyInfo.extItemText7);
    
        //�e�L�X�g8
        l_params.setExtItemText8(l_accOpenApplyInfo.extItemText8);
    
        //�e�L�X�g9
        l_params.setExtItemText9(l_accOpenApplyInfo.extItemText9);
        
        //�e�L�X�g10
        l_params.setExtItemText10(l_accOpenApplyInfo.extItemText10);
    
        //�U�����s���
        WEB3AccOpenTransferBankInfo l_transferBankInfo = 
            l_accOpenApplyInfo.transferBankInfo;
        if (l_accOpenApplyInfo.transferBankInfo != null)
        {
            //��s�R�[�h
            l_params.setFinInstitutionCode(l_transferBankInfo.financialInstitutionCode);
    
            //��s��
            l_params.setFinInstitutionName(l_transferBankInfo.financialInstitutionName);
    
            // �x�X�R�[�h
            l_params.setFinBranchCode(l_transferBankInfo.financialBranchCode);
    
            //�x�X��
            l_params.setFinBranchName(l_transferBankInfo.financialBranchName);
    
            //�a���敪
            l_params.setFinSaveDiv(l_transferBankInfo.financialAccountDiv);
    
            //�����ԍ�
            l_params.setFinAccountNo(l_transferBankInfo.financialAccountCode);
    
            //�U�֎萔���敪
            l_params.setTransCommission(l_transferBankInfo.transferCommissionDiv);
        }
        
        //�X�֐U�֏��
        WEB3AccOpenPostalTransferInfo l_postalTransferInfo = 
            l_accOpenApplyInfo.postalTransferInfo;
        if (l_accOpenApplyInfo.postalTransferInfo != null)
        {
            //�ʒ��ԍ�
            l_params.setPostalSaveNo(l_postalTransferInfo.passbookCode);
    
            //�ʒ��L��
            l_params.setPostalSaveCode(l_postalTransferInfo.passbookSign);
        }
    
        //�����o�����
        WEB3AccOpenInvestmentExperienceInfo l_investmentExperienceInfo = 
            l_accOpenApplyInfo.investExpInfo;
            
        if (l_investmentExperienceInfo != null)
        {
            //��������
            l_params.setExperienceDivEquity(l_investmentExperienceInfo.experienceEquityDiv);
    
            //�M�p���
            l_params.setExperienceDivMargin(l_investmentExperienceInfo.experienceMarginDiv);
    
            //��
            l_params.setExperienceDivBond(l_investmentExperienceInfo.experienceBondDiv);
    
            //�]���Ѝ�
            l_params.setExperienceDivWt(l_investmentExperienceInfo.experienceWtDiv);
    
            //�����M���i�����j
            l_params.setExperienceDivFundSk(l_investmentExperienceInfo.experienceFundSkDiv);
    
            //�����M���i���j
            l_params.setExperienceDivFundBd(l_investmentExperienceInfo.experienceFundBdDiv);
    
            //�敨�E�I�v�V����
            l_params.setExperienceDivFo(l_investmentExperienceInfo.experienceFoDiv);
    
            //�O���،�
            l_params.setExperienceDivFEquity(l_investmentExperienceInfo.experienceFEquityDiv);
    
            //���̑�
            l_params.setExperienceDivEtc(l_investmentExperienceInfo.experienceEtcDiv);
        }

        //�����̂��������
        WEB3AccOpenInterestDealingInfo l_interestDealingInfo = 
            l_accOpenApplyInfo.interestDealInfo;
            
        if (l_interestDealingInfo != null)
        {
            //���������t���O
            if (l_interestDealingInfo.interestEquityFlag)
            {
                l_params.setInterestFlagEquity(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagEquity(BooleanEnum.FALSE);
            }
    
            //�����~�j�����t���O
            if (l_interestDealingInfo.interestMstkFlag)
            {
                l_params.setInterestFlagMinistock(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagMinistock(BooleanEnum.FALSE);
            }
    
            //�M�p����t���O
            if (l_interestDealingInfo.interestMarginFlag)
            {
                l_params.setInterestFlagMargin(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagMargin(BooleanEnum.FALSE);
            }
    
            //���t���O
            if (l_interestDealingInfo.interestBondFlag)
            {
                l_params.setInterestFlagBond(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagBond(BooleanEnum.FALSE);
            }
    
            //�����M���t���O
            if (l_interestDealingInfo.interestFundFlag)
            {
                l_params.setInterestFlagFund(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagFund(BooleanEnum.FALSE);
            }
    
            //�敨�E�I�v�V�����t���O
            if (l_interestDealingInfo.interestFoFlag)
            {
                l_params.setInterestFlagFo(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagFo(BooleanEnum.FALSE);
            }
    
            //�O���،��t���O
            if (l_interestDealingInfo.interestFEquityFlag)
            {
                l_params.setInterestFlagFEquity(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagFEquity(BooleanEnum.FALSE);
            }
    
            //���̑��t���O
            if (l_interestDealingInfo.interestEtcFlag)
            {
                l_params.setInterestFlagEtc(BooleanEnum.TRUE);
            }
            else
            {
                l_params.setInterestFlagEtc(BooleanEnum.FALSE);
            }
        }
        else
        {
            //���������t���O
            l_params.setInterestFlagEquity(BooleanEnum.FALSE);

            //�����~�j�����t���O
            l_params.setInterestFlagMinistock(BooleanEnum.FALSE);
    
            //�M�p����t���O
            l_params.setInterestFlagMargin(BooleanEnum.FALSE);
    
            //���t���O
            l_params.setInterestFlagBond(BooleanEnum.FALSE);
    
            //�����M���t���O
            l_params.setInterestFlagFund(BooleanEnum.FALSE);

            //�敨�E�I�v�V�����t���O
            l_params.setInterestFlagFo(BooleanEnum.FALSE);

            //�O���،��t���O
            l_params.setInterestFlagFEquity(BooleanEnum.FALSE);
    
            //���̑��t���O
            l_params.setInterestFlagEtc(BooleanEnum.FALSE);
        }
        
        //�ڋq�������̍쐬�敪
        l_params.setRealNameVoucherDiv(l_accOpenApplyInfo.accountRealNameDiv);
        
        //�ڋq�������̂P
        l_params.setRealName1(l_accOpenApplyInfo.accountRealName1);
        
        //�ڋq�������̂Q
        l_params.setRealName2(l_accOpenApplyInfo.accountRealName2);
        
        //�����ғo�^�`�[
        WEB3AccOpenInsiderInfo l_insiderInfo = l_accOpenApplyInfo.insiderInfo;
        if (l_insiderInfo != null)
        {
            //�i�����ҁj�쐬�敪
            l_params.setInsiderVoucherDiv(l_insiderInfo.createDiv);
            
            //�i�����ҁj�����R�[�h
            l_params.setInsiderProductCode(l_insiderInfo.productCode);
            
            //�i�����ҁj�֌W�敪
            l_params.setInsiderRelationDiv(l_insiderInfo.relationCode);
            
            //�i�����ҁj������
            l_params.setInsiderOfficerName(l_insiderInfo.executive);
            
            //�i�����ҁj��E���R�[�h
            l_params.setInsiderPostCode(l_insiderInfo.positionCode);
            
            //�i�����ҁj��E��
            l_params.setInsiderPostName(l_insiderInfo.position);
        }
        
        //GP�����o�^�`�[
        WEB3AccOpenGpInfo l_gpInfo = l_accOpenApplyInfo.gpInfo;
        if (l_gpInfo != null)
        {
            //�i�f�o�j�쐬�敪
            l_params.setGpVoucherDiv(l_gpInfo.createDiv);
            
            //�i�f�o�j�R�[�X
            l_params.setGpCourse(l_gpInfo.course);
            
            //�i�f�o�j�v����
            l_params.setGpPlan(l_gpInfo.plan);
            
            //�i�f�o�j�ڕW�z
            l_params.setGpTargetFigure(l_gpInfo.targetFigure);
            
            //�i�f�o�j�ڕW�N
            l_params.setGpTargetYear(l_gpInfo.targetYear);
            
            //�i�f�o�j�ڕW��
            l_params.setGpTargetMonth(l_gpInfo.targetMonth);
            
            //�i�f�o�j�ϗ��z
            l_params.setGpInstallmentFigure(l_gpInfo.installmentFigure);
            
            //�i�f�o�j��������
            l_params.setGpDepositCycle(l_gpInfo.depositCycle);
            
            //�i�f�o�j��n�o�H
            l_params.setGpPaymentRoot(l_gpInfo.paymentRoot);
            
            //�i�f�o�j�ē����敪
            l_params.setGpReinvestDiv(l_gpInfo.reinvestDiv);
            
            //�i�f�o�j�ŋ敪
            l_params.setGpTaxDiv(l_gpInfo.taxType);
            
            //�i�f�o�j�i�D�j���x�z
            l_params.setGpTaxfreeLimit(l_gpInfo.taxfreeLimit);
            
            //�i�f�o�j�i���D�j���x�z
            l_params.setGpSpecialTaxfreeLimit(l_gpInfo.specialTaxfreeLimit);
            
            //�i�f�o�j�����E�v
            l_params.setGpSubscrSummary(l_gpInfo.subscrSummary);
            
            //�i�f�o�j�����R�[�h
            l_params.setGpProductCode(l_gpInfo.productCode);
            
            //�i�f�o�j�S�ۋq
            l_params.setGpMortgageCustomer(l_gpInfo.mortgageCustomer);
            
            //�i�f�o�j�~�b�N�X�q
            l_params.setGpMixCustomer(l_gpInfo.mixCustomer);
            
            //�i�f�o�j�_��
            l_params.setGpContract(l_gpInfo.contract);
        }
        
        //���O���E����o�^�`�[
        WEB3AccOpenListedFeqInfo l_listedFeqInfo = l_accOpenApplyInfo.listedFeqInfo;
        if (l_listedFeqInfo != null)
        {
            //�i���O���j�쐬�敪
            l_params.setStkVoucherDiv(l_listedFeqInfo.createDiv);
            
            //�i���O���j���n
            l_params.setStkTaxationTranDiv(l_listedFeqInfo.taxationTran);
            
            //�i���O���j�Z���i�J�i�j
            l_params.setStkAddressLineKana(l_listedFeqInfo.addressKana);
            
            //�i���O���j����
            l_params.setStkTransferDiv(l_listedFeqInfo.transferDiv);
            
            //�i���O���j��s�R�[�h
            l_params.setStkFinInstitutionCode(l_listedFeqInfo.financialInstitutionCode);
            
            //�i���O���j�x�X�R�[�h
            l_params.setStkFinBranchCode(l_listedFeqInfo.financialBranchCode);
            
            //�i���O���j�a���敪 
            l_params.setStkFinSaveDiv(l_listedFeqInfo.financialAccountDiv);
            
            //�i���O���j�����ԍ�
            l_params.setStkFinAccountNo(l_listedFeqInfo.financialAccountCode);
        }

        //����ƈ��҃R�[�h
        l_params.setBrokerageTraderCode(l_accOpenApplyInfo.brokerageCode);
        
        //�e�Њg�����ځi�敪1�P�j
        l_params.setExtItemDiv11(l_accOpenApplyInfo.extItemDiv11);
        
        //�e�Њg�����ځi�敪�P2�j
        l_params.setExtItemDiv12(l_accOpenApplyInfo.extItemDiv12);

        //�e�Њg�����ځi�敪�P3�j
        l_params.setExtItemDiv13(l_accOpenApplyInfo.extItemDiv13);

        //�e�Њg�����ځi�敪�P4�j
        l_params.setExtItemDiv14(l_accOpenApplyInfo.extItemDiv14);

        //�e�Њg�����ځi�敪�P5�j
        l_params.setExtItemDiv15(l_accOpenApplyInfo.extItemDiv15);
        
        //�O�ݗa���������
        WEB3AccOpenForeignSaveInfo l_foreignSaveInfo = l_accOpenApplyInfo.foreignSaveInfo;
        if (l_foreignSaveInfo != null)
        {
            //�����ԍ��i�O�݁j
            l_params.setForeignAccountNo(l_foreignSaveInfo.financialAccountCode);
            
            //�������`�l�i�O�݁j
            l_params.setForeignAccountName(l_foreignSaveInfo.financialAccountName);
            
            //�������`�l�p���i�O�݁j
            l_params.setForeignAccountNameEng(l_foreignSaveInfo.financialAccountNameAlpha);
            
            //�a���敪�i�O�݁j
            l_params.setForeignSaveDiv(l_foreignSaveInfo.financialAccountDiv);
        }

        //�폜�t���O
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.deleteFlag))
        {
            l_params.setDeleteFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.deleteFlag)));
        }

        //�폜����
        l_params.setDeleteTimestamp(l_accOpenApplyInfo.deleteDate);

        //����t���O
        l_params.setPrintFlag(l_accOpenApplyInfo.printFlag);

        //��̃t���O
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.receiveFlag))
        {
            l_params.setReceiptFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.receiveFlag)));
        }

        //�����t���O
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.approveFlag))
        {
            l_params.setAgreementFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.approveFlag)));
        }

        //�O���l�t���O
        if (!WEB3StringTypeUtility.isEmpty(l_accOpenApplyInfo.foreignerFlag))
        {
            l_params.setForeignFlag(
                (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                    BooleanEnum.class, Integer.parseInt(l_accOpenApplyInfo.foreignerFlag)));
        }

        //�@@�\�ʒm���
        WEB3AccOpenAgencyInfo l_agencyInfo= l_accOpenApplyInfo.agencyInfo;
        if (l_agencyInfo != null)
        {
            //�t���K�i1
            l_params.setAgencyAccNameKana1(l_agencyInfo.agencyAccNameKana1);
            //�t���K�i2
            l_params.setAgencyAccNameKana2(l_agencyInfo.agencyAccNameKana2);
            //����1
             l_params.setAgencyAccName1(l_agencyInfo.agencyAccName1);
            //����2
             l_params.setAgencyAccName2(l_agencyInfo.agencyAccName2);
            //�Z��1
             l_params.setAgencyAddressLine1(l_agencyInfo.agencyAddress1);
            //�Z��2
             l_params.setAgencyAddressLine2(l_agencyInfo.agencyAddress2);
            //��\�҂̖�E
             l_params.setAgencyRepPost(l_agencyInfo.agencyRepPost);
            //��\�҂̃t���K�i1
             l_params.setAgencyRepNameKana1(l_agencyInfo.agencyRepNameKana1);
            //��\�҂̃t���K�i2
             l_params.setAgencyRepNameKana2(l_agencyInfo.agencyRepNameKana2);
            //��\�҂̎���1
             l_params.setAgencyRepName1(l_agencyInfo.agencyRepName1);
            //��\�҂̎���2
             l_params.setAgencyRepName2(l_agencyInfo.agencyRepName2);
        }

        //�R�j�@@�I�u�W�F�N�g����
        WEB3AccOpenExpAccountOpen l_expAccountOpen = new WEB3AccOpenExpAccountOpen(l_params);
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_expAccountOpen;
    }
    
    /**
     * (to�`�[�쐬���)<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�I�u�W�F�N�g�̔z����A�`�[�쐬���𐶐�����B<BR>
     * <BR>
     * �P�j�@@�`�[�쐬���I�u�W�F�N�g����<BR>
     * �@@�`�[�쐬���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �@@�ȉ��̒ʂ�A�`�[�쐬���I�u�W�F�N�g�ɒl���Z�b�g���ԋp����B<BR>
     * <BR>
     * �@@�`�[�쐬���.G11�ڋq�o�^ = �i�f�[�^�R�[�h=�ڋq�o�^�j�ɊY������v�f��<BR>
     * �`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G1151�_�񏑒��� = �i�f�[�^�R�[�h=�_�񏑒����j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G26�U�֐\�� = �i�f�[�^�R�[�h=�U�֐\���i��s�j�C<BR>
     * �f�[�^�R�[�h=�U�֐\���i�X���j�j�ɊY������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.GA300�ېU���� = �i�f�[�^�R�[�h=�ېU���Ӂj��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G5401�L����� = �i�f�[�^�R�[�h=�L�����j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.GI601MRF���� = �i�f�[�^�R�[�h=MRF�����j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G5511�Ïؔԍ� = �i�f�[�^�R�[�h=�Ïؔԍ��j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G1159�d�v���� = �i�f�[�^�R�[�h=�d�v�����j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G1175�{�l�m�F = �i�f�[�^�R�[�h=�{�l�m�F�j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.GI311��c�E�d�q��t�E������� = �i�`�[�R�[�h=<BR>
     * ��c�E�d�q��t�E��������j�ɊY������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G9801������ = �i�f�[�^�R�[�h=�����ҁj��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G1220GP���� = �i�f�[�^�R�[�h=GP�����j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z�� <BR>
     * �@@�`�[�쐬���.G8610���O���E���� = �i�f�[�^�R�[�h=���O���E����j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.G1190�ڋq�������� = �i�f�[�^�R�[�h=�ڋq�������́j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z�� <BR>
     * �@@�`�[�쐬���.G43�O�ݗa�������o�^ = �i�f�[�^�R�[�h=�O�ݗa�������o�^�j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * �@@�`�[�쐬���.GS103�@@�\�ʒm = �i�f�[�^�R�[�h=�@@�\�ʒm���o�^�j��<BR>
     * �Y������v�f�̓`�[�쐬�X�e�[�^�X�̔z��<BR>
     * <BR>
     * �@@�� �`�[�쐬�G���[������ꍇ�i�����J�ݓ`�[�쐬�X�e�[�^�X.�G���[�R�[�h != null�j�A<BR>
     * �@@�@@�Y���v�f�́A�u�����J�ݓ`�[�쐬�X�e�[�^�X.�`�[�쐬�X�e�[�^�X�F<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X.�G���[�R�[�h�v�̌`���ŁA�G���[�R�[�h��<BR>
     * �ǉ������X�e�[�^�X��ҏW����B<BR>
     * �@@�@@�ȊO�A�u�����J�ݓ`�[�쐬�X�e�[�^�X.�`�[�쐬�X�e�[�^�X�v��ҏW����B<BR>
     * @@param l_accOpenVoucherCreatedStatuses - �����J�ݓ`�[�쐬�X�e�[�^�X�I�u�W�F�N�g�̔z��
     * @@return webbroker3.accountopen.message.WEB3AccOpenVoucherInfo
     * @@roseuid 4191A6460330
     */
    public WEB3AccOpenVoucherInfo toAccOpenVoucherInfo(WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses) 
    {
        final String STR_METHOD_NAME = " toAccOpenVoucherInfo(WEB3AccOpenVoucherCreatedStatus[]) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�`�[�쐬���I�u�W�F�N�g����
        WEB3AccOpenVoucherInfo l_voucherInfo = 
            new WEB3AccOpenVoucherInfo();
        if (l_accOpenVoucherCreatedStatuses == null || l_accOpenVoucherCreatedStatuses.length == 0)
        {
             return l_voucherInfo;
        } 
        
        //�Q�j�@@�v���p�e�B�Z�b�g
        
        ArrayList l_g11List = new ArrayList();
        ArrayList l_g1151List = new ArrayList();
        ArrayList l_g26List = new ArrayList();
        ArrayList l_ga300List = new ArrayList();
        ArrayList l_g5401List = new ArrayList();
        ArrayList l_g1601List = new ArrayList();
        ArrayList l_g5511List = new ArrayList();
        ArrayList l_g1159List = new ArrayList();
        ArrayList l_g1175List = new ArrayList();
        ArrayList l_g1311List = new ArrayList();
        ArrayList l_g9801List = new ArrayList();
        ArrayList l_g1220List = new ArrayList();
        ArrayList l_g8610List = new ArrayList();
        ArrayList l_g1190List = new ArrayList();
        ArrayList l_g43List = new ArrayList();
        ArrayList l_gs103List = new ArrayList();
        
        int l_intCnt = l_accOpenVoucherCreatedStatuses.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenVoucherCreatedStatus l_voucherCreatedStatuse = 
                l_accOpenVoucherCreatedStatuses[i];
            
            if (l_voucherCreatedStatuse == null)
            {
                continue;
            }
        
            AccOpenVoucherStatusParams l_params = (AccOpenVoucherStatusParams)
                l_voucherCreatedStatuse.getDataSourceObject();
        
            String l_strRequestCode = l_params.getRequestCode();
        
            //G11�ڋq�o�^
            //�ڋq�o�^�i����Ɓj�`�[
            if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST.equals(l_strRequestCode) ||
                WEB3HostRequestCodeDef.ACCOPEN_REG_BROKERAGE.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g11List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g11List.add(l_params.getVoucherStatus());
                }
             }
            //G1151�_�񏑒��� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1151List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1151List.add(l_params.getVoucherStatus());
                }
            }
            //G26�U�֐\��
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK.equals(l_strRequestCode) || WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g26List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g26List.add(l_params.getVoucherStatus());
                }
            }
            //GA300�ېU����
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_ga300List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_ga300List.add(l_params.getVoucherStatus());
                }
            }
            //G5401�L�����
            else if (WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g5401List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g5401List.add(l_params.getVoucherStatus());
                }
            }
            //GI601MRF����  
            else if (WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1601List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1601List.add(l_params.getVoucherStatus());
                }
            }
            //G5511�Ïؔԍ�
            else if (WEB3HostRequestCodeDef.ACCOPEN_PASSWORD.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g5511List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g5511List.add(l_params.getVoucherStatus());
                }
            }
            //G1159�d�v���� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_IMPORTANT_ITEM.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1159List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1159List.add(l_params.getVoucherStatus());
                }
            }
            //G1175�{�l�m�F
            else if (WEB3HostRequestCodeDef.ACCOPEN_ID_CONFIRM.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1175List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1175List.add(l_params.getVoucherStatus());
                }
            } 
            //�`�[�쐬���.GI311��c�E�d�q��t�E������� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRADE_CONDITION.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1311List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1311List.add(l_params.getVoucherStatus());
                }
            }
            //�`�[�쐬���.G9801������ 
            else if (WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g9801List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g9801List.add(l_params.getVoucherStatus());
                }
            }
            //�`�[�쐬���.G1220GP���� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_GP_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1220List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1220List.add(l_params.getVoucherStatus());
                }
            }
            //�`�[�쐬���.G8610���O���E���� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g8610List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g8610List.add(l_params.getVoucherStatus());
                }
            }
            //�`�[�쐬���.G1190�ڋq�������� 
            else if (WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_VOUCHER.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g1190List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g1190List.add(l_params.getVoucherStatus());
                }
            }
            //�`�[�쐬���.G43�O�ݗa�������o�^
            else if (WEB3HostRequestCodeDef.F_DEPOSIT_REG.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_g43List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_g43List.add(l_params.getVoucherStatus());
                }
            }
            //�`�[�쐬���.GS103�@@�\�ʒm
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REGIST.equals(l_strRequestCode))
            {
                if (l_params.getErrorCode() != null)
                {
                    l_gs103List.add(l_params.getVoucherStatus() + ":" + l_params.getErrorCode());
                }
                else
                {
                    l_gs103List.add(l_params.getVoucherStatus());
                }
            }
        }

        int l_intG11Cnt = l_g11List.size();
        String[] l_strG11s = null;
        if (l_intG11Cnt > 0)
        {
            l_strG11s = new String[l_intG11Cnt];
            l_g11List.toArray(l_strG11s);
            l_voucherInfo.accRegVoucherDiv = l_strG11s;
        }

        int l_intG1151Cnt = l_g1151List.size();
        String[] l_strG1151s = null;
        if (l_intG1151Cnt > 0)
        {
            l_strG1151s = new String[l_intG1151Cnt];
            l_g1151List.toArray(l_strG1151s);
            l_voucherInfo.contMrgVoucherDiv = l_strG1151s;
        }
        
        int l_intG26Cnt = l_g26List.size();
        String[] l_strG26s = null;
        if (l_intG26Cnt > 0)
        {
            l_strG26s = new String[l_intG26Cnt];
            l_g26List.toArray(l_strG26s);
            l_voucherInfo.transVoucherDiv = l_strG26s;
        }
        
        int l_intGa300Cnt = l_ga300List.size();
        String[] l_strGa300s = null;
        if (l_intGa300Cnt > 0)
        {
            l_strGa300s = new String[l_intGa300Cnt];
            l_ga300List.toArray(l_strGa300s);
            l_voucherInfo.agreeTransVoucherDiv = l_strGa300s;
        }
        
        int l_intG5401Cnt = l_g5401List.size();
        String[] l_strG5401s = null;
        if (l_intG5401Cnt > 0)
        {
            l_strG5401s = new String[l_intG5401Cnt];
            l_g5401List.toArray(l_strG5401s);
            l_voucherInfo.chargedInfoVoucherDiv = l_strG5401s;
        }
        
        int l_intG1601Cnt = l_g1601List.size();
        String[] l_strG1601s = null;
        if (l_intG1601Cnt > 0)
        {
            l_strG1601s = new String[l_intG1601Cnt];
            l_g1601List.toArray(l_strG1601s);
            l_voucherInfo.mrfAccVoucherDiv = l_strG1601s;
        }
        
        int l_intG5511Cnt = l_g5511List.size();        
        String[] l_strG5511s = null;
        if (l_intG5511Cnt > 0)
        {
            l_strG5511s = new String[l_intG5511Cnt];
            l_g5511List.toArray(l_strG5511s);
            l_voucherInfo.passwordVoucherDiv = l_strG5511s;
        }
        
        int l_intG1159Cnt = l_g1159List.size();
        String[] l_strG1159s = null;
        if (l_intG1159Cnt > 0)
        {
            l_strG1159s = new String[l_intG1159Cnt];
            l_g1159List.toArray(l_strG1159s);
            l_voucherInfo.impConfirmVoucherDiv = l_strG1159s;
        }
        
        int l_intG1175Cnt = l_g1175List.size();
        String[] l_strG1175s = null;
        if (l_intG1175Cnt > 0)
        {
            l_strG1175s = new String[l_intG1175Cnt];
            l_g1175List.toArray(l_strG1175s);
            l_voucherInfo.confirmVoucherDiv = l_strG1175s;
        }
        
        int l_intG1311Cnt = l_g1311List.size();
        String[] l_strG1311s = null;
        if (l_intG1311Cnt > 0)
        {
            l_strG1311s = new String[l_intG1311Cnt];
            l_g1311List.toArray(l_strG1311s);
            l_voucherInfo.tradeConditionVoucherDiv = l_strG1311s;
        }

        int l_intG9801Cnt = l_g9801List.size();
        String[] l_strG9801s = null;
        if (l_intG9801Cnt > 0)
        {
            l_strG9801s = new String[l_intG9801Cnt];
            l_g9801List.toArray(l_strG9801s);
            l_voucherInfo.insiderVoucherDiv = l_strG9801s;
        }
        
        int l_intG1220Cnt = l_g1220List.size();
        String[] l_strG1220s = null;
        if (l_intG1220Cnt > 0)
        {
            l_strG1220s = new String[l_intG1220Cnt];
            l_g1220List.toArray(l_strG1220s);
            l_voucherInfo.gpVoucherDiv = l_strG1220s;
        }
        
        int l_intG8610Cnt = l_g8610List.size();
        String[] l_strG8610s = null;
        if (l_intG8610Cnt > 0)
        {
            l_strG8610s = new String[l_intG8610Cnt];
            l_g8610List.toArray(l_strG8610s);
            l_voucherInfo.listedHolderVoucherDiv = l_strG8610s;
        }
        
        int l_intG1190Cnt = l_g1190List.size();
        String[] l_strG1190s = null;
        if (l_intG1190Cnt > 0)
        {
            l_strG1190s = new String[l_intG1190Cnt];
            l_g1190List.toArray(l_strG1190s);
            l_voucherInfo.accRealNameVoucherDiv = l_strG1190s;
        } 
        
        int l_intG43Cnt = l_g43List.size();
        String[] l_strG43s = null;
        if (l_intG43Cnt > 0)
        {
            l_strG43s = new String[l_intG43Cnt];
            l_g43List.toArray(l_strG43s);
            l_voucherInfo.foreignSaveInfoVoucherDiv = l_strG43s;
        }

        int l_intGs103Cnt = l_gs103List.size();
        String[] l_strGs103s = null;
        if (l_intGs103Cnt > 0)
        {
            l_strGs103s = new String[l_intGs103Cnt];
            l_gs103List.toArray(l_strGs103s);
            l_voucherInfo.agencyVoucherDiv = l_strGs103s;
        }
        log.exiting(STR_METHOD_NAME);
        return l_voucherInfo;
    }
    
    /**
     * (to�s�����ڏ��)<BR>
     * �����J�ݕs���I�u�W�F�N�g���A�s�����ڏ��̔z��𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�����ڏ��List�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�s�����ڐ����i10�� Loop�j�A�s�����ڏ��𐶐�����B<BR>
     * <BR>
     * �@@�� �Ώ�index�̕s�����ڂɓo�^������ꍇ<BR>
     * �i�����J�ݕs��.is�s�����ړo�^(index) == true�j�̂�<BR>
     * �@@�@@�@@�Q�|�P�j�`�Q�|�R�j�����{����B�ȊOcontinue;<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�s�����ڏ�񐶐�<BR>
     * �@@�@@�s�����ڏ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�v���p�e�B�Z�b�g<BR>
     * �@@�@@�s�����ڏ��Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�s�����ڏ��.�s�����ږ� = �����J�ݕs��.get�s�����ږ�(index)<BR>
     * �@@�@@�s�����ڏ��.�R�����g = �����J�ݕs��.get�R�����g(index)<BR>
     * �@@�@@�s�����ڏ��.�����t���O = �����J�ݕs��.is����(index)<BR>
     * <BR>�@@
     * �@@�Q�|�R�j�@@�s�����ڏ��List�i�FArrayList�j�ɗv�f��ǉ�����B<BR>
     * �@@�@@�s�����ڏ��List�i�FArrayList�j.add()�ɂāA���������s�����ڏ���<BR>
     * �ǉ�����B<BR>
     * <BR>
     * �R�j�@@�l�ԋp<BR>
     * �@@�s�����ڏ��List�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_accOpenInvalidValues - �����J�ݕs���I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo[]
     * @@roseuid 4191B3EF0275
     */
    public WEB3AccOpenInvalidItemInfo[] toAccOpenInvalidItemInfo(WEB3AccOpenInvalidValues l_accOpenInvalidValues) 
    {
        final String STR_METHOD_NAME = " toAccOpenInvalidItemInfo(WEB3AccOpenInvalidValues) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenInvalidValues == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@�s�����ڏ��List�i�FArrayList�j����
        ArrayList l_list = new ArrayList();
        
        //�Q�j�@@�s�����ڐ����i10�� Loop�j�A�s�����ڏ��𐶐�����B
        for (int i = 1; i < 11; i++)
        {
            //�Ώ�index�̕s�����ڂɓo�^������ꍇ<BR>
            //�i�����J�ݕs��.is�s�����ړo�^(index) == true�j�̂�
            //�Q�|�P�j�`�Q�|�R�j�����{����B�ȊOcontinue;
            if (l_accOpenInvalidValues.isInvalidItemRegist(i))
            {
                //�Q�|�P�j�@@�s�����ڏ�񐶐� 
                WEB3AccOpenInvalidItemInfo l_invaildItemInfo = 
                    new WEB3AccOpenInvalidItemInfo();
                    
                //�Q�|�Q�j�@@�v���p�e�B�Z�b�g 
                //�s�����ڏ��.�s�����ږ� = �����J�ݕs��.get�s�����ږ�(index)
                l_invaildItemInfo.invalidItemName = l_accOpenInvalidValues.getInvalidItemName(i);
                
                //�s�����ڏ��.�R�����g = �����J�ݕs��.get�R�����g(index) 
                l_invaildItemInfo.comment = l_accOpenInvalidValues.getComment(i);
                
                //�s�����ڏ��.�����t���O = �����J�ݕs��.is����(index)
                l_invaildItemInfo.compFlag = l_accOpenInvalidValues.isComplete(i);
                 
                //�Q�|�R�j�@@�s�����ڏ��List�i�FArrayList�j�ɗv�f��ǉ�����B 
                l_list.add(l_invaildItemInfo);
            }
        }

        //�R�j�@@�l�ԋp
        int l_intCnt = l_list.size();
        WEB3AccOpenInvalidItemInfo[] l_invalidItemInfos = 
            new  WEB3AccOpenInvalidItemInfo[l_intCnt];
        
        l_list.toArray(l_invalidItemInfos);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_invalidItemInfos;
    }
    
    /**
     * (to�����J�ݕs��)<BR>
     * �s�����ڏ��̔z����A�����J�݃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�����J�ݕs������<BR>
     * �@@�����J�ݕs���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h�F�@@���ʃR�[�h<BR>
     * <BR>
     * �@@�����ł��Ȃ������ꍇ�A�f�t�H���g�R���X�g���N�^�ɂăI�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�s�����ڐ����i10�� Loop�j�A�s�����ڂ��Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�s�����ڃZ�b�g<BR>
     * �@@�@@�����J�ݕs��.set�s������()�ɂāA�s�����ڂ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�� �s�����ڏ��̗v�f������ꍇ�iindex < �s�����ڏ��.length�j<BR>
     * <BR>
     * �@@�@@�@@[set�s������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@index�F�@@index<BR>
     * �@@�@@�@@�s�����ږ��F�@@�s�����ڏ��[index].�s�����ږ�<BR>
     * �@@�@@�@@�R�����g�F�@@�s�����ڏ��[index].�R�����g<BR>
     * �@@�@@�@@is�����F�@@�s�����ڏ��[index].�����t���O<BR>
     * <BR>
     * �@@�@@�� �s�����ڏ��̗v�f���Ȃ��ꍇ�iindex >= �s�����ڏ��.length�j<BR>
     * <BR>
     * �@@�@@�@@[set�s������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@index�F�@@index<BR>
     * �@@�@@�@@�s�����ږ��F�@@null<BR>
     * �@@�@@�@@�R�����g�F�@@null<BR>
     * �@@�@@�@@is�����F�@@false<BR>
     * <BR>
     * �R�j�@@�l�ԋp<BR>
     * �@@�����J�ݕs���I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_accOpenInvalidItemInfo - �s�����ڏ�񃁃b�Z�[�W�f�[�^
     * 
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@roseuid 41944A2F0198
     */
    public WEB3AccOpenInvalidValues toAccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber, WEB3AccOpenInvalidItemInfo[] l_accOpenInvalidItemInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " toAccOpenInvalidValues(String, String, WEB3AccOpenInvalidItemInfo[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenInvalidItemInfo == null)
        {
            l_accOpenInvalidItemInfo = new WEB3AccOpenInvalidItemInfo[0];
        }
        
        WEB3AccOpenInvalidValues l_invalidValues = null;
        
        try
        {
            //�P�j�@@�����J�ݕs������ 
            l_invalidValues = new WEB3AccOpenInvalidValues
                (l_strInstitutionCode, l_strRequestNumber);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            //�����ł��Ȃ������ꍇ�A�f�t�H���g�R���X�g���N�^�ɂăI�u�W�F�N�g�𐶐�����B
            AccOpenInvalidValuesParams l_params = 
                new AccOpenInvalidValuesParams();
            l_params.setInstitutionCode(l_strInstitutionCode);
            l_params.setAccOpenRequestNumber(l_strRequestNumber);
            l_invalidValues = new WEB3AccOpenInvalidValues(l_params);            
        }
        
        int l_intCnt = l_accOpenInvalidItemInfo.length;
        
        //�Q�j�@@�s�����ڐ����i10�� Loop�j�A�s�����ڂ��Z�b�g����B
        for (int i = 0; i < 10; i++)
        {
            //�Q�|�P�j�@@�s�����ڃZ�b�g 
            //�����J�ݕs��.set�s������()�ɂāA�s�����ڂ��Z�b�g����B
            //�s�����ڏ��̗v�f������ꍇ�iindex < �s�����ڏ��.length�j
            if (i < l_intCnt && l_accOpenInvalidItemInfo[i] != null)
            {
                //index�Findex
                //�s�����ږ��F�@@�s�����ڏ��[index].�s�����ږ� 
                //�R�����g�F�@@�s�����ڏ��[index].�R�����g 
                //is�����F�@@�s�����ڏ��[index].�����t���O
                WEB3AccOpenInvalidItemInfo l_invalidItemInfo = 
                    l_accOpenInvalidItemInfo[i];
                l_invalidValues.setInvalidItem(
                    i+1, 
                    l_invalidItemInfo.invalidItemName,
                    l_invalidItemInfo.comment,
                    l_invalidItemInfo.compFlag);                 
            }
            //�s�����ڏ��̗v�f���Ȃ��ꍇ�iindex >= �s�����ڏ��.length�j 
            else
            {
                //index�F�@@index         
                //�s�����ږ��F�@@null 
                //�R�����g�F�@@null 
                //is�����F�@@false
                l_invalidValues.setInvalidItem(i+1, 
                    null,
                    null,
                    false);  
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_invalidValues;
    }
    
    /**
     * (to�񕨗���)<BR>
     * ���b�Z�[�W�I�u�W�F�N�g�̍��ږ����A�Ή���������J�݌����q�e�[�u����<BR>
     * �񕨗����ɕϊ�����B<BR>
     * <BR>
     * ��P�j�@@�����ŁA"addressKana1"���w�肳�ꂽ�ꍇ�A<BR>
     * "address_line1_kana"(*1)��ԋp����B<BR>
     * ��Q�j�@@�����ŁA"houseHolderOfficeInfo.occupationDiv"(*2) ��<BR>
     * �w�肳�ꂽ�ꍇ�A"householder_occupation_div"��ԋp����B<BR>
     * <BR>
     * (*1) �Z���P�i�J�i�j�@@<BR>
     * (*2) ���ю�Ζ�����.�E�Ƌ敪�@@<BR>
     * @@param l_strMessageItemSymbolName - ���b�Z�[�W���ڕ�����<BR>
     * <BR>
     * ���@@�����J�ݐ\�����̊e�v���p�e�B������<BR>
     * ���@@Unit�N���X�̕ϐ��́A���ю�Ζ�����.��E���̂悤�ɁA<BR>
     * �u�ϐ���.���ږ��v�̌`���Ŏw�肷��B<BR>
     * 
     * 
     * @@return String
     * @@roseuid 419348B900B2
     */
    public String toColumnSymbolName(String l_strMessageItemSymbolName) 
    {
        final String STR_METHOD_NAME = " toColumnSymbolName(String) ";
        log.entering(STR_METHOD_NAME);
         
        String l_strColSymName = "";
        
        //����������
        if (WEB3AccOpenApplyInfoItemDef.INFO_CLAIM_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INFOMATION_CLAIM_DATETIME;
        }
        //�X�V��
        else if (WEB3AccOpenApplyInfoItemDef.UPDATE_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATED_TIMESTAMP;
        }
        //�X�V�҃R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.UPDATER_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATER;
        }
        //���ʃR�[�h
        else if (WEB3AccOpenApplyInfoItemDef.REQUEST_NUMBER.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
        }
        //���͋敪
        else if (WEB3AccOpenApplyInfoItemDef.INPUT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ORDER_DIV;
        }
        //�쐬�҃R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.CREATOR_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.CREATOR;
        }
        //���������t���O
        else if (WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_FLAG;
        }
        //���������R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_CODE;
        }
        //�����������X��
        else if (WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_BRANCH_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_BRANCH_NAME;
        }
        //�،���ЃR�[�h
        else if (WEB3AccOpenApplyInfoItemDef.INSTITUTION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE;
        }
        //���X�R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.BRANCH_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
        }
        //�����敪
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_DIV;
        }
        //�ڋq�R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
        }
        //���҃R�[�h�iSONAR�j
        else if (WEB3AccOpenApplyInfoItemDef.TRADER_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE;
        }
        //���[���A�h���X
        else if (WEB3AccOpenApplyInfoItemDef.MAIL_ADDRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS;
        }
        //���[���A�h���X�i�g�сj
        else if (WEB3AccOpenApplyInfoItemDef.MOBILE_MAIL_ADDRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS_ALT1;
        }
        //�ڋq���i�����j
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME;
        }
        //�ڋq���i�����j
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME;
        }
        //�ڋq���i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1;
        }
        //�ڋq���i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1;
        }
        //���N�����N��
        else if (WEB3AccOpenApplyInfoItemDef.ERA_BORN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ERA_BORN;
        }
        //���N����
        else if (WEB3AccOpenApplyInfoItemDef.BORN_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.BORN_DATE;
        }
        //����
        else if (WEB3AccOpenApplyInfoItemDef.SEX.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEX;
        }
        //�Ïؔԍ�
        else if (WEB3AccOpenApplyInfoItemDef.PASSWORD.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INITIAL_PASSWORD;
        }
        //�X�֔ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.ZIP_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE;
        }
        //�Z��1�i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1_KANA;
        }
        //�Z��2�i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2_KANA;
        }
        //�Z��3�i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3_KANA;
        }
        //�Z��1�i�����j
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1;
        }
        //�Z��2�i�����j
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2;
        }
        //�Z��3�i�����j
        else if (WEB3AccOpenApplyInfoItemDef.ADDRESS3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3;
        }
        //�d�b�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.TELEPHONE;
        }
        //�g�єԍ�
        else if (WEB3AccOpenApplyInfoItemDef.MOBILE_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.MOBILE;
        }
        //�e�`�w�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.FAX_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAX;
        }
        //�Ζ�����.�E�Ƌ敪
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OCCUPATION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OCCUPATION_DIV;
        }
        //�Ζ�����.�Ζ��於��
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE;
        }
        //�Ζ�����.�Ζ���X�֔ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ZIP_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ZIP_CODE;
        }
        //�Ζ�����.�Ζ���Z��
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ADRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ADDRESS;
        }
        //�Ζ�����.�Ζ���d�b�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_TELEPHONE;
        }
        //�Ζ�����.�Ζ���e�`�w�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_FAX_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_FAX;
        }
        //�Ζ�����.��������
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_DEPARTMENT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.DEPARTMENT;
        }
        //�Ζ�����.��E��
        else if (WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_POSITION.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.POST;
        }
        //�A����Z��
        else if (WEB3AccOpenApplyInfoItemDef.CONTACT_ADDRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_ADDRESS;
        }
        //�A����d�b�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.CONTACT_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_TELEPHONE;
        }
        //�����敪
        else if (WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP;
        }
        //�����i���̑��j
        else if (WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_ETC.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP_ETC;
        }
        //���ю喼�i�����j
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER;
        }
        //���ю喼�i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_KANA;
        }
        //���ю�Ζ�����.�E�Ƌ敪
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OCCUPATION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OCCUPATION_DIV;
        }
        //���ю�Ζ�����.�Ζ��於��
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE;
        }
        //���ю�Ζ�����.�Ζ���Z��
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_ADRESS.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_ADDRESS;
        }
        //���ю�Ζ�����.�Ζ���d�b�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_TEL;
        }
        //���ю�Ζ�����.�Ζ���e�`�w�ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_FAX_TELEPHONE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_FAX;
        }
        //���ю�Ζ�����.��������
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_DEPARTMENT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_DEPARTMENT;
        }
        //���ю�Ζ�����.��E��
        else if (WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_POSITION.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_POST;
        }
        //���Z�^�񋏏Z�敪
        else if (WEB3AccOpenApplyInfoItemDef.RESIDENT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.RESIDENT;
        }
        //�U�֋敪
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANSFER_DIV;
        }
        //�������`�l
        else if (WEB3AccOpenApplyInfoItemDef.FINANCIAL_ACCOUNT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NAME;
        }
        //�����ړI�敪
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_PURPOSE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INVEST_PURPOSE_DIV;
        }
        //������@@�敪
        else if (WEB3AccOpenApplyInfoItemDef.APPLI_MOTIVAT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.APPLI_MOTIVAT_DIV;
        }
        //�N���敪
        else if (WEB3AccOpenApplyInfoItemDef.ANNUAL_INCOME_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_DIV;
        }
        //���Z���Y�敪
        else if (WEB3AccOpenApplyInfoItemDef.ASSET_VALUE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_DIV;
        }
        //�^�p�\��z�敪
        else if (WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_AMOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_AMOUNT_DIV;
        }
        //���Y�̐��i�敪
        else if (WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_DIV;
        }
        //���Y�̐��i�i���̑��j
        else if (WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_ETC.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_ETC;
        }
        //�����p�敪
        else if (WEB3AccOpenApplyInfoItemDef.SEARCH_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_FLAG;
        }
        //�{�l�m�F���ދ敪
        else if (WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_DIV;
        }
        //�{�l�m�F���ށi���̑��j
        else if (WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_ETC.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_ETC;
        }
        //�������������敪
        else if (WEB3AccOpenApplyInfoItemDef.EQUITY_TAX_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC;
        }
        //�M�p��������敪
        else if (WEB3AccOpenApplyInfoItemDef.MARGIN_TAX_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC_MARGIN;
        }
        //�����ғo�^�t���O
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_FLAG;
        }
        //�����Җ�����
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_PRODUCT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.PRODUCT_NAME;
        }
        //���t��X�֔ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ZIP_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ZIP_CODE;
        }
        //���t��Z���P
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE1;
        }
        //���t��Z���Q
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE2;
        }
        //���t��Z���R
        else if (WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE3;
        }
        //�敪�P
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV1;
        }
        //�敪�Q
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV2;
        }
        //�敪�R
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV3;
        }
        //�敪�S
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV4.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV4;
        }
        //�敪�T
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV5.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV5;
        }
        //�敪�U
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV6.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV6;
        }
        //�敪�V
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV7.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV7;
        }
        //�敪�W
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV8.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV8;
        }
        //�敪�X
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV9.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV9;
        }
        //�敪�P�O
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV10.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV10;
        }
        //�t���O�P
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG1;
        }
        //�t���O�Q
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG2;
        }
        //�t���O�R
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG3;
        }
        //�t���O�S
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG4.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG4;
        }
        //�t���O�T
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG5.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG5;
        }
        //�t���O�U
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG6.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG6;
        }
        //�t���O�V
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG7.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG7;
        }
        //�t���O�W
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG8.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG8;
        }
        //�t���O�X
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG9.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG9;
        }
        //�t���O�P�O
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG10.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG10;
        }
        //�e�L�X�g�P
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT1;
        }
        //�e�L�X�g�Q
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT2;
        }
        //�e�L�X�g�R
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT3.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT3;
        }
        //�e�L�X�g�S
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT4.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT4;
        }
        //�e�L�X�g�T
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT5.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT5;
        }
        //�e�L�X�g�U
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT6.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT6;
        }
        //�e�L�X�g�V
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT7.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT7;
        }
        //�e�L�X�g�W
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT8.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT8;
        }
        //�e�L�X�g�X
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT9.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT9;
        }
        //�e�L�X�g�P�O
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT10.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT10;
        }
        //�U�����s���.��s�R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_CODE;
        }
        //�U�����s���.��s��
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_NAME;
        }
        //�U�����s���.�x�X�R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_CODE;
        }
        //�U�����s���.�x�X��
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_NAME;
        }
        //�U�����s���.�a���敪
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_SAVE_DIV;
        }
        //�U�����s���.�����ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NO;
        }
        //�U�����s���.�U�֎萔���敪
        else if (WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_TRANSFER_COMMISSION_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANS_COMMISSION;
        }
        //�X�֐U�֏��.�ʒ��ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_NO;
        }
        //�X�֐U�֏��.�ʒ��L��
        else if (WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_SIGN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_CODE;
        }
        //�����o�����.��������
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_EQUITY_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_EQUITY;
        }
        //�����o�����.�M�p���
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_MARGIN_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_MARGIN;
        }
        //�����o�����.��
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_BOND_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_BOND;
        }
        //�����o�����.�]���Ѝ�
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_WT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_WT;
        }
        //�����o�����.�����M���i�����j
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_SK_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_SK;
        }
        //�����o�����.�����M���i���j
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_BD_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_BD;
        }
        //�����o�����.�敨�E�I�v�V����
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FO_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FO;
        }
        //�����o�����.�O���،�
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_F_EQUITY_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_F_EQUITY;
        }
        //�����o�����.���̑�
        else if (WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_ETC_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_ETC;
        }
        //�����̂��������.���������t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_EQUITY_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_EQUITY;
        }
        //�����̂��������.�����~�j�����t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MSTK_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MINISTOCK;
        }
        //�����̂��������.�M�p����t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MARGIN_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MARGIN;
        }
        //�����̂��������.���t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_BOND_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_BOND;
        }
        //�����̂��������.�����M���t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FUND_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FUND;
        }
        //�����̂��������.�敨�E�I�v�V�����t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FO_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FO;
        }
        //�����̂��������.�O���،��t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_F_EQUITY_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_F_EQUITY;
        }
        //�����̂��������.���̑��t���O
        else if (WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_ETC_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_ETC;
        }
        //�ڋq�������̍쐬�敪
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME_VOUCHER_DIV;
        }
        //�ڋq�������̂P
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME1;
        }
        //�ڋq�������̂Q
        else if (WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME2;
        }
        //����ƈ��҃R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.BROKERAGE_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.BROKERAGE_TRADER_CODE;
        }
        //�e�Њg�����ځi�敪1�P�j
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV11.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV11;
        }
        //�e�Њg�����ځi�敪�P2�j
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV12.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV12;
        }
        //�e�Њg�����ځi�敪�P3�j
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV13.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV13;
        }
        //�e�Њg�����ځi�敪�P4�j
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV14.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV14;
        }
        //�e�Њg�����ځi�敪�P5�j
        else if (WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV15.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV15;
        }
        //�i�����ҁj�쐬�敪
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_CREATE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_VOUCHER_DIV;
        }
        //�i�����ҁj�����R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_PRODUCT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_PRODUCT_CODE;
        }
        //�i�����ҁj�֌W�敪
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_RELATION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_RELATION_DIV;
        }
        //�i�����ҁj������
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_EXECUTIVE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_OFFICER_NAME;
        }
        //�i�����ҁj��E���R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_CODE;
        }
        //�i�����ҁj��E��
        else if (WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_NAME;
        }
        //�i�f�o�j�쐬�敪
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_CREATE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_VOUCHER_DIV;
        }
        //�i�f�o�j�R�[�X
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_COURSE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_COURSE;
        }
        //�i�f�o�j�v����
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_PLAN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PLAN;
        }
        //�i�f�o�j�ڕW�z
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_FIGURE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_FIGURE;
        }
        //�i�f�o�j�ڕW�N
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_YEAR.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_YEAR;
        }
        //�i�f�o�j�ڕW��
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_MONTH.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_MONTH;
        }
        //�i�f�o�j�ϗ��z
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_INSTALLMENT_FIGURE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_INSTALLMENT_FIGURE;
        }
        //�i�f�o�j��������
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_DEPOSIT_CYCLE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_DEPOSIT_CYCLE;
        }
        //�i�f�o�j��n�o�H
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_PAYMENT_ROOT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PAYMENT_ROOT;
        }
        //�i�f�o�j�ē����敪
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_REINVEST_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_REINVEST_DIV;
        }
        //�i�f�o�j�ŋ敪
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TAX_TYPE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAX_DIV;
        }
        //�i�f�o�j�i�D�j���x�z
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_TAXFREE_LIMIT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAXFREE_LIMIT;
        }
        //�i�f�o�j�i���D�j���x�z
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_SPECIAL_TAXFREE_LIMIT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SPECIAL_TAXFREE_LIMIT;
        }
        //�i�f�o�j�����E�v
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_SUBSCR_SUMMARY.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SUBSCR_SUMMARY;
        }
        //�i�f�o�j�����R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_PRODUCT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PRODUCT_CODE;
        }
        //�i�f�o�j�S�ۋq
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_MORTGAGE_CUSTOMER.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MORTGAGE_CUSTOMER;
        }
        //�i�f�o�j�~�b�N�X�q
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_MIX_CUSTOMER.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MIX_CUSTOMER;
        }
        //�i�f�o�j�_��
        else if (WEB3AccOpenApplyInfoItemDef.GP_INFO_CONTRACT.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_CONTRACT;
        }
        //���O���j�쐬�敪
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_CREATE_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_VOUCHER_DIV;
        }
        //�i���O���j���n
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TAXATION_TRAN.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TAXATION_TRAN_DIV;
        }
        //�i���O���j�Z���i�J�i�j
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_ADDRESS_KANA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_ADDRESS_LINE_KANA;
        }
        //�i���O���j����
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TRANSFER_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TRANSFER_DIV;
        }
        //�i���O���j��s�R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_INSTITUTION_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_INSTITUTION_CODE;
        }
        //�i���O���j�x�X�R�[�h
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_BRANCH_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_BRANCH_CODE;
        }
        //�i���O���j�a���敪
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_SAVE_DIV;
        }
        //�i���O���j�����ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_ACCOUNT_NO;
        }
        //(�O�ݗa���������)�����ԍ�
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_CODE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NO;
        }
        //(�O�ݗa���������)�������`�l
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME;
        }
        //(�O�ݗa���������)�������`�l�p��
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME_ALPHA.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME_ENG;
        }
        //(�O�ݗa���������)�a���敪
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_DIV.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_SAVE_DIV;
        }
        // �폜�t���O
        else if (WEB3AccOpenApplyInfoItemDef.DELETE_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_FLAG;
        }
        //�폜����
        else if (WEB3AccOpenApplyInfoItemDef.DELETE_DATE.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_TIMESTAMP;
        }
        //����t���O
        else if (WEB3AccOpenApplyInfoItemDef.PRINT_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.PRINT_FLAG;
        }
        //��̃t���O
        else if (WEB3AccOpenApplyInfoItemDef.RECEIVE_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.RECEIPT_FLAG;
        }
        //�����t���O
        else if (WEB3AccOpenApplyInfoItemDef.APPROVE_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGREEMENT_FLAG;
        }
        //�O���l�t���O
        else if (WEB3AccOpenApplyInfoItemDef.FOREIGNER_FLAG.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_FLAG;
        }
        //�@@�\�ʒm���
        //�t���K�i1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA1;
        }
        //�t���K�i2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA2;
        }
        //����1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME1;
        }
        //����2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME2;
        }
        //�Z��1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE1;
        }
        //�Z��2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE2;
        }
        //��\�҂̖�E
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_POST.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_POST;
        }
        //��\�҂̃t���K�i1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA1;
        }
        //��\�҂̃t���K�i2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA2;
        }
        //��\�҂̎���1
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME1.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME1;
        }
        //��\�҂̎���2
        else if (WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME2.equals(l_strMessageItemSymbolName))
        {
            l_strColSymName = WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME2;
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_strColSymName;
    }
    
    /**
     * (to���b�Z�[�W���ږ�)<BR>
     * �����J�݌����q�e�[�u���̗񕨗�����Ή����郁�b�Z�[�W�I�u�W�F�N�g��<BR>
     * ���ږ��ɕϊ�����B<BR>
     * <BR>
     * ��P�j�@@�����ŁA"address_line1_kana"���w�肳�ꂽ�ꍇ�A<BR>
     * "addressKana1"(*1)��ԋp����B<BR>
     * ��Q�j�@@�����ŁA"householder_occupation_div"���w�肳�ꂽ�ꍇ�A<BR>
     * "houseHolderOfficeInfo.occupationDiv"(*2) ��ԋp����B<BR>
     * <BR>
     * (*1) �Z���P�i�J�i�j�@@<BR>
     * (*2) ���ю�Ζ�����.�E�Ƌ敪<BR>
     * @@param l_strColumnSymbolName - �񕨗���<BR>
     * <BR>
     * ���@@�����J�݌����q�e�[�u���̗񕨗���<BR>
     * 
     * 
     * @@return String
     * @@roseuid 419C593C033F
     */
    public String toMessageItemName(String l_strColumnSymbolName) 
    {
        final String STR_METHOD_NAME = " toMessageItemName(String) ";
        log.entering(STR_METHOD_NAME);
         
        String l_strMessageItemName = "";

        //�،���ЃR�[�h
        if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSTITUTION_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSTITUTION_CODE;
        }
        //���X�R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.BRANCH_CODE;
        }
        //���ʃR�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.REQUEST_NUMBER;
        }
        //�����R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_CODE;
        }
        //���҃R�[�h�iSONAR�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SONAR_TRADER_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRADER_CODE;
        }
        //���������t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_FLAG;
        }
        //�����������X��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_BRANCH_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_BRANCH_NAME;
        }
        //���������R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EX_ACCOUNT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EX_ACCOUNT_CODE;
        }
        //�����敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_TYPE;
        }
        //���͋敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ORDER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INPUT_DIV;
        }
        //������������
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INFOMATION_CLAIM_DATETIME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INFO_CLAIM_DATE;
        }
        //�����p�X���[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INITIAL_PASSWORD.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.PASSWORD;
        }
        //�ڋq���i�����j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME;
        }
        //�ڋq���i�����j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME;
        }
        //�ڋq���i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_NAME_ALT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_FAMILY_NAME_KANA;
        }
        //�ڋq���i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GIVEN_NAME_ALT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_NAME_KANA;
        }
        //����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEX;
        }
        //���N�����N��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ERA_BORN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ERA_BORN;
        }
        //���N����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.BORN_DATE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.BORN_DATE;
        }
        //email�A�h���X
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MAIL_ADDRESS;
        }
        //email�A�h���X�P
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EMAIL_ADDRESS_ALT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MOBILE_MAIL_ADDRESS;
        }
        //�X�֔ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ZIP_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ZIP_CODE;
        }
        //�Z���P
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS1;
        }
        //�Z���Q
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS2;
        }
        //�Z���R
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS3;
        }
        //�Z���P�i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE1_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA1;
        }
        //�Z���Q�i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE2_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA2;
        }
        //�Z���R�i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ADDRESS_LINE3_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ADDRESS_KANA3;
        }
        //�d�b�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.TELEPHONE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TELEPHONE;
        }
        //�A����d�b�ԍ��i�g�сj
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.MOBILE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MOBILE_TELEPHONE;
        }
        //�e�`�w�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FAX_TELEPHONE;
        }
        //�E�Ƌ敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OCCUPATION_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OCCUPATION_DIV;
        }
        //�Ζ��於��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_NAME;
        }
        //�Ζ���X�֔ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ZIP_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ZIP_CODE;
        }
        //�Ζ���Z��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_ADRESS;
        }
        //�Ζ���d�b�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_TELEPHONE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_TELEPHONE;
        }
        //�Ζ���FAX�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.OFFICE_FAX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_OFFICE_FAX_TELEPHONE;
        }
        //��������
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.DEPARTMENT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_DEPARTMENT;
        }
        //��E
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.POST.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.OFFICE_INFO_POSITION;
        }
        //�A����Z��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.CONTACT_ADDRESS;
        }
        //�A����d�b�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.CONTACT_TELEPHONE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.CONTACT_TELEPHONE;
        }
        //�����敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_DIV;
        }
        //�����敪�i���̑��j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FAMILY_RELATIONSHIP_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FAMILY_RELATION_ETC;
        }
        //���ю喼�i�����j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME;
        }
        //���ю喼�i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_NAME_KANA;
        }
        //���ю�E�Ƌ敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OCCUPATION_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OCCUPATION_DIV;
        }
        //���ю�Ζ���
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_NAME;
        }
        //���ю�Ζ���Z��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_ADDRESS.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_ADRESS;
        }
        //���ю及������
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_DEPARTMENT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_DEPARTMENT;
        }
        //���ю�Ζ���d�b�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_TEL.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_TELEPHONE;
        }
        //���ю�Ζ���FAX�ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_OFFICE_FAX.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_OFFICE_FAX_TELEPHONE;
        }
        //���ю��E
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.HOUSEHOLDER_POST.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.HOUSE_HOLDER_OFFICE_INFO_POSITION;
        }
        //���Z�^�񋏏Z�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.RESIDENT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.RESIDENT_DIV;
        }
        //�U�֋敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANSFER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_DIV;
        }
        //��s�R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_CODE;
        }
        //��s��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_INSTITUTION_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_NAME;
        }
        //�x�X�R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_CODE;
        }
        //�x�X��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_BRANCH_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_BRANCH_NAME;
        }
        //�a���敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_SAVE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_DIV;
        }
        //�����ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_CODE;
        }
        //�ʒ��L��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_SIGN;
        }
        //�ʒ��ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.POSTAL_SAVE_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.POSTAL_TRANSFER_INFO_PASSBOOK_CODE;
        }
        //�������`�l
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FIN_ACCOUNT_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FINANCIAL_ACCOUNT_NAME;
        }
        //�U�֎萔���敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.TRANS_COMMISSION.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.TRANSFER_BANK_INFO_TRANSFER_COMMISSION_DIV;
        }
        //��������
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_EQUITY.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_EQUITY.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FROM.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_TO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_EQUITY_DIV;
        }
        //�M�p���
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_MARGIN.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_MARGIN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_MARGIN_DIV;
        }
        //��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_BOND.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_BOND.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_BOND_DIV;
        }
        //�]���Ѝ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_WT.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_WT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_WT_DIV;
        }
        //�����M���i�����j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_SK.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FUND_SK.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_SK_DIV;
        }
        //�����M���i���Ѝj
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FUND_BD.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FUND_BD.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FUND_BD_DIV;
        }
        //�敨�E�I�v�V����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_FO.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_FO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_FO_DIV;
        }
        //�O���،�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_F_EQUITY.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_F_EQUITY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_F_EQUITY_DIV;
        }
        //���̑�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_DIV_ETC.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.EXPERIENCE_FLAG_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_EXP_INFO_EXPERIENCE_ETC_DIV;
        }
        //���������t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_EQUITY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_EQUITY_FLAG;
        }
        //�����~�j�����t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MINISTOCK.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MSTK_FLAG;
        }
        //�M�p����t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_MARGIN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_MARGIN_FLAG;
        }
        //���t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_BOND.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_BOND_FLAG;
        }
        //�����M���t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FUND.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FUND_FLAG;
        }
        //�敨�E�I�v�V�����t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_FO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_FO_FLAG;
        }
        //�O���،��t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_F_EQUITY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_F_EQUITY_FLAG;
        }
        //���̑��t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INTEREST_FLAG_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INTEREST_DEAL_INFO_INTEREST_ETC_FLAG;
        }
        //�����ړI�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INVEST_PURPOSE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INVEST_PURPOSE_DIV;
        }
        //������@@�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.APPLI_MOTIVAT_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.APPLI_MOTIVAT_DIV;
        }
        //�N���敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_DIV.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_FROM.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ANNUAL_INCOME_TO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ANNUAL_INCOME_DIV;
        }
        //���Z���Y�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_DIV.equals(l_strColumnSymbolName) || 
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_FROM.equals(l_strColumnSymbolName) ||
            WEB3AccountOpenExpAccountOpenSymbolNameDef.ASSET_VALUE_TO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ASSET_VALUE_DIV;
        }
        //�^�p�\��z
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_AMOUNT_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_AMOUNT_DIV;
        }
        //�����̐��i
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_DIV;
        }
        //�����̐��i�i���̑��j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FUND_BUDGET_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FUND_BUNDGET_ETC;
        }
        //�����p�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEARCH_DIV;
        }
        //�{�l�m�F���ދ敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_DIV;
        }
        //�{�l�m�F���ދ敪�i���̑��j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ID_CONFIRM_DOC_ETC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ID_CONFIRM_DOC_ETC;
        }
        //��������敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EQUITY_TAX_TYPE;
        }
        //�M�p�����������敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SPECIAL_ACC_MARGIN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.MARGIN_TAX_TYPE;
        }
        //�����ғo�^�t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_FLAG;
        }
        //�����Җ�����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.PRODUCT_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_PRODUCT_NAME;
        }
        //���t��X�֔ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ZIP_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ZIP_CODE;
        }
        //���t��Z���P
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS1;
        }
        //���t��Z���Q
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS2;
        }
        //���t��Z���R
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.SEND_ADDRESS_LINE3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.SEND_ADDRESS3;
        }
        //�e�Њg�����ځi�敪�P�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV1;
        }
        //�e�Њg�����ځi�敪�Q�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV2;
        }
        //�e�Њg�����ځi�敪�R�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV3;
        }
        //�e�Њg�����ځi�敪�S�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV4.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV4;
        }
        //�e�Њg�����ځi�敪�T�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV5.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV5;
        }
        //�e�Њg�����ځi�敪�U�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV6.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV6;
        }
        //�e�Њg�����ځi�敪�V�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV7.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV7;
        }
        //�e�Њg�����ځi�敪�W�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV8.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV8;
        }
        //�e�Њg�����ځi�敪�X�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV9.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV9;
        }
        //�e�Њg�����ځi�敪�P�O�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV10.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV10;
        }
        //�e�Њg�����ځi�t���O1�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG1;
        }
        //�e�Њg�����ځi�t���O2�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG2;
        }
        //�e�Њg�����ځi�t���O3�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG3;
        }
        //�e�Њg�����ځi�t���O4�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG4.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG4;
        }
        //�e�Њg�����ځi�t���O5�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG5.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG5;
        }
        //�e�Њg�����ځi�t���O�U�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG6.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG6;
        }
        //�e�Њg�����ځi�t���O�V�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG7.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG7;
        }
        //�e�Њg�����ځi�t���O�W�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG8.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG8;
        }
        //�e�Њg�����ځi�t���O�X�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG9.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG9;
        }
        //�e�Њg�����ځi�t���O�P�O�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_FLAG10.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_FLAG10;
        }
        //�e�Њg�����ځi�e�L�X�g�P�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT1;
        }
        //�e�Њg�����ځi�e�L�X�g�Q�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT2;
        }
        //�e�Њg�����ځi�e�L�X�g�R�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT3.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT3;
        }
        //�e�Њg�����ځi�e�L�X�g�S�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT4.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT4;
        }
        //�e�Њg�����ځi�e�L�X�g�T�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT5.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT5;
        }
        //�e�Њg�����ځi�e�L�X�g�U�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT6.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT6;
        }
        //�e�Њg�����ځi�e�L�X�g�V�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT7.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT7;
        }
        //�e�Њg�����ځi�e�L�X�g�W�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT8.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT8;
        }
        //�e�Њg�����ځi�e�L�X�g�X�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT9.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT9;
        }
        //�e�Њg�����ځi�e�L�X�g�P�O�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_TEXT10.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_TEXT10;
        }
        //�X�V�҃R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.UPDATER_CODE;
        }
        //�X�V����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.LAST_UPDATED_TIMESTAMP.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.UPDATE_DATE;
        }
        //�쐬�҃R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.CREATOR.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.CREATOR_CODE;
        }
        //�ڋq�������̍쐬�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME_DIV;
        }
        //�ڋq�������̂P
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME1;
        }
        //�ڋq�������̂Q
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.REAL_NAME2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.ACCOUNT_REAL_NAME2;
        }
        //����ƈ��҃R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.BROKERAGE_TRADER_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.BROKERAGE_CODE;
        }
        //�e�Њg�����ځi�敪1�P�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV11.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV11;
        }
        //�e�Њg�����ځi�敪�P2�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV12.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV12;
        }
        //�e�Њg�����ځi�敪�P3�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV13.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV13;
        }
        //�e�Њg�����ځi�敪�P4�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV14.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV14;
        }
        //�e�Њg�����ځi�敪�P5�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.EXT_ITEM_DIV15.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.EXT_ITEM_DIV15;
        }
        //�i�����ҁj�쐬�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_CREATE_DIV;
        }
        //�i�����ҁj�����R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_PRODUCT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_PRODUCT_CODE;
        }
        //�i�����ҁj�֌W�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_RELATION_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_RELATION_CODE;
        }
        //�i�����ҁj������
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_OFFICER_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_EXECUTIVE;
        }
        //�i�����ҁj��E���R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION_CODE;
        }
        //�i�����ҁj��E��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.INSIDER_POST_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.INSIDER_INFO_POSITION;
        }
        //�i�f�o�j�쐬�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_CREATE_DIV;
        }
        //�i�f�o�j�R�[�X
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_COURSE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_COURSE;
        }
        //�i�f�o�j�v����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PLAN.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_PLAN;
        }
        //�i�f�o�j�ڕW�z
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_FIGURE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_FIGURE;
        }
        //�i�f�o�j�ڕW�N
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_YEAR.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_YEAR;
        }
        //�i�f�o�j�ڕW��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TARGET_MONTH.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TARGET_MONTH;
        }
        //�i�f�o�j�ϗ��z
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_INSTALLMENT_FIGURE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_INSTALLMENT_FIGURE;
        }
        //�i�f�o�j��������
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_DEPOSIT_CYCLE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_DEPOSIT_CYCLE;
        }
        //�i�f�o�j��n�o�H
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PAYMENT_ROOT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_PAYMENT_ROOT;
        }
        //�i�f�o�j�ē����敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_REINVEST_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_REINVEST_DIV;
        }
        //�i�f�o�j�ŋ敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAX_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TAX_TYPE;
        }
        //�i�f�o�j�i�D�j���x�z
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_TAXFREE_LIMIT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_TAXFREE_LIMIT;
        }
        //�i�f�o�j�i���D�j���x�z
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SPECIAL_TAXFREE_LIMIT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_SPECIAL_TAXFREE_LIMIT;
        }
        //�i�f�o�j�����E�v
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_SUBSCR_SUMMARY.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_SUBSCR_SUMMARY;
        }
        //�i�f�o�j�����R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_PRODUCT_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_PRODUCT_CODE;
        }
        //�i�f�o�j�S�ۋq
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MORTGAGE_CUSTOMER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_MORTGAGE_CUSTOMER;
        }
        //�i�f�o�j�~�b�N�X�q
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_MIX_CUSTOMER.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_MIX_CUSTOMER;
        }
        //�i�f�o�j�_��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.GP_CONTRACT.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.GP_INFO_CONTRACT;
        }
        //���O���j�쐬�敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_VOUCHER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_CREATE_DIV;
        }
        //�i���O���j���n
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TAXATION_TRAN_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TAXATION_TRAN;
        }
        //�i���O���j�Z���i�J�i�j
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_ADDRESS_LINE_KANA.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_ADDRESS_KANA;
        }
        //�i���O���j����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_TRANSFER_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_TRANSFER_DIV;
        }
        //�i���O���j��s�R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_INSTITUTION_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_INSTITUTION_CODE;
        }
        //�i���O���j�x�X�R�[�h
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_BRANCH_CODE.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_BRANCH_CODE;
        }
        //�i���O���j�a���敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_SAVE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_DIV;
        }
        //�i���O���j�����ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.STK_FIN_ACCOUNT_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_CODE;
        }            
        //(�O�ݗa���������)�����ԍ�
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NO.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_CODE;
        }
        //(�O�ݗa���������)�������`�l
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME;
        }
        //(�O�ݗa���������)�������`�l�p��
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_ACCOUNT_NAME_ENG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME_ALPHA;
        }
        //(�O�ݗa���������)�a���敪
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_SAVE_DIV.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_DIV;
        }
        // �폜�t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.DELETE_FLAG;
        }
        //�폜����
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.DELETE_TIMESTAMP.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.DELETE_DATE;
        }
        //����t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.PRINT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.PRINT_FLAG;
        }
        //��̃t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.RECEIPT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.RECEIVE_FLAG;
        }
        //�����t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGREEMENT_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.APPROVE_FLAG;
        }
        //�O���l�t���O
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.FOREIGN_FLAG.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.FOREIGNER_FLAG;
        }
        //�@@�\�ʒm���
        //�t���K�i1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA1;
        }
        //�t���K�i2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME_KANA2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME_KANA2;
        }
        //����1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME1;
        }
        //����2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ACC_NAME2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ACC_NAME2;
        }
        //�Z��1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS1;
        }
        //�Z��2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_ADDRESS_LINE2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_ADDRESS2;
        }
        //��\�҂̖�E
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_POST.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_POST;
        }
        //��\�҂̃t���K�i1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA1;
        }
        //��\�҂̃t���K�i2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME_KANA2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME_KANA2;
        }
        //��\�҂̎���1
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME1.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME1;
        }
        //��\�҂̎���2
        else if (WEB3AccountOpenExpAccountOpenSymbolNameDef.AGENCY_REP_NAME2.equals(l_strColumnSymbolName))
        {
            l_strMessageItemName = WEB3AccOpenApplyInfoItemDef.AGENCY_REP_NAME2;
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_strMessageItemName;
    }

    /**
     * (to�����J�ݐR���҂�)<BR>
     * �����J�ݐR���҂��I�u�W�F�N�g�𐶐�����B<BR>
     * �P�j�@@�����J�ݐR���҂��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR> 
     * �@@�Ȃ�<BR> 
     * <BR>
     * �Q�j�@@�l�ԋp <BR>
     * �@@�����J�ݐR���҂��I�u�W�F�N�g��ԋp����B<BR> 
     * @@return WEB3AccOpenJudgeWaiting
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenJudgeWaiting toAccOpenJudgeWaiting()
    {
        final String STR_METHOD_NAME = " toAccOpenJudgeWaiting() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����J�ݐR���҂��I�u�W�F�N�g�𐶐�����B
        WEB3AccOpenJudgeWaiting l_judgeWaiting = new WEB3AccOpenJudgeWaiting();
        
        // �Q�j�@@�l�ԋp 
        // �@@�����J�ݐR���҂��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_judgeWaiting;
    }
    /**
     * (to�����J�݃��[���A�h���X�d���`�F�b�N)<BR>
     * �����J�݃��[���A�h���X�d���`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR>
     * �P�j�@@�����J�݃��[���A�h���X�d���`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR>
     *   [�R���X�g���N�^�̈���]<BR> 
     *   �ڋq�R�[�h�B<BR> 
     *   ���ʃR�[�h�B<BR> 
     *   �،���ЃR�[�h�B<BR> 
     * <BR>
     * �Q�j�@@�l�ԋp<BR> 
     *   �����J�݃��[���A�h���X�d���`�F�b�N�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return WEB3AccOpenMailAddressDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenMailAddressDuplicationCheck toAccOpenMailAddressDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " toAccOpenMailAddressDuplicationCheck(String, String ,String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����J�݃��[���A�h���X�d���`�F�b�N�I�u�W�F�N�g�𐶐�����B
        WEB3AccOpenMailAddressDuplicationCheck l_mailAddressDuplicationCheck = 
            new WEB3AccOpenMailAddressDuplicationCheck(
                l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
        
        log.exiting(STR_METHOD_NAME);
        // �Q�j�@@�l�ԋp 
        //   �����J�݃��[���A�h���X�d���`�F�b�N�I�u�W�F�N�g��ԋp����B
        return l_mailAddressDuplicationCheck;
    }
    
    /**
     * (to�����J�ݓd�b�ԍ��d���`�F�b�N )<BR>
     * �����J�ݓd�b�ԍ��d���`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR>
     * �P�j�@@�����J�ݓd�b�ԍ��d���`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR>
     *   [�R���X�g���N�^�̈���]<BR> 
     *   �ڋq�R�[�h�B<BR> 
     *   ���ʃR�[�h�B<BR> 
     *   �،���ЃR�[�h�B<BR> 
     * <BR>
     * �Q�j�@@�l�ԋp<BR> 
     * �@@�����J�ݓd�b�ԍ��d���`�F�b�N�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return WEB3AccOpenMailAddressDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenTelNumberDuplicationCheck toAccOpenTelNumberDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " toAccOpenTelNumberDuplicationCheck(String, String ,String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����J�ݓd�b�ԍ��d���`�F�b�N�I�u�W�F�N�g�𐶐�����B
        WEB3AccOpenTelNumberDuplicationCheck l_telNumberDuplicationCheck = 
            new WEB3AccOpenTelNumberDuplicationCheck(
                l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
        
        // �Q�j�@@�l�ԋp
        // �@@�����J�ݓd�b�ԍ��d���`�F�b�N�I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_telNumberDuplicationCheck;        
    }
    
}
@
