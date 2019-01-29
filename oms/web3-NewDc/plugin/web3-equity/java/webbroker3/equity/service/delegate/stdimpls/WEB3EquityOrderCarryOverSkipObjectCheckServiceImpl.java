head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�XImpl
                       (WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/31 羐� (���u) �V�K�쐬
                   2004/11/03 �@@���@@�C�� 
                   2004/12/13 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;


import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipObjectCheckService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�XImpl�j�B
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl
    implements WEB3EquityOrderCarryOverSkipObjectCheckService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl.class);

    /**
     * @@roseuid 40B2E8FD020F
     */
    public WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl()
    {

    }

    /**
     * (is�J�z�����P��)<BR>
     * �����̒����P�ʂ��J�z�Ώۂł��邩�ǂ����𔻒肷��B<BR>
     * �i�����\�b�h�͎s��ǌ�ɃR�[�������B�j<BR>
     * <BR>
     * �����̒����P��.��������������ԊǗ�.get������( )�̑O�c�Ɠ�(*1)
     * <BR>
     * ���@@�����̒����P��.������������������ԊǗ�.get������( )(*2)�@@�̏ꍇ�́A<BR>
     * �����J�z�Ώۂł���Ɣ��肵true��Ԃ��B<BR>
     * ��L�ȊO�̏ꍇ�́Afalse��Ԃ��B<BR>
     * <BR>
     * (*1)���������̒���<BR>
     * (*2)�����ȍ~�܂ŗL���Ȓ���<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3SystemLayerException
     * @@return boolean
     * @@roseuid 406A51E501D0
     */
    public boolean isCarryOverOrderUnit(OrderUnit l_orderUnit)
    throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isCarryOverOrderUnit(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        EqtypeOrderUnitRow l_eqOrderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //get�����P��.������
        String l_strOrderUnitBizDate = l_eqOrderUnitRow.getBizDate();

        //get�����P��.����������
        Date l_orderUnitExpirationDate = l_eqOrderUnitRow.getExpirationDate();
        String l_strExpirationDate = WEB3DateUtility.formatDate(l_orderUnitExpirationDate, "yyyyMMdd");

        //get������ԊǗ�.get������
        Date l_orderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        String l_strOrderBizDate = WEB3DateUtility.formatDate(l_orderBizDate, "yyyyMMdd");

        //get������ԊǗ�.get������( )�̑O�c�Ɠ�
        WEB3GentradeBizDate l_dateCalc =
             new WEB3GentradeBizDate(new Timestamp(l_orderBizDate.getTime()));
        Date l_prevBizDate = l_dateCalc.roll(-1);
        String l_strPrevBizDate = WEB3DateUtility.formatDate(l_prevBizDate, "yyyyMMdd");
        
        log.debug("get�����P��.������ = " + l_strOrderUnitBizDate);
        log.debug("get�����P��.���������� = " + l_strExpirationDate);
        log.debug("get������ԊǗ�.get������ = " + l_strOrderBizDate);
        log.debug("get������ԊǗ�.get������( )�̑O�c�Ɠ� = " + l_strPrevBizDate);
        
        boolean l_result;
        if ((l_strOrderUnitBizDate.compareTo(l_strPrevBizDate) == 0)
            && (l_strExpirationDate.compareTo(l_strOrderBizDate) >= 0))
        {
            l_result = true;
        }
        else
        {
            l_result = false;
        }
        log.debug("l_result = " + l_result);
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
}
@
