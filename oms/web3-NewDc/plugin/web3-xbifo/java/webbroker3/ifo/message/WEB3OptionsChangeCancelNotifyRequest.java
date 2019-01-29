head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsChangeCancelNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式指数オプション訂正取消通知リクエスト(WEB3OptionsChangeCancelNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (株式指数オプション訂正取消通知リクエスト)<BR>
 * 株式指数オプション訂正取消通知リクエストクラス<BR>
 * @@author 李海波
 * @@version 1.0 
 */
public class WEB3OptionsChangeCancelNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static  final String PTYPE = "options_changeCancelNotify";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141527L;
       
    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * @@roseuid 40C0AE5103C8
     */
    public WEB3OptionsChangeCancelNotifyRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3OptionsChangeCancelNotifyResponse(this);
    }
}
@
