head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptAutoLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�������O�C����v�����郊�N�G�X�g�N���X(WEB3AcceptAutoLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/08 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�ڋq�������O�C�����N�G�X�g�j
 * �ڋq�������O�C����v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptAutoLoginRequest extends WEB3GenRequest 
{
   public static  final String TAGNAME = "request";
   public static  final String PTYPE = "web3_accept_autologin";
   
   /**
    * (��ЃR�[�h)
    */
   public String institutionCode;
   
   /**
    * (���X�R�[�h)
    */
   public String branchCode;
   
   /**
    * �i�ڋq�R�[�h�j
    */
   public String acceptCode;
   
   /**
    * (xTrade���[�U��)<BR>
    * ���͂��ꂽ�ڋq�R�[�h��xTrade���O�C�����[�U���ɕϊ������l<BR>
    */
   public String xTradeUsername;
   
   /**
    * (�ڋq�p�X���[�h)
    */
   public String acceptPassword;
   
   /**
    * (�����`���l��)
    */
   public String orderChannel;
   
   /**
    * (�����o�H�敪)
    */
   public String orderRootDiv;
   
   /**
    * �i�n�b�V���l�j
    */
   public String hashValue;
   
   /**
    * @@roseuid 40691A1A01C4
    */
   public WEB3AcceptAutoLoginRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4062B2C00128
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptAutoLoginResponse(this);    
   }
}
@
