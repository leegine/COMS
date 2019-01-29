head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSimplexAskingTelegramUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Simplex依頼電文明細(WEB3FXSimplexAskingTelegramUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 張騰宇 (中訊) 新規作成・モデル1201
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Simplex依頼電文明細) <BR>
 * Simplex依頼電文明細クラス<BR>
 * <BR>
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public class WEB3FXSimplexAskingTelegramUnit extends Message
{
    /**
     * (処理区分)<BR>
     * (simplexOperationDiv) <BR>
     * 01：口座開設 <BR>
     * 02：入金 <BR>
     * 04：出金 <BR>
     * 07：振替可能額<BR>
     */
    public String simplexOperationDiv;

    /**
     * (大証FXログインID)<BR>
     * 大証FXログインID <BR>
     * (oseFxLoginId)<BR>
     */
    public String oseFxLoginId;

    /**
     * (顧客名カナ)<BR>
     * 顧客名カナ <BR>
     * (fullNameKana)<BR>
     */
    public String fullNameKana;

    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     * (fullName)<BR>
     */
    public String fullName;

    /**
     * (性別)<BR>
     * 性別<BR>
     * (genderType)<BR>
     */
    public String genderType;

    /**
     * (郵便番号)<BR>
     * 郵便番号<BR>
     * (zipCode)<BR>
     */
    public String zipCode;

    /**
     * (住所１)<BR>
     * 住所１<BR>
     * (address1)<BR>
     */
    public String address1;

    /**
     * (住所２)<BR>
     * 住所２<BR>
     * (address2)<BR>
     */
    public String address2;

    /**
     * (住所３)<BR>
     * 住所３<BR>
     * (address3)<BR>
     */
    public String address3;

    /**
     * (第一メールアドレス)<BR>
     * 第一メールアドレス<BR>
     * (mail1)<BR>
     */
    public String mail1;

    /**
     * (初期ログインパスワード)<BR>
     * 初期ログインパスワード<BR>
     * (initialLoginPassword)<BR>
     */
    public String initialLoginPassword;

    /**
     * (初期取引パスワード)<BR>
     * 初期取引パスワード<BR>
     * (initialTradePassword)<BR>
     */
    public String initialTradePassword;

    /**
     * (初期大証パスワード)<BR>
     * 初期大証パスワード<BR>
     * (initialOsePassword)<BR>
     */
    public String initialOsePassword;

    /**
     * (タイムスタンプ)<BR>
     * タイムスタンプ<BR>
     * (timeStamp)<BR>
     */
    public String timeStamp;

    /**
     * (ハッシュ値)<BR>
     * ハッシュ値<BR>
     * (hashValue)<BR>
     */
    public String hashValue;

    /**
     * (振替ID)<BR>
     * 振替ID<BR>
     * (transferId)<BR>
     */
    public String transferId;

    /**
     * (大証FX口座番号)<BR>
     * 大証FX口座番号<BR>
     * (oseAccountId)<BR>
     */
    public String oseAccountId;

    /**
     * (振替入金額)<BR>
     * 振替入金額<BR>
     * (depositAmount)<BR>
     */
    public String depositAmount;

    /**
     * (振替出金額)<BR>
     * 振替出金額<BR>
     * (withdrawalAmount)<BR>
     */
    public String withdrawalAmount;

    /**
     * コンストラクタ。<BR>
     */
    public WEB3FXSimplexAskingTelegramUnit()
    {

    }
}
@
