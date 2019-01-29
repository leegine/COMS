head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品情報明細(WEB3AdminPointPremiumDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;


/**
 * (景品情報明細)<BR>
 * 景品情報明細クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumDetail extends WEB3PointPremiumUnit 
{
    
    /**
     * (提供開始日時)<BR>
     * 提供開始日時<BR>
     */
    public Date startDate;
    
    /**
     * (提供終了日時)<BR>
     * 提供終了日時<BR>
     */
    public Date endDate;
    
    /**
     * (景品情報明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 418F560D0127
     */
    public WEB3AdminPointPremiumDetail() 
    {
     
    }
}
@
