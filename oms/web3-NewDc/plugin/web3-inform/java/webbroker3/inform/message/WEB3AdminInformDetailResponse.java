head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索詳細レスポンスクラス(WEB3AdminInformDetailResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (連絡情報検索詳細レスポンス)<BR>
 * 連絡情報検索詳細レスポンスクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformDetailResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (連絡情報)<BR>
     * 連絡情報
     */     
    public WEB3InformDetailHeaderInfoUnit informInfoDetailUnit;
    
    /**
     * @@roseuid 41EE625B0157
     */
    public WEB3AdminInformDetailResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
