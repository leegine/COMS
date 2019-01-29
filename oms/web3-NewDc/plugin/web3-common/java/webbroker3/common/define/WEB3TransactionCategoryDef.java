head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransactionCategoryDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 機@能カテゴリコード 定数定義インタフェイス(WEB3TransactionCategoryDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2006/02/20 凌建平(中訊) 共通仕様変更・ＤＢレイアウト344
Revesion History : 2006/03/17 凌建平(中訊) 共通仕様変更・ＤＢレイアウト350
Revesion History : 2006/03/28 凌建平(中訊) 共通仕様変更・ＤＢレイアウト363
Revesion History : 2006/03/31 凌建平(中訊) 共通仕様変更・ＤＢレイアウト357
Revesion History : 2006/05/11 凌建平(中訊) 共通仕様変更・ＤＢレイアウト371
Revesion History : 2006/05/26 凌建平(中訊) 共通仕様変更・ＤＢレイアウト372
Revesion History : 2006/06/20 凌建平(中訊) 共通仕様変更・ＤＢレイアウト379
Revesion History : 2006/07/21 栄イ(中訊) 共通仕様変更・ＤＢレイアウト394、395
Revesion History : 2006/07/21 栄イ(中訊) 共通仕様変更・ＤＢレイアウト401
Revesion History : 2006/08/30 栄イ(中訊) 共通仕様変更・ＤＢレイアウト409
Revesion History : 2006/10/12 栄イ(中訊) 共通仕様変更・ＤＢレイアウト434
Revesion History : 2006/10/24 栄イ(中訊) 共通仕様変更・ＤＢレイアウト439
Revesion History : 2007/01/15 栄イ(中訊) 共通仕様変更・ＤＢレイアウト454
Revesion History : 2007/01/25 栄イ(中訊) 共通仕様変更・ＤＢレイアウト452
Revesion History : 2007/01/31 栄イ(中訊) 共通仕様変更・ＤＢレイアウト458
Revesion History : 2007/04/23 栄イ(中訊) 共通仕様変更・ＤＢレイアウト474
Revesion History : 2007/05/29 栄イ(中訊) 共通仕様変更・ＤＢレイアウト493
Revesion History : 2007/07/20 栄イ(中訊) 共通仕様変更・ＤＢレイアウト528
Revesion History : 2007/09/10 栄イ(中訊) 共通仕様変更・ＤＢレイアウト542
Revesion History : 2007/10/09 栄イ(中訊) 共通仕様変更・ＤＢレイアウト556
Revesion History : 2007/11/26 栄イ(中訊) 共通仕様変更・ＤＢレイアウト578
Revesion History : 2008/01/28 栄イ(中訊) 共通仕様変更・ＤＢレイアウト591
Revesion History : 2008/03/04 栄イ(中訊) 共通仕様変更・ＤＢレイアウト612
Revesion History : 2008/03/11 栄イ(中訊) 共通仕様変更・ＤＢレイアウト614
Revesion History : 2008/03/24 栄イ(中訊) 共通仕様変更・ＤＢレイアウト617
Revesion History : 2008/09/24 陸文静 (中訊) 共通仕様変更・ＤＢレイアウト642
Revesion History : 2008/12/16 趙林鵬 (中訊) 共通仕様変更・ＤＢレイアウト666
Revesion History : 2009/01/05 趙林鵬 (中訊) 共通仕様変更・ＤＢレイアウト669
Revesion History : 2009/03/04 趙林鵬 (中訊) 共通仕様変更・ＤＢレイアウト675
*/
package webbroker3.common.define;

/**
 * 管理者権限.機@能カテゴリコード 定数定義インタフェイス
 *
 * @@author 鄭海良(SRA)
 * @@version 1.0
 */
public interface WEB3TransactionCategoryDef
{

    /**
     * 顧客基本情報（基本）
     */
    public static final String ACCINFO_BASE_INFO = "A0101";

    /**
     *　@顧客振込先管理
     */
    public static final String ACCINFO_TRANSFER_ACCOUNT = "A0102";

    /**
     * 顧客取引管理：A0103
     */
    public static final String ACCINFO_TRADING = "A0103";

    /**
     * 暗証番号管理
     */
    public static final String ACCINFO_PASSWORD = "A0104";

    /**
     * 顧客登録管理メニュー
     */
    public static final String ACCINFO_REGISTER_MANAGE_MENU = "A0105";

    /**
     * 口座切替・電子交付申込管理
     */
    public static final String ACCOUNT_SWITCH_ELECTRON_DELI_APPLY = "A0106";

    /**
     * 余力管理
     */
    public static final String TRADING_POWER = "A0201";

    /**
     * プライベートインフォメーション
     */
    public static final String PRIVATE_INFO = "A0301";

    /**
     * メール情報 　@
     */
    public static final String MAILINFO = "A0302";

    /**
     * 連絡管理 　@
     */
    public static final String INFORM = "A0303";
    
    /**
     * 問合せ管理
     */
    public static final String FAQ = "A0304";
    
    /**
     * 問合せ管理（現物株式・ミニ株・信用取引）
     */
    public static final String FAQ_EQUITY = "A0305";
    
    /**
     * 問合せ管理（株価指数先物・オプション）
     */
    public static final String FAQ_XBIFO = "A0306";
    
    /**
     * 問合せ管理（外国株式）
     */
    public static final String FAQ_FOREIGN_EQUITY = "A0307";
    
    /**
     * 問合せ管理（投信・累投）
     */
    public static final String FAQ_XBMF_XBRUITO = "A0308";
    
    /**
     * 問合せ管理（IPO）
     */
    public static final String FAQ_IPO = "A0309";
    
    /**
     * 問合せ管理（サービス利用）
     */
    public static final String FAQ_SRVREGI = "A0310";
    
    /**
     * 問合せ管理（入出金）
     */
    public static final String FAQ_AIO = "A0311";
    
    /**
     * 問合せ管理（口座開設）
     */
    public static final String FAQ_ACCOPEN = "A0312";
    
    /**
     * 問合せ管理（お客様情報）
     */
    public static final String FAQ_ACCOUNTINFO = "A0313";
    
    /**
     * 問合せ管理（プライベートインフォメーション）
     */
    public static final String FAQ_PRIVATEINFO = "A0314";
    
    /**
     * 問合せ管理（取引履歴）
     */
    public static final String FAQ_TRADEHISTORY = "A0315";
    
    /**
     * 問合せ管理（取引残高報告書）
     */
    public static final String FAQ_TRADE_BALANCE_REPORT = "A0316";
    
    /**
     * 問合せ管理（入出庫履歴）
     */
    public static final String FAQ_SEC_RECEIPT_DELIVERY_ACTION = "A0317";
    
    /**
     * 問合せ管理（証拠金振替）
     */
    public static final String FAQ_DEPOSIT_TRANSFER = "A0318";
    
    /**
     * 問合せ管理（資産余力）
     */
    public static final String FAQ_ASSET_POWER = "A0319";
    
    /**
     * 問合せ管理（損益明細照会）
     */
    public static final String FAQ_PROFIT_LOSS_INQUIRY = "A0320";
    
    /**
     * 問合せ管理（簿価単価明細詳細）
     */
    public static final String FAQ_UNIT_PRICE_DETAILS = "A0321";
    
    /**
     * 問合せ管理（ポイントシステム）
     */
    public static final String FAQ_POINTSYSTEM = "A0322";
    
    /**
     * 問合せ管理（為替証拠金）
     */
    public static final String FAQ_EXCHANGE_DEPOSIT = "A0323";
    
    /**
     * 問合せ管理（計算サービス）
     */
    public static final String FAQ_CALC_SERVICE = "A0324";
    
    /**
     * 問合せ管理（電子交付サービス）
     */
    public static final String FAQ_ELEC_PAY_SERVICE = "A0325";
    
    /**
     * 問合せ管理（外国株式振替連携）
     */
    public static final String FAQ_FEQ_CON = "A0326";

    /**
     * 口座開設 　@
     */
    public static final String ACC_OPEN = "A0401";

    /**
     * 審査 　@
     */
    public static final String JUDGE = "A0402";

    /**
     * 口座開設申込アップロード
     */
    public static final String ACC_OPEN_APPLY_UPLOAD = "A0403";

    /**
     * PTS顧客管理
     */
    public static final String PTS_ACCOUNT_MANAGE = "A0501";

    /**
     * 出金請求 　@
     */
    public static final String AIO_CASH_OUT_INQ = "B0101";

    /**
     * 銀行連携 　@
     */
    public static final String BANK_COOPERATION = "B0102";

    /**
     * 金融機@関決済連携　@
     */
    public static final String AIO_SETTLE_REPORT = "B0201";

    /**
     * 入金連絡 　@
     */
    public static final String AIO_CASH_IN_NOTICE = "B0301";

    /**
     * 為替保証金管理(口座管理・口座開設管理) 　@
     */
    public static final String FX_ACCOUNT_MANAGE = "B0401";

    /**
     * 為替保証金管理(振替管理) 　@
     */
    public static final String FX_TRANSFER_MANAGE = "B0402";

    /**
     * 外国株式振替連携(口座管理・口座開設管理) 　@
     */
    public static final String FEQ_ACCOUNT_MANAGE = "B0501";

    /**
     * 外国株式振替連携(振替管理) 　@
     */
    public static final String FEQ_TRANSFER_MANAGE = "B0502";

    /**
     * 証券担保ローン(口座開設管理)
     */
    public static final String SECURITY_LOAN_ACCOUNT_OPEN_MANAGE = "B0601";

    /**
     * 証券担保ローン(担保銘柄管理)
     */
    public static final String SECURITY_LOAN_PRODUCT_MANAGE = "B0602";

    /**
     * 証券担保ローン(出金可能額制御管理)
     */
    public static final String SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE = "B0603";

    /**
     * 国内株式（顧客・銘柄別取引停止） 　@
     */
    public static final String EQTYPE_ACCOUNT_PRODUCT_STOP = "C0101";

    /**
     * 国内株式（銘柄設定） 　@
     */
    public static final String EQTYPE_PRODUCT_SETTING = "C0102";

    /**
     * 国内株式（注文約定照会） 　@
     */
    public static final String EQTYPE_ORDER_EXEC_INQUIRY = "C0103";

    /**
     * 国内株式（立会い外分売） 　@
     */
    public static final String EQTYPE_OFF_FLOOR_ORDER = "C0104";

    /**
     * 投信（銘柄管理） 　@
     */
    public static final String ADMIN_MUTUAL_CONDITIONS = "C0201";

    /**
     * 投信（カレンダー管理） 　@
     */
    public static final String ADMIN_MUTUAL_FRGNCAL = "C0202";

    /**
     * 投信・累投（注文約定照会） 　@
     */
    public static final String XBMF_XBRUITO_ORDER_EXEC_INQUIRY = "C0203";

    /**
     * 累投（銘柄管理）　@
     */
    public static final String ADMIN_RUITO_TRADE_STOP = "C0204";

    /**
     * 投信（余力調整）
     */
    public static final String ADMIN_MUTUAL_TRADING_POWER_ADJUST = "C0205";

    /**
     * 先物・ＯＰ（注文約定照会） 　@
     */
    public static final String XBIFO_ORDER_EXEC_INQUIRY = "C0301";

    /**
     * 外株（基準為替登録） 　@
     */
    public static final String FEQ_BASE_EXCHANGE_REGIST = "C0401";
    
    /**
     * 外株（注文約定管理）
     */
    public static final String FEQ_ORDER_EXEC_MANAGE = "C0402";
    
    /**
     * 外株（銘柄管理）
     */
    public static final String FEQ_PRODUCT_MANAGE = "C0403";
    
    /**
     * 外株（現地手数料管理）
     */
    public static final String FEQ_LOCATION_COMMISSION_MANAGE = "C0404";
    
    /**
     * 外株（カレンダー管理）
     */
    public static final String FEQ_CALENDAR_MANAGE = "C0405";
    
    /**
     * ＩＰＯ管理（新規登録） 　@
     */
    public static final String ADMIN_IPO_PROD_REG = "C0501";

    /**
     * ＩＰＯ管理（銘柄管理） 　@
     */
    public static final String ADMIN_IPO_PROD_OPE = "C0502";

    /**
     * サービス利用管理（サービス） 　@
     */
    public static final String SRVREGI_SERVICE = "C0601";

    /**
     * サービス利用管理（顧客） 　@
     */
    public static final String SRVREGI_ACCOUNT = "C0602";

    /**
     * サービス利用管理（外部連携）
     */
    public static final String SRVREGI_OTHERORG = "C0603";

    /**
     * ポイントシステム（ポイント交換） 　@
     */
    public static final String POINT_EXCHANGE = "C0701";

    /**
     * ポイントシステム（顧客別ポイント管理） 　@
     */
    public static final String POINT_MANAGE_BY_CUSTOMER = "C0702";

    /**
     * ポイントシステム（ポイント一括調整） 　@
     */
    public static final String POINT_PACKAGE_ADJUST = "C0703";

    /**
     * ポイントシステム（景品管理） 　@
     */
    public static final String POINT_PREMIUM_MANAGE = "C0704";

    /**
     * 取引管理（ログイン） 　@
     */
    public static final String TRADE_MANAGEMENT_LOGIN = "C0801";

    /**
     * 取引管理（商品） 　@
     */
    public static final String TRADE_MANAGEMENT_PRODUCT = "C0802";

    /**
     * 取引管理（市場）
     */
    public static final String TRADE_MANAGEMENT_MARKET = "C0803";

    /**
     * 注文件数管理
     */
    public static final String ORDER_COUNT_MANAGEMENT = "C0804";

    /**
     * その他件数管理
     */
    public static final String OTHER_ORDER_COUNT_MANAGEMENT = "C0805";

    /**
     * 手数料管理 　@
     */
    public static final String ACCINFO_COMMISSION = "C0901";

    /**
     * ハイパーボックス手数料管理 　@
     */
    public static final String ACCINFO_HYPERBOX_COMMISSION = "C0902";

    /**
     * 手数料割引キャンペーン管理
     */
    public static final String ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN = "C0903";

    /**
     * フロント発注（通知履歴参照） 　@
     */
    public static final String ADMIN_FRONT_NOTICE_HIST_REF = "C1001";

    /**
     * フロント発注（発注経路切替）　@
     */
    public static final String ADMIN_FRONT_ROUTE_SWITCH = "Z0201";

    /**
     * 管理者管理 　@
     */
    public static final String ADMINMC_ADMINISTRATOR = "D0101";

    /**
     * 管理者権限管理 　@
     */
    public static final String ADMINMC_PERMISSION = "D0102";

    /**
     * オペレータ管理
     */
    public static final String ADMINMC_OPERATOR = "D0201";

    /**
     * 先物・ＯＰ（トリガー注文照会）
     */
    public static final String TRIGGER_ORDER_INQUIRY = "C0302";

    /**
     * 先物・ＯＰ（トリガー注文手動発注）
     */
    public static final String TRIGGER_ORDER_HANDLER_SEND = "C0303";

    /**
     * 先物・ＯＰ（手動失効）
     */
    public static final String MANUAL_EXPIRE = "C0304";

    /**
     * C0105：国内株式（トリガー注文照会）
     */
    public static final String DOMESTIC_EQUITY_TRIGGER_ORDER_INQUIRY = "C0105";

    /**
     * C0106：国内株式（トリガー注文手動発注）
     */
    public static final String DOMESTIC_EQUITY_TRIGGER_ORDER_MANUAL = "C0106";

    /**
     * C0806:トリガー注文手動失効
     */
    public static final String TRIGGER_ORDER_MANUAL_EXPIRE = "C0806";

    /**
     * Z0101:キューテーブルステータス更新
     */
    public static final String HOST_STATUS_UPDATE = "Z0101";

    /**
     * C0807:トリガー注文取扱停止
     */
    public static final String TRIGGER_ORDER_TRADING_STOP = "C0807";

    /**
     * C0808:基準為替登録
     */
    public static final String BASE_FX_RATE_REG = "C0808";
    
    /**
     * B0103:バーチャル口座入金アップロード
     */
    public static final String VIRTUAL_ACC_CASHIN_UPLOAD = "B0103";

    /**
     * C0107:国内株式（手動失効）
     */
    public static final String DOMESTIC_EQUITY_MANUAL_EXPIRE = "C0107";

    /**
     * C0108:国内株式（強制決済）
     */
    public static final String DOMESTIC_EQUITY_FORCED_SETTLE = "C0108";

    /**
     * C0109:国内株式（出来入力・出来取消）
     */
    public static final String DOMESTIC_EQUITY_EXEC_INPUT_CANCEL = "C0109";

    /**
     * Z0102:注文単位テーブルステータス更新
     */
    public static final String ORDER_UNIT_UPDATE = "Z0102";

    /**
     * Z0103:デーモントリガーテーブルステータス更新
     */
    public static final String DAEMON_TRIGGER_UPDATE = "Z0103";

    /**
     * Z0104:アップロードテーブル終了日時更新
     */
    public static final String UPLOAD_END_TIMESTAMP_UPDATE = "Z0104";

    /**
     * C1101:債券（銘柄管理）
     */
    public static final String BOND_PRODUCT_MANAGE = "C1101";

    /**
     * C1102:債券（約定入力）
     */
    public static final String BOND_EXECUTE_INPUT = "C1102";

    /**
     * C1103:債券（注文約定照会）
     */
    public static final String BOND_ORDER_EXECUTE_REFERENCE = "C1103";

    /**
     * C1104:債券（約定変更、約定取消）
     */
    public static final String BOND_EXECUTE_MODIFY_CANCEL = "C1104";

    /**
     * F0101:審査状況照会
     */
    public static final String EXAMINATION_STATUS_REFERENCE = "F0101";

    /**
     * 
     * G0101:金商法@交付閲覧登録
     */
    public static final String FPT_REG = "G0101";

    /**
     * G0102:金商法@交付閲覧登録照会
     */
    public static final String FPT_REGIST_INQUIRY = "G0102";

    /**
     * G0103:金商法@交付閲覧履歴照会
     */
    public static final String FPT_HISTORY_INQUIRY = "G0103";

    /**
     * G0104:金商法@交付閲覧アップロード
     */
    public static final String FPT_UPLOAD = "G0104";

    /**
     * G0105:書面未承諾強制ログアウト
     */
    public static final String FPT_FORCE_LOGOUT = "G0105";

    /**
     * C1301:IP別ログイン回数一覧
     */
    public static final String IP_LOGIN_FREQUENCY_LIST = "C1301";

    /**
     * A0404:口座開設資料請求データ削除
     */
    public static final String ACC_OPEN_APPLY_DATA_DEL = "A0404";

    /**
     * C0110:国内株式（注意情報照会）
     */
    public static final String DOMESTIC_EQUITY_ATTENTION_INFO_REFERENCE = "C0110";

    /**
     * C0305:証拠金不足状況照会
     */
    public static final String DEPOSIT_SHORTAGE_REFERENCE = "C0305";
}@
