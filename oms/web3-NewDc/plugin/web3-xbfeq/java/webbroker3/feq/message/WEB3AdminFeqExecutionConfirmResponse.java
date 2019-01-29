head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来入力確認レスポンス(WEB3AdminFeqExecutionConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式出来入力確認レスポンス)<BR>
 * 管理者外国株式出来入力確認レスポンスクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;    
    
    /**
     * (出来詳細情報一覧)<BR>
     * 外国株式約定詳細の配列
     */
    public WEB3FeqExecDetailInfoUnit[] execDetailInfoList;
    
    /**
     * (約定為替レート)<BR>
     * 約定為替レート
     */
    public String execExchangeRate;
    
    /**
     * (約定日)<BR>
     * 約定日
     */
    public Date executionDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日
     */
    public Date localDeliveryDate;
    
    /**
     * (譲渡益)<BR>
     * 譲渡益
     */
    public String passProfit;
    
    /**
     * (譲渡益税)<BR>
     * 譲渡益税
     */
    public String passProfitTax;
    
    /**
     * @@roseuid 42CE39FD037A
     */
    public WEB3AdminFeqExecutionConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqExecutionConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
