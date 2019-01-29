head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�z�ꌏ�T�[�r�X(WEB3EquityOrderCarryOverUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 羐� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * �i�����J�z�ꌏ�T�[�r�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverUnitService extends Service 
{
   
    /**
     * (insert�J�z����)<BR>
     * �����Ɏw�肳�ꂽ�����P�ʃI�u�W�F�N�g����A
     * �J�z�̐V�K�����f�[�^�i�������� or �V�K�� or �ԍρj���쐬����B
     * @@param l_orderUnit - �����P��<BR>
     * �J�z�Ώۂ̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4137CDD9011C
     */
    public boolean insertCarryOverOrder(OrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (update�J�z������)<BR>
     * �����R���ŃG���[�ƂȂ����J�z�������̒����G���[���R�R�[�h�Ȃǂ��X�V����B<BR>
     * <BR>
     * �P�j�@@�J�z�������̒����G���[���R�R�[�h ��update����B<BR>
     * <BR>
     * �P�|�P�j�@@�ȉ��̏����ɊY������J�z�������̒����P�ʃ��R�[�h��update����B<BR>
     * �@@�@@<����><BR>
     * �@@�@@�@@�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�����G���[���R�R�[�h = <BR>
     * �p�����[�^.�����G���[���R�R�[�h<BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �P�|�Q�j�@@�ȉ��̏����ɊY������J�z�������̒��������́A<BR>
     * �@@�@@�@@�@@�@@�ŏI�������R�[�h�̒����G���[���R�R�[�h ��update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����e�[�u��.�����P��ID�@@���@@<BR>
     * �p�����[�^.�����P��.�����P��ID�@@����<BR>
     * �@@�@@�����e�[�u��.��������ԍ��@@���@@<BR>
     * �p�����[�^.�����P��.���������ŏI�ʔ�<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�������R�[�h.�����G���[���R�R�[�h�@@���@@<BR>
     * �p�����[�^.�����G���[���R�R�[�h<BR>
     * �@@�@@�������R�[�h.�X�V���t�@@���@@���ݓ���<BR>
     * <BR>
     * �P�|�R�j�@@�ȉ��̏����ɊY������A<BR>
     * �J�z�������̒����i�w�b�_�j�̍X�V������update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID�@@���@@�p�����[�^.�����P��.����ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V���t�@@���@@���ݓ���<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �J�z���̒����P��
     * @@param l_strOrderErrReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R
     * @@roseuid 4121D908036F
     */
    public void updateOriginalOrder(
        EqTypeOrderUnit l_orderUnit,
        String l_strOrderErrReasonCode)
        throws WEB3SystemLayerException;
}
@
