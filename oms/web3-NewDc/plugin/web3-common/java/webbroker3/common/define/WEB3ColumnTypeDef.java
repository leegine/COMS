head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ColumnTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ڌ^(WEB3ColumnTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * ���ڌ^ �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3ColumnTypeDef
{

    /**
     * 0�F������
     */
    public final static String CHARACTER_STRING  = "0";

    /**
     * 10�F���l�iint�j
     */
    public final static String INT_NUMBER  = "10";

    /**
     * 11�F���l�ilong�j
     */
    public final static String LONG_NUMBER  = "11";

    /**
     * 12�F���l�idouble�j
     */
    public final static String DOUBLE_NUMBER  = "12";

    /**
     * 20�F���t
     */
    public final static String DATE  = "20";

    /**
     * 21�F���t���ԁiCSV�񃂃f���ɒ�`�j
     */
    public final static String DATE_TIME  = "21";

}@
