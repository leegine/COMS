head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DaemonTriggerTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �f�[�����g���K�[�e�[�u���E�����^�C�v �萔��`�C���^�t�F�C�X(WEB3DaemonTriggerTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/16 ������ (���u) �V�K�쐬 ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.351��Ή�
Revesion History : 2006/04/04 ������ (���u) �C�� ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.360-362��Ή�
Revesion History : 2006/05/11 ������ (���u) �C�� �c�a���C�A�E�g(�f�[�����g���K�[�e�[�u��)�ɂ��
Revesion History : 2006/05/19 �R�c���a (SCS) �C�� �c�a���C�A�E�g(�f�[�����g���K�[�e�[�u��)�ɂ��
Revesion History : 2006/05/26 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g373
Revesion History : 2007/01/25 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g453
Revesion History : 2007/04/23 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g477�A478
Revesion History : 2008/03/31 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g618
Revesion History : 2008/04/07 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g619
*/

package webbroker3.common.define;

/**
 * �f�[�����g���K�[�e�[�u���E�����^�C�v�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author ������
 * @@version 1.0
 */
public interface WEB3DaemonTriggerTypeDef
{
    /**
     * 1:���(���E�M�p)
     */
    public static final String EXECUTE_EQUITY_MARGIN = "1";

    /**
     * 2:����(���E�M�p)
     */
    public static final String EXPIRE_EQUITY_MARGIN = "2";

    /**
     * 3:������t�i���E�M�p�j
     */
    public static final String ORDER_ACCEPT_EQUITY_MARGIN = "3";

    /**
     * 4:��������ʒm�i���E�M�p�j
     */
    public static final String CHANGE_CANCEL_EQUITY_MARGIN = "4";

    /**
     * 5:��� (�敨)
     */
    public static final String EXECUTE_FUTURE = "5";

    /**
     * 6:��� (OPTION)
     */
    public static final String EXECUTE_OPTION = "6";

    /**
     * 7:����(�敨/OPTION)
     */
    public static final String EXPIRE_FUTURE_OPTION = "7";

    /**
     * 8:������t�i�敨.OPTION�j
     */
    public static final String ORDER_ACCEPT_FUTURE_OPTION = "8";

    /**
     * 9:��������ʒm�i�敨�j
     */
    public static final String CHANGE_CANCEL_FUTURE = "9";

    /**
     * A:��������ʒm�iOPTION�j
     */
    public static final String CHANGE_CANCEL_OPTION = "A";
    
    /**
     * B:�]�͍X�V
     */
    public static final String TRADING_POWER_UPDATE = "B";

    /**
     * C:���������t(���E�M�p)
     */
    public static final String CHANGE_CANCEL_ACCEPT_EQUITY_MARGIN = "C";

    /**
     * D:���������t(�敨/OPTION)
     */
    public static final String CHANGE_CANCEL_ACCEPT_FUTURE_OPTION = "D";

    /**
     * E:1���f�[�����i���E�o���I���j
     */
    public static final String DAEMON_EQUITY_ORDER_EXEC_END= "E";

    /**
     * F:1���f�[�����i���E�����J�z�j
     */
    public static final String DAEMON_EQUITY_ORDER_CARRY_OVER = "F";

    /**
     * G:1���f�[�����iOP�E�����J�z�j
     */
    public static final String DAEMON_OPTION_ORDER_CARRY_OVER = "G";

    /**
     * H:1���f�[�����i�敨�E�����J�z�j
     */
    public static final String DAEMON_FUTURE_ORDER_CARRY_OVER = "H";

    /**
     * J:�]�͍X�V�i�o�b�`�j
     */
    public static final String TRADING_POWER_UPDATE_BATCH = "J";
    
	/**
     * K:�g���K�[�A������
     */
    public static final String TRIGGER_SUCC_ORDER = "K";

	/**
     * L:�g���K�[�t�w�l�����iEqtype�^Ifo�j
     */
    public static final String EQTYPE_IFO_TRIGGER_STOP_ORDER = "L";

	/**
     * M�F�g���K�[�����蓮�����iEqtype�^Ifo�j
     */
    public static final String EQTYPE_IFO_TRIGGER_MANUAL_EXPIRE = "M";

	/**
     * R:���b�`�N���C�A���g�v�b�V���iEqtype�^Ifo�j
     */
    public static final String EQTYPE_IFO_RICH_PUSH = "R";
    
	/**
     * V:1���f�[�����i�I���b�N�X�A�g�j
     */
    public static final String DAEMON_ORIX_CONNECT = "V";

	/**
     * P:�I���b�N�X�������[�h
     */
    public static final String ORIX_CASHIN_LOAD = "P";

	/**
     * O:�I���b�N�X�����ʒm
     */
    public static final String ORIX_CASHIN_NOTICE = "O";

	/**
     * N�F�蓮�����iEqtype�j
     */
    public static final String EQTYPE_MANUAL_EXPIRE = "N";

	/**
     * U:1���f�[�����i�������ρi�I�����C���J�n�O�j�j
     */
    public static final String DAEMON_FORCED_SETTLE_BEFORE_ONLINE = "U";

    /**
     * W:�蓮�����iIfo�j
     */
    public static final String IFO_MANUAL_EXPIRE = "W";

    /**
     * Y�F�������ρi���F�j
     */
    public static final String FORCED_SETTLE_APPROVAL = "Y";

    /**
     * Z�F�������ρi�񏳔F�j
     */
    public static final String FORCED_SETTLE_UNAPPROVAL = "Z";

    /**
     * j:���ʖ����� �������O�A�E�g
     */
    public static final String FPT_FORCE_LOGOUT = "j";
}
@
