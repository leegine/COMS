head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointPremiumUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品明細(WEB3PointPremiumUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (景品明細)<BR>
 * 景品明細クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointPremiumUnit extends Message
{
    
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
     * (必要ポイント)<BR>
     * 必要ポイント<BR>
     */
    public String requiredPoint;
    
    /**
     * (景品明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 418F5871034B
     */
    public WEB3PointPremiumUnit() 
    {
     
    }
}
@
