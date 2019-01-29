head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.45.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3GftTelegramFormatDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GftTelegramFormatDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/26 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * GFT電文formatのキー　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3GftTelegramFormatDef
{
    /**
     * DIR→GFT送信日時   DIR_sendTime
     */
    public static final String DIR_sendTime = "DIR_sendTime";

    /**
     * 処理区分 Operation
     */
    public static final String Operation = "Operation";

    /**
     * 為替保証金口座番号   Account
     */
    public static final String Account = "Account";

    /**
     * メールアドレス  email
     */
    public static final String email = "email";

    /**
     * 初期ログインID    GFT_link_1
     */
    public static final String GFT_link_1 = "GFT_link_1";

    /**
     * 初期パスワード   GFT_link_2
     */
    public static final String GFT_link_2 = "GFT_link_2";

    /**
     * 担当区分 Group_name
     */
    public static final String Group_name = "Group_name";

    /**
     * 入出金額 Amount
     */
    public static final String Amount = "Amount";

    /**
     * WOLFセッションキー  wolfSessionKey
     */
    public static final String wolfSessionKey = "wolfSessionKey";

    /**
     * アプリケーションID aa_aid
     */
    public static final String aa_aid = "aa_aid";

    /**
     * 再生成サービスID 
     */
    public static final String aa_rsid = "aa_rsid";

    /**
     * SSID ssid
     */
    public static final String ssid = "ssid";
    
    /**
     * 証券会社コード  cpy
     */
    public static final String cpy = "cpy";

    /**
     * 部店コード    brn
     */
    public static final String brn = "brn";

    /**
     * 顧客コード    acc
     */
    public static final String acc = "acc";

    /**
     * 識別コード    req
     */
    public static final String req = "req";

    /**
     * 受付結果  resultCode
     */
    public static final String resultCode = "resultCode";

    /**
     * GFT→DIR送信日時 GFT_sendTime
     */
    public static final String GFT_sendTime = "GFT_sendTime";

    /**
     * 為替保証金口座番号（1万通貨) GFT_ac1
     */
    public static final String GFT_ac1 = "GFT_ac1";

    /**
     * 為替保証金口座番号(10万通貨)  GFT_ac2
     */
    public static final String GFT_ac2 = "GFT_ac2";

    /**
     * 名前（姓） Last_name
     */
    public static final String Last_name = "Last_name";

    /**
     * 名前（名） First_name
     */
    public static final String First_name = "First_name";

    /**
     * ハッシュ値  hashKey
     */
    public static final String hashKey = "hashKey";
    
    /**
     * 暗号化文字列  encryptedData
     */
    public static final String encryptedData = "encryptedData";
    
    /**
     * 秘密鍵  secretKey
     */
    public static final String secretKey = "secretKey";
}
@
