head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �o����t�󋵉�ʕ\�����N�G�X�g�N���X(WEB3TPPaymentAcceptRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o����t�󋵉�ʕ\�����N�G�X�g)<BR>
 * �o����t�󋵉�ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPPaymentAcceptRequest extends WEB3GenRequest 
{
 
    /**
     * PTYPE
     */   
    public static final String PTYPE = "tradingpower_payment_status";
   
   /**
    * (�R���X�g���N�^)<BR>
    * @@roseuid 41B930F600B3
    */
   public WEB3TPPaymentAcceptRequest() 
   {
   }
   
   /**
    * (create���X�|���X)
    * @@return WEB3GenResponse
    * @@roseuid 41B930F600D2
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPPaymentAcceptResponse(this);
   }
}
@
