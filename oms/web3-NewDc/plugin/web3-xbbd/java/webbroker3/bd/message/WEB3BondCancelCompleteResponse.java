head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消完了レスポンス(WEB3BondCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 周捷 (中訊) 新規作成
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (債券取消完了レスポンス)<BR>
 * 債券取消完了レスポンス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondCancelCompleteResponse extends WEB3GenResponse
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_complete";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (更新日時)<BR>
     * 更新日時<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (債券取消完了レスポンス)<BR>
     * コンストラクタ<BR> 
     */
    public WEB3BondCancelCompleteResponse()
    {
        
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
