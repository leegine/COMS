head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSettleReportUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポート明細クラス(WEB3AioSettleReportUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2006/04/14 李小健 仕様変更・モデル 531
*/

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (決済連携レポート明細)<BR>
 * 決済連携レポート明細クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */
public class WEB3AioSettleReportUnit extends Message
{    
    /**
     * (顧客コード)<BR>
     * 顧客の顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客の名称（漢字）
     */
    public String accountName;
    
    /**
     * (.comデビット決済取引番号)<BR>
     * 決済取引番号
     */
    public String comDebitNumber;
    
    /**
     * (加盟店注文番号)<BR>
     * 加盟店の注文番号
     * （会社コード＋部店コード＋識別コード）
     */
    public String shopOrderId;
    
    /**
     * (受付日時)<BR>
     * 注文の受付日時
     */
    public Date receptionDate;
    
    /**
     * (戻り日時)<BR>
     * 注文の戻り日時
     */
    public Date returnDate;
    
    /**
     * (受渡日)<BR>
     * 注文の受渡日
     */
    public Date deliveryDate;
    
    /**
     * (振込予定日)<BR>
     * 注文の振込予定日
     */
    public Date transScheduledDate;
    
    /**
     * (金額)<BR>
     * 入金額
     */
    public String amount;
    
    /**
     * (処理状態)<BR>
     * <BR>
     * ０：未処理<BR>
     * １：決済完了<BR>
     * ２：決済中止<BR>
     * ３：エラー<BR>
     */
    public String transactionStatus;
    
    /**
     * (メッセージコード)<BR>
     * オンライン決済の処理状況<BR>
     * <BR>
     * A： 決済中<BR>
     * B： データ通信エラー（金融機@関決済開始後）<BR>
     * C： データ通信エラー（金融機@関決済開始前）<BR>
     * D： 決済受付<BR>
     * E： 決済完了<BR>
     * F： 決済エラー<BR>
     * G： 金融機@関決済中止<BR>
     * H： 金融機@関決済結果エラー<BR>
     * I： 取消済み<BR>
     * J： 決済失敗（システムエラー）<BR>
     * K： 決済受付（ただし買付余力には加算されず）<BR>
     * L： 決済完了（ただし買付余力には加算されず）<BR>
     * M： 決済エラー<BR>
     * N： セッションアウトエラー（金融機@関決済完了）<BR>
     * O： セッションアウトエラー（金融機@関決済失敗）<BR>
     * P： 決済中<BR>
     * V： 取消中<BR>
     * W： 取消不可<BR>
     * X： 取消失敗<BR>
     * ” ”： NULL<BR>
     * <BR>
     */
    public String messageCode;
    
    /**
     * (入力区分)<BR>
     * 入力区分<BR>
     * null：　@PC<BR>
     * 1：　@コールセンター <BR>
     * 2：　@i-mode<BR>
     * 3：　@スリングショット<BR>
     * 4：　@vodafone<BR>
     * 5：　@au <BR>
     * 9：　@HOST<BR>
     */
    public String inputDiv;
    
    /**
     * (決済連携レポート明細)<BR>
     * コンストラクタ
     * @@return webbroker3.aio.message.WEB3AioSettleReportUnit
     * @@roseuid 40E533FD014A
     */
    public WEB3AioSettleReportUnit()
    {
     
    }
}
@
