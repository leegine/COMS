head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransferDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FX振替明細(WEB3FXTransferDetailUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/08 黄建(中訊) 仕様変更・モデル481
                    2006/07/12 丁昭奎 (中訊) 仕様変更・モデル595,601
 Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.866
*/

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX振替明細) <BR>
 * FX振替明細
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXTransferDetailUnit extends Message
{
    /**
     * (選択可能フラグ) <BR>
     * 選択可能の場合、true。以外、false。
     */
    public boolean selectableFlag;

    /**
     * (振替区分) <BR>
     * 1：入金（FX）<BR>
     * 2：出金（FX）<BR>
     * 3：出金（先OP）<BR>
     * 4：入金（先OP）<BR>
     */
    public String fxTransferDiv;

    /**
     * (部店コード) <BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (顧客コード) <BR>
     * 顧客コード
     */
    public String accountCode;

    /**
     * (顧客名) <BR>
     * 顧客名
     */
    public String accountName;

    /**
     * (識別コード) <BR>
     * 識別コード
     */
    public String requestNumber;

    /**
     * (受付日時) <BR>
     * 受付日時 <BR>
     * YYYYMMDDHHMMSS
     */
    public Date receiptTime;

    /**
     * (振替日) <BR>
     * 振替日 <BR>
     * YYYYMMDD
     */
    public Date transferDate;

    /**
     * (振替金額) <BR>
     * 振替金額
     */
    public String transferAmount;

    /**
     * (（FX）口座番号) <BR>
     * 振替が行われた為替保証金取引口座番号
     */
    public String fxAccountCode;

    /**
     * (（FX）コース区分) <BR>
     * 振替が行われた為替保証金取引コース区分 <BR>
     * <BR>
     * 1： 1万通貨コース <BR>
     * 2： 10万通貨コース
     */
    public String fxCourseDiv;

    /**
     * (FXシステム受付日時) <BR>
     * FXシステム受付日時 <BR>
     * YYYYMMDDHHMMSS
     */
    public String fxReceiptTime;

    /**
     * (ステータス区分) <BR>
     * 0：決済中 <BR>
     * 1：決済完了 <BR>
     * 2：決済エラー <BR>
     * 3：取消
     */
    public String statusDiv;

    /**
     * (メッセージ) <BR>
     * 10000000：決済中 <BR>
     * 90000000：取消完了 <BR>
     * 99999999：決済失敗（システムエラー） <BR>
     * 00000000：決済完了 <BR>
     * 00000105：GFT受付時間外エラー <BR>
     * 00000199：GFTシステム起因エラー <BR>
     * 00000204：残高不足エラー <BR>
     * 00000299：ユーザー起因エラー <BR>
     * 00000501：該当保証金口座無しエラー <BR>
     * 00000502：入出金金額エラー <BR>
     * 00000601：電文書式エラー（必須項目未入力） <BR>
     * 00000602：電文書式エラー（不正文字使用） <BR>
     * 00000603：電文書式エラー（桁数不備） <BR>
     * 00000609：電文書式起因エラー <BR>
     * 00000701：処理区分エラー <BR>
     * 00000801：2重処理エラー <BR>
     * 00000901：GFTシステムエラー <BR>
     * 00000909：ハッシュ値エラー <BR>
     * 00000910：タイムアウトエラー <BR>
     * 90000009:口座抹消<BR>
     * 00000911：受渡日エラー<BR>
     */
    public String fxMessage;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * (FX振替明細) <BR>
     * コンストラクタ。
     * 
     * @@roseuid 41B456EF0195
     */
    public WEB3FXTransferDetailUnit()
    {
    }
}@
