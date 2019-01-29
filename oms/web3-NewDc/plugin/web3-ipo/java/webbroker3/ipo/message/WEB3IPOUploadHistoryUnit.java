head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOアップロード履歴明細メッセージデータ(WEB3IPOUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPOアップロード履歴明細メッセージデータクラス
 *                                                                    
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOUploadHistoryUnit extends Message
{
    
    /**
     * 抽選区分<BR>
     * <BR>
     * 1：　@新規抽選<BR>
     * 2：　@繰上抽選<BR>
     */
    public String lotDiv;
    
    /**
     * アップロード処理状態区分<BR>
     * <BR>
     * 0：　@アップロード待ち<BR>
     * 1：　@アップロード中<BR>
     * 2：　@アップロード済
     */
    public String uploadStateDiv;
    
    /**
     * アップロード件数
     */
    public String uploadNumber;
    
    /**
     * アップロード開始日時
     */
    public Date uploadStartDate;
    
    /**
     * アップロード終了日時
     */
    public Date uploadEndDate;
    
    /**
     * IPOエラー番号<BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK
     */
    public String ipoErrorId;
    
    /**
     * @@roseuid 4112E4E1023C
     */
    public WEB3IPOUploadHistoryUnit() 
    {
     
    }
}
@
