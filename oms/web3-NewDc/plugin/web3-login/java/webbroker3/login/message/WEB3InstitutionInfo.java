head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3InstitutionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ��Џ���xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3InstitutionInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/05 �e�n(SRA)
 */

package webbroker3.login.message;


import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (��Џ��)<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3InstitutionInfo extends Message 
{
   /**
    * (���ID)
    */
   public long institutionID;
   
   /**
    * (��ЃR�[�h)
    */
   public String institutionCode;
   
   /**
    * (��Ж�)
    */
   public String institutionName;
   
   /**
    * @@roseuid 4021A2F203D8
    */
   public WEB3InstitutionInfo() 
   {
    
   }
}
@
