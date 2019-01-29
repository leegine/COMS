head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiStateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客申込状況一覧レスポンス(WEB3AdminSrvRegiStateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者顧客申込状況一覧レスポンス)<BR>
 * サービス利用管理者顧客申込状況一覧レスポンスクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiStateResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_state";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (抽選有サービス明細情報)
     */
    public WEB3AdminSrvRegiLotteryStateGroup[] lotDetails;
    
    /**
     * (抽選無サービス明細情報)
     */
    public WEB3AdminSrvRegiNoLotteryStateGroup[] noLotDetails;
    
    /**
     * (サービス利用管理者顧客申込状況一覧レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE569A020A
     */
    public WEB3AdminSrvRegiStateResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiStateResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
