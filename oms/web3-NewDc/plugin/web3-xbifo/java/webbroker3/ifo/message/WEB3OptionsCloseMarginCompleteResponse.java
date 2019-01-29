head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済完了レスポンスクラス(WEB3OptionsCloseMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
              001: 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000088
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;



/**
 * (株価指数オプション返済完了レスポンス)<BR>
 * 株価指数オプション返済完了レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    //Start 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000088
    //public final static  String PTYPE = "options_closeMarginComple";
    public final static  String PTYPE = "options_closeMarginComplete";
    //End 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000088
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111930L;
        
    /**
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     * 注文履歴ＩＤ<BR>
     */
    public String orderActionId;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsCloseMarginCompleteResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsCloseMarginCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
