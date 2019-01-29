head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationIfoOrderUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�\�񒍕��X�V�T�[�r�X(WEB3ToSuccReservationIfoOrderUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/11 �����Q (���u) �V�K�쐬 
*/
package webbroker3.triggerorder.base.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;

/**
 * (�敨OP�\�񒍕��X�V�T�[�r�X)<BR>
 * �敨OP�\�񒍕��X�V�T�[�r�X�̃C���^�t�F�[�X�B<BR>
 * @@author �����Q
 * @@version 1.0
 */
public interface WEB3ToSuccReservationIfoOrderUpdateService extends Service
{
    /**
     * (insert�\�񒍕�����)<BR>
     * �ŐV�̐敨OP�\�񒍕��P�ʃe�[�u���̓��e���A<BR>
     * �敨OP�\�񒍕��������P���R�[�h�쐬���o�^����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�敨OP�\�񒍕��P��.����ID���Z�b�g�j
     * @@throws WEB3BaseException
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException;

    /**
     * (invalidate�\�񒍕��P��)<BR>
     * �iinvalidateOrderUnit�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P�ʍs������������B�iupdate����j<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@param l_strOrderErrorCode - (�����G���[�R�[�h)<BR>
     * �����G���[�R�[�h�B<BR>
     * �i�G���[�����̓��肪�\��ErrorInfo.error_code���Z�b�g�A<BR>
     * �����G���[�ȊO�Ŏ�������ꍇ�Anull���Z�b�g�j
     * @@throws WEB3BaseException
     */
    public void invalidateOrderUnit(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
        String l_strOrderErrorCode) throws WEB3BaseException;

    /**
     * (invalidateAll�\�񒍕��P��)<BR>
     * �iinvalidateAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (cancel�\�񒍕��P��)<BR>
     * �icancelOrderUnit�j<BR>
     * �����̐敨OP�\�񒍕��P�ʍs���������B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B
     * @@throws WEB3BaseException
     */
    public void cancelOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException;

    /**
     * (cancelAll�\�񒍕��P��)<BR>
     * �icancelAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď������B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (expire�\�񒍕��P��)<BR>
     * �iexpireOrderUnit�j<BR>
     * �����̐敨OP�\�񒍕��P�ʍs������������B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B
     * @@throws WEB3BaseException
     */
    public void expireOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException;

    /**
     * (expireAll�\�񒍕��P��)<BR>
     * �iexpireAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (get�L���\�񒍕��P�ʈꗗ)<BR>
     * �igetOpenReserveIfoOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȑ敨OP�\�񒍕��P�ʍs�̔z���ԋp����B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getOpenReserveIfoOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (deleteAll�\�񒍕��P��)<BR>
     * �ideleteAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��f�[�^��<BR>
     * �S�č폜����B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (get�\�񒍕��P�ʈꗗ)<BR>
     * �igetReserveIfoOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�敨OP�\�񒍕��P�ʍs�̔z���ԋp����B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveIfoOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;

    /**
     * (update�\�񒍕��f�[�^)<BR>
     * �iupdateOrderData�j<BR>
     * <BR>
     * �w�肳�ꂽ�����I�u�W�F�N�g���g�p���A <BR>
     * QueryProcessor�ɂ��\�񒍕��f�[�^�ނ̍X�V���s���B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs
     * @@param l_rsvIfoOrderActionRow - (�敨OP�\�񒍕������s)<BR>
     * �敨OP�\�񒍕������s
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow, 
        RsvIfoOrderActionRow l_rsvIfoOrderActionRow) throws WEB3BaseException;
}
@
