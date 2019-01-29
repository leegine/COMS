head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡明細(WEB3AioCashinNoticeUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/
package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (入金連絡明細)<BR>
 * 入金連絡明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashinNoticeUnit extends Message
{
    
    /**
     * (部店コード)<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード（口座番号）<BR>
     */
    public String accountCode;
    
    /**
     * (顧客名（カナ）)<BR>
     */
    public String accountName;
    
    /**
     * (入金額)<BR>
     */
    public String cashinAmt;
    
    /**
     * (振込先金融機@関名称)<BR>
     */
    public String finInstitutionName;
    
    /**
     * (振込日)<BR>
     */
    public Date transferDate;
    
    /**
     * (連絡日時)<BR>
     */
    public Date noticeDate;
    
    /**
     * (識別コード)<BR>
     */
    public String orderRequestNumber;
    
    /**
     * (入金連絡明細)<BR>
     * @@return webbroker3.aio.message.WEB3AioCashinNoticeUnit
     * @@roseuid 40E284450344
     */
    public WEB3AioCashinNoticeUnit() 
    {
     
    }
}
@
