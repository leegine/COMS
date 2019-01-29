head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会条件表示リクエスト(WEB3AdminBondExecRefInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成  
  
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券管理者注文約定照会条件表示リクエスト)<BR>
 * 債券管理者注文約定照会条件表示リクエストクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_or_bond_exec_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171156L; 
    
    /**
     * (債券管理者注文約定照会条件表示リクエスト)<BR>
     * コンストラクタ。
     * @@roseuid 44B735090219
     */
    public WEB3AdminORBondExecRefInputRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 債券管理者注文約定照会条件表示レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44DA82400154
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminORBondExecRefInputResponse(this);
    }
  
}
@
