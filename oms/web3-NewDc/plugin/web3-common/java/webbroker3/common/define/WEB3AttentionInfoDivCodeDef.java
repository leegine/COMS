head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AttentionInfoDivCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��敪�R�[�h�萔��`�C���^�t�F�C�X(WEB3AttentionInfoDivCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/04 ��іQ(���u) �V�K�쐬
*/

package webbroker3.common.define;

/**
 * ���ӏ��敪�R�[�h�萔��`�C���^�t�F�C�X<BR>
 * (���ӏ�񗚗��e�[�u���̒��ӏ��敪�R�[�h�̙ҏ�)<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public interface WEB3AttentionInfoDivCodeDef
{
    /**
     * A001�F������~�i������t�j
     */
    public final static String TRADE_STOP_ORDER_ACCEPT_ENABLE = "A001";

    /**
     * A002�F������~�i������t�s�j
     */
    public final static String TRADE_STOP_ORDER_ACCEPT_DISABLE = "A002";

    /**
     * A003�F������~�i������t�j�̎��
     */
    public final static String TRADE_STOP_CANCEL_ORDER_ACCEPT_ENABLE = "A003";

    /**
     * A004�F������~�i������t�s�j�̎��
     */
    public final static String TRADE_STOP_CANCEL_ORDER_ACCEPT_DISABLE = "A004";

    /**
     * A005�F������~�i������t�j�̉���
     */
    public final static String TRADE_STOP_RELEASE_ORDER_ACCEPT_ENABLE = "A005";

    /**
     * A006�F������~�i������t�s�j�̉���
     */
    public final static String TRADE_STOP_RELEASE_ORDER_ACCEPT_DISABLE = "A006";

    /**
     * A007�F������~�i������t�j�̉����̎��
     */
    public final static String TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_ENABLE = "A007";

    /**
     * A008�F������~�i������t�s�j�̉����̎��
     */
    public final static String TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_DISABLE = "A008";

    /**
     * A011�F�������f
     */
    public final static String TRADE_INTERRUPT = "A011";

    /**
     * A012�F�������f�̎��
     */
    public final static String TRADE_INTERRUPT_CANCEL = "A012";

    /**
     * A013�F�������f�̉���
     */
    public final static String TRADE_INTERRUPT_RELEASE = "A013";

    /**
     * A014�F�������f�̉����̎��
     */
    public final static String TRADE_INTERRUPT_RELEASE_CANCEL = "A014";

    /**
     * A031�F�V�K�������̏��l���t�����ꍇ
     */
    public final static String OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE = "A031";

    /**
     * A081�F�t���[�t�H�[�}�b�g
     */
    public final static String FREE_FORMAT = "A081";
}@
