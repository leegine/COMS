head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BankAccountRegiDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3BankAccountRegiDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2007/03/09 �h�C (���u) �d�l�ύX�E�c�a���C�A�E�gNo.471��Ή�
*/
package webbroker3.common.define;

/**
 * �U����i��s�����j�o�^�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3BankAccountRegiDef
{
    /**
     * 0 : ���o�^
     */
    public static final String NOT_REGISTER = "0";

    /**
     * 1:�~�݂̂ݓo�^��
     */
    public static final String ALREADY_REGISTER = "1";

    /**
     * 2:�O�݂̂ݓo�^��
     */
    public static final String ONLY_FOREIGN_CURRENCY_REGISTERED = "2";

    /**
     * 3:�����o�^��
     */
    public static final String BOTH_REGISTERED = "3";
}
@
