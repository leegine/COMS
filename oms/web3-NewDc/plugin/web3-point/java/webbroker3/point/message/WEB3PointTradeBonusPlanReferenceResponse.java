head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トレードボーナスプラン照会レスポンス(WEB3PointTradeBonusPlanReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (トレードボーナスプラン照会レスポンス)<BR>
 * トレードボーナスプラン照会レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_tradeBonusPlanReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502241605L;
    
    /**
     * (ポイント適用年月)<BR>
     * ポイント適用年月<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthCurr;
    
    /**
     * (トレードポイント)<BR>
     * トレードポイント<BR>
     */
    public String trdPointCurr;
    
    /**
     * キャンペーンポイント<BR>
     * (キャンペーンポイント)<BR>
     */
    public String cmpPointCurr;
    
    /**
     * (合計ポイント)<BR>
     * 合計ポイント<BR>
     */
    public String totalPointCurr;
    
    /**
     * (割引率)<BR>
     * 割引率<BR>
     */
    public String cutRateCurr;
    
    /**
     * (ポイント適用年月（翌月）)<BR>
     * ポイント適用年月（翌月）<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthNext;
    
    /**
     * (トレードポイント（翌月）)<BR>
     * トレードポイント（翌月）<BR>
     */
    public String trdPointNext;
    
    /**
     * (キャンペーンポイント（翌月）)<BR>
     * キャンペーンポイント（翌月）<BR>
     */
    public String cmpPointNext;
    
    /**
     * (合計ポイント（翌月）)<BR>
     * 合計ポイント（翌月）<BR>
     */
    public String totalPointNext;
    
    /**
     * (割引率（翌月）)<BR>
     * 割引率（翌月）<BR>
     */
    public String cutRateNext;    
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3PointTradeBonusPlanReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
