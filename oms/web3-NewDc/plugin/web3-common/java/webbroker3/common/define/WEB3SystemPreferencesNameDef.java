head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SystemPreferencesNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�X�e���v���t�@@�����X���萔��`�C���^�t�F�C�X(WEB3SystemPreferencesNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/02 �Г�(sinocom) �V�K�쐬
Revesion History : 2006/06/01 ������(���u) �C���^�[�t�F���X�\�����ENo080
Revesion History : 2006/08/30 �h�C(���u) �C���^�[�t�F���X�\�����ENo086
Revesion History : 2006/09/18 �h�C(���u)  �c�a���C�A�E�g(�V�X�e���v���t�@@�����X)�ɂ��
Revesion History : 2006/10/18 �h�C(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo432
Revesion History : 2007/03/28 �L���E�ĕ�(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo481
Revesion History : 2007/04/09 �L���E�ĕ�(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo484
Revesion History : 2007/04/19 �h�C(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo487
Revesion History : 2007/12/10 �h�C(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo583
Revesion History : 2008/07/14 ��іQ(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo635
Revesion History : 2009/02/05 ��іQ(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo674
Revesion History : 2009/08/19 ��іQ(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo692
*/
package webbroker3.common.define;

/**
 * �V�X�e���v���t�@@�����X���萔��`�C���^�t�F�C�X
 *
 * @@author meng-d
 * @@version 1.0
 */
public interface WEB3SystemPreferencesNameDef
{
    /**
     * DL_REC_COUNT_TRADEHISTORYLIST�F��������ꗗ�t�@@�C���_�E�����[�h�ɂ����钴�ߌ����l
     */
    public final static String DL_REC_COUNT_TRADEHISTORYLIST = "DL_REC_COUNT_TRADEHISTORYLIST";

    /**
     * _MIN_ACCOUNT:��p�U��������x������
     */
    public final static String MIN_ACCOUNT = "_MIN_ACCOUNT";

    /**
     * ONLINE_SERVICE_START_TIME:�I�����C���T�[�r�X�J�n����
     */
    public final static String ONLINE_SERVICE_START_TIME = "system.onlineservice.starttime";

    /**
     * �g���K�[���������x���������{��Ѓt���O
     */
    public final static String IS_SUBMIT_DELAY_ORDER = ".rls.is.submit.delay.order";

    /**
     * _FIN_ACCOUNT_NAME:�������`�l
     */
    public final static String FIN_ACCOUNT_NAME = "_FIN_ACCOUNT_NAME";

    /**
     * DL_REC_COUNT_PROFITLOSSLIST�F���v���׃t�@@�C���_�E�����[�h�ɂ����钴�ߌ����l
     */
    public final static String DL_REC_COUNT_PROFITLOSSLIST = "DL_REC_COUNT_PROFITLOSSLIST";

    /**
     * PAY_TRIGGER_ORDER_MAX_COUNT�F�o���������R�[�h�g���K�[���s����Max�l
     */
    public final static String PAY_TRIGGER_ORDER_MAX_COUNT = "PAY_TRIGGER_ORDER_MAX_COUNT";

    /**
     * DL_REC_COUNT_SERVICE_ACCOUNT_DATA�F�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h�ɂ����钴�ߌ����l
     */
    public final static String DL_REC_COUNT_SERVICE_ACCOUNT_DATA = "DL_REC_COUNT_SERVICE_ACCOUNT_DATA";

    /**
     * exec.notify.wait.seconds�FSONAR���̖͂�肪�����ʒm�����WEB�V�ɓ��������ꍇ�A�����ʒm�����b�҂��ݒ肷��
     */
    public final static String EXEC_NOTIFY_WAIT_SECONDS = "exec.notify.wait.seconds";

    /**
     * UL_DOCADMIN_DEVMANAGE_COUNT:�i�����@@���ʌ�t�Ǘ��A�b�v���[�h�����j<BR>
     * �@@���h�L�������g�Ǘ������@@��t�{��UL�Ŏg�p
     */
    public final static String UL_DOCADMIN_DEVMANAGE_COUNT = "UL_DOCADMIN_DEVMANAGE_COUNT";

    /**
     * _SBS_DRAW_DD:�i�莞��z���t�@@�������j<BR>
     * �@@���莞��z���t�̒��ؓ��Z�o�Ɏg�p�iXX=�،���ЃR�[�h�j<BR>
     */
    public final static String SBS_DRAW_DD = "_SBS_DRAW_DD";

    /**
     * _SBS_DAY_COUNT:�i�莞��z���t�@@�N�Z�c�Ɠ����j<BR>
     * �@@���莞��z���t�̒��ؓ��Z�o�ɁiXX=�،���ЃR�[�h�j<BR>
     */
    public final static String SBS_DAY_COUNT = "_SBS_DAY_COUNT";

    /**
     * _SBS_DRAW_DD_BONUS:�i�莞��z���t�ܗ^�������j<BR>
     */
    public final static String SBS_DRAW_DD_BONUS = "_SBS_DRAW_DD_BONUS";

    /**
     * _SBS_DAY_COUNT_BONUS:�i�莞��z���t�ܗ^���ؓ��N�Z�����j<BR>
     */
    public final static String SBS_DAY_COUNT_BONUS = "_SBS_DAY_COUNT_BONUS";

    /**
     * _SBS_BONUS_MONTH:�i�莞��z���t�{�[�i�X���j<BR>
     */
    public final static String SBS_BONUS_MONTH = "_SBS_BONUS_MONTH";

    /**
     * era_condition_year:�i�N����������j<BR>
     */
    public final static String ERA_CONDITION_YEAR = "era_condition_year";

    /**
     * web3.adminAccountOpenAccTransfer.MaxAmount:�i�����J�݈ڊǂ̖���ő又�������j<BR>
     */
    public final static String ADMIN_ACCOUNT_OPEN_ACC_TRANSFER_MAX_AMOUNT = "web3.adminAccountOpenAccTransfer.MaxAmount";
}
@
