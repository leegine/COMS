head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetSessionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Z�b�V���������ݒ��v�����郊�N�G�X�g�N���X(WEB3SetSessionRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 �e�n(SRA)
 */
 
package webbroker3.login.message;


import java.util.*;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Z�b�V���������ݒ胊�N�G�X�g)<BR>
 * �Z�b�V���������ݒ��v�����郊�N�G�X�g�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3SetSessionRequest extends WEB3GenRequest 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "request";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_set_session";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200402251830L;
   
   /**
    * �ݒ肷��Z�b�V�����������i�[����B<BR>
    */
   public Hashtable sessionAttributes = new Hashtable();
   
   /**
    * �f�t�H���g�R���X�g���N�^�B
    */
   public WEB3SetSessionRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3SetSessionResponse(this);
   }
}
@
