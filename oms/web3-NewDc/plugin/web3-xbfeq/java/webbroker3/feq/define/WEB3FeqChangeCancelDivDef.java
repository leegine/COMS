head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������敪���x����`�C���^�t�F�C�X(WEB3FeqChangeCancelDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/11 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * ��������敪���x����`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqChangeCancelDivDef
{
    /**
     * 0�F�����l
     */
    public static final String INITIAL_VALUE = "0";
    
    /**
     * 1�F�����
     */
    public static final String CANCELING = "1";

    /**
     * 2�F�ꕔ�������
     */
    public static final String PART_CANCELED = "3";

    /**
     * 3�F�S���������
     */
    public static final String CANCELED = "3";

    /**
     * 4�F������s
     */
    public static final String CANCEL_ERROR = "4";

    /**
     * 5�F������
     */
    public static final String CHANGING = "5";

    /**
     * 6�F�ꕔ��������
     */
    public static final String PARTIALLY_CHANGED = "6";

    /**
     * 7�F�S����������
     */
    public static final String CHANGED = "7";

    /**
     * 8�F�������s
     */
    public static final String CHANGE_ERROR = "8";

    /**
     * 9�F������V�K����
     */
    public static final String CHANGED_NEW_ORDER = "9";
}
@
