head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransferRangeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�o�͈�(WEB3TransferRangeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
Revesion History : 2007/06/06 �h�C(���u) �c�a���C�A�E�g(�e��A���e�[�u��)�ɂ��
*/

package webbroker3.common.define;

/**
 * �U�o�͈� �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3TransferRangeDef
{

    /**
     * 1�F�S����
     */
    public final static String ALL_ACCOUNT  = "1";

    /**
     * 2�F�ی�a�����
     */
    public final static String SAFE_CUSTODY_ACCOUNT  = "2";

    /**
     * 3�F�ϗ��S����
     */
    public final static String RESERVE_ALL_ACCOUNT  = "3";

    /**
     * 4�F�ϗ��ʌ������͌ʖ���
     */
    public final static String RESERVE_INDIVIDUAL_ACCOUNT  = "4";

    /**
     * 5�F������
     */
    public final static String PUBLIC_BOND  = "5";

    /**
     * 6�F�~�݌��O�݂�����������
     */
    public final static String EN_EXCEPT_FOREIGN_DOMESTIC_BOND  = "6";

    /**
     * A�F���p���o���
     */
    public final static String SALE_TURNOVER  = "A";

    /**
     * B�F���p���o����Q
     */
    public final static String SALE_TURNOVER_2  = "B";

    /**
     * C�F���p���o����R
     */
    public final static String SALE_TURNOVER_3  = "C";

}@
