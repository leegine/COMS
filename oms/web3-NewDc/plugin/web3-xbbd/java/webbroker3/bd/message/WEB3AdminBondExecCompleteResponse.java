head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者新規約定完了レスポンス(WEB3AdminBondExecCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者新規約定完了レスポンス)<BR>
 * 管理者新規約定完了レスポンス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecCompleteResponse extends WEB3GenResponse 
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_complete";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (更新時間)<BR>
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     * 識別番号
     */
    public String orderActionId;
    
    /**
     * @@roseuid 44E3363602BF
     */
    public WEB3AdminBondExecCompleteResponse() 
    {
     
    }
    
    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondExecCompleteResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
