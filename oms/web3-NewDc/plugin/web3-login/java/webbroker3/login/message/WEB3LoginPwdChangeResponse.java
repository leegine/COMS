head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O�C�����p�X���[�h�ύX���ʂ�Ԃ����X�|���X�N���X(WEB3LoginPwdChangeResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/31 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i���O�C�����p�X���[�h�ύX���X�|���X�j<BR>
 * ���O�C�����p�X���[�h�ύX���ʂ�Ԃ����X�|���X�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3LoginPwdChangeResponse extends WEB3GenResponse 
{
   public static  final String TAGNAME = "response";
   public static  final String PTYPE = "web3_login_pwdchange";
   
   /**
    * �i�ă��O�C�����{�t���O�j<BR>
    * �p�X���[�h�ύX������I��������A�ă��O�C�������{���邩���w������t���O�B<BR>
    * �p�X���[�h�ύX��ă��O�C�����{���X����\�����̂ł���A���N�G�X�g�E�p�����[�^<BR>
    * �̎w���ɂ�胍�O�A�E�g�����ꍇ�́u�ă��O�C������v�Ƃ͂Ȃ�Ȃ��B<BR>
    * �@@�@@�ă��O�C������:"1"�A�ă��O�C�����Ȃ�:"0"<BR>
    */
   public String reLoginFlag;
   
   /**
    * @@roseuid 40692C570186
    */
   public WEB3LoginPwdChangeResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<B
    * R>
    * @@param l_request
    * @@roseuid 4063C00C0123
    */
   public WEB3LoginPwdChangeResponse(WEB3LoginPwdChangeRequest l_request) 
   {
       super(l_request);    
   }
}
@
