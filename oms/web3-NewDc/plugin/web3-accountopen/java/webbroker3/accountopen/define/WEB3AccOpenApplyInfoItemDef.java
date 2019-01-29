head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInfoItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\����񍀖ږ�(WEB3AccOpenApplyInfoItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 �s�p (���u) �V�K�쐬
                   2006/07/13 ���� (���u) �d�l�ύX ���f��078
                   2006/08/21 ���r (���u) �d�l�ύX ���f��087
Revesion History : 2009/08/12 �����F(���u) �c�a���C�A�E�g053�A056
**/

package webbroker3.accountopen.define;

/**
 * �����J�ݐ\����񍀖ږ�
 * 
 * @@author �s�p(���u)
 * @@version 1.0
 */
public interface WEB3AccOpenApplyInfoItemDef
{
    /**
     * infoClaimDate:  ����������
     */
    public static final String INFO_CLAIM_DATE = "infoClaimDate";

    /**
     * updateDate:  �X�V��
     */
    public static final String UPDATE_DATE = "updateDate";

    /**
     * updaterCode:  �X�V�҃R�[�h
     */
    public static final String UPDATER_CODE = "updaterCode";

    /**
     * requestNumber:  ���ʃR�[�h
     */
    public static final String REQUEST_NUMBER = "requestNumber";
   
    /**
     * requestNumber:  ���͋敪
     */
    public static final String INPUT_DIV = "inputDiv";
    
    /**
     * creatorCode:  �쐬�҃R�[�h
     */
    public static final String CREATOR_CODE = "creatorCode";

    /**
     * exAccountFlag:  ���������t���O
     */
    public static final String EX_ACCOUNT_FLAG = "exAccountFlag";

    /**
     * exAccountCode:  ���������R�[�h
     */
    public static final String EX_ACCOUNT_CODE = "exAccountCode";

    /**
     * exAccountBranchName:  �����������X��
     */
    public static final String EX_ACCOUNT_BRANCH_NAME = "exAccountBranchName";

    /**
     * institutionCode:  �،���ЃR�[�h
     */
    public static final String INSTITUTION_CODE = "institutionCode";

    /**
     * branchCode:  ���X�R�[�h
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountType:  �����敪
     */
    public static final String ACCOUNT_TYPE = "accountType";

    /**
     * accountCode:  �ڋq�R�[�h
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * traderCode:  ���҃R�[�h�iSONAR�j
     */
    public static final String TRADER_CODE = "traderCode";

    /**
     * mailAddress:  ���[���A�h���X
     */
    public static final String MAIL_ADDRESS = "mailAddress";

    /**
     * mobileMailAddress:  ���[���A�h���X�i�g�сj
     */
    public static final String MOBILE_MAIL_ADDRESS = "mobileMailAddress";

    /**
     * accountFamilyName:  �ڋq���i�����j
     */
    public static final String ACCOUNT_FAMILY_NAME = "accountFamilyName";

    /**
     * accountName:  �ڋq���i�����j
     */
    public static final String ACCOUNT_NAME = "accountName";

    /**
     * accountFamilyNameKana:  �ڋq���i�J�i�j
     */
    public static final String ACCOUNT_FAMILY_NAME_KANA = "accountFamilyNameKana";

    /**
     * accountNameKana:  �ڋq���i�J�i�j
     */
    public static final String ACCOUNT_NAME_KANA = "accountNameKana";

    /**
     * eraBorn:  ���N�����N��
     */
    public static final String ERA_BORN = "eraBorn";

    /**
     * bornDate:  ���N����
     */
    public static final String BORN_DATE = "bornDate";

    /**
     * sex:  ����
     */
    public static final String SEX = "sex";

    /**
     * password:  �Ïؔԍ�
     */
    public static final String PASSWORD = "password";

    /**
     * zipCode:  �X�֔ԍ�
     */
    public static final String ZIP_CODE = "zipCode";

    /**
     * addressKana1:  �Z��1�i�J�i�j
     */
    public static final String ADDRESS_KANA1 = "addressKana1";

    /**
     * addressKana2:  �Z��2�i�J�i�j
     */
    public static final String ADDRESS_KANA2 = "addressKana2";

    /**
     * addressKana3:  �Z��3�i�J�i�j
     */
    public static final String ADDRESS_KANA3 = "addressKana3";

    /**
     * address1:  �Z��1�i�����j
     */
    public static final String ADDRESS1 = "address1";

    /**
     * address2:  �Z��2�i�����j
     */
    public static final String ADDRESS2 = "address2";

    /**
     * address3:  �Z��3�i�����j
     */
    public static final String ADDRESS3 = "address3";

    /**
     * telephone:  �d�b�ԍ�
     */
    public static final String TELEPHONE = "telephone";

    /**
     * mobileTelephone:  �g�єԍ�
     */
    public static final String MOBILE_TELEPHONE = "mobileTelephone";

    /**
     * faxTelephone:  �e�`�w�ԍ�
     */
    public static final String FAX_TELEPHONE = "faxTelephone";

    /**
     * officeInfo.occupationDiv:  �Ζ�����.�E�Ƌ敪
     */
    public static final String OFFICE_INFO_OCCUPATION_DIV = "officeInfo.occupationDiv";

    /**
     * officeInfo.officeName:  �Ζ�����.�Ζ��於��
     */
    public static final String OFFICE_INFO_OFFICE_NAME = "officeInfo.officeName";

    /**
     * officeInfo.officeZipCode:  �Ζ�����.�Ζ���X�֔ԍ�
     */
    public static final String OFFICE_INFO_OFFICE_ZIP_CODE = "officeInfo.officeZipCode";

    /**
     * officeInfo.officeAdress:  �Ζ�����.�Ζ���Z��
     */
    public static final String OFFICE_INFO_OFFICE_ADRESS = "officeInfo.officeAdress";

    /**
     * officeInfo.officeTelephone:  �Ζ�����.�Ζ���d�b�ԍ�
     */
    public static final String OFFICE_INFO_OFFICE_TELEPHONE = "officeInfo.officeTelephone";

    /**
     * officeInfo.officeFaxTelephone:  �Ζ�����.�Ζ���e�`�w�ԍ�
     */
    public static final String OFFICE_INFO_OFFICE_FAX_TELEPHONE = "officeInfo.officeFaxTelephone";

    /**
     * officeInfo.department:  �Ζ�����.��������
     */
    public static final String OFFICE_INFO_DEPARTMENT = "officeInfo.department";

    /**
     * officeInfo.position:  �Ζ�����.��E��
     */
    public static final String OFFICE_INFO_POSITION = "officeInfo.position";

    /**
     * contactAddress:  �A����Z��
     */
    public static final String CONTACT_ADDRESS = "contactAddress";

    /**
     * contactTelephone:  �A����d�b�ԍ�
     */
    public static final String CONTACT_TELEPHONE = "contactTelephone";

    /**
     * familyRelationDiv:  �����敪
     */
    public static final String FAMILY_RELATION_DIV = "familyRelationDiv";

    /**
     * familyRelationEtc:  �����i���̑��j
     */
    public static final String FAMILY_RELATION_ETC = "familyRelationEtc";

    /**
     * houseHolderName:  ���ю喼�i�����j
     */
    public static final String HOUSE_HOLDER_NAME = "houseHolderName";

    /**
     * houseHolderNameKana:  ���ю喼�i�J�i�j
     */
    public static final String HOUSE_HOLDER_NAME_KANA = "houseHolderNameKana";

    /**
     * houseHolderOfficeInfo.occupationDiv:  ���ю�Ζ�����.�E�Ƌ敪
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OCCUPATION_DIV = "houseHolderOfficeInfo.occupationDiv";

    /**
     * houseHolderOfficeInfo.officeName:  ���ю�Ζ�����.�Ζ��於��
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_NAME = "houseHolderOfficeInfo.officeName";

    /**
     * houseHolderOfficeInfo.officeZipCode:  ���ю�Ζ�����.�Ζ���X�֔ԍ�
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_ZIP_CODE = "houseHolderOfficeInfo.officeZipCode";

    /**
     * houseHolderOfficeInfo.officeAdress:  ���ю�Ζ�����.�Ζ���Z��
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_ADRESS = "houseHolderOfficeInfo.officeAdress";

    /**
     * houseHolderOfficeInfo.officeTelephone:  ���ю�Ζ�����.�Ζ���d�b�ԍ�
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_TELEPHONE = "houseHolderOfficeInfo.officeTelephone";

    /**
     * houseHolderOfficeInfo.officeFaxTelephone:  ���ю�Ζ�����.�Ζ���e�`�w�ԍ�
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_FAX_TELEPHONE = "houseHolderOfficeInfo.officeFaxTelephone";

    /**
     * houseHolderOfficeInfo.department:  ���ю�Ζ�����.��������
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_DEPARTMENT = "houseHolderOfficeInfo.department";

    /**
     * houseHolderOfficeInfo.position:  ���ю�Ζ�����.��E��
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_POSITION = "houseHolderOfficeInfo.position";

    /**
     * residentDiv:  ���Z�^�񋏏Z�敪
     */
    public static final String RESIDENT_DIV = "residentDiv";

    /**
     * transferDiv:  �U�֋敪
     */
    public static final String TRANSFER_DIV = "transferDiv";

    /**
     * financialAccountName:  �������`�l
     */
    public static final String FINANCIAL_ACCOUNT_NAME = "financialAccountName";

    /**
     * investPurposeDiv:  �����ړI�敪
     */
    public static final String INVEST_PURPOSE_DIV = "investPurposeDiv";

    /**
     * appliMotivatDiv:  ������@@�敪
     */
    public static final String APPLI_MOTIVAT_DIV = "appliMotivatDiv";

    /**
     * annualIncomeDiv:  �N���敪
     */
    public static final String ANNUAL_INCOME_DIV = "annualIncomeDiv";

    /**
     * assetValueDiv:  ���Z���Y�敪
     */
    public static final String ASSET_VALUE_DIV = "assetValueDiv";

    /**
     * fundBundgetAmountDiv:  �^�p�\��z�敪
     */
    public static final String FUND_BUNDGET_AMOUNT_DIV = "fundBundgetAmountDiv";

    /**
     * fundBundgetDiv:  ���Y�̐��i�敪
     */
    public static final String FUND_BUNDGET_DIV = "fundBundgetDiv";

    /**
     * fundBundgetEtc:  ���Y�̐��i�i���̑��j
     */
    public static final String FUND_BUNDGET_ETC = "fundBundgetEtc";

    /**
     * searchDiv:  �����p�敪
     */
    public static final String SEARCH_DIV = "searchDiv";

    /**
     * idConfirmDocDiv:  �{�l�m�F���ދ敪
     */
    public static final String ID_CONFIRM_DOC_DIV = "idConfirmDocDiv";

    /**
     * idConfirmDocEtc:  �{�l�m�F���ށi���̑��j
     */
    public static final String ID_CONFIRM_DOC_ETC = "idConfirmDocEtc";

    /**
     * equityTaxType:  �������������敪
     */
    public static final String EQUITY_TAX_TYPE = "equityTaxType";

    /**
     * marginTaxType:  �M�p��������敪
     */
    public static final String MARGIN_TAX_TYPE = "marginTaxType";

    /**
     * insiderFlag:  �����ғo�^�t���O
     */
    public static final String INSIDER_FLAG = "insiderFlag";

    /**
     * insiderProductName:  �����Җ�����
     */
    public static final String INSIDER_PRODUCT_NAME = "insiderProductName";

    /**
     * sendZipCode:  ���t��X�֔ԍ�
     */
    public static final String SEND_ZIP_CODE = "sendZipCode";

    /**
     * sendAddress1:  ���t��Z���P
     */
    public static final String SEND_ADDRESS1 = "sendAddress1";

    /**
     * sendAddress2:  ���t��Z���Q
     */
    public static final String SEND_ADDRESS2 = "sendAddress2";

    /**
     * sendAddress3:  ���t��Z���R
     */
    public static final String SEND_ADDRESS3 = "sendAddress3";

    /**
     * extItemDiv1:  �敪�P
     */
    public static final String EXT_ITEM_DIV1 = "extItemDiv1";

    /**
     * extItemDiv2:  �敪�Q
     */
    public static final String EXT_ITEM_DIV2 = "extItemDiv2";

    /**
     * extItemDiv3:  �敪�R
     */
    public static final String EXT_ITEM_DIV3 = "extItemDiv3";

    /**
     * extItemDiv4:  �敪�S
     */
    public static final String EXT_ITEM_DIV4 = "extItemDiv4";

    /**
     * extItemDiv5:  �敪�T
     */
    public static final String EXT_ITEM_DIV5 = "extItemDiv5";

    /**
     * extItemDiv6:  �敪�U
     */
    public static final String EXT_ITEM_DIV6 = "extItemDiv6";

    /**
     * extItemDiv7:  �敪�V
     */
    public static final String EXT_ITEM_DIV7 = "extItemDiv7";

    /**
     * extItemDiv8:  �敪�W
     */
    public static final String EXT_ITEM_DIV8 = "extItemDiv8";

    /**
     * extItemDiv9:  �敪�X
     */
    public static final String EXT_ITEM_DIV9 = "extItemDiv9";

    /**
     * extItemDiv10:  �敪�P�O
     */
    public static final String EXT_ITEM_DIV10 = "extItemDiv10";

    /**
     * extItemFlag1:  �t���O�P
     */
    public static final String EXT_ITEM_FLAG1 = "extItemFlag1";

    /**
     * extItemFlag2:  �t���O�Q
     */
    public static final String EXT_ITEM_FLAG2 = "extItemFlag2";

    /**
     * extItemFlag3:  �t���O�R
     */
    public static final String EXT_ITEM_FLAG3 = "extItemFlag3";

    /**
     * extItemFlag4:  �t���O�S
     */
    public static final String EXT_ITEM_FLAG4 = "extItemFlag4";

    /**
     * extItemFlag5:  �t���O�T
     */
    public static final String EXT_ITEM_FLAG5 = "extItemFlag5";

    /**
     * extItemFlag6:  �t���O�U
     */
    public static final String EXT_ITEM_FLAG6 = "extItemFlag6";

    /**
     * extItemFlag7:  �t���O�V
     */
    public static final String EXT_ITEM_FLAG7 = "extItemFlag7";

    /**
     * extItemFlag8:  �t���O�W
     */
    public static final String EXT_ITEM_FLAG8 = "extItemFlag8";

    /**
     * extItemFlag9:  �t���O�X
     */
    public static final String EXT_ITEM_FLAG9 = "extItemFlag9";

    /**
     * extItemFlag10:  �t���O�P�O
     */
    public static final String EXT_ITEM_FLAG10 = "extItemFlag10";

    /**
     * extItemText1:  �e�L�X�g�P
     */
    public static final String EXT_ITEM_TEXT1 = "extItemText1";

    /**
     * extItemText2:  �e�L�X�g�Q
     */
    public static final String EXT_ITEM_TEXT2 = "extItemText2";

    /**
     * extItemText3:  �e�L�X�g�R
     */
    public static final String EXT_ITEM_TEXT3 = "extItemText3";

    /**
     * extItemText4:  �e�L�X�g�S
     */
    public static final String EXT_ITEM_TEXT4 = "extItemText4";

    /**
     * extItemText5:  �e�L�X�g�T
     */
    public static final String EXT_ITEM_TEXT5 = "extItemText5";

    /**
     * extItemText6:  �e�L�X�g�U
     */
    public static final String EXT_ITEM_TEXT6 = "extItemText6";

    /**
     * extItemText7:  �e�L�X�g�V
     */
    public static final String EXT_ITEM_TEXT7 = "extItemText7";

    /**
     * extItemText8:  �e�L�X�g�W
     */
    public static final String EXT_ITEM_TEXT8 = "extItemText8";

    /**
     * extItemText9:  �e�L�X�g�X
     */
    public static final String EXT_ITEM_TEXT9 = "extItemText9";

    /**
     * extItemText10:  �e�L�X�g�P�O
     */
    public static final String EXT_ITEM_TEXT10 = "extItemText10";

    /**
     * transferBankInfo.financialInstitutionCode:  �U�����s���.��s�R�[�h
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_CODE = "transferBankInfo.financialInstitutionCode";

    /**
     * transferBankInfo.financialInstitutionName:  �U�����s���.��s��
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_NAME = "transferBankInfo.financialInstitutionName";

    /**
     * transferBankInfo.financialBranchCode:  �U�����s���.�x�X�R�[�h
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_BRANCH_CODE = "transferBankInfo.financialBranchCode";

    /**
     * transferBankInfo.financialBranchName:  �U�����s���.�x�X��
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_BRANCH_NAME = "transferBankInfo.financialBranchName";

    /**
     * transferBankInfo.financialAccountDiv:  �U�����s���.�a���敪
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_DIV = "transferBankInfo.financialAccountDiv";

    /**
     * transferBankInfo.financialAccountCode:  �U�����s���.�����ԍ�
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_CODE = "transferBankInfo.financialAccountCode";

    /**
     * transferBankInfo.transferCommissionDiv:  �U�����s���.�U�֎萔���敪
     */
    public static final String TRANSFER_BANK_INFO_TRANSFER_COMMISSION_DIV = "transferBankInfo.transferCommissionDiv";

    /**
     * postalTransferInfo.passbookCode:  �X�֐U�֏��.�ʒ��ԍ�
     */
    public static final String POSTAL_TRANSFER_INFO_PASSBOOK_CODE = "postalTransferInfo.passbookCode";

    /**
     * postalTransferInfo.passbookSign:  �X�֐U�֏��.�ʒ��L��
     */
    public static final String POSTAL_TRANSFER_INFO_PASSBOOK_SIGN = "postalTransferInfo.passbookSign";

    /**
     * investExpInfo.experienceEquityDiv:  �����o�����.��������
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_EQUITY_DIV = "investExpInfo.experienceEquityDiv";

    /**
     * investExpInfo.experienceMarginDiv:  �����o�����.�M�p���
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_MARGIN_DIV = "investExpInfo.experienceMarginDiv";

    /**
     * investExpInfo.experienceBondDiv:  �����o�����.��
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_BOND_DIV = "investExpInfo.experienceBondDiv";

    /**
     * investExpInfo.experienceWtDiv:  �����o�����.�]���Ѝ�
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_WT_DIV = "investExpInfo.experienceWtDiv";

    /**
     * investExpInfo.experienceFundSkDiv:  �����o�����.�����M���i�����j
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_FUND_SK_DIV = "investExpInfo.experienceFundSkDiv";

    /**
     * investExpInfo.experienceFundBdDiv:  �����o�����.�����M���i���j
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_FUND_BD_DIV = "investExpInfo.experienceFundBdDiv";

    /**
     * investExpInfo.experienceFoDiv:  �����o�����.�敨�E�I�v�V����
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_FO_DIV = "investExpInfo.experienceFoDiv";

    /**
     * investExpInfo.experienceFEquityDiv:  �����o�����.�O���،�
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_F_EQUITY_DIV = "investExpInfo.experienceFEquityDiv";

    /**
     * investExpInfo.experienceEtcDiv:  �����o�����.���̑�
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_ETC_DIV = "investExpInfo.experienceEtcDiv";

    /**
     * interestDealInfo.interestEquityFlag:  �����̂��������.���������t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_EQUITY_FLAG = "interestDealInfo.interestEquityFlag";

    /**
     * interestDealInfo.interestMstkFlag:  �����̂��������.�����~�j�����t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_MSTK_FLAG = "interestDealInfo.interestMstkFlag";

    /**
     * interestDealInfo.interestMarginFlag:  �����̂��������.�M�p����t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_MARGIN_FLAG = "interestDealInfo.interestMarginFlag";

    /**
     * interestDealInfo.interestBondFlag:  �����̂��������.���t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_BOND_FLAG = "interestDealInfo.interestBondFlag";

    /**
     * interestDealInfo.interestFundFlag:  �����̂��������.�����M���t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_FUND_FLAG = "interestDealInfo.interestFundFlag";

    /**
     * interestDealInfo.interestFoFlag:  �����̂��������.�敨�E�I�v�V�����t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_FO_FLAG = "interestDealInfo.interestFoFlag";

    /**
     * interestDealInfo.interestFEquityFlag:  �����̂��������.�O���،��t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_F_EQUITY_FLAG = "interestDealInfo.interestFEquityFlag";

    /**
     * interestDealInfo.interestEtcFlag:  �����̂��������.���̑��t���O
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_ETC_FLAG = "interestDealInfo.interestEtcFlag";
    
    /**
     * accountRealNameDiv:  �ڋq�������̍쐬�敪
     */
    public static final String ACCOUNT_REAL_NAME_DIV = "accountRealNameDiv";
    
    /**
     * accountRealName1:  �ڋq��������1
     */
    public static final String ACCOUNT_REAL_NAME1 = "accountRealName1";
    
    /**
     * accountRealName2:  �ڋq��������2
     */
    public static final String ACCOUNT_REAL_NAME2 = "accountRealName2";
    
    /**
     * brokerageCode:  ����ƈ��҃R�[�h
     */
    public static final String BROKERAGE_CODE = "brokerageCode";
    
    /**
     * extItemDiv11:  �敪�P�P
     */
    public static final String EXT_ITEM_DIV11 = "extItemDiv11";
    
    /**
     * extItemDiv12:  �敪�P�Q
     */
    public static final String EXT_ITEM_DIV12 = "extItemDiv12";
    
    /**
     * extItemDiv13:  �敪�P�R
     */
    public static final String EXT_ITEM_DIV13 = "extItemDiv13";
    
    /**
     * extItemDiv14:  �敪�P�S
     */
    public static final String EXT_ITEM_DIV14 = "extItemDiv14";
    
    /**
     * extItemDiv15:  �敪�P�T
     */
    public static final String EXT_ITEM_DIV15 = "extItemDiv15";
    
    /**
     * insiderInfo.createDiv:  �����ҏ��.�쐬�敪
     */
    public static final String INSIDER_INFO_CREATE_DIV = "insiderInfo.createDiv";
    
    /**
     * insiderInfo.productCode:  �����ҏ��.�����R�[�h
     */
    public static final String INSIDER_INFO_PRODUCT_CODE = "insiderInfo.productCode";
    
    /**
     * insiderInfo.relationCode:  �����ҏ��.�֌W�敪
     */
    public static final String INSIDER_INFO_RELATION_CODE = "insiderInfo.relationCode";
    
    /**
     * insiderInfo.executive:  �����ҏ��.������
     */
    public static final String INSIDER_INFO_EXECUTIVE = "insiderInfo.executive";
    
    /**
     * insiderInfo.positionCode:  �����ҏ��.��E���R�[�h
     */
    public static final String INSIDER_INFO_POSITION_CODE = "insiderInfo.positionCode";

    /**
     * insiderInfo.position:  �����ҏ��.��E��
     */
    public static final String INSIDER_INFO_POSITION = "insiderInfo.position";
    
    /**
     * gpInfo.createDiv:  GP���.�쐬�敪
     */
    public static final String GP_INFO_CREATE_DIV = "gpInfo.createDiv";
    
    /**
     * gpInfo.course:  GP���.�R�[�X
     */
    public static final String GP_INFO_COURSE = "gpInfo.course";
    
    /**
     * gpInfo.plan:  GP���.�v����
     */
    public static final String GP_INFO_PLAN = "gpInfo.plan";
    
    /**
     * gpInfo.targetFigure:  GP���.�ڕW�z
     */
    public static final String GP_INFO_TARGET_FIGURE = "gpInfo.targetFigure";
    
    /**
     * gpInfo.targetYear:  GP���.�ڕW�N
     */
    public static final String GP_INFO_TARGET_YEAR = "gpInfo.targetYear";
    
    /**
     * gpInfo.targetMonth:  GP���.�ڕW��
     */
    public static final String GP_INFO_TARGET_MONTH = "gpInfo.targetMonth";
    
    /**
     * gpInfo.installmentFigure:  GP���.�ϗ��z
     */
    public static final String GP_INFO_INSTALLMENT_FIGURE = "gpInfo.installmentFigure";
    
    /**
     * gpInfo.depositCycle:  GP���.��������
     */
    public static final String GP_INFO_DEPOSIT_CYCLE = "gpInfo.depositCycle";
    
    /**
     * gpInfo.paymentRoot:  GP���.��n�o�H
     */
    public static final String GP_INFO_PAYMENT_ROOT = "gpInfo.paymentRoot";
    
    /**
     * gpInfo.reinvestDiv:  GP���.�ē����敪
     */
    public static final String GP_INFO_REINVEST_DIV = "gpInfo.reinvestDiv";
    
    /**
     * gpInfo.taxType:  GP���.�ŋ敪
     */
    public static final String GP_INFO_TAX_TYPE = "gpInfo.taxType";
    
    /**
     * gpInfo.taxfreeLimit:  GP���.�i�D�j���x�z
     */
    public static final String GP_INFO_TAXFREE_LIMIT = "gpInfo.taxfreeLimit";
    
    /**
     * gpInfo.specialTaxfreeLimit:  GP���.�i���D�j���x�z
     */
    public static final String GP_INFO_SPECIAL_TAXFREE_LIMIT = "gpInfo.specialTaxfreeLimit";
    
    /**
     * gpInfo.subscrSummary:  GP���.�����E�v
     */
    public static final String GP_INFO_SUBSCR_SUMMARY = "gpInfo.subscrSummary";
    
    /**
     * gpInfo.productCode:  GP���.�����R�[�h
     */
    public static final String GP_INFO_PRODUCT_CODE = "gpInfo.productCode";
    
    /**
     * gpInfo.mortgageCustomer:  GP���.�S�ۋq
     */
    public static final String GP_INFO_MORTGAGE_CUSTOMER = "gpInfo.mortgageCustomer";
    
    /**
     * gpInfo.mixCustomer:  GP���.�~�b�N�X�q
     */
    public static final String GP_INFO_MIX_CUSTOMER = "gpInfo.mixCustomer";
    
    /**
     * gpInfo.contract:  GP���.�_��
     */
    public static final String GP_INFO_CONTRACT = "gpInfo.contract";
    
    /**
     * listedFeqInfo.createDiv:  ���O�����.�쐬�敪
     */
    public static final String LISTED_FEQ_INFO_CREATE_DIV = "listedFeqInfo.createDiv";
    
    /**
     * listedFeqInfo.taxationTran:  ���O�����.���n
     */
    public static final String LISTED_FEQ_INFO_TAXATION_TRAN = "listedFeqInfo.taxationTran";
    
    /**
     * listedFeqInfo.addressKana:  ���O�����.�Z���i�J�i�j
     */
    public static final String LISTED_FEQ_INFO_ADDRESS_KANA = "listedFeqInfo.addressKana";
    
    /**
     * listedFeqInfo.transferDiv:  ���O�����.����
     */
    public static final String LISTED_FEQ_INFO_TRANSFER_DIV = "listedFeqInfo.transferDiv";
    
    /**
     * listedFeqInfo.financialInstitutionCode:  ���O�����.��s�R�[�h
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_INSTITUTION_CODE = "listedFeqInfo.financialInstitutionCode";
    
    /**
     * listedFeqInfo.financialBranchCode:  ���O�����.�x�X�R�[�h
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_BRANCH_CODE = "listedFeqInfo.financialBranchCode";
    
    /**
     * listedFeqInfo.financialAccountDiv:  ���O�����.�a���敪
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_DIV = "listedFeqInfo.financialAccountDiv";
    
    /**
     * listedFeqInfo.financialAccountCode:  ���O�����.�����ԍ�
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_CODE = "listedFeqInfo.financialAccountCode";
    
    /**
     * foreignSaveInfo.financialAccountCode:�O�ݗa���������.�����ԍ�
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_CODE = "foreignSaveInfo.financialAccountCode";
    
    /**
     * foreignSaveInfo.financialAccountName:�O�ݗa���������.�������`�l
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME = "foreignSaveInfo.financialAccountName";
    
    /**
     * foreignSaveInfo.financialAccountNameAlpha:�O�ݗa���������.�������`�l�p��
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME_ALPHA = "foreignSaveInfo.financialAccountNameAlpha";
    
    /**
     * foreignSaveInfo.financialAccountDiv:�O�ݗa���������.�a���敪
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_DIV= "foreignSaveInfo.financialAccountDiv";

    /**
     * deleteFlag�폜�t���O
     */
    public static final String DELETE_FLAG = "deleteFlag";

    /**
     * deleteDate�폜����
     */
    public static final String DELETE_DATE = "deleteDate";

    /**
     * printFlag����t���O
     */
    public static final String PRINT_FLAG = "printFlag";

    /**
     * receiveFlag��̃t���O
     */
    public static final String RECEIVE_FLAG = "receiveFlag";

    /**
     * approveFlag�����t���O
     */
    public static final String APPROVE_FLAG = "approveFlag";

    /**
     * foreignerFlag�O���l�t���O
     */
    public static final String FOREIGNER_FLAG = "foreignerFlag";

    /**
     * agencyInfo.agencyAccNameKana1�t���K�i1
     */
    public static final String AGENCY_ACC_NAME_KANA1 = "agencyInfo.agencyAccNameKana1";

    /**
     * agencyInfo.agencyAccNameKana2�t���K�i2
     */
    public static final String AGENCY_ACC_NAME_KANA2 = "agencyInfo.agencyAccNameKana2";

    /**
     * agencyInfo.agencyAccName1����1
     */
    public static final String AGENCY_ACC_NAME1 = "agencyInfo.agencyAccName1";

    /**
     * agencyInfo.agencyAccName2����2
     */
    public static final String AGENCY_ACC_NAME2= "agencyInfo.agencyAccName2";

    /**
     * agencyInfo.agencyAddress1�Z��1
     */
    public static final String AGENCY_ADDRESS1 = "agencyInfo.agencyAddress1";

    /**
     * agencyInfo.agencyAddress2�Z��2
     */
    public static final String AGENCY_ADDRESS2 = "agencyInfo.agencyAddress2";

    /**
     * agencyInfo.agencyRepPost��\�҂̖�E
     */
    public static final String AGENCY_REP_POST = "agencyInfo.agencyRepPost";

    /**
     * agencyInfo.agencyRepNameKana1��\�҂̃t���K�i1
     */
    public static final String AGENCY_REP_NAME_KANA1 = "agencyInfo.agencyRepNameKana1";

    /**
     * agencyInfo.agencyRepNameKana2��\�҂̃t���K�i2
     */
    public static final String AGENCY_REP_NAME_KANA2 = "agencyInfo.agencyRepNameKana2";

    /**
     * agencyInfo.agencyRepName1��\�҂̎���1
     */
    public static final String AGENCY_REP_NAME1 = "agencyInfo.agencyRepName1";

    /**
     * agencyInfo.agencyRepName2��\�҂̎���2
     */
    public static final String AGENCY_REP_NAME2 = "agencyInfo.agencyRepName2";
}
@
