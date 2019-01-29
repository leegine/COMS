head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOManagementResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄管理レスポンス(WEB3AdminIPOManagementResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄管理レスポンス)<BR>
 * IPO銘柄管理レスポンスクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOManagementResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_management";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408160957L;
    
    /**
     * 新規上場一覧
     */
    public WEB3AdminIPOPublicOfferingProductUnit[] newListingList;
    
    /**
     * 既上場一覧
     */
    public WEB3AdminIPOPublicOfferingProductUnit[] listingList;
    
    /**
     * @@roseuid 4112E3800090
     */
    public WEB3AdminIPOManagementResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄管理レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D141510015
     */
    public WEB3AdminIPOManagementResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
