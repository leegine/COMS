head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SystemHandlingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�X�e���戵�敪�萔��`�C���^�t�F�C�X(WEB3SystemHandlingDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 ���]��(sinocom) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �V�X�e���戵�敪 �萔��`�C���^�t�F�C�X
 *
 * @@author ���]��
 * @@version 1.0
 */
public interface WEB3SystemHandlingDivDef
{

    /**
     * 0�FWEBBROKER�V�Ŏ�舵��Ȃ��@@�@@  �@@�@@
     */
    public final static String WEBBROKER3_DONOT_TREAT_IT_IN = "0";

    /**
     * 1�FWEBBROKER�V�Ŏ�舵��
     */
    public final static String WEBBROKER3_TREAT_IT_IN = "1";
  
    /**
     * 2:�X�������̂� (null�̏ꍇ�A�ύX�����Ƃ���)
     */
    public final static String MAIL_REQUEST_ONLY = "2";
}
@
