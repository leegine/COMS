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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設見込客列物理名WEB3AccountOpenExpAccountOpenSymbolNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 郭英 (中訊) 新規作成
                   2006/07/13 周捷 (中訊) 仕様変更 モデル078
                   2006/08/14 李俊 (中訊) 仕様変更 モデル087
Revesion History : 2009/08/12 張騰宇(中訊) ＤＢレイアウト053、056
*/
package webbroker3.accountopen.define;

/**
 * 口座開設見込客列物理名
 * 
 * @@author 郭英(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenExpAccountOpenSymbolNameDef
{
    /**
     * institution_code:  証券会社コード
     */
    public static final String INSTITUTION_CODE = "institution_code";

    /**
     * institution_id:  証券会社ID
     */
    public static final String INSTITUTION_ID = "institution_id";

    /**
     * branch_id:  部店ＩＤ
     */
    public static final String BRANCH_ID = "branch_id";

    /**
     * branch_code:  部店コード
     */
    public static final String BRANCH_CODE = "branch_code";

    /**
     * acc_open_request_number:  識別コード
     */
    public static final String ACC_OPEN_REQUEST_NUMBER = "acc_open_request_number";

    /**
     * account_code:  口座コード
     */
    public static final String ACCOUNT_CODE = "account_code";

    /**
     * sonar_trader_code:  扱者コード（SONAR）
     */
    public static final String SONAR_TRADER_CODE = "sonar_trader_code";

    /**
     * ex_account_flag:  既存口座フラグ
     */
    public static final String EX_ACCOUNT_FLAG = "ex_account_flag";

    /**
     * ex_branch_name:  既存口座部店名
     */
    public static final String EX_BRANCH_NAME = "ex_branch_name";

    /**
     * ex_account_code:  既存口座コード
     */
    public static final String EX_ACCOUNT_CODE = "ex_account_code";

    /**
     * account_div:  口座区分
     */
    public static final String ACCOUNT_DIV = "account_div";

    /**
     * order_div:  入力区分
     */
    public static final String ORDER_DIV = "order_div";

    /**
     * infomation_claim_datetime:  資料請求日時
     */
    public static final String INFOMATION_CLAIM_DATETIME = "infomation_claim_datetime";

    /**
     * account_open_date:  口座登録日
     */
    public static final String ACCOUNT_OPEN_DATE = "account_open_date";

    /**
     * initial_password:  初期パスワード
     */
    public static final String INITIAL_PASSWORD = "initial_password";

    /**
     * family_name:  顧客姓（漢字）
     */
    public static final String FAMILY_NAME = "family_name";

    /**
     * given_name:  顧客名（漢字）
     */
    public static final String GIVEN_NAME = "given_name";

    /**
     * family_name_alt1:  顧客姓（カナ）
     */
    public static final String FAMILY_NAME_ALT1 = "family_name_alt1";

    /**
     * given_name_alt1:  顧客名（カナ）
     */
    public static final String GIVEN_NAME_ALT1 = "given_name_alt1";

    /**
     * sex:  性別
     */
    public static final String SEX = "sex";

    /**
     * era_born:  生年月日年号
     */
    public static final String ERA_BORN = "era_born";

    /**
     * born_date:  生年月日
     */
    public static final String BORN_DATE = "born_date";

    /**
     * email_address:  emailアドレス
     */
    public static final String EMAIL_ADDRESS = "email_address";

    /**
     * email_address_alt1:  emailアドレス１
     */
    public static final String EMAIL_ADDRESS_ALT1 = "email_address_alt1";

    /**
     * zip_code:  郵便番号
     */
    public static final String ZIP_CODE = "zip_code";

    /**
     * address_line1:  住所１
     */
    public static final String ADDRESS_LINE1 = "address_line1";

    /**
     * address_line2:  住所２
     */
    public static final String ADDRESS_LINE2 = "address_line2";

    /**
     * address_line3:  住所３
     */
    public static final String ADDRESS_LINE3 = "address_line3";

    /**
     * address_line1_kana:  住所１（カナ）
     */
    public static final String ADDRESS_LINE1_KANA = "address_line1_kana";

    /**
     * address_line2_kana:  住所２（カナ）
     */
    public static final String ADDRESS_LINE2_KANA = "address_line2_kana";

    /**
     * address_line3_kana:  住所３（カナ）
     */
    public static final String ADDRESS_LINE3_KANA = "address_line3_kana";

    /**
     * telephone:  電話番号
     */
    public static final String TELEPHONE = "telephone";

    /**
     * mobile:  連絡先電話番号（携帯）
     */
    public static final String MOBILE = "mobile";

    /**
     * fax:  ＦＡＸ番号
     */
    public static final String FAX = "fax";

    /**
     * occupation_div:  職業区分
     */
    public static final String OCCUPATION_DIV = "occupation_div";

    /**
     * office:  勤務先名称
     */
    public static final String OFFICE = "office";

    /**
     * office_zip_code:  勤務先郵便番号
     */
    public static final String OFFICE_ZIP_CODE = "office_zip_code";

    /**
     * office_address:  勤務先住所
     */
    public static final String OFFICE_ADDRESS = "office_address";

    /**
     * office_telephone:  勤務先電話番号
     */
    public static final String OFFICE_TELEPHONE = "office_telephone";

    /**
     * office_fax:  勤務先FAX番号
     */
    public static final String OFFICE_FAX = "office_fax";

    /**
     * department:  所属部署
     */
    public static final String DEPARTMENT = "department";

    /**
     * post:  役職
     */
    public static final String POST = "post";

    /**
     * contact_address:  連絡先住所
     */
    public static final String CONTACT_ADDRESS = "contact_address";

    /**
     * contact_telephone:  連絡先電話番号
     */
    public static final String CONTACT_TELEPHONE = "contact_telephone";

    /**
     * family_relationship:  続柄区分
     */
    public static final String FAMILY_RELATIONSHIP = "family_relationship";

    /**
     * family_relationship_etc:  続柄区分（その他）
     */
    public static final String FAMILY_RELATIONSHIP_ETC = "family_relationship_etc";

    /**
     * householder:  世帯主名（漢字）
     */
    public static final String HOUSEHOLDER = "householder";

    /**
     * householder_kana:  世帯主名（カナ）
     */
    public static final String HOUSEHOLDER_KANA = "householder_kana";

    /**
     * householder_occupation_div:  世帯主職業区分
     */
    public static final String HOUSEHOLDER_OCCUPATION_DIV = "householder_occupation_div";

    /**
     * householder_office:  世帯主勤務先
     */
    public static final String HOUSEHOLDER_OFFICE = "householder_office";

    /**
     * householder_office_address:  世帯主勤務先住所
     */
    public static final String HOUSEHOLDER_OFFICE_ADDRESS = "householder_office_address";

    /**
     * householder_department:  世帯主所属部署
     */
    public static final String HOUSEHOLDER_DEPARTMENT = "householder_department";

    /**
     * householder_office_tel:  世帯主勤務先電話番号
     */
    public static final String HOUSEHOLDER_OFFICE_TEL = "householder_office_tel";

    /**
     * householder_office_fax:  世帯主勤務先FAX番号
     */
    public static final String HOUSEHOLDER_OFFICE_FAX = "householder_office_fax";

    /**
     * householder_post:  世帯主役職
     */
    public static final String HOUSEHOLDER_POST = "householder_post";

    /**
     * resident:  居住／非居住区分
     */
    public static final String RESIDENT = "resident";

    /**
     * transfer_div:  振替区分
     */
    public static final String TRANSFER_DIV = "transfer_div";

    /**
     * fin_institution_code:  銀行コード
     */
    public static final String FIN_INSTITUTION_CODE = "fin_institution_code";

    /**
     * fin_institution_name:  銀行名
     */
    public static final String FIN_INSTITUTION_NAME = "fin_institution_name";

    /**
     * fin_branch_code:  支店コード
     */
    public static final String FIN_BRANCH_CODE = "fin_branch_code";

    /**
     * fin_branch_name:  支店名
     */
    public static final String FIN_BRANCH_NAME = "fin_branch_name";

    /**
     * fin_save_div:  預金区分
     */
    public static final String FIN_SAVE_DIV = "fin_save_div";

    /**
     * fin_account_no:  口座番号
     */
    public static final String FIN_ACCOUNT_NO = "fin_account_no";

    /**
     * postal_save_code:  通帳記号
     */
    public static final String POSTAL_SAVE_CODE = "postal_save_code";

    /**
     * postal_save_no:  通帳番号
     */
    public static final String POSTAL_SAVE_NO = "postal_save_no";

    /**
     * fin_account_name:  口座名義人
     */
    public static final String FIN_ACCOUNT_NAME = "fin_account_name";

    /**
     * trans_commission:  振替手数料区分
     */
    public static final String TRANS_COMMISSION = "trans_commission";

    /**
     * experience_div_equity:  現物株式
     */
    public static final String EXPERIENCE_DIV_EQUITY = "experience_div_equity";

    /**
     * experience_div_margin:  信用取引
     */
    public static final String EXPERIENCE_DIV_MARGIN = "experience_div_margin";

    /**
     * experience_div_bond:  債券
     */
    public static final String EXPERIENCE_DIV_BOND = "experience_div_bond";

    /**
     * experience_div_wt:  転換社債
     */
    public static final String EXPERIENCE_DIV_WT = "experience_div_wt";

    /**
     * experience_div_fund_sk:  投資信託（株式）
     */
    public static final String EXPERIENCE_DIV_FUND_SK = "experience_div_fund_sk";

    /**
     * experience_div_fund_bd:  投資信託（公社債）
     */
    public static final String EXPERIENCE_DIV_FUND_BD = "experience_div_fund_bd";

    /**
     * experience_div_fo:  先物・オプション
     */
    public static final String EXPERIENCE_DIV_FO = "experience_div_fo";

    /**
     * experience_div_f_equity:  外国証券
     */
    public static final String EXPERIENCE_DIV_F_EQUITY = "experience_div_f_equity";

    /**
     * experience_div_etc:  その他
     */
    public static final String EXPERIENCE_DIV_ETC = "experience_div_etc";

    /**
     * experience_flag_equity:  現物株式フラグ
     */
    public static final String EXPERIENCE_FLAG_EQUITY = "experience_flag_equity";

    /**
     * experience_flag_margin:  信用取引フラグ
     */
    public static final String EXPERIENCE_FLAG_MARGIN = "experience_flag_margin";

    /**
     * experience_flag_bond:  債券フラグ
     */
    public static final String EXPERIENCE_FLAG_BOND = "experience_flag_bond";

    /**
     * experience_flag_wt:  転換社債フラグ
     */
    public static final String EXPERIENCE_FLAG_WT = "experience_flag_wt";

    /**
     * experience_flag_fund_sk:  投資信託（株式）フラグ
     */
    public static final String EXPERIENCE_FLAG_FUND_SK = "experience_flag_fund_sk";

    /**
     * experience_flag_fund_bd:  投資信託（公社債）フラグ
     */
    public static final String EXPERIENCE_FLAG_FUND_BD = "experience_flag_fund_bd";

    /**
     * experience_flag_fo:  先物・オプションフラグ
     */
    public static final String EXPERIENCE_FLAG_FO = "experience_flag_fo";

    /**
     * experience_flag_f_equity:  外国証券フラグ
     */
    public static final String EXPERIENCE_FLAG_F_EQUITY = "experience_flag_f_equity";

    /**
     * experience_flag_etc:  その他フラグ
     */
    public static final String EXPERIENCE_FLAG_ETC = "experience_flag_etc";

    /**
     * experience_from:  経験年数（自）
     */
    public static final String EXPERIENCE_FROM = "experience_from";

    /**
     * experience_to:  経験年数（至）
     */
    public static final String EXPERIENCE_TO = "experience_to";

    /**
     * interest_flag_equity:  現物株式フラグ
     */
    public static final String INTEREST_FLAG_EQUITY = "interest_flag_equity";

    /**
     * interest_flag_ministock:  株式ミニ投資フラグ
     */
    public static final String INTEREST_FLAG_MINISTOCK = "interest_flag_ministock";

    /**
     * interest_flag_margin:  信用取引フラグ
     */
    public static final String INTEREST_FLAG_MARGIN = "interest_flag_margin";

    /**
     * interest_flag_bond:  債券フラグ
     */
    public static final String INTEREST_FLAG_BOND = "interest_flag_bond";

    /**
     * interest_flag_fund:  投資信託フラグ
     */
    public static final String INTEREST_FLAG_FUND = "interest_flag_fund";

    /**
     * interest_flag_fo:  先物・オプションフラグ
     */
    public static final String INTEREST_FLAG_FO = "interest_flag_fo";

    /**
     * interest_flag_f_equity:  外国証券フラグ
     */
    public static final String INTEREST_FLAG_F_EQUITY = "interest_flag_f_equity";

    /**
     * interest_flag_etc:  その他フラグ
     */
    public static final String INTEREST_FLAG_ETC = "interest_flag_etc";

    /**
     * invest_purpose_div:  投資目的区分
     */
    public static final String INVEST_PURPOSE_DIV = "invest_purpose_div";

    /**
     * appli_motivat_div:  取引動機@区分
     */
    public static final String APPLI_MOTIVAT_DIV = "appli_motivat_div";

    /**
     * annual_income_div:  年収区分
     */
    public static final String ANNUAL_INCOME_DIV = "annual_income_div";

    /**
     * annual_income_from:  年収（自）
     */
    public static final String ANNUAL_INCOME_FROM = "annual_income_from";

    /**
     * annual_income_to:  年収（至）
     */
    public static final String ANNUAL_INCOME_TO = "annual_income_to";

    /**
     * asset_value_div:  金融資産区分
     */
    public static final String ASSET_VALUE_DIV = "asset_value_div";

    /**
     * asset_value_from:  金融資産（自）
     */
    public static final String ASSET_VALUE_FROM = "asset_value_from";

    /**
     * asset_value_to:  金融資産（至）
     */
    public static final String ASSET_VALUE_TO = "asset_value_to";

    /**
     * fund_budget_amount_div:  運用予定額
     */
    public static final String FUND_BUDGET_AMOUNT_DIV = "fund_budget_amount_div";

    /**
     * fund_budget_div:  資金の性格
     */
    public static final String FUND_BUDGET_DIV = "fund_budget_div";

    /**
     * fund_budget_etc:  資金の性格（その他）
     */
    public static final String FUND_BUDGET_ETC = "fund_budget_etc";

    /**
     * id_confirm_flag:  検索用区分
     */
    public static final String ID_CONFIRM_FLAG = "id_confirm_flag";

    /**
     * id_confirm_doc_div:  本人確認書類区分
     */
    public static final String ID_CONFIRM_DOC_DIV = "id_confirm_doc_div";

    /**
     * id_confirm_doc_etc:  本人確認書類区分（その他）
     */
    public static final String ID_CONFIRM_DOC_ETC = "id_confirm_doc_etc";

    /**
     * special_acc:  特定口座区分
     */
    public static final String SPECIAL_ACC = "special_acc";

    /**
     * special_acc_margin:  信用取引特定口座区分
     */
    public static final String SPECIAL_ACC_MARGIN = "special_acc_margin";

    /**
     * insider_flag:  内部者登録フラグ
     */
    public static final String INSIDER_FLAG = "insider_flag";

    /**
     * product_name:  内部者銘柄名
     */
    public static final String PRODUCT_NAME = "product_name";

    /**
     * send_zip_code:  送付先郵便番号
     */
    public static final String SEND_ZIP_CODE = "send_zip_code";

    /**
     * send_address_line1:  送付先住所１
     */
    public static final String SEND_ADDRESS_LINE1 = "send_address_line1";

    /**
     * send_address_line2:  送付先住所２
     */
    public static final String SEND_ADDRESS_LINE2 = "send_address_line2";

    /**
     * send_address_line3:  送付先住所３
     */
    public static final String SEND_ADDRESS_LINE3 = "send_address_line3";

    /**
     * ext_item_div1:  各社拡張項目（区分１）
     */
    public static final String EXT_ITEM_DIV1 = "ext_item_div1";

    /**
     * ext_item_div2:  各社拡張項目（区分２）
     */
    public static final String EXT_ITEM_DIV2 = "ext_item_div2";

    /**
     * ext_item_div3:  各社拡張項目（区分３）
     */
    public static final String EXT_ITEM_DIV3 = "ext_item_div3";

    /**
     * ext_item_div4:  各社拡張項目（区分４）
     */
    public static final String EXT_ITEM_DIV4 = "ext_item_div4";

    /**
     * ext_item_div5:  各社拡張項目（区分５）
     */
    public static final String EXT_ITEM_DIV5 = "ext_item_div5";

    /**
     * ext_item_div6:  各社拡張項目（区分６）
     */
    public static final String EXT_ITEM_DIV6 = "ext_item_div6";

    /**
     * ext_item_div7:  各社拡張項目（区分７）
     */
    public static final String EXT_ITEM_DIV7 = "ext_item_div7";

    /**
     * ext_item_div8:  各社拡張項目（区分８）
     */
    public static final String EXT_ITEM_DIV8 = "ext_item_div8";

    /**
     * ext_item_div9:  各社拡張項目（区分９）
     */
    public static final String EXT_ITEM_DIV9 = "ext_item_div9";

    /**
     * ext_item_div10:  各社拡張項目（区分１０）
     */
    public static final String EXT_ITEM_DIV10 = "ext_item_div10";

    /**
     * ext_item_flag1:  各社拡張項目（フラグ1）
     */
    public static final String EXT_ITEM_FLAG1 = "ext_item_flag1";

    /**
     * ext_item_flag2:  各社拡張項目（フラグ2）
     */
    public static final String EXT_ITEM_FLAG2 = "ext_item_flag2";

    /**
     * ext_item_flag3:  各社拡張項目（フラグ3）
     */
    public static final String EXT_ITEM_FLAG3 = "ext_item_flag3";

    /**
     * ext_item_flag4:  各社拡張項目（フラグ4）
     */
    public static final String EXT_ITEM_FLAG4 = "ext_item_flag4";

    /**
     * ext_item_flag5:  各社拡張項目（フラグ5）
     */
    public static final String EXT_ITEM_FLAG5 = "ext_item_flag5";

    /**
     * ext_item_flag6:  各社拡張項目（フラグ６）
     */
    public static final String EXT_ITEM_FLAG6 = "ext_item_flag6";

    /**
     * ext_item_flag7:  各社拡張項目（フラグ７）
     */
    public static final String EXT_ITEM_FLAG7 = "ext_item_flag7";

    /**
     * ext_item_flag8:  各社拡張項目（フラグ８）
     */
    public static final String EXT_ITEM_FLAG8 = "ext_item_flag8";

    /**
     * ext_item_flag9:  各社拡張項目（フラグ９）
     */
    public static final String EXT_ITEM_FLAG9 = "ext_item_flag9";

    /**
     * ext_item_flag10:  各社拡張項目（フラグ１０）
     */
    public static final String EXT_ITEM_FLAG10 = "ext_item_flag10";

    /**
     * ext_item_text1:  各社拡張項目（テキスト１）
     */
    public static final String EXT_ITEM_TEXT1 = "ext_item_text1";

    /**
     * ext_item_text2:  各社拡張項目（テキスト２）
     */
    public static final String EXT_ITEM_TEXT2 = "ext_item_text2";

    /**
     * ext_item_text3:  各社拡張項目（テキスト３）
     */
    public static final String EXT_ITEM_TEXT3 = "ext_item_text3";

    /**
     * ext_item_text4:  各社拡張項目（テキスト４）
     */
    public static final String EXT_ITEM_TEXT4 = "ext_item_text4";

    /**
     * ext_item_text5:  各社拡張項目（テキスト５）
     */
    public static final String EXT_ITEM_TEXT5 = "ext_item_text5";

    /**
     * ext_item_text6:  各社拡張項目（テキスト６）
     */
    public static final String EXT_ITEM_TEXT6 = "ext_item_text6";

    /**
     * ext_item_text7:  各社拡張項目（テキスト７）
     */
    public static final String EXT_ITEM_TEXT7 = "ext_item_text7";

    /**
     * ext_item_text8:  各社拡張項目（テキスト８）
     */
    public static final String EXT_ITEM_TEXT8 = "ext_item_text8";

    /**
     * ext_item_text9:  各社拡張項目（テキスト９）
     */
    public static final String EXT_ITEM_TEXT9 = "ext_item_text9";

    /**
     * ext_item_text10:  各社拡張項目（テキスト１０）
     */
    public static final String EXT_ITEM_TEXT10 = "ext_item_text10";

    /**
     * last_updater:  更新者コード
     */
    public static final String LAST_UPDATER = "last_updater";

    /**
     * created_timestamp:  作成日時
     */
    public static final String CREATED_TIMESTAMP = "created_timestamp";

    /**
     * last_updated_timestamp:  更新日時
     */
    public static final String LAST_UPDATED_TIMESTAMP = "last_updated_timestamp";
    
    /**
     * creator:  作成者コード
     */
    public static final String CREATOR = "creator";
    
    /**
     * exclusive_use_account_no:  専用振込先口座番号
     */
    public static final String EXCLUSIVE_USE_ACCOUNT_NO = "exclusive_use_account_no";
    
    /**
     * send_timestamp:  HULFT送信日時
     */
    public static final String SEND_TIMESTAMP = "send_timestamp";
    
    /**
     * real_name_voucher_div:  顧客正式名称作成区分
     */
    public static final String REAL_NAME_VOUCHER_DIV = "real_name_voucher_div";
    
    /**
     * real_name1:  顧客正式名称１
     */
    public static final String REAL_NAME1 = "real_name1";
    
    /**
     * real_name2:  顧客正式名称2
     */
    public static final String REAL_NAME2 = "real_name2";
    
    /**
     * insider_voucher_div:  （内部者）作成区分
     */
    public static final String INSIDER_VOUCHER_DIV = "insider_voucher_div";
    
    /**
     * insider_product_code:  （内部者）銘柄コード
     */
    public static final String INSIDER_PRODUCT_CODE = "insider_product_code";
    
    /**
     * insider_relation_div:  （内部者）関係区分
     */
    public static final String INSIDER_RELATION_DIV = "insider_relation_div";
    
    /**
     * insider_officer_name:  （内部者）役員名
     */
    public static final String INSIDER_OFFICER_NAME = "insider_officer_name";
    
    /**
     * insider_post_code:  （内部者）役職名コード
     */
    public static final String INSIDER_POST_CODE = "insider_post_code";
    
    /**
     * insider_post_name:  （内部者）役職名
     */
    public static final String INSIDER_POST_NAME = "insider_post_name";
    
    /**
     * gp_voucher_div:  （ＧＰ）作成区分
     */
    public static final String GP_VOUCHER_DIV = "gp_voucher_div";
    
    /**
     * gp_course:  （ＧＰ）コース
     */
    public static final String GP_COURSE = "gp_course";
    
    /**
     * gp_plan:  （ＧＰ）プラン
     */
    public static final String GP_PLAN = "gp_plan";
    
    /**
     * gp_target_figure:  （ＧＰ）目標額
     */
    public static final String GP_TARGET_FIGURE = "gp_target_figure";
    
    /**
     * gp_target_year:  （ＧＰ）目標年
     */
    public static final String GP_TARGET_YEAR = "gp_target_year";
    
    /**
     * gp_target_month:  （ＧＰ）目標月
     */
    public static final String GP_TARGET_MONTH = "gp_target_month";
    
    /**
     * gp_installment_figure:  （ＧＰ）積立額
     */
    public static final String GP_INSTALLMENT_FIGURE = "gp_installment_figure";
    
    /**
     * gp_deposit_cycle:  （ＧＰ）入金周期
     */
    public static final String GP_DEPOSIT_CYCLE = "gp_deposit_cycle";
    
    /**
     * gp_payment_root:  （ＧＰ）受渡経路
     */
    public static final String GP_PAYMENT_ROOT = "gp_payment_root";
    
    /**
     * gp_reinvest_div:  （ＧＰ）再投資区分
     */
    public static final String GP_REINVEST_DIV = "gp_reinvest_div";
    
    /**
     * gp_tax_div:  （ＧＰ）税区分
     */
    public static final String GP_TAX_DIV = "gp_tax_div";
    
    /**
     * gp_taxfree_limit:  （ＧＰ）（優）限度額
     */
    public static final String GP_TAXFREE_LIMIT = "gp_taxfree_limit";
    
    /**
     * gp_special_taxfree_limit:  （ＧＰ）（特優）限度額
     */
    public static final String GP_SPECIAL_TAXFREE_LIMIT = "gp_special_taxfree_limit";
    
    /**
     * gp_subscr_summary:  （ＧＰ）加入摘要
     */
    public static final String GP_SUBSCR_SUMMARY = "gp_subscr_summary";
    
    /**
     * gp_product_code:  （ＧＰ）銘柄コード
     */
    public static final String GP_PRODUCT_CODE = "gp_product_code";
    
    /**
     * gp_mortgage_customer:  （ＧＰ）担保客
     */
    public static final String GP_MORTGAGE_CUSTOMER = "gp_mortgage_customer";
    
    /**
     * gp_mix_customer:  （ＧＰ）ミックス客
     */
    public static final String GP_MIX_CUSTOMER = "gp_mix_customer";
    
    /**
     * gp_contract:  （ＧＰ）契約書
     */
    public static final String GP_CONTRACT = "gp_contract";
    
    /**
     * stk_voucher_div:  （上場外株）作成区分
     */
    public static final String STK_VOUCHER_DIV = "stk_voucher_div";
    
    /**
     * stk_taxation_tran_div:  （上場外株）譲渡
     */
    public static final String STK_TAXATION_TRAN_DIV = "stk_taxation_tran_div";
    
    /**
     * stk_address_line_kana:  （上場外株）住所（カナ）
     */
    public static final String STK_ADDRESS_LINE_KANA = "stk_address_line_kana";
    
    /**
     * stk_transfer_div:  （上場外株）送金
     */
    public static final String STK_TRANSFER_DIV = "stk_transfer_div";
    
    /**
     * stk_fin_institution_code:  （上場外株）銀行コード
     */
    public static final String STK_FIN_INSTITUTION_CODE = "stk_fin_institution_code";
    
    /**
     * stk_fin_branch_code:  （上場外株）支店コード
     */
    public static final String STK_FIN_BRANCH_CODE = "stk_fin_branch_code";
    
    /**
     * stk_fin_save_div:  （上場外株）預金区分
     */
    public static final String STK_FIN_SAVE_DIV = "stk_fin_save_div";
    
    /**
     * stk_fin_account_no:  （上場外株）口座番号
     */
    public static final String STK_FIN_ACCOUNT_NO = "stk_fin_account_no";
    
    /**
     * brokerage_trader_code:  仲介業扱者コード
     */
    public static final String BROKERAGE_TRADER_CODE = "brokerage_trader_code";
    
    /**
     * ext_item_div11:  各社拡張項目（区分11）
     */
    public static final String EXT_ITEM_DIV11 = "ext_item_div11";
    
    /**
     * ext_item_div12:  各社拡張項目（区分12）
     */
    public static final String EXT_ITEM_DIV12 = "ext_item_div12";
    
    /**
     * ext_item_div13:  各社拡張項目（区分13）
     */
    public static final String EXT_ITEM_DIV13 = "ext_item_div13";
    
    /**
     * ext_item_div14:  各社拡張項目（区分14）
     */
    public static final String EXT_ITEM_DIV14 = "ext_item_div14";
    
    /**
     * ext_item_div15:  各社拡張項目（区分15）
     */
    public static final String EXT_ITEM_DIV15 = "ext_item_div15";

    /**
     * foreign_account_no:	口座番号（外貨）
     */
    public static final String FOREIGN_ACCOUNT_NO = "foreign_account_no";
    
    /**
     * foreign_account_name:  口座名義人（外貨）
     */
    public static final String FOREIGN_ACCOUNT_NAME = "foreign_account_name";
    
    /**
     * foreign_account_name_eng:	口座名義人英数（外貨）
     */
    public static final String FOREIGN_ACCOUNT_NAME_ENG = "foreign_account_name_eng";
    
    /**
     * foreign_save_div:	預金区分（外貨）
     */
    public static final String FOREIGN_SAVE_DIV = "foreign_save_div";

    /**
     * delete_flag削除フラグ
     */
    public static final String DELETE_FLAG = "delete_flag";

    /**
     * delete_timestamp削除日時
     */
    public static final String DELETE_TIMESTAMP = "delete_timestamp";

    /**
     * print_flag印刷フラグ
     */
    public static final String PRINT_FLAG = "print_flag";

    /**
     * receipt_flag受領フラグ
     */
    public static final String RECEIPT_FLAG = "receipt_flag";

    /**
     * agreement_flag承諾フラグ
     */
    public static final String AGREEMENT_FLAG = "agreement_flag";

    /**
     * foreign_flag外国人フラグ
     */
    public static final String FOREIGN_FLAG = "foreign_flag";

    /**
     * agency_acc_name_kana1フリガナ1
     */
    public static final String AGENCY_ACC_NAME_KANA1 = "agency_acc_name_kana1";

    /**
     * agency_acc_name_kana2フリガナ2
     */
    public static final String AGENCY_ACC_NAME_KANA2 = "agency_acc_name_kana2";

    /**
     * agency_acc_name1名称1
     */
    public static final String AGENCY_ACC_NAME1 = "agency_acc_name1";

    /**
     * agency_acc_name2名称2
     */
    public static final String AGENCY_ACC_NAME2 = "agency_acc_name2";

    /**
     * agency_address_line1住所1
     */
    public static final String AGENCY_ADDRESS_LINE1 = "agency_address_line1";

    /**
     * agency_address_line2住所2
     */
    public static final String AGENCY_ADDRESS_LINE2 = "agency_address_line2";

    /**
     * agency_rep_post代表者の役職
     */
    public static final String AGENCY_REP_POST = "agency_rep_post";

    /**
     * agency_rep_name_kana1代表者のフリガナ1
     */
    public static final String AGENCY_REP_NAME_KANA1 = "agency_rep_name_kana1";

    /**
     * agency_rep_name_kana2代表者のフリガナ2
     */
    public static final String AGENCY_REP_NAME_KANA2 = "agency_rep_name_kana2";

    /**
     * agency_rep_name1代表者の氏名1
     */
    public static final String AGENCY_REP_NAME1 = "agency_rep_name1";

    /**
     * agency_rep_name2代表者の氏名2
     */
    public static final String AGENCY_REP_NAME2 = "agency_rep_name2";
}
@
