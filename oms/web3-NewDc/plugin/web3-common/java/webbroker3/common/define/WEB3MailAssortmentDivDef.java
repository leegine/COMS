head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MailAssortmentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[����ʋ敪�萔��`�C���^�t�F�C�X(WEB3MailAssortmentDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/23 ��іQ(���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * ���[����ʋ敪�萔��`�C���^�t�F�C�X<BR>
 * (���[����ʃe�[�u���̃��[����ʋ敪�̙ҏ�)<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public interface WEB3MailAssortmentDivDef
{
    /**
     * 1:������胁�[��
     */
    public final static String EQUITY_ORDER_MAIL = "1";

    /**
     * 2:��������胁�[��
     */
    public final static String EQUITY_NOT_ORDER_MAIL = "2";

    /**
     * 3:��OP��胁�[�� 
     */
    public final static String FUTURES_OPTION_ORDER_MAIL = "3";

    /**
     * 4:��OP����胁�[��
     */
    public final static String FUTURES_OPTION_NOT_ORDER_MAIL = "4";

    /**
     * 5:�d�v���[��
     */
    public final static String IMPORTANT_MAIL = "5";

    /**
     * 6:�ē����[��
     */
    public final static String GUIDE_MAIL = "6";

    /**
     * 7:�d�q��t���[��
     */
    public final static String ELECTRONIC_DELIVER_MAIL = "7";
}
@
