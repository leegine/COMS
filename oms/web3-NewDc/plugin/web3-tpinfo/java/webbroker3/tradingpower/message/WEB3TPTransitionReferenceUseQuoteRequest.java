head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceUseQuoteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐���(�����v�Z)��ʕ\�����N�G�X�g�N���X(WEB3TPTransitionReferenceUseQuoteRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/01/31 �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�]�͐���(�����v�Z)��ʕ\�����N�G�X�g)<BR>
 * �]�͐���(�����v�Z)��ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 */
public class WEB3TPTransitionReferenceUseQuoteRequest extends WEB3TPTransitionReferenceRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_transition_reference_use_quote";

    /**
     * �R���X�g���N�^
     */
    public WEB3TPTransitionReferenceUseQuoteRequest() 
    {
    
    }
   
    /**
     * (create���X�|���X)
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3TPTransitionReferenceUseQuoteResponse(this);
    }

}
@
