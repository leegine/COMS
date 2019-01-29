head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当確認レスポンス(WEB3AdminIPOLotConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者IPO抽選割当確認レスポンス)<BR>
 *  管理者IPO抽選割当確認レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminIPOLotConfirmResponse extends WEB3IPOLotCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * @@roseuid 4112DAD600B9
     */
    public WEB3AdminIPOLotConfirmResponse() 
    {
     
    }
    
    /**
     * 処理状況区分。<BR>
     * １：待ち状況<BR>
     * ２：完了状況<BR>
     * ９：異常終了<BR>
     */
    public String transactionStateType;
    
    /**
     * 抽選割当詳細
     */
    public WEB3IPOLotDetailUnit[] lotDetail;
    
    /**
     * (管理者IPO抽選割当確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D140A203AF
     */
    public WEB3AdminIPOLotConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
