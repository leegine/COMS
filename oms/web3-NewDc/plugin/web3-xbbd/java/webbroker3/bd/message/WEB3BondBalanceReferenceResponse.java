head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会レスポンス(WEB3BondBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (債券残高照会レスポンス)<BR>
 * 債券残高照会レスポンスクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceResponse extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609201900L;
    
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
    
    public WEB3BondCurrencyInfo[] currencyList;
    
    public WEB3BondBalanceReferenceDetailUnit[] balanceReference;
    
    /**
     * (債券残高照会レスポンス)<BR>
     * コンストラクタ<BR>
     */
    public WEB3BondBalanceReferenceResponse()
    {
        
    }
    
    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3BondBalanceReferenceResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }

}
@
