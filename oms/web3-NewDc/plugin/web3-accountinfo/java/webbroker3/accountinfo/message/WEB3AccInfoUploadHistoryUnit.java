head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報アップロード履歴明細(WEB3AccInfoUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (お客様情報アップロード履歴明細)<BR>
 * お客様情報アップロード履歴明細<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoUploadHistoryUnit extends Message 
{
    
    /**
     * (アップロード処理状態区分)<BR>
     * アップロード処理状態区分 <BR>
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
     * (お客様情報エラー番号)<BR>
     * お客様情報エラー番号<BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK<BR>
     */
    public String accInfoErrorId;
    
    /**
     * (お客様情報アップロード履歴明細)<BR>
     * デフォルトコンストラクタ<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoUploadHistoryUnit
     * @@roseuid 415BCF700005
     */
    public WEB3AccInfoUploadHistoryUnit() 
    {
     
    }
}
@
