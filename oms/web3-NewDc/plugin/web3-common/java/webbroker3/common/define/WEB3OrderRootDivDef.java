head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderRootDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o�H�敪�萔��`�N���X(WEB3OrderRootDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/01 �e�n(SRA) �V�K�쐬
Revesion History : 2006/09/14 �h�C(���u) �d�l�ύX �c�a���C�A�E�g115�A116
Revesion History : 2007/02/13 �L���E�ĕ�(���u) IVR�Ή�
Revesion History : 2007/04/23 �h�C(���u) �d�l�ύX �c�a���C�A�E�g144
*/
package webbroker3.common.define;

/**
 * �����o�H�敪 �萔��`�N���X
 *
 * @@author �e�n(SRA)
 * @@version 1.0
 */
public interface WEB3OrderRootDivDef
{
    /**
     * �R�[���Z���^�[�i�ڋq���肷�܂��j
     */
     public static final String CALLCENTER = "1";
     
     /**
      * PC�i�ʏ�̃u���E�U�j
      */
     public static final String PC = "2";
     
     /**
      * �X�����O�V���b�g�i�����A�g�ɂ�����j
      */
     public static final String SLINGSHOT = "3";
     
     /**
      * i-mode
      */
     public static final String I_MODE = "4";
     
     /**
      * Vodafone
      */
     public static final String VODAFONE = "5";
     
     /**
      * AU
      */
     public static final String AU = "6";
     
    /**
     * �X�����O�V���b�g(����)
     */
    public static final String SLINGSHOT_NO_TOLL = "7";
     
     /**
      * HOST
      */
     public static final String HOST = "9";
     
    /**
     * �Ǘ���
     */
     public static final String ADMIN = "A";
     
    /**
     * �ۏ؋������U�փo�b�`
     */
     public static final String DEPOSIT_AUTO_TRANSFER_BATCH = "B";

    /**
     * C�F���b�`�N���C�A���g�iRICH_CLIENT�j
     */
    public static final String RICH_CLIENT = "C";

    /**
     * D�F�����A�g
     */
    public static final String CASH_IN_COOPERATION = "D";

    /**
     * E�F�U�֒���(�a��������؋�
     */
    public static final String FROM_DEPOSIT_AMOUNT_DSK = "E";
    
    /**
     * F�FIVR(���������d�b�j
     */
    public static final String IVR = "F";

    /**
     * G�F��������
     */
    public static final String FORCED_SETTLE = "G";
}
@
