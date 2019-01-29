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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込情報項目名(WEB3AccOpenApplyInfoItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 郭英 (中訊) 新規作成
                   2006/07/13 周捷 (中訊) 仕様変更 モデル078
                   2006/08/21 李俊 (中訊) 仕様変更 モデル087
Revesion History : 2009/08/12 張騰宇(中訊) ＤＢレイアウト053、056
**/

package webbroker3.accountopen.define;

/**
 * 口座開設申込情報項目名
 * 
 * @@author 郭英(中訊)
 * @@version 1.0
 */
public interface WEB3AccOpenApplyInfoItemDef
{
    /**
     * infoClaimDate:  資料請求日
     */
    public static final String INFO_CLAIM_DATE = "infoClaimDate";

    /**
     * updateDate:  更新日
     */
    public static final String UPDATE_DATE = "updateDate";

    /**
     * updaterCode:  更新者コード
     */
    public static final String UPDATER_CODE = "updaterCode";

    /**
     * requestNumber:  識別コード
     */
    public static final String REQUEST_NUMBER = "requestNumber";
   
    /**
     * requestNumber:  入力区分
     */
    public static final String INPUT_DIV = "inputDiv";
    
    /**
     * creatorCode:  作成者コード
     */
    public static final String CREATOR_CODE = "creatorCode";

    /**
     * exAccountFlag:  既存口座フラグ
     */
    public static final String EX_ACCOUNT_FLAG = "exAccountFlag";

    /**
     * exAccountCode:  既存口座コード
     */
    public static final String EX_ACCOUNT_CODE = "exAccountCode";

    /**
     * exAccountBranchName:  既存口座部店名
     */
    public static final String EX_ACCOUNT_BRANCH_NAME = "exAccountBranchName";

    /**
     * institutionCode:  証券会社コード
     */
    public static final String INSTITUTION_CODE = "institutionCode";

    /**
     * branchCode:  部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountType:  口座区分
     */
    public static final String ACCOUNT_TYPE = "accountType";

    /**
     * accountCode:  顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * traderCode:  扱者コード（SONAR）
     */
    public static final String TRADER_CODE = "traderCode";

    /**
     * mailAddress:  メールアドレス
     */
    public static final String MAIL_ADDRESS = "mailAddress";

    /**
     * mobileMailAddress:  メールアドレス（携帯）
     */
    public static final String MOBILE_MAIL_ADDRESS = "mobileMailAddress";

    /**
     * accountFamilyName:  顧客姓（漢字）
     */
    public static final String ACCOUNT_FAMILY_NAME = "accountFamilyName";

    /**
     * accountName:  顧客名（漢字）
     */
    public static final String ACCOUNT_NAME = "accountName";

    /**
     * accountFamilyNameKana:  顧客姓（カナ）
     */
    public static final String ACCOUNT_FAMILY_NAME_KANA = "accountFamilyNameKana";

    /**
     * accountNameKana:  顧客名（カナ）
     */
    public static final String ACCOUNT_NAME_KANA = "accountNameKana";

    /**
     * eraBorn:  生年月日年号
     */
    public static final String ERA_BORN = "eraBorn";

    /**
     * bornDate:  生年月日
     */
    public static final String BORN_DATE = "bornDate";

    /**
     * sex:  性別
     */
    public static final String SEX = "sex";

    /**
     * password:  暗証番号
     */
    public static final String PASSWORD = "password";

    /**
     * zipCode:  郵便番号
     */
    public static final String ZIP_CODE = "zipCode";

    /**
     * addressKana1:  住所1（カナ）
     */
    public static final String ADDRESS_KANA1 = "addressKana1";

    /**
     * addressKana2:  住所2（カナ）
     */
    public static final String ADDRESS_KANA2 = "addressKana2";

    /**
     * addressKana3:  住所3（カナ）
     */
    public static final String ADDRESS_KANA3 = "addressKana3";

    /**
     * address1:  住所1（漢字）
     */
    public static final String ADDRESS1 = "address1";

    /**
     * address2:  住所2（漢字）
     */
    public static final String ADDRESS2 = "address2";

    /**
     * address3:  住所3（漢字）
     */
    public static final String ADDRESS3 = "address3";

    /**
     * telephone:  電話番号
     */
    public static final String TELEPHONE = "telephone";

    /**
     * mobileTelephone:  携帯番号
     */
    public static final String MOBILE_TELEPHONE = "mobileTelephone";

    /**
     * faxTelephone:  ＦＡＸ番号
     */
    public static final String FAX_TELEPHONE = "faxTelephone";

    /**
     * officeInfo.occupationDiv:  勤務先情報.職業区分
     */
    public static final String OFFICE_INFO_OCCUPATION_DIV = "officeInfo.occupationDiv";

    /**
     * officeInfo.officeName:  勤務先情報.勤務先名称
     */
    public static final String OFFICE_INFO_OFFICE_NAME = "officeInfo.officeName";

    /**
     * officeInfo.officeZipCode:  勤務先情報.勤務先郵便番号
     */
    public static final String OFFICE_INFO_OFFICE_ZIP_CODE = "officeInfo.officeZipCode";

    /**
     * officeInfo.officeAdress:  勤務先情報.勤務先住所
     */
    public static final String OFFICE_INFO_OFFICE_ADRESS = "officeInfo.officeAdress";

    /**
     * officeInfo.officeTelephone:  勤務先情報.勤務先電話番号
     */
    public static final String OFFICE_INFO_OFFICE_TELEPHONE = "officeInfo.officeTelephone";

    /**
     * officeInfo.officeFaxTelephone:  勤務先情報.勤務先ＦＡＸ番号
     */
    public static final String OFFICE_INFO_OFFICE_FAX_TELEPHONE = "officeInfo.officeFaxTelephone";

    /**
     * officeInfo.department:  勤務先情報.所属部署
     */
    public static final String OFFICE_INFO_DEPARTMENT = "officeInfo.department";

    /**
     * officeInfo.position:  勤務先情報.役職名
     */
    public static final String OFFICE_INFO_POSITION = "officeInfo.position";

    /**
     * contactAddress:  連絡先住所
     */
    public static final String CONTACT_ADDRESS = "contactAddress";

    /**
     * contactTelephone:  連絡先電話番号
     */
    public static final String CONTACT_TELEPHONE = "contactTelephone";

    /**
     * familyRelationDiv:  続柄区分
     */
    public static final String FAMILY_RELATION_DIV = "familyRelationDiv";

    /**
     * familyRelationEtc:  続柄（その他）
     */
    public static final String FAMILY_RELATION_ETC = "familyRelationEtc";

    /**
     * houseHolderName:  世帯主名（漢字）
     */
    public static final String HOUSE_HOLDER_NAME = "houseHolderName";

    /**
     * houseHolderNameKana:  世帯主名（カナ）
     */
    public static final String HOUSE_HOLDER_NAME_KANA = "houseHolderNameKana";

    /**
     * houseHolderOfficeInfo.occupationDiv:  世帯主勤務先情報.職業区分
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OCCUPATION_DIV = "houseHolderOfficeInfo.occupationDiv";

    /**
     * houseHolderOfficeInfo.officeName:  世帯主勤務先情報.勤務先名称
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_NAME = "houseHolderOfficeInfo.officeName";

    /**
     * houseHolderOfficeInfo.officeZipCode:  世帯主勤務先情報.勤務先郵便番号
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_ZIP_CODE = "houseHolderOfficeInfo.officeZipCode";

    /**
     * houseHolderOfficeInfo.officeAdress:  世帯主勤務先情報.勤務先住所
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_ADRESS = "houseHolderOfficeInfo.officeAdress";

    /**
     * houseHolderOfficeInfo.officeTelephone:  世帯主勤務先情報.勤務先電話番号
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_TELEPHONE = "houseHolderOfficeInfo.officeTelephone";

    /**
     * houseHolderOfficeInfo.officeFaxTelephone:  世帯主勤務先情報.勤務先ＦＡＸ番号
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_OFFICE_FAX_TELEPHONE = "houseHolderOfficeInfo.officeFaxTelephone";

    /**
     * houseHolderOfficeInfo.department:  世帯主勤務先情報.所属部署
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_DEPARTMENT = "houseHolderOfficeInfo.department";

    /**
     * houseHolderOfficeInfo.position:  世帯主勤務先情報.役職名
     */
    public static final String HOUSE_HOLDER_OFFICE_INFO_POSITION = "houseHolderOfficeInfo.position";

    /**
     * residentDiv:  居住／非居住区分
     */
    public static final String RESIDENT_DIV = "residentDiv";

    /**
     * transferDiv:  振替区分
     */
    public static final String TRANSFER_DIV = "transferDiv";

    /**
     * financialAccountName:  口座名義人
     */
    public static final String FINANCIAL_ACCOUNT_NAME = "financialAccountName";

    /**
     * investPurposeDiv:  投資目的区分
     */
    public static final String INVEST_PURPOSE_DIV = "investPurposeDiv";

    /**
     * appliMotivatDiv:  取引動機@区分
     */
    public static final String APPLI_MOTIVAT_DIV = "appliMotivatDiv";

    /**
     * annualIncomeDiv:  年収区分
     */
    public static final String ANNUAL_INCOME_DIV = "annualIncomeDiv";

    /**
     * assetValueDiv:  金融資産区分
     */
    public static final String ASSET_VALUE_DIV = "assetValueDiv";

    /**
     * fundBundgetAmountDiv:  運用予定額区分
     */
    public static final String FUND_BUNDGET_AMOUNT_DIV = "fundBundgetAmountDiv";

    /**
     * fundBundgetDiv:  資産の性格区分
     */
    public static final String FUND_BUNDGET_DIV = "fundBundgetDiv";

    /**
     * fundBundgetEtc:  資産の性格（その他）
     */
    public static final String FUND_BUNDGET_ETC = "fundBundgetEtc";

    /**
     * searchDiv:  検索用区分
     */
    public static final String SEARCH_DIV = "searchDiv";

    /**
     * idConfirmDocDiv:  本人確認書類区分
     */
    public static final String ID_CONFIRM_DOC_DIV = "idConfirmDocDiv";

    /**
     * idConfirmDocEtc:  本人確認書類（その他）
     */
    public static final String ID_CONFIRM_DOC_ETC = "idConfirmDocEtc";

    /**
     * equityTaxType:  現物株式口座区分
     */
    public static final String EQUITY_TAX_TYPE = "equityTaxType";

    /**
     * marginTaxType:  信用取引口座区分
     */
    public static final String MARGIN_TAX_TYPE = "marginTaxType";

    /**
     * insiderFlag:  内部者登録フラグ
     */
    public static final String INSIDER_FLAG = "insiderFlag";

    /**
     * insiderProductName:  内部者銘柄名
     */
    public static final String INSIDER_PRODUCT_NAME = "insiderProductName";

    /**
     * sendZipCode:  送付先郵便番号
     */
    public static final String SEND_ZIP_CODE = "sendZipCode";

    /**
     * sendAddress1:  送付先住所１
     */
    public static final String SEND_ADDRESS1 = "sendAddress1";

    /**
     * sendAddress2:  送付先住所２
     */
    public static final String SEND_ADDRESS2 = "sendAddress2";

    /**
     * sendAddress3:  送付先住所３
     */
    public static final String SEND_ADDRESS3 = "sendAddress3";

    /**
     * extItemDiv1:  区分１
     */
    public static final String EXT_ITEM_DIV1 = "extItemDiv1";

    /**
     * extItemDiv2:  区分２
     */
    public static final String EXT_ITEM_DIV2 = "extItemDiv2";

    /**
     * extItemDiv3:  区分３
     */
    public static final String EXT_ITEM_DIV3 = "extItemDiv3";

    /**
     * extItemDiv4:  区分４
     */
    public static final String EXT_ITEM_DIV4 = "extItemDiv4";

    /**
     * extItemDiv5:  区分５
     */
    public static final String EXT_ITEM_DIV5 = "extItemDiv5";

    /**
     * extItemDiv6:  区分６
     */
    public static final String EXT_ITEM_DIV6 = "extItemDiv6";

    /**
     * extItemDiv7:  区分７
     */
    public static final String EXT_ITEM_DIV7 = "extItemDiv7";

    /**
     * extItemDiv8:  区分８
     */
    public static final String EXT_ITEM_DIV8 = "extItemDiv8";

    /**
     * extItemDiv9:  区分９
     */
    public static final String EXT_ITEM_DIV9 = "extItemDiv9";

    /**
     * extItemDiv10:  区分１０
     */
    public static final String EXT_ITEM_DIV10 = "extItemDiv10";

    /**
     * extItemFlag1:  フラグ１
     */
    public static final String EXT_ITEM_FLAG1 = "extItemFlag1";

    /**
     * extItemFlag2:  フラグ２
     */
    public static final String EXT_ITEM_FLAG2 = "extItemFlag2";

    /**
     * extItemFlag3:  フラグ３
     */
    public static final String EXT_ITEM_FLAG3 = "extItemFlag3";

    /**
     * extItemFlag4:  フラグ４
     */
    public static final String EXT_ITEM_FLAG4 = "extItemFlag4";

    /**
     * extItemFlag5:  フラグ５
     */
    public static final String EXT_ITEM_FLAG5 = "extItemFlag5";

    /**
     * extItemFlag6:  フラグ６
     */
    public static final String EXT_ITEM_FLAG6 = "extItemFlag6";

    /**
     * extItemFlag7:  フラグ７
     */
    public static final String EXT_ITEM_FLAG7 = "extItemFlag7";

    /**
     * extItemFlag8:  フラグ８
     */
    public static final String EXT_ITEM_FLAG8 = "extItemFlag8";

    /**
     * extItemFlag9:  フラグ９
     */
    public static final String EXT_ITEM_FLAG9 = "extItemFlag9";

    /**
     * extItemFlag10:  フラグ１０
     */
    public static final String EXT_ITEM_FLAG10 = "extItemFlag10";

    /**
     * extItemText1:  テキスト１
     */
    public static final String EXT_ITEM_TEXT1 = "extItemText1";

    /**
     * extItemText2:  テキスト２
     */
    public static final String EXT_ITEM_TEXT2 = "extItemText2";

    /**
     * extItemText3:  テキスト３
     */
    public static final String EXT_ITEM_TEXT3 = "extItemText3";

    /**
     * extItemText4:  テキスト４
     */
    public static final String EXT_ITEM_TEXT4 = "extItemText4";

    /**
     * extItemText5:  テキスト５
     */
    public static final String EXT_ITEM_TEXT5 = "extItemText5";

    /**
     * extItemText6:  テキスト６
     */
    public static final String EXT_ITEM_TEXT6 = "extItemText6";

    /**
     * extItemText7:  テキスト７
     */
    public static final String EXT_ITEM_TEXT7 = "extItemText7";

    /**
     * extItemText8:  テキスト８
     */
    public static final String EXT_ITEM_TEXT8 = "extItemText8";

    /**
     * extItemText9:  テキスト９
     */
    public static final String EXT_ITEM_TEXT9 = "extItemText9";

    /**
     * extItemText10:  テキスト１０
     */
    public static final String EXT_ITEM_TEXT10 = "extItemText10";

    /**
     * transferBankInfo.financialInstitutionCode:  振込先銀行情報.銀行コード
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_CODE = "transferBankInfo.financialInstitutionCode";

    /**
     * transferBankInfo.financialInstitutionName:  振込先銀行情報.銀行名
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_INSTITUTION_NAME = "transferBankInfo.financialInstitutionName";

    /**
     * transferBankInfo.financialBranchCode:  振込先銀行情報.支店コード
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_BRANCH_CODE = "transferBankInfo.financialBranchCode";

    /**
     * transferBankInfo.financialBranchName:  振込先銀行情報.支店名
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_BRANCH_NAME = "transferBankInfo.financialBranchName";

    /**
     * transferBankInfo.financialAccountDiv:  振込先銀行情報.預金区分
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_DIV = "transferBankInfo.financialAccountDiv";

    /**
     * transferBankInfo.financialAccountCode:  振込先銀行情報.口座番号
     */
    public static final String TRANSFER_BANK_INFO_FINANCIAL_ACCOUNT_CODE = "transferBankInfo.financialAccountCode";

    /**
     * transferBankInfo.transferCommissionDiv:  振込先銀行情報.振替手数料区分
     */
    public static final String TRANSFER_BANK_INFO_TRANSFER_COMMISSION_DIV = "transferBankInfo.transferCommissionDiv";

    /**
     * postalTransferInfo.passbookCode:  郵便振替情報.通帳番号
     */
    public static final String POSTAL_TRANSFER_INFO_PASSBOOK_CODE = "postalTransferInfo.passbookCode";

    /**
     * postalTransferInfo.passbookSign:  郵便振替情報.通帳記号
     */
    public static final String POSTAL_TRANSFER_INFO_PASSBOOK_SIGN = "postalTransferInfo.passbookSign";

    /**
     * investExpInfo.experienceEquityDiv:  投資経験情報.現物株式
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_EQUITY_DIV = "investExpInfo.experienceEquityDiv";

    /**
     * investExpInfo.experienceMarginDiv:  投資経験情報.信用取引
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_MARGIN_DIV = "investExpInfo.experienceMarginDiv";

    /**
     * investExpInfo.experienceBondDiv:  投資経験情報.債権
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_BOND_DIV = "investExpInfo.experienceBondDiv";

    /**
     * investExpInfo.experienceWtDiv:  投資経験情報.転換社債
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_WT_DIV = "investExpInfo.experienceWtDiv";

    /**
     * investExpInfo.experienceFundSkDiv:  投資経験情報.投資信託（株式）
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_FUND_SK_DIV = "investExpInfo.experienceFundSkDiv";

    /**
     * investExpInfo.experienceFundBdDiv:  投資経験情報.投資信託（債権）
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_FUND_BD_DIV = "investExpInfo.experienceFundBdDiv";

    /**
     * investExpInfo.experienceFoDiv:  投資経験情報.先物・オプション
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_FO_DIV = "investExpInfo.experienceFoDiv";

    /**
     * investExpInfo.experienceFEquityDiv:  投資経験情報.外国証券
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_F_EQUITY_DIV = "investExpInfo.experienceFEquityDiv";

    /**
     * investExpInfo.experienceEtcDiv:  投資経験情報.その他
     */
    public static final String INVEST_EXP_INFO_EXPERIENCE_ETC_DIV = "investExpInfo.experienceEtcDiv";

    /**
     * interestDealInfo.interestEquityFlag:  興味のある取引情報.現物株式フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_EQUITY_FLAG = "interestDealInfo.interestEquityFlag";

    /**
     * interestDealInfo.interestMstkFlag:  興味のある取引情報.株式ミニ投資フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_MSTK_FLAG = "interestDealInfo.interestMstkFlag";

    /**
     * interestDealInfo.interestMarginFlag:  興味のある取引情報.信用取引フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_MARGIN_FLAG = "interestDealInfo.interestMarginFlag";

    /**
     * interestDealInfo.interestBondFlag:  興味のある取引情報.債権フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_BOND_FLAG = "interestDealInfo.interestBondFlag";

    /**
     * interestDealInfo.interestFundFlag:  興味のある取引情報.投資信託フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_FUND_FLAG = "interestDealInfo.interestFundFlag";

    /**
     * interestDealInfo.interestFoFlag:  興味のある取引情報.先物・オプションフラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_FO_FLAG = "interestDealInfo.interestFoFlag";

    /**
     * interestDealInfo.interestFEquityFlag:  興味のある取引情報.外国証券フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_F_EQUITY_FLAG = "interestDealInfo.interestFEquityFlag";

    /**
     * interestDealInfo.interestEtcFlag:  興味のある取引情報.その他フラグ
     */
    public static final String INTEREST_DEAL_INFO_INTEREST_ETC_FLAG = "interestDealInfo.interestEtcFlag";
    
    /**
     * accountRealNameDiv:  顧客正式名称作成区分
     */
    public static final String ACCOUNT_REAL_NAME_DIV = "accountRealNameDiv";
    
    /**
     * accountRealName1:  顧客正式名称1
     */
    public static final String ACCOUNT_REAL_NAME1 = "accountRealName1";
    
    /**
     * accountRealName2:  顧客正式名称2
     */
    public static final String ACCOUNT_REAL_NAME2 = "accountRealName2";
    
    /**
     * brokerageCode:  仲介業扱者コード
     */
    public static final String BROKERAGE_CODE = "brokerageCode";
    
    /**
     * extItemDiv11:  区分１１
     */
    public static final String EXT_ITEM_DIV11 = "extItemDiv11";
    
    /**
     * extItemDiv12:  区分１２
     */
    public static final String EXT_ITEM_DIV12 = "extItemDiv12";
    
    /**
     * extItemDiv13:  区分１３
     */
    public static final String EXT_ITEM_DIV13 = "extItemDiv13";
    
    /**
     * extItemDiv14:  区分１４
     */
    public static final String EXT_ITEM_DIV14 = "extItemDiv14";
    
    /**
     * extItemDiv15:  区分１５
     */
    public static final String EXT_ITEM_DIV15 = "extItemDiv15";
    
    /**
     * insiderInfo.createDiv:  内部者情報.作成区分
     */
    public static final String INSIDER_INFO_CREATE_DIV = "insiderInfo.createDiv";
    
    /**
     * insiderInfo.productCode:  内部者情報.銘柄コード
     */
    public static final String INSIDER_INFO_PRODUCT_CODE = "insiderInfo.productCode";
    
    /**
     * insiderInfo.relationCode:  内部者情報.関係区分
     */
    public static final String INSIDER_INFO_RELATION_CODE = "insiderInfo.relationCode";
    
    /**
     * insiderInfo.executive:  内部者情報.役員名
     */
    public static final String INSIDER_INFO_EXECUTIVE = "insiderInfo.executive";
    
    /**
     * insiderInfo.positionCode:  内部者情報.役職名コード
     */
    public static final String INSIDER_INFO_POSITION_CODE = "insiderInfo.positionCode";

    /**
     * insiderInfo.position:  内部者情報.役職名
     */
    public static final String INSIDER_INFO_POSITION = "insiderInfo.position";
    
    /**
     * gpInfo.createDiv:  GP情報.作成区分
     */
    public static final String GP_INFO_CREATE_DIV = "gpInfo.createDiv";
    
    /**
     * gpInfo.course:  GP情報.コース
     */
    public static final String GP_INFO_COURSE = "gpInfo.course";
    
    /**
     * gpInfo.plan:  GP情報.プラン
     */
    public static final String GP_INFO_PLAN = "gpInfo.plan";
    
    /**
     * gpInfo.targetFigure:  GP情報.目標額
     */
    public static final String GP_INFO_TARGET_FIGURE = "gpInfo.targetFigure";
    
    /**
     * gpInfo.targetYear:  GP情報.目標年
     */
    public static final String GP_INFO_TARGET_YEAR = "gpInfo.targetYear";
    
    /**
     * gpInfo.targetMonth:  GP情報.目標月
     */
    public static final String GP_INFO_TARGET_MONTH = "gpInfo.targetMonth";
    
    /**
     * gpInfo.installmentFigure:  GP情報.積立額
     */
    public static final String GP_INFO_INSTALLMENT_FIGURE = "gpInfo.installmentFigure";
    
    /**
     * gpInfo.depositCycle:  GP情報.入金周期
     */
    public static final String GP_INFO_DEPOSIT_CYCLE = "gpInfo.depositCycle";
    
    /**
     * gpInfo.paymentRoot:  GP情報.受渡経路
     */
    public static final String GP_INFO_PAYMENT_ROOT = "gpInfo.paymentRoot";
    
    /**
     * gpInfo.reinvestDiv:  GP情報.再投資区分
     */
    public static final String GP_INFO_REINVEST_DIV = "gpInfo.reinvestDiv";
    
    /**
     * gpInfo.taxType:  GP情報.税区分
     */
    public static final String GP_INFO_TAX_TYPE = "gpInfo.taxType";
    
    /**
     * gpInfo.taxfreeLimit:  GP情報.（優）限度額
     */
    public static final String GP_INFO_TAXFREE_LIMIT = "gpInfo.taxfreeLimit";
    
    /**
     * gpInfo.specialTaxfreeLimit:  GP情報.（特優）限度額
     */
    public static final String GP_INFO_SPECIAL_TAXFREE_LIMIT = "gpInfo.specialTaxfreeLimit";
    
    /**
     * gpInfo.subscrSummary:  GP情報.加入摘要
     */
    public static final String GP_INFO_SUBSCR_SUMMARY = "gpInfo.subscrSummary";
    
    /**
     * gpInfo.productCode:  GP情報.銘柄コード
     */
    public static final String GP_INFO_PRODUCT_CODE = "gpInfo.productCode";
    
    /**
     * gpInfo.mortgageCustomer:  GP情報.担保客
     */
    public static final String GP_INFO_MORTGAGE_CUSTOMER = "gpInfo.mortgageCustomer";
    
    /**
     * gpInfo.mixCustomer:  GP情報.ミックス客
     */
    public static final String GP_INFO_MIX_CUSTOMER = "gpInfo.mixCustomer";
    
    /**
     * gpInfo.contract:  GP情報.契約書
     */
    public static final String GP_INFO_CONTRACT = "gpInfo.contract";
    
    /**
     * listedFeqInfo.createDiv:  上場外株情報.作成区分
     */
    public static final String LISTED_FEQ_INFO_CREATE_DIV = "listedFeqInfo.createDiv";
    
    /**
     * listedFeqInfo.taxationTran:  上場外株情報.譲渡
     */
    public static final String LISTED_FEQ_INFO_TAXATION_TRAN = "listedFeqInfo.taxationTran";
    
    /**
     * listedFeqInfo.addressKana:  上場外株情報.住所（カナ）
     */
    public static final String LISTED_FEQ_INFO_ADDRESS_KANA = "listedFeqInfo.addressKana";
    
    /**
     * listedFeqInfo.transferDiv:  上場外株情報.送金
     */
    public static final String LISTED_FEQ_INFO_TRANSFER_DIV = "listedFeqInfo.transferDiv";
    
    /**
     * listedFeqInfo.financialInstitutionCode:  上場外株情報.銀行コード
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_INSTITUTION_CODE = "listedFeqInfo.financialInstitutionCode";
    
    /**
     * listedFeqInfo.financialBranchCode:  上場外株情報.支店コード
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_BRANCH_CODE = "listedFeqInfo.financialBranchCode";
    
    /**
     * listedFeqInfo.financialAccountDiv:  上場外株情報.預金区分
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_DIV = "listedFeqInfo.financialAccountDiv";
    
    /**
     * listedFeqInfo.financialAccountCode:  上場外株情報.口座番号
     */
    public static final String LISTED_FEQ_INFO_FINANCIAL_ACCOUNT_CODE = "listedFeqInfo.financialAccountCode";
    
    /**
     * foreignSaveInfo.financialAccountCode:外貨預金口座情報.口座番号
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_CODE = "foreignSaveInfo.financialAccountCode";
    
    /**
     * foreignSaveInfo.financialAccountName:外貨預金口座情報.口座名義人
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME = "foreignSaveInfo.financialAccountName";
    
    /**
     * foreignSaveInfo.financialAccountNameAlpha:外貨預金口座情報.口座名義人英数
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_NAME_ALPHA = "foreignSaveInfo.financialAccountNameAlpha";
    
    /**
     * foreignSaveInfo.financialAccountDiv:外貨預金口座情報.預金区分
     */
    public static final String FOREIGN_SAVE_INFO_FINANCIAL_ACCOUNT_DIV= "foreignSaveInfo.financialAccountDiv";

    /**
     * deleteFlag削除フラグ
     */
    public static final String DELETE_FLAG = "deleteFlag";

    /**
     * deleteDate削除日時
     */
    public static final String DELETE_DATE = "deleteDate";

    /**
     * printFlag印刷フラグ
     */
    public static final String PRINT_FLAG = "printFlag";

    /**
     * receiveFlag受領フラグ
     */
    public static final String RECEIVE_FLAG = "receiveFlag";

    /**
     * approveFlag承諾フラグ
     */
    public static final String APPROVE_FLAG = "approveFlag";

    /**
     * foreignerFlag外国人フラグ
     */
    public static final String FOREIGNER_FLAG = "foreignerFlag";

    /**
     * agencyInfo.agencyAccNameKana1フリガナ1
     */
    public static final String AGENCY_ACC_NAME_KANA1 = "agencyInfo.agencyAccNameKana1";

    /**
     * agencyInfo.agencyAccNameKana2フリガナ2
     */
    public static final String AGENCY_ACC_NAME_KANA2 = "agencyInfo.agencyAccNameKana2";

    /**
     * agencyInfo.agencyAccName1名称1
     */
    public static final String AGENCY_ACC_NAME1 = "agencyInfo.agencyAccName1";

    /**
     * agencyInfo.agencyAccName2名称2
     */
    public static final String AGENCY_ACC_NAME2= "agencyInfo.agencyAccName2";

    /**
     * agencyInfo.agencyAddress1住所1
     */
    public static final String AGENCY_ADDRESS1 = "agencyInfo.agencyAddress1";

    /**
     * agencyInfo.agencyAddress2住所2
     */
    public static final String AGENCY_ADDRESS2 = "agencyInfo.agencyAddress2";

    /**
     * agencyInfo.agencyRepPost代表者の役職
     */
    public static final String AGENCY_REP_POST = "agencyInfo.agencyRepPost";

    /**
     * agencyInfo.agencyRepNameKana1代表者のフリガナ1
     */
    public static final String AGENCY_REP_NAME_KANA1 = "agencyInfo.agencyRepNameKana1";

    /**
     * agencyInfo.agencyRepNameKana2代表者のフリガナ2
     */
    public static final String AGENCY_REP_NAME_KANA2 = "agencyInfo.agencyRepNameKana2";

    /**
     * agencyInfo.agencyRepName1代表者の氏名1
     */
    public static final String AGENCY_REP_NAME1 = "agencyInfo.agencyRepName1";

    /**
     * agencyInfo.agencyRepName2代表者の氏名2
     */
    public static final String AGENCY_REP_NAME2 = "agencyInfo.agencyRepName2";
}
@
