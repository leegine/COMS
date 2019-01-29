head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式市場連動区分変更完了レスポンス(WEB3AdminFeqMarketLinkChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者外国株式市場連動区分変更完了レスポンス)<BR>
 * 管理者外国株式市場連動区分変更完了レスポンスクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */


public class WEB3AdminFeqMarketLinkChangeCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_market_link_change_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqMarketLinkChangeCompleteResponse() 
    {
     
    }
    
    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;
    
    /**
     * (外国株式市場連動状況一覧)<BR>
     * 外国株式市場連動状況の配列<BR>
     */
    public WEB3FeqMarketLinkStateUnit[] feqMarketLinkStateList;
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqMarketLinkChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
