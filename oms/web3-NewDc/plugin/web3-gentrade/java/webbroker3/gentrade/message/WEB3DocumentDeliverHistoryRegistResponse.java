head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付履歴登録レスポンス(WEB3DocumentDeliverHistoryRegistResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (書面交付履歴登録レスポンス)<BR>
 * <BR>
 * 書面交付履歴登録レスポンスクラス<BR>
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistResponse extends WEB3GenResponse 
{
    
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "document_deliver_history_regist";
    
    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200709281830L;
    
	/**
	 * コンストラクタ<BR>
	 */
	public WEB3DocumentDeliverHistoryRegistResponse() 
    {
	}

	/**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
	 * @@param l_request リクエストオブジェクト
	 */
	public WEB3DocumentDeliverHistoryRegistResponse(WEB3GenRequest l_request) 
    {
		super(l_request);
	}

}
@
