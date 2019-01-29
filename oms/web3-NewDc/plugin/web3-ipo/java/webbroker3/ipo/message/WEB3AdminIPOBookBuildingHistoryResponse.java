head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾚｽﾎﾟﾝｽ(WEB3AdminIPOBookBuildingHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121108L;
    
    /**
     * 出金余力
     */
    public String paymentPower;
    
    /**
     * 申告履歴一覧
     */
    public WEB3IPODemandHistoryUnit[] demandHistoryList;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）<BR>
     */
    public String displayUnitDiv;
    
    /**
     * @@roseuid 4112DF8C015D
     */
    public WEB3AdminIPOBookBuildingHistoryResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11AE903CA
     */
    public WEB3AdminIPOBookBuildingHistoryResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
