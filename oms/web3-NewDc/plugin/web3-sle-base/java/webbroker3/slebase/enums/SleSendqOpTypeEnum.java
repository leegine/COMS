head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendqOpTypeEnum.java;


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
 Revision History : 2006/05/2 �� �V�K�쐬
 */

package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;


/**
 *  SEND_Q�̃I�y���[�^�^�C�v�Ɋւ���G�i���萔���`���܂��B
 */
public class SleSendqOpTypeEnum extends Enumerated {

	/** �V�K����������킵�܂��B */
	public static final SleSendqOpTypeEnum NEW_ORDER = new SleSendqOpTypeEnum(IntValues.NEW_ORDER,
		  "NEW_ORDER");

	/** �ύX����������킵�܂��B */
	public static final SleSendqOpTypeEnum CHANGE_ORDER= new SleSendqOpTypeEnum(IntValues.CHANGE_ORDER,
		  "CHANGE_ORDER");

	/** �������������킵�܂��B */
	public static final SleSendqOpTypeEnum CANCEL_ORDER= new SleSendqOpTypeEnum(IntValues.CANCEL_ORDER,
		  "CANCEL_ORDER");

	/**
	 * ��ʃN���X�̃R���X�g���N�^���I�[�o���C�h����K�{�̃R���X�g���N�^�ł��B 
	 *
	 * @@param i int�^�̒l
	 * @@param s ������\��
	 */
	public SleSendqOpTypeEnum(int i, String s) {
		super(i, s);
	}

	/**
	 * �C�ӂ̃G�i���̐����l��`����������N���X�ł��B�����l���`���邱�Ƃɂ��A 
	 * ���̃N���X��switch���ŗe�Ղɗ��p���邱�Ƃ��ł��܂��B
	 */
	public static class IntValues {

		//~ Static fields/initializers ---------------------------------------------

		/** �V�K����������킵�܂��B */
		public static final int NEW_ORDER = 0;

		/** �ύX����������킵�܂��B */
		public static final int CHANGE_ORDER = 1;

		/** �������������킵�܂��B */
		public static final int CANCEL_ORDER = 2;
	}
}
@
