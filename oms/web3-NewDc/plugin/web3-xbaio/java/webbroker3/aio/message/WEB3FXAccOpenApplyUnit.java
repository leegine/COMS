head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenApplyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FX口座開設申込明細(WEB3FXAccOpenApplyUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/08 黄建(中訊) 仕様変更・モデル481
                    2006/02/09 余新敏(中訊) 仕様変更・モデル458
                    2006/02/09 鄭徳懇(中訊) 仕様変更・モデル475
                    2006/02/22 情野(SRA) 仕様変更・モデル500
 Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.866
 */

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX口座開設申込明細) <BR>
 * FX口座開設申込明細
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXAccOpenApplyUnit extends Message
{
    /**
     * (選択可能フラグ) <BR>
     * 選択可能の場合、true。以外、false。
     */
    public boolean selectableFlag;

    /**
     * (識別コード) <BR>
     * 識別コード
     */
    public String requestNumber;

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
     * (申込日時) <BR>
     * YYYYMMDDHHMMSS
     */
    public Date applyTime;

    /**
     * (ステータス区分) <BR>
     * 0：口座開設中 <BR>
     * 1：口座開設完了 <BR>
     * 2：口座開設エラー <BR>
     * 3：ダウンロード済 <BR>
     * 9：削除
     */
    public String statusDiv;

    /**
     * (送受信区分) <BR>
     * 0：送信未済 <BR>
     * 1：送信済
     */
    public String sendRcvDiv;

    /**
     * (（FX）名前（姓）) <BR>
     * 為替保証金取引用の名前（姓）
     */
    public String fxLastName;

    /**
     * (（FX）名前（名）) <BR>
     * 為替保証金取引用の名前（名）
     */
    public String fxFirstName;

    /**
     * (（FX）ログインID) <BR>
     * 為替保証金取引用のログインID
     */
    public String fxLoginId;

    /**
     * (（FX）メールアドレス) <BR>
     * 為替保証金取引用のメールアドレス
     */
    public String fxMailAddress;

    /**
     * (FX口座情報一覧) <BR>
     * FX口座情報の一覧 <BR>
     * <BR>
     * 口座開設状況区分＝0(口座開設中)または2(口座開設エラー）の場合はnull
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (備考) <BR>
     * 10000000：口座開設中 <BR>
     * 90000000：削除 <BR>
     * 99999999：システムエラー <BR>
     * 00000000：口座開設完了 <BR>
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
     * 90000009:口座抹消
     */
    public String fxRemark;

    /**
     * (MRF残高フラグ) <BR>
     * MRF残高ありの場合、true。以外、false。
     */
    public boolean mrfBalanceFlag;

    /**
     * (MRF口座フラグ) <BR>
     * MRF口座ありの場合、true。以外、false。
     */
    public boolean mrfAccountFlag;

    /**
     * (FX取引同意質問情報一覧) <BR>
     * FX取引同意質問情報の一覧
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;
    
    /**
     * (約諾書区分)<BR>
     * 0：未送信<BR>
     * 1：送信済<BR>
     * 2：受領済<BR>
     */
    public String agreementDiv;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * (FX口座開設申込明細) <BR>
     * コンストラクタ。
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3FXAccOpenApplyUnit()
    {
    }
}@
