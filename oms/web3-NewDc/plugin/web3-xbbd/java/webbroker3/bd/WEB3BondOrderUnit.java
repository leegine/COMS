head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���������P��(WEB3BondOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
                      : 2006/10/08 �����F (���u) ���f�� 110
*/

package webbroker3.bd;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.OrderUnitIntroduceDivRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.define.WEB3BondDealDivDef;

/**
 * (�g���������P��)<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3BondOrderUnit extends BondOrderUnitImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderUnit.class);

    /**
     * (�g���������P��)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * super(�������P��ID)���R�[������B <BR>
     * @@param l_lngOrderUnitId - (�������P��ID)<BR>
     * �������P��ID
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 44BC382502D7
     */
    public WEB3BondOrderUnit(long l_lngOrderUnitId) throws DataQueryException, DataNetworkException
    {
        super(l_lngOrderUnitId);
    }

    /**
     * (�g���������P��)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * super(�������P��Row)���R�[������B <BR>
     * @@param l_row - (�������P��Row)<BR>
     * �������P��Row
     * @@roseuid 44BC381103BE
     */
    public WEB3BondOrderUnit(BondOrderUnitRow l_row)
    {
        super(l_row);
    }

    /**
     * (get���)<BR>
     * �����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC39A301AA
     */
    public String getDealType()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getDealType();
    }

    /**
     * (get���n��n��)<BR>
     * ���n��n����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���n��n��()�̖߂�l��Ԃ�<BR>
     * @@return Date
     * @@roseuid 44BC3A0901B7
     */
    public Date getForeignDeliveryDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignDeliveryDate();
    }

    /**
     * (get�������b�N�敪)<BR>
     * �������b�N�敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�������b�N�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3A630127
     */
    public String getLockStatus()
    {
        String l_strLockStatus = ((BondOrderUnitRow)this.getDataSourceObject()).getLockStatus();
        return l_strLockStatus;
    }

    /**
     * (get�������敪)<BR>
     * �������敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�������敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3B05035D
     */
    public String getOrderExecStatus()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderExecStatus();
    }

    /**
     * (get���n������)<BR>
     * ���n��������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���n������()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3B3E0045
     */
    public String getForeignBizDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignBizDate();
    }

    /**
     * (get���񒍕��̒����`���l��)<BR>
     * ���񒍕��̒����`���l����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���񒍕��̒����`���l��()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3B7702E9
     */
    public String getOrderChannel()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderChanel();
    }

    /**
     * (get�󒍓���)<BR>
     * �󒍓�����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�󒍓���()�̖߂�l��Ԃ��B<BR>
     * @@return Date
     * @@roseuid 44BC3D000330
     */
    public Date getReceivedDateTime()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getReceivedDateTime();
    }

    /**
     * (get���҃R�[�hSONAR)<BR>
     * �ڋq�̈��҃R�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���҃R�[�hSONAR()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3D270273
     */
    public String getSonarTraderCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getSonarTraderCode();
    }

    /**
     * (get�����P��)<BR>
     * �����P����Ԃ�<BR>
     * @@return double
     * @@roseuid 44CFFD9A034F
     */
    public double getPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getPrice();
    }

    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ʃR�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3D4E01D6
     */
    public String getOrderRequestNumber()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderRequestNumber();
    }

    /**
     * (get���^�C�v)<BR>
     * ���^�C�v��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���^�C�v()�̖߂�l��Ԃ��B<BR>
     * @@return BondTypeEnum
     * @@roseuid 44BC3D7700BD
     */
    public BondTypeEnum getBondType()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getBondType();
    }

    /**
     * (get�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�ʉ݃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3D8301D6
     */
    public String getCurrencyCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getCurrencyCode();
    }

    /**
     * (get���ϋ敪)<BR>
     * ���ϋ敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ϋ敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3DAA0030
     */
    public String getSettlementDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getSettlementDiv();
    }

    /**
     * (get�������敪)<BR>
     * �������敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�������敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3DC3034C
     */
    public String getAutoExecDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getAutoExecDiv();
    }

    /**
     * (get���P��)<BR>
     * ���P����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���P��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44BC3DDC037B
     */
    public double getExecutedPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getExecutedPrice();
    }

    /**
     * (get��בփ��[�g)<BR>
     * ��בփ��[�g��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��בփ��[�g()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44BC3DFF033C
     */
    public double getBaseFxRate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getBaseFxRate();
    }

    /**
     * (get���בփ��[�g)<BR>
     * ���בփ��[�g��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���בփ��[�g()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44BC3E2102A0
     */
    public double getExecFxRate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getExecFxRate();
    }

    /**
     * (get�������(�~��))<BR>
     * �������(�~��)��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�������(�~��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44BC3E3E007D
     */
    public double getTradingPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getTradingPrice();
    }

    /**
     * (get�������(�O��))<BR>
     * �������(�O��)��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�������(�O��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44BC3E660261
     */
    public double getForeignTradingPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignTradingPrice();
    }

    /**
     * (get�o�ߗ��q(�~��))<BR>
     * �o�ߗ��q(�~��)��ԋp<BR>
     * <BR>
     * this.getDataSourceObject().get�o�ߗ��q(�~��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C710A30293
     */
    public double getAccruedInterest()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getAccruedInterest();
    }

    /**
     * (get�o�ߗ��q(�O��))<BR>
     * �o�ߗ��q(�O��)��ԋp<BR>
     * <BR>
     * this.getDataSourceObject().get�o�ߗ��q(�O��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C7101202DC
     */
    public double getForeignAccruedInterest()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignAccruedInterest();
    }

    /**
     * (get��n���(�~��))<BR>
     * ��n���(�~��)��ԋp<BR>
     * <BR>
     * this.getDataSourceObject().get��n���(�~��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C7117E0104
     */
    public double getEstimatedPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getEstimatedPrice();
    }

    /**
     * (get��n���(�O��))<BR>
     * ��n���(�O��)��ԋp<BR>
     * <BR>
     * this.getDataSourceObject().get��n���(�O��)()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 44C71108018D
     */
    public double getForeignEstimatedPrice()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignEstimatedPrice();
    }

    /**
     * (get�o�ߓ���)<BR>
     * �o�ߓ�����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�o�ߓ���()�̖߂�l��Ԃ��B<BR>
     * @@return int
     * @@roseuid 44BC3E95034C
     */
    public int getElapsedDays()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getElapsedDays();
    }

    /**
     * (get�����)<BR>
     * �������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�����()�̖߂�l��Ԃ��B<BR>
     * @@return int
     * @@roseuid 44BC3EBB035C
     */
    public int getCalcBaseDays()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getCalcBaseDays();
    }

    /**
     * (get����)<BR>
     * ������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get����()�̖߂�l��Ԃ��B<BR>
     * @@return Date
     * @@roseuid 44BC3ED7035C
     */
    public Date getExecDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getExecDate();
    }

    /**
     * (get���n����)<BR>
     * ���n������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���n����()�̖߂�l��Ԃ��B<BR>
     * @@return Date
     * @@roseuid 44BC3F000205
     */
    public Date getForeignExecDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getForeignExecDate();
    }

    /**
     * (get�J�X�g�f�B�A���R�[�h)<BR>
     * �J�X�g�f�B�A���R�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�J�X�g�f�B�A���R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3F1B038C
     */
    public String getCustodianCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getCustodianCode();
    }

    /**
     * (get�����o�H�敪)<BR>
     * �����o�H�敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�����o�H�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3F490310
     */
    public String getOrderRootDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getOrderRootDiv();
    }

    /**
     * (get�����P�ʏЉ�敪)<BR>
     * OrderUnitIntroduceDivRow��Ԃ��B<BR>
     * <BR>
     * �P�j�����P�ʏЉ�敪�e�[�u������������B<BR>
     * �@@�@@���������F<BR>
     * �@@�@@�@@�����P�ʏЉ�敪�e�[�u��.�����P��ID��this.getOrderUnitId<BR>
     * �@@�@@�@@�����P�ʏЉ�敪�e�[�u��.�����^�C�v�@@��this.getProductType<BR>
     * �@@�@@�@@�@@���Y�����R�[�h�����݂��Ȃ��ꍇ�A�G���[���X���[���Ȃ��B<BR>
     * <BR>
     * �Q�j�߂�l��Ԃ�<BR>
     * �@@�Q�|�P�j���R�[�h�����݂���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��������Row�I�u�W�F�N�g��Ԃ�<BR>
     * �@@�Q�|�Q�j���R�[�h�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@null��Ԃ�<BR>
     * @@return OrderUnitIntroduceDivRow
     * @@roseuid 44BC3F6E036F
     */
    public OrderUnitIntroduceDivRow getOrderUnitIntroduceDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitIntroduceDiv()";
        log.entering(STR_METHOD_NAME);
        String l_strWhere = "order_unit_id = ? and product_type = ?";
        Object[] l_objbindVars =  new Object[2];
        l_objbindVars[0] = new Long(this.getOrderUnitId());
        l_objbindVars[1] = new Integer(this.getProductType().intValue());
        List l_lisRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow = l_queryProcessor.doFindAllQuery(
                OrderUnitIntroduceDivRow.TYPE,
                l_strWhere,
                null,
                l_objbindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRow == null || l_lisRow.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return (OrderUnitIntroduceDivRow)l_lisRow.get(0);
        }
    }

    /**
     * (getHOST���M�敪)<BR>
     * HOST���M�敪��Ԃ�<BR>
     * <BR>
     * this.getDataSourceObject().getHOST���M�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44CF21C301E1
     */
    public String getHostSendDiv()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getHostSendDiv();
    }

    /**
     * (get�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h��Ԃ�<BR>
     * <BR>
     * this.getDataSourceObject().get�Ǘ��҃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44CFFE2C03DD
     */
    public String getAdminstratorCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getAdministratorCode();
    }

    /**
     * (get�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�����G���[���R�R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3FBA017C
     */
    public String getErrorReasonCode()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getErrorReasonCode();
    }

    /**
     * (get��������ʔ���)<BR>
     * get��������ʔ���<BR>
     * <BR>
     * ��������ʔ���i�������, ����j�𐶐����A�Ԃ��B<BR>
     * [�R���X�g���N�^�̈���]<BR>
     * ������ʁFthis.getOrderType<BR>
     * ����@@�@@�Fthis.get���<BR>
     * @@return WEB3BondOrderTypeJudge
     * @@throws WEB3BaseException
     * @@roseuid 44CAFC7A0343
     */
    public WEB3BondOrderTypeJudge getBondOrderTypeJudge() throws WEB3BaseException
    {
        WEB3BondOrderTypeJudge l_judge =
            new WEB3BondOrderTypeJudge(this.getOrderType(), this.getDealType());
        return l_judge;
    }

    /**
     * (get����敪)<BR>
     * ����敪���擾����B<BR>
     * <BR>
     * �P�jthis.get��������ʔ�����R�[�����A��������ʔ�����쐬����B <BR>
     * <BR>
     * �Q�j��������ʔ���.is���咍��()�̖߂�l == true �̏ꍇ�A <BR>
     * "����"�i����敪�j��ԋp����B <BR>
     * <BR>
     * �R�j��������ʔ���.is���t����()�̖߂�l == true �̏ꍇ�A<BR>
     * "���t"�i����敪�j��ԋp����B <BR>
     * <BR>
     * �S�j��������ʔ���.is���p����()�̖߂�l == true �̏ꍇ�A <BR>
     * "���p"�i����敪�j��ԋp����B <BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44CAFC7A0343
     */
    public String getDealDiv() throws WEB3BaseException
    {
        //�P�jthis.get��������ʔ�����R�[�����A��������ʔ�����쐬����B
        WEB3BondOrderTypeJudge l_judge = this.getBondOrderTypeJudge();

        //�Q�j��������ʔ���.is���咍��()�̖߂�l == true �̏ꍇ
        if (l_judge.isRecruitOrder())
        {
            return WEB3BondDealDivDef.RECRUIT;
        }

        //�R�j��������ʔ���.is���t����()�̖߂�l == true �̏ꍇ
        else if (l_judge.isBuyOrder())
        {
            return WEB3BondDealDivDef.BUY;
        }

        //�S�j��������ʔ���.is���p����()�̖߂�l == true �̏ꍇ
        else if (l_judge.isSellOrder())
        {
            return WEB3BondDealDivDef.SELL;
        }

        else
        {
            return null;
        }
    }

    /**
     * (get����ID)<BR>
     * get����ID<BR>
     * <BR>
     * this.getDataSourceObject().get����ID()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 44BC3FBA017C
     */
    public long getProductId()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getProductId();
    }

    /**
     * (get������)<BR>
     * ��������Ԃ�<BR>
     * <BR>
     * this.getDataSourceObject().get������()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 44BC3FBA017C
     */
    public String getBizDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getBizDate();
    }

    /**
     * (get������)<BR>
     * ��������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get������()�̖߂�l��Ԃ��B<BR>
     * @@return Date
     */
    public Date getPaymentDate()
    {
        return ((BondOrderUnitRow)this.getDataSourceObject()).getPaymentDate();
    }
}
@
