head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceUseQuoteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐���(�����v�Z)��ʕ\�����X�|���X(WEB3TPTransitionReferenceUseQuoteResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/01/31 �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�]�͐���(�����v�Z)��ʕ\�����X�|���X)<BR>
 * �]�͐���(�����v�Z)��ʕ\�����X�|���X�N���X�B<BR>
 * 
 */
public class WEB3TPTransitionReferenceUseQuoteResponse extends WEB3TPTransitionReferenceResponse 
{

    public static final String PTYPE = "tradingpower_transition_reference_use_quote";

    /**
     * �R���X�g���N�^
     * @@param l_request
     */
    protected WEB3TPTransitionReferenceUseQuoteResponse(WEB3GenRequest l_request) 
    {
         super( l_request );
    }
   
    /**
     * �R���X�g���N�^
     */
    public WEB3TPTransitionReferenceUseQuoteResponse() 
    {
    
    }

}
@
