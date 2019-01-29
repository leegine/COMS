head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq�p�X���[�h�`�F�b�N��v�����郊�N�G�X�g�N���X(WEB3AcceptPasswordCheckRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 �V���@@�h�O(FLJ) �V�K�쐬
*/
 
package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�ڋq�p�X���[�h�`�F�b�N���N�G�X�g)<BR>
 * �ڋq�p�X���[�h�`�F�b�N��v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 */
public class WEB3AcceptPasswordCheckRequest extends WEB3GenRequest 
{
   
   /**
    * TAGNAME
    */
   public static  final String TAGNAME = "request";
   
   /**
    * PTYPE
    */
   public static  final String PTYPE = "web3_password_check";
   
   /**
    * SerialVersionUID
    */
   public static  final long serialVersionUID = 200510281830L;
   
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
    * (�ڋqID)<BR>
    * Affinity�Ή���accountID
    */
   public String account_id;

   /**
    * (�ڋq�p�X���[�h)
    */
   public String acceptPassword;
   
   /**
    * �R���X�g���N�^
    */
   public WEB3AcceptPasswordCheckRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptPasswordCheckResponse(this);    
   }
}
@
