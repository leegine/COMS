head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointApplyDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント申込明細(WEB3AdminPointApplyDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (ポイント申込明細)<BR>
 * ポイント申込明細クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointApplyDetail extends Message
{
    
    /**
     * (申込ID)<BR>
     * 申込ID<BR>
     */
    public String applyId;
    
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
     * (景品番号)<BR>
     * 景品番号<BR>
     */
    public String premiumNo;
    
    /**
     * (景品名)<BR>
     * 景品名<BR>
     */
    public String premiumName;
    
    /**
     * (申込日時)<BR>
     * 申込日時<BR>
     */
    public Date applyDate;
    
    /**
     * (受付区分)<BR>
     * 受付区分<BR>
     * <BR>
     * 0： 受付未済<BR>
     * 1： 受付済み<BR>
     */
    public String acceptDiv;
    
    /**
     * (更新日時)<BR>
     * 更新日時<BR>
     */
    public Date updateTimeStamp;
    
    /**
     * (受付ユーザ)<BR>
     * 受付ユーザID<BR>
     */
    public String acceptUser;
    
    /**
     * (取消区分)<BR>
     * 取消区分<BR>
     * <BR>
     * 0： 取消未済<BR>
     * 1： 取消済み<BR>
     */
    public String cancelDiv;
    
    /**
     * (ポイント申込明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 418F497B0165
     */
    public WEB3AdminPointApplyDetail() 
    {
     
    }
}
@
