head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3UpdateAccessTimeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Z�b�V�����A�N�Z�X�����X�V��v�����郊�N�G�X�g�N���X(WEB3UpdateAccessTimeRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 �e�n(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Z�b�V�����A�N�Z�X�����X�V���N�G�X�g)<BR>
 * �Z�b�V�����A�N�Z�X�����X�V��v�����郊�N�G�X�g�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3UpdateAccessTimeRequest extends WEB3GenRequest 
{
   /**
    * TAGNAME<BR>
    */
   public static final String TAGNAME = "request";
   
   /**
    * PTYPE<BR>
    */
   public static final String PTYPE   = "web3_update_access_time";
   
   /**
    * SerialVersionUID<BR>
    */
   public final static long serialVersionUID = 200402261800L;
   
   /**
    * �f�t�H���g�R���X�g���N�^�B<BR>
    * @@roseuid 403EF0E80267
    */
   public WEB3UpdateAccessTimeRequest() 
   {
    
   }
   
   /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    * <BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    * @@roseuid 403EF0440277
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3UpdateAccessTimeResponse(this);
   }
}
@
