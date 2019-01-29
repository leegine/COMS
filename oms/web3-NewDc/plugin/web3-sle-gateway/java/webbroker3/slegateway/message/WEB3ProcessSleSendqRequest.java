head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleSendqRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ProcessSleSendqRequestクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 呉 新規作成
*/
package webbroker3.slegateway.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * send qにあるメッセージを処理するためsle market adapterへ通知するために利用されるメッセージです。

 */
public class WEB3ProcessSleSendqRequest extends WEB3GenRequest
{

	/** 最上位タグ名です。 */
	public static final String TAGNAME = "request";

	/** このメッセージのPTYPEです。 */
	public static final String PTYPE = "process_sendq";//←2006/10/11　@requestメッセージのPTYPEを変更
	
	/**
	 * スレッドNO 
	 */
	public	Long threadNo;
	
	/**
	 * 口座開始ID
	 */
	public long fromAccountId;
	
	/**
	 * 口座終了ID
	 */
	public long toAccountId;
	
	/**
	 * 市場コード
	 */
	public String[] marketCode;//⇒2007/10/23 深セン市場対応
	
	/**
	 * リクエスト送信日時
	 */
	public Date date;

    /* (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
		return new WEB3ProcessSleSendqResponse(this);
    }
}
@
