head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPClaimReasonDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������R��`�C���^�[�t�F�[�X(WEB3AdminTPClaimReasonDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/09�@@�I�O(���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.define;

/**
 * �������R�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author �I�O
 * @@version 1.0
 */
public interface WEB3AdminTPClaimReasonDef
{
    /**
     * 1�F���֋�/���ʗ��֋�
     */
    public static final String DEBIT_AMOUNT_SPECIAL = "1";

    /**
     * 2�F�s����(����)
     */
    public static final String SHORT_FALL_GENERATION_TODAY = "2";

    /**
     * 3�F30%����Ǐ�
     */
    public static final String BREAK30_ADDITIONAL = "3";

    /**
     * 4�F20%����Ǐ�
     */
    public static final String BREAK20_ADDITIONAL = "4";

    /**
     * 5�F�w��Ȃ�
     */
    public static final String DEFAULT= "5";

}
@
