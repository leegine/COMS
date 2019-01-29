head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXアップロード履歴明細(WEB3FXUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FXアップロード履歴明細)<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3FXUploadHistoryUnit extends Message 
{
    
    /**
     * (アップロード処理状態区分)<BR>
     * アップロード処理状態区分<BR>
     * <BR>
     * 0：　@アップロード待ち <BR>
     * 1：　@アップロード中 <BR>
     * 2：　@アップロード済 <BR>
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
     * (アップロードエラー番号)<BR>
     * アップロードエラー番号 <BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK <BR>
     */
    public String fxErrorId;
    
    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 43C61CC10332
     */
    public WEB3FXUploadHistoryUnit() 
    {
     
    }
}
@
