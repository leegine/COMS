head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細(WEB3PvInfoUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細クラス<BR>
 * 
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoUploadHistoryUnit extends Message 
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
     */
    public String uploadNumber;
    
    /**
     * (アップロード開始日時)<BR>
     */
    public Date uploadStartDate;
    
    /**
     * (アップロード終了日時)<BR>
     */
    public Date uploadEndDate;
    
    /**
     * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝエラー番号 )<BR>
     * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝエラー番号 <BR>
     * <BR>
     * ※ ErrorMessageテーブルのPK <BR>
     */
    public String pvInfoErrorId;
    
    /**
     * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝアップロード履歴明細)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoUploadHistoryUnit
     * @@roseuid 4160AF08025D
     */
    public WEB3PvInfoUploadHistoryUnit() 
    {
     
    }
}
@
