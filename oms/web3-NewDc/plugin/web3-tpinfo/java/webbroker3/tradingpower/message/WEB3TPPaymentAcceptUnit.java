head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentAcceptUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �o����t�󋵖��׃��j�b�g�N���X(WEB3TPPaymentAcceptUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

/**
 * (�o����t�󋵖��׃��j�b�g)<BR>
 * �o����t�󋵖��׃��j�b�g�N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �o����t�󋵖���
 */
public class WEB3TPPaymentAcceptUnit extends Message 
{
   
   /**
    * ���t
    */
   public Date bizDate;
   
   /**
    * �o���z
    */
   public String paymentAmount;
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B932E60130
    */
   public WEB3TPPaymentAcceptUnit() 
   {
    
   }
}
@
