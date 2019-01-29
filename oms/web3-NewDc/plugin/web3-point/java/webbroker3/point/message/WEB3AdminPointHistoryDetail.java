head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointHistoryDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント履歴明細(WEB3AdminPointHistoryDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (ポイント履歴明細)<BR>
 * ポイント履歴明細クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointHistoryDetail extends Message
{
    
    /**
     * (年月)<BR>
     * 年月<BR>
     */
    public String period;
    
    /**
     * (発生ポイント)<BR>
     * 発生ポイント<BR>
     */
    public String accrualPoint;
    
    /**
     * (利用ポイント)<BR>
     * 利用ポイント<BR>
     */
    public String usedPoint;
    
    /**
     * (調整ポイント)<BR>
     * 調整ポイント<BR>
     */
    public String adjustPoint;
    
    /**
     * (ポイント履歴明細)<BR>
     * コンストラクタ <BR>
     * @@roseuid 41D1283B0261
     */
    public WEB3AdminPointHistoryDetail() 
    {
     
    }
}
@
