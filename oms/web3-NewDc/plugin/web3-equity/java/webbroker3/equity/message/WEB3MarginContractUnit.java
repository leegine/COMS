head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引建株明細(WEB3MarginContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引建株明細）。<br>
 * <br>
 * 信用取引建株明細クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginContractUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * 建株ＩＤ
     */
    public String id;
    
    /**
     * (建日)
     */
    public Date openDate;
    
    /**
     * (建単価)
     */
    public String contractPrice;
    
    /**
     * (建株数)
     */
    public String contractQuantity;
    
    /**
     * (建代金)
     */
    public String contractExecPrice;
    
    /**
     * (評価損益)<BR>
     */
    public String appraisalProfitLoss;
    
    /**
     * (注文株数)<BR>
     * <BR>
     * 一括決済の場合設定<BR>
     */
    public String orderQuantity;
    
    /**
     * (内出来株数)<BR>
     * <BR>
     * 約定がある場合設定<BR>
     */
    public String partContQuantity;
    
    /**
     * (決済順位)<BR>
     * <BR>
     * 一括決済の場合設定<BR>
     */
    public String settlePriority;
    
    /**
     * (信用取引建株明細)<BR>
     * コンストラクタ。
     * @@return webbroker3.margin.message.WEB3MarginContractUnit
     * @@roseuid 40B6DFDD02CE
     */
    public WEB3MarginContractUnit() 
    {
     
    }
}
@
