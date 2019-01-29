head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwtListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約乗換一覧照会レスポンスクラス(WEB3MutualSellSwtListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 黄建 (中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー  
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託解約乗換一覧照会レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3MutualSellSwtListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_swt_list";
    
    /**
     * SerialVersionUID<BR>
     */
     public final static long serialVersionUID = 200408111740L;
    
    /**
     * 表示ページ番号<BR>
     * <BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * 総ページ数
     */
    public String totalPages;
    
    /**
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * 解約乗換銘柄一覧
     */
    public WEB3MutualSellSwitchingProductGroup[ ] sellSwitchingProductGroups;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A889E1015F
     */
    public WEB3MutualSellSwtListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualSellSwtListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
