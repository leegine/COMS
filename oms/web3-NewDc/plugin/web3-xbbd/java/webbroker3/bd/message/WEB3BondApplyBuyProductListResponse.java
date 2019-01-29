head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付銘柄一覧レスポンス(WEB3BondApplyBuyProductListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券応募/買付銘柄一覧レスポンス)<BR>
 * 債券応募/買付銘柄一覧レスポンス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductListResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyProductList";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;  
    
    /**
     * (通貨情報一覧)<BR>
     * 通貨情報一覧<BR>
     */
    public WEB3BondCurrencyInfo[] currencyList;
    
    /**
     * (応募/買付銘柄一覧)<BR>
     * 応募/買付銘柄一覧<BR>
     */
    public WEB3BondApplyBuyProductInfo[] productList;
    
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
     * (債券応募/買付銘柄一覧レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44B6E4AB01A5
     */
    public WEB3BondApplyBuyProductListResponse() 
    {
     
    }
    
     /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondApplyBuyProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
