head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccOpenApplyDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設申込ダウンロードCSV(WEB3AdminFXAccOpenApplyDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 譚漢江 (中訊) 新規作成
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル486
Revesion History : 2008/09/08 許  丹 (中訊) 仕様変更・モデル909~934
Revesion History : 2008/10/06 王志葵 (中訊) 仕様変更・モデル1057,1058,1059
*/

package webbroker3.aio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.aio.define.WEB3AdminFXAccOpenApplyDownloadCsvColumnDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者FX口座開設申込ダウンロードCSV)<BR>
 * 管理者FX口座開設申込ダウンロードCSVクラス 
 * 
 * @@author 譚漢江(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyDownloadCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenApplyDownloadCsv.class);

    /**
     * (追加変更区分ラベル)<BR>
     * 追加変更区分ラベル<BR>
     */
    public static  String addModifiedDivLabel = "追加変更区分";
    
    /**
     * (利用者コードラベル)<BR>
     * 利用者コードラベル<BR>
     */
    public static  String userCodeLabel = "利用者コード";
    
    /**
     * (利用者名ラベル)<BR>
     * 利用者名ラベル<BR>
     */
    public static  String userlNameLabel = "利用者名";
    
    /**
     * (ログインIDラベル)<BR>
     * ログインIDラベル<BR>
     */
    public static  String loginIdLabel = "ログインID";
    
    /**
     * (ログインパスワードラベル)<BR>
     * ログインパスワードラベル<BR>
     */
    public static  String loginPasswordLabel = "ログインパスワード";
    
    /**
     * (発注パスワードラベル)<BR>
     * 発注パスワードラベル<BR>
     */
    public static  String orderPasswordLabel = "発注パスワード";
    
    /**
     * (メールアドレス１ラベル)<BR>
     * メールアドレス１ラベル<BR>
     */
    public static  String mailAddress1Label = "メールアドレス１";
    
    /**
     * (メールアドレス２ラベル)<BR>
     * メールアドレス２ラベル<BR>
     */
    public static  String mailAddress2Label = "メールアドレス２";
    
    /**
     * (自己受託区分ラベル)<BR>
     * 自己受託区分ラベル<BR>
     */
    public static  String selfTrustDivLabel = "自己受託区分";

    /**
     * (利用者属性ラベル)<BR>
     * 利用者属性ラベル<BR>
     */
    public static  String userAttributeLabel = "利用者属性";

    /**
     * (決済方法@ラベル)<BR>
     * 決済方法@ラベル<BR>
     */
    public static  String transferMethodLabel = "決済方法@";
    
    /**
     * (ロスカット区分ラベル)<BR>
     * ロスカット区分ラベル<BR>
     */
    public static  String lossCutDivLabel = "ロスカット区分";
    
    /**
     * (手数料区分ラベル)<BR>
     * 手数料区分ラベル<BR>
     */
    public static  String commissionDivLabel = "手数料区分";
    
    /**
     * (取引可能区分ラベル)<BR>
     * 取引可能区分ラベル<BR>
     */
    public static  String tradingDivLabel = "取引可能区分";

    /**
     * (電子交付承諾日ラベル)<BR>
     * 電子交付承諾日ラベル<BR>
     */
    public static  String reportAcceptDateLabel = "電子交付承諾日";

    /**
     * (取引説明書確認日ラベル)<BR>
     * 取引説明書確認日ラベル<BR>
     */
    public static  String tradeInstructionsConfirmDateLabel = "取引説明書確認日";

    /**
     * (約諾書番号ラベル)<BR>
     * 約諾書番号ラベル<BR>
     */
    public static  String contractCodeLabel = "約諾書番号";
    
    /**
     * (備考ラベル)<BR>
     * 備考ラベル<BR>
     */
    public static  String remarkLabel = "備考";
    
    /**
     * (商品コード１ラベル)<BR>
     * 商品コード１ラベル<BR>
     */
    public static  String productCode1Label = "商品コード１";
    
    /**
     * (発注上限数量１ラベル)<BR>
     * 発注上限数量１ラベル<BR>
     */
    public static  String orderQuantityUpper1Label = "発注上限数量１";
    
    /**
     * (商品コード２ラベル)<BR>
     * 商品コード２ラベル<BR>
     */
    public static  String productCode2Label = "商品コード２";
    
    /**
     * (発注上限数量２ラベル)<BR>
     * 発注上限数量２ラベル<BR>
     */
    public static  String orderQuantityUpper2Label = "発注上限数量２";
    
    /**
     * (商品コード３ラベル)<BR>
     * 商品コード３ラベル<BR>
     */
    public static  String productCode3Label = "商品コード３";
    
    /**
     * (発注上限数量３ラベル)<BR>
     * 発注上限数量３ラベル<BR>
     */
    public static  String orderQuantityUpper3Label = "発注上限数量３";
    
    /**
     * (商品コード４ラベル)<BR>
     * 商品コード４ラベル<BR>
     */
    public static  String productCode4Label = "商品コード４";
    
    /**
     * (発注上限数量４ラベル)<BR>
     * 発注上限数量４ラベル<BR>
     */
    public static  String orderQuantityUpper4Label = "発注上限数量４";
    
    /**
     * (商品コード５ラベル)<BR>
     * 商品コード５ラベル<BR>
     */
    public static  String productCode5Label = "商品コード５";
    
    /**
     * (発注上限数量５ラベル)<BR>
     * 発注上限数量５ラベル<BR>
     */
    public static  String orderQuantityUpper5Label = "発注上限数量５";
    
    /**
     * (商品コード６ラベル)<BR>
     * 商品コード６ラベル<BR>
     */
    public static  String productCode6Label = "商品コード６";
    
    /**
     * (発注上限数量６ラベル)<BR>
     * 発注上限数量６ラベル<BR>
     */
    public static  String orderQuantityUpper6Label = "発注上限数量６";
    
    /**
     * (商品コード７ラベル)<BR>
     * 商品コード７ラベル<BR>
     */
    public static  String productCode7Label = "商品コード７";
    
    /**
     * (発注上限数量７ラベル)<BR>
     * 発注上限数量７ラベル<BR>
     */
    public static  String orderQuantityUpper7Label = "発注上限数量７";

    /**
     * (商品コード８ラベル)<BR>
     * 商品コード８ラベル<BR>
     */
    public static  String productCode8Label = "商品コード８";

    /**
     * (発注上限数量８ラベル)<BR>
     * 発注上限数量８ラベル<BR>
     */
    public static  String orderQuantityUpper8Label = "発注上限数量８";

    /**
     * (商品コード９ラベル)<BR>
     * 商品コード９ラベル<BR>
     */
    public static  String productCode9Label = "商品コード９";

    /**
     * (発注上限数量９ラベル)<BR>
     * 発注上限数量９ラベル<BR>
     */
    public static  String orderQuantityUpper9Label = "発注上限数量９";

    /**
     * (商品コード１０ラベル)<BR>
     * 商品コード１０ラベル<BR>
     */
    public static  String productCode10Label = "商品コード１０";

    /**
     * (発注上限数量１０ラベル)<BR>
     * 発注上限数量１０ラベル<BR>
     */
    public static  String orderQuantityUpper10Label = "発注上限数量１０";

    /**
     * (商品コード１１ラベル)<BR>
     * 商品コード１１ラベル<BR>
     */
    public static  String productCode11Label = "商品コード１１";

    /**
     * (発注上限数量１１ラベル)<BR>
     * 発注上限数量１１ラベル<BR>
     */
    public static  String orderQuantityUpper11Label = "発注上限数量１１";

    /**
     * (商品コード１２ラベル)<BR>
     * 商品コード１２ラベル<BR>
     */
    public static  String productCode12Label = "商品コード１２";

    /**
     * (発注上限数量１２ラベル)<BR>
     * 発注上限数量１２ラベル<BR>
     */
    public static  String orderQuantityUpper12Label = "発注上限数量１２";

    /**
     * (商品コード１３ラベル)<BR>
     * 商品コード１３ラベル<BR>
     */
    public static  String productCode13Label = "商品コード１３";

    /**
     * (発注上限数量１３ラベル)<BR>
     * 発注上限数量１３ラベル<BR>
     */
    public static  String orderQuantityUpper13Label = "発注上限数量１３";

    /**
     * (商品コード１４ラベル)<BR>
     * 商品コード１４ラベル<BR>
     */
    public static  String productCode14Label = "商品コード１４";

    /**
     * (発注上限数量１４ラベル)<BR>
     * 発注上限数量１４ラベル<BR>
     */
    public static  String orderQuantityUpper14Label = "発注上限数量１４";

    /**
     * (商品コード１５ラベル)<BR>
     * 商品コード１５ラベル<BR>
     */
    public static  String productCode15Label = "商品コード１５";

    /**
     * (発注上限数量１５ラベル)<BR>
     * 発注上限数量１５ラベル<BR>
     */
    public static  String orderQuantityUpper15Label = "発注上限数量１５";

    /**
     * (商品コード１６ラベル)<BR>
     * 商品コード１６ラベル<BR>
     */
    public static  String productCode16Label = "商品コード１６";

    /**
     * (発注上限数量１６ラベル)<BR>
     * 発注上限数量１６ラベル<BR>
     */
    public static  String orderQuantityUpper16Label = "発注上限数量１６";

    /**
     * (商品コード１７ラベル)<BR>
     * 商品コード１７ラベル<BR>
     */
    public static  String productCode17Label = "商品コード１７";

    /**
     * (発注上限数量１７ラベル)<BR>
     * 発注上限数量１７ラベル<BR>
     */
    public static  String orderQuantityUpper17Label = "発注上限数量１７";

    /**
     * (商品コード１８ラベル)<BR>
     * 商品コード１８ラベル<BR>
     */
    public static  String productCode18Label = "商品コード１８";

    /**
     * (発注上限数量１８ラベル)<BR>
     * 発注上限数量１８ラベル<BR>
     */
    public static  String orderQuantityUpper18Label = "発注上限数量１８";

    /**
     * (商品コード１９ラベル)<BR>
     * 商品コード１９ラベル<BR>
     */
    public static  String productCode19Label = "商品コード１９";

    /**
     * (発注上限数量１９ラベル)<BR>
     * 発注上限数量１９ラベル<BR>
     */
    public static  String orderQuantityUpper19Label = "発注上限数量１９";

    /**
     * (商品コード２０ラベル)<BR>
     * 商品コード２０ラベル<BR>
     */
    public static  String productCode20Label = "商品コード２０";

    /**
     * (発注上限数量２０ラベル)<BR>
     * 発注上限数量２０ラベル<BR>
     */
    public static  String orderQuantityUpper20Label = "発注上限数量２０";

    /**
     * (商品コード２１ラベル)<BR>
     * 商品コード２１ラベル<BR>
     */
    public static  String productCode21Label = "商品コード２１";

    /**
     * (発注上限数量２１ラベル)<BR>
     * 発注上限数量２１ラベル<BR>
     */
    public static  String orderQuantityUpper21Label = "発注上限数量２１";

    /**
     * (商品コード２２ラベル)<BR>
     * 商品コード２２ラベル<BR>
     */
    public static  String productCode22Label = "商品コード２２";

    /**
     * (発注上限数量２２ラベル)<BR>
     * 発注上限数量２２ラベル<BR>
     */
    public static  String orderQuantityUpper22Label = "発注上限数量２２";

    /**
     * (商品コード２３ラベル)<BR>
     * 商品コード２３ラベル<BR>
     */
    public static  String productCode23Label = "商品コード２３";

    /**
     * (発注上限数量２３ラベル)<BR>
     * 発注上限数量２３ラベル<BR>
     */
    public static  String orderQuantityUpper23Label = "発注上限数量２３";

    /**
     * (商品コード２４ラベル)<BR>
     * 商品コード２４ラベル<BR>
     */
    public static  String productCode24Label = "商品コード２４";

    /**
     * (発注上限数量２４ラベル)<BR>
     * 発注上限数量２４ラベル<BR>
     */
    public static  String orderQuantityUpper24Label = "発注上限数量２４";

    /**
     * (商品コード２５ラベル)<BR>
     * 商品コード２５ラベル<BR>
     */
    public static  String productCode25Label = "商品コード２５";

    /**
     * (発注上限数量２５ラベル)<BR>
     * 発注上限数量２５ラベル<BR>
     */
    public static  String orderQuantityUpper25Label = "発注上限数量２５";
    
    /**
     * (管理者FX口座開設申込ダウンロードCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * this.createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@roseuid 43D5BD8B03DB
     */
    public WEB3AdminFXAccOpenApplyDownloadCsv() 
    {
        this.createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * - index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.追加変更区分ラベル<BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * - index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.利用者コードラベル<BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * - index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.利用者名ラベル<BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * - index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.ログインIDラベル<BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * - index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.ログインパスワードラベル<BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * - index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注パスワードラベル<BR>
     * 　@カラム番号：5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * - index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.メールアドレス１ラベル<BR>
     * 　@カラム番号： 6 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index =  7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.メールアドレス２ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.自己受託区分ラベル<BR>
     * 　@カラム番号： 8 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.利用者属性ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.決済方法@ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.手数料区分ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.ロスカット区分ラベル<BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.取引可能区分ラベル<BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 14<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.電子交付承諾日ラベル<BR>
     * 　@カラム番号： 14<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付 <BR>
     * 　@日付フォーマット：　@ new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * - index = 15<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.取引説明書確認日ラベル<BR>
     * 　@カラム番号： 15<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付 <BR>
     * 　@日付フォーマット：　@ new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * - index = 16<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.約諾書番号ラベル<BR>
     * 　@カラム番号： 16 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 17<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.備考ラベル<BR>
     * 　@カラム番号： 17 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 18<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１ラベル<BR>
     * 　@カラム番号： 18<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 19<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１ラベル<BR>
     * 　@カラム番号： 19<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 20<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２ラベル<BR>
     * 　@カラム番号： 20<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 21<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２ラベル<BR>
     * 　@カラム番号： 21<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 22<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード３ラベル<BR>
     * 　@カラム番号： 22<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 23<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量３ラベル<BR>
     * 　@カラム番号： 23<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 24<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード４ラベル<BR>
     * 　@カラム番号： 24<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 25<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量４ラベル<BR>
     * 　@カラム番号： 25<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 26<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード５ラベル<BR>
     * 　@カラム番号： 26<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 27<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量５ラベル<BR>
     * 　@カラム番号： 27<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 28<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード６ラベル<BR>
     * 　@カラム番号： 28<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 29<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量６ラベル<BR>
     * 　@カラム番号： 29<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 30<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード７ラベル<BR>
     * 　@カラム番号： 30<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 31<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量７ラベル<BR>
     * 　@カラム番号： 31<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 32<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード８ラベル<BR>
     * 　@カラム番号： 32<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 33<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量８ラベル<BR>
     * 　@カラム番号： 33<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 34<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード９ラベル<BR>
     * 　@カラム番号： 34<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * - index = 35<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量９ラベル<BR>
     * 　@カラム番号： 35<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 36<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１０ラベル<BR>
     * 　@カラム番号： 36<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 37<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１０ラベル<BR>
     * 　@カラム番号： 37<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 38<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１１ラベル<BR>
     * 　@カラム番号： 38<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 39<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１１ラベル<BR>
     * 　@カラム番号： 39<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 40<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１２ラベル<BR>
     * 　@カラム番号： 40<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 41<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１２ラベル<BR>
     * 　@カラム番号： 41<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 42<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１３ラベル<BR>
     * 　@カラム番号： 42<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 43<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１３ラベル<BR>
     * 　@カラム番号： 43<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 44<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１４ラベル<BR>
     * 　@カラム番号： 44<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 45<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１４ラベル<BR>
     * 　@カラム番号： 45<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 46<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１５ラベル<BR>
     * 　@カラム番号： 46<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 47<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１５ラベル<BR>
     * 　@カラム番号： 47<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 48<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１６ラベル<BR>
     * 　@カラム番号： 48<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 49<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１６ラベル<BR>
     * 　@カラム番号： 49<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 50<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１７ラベル<BR>
     * 　@カラム番号： 50<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 51<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１７ラベル<BR>
     * 　@カラム番号： 51<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 52<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１８ラベル<BR>
     * 　@カラム番号： 52<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 53<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１８ラベル<BR>
     * 　@カラム番号： 53<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 54<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード１９ラベル<BR>
     * 　@カラム番号： 54<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 55<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量１９ラベル<BR>
     * 　@カラム番号： 55<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 56<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２０ラベル<BR>
     * 　@カラム番号： 56<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 57<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２０ラベル <BR>
     * 　@カラム番号： 57<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 58<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２１ラベル<BR>
     * 　@カラム番号： 58<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 59<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２１ラベル<BR>
     * 　@カラム番号： 59<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 60<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２２ラベル<BR>
     * 　@カラム番号： 60<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 61<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２２ラベル<BR>
     * 　@カラム番号： 61<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 62<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２３ラベル<BR>
     * 　@カラム番号： 62<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 63<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２３ラベル<BR>
     * 　@カラム番号： 63<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 64<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２４ラベル<BR>
     * 　@カラム番号： 64<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 65<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２４ラベル<BR>
     * 　@カラム番号： 65<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 66<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.商品コード２５ラベル<BR>
     * 　@カラム番号： 66<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * - index = 67<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@管理者FX口座開設申込ダウンロードCSV.発注上限数量２５ラベル<BR>
     * 　@カラム番号： 67<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * @@roseuid 43D5BF5802A3
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        List l_lisLabels = new ArrayList();
        
        //管理者FX口座開設申込ダウンロードCSV.追加変更区分ラベル
        l_lisLabels.add(this.addModifiedDivLabel);
        //管理者FX口座開設申込ダウンロードCSV.利用者コードラベル
        l_lisLabels.add(this.userCodeLabel);
        //管理者FX口座開設申込ダウンロードCSV.利用者名ラベル
        l_lisLabels.add(this.userlNameLabel);
        //管理者FX口座開設申込ダウンロードCSV.ログインIDラベル
        l_lisLabels.add(this.loginIdLabel);
        //管理者FX口座開設申込ダウンロードCSV.ログインパスワードラベル
        l_lisLabels.add(this.loginPasswordLabel);
        //管理者FX口座開設申込ダウンロードCSV.発注パスワードラベル
        l_lisLabels.add(this.orderPasswordLabel);
        //管理者FX口座開設申込ダウンロードCSV.メールアドレス１ラベル
        l_lisLabels.add(this.mailAddress1Label);
        //管理者FX口座開設申込ダウンロードCSV.メールアドレス２ラベル
        l_lisLabels.add(this.mailAddress2Label);
        //管理者FX口座開設申込ダウンロードCSV.自己受託区分ラベル
        l_lisLabels.add(this.selfTrustDivLabel);
        //管理者FX口座開設申込ダウンロードCSV.利用者属性ラベル
        l_lisLabels.add(this.userAttributeLabel);
        //管理者FX口座開設申込ダウンロードCSV.決済方法@ラベル
        l_lisLabels.add(this.transferMethodLabel);
        //管理者FX口座開設申込ダウンロードCSV.手数料区分ラベル
        l_lisLabels.add(this.commissionDivLabel);
        //管理者FX口座開設申込ダウンロードCSV.ロスカット区分ラベル
        l_lisLabels.add(this.lossCutDivLabel);
        //管理者FX口座開設申込ダウンロードCSV.取引可能区分ラベル 
        l_lisLabels.add(this.tradingDivLabel);
        //管理者FX口座開設申込ダウンロードCSV.電子交付承諾日ラベル
        l_lisLabels.add(this.reportAcceptDateLabel);
        //管理者FX口座開設申込ダウンロードCSV.取引説明書確認日ラベル
        l_lisLabels.add(this.tradeInstructionsConfirmDateLabel);
        //管理者FX口座開設申込ダウンロードCSV.約諾書番号ラベル
        l_lisLabels.add(this.contractCodeLabel);
        //管理者FX口座開設申込ダウンロードCSV.備考ラベル
        l_lisLabels.add(this.remarkLabel);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１ラベル
        l_lisLabels.add(this.productCode1Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１ラベル
        l_lisLabels.add(this.orderQuantityUpper1Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２ラベル
        l_lisLabels.add(this.productCode2Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２ラベル
        l_lisLabels.add(this.orderQuantityUpper2Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード３ラベル
        l_lisLabels.add(this.productCode3Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量３ラベル
        l_lisLabels.add(this.orderQuantityUpper3Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード４ラベル
        l_lisLabels.add(this.productCode4Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量４ラベル
        l_lisLabels.add(this.orderQuantityUpper4Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード５ラベル
        l_lisLabels.add(this.productCode5Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量５ラベル
        l_lisLabels.add(this.orderQuantityUpper5Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード６ラベル
        l_lisLabels.add(this.productCode6Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量６ラベル
        l_lisLabels.add(this.orderQuantityUpper6Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード７ラベル
        l_lisLabels.add(this.productCode7Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量７ラベル
        l_lisLabels.add(this.orderQuantityUpper7Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード８ラベル
        l_lisLabels.add(this.productCode8Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量８ラベル
        l_lisLabels.add(this.orderQuantityUpper8Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード９ラベル
        l_lisLabels.add(this.productCode9Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量９ラベル
        l_lisLabels.add(this.orderQuantityUpper9Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１０ラベル
        l_lisLabels.add(this.productCode10Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１０ラベル
        l_lisLabels.add(this.orderQuantityUpper10Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１１ラベル
        l_lisLabels.add(this.productCode11Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１１ラベル
        l_lisLabels.add(this.orderQuantityUpper11Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１２ラベル
        l_lisLabels.add(this.productCode12Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１２ラベル
        l_lisLabels.add(this.orderQuantityUpper12Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１３ラベル
        l_lisLabels.add(this.productCode13Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１３ラベル
        l_lisLabels.add(this.orderQuantityUpper13Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１４ラベル
        l_lisLabels.add(this.productCode14Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１４ラベル
        l_lisLabels.add(this.orderQuantityUpper14Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１５ラベル
        l_lisLabels.add(this.productCode15Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１５ラベル
        l_lisLabels.add(this.orderQuantityUpper15Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１６ラベル
        l_lisLabels.add(this.productCode16Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１６ラベル
        l_lisLabels.add(this.orderQuantityUpper16Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１７ラベル
        l_lisLabels.add(this.productCode17Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１７ラベル
        l_lisLabels.add(this.orderQuantityUpper17Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１８ラベル
        l_lisLabels.add(this.productCode18Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１８ラベル
        l_lisLabels.add(this.orderQuantityUpper18Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード１９ラベル
        l_lisLabels.add(this.productCode19Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量１９ラベル
        l_lisLabels.add(this.orderQuantityUpper19Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２０ラベル
        l_lisLabels.add(this.productCode20Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２０ラベル
        l_lisLabels.add(this.orderQuantityUpper20Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２１ラベル
        l_lisLabels.add(this.productCode21Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２１ラベル
        l_lisLabels.add(this.orderQuantityUpper21Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２２ラベル
        l_lisLabels.add(this.productCode22Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２２ラベル
        l_lisLabels.add(this.orderQuantityUpper22Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２３ラベル
        l_lisLabels.add(this.productCode23Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２３ラベル
        l_lisLabels.add(this.orderQuantityUpper23Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２４ラベル
        l_lisLabels.add(this.productCode24Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２４ラベル
        l_lisLabels.add(this.orderQuantityUpper24Label);
        //管理者FX口座開設申込ダウンロードCSV.商品コード２５ラベル
        l_lisLabels.add(this.productCode25Label);
        //管理者FX口座開設申込ダウンロードCSV.発注上限数量２５ラベル
        l_lisLabels.add(this.orderQuantityUpper25Label);
        
        //以下の通りCSVカラムモデルの配列を生成し
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[l_lisLabels.size()];

        String l_strColumn = null;
        DateFormat l_dateFormat = null;
        int l_intModel = 0;

        for (int i = 0; i < l_lisLabels.size(); i++)
        {
            l_strColumn = (String) l_lisLabels.get(i);
            l_dateFormat = null;
            l_intModel = WEB3GentradeCsvColumnModel.STRINGTYPE;

            if (this.reportAcceptDateLabel.equals(l_strColumn)
                || this.tradeInstructionsConfirmDateLabel.equals(l_strColumn))
            {
                l_intModel = WEB3GentradeCsvColumnModel.DATETYPE;
                l_dateFormat = new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            }

            l_columnModel[i] = 
                new WEB3GentradeCsvColumnModel(
                    l_strColumn,
                    i,
                    l_intModel,
                    l_dateFormat);
        }
        
        //setカラムヘッダ()にてインスタンスにセットする。
        this.setColumnHeader(l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set追加変更区分)<BR>
     * 追加変更区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.追加変更区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "I"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DC26012C
     */
    public void setAddModifiedDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setAddModifiedDiv(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.addModifiedDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ADDMODIFIEDDIV_I);
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (set利用者コード)<BR>
     * 利用者コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.利用者コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strValue - (値)<BR>
     * CSVにセットする値<BR>
     * @@roseuid 43D5DE45016A
     */
    public void setUserCode(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setUserCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.userCodeLabel), l_strValue);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set利用者名)<BR>
     * 利用者名をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.利用者名ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strValue - (値)<BR>
     * CSVにセットする値<BR>
     * @@roseuid 43D5DE8D0032
     */
    public void setUserName(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setUserName(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.userlNameLabel), l_strValue);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setログインID)<BR>
     * ログインIDをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.ログインIDラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strValue - (値)<BR>
     * CSVにセットする値<BR>
     * @@roseuid 43D5DEA80284
     */
    public void setLoginId(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setLoginId(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.loginIdLabel), l_strValue);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (setログインパスワード)<BR>
     * ログインパスワードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.ログインパスワードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DECC013C
     */
    public void setLoginPassword(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setLoginPassword(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.loginPasswordLabel), null);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注パスワード)<BR>
     * 発注パスワードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注パスワードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DEFA0051
     */
    public void setOrderPassword(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderPassword(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.orderPasswordLabel), null);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (setメールアドレス１)<BR>
     * メールアドレス１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.メールアドレス１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strValue - (値)<BR>
     * CSVにセットする値<BR>
     * @@roseuid 43D5DF1D0321
     */
    public void setMailAddress1(int l_intLineNumber, String l_strValue) 
    {
        final String STR_METHOD_NAME = " setMailAddress1(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.mailAddress1Label), l_strValue);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (setメールアドレス２)<BR>
     * メールアドレス２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.メールアドレス２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF3901E8
     */
    public void setMailAddress2(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setMailAddress2(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.mailAddress2Label), null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set自己受託区分)<BR>
     * 自己受託区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.自己受託区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "A"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF590061
     */
    public void setSelfTrustDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setSelfTrustDiv(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.selfTrustDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.SELFTRUSTDIV);
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set利用者属性)<BR>
     * 利用者属性をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.利用者属性ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "A"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setUserAttribute(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setUserAttribute(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.userAttributeLabel),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.USERATTRIBUTE);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set決済方法@)<BR>
     * 決済方法@をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.決済方法@ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "2"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */

    public void setTransferMethod(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setTransferMethod(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.transferMethodLabel),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.TRANSFERMETHOD);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (setロスカット区分)<BR>
     * ロスカット区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.ロスカット区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "01"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF850207
     */
    public void setLossCutDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setLossCutDiv(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.lossCutDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.LOSSCUTDIV);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set手数料区分)<BR>
     * 手数料区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.手数料区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値：  "01"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF87012C
     */
    public void setCommissionDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setCommissionDiv(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.commissionDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.COMMISSIONDIV);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set取引可能区分)<BR>
     * 取引可能区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.取引可能区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "1"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF8800DE
     */
    public void setTradingDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setTradingDiv(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.tradingDivLabel), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.TRADINGDIV);
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set電子交付承諾日)<BR>
     * 電子交付承諾日をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.電子交付承諾日ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： 引数.値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datReportAcceptDate - (値)<BR>
     * 値<BR>
     */
    public void setReportAcceptDate(int l_intLineNumber, Date l_datReportAcceptDate)
    {
        final String STR_METHOD_NAME = " setReportAcceptDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.reportAcceptDateLabel), l_datReportAcceptDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set取引説明書確認日)<BR>
     * 取引説明書確認日をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.取引説明書確認日ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： 引数.値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datConfirmDate - (値)<BR>
     * 値<BR>
     */
    public void setTradeInstructionsConfirmDate(int l_intLineNumber, Date l_datConfirmDate)
    {
        final String STR_METHOD_NAME = "setTradeInstructionsConfirmDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber,
            this.getColumnModel(this.tradeInstructionsConfirmDateLabel),
            l_datConfirmDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set約諾書番号)<BR>
     * 約諾書番号をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.約諾書番号ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */

    public void setContractCode(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setContractCode(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.contractCodeLabel),
            null);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set備考)<BR>
     * 備考をセットする。 <BR>
     *  <BR>
     * １）カラムモデル取得 <BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.備考ラベル <BR>
     *  <BR>
     * ２）値セット <BR>
     *    this.set項目値()にて、値をセットする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    行番号： 引数.行番号 <BR>
     *    カラム： １）で取得したカラムモデル <BR>
     *    値： 引数.値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strRemark - (備考)<BR>
     * @@roseuid 43D5DF8803DB
     */
    public void setRemark(int l_intLineNumber, String l_strRemark) 
    {
        final String STR_METHOD_NAME = " setRemark(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(this.remarkLabel), l_strRemark);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set商品コード１)<BR>
     * 商品コード１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "USD/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF890264
     */
    public void setProductCode1(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode1(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode1Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE1);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量１)<BR>
     * 発注上限数量１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF8A0070
     */
    public void setOrderQuantityUpper1(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper1(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper1Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set商品コード２)<BR>
     * 商品コード２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "EUR/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF8A0264
     */
    public void setProductCode2(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode2(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode2Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE2);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量２)<BR>
     * 発注上限数量２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF8B0051
     */
    public void setOrderQuantityUpper2(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper2(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper2Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);
 
    }
    
    /**
     * (set商品コード３)<BR>
     * 商品コード３をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード３ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "GBP/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF8B037E
     */
    public void setProductCode3(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode3(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode3Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE3);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量３)<BR>
     * 発注上限数量３をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量３ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DF8C0032
     */
    public void setOrderQuantityUpper3(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper3(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper3Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set商品コード４)<BR>
     * 商品コード４をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード４ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "AUD/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5DFFE018A
     */
    public void setProductCode4(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode4(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode4Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE4);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量４)<BR>
     * 発注上限数量４をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量４ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E00501F7
     */
    public void setOrderQuantityUpper4(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper4(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper4Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set商品コード５)<BR>
     * 商品コード５をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード５ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "CHF/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E0060216
     */
    public void setProductCode5(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode5(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode5Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE5);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量５)<BR>
     * 発注上限数量５をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量５ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値："300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E007013C
     */
    public void setOrderQuantityUpper5(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper5(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper5Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set商品コード６)<BR>
     * 商品コード６をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード６ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "CAD/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E007037E
     */
    public void setProductCode6(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode6(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode6Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE6);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量６)<BR>
     * 発注上限数量６をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量６ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E008015B
     */
    public void setOrderQuantityUpper6(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper6(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper6Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set商品コード７)<BR>
     * 商品コード７をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード７ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値：  "NZD/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E0090032
     */
    public void setProductCode7(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setProductCode7(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode7Label), WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE7);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set発注上限数量７)<BR>
     * 発注上限数量７をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量７ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数.行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 43D5E03A0032
     */
    public void setOrderQuantityUpper7(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper7(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper7Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード８)<BR>
     * 商品コード８をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード８ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "ZAR/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode8(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode8(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode8Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE8);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量８)<BR>
     * 発注上限数量８をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量８ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper8(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper8(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper8Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード９)<BR>
     * 商品コード９をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード９ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "TRY/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */

    public void setProductCode9(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode9(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode9Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE9);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量９)<BR>
     * 発注上限数量９をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量９ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper9(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper9(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper9Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１０)<BR>
     * 商品コード１０をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１０ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "NOK/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode10(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode10(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode10Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE10);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１０)<BR>
     * 発注上限数量１０をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１０ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper10(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper10(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper10Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１１)<BR>
     * 商品コード１１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "HKD/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode11(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode11(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode11Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE11);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１１)<BR>
     * 発注上限数量１１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper11(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper11(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper11Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１２)<BR>
     * 商品コード１２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "SEK/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode12(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode12(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode12Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE12);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１２)<BR>
     * 発注上限数量１２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper12(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper12(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper12Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１３)<BR>
     * 商品コード１３をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１３ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "MXN/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode13(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode13(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode13Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE13);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１３)<BR>
     * 発注上限数量１３をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１３ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper13(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper13(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper13Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１４)<BR>
     * 商品コード１４をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１４ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "PLN/JPY"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode14(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode14(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode14Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE14);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１４)<BR>
     * 発注上限数量１４をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１４ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper14(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper14(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper14Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１５)<BR>
     * 商品コード１５をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１５ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "EUR/USD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode15(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode15(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode15Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE15);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１５)<BR>
     * 発注上限数量１５をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１５ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper15(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper15(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper15Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１６)<BR>
     * 商品コード１６をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１６ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "GBP/USD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode16(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode16(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode16Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE16);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１６)<BR>
     * 発注上限数量１６をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１６ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper16(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper16(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper16Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１７)<BR>
     * 商品コード１７をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１７ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "GBP/CHF"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode17(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode17(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode17Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE17);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１７)<BR>
     * 発注上限数量１７をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１７ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper17(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper17(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper17Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１８)<BR>
     * 商品コード１８をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１８ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "USD/CHF"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode18(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode18(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode18Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE18);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１８)<BR>
     * 発注上限数量１８をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１８ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper18(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper18(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper18Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード１９)<BR>
     * 商品コード１９をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード１９ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "USD/CAD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode19(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode19(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode19Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE19);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量１９)<BR>
     * 発注上限数量１９をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量１９ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper19(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper19(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper19Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード２０)<BR>
     * 商品コード２０をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２０ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "AUD/USD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode20(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode20(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode20Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE20);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量２０)<BR>
     * 発注上限数量２０をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２０ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper20(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper20(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper20Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード２１)<BR>
     * 商品コード２１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "EUR/CHF"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode21(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode21(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode21Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE21);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量２１)<BR>
     * 発注上限数量２１をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２１ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper21(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper21(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper21Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード２２)<BR>
     * 商品コード２２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "EUR/GBP"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode22(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode22(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode22Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE22);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量２２)<BR>
     * 発注上限数量２２をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２２ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper22(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper22(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper22Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード２３)<BR>
     * 商品コード２３をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２３ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "NZD/USD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode23(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode23(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode23Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE23);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量２３)<BR>
     * 発注上限数量２３をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２３ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper23(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper23(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper23Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード２４)<BR>
     * 商品コード２４をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２４ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "EUR/AUD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode24(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode24(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode24Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE24);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量２４)<BR>
     * 発注上限数量２４をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２４ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper24(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper24(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper24Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set商品コード２５)<BR>
     * 商品コード２５をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.商品コード２５ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "GBP/AUD"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setProductCode25(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setProductCode25(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.productCode25Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.PRODUCTCODE25);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (set発注上限数量２５)<BR>
     * 発注上限数量２５をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@項目ラベル： 管理者FX口座開設申込ダウンロードCSV.発注上限数量２５ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@行番号： 引数.行番号<BR>
     * 　@　@カラム： １）で取得したカラムモデル<BR>
     * 　@　@値： "300"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setOrderQuantityUpper25(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setOrderQuantityUpper25(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(
            l_intLineNumber,
            this.getColumnModel(this.orderQuantityUpper25Label),
            WEB3AdminFXAccOpenApplyDownloadCsvColumnDef.ORDERQUANTITYUPPER);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (isカラムヘッダ行出力)<BR>
     * （オーバーライドメソッド） <BR>
     * CSVファ@イルにカラムヘッダ行の出力要否を判定する。 <BR>
     * <BR>
     * falseを返却する。 <BR>
     * @@return boolean
     * @@roseuid 43D5F38F01B9
     */
    public boolean isColumnHeadOutput() 
    {
        return false;
    }
    
    /**
     * (getCSVファ@イル行)<BR>
     * （オーバーライドメソッド） <BR>
     * CSVファ@イルに出力するデータを、行毎の配列にて返却する。 <BR>
     * <BR>
     * １）スーパークラスのgetCSVファ@イル行()メソッドをコールする。 <BR>
     * <BR>
     * ２）取得した配列から空行、ブランクのみの行を除外する。 <BR>
     * <BR>
     * ３）２）の結果を返却する。 <BR>
     * @@return String[]
     * @@roseuid 43D5F8D602E1
     */
    public String[] getCsvFileLines() 
    {
        final String STR_METHOD_NAME = " getCsvFileLines()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのgetCSVファ@イル行()メソッドをコールする。
        String[] l_strCvsFileLines = super.getCsvFileLines();
        
        //２）取得した配列から空行、ブランクのみの行を除外する。
        List l_lisResults = new ArrayList();
        
        for (int i = 0; i < l_strCvsFileLines.length; i++)
        {
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strCvsFileLines[i]))
            {
                l_lisResults.add(l_strCvsFileLines[i]);
            }
        }
            
        //３）２）の結果を返却する。
        String[] l_strResults = new String[l_lisResults.size()];
        l_lisResults.toArray(l_strResults);
        
        log.exiting(STR_METHOD_NAME);
        return l_strResults;
    }
}
@
