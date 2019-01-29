head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��҃��O�C����v�����郊�N�G�X�g�N���X(WEB3AdministratorLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��҃��O�C�����N�G�X�g�j
 * �Ǘ��҃��O�C����v�����郊�N�G�X�g�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorLoginRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_administrator_login";
   
   /**
    * �i��ЃR�[�h�j
    */
   public String institutionCode;
   
   /**
    * �i���X�R�[�h�j<BR>
    * 2004.04.21���_�ł̓_�~�[�B���X�͊Ǘ��ҁD���XID�̂��̂��g�p����B<BR>
    */
   public String branchCode;
   
   /**
    * �i�Ǘ��҃R�[�h�j
    */
   public String administratorCode;
   
   /**
    * �ixTrade���[�U���j<BR>
    * ���͂��ꂽ�Ǘ��҃R�[�h��xTrade���O�C�����[�U���ɕϊ������l<BR>
    */
   public String xTradeUsername;
   
   /**
    * �i�Ǘ��҃p�X���[�h�j
    */
   public String administratorPassword;
   
   /**
    * �i���O�C���^�C�v�j<BR>
    * "0"�F�ʏ탍�O�C���A"1"�F�p�X���[�h�ύX�ׂ̈̃��O�C��<BR>
    */
   public String loginType;
   
   /**
    * @@roseuid 408F4C930396
    */
   public WEB3AdministratorLoginRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4084F8EC01BF
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AdministratorLoginResponse(this);
   }
}
@
