head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー訂正入力レスポンス(WEB3AdminPointCategoryChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (カテゴリー訂正入力レスポンス)<BR>
 * カテゴリー訂正入力レスポンスクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryChangeInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (訂正前カテゴリー名)<BR>
     * 訂正前のカテゴリー名<BR>
     */
    public String beforeCategoryName;
    
    /**
     * (訂正前カテゴリー概要)<BR>
     * 訂正前のカテゴリー概要<BR>
     */
    public String beforeCategoryOutline;
    
    /**
     * @@roseuid 41D123240138
     */
    public WEB3AdminPointCategoryChangeInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointCategoryChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
