head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSelectSettleInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 選択決済機@関明細(WEB3AioSelectSettleInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (選択決済機@関明細)<BR>
 * 選択決済機@関明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */
public class WEB3AioSelectSettleInstitutionUnit extends Message
{
    /**
     * (決済機@関ID)
     */
    public String paySchemeId;
    
    /**
     * (決済機@関の名称)
     */
    public String paySchemeName;
    
    /**
     * (受付状況)<BR>
     * 決済機@関の現時点での受付状況<BR>
     * <BR>
     * 0： 停止中<BR>
     * 1： 受付中<BR>
     */
    public String receptionStatus;
    
    /**
     * (コンストラクタ)
     * @@return webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit
     * @@roseuid 40E257400363
     */
    public WEB3AioSelectSettleInstitutionUnit() 
    {
     
    }
}
@
