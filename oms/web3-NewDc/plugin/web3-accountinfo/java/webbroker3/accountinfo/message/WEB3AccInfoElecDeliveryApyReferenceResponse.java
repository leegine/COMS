head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryApyReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付情報一覧レスポンス(WEB3AccInfoElecDeliveryApyReferenceResponse.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.277
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (電子交付情報一覧レスポンス)<BR>
 * 電子交付情報一覧レスポンスクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryApyReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "elec_delivery_apy_reference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011121511L;

    /**
     * 電子交付情報<BR>
     */
    public WEB3AccInfoEleDeliveryInfo eleDeliveryInfo;

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
     public WEB3AccInfoElecDeliveryApyReferenceResponse(WEB3GenRequest l_request)
     {
         super(l_request);
     }
}
@
