head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentMethodDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��n���@@  �萔��`�N���X(WEB3PaymentMethodDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 ���E (sinocom) �V�K�쐬
Revesion History : 2004/09/24 �d�� (���u) ����
*/
package webbroker3.common.define;

/**
 * ��n���@@  �萔���`����B
 *
 * @@author ���E (sinocom)
 * @@version 1.0
 */
public interface WEB3PaymentMethodDef
{

    /**
     * �I���w��
     */
    public final static String SELECT_APP = "0";

    /**
     * ��s�U����
     */
    public final static String BANK_TRANSFER = "1";

    /**
     * �،���������
     */
    public final static String SECURITIES_ACCOUNT_INPUT = "2";
    
    /**
     *  �U�F�X�֐U�� 
     */
    public static final String MAIL_TRANSFER = "6";

    /**
     *  �V�F��s�U���iNET�j 
     */
    public static final String BANK_TRANSFER_NET = "7";

    /**
     *  �X�F��s�U���i���ʁj
     */
    public static final String BANK_TRANSFER_GENERAL = "9";

}
@
