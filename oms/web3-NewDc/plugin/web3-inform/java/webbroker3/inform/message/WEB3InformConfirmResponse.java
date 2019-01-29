head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡確認レスポンス(WEB3InformConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (連絡確認レスポンス)<BR>
 * 連絡確認レスポンスクラス
 */
public class WEB3InformConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;


    /**
     * @@roseuid 41EE625C0251
     */
    public WEB3InformConfirmResponse()
    {

    }
    
    public WEB3InformConfirmResponse(WEB3InformConfirmRequest l_request)
    {
        super(l_request);
    }
    /**
     * (付加情報)<BR>
     * 各種連絡情報について画面表示で使用する付加情報
     *<BR>
     */
    public WEB3InformAddInfoUnit informAddUnit;
}
@
