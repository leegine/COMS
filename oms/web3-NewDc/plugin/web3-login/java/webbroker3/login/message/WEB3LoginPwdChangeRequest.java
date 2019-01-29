head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O�C�����p�X���[�h�ύX��v�����郊�N�G�X�g�N���X(WEB3LoginPwdChangeRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i���O�C�����p�X���[�h�ύX���N�G�X�g�j<BR>
 * ���O�C�����p�X���[�h�ύX��v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3LoginPwdChangeRequest extends WEB3GenRequest 
{
   public static  final String TAGNAME = "request";
   public static  final String PTYPE = "web3_login_pwdchange";
   
   /**
    * �i���O�C�����j<BR>
    * ���O�C�����i�ڋq�R�[�h�ACC�I�y���[�^�R�[�h�A�܂��͊Ǘ��҃R�[�h�j
    */
   public String loginName;
   
   /**
    * �i���ڋq�p�X���[�h�j
    */
   public String oldPassword;
   
   /**
    * �i�V�ڋq�p�X���[�h�P�j
    */
   public String newPassword1;
   
   /**
    * �i�V�ڋq�p�X���[�h�Q�j
    */
   public String newPassword2;
   
   /**
    * �i���O�A�E�g�t���O�j<BR>
    * �p�X���[�h�ύX������I��������A���O�A�E�g���邩�ǂ������w������t���O�B<BR>
    * �@@�@@���O�A�E�g����:"1"�A���O�A�E�g���Ȃ�:"0"<BR>
    * �ڋqSS�J�ڑO�̃p�X���[�h�ύX�̏ꍇ�A����I����Ƀ��O�A�E�g����K�v������B<BR>
    */
   public String logoutFlag;
   
   /**
    * @@roseuid 40692C4F03A9
    */
   public WEB3LoginPwdChangeRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4063C0380355
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3LoginPwdChangeResponse(this);    
   }
}
@
