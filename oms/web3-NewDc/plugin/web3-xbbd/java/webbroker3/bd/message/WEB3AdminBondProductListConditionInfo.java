head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductListConditionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧検索条件(WEB3AdminBondProductListConditionInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (管理者債券銘柄一覧検索条件)<BR>
 * 銘柄一覧を検索するときの条件
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductListConditionInfo extends Message
{
    
    /**
     * (債券タイプ)<BR>
     * 債券タイプ<BR>
     * <BR>
     * 4:外国債券　@10:国内債券
     */
    public String bondType;
    
    /**
     * (種別コード)<BR>
     * 種別コード
     */
    public String bondCategCode;
    
    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;
    
    /**
     * (発行日)<BR>
     * 発行日
     */
    public Date issueDate;
    
    /**
     * (償還日)<BR>
     * 償還日
     */
    public Date maturityDate;
    
    /**
     * (利払日)<BR>
     * 利払日
     */
    public String interestPaymentDay;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (取扱区分)<BR>
     * 取扱区分<BR>
     * <BR>
     * 0：不可  1：管理者　@2：管理者/顧客
     */
    public String tradeHandleDiv;
    
    /**
     * @@roseuid 44E3363B00EA
     */
    public WEB3AdminBondProductListConditionInfo() 
    {
     
    }
}
@
