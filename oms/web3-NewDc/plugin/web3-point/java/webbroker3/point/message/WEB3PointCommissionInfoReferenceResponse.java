head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手数料無料情報照会レスポンス(WEB3PointCommissionInfoReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株式手数料無料情報照会レスポンス)<BR>
 * 株式手数料無料情報照会レスポンスクラス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_commissionInfoReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502241605L;
    
    /**
     * (手数料無料期間表示フラグ)<BR>
     * 手数料無料期間表示フラグ<BR>
     * <BR>
     * true： 表示<BR>
     * false： 非表示<BR>
     */
    public boolean freeTermDisplayFlag;
    
    /**
     * (手数料無料期間（自）)<BR>
     * 手数料無料期間（自）<BR>
     * （YYYYMMDD）<BR>
     */
    public Date freeTermFrom;
    
    /**
     * (手数料無料期間（至）)<BR>
     * 手数料無料期間（至）<BR>
     * （YYYYMMDD）<BR>
     */
    public Date freeTermTo;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3PointCommissionInfoReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
