head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  シングルサインオンレスポンス (WEB3FXSingleSignOnResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/25 王暁傑 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (シングルサインオンレスポンス) <BR>
 * シングルサインオンレスポンスクラス <BR>
 * 
 * @@author 王暁傑(中訊)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_single_sign_on";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (識別コード) <BR>
     * FXドキュメント閲覧履歴チェック結果が閲覧履歴無しの識別コード <BR>
     * <BR>
     * ※シングルサインオンリクエスト.電子鳩チェックフラグ=falseの場合、null<BR>
     */
    public String[] requestCode;

    /**
     * (説明不要承諾履歴チェック結果) <BR>
     * 説明不要承諾履歴チェック結果 <BR>
     * <BR>
     * true：履歴有り <BR>
     * false：履歴無し<BR>
     */
    public boolean noExplainAgreeHistoryCheck;

    /**
     * (暗号化文字列) <BR>
     * 秘密鍵とポスト用生成データにて作成された暗号化文字列 <BR>
     */
    public String encryptString;
    
    /**
     * (秘密鍵) <BR>
     * ランダムに作成された秘密鍵 <BR>
     */
    public String secretKey;
    
    /**
     * (ハッシュ値) <BR>
     * 暗号化文字列と秘密鍵で生成されたハッシュ値<BR>
     */
    public String hashValue;

    /**
     * (URL) <BR>
     * 外部為替保証金システムURL<BR>
     */
    public String fxUrl;
    
    /**
     * @@roseuid 41E783E3032C
     */
    public WEB3FXSingleSignOnResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FXSingleSignOnResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}@
