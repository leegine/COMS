head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂���v�����郊�N�G�X�g�N���X(WEB3SetAccountRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.*;

/**
 * (�ڋq���肷�܂����N�G�X�g)<BR>
 * �ڋq���肷�܂���v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3SetAccountRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_login_set_account";
   
   /**
    * (���X�R�[�h)<BR>
    * ���肷�܂��ڋq�̕��X�R�[�h
    */
   public String branchCode;
   
   /**
    * (�ڋq�R�[�h)<BR>
    * �ڋq�R�[�h�i���O�C�����̌����ԍ��j
    */
   public String acceptCode;
   
   /**
    * (xTrade���[�U��)<BR>
    * ���͂��ꂽ�ڋq�R�[�h��xTrade���O�C�����[�U���ɕϊ������l
    */
   public String xTradeUsername;

     /**
      * �ڋqID
      * Login���̂Ȃ񂿂����accountID
      */
     public String account_id;

   /**
    * (�ڋq�p�X���[�h)<BR>
    * ���肷�܂����p�X���[�h�`�F�b�N�Ȃ����X���L�蓾��̂ŁA���ݒ��������B
    */
   public String acceptPassword;
   
   /**
    * (�����`���l��)
    */
   public String orderChannel;
   
   /**
    * (�����o�H�敪)<BR>
    * �Œ�I�Ɂu"1"�F�R�[���Z���^�[�v���Z�b�g���A�T�[�r�X�ɓn���B
    */
   public String orderRootDiv;
   
   /**
    * �i�p�X���[�h�`�F�b�N�L���t���O�j
    */
   public String passwordCheckFlag;
   
   /**
    * @@roseuid 4045C89302AB
    */
   public WEB3SetAccountRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return WEB3GenResponse
    * @@roseuid 4045C72001E0
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3SetAccountResponse(this);    
   }
}
@
