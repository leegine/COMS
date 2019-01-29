head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyCommissionCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用適用手数料条件(WEB3SrvRegiApplyCommissionCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (サービス利用適用手数料条件)<BR>
 * サービス利用適用手数料条件クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3SrvRegiApplyCommissionCondition extends Message
{
    
    /**
     * (商品分類区分)
     */
    public String productKindDiv;
    
    /**
     * (無効区分)<BR>
     * true:無効　@false:有効<BR>
     */
    public boolean invalidDiv;
    
    /**
     * (サービス利用適用手数料条件)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4186E750025E
     */
    public WEB3SrvRegiApplyCommissionCondition() 
    {
     
    }
}
@
