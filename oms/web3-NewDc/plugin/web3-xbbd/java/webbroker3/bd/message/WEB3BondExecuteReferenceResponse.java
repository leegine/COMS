head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会レスポンス(WEB3BondExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 周捷 (中訊) 新規作成
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (債券注文約定照会レスポンス)<BR>
 * 債券注文約定照会レスポンス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceResponse extends WEB3GenResponse
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_execute_reference";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;
    
    /**
     * (債券注文約定照会明細一覧)<BR>
     * 債券注文約定照会明細一覧<BR>
     */
    public WEB3BondExecuteReferenceDetailUnit[] details;
    
    /**
     * (債券注文約定照会レスポンス)<BR>
     * コンストラクタ<BR> 
     */
    public WEB3BondExecuteReferenceResponse()
    {
        
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
