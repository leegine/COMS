head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却完了レスポンス(WEB3BondSellCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券売却完了レスポンス)<BR>
 * 債券売却完了レスポンス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellCompleteResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellComplete";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;  
    
    /**
     * (更新日時)<BR>
     * 更新日時<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (債券売却完了レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D7FEA70008
     */
    public WEB3BondSellCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondSellCompleteResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    } 
}
@
