head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiConsentResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用同意書レスポンス(WEB3SrvRegiConsentResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2007/06/05 張騰宇　@モデル 248
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用同意書レスポンス)<BR>
 * サービス利用同意書レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiConsentResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_consent";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151442L;
    
    /**
     * (同意書文言)
     */
    public String consentSentence;

    /**
     * (申込属性区分)<BR>
     * 1：無料対象　@2：申込不可<BR>
     */
    public String applyAttributeDiv;

    /**
     * (申込属性期間From)<BR>
     * 申込属性期間From(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodFrom;

    /**
     * (申込属性期間To)<BR>
     * 申込属性期間To(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodTo;

    /**
     * (無料属性申込区分)<BR>
     * null or '0' ：通常申込　@'1'：無料属性申込<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (サービス利用同意書レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1EBAC01EB
     */
    public WEB3SrvRegiConsentResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3SrvRegiConsentResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
