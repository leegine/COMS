head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiUploadHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : サービス利用アップロード履歴明細(WEB3SrvRegiUploadHistoryInfoUnit.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (サービス利用アップロード履歴明細)<BR>
 * サービス利用アップロード履歴明細<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3SrvRegiUploadHistoryInfoUnit extends Message
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
     * (サービス利用エラー番号)<BR>
     * お客様情報エラー番号<BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK<BR>
     */
    public String srvRegiErrorId;

    /**
     * (サービス利用アップロード履歴明細)<BR>
     * @@roseuid 47B8D9EC01BA
     */
    public WEB3SrvRegiUploadHistoryInfoUnit()
    {

    }
}
@
