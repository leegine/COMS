head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostRequestCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブルデータコード 定数定義インタフェイス(WEB3HostRequestCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 中尾　@寿彦(SRA) 新規作成
Revesion History : 2006/06/29 栄イ 【口座開設】仕様変更管理台帳・ＤＢレイアウト026を対応
Revesion History : 2006/07/12 栄イ 【口座開設】仕様変更管理台帳・ＤＢレイアウト028を対応
Revesion History : 2006/08/30 栄イ(中訊)  ＤＢレイアウト(外貨入出金テーブル)による
Revesion History : 2006/08/30 栄イ(中訊)  ＤＢレイアウト(外貨入出金伝票キューテーブル)による
Revesion History : 2006/09/05 栄イ(中訊)  ＤＢレイアウト(外貨入出金伝票受付キューテーブル)による
Revesion History : 2006/09/06 栄イ 【口座開設】仕様変更管理台帳・ＤＢレイアウト033を対応
Revesion History : 2006/09/11 栄イ 【口座開設】仕様変更管理台帳・ＤＢレイアウト034を対応
Revesion History : 2007/02/08 栄イ ＤＢレイアウト(外貨MMF注文キューテーブル)による
Revesion History : 2007/06/13 栄イ 【共通】仕様変更管理台帳・ＤＢレイアウト500を対応
Revesion History : 2007/09/20 孟暁シン 【連絡管理】モバイル専用口座２次対応
Revesion History : 2008/12/31 趙林鵬 【株管理者】仕様変更管理台帳ＤＢレイアウト024
Revesion History : 2009/08/13 趙林鵬 【口座開設】仕様変更管理台帳・ＤＢレイアウト055を対応
Revesion History : 2009/09/04 趙林鵬 【口座開設】仕様変更管理台帳・ＤＢレイアウト063を対応
*/
package webbroker3.common.define;

/**
 * キューテーブルデータコード定数定義インタフェイス。<BR>
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3HostRequestCodeDef
{
    /**
     * 株式注文訂正取消通知。<BR>
     *
     */
    public static final String EQUITY_CHANGE_CANCEL_NOTICE = "AI817";

    /**
     * 株式取引注文。<BR>
     *
     */
    public static final String EQUITY_ORDER = "AI801";

    /**
     * 株式注文受付。<BR>
     *
     */
    public static final String EQUITY_ORDER_ACCEPT = "AI80A";

    /**
     * 株式注文訂正取消。<BR>
     *
     */
    public static final String EQUITY_CHANGE_CANCEL = "AI80B";

    /**
     * 株式出来通知。<BR>
     */
    public static final String EQUITY_ORDER_EXEC_NOTICE = "AI811";

    /**
     * 株式失効通知。<BR>
     */
    public static final String EQUITY_ORDER_CLOSE_NOTICE = "AI813";

    /**
     * 株式出来終了通知。<BR>
     */
    public static final String EQUITY_ORDER_EXEC_END_NOTICE = "AI814";

    /**
     * 株式注文入力通知。<BR>
     */
    public static final String EQUITY_ORDER_INPUT_NOTICE = "AI821";

    /**
     * 株式注文訂正。<BR>
     */
    public static final String EQUITY_ORDER_CHANGE = "AI802";

    /**
     * 株式注文取消。<BR>
     */
    public static final String EQUITY_ORDER_CANCEL = "AI802";

    /**
     * 株式現引現渡注文。<BR>
     */
    public static final String EQUITY_SPOT_ORDER = "AI805";

    /**
     * 株式現引現渡注文訂正取消。<BR>
     */
    public static final String EQUITY_SPOT_ORDER_CHANGE_CANCEL = "AI808";

    /**
     * 株式指定埋指示。<BR>
     */
    public static final String EQUITY_ORDER_DESIGNATE = "AI806";

    /**
     * 株式注文訂正受付。<BR>
     */
    public static final String EQUITY_ORDER_CHANGE_ACCEPT = "AI80B";

    /**
     * 株式注文取消受付。<BR>
     */
    public static final String EQUITY_ORDER_CANCEL_ACCEPT = "AI80B";

    /**
     * 株式現引現渡注文受付。<BR>
     */
    public static final String EQUITY_SPOT_ORDER_ACCEPT = "AI80E";

    /**
     * 株式現引現渡注文訂正取消受付。<BR>
     */
    public static final String EQUITY_SPOT_ORDER_CHANGE_CANCEL_ACCEPT = "AI80H";

    /**
     * 株式注文通知。<BR>
     */
    public static final String EQUITY_ORDER_NOTICE = "AI821";

    /**
     * 注文繰越スキップ銘柄通知。<BR>
     */
    public static final String EQUITY_TRANSFE_PRODUCT_NOTICE = "AI822";

    /**
     * 現引現渡入力通知。<BR>
     */
    public static final String MARGIN_SWAP_INPUT_NOTICE = "AI826";
    

    /* 先物オプションデータコード名称一覧 */

    /* 上り処理 */

    /** 
     * 株価指数オプション注文 
     */
    public static final String OPTION_ORDER = "EI801";

    /** 
     * 株価指数オプション注文訂正 & 株価指数オプション注文取消 
     */
    public static final String OPTION_ORDER_CHANGE_CANCEL = "EI802";

    /** 
     * 株価指数先物注文 
     */
    public static final String FUTURES_ORDER = "EI803";

    /** 
     * 株価指数先物注文訂正 & 株価指数先物注文取消 
     */
    public static final String FUTURES_ORDER_CHANGE_CANCEL = "EI804";

    /** 
     * 株価指数オプション指定埋指示 
     */
    public static final String OPTION_DESIGNATE_EMBEDDED_INDICATION = "EI806";

    /** 
     * 株価指数先物指定埋指示 
     */
    public static final String FUTURES_DESIGNATE_EMBEDDED_INDICATION = "EI807";

    /* 下り処理 */

    /** 
     * 株価指数オプション注文受付 
     */
    public static final String OPTION_ORDER_ACCEPT = "EI80A";

    /** 
     * 株価指数オプション注文訂正受付 & 株価指数オプション注文取消受付 
     */
    public static final String OPTION_ORDER_CHANGE_CANCEL_ACCEPT = "EI80B";

    /** 
     * 株価指数先物注文受付 
     */
    public static final String FUTURES_ORDER_ACCEPT = "EI80C";

    /** 
     * 株価指数先物注文訂正受付 & 株価指数先物注文取消受付
     */
    public static final String FUTURES_ORDER_CHANGE_CANCEL_ACCEPT = "EI80D";

    /** 
     * 株価指数オプション出来通知 
     */
    public static final String OPTION_EXEC_NOTICE = "EI811";

    /** 
     * 株価指数オプション訂正取消通知 
     */
    public static final String OPTION_CHANGE_CANCEL_NOTICE = "EI812";

    /** 
     * 株価指数オプション失効通知 
     */
    public static final String OPTION_CLOSE_NOTICE = "EI813";

    /** 
     * 株価指数オプション出来終了通知 
     */
    public static final String OPTION_EXEC_END_NOTICE = "EI814";

    /** 
     * 株価指数先物出来通知 
     */
    public static final String FUTURES_EXEC_NOTICE = "EI815";

    /** 
     * 株価指数先物訂正取消通知 
     */
    public static final String FUTURES_CHANGE_CANCEL_NOTICE = "EI816";

    /** 
     * 株価指数先物失効通知 
     */
    public static final String FUTURES_CLOSE_NOTICE = "EI817";

    /** 
     * (MAXAS-AP：OP注文通知)固定
     */
    public static final String OPTION_ORDER_NOTICE = "EI821";    
    
    /** 
     * (MAXAS-AP：先物注文通知)固定
     */
    public static final String FUTURES_ORDER_NOTICE = "EI822";

    /**
     * 株価指数オプション夕場出来終了
     */
    public static final String OPTION_EVENING_SESSION_END = "EI823";

    /**
     * 株価指数先物夕場出来終了
     */
    public static final String FUTURES_EVENING_SESSION_END = "EI824";

    /** 
     * 株価指数先物出来終了通知 
     */
    public static final String FUTURES_EXEC_END_NOTICE = "EI818";

    /* 累積投資データコード名称一覧 */
    
    /** 
     * 新規注文市場リクエストメッセージ送信（MRF解約） 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_MRF_SELL = "GI831";

    /** 
     * 新規注文市場リクエストメッセージ送信（買付） 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_BUY = "DI801";

    /** 
     * 新規注文市場リクエストメッセージ送信（解約）
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_SELL = "DI803";

    /* 投資信託データコード名称一覧 */
    
    /** 
     * 国内投信注文 
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER = "CI801";

    /** 
     * 国内投信注文取消 
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_CANCEL = "CI802";

    /** 
     * 外国投信注文 
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER = "CI803";

    /** 
     * 外国投信注文取消 
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_CANCEL = "CI804";

    /** 
     * 投信乗換注文 
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER = "CI805";

    /** 
     * 投信乗換注文取消	
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER_CANCEL = "CI806";

    /**
     * CI807：募集
     */
    public static final String MUTUAL_FUND_RECRUIT = "CI807";

    /** 
     * 国内投信注文受付	
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_ACCEPT = "CI80A";

    /** 
     * 国内投信注文取消受付	
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_ACCEPT_CANCEL = 
        "CI80B";

    /** 
     * 外国投信注文受付	
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_ACCEPT = "CI80C";

    /** 
     * 外国投信注文取消受付	
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_ACCEPT_CANCEL =
        "CI80D";

    /** 
     * 投信乗換注文受付	
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER_ACCEPT = "CI80E";

    /** 
     * 投信乗換注文取消受付	
     */
    public static final String MUTUAL_FUND_SWITCHING_ORDER_ACCEPT_CANCEL =
        "CI80F";

    /** 
     * 投信売買乗換注文通知	
     */
    public static final String MUTUAL_FUND_DEALING_SWITCHING_ORDER_NOTIFY =
        "CI811";

    /** 
     * 新規注文市場リクエストメッセージ送信（解約:市場送信あり）
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_SELL_CANCEL = "DI804";

    /** 
     * 新規注文市場リクエストメッセージ送信（買付:市場送信あり） 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_ORDER_CANCEL =
        "DI802";

    /** 
     * 新規注文市場リクエストメッセージ送信（MRF解約:市場送信あり） 
     */
    public static final String RUITO_REQUEST_MESSAGE_SEND_MRF_ORDER_CANCEL =
        "GI832";

    /**
     * （入出金テーブルのデータコード）GI811：個別
     */
    public static final String AIO_INDIVIDUAL = "GI811";

    /**
     * （入出金テーブルのデータコード）FI811：本部一括 
     */
    public static final String AIO_ALL_HEADQUARTERS = "FI811";

    /** 
     * GI80C（入出金伝票受付） 
     */
    public static final String AIO_SLIP_ACCEPT = "GI80C";

    /**
     * （入出金伝票キューテーブルのデータコード）GI803 
     */
    public static final String AIO_SLIP_SERVE = "GI803";

    /**
     * （出金請求注文キューテーブルのデータコード）GI801 
     */
    public static final String AIO_CASH_OUT_REQUEST_ORDER = "GI801";

    /**
     * （出金請求受付キューテーブルのデータコード）GI80A 
     */
    public static final String AIO_CASH_OUT_REQUEST_ACCEPT = "GI80A";

    /**
     * （振替請求注文キューテーブルのデータコード）GI806(保証金振替請求) 
     */
    public static final String AIO_TRANSFER_REQUEST_ORDER = "GI806";

    /**
     * （振替請求受付キューテーブルのデータコード）GI80F (保証金振替請求受付)
     */
    public static final String AIO_TRANSFER_REQUEST_ACCEPT = "GI80F";

    /**
     * （振替入力通知キューテーブルのデータコード）GI812(保証金振替強制通知) 
     */
    public static final String AIO_TRANSFER_INPUT_NOTIFY = "GI812";
    
    /**
     * （代用振替請求キューテーブルのデータコード）GI807(代用振替請求)
     */
    public static final String AIO_MRGSEC_TRANSFER = "GI807";
 
    /**
     * （代用振替強制キューテーブルのデータコード）GI813(代用振替強制通知) 
     */
    public static final String AIO_MRGSEC_TRANS_NOTIFY = "GI813";
    
    /**
     * （代用振替受付キューテーブルのデータコード）GI80G (代用振替請求受付) 
     */
    public static final String AIO_MRGSEC_TRANS_ACCEPT = "GI80G";
    
    /**
     * GI821：顧客登録
     */
    public static final String ACCOPEN_ACCOUNT_REGIST = "GI821";
 
    /**
     * GI827：契約書徴収
     */
    public static final String ACCOPEN_CONTRACT_COLLECT = "GI827";
 
    /**
     * GI823：振替申込（銀行）
     */
    public static final String ACCOPEN_TRANSFER_BANK = "GI823";
 
    /**
     * GI828：振替申込（郵貯）
     */
    public static final String ACCOPEN_TRANSFER_POSTAL = "GI828";
 
    /**
     * GI822：保振同意
     */
    public static final String ACCOPEN_AGREE_TRANSFER = "GI822";
 
    /**
     * GI837：有料情報
     */
    public static final String ACCOPEN_CHARGED_INFO = "GI837";
 
    /**
     * GI825：MRF口座
     */
    public static final String ACCOPEN_MRF_ACCOUNT = "GI825";
 
    /**
     * GI842：暗証番号
     */
    public static final String ACCOPEN_PASSWORD = "GI842";

    /**
     * GI835：特定口座振替強制通知 
     */
    public static final String AIO_SPSEC_TRANS_NOTIFY = "GI835";

    /**
     * GI838：重要事項
     */
    public static final String ACCOPEN_IMPORTANT_ITEM = "GI838";
 
    /**
     * GI839：本人確認
     */
    public static final String ACCOPEN_ID_CONFIRM = "GI839";
 
    /**
     * GI844：取電・電子交付・特定口座
     */
    public static final String ACCOPEN_TRADE_CONDITION = "GI844";
 
    /**
     * GI819：内部者登録伝票
     */
    public static final String ACCOPEN_INSIDER_REG_VOUCHER = "GI819";
    
    /**
     * GI824：GP条件登録伝票
     */
    public static final String ACCOPEN_GP_REG_VOUCHER = "GI824";
    
    /**
     * GI848：顧客正式名称登録伝票
     */
    public static final String ACCOPEN_REALNAME_REG_VOUCHER = "GI848";
    
    /**
     * GI849：上場外株・株主登録伝票
     */
    public static final String ACCOPEN_STOCKHOLDER_REG_VOUCHER = "GI849";
    
    /**
     * GI845：顧客登録（仲介業）
     */
    public static final String ACCOPEN_REG_BROKERAGE = "GI845";
    
    /**
     * GI82A：顧客登録受付
     */
    public static final String ACCOPEN_ACCOUNT_REGIST_ACCEPT = "GI82A";
 
    /**
     * GI82G：契約書徴収受付
     */
    public static final String ACCOPEN_CONTRACT_COLLECT_ACCEPT = "GI82G";
 
    /**
     * GI82C：振替申込（銀行）受付
     */
    public static final String ACCOPEN_TRANSFER_BANK_ACCEPT = "GI82C";
 
    /**
     * GI82H：振替申込（郵貯）受付
     */
    public static final String ACCOPEN_TRANSFER_POSTAL_ACCEPT = "GI82H";
 
    /**
     * GI82B：保振同意受付
     */
    public static final String ACCOPEN_AGREE_TRANSFER_ACCEPT = "GI82B";
 
    /**
     * GI83G：有料情報受付
     */
    public static final String ACCOPEN_CHARGED_INFO_ACCEPT = "GI83G";
 
    /**
     * GI82E：MRF口座受付
     */
    public static final String ACCOPEN_MRF_ACCOUNT_ACCEPT = "GI82E";
    
    /**
     * GI81I：内部者登録受付
     */
    public static final String ACCOPEN_INSIDER_REG_ACCEPT = "GI81I";
    
    /**
     * GI82D：GP条件登録受付
     */
    public static final String ACCOPEN_GP_REG_ACCEPT = "GI82D";
    
    /**
     * GI84I：上場外株・登録受付
     */
    public static final String ACCOPEN_STOCKHOLDER_REG_ACCEPT = "GI84I";
    
    /**
     * GI84H：顧客名称登録受付
     */
    public static final String ACCOPEN_REALNAME_REG_ACCEPT = "GI84H";
    
    /**
     * GI84E：顧客登録（仲介業）受付
     */
    public static final String ACCOPEN_ACC_REG_ACCEPT = "GI84E";

    /**
     * GI851:振替請求伝票
     */
    public static final String AIO_REQUEST_SLIP = "GI851";    

    /**
     * GI852:証券出庫請求伝票
     */
    public static final String SECURITIES_OUT_REQUEST_SLIP = "GI852";    

    /**
     * GI846:ロック客登録解除
     */
    public static final String ACCINFO_LOCK_REGIST_CANCEL = "GI846";

    /**
     * GI847:Y客登録解除
     */
    public static final String ACCINFO_YELLOW_REGIST_CANCEL = "GI847";

    /**
     * GI84F:ロック客登録解除受付
     */
    public static final String ACCINFO_LOCK_REGIST_CANCEL_ACCEPT = "GI84F";

    /**
     * GI84G:Y客登録解除受付
     */
    public static final String ACCINFO_YELLOW_REGIST_CANCEL_ACCEPT = "GI84G";

    /**
     * CI811:国内投信-投信注文通知
     */
    public static final String MUTUAL_FUND_DOMESTIC_ORDER_NOTIFY = "CI811";

    /**
     * CI813:外国投信-投信注文通知
     */
    public static final String MUTUAL_FUND_FOREIGN_ORDER_NOTIFY = "CI813";

    /**
     * CI817：募集-投信注文通知
     */
    public static final String MUTUAL_FUND_RECRUIT_ORDER_NOTIFY = "CI817";
    
    /**
     * GI854：外貨預金口座登録伝票
     */
    public static final String F_DEPOSIT_REG = "GI854";
    
    /**
     * GI853：外貨入出金
     */
    public static final String FOREIGN_CASH_TRANSFER = "GI853";
    
    /**
     * GI804：外貨入出金伝票
     */
    public static final String FOREIGN_CASH_TRANSFER_ORDER = "GI804";

    /**
     * GI80D：外貨入出金伝票受付
     */
    public static final String F_CASH_TRANS_ORDER_ACCEPT = "GI80D";

    /**
     * GI85D：外貨預金口座登録受付
     */
    public static final String F_DEPOSIT_REG_ACCEPT = "GI85D";

    /**
     * DI821：外貨MMF
     */
    public static final String FOREIGN_MMF = "DI821";

    /**
     * GI843:取報・取残電子交付・特定口座登録
     */
    public static final String ACCOPEN_CONDITION_REGIST = "GI843";

    /**
     * GI84C：取報・取残電子交付・特定口座登録受付 
     */
    public static final String ACCOPEN_CONDITION_REG_ACCEPT = "GI84C";

    /**
     * AXQY1:注意情報（売停情報）通知
     */
    public final static String SELL_STOP_INFO = "AXQY1";

    /**
     * AXRY1:注意情報（制限値幅情報）通知
     */
    public final static String LIMIT_RANGE_INFO = "AXRY1";

    /**
     * AXSY1:注意情報（フリーフォーマット）通知
     */
    public final static String FREE_FORMAT = "AXSY1";

    /**
     * GI865：機@構通知情報登録
     */
    public static final String ACCOPEN_AGENCY_INFO_REGIST = "GI865";

    /**
     * GI86E：機@構通知情報登録受付
     */
    public static final String ACCOPEN_AGENCY_INFO_REG_ACCEPT = "GI86E";
}
@
