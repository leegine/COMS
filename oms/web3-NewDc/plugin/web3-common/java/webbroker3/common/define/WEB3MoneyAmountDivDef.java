head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MoneyAmountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���z�敪(WEB3MoneyAmountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * ���z�敪 �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3MoneyAmountDivDef
{

    /**
     * 0�FDEFAULT�i���񓚁j
     */
    public final static String DEFAULT  = "0";

    /**
     * 1�F�R�O�O���~����
     */
    public final static String LESS_THAN_THREE_MILLION  = "1";

    /**
     * 2�F�R�O�O���~�`�T�O�O���~�@@
     */
    public final static String THREE_MILLION_TO_FIVE_MILLION  = "2";

    /**
     * 3�F�T�O�O���~�`�P�C�O�O�O���~
     */
    public final static String FIVE_MILLION_TO_TEN_MILLION  = "3";

    /**
     * 4�F�P�C�O�O�O���~�`�R�C�O�O�O���~�@@
     */
    public final static String TEN_MILLION_TO_THIRTY_MILLION  = "4";

    /**
     * 5�F�R�C�O�O�O���~�`�T�C�O�O�O���~
     */
    public final static String THIRTY_MILLION_TO_FIFTY_MILLION  = "5";

    /**
     * 6�F�T�C�O�O�O���~�`�P���~
     */
    public final static String FIFTY_MILLION_TO_ONE_HUNDRED_MILLION  = "6";

    /**
     * 7�F�P���~�ȏ�
     */
    public final static String MORE_THAN_ONE_HUNDRED_MILLION  = "7";

}@
