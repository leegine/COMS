head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqItemContentsDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ړ��e �萔��`�C���^�t�F�C�X(WEB3FeqItemContentsDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * ���ړ��e �萔��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqItemContentsDef
{
    /**
     * �������.���t
     */
    public static final String BUY = "���t";
    
    /**
     * �������.���t
     */
    public static final String SELL = "���t";

    /**
     * ���ϋ敪.�~��
     */
    public static final String JAPANESE_CURRENCY = "�~��";
    
    /**
     * ���ϋ敪.�O��
     */
    public static final String FOREIGN_CURRENCY = "�O��";

    /**
     * �ŋ敪.���
     */
    public static final String NORMAL = "���";
    
    /**
     * �ŋ敪.����
     */
    public static final String SPECIAL = "����";

    /**
     * ���s����.������
     */
    public static final String NONE = "������";
    
    /**
     * ���s����.��t
     */
    public static final String AT_MARKET_OPEN = "��t";
    
    /**
     * ���s����.����
     */
    public static final String AT_MARKET_CLOSE = "����";
    
    /**
     * ���s����.�o�����Έ���(�s��)
     */
    public static final String AT_MARKET_CLOSE_NOT_EXECUTED = "�o�����Έ���(�s��)";

    /**
     * ���������敪.��������
     */
    public static final String DAY_LIMIT = "��������";
    
    /**
     * ���������敪.�o����܂Œ���
     */
    public static final String CARRIED_ORDER = "�o����܂Œ���";

    /**
     * ��������.�t�w�l
     */
    public static final String STOP_LIMIT_PRICE = "�t�w�l";
    
    /**
     * ��������.W�w�l
     */
    public static final String W_LIMIT_PRICE = "W�w�l";

    /**
     * �����������Z�q.�ȏ�
     */
    public static final String ABOVE_BASE_PRICE = "�ȏ�";
    
    /**
     * �����������Z�q.�ȉ�
     */
    public static final String BELOW_BASE_PRICE = "�ȉ�";

    /**
     * ��������.DEFAULT�i�����w��Ȃ��j
     */
    public static final String ORDER_CONDITION_DEFAULT = "DEFAULT�i�����w��Ȃ��j";
}
@
