head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L���[�e�[�u�������敪 �萔��`�C���^�t�F�C�X(WEB3HostStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �L���[�e�[�u�������敪�@@�萔��`�C���^�t�F�C�X<BR>
 *
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public interface WEB3HostStatusDef
{
    /**
     * 0�F������<BR>
     */
    public static final String NOT_STARTED_PROCESS = "0";

    /**
     * 1�F�����ς�<BR>
     */
    public static final String COMPLETE_PROCESS = "1";

    /**
     * 2�F������<BR>
     */
    public static final String STARTED_PROCESS = "2";

    /**
     * 8�F�v���O�����G���[<BR>
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * 9�F�f�[�^�G���[<BR>
     */
    public static final String DATA_ERROR = "9";
}
@
