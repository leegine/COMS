head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���i�敪�萔��`�C���^�t�F�C�X(WEB3BondProductDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/10 ���n�m (���u) �V�K�쐬
*/

package webbroker3.bd.define;

/**
 * ���i�敪�萔��`�C���^�t�F�C�X
 *
 * @@author ���n�m
 * @@version 1.0
 */
public interface WEB3BondProductDivDef
{

    /**
     * 1�F���ׂĂ̖���
     */
    public static final String ALL_PRODUCT = "1";

    /**
     * 2�F�O���������̂�
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

}
@
