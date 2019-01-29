head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス一覧レスポンス(WEB3AdminSrvRegiServiceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用管理者サービス一覧レスポンス)<BR>
 * サービス利用管理者サービス一覧レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151448L;
    
    /**
     * (抽選無サービス情報一覧)
     */
    public WEB3AdminSrvRegiNoLotteryGroup[] noLotList;
    
    /**
     * (抽選有サービス情報一覧)
     */
    public WEB3AdminSrvRegiLotteryGroup[] lotList;
    
    /**
     * (申込不要サービス情報一覧)
     */
    public WEB3AdminSrvRegiNoApplyGroup[] noApplyList;
    
    /**
     * (サービス利用管理者サービス一覧レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE31F0020A
     */
    public WEB3AdminSrvRegiServiceReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminSrvRegiServiceReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
