head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���O�C����v�����郊�N�G�X�g�N���X(WEB3AcceptLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/30 �e�n(SRA) �V�K�쐬
Revesion History    : 2007/06/13 �Ӑ� �d�l�ύX���f��No.033
 */
 
package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�ڋq���O�C�����N�G�X�g)<BR>
 * �ڋq���O�C����v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptLoginRequest extends WEB3GenRequest 
{
   
   /**
    * TAGNAME
    */
   public static  final String TAGNAME = "request";
   
   /**
    * PTYPE
    */
   public static  final String PTYPE = "web3_accept_login";
   
   /**
    * SerialVersionUID
    */
   public static  final long serialVersionUID = 200402141830L;
   
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
      * �ڋqID
      * Login���̂Ȃ񂿂����accountID
      */
     public String account_id;

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
    * (IP�A�h���X)
    */
   public String ipAddress;

   /**
    * (���q�l���ʔԍ�)
    */
   public String discriminationCode;

   /**
    * @@roseuid 403EDB16018D
    */
   public WEB3AcceptLoginRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 403EECCE0390
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptLoginResponse(this);    
   }
}
@
