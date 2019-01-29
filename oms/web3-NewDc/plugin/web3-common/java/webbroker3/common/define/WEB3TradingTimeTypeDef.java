head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingTimeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付時間区分　@定数定義インタフェイス(WEB3TradingTimeType)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 仲川 里織(SRA) 新規作成
Revesion History : 2006/06/29 栄イ 共通仕様変更管理台帳・ＤＢレイアウト386を対応
Revesion History : 2007/06/06 キョウ再平 ＤＢレイアウト(取引時間テーブル)による
Revesion History : 2007/07/11 栄イ 共通仕様変更管理台帳・ＤＢレイアウト524を対応
Revesion History : 2007/09/14 孟暁シン 共通仕様変更管理台帳・ＤＢレイアウト544を対応
Revesion History : 2008/02/01 栄イ 共通仕様変更管理台帳・ＤＢレイアウト605を対応
Revesion History : 2008/04/24 栄イ 共通仕様変更管理台帳・ＤＢレイアウト622を対応
Revesion History : 2008/9/24 陸文静 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.649
*/
package webbroker3.common.define;

/**
 * 受付時間区分　@定数定義インタフェイス。
 *
 * @@author 仲川 里織(SRA)
 * @@version 1.0
 */
public interface WEB3TradingTimeTypeDef
{
    /**
     * その他：00
     */
    public static final String DEFAULT = "00";
    
    /**
     * 株式・信用：01
     */
    public static final String EQUITY = "01";
    
    /**
     * IPO：02
     */
    public static final String IPO = "02";
    
    /**
     * 立会外分売：03
     */
    public static final String SALES_OUTSIDE_MARKET = "03";
    
    /**
     * 代用保証金：04
     */
    public static final String SUB_GURANTEE_FEE = "04";
    
    /**
     * 株式ミニ投資：05
     */
    public static final String MINI_STOCK = "05";
    
    /**
     * 投資信託：06
     */
    public static final String MUTUAL_FUND = "06";
    
    /**
     * 中国Ｆ：07
     */
    public static final String MIDIUM_TERM_GOV_FUND = "07";
    
    /**
     * ＭＭＦ（設定）：08
     */
    public static final String MMF_SET = "08";
    
    /**
     * ＭＭＦ（設定・解約）：09
     */
    public static final String MMF_SET_CANCEL = "09";
    
    /**
     * 外国株式：10
     */
    public static final String FOREIGN_STOCK = "10";
    
    /**
     * 株価指数オプション：11
     */
    public static final String INDEX_FUTURE_OP = "11";
    
    /**
     * 株価指数オプション（訂正取消）：12
     */
    public static final String INDEX_FUTURE_OP_CHANGE_CANCEL = "12";
    
    /**
     * 証拠金振替：13
     */
    public static final String MARGIN_TRANSFER = "13";
    
    /**
     * 入金：14
     */
    public static final String RECIEPT = "14";
    
    /**
     * ＭＲＦ：15
     */
    public static final String MRF = "15";
    
    /**
     * 出金：16
     */
    public static final String PAYMENT = "16";
    
    /**
     * アップロード（管理者）：17
     */
    public static final String UPLOAD = "17";

    /**
     * 入金連絡：18
     */
    public static final String DEPOSIT_INFORM = "18";

    /**
     * 現引・現渡：19
     */
    public static final String SWAP = "19";
    
    /**
     * 証券振替、出庫通知　@：20
     */
    public static final String SECURITY_TRANSFER = "20";
    
    /**
     * ポイントシステム：21
     */
    public static final String POINT_SYSTEM = "21";
    
    /**
     * 口座開設：22
     */
    public static final String ACCOUNT_OPEN = "22";
    
    /**
     * 為替保証金：23
     */
    public static final String EXCHANGE_GUARANTEE = "23";
    
    /**
     * 外国株式振替連携：24
     */
    public static final String FEQ_CON = "24";
    
    /**
     * 債券：25
     */
    public static final String BOND = "25";
    
    /**
     * アップロード終日（管理者）：26
     */
    public static final String UPLOAD_DAYLONG = "26";
    
    /**
     * サービス利用：27
     */
    public static final String SRVREGI = "27";
    
    
    /**
     * シングルサインオン：28
     */
    public static final String SINGLE_SIGN_ON = "28";

    /**
     * SONAR連携（管理者）：29
     */
    public static final String SONAR_CON = "29";

    /**
     * 30：ダウンロード
     */
    public static final String DOWNLOAD = "30";

    /**
     * 31：入金通知再処理
     */
    public static final String CASHIN_NOTICE_REDEALING = "31";

    /**
     * 32：IPO抽選割当(管理者)
     */
    public static final String IPO_LOT_ADMIN = "32";

    /**
     * 33：投信定時定額買付
     */
    public static final String MF_FIXED_BUYING = "33";

    /**
     * 35:顧客情報伝票作成
     */
    public static final String ACCOUNTINFO_VOUCHER_CREATE = "35";

    /**
     * 36:国内債券
     */
    public static final String DOMESTIC_BOND = "36";

    /**
     * 37:証券担保ローン
     */
    public static final String SECURITY_LOAN = "37";

    /**
     * 38:外国株式（取消）
     */
    public static final String FEQ_CANCEL = "38";

    /**
     * 39:外国株式（訂正）
     */
    public static final String FEQ_CHANGE = "39";

    /**
     * 40：CFD
     */
    public static final String CFD = "40";
}
@
