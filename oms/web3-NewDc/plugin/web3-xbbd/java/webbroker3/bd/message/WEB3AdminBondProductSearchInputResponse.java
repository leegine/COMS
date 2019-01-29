head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧画面表示レスポンス(WEB3AdminBondProductSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者債券銘柄一覧画面表示レスポンス)<BR>
 * 管理者債券銘柄一覧画面表示レスポンス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductSearchInputResponse extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_search_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (種別コード一覧)<BR>
     * 種別コード一覧。
     */
    public String[] bondKindCodeList;
    
    /**
     * (通貨コード一覧)<BR>
     * 通貨コード一覧
     */
    public String[] currencyCodeList;
    
    /**
     * （取扱区分一覧）<BR>
     * 取扱区分一覧<BR>
     * <BR>
     * 0：不可  1：管理者　@2：管理者/顧客
     */
    public String[] tradeHandleDivList;
    
    /**
     * @@roseuid 44E3363D03B9
     */
    public WEB3AdminBondProductSearchInputResponse() 
    {
     
    }
    
    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondProductSearchInputResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
