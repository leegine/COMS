head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐��ډ�ʕ\�����N�G�X�g�N���X(WEB3TPTransitionReferenceRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�]�͐��ډ�ʕ\�����N�G�X�g)<BR>
 * �]�͐��ډ�ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTransitionReferenceRequest extends WEB3GenRequest 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_transition_reference";
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B55B6202D6
    */
   public WEB3TPTransitionReferenceRequest() 
   {
    
   }
   
   /**
    * (cretate���X�|���X)
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B55B620305
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPTransitionReferenceResponse(this);
   }
}
@
