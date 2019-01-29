head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccDataGettingService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������f�[�^�擾�T�[�r�X(WEB3ToSuccDataGettingService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 �Г�(���u) �V�K�쐬
                   2006/11/30 ���G��(���u) �d�l�ύX���f��201
Revesion History : 2008/03/20 ��іQ(���u) �d�l�ύX���f��258
*/

package webbroker3.triggerorder.service.delegate;

import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;

/**
 * (�A�������f�[�^�擾�T�[�r�X)<BR>
 * �A�������f�[�^�擾�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �A�������@@�\�̋��ʏ�������������B<BR>
 * <BR>
 * @@author �Г� <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccDataGettingService extends Service 
{
    
    /**
     * (create�A����������)<BR>
     * �����̘A���������ׂɃv���p�e�B���Z�b�g����B
     * @@param l_succOrderUnit - (�A����������)<BR>
     * �A���������׃I�u�W�F�N�g
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@param l_blnIsPossibleFlagSet - (is�\�t���O�ݒ�)<BR>
     * �����E����E�A�������\�t���O��<BR>
     * �ݒ肷�邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�ݒ肵�Ȃ�<BR>
     * true�F�@@�ݒ肷��<BR>
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100DA
     */
    public void createSuccOrderUnit(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException;
    
    /**
     * (create�\�񒍕�����)<BR>
     * �����̑O�񒍕����ׂɗ\�񒍕����ׂ��Z�b�g����B
     * @@param l_requiredOrderUnit - (�O�񒍕�����)<BR>
     * �O�񒍕�����
     * @@param l_rsvOrderRowList - (�\�񒍕�Row�ꗗ)<BR>
     * �\�񒍕��ꗗ
     * @@param l_blnIsPossibleFlagSet - (is�\�t���O�ݒ�)<BR>
     * �����E����E�A�������\�t���O��<BR>
     * �ݒ肷�邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�ݒ肵�Ȃ�<BR>
     * true�F�@@�ݒ肷��<BR>
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100EA
     */
    public void createRsvOrderUnit(
        WEB3SuccOrderUnit l_requiredOrderUnit, 
        Row[] l_rsvOrderRowList, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException;
    
    /**
     * (set�\�t���O)<BR>
     * �����̘A���������ׂɒ����E����E�A���\�t���O��<BR>
     * �Z�b�g����B<BR>
     * @@param l_succOrderUnit - (�A����������)<BR>
     * �A���������׃I�u�W�F�N�g
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100F9
     */
    public void setPossibleFlag(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get�\�񒍕��P�ʈꗗ)<BR>
     * �ڋq���ێ�����\�񒍕��̈ꗗ��ԋp����B
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID
     * @@param l_strCommodityTypeList - (���i�敪�ꗗ)<BR>
     * ���i�敪�ꗗ
     * @@return Hashtable
     * @@exception WEB3BaseException
     * @@roseuid 43280A5100FC
     */
    public Hashtable getRsvOrderUnitList(long l_lngAccountId, String[] l_strCommodityTypeList)
        throws WEB3BaseException;
    
    /**
     * (get���i�敪)<BR>
     * �����̒�����ʂ�菤�i�敪�𔻕ʂ��A<BR>
     * �ԋp����B<BR>
     * @@param l_orderType - (�������)<BR>
     * �������
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 43280A510109
     */
    public String getCommodityDiv(OrderTypeEnum l_orderType)
        throws WEB3BaseException;
    
    /**
     * (get����)<BR>
     * �����̏،���ЁA�����R�[�h�A���i�敪�� <BR>
     * �Y������������擾���A�ԋp����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 43280A51010B
     */
    public Product getProduct(
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode, 
        String l_strCommodityDiv)
        throws WEB3BaseException;
        
    /**
     * �iget��t���ԋ敪�j <BR>
     * �����̒����J�e�S����������ԃe�[�u��read�Ɏg�p�����t���ԋ敪�𔻕ʂ��A<BR>
     * �ԋp����B <BR>
     * <BR>
     * @@param l_orderCateg - (�����J�e�S��)<BR>
     * �����J�e�S��<BR>
     * @@param l_strSonarTradedCode - (����R�[�h�iSONAR�j)<BR>
     * ����R�[�h�iSONAR�j<BR>
     * @@return String
     * @@exception WEB3SystemLayerException
     * @@roseuid 431F90BF00DF
     */
    public String getTradingTimeType(OrderCategEnum l_orderCateg, String l_strSonarTradedCode) 
        throws WEB3SystemLayerException;
        
    /**
     * (get����)<BR>
     * �����̖����^�C�v�A����ID�ɊY�����������<BR>
     * �擾���A�ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 43280A510118
     */
    public Product getProduct(
        long l_lngProductId, 
        ProductTypeEnum l_productType)
        throws WEB3BaseException;
    
    /**
     * (get���s�����iPR�w�j)<BR>
     * �����̎��s�����ɊY�����鎷�s�����iPR�w�j��<BR>
     * �ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 43280A51011B
     */
    public String getExecutionConditionTypeByPr(OrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get������)<BR>
     * �����P�ʂɊY�����鏈���󋵋敪��ԋp����B
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 43280A510128
     */
    public String getTransactionState(OrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (get�����P��)<BR>
     * �����̒����P��ID�A�����^�C�v�ɊY�����钍���P�ʂ�<BR>
     * �擾���A�ԋp����B<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v
     * @@return OrderUnit
     * @@exception WEB3BaseException
     * @@roseuid 43280A51012A
     */
    public OrderUnit getOrderUnit(
        long l_lngOrderUnitId, 
        ProductTypeEnum l_productType)
        throws WEB3BaseException;
    
    /**
     * (get�����P��)<BR>
     * �����̒���ID�A���i�敪�ɊY�����钍���P�ʂ�<BR>
     * �擾���A�ԋp����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@return OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 43280A510138
     */
    public OrderUnit getOrderUnit(long l_lngOrderId, String l_strCommodityDiv)
        throws WEB3BaseException;
    
    /**
     * (get�����P�ʈꗗ)<BR>
     * �����̖����^�C�v�ɊY�����钍���P�ʂ̈ꗗ��<BR>
     * �擾���A�ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j
     * @@param l_strQueryString - (��������������)<BR>
     * �������� ������
     * @@param l_strQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ��������
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 43280A510147
     */
    public List getOrderUnitList(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond)
        throws WEB3BaseException;
        
    /**
     * (get�A�����������󋵋敪)<BR>
     * �p�����[�^.�����P�ʂ��<BR>
     * PR�w�Ŏg�p����A�������̔����󋵋敪��ԋp����B <BR>
     * @@param l_orderUnit - (�����P��)
     * �����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getToSuccTriggerOrderStatusType(OrderUnit l_orderUnit)
        throws WEB3BaseException;
        
}
@
