head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設アップロード履歴明細(WEB3AccOpenUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 武波 (中訊) 新規作成 モデル No.147
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (口座開設アップロード履歴明細)<BR>
 * 口座開設アップロード履歴明細<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccOpenUploadHistoryUnit extends Message
{

    /**
     * (アップロード処理状態区分)<BR>
     * アップロード処理状態区分<BR>
     * <BR>
     * 0：　@アップロード待ち<BR>
     * 1：　@アップロード中<BR>
     * 2：　@アップロード済<BR>
     */
    public String uploadStateDiv;

    /**
     * (アップロード件数)<BR>
     * アップロード件数<BR>
     */
    public String uploadNumber;

    /**
     * (アップロード開始日時)<BR>
     * アップロード開始日時<BR>
     */
    public Date uploadStartDate;

    /**
     * (アップロード終了日時)<BR>
     * アップロード終了日時<BR>
     */
    public Date uploadEndDate;

    /**
     * (口座開設エラー番号)<BR>
     * 口座開設エラー番号<BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK<BR>
     */
    public String accOpenErrorId;

    /**
     * (口座開設アップロード履歴明細)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47311CBE002E
     */
    public WEB3AccOpenUploadHistoryUnit()
    {

    }
}
@
