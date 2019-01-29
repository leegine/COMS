head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointAdjustConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント調整確認レスポンス(WEB3AdminPointAdjustConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント調整確認レスポンス)<BR>
 * ポイント調整確認レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointAdjustConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_adjustConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410150127L;
    
    /**
     * (調整前利用可能ポイント)<BR>
     * 調整前の利用可能ポイント<BR>
     */
    public String beforeAvailablePoint;
    
    /**
     * (調整後利用可能ポイント)<BR>
     * 調整後の利用可能ポイント<BR>
     */
    public String afterAvailablePoint;
    
    /**
     * @@roseuid 41D1254D034B
     */
    public WEB3AdminPointAdjustConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointAdjustConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
