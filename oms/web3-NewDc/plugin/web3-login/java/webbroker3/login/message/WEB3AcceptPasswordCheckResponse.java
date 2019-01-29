head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�p�X���[�h�`�F�b�N���ʂ�Ԃ����X�|���X�N���X(WEB3AcceptPasswordCheckResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 �V���@@�h�O(FLJ) �V�K�쐬
*/
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;


/**
 * (�ڋq�p�X���[�h�`�F�b�N���X�|���X)<BR>
 * �ڋq�p�X���[�h�`�F�b�N���ʂ�Ԃ����X�|���X�N���X<BR>
 *<BR> 
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 */
public class WEB3AcceptPasswordCheckResponse extends WEB3GenResponse 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "response";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_password_check";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200510281830L;

   /**
    * (�p�X���[�h�`�F�b�N����)<BR>
    */
   public boolean certifiedPasswordFlg;
   
   /**
    * �R���X�g���N�^
    */
   public WEB3AcceptPasswordCheckResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    * @@param l_request
    */
   public WEB3AcceptPasswordCheckResponse(WEB3AcceptPasswordCheckRequest l_request)
   {
       super(l_request);
   }
}
@
