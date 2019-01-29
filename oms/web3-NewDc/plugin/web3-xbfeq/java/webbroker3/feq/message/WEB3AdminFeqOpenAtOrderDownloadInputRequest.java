head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOpenAtOrderDownloadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式寄付注文ダウンロード入力リクエスト(WEB3AdminFeqOpenAtOrderDownloadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式寄付注文ダウンロード入力リクエスト)<BR>
 * 管理者外国株式寄付注文ダウンロード入力リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDownloadInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_openAtOrderDownloadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
    
    /**
     * @@roseuid 42CE39FF0222
     */
    public WEB3AdminFeqOpenAtOrderDownloadInputRequest() 
    {
     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOpenAtOrderDownloadInputResponse(this);
    }
}
@
