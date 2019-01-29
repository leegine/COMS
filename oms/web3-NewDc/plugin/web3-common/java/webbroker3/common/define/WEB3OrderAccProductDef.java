head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderAccProductDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文受付商品 定数定義インタフェイス(WEB3OrderAccProductDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2004/12/17 森川    (SRA)    　@定数追加
Revesion History : 2006/04/27 凌建平(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo368
Revesion History : 2006/10/19 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo436
Revesion History : 2008/9/24 陸文静 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.653
*/
package webbroker3.common.define;

/**
 * 注文受付商品(注文受付ステイタステーブル)　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderAccProductDef
{
    /**
     * 01 : 株式
     */
    public static final String STOCK = "01";

    /**
     * 02 : 株式ミニ投資
     */
    public static final String MINI_STOCK = "02";

    /**
     * 03 : 信用取引
     */
    public static final String MARGIN = "03";

    /**
     * 04 : 外国株
     */
    public static final String FOREIGN_STOCK = "04";

    /**
     * 05 : 先物
     */
    public static final String FUTURE = "05";

    /**
     * 06 : オプション
     */
    public static final String OPTION = "06";

    /**
     * 07 : 投資信託
     */
    public static final String MUTUAL_FUND = "07";

    /**
     * 08 : 累積投資
     */
    public static final String MONTHLY_INVESTMENT_PLAN = "08";

    /**
     * 09 : 信用保証金への振替
     */
    public static final String MARGIN_GUARANTEE_MONEY_TRANSFER = "09";

    /**
     * 10 : 信用保証金からの振替
     */
    public static final String MARGIN_GUARANTEE_MONEY_TRANSFER_FROM = "10";

    /**
     * 11 : 証券振替、出庫通知
     */
    public static final String TRASUT_SUBSTITUTION_CHANGE = "11";

    /**
     * 12 : 先OP保証金への振替
     */
    public static final String FUTURE_OP_GUARANTEE_MONEY_CHANGE = "12";

    /**
     * 13 : 先OP証拠金からの振替
     */
    public static final String FUTURE_OP_GUARANTEE_MONEY_CHANGE_FROM = "13";

    /**
     * 14 : 入出金
     */
    public static final String PAYMENT = "14";

    /**
     * 20 : IPO
     */
    public static final String IPO = "20";

    /**
     * 21 : サービス利用
     */
    public static final String SRV_REGI = "21";

    /**
     * 22 : 顧客サービス
     */
    public static final String ACCOUNT_SERVICE = "22";

    /**
     * 23 : 為替保証金
     */
    public static final String EXCHANGE_GUARANTEE = "23";

    /**
     * 24 : ポイントシステム
     */
    public static final String POINT_SYSTEM = "24";

    /**
     * 25 : 立会外分売
     */
    public static final String SALES_OUTSIDE_MARKET = "25";

    /**
     * 26 : 連絡管理
     */
    public static final String INFORM_MANAGEMENT = "26"; 
    
    /**
     * 27 : 外国株式振替連携
     */
    public static final String FEQ_CON = "27";
    
    /**
     * 28 : 債券
     */
    public static final String BOND = "28";
    
    /**
     * 29 : シングルサインオン
     */
    public static final String SINGLE_SIGN_ON = "29";
    
    /**
     * 30：連続注文
     */
    public static final String RESERVE_ORDER = "30";
    
    /**
     * 31: 証拠金推移
     */
    public static final String DEPOSIT_TRANSITION = "31";

    /**
     * 31: 入金通知再処理
     */
    public static final String CASHIN_NOTICE_REDEALING = "31";

    /**
     * 32：余力
     */
    public static final String TRADING_POWER = "32";

    /**
     * 33：コンプライアンス
     */
    public static final String COMPLIANCE = "33";

    /**
     * 40：CFD
     */
    public static final String CFD = "40";
}
@
