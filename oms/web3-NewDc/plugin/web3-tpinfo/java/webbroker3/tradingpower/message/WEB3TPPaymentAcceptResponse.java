head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �o����t�󋵉�ʕ\�����X�|���X(WEB3TPPaymentAcceptResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o����t�󋵉�ʕ\�����X�|���X)<BR>
 * �o����t�󋵉�ʕ\�����X�|���X�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPPaymentAcceptResponse extends WEB3GenResponse 
{

    public static final String PTYPE = "tradingpower_payment_status";
     
   /**
    * �]�͌v�Z����ID
    */
   public String calcResultId;

   /**
    * �o����t�󋵖��׈ꗗ
    */
   public WEB3TPPaymentAcceptUnit[] paymentAcceptUnits;
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B932E60036
    */
   public WEB3TPPaymentAcceptResponse() 
   {
    
   }
   
   /**
    * (�R���X�g���N�^)
    * @@param request
    */
   public WEB3TPPaymentAcceptResponse(WEB3TPPaymentAcceptRequest l_request) 
   {
        
       super(l_request);
   }
}
@
