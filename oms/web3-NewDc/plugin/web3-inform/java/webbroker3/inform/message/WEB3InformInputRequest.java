head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡入力リクエスト(WEB3InformInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (連絡入力リクエスト)<BR>
 * 連絡入力リクエストクラス
 */
public class WEB3InformInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;
    /**
     * @@roseuid 41EE625E01F4
     */
    public WEB3InformInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {

        return new WEB3InformInputResponse(this);
    }
}
@
