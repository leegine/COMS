head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExpirationDateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  ���������敪�@@�萔��`�C���^�t�F�C�X(WEB3OrderExpirationDateTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 羐� (���u) �V�K�쐬
Revesion History : 2007/06/12 �h�C(���u) �[��܂Œ�����ǉ�
*/
package webbroker3.common.define;

/**
 * ���������敪�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author 羐�
 * @@version 1.0
 */
public interface WEB3OrderExpirationDateTypeDef
{
    /**
      * 1�F�������� 
      */
    public static final String DAY_LIMIT = "1";

    /**
      * 2�F�o����܂Œ���
      */
    public static final String CARRIED_ORDER = "2";

    /**
      * 3�F�[��܂Œ���
      */
    public static final String EVENING_SESSION_ORDER = "3";
}@
