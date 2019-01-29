head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品訂正入力レスポンス(WEB3AdminPointPremiumChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (景品訂正入力レスポンス)<BR>
 * 景品訂正入力レスポンスクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (訂正前景品名)<BR>
     * 訂正前の景品名<BR>
     */
    public String beforePremiumName;
    
    /**
     * (訂正前必要ポイント)<BR>
     * 訂正前の必要ポイント<BR>
     */
    public String beforeRequiredPoint;
    
    /**
     * (訂正前提供開始日時)<BR>
     * 訂正前の提供開始日時<BR>
     */
    public Date beforeStartDate;
    
    /**
     * (訂正前提供終了日時)<BR>
     * 訂正前の提供終了日時<BR>
     */
    public Date beforeEndDate;
    
    /**
     * @@roseuid 41D12549031C
     */
    public WEB3AdminPointPremiumChangeInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointPremiumChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
