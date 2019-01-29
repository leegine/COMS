head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBatoUrlResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO電子鳩URL取得レスポンス(WEB3IPOBatoUrlResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 陳琦(中訊) 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (IPO電子鳩URL取得レスポンス)<BR>
 * IPO電子鳩URL取得レスポンスクラス
 * 
 * @@author 陳琦
 * @@version 1.0
 */
public class WEB3IPOBatoUrlResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_batoUrl";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200601251132L;
    
    /**
     * (稼動チェック結果)<BR>
     * 稼動チェック結果<BR>
     * <BR>
     * true:稼動中 false:停止中
     */
    public boolean isWorking;
    
    /**
     * (URL)<BR>
     * チェック結果が”閲覧未済”の場合、目論見書表示の際に使用するURL
     */
    public String url;
    
    /**
     * (ハッシュ値)<BR>
     * ハッシュ値
     */
    public String hashValue;
    
    /**
     * @@roseuid 43D8344F006D
     */
    public WEB3IPOBatoUrlResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D26DC4013F
     */
    public WEB3IPOBatoUrlResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }

}
@
