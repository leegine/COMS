head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptSlingshotRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋqSS�J�ڂ�v�����郊�N�G�X�g�N���X(WEB3AcceptSlingshotRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/30 �e�n(SRA) �V�K�쐬
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�ڋqSS�J�ڃ��N�G�X�g�j
 * �ڋqSS�J�ڂ�v�����郊�N�G�X�g�N���X<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptSlingshotRequest extends WEB3GenRequest 
{
   public static  final String TAGNAME = "request";
   public static  final String PTYPE = "web3_accept_slingshot";
   
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
    * �i�������T�[�r�X�R�[�h�j
    */
   public String infoServiceCode;
   
   /**
    * @@roseuid 406906C60203
    */
   public WEB3AcceptSlingshotRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 40627FDA001F
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptSlingshotResponse(this);    
   }
}
@
