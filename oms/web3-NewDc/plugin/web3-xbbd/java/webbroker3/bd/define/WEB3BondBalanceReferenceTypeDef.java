head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�ʏƉ�敪�萔��`�C���^�t�F�C�X(WEB3BondBalanceReferenceTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/3/9 ꎉ�(���u) �V�K�쐬
Revesion History : 2007/07/17 ���g   (���u) �d�l�ύX�E���f��207
*/

package webbroker3.bd.define;

/**
 * ���c���Ɖ�ʏƉ�敪�萔��`�C���^�t�F�C�X
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public interface WEB3BondBalanceReferenceTypeDef
{
    /**
     * �P�F���ׂĂ̖���
     */
    public static final String ALL_PRODUCT = "1";

    /**
     * �Q�F�O���������̂�
     */
    public static final String FOREIGN_BOND_ONLY = "2";

    /**
     * 3�F������(�l���������܂ށj
     */
    public static final String DOMESTIC_BOND = "3";

    /**
     * 4�F������(�l���������܂܂Ȃ��j
     */
    public static final String DOMESTIC_BOND_EXCEPT_INDIVIDUAL = "4";

    /**
     * 5�F�l�������̂�
     */
    public static final String DOMESTIC_BOND_INDIVIDUAL = "5";
}@
