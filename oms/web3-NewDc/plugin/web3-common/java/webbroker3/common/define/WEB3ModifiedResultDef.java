head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ModifiedResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ModifiedResultDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * �������ʃR�[�h�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ModifiedResultDef
{
    /**
     * 01 : �S������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@  �������
     */
    public static final String ALL_CANCEL = "01";

    /**
     * 02 : �S������(���o���Ȃ�)�@@�@@�@@�@@�@@  �������� 
     */
    public static final String ALL_CHANGED_NO_EXECUTED = "02";

    /**
     * 03 : �S������(���o������)�@@�@@�@@�@@�@@  ��������
     */
    public static final String ALL_CHANGED_PARTIALLY_EXECUTED = "03";

    /**
     * 04 : �ꕔ����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@  �������
     */
    public static final String PARTIALLY_CANCEL = "04";

    /**
     * 05 : �ꕔ�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@  ��������
     */
    public static final String PARTIALLY_CHANGED = "05";

    /**
     * 06 : �ꕔ����s�\(���o���Ȃ�)       �������
     */
    public static final String PARTIALLY_NOT_CANCEL_NO_EXECUTED = "06";

    /**
     * 07 :  �ꕔ����s�\(���o������)       �������
     */
    public static final String PARTIALLY_NOT_CANCEL_EXECUTED = "07";

    /**
     * 08 : �ꕔ�����s�\(���o���Ȃ�)       ��������
     */
    public static final String PARTIALLY_NOT_CHANGED_NO_EXECUTED = "08";

    /**
     * 09 : �ꕔ�����s�\(���o������)       ��������
     */
    public static final String PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED = "09";

    /**
     * 10 : �S�����ς�                  �������s�A������s
     */
    public static final String ALL_EXECUTED = "10";

    /**
     * 11 : �n�l����ς�                  �������s
     */
    public static final String OPENING_RATE_DECIDED = "11";

    /**
     * 20 : �����`�[�j��                  �������s�A������s
     */
    public static final String CHANGE_SLIP_DESTORIED = "20";

    /**
     * 21 : �Ԃɍ��킸                    �������s�A������s
     */
    public static final String MAKESHIFT = "21";

}
@
