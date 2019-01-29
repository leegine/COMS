head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���O�C�����ʂ�Ԃ����X�|���X�N���X(WEB3AcceptLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 �e�n(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;


/**
 * (�ڋq���O�C�����X�|���X)<BR>
 * �ڋq���O�C�����ʂ�Ԃ����X�|���X�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3AcceptLoginResponse extends WEB3GenResponse 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "response";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_accept_login";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200402141830L;
   
   /**
    * (xTrade�Z�b�V����ID)
    */
   public String xTradeSessionID;
   
   /**
    * (�ڋq���)
    */
   public WEB3AcceptInfo acceptInfo;
   
   /**
    * (��Џ��)
    */
   public WEB3InstitutionInfo institutionInfo;
   
   /**
    * (���X���)
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * (�T�[�r�X���{���)
    */
   public WEB3ServiceImpState serviceImpState;
   
   /**
    * (�p�X���[�h�ύX�t���O)<BR>
    * ���O�C�����p�X���[�h�ύX�̗L����\���t���O<BR>
    * �@@"0"�F�ύX�Ȃ�<BR>
    * �@@"1"�F�ύX�K�v<BR>
    */
   public String passwordChangeFlag;
   
   /**
    * (�擪���ID)<BR>
    * ���[�U�w��̐擪��ʂ�\��ID<BR>
    */
   public String topPageID;
   
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
    * @@roseuid 4021A5190138
    */
   public WEB3AcceptLoginResponse() 
   {
    
   }
   
   /**
    * �R���X�g���N�^�B<BR>
    * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g����Ƀ��X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    * @@param l_request
    * @@roseuid 403EF23602B6
    */
   public WEB3AcceptLoginResponse(WEB3AcceptLoginRequest l_request)
   {
       super(l_request);
   }
}
@
