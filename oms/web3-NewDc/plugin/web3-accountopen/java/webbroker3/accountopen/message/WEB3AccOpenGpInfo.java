head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.02.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenGpInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : クラス GP情報(WEB3AccOpenGpInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/13 柴雙紅(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (クラス GP情報 )<BR>
 * クラス GP情報 <BR>
 *   
 * @@author 柴雙紅
 * @@version 1.0
 */
public class WEB3AccOpenGpInfo extends Message
{
    
    /**
     * (作成区分)<BR>
     * 作成区分<BR>
     */    
    public String createDiv;
    
    /**
     * (コース)<BR>
     * コース<BR>
     */ 
    public String course;
    
    /**
     * (プラン)<BR>
     * プラン<BR>
     */ 
    public String plan;
    
    /**
     * (目標額)<BR>
     * 目標額<BR>
     */ 
    public String targetFigure;
    
    /**
     * (目標年)<BR>
     * 目標年<BR>
     */ 
    public String targetYear;
    
    /**
     * (目標月)<BR>
     * 目標月<BR>
     */ 
    public String targetMonth;
    
    /**
     * (積立額)<BR>
     * 積立額<BR>
     */ 
    public String installmentFigure;
    
    /**
     * (入金周期)<BR>
     * 入金周期<BR>
     */ 
    public String depositCycle;
    
    /**
     * (受渡経路)<BR>
     * 受渡経路<BR>
     */ 
    public String paymentRoot;
    
    /**
     * (再投資区分)<BR>
     * 再投資区分<BR>
     */ 
    public String reinvestDiv;
    
    /**
     * (税区分)<BR>
     * 税区分<BR>
     */ 
    public String taxType;
    
    /**
     * ((優)限度額)<BR>
     * (優)限度額<BR>
     */ 
    public String taxfreeLimit;
    
    /**
     * ((特優)限度額)<BR>
     * (特優)限度額<BR>
     */ 
    public String specialTaxfreeLimit;
    
    /**
     * (加入摘要)<BR>
     * 加入摘要<BR>
     */ 
    public String subscrSummary;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */ 
    public String productCode;
    
    /**
     * (担保客)<BR>
     * 担保客<BR>
     */ 
    public String mortgageCustomer;
    
    /**
     * (ミックス客)<BR>
     * ミックス客<BR>
     */ 
    public String mixCustomer;
    
    /**
     * (契約書)<BR>
     * 契約書<BR>
     */ 
    public String contract;
    
    /**
     * @@roseuid 41B45E7701A5
     */
    public WEB3AccOpenGpInfo() 
    {
     
    }

}
@
