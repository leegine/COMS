head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorHelpler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���G���W���ւ̒������M�w���p�[(WEB3RlsConnectorHelpler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 �V�� �h�O(FLJ) �V�K�쐬
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.price.PriceCondOrder;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.simple.SimpleCondOrder;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.rlsgateway.data.OmsConOrderRequestRow;
import webbroker3.rlsgateway.data.RlsCondOrderRow;
import webbroker3.rlsgateway.data.RlsOmsOrderRow;
import webbroker3.rlsgateway.data.RlsPriceCondRow;

/**
 *
 * ���[���G���W���ւ̒������M�w���p�[
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsConnectorHelpler
{
    
    /**
     * create�����t������ <BR>
     * �����ɉ����ď����t�������𐶐�����B <BR>
     * <BR>
     * �P�j�V�K���ȑO��CondOrder���쐬���邩�ŕ��򂷂�B <BR>
     * <BR>
     * �Q�j�����t�������^�C�v�ŏ����𕪊򂷂�B <BR>
     * <BR>
     * �R�j�����ɉ����������𐶐�����B <BR>
     * <BR>
     * @@param OmsConOrderRequestRow - (���[���G���W���ւ̃��N�G�X�g�ʒm)
     * @@param WEB3RlsOrders - (�����ꗗ)
     * @@param boolean - (�V�K=true �ȑO=false)
     * @@return CondOrder
     */
    public CondOrder createCondOrder(OmsConOrderRequestRow l_request, WEB3RlsOrders l_rlsOrders, boolean l_isNew);

    /**
     * create�t�w�l���� <BR>
     * �t�w�l�����𐶐�����B <BR>
     * <BR>
     * �P�j�t�w�l�����𐶐�����B <BR>
     * <BR>
     * @@param RlsCondOrderRow - (�����t������)
     * @@param RlsPriceCondRow - (�t�w�l����)
     * @@return PriceCondOrder
     */
    public PriceCondOrder createPriceCondOrder(RlsCondOrderRow l_rlsCondOrderRow, RlsPriceCondRow l_rlsPriceCondRow);
    
    /**
     * create�V���v�� <BR>
     * �V���v�������𐶐�����B <BR>
     * <BR>
     * �P�j�V���v�������𐶐�����B <BR>
     * <BR>
     * @@param RlsCondOrderRow - (�����t������)
     * @@param RlsOmsOrderRow - (�������e)
     * @@return SimpleCondOrder
     */
    public SimpleCondOrder createSimpleCondOrder(RlsCondOrderRow l_rlsCondOrderRow, RlsOmsOrderRow l_rlsOmsOrderRow);
    
    /**
     * addDb���� <BR>
     * DB�������𒍕��ꗗ�ɕt�^����B <BR>
     * <BR>
     * �P�jDB�������𒍕��ꗗ�ɕt�^����B <BR>
     * <BR>
     * @@param OmsConOrderRequestRow - (���[���G���W���ւ̃��N�G�X�g�ʒm)
     * @@param WEB3RlsOrders - (�����ꗗ)
     * @@return WEB3RlsOrders
     */
    public WEB3RlsOrders addDbOrders(OmsConOrderRequestRow l_request, WEB3RlsOrders l_rlsOrders) throws WEB3SystemLayerException;
    
    
    /**
     * create�����ꗗ <BR>
     * �����P�ʃI�u�W�F�N�g���璍���ꗗ���쐬����B <BR>
     * <BR>
     * �P�jDB�������𒍕��ꗗ�ɕt�^����B <BR>
     * <BR>
     * �Q�j�����t�������^�C�v�ŏ����𕪊򂷂�B <BR>
     * <BR>
     * �P�jDB�������𒍕��ꗗ�ɕt�^����B <BR>
     * <BR>
     * @@param OmsConOrderRequestRow - (���[���G���W���ւ̃��N�G�X�g�ʒm)
     * @@param OrderUnit - (�����P�ʃI�u�W�F�N�g)
     * @@return WEB3RlsOrders
     */
    public WEB3RlsOrders createRlsOrders(OmsConOrderRequestRow l_request, OrderUnit l_unit);
}
@
