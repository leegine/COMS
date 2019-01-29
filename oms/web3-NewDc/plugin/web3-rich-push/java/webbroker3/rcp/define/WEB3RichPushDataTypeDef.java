head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.14.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3RichPushDataTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���b�`�N���C�A���g�v�b�V�f�[�^�^�C�v�̒萔��`�N���X(WEB3RichPushDataTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 ��(FLJ) �V�K�쐬
 */
package webbroker3.rcp.define;

import java.util.*;

/**
 * ���b�`�N���C�A���g�v�b�V�f�[�^�^�C�v�̒萔��`�N���X<br>
 *
 * @@author�@@��(FLJ)
 * @@version 1.0
 */
public class WEB3RichPushDataTypeDef
{

    /**
     * ����������t�i�M�p�������n�ȊO�j
     */
    public static final String EQUITY_ORDER_ACCEPT = "00";

    /**
     * �����M�p�������n��t
     */
    public static final String SWAP_ORDER_ACCEPT = "01";

    /**
     * ������������ʒm
     */
    public static final String EQTYPE_CHANGE_CANCEL = "02";

    /**
     * �����o���ʒm
     */
    public static final String EQTYPE_CONT = "03";

    /**
     * ���������ʒm
     */
    public static final String EQTYPE_LAPSE = "04";

    /**
     * �敨OP������t�ʒm
     */
    public static final String IFO_ORDER_ACCEPT = "10";

    /**
     * �敨OP��������ʒm
     */
    public static final String IFO_CHANGE_CANCEL = "12";

    /**
     * �敨OP�o���ʒm
     */
    public static final String IFO_CONT = "13";

    /**
     * �敨OP�����ʒm
     */
    public static final String IFO_LAPSE = "14";

    /**
     * �l�[�~���O�}�b�v
     */
    public static final Map NAME_TYPE_MAP = new Hashtable(10);

    static
    {
        NAME_TYPE_MAP.put(EQUITY_ORDER_ACCEPT, "eq_order_accept");
        NAME_TYPE_MAP.put(SWAP_ORDER_ACCEPT, "swap_order_accept");
        NAME_TYPE_MAP.put(EQTYPE_CHANGE_CANCEL, "eq_change_cancel");
        NAME_TYPE_MAP.put(EQTYPE_CONT, "eq_cont");
        NAME_TYPE_MAP.put(EQTYPE_LAPSE, "eq_lapse");
        NAME_TYPE_MAP.put(IFO_ORDER_ACCEPT, "ifo_order_accept");
        NAME_TYPE_MAP.put(IFO_CHANGE_CANCEL, "ifo_change_cancel");
        NAME_TYPE_MAP.put(IFO_CONT, "ifo_cont");
        NAME_TYPE_MAP.put(IFO_LAPSE, "ifo_lapse");
    }

}
@
