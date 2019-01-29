head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoFrontOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����T�[�r�X(WEB3IfoFrontOrderService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/25 �����q (���u) �V�K�쐬 �d�l�ύX�@@���f��NO.587
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.ifo.data.HostFotypeOrderAllParams;

/**
 * (�敨OP�����T�[�r�X)<BR>
 * �敨OP�����T�[�r�X�̃C���^�t�F�[�X�B<BR>
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3IfoFrontOrderService extends Service
{
    /**
     * (get�t�����g�����V�X�e���敪)<BR>
     * �����̎s��R�[�h�ɂ��A�t�����g�����V�X�e���敪���擾���ԋp����B<BR>
     * <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * WEB�V�̎s��R�[�h
     * @@return String
     */
    public String getFrontOrderSystemCode(String l_strMarketCode);

    /**
     * (get�����o�H�敪)<BR>
     * �����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * WEB�V�̎s��R�[�h
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(String l_strInstitutionCode, String l_strMarketCode)
        throws WEB3BaseException;

    /**
     * (get����Rev�J�n�ʒuIN�Г���������)<BR>
     * �Г��������ڂ̕����񒆂́A����Rev.�J�n�ʒu��Ԃ��B<BR>
     * <BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode();

    /**
     * (get����Rev����)<BR>
     * ����Rev.�̌�����Ԃ��B<BR>
     * <BR>
     * @@return int
     */
    public int getFigureOfOrderRev();

    /**
     * (get�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�Г��������ځv�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException;

    /**
     * (is�s��ʒm������IN�x�e���ԑ�)<BR>
     * ��������x�e���Ԓ��̎��ԑтɂ����āA<BR>
     * �w��̒����Ɋ֌W����f�[�^�i�����A������܂ށj���s��ɒʒm���ł��邩�ǂ�����<BR>
     * �敨OP��������L���[�e�[�u���̃f�[�^��蔻�肵�Ԃ��B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get�敨OP��������L���[)<BR>
     * �w��̒����P�ʂɊY������敨OP��������L���[���擾���Ԃ��B<BR>
     * �Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return �敨OP��������L���[Params
     * @@throws WEB3BaseException
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get����������Rev)<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A<BR>
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B<BR>
     * <BR>
     * @@param l_ifoOrderUnitAfter - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(IfoOrderUnit l_ifoAfterOrderUnit)
        throws WEB3BaseException;

    /**
     * (lock�敨OP��������L���[)<BR>
     * �w��̒����P�ʂ̐敨OP��������L���[�f�[�^�ɋ��L���b�N��������B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public void lockHostFotypeOrderAll(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get������MQ�f�[�^�R�[�h)<BR>
     * �����o�H�敪�A�敨�I�v�V�����敪�ɂ��A�������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p��������擾���ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P��
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrderMQDataCode(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get���������MQ�f�[�^�R�[�h)<BR>
     * �����o�H�敪�A�敨�I�v�V�����敪�ɂ��A����������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p��������擾���ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P��
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeCancelMQDataCode(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get��������������o�H�敪)<BR>
     * �����̒�������Ώۂ̒����P�ʃI�u�W�F�N�g���A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get�i������j�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�i������j�Г��������ځv�ݒ�p��������擾���ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get�t�����g����������敪�R�[�h)<BR>
     * �����̎s��R�[�h���A�t�����g����������敪�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode);  

    /**
     * (get������ؑ�)<BR>
     * �w��̒����P�ʂɍ��v���锭����ؑփI�u�W�F�N�g���擾���Ԃ�<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return ������ؑ�
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (update�敨OP��������L���[AT��t���ԊO)<BR>
     * �O���t���ԊO�������Ĕ������邽�߂̃L���[�f�[�^�X�V���s���B<BR>
     * <BR>
     * @@param l_orderUnitAfter - (�����P�ʁi�X�V��j)<BR>
     * �X�V��̒����P�ʃI�u�W�F�N�g�B
     * @@param l_orderUnitBefore - (�����P�ʁi�X�V�O�j)<BR>
     * �X�V�O�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_blnIsCancel - (is���)<BR>
     * ������ǂ����𔻒肷��t���O�B
     * �itrue�F����Afalse�F����ȊO�j
     * @@throws WEB3BaseException
     */
    public void updateHostFotypeOrderAllAtAcceptOvertime(
        IfoOrderUnit l_orderUnitAfter,
        IfoOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel) throws WEB3BaseException;

    /**
     * (getNext����Rev)<BR>
     * �����̒���Rev��1���Z�����l��Ԃ��B<BR>
     * <BR>
     * @@param l_strOrderRev - (����Rev)
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getNextOrderRev(String l_strOrderRev)
        throws WEB3BusinessLayerException;
}
@
