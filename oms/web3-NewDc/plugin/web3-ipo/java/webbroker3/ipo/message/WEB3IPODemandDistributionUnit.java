head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandDistributionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告分布明細メッセージデータ(WEB3IPODemandDistributionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * IPO申告分布明細メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPODemandDistributionUnit extends Message
{
    
    /**
     * 申告件数
     */
    public String demandNumber;
    
    /**
     * 申告比率
     */
    public String demandPercentage;
    
    /**
     * 申告価格下限
     */
    public String demandPriceLower;
    
    /**
     * 申告価格上限
     */
    public String demandPriceUpper;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40E51D28034F
     */
    public WEB3IPODemandDistributionUnit() 
    {
     
    }
}
@
