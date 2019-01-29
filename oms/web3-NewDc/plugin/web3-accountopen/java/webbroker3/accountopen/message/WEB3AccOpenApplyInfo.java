head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込情報(WEB3AccOpenApplyInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
                 : 2006/07/13 周捷 (中訊) 仕様変更 モデル078
                 : 2006/08/14 柴雙紅 (中訊) 仕様変更 モデル087、090
Revesion History : 2009/08/10 張騰宇(中訊) 仕様変更 モデル162、168
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (口座開設申込情報)<BR>
 * 口座開設申込情報<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenApplyInfo extends Message 
{
    
    /**
     * (資料請求日)<BR>
     * 資料請求日<BR>
     */
    public Date infoClaimDate;
    
    /**
     * (更新日)<BR>
     * 更新日<BR>
     */
    public Date updateDate;
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;
    
    /**
     * (入力区分)<BR>
     * 入力区分<BR>
     * <BR>
     * 0：顧客<BR>
     * 1：ＣＣオペレータ<BR> 
     * 2：管理者<BR>
     */
    public String inputDiv;
    
    /**
     * (作成者コード)<BR>
     * 作成者コード<BR>
     */
    public String creatorCode;
    
    /**
     * (既存口座フラグ)<BR>
     * 既存口座フラグ<BR>
     * <BR>
     * true：　@既存口座<BR>
     * false：　@新規口座<BR>
     */
    public boolean exAccountFlag;
    
    /**
     * (既存口座コード)<BR>
     * 既存口座コード<BR>
     */
    public String exAccountCode;
    
    /**
     * (既存口座部店名)<BR>
     * 既存口座部店名<BR>
     */
    public String exAccountBranchName;
    
    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：個人アカウント　@1：法@人アカウント<BR>
     */
    public String accountType;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR）<BR>
     */
    public String traderCode;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;
    
    /**
     * (メールアドレス（携帯）)<BR>
     * メールアドレス（携帯）<BR>
     */
    public String mobileMailAddress;
    
    /**
     * (顧客姓（漢字）)<BR>
     * 顧客姓（漢字）<BR>
     */
    public String accountFamilyName;
    
    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;
    
    /**
     * (顧客姓（カナ）)<BR>
     * 顧客姓（カナ）<BR>
     */
    public String accountFamilyNameKana;
    
    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;
    
    /**
     * (生年月日年号)<BR>
     * 生年月日年号<BR>
     * <BR>
     * 1：明治　@2：大正　@3：昭和　@4：平成　@9：不明<BR>
     */
    public String eraBorn;
    
    /**
     * (生年月日)<BR>
     * 生年月日<BR>
     * <BR>
     * 和暦（YYMMDD）：6桁<BR>
     */
    public String bornDate;
    
    /**
     * (性別)<BR>
     * 性別<BR>
     * <BR>
     * 1：　@男性<BR>
     * 2：　@女性<BR>
     */
    public String sex;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     * <BR>
     * ※ 入力のみ。返却しない。<BR>
     */
    public String password;
    
    /**
     * (郵便番号)<BR>
     * 郵便番号<BR>
     */
    public String zipCode;
    
    /**
     * (住所１（カナ）)<BR>
     * 住所１（カナ）<BR>
     */
    public String addressKana1;
    
    /**
     * (住所２（カナ）)<BR>
     * 住所２（カナ）<BR>
     */
    public String addressKana2;
    
    /**
     * (住所３（カナ）)<BR>
     * 住所３（カナ）<BR>
     */
    public String addressKana3;
    
    /**
     * (住所１（漢字）)<BR>
     * 住所１（漢字）<BR>
     */
    public String address1;
    
    /**
     * (住所２（漢字）)<BR>
     * 住所２（漢字）<BR>
     */
    public String address2;
    
    /**
     * (住所３（漢字）)<BR>
     * 住所３（漢字）<BR>
     */
    public String address3;
    
    /**
     * (電話番号)<BR>
     * 電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String telephone;
    
    /**
     * (携帯番号)<BR>
     * 携帯番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String mobileTelephone;
    
    /**
     * (ＦＡＸ番号)<BR>
     * ＦＡＸ番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String faxTelephone;
    
    /**
     * (勤務先情報)<BR>
     * 勤務先情報<BR>
     */
    public WEB3AccOpenOfficeInfo officeInfo;
    
    /**
     * (連絡先住所)<BR>
     * 連絡先住所<BR>
     */
    public String contactAddress;
    
    /**
     * (連絡先電話番号)<BR>
     * 連絡先電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String contactTelephone;
    
    /**
     * (続柄区分)<BR>
     * 続柄区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String familyRelationDiv;
    
    /**
     * (続続柄（その他）)<BR>
     * 続柄（その他）<BR>
     * <BR>
     * ※ その他を選択した場合入力<BR>
     */
    public String familyRelationEtc;
    
    /**
     * (世帯主名（漢字）)<BR>
     * 世帯主名（漢字）<BR>
     */
    public String houseHolderName;
    
    /**
     * (世帯主名（カナ）)<BR>
     * 世帯主名（カナ）<BR>
     */
    public String houseHolderNameKana;
    
    /**
     * (世帯主勤務先情報)<BR>
     * 世帯主勤務先情報<BR>
     */
    public WEB3AccOpenOfficeInfo houseHolderOfficeInfo;
    
    /**
     * (居住（日本）／非居住（日本以外）区分)<BR>
     * 居住（日本）／非居住（日本以外）区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String residentDiv;
    
    /**
     * (振替区分)<BR>
     * 振替区分<BR>
     * <BR>
     * 1：　@銀行振込<BR>
     * 2：　@郵貯振替<BR>
     */
    public String transferDiv;
    
    /**
     * (口座名義人)<BR>
     * 口座名義人<BR>
     */
    public String financialAccountName;
    
    /**
     * (投資目的区分)<BR>
     * 投資目的区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String investPurposeDiv;
    
    /**
     * (取引動機@区分)<BR>
     * 取引動機@区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String appliMotivatDiv;
    
    /**
     * (年収区分)<BR>
     * 年収区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String annualIncomeDiv;
    
    /**
     * (金融資産区分)<BR>
     * 金融資産区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String assetValueDiv;
    
    /**
     * (運用予定額区分)<BR>
     * 運用予定額区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String fundBundgetAmountDiv;
    
    /**
     * (資産の性格区分)<BR>
     * 資産の性格区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String fundBundgetDiv;
    
    /**
     * (資金の性格（その他）)<BR>
     * 資金の性格（その他）<BR>
     * <BR>
     * ※ その他を選択した場合入力<BR>
     */
    public String fundBundgetEtc;
    
    /**
     * (検索用区分)<BR>
     * 検索用区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String searchDiv;
    
    /**
     * (本人確認書類区分)<BR>
     * 本人確認書類区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String idConfirmDocDiv;
    
    /**
     * (本人確認書類（その他）)<BR>
     * 本人確認書類（その他）<BR>
     * <BR>
     * ※ その他を選択した場合入力<BR>
     */
    public String idConfirmDocEtc;
    
    /**
     * (現物株式口座区分)<BR>
     * 現物株式口座区分<BR>
     * <BR>
     * 0：　@一般口座<BR>
     * 1：　@特定口座（源泉徴収なし）<BR>
     * 2：　@特定口座（源泉徴収あり）<BR>
     */
    public String equityTaxType;
    
    /**
     * (信用取引口座区分)<BR>
     * 信用取引口座区分<BR>
     * <BR>
     * 0：　@一般口座<BR>
     * 1：　@特定口座（源泉徴収なし）<BR>
     * 2：　@特定口座（源泉徴収あり）<BR>
     */
    public String marginTaxType;
    
    /**
     * (内部者登録フラグ)<BR>
     * 内部者登録フラグ<BR>
     * <BR>
     * true：　@内部者登録あり<BR>
     * false：　@内部者登録なし<BR>
     */
    public boolean insiderFlag;
    
    /**
     * (内部者銘柄名)<BR>
     * 内部者銘柄名<BR>
     */
    public String insiderProductName;
    
    /**
     * (送付先郵便番号)<BR>
     * 送付先郵便番号<BR>
     */
    public String sendZipCode;
    
    /**
     * (送付先住所１)<BR>
     * 送付先住所１<BR>
     */
    public String sendAddress1;
    
    /**
     * (送付先住所２)<BR>
     * 送付先住所２<BR>
     */
    public String sendAddress2;
    
    /**
     * (送付先住所３)<BR>
     * 送付先住所３<BR>
     */
    public String sendAddress3;
    
    /**
     * (区分１)<BR>
     * 区分１<BR>
     */
    public String extItemDiv1;
    
    /**
     * (区分２)<BR>
     * 区分２<BR>
     */
    public String extItemDiv2;
    
    /**
     * (区分３)<BR>
     * 区分３<BR>
     */
    public String extItemDiv3;
    
    /**
     * (区分４)<BR>
     * 区分４<BR>
     */
    public String extItemDiv4;
    
    /**
     * (区分５)<BR>
     * 区分５<BR>
     */
    public String extItemDiv5;
    
    /**
     * (区分６)<BR>
     * 区分６<BR>
     */
    public String extItemDiv6;
    
    /**
     * (区分７)<BR>
     * 区分７<BR>
     */
    public String extItemDiv7;
    
    /**
     * (区分８)<BR>
     * 区分８<BR>
     */
    public String extItemDiv8;
    
    /**
     * (区分９)<BR>
     * 区分９<BR>
     */
    public String extItemDiv9;
    
    /**
     * (区分１０)<BR>
     * 区分１０<BR>
     */
    public String extItemDiv10;
    
    /**
     * (フラグ１)<BR>
     * フラグ１<BR>
     */
    public boolean extItemFlag1;
    
    /**
     * (フラグ２)<BR>
     * フラグ２<BR>
     */
    public boolean extItemFlag2;
    
    /**
     * (フラグ３)<BR>
     * フラグ３<BR>
     */
    public boolean extItemFlag3;
    
    /**
     * (フラグ４)<BR>
     * フラグ４<BR>
     */
    public boolean extItemFlag4;
    
    /**
     * (フラグ５)<BR>
     * フラグ５<BR>
     */
    public boolean extItemFlag5;
    
    /**
     * (フラグ６)<BR>
     * フラグ６<BR>
     */
    public boolean extItemFlag6;
    
    /**
     * (フラグ７)<BR>
     * フラグ７<BR>
     */
    public boolean extItemFlag7;
    
    /**
     * (フラグ８)<BR>
     * フラグ８<BR>
     */
    public boolean extItemFlag8;
    
    /**
     * (フラグ９)<BR>
     * フラグ９<BR>
     */
    public boolean extItemFlag9;
    
    /**
     * (フラグ１０)<BR>
     * フラグ１０<BR>
     */
    public boolean extItemFlag10;
    
    /**
     * (テキスト１)<BR>
     * テキスト１<BR>
     */
    public String extItemText1;
    
    /**
     * (テキスト２)<BR>
     * テキスト２<BR>
     */
    public String extItemText2;
    
    /**
     * (テキスト３)<BR>
     * テキスト３<BR>
     */
    public String extItemText3;
    
    /**
     * (テキスト４)<BR>
     * テキスト４<BR>
     */
    public String extItemText4;
    
    /**
     * (テキスト５)<BR>
     * テキスト５<BR>
     */
    public String extItemText5;
    
    /**
     * (テキスト６)<BR>
     * テキスト６<BR>
     */
    public String extItemText6;
    
    /**
     * (テキスト７)<BR>
     * テキスト７<BR>
     */
    public String extItemText7;
    
    /**
     * (テキスト８)<BR>
     * テキスト８<BR>
     */
    public String extItemText8;
    
    /**
     * (テキスト９)<BR>
     * テキスト９<BR>
     */
    public String extItemText9;
    
    /**
     * (テキスト１０)<BR>
     * テキスト１０<BR>
     */
    public String extItemText10;
    
    /**
     * (顧客正式名称作成区分)<BR>
     * 顧客正式名称作成区分<BR>
     */
    public String accountRealNameDiv;
    
    /**
     * (顧客正式名称１)<BR>
     * 顧客正式名称１ <BR>
     * <BR>
     * ※マスタ上は66byteあるが、全角文字の入力可能文字数が20文字のため当該レングスとする<BR>
     */
    public String accountRealName1;
    
    /**
     * (顧客正式名称２)<BR>
     * 顧客正式名称２<BR> 
     * <BR>
     * ※マスタ上は66byteあるが、全角文字の入力可能文字数が20文字のため当該レングスとする<BR>
     */
    public String accountRealName2;
    
    /**
     * (仲介業扱者コード)<BR>
     * 仲介業扱者コード<BR>
     */
    public String brokerageCode;
    
    /**
     * (区分１１)<BR>
     * 区分１１<BR>
     */
    public String extItemDiv11;
    
    /**
     * (区分１２)<BR>
     * 区分１２<BR>
     */
    public String extItemDiv12;
    
    /**
     * (区分１３)<BR>
     * 区分１３<BR>
     */
    public String extItemDiv13;
    
    /**
     * (区分１４)<BR>
     * 区分１４<BR>
     */
    public String extItemDiv14;
    
    /**
     * (区分１５)<BR>
     * 区分１５<BR>
     */
    public String extItemDiv15;

    /**
     * (削除フラグ)<BR>
     * 削除フラグ <BR>
     * <BR>
     * 1：TRUE/無効（削除） <BR>
     * 0：FALSE/有効（DEFAULT）<BR>
     */
    public String deleteFlag;

    /**
     * (削除日時)<BR>
     * 削除日時<BR>
     */
    public Date deleteDate;

    /**
     * (印刷フラグ)<BR>
     * 印刷フラグ <BR>
     * <BR>
     * 0：印刷可 <BR>
     * 1：印刷済 <BR>
     * 3：未処理（DEFAULT）<BR>
     */
    public String printFlag;

    /**
     * (受領フラグ)<BR>
     * 受領フラグ <BR>
     * <BR>
     * 1：TRUE/受領済 <BR>
     * 0：FALSE/未受領（DEFAULT）<BR>
     */
    public String receiveFlag;

    /**
     * (承諾フラグ)<BR>
     * 承諾フラグ <BR>
     * <BR>
     * 1：TRUE/承諾 <BR>
     * 0：FALSE/未承諾（DEFAULT）<BR>
     */
    public String approveFlag;

    /**
     * (外国人フラグ)<BR>
     * 外国人フラグ <BR>
     * <BR>
     * 1：TRUE/日本以外 <BR>
     * 0：FALSE/日本（DEFAULT）<BR>
     */
    public String foreignerFlag;

    /**
     * (振込先銀行情報)<BR>
     * 振込先銀行情報<BR>
     */
    public WEB3AccOpenTransferBankInfo transferBankInfo;
    
    /**
     * (郵便振替情報)<BR>
     * 郵便振替情報<BR>
     */
    public WEB3AccOpenPostalTransferInfo postalTransferInfo;
    
    /**
     * (投資経験情報)<BR>
     * 投資経験情報<BR>
     */
    public WEB3AccOpenInvestmentExperienceInfo investExpInfo;
    
    /**
     * (興味のある取引情報)<BR>
     * 興味のある取引情報<BR>
     */
    public WEB3AccOpenInterestDealingInfo interestDealInfo;
    
    /**
     * 内部者情報<BR>
     */
    public WEB3AccOpenInsiderInfo insiderInfo;
    
    /**
     * GP情報<BR>
     */
    public WEB3AccOpenGpInfo gpInfo;

    /**
     * (機@構通知情報)<BR>
     * 機@構通知情報<BR>
     */
    public WEB3AccOpenAgencyInfo agencyInfo;

    /**
     * 上場外株情報<BR>
     */
    public WEB3AccOpenListedFeqInfo listedFeqInfo;
    
    /**
     * 顧客コード自動採番フラグ<BR>
     */
    public String accountCodeAutoFlag;
    
    /**
     * 外貨預金口座情報<BR>
     */
    public WEB3AccOpenForeignSaveInfo foreignSaveInfo;
    
    /**
     * @@roseuid 41B45E7701A5
     */
    public WEB3AccOpenApplyInfo() 
    {
     
    }
}
@
