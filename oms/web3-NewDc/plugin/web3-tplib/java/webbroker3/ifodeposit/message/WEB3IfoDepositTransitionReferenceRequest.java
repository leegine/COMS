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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋������ڎQ�Ɖ�ʕ\�����N�G�X�g�N���X(WEB3IfoDepositTransitionReferenceRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�؋������ڎQ�Ɖ�ʕ\�����N�G�X�g)<BR>
 * �؋������ڎQ�Ɖ�ʕ\�����N�G�X�g�N���X�B<BR>
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
