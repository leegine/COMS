head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金推移参照画面表示リクエストクラス(WEB3IfoDepositTransitionReferenceRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (証拠金推移参照画面表示リクエスト)<BR>
 * 証拠金推移参照画面表示リクエストクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceRequest extends WEB3GenRequest
{
    
    public static final String PTYPE = "ifodeposit_transition_reference";

    /**
     * @@roseuid 4186176102CD
     */
    public WEB3IfoDepositTransitionReferenceRequest()
    {

    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 418620B3005C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3IfoDepositTransitionReferenceResponse();
    }
}
@
