head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者トレードボーナスプラン照会レスポンス(WEB3AdminPointTradeBonusPlanReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 郭英(中訊) 新規作成
*/

package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者トレードボーナスプラン照会レスポンス)<BR>
 * 管理者トレードボーナスプラン照会レスポンスクラス<BR>
 */
public class WEB3AdminPointTradeBonusPlanReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_tradeBonusPlanReference";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200506141000L;
    
    /**
     * (ポイント適用年月)<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthCurr;
    
    /**
     * (トレードポイント)<BR>
     */
    public String trdPointCurr;
    
    /**
     * (キャンペーンポイント)<BR>
     */
    public String cmpPointCurr;
    
    /**
     * (合計ポイント)<BR>
     */
    public String totalPointCurr;
    
    /**
     * (割引率)<BR>
     */
    public String cutRateCurr;
    
    /**
     * (ポイント適用年月（翌月）)<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthNext;
    
    /**
     * (トレードポイント（翌月）)<BR>
     */
    public String trdPointNext;
    
    /**
     * (キャンペーンポイント（翌月）)<BR>
     */
    public String cmpPointNext;
    
    /**
     * (合計ポイント（翌月）)<BR>
     */
    public String totalPointNext;
    
    /**
     * (割引率（翌月）)<BR>
     */
    public String cutRateNext;
    
    /**
     * @@roseuid 42AE353303D8
     */
    public WEB3AdminPointTradeBonusPlanReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointTradeBonusPlanReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
