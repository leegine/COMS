head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccVoucherRecordDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客情報登録伝票レコード詳細(WEB3AdminDirSecAccVoucherRecordDetail.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
*/

package webbroker3.dirsec.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (顧客情報登録伝票レコード詳細)<BR>
 * 顧客情報登録伝票レコード詳細クラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccVoucherRecordDetail extends Message
{
    /**
     * (証券会社コード )<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (データコード)<BR>
     * データコード<BR>
     */
    public String dataCode;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (伝票作成状況)<BR>
     * 伝票作成状況<BR>
     * <BR>
     * 各種連絡テーブルレコードの場合<BR>
     * 0：未作成<BR>
     * 1：作成済<BR>
     * 2：受付中<BR>
     * 3：受付完了<BR>
     * 4：受付エラー<BR>
     * <BR>
     * 口座開設伝票作成ステータスレコードの場合<BR>
     * 0：伝票未作成<BR>
     * 1：作成済<BR>
     * 2：送信保留中<BR>
     * 3：送信済<BR>
     * 4：受信済<BR>
     * 5：送信エラー<BR>
     * 6：受信エラー<BR>
     */
    public String voucherMakeStatus;

    /**
     * (エラー理由コード)<BR>
     * エラー理由コード<BR>
     */
    public String errorReasonCode;

    /**
     * (伝票送信日時)<BR>
     * 伝票送信日時<BR>
     */
    public Date voucherSendTimestamp;

    /**
     * (伝票受信日時)<BR>
     * 伝票受信日時<BR>
     */
    public Date voucherRecvTimestamp;

    /**
     * (口座開設伝票フラグ)<BR>
     * 口座開設伝票フラグ<BR>
     * <BR>
     * TRUE  ：口座開設伝票作成ステータスレコード<BR>
     * FALSE ：各種連絡テーブルレコード<BR>
     */
    public boolean voucherFlag;

    /**
     * (連絡種別)<BR>
     * 連絡種別。<BR>
     */
    public String infoType;

    /**
     * (伝票通番)<BR>
     * 伝票通番。<BR>
     */
    public String voucherNumber;

    /**
     * @@roseuid 466E27F10126
     */
    public WEB3AdminDirSecAccVoucherRecordDetail()
    {

    }
}
@
