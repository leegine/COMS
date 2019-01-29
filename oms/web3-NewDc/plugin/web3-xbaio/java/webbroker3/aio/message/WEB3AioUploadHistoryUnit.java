head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金UL履歴明細(WEB3AioUploadHistoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 李小健 (中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (バーチャル口座入金UL履歴明細)<BR>
 * バーチャル口座入金UL履歴明細クラス
 * 
 * @@author 李小健(中訊)
 * @@version 1.0 
 */

public class WEB3AioUploadHistoryUnit extends Message
{
    /**
     * @@roseuid 4158EB330171
     */
    public WEB3AioUploadHistoryUnit()
    {
    }
    
    /**
     * (アップロード処理状態区分)<BR>
     * アップロード処理状態区分<BR>
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
     * (バーチャル口座入金エラー番号)<BR>
     * バーチャル口座入金エラー番号<BR>
     */
    public String aioErrorId;
}
@
