head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ApproveStatusType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���F��ԋ敪�萔��`�C���^�t�F�C�X(WEB3ApproveStatusType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ���F��ԋ敪 �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3ApproveStatusType
{
    /**
     * 0�F�����F
     */
    public final static String UNAPPROVED = "0";

    /**
     * 1�F���F��
     */
    public final static String APPROVED = "1";

    /**
     * 2�F�񏳔F
     */
    public final static String NON_APPROVED = "2";

    /**
     * 9�F�G���[
     */
    public final static String ERROR = "9";
}
@
