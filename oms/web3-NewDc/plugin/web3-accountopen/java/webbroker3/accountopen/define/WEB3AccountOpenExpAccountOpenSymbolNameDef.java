head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccountOpenExpAccountOpenSymbolNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݌����q�񕨗���WEB3AccountOpenExpAccountOpenSymbolNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �s�p (���u) �V�K�쐬
                   2006/07/13 ���� (���u) �d�l�ύX ���f��078
                   2006/08/14 ���r (���u) �d�l�ύX ���f��087
Revesion History : 2009/08/12 �����F(���u) �c�a���C�A�E�g053�A056
*/
package webbroker3.accountopen.define;

/**
 * �����J�݌����q�񕨗���
 * 
 * @@author �s�p(���u)
 * @@version 1.0
 */
public interface WEB3AccountOpenExpAccountOpenSymbolNameDef
{
    /**
     * institution_code:  �،���ЃR�[�h
     */
    public static final String INSTITUTION_CODE = "institution_code";

    /**
     * institution_id:  �،����ID
     */
    public static final String INSTITUTION_ID = "institution_id";

    /**
     * branch_id:  ���X�h�c
     */
    public static final String BRANCH_ID = "branch_id";

    /**
     * branch_code:  ���X�R�[�h
     */
    public static final String BRANCH_CODE = "branch_code";

    /**
     * acc_open_request_number:  ���ʃR�[�h
     */
    public static final String ACC_OPEN_REQUEST_NUMBER = "acc_open_request_number";

    /**
     * account_code:  �����R�[�h
     */
    public static final String ACCOUNT_CODE = "account_code";

    /**
     * sonar_trader_code:  ���҃R�[�h�iSONAR�j
     */
    public static final String SONAR_TRADER_CODE = "sonar_trader_code";

    /**
     * ex_account_flag:  ���������t���O
     */
    public static final String EX_ACCOUNT_FLAG = "ex_account_flag";

    /**
     * ex_branch_name:  �����������X��
     */
    public static final String EX_BRANCH_NAME = "ex_branch_name";

    /**
     * ex_account_code:  ���������R�[�h
     */
    public static final String EX_ACCOUNT_CODE = "ex_account_code";

    /**
     * account_div:  �����敪
     */
    public static final String ACCOUNT_DIV = "account_div";

    /**
     * order_div:  ���͋敪
     */
    public static final String ORDER_DIV = "order_div";

    /**
     * infomation_claim_datetime:  ������������
     */
    public static final String INFOMATION_CLAIM_DATETIME = "infomation_claim_datetime";

    /**
     * account_open_date:  �����o�^��
     */
    public static final String ACCOUNT_OPEN_DATE = "account_open_date";

    /**
     * initial_password:  �����p�X���[�h
     */
    public static final String INITIAL_PASSWORD = "initial_password";

    /**
     * family_name:  �ڋq���i�����j
     */
    public static final String FAMILY_NAME = "family_name";

    /**
     * given_name:  �ڋq���i�����j
     */
    public static final String GIVEN_NAME = "given_name";

    /**
     * family_name_alt1:  �ڋq���i�J�i�j
     */
    public static final String FAMILY_NAME_ALT1 = "family_name_alt1";

    /**
     * given_name_alt1:  �ڋq���i�J�i�j
     */
    public static final String GIVEN_NAME_ALT1 = "given_name_alt1";

    /**
     * sex:  ����
     */
    public static final String SEX = "sex";

    /**
     * era_born:  ���N�����N��
     */
    public static final String ERA_BORN = "era_born";

    /**
     * born_date:  ���N����
     */
    public static final String BORN_DATE = "born_date";

    /**
     * email_address:  email�A�h���X
     */
    public static final String EMAIL_ADDRESS = "email_address";

    /**
     * email_address_alt1:  email�A�h���X�P
     */
    public static final String EMAIL_ADDRESS_ALT1 = "email_address_alt1";

    /**
     * zip_code:  �X�֔ԍ�
     */
    public static final String ZIP_CODE = "zip_code";

    /**
     * address_line1:  �Z���P
     */
    public static final String ADDRESS_LINE1 = "address_line1";

    /**
     * address_line2:  �Z���Q
     */
    public static final String ADDRESS_LINE2 = "address_line2";

    /**
     * address_line3:  �Z���R
     */
    public static final String ADDRESS_LINE3 = "address_line3";

    /**
     * address_line1_kana:  �Z���P�i�J�i�j
     */
    public static final String ADDRESS_LINE1_KANA = "address_line1_kana";

    /**
     * address_line2_kana:  �Z���Q�i�J�i�j
     */
    public static final String ADDRESS_LINE2_KANA = "address_line2_kana";

    /**
     * address_line3_kana:  �Z���R�i�J�i�j
     */
    public static final String ADDRESS_LINE3_KANA = "address_line3_kana";

    /**
     * telephone:  �d�b�ԍ�
     */
    public static final String TELEPHONE = "telephone";

    /**
     * mobile:  �A����d�b�ԍ��i�g�сj
     */
    public static final String MOBILE = "mobile";

    /**
     * fax:  �e�`�w�ԍ�
     */
    public static final String FAX = "fax";

    /**
     * occupation_div:  �E�Ƌ敪
     */
    public static final String OCCUPATION_DIV = "occupation_div";

    /**
     * office:  �Ζ��於��
     */
    public static final String OFFICE = "office";

    /**
     * office_zip_code:  �Ζ���X�֔ԍ�
     */
    public static final String OFFICE_ZIP_CODE = "office_zip_code";

    /**
     * office_address:  �Ζ���Z��
     */
    public static final String OFFICE_ADDRESS = "office_address";

    /**
     * office_telephone:  �Ζ���d�b�ԍ�
     */
    public static final String OFFICE_TELEPHONE = "office_telephone";

    /**
     * office_fax:  �Ζ���FAX�ԍ�
     */
    public static final String OFFICE_FAX = "office_fax";

    /**
     * department:  ��������
     */
    public static final String DEPARTMENT = "department";

    /**
     * post:  ��E
     */
    public static final String POST = "post";

    /**
     * contact_address:  �A����Z��
     */
    public static final String CONTACT_ADDRESS = "contact_address";

    /**
     * contact_telephone:  �A����d�b�ԍ�
     */
    public static final String CONTACT_TELEPHONE = "contact_telephone";

    /**
     * family_relationship:  �����敪
     */
    public static final String FAMILY_RELATIONSHIP = "family_relationship";

    /**
     * family_relationship_etc:  �����敪�i���̑��j
     */
    public static final String FAMILY_RELATIONSHIP_ETC = "family_relationship_etc";

    /**
     * householder:  ���ю喼�i�����j
     */
    public static final String HOUSEHOLDER = "householder";

    /**
     * householder_kana:  ���ю喼�i�J�i�j
     */
    public static final String HOUSEHOLDER_KANA = "householder_kana";

    /**
     * householder_occupation_div:  ���ю�E�Ƌ敪
     */
    public static final String HOUSEHOLDER_OCCUPATION_DIV = "householder_occupation_div";

    /**
     * householder_office:  ���ю�Ζ���
     */
    public static final String HOUSEHOLDER_OFFICE = "householder_office";

    /**
     * householder_office_address:  ���ю�Ζ���Z��
     */
    public static final String HOUSEHOLDER_OFFICE_ADDRESS = "householder_office_address";

    /**
     * householder_department:  ���ю及������
     */
    public static final String HOUSEHOLDER_DEPARTMENT = "householder_department";

    /**
     * householder_office_tel:  ���ю�Ζ���d�b�ԍ�
     */
    public static final String HOUSEHOLDER_OFFICE_TEL = "householder_office_tel";

    /**
     * householder_office_fax:  ���ю�Ζ���FAX�ԍ�
     */
    public static final String HOUSEHOLDER_OFFICE_FAX = "householder_office_fax";

    /**
     * householder_post:  ���ю��E
     */
    public static final String HOUSEHOLDER_POST = "householder_post";

    /**
     * resident:  ���Z�^�񋏏Z�敪
     */
    public static final String RESIDENT = "resident";

    /**
     * transfer_div:  �U�֋敪
     */
    public static final String TRANSFER_DIV = "transfer_div";

    /**
     * fin_institution_code:  ��s�R�[�h
     */
    public static final String FIN_INSTITUTION_CODE = "fin_institution_code";

    /**
     * fin_institution_name:  ��s��
     */
    public static final String FIN_INSTITUTION_NAME = "fin_institution_name";

    /**
     * fin_branch_code:  �x�X�R�[�h
     */
    public static final String FIN_BRANCH_CODE = "fin_branch_code";

    /**
     * fin_branch_name:  �x�X��
     */
    public static final String FIN_BRANCH_NAME = "fin_branch_name";

    /**
     * fin_save_div:  �a���敪
     */
    public static final String FIN_SAVE_DIV = "fin_save_div";

    /**
     * fin_account_no:  �����ԍ�
     */
    public static final String FIN_ACCOUNT_NO = "fin_account_no";

    /**
     * postal_save_code:  �ʒ��L��
     */
    public static final String POSTAL_SAVE_CODE = "postal_save_code";

    /**
     * postal_save_no:  �ʒ��ԍ�
     */
    public static final String POSTAL_SAVE_NO = "postal_save_no";

    /**
     * fin_account_name:  �������`�l
     */
    public static final String FIN_ACCOUNT_NAME = "fin_account_name";

    /**
     * trans_commission:  �U�֎萔���敪
     */
    public static final String TRANS_COMMISSION = "trans_commission";

    /**
     * experience_div_equity:  ��������
     */
    public static final String EXPERIENCE_DIV_EQUITY = "experience_div_equity";

    /**
     * experience_div_margin:  �M�p���
     */
    public static final String EXPERIENCE_DIV_MARGIN = "experience_div_margin";

    /**
     * experience_div_bond:  ��
     */
    public static final String EXPERIENCE_DIV_BOND = "experience_div_bond";

    /**
     * experience_div_wt:  �]���Ѝ�
     */
    public static final String EXPERIENCE_DIV_WT = "experience_div_wt";

    /**
     * experience_div_fund_sk:  �����M���i�����j
     */
    public static final String EXPERIENCE_DIV_FUND_SK = "experience_div_fund_sk";

    /**
     * experience_div_fund_bd:  �����M���i���Ѝj
     */
    public static final String EXPERIENCE_DIV_FUND_BD = "experience_div_fund_bd";

    /**
     * experience_div_fo:  �敨�E�I�v�V����
     */
    public static final String EXPERIENCE_DIV_FO = "experience_div_fo";

    /**
     * experience_div_f_equity:  �O���،�
     */
    public static final String EXPERIENCE_DIV_F_EQUITY = "experience_div_f_equity";

    /**
     * experience_div_etc:  ���̑�
     */
    public static final String EXPERIENCE_DIV_ETC = "experience_div_etc";

    /**
     * experience_flag_equity:  ���������t���O
     */
    public static final String EXPERIENCE_FLAG_EQUITY = "experience_flag_equity";

    /**
     * experience_flag_margin:  �M�p����t���O
     */
    public static final String EXPERIENCE_FLAG_MARGIN = "experience_flag_margin";

    /**
     * experience_flag_bond:  ���t���O
     */
    public static final String EXPERIENCE_FLAG_BOND = "experience_flag_bond";

    /**
     * experience_flag_wt:  �]���Ѝt���O
     */
    public static final String EXPERIENCE_FLAG_WT = "experience_flag_wt";

    /**
     * experience_flag_fund_sk:  �����M���i�����j�t���O
     */
    public static final String EXPERIENCE_FLAG_FUND_SK = "experience_flag_fund_sk";

    /**
     * experience_flag_fund_bd:  �����M���i���Ѝj�t���O
     */
    public static final String EXPERIENCE_FLAG_FUND_BD = "experience_flag_fund_bd";

    /**
     * experience_flag_fo:  �敨�E�I�v�V�����t���O
     */
    public static final String EXPERIENCE_FLAG_FO = "experience_flag_fo";

    /**
     * experience_flag_f_equity:  �O���،��t���O
     */
    public static final String EXPERIENCE_FLAG_F_EQUITY = "experience_flag_f_equity";

    /**
     * experience_flag_etc:  ���̑��t���O
     */
    public static final String EXPERIENCE_FLAG_ETC = "experience_flag_etc";

    /**
     * experience_from:  �o���N���i���j
     */
    public static final String EXPERIENCE_FROM = "experience_from";

    /**
     * experience_to:  �o���N���i���j
     */
    public static final String EXPERIENCE_TO = "experience_to";

    /**
     * interest_flag_equity:  ���������t���O
     */
    public static final String INTEREST_FLAG_EQUITY = "interest_flag_equity";

    /**
     * interest_flag_ministock:  �����~�j�����t���O
     */
    public static final String INTEREST_FLAG_MINISTOCK = "interest_flag_ministock";

    /**
     * interest_flag_margin:  �M�p����t���O
     */
    public static final String INTEREST_FLAG_MARGIN = "interest_flag_margin";

    /**
     * interest_flag_bond:  ���t���O
     */
    public static final String INTEREST_FLAG_BOND = "interest_flag_bond";

    /**
     * interest_flag_fund:  �����M���t���O
     */
    public static final String INTEREST_FLAG_FUND = "interest_flag_fund";

    /**
     * interest_flag_fo:  �敨�E�I�v�V�����t���O
     */
    public static final String INTEREST_FLAG_FO = "interest_flag_fo";

    /**
     * interest_flag_f_equity:  �O���،��t���O
     */
    public static final String INTEREST_FLAG_F_EQUITY = "interest_flag_f_equity";

    /**
     * interest_flag_etc:  ���̑��t���O
     */
    public static final String INTEREST_FLAG_ETC = "interest_flag_etc";

    /**
     * invest_purpose_div:  �����ړI�敪
     */
    public static final String INVEST_PURPOSE_DIV = "invest_purpose_div";

    /**
     * appli_motivat_div:  ������@@�敪
     */
    public static final String APPLI_MOTIVAT_DIV = "appli_motivat_div";

    /**
     * annual_income_div:  �N���敪
     */
    public static final String ANNUAL_INCOME_DIV = "annual_income_div";

    /**
     * annual_income_from:  �N���i���j
     */
    public static final String ANNUAL_INCOME_FROM = "annual_income_from";

    /**
     * annual_income_to:  �N���i���j
     */
    public static final String ANNUAL_INCOME_TO = "annual_income_to";

    /**
     * asset_value_div:  ���Z���Y�敪
     */
    public static final String ASSET_VALUE_DIV = "asset_value_div";

    /**
     * asset_value_from:  ���Z���Y�i���j
     */
    public static final String ASSET_VALUE_FROM = "asset_value_from";

    /**
     * asset_value_to:  ���Z���Y�i���j
     */
    public static final String ASSET_VALUE_TO = "asset_value_to";

    /**
     * fund_budget_amount_div:  �^�p�\��z
     */
    public static final String FUND_BUDGET_AMOUNT_DIV = "fund_budget_amount_div";

    /**
     * fund_budget_div:  �����̐��i
     */
    public static final String FUND_BUDGET_DIV = "fund_budget_div";

    /**
     * fund_budget_etc:  �����̐��i�i���̑��j
     */
    public static final String FUND_BUDGET_ETC = "fund_budget_etc";

    /**
     * id_confirm_flag:  �����p�敪
     */
    public static final String ID_CONFIRM_FLAG = "id_confirm_flag";

    /**
     * id_confirm_doc_div:  �{�l�m�F���ދ敪
     */
    public static final String ID_CONFIRM_DOC_DIV = "id_confirm_doc_div";

    /**
     * id_confirm_doc_etc:  �{�l�m�F���ދ敪�i���̑��j
     */
    public static final String ID_CONFIRM_DOC_ETC = "id_confirm_doc_etc";

    /**
     * special_acc:  ��������敪
     */
    public static final String SPECIAL_ACC = "special_acc";

    /**
     * special_acc_margin:  �M�p�����������敪
     */
    public static final String SPECIAL_ACC_MARGIN = "special_acc_margin";

    /**
     * insider_flag:  �����ғo�^�t���O
     */
    public static final String INSIDER_FLAG = "insider_flag";

    /**
     * product_name:  �����Җ�����
     */
    public static final String PRODUCT_NAME = "product_name";

    /**
     * send_zip_code:  ���t��X�֔ԍ�
     */
    public static final String SEND_ZIP_CODE = "send_zip_code";

    /**
     * send_address_line1:  ���t��Z���P
     */
    public static final String SEND_ADDRESS_LINE1 = "send_address_line1";

    /**
     * send_address_line2:  ���t��Z���Q
     */
    public static final String SEND_ADDRESS_LINE2 = "send_address_line2";

    /**
     * send_address_line3:  ���t��Z���R
     */
    public static final String SEND_ADDRESS_LINE3 = "send_address_line3";

    /**
     * ext_item_div1:  �e�Њg�����ځi�敪�P�j
     */
    public static final String EXT_ITEM_DIV1 = "ext_item_div1";

    /**
     * ext_item_div2:  �e�Њg�����ځi�敪�Q�j
     */
    public static final String EXT_ITEM_DIV2 = "ext_item_div2";

    /**
     * ext_item_div3:  �e�Њg�����ځi�敪�R�j
     */
    public static final String EXT_ITEM_DIV3 = "ext_item_div3";

    /**
     * ext_item_div4:  �e�Њg�����ځi�敪�S�j
     */
    public static final String EXT_ITEM_DIV4 = "ext_item_div4";

    /**
     * ext_item_div5:  �e�Њg�����ځi�敪�T�j
     */
    public static final String EXT_ITEM_DIV5 = "ext_item_div5";

    /**
     * ext_item_div6:  �e�Њg�����ځi�敪�U�j
     */
    public static final String EXT_ITEM_DIV6 = "ext_item_div6";

    /**
     * ext_item_div7:  �e�Њg�����ځi�敪�V�j
     */
    public static final String EXT_ITEM_DIV7 = "ext_item_div7";

    /**
     * ext_item_div8:  �e�Њg�����ځi�敪�W�j
     */
    public static final String EXT_ITEM_DIV8 = "ext_item_div8";

    /**
     * ext_item_div9:  �e�Њg�����ځi�敪�X�j
     */
    public static final String EXT_ITEM_DIV9 = "ext_item_div9";

    /**
     * ext_item_div10:  �e�Њg�����ځi�敪�P�O�j
     */
    public static final String EXT_ITEM_DIV10 = "ext_item_div10";

    /**
     * ext_item_flag1:  �e�Њg�����ځi�t���O1�j
     */
    public static final String EXT_ITEM_FLAG1 = "ext_item_flag1";

    /**
     * ext_item_flag2:  �e�Њg�����ځi�t���O2�j
     */
    public static final String EXT_ITEM_FLAG2 = "ext_item_flag2";

    /**
     * ext_item_flag3:  �e�Њg�����ځi�t���O3�j
     */
    public static final String EXT_ITEM_FLAG3 = "ext_item_flag3";

    /**
     * ext_item_flag4:  �e�Њg�����ځi�t���O4�j
     */
    public static final String EXT_ITEM_FLAG4 = "ext_item_flag4";

    /**
     * ext_item_flag5:  �e�Њg�����ځi�t���O5�j
     */
    public static final String EXT_ITEM_FLAG5 = "ext_item_flag5";

    /**
     * ext_item_flag6:  �e�Њg�����ځi�t���O�U�j
     */
    public static final String EXT_ITEM_FLAG6 = "ext_item_flag6";

    /**
     * ext_item_flag7:  �e�Њg�����ځi�t���O�V�j
     */
    public static final String EXT_ITEM_FLAG7 = "ext_item_flag7";

    /**
     * ext_item_flag8:  �e�Њg�����ځi�t���O�W�j
     */
    public static final String EXT_ITEM_FLAG8 = "ext_item_flag8";

    /**
     * ext_item_flag9:  �e�Њg�����ځi�t���O�X�j
     */
    public static final String EXT_ITEM_FLAG9 = "ext_item_flag9";

    /**
     * ext_item_flag10:  �e�Њg�����ځi�t���O�P�O�j
     */
    public static final String EXT_ITEM_FLAG10 = "ext_item_flag10";

    /**
     * ext_item_text1:  �e�Њg�����ځi�e�L�X�g�P�j
     */
    public static final String EXT_ITEM_TEXT1 = "ext_item_text1";

    /**
     * ext_item_text2:  �e�Њg�����ځi�e�L�X�g�Q�j
     */
    public static final String EXT_ITEM_TEXT2 = "ext_item_text2";

    /**
     * ext_item_text3:  �e�Њg�����ځi�e�L�X�g�R�j
     */
    public static final String EXT_ITEM_TEXT3 = "ext_item_text3";

    /**
     * ext_item_text4:  �e�Њg�����ځi�e�L�X�g�S�j
     */
    public static final String EXT_ITEM_TEXT4 = "ext_item_text4";

    /**
     * ext_item_text5:  �e�Њg�����ځi�e�L�X�g�T�j
     */
    public static final String EXT_ITEM_TEXT5 = "ext_item_text5";

    /**
     * ext_item_text6:  �e�Њg�����ځi�e�L�X�g�U�j
     */
    public static final String EXT_ITEM_TEXT6 = "ext_item_text6";

    /**
     * ext_item_text7:  �e�Њg�����ځi�e�L�X�g�V�j
     */
    public static final String EXT_ITEM_TEXT7 = "ext_item_text7";

    /**
     * ext_item_text8:  �e�Њg�����ځi�e�L�X�g�W�j
     */
    public static final String EXT_ITEM_TEXT8 = "ext_item_text8";

    /**
     * ext_item_text9:  �e�Њg�����ځi�e�L�X�g�X�j
     */
    public static final String EXT_ITEM_TEXT9 = "ext_item_text9";

    /**
     * ext_item_text10:  �e�Њg�����ځi�e�L�X�g�P�O�j
     */
    public static final String EXT_ITEM_TEXT10 = "ext_item_text10";

    /**
     * last_updater:  �X�V�҃R�[�h
     */
    public static final String LAST_UPDATER = "last_updater";

    /**
     * created_timestamp:  �쐬����
     */
    public static final String CREATED_TIMESTAMP = "created_timestamp";

    /**
     * last_updated_timestamp:  �X�V����
     */
    public static final String LAST_UPDATED_TIMESTAMP = "last_updated_timestamp";
    
    /**
     * creator:  �쐬�҃R�[�h
     */
    public static final String CREATOR = "creator";
    
    /**
     * exclusive_use_account_no:  ��p�U��������ԍ�
     */
    public static final String EXCLUSIVE_USE_ACCOUNT_NO = "exclusive_use_account_no";
    
    /**
     * send_timestamp:  HULFT���M����
     */
    public static final String SEND_TIMESTAMP = "send_timestamp";
    
    /**
     * real_name_voucher_div:  �ڋq�������̍쐬�敪
     */
    public static final String REAL_NAME_VOUCHER_DIV = "real_name_voucher_div";
    
    /**
     * real_name1:  �ڋq�������̂P
     */
    public static final String REAL_NAME1 = "real_name1";
    
    /**
     * real_name2:  �ڋq��������2
     */
    public static final String REAL_NAME2 = "real_name2";
    
    /**
     * insider_voucher_div:  �i�����ҁj�쐬�敪
     */
    public static final String INSIDER_VOUCHER_DIV = "insider_voucher_div";
    
    /**
     * insider_product_code:  �i�����ҁj�����R�[�h
     */
    public static final String INSIDER_PRODUCT_CODE = "insider_product_code";
    
    /**
     * insider_relation_div:  �i�����ҁj�֌W�敪
     */
    public static final String INSIDER_RELATION_DIV = "insider_relation_div";
    
    /**
     * insider_officer_name:  �i�����ҁj������
     */
    public static final String INSIDER_OFFICER_NAME = "insider_officer_name";
    
    /**
     * insider_post_code:  �i�����ҁj��E���R�[�h
     */
    public static final String INSIDER_POST_CODE = "insider_post_code";
    
    /**
     * insider_post_name:  �i�����ҁj��E��
     */
    public static final String INSIDER_POST_NAME = "insider_post_name";
    
    /**
     * gp_voucher_div:  �i�f�o�j�쐬�敪
     */
    public static final String GP_VOUCHER_DIV = "gp_voucher_div";
    
    /**
     * gp_course:  �i�f�o�j�R�[�X
     */
    public static final String GP_COURSE = "gp_course";
    
    /**
     * gp_plan:  �i�f�o�j�v����
     */
    public static final String GP_PLAN = "gp_plan";
    
    /**
     * gp_target_figure:  �i�f�o�j�ڕW�z
     */
    public static final String GP_TARGET_FIGURE = "gp_target_figure";
    
    /**
     * gp_target_year:  �i�f�o�j�ڕW�N
     */
    public static final String GP_TARGET_YEAR = "gp_target_year";
    
    /**
     * gp_target_month:  �i�f�o�j�ڕW��
     */
    public static final String GP_TARGET_MONTH = "gp_target_month";
    
    /**
     * gp_installment_figure:  �i�f�o�j�ϗ��z
     */
    public static final String GP_INSTALLMENT_FIGURE = "gp_installment_figure";
    
    /**
     * gp_deposit_cycle:  �i�f�o�j��������
     */
    public static final String GP_DEPOSIT_CYCLE = "gp_deposit_cycle";
    
    /**
     * gp_payment_root:  �i�f�o�j��n�o�H
     */
    public static final String GP_PAYMENT_ROOT = "gp_payment_root";
    
    /**
     * gp_reinvest_div:  �i�f�o�j�ē����敪
     */
    public static final String GP_REINVEST_DIV = "gp_reinvest_div";
    
    /**
     * gp_tax_div:  �i�f�o�j�ŋ敪
     */
    public static final String GP_TAX_DIV = "gp_tax_div";
    
    /**
     * gp_taxfree_limit:  �i�f�o�j�i�D�j���x�z
     */
    public static final String GP_TAXFREE_LIMIT = "gp_taxfree_limit";
    
    /**
     * gp_special_taxfree_limit:  �i�f�o�j�i���D�j���x�z
     */
    public static final String GP_SPECIAL_TAXFREE_LIMIT = "gp_special_taxfree_limit";
    
    /**
     * gp_subscr_summary:  �i�f�o�j�����E�v
     */
    public static final String GP_SUBSCR_SUMMARY = "gp_subscr_summary";
    
    /**
     * gp_product_code:  �i�f�o�j�����R�[�h
     */
    public static final String GP_PRODUCT_CODE = "gp_product_code";
    
    /**
     * gp_mortgage_customer:  �i�f�o�j�S�ۋq
     */
    public static final String GP_MORTGAGE_CUSTOMER = "gp_mortgage_customer";
    
    /**
     * gp_mix_customer:  �i�f�o�j�~�b�N�X�q
     */
    public static final String GP_MIX_CUSTOMER = "gp_mix_customer";
    
    /**
     * gp_contract:  �i�f�o�j�_��
     */
    public static final String GP_CONTRACT = "gp_contract";
    
    /**
     * stk_voucher_div:  �i���O���j�쐬�敪
     */
    public static final String STK_VOUCHER_DIV = "stk_voucher_div";
    
    /**
     * stk_taxation_tran_div:  �i���O���j���n
     */
    public static final String STK_TAXATION_TRAN_DIV = "stk_taxation_tran_div";
    
    /**
     * stk_address_line_kana:  �i���O���j�Z���i�J�i�j
     */
    public static final String STK_ADDRESS_LINE_KANA = "stk_address_line_kana";
    
    /**
     * stk_transfer_div:  �i���O���j����
     */
    public static final String STK_TRANSFER_DIV = "stk_transfer_div";
    
    /**
     * stk_fin_institution_code:  �i���O���j��s�R�[�h
     */
    public static final String STK_FIN_INSTITUTION_CODE = "stk_fin_institution_code";
    
    /**
     * stk_fin_branch_code:  �i���O���j�x�X�R�[�h
     */
    public static final String STK_FIN_BRANCH_CODE = "stk_fin_branch_code";
    
    /**
     * stk_fin_save_div:  �i���O���j�a���敪
     */
    public static final String STK_FIN_SAVE_DIV = "stk_fin_save_div";
    
    /**
     * stk_fin_account_no:  �i���O���j�����ԍ�
     */
    public static final String STK_FIN_ACCOUNT_NO = "stk_fin_account_no";
    
    /**
     * brokerage_trader_code:  ����ƈ��҃R�[�h
     */
    public static final String BROKERAGE_TRADER_CODE = "brokerage_trader_code";
    
    /**
     * ext_item_div11:  �e�Њg�����ځi�敪11�j
     */
    public static final String EXT_ITEM_DIV11 = "ext_item_div11";
    
    /**
     * ext_item_div12:  �e�Њg�����ځi�敪12�j
     */
    public static final String EXT_ITEM_DIV12 = "ext_item_div12";
    
    /**
     * ext_item_div13:  �e�Њg�����ځi�敪13�j
     */
    public static final String EXT_ITEM_DIV13 = "ext_item_div13";
    
    /**
     * ext_item_div14:  �e�Њg�����ځi�敪14�j
     */
    public static final String EXT_ITEM_DIV14 = "ext_item_div14";
    
    /**
     * ext_item_div15:  �e�Њg�����ځi�敪15�j
     */
    public static final String EXT_ITEM_DIV15 = "ext_item_div15";

    /**
     * foreign_account_no:	�����ԍ��i�O�݁j
     */
    public static final String FOREIGN_ACCOUNT_NO = "foreign_account_no";
    
    /**
     * foreign_account_name:  �������`�l�i�O�݁j
     */
    public static final String FOREIGN_ACCOUNT_NAME = "foreign_account_name";
    
    /**
     * foreign_account_name_eng:	�������`�l�p���i�O�݁j
     */
    public static final String FOREIGN_ACCOUNT_NAME_ENG = "foreign_account_name_eng";
    
    /**
     * foreign_save_div:	�a���敪�i�O�݁j
     */
    public static final String FOREIGN_SAVE_DIV = "foreign_save_div";

    /**
     * delete_flag�폜�t���O
     */
    public static final String DELETE_FLAG = "delete_flag";

    /**
     * delete_timestamp�폜����
     */
    public static final String DELETE_TIMESTAMP = "delete_timestamp";

    /**
     * print_flag����t���O
     */
    public static final String PRINT_FLAG = "print_flag";

    /**
     * receipt_flag��̃t���O
     */
    public static final String RECEIPT_FLAG = "receipt_flag";

    /**
     * agreement_flag�����t���O
     */
    public static final String AGREEMENT_FLAG = "agreement_flag";

    /**
     * foreign_flag�O���l�t���O
     */
    public static final String FOREIGN_FLAG = "foreign_flag";

    /**
     * agency_acc_name_kana1�t���K�i1
     */
    public static final String AGENCY_ACC_NAME_KANA1 = "agency_acc_name_kana1";

    /**
     * agency_acc_name_kana2�t���K�i2
     */
    public static final String AGENCY_ACC_NAME_KANA2 = "agency_acc_name_kana2";

    /**
     * agency_acc_name1����1
     */
    public static final String AGENCY_ACC_NAME1 = "agency_acc_name1";

    /**
     * agency_acc_name2����2
     */
    public static final String AGENCY_ACC_NAME2 = "agency_acc_name2";

    /**
     * agency_address_line1�Z��1
     */
    public static final String AGENCY_ADDRESS_LINE1 = "agency_address_line1";

    /**
     * agency_address_line2�Z��2
     */
    public static final String AGENCY_ADDRESS_LINE2 = "agency_address_line2";

    /**
     * agency_rep_post��\�҂̖�E
     */
    public static final String AGENCY_REP_POST = "agency_rep_post";

    /**
     * agency_rep_name_kana1��\�҂̃t���K�i1
     */
    public static final String AGENCY_REP_NAME_KANA1 = "agency_rep_name_kana1";

    /**
     * agency_rep_name_kana2��\�҂̃t���K�i2
     */
    public static final String AGENCY_REP_NAME_KANA2 = "agency_rep_name_kana2";

    /**
     * agency_rep_name1��\�҂̎���1
     */
    public static final String AGENCY_REP_NAME1 = "agency_rep_name1";

    /**
     * agency_rep_name2��\�҂̎���2
     */
    public static final String AGENCY_REP_NAME2 = "agency_rep_name2";
}
@
