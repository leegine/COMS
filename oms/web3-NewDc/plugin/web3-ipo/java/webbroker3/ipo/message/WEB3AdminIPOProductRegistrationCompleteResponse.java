head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄新規登録完了レスポンス(WEB3AdminIPOProductRegistrationCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄新規登録完了レスポンス)<BR>
 * IPO銘柄新規登録完了レスポンスクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161055L;
    
    /**
     * @@roseuid 4112DF8D01C3
     */
    public WEB3AdminIPOProductRegistrationCompleteResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄新規登録完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1420F019C
     */
    public WEB3AdminIPOProductRegistrationCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
