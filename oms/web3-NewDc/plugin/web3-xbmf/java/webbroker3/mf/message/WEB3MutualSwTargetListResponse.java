head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwTargetListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信乗換先銘柄一覧レスポンスクラス(WEB3MutualSwTargetListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 韋念瓊 (中訊) 新規作成      
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投信乗換先銘柄一覧レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwTargetListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sw_target_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200510181049L; 
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualSwTargetListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * (投信銘柄カテゴリー一覧)<BR>
     *  投信銘柄カテゴリー一覧<BR>
     * <BR>
     *  (nullの場合はカテゴリ指定無しとする)<BR>
     */
    public WEB3MutualProductCategoryUnit[] categoryList;    
    
    /**
     * (表示ページ番号)<BR>
     *  表示ページ番号 <BR>
     *  実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (総ページ数)<BR>
     *  総ページ数<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     *  総レコード数<BR>
     */
    public String totalRecords;
    
    /**
     * (乗換先銘柄一覧)<BR>
     *  乗換先銘柄一覧<BR>
     */
    public WEB3MutualBuyProductGroup[] switchingProductGroups;
   
    /**
     * (投信乗換先銘柄一覧レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8AABD0130
     */
    public WEB3MutualSwTargetListResponse() 
    {
     
    }
}
@
