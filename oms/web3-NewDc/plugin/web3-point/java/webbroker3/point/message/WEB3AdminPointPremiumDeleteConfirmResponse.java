head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品削除確認レスポンス(WEB3AdminPointPremiumDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (景品削除確認レスポンス)<BR>
 * 景品削除確認レスポンスクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumDeleteConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumDeleteConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (申込件数)<BR>
     * 該当する景品に対する申込の件数<BR>
     */
    public String applyCount;
    
    /**
     * (景品名)<BR>
     * 景品名<BR>
     */
    public String premiumName;
    
    /**
     * (必要ポイント)<BR>
     * 必要ポイント<BR>
     */
    public String requiredPoint;
    
    /**
     * (提供開始日時)<BR>
     * 提供開始日時<BR>
     */
    public Date startDate;
    
    /**
     * (提供終了日時)<BR>
     * 提供終了日時<BR>
     */
    public Date endDate;
    
    /**
     * @@roseuid 41D1254703D8
     */
    public WEB3AdminPointPremiumDeleteConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointPremiumDeleteConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
