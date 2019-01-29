head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDownLoadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索ダウンロードリクエストクラス(WEB3AdminInformDownLoadRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (連絡情報検索ダウンロードリクエスト)<BR>
 * 連絡情報検索ダウンロードリクエストクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformDownLoadRequest extends WEB3AdminInformCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;
    
    /**
     * @@roseuid 41EE625B01C5
     */
    public WEB3AdminInformDownLoadRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformDownLoadResponse(this);
    }
}
@
