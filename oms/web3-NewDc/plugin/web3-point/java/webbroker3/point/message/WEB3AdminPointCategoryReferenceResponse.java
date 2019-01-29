head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー一覧レスポンス(WEB3AdminPointCategoryReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/

package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (カテゴリー一覧レスポンス)<BR>
 * カテゴリー一覧レスポンスクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290112L;
    
    /**
     * (カテゴリー一覧)<BR>
     * カテゴリー明細の配列<BR>
     */
    public webbroker3.point.message.WEB3AdminPointCategoryUnit[] categoryList;
    
    /**
     * @@roseuid 41D1232201B5
     */
    public WEB3AdminPointCategoryReferenceResponse() 
    {
    
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointCategoryReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
