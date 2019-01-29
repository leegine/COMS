head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込確認リクエスト(WEB3SrvRegiApplyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用申込確認リクエスト)<BR>
 * サービス利用申込確認リクエストクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiApplyConfirmRequest extends WEB3SrvRegiApplyCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151431L;
    
    /**
     * (サービス利用申込確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1F9D502F5
     */
    public WEB3SrvRegiApplyConfirmRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用申込確認レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F1F9D50314
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3SrvRegiApplyConfirmResponse(this);
    }
}
@
