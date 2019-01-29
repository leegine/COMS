head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付条件一覧リクエスト(WEB3MutualFixedBuyConditionListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信定時定額買付条件一覧リクエスト)<BR>
 * 投信定時定額買付条件一覧リクエスト<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607221353L; 
   
    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyConditionListRequest() 
    {
    }
    
    /**
     * （createResponseの実装） <BR>
     * <BR>
     * 投信定時定額買付条件一覧レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualFixedBuyConditionListResponse(this);
    }
    
}

@
