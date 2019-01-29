head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTelegramFormatDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTelegramFormatDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 王蘭芬 (中訊) 新規作成
                   2006/04/14 肖志偉 (中訊)　@仕様変更・モデル527
*/
package webbroker3.aio.define;

/**
 * 注文情報要求fromatと決済開始要求format
 * と決済結果通知formatのキー　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTelegramFormatDef 
{
    /**
     * プロトコルバーション   protocolVersion
     */
    public static final String protocolVersion = "protocolVersion";

    /**
     * 注文日時 date
     */
    public static final String date = "date";

    /**
     * .comデビット決済取引番号   centerPayId
     */
    public static final String centerPayId = "centerPayId";

    /**
     * リターンコード  returnCode
     */
    public static final String returnCode = "returnCode";

    /**
     * 支払い区分    ComOndebiPayMode
     */
    public static final String ComOndebiPayMode = "ComOndebiPayMode";

    /**
     * 代表商品名称   ComOndebiTypicalGoodsName
     */
    public static final String ComOndebiTypicalGoodsName = "ComOndebiTypicalGoodsName";

    /**
     * 取引金額 amount
     */
    public static final String amount = "amount";

    /**
     * 税、送料 ComOndebiTax
     */
    public static final String ComOndebiTax = "ComOndebiTax";

    /**
     * エラー表示内容  description
     */
    public static final String description = "description";

    /**
     * キャンセルURL cancelURL
     */
    public static final String cancelURL = "cancelURL";

    /**
     * エラーURL   errorURL
     */
    public static final String errorURL = "errorURL";

    // =========remain zhou-yong NO.1 begin ========
    /**
     * PR層セッションキー   prsid
     */
    public static final String prsid = "prsid";
    
    /**
     * PR層アプリケーションID   praid
     */
    public static final String praid = "praid";
    
    /**
     * PR層再生成サービスID   praarsid
     */
    public static final String praarsid = "praarsid";
    
    /**
     * PR層SSID  prssid
     */
    public static final String prssid = "prssid";
    
    /**
     * PR層DPDV  prdpdv
     */
    public static final String prdpdv = "prdpdv";
    
    /**
     * io_rturl
     */
    public static final String io_rturl = "io_rturl";
    
    /**
     * aa_aid
     */
    public static final String aa_aid = "aa_aid";

    /**
     * aa_rsid
     */
    public static final String aa_rsid = "aa_rsid";

    /**
     * ssid
     */
    public static final String ssid = "ssid";
    
    /**
     * prdpdv
     */
    public static final String aa_dpdv = "aa_dpdv";
    
    // =========remain zhou-yong NO.1 end ========
    
    /**
     * PR層セッションID   wolfSessionKey
     */
    public static final String wolfSessionKey = "wolfSessionKey";

    /**
     * AP層セッションID   apsid
     */
    public static final String apsid = "apsid";

    /**
     * 証券会社コード  cpy
     */
    public static final String cpy = "cpy";

    /**
     * 部店コード    btn
     */
    public static final String btn = "btn";

    /**
     * 顧客コード    acc
     */
    public static final String acc = "acc";
    
    /**
     * 識別コード    req
     */
    public static final String req = "req";
    
    /**
     * 注文経路区分    rdiv
     */
    public static final String rdiv = "rdiv";

    /**
     * WEB3リクエストコード web3Request
     */
    public static final String web3Request = "web3Request";

    /**
     * 注文番号 linked_1
     */
    public static final String linked_1 = "linked_1";

    /**
     * 加盟店ID    shopId
     */
    public static final String shopId = "shopId";

    /**
     * 決済機@関ID   paySchemeId
     */
    public static final String paySchemeId = "paySchemeId";

    /**
     * アクセスキー   accessKey
     */
    public static final String accessKey = "accessKey";

    /**
     * 決済機@関ID   pSId
     */
    public static final String pSId = "pSId";

    /**
     * リターンURL  returnURL
     */
    public static final String returnURL = "returnURL";

    /**
     * PF側処理通番  ComOndebiSalesSlip
     */
    public static final String ComOndebiSalesSlip = "ComOndebiSalesSlip";

    /**
     * PF内処理日時  ComOndebiAuthDate
     */
    public static final String ComOndebiAuthDate = "ComOndebiAuthDate";

    /**
     * 処理状態 payStatus
     */
    public static final String payStatus = "payStatus";

    /**
     * 承認番号 ComOndebiAuthresCode
     */
    public static final String ComOndebiAuthresCode = "ComOndebiAuthresCode";

    /**
     * 振込入金予定日  ComOndebiCaptureDate
     */
    public static final String ComOndebiCaptureDate = "ComOndebiCaptureDate";

    /**
     * リモートアドレス remoteAdd
     */
    public static final String remoteAdd = "remoteAdd";
}
@
