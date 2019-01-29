head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXGftResultNoticeTelegramUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : GFT結果通知電文明細(WEB3FXGftResultNoticeTelegramUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
                 : 2006/04/26 李小健 (中訊) 仕様変更・モデル533
                 : 2006/07/13 韋念瓊 (中訊) 仕様変更・モデル601
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデル993,1013,1039,1040
Revesion History : 2009/03/19 車進 (中訊) 仕様変更・モデル1129,1154
Revesion History : 2009/06/25 張騰宇 (中訊) 仕様変更・モデル1167
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (GFT結果通知電文明細) <BR>
 * GFT結果通知電文の明細 <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXGftResultNoticeTelegramUnit extends Message
{    
    /**
     * (DIR→GFT送信日時) <BR>
     * YYYYMMDDHHMMSS <BR>
     */
    public String dirSendTime;

    /**
     * (処理区分) <BR>
     * <BR>
     * 01：口座開設 <BR>
     * 02：入金 <BR>
     * 03：口座追加<BR>
     * 04：出金 <BR>
     * 07：振替可能額<BR>
     */
    public String gftOperationDiv;

    /**
     * (為替保証金口座番号) <BR>
     * 為替保証金口座番号 <BR>
     * <BR>
     * 処理区分＝04(出金)または02(入金)の場合、設定 <BR>
     */
    public String fxAccountCode;

    /**
     * (メールアドレス) <BR>
     * メールアドレス <BR>
     * <BR>
     * 処理区分＝01(口座開設)の場合、設定 <BR>
     */
    public String fxMailAddress;

    /**
     * (初期ログインID) <BR>
     * 初期ログインID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String fxFirstLoginId;

    /**
     * (初期パスワード) <BR>
     * 初期パスワード <BR>
     * <BR>
     * 処理区分＝01(口座開設)の場合、設定 <BR>
     */
    public String fxFirstPassword;

    /**
     * (担当区分) <BR>
     * 担当区分 <BR>
     */
    public String groupName;

    /**
     * (入出金額) <BR>
     * 入出金額 <BR>
     * <BR>
     * 処理区分＝04(出金)または02(入金)の場合、設定 <BR>
     */
    public String cashinoutAmt;

    /**
     * (WOLFセッションキー) <BR>
     * WOLFセッションキー <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String wolfSession;

    /**
     * (アプリケーションID) <BR>
     * アプリケーションID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String wolfAid;

    /**
     * (再生成サービスID) <BR>
     * 再生成サービスID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String regetServiceId;

    /**
     * (SSID) <BR>
     * SSID <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String wolfSsid;

    /**
     * (会社コード) <BR>
     * 会社コード <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String institutionCode;

    /**
     * (部店コード) <BR>
     * 部店コード <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String branchCode;

    /**
     * (顧客コード) <BR>
     * 顧客コード <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String accountCode;

    /**
     * (識別コード） <BR>
     * 識別コード <BR>
     * （WEB3にて独自に設定している項目） <BR>
     */
    public String requestNumber;

    /**
     * (受付結果) <BR>
     * 受付結果 <BR>
     */
    public String resultCode;

    /**
     * (GFT→DIR送信日時) <BR>
     * YYYYYMMDDHHMMSS <BR>
     */
    public String gftSendTime;

    /**
     * (為替保証金口座番号（1万通貨）) <BR>
     * 為替保証金口座番号（1万通貨） <BR>
     * <BR>
     * 処理区分＝01(口座開設)の場合、設定 <BR>
     */
    public String gftAcc1;

    /**
     * (為替保証金口座番号（10万通貨）) <BR>
     * 為替保証金口座番号（10万通貨） <BR>
     * <BR>
     * 処理区分＝01(口座開設)の場合、設定 <BR>
     */
    public String gftAcc2;

    /**
     * (名前（姓）) <BR>
     * 名前（姓） <BR>
     * <BR>
     * 処理区分＝01(口座開設)の場合、設定 <BR>
     */
    public String fxLastName;

    /**
     * (名前（名）) <BR>
     * 名前（名） <BR>
     * <BR>
     * 処理区分＝01(口座開設)の場合、設定 <BR>
     */
    public String fxFirstName;

    /**
     * (ハッシュ値) <BR>
     * ハッシュ値 <BR>
     */
    public String hashValue;
    
    /**
     * (受渡日) <BR>
     * 受渡日（YYYYYMMDD）<BR>
     */
    public String deliveryDate;
    
    /**
     * (入出金額2) <BR>
     * 入出金額2<BR>
     */
    public String cashinoutAmt2;

    /**
     * (為替保証金口座情報一覧)<BR>
     * 為替保証金口座情報の一覧<BR>
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (GFT結果通知電文明細) <BR>
     * コンストラクタ。 <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit
     * @@roseuid 41B038EB0156
     */
    public WEB3FXGftResultNoticeTelegramUnit()
    {
    }
}@
