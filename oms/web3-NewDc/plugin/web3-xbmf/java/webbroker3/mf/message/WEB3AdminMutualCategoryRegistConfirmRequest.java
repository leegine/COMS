head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー登録確認リクエスト(WEB3AdminMutualCategoryRegistConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信管理者カテゴリー登録確認リクエスト)<BR>
 * 投資信託管理者カテゴリー登録確認リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistConfirmRequest 
    extends WEB3MutualCategoryRegistCommonRequest 
{   
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030951L;
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信管理者カテゴリー登録確認レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 4153B8260150
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistConfirmResponse(this);
    }
}
@
