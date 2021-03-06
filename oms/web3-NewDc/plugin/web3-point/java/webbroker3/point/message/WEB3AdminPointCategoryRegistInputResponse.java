head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー登録入力レスポンス(WEB3AdminPointCategoryRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (カテゴリー登録入力レスポンス)<BR>
 * カテゴリー登録入力レスポンスクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryRegistInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * @@roseuid 41D1232300FA
     */
    public WEB3AdminPointCategoryRegistInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointCategoryRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
