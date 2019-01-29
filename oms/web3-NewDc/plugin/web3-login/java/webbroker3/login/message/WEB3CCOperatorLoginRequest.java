head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^���O�C����v�����郊�N�G�X�g�N���X(WEB3CCOperatorLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
 */

package webbroker3.login.message;


import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (CC�I�y���[�^���O�C�����N�G�X�g)<BR>
 * CC�I�y���[�^���O�C����v�����郊�N�G�X�g�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3CCOperatorLoginRequest extends WEB3GenRequest
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_ccoperator_login";
   
   /**
    * (��ЃR�[�h)
    */
   public String institutionCode;
   
   /**
    * (���X�R�[�h)
    */
   public String branchCode;
   
   /**
    * (CC�I�y���[�^�R�[�h)
    */
   public String ccOperatorCode;
   
   /**
    * (xTrade���[�U��)<BR>
    * ���͂��ꂽCC�I�y���[�^�R�[�h��xTrade���O�C�����[�U���ɕϊ������l�B
    */
   public String xTradeUsername;
   
   /**
    * (CC�I�y���[�^�p�X���[�h)
    */
   public String ccOperatorPassword;
   
   /**
    * �i���O�C���^�C�v�j<BR>
    * "0"�F�ʏ탍�O�C���A"1"�F�p�X���[�h�ύX�ׂ̈̃��O�C���B<BR>
    * xTrade�̃��O�C���^�C�v�Ƃ͈Ⴄ���́B<BR>
    */
   public String loginType;
   
   /**
    * (IP�A�h���X)
    */
   public String ipAddress;
   
   /**
    * @@roseuid 4045C220029B
    */
   public WEB3CCOperatorLoginRequest()
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    * @@return WEB3GenResponse
    * @@roseuid 4045C67B0097
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3CCOperatorLoginResponse(this);
   }
}
@
