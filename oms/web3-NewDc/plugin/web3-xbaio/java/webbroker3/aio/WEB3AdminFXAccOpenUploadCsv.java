head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccOpenUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設アップロードCSV(WEB3AdminFXAccOpenUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 黄浩澎 (中訊) 新規作成
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル503
                 : 2006/02/27 情野（SRA） 仕様変更・モデル505
                 : 2006/03/01 情野（SRA） 受入障害U02771,2774対応
                 : 2006/03/01 情野（SRA） 受入障害U02775対応
                 : 2006/03/02 黄浩澎 (中訊) 仕様変更・モデル486
Revesion History : 2008/09/12 劉仁和 (中訊) 仕様変更・モデル936~959,モデル979,980,982,986
*/

package webbroker3.aio;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者FX口座開設アップロードCSV)<BR>
 * 管理者FX口座開設アップロードCSVクラス<BR>
 * 
 * @@author 黄浩澎(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCsv extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadCsv.class);
    
    /**
     * (アップロードファ@イルID)<BR>
     * (アップロードファ@イルID)<BR>
     */
    private String UPLOAD_FILEID = "FX口座開設アップロード";
    
    /**
     * (利用者コードラベル)<BR>
     * 利用者コードラベル<BR>
     */
    public static  String USER_CODE_LABEL = "利用者コード";
    
    /**
     * (利用者名ラベル)<BR>
     * 利用者名ラベル<BR>
     */
    public static  String USER_NAME_LABEL = "利用者名";
    
    /**
     * (ログインIDラベル)<BR>
     * ログインIDラベル<BR>
     */
    public static  String LOGINID_LABEL = "ログインID";
    
    /**
     * (ログインパスワードラベル)<BR>
     * ログインパスワードラベル<BR>
     */
    public static  String LOGIN_PASSWORD_LABEL = "ログインパスワード";
    
    /**
     * (発注パスワードラベル)<BR>
     * 発注パスワードラベル<BR>
     */
    public static  String ORDER_PASSWORD_LABEL = "発注パスワード";
    
    /**
     * (メールアドレス１ラベル)<BR>
     * メールアドレス１ラベル<BR>
     */
    public static  String MAILADDRESS1_LABEL = "メールアドレス１";
    
    /**
     * (メールアドレス２ラベル)<BR>
     * メールアドレス２ラベル<BR>
     */
    public static  String MAILADDRESS2_LABEL = "メールアドレス２";
    
    /**
     * (自己受託区分ラベル)<BR>
     * 自己受託区分ラベル<BR>
     */
    public static  String SELF_TRUSTDIV_LABEL = "自己受託区分";

    /**
     * (利用者属性ラベル)<BR>
     * 利用者属性ラベル<BR>
     */
    public static String USER_ATTRIBUTE_LABEL = "利用者属性";

    /**
     * (決済方法@ラベル)<BR>
     * 決済方法@ラベル<BR>
     */
    public static String SETTLEMENT_METHOD_LABEL = "決済方法@";

    /**
     * (ロスカット区分ラベル)<BR>
     * ロスカット区分ラベル<BR>
     */
    public static  String LOSSCUTDIV_LABEL = "ロスカット区分";
    
    /**
     * (手数料区分ラベル)<BR>
     * 手数料区分ラベル<BR>
     */
    public static  String COMMISSIONDIV_LABEL = "手数料区分";
    
    /**
     * (取引可能区分ラベル)<BR>
     * 取引可能区分ラベル<BR>
     */
    public static  String TRADINGDIV_LABEL = "取引可能区分";

    /**
     * (電子交付承諾日ラベル)<BR>
     * 電子交付承諾日ラベル<BR>
     */
    public static String ELECTRON_GRANT_PERMISSION_DAY_LABEL = "電子交付承諾日";

    /**
     * (取引説明書確認日ラベル)<BR>
     * 取引説明書確認日ラベル<BR>
     */
    public static String TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL = "取引説明書確認日";

    /**
     * (約諾書番号ラベル)<BR>
     * 約諾書番号ラベル<BR>
     */
    public static String AGREEMENT_BOOK_NUMBER_LABEL = "約諾書番号";

    /**
     * (備考ラベル)<BR>
     * 備考ラベル<BR>
     */
    public static  String REMARK_LABEL = "備考";
    
    /**
     * (商品コード１ラベル)<BR>
     * 商品コード１ラベル<BR>
     */
    public static  String PRODUCTCODE1_LABEL = "商品コード１";
    
    /**
     * (発注上限数量１ラベル)<BR>
     * 発注上限数量１ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER1_LABEL = "発注上限数量１";
    
    /**
     * (商品コード２ラベル)<BR>
     * 商品コード２ラベル<BR>
     */
    public static  String PRODUCTCODE2_LABEL = "商品コード２";
    
    /**
     * (発注上限数量２ラベル)<BR>
     * 発注上限数量２ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER2_LABEL = "発注上限数量２";
    
    /**
     * (商品コード３ラベル)<BR>
     * 商品コード３ラベル<BR>
     */
    public static  String PRODUCTCODE3_LABEL = "商品コード３";
    
    /**
     * (発注上限数量３ラベル)<BR>
     * 発注上限数量３ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER3_LABEL = "発注上限数量３";
    
    /**
     * (商品コード４ラベル)<BR>
     * 商品コード４ラベル<BR>
     */
    public static  String PRODUCTCODE4_LABEL = "商品コード４";
    
    /**
     * (発注上限数量４ラベル)<BR>
     * 発注上限数量４ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER4_LABEL = "発注上限数量４";
    
    /**
     * (商品コード５ラベル)<BR>
     * 商品コード５ラベル<BR>
     */
    public static  String PRODUCTCODE5_LABEL = "商品コード５";
    
    /**
     * (発注上限数量５ラベル)<BR>
     * 発注上限数量５ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER5_LABEL = "発注上限数量５";
    
    /**
     * (商品コード６ラベル)<BR>
     * 商品コード６ラベル<BR>
     */
    public static  String PRODUCTCODE6_LABEL = "商品コード６";
    
    /**
     * (発注上限数量６ラベル)<BR>
     * 発注上限数量６ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER6_LABEL = "発注上限数量６";
    
    /**
     * (商品コード７ラベル)<BR>
     * 商品コード７ラベル<BR>
     */
    public static  String PRODUCTCODE7_LABEL = "商品コード７";
    
    /**
     * (発注上限数量７ラベル)<BR>
     * 発注上限数量７ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER7_LABEL = "発注上限数量７";

    /**
     * (商品コード８ラベル)<BR>
     * 商品コード８ラベル<BR>
     */
    public static  String PRODUCTCODE8_LABEL = "商品コード８";

    /**
     * (発注上限数量８ラベル)<BR>
     * 発注上限数量８ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER8_LABEL = "発注上限数量８";

    /**
     * (商品コード９ラベル)<BR>
     * 商品コード９ラベル<BR>
     */
    public static  String PRODUCTCODE9_LABEL = "商品コード９";

    /**
     * (発注上限数量９ラベル)<BR>
     * 発注上限数量９ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER9_LABEL = "発注上限数量９";

    /**
     * (商品コード１０ラベル)<BR>
     * 商品コード１０ラベル<BR>
     */
    public static  String PRODUCTCODE10_LABEL = "商品コード１０";

    /**
     * (発注上限数量１０ラベル)<BR>
     * 発注上限数量１０ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER10_LABEL = "発注上限数量１０";

    /**
     * (商品コード１１ラベル)<BR>
     * 商品コード１１ラベル<BR>
     */
    public static  String PRODUCTCODE11_LABEL = "商品コード１１";

    /**
     * (発注上限数量１１ラベル)<BR>
     * 発注上限数量１１ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER11_LABEL = "発注上限数量１１";

    /**
     * (商品コード１２ラベル)<BR>
     * 商品コード１２ラベル<BR>
     */
    public static  String PRODUCTCODE12_LABEL = "商品コード１２";

    /**
     * (発注上限数量１２ラベル)<BR>
     * 発注上限数量１２ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER12_LABEL = "発注上限数量１２";

    /**
     * (商品コード１３ラベル)<BR>
     * 商品コード１３ラベル<BR>
     */
    public static  String PRODUCTCODE13_LABEL = "商品コード１３";

    /**
     * (発注上限数量１３ラベル)<BR>
     * 発注上限数量１３ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER13_LABEL = "発注上限数量１３";

    /**
     * (商品コード１４ラベル)<BR>
     * 商品コード１４ラベル<BR>
     */
    public static  String PRODUCTCODE14_LABEL = "商品コード１４";

    /**
     * (発注上限数量１４ラベル)<BR>
     * 発注上限数量１４ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER14_LABEL = "発注上限数量１４";

    /**
     * (商品コード１５ラベル)<BR>
     * 商品コード１５ラベル<BR>
     */
    public static  String PRODUCTCODE15_LABEL = "商品コード１５";

    /**
     * (発注上限数量１５ラベル)<BR>
     * 発注上限数量１５ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER15_LABEL = "発注上限数量１５";

    /**
     * (商品コード１６ラベル)<BR>
     * 商品コード１６ラベル<BR>
     */
    public static  String PRODUCTCODE16_LABEL = "商品コード１６";

    /**
     * (発注上限数量１６ラベル)<BR>
     * 発注上限数量１６ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER16_LABEL = "発注上限数量１６";

    /**
     * (商品コード１７ラベル)<BR>
     * 商品コード１７ラベル<BR>
     */
    public static  String PRODUCTCODE17_LABEL = "商品コード１７";

    /**
     * (発注上限数量１７ラベル)<BR>
     * 発注上限数量１７ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER17_LABEL = "発注上限数量１７";

    /**
     * (商品コード１８ラベル)<BR>
     * 商品コード１８ラベル<BR>
     */
    public static  String PRODUCTCODE18_LABEL = "商品コード１８";

    /**
     * (発注上限数量１８ラベル)<BR>
     * 発注上限数量１８ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER18_LABEL = "発注上限数量１８";

    /**
     * (商品コード１９ラベル)<BR>
     * 商品コード１９ラベル<BR>
     */
    public static  String PRODUCTCODE19_LABEL = "商品コード１９";

    /**
     * (発注上限数量１９ラベル)<BR>
     * 発注上限数量１９ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER19_LABEL = "発注上限数量１９";

    /**
     * (商品コード２０ラベル)<BR>
     * 商品コード２０ラベル<BR>
     */
    public static  String PRODUCTCODE20_LABEL = "商品コード２０";

    /**
     * (発注上限数量２０ラベル)<BR>
     * 発注上限数量２０ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER20_LABEL = "発注上限数量２０";

    /**
     * (商品コード２１ラベル)<BR>
     * 商品コード２１ラベル<BR>
     */
    public static  String PRODUCTCODE21_LABEL = "商品コード２１";

    /**
     * (発注上限数量２１ラベル)<BR>
     * 発注上限数量２１ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER21_LABEL = "発注上限数量２１";

    /**
     * (商品コード２２ラベル)<BR>
     * 商品コード２２ラベル<BR>
     */
    public static  String PRODUCTCODE22_LABEL = "商品コード２２";

    /**
     * (発注上限数量２２ラベル)<BR>
     * 発注上限数量２２ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER22_LABEL = "発注上限数量２２";

    /**
     * (商品コード２３ラベル)<BR>
     * 商品コード２３ラベル<BR>
     */
    public static  String PRODUCTCODE23_LABEL = "商品コード２３";

    /**
     * (発注上限数量２３ラベル)<BR>
     * 発注上限数量２３ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER23_LABEL = "発注上限数量２３";

    /**
     * (商品コード２４ラベル)<BR>
     * 商品コード２４ラベル<BR>
     */
    public static  String PRODUCTCODE24_LABEL = "商品コード２４";

    /**
     * (発注上限数量２４ラベル)<BR>
     * 発注上限数量２４ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER24_LABEL = "発注上限数量２４";

    /**
     * (商品コード２５ラベル)<BR>
     * 商品コード２５ラベル<BR>
     */
    public static  String PRODUCTCODE25_LABEL = "商品コード２５";

    /**
     * (発注上限数量２５ラベル)<BR>
     * 発注上限数量２５ラベル<BR>
     */
    public static  String ORDER_QUANTITY_UPPER25_LABEL = "発注上限数量２５";

    /**
     * (管理者FX口座開設アップロードCSV)<BR>
     * コンストラクタ <BR>
     * ※　@アップロード中止の場合に使用する。 <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。 <BR>
     * @@param l_lngUploadId - (アップロードID)<BR>
     * @@roseuid 43E2DD230241
     */
    public WEB3AdminFXAccOpenUploadCsv(long l_lngUploadId) 
    {
        super.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (管理者FX口座開設アップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * this.createカラムヘッダ()をコールする。 <BR>
     * @@roseuid 43E05A3D029D
     */
    public WEB3AdminFXAccOpenUploadCsv() 
    {
        this.createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()に<BR>
     * てインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.利用者コードラベル<BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.利用者名ラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.ログインIDラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.ログインパスワードラベル<BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注パスワードラベル<BR>
     * 　@カラム番号：4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.メールアドレス１ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index =  6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.メールアドレス２ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.自己受託区分ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.利用者属性ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.決済方法@ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.手数料区分ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.ロスカット区分ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.取引可能区分ラベル<BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.電子交付承諾日ラベル<BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 14<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.取引説明書確認日ラベル<BR>
     * 　@カラム番号： 14<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 15 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.約諾書番号ラベル<BR>
     * 　@カラム番号： 15 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 16 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.備考ラベル<BR>
     * 　@カラム番号： 16 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 17<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１ラベル<BR>
     * 　@カラム番号： 17<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 18<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１ラベル<BR>
     * 　@カラム番号： 18<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 19<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２ラベル<BR>
     * 　@カラム番号： 19<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 20<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２ラベル<BR>
     * 　@カラム番号： 20<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 21<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード３ラベル<BR>
     * 　@カラム番号： 21<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 22<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量３ラベル<BR>
     * 　@カラム番号： 22<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 23<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード４ラベル<BR>
     * 　@カラム番号： 23<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 24<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量４ラベル<BR>
     * 　@カラム番号： 24<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 25<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード５ラベル<BR>
     * 　@カラム番号： 25<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 26<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量５ラベル<BR>
     * 　@カラム番号： 26<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 27<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード６ラベル<BR>
     * 　@カラム番号： 27<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 28<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量６ラベル<BR>
     * 　@カラム番号： 28<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 29<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード７ラベル<BR>
     * 　@カラム番号： 29<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 30<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量７ラベル<BR>
     * 　@カラム番号： 30<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 31<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード８ラベル<BR>
     * 　@カラム番号： 31<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 32<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量８ラベル<BR>
     * 　@カラム番号： 32<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 33<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード９ラベル<BR>
     * 　@カラム番号： 33<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 34<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量９ラベル<BR>
     * 　@カラム番号： 34<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>        
     * <BR>
     * －　@index = 35<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１０ラベル<BR>
     * 　@カラム番号： 35<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 36<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１０ラベル<BR>
     * 　@カラム番号： 36<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>  
     * <BR>
     * －　@index = 37<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１１ラベル<BR>
     * 　@カラム番号： 37<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 38<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１１ラベル<BR>
     * 　@カラム番号： 38<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 39<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１２ラベル<BR>
     * 　@カラム番号： 39<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 40<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１２ラベル<BR>
     * 　@カラム番号： 40<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 41<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１３ラベル<BR>
     * 　@カラム番号： 41<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 42<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１３ラベル<BR>
     * 　@カラム番号： 42<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR> 
     * <BR>
     * －　@index = 43<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１４ラベル<BR>
     * 　@カラム番号： 43<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 44<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１４ラベル<BR>
     * 　@カラム番号： 44<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 45<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１５ラベル<BR>
     * 　@カラム番号： 45<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 46<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１５ラベル<BR>
     * 　@カラム番号： 46<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 47<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１６ラベル<BR>
     * 　@カラム番号： 47<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 48<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１６ラベル<BR>
     * 　@カラム番号： 48<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 49<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１７ラベル<BR>
     * 　@カラム番号： 49<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 50<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１７ラベル<BR>
     * 　@カラム番号： 50<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 51<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１８ラベル<BR>
     * 　@カラム番号： 51<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 52<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１８ラベル<BR>
     * 　@カラム番号： 52<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 53<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード１９ラベル<BR>
     * 　@カラム番号： 53<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 54<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量１９ラベル<BR>
     * 　@カラム番号： 54<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 55<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２０ラベル<BR>
     * 　@カラム番号： 55<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 56<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２０ラベル<BR>
     * 　@カラム番号： 56<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 57<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２１ラベル<BR>
     * 　@カラム番号： 57<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 58<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２１ラベル<BR>
     * 　@カラム番号： 58<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 59<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２２ラベル<BR>
     * 　@カラム番号： 59<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 60<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２２ラベル<BR>
     * 　@カラム番号： 60<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 61<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２３ラベル<BR>
     * 　@カラム番号： 61<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 62<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２３ラベル<BR>
     * 　@カラム番号： 62<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 63<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２４ラベル<BR>
     * 　@カラム番号： 63<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 64<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２４ラベル<BR>
     * 　@カラム番号： 64<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 65<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.商品コード２５ラベル<BR>
     * 　@カラム番号： 65<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * －　@index = 66<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@管理者FX口座開設アップロードCSV.発注上限数量２５ラベル<BR>
     * 　@カラム番号： 66<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@ null <BR>
     * <BR>
     * @@roseuid 43E0569A00E8
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        List l_lisLabels = new ArrayList();
        
        //管理者FX口座開設アップロードCSV.利用者コードラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_CODE_LABEL);
        //管理者FX口座開設アップロードCSV.利用者名ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_NAME_LABEL);
        //管理者FX口座開設アップロードCSV.ログインIDラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGINID_LABEL);
        //管理者FX口座開設アップロードCSV.ログインパスワードラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOGIN_PASSWORD_LABEL);
        //管理者FX口座開設アップロードCSV.発注パスワードラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_PASSWORD_LABEL);
        //管理者FX口座開設アップロードCSV.メールアドレス１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS1_LABEL);
        //管理者FX口座開設アップロードCSV.メールアドレス２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS2_LABEL);
        //管理者FX口座開設アップロードCSV.自己受託区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SELF_TRUSTDIV_LABEL);
        //管理者FX口座開設アップロードCSV.利用者属性ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.USER_ATTRIBUTE_LABEL);
        //管理者FX口座開設アップロードCSV.決済方法@ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.SETTLEMENT_METHOD_LABEL);
        //管理者FX口座開設アップロードCSV.手数料区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.COMMISSIONDIV_LABEL);
        //管理者FX口座開設アップロードCSV.ロスカット区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.LOSSCUTDIV_LABEL);
        //管理者FX口座開設アップロードCSV.取引可能区分ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADINGDIV_LABEL);
        //管理者FX口座開設アップロードCSV.電子交付承諾日ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ELECTRON_GRANT_PERMISSION_DAY_LABEL);
        //管理者FX口座開設アップロードCSV.取引説明書確認日ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL);
        //管理者FX口座開設アップロードCSV.約諾書番号ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.AGREEMENT_BOOK_NUMBER_LABEL);
        //管理者FX口座開設アップロードCSV.備考ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.REMARK_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE1_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER1_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE2_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER2_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE3_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER3_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE4_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER4_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE5_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER5_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE6_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER6_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE7_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER7_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE8_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER8_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE9_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER9_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE10_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER10_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE11_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER11_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE12_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER12_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE13_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER13_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE14_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER14_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE15_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER15_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE16_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１６ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER16_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE17_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１７ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER17_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE18_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１８ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER18_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード１９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE19_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量１９ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER19_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE20_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２０ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER20_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE21_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２１ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER21_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE22_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２２ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER22_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE23_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２３ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER23_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE24_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２４ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER24_LABEL);
        //管理者FX口座開設アップロードCSV.商品コード２５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE25_LABEL);
        //管理者FX口座開設アップロードCSV.発注上限数量２５ラベル
        l_lisLabels.add(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER25_LABEL);

        //CSVカラムモデルの配列を生成し
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[l_lisLabels.size()];

        for (int i = 0; i < l_lisLabels.size(); i++)
        {
            l_columnModel[i] = 
                new WEB3GentradeCsvColumnModel(
                    (String) l_lisLabels.get(i),
                    i,
                    WEB3GentradeCsvColumnModel.STRINGTYPE,
                    null);
        }
        
        //setカラムヘッダ()にてインスタンスにセットする。
        this.setColumnHeader(l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * this.アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに<BR>
     * 格納する文字列<BR>
     * @@return String
     * @@roseuid 43E0675E029D
     */
    public String getUploadFileId() 
    {
        return this.UPLOAD_FILEID;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.現金 を返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 43EFF41C0343
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.CASH;
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * １）　@利用者コードのチェック<BR>
     * 　@get利用者コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get利用者コード()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * 　@<BR>
     * 　@１－１）　@利用者コードが取得できない場合（get利用者コード() == null）、<BR>
     * 　@　@　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02366<BR>
     * <BR>
     * 　@１－２）　@半角数字以外が含まれる場合、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02367<BR>
     * <BR>
     * 　@１－３）　@文字数が8byteでない場合、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02368<BR>
     * <BR>
     * ２）　@利用者名チェック<BR>
     * 　@get利用者名()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get利用者名()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２－１）　@利用者名が取得できた場合<BR>
     * 　@　@２－１－１）　@文字数が120byteより大きい場合、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02369<BR>
     * <BR>
     * ３）　@ログインＩＤのチェック　@<BR>
     * 　@getログインＩＤ()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[getログインＩＤ()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３－１）　@ログインＩＤが取得できない場合（getログインＩＤ() == null）、<BR>
     * 　@　@　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02370<BR>
     * <BR>
     * 　@３－２）　@半角数字以外が含まれる場合、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02371<BR>
     * <BR>
     * 　@３－３）　@文字数が8byteでない場合、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02372<BR>
     * <BR>
     * ４）　@ログインパスワードのチェック<BR>
     * 　@getログインパスワード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[getログインパスワード()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * 　@<BR>
     * 　@４－１）　@ログインパスワードが取得できた場合<BR>
     * 　@　@４－１－１）　@文字数が8byte以上13byte以下でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_01915<BR>
     * <BR>
     * ５）　@発注パスワードのチェック<BR>
     * 　@get発注パスワード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注パスワード()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * 　@<BR>
     * 　@５－１）　@発注パスワードが取得できた場合<BR>
     * 　@　@５－１－１）　@文字数が8byte以上13byte以下でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_01915<BR>
     * <BR>
     * ６）　@メールアドレス１のチェック<BR>
     * 　@getメールアドレス１()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[getメールアドレス１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６－１）　@メールアドレス１が取得できた場合<BR>
     * 　@　@６－１－１）　@文字数が50byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_01693<BR>
     * <BR>
     * ７）　@メールアドレス２のチェック<BR>
     * 　@getメールアドレス２()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[getメールアドレス２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@７－１）　@メールアドレス２が取得できた場合<BR>
     * 　@　@７－１－１）　@文字数が50byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_01693<BR>
     * <BR>
     * ８）　@自己受託区分のチェック<BR>
     * 　@get自己受託区分()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get自己受託区分()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@８－１）　@文字数が1byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02373<BR>
     * <BR>
     * ９）　@利用者属性のチェック<BR>
     * 　@get利用者属性()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get利用者属性()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@９－１）　@利用者属性が取得できた場合 <BR>
     * 　@　@９－１－１） 文字数が1byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_03111<BR>　@　@　@　@
     * <BR>
     * １０）　@決済方法@のチェック<BR>
     * 　@get決済方法@()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get決済方法@()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１０－１）　@文字数が1byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_03112<BR>
     * <BR>
     * １１）　@手数料区分のチェック<BR>
     * 　@get手数料区分()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get手数料区分()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１１－１）　@文字数が2byteより大きい場合、例外をスローする。<BR>
     * <BR>
     * １２）　@ロスカット区分のチェック<BR>
     * 　@getロスカット区分()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[getロスカット区分()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１２－１）　@文字数が2byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02374<BR>
     * <BR>
     * １３）　@取引可能区分のチェック<BR>
     * 　@get取引可能区分()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get取引可能区分()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１３－１）　@文字数が1byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02376<BR>
     * <BR>
     * １４）　@電子交付承諾日のチェック<BR>
     * 　@get電子交付承諾日()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get電子交付承諾日()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１４－１）　@電子交付承諾日が取得できた場合。<BR>
     *    　@１４－１－１） 文字数が8byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_03113<BR>     
     * <BR>
     * １５）　@取引説明書確認日のチェック<BR>
     * 　@get取引説明書確認日()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get取引説明書確認日()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１５－１）　@取引説明書確認日が取得できた場合<BR>
     *    　@１５－１－１） 文字数が8byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_03114<BR> 
     * <BR>
     * １６）　@約諾書番号のチェック<BR>
     * 　@get約諾書番号()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get約諾書番号()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１６－１）　@文字数が10byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_03115<BR> 
     * <BR>
     * １７）　@備考のチェック<BR>
     * 　@get備考()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get備考()に指定する引数]<BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１７－１）　@備考が取得できない場合（get備考() == null）、<BR>
     * 　@　@　@　@　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02377<BR>
     * <BR>
     * 　@１７－２）　@文字数が9byteでない場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_00487<BR>
     * <BR>
     * １８）　@商品コード１のチェック<BR>
     * 　@get商品コード１()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１８－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * １９）　@発注上限数量１のチェック<BR>
     * 　@get発注上限数量１()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１９－１）　@発注上限数量１が取得できた場合<BR>
     * 　@　@１９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@１９－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ２０）　@商品コード２のチェック<BR>
     * 　@get商品コード２()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２０－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ２１）　@発注上限数量２のチェック<BR>
     * 　@get発注上限数量２()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２１－１）　@発注上限数量２が取得できた場合<BR>
     * 　@　@２１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@２１－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ２２）　@商品コード３のチェック<BR>
     * 　@get商品コード３()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード３()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２２－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ２３）　@発注上限数量３のチェック<BR>
     * 　@get発注上限数量３()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量３()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２３－１）　@発注上限数量３が取得できた場合<BR>
     * 　@　@２３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@２３－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ２４）　@商品コード４のチェック<BR>
     * 　@get商品コード４()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード４()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２４－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ２５）　@発注上限数量４のチェック<BR>
     * 　@get発注上限数量４()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量４()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２５－１）　@発注上限数量４が取得できた場合<BR>
     * 　@　@２５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@２５－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ２６）　@商品コード５のチェック<BR>
     * 　@get商品コード５()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード５()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２６－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ２７）　@発注上限数量５のチェック<BR>
     * 　@get発注上限数量５()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量５()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２７－１）　@発注上限数量５が取得できた場合<BR>
     * 　@　@２７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  : BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@２７－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ２８）　@商品コード６のチェック<BR>
     * 　@get商品コード６()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード６()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２８－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ２９）　@発注上限数量６のチェック<BR>
     * 　@get発注上限数量６()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量６()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２９－１）　@発注上限数量６が取得できた場合<BR>
     * 　@　@２９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@２９－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ３０）　@商品コード７のチェック<BR>
     * 　@get商品コード７()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード７()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３０－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ３１）　@発注上限数量７のチェック<BR>
     * 　@get発注上限数量７()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量７()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３１－１）　@発注上限数量７が取得できた場合<BR>
     * 　@　@３１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@３１－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ３２）　@商品コード８のチェック<BR>
     * 　@get商品コード８()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード８()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３２－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ３３）　@発注上限数量８のチェック<BR>
     * 　@get発注上限数量８()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量８()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３３－１）　@発注上限数量８が取得できた場合<BR>
     * 　@　@３３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@３３－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ３４）　@商品コード９のチェック<BR>
     * 　@get商品コード９()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード９()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３４－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ３５）　@発注上限数量９のチェック<BR>
     * 　@get発注上限数量９()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量９()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３５－１）　@発注上限数量９が取得できた場合<BR>
     * 　@　@３５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@３５－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ３６）　@商品コード１０のチェック<BR>
     * 　@get商品コード１０()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１０()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３６－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ３７）　@発注上限数量１０のチェック<BR>
     * 　@get発注上限数量１０()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１０()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３７－１）　@発注上限数量１０が取得できた場合<BR>
     * 　@　@３７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@３７－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ３８）　@商品コード１１のチェック<BR>
     * 　@get商品コード１１()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３８－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ３９）　@発注上限数量１１のチェック<BR>
     * 　@get発注上限数量１１()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３９－１）　@発注上限数量１１が取得できた場合<BR>
     * 　@　@３９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@３９－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ４０）　@商品コード１２のチェック<BR>
     * 　@get商品コード１２()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４０－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ４１）　@発注上限数量１２のチェック<BR>
     * 　@get発注上限数量１２()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４１－１）　@発注上限数量１２が取得できた場合<BR>
     * 　@　@４１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@４１－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ４２）　@商品コード１３のチェック<BR>
     * 　@get商品コード１３()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１３()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４２－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ４３）　@発注上限数量１３のチェック<BR>
     * 　@get発注上限数量１３()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１３()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４３－１）　@発注上限数量１３が取得できた場合<BR>
     * 　@　@４３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@４３－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ４４）　@商品コード１４のチェック<BR>
     * 　@get商品コード１４()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１４()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４４－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ４５）　@発注上限数量１４のチェック<BR>
     * 　@get発注上限数量１４()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１４()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４５－１）　@発注上限数量１４が取得できた場合<BR>
     * 　@　@４５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@４５－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ４６）　@商品コード１５のチェック<BR>
     * 　@get商品コード１５()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１５()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４６－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ４７）　@発注上限数量１５のチェック<BR>
     * 　@get発注上限数量１５()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１５()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４７－１）　@発注上限数量１５が取得できた場合<BR>
     * 　@　@４７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@４７－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ４８）　@商品コード１６のチェック<BR>
     * 　@get商品コード１６()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１６()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４８－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ４９）　@発注上限数量１６のチェック<BR>
     * 　@get発注上限数量１６()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１６()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@４９－１）　@発注上限数量１６が取得できた場合<BR>
     * 　@　@４９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@４９－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ５０）　@商品コード１７のチェック<BR>
     * 　@get商品コード１７()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１７()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５０－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ５１）　@発注上限数量１７のチェック<BR>
     * 　@get発注上限数量１７()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１７()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５１－１）　@発注上限数量１７が取得できた場合<BR>
     * 　@　@５１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@５１－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ５２）　@商品コード１８のチェック<BR>
     * 　@get商品コード１８()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１８()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５２－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ５３）　@発注上限数量１８のチェック<BR>
     * 　@get発注上限数量１８()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１８()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５３－１）　@発注上限数量１８が取得できた場合<BR>
     * 　@　@５３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@５３－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ５４）　@商品コード１９のチェック<BR>
     * 　@get商品コード１９()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード１９()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５４－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ５５）　@発注上限数量１９のチェック<BR>
     * 　@get発注上限数量１９()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量１９()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５５－１）　@発注上限数量１９が取得できた場合<BR>
     * 　@　@５５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@５５－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ５６）　@商品コード２０のチェック<BR>
     * 　@get商品コード２０()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード２０()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５６－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ５７）　@発注上限数量２０のチェック<BR>
     * 　@get発注上限数量２０()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２０()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５７－１）　@発注上限数量２０が取得できた場合<BR>
     * 　@　@５７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@５７－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ５８）　@商品コード２１のチェック<BR>
     * 　@get商品コード２１()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get商品コード２１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５８－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ５９）　@発注上限数量２１のチェック<BR>
     * 　@get発注上限数量２１()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２１()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５９－１）　@発注上限数量２１が取得できた場合<BR>
     * 　@　@５９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@５９－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ６０）　@商品コード２２のチェック<BR>
     * 　@get商品コード２２()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード２２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６０－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ６１）　@発注上限数量２２のチェック<BR>
     * 　@get発注上限数量２２()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２２()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６１－１）　@発注上限数量２２が取得できた場合<BR>
     * 　@　@６１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@６１－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ６２）　@商品コード２３のチェック<BR>
     * 　@get商品コード２３()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード２３()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６２－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ６３）　@発注上限数量２３のチェック<BR>
     * 　@get発注上限数量２３()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２３()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６３－１）　@発注上限数量２３が取得できた場合<BR>
     * 　@　@６３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@６３－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ６４）　@商品コード２４のチェック<BR>
     * 　@get商品コード２４()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード２４()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６４－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ６５）　@発注上限数量２４のチェック<BR>
     * 　@get発注上限数量２４()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２４()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６５－１）　@発注上限数量２４が取得できた場合<BR>
     * 　@　@６５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@６５－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * ６６）　@商品コード２５のチェック<BR>
     * 　@get商品コード２５()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get商品コード２５()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６６－１）　@文字数が7byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02378<BR>
     * <BR>
     * ６７）　@発注上限数量２５のチェック<BR>
     * 　@get発注上限数量２５()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get発注上限数量２５()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@６７－１）　@発注上限数量２５が取得できた場合<BR>
     * 　@　@６７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02379<BR>
     * <BR>
     * 　@　@６７－１－２）　@15byteより大きい場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :  BUSINESS_ERROR_02380<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E088AB01C2
     */
    public void validateDetailsLine(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@利用者コードのチェック
        //　@get利用者コード()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get利用者コード()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strUserCode = this.getUserCode(l_intLineNumber);
        //　@１－１）　@利用者コードが取得できない場合（get利用者コード() == null）、
        //           例外をスローする。 
        if (l_strUserCode == null)
        {
            log.debug("利用者コードが取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02366,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者コードが取得できません。");
        }
            
        //　@１－２）　@半角数字以外が含まれる場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isDigit(l_strUserCode))
        {
            log.debug("利用者コードの値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02367,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者コードの値が半角数字以外の値です。");
        }
            
        //　@１－３）　@文字数が8byteでない場合、例外をスローする。 
        if (l_strUserCode.length() != 8)
        {
            log.debug("利用者コードの値が8byteが数字ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02368,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者コードの値が8byteが数字ではありません。");
        }

        //２）　@利用者名チェック
        //　@get利用者名()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get利用者名()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strUserName = this.getUserName(l_intLineNumber);
        //　@２－１）　@利用者名が取得できた場合
        //　@　@２－１－１）　@文字数が120byteより大きい場合、例外をスローする。 
        if (l_strUserName != null && l_strUserName.getBytes().length > 120)
        {
            log.debug("利用者名の文字数が120byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02369,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者名の文字数が120byteより大きいです。");
        }
        
        //３）　@ログインＩＤのチェック　@
        //　@getログインＩＤ()にて、指定行番号のデータを取得しチェックを行う。
        //　@[getログインＩＤ()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strLoginID = this.getLoginId(l_intLineNumber);
        //　@３－１）　@ログインＩＤが取得できない場合（getログインＩＤ() == null）、
        //           例外をスローする。 
        if (l_strLoginID == null)
        {
            log.debug("ログインＩＤが取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02370,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ログインＩＤが取得できません。");
        }

        //　@３－２）　@半角数字以外が含まれる場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isDigit(l_strLoginID))
        {
            log.debug("ログインＩＤの値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02371,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ログインＩＤの値が半角数字以外の値です。");
        }

        //　@３－３）　@文字数が8byteでない場合、例外をスローする。         
        if (l_strLoginID.length() != 8)
        {
            log.debug("ログインＩＤの値が8byteが数字ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02372,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ログインＩＤの値が8byteが数字ではありません。");
        }
        
        //４）　@ログインパスワードのチェック
        //　@getログインパスワード()にて、指定行番号のデータを取得しチェックを行う。
        //　@[getログインパスワード()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strLoginPassword = this.getLoginPassword(l_intLineNumber);
        //　@４－１）　@ログインパスワードが取得できた場合
        //　@　@４－１－１）　@文字数が8byte以上13byte以下でない場合、
        //                 例外をスローする。        
        if (l_strLoginPassword != null 
                && (l_strLoginPassword.getBytes().length < 8 || l_strLoginPassword.getBytes().length > 13))
        {
            log.debug("パスワード（文字列）の長さが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パスワード（文字列）の長さが不正です。");
        }
        
        //５）　@発注パスワードのチェック
        //　@get発注パスワード()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注パスワード()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderPassword = this.getOrderPassword(l_intLineNumber);
        //　@５－１）　@発注パスワードが取得できた場合
        //　@　@５－１－１）　@文字数が8byte以上13byte以下でない場合、
        //                 例外をスローする。        
        if (l_strOrderPassword != null 
                && (l_strOrderPassword.getBytes().length < 8 || l_strOrderPassword.getBytes().length > 13))
        {
            log.debug("パスワード（文字列）の長さが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パスワード（文字列）の長さが不正です。");
        }
        
        //６）　@メールアドレス１のチェック
        //　@getメールアドレス１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[getメールアドレス１()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strMailAddress1 = this.getMailAddress1(l_intLineNumber);
        //　@６－１）　@メールアドレス１が取得できた場合
        //　@　@６－１－１）　@文字数が50byteより大きい場合、例外をスローする。
        if (l_strMailAddress1 != null && l_strMailAddress1.getBytes().length > 50)
        {
            log.debug("メールアドレスの桁数が制限を越えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01693,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレスの桁数が制限を越えています。");
        }

        //７）　@メールアドレス２のチェック
        //　@getメールアドレス２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[getメールアドレス２()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strMailAddress2 = this.getMailAddress2(l_intLineNumber);
        //　@７－１）　@メールアドレス２が取得できた場合
        //　@　@７－１－１）　@文字数が50byteより大きい場合、例外をスローする。
        if (l_strMailAddress2 != null && l_strMailAddress2.getBytes().length > 50)
        {
            log.debug("メールアドレスの桁数が制限を越えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01693,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレスの桁数が制限を越えています。");
        }

        //８）　@自己受託区分のチェック
        //　@get自己受託区分()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get自己受託区分()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strSelfTurstDiv = this.getSelfTrustDiv(l_intLineNumber);
        //　@８－１）　@文字数が1byteより大きい場合、例外をスローする。        
        if (l_strSelfTurstDiv != null && l_strSelfTurstDiv.getBytes().length > 1)
        {
            log.debug("自己受託区分の値が1byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02373,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "自己受託区分の値が1byteより大きいです。");
        }

        //９）　@利用者属性のチェック
        //　@get利用者属性()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get利用者属性()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strUserAttribute = this.getUserAttribute(l_intLineNumber);
        //　@９－１）　@利用者属性が取得できた場合
        //　@　@９－１－１）　@文字数が1byteより大きい場合、例外をスローする。
        if (l_strUserAttribute != null && l_strUserAttribute.getBytes().length > 1)
        {
        	log.debug("利用者属性の文字数が1byteより大きいです。");
        	log.exiting(STR_METHOD_NAME);
        	throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03111,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者属性の文字数が1byteより大きいです。");
        }

        //１０）　@決済方法@のチェック
        //　@get決済方法@()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get決済方法@()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strSettlementMethod = this.getSettlementMethod(l_intLineNumber);
        //　@１０－１）　@文字数が1byteより大きい場合、例外をスローする。
        if(l_strSettlementMethod != null && l_strSettlementMethod.getBytes().length > 1)
        {
        	log.debug("決済方法@の文字数が1byteより大きいです。");
        	log.exiting(STR_METHOD_NAME);
        	throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03112,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済方法@の文字数が1byteより大きいです。");
        }

        //１１）　@手数料区分のチェック
        //　@get手数料区分()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get手数料区分()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strCommissionDiv = this.getCommissionDiv(l_intLineNumber);
        //　@１１－１）　@文字数が2byteより大きい場合、例外をスローする。
        if (l_strCommissionDiv != null && l_strCommissionDiv.getBytes().length > 2)
        {
            log.debug("手数料区分の値が2byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02375,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "手数料区分の値が2byteより大きいです。");
        }

        //１２）　@ロスカット区分のチェック
        //　@getロスカット区分()にて、指定行番号のデータを取得しチェックを行う。
        //　@[getロスカット区分()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strLossCutDiv = this.getLossCutDiv(l_intLineNumber);
        //　@１２－１）　@文字数が2byteより大きい場合、例外をスローする。        
        if (l_strLossCutDiv != null && l_strLossCutDiv.getBytes().length > 2)
        {
            log.debug("ロスカット区分の値が2byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02374,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ロスカット区分の値が2byteより大きいです。");
        }

        //１３）　@取引可能区分のチェック
        //　@get取引可能区分()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get取引可能区分()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strTradingDiv = this.getTradingDiv(l_intLineNumber);
        //　@１３－１）　@文字数が1byteより大きい場合、例外をスローする。        
        if (l_strTradingDiv != null && l_strTradingDiv.getBytes().length > 1)
        {
            log.debug("取引可能区分の値が1byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02376,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引可能区分の値が1byteより大きいです。");
        }

        //１４）　@電子交付承諾日のチェック
        //　@get電子交付承諾日()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get電子交付承諾日()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strElectronGrantPermissionDay = this.getElectronGrantPermissionDay(l_intLineNumber);
        //　@１４－１）　@電子交付承諾日が取得できた場合
        //    １４－１－１） 文字数が8byteより大きい場合、例外をスローする。
        if (l_strElectronGrantPermissionDay != null && l_strElectronGrantPermissionDay.getBytes().length > 8)
        {
            log.debug("電子交付承諾日の値が8byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03113,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付承諾日の値が8byteより大きいです。");
        }

        //１５）　@取引説明書確認日のチェック
        //　@get取引説明書確認日()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get取引説明書確認日()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strTradeOperatingManualConfirmationDay = this.getTradeOperatingManualConfirmationDay(l_intLineNumber);
        //　@１５－１）　@取引説明書確認日が取得できた場合
        //    １５－１－１） 文字数が8byteより大きい場合、例外をスローする。
        if (l_strTradeOperatingManualConfirmationDay != null && l_strTradeOperatingManualConfirmationDay.getBytes().length > 8)
        {
            log.debug("取引説明書確認日の文字数が8byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03114,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引説明書確認日の文字数が8byteより大きいです。");
        }

        //１６）　@約諾書番号のチェック
        //　@get約諾書番号()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get約諾書番号()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strAgreementBookNumber = this.getAgreementBookNumber(l_intLineNumber);
        //　@１６－１）　@文字数が10byteより大きい場合、例外をスローする。
        if (l_strAgreementBookNumber != null && l_strAgreementBookNumber.getBytes().length > 10)
        {
            log.debug("約諾書番号の文字数が10byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03115,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約諾書番号の文字数が10byteより大きいです。");
        }

        //１７）　@備考のチェック
        //　@get備考()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get備考()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strRemark = this.getRemark(l_intLineNumber);
        //　@１７－１）　@備考が取得できない場合（get備考() == null）、
        //             例外をスローする。 
        if (l_strRemark == null)
        {
            log.debug("備考が取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02377,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "備考が取得できません。");
        }
        
        //　@１７－２）　@文字数が9byteでない場合、例外をスローする。        
        if (l_strRemark.getBytes().length != 9)
        {
            log.debug("備考のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00487,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "備考のサイズが不正です。");
        }
       
        //１８）　@商品コード１のチェック
        //　@get商品コード１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode1 = this.getProductCode1(l_intLineNumber);
        //　@１８－１）　@文字数が7byteより大きい場合、例外をスローする。        
        if (l_strProductCode1 != null && l_strProductCode1.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }
        
        //１９）　@発注上限数量１のチェック
        //　@get発注上限数量１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper1 = this.getOrderQuantityUpper1(l_intLineNumber);
        //　@１９－１）　@発注上限数量１が取得できた場合
        //　@　@１９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper1 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper1))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }
        //　@　@１９－１－２）　@15byteより大きい場合、例外をスローする。        
        if (l_strOrderQuantityUpper1 != null && l_strOrderQuantityUpper1.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }
        
        //２０）　@商品コード２のチェック
        //　@get商品コード２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode2 = this.getProductCode2(l_intLineNumber);
        //　@２０－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode2 != null && l_strProductCode2.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }
        
        //２１）　@発注上限数量２のチェック
        //　@get発注上限数量２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper2 = this.getOrderQuantityUpper2(l_intLineNumber);
        //　@２１－１）　@発注上限数量２が取得できた場合
        //　@　@２１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper2 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper2))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }
        //　@　@２１－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper2 != null && l_strOrderQuantityUpper2.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }
        
        //２２）　@商品コード３のチェック
        //　@get商品コード３()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード３()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode3 = this.getProductCode3(l_intLineNumber);
        //　@２２－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode3 != null && l_strProductCode3.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }
        
        //２３）　@発注上限数量３のチェック
        //　@get発注上限数量３()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量３()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper3 = this.getOrderQuantityUpper3(l_intLineNumber);
        //　@２３－１）　@発注上限数量３が取得できた場合
        //　@　@２３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper3 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper3))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@２３－１－２）　@15byteより大きい場合、例外をスローする。        
        if (l_strOrderQuantityUpper3 != null && l_strOrderQuantityUpper3.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }
        
        //２４）　@商品コード４のチェック
        //　@get商品コード４()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード４()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode4 = this.getProductCode4(l_intLineNumber);
        //　@２４－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode4 != null && l_strProductCode4.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }
        
        //２５）　@発注上限数量４のチェック
        //　@get発注上限数量４()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量４()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper4 = this.getOrderQuantityUpper4(l_intLineNumber);
        //　@２５－１）　@発注上限数量４が取得できた場合
        //　@　@２５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper4 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper4))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@２５－１－２）　@15byteより大きい場合、例外をスローする。        
        if (l_strOrderQuantityUpper4 != null && l_strOrderQuantityUpper4.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }
        
        //２６）　@商品コード５のチェック
        //　@get商品コード５()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード５()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode5 = this.getProductCode5(l_intLineNumber);
        //　@２６－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode5 != null && l_strProductCode5.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //２７）　@発注上限数量５のチェック
        //　@get発注上限数量５()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量５()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper5 = this.getOrderQuantityUpper5(l_intLineNumber);
        //２７－１）　@発注上限数量５が取得できた場合
        //　@２７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper5 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper5))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@２７－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper5 != null && l_strOrderQuantityUpper5.getBytes().length > 15)    
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //２８）　@商品コード６のチェック
        //　@get商品コード６()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード６()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode6 = this.getProductCode6(l_intLineNumber);
        //　@２８－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode6 != null && l_strProductCode6.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //２９）　@発注上限数量６のチェック
        //　@get発注上限数量６()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量６()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper6 = this.getOrderQuantityUpper6(l_intLineNumber);
        //　@２９－１）　@発注上限数量６が取得できた場合
        //　@　@２９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper6 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper6))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@２９－１－２）　@15byteより大きい場合、例外をスローする。         
        if (l_strOrderQuantityUpper6 != null && l_strOrderQuantityUpper6.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }
         
        //３０）　@商品コード７のチェック
        //　@get商品コード７()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード７()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strProductCode7 = this.getProductCode7(l_intLineNumber);
        //　@３０－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode7 != null && l_strProductCode7.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //３１）　@発注上限数量７のチェック
        //　@get発注上限数量７()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量７()に指定する引数] 
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper7 = this.getOrderQuantityUpper7(l_intLineNumber);
        //　@３１－１）　@発注上限数量７が取得できた場合
        //　@　@３１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (l_strOrderQuantityUpper7 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper7))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@３１－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper7 != null && l_strOrderQuantityUpper7.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //３２）　@商品コード８のチェック
        //　@get商品コード８()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード８()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode8 = this.getProductCode8(l_intLineNumber);
        //　@３２－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode8 != null && l_strProductCode8.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //３３）　@発注上限数量８のチェック
        //　@get発注上限数量８()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量８()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper8 = this.getOrderQuantityUpper8(l_intLineNumber);
        //　@３３－１）　@発注上限数量８が取得できた場合
        //　@　@３３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper8 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper8))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@３３－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper8 != null && l_strOrderQuantityUpper8.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //３４）　@商品コード９のチェック
        //　@get商品コード９()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード９()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode9 = this.getProductCode9(l_intLineNumber);
        //　@３４－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode9 != null && l_strProductCode9.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //３５）　@発注上限数量９のチェック
        //　@get発注上限数量９()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量９()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper9 = this.getOrderQuantityUpper9(l_intLineNumber);
        //　@３５－１）　@発注上限数量９が取得できた場合
        //　@　@３５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper9 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper9))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@３５－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper9 != null && l_strOrderQuantityUpper9.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //３６）　@商品コード１０のチェック
        //　@get商品コード１０()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１０()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode10 = this.getProductCode10(l_intLineNumber);
        //　@３６－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode10 != null && l_strProductCode10.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //３７）　@発注上限数量１０のチェック
        //　@get発注上限数量１０()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１０()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper10 = this.getOrderQuantityUpper10(l_intLineNumber);
        //　@３７－１）　@発注上限数量１０が取得できた場合
        //　@　@３７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper10 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper10))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@３７－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper10 != null && l_strOrderQuantityUpper10.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //３８）　@商品コード１１のチェック
        //　@get商品コード１１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１１()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode11 = this.getProductCode11(l_intLineNumber);
        //　@３８－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode11 != null && l_strProductCode11.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //３９）　@発注上限数量１１のチェック
        //　@get発注上限数量１１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１１()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper11 = this.getOrderQuantityUpper11(l_intLineNumber);
        //　@３９－１）　@発注上限数量１１が取得できた場合
        //　@　@３９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper11 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper11))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@３９－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper11 != null && l_strOrderQuantityUpper11.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //４０）　@商品コード１２のチェック
        //　@get商品コード１２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１２()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode12 = this.getProductCode12(l_intLineNumber);
        //　@４０－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode12 != null && l_strProductCode12.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //４１）　@発注上限数量１２のチェック
        //　@get発注上限数量１２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１２()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper12 = this.getOrderQuantityUpper12(l_intLineNumber);
        //　@４１－１）　@発注上限数量１２が取得できた場合
        //　@　@４１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper12 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper12))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@４１－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper12 != null && l_strOrderQuantityUpper12.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //４２）　@商品コード１３のチェック
        //　@get商品コード１３()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１３()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode13 = this.getProductCode13(l_intLineNumber);
        //　@４２－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode13 != null && l_strProductCode13.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //４３）　@発注上限数量１３のチェック
        //　@get発注上限数量１３()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１３()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper13 = this.getOrderQuantityUpper13(l_intLineNumber);
        //　@４３－１）　@発注上限数量１３が取得できた場合
        //　@　@４３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper13 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper13))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@４３－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper13 != null && l_strOrderQuantityUpper13.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //４４）　@商品コード１４のチェック
        //　@get商品コード１４()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１４()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode14 = this.getProductCode14(l_intLineNumber);
        //　@４４－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode14 != null && l_strProductCode14.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //４５）　@発注上限数量１４のチェック
        //　@get発注上限数量１４()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１４()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper14 = this.getOrderQuantityUpper14(l_intLineNumber);
        //　@４５－１）　@発注上限数量１４が取得できた場合
        //　@　@４５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper14 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper14))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@４５－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper14 != null && l_strOrderQuantityUpper14.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //４６）　@商品コード１５のチェック
        //　@get商品コード１５()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１５()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode15 = this.getProductCode15(l_intLineNumber);
        //　@４６－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode15 != null && l_strProductCode15.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //４７）　@発注上限数量１５のチェック
        //　@get発注上限数量１５()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１５()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper15 = this.getOrderQuantityUpper15(l_intLineNumber);
        //　@４７－１）　@発注上限数量１５が取得できた場合
        //　@　@４７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper15 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper15))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@４７－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper15 != null && l_strOrderQuantityUpper15.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //４８）　@商品コード１６のチェック
        //　@get商品コード１６()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１６()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode16 = this.getProductCode16(l_intLineNumber);
        //　@４８－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode16 != null && l_strProductCode16.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //４９）　@発注上限数量１６のチェック
        //　@get発注上限数量１６()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１６()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper16 = this.getOrderQuantityUpper16(l_intLineNumber);
        //　@４９－１）　@発注上限数量１６が取得できた場合
        //　@　@４９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper16 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper16))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@４９－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper16 != null && l_strOrderQuantityUpper16.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //５０）　@商品コード１７のチェック
        //　@get商品コード１７()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１７()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode17 = this.getProductCode17(l_intLineNumber);
        //　@５０－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode17 != null && l_strProductCode17.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //５１）　@発注上限数量１７のチェック
        //　@get発注上限数量１７()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１７()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper17 = this.getOrderQuantityUpper17(l_intLineNumber);
        //　@５１－１）　@発注上限数量１７が取得できた場合
        //　@　@５１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper17 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper17))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@５１－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper17 != null && l_strOrderQuantityUpper17.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //５２）　@商品コード１８のチェック
        //　@get商品コード１８()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１８()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode18 = this.getProductCode18(l_intLineNumber);
        //　@５２－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode18 != null && l_strProductCode18.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //５３）　@発注上限数量１８のチェック
        //　@get発注上限数量１８()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１８()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper18 = this.getOrderQuantityUpper18(l_intLineNumber);
        //　@５３－１）　@発注上限数量１８が取得できた場合
        //　@　@５３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper18 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper18))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@５３－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper18 != null && l_strOrderQuantityUpper18.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //５４）　@商品コード１９のチェック
        //　@get商品コード１９()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード１９()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode19 = this.getProductCode19(l_intLineNumber);
        //　@５４－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode19 != null && l_strProductCode19.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //５５）　@発注上限数量１９のチェック
        //　@get発注上限数量１９()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量１９()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper19 = this.getOrderQuantityUpper19(l_intLineNumber);
        //　@５５－１）　@発注上限数量１９が取得できた場合
        //　@　@５５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper19 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper19))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@５５－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper19 != null && l_strOrderQuantityUpper19.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //５６）　@商品コード２０のチェック
        //　@get商品コード２０()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２０()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode20 = this.getProductCode20(l_intLineNumber);
        //　@５６－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode20 != null && l_strProductCode20.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //５７）　@発注上限数量２０のチェック
        //　@get発注上限数量２０()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２０()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper20 = this.getOrderQuantityUpper20(l_intLineNumber);
        //　@５７－１）　@発注上限数量２０が取得できた場合
        //　@　@５７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper20 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper20))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@５７－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper20 != null && l_strOrderQuantityUpper20.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //５８）　@商品コード２１のチェック
        //　@get商品コード２１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２１()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode21 = this.getProductCode21(l_intLineNumber);
        //　@５８－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode21 != null && l_strProductCode21.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //５９）　@発注上限数量２１のチェック
        //　@get発注上限数量２１()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２１()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper21 = this.getOrderQuantityUpper21(l_intLineNumber);
        //　@５９－１）　@発注上限数量２１が取得できた場合
        //　@　@５９－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper21 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper21))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@５９－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper21 != null && l_strOrderQuantityUpper21.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //６０）　@商品コード２２のチェック
        //　@get商品コード２２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２２()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode22 = this.getProductCode22(l_intLineNumber);
        //　@６０－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode22 != null && l_strProductCode22.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //６１）　@発注上限数量２２のチェック
        //　@get発注上限数量２２()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２２()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper22 = this.getOrderQuantityUpper22(l_intLineNumber);
        //　@６１－１）　@発注上限数量２２が取得できた場合
        //　@　@６１－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper22 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper22))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@６１－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper22 != null && l_strOrderQuantityUpper22.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //６２）　@商品コード２３のチェック
        //　@get商品コード２３()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２３()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode23 = this.getProductCode23(l_intLineNumber);
        //　@６２－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode23 != null && l_strProductCode23.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //６３）　@発注上限数量２３のチェック
        //　@get発注上限数量２３()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２３()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper23 = this.getOrderQuantityUpper23(l_intLineNumber);
        //　@６３－１）　@発注上限数量２３が取得できた場合
        //　@　@６３－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper23 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper23))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@６３－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper23 != null && l_strOrderQuantityUpper23.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //６４）　@商品コード２４のチェック
        //　@get商品コード２４()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２４()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode24 = this.getProductCode24(l_intLineNumber);
        //　@６４－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode24 != null && l_strProductCode24.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //６５）　@発注上限数量２４のチェック
        //　@get発注上限数量２４()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２４()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper24 = this.getOrderQuantityUpper24(l_intLineNumber);
        //　@６５－１）　@発注上限数量２４が取得できた場合
        //　@　@６５－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper24 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper24))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@６５－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper24 != null && l_strOrderQuantityUpper24.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }

        //６６）　@商品コード２５のチェック
        //　@get商品コード２５()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get商品コード２５()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strProductCode25 = this.getProductCode25(l_intLineNumber);
        //　@６６－１）　@文字数が7byteより大きい場合、例外をスローする。
        if (l_strProductCode25 != null && l_strProductCode25.getBytes().length > 7)
        {
            log.debug("商品コードの値が7byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02378,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品コードの値が7byteより大きいです。");
        }

        //６７）　@発注上限数量２５のチェック
        //　@get発注上限数量２５()にて、指定行番号のデータを取得しチェックを行う。
        //　@[get発注上限数量２５()に指定する引数]
        //　@行番号：　@引数.行番号
        String l_strOrderQuantityUpper25 = this.getOrderQuantityUpper25(l_intLineNumber);
        //　@６７－１）　@発注上限数量２５が取得できた場合
        //　@　@６７－１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (l_strOrderQuantityUpper25 != null && !WEB3StringTypeUtility.isDigit(l_strOrderQuantityUpper25))
        {
            log.debug("発注上限数量の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02379,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が半角数字以外の値です。");
        }

        //　@　@６７－１－２）　@15byteより大きい場合、例外をスローする。
        if (l_strOrderQuantityUpper25 != null && l_strOrderQuantityUpper25.getBytes().length > 15)
        {
            log.debug("発注上限数量の値が15byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注上限数量の値が15byteより大きいです。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate重複行)<BR>
     * 重複データが存在しないかチェックする。<BR>
     * <BR>
     * １）　@追加済みの明細チェック<BR>
     * <BR>
     * 　@１－１）　@get利用者コード（行番号）にて、<BR>
     * 　@　@　@　@　@　@　@　@指定行番号の利用者コードを取得する。<BR> 
     * <BR>
     * 　@１－２）　@取得した利用者コードと引数.行番号より前の明細行の<BR>
     * 　@　@　@　@　@　@利用者コードを比較する。 <BR>
     * 　@　@　@　@　@　@同じ値だった場合、例外「重複エラー。」をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :   BUSINESS_ERROR_02381<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E193B900B3
     */
    public void validateDuplicateLine(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@追加済みの明細チェック
        //　@１－１）　@get利用者コード（行番号）にて、
        //           指定行番号の利用者コードを取得する。
        String l_strUserCode = this.getUserCode(l_intLineNumber);
         
        //　@１－２）　@取得した利用者コードと引数.行番号より
        //           前の明細行の利用者コードを比較する。 
        //　@　@　@　@　@　@同じ値だった場合、例外「重複エラー。」をスローする。
        for (int i = 0; i < l_intLineNumber; i++)
        {
            if (l_strUserCode.equals(this.getUserCode(i)))
            {
                log.debug("利用者コード重複エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02381,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "利用者コード重複エラー。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get利用者コード)<BR>
     * 指定行番号に対応する明細行の利用者コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて利用者コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.利用者コードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43E1A96E0075
     */
    public String getUserCode(int l_intLineNumber) 
    {
        //this.get項目値()にて利用者コードを取得し返却する。         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.USER_CODE_LABEL));
    }
    
    /**
     * (get利用者名)<BR>
     * 指定行番号に対応する明細行の利用者名を取得する。 <BR>
     * <BR>
     * this.get項目値()にて利用者名を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.利用者名ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43E31F02032B
     */
    public String getUserName(int l_intLineNumber) 
    {
        //this.get項目値()にて利用者名を取得し返却する。         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.USER_NAME_LABEL));
    }
    
    /**
     * (getログインＩＤ)<BR>
     * 指定行番号に対応する明細行のログインＩＤを取得する。 <BR>
     * <BR>
     * this.get項目値()にてログインＩＤを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.ログインＩＤラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1CD50393
     */
    public String getLoginId(int l_intLineNumber) 
    {
        //this.get項目値()にてログインＩＤを取得し返却する。         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.LOGINID_LABEL));
    }
    
    /**
     * (getログインパスワード)<BR>
     * 指定行番号に対応する明細行のログインパスワードを取得する。 <BR>
     * <BR>
     * this.get項目値()にてログインパスワードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.ログインパスワードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC14EA0181
     */
    public String getLoginPassword(int l_intLineNumber) 
    {
        //this.get項目値()にてログインパスワードを取得し返却する。         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.LOGIN_PASSWORD_LABEL));
    }
    
    /**
     * (get発注パスワード)<BR>
     * 指定行番号に対応する明細行の発注パスワードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注パスワードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注パスワードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC15400181
     */
    public String getOrderPassword(int l_intLineNumber) 
    {
        //this.get項目値()にて発注パスワードを取得し返却する。         
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_PASSWORD_LABEL));
    }
    
    /**
     * (getメールアドレス１)<BR>
     * 指定行番号に対応する明細行のメールアドレス１を取得する。 <BR>
     * <BR>
     * this.get項目値()にてメールアドレス１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.メールアドレス１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43E31F8803D7
     */
    public String getMailAddress1(int l_intLineNumber) 
    {
        //this.get項目値()にてメールアドレス１を取得し返却する。     
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS1_LABEL));
    }
    
    /**
     * (getメールアドレス２)<BR>
     * 指定行番号に対応する明細行のメールアドレス２を取得する。 <BR>
     * <BR>
     * this.get項目値()にてメールアドレス２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.メールアドレス２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1B560170
     */
    public String getMailAddress2(int l_intLineNumber) 
    {
        //this.get項目値()にてメールアドレス２を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.MAILADDRESS2_LABEL));
    }
    
    /**
     * (get自己受託区分)<BR>
     * 指定行番号に対応する明細行の自己受託区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にて自己受託区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.自己受託区分ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1B5A01BE
     */
    public String getSelfTrustDiv(int l_intLineNumber) 
    {
        //this.get項目値()にて自己受託区分を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.SELF_TRUSTDIV_LABEL));
    }

    /**
     * (get利用者属性)<BR>
     * 指定行番号に対応する明細行の利用者属性を取得する。 <BR>
     * <BR>
     * this.get項目値()にて利用者属性を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.利用者属性ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getUserAttribute(int l_intLineNumber)
    {
        //this.get項目値()にて利用者属性を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.USER_ATTRIBUTE_LABEL));
    }

    /**
     * (get決済方法@)<BR>
     * 指定行番号に対応する明細行の決済方法@を取得する。 <BR>
     * <BR>
     * this.get項目値()にて決済方法@を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.決済方法@ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getSettlementMethod(int l_intLineNumber)
    {
        //this.get項目値()にて決済方法@を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.SETTLEMENT_METHOD_LABEL));
    }

    /**
     * (getロスカット区分)<BR>
     * 指定行番号に対応する明細行のロスカット区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にてロスカット区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.ロスカット区分ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1BA00354
     */
    public String getLossCutDiv(int l_intLineNumber) 
    {
        //this.get項目値()にてロスカット区分を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.LOSSCUTDIV_LABEL));
    }
    
    /**
     * (get手数料区分)<BR>
     * 指定行番号に対応する明細行の手数料区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にて手数料区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.手数料区分ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1BA30170
     */
    public String getCommissionDiv(int l_intLineNumber) 
    {
        //this.get項目値()にて手数料区分を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.COMMISSIONDIV_LABEL));
    }
    
    /**
     * (get取引可能区分)<BR>
     * 指定行番号に対応する明細行の取引可能区分を取得する。 <BR>
     * <BR>
     * this.get項目値()にて取引可能区分を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.取引可能区分ラベル)の戻り値。  <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1BA6024B
     */
    public String getTradingDiv(int l_intLineNumber) 
    {
        //this.get項目値()にて取引可能区分を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.TRADINGDIV_LABEL));
    }

    /**
     * (get電子交付承諾日)<BR>
     * 指定行番号に対応する明細行の電子交付承諾日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて電子交付承諾日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.電子交付承諾日ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getElectronGrantPermissionDay(int l_intLineNumber)
    {
        //this.get項目値()にて電子交付承諾日を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminFXAccOpenUploadCsv.ELECTRON_GRANT_PERMISSION_DAY_LABEL));
    }

    /**
     * (get取引説明書確認日)<BR>
     * 指定行番号に対応する明細行の取引説明書確認日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて取引説明書確認日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.取引説明書確認日ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getTradeOperatingManualConfirmationDay(int l_intLineNumber)
    {
        //this.get項目値()にて取引説明書確認日を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminFXAccOpenUploadCsv.TRADE_OPERATING_MANUAL_CONFIRMATION_DAY_LABEL));
    }

    /**
     * (get約諾書番号)<BR>
     * 指定行番号に対応する明細行の約諾書番号を取得する。 <BR>
     * <BR>
     * this.get項目値()にて約諾書番号を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.約諾書番号ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getAgreementBookNumber(int l_intLineNumber)
    {
        //this.get項目値()にて約諾書番号を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminFXAccOpenUploadCsv.AGREEMENT_BOOK_NUMBER_LABEL));
    }

    /**
     * (get備考)<BR>
     * 指定行番号に対応する明細行の備考を取得する。 <BR>
     * <BR>
     * this.get項目値()にて備考を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.備考ラベル)の戻り値。の戻り値。  <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43E31FDA005D
     */
    public String getRemark(int l_intLineNumber) 
    {
        //this.get項目値()にて備考を取得し返却する。 
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.REMARK_LABEL));
    }
    
    /**
     * (get商品コード１)<BR>
     * 指定行番号に対応する明細行の商品コード１を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1BAA0354
     */
    public String getProductCode1(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード１を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE1_LABEL));
    }
    
    /**
     * (get発注上限数量１)<BR>
     * 指定行番号に対応する明細行の発注上限数量１を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1BAD00A5
     */
    public String getOrderQuantityUpper1(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量１を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER1_LABEL));
    }
    
    /**
     * (get商品コード２)<BR>
     * 指定行番号に対応する明細行の商品コード２を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C12024B
     */
    public String getProductCode2(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード２を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE2_LABEL));
    }
    
    /**
     * (get発注上限数量２)<BR>
     * 指定行番号に対応する明細行の発注上限数量２を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C12026A
     */
    public String getOrderQuantityUpper2(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量２を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER2_LABEL));
    }
    
    /**
     * (get商品コード３)<BR>
     * 指定行番号に対応する明細行の商品コード３を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード３を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード３ラベル)の戻り<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C140160
     */
    public String getProductCode3(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード３を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE3_LABEL));
    }
    
    /**
     * (get発注上限数量３)<BR>
     * 指定行番号に対応する明細行の発注上限数量３を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量３を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量３ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C140170
     */
    public String getOrderQuantityUpper3(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量３を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER3_LABEL));
    }
    
    /**
     * (get商品コード４)<BR>
     * 指定行番号に対応する明細行の商品コード４を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード４を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード４ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C15021C
     */
    public String getProductCode4(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード４を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE4_LABEL));
    }
    
    /**
     * (get発注上限数量４)<BR>
     * 指定行番号に対応する明細行の発注上限数量４を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量４を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量４ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C15023B
     */
    public String getOrderQuantityUpper4(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量４を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER4_LABEL));
    }
    
    /**
     * (get商品コード５)<BR>
     * 指定行番号に対応する明細行の商品コード５を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード５を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード５ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C1602C8
     */
    public String getProductCode5(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード５を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE5_LABEL));
    }
    
    /**
     * (get発注上限数量５)<BR>
     * 指定行番号に対応する明細行の発注上限数量５を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量５を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量５ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C1602D7
     */
    public String getOrderQuantityUpper5(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量５を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER5_LABEL));
    }
    
    /**
     * (get商品コード６)<BR>
     * 指定行番号に対応する明細行の商品コード６を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード６を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード６ラベル)の戻り値。  <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C1702C8
     */
    public String getProductCode6(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード６を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE6_LABEL));
    }
    
    /**
     * (get発注上限数量６)<BR>
     * 指定行番号に対応する明細行の発注上限数量６を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量６を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量６ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C1702E7
     */
    public String getOrderQuantityUpper6(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量６を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER6_LABEL));
    }
    
    /**
     * (get商品コード７)<BR>
     * 指定行番号に対応する明細行の商品コード７を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード７を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード７ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C190047
     */
    public String getProductCode7(int l_intLineNumber) 
    {
        //this.get項目値()にて商品コード７を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE7_LABEL));
    }
    
    /**
     * (get発注上限数量７)<BR>
     * 指定行番号に対応する明細行の発注上限数量７を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量７を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量７ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC1C190066
     */
    public String getOrderQuantityUpper7(int l_intLineNumber) 
    {
        //this.get項目値()にて発注上限数量７を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER7_LABEL));
    }

    /**
     * (get商品コード８)<BR>
     * 指定行番号に対応する明細行の商品コード８を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード８を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード８ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode8(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード８を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE8_LABEL));
    }

    /**
     * (get発注上限数量８)<BR>
     * 指定行番号に対応する明細行の発注上限数量８を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量８を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量８ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper8(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量８を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER8_LABEL));
    }

    /**
     * (get商品コード９)<BR>
     * 指定行番号に対応する明細行の商品コード９を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード９を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード９ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode9(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード９を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE9_LABEL));
    }

    /**
     * (get発注上限数量９)<BR>
     * 指定行番号に対応する明細行の発注上限数量９を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量９を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量９ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper9(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量９を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER9_LABEL));
    }
    
    /**
     * (get商品コード１０)<BR>
     * 指定行番号に対応する明細行の商品コード１０を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１０を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１０ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode10(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１０を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE10_LABEL));
    }

    /**
     * (get発注上限数量１０)<BR>
     * 指定行番号に対応する明細行の発注上限数量１０を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１０を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１０ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper10(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１０を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER10_LABEL));
    }

    /**
     * (get商品コード１１)<BR>
     * 指定行番号に対応する明細行の商品コード１１を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode11(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１１を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE11_LABEL));
    }

    /**
     * (get発注上限数量１１)<BR>
     * 指定行番号に対応する明細行の発注上限数量１１を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper11(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１１を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER11_LABEL));
    }

    /**
     * (get商品コード１２)<BR>
     * 指定行番号に対応する明細行の商品コード１２を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode12(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１２を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE12_LABEL));
    }

    /**
     * (get発注上限数量１２)<BR>
     * 指定行番号に対応する明細行の発注上限数量１２を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper12(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１２を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER12_LABEL));
    }

    /**
     * (get商品コード１３)<BR>
     * 指定行番号に対応する明細行の商品コード１３を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１３を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１３ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode13(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１３を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE13_LABEL));
    }

    /**
     * (get発注上限数量１３)<BR>
     * 指定行番号に対応する明細行の発注上限数量１３を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１３を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１３ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper13(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１３を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER13_LABEL));
    }

    /**
     * (get商品コード１４)<BR>
     * 指定行番号に対応する明細行の商品コード１４を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１４を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１４ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode14(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１４を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE14_LABEL));
    }

    /**
     * (get発注上限数量１４)<BR>
     * 指定行番号に対応する明細行の発注上限数量１４を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１４を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１４ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper14(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１４を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER14_LABEL));
    }

    /**
     * (get商品コード１５)<BR>
     * 指定行番号に対応する明細行の商品コード１５を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１５を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１５ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode15(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１５を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE15_LABEL));
    }

    /**
     * (get発注上限数量１５)<BR>
     * 指定行番号に対応する明細行の発注上限数量１５を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１５を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１５ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper15(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１５を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER15_LABEL));
    }

    /**
     * (get商品コード１６)<BR>
     * 指定行番号に対応する明細行の商品コード１６を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１６を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１６ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode16(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１６を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE16_LABEL));
    }

    /**
     * (get発注上限数量１６)<BR>
     * 指定行番号に対応する明細行の発注上限数量１６を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１６を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１６ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper16(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１６を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER16_LABEL));
    }

    /**
     * (get商品コード１７)<BR>
     * 指定行番号に対応する明細行の商品コード１７を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１７を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１７ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode17(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１７を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE17_LABEL));
    }

    /**
     * (get発注上限数量１７)<BR>
     * 指定行番号に対応する明細行の発注上限数量１７を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１７を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１７ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper17(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１７を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER17_LABEL));
    }

    /**
     * (get商品コード１８)<BR>
     * 指定行番号に対応する明細行の商品コード１８を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１８を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１８ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode18(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１８を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE18_LABEL));
    }

    /**
     * (get発注上限数量１８)<BR>
     * 指定行番号に対応する明細行の発注上限数量１８を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１８を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１８ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper18(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１８を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER18_LABEL));
    }

    /**
     * (get商品コード１９)<BR>
     * 指定行番号に対応する明細行の商品コード１９を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード１９を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード１９ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode19(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード１９を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE19_LABEL));
    }

    /**
     * (get発注上限数量１９)<BR>
     * 指定行番号に対応する明細行の発注上限数量１９を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量１９を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量１９ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper19(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量１９を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER19_LABEL));
    }

    /**
     * (get商品コード２０)<BR>
     * 指定行番号に対応する明細行の商品コード２０を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２０を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２０ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode20(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード２０を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE20_LABEL));
    }

    /**
     * (get発注上限数量２０)<BR>
     * 指定行番号に対応する明細行の発注上限数量２０を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２０を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２０ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper20(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量２０を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER20_LABEL));
    }

    /**
     * (get商品コード２１)<BR>
     * 指定行番号に対応する明細行の商品コード２１を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode21(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード２１を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE21_LABEL));
    }

    /**
     * (get発注上限数量２１)<BR>
     * 指定行番号に対応する明細行の発注上限数量２１を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２１を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２１ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper21(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量２１を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER21_LABEL));
    }

    /**
     * (get商品コード２２)<BR>
     * 指定行番号に対応する明細行の商品コード２２を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode22(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード２２を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE22_LABEL));
    }

    /**
     * (get発注上限数量２２)<BR>
     * 指定行番号に対応する明細行の発注上限数量２２を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２２を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２２ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper22(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量２２を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER22_LABEL));
    }

    /**
     * (get商品コード２３)<BR>
     * 指定行番号に対応する明細行の商品コード２３を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２３を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２３ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode23(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード２３を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE23_LABEL));
    }

    /**
     * (get発注上限数量２３)<BR>
     * 指定行番号に対応する明細行の発注上限数量２３を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２３を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２３ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper23(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量２３を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER23_LABEL));
    }

    /**
     * (get商品コード２４)<BR>
     * 指定行番号に対応する明細行の商品コード２４を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２４を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２４ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode24(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード２４を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE24_LABEL));
    }

    /**
     * (get発注上限数量２４)<BR>
     * 指定行番号に対応する明細行の発注上限数量２４を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２４を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２４ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper24(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量２４を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER24_LABEL));
    }

    /**
     * (get商品コード２５)<BR>
     * 指定行番号に対応する明細行の商品コード２５を取得する。 <BR>
     * <BR>
     * this.get項目値()にて商品コード２５を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.商品コード２５ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getProductCode25(int l_intLineNumber)
    {
        //this.get項目値()にて商品コード２５を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.PRODUCTCODE25_LABEL));
    }

    /**
     * (get発注上限数量２５)<BR>
     * 指定行番号に対応する明細行の発注上限数量２５を取得する。 <BR>
     * <BR>
     * this.get項目値()にて発注上限数量２５を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(管理者FX口座開設アップロードCSV<BR>
     * 　@　@　@　@　@.発注上限数量２５ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     */
    public String getOrderQuantityUpper25(int l_intLineNumber)
    {
        //this.get項目値()にて発注上限数量２５を取得し返却する。
        return (String) this.getValue(l_intLineNumber,
            this.getColumnModel(WEB3AdminFXAccOpenUploadCsv.ORDER_QUANTITY_UPPER25_LABEL));
    }

    /**
     * (getFXログインID頭文字)<BR>
     * 引数.行番号に対応する明細行のFXログインID頭文字を取得する。 <BR>
     * <BR>
     * １） this.get利用者コード(引数.行番号)にて利用者コードを取得する。 <BR>
     * <BR>
     * ２）　@取得した利用者コードの前2桁を返却する。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC6CA90041
     */
    public String getFXHeadOfLoginId(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getFXHeadOfLoginId(int)";
        log.entering(STR_METHOD_NAME);
        
        //１） this.get利用者コード(引数.行番号)にて利用者コードを取得する。 
        String l_strUserCode = this.getUserCode(l_intLineNumber);
       
        //２）　@取得した利用者コードの前2桁を返却する。
        log.exiting(STR_METHOD_NAME);    
        return l_strUserCode.substring(0, 2);
    }
    
    /**
     * (get顧客コード)<BR>
     * 指定行番号に対応する明細行の顧客コード（6桁）を取得する。 <BR>
     * <BR>
     * １）　@this.get利用者コード(引数.行番号)にて利用者コードを取得する。<BR>
     * <BR>
     * ２）　@取得した利用者コードの後6桁を返却する。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43EC341401BE
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME);
        
        //１） this.get利用者コード(引数.行番号)にて利用者コードを取得する。 
        String l_strUserCode = this.getUserCode(l_intLineNumber);
       
        //２） 取得した利用者コードの後6桁を返却する。
        log.exiting(STR_METHOD_NAME);    
        return l_strUserCode.substring(l_strUserCode.length() - 6);
    }
    
    /**
     * (get部店コード)<BR>
     * 引数.行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * １） this.getFXログインID頭文字(引数.行番号)にて<BR>
     * 　@　@　@FXログインID頭文字を取得する。<BR>
     * <BR>
     * ２） 以下の条件のもとに会社別FXシステム条件テーブルを取得する。<BR>
     * <BR>
     * 　@・証券会社コード == this.get証券会社コード()<BR>
     * 　@・FXログインID頭文字.substring(1) == 取得したFXログインID頭文字<BR>
     * <BR>
     * ３） 会社別FXシステム条件Param.部店コードを返却する。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43E2F60F0379
     */
    public String getBranchCode(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //１） this.getFXログインID頭文字(引数.行番号)にて
        //     FXログインID頭文字を取得する。
        String l_strFXHeadOfLoginId = this.getFXHeadOfLoginId(l_intLineNumber);

        //２） 以下の条件のもとに会社別FXシステム条件テーブルを取得する。
        //　@・証券会社コード == this.get証券会社コード()
        //　@・FXログインID頭文字.substring(1) == 取得したFXログインID頭文字
        String l_strWhere = " institution_code = ? and substr(fx_head_of_login_id, 2) = ? ";
        Object[] l_objValue = {this.getInstitutionCode(), l_strFXHeadOfLoginId};
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if(l_lisRows == null || l_lisRows.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        //３） 会社別FXシステム条件Param.部店コードを返却する。
        String l_strBranchCode = ((CompFxConditionParams) l_lisRows.get(0)).getBranchCode();
        log.exiting(STR_METHOD_NAME);    
        return l_strBranchCode;
    }
    
    /**
     * (getGFT口座開設状況)<BR>
     * 行番号に対応する明細行のGFT口座開設状況Paramsを取得する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 　@以下の条件で、GFT口座開設状況テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード == this.get証券会社コード()<BR>
     * 　@部店コード == this.get部店コード（引数.行番号）<BR>
     * 　@「顧客コード」の前6桁 == this.get顧客コード(引数.行番号)<BR>
     * 　@識別コード == this.get備考(引数.行番号)<BR>
     * 　@名前（姓） == this.get利用者名(引数.行番号)<BR>
     * 　@メールアドレス == this.getメールアドレス１(引数.行番号)<BR>
     * 　@「ログインID」の後8桁 == this.getログインＩＤ（引数.行番号）<BR>
     * 　@口座開設状況区分 == "3：ダウンロード済"<BR>
     * 　@約諾書区分 == "2：受領済"<BR>
     * <BR>
     * 　@１－１）　@レコードが取得できなかった場合、または<BR>
     * 　@　@　@　@　@複数取得できた場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag  :   BUSINESS_ERROR_02392<BR>
     * <BR>
     * ２）　@取得したGFT口座開設状況Paramsを返却する。<BR>
     * 　@　@　@　@　@
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return GftAccountOpenStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 43E30BAB031C
     */
    public GftAccountOpenStatusParams getGFTAccountOpenStatus(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getGFTAccountOpenStatus(int)";
        log.entering(STR_METHOD_NAME);        
        //１）DB検索 
        //　@以下の条件で、GFT口座開設状況テーブルを検索する。 
        //　@[条件]
        //　@証券会社コード == this.get証券会社コード()
        //　@部店コード == this.get部店コード（引数.行番号）
        //　@「顧客コード」の前6桁 == this.get顧客コード(引数.行番号)
        //　@識別コード == this.get備考(引数.行番号)
        //　@名前（姓） == this.get利用者名(引数.行番号)
        //　@メールアドレス == this.getメールアドレス１(引数.行番号)
        //　@「ログインID」の後8桁 == this.getログインＩＤ（引数.行番号）
        //　@口座開設状況区分 == "3：ダウンロード済"
        //　@約諾書区分 == "2：受領済"
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and substr(account_code,0,6) = ? ");
        l_sbWhere.append(" and order_request_number = ? ");
        l_sbWhere.append(" and substr(login_id,length(login_id) - 7) = ? ");
        l_sbWhere.append(" and account_open_status_div = ? ");
        l_sbWhere.append(" and agreement_div = ? ");
                            
        ArrayList l_arrValue = new ArrayList();
        l_arrValue.add(this.getInstitutionCode()); 
        l_arrValue.add(this.getBranchCode(l_intLineNumber));
        l_arrValue.add(this.getAccountCode(l_intLineNumber));
        l_arrValue.add(this.getRemark(l_intLineNumber));
        l_arrValue.add(this.getLoginId(l_intLineNumber));
        l_arrValue.add(WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE);
        l_arrValue.add(WEB3AioAgreementDivDef.RECIEVED);
        
        if (this.getUserName(l_intLineNumber) == null)
        {
            l_sbWhere.append(" and last_name is null ");
        }
        else
        {
            l_sbWhere.append(" and last_name = ? ");
            l_arrValue.add(this.getUserName(l_intLineNumber));
        }
        
        if (this.getMailAddress1(l_intLineNumber) == null)
        {
            l_sbWhere.append("and mail_address is null ");
        }
        else
        {
            l_sbWhere.append("and mail_address = ? ");
            l_arrValue.add(this.getMailAddress1(l_intLineNumber));
        }
        
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    GftAccountOpenStatusRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_arrValue.toArray());
        }
        catch (DataQueryException l_ex)
        {
            log.error("条件に該当するデータが存在しない、または複数存在します。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02392,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない、または複数存在します。");
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@１－１）　@レコードが取得できなかった場合、または
        //　@　@　@　@　@複数取得できた場合、例外をスローする。
        if(l_lisRows == null || l_lisRows.size() != 1)
        {
            log.debug("条件に該当するデータが存在しない、または複数存在します。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02392,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない、または複数存在します。");
        }
        
        //２）　@取得したGFT口座開設状況Paramsを返却する。        
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams  = (GftAccountOpenStatusParams) l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);    
        return l_gftAccountOpenStatusParams;
    }
}
@
