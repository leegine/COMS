head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.39.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccountOpenOutputItemSymbolNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出力項目物理名 定数定義インタフェイス(WEB3AccountOpenOutputItemSymbolNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 李頴淵 (中訊) 新規作成
                   2006/07/13 周捷 (中訊) 仕様変更 モデル078
Revesion History : 2009/08/14 武波 (中訊) 仕様変更 モデル171
*/
package webbroker3.accountopen.define;

/**
 * 出力項目物理名 定数定義インタフェイス
 * 
 * @@author 李頴淵(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenOutputItemSymbolNameDef
{
    /**
     * request_code：　@データコード
     */
    public static final String REQUEST_CODE = "request_code";
    
    /**
     * institution_code：　@証券会社コード
     */
    public static final String INSTITUTION_CODE = "institution_code";
    
    /**
     * branch_code：　@部店コード
     */
    public static final String BRANCH_CODE = "branch_code";
    
    /**
     * account_code：　@顧客コード
     */
    public static final String ACCOUNT_CODE = "account_code";
    
    /**
     * trader_code：　@扱者コード
     */
    public static final String TRADER_CODE = "trader_code";
    
    /**
     * acc_open_request_number：　@識別コード（口座開設見込客）
     */
    public static final String ACC_OPEN_REQUEST_NUMBER = "acc_open_request_number";
    
    /**
     * serial_no：　@伝票通番
     */
    public static final String SERIAL_NO = "serial_no";
    
    /**
     * regist_div：　@登録区分
     */
    public static final String REGIST_DIV = "regist_div";
    
    /**
     * auto_product_code：　@自動運用銘柄コード
     */
    public static final String AUTO_PRODUCT_CODE = "auto_product_code";
    
    /**
     * mrf_product_code：　@MRF銘柄コード
     */
    public static final String MRF_PRODUCT_CODE = "mrf_product_code";
    
    /**
     * taxfree_limit：　@マル優限度額
     */
    public static final String TAXFREE_LIMIT = "taxfree_limit";
    
    /**
     * suspended_auto_buy_div：　@自動買付保留区分
     */
    public static final String SUSPENDED_AUTO_BUY_DIV = "suspended_auto_buy_div";
    
    /**
     * status：　@処理区分
     */
    public static final String STATUS = "status";
    
    /**
     * password：　@暗証番号
     */
    public static final String PASSWORD = "password";
    
    /**
     * modified_name：　@訂正氏名
     */
    public static final String MODIFIED_NAME = "modified_name";
    
    /**
     * regist_delete_div：　@発行取消区分
     */
    public static final String REGIST_DELETE_DIV = "regist_delete_div";
    
    /**
     * data_class：　@デ－タ種別
     */
    public static final String DATA_CLASS = "data_class";
    
    /**
     * voucher_name：　@データコード（伝票名）
     */
    public static final String VOUCHER_NAME = "voucher_name";
    
    /**
     * regist_date：　@申込年月日
     */
    public static final String REGIST_DATE = "regist_date";
    
    /**
     * pub_reason_div：　@発行理由
     */
    public static final String PUB_REASON_DIV = "pub_reason_div";
    
    /**
     * ref_number：　@整理番号
     */
    public static final String REF_NUMBER = "ref_number";
    
    /**
     * card_type：　@カードタイプ
     */
    public static final String CARD_TYPE = "card_type";
    
    /**
     * online_batch_div：　@オンライン・バッチ区分
     */
    public static final String ONLINE_BATCH_DIV = "online_batch_div";
    
    /**
     * seq_no：　@SEQ_NO
     */
    public static final String SEQ_NO = "seq_no";
    
    /**
     * trading_account_no：　@取引口座Ｎｏ．
     */
    public static final String TRADING_ACCOUNT_NO = "trading_account_no";
    
    /**
     * account_open_div：　@口座開設区分
     */
    public static final String ACCOUNT_OPEN_DIV = "account_open_div";
    
    /**
     * cont_mrg_div：　@契約書徴収区分
     */
    public static final String CONT_MRG_DIV = "cont_mrg_div";
    
    /**
     * account_name_kana：　@顧客名（ｶﾅ）
     */
    public static final String ACCOUNT_NAME_KANA = "account_name_kana";

    /**
     * account_name_kana1：　@フリガナ1
     */
    public static final String ACCOUNT_NAME_KANA1 = "account_name_kana1";

    /**
     * account_name_kana2：　@フリガナ2
     */
    public static final String ACCOUNT_NAME_KANA2 = "account_name_kana2";

    /**
     * account_name：　@顧客名（漢字）
     */
    public static final String ACCOUNT_NAME = "account_name";

    /**
     * account_name1：　@名称1
     */
    public static final String ACCOUNT_NAME1 = "account_name1";

    /**
     * account_name2：　@名称2
     */
    public static final String ACCOUNT_NAME2 = "account_name2";

    /**
     * zip_code：　@郵便番号
     */
    public static final String ZIP_CODE = "zip_code";

    /**
     * zip_code1：　@郵便番号（親）
     */
    public static final String ZIP_CODE1 = "zip_code1";

    /**
     * zip_code2：　@郵便番号（子）
     */
    public static final String ZIP_CODE2 = "zip_code2";

    /**
     * address_line1：　@住所１
     */
    public static final String ADDRESS_LINE1 = "address_line1";
    
    /**
     * address_line1_kana：　@住所１（カナ）
     */
    public static final String ADDRESS_LINE1_KANA = "address_line1_kana";
    
    /**
     * address_line2：　@住所２
     */
    public static final String ADDRESS_LINE2 = "address_line2";
    
    /**
     * address_line2_kana：　@住所２（カナ）
     */
    public static final String ADDRESS_LINE2_KANA = "address_line2_kana";
    
    /**
     * address_line3：　@住所３
     */
    public static final String ADDRESS_LINE3 = "address_line3";
    
    /**
     * address_line3_kana：　@住所３（カナ）
     */
    public static final String ADDRESS_LINE3_KANA = "address_line3_kana";
    
    /**
     * telephone：　@電話番号
     */
    public static final String TELEPHONE = "telephone";
    
    /**
     * contact_address：　@連絡先住所
     */
    public static final String CONTACT_ADDRESS = "contact_address";
    
    /**
     * contact_telephone：　@連絡先電話番号
     */
    public static final String CONTACT_TELEPHONE = "contact_telephone";
    
    /**
     * ex_branch_name：　@移管前部店名
     */
    public static final String EX_BRANCH_NAME = "ex_branch_name";
    
    /**
     * ex_account_code：　@移管前口座コード
     */
    public static final String EX_ACCOUNT_CODE = "ex_account_code";
    
    /**
     * occupation_div：　@職業区分
     */
    public static final String OCCUPATION_DIV = "occupation_div";
    
    /**
     * era_born：　@生年月日年号
     */
    public static final String ERA_BORN = "era_born";
    
    /**
     * born_date：　@生年月日
     */
    public static final String BORN_DATE = "born_date";
    
    /**
     * sex：　@性別
     */
    public static final String SEX = "sex";
    
    /**
     * document：　@資料
     */
    public static final String DOCUMENT = "document";
    
    /**
     * unknown_address：　@住所不明
     */
    public static final String UNKNOWN_ADDRESS = "unknown_address";
    
    /**
     * report：　@報告書
     */
    public static final String REPORT = "report";
    
    /**
     * resident：　@居住／非居住区分
     */
    public static final String RESIDENT = "resident";
    
    /**
     * taxation_div：　@課税区分
     */
    public static final String TAXATION_DIV = "taxation_div";
    
    /**
     * forign_taxation_div：　@課税区分（外国）
     */
    public static final String FORIGN_TAXATION_DIV = "forign_taxation_div";
    
    /**
     * account_div：　@顧客区分
     */
    public static final String ACCOUNT_DIV = "account_div";
    
    /**
     * account_open_div1：　@１（保護預り）
     */
    public static final String ACCOUNT_OPEN_DIV1 = "account_open_div1";
    
    /**
     * account_open_div2：　@２（積立投資）
     */
    public static final String ACCOUNT_OPEN_DIV2 = "account_open_div2";
    
    /**
     * account_open_div3：　@３（信用取引）
     */
    public static final String ACCOUNT_OPEN_DIV3 = "account_open_div3";
    
    /**
     * account_open_div4：　@４（発行日取引）
     */
    public static final String ACCOUNT_OPEN_DIV4 = "account_open_div4";
    
    /**
     * account_open_div5：　@５（外国証券）
     */
    public static final String ACCOUNT_OPEN_DIV5 = "account_open_div5";
    
    /**
     * account_open_div6：　@６（金述取引）
     */
    public static final String ACCOUNT_OPEN_DIV6 = "account_open_div6";
    
    /**
     * account_open_div7：　@７（優）
     */
    public static final String ACCOUNT_OPEN_DIV7 = "account_open_div7";
    
    /**
     * account_open_div8：　@８（総）
     */
    public static final String ACCOUNT_OPEN_DIV8 = "account_open_div8";
    
    /**
     * account_open_div9：　@９（債券先物）
     */
    public static final String ACCOUNT_OPEN_DIV9 = "account_open_div9";
    
    /**
     * account_open_div10：　@１０（株式先物）
     */
    public static final String ACCOUNT_OPEN_DIV10 = "account_open_div10";
    
    /**
     * account_open_div11：　@１１（株先オプション）
     */
    public static final String ACCOUNT_OPEN_DIV11 = "account_open_div11";
    
    /**
     * account_open_div12：　@１２（TBONDオプション）
     */
    public static final String ACCOUNT_OPEN_DIV12 = "account_open_div12";
    
    /**
     * account_open_div13：　@１３（株券オプション）
     */
    public static final String ACCOUNT_OPEN_DIV13 = "account_open_div13";
    
    /**
     * taxation_appl_div：　@申請区分（申告分離課税）
     */
    public static final String TAXATION_APPL_DIV = "taxation_appl_div";
    
    /**
     * taxation_tran_div：　@譲渡課税区分
     */
    public static final String TAXATION_TRAN_DIV = "taxation_tran_div";
    
    /**
     * send_div：　@発送区分
     */
    public static final String SEND_DIV = "send_div";
    
    /**
     * trust_via_div：　@信託経由区分
     */
    public static final String TRUST_VIA_DIV = "trust_via_div";
    
    /**
     * correct_div1：　@１（保護預り）
     */
    public static final String CORRECT_DIV1 = "correct_div1";
    
    /**
     * correct_div2：　@２（外国証券）
     */
    public static final String CORRECT_DIV2 = "correct_div2";
    
    /**
     * correct_div3：　@３（金地金）
     */
    public static final String CORRECT_DIV3 = "correct_div3";
    
    /**
     * correct_div4：　@４（金貯蓄）
     */
    public static final String CORRECT_DIV4 = "correct_div4";
    
    /**
     * correct_div5：　@５（株累投）
     */
    public static final String CORRECT_DIV5 = "correct_div5";
    
    /**
     * ifo_acc_open_div_tokyo：　@先物OP口座開設区分（東証）
     */
    public static final String IFO_ACC_OPEN_DIV_TOKYO = "ifo_acc_open_div_tokyo";
    
    /**
     * ifo_acc_open_div_osaka：　@先物OP口座開設区分（大証）
     */
    public static final String IFO_ACC_OPEN_DIV_OSAKA = "ifo_acc_open_div_osaka";
    
    /**
     * ifo_acc_open_div_nagoya：　@先物OP口座開設区分（名証）
     */
    public static final String IFO_ACC_OPEN_DIV_NAGOYA = "ifo_acc_open_div_nagoya";
    
    /**
     * trading_e_report_div：　@取引報告書
     */
    public static final String TRADING_E_REPORT_DIV = "trading_e_report_div";
    
    /**
     * inv_e_report_div：　@投信運用報告書
     */
    public static final String INV_E_REPORT_DIV = "inv_e_report_div";
    
    /**
     * refund_e_report_div：　@分配金・償還金
     */
    public static final String REFUND_E_REPORT_DIV = "refund_e_report_div";
    
    /**
     * e_report_div1：　@予備1
     */
    public static final String E_REPORT_DIV1 = "e_report_div1";
    
    /**
     * e_report_div2：　@予備2
     */
    public static final String E_REPORT_DIV2 = "e_report_div2";
    
    /**
     * e_report_div3：　@予備3
     */
    public static final String E_REPORT_DIV3 = "e_report_div3";
    
    /**
     * pos_report_term_div：　@定期
     */
    public static final String POS_REPORT_TERM_DIV = "pos_report_term_div";
    
    /**
     * pos_report_cycle_div：　@都度
     */
    public static final String POS_REPORT_CYCLE_DIV = "pos_report_cycle_div";
    
    /**
     * pos_report_certif_depo_div：　@預り証
     */
    public static final String POS_REPORT_CERTIF_DEPO_DIV = "pos_report_certif_depo_div";
    
    /**
     * pos_report_acc_state_div：　@計算書
     */
    public static final String POS_REPORT_ACC_STATE_DIV = "pos_report_acc_state_div";
    
    /**
     * equity_tax_div：　@現物株式特定口座区分
     */
    public static final String EQUITY_TAX_DIV = "equity_tax_div";
    
    /**
     * equity_tax_div_next：　@現物株式特定口座区分（次年）
     */
    public static final String EQUITY_TAX_DIV_NEXT = "equity_tax_div_next";
    
    /**
     * equity_sp_acc_open_dat：　@現物株式特定口座開設日
     */
    public static final String EQUITY_SP_ACC_OPEN_DAT = "equity_sp_acc_open_dat";
    
    /**
     * margin_tax_div：　@信用取引特定口座区分
     */
    public static final String MARGIN_TAX_DIV = "margin_tax_div";
    
    /**
     * margin_tax_div_next：　@信用取引特定口座区分（次年）
     */
    public static final String MARGIN_TAX_DIV_NEXT = "margin_tax_div_next";
    
    /**
     * margin_sp_acc_open_dat：　@信用取引特定口座開設日
     */
    public static final String MARGIN_SP_ACC_OPEN_DAT = "margin_sp_acc_open_dat";
    
    /**
     * sp_mng_acc_open_div：　@特定管理口座開設区分
     */
    public static final String SP_MNG_ACC_OPEN_DIV = "sp_mng_acc_open_div";
    
    /**
     * category_no：　@カテゴリＮＯ
     */
    public static final String CATEGORY_NO = "category_no";
    
    /**
     * confirm_div：　@確認区分
     */
    public static final String CONFIRM_DIV = "confirm_div";
    
    /**
     * note：　@備考
     */
    public static final String NOTE = "note";
    
    /**
     * correct_date：　@徴収日
     */
    public static final String CORRECT_DATE = "correct_date";
    
    /**
     * transfer_range：　@振出範囲
     */
    public static final String TRANSFER_RANGE = "transfer_range";
    
    /**
     * product_type_code_spec：　@指定商品コード
     */
    public static final String PRODUCT_TYPE_CODE_SPEC = "product_type_code_spec";
    
    /**
     * product_code_spec：　@指定銘柄コード
     */
    public static final String PRODUCT_CODE_SPEC = "product_code_spec";
    
    /**
     * transfer_div：　@振替区分
     */
    public static final String TRANSFER_DIV = "transfer_div";
    
    /**
     * trans_commission：　@振替手数料区分
     */
    public static final String TRANS_COMMISSION = "trans_commission";
    
    /**
     * trans_deal_div：　@取扱区分
     */
    public static final String TRANS_DEAL_DIV = "trans_deal_div";
    
    /**
     * fin_institution_code：　@振込先銀行コード
     */
    public static final String FIN_INSTITUTION_CODE = "fin_institution_code";
    
    /**
     * fin_branch_code：　@振込先銀行支店コード
     */
    public static final String FIN_BRANCH_CODE = "fin_branch_code";
    
    /**
     * fin_save_div：　@預金種類
     */
    public static final String FIN_SAVE_DIV = "fin_save_div";
    
    /**
     * fin_account_no：　@口座番号
     */
    public static final String FIN_ACCOUNT_NO = "fin_account_no";
    
    /**
     * fin_account_name：　@口座名義
     */
    public static final String FIN_ACCOUNT_NAME = "fin_account_name";
    
    /**
     * postal_save_code：　@通帳記号
     */
    public static final String POSTAL_SAVE_CODE = "postal_save_code";
    
    /**
     * postal_save_no：　@通帳番号
     */
    public static final String POSTAL_SAVE_NO = "postal_save_no";
    
    /**
     * org_consign_div：　@機@構委託
     */
    public static final String ORG_CONSIGN_DIV = "org_consign_div";
    
    /**
     * stockholder_report_div：　@株主報告
     */
    public static final String STOCKHOLDER_REPORT_DIV = "stockholder_report_div";
    
    /**
     * inquiry_report_div：　@照会報告
     */
    public static final String INQUIRY_REPORT_DIV = "inquiry_report_div";
    
    /**
     * id_attribute_div：　@本人属性区分
     */
    public static final String ID_ATTRIBUTE_DIV = "id_attribute_div";
    
    /**
     * trading_div：　@対象取引区分
     */
    public static final String TRADING_DIV = "trading_div";
    
    /**
     * confirm_way_div：　@確認方法@区分
     */
    public static final String CONFIRM_WAY_DIV = "confirm_way_div";
    
    /**
     * confirm_doc_div：　@確認書類区分
     */
    public static final String CONFIRM_DOC_DIV = "confirm_doc_div";
    
    /**
     * address_confirm_doc：　@住所確認書類区分
     */
    public static final String ADDRESS_CONFIRM_DOC = "address_confirm_doc";
    
    /**
     * confirm_date：　@確認日
     */
    public static final String CONFIRM_DATE = "confirm_date";
    
    /**
     * appli_motivat_div：　@取引動機@区分
     */
    public static final String APPLI_MOTIVAT_DIV = "appli_motivat_div";
    
    /**
     * invest_purpose_div：　@投資目的区分
     */
    public static final String INVEST_PURPOSE_DIV = "invest_purpose_div";
    
    /**
     * experience_equity：　@投資経験（現物株式）
     */
    public static final String EXPERIENCE_EQUITY = "experience_equity";
    
    /**
     * experience_margin：　@投資経験（信用取引）
     */
    public static final String EXPERIENCE_MARGIN = "experience_margin";
    
    /**
     * experience_bond：　@投資経験（債券）
     */
    public static final String EXPERIENCE_BOND = "experience_bond";
    
    /**
     * experience_fund：　@投資経験（投資信託）
     */
    public static final String EXPERIENCE_FUND = "experience_fund";
    
    /**
     * experience_fo：　@投資経験（先物・オプション）
     */
    public static final String EXPERIENCE_FO = "experience_fo";
    
    /**
     * experience_f_equity：　@投資経験（外国証券）
     */
    public static final String EXPERIENCE_F_EQUITY = "experience_f_equity";
    
    /**
     * experience_etc：　@投資経験（その他）
     */
    public static final String EXPERIENCE_ETC = "experience_etc";
    
    /**
     * experience_from：　@経験年数（自）
     */
    public static final String EXPERIENCE_FROM = "experience_from";
    
    /**
     * experience_to：　@経験年数（至）
     */
    public static final String EXPERIENCE_TO = "experience_to";
    
    /**
     * equity_trade_div：　@取引種類（現物株式）
     */
    public static final String EQUITY_TRADE_DIV = "equity_trade_div";
    
    /**
     * margin_trade_diiv：　@取引種類（信用取引）
     */
    public static final String MARGIN_TRADE_DIIV = "margin_trade_diiv";
    
    /**
     * bond_trade_div：　@取引種類（債券）
     */
    public static final String BOND_TRADE_DIV = "bond_trade_div";
    
    /**
     * fund_trade_div：　@取引種類（投資信託）
     */
    public static final String FUND_TRADE_DIV = "fund_trade_div";
    
    /**
     * fo_trade_div：　@取引種類（先物・オプション）
     */
    public static final String FO_TRADE_DIV = "fo_trade_div";
    
    /**
     * f_equity_trade_div：　@取引種類（外国証券）
     */
    public static final String F_EQUITY_TRADE_DIV = "f_equity_trade_div";
    
    /**
     * etc_trade_div：　@取引種類（その他）
     */
    public static final String ETC_TRADE_DIV = "etc_trade_div";
    
    /**
     * annual_income_from：　@年収（自）
     */
    public static final String ANNUAL_INCOME_FROM = "annual_income_from";
    
    /**
     * annual_income_to：　@年収（至）
     */
    public static final String ANNUAL_INCOME_TO = "annual_income_to";
    
    /**
     * asset_value_from：　@金融資産（自）
     */
    public static final String ASSET_VALUE_FROM = "asset_value_from";
    
    /**
     * asset_value_to：　@金融資産（至）
     */
    public static final String ASSET_VALUE_TO = "asset_value_to";
    
    /**
     * settlement_div：　@決済方法@
     */
    public static final String SETTLEMENT_DIV = "settlement_div";
    
    /**
     * pc_div：　@パソコン機@種
     */
    public static final String PC_DIV = "pc_div";
    
    /**
     * apply_div：　@申込区分
     */
    public static final String APPLY_DIV = "apply_div";
    
    /**
     * apply_date：　@申込日
     */
    public static final String APPLY_DATE = "apply_date";
    
    /**
     * course:  コース
     */
    public static final String COURSE = "course";
    
    /**
     * plan:  プラン
     */
    public static final String PLAN = "plan";
    
    /**
     * target_figure:  目標額
     */
    public static final String TARGET_FIGURE = "target_figure";
    
    /**
     * target_year:  目標年
     */
    public static final String TARGET_YEAR = "target_year";
    
    /**
     * target_month:  目標月
     */
    public static final String TARGET_MONTH = "target_month";
    
    /**
     * installment_figure:  積立額
     */
    public static final String INSTALLMENT_FIGURE = "installment_figure";
    
    /**
     * deposit_cycle:  入金周期
     */
    public static final String DEPOSIT_CYCLE = "deposit_cycle";
    
    /**
     * payment_root:  受渡経路
     */
    public static final String PAYMENT_ROOT = "payment_root";
    
    /**
     * reinvest_div:  再投資区分
     */
    public static final String REINVEST_DIV = "reinvest_div";
    
    /**
     * tax_div:  税区分
     */
    public static final String TAX_DIV = "tax_div";
    
    /**
     * special_taxfree_limit:  （特優）限度額
     */
    public static final String SPECIAL_TAXFREE_LIMIT = "special_taxfree_limit";
    
    /**
     * subscr_summary:  加入摘要
     */
    public static final String SUBSCR_SUMMARY = "subscr_summary";
    
    /**
     * gp_product_code:  銘柄コード
     */
    public static final String GP_PRODUCT_CODE = "gp_product_code";
    
    /**
     * mortgage_customer:  担保客
     */
    public static final String MORTGAGE_CUSTOMER = "mortgage_customer";
    
    /**
     * mix_customer:  ミックス客
     */
    public static final String MIX_CUSTOMER = "mix_customer";
    
    /**
     * contract:  契約書
     */
    public static final String CONTRACT = "contract";
    
    /**
     * brokerage_trader_code:  仲介業扱者コード
     */
    public static final String BROKERAGE_TRADER_CODE = "brokerage_trader_code";
    
    /**
     * send_timestamp:  送信日時
     */
    public static final String SEND_TIMESTAMP = "send_timestamp";
    
    /**
     * address_line_kana:  住所（カナ）
     */
    public static final String ADDRESS_LINE_KANA = "address_line_kana"; 
    
    /**
     * transfer_div:  送金
     */
    public static final String STK_TRANSFER_DIV = "transfer_div";
    
    /**
     * fin_institution_code:  銀行コード
     */
    public static final String BANK_CODE = "fin_institution_code";

    /**
     * fin_branch_code:  支店コード
     */
    public static final String OTHER_BRANCH_CODE = "fin_branch_code";
 
    /**
     * product_code:  銘柄コード
     */
    public static final String PRODUCT_CODE = "product_code";
    
    /**
     * relation_div:  関係区分
     */
    public static final String RELATION_DIV = "relation_div";
    
    /**
     * officer_name:  役員名
     */
    public static final String OFFICER_NAME = "officer_name";
    
    /**
     * post_code:  役職名コード
     */
    public static final String POST_CODE = "post_code";
    
    /**
     * post_name:  役職名
     */
    public static final String POST_NAME = "post_name";
    
    /**
     * real_name1:  顧客名称(1)
     */
    public static final String REAL_NAME1 = "real_name1";
    
    /**
     * real_name2:  顧客名称(2)
     */
    public static final String REAL_NAME2 = "real_name2";
    
    /**
     * other_contact_telephone:　@その他連絡先電話番号
     */
    public static final String OTHER_CONTACT_TELEPHONE = "other_contact_telephone";
    
    /**
     * currency_code:　@通貨コード
     */
    public static final String CURENCY_CODE = "currency_code";
    
    /**
     * ammount:　@預金
     */
    public static final String AMMOUNT = "ammount";
    
    /**
     * fee:　@手数料
     */
    public static final String FEE = "fee";
    
    /**
     * account_name_eng:　@口座名義人（英数）
     */
    public static final String ACCOUNT_NAME_ENG = "account_name_eng";
    
    /**
     * bank_name:　@銀行名
     */
    public static final String BANK_NAME = "bank_name";
    
    /**
     * bank_branch_name:　@支店名
     */
    public static final String BANK_BRANCH_NAME = "bank_branch_name";
    
    /**
     * extra:　@予備
     */
    public static final String EXTRA = "extra";

    /**
     * proam_div : プロ・アマ区分
     */
    public static final String  PROAM_DIV = "proam_div";
    
    /**
     * foreigner_div_broadcast: 外国人区分（放送法@）
     */
    public static final String  FOREIGNER_DIV_BROADCAST = "foreigner_div_broadcast";
    
    /**
     * foreigner_div_aviation : 外国人区分（航空法@）
     */
    public static final String FOREIGNER_DIV_AVIATION = "foreigner_div_aviation";
    
    /**
     * foreigner_div_ntt: 外国人区分（NTT法@）   
     */
    public static final String  FOREIGNER_DIV_NTT = "foreigner_div_ntt";
    
    /**
     * dividend_transfer_div : 配当金振込指定区分
     */
    public static final String  DIVIDEND_TRANSFER_DIV = "dividend_transfer_div";
    
    /**
     * agent_div_permanent : 代理人区分（常任代理人）
     */
    public static final String  AGENT_DIV_PERMANENT = "agent_div_permanent";
    
    /**
     * agent_div_legal : 代理人区分（法@定代理人）
     */
    public static final String AGENT_DIV_LEGAL = "agent_div_legal";
    
    /**
     * self_div : 自己区分
     */
    public static final String SELF_DIV = "self_div";
    
    /**
     * tax_code : 税コード
     */
    public static final String TAX_CODE = "tax_code";

    /**
     * represent_post : 代表者の役職
     */
    public static final String REPRESENT_POST = "represent_post";

    /**
     * represent_name_kana1 : 代表者のフリガナ1
     */
    public static final String REPRESENT_NAME_KANA1 = "represent_name_kana1";

    /**
     * represent_name_kana2 : 代表者のフリガナ2
     */
    public static final String REPRESENT_NAME_KANA2 = "represent_name_kana2";

    /**
     * represent_name1 : 代表者の氏名1
     */
    public static final String REPRESENT_NAME1 = "represent_name1";

    /**
     * represent_name2 : 代表者の氏名2
     */
    public static final String REPRESENT_NAME2 = "represent_name2";

    /**
     * receipt_kana : フリガナ
     */
    public static final String RECEIPT_KANA = "receipt_kana";

    /**
     * receipt_name1 : 氏名1
     */
    public static final String RECEIPT_NAME1 = "receipt_name1";

    /**
     * receipt_name2 : 氏名2
     */
    public static final String RECEIPT_NAME2 = "receipt_name2";

    /**
     * receipt_fin_institution : 金融機@関等コード
     */
    public static final String RECEIPT_FIN_INSTITUTION = "receipt_fin_institution";

    /**
     * receipt_fin_branch : 店舗コード
     */
    public static final String RECEIPT_FIN_BRANCH = "receipt_fin_branch";

    /**
     * receipt_fin_acc_type : 預金種目
     */
    public static final String RECEIPT_FIN_ACC_TYPE = "receipt_fin_acc_type";

    /**
     * receipt_fin_acc_no : 口座番号
     */
    public static final String RECEIPT_FIN_ACC_NO = "receipt_fin_acc_no";

    /**
     * receipt_fin_acc_div : 名義人区分
     */
    public static final String RECEIPT_FIN_ACC_DIV = "receipt_fin_acc_div";

    /**
     * div:区分
     */
    public static final String DIV = "div";

    /**
     * relation:関係
     */
    public static final String RELATION = "relation";

    /**
     * confirm_way:確認方法@
     */
    public static final String CONFIRM_WAY = "confirm_way";

    /**
     * confirm_doc:確認書類
     */
    public static final String CONFIRM_DOC = "confirm_doc";

    /**
     * add_confirm_doc:住所確認書類
     */
    public static final String ADD_CONFIRM_DOC = "add_confirm_doc";

    /**
     * charge_name:担当者（代理人）名
     */
    public static final String CHARGE_NAME = "charge_name";

    /**
     * telephone1:連絡先電話番号(市外局番）
     */
    public static final String TELEPHONE1 = "telephone1";

    /**
     * telephone2:連絡先電話番号(局番）
     */
    public static final String TELEPHONE2 = "telephone2";

    /**
     * telephone3:連絡先電話番号(番号）
     */
    public static final String TELEPHONE3 = "telephone3";

    /**
     * charge_confirm_date:確認日
     */
    public static final String CHARGE_CONFIRM_DATE = "charge_confirm_date";
}
@
