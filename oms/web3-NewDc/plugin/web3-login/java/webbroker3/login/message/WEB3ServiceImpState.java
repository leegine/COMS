head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3ServiceImpState.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���{��Ԃ�xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X(WEB3ServiceImpState.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/10 �e�n(SRA)
 */

package webbroker3.login.message;


import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���{���)<BR>
 * �T�[�r�X���{��Ԃ�xTrade�N���C�A���g�ɓn���ׂ̃��b�Z�[�W�N���X<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 */
public class WEB3ServiceImpState extends Message 
{
   /**
    * ������񃊃A���f�B���C�敪�i���A���j<BR>
    */
   public static final String QUICK_REAL = "07_Quick_Real";
   
   /**
    * ������񃊃A���f�B���C�敪�i�f�B���C�j<BR>
    */
   public static final String QUICK_DELAY = "07_Quick_Delay";
   
   /**
    * (������񃊃A���f�B���C�敪)<BR>
    * "07_Quick_Real" �F���A��<BR>
    * "07_Quick_Delay"�F�f�B���C<BR>
    * ���ύX����K�v���Ȃ��Ǝv���̂ŁA���s�ɍ��킹�鎖�Ƃ���B<BR>
    * �@@�S�̂ƒ�������ہA�ύX���K�v�ƂȂ邩���m��Ȃ��B<BR>
    */
   public String quickDiv;
   
   /**
    * @@roseuid 4021A3810186
    */
   public WEB3ServiceImpState() 
   {
    
   }
}
@
