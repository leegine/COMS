head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderDLCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済注文ダウンロードCSV（WEB3AdminEquityForcedSettleOrderDLCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 于瀟(中訊) 新規作成モデル171 191
Revision History : 2008/11/07 姜丹(中訊) 仕様変更モデルNo.211
*/
package webbroker3.eqtypeadmin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveStatusDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityCloseDateDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityContractTypeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityErrorReasonCodeDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityForcedSettleReasonDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityMarginAccruedDaysDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityRepaymentDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityTaxTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済注文ダウンロードCSV)<BR>
 * 強制決済注文ダウンロードCSVクラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderDLCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLCsv.class);

    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public String branchCodeLabel = "部店";

    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public String accountCodeLabel = "顧客";

    /**
     * (顧客名ラベル)<BR>
     * 顧客名ラベル<BR>
     */
    public String accountNameLabel = "顧客名";

    /**
     * (強制決済理由ラベル)<BR>
     * 強制決済理由ラベル<BR>
     */
    public String forcedSettleReasonLabel = "強制決済理由";

    /**
     * (市場名ラベル)<BR>
     * 市場名ラベル<BR>
     */
    public String marketNameLabel = "市場";

    /**
     * (銘柄コードラベル)<BR>
     * 銘柄コードラベル<BR>
     */
    public String productCodeLabel = "銘柄";

    /**
     * (銘柄名ラベル)<BR>
     * 銘柄名ラベル<BR>
     */
    public String productNameLabel = "銘柄名";

    /**
     * (税区分ラベル)<BR>
     * 税区分ラベル<BR>
     */
    public String taxTypeLabel = "口座";

    /**
     * (建区分ラベル)<BR>
     * 建区分ラベル<BR>
     */
    public String contractTypeLabel = "建区分";

    /**
     * (弁済区分ラベル)<BR>
     * 弁済区分ラベル<BR>
     */
    public String repaymentDivLabel = "弁済";

    /**
     * (建日ラベル)<BR>
     * 建日ラベル<BR>
     */
    public String openDateLabel = "建日";

    /**
     * (決済期日ラベル)<BR>
     * 決済期日ラベル<BR>
     */
    public String closeDateLabel = "決済期日";

    /**
     * (建株数ラベル)<BR>
     * 建株数ラベル<BR>
     */
    public String contractQuantityLabel = "建株数";

    /**
     * (建単価ラベル)<BR>
     * 建単価ラベル<BR>
     */
    public String contractPriceLabel = "建単価";

    /**
     * (建代金ラベル)<BR>
     * 建代金ラベル<BR>
     */
    public String contractExecPriceLabel = "建代金";

    /**
     * (保証金率ラベル)<BR>
     * 保証金率ラベル<BR>
     */
    public String marginDepositRateLabel = "保証金率 (%)";

    /**
     * (追証発生日ラベル)<BR>
     * 追証発生日ラベル<BR>
     */
    public String additionalMarginDateLabel = "追証発生日";

    /**
     * (経過日数ラベル)<BR>
     * 経過日数ラベル<BR>
     */
    public String marginAccruedDaysLabel = "経過日数(日)";

    /**
     * (作成日時ラベル)<BR>
     * 作成日時ラベル<BR>
     */
    public String createdTimestampLabel = "作成日時";

    /**
     * (処理日時ラベル)<BR>
     * 処理日時ラベル<BR>
     */
    public String processTimeLabel = "処理日時";

    /**
     * (承認状態ラベル)<BR>
     * 承認状態ラベル<BR>
     */
    public String approveStatusLabel = "承認状態";

    /**
     * (承認者ラベル)<BR>
     * 承認者ラベル<BR>
     */
    public String approverLabel = "承認者";

    /**
     * (強制決済注文ダウンロードCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * <BR>
     * １）super()をコールし、インスタンスを生成する。<BR>
     * <BR>
     * ２）createキーヘッダ()をコールし、キーヘッダ情報を作成する。<BR>
     * <BR>
     * ３）createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@roseuid 477312FD0243
     */
    public WEB3AdminEquityForcedSettleOrderDLCsv()
    {
        super();
        this.createKeyHeader();
        this.createColumnHeader();
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 478326AF022B
     */
    protected void createKeyHeader()
    {
        final String STR_METHOD_NAME = "createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        //キーヘッダ配列
        //－　@index = 0
        //　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。
        String[] l_strKeyHeaders = new String[1];
        l_strKeyHeaders[0] =
            WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
        this.setKeyHeader(l_strKeyHeaders);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。 <BR>
     * <BR>
     * 以下のとおりにCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * １）　@部店コード<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.部店コードラベル<BR>
     * 　@　@カラム番号： 0<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ２）　@顧客コード<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客コードラベル<BR>
     * 　@　@カラム番号： 1<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ３）　@顧客名<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客名ラベル<BR>
     * 　@　@カラム番号： 2<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ４）　@強制決済理由<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.強制決済理由ラベル<BR>
     * 　@　@カラム番号： 3<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ５）　@市場名<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.市場名ラベル<BR>
     * 　@　@カラム番号： 4<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ６）　@銘柄コード<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄コードラベル<BR>
     * 　@　@カラム番号： 5<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ７）　@銘柄名<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄名ラベル<BR>
     * 　@　@カラム番号： 6<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ８）　@税区分<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.税区分ラベル<BR>
     * 　@　@カラム番号： 7<BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * ９）　@建区分<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数]<BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建区分ラベル <BR>
     * 　@　@カラム番号： 8 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * １０）　@弁済区分<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.弁済区分ラベル <BR>
     * 　@　@カラム番号： 9 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * １１）　@建日<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建日ラベル <BR>
     * 　@　@カラム番号： 10 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_日付時間 <BR>
     * 　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d")<BR>
     * <BR>
     * １２）　@決済期日<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.決済期日ラベル <BR>
     * 　@　@カラム番号： 11 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列<BR>
     * 　@　@日付フォーマット： null<BR>
     * <BR>
     * １３）　@建株数<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建株数ラベル <BR>
     * 　@　@カラム番号： 12 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * １４）　@建単価<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建単価ラベル <BR>
     * 　@　@カラム番号： 13 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * １５）　@建代金<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建代金ラベル <BR>
     * 　@　@カラム番号： 14 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * １６）　@保証金率<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.保証金率ラベル <BR>
     * 　@　@カラム番号： 15 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * １７）　@追証発生日<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.追証発生日ラベル <BR>
     * 　@　@カラム番号： 16 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_日付時間 <BR>
     * 　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d")<BR>
     * <BR>
     * １８）　@経過日数<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.経過日数ラベル <BR>
     * 　@　@カラム番号： 17 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * １９）　@作成日時<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.作成日時ラベル <BR>
     * 　@　@カラム番号： 18 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_日付時間 <BR>
     * 　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d H:mm")<BR>
     * <BR>
     * ２０）　@処理日時<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.処理日時ラベル<BR>
     * 　@　@カラム番号： 19 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_日付時間 <BR>
     * 　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d H:mm")<BR>
     * <BR>
     * ２１）　@承認状態<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.承認状態ラベル <BR>
     * 　@　@カラム番号： 20 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * <BR>
     * ２２）　@承認者<BR>
     * <BR>
     * 　@[CSVカラムモデルのコンストラクタの引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.承認者ラベル <BR>
     * 　@　@カラム番号： 21 <BR>
     * 　@　@項目型： CSVカラムモデル.項目型_文字列 <BR>
     * 　@　@日付フォーマット： null <BR>
     * @@roseuid 478329C6015C
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 22;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //１）　@部店コード
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.部店コードラベル
        //　@　@カラム番号： 0
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.branchCodeLabel,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //２）　@顧客コード
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客コードラベル
        //　@　@カラム番号： 1
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.accountCodeLabel,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //３）　@顧客名
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客名ラベル
        //　@　@カラム番号： 2
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.accountNameLabel,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //４）　@強制決済理由
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.強制決済理由ラベル
        //　@　@カラム番号： 3
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.forcedSettleReasonLabel,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //５）　@市場名
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.市場名ラベル
        //　@　@カラム番号： 4
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.marketNameLabel,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //６）　@銘柄コード
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄コードラベル
        //　@　@カラム番号： 5
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.productCodeLabel,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //７）　@銘柄名
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄名ラベル
        //　@　@カラム番号： 6
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.productNameLabel,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //８）　@税区分
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.税区分ラベル
        //　@　@カラム番号： 7
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.taxTypeLabel,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //９）　@建区分
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.建区分ラベル
        //　@　@カラム番号： 8
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[8] = new WEB3GentradeCsvColumnModel(
            this.contractTypeLabel,
            8,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１０）　@弁済区分
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.弁済区分ラベル
        //　@　@カラム番号： 9
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[9] = new WEB3GentradeCsvColumnModel(
            this.repaymentDivLabel,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１１）　@建日
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.建日ラベル
        //　@　@カラム番号： 10
        //　@　@項目型： CSVカラムモデル.項目型_日付時間
        //　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d")
        l_models[10] = new WEB3GentradeCsvColumnModel(
            this.openDateLabel,
            10,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD_SHORT));

        //１２）　@決済期日
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.決済期日ラベル
        //　@　@カラム番号： 11
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[11] = new WEB3GentradeCsvColumnModel(
            this.closeDateLabel,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１３）　@建株数
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.建株数ラベル
        //　@　@カラム番号： 12
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[12] = new WEB3GentradeCsvColumnModel(
            this.contractQuantityLabel,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１４）　@建単価
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.建単価ラベル
        //　@　@カラム番号： 13
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[13] = new WEB3GentradeCsvColumnModel(
            this.contractPriceLabel,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１５）　@建代金
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.建代金ラベル
        //　@　@カラム番号： 14
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[14] = new WEB3GentradeCsvColumnModel(
            this.contractExecPriceLabel,
            14,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１６）　@保証金率
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.保証金率ラベル
        //　@　@カラム番号： 15
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[15] = new WEB3GentradeCsvColumnModel(
            this.marginDepositRateLabel,
            15,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１７）　@追証発生日
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.追証発生日ラベル
        //　@　@カラム番号： 16
        //　@　@項目型： CSVカラムモデル.項目型_日付時間
        //　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d")
        l_models[16] = new WEB3GentradeCsvColumnModel(
            this.additionalMarginDateLabel,
            16,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD_SHORT));

        //１８）　@経過日数
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.経過日数ラベル
        //　@　@カラム番号： 17
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[17] = new WEB3GentradeCsvColumnModel(
            this.marginAccruedDaysLabel,
            17,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //１９）　@作成日時
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.作成日時ラベル
        //　@　@カラム番号： 18
        //　@　@項目型： CSVカラムモデル.項目型_日付時間
        //　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d H:mm")
        l_models[18] = new WEB3GentradeCsvColumnModel(
            this.createdTimestampLabel,
            18,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_YMDHM_SHORT));

        //２０）　@処理日時
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.処理日時ラベル
        //　@　@カラム番号： 19
        //　@　@項目型： CSVカラムモデル.項目型_日付時間
        //　@　@日付フォーマット： new SimpleDateFormat("yyyy/M/d H:mm")
        l_models[19] = new WEB3GentradeCsvColumnModel(
            this.processTimeLabel,
            19,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_YMDHM_SHORT));

        //２１）　@承認状態
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.承認状態ラベル
        //　@　@カラム番号： 20
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[20] = new WEB3GentradeCsvColumnModel(
            this.approveStatusLabel,
            20,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //２２）　@承認者
        //　@[CSVカラムモデルのコンストラクタの引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.承認者ラベル
        //　@　@カラム番号： 21
        //　@　@項目型： CSVカラムモデル.項目型_文字列
        //　@　@日付フォーマット： null
        l_models[21] = new WEB3GentradeCsvColumnModel(
            this.approverLabel,
            21,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.部店コードラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.部店IDに該当する部店.部店コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@roseuid 4784326502C8
     */
    public void setBranchCode(int l_intLineNo, long l_lngBranchId)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にてCSVカラムモデルを取得する。
        //[引数] 項目ラベル： 強制決済注文ダウンロードCSV.部店コードラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.branchCodeLabel);

        String l_strBranchCode = null;
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_lngBranchId);
            l_strBranchCode = l_branch.getBranchCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[引数] 行番号： 引数の行番号
        //   　@　@カラム： １）で取得したカラムモデル
        //   　@　@値： 引数.部店IDに該当する部店.部店コード
        this.setValue(l_intLineNo, l_csvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客)<BR>
     * １）（顧客コード）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客コードラベル <BR>
     * <BR>
     * ２）（顧客コード）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： ２）で取得したカラムモデル <BR>
     * 　@　@値： 引数.口座コード <BR>
     * <BR>
     * ３）（顧客名）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客名ラベル <BR>
     * <BR>
     * ４）（顧客名）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： ４）で取得したカラムモデル <BR>
     * 　@　@値： 引数.口座ＩＤに該当する顧客.get顧客表示名()　@(*1)<BR>
     * <BR>
     * 　@(*1)　@取得できない場合は、nullをセットする。<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@roseuid 4784373B031E
     */
    public void setAccount(int l_intLineNo, String l_strAccountCode, long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);

        //１）（顧客コード）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客コードラベル
        WEB3GentradeCsvColumnModel l_accountCodeColumnModel =
            this.getColumnModel(this.accountCodeLabel);

        //２）（顧客コード）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： ２）で取得したカラムモデル
        //　@　@値： 引数.口座コード
        this.setValue(l_intLineNo, l_accountCodeColumnModel, l_strAccountCode);

        //３）（顧客名）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.顧客名ラベル
        WEB3GentradeCsvColumnModel l_accountNameColumnModel =
            this.getColumnModel(this.accountNameLabel);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strDisplayAccountName = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
            l_strDisplayAccountName = l_mainAccount.getDisplayAccountName();
        }
        catch (NotFoundException l_ex)
        {
            l_strDisplayAccountName = null;
        }
        //４）（顧客名）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： ４）で取得したカラムモデル
        //　@　@値： 引数.口座ＩＤに該当する顧客.get顧客表示名()　@(*1)
        //(*1)　@取得できない場合は、nullをセットする。
        this.setValue(l_intLineNo, l_accountNameColumnModel, l_strDisplayAccountName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set強制決済理由)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.強制決済理由ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.強制決済理由区分＝"期日到来"の場合]<BR>
     * 　@　@　@　@"決済期日到来"<BR>
     * <BR>
     * 　@　@　@[引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・軽度）"、<BR>
     * 　@　@　@　@または、引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・重度）"の場合]<BR>
     * 　@　@　@　@"30%割れ7日以上"<BR>
     * <BR>
     * 　@　@　@[引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"、<BR>
     * 　@　@　@　@または、引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合]<BR>
     * 　@　@　@　@"20%割れ期日超過"<BR>
     * <BR>
     * 　@　@　@[引数.強制決済理由区分＝"手動強制決済"の場合]<BR>
     * 　@　@　@　@"手動強制決済"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strForcedSettleReasonType - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@roseuid 47844876024C
     */
    public void setForcedSettleReason(int l_intLineNo, String l_strForcedSettleReasonType)
    {
        final String STR_METHOD_NAME = "setForcedSettleReason(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.強制決済理由ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.forcedSettleReasonLabel);

        //２）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： １）で取得したカラムモデル
        //　@　@値：
        //　@　@　@[引数.強制決済理由区分＝"期日到来"の場合]
        //　@　@　@　@"決済期日到来"
        String l_strForcedSettleReason = null;
        if (WEB3ForcedSettleReasonType.FIXED_DATE_COMING.equals(l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.FIXED_DATE_COMING;
        }
        //　@　@　@[引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・軽度）"、
        //　@　@　@　@または、引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・重度）"の場合]
        //　@　@　@　@"30%割れ7日以上"
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS.equals(
                l_strForcedSettleReasonType)
            || WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS.equals(
                l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.ADDDEPOSIT_FIRST_DATE_EXCESS;
        }
        //　@　@　@[引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"、
        //　@　@　@　@または、引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合]
        //　@　@　@　@"20%割れ期日超過"
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
                l_strForcedSettleReasonType)
            || WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
                l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.ADDDEPOSIT_SECOND_DATE_EXCESS;
        }
        //　@　@　@[引数.強制決済理由区分＝"手動強制決済"の場合]
        //　@　@　@　@"手動強制決済"
        else if (WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(
            l_strForcedSettleReasonType))
        {
            l_strForcedSettleReason = WEB3AdminEquityForcedSettleReasonDivDef.MANUAL_FORCED_SETTLE;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strForcedSettleReason);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set市場名)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.市場名ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.市場IDに該当する市場.市場名<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_lngMarketId - (市場ID)<BR>
     * 市場ID<BR>
     * @@roseuid 4784496B0053
     */
    public void setMarketName(int l_intLineNo, long l_lngMarketId)
    {
        final String STR_METHOD_NAME = "setMarketName(int, long)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.市場名ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.marketNameLabel);

        String l_strMarketName = null;
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
            Market l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_strMarketName = l_market.getMarketName();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //２）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： １）で取得したカラムモデル
        //　@　@値： 引数.市場IDに該当する市場.市場名
        this.setValue(l_intLineNo, l_csvColumnModel, l_strMarketName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set銘柄)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄コードラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.銘柄コードの左4byte<BR>
     * <BR>
     * ３）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄名ラベル<BR>
     * <BR>
     * ４）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： ３）で取得したカラムモデル <BR>
     * 　@　@値： 引数.銘柄IDに該当する株式銘柄.銘柄名　@(*1)<BR>
     * <BR>
     * 　@(*1)　@取得できない場合は、nullをセットする。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@roseuid 478449DF00ED
     */
    public void setProduct(int l_intLineNumber, String l_strProductCode, long l_lngProductId)
    {
        final String STR_METHOD_NAME = "setProduct(int, String, long)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄コードラベル
        WEB3GentradeCsvColumnModel l_productCodeCsvColumnModel =
            this.getColumnModel(this.productCodeLabel);

        //２）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： １）で取得したカラムモデル
        //　@　@値： 引数.銘柄コードの左4byte
        this.setValue(l_intLineNumber, l_productCodeCsvColumnModel, l_strProductCode.substring(0, 4));

        //３）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.銘柄名ラベル
        WEB3GentradeCsvColumnModel l_productNameCsvColumnModel =
            this.getColumnModel(this.productNameLabel);

        String l_strProductName = null;
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            ProductManager l_productManager = l_tradingModule.getProductManager();
            EqTypeProduct l_eqTypeProduct = (EqTypeProduct)l_productManager.getProduct(l_lngProductId);
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqTypeProduct.getDataSourceObject();
            l_strProductName = l_eqtypeProductRow.getStandardName();
        }
        catch (NotFoundException l_ex)
        {
            l_strProductName = null;
        }
        //４）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： ３）で取得したカラムモデル
        //　@　@値： 引数.銘柄IDに該当する株式銘柄.銘柄名　@(*1)
        //　@(*1)　@取得できない場合は、nullをセットする。
        this.setValue(l_intLineNumber, l_productNameCsvColumnModel, l_strProductName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set税区分)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.税区分ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.税区分＝TaxTypeEnum."一般"の場合]<BR>
     * 　@　@　@　@"一般"<BR>
     * 　@　@　@[引数.税区分＝TaxTypeEnum."特定"または、 <BR>
     * 　@　@　@　@TaxTypeEnum."特定口座かつ源泉徴収"の場合]<BR>
     * 　@　@　@　@"特定"<BR>
     * 　@　@　@[以外の場合]<BR>
     * 　@　@　@　@nullをセットする。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * @@roseuid 47844A290040
     */
    public void setTaxType(int l_intLineNo, TaxTypeEnum l_taxType)
    {
        final String STR_METHOD_NAME = "setTaxType(int, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.税区分ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.taxTypeLabel);

        //２）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： １）で取得したカラムモデル
        //　@　@値：
        //　@　@　@[引数.税区分＝TaxTypeEnum."一般"の場合]
        //　@　@　@　@"一般"
        //　@　@　@[引数.税区分＝TaxTypeEnum."特定"または、
        //　@　@　@　@TaxTypeEnum."特定口座かつ源泉徴収"の場合]
        //　@　@　@　@"特定"
        //　@　@　@[以外の場合]
        //　@　@　@　@nullをセットする。
        String l_strTaxType = null;
        if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            l_strTaxType = WEB3AdminEquityTaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            l_strTaxType = WEB3AdminEquityTaxTypeDef.SPECIAL;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strTaxType);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set建区分)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建区分ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.建区分＝ContractTypeEnum."買建"の場合]<BR>
     * 　@　@　@　@"新規買"<BR>
     * 　@　@　@[引数.建区分＝ContractTypeEnum."売建"の場合]<BR>
     * 　@　@　@　@"新規売"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_contractType - (建区分)<BR>
     * 建区分<BR>
     * @@roseuid 47844A7800FA
     */
    public void setContractType(int l_intLineNo, ContractTypeEnum l_contractType)
    {
        final String STR_METHOD_NAME = "setContractType(int, ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.建区分ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.contractTypeLabel);
        //
        //２）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： １）で取得したカラムモデル
        //　@　@値：
        //　@　@　@[引数.建区分＝ContractTypeEnum."買建"の場合]
        //　@　@　@　@"新規買"
        //　@　@　@[引数.建区分＝ContractTypeEnum."売建"の場合]
        //　@　@　@　@"新規売"
        String l_strContractType = null;
        if (ContractTypeEnum.LONG.equals(l_contractType))
        {
            l_strContractType = WEB3AdminEquityContractTypeDef.LONG;
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractType))
        {
            l_strContractType = WEB3AdminEquityContractTypeDef.SHORT;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strContractType);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set弁済区分)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.弁済区分ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.弁済区分＝"1：制度信用"の場合]<BR>
     * 　@　@　@　@"制度信用"<BR>
     * 　@　@　@[引数.弁済区分＝"2：一般信用"の場合]<BR>
     * 　@　@　@　@"一般信用"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strRepaymentDiv - (弁済区分)<BR>
     * 弁済区分<BR>
     * @@roseuid 47844A9900AB
     */
    public void setRepaymentDiv(int l_intLineNo, String l_strRepaymentDiv)
    {
        final String STR_METHOD_NAME = "setRepaymentDiv(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        //　@[引数]
        //　@　@項目ラベル： 強制決済注文ダウンロードCSV.弁済区分ラベル
        WEB3GentradeCsvColumnModel l_csvColumnModel =
            this.getColumnModel(this.repaymentDivLabel);

        //２）値セット
        //　@this.set項目値()にて項目値をセットする。
        //　@[引数]
        //　@　@行番号： 引数の行番号
        //　@　@カラム： １）で取得したカラムモデル
        //　@　@値：
        //　@　@　@[引数.弁済区分＝"1：制度信用"の場合]
        //　@　@　@　@"制度信用"
        //　@　@　@[引数.弁済区分＝"2：一般信用"の場合]
        //　@　@　@　@"一般信用"
        String l_strRepayment = null;
        if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentDiv))
        {
            l_strRepayment = WEB3AdminEquityRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        else
        {
            l_strRepayment = WEB3AdminEquityRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strRepayment);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set建日)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建日ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.建日<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datOpenDate - (建日)<BR>
     * 建日<BR>
     * @@roseuid 47844AC70195
     */
    public void setOpenDate(int l_intLineNo, Date l_datOpenDate)
    {
        final String STR_METHOD_NAME = "setOpenDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.openDateLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datOpenDate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set決済期日)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.決済期日ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.決済期日が"9999/12/31"の場合]<BR>
     * 　@　@　@　@"無期"<BR>
     * 　@　@　@[以外の場合]<BR>
     * 　@　@　@　@"yyyy/M/d"にフォーマットしたもの。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datCloseDate - (決済期日)<BR>
     * 決済期日<BR>
     * @@roseuid 47844B0B0174
     */
    public void setCloseDate(int l_intLineNo, Date l_datCloseDate)
    {
        final String STR_METHOD_NAME = "setCloseDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.closeDateLabel);

        String l_strCloseDate = null;
        if (WEB3GentradeTimeDef.MAX_YMD.equals(
            WEB3DateUtility.formatDate(l_datCloseDate, WEB3GentradeTimeDef.DATE_SPLIT_YMD)))
        {
            l_strCloseDate = WEB3AdminEquityCloseDateDef.INDEFINITE;
        }
        else
        {
            l_strCloseDate =
                WEB3DateUtility.formatDate(l_datCloseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD_SHORT);
        }
        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strCloseDate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set建株数)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建株数ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.建株数<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strContractQuantity - (建株数)<BR>
     * 建株数<BR>
     * @@roseuid 47844B2E0089
     */
    public void setContractQuantity(int l_intLineNo, String l_strContractQuantity)
    {
        final String STR_METHOD_NAME = "setContractQuantity(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.contractQuantityLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strContractQuantity);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set建単価)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建単価ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.建単価<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strContractPrice - (建単価)<BR>
     * 建単価<BR>
     * @@roseuid 47844B680386
     */
    public void setContractPrice(int l_intLineNo, String l_strContractPrice)
    {
        final String STR_METHOD_NAME = "setContractPrice(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.contractPriceLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strContractPrice);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set建代金)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.建代金ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.建代金<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strContractExecPrice - (建代金)<BR>
     * 建代金<BR>
     * @@roseuid 47849CF70336
     */
    public void setContractExecPrice(int l_intLineNo, String l_strContractExecPrice)
    {
        final String STR_METHOD_NAME = "setContractExecPrice(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.contractExecPriceLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strContractExecPrice);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set保証金率)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.保証金率ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.保証金率<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strMarginDepositRate - (保証金率)<BR>
     * 保証金率<BR>
     * @@roseuid 47844B6A0116
     */
    public void setMarginDepositRate(int l_intLineNo, String l_strMarginDepositRate)
    {
        final String STR_METHOD_NAME = "setMarginDepositRate(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.marginDepositRateLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strMarginDepositRate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set追証発生日)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.追証発生日ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.追証発生日<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datAdditionalMarginDate - (追証発生日)<BR>
     * 追証発生日<BR>
     * @@roseuid 47844BFC019F
     */
    public void setAdditionalMarginDate(int l_intLineNo, Date l_datAdditionalMarginDate)
    {
        final String STR_METHOD_NAME = "setAdditionalMarginDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.additionalMarginDateLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datAdditionalMarginDate);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set経過日数)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.経過日数ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"、<BR>
     * 　@　@　@　@または、引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合]<BR>
     * 　@　@　@　@　@"追証未入"<BR>
     * 　@　@　@[以外の場合]<BR>
     * 　@　@　@　@　@引数.経過日数<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strMarginAccruedDays - (経過日数)<BR>
     * 経過日数<BR>
     * @@param l_strForcedSettleReasonType - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@roseuid 47844C4E0239
     */
    public void setMarginAccruedDays(int l_intLineNo,
        String l_strMarginAccruedDays, String l_strForcedSettleReasonType)
    {
        final String STR_METHOD_NAME = "setMarginAccruedDays(int, String, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.marginAccruedDaysLabel);

        //引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"
        //または、引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合
        if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
                l_strForcedSettleReasonType)
            || WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
                l_strForcedSettleReasonType))
        {
            //"追証未入"
            this.setValue(
                l_intLineNo,
                l_gentradeCsvColumnModel,
                WEB3AdminEquityMarginAccruedDaysDef.ADDITIONAL_DEPOSIT_NOT);
        }
        else
        {
            //引数.経過日数
            this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strMarginAccruedDays);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set作成日時)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.作成日時ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.作成日時<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datCreatedTimestamp - (作成日時)<BR>
     * 作成日時<BR>
     * @@roseuid 47844C7D01DB
     */
    public void setCreatedTimestamp(int l_intLineNo, Date l_datCreatedTimestamp)
    {
        final String STR_METHOD_NAME = "setCreatedTimestamp(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.createdTimestampLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datCreatedTimestamp);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set処理日時)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.処理日時ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.処理日時<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datProcessTime - (処理日時)<BR>
     * 処理日時<BR>
     * @@roseuid 47844CB20360
     */
    public void setProcessTime(int l_intLineNo, Date l_datProcessTime)
    {
        final String STR_METHOD_NAME = "setProcessTime(int, Date)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.processTimeLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_datProcessTime);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set承認状態)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.承認状態ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： <BR>
     * 　@　@　@[引数.強制決済理由区分＝"手動強制決済"の場合]<BR>
     * 　@　@　@　@"手動承認済"<BR> 
     * 　@　@　@[引数.承認状態区分＝"0：未承認"の場合]<BR>
     * 　@　@　@　@"未承認"<BR>
     * 　@　@　@[引数.承認状態区分＝"1：承認済"の場合]<BR>
     * 　@　@　@　@"承認済"<BR>
     * 　@　@　@[引数.承認状態区分＝"2：非承認"の場合]<BR>
     * 　@　@　@　@"否認済"<BR>
     * 　@　@　@[引数.承認状態区分＝"9：エラー"の場合]<BR>
     * 　@　@　@　@[引数.注文エラー理由コード＝"建株残高不足エラー"の場合]<BR>
     * 　@　@　@　@　@"建株残高不足エラー"<BR>
     * 　@　@　@　@[引数.注文エラー理由コード＝"売買停止銘柄エラー"の場合]<BR>
     * 　@　@　@　@　@"売買停止銘柄エラー"<BR>
     * 　@　@　@　@[引数.注文エラー理由コード＝"決済期日到来済エラー"の場合]<BR>
     * 　@　@　@　@　@"決済期日到来済エラー"<BR><BR>
     * 　@　@　@　@[引数.注文エラー理由コード＝"現引・現渡注文登録済エラー"の場合]<BR>
     * 　@　@　@　@　@"現引・現渡注文登録済エラー"<BR>
     * 　@　@　@　@[引数.注文エラー理由コード＝"その他エラー"の場合]<BR>
     * 　@　@　@　@　@"その他エラー"<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strApproveStatusType - (承認状態区分)<BR>
     * 承認状態区分<BR>
     * @@param l_strOrderErrorReasonCode - (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * @@param l_strForcedSettleReasonType - (強制決済理由区分)<BR>
     * 強制決済理由区分<BR>
     * @@roseuid 47844CCA0247
     */
    public void setApproveStatus(
        int l_intLineNo,
        String l_strApproveStatusType,
        String l_strOrderErrorReasonCode,
        String l_strForcedSettleReasonType)
    {
        final String STR_METHOD_NAME = "setApproveStatus(int, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.approveStatusLabel);
        //値
        //[引数.強制決済理由区分＝"手動強制決済"の場合]
        String l_strApproveStatus = null;
        if (WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(l_strForcedSettleReasonType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.MANUAL_APPROVED;
        }
        //引数.承認状態区分＝"0：未承認"の場合
        else if (WEB3ApproveStatusType.UNAPPROVED.equals(l_strApproveStatusType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.UNAPPROVED;
        }
        //引数.承認状態区分＝"1：承認済"の場合
        else if (WEB3ApproveStatusType.APPROVED.equals(l_strApproveStatusType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.APPROVED;
        }
        //引数.承認状態区分＝"2：非承認"の場合
        else if (WEB3ApproveStatusType.NON_APPROVED.equals(l_strApproveStatusType))
        {
            l_strApproveStatus = WEB3AdminEquityApproveStatusDef.NON_APPROVED;
        }
        //引数.承認状態区分＝"9：エラー"の場合
        else if (WEB3ApproveStatusType.ERROR.equals(l_strApproveStatusType))
        {
            //引数.注文エラー理由コード＝"建株残高不足エラー"の場合
            if (WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR;
            }
            //引数.注文エラー理由コード＝"売買停止銘柄エラー"の場合
            else if (WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
            }
            //引数.注文エラー理由コード＝"決済期日到来済エラー"の場合
            else if (WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.SETTLEDAY_CAME_ERROR;
            }
            //引数.注文エラー理由コード＝"現引・現渡注文登録済エラー"の場合
            else if (WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR;
            }
            //引数.注文エラー理由コード＝"その他エラー"の場合
            else if (WEB3ErrorReasonCodeDef.OTHRE_ERROR.equals(l_strOrderErrorReasonCode))
            {
                l_strApproveStatus = WEB3AdminEquityErrorReasonCodeDef.OTHRE_ERROR;
            }
        }

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strApproveStatus);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set承認者)<BR>
     * １）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@項目ラベル： 強制決済注文ダウンロードCSV.承認者ラベル<BR>
     * <BR>
     * ２）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@行番号： 引数の行番号 <BR>
     * 　@　@カラム： １）で取得したカラムモデル <BR>
     * 　@　@値： 引数.承認者コード<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strApproverCode - (承認者コード)<BR>
     * 承認者コード<BR>
     * @@roseuid 47844CF503BD
     */
    public void setApprover(int l_intLineNo, String l_strApproverCode)
    {
        final String STR_METHOD_NAME = "setApprover(int, String)";
        log.entering(STR_METHOD_NAME);

        //this.getカラムモデル()にてCSVカラムモデルを取得する
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.approverLabel);

        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strApproverCode);
        log.exiting(STR_METHOD_NAME);
    }
}
@
