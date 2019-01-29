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
filename	WEB3GentradePasswordConvAccOpenRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設 暗証番号復号化リクエスト(WEB3GentradePasswordConvAccOpenRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 口座開設 暗証番号復号化リクエストクラス
 */
public class WEB3GentradePasswordConvAccOpenRequest extends WEB3BackRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "gentrade_password_conv_accopen";
   
	/**
	 * SerialVersionUID
	 */
	public static final long serialVersionUID = 200503241415L;
   
	/**
	 * デフォルトコンストラクタ
	 */
	public WEB3GentradePasswordConvAccOpenRequest() 
	{
	}
   
	/**
	 * リクエストに対応するレスポンスオブジェクトを返却する。<br />
	 * @@return WEB3GenResponse<br />
	 * @@roseuid 42366FBD03A6<br />
	 */
	public WEB3BackResponse createResponse() 
	{
		return new WEB3GentradePasswordConvAccOpenResponse(this);
	}
}
@
