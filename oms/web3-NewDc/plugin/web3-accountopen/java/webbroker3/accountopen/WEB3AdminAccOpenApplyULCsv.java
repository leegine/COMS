head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込ULCSV(WEB3AdminAccOpenApplyULCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 武波 (中訊) 新規作成 モデル No.148,No.150,No.153
Revision History : 2007/12/11 謝旋 (中訊) 仕様変更 モデル No.155,No.156
Revision History : 2007/12/17 武波 (中訊) 仕様変更 モデル No.157
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (口座開設申込ULCSV)<BR>
 * 口座開設申込ULCSV<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyULCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyULCsv.class);

    /**
     * (アップロードファ@イルID)<BR>
     * アップロードファ@イルID<BR>
     */
    public String uploadFileId = "口座開設申込";

    /**
     * (アップロード中止)<BR>
     * アップロード中止用コメント<BR>
     */
    public String uploadCancel = "中止";

    /**
     * (レコード番号ラベル)<BR>
     * 定数定義プロパティ　@”レコード番号”<BR>
     */
    public String recordNumberLabel = "レコード番号";

    /**
     * (識別コードラベル)<BR>
     * 定数定義プロパティ　@”識別コード”<BR>
     */
    public String requestNumberLabel = "識別コード";

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード”<BR>
     */
    public String branchCodeLabel = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード”<BR>
     */
    public String accountCodeLabel = "顧客コード";

    /**
     * (扱者コード（SONAR）ラベル)<BR>
     * 定数定義プロパティ　@”扱者コード（SONAR）”<BR>
     */
    public String sonarTraderCodeLabel = "扱者コード（SONAR）";

    /**
     * (口座区分ラベル)<BR>
     * 定数定義プロパティ　@”口座区分”<BR>
     */
    public String accountTypeLabel = "口座区分";

    /**
     * (入力区分ラベル)<BR>
     * 定数定義プロパティ　@”入力区分”<BR>
     */
    public String inputDivLabel = "入力区分";

    /**
     * (資料請求日時ラベル)<BR>
     * 定数定義プロパティ　@”資料請求日時”<BR>
     */
    public String infoClaimDateLabel = "資料請求日時";

    /**
     * (お名前　@姓（漢字）ラベル)<BR>
     * 定数定義プロパティ　@”お名前　@姓（漢字）”<BR>
     */
    public String accountFamilyNameLabel = "お名前　@姓（漢字）";

    /**
     * (お名前　@名（漢字）ラベル)<BR>
     * 定数定義プロパティ　@”お名前　@名（漢字）”<BR>
     */
    public String accountNameLabel = "お名前　@名（漢字）";

    /**
     * (お名前　@姓（フリガナ）ラベル)<BR>
     * 定数定義プロパティ　@”お名前　@姓（フリガナ）”<BR>
     */
    public String accountFamilyNameKanaLabel = "お名前　@姓（フリガナ）";

    /**
     * (お名前　@名（フリガナ）ラベル)<BR>
     * 定数定義プロパティ　@”お名前　@名（フリガナ）”<BR>
     */
    public String accountNameKanaLabel = "お名前　@名（フリガナ）";

    /**
     * (性別ラベル)<BR>
     * 定数定義プロパティ　@”性別”<BR>
     */
    public String sexLabel = "性別";

    /**
     * (生年月日年号ラベル)<BR>
     * 定数定義プロパティ　@”生年月日年号”<BR>
     */
    public String eraBornLabel = "生年月日年号";

    /**
     * (生年月日ラベル)<BR>
     * 定数定義プロパティ　@”生年月日”<BR>
     */
    public String bornDateLabel = "生年月日";

    /**
     * (メールアドレスラベル)<BR>
     * 定数定義プロパティ　@”メールアドレス”<BR>
     */
    public String mailAddressLabel = "メールアドレス";

    /**
     * (郵便番号ラベル)<BR>
     * 定数定義プロパティ　@”郵便番号”<BR>
     */
    public String zipCodeLabel = "郵便番号";

    /**
     * (住所１（漢字）ラベル)<BR>
     * 定数定義プロパティ　@”住所１（漢字）”<BR>
     */
    public String address1Label = "住所１（漢字）";

    /**
     * (住所２（漢字）ラベル)<BR>
     * 定数定義プロパティ　@”住所２（漢字）”<BR>
     */
    public String address2Label = "住所２（漢字）";

    /**
     * (住所３（漢字）ラベル)<BR>
     * 定数定義プロパティ　@”住所３（漢字）”<BR>
     */
    public String address3Label = "住所３（漢字）";

    /**
     * (住所１（カナ）ラベル)<BR>
     * 定数定義プロパティ　@”住所１（カナ）”<BR>
     */
    public String addressKana1Label = "住所１（カナ）";

    /**
     * (住所２（カナ）ラベル)<BR>
     * 定数定義プロパティ　@”住所２（カナ）”<BR>
     */
    public String addressKana2Label = "住所２（カナ）";

    /**
     * (住所３（カナ）ラベル)<BR>
     * 定数定義プロパティ　@”住所３（カナ）”<BR>
     */
    public String addressKana3Label = "住所３（カナ）";

    /**
     * (電話番号（市外局番）ラベル)<BR>
     * 定数定義プロパティ　@”電話番号（市外局番）”<BR>
     */
    public String telephoneAreaCodeLabel = "電話番号（市外局番）";

    /**
     * (電話番号（局番）ラベル)<BR>
     * 定数定義プロパティ　@”電話番号（局番）”<BR>
     */
    public String telephoneExchangeNumberLabel = "電話番号（局番）";

    /**
     * (電話番号（番号）ラベル)<BR>
     * 定数定義プロパティ　@”電話番号（番号）”<BR>
     */
    public String telephoneNumberLabel = "電話番号（番号）";

    /**
     * (携帯番号（市外局番）ラベル)<BR>
     * 定数定義プロパティ　@”携帯番号（市外局番）”<BR>
     */
    public String mobileAreaCodeLabel = "携帯番号（市外局番）";

    /**
     * (携帯番号（局番）ラベル)<BR>
     * 定数定義プロパティ　@”携帯番号（局番）”<BR>
     */
    public String mobileExchangeNumberLabel = "携帯番号（局番）";

    /**
     * (携帯番号（番号）ラベル)<BR>
     * 定数定義プロパティ　@”携帯番号（番号）”<BR>
     */
    public String mobileNumberLabel = "携帯番号（番号）";

    /**
     * (職業区分ラベル)<BR>
     * 定数定義プロパティ　@”職業区分”<BR>
     */
    public String occupationDivLabel = "職業区分";

    /**
     * (勤務先名称ラベル)<BR>
     * 定数定義プロパティ　@”勤務先名称”<BR>
     */
    public String officeLabel = "勤務先名称";

    /**
     * (勤務先郵便番号ラベル)<BR>
     * 定数定義プロパティ　@”勤務先郵便番号”<BR>
     */
    public String officeZipCodeLabel = "勤務先郵便番号";

    /**
     * (勤務先住所ラベル)<BR>
     * 定数定義プロパティ　@”勤務先住所”<BR>
     */
    public String officeAddressLabel = "勤務先住所";

    /**
     * (勤務先電話番号１ラベル)<BR>
     * 定数定義プロパティ　@”勤務先電話番号１”<BR>
     */
    public String officeTelephone1Label = "勤務先電話番号１";

    /**
     * (勤務先電話番号２ラベル)<BR>
     * 定数定義プロパティ　@”勤務先電話番号２”<BR>
     */
    public String officeTelephone2Label = "勤務先電話番号２";

    /**
     * (勤務先電話番号３ラベル)<BR>
     * 定数定義プロパティ　@”勤務先電話番号３”<BR>
     */
    public String officeTelephone3Label = "勤務先電話番号３";

    /**
     * (所属部署ラベル)<BR>
     * 定数定義プロパティ　@”所属部署”<BR>
     */
    public String departmentLabel = "所属部署";

    /**
     * (役職ラベル)<BR>
     * 定数定義プロパティ　@”役職”<BR>
     */
    public String postLabel = "役職";

    /**
     * (世帯主との続柄ラベル)<BR>
     * 定数定義プロパティ　@”世帯主との続柄”<BR>
     */
    public String houseHolderFamilyRelationLabel = "世帯主との続柄";

    /**
     * (世帯主名ラベル)<BR>
     * 定数定義プロパティ　@”世帯主名”<BR>
     */
    public String houseHolderLabel = "世帯主名";

    /**
     * (世帯主　@勤務先ラベル)<BR>
     * 定数定義プロパティ　@”世帯主　@勤務先”<BR>
     */
    public String houseHolderOfficeLabel = "世帯主　@勤務先";

    /**
     * (世帯主　@役職ラベル)<BR>
     * 定数定義プロパティ　@”世帯主　@役職”<BR>
     */
    public String houseHolderPostLabel = "世帯主　@役職";

    /**
     * (振込先ラベル)<BR>
     * 定数定義プロパティ　@”振込先”<BR>
     */
    public String transferLabel = "振込先";

    /**
     * (銀行コードラベル)<BR>
     * 定数定義プロパティ　@”銀行コード”<BR>
     */
    public String finInstitutionCodeLabel = "銀行コード";

    /**
     * (銀行名ラベル)<BR>
     * 定数定義プロパティ　@”銀行名”<BR>
     */
    public String finInstitutionNameLabel = "銀行名";

    /**
     * (支店コードラベル)<BR>
     * 定数定義プロパティ　@”支店コード”<BR>
     */
    public String finBranchCodeLabel = "支店コード";

    /**
     * (支店名ラベル)<BR>
     * 定数定義プロパティ　@”支店名”<BR>
     */
    public String finBranchNameLabel = "支店名";

    /**
     * (預金区分ラベル)<BR>
     * 定数定義プロパティ　@”預金区分”<BR>
     */
    public String finSaveDivLabel = "預金区分";

    /**
     * (口座番号ラベル)<BR>
     * 定数定義プロパティ　@”口座番号”<BR>
     */
    public String finAccountNoLabel = "口座番号";

    /**
     * (通帳記号ラベル)<BR>
     * 定数定義プロパティ　@”通帳記号”<BR>
     */
    public String postalSaveCodeLabel = "通帳記号";

    /**
     * (通帳番号ラベル)<BR>
     * 定数定義プロパティ　@”通帳番号”<BR>
     */
    public String postalSaveNoLabel = "通帳番号";

    /**
     * (投資経験（株式現物取引）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（株式現物取引）”<BR>
     */
    public String experienceEquityDivLabel = "投資経験（株式現物取引）";

    /**
     * (投資経験（株式信用取引）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（株式信用取引）”<BR>
     */
    public String experienceMarginDivLabel = "投資経験（株式信用取引）";

    /**
     * (投資経験（債券）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（債券）”<BR>
     */
    public String experienceBondDivLabel = "投資経験（債券）";

    /**
     * (投資経験（株価指数オプション）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（株価指数オプション）”<BR>
     */
    public String experienceOptionsDivLabel = "投資経験（株価指数オプション）";

    /**
     * (投資経験（投資信託：株式）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（投資信託：株式）”<BR>
     */
    public String experienceFundSkDivLabel = "投資経験（投資信託：株式）";

    /**
     * (投資経験（投資信託：公社債）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（投資信託：公社債）”<BR>
     */
    public String experienceFundBdDivLabel = "投資経験（投資信託：公社債）";

    /**
     * (投資経験（株価指数先物）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（株価指数先物）”<BR>
     */
    public String experienceFuturesDivLabel = "投資経験（株価指数先物）";

    /**
     * (投資経験（外国証券）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（外国証券）”<BR>
     */
    public String experienceFEquityDivLabel = "投資経験（外国証券）";

    /**
     * (投資経験（その他）ラベル)<BR>
     * 定数定義プロパティ　@”投資経験（その他）”<BR>
     */
    public String experienceEtcDivLabel = "投資経験（その他）";

    /**
     * (希望される取引の種類　@株式（現物）ラベル)<BR>
     * 定数定義プロパティ　@”希望される取引の種類　@株式（現物）”<BR>
     */
    public String interestEquityFlagLabel = "希望される取引の種類　@株式（現物）";

    /**
     * (希望される取引の種類　@株式（信用）ラベル)<BR>
     * 定数定義プロパティ　@”希望される取引の種類　@株式（信用）”<BR>
     */
    public String interestMarginFlagLabel = "希望される取引の種類　@株式（信用）";

    /**
     * (希望される取引の種類　@国債先物ラベル)<BR>
     * 定数定義プロパティ　@”希望される取引の種類　@国債先物”<BR>
     */
    public String interestBondFlagLabel = "希望される取引の種類　@国債先物";

    /**
     * (希望される取引の種類　@投資信託ラベル)<BR>
     * 定数定義プロパティ　@”希望される取引の種類　@投資信託”<BR>
     */
    public String interestFundFlagLabel = "希望される取引の種類　@投資信託";

    /**
     * (希望される取引の種類　@株価指数先物ラベル)<BR>
     * 定数定義プロパティ　@”希望される取引の種類　@株価指数先物”<BR>
     */
    public String interestFuturesFlagLabel = "希望される取引の種類　@株価指数先物";

    /**
     * (希望される取引の種類　@株価指数オプションラベル)<BR>
     * 定数定義プロパティ　@”希望される取引の種類　@株価指数オプション”<BR>
     */
    public String interestOptionsFlagLabel = "希望される取引の種類　@株価指数オプション";

    /**
     * (年収区分ラベル)<BR>
     * 定数定義プロパティ　@”年収区分”<BR>
     */
    public String annualIncomeDivLabel = "年収区分";

    /**
     * (金融資産区分ラベル)<BR>
     * 定数定義プロパティ　@”金融資産区分”<BR>
     */
    public String assetValueDivLabel = "金融資産区分";

    /**
     * (特定口座区分現物ラベル)<BR>
     * 定数定義プロパティ　@”特定口座区分現物”<BR>
     */
    public String specialAccEquityLabel = "特定口座区分現物";

    /**
     * (内部者登録区分ラベル)<BR>
     * 定数定義プロパティ　@”内部者登録区分”<BR>
     */
    public String insiderFlagLabel = "内部者登録区分";

    /**
     * (内部者登録銘柄ラベル)<BR>
     * 定数定義プロパティ　@”内部者登録銘柄”<BR>
     */
    public String productNameLabel = "内部者登録銘柄";

    /**
     * (業種区分ラベル)<BR>
     * 定数定義プロパティ　@”業種区分”<BR>
     */
    public String typeDivLabel = "業種区分";

    /**
     * (収入の形態区分ラベル)<BR>
     * 定数定義プロパティ　@”収入の形態区分”<BR>
     */
    public String incomeDormDivLable = "収入の形態区分";

    /**
     * (内部者関係区分ラベル)<BR>
     * 定数定義プロパティ　@”内部者関係区分”<BR>
     */
    public String insiderRelationDivLabel = "内部者関係区分";

    /**
     * (投資目的区分ラベル)<BR>
     * 定数定義プロパティ　@”投資目的区分”<BR>
     */
    public String investPurposeDivLabel = "投資目的区分";

    /**
     * (取引動機@区分ラベル)<BR>
     * 定数定義プロパティ　@”取引動機@区分”<BR>
     */
    public String appliMotivatDivLabel = "取引動機@区分";

    /**
     * (本人確認書類区分ラベル)<BR>
     * 定数定義プロパティ　@”本人確認書類区分”<BR>
     */
    public String idConfirmDocDivLabel = "本人確認書類区分";

    /**
     * (口座開設申込ULCSV)<BR>
     * コンストラクタ<BR>
     * ※　@アップロード中止の場合に使用する。<BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。<BR>
     * @@param l_lngUploadFileID - (アップロードID)<BR>
     * アップロードID<BR>
     * @@roseuid 472AE8F70363
     */
    public WEB3AdminAccOpenApplyULCsv(long l_lngUploadFileID)
    {
        final String STR_METHOD_NAME = "WEB3AdminAccOpenApplyULCsv(long)";
        log.entering(STR_METHOD_NAME);

        //引数のアップロードIDをプロパティにセットする。
        this.administratorUploadId = l_lngUploadFileID;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (口座開設申込ULCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。<BR>
     * @@roseuid 472AE89D0023
     */
    public WEB3AdminAccOpenApplyULCsv()
    {
        this.createColumnHeader();
    }

    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.その他 を返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 473923F200C5
     */
    public ProductTypeEnum getProductType()
    {
        //ProductTypeEnum.その他 を返却する
        return ProductTypeEnum.OTHER;
    }

    /**
     * (getアップロードファ@イルID)<BR>
     * 口座開設申込ULCSV.アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     * @@return String
     * @@roseuid 47392444039D
     */
    public String getUploadFileId()
    {
        return this.uploadFileId;
    }

    /**
     * (getアップロード最新履歴)<BR>
     * 当該アップロードファ@イルに関連するアップロード最新履歴を取得する。<BR>
     * <BR>
     * 以下の条件で「（管理者共通）アップロードテーブル」を検索し、<BR>
     * 取得した行オブジェクトを返却する。<BR>
     * レコードがない場合はnullを返却する。<BR>
     * <BR>
     * [条件]<BR>
     * （管理者共通）アップロードテーブル.証券会社コード = this.get証券会社コード()<BR>
     * （管理者共通）アップロードテーブル.アップロードファ@イルＩＤ =<BR>
     * this.getアップロードファ@イルＩＤ()<BR>
     * （管理者共通）アップロードテーブル.銘柄タイプ = this.get銘柄タイプ()<BR>
     * （管理者共通）アップロードテーブル.データキー = 引数.データキー<BR>
     * （管理者共通）アップロードテーブル.備考１ != "中止"<BR>
     * ※備考１を比較する時、項目がNULLの場合、文字列で"NULL"と
     * 　@　@置き換えてから比較する。(SQL文：nvl(note1,'NULL') != '中止')<BR>
     * <BR>
     * [取得順]<BR>
     * アップロード開始日時　@降順（：desc）<BR>
     * @@param l_lngUploadKey - (データキー)<BR>
     * データキー<BR>
     * @@return Object
     * @@roseuid 473035330388
     */
    public Object getUploadNewHistory(long l_lngUploadKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUploadNewHistory(long)";
        log.entering(STR_METHOD_NAME);

        //以下の条件で「（管理者共通）アップロードテーブル」を検索し、
        //取得した行オブジェクトを返却する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" AND upload_file_id = ? ");
        l_sbWhere.append(" AND product_type = ? ");
        l_sbWhere.append(" AND upload_key = ? ");
        l_sbWhere.append(" AND nvl(note1,'NULL') != ? ");

        Object[] l_whereValues = new Object[5];
        l_whereValues[0] = this.getInstitutionCode();
        l_whereValues[1] = this.getUploadFileId();
        l_whereValues[2] = this.getProductType();
        l_whereValues[3] = new Long(l_lngUploadKey);
        l_whereValues[4] = this.uploadCancel;

        //[取得順]
        //アップロード開始日時　@降順（：desc）
        String l_strSortKey = " upload_start_timestamp DESC ";

        List l_lisAdministratorUploads = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAdministratorUploads = l_queryProcessor.doFindAllQuery(
                AdministratorUploadRow.TYPE,
                l_sbWhere.toString(),
                l_strSortKey,
                null,
                l_whereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードがない場合はnullを返却する。
        if (l_lisAdministratorUploads == null || l_lisAdministratorUploads.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisAdministratorUploads.get(0);
    }

    /**
     * (validate重複顧客)<BR>
     * 重複顧客が追加されていないかチェックを行う。<BR>
     * <BR>
     * １）　@識別コードチェック<BR>
     * 　@１－１）　@get識別コード（行番号）にて、指定行番号の識別コードを取得する。<BR>
     * <BR>
     * 　@１－２）　@取得した識別コードと指定行番号より前の明細行の識別コードを比較する。<BR>
     * <BR>
     * <BR>
     * 　@１－３）　@同じ値の行（識別コード == 識別コード）が存在する場合は、<BR>
     * 　@　@顧客が重複して登録されていると判定し、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02959<BR>
     * <BR>
     * ２）　@部店・顧客コードチェック<BR>
     * 　@２－１）　@get部店コード（行番号），get顧客コード(行番号)にて、<BR>
     * 　@　@　@　@　@指定行番号の部店コード，顧客コードを取得する。<BR>
     * <BR>
     * 　@２－２）　@get顧客コード(行番号)の戻り値がNULL以外の場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@２－２－１）　@取得した部店コード，顧客コードと指定行番号より前の明細行の<BR>
     * 　@　@　@　@　@　@部店コード，顧客コードを比較する。<BR>
     * <BR>
     * 　@　@２－２－２）　@同じ値の行（部店コード == 部店コード && 顧客コード ==<BR>
     * 　@　@　@　@　@　@顧客コード）が存在する場合は、<BR>
     * 　@　@　@顧客が重複して登録されていると判定し、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02959<BR>
     * <BR>
     * ３）　@例外をスローした場合、以下のメッセージを表示する。<BR>
     * 　@メッセージ　@　@　@：『重複する顧客コード又は識別コードの申込みが存在します。』<BR>
     * 　@　@　@※BUSINESS_ERROR<BR>
     * 　@追加文字列　@：以下の内容をセットする。<BR>
     * 　@　@－識別コードチェックの場合　@：　@get識別コード()<BR>
     * 　@　@－顧客コードチェックの場合　@：　@get部店コード() + "　@"（スペース） +<BR>
     * 　@　@　@　@　@get顧客コード()<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 4731592B0009
     */
    public void validateDuplicateAccount(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDuplicateAccount(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@識別コードチェック
        //  １－１）　@get識別コード（行番号）にて、指定行番号の識別コードを取得する。
        String l_strRequestNumber = this.getRequestNumber(l_intLineNo);

        //  １－２）　@取得した識別コードと指定行番号より前の明細行の識別コードを比較する。
        for (int i = 0; i < l_intLineNo; i++)
        {
            String l_strRequestNumberPrevious = this.getRequestNumber(i);

            //　@１－３）　@同じ値の行（識別コード == 識別コード）が存在する場合は、
            //　@　@顧客が重複して登録されていると判定し、例外をスローする。
            if (l_strRequestNumber.equals(l_strRequestNumberPrevious))
            {
                //３）　@例外をスローした場合、以下のメッセージを表示する。
                //メッセージ　@　@　@：『重複する顧客コード又は識別コードの申込みが存在します。』
                //追加文字列　@：以下の内容をセットする。
                //　@－識別コードチェックの場合　@：　@get識別コード()
                log.debug("重複する顧客コード又は識別コードの申込みが存在します。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02959,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getRequestNumber(l_intLineNo));
            }
        }

        //  ２）　@部店・顧客コードチェック
        //  ２－１）　@get部店コード（行番号），get顧客コード(行番号)にて、
        //    指定行番号の部店コード，顧客コードを取得する。
        String l_strBranchCode = this.getBranchCode(l_intLineNo);
        String l_strAccountCode = this.getAccountCode(l_intLineNo);

        //２－２）　@get顧客コード(行番号)の戻り値がNULL以外の場合、以下の処理を行う。
        if (l_strAccountCode != null)
        {
            for (int i = 0; i < l_intLineNo; i++)
            {
                //２－２－１）　@取得した部店コード，顧客コードと指定行番号
                //  より前の明細行の部店コード，顧客コードを比較する。
                String l_strBranchCodePrevious = this.getBranchCode(i);
                String l_strAccountCodePrevious = this.getAccountCode(i);

                //２－２－２）　@同じ値の行（部店コード == 部店コード && 顧客コード == 顧客コード）が存在する場合は、
                //顧客が重複して登録されていると判定し、例外をスローする。
                if (l_strBranchCode.equals(l_strBranchCodePrevious)
                    && l_strAccountCode.equals(l_strAccountCodePrevious))
                {
                    //３）　@例外をスローした場合、以下のメッセージを表示する。
                    //メッセージ　@　@　@：『重複する顧客コード又は識別コードの申込みが存在します。』
                    //追加文字列　@：以下の内容をセットする。
                    //　@－顧客コードチェックの場合　@：　@get部店コード() + "　@"（スペース） + get顧客コード()
                    log.debug("重複する顧客コード又は識別コードの申込みが存在します。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02959,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        this.getBranchCode(l_intLineNo) + "　@" + this.getAccountCode(l_intLineNo));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate口座開設見込客登録)<BR>
     * 口座開設見込客テーブルに顧客が登録されていないかチェックを行う。<BR>
     * <BR>
     * １）　@重複識別コードのチェック<BR>
     * <BR>
     * 　@１－１）get識別コード（行番号）にて、指定行番号の識別コードを取得する。<BR>
     * <BR>
     * 　@１－２）　@以下の条件で口座開設見込客テーブルを検索する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@証券会社コード： 証券会社コード<BR>
     * 　@　@識別コード： get識別コード()の戻り値<BR>
     * <BR>
     * 　@１－３）１－２）にてレコードが取得できた場合は、<BR>
     * 　@　@　@顧客が登録されていると判定し、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02960<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * <BR>
     * 　@２－１）　@get部店コード（行番号），get顧客コード(行番号)にて、<BR>
     * 　@　@　@指定行番号の部店コード，顧客コードを取得する。<BR>
     * <BR>
     * 　@２－２）　@get顧客コード(行番号)の戻り値がNULL以外の場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@２－２－１） 以下の条件で口座開設見込客テーブルを検索する。<BR>
     * <BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@証券会社コード： 証券会社コード<BR>
     * 　@　@　@部店コード： get部店コード()の戻り値<BR>
     * 　@　@　@口座コード： get顧客コード()の戻り値　@※最初の6byteのみ比較する。<BR>
     * <BR>
     * 　@　@２－２－２）２－２－１）にてレコードが取得できた場合は、<BR>
     * 　@　@　@　@顧客が登録されていると判定し、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_02960<BR>
     * <BR>
     * ３）　@例外をスローした場合、以下のメッセージを表示する。<BR>
     * 　@メッセージ　@　@　@：『指定の顧客コード又は識別コードは既<BR>
     * 　@　@に口座開設見込客に登録されています。』<BR>
     * ※BUSINESS_ERROR<BR>
     * 　@追加文字列　@：以下の内容をセットする。<BR>
     * 　@　@－識別コードチェックの場合　@：　@get識別コード()<BR>
     * 　@　@－顧客コードチェックの場合　@：　@get部店コード() + "　@"（スペース）<BR>
     * 　@　@　@+ get顧客コード()<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4738FE96004F
     */
    public void validateExpAccountOpenLogin(
        int l_intLineNo, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExpAccountOpenLogin(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@重複識別コードのチェック
        //１－１）get識別コード（行番号）にて、指定行番号の識別コードを取得する。
        String l_strRequestNumber = this.getRequestNumber(l_intLineNo);

        //１－２）　@以下の条件で口座開設見込客テーブルを検索する。
        //[検索条件]
        //証券会社コード： 証券会社コード
        //識別コード： get識別コード()の戻り値
        List l_lisRecords = null;
        try
        {
            String l_strQueryString = " institution_code = ? and acc_open_request_number = ? ";
            Object[] l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strRequestNumber};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);

            //１－３） １－２）にてレコードが取得できた場合は、顧客が登録されていると判定し、例外をスローする。
            if (!l_lisRecords.isEmpty())
            {
                //３）　@例外をスローした場合、以下のメッセージを表示する。
                //メッセージ　@　@　@：『指定の顧客コード又は識別コードは既に口座開設見込客に登録されています。』
                //　@※BUSINESS_ERROR
                //追加文字列　@：以下の内容をセットする。
                //　@－識別コードチェックの場合　@：　@get識別コード()
                log.debug("指定の顧客コード又は識別コードは既に口座開設見込客に登録されています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02960,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getRequestNumber(l_intLineNo));
            }

            //２）　@顧客コードのチェック
            //２－１）　@get部店コード（行番号），get顧客コード(行番号)にて、指定行番号の部店コード，顧客コードを取得する。
            String l_strBranchCode = this.getBranchCode(l_intLineNo);
            String l_strAccountCode = this.getAccountCode(l_intLineNo);

            //２－２）　@get顧客コード(行番号)の戻り値がNULL以外の場合、以下の処理を行う。
            if (l_strAccountCode != null)
            {
                //２－２－１） 以下の条件で口座開設見込客テーブルを検索する。
                //[検索条件]
                //証券会社コード： 証券会社コード
                //部店コード： get部店コード()の戻り値
                //口座コード： get顧客コード()の戻り値　@※最初の6byteのみ比較する。
                l_strQueryString =
                    "institution_code = ? and branch_code = ? and account_code like ? || '%' ";

                l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strBranchCode, l_strAccountCode};

                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    ExpAccountOpenRow.TYPE,
                    l_strQueryString,
                    l_objQueryDataContainer);

                //２－２－２） ２－２－１）にてレコードが取得できた場合は、顧客が登録されていると判定し、例外をスローする。
                if (!l_lisRecords.isEmpty())
                {
                    //３）　@例外をスローした場合、以下のメッセージを表示する。
                    //メッセージ　@　@　@：『指定の顧客コード又は識別コードは既に口座開設見込客に登録されています。』
                    //　@※BUSINESS_ERROR
                    //追加文字列　@：以下の内容をセットする。
                    //　@－顧客コードチェックの場合　@：　@get部店コード() + "　@"（スペース） + get顧客コード()
                    log.debug("指定の顧客コード又は識別コードは既に口座開設見込客に登録されています。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02960,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        this.getBranchCode(l_intLineNo) + "　@" + this.getAccountCode(l_intLineNo));
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateアップロード中止)<BR>
     * 該当アップロード行にアップロード中止を更新する。<BR>
     * <BR>
     * 　@this.getアップロードＩＤ()に該当する行について<BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR>
     * <BR>
     * 　@アップロード終了日時 = System.currentTimeMillis()<BR>
     * 　@アップロード件数 = 0<BR>
     * 　@備考１ = this.アップロード中止<BR>
     * <BR>
     *  ※該当データがなくても、例外を上位にスローしない。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47325AF101C1
     */
    public void updateUploadCancel() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateUploadCancel()";
        log.entering(STR_METHOD_NAME);

        //this.getアップロードＩＤ()に該当する行について
        //以下の通り、（管理者共通）アップロードテーブルを更新する。
        AdministratorUploadRow l_administratorUploadRow;
        try
        {
            l_administratorUploadRow =
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());

            if (l_administratorUploadRow == null)
            {
                //※該当データがなくても、例外を上位にスローしない。
                log.exiting(STR_METHOD_NAME);
                return;
            }

            AdministratorUploadParams l_administratorUploadParams =
                new AdministratorUploadParams(l_administratorUploadRow);

            //アップロード終了日時 = System.currentTimeMillis()
            l_administratorUploadParams.setUploadEndTimestamp(new Timestamp(System.currentTimeMillis()));

            //アップロード件数 = 0
            l_administratorUploadParams.setUploadCount(0);

            //備考１ = this.アップロード中止
            l_administratorUploadParams.setNote1(this.uploadCancel);

            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            Processors.getDefaultProcessor().doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateレコード番号)<BR>
     * レコード番号のチェックを行う。<BR>
     * <BR>
     * １）　@getレコード番号()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[getレコード番号()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@１－１）　@レコード番号が取得できない場合（getレコード番号() == null）、<BR>
     * 　@　@以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@『必須項目の値が入力されていません。』<BR>
     * 　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01309）<BR>
     * <BR>
     * 　@１－２）　@半角数字以外が含まれる場合は、以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@『項目値が有効なコード値ではありません。』<BR>
     * 　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01738）<BR>
     * <BR>
     * ２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。<BR>
     * 　@追加文字列：　@口座開設申込ULCSV.レコード番号ラベル<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47325AF101D1
     */
    public void validateRecordNumber(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRecordNumber(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@getレコード番号()にて、指定行番号のデータを取得しチェックを行う。
        //[getレコード番号()に指定する引数]
        //行番号：　@行番号
        String l_strRecordNumber = this.getRecordNumber(l_intLineNo);
        //１－１）　@レコード番号が取得できない場合（getレコード番号() == null）、 以下の例外をスローする。
        //『必須項目の値が入力されていません。』（エラーコード：BUSINESS_ERROR_01309）

        //２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。
        //追加文字列：　@口座開設申込ULCSV.レコード番号ラベル
        if (WEB3StringTypeUtility.isEmpty(l_strRecordNumber))
        {
            log.debug("必須項目の値が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.recordNumberLabel);
        }

        //１－２）　@半角数字以外が含まれる場合は、以下の例外をスローする。
        //『項目値が有効なコード値ではありません。』（エラーコード：BUSINESS_ERROR_01738）
        if (!WEB3StringTypeUtility.isDigit(l_strRecordNumber))
        {
            log.debug("項目値が有効なコード値ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01738,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.recordNumberLabel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate識別コード)<BR>
     * 識別コードのチェックを行う。<BR>
     * <BR>
     * １）　@get識別コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get識別コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@１－１）　@識別コードが取得できない場合（get識別コード() == null）、<BR>
     * 　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@『必須項目の値が入力されていません。』<BR>
     * 　@　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01309）<BR>
     * <BR>
     * 　@１－２）　@13桁でない場合、 以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@『項目長さが有効な範囲内ではありません。』<BR>
     * 　@　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01310）<BR>
     * <BR>
     * 　@１－３）　@半角数字以外が含まれる場合、以下の例外をスローする。  <BR>
     * 　@　@　@　@　@　@　@『項目が有効な値ではありません。』<BR>
     * 　@　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01312）<BR>
     * <BR>
     * ２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。<BR>
     * 　@追加文字列：　@口座開設申込ULCSV.識別コードラベル<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@throws WEB3BaseException
     */
    public void validateRequestNumber(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRequestNumber(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@get識別コード()にて、指定行番号のデータを取得しチェックを行う。
        //[get識別コード()に指定する引数]
        //行番号：　@行番号
        //２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。
        //追加文字列：　@口座開設申込ULCSV.識別コードラベル
        String l_strRequestNumber = this.getRequestNumber(l_intLineNo);

        //１－１）　@識別コードが取得できない場合（get識別コード() == null）、以下の例外をスローする。
        //『必須項目の値が入力されていません。』（エラーコード：BUSINESS_ERROR_01309）
        if (l_strRequestNumber == null)
        {
            log.debug("必須項目の値が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.requestNumberLabel);
        }

        //１－２）　@13桁でない場合、 以下の例外をスローする。
        //『項目長さが有効な範囲内ではありません。』（エラーコード：BUSINESS_ERROR_01310）
        if (WEB3StringTypeUtility.getByteLength(l_strRequestNumber) != 13)
        {
            log.debug("項目長さが有効な範囲内ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01310,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.requestNumberLabel);
        }

        //１－３）　@半角数字以外が含まれる場合、以下の例外をスローする。
        //『項目が有効な値ではありません。』（エラーコード：BUSINESS_ERROR_01312）
        if (!WEB3StringTypeUtility.isDigit(l_strRequestNumber))
        {
            log.debug("項目が有効な値ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01312,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.requestNumberLabel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate口座区分)<BR>
     * 口座区分のチェックを行う。<BR>
     * <BR>
     * １）　@get口座区分()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get口座区分()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@１－１）　@口座区分が取得できない場合（get口座区分() == null）、<BR>
     * 　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@『必須項目の値が入力されていません。』<BR>
     * 　@　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01309）<BR>
     * <BR>
     * 　@１－２）　@「0：個人口座 1：法@人口座」以外の場合、 以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@『項目値が有効なコード値ではありません。』<BR>
     * 　@　@　@　@　@　@　@（エラーコード：BUSINESS_ERROR_01738）<BR>
     * <BR>
     * ２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。<BR>
     * 　@追加文字列：　@口座開設申込ULCSV.口座区分ラベル<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@throws WEB3BaseException
     */
    public void validateAccountType(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountType(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@get口座区分()にて、指定行番号のデータを取得しチェックを行う。
        //[get口座区分()に指定する引数]
        //行番号：　@行番号
        //２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。
        //追加文字列：　@口座開設申込ULCSV.口座区分ラベル
        String l_strAccountDiv = this.getAccountDiv(l_intLineNo);

        //１－１）　@口座区分が取得できない場合（get口座区分() == null）、以下の例外をスローする。
        //『必須項目の値が入力されていません。』（エラーコード：BUSINESS_ERROR_01309）
        if (l_strAccountDiv == null)
        {
            log.debug("必須項目の値が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.accountTypeLabel);
        }

        //１－２）　@「0：個人口座 1：法@人口座」以外の場合、 以下の例外をスローする。
        //『項目値が有効なコード値ではありません。』（エラーコード：BUSINESS_ERROR_01738）
        if (!(WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_strAccountDiv)
            || WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_strAccountDiv)))
        {
            log.debug("項目値が有効なコード値ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01738,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                this.accountTypeLabel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate資料請求日時)<BR>
     * 資料請求日時のチェックを行う。<BR>
     * <BR>
     * １）　@get資料請求日時()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get資料請求日時()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@１－１）　@get資料請求日時() != nullの場合、以下のチェックを行う。<BR>
     * <BR>
     * 　@　@１－１－１）　@資料請求日時が14桁でない、又は、暦日でない場合、<BR>
     * 　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@『項目が有効な値ではありません。』（エラーコード：BUSINESS_ERROR_01312）<BR>
     * <BR>
     * ２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。<BR>
     * 　@追加文字列：　@口座開設申込ULCSV.資料請求日時ラベル<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@throws WEB3BaseException
     */
    public void validateInfomationClaimDatetime(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInfomationClaimDatetime(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@get資料請求日時()にて、指定行番号のデータを取得しチェックを行う。
        //行番号：　@行番号
        String l_strInfoClaimDate = this.getInfoClaimDate(l_intLineNo);

        //１－１）　@get資料請求日時() != nullの場合、以下のチェックを行う。
        if (l_strInfoClaimDate != null)
        {
            //１－１－１）　@資料請求日時が14桁でない、又は、暦日でない場合、 以下の例外をスローする。
            //     　@『項目が有効な値ではありません。』（エラーコード：BUSINESS_ERROR_01312）
            //２）　@１）にて例外が発生した場合、追加文字列に以下をセットする。
            //      追加文字列：　@口座開設申込ULCSV.資料請求日時ラベル
            if (WEB3StringTypeUtility.getByteLength(l_strInfoClaimDate) != 14
                || !WEB3StringTypeUtility.isDateStr(
                    l_strInfoClaimDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS))
            {
                log.debug("項目が有効な値ではありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01312,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.infoClaimDateLabel);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (to口座開設見込客)<BR>
     * 明細行の内容より、口座開設見込客オブジェクトを生成する。<BR>
     * <BR>
     * １）　@行オブジェクト生成<BR>
     * 　@デフォルトコンストラクタにて、口座開設見込客行オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@プロパティセット<BR>
     * 　@口座開設申込情報メッセージオブジェクトより、生成した口座開設見込客行オブジェクトに<BR>
     * 　@以下の通り値をセットする。<BR>
     * <BR>
     * 　@２－１）証券会社コード<BR>
     * 　@　@　@口座開設見込客行.証券会社コードに引数.証券会社コードをセットする<BR>
     * <BR>
     * 　@２－２）識別コード<BR>
     * 　@　@　@get識別コード()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.識別コードにセットする。<BR>
     * <BR>
     * 　@　@　@[get識別コード()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３）部店コード<BR>
     * 　@　@　@get部店コード()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.部店コードにセットする。<BR>
     * <BR>
     * 　@　@　@[get部店コード()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４）顧客コード<BR>
     * 　@　@　@get顧客コード()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.口座コードにセットする。<BR>
     * <BR>
     * 　@　@　@[get顧客コード()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５）扱者コード（SONAR）<BR>
     * 　@　@　@get扱者コード（SONAR）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.扱者コード（SONAR）にセットする。<BR>
     * <BR>
     * 　@　@　@[get扱者コード（SONAR）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６）口座区分<BR>
     * 　@　@　@get口座区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.口座区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get口座区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－７）入力区分<BR>
     * 　@　@　@get入力区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.入力区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get入力区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－８）資料請求日時<BR>
     * 　@　@　@get資料請求日時()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.資料請求日時にセットする。<BR>
     * <BR>
     * 　@　@　@[get資料請求日時()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－９）お名前　@姓（漢字）<BR>
     * 　@　@　@getお名前　@姓（漢字）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.顧客姓（漢字）にセットする。<BR>
     * <BR>
     * 　@　@　@[getお名前　@姓（漢字）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１０）お名前　@名（漢字）<BR>
     * 　@　@　@getお名前　@名（漢字）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.顧客名（漢字）にセットする。<BR>
     * <BR>
     * 　@　@　@[getお名前　@名（漢字）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１１）お名前　@姓（フリガナ）<BR>
     * 　@　@　@getお名前　@姓（フリガナ）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.顧客姓（カナ）にセットする。<BR>
     * <BR>
     * 　@　@　@[getお名前　@姓（フリガナ）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１２）お名前　@名（フリガナ）<BR>
     * 　@　@　@getお名前　@名（フリガナ）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.顧客名（カナ）にセットする。<BR>
     * <BR>
     * 　@　@　@[getお名前　@名（フリガナ）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１３）性別<BR>
     * 　@　@　@get性別()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.性別にセットする。<BR>
     * <BR>
     * 　@　@　@[get性別()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１４）生年月日年号<BR>
     * 　@　@　@get生年月日年号()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.生年月日年号にセットする。<BR>
     * <BR>
     * 　@　@　@[get生年月日年号()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１５）生年月日<BR>
     * 　@　@　@get生年月日()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.生年月日にセットする。<BR>
     * <BR>
     * 　@　@　@[get生年月日()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１６）メールアドレス<BR>
     * 　@　@　@getメールアドレス()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.emailアドレスにセットする。<BR>
     * <BR>
     * 　@　@　@[getメールアドレス()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１７）郵便番号<BR>
     * 　@　@　@get郵便番号()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.郵便番号にセットする。<BR>
     * <BR>
     * 　@　@　@[get郵便番号()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１８）住所１（漢字）<BR>
     * 　@　@　@get住所１（漢字）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.住所１にセットする。<BR>
     * <BR>
     * 　@　@　@[get住所１（漢字）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１９）住所２（漢字）<BR>
     * 　@　@　@get住所２（漢字）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.住所２にセットする。<BR>
     * <BR>
     * 　@　@　@[get住所２（漢字）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２０）住所３（漢字）<BR>
     * 　@　@　@get住所３（漢字）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.住所３にセットする。<BR>
     * <BR>
     * 　@　@　@[get住所３（漢字）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２１）住所１（カナ）<BR>
     * 　@　@　@get住所１（カナ）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.住所１（カナ）にセットする。<BR>
     * <BR>
     * 　@　@　@[get住所１（カナ）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２２）住所２（カナ）<BR>
     * 　@　@　@get住所２（カナ）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.住所２（カナ）にセットする。<BR>
     * <BR>
     * 　@　@　@[get住所２（カナ）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２３）住所３（カナ）<BR>
     * 　@　@　@get住所３（カナ）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.住所３（カナ）にセットする。<BR>
     * <BR>
     * 　@　@　@[get住所３（カナ）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２４）電話番号<BR>
     * <BR>
     * 　@　@２－２４－１）電話番号（市外局番）<BR>
     * 　@　@　@get電話番号（市外局番）()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get電話番号（市外局番）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－２４－２）電話番号（局番）<BR>
     * 　@　@　@get電話番号（局番）()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get電話番号（局番）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－２４－３）電話番号（番号）<BR>
     * 　@　@　@get電話番号（番号）()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get電話番号（番号）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－２４－４）口座開設見込客行.電話番号に以下をセットする。<BR>
     * 　@　@　@get電話番号（市外局番）()の戻り値 + "-" + <BR>
     * 　@　@　@get電話番号（局番）()の戻り値 + "-" + <BR>
     * 　@　@　@get電話番号（番号）()の戻り値<BR>
     * <BR>
     * 　@２－２５）携帯番号<BR>
     * <BR>
     * 　@　@２－２５－１）携帯番号（市外局番）<BR>
     * 　@　@　@get携帯番号（市外局番）()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get携帯番号（市外局番）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－２５－２）携帯番号（局番）<BR>
     * 　@　@　@get携帯番号（局番）()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get携帯番号（局番）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－２５－３）携帯番号（番号）<BR>
     * 　@　@　@get携帯番号（番号）()にて、指定行番号のデータを取得。<BR>
     * 　@　@<BR>
     * 　@　@　@[get携帯番号（番号）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * 　@　@<BR>
     * 　@　@２－２５－４）口座開設見込客行.連絡先電話番号（携帯）に以下をセットする。<BR>
     * 　@　@　@get携帯番号（市外局番）()の戻り値 + "-" +<BR>
     * 　@　@　@get携帯番号（局番）()の戻り値 + "-" +<BR>
     * 　@　@　@get携帯番号（番号）()の戻り値<BR>
     * 　@　@<BR>
     * 　@２－２６）職業区分<BR>
     * 　@　@　@get職業区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.職業区分にセットする。<BR>
     * 　@　@　@<BR>
     * 　@　@　@[get職業区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２７）勤務先名称<BR>
     * 　@　@　@get勤務先名称()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.勤務先名称にセットする。<BR>
     * <BR>
     * 　@　@　@[get勤務先名称()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２８）勤務先郵便番号<BR>
     * 　@　@　@get勤務先郵便番号()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.勤務先郵便番号にセットする。<BR>
     * <BR>
     * 　@　@　@[get勤務先郵便番号()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－２９）勤務先住所<BR>
     * 　@　@　@get勤務先住所()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.勤務先住所にセットする。<BR>
     * <BR>
     * 　@　@　@[get勤務先住所()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３０）勤務先電話番号<BR>
     * <BR>
     * 　@　@２－３０－１）get勤務先電話番号１<BR>
     * 　@　@　@get勤務先電話番号１()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get勤務先電話番号１()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－３０－２）get勤務先電話番号２<BR>
     * 　@　@　@get勤務先電話番号２()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get勤務先電話番号２()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－３０－３）get勤務先電話番号１<BR>
     * 　@　@　@get勤務先電話番号３()にて、指定行番号のデータを取得。<BR>
     * <BR>
     * 　@　@　@[get勤務先電話番号３()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@２－３０－４）口座開設見込客行.勤務先電話番号以下をセットする。<BR>
     * 　@　@　@get勤務先電話番号１()の戻り値 + "-" +<BR>
     * 　@　@　@get勤務先電話番号２()の戻り値 + "-" +<BR>
     * 　@　@　@get勤務先電話番号３()の戻り値<BR>
     * <BR>
     * 　@２－３１）所属部署<BR>
     * 　@　@　@get所属部署()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.所属部署にセットする。<BR>
     * <BR>
     * 　@　@　@[get所属部署()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３２）役職<BR>
     * 　@　@　@get役職()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.役職にセットする。<BR>
     * <BR>
     * 　@　@　@[get役職()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３３）世帯主との続柄<BR>
     * 　@　@　@get世帯主との続柄()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.続柄区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get世帯主との続柄()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３４）世帯主名<BR>
     * 　@　@　@get世帯主名()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.世帯主名（漢字）にセットする。<BR>
     * <BR>
     * 　@　@　@[get世帯主名()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３５）世帯主　@勤務先<BR>
     * 　@　@　@get世帯主　@勤務先()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.世帯主勤務先にセットする。<BR>
     * <BR>
     * 　@　@　@[get世帯主　@勤務先()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３６）世帯主　@役職<BR>
     * 　@　@　@get世帯主　@役職()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.世帯主役職にセットする。<BR>
     * <BR>
     * 　@　@　@[get世帯主　@役職()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３７）振込先<BR>
     * 　@　@　@get振込先()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 振替区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get振込先()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３８）銀行コード<BR>
     * 　@　@　@get銀行コード()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 銀行コードにセットする。<BR>
     * <BR>
     * 　@　@　@[get銀行コード()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－３９）銀行名<BR>
     * 　@　@　@get銀行名()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 銀行名にセットする。<BR>
     * <BR>
     * 　@　@　@[get銀行名()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４０）支店コード<BR>
     * 　@　@　@get支店コード()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 支店コードにセットする。<BR>
     * <BR>
     * 　@　@　@[get支店コード()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４１）支店名<BR>
     * 　@　@　@get支店名()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 支店名にセットする。<BR>
     * <BR>
     * 　@　@　@[get支店名()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４２）預金区分<BR>
     * 　@　@　@get預金区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 預金区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get預金区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４３）口座番号<BR>
     * 　@　@　@get口座番号()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 口座番号にセットする。<BR>
     * <BR>
     * 　@　@　@[get口座番号()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４４）通帳記号<BR>
     * 　@　@　@get通帳記号()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 通帳記号にセットする。<BR>
     * <BR>
     * 　@　@　@[get通帳記号()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４５）通帳番号<BR>
     * 　@　@　@get通帳番号()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.振込先金融機@関情報 通帳番号にセットする。<BR>
     * <BR>
     * 　@　@　@[get通帳番号()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４６）投資経験（株式現物取引）<BR>
     * 　@　@　@get投資経験（株式現物取引）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 現物株式にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（株式現物取引）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４７）投資経験（株式信用取引）<BR>
     * 　@　@　@get投資経験（株式信用取引）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 信用取引にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（株式信用取引）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４８）投資経験（債券）<BR>
     * 　@　@　@get投資経験（債券）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 債券にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（債券）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－４９）投資経験（株価指数オプション）<BR>
     * 　@　@　@get投資経験（株価指数オプション）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 転換社債にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（株価指数オプション）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５０）投資経験（投資信託：株式）<BR>
     * 　@　@　@get投資経験（投資信託：株式）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 投資信託（株式）にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（投資信託：株式）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５１）投資経験（投資信託：公社債）<BR>
     * 　@　@　@get投資経験（投資信託：公社債）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 投資信託（公社債）にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（投資信託：公社債）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５２）投資経験（株価指数先物）<BR>
     * 　@　@　@get投資経験（株価指数先物）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 先物・オプションにセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（株価指数先物）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５３）投資経験（外国証券）<BR>
     * 　@　@　@get投資経験（外国証券）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 外国証券にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（外国証券）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５４）投資経験（その他）<BR>
     * 　@　@　@get投資経験（その他）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.投資経験 その他にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資経験（その他）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５５）希望される取引の種類　@株式（現物）<BR>
     * 　@　@　@get希望される取引の種類　@株式（現物）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.興味のあるお取引 現物株式フラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get希望される取引の種類　@株式（現物）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５６）希望される取引の種類　@株式（信用）<BR>
     * 　@　@　@get希望される取引の種類　@株式（信用）()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.興味のあるお取引 信用取引フラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get希望される取引の種類　@株式（信用）()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５７）希望される取引の種類　@国債先物<BR>
     * 　@　@　@get希望される取引の種類　@国債先物()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.興味のあるお取引 債券フラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get希望される取引の種類　@国債先物()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５８）希望される取引の種類　@投資信託<BR>
     * 　@　@　@get希望される取引の種類　@投資信託()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.興味のあるお取引 投資信託フラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get希望される取引の種類　@投資信託()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－５９）希望される取引の種類　@株価指数先物<BR>
     * 　@　@　@get希望される取引の種類　@株価指数先物()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.興味のあるお取引 先物・オプションフラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get希望される取引の種類　@株価指数先物()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６０）希望される取引の種類　@株価指数オプション<BR>
     * 　@　@　@get希望される取引の種類　@株価指数オプション()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.興味のあるお取引 外国証券フラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get希望される取引の種類　@株価指数オプション()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６１）年収区分<BR>
     * 　@　@　@get年収区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.年収区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get年収区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６２）金融資産区分<BR>
     * 　@　@　@get金融資産区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.金融資産区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get金融資産区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６３）特定口座区分現物<BR>
     * 　@　@　@get特定口座区分現物()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.特定口座区分にセットする。<BR>
     * <BR>
     * 　@　@　@[get特定口座区分現物()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６４）内部者登録区分<BR>
     * 　@　@　@get内部者登録区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.内部者登録フラグにセットする。<BR>
     * <BR>
     * 　@　@　@[get内部者登録区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６５）内部者登録銘柄<BR>
     * 　@　@　@get内部者登録銘柄()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.内部者銘柄名にセットする。<BR>
     * <BR>
     * 　@　@　@[get内部者登録銘柄()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６６）業種区分<BR>
     * 　@　@　@get業種区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.各社拡張項目（区分１）にセットする。<BR>
     * <BR>
     * 　@　@　@[get業種区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６７）収入の形態区分<BR>
     * 　@　@　@get収入の形態区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.各社拡張項目（区分２）にセットする。<BR>
     * <BR>
     * 　@　@　@[get収入の形態区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６８）内部者関係区分<BR>
     * 　@　@　@get内部者関係区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.各社拡張項目（区分３）にセットする。<BR>
     * <BR>
     * 　@　@　@[get内部者関係区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－６９）投資目的区分<BR>
     * 　@　@　@get投資目的区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.各社拡張項目（区分４）にセットする。<BR>
     * <BR>
     * 　@　@　@[get投資目的区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－７０）取引動機@区分<BR>
     * 　@　@　@get取引動機@区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.各社拡張項目（区分５）にセットする。<BR>
     * <BR>
     * 　@　@　@[get取引動機@区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－７１）本人確認書類区分<BR>
     * 　@　@　@get本人確認書類区分()にて、指定行番号のデータを取得し、<BR>
     * 　@　@　@口座開設見込客行.各社拡張項目（区分６）にセットする。<BR>
     * <BR>
     * 　@　@　@[get本人確認書類区分()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－７２）必須項目にデフォルト値をセット<BR>
     * 　@　@　@－口座開設見込客行.既存口座フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 現物株式フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 信用取引フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 債券フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 転換社債フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 投資信託（株式）フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 投資信託（公社債）フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 先物・オプションフラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 外国証券フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.投資経験有無 その他フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.興味のあるお取引 株式ミニ投資フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.興味のあるお取引 その他フラグ == 0：DEFAULT<BR>
     * 　@　@　@－口座開設見込客行.検索用区分 == 0：DEFAULT<BR>
     * <BR>
     * 　@※ boolean型の項目は、対応するBooleanEnum値に変換すること。<BR>
     * <BR>
     * ３）　@オブジェクト生成<BR>
     * 　@生成した行オブジェクトを指定し、口座開設見込客オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを返却する。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return WEB3AccOpenExpAccountOpen
     * @@throws WEB3BaseException
     * @@roseuid 4733C7040043
     */
    public WEB3AccOpenExpAccountOpen toExpAccountOpen(
        int l_intLineNo, String l_strInstitutionCode) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "toExpAccountOpen(int, String)";
    	log.entering(STR_METHOD_NAME);

        BooleanEnum l_insiderFlag = BooleanEnum.FALSE;
    	//１）　@行オブジェクト生成
    	//　@デフォルトコンストラクタにて、口座開設見込客行オブジェクトを生成する。
    	ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams();

    	//２）　@プロパティセット
    	//　@２－１）証券会社コード
    	//　@　@　@口座開設見込客行.証券会社コードに引数.証券会社コードをセットする
    	l_expAccountOpenParams.setInstitutionCode(l_strInstitutionCode);

    	//　@２－２）識別コード
    	//　@　@　@get識別コード()にて、指定行番号のデータを取得し、
    	//　@　@　@口座開設見込客行.識別コードにセットする。
    	//　@　@　@[get識別コード()に指定する引数]
    	//　@　@　@行番号：　@行番号
    	l_expAccountOpenParams.setAccOpenRequestNumber(this.getRequestNumber(l_intLineNo));

    	//　@２－３）部店コード
    	//　@　@　@get部店コード()にて、指定行番号のデータを取得し、
    	//　@　@　@口座開設見込客行.部店コードにセットする。
    	//　@　@　@[get部店コード()に指定する引数]
    	//　@　@　@行番号：　@行番号
    	l_expAccountOpenParams.setBranchCode(this.getBranchCode(l_intLineNo));

        //　@２－４）顧客コード
        //　@　@　@get顧客コード()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.口座コードにセットする。
        //
        //　@　@　@[get顧客コード()に指定する引数]
        //　@　@　@行番号：　@行番号
    	l_expAccountOpenParams.setAccountCode(this.getAccountCode(l_intLineNo));

        //　@２－５）扱者コード（SONAR）
        //　@　@　@get扱者コード（SONAR）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.扱者コード（SONAR）にセットする。
        //
        //　@　@　@[get扱者コード（SONAR）()に指定する引数]
        //　@　@　@行番号：　@行番号
    	l_expAccountOpenParams.setSonarTraderCode(this.getSonarTraderCode(l_intLineNo));

        //　@２－６）口座区分
        //　@　@　@get口座区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.口座区分にセットする。
        //　@　@　@[get口座区分()に指定する引数]
        //　@　@　@行番号：　@行番号
    	l_expAccountOpenParams.setAccountDiv(this.getAccountDiv(l_intLineNo));

        //　@２－７）入力区分
        //　@　@　@get入力区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.入力区分にセットする。
        //　@　@　@[get入力区分()に指定する引数]
        //　@　@　@行番号：　@行番号
    	l_expAccountOpenParams.setOrderDiv(this.getOrderDiv(l_intLineNo));

        //　@２－８）資料請求日時
        //　@　@　@get資料請求日時()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.資料請求日時にセットする。
        //　@　@　@[get資料請求日時()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strTimeStyle = WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS;
        String l_strInfoClaimDate = this.getInfoClaimDate(l_intLineNo);
        if (l_strInfoClaimDate != null)
        {
            Date l_datInfoClaimDate = WEB3DateUtility.getDate(this.getInfoClaimDate(l_intLineNo), l_strTimeStyle);
            l_expAccountOpenParams.setInfomationClaimDatetime(
                new Timestamp(l_datInfoClaimDate.getTime()));
        }

        //　@２－９）お名前　@姓（漢字）
        //　@　@　@getお名前　@姓（漢字）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.顧客姓（漢字）にセットする。
        //　@　@　@[getお名前　@姓（漢字）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFamilyName(this.getAccountFamilyName(l_intLineNo));

        //　@２－１０）お名前　@名（漢字）
        //　@　@　@getお名前　@名（漢字）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.顧客名（漢字）にセットする。
        //　@　@　@[getお名前　@名（漢字）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setGivenName(this.getAccountName(l_intLineNo));

        //　@２－１１）お名前　@姓（フリガナ）
        //　@　@　@getお名前　@姓（フリガナ）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.顧客姓（カナ）にセットする。
        //　@　@　@[getお名前　@姓（フリガナ）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFamilyNameAlt1(this.getAccountFamilyNameKana(l_intLineNo));

        //　@２－１２）お名前　@名（フリガナ）
        //　@　@　@getお名前　@名（フリガナ）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.顧客名（カナ）にセットする。
        //　@　@　@[getお名前　@名（フリガナ）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setGivenNameAlt1(this.getAccountNameKana(l_intLineNo));

        //　@２－１３）性別
        //　@　@　@get性別()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.性別にセットする。
        //　@　@　@[get性別()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setSex(this.getSex(l_intLineNo));

        //　@２－１４）生年月日年号
        //　@　@　@get生年月日年号()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.生年月日年号にセットする。
        //　@　@　@[get生年月日年号()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setEraBorn(this.getEraBorn(l_intLineNo));

        //　@２－１５）生年月日
        //　@　@　@get生年月日()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.生年月日にセットする。
        //　@　@　@[get生年月日()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setBornDate(this.getBornDate(l_intLineNo));

        //　@２－１６）メールアドレス
        //　@　@　@getメールアドレス()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.emailアドレスにセットする。
        //　@　@　@[getメールアドレス()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setEmailAddress(this.getMailAddress(l_intLineNo));

        //　@２－１７）郵便番号
        //　@　@　@get郵便番号()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.郵便番号にセットする。
        //　@　@　@[get郵便番号()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setZipCode(this.getZipCode(l_intLineNo));

        //　@２－１８）住所１（漢字）
        //　@　@　@get住所１（漢字）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.住所１にセットする。
        //　@　@　@[get住所１（漢字）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAddressLine1(this.getAddress1(l_intLineNo));

        //　@２－１９）住所２（漢字）
        //　@　@　@get住所２（漢字）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.住所２にセットする。
        //　@　@　@[get住所２（漢字）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAddressLine2(this.getAddress2(l_intLineNo));

        //　@２－２０）住所３（漢字）
        //　@　@　@get住所３（漢字）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.住所３にセットする。
        //　@　@　@[get住所３（漢字）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAddressLine3(this.getAddress3(l_intLineNo));

        //　@２－２１）住所１（カナ）
        //　@　@　@get住所１（カナ）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.住所１（カナ）にセットする。
        //　@　@　@[get住所１（カナ）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAddressLine1Kana(this.getAddressKana1(l_intLineNo));

        //　@２－２２）住所２（カナ）
        //　@　@　@get住所２（カナ）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.住所２（カナ）にセットする。
        //　@　@　@[get住所２（カナ）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAddressLine2Kana(this.getAddressKana2(l_intLineNo));

        //　@２－２３）住所３（カナ）
        //　@　@　@get住所３（カナ）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.住所３（カナ）にセットする。
        //　@　@　@[get住所３（カナ）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAddressLine3Kana(this.getAddressKana3(l_intLineNo));

        //　@２－２４）電話番号
        //
        //　@　@２－２４－１）電話番号（市外局番）
        //　@　@　@get電話番号（市外局番）()にて、指定行番号のデータを取得。
        //　@　@　@[get電話番号（市外局番）()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strTelephoneAreaCode = this.getTelephoneAreaCode(l_intLineNo);

        //　@　@２－２４－２）電話番号（局番）
        //　@　@　@get電話番号（局番）()にて、指定行番号のデータを取得。
        //　@　@　@[get電話番号（局番）()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strTelephoneExchangeNumber = this.getTelephoneExchangeNumber(l_intLineNo);

        //　@　@２－２４－３）電話番号（番号）
        //　@　@　@get電話番号（番号）()にて、指定行番号のデータを取得。
        //　@　@　@[get電話番号（番号）()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strTelephoneNumber = this.getTelephoneNumber(l_intLineNo);

        //　@　@２－２４－４）口座開設見込客行.電話番号に以下をセットする。
        //　@　@　@get電話番号（市外局番）()の戻り値 + "-" +
        //　@　@　@get電話番号（局番）()の戻り値 + "-" +
        //　@　@　@get電話番号（番号）()の戻り値
        if (l_strTelephoneAreaCode != null
            || l_strTelephoneExchangeNumber != null
            || l_strTelephoneNumber != null)
        {
            l_expAccountOpenParams.setTelephone(l_strTelephoneAreaCode + "-"
                + l_strTelephoneExchangeNumber + "-"
                + l_strTelephoneNumber);
        }

        //　@２－２５）携帯番号
        //
        //　@　@２－２５－１）携帯番号（市外局番）
        //　@　@　@get携帯番号（市外局番）()にて、指定行番号のデータを取得。
        //　@　@　@[get携帯番号（市外局番）()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strMobileAreaCode = this.getMobileAreaCode(l_intLineNo);

        //　@　@２－２５－２）携帯番号（局番）
        //　@　@　@get携帯番号（局番）()にて、指定行番号のデータを取得。
        //　@　@　@[get携帯番号（局番）()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strMobileExchangeNumber = this.getMobileExchangeNumber(l_intLineNo);

        //　@　@２－２５－３）携帯番号（番号）
        //　@　@　@get携帯番号（番号）()にて、指定行番号のデータを取得。
        //　@　@　@[get携帯番号（番号）()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strMobileNumber = this.getMobileNumber(l_intLineNo);

        //　@　@２－２５－４）口座開設見込客行.連絡先電話番号（携帯）に以下をセットする。
        //　@　@　@get携帯番号（市外局番）()の戻り値 + "-" +
        //　@　@　@get携帯番号（局番）()の戻り値 + "-" +
        //　@　@　@get携帯番号（番号）()の戻り値
        if (l_strMobileAreaCode != null
            || l_strMobileExchangeNumber != null
            || l_strMobileNumber != null)
        {
            l_expAccountOpenParams.setMobile(l_strMobileAreaCode + "-"
                + l_strMobileExchangeNumber + "-" + l_strMobileNumber);
        }

        //　@２－２６）職業区分
        //　@　@　@get職業区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.職業区分にセットする。
        //　@　@　@[get職業区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setOccupationDiv(this.getOccupationDiv(l_intLineNo));

        //　@２－２７）勤務先名称
        //　@　@　@get勤務先名称()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.勤務先名称にセットする。
        //　@　@　@[get勤務先名称()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setOffice(this.getOffice(l_intLineNo));

        //　@２－２８）勤務先郵便番号
        //　@　@　@get勤務先郵便番号()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.勤務先郵便番号にセットする。
        //　@　@　@[get勤務先郵便番号()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setOfficeZipCode(this.getOfficeZipCode(l_intLineNo));

        //　@２－２９）勤務先住所
        //　@　@　@get勤務先住所()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.勤務先住所にセットする。
        //　@　@　@[get勤務先住所()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setOfficeAddress(this.getOfficeAddress(l_intLineNo));

        //　@２－３０）勤務先電話番号
        //
        //　@　@２－３０－１）get勤務先電話番号１
        //　@　@　@get勤務先電話番号１()にて、指定行番号のデータを取得。
        //　@　@　@[get勤務先電話番号１()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strOfficeTelephone1 = this.getOfficeTelephone1(l_intLineNo);

        //　@　@２－３０－２）get勤務先電話番号２
        //　@　@　@get勤務先電話番号２()にて、指定行番号のデータを取得。
        //　@　@　@[get勤務先電話番号２()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strOfficeTelephone2 = this.getOfficeTelephone2(l_intLineNo);

        //　@　@２－３０－３）get勤務先電話番号１
        //　@　@　@get勤務先電話番号３()にて、指定行番号のデータを取得。
        //　@　@　@[get勤務先電話番号３()に指定する引数]
        //　@　@　@行番号：　@行番号
        String l_strOfficeTelephone3 = this.getOfficeTelephone3(l_intLineNo);

        //　@　@２－３０－４）口座開設見込客行.勤務先電話番号以下をセットする。
        //　@　@　@get勤務先電話番号１()の戻り値 + "-" +
        //　@　@　@get勤務先電話番号２()の戻り値 + "-" +
        //　@　@　@get勤務先電話番号３()の戻り値
        if (l_strOfficeTelephone1 != null
            || l_strOfficeTelephone2 != null
            || l_strOfficeTelephone3 != null)
        {
            l_expAccountOpenParams.setOfficeTelephone(l_strOfficeTelephone1 + "-"
                + l_strOfficeTelephone2 + "-" + l_strOfficeTelephone3);
        }

        //　@２－３１）所属部署
        //　@　@　@get所属部署()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.所属部署にセットする。
        //　@　@　@[get所属部署()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setDepartment(this.getDepartment(l_intLineNo));

        //　@２－３２）役職
        //　@　@　@get役職()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.役職にセットする。
        //　@　@　@[get役職()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setPost(this.getPost(l_intLineNo));

        //　@２－３３）世帯主との続柄
        //　@　@　@get世帯主との続柄()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.続柄区分にセットする。
        //　@　@　@[get世帯主との続柄()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFamilyRelationship(this.getHouseHolderFamilyRelation(l_intLineNo));

        //　@２－３４）世帯主名
        //　@　@　@get世帯主名()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.世帯主名（漢字）にセットする。
        //　@　@　@[get世帯主名()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setHouseholder(this.getHouseHolder(l_intLineNo));

        //　@２－３５）世帯主　@勤務先
        //　@　@　@get世帯主　@勤務先()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.世帯主勤務先にセットする。
        //　@　@　@[get世帯主　@勤務先()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setHouseholderOffice(this.getHouseHolderOffice(l_intLineNo));

        //　@２－３６）世帯主　@役職
        //　@　@　@get世帯主　@役職()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.世帯主役職にセットする。
        //　@　@　@[get世帯主　@役職()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setHouseholderPost(this.getHouseHolderPost(l_intLineNo));

        //　@２－３７）振込先
        //　@　@　@get振込先()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 振替区分にセットする。
        //　@　@　@[get振込先()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setTransferDiv(this.getTransfer(l_intLineNo));

        //　@２－３８）銀行コード
        //　@　@　@get銀行コード()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 銀行コードにセットする。
        //　@　@　@[get銀行コード()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFinInstitutionCode(this.getFinInstitutionCode(l_intLineNo));

        //　@２－３９）銀行名
        //　@　@　@get銀行名()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 銀行名にセットする。
        //　@　@　@[get銀行名()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFinInstitutionName(this.getFinInstitutionName(l_intLineNo));

        //　@２－４０）支店コード
        //　@　@　@get支店コード()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 支店コードにセットする。
        //　@　@　@[get支店コード()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFinBranchCode(this.getFinBranchCode(l_intLineNo));

        //　@２－４１）支店名
        //　@　@　@get支店名()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 支店名にセットする。
        //　@　@　@[get支店名()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFinBranchName(this.getFinBranchName(l_intLineNo));

        //　@２－４２）預金区分
        //　@　@　@get預金区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 預金区分にセットする。
        //　@　@　@[get預金区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFinSaveDiv(this.getFinSaveDiv(l_intLineNo));

        //　@２－４３）口座番号
        //　@　@　@get口座番号()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 口座番号にセットする。
        //　@　@　@[get口座番号()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setFinAccountNo(this.getFinAccountNo(l_intLineNo));

        //　@２－４４）通帳記号
        //　@　@　@get通帳記号()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 通帳記号にセットする。
        //　@　@　@[get通帳記号()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setPostalSaveCode(this.getPostalSaveCode(l_intLineNo));

        //　@２－４５）通帳番号
        //　@　@　@get通帳番号()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.振込先金融機@関情報 通帳番号にセットする。
        //　@　@　@[get通帳番号()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setPostalSaveNo(this.getPostalSaveNo(l_intLineNo));

        //　@２－４６）投資経験（株式現物取引）
        //　@　@　@get投資経験（株式現物取引）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 現物株式にセットする。
        //　@　@　@[get投資経験（株式現物取引）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivEquity(this.getExperienceEquityDiv(l_intLineNo));

        //　@２－４７）投資経験（株式信用取引）
        //　@　@　@get投資経験（株式信用取引）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 信用取引にセットする。
        //　@　@　@[get投資経験（株式信用取引）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivMargin(this.getExperienceMarginDiv(l_intLineNo));

        //　@２－４８）投資経験（債券）
        //　@　@　@get投資経験（債券）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 債券にセットする。
        //　@　@　@[get投資経験（債券）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivBond(this.getExperienceBondDiv(l_intLineNo));

        //　@２－４９）投資経験（株価指数オプション）
        //　@　@　@get投資経験（株価指数オプション）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 転換社債にセットする。
        //　@　@　@[get投資経験（株価指数オプション）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivWt(this.getExperienceOptionsDiv(l_intLineNo));

        //　@２－５０）投資経験（投資信託：株式）
        //　@　@　@get投資経験（投資信託：株式）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 投資信託（株式）にセットする。
        //　@　@　@[get投資経験（投資信託：株式）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivFundSk(this.getExperienceFundSkDiv(l_intLineNo));

        //　@２－５１）投資経験（投資信託：公社債）
        //　@　@　@get投資経験（投資信託：公社債）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 投資信託（公社債）にセットする。
        //　@　@　@[get投資経験（投資信託：公社債）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivFundBd(this.getExperienceFundBdDiv(l_intLineNo));

        //　@２－５２）投資経験（株価指数先物）
        //　@　@　@get投資経験（株価指数先物）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 先物・オプションにセットする。
        //　@　@　@[get投資経験（株価指数先物）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivFo(this.getExperienceFuturesDiv(l_intLineNo));

        //　@２－５３）投資経験（外国証券）
        //　@　@　@get投資経験（外国証券）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 外国証券にセットする。
        //　@　@　@[get投資経験（外国証券）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivFEquity(this.getExperienceFEquityDiv(l_intLineNo));

        //　@２－５４）投資経験（その他）
        //　@　@　@get投資経験（その他）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.投資経験 その他にセットする。
        //　@　@　@[get投資経験（その他）()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExperienceDivEtc(this.getExperienceEtcDiv(l_intLineNo));

        //　@２－５５）希望される取引の種類　@株式（現物）
        //　@　@　@get希望される取引の種類　@株式（現物）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.興味のあるお取引 現物株式フラグにセットする。
        //　@　@　@[get希望される取引の種類　@株式（現物）()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestEquityFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagEquity(l_insiderFlag);

        //　@２－５６）希望される取引の種類　@株式（信用）
        //　@　@　@get希望される取引の種類　@株式（信用）()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.興味のあるお取引 信用取引フラグにセットする。
        //　@　@　@[get希望される取引の種類　@株式（信用）()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestMarginFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagMargin(l_insiderFlag);

        //　@２－５７）希望される取引の種類　@国債先物
        //　@　@　@get希望される取引の種類　@国債先物()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.興味のあるお取引 債券フラグにセットする。
        //　@　@　@[get希望される取引の種類　@国債先物()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestBondFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagBond(l_insiderFlag);

        //　@２－５８）希望される取引の種類　@投資信託
        //　@　@　@get希望される取引の種類　@投資信託()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.興味のあるお取引 投資信託フラグにセットする。
        //　@　@　@[get希望される取引の種類　@投資信託()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestFundFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagFund(l_insiderFlag);

        //　@２－５９）希望される取引の種類　@株価指数先物
        //　@　@　@get希望される取引の種類　@株価指数先物()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.興味のあるお取引 先物・オプションフラグにセットする。
        //　@　@　@[get希望される取引の種類　@株価指数先物()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestFuturesFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagFo(l_insiderFlag);

        //　@２－６０）希望される取引の種類　@株価指数オプション
        //　@　@　@get希望される取引の種類　@株価指数オプション()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.興味のあるお取引 外国証券フラグにセットする。
        //　@　@　@[get希望される取引の種類　@株価指数オプション()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInterestOptionsFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInterestFlagFEquity(l_insiderFlag);

        //　@２－６１）年収区分
        //　@　@　@get年収区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.年収区分にセットする。
        //　@　@　@[get年収区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAnnualIncomeDiv(this.getAnnualIncomeDiv(l_intLineNo));

        //　@２－６２）金融資産区分
        //　@　@　@get金融資産区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.金融資産区分にセットする。
        //　@　@　@[get金融資産区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setAssetValueDiv(this.getAssetValueDiv(l_intLineNo));

        //　@２－６３）特定口座区分現物
        //　@　@　@get特定口座区分現物()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.特定口座区分にセットする。
        //　@　@　@[get特定口座区分現物()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setSpecialAcc(this.getSpecialAccEquity(l_intLineNo));

        //　@２－６４）内部者登録区分
        //　@　@　@get内部者登録区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.内部者登録フラグにセットする。
        //　@　@　@[get内部者登録区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        if ((BooleanEnum.TRUE.intValue() + "").equals(this.getInsiderFlag(l_intLineNo)))
        {
            l_insiderFlag = BooleanEnum.TRUE;
        }
        else
        {
            l_insiderFlag = BooleanEnum.FALSE;
        }
        l_expAccountOpenParams.setInsiderFlag(l_insiderFlag);

        //　@２－６５）内部者登録銘柄
        //　@　@　@get内部者登録銘柄()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.内部者銘柄名にセットする。
        //　@　@　@[get内部者登録銘柄()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setProductName(this.getProductName(l_intLineNo));

        //　@２－６６）業種区分
        //　@　@　@get業種区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.各社拡張項目（区分１）にセットする。
        //　@　@　@[get業種区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExtItemDiv1(this.getTypeDiv(l_intLineNo));

        //　@２－６７）収入の形態区分
        //　@　@　@get収入の形態区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.各社拡張項目（区分２）にセットする。
        //　@　@　@[get収入の形態区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExtItemDiv2(this.getIncomeDormDiv(l_intLineNo));

        //　@２－６８）内部者関係区分
        //　@　@　@get内部者関係区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.各社拡張項目（区分３）にセットする。
        //　@　@　@[get内部者関係区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExtItemDiv3(this.getInsiderRelationDiv(l_intLineNo));

        //　@２－６９）投資目的区分
        //　@　@　@get投資目的区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.各社拡張項目（区分４）にセットする。
        //　@　@　@[get投資目的区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExtItemDiv4(this.getInvestPurposeDiv(l_intLineNo));

        //　@２－７０）取引動機@区分
        //　@　@　@get取引動機@区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.各社拡張項目（区分５）にセットする。
        //　@　@　@[get取引動機@区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExtItemDiv5(this.getAppliMotivatDiv(l_intLineNo));

        //　@２－７１）本人確認書類区分
        //　@　@　@get本人確認書類区分()にて、指定行番号のデータを取得し、
        //　@　@　@口座開設見込客行.各社拡張項目（区分６）にセットする。
        //　@　@　@[get本人確認書類区分()に指定する引数]
        //　@　@　@行番号：　@行番号
        l_expAccountOpenParams.setExtItemDiv6(this.getIDConfirmDocDiv(l_intLineNo));

        //２－７２）必須項目にデフォルト値をセット
        //－口座開設見込客行.既存口座フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 現物株式フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 信用取引フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 債券フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 転換社債フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 投資信託（株式）フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 投資信託（公社債）フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 先物・オプションフラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 外国証券フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        //－口座開設見込客行.投資経験有無 その他フラグ == 0：DEFAULT
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        //－口座開設見込客行.興味のあるお取引 株式ミニ投資フラグ == 0：DEFAULT
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        //－口座開設見込客行.興味のあるお取引 その他フラグ == 0：DEFAULT
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        //－口座開設見込客行.検索用区分 == 0：DEFAULT
        l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);

        // ３）　@オブジェクト生成
        //  生成した行オブジェクトを指定し、口座開設見込客オブジェクトを生成する。
        //  生成したオブジェクトを返却する。
    	WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen =
    		new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);
    	log.exiting(STR_METHOD_NAME);

        return l_accOpenExpAccountOpen;
    }

    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.レコード番号ラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.識別コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.部店コードラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.顧客コードラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.扱者コード（SONAR）ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.口座区分ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.入力区分ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.資料請求日時ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.お名前　@姓（漢字）ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.お名前　@名（漢字）ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.お名前　@姓（フリガナ）ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.お名前　@名（フリガナ）ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.性別ラベル<BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.生年月日年号ラベル<BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 14<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.生年月日ラベル<BR>
     * 　@カラム番号： 14<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 15<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.メールアドレスラベル<BR>
     * 　@カラム番号： 15<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 16<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.郵便番号ラベル<BR>
     * 　@カラム番号： 16<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 17<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.住所１（漢字）ラベル<BR>
     * 　@カラム番号： 17<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 18<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.住所２（漢字）ラベル<BR>
     * 　@カラム番号： 18<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 19<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.住所３（漢字）ラベル<BR>
     * 　@カラム番号： 19<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 20<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.住所１（カナ）ラベル<BR>
     * 　@カラム番号： 20<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 21<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.住所２（カナ）ラベル<BR>
     * 　@カラム番号： 21<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 22<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.住所３（カナ）ラベル<BR>
     * 　@カラム番号： 22<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 23<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.電話番号（市外局番）ラベル<BR>
     * 　@カラム番号： 23<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 24<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.電話番号（局番）ラベル<BR>
     * 　@カラム番号： 24<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 25<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.電話番号（番号）ラベル<BR>
     * 　@カラム番号： 25<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 26<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.携帯番号（市外局番）ラベル<BR>
     * 　@カラム番号： 26<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 27<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.携帯番号（局番）ラベル<BR>
     * 　@カラム番号： 27<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 28<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.携帯番号（番号）ラベル<BR>
     * 　@カラム番号： 28<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 29<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.職業区分ラベル<BR>
     * 　@カラム番号： 29<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 30<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.勤務先名称ラベル<BR>
     * 　@カラム番号： 30<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 31<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.勤務先郵便番号ラベル<BR>
     * 　@カラム番号： 31<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 32<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.勤務先住所ラベル<BR>
     * 　@カラム番号： 32<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 33<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.勤務先電話番号１ラベル<BR>
     * 　@カラム番号： 33<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 34<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.勤務先電話番号２ラベル<BR>
     * 　@カラム番号： 34<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 35<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.勤務先電話番号３ラベル<BR>
     * 　@カラム番号： 35<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 36<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.所属部署ラベル<BR>
     * 　@カラム番号： 36<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 37<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.役職ラベル<BR>
     * 　@カラム番号： 37<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 38<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.世帯主との続柄ラベル<BR>
     * 　@カラム番号： 38<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 39<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.世帯主名ラベル<BR>
     * 　@カラム番号： 39<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 40<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.世帯主　@勤務先ラベル<BR>
     * 　@カラム番号： 40<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 41<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.世帯主　@役職ラベル<BR>
     * 　@カラム番号： 41<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 42<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.振込先ラベル<BR>
     * 　@カラム番号： 42<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 43<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.銀行コードラベル<BR>
     * 　@カラム番号： 43<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 44<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.銀行名ラベル<BR>
     * 　@カラム番号： 44<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 45<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.支店コードラベル<BR>
     * 　@カラム番号： 45<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 46<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.支店名ラベル<BR>
     * 　@カラム番号： 46<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 47<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.預金区分ラベル<BR>
     * 　@カラム番号： 47<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 48<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.口座番号ラベル<BR>
     * 　@カラム番号： 48<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 49<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.通帳記号ラベル<BR>
     * 　@カラム番号： 49<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 50<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.通帳番号ラベル<BR>
     * 　@カラム番号： 50<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 51<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（株式現物取引）ラベル<BR>
     * 　@カラム番号： 51<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 52<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（株式信用取引）ラベル<BR>
     * 　@カラム番号： 52<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 53<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（債券）ラベル<BR>
     * 　@カラム番号： 53<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 54<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（株価指数オプション）ラベル<BR>
     * 　@カラム番号： 54<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 55<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（投資信託：株式）ラベル<BR>
     * 　@カラム番号： 55<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 56<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（投資信託：公社債）ラベル<BR>
     * 　@カラム番号： 56<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 57<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（株価指数先物）ラベル<BR>
     * 　@カラム番号： 57<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 58<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（外国証券）ラベル<BR>
     * 　@カラム番号： 58<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 59<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資経験（その他）ラベル<BR>
     * 　@カラム番号： 59<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 60<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株式（現物）ラベル<BR>
     * 　@カラム番号： 60<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 61<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株式（信用）ラベル<BR>
     * 　@カラム番号： 61<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 62<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@国債先物ラベル<BR>
     * 　@カラム番号： 62<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 63<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@投資信託ラベル<BR>
     * 　@カラム番号： 63<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 64<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株価指数先物ラベル<BR>
     * 　@カラム番号： 64<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 65<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株価指数オプションラベル
     * <BR>
     * 　@カラム番号： 65<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 66<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.年収区分ラベル<BR>
     * 　@カラム番号： 66<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 67<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.金融資産区分ラベル<BR>
     * 　@カラム番号： 67<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 68<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.特定口座区分現物ラベル<BR>
     * 　@カラム番号： 68<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 69<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.内部者登録区分ラベル<BR>
     * 　@カラム番号： 69<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 70<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.内部者登録銘柄ラベル<BR>
     * 　@カラム番号： 70<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 71<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.業種区分ラベル<BR>
     * 　@カラム番号： 71<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 72<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.収入の形態区分ラベル<BR>
     * 　@カラム番号： 72<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 73<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.内部者関係区分ラベル<BR>
     * 　@カラム番号： 73<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 74<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.投資目的区分ラベル<BR>
     * 　@カラム番号： 74<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 75<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.取引動機@区分ラベル<BR>
     * 　@カラム番号： 75<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 76<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@口座開設申込ULCSV.本人確認書類区分ラベル<BR>
     * 　@カラム番号： 76<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * @@roseuid 472FD2E603BD
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 77;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //index = 0
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.レコード番号ラベル
        //カラム番号： 0
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            recordNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 1
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.識別コードラベル
        //カラム番号： 1
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            requestNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 2
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.部店コードラベル
        //カラム番号： 2
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            branchCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 3
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.顧客コードラベル
        //カラム番号： 3
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 4
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.扱者コード（SONAR）ラベル
        //カラム番号： 4
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            sonarTraderCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 5
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.口座区分ラベル
        //カラム番号： 5
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountTypeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 6
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.入力区分ラベル
        //カラム番号： 6
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            inputDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 7
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.資料請求日時ラベル
        //カラム番号： 7
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            infoClaimDateLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 8
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.お名前　@姓（漢字）ラベル
        //カラム番号： 8
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountFamilyNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index = 9
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@口座開設申込ULCSV.お名前　@名（漢字）ラベル
        //カラム番号： 9
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //      －　@index = 10
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.お名前　@姓（フリガナ）ラベル
        //     　@カラム番号： 10
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountFamilyNameKanaLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 11
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.お名前　@名（フリガナ）ラベル
        //     　@カラム番号： 11
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountNameKanaLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 12
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.性別ラベル
        //     　@カラム番号： 12
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            sexLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 13
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.生年月日年号ラベル
        //     　@カラム番号： 13
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            eraBornLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 14
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.生年月日ラベル
        //     　@カラム番号： 14
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            bornDateLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 15
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.メールアドレスラベル
        //     　@カラム番号： 15
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mailAddressLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 16
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.郵便番号ラベル
        //     　@カラム番号： 16
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            zipCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 17
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.住所１（漢字）ラベル
        //     　@カラム番号： 17
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            address1Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 18
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.住所２（漢字）ラベル
        //     　@カラム番号： 18
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            address2Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 19
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.住所３（漢字）ラベル
        //     　@カラム番号： 19
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            address3Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 20
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.住所１（カナ）ラベル
        //     　@カラム番号： 20
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            addressKana1Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 21
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.住所２（カナ）ラベル
        //     　@カラム番号： 21
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            addressKana2Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 22
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.住所３（カナ）ラベル
        //     　@カラム番号： 22
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            addressKana3Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 23
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.電話番号（市外局番）ラベル
        //     　@カラム番号： 23
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            telephoneAreaCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 24
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.電話番号（局番）ラベル
        //     　@カラム番号： 24
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            telephoneExchangeNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 25
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.電話番号（番号）ラベル
        //     　@カラム番号： 25
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            telephoneNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 26
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.携帯番号（市外局番）ラベル
        //     　@カラム番号： 26
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mobileAreaCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 27
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.携帯番号（局番）ラベル
        //     　@カラム番号： 27
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mobileExchangeNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 28
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.携帯番号（番号）ラベル
        //     　@カラム番号： 28
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            mobileNumberLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 29
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.職業区分ラベル
        //     　@カラム番号： 29
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            occupationDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 30
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.勤務先名称ラベル
        //     　@カラム番号： 30
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 31
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.勤務先郵便番号ラベル
        //     　@カラム番号： 31
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeZipCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 32
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.勤務先住所ラベル
        //     　@カラム番号： 32
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeAddressLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 33
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.勤務先電話番号１ラベル
        //     　@カラム番号： 33
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeTelephone1Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 34
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.勤務先電話番号２ラベル
        //     　@カラム番号： 34
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeTelephone2Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 35
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.勤務先電話番号３ラベル
        //     　@カラム番号： 35
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            officeTelephone3Label,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 36
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.所属部署ラベル
        //     　@カラム番号： 36
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            departmentLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 37
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.役職ラベル
        //     　@カラム番号： 37
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            postLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 38
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.世帯主との続柄ラベル
        //     　@カラム番号： 38
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderFamilyRelationLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 39
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.世帯主名ラベル
        //     　@カラム番号： 39
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 40
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.世帯主　@勤務先ラベル
        //     　@カラム番号： 40
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderOfficeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 41
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.世帯主　@役職ラベル
        //     　@カラム番号： 41
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            houseHolderPostLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 42
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.振込先ラベル
        //     　@カラム番号： 42
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            transferLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 43
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.銀行コードラベル
        //     　@カラム番号： 43
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finInstitutionCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 44
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.銀行名ラベル
        //     　@カラム番号： 44
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finInstitutionNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 45
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.支店コードラベル
        //     　@カラム番号： 45
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finBranchCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 46
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.支店名ラベル
        //     　@カラム番号： 46
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finBranchNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 47
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.預金区分ラベル
        //     　@カラム番号： 47
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finSaveDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 48
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.口座番号ラベル
        //     　@カラム番号： 48
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            finAccountNoLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 49
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.通帳記号ラベル
        //     　@カラム番号： 49
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            postalSaveCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 50
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.通帳番号ラベル
        //     　@カラム番号： 50
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            postalSaveNoLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 51
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（株式現物取引）ラベル
        //     　@カラム番号： 51
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceEquityDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 52
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（株式信用取引）ラベル
        //     　@カラム番号： 52
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceMarginDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 53
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（債券）ラベル
        //     　@カラム番号： 53
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceBondDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 54
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（株価指数オプション）ラベル
        //     　@カラム番号： 54
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceOptionsDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 55
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（投資信託：株式）ラベル
        //     　@カラム番号： 55
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFundSkDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 56
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（投資信託：公社債）ラベル
        //     　@カラム番号： 56
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFundBdDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 57
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（株価指数先物）ラベル
        //     　@カラム番号： 57
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFuturesDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 58
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（外国証券）ラベル
        //     　@カラム番号： 58
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceFEquityDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 59
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資経験（その他）ラベル
        //     　@カラム番号： 59
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            experienceEtcDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 60
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株式（現物）ラベル
        //     　@カラム番号： 60
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestEquityFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 61
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株式（信用）ラベル
        //     　@カラム番号： 61
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestMarginFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 62
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@国債先物ラベル
        //     　@カラム番号： 62
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestBondFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 63
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@投資信託ラベル
        //     　@カラム番号： 63
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestFundFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 64
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株価指数先物ラベル
        //     　@カラム番号： 64
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestFuturesFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 65
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.希望される取引の種類　@株価指数オプションラベル
        //     　@カラム番号： 65
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            interestOptionsFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 66
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.年収区分ラベル
        //     　@カラム番号： 66
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            annualIncomeDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 67
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.金融資産区分ラベル
        //     　@カラム番号： 67
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            assetValueDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 68
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.特定口座区分現物ラベル
        //     　@カラム番号： 68
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            specialAccEquityLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 69
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.内部者登録区分ラベル
        //     　@カラム番号： 69
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            insiderFlagLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 70
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.内部者登録銘柄ラベル
        //     　@カラム番号： 70
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            productNameLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 71
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.業種区分ラベル
        //     　@カラム番号： 71
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            typeDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 72
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.収入の形態区分ラベル
        //     　@カラム番号： 72
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            incomeDormDivLable,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 73
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.内部者関係区分ラベル
        //     　@カラム番号： 73
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            insiderRelationDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 74
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.投資目的区分ラベル
        //     　@カラム番号： 74
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            investPurposeDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 75
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.取引動機@区分ラベル
        //     　@カラム番号： 75
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            appliMotivatDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //     －　@index = 76
        //     　@[CSVカラムモデル コンストラクタの引数]
        //     　@項目ラベル：　@口座開設申込ULCSV.本人確認書類区分ラベル
        //     　@カラム番号： 76
        //     　@項目型：　@CSVカラムモデル.項目型_文字列
        //     　@日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            idConfirmDocDivLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getレコード番号)<BR>
     * 行番号に対応する明細行のレコード番号を取得する。<BR>
     * <BR>
     * this.get項目値()にてレコード番号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.レコード番号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 473BFD3100B3
     */
    public String getRecordNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getRecordNumber(int)";
    	log.entering(STR_METHOD_NAME);
    	//カラム：　@getカラムモデル(口座開設申込ULCSV.レコード番号ラベル)
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.recordNumberLabel);
    	//this.get項目値()にてレコード番号を取得し返却する。
    	String l_strRecordNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strRecordNumber;
    }

    /**
     * (get識別コード)<BR>
     * 行番号に対応する明細行の識別コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて識別コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.識別コードラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 473BFD3403BD
     */
    public String getRequestNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getRequestNumber(int)";
    	log.entering(STR_METHOD_NAME);
        //カラム：　@getカラムモデル(口座開設申込ULCSV.識別コードラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.requestNumberLabel);
    	//this.get項目値()にて識別コードを取得し返却する。
    	String l_strRequestNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strRequestNumber;
    }

    /**
     * (get部店コード)<BR>
     * 行番号に対応する明細行の部店コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.部店コードラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF52F0124
     */
    public String getBranchCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getBranchCode(int)";
    	log.entering(STR_METHOD_NAME);
        //カラム：　@getカラムモデル(口座開設申込ULCSV.部店コードラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.branchCodeLabel);
    	//this.get項目値()にて部店コードを取得し返却する。
    	String l_strBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strBranchCode;
    }

    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.顧客コードラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF5D80183
     */
    public String getAccountCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountCode(int)";
    	log.entering(STR_METHOD_NAME);
        //カラム：　@getカラムモデル(口座開設申込ULCSV.顧客コードラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountCodeLabel);
    	//this.get項目値()にて顧客コードを取得し返却する。
    	String l_strAccountCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountCode;
    }

    /**
     * (get扱者コード（SONAR）)<BR>
     * 行番号に対応する明細行の扱者コード（SONAR）を取得する。<BR>
     * <BR>
     * this.get項目値()にて扱者コード（SONAR）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.扱者コード（SONAR）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF62C0092
     */
    public String getSonarTraderCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getSonarTraderCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.扱者コード（SONAR）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.sonarTraderCodeLabel);
    	//this.get項目値()にて扱者コード（SONAR）を取得し返却する。
    	String l_strSonarTraderCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strSonarTraderCode;

     }

    /**
     * (get口座区分)<BR>
     * 行番号に対応する明細行の口座区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて口座区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.口座区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF62D012E
     */
    public String getAccountDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.口座区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountTypeLabel);
    	//this.get項目値()にて口座区分を取得し返却する。
    	String l_strAccountDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountDiv;

    }

    /**
     * (get入力区分)<BR>
     * 行番号に対応する明細行の入力区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて入力区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.入力区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF62E014D
     */
    public String getOrderDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOrderDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.入力区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.inputDivLabel);
    	//this.get項目値()にて入力区分を取得し返却する。
    	String l_strOrderDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOrderDiv;

    }

    /**
     * (get資料請求日時)<BR>
     * 行番号に対応する明細行の資料請求日時を取得する。<BR>
     * <BR>
     * this.get項目値()にて資料請求日時を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.資料請求日時ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF62F010F
     */
    public String getInfoClaimDate(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInfoClaimDate(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.資料請求日時ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.infoClaimDateLabel);
    	//this.get項目値()にて資料請求日時を取得し返却する。
    	String l_strInfoClaimDate = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInfoClaimDate;

    }

    /**
     * (getお名前　@姓（漢字）)<BR>
     * 行番号に対応する明細行のお名前　@姓（漢字）を取得する。<BR>
     * <BR>
     * this.get項目値()にてお名前　@姓（漢字）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@姓（漢字）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF63001DA
     */
    public String getAccountFamilyName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountFamilyName(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@姓（漢字）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountFamilyNameLabel);
    	//this.get項目値()にてお名前　@姓（漢字）を取得し返却する。
    	String l_strAccountFamilyName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountFamilyName;

    }

    /**
     * (getお名前　@名（漢字）)<BR>
     * 行番号に対応する明細行のお名前　@名（漢字）を取得する。<BR>
     * <BR>
     * this.get項目値()にてお名前　@名（漢字）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@名（漢字）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF63500FE
     */
    public String getAccountName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountName(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@名（漢字）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountNameLabel);
    	//this.get項目値()にてお名前　@名（漢字）を取得し返却する。
    	String l_strAccountName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountName;

    }

    /**
     * (getお名前　@姓（フリガナ）)<BR>
     * 行番号に対応する明細行のお名前　@姓（フリガナ）を取得する。<BR>
     * <BR>
     * this.get項目値()にてお名前　@姓（フリガナ）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@姓（フリガナ）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF635010E
     */
    public String getAccountFamilyNameKana(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountFamilyNameKana(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@姓（フリガナ）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountFamilyNameKanaLabel);
    	//this.get項目値()にてお名前　@姓（フリガナ）を取得し返却する。
    	String l_strAccountFamilyNameKana = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountFamilyNameKana;

    }

    /**
     * (getお名前　@名（フリガナ）)<BR>
     * 行番号に対応する明細行のお名前　@名（フリガナ）を取得する。<BR>
     * <BR>
     * this.get項目値()にてお名前　@名（フリガナ）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@名（フリガナ）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF635011E
     */
    public String getAccountNameKana(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAccountNameKana(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.お名前　@名（フリガナ）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.accountNameKanaLabel);
    	//this.get項目値()にてお名前　@名（フリガナ）を取得し返却する。
    	String l_strAccountNameKana = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAccountNameKana;

    }

    /**
     * (get性別)<BR>
     * 行番号に対応する明細行の性別を取得する。<BR>
     * <BR>
     * this.get項目値()にて性別を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.性別ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6350120
     */
    public String getSex(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getSex(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.性別ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.sexLabel);
    	//this.get項目値()にて性別を取得し返却する。
    	String l_strSex = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strSex;

    }

    /**
     * (get生年月日年号)<BR>
     * 行番号に対応する明細行の生年月日年号を取得する。<BR>
     * <BR>
     * this.get項目値()にて生年月日年号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.生年月日年号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF635012D
     */
    public String getEraBorn(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getEraBorn(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.生年月日年号ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.eraBornLabel);
    	//this.get項目値()にて生年月日年号を取得し返却する。
    	String l_strEraBorn = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strEraBorn;

    }

    /**
     * (get生年月日)<BR>
     * 行番号に対応する明細行の生年月日を取得する。<BR>
     * <BR>
     * this.get項目値()にて生年月日を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.生年月日ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6360302
     */
    public String getBornDate(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getBornDate(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.生年月日ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.bornDateLabel);
    	//this.get項目値()にて生年月日を取得し返却する。
    	String l_strBornDate = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strBornDate;

    }

    /**
     * (getメールアドレス)<BR>
     * 行番号に対応する明細行のメールアドレスを取得する。<BR>
     * <BR>
     * this.get項目値()にてメールアドレスを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.メールアドレスラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6360312
     */
    public String getMailAddress(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMailAddress(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.メールアドレスラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mailAddressLabel);
    	//this.get項目値()にてメールアドレスを取得し返却する。
    	String l_strMailAddress = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMailAddress;

    }

    /**
     * (get郵便番号)<BR>
     * 行番号に対応する明細行の郵便番号を取得する。<BR>
     * <BR>
     * this.get項目値()にて郵便番号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.郵便番号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6360321
     */
    public String getZipCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getZipCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.郵便番号ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.zipCodeLabel);
    	//this.get項目値()にて郵便番号を取得し返却する。
    	String l_strZipCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strZipCode;

    }

    /**
     * (get住所１（漢字）)<BR>
     * 行番号に対応する明細行の住所１（漢字）を取得する。<BR>
     * <BR>
     * this.get項目値()にて住所１（漢字）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.住所１（漢字）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6360323
     */
    public String getAddress1(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddress1(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.住所１（漢字）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.address1Label);
    	//this.get項目値()にて住所１（漢字）を取得し返却する。
    	String l_strAddress1 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddress1;

    }

    /**
     * (get住所２（漢字）)<BR>
     * 行番号に対応する明細行の住所２（漢字）を取得する。<BR>
     * <BR>
     * this.get項目値()にて住所２（漢字）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.住所２（漢字）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6360331
     */
    public String getAddress2(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddress2(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.住所２（漢字）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.address2Label);
    	//this.get項目値()にて住所２（漢字）を取得し返却する。
    	String l_strAddress2 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddress2;

    }

    /**
     * (get住所３（漢字）)<BR>
     * 行番号に対応する明細行の住所３（漢字）を取得する。<BR>
     * <BR>
     * this.get項目値()にて住所３（漢字）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.住所３（漢字）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400013
     */
    public String getAddress3(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddress3(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.住所３（漢字）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.address3Label);
    	//this.get項目値()にて住所３（漢字）を取得し返却する。
    	String l_strAddress3 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddress3;

    }

    /**
     * (get住所１（カナ）)<BR>
     * 行番号に対応する明細行の住所１（カナ）を取得する。<BR>
     * <BR>
     * this.get項目値()にて住所１（カナ）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.住所１（カナ）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400023
     */
    public String getAddressKana1(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddressKana1(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.住所１（カナ）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.addressKana1Label);
    	//this.get項目値()にて住所１（カナ）を取得し返却する。
    	String l_strAddressKana1 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddressKana1;

    }

    /**
     * (get住所２（カナ）)<BR>
     * 行番号に対応する明細行の住所２（カナ）を取得する。<BR>
     * <BR>
     * this.get項目値()にて住所２（カナ）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.住所２（カナ）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400025
     */
    public String getAddressKana2(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddressKana2(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.住所２（カナ）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.addressKana2Label);
    	//this.get項目値()にて住所２（カナ）を取得し返却する。
    	String l_strAddressKana2 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddressKana2;

    }

    /**
     * (get住所３（カナ）)<BR>
     * 行番号に対応する明細行の住所３（カナ）を取得する。<BR>
     * <BR>
     * this.get項目値()にて住所３（カナ）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.住所３（カナ）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400032
     */
    public String getAddressKana3(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAddressKana3(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.住所３（カナ）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.addressKana3Label);
    	//this.get項目値()にて住所３（カナ）を取得し返却する。
    	String l_strAddressKana3 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAddressKana3;

    }

    /**
     * (get電話番号（市外局番）)<BR>
     * 行番号に対応する明細行の電話番号（市外局番）を取得する。<BR>
     * <BR>
     * this.get項目値()にて電話番号（市外局番）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.電話番号（市外局番）ラベル)の戻り値値。 <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400042
     */
    public String getTelephoneAreaCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTelephoneAreaCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.電話番号（市外局番）ラベル)の戻り値値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.telephoneAreaCodeLabel);
    	//this.get項目値()にて電話番号（市外局番）を取得し返却する。
    	String l_strTelephoneAreaCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTelephoneAreaCode;

    }

    /**
     * (get電話番号（局番）)<BR>
     * 行番号に対応する明細行の電話番号（局番）を取得する。<BR>
     * <BR>
     * this.get項目値()にて電話番号（局番）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.電話番号（局番）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400052
     */
    public String getTelephoneExchangeNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTelephoneExchangeNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.電話番号（局番）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.telephoneExchangeNumberLabel);
    	//this.get項目値()にて電話番号（局番）を取得し返却する。
    	String l_strTelephoneExchangeNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTelephoneExchangeNumber;

    }

    /**
     * (get電話番号（番号）)<BR>
     * 行番号に対応する明細行の電話番号（番号）を取得する。<BR>
     * <BR>
     * this.get項目値()にて電話番号（番号）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.電話番号（番号）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400061
     */
    public String getTelephoneNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTelephoneNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.電話番号（番号）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.telephoneNumberLabel);
    	//this.get項目値()にて電話番号（番号）を取得し返却する。
    	String l_strTelephoneNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTelephoneNumber;

    }

    /**
     * (get携帯番号（市外局番）)<BR>
     * 行番号に対応する明細行の携帯番号（市外局番）を取得する。<BR>
     * <BR>
     * this.get項目値()にて携帯番号（市外局番）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.携帯番号（市外局番）ラベル)の戻り値値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400071
     */
    public String getMobileAreaCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMobileAreaCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.携帯番号（市外局番）ラベル)の戻り値値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mobileAreaCodeLabel);
    	//this.get項目値()にて携帯番号（市外局番）を取得し返却する。
    	String l_strMobileAreaCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMobileAreaCode;

    }

    /**
     * (get携帯番号（局番）)<BR>
     * 行番号に対応する明細行の携帯番号（局番）を取得する。<BR>
     * <BR>
     * this.get項目値()にて携帯番号（局番）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.携帯番号（局番）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400080
     */
    public String getMobileExchangeNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMobileExchangeNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.携帯番号（局番）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mobileExchangeNumberLabel);
    	//this.get項目値()にて携帯番号（局番）を取得し返却する。
    	String l_strMobileExchangeNumbe = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMobileExchangeNumbe;

    }

    /**
     * (get携帯番号（番号）)<BR>
     * 行番号に対応する明細行の携帯番号（番号）を取得する。<BR>
     * <BR>
     * this.get項目値()にて携帯番号（番号）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.携帯番号（番号）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400082
     */
    public String getMobileNumber(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getMobileNumber(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.携帯番号（番号）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.mobileNumberLabel);
    	//this.get項目値()にて携帯番号（番号）を取得し返却する。
    	String l_strMobileNumber = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strMobileNumber;

    }

    /**
     * (get職業区分)<BR>
     * 行番号に対応する明細行の職業区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて職業区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.職業区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6400090
     */
    public String getOccupationDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOccupationDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.職業区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.occupationDivLabel);
    	//this.get項目値()にて職業区分を取得し返却する。
    	String l_strOccupationDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOccupationDiv;

    }

    /**
     * (get勤務先名称)<BR>
     * 行番号に対応する明細行の勤務先名称を取得する。<BR>
     * <BR>
     * this.get項目値()にて勤務先名称を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先名称ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64000A0
     */
    public String getOffice(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOffice(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先名称ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeLabel);
    	//this.get項目値()にて勤務先名称を取得し返却する。
    	String l_strOffice = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOffice;

    }

    /**
     * (get勤務先郵便番号)<BR>
     * 行番号に対応する明細行の勤務先郵便番号を取得する。<BR>
     * <BR>
     * this.get項目値()にて勤務先郵便番号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先郵便番号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64000AF
     */
    public String getOfficeZipCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeZipCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先郵便番号ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeZipCodeLabel);
    	//this.get項目値()にて勤務先郵便番号を取得し返却する。
    	String l_strOfficeZipCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeZipCode;

    }

    /**
     * (get勤務先住所)<BR>
     * 行番号に対応する明細行の勤務先住所を取得する。<BR>
     * <BR>
     * this.get項目値()にて勤務先住所を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先住所ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64000BF
     */
    public String getOfficeAddress(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeAddress(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先住所ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeAddressLabel);
    	//this.get項目値()にて勤務先住所を取得し返却する。
    	String l_strOfficeAddress = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeAddress;

    }

    /**
     * (get勤務先電話番号１)<BR>
     * 行番号に対応する明細行の勤務先電話番号１を取得する。<BR>
     * <BR>
     * this.get項目値()にて勤務先電話番号１を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先電話番号１ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64101B9
     */
    public String getOfficeTelephone1(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeTelephone1(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先電話番号１ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeTelephone1Label);
    	//this.get項目値()にて勤務先電話番号１を取得し返却する。
    	String l_strOfficeTelephone1 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeTelephone1;

    }

    /**
     * (get勤務先電話番号２)<BR>
     * 行番号に対応する明細行の勤務先電話番号２を取得する。<BR>
     * <BR>
     * this.get項目値()にて勤務先電話番号２を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先電話番号２ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64101C9
     */
    public String getOfficeTelephone2(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeTelephone2(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先電話番号２ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeTelephone2Label);
    	//this.get項目値()にて勤務先電話番号２を取得し返却する。
    	String l_strOfficeTelephone2 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeTelephone2;

    }

    /**
     * (get勤務先電話番号３)<BR>
     * 行番号に対応する明細行の勤務先電話番号３を取得する。<BR>
     * <BR>
     * this.get項目値()にて勤務先電話番号３を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先電話番号３ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64101E8
     */
    public String getOfficeTelephone3(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getOfficeTelephone3(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.勤務先電話番号３ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.officeTelephone3Label);
    	//this.get項目値()にて勤務先電話番号３を取得し返却する。
    	String l_strOfficeTelephone3 = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strOfficeTelephone3;

    }

    /**
     * (get所属部署)<BR>
     * 行番号に対応する明細行の所属部署を取得する。<BR>
     * <BR>
     * this.get項目値()にて所属部署を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.所属部署ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64101EA
     */
    public String getDepartment(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getDepartment(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.所属部署ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.departmentLabel);
    	//this.get項目値()にて所属部署を取得し返却する。
    	String l_strDepartment = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strDepartment;

    }

    /**
     * (get役職)<BR>
     * 行番号に対応する明細行の役職を取得する。<BR>
     * <BR>
     * this.get項目値()にて役職を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.役職ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64101F7
     */
    public String getPost(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getPost(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.役職ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.postLabel);
    	//this.get項目値()にて役職を取得し返却する。
    	String l_strPost = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strPost;

    }

    /**
     * (get世帯主との続柄)<BR>
     * 行番号に対応する明細行の世帯主との続柄を取得する。<BR>
     * <BR>
     * this.get項目値()にて世帯主との続柄を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主との続柄ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410207
     */
    public String getHouseHolderFamilyRelation(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolderFamilyRelation(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主との続柄ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderFamilyRelationLabel);
    	//this.get項目値()にて世帯主との続柄を取得し返却する。
    	String l_strHouseHolderFamilyRelationship = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolderFamilyRelationship;

    }

    /**
     * (get世帯主名)<BR>
     * 行番号に対応する明細行の世帯主名を取得する。<BR>
     * <BR>
     * this.get項目値()にて世帯主名を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主名ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410217
     */
    public String getHouseHolder(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolder(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主名ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderLabel);
    	//this.get項目値()にて世帯主との続柄を取得し返却する。
    	String l_strHouseHolder = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolder;

    }

    /**
     * (get世帯主　@勤務先)<BR>
     * 行番号に対応する明細行の世帯主　@勤務先を取得する。<BR>
     * <BR>
     * this.get項目値()にて世帯主　@勤務先を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主　@勤務先ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410226
     */
    public String getHouseHolderOffice(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolderOffice(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主　@勤務先ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderOfficeLabel);
    	//this.get項目値()にて世帯主　@勤務先を取得し返却する。
    	String l_strHouseHolderOffice = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolderOffice;

    }

    /**
     * (get世帯主　@役職)<BR>
     * 行番号に対応する明細行の世帯主　@役職を取得する。<BR>
     * <BR>
     * this.get項目値()にて世帯主　@役職を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主　@役職ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410236
     */
    public String getHouseHolderPost(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getHouseHolderPost(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.世帯主　@役職ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.houseHolderPostLabel);
    	//this.get項目値()にて世帯主　@役職を取得し返却する。
    	String l_strHouseHolderPost = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strHouseHolderPost;

    }

    /**
     * (get振込先)<BR>
     * 行番号に対応する明細行の振込先を取得する。<BR>
     * <BR>
     * this.get項目値()にて振込先を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.振込先ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410245
     */
    public String getTransfer(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTransfer(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.振込先ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.transferLabel);
    	//this.get項目値()にて振込先を取得し返却する。
    	String l_strTransfer = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTransfer;

    }

    /**
     * (get銀行コード)<BR>
     * 行番号に対応する明細行の銀行コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて銀行コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.銀行コードラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410255
     */
    public String getFinInstitutionCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinInstitutionCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.銀行コードラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finInstitutionCodeLabel);
    	//this.get項目値()にて銀行コードを取得し返却する。
    	String l_strFinInstitutionCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinInstitutionCode;

    }

    /**
     * (get銀行名)<BR>
     * 行番号に対応する明細行の銀行名を取得する。<BR>
     * <BR>
     * this.get項目値()にて銀行名を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.銀行名ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410257
     */
    public String getFinInstitutionName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinInstitutionName(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.銀行名ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finInstitutionNameLabel);
    	//this.get項目値()にて銀行名を取得し返却する。
    	String l_strFinInstitutionName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinInstitutionName;

    }

    /**
     * (get支店コード)<BR>
     * 行番号に対応する明細行の支店コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて支店コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.支店コードラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410265
     */
    public String getFinBranchCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinBranchCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.支店コードラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finBranchCodeLabel);
    	//this.get項目値()にて支店コードを取得し返却する。
    	String l_strFinBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinBranchCode;

    }

    /**
     * (get支店名)<BR>
     * 行番号に対応する明細行の支店名を取得する。<BR>
     * <BR>
     * this.get項目値()にて支店名を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.支店名ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF6410274
     */
    public String getFinBranchName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinBranchName(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.支店名ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finBranchNameLabel);
    	//this.get項目値()にて支店名を取得し返却する。
    	String l_strFinBranchName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinBranchName;

    }

    /**
     * (get預金区分)<BR>
     * 行番号に対応する明細行の預金区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて預金区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.預金区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B015A
     */
    public String getFinSaveDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinSaveDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.預金区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finSaveDivLabel);
    	//this.get項目値()にて預金区分を取得し返却する。
    	String l_strFinSaveDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strFinSaveDiv;

    }

    /**
     * (get口座番号)<BR>
     * 行番号に対応する明細行の口座番号を取得する。<BR>
     * <BR>
     * this.get項目値()にて口座番号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.口座番号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B016A
     */
    public String getFinAccountNo(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getFinAccountNo(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.口座番号ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.finAccountNoLabel);
    	//this.get項目値()にて口座番号を取得し返却する。
    	String l_strtFinAccountNo = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strtFinAccountNo;

    }

    /**
     * (get通帳記号)<BR>
     * 行番号に対応する明細行の通帳記号を取得する。<BR>
     * <BR>
     * this.get項目値()にて通帳記号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.通帳記号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B017A
     */
    public String getPostalSaveCode(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getPostalSaveCode(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.通帳記号ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.postalSaveCodeLabel);
    	//this.get項目値()にて通帳記号を取得し返却する。
    	String l_strPostalSaveCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strPostalSaveCode;

    }

    /**
     * (get通帳番号)<BR>
     * 行番号に対応する明細行の通帳番号を取得する。<BR>
     * <BR>
     * this.get項目値()にて通帳番号を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.通帳番号ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B0189
     */
    public String getPostalSaveNo(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getPostalSaveNo(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.通帳番号ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.postalSaveNoLabel);
    	//this.get項目値()にて通帳番号を取得し返却する。
    	String l_strPostalSaveNo = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strPostalSaveNo;

    }

    /**
     * (get投資経験（株式現物取引）)<BR>
     * 行番号に対応する明細行の投資経験（株式現物取引）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（株式現物取引）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株式現物取引）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B0199
     */
    public String getExperienceEquityDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceEquityDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株式現物取引）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceEquityDivLabel);
    	//this.get項目値()にて投資経験（株式現物取引）を取得し返却する。
    	String l_strExperienceEquityDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceEquityDiv;

    }

    /**
     * (get投資経験（株式信用取引）)<BR>
     * 行番号に対応する明細行の投資経験（株式信用取引）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（株式信用取引）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株式信用取引）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01A8
     */
    public String getExperienceMarginDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceMarginDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株式信用取引）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceMarginDivLabel);
    	//this.get項目値()にて投資経験（株式信用取引）を取得し返却する。
    	String l_strExperienceMarginDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceMarginDiv;

    }

    /**
     * (get投資経験（債券）)<BR>
     * 行番号に対応する明細行の投資経験（債券）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（債券）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（債券）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01B8
     */
    public String getExperienceBondDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceBondDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（債券）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceBondDivLabel);
    	//this.get項目値()にて投資経験（債券）を取得し返却する。
    	String l_strExperienceBondDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceBondDiv;

    }

    /**
     * (get投資経験（株価指数オプション）)<BR>
     * 行番号に対応する明細行の投資経験（株価指数オプション）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（株価指数オプション）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株価指数オプション）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01C8
     */
    public String getExperienceOptionsDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceOptionsDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株価指数オプション）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceOptionsDivLabel);
    	//this.get項目値()にて投資経験（株価指数オプション）を取得し返却する。
    	String l_strExperienceOptionsDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceOptionsDiv;

    }

    /**
     * (get投資経験（投資信託：株式）)<BR>
     * 行番号に対応する明細行の投資経験（投資信託：株式）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（投資信託：株式）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（投資信託：株式）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01CA
     */
    public String getExperienceFundSkDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFundSkDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（投資信託：株式）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFundSkDivLabel);
    	//this.get項目値()にて投資経験（投資信託：株式）を取得し返却する。
    	String l_strExperienceFundSkDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFundSkDiv;

    }

    /**
     * (get投資経験（投資信託：公社債）)<BR>
     * 行番号に対応する明細行の投資経験（投資信託：公社債）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（投資信託：公社債）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（投資信託：公社債）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01D7
     */
    public String getExperienceFundBdDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFundBdDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（投資信託：公社債）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFundBdDivLabel);
    	//this.get項目値()にて投資経験（投資信託：公社債）を取得し返却する。
    	String l_strExperienceFundBdDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFundBdDiv;

    }

    /**
     * (get投資経験（株価指数先物）)<BR>
     * 行番号に対応する明細行の投資経験（株価指数先物）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（株価指数先物）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株価指数先物）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01E7
     */
    public String getExperienceFuturesDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFuturesDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（株価指数先物）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFuturesDivLabel);
    	//this.get項目値()にて投資経験（株価指数先物）を取得し返却する。
    	String l_strExperienceFuturesDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFuturesDiv;

    }

    /**
     * (get投資経験（外国証券）)<BR>
     * 行番号に対応する明細行の投資経験（外国証券）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（外国証券）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（外国証券）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B01F7
     */
    public String getExperienceFEquityDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceFEquityDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（外国証券）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceFEquityDivLabel);
    	//this.get項目値()にて投資経験（外国証券）を取得し返却する。
    	String l_strExperienceFEquityDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceFEquityDiv;

    }

    /**
     * (get投資経験（その他）)<BR>
     * 行番号に対応する明細行の投資経験（その他）を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資経験（その他）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（その他）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B0206
     */
    public String getExperienceEtcDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getExperienceEtcDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資経験（その他）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.experienceEtcDivLabel);
    	//this.get項目値()にて投資経験（その他）を取得し返却する。
    	String l_strExperienceEtcDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strExperienceEtcDiv;

    }

    /**
     * (get希望される取引の種類　@株式（現物）)<BR>
     * 行番号に対応する明細行の希望される取引の種類　@株式（現物）を取得する。<BR>
     * <BR>
     * this.get項目値()にて希望される取引の種類　@株式（現物）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株式（現物）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64B0216
     */
    public String getInterestEquityFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestEquityFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株式（現物）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestEquityFlagLabel);
    	//this.get項目値()にて希望される取引の種類　@株式（現物）を取得し返却する。
    	String l_strInterestEquityFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestEquityFlag;

    }

    /**
     * (get希望される取引の種類　@株式（信用）)<BR>
     * 行番号に対応する明細行の希望される取引の種類　@株式（信用）を取得する。<BR>
     * <BR>
     * this.get項目値()にて希望される取引の種類　@株式（信用）を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株式（信用）ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C01E7
     */
    public String getInterestMarginFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestMarginFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株式（信用）ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestMarginFlagLabel);
    	//this.get項目値()にて希望される取引の種類　@株式（信用）を取得し返却する。
    	String l_strInterestMarginFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestMarginFlag;

    }

    /**
     * (get希望される取引の種類　@国債先物)<BR>
     * 行番号に対応する明細行の希望される取引の種類　@国債先物を取得する。<BR>
     * <BR>
     * this.get項目値()にて希望される取引の種類　@国債先物を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類国債先物ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C01F6
     */
    public String getInterestBondFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestBondFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類国債先物ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestBondFlagLabel);
    	//this.get項目値()にて希望される取引の種類　@国債先物を取得し返却する。
    	String l_strInterestBondFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestBondFlag;

    }

    /**
     * (get希望される取引の種類　@投資信託)<BR>
     * 行番号に対応する明細行の希望される取引の種類　@投資信託を取得する。<BR>
     * <BR>
     * this.get項目値()にて希望される取引の種類　@投資信託を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類投資信託ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0206
     */
    public String getInterestFundFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestFundFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類投資信託ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestFundFlagLabel);
    	//this.get項目値()にて希望される取引の種類　@投資信託を取得し返却する。
    	String l_strInterestFundFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestFundFlag;

    }

    /**
     * (get希望される取引の種類　@株価指数先物)<BR>
     * 行番号に対応する明細行の希望される取引の種類　@株価指数先物を取得する。<BR>
     * <BR>
     * this.get項目値()にて希望される取引の種類　@株価指数先物を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株価指数先物ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0216
     */
    public String getInterestFuturesFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestFuturesFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株価指数先物ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestFuturesFlagLabel);
    	//this.get項目値()にて希望される取引の種類　@株価指数先物を取得し返却する。
    	String l_strInterestFuturesFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestFuturesFlag;

    }

    /**
     * (get希望される取引の種類　@株価指数オプション)<BR>
     * 行番号に対応する明細行の希望される取引の種類　@株価指数オプションを取得する。<BR>
     * <BR>
     * this.get項目値()にて希望される取引の種類　@株価指数オプションを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株価指数オプションラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0218
     */
    public String getInterestOptionsFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInterestOptionsFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.希望される取引の種類株価指数オプションラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.interestOptionsFlagLabel);
    	//this.get項目値()にて希望される取引の種類　@株価指数オプションを取得し返却する。
    	String l_strInterestOptionsFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInterestOptionsFlag;

    }

    /**
     * (get年収区分)<BR>
     * 行番号に対応する明細行の年収区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて年収区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.年収区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0225
     */
    public String getAnnualIncomeDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAnnualIncomeDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.年収区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.annualIncomeDivLabel);
    	//this.get項目値()にて年収区分を取得し返却する。
    	String l_strAnnualIncomeDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAnnualIncomeDiv;

    }

    /**
     * (get金融資産区分)<BR>
     * 行番号に対応する明細行の金融資産区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて金融資産区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.金融資産区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0235
     */
    public String getAssetValueDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAssetValueDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.金融資産区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.assetValueDivLabel);
    	//this.get項目値()にて金融資産区分を取得し返却する。
    	String l_strAssetValueDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAssetValueDiv;

    }

    /**
     * (get特定口座区分現物)<BR>
     * 行番号に対応する明細行の特定口座区分現物を取得する。<BR>
     * <BR>
     * this.get項目値()にて特定口座区分現物を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.特定口座区分現物ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0245
     */
    public String getSpecialAccEquity(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getSpecialAccEquity(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.特定口座区分現物ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.specialAccEquityLabel);
    	//this.get項目値()にて特定口座区分現物を取得し返却する。
    	String l_strSpecialAcc = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strSpecialAcc;

    }

    /**
     * (get内部者登録区分)<BR>
     * 行番号に対応する明細行の内部者登録区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて内部者登録区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.内部者登録区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0254
     */
    public String getInsiderFlag(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInsiderFlag(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.内部者登録区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.insiderFlagLabel);
    	//this.get項目値()にて内部者登録区分を取得し返却する。
    	String l_strInsiderFlag = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInsiderFlag;

    }

    /**
     * (get内部者登録銘柄)<BR>
     * 行番号に対応する明細行の内部者登録銘柄を取得する。<BR>
     * <BR>
     * this.get項目値()にて内部者登録銘柄を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.内部者登録銘柄ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0264
     */
    public String getProductName(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getProductName(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.内部者登録銘柄ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.productNameLabel);
    	//this.get項目値()にて内部者登録銘柄を取得し返却する。
    	String l_strProductName = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strProductName;

    }

    /**
     * (get業種区分)<BR>
     * 行番号に対応する明細行の業種区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて業種区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.業種区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0273
     */
    public String getTypeDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getTypeDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.業種区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.typeDivLabel);
    	//this.get項目値()にて業種区分を取得し返却する。
    	String l_strTypeDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strTypeDiv;

    }

    /**
     * (get収入の形態区分)<BR>
     * 行番号に対応する明細行の収入の形態区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて収入の形態区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.収入の形態区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0283
     */
    public String getIncomeDormDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getIncomeDormDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.収入の形態区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.incomeDormDivLable);
    	//this.get項目値()にて収入の形態区分を取得し返却する。
    	String l_strIncomeDormDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strIncomeDormDiv;

    }

    /**
     * (get内部者関係区分)<BR>
     * 行番号に対応する明細行の内部者関係区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて内部者関係区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.内部者関係区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C0293
     */
    public String getInsiderRelationDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInsiderRelationDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.内部者関係区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.insiderRelationDivLabel);
    	//this.get項目値()にて内部者関係区分を取得し返却する。
    	String l_strInsiderRelationDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInsiderRelationDiv;

    }

    /**
     * (get投資目的区分)<BR>
     * 行番号に対応する明細行の投資目的区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて投資目的区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.投資目的区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64C02A2
     */
    public String getInvestPurposeDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getInvestPurposeDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.投資目的区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.investPurposeDivLabel);
    	//this.get項目値()にて投資目的区分を取得し返却する。
    	String l_strInvestPurposeDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strInvestPurposeDiv;

    }

    /**
     * (get取引動機@区分)<BR>
     * 行番号に対応する明細行の取引動機@区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて取引動機@区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.取引動機@区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64D015A
     */
    public String getAppliMotivatDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getAppliMotivatDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.取引動機@区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.appliMotivatDivLabel);
    	//this.get項目値()にて取引動機@区分を取得し返却する。
    	String l_strAppliMotivatDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strAppliMotivatDiv;

    }

    /**
     * (get本人確認書類区分)<BR>
     * 行番号に対応する明細行の本人確認書類区分を取得する。<BR>
     * <BR>
     * this.get項目値()にて本人確認書類区分を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(口座開設申込ULCSV.本人確認書類区分ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 472FF64D016A
     */
    public String getIDConfirmDocDiv(int l_intLineNo)
    {
    	final String STR_METHOD_NAME = "getIDConfirmDocDiv(int)";
    	log.entering(STR_METHOD_NAME);

    	//カラム：　@getカラムモデル(口座開設申込ULCSV.本人確認書類区分ラベル)の戻り値。
    	WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
    		this.getColumnModel(this.idConfirmDocDivLabel);
    	//this.get項目値()にて本人確認書類区分を取得し返却する。
    	String l_strIDConfirmDocDiv = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

    	log.exiting(STR_METHOD_NAME);
    	return l_strIDConfirmDocDiv;

    }
}
@
