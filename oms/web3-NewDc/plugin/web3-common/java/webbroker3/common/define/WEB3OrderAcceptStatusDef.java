head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������t�X�e�[�^�X �萔��`�C���^�t�F�C�X(WEB3OrderAcceptStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 ����@@���j(SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ������t�X�e�[�^�X�@@�萔��`�C���^�t�F�C�X
 *
 * @@author ����@@���j(SRA)
 * @@version 1.0
 */
public interface WEB3OrderAcceptStatusDef
{

    /**
     * 0 : �ʏ�
     */
    public static final String NORMAL = "0";
    
    /**
     * 1 : �o�b�`������
     */
    public static final String BATCH = "1";

    /**
     * 2 : �ً}��~��
     */
    public static final String SCRAM = "2";
}
@
