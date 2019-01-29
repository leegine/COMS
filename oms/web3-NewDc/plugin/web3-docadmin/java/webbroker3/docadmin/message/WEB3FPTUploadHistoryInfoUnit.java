head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTUploadHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金商法@交付閲覧アップロード履歴明細(WEB3FPTUploadHistoryInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.013,No.014
*/
package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (金商法@交付閲覧アップロード履歴明細)<BR>
 * 金商法@交付閲覧アップロード履歴明細<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3FPTUploadHistoryInfoUnit extends Message
{

    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0： 登録<BR>
     * 1： 削除<BR>
     */
    public String statusDiv;

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
     * (金商法@交付閲覧エラー番号)<BR>
     * 金商法@交付閲覧エラー番号<BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK<BR>
     */
    public String fptErrorId;

    /**
     * (金商法@交付閲覧アップロード履歴明細)<BR>
     * ディフォルトコンストラクタ<BR>
     */
    public WEB3FPTUploadHistoryInfoUnit()
    {

    }
}
@
