head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSetAbleCommissionCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用設定可能手数料条件(WEB3SrvRegiSetAbleCommissionCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用設定可能手数料条件)<BR>
 * サービス利用設定可能手数料条件クラス<BR>
 *
 *  @@author 郭英
 * @@version 1.0
 */

public class WEB3SrvRegiSetAbleCommissionCondition extends Message
{
    
    /**
     * (商品分類区分)
     */
    public String productKindDiv;
    
    /**
     * (商品名)
     */
    public String productName;
    
    /**
     * (サービス利用設定可能手数料条件)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4186E7870164
     */
    public WEB3SrvRegiSetAbleCommissionCondition() 
    {
     
    }
}
@
