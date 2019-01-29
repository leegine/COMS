head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索一覧レスポンスクラス(WEB3AdminInformListResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (連絡情報検索一覧レスポンス)<BR>
 * 連絡情報検索一覧レスポンスクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (連絡情報)<BR>
     * 連絡情報
     */
    public WEB3InformDetailHeaderInfoUnit[] informInfoDetailUnit;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
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
     * @@roseuid 41EE625C002E
     */
    public WEB3AdminInformListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
