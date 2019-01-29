head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更検索条件(WEB3AdminSrvRegiCustomerSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用管理者顧客変更検索条件)<BR>
 * サービス利用管理者顧客変更検索条件データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiCustomerSearchCondition extends Message 
{
    
    /**
     * (申込登録ID)
     */
    public String applyRegId;
    
    /**
     * (部店コード)
     */
    public String branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (サービス利用管理者顧客変更検索条件)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE7B0C0361
     */
    public WEB3AdminSrvRegiCustomerSearchCondition() 
    {
     
    }
}
@
