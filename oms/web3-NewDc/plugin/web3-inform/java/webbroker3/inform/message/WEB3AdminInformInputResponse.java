head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索入力レスポンスクラス(WEB3AdminInformInputResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (連絡情報検索入力レスポンス)<BR>
 * 連絡情報検索入力レスポンスクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (前営業日)<BR>
     * 前営業日日付
     */
    public Date previousBizDate;
    
    /**
     * (当日)<BR>
     * 当日日付
     */
    public Date previousDate;
    
    /**
     * @@roseuid 41EE625B02FD
     */
    public WEB3AdminInformInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
