head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleOpenStatusEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleBasedMarketAdapterBasePlugin�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/05/19 �� �V�K�쐬
*/
package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
/**
 * 0(CLOSED),1(OPEN) �X�e�[�^�X�����܂܂Ȃ��X�e�[�^�X�G�i���N���X
 * @@author  : ���iFLJ�j
 * @@version : 1.0
 */
public class SleOpenStatusEnum extends Enumerated {
	  /**0�F�N���[�Y */
	  public static final SleOpenStatusEnum CLOSE = new SleOpenStatusEnum(IntValues.CLOSE, "CLOSE");

	  /**1�F�I�[�v�� */
	  public static final SleOpenStatusEnum OPEN =  new SleOpenStatusEnum(IntValues.OPEN,"OPEN");
    
	  public SleOpenStatusEnum(int i, String s)
	  {
		  super(i, s);    
	  }
    
	  /**
	   * �C�ӂ̃G�i���̐����l��`����������N���X�ł��B�����l���`���邱�Ƃɂ��A 
	   * ���̃N���X��switch���ŗe�Ղɗ��p���邱�Ƃ��ł��܂��B
	   */
	  public static class IntValues {

		  //~ Static fields/initializers ---------------------------------------------

		/**0�F �N���[�Y*/
		public static final int CLOSE = 0;
		
		/**1:  �I�[�v�� */
		public static final int OPEN = 1;
	  }    
}
@
