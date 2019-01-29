head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイントサービス画面レスポンス(WEB3PointApplyReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイントサービス画面レスポンス)<BR>
 * ポイントサービス画面レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointApplyReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290002L;
    
    /**
     * (利用可能ポイント)<BR>
     * 該当顧客の利用可能ポイント<BR>
     */
    public String availablePoint;
    
    /**
     * (失効注意ポイント)<BR>
     * 該当顧客の失効注意ポイント<BR>
     */
    public String lapseWarningPoint;
    
    /**
     * (カテゴリー一覧)<BR>
     * 選択可能なカテゴリーの一覧<BR>
     */
    public WEB3AdminPointCategoryUnit[] categoryList;
    
    /**
     * (申込状況一覧)<BR>
     * 該当顧客のポイント交換の申込状況<BR>
     */
    public WEB3PointApplyStateDetail[] applyStateList;
    
    /**
     * @@roseuid 41D1255200CB
     */
    public WEB3PointApplyReferenceResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3PointApplyReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
