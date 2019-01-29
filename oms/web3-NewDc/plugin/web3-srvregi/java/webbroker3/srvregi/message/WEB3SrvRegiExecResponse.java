head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス起動レスポンス(WEB3SrvRegiExecResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2008/02/29 金シュ 仕様変更モデルNo.329
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用サービス起動レスポンス)<BR>
 * サービス利用サービス起動レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiExecResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_exec";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151415L;
    
    /**
     * (URL)
     */
    public String url;
    
    /**
     * (送信方法@区分)<BR>
     * 0:GET <BR>
     * 1:POST <BR>
     * 2:HTTP-REQUEST<BR> 
     * 3:特殊（１）－リテラクレア証券 MULTEX 専用<BR> 
     * 4:特殊（２）－リテラクレア証券 日経テレコン21 専用<BR>
     */
    public String sendHowToDiv;
    
    /**
     * (送信パラメータ一覧)<BR>
     * 送信パラメータ一覧 <BR>
     * （書式：<パラメータ名>=<値>）<BR>
     */
    public String[] sendParamList;

    /**
     * (エラー区分)<BR>
     * エラー区分<BR>
     * 1：未申込エラー有<BR>
     * null：エラー無<BR>
     */
    public String srvRegiExecErrDiv;

    /**
     * (サービス利用サービス起動レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F77E29033E
     */
    public WEB3SrvRegiExecResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3SrvRegiExecResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
