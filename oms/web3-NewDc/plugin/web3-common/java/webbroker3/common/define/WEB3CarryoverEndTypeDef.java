head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CarryoverEndTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����J�z�����敪�@@�萔��`�C���^�t�F�C�X(WEB3CarryoverEndTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 羐� (���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �����J�z�����敪�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author 羐�
 * @@version 1.0
 */
public interface WEB3CarryoverEndTypeDef
{
    /**
     * 0�F������ 
     */
    public static final String NOT_STARTED_PROCESS = "0";

    /**
     * 1�F�����ρ@@ 
     */
    public static final String COMPLETE_PROCESS = "1";

    /**
     * 2�F�����J�zAP�ďo��
     */
    public static final String CALL_CARRYOVER_AP = "2";

    /**
     * 9�F�G���[
     */
    public static final String ERROR = "9";

}@
