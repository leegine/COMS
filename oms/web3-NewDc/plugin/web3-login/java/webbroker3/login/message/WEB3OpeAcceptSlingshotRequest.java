head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂�SS�J�ڂ�v�����郊�N�G�X�g�N���X(WEB3OpeAcceptSlingshotRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 �e�n(SRA) �V�K�쐬
 */
package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�ڋq���肷�܂�SS�J�ڃ��N�G�X�g�j
 * �ڋq���肷�܂�SS�J�ڂ�v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptSlingshotRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_ope_accept_slingshot";
   
   /**
    * (���X�R�[�h)<BR>
    * ���肷�܂��ڋq�̕��X�R�[�h
    */
   public String branchCode;
   
   /**
    * �i�ڋq�R�[�h�j<BR>
    * �ڋq�R�[�h�i���O�C�����̌����ԍ��j<BR>
    */
   public String acceptCode;
   
   /**
    * �ixTrade���[�U���j<BR>
    * ���͂��ꂽ�ڋq�R�[�h��xTrade���O�C�����[�U���ɕϊ������l<BR>
    */
   public String xTradeUsername;
   
   /**
    * �i�ڋq�p�X���[�h�j<BR>
    * ���肷�܂����p�X���[�h�`�F�b�N�Ȃ����X���L�蓾��̂ŁA���ݒ��������B<BR>
    */
   public String acceptPassword;
   
   /**
    * �i�������T�[�r�X�R�[�h�j
    */
   public String infoServiceCode;
   
   /**
    * �i�p�X���[�h�`�F�b�N�L���t���O�j
    */
   public String passwordCheckFlag;
   
   /**
    * @@roseuid 406D2D13032C
    */
   public WEB3OpeAcceptSlingshotRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 406BBC540128
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3OpeAcceptSlingshotResponse(this);    
   }
}
@
