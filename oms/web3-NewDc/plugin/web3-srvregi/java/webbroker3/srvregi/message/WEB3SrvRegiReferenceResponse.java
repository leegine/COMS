head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス一覧照会レスポンス(WEB3SrvRegiReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用サービス一覧照会レスポンス)<BR>
 * サービス利用サービス一覧照会レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151411L;
    
    /**
     * (抽選無情報一覧)
     */
    public WEB3SrvRegiNoLotteryGroup[] noLotList;
    
    /**
     * (抽選有情報一覧)
     */
    public WEB3SrvRegiLotteryGroup[] lotList;
    
    /**
     * (サービス利用サービス一覧照会レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1E4770278
     */
    public WEB3SrvRegiReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3SrvRegiReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
