head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleSendqResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ProcessSleSendqResponseクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 呉 新規作成
*/
package webbroker3.slegateway.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ProcessSleSendqRequestメッセージのレスポンスクラスです。
 */
public class WEB3ProcessSleSendqResponse extends WEB3GenResponse {

	/** 最上位レベルのタグです。 */
	public static final String TAGNAME = "response";

	/** このメッセージのPTYPEです。 */
	public static final String PTYPE = WEB3ProcessSleSendqRequest.PTYPE;
	
	/**
	 * レスポンス返信日時
	 */
	public Date date;
	
	/**
	 * コンストラクタ
	 */
	public WEB3ProcessSleSendqResponse()
	{ 
	}
	
	/**
	 * コンストラクタ。<BR>
	 * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
	 * @@param l_request - リクエストオブジェクト
	 */
	public WEB3ProcessSleSendqResponse(WEB3GenRequest  request)
	{ 
		super(request);
	}	

}
@
