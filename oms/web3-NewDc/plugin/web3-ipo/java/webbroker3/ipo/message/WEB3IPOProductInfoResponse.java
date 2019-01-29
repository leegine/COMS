head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOProductInfoResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO個別銘柄情報レスポンスクラス(WEB3IPOProductInfoResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO個別銘柄情報レスポンスクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOProductInfoResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_productInfo";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171834L;
    
    /**
     * 申告可能フラグ<BR>
     * <BR>
     * true：　@申告可能<BR>
     * false：　@申告不可<BR>
     */
    public boolean demandFlag;
    
    /**
     * (銘柄情報)
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E79C00A7
     */
    public WEB3IPOProductInfoResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1670C0111
     */
    public WEB3IPOProductInfoResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
