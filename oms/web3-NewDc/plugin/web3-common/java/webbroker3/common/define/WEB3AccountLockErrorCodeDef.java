head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountLockErrorCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq���b�N�G���[�R�[�h�萔��`�C���^�[�t�F�C�X(WEB3AccountLockErrorCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/24 ������(sinocom) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �ڋq���b�N�G���[�R�[�h�萔��`�C���^�[�t�F�C�X
 *
 * @@author ������
 * @@version 1.0
 */
public interface WEB3AccountLockErrorCodeDef
{

    /**
     * 1900�F��d�o�^�G���[
     */
    public static final String DOUBLE_REGI_ERROR = "1900";

    /**
     * FF51�F�����σG���[
     */
    public static final String ERASED_ERROR = "FF51";

    /**
     * FF72�F���b�N�G���[
     */
    public static final String LOCK_ERROR = "FF72";

    /**
     * FF70�F�����G���[
     */
    public static final String LOCK_OFF_ERROR = "FF70";

    /**
     * FF73�F�F�G���[
     */
    public static final String PERMISSION_ERROR = "FF73";

    /**
     * 1E00�F����敪�G���[
     */
    public static final String CANCEL_DIV_ERROR = "1E00";

    /**
     * 2700�F�Y������
     */
    public static final String NO_DATA_ERROR = "2700";
} @
