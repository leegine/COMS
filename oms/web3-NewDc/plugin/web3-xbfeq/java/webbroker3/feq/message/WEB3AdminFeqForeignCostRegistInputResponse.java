head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式現地手数料登録入力レスポンス(WEB3AdminFeqForeignCostRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式現地手数料登録入力レスポンス)<BR>
 * 管理者外国株式現地手数料登録入力レスポンスクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_foreignCostRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (現地手数料情報一覧)<BR>
     * 現地手数料情報の配列
     */
    public WEB3AdminFeqForeignCostUnit[] foreignCostRegist;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列
     */
    public String[] marketList;
    
    /**
     * @@roseuid 42CE39FE038A
     */
    public WEB3AdminFeqForeignCostRegistInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqForeignCostRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
