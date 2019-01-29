head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingEnterResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング参加レスポンスクラス(WEB3IPOBookBuildingEnterResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング参加レスポンスクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingEnterResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingEnter";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171847L;
    
    /**
     * 新規上場一覧
     */
    public WEB3IPOPublicOfferingProductUnit[] newListingList;
    
    /**
     * 既上場一覧
     */
    public WEB3IPOPublicOfferingProductUnit[] listingList;
    
    /**
     * @@roseuid 4112EA850030
     */
    public WEB3IPOBookBuildingEnterResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D165300353
     */
    public WEB3IPOBookBuildingEnterResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
