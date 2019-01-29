head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会レスポンスクラス(WEB3MutualBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
                   2005/10/20 韋念瓊 (中訊) フィデリティ対応
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信残高照会レスポンス)<BR>
 * 投信残高照会レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceResponse extends WEB3GenResponse 
{   
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;

    /**
    * (残高照会明細)<BR>
    * 投信残高照会明細の配列
    */
   public WEB3MutualBalanceReferenceDetailUnit[] balanceReference;
   
   /**
    * (表示ページ番号)<BR>
    * 表示ページ番号<BR>
    * <BR>
    * 実際に表示するページ位置を指定　@※先頭ページを"1"とする
    */
   public String pageIndex;
   
   /**
    * (総ページ数)<BR>
    * 総ページ数
    */
   public String totalPages;
   
   /**
    * (総レコード数)<BR>
    * 総レコード数
    */
   public String totalRecords;
   
   /**
    * (投信銘柄カテゴリー一覧)<BR>
    * 投信銘柄カテゴリー一覧<BR>
    * (nullの場合はカテゴリ指定無しとする)<BR>
    */
   public WEB3MutualProductCategoryUnit[] categoryList;

   /**
    * コンストラクタ。<BR>
    * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
    * @@param l_request - リクエストオブジェクト
    */
   public WEB3MutualBalanceReferenceResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }    
   
   /**
    * @@roseuid 41D13CC10196
    */
   public WEB3MutualBalanceReferenceResponse() 
   {
    
   }
}
@
