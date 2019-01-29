head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��҃��O�C�����ʂ�Ԃ����X�|���X�N���X(WEB3AdministratorLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�Ǘ��҃��O�C�����X�|���X�j
 * �Ǘ��҃��O�C�����ʂ�Ԃ����X�|���X�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorLoginResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_administrator_login";
   
   /**
    * �ixTrade�Z�b�V����ID�j
    */
   public String xTradeSessionID;
   
   /**
    * �i�Ǘ��ҏ��j
    */
   public WEB3AdministratorInfo administratorInfo;
   
   /**
    * �i��Џ��j
    */
   public WEB3InstitutionInfo institutionInfo;
   
   /**
    * �i���X���j
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * �i�p�X���[�h�ύX�t���O�j<BR>
    * ���O�C�����p�X���[�h�ύX�̗L����\���t���O<BR>
    * �@@"0"�F�ύX�Ȃ�<BR>
    * �@@"1"�F�ύX�K�v<BR>
    */
   public String passwordChangeFlag;
   
   /**
    * (�p�X���[�h�ŏ���)<BR>
    */
   public String passwordMinLength;
   
   /**
    * (�p�X���[�h�ő咷)<BR>
    */
   public String passwordMaxLength;
   
   /**
    * (�p�X���[�h�`�F�b�N����)<BR>
    *  <BR>
    * 1�F�@@�����̂� <BR>
    * 2�F�@@�p���� <BR>
    * 3�F�@@�p�������� <BR>
    * <BR>
    * �� WEB3CodeCheckModeDef�ɂĒ�`��<BR> 
    */
   public String passwordCheckMethod;
   
   /**
    * @@roseuid 408F4D0300C8
    */
   public WEB3AdministratorLoginResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<B
    * R>
    * @@param l_request
    * @@roseuid 4084F9A202B9
    */
   public WEB3AdministratorLoginResponse(WEB3AdministratorLoginRequest l_request) 
   {
       super(l_request);
   }
}
@
