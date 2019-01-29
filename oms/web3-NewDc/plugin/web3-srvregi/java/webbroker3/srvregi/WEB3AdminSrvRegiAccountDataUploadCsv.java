head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客データアップロードCSV(WEB3AdminSrvRegiAccountDataUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
Revesion History : 2005/04/05 内田 亨(SRA) デバッグログ出力修正
Revesion History : 2005/04/06 内田 亨(SRA) validate入力必須項目()にアップロード区分のnullチェックを追加
Revesion History : 2005/04/06 内田 亨(SRA) validate入力必須項目()に申込抽選区分のチェックを追加
Revesion History : 2005/04/07 内田 亨(SRA) validate入力必須項目()に登録区分のチェックを追加
Revesion History : 2005/04/07 内田 亨(SRA) validate入力必須項目()に申込登録IDのチェックを追加
Revesion History : 2005/04/07 内田 亨(SRA) validate入力必須項目()の利用料金チェックのバグ修正
Revesion History : 2007/06/05 金傑(中訊) 仕様変更モデルNo.238
Revesion History : 2007/06/08 金傑(中訊) 仕様変更モデルNo.258 No.260
Revesion History : 2007/06/21 崔遠鵬(中訊) 仕様変更モデルNo.266 No.268
Revesion History : 2007/07/11 孟亜南(中訊) 仕様変更モデルNo.279
Revesion History : 2008/06/02 武波 (中訊) 仕様変更 モデルNo.382,No.388
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesPK;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (顧客データアップロードCSV)<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountDataUploadCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadCsv.class);

    /**
     * (アップロード区分ラベル)<BR>
     * 定数定義プロパティ　@”アップロード区分” <BR>
     */
    public static  String UPLOAD_DIV_LABEL = "アップロード区分";

    /**
     * (申込登録IDラベル)<BR>
     * 定数定義プロパティ　@”申込登録ID” <BR>
     */
    public static  String REGIST_ID_LABEL = "申込登録ID";

    /**
     * (証券会社コードラベル)<BR>
     * 定数定義プロパティ　@”証券会社コード” <BR>
     */
    public static  String INSTITUTION_CODE_LABEL = "証券会社コード";

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード” <BR>
     */
    public static  String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (サービス区分ラベル)<BR>
     * 定数定義プロパティ　@”サービス区分” <BR>
     */
    public static  String SRV_DIV_LABEL = "サービス区分";

    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード” <BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (顧客名ラベル)<BR>
     * 定数定義プロパティ　@”顧客名” <BR>
     */
    public static  String ACCOUNT_NAME_LABEL = "顧客名";

    /**
     * (登録区分ラベル)<BR>
     * 定数定義プロパティ　@"登録区分"<BR>
     */
    public static  String PAYMENT_DIV_LABEL = "登録区分";

    /**
     * (申込抽選区分ラベル)<BR>
     * 定数定義プロパティ　@"申込抽選区分"<BR>
     */
    public static  String APPLI_LOT_DIV_LABEL = "申込抽選区分";

    /**
     * (申込日ラベル)<BR>
     * 定数定義プロパティ　@"申込日"<BR>
     */
    public static  String APPLI_DATE_LABEL = "申込日";

    /**
     * (適用開始日ラベル)<BR>
     * 定数定義プロパティ　@"適用開始日"<BR>
     */
    public static  String APPLI_START_DATE_LABEL = "適用開始日";

    /**
     * (適用終了日ラベル)<BR>
     * 定数定義プロパティ　@"適用終了日"<BR>
     */
    public static  String APPLI_END_DATE_LABEL = "適用終了日";

    /**
     * (利用料金ラベル)<BR>
     * 定数定義プロパティ　@"利用料金"<BR>
     */
    public static  String USE_AMT_LABEL = "利用料金";

    /**
     * (出金余力ラベル)<BR>
     * 定数定義プロパティ　@"出金余力"<BR>
     */
    public static  String PAYMENT_POWER_LABEL = "出金余力";

    /**
     * (出金日ラベル)<BR>
     * 定数定義プロパティ　@"出金日"<BR>
     */
    public static  String PAYMENT_DATE_LABEL = "出金日";

    /**
     * (アップロード区分_新規登録)<BR>
     * 定数定義プロパティ　@アップロード区分-新規登録<BR>
     */
    public static  String UPLOAD_DIV_NEW_REGIST_LABEL = "0";

    /**
     * (アップロード区分_変更登録)<BR>
     * 定数定義プロパティ　@アップロード区分-変更登録<BR>
     */
    public static  String UPLOAD_DIV_CHANGE_REGIST_LABEL = "1";

    /**
     * (アップロード区分_抽選結果アップロード)<BR>
     * 定数定義プロパティ　@アップロード区分-抽選結果アップロード<BR>
     */
    public static  String UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL = "2";

    /**
     * (申込属性)<BR>
     * 定数定義プロパティ　@アップロード区分-申込属性<BR>
     */
    public static String APPLI_ATTRIBUTE_LABEL = "3";

    /**
     * (登録区分_有料)<BR>
     * 定数定義プロパティ　@登録区分－有料<BR>
     */
    public static  String PAYMENT_DIV_CHARGE_LABEL = "0";

    /**
     * (登録区分_無料)<BR>
     * 定数定義プロパティ　@登録区分－無料<BR>
     */
    public static  String PAYMENT_DIV_FREE_LABEL = "1";

    /**
     * (申込抽選区分_試用申込)<BR>
     * 定数定義プロパティ　@申込抽選区分－試用申込<BR>
     */
    public static  String APPLI_LOT_DIV_TRIAL_LABEL = "0";

    /**
     * (申込抽選区分_申込)<BR>
     * 定数定義プロパティ　@申込抽選区分－申込<BR>
     */
    public static  String APPLI_LOT_DIV_APPLI_LABEL = "1";

    /**
     * (申込抽選区分_当選)<BR>
     * 定数定義プロパティ　@申込抽選区分－当選（抽選なしの場合は、本申込の意）<BR>
     */
    public static  String APPLI_LOT_DIV_ELECTION_LABEL = "2";

    /**
     * (申込抽選区分_落選)<BR>
     * 定数定義プロパティ　@申込抽選区分－落選<BR>
     */
    public static  String APPLI_LOT_DIV_DEFEAT_LABEL = "3";

    /**
     * (申込抽選区分_取消)<BR>
     * 定数定義プロパティ　@申込抽選区分－取消<BR>
     */
    public static  String APPLI_LOT_DIV_CANCEL_LABEL = "4";
    
    /**
     * (申込抽選区分_無料)<BR>
     * 定数定義プロパティ　@申込抽選区分－無料<BR>
     */
    public static String APPLI_LOT_DIV_FREE_LABEL = "1";
    
    /**
     * (申込抽選区分_利用不可)<BR>
     * 定数定義プロパティ　@申込抽選区分－利用不可<BR>
     */
    public static String APPLI_LOT_DIV_USE_NOT_LABEL = "2";

    /**
     * (申込抽選区分_属性削除)<BR>
     * 定数定義プロパティ　@申込抽選区分－属性削除<BR>
     */
    public static String APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL = "3";

    /**
     * (抽選設定_抽選無)<BR>
     * 抽選設定_抽選無を表現する。<BR>
     */
    public static  String LOT_DIV_NO_LOT_LABEL = "0";

    /**
     * (アップロードファ@イルID)<BR>
     */
    public String strUploadFileId = "顧客データアップロード";

    /**
     * (顧客データアップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@roseuid 410F76370208
     */
    public WEB3AdminSrvRegiAccountDataUploadCsv()
    {
        this.createColumnHeader();
    }

    /**
     * (顧客データアップロードCSV)<BR>
     * コンストラクタ <BR>
     * －引数のアップロードIDをプロパティにセットする。<BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@param l_lngUploadId - (アップロードID)<BR>
     * アップロードID <BR>
     * <BR>
     * ※　@（管理者共通）アップロードテーブルのPK<BR>
     * @@roseuid 410F75C10342
     */
    public WEB3AdminSrvRegiAccountDataUploadCsv(long l_lngUploadId)
    {
        this.administratorUploadId = l_lngUploadId;
        this.createColumnHeader();//WEB3-SRVREGI-A-UT-0086
    }

    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * 顧客データアップロードCSV.アップロードファ@イルＩＤを返却する。<BR>
     * @@return String
     * @@roseuid 410F1C1003B0
     */
    public String getUploadFileId()
    {
        return this.strUploadFileId;
    }

    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを返却する。<BR>
     * <BR>
     * ProductTypeEnum.その他を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 410F1C7102A6
     */
    public ProductTypeEnum getProductType()
    {
        return ProductTypeEnum.OTHER;
    }

    /**
     * (getアップロード区分)<BR>
     * 行番号に対応する明細行のアップロード区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にてアップロード区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.アップロード区分ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F20E30371
     */
    public String getUploadDiv(int l_intLineNumber)
    {
        String l_strUploadDiv = this.getValue(l_intLineNumber, this.getColumnModel(UPLOAD_DIV_LABEL)).toString();
        return l_strUploadDiv;
    }

    /**
     * (get申込登録ＩＤ)<BR>
     * 顧客データアップロードCSV.申込登録ＩＤを返却する。<BR>
     * 行番号に対応する明細行の申込登録ＩＤを取得する。 <BR>
     * <BR>
     * this.get項目値()にて申込登録ＩＤを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.申込登録ＩＤラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 41201F3B004A
     */
    public String getRegistId(int l_intLineNumber)
    {
		String l_strRegistId = (String) this.getValue(l_intLineNumber, this.getColumnModel(REGIST_ID_LABEL));
        return l_strRegistId;
    }

    /**
     * (get証券会社コード)<BR>
     * 行番号に対応する明細行の証券会社コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて証券会社コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.証券会社コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F1E5A03B0
     */
    public String getInstitutionCode(int l_intLineNumber)
    {
        String l_strInstitutionCode = (String) this.getValue(l_intLineNumber, this.getColumnModel(INSTITUTION_CODE_LABEL));
        return l_strInstitutionCode;
    }

    /**
     * (get部店コード)<BR>
     * 行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.部店コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F1E5A03DE
     */
    public String getBranchCode(int l_intLineNumber)
    {
        String l_strBranchCode = (String) this.getValue(l_intLineNumber, this.getColumnModel(BRANCH_CODE_LABEL));
        return l_strBranchCode;
    }

    /**
     * (getサービス区分)<BR>
     * 行番号に対応する明細行のサービス区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にてサービス区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.サービス区分ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F1E5B0025
     */
    public String getSrvDiv(int l_intLineNumber)
    {
        String l_strSrvDiv = (String) this.getValue(l_intLineNumber, this.getColumnModel(SRV_DIV_LABEL));
        return l_strSrvDiv;
    }

    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.顧客コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F1E5B0054
     */
    public String getAccountCode(int l_intLineNumber)
    {
        String l_strAccountCode = (String) this.getValue(l_intLineNumber, this.getColumnModel(ACCOUNT_CODE_LABEL));
        return l_strAccountCode;
    }

    /**
     * (get登録区分)<BR>
     * 行番号に対応する明細行の登録区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にて登録区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.登録区分ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F1E5B0083
     */
    public String getPaymentDiv(int l_intLineNumber)
    {
        // [WEB3-SRVREGI-A-FT-0163の再質問]というQAにより修正
        String l_strPaymentDiv = null;
        Object l_paymentDivValue =this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DIV_LABEL));
        if (l_paymentDivValue != null)
        {
            l_strPaymentDiv = l_paymentDivValue.toString();
        }
        return l_strPaymentDiv;
    }

    /**
     * (get申込抽選区分)<BR>
     * 行番号に対応する明細行の申込抽選区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にて申込抽選区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.申込抽選区分ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 410F1E5B00B2
     */
    public String getAppliLotDiv(int l_intLineNumber)
    {
        String l_strAppliLotDiv = this.getValue(l_intLineNumber, this.getColumnModel(APPLI_LOT_DIV_LABEL)).toString();
        return l_strAppliLotDiv;
    }

    /**
     * (get申込日)<BR>
     * 行番号に対応する明細行の申込日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて申込日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.申込日ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return Date
     * @@roseuid 410F1E5B00F0
     */
    public Date getAppliDate(int l_intLineNumber)
    {
        Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(APPLI_DATE_LABEL));
        return l_dat;
    }

    /**
     * (get適用開始日)<BR>
     * 行番号に対応する明細行の適用開始日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて適用開始日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.適用開始日ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return Date
     * @@roseuid 410F1E5B011F
     */
    public Date getAppliStartDate(int l_intLineNumber)
    {
		Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(APPLI_START_DATE_LABEL));
        return l_dat;
    }

    /**
     * (get適用終了日)<BR>
     * 行番号に対応する明細行の適用終了日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて適用終了日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.適用終了日ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return Date
     * @@roseuid 410F1E5B014E
     */
    public Date getAppliEndDate(int l_intLineNumber)
    {
        Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(APPLI_END_DATE_LABEL));
        return l_dat;
    }

    /**
     * (get利用料金)<BR>
     * 行番号に対応する明細行の利用料金を取得する。 <BR>
     * <BR>
     * this.get項目値()にて利用料金を取得し、標準データ型(double)に変換して返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.利用料金ラベル)の戻り値。<BR>
     * <BR>
     * ※）取得した利用料金==nullの場合、nullを返却する。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return double
     * @@roseuid 410F1E5B017D
     */
    public Double getUseAmt(int l_intLineNumber)
    {
		Double l_dblUseAmt = null;

		if ((this.getColumnModel(USE_AMT_LABEL)  != null) &&
			(this.getValue(l_intLineNumber, this.getColumnModel(USE_AMT_LABEL)) != null))
		{
			l_dblUseAmt = new Double(
				this.getValue(l_intLineNumber, this.getColumnModel(USE_AMT_LABEL)).toString());
		}
		return l_dblUseAmt;
    }

    /**
     * (get出金日)<BR>
     * 行番号に対応する明細行の出金日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて出金日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(顧客データアップロードCSV.出金日ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return Date
     * @@roseuid 410F1E5B01CB
     */
    public Date getPaymentDate(int l_intLineNumber)
    {
        Date l_dat = (Date)this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DATE_LABEL));
        return l_dat;
    }

    /**
     * (validateアップロード区分)<BR>
     * アップロード区分の値が正しいかチェックを行う。<BR>
     * <BR>
     * [Error条件]　@（アップロード区分エラー）<BR>
     * <BR>
     * （アップロード区分 ≠ 顧客アップロードCSV.アップロード区分_新規登録） and<BR>
     * （アップロード区分 ≠ 顧客アップロードCSV.アップロード区分_変更登録） and<BR>
     * （アップロード区分 ≠ 顧客アップロードCSV.アップロード区分_抽選結果アップロード） and<BR>
     * （アップロード区分 ≠ 顧客アップロードCSV.アップロード区分_申込属性）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01020<BR>
     * @@param l_strUploadDiv - (アップロード区分)<BR>
     * アップロード区分<BR>
     * <BR>
     * 0：新規登録<BR>
     * 1：変更登録<BR>
     * 2：抽選結果アップロード<BR>
     * 3：申込属性<BR>
     * @@roseuid 410F256E00F0
     */
    public void validateUploadDiv(String l_strUploadDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUploadDiv(String )";
        log.entering(STR_METHOD_NAME);

        if (l_strUploadDiv == null
            || !(WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv)))
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01020,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("アップロード区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateサービス区分)<BR>
     * 指定されたサービス区分が、サービス登録されているか、申込可能かチェックする。<BR>
     * <BR>
     * １） サービスオブジェクトを取得する。<BR>
     * <BR>
     * 　@(サービス利用)商品エンティティ.サービス情報管理.getサービスマスター()にて
     * 　@サービスオブジェクトを取得する。<BR>
     * <BR>
     * 　@[サービス情報管理.getサービスマスター()に指定する引数]<BR>
     * 　@証券会社コード：引数の証券会社コード<BR>
     * 　@サービス区分：引数のサービス区分<BR>
     * 　@is行ロック：false<BR>
     * <BR>
     * <BR>
     * ２） サービスが申込可能かチェックする。<BR>
     * <BR>
     * 　@(サービス利用)商品エンティティ.サービス.is申込可能()にて<BR>
     * 　@サービス申込可能かチェックし、申込可能でない場合は例外をスローする。<BR>
     * 　@（サービス区分エラー）　@<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分を指定する。<BR>
     * @@roseuid 41171E890019
     */
    public void validateSrvDiv(String l_strInstitutionCode, String l_strSrvDiv)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSrvDiv(String, String )";
        log.entering(STR_METHOD_NAME );

        //１） サービスオブジェクトを取得する
        WEB3SrvRegiServiceInfoManagement l_management = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_master = l_management.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //２） サービスが申込可能かチェックする。
        if (!l_master.isAppliPossible())
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate登録区分)<BR>
     * 登録区分の値が正しいかチェックを行う。<BR>
     * <BR>
     * [Error条件]（登録区分エラー）<BR>
     * <BR>
     * （登録区分 != 顧客アップロードCSV.登録区分_有料） and<BR>
     * （登録区分 != 顧客アップロードCSV.登録区分_無料） <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00841<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 登録区分<BR>
     * <BR>
     * 0：有料<BR>
     * 1：無料<BR>
     * @@roseuid 410F25840390
     */
    public void validatePaymentDiv(String l_strPaymentDiv)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePaymentDiv(String, String )";
        log.entering(STR_METHOD_NAME );

        //[Error条件]（登録区分エラー）
        // （登録区分 != 顧客アップロードCSV.登録区分_有料） and
        // （登録区分 != 顧客アップロードCSV.登録区分_無料）
        if (l_strPaymentDiv == null
            || !(WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_FREE_LABEL.equals(l_strPaymentDiv)))
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("登録区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate申込抽選区分)<BR>
     * 明細行の申込抽選区分の設定値が妥当かチェックする。<BR>
     * <BR>
     * 1） 引数.抽選設定が抽選設定_抽選無の場合<BR>
     * <BR>
     * 　@①@ （引数.アップロード区分==アップロード区分_抽選結果アップロード）の場合<BR>
     * 　@　@　@例外をスローする。（申込抽選区分エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * 　@② （引数.アップロード区分≠アップロード区分_抽選結果アップロード）尚且つ<BR>
     * 　@　@　@引数.アップロード区分≠アップロード区分_申込属性）なら<BR>
     * 　@　@　@（引数.申込抽選区分＝申込抽選区分_当選）以外は、<BR>
     * 　@　@　@コード値不正として例外をスローする。（申込抽選区分エラー）<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * 　@③ （引数.アップロード区分 == アップロード区分_申込属性）の場合<BR>
     *      以下の条件に当てはまらない場合、コード値不正として例外をスローする。 
     *      （申込抽選区分エラー）<BR>
     *          （引数.申込抽選区分 == 申込抽選区分_無料）　@or <BR>
     *          （引数.申込抽選区分 == 申込抽選区分_利用不可）or <BR>
     *          （引数.申込抽選区分 == 申込抽選区分_属性削除）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>  
     * 2） 引数.抽選設定が抽選設定_抽選無以外の場合<BR>
     * <BR>
     * 　@①@ （引数.アップロード区分==アップロード区分_新規登録） の場合<BR>
     * <BR>
     * 　@　@　@下の条件に当てはまる場合、コード値不正として例外をスローする。<BR>
     * 　@　@　@　@（申込抽選区分エラー）<BR>
     * <BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_申込）　@and<BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_当選）　@and<BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_落選）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * 　@② （引数.アップロード区分==アップロード区分_変更登録）の場合<BR>
     * <BR>
     * 　@　@　@下の条件に当てはまる場合、コード値不正として例外をスローする。（申込抽選区分エラー）<BR>
     * <BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_申込）　@and<BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_当選）　@and<BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_落選）　@and<BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_取消）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * <BR>
     * 　@③ （引数.アップロード区分==アップロード区分_抽選結果アップロード）<BR>
     * <BR>
     * 　@　@　@下の条件に当てはまる場合、コード値不正として例外をスローする。<BR> 　@　@　@　@　@　@　@　@
     *       （申込抽選区分エラー）<BR>
     * 　@　@ <BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_当選）　@and<BR>
     * 　@　@　@　@　@（引数.申込抽選区分≠申込抽選区分_落選）　@　@　@　@<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     * @@param l_strUploadDiv - (アップロード区分)<BR>
     * アップロード区分<BR>
     * <BR>
     * 0：新規登録<BR>
     * 1：変更登録<BR>
     * 2：抽選結果アップロード<BR>
     * @@param l_strLotDiv - (抽選設定)<BR>
     * 抽選設定（get抽選設定()の戻り値）を指定する。<BR>
     *
     *
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 申込抽選区分<BR>
     *
     * 1：申込<BR>
     * 2：当選（抽選無しの場合は本申込）<BR>
     * 3：落選<BR>
     * 4：取消<BR>
     * @@roseuid 410F259C0323
     */
    public void validateAppliLotDiv(String l_strUploadDiv, String l_strLotDiv, String l_strAppliLotDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliLotDiv(String, String, String )";
        log.entering(STR_METHOD_NAME );

        //1） 引数.抽選設定が抽選設定_抽選無の場合
        if (WEB3AdminSrvRegiAccountDataUploadCsv.LOT_DIV_NO_LOT_LABEL.equals(l_strLotDiv))
        {
			//（引数.アップロード区分==アップロード区分_抽選結果アップロード）の場合
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("申込抽選区分エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
			//（引数.アップロード区分≠アップロード区分_抽選結果アップロード）尚且つ 
            //引数.アップロード区分≠アップロード区分_申込属性）なら 
            else if(!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
            {
			    //（引数.申込抽選区分＝申込抽選区分_当選）以外は、
			    if (!WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv))
			    {
				    WEB3BaseException l_e =
					    new WEB3BusinessLayerException(
						    WEB3ErrorCatalog.BUSINESS_ERROR_01022,
						    this.getClass().getName() + STR_METHOD_NAME);
				    log.debug("申込抽選区分エラー.", l_e);
				    log.exiting(STR_METHOD_NAME);
				    throw l_e;
			  }            	
            }
            // （引数.アップロード区分 == アップロード区分_申込属性）の場合
            if (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_ATTRIBUTE_LABEL.equals(l_strUploadDiv))
            {
                // 以下の条件に当てはまらない場合、コード値不正として例外をスローする
                // （引数.申込抽選区分 == 申込抽選区分_無料）　@or
                // （引数.申込抽選区分 == 申込抽選区分_利用不可）or
                // （引数.申込抽選区分 == 申込抽選区分_属性削除）
                if (!(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_FREE_LABEL.equals(l_strAppliLotDiv)
                    || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_USE_NOT_LABEL.equals(l_strAppliLotDiv)
                    || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ATTRIBUTE_DELETE_LABEL.equals(
                       l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("申込抽選区分エラー.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }
        else
        {
            //2） 引数.抽選設定が抽選設定_抽選無以外の場合
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
            {
                //①@ （引数.アップロード区分==アップロード区分_新規登録） の場合
                if (l_strAppliLotDiv == null
                    || !(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("申込抽選区分エラー.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
            else if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv))
            {
                //② （引数.アップロード区分==アップロード区分_変更登録）の場合
                if (l_strAppliLotDiv == null
                    || !(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_CANCEL_LABEL.equals(l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("申込抽選区分エラー.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

            }
            else if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
            {
                //③ （引数.アップロード区分==アップロード区分_抽選結果アップロード）
                if (l_strAppliLotDiv == null
                    || !(WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)))
                {
                    WEB3BaseException l_e =
                        new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                            this.getClass().getName() + STR_METHOD_NAME);
                    log.debug("申込抽選区分エラー.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate顧客)<BR>
     * 顧客コードに対応する顧客が登録されているかチェックする。<BR>
     * <BR>
     * １）証券会社コードの桁数が2桁の数字でない場合は例外をスローする。（証券会社コードエラー）<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01023<BR>
     * <BR>
     * ２）部店コードの桁数が3桁の数字でない場合は例外をスローする。（部店コードエラー）<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * ３）顧客コードの桁数が6桁の数字でない場合は例外をスローする。（顧客コードエラー）<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * ４）顧客オブジェクトを取得する<BR>
     * 　@　@③ 顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@アカウントマネージャ.getMainAccount(証券会社コード, 引数.部店コード, 引数.顧客コード)<BR>
     * <BR>
     * 　@　@④ 顧客オブジェクトを取得できない場合は例外をスローする。（未登録顧客エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01026<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コードを指定する。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コードを指定する。<BR>
     * @@roseuid 41171F5901EE
     */
    public void validateAccount(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAccount(String, String, String )";
        log.entering(STR_METHOD_NAME );

        //１）証券会社コードの桁数が2桁でない場合は例外をスローする。（証券会社コードエラー）
        if (l_strInstitutionCode == null
            || l_strInstitutionCode.length() != 2)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01023,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("証券会社コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //２）部店コードの桁数が3桁でない場合は例外をスローする。（部店コードエラー）
        if (l_strBranchCode == null
            || l_strBranchCode.length() != 3)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("部店コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //３）顧客コードの桁数が6桁でない場合は例外をスローする。（顧客コードエラー）
        if (l_strAccountCode == null
            || l_strAccountCode.length() != 6)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("顧客コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //４）顧客オブジェクトを取得する
        //WEB3-SRVREGI-A-UT-0090
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //拡張アカウントマネージャ取得する
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //③ 顧客オブジェクトを取得する。
            l_accountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        }
        catch (WEB3SystemLayerException l_e)
        {
            //④ 顧客オブジェクトを取得できない場合は例外をスローする。（未登録顧客エラー）
            WEB3BaseException l_webe =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "未登録顧客エラー.",
                    l_e);
            log.error("未登録顧客エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate日付設定)<BR>
     * 申込日、適用開始日、適用終了日の設定値と相互関連のチェックをする。<BR>
     * <BR>
     * １） 日付の暦日チェック<BR>
     * 　@①@ 申込日がnull、または暦日でない場合は例外をスローする。（申込日エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01027<BR>
     * 　@② 適用開始日がnull、または暦日でない場合は例外をスローする。（適用開始日エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01028<BR>
     * 　@③ 適用開始日がnull、または暦日でない場合は例外をスローする。（適用終了日エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01029<BR>
     * <BR>
     * ２） 日付の相関関係チェック<BR>
     * 　@①@ 申込日　@≦　@適用開始日　@でない場合は例外をスローする。（適用開始日不適切エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00840<BR>
     * 　@② 適用開始日　@≦　@適用終了日　@でない場合は例外をスローする。（適用終了日不適切エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * <BR>
     * ３） 抽選有りのサービスの場合、申込日が抽選情報テーブルの申込期間内かチェックする。<BR>
     * 　@　@（申込日00:00:00から申込日23:59:59の範囲が申込期間内であればOK）<BR>
     * <BR>
     * 　@①@　@getサービス抽選情報()をコールして、申込期間（自）≦申込日≦申込期間（至）かチェックする。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社コード：引数の証券会社コード<BR>
     * 　@　@　@　@サービス区分：引数のサービス区分<BR>
     * 　@　@　@　@指定日時：引数の申込日（YYYYMMDD）に時間（00:00:00）を付加したものをセット<BR>
     * 　@　@　@　@ロール値：0<BR>
     * <BR>
     * 　@②　@getサービス抽選情報()をコールして、申込期間（自）≦申込日≦申込期間（至）かチェックする。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社コード：引数の証券会社コード<BR>
     * 　@　@　@　@サービス区分：引数のサービス区分<BR>
     * 　@　@　@　@指定日時：引数の申込日（YYYYMMDD）に時間（23:59:59）を付加したものをセット<BR>
     * 　@　@　@　@ロール値：0<BR>
     * <BR>
     * 　@③　@①@と②共に戻り値がnullの場合、申込日が申込期間外と判断し例外をスローする。<BR>
     * 　@　@　@　@（申込日設定エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00993<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分を指定する。<BR>
     * @@param l_strLotDiv - (抽選設定)<BR>
     * 抽選設定（get抽選設定()の戻り値）を指定する。<BR>
     *
     *
     * @@param l_datAppliDate - (申込日)<BR>
     * 明細行の申込日<BR>
     * @@param l_datAppliStartDate - (適用開始日)<BR>
     * 明細行の適用開始日<BR>
     * @@param l_datAppliEndDate - (適用終了日)<BR>
     * 明細行の適用終了日<BR>
     * @@roseuid 4117536002B9
     */
    public void validateTimestampSetup(
        String l_strInstitutionCode,
        String l_strSrvDiv,
        String l_strLotDiv,
        Date l_datAppliDate,
        Date l_datAppliStartDate,
        Date l_datAppliEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateTimestampSetup(String, String, String, Date, Date, Date )";
        log.entering(STR_METHOD_NAME );

        //１） 日付の暦日チェック<BR>
        //①@ 申込日がnull、または暦日でない場合は例外をスローする。（申込日エラー）
        if (l_datAppliDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01027,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("申込日エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //② 適用開始日がnull、または暦日でない場合は例外をスローする。（適用開始日エラー）
        if (l_datAppliStartDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01028,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("適用開始日エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //③ 適用終了日がnull、または暦日でない場合は例外をスローする。（適用終了日エラー）
        if (l_datAppliEndDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01029,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("適用終了日エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //２） 日付の相関関係チェック
        //①@ 申込日　@≦　@適用開始日　@でない場合は例外をスローする。（適用開始日不適切エラー）
        if (WEB3DateUtility.compareToDay(l_datAppliDate, l_datAppliStartDate) > 0)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00840,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("適用開始日不適切エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //② 適用開始日　@≦　@適用終了日　@でない場合は例外をスローする。（適用終了日不適切エラー）
        if (WEB3DateUtility.compareToDay(l_datAppliStartDate, l_datAppliEndDate) > 0)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("適用終了日不適切エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //３） 抽選有りのサービスの場合、申込日が抽選情報テーブルの申込期間内かチェックする。
        if (!WEB3AdminSrvRegiAccountDataUploadCsv.LOT_DIV_NO_LOT_LABEL.equals(l_strLotDiv))
        {
            //①@　@getサービス抽選情報()をコールして、申込期間（自）≦申込日≦申込期間（至）かチェックする。
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
            Timestamp l_tsAppliDate = new Timestamp(l_datAppliDate.getTime());
            l_tsAppliDate.setHours(0);
            l_tsAppliDate.setMinutes(0);
            l_tsAppliDate.setSeconds(0);
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);

            //②　@getサービス抽選情報()をコールして、申込期間（自）≦申込日≦申込期間（至）かチェックする。
			if (l_srvRegiServiceLotInfo == null)
			{
	            l_tsAppliDate.setHours(23);
	            l_tsAppliDate.setMinutes(59);
	            l_tsAppliDate.setSeconds(59);
	            l_srvRegiServiceLotInfo =
	                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);
			}

            if (l_srvRegiServiceLotInfo == null)
            {
                //③　@①@と②共に戻り値がnullの場合、申込日が申込期間外と判断し例外をスローする。
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00993,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("申込日設定エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate利用料金)<BR>
     * 利用料金に値がセットされているかをチェックする。<BR>
     * <BR>
     * 登録区分＝登録区分_有料の場合に、利用料金 ＞ 0の<BR>
     * 数字がセットされていない場合は例外をスローする。（利用料金未入力エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01030<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 登録区分<BR>
     * <BR>
     * 0：有料<BR>
     * 1：無料<BR>
     * @@roseuid 4118374102A5
     */
    public void validateUseAmt(double l_dblUseAmt, String l_strPaymentDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateUseAmt(double, String )";
        log.entering(STR_METHOD_NAME );

        if (WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv))
        {
            if (l_dblUseAmt <= 0 || Double.isNaN(l_dblUseAmt))
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("利用料金未入力エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出金余力)<BR>
     * 出金余力が利用料金以上あるかチェックする。<BR>
     * <BR>
     * １） 出金余力を取得する。<BR>
     *      －拡張アカウントマネージャ.get顧客( )から顧客オブジェクトを取得。<BR>
     *      [get顧客の引数] <BR>
     *　@     証券会社コード＝引数.証券会社コード<BR>
     *　@     部店コード＝引数.部店コード <BR>
     *　@     口座コード＝引数.口座コード<BR>
     *<BR>
     *      －取得した顧客オブジェクト.get補助口座( )から補助口座オブジェクトを取得。<BR>
     *      [get補助口座の引数 <BR>
     *　@     補助口座タイプ="株式取引口座（預り金）"<BR>
     *<BR>
     *      －取引余力サービスImpl.getその他商品買付可能額( )から取引余力を取得。<BR>
     *      [getその他商品買付可能額の引数] <BR>
     *　@     補助口座＝取得した補助口座オブジェクト<BR>
     *　@     受渡日＝出金日<BR>
     * <BR>
     * ２） 出金余力 ＜ 利用料金 の場合は例外をスローする。（出金余力エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01031<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コードを指定する。<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コードを指定する。<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@roseuid 41184CBA025F
     */
    public void validatePaymentPower(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        double l_dblUseAmt,
        Timestamp l_tsPaymentDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePaymentPower(" +
            "String, String, String, double ,Timestamp )";
        log.entering(STR_METHOD_NAME );

        try
        {
            //１） 出金余力を取得する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //－拡張アカウントマネージャ.get顧客( )から顧客オブジェクトを取得。
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
            //取得した顧客オブジェクト.get補助口座( )から補助口座オブジェクトを取得。
            SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException

            //取引余力サービスImpl.getその他商品買付可能額( )から取引余力を取得。
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            //仕様変更対応 NO_209
			double l_dblPaymentPower = l_service.getPaymentTradingPower((WEB3GentradeSubAccount)l_subAccount, l_tsPaymentDate);
            
            //２） 出金余力 ＜ 利用料金 の場合は例外をスローする。（出金余力エラー）
            if (l_dblPaymentPower < l_dblUseAmt)
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01031,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("出金余力エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }
        catch (NotFoundException l_e)
        {
            WEB3BaseException l_webe =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            log.error("登録顧客エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate出金日)<BR>
     * 出金日のチェックをする。<BR>
     * <BR>
     * １） 出金日がnull、または暦日でない場合は例外をスローする。（出金日エラー） <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01032<BR>
     * <BR>
     * ２） 出金日 ≦ 現在日付 の場合は例外をスローする。（出金日エラー）<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01032<BR>
     * ３） 出金日が営業日でない場合は例外をスローする。（出金日エラー）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01032<BR>
     * ４） 営業日・翌営業日のチェック <BR>
     * 　@ サービス利用取引時間管理.get発注日()>出金日の場合、<BR>
     * 　@例外をスローする。（出金日エラー）<BR>
     * @@param l_datPaymentDate - (出金日)<BR>
     * 明細行の出金日<BR>
     * @@roseuid 411759A90384
     */
    public void validatePaymentDate(Date l_datPaymentDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePaymentDate(Date )";
        log.entering(STR_METHOD_NAME );

        //１）出金日がnull、または暦日でない場合は例外をスローする。（出金日エラー）
        if (l_datPaymentDate == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("出金日エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //２） 出金日 ≦ 現在日付 の場合は例外をスローする。（出金日エラー）
        Timestamp l_tsCurrent =
            GtlUtils.getTradingSystem().getSystemTimestamp();
        if (WEB3DateUtility.compareToDay(l_datPaymentDate, l_tsCurrent) <= 0)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("出金日エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //３） 出金日が営業日でない場合は例外をスローする。（出金日エラー）
        Timestamp l_tsPaymentDate = null;
        if (l_datPaymentDate != null)
        {
            l_tsPaymentDate = new Timestamp(l_datPaymentDate.getTime());
        }
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsPaymentDate)))
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("出金日エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // ４） 営業日・翌営業日のチェック
        Date l_datBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();//WEB3SystemLayerException
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datPaymentDate) >= 0)
        {
            String l_strMessage = "出金日エラー." + l_tsCurrent.toLocaleString();
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getアップロード履歴)<BR>
     * 該アップロードファ@イルに関連するアップロード履歴をすべて取得する。 <BR>
     * <BR>
     * this.getアップロード履歴()（overloadメソッド）をコールし、戻り値を返却する。 <BR>
     * <BR>
     * [getアップロード履歴()に指定する引数] <BR>
     * データキー：　@サービス区分<BR>
     * @@param l_lngDataKey - (データキー)<BR>
     * 「（管理者共通）アップロード管理テーブル」.データキーに更新する内容。<BR>
     *
     * @@return webbroker3.gentrade.data.AdministratorUploadRow[]
     * @@roseuid 4110C73F00B5
     */
    public AdministratorUploadRow[] getUploadAction(long l_lngDataKey)
        throws WEB3SystemLayerException
    {
        AdministratorUploadRow[] l_rows = super.getUploadAction(l_lngDataKey);
        return l_rows;
    }

    /**
     * (get最新アップロード履歴)<BR>
     * 該アップロードファ@イルに関連する直近のアップロード履歴を取得する。 <BR>
     * <BR>
     * this.get最新アップロード履歴()（overloadメソッド）をコールし、戻り値を返却する。 <BR>
     * <BR>
     * [getアップロード履歴()に指定する引数] <BR>
     * 備考１：　@サービス区分 <BR>
     * @@param l_lngReference - (備考１)<BR>
     * 「（管理者共通）アップロード管理テーブル」.備考１に更新する内容。<BR>
     *
     * @@return AdministratorUploadRow
     * @@roseuid 4110C6D701E0
     */
    public AdministratorUploadRow getLatestUploadAction(long l_lngReference)
        throws WEB3SystemLayerException
    {
        AdministratorUploadRow l_row = super.getLatestUploadAction(l_lngReference);
        return l_row;
    }

    /**
     * (createカラムヘッダ())<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.アップロード区分ラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.申込登録区分ラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.証券会社コードラベル <BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.部店コードラベル <BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.サービス区分ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.顧客コードラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.顧客名ラベル <BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.登録区分/申込・抽選ラベル <BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.申込日ラベル <BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyymmdd")<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.適用開始日ラベル <BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyymmdd")<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.適用終了日ラベル <BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyymmdd")<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.利用料金ラベル <BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値(double) <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.出金余力ラベル <BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値(double) <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客アップロードCSV.出金日ラベル <BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyymmdd")<BR>
     * @@roseuid 4110C7E902F5
     */
    private void createColumnHeader()
    {
        /*by db layout->csv file layout->顧客データアップロードファ@イル仕様.xls, the code are not fit to javaDoc*/
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 15;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 1
        l_models[1] = new WEB3GentradeCsvColumnModel(
        WEB3AdminSrvRegiAccountDataUploadCsv.REGIST_ID_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 2
        l_models[2] = new WEB3GentradeCsvColumnModel(
        WEB3AdminSrvRegiAccountDataUploadCsv.INSTITUTION_CODE_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 3
        l_models[3] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.BRANCH_CODE_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 4
        l_models[4] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.SRV_DIV_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 5
        l_models[5] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.ACCOUNT_CODE_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 6
        l_models[6] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.ACCOUNT_NAME_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 7
        l_models[7] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

		//障害対応 NO_2140
        //index 8
        DateFormat l_dateFormat8 = new SimpleDateFormat("yyyyMMdd");
        l_dateFormat8.setLenient(false);
        
        l_models[8] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_DATE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat8);
		
		//障害対応 NO_2140
        //index 9
		DateFormat l_dateFormat9 = new SimpleDateFormat("yyyyMMdd");
		l_dateFormat9.setLenient(false);
		
        l_models[9] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_START_DATE_LABEL,
            9,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat9);

		//障害対応 NO_2140
        //index 10
		DateFormat l_dateFormat10 = new SimpleDateFormat("yyyyMMdd");
		l_dateFormat10.setLenient(false);
		
        l_models[10] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_END_DATE_LABEL,
            10,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat10);

        //index 11
        l_models[11] = new WEB3GentradeCsvColumnModel(
        WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 12
        l_models[12] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.USE_AMT_LABEL,
            12,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        //index 13
        l_models[13] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_POWER_LABEL,
            13,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

		//障害対応 NO_2140
        //index 14
		DateFormat l_dateFormat14 = new SimpleDateFormat("yyyyMMdd");
		l_dateFormat14.setLenient(false);
		
        l_models[14] = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DATE_LABEL,
            14,
            WEB3GentradeCsvColumnModel.DATETYPE,
			l_dateFormat14);

        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validateキーヘッダ)<BR>
     * キーヘッダ文字列の妥当性をチェックし、内容をプロパティにセットする。 <BR>
     * <BR>
     * １）　@キーヘッダ指定文字列解析 <BR>
     * 　@キーヘッダ.split(顧客データアップロードCSV.区切り文字)にて、<BR>
     * 区切り文字ごとのtoken[]に分割する。 <BR>
     * 　@tokenの要素数が15でない場合（token.length != 15）、ヘッダの<BR>
     * フォーマットが違うと判定し例外をスローする。 （フォーマットエラー）<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00522<BR>
     * <BR>
     * ２）　@キーヘッダセット <BR>
     * 　@this.setキーヘッダ()にてキーヘッダをセットする。 <BR>
     * <BR>
     * 　@[setキーヘッダ()に指定する引数] <BR>
     * 　@キーヘッダ：　@token[] <BR>
     * @@param l_strKeyHeaderLine - (キーヘッダ行)<BR>
     * @@roseuid 41187FE5000D
     */
    public void validateKeyHeader(String l_strKeyHeaderLine)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateKeyHeader(String )";
        log.entering(STR_METHOD_NAME );

        //１）　@キーヘッダ指定文字列解析
        String[] l_strKeyHeaders = l_strKeyHeaderLine.split(WEB3GentradeCsvDataModel.regex);
        if (l_strKeyHeaders.length != 15)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00522,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("キーヘッダのフォーマットエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //２）　@キーヘッダセット <BR>
        this.setKeyHeader(l_strKeyHeaders);

        log.exiting(STR_METHOD_NAME);
    }

	/**
	 * （validate入力必須項目）<BR>
	 * １）行番号に対応する明細行の入力必須項目をチェックする。<BR>
	 * <BR>
	 * １ー１）アップロード区分のチェック<BR>
	 * 　@this.get項目値()にて取得したアップロード区分==nullの場合、例外をスローする。<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_01020<BR>
	 * <BR>
     * １ー２）証券会社コードのチェック<BR>
     * 　@this.get項目値()にて取得した証券会社コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
	 * １ー３）部店コードのチェック<BR>
	 * 　@this.get項目値()にて取得した部店コード==nullの場合、例外をスローする。<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_00833<BR>
	 * <BR>
	 * １ー４）サービス区分のチェック<BR>
	 * 　@this.get項目値()にて取得したサービス区分==nullの場合、例外をスローする。<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_00758<BR>
	 * <BR>
	 * 　@サービス区分の桁数が2桁の数字でない場合、例外をスローする。<BR>
	 *   class: WEB3BusinessLayerException<BR>                    
	 *   tag:   BUSINESS_ERROR_00831<BR>                          
	 * <BR>                                                             
	 * １ー５）顧客コードのチェック<BR>
	 * 　@this.get項目値()にて取得した顧客コード==nullの場合、例外をスローする。<BR>
	 *         class: WEB3BusinessLayerException<BR>
	 *         tag:   BUSINESS_ERROR_00835<BR>
	 * <BR>
     * １－６）申込抽選区分のチェック<BR>
     * 　@this.get項目値()にて取得した申込抽選区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01905<BR>
     * <BR>
     * <BR>
     * ２）this.get項目値()にて取得したアップロード区分 != '3'(申込属性) の場合、<BR>
     * 以下の処理を行う。<BR>
     * <BR>
     * ２－１）申込登録IDのチェック
     * 　@this.get項目値()にて取得したアップロード区分≠“新規登録” &&<BR>
     *   this.get項目値()にて取得した申込登録ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00832<BR>
     * <BR>
     * ２ー２）登録区分のチェック<BR>
     *   アップロード区分≠抽選アップロードの場合 &&<BR>
     * 　@this.get項目値()にて取得した申込登録ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00841<BR>
     * <BR>
     * ２－３）利用料金のチェック<BR>
     *   this.get項目値()にて取得した登録区分=“有料” &&<BR>
     * 　@this.get項目値()にて取得した利用料金==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01030<BR>
     * <BR>
	 * @@param l_intLineNumber - (行番号)<BR>
	 * 行番号を指定する。<BR>
	 */
    public void validateDispensableItem(int l_intLineNumber)
		throws WEB3BaseException
    {
		final String STR_METHOD_NAME = " validateDispensableItem(int )";
		log.entering(STR_METHOD_NAME);

		// １）行番号に対応する明細行の入力必須項目をチェックする。

        // １ー１）アップロード区分のチェック
        // this.get項目値()にて取得したアップロード区分==nullの場合、例外をスローする。
        if (this.getValue(l_intLineNumber, this.getColumnModel(UPLOAD_DIV_LABEL)) == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01020,
                this.getClass().getName() + STR_METHOD_NAME);
            log.debug("アップロード区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // １ー２）証券会社コードのチェック
        // this.get項目値()にて取得した証券会社コード==nullの場合、例外をスローする。
		if (this.getValue(l_intLineNumber, this.getColumnModel(INSTITUTION_CODE_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00827,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("証券会社コード未入力エラー.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        // １ー３）部店コードのチェック
        // this.get項目値()にて取得した部店コード==nullの場合、例外をスローする。
		if (this.getValue(l_intLineNumber, this.getColumnModel(BRANCH_CODE_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00833,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("部店コード未入力エラー.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        // １ー４）サービス区分のチェック
        // this.get項目値()にて取得したサービス区分==nullの場合、例外をスローする。
		if (this.getValue(l_intLineNumber, this.getColumnModel(SRV_DIV_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00758,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("サービス区分未入力エラー.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        //サービス区分の桁数が2桁の数字でない場合、例外をスローする                       
 		String l_strSrvDiv = this.getValue(l_intLineNumber, this.getColumnModel(SRV_DIV_LABEL)).toString();                                                                               
        if (l_strSrvDiv.length() != 2                                                     
             || !WEB3StringTypeUtility.isInteger(l_strSrvDiv))                             
        {                                                                                 
        	WEB3BaseException l_e =                                                       
                new WEB3BusinessLayerException(                                           
                    WEB3ErrorCatalog.BUSINESS_ERROR_00831,                                
                    this.getClass().getName() + STR_METHOD_NAME);                         
            log.debug("サービス区分エラー.", l_e);                                        
            log.exiting(STR_METHOD_NAME);                                                 
            throw l_e;                                                                    
        }                                                                                 

		// １ー５）顧客コードのチェック
        // this.get項目値()にて取得した顧客コード==nullの場合、例外をスローする。
		if (this.getValue(l_intLineNumber, this.getColumnModel(ACCOUNT_CODE_LABEL)) == null)
		{
			WEB3BaseException l_e =
				new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00835,
					this.getClass().getName() + STR_METHOD_NAME);
			log.debug("顧客コード未入力エラー.", l_e);
			log.exiting(STR_METHOD_NAME);
			throw l_e;
		}

        // １－６）申込抽選区分のチェック
        // this.get項目値()にて取得した申込抽選区分==nullの場合、例外をスローする。
        if (this.getValue(l_intLineNumber, this.getColumnModel(APPLI_LOT_DIV_LABEL)) == null)
        {
            WEB3BaseException l_e =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01905,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("申込抽選区分未指定エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // ２）this.get項目値()にて取得したアップロード区分 != '3'(申込属性) の場合
        if (!APPLI_ATTRIBUTE_LABEL.equals(this.getValue(l_intLineNumber, this.getColumnModel(UPLOAD_DIV_LABEL))))
        {

            // ２－１）申込登録IDのチェック
            // this.get項目値()にて取得したアップロード区分≠“新規登録” &&
            // this.get項目値()にて取得した申込登録ID==nullの場合、例外をスローする。
            if (!UPLOAD_DIV_NEW_REGIST_LABEL.equals(this.getValue(l_intLineNumber,
                this.getColumnModel(UPLOAD_DIV_LABEL)))
                && this.getValue(l_intLineNumber, this.getColumnModel(REGIST_ID_LABEL)) == null)
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00832,
                    this.getClass().getName() + STR_METHOD_NAME);
                log.debug("申込登録ID未指定エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            // ２ー２）登録区分のチェック
            // アップロード区分≠抽選アップロードの場合、
            // this.get項目値()にて取得した登録区分==nullの場合、例外をスローする。
            if (!UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(this.getValue(l_intLineNumber,
                this.getColumnModel(UPLOAD_DIV_LABEL)))
                && this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DIV_LABEL)) == null)
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                    this.getClass().getName() + STR_METHOD_NAME);
                log.debug("登録区分エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            // ２－３）利用料金のチェック
            // this.get項目値()にて取得した登録区分=“有料” &&
            // this.get項目値()にて取得した利用料金==nullの場合、例外をスローする。
            if ((PAYMENT_DIV_CHARGE_LABEL.equals(
                this.getValue(l_intLineNumber, this.getColumnModel(PAYMENT_DIV_LABEL))))
                && (this.getValue(l_intLineNumber, this.getColumnModel(USE_AMT_LABEL)) == null))
            {
                WEB3BaseException l_e =
                    new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                    this.getClass().getName() + STR_METHOD_NAME);
                log.debug("利用料金未入力エラー.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate無料登録日付設定）<BR>
     * 適用開始日、適用終了日の設定値と相互関連のチェックをする。<BR>
     * １） 日付の相関関係チェック  <BR>
     *   １－１）適用開始日 != null && 適用終了日 != null の場合<BR>
     *     TO_CHAR(適用開始日, 'YYYYMMDD')　@>　@適用終了日　@の場合は例外をスローする。<BR>
     *     （適用日不適切エラー） <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * @@param l_datAppliyStartDate - (適用開始日)<BR>
     * 明細行の適用開始日<BR>
     * @@param l_datAppliyEndDate - (適用終了日)<BR>
     * 明細行の適用終了日<BR>
     * @@throws WEB3BaseException
     */
    public void validatePaymentFreeRegiDate(Date l_datAppliyStartDate, Date l_datAppliyEndDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePaymentFreeRegiDate(Date,Date)";
        log.entering(STR_METHOD_NAME);

        // １－１）適用開始日 != null && 適用終了日 != null の場合
        if ((l_datAppliyStartDate != null) && (l_datAppliyEndDate != null))
        {
            // TO_CHAR(適用開始日, 'YYYYMMDD')　@>　@適用終了日　@の場合は例外をスローする。
            if (WEB3DateUtility.compareToDay(l_datAppliyStartDate, l_datAppliyEndDate) > 0)
            {
                log.debug("適用日不適切エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (check明細行数)<BR>
     * アップロード件数が「部店用プリファ@レンステーブル」<BR>
     * に登録されているアップロード可能件数を超えているかのチェックを行う。<BR>
     * <BR>
     * １） 以下の条件で「証券会社プリファ@レンステーブル」を検索する。<BR>
     * [検索条件] <BR>
     * 証券会社ＩＤ  = 引数.証券会社ＩＤ and<BR>
     * プリファ@レンス名 = srvregi.account.upload.maxcount(サービス利用：顧客アップロード可能件数) and<BR>
     * プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ※レコードが取得できなかった場合、以下の処理を行わず終了する。<BR>
     * <BR>
     * ２） 明細行数をチェックする。<BR>
     * 証券会社プリファ@レンステーブル.プリファ@レンスの値(数値型に変換) < 引数.明細行数の場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@:　@BUSINESS_ERROR_02418<BR>
     * @@param l_lngInstitutionId - (証券会社ID)<BR>
     * 部店ID<BR>
     * @@param l_intDetailLine - (明細行数)<BR>
     * 明細行数<BR>
     * @@throws WEB3BaseException
     */
    public void checkDetailLines(long l_lngInstitutionId, int l_intDetailLine) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkDetailLines(long, int)";
        log.entering(STR_METHOD_NAME);

        //１） 以下の条件で「証券会社プリファ@レンステーブル」を検索する。
        //証券会社ＩＤ  = 引数.証券会社ＩＤ and
        //プリファ@レンス名 = srvregi.account.upload.maxcount(サービス利用：顧客アップロード可能件数) and
        //プリファ@レンス名の連番 = 1
       InstitutionPreferencesPK l_institutionPreferencesPK =
            new InstitutionPreferencesPK(
                l_lngInstitutionId,
                WEB3InstitutionPreferencesNameDef.SRVREGI_ACCOUNT_UPLOAD_MAXCOUNT,
                1);

        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByPk(l_institutionPreferencesPK);
        }
        catch (DataFindException l_ex)
        {
            //※レコードが取得できなかった場合、以下の処理を行わず終了する。
            return;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２） 明細行数をチェックする。
        //証券会社プリファ@レンステーブル.プリファ@レンスの値(数値型に変換) < 引数.明細行数の場合、例外をスローする。
        int l_intValue =
            Integer.parseInt(l_institutionPreferencesRow.getValue());
        if (l_intValue < l_intDetailLine)
        {
            WEB3BaseException l_baseException =
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "レコード件数が処理限界値を超えています。");
            log.debug("レコード件数が処理限界値を超えています。", l_baseException);
            log.exiting(STR_METHOD_NAME);
            throw l_baseException;
        }
    }
}
@
