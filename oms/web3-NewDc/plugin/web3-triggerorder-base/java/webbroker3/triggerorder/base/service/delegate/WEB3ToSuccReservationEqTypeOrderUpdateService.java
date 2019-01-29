head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.21.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationEqTypeOrderUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����\�񒍕��X�V�T�[�r�X(WEB3ToSuccReservationEqTypeOrderUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 �s�p (���u) �V�K�쐬 
Revesion History : 2007/4/25 ������ (���u) ���f��NO.232 
*/

package webbroker3.triggerorder.base.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;


/**
 * (�����\�񒍕��X�V�T�[�r�X)<BR>
 * �����\�񒍕��X�V�T�[�r�X�̃C���^�t�F�[�X�B
 *   
 * @@author �s�p
 * @@version 1.0
 */
public interface WEB3ToSuccReservationEqTypeOrderUpdateService extends Service 
{
    
    /**
     * (insert�\�񒍕�����)<BR>
     * �ŐV�̊����\�񒍕��P�ʃe�[�u���̓��e���A<BR>
     * �����\�񒍕��������P���R�[�h�쐬���o�^����B
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�����\�񒍕��P��.����ID���Z�b�g�j
     * @@throws WEB3BaseException
     * @@roseuid 433A22CA0070
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException;
    
    /**
     * (invalidate�\�񒍕��P��)<BR>
     * �iinvalidateOrderUnit�j<BR>
     * <BR>
     * �����\�񒍕��P�ʍs������������B�iupdate����j
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B
     * @@param l_strOrderErrorCode - (�����G���[�R�[�h)<BR>
     * �����G���[�R�[�h�B<BR>
     * �i�G���[�����̓��肪�\��ErrorInfo.error_code���Z�b�g�A<BR>
     * �����G���[�ȊO�Ŏ�������ꍇ�Anull���Z�b�g�j
     * @@throws WEB3BaseException
     * @@roseuid 433A22CA007D
     */
    public void invalidateOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, String l_strOrderErrorCode) throws WEB3BaseException;
    
    /**
     * (invalidateAll�\�񒍕��P��)<BR>
     * �iinvalidateAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (cancel�\�񒍕��P��)<BR>
     * �icancelOrderUnit�j<BR>
     * �����̊����\�񒍕��P�ʍs���������B<BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@throws WEB3BaseException
     */
    public void cancelOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException;
    
    /**
     * (cancelAll�\�񒍕��P��)<BR>
     * �icancelAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď������B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (expire�\�񒍕��P��)<BR>
     *�iexpireOrderUnit�j<BR>
     * �����̊����\�񒍕��P�ʍs������������B<BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@throws WEB3BaseException
     */
    public void expireOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException;
    
    /**
     * (expireAll�\�񒍕��P��)<BR>
     * �iexpireAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (get�L���\�񒍕��P�ʈꗗ)<BR>
     * �igetOpenReserveEqtypeOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȋ����\�񒍕��P�ʍs�̔z���ԋp����B
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 433A22CA009E
     */
    public List getOpenReserveEqtypeOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (deleteAll�\�񒍕��P��)<BR>
     * �ideleteAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��f�[�^��<BR>
     * �S�č폜����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4355E27F034B
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (get�\�񒍕��P�ʈꗗ)<BR>
     * �igetReserveEqtypeOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�����\�񒍕��P�ʍs�̔z���ԋp����B
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveEqtypeOrderUnits(long l_lngParentOrderId) throws WEB3BaseException;
    
    /**
     * (update�\�񒍕��f�[�^)<BR>
     * (updateReserveOrderData)<BR>
     * <BR>
     * �w�肳�ꂽ�����I�u�W�F�N�g���g�p���AQueryProcessor�ɂ��\�񒍕��f�[�^�ނ̍X�V���s���B<BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P�ʍs)<BR>
     * �����\�񒍕��P�ʍs�B
     * @@param l_rsvEqOrderActionRow - (�����\�񒍕������s)<BR>
     * �����\�񒍕������s�B
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, 
        RsvEqOrderActionRow l_rsvEqOrderActionRow) throws WEB3BaseException;

}
@
