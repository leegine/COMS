head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSettleInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 提携決済機@関明細(WEB3AioSettleInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (提携決済機@関明細)<BR>
 * 提携決済機@関明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */
public class WEB3AioSettleInstitutionUnit extends Message
{
    
    /**
     * (決済機@関のID)
     */
    public String paySchemeId;
    
    /**
     * (決済機@関の名称)
     */
    public String paySchemeName;
    
    /**
     * (提携決済機@関明細)<BR>
     * コンストラクタ
     * @@return webbroker3.aio.message.WEB3AioSettleInstitutionUnit
     * @@roseuid 40E51A76030F
     */
    public WEB3AioSettleInstitutionUnit() 
    {
     
    }
}
@
