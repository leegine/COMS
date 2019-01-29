head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptAutoLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���肷�܂��������O�C����v�����郊�N�G�X�g�N���X(WEB3OpeAcceptAutoLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/08 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�ڋq���肷�܂��������O�C�����N�G�X�g�j
 * �ڋq���肷�܂��������O�C����v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptAutoLoginRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_ope_accept_autologin";
   
   /**
    * �i��ЃR�[�h�j
    */
   public String institutionCode;
   
   /**
    * �iCC�I�y���[�^�̕��X�R�[�h�j
    */
   public String ccOpeBranchCode;
   
   /**
    * �iCC�I�y���[�^�R�[�h�j
    */
   public String ccOperatorCode;
   
   /**
    * �iCC�I�y���[�^xTrade���[�U���j<BR>
    * ���͂��ꂽCC�I�y���[�^�R�[�h��xTrade���O�C�����[�U���ɕϊ������l�B<BR>
    */
   public String xTradeCCOpeUsername;
   
   /**
    * �iCC�I�y���[�^�p�X���[�h�j
    */
   public String ccOperatorPassword;
   
   /**
    * �i�ڋq�̕��X�R�[�h�j
    */
   public String acceptBranchCode;
   
   /**
    * �i�ڋq�R�[�h�j<BR>
    * �ڋq�R�[�h�i���O�C�����̌����ԍ��j
    */
   public String acceptCode;
   
   /**
    * �i�ڋqxTrade���[�U���j<BR>
    * ���͂��ꂽ�ڋq�R�[�h��xTrade���O�C�����[�U���ɕϊ������l<BR>
    */
   public String xTradeAcceptUsername;
   
   /**
    * �i�����`���l���j
    */
   public String orderChannel;
   
   /**
    * �i�����o�H�敪�j<BR>
    * �Œ�I�Ɂu"1"�F�R�[���Z���^�[�v���Z�b�g���A�T�[�r�X�ɓn���B<BR>
    */
   public String orderRootDiv;
   
   /**
    * �i�n�b�V���l�j
    */
   public String hashValue;
   
   /**
    * @@roseuid 406D317E02FD
    */
   public WEB3OpeAcceptAutoLoginRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 406BDE660157
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3OpeAcceptAutoLoginResponse(this);    
   }
}
@
