head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetSessionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Z�b�V���������ݒ茋�ʂ�Ԃ����X�|���X�N���X(WEB3SetSessionResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 �e�n(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Z�b�V���������ݒ背�X�|���X)<BR>
 * �Z�b�V���������ݒ茋�ʂ�Ԃ����X�|���X�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3SetSessionResponse extends WEB3GenResponse 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "response";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_set_session";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200402251830L;
   
   /**
    * �f�t�H���g�R���X�g���N�^�B
    */
   public WEB3SetSessionResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    *<BR>
    * @@param l_request
    */
   public WEB3SetSessionResponse(WEB3SetSessionRequest l_request)
   {
       super(l_request);
   }
}
@
